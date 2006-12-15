/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
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

package org.apache.harmony.security.tests.java.security;

import java.security.AlgorithmParameters;
import java.security.AlgorithmParametersSpi;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;

import junit.framework.TestCase;

import org.apache.harmony.security.tests.support.MyAlgorithmParameters;

/**
 * Tests for <code>AlgorithmParameters</code> class constructors and
 * methods.
 * 
 */
public class AlgorithmParametersTest extends TestCase {

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

    /**
     * @tests java.security.AlgorithmParameters#getAlgorithm()
     */
    public void test_getAlgorithm() throws Exception {

        // test: null value
        AlgorithmParameters ap = new DummyAlgorithmParameters(null, p, null);
        assertNull(ap.getAlgorithm());

        // test: not null value
        ap = new DummyAlgorithmParameters(null, p, "AAA");
        assertEquals("AAA", ap.getAlgorithm());
    }

	/*
	 * Class under test for AlgorithmParameters getInstance(String)
	 */
	public void testGetInstanceString() {
		AlgorithmParameters ap = null;
		
		try {
			ap = AlgorithmParameters.getInstance("ABC");		
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		}
		checkUnititialized(ap);
		
		try {
			ap.init(new MyAlgorithmParameterSpec());
		} catch (java.security.spec.InvalidParameterSpecException e) {
			fail(e.toString());
		}
		if (!MyAlgorithmParameters.runEngineInit1) {
			fail("init() failed");
		}
		try {
			ap.init(new byte[6]);
			fail("getEncoded(format): No expected IOException");
		} catch (java.io.IOException e) {
		}
		try {
			ap.init(new byte[6], "aaa");
			fail("getEncoded(format): No expected IOException");
		} catch (java.io.IOException e) {
		}
		checkAP(ap, p);
	}

	/*
	 * Class under test for AlgorithmParameters getInstance(String, String)
	 */
	public void testGetInstanceStringString() {
		AlgorithmParameters ap = null;
		
		try {
			ap = AlgorithmParameters.getInstance("ABC", "MyProvider");	
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		} catch (NoSuchProviderException e) {
			fail(e.toString());
		}
		checkUnititialized(ap);
		
		try {
			ap.init(new byte[6]);
		} catch (java.io.IOException e) {
			fail(e.toString());
		}
		if (!MyAlgorithmParameters.runEngineInit2) {
			fail("init() failed");
		}
		try {
			ap.init(new MyAlgorithmParameterSpec());
			fail("getEncoded(format): No expected InvalidParameterSpecException");
		} catch (java.security.spec.InvalidParameterSpecException e) {
		}
		try {
			ap.init(new byte[6], "aaa");
			fail("getEncoded(format): No expected IOException");
		} catch (java.io.IOException e) {
		}
		checkAP(ap, p);
	}

	/*
	 * Class under test for AlgorithmParameters getInstance(String, Provider)
	 */
	public void testGetInstanceStringProvider() {
		AlgorithmParameters ap = null;
		
		try {
			ap = AlgorithmParameters.getInstance("ABC", p);	
		} catch (NoSuchAlgorithmException e) {
			fail(e.toString());
		}
		checkUnititialized(ap);
		
		try {
			ap.init(new byte[6], "aaa");
		} catch (java.io.IOException e) {
			fail(e.toString());
		}
		if (!MyAlgorithmParameters.runEngineInit3) {
			fail("init() failed");
		}
		try {
			ap.init(new MyAlgorithmParameterSpec());
			fail("getEncoded(format): No expected InvalidParameterSpecException");
		} catch (java.security.spec.InvalidParameterSpecException e) {
		}
		try {
			ap.init(new byte[6]);
			fail("getEncoded(format): No expected IOException");
		} catch (java.io.IOException e) {
		}
		checkAP(ap, p);
	}

    /**
     * @tests java.security.AlgorithmParameters#getProvider()
     */
    public void test_getProvider() throws Exception {
        // test: null value
        AlgorithmParameters ap = new DummyAlgorithmParameters(null, null, "AAA");
        assertNull(ap.getProvider());

        // test: not null value
        ap = new DummyAlgorithmParameters(null, p, "AAA");
        assertSame(p, ap.getProvider());
    }

	private void checkUnititialized(AlgorithmParameters ap) {
		try {
			ap.getEncoded();
			fail("getEncoded(): No expected IOException");
		} catch (java.io.IOException e) {
		}
		
		try {
			ap.getEncoded("aaa");
			fail("getEncoded(format): No expected IOException");
		} catch (java.io.IOException e) {
		}
		
		try {
		    //make it compilable on 1.5
			ap.getParameterSpec((Class<AlgorithmParameterSpec>)new Object().getClass());
			fail("getParameterSpec(): No expected InvalidParameterSpecException");
		} catch (java.security.spec.InvalidParameterSpecException e) {
		}
		
		if (ap.toString() != null) {
			fail("Unititialized: toString() failed");
		}
	}
	
	private void checkAP(AlgorithmParameters ap, Provider p) {	
		if (ap.getProvider() != p) {
			fail("getProvider() failed");
		}
		if (!"ABC".equals(ap.getAlgorithm())) {
			fail("getAlgorithm() failed");			
		}
		
		try {
			ap.getEncoded();
		} catch (java.io.IOException e) {
			fail(e.toString());
		}
		if (!MyAlgorithmParameters.runEngineGetEncoded1) {
			fail("getEncoded() failed");
		}
		
		try {
			ap.getEncoded("aaa");
		} catch (java.io.IOException e) {
			fail(e.toString());
		}
		if (!MyAlgorithmParameters.runEngineGetEncoded2) {
			fail("getEncoded(format) failed");
		}		
		
		try {
			//make it compilable on 1.5
			ap.getParameterSpec((Class<AlgorithmParameterSpec>)new Object().getClass());
		} catch (java.security.spec.InvalidParameterSpecException e) {
			fail(e.toString());
		}
		if (!MyAlgorithmParameters.runEngineGetParameterSpec) {
			fail("getParameterSpec() failed");
		}
		
		if (!"AlgorithmParameters".equals(ap.toString()) ||
				!MyAlgorithmParameters.runEngineToString) {
			fail("toString() failed");
		}
	}

	private class MyProvider extends Provider {
		MyProvider() {
			super("MyProvider", 1.0, "Provider for testing");
			put("AlgorithmParameters.ABC", "org.apache.harmony.security.tests.support.MyAlgorithmParameters");
		}
		
		MyProvider(String name, double version, String info) {
			super(name, version, info);
		}
	}
	
	private class MyAlgorithmParameterSpec implements java.security.spec.AlgorithmParameterSpec{
	}
	
	private class DummyAlgorithmParameters extends AlgorithmParameters {
		public DummyAlgorithmParameters(AlgorithmParametersSpi paramSpi, 
				Provider provider, String algorithm) {
			super(paramSpi, provider, algorithm);
		}
	}
}
