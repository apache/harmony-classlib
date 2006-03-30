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
 * @version $Revision: 1.3.6.3 $
 */

package org.apache.harmony.tests.java.math;

import junit.framework.TestCase;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Class:  java.math.BigDecimal
 * Methods: add, subtract, multiply, divide 
 */
public class BigDecimalArithmeticTest extends TestCase {
    /**
     * Add two numbers of equal positive scales
     */
    public void testAddEqualScalePosPos() {
        String a = "1231212478987482988429808779810457634781384756794987";
        int aScale = 10;
        String b = "747233429293018787918347987234564568";
        int bScale = 10;
        String c = "123121247898748373566323807282924555312937.1991359555";
        int cScale = 10;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.add(bNumber);
        assertTrue("incorrect value " + result.toString(), result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == cScale);
    }

    /**
     * Add two numbers of equal negative scales
     */
    public void testAddEqualScaleNegNeg() {
        String a = "1231212478987482988429808779810457634781384756794987";
        int aScale = -10;
        String b = "747233429293018787918347987234564568";
        int bScale = -10;
        String c = "1.231212478987483735663238072829245553129371991359555E+61";
        int cScale = -10;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.add(bNumber);
        assertTrue("incorrect value " + result.toString(), result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == cScale);
    }

    /**
     * Add two numbers of different scales; the first is positive
     */
    public void testAddDiffScalePosNeg() {
        String a = "1231212478987482988429808779810457634781384756794987";
        int aScale = 15;
        String b = "747233429293018787918347987234564568";
        int bScale = -10;
        String c = "7472334294161400358170962860775454459810457634.781384756794987";
        int cScale = 15;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.add(bNumber);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == cScale);
    }

    /**
     * Add two numbers of different scales; the first is negative
     */
    public void testAddDiffScaleNegPos() {
        String a = "1231212478987482988429808779810457634781384756794987";
        int aScale = -15;
        String b = "747233429293018787918347987234564568";
        int bScale = 10;
        String c = "1231212478987482988429808779810457634781459480137916301878791834798.7234564568";
        int cScale = 10;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.add(bNumber);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == cScale);
    }

    /**
     * Add two zeroes of different scales; the first is negative
     */
    public void testAddDiffScaleZeroZero() {
        String a = "0";
        int aScale = -15;
        String b = "0";
        int bScale = 10;
        String c = "0E-10";
        int cScale = 10;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.add(bNumber);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == cScale);
    }

    /**
     * Subtract two numbers of equal positive scales
     */
    public void testSubtractEqualScalePosPos() {
        String a = "1231212478987482988429808779810457634781384756794987";
        int aScale = 10;
        String b = "747233429293018787918347987234564568";
        int bScale = 10;
        String c = "123121247898748224119637948679166971643339.7522230419";
        int cScale = 10;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.subtract(bNumber);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == cScale);
    }

    /**
     * Subtract two numbers of equal negative scales
     */
    public void testSubtractEqualScaleNegNeg() {
        String a = "1231212478987482988429808779810457634781384756794987";
        int aScale = -10;
        String b = "747233429293018787918347987234564568";
        int bScale = -10;
        String c = "1.231212478987482241196379486791669716433397522230419E+61";
        int cScale = -10;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.subtract(bNumber);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == cScale);
    }

    /**
     * Subtract two numbers of different scales; the first is positive
     */
    public void testSubtractDiffScalePosNeg() {
        String a = "1231212478987482988429808779810457634781384756794987";
        int aScale = 15;
        String b = "747233429293018787918347987234564568";
        int bScale = -10;
        String c = "-7472334291698975400195996883915836900189542365.218615243205013";
        int cScale = 15;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.subtract(bNumber);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == cScale);
    }

    /**
     * Subtract two numbers of different scales; the first is negative
     */
    public void testSubtractDiffScaleNegPos() {
        String a = "1231212478987482988429808779810457634781384756794987";
        int aScale = -15;
        String b = "747233429293018787918347987234564568";
        int bScale = 10;
        String c = "1231212478987482988429808779810457634781310033452057698121208165201.2765435432";
        int cScale = 10;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.subtract(bNumber);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == cScale);
    }

    /**
     * Multiply two numbers of positive scales
     */
    public void testMultiplyScalePosPos() {
        String a = "1231212478987482988429808779810457634781384756794987";
        int aScale = 15;
        String b = "747233429293018787918347987234564568";
        int bScale = 10;
        String c = "92000312286217574978643009574114545567010139156902666284589309.1880727173060570190220616";
        int cScale = 25;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.multiply(bNumber);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == cScale);
    }

    /**
     * Multiply two numbers of negative scales
     */
    public void testMultiplyEqualScaleNegNeg() {
        String a = "1231212478987482988429808779810457634781384756794987";
        int aScale = -15;
        String b = "747233429293018787918347987234564568";
        int bScale = -10;
        String c = "9.20003122862175749786430095741145455670101391569026662845893091880727173060570190220616E+111";
        int cScale = -25;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.multiply(bNumber);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == cScale);
    }

    /**
     * Multiply two numbers of different scales
     */
    public void testMultiplyDiffScalePosNeg() {
        String a = "1231212478987482988429808779810457634781384756794987";
        int aScale = 10;
        String b = "747233429293018787918347987234564568";
        int bScale = -10;
        String c = "920003122862175749786430095741145455670101391569026662845893091880727173060570190220616";
        int cScale = 0;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.multiply(bNumber);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == cScale);
    }

    /**
     * Multiply two numbers of different scales
     */
    public void testMultiplyDiffScaleNegPos() {
        String a = "1231212478987482988429808779810457634781384756794987";
        int aScale = -15;
        String b = "747233429293018787918347987234564568";
        int bScale = 10;
        String c = "9.20003122862175749786430095741145455670101391569026662845893091880727173060570190220616E+91";
        int cScale = -5;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.multiply(bNumber);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == cScale);
    }

    /**
     * Divide by zero
     */
    public void testDivideByZero() {
        String a = "1231212478987482988429808779810457634781384756794987";
        int aScale = 15;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = BigDecimal.valueOf(0L);
        try {
            BigDecimal result = aNumber.divide(bNumber, BigDecimal.ROUND_UNNECESSARY);
            fail("ArithmeticException has not been caught");
        } catch (ArithmeticException e) {
            assertTrue("Improper exception message", e.getMessage().equals("division by zero"));
        }
    }

    /**
     * Divide with ROUND_UNNECESSARY
     */
    public void testDivideExceptionRM() {
        String a = "1231212478987482988429808779810457634781384756794987";
        int aScale = 15;
        String b = "747233429293018787918347987234564568";
        int bScale = 10;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        try {
            BigDecimal result = aNumber.divide(bNumber, BigDecimal.ROUND_UNNECESSARY);
            fail("ArithmeticException has not been caught");
        } catch (ArithmeticException e) {
            assertTrue("Improper exception message", e.getMessage().equals("rounding mode is ROUND_UNNECESSARY but the result is not exact"));
        }
    }

    /**
     * Divide with invalid rounding mode
     */
    public void testDivideExceptionInvalidRM() {
        String a = "1231212478987482988429808779810457634781384756794987";
        int aScale = 15;
        String b = "747233429293018787918347987234564568";
        int bScale = 10;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        try {
            BigDecimal result = aNumber.divide(bNumber, 100);
            fail("IllegalArgumentException has not been caught");
        } catch (IllegalArgumentException e) {
            assertTrue("Improper exception message", e.getMessage().equals("invalid rounding mode"));
        }
    }

    /**
     * Divide: local variable exponent is less than zero
     */
    public void testDivideExpLessZero() {
        String a = "1231212478987482988429808779810457634781384756794987";
        int aScale = 15;
        String b = "747233429293018787918347987234564568";
        int bScale = 10;
        String c = "1.64770E+10";
        int resScale = -5;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.divide(bNumber, resScale, BigDecimal.ROUND_CEILING);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == resScale);
    }

    /**
     * Divide: local variable exponent is equal to zero
     */
    public void testDivideExpEqualsZero() {
        String a = "1231212478987482988429808779810457634781384756794987";
        int aScale = -15;
        String b = "747233429293018787918347987234564568";
        int bScale = 10;
        String c = "1.64769459009933764189139568605273529E+40";
        int resScale = -5;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.divide(bNumber, resScale, BigDecimal.ROUND_CEILING);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == resScale);
    }

    /**
     * Divide: local variable exponent is greater than zero
     */
    public void testDivideExpGreaterZero() {
        String a = "1231212478987482988429808779810457634781384756794987";
        int aScale = -15;
        String b = "747233429293018787918347987234564568";
        int bScale = 20;
        String c = "1.647694590099337641891395686052735285121058381E+50";
        int resScale = -5;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.divide(bNumber, resScale, BigDecimal.ROUND_CEILING);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == resScale);
    }

    /**
     * Divide: remainder is zero
     */
    public void testDivideRemainderIsZero() {
        String a = "8311389578904553209874735431110";
        int aScale = -15;
        String b = "237468273682987234567849583746";
        int bScale = 20;
        String c = "3.5000000000000000000000000000000E+36";
        int resScale = -5;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.divide(bNumber, resScale, BigDecimal.ROUND_CEILING);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == resScale);
    }
    
    /**
     * Divide: rounding mode is ROUND_UP, result is negative
     */
    public void testDivideRoundUpNeg() {
        String a = "-92948782094488478231212478987482988429808779810457634781384756794987";
        int aScale = -24;
        String b = "7472334223847623782375469293018787918347987234564568";
        int bScale = 13;
        String c = "-1.24390557635720517122423359799284E+53";
        int resScale = -21;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.divide(bNumber, resScale, BigDecimal.ROUND_UP);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == resScale);
    }

    /**
     * Divide: rounding mode is ROUND_UP, result is positive
     */
    public void testDivideRoundUpPos() {
        String a = "92948782094488478231212478987482988429808779810457634781384756794987";
        int aScale = -24;
        String b = "7472334223847623782375469293018787918347987234564568";
        int bScale = 13;
        String c = "1.24390557635720517122423359799284E+53";
        int resScale = -21;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.divide(bNumber, resScale, BigDecimal.ROUND_UP);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == resScale);
    }

    /**
     * Divide: rounding mode is ROUND_DOWN, result is negative
     */
    public void testDivideRoundDownNeg() {
        String a = "-92948782094488478231212478987482988429808779810457634781384756794987";
        int aScale = -24;
        String b = "7472334223847623782375469293018787918347987234564568";
        int bScale = 13;
        String c = "-1.24390557635720517122423359799283E+53";
        int resScale = -21;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.divide(bNumber, resScale, BigDecimal.ROUND_DOWN);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == resScale);
    }

    /**
     * Divide: rounding mode is ROUND_DOWN, result is positive
     */
    public void testDivideRoundDownPos() {
        String a = "92948782094488478231212478987482988429808779810457634781384756794987";
        int aScale = -24;
        String b = "7472334223847623782375469293018787918347987234564568";
        int bScale = 13;
        String c = "1.24390557635720517122423359799283E+53";
        int resScale = -21;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.divide(bNumber, resScale, BigDecimal.ROUND_DOWN);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == resScale);
    }
    
    /**
     * Divide: rounding mode is ROUND_FLOOR, result is positive
     */
    public void testDivideRoundFloorPos() {
        String a = "92948782094488478231212478987482988429808779810457634781384756794987";
        int aScale = -24;
        String b = "7472334223847623782375469293018787918347987234564568";
        int bScale = 13;
        String c = "1.24390557635720517122423359799283E+53";
        int resScale = -21;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.divide(bNumber, resScale, BigDecimal.ROUND_FLOOR);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == resScale);
    }

    /**
     * Divide: rounding mode is ROUND_FLOOR, result is negative
     */
    public void testDivideRoundFloorNeg() {
        String a = "-92948782094488478231212478987482988429808779810457634781384756794987";
        int aScale = -24;
        String b = "7472334223847623782375469293018787918347987234564568";
        int bScale = 13;
        String c = "-1.24390557635720517122423359799284E+53";
        int resScale = -21;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.divide(bNumber, resScale, BigDecimal.ROUND_FLOOR);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == resScale);
    }
    
    /**
     * Divide: rounding mode is ROUND_CEILING, result is positive
     */
    public void testDivideRoundCeilingPos() {
        String a = "92948782094488478231212478987482988429808779810457634781384756794987";
        int aScale = -24;
        String b = "7472334223847623782375469293018787918347987234564568";
        int bScale = 13;
        String c = "1.24390557635720517122423359799284E+53";
        int resScale = -21;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.divide(bNumber, resScale, BigDecimal.ROUND_CEILING);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == resScale);
    }

    /**
     * Divide: rounding mode is ROUND_CEILING, result is negative
     */
    public void testDivideRoundCeilingNeg() {
        String a = "-92948782094488478231212478987482988429808779810457634781384756794987";
        int aScale = -24;
        String b = "7472334223847623782375469293018787918347987234564568";
        int bScale = 13;
        String c = "-1.24390557635720517122423359799283E+53";
        int resScale = -21;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.divide(bNumber, resScale, BigDecimal.ROUND_CEILING);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == resScale);
    }
    
    /**
     * Divide: rounding mode is ROUND_HALF_UP, result is positive; distance = -1
     */
    public void testDivideRoundHalfUpPos() {
        String a = "92948782094488478231212478987482988429808779810457634781384756794987";
        int aScale = -24;
        String b = "7472334223847623782375469293018787918347987234564568";
        int bScale = 13;
        String c = "1.24390557635720517122423359799284E+53";
        int resScale = -21;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.divide(bNumber, resScale, BigDecimal.ROUND_HALF_UP);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == resScale);
    }

    /**
     * Divide: rounding mode is ROUND_HALF_UP, result is negative; distance = -1
     */
    public void testDivideRoundHalfUpNeg() {
        String a = "-92948782094488478231212478987482988429808779810457634781384756794987";
        int aScale = -24;
        String b = "7472334223847623782375469293018787918347987234564568";
        int bScale = 13;
        String c = "-1.24390557635720517122423359799284E+53";
        int resScale = -21;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.divide(bNumber, resScale, BigDecimal.ROUND_HALF_UP);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == resScale);
    }
    
    /**
     * Divide: rounding mode is ROUND_HALF_UP, result is positive; distance = 1
     */
    public void testDivideRoundHalfUpPos1() {
        String a = "92948782094488478231212478987482988798104576347813847567949855464535634534563456";
        int aScale = -24;
        String b = "74723342238476237823754692930187879183479";
        int bScale = 13;
        String c = "1.2439055763572051712242335979928354832010167729111113605E+76";
        int resScale = -21;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.divide(bNumber, resScale, BigDecimal.ROUND_HALF_UP);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == resScale);
    }

    /**
     * Divide: rounding mode is ROUND_HALF_UP, result is negative; distance = 1
     */
    public void testDivideRoundHalfUpNeg1() {
        String a = "-92948782094488478231212478987482988798104576347813847567949855464535634534563456";
        int aScale = -24;
        String b = "74723342238476237823754692930187879183479";
        int bScale = 13;
        String c = "-1.2439055763572051712242335979928354832010167729111113605E+76";
        int resScale = -21;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.divide(bNumber, resScale, BigDecimal.ROUND_HALF_UP);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == resScale);
    }
    
    /**
     * Divide: rounding mode is ROUND_HALF_UP, result is negative; equidistant
     */
    public void testDivideRoundHalfUpNeg2() {
        String a = "-37361671119238118911893939591735";
        int aScale = 10;
        String b = "74723342238476237823787879183470";
        int bScale = 15;
        String c = "-1E+5";
        int resScale = -5;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.divide(bNumber, resScale, BigDecimal.ROUND_HALF_UP);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == resScale);
    }
    
    /**
     * Divide: rounding mode is ROUND_HALF_DOWN, result is positive; distance = -1
     */
    public void testDivideRoundHalfDownPos() {
        String a = "92948782094488478231212478987482988429808779810457634781384756794987";
        int aScale = -24;
        String b = "7472334223847623782375469293018787918347987234564568";
        int bScale = 13;
        String c = "1.24390557635720517122423359799284E+53";
        int resScale = -21;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.divide(bNumber, resScale, BigDecimal.ROUND_HALF_DOWN);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == resScale);
    }

    /**
     * Divide: rounding mode is ROUND_HALF_DOWN, result is negative; distance = -1
     */
    public void testDivideRoundHalfDownNeg() {
        String a = "-92948782094488478231212478987482988429808779810457634781384756794987";
        int aScale = -24;
        String b = "7472334223847623782375469293018787918347987234564568";
        int bScale = 13;
        String c = "-1.24390557635720517122423359799284E+53";
        int resScale = -21;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.divide(bNumber, resScale, BigDecimal.ROUND_HALF_DOWN);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == resScale);
    }
    
    /**
     * Divide: rounding mode is ROUND_HALF_DOWN, result is positive; distance = 1
     */
    public void testDivideRoundHalfDownPos1() {
        String a = "92948782094488478231212478987482988798104576347813847567949855464535634534563456";
        int aScale = -24;
        String b = "74723342238476237823754692930187879183479";
        int bScale = 13;
        String c = "1.2439055763572051712242335979928354832010167729111113605E+76";
        int resScale = -21;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.divide(bNumber, resScale, BigDecimal.ROUND_HALF_DOWN);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == resScale);
    }

    /**
     * Divide: rounding mode is ROUND_HALF_DOWN, result is negative; distance = 1
     */
    public void testDivideRoundHalfDownNeg1() {
        String a = "-92948782094488478231212478987482988798104576347813847567949855464535634534563456";
        int aScale = -24;
        String b = "74723342238476237823754692930187879183479";
        int bScale = 13;
        String c = "-1.2439055763572051712242335979928354832010167729111113605E+76";
        int resScale = -21;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.divide(bNumber, resScale, BigDecimal.ROUND_HALF_DOWN);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == resScale);
    }

    /**
     * Divide: rounding mode is ROUND_HALF_UP, result is negative; equidistant
     */
    public void testDivideRoundHalfDownNeg2() {
        String a = "-37361671119238118911893939591735";
        int aScale = 10;
        String b = "74723342238476237823787879183470";
        int bScale = 15;
        String c = "0E+5";
        int resScale = -5;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.divide(bNumber, resScale, BigDecimal.ROUND_HALF_DOWN);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == resScale);
    }

    /**
     * Divide: rounding mode is ROUND_HALF_EVEN, result is positive; distance = -1
     */
    public void testDivideRoundHalfEvenPos() {
        String a = "92948782094488478231212478987482988429808779810457634781384756794987";
        int aScale = -24;
        String b = "7472334223847623782375469293018787918347987234564568";
        int bScale = 13;
        String c = "1.24390557635720517122423359799284E+53";
        int resScale = -21;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.divide(bNumber, resScale, BigDecimal.ROUND_HALF_EVEN);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == resScale);
    }

    /**
     * Divide: rounding mode is ROUND_HALF_EVEN, result is negative; distance = -1
     */
    public void testDivideRoundHalfEvenNeg() {
        String a = "-92948782094488478231212478987482988429808779810457634781384756794987";
        int aScale = -24;
        String b = "7472334223847623782375469293018787918347987234564568";
        int bScale = 13;
        String c = "-1.24390557635720517122423359799284E+53";
        int resScale = -21;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.divide(bNumber, resScale, BigDecimal.ROUND_HALF_EVEN);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == resScale);
    }
    
    /**
     * Divide: rounding mode is ROUND_HALF_EVEN, result is positive; distance = 1
     */
    public void testDivideRoundHalfEvenPos1() {
        String a = "92948782094488478231212478987482988798104576347813847567949855464535634534563456";
        int aScale = -24;
        String b = "74723342238476237823754692930187879183479";
        int bScale = 13;
        String c = "1.2439055763572051712242335979928354832010167729111113605E+76";
        int resScale = -21;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.divide(bNumber, resScale, BigDecimal.ROUND_HALF_EVEN);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == resScale);
    }

    /**
     * Divide: rounding mode is ROUND_HALF_EVEN, result is negative; distance = 1
     */
    public void testDivideRoundHalfEvenNeg1() {
        String a = "-92948782094488478231212478987482988798104576347813847567949855464535634534563456";
        int aScale = -24;
        String b = "74723342238476237823754692930187879183479";
        int bScale = 13;
        String c = "-1.2439055763572051712242335979928354832010167729111113605E+76";
        int resScale = -21;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.divide(bNumber, resScale, BigDecimal.ROUND_HALF_EVEN);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == resScale);
    }

    /**
     * Divide: rounding mode is ROUND_HALF_EVEN, result is negative; equidistant
     */
    public void testDivideRoundHalfEvenNeg2() {
        String a = "-37361671119238118911893939591735";
        int aScale = 10;
        String b = "74723342238476237823787879183470";
        int bScale = 15;
        String c = "0E+5";
        int resScale = -5;
        BigDecimal aNumber = new BigDecimal(new BigInteger(a), aScale);
        BigDecimal bNumber = new BigDecimal(new BigInteger(b), bScale);
        BigDecimal result = aNumber.divide(bNumber, resScale, BigDecimal.ROUND_HALF_EVEN);
        assertTrue("incorrect value", result.toString().equals(c));
        assertTrue("incorrect scale", result.scale() == resScale);
    }
}
