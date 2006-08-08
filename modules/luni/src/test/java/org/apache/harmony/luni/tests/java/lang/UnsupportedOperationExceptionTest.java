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

package org.apache.harmony.luni.tests.java.lang;

import java.util.AbstractCollection;
import java.util.Iterator;

import tests.util.SerializationTester;

public class UnsupportedOperationExceptionTest extends junit.framework.TestCase {

	private static final String SERIALIZATION_FILE_NAME =
		"serialization/java/lang/UnsupportedOperationException.ser"; //$NON-NLS-1$
	
	/**
	 * @tests java.lang.UnsupportedOperationException#UnsupportedOperationException()
	 */
	public void test_Constructor() {
		// Test for method java.lang.UnsupportedOperationException()
		class UnsupportedCollection extends AbstractCollection {
			public int size() {
				return 0;
			}

			public Iterator iterator() {
				return null;
			}
		}
		try {
			UnsupportedCollection uc = new UnsupportedCollection();
			uc.add(new Object());
		} catch (UnsupportedOperationException e) {
			return;
		} catch (Exception e) {
			fail("Exception during Constructor : " + e.getMessage());
		}
		fail("Constructor failed.");
	}

	/**
	 * @tests java.lang.UnsupportedOperationException#UnsupportedOperationException(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method
		// java.lang.UnsupportedOperationException(java.lang.String)
		try {
			throw new UnsupportedOperationException("HelloWorld");
		} catch (UnsupportedOperationException e) {
			assertEquals("Wrong message given.", 
					"HelloWorld", e.getMessage());
			return;
		} catch (Exception e) {
			fail("Exception during Constructor : " + e.getMessage());
		}
		fail("Constructor failed");
	}
	
	
	/**
	 * @tests serialization/deserilazation.
	 */
	public void test_serialization() throws Exception {
		UnsupportedOperationException srcUnsupportedOperationException = new UnsupportedOperationException();
		UnsupportedOperationException destUnsupportedOperationException = (UnsupportedOperationException) SerializationTester
				.getDeserilizedObject(srcUnsupportedOperationException);
	}

	/**
	 * @tests serialization/deserilazation compatibility with RI.
	 */
	public void test_serializationCompatibility() throws Exception {
		UnsupportedOperationException srcUnsupportedOperationException = new UnsupportedOperationException();
		UnsupportedOperationException destUnsupportedOperationException = (UnsupportedOperationException) SerializationTester
				.readObject(srcUnsupportedOperationException,
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
