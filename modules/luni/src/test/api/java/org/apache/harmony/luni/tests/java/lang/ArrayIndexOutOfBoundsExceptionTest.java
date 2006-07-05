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

package org.apache.harmony.luni.tests.java.lang;

public class ArrayIndexOutOfBoundsExceptionTest extends
		junit.framework.TestCase {

	/**
	 * @tests java.lang.ArrayIndexOutOfBoundsException#ArrayIndexOutOfBoundsException()
	 */
	public void test_Constructor() {
		// Test for method java.lang.ArrayIndexOutOfBoundsException()
		int r = 0;
		try {
			byte[] b = new byte[1];
			byte z = b[2];
			if (z > 0)
				; // use z so we don't get an unused variable warning
		} catch (ArrayIndexOutOfBoundsException e) {
			r = 1;
		}
		assertEquals("failed to generate ArrayIndexOutOfBoundsException", 1, r);
	}

	/**
	 * @tests java.lang.ArrayIndexOutOfBoundsException#ArrayIndexOutOfBoundsException(int)
	 */
	public void test_ConstructorI() {
		try {
			if (true)
				throw new ArrayIndexOutOfBoundsException(-1);
		} catch (ArrayIndexOutOfBoundsException e) {
			assertTrue(
					"toString of ArrayIndexOutOfBoundsException did not reveal offending position",
					e.toString().indexOf("-1", 0) >= 0);
		}
	}

	/**
	 * @tests java.lang.ArrayIndexOutOfBoundsException#ArrayIndexOutOfBoundsException(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method
		// java.lang.ArrayIndexOutOfBoundsException(java.lang.String)
		int r = 0;
		try {
			byte[] b = new byte[1];
			byte z = b[2];
			if (z > 0)
				; // use z so we don't get an unused variable warning
		} catch (ArrayIndexOutOfBoundsException e) {
			r = 1;
		}
		assertEquals("failed to generate ArrayIndexOutOfBoundsException", 1, r);

	}

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() {
	}

	/**
	 * Tears down the fixture, for example, close a network connection. This
	 * method is called after a test is executed.
	 */
	protected void tearDown() {
	}
}
