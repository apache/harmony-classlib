/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.security.tests.java.security;

import java.io.ByteArrayOutputStream;
import java.security.DigestOutputStream;
import java.security.MessageDigest;

public class DigestOutputStream2Test extends junit.framework.TestCase {
	ByteArrayOutputStream output1;

	ByteArrayOutputStream output2;

	MessageDigest digest;

	/**
	 * @tests java.security.DigestOutputStream#getMessageDigest()
	 */
	public void test_getMessageDigest() {
		// Test for method java.security.MessageDigest
		// java.security.DigestOutputStream.getMessageDigest()
		DigestOutputStream dos = new DigestOutputStream(output1, digest);
		assertEquals("getMessageDigest did not return expected result", 
				digest, dos.getMessageDigest());
		dos = new DigestOutputStream(output1, null);
		assertNull("getMessageDigest should have returned null", dos
				.getMessageDigest());
	}

	/**
	 * @tests java.security.DigestOutputStream#on(boolean)
	 */
	public void test_onZ() {
		// Test for method void java.security.DigestOutputStream.on(boolean)
		try {
			DigestOutputStream dos = new DigestOutputStream(output1, digest);
			dos.on(false);
			byte digestArray[] = { 23, 43, 44 };
			dos.write(digestArray, 1, 1);
			byte digestResult[] = dos.getMessageDigest().digest();
			byte expected[] = { -38, 57, -93, -18, 94, 107, 75, 13, 50, 85,
					-65, -17, -107, 96, 24, -112, -81, -40, 7, 9 };
			assertTrue("Digest did not return expected result.",
					java.util.Arrays.equals(digestResult, expected));
			// now turn on processing and re-run
			dos.on(true);
			dos.write(digestArray, 1, 1);
			digestResult = dos.getMessageDigest().digest();
			byte expected1[] = { -87, 121, -17, 16, -52, 111, 106, 54, -33,
					107, -118, 50, 51, 7, -18, 59, -78, -30, -37, -100 };

			assertTrue("Digest did not return expected result.",
					java.util.Arrays.equals(digestResult, expected1));
		} catch (Exception e) {
			fail("Caught exception : " + e);
		}
	}

	/**
	 * @tests java.security.DigestOutputStream#setMessageDigest(java.security.MessageDigest)
	 */
	public void test_setMessageDigestLjava_security_MessageDigest() {
		// Test for method void
		// java.security.DigestOutputStream.setMessageDigest(java.security.MessageDigest)
		DigestOutputStream dos = new DigestOutputStream(output1, null);
		dos.setMessageDigest(digest);
		assertEquals("getMessageDigest did not return expected result", digest,
				dos.getMessageDigest());
		dos.setMessageDigest(null);
		assertNull("getMessageDigest should have returned null", dos
				.getMessageDigest());
	}

	/**
	 * @tests java.security.DigestOutputStream#write(byte[], int, int)
	 */
	public void test_write$BII() throws Exception {
		// Test for method void java.security.DigestOutputStream.write(byte [],
		// int, int)
       		DigestOutputStream dos = new DigestOutputStream(output1, digest);
       		byte digestArray[] = { 23, 43, 44 };
       		dos.write(digestArray, 1, 1);
       		byte digestResult[] = dos.getMessageDigest().digest();
       		byte expected[] = { -87, 121, -17, 16, -52, 111, 106, 54, -33, 107,
       				-118, 50, 51, 7, -18, 59, -78, -30, -37, -100 };

       		assertTrue("Digest did not return expected result.",
       				java.util.Arrays.equals(digestResult, expected));
	}

	/**
	 * @tests java.security.DigestOutputStream#write(int)
	 */
	public void test_writeI() throws Exception {
		// Test for method void java.security.DigestOutputStream.write(int)
       		DigestOutputStream dos = new DigestOutputStream(output1, digest);
       		dos.write((byte) 43);
       		byte digestResult[] = dos.getMessageDigest().digest();
       		byte expected[] = { -87, 121, -17, 16, -52, 111, 106, 54, -33, 107,
       				-118, 50, 51, 7, -18, 59, -78, -30, -37, -100 };

       		assertTrue("Digest did not return expected result.",
       				java.util.Arrays.equals(digestResult, expected));
	}

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() throws Exception {
		output1 = new ByteArrayOutputStream();
		digest = MessageDigest.getInstance("SHA");
	}
}