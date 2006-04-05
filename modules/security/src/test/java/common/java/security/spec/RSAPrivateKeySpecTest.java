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
* @author Vladimir N. Molotkov
* @version $Revision$
*/

package java.security.spec;

import java.math.BigInteger;

import junit.framework.TestCase;


/**
 * Tests for <code>RSAPrivateKeySpec</code> class fields and methods
 * 
 */
public class RSAPrivateKeySpecTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Constructor for RSAPrivateKeySpecTest.
     * @param name
     */
    public RSAPrivateKeySpecTest(String name) {
        super(name);
    }

    /**
     * Test for <code>RSAPrivateKeySpec(BigInteger,BigInteger)</code> ctor
     * Assertion: constructs <code>RSAPrivateKeySpec</code>
     * object using valid parameters
     */
    public final void testRSAPrivateKeySpec() {
        KeySpec ks = new RSAPrivateKeySpec(BigInteger.valueOf(1234567890L),
                                           BigInteger.valueOf(3L));
        assertTrue(ks instanceof RSAPrivateKeySpec);
    }

    /**
     * Test for <code>getModulus()</code> method<br>
     * Assertion: returns modulus
     */
    public final void testGetModulus() {
        RSAPrivateKeySpec rpks =
            new RSAPrivateKeySpec(BigInteger.valueOf(1234567890L),
                                  BigInteger.valueOf(3L));
        assertTrue(rpks.getModulus().longValue() == 1234567890L);
    }

    /**
     * Test for <code>getPrivateExponent()</code> method<br>
     * Assertion: returns private exponent
     */
    public final void testGetPrivateExponent() {
        RSAPrivateKeySpec rpks =
            new RSAPrivateKeySpec(BigInteger.valueOf(1234567890L),
                                  BigInteger.valueOf(3L));
        assertTrue(rpks.getPrivateExponent().longValue() == 3L);
    }

}
