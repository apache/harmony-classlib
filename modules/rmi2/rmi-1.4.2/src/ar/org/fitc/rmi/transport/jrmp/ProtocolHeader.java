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

import java.util.Arrays;

import ar.org.fitc.rmi.transport.ProtocolException;

/*
 * NOTE: 
 * This class has been modified in order to support 
 * Java VM 1.4.2 using the javac's target jsr14 
 */

/**
 * This Enumerated Type describes the possible protocol headers defined by the 
 * JRMP specification.
 * 
 * @author Gustavo Petri
 * 
 */
public class ProtocolHeader {

	/**
	 * The <code>byte[]</code> sequence corresponding to the JRMP protocol initial header
	 */
	public final static ProtocolHeader JRMI_PROTOCOL_HEADER = 
        new ProtocolHeader(new byte[] {0x4a, 0x52, 0x4d, 0x49}); 

	/**
	 * The <code>byte[]</code> sequence corresponding to the HTTP protocol initial header
	 */
    public final static ProtocolHeader HTTP_PROTOCOL_HEADER = 
        new ProtocolHeader(new byte[] {0x50, 0x4f, 0x53, 0x54});
    
	/**
	 * The internal <code>byte[]</code> value for this enumeration
	 */
    private byte[] value;

	/**
	 * Private constructor, sets the internal {@link ProtocolHeader#value}
	 * 
	 * @param value
	 */
    private ProtocolHeader(byte[] value) {
        this.value = value.clone();
    }

	/**
	 * Getter method which returns the <code>byte[]</code> value of this enumeration
	 * 
	 * @return the {@link ProtocolHeader#value} of this enumeration
	 */
    final byte[] getValue() {
        return value;
    }

	/**
	 * Creates a new {@link ProtocolHeader}
	 * 
	 * @param value
	 *            the <code>byte[]</code> value of the {@link ProtocolHeader}
	 * @return a new {@link ProtocolHeader}
	 * @throws ProtocolException
	 *             if the <code>value</code> parameter is not a valid JRMP
	 *             initial protocol header sequence.
	 */
    public static final ProtocolHeader createProtocolHeader(byte[] value)
            throws ProtocolException {
        if (Arrays.equals(value, JRMI_PROTOCOL_HEADER.getValue())) {
            return JRMI_PROTOCOL_HEADER;
        } else if (Arrays.equals(value, HTTP_PROTOCOL_HEADER.getValue())) {
            return HTTP_PROTOCOL_HEADER;
        } else {
            StringBuilder str = new StringBuilder();
            for (byte b : value) {
                str.append(Integer.toHexString(b) + ", ");
            }
            throw new ProtocolException("Protocol Header not recognized: "
                    + str.toString());
        }
    }
}
