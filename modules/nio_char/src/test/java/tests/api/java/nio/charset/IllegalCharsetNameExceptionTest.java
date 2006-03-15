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

import java.nio.charset.IllegalCharsetNameException;

import junit.framework.TestCase;
import tests.util.SerializationTester;

/**
 * Test class IllegalCharsetNameException.
 */
public class IllegalCharsetNameExceptionTest extends TestCase {

	public void testConstructor() {
		IllegalCharsetNameException ex = new IllegalCharsetNameException(
				"impossible");
		assertTrue(ex instanceof IllegalArgumentException);
		assertNull(ex.getCause());
		assertEquals(ex.getCharsetName(), "impossible");
		assertTrue(ex.getMessage().indexOf("impossible") != -1);

		ex = new IllegalCharsetNameException("ascii");
		assertNull(ex.getCause());
		assertEquals(ex.getCharsetName(), "ascii");
		assertTrue(ex.getMessage().indexOf("ascii") != -1);

		ex = new IllegalCharsetNameException("");
		assertNull(ex.getCause());
		assertEquals(ex.getCharsetName(), "");
		ex.getMessage();

		ex = new IllegalCharsetNameException(null);
		assertNull(ex.getCause());
		assertEquals(ex.getCharsetName(), null);
		assertTrue(ex.getMessage().indexOf("null") != -1);

	}

	/*
	 * Test serialization/deserialization.
	 */
	public void testSerialization() throws Exception {
		IllegalCharsetNameException ex = new IllegalCharsetNameException(
				"charsetName");

		IllegalCharsetNameException deEx = (IllegalCharsetNameException) SerializationTester
				.getDeserilizedObject(ex);
	}

	/*
	 * Test serialization/deserialization
	 */
	public void testSerializationCompatibility() throws Exception {
		IllegalCharsetNameException ex = new IllegalCharsetNameException(
				"charsetName");

		IllegalCharsetNameException deEx = (IllegalCharsetNameException) SerializationTester
				.readObject(ex,
						"tests/api/java/nio/charset/IllegalCharsetNameException.ser");
	}

}
