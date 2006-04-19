/* Copyright 1998, 2006 The Apache Software Foundation or its licensors, as applicable
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

import tests.util.SerializationTester;

public class IllegalStateExceptionTest extends junit.framework.TestCase {

	private static final String SERIALIZATION_FILE_NAME =
		"/serialization/java/lang/IllegalStateException.ser"; //$NON-NLS-1$
	
	/**
	 * @tests java.lang.IllegalStateException#IllegalStateException()
	 */
	public void test_Constructor() {
		// Test for method java.lang.IllegalStateException()
		IllegalStateException ill = new IllegalStateException();
		assertTrue("failed to create an instance of illegalStateException", ill
				.getMessage() == null);	
	}

	/**
	 * @tests java.lang.IllegalStateException#IllegalStateException(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method java.lang.IllegalStateException(java.lang.String)
		IllegalStateException ill = new IllegalStateException(
				"testing illState exception");
		assertTrue(
				"failed to create instance of illegalStateException(string)",
				ill.getMessage().equals("testing illState exception"));
	}

	/**
	 * @tests serialization/deserilazation.
	 */
	public void test_serialization() throws Exception {
		IllegalStateException srcIllegalStateException = new IllegalStateException();
		IllegalStateException destIllegalStateException = (IllegalStateException) SerializationTester
				.getDeserilizedObject(srcIllegalStateException);
	}

	/**
	 * @tests serialization/deserilazation compatibility with RI.
	 */
	public void test_serializationCompatibility() throws Exception {
		IllegalStateException srcIllegalStateException = new IllegalStateException();
		IllegalStateException destIllegalStateException = (IllegalStateException) SerializationTester
				.readObject(srcIllegalStateException,
						SERIALIZATION_FILE_NAME);
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
