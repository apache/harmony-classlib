/* Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.harmony.nio.internal;

import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.IllegalSelectorException;
import java.nio.channels.Pipe;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelectableChannel;
import java.nio.channels.spi.AbstractSelectionKey;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.harmony.luni.platform.FileDescriptorHandler;
import org.apache.harmony.luni.platform.Platform;

/*
 * Default implementation of java.nio.channels.Selector
 */
final class SelectorImpl extends AbstractSelector {

    private static final int MOCK_WRITEBUF_SIZE = 1;

    private static final int MOCK_READBUF_SIZE = 8;

    private static final int NA = 0;

    private static final int READABLE = 1;

    private static final int WRITEABLE = 2;

    private static final int SELECT_BLOCK = -1;

    private static final int SELECT_NOW = 0;

    // keysLock is used to brief synchronization when get selectionKeys snapshot
    // before selection
    final Object keysLock = new Object();

    boolean keySetChanged = true;

    private SelectionKey[] keys = new SelectionKey[1];

    private final Set<SelectionKey> keysSet = new HashSet<SelectionKey>();

    private Set<SelectionKey> unmodifiableKeys = Collections
            .unmodifiableSet(keysSet);

    private final Set<SelectionKey> selectedKeys = new HashSet<SelectionKey>();

    private Set<SelectionKey> unaddableSelectedKeys = new UnaddableSet<SelectionKey>(
            selectedKeys);

    // sink and source are used by wakeup()
    private Pipe.SinkChannel sink;

    private Pipe.SourceChannel source;

    private FileDescriptor sourcefd;

    private FileDescriptor[] readableFDs;

    private FileDescriptor[] writableFDs;

    private int lastKeyIndex = -1;

    private int readableKeysCount = 0;

    private int writableKeysCount = 0;

    private int[] keysToReadableFDs;

    private int[] keysToWritableFDs;

    private int[] readableFDsToKeys;

    private int[] writableFDsToKeys;

    public SelectorImpl(SelectorProvider selectorProvider) {
        super(selectorProvider);
        try {
            Pipe mockSelector = selectorProvider.openPipe();
            sink = mockSelector.sink();
            source = mockSelector.source();
            sourcefd = ((FileDescriptorHandler) source).getFD();
            source.configureBlocking(false);

            readableFDs = new FileDescriptor[1];
            writableFDs = new FileDescriptor[0];
            keysToReadableFDs = new int[1];
            keysToWritableFDs = new int[0];
            readableFDsToKeys = new int[1];
            writableFDsToKeys = new int[0];

            // register sink channel
            readableFDs[0] = sourcefd;
            keys[0] = source.keyFor(this);

            // index it
            keysToReadableFDs[0] = 0;
            readableFDsToKeys[0] = 0;

            lastKeyIndex = 0;
            readableKeysCount = 1;

        } catch (IOException e) {
            // do nothing
        }
    }

    /*
     * @see java.nio.channels.spi.AbstractSelector#implCloseSelector()
     */
    protected void implCloseSelector() throws IOException {
        synchronized (this) {
            synchronized (keysSet) {
                synchronized (selectedKeys) {
                    doCancel();
                    for (SelectionKey sk : keys) {
                        if (sk != null) {
                            deregister((AbstractSelectionKey) sk);
                        }
                    }
                    wakeup();
                }
            }
        }
    }

    private void ensureCapacity(int c) {
        // TODO: rewrite array handling as some internal class
        if (c >= keys.length) {
            SelectionKey[] newKeys = new SelectionKey[(keys.length + 1) << 1];
            System.arraycopy(keys, 0, newKeys, 0, keys.length);
            keys = newKeys;
        }

        if (c >= keysToReadableFDs.length) {
            int[] newKeysToReadableFDs = new int[(keysToReadableFDs.length + 1) << 1];
            System.arraycopy(keysToReadableFDs, 0, newKeysToReadableFDs, 0,
                    keysToReadableFDs.length);
            keysToReadableFDs = newKeysToReadableFDs;
        }

        if (c >= keysToWritableFDs.length) {
            int[] newKeysToWritableFDs = new int[(keysToWritableFDs.length + 1) << 1];
            System.arraycopy(keysToWritableFDs, 0, newKeysToWritableFDs, 0,
                    keysToWritableFDs.length);
            keysToWritableFDs = newKeysToWritableFDs;
        }

        if (readableKeysCount >= readableFDs.length) {
            FileDescriptor[] newReadableFDs = new FileDescriptor[(readableFDs.length + 1) << 1];
            System.arraycopy(readableFDs, 0, newReadableFDs, 0,
                    readableFDs.length);
            readableFDs = newReadableFDs;
        }

        if (readableKeysCount >= readableFDsToKeys.length) {
            int[] newReadableFDsToKeys = new int[(readableFDsToKeys.length + 1) << 1];
            System.arraycopy(readableFDsToKeys, 0, newReadableFDsToKeys, 0,
                    readableFDsToKeys.length);
            readableFDsToKeys = newReadableFDsToKeys;
        }

        if (writableKeysCount >= writableFDs.length) {
            FileDescriptor[] newWriteableFDs = new FileDescriptor[(writableFDs.length + 1) << 1];
            System.arraycopy(writableFDs, 0, newWriteableFDs, 0,
                    writableFDs.length);
            writableFDs = newWriteableFDs;
        }

        if (writableKeysCount >= writableFDsToKeys.length) {
            int[] newWritableFDsToKeys = new int[(writableFDsToKeys.length + 1) << 1];
            System.arraycopy(writableFDsToKeys, 0, newWritableFDsToKeys, 0,
                    writableFDsToKeys.length);
            writableFDsToKeys = newWritableFDsToKeys;
        }
    }

    private void limitCapacity() {
        // TODO: implement array squeezing
    }

    /**
     * Adds the specified key to storage and updates the indexes accordingly
     * 
     * @param sk
     *            key to add
     * @return index in the storage
     */
    private int addKey(SelectionKey sk) {

        lastKeyIndex++;
        int c = lastKeyIndex;

        // make sure that enough space is available
        ensureCapacity(c);

        // add to keys storage
        keys[c] = sk;

        // cache the fields
        int ops = sk.interestOps();
        FileDescriptor fd = ((FileDescriptorHandler) sk.channel()).getFD();

        // presume that we have no FD associated
        keysToReadableFDs[c] = -1;
        keysToWritableFDs[c] = -1;

        // if readable operations requested
        if (((SelectionKey.OP_ACCEPT | SelectionKey.OP_READ) & ops) != 0) {
            // save as readable FD
            readableFDs[readableKeysCount] = fd;

            // create index
            keysToReadableFDs[c] = readableKeysCount;
            readableFDsToKeys[readableKeysCount] = c;

            readableKeysCount++;
        }

        // if writable operations requested
        if (((SelectionKey.OP_CONNECT | SelectionKey.OP_WRITE) & ops) != 0) {
            // save as writable FD
            writableFDs[writableKeysCount] = fd;

            // create index
            keysToWritableFDs[c] = writableKeysCount;
            writableFDsToKeys[writableKeysCount] = c;

            writableKeysCount++;
        }

        return c;
    }

    /**
     * Deletes the key from the internal storage and updates the indexes
     * accordingly
     * 
     * @param sk
     *            key to delete
     */
    private void delKey(SelectionKey sk) {
        int index = ((SelectionKeyImpl) sk).getIndex();

        // === deleting the key and FDs

        // key is null now
        keys[index] = null;

        // free FDs connected with the key
        // free indexes
        int readableIndex = keysToReadableFDs[index];
        if (readableIndex != -1) {
            readableFDs[readableIndex] = null;
            readableFDsToKeys[readableIndex] = -1;
            keysToReadableFDs[index] = -1;
        }

        int writableIndex = keysToWritableFDs[index];
        if (writableIndex != -1) {
            writableFDs[writableIndex] = null;
            writableFDsToKeys[writableIndex] = -1;
            keysToWritableFDs[index] = -1;
        }

        // === compacting arrays and indexes

        // key compaction
        if (keys[lastKeyIndex] != null) {
            keys[index] = keys[lastKeyIndex];
            keys[lastKeyIndex] = null;

            // update key index
            ((SelectionKeyImpl) keys[index]).setIndex(index);

            // the key in the new position references the same FDs
            keysToReadableFDs[index] = keysToReadableFDs[lastKeyIndex];
            keysToWritableFDs[index] = keysToWritableFDs[lastKeyIndex];

            // associated FDs reference the same key at new index
            if (keysToReadableFDs[index] != -1) {
                readableFDsToKeys[keysToReadableFDs[index]] = index;
            }

            if (keysToWritableFDs[index] != -1) {
                writableFDsToKeys[keysToWritableFDs[index]] = index;
            }

        }
        lastKeyIndex--;

        // readableFDs compaction
        if (readableIndex != -1) {
            if (readableFDs[readableKeysCount - 1] != null) {
                readableFDs[readableIndex] = readableFDs[readableKeysCount - 1];

                // new FD references the same key
                readableFDsToKeys[readableIndex] = readableFDsToKeys[readableKeysCount - 1];

                // the key references the same FD at new index
                if (readableFDsToKeys[readableIndex] != -1) {
                    keysToReadableFDs[readableFDsToKeys[readableIndex]] = readableIndex;
                }
            }
            readableKeysCount--;
        }

        // writableFDs compaction
        if (writableIndex != -1) {
            if (writableFDs[writableKeysCount - 1] != null) {
                writableFDs[writableIndex] = writableFDs[writableKeysCount - 1];

                // new FD references the same key
                writableFDsToKeys[writableIndex] = writableFDsToKeys[writableKeysCount - 1];

                // the key references the same FD at new index
                if (writableFDsToKeys[writableIndex] != -1) {
                    keysToWritableFDs[writableFDsToKeys[writableIndex]] = writableIndex;
                }
            }
            writableKeysCount--;
        }
    }

    /**
     * 
     * @param sk
     */
    void modKey(SelectionKey sk) {
        // TODO: update indexes rather than recreate the key
        delKey(sk);
        addKey(sk);
    }

    /*
     * @see java.nio.channels.spi.AbstractSelector#register(java.nio.channels.spi.AbstractSelectableChannel,
     *      int, java.lang.Object)
     */
    protected SelectionKey register(AbstractSelectableChannel channel,
            int operations, Object attachment) {
        if (!provider().equals(channel.provider())) {
            throw new IllegalSelectorException();
        }
        synchronized (this) {
            synchronized (keysSet) {

                // create the key
                SelectionKey sk = new SelectionKeyImpl(channel, operations,
                        attachment, this);

                int index = addKey(sk);
                ((SelectionKeyImpl) sk).setIndex(index);

                return sk;
            }
        }
    }

    /*
     * @see java.nio.channels.Selector#keys()
     */
    public synchronized Set<SelectionKey> keys() {
        closeCheck();

        keysSet.clear();

        if (keys.length != lastKeyIndex + 1) {
            SelectionKey[] chompedKeys = new SelectionKey[lastKeyIndex + 1];
            System.arraycopy(keys, 0, chompedKeys, 0, lastKeyIndex + 1);
            keysSet.addAll(Arrays.asList(chompedKeys));
        } else {
            keysSet.addAll(Arrays.asList(keys));
        }

        keysSet.remove(source.keyFor(this));
        return unmodifiableKeys;
    }

    private void closeCheck() {
        if (!isOpen()) {
            throw new ClosedSelectorException();
        }
    }

    /*
     * @see java.nio.channels.Selector#select()
     */
    public int select() throws IOException {
        return selectInternal(SELECT_BLOCK);
    }

    /*
     * @see java.nio.channels.Selector#select(long)
     */
    public int select(long timeout) throws IOException {
        if (timeout < 0) {
            throw new IllegalArgumentException();
        }
        return selectInternal((0 == timeout) ? SELECT_BLOCK : timeout);
    }

    /*
     * @see java.nio.channels.Selector#selectNow()
     */
    public int selectNow() throws IOException {
        return selectInternal(SELECT_NOW);
    }

    private int selectInternal(long timeout) throws IOException {
        closeCheck();
        synchronized (this) {
            synchronized (keysSet) {
                synchronized (selectedKeys) {
                    doCancel();
                    int[] readyChannels = null;
                    boolean isBlock = (SELECT_NOW != timeout);
                    prepareChannels();
                    try {
                        if (isBlock) {
                            begin();
                        }
                        readyChannels = Platform.getNetworkSystem().select(
                                readableFDs, writableFDs, timeout);
                    } finally {
                        if (isBlock) {
                            end();
                        }
                    }
                    return processSelectResult(readyChannels);
                }
            }
        }
    }

    private boolean isConnected(SelectionKeyImpl key) {
        SelectableChannel channel = key.channel();
        if (channel instanceof SocketChannel) {
            return ((SocketChannel) channel).isConnected();
        }
        return true;
    }

    // Prepares and adds channels to list for selection
    private void prepareChannels() {

        // chomp the arrays if needed

        if (readableFDs.length != readableKeysCount) {
            FileDescriptor[] chompedReadableFDs = new FileDescriptor[readableKeysCount];
            System.arraycopy(readableFDs, 0, chompedReadableFDs, 0,
                    readableKeysCount);
            readableFDs = chompedReadableFDs;
        }

        if (writableFDs.length != writableKeysCount) {
            FileDescriptor[] chompedWriteableFDs = new FileDescriptor[writableKeysCount];
            System.arraycopy(writableFDs, 0, chompedWriteableFDs, 0,
                    writableKeysCount);
            writableFDs = chompedWriteableFDs;
        }

    }

    /*
     * Analyses selected channels and adds keys of ready channels to
     * selectedKeys list.
     * 
     * readyChannels are encoded as concatenated array of flags for readable
     * channels followed by writable channels.
     */
    private int processSelectResult(int[] readyChannels) throws IOException {
        if (0 == readyChannels.length) {
            return 0;
        }
        // if the mock channel is selected, read the content.
        if (READABLE == readyChannels[0]) {
            ByteBuffer readbuf = ByteBuffer.allocate(MOCK_READBUF_SIZE);
            while (source.read(readbuf) > 0) {
                readbuf.flip();
            }
        }
        int selected = 0;

        for (int i = 1; i < readyChannels.length; i++) {

            if (readyChannels[i] != NA) {
                SelectionKeyImpl key = (SelectionKeyImpl) (i >= readableKeysCount ? keys[writableFDsToKeys[i
                        - readableKeysCount]]
                        : keys[readableFDsToKeys[i]]);

                if (null == key) {
                    continue;
                }

                int ops = key.interestOps();
                int selectedOp = 0;

                switch (readyChannels[i]) {

                    case READABLE:
                        selectedOp = (SelectionKey.OP_READ | SelectionKey.OP_ACCEPT)
                                & ops;
                        break;
                    case WRITEABLE:
                        if (isConnected(key)) {
                            selectedOp = SelectionKey.OP_WRITE & ops;
                        } else {
                            selectedOp = SelectionKey.OP_CONNECT & ops;
                        }
                        break;
                }

                if (0 != selectedOp) {
                    boolean isOldSelectedKey = selectedKeys.contains(key);
                    if (isOldSelectedKey && key.readyOps() != selectedOp) {
                        key.setReadyOps(key.readyOps() | selectedOp);
                        selected++;
                    } else if (!isOldSelectedKey) {
                        key.setReadyOps(selectedOp);
                        selectedKeys.add(key);
                        selected++;
                    }
                }

            }
        }

        return selected;
    }

    /*
     * @see java.nio.channels.Selector#selectedKeys()
     */
    public synchronized Set<SelectionKey> selectedKeys() {
        closeCheck();
        return unaddableSelectedKeys;
    }

    private void doCancel() {
        Set<SelectionKey> cancelledKeys = cancelledKeys();
        synchronized (cancelledKeys) {
            if (cancelledKeys.size() > 0) {
                for (SelectionKey currentkey : cancelledKeys) {
                    delKey(currentkey);
                    deregister((AbstractSelectionKey) currentkey);
                    selectedKeys.remove(currentkey);
                }
            }
            cancelledKeys.clear();
            limitCapacity();
        }
    }

    /*
     * @see java.nio.channels.Selector#wakeup()
     */
    public Selector wakeup() {
        try {
            sink.write(ByteBuffer.allocate(MOCK_WRITEBUF_SIZE));
        } catch (IOException e) {
            // do nothing
        }
        return this;
    }

    private static class UnaddableSet<E> implements Set<E> {

        private Set<E> set;

        UnaddableSet(Set<E> set) {
            this.set = set;
        }

        public boolean equals(Object object) {
            return set.equals(object);
        }

        public int hashCode() {
            return set.hashCode();
        }

        public boolean add(E object) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection<? extends E> c) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            set.clear();
        }

        public boolean contains(Object object) {
            return set.contains(object);
        }

        public boolean containsAll(Collection<?> c) {
            return set.containsAll(c);
        }

        public boolean isEmpty() {
            return set.isEmpty();
        }

        public Iterator<E> iterator() {
            return set.iterator();
        }

        public boolean remove(Object object) {
            return set.remove(object);
        }

        public boolean removeAll(Collection<?> c) {
            return set.removeAll(c);
        }

        public boolean retainAll(Collection<?> c) {
            return set.retainAll(c);
        }

        public int size() {
            return set.size();
        }

        public Object[] toArray() {
            return set.toArray();
        }

        public <T> T[] toArray(T[] a) {
            return set.toArray(a);
        }
    }
}
