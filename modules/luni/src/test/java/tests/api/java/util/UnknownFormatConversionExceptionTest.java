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

import java.util.UnknownFormatConversionException;

import tests.util.SerializationTester;

import junit.framework.TestCase;

public class UnknownFormatConversionExceptionTest extends TestCase {

	private static final String SERIALIZATION_FILE_NAME = "serialization/java/util/UnknownFormatConversionException.ser";

	/**
	 * @tests java.util.UnknownFormatConversionException#UnknownFormatConversionException(String)
	 */
	public void test_unknownFormatConversionException() {

		// RI 5.0 will not throw NullPointerException, it is the bug according
		// to spec.
		try {
			String s = null;
			UnknownFormatConversionException UnknownFormatConversionException = new UnknownFormatConversionException(
					s);
			fail("should throw NullPointerExcepiton");
		} catch (NullPointerException e) {
			// expected
		}

	}

	/**
	 * @tests java.util.UnknownFormatConversionException#getConversion()
	 */
	public void test_getConversion() {
		String s = "MYTESTSTRING";
		UnknownFormatConversionException UnknownFormatConversionException = new UnknownFormatConversionException(
				s);
		assertEquals(s, UnknownFormatConversionException.getConversion());

	}

	/**
	 * @tests java.util.UnknownFormatConversionException#getMessage()
	 */
	public void test_getMessage() {
		String s = "MYTESTSTRING";
		UnknownFormatConversionException UnknownFormatConversionException = new UnknownFormatConversionException(
				s);
		assertTrue(null != UnknownFormatConversionException.getMessage());

	}

	/**
	 * @tests serialization/deserilazation.
	 */
	public void test_serialization() throws Exception {
		String s = "MYTESTSTRING";
		UnknownFormatConversionException srcUnknownFormatConversionException = new UnknownFormatConversionException(
				s);
		UnknownFormatConversionException destUnknownFormatConversionException = (UnknownFormatConversionException) SerializationTester
				.getDeserilizedObject(srcUnknownFormatConversionException);
		assertEquals(srcUnknownFormatConversionException.getConversion(),
				destUnknownFormatConversionException.getConversion());

	}

	/**
	 * @tests serialization/deserilazation compatibility with RI.
	 */
	public void test_serializationCompatibility() throws Exception {
		String s = "MYTESTSTRING";
		UnknownFormatConversionException srcUnknownFormatConversionException = new UnknownFormatConversionException(
				s);
		UnknownFormatConversionException destUnknownFormatConversionException = (UnknownFormatConversionException) SerializationTester
				.readObject(srcUnknownFormatConversionException,
						SERIALIZATION_FILE_NAME);
		assertEquals(srcUnknownFormatConversionException.getConversion(),
				destUnknownFormatConversionException.getConversion());

	}

}
