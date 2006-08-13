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

import java.util.IllegalFormatConversionException;

import tests.util.SerializationTester;

import junit.framework.TestCase;

public class IllegalFormatConversionExceptionTest extends TestCase {

	private static final String SERIALIZATION_FILE_NAME = "serialization/java/util/IllegalFormatConversionException.ser";

	/**
	 * @tests java.util.IllegalFormatConversionException#IllegalFormatConversionException(char,
	 *        Class)
	 */
	public void test_illegalFormatConversionException() {
		try {
            new IllegalFormatConversionException(' ', null);
			fail("should throw NullPointerExcetpion.");
		} catch (NullPointerException e) {
			// desired
		}
	}

	/**
	 * @tests java.util.IllegalFormatConversionException#getArgumentClass()
	 */
	public void test_getArgumentClass() {
		char c = '*';
		Class argClass = String.class;
		IllegalFormatConversionException illegalFormatConversionException = new IllegalFormatConversionException(
				c, argClass);
		assertEquals(argClass, illegalFormatConversionException
				.getArgumentClass());

	}

	/**
	 * @tests java.util.IllegalFormatConversionException#getConversion()
	 */
	public void test_getConversion() {
		char c = '*';
		Class argClass = String.class;
		IllegalFormatConversionException illegalFormatConversionException = new IllegalFormatConversionException(
				c, argClass);
		assertEquals(c, illegalFormatConversionException.getConversion());

	}

	/**
	 * @tests java.util.IllegalFormatConversionException#getMessage()
	 */
	public void test_getMessage() {
		char c = '*';
		Class argClass = String.class;
		IllegalFormatConversionException illegalFormatConversionException = new IllegalFormatConversionException(
				c, argClass);
		assertTrue(null != illegalFormatConversionException.getMessage());

	}

	/**
	 * @tests serialization/deserilazation.
	 */
	public void test_serialization() throws Exception {
		char c = '*';
		Class argClass = String.class;
		IllegalFormatConversionException srcIllegalFormatConversionException = new IllegalFormatConversionException(
				c, argClass);
		IllegalFormatConversionException destIllegalFormatConversionException = (IllegalFormatConversionException) SerializationTester
				.getDeserilizedObject(srcIllegalFormatConversionException);
		assertEquals(srcIllegalFormatConversionException.getArgumentClass(),
				destIllegalFormatConversionException.getArgumentClass());
		assertEquals(srcIllegalFormatConversionException.getConversion(),
				destIllegalFormatConversionException.getConversion());
	}

	/**
	 * @tests serialization/deserilazation compatibility with RI.
	 */
	public void test_serializationCompatibility() throws Exception {
		char c = '*';
		Class argClass = String.class;
		IllegalFormatConversionException srcIllegalFormatConversionException = new IllegalFormatConversionException(
				c, argClass);
		IllegalFormatConversionException destIllegalFormatConversionException = (IllegalFormatConversionException) SerializationTester
				.readObject(srcIllegalFormatConversionException,
						SERIALIZATION_FILE_NAME);
		assertEquals(srcIllegalFormatConversionException.getArgumentClass(),
				destIllegalFormatConversionException.getArgumentClass());
		assertEquals(srcIllegalFormatConversionException.getConversion(),
				destIllegalFormatConversionException.getConversion());
	}

}
