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
import java.rmi.server.UID;

import org.apache.harmony.rmi.internal.transport.RMIObjectInputStream;
import org.apache.harmony.rmi.internal.transport.RMIObjectOutputStream;
import org.apache.harmony.rmi.internal.utils.Pair;

/**
 * A tagged union type which represents a message returned from the RMI Server
 * 
 * @author Gustavo Petri
 * 
 */
public class ReturnMessage {

	/**
	 * ID used for serialization purposes
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * A {@link MessageResponse} which defines the type of return message.
	 */
	private MessageResponse type;

	/**
	 * A flag that identifies if this {@link ReturnMessage} represents an
	 * exception.
	 */
	private boolean isException;

	/**
	 * This attribute is used to store the exception returned from the server,
	 * when {@link ReturnMessage#type} is <code>true</code>
	 */
	private Exception exception = null;

	/**
	 * This attribute is used to store the object returned from the server, when
	 * {@link ReturnMessage#type} is <code>false</code>
	 */
	private Object result = null;

	/**
	 * This flag is used to determine if this {@link ReturnMessage} should
	 * read/write a return value from/to the stream
	 */
	private boolean returnsValue;

	/**
	 * This flag is set to <code>true</code> when the read result is a remote
	 * object, and a DGCAck message should be sent to the server
	 */
	private boolean sendDGCAck;

	/**
	 * The identificator which is sent and received with the return message
	 */
	private UID uid;

	/**
	 * Creates a new {@link ReturnMessage}. By default, a
	 * {@link MessageResponse#PING_ACK} message response is created.
	 */
	public ReturnMessage() {
		this.type = MessageResponse.PING_ACK;
	}

	/**
	 * Creates a new {@link ReturnMessage} of the {@link MessageResponse#RETURN}
	 * type, which will represent an exception returned from the server.
	 * 
	 * @param e
	 *            the exception that will be encapsulated by this
	 *            {@link ReturnMessage}
	 */
	public ReturnMessage(Exception e) {
		this.type = MessageResponse.RETURN;
		this.isException = true;
		this.exception = e;
	}

	/**
	 * Creates a new {@link ReturnMessage} of the {@link MessageResponse#RETURN}
	 * type, which will represent an object returned from the server.
	 * 
	 * @param o
	 *            the object that will be encapsulated by this
	 *            {@link ReturnMessage}
	 * @param returnsValue
	 *            A flag that indicates if this {@link ReturnMessage} should
	 *            read/write a return value from/to the stream
	 */
	public ReturnMessage(Object o, boolean returnsValue) {
		this.type = MessageResponse.RETURN;
		this.isException = false;
		this.returnsValue = returnsValue;
		this.result = o;
	}

	/**
	 * Returns the {@link ReturnMessage#type} of this {@link ReturnMessage}
	 * 
	 * @return the {@link ReturnMessage#type} of this {@link ReturnMessage}
	 */
	public final MessageResponse getReturnType() {
		return type;
	}

	/**
	 * Returns the {@link ReturnMessage#isException} of this
	 * {@link ReturnMessage}
	 * 
	 * @return the {@link ReturnMessage#isException} of this
	 *         {@link ReturnMessage}
	 */
	public final boolean isException() {
		return isException;
	}

	/**
	 * Returns the {@link ReturnMessage#exception} of this {@link ReturnMessage}
	 * 
	 * @return the {@link ReturnMessage#exception} of this {@link ReturnMessage}
	 */
	public final Exception getException() {
		return exception;
	}

	/**
	 * Returns the {@link ReturnMessage#result} of this {@link ReturnMessage}
	 * 
	 * @return the {@link ReturnMessage#result} of this {@link ReturnMessage}
	 */
	public final Object getResult() {
		return result;
	}

	/**
	 * Returns the {@link ReturnMessage#sendDGCAck} of this
	 * {@link ReturnMessage}
	 * 
	 * @return the {@link ReturnMessage#sendDGCAck} of this
	 *         {@link ReturnMessage}
	 */
	public final boolean sendsDGCAck() {
		return sendDGCAck;
	}

	/**
	 * Returns the {@link ReturnMessage#uid} of this {@link ReturnMessage}
	 * 
	 * @return the {@link ReturnMessage#uid} of this {@link ReturnMessage}
	 */
	public final UID getUID() {
		return uid;
	}

	/**
	 * Sets the {@link ReturnMessage#returnsValue} of this {@link ReturnMessage}
	 * 
	 * @param returnsValue
	 *            The flag that indicates if this {@link ReturnMessage} should
	 *            read/write a return value from/to the stream
	 * 
	 */
	public final void setReturnsValue(boolean returnsValue) {
		this.returnsValue = returnsValue;
	}

	/**
	 * Writes this {@link ReturnMessage} to a {@link DataOutputStream}
	 * 
	 * @param out
	 *            the ouput stream in which the {@link ReturnMessage} will be
	 *            written to
	 * @return the {@link UID} that identificates the written
	 *         {@link ReturnMessage}
	 * @throws IOException
	 *             if an exception occurs writing the {@link ReturnMessage} to
	 *             the stream
	 */
	public final UID write(DataOutputStream out) throws IOException {
		RMIObjectOutputStream objOut;
		boolean writesRemote = false;

		objOut = new RMIObjectOutputStream(out);
		objOut.writeByte(type.getValue());
		if (type == MessageResponse.RETURN) {
			if (!isException) {
				objOut.writeByte(ReturnType.RETURN_HEADER.getValue());
				uid = new UID();
				uid.write(objOut);
				if (returnsValue) {
					writesRemote = objOut.writeResultObject(result);
				}
			} else {
				objOut.writeByte(ReturnType.EXCEPTION_HEADER.getValue());
				uid = new UID();
				uid.write(objOut);
				writesRemote = objOut.writeResultObject(exception);
			}
		}
		objOut.drain(); // don't propagate through the socket's output stream
		return writesRemote ? uid : null;
	}

	/**
	 * Reads a {@link ReturnMessage} from a {@link DataInputStream} and sets its
	 * internal attributes.
	 * 
	 * @param in
	 *            the input stream where the {@link ReturnMessage} will be read
	 *            from
	 * @throws IOException
	 *             if an exception occurs when reading the {@link ReturnMessage}
	 * @throws ClassNotFoundException
	 *             if a required class is not found
	 */
	public final void read(DataInputStream in) throws IOException,
			ClassNotFoundException {
		RMIObjectInputStream objIn = null;

		objIn = new RMIObjectInputStream(in);
		type = MessageResponse.createMessageResponse(objIn.readByte());
		if (type == MessageResponse.RETURN) {
			ReturnType retType = ReturnType.createReturnType(objIn.readByte());
			uid = UID.read(objIn);
			if (retType.equals(ReturnType.RETURN_HEADER) && returnsValue) {
				Pair<Object, Boolean> pair = objIn.readResultObject();
				result = pair.getFirst();
				sendDGCAck = pair.getSecond();
			} else if (retType.equals(ReturnType.EXCEPTION_HEADER)) {
				Pair<Object, Boolean> pair = objIn.readResultObject();
				isException = true;
				exception = (Exception) pair.getFirst();
				sendDGCAck = pair.getSecond();
			}
		}
	}
}
