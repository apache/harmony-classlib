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

package org.apache.harmony.security.tests.java.security;

import java.io.IOException;
import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.security.spec.DSAParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;

public class AlgorithmParameters2Test extends junit.framework.TestCase {

	/**
	 * @tests java.security.AlgorithmParameters#getAlgorithm()
	 */
	public void test_getAlgorithm() throws Exception {
		// Test for method java.lang.String
		// java.security.AlgorithmParameters.getAlgorithm()
		String alg = AlgorithmParameters.getInstance("DSA").getAlgorithm();
		assertEquals("algorithm name should be DSA", alg, "DSA");
	}

	/**
	 * @tests java.security.AlgorithmParameters#getEncoded()
	 */
	public void test_getEncoded() throws Exception {
		// Test for method byte []
		// java.security.AlgorithmParameters.getEncoded()
		AlgorithmParameters params = AlgorithmParameters.getInstance("DSA");

		byte[] enc = null;
		try {
			enc = params.getEncoded();
			fail("should not get encoded from un-initialized instance");
		} catch (IOException e) {
			// expected
		}

		params.init(new DSAParameterSpec(BigInteger.ONE, BigInteger.ONE,
				BigInteger.ONE));
		enc = params.getEncoded();
		assertNotNull("encoded is null", enc);
	}

	/**
	 * @tests java.security.AlgorithmParameters#getEncoded(java.lang.String)
	 */
    public void test_getEncodedLjava_lang_String() throws Exception {
        // Test for method byte []
        // java.security.AlgorithmParameters.getEncoded(java.lang.String)
        AlgorithmParameters params = AlgorithmParameters.getInstance("DSA");

        params.init(new DSAParameterSpec(BigInteger.ONE, BigInteger.ONE,
                BigInteger.ONE));
        
        // getEncoded behavior is not specified for unknown 'format'
        // different providers work in different manner:
        // 1.5 RI provider uses primary encoding format,
        // but BC provider returns null.
        // As it is provider specific behavior - it is not tested.
        //byte[] enc = params.getEncoded("JUNK");

        byte[] enc = params.getEncoded("ASN.1");
        assertNotNull("ANS.1 should be supported", enc);
    }

	/**
	 * @tests java.security.AlgorithmParameters#getInstance(java.lang.String)
	 */
	public void test_getInstanceLjava_lang_String() throws Exception {
		// Test for method java.security.AlgorithmParameters
		// java.security.AlgorithmParameters.getInstance(java.lang.String)
		AlgorithmParameters.getInstance("DSA");
	}

	/**
	 * @tests java.security.AlgorithmParameters#getInstance(java.lang.String,
	 *        java.lang.String)
	 */
	public void test_getInstanceLjava_lang_StringLjava_lang_String() throws Exception {
		// Test for method java.security.AlgorithmParameters
		// java.security.AlgorithmParameters.getInstance(java.lang.String,
		// java.lang.String)

		// Opting for DSA here as it is pretty widely supported
       	Provider[] provs = Security.getProviders("AlgorithmParameters.DSA");

       	for (int i = 0; i < provs.length; i++) {
       		Provider provider = provs[i];
       		AlgorithmParameters.getInstance("DSA", provider.getName());
       	}// end for
	}

	/**
	 * @tests java.security.AlgorithmParameters#getParameterSpec(java.lang.Class)
	 */
	public void test_getParameterSpecLjava_lang_Class() throws Exception {
		// Test for method java.security.spec.AlgorithmParameterSpec
		// java.security.AlgorithmParameters.getParameterSpec(java.lang.Class)

		AlgorithmParameters params = AlgorithmParameters.getInstance("DSA");

		DSAParameterSpec dsaps = new DSAParameterSpec(BigInteger.ONE,
				BigInteger.ONE, BigInteger.ONE);

		params.init(dsaps);

       		DSAParameterSpec spec = (DSAParameterSpec) params
       				.getParameterSpec(dsaps.getClass());
       		assertNotNull("param spec is null", spec);
       		assertTrue("p is wrong ", spec.getP().equals(BigInteger.ONE));
       		assertTrue("q is wrong ", spec.getQ().equals(BigInteger.ONE));
       		assertTrue("g is wrong ", spec.getG().equals(BigInteger.ONE));
	}

	/**
	 * @tests java.security.AlgorithmParameters#getProvider()
	 */
	public void test_getProvider() throws Exception {
		// Test for method java.security.Provider
		// java.security.AlgorithmParameters.getProvider()
        Provider p = AlgorithmParameters.getInstance("DSA").getProvider();
        assertNotNull("provider is null", p);

	}

	/**
	 * @tests java.security.AlgorithmParameters#init(byte[])
	 */
	public void test_init$B() throws Exception {
		// Test for method void java.security.AlgorithmParameters.init(byte [])
        AlgorithmParameters params = AlgorithmParameters.getInstance("DSA");
        params.init(new DSAParameterSpec(BigInteger.ONE, BigInteger.ONE,
                BigInteger.ONE));
        byte[] encoded = params.getEncoded();
        assertNotNull("encoded spec is null", encoded);
        AlgorithmParameters params2 = AlgorithmParameters.getInstance("DSA");
        params2.init(encoded);
        byte[] encodedAfter = params2.getEncoded();
        assertTrue("param encoded is different", Arrays.equals(encoded,
                encodedAfter));
	}

	/**
	 * @tests java.security.AlgorithmParameters#init(byte[], java.lang.String)
	 */
	public void test_init$BLjava_lang_String() throws Exception {
		// Test for method void java.security.AlgorithmParameters.init(byte [],
		// java.lang.String)
        AlgorithmParameters params = AlgorithmParameters.getInstance("DSA");
        params.init(new DSAParameterSpec(BigInteger.ONE, BigInteger.ONE,
                BigInteger.ONE));
        byte[] encoded = params.getEncoded();
        assertNotNull("encoded spec is null", encoded);
        AlgorithmParameters params2 = AlgorithmParameters.getInstance("DSA");
        params2.init(encoded, "ASN.1");
        byte[] encodedAfter = params2.getEncoded();
        assertTrue("param encoded is different", Arrays.equals(encoded,
                encodedAfter));

		try {
			params = AlgorithmParameters.getInstance("DSA");
			params.init(new DSAParameterSpec(BigInteger.ONE, BigInteger.ONE,
					BigInteger.ONE));
			encoded = params.getEncoded();
			assertNotNull("encoded spec is null", encoded);
			params.init(encoded, "DOUGLASMAWSON");
			fail("unsupported format should have raised IOException");
		} catch (IOException e) {
			// expected
		}

	}

	/**
	 * @tests java.security.AlgorithmParameters#init(java.security.spec.AlgorithmParameterSpec)
	 */
	public void test_initLjava_security_spec_AlgorithmParameterSpec() throws Exception {
		// Test for method void
		// java.security.AlgorithmParameters.init(java.security.spec.AlgorithmParameterSpec)
		AlgorithmParameters params = null;
        params = AlgorithmParameters.getInstance("DSA");
        params.init(new DSAParameterSpec(BigInteger.ONE, BigInteger.ONE,
                BigInteger.ONE));
	}

	/**
	 * @tests java.security.AlgorithmParameters#toString()
	 */
	public void test_toString() throws Exception {
		// Test for method java.lang.String
		// java.security.AlgorithmParameters.toString()
        String str = AlgorithmParameters.getInstance("DSA").toString();
        assertNull("toString should be null", str);

	}
}
