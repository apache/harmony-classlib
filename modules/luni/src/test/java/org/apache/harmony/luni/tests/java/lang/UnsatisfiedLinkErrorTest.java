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

public class UnsatisfiedLinkErrorTest extends junit.framework.TestCase {

	/**
	 * @tests java.lang.UnsatisfiedLinkError#UnsatisfiedLinkError()
	 */
	public void test_Constructor() {
		// SM.
		try {
			if (true)
				throw new UnsatisfiedLinkError();
		} catch (UnsatisfiedLinkError e) {
			assertNull("Initializer failed.", e.getMessage());
			assertEquals("To string failed.", 
					"java.lang.UnsatisfiedLinkError", e.toString());
		}
	}

	/**
	 * @tests java.lang.UnsatisfiedLinkError#UnsatisfiedLinkError(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// TODO : No method throws this error.
		boolean exception = false;
		try {
			Runtime.getRuntime().loadLibrary("Hello World89797");
		} catch (UnsatisfiedLinkError e) {
			assertNotNull("Does not set message", e.getMessage());
			exception = true;
		}
		assertTrue("Does not throw UnsatisfiedLinkError", exception);

		UnsatisfiedLinkError err = new UnsatisfiedLinkError("my message");
		assertEquals("Incorrect message", "my message", err.getMessage());
	}

	protected void setUp() {
	}

	protected void tearDown() {
	}
}
