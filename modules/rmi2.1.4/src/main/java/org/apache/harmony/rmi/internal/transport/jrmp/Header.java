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

import org.apache.harmony.rmi.internal.transport.ProtocolException;

/**
 * A class representing the Header of the JRMI protocol.
 * 
 * @author Gustavo Petri
 */
public class Header {

	/**
	 * ID for serialization purposes
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * A byte array representing the version of the JRMI protocol
	 */
	static final byte[] JRMI_PROTOCOL_VERSION = new byte[] { 0x00, 0x01 };

	/**
	 * A {@link org.apache.harmony.rmi.internal.transport.jrmp.ProtocolType} object that
	 * indicates the header type
	 */
	private ProtocolType type;

	/**
	 * Constructor. Does nothing
	 */
	public Header() {
		super();
	}

	/**
	 * Constructor. Sets {@link Header#type type} with the parameter
	 * <code>type</code>
	 * 
	 * @param type
	 */
	public Header(ProtocolType type) {
		this.type = type;
	}

	/**
	 * Returns the protocol type.
	 * 
	 * @return the wrapped {@link Header#type type}
	 */
	public ProtocolType getProtocolType() {
		return type;
	}

	/**
	 * Writes the header representation on the stream.
	 * 
	 * @param out
	 *            the output stream to write
	 * @throws IOException
	 *             if an exception occurs when writing on the stream
	 */
	public final void writeExternal(DataOutputStream out) throws IOException {
		out.write(ProtocolHeader.JRMI_PROTOCOL_HEADER.getValue());
		out.write(JRMI_PROTOCOL_VERSION);
		out.write(type.getValue());
	}

	/**
	 * Reads a header from the input stream, and sets the {@link Header#type}
	 * attribute to the received protocol type.
	 * 
	 * @param in
	 *            the input stream used to read the data
	 * @throws IOException
	 *             if an exception occurs reading the header bytes
	 */
	public final void readExternal(DataInputStream in) throws IOException {

		if (type.equals(ProtocolType.SINGLE_OP)) {
			for (byte b : ProtocolHeader.JRMI_PROTOCOL_HEADER.getValue()) {
				if (in.readByte() != b) {
					throw new ProtocolException(
							"Error reading JRMP protocol header");
				}
			}
		}
		for (byte b : JRMI_PROTOCOL_VERSION) {
			if (in.readByte() != b) {
				throw new ProtocolException(
						"Error reading JRMP protocol header");
			}
		}
		type = ProtocolType.createProtocolType(in.readByte());
	}
}
