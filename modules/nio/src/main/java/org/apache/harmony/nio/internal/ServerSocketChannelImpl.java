/* Copyright 2005, 2006 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
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
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketImpl;
import java.net.SocketTimeoutException;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.NotYetBoundException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;

import org.apache.harmony.luni.net.NetUtil;
import org.apache.harmony.luni.net.SocketImplProvider;
import org.apache.harmony.luni.platform.FileDescriptorHandler;
import org.apache.harmony.luni.platform.Platform;
import org.apache.harmony.luni.util.Msg;

/*
 * The default implementation class of java.nio.channels.ServerSocketChannel.
 */
public class ServerSocketChannelImpl extends ServerSocketChannel implements
        FileDescriptorHandler {

    // ----------------------------------------------------
    // Class Variables
    // ----------------------------------------------------

    // status un-init, not initialized.
    private static final int SERVER_STATUS_UNINIT = -1;

    // status after open and before closed.
    private static final int SERVER_STATUS_OPEN = 0;

    // status closed.
    private static final int SERVER_STATUS_CLOSED = 1;

    // default timeout used to nonblocking mode.
    private static final int DEFAULT_TIMEOUT = 100;

    // error message, for native dependent.
    private static final String ERRMSG_ASYNCHRONOUS = "The call was cancelled"; //$NON-NLS-1$

    // -------------------------------------------------------------------
    // Instance variables
    // -------------------------------------------------------------------

    // The fd to interact with native code
    private final FileDescriptor fd;

    // The interanl ServerSocket
    private final ServerSocket socket;

    private final SocketImpl impl;

    int status = SERVER_STATUS_UNINIT;

    // whether the socket is bound
    boolean isBound = false;

    // lock for accept
    private final Object acceptLock = new Object();

    // lock for status
    // final Object statusLock = new Object();

    // ----------------------------------------------------
    // Constructor
    // ----------------------------------------------------

    /*
     * Constructor
     */
    public ServerSocketChannelImpl(SelectorProvider sp) throws IOException {
        super(sp);
        status = SERVER_STATUS_OPEN;
        fd = new FileDescriptor();
        Platform.getNetworkSystem().createServerStreamSocket(fd,
                NetUtil.preferIPv4Stack());
        impl = SocketImplProvider.getServerSocketImpl(fd);
        socket = new ServerSocketAdapter(impl, this);
    }

    // ----------------------------------------------------
    // Methods
    // ----------------------------------------------------

    /*
     * Getting the internal Socket If we have not the socket, we create a new
     * one.
     */
    public ServerSocket socket() {
        return socket;
    }

    /*
     * 
     * @see java.nio.channels.ServerSocketChannel#accept()
     */
    public SocketChannel accept() throws IOException {
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
        if (!isBound || !socket.isBound()) {
            throw new NotYetBoundException();
        }

        SocketChannel sockChannel = SocketChannel.open();
        Socket socketGot = sockChannel.socket();

        try {
            begin();

            synchronized (acceptLock) {
                synchronized (blockingLock()) {
                    int oldtime = socket.getSoTimeout();
                    // timeout, 0 means blocking.
                    socket.setSoTimeout(isBlocking() ? 0 : DEFAULT_TIMEOUT);
                    ((ServerSocketAdapter) socket).accept(socketGot,
                            (SocketChannelImpl) sockChannel);
                    socket.setSoTimeout(oldtime);
                }
            }
        } catch (BindException e) {
            // FIXME improve native code.
            if (ERRMSG_ASYNCHRONOUS.equals(e.getMessage())) {
                throw new AsynchronousCloseException();
            }
        } catch (SocketTimeoutException e) {
            // nonblocking mode.
            return null;
        } finally {
            end(socketGot.isConnected());
        }

        // security check
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            try {
                sm.checkAccept(socketGot.getInetAddress().getHostAddress(),
                        socketGot.getPort());
            } catch (SecurityException e) {
                sockChannel.close();
                throw e;
            }
        }
        return sockChannel;
    }

    // -------------------------------------------------------------------
    // Protected inherited methods
    // -------------------------------------------------------------------

    /*
     * @see java.nio.channels.spi.AbstractSelectableChannel#implConfigureBlocking
     * 
     * (boolean)
     */
    protected void implConfigureBlocking(boolean blockingMode)
            throws IOException {
        Platform.getNetworkSystem().setNonBlocking(getFD(), blockingMode);
    }

    /*
     * 
     * @see java.nio.channels.spi.AbstractSelectableChannel#implCloseSelectableChannel()
     */
    synchronized protected void implCloseSelectableChannel() throws IOException {
        status = SERVER_STATUS_CLOSED;     
        if (!socket.isClosed()) {
            socket.close();
        }
    }

    // ----------------------------------------------------
    // Adapter classes.
    // ----------------------------------------------------

    /*
     * get the fd
     */
    public FileDescriptor getFD() {
        return fd;
    }

    /*
     * The adapter class of ServerSocekt.
     */
    private class ServerSocketAdapter extends ServerSocket {
        /*
         * Internal impl.
         */
        ServerSocketChannelImpl channelImpl;

        /*
         * init the impl.
         */
        ServerSocketAdapter(SocketImpl impl,
                ServerSocketChannelImpl aChannelImpl){
            super(impl);
            this.channelImpl = aChannelImpl;
        }

        /*
         * 
         * @see java.net.ServerSocket#bind(java.net.SocketAddress, int)
         */
        public void bind(SocketAddress localAddr, int backlog)
                throws IOException {
            super.bind(localAddr, backlog);
        }

        /*
         * do the accept.
         */
        public Socket accept(Socket aSocket, SocketChannelImpl sockChannel)
                throws IOException {
            if (isClosed()) {
                throw new SocketException(Msg.getString("K003d")); //$NON-NLS-1$
            }
            if (!isBound()) {
                throw new SocketException(Msg.getString("K031f")); //$NON-NLS-1$
            }

            // If a SOCKS proxy is being used, accept does strange things.
            // Instead of returning a new Socket and allowing this ServerSocket
            // to be used for another accept, it actually uses the current
            // ServerSocket
            // as the accepted Socket. So, closing the returned socket will
            // close the
            // ServerSocket as well. The ServerSocket cannot be used for a
            // second accept.
            if (NetUtil.usingSocks()) {
                return super.accept();
            }

            // a new socket is pass in so we do not need to "Socket aSocket =
            // new Socket();"
            boolean connectOK = false;
            try {
                synchronized (this) {
                    super.implAccept(aSocket);
                    // FIXME wait for fix.
                    // if (aSocket.isConnected()) {
                    sockChannel.setConnected();
                    // }
                }
                SecurityManager sm = System.getSecurityManager();
                if (sm != null) {
                    sm.checkAccept(aSocket.getInetAddress().getHostAddress(),
                            aSocket.getPort());
                }
                connectOK = true;
            } catch (SocketException e) {
                if (ERRMSG_ASYNCHRONOUS.equals(e.getMessage())) {
                    throw new AsynchronousCloseException();
                }
            } finally {
                if (!connectOK) {
                    aSocket.close();
                }
            }
            return aSocket;
        }

        /*
         * getting internal channel.
         */
        public ServerSocketChannel getChannel() {
            return channelImpl;
        }

        /*
         * 
         * @see java.net.ServerSocket#isBound()
         */
        public boolean isBound() {
            return channelImpl.isBound;
        }

        /*
         * 
         * @see java.net.ServerSocket#bind(java.net.SocketAddress)
         */
        public void bind(SocketAddress localAddr) throws IOException {
            super.bind(localAddr);
            channelImpl.isBound = true;
        }

        /*
         * @see java.net.ServerSocket#close()
         */
        public void close() throws IOException {
            synchronized (channelImpl) {
                if(channelImpl.isOpen()){
                    channelImpl.close();
                } else {
                    super.close();
                }
                channelImpl.status = SERVER_STATUS_CLOSED;
            }
        }
    }
}