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
 * @author Elena Semukhina
 * @version $Revision: 1.4.6.2 $
 */

package java.math;

import junit.framework.TestCase;

/**
 * Class:  java.math.BigDecimal
 * Methods: abs, compareTo, equals, hashCode, 
 * max, min, negate, signum
 */
public class BigDecimalCompareTest extends TestCase {
    /**
     * Abs() of a negative BigDecimal
     */
    public void testAbsNeg() {
        String a = "-123809648392384754573567356745735.63567890295784902768787678287E+21";
        BigDecimal aNumber = new BigDecimal(a);
        String result = "123809648392384754573567356745735635678902957849027687.87678287";
        assertTrue("incorrect value", aNumber.abs().toString().equals(result));
    }

    /**
     * Abs() of a positive BigDecimal
     */
    public void testAbsPos() {
        String a = "123809648392384754573567356745735.63567890295784902768787678287E+21";
        BigDecimal aNumber = new BigDecimal(a);
        String result = "123809648392384754573567356745735635678902957849027687.87678287";
        assertTrue("incorrect value", aNumber.abs().toString().equals(result));
    }

    /**
     * Compare to a number of an equal scale
     */
    public void testCompareEqualScale1() {
        String a = "12380964839238475457356735674573563567890295784902768787678287";
        int aScale = 18;
        String b = "4573563567890295784902768787678287";
        int bScale = 18;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        int result = 1;
        assertTrue("incorrect result", aNumber.compareTo(bNumber) == result);
    }

    /**
     * Compare to a number of an equal scale
     */
    public void testCompareEqualScale2() {
        String a = "12380964839238475457356735674573563567890295784902768787678287";
        int aScale = 18;
        String b = "4573563923487289357829759278282992758247567890295784902768787678287";
        int bScale = 18;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        int result = -1;
        assertTrue("incorrect result", aNumber.compareTo(bNumber) == result);
    }

    /**
     * Compare to a number of an greater scale
     */
    public void testCompareGreaterScale1() {
        String a = "12380964839238475457356735674573563567890295784902768787678287";
        int aScale = 28;
        String b = "4573563567890295784902768787678287";
        int bScale = 18;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        int result = 1;
        assertTrue("incorrect result", aNumber.compareTo(bNumber) == result);
    }

    /**
     * Compare to a number of an greater scale
     */
    public void testCompareGreaterScale2() {
        String a = "12380964839238475457356735674573563567890295784902768787678287";
        int aScale = 48;
        String b = "4573563567890295784902768787678287";
        int bScale = 2;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        int result = -1;
        assertTrue("incorrect result", aNumber.compareTo(bNumber) == result);
    }

    /**
     * Compare to a number of an less scale
     */
    public void testCompareLessScale1() {
        String a = "12380964839238475457356735674573563567890295784902768787678287";
        int aScale = 18;
        String b = "4573563567890295784902768787678287";
        int bScale = 28;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        int result = 1;
        assertTrue("incorrect result", aNumber.compareTo(bNumber) == result);
    }

    /**
     * Compare to a number of an less scale
     */
    public void testCompareLessScale2() {
        String a = "12380964839238475457356735674573";
        int aScale = 36;
        String b = "45735635948573894578349572001798379183767890295784902768787678287";
        int bScale = 48;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        int result = -1;
        assertTrue("incorrect result", aNumber.compareTo(bNumber) == result);
    }

    /**
     * Equals() for unequal BigDecimals
     */
    public void testEqualsUnequal1() {
       String a = "92948782094488478231212478987482988429808779810457634781384756794987";
       int aScale = -24;
       String b = "7472334223847623782375469293018787918347987234564568";
       int bScale = 13;
       BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
       BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
       assertTrue(!aNumber.equals(bNumber));
    }

    /**
     * Equals() for unequal BigDecimals
     */
    public void testEqualsUnequal2() {
       String a = "92948782094488478231212478987482988429808779810457634781384756794987";
       int aScale = -24;
       String b = "92948782094488478231212478987482988429808779810457634781384756794987";
       int bScale = 13;
       BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
       BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
       assertTrue(!aNumber.equals(bNumber));
    }

    /**
     * Equals() for unequal BigDecimals
     */
    public void testEqualsUnequal3() {
       String a = "92948782094488478231212478987482988429808779810457634781384756794987";
       int aScale = -24;
       String b = "92948782094488478231212478987482988429808779810457634781384756794987";
       BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
       assertTrue(!aNumber.equals(b));
    }

    /**
     * equals() for equal BigDecimals
     */
    public void testEqualsEqual() {
       String a = "92948782094488478231212478987482988429808779810457634781384756794987";
       int aScale = -24;
       String b = "92948782094488478231212478987482988429808779810457634781384756794987";
       int bScale = -24;
       BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
       BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
       assertTrue(aNumber.equals(bNumber));
    }

    /**
     * equals() for equal BigDecimals
     */
    public void testEqualsNull() {
       String a = "92948782094488478231212478987482988429808779810457634781384756794987";
       int aScale = -24;
       BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
       assertFalse(aNumber.equals(null));
    }

    /**
     * hashCode() for equal BigDecimals
     */
    public void testHashCodeEqual() {
       String a = "92948782094488478231212478987482988429808779810457634781384756794987";
       int aScale = -24;
       String b = "92948782094488478231212478987482988429808779810457634781384756794987";
       int bScale = -24;
       BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
       BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
       assertTrue("incorrect value", aNumber.hashCode() == bNumber.hashCode());
    }

    /**
     * hashCode() for unequal BigDecimals
     */
    public void testHashCodeUnequal() {
       String a = "8478231212478987482988429808779810457634781384756794987";
       int aScale = 41;
       String b = "92948782094488478231212478987482988429808779810457634781384756794987";
       int bScale = -24;
       BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
       BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
       assertTrue("incorrect value", aNumber.hashCode() != bNumber.hashCode());
    }

    /**
     * max() for equal BigDecimals
     */
    public void testMaxEqual() {
       String a = "8478231212478987482988429808779810457634781384756794987";
       int aScale = 41;
       String b = "8478231212478987482988429808779810457634781384756794987";
       int bScale = 41;
       String c = "8478231212478987482988429808779810457634781384756794987";
       int cScale = 41;
       BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
       BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
       BigDecimal cNumber = new BigDecimal(new BigInteger(c), cScale);
       assertTrue("incorrect value", aNumber.max(bNumber).equals(cNumber));
    }

    /**
     * max() for unequal BigDecimals
     */
    public void testMaxUnequal1() {
       String a = "92948782094488478231212478987482988429808779810457634781384756794987";
       int aScale = 24;
       String b = "92948782094488478231212478987482988429808779810457634781384756794987";
       int bScale = 41;
       String c = "92948782094488478231212478987482988429808779810457634781384756794987";
       int cScale = 24;
       BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
       BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
       BigDecimal cNumber = new BigDecimal(new BigInteger(c), cScale);
       assertTrue("incorrect value", aNumber.max(bNumber).equals(cNumber));
    }

    /**
     * max() for unequal BigDecimals
     */
    public void testMaxUnequal2() {
       String a = "92948782094488478231212478987482988429808779810457634781384756794987";
       int aScale = 41;
       String b = "94488478231212478987482988429808779810457634781384756794987";
       int bScale = 41;
       String c = "92948782094488478231212478987482988429808779810457634781384756794987";
       int cScale = 41;
       BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
       BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
       BigDecimal cNumber = new BigDecimal(new BigInteger(c), cScale);
       assertTrue("incorrect value", aNumber.max(bNumber).equals(cNumber));
    }

    /**
     * min() for equal BigDecimals
     */
    public void testMinEqual() {
       String a = "8478231212478987482988429808779810457634781384756794987";
       int aScale = 41;
       String b = "8478231212478987482988429808779810457634781384756794987";
       int bScale = 41;
       String c = "8478231212478987482988429808779810457634781384756794987";
       int cScale = 41;
       BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
       BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
       BigDecimal cNumber = new BigDecimal(new BigInteger(c), cScale);
       assertTrue("incorrect value", aNumber.min(bNumber).equals(cNumber));
    }

    /**
     * min() for unequal BigDecimals
     */
    public void testMinUnequal1() {
       String a = "92948782094488478231212478987482988429808779810457634781384756794987";
       int aScale = 24;
       String b = "92948782094488478231212478987482988429808779810457634781384756794987";
       int bScale = 41;
       String c = "92948782094488478231212478987482988429808779810457634781384756794987";
       int cScale = 41;
       BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
       BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
       BigDecimal cNumber = new BigDecimal(new BigInteger(c), cScale);
       assertTrue("incorrect value", aNumber.min(bNumber).equals(cNumber));
    }

    /**
     * min() for unequal BigDecimals
     */
    public void testMinUnequal2() {
       String a = "92948782094488478231212478987482988429808779810457634781384756794987";
       int aScale = 41;
       String b = "94488478231212478987482988429808779810457634781384756794987";
       int bScale = 41;
       String c = "94488478231212478987482988429808779810457634781384756794987";
       int cScale = 41;
       BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
       BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
       BigDecimal cNumber = new BigDecimal(new BigInteger(c), cScale);
       assertTrue("incorrect value", aNumber.min(bNumber).equals(cNumber));
    }

    /**
     * negate() for a positive BigDecimal
     */
    public void testNegatePositive() {
       String a = "92948782094488478231212478987482988429808779810457634781384756794987";
       int aScale = 41;
       String c = "-92948782094488478231212478987482988429808779810457634781384756794987";
       int cScale = 41;
       BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
       BigDecimal cNumber = new BigDecimal(new BigInteger(c), cScale);
       assertTrue("incorrect value", aNumber.negate().equals(cNumber));
    }

    /**
     * negate() for a negative BigDecimal
     */
    public void testNegateNegative() {
       String a = "-92948782094488478231212478987482988429808779810457634781384756794987";
       int aScale = 41;
       String c = "92948782094488478231212478987482988429808779810457634781384756794987";
       int cScale = 41;
       BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
       BigDecimal cNumber = new BigDecimal(new BigInteger(c), cScale);
       assertTrue("incorrect value", aNumber.negate().equals(cNumber));
    }

    /**
     * signum() for a positive BigDecimal
     */
    public void testSignumPositive() {
       String a = "92948782094488478231212478987482988429808779810457634781384756794987";
       int aScale = 41;
       BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
       assertTrue("incorrect value", aNumber.signum() == 1);
    }

    /**
     * signum() for a negative BigDecimal
     */
    public void testSignumNegative() {
       String a = "-92948782094488478231212478987482988429808779810457634781384756794987";
       int aScale = 41;
       BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
       assertTrue("incorrect value", aNumber.signum() == -1);
    }

    /**
     * signum() for zero
     */
    public void testSignumZero() {
       String a = "0";
       int aScale = 41;
       BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
       assertTrue("incorrect value", aNumber.signum() == 0);
    }
}
