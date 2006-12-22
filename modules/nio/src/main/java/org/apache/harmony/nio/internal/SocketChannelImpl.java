/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.harmony.nio.internal;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketImpl;
import java.net.SocketOptions;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.AlreadyConnectedException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ConnectionPendingException;
import java.nio.channels.IllegalBlockingModeException;
import java.nio.channels.NoConnectionPendingException;
import java.nio.channels.NotYetConnectedException;
import java.nio.channels.SocketChannel;
import java.nio.channels.UnresolvedAddressException;
import java.nio.channels.UnsupportedAddressTypeException;
import java.nio.channels.spi.SelectorProvider;

import org.apache.harmony.luni.net.SocketImplProvider;
import org.apache.harmony.luni.platform.FileDescriptorHandler;
import org.apache.harmony.luni.platform.INetworkSystem;
import org.apache.harmony.luni.platform.Platform;
import org.apache.harmony.luni.util.ErrorCodeException;
import org.apache.harmony.nio.internal.nls.Messages;


/*
 * 
 * The default implementation class of java.nio.channels.SocketChannel.
 * 
 */
class SocketChannelImpl extends SocketChannel implements FileDescriptorHandler {

    // -------------------------------------------------------------------
    // Class variables
    // -------------------------------------------------------------------

    private static final int EOF = -1;

    private static final int ERRCODE_SOCKET_NONBLOCKING_WOULD_BLOCK = -211;

    // The singleton to do the native network operation.
    static final INetworkSystem networkSystem = Platform.getNetworkSystem();

    // status un-init, not initialized.
    static final int SOCKET_STATUS_UNINIT = EOF;

    // status before connect.
    static final int SOCKET_STATUS_UNCONNECTED = 0;

    // status connection pending
    static final int SOCKET_STATUS_PENDING = 1;

    // status after connection success
    static final int SOCKET_STATUS_CONNECTED = 2;

    // status closed.
    static final int SOCKET_STATUS_CLOSED = 3;

    // timeout used for non-block mode.
    private static final int TIMEOUT_NONBLOCK = 0;

    // timeout used for block mode.
    private static final int TIMEOUT_BLOCK = EOF;

    // step used for connect
    private static final int HY_SOCK_STEP_START = 0;

    // step used for finishConnect
    private static final int HY_PORT_SOCKET_STEP_CHECK = 1;

    // connect success
    private static final int CONNECT_SUCCESS = 0;

    // a address of localhost
    private static final byte[] localAddrArray = { 127, 0, 0, 1 };

    // -------------------------------------------------------------------
    // Instance Variables
    // -------------------------------------------------------------------

    // The fd to interact with native code
    FileDescriptor fd;

    // Our internal Socket.
    private Socket socket = null;

    // The address to be connected.
    InetSocketAddress connectAddress = null;

    // Local address of the this socket (package private for adapter)
    InetAddress localAddress = null;

    // local port
    int localPort;

    // At first, uninitialized.
    int status = SOCKET_STATUS_UNINIT;

    // whether the socket is bound
    boolean isBound = false;

    // lock for read and write
    private class ReadLock {}
    private final Object readLock = new ReadLock();

    private class WriteLock {}
    private final Object writeLock = new WriteLock();

    // this content is a point used to set in connect_withtimeout() in pending
    // mode
    private Long connectContext = Long.valueOf("0"); //$NON-NLS-1$

    // used to store the trafficClass value which is simply returned
    // as the value that was set. We also need it to pass it to methods
    // that specify an address packets are going to be sent to
    private int trafficClass = 0;

    // -------------------------------------------------------------------
    // Constructor
    // -------------------------------------------------------------------

    /*
     * Constructor
     */
    public SocketChannelImpl(SelectorProvider selectorProvider)
            throws IOException {
        super(selectorProvider);
        fd = new FileDescriptor();
        status = SOCKET_STATUS_UNCONNECTED;
        networkSystem.createSocket(fd, true);
    }

    /*
     * for native call
     */
    private SocketChannelImpl() throws IOException {
		super(SelectorProvider.provider());
		fd = new FileDescriptor();
		connectAddress = new InetSocketAddress(0);
		status = SOCKET_STATUS_CONNECTED;
	}
    
    // Keep this to see if need next version
    // SocketChannelImpl(SelectorProvider selectorProvider, FileDescriptor fd,
    // SocketImpl si) {
    // super(selectorProvider);
    // fd = fd;
    // networkSystem = OSNetworkSystem.getOSNetworkSystem();
    // status = SOCKET_STATUS_UNCONNECTED;
    // networkSystem.createSocket(fd, true);
    // }

    /*
     * Package private constructor.
     */
    SocketChannelImpl(Socket aSocket, FileDescriptor aFd) {
        super(SelectorProvider.provider());
        socket = aSocket;
        fd = aFd;
        status = SOCKET_STATUS_UNCONNECTED;
    }

    // -------------------------------------------------------------------
    // Methods for getting internal Socket.
    // -------------------------------------------------------------------

    /*
     * Getting the internal Socket If we have not the socket, we create a new
     * one.
     */
    synchronized public Socket socket() {
        if (null == socket) {
            try {
                InetAddress addr = null;
                int port = 0;
                if (connectAddress != null) {
                    addr = connectAddress.getAddress();
                    port = connectAddress.getPort();
                }
                socket = new SocketAdapter(SocketImplProvider.getSocketImpl(fd,
                        localPort, addr, port), this);
            } catch (SocketException e) {
                return null;
            }
        }
        return socket;
    }

    // -------------------------------------------------------------------
    // Methods for connect and finishConnect
    // -------------------------------------------------------------------

    /*
     * @see java.nio.channels.SocketChannel#isConnected()
     */
    synchronized public boolean isConnected() {
        return status == SOCKET_STATUS_CONNECTED;
    }

    /*
     * status setting used by other class.
     */
    synchronized void setConnected() {
        status = SOCKET_STATUS_CONNECTED;
    }

    /*
     * @see java.nio.channels.SocketChannel#isConnectionPending()
     */
    synchronized public boolean isConnectionPending() {
        return status == SOCKET_STATUS_PENDING;
    }

    /*
     * @see java.nio.channels.SocketChannel#connect(java.net.SocketAddress)
     */
    public boolean connect(SocketAddress socketAddress) throws IOException {
        // status must be open and unconnected
        checkUnconnected();

        // check the address
        InetSocketAddress inetSocketAddress = validateAddress(socketAddress);

        int port = inetSocketAddress.getPort();
        String hostName = inetSocketAddress.getAddress().getHostName();
        // security check
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkConnect(hostName, port);
        }

        // connect result
        int result = EOF;
        boolean finished = false;

        try {           
            if (!isBound) {
                // bind
                networkSystem.bind2(fd, 0, true, InetAddress
                        .getByAddress(new byte[] { 0, 0, 0, 0 }));
                isBound = true;
            }

            if (isBlocking()) {
                begin();
                result = networkSystem.connect(fd, trafficClass,
                        inetSocketAddress.getAddress(), inetSocketAddress
                                .getPort());

            } else {
                result = networkSystem.connectWithTimeout(fd, 0, trafficClass,
                        inetSocketAddress.getAddress(), inetSocketAddress
                                .getPort(), HY_SOCK_STEP_START, connectContext);
                // set back to nonblocking to work around with a bug in portlib
                if (!this.isBlocking()){
                    networkSystem.setNonBlocking(fd, true);
                }
            }
            finished = (CONNECT_SUCCESS == result);
            isBound = finished;
        } catch (IOException e) {
            if (e instanceof ConnectException && !isBlocking()) {
                status = SOCKET_STATUS_PENDING;
            } else {
                if (isOpen()){
                    close();
                    finished = true;
                }                
                throw e;                
            }
        } finally {
            if (isBlocking()) {
                end(finished);
            }
        }

        // set local port
        localPort = networkSystem.getSocketLocalPort(fd, false);
        localAddress = networkSystem.getSocketLocalAddress(fd, false);
        
        // set the connected address.
        connectAddress = inetSocketAddress;
        synchronized (this) {
            if (isBlocking()) {
                status = (finished ? SOCKET_STATUS_CONNECTED
                        : SOCKET_STATUS_UNCONNECTED);
            } else {
                status = SOCKET_STATUS_PENDING;
            }
        }
        return finished;
    }

    /*
     * @see java.nio.channels.SocketChannel#finishConnect()
     */
    public boolean finishConnect() throws IOException {
        // status check
        synchronized (this) {
            if (!isOpen()) {
                throw new ClosedChannelException();
            }
            if (status == SOCKET_STATUS_CONNECTED) {
                return true;
            }
            if (status != SOCKET_STATUS_PENDING) {
                throw new NoConnectionPendingException();
            }
        }

        // finish result
        int result = EOF;
        boolean finished = false;

        try {
            begin();
            if (isBlocking()) {
                result = networkSystem.connect(fd, trafficClass, connectAddress
                        .getAddress(), connectAddress.getPort());

            } else {
                result = networkSystem.connectWithTimeout(fd, 0, trafficClass,
                        connectAddress.getAddress(), connectAddress.getPort(),
                        HY_PORT_SOCKET_STEP_CHECK, connectContext);
            }
            finished = (result == CONNECT_SUCCESS);
            isBound = finished;
            localAddress = networkSystem.getSocketLocalAddress(fd, false);
        } catch (ConnectException e) {
            if (isOpen()){
                close();
                finished = true;
            }     
            throw e;
        } finally {
            end(finished);
        }

        synchronized (this) {
            status = (finished ? SOCKET_STATUS_CONNECTED : status);
            isBound = finished;
        }
        return finished;
    }

    // -------------------------------------------------------------------
    // Methods for read and write
    // -------------------------------------------------------------------
    /*
     * @see java.nio.channels.SocketChannel#read(java.nio.ByteBuffer)
     */
    public int read(ByteBuffer target) throws IOException {
        if (null == target) {
            throw new NullPointerException();
        }
        checkOpenConnected();
        if (!target.hasRemaining()) {
            return 0;
        }
        byte[] readArray = new byte[target.remaining()];
        int readCount;
        synchronized (readLock) {
            readCount = readImpl(readArray);
        }
        if (EOF != readCount) {
            target.put(readArray, 0, readCount);
        }
        return readCount;
    }

    /*
     * @see java.nio.channels.SocketChannel#read(java.nio.ByteBuffer[], int,
     *      int)
     */
    public long read(ByteBuffer[] targets, int offset, int length)
            throws IOException {
        if (!isIndexValid(targets, offset, length)) {
            throw new IndexOutOfBoundsException();
        }
        checkOpenConnected();

        int totalCount = calculateByteBufferArray(targets, offset, length);
        if (0 == totalCount) {
            return 0;
        }
        byte[] readBuffer = new byte[totalCount];
        int readCount;
        // read data to readBuffer, and then transfer data from readBuffer to
        // targets.
        synchronized (readLock) {
            readCount = readImpl(readBuffer);
        }
        if (readCount > 0) {
            int left = readCount;
            int index = offset;
            // transfer data from readBuffer to targets
            while (left > 0) {
                int putLength = Math.min(targets[index].remaining(), left);
                targets[index].put(readBuffer, readCount - left, putLength);
                index++;
                left -= putLength;
            }
        }
        return readCount;
    }

    private boolean isIndexValid(ByteBuffer[] targets, int offset, int length) {
        return (length >= 0) && (offset >= 0)
                && (length + offset <= targets.length);
    }

    /*
     * read from channel, and store the result in the target.
     *
     * @param target    output parameter
     */
    private int readImpl(byte[] target) throws IOException {
        int readCount = 0;
        try {
            if (isBlocking()){
                begin();
            }
            readCount = networkSystem.read(fd, target, 0, target.length,
                    (isBlocking() ? TIMEOUT_BLOCK : TIMEOUT_NONBLOCK));
            return readCount;
        } finally {
            if (isBlocking()){
                end(readCount > 0);
            }
        }
    }

    /*
     * 
     * @see java.nio.channels.SocketChannel#write(java.nio.ByteBuffer)
     */
    public int write(ByteBuffer source) throws IOException {
        if (null == source) {
            throw new NullPointerException();
        }
        checkOpenConnected();
        if (!source.hasRemaining()) {
            return 0;
        }
        synchronized (writeLock) {
            return writeImpl(source);
        }
    }

    /*
     * @see java.nio.channels.SocketChannel#write(java.nio.ByteBuffer[], int,
     *      int)
     */
    public long write(ByteBuffer[] sources, int offset, int length)
            throws IOException {
        if (!isIndexValid(sources, offset, length)) {
            throw new IndexOutOfBoundsException();
        }
        checkOpenConnected();
        if (0 == calculateByteBufferArray(sources, offset, length)) {
            return 0;
        }
        synchronized (writeLock) {
            long writeCount = 0;
            for (int val = offset; val < offset + length; val++) {
                writeCount = writeCount + writeImpl(sources[val]);
            }
            return writeCount;
        }
    }

    private int calculateByteBufferArray(ByteBuffer[] sources, int offset, int length){
        int sum = 0;
        for (int val = offset; val < offset + length; val++) {
            sum = sum + sources[val].remaining();
        }
        return sum;
    }
    /*
     * write the source. return the count of bytes written.
     */
    private int writeImpl(ByteBuffer source) throws IOException {
        if (!source.hasRemaining()) {
            return 0;
        }
        int writeCount = 0;
        try {
            int pos = source.position();
            int length = source.remaining();
            if (isBlocking()){
                begin();
            }
            if (source.hasArray()) {
                writeCount = networkSystem.write(fd, source.array(), pos,
                        length);
            } else {
                byte[] array = new byte[length];
                source.get(array);
                writeCount = networkSystem.write(fd, array, 0, length);
            }
            source.position(pos + writeCount);
        } catch (SocketException e) {
            if (e.getCause() instanceof ErrorCodeException) {
                if (ERRCODE_SOCKET_NONBLOCKING_WOULD_BLOCK == ((ErrorCodeException) e
                        .getCause()).getErrorCode()) {
                    return writeCount;
                }
            }
            throw e;
        } finally {
            if (isBlocking()){
                end(writeCount >= 0);
            }
        }
        return writeCount;
    }

    // -------------------------------------------------------------------
    // Shared methods
    // -------------------------------------------------------------------

    /*
     * status check, open and "connected", when read and write.
     */
    synchronized private void checkOpenConnected()
            throws ClosedChannelException {
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
        if (!isConnected()) {
            throw new NotYetConnectedException();
        }
    }

    /*
     * status check, open and "unconnected", before connection.
     */
    synchronized private void checkUnconnected() throws IOException {
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
        if (status == SOCKET_STATUS_CONNECTED) {
            throw new AlreadyConnectedException();
        }
        if (status == SOCKET_STATUS_PENDING) {
            throw new ConnectionPendingException();
        }
    }

    /*
     * shared by this class and DatagramChannelImpl, to do the address transfer
     * and check.
     */
    static InetSocketAddress validateAddress(SocketAddress socketAddress) {
        if (null == socketAddress) {
            throw new IllegalArgumentException();
        }
        if (!(socketAddress instanceof InetSocketAddress)) {
            throw new UnsupportedAddressTypeException();
        }
        InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
        if (inetSocketAddress.isUnresolved()) {
            throw new UnresolvedAddressException();
        }
        return inetSocketAddress;
    }

    /*
     * get local address
     */
    public InetAddress getLocalAddress() throws UnknownHostException {
        byte[] any_bytes = { 0, 0, 0, 0 };
        if (!isBound) {
            return InetAddress.getByAddress(any_bytes);
        }
        return localAddress;
    }

    // -------------------------------------------------------------------
    // Protected inherited methods
    // -------------------------------------------------------------------
    /*
     * do really closing action here
     */
    synchronized protected void implCloseSelectableChannel() throws IOException {
        if (SOCKET_STATUS_CLOSED != status) {
            status = SOCKET_STATUS_CLOSED;
            if (null != socket && !socket.isClosed()) {
                socket.close();
            } else {
                networkSystem.socketClose(fd);
            }
        }
    }

    /*
     * @see java.nio.channels.spi.AbstractSelectableChannel#implConfigureBlocking(boolean)
     */
    protected void implConfigureBlocking(boolean blockMode) throws IOException {
        synchronized (blockingLock()) {
            networkSystem.setNonBlocking(fd, !blockMode);
        }
    }

    /*
     * get the fd
     */
    public FileDescriptor getFD() {
        return fd;
    }

    // -------------------------------------------------------------------
    // Adapter classes for internal socket.
    // -------------------------------------------------------------------
    
    private static class SocketAdapter extends Socket {

        // ----------------------------------------------------
        // Class Variables
        // ----------------------------------------------------

        SocketChannelImpl channel;

        SocketImpl socketImpl;

        // ----------------------------------------------------
        // Methods
        // ----------------------------------------------------

        SocketAdapter(SocketImpl socketimpl, SocketChannelImpl channel)
                throws SocketException {
            super(socketimpl);
            socketImpl = socketimpl;
            this.channel = channel;
        }

        /*
         * 
         * @see java.net.Socket#getChannel()
         */
        public SocketChannel getChannel() {
            return channel;
        }

        /*
         * 
         * @see java.net.Socket#isBound()
         */
        public boolean isBound() {
            return channel.isBound;
        }

        /*
         * 
         * @see java.net.Socket#isConnected()
         */
        public boolean isConnected() {
            return channel.isConnected();
        }

        /*
         * 
         * @see java.net.Socket#getLocalAddress()
         */
        public InetAddress getLocalAddress() {
            try {
                return channel.getLocalAddress();
            } catch (UnknownHostException e) {
                return null;
            }
        }

        /*
         * 
         * @see java.net.Socket#connect(java.net.SocketAddress, int)
         */
        public void connect(SocketAddress remoteAddr, int timeout)
                throws IOException {
            if (!channel.isBlocking()) {
                throw new IllegalBlockingModeException();
            }
            if (isConnected()) {
                throw new AlreadyConnectedException();
            }
            super.connect(remoteAddr, timeout);
            channel.localAddress = networkSystem.getSocketLocalAddress(
                    channel.fd, false);
            if (super.isConnected()) {
                channel.setConnected();
                channel.isBound = super.isBound();
            }
        }

        /*
         * 
         * @see java.net.Socket#bind(java.net.SocketAddress)
         */
        public void bind(SocketAddress localAddr) throws IOException {
            if (channel.isConnected()) {
                throw new AlreadyConnectedException();
            }
            if (SocketChannelImpl.SOCKET_STATUS_PENDING == channel.status) {
                throw new ConnectionPendingException();
            }
            super.bind(localAddr);
            // keep here to see if need next version
            // channel.Address = getLocalSocketAddress();
            // channel.localport = getLocalPort();
            channel.isBound = true;

        }

        /*
         * 
         * @see java.net.Socket#close()
         */
        public void close() throws IOException {
            synchronized (channel) {
                if (channel.isOpen()) {
                    channel.close();
                } else {
                    super.close();
                }
                channel.status = SocketChannelImpl.SOCKET_STATUS_CLOSED;
            }
        }

        
        public boolean getReuseAddress() throws SocketException {
            checkOpen();
            return ((Boolean) socketImpl.getOption(SocketOptions.SO_REUSEADDR))
                    .booleanValue();
        }
        
        public synchronized int getReceiveBufferSize() throws SocketException {
            checkOpen();
            return ((Integer) socketImpl.getOption(SocketOptions.SO_RCVBUF)).intValue();
        }
        
        public synchronized int getSendBufferSize() throws SocketException {
            checkOpen();
            return ((Integer) socketImpl.getOption(SocketOptions.SO_SNDBUF)).intValue();
        }
        
        public synchronized int getSoTimeout() throws SocketException {
            checkOpen();
            return ((Integer) socketImpl.getOption(SocketOptions.SO_TIMEOUT)).intValue();
        }
        
        public int getTrafficClass() throws SocketException {
            checkOpen();
            return ((Number) socketImpl.getOption(SocketOptions.IP_TOS)).intValue();
        }
        /*
         * 
         * @see java.net.Socket#getKeepAlive()
         */
        public boolean getKeepAlive() throws SocketException {
            checkOpen();
            return ((Boolean) socketImpl.getOption(SocketOptions.SO_KEEPALIVE))
                    .booleanValue();
        }

        /*
         * 
         * @see java.net.Socket#getOOBInline()
         */
        public boolean getOOBInline() throws SocketException {
            checkOpen();
            return ((Boolean) socketImpl.getOption(SocketOptions.SO_OOBINLINE))
                    .booleanValue();
        }

        /*
         * 
         * @see java.net.Socket#getSoLinger()
         */
        public int getSoLinger() throws SocketException {
            checkOpen();
            return ((Integer) socketImpl.getOption(SocketOptions.SO_LINGER))
                    .intValue();
        }

        /*
         * @see java.net.Socket#getTcpNoDelay()
         */
        public boolean getTcpNoDelay() throws SocketException {
            checkOpen();
            return ((Boolean) socketImpl.getOption(SocketOptions.TCP_NODELAY))
                    .booleanValue();
        }

        /* 
         * @see java.net.Socket#getOutputStream()
         */
        public OutputStream getOutputStream() throws IOException {
            if (!channel.isOpen()) {
                // nio.00=Socket is closed
                throw new SocketException(Messages.getString("nio.00")); //$NON-NLS-1$
            }
            if (!channel.isConnected()) {
                // nio.01=Socket is not connected
                throw new SocketException(Messages.getString("nio.01")); //$NON-NLS-1$
            }             
            if (isOutputShutdown()) {
                // nio.02=Socket output is shutdown
                throw new SocketException(Messages.getString("nio.02")); //$NON-NLS-1$
            }
            return new SocketChannelOutputStream(channel);
        }

        /*
         * 
         * @see java.net.Socket#getInputStream()
         */
        public InputStream getInputStream() throws IOException {
            if (!channel.isOpen()) {
                // nio.00=Socket is closed
                throw new SocketException(Messages.getString("nio.00")); //$NON-NLS-1$
            }
            if (!channel.isConnected()) {
                // nio.01=Socket is not connected
                throw new SocketException(Messages.getString("nio.01")); //$NON-NLS-1$
            }             
            if (isInputShutdown()) {
                // nio.03=Socket input is shutdown
                throw new SocketException(Messages.getString("nio.03")); //$NON-NLS-1$
            }
            return new SocketChannelInputStream(channel);
        }

        /*
         * Checks whether the channel is open
         */
        private void checkOpen() throws SocketException {
            if (isClosed()) {
                // nio.00=Socket is closed
                throw new SocketException(Messages.getString("nio.00")); //$NON-NLS-1$
            }
        }

        /*
         * used for net and nio exchange
         */
        public SocketImpl getImpl() {
            return socketImpl;
        }
    }

    
    /*
     * This output stream delegates all operations to the associated channel.
     * Throws an IllegalBlockingModeException if the channel is in non-blocking
     * mode when performing write operations.
     */
    private static class SocketChannelOutputStream extends OutputStream {
        SocketChannel channel;

        public SocketChannelOutputStream(SocketChannel channel) {
            this.channel = channel;
        }

        /*
         * Closes this stream and channel
         * 
         * @exception IOException
         *                thrown if an error occurs during the close
         */
        public void close() throws IOException {
            channel.close();
        }
        
        /* 
         * @see java.io.OutputStream#write(byte[], int, int)
         */
        public void write(byte[] buffer, int offset, int count)
                throws IOException {
            if (0 > offset || 0 > count || count + offset > buffer.length) {
                throw new IndexOutOfBoundsException();
            }
            ByteBuffer buf = ByteBuffer.wrap(buffer, offset, count);
            if (!channel.isBlocking()) {
                throw new IllegalBlockingModeException();
            }            
            channel.write(buf);
        }

        /* 
         * @see java.io.OutputStream#write(int)
         */
        public void write(int oneByte) throws IOException {
            if (!channel.isBlocking()) {
                throw new IllegalBlockingModeException();
            }
            ByteBuffer buffer = ByteBuffer.allocate(1);
            buffer.put((byte)(oneByte & 0xFF));
            channel.write(buffer);
        }
    }

    /*
     * This input stream delegates all operations to the associated channel.
     * Throws an IllegalBlockingModeException if the channel is in non-blocking
     * mode when performing read operations.
     */
    private static class SocketChannelInputStream extends InputStream {
        SocketChannel channel;

        public SocketChannelInputStream(SocketChannel channel) {
            this.channel = channel;
        }
        
        /*
         * Closes this stream and channel.
         */
        public void close() throws IOException {
            channel.close();
        }

        /* 
         * @see java.io.InputStream#read()
         */
        public int read() throws IOException {
            if (!channel.isBlocking()) {
                throw new IllegalBlockingModeException();
            }
            ByteBuffer buf = ByteBuffer.allocate(1);
            int result = channel.read(buf);
            return (-1 == result) ? result : buf.get() & 0xFF;
        }

        /* 
         * @see java.io.InputStream#read(byte[], int, int)
         */
        public int read(byte[] buffer, int offset, int count)
                throws IOException {
            if (0 > offset || 0 > count || count + offset > buffer.length) {
                throw new IndexOutOfBoundsException();
            }
            if (!channel.isBlocking()) {
                throw new IllegalBlockingModeException();
            }
            ByteBuffer buf = ByteBuffer.wrap(buffer, offset, count);
            return channel.read(buf);
        }
    }
}
