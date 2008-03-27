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

public class CPString extends CPConstant {

    private transient int nameIndex;
	private CPUTF8 name;
	private boolean mustStartClassPool = false;

    public CPString(CPUTF8 value) {
		super(ConstantPoolEntry.CP_String, value);
		this.domain = ClassConstantPool.DOMAIN_STRING;
		this.name = value;
	}

    protected void writeBody(DataOutputStream dos) throws IOException {
		dos.writeShort(nameIndex);
	}


	public String toString() {
		return "String: " + getValue();
	}

	/**
	 * Allows the constant pool entries to resolve their nested entries
	 *
	 * @param pool
	 */
	protected void resolve(ClassConstantPool pool) {
		super.resolve(pool);
		nameIndex = pool.indexOf(name);
	}

	protected ClassFileEntry[] getNestedClassFileEntries() {
		return new ClassFileEntry[] { name };
 	}

	public String comparisonString() {
	    return ((CPUTF8)getValue()).underlyingString();
	}

    /**
     * Set whether the receiver must be at the start of the
     * class pool. Anything which is the target of a single-
     * byte ldc (bytecode 18, String) command must be at
     * the start of the class pool.
     *
     * @param b boolean true if the receiver must be at
     * the start of the class pool, otherwise false.
     */
    public void mustStartClassPool(boolean b) {
        mustStartClassPool = b;
    }


    /* (non-Javadoc)
     * @see org.apache.harmony.pack200.bytecode.ClassFileEntry#mustStartClassPool()
     */
    public boolean mustStartClassPool() {
        return mustStartClassPool;
    }
}