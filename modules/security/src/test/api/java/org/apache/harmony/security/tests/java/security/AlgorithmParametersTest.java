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

import java.io.IOException;
import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.AlgorithmParametersSpi;
import java.security.Provider;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.DSAParameterSpec;
import java.security.spec.InvalidParameterSpecException;

import junit.framework.TestCase;

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

    /**
     * @tests java.security.AlgorithmParameters#getEncoded()
     */
    public void test_getEncoded() throws Exception {

        final byte[] enc = new byte[] { 0x02, 0x01, 0x03 };

        MyAlgorithmParameters paramSpi = new MyAlgorithmParameters() {
            protected byte[] engineGetEncoded() throws IOException {
                return enc;
            }
        };

        AlgorithmParameters params = new DummyAlgorithmParameters(paramSpi, p,
                "algorithm");

        //
        // test: IOException if not initialized
        //
        try {
            params.getEncoded();
            fail("should not get encoded from un-initialized instance");
        } catch (IOException e) {
            // expected
        }

        //
        // test: corresponding spi method is invoked
        //
        params.init(new MyAlgorithmParameterSpec());
        assertSame(enc, params.getEncoded());
    }

    /**
     * @tests java.security.AlgorithmParameters#getEncoded(String)
     */
    public void test_getEncodedLjava_lang_String() throws Exception {

        final byte[] enc = new byte[] { 0x02, 0x01, 0x03 };

        final String strFormatParam = "format";

        MyAlgorithmParameters paramSpi = new MyAlgorithmParameters() {
            protected byte[] engineGetEncoded(String format) throws IOException {
                assertEquals(strFormatParam, format);
                return enc;
            }
        };

        AlgorithmParameters params = new DummyAlgorithmParameters(paramSpi, p,
                "algorithm");

        //
        // test: IOException if not initialized
        //
        try {
            params.getEncoded(strFormatParam);
            fail("should not get encoded from un-initialized instance");
        } catch (IOException e) {
            // expected
        }

        //
        // test: corresponding spi method is invoked
        //
        params.init(new MyAlgorithmParameterSpec());
        assertSame(enc, params.getEncoded(strFormatParam));
        
        //
        // test: if format param is null
        //
        paramSpi = new MyAlgorithmParameters() {
            protected byte[] engineGetEncoded(String format) throws IOException {
                assertNull(format); // null is passed to spi-provider
                return enc;
            }
        };

        params = new DummyAlgorithmParameters(paramSpi, p, "algorithm");
        params.init(new MyAlgorithmParameterSpec());
        assertSame(enc, params.getEncoded(null));
    }

	/**
     * @tests java.security.AlgorithmParameters#getInstance(String)
     */
    public void test_getInstanceLjava_lang_String() throws Exception {

        AlgorithmParameters ap = AlgorithmParameters.getInstance("ABC");

        checkUnititialized(ap);

        ap.init(new MyAlgorithmParameterSpec());

        assertTrue("init() failed", MyAlgorithmParameters.runEngineInit1);

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

    /**
     * @tests java.security.AlgorithmParameters#getInstance(String, String)
     */
    public void test_getInstanceLjava_lang_StringLjava_lang_String()
            throws Exception {

        AlgorithmParameters ap = AlgorithmParameters.getInstance("ABC",
                "MyProvider");

        checkUnititialized(ap);

        ap.init(new byte[6]);

        assertTrue("init() failed", MyAlgorithmParameters.runEngineInit2);

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

    /**
     * @tests java.security.AlgorithmParameters#getInstance(String, Provider)
     */
    public void test_getInstanceLjava_lang_StringLjava_security_Provider()
            throws Exception {

        AlgorithmParameters ap = AlgorithmParameters.getInstance("ABC", p);

        checkUnititialized(ap);

        ap.init(new byte[6], "aaa");
        assertTrue("init() failed", MyAlgorithmParameters.runEngineInit3);

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
		    //make it compilable on 1.5
			ap.getParameterSpec((Class<AlgorithmParameterSpec>)new Object().getClass());
			fail("getParameterSpec(): No expected InvalidParameterSpecException");
		} catch (java.security.spec.InvalidParameterSpecException e) {
		}
		
        assertNull("Unititialized: toString() failed", ap.toString());
	}
	
	private void checkAP(AlgorithmParameters ap, Provider p) throws Exception {

        assertSame("getProvider() failed", p, ap.getProvider());
        assertEquals("getAlgorithm() failed", "ABC", ap.getAlgorithm());

        //make it compilable on 1.5
        ap.getParameterSpec((Class<AlgorithmParameterSpec>) new Object()
                .getClass());
        assertTrue("getParameterSpec() failed",
                MyAlgorithmParameters.runEngineGetParameterSpec);

        assertEquals("AlgorithmParameters", ap.toString());
        assertTrue("toString() failed", MyAlgorithmParameters.runEngineToString);
    }

	private class MyProvider extends Provider {
		MyProvider() {
			super("MyProvider", 1.0, "Provider for testing");
            put("AlgorithmParameters.ABC", MyAlgorithmParameters.class.getName());
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
    
    public static class MyAlgorithmParameters extends AlgorithmParametersSpi {

        public static boolean runEngineInit1 = false;

        public static boolean runEngineInit2 = false;

        public static boolean runEngineInit3 = false;

        public static boolean runEngineGetParameterSpec = false;

        public static boolean runEngineToString = false;

        protected void engineInit(AlgorithmParameterSpec paramSpec)
                throws InvalidParameterSpecException {
            runEngineInit1 = true;
        }

        protected void engineInit(byte[] params) throws IOException {
            runEngineInit2 = true;
        }

        protected void engineInit(byte[] params, String format)
                throws IOException {
            runEngineInit3 = true;
        }

        protected AlgorithmParameterSpec engineGetParameterSpec(Class paramSpec)
                throws InvalidParameterSpecException {
            runEngineGetParameterSpec = true;
            return null;
        }

        protected byte[] engineGetEncoded() throws IOException {
            return null;
        }

        protected byte[] engineGetEncoded(String format) throws IOException {
            return null;
        }

        protected String engineToString() {
            runEngineToString = true;
            return "AlgorithmParameters";
        }
    }
}
