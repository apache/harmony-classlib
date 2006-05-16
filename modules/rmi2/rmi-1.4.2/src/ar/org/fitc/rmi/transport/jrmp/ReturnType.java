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

/*
 * NOTE: 
 * This class has been modified in order to support 
 * Java VM 1.4.2 using the javac's target jsr14 
 */

/**
 * This Enumerated Type describes the possible return message types received
 * from a RMI Server.
 * 
 * @author Gustavo Petri
 * 
 */
class ReturnType {

	/**
	 * A 0x01 <code>byte</code> representing a return message, when the call
	 * execution on the remote server was successful
	 */
	static final ReturnType RETURN_HEADER = new ReturnType((byte) 0x01);

	/**
	 * A 0x02 <code>byte</code> representing a return message, when the call
	 * execution on the remote server threw and exception
	 */
    static final ReturnType EXCEPTION_HEADER = new ReturnType((byte) 0x02);

	/**
	 * The internal <code>byte</code> value for this enum
	 */
	private byte value;

	/**
	 * Private constructor, sets the internal {@link ReturnType#value}
	 * 
	 * @param value
	 */
	private ReturnType(byte value) {
		this.value = value;
	}

	/**
	 * Getter method which returns the <code>byte</code> value of this enum
	 * 
	 * @return the {@link ReturnType#value} of this enumeration
	 */
	final byte getValue() {
		return value;
	}

	/**
	 * Creates a new {@link ReturnType}
	 * 
	 * @param value
	 *            the <code>byte</code> value of the {@link ReturnType}
	 * @return a new {@link ReturnType}
	 * @throws ProtocolException
	 *             if the <code>value</code> parameter is not a valid JRMP
	 *             return message type.
	 */
	final static ReturnType createReturnType(byte value)
			throws ProtocolException {
		if (value == RETURN_HEADER.getValue()) {
			return RETURN_HEADER;
		} else if (value == EXCEPTION_HEADER.getValue()) {
			return EXCEPTION_HEADER;
		} else {
			throw new ProtocolException("Invalid return type byte "
					+ Integer.toHexString(value));
		}
	}
}
