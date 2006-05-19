/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
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
package tests.api.java.util;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import tests.util.SerializationTester;

import junit.framework.TestCase;

public class InputMismatchExceptionTest extends TestCase {

	private static final String SERIALIZATION_FILE_NAME = "serialization/java/util/InputMismatchExceptionTest.ser"; //$NON-NLS-1$

	private static final String ERROR_MESSAGE = "for serialization test"; //$NON-NLS-1$

	/**
	 * @tests java.util.InputMismatchException#InputMismatchException()
	 */
	public void test_Constructor() {
		InputMismatchException exception = new InputMismatchException();
		assertNotNull(exception);
		assertTrue(exception instanceof NoSuchElementException);
		assertTrue(exception instanceof Serializable);
	}

	/**
	 * @tests java.util.InputMismatchException#InputMismatchException(String)
	 */
	public void test_ConstructorLjava_lang_String() {
		InputMismatchException exception = new InputMismatchException(ERROR_MESSAGE);
		assertNotNull(exception);
		assertEquals(ERROR_MESSAGE, exception.getMessage());
	}

	/**
	 * @tests serialization/deserilazation.
	 */
	public void testSerialization() throws Exception {
		InputMismatchException exception = new InputMismatchException(ERROR_MESSAGE);
		InputMismatchException deserialedException = (InputMismatchException) SerializationTester
				.getDeserilizedObject(exception);
		assertEquals(exception.getMessage(), deserialedException.getMessage());
		assertEquals(exception.getCause(), deserialedException.getCause());
	}

	/**
	 * @tests serialization/deserilazation compatibility with RI.
	 */
	public void testSerializationCompatibility() throws Exception {
		InputMismatchException exception = new InputMismatchException(ERROR_MESSAGE);
		InputMismatchException deserialedException = (InputMismatchException) SerializationTester
				.readObject(exception, SERIALIZATION_FILE_NAME);
		assertEquals(deserialedException.getMessage(), exception.getMessage());
		assertEquals(deserialedException.getCause(), exception.getCause());
	}
}