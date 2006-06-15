/* Copyright 1998, 2005 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tests.api.java.io;

import java.io.ObjectStreamClass;
import java.io.ObjectStreamField;
import java.io.Serializable;

public class ObjectStreamFieldTest extends junit.framework.TestCase {

	static class DummyClass implements Serializable {
		private static final long serialVersionUID = 999999999999998L;

		long bam = 999L;

		int ham = 9999;

		int sam = 8888;

		Object hola = new Object();

		public static long getUID() {
			return serialVersionUID;
		}
	}

	ObjectStreamClass osc;

	ObjectStreamField hamField;

	ObjectStreamField samField;

	ObjectStreamField bamField;

	ObjectStreamField holaField;

	/**
	 * @tests java.io.ObjectStreamField#ObjectStreamField(java.lang.String,
	 *        java.lang.Class)
	 */
	public void test_ConstructorLjava_lang_StringLjava_lang_Class() {
		// Test for method java.io.ObjectStreamField(java.lang.String,
		// java.lang.Class)
		assertTrue("Used to test", true);
	}

	/**
	 * @tests java.io.ObjectStreamField#compareTo(java.lang.Object)
	 */
	public void test_compareToLjava_lang_Object() {
		// Test for method int
		// java.io.ObjectStreamField.compareTo(java.lang.Object)
		assertTrue("Object compared to int did not return > 0", holaField
				.compareTo(hamField) > 0);
		assertEquals("Int compared to itself did not return 0", 0, hamField
				.compareTo(hamField));
		assertTrue("(Int)ham compared to (Int)sam did not return < 0", hamField
				.compareTo(samField) < 0);
	}

	/**
	 * @tests java.io.ObjectStreamField#getName()
	 */
	public void test_getName() {
		// Test for method java.lang.String java.io.ObjectStreamField.getName()
		assertEquals("Field did not return correct name", "hola", holaField.getName()
				);
	}

	/**
	 * @tests java.io.ObjectStreamField#getOffset()
	 */
	public void test_getOffset() {
		// Test for method int java.io.ObjectStreamField.getOffset()
		ObjectStreamField[] osfArray;
		osfArray = osc.getFields();
		assertTrue("getOffset did not return reasonable values", osfArray[0]
				.getOffset() != osfArray[1].getOffset());
		assertEquals("getOffset for osfArray[0].getOffset() did not return 0",
				0, osfArray[0].getOffset());
		assertEquals("osfArray[1].getOffset() did not return	8", 8, osfArray[1]
				.getOffset());
		assertEquals("osfArray[2].getOffset() did not return 12", 12, osfArray[2]
				.getOffset());
	}

	/**
	 * @tests java.io.ObjectStreamField#getType()
	 */
	public void test_getType() {
		// Test for method java.lang.Class java.io.ObjectStreamField.getType()
		assertTrue("getType on an Object field did not answer Object",
				holaField.getType().equals(Object.class));
	}

	/**
	 * @tests java.io.ObjectStreamField#getTypeCode()
	 */
	public void test_getTypeCode() {
		// Test for method char java.io.ObjectStreamField.getTypeCode()
		assertEquals("getTypeCode on an Object field did not answer 'L'",
				'L', holaField.getTypeCode());
		assertEquals("getTypeCode on a long field did not answer 'J'", 'J', bamField
				.getTypeCode());
	}

	/**
	 * @tests java.io.ObjectStreamField#getTypeString()
	 */
	public void test_getTypeString() {
		assertTrue("getTypeString returned: " + holaField.getTypeString(),
				holaField.getTypeString().indexOf("Object") >= 0);
		assertNull("Primitive types' strings should be null", hamField.getTypeString());
	}

	/**
	 * @tests java.io.ObjectStreamField#toString()
	 */
	public void test_toString() {
		// Test for method java.lang.String java.io.ObjectStreamField.toString()
		assertTrue("toString on a long returned: " + bamField.toString(),
				bamField.toString().indexOf("bam") >= 0);
	}

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() {
		osc = ObjectStreamClass.lookup(DummyClass.class);
		bamField = osc.getField("bam");
		samField = osc.getField("sam");
		hamField = osc.getField("ham");
		holaField = osc.getField("hola");
	}

	/**
	 * Tears down the fixture, for example, close a network connection. This
	 * method is called after a test is executed.
	 */
	protected void tearDown() {
	}
}
