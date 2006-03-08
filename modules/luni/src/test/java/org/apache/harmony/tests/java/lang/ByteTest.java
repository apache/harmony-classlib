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

public class ByteTest extends TestCase {

	/**
	 * @tests java.lang.Byte#valueOf(byte)
	 */
	public void test_valueOfB() {
		assertEquals(new Byte(Byte.MIN_VALUE), Byte.valueOf(Byte.MIN_VALUE));
		assertEquals(new Byte(Byte.MAX_VALUE), Byte.valueOf(Byte.MAX_VALUE));
		assertEquals(new Byte((byte) 0), Byte.valueOf((byte) 0));

		byte b = Byte.MIN_VALUE + 1;
		while (b < Byte.MAX_VALUE) {
			assertEquals(new Byte(b), Byte.valueOf(b));
			assertSame(Byte.valueOf(b), Byte.valueOf(b));
			b++;
		}
	}
}
