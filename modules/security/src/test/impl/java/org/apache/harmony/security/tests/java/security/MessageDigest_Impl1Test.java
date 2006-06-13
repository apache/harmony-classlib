/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
* @author Boris V. Kuznetsov
* @version $Revision$
*/

package org.apache.harmony.security.tests.java.security;

import org.apache.harmony.security.tests.support.MyMessageDigest1;

import junit.framework.TestCase;

/**
 * Tests for <code>MessageDigest</code> constructor and methods
 * 
 */
public class MessageDigest_Impl1Test extends TestCase {

	/*
	 * Class under test for int digest(byte[], int, int)
	 */
	public void testDigestbyteArrayintint() throws Exception {
		MyMessageDigest1 md = new MyMessageDigest1("ABC");
		byte[] b = {1, 2, 3, 4, 5};
		assertEquals("incorrect result", 0, md.digest(b, 2, 3));
        assertTrue("digest failed", md.runEngineDigest);
	}

	/*
	 * Class under test for String toString()
	 */
	public void testToString() {
		MyMessageDigest1 md = new MyMessageDigest1("ABC");
        assertEquals("incorrect result", "MESSAGE DIGEST ABC", md.toString());
	}
}

