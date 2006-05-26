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
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.rmi.RemoteException;

import org.apache.harmony.rmi.internal.transport.jrmp.Message;
import org.apache.harmony.rmi.internal.transport.jrmp.MessageType;
import org.apache.harmony.rmi.internal.transport.jrmp.ProtocolType;

/**
 * Encapsulates the Server events occurring in the JRMP protocol.
 * 
 * @author Gustavo Petri
 */
final class StreamServerConnection extends AbstractServerConnection {

	/**
	 * Creates a new connection to the specified
	 * {@link org.apache.harmony.rmi.internal.transport.EndpointID}
	 * 
	 * @param in
	 *            the specified
	 *            {@link InputStream}
	 * @param out
	 *            the specified
	 *            {@link OutputStream}
	 * @param clientEP
	 *            the specified {@link org.apache.harmony.rmi.internal.transport.EndpointID}
	 * @param sock
	 *            the socket of connection
	 */
	public StreamServerConnection(InputStream in, OutputStream out,
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
		/*
		 * REVIEW: Check what to do with the client's default serving endpoint.
		 */
		protocolHandler.readHandShake(ProtocolType.STREAM);
		protocolHandler.answerHandshake();
		try {
			out.flush();
		} catch (IOException e) {
			throw new ProtocolException(
					"IOException flushing the socket contents", e);
		}
		// ignores the returned ServerProtocolHandler
		protocolHandler.readEndpointNegotiation();
	}

	/**
	 * Handles the incoming message.
	 * 
	 * @throws RemoteException
	 *             If an exception occurs during message handling.
	 */
	@Override
	public final void serve() throws Exception {
		Message msg = null;
        try {
            msg = protocolHandler.readMessage();
        } catch (Exception e) {
            handleException(e);
            throw e;
        }
		MessageType type = msg.getType();

		if (type == MessageType.CALL) {
			handleCall(msg);
		} else if (type == MessageType.DGCACK) {
			handleDGCAck(msg);
		} else if (type == MessageType.PING) {
			protocolHandler.writePingAck();
			try {
				out.flush();
			} catch (IOException e) {
				throw new ProtocolException("I/O Error Writing the PingAck");
			}
		}
	}
}