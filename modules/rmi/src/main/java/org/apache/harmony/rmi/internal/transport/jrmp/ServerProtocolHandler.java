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
package org.apache.harmony.rmi.internal.transport.jrmp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.rmi.UnmarshalException;

import org.apache.harmony.rmi.internal.transport.EndpointID;
import org.apache.harmony.rmi.internal.transport.ProtocolException;

/**
 * Implements the Server side management of the messages of the JRMP protocol.
 * 
 * @author Gustavo Petri
 */
public final class ServerProtocolHandler {

	/**
	 * The client endpoint of the connection.
	 */
	private EndpointID clientEP;

	/**
	 * The local {@link java.io.InputStream} to interact with the client
	 */
	private DataInputStream in;

	/**
	 * The local {@link java.io.OutputStream} to interact with the client.
	 */
	private DataOutputStream out;

	/**
	 * The {@link ProtocolType} which identifies the type of the connection
	 */
	private ProtocolType protocolType;

	/**
	 * Constructs a new {@link ServerProtocolHandler}
	 * 
	 * @param in
	 *            the {@link org.apache.harmony.rmi.internal.transport.RMIObjectInputStream}
	 * @param out
	 *            the {@link org.apache.harmony.rmi.internal.transport.RMIObjectInputStream}
	 * @param clientEP
	 *            the client {@link EndpointID}
	 */
	public ServerProtocolHandler(DataInputStream in, DataOutputStream out,
			EndpointID clientEP) {

		this.out = out;
		this.in = in;
		this.clientEP = (clientEP == null) ? new EndpointID() : clientEP;
	}

	/**
	 * Writes the JRMP handshake answer to the client.
	 * 
	 * @throws ProtocolException
	 *             if an exception occurs writing the hanshake data to the
	 *             client.
	 */
	public final void answerHandshake() throws ProtocolException {

		try {
			if (protocolType == ProtocolType.STREAM) {
				out.writeByte(HeaderResponse.PROTOCOL_ACK.getValue());
				clientEP.write(out);
			} else if (protocolType == ProtocolType.SINGLE_OP) {
				out.writeByte(HeaderResponse.PROTOCOL_ACK.getValue());
			} else {
				out.writeByte(HeaderResponse.PROTOCOL_NOT_SUPPORTED.getValue());
			}
		} catch (IOException e) {
			throw new ProtocolException("Error sending Protocol answer");
		}
	}

	/**
	 * Reads the default client's Endpoint used to accept calls.
	 * 
	 * @return an {@link org.apache.harmony.rmi.internal.transport.EndpointID}
	 */
	public final EndpointID readEndpointNegotiation() {
		EndpointID defaultEP = null;
		try {
			defaultEP = EndpointID.read(in);
		} catch (IOException e) {
			new ProtocolException("Exception reading EndpointID", e);
		}
		return defaultEP;
	}

	/**
	 * Reads the initial handshake sequence sent by the client.
	 * 
	 * @param type
	 *            a {@link ProtocolType} object indicating the protocol type
	 * @throws ProtocolException
	 *             if an exception occurs while reading the handshake data from
	 *             the imput stream
	 */
	public final void readHandShake(ProtocolType type) throws ProtocolException {
		Header header = new Header(type);
		try {
			header.readExternal(in);
			protocolType = header.getProtocolType();
		} catch (IOException e) {
			throw new ProtocolException("I/O Error Reading Transport Header", e);
		}
	}

	/**
	 * Reads a {@link Message} object from the connection's input stream.
	 * 
	 * @return the {@link Message} object read.
	 * @throws UnmarshalException
	 *             if an exception occurs reading the input stream.
	 */
	public final Message readMessage() throws UnmarshalException {
		Message msg = new Message();
		try {
			msg.readExternal(in);
		} catch (IOException e) {
			throw new UnmarshalException("IO error unmarshaling a message", e);
		}
		return msg;
	}

	/**
	 * Writes a {@link MessageResponse#PING_ACK} into the ouput stream.
	 * 
	 * @throws ProtocolException
	 *             if there is an error in the underlying protocol
	 */
	public final void writePingAck() throws ProtocolException {
		try {
			out.writeByte(MessageResponse.PING_ACK.getValue());
		} catch (IOException e) {
			throw new ProtocolException("I/O Error Marshaling a ProtocolAck", e);
		}
	}

}
