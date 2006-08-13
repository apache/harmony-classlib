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

package org.apache.harmony.luni.tests.java.util;

import java.util.IllegalFormatCodePointException;

import tests.util.SerializationTester;

import junit.framework.TestCase;

public class IllegalFormatCodePointExceptionTest extends TestCase {

	private static final String SERIALIZATION_FILE_NAME = "serialization/java/util/IllegalFormatCodePointException.ser";

	/**
	 * @tests java.util.IllegalFormatCodePointException.IllegalFormatCodePointException(int)
	 */
	public void test_illegalFormatCodePointException() {
		IllegalFormatCodePointException illegalFormatCodePointException = new IllegalFormatCodePointException(
				-1);
		assertTrue(null != illegalFormatCodePointException);
	}

	/**
	 * @tests java.util.IllegalFormatCodePointException.getCodePoint()
	 */
	public void test_getCodePoint() {
		int codePoint = 12345;
		IllegalFormatCodePointException illegalFormatCodePointException = new IllegalFormatCodePointException(
				codePoint);
		assertEquals(codePoint, illegalFormatCodePointException.getCodePoint());
	}

	/**
	 * @tests java.util.IllegalFormatCodePointException.getMessage()
	 */
	public void test_getMessage() {
		int codePoint = 12345;
		IllegalFormatCodePointException illegalFormatCodePointException = new IllegalFormatCodePointException(
				codePoint);
		assertTrue(null != illegalFormatCodePointException.getMessage());
	}

	/**
	 * @tests serialization/deserilazation.
	 */
	public void test_serialization() throws Exception {
		int codePoint = 12345;
		IllegalFormatCodePointException srcIllegalFormatCodePointException = new IllegalFormatCodePointException(
				codePoint);
		IllegalFormatCodePointException destIllegalFormatCodePointException = (IllegalFormatCodePointException) SerializationTester
				.getDeserilizedObject(srcIllegalFormatCodePointException);
		assertEquals(srcIllegalFormatCodePointException.getCodePoint(),
				destIllegalFormatCodePointException.getCodePoint());
	}

	/**
	 * @tests serialization/deserilazation compatibility with RI.
	 */
	public void test_serializationCompatibility() throws Exception {
		int codePoint = 12345;
		IllegalFormatCodePointException srcIllegalFormatCodePointException = new IllegalFormatCodePointException(
				codePoint);
		IllegalFormatCodePointException destIllegalFormatCodePointException = (IllegalFormatCodePointException) SerializationTester
				.readObject(srcIllegalFormatCodePointException,
						SERIALIZATION_FILE_NAME);
		assertEquals(srcIllegalFormatCodePointException.getCodePoint(),
				destIllegalFormatCodePointException.getCodePoint());
	}

}
