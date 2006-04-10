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

import java.io.IOException;
import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.security.spec.DSAParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;

public class AlgorithmParametersTest extends junit.framework.TestCase {

	/**
	 * @tests java.security.AlgorithmParameters#getAlgorithm()
	 */
	public void test_getAlgorithm() {
		// Test for method java.lang.String
		// java.security.AlgorithmParameters.getAlgorithm()
		try {
			String alg = AlgorithmParameters.getInstance("DSA").getAlgorithm();
			assertTrue("provider is null", alg.equals("DSA"));
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm");
		}
	}

	/**
	 * @tests java.security.AlgorithmParameters#getEncoded()
	 */
	public void test_getEncoded() {
		// Test for method byte []
		// java.security.AlgorithmParameters.getEncoded()
		AlgorithmParameters params = null;
		try {
			params = AlgorithmParameters.getInstance("DSA");
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm");
		}

		byte[] enc = null;
		try {
			enc = params.getEncoded();
			fail("should not get encoded from un-initialized instance");
		} catch (IOException e) {
			// expected
		}

		try {
			params.init(new DSAParameterSpec(BigInteger.ONE, BigInteger.ONE,
					BigInteger.ONE));
			enc = params.getEncoded();
			assertTrue("encoded is null", enc != null);
		} catch (InvalidParameterSpecException e) {
			fail("can't pass DSAParameterSpec");
		} catch (IOException e) {
			fail("IOException when passing DSAParameterSpec");
		} catch (Exception e) {
			fail("Caught unexpected exception : " + e);
		}
	}

	/**
	 * @tests java.security.AlgorithmParameters#getEncoded(java.lang.String)
	 */
	public void test_getEncodedLjava_lang_String() {
		// Test for method byte []
		// java.security.AlgorithmParameters.getEncoded(java.lang.String)
		AlgorithmParameters params = null;
		try {
			params = AlgorithmParameters.getInstance("DSA");
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm");
		}

		byte[] enc = null;
		try {
			params.init(new DSAParameterSpec(BigInteger.ONE, BigInteger.ONE,
					BigInteger.ONE));
			enc = params.getEncoded("JUNK");
			fail("bogus format should have resulted in IOException");
		} catch (InvalidParameterSpecException e) {
			fail("cant pass DSAParameterSpec");
		} catch (IOException e) {
			// expected
		}

		try {
			enc = params.getEncoded("ASN.1");
			assertTrue("ANS.1 should be supported", enc != null);
		} catch (IOException e) {
			fail("IOException with ASN.1");
		}
	}

	/**
	 * @tests java.security.AlgorithmParameters#getInstance(java.lang.String)
	 */
	public void test_getInstanceLjava_lang_String() {
		// Test for method java.security.AlgorithmParameters
		// java.security.AlgorithmParameters.getInstance(java.lang.String)
		try {
			AlgorithmParameters.getInstance("DSA");
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm");
		}
	}

	/**
	 * @tests java.security.AlgorithmParameters#getInstance(java.lang.String,
	 *        java.lang.String)
	 */
	public void test_getInstanceLjava_lang_StringLjava_lang_String() {
		// Test for method java.security.AlgorithmParameters
		// java.security.AlgorithmParameters.getInstance(java.lang.String,
		// java.lang.String)

		// Opting for DSA here as it is pretty widely supported
		try {
			Provider[] provs = Security.getProviders("AlgorithmParameters.DSA");

			if (provs != null) {
				for (int i = 0; i < provs.length; i++) {
					Provider provider = provs[i];
					AlgorithmParameters.getInstance("DSA", provider.getName());
				}// end for
			} else {
				fail("No providers support AlgorithmParameters.DSA");
			}
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm");
		} catch (NoSuchProviderException e) {
			fail("getInstance did not find the provider");
		}
	}

	/**
	 * @tests java.security.AlgorithmParameters#getParameterSpec(java.lang.Class)
	 */
	public void test_getParameterSpecLjava_lang_Class() {
		// Test for method java.security.spec.AlgorithmParameterSpec
		// java.security.AlgorithmParameters.getParameterSpec(java.lang.Class)
		AlgorithmParameters params = null;
		try {
			params = AlgorithmParameters.getInstance("DSA");
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm");
		}

		DSAParameterSpec dsaps = new DSAParameterSpec(BigInteger.ONE,
				BigInteger.ONE, BigInteger.ONE);
		try {
			params.init(dsaps);
		} catch (InvalidParameterSpecException e) {
			fail("cant pass DSAParameterSpec");
		}

		try {
			DSAParameterSpec spec = (DSAParameterSpec) params
					.getParameterSpec(dsaps.getClass());
			assertTrue("param spec is null", spec != null);
			assertTrue("p is wrong ", spec.getP().equals(BigInteger.ONE));
			assertTrue("q is wrong ", spec.getQ().equals(BigInteger.ONE));
			assertTrue("g is wrong ", spec.getG().equals(BigInteger.ONE));
		} catch (InvalidParameterSpecException e) {
			fail("InvalidParameterSpecException getting spec");
		}
	}

	/**
	 * @tests java.security.AlgorithmParameters#getProvider()
	 */
	public void test_getProvider() {
		// Test for method java.security.Provider
		// java.security.AlgorithmParameters.getProvider()
		try {
			Provider p = AlgorithmParameters.getInstance("DSA").getProvider();
			assertTrue("provider is null", p != null);
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm");
		}
	}

	/**
	 * @tests java.security.AlgorithmParameters#init(byte[])
	 */
	public void test_init$B() {
		// Test for method void java.security.AlgorithmParameters.init(byte [])
		try {
			AlgorithmParameters params = AlgorithmParameters.getInstance("DSA");
			params.init(new DSAParameterSpec(BigInteger.ONE, BigInteger.ONE,
					BigInteger.ONE));
			byte[] encoded = params.getEncoded();
			assertTrue("encoded spec is null", encoded != null);
			AlgorithmParameters params2 = AlgorithmParameters
					.getInstance("DSA");
			params2.init(encoded);
			byte[] encodedAfter = params2.getEncoded();
			assertTrue("param encoded is different", Arrays.equals(encoded,
					encodedAfter));
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm");
		} catch (IOException e) {
			fail("IOException getting encoded");
		} catch (InvalidParameterSpecException e) {
			fail("cant pass DSAParameterSpec");
		}
	}

	/**
	 * @tests java.security.AlgorithmParameters#init(byte[], java.lang.String)
	 */
	public void test_init$BLjava_lang_String() {
		// Test for method void java.security.AlgorithmParameters.init(byte [],
		// java.lang.String)
		try {
			AlgorithmParameters params = AlgorithmParameters.getInstance("DSA");
			params.init(new DSAParameterSpec(BigInteger.ONE, BigInteger.ONE,
					BigInteger.ONE));
			byte[] encoded = params.getEncoded();
			assertTrue("encoded spec is null", encoded != null);
			AlgorithmParameters params2 = AlgorithmParameters
					.getInstance("DSA");
			params2.init(encoded, "ASN.1");
			byte[] encodedAfter = params2.getEncoded();
			assertTrue("param encoded is different", Arrays.equals(encoded,
					encodedAfter));
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm");
		} catch (IOException e) {
			fail("IOException getting encoded");
		} catch (InvalidParameterSpecException e) {
			fail("cant pass DSAParameterSpec");
		}

		try {
			AlgorithmParameters params = AlgorithmParameters.getInstance("DSA");
			params.init(new DSAParameterSpec(BigInteger.ONE, BigInteger.ONE,
					BigInteger.ONE));
			byte[] encoded = params.getEncoded();
			assertTrue("encoded spec is null", encoded != null);
			params.init(encoded, "DOUGLASMAWSON");
			fail("unsupported format should have raised IOException");
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm");
		} catch (IOException e) {
			// expected
		} catch (InvalidParameterSpecException e) {
			fail("cant pass DSAParameterSpec");
		}

	}

	/**
	 * @tests java.security.AlgorithmParameters#init(java.security.spec.AlgorithmParameterSpec)
	 */
	public void test_initLjava_security_spec_AlgorithmParameterSpec() {
		// Test for method void
		// java.security.AlgorithmParameters.init(java.security.spec.AlgorithmParameterSpec)
		AlgorithmParameters params = null;
		try {
			params = AlgorithmParameters.getInstance("DSA");
		} catch (NoSuchAlgorithmException e) {
			fail("getInstance did not find algorithm");
		}

		try {
			params.init(new DSAParameterSpec(BigInteger.ONE, BigInteger.ONE,
					BigInteger.ONE));
		} catch (InvalidParameterSpecException e) {
			fail("cant pass DSAParameterSpec");
		}
	}

	/**
	 * @tests java.security.AlgorithmParameters#toString()
	 */
	public void test_toString() {
		// Test for method java.lang.String
		// java.security.AlgorithmParameters.toString()
		try {
			String str = AlgorithmParameters.getInstance("DSA").toString();
			assertNull("toString should be null", str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			assertTrue("getInstance did not find algorithm", false);
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