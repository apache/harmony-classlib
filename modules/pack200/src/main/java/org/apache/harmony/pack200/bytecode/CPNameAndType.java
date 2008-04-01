/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.harmony.pack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.harmony.pack200.SegmentUtils;

public class CPNameAndType extends ConstantPoolEntry {

	CPUTF8 descriptor;

	transient int descriptorIndex;

	CPUTF8 name;

	transient int nameIndex;

	protected String cachedComparisonString = null;

	public CPNameAndType(CPUTF8 name, CPUTF8 descriptor, int domain) {
		super(ConstantPoolEntry.CP_NameAndType);
		this.name = name;
		this.descriptor = descriptor;
        this.domain = domain;
	}

	protected ClassFileEntry[] getNestedClassFileEntries() {
		return new ClassFileEntry[] { name, descriptor };
	}

	protected void resolve(ClassConstantPool pool) {
		super.resolve(pool);
		descriptorIndex = pool.indexOf(descriptor);
		nameIndex = pool.indexOf(name);
	}

	/*
	 * field_info { u2 access_flags; u2 name_index; u2 descriptor_index; u2
	 * attributes_count; attribute_info attributes[attributes_count]; }
	 */

	protected void writeBody(DataOutputStream dos) throws IOException {
		dos.writeShort(nameIndex);
		dos.writeShort(descriptorIndex);
	}


	public String toString() {
		return "NameAndType: " + name + "(" + descriptor + ")";
	}


	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((descriptor == null) ? 0 : descriptor.hashCode());
		result = PRIME * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final CPNameAndType other = (CPNameAndType) obj;
		if (descriptor == null) {
			if (other.descriptor != null)
				return false;
		} else if (!descriptor.equals(other.descriptor))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * Answers the invokeinterface count argument when the
	 * receiver is treated as an invokeinterface target.
	 * This value is not meaningful if the receiver is not
     * an invokeinterface target.
	 * @return count
	 */
	public int invokeInterfaceCount() {
	    return 1 + SegmentUtils.countInvokeInterfaceArgs(descriptor.underlyingString());
	}


    /* (non-Javadoc)
     * @see org.apache.harmony.pack200.bytecode.ConstantPoolEntry#comparisonString()
     */
    public String comparisonString() {
        // First come those things which don't have an
        // associated signature. Then come the native signatures,
        // then finally the class signatures.
        // TODO: I think Character.MAX_VALUE is no longer the
        // biggest character, what with the weird codepage thing
        // going on. How to sort these things so that even if
        // they're in some oddball codepage they'll still end
        // up sorted correctly?
        if(cachedComparisonString != null) {
            return cachedComparisonString;
        }
        String descriptorString = descriptor.underlyingString();
        StringBuffer comparisonBuffer = new StringBuffer();
        if((descriptorString.indexOf("(")) == -1) {
            // it's a variable reference
            comparisonBuffer.append(descriptor.underlyingString());
        } else {
            // it's a signature. Append something that will
            // make the comparison buffer bigger than all
            // non-signature references.
            comparisonBuffer.append(Character.MAX_VALUE);
            // do the natives first
            if(descriptorString.length() <= 4) {
                // it's a native signature
                comparisonBuffer.append(descriptor.underlyingString());
            } else {
                // it's a non-native signature. Append something
                // that will make the comparison buffer bigger
                // than all native signature references.
                comparisonBuffer.append(Character.MAX_VALUE);
                comparisonBuffer.append(descriptor.underlyingString());
            }
        }
        comparisonBuffer.append(name.underlyingString());
        cachedComparisonString = comparisonBuffer.toString();
        return cachedComparisonString;
    }
}