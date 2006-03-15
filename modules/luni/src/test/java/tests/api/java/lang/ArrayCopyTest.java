/* Copyright 2005 The Apache Software Foundation or its licensors, as applicable
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

package tests.api.java.lang;

import junit.framework.TestCase;

/**
 * Testing arraycopy behavior.
 */
public class ArrayCopyTest extends TestCase {

	public void testArrayCopy() {
		char[][] source = new char[][] { { 'H', 'e', 'l', 'l', 'o' },
				{ 'W', 'o', 'r', 'l', 'd' } };
		char[][] dest = new char[2][];
		System.arraycopy(source, 0, dest, 0, 2);
	}
}
