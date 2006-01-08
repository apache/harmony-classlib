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

import com.openintel.drl.security.test.PerformanceTest;

/**
 * Tests for <code>ECPublicKeySpec</code> class fields and methods.
 * 
 */
public class ECPublicKeySpecTest extends PerformanceTest {

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
     * Constructor for ECPublicKeySpecTest.
     * @param name
     */
    public ECPublicKeySpecTest(String name) {
        super(name);
    }

    //
    // Tests
    //
    // NOTE: the following tests use EC domain parameters
    // which are invalid for real EC cryptography application
    // but must be acceptable by the class under test according
    // to the API specification
    //

    /**
     * Test #1 for <code>ECPublicKeySpec(ECPoint, ECParameterSpec)</code> constructor<br> 
     * Assertion: creates <code>ECPublicKeySpec</code> instance<br>
     * Test preconditions: valid parameters passed<br>
     * Expected: must pass without any exceptions
     */
    public final void testECPublicKeySpec01() {
        // Valid (see note below) parameters set
        EllipticCurve c =
            new EllipticCurve(new ECFieldFp(BigInteger.valueOf(5L)),
                              BigInteger.ZERO,
                              BigInteger.valueOf(4L));
        ECPoint g = new ECPoint(BigInteger.ZERO, BigInteger.valueOf(2L));
        ECPublicKeySpec ks =
            new ECPublicKeySpec(g,
                    new ECParameterSpec(c, g, BigInteger.valueOf(5L), 10));
        
    }

   /**
     * Test #2 for <code>ECPublicKeySpec(ECPoint, ECParameterSpec)</code> constructor<br> 
     * Assertion: throws <code>NullPointerException</code> if
     * <code>w</code> or <code>params</code> is <code>null</code><br>
     * Test preconditions: pass <code>null</code> as mentioned parameters<br>
     * Expected: must throw <code>NullPointerException</code>
     */
    public final void testECPublicKeySpec02() {
        // Valid (see note below) parameters set
        EllipticCurve c =
            new EllipticCurve(new ECFieldFp(BigInteger.valueOf(5L)),
                              BigInteger.ZERO,
                              BigInteger.valueOf(4L));
        ECPoint g = new ECPoint(BigInteger.ZERO, BigInteger.valueOf(2L));

        // Test case 1: w is null
        boolean passed = false;
        try {
            ECPublicKeySpec ks = new ECPublicKeySpec(null,
                new ECParameterSpec(c, g, BigInteger.valueOf(5L), 10));
        } catch (NullPointerException ok) {
            passed = true;
            logln(getName() + ": " + ok);
        }
        assertTrue(passed);

        // Test case 2: params is null
        passed = false;
        try {
            ECPublicKeySpec ks = new ECPublicKeySpec(g, null);
        } catch (NullPointerException ok) {
            passed = true;
            logln(getName() + ": " + ok);
        }
        assertTrue(passed);

        // Test case 3: both w and params are null
        passed = false;
        try {
            ECPublicKeySpec ks = new ECPublicKeySpec(null, null);
        } catch (NullPointerException ok) {
            passed = true;
            logln(getName() + ": " + ok);
        }
        assertTrue(passed);
    }

    /**
      * Test #3 for <code>ECPublicKeySpec(ECPoint, ECParameterSpec)</code> constructor<br> 
      * Assertion: throws <code>IllegalArgumentException</code> if
      * <code>w</code> is point at infinity<br>
      * Test preconditions: pass <code>ECPoint.POINT_INFINITY</code>
      * as mentioned parameter value<br>
      * Expected: must throw <code>IllegalArgumentException</code>
      */
     public final void testECPublicKeySpec03() {
         // Valid (see note below) parameters set
         EllipticCurve c =
             new EllipticCurve(new ECFieldFp(BigInteger.valueOf(5L)),
                               BigInteger.ZERO,
                               BigInteger.valueOf(4L));
         ECPoint g = new ECPoint(BigInteger.ZERO, BigInteger.valueOf(2L));
         boolean passed = false;
         try {
             ECPublicKeySpec ks =
                 new ECPublicKeySpec(ECPoint.POINT_INFINITY,
                         new ECParameterSpec(c, g, BigInteger.valueOf(5L), 10));
         } catch (IllegalArgumentException ok) {
             passed = true;
             logln(getName() + ": " + ok);
         }
         assertTrue(passed);
     }

     /**
     * Test for <code>getParams()</code> method<br>
     * Assertion: returns associated EC parameters<br>
     * Test preconditions: <code>ECPublicKeySpec</code> instance
     * created using valid parameters<br>
     * Expected: must return params value which is equal
     * to the one passed to the constructor; (both must refer
     * the same object)
     */
    public final void testGetParams() {
        // Valid (see note below) parameters set
        EllipticCurve c =
            new EllipticCurve(new ECFieldFp(BigInteger.valueOf(5L)),
                              BigInteger.ZERO,
                              BigInteger.valueOf(4L));
        ECPoint g = new ECPoint(BigInteger.ZERO, BigInteger.valueOf(2L));
        ECParameterSpec params =
            new ECParameterSpec(c, g, BigInteger.valueOf(5L), 10);

        ECPublicKeySpec ks = new ECPublicKeySpec(g, params);
        ECParameterSpec paramsRet = ks.getParams();
        
        assertEquals(params, paramsRet);
        assertSame(params, paramsRet);
    }

    /**
     * Test for <code>getW()</code> method<br>
     * Assertion: returns associated public point<br>
     * Test preconditions: <code>ECPublicKeySpec</code> instance
     * created using valid parameters<br>
     * Expected: must return w value which is equal
     * to the one passed to the constructor; (both must refer
     * the same object)
     */
    public final void testGetW() {
        // Valid (see note below) parameters set
        EllipticCurve c =
            new EllipticCurve(new ECFieldFp(BigInteger.valueOf(5L)),
                              BigInteger.ZERO,
                              BigInteger.valueOf(4L));
        ECPoint g = new ECPoint(BigInteger.ZERO, BigInteger.valueOf(2L));
        ECParameterSpec params =
            new ECParameterSpec(c, g, BigInteger.valueOf(5L), 10);

        ECPublicKeySpec ks = new ECPublicKeySpec(g, params);
        ECPoint wRet = ks.getW();

        assertEquals(g, wRet);
        assertSame(g, wRet);
    }
}
