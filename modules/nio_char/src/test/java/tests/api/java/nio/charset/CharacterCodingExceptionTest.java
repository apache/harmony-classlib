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

package tests.api.java.nio.charset;

import java.io.IOException;
import java.nio.charset.CharacterCodingException;

import junit.framework.TestCase;
import tests.util.SerializationTester;

/**
 * Test CharacterCodingException
 */
public class CharacterCodingExceptionTest extends TestCase {

	public void testConstructor() {
		CharacterCodingException ex = new CharacterCodingException();
		assertTrue(ex instanceof IOException);
		assertNull(ex.getCause());
		assertNull(ex.getMessage());
	}

	/*
	 * Test serialization/deserialization.
	 */
	public void testSerialization() throws Exception {
		CharacterCodingException ex = new CharacterCodingException();

		CharacterCodingException deEx = (CharacterCodingException) SerializationTester
				.getDeserilizedObject(ex);
	}

	/*
	 * Test serialization/deserialization.
	 */
	public void testSerializationCompatibility() throws Exception {
		CharacterCodingException ex = new CharacterCodingException();

		CharacterCodingException deEx = (CharacterCodingException) SerializationTester
				.readObject(ex,
						"tests/api/java/nio/charset/CharacterCodingException.ser");
	}
}
