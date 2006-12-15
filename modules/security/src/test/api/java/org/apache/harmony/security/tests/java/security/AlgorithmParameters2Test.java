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

import java.io.IOException;
import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.spec.DSAParameterSpec;
import java.util.Arrays;

public class AlgorithmParameters2Test extends junit.framework.TestCase {

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
