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
package org.apache.harmony.rmi.internal.transport;

import java.io.IOException;
import java.net.Socket;
import java.rmi.ConnectIOException;
import java.rmi.MarshalException;
import java.rmi.server.ObjID;
import java.rmi.server.UID;

import org.apache.harmony.rmi.internal.transport.jrmp.ProtocolType;
import org.apache.harmony.rmi.internal.transport.jrmp.ReturnMessage;

/**
 * Encapsulates the Client events occurring in the JRMP protocol, when is 
 * encapsulated inside an HTTP call. It creates a  socket for the specified 
 * {@link org.apache.harmony.rmi.internal.transport.Endpoint}, it sends all
 * the parameters in the apropriate order and it waits for the results.
 * 
 * @author Gustavo Petri
 */
final class SingleOpClientConnection extends AbstractClientConnection {

    /**
     * Creates a new connection to the specified
     * {@link org.apache.harmony.rmi.internal.transport.Endpoint}.
     * @param sock 
     *            the {@link Socket} to be used by this Connection 
     * @param ep
     *            the {@link org.apache.harmony.rmi.internal.transport.Endpoint} to connect
     * @throws ConnectIOException
     *             if an IOException occurs while making a connection to the
     *             remote host
     */
    public SingleOpClientConnection(Socket sock, Endpoint ep) throws ConnectIOException {
        super(sock, ep);
    }

    /**
     * Establishes a connection.
     * <li>If {@link #lastUsageTime} is <code>null</code> then starts a
     * connection.
     * <li>If the difference between current time and {@link #lastUsageTime} is
     * bigger or equal than double of the value stored in the table then renew
     * the connection. The multiplied constant 2 is suggested in this
     * {@link <a href="http://archives.java.sun.com/cgi-bin/wa?A2=ind0101&L=rmi-users&P=R23746&D=0&I=-3">link</a>}
     * 
     * @throws MarshalException
     *             if a {@link java.io.IOException} occurs while marshalling the 
     *             remote call header, arguments or return value for a remote 
     *             method call
     * @throws ProtocolException
     *             if there is an error in the underlying protocol
     */
    @Override
	public final void establishConnection() throws MarshalException, IOException {
        protocolHandler.writeHandshake(ProtocolType.SINGLE_OP);
    }

    /**
     * Writes the call request data into the connection, and reads the 
     * results of the execution in the server.
     * 
     * @param args
     *            the arguments of the invocation
     * @param waitReturn
     *            this parameter indicates whether or not to wait for a return
     *            value
     * @return the return value of the remote method call
     * @throws Exception
     *             if any exception is thrown on the server side
     */
    @Override
	protected final Object methodCall(ObjID objId, long hash, Object[] args,
            boolean waitReturn) throws Exception {
        @SuppressWarnings("unused") 
        UID uid = null;
        ReturnMessage result = null;
        TransportManager tm = TransportManager.getTransportManager();
        
        protocolHandler.writeCall(objId, hash, args);
        out.flush();
        protocolHandler.readHandshakeResponse();
        result = protocolHandler.readResult(waitReturn); 
        uid = result.getUID();
        if (result.sendsDGCAck()) {
            tm.acknowledgeDGC(uid, this.ep);
        }
        if (result.isException()) {
            throw result.getException();
        } 
        return result.getResult();
    }
    
    /**
     * Indicates is this {@link Socket} is reusable. 
     */
    @Override
    public boolean isReusable() {
    	return false;
    }
}
