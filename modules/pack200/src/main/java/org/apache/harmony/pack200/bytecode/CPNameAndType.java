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

	public CPNameAndType(String descriptor) {
		super(ConstantPoolEntry.CP_NameAndType);
		int descriptorDomain = ClassConstantPool.DOMAIN_UNDEFINED;
		int colon = descriptor.indexOf(':');
		String nameString = descriptor.substring(0,colon);
		String descriptorString = descriptor.substring(colon+1);
		// For some reason, descriptors which have just plain
		// native types are stored in DOMAIN_NORMALASCIIZ rather
		// than in DOMAIN_SIGNATUREASCIIZ. This might indicate
		// that DOMAIN_SIGNATUREASCIIZ is poorly named.
		boolean nativeDescriptor = true;
		for(int index=0; index < descriptorString.length(); index++) {
		    char currentChar = descriptorString.charAt(index);
		    if(Character.isLetter(currentChar)) {
		        if(currentChar == 'L') {
		            nativeDescriptor = false;
		        }
		        break;
		    }
		}
		this.domain = ClassConstantPool.DOMAIN_NAMEANDTYPE;
		this.name = new CPUTF8(nameString, ClassConstantPool.DOMAIN_NORMALASCIIZ);
		if((nameString.equals("<init>")) || (nameString.equals("<clinit>")) || nativeDescriptor ) {
		    // Signatures for init methods are stored with the init methods.
		    // Not sure why. Similarly, native signatures are stored
		    // there as well.
		    descriptorDomain = ClassConstantPool.DOMAIN_NORMALASCIIZ;
		} else {
		    descriptorDomain = ClassConstantPool.DOMAIN_SIGNATUREASCIIZ;
		}
		this.descriptor = new CPUTF8(descriptorString, descriptorDomain);
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
}