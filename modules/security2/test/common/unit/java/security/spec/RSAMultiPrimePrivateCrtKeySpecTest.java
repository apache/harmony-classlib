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

import org.apache.harmony.security.test.PerformanceTest;


/**
 * Tests for <code>RSAMultiPrimePrivateCrtKeySpec</code> class fields and methods.
 * 
 */
public class RSAMultiPrimePrivateCrtKeySpecTest extends PerformanceTest {
    /**
     * Reference array of RSAOtherPrimeInfo. DO NOT MODIFY
     */
    private static final RSAOtherPrimeInfo[] opi = new RSAOtherPrimeInfo[] {
            new RSAOtherPrimeInfo(BigInteger.ONE, BigInteger.ONE, BigInteger.ONE),
            new RSAOtherPrimeInfo(BigInteger.ONE, BigInteger.ONE, BigInteger.ONE),
            new RSAOtherPrimeInfo(BigInteger.ONE, BigInteger.ONE, BigInteger.ONE)
    };
    
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
     * Constructor for RSAMultiPrimePrivateCrtKeySpecTest.
     * @param name
     */
    public RSAMultiPrimePrivateCrtKeySpecTest(String name) {
        super(name);
    }

    // Test-cases:

    /**
     * Test #1 for
     * <code>RSAMultiPrimePrivateCrtKeySpec(BigInteger modulus,
     *                                      BigInteger publicExponent,
     *                                      BigInteger privateExponent,
     *                                      BigInteger primeP,
     *                                      BigInteger primeQ,
     *                                      BigInteger primeExponentP,
     *                                      BigInteger primeExponentQ,
     *                                      BigInteger crtCoefficient,
     *                                      RSAOtherPrimeInfo[] otherPrimeInfo)
     * </code> ctor<br>
     * Assertion: constructs <code>RSAMultiPrimePrivateCrtKeySpec</code>
     * object using valid parameters
     */
    public final void testRSAMultiPrimePrivateCrtKeySpec01() {
        KeySpec ks = new RSAMultiPrimePrivateCrtKeySpec(
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.ONE,
                opi);
        assertTrue(ks instanceof RSAMultiPrimePrivateCrtKeySpec);
    }

    /**
     * Test #2 for
     * <code>RSAMultiPrimePrivateCrtKeySpec(BigInteger modulus,
     *                                      BigInteger publicExponent,
     *                                      BigInteger privateExponent,
     *                                      BigInteger primeP,
     *                                      BigInteger primeQ,
     *                                      BigInteger primeExponentP,
     *                                      BigInteger primeExponentQ,
     *                                      BigInteger crtCoefficient,
     *                                      RSAOtherPrimeInfo[] otherPrimeInfo)
     * </code> ctor<br>
     * Assertion: NullPointerException if modulus is null
     */
    public final void testRSAMultiPrimePrivateCrtKeySpec02() {
        boolean passed = false;
        try {
            KeySpec ks = new RSAMultiPrimePrivateCrtKeySpec(
                    null,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    opi);
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                passed = true;
                logln(getName() + ": " + e);
            }
        }
        assertTrue(passed);
    }
    
    /**
     * Test #3 for
     * <code>RSAMultiPrimePrivateCrtKeySpec(BigInteger modulus,
     *                                      BigInteger publicExponent,
     *                                      BigInteger privateExponent,
     *                                      BigInteger primeP,
     *                                      BigInteger primeQ,
     *                                      BigInteger primeExponentP,
     *                                      BigInteger primeExponentQ,
     *                                      BigInteger crtCoefficient,
     *                                      RSAOtherPrimeInfo[] otherPrimeInfo)
     * </code> ctor<br>
     * Assertion: NullPointerException if publicExponent is null
     */
    public final void testRSAMultiPrimePrivateCrtKeySpec03() {
        boolean passed = false;
        try {
            KeySpec ks = new RSAMultiPrimePrivateCrtKeySpec(
                    BigInteger.ONE,
                    null,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    opi);
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                passed = true;
                logln(getName() + ": " + e);
            }
        }
        assertTrue(passed);
    }
    
    /**
     * Test #4 for
     * <code>RSAMultiPrimePrivateCrtKeySpec(BigInteger modulus,
     *                                      BigInteger publicExponent,
     *                                      BigInteger privateExponent,
     *                                      BigInteger primeP,
     *                                      BigInteger primeQ,
     *                                      BigInteger primeExponentP,
     *                                      BigInteger primeExponentQ,
     *                                      BigInteger crtCoefficient,
     *                                      RSAOtherPrimeInfo[] otherPrimeInfo)
     * </code> ctor<br>
     * Assertion: NullPointerException if privateExponent is null
     */
    public final void testRSAMultiPrimePrivateCrtKeySpec04() {
        boolean passed = false;
        try {
            KeySpec ks = new RSAMultiPrimePrivateCrtKeySpec(
                    BigInteger.ONE,
                    BigInteger.ONE,
                    null,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    opi);
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                passed = true;
                logln(getName() + ": " + e);
            }
        }
        assertTrue(passed);
    }
    
    /**
     * Test #5 for
     * <code>RSAMultiPrimePrivateCrtKeySpec(BigInteger modulus,
     *                                      BigInteger publicExponent,
     *                                      BigInteger privateExponent,
     *                                      BigInteger primeP,
     *                                      BigInteger primeQ,
     *                                      BigInteger primeExponentP,
     *                                      BigInteger primeExponentQ,
     *                                      BigInteger crtCoefficient,
     *                                      RSAOtherPrimeInfo[] otherPrimeInfo)
     * </code> ctor<br>
     * Assertion: NullPointerException if primeP is null
     */
    public final void testRSAMultiPrimePrivateCrtKeySpec05() {
        boolean passed = false;
        try {
            KeySpec ks = new RSAMultiPrimePrivateCrtKeySpec(
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    null,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    opi);
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                passed = true;
                logln(getName() + ": " + e);
            }
        }
        assertTrue(passed);
    }
    
    /**
     * Test #6 for
     * <code>RSAMultiPrimePrivateCrtKeySpec(BigInteger modulus,
     *                                      BigInteger publicExponent,
     *                                      BigInteger privateExponent,
     *                                      BigInteger primeP,
     *                                      BigInteger primeQ,
     *                                      BigInteger primeExponentP,
     *                                      BigInteger primeExponentQ,
     *                                      BigInteger crtCoefficient,
     *                                      RSAOtherPrimeInfo[] otherPrimeInfo)
     * </code> ctor<br>
     * Assertion: NullPointerException if primeQ is null
     */
    public final void testRSAMultiPrimePrivateCrtKeySpec06() {
        boolean passed = false;
        try {
            KeySpec ks = new RSAMultiPrimePrivateCrtKeySpec(
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    null,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    opi);
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                passed = true;
                logln(getName() + ": " + e);
            }
        }
        assertTrue(passed);
    }
    
    /**
     * Test #7 for
     * <code>RSAMultiPrimePrivateCrtKeySpec(BigInteger modulus,
     *                                      BigInteger publicExponent,
     *                                      BigInteger privateExponent,
     *                                      BigInteger primeP,
     *                                      BigInteger primeQ,
     *                                      BigInteger primeExponentP,
     *                                      BigInteger primeExponentQ,
     *                                      BigInteger crtCoefficient,
     *                                      RSAOtherPrimeInfo[] otherPrimeInfo)
     * </code> ctor<br>
     * Assertion: NullPointerException if primeExponentP is null
     */
    public final void testRSAMultiPrimePrivateCrtKeySpec07() {
        boolean passed = false;
        try {
            KeySpec ks = new RSAMultiPrimePrivateCrtKeySpec(
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    null,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    opi);
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                passed = true;
                logln(getName() + ": " + e);
            }
        }
        assertTrue(passed);
    }
    
    /**
     * Test #8 for
     * <code>RSAMultiPrimePrivateCrtKeySpec(BigInteger modulus,
     *                                      BigInteger publicExponent,
     *                                      BigInteger privateExponent,
     *                                      BigInteger primeP,
     *                                      BigInteger primeQ,
     *                                      BigInteger primeExponentP,
     *                                      BigInteger primeExponentQ,
     *                                      BigInteger crtCoefficient,
     *                                      RSAOtherPrimeInfo[] otherPrimeInfo)
     * </code> ctor<br>
     * Assertion: NullPointerException if primeExponentQ is null
     */
    public final void testRSAMultiPrimePrivateCrtKeySpec08() {
        boolean passed = false;
        try {
            KeySpec ks = new RSAMultiPrimePrivateCrtKeySpec(
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    null,
                    BigInteger.ONE,
                    opi);
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                passed = true;
                logln(getName() + ": " + e);
            }
        }
        assertTrue(passed);
    }
    
    /**
     * Test #9 for
     * <code>RSAMultiPrimePrivateCrtKeySpec(BigInteger modulus,
     *                                      BigInteger publicExponent,
     *                                      BigInteger privateExponent,
     *                                      BigInteger primeP,
     *                                      BigInteger primeQ,
     *                                      BigInteger primeExponentP,
     *                                      BigInteger primeExponentQ,
     *                                      BigInteger crtCoefficient,
     *                                      RSAOtherPrimeInfo[] otherPrimeInfo)
     * </code> ctor<br>
     * Assertion: NullPointerException if crtCoefficient is null
     */
    public final void testRSAMultiPrimePrivateCrtKeySpec09() {
        boolean passed = false;
        try {
            KeySpec ks = new RSAMultiPrimePrivateCrtKeySpec(
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    null,
                    opi);
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                passed = true;
                logln(getName() + ": " + e);
            }
        }
        assertTrue(passed);
    }
    
    /**
     * Test #10 for
     * <code>RSAMultiPrimePrivateCrtKeySpec(BigInteger modulus,
     *                                      BigInteger publicExponent,
     *                                      BigInteger privateExponent,
     *                                      BigInteger primeP,
     *                                      BigInteger primeQ,
     *                                      BigInteger primeExponentP,
     *                                      BigInteger primeExponentQ,
     *                                      BigInteger crtCoefficient,
     *                                      RSAOtherPrimeInfo[] otherPrimeInfo)
     * </code> ctor<br>
     * Assertion: otherPrimeInfo can be null
     */
    public final void testRSAMultiPrimePrivateCrtKeySpec10() {
        boolean passed = false;
        try {
            KeySpec ks = new RSAMultiPrimePrivateCrtKeySpec(
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    null);
            passed = true;
        } catch (Exception e) {
        }
        assertTrue(passed);
    }
    
    /**
     * Test #11 for
     * <code>RSAMultiPrimePrivateCrtKeySpec(BigInteger modulus,
     *                                      BigInteger publicExponent,
     *                                      BigInteger privateExponent,
     *                                      BigInteger primeP,
     *                                      BigInteger primeQ,
     *                                      BigInteger primeExponentP,
     *                                      BigInteger primeExponentQ,
     *                                      BigInteger crtCoefficient,
     *                                      RSAOtherPrimeInfo[] otherPrimeInfo)
     * </code> ctor<br>
     * Assertion: IllegalArgumentException if otherPrimeInfo length is 0
     */
    public final void testRSAMultiPrimePrivateCrtKeySpec11() {
        boolean passed = false;
        try {
            KeySpec ks = new RSAMultiPrimePrivateCrtKeySpec(
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    new RSAOtherPrimeInfo[0]);
        } catch (Exception e) {
            if (e instanceof IllegalArgumentException) {
                passed = true;
                logln(getName() + ": " + e);
            }
        }
        assertTrue(passed);
    }

    /**
     * Test #12 for
     * <code>RSAMultiPrimePrivateCrtKeySpec(BigInteger modulus,
     *                                      BigInteger publicExponent,
     *                                      BigInteger privateExponent,
     *                                      BigInteger primeP,
     *                                      BigInteger primeQ,
     *                                      BigInteger primeExponentP,
     *                                      BigInteger primeExponentQ,
     *                                      BigInteger crtCoefficient,
     *                                      RSAOtherPrimeInfo[] otherPrimeInfo)
     * </code> ctor<br>
     * Assertion: constructs <code>RSAMultiPrimePrivateCrtKeySpec</code>
     * object using valid parameters. Constructed object must be
     * instance of RSAPrivateKeySpec.
     */
    public final void testRSAMultiPrimePrivateCrtKeySpec12() {
        KeySpec ks = new RSAMultiPrimePrivateCrtKeySpec(
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.ONE,
                opi);
        assertTrue(ks instanceof RSAPrivateKeySpec);
    }
    
    /**
     * Test for <code>getCrtCoefficient()</code> method<br>
     * Assertion: returns crt coefficient
     */
    public final void testGetCrtCoefficient() {
        RSAMultiPrimePrivateCrtKeySpec ks =
            new RSAMultiPrimePrivateCrtKeySpec(
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    opi);
        assertTrue(BigInteger.ONE.equals(ks.getCrtCoefficient()));
    }

    /**
     * Test for <code>getPrimeExponentP()</code> method<br>
     * Assertion: returns prime exponent P
     */
    public final void testGetPrimeExponentP() {
        RSAMultiPrimePrivateCrtKeySpec ks =
            new RSAMultiPrimePrivateCrtKeySpec(
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    opi);
        assertTrue(BigInteger.ONE.equals(ks.getPrimeExponentP()));
    }

    /**
     * Test for <code>getPrimeExponentQ()</code> method<br>
     * Assertion: returns prime exponent Q
     */
    public final void testGetPrimeExponentQ() {
        RSAMultiPrimePrivateCrtKeySpec ks =
            new RSAMultiPrimePrivateCrtKeySpec(
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    opi);
        assertTrue(BigInteger.ONE.equals(ks.getPrimeExponentQ()));
    }

    /**
     * Test for <code>getPrimeP()</code> method<br>
     * Assertion: returns prime P
     */
    public final void testGetPrimeP() {
        RSAMultiPrimePrivateCrtKeySpec ks =
            new RSAMultiPrimePrivateCrtKeySpec(
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    opi);
        assertTrue(BigInteger.ONE.equals(ks.getPrimeP()));
    }

    /**
     * Test for <code>getPrimeQ()</code> method<br>
     * Assertion: returns prime Q
     */
    public final void testGetPrimeQ() {
        RSAMultiPrimePrivateCrtKeySpec ks =
            new RSAMultiPrimePrivateCrtKeySpec(
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    opi);
        assertTrue(BigInteger.ONE.equals(ks.getPrimeQ()));
    }

    /**
     * Test for <code>getPublicExponent()</code> method<br>
     * Assertion: returns public exponent
     */
    public final void testGetPublicExponent() {
        RSAMultiPrimePrivateCrtKeySpec ks =
            new RSAMultiPrimePrivateCrtKeySpec(
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    opi);
        assertTrue(BigInteger.ONE.equals(ks.getPublicExponent()));
    }

    /**
     * Test #1 for <code>getOtherPrimeInfo()</code> method<br>
     * Assertion: returns array of RSAOtherPrimeInfo
     */
    public final void testGetOtherPrimeInfo01() {
        RSAMultiPrimePrivateCrtKeySpec ks =
            new RSAMultiPrimePrivateCrtKeySpec(
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    opi);
        assertTrue(checkOtherPrimeInfo(ks.getOtherPrimeInfo()));
    }

    /**
     * Test #2 for <code>getOtherPrimeInfo()</code> method<br>
     * Assertion: returns null if null has been passed to the
     * constructor as otherPrimeInfo parameter
     */
    public final void testGetOtherPrimeInfo02() {
        RSAMultiPrimePrivateCrtKeySpec ks =
            new RSAMultiPrimePrivateCrtKeySpec(
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    null);
        assertTrue(ks.getOtherPrimeInfo() == null);
    }

    //
    // immutabelity tests
    //
    
    /**
     * Tests that internal state of the object
     * can not be modified by modifying initial array
     */
    public final void testIsStatePreserved1() {
        // Create initial array
        RSAOtherPrimeInfo[] opi1 = (RSAOtherPrimeInfo[])opi.clone();
        
        RSAMultiPrimePrivateCrtKeySpec ks = new RSAMultiPrimePrivateCrtKeySpec(
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.ONE,
                opi1);
        
        // Modify initial array
        opi1[2] = new RSAOtherPrimeInfo(BigInteger.ZERO,
                                        BigInteger.ZERO,
                                        BigInteger.ZERO);
        
        // Check that above modification
        // does not affect internal state
        assertTrue(checkOtherPrimeInfo(ks.getOtherPrimeInfo()));
    }
    
    /**
     * Tests that internal state of the object
     * can not be modified using array reference
     * returned by <code>getOtherPrimeInfo()</code>
     * method 
     */
    public final void testIsStatePreserved2() {
        // Create initial array
        RSAOtherPrimeInfo[] opi1 = (RSAOtherPrimeInfo[])opi.clone();
        
        RSAMultiPrimePrivateCrtKeySpec ks = new RSAMultiPrimePrivateCrtKeySpec(
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.ONE,
                BigInteger.ONE,
                opi1);
        
        RSAOtherPrimeInfo[] ret = ks.getOtherPrimeInfo();
        
        // Modify returned array
        ret[2] = new RSAOtherPrimeInfo(BigInteger.ZERO,
                BigInteger.ZERO,
                BigInteger.ZERO);
        
        // Check that above modification
        // does not affect internal state
        assertTrue(checkOtherPrimeInfo(ks.getOtherPrimeInfo()));
    }
    
    //
    // Tests for inherited methods
    //
    
    /**
     * Test for <code>getModulus()</code> method<br>
     * Assertion: returns modulus
     */
    public final void testGetModulus() {
        RSAMultiPrimePrivateCrtKeySpec ks =
            new RSAMultiPrimePrivateCrtKeySpec(
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    opi);
        assertTrue(BigInteger.ONE.equals(ks.getModulus()));
    }

    /**
     * Test for <code>getPrivateExponent()</code> method<br>
     * Assertion: returns private exponent
     */
    public final void testGetPrivateExponent() {
        RSAMultiPrimePrivateCrtKeySpec ks =
            new RSAMultiPrimePrivateCrtKeySpec(
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    BigInteger.ONE,
                    opi);
        assertTrue(BigInteger.ONE.equals(ks.getPrivateExponent()));
    }

// private stuff
//    
    /**
     * Compares array passed as a parameter with reference one<br>
     * 
     *  <code>private static final RSAOtherPrimeInfo[] opi</code>
     * 
     * @param toBeChecked
     *  Array to be compared
     * @return
     *  true if arrays are equal
     */
    private boolean checkOtherPrimeInfo(RSAOtherPrimeInfo[] toBeChecked) {
        if (toBeChecked == null || toBeChecked.length != opi.length) {
            return false;
        }
        for (int i=0; i<opi.length; i++) {
            if (opi[i].getPrime().equals(toBeChecked[i].getPrime()) &&
                opi[i].getExponent().equals(toBeChecked[i].getExponent()) &&
                opi[i].getCrtCoefficient().equals(toBeChecked[i].getCrtCoefficient())) {
                continue;
            }
            return false;
        }
        return true;
    }

}
