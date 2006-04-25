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

import java.util.IllegalFormatWidthException;

import tests.util.SerializationTester;

import junit.framework.TestCase;

public class IllegalFormatWidthExceptionTest extends TestCase {

	private static final String SERIALIZATION_FILE_NAME = "serialization/java/util/IllegalFormatWidthException.ser";

	/**
	 * @tests java.util.IllegalFormatWidthException#IllegalFormatWidthException(int)
	 */
	public void test_illegalFormatWidthException() {
		int width = Integer.MAX_VALUE;
		IllegalFormatWidthException illegalFormatWidthException = new IllegalFormatWidthException(
				width);
		assertEquals(width, illegalFormatWidthException.getWidth());

	}

	/**
	 * @tests java.util.IllegalFormatWidthException#getWidth()
	 */
	public void test_getWidth() {
		int width = 12345;
		IllegalFormatWidthException illegalFormatWidthException = new IllegalFormatWidthException(
				width);
		assertEquals(width, illegalFormatWidthException.getWidth());

	}

	/**
	 * @tests java.util.IllegalFormatWidthException#getMessage()
	 */
	public void test_getMessage() {
		int width = 12345;
		IllegalFormatWidthException illegalFormatWidthException = new IllegalFormatWidthException(
				width);
		assertTrue(null != illegalFormatWidthException.getMessage());

	}

	/**
	 * @tests serialization/deserilazation.
	 */
	public void test_serialization() throws Exception {
		int width = 12345;
		IllegalFormatWidthException srcIllegalFormatWidthException = new IllegalFormatWidthException(
				width);
		IllegalFormatWidthException destIllegalFormatWidthException = (IllegalFormatWidthException) SerializationTester
				.getDeserilizedObject(srcIllegalFormatWidthException);
		assertEquals(srcIllegalFormatWidthException.getWidth(),
				destIllegalFormatWidthException.getWidth());

	}

	/**
	 * @tests serialization/deserilazation compatibility with RI.
	 */
	public void test_serializationCompatibility() throws Exception {
		int width = 12345;
		IllegalFormatWidthException srcIllegalFormatWidthException = new IllegalFormatWidthException(
				width);
		IllegalFormatWidthException destIllegalFormatWidthException = (IllegalFormatWidthException) SerializationTester
				.readObject(srcIllegalFormatWidthException,
						SERIALIZATION_FILE_NAME);
		assertEquals(srcIllegalFormatWidthException.getWidth(),
				destIllegalFormatWidthException.getWidth());

	}

}
