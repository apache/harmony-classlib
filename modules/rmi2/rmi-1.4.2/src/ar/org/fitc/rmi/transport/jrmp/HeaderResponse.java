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
 * An enum representing the valid header responses of the JRMP protocol
 * 
 * @author Gustavo Petri
 * 
 */
class HeaderResponse {

	/**
	 * A 0x4e byte representing the protocol acknowledge value
	 */
	static final HeaderResponse PROTOCOL_ACK = 
        new HeaderResponse((byte) 0x4e);
    
	/**
	 * A 0x4f byte representing the protocol not supported value
	 */
    static final HeaderResponse PROTOCOL_NOT_SUPPORTED = 
        new HeaderResponse((byte) 0x4f);

	/**
	 * The current value
	 */
	private byte value;

	/**
	 * Private constructor, sets the internal {@link HeaderResponse#value} 
	 * 
	 * @param value
	 *            the value for this enum
	 */
	private HeaderResponse(byte value) {
		this.value = value;
	}

	/**
	 * Getter method which returns the <code>byte</code> value of this enum
	 * 
	 * @return the {@link HeaderResponse#value} of this enum
	 */
	final byte getValue() {
		return value;
	}

	/**
	 * Creates a new {@link HeaderResponse}
	 * 
	 * @param value
	 *            the <code>byte</code> value of the {@link HeaderResponse}
	 * @return the new {@link HeaderResponse}
	 * @throws ProtocolException
	 *             if the <code>value</code> parameters is not a valid JRMP
	 *             header response value
	 */
	static final HeaderResponse createHeaderResponse(byte value)
			throws ProtocolException {
		if (value == PROTOCOL_ACK.getValue()) {
			return PROTOCOL_ACK;
		} else if (value == PROTOCOL_NOT_SUPPORTED.getValue()) {
			return PROTOCOL_NOT_SUPPORTED;
		} else {
			throw new ProtocolException("Unrecognized Header Response: "
					+ Integer.toHexString(value));
		}
	}
}
