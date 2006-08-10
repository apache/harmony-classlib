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

import java.util.MissingFormatWidthException;

import tests.util.SerializationTester;

import junit.framework.TestCase;

public class MissingFormatWidthExceptionTest extends TestCase {

	private static final String SERIALIZATION_FILE_NAME = "serialization/java/util/MissingFormatWidthException.ser";

	/**
	 * @tests java.util.MissingFormatWidthException#MissingFormatWidthException(String)
	 */
	public void test_missingFormatWidthException() {
		try {
            new MissingFormatWidthException(null);
			fail("should throw NullPointerExcepiton");
		} catch (NullPointerException e) {
			// expected
		}
	}

	/**
	 * @tests java.util.MissingFormatWidthException#getFormatSpecifier()
	 */
	public void test_getFormatSpecifier() {
		String s = "MYTESTSTRING";
		MissingFormatWidthException missingFormatWidthException = new MissingFormatWidthException(
				s);
		assertEquals(s, missingFormatWidthException.getFormatSpecifier());

	}

	/**
	 * @tests java.util.MissingFormatWidthException#getMessage()
	 */
	public void test_getMessage() {
		String s = "MYTESTSTRING";
		MissingFormatWidthException missingFormatWidthException = new MissingFormatWidthException(
				s);
		assertTrue(null != missingFormatWidthException.getMessage());

	}

	/**
	 * @tests serialization/deserilazation.
	 */
	public void test_serialization() throws Exception {
		String s = "MYTESTSTRING";
		MissingFormatWidthException srcMissingFormatWidthException = new MissingFormatWidthException(
				s);
		MissingFormatWidthException destMissingFormatWidthException = (MissingFormatWidthException) SerializationTester
				.getDeserilizedObject(srcMissingFormatWidthException);
		assertEquals(srcMissingFormatWidthException.getFormatSpecifier(),
				destMissingFormatWidthException.getFormatSpecifier());

	}

	/**
	 * @tests serialization/deserilazation compatibility with RI.
	 */
	public void test_serializationCompatibility() throws Exception {
		String s = "MYTESTSTRING";
		MissingFormatWidthException srcMissingFormatWidthException = new MissingFormatWidthException(
				s);
		MissingFormatWidthException destMissingFormatWidthException = (MissingFormatWidthException) SerializationTester
				.readObject(srcMissingFormatWidthException,
						SERIALIZATION_FILE_NAME);
		assertEquals(srcMissingFormatWidthException.getFormatSpecifier(),
				destMissingFormatWidthException.getFormatSpecifier());

	}

}
