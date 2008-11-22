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

package org.apache.harmony.luni.platform;

import java.io.FileDescriptor;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketImpl;
import java.net.UnknownHostException;
import java.nio.channels.Channel;

/**
 * This wraps native code that implements the INetworkSystem interface.
 */
final class OSNetworkSystem implements INetworkSystem {

    private static final int ERRORCODE_SOCKET_TIMEOUT = -209;

    private static final int INETADDR_REACHABLE = 0;

    private static boolean isNetworkInited = false;

    private static OSNetworkSystem ref = new OSNetworkSystem();

    // Can not be instantiated.
    private OSNetworkSystem() {
        super();
    }

    /**
     * Answers the unique instance of the OSNetworkSystem.
     * 
     * @return the network system interface instance
     */
    public static OSNetworkSystem getOSNetworkSystem() {
        return ref;
    }

    public native void createSocket(FileDescriptor fd, boolean preferIPv4Stack)
            throws IOException;

    public native void createDatagramSocket(FileDescriptor fd,
            boolean preferIPv4Stack) throws SocketException;

    public native int read(FileDescriptor fd, byte[] data, int offset,
            int count, int timeout) throws IOException;

    public native int readDirect(FileDescriptor fd, long address, int count,
            int timeout) throws IOException;

    public native int write(FileDescriptor fd, byte[] data, int offset,
            int count) throws IOException;

    public native int writeDirect(FileDescriptor fd, long address, int count)
            throws IOException;

    public native void setNonBlocking(FileDescriptor fd, boolean block)
            throws IOException;

    public native void connectDatagram(FileDescriptor fd, int port,
            int trafficClass, InetAddress inetAddress) throws SocketException;

    public native int connect(FileDescriptor fd, int trafficClass,
            InetAddress inetAddress, int port) throws IOException;

    public native int connectWithTimeout(FileDescriptor fd, int timeout,
            int trafficClass, InetAddress inetAddress, int port, int step,
            Long context) throws IOException;

    public native void connectStreamWithTimeoutSocket(FileDescriptor aFD, int aport,
            int timeout, int trafficClass, InetAddress inetAddress)
            throws IOException;

    public native void bind(FileDescriptor aFD, int port,
            InetAddress inetAddress) throws SocketException;

    /**
     * Bind the socket to the port/localhost in the IP stack.
     * 
     * @param fd
     *            the socket descriptor
     * @param port
     *            the option selector
     * @param bindToDevice
     *            bind the socket to the specified interface
     * @param inetAddress
     *            address to connect to.
     * @return if bind successful
     * @exception SocketException
     *                thrown if bind operation fails
     */
    public native boolean bind2(FileDescriptor fd, int port, boolean bindToDevice,
            InetAddress inetAddress) throws SocketException;

    public native void accept(FileDescriptor fdServer, SocketImpl newSocket,
            FileDescriptor fdnewSocket, int timeout) throws IOException;

    /**
     * Send the <code>data</code> to the nominated target <code>address</code>
     * and <code>port</code>. These values are derived from the DatagramPacket
     * to reduce the field calls within JNI.
     * 
     * @param fd
     *            the socket FileDescriptor
     * @param data
     *            the data buffer of the packet
     * @param offset
     *            the offset in the data buffer
     * @param length
     *            the length of the data buffer in the packet
     * @param port
     *            the target host port
     * @param bindToDevice
     *            if bind to device
     * @param trafficClass
     *            the traffic class to be used when the datagram is sent
     * @param inetAddress
     *            address to connect to.
     * @return number of data send
     * 
     * @exception IOException
     *                upon an read error or timeout
     */
    public native int sendDatagram(FileDescriptor fd, byte[] data, int offset,
            int length, int port, boolean bindToDevice, int trafficClass,
            InetAddress inetAddress) throws IOException;

    public native int sendDatagramDirect(FileDescriptor fd, long address,
            int offset, int length, int port, boolean bindToDevice,
            int trafficClass, InetAddress inetAddress) throws IOException;

    public native int sendDatagram2(FileDescriptor fd, byte[] data, int offset,
            int length, int port, InetAddress inetAddress) throws IOException;

    /**
     * Receive data on the socket into the specified buffer. The packet fields
     * <code>data</code> & <code>length</code> are passed in addition to
     * <code>packet</code> to eliminate the JNI field access calls.
     * 
     * @param fd
     *            the socket FileDescriptor
     * @param packet
     *            the DatagramPacket to receive into
     * @param data
     *            the data buffer of the packet
     * @param offset
     *            the offset in the data buffer
     * @param length
     *            the length of the data buffer in the packet
     * @param receiveTimeout
     *            the maximum length of time the socket should block, reading
     * @param peek
     *            indicates to peek at the data
     * @return number of data received
     * @exception IOException
     *                upon an read error or timeout
     */
    public native int receiveDatagram(FileDescriptor fd, DatagramPacket packet,
            byte[] data, int offset, int length, int receiveTimeout,
            boolean peek) throws IOException;

    public native int receiveDatagramDirect(FileDescriptor fd, DatagramPacket packet,
            long address, int offset, int length, int receiveTimeout,
            boolean peek) throws IOException;

    public int recvConnectedDatagram(FileDescriptor aFD, DatagramPacket packet,
            byte[] data, int offset, int length, int receiveTimeout,
            boolean peek) throws IOException {
        return recvConnectedDatagramImpl(aFD, packet, data, offset, length,
                receiveTimeout, peek);
    }

    public int recvConnectedDatagramDirect(FileDescriptor aFD,
            DatagramPacket packet, long address, int offset, int length,
            int receiveTimeout, boolean peek) throws IOException {
        return recvConnectedDatagramDirectImpl(aFD, packet, address, offset,
                length, receiveTimeout, peek);
    }

    /**
     * Peek on the socket, update <code>sender</code> address and answer the
     * sender port.
     * 
     * @param fd
     *            the socket FileDescriptor
     * @param sender
     *            an InetAddress, to be updated with the sender's address
     * @param receiveTimeout
     *            the maximum length of time the socket should block, reading
     * @return the sender port
     * 
     * @exception IOException
     *                upon an read error or timeout
     */
    public native int peekDatagram(FileDescriptor fd, InetAddress sender,
            int receiveTimeout) throws IOException;

    public int sendConnectedDatagram(FileDescriptor fd, byte[] data,
            int offset, int length, boolean bindToDevice) throws IOException {
        return sendConnectedDatagramImpl(fd, data, offset, length, bindToDevice);
    }

    public int sendConnectedDatagramDirect(FileDescriptor fd, long address,
            int offset, int length, boolean bindToDevice) throws IOException {
        return sendConnectedDatagramDirectImpl(fd, address, offset, length,
                bindToDevice);
    }

    /**
     * Disconnect the socket to a port and address
     * 
     * @param fd
     *            the FileDescriptor associated with the socket
     * 
     * @exception SocketException
     *                if the disconnect fails
     */
    public native void disconnectDatagram(FileDescriptor fd) throws SocketException;

    /**
     * Answer the result of attempting to create a multicast socket in the IP
     * stack. Any special options required for server sockets will be set by
     * this method.
     * 
     * @param aFD
     *            the socket FileDescriptor
     *            @param preferIPv4Stack if use IPV4
     * @exception SocketException
     *                if an error occurs while creating the socket
     */
    public native void createMulticastSocket(FileDescriptor aFD,
            boolean preferIPv4Stack) throws SocketException;

    /**
     * Answer the result of attempting to create a server stream socket in the
     * IP stack. Any special options required for server sockets will be set by
     * this method.
     * 
     * @param aFD
     *            the socket FileDescriptor @param preferIPv4Stack if use IPV4
     * @exception SocketException
     *                if an error occurs while creating the socket
     */
    public native void createServerStreamSocket(FileDescriptor aFD,
            boolean preferIPv4Stack) throws SocketException;

    /**
     * Receive at most <code>count</code> bytes into the buffer
     * <code>data</code> at the <code>offset</code> on the socket.
     * 
     * @param aFD
     *            the socket FileDescriptor
     * @param data
     *            the receive buffer
     * @param offset
     *            the offset into the buffer
     * @param count
     *            the max number of bytes to receive
     * @param timeout
     *            the max time the read operation should block waiting for data
     * @return int the actual number of bytes read
     * @throws IOException
     * @throws SocketException
     *             if an error occurs while reading
     */
    public native int receiveStream(FileDescriptor aFD, byte[] data, int offset,
            int count, int timeout) throws IOException;

    public int sendStream(FileDescriptor fd, byte[] data, int offset, int count)
            throws IOException {
        return sendStreamImpl(fd, data, offset, count);
    }

    public void shutdownInput(FileDescriptor descriptor) throws IOException {
        shutdownInputImpl(descriptor);
    }

    public void shutdownOutput(FileDescriptor descriptor) throws IOException {
        shutdownOutputImpl(descriptor);
    }

    public native boolean supportsUrgentData(FileDescriptor fd);

    public void sendUrgentData(FileDescriptor fd, byte value) {
        sendUrgentDataImpl(fd, value);
    }

    public native int availableStream(FileDescriptor fd) throws SocketException;

    public void acceptStreamSocket(FileDescriptor fdServer,
            SocketImpl newSocket, FileDescriptor fdnewSocket, int timeout)
            throws IOException {
        acceptStreamSocketImpl(fdServer, newSocket, fdnewSocket, timeout);
    }

    public native void createStreamSocket(FileDescriptor fd, boolean preferIPv4Stack)
            throws SocketException;

    public native void listenStreamSocket(FileDescriptor aFD, int backlog)
            throws SocketException;

    public boolean isReachableByICMP(final InetAddress dest,
            InetAddress source, final int ttl, final int timeout) {
        return INETADDR_REACHABLE == isReachableByICMPImpl(dest, source, ttl,
                timeout);
    }

    /**
     * Select the given file descriptors for read and write operations.
     * 
     * The file descriptors passed in as readFDs will be selected for read-ready
     * operations, and those in the writeFDs will be selected for write-ready
     * operations. A file descriptor can appear in either or both array, and
     * must not be <code>null</code>. If the file descriptor is closed during
     * the select the behavior depends upon the underlying OS.
     * 
     * Upon return the result is a single array of length
     * <code>readFDs.length</code> + <code>writeFDs.length</code> laid out as
     * the result of the select operation on the corresponding file descriptors.
     * 
     * @param readFDs
     *            all sockets interested in read and accept
     * @param writeFDs
     *            all sockets interested in write and connect
     * @param timeout
     *            timeout in milliseconds
     * @returns each element describes the corresponding state of the descriptor
     *          in the read and write arrays.
     * @throws SocketException
     */
    public int[] select(FileDescriptor[] readFDs, FileDescriptor[] writeFDs,
            long timeout) throws SocketException {
        int countRead = readFDs.length;
        int countWrite = writeFDs.length;
        int result = 0;
        if (0 == countRead + countWrite) {
            return (new int[0]);
        }
        int[] flags = new int[countRead + countWrite];

        assert validateFDs(readFDs, writeFDs) : "Invalid file descriptor arrays"; //$NON-NLS-1$

        // handle timeout in native
        result = selectImpl(readFDs, writeFDs, countRead, countWrite, flags,
                timeout);

        if (0 <= result) {
            return flags;
        }
        if (ERRORCODE_SOCKET_TIMEOUT == result) {
            return new int[0];
        }
        throw new SocketException();
    }

    /*
     * Used to check if the file descriptor arrays are valid before passing them
     * into the select native call.
     */
    private boolean validateFDs(FileDescriptor[] readFDs,
            FileDescriptor[] writeFDs) {
        for (FileDescriptor fd : readFDs) {
            // Also checks fd not null
            if (!fd.valid()) {
                return false;
            }
        }
        for (FileDescriptor fd : writeFDs) {
            if (!fd.valid()) {
                return false;
            }
        }
        return true;
    }

    public native InetAddress getSocketLocalAddress(FileDescriptor fd,
            boolean preferIPv6Addresses);

    /**
     * Query the IP stack for the local port to which this socket is bound.
     * 
     * @param aFD
     *            the socket descriptor
     * @param preferIPv6Addresses
     *            address preference for nodes that support both IPv4 and IPv6
     * @return the local port to which the socket is bound
     */
    public native int getSocketLocalPort(FileDescriptor aFD,
            boolean preferIPv6Addresses);

    /**
     * Query the IP stack for the nominated socket option.
     * 
     * @param fd
     *            the socket descriptor
     * @param opt
     *            the socket option type
     * @return the nominated socket option value
     * @throws SocketException
     *             if the option is invalid
     */
    public native Object getSocketOption(FileDescriptor fd, int opt)
            throws SocketException;

    /**
     * Set the nominated socket option in the IP stack.
     * 
     * @param aFD
     *            the socket descriptor @param opt the option selector @param
     *            optVal the nominated option value
     * 
     * @throws SocketException
     *             if the option is invalid or cannot be set
     */
    public native void setSocketOption(FileDescriptor aFD, int opt, Object optVal)
            throws SocketException;

    public native int getSocketFlags();

    /**
     * Close the socket in the IP stack.
     * 
     * @param aFD
     *            the socket descriptor
     */
    public native void socketClose(FileDescriptor aFD) throws IOException;

    public native InetAddress getHostByAddr(byte[] addr) throws UnknownHostException;

    public native InetAddress getHostByName(String addr, boolean preferIPv6Addresses)
            throws UnknownHostException;

    public native void setInetAddress(InetAddress sender, byte[] address);

    static native void sendUrgentDataImpl(FileDescriptor fd, byte value);

    /**
     * Connect the socket to a port and address
     * 
     * @param aFD
     *            the FileDescriptor to associate with the socket @param port
     *            the port to connect to
     * @param trafficClass
     *            the traffic Class to be used then the connection is made
     * @param inetAddress
     *            address to connect to.
     * 
     * @exception SocketException
     *                if the connect fails
     */
    static native void connectDatagramImpl2(FileDescriptor aFD, int port,
            int trafficClass, InetAddress inetAddress) throws SocketException;

    /**
     * Allocate a datagram socket in the IP stack. The socket is associated with
     * the <code>aFD</code>.
     * 
     * @param aFD
     *            the FileDescriptor to associate with the socket @param
     *            preferIPv4Stack IP stack preference if underlying platform is
     *            V4/V6
     * @exception SocketException
     *                upon an allocation error
     */

    /**
     * Recieve data on the connected socket into the specified buffer. The
     * packet fields <code>data</code> & <code>length</code> are passed in
     * addition to <code>packet</code> to eliminate the JNI field access calls.
     * 
     * @param aFD
     *            the socket FileDescriptor @param packet the DatagramPacket to
     *            receive into @param data the data buffer of the packet @param
     *            offset the offset in the data buffer @param length the length
     *            of the data buffer in the packet @param receiveTimeout the
     *            maximum length of time the socket should block, reading @param
     *            peek indicates to peek at the data @return number of data
     *            received @exception IOException upon an read error or timeout
     */
    static native int recvConnectedDatagramImpl(FileDescriptor aFD,
            DatagramPacket packet, byte[] data, int offset, int length,
            int receiveTimeout, boolean peek) throws IOException;

    static native int recvConnectedDatagramDirectImpl(FileDescriptor aFD,
            DatagramPacket packet, long address, int offset, int length,
            int receiveTimeout, boolean peek) throws IOException;

    /**
     * Send the <code>data</code> to the address and port to which the was
     * connnected and <code>port</code>.
     * 
     * @param fd
     *            the socket FileDescriptor @param data the data buffer of the
     *            packet @param offset the offset in the data buffer @param
     *            length the length of the data buffer in the packet @param
     *            bindToDevice not used, current kept in case needed as was the
     *            case for sendDatagramImpl @return number of data send @exception
     *            IOException upon an read error or timeout
     */
    static native int sendConnectedDatagramImpl(FileDescriptor fd, byte[] data,
            int offset, int length, boolean bindToDevice) throws IOException;

    static native int sendConnectedDatagramDirectImpl(FileDescriptor fd,
            long address, int offset, int length, boolean bindToDevice)
            throws IOException;

    /**
     * Send <code>count</code> bytes from the buffer <code>data</code> at the
     * <code>offset</code>, on the socket.
     * 
     * @param fd
     * 
     * @param data
     *            the send buffer @param offset the offset into the buffer
     * @param count
     *            the number of bytes to receive @return int the actual number
     *            of bytes sent @throws IOException @exception SocketException
     *            if an error occurs while writing
     */
    static native int sendStreamImpl(FileDescriptor fd, byte[] data,
            int offset, int count) throws IOException;

    private native void shutdownInputImpl(FileDescriptor descriptor)
            throws IOException;

    private native void shutdownOutputImpl(FileDescriptor descriptor)
            throws IOException;

    static native void acceptStreamSocketImpl(FileDescriptor fdServer,
            SocketImpl newSocket, FileDescriptor fdnewSocket, int timeout)
            throws IOException;

    static native int selectImpl(FileDescriptor[] readfd,
            FileDescriptor[] writefd, int cread, int cwirte, int[] flags,
            long timeout);

    native int isReachableByICMPImpl(InetAddress addr, InetAddress local,
            int ttl, int timeout);

    public native Channel inheritedChannel();

    public void oneTimeInitialization(boolean jcl_supports_ipv6) {
        if (!isNetworkInited) {
            oneTimeInitializationImpl(jcl_supports_ipv6);
            isNetworkInited = true;
        }
    }

    native void oneTimeInitializationImpl(boolean jcl_supports_ipv6);
}
