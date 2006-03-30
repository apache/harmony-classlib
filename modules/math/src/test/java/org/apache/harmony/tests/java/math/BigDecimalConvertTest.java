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
 * @version $Revision: 1.4.4.2 $
 */

package org.apache.harmony.tests.java.math;

import junit.framework.TestCase;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Class:  java.math.BigDecimal
 * Methods: doubleValue, floatValue, intValue, longValue,  
 * valueOf, toString, toBigInteger
 */
public class BigDecimalConvertTest extends TestCase {
    /**
     * Double value of a negative BigDecimal
     */
    public void testDoubleValueNeg() {
        String a = "-123809648392384754573567356745735.63567890295784902768787678287E+21";
        BigDecimal aNumber = new BigDecimal(a);
        double result = -1.2380964839238476E53;
        assertTrue("incorrect value", aNumber.doubleValue() == result);
    }

    /**
     * Double value of a positive BigDecimal
     */
    public void testDoubleValuePos() {
        String a = "123809648392384754573567356745735.63567890295784902768787678287E+21";
        BigDecimal aNumber = new BigDecimal(a);
        double result = 1.2380964839238476E53;
        assertTrue("incorrect value", aNumber.doubleValue() == result);
    }

    /**
     * Double value of a large positive BigDecimal
     */
    public void testDoubleValuePosInfinity() {
        String a = "123809648392384754573567356745735.63567890295784902768787678287E+400";
        BigDecimal aNumber = new BigDecimal(a);
        double result = Double.POSITIVE_INFINITY;
        assertTrue("incorrect value", aNumber.doubleValue() == result);
    }

    /**
     * Double value of a large negative BigDecimal
     */
    public void testDoubleValueNegInfinity() {
        String a = "-123809648392384754573567356745735.63567890295784902768787678287E+400";
        BigDecimal aNumber = new BigDecimal(a);
        double result = Double.NEGATIVE_INFINITY;
        assertTrue("incorrect value", aNumber.doubleValue() == result);
    }

    /**
     * Double value of a small negative BigDecimal
     */
    public void testDoubleValueMinusZero() {
        String a = "-123809648392384754573567356745735.63567890295784902768787678287E-400";
        BigDecimal aNumber = new BigDecimal(a);
        long minusZero = -9223372036854775808L;
        double result = aNumber.doubleValue();
        assertTrue("incorrect value", Double.doubleToLongBits(result) == minusZero);
    }

    /**
     * Double value of a small positive BigDecimal
     */
    public void testDoubleValuePlusZero() {
        String a = "123809648392384754573567356745735.63567890295784902768787678287E-400";
        BigDecimal aNumber = new BigDecimal(a);
        long zero = 0;
        double result = aNumber.doubleValue();
        assertTrue("incorrect value", Double.doubleToLongBits(result) == zero);
    }

    /**
     * Float value of a negative BigDecimal
     */
    public void testFloatValueNeg() {
        String a = "-1238096483923847.6356789029578E+21";
        BigDecimal aNumber = new BigDecimal(a);
        float result = -1.2380965E36F;
        assertTrue("incorrect value", aNumber.floatValue() == result);
    }

    /**
     * Float value of a positive BigDecimal
     */
    public void testFloatValuePos() {
        String a = "1238096483923847.6356789029578E+21";
        BigDecimal aNumber = new BigDecimal(a);
        float result = 1.2380965E36F;
        assertTrue("incorrect value", aNumber.floatValue() == result);
    }

    /**
     * Float value of a large positive BigDecimal
     */
    public void testFloatValuePosInfinity() {
        String a = "123809648373567356745735.6356789787678287E+200";
        BigDecimal aNumber = new BigDecimal(a);
        float result = Float.POSITIVE_INFINITY;
        assertTrue("incorrect value", aNumber.floatValue() == result);
    }

    /**
     * Float value of a large negative BigDecimal
     */
    public void testFloatValueNegInfinity() {
        String a = "-123809648392384755735.63567887678287E+200";
        BigDecimal aNumber = new BigDecimal(a);
        float result = Float.NEGATIVE_INFINITY;
        assertTrue("incorrect value", aNumber.floatValue() == result);
    }

    /**
     * Float value of a small negative BigDecimal
     */
    public void testFloatValueMinusZero() {
        String a = "-123809648392384754573567356745735.63567890295784902768787678287E-400";
        BigDecimal aNumber = new BigDecimal(a);
        int minusZero = -2147483648;
        float result = aNumber.floatValue();
        assertTrue("incorrect value", Float.floatToIntBits(result) == minusZero);
    }

    /**
     * Float value of a small positive BigDecimal
     */
    public void testFloatValuePlusZero() {
        String a = "123809648392384754573567356745735.63567890295784902768787678287E-400";
        BigDecimal aNumber = new BigDecimal(a);
        int zero = 0;
        float result = aNumber.floatValue();
        assertTrue("incorrect value", Float.floatToIntBits(result) == zero);
    }

    /**
     * Integer value of a negative BigDecimal
     */
    public void testIntValueNeg() {
        String a = "-123809648392384754573567356745735.63567890295784902768787678287E+21";
        BigDecimal aNumber = new BigDecimal(a);
        int result = 218520473;
        assertTrue("incorrect value", aNumber.intValue() == result);
    }

    /**
     * Integer value of a positive BigDecimal
     */
    public void testIntValuePos() {
        String a = "123809648392384754573567356745735.63567890295784902768787678287E+21";
        BigDecimal aNumber = new BigDecimal(a);
        int result = -218520473;
        assertTrue("incorrect value", aNumber.intValue() == result);
    }

    /**
     * Long value of a negative BigDecimal
     */
    public void testLongValueNeg() {
        String a = "-123809648392384754573567356745735.63567890295784902768787678287E+21";
        BigDecimal aNumber = new BigDecimal(a);
        long result = -1246043477766677607L;
        assertTrue("incorrect value", aNumber.longValue() == result);
    }

    /**
     * Long value of a positive BigDecimal
     */
    public void testLongValuePos() {
        String a = "123809648392384754573567356745735.63567890295784902768787678287E+21";
        BigDecimal aNumber = new BigDecimal(a);
        long result = 1246043477766677607L;
        assertTrue("incorrect value", aNumber.longValue() == result);
    }

    /**
     * Convert a positive BigDecimal to BigInteger
     */
    public void testToBigIntegerPos1() {
        String a = "123809648392384754573567356745735.63567890295784902768787678287E+21";
        BigInteger bNumber = new BigInteger("123809648392384754573567356745735635678902957849027687");
        BigDecimal aNumber = new BigDecimal(a);
        BigInteger result = aNumber.toBigInteger();
        assertTrue("incorrect value", result.equals(bNumber));
    }

    /**
     * Convert a positive BigDecimal to BigInteger
     */
    public void testToBigIntegerPos2() {
        String a = "123809648392384754573567356745735.63567890295784902768787678287E+15";
        BigInteger bNumber = new BigInteger("123809648392384754573567356745735635678902957849");
        BigDecimal aNumber = new BigDecimal(a);
        BigInteger result = aNumber.toBigInteger();
        assertTrue("incorrect value", result.equals(bNumber));
    }

    /**
     * Convert a positive BigDecimal to BigInteger
     */
    public void testToBigIntegerPos3() {
        String a = "123809648392384754573567356745735.63567890295784902768787678287E+45";
        BigInteger bNumber = new BigInteger("123809648392384754573567356745735635678902957849027687876782870000000000000000");
        BigDecimal aNumber = new BigDecimal(a);
        BigInteger result = aNumber.toBigInteger();
        assertTrue("incorrect value", result.equals(bNumber));
    }

    /**
     * Convert a negative BigDecimal to BigInteger
     */
    public void testToBigIntegerNeg1() {
        String a = "-123809648392384754573567356745735.63567890295784902768787678287E+21";
        BigInteger bNumber = new BigInteger("-123809648392384754573567356745735635678902957849027687");
        BigDecimal aNumber = new BigDecimal(a);
        BigInteger result = aNumber.toBigInteger();
        assertTrue("incorrect value", result.equals(bNumber));
    }

    /**
     * Convert a negative BigDecimal to BigInteger
     */
    public void testToBigIntegerNeg2() {
        String a = "-123809648392384754573567356745735.63567890295784902768787678287E+15";
        BigInteger bNumber = new BigInteger("-123809648392384754573567356745735635678902957849");
        BigDecimal aNumber = new BigDecimal(a);
        BigInteger result = aNumber.toBigInteger();
        assertTrue("incorrect value", result.equals(bNumber));
    }

    /**
     * Convert a negative BigDecimal to BigInteger
     */
    public void testToBigIntegerNeg3() {
        String a = "-123809648392384754573567356745735.63567890295784902768787678287E+45";
        BigInteger bNumber = new BigInteger("-123809648392384754573567356745735635678902957849027687876782870000000000000000");
        BigDecimal aNumber = new BigDecimal(a);
        BigInteger result = aNumber.toBigInteger();
         assertTrue("incorrect value", result.equals(bNumber));
    }

    /**
     * Convert a small BigDecimal to BigInteger
     */
    public void testToBigIntegerZero() {
        String a = "-123809648392384754573567356745735.63567890295784902768787678287E-500";
        BigInteger bNumber = new BigInteger("0");
        BigDecimal aNumber = new BigDecimal(a);
        BigInteger result = aNumber.toBigInteger();
        assertTrue("incorrect value", result.equals(bNumber));
    }

    /**
     * Convert a negative BigDecimal with a negative exponent 
     * to a plain string representation;
     * scale == 0.
     */
     public void testToPlainStringNegNegExp() {
        String a = "-123809648392384754573567356745735.63567890295784902768787678287E-100";
        BigDecimal aNumber = new BigDecimal(a);
        String result = "-0.000000000000000000000000000000000000000000000000000000000000000000012380964839238475457356735674573563567890295784902768787678287";
        assertTrue("incorrect value", aNumber.toPlainString().equals(result));
    }

    /**
     * Convert a negative BigDecimal with a positive exponent
     * to a plain string representation;
     * scale == 0.
     */
     public void testToPlainStringNegPosExp() {
        String a = "-123809648392384754573567356745735.63567890295784902768787678287E100";
        BigDecimal aNumber = new BigDecimal(a);
        String result = "-1238096483923847545735673567457356356789029578490276878767828700000000000000000000000000000000000000000000000000000000000000000000000";
        assertTrue("incorrect value", aNumber.toPlainString().equals(result));
    }

    /**
     * Convert a positive BigDecimal with a negative exponent
     * to a plain string representation;
     * scale == 0.
     */
     public void testToPlainStringPosNegExp() {
        String a = "123809648392384754573567356745735.63567890295784902768787678287E-100";
        BigDecimal aNumber = new BigDecimal(a);
        String result = "0.000000000000000000000000000000000000000000000000000000000000000000012380964839238475457356735674573563567890295784902768787678287";
        assertTrue("incorrect value", aNumber.toPlainString().equals(result));
    }

    /**
     * Convert a negative BigDecimal with a negative exponent
     * to a plain string representation;
     * scale == 0.
     */
     public void testToPlainStringPosPosExp() {
        String a = "123809648392384754573567356745735.63567890295784902768787678287E+100";
        BigDecimal aNumber = new BigDecimal(a);
        String result = "1238096483923847545735673567457356356789029578490276878767828700000000000000000000000000000000000000000000000000000000000000000000000";
        assertTrue("incorrect value", aNumber.toPlainString().equals(result));
    }

    /**
     * Convert a BigDecimal to a string representation;
     * scale == 0.
     */
     public void testToStringZeroScale() {
        String a = "-123809648392384754573567356745735635678902957849027687876782870";
        BigDecimal aNumber = new BigDecimal(new BigInteger(a));
        String result = "-123809648392384754573567356745735635678902957849027687876782870";
        assertTrue("incorrect value", aNumber.toString().equals(result));
    }

    /**
     * Convert a positive BigDecimal to a string representation
     */
    public void testToStringPos() {
        String a = "123809648392384754573567356745735.63567890295784902768787678287E-500";
        BigDecimal aNumber = new BigDecimal(a);
        String result = "1.2380964839238475457356735674573563567890295784902768787678287E-468";
        assertTrue("incorrect value", aNumber.toString().equals(result));
    }

    /**
     * Convert a negative BigDecimal to a string representation
     */
    public void testToStringNeg() {
        String a = "-123.4564563673567380964839238475457356735674573563567890295784902768787678287E-5";
        BigDecimal aNumber = new BigDecimal(a);
        String result = "-0.001234564563673567380964839238475457356735674573563567890295784902768787678287";
        assertTrue("incorrect value", aNumber.toString().equals(result));
    }

    /**
     * Create a BigDecimal from a positive long value; scale == 0
     */
    public void testValueOfPosZeroScale() {
        long a = 98374823947823578L;
        BigDecimal aNumber = BigDecimal.valueOf(a);
        String result = "98374823947823578";
        assertTrue("incorrect value", aNumber.toString().equals(result));
    }

    /**
     * Create a BigDecimal from a negative long value; scale is 0
     */
    public void testValueOfNegZeroScale() {
        long a = -98374823947823578L;
        BigDecimal aNumber = BigDecimal.valueOf(a);
        String result = "-98374823947823578";
        assertTrue("incorrect value", aNumber.toString().equals(result));
    }

    /**
     * Create a BigDecimal from a negative long value; scale is positive
     */
    public void testValueOfNegScalePos() {
        long a = -98374823947823578L;
        int scale = 12;
        BigDecimal aNumber = BigDecimal.valueOf(a, scale);
        String result = "-98374.823947823578";
        assertTrue("incorrect value", aNumber.toString().equals(result));
    }

    /**
     * Create a BigDecimal from a negative long value; scale is negative
     */
    public void testValueOfNegScaleNeg() {
        long a = -98374823947823578L;
        int scale = -12;
        BigDecimal aNumber = BigDecimal.valueOf(a, scale);
        String result = "-9.8374823947823578E+28";
        assertTrue("incorrect value", aNumber.toString().equals(result));
    }

    /**
     * Create a BigDecimal from a negative long value; scale == 0
     */
    public void testValueOfPosScalePos() {
        long a = 98374823947823578L;
        int scale = 12;
        BigDecimal aNumber = BigDecimal.valueOf(a, scale);
        String result = "98374.823947823578";
        assertTrue("incorrect value", aNumber.toString().equals(result));
    }

    /**
     * Create a BigDecimal from a negative long value; scale == 0
     */
    public void testValueOfPosScaleNeg() {
        long a = 98374823947823578L;
        int scale = -12;
        BigDecimal aNumber = BigDecimal.valueOf(a, scale);
        String result = "9.8374823947823578E+28";
        assertTrue("incorrect value", aNumber.toString().equals(result));
    }
}
