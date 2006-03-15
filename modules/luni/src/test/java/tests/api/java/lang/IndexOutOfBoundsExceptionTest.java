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

package tests.api.java.lang;

public class IndexOutOfBoundsExceptionTest extends junit.framework.TestCase {

	/**
	 * @tests java.lang.IndexOutOfBoundsException#IndexOutOfBoundsException()
	 */
	public void test_Constructor() {
		// Test for method java.lang.IndexOutOfBoundsException()
		try {
			try {
				byte[] buf = new byte[1];
				byte z = buf[1];
				if (z > 0)
					; // use z to avoid unused var warning
			} catch (IndexOutOfBoundsException e) {
				return;
			}
			fail("Failed to generate Exception");
		} catch (Exception e) {
			fail("Exception during test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.lang.IndexOutOfBoundsException#IndexOutOfBoundsException(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method java.lang.IndexOutOfBoundsException(java.lang.String)
		try {
			try {
				byte[] buf = new byte[1];
				byte z = buf[1];
				if (z > 0)
					; // use z to avoid warning msg
			} catch (IndexOutOfBoundsException e) {
				return;
			}
			fail("Failed to generate Exception");
		} catch (Exception e) {
			fail("Exception during test : " + e.getMessage());
		}
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
