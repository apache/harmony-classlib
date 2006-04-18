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
            intVal = intVal.multiply(BigInteger.valueOf(5L).pow(-scale));
            scale = -scale;
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
     * Answers the absolute value of this BigDecimal.
     * 
     * @return BigDecimal absolute value of the receiver.
     */
    public BigDecimal abs() {
        return new BigDecimal(intVal.abs(), this.scale);
    }

    /**
     * Answers the sum of the receiver and argument.
     * 
     * @return BigDecimal The sum of adding two BigDecimal.
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
     * Compares the receiver BigDecimal and argument BigDecimal e.x 1.00 & 1.0
     * will return 0 in compareTo.
     * 
     * @return int 0 - equal; 1 - this > value; -1 - this < value.
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
     * Compares an receiver to the argument Object.
     * 
     * @return int 0 - equal; 1 - this > object; -1 - this < object
     * @exception ClassCastException
     *                if the argument is not of type BigDecimal
     */
    public int compareTo(Object object) {
        if (object instanceof BigDecimal) {
            return (this.compareTo((BigDecimal)object));
        }
        throw new ClassCastException("BigDecimals are comparable only with other BigDecimals");
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
            this.compareTo(object) == 0 && 
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
     * Converts this BigDecimal to a long.
     * 
     * @return long long representation of the receiver.
     */
    public long longValue() {
        return toBigInteger().longValue();
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
        int newScale = getValidInt((long)scale + (long)shift, "scale");
        if (newScale > 0) {
            return new BigDecimal(intVal, newScale);
        }
        return new BigDecimal(this.intVal.multiply(BigInteger.TEN.pow(-newScale)), 0);
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
     * Negates this BigDecimal value.
     * 
     * @return BigDecimal new BigDecimal with value negated.
     */
    public BigDecimal negate() {
        return new BigDecimal(intVal.negate(), scale);
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
     * Converts this to a BigInteger.
     * 
     * @return BigInteger BigDecimal equivalent of BigInteger.
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
     * Answers a string containing a concise, human-readable description of the
     * receiver.
     * 
     * @return String a printable representation for the receiver.
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
