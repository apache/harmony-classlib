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

/**
 * This Enumerated Type describes the possible Protocol types defined by the
 * JRMP specification.
 * 
 * @author Gustavo Petri
 * 
 */
public enum ProtocolType {

	/**
	 * A 0x4c <code>byte</code> representing the SingleOp protocol
	 */
	SINGLE_OP((byte) 0x4c),

	/**
	 * A 0x4b <code>byte</code> representing the Stream protocol
	 */
	STREAM((byte) 0x4b),

	/**
	 * A 0x4d <code>byte</code> representing the Multiplex protocol
	 */
	MULTIPLEX((byte) 0x4d);

	/**
	 * The internal <code>byte</code> value for this enum
	 */
	private byte value;

	/**
	 * Private constructor, sets the internal {@link ProtocolType#value}
	 * 
	 * @param value
	 */
	private ProtocolType(byte value) {
		this.value = value;
	}

	/**
	 * Getter method which returns the <code>byte</code> value of this enum
	 * 
	 * @return the {@link ProtocolType#value} of this enum
	 */
	final byte getValue() {
		return value;
	}

	/**
	 * Creates a new {@link ProtocolType}
	 * 
	 * @param value
	 *            the <code>byte</code> value of the {@link ProtocolType}
	 * @return a new {@link ProtocolType}
	 * @throws IllegalArgumentException
	 *             if the <code>value</code> parameter is not a valid JRMP
	 *             protocol type
	 */
	public static final ProtocolType createProtocolType(byte value) {
		if (value == SINGLE_OP.getValue()) {
			return SINGLE_OP;
		} else if (value == STREAM.getValue()) {
			return STREAM;
		} else if (value == MULTIPLEX.getValue()) {
			return MULTIPLEX;
		} else {
			throw new IllegalArgumentException("Unrecognized value");
		}
	}
}
