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
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.DSAParameterSpec;

public class AlgorithmParameterGeneratorTest extends junit.framework.TestCase {

	/**
	 * @tests java.security.AlgorithmParameterGenerator#generateParameters()
	 */
	public void test_generateParameters() throws Exception {

		//fail("Takes ages. Problem with SecureRandom and stub math ?");
		
		// Test for method java.security.AlgorithmParameters
		// java.security.AlgorithmParameterGenerator.generateParameters()
		AlgorithmParameterGenerator gen = AlgorithmParameterGenerator
				.getInstance("DSA");
		gen.init(1024);

		// WARNING - The next line can take MINUTES to run
		AlgorithmParameters params = gen.generateParameters();
		assertNotNull("params is null", params);
	}

	/**
	 * @tests java.security.AlgorithmParameterGenerator#getAlgorithm()
	 */
	public void test_getAlgorithm() throws Exception {
		// Test for method java.lang.String
		// java.security.AlgorithmParameterGenerator.getAlgorithm()
		String alg = AlgorithmParameterGenerator.getInstance("DSA")
				.getAlgorithm();
		assertEquals("getAlgorithm ok", "DSA", alg);
	}

	/**
	 * @tests java.security.AlgorithmParameterGenerator#getInstance(java.lang.String)
	 */
	public void test_getInstanceLjava_lang_String() throws Exception {
		// Test for method java.security.AlgorithmParameterGenerator
		// java.security.AlgorithmParameterGenerator.getInstance(java.lang.String)
		AlgorithmParameterGenerator.getInstance("DSA");
	}

	/**
	 * @tests java.security.AlgorithmParameterGenerator#getInstance(java.lang.String,
	 *        java.lang.String)
	 */
	public void test_getInstanceLjava_lang_StringLjava_lang_String() throws Exception {
		// Test for method java.security.AlgorithmParameterGenerator
		// java.security.AlgorithmParameterGenerator.getInstance(java.lang.String,
		// java.lang.String)

		// Opting for DSA here as it is pretty widely supported
       		Provider[] provs = Security
       				.getProviders("AlgorithmParameterGenerator.DSA");

		for (int i = 0; i < provs.length; i++) {
    			AlgorithmParameterGenerator.getInstance("DSA", provs[i].getName());
    		}// end for


		// exception case - should throw IllegalArgumentException for null
		// provider
		try {
			AlgorithmParameterGenerator.getInstance("DSA", (String) null);
			fail("Should have thrown IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Expected
		}

		// exception case - should throw IllegalArgumentException for empty
		// provider
		try {
			AlgorithmParameterGenerator.getInstance("DSA", "");
			fail("Should have thrown IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// Expected
		}

		// exception case - should throw NoSuchProviderException
		try {
			AlgorithmParameterGenerator.getInstance("DSA", "IDontExist");
			fail("Should have thrown NoSuchProviderException");
		} catch (NoSuchProviderException e) {
			// Expected
		}
	}

	/**
	 * @tests java.security.AlgorithmParameterGenerator#getProvider()
	 */
	public void test_getProvider() throws Exception {
		// Test for method java.security.Provider
		// java.security.AlgorithmParameterGenerator.getProvider()
        	// checks that no exception is thrown
		Provider p = AlgorithmParameterGenerator.getInstance("DSA")
				.getProvider();
		assertNotNull("provider is null", p);
	}

	/**
	 * @tests java.security.AlgorithmParameterGenerator#init(int)
	 */
	public void test_initI() throws Exception {
		// Test for method void
		// java.security.AlgorithmParameterGenerator.init(int)
        	// checks that no exception is thrown
		AlgorithmParameterGenerator gen = AlgorithmParameterGenerator
				.getInstance("DSA");
		gen.init(1024);
	}

	/**
	 * @tests java.security.AlgorithmParameterGenerator#init(int,
	 *        java.security.SecureRandom)
	 */
	public void test_initILjava_security_SecureRandom() throws Exception {
		// Test for method void
		// java.security.AlgorithmParameterGenerator.init(int,
		// java.security.SecureRandom)
        	// checks that no exception is thrown
		AlgorithmParameterGenerator gen = AlgorithmParameterGenerator
				.getInstance("DSA");
		gen.init(1024, new SecureRandom());
	}

	/**
	 * @tests java.security.AlgorithmParameterGenerator#init(java.security.spec.AlgorithmParameterSpec)
	 */
	public void test_initLjava_security_spec_AlgorithmParameterSpec() throws Exception {
        	// Test for method void
        	// java.security.AlgorithmParameterGenerator.init(java.security.spec.AlgorithmParameterSpec)
        	// checks that no exception is thrown
       		DSAParameterSpec spec = new DSAParameterSpec(BigInteger.ONE,
       				BigInteger.ONE, BigInteger.ONE);
       		AlgorithmParameterGenerator gen = AlgorithmParameterGenerator
       				.getInstance("DSA");
       		gen.init(spec);
	}

	/**
	 * @tests java.security.AlgorithmParameterGenerator#init(java.security.spec.AlgorithmParameterSpec,
	 *        java.security.SecureRandom)
	 */
	public void test_initLjava_security_spec_AlgorithmParameterSpecLjava_security_SecureRandom() throws Exception {
		// Test for method void
		// java.security.AlgorithmParameterGenerator.init(java.security.spec.AlgorithmParameterSpec,
		// java.security.SecureRandom)
        	// checks that no exception is thrown
		DSAParameterSpec spec = new DSAParameterSpec(BigInteger.ONE,
				BigInteger.ONE, BigInteger.ONE);
		AlgorithmParameterGenerator gen = AlgorithmParameterGenerator
				.getInstance("DSA");
		gen.init(spec, new SecureRandom());
	}
}