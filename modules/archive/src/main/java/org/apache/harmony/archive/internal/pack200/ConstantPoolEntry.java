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
import java.io.UnsupportedEncodingException;

/**
 * @author alex
 * 
 */
abstract class ConstantPoolEntry extends ClassFileEntry {
	static class Class extends ConstantPoolEntry {
		private int index;

		public String name;

		private UTF8 utf8;

		Class(String name) {
			super(CP_Class);
			this.name = name;
			this.utf8 = new UTF8(name);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final Class other = (Class) obj;
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

		@Override
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

		void writeBody(DataOutputStream dos) throws IOException {
			dos.writeShort(index);
		}
	}
	
	static class UTF8 extends ConstantPoolEntry {
		private String utf8;
		
		UTF8(String utf8) {
			super(CP_UTF8);
			this.utf8 = utf8;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final UTF8 other = (UTF8) obj;
			if (utf8 == null) {
				if (other.utf8 != null)
					return false;
			} else if (!utf8.equals(other.utf8))
				return false;
			return true;
		}

		@Override
		public int hashCode() {
			final int PRIME = 31;
			int result = 1;
			result = PRIME * result + ((utf8 == null) ? 0 : utf8.hashCode());
			return result;
		}

		void writeBody(DataOutputStream dos) throws IOException {
			byte[] bytes;
			try {
				// TODO Check that this is the right UTF-8 for bytes
				if (utf8 == null) {
					bytes = new byte[0];
				} else {
					bytes = utf8.getBytes("UTF-8");
				}
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException("Couldn't convert string " + utf8
						+ " to UTF-8");
			}
			dos.writeShort(bytes.length);
			dos.write(bytes);
		}

	}

	public static final byte CP_Class = 7;

	public static final byte CP_Double = 6;

	public static final byte CP_Fieldref = 9;

	public static final byte CP_Float = 4;

	public static final byte CP_Integer = 3;

	/*
	 * class MemberRef extends ConstantPoolEntry { private int index;
	 * 
	 * Class(String name) { super(CP_Class); index = pool.indexOf(name); }
	 * 
	 * void writeBody(DataOutputStream dos) throws IOException {
	 * dos.writeShort(index); }
	 *  }
	 */

	public static final byte CP_InterfaceMethodref = 11;

	public static final byte CP_Long = 5;

	public static final byte CP_Methodref = 10;

	public static final byte CP_NameAndType = 12;

	public static final byte CP_String = 8;

	public static final byte CP_UTF8 = 1;

	byte tag;

	ConstantPoolEntry(byte tag) {
		this.tag = tag;
	}

	public abstract boolean equals(Object obj);

	public byte getTag() {
		return tag;
	}

	public abstract int hashCode();

	public void write(DataOutputStream dos) throws IOException {
		dos.writeByte(tag);
		writeBody(dos);
	}

	abstract void writeBody(DataOutputStream dos) throws IOException;
}
