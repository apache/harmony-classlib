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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestInputStream2Test extends junit.framework.TestCase {

	ByteArrayInputStream inStream;

	ByteArrayInputStream inStream1;

	MessageDigest digest;

	/**
	 * @tests java.security.DigestInputStream#DigestInputStream(java.io.InputStream,
	 *        java.security.MessageDigest)
	 */
	public void test_ConstructorLjava_io_InputStreamLjava_security_MessageDigest() {
		// Test for method java.security.DigestInputStream(java.io.InputStream,
		// java.security.MessageDigest)
		DigestInputStream dis = new DigestInputStream(inStream, digest);
		assertNotNull("Constructor returned null instance", dis);
	}

	/**
	 * @tests java.security.DigestInputStream#getMessageDigest()
	 */
	public void test_getMessageDigest() {
		// Test for method java.security.MessageDigest
		// java.security.DigestInputStream.getMessageDigest()
		DigestInputStream dis = new DigestInputStream(inStream, digest);
		assertEquals("getMessageDigest returned a bogus result", digest, dis
				.getMessageDigest());
	}

	/**
	 * @tests java.security.DigestInputStream#on(boolean)
	 */
	public void test_onZ() {
		// Test for method void java.security.DigestInputStream.on(boolean)
		try {
			MessageDigest originalDigest = (MessageDigest) (digest.clone());
			MessageDigest noChangeDigest = (MessageDigest) (digest.clone());
			DigestInputStream dis = new DigestInputStream(inStream,
					noChangeDigest);
			// turn off processing
			dis.on(false);
			// read some data
			try {
				int c = dis.read();
				assertTrue("Stream returned bogus first character. Char was: '"
						+ (char) c + " 'Should have been 'T'", c == 'T');
			} catch (IOException e) {
				fail("Stream threw an IOException : " + e);
			}
			
			// make sure the digest for the part where it was off has not
			// changed
			assertTrue("MessageDigest changed even though processing was off",
					MessageDigest.isEqual(noChangeDigest.digest(),
							originalDigest.digest()));
			MessageDigest changeDigest = (MessageDigest) (digest.clone());
			dis = new DigestInputStream(inStream, digest);
			
			// turn on processing
			dis.on(true);
			try {
				int c = dis.read();
				assertTrue(
						"Stream returned bogus second character. Char was: '"
								+ (char) c + " 'Should have been 'h'", c == 'h');
			} catch (IOException e) {
				fail("Stream threw an IOException : " + e);
			}
			// make sure the digest has changed
			assertTrue("MessageDigest did not change with processing on",
					!MessageDigest.isEqual(digest.digest(), changeDigest
							.digest()));
		} catch (CloneNotSupportedException e) {
			fail("MessageDigest should support clone : " + e);
		}

	}

	/**
	 * @tests java.security.DigestInputStream#read()
	 */
	public void test_read() {
		// Test for method int java.security.DigestInputStream.read()
		DigestInputStream dis = new DigestInputStream(inStream, digest);
		
		// read and compare the data that the inStream has
		try {
			int c;
			while ((c = dis.read()) > -1) {
				int d = inStream1.read();
				assertTrue("Stream returned bogus character '" + (char) c
						+ "' should have been '" + (char) d + "'", c == d);
			}// end while
		} catch (IOException e) {
			fail("Stream threw an IOException : " + e);
		}
	}

	/**
	 * @tests java.security.DigestInputStream#read(byte[], int, int)
	 */
	public void test_read$BII() {
		// Test for method int java.security.DigestInputStream.read(byte [],
		// int, int)
		DigestInputStream dis = new DigestInputStream(inStream, digest);
		int bytesToRead = inStream.available();
		byte buf1[] = new byte[bytesToRead + 5];
		byte buf2[] = new byte[bytesToRead + 5];
		// make sure we're actually reading some data
		assertTrue("No data to read for this test", bytesToRead>0);
		
		// read and compare the data that the inStream has
		try {
			int bytesRead1 = dis.read(buf1, 5, bytesToRead);
			int bytesRead2 = inStream1.read(buf2, 5, bytesToRead);
			assertTrue("Didn't read the same from each stream",
					bytesRead1 == bytesRead2);
			assertTrue("Didn't read the entire", bytesRead1 == bytesToRead);
			// compare the arrays
			boolean same = true;
			for (int i = 0; i < bytesToRead + 5; i++) {
				if (buf1[i] != buf2[i]) {
					same = false;
				}
			}// end for 
			assertTrue("Didn't get the same data", same);
		} catch (IOException e) {
			fail("Stream threw an IOException : " + e);
		}
	}

	/**
	 * @tests java.security.DigestInputStream#setMessageDigest(java.security.MessageDigest)
	 */
	public void test_setMessageDigestLjava_security_MessageDigest() {
		// Test for method void
		// java.security.DigestInputStream.setMessageDigest(java.security.MessageDigest)
		DigestInputStream dis = new DigestInputStream(inStream, null);
		
		// make sure the digest is null when it's not been set
		assertNull(
				"Uninitialised MessageDigest should have been returned as null",
				dis.getMessageDigest());
		dis.setMessageDigest(digest);
		assertEquals("Wrong MessageDigest was returned.", digest, dis
				.getMessageDigest());
	}

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() {
		// create a ByteArrayInputStream to perform digesting on
		inStream = new ByteArrayInputStream(
				"This is a test string for digesting".getBytes());
		inStream1 = new ByteArrayInputStream(
				"This is a test string for digesting".getBytes());
		try {
			digest = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			fail("Unable to find SHA-1 algorithm");
		}
	}
}