/*
 *  Copyright 2006 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * @author Alexander Y. Kleymenov
 * @version $Revision$
 */

package org.apache.harmony.security.provider.jsse;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;

/**
 * This class wraps the SSL fuctionality over existing conneted socket.
 */
public class SSLSocketWrapper extends SSLSocketImpl {

    private final Socket socket;
    private final boolean autoClose;

    protected SSLSocketWrapper(Socket socket, boolean autoClose, SSLParameters sslParameters) throws IOException {
        super(sslParameters);
        if (!socket.isConnected()) {
            throw new SocketException("Socket is not connected.");
        }
        this.socket = socket;
        this.autoClose = autoClose;
        init();
    }

    protected void initTransportLayer() throws IOException {
        input = socket.getInputStream();
        output = socket.getOutputStream();
    }

    protected void closeTransportLayer() throws IOException {
        if (autoClose && (input != null)) {
            socket.close();
            input.close();
            output.close();
        }
    }

    // ------------------- Wrapping method implementations ---------------

    public void connect(SocketAddress sockaddr, int timeout)
        throws IOException {
        throw new IOException("Underlying socket is already connected.");
    }

    public void connect(SocketAddress sockaddr) throws IOException {
        throw new IOException("Underlying socket is already connected.");
    }

    public void bind(SocketAddress sockaddr) throws IOException {
        throw new IOException("Underlying socket is already connected.");
    }

    public SocketAddress getRemoteSocketAddress() {
        return socket.getRemoteSocketAddress();
    }

    public SocketAddress getLocalSocketAddress() {
        return socket.getLocalSocketAddress();
    }

    public InetAddress getLocalAddress() {
        return socket.getLocalAddress();
    }

    public InetAddress getInetAddress() {
        return socket.getInetAddress();
    }

    public String toString() {
        return "SSL socket over " + socket.toString();
    }

    public void setSoLinger(boolean on, int linger) throws SocketException {
        socket.setSoLinger(on, linger);
    }

    public void setTcpNoDelay(boolean on) throws SocketException {
        socket.setTcpNoDelay(on);
    }

    public void setReuseAddress(boolean on) throws SocketException {
        socket.setReuseAddress(on);
    }

    public void setKeepAlive(boolean on) throws SocketException {
        socket.setKeepAlive(on);
    }

    public void setTrafficClass(int tos) throws SocketException {
        socket.setTrafficClass(tos);
    }

    public void setSoTimeout(int to) throws SocketException {
        socket.setSoTimeout(to);
    }

    public void setSendBufferSize(int size) throws SocketException {
        socket.setSendBufferSize(size);
    }

    public void setReceiveBufferSize(int size) throws SocketException {
        socket.setReceiveBufferSize(size);
    }

    public boolean getTcpNoDelay() throws SocketException {
        return socket.getTcpNoDelay();
    }

    public boolean getReuseAddress() throws SocketException {
        return socket.getReuseAddress();
    }

    public boolean getOOBInline() throws SocketException {
        return socket.getOOBInline();
    }

    public boolean getKeepAlive() throws SocketException {
        return socket.getKeepAlive();
    }

    public int getTrafficClass() throws SocketException {
        return socket.getTrafficClass();
    }

    public int getSoTimeout() throws SocketException {
        return socket.getSoTimeout();
    }

    public int getSoLinger() throws SocketException {
        return socket.getSoLinger();
    }

    public int getSendBufferSize() throws SocketException {
        return socket.getSendBufferSize();
    }

    public int getReceiveBufferSize() throws SocketException {
        return socket.getReceiveBufferSize();
    }

    public boolean isConnected() {
        return socket.isConnected();
    }

    public boolean isClosed() {
        return socket.isClosed();
    }

    public boolean isBound() {
        return socket.isBound();
    }

    public boolean isOutputShutdown() {
        return socket.isOutputShutdown();
    }

    public boolean isInputShutdown() {
        return socket.isInputShutdown();
    }

    public int getPort() {
        return socket.getPort();
    }

    public int getLocalPort() {
        return socket.getLocalPort();
    }

    // -------------------------------------------------------------------

}

