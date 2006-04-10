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

package tests.api.java.security;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.DSAParameterSpec;

public class SignatureTest extends junit.framework.TestCase {

	private static final String MESSAGE = "abc";

	/**
	 * @tests java.security.Signature#clone()
	 */
	public void test_clone() {
		try {
			Signature s = Signature.getInstance("DSA");
			try {
				s.clone();
				fail("A Signature may not be cloneable");
			} catch (CloneNotSupportedException e) {
				// Expected - a Signature may not be cloneable
			}
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm");
		}
	}

	/**
	 * @tests java.security.Signature#getAlgorithm()
	 */
	public void test_getAlgorithm() {
		try {
			String alg = Signature.getInstance("DSA").getAlgorithm();
			assertTrue("getAlgorithm did not get DSA (" + alg + ")", alg
					.indexOf("DSA") != -1);
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm");
		}
	}

	/**
	 * @tests java.security.Signature#getInstance(java.lang.String)
	 */
	public void test_getInstanceLjava_lang_String() {
		try {
			Signature.getInstance("DSA");
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm");
		}
	}

	/**
	 * @tests java.security.Signature#getInstance(java.lang.String,
	 *        java.lang.String)
	 */
	public void test_getInstanceLjava_lang_StringLjava_lang_String() {
		try {
			Provider[] providers = Security.getProviders("Signature.DSA");
			assertNotNull("No providers support Signature.DSA", providers);
			for (int i = 0; i < providers.length; i++) {
				Signature.getInstance("DSA", providers[i].getName());
			}// end for
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm : " + e);
		} catch (NoSuchProviderException e) {
			fail("getInstance did not find named provider : " + e);
		}
	}

	/**
	 * @tests java.security.Signature#getParameter(java.lang.String)
	 */
	public void test_getParameterLjava_lang_String() {
		Signature sig = null;
		try {
			sig = Signature.getInstance("DSA");
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm : " + e);
		}

		boolean passed = false;
		try {
			sig.getParameter("r");
			sig.getParameter("s");
			passed = true;
		} catch (UnsupportedOperationException e) {
			passed = true;
		} catch (Exception e) {
			passed = false;
		}
		assertTrue("getParameter did not pass or threw incorrect exception",
				passed);
	}

	/**
	 * @tests java.security.Signature#getProvider()
	 */
	public void test_getProvider() {
		try {
			Provider p = Signature.getInstance("DSA").getProvider();
			assertNotNull("provider is null", p);
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm : " + e);
		}
	}

	/**
	 * @tests java.security.Signature#initSign(java.security.PrivateKey)
	 */
	public void test_initSignLjava_security_PrivateKey() {

		fail("Test hangs - dependent on full math implementation ??");

		Signature sig = null;
		try {
			sig = Signature.getInstance("DSA");
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm : " + e);
		}

		KeyPairGenerator keyGen = null;
		try {
			keyGen = KeyPairGenerator.getInstance("DSA");
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm : " + e);
		}
		SecureRandom random = new SecureRandom();
		keyGen.initialize(1024, random);
		KeyPair keys = keyGen.generateKeyPair();
		try {
			sig.initSign(keys.getPrivate());
		} catch (InvalidKeyException e) {
			fail("Invalid key : " + e);
		}
	}

	/**
	 * @tests java.security.Signature#initVerify(java.security.PublicKey)
	 */
	public void test_initVerifyLjava_security_PublicKey() {

		fail("Test hangs - dependent on full math implementation ??");

		Signature sig = null;
		try {
			sig = Signature.getInstance("DSA");
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm : " + e);
		}

		KeyPairGenerator keyGen = null;
		try {
			keyGen = KeyPairGenerator.getInstance("DSA");
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm : " + e);
		}
		SecureRandom random = new SecureRandom();
		keyGen.initialize(1024, random);
		KeyPair keys = keyGen.generateKeyPair();
		try {
			sig.initVerify(keys.getPublic());
		} catch (InvalidKeyException e) {
			fail("Invalid key : " + e);
		}
	}

	/**
	 * @tests java.security.Signature#setParameter(java.lang.String,
	 *        java.lang.Object)
	 */
	public void test_setParameterLjava_lang_StringLjava_lang_Object() {
		Signature sig = null;
		try {
			sig = Signature.getInstance("DSA");
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm : " + e);
		}

		try {
			sig.setParameter("r", BigInteger.ONE);
			sig.setParameter("s", BigInteger.ONE);
		} catch (InvalidParameterException e) {
			// Could be that it's an invalid param for the found algorithm
		} catch (UnsupportedOperationException e) {
			// Could be that the operation is not supported
		} catch (Exception e) {
			fail("test_setParameterLjava_lang_StringLjava_lang_Object "
					+ "threw incorrect exception : " + e);
		}
	}

	/**
	 * @tests java.security.Signature#setParameter(java.security.spec.AlgorithmParameterSpec)
	 */
	public void test_setParameterLjava_security_spec_AlgorithmParameterSpec() {
		Signature sig = null;
		try {
			sig = Signature.getInstance("DSA");
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm : " + e);
		}
		try {
			DSAParameterSpec spec = new DSAParameterSpec(BigInteger.ONE,
					BigInteger.ONE, BigInteger.ONE);
			sig.setParameter(spec);
		} catch (InvalidParameterException e) {
			// Could be that it's an invalid param for the found algorithm
		} catch (UnsupportedOperationException e) {
			// Could be that the operation is not supported
		} catch (Exception e) {
			fail("test_setParameterLjava_security_spec_AlgorithmParameterSpec "
					+ "threw incorrect exception : " + e);
		}
	}

	/**
	 * @tests java.security.Signature#sign()
	 */
	public void test_sign() {

		fail("Test hangs - dependent on full math implementation ??");

		Signature sig = null;
		try {
			sig = Signature.getInstance("DSA");
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm : " + e);
		}

		KeyPairGenerator keyGen = null;
		try {
			keyGen = KeyPairGenerator.getInstance("DSA");
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm : " + e);
		}
		SecureRandom random = new SecureRandom();
		keyGen.initialize(1024, random);
		KeyPair keys = keyGen.generateKeyPair();
		try {
			sig.initSign(keys.getPrivate());
		} catch (InvalidKeyException e) {
			fail("Invalid key : " + e);
		}
		try {
			sig.update(MESSAGE.getBytes());
		} catch (SignatureException e) {
			fail("Problem updating Signature bytes : " + e);
		}
		try {
			sig.sign();
		} catch (SignatureException e) {
			fail("Signature problem signing bytes : " + e);
		}
	}

	/**
	 * @tests java.security.Signature#toString()
	 */
	public void test_toString() {
		try {
			String str = Signature.getInstance("DSA").toString();
			assertNotNull("toString is null", str);
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm : " + e);
		}
	}

	/**
	 * @tests java.security.Signature#update(byte[])
	 */
	public void test_update$B() {

		fail("Test hangs - dependent on full math implementation ??");

		Signature sig = null;
		try {
			sig = Signature.getInstance("DSA");
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm : " + e);
		}

		KeyPairGenerator keyGen = null;
		try {
			keyGen = KeyPairGenerator.getInstance("DSA");
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm : " + e);
		}
		SecureRandom random = new SecureRandom();
		keyGen.initialize(1024, random);
		KeyPair keys = keyGen.generateKeyPair();
		try {
			sig.initSign(keys.getPrivate());
		} catch (InvalidKeyException e) {
			fail("Invalid key : " + e);
		}
		try {
			byte[] bytes = MESSAGE.getBytes();
			sig.update(bytes);
		} catch (SignatureException e) {
			fail("Problem updating Signature bytes : " + e);
		}
	}

	/**
	 * @tests java.security.Signature#update(byte[], int, int)
	 */
	public void test_update$BII() {

		fail("Test hangs - dependent on full math implementation ??");

		Signature sig = null;
		try {
			sig = Signature.getInstance("DSA");
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm : " + e);
		}

		KeyPairGenerator keyGen = null;
		try {
			keyGen = KeyPairGenerator.getInstance("DSA");
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm : " + e);
		}
		SecureRandom random = new SecureRandom();
		keyGen.initialize(1024, random);
		KeyPair keys = keyGen.generateKeyPair();
		try {
			sig.initSign(keys.getPrivate());
		} catch (InvalidKeyException e) {
			fail("Invalid key : " + e);
		}
		try {
			byte[] bytes = MESSAGE.getBytes();
			sig.update(bytes, 0, bytes.length);
		} catch (SignatureException e) {
			fail("Signature problem updating bytes : " + e);
		}
	}

	/**
	 * @tests java.security.Signature#update(byte)
	 */
	public void test_updateB() {

		fail("Test hangs - dependent on full math implementation ??");

		Signature sig = null;
		try {
			sig = Signature.getInstance("DSA");
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm : " + e);
		}

		KeyPairGenerator keyGen = null;
		try {
			keyGen = KeyPairGenerator.getInstance("DSA");
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm : " + e);
		}
		SecureRandom random = new SecureRandom();
		keyGen.initialize(1024, random);
		KeyPair keys = keyGen.generateKeyPair();
		try {
			sig.initSign(keys.getPrivate());
		} catch (InvalidKeyException e) {
			fail("Invalid key : " + e);
		}
		try {
			sig.update(MESSAGE.getBytes()[0]);
		} catch (SignatureException e) {
			fail("Problem updating Signature bytes : " + e);
		}
	}

	/**
	 * @tests java.security.Signature#verify(byte[])
	 */
	public void test_verify$B() {

		fail("Test hangs - dependent on full math implementation ??");

		Signature sig = null;
		try {
			sig = Signature.getInstance("DSA");
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm : " + e);
		}

		KeyPairGenerator keyGen = null;
		try {
			keyGen = KeyPairGenerator.getInstance("DSA");
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm : " + e);
		}
		SecureRandom random = new SecureRandom();
		keyGen.initialize(1024, random);
		KeyPair keys = keyGen.generateKeyPair();
		try {
			sig.initSign(keys.getPrivate());
		} catch (InvalidKeyException e) {
			fail("Invalid key : " + e);
		}
		try {
			sig.update(MESSAGE.getBytes());
		} catch (SignatureException e) {
			fail("Problem updating Signature bytes : " + e);
		}
		byte[] signature = null;
		try {
			signature = sig.sign();
		} catch (SignatureException e) {
			fail("Signature problem signing bytes : " + e);
		}

		try {
			sig.initVerify(keys.getPublic());
		} catch (InvalidKeyException e) {
			fail("Invalid key : " + e);
		}
		try {
			sig.update(MESSAGE.getBytes());
		} catch (SignatureException e) {
			fail("Problem updating Signature bytes : " + e);
		}
		boolean ok = false;
		try {
			ok = sig.verify(signature);
		} catch (SignatureException e) {
			fail("Signature problem verifying bytes : " + e);
		}

		assertTrue("Sign/Verify does not pass", ok);
	}
}