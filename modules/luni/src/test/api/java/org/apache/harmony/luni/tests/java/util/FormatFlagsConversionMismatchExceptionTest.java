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

import java.util.FormatFlagsConversionMismatchException;

import tests.util.SerializationTester;

import junit.framework.TestCase;

public class FormatFlagsConversionMismatchExceptionTest extends TestCase {

	private static final String SERIALIZATION_FILE_NAME = "serialization/java/util/FormatFlagsConversionMismatchException.ser"; //$NON-NLS-1$

	/**
	 * @tests java.util.FormatFlagsConversionMismatchException#FormatFlagsConversionMismatchException(String,
	 *        char)
	 */
	public void test_formatFlagsConversionMismatchException() {
		try {
			FormatFlagsConversionMismatchException formatFlagsConversionMismatchException = new FormatFlagsConversionMismatchException(
					null, ' ');
			fail("should throw NullPointerException.");
		} catch (NullPointerException e) {
			// expected
		}

	}

	/**
	 * @tests java.util.FormatFlagsConversionMismatchException#getFlags()
	 */
	public void test_getFlags() {
		String flags = "MYTESTFLAGS";
		char conversion = 'T';
		FormatFlagsConversionMismatchException formatFlagsConversionMismatchException = new FormatFlagsConversionMismatchException(
				flags, conversion);
		assertEquals(flags, formatFlagsConversionMismatchException.getFlags());
	}

	/**
	 * @tests java.util.FormatFlagsConversionMismatchException#getConversion()
	 */
	public void test_getConversion() {
		String flags = "MYTESTFLAGS";
		char conversion = 'T';
		FormatFlagsConversionMismatchException formatFlagsConversionMismatchException = new FormatFlagsConversionMismatchException(
				flags, conversion);
		assertEquals(conversion, formatFlagsConversionMismatchException
				.getConversion());

	}

	/**
	 * @tests java.util.FormatFlagsConversionMismatchException#getMessage()
	 */
	public void test_getMessage() {
		String flags = "MYTESTFLAGS";
		char conversion = 'T';
		FormatFlagsConversionMismatchException formatFlagsConversionMismatchException = new FormatFlagsConversionMismatchException(
				flags, conversion);
		assertTrue(null != formatFlagsConversionMismatchException.getMessage());

	}

	/**
	 * @tests serialization/deserilazation.
	 */
	public void test_serialization() throws Exception {
		String flags = "MYTESTFLAGS";
		char conversion = 'T';
		FormatFlagsConversionMismatchException srcFormatFlagsConversionMismatchException = new FormatFlagsConversionMismatchException(
				flags,conversion);
		FormatFlagsConversionMismatchException destFormatFlagsConversionMismatchException = (FormatFlagsConversionMismatchException) SerializationTester
				.getDeserilizedObject(srcFormatFlagsConversionMismatchException);
		assertEquals(srcFormatFlagsConversionMismatchException.getFlags(),destFormatFlagsConversionMismatchException.getFlags());
		assertEquals(srcFormatFlagsConversionMismatchException.getConversion(),destFormatFlagsConversionMismatchException.getConversion());
	}

	/**
	 * @tests serialization/deserilazation compatibility with RI.
	 */
	public void test_serializationCompatibility() throws Exception {
		String flags = "MYTESTFLAGS";
		char conversion = 'T';
		FormatFlagsConversionMismatchException srcFormatFlagsConversionMismatchException = new FormatFlagsConversionMismatchException(
				flags,conversion);
		FormatFlagsConversionMismatchException destFormatFlagsConversionMismatchException = (FormatFlagsConversionMismatchException) SerializationTester
				.readObject(srcFormatFlagsConversionMismatchException,
						SERIALIZATION_FILE_NAME);
		assertEquals(srcFormatFlagsConversionMismatchException.getFlags(),destFormatFlagsConversionMismatchException.getFlags());
		assertEquals(srcFormatFlagsConversionMismatchException.getConversion(),destFormatFlagsConversionMismatchException.getConversion());
	}

}
