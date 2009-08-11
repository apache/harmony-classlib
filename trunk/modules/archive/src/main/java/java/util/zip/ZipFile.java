/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
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

package java.util.zip;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.NoSuchElementException;

import org.apache.harmony.archive.internal.nls.Messages;
import org.apache.harmony.luni.util.Util;

/**
 * This class provides random read access to a <i>ZIP-archive</i> file.
 * <p>
 * While {@code ZipInputStream} provides stream based read access to a
 * <i>ZIP-archive</i>, this class implements more efficient (file based) access
 * and makes use of the <i>central directory</i> within a <i>ZIP-archive</i>.
 * <p>
 * Use {@code ZipOutputStream} if you want to create an archive.
 * <p>
 * A temporary ZIP file can be marked for automatic deletion upon closing it.
 * 
 * @see ZipEntry
 * @see ZipOutputStream
 */
public class ZipFile implements ZipConstants {

    String fileName;

    long descriptor = -1;

    private int size = -1;

    private int mode;
    static {
        System.loadLibrary("hyarchive"); //$NON-NLS-1$
        ntvinit();
    }

    /**
     * Open zip file for read.
     */
    public static final int OPEN_READ = 1;

    /**
     * Delete zip file when closed.
     */
    public static final int OPEN_DELETE = 4;

    /**
     * Constructs a new {@code ZipFile} with the specified file.
     * 
     * @param file
     *            the file to read from.
     * @throws ZipException
     *             if a ZIP error occurs.
     * @throws IOException
     *             if an {@code IOException} occurs.
     */
    public ZipFile(File file) throws ZipException, IOException {
        this(file.getPath());
    }

    /**
     * Opens a file as <i>ZIP-archive</i>. "mode" must be {@code OPEN_READ} or
     * {@code OPEN_DELETE} . The latter sets the "delete on exit" flag through a
     * file.
     * 
     * @param file
     *            the ZIP file to read.
     * @param mode
     *            the mode of the file open operation.
     * @throws IOException
     *             if an {@code IOException} occurs.
     */
    public ZipFile(File file, int mode) throws IOException {
        if (mode == OPEN_READ || mode == (OPEN_READ | OPEN_DELETE)) {
            fileName = file.getPath();
            SecurityManager security = System.getSecurityManager();
            if (security != null) {
                security.checkRead(fileName);
                if ((mode & OPEN_DELETE) != 0) {
                    security.checkDelete(fileName);
                }
            }
            this.mode = mode;
            openZip();
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Opens a ZIP archived file.
     * 
     * @param name
     *            the name of the ZIP file.
     * @throws IOException
     *             if an IOException occurs.
     */
    public ZipFile(String name) throws IOException {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkRead(name);
        }
        fileName = name;
        openZip();
    }

    private void openZip() throws IOException {
        int result = openZipImpl(Util.getUTF8Bytes(fileName));

        if (result != 0) {
            switch (result) {
            case 1:
                throw new ZipException(Messages.getString(
                        "archive.24", fileName)); //$NON-NLS-1$
            case 2:
                throw new ZipException(Messages.getString(
                        "archive.25", fileName)); //$NON-NLS-1$
            default:
                throw new OutOfMemoryError();
            }
        }
    }

    @Override
    protected void finalize() throws IOException {
        close();
    }

    /**
     * Closes this ZIP file.
     * 
     * @throws IOException
     *             if an IOException occurs.
     */
    public synchronized void close() throws IOException {
        if (descriptor != -1 && fileName != null) {
            // Only close initialized instances
            closeZipImpl(descriptor);
            if ((mode & OPEN_DELETE) != 0) {
                AccessController.doPrivileged(new PrivilegedAction<Object>() {
                    public Object run() {
                        new File(fileName).delete();
                        return null;
                    }
                });
            }
        }
    }

    /**
     * Returns an enumeration of the entries. The entries are listed in the
     * order in which they appear in the ZIP archive.
     * 
     * @return the enumeration of the entries.
     */
    public Enumeration<? extends ZipEntry> entries() {
        return new ZFEnum<ZipEntry>();
    }

    /**
     * Gets the ZIP entry with the specified name from this {@code ZipFile}.
     * 
     * @param entryName
     *            the name of the entry in the ZIP file.
     * @return a {@code ZipEntry} or {@code null} if the entry name does not
     *         exist in the ZIP file.
     */
    public ZipEntry getEntry(String entryName) {
        if (entryName != null) {
            ZipEntry entry = getEntryImpl(descriptor, entryName);
            return entry;
        }
        throw new NullPointerException();
    }

    /**
     * Returns an input stream on the data of the specified {@code ZipEntry}.
     * 
     * @param entry
     *            the ZipEntry.
     * @return an input stream of the data contained in the {@code ZipEntry}.
     * @throws IOException
     *             if an {@code IOException} occurs.
     */
    public InputStream getInputStream(ZipEntry entry) throws IOException {
        if (descriptor == -1) {
            /*
             * the descriptor is set to -1 by native code to indicate the zip
             * was closed
             */
            throw new IllegalStateException();
        }
        byte[] buf = inflateEntryImpl2(descriptor, entry.getName());
        if (buf == null) {
            return null;
        }
        return new ByteArrayInputStream(buf);
    }

    /**
     * Gets the file name of this {@code ZipFile}.
     * 
     * @return the file name of this {@code ZipFile}.
     */
    public String getName() {
        return fileName;
    }

    private synchronized native int openZipImpl(byte[] fileName1);

    private native void closeZipImpl(long descriptor1) throws IOException;

    private native ZipEntry getEntryImpl(long descriptor1, String entryName);

    private native byte[] inflateEntryImpl2(long descriptor1, String entryName)
            throws ZipException;

    /**
     * Returns the number of {@code ZipEntries} in this {@code ZipFile}.
     * 
     * @return the number of entries in this file.
     */
    public int size() {
        if (size != -1) {
            return size;
        }

        size = 0;
        Enumeration<?> e = entries();
        while (e.hasMoreElements()) {
            size++;
            e.nextElement();
        }
        return size;

    }

    private static native void ntvinit();

    class ZFEnum<T extends ZipEntry> implements Enumeration<T> {
        private final long nextEntryPointer;

        private T current;

        ZFEnum() {
            nextEntryPointer = resetZip(descriptor);
            current = getNextEntry(descriptor, nextEntryPointer);
        }

        private native long resetZip(long descriptor1);

        private native T getNextEntry(long descriptor1, long nextEntryPointer1);

        public boolean hasMoreElements() {
            if (descriptor == -1) {
                /*
                 * the descriptor is set to -1 by native code to indicate the
                 * zip was closed
                 */
                throw new IllegalStateException();
            }
            return current != null;
        }

        public T nextElement() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            T ze = current;
            current = getNextEntry(descriptor, nextEntryPointer);
            return ze;
        }
    }
}
