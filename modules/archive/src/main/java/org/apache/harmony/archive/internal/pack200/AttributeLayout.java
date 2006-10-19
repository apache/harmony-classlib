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
import org.apache.harmony.archive.internal.pack200.Segment.SegmentConstantPool;

public class AttributeLayout {
	static class Key {
		public Key(String name, int context) throws Pack200Exception {
			if (name == null || name.length() == 0)
				throw new Pack200Exception("Cannot have an unnamed layout");
			if (context != CONTEXT_CLASS && context != CONTEXT_CODE
					&& context != CONTEXT_FIELD && context != CONTEXT_METHOD)
				throw new Pack200Exception("Attribute context out of range: "
						+ context);
			this.name = name;
			this.context = context;

		}

		private int context;

		private String name;

		@Override
		public int hashCode() {
			final int PRIME = 31;
			int result = 1;
			result = PRIME * result + context;
			result = PRIME * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final Key other = (Key) obj;
			if (context != other.context)
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

		public String toString() {
			return contextNames[context] + ": " + name;
		}

	}

	private String layout;

	Key key;

	private long mask;

	public static final int CONTEXT_CODE = 1 << 4;

	public static final int CONTEXT_CLASS = 1 << 0;

	public static final int CONTEXT_FIELD = 1 << 2;

	public static final int CONTEXT_METHOD = 1 << 3;

	private static final String[] contextNames = { "Class", "Field", "Method",
			"Code", };

	public Codec getCodec() {
		if (layout.indexOf("O") >= 0) {
			return Codec.BRANCH5;
		} else if (layout.indexOf("P") >= 0) {
			return Codec.BCI5;
		} else if (layout.indexOf("S") >= 0 && layout.indexOf("KS") < 0
				&& layout.indexOf("RS") < 0) {
			return Codec.SIGNED5;
		} else if (layout.indexOf("B") >= 0) {
			return Codec.BYTE1;
		}
		/*
		 * TODO Add this as a test (and don't commit since this is copyright
		 * text) && ) If the layout contains 'O', use BRANCH5. Otherwise, if the
		 * layout contains 'P', use BCI5. Otherwise, if the layout contains 'S'
		 * but not 'KS' or 'RS', use SIGNED5. Otherwise, if the layout contains
		 * 'B', use BYTE1. For all other layouts use UNSIGNED5.
		 */
		else {
			return Codec.UNSIGNED5;
		}
	}

	public Object getValue(long value, Segment segment) throws Pack200Exception {
		if (layout.startsWith("R")) {
			// references
			if (layout.indexOf('N') != -1)
				value--;
			SegmentConstantPool pool = segment.getConstantPool();
			if (layout.startsWith("RU")) {
				return pool.getValue(SegmentConstantPool.UTF_8, value);
			} else if (layout.startsWith("RS")) {
				return pool.getValue(SegmentConstantPool.SIGNATURE, value);
			}
		}
		throw new Pack200Exception("Unknown layout encoding: " + layout);
	}

	public AttributeLayout(String name, int context, String layout, int index)
			throws Pack200Exception {
		super();
		this.key = new Key(name, context);
		if (index >= 0) {
			this.mask = 1L << index;
		} else {
			this.mask = 0;
		}
		if (layout == null) // || layout.length() == 0)
			throw new Pack200Exception("Cannot have a null layout");
		this.layout = layout;
	}

	public boolean isCode() {
		return key.context == CONTEXT_CODE;
	}

	public boolean isClass() {
		return key.context == CONTEXT_CLASS;
	}

	public boolean isMethod() {
		return key.context == CONTEXT_METHOD;
	}

	public boolean isField() {
		return key.context == CONTEXT_FIELD;
	}

	public int hashCode() {
		return key.hashCode();
	}

	public boolean matches(long value) {
		return (value & mask) != 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final AttributeLayout other = (AttributeLayout) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (layout == null) {
			if (other.layout != null)
				return false;
		} else if (!layout.equals(other.layout))
			return false;
		return true;
	}

	public String toString() {
		return key.toString();
	}

	public String getLayout() {
		return layout;
	}

}
