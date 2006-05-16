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
package ar.org.fitc.rmi.transport;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.ConnectIOException;
import java.rmi.MarshalException;
import java.rmi.server.ObjID;
import java.rmi.server.UID;
import java.util.WeakHashMap;

import ar.org.fitc.rmi.transport.jrmp.ClientProtocolHandler;
import ar.org.fitc.rmi.utils.PropertiesReader;

/*
 * NOTE: 
 * This class has been modified in order to support 
 * Java VM 1.4.2 using the javac's target jsr14 
 */

/**
 * Encapsulates the Client events occurring in the JRMP protocol. It creates a
 * socket for the specified {@link ar.org.fitc.rmi.transport.Endpoint}, it
 * sends all the parameters in the apropriate order and it waits for the
 * results.
 * 
 * @author Gustavo Petri
 */
abstract class AbstractClientConnection {

    /**
     * A {@link #clientConnectionID} counter.
     */
    static protected int clientConnectionCounter = 0;

    /**
     * The table for storing the {@link ar.org.fitc.rmi.transport.Endpoint} and
     * the {@link #lastUsageTime} for the connection.
     */
    static protected WeakHashMap<Endpoint, Long> rttTable;

    /**
     * Used to store the value of the ar.org.fitc.rmi.transport.readTimeout
     * property.
     */
    protected static int SO_TIME_OUT;

    static {
        SO_TIME_OUT = PropertiesReader.readInt(
                "ar.org.fitc.rmi.transport.readTimeout", 0);
        rttTable = new WeakHashMap<Endpoint, Long>();
    }

    /**
     * The client connection ID.
     */
    protected int clientConnectionID;

    /**
     * The endpoint of the connexion.
     */
    protected Endpoint ep;

    /**
     * The input stream of the connection
     */
    protected DataInputStream in;

    /**
     * The last usage time for the connection.
     */
    protected Long lastUsageTime;

    /**
     * The output stream of the connection
     */
    protected DataOutputStream out;

    /**
     * The client protocolHandler neeeded to implement the JRMP protocol.
     */
    protected ClientProtocolHandler protocolHandler;

    /**
     * The socket for communication.
     */
    protected Socket sock;

    /**
     * Creates a new connection to the specified
     * {@link ar.org.fitc.rmi.transport.Endpoint} using the underlying
     * {@link java.net.Socket}
     * 
     * @param sock
     *            the {@link java.net.Socket} to which the connection belongs
     * @param ep
     *            the {@link ar.org.fitc.rmi.transport.Endpoint} for this
     *            connection
     * @throws ConnectIOException
     *             if an IOException occurs while making a connection to the
     *             remote host
     */
    public AbstractClientConnection(Socket sock, Endpoint ep)
            throws ConnectIOException {
        this.ep = ep;
        this.lastUsageTime = null;
        clientConnectionID = ++clientConnectionCounter;
        this.sock = sock;
        try {
            this.out = new DataOutputStream(new BufferedOutputStream(sock
                    .getOutputStream()));
            this.in = new DataInputStream(new BufferedInputStream(sock
                    .getInputStream()));
        } catch (IOException e) {
            throw new ConnectIOException("I/O exception Creating Connection", e);
        }
        protocolHandler = new ClientProtocolHandler(this.in, this.out);
    }

    /**
     * Writes the call request data into the connection, and reads the results
     * of the execution in the server.
     * 
     * @param objId
     *            the specified {@link java.rmi.server.ObjID}
     * @param hash
     *            the specified hash for the method being invoked
     * @param args
     *            the arguments of the invocation
     * @param waitReturn
     *            this parameter indicates whether or not to wait for a return
     *            value
     * @return the return value of the remote method call
     * @throws Exception
     *             if any exception is thrown on the server side
     */
    protected abstract Object methodCall(ObjID objId, long hash, Object[] args,
            boolean waitReturn) throws Exception;

    /**
     * Sets up the new connection, sending and/or receiving all the data needed
     * to initialize the connection.
     * 
     * @throws MarshalException
     *             if an exception occurs while marshalling parameters
     * @throws IOException
     *             if the socket is closed
     * @throws ProtocolException
     *             if there is an error in the underlying protocol
     */
    protected abstract void establishConnection() throws MarshalException,
            IOException, ProtocolException;

    /**
     * Returns the ID of this connection.
     * 
     * @return the ID of this connection
     */
    public final int getClientConnectionID() {
        return clientConnectionID;
    }

    /**
     * Executes the remote method call. This method accomplishes the handling of
     * the client side of the JRMP protocol.
     * 
     * @param objId
     *            the {@link java.rmi.server ObjID} to which the call will be
     *            issued
     * @param hash
     *            the hash of the method to be called
     * @param args
     *            the arguments of the call
     * @param waitReturn
     *            this parameter indicates whether or not to wait for a return
     *            value
     * @return the return value of the remote method call
     * @throws Exception
     *             if any exception is thrown on the server side
     */
    public final Object invoke(ObjID objId, long hash, Object[] args,
            boolean waitReturn) throws Exception {
        Object obj = null;

        try {
            obj = methodCall(objId, hash, args, waitReturn);
            this.lastUsageTime = new Long(System.currentTimeMillis());
            return obj;
        } catch (ProtocolException e) {
            // This exception can only happen on the unusual case of a DGCAck
            // failure;
            return obj;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Sends a DGCack message, using the specified {@link java.rmi.server.UID} to
     * identify it.
     * 
     * @param uid
     *            the identifier used to identify the DGC ack message
     * @throws ProtocolException
     *             if a problem occurs when flushing the DGC acknowledge message
     */
    public final void acknowledgeDGC(UID uid) throws ProtocolException {
        protocolHandler.writeDGCAck(uid);
        try {
            out.flush();
        } catch (IOException e) {
            throw new ProtocolException("Exception flushing a DGCAck", e);
        }
    }

    /**
     * Closes this connection. It also closes the underlying socket.
     * 
     */
    public final void releaseConnection() {

        try {
            this.sock.shutdownOutput();
            this.sock.shutdownInput();
            this.sock.close();
        } catch (IOException e) {
            // FIXME REVIEW: What to do when an exception is thrown here. May be
            // a logger could be useful.
        } finally {
            this.sock = null;
        }
    }

    /**
     * Returns <code>true</code> if the connection can be pooled for future
     * reuse.
     * 
     * @return <code>true</code> if the connection can be pooled for future
     *         reuse, <code>false</code> otherwise.
     */
    public boolean isReusable() {
        return false;
    }
}
