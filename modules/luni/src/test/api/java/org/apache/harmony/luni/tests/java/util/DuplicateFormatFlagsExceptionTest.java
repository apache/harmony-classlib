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

import java.util.DuplicateFormatFlagsException;

import tests.util.SerializationTester;

import junit.framework.TestCase;

public class DuplicateFormatFlagsExceptionTest extends TestCase {

	private static final String SERIALIZATION_FILE_NAME = "serialization/java/util/DuplicateFormatFlagsException.ser"; //$NON-NLS-1$

	/**
	 * @tests java.util.DuplicateFormatFlagsException#DuplicateFormatFlagsException(String)
	 */
	public void test_duplicateFormatFlagsException() {
		try {
            new DuplicateFormatFlagsException(null);
			fail("should throw NullPointerException.");
		} catch (NullPointerException e) {
			// desired
		}
	}

	/**
	 * @tests java.util.DuplicateFormatFlagsException#getFlags()
	 */
	public void test_getFlags() {
		String strFlags = "MYTESTFLAGS";
		DuplicateFormatFlagsException duplicateFormatException = new DuplicateFormatFlagsException(
				strFlags);
		assertEquals(strFlags, duplicateFormatException.getFlags());
	}

	/**
	 * @tests java.util.DuplicateFormatFlagsException#getMessage()
	 */
	public void test_getMessage() {
		String strFlags = "MYTESTFLAGS";
		DuplicateFormatFlagsException duplicateFormatException = new DuplicateFormatFlagsException(
				strFlags);
		assertTrue(null != duplicateFormatException.getFlags());

	}

	/**
	 * @tests serialization/deserilazation.
	 */
	public void test_serialization() throws Exception {
		DuplicateFormatFlagsException srcDuplicateFormatFlagsException = new DuplicateFormatFlagsException(
				"TESTDESC");
		DuplicateFormatFlagsException destDuplicateFormatFlagsException = (DuplicateFormatFlagsException) SerializationTester
				.getDeserilizedObject(srcDuplicateFormatFlagsException);
	}

	/**
	 * @tests serialization/deserilazation compatibility with RI.
	 */
	public void test_serializationCompatibility() throws Exception {
		DuplicateFormatFlagsException srcDuplicateFormatFlagsException = new DuplicateFormatFlagsException(
				"TESTDESC");
		DuplicateFormatFlagsException destDuplicateFormatFlagsException = (DuplicateFormatFlagsException) SerializationTester
				.readObject(srcDuplicateFormatFlagsException,
						SERIALIZATION_FILE_NAME);
	}

}
