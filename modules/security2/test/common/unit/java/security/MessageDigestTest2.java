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

package java.security;

import junit.framework.TestCase;

/**
 * Tests for <code>MessageDigest</code> constructor and methods
 * 
 */
public class MessageDigestTest2 extends TestCase {

	/**
	 * Provider
	 */
	Provider p;
	
	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		p = new MyProvider();
		Security.insertProviderAt(p, 1);
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		Security.removeProvider(p.getName());
	}

	/*
	 * Class under test for MessageDigest getInstance(String)
	 */
	public void testGetInstanceString1() {
		MessageDigest md1 = null;
		
		try {
			md1 = MessageDigest.getInstance("ABC");		
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		}
		checkMD1(md1, p);
	}
	
	/*
	 * Class under test for MessageDigest getInstance(String)
	 */
	public void testGetInstanceString2() {
		MessageDigest md2 = null;
		
		try {
			md2 = MessageDigest.getInstance("CBA");
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		}
		checkMD2(md2, p);
	}

	/*
	 * Class under test for MessageDigest getInstance(String, String)
	 */
	public void testGetInstanceStringString1() {
		MessageDigest md1 = null;
		
		try {
			md1 = MessageDigest.getInstance("ABC", "MyProvider");		
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		} catch (NoSuchProviderException e) {
			fail(e.toString());
		}
		checkMD1(md1, p);
	}
	
	/*
	 * Class under test for MessageDigest getInstance(String, String)
	 */
	public void testGetInstanceStringString2() {
		MessageDigest md2 = null;
		try {
			md2 = MessageDigest.getInstance("CBA", "MyProvider");
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		} catch (NoSuchProviderException e) {
			fail(e.toString());
		}
		checkMD2(md2, p);
	}

	/*
	 * Class under test for MessageDigest getInstance(String, Provider)
	 */
	public void testGetInstanceStringProvider1() {
		MessageDigest md1 = null;
		
		Provider p = new MyProvider();
		try {
			md1 = MessageDigest.getInstance("ABC", p);		
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		}
		checkMD1(md1, p);
	}
	
	/*
	 * Class under test for MessageDigest getInstance(String, Provider)
	 */
	public void testGetInstanceStringProvider2() {
		MessageDigest md2 = null;
		Provider p = new MyProvider();
		try {
			md2 = MessageDigest.getInstance("CBA", p);
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		}
		checkMD2(md2, p);
	}
	
	private void checkMD1(MessageDigest md1, Provider p) {
		byte[] b = {1, 2, 3, 4, 5};
		
		if (!(md1 instanceof MyMessageDigest1)) {
			fail("getInstance() failed");
		}
		if (md1.getProvider() != p) {
			fail("getProvider() failed");
		}
		if (!"ABC".equals(md1.getAlgorithm())) {
			fail("getAlgorithm() failed");			
		}
		md1.update((byte)1);
		md1.update(b, 1, 4);
		if (!((MyMessageDigest1)md1).runEngineUpdate1 || 
				!((MyMessageDigest1)md1).runEngineUpdate2) {
			fail("update failed");
		}
		try {
			if (md1.digest() != null) {
				fail("incorrect digest result");
			}
			if (md1.digest(b, 2, 3) != 0) {
				fail("incorrect digest result");
			}			
		} catch (java.security.DigestException e) {
			fail(e.toString());
		}
		if (!((MyMessageDigest1)md1).runEngineDigest) {
			fail("update failed");
		}
		md1.reset();
		if (!((MyMessageDigest1)md1).runEngineReset) {
			fail("reset failed");
		}
		if (md1.getDigestLength() != 0) {
			fail("getDigestLength failed");
		}
		try {
			md1.clone();
			fail("No expected CloneNotSupportedException");
		} catch (CloneNotSupportedException e) {	
		}		
	}
	
	private void checkMD2(MessageDigest md2, Provider p) {
		byte[] b = {1, 2, 3, 4, 5};
		
		if (!(md2 instanceof MessageDigestSpi)) {
			fail("getInstance() failed");
		}
		if (md2.getProvider() != p) {
			fail("getProvider() failed");
		}
		if (!"CBA".equals(md2.getAlgorithm())) {
			fail("getAlgorithm() failed");			
		}
		md2.update((byte)1);
		md2.update(b, 1, 3);
		if (!MyMessageDigest2.runEngineUpdate1 || 
				!MyMessageDigest2.runEngineUpdate2) {
			fail("update failed");
		}
		try {
			if (md2.digest() != null) {
				fail("incorrect digest result");
			}
			if (md2.digest(b, 2, 3) != 0) {
				fail("incorrect digest result");
			}			
		} catch (java.security.DigestException e) {
			fail(e.toString());
		}
		if (!MyMessageDigest2.runEngineDigest) {
			fail("update failed");
		}
		md2.reset();
		if (!MyMessageDigest2.runEngineReset) {
			fail("reset failed");
		}
		if (md2.getDigestLength() != 0) {
			fail("getDigestLength failed");
		}
		try {
			md2.clone();
			fail("No expected CloneNotSupportedException");
		} catch (CloneNotSupportedException e) {	
		}
	}
	
	private class MyProvider extends Provider {
		MyProvider() {
			super("MyProvider", 1.0, "Provider for testing");
			put("MessageDigest.ABC", "java.security.MyMessageDigest1");
			put("MessageDigest.CBA", "java.security.MyMessageDigest2");
		}
		
		MyProvider(String name, double version, String info) {
			super(name, version, info);
		}
	}
}
