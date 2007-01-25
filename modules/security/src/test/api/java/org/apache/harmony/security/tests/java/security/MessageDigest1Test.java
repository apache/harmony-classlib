/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
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
import java.security.*;
import org.apache.harmony.security.tests.support.MyMessageDigest1;

import junit.framework.TestCase;

/**
 * Tests for <code>MessageDigest</code> constructor and methods
 * 
 */
public class MessageDigest1Test extends TestCase {

	public void testReset() {	
		MyMessageDigest1 md = new MyMessageDigest1("ABC");
		md.reset();
		if (!md.runEngineReset) {
			fail("reset failed");
		}
	}
	
	/*
	 * Class under test for void update(byte)
	 */
	public void testUpdatebyte() {
		MyMessageDigest1 md = new MyMessageDigest1("ABC");
		md.update((byte)1);
		if (!md.runEngineUpdate1) {
			fail("update failed");
		}
	}

	/*
	 * Class under test for void update(byte[], int, int)
	 */
	public void testUpdatebyteArrayintint() {
		MyMessageDigest1 md = new MyMessageDigest1("ABC");
		byte[] b = {1, 2, 3, 4, 5};
		md.update(b, 1, 2);
		if (!md.runEngineUpdate2) {
			fail("update failed");
		}
	}

	/*
	 * Class under test for void update(byte[])
	 */
	public void testUpdatebyteArray() {
		MyMessageDigest1 md = new MyMessageDigest1("ABC");
		byte[] b = {1, 2, 3, 4, 5};
		md.update(b);
		if (!md.runEngineUpdate2) {
			fail("update failed");
		}
	}

	/*
	 * Class under test for byte[] digest()
	 */
	public void testDigest() {
		MyMessageDigest1 md = new MyMessageDigest1("ABC");
        assertEquals("incorrect result", 0,md.digest().length);
		if (!md.runEngineDigest) {
			fail("update failed");
		}
	}

	/*
	 * Class under test for byte[] digest(byte[])
	 */
	public void testDigestbyteArray() {
		MyMessageDigest1 md = new MyMessageDigest1("ABC");
		byte[] b = {1, 2, 3, 4, 5};
        assertEquals("incorrect result", 0, md.digest(b).length);
		if (!md.runEngineDigest) {
			fail("update failed");
		}
	}

	public void testIsEqual() {
		byte[] b1 = {1, 2, 3, 4};
		byte[] b2 = {1, 2, 3, 4, 5};
		byte[] b3 = {1, 3, 3, 4};
		byte[] b4 = {1, 2, 3, 4};
		
		if (!MessageDigest.isEqual(b1, b4) || 
				MessageDigest.isEqual(b1, b2) || 
				MessageDigest.isEqual(b1, b3)) {
			fail("isEqual failed");			
		}
	}

	public void testGetAlgorithm() {
		MyMessageDigest1 md = new MyMessageDigest1("ABC");
		if (!"ABC".equals(md.getAlgorithm())) {
			fail("getAlgorithm failed");
		}
	}

	public void testGetProvider() {
		MyMessageDigest1 md = new MyMessageDigest1("ABC");
		if (md.getProvider() != null) {
			fail("getProvider failed");
		}
	}

	public void testGetDigestLength() {
		MyMessageDigest1 md = new MyMessageDigest1("ABC");
		if (md.getDigestLength() != 0) {
			fail("getDigestLength failed");
		}
	}
}

