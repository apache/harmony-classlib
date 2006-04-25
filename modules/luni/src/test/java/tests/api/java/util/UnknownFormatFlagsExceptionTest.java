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

import java.util.UnknownFormatFlagsException;

import tests.util.SerializationTester;

import junit.framework.TestCase;

public class UnknownFormatFlagsExceptionTest extends TestCase {

	private static final String SERIALIZATION_FILE_NAME = "serialization/java/util/UnknownFormatFlagsException.ser";

	/**
	 * @tests java.util.UnknownFormatFlagsException#UnknownFormatFlagsException(String)
	 */
	public void test_unknownFormatFlagsException() {

		try {
			String s = null;
			UnknownFormatFlagsException UnknownFormatFlagsException = new UnknownFormatFlagsException(
					s);
			fail("should throw NullPointerExcepiton");
		} catch (NullPointerException e) {
			// expected
		}

	}

	/**
	 * @tests java.util.UnknownFormatFlagsException#getFlags()
	 */
	public void test_getFlags() {
		String s = "MYTESTSTRING";
		UnknownFormatFlagsException UnknownFormatFlagsException = new UnknownFormatFlagsException(
				s);
		assertEquals(s, UnknownFormatFlagsException.getFlags());
	}

	/**
	 * @tests java.util.UnknownFormatFlagsException#getMessage()
	 */
	public void test_getMessage() {
		String s = "MYTESTSTRING";
		UnknownFormatFlagsException UnknownFormatFlagsException = new UnknownFormatFlagsException(
				s);
		assertNotNull(UnknownFormatFlagsException.getMessage());
	}

	/**
	 * @tests serialization/deserilazation.
	 */
	public void test_serialization() throws Exception {
		String s = "MYTESTSTRING";
		UnknownFormatFlagsException srcUnknownFormatFlagsException = new UnknownFormatFlagsException(
				s);
		UnknownFormatFlagsException destUnknownFormatFlagsException = (UnknownFormatFlagsException) SerializationTester
				.getDeserilizedObject(srcUnknownFormatFlagsException);
		assertEquals(srcUnknownFormatFlagsException.getFlags(),
				destUnknownFormatFlagsException.getFlags());

	}

	/**
	 * @tests serialization/deserilazation compatibility with RI.
	 */
	public void test_serializationCompatibility() throws Exception {
		String s = "MYTESTSTRING";
		UnknownFormatFlagsException srcUnknownFormatFlagsException = new UnknownFormatFlagsException(
				s);
		UnknownFormatFlagsException destUnknownFormatFlagsException = (UnknownFormatFlagsException) SerializationTester
				.readObject(srcUnknownFormatFlagsException,
						SERIALIZATION_FILE_NAME);
		assertEquals(srcUnknownFormatFlagsException.getFlags(),
				destUnknownFormatFlagsException.getFlags());

	}

}
