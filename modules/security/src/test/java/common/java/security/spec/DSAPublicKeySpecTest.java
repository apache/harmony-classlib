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
 * Tests for <code>DSAPublicKeySpec</code>
 * 
 */
public class DSAPublicKeySpecTest extends TestCase {

    /**
     * Constructor for DSAPublicKeySpecTest.
     * @param name
     */
    public DSAPublicKeySpecTest(String name) {
        super(name);
    }

    /**
     * Test for <code>DSAPublicKeySpec</code> ctor
     */
    public final void testDSAPublicKeySpec() {
        KeySpec ks = new DSAPublicKeySpec(
                new BigInteger("1"), // y
                new BigInteger("2"), // p
                new BigInteger("3"), // q
                new BigInteger("4"));// g
        
        assertTrue(ks instanceof DSAPublicKeySpec);
    }

    /**
     * Test for <code>getG</code> method 
     */
    public final void testGetG() {
        DSAPublicKeySpec dpks = new DSAPublicKeySpec(
                new BigInteger("1"), // y
                new BigInteger("2"), // p
                new BigInteger("3"), // q
                new BigInteger("4"));// g
        
        assertTrue(dpks.getG().intValue() == 4);
    }

    /**
     * Test for <code>getP</code> method 
     */
    public final void testGetP() {
        DSAPublicKeySpec dpks = new DSAPublicKeySpec(
                new BigInteger("1"), // y
                new BigInteger("2"), // p
                new BigInteger("3"), // q
                new BigInteger("4"));// g
        
        assertTrue(dpks.getP().intValue() == 2);
    }

    /**
     * Test for <code>getQ</code> method 
     */
    public final void testGetQ() {
        DSAPublicKeySpec dpks = new DSAPublicKeySpec(
                new BigInteger("1"), // y
                new BigInteger("2"), // p
                new BigInteger("3"), // q
                new BigInteger("4"));// g
        
        assertTrue(dpks.getQ().intValue() == 3);
    }

    /**
     * Test for <code>getY</code> method 
     */
    public final void testGetY() {
        DSAPublicKeySpec dpks = new DSAPublicKeySpec(
                new BigInteger("1"), // y
                new BigInteger("2"), // p
                new BigInteger("3"), // q
                new BigInteger("4"));// g
        
        assertTrue(dpks.getY().intValue() == 1);
    }
}
