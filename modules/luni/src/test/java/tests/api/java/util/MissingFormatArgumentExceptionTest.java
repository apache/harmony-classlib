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

import java.util.MissingFormatArgumentException;

import tests.util.SerializationTester;

import junit.framework.TestCase;

public class MissingFormatArgumentExceptionTest extends TestCase {

	private static final String SERIALIZATION_FILE_NAME = "serialization/java/util/MissingFormatArgumentException.ser";

	/**
	 * @tests java.util.MissingFormatArgumentException#MissingFormatArgumentException(String)
	 */
	public void test_missingFormatArgumentException() {

		try {
			MissingFormatArgumentException missingFormatArgumentException = new MissingFormatArgumentException(
					null);
			fail("should throw NullPointerExcepiton.");
		} catch (NullPointerException e) {
			// expected
		}

	}

	/**
	 * @tests java.util.MissingFormatArgumentException#getFormatSpecifier()
	 */
	public void test_getFormatSpecifier() {
		String s = "MYTESTSTRING";
		MissingFormatArgumentException missingFormatArgumentException = new MissingFormatArgumentException(
				s);
		assertEquals(s, missingFormatArgumentException.getFormatSpecifier());
	}

	/**
	 * @tests java.util.MissingFormatArgumentException#getMessage()
	 */
	public void test_getMessage() {
		String s = "MYTESTSTRING";
		MissingFormatArgumentException missingFormatArgumentException = new MissingFormatArgumentException(
				s);
		assertTrue(null != missingFormatArgumentException.getMessage());

	}

	/**
	 * @tests serialization/deserilazation.
	 */
	public void test_serialization() throws Exception {
		String s = "MYTESTSTRING";
		MissingFormatArgumentException srcMissingFormatArgumentException = new MissingFormatArgumentException(
				s);
		MissingFormatArgumentException destMissingFormatArgumentException = (MissingFormatArgumentException) SerializationTester
				.getDeserilizedObject(srcMissingFormatArgumentException);
		assertEquals(srcMissingFormatArgumentException.getFormatSpecifier(),
				destMissingFormatArgumentException.getFormatSpecifier());

	}

	/**
	 * @tests serialization/deserilazation compatibility with RI.
	 */
	public void test_serializationCompatibility() throws Exception {
		String s = "MYTESTSTRING";
		MissingFormatArgumentException srcMissingFormatArgumentException = new MissingFormatArgumentException(
				s);
		MissingFormatArgumentException destMissingFormatArgumentException = (MissingFormatArgumentException) SerializationTester
				.readObject(srcMissingFormatArgumentException,
						SERIALIZATION_FILE_NAME);
		assertEquals(srcMissingFormatArgumentException.getFormatSpecifier(),
				destMissingFormatArgumentException.getFormatSpecifier());

	}

}
