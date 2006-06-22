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
 * @version $Revision$
 */

package java.math;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.io.Serializable;

/**
 * BigDecimal objects represent an arbitrary precisioned decimal Number. They
 * contain values that cannot be changed. Thus, most operations on the
 * BigDecimal object yield new instances of BigDecimal.
 * <p>
 * BigDecimal is respresented by an unscaled BigInteger value and an integer
 * representing the scale of the object. The scale of the BigDecimal is the
 * number of digits after the decimal point. Eg. 1.234 would have a scale of 3
 * and an unscaled value of 1234. Therefore, decimal representation of a
 * BigDecimal is BigIntegerValue/10^scale.
 * 
 * @see java.math.BigInteger
 */
public class BigDecimal extends Number 
                        implements Comparable<BigDecimal>, Serializable {

    /**
     * @com.intel.drl.spec_ref
     */
    private static final long serialVersionUID = 6108874887143696463L; 

    /**
     * The unscaled value of this BigDecimal
     */
    private BigInteger intVal;

    /**
     * The scale of this BigDecimal
     */
    private int scale;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final BigDecimal ONE = new BigDecimal(BigInteger.ONE, 0);

    /**
     * @com.intel.drl.spec_ref
     */
    public static final BigDecimal TEN = new BigDecimal(BigInteger.TEN, 0);
    
    /**
     * @com.intel.drl.spec_ref
     */
    public static final BigDecimal ZERO = new BigDecimal(BigInteger.ZERO, 0);

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int ROUND_CEILING = 2;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int ROUND_DOWN = 1;
    
    /**
     * @com.intel.drl.spec_ref
     */
    public static final int ROUND_FLOOR = 3;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int ROUND_HALF_DOWN = 5;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int ROUND_HALF_EVEN = 6;
    
    /**
     * @com.intel.drl.spec_ref
     */
    public static final int ROUND_HALF_UP = 4;
    
    /**
     * @com.intel.drl.spec_ref
     */
    public static final int ROUND_UNNECESSARY = 7;
    
    /**
     * @com.intel.drl.spec_ref
     */
    public static final int ROUND_UP = 0;
    
    private transient String toStringImage = null;

    private static final BigDecimal[] SMALL_ZERO_SCALED_VALUES = {
        ZERO,
        ONE,
        new BigDecimal(BigInteger.SMALL_VALUES[2], 0),
        new BigDecimal(BigInteger.SMALL_VALUES[3], 0),
        new BigDecimal(BigInteger.SMALL_VALUES[4], 0),
        new BigDecimal(BigInteger.SMALL_VALUES[5], 0),
        new BigDecimal(BigInteger.SMALL_VALUES[6], 0),
        new BigDecimal(BigInteger.SMALL_VALUES[7], 0),
        new BigDecimal(BigInteger.SMALL_VALUES[8], 0),
        new BigDecimal(BigInteger.SMALL_VALUES[9], 0),
        TEN
    };

    private static final BigDecimal[] ZERO_SMALL_SCALED_VALUES = {
        ZERO,
        new BigDecimal(BigInteger.ZERO, 1),
        new BigDecimal(BigInteger.ZERO, 2),
        new BigDecimal(BigInteger.ZERO, 3),
        new BigDecimal(BigInteger.ZERO, 4),
        new BigDecimal(BigInteger.ZERO, 5),
        new BigDecimal(BigInteger.ZERO, 6),
        new BigDecimal(BigInteger.ZERO, 7),
        new BigDecimal(BigInteger.ZERO, 8),
        new BigDecimal(BigInteger.ZERO, 9),
        new BigDecimal(BigInteger.ZERO, 10),
    };


    /**
     * Controls overflow in the calculated preferred scale.
     * If it overflows a 32-bit integer, return extreme Integer values.
     * @param longScale the long preferred scale.
     * @return int scale.
     */
    private int getPreferredScale(long longScale) {
        if (longScale != (int) longScale) {
            if (longScale < 0) {
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        return (int) longScale;
    }

    /**
     * Verifies if a value overflows a 32-bit integer.
     * @param longVal long value to be verified.
     * @param val the name of the value.
     * @return int longVal.
     */
    private static int getValidInt(long longVal, String val) {
        if (longVal != (int)longVal) {
            throw new ArithmeticException(val + " outside the range of a 32-bit integer");
        }
        return (int)longVal;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static BigDecimal valueOf(double value) {
        return new BigDecimal(Double.toString(value));
    }

    /**
     * Translate long value into a BigDecimal with scale of zero.
     * 
     * @return BigDecimal BigDecimal equivalence of a long value.
     */
    public static BigDecimal valueOf(long value) {
        return valueOf(value, 0);
    }

    /**
     * Translate long unscaled value into a BigDecimal specified by the scale.
     * 
     * @return BigDecimal BigDecimal equalvalence of a long value.
     * @exception NumberFormatException
     *                the scale value is < 0;
     */
    public static BigDecimal valueOf(long unscaledValue, int scale) {
        if (unscaledValue == 0L && (scale >= 0 && scale <= 10)) {
            return ZERO_SMALL_SCALED_VALUES[scale];
        }
        if (scale == 0 && (unscaledValue >= 0 && unscaledValue <= 10)) {
            return SMALL_ZERO_SCALED_VALUES[(int) unscaledValue];
        }
        return new BigDecimal(BigInteger.valueOf(unscaledValue), scale);
    }

    /**
     * Constructs a BigDecimal with unscaled value initialized as value and scale
     * as 0.
     */
    public BigDecimal(BigInteger value) {
        intVal = value;
        scale = 0;
    }

    /**
     * Constructs a BigDecimal with unscaled value initialized as unScaledValue and scale
     * as scale from the argument.
     */
    public BigDecimal(BigInteger unScaledValue, int scale) {
        intVal = unScaledValue;
        this.scale = scale;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal(BigInteger unscaledVal, int scale, MathContext mC) {
        this(unscaledVal, scale);
        if (mC.precision != 0) {
            BigDecimal rounded = this.round(mC);
            this.intVal = rounded.intVal;
            this.scale = rounded.scale;
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal(BigInteger value, MathContext mC) {
        this(value);
        if (mC.precision != 0) {
            BigDecimal rounded = this.round(mC);
            this.intVal = rounded.intVal;
            this.scale = rounded.scale;
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal(char[] input) {
        this(input, 0, input.length);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal(char[] in, int offset, int len) {
        int endIndex = offset + len - 1;
        if (offset < 0 || endIndex >= in.length) {
            throw new NumberFormatException("the subarray exceeds the bounds" +
                                            " of the array");
        }
        int count = 0; // the pointer to the current char
        int dotPosition = -1; // the position of the decimal point
        String unscaled;
        String exponent;
        char symbol = in[offset];
        int startIndex = symbol == '+' ? offset + 1 : offset;
        
        // parse the significand
        for (count = startIndex; count <= endIndex; count++) {
            symbol = in[count];
            if (symbol == '.') {
                dotPosition = count;
            }
            if (symbol == 'e' || symbol == 'E') {
                break;
            }
        }
        if (dotPosition < 0) {
            unscaled = new String(in, startIndex, count - startIndex);
            scale = 0;
        } else {
            unscaled = new String(in, startIndex, dotPosition - startIndex) +
                ((dotPosition == endIndex) 
                 ? "" 
                 : new String(in, dotPosition + 1, count - dotPosition - 1));
            scale = count - dotPosition - 1;
        }
        intVal = new BigInteger(unscaled);
        
        // parse the exponent
        if (count <= endIndex) {
            if (++count > endIndex) {    // skip 'E'
                throw new NumberFormatException("empty exponent");
            }
            symbol = in[count];
            if ((symbol == '+' || symbol == '-') && count == endIndex) {
                throw new NumberFormatException("empty exponent");
            }
            if (symbol == '+') {
                count++;
                symbol = in[count];
                if(symbol == '-') {
                    throw new NumberFormatException("exponent is not signed integer");
                }
            }
            exponent = new String(in, count, endIndex - count + 1);
            int exp = 0;
            try {
                exp = Integer.parseInt(exponent);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("exponent is not " +
                                                "signed integer");
            }
            
            // the value of the resulting scale must lie between 
            // Integer.MIN_VALUE and Integer.MAX_VALUE inclusive
            long longScale = (long) scale - (long) exp;
            if (longScale != (int) longScale) {
                throw new NumberFormatException("resulting scale out of range");
            }
            scale = (int) longScale;
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal(char[] input, int offset, int len, MathContext mC) {
        this(input, offset, len);
        if (mC.precision != 0) {
            BigDecimal rounded = this.round(mC);
            this.intVal = rounded.intVal;
            this.scale = rounded.scale;
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal(char[] input, MathContext mC) {
        this(input, 0, input.length, mC);
    }

    /**
     * Constructs a BigDecimal with a double value as an arugment.
     * 
     * @exception NumberFormatException
     *                If the is Infinity, Negative Infinity or NaN.
     */
    public BigDecimal(double value) {
        if (value != value) {
            throw new NumberFormatException ("argument is NaN");
        }
        if (Double.isInfinite(value)) {
            throw new NumberFormatException ("argument is infinite");
        }
        long longValue = Double.doubleToLongBits(value);
        long significand = longValue & 0x000fffffffffffffL; // mantissa
        scale = ((int)(longValue >> 52)) & 0x7ff; // exponent
        if (scale == 0) {
            scale = 1; // to be calculated correctly in the next operation 
        } else {
            significand |= 0x0010000000000000L;
        }
        scale -= 1075;
        // if the scale is negative, strip tailing zeros 
        // to ensure that the scale is the smallest
        while ((significand & 1) == 0 && scale < 0) {
            significand >>= 1;
            scale++;
        }
        intVal = BigInteger.valueOf((longValue & 0x8000000000000000L) == 0 ?
            significand : -significand);
        if (scale > 0) {
            intVal = intVal.shiftLeft(scale);
            scale = 0;
        } else {
            // m * 2 ^ e = m * (10 / 5) ^ e = (m * 5 ^ (-e)) * 10 ^ e
            scale = -scale;
            if(scale<fivePows.length) {
                intVal = intVal.multiply(fivePows[scale]);
            } else {
                intVal = intVal.multiply(BigInteger.FIVE.pow(scale));
            }
        }
    }

    private static final BigInteger[] fivePows = new BigInteger[36];

    static {
        fivePows[0] = BigInteger.ONE;
        fivePows[1] = BigInteger.FIVE;
        int i=2;
        long val = 25L;
        for(;i<=27;i++) {
            fivePows[i] = BigInteger.valueOf(val);
            val*=5L;
        }
        for(;i<fivePows.length;i++) {
            fivePows[i] = fivePows[i-1].multiply(BigInteger.FIVE);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal(double value, MathContext mC) {
        this(value);
        if (mC.precision != 0) {
            BigDecimal rounded = this.round(mC);
            this.intVal = rounded.intVal;
            this.scale = rounded.scale;
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal(int value) {
        this(new Integer(value).toString());
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal(int value, MathContext mC) {
        this(value);
        if (mC.precision != 0) {
            BigDecimal rounded = this.round(mC);
            this.intVal = rounded.intVal;
            this.scale = rounded.scale;
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal(long value) {
        this(new Long(value).toString());
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal(long value, MathContext mC) {
        this(value);
        if (mC.precision != 0) {
            BigDecimal rounded = this.round(mC);
            this.intVal = rounded.intVal;
            this.scale = rounded.scale;
        }
    }

    /**
     * Constructs a BigDecimal from the string which can only
     * contain digits of 0-9, a decimal point and a negative sign.
     * 
     * @exception NumberFormatException
     *                If the argument contained characters other than digits.
     */
    public BigDecimal(String value) {
        int count; // the significand's length
        int dotPlace = -1;
        int strLen = value.length();
        String unscaled;
        String exponent;
        
        if(strLen == 0) {
            throw new NumberFormatException("empty string");
        }
        char symbol = value.charAt(0);
        int startPoint = symbol == '+' ? 1 : 0;
        
        // parse the significand
        for (count = 0; count < strLen; count++) {
            symbol = value.charAt(count);
            if (symbol == '.') {
                dotPlace = count;
            }
            if (symbol == 'e' || symbol == 'E') {
                break;
            }
        }
        if (dotPlace == -1) {
            unscaled = value.substring(startPoint, count);
            scale = 0;
        } else {
            unscaled = value.substring(startPoint, dotPlace) + 
                value.substring(dotPlace + 1, count);
            scale = count - dotPlace - 1;
        }
        intVal = new BigInteger(unscaled);
        
        // parse the exponent
        if (count < strLen) {
            if (++count == strLen) {    // skip 'E'
                throw new NumberFormatException("empty exponent");
            }
            symbol = value.charAt(count);
            if ((symbol == '+' || symbol == '-') && count == strLen - 1) {
                throw new NumberFormatException("empty exponent");
            }
            if (symbol == '+') {
                count++;
                symbol = value.charAt(count);
                if(symbol == '-') {
                    throw new NumberFormatException("exponent is not signed integer");
                }
            }
            exponent = value.substring(count, strLen);
            int exp = 0;
            try {
                exp = Integer.parseInt(exponent);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("exponent is not signed integer");
            }
            
            // the value of the resulting scale must lie between 
            // Integer.MIN_VALUE and Integer.MAX_VALUE inclusive
            long longScale = (long)scale - (long)exp;
            if (longScale != (int)longScale) {
                throw new NumberFormatException("resulting scale out of range");
            }
            scale = (int)longScale;
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal(String value, MathContext mC) {
        this(value);
        if (mC.precision != 0) {
            BigDecimal rounded = this.round(mC);
            this.intVal = rounded.intVal;
            this.scale = rounded.scale;
        }
    }

    /**
     * Answers the absolute value of this BigDecimal.
     * 
     * @return BigDecimal absolute value of the receiver.
     */
    public BigDecimal abs() {
        return new BigDecimal(intVal.abs(), this.scale);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal abs(MathContext mC) {
        return round(mC).abs();
    }

    /**
     * Answers the sum of the receiver and argument.
     * 
     * @return BigDecimal The sum of adding two BigDecimal.
     */
    public BigDecimal add(BigDecimal value) {
        int delta = scale - value.scale;
        if (delta == 0) {
            return new BigDecimal(intVal.add(value.intVal), scale);
        }
        if (delta < 0) {
            return new BigDecimal(this.intVal.multiplyByTenPow(-delta)
                                  .add(value.intVal), value.scale);
        } else {
            return new BigDecimal(value.intVal.multiplyByTenPow(delta)
                                  .add(this.intVal), this.scale);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal add(BigDecimal value, MathContext mC) {
        if (mC.precision == 0) {
            return add(value);
        }
        int newScale = Math.max(this.scale, value.scale);
        BigDecimal result = null;
        boolean thisZero = this.intVal.signum() == 0;
        boolean valueZero = value.intVal.signum() == 0;
        if (thisZero || valueZero) {
            if (thisZero && valueZero) {
                return new BigDecimal(BigInteger.ZERO, newScale);
            }
            result = (thisZero) ? value.round(mC) : this.round(mC);
            if (newScale == result.scale) {
                return result;
            }
            if (newScale > result.scale) { 
                // try to increase the result.scale 
                // and stay in precision's limits
                if (mC.precision - result.precision() >= 
                    newScale - result.scale) {
                    
                    return result.setScale(newScale);
                } else {
                    return result.setScale(result.scale + mC.precision - 
                                           result.precision()); 
                }
            } else { 
                // try to decrease the result.scale by stripping possible zeros
                return result.stripTrailingZeros(newScale);
            }
        } 
        // both != 0
        return round(add(value), mC);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public byte byteValueExact() {
        return (byte)valueExact(3, "127", "-128");
    }

    /**
     * Compares the receiver BigDecimal and argument BigDecimal e.x 1.00 & 1.0
     * will return 0 in compareTo.
     * 
     * @return int 0 - equal; 1 - this > value; -1 - this < value.
     */
    public int compareTo(BigDecimal value) {
        if (this.intVal.sign == value.intVal.sign) {
            int delta = scale - value.scale;
            if (delta == 0) {
                return this.intVal.compareTo(value.intVal);
            }
            if (delta < 0) {
                return this.intVal.multiplyByTenPow(-delta).compareTo(value.intVal);
            } else {
                return this.intVal.compareTo(value.intVal.multiplyByTenPow(delta));
            }
        }
        if (this.intVal.sign == 0) {
            return -value.intVal.sign;
        }
        return this.intVal.sign;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal divide(BigDecimal value) {
        if (value.intVal.signum() == 0) {
            throw new ArithmeticException("division by zero");
        }
        int newScale = getValidInt((long) this.scale - (long) value.scale, 
                                   "preferred scale");
        if (this.intVal.signum() == 0) {
            return new BigDecimal(BigInteger.ZERO, newScale);
        }
        // Try to evaluate the precision of the terminating decimal expansion
        // of the result.
        // this / value = this * (1 / value).
        // The precision of the result is not greater than the sum of 
        // this.precision and the precision of (1 / value).
        // It is known that the precision of (1 / 2 ^ n) is n digits. 
        // We approximate the value.intVal by 2 ^ n 
        // where n is the bit length of value.intVal.
        // It is known that the number of bits for a radix digit is 
        // log(radix) / log(2).
        // The bit length can be approximated by precision * log(10) / log(2). 

        int resultPrecision = getValidInt((long) this.precision() +
            (long) Math.ceil(value.precision() * 3.321928094887362),
            "result precision");
        MathContext divisionMC = new MathContext(resultPrecision, 
                                                 RoundingMode.UNNECESSARY);
        BigDecimal result;
        // if the exact result cannot be computed, throw the exception
        try {
            result = divide(value, divisionMC);
        } catch (ArithmeticException e) {
            throw new ArithmeticException("quotient does not have " + 
                                          "a terminating decimal expansion");
        }
        if (newScale > result.scale) {
            result = result.setScale(newScale);
        }
        return result;
    }

    /**
     * Answers the result of (this / value).
     * 
     * @return BigDecimal result of this/value.
     */
    public BigDecimal divide(BigDecimal value, int roundingMode) {
        return divide(value, this.scale, roundingMode);
    }

    /**
     * Answers the result of (this / value) and whose scale is specified.
     * 
     * @return BigDecimal result of this/value.
     * @exception ArithmeticException
     *                division by zero.
     * @exception IllegalArgumentException
     *                roundingMode is not valid.
     */
    public BigDecimal divide(BigDecimal value, int quotientScale, int roundingMode) {
        if (roundingMode > 7 || roundingMode < 0) {
            throw new IllegalArgumentException("invalid rounding mode");
        }
        if (value.intVal.signum() == 0) {
            throw new ArithmeticException("division by zero");
        }
        int exponent = quotientScale - this.scale + value.scale; 
        // If exponent > 0, the dividend should be multiplied by 10**exponent.
        // If exponent < 0, the divisor should be multiplied by 10**(-exponent).
        BigInteger dividend = this.intVal;
        BigInteger divisor = value.intVal;
        if (exponent < 0) {
            divisor = divisor.multiplyByTenPow(-exponent);
        } else if (exponent > 0) {
            dividend = dividend.multiplyByTenPow(exponent);
        }
        return divideByBigInteger(dividend, divisor, roundingMode, quotientScale);
    }

    private static boolean needIncDec(int quotientLo, boolean negativeQuotient, long divisor, long remainder, int roundingMode) {
        // Assertion:  divisor & remainder are positive
        if (roundingMode == ROUND_UNNECESSARY) {
            throw new ArithmeticException("rounding mode is"
                                          + " ROUND_UNNECESSARY but the result is not exact");
        }
        boolean flag = false;
        if(roundingMode==ROUND_UP) {
        	flag =true;
        } else if (roundingMode == ROUND_FLOOR) {
            flag = negativeQuotient; //negativeQuotient ? ROUND_UP : ROUND_DOWN
        } else if (roundingMode == ROUND_CEILING) {
            flag = !negativeQuotient; // negativeQuotient ? ROUND_DOWN : ROUND_UP;
        } else {
            // distance == -1 if the quotient is NOT the nearest neighbor
            // to the exact result;
            // distance == 0 if the exact result is equidistant from
            // the neighbors;
            // distance == +1 if the quotient is the nearest neighbor
            // to the exact result.
            long halfDivisor = divisor >>> 1;

            if (roundingMode == ROUND_HALF_DOWN) {
                flag = halfDivisor < remainder;
                //roundingMode = (distance >= 0) ? ROUND_DOWN : ROUND_UP;
            } else if (roundingMode == ROUND_HALF_UP) {
                flag = halfDivisor <= remainder;
                //roundingMode = (distance > 0) ? ROUND_DOWN : ROUND_UP;
            } else if (roundingMode == ROUND_HALF_EVEN) {
                if (halfDivisor > remainder) {
                    //roundingMode = ROUND_DOWN;
                } else if (halfDivisor < remainder) {
                    flag = true;
                    //roundingMode = ROUND_UP;
                } else {
                    flag = (quotientLo & 1) == 1;
                    //roundingMode = ROUND_UP;
                }
            }
        }
        return flag;
    }

    private static BigDecimal divideByInteger(BigInteger dividend, int divisor, int divisorSign, int roundingMode, int quotientScale) {
        // res[0] is a quotient and res[1] is a remainder:
        int[] dividendDigits = dividend.digits;
        int dividendLen = dividend.numberLength;
        int dividendSign = dividend.sign;
        if (dividendLen == 1) {
            long a = ((long) dividendDigits[0] & 0xffffffffL);
            long b = ((long) divisor & 0xffffffffL);
            long quo = a / b;
            long rem = a % b;
            if (dividendSign != divisorSign) {
                quo = -quo;
                if (rem != 0 && needIncDec((int) quo, true, b, rem, roundingMode)) {
                    quo--;
                }
            } else {
                if (rem != 0 && needIncDec((int) quo, false, b, rem, roundingMode)) {
                    quo++;
                }
            }
            return new BigDecimal(BigInteger.valueOf(quo), quotientScale);
        } else {
            int quotientLength = dividendLen;
            int quotientSign = ((dividendSign == divisorSign) ? 1 : -1);
            int quotientDigits[] = new int[quotientLength];
            int remainder = BigInteger.divideArrayByInt(quotientDigits,
                                                        dividendDigits, dividendLen, divisor);
            if (remainder != 0 && needIncDec(quotientDigits[0], dividendSign != divisorSign, (long) divisor & 0xffffffffL, remainder, roundingMode)) {
                quotientDigits = BigInteger.incInPlace(quotientDigits);
            }
            BigInteger result = new BigInteger(quotientSign, quotientLength,
                                               quotientDigits);
            result.cutOffLeadingZeroes();
            return new BigDecimal(result, quotientScale);
        }
    }

    private static BigDecimal divideByBigInteger(BigInteger dividend, BigInteger divisor, int roundingMode, int quotientScale) {
        int divisorLen = divisor.numberLength;
        if (divisorLen == 1) {
            //res = dividend.divideAndRemainderByInteger(divisorDigits[0], divisorSign);
            return divideByInteger(dividend, divisor.digits[0], divisor.sign, roundingMode, quotientScale);
        }
        BigInteger quotient;
        BigInteger remainder;
        int divisorSign = divisor.sign;
        int[] divisorDigits = divisor.digits;
        // res[0] is a quotient and res[1] is a remainder:
        int[] thisDigits = dividend.digits;
        int thisLen = dividend.numberLength;
        boolean negativeQuotient = dividend.signum() != divisorSign;
        int cmp = (thisLen != divisorLen ?
                   ((thisLen > divisorLen) ? 1 : -1) :
                   BigInteger.compareArrays(thisDigits, divisorDigits, thisLen));
        if (cmp < 0) {
            if (!dividend.isZero() && needIncDec(0, negativeQuotient, divisor, dividend.abs(), roundingMode)) {
                return new BigDecimal(negativeQuotient ? BigInteger.NEG_ONE : BigInteger.ONE, quotientScale);
            }
            return new BigDecimal(BigInteger.ZERO, quotientScale);
        } else {
            int thisSign = dividend.sign;
            int quotientLength = thisLen - divisorLen + 1;
            int remainderLength = divisorLen;
            int quotientSign = ((thisSign == divisorSign) ? 1 : -1);
            int quotientDigits[] = new int[quotientLength];
            int remainderDigits[] = BigInteger.divide(quotientDigits, quotientLength,
                                                      thisDigits, thisLen,
                                                      divisorDigits, divisorLen);
            remainder = new BigInteger(1, remainderLength,
                                       remainderDigits);
            remainder.cutOffLeadingZeroes();
            if (!remainder.isZero() && needIncDec(quotientDigits[0], quotientSign < 0, divisor, remainder, roundingMode)) {
                quotientDigits = BigInteger.incInPlace(quotientDigits);
            }
            quotient = new BigInteger(quotientSign, quotientLength,
                                      quotientDigits);
            quotient.cutOffLeadingZeroes();
            return new BigDecimal(quotient, quotientScale);
        }
    }

    private static boolean needIncDec(int quotientLo, boolean negativeQuotient, BigInteger divisor, BigInteger remainder, int roundingMode) {
        if (roundingMode == ROUND_UNNECESSARY) {
            throw new ArithmeticException("rounding mode is"
                                          + " ROUND_UNNECESSARY but the result is not exact");
        }
        boolean flag = false;
        int divisorSignum = divisor.signum();
        if(roundingMode==ROUND_UP) {
        	flag =true;
        } else if (roundingMode == ROUND_FLOOR) {
            flag = negativeQuotient; //negativeQuotient ? ROUND_UP : ROUND_DOWN
        } else if (roundingMode == ROUND_CEILING) {
            flag = !negativeQuotient; // negativeQuotient ? ROUND_DOWN : ROUND_UP;
        } else {
            // distance == -1 if the quotient is NOT the nearest neighbor
            // to the exact result;
            // distance == 0 if the exact result is equidistant from
            // the neighbors;
            // distance == +1 if the quotient is the nearest neighbor
            // to the exact result.
            if (divisorSignum == -1) {
                divisor = divisor.abs();
            }

            int distance = divisor.subtract(remainder).compareTo(remainder);
            if (roundingMode == ROUND_HALF_DOWN) {
                flag = (distance < 0);
                //(distance >= 0) ? ROUND_DOWN : ROUND_UP;
            } else if (roundingMode == ROUND_HALF_UP) {
                flag = (distance <= 0);
                //roundingMode = (distance > 0) ? ROUND_DOWN : ROUND_UP;
            } else if (roundingMode == ROUND_HALF_EVEN) {
                if (distance > 0) {
                    //roundingMode = ROUND_DOWN;
                } else if (distance < 0) {
                    flag = true; //roundingMode = ROUND_UP;
                } else if (!((quotientLo & 1) == 1)) {
                    //roundingMode = ROUND_DOWN;
                } else {
                    flag = true;
                    //roundingMode = ROUND_UP;
                }
            }
        }
        return flag;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal divide(BigDecimal value, int scale, RoundingMode rm) {
        return divide(value, scale, rm.bigDecimalRM);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal divide(BigDecimal value, MathContext mC) {
        if (mC.precision == 0) {
            return divide(value);
        }
        if (value.intVal.signum() == 0) {
            throw new ArithmeticException("division by zero");
        }
        int newScale = getPreferredScale((long) this.scale - (long) value.scale);
        if (this.intVal.signum() == 0) {
            return new BigDecimal(BigInteger.ZERO, newScale);
        }
        long sDAdd = newScale > 0 ? newScale + 1L : 1L;
        int pD = value.precision() - this.precision();
        long pDAdd = pD > 0 ? pD + 1 : 1;
        int divScale = getValidInt(mC.precision + 1 + pDAdd + sDAdd,
                                   "intermediate division scale");
        BigDecimal result = divide(value, divScale, 
                                   mC.roundingMode.bigDecimalRM);
        result = round(result, mC);
        if (result.multiply(value).compareTo(this) == 0) { 
             // exact result
            result = result.stripTrailingZeros(newScale);
        }
        return result;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal divide(BigDecimal value, RoundingMode rm) {
        return divide(value, rm.bigDecimalRM);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal[] divideAndRemainder(BigDecimal value) {
        BigDecimal resultArray[] = new BigDecimal[2];
        resultArray[0] = divideToIntegralValue(value);
        resultArray[1] = subtract(resultArray[0].multiply(value));
        return resultArray;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal[] divideAndRemainder(BigDecimal value, MathContext mC) {
        if (mC.precision == 0) {
            return divideAndRemainder(value);
        }
        BigDecimal resultArray[] = new BigDecimal[2];
        resultArray[0] = divideToIntegralValue(value, mC);
        resultArray[1] = subtract(resultArray[0].multiply(value));
        return resultArray;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal divideToIntegralValue(BigDecimal value) {
        if (value.intVal.signum() == 0) {
            throw new ArithmeticException("division by zero");
        }
        int newScale = getPreferredScale((long) this.scale - (long)value.scale);
        if (this.intVal.signum() == 0) {
            return setScale(newScale);
        }
        if (value.abs().compareTo(this.abs()) > 0) {
            // if the divisor > the dividend, return 0
            return new BigDecimal(BigInteger.ZERO, newScale);
        }
        // below the divisor always <= the dividend
        int dA = this.precision() - this.scale;
        int dB = value.precision() - value.scale;
        int divisionPrecision;
        if (dA > 0 && dB > 0) { // there are integer parts in both numbers
            // extra digits for rounding
            divisionPrecision = dA - dB + (dA == dB ? 2 : 1);
        } else if (dA > 0 && dB <= 0) {
            // this has an integer part and value is a fraction
            divisionPrecision = dA + Math.abs(dB) + 1;
        } else { 
            // both are fractions
            divisionPrecision = Math.abs(dA) + Math.abs(dB) + 1;
        }
        // control overflow
        if (divisionPrecision < 0) {
            divisionPrecision = Integer.MAX_VALUE;
        }
        BigDecimal result = divide(value, new MathContext(divisionPrecision,
                                                          RoundingMode.DOWN));
        if (result.scale > 0) {
            // round to an integer part
            result = result.setScale(0, RoundingMode.DOWN);
        } 
        if (result.scale > newScale) {
            result = result.stripTrailingZeros(newScale);
        } else if (result.scale < newScale) {
            result = result.setScale(newScale);
        }
        return result;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal divideToIntegralValue(BigDecimal value, MathContext mC) {
        if (mC.precision == 0) {
            return divideToIntegralValue(value);
        }
        if (value.intVal.signum() == 0) {
            throw new ArithmeticException("division by zero");
        }
        int newScale = getPreferredScale((long) this.scale - (long)value.scale);
        if (value.abs().compareTo(this.abs()) > 0) {
            return new BigDecimal(BigInteger.ZERO, newScale);
        }
        BigDecimal result = divide(value, new MathContext(mC.precision,
                                                          RoundingMode.DOWN));
        if (result.scale > 0) {
            // round to an integer part
            result = result.setScale(0, RoundingMode.DOWN);
        } else if (result.scale < 0) { // got an integer
            BigDecimal remainder = this.subtract(result.multiply(value));
            // compare the divisor and the remainder
            if ((value.abs()).compareTo(remainder.abs()) == -1) {
                throw new ArithmeticException(
                    "integer part of the quotient needs more than " + 
                    mC.precision + " digits");
            }
        }
        // result.scale <= 0;
        int dP = mC.precision - result.precision();
        int dS = getValidInt((long) newScale - (long) result.scale,
                             "temporary scale");
        if (dS != 0 || dP != 0) {
            if (dS < 0 || dP < 0) {
                result = result.stripTrailingZeros(newScale);
            } else {
                result = result.setScale(result.scale + Math.min(dS, dP)); 
            }
        }
        return result;
    }

    /**
     * Converts this BigDecimal to a double. If magnitude of the BigDecimal
     * value is larger than what can be represented by a double, either Infinity
     * or -Infinity is returned.
     * 
     * @return double the value of the receiver.
     */
    public double doubleValue() {
        return Double.valueOf(toString()).doubleValue();
    }

    /**
     * Compares the argument to the receiver, and answers true if they represent
     * the <em>same</em> object using a class specific comparison. The
     * implementation in Object answers true only if the argument is the exact
     * same object as the receiver (==).
     * 
     * @param o
     *            Object the object to compare with this object.
     * @return boolean <code>true</code> if the object is the same as this
     *         object <code>false</code> if it is different from this object.
     * @see hashCode
     */
    public boolean equals(Object object) {
        return (object instanceof BigDecimal &&
                this.compareTo((BigDecimal)object) == 0 && 
                this.scale == ((BigDecimal)object).scale);
    }

    /**
     * Converts this BigDecimal to a float.If magnitude of the BigDecimal value
     * is larger than what can be represented by a float, either Infinity or
     * -Infinity is returned.
     * 
     * @return float the value of the receiver.
     */
    public float floatValue() {
        return Float.valueOf(toString()).floatValue();
    }

    /**
     * Answers an integer hash code for the receiver. Any two objects which
     * answer <code>true</code> when passed to <code>.equals</code> must
     * answer the same value for this method.
     * 
     * @return int the receiver's hash.
     * 
     * @see #equals(Object)
     */
    public int hashCode() {
        return intVal.intValue() + scale;
    }

    /**
     * Converts this BigDecimal to an int.
     * 
     * @return int the value of the receiver.
     */
    public int intValue() {
        return toBigInteger().intValue();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int intValueExact() {
        return (int) valueExact(10, "2147483647", "-2147483648");
    }

    /**
     * Converts this BigDecimal to a long.
     * 
     * @return long long representation of the receiver.
     */
    public long longValue() {
        return toBigInteger().longValue();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public long longValueExact() {
        return valueExact(19, "9223372036854775807", "-9223372036854775808");
    }

    /**
     * Answers the max value between the receiver and this BigDecimal.
     * 
     * @return BigDecimal max BigDecimal.
     */
    public BigDecimal max(BigDecimal value) {
        return (this.compareTo(value) >= 0 ? this : value);
    }

    /**
     * Answers the min value between the receiver and argument.
     * 
     * @return BigDecimal min BigDecimal.
     */
    public BigDecimal min(BigDecimal value) {
        return (this.compareTo(value) <= 0 ? this : value);
    }

    /**
     * Moves the decimal point shift digits
     * @param shift shift distance
     */
    private BigDecimal movePoint(int shift) {
        long newScale = (long) scale + (long) shift;
        if (newScale != (int) newScale) {
            throw new ArithmeticException("scale outside the range of a 32-bit integer");
        }
        int newSc = (int) newScale;
        if (newSc >= 0) {
            return new BigDecimal(intVal, newSc);
        }
        return new BigDecimal(this.intVal
                              .multiplyByTenPow(-newSc), 0);
    }

    /**
     * Moves the decimal point of this BigDecimal n places to the left.
     * 
     * @return BigDecimal new BigDecimal with decimal moved n places to the
     *         left.
     */
    public BigDecimal movePointLeft(int shift) {
        return movePoint(shift);
    }

    /**
     * Moves the decimal point of this BigDecimal n places to the right.
     * 
     * @return BigDecimal new BigDecimal with decimal moved n places to the
     *         right.
     */
    public BigDecimal movePointRight(int shift) {
        return movePoint(-shift);
    }

    /**
     * Answers the multiplication result of the receiver and argument.
     * 
     * @return BigDecimal result of multiplying two bigDecimals.
     */
    public BigDecimal multiply(BigDecimal value) {
        return new BigDecimal(this.intVal.multiply(value.intVal), 
            this.scale + value.scale);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal multiply(BigDecimal value, MathContext mC) {
        if (mC.precision != 0) {
            return round(this.multiply(value), mC);
        } else {
            return this.multiply(value);
        }
    }

    /**
     * Negates this BigDecimal value.
     * 
     * @return BigDecimal new BigDecimal with value negated.
     */
    public BigDecimal negate() {
        return new BigDecimal(intVal.negate(), scale);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal negate(MathContext mC) {
        return round(mC).negate();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal plus() {
        return this;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal plus(MathContext mc) {
        return round(mc);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal pow(int exp) {
        if (exp < 0 || exp > 999999999) {
            throw new ArithmeticException("power is out of range");
        }
        if (intVal.signum() == 0 && exp == 0) {
            return ONE;
        }
        // test if it fits an int 
        int newScale = getValidInt((long) scale * exp, "result scale");
        return new BigDecimal(this.intVal.pow(exp), newScale);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal pow(int exp, MathContext mC) {
        if (mC.precision == 0) {
            return pow(exp);
        }
        if (exp < -999999999 || exp > 999999999) {
            throw new ArithmeticException("exponent out of range");
        }
        if (exp == 0) {
            return ONE;
        }
        int expV = Math.abs(exp);
        int expLen = new Integer(expV).toString().length();
        if (expLen > mC.precision) {
            throw new ArithmeticException("exponent is longer than " +
                                          "MathContext precision");
        }
        MathContext multMC = new MathContext(mC.precision + expLen + 1,
                                             mC.roundingMode);
        BigDecimal result = ONE;
        do {
            result = this.multiply(result, multMC);
        } while (--expV > 0);
        if (exp < 0) {
            result = ONE.divide(result, multMC);
        }
        return round(result, mC);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int precision() {
        if (intVal.equals(BigInteger.ZERO)) {
            return 1;
        }
        return intVal.toString().length() - (intVal.signum() == -1 ? 1 : 0);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal remainder(BigDecimal value) {
        return divideAndRemainder(value)[1];
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal remainder(BigDecimal value, MathContext mC) {
        return divideAndRemainder(value, mC)[1];
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal round(MathContext mC) {
        if (mC.precision == 0) {
            return this;
        }
        return round(this, mC);
    }

    /**
     * Rounds a BigDecimal according to required MathContext
     * @param value the BigDecimal to be rounded
     * @param mc the context to use
     * @return the rounded value
     */
    private BigDecimal round(BigDecimal value, MathContext mC) {
        int delta = mC.precision - value.precision();
        if (delta >= 0) {
            return value;
        }
        BigDecimal result = 
            value.divide(new BigDecimal(BigInteger.TEN.pow(-delta), 0),
                         value.scale, mC.roundingMode.bigDecimalRM);
        result.scale += delta;
        if (result.precision() != mC.precision) {
            result = round(result, mC);
        }
        return result;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal scaleByPowerOfTen(int n) {
        int intScale = getValidInt((long) this.scale - (long) n, "scale");
        return new BigDecimal(this.intVal, intScale);
    }

    /**
     * Returns the scale of this BigDecimal.
     * 
     * @return int scale value.
     */
    public int scale() {
        return scale;
    }

    /**
     * Sets the scale of this BigDecimal.
     * 
     * @return BigDecimal a BigDecimal with the same value, but specified scale.
     */
    public BigDecimal setScale(int newScale) {
        return setScale(newScale, ROUND_UNNECESSARY);
    }

    /**
     * Sets the scale of this BigDecimal. The unscaled value is determined by
     * the rounding Mode
     * 
     * @return BigDecimal a BigDecimal with the same value, but specified cale.
     * @exception ArithmeticException
     *                rounding mode must be specified if lose of precision due
     *                to setting scale.
     * @exception IllegalArgumentException
     *                invalid rounding mode
     */
    public BigDecimal setScale(int newScale, int roundingMode) {
        if (roundingMode > 7 || roundingMode < 0) {
            throw new IllegalArgumentException("invalid rounding mode");
        }
        int delta = this.scale - newScale;
        if (delta == 0) {
            return this;
        }
        if (delta > 0) {
            return divideByTenPow(delta, roundingMode, newScale);
        } else {
            return new BigDecimal(intVal.multiplyByTenPow(-delta), newScale);
        }
    }

    private BigDecimal divideByTenPow(int delta, int roundingMode, int newScale) {
        if (delta < 10) {
            return divideByInteger(intVal, BigInteger.tenPows[delta], 1, roundingMode, newScale);
        } else {
            return divideByBigInteger(intVal, BigInteger.getTenPow(delta), roundingMode, newScale);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal setScale(int newScale, RoundingMode rm) {
        return setScale(newScale, rm.bigDecimalRM);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public short shortValueExact() {
        return (short)valueExact(5, "36767", "-36768");
    }
        
    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal stripTrailingZeros() {
        return stripTrailingZeros(Integer.MIN_VALUE);
    }

    /**
     * Remove trailing zeros to make the scale closer to the required scale
     * @param newScale the required scale
     * @return this BigDecimal with reduced scale
     */
    private BigDecimal stripTrailingZeros(int newScale) {
        if (intVal.testBit(0)) {
            return this;
        }
        BigDecimal result = this;
        StringBuffer strVal = new StringBuffer(intVal.toString());
        int checkedChar = strVal.length();
        while (--checkedChar > 0) {
            if (strVal.charAt(checkedChar) != '0') {
                break;
            }
            strVal.deleteCharAt(checkedChar);
            result.scale--;
            if (result.scale == newScale) {
                break;
            }
        }
        result.intVal = new BigInteger(strVal.toString());
        return result;
    }

    /**
     * Answers the signum function of this instance.
     * 
     * @return int -1, 0, or 1 if the receiver is negative, zero, or positive.
     */
    public int signum() {
        return intVal.signum();
    }

    /**
     * Answers the subtract result of the receiver and argument.
     * 
     * @return BigDecimal The result of subtracting the BigDecimal argument.
     */
    public BigDecimal subtract(BigDecimal value) {
        int delta = scale - value.scale;
        if (delta == 0) {
            return new BigDecimal(intVal.subtract(value.intVal), scale);
        }
        if (delta < 0) {
            return new BigDecimal(this.intVal.multiplyByTenPow(-delta)
                                  .subtract(value.intVal), value.scale);
        } else {
            return new BigDecimal(this.intVal
                                  .subtract(value.intVal.multiplyByTenPow(delta)),
                                  this.scale);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal subtract(BigDecimal value, MathContext mC) {
        if (mC.precision == 0) {
            return subtract(value);
        }
        return add(value.negate(), mC);
    }

    /**
     * Converts this to a BigInteger.
     * 
     * @return BigInteger BigDecimal equivalent of BigInteger.
     */
    public BigInteger toBigInteger() {
        if (scale == 0) {
            return intVal;
        }
        if (scale > 0) {
            return intVal.divide(BigInteger.getTenPow(scale));
        }
        return intVal.multiplyByTenPow(-scale);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigInteger toBigIntegerExact() {
        if (intVal.signum() == 0) {
            return intVal;
        }
        String strVal = this.intVal.toString();
        if (scale > 0) {
            // check the fractional part
            int checkedChar = strVal.length();
            for (int i = 1; i <= scale; i++) {
                if (strVal.charAt(--checkedChar) != '0') {
                    throw new ArithmeticException("nonzero fractional part");
                }
            }
        }
        return toBigInteger();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String toEngineeringString() {
        String intString = intVal.toString();
        if (scale == 0) {
            return intString;
        }
        boolean negNumber = intVal.signum() < 0;
        int startPoint = negNumber ? 2 : 1;
        int endPoint = intString.length();
        int exponent = -scale + intString.length() - startPoint;
        StringBuffer result = new StringBuffer(intString);
        if (scale > 0 && exponent >= -6) {
            if (exponent >= 0) {
                result.insert(exponent + startPoint, '.');
            } else {
                char zeros[] = new char[-exponent + 1];
                zeros[0] = '0';
                zeros[1] = '.';
                for (int i = 2; i < zeros.length; i++) {
                    zeros[i] = '0';
                }
                result.insert(negNumber ? 1 : 0, zeros);
            }
        } else {
            int delta = endPoint - startPoint;
            int rem = exponent % 3;
            if (rem != 0) { 
                // adjust exponent so it is a multiple of three
                if (intVal.signum() == 0) { 
                    // zero value
                    rem = rem < 0 ? -rem : 3 - rem;
                    exponent += rem;
                } else { 
                    // nonzero value
                    rem = rem < 0 ? rem + 3 : rem;
                    exponent -= rem;
                    startPoint += rem;
                }
                if (delta < 3) {
                    for (int i = rem - delta; i > 0; i--) {
                        result.insert(endPoint++, '0');
                    }
                }
            } 
            if (endPoint - startPoint >= 1) {
                result.insert(startPoint, '.');
                endPoint++;
            }
            if (exponent != 0) {
                result.insert(endPoint, 'E');
                if (exponent > 0) {
                    result.insert(++endPoint, '+');
                }
                result.insert(++endPoint, Integer.toString(exponent));
            }
        }
        return result.toString();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String toPlainString() {
        String intString = intVal.toString();
        if (scale == 0) {
            return intString;
        }
        boolean negNumber = intVal.signum() < 0;
        int startPoint = negNumber ? 2 : 1;
        int endPoint = intString.length();
        int dotPosition = -scale + intString.length() - startPoint;
        StringBuffer result = new StringBuffer(intString);
        char zeros[];
        if (scale > 0) {
            if (dotPosition >= 0) {
                result.insert(dotPosition + startPoint, '.');
            } else {
                zeros = new char[-dotPosition + 1];
                zeros[0] = '0';
                zeros[1] = '.';
                for (int i = 2; i < zeros.length; i++) {
                    zeros[i] = '0';
                }
                result.insert(negNumber ? 1 : 0, zeros);
            }
        } else {
            zeros = new char[-scale];
            for (int i = 0; i < zeros.length; i++) {
                zeros[i] = '0';
            }
            result.insert(endPoint, zeros);
        }
        return result.toString();
    }

    /**
     * Answers a string containing a concise, human-readable description of the
     * receiver.
     * 
     * @return String a printable representation for the receiver.
     */
    public String toString() {
        if (toStringImage == null) {
            toStringImage = intVal.toDecimalScaledString(scale);
        }
        return toStringImage;
    }

    /**
     * Calculates an exact value of a primitive type.
     * @param charsPerMaxValue the number of characters in a type's 
     * extreme value, e.g. 9 for an int.
     * @param maxValue the type's maximal value
     * @param minValue the type's minimal value
     * @return the long value of this BigDecimal
     */
    private long valueExact(int charsPerMaxValue, String maxValue, 
                            String minValue) {
        int valueSign = intVal.signum();
        if (valueSign == 0) {
            return 0;
        }
        StringBuffer strVal = new StringBuffer(intVal.toString());
        int startChar = valueSign == 1 ? 0 : 1;
        if (scale > 0) {
            // check the fractional part
            int checkedChar = strVal.length() - 1;
            for (int i = 1; i <= scale; i++) {
                if (strVal.charAt(checkedChar) != '0') {
                    throw new ArithmeticException("nonzero fractional part");
                }
                strVal.deleteCharAt(checkedChar);
                checkedChar--;
            }
        } else {
            for (int i = 1; i <= -scale; i++) {
                strVal.append('0');
            }
        }
        int delta = ((valueSign == 1) 
                     ? charsPerMaxValue 
                     : charsPerMaxValue + 1) - strVal.length();
        if (delta < 0) {
            throw new ArithmeticException("number does not fit in an int");
        }
        for (int i = 0; i < delta; i++) {
            strVal.insert(startChar, '0');
        }
        String stringValue = strVal.toString();
        if (valueSign == 1 && stringValue.compareTo(maxValue) > 0 ||
            valueSign == -1 && stringValue.compareTo(minValue) > 0 ) { 
            throw new ArithmeticException("number does not fit in an int");
        }
        return new Long(stringValue).longValue();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal ulp() {
        return new BigDecimal(BigInteger.ONE, scale);
    }

    /**
     * Returns an unscaled value of this BigDecimal.
     * 
     * @return BigInteger The unscaled value.
     */
    public BigInteger unscaledValue() {
        return intVal;
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        if (intVal == null) {
            throw new StreamCorruptedException("null unscaled value");
        }
    }
}
