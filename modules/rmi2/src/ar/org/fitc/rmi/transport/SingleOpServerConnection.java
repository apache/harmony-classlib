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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.rmi.RemoteException;

import ar.org.fitc.rmi.transport.jrmp.Message;
import ar.org.fitc.rmi.transport.jrmp.MessageType;
import ar.org.fitc.rmi.transport.jrmp.ProtocolType;

/**
 * Encapsulates the Server events occurring in the JRMP protocol.
 * 
 * @author Gustavo Petri
 */
final class SingleOpServerConnection extends AbstractServerConnection {

    /**
     * Creates a new connection to the specified
     * {@link ar.org.fitc.rmi.transport.EndpointID}
     * 
     * @param in
     *            the specified {@link InputStream}
     * @param out
     *            the specified {@link OutputStream}
     * @param clientEP
     *            the specified {@link ar.org.fitc.rmi.transport.EndpointID}
     * @param sock
     *            the socket of connection
     */
    public SingleOpServerConnection(InputStream in, OutputStream out,
            EndpointID clientEP, Socket sock) {
        super(in, out, clientEP, sock);
    }

    /**
     * Establishes a connection.
     * 
     * @throws ProtocolException
     *             if there is an error in the underlying protocol
     */
    @Override
	public final void establishConnection() throws ProtocolException {
        protocolHandler.readHandShake(ProtocolType.SINGLE_OP);
        protocolHandler.answerHandshake();
    }

	/**
	 * Handles the incoming message.
	 * 
	 * @throws RemoteException If an exception occurs during message handling.
	 */
    @Override
	public final void serve() throws RemoteException {
        Message msg = protocolHandler.readMessage();
        MessageType type = msg.getType();

        if (type == MessageType.CALL) {
            handleCall(msg);
        } else if (type == MessageType.DGCACK) {
            handleDGCAck(msg);
            // Writes the HTTP Response message
            try {
                out.flush();
            } catch (IOException e) {
                throw new ProtocolException("Exception writing the HTTP DGCAck response");
            }
        }
    }

}