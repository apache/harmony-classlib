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
 * Tests for <code>RSAKeyGenParameterSpec</code> class fields and methods.
 * 
 */
public class RSAKeyGenParameterSpecTest extends TestCase {

    /**
     * Constructor for RSAKeyGenParameterSpecTest.
     * @param name
     */
    public RSAKeyGenParameterSpecTest(String name) {
        super(name);
    }

    /**
     * Test for <code>RSAKeyGenParameterSpec(int,BigInteger)</code> ctor
     * Assertion: constructs <code>RSAKeyGenParameterSpec</code>
     * object using valid parameters
     */
    public final void testRSAKeyGenParameterSpec() {
        AlgorithmParameterSpec aps =
            new RSAKeyGenParameterSpec(512, BigInteger.valueOf(0L));
        assertTrue(aps instanceof RSAKeyGenParameterSpec);
    }

    /**
     * Test for <code>getKeySize()</code> method<br>
     * Assertion: returns key size value
     */
    public final void testGetKeysize() {
        RSAKeyGenParameterSpec rkgps =
            new RSAKeyGenParameterSpec(512, BigInteger.valueOf(0L));
        assertTrue(rkgps.getKeysize() == 512);
    }

    /**
     * Test for <code>getPublicExponent()</code> method<br>
     * Assertion: returns public exponent value
     */
    public final void testGetPublicExponent() {
        RSAKeyGenParameterSpec rkgps =
            new RSAKeyGenParameterSpec(512, BigInteger.valueOf(0L));
        assertTrue(rkgps.getPublicExponent().intValue() == 0);
    }
    
    /**
     * Test for <code>F0</code> field<br>
     * Assertion: the public exponent value F0 = 3
     */
    public final void testF0Value() {
        assertTrue(RSAKeyGenParameterSpec.F0.intValue() == 3);        
    }
    
    /**
     * Test for <code>F4</code> field<br>
     * Assertion: the public exponent value F0 = 65537
     */
    public final void testF4Value() {
        assertTrue(RSAKeyGenParameterSpec.F4.intValue() == 65537);        
    }

}
