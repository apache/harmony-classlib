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

package org.apache.harmony.luni.tests.java.io;

import java.io.ObjectStreamConstants;
import junit.framework.TestCase;

public class ObjectStreamConstantsTest extends TestCase {

	/**
	 * @tests java.io.ObjectStreamConstants#TC_ENUM
	 */
	public void test_TC_ENUM() {
		assertEquals(126, MockObjectStreamConstants.TC_ENUM);
	}

	/**
	 * @tests java.io.ObjectStreamConstants#SC_ENUM
	 */
	public void test_SC_ENUM() {
		assertEquals(16, MockObjectStreamConstants.SC_ENUM);
	}

	/**
	 * @tests java.io.ObjectStreamConstants#TC_MAX
	 */
	public void test_TC_MAX() {
		assertEquals(126, MockObjectStreamConstants.TC_MAX);
	}

	private class MockObjectStreamConstants implements ObjectStreamConstants {
		// empty
	}
}
