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
import java.net.Socket;
import java.rmi.ConnectIOException;
import java.rmi.MarshalException;
import java.rmi.server.ObjID;
import java.rmi.server.UID;

import ar.org.fitc.rmi.transport.jrmp.ProtocolType;
import ar.org.fitc.rmi.transport.jrmp.ReturnMessage;

/**
 * Encapsulates the Client events occurring in the JRMP protocol. It creates a
 * socket for the specified {@link ar.org.fitc.rmi.transport.Endpoint}, it
 * sends all the parameters in the apropriate order and it waits for the
 * results.
 * 
 * @author Gustavo Petri
 */
final class StreamClientConnection extends AbstractClientConnection {

	/**
	 * Creates a new connection to the specified
	 * {@link ar.org.fitc.rmi.transport.Endpoint}.
	 * 
	 * @param ep
	 *            the {@link ar.org.fitc.rmi.transport.Endpoint} to connect
	 * @param sock
	 *            the {@link java.net.Socket} to connect
	 * @throws ConnectIOException
	 *             if an IOException occurs while making a connection to the
	 *             remote host
	 */
	public StreamClientConnection(Socket sock, Endpoint ep)
			throws ConnectIOException {
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
	 *             if a {@link java.io.IOException} occurs while
	 *             marshalling the remote call header, arguments or return value
	 *             for a remote method call
	 * @throws IOException
	 *             if the socket is closed
	 * @throws ProtocolException
	 *             if there is an error in the underlying protocol
	 */
	@Override
	public final void establishConnection() throws MarshalException,
			IOException, ProtocolException {

		if (lastUsageTime == null) {
			// Initially, by default 1 millisecond.
			handshake();
			rttTable.put(this.ep, new Long(1));
			lastUsageTime = System.currentTimeMillis();
		} else if (System.currentTimeMillis() - this.lastUsageTime >= (rttTable
				.get(this.ep) * 2)) {
			// The multiplied constant 2 is suggested in
			// http://archives.java.sun.com/cgi-bin/wa?A2=ind0101&L=rmi-users&P=R23746&D=0&I=-3.
			Long sentTime = System.currentTimeMillis();
			protocolHandler.writePing(); // renewConnection();
			out.flush();
			protocolHandler.readPingAck();
			rttTable.put(ep, System.currentTimeMillis() - sentTime);
		}
	}

	/**
	 * Writes the initial handshake data, indicating that the Stream protocol
	 * will be used, reads the handshake response from the server, and writes
	 * the client's default Endpoint.
	 * 
	 * @throws MarshalException
	 *             if an exception occurs while writing the handshake
	 *             information.
	 */
	private final void handshake() throws MarshalException {
		try {
			protocolHandler.writeHandshake(ProtocolType.STREAM);
			out.flush();
			protocolHandler.readHandshakeResponse();
			EndpointID.read(in);
			protocolHandler.writeHandshakeResponse();
			out.flush();
		} catch (MarshalException e) {
			throw new MarshalException("I/O Error Marshaling Transport Header",
					e);
		} catch (ProtocolException e) {
			throw new MarshalException("I/O Error Marshaling Transport Header",
					e);
		} catch (IOException e) {
			throw new MarshalException("Exception marshaling JRMP Header", e);
		}
	}

	/**
	 * Writes the call request data into the connection, and reads the results
	 * of the execution in the server.
	 * 
	 * @param objId
	 *            the specified {@link java.rmi.server.ObjID}
	 * @param args
	 *            the arguments of the invocation
	 * @param hash
	 *            the specified hash for the invoke method
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
		UID uid = null;
		ReturnMessage result = null;

		protocolHandler.writeCall(objId, hash, args);
		out.flush();
		result = protocolHandler.readResult(waitReturn);
		uid = result.getUID();
		// FIXME : What do we do with the UID?
		if (result.sendsDGCAck()) {
			try {
				acknowledgeDGC(uid);
			} catch (ProtocolException e) {
				// FIXME REVIEW: Not sure this exception must be swallowed.
			}
		}
		if (result.isException()) {
			throw result.getException();
		}
		return result.getResult();
	}

	@Override
	public boolean isReusable() {
		return true;
	}
}
