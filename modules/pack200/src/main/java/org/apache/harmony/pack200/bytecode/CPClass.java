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


public class CPClass extends ConstantPoolEntry {
	private int index;

	public String name;

	private CPUTF8 utf8;

	public   CPClass(String name) {
		super(ConstantPoolEntry.CP_Class);
		this.name = name;
		this.domain = ClassConstantPool.DOMAIN_CLASSREF;
		this.utf8 = new CPUTF8(name, ClassConstantPool.DOMAIN_NORMALASCIIZ);
	}

	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		final CPClass other = (CPClass) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (utf8 == null) {
			if (other.utf8 != null)
				return false;
		} else if (!utf8.equals(other.utf8))
			return false;
		return true;
	}

	protected ClassFileEntry[] getNestedClassFileEntries() {
		return new ClassFileEntry[] { utf8, };
	}

	
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((name == null) ? 0 : name.hashCode());
		result = PRIME * result + ((utf8 == null) ? 0 : utf8.hashCode());
		return result;
	}

	protected void resolve(ClassConstantPool pool) {
		super.resolve(pool);
		index = pool.indexOf(utf8);
	}

	public String toString() {
		return "Class: " + getName();
	}

	public String getName() {
		return name;
	}
	
	protected void writeBody(DataOutputStream dos) throws IOException {
		dos.writeShort(index);
	}
	
   public String comparisonString() {
        return getName();
   }

}