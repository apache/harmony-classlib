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

import java.util.IllegalFormatPrecisionException;

import tests.util.SerializationTester;

import junit.framework.TestCase;

public class IllegalFormatPrecisionExceptionTest extends TestCase {

	private static final String SERIALIZATION_FILE_NAME = "serialization/java/util/IllegalFormatPrecisionException.ser";

	/**
	 * @tests java.util.IllegalFormatPrecisionException#IllegalFormatPrecisionException(int)
	 */
	public void test_illegalFormatPrecisionException() {
		IllegalFormatPrecisionException illegalFormatPrecisionException = new IllegalFormatPrecisionException(
				Integer.MIN_VALUE);
		assertEquals(Integer.MIN_VALUE, illegalFormatPrecisionException
				.getPrecision());
	}

	/**
	 * @tests java.util.IllegalFormatPrecisionException#getPrecision()
	 */
	public void test_getPrecision() {
		int precision = 12345;
		IllegalFormatPrecisionException illegalFormatPrecisionException = new IllegalFormatPrecisionException(
				precision);
		assertEquals(precision, illegalFormatPrecisionException.getPrecision());
	}

	/**
	 * @tests method for 'java.util.IllegalFormatPrecisionException#getMessage()
	 */
	public void test_getMessage() {
		int precision = 12345;
		IllegalFormatPrecisionException illegalFormatPrecisionException = new IllegalFormatPrecisionException(
				precision);
		assertTrue(null != illegalFormatPrecisionException.getMessage());

	}

	/**
	 * @tests serialization/deserilazation.
	 */
	public void test_serialization() throws Exception {
		int precision = 12345;
		IllegalFormatPrecisionException srcIllegalFormatPrecisionException = new IllegalFormatPrecisionException(
				precision);
		IllegalFormatPrecisionException destIllegalFormatPrecisionException = (IllegalFormatPrecisionException) SerializationTester
				.getDeserilizedObject(srcIllegalFormatPrecisionException);
		assertEquals(srcIllegalFormatPrecisionException.getPrecision(),
				destIllegalFormatPrecisionException.getPrecision());

	}

	/**
	 * @tests serialization/deserilazation compatibility with RI.
	 */
	public void test_serializationCompatibility() throws Exception {
		int precision = 12345;
		IllegalFormatPrecisionException srcIllegalFormatPrecisionException = new IllegalFormatPrecisionException(
				precision);
		IllegalFormatPrecisionException destIllegalFormatPrecisionException = (IllegalFormatPrecisionException) SerializationTester
				.readObject(srcIllegalFormatPrecisionException,
						SERIALIZATION_FILE_NAME);
		assertEquals(srcIllegalFormatPrecisionException.getPrecision(),
				destIllegalFormatPrecisionException.getPrecision());

	}

}
