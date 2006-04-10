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
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.DSAParameterSpec;

public class AlgorithmParameterGeneratorTest extends junit.framework.TestCase {

	/**
	 * @tests java.security.AlgorithmParameterGenerator#generateParameters()
	 */
	public void test_generateParameters() {

		fail("Takes ages. Problem with SecureRandom and stub math ?");
		
		// Test for method java.security.AlgorithmParameters
		// java.security.AlgorithmParameterGenerator.generateParameters()
		try {
			AlgorithmParameterGenerator gen = AlgorithmParameterGenerator
					.getInstance("DSA");
			gen.init(1024);
			// WARNING - The next line can take MINUTES to run
			AlgorithmParameters params = gen.generateParameters();
			assertTrue("params is null", params != null);
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm");
		}
	}

	/**
	 * @tests java.security.AlgorithmParameterGenerator#getAlgorithm()
	 */
	public void test_getAlgorithm() {
		// Test for method java.lang.String
		// java.security.AlgorithmParameterGenerator.getAlgorithm()
		try {
			String alg = AlgorithmParameterGenerator.getInstance("DSA")
					.getAlgorithm();
			assertTrue("getAlgorithm ok", alg.equals("DSA"));
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm");
		}
	}

	/**
	 * @tests java.security.AlgorithmParameterGenerator#getInstance(java.lang.String)
	 */
	public void test_getInstanceLjava_lang_String() {
		// Test for method java.security.AlgorithmParameterGenerator
		// java.security.AlgorithmParameterGenerator.getInstance(java.lang.String)
		try {
			AlgorithmParameterGenerator.getInstance("DSA");
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm");
		}
	}

	/**
	 * @tests java.security.AlgorithmParameterGenerator#getInstance(java.lang.String,
	 *        java.lang.String)
	 */
	public void test_getInstanceLjava_lang_StringLjava_lang_String() {
		// Test for method java.security.AlgorithmParameterGenerator
		// java.security.AlgorithmParameterGenerator.getInstance(java.lang.String,
		// java.lang.String)

		// Opting for DSA here as it is pretty widely supported
		try {
			Provider[] provs = Security
					.getProviders("AlgorithmParameterGenerator.DSA");

			if (provs != null) {
				for (int i = 0; i < provs.length; i++) {
					Provider provider = provs[i];
					AlgorithmParameterGenerator.getInstance("DSA", provider
							.getName());
				}// end for
			} else {
				fail("No providers support AlgorithmParameterGenerator.DSA");
			}
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm");
		} catch (NoSuchProviderException e) {
			fail("getInstance did not find the provider");
		}

		// exception case - should throw IllegalArgumentException for null
		// provider
		try {
			AlgorithmParameterGenerator.getInstance("DSA", (String) null);
			fail("Should have thrown IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Expected
		} catch (Exception e) {
			fail("Expected IllegalArgumentException for null provider "
					+ "string, got " + e);
		}

		// exception case - should throw IllegalArgumentException for empty
		// provider
		try {
			AlgorithmParameterGenerator.getInstance("DSA", "");
			fail("Should have thrown IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Expected
		} catch (Exception e) {
			fail("Expected IllegalArgumentException for empty provider "
					+ "string, got " + e);
		}

		// exception case - should throw NoSuchProviderException
		try {
			AlgorithmParameterGenerator.getInstance("DSA", "IDontExist");
			fail("Should have thrown NoSuchProviderException");
		} catch (NoSuchProviderException e) {
			// Expected
		} catch (Exception e) {
			fail("Expected NoSuchProviderException for non-existent "
					+ "provider, got " + e);
		}
	}

	/**
	 * @tests java.security.AlgorithmParameterGenerator#getProvider()
	 */
	public void test_getProvider() {
		// Test for method java.security.Provider
		// java.security.AlgorithmParameterGenerator.getProvider()
		try {
			Provider p = AlgorithmParameterGenerator.getInstance("DSA")
					.getProvider();
			assertTrue("provider is null", p != null);
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm");
		}
	}

	/**
	 * @tests java.security.AlgorithmParameterGenerator#init(int)
	 */
	public void test_initI() {
		// Test for method void
		// java.security.AlgorithmParameterGenerator.init(int)
		try {
			AlgorithmParameterGenerator gen = AlgorithmParameterGenerator
					.getInstance("DSA");
			gen.init(1024);
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm");
		}
	}

	/**
	 * @tests java.security.AlgorithmParameterGenerator#init(int,
	 *        java.security.SecureRandom)
	 */
	public void test_initILjava_security_SecureRandom() {
		// Test for method void
		// java.security.AlgorithmParameterGenerator.init(int,
		// java.security.SecureRandom)
		try {
			AlgorithmParameterGenerator gen = AlgorithmParameterGenerator
					.getInstance("DSA");
			gen.init(1024, new SecureRandom());
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm");
		}
	}

	/**
	 * @tests java.security.AlgorithmParameterGenerator#init(java.security.spec.AlgorithmParameterSpec)
	 */
	public void test_initLjava_security_spec_AlgorithmParameterSpec() {
		// Test for method void
		// java.security.AlgorithmParameterGenerator.init(java.security.spec.AlgorithmParameterSpec)
		try {
			DSAParameterSpec spec = new DSAParameterSpec(BigInteger.ONE,
					BigInteger.ONE, BigInteger.ONE);
			AlgorithmParameterGenerator gen = AlgorithmParameterGenerator
					.getInstance("DSA");
			gen.init(spec);
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm DSA");
		} catch (InvalidAlgorithmParameterException e) {
			fail("InvalidAlgorithmParameterException getting spec");
		}
	}

	/**
	 * @tests java.security.AlgorithmParameterGenerator#init(java.security.spec.AlgorithmParameterSpec,
	 *        java.security.SecureRandom)
	 */
	public void test_initLjava_security_spec_AlgorithmParameterSpecLjava_security_SecureRandom() {
		// Test for method void
		// java.security.AlgorithmParameterGenerator.init(java.security.spec.AlgorithmParameterSpec,
		// java.security.SecureRandom)
		try {
			DSAParameterSpec spec = new DSAParameterSpec(BigInteger.ONE,
					BigInteger.ONE, BigInteger.ONE);
			AlgorithmParameterGenerator gen = AlgorithmParameterGenerator
					.getInstance("DSA");
			gen.init(spec, new SecureRandom());
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm DSA");
		} catch (InvalidAlgorithmParameterException e) {
			fail("InvalidAlgorithmParameterException getting spec");
		}
	}

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() {
	}

	/**
	 * Tears down the fixture, for example, close a network connection. This
	 * method is called after a test is executed.
	 */
	protected void tearDown() {
	}

}