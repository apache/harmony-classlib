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
 * @version $Revision: 1.11.2.3 $
 */

package java.math;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.io.Serializable;

/**
 * @com.intel.drl.spec_ref
 */
public class BigDecimal extends Number implements Comparable, Serializable {

    /**
     * @com.intel.drl.spec_ref
     */
    private static final long serialVersionUID = 6108874887143696463L; 

    /**
     * @com.intel.drl.spec_ref
     */
    private BigInteger intVal;

    /**
     * @com.intel.drl.spec_ref
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
    public static BigDecimal valueOf(long value) {
        return valueOf(value, 0);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static BigDecimal valueOf(long unscaledValue, int scale) {
        return new BigDecimal(BigInteger.valueOf(unscaledValue), scale);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal(BigInteger value) {
        intVal = value;
        scale = 0;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal(BigInteger unScaledValue, int scale) {
        intVal = unScaledValue;
        this.scale = scale;
    }

    /**
     * @com.intel.drl.spec_ref
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
            intVal = intVal.multiply(BigInteger.valueOf(5L).pow(-scale));
            scale = -scale;
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal(String value) {
        int count; // the significand's length
        int dotPlace = -1;
        int strLen = value.length();
        String unscaled;
        String exponent;
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
    public BigDecimal abs() {
        return new BigDecimal(intVal.abs(), this.scale);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal add(BigDecimal value) {
        if (scale == value.scale) {
            return new BigDecimal(intVal.add(value.intVal), scale);
        }
        boolean thisScaleIsLess = this.scale < value.scale;
        int newScale = thisScaleIsLess ? value.scale : this.scale;
        if (thisScaleIsLess) {
            return new BigDecimal(this.intVal.multiply
                (BigInteger.TEN.pow(value.scale - this.scale)).add(value.intVal), newScale);
        } else {
            return new BigDecimal(value.intVal.multiply
                (BigInteger.TEN.pow(this.scale - value.scale)).add(this.intVal), newScale);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int compareTo(BigDecimal value) {
        if (this.scale == value.scale) {
            return this.intVal.compareTo(value.intVal);
        }
        if (this.scale < value.scale) {
            return this.intVal.multiply(BigInteger.TEN.pow(value.scale - this.scale)).
                compareTo(value.intVal);
        } else {
            return this.intVal.compareTo(value.intVal.multiply
                (BigInteger.TEN.pow(this.scale - value.scale)));
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int compareTo(Object object) {
        if (object instanceof BigDecimal) {
            return (this.compareTo((BigDecimal)object));
        }
        throw new ClassCastException("BigDecimals are comparable only with other BigDecimals");
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal divide(BigDecimal value, int roundingMode) {
        return divide(value, this.scale, roundingMode);
    }

    /**
     * @com.intel.drl.spec_ref
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
            divisor = divisor.multiply(BigInteger.TEN.pow(-exponent));
        } else if (exponent > 0) {
            dividend = dividend.multiply(BigInteger.TEN.pow(exponent));
        }
        BigInteger res[] = dividend.divideAndRemainder(divisor);
        BigInteger quotient = res[0];
        if (res[1].signum() != 0) {
            if (roundingMode == ROUND_UNNECESSARY) {
                throw new ArithmeticException
                    ("rounding mode is ROUND_UNNECESSARY but the result is not exact");
            }
            boolean negativeQuotient = intVal.signum() != divisor.signum();
            boolean negativeRemainder = res[1].signum() == -1;
            boolean negativeDivisor = divisor.signum() == -1;
            if (roundingMode == ROUND_FLOOR) {
                roundingMode = negativeQuotient ? ROUND_UP : ROUND_DOWN;
            } else if (roundingMode == ROUND_CEILING) {
                roundingMode = negativeQuotient ? ROUND_DOWN : ROUND_UP;
            } else {
                if (negativeDivisor) {
                    divisor = divisor.abs();
                }
                if (negativeRemainder) {
                    res[1] = res[1].abs();
                }
                // distance == -1 if the quotient is NOT the nearest neighbor to the exact result;
                // distance == 0 if the exact result is equidistant from the neighbors;
                // distance == +1 if the quotient is the nearest neighbor to the exact result.
                int distance = divisor.subtract(res[1]).compareTo(res[1]);
                if (roundingMode == ROUND_HALF_DOWN) {
                    roundingMode = distance >= 0 ? ROUND_DOWN : ROUND_UP;
                } else if (roundingMode == ROUND_HALF_UP) {
                    roundingMode = distance > 0 ? ROUND_DOWN : ROUND_UP;
                } else if (roundingMode == ROUND_HALF_EVEN) {
                    if (distance > 0) {
                        roundingMode = ROUND_DOWN;
                    } else if (distance < 0) {
                        roundingMode = ROUND_UP;
                    } else if (!quotient.testBit(0)) {
                        roundingMode = ROUND_DOWN;
                    } else {
                        roundingMode = ROUND_UP;
                    }
                } 
            }
            if (roundingMode == ROUND_UP) { 
                quotient = quotient.add(BigInteger.valueOf(negativeQuotient ? -1 : 1));
            }
        }
        return new BigDecimal(quotient, quotientScale);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public double doubleValue() {
        return Double.valueOf(toString()).doubleValue();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean equals(Object object) {
        return (object instanceof BigDecimal &&
            this.compareTo(object) == 0 && 
            this.scale == ((BigDecimal)object).scale);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public float floatValue() {
        return Float.valueOf(toString()).floatValue();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int hashCode() {
        return intVal.intValue() + scale;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int intValue() {
        return toBigInteger().intValue();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public long longValue() {
        return toBigInteger().longValue();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal max(BigDecimal value) {
        return (this.compareTo(value) >= 0 ? this : value);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal min(BigDecimal value) {
        return (this.compareTo(value) <= 0 ? this : value);
    }

    /**
     * Moves the decimal point shift digits
     * @param shift shift distance
     */
    private BigDecimal movePoint(int shift) {
        int newScale = getValidInt((long)scale + (long)shift, "scale");
        if (newScale > 0) {
            return new BigDecimal(intVal, newScale);
        }
        return new BigDecimal(this.intVal.multiply(BigInteger.TEN.pow(-newScale)), 0);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal movePointLeft(int shift) {
        return movePoint(shift);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal movePointRight(int shift) {
        return movePoint(-shift);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal multiply(BigDecimal value) {
        return new BigDecimal(this.intVal.multiply(value.intVal), 
            this.scale + value.scale);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal negate() {
        return new BigDecimal(intVal.negate(), scale);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int scale() {
        return scale;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal setScale(int newScale) {
        return setScale(newScale, ROUND_UNNECESSARY);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal setScale(int newScale, int roundingMode) {
        int delta = this.scale - newScale;
        if (delta == 0) {
            return this;
        }
        if (delta > 0) {
            return divide(new BigDecimal(BigInteger.TEN.pow(delta), delta), newScale, roundingMode);
        } else {
            return new BigDecimal(intVal.multiply(BigInteger.TEN.pow(-delta)), newScale);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int signum() {
        return intVal.signum();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigDecimal subtract(BigDecimal value) {
        if (scale == value.scale) {
            return new BigDecimal(intVal.subtract(value.intVal), scale);
        }
        boolean thisScaleIsLess = this.scale < value.scale;
        int newScale = thisScaleIsLess ? value.scale : this.scale;
        if (thisScaleIsLess) {
            return new BigDecimal(this.intVal.multiply(BigInteger.TEN.pow(value.scale - this.scale)).
                subtract(value.intVal), newScale);
        } else {
            return new BigDecimal(this.intVal.
                subtract(value.intVal.multiply(BigInteger.TEN.pow(this.scale - value.scale))), newScale);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigInteger toBigInteger() {
        if (scale == 0) {
            return intVal;
        }
        if (scale > 0) {
            return intVal.divide(BigInteger.TEN.pow(scale));
        }
        return intVal.multiply(BigInteger.TEN.pow(-scale));
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
        int dotPosition = -scale + intString.length() - (negNumber ? 2 : 1);
        StringBuffer result = new StringBuffer(intString);
        char zeros[];
        if (scale > 0) {
            if (dotPosition >= 0) {
                result.insert(dotPosition + (negNumber ? 2 : 1), '.');
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
     * @com.intel.drl.spec_ref
     */
    public String toString() {
        String intString = intVal.toString();
        if (scale == 0) {
            return intString;
        }
        boolean negNumber = intVal.signum() < 0;
        int startPoint = negNumber ? 2 : 1;
        int endPoint = intString.length();
        int exponent = -scale + intString.length() - (negNumber ? 2 : 1);
        StringBuffer result = new StringBuffer();
        result.append(intString);
        if (scale > 0 && exponent >= -6) {
            if (exponent >= 0) {
                result.insert(exponent + (negNumber ? 2 : 1), '.');
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
            if (endPoint - startPoint >= 1) {
                result.insert(startPoint, '.');
                endPoint++;
            }
            result.insert(endPoint, 'E');
            if (exponent > 0) {
                result.insert(++endPoint, '+');
            }
            result.insert(++endPoint, Integer.toString(exponent));
        }
        return result.toString();
    }

    /**
     * @com.intel.drl.spec_ref
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