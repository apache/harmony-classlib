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

import java.util.Random;

import junit.framework.TestCase;


/**
 *
 * Tests for internal Secure Random implementation based on Random
 * 
 */
public class SecureRandomTest2 extends TestCase {
	
	/**
	 * Registered providers
	 */
	Provider providers[] = Security.getProviders();
	
	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		// remove all registerd providers
		for (int i = 0; i < providers.length; i++) {
			Security.removeProvider(providers[i].getName());
		}
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		// restore all registerd providers
		for (int i = 0; i < providers.length; i++) {
			Security.addProvider(providers[i]);
		}
	}

	/*
	 * Class under test for void setSeed(long)
	 */
	public final void testSetSeedlong() {
		SecureRandom sr = new SecureRandom();
		sr.setSeed(0);
		sr.setSeed(-1);
		sr.setSeed(11111111111L);
	}

	public final void testNextBytes() {
		SecureRandom sr = new SecureRandom();
		sr.nextBytes(new byte[20]);
		sr.nextBytes(new byte[1]);
		
		//Not specified behavior: throws NullPointerException if bytes is null
		try {
			sr.nextBytes(null);
		} catch (NullPointerException e) {	
		}
		sr.nextBytes(new byte[5]);
	}

	/*
	 * Class under test for void SecureRandom()
	 */
	public final void testSecureRandom() {
		SecureRandom sr = new SecureRandom();
		Random r = new Random(0);
		sr.setSeed(0);
		byte[] b1 = new byte[8];
		sr.nextBytes(b1);			
	}

	/*
	 * Class under test for void SecureRandom(byte[])
	 */
	public final void testSecureRandombyteArray() {
		byte[] seed = {1,2,3,4,5,6,7,8};
		SecureRandom sr = new SecureRandom(seed);

		long l = 0;
		for (int i = 0; i < seed.length; i++) {
			l = (l << 8) | (seed[i] & 0xFF);
		}
		Random r = new Random(l);
		byte[] b1 = new byte[8];
		byte[] b2 = new byte[8];
		sr.nextBytes(b1);
		r.nextBytes(b2);
		for (int i = 0; i < b1.length; i++) {
			if (b1[i] != b2[i]) {
				fail("incorrect random bytes");
			}
		}
	}


	/*
	 * Class under test for SecureRandom getInstance(String)
	 */
	public final void testGetInstanceString() {
		try {
			SecureRandom.getInstance("SHA1PRNG");
			fail("No expected NoSuchAlgorithmException");
		} catch (NoSuchAlgorithmException e) {	
		}
	}

	
	public final void testGetProvider() {
		if (new SecureRandom().getProvider() != null) {
			fail("Non null provider");
		}
	}
	
	public final void testGetAlgorithm() {
		SecureRandom sr = new SecureRandom();
		if (!sr.getAlgorithm().equals("java.util.Random")) {
			fail("Incorrect algorithm");
		}
	}
	
	/*
	 * Class under test for void setSeed(byte[])
	 */
	public final void testSetSeedbyteArray() {
		SecureRandom sr = new SecureRandom();

		//Not specified behavior: throws NullPointerException if bytes is null
		try {
			sr.setSeed(null);
		} catch (NullPointerException e) {	
		}
		
		byte[] seed = {1,2,3,4};
		sr.setSeed(seed);
		
		byte[] seed1 = {1,2,3,4, -2, 100, 9, 111};
		sr.setSeed(seed1);
	}

	/**
	 * 
	 *
	 */
	public final void testGetSeed() {
		byte[] seed = SecureRandom.getSeed(5);
		new SecureRandom(seed).nextBytes(new byte[20]);
	}

	/**
	 * 
	 *
	 */
	public final void testGenerateSeed() {
		SecureRandom sr = new SecureRandom();
		byte[] seed = sr.generateSeed(5);
		new SecureRandom(seed).nextBytes(new byte[20]);
	}
}
