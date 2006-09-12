/* Copyright 2004 The Apache Software Foundation or its licensors, as applicable
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

package org.apache.harmony.nio_char.tests.java.nio.charset;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.MalformedInputException;

import junit.framework.TestCase;
import tests.util.SerializationTester;

/**
 * Test class MalformedInputException.
 */
public class MalformedInputExceptionTest extends TestCase {

	public void testConstructor() {
		MalformedInputException ex = new MalformedInputException(3);
		assertTrue(ex instanceof CharacterCodingException);
		assertNull(ex.getCause());
		assertEquals(ex.getInputLength(), 3);
		assertTrue(ex.getMessage().indexOf("3") != -1);

		ex = new MalformedInputException(-3);
		assertNull(ex.getCause());
		assertEquals(ex.getInputLength(), -3);
		assertTrue(ex.getMessage().indexOf("-3") != -1);

		ex = new MalformedInputException(0);
		assertNull(ex.getCause());
		assertEquals(ex.getInputLength(), 0);
		assertTrue(ex.getMessage().indexOf("0") != -1);
	}

	/*
	 * Test serialization/deserialization.
	 */
	public void testSerialization() throws Exception {
		MalformedInputException ex = new MalformedInputException(11);

		MalformedInputException deEx = (MalformedInputException) SerializationTester
				.getDeserilizedObject(ex);
		assertEquals(11, deEx.getInputLength());
	}

	/*
	 * Test serialization/deserialization compatibility with reference
	 * implementation.
	 */
	public void testSerializationCompatibility() throws Exception {
		MalformedInputException ex = new MalformedInputException(11);

		MalformedInputException deEx = (MalformedInputException) SerializationTester
				.readObject(ex,
						"tests/api/java/nio/charset/MalformedInputException.ser");
		assertEquals(11, deEx.getInputLength());
	}

}
