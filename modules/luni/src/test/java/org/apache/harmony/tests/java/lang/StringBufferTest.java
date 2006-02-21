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

package org.apache.harmony.tests.java.lang;

import junit.framework.TestCase;

public class StringBufferTest extends TestCase {

	/**
	 * @tests java.lang.StringBuffer#setLength(int)
	 */
	public void test_setLengthI() {
		// Regression for HARMONY-90
		StringBuffer buffer = new StringBuffer("abcde");
		try {
			buffer.setLength(-1);
			fail("Assert 0: IndexOutOfBoundsException must be thrown");
		} catch (IndexOutOfBoundsException e) {
			// expected
		}
	}
}
