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
 * @version $Revision: 1.5.4.2 $
 */

package org.apache.harmony.tests.java.math;

import junit.framework.TestCase;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Class:  java.math.BigDecimal
 * Methods: constructors and fields
 */
public class BigDecimalConstructorsTest extends TestCase {
    /**
     * check ONE
     */
    public void testFieldONE() {
        String oneS = "1";
        double oneD = 1.0;
        assertTrue("incorrect string value", BigDecimal.ONE.toString().equals(oneS));
        assertTrue("incorrect double value", BigDecimal.ONE.doubleValue() == oneD);
    }

    /**
     * check TEN
     */
    public void testFieldTEN() {
        String oneS = "10";
        double oneD = 10.0;
        assertTrue("incorrect string value", BigDecimal.TEN.toString().equals(oneS));
        assertTrue("incorrect double value", BigDecimal.TEN.doubleValue() == oneD);
    }

    /**
     * check ZERO
     */
    public void testFieldZERO() {
        String oneS = "0";
        double oneD = 0.0;
        assertTrue("incorrect string value", BigDecimal.ZERO.toString().equals(oneS));
        assertTrue("incorrect double value", BigDecimal.ZERO.doubleValue() == oneD);
    }

    /**
     * new BigDecimal(BigInteger value)
     */
    public void testConstrBI() {
        String a = "1231212478987482988429808779810457634781384756794987";
        BigInteger bA = new BigInteger(a);
        BigDecimal aNumber = new BigDecimal(bA);
        assertTrue("incorrect value", aNumber.unscaledValue().equals(bA));
        assertTrue("incorrect scale", aNumber.scale() == 0);
    }
     
    /**
     * new BigDecimal(BigInteger value, int scale)
     */
    public void testConstrBIScale() {
        String a = "1231212478987482988429808779810457634781384756794987";
        BigInteger bA = new BigInteger(a);
        int aScale = 10;
        BigDecimal aNumber = new BigDecimal(bA, aScale);
        assertTrue("incorrect value", aNumber.unscaledValue().equals(bA));
        assertTrue("incorrect scale", aNumber.scale() == aScale);
    }

    /**
     * new BigDecimal(double value) when value is NaN
     */
    public void testConstrDoubleNaN() {
        double a = Double.NaN;
        try {
            BigDecimal aNumber = new BigDecimal(a);
           fail("NumberFormatException has not been caught");
       } catch (NumberFormatException e) {
           assertTrue("Improper exception message", e.getMessage().equals("argument is NaN"));
       }
    }

    /**
     * new BigDecimal(double value) when value is positive infinity
     */
    public void testConstrDoublePosInfinity() {
        double a = Double.POSITIVE_INFINITY;
        try {
            BigDecimal aNumber = new BigDecimal(a);
           fail("NumberFormatException has not been caught");
       } catch (NumberFormatException e) {
           assertTrue("Improper exception message", e.getMessage().equals("argument is infinite"));
       }
    }

    /**
     * new BigDecimal(double value) when value is positive infinity
     */
    public void testConstrDoubleNegInfinity() {
        double a = Double.NEGATIVE_INFINITY;
        try {
            BigDecimal aNumber = new BigDecimal(a);
           fail("NumberFormatException has not been caught");
       } catch (NumberFormatException e) {
           assertTrue("Improper exception message", e.getMessage().equals("argument is infinite"));
       }
    }

    /**
     * new BigDecimal(double value)
     */
    public void testConstrDouble() {
        double a = 732546982374982347892379283571094797.287346782359284756;
        int aScale = 0;
        BigInteger bA = new BigInteger("732546982374982285073458350476230656");
        BigDecimal aNumber = new BigDecimal(a);
        assertTrue("incorrect value", aNumber.unscaledValue().equals(bA));
        assertTrue("incorrect scale", aNumber.scale() == aScale);
    }

    /**
     * new BigDecimal(0.1)
     */
    public void testConstrDouble01() {
        double a = 1.E-1;
        int aScale = 55;
        BigInteger bA = new BigInteger("1000000000000000055511151231257827021181583404541015625");
        BigDecimal aNumber = new BigDecimal(a);
        assertTrue("incorrect value", aNumber.unscaledValue().equals(bA));
        assertTrue("incorrect scale", aNumber.scale() == aScale);
    }

    /**
     * new BigDecimal(0.555)
     */
    public void testConstrDouble02() {
        double a = 0.555;
        int aScale = 53;
        BigInteger bA = new BigInteger("55500000000000004884981308350688777863979339599609375");
        BigDecimal aNumber = new BigDecimal(a);
        assertTrue("incorrect value", aNumber.unscaledValue().equals(bA));
        assertTrue("incorrect scale", aNumber.scale() == aScale);
    }

    /**
     * new BigDecimal(-0.1)
     */
    public void testConstrDoubleMinus01() {
        double a = -1.E-1;
        int aScale = 55;
        BigInteger bA = new BigInteger("-1000000000000000055511151231257827021181583404541015625");
        BigDecimal aNumber = new BigDecimal(a);
        assertTrue("incorrect value", aNumber.unscaledValue().equals(bA));
        assertTrue("incorrect scale", aNumber.scale() == aScale);
    }

    /**
     * new BigDecimal(double value) when value is denormalized
     */
    public void testConstrDoubleDenormalized() {
        double a = 2.274341322658976E-309;
        int aScale = 1073;
        BigInteger bA = new BigInteger("227434132265897633950269241702666687639731047124115603942986140264569528085692462493371029187342478828091760934014851133733918639492582043963243759464684978401240614084312038547315281016804838374623558434472007664427140169018817050565150914041833284370702366055678057809362286455237716100382057360123091641959140448783514464639706721250400288267372238950016114583259228262046633530468551311769574111763316146065958042194569102063373243372766692713192728878701004405568459288708477607744497502929764155046100964958011009313090462293046650352146796805866786767887226278836423536035611825593567576424943331337401071583562754098901412372708947790843318760718495117047155597276492717187936854356663665005157041552436478744491526494952982062613955349661409854888916015625");
        BigDecimal aNumber = new BigDecimal(a);
        assertTrue("incorrect value", aNumber.unscaledValue().equals(bA));
        assertTrue("incorrect scale", aNumber.scale() == aScale);
    }
     
    /**
     * new BigDecimal(String value)
     * when value is not a valid representation of BigDecimal.
     */
    public void testConstrStringException() {
        String a = "-238768.787678287a+10";
        try {
            BigDecimal aNumber = new BigDecimal(a);
           fail("NumberFormatException has not been caught");
       } catch (NumberFormatException e) {
       }
    }

    /**
     * new BigDecimal(String value)
     * when exponent is empty.
     */
    public void testConstrStringExceptionEmptyExponent1() {
        String a = "-238768.787678287e";
        try {
            BigDecimal aNumber = new BigDecimal(a);
           fail("NumberFormatException has not been caught");
       } catch (NumberFormatException e) {
           assertTrue("Improper exception message", e.getMessage().equals("empty exponent"));
       }
    }

    /**
     * new BigDecimal(String value)
     * when exponent is empty.
     */
    public void testConstrStringExceptionEmptyExponent2() {
        String a = "-238768.787678287e-";
        try {
            BigDecimal aNumber = new BigDecimal(a);
           fail("NumberFormatException has not been caught");
       } catch (NumberFormatException e) {
           assertTrue("Improper exception message", e.getMessage().equals("empty exponent"));
       }
    }

    /**
     * new BigDecimal(String value)
     * when exponent is greater than Integer.MAX_VALUE.
     */
    public void testConstrStringExceptionExponentGreaterIntegerMax() {
        String a = "-238768.787678287e214748364767876";
        try {
            BigDecimal aNumber = new BigDecimal(a);
           fail("NumberFormatException has not been caught");
       } catch (NumberFormatException e) {
           assertTrue("Improper exception message", e.getMessage().equals("exponent is not signed integer"));
       }
    }

    /**
     * new BigDecimal(String value)
     * when exponent is less than Integer.MIN_VALUE.
     */
    public void testConstrStringExceptionExponentLessIntegerMin() {
        String a = "-238768.787678287e-214748364767876";
        try {
            BigDecimal aNumber = new BigDecimal(a);
           fail("NumberFormatException has not been caught");
       } catch (NumberFormatException e) {
           assertTrue("Improper exception message", e.getMessage().equals("exponent is not signed integer"));
       }
    }

    /**
     * new BigDecimal(String value)
     * when exponent is Integer.MAX_VALUE.
     */
    public void testConstrStringExponentIntegerMax() {
        String a = "-238768.787678287e2147483647";
        int aScale = -2147483638;
        BigInteger bA = new BigInteger("-238768787678287");
        BigDecimal aNumber = new BigDecimal(a);
        assertTrue("incorrect value", aNumber.unscaledValue().equals(bA));
        assertTrue("incorrect scale", aNumber.scale() == aScale);
    }

    /**
     * new BigDecimal(String value)
     * when exponent is Integer.MIN_VALUE.
     */
    public void testConstrStringExponentIntegerMin() {
        String a = ".238768e-2147483648";
        int aScale = -2147483638;
        BigInteger bA = new BigInteger("-238768787678287");
        try {
           BigDecimal aNumber = new BigDecimal(a);
       } catch (NumberFormatException e) {
           assertTrue("Improper exception message", 
               e.getMessage().equals("resulting scale out of range"));
       }
    }

    /**
     * new BigDecimal(String value); value does not contain exponent
     */
      public void testConstrStringWithoutExpPos1() {
        String a = "732546982374982347892379283571094797.287346782359284756";
        int aScale = 18;
        BigInteger bA = new BigInteger("732546982374982347892379283571094797287346782359284756");
        BigDecimal aNumber = new BigDecimal(a);
        assertTrue("incorrect value", aNumber.unscaledValue().equals(bA));
        assertTrue("incorrect scale", aNumber.scale() == aScale);
    }

    /**
     * new BigDecimal(String value); value does not contain exponent
     */
      public void testConstrStringWithoutExpPos2() {
        String a = "+732546982374982347892379283571094797.287346782359284756";
        int aScale = 18;
        BigInteger bA = new BigInteger("732546982374982347892379283571094797287346782359284756");
        BigDecimal aNumber = new BigDecimal(a);
        assertTrue("incorrect value", aNumber.unscaledValue().equals(bA));
        assertTrue("incorrect scale", aNumber.scale() == aScale);
    }
       
    /**
     * new BigDecimal(String value); value does not contain exponent
     */
      public void testConstrStringWithoutExpNeg() {
        String a = "-732546982374982347892379283571094797.287346782359284756";
        int aScale = 18;
        BigInteger bA = new BigInteger("-732546982374982347892379283571094797287346782359284756");
        BigDecimal aNumber = new BigDecimal(a);
        assertTrue("incorrect value", aNumber.unscaledValue().equals(bA));
        assertTrue("incorrect scale", aNumber.scale() == aScale);
    }
       
    /**
     * new BigDecimal(String value); value does not contain exponent
     * and decimal point
     */
      public void testConstrStringWithoutExpWithoutPoint() {
        String a = "-732546982374982347892379283571094797287346782359284756";
        int aScale = 0;
        BigInteger bA = new BigInteger("-732546982374982347892379283571094797287346782359284756");
        BigDecimal aNumber = new BigDecimal(a);
        assertTrue("incorrect value", aNumber.unscaledValue().equals(bA));
        assertTrue("incorrect scale", aNumber.scale() == aScale);
    }
       
    /**
     * new BigDecimal(String value); value contains exponent
     * and does not contain decimal point
     */
    public void testConstrStringWithExponentWithoutPoint1() {
        String a = "-238768787678287e214";
        int aScale = -214;
        BigInteger bA = new BigInteger("-238768787678287");
        BigDecimal aNumber = new BigDecimal(a);
        assertTrue("incorrect value", aNumber.unscaledValue().equals(bA));
        assertTrue("incorrect scale", aNumber.scale() == aScale);
    }

    /**
     * new BigDecimal(String value); value contains exponent
     * and does not contain decimal point
     */
    public void testConstrStringWithExponentWithoutPoint2() {
        String a = "-238768787678287e-214";
        int aScale = 214;
        BigInteger bA = new BigInteger("-238768787678287");
        BigDecimal aNumber = new BigDecimal(a);
        assertTrue("incorrect value", aNumber.unscaledValue().equals(bA));
        assertTrue("incorrect scale", aNumber.scale() == aScale);
    }
     
    /**
     * new BigDecimal(String value); value contains exponent
     * and does not contain decimal point
     */
    public void testConstrStringWithExponentWithoutPoint3() {
        String a = "238768787678287e-214";
        int aScale = 214;
        BigInteger bA = new BigInteger("238768787678287");
        BigDecimal aNumber = new BigDecimal(a);
        assertTrue("incorrect value", aNumber.unscaledValue().equals(bA));
        assertTrue("incorrect scale", aNumber.scale() == aScale);
    }

    /**
     * new BigDecimal(String value); value contains exponent
     * and does not contain decimal point
     */
    public void testConstrStringWithExponentWithoutPoint4() {
        String a = "238768787678287e+214";
        int aScale = -214;
        BigInteger bA = new BigInteger("238768787678287");
        BigDecimal aNumber = new BigDecimal(a);
        assertTrue("incorrect value", aNumber.unscaledValue().equals(bA));
        assertTrue("incorrect scale", aNumber.scale() == aScale);
    }

    /**
     * new BigDecimal(String value); value contains exponent
     * and does not contain decimal point
     */
    public void testConstrStringWithExponentWithoutPoint5() {
        String a = "238768787678287E214";
        int aScale = -214;
        BigInteger bA = new BigInteger("238768787678287");
        BigDecimal aNumber = new BigDecimal(a);
        assertTrue("incorrect value", aNumber.unscaledValue().equals(bA));
        assertTrue("incorrect scale", aNumber.scale() == aScale);
    }

    /**
     * new BigDecimal(String value); 
     * value contains both exponent and decimal point
     */
    public void testConstrStringWithExponentWithPoint1() {
        String a = "23985439837984782435652424523876878.7678287e+214";
        int aScale = -207;
        BigInteger bA = new BigInteger("239854398379847824356524245238768787678287");
        BigDecimal aNumber = new BigDecimal(a);
        assertTrue("incorrect value", aNumber.unscaledValue().equals(bA));
        assertTrue("incorrect scale", aNumber.scale() == aScale);
    }

    /**
     * new BigDecimal(String value); 
     * value contains both exponent and decimal point
     */
    public void testConstrStringWithExponentWithPoint2() {
        String a = "238096483923847545735673567457356356789029578490276878.7678287e-214";
        int aScale = 221;
        BigInteger bA = new BigInteger("2380964839238475457356735674573563567890295784902768787678287");
        BigDecimal aNumber = new BigDecimal(a);
        assertTrue("incorrect value", aNumber.unscaledValue().equals(bA));
        assertTrue("incorrect scale", aNumber.scale() == aScale);
    }

    /**
     * new BigDecimal(String value); 
     * value contains both exponent and decimal point
     */
    public void testConstrStringWithExponentWithPoint3() {
        String a = "2380964839238475457356735674573563567890.295784902768787678287E+21";
        int aScale = 0;
        BigInteger bA = new BigInteger("2380964839238475457356735674573563567890295784902768787678287");
        BigDecimal aNumber = new BigDecimal(a);
        assertTrue("incorrect value", aNumber.unscaledValue().equals(bA));
        assertTrue("incorrect scale", aNumber.scale() == aScale);
    }
     
    /**
     * new BigDecimal(String value); 
     * value contains both exponent and decimal point
     */
    public void testConstrStringWithExponentWithPoint4() {
        String a = "23809648392384754573567356745735635678.90295784902768787678287E+21";
        int aScale = 2;
        BigInteger bA = new BigInteger("2380964839238475457356735674573563567890295784902768787678287");
        BigDecimal aNumber = new BigDecimal(a);
        assertTrue("incorrect value", aNumber.unscaledValue().equals(bA));
        assertTrue("incorrect scale", aNumber.scale() == aScale);
    }
     
    /**
     * new BigDecimal(String value); 
     * value contains both exponent and decimal point
     */
    public void testConstrStringWithExponentWithPoint5() {
        String a = "238096483923847545735673567457356356789029.5784902768787678287E+21";
        int aScale = -2;
        BigInteger bA = new BigInteger("2380964839238475457356735674573563567890295784902768787678287");
        BigDecimal aNumber = new BigDecimal(a);
        assertTrue("incorrect value", aNumber.unscaledValue().equals(bA));
        assertTrue("incorrect scale", aNumber.scale() == aScale);
    }
}
