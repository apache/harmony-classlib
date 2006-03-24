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

public class IllegalArgumentExceptionTest extends junit.framework.TestCase {

	class TestThread implements Runnable {
		public void run() {
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
				System.out.println("Unable to start thread");
			}
		}
	}

	/**
	 * @tests java.lang.IllegalArgumentException#IllegalArgumentException()
	 */
	public void test_Constructor() {
		// Test for method java.lang.IllegalArgumentException()
		IllegalArgumentException ill = new IllegalArgumentException();
		assertTrue("failed to create an instance of illegalArgumentException",
				ill.getMessage() == null);
		try {
			try {
				new java.io.ByteArrayOutputStream(-12);
			} catch (IllegalArgumentException e) {
				return;
			}
			fail("Failed to generate Exception");
		} catch (Exception e) {
			fail("Exception during test : " + e.getMessage());
		}
	}

	/**
	 * @tests java.lang.IllegalArgumentException#IllegalArgumentException(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method java.lang.IllegalArgumentException(java.lang.String)
		IllegalArgumentException ill = new IllegalArgumentException(
				"testing illArg exception");
		assertTrue(
				"failed to create instance of illegalArgumentException(string)",
				ill.getMessage().equals("testing illArg exception"));
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
