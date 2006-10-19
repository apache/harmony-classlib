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
package org.apache.harmony.archive.internal.pack200;
//NOTE: Do not use generics in this code; it needs to run on JVMs < 1.5
//NOTE: Do not extract strings as messages; this code is still a work-in-progress
//NOTE: Also, don't get rid of 'else' statements for the hell of it ...
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.harmony.archive.internal.pack200.ConstantPoolEntry.UTF8;


public abstract class ClassFileEntry {
	static abstract class Attribute extends ClassFileEntry {
		public Attribute(String attributeName) {
			this.attributeName = new UTF8(attributeName);
		}
		private UTF8 attributeName;
		private int attributeNameIndex;
		protected UTF8 getAttributeName() {
			return attributeName;
		}
		protected void resolve(ClassConstantPool pool) {
			super.resolve(pool);
			attributeNameIndex = pool.indexOf(attributeName);
		}
		protected abstract int getLength();
		public void write(DataOutputStream dos) throws IOException {
			dos.writeShort(attributeNameIndex);
			dos.writeInt(getLength());
			writeBody(dos);
		}
		protected abstract void writeBody(DataOutputStream dos) throws IOException;
		
	}
	static class SourceFile extends Attribute {
		private UTF8 name;
		public int nameIndex;
		public SourceFile(String name) {
			super("SourceFile"); //$NON-NLS-1$
			this.name = new UTF8(name);
		}

		protected ClassFileEntry[] getNestedClassFileEntries() {
			return new ClassFileEntry[] { getAttributeName(), name };
		}
		protected void resolve(ClassConstantPool pool) {
			super.resolve(pool);
			nameIndex = pool.indexOf(name);
		}
		protected void writeBody(DataOutputStream dos) throws IOException {
			dos.writeShort(nameIndex);			
		}

		@Override
		protected int getLength() {
			return 2;
		}
	}
	private static final ClassFileEntry[] NONE = new ClassFileEntry[0];
	protected ClassFileEntry[] getNestedClassFileEntries() {
		return NONE;
	}
	/**
	 * Allows the constant pool entries to resolve their nested entries
	 * 
	 * @param pool
	 */
	protected void resolve(ClassConstantPool pool) {
	}
	public abstract void write(DataOutputStream dos) throws IOException;

}
