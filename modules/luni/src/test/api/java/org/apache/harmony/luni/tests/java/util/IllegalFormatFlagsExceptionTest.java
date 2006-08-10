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

import java.util.IllegalFormatFlagsException;

import tests.util.SerializationTester;

import junit.framework.TestCase;

public class IllegalFormatFlagsExceptionTest extends TestCase {

	private static final String SERIALIZATION_FILE_NAME = "serialization/java/util/IllegalFormatFlagsException.ser";

	/**
	 * @tests java.util.IllegalFormatFlagsException#IllegalFormatFlagsException(String)
	 */
	public void test_illegalFormatFlagsException() {
		try {
            new IllegalFormatFlagsException(null);
			fail("should throw NullPointerException");
		} catch (NullPointerException e) {
			// expected
		}
	}

	/**
	 * @tests java.util.IllegalFormatFlagsException.getFlags()
	 */
	public void test_getFlags() {
		String flags = "TESTFLAGS";
		IllegalFormatFlagsException illegalFormatFlagsException = new IllegalFormatFlagsException(
				flags);
		assertEquals(flags, illegalFormatFlagsException.getFlags());
	}

	/**
	 * @tests java.util.IllegalFormatFlagsException.getMessage()
	 */
	public void test_getMessage() {
		String flags = "TESTFLAGS";
		IllegalFormatFlagsException illegalFormatFlagsException = new IllegalFormatFlagsException(
				flags);
		assertTrue(null != illegalFormatFlagsException.getMessage());

	}

	/**
	 * @tests serialization/deserilazation.
	 */
	public void test_serialization() throws Exception {
		String flags = "TESTFLAGS";
		IllegalFormatFlagsException srcIllegalFormatFlagsException = new IllegalFormatFlagsException(
				flags);
		IllegalFormatFlagsException destIllegalFormatFlagsException = (IllegalFormatFlagsException) SerializationTester
				.getDeserilizedObject(srcIllegalFormatFlagsException);
		assertEquals(srcIllegalFormatFlagsException.getFlags(),
				destIllegalFormatFlagsException.getFlags());

	}

	/**
	 * @tests serialization/deserilazation compatibility with RI.
	 */
	public void test_serializationCompatibility() throws Exception {
		String flags = "TESTFLAGS";
		IllegalFormatFlagsException srcIllegalFormatFlagsException = new IllegalFormatFlagsException(
				flags);		
		IllegalFormatFlagsException destIllegalFormatFlagsException = (IllegalFormatFlagsException) SerializationTester
				.readObject(srcIllegalFormatFlagsException,
						SERIALIZATION_FILE_NAME);
		assertEquals(srcIllegalFormatFlagsException.getFlags(),
				destIllegalFormatFlagsException.getFlags());

	}

}
