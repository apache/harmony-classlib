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

/*
 * NOTE: 
 * This class has been modified in order to support 
 * Java VM 1.4.2 using the javac's target jsr14 
 */

/**
 * This Enumerated Type describes the possible return types received from a RMI
 * Server.
 * 
 * @author Gustavo Petri
 * 
 */
class MessageResponse {

	/**
	 * A 0x51 <code>byte</code> representing a return message
	 */
	static final MessageResponse RETURN = new MessageResponse((byte) 0x51);

	/**
	 * A 0x53 <code>byte</code> representing a PingAck message
	 */
    static final MessageResponse PING_ACK = new MessageResponse((byte) 0x53);

	/**
	 * The internal <code>byte</code> value for this enum
	 */
	private byte value;

	/**
	 * Private constructor, sets the internal {@link MessageResponse#value}
	 * 
	 * @param value
	 */
	private MessageResponse(byte value) {
		this.value = value;
	}

	/**
	 * Getter method which returns the <code>byte</code> value of this enum
	 * 
	 * @return the {@link MessageResponse#value} of this enumeration
	 */
	byte getValue() {
		return value;
	}

	/**
	 * Creates a new {@link MessageResponse}
	 * 
	 * @param value
	 *            the <code>byte</code> value of the {@link MessageResponse}
	 * @return a new {@link MessageResponse}
	 * @throws IllegalArgumentException
	 *             if the <code>value</code> parameter is not a valid JRMP
	 *             return type.
	 */
	static MessageResponse createMessageResponse(byte value) {
		if (value == RETURN.getValue()) {
			return RETURN;
		} else if (value == PING_ACK.getValue()) {
			return PING_ACK;
		} else {
			throw new IllegalArgumentException("Unrecognized value: "
					+ Integer.toHexString(value));
		}
	}
}
