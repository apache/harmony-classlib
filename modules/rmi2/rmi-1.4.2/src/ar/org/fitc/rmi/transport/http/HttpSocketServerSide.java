/* 
*  Copyright 2005 The Apache Software Foundation or its licensors, as applicable. 
* 
*  Licensed under the Apache License, Version 2.0 (the "License"); 
*  you may not use this file except in compliance with the License. 
*  You may obtain a copy of the License at 
* 
*    http://www.apache.org/licenses/LICENSE-2.0 
* 
*  Unless required by applicable law or agreed to in writing, software 
*  distributed under the License is distributed on an "AS IS" BASIS, 
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
*  See the License for the specific language governing permissions and 
*  limitations under the License. 
*/
package ar.org.fitc.rmi.transport.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;

/**
 * For server purposes. Acts as a wrapper for a socket that is specified at the
 * constructor. Overrides all the socket's methods and delegates to the wrapped
 * socket. Specify other functionality overriding the methods
 * <code>getInputStream()</code> and <code>getOutputStream()</code>.
 * 
 * @author Diego Raúl Mercado
 */
public final class HttpSocketServerSide extends Socket {

    /** The socket's input stream */
    private InputStream input;

    /** The socket's output stream */
    private OutputStream output;

    /** The wrapped socket */
    private Socket serverSocket;

    /**
     * Constructor.
     * 
     * @param socket
     *            the socket to be wrapped
     */
    public HttpSocketServerSide(Socket socket) {
        serverSocket = socket;
    }

    /**
     * @ar.org.fitc.spec_ref returns a <code>HTTPInputStream</code> instance
     *                       with the input stream of the wrapped socket as the
     *                       constructor parameter
     */
    @Override
    public synchronized final InputStream getInputStream() throws IOException {
        if (input == null) {
            input = new HttpServerInputStream(serverSocket.getInputStream());
        }
        return input;
    }

    /**
     * @ar.org.fitc.spec_ref returns a <code>HTTPClientOutputStream</code>
     *                       instance with the input stream of the wrapped
     *                       socket as one of the constructor parameters
     */
    @Override
    public synchronized final OutputStream getOutputStream() throws IOException {
        if (output == null) {
            output = new HttpServerOutputStream(serverSocket.getOutputStream(),
                    getLocalAddress().isAnyLocalAddress() ? "127.0.0.1"
                            : getLocalAddress().getHostAddress());
        }
        return output;
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final void bind(SocketAddress arg0) throws IOException {
        serverSocket.bind(arg0);
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public synchronized final void close() throws IOException {
        serverSocket.close();
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final void connect(SocketAddress arg0, int arg1) throws IOException {
        serverSocket.connect(arg0, arg1);
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final void connect(SocketAddress arg0) throws IOException {
        serverSocket.connect(arg0);
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final SocketChannel getChannel() {
        return serverSocket.getChannel();
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final InetAddress getInetAddress() {
        return serverSocket.getInetAddress();
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final boolean getKeepAlive() throws SocketException {
        return serverSocket.getKeepAlive();
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final InetAddress getLocalAddress() {
        return serverSocket.getLocalAddress();
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final int getLocalPort() {
        return serverSocket.getLocalPort();
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final SocketAddress getLocalSocketAddress() {
        return serverSocket.getLocalSocketAddress();
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final boolean getOOBInline() throws SocketException {
        return serverSocket.getOOBInline();
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final int getPort() {
        return serverSocket.getPort();
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final synchronized int getReceiveBufferSize() throws SocketException {
        return serverSocket.getReceiveBufferSize();
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final SocketAddress getRemoteSocketAddress() {
        return serverSocket.getRemoteSocketAddress();
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final boolean getReuseAddress() throws SocketException {
        return serverSocket.getReuseAddress();
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final synchronized int getSendBufferSize() throws SocketException {
        return serverSocket.getSendBufferSize();
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final int getSoLinger() throws SocketException {
        return serverSocket.getSoLinger();
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final synchronized int getSoTimeout() throws SocketException {
        return serverSocket.getSoTimeout();
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final boolean getTcpNoDelay() throws SocketException {
        return serverSocket.getTcpNoDelay();
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final int getTrafficClass() throws SocketException {
        return serverSocket.getTrafficClass();
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final boolean isBound() {
        return serverSocket.isBound();
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final boolean isClosed() {
        return serverSocket.isClosed();
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final boolean isConnected() {
        return serverSocket.isConnected();
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final boolean isInputShutdown() {
        return serverSocket.isInputShutdown();
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final boolean isOutputShutdown() {
        return serverSocket.isOutputShutdown();
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final void sendUrgentData(int arg0) throws IOException {
        serverSocket.sendUrgentData(arg0);
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final void setKeepAlive(boolean arg0) throws SocketException {
        serverSocket.setKeepAlive(arg0);
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final void setOOBInline(boolean arg0) throws SocketException {
        serverSocket.setOOBInline(arg0);
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final void setPerformancePreferences(int arg0, int arg1, int arg2) {
        serverSocket.setPerformancePreferences(arg0, arg1, arg2);
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public synchronized final void setReceiveBufferSize(int arg0)
            throws SocketException {
        serverSocket.setReceiveBufferSize(arg0);
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final void setReuseAddress(boolean arg0) throws SocketException {
        serverSocket.setReuseAddress(arg0);
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public synchronized void setSendBufferSize(int arg0) throws SocketException {
        serverSocket.setSendBufferSize(arg0);
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final void setSoLinger(boolean arg0, int arg1)
            throws SocketException {
        serverSocket.setSoLinger(arg0, arg1);
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public synchronized final void setSoTimeout(int arg0)
            throws SocketException {
        serverSocket.setSoTimeout(arg0);
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final void setTcpNoDelay(boolean arg0) throws SocketException {
        serverSocket.setTcpNoDelay(arg0);
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final void setTrafficClass(int arg0) throws SocketException {
        serverSocket.setTrafficClass(arg0);
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final void shutdownInput() throws IOException {
        serverSocket.shutdownInput();
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final void shutdownOutput() throws IOException {
        serverSocket.shutdownOutput();
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    @Override
    public final String toString() {
        return serverSocket.toString();
    }
}
