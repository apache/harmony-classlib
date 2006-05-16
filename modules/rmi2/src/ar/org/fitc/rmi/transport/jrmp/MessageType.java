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
package ar.org.fitc.rmi.transport.jrmp;

import ar.org.fitc.rmi.transport.ProtocolException;

/**
 * This Enumerated Type describes the possible Messages received by a RMI
 * Server.
 * 
 * @author Gustavo Petri
 * 
 */
public enum MessageType {

	/**
	 * A 0x50 <code>byte</code> representing a CALL message
	 */
	CALL((byte) 0x50),

	/**
	 * A 0x52 <code>byte</code> representing a PING message
	 */
	PING((byte) 0x52),

	/**
	 * A 0x54 <code>byte</code> representing a DGCACK message
	 */
	DGCACK((byte) 0x54);

	/**
	 * The internal <code>byte</code> value for this enum
	 */
	private byte value;

	/**
	 * Private constructor, sets the internal {@link MessageType#value}
	 * 
	 * @param value
	 */
	private MessageType(byte value) {
		this.value = value;
	}

	/**
	 * Getter method which returns the <code>byte</code> value of this enum
	 * 
	 * @return the {@link MessageType#value} of this enum
	 */
	final byte getValue() {
		return value;
	}

	/**
	 * Creates a new {@link MessageType}
	 * 
	 * @param value
	 *            the <code>byte</code> value of the {@link MessageType}
	 * @return a new {@link MessageType}
	 * @throws ProtocolException
	 *             if the <code>value</code> parameter is not a valid JRMP
	 *             header message type
	 */
	static final MessageType createMessageType(byte value)
			throws ProtocolException {
		if (value == CALL.getValue()) {
			return CALL;
		} else if (value == PING.getValue()) {
			return PING;
		} else if (value == DGCACK.getValue()) {
			return DGCACK;
		} else {
			throw new ProtocolException("Unrecognized Message Header "
					+ Integer.toHexString(value));
		}
	}
}
