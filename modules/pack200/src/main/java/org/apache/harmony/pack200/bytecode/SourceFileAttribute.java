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

public class SourceFileAttribute extends Attribute {

	private CPUTF8 name;
	private int nameIndex;
    private static final CPUTF8 attributeName = new CPUTF8(
            "SourceFile", ClassConstantPool.DOMAIN_ATTRIBUTEASCIIZ); //$NON-NLS-1$


	public SourceFileAttribute(CPUTF8 name) {
		super(attributeName);
		this.name = name;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		final SourceFileAttribute other = (SourceFileAttribute) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

    /* (non-Javadoc)
     * @see org.apache.harmony.pack200.bytecode.Attribute#isSourceFileAttribute()
     */
    public boolean isSourceFileAttribute() {
        return true;
    }

	protected int getLength() {
		return 2;
	}

	protected ClassFileEntry[] getNestedClassFileEntries() {
		return new ClassFileEntry[] { getAttributeName(), name };
	}

	public int hashCode() {
		final int PRIME = 31;
		int result = super.hashCode();
		result = PRIME * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	protected void resolve(ClassConstantPool pool) {
		super.resolve(pool);
		nameIndex = pool.indexOf(name);
	}

	public String toString() {
		return "SourceFile: " + name;
	}

	protected void writeBody(DataOutputStream dos) throws IOException {
		dos.writeShort(nameIndex);
	}
}