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

public class ExceptionInInitializerErrorTest extends junit.framework.TestCase {

	/**
	 * @tests java.lang.ExceptionInInitializerError#ExceptionInInitializerError()
	 */
	public void test_Constructor() {
		try {
			if (true)
				throw new ExceptionInInitializerError();
		} catch (ExceptionInInitializerError e) {
			assertTrue("Initializer failed." + e.toString(), e.toString()
					.equals("java.lang.ExceptionInInitializerError"));
			assertNull("Initializer failed.", e.getException());
			assertNull("Initializer failed.", e.getMessage());
			return;
		}
		fail("Constructor failed.");
	}

	/**
	 * @tests java.lang.ExceptionInInitializerError#ExceptionInInitializerError(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		try {
			if (true) {
				throw new ExceptionInInitializerError("HelloWorld");
			}
			fail("Constructor failed.");
		} catch (ExceptionInInitializerError e) {
			assertTrue("Initializer failed: " + e.getMessage(), e.getMessage()
					.equals("HelloWorld"));
		}
	}

	/**
	 * @tests java.lang.ExceptionInInitializerError#ExceptionInInitializerError(java.lang.Throwable)
	 */
	public void test_ConstructorLjava_lang_Throwable() {
		try {
			if (true) {
				throw new ExceptionInInitializerError(
						new ExceptionInInitializerError("HelloWorld"));
			}
			fail("Constructor failed.");
		} catch (ExceptionInInitializerError e) {
			assertNull("Initializer failed." + e.getMessage(),
					e.getMessage());
			assertTrue("Initializer failed." + e.toString(),
					e.getException() != null
							&& e.getException().getMessage().equals(
									"HelloWorld"));
		}
	}

	/**
	 * @tests java.lang.ExceptionInInitializerError#getException()
	 */
	public void test_getException() {
		assertTrue("Already tested.", true);
	}

	protected void setUp() {
	}

	protected void tearDown() {
	}
}
