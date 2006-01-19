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
 * Tests for <code>ECParameterSpec</code> class fields and methods.
 * 
 */
public class ECParameterSpecTest extends PerformanceTest {

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
     * Constructor for ECParameterSpecTest.
     * @param name
     */
    public ECParameterSpecTest(String name) {
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
     * Test #1 for <code>ECParameterSpec(EllipticCurve, ECPoint, BigInteger, int)</code> constructor<br> 
     * Assertion: creates <code>ECParameterSpec</code> instance<br>
     * Test preconditions: valid parameters passed<br>
     * Expected: must pass without any exceptions
     */
    public final void testECParameterSpec01() {
        // Valid (see note below) parameters set
        EllipticCurve c =
            new EllipticCurve(new ECFieldFp(BigInteger.valueOf(5L)),
                              BigInteger.ZERO,
                              BigInteger.valueOf(4L));
        ECPoint g = new ECPoint(BigInteger.ZERO, BigInteger.valueOf(2L));
        AlgorithmParameterSpec ps =
            new ECParameterSpec(c, g, BigInteger.valueOf(5L), 10);        
    }

    /**
     * Test #2 for <code>ECParameterSpec(EllipticCurve, ECPoint, BigInteger, int)</code> constructor<br> 
     * Assertion: throws <code>NullPointerException</code> if
     * <code>curve</code>, <code>generator</code> or <code>order</code> is <code>null</code><br>
     * Test preconditions: pass <code>null</code> as mentioned parameters<br>
     * Expected: must throw <code>NullPointerException</code>
     */
    public final void testECParameterSpec02() {
        // Valid (see note below) parameters set
        EllipticCurve curve =
            new EllipticCurve(new ECFieldFp(BigInteger.valueOf(5L)),
                              BigInteger.ZERO,
                              BigInteger.valueOf(4L));
        ECPoint generator = new ECPoint(BigInteger.ZERO, BigInteger.valueOf(2L));
        BigInteger order = BigInteger.valueOf(5L);
        // Test case 1: curve is null
        boolean passed = false;
        try {
            AlgorithmParameterSpec ps =
                new ECParameterSpec(null, generator, order, 10);
        } catch (NullPointerException ok) {
            passed = true;
            logln(getName() + ": " + ok);
        }
        assertTrue(passed);

        // Test case 2: generator is null
        passed = false;
        try {
            AlgorithmParameterSpec ps =
                new ECParameterSpec(curve, null, order, 10);
        } catch (NullPointerException ok) {
            passed = true;
            logln(getName() + ": " + ok);
        }
        assertTrue(passed);

        // Test case 3: order is null
        passed = false;
        try {
            AlgorithmParameterSpec ps =
                new ECParameterSpec(curve, generator, null, 10);
        } catch (NullPointerException ok) {
            passed = true;
            logln(getName() + ": " + ok);
        }
        assertTrue(passed);

        // Test case 4: all above are null
        passed = false;
        try {
            AlgorithmParameterSpec ps =
                new ECParameterSpec(null, null, null, 10);
        } catch (NullPointerException ok) {
            passed = true;
            logln(getName() + ": " + ok);
        }
        assertTrue(passed);
    }

    /**
     * Test #3 for <code>ECParameterSpec(EllipticCurve, ECPoint, BigInteger, int)</code> constructor<br> 
     * Assertion: throws <code>IllegalArgumentException</code> if
     * <code>order</code> or <code>cofactor</code> is not positive<br>
     * Test preconditions: pass not positive as mentioned parameters<br>
     * Expected: must throw <code>IllegalArgumentException</code>
     */
    public final void testECParameterSpec03() {
        // Valid (see note below) parameters set
        EllipticCurve curve =
            new EllipticCurve(new ECFieldFp(BigInteger.valueOf(5L)),
                              BigInteger.ZERO,
                              BigInteger.valueOf(4L));
        ECPoint generator = new ECPoint(BigInteger.ZERO, BigInteger.valueOf(2L));
        // Test case 1: order is negative
        boolean passed = false;
        try {
            AlgorithmParameterSpec ps =
                new ECParameterSpec(curve, generator,
                        BigInteger.valueOf(-5L), 10);
        } catch (IllegalArgumentException ok) {
            passed = true;
            logln(getName() + ": " + ok);
        }
        assertTrue(passed);

        // Test case 2: order == 0
        passed = false;
        try {
            AlgorithmParameterSpec ps =
                new ECParameterSpec(curve, generator,
                        BigInteger.ZERO, 10);
        } catch (IllegalArgumentException ok) {
            passed = true;
            logln(getName() + ": " + ok);
        }
        assertTrue(passed);

        // Test case 3: cofactor is negative
        passed = false;
        try {
            AlgorithmParameterSpec ps =
                new ECParameterSpec(curve, generator,
                        BigInteger.valueOf(5L), -10);
        } catch (IllegalArgumentException ok) {
            passed = true;
            logln(getName() + ": " + ok);
        }
        assertTrue(passed);

        // Test case 4: cofactor == 0
        passed = false;
        try {
            AlgorithmParameterSpec ps =
                new ECParameterSpec(curve, generator,
                        BigInteger.valueOf(5L), 0);
        } catch (IllegalArgumentException ok) {
            passed = true;
            logln(getName() + ": " + ok);
        }
        assertTrue(passed);

        // Test case 5: both order and cofactor are not positive
        passed = false;
        try {
            AlgorithmParameterSpec ps =
                new ECParameterSpec(curve, generator,
                        BigInteger.valueOf(-5L), 0);
        } catch (IllegalArgumentException ok) {
            passed = true;
            logln(getName() + ": " + ok);
        }
        assertTrue(passed);
    }

    /**
     * Test for <code>getCofactor()</code> method<br>
     * Assertion: returns cofactor<br>
     * Test preconditions: <code>ECParameterSpec</code> instance
     * created using valid parameters<br>
     * Expected: must return cofactor value which is equal
     * to the one passed to the constructor
     */
    public final void testGetCofactor() {
        // Valid (see note below) parameters set
        EllipticCurve curve =
            new EllipticCurve(new ECFieldFp(BigInteger.valueOf(5L)),
                              BigInteger.ZERO,
                              BigInteger.valueOf(4L));
        ECPoint generator = new ECPoint(BigInteger.ZERO, BigInteger.valueOf(2L));
        BigInteger order = BigInteger.valueOf(5L);
        int cofactor = 10;
        ECParameterSpec ps = 
            new ECParameterSpec(curve, generator, order, cofactor);
        assertEquals(cofactor, ps.getCofactor());
    }

    /**
     * Test for <code>getCurve()</code> method<br>
     * Assertion: returns curve<br>
     * Test preconditions: <code>ECParameterSpec</code> instance
     * created using valid parameters<br>
     * Expected: must return ref to the <code>EllipticCurve</code> object
     * which is equal to the one passed to the constructor; (both must refer
     * the same object)
     */
    public final void testGetCurve() {
        // Valid (see note below) parameters set
        EllipticCurve curve =
            new EllipticCurve(new ECFieldFp(BigInteger.valueOf(5L)),
                              BigInteger.ZERO,
                              BigInteger.valueOf(4L));
        ECPoint generator = new ECPoint(BigInteger.ZERO, BigInteger.valueOf(2L));
        BigInteger order = BigInteger.valueOf(5L);
        int cofactor = 10;
        ECParameterSpec ps = 
            new ECParameterSpec(curve, generator, order, cofactor);
        EllipticCurve curveRet = ps.getCurve();
        assertEquals(curve, curveRet);
        assertSame(curve, curveRet);
    }

    /**
     * Test for <code>getGenerator()</code> method<br>
     * Assertion: returns generator<br>
     * Test preconditions: <code>ECParameterSpec</code> instance
     * created using valid parameters<br>
     * Expected: must return ref to the <code>ECPoint</code> object
     * which is equal to the one passed to the constructor; (both must refer
     * the same object)
     */
    public final void testGetGenerator() {
        // Valid (see note below) parameters set
        EllipticCurve curve =
            new EllipticCurve(new ECFieldFp(BigInteger.valueOf(5L)),
                              BigInteger.ZERO,
                              BigInteger.valueOf(4L));
        ECPoint generator = new ECPoint(BigInteger.ZERO, BigInteger.valueOf(2L));
        BigInteger order = BigInteger.valueOf(5L);
        int cofactor = 10;
        ECParameterSpec ps = 
            new ECParameterSpec(curve, generator, order, cofactor);
        ECPoint generatorRet = ps.getGenerator();
        assertEquals(generator, generatorRet);
        assertSame(generator, generatorRet);
    }

    /**
     * Test for <code>getOrder()</code> method<br>
     * Assertion: returns order<br>
     * Test preconditions: <code>ECParameterSpec</code> instance
     * created using valid parameters<br>
     * Expected: must return ref to the <code>BigInteger</code> object
     * which is equal to the one passed to the constructor; (both must refer
     * the same object)
     */
    public final void testGetOrder() {
        // Valid (see note below) parameters set
        EllipticCurve curve =
            new EllipticCurve(new ECFieldFp(BigInteger.valueOf(5L)),
                              BigInteger.ZERO,
                              BigInteger.valueOf(4L));
        ECPoint generator = new ECPoint(BigInteger.ZERO, BigInteger.valueOf(2L));
        BigInteger order = BigInteger.valueOf(5L);
        int cofactor = 10;
        ECParameterSpec ps = 
            new ECParameterSpec(curve, generator, order, cofactor);
        BigInteger orderRet = ps.getOrder();
        assertEquals(order, orderRet);
        assertSame(order, orderRet);
    }

}
