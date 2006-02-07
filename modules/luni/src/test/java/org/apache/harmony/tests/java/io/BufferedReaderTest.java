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

package org.apache.harmony.tests.java.io;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;

import junit.framework.TestCase;

public class BufferedReaderTest extends TestCase {

	/**
	 * @tests java.io.BufferedReader#read(char[], int, int)
	 */
	public void test_read$CII() throws IOException {
		// Regression for HARMONY-54
		char[] ch = {};
		BufferedReader reader = new BufferedReader(new CharArrayReader(ch));
		try {
			// Check exception thrown when the reader is open.
			reader.read(null, 1, 0);
			fail("Assert 0: NullPointerException expected");
		} catch (NullPointerException e) {
			// Expected
		}

		// Now check IOException is thrown in preference to
		// NullPointerexception when the reader is closed.
		reader.close();
		try {
			reader.read(null, 1, 0);
			fail("Assert 1: IOException expected");
		} catch (IOException e) {
			// Expected
		}

		try {
			// And check that the IOException is thrown before
			// ArrayIndexOutOfBoundException
			reader.read(ch, 0, 42);
			fail("Assert 2: IOException expected");
		} catch (IOException e) {
			// expected
		}
	}
}
