/*
 *  Copyright 2005, 2006 The Apache Software Foundation or its licensors, as applicable.
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

package java.math;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;

/**
 * @author Daniel Fridlender
 * @author Matthias Gallé
 * @author Mariano Heredia
 * @author Miguel Vasquez
 * 
 * @ar.org.fitc.spec_ref 
 */
public class BigDecimal extends Number implements Comparable<BigDecimal>,
        Serializable {

    /* Static Fields */

    /** @ar.org.fitc.spec_ref */
    public static final BigDecimal ZERO = new BigDecimal(BigInteger.ZERO, 0);

    /** @ar.org.fitc.spec_ref */
    public static final BigDecimal ONE = new BigDecimal(BigInteger.ONE, 0);

    /** @ar.org.fitc.spec_ref */
    public static final BigDecimal TEN = new BigDecimal(BigInteger.TEN, 0);

    /** @ar.org.fitc.spec_ref */
    public static final int ROUND_UP = 0;

    /** @ar.org.fitc.spec_ref */
    public static final int ROUND_DOWN = 1;

    /** @ar.org.fitc.spec_ref */
    public static final int ROUND_CEILING = 2;

    /** @ar.org.fitc.spec_ref */
    public static final int ROUND_FLOOR = 3;

    /** @ar.org.fitc.spec_ref */
    public static final int ROUND_HALF_UP = 4;

    /** @ar.org.fitc.spec_ref */
    public static final int ROUND_HALF_DOWN = 5;

    /** @ar.org.fitc.spec_ref */
    public static final int ROUND_HALF_EVEN = 6;

    /** @ar.org.fitc.spec_ref */
    public static final int ROUND_UNNECESSARY = 7;

    /* Private Fields */

    /** @ar.org.fitc.spec_ref */
    private static final long serialVersionUID = 6108874887143696463L;

    /** The double closer to <code>Log10(2)</code>. */
    private static final double LOG10_2 = 0.3010299956639812;

    /** The <code>String</code> representation is cached. */
    private transient String toStringImage = null;

    /**
     * An array with powers of five that fit in the type <code>long</code>
     * (<code>5^0,5^1,...,5^27</code>)
     */
    private static final BigInteger FIVE_POW[] = new BigInteger[28];

    /**
     * An array with powers of ten that fit in the type <code>long</code>
     * (<code>10^0,10^1,...,10^18</code>)
     */
    private static final BigInteger TEN_POW[] = new BigInteger[19];

    /**
     * An array with the first <code>BigInteger</code> scaled by zero.
     * (<code>[0,0],[1,0],...,[10,0]</code>)
     */
    private static final BigDecimal BI_SCALED_BY_ZERO[] = new BigDecimal[11];

    /**
     * An array with the zero number scaled by the first positive scales.
     * (<code>0*10^0, 0*10^1, ..., 0*10^10</code>)
     */
    private static final BigDecimal ZERO_SCALED_BY[] = new BigDecimal[11];

    /** An array filled with characters <code>'0'</code>. */
    private static final char[] CH_ZEROS = new char[100];

    static {
        // To fill all static arrays.
        int i = 0;
        long fivePow = 1;

        for (; i < ZERO_SCALED_BY.length; i++) {
            BI_SCALED_BY_ZERO[i] = new BigDecimal(BigInteger.valueOf(i), 0);
            ZERO_SCALED_BY[i] = new BigDecimal(BigInteger.ZERO, i);
            FIVE_POW[i] = BigInteger.valueOf(fivePow);
            TEN_POW[i] = BigInteger.valueOf(fivePow << i);
            CH_ZEROS[i] = '0';
            fivePow *= 5;
        }
        for (; i < TEN_POW.length; i++) {
            FIVE_POW[i] = BigInteger.valueOf(fivePow);
            TEN_POW[i] = BigInteger.valueOf(fivePow << i);
            CH_ZEROS[i] = '0';
            fivePow *= 5;
        }
        for (; i < FIVE_POW.length; i++) {
            FIVE_POW[i] = BigInteger.valueOf(fivePow);
            CH_ZEROS[i] = '0';
            fivePow *= 5;
        }
        for (; i < CH_ZEROS.length; i++) {
            CH_ZEROS[i] = '0';
        }
    }

    /**
     * The arbitrary precision integer (unscaled value) in the internal
     * representation of <code>BigDecimal</code>.
     */
    private BigInteger unscaledValue;

    /** 
     * The 32-bit integer scale in the internal representation of <code>BigDecimal</code>. 
     */
    private int scale;

    /**
     * Represent the number of decimal digits in the unscaled value. This 
     * precision is calculated the first time, and used in the following 
     * calls of method <code>precision()</code>. Note that some call to 
     * the private method <code>inplaceRound()</code> could update this field.
     * @see #precision()
     * @see #inplaceRound(MathContext)
     */
    private int precision = 0;

    /* Constructors */

    /** @ar.org.fitc.spec_ref */
    public BigDecimal(char[] in, int offset, int len) {
        int begin = offset; // first index to be copied
        int last = offset + (len - 1); // last index to be copied
        String scaleString = null; // buffer for scale
        StringBuffer unscaledBuffer; // buffer for unscaled value
        long newScale; // the new scale

        if (in == null) {
            throw new NullPointerException();
        }
        if ((last >= in.length) || (offset < 0) || (len <= 0) || (last < 0)) {
            throw new NumberFormatException();
        }
        unscaledBuffer = new StringBuffer(len);
        // To skip a possible '+' symbol
        if ((offset <= last) && (in[offset] == '+')) {
            offset++;
            begin++;
        }
        // Acumulating all digits until a possible decimal point
        for (; (offset <= last) && (in[offset] != '.') && (in[offset] != 'e')
                && (in[offset] != 'E'); offset++)
            ;
        unscaledBuffer.append(in, begin, offset - begin);
        // A decimal point was found
        if ((offset <= last) && (in[offset] == '.')) {
            offset++;
            // Acumulating all digits until a possible exponent
            begin = offset;
            for (; (offset <= last) && (in[offset] != 'e')
                    && (in[offset] != 'E'); offset++)
                ;
            scale = offset - begin;
            unscaledBuffer.append(in, begin, scale);
        } else {
            scale = 0;
        }
        // An exponent was found
        if ((offset <= last) && ((in[offset] == 'e') || (in[offset] == 'E'))) {
            offset++;
            // Checking for a posible sign of scale
            begin = offset;
            if ((offset <= last) && (in[offset] == '+')) {
                offset++;
                if ((offset <= last) && (in[offset] != '-')) {
                    begin++;
                }
            }
            // Acumulating all reminaining digits
            scaleString = String.valueOf(in, begin, last + 1 - begin);
            // Checking if the scale is defined            
            newScale = (long) scale - Integer.parseInt(scaleString);
            scale = (int) newScale;
            if (newScale != scale) {
                throw new NumberFormatException("Scale out of range.");
            }
        }
        // Parsing the unscaled value
        unscaledValue = new BigInteger(unscaledBuffer.toString());
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal(char[] in, int offset, int len, MathContext mc) {
        this(in, offset, len);
        inplaceRound(mc);
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal(char[] in) {
        this(in, 0, in.length);
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal(char[] in, MathContext mc) {
        this(in, 0, in.length);
        inplaceRound(mc);
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal(String val) {
        this(val.toCharArray(), 0, val.length());
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal(String val, MathContext mc) {
        this(val.toCharArray(), 0, val.length());
        inplaceRound(mc);
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal(double val) {
        if (Double.isInfinite(val) || Double.isNaN(val)) {
            throw new NumberFormatException("Infinite or NaN");
        }
        long bits = Double.doubleToLongBits(val); // IEEE-754
        long mantisa;
        int trailingZeros;
        // Extracting the exponent, note that the bias is 1023
        scale = 1075 - (int) ((bits >> 52) & 0x7FFL);
        // Extracting the 52 bits of the mantisa.
        mantisa = (scale == 1075) ? (bits & 0xFFFFFFFFFFFFFL) << 1
                : (bits & 0xFFFFFFFFFFFFFL) | 0x10000000000000L;
        // To simplify all factors '2' in the mantisa 
        if (scale > 0) {
            trailingZeros = Math
                    .min(scale, Long.numberOfTrailingZeros(mantisa));
            mantisa >>>= trailingZeros;
            scale -= trailingZeros;
        }
        // Calculating the new unscaled value and the new scale
        unscaledValue = BigInteger.valueOf(((bits >> 63) == 0) ? mantisa
                : -mantisa);
        if (scale < 0) {
            unscaledValue = unscaledValue.shiftLeft(-scale);
            scale = 0;
        } else if (scale > 0) {
            // m * 2^e =  (m * 5^(-e)) * 10^e
            unscaledValue = (scale < FIVE_POW.length) ? unscaledValue
                    .multiply(FIVE_POW[scale]) : unscaledValue
                    .multiply(FIVE_POW[1].pow(scale));
        }
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal(double val, MathContext mc) {
        this(val);
        inplaceRound(mc);
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal(BigInteger val) {
        this(val, 0);
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal(BigInteger val, MathContext mc) {
        this(val);
        inplaceRound(mc);
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal(BigInteger unscaledVal, int scale) {
        unscaledValue = unscaledVal;
        this.scale = scale;
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal(BigInteger unscaledVal, int scale, MathContext mc) {
        this(unscaledVal, scale);
        inplaceRound(mc);
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal(int val) {
        this((long) val);
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal(int val, MathContext mc) {
        this((long) val);
        inplaceRound(mc);
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal(long val) {
        unscaledValue = BigInteger.valueOf(val);
        scale = 0;
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal(long val, MathContext mc) {
        this(val);
        inplaceRound(mc);
    }

    /* Public Methods */

    /** @ar.org.fitc.spec_ref */
    public static BigDecimal valueOf(long unscaledVal, int scale) {
        if ((unscaledVal == 0) && (scale >= 0)
                && (scale < ZERO_SCALED_BY.length)) {
            return ZERO_SCALED_BY[scale];
        }
        if ((scale == 0) && (unscaledVal >= 0)
                && (unscaledVal < BI_SCALED_BY_ZERO.length)) {
            return BI_SCALED_BY_ZERO[(int) unscaledVal];
        }
        return new BigDecimal(BigInteger.valueOf(unscaledVal), scale);
    }

    /** @ar.org.fitc.spec_ref */
    public static BigDecimal valueOf(long val) {
        return valueOf(val, 0);
    }

    /** @ar.org.fitc.spec_ref */
    public static BigDecimal valueOf(double val) {
        if (Double.isInfinite(val) || Double.isNaN(val)) {
            throw new NumberFormatException("Infinity or NaN");
        }
        return new BigDecimal(Double.toString(val));
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal add(BigDecimal augend) {
        long diffScale = (long) this.scale - augend.scale;
        // Fast return when some operand is zero
        if (this.signum() == 0) {
            if (diffScale <= 0) {
                return augend;
            }
            if (augend.signum() == 0) {
                return this;
            }
        } else if (augend.signum() == 0) {
            if (diffScale >= 0) {
                return this;
            }
        }
        // Let be:  this = [u1,s1]  and  augend = [u2,s2]
        if (diffScale == 0) {
            // case s1 == s2: [u1 + u2 , s1]
            return new BigDecimal(this.unscaledValue.add(augend.unscaledValue),
                    this.scale);
        } else if (diffScale > 0) {
            // case s1 > s2 : [(u1 + u2) * 10 ^ (s1 - s2) , s1]
            return new BigDecimal(this.unscaledValue.add(augend.unscaledValue
                    .multiply(powerOf10(diffScale))), this.scale);
        } else {// case s2 > s1 : [(u2 + u1) * 10 ^ (s2 - s1) , s2]
            return new BigDecimal(augend.unscaledValue.add(this.unscaledValue
                    .multiply(powerOf10(-diffScale))), augend.scale);
        }
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal add(BigDecimal augend, MathContext mc) {
        BigDecimal larger; // operand with the largest unscaled value
        BigDecimal smaller; // operand with the smallest unscaled value
        BigInteger tempBI;
        long diffScale = (long) this.scale - augend.scale;
        int largerSignum;
        // Some operand is zero or the precision is infinity  
        if ((augend.signum() == 0) || (this.signum() == 0)
                || (mc.getPrecision() == 0)) {
            return add(augend).round(mc);
        }
        // Cases where there is room for optimizations
        if (this.aproxPrecision() < diffScale - 1) {
            larger = augend;
            smaller = this;
        } else if (augend.aproxPrecision() < -diffScale - 1) {
            larger = this;
            smaller = augend;
        } else {// No optimization is done 
            return add(augend).round(mc);
        }
        if (mc.getPrecision() >= larger.aproxPrecision()) {
            // No optimization is done
            return add(augend).round(mc);
        }
        // Cases where it's unnecessary to add two numbers with very different scales 
        largerSignum = larger.signum();
        if (largerSignum == smaller.signum()) {
            tempBI = larger.unscaledValue.multiply(BigInteger.TEN).add(
                    BigInteger.valueOf(largerSignum));
        } else {
            tempBI = larger.unscaledValue.subtract(BigInteger
                    .valueOf(largerSignum));
            tempBI = tempBI.multiply(BigInteger.TEN).add(
                    BigInteger.valueOf(largerSignum * 9));
        }
        // Rounding the improved adding 
        larger = new BigDecimal(tempBI, larger.scale + 1);
        return larger.round(mc);
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal subtract(BigDecimal subtrahend) {
        long diffScale = (long) this.scale - subtrahend.scale;
        // Fast return when some operand is zero
        if (this.signum() == 0) {
            if (diffScale <= 0) {
                return subtrahend.negate();
            }
            if (subtrahend.signum() == 0) {
                return this;
            }
        } else if (subtrahend.signum() == 0) {
            if (diffScale >= 0) {
                return this;
            }
        }
        // Let be: this = [u1,s1] and subtrahend = [u2,s2] so:
        if (diffScale == 0) {
            // case s1 = s2 : [u1 - u2 , s1]
            return new BigDecimal(this.unscaledValue
                    .subtract(subtrahend.unscaledValue), this.scale);
        } else if (diffScale > 0) {
            // case s1 > s2 : [ u1 - u2 * 10 ^ (s1 - s2) , s1 ]
            return new BigDecimal(this.unscaledValue
                    .subtract(subtrahend.unscaledValue
                            .multiply(powerOf10(diffScale))), this.scale);
        } else {// case s2 > s1 : [ u1 * 10 ^ (s2 - s1) - u2 , s2 ]
            return new BigDecimal(this.unscaledValue.multiply(
                    powerOf10(-diffScale)).subtract(subtrahend.unscaledValue),
                    subtrahend.scale);
        }
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal subtract(BigDecimal subtrahend, MathContext mc) {
        long diffScale = subtrahend.scale - (long) this.scale;
        int thisSignum;
        BigDecimal leftOperand; // it will be only the left operand (this) 
        BigInteger tempBI;
        // Some operand is zero or the precision is infinity  
        if ((subtrahend.signum() == 0) || (this.signum() == 0)
                || (mc.getPrecision() == 0)) {
            return subtract(subtrahend).round(mc);
        }
        // Now:   this != 0   and   subtrahend != 0
        if (subtrahend.aproxPrecision() < diffScale - 1) {
            // Cases where it is unnecessary to subtract two numbers with very different scales
            if (mc.getPrecision() < this.aproxPrecision()) {
                thisSignum = this.signum();
                if (thisSignum != subtrahend.signum()) {
                    tempBI = this.unscaledValue.multiply(BigInteger.TEN).add(
                            BigInteger.valueOf(thisSignum));
                } else {
                    tempBI = this.unscaledValue.subtract(BigInteger
                            .valueOf(thisSignum));
                    tempBI = tempBI.multiply(BigInteger.TEN).add(
                            BigInteger.valueOf(thisSignum * 9));
                }
                // Rounding the improved substracting
                leftOperand = new BigDecimal(tempBI, this.scale + 1);
                return leftOperand.round(mc);
            }
        }
        // No optimization is done
        return subtract(subtrahend).round(mc);
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal multiply(BigDecimal multiplicand) {
        long newScale = (long) this.scale + multiplicand.scale;

        if ((this.signum() == 0) || (multiplicand.signum() == 0)) {
            return zeroScaledBy(newScale);
        } else {
            /* Let be: this = [u1,s1] and multiplicand = [u2,s2] so:
             * this x multiplicand = [ s1 * s2 , s1 + s2 ] */
            return new BigDecimal(this.unscaledValue
                    .multiply(multiplicand.unscaledValue), toIntScale(newScale));
        }
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal multiply(BigDecimal multiplicand, MathContext mc) {
        BigDecimal result = multiply(multiplicand);

        result.inplaceRound(mc);
        return result;
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal divide(BigDecimal divisor, int scale, int roundingMode) {
        return divide(divisor, scale, RoundingMode.valueOf(roundingMode));
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal divide(BigDecimal divisor, int scale,
            RoundingMode roundingMode) {
        // Let be: this = [u1,s1]  and  divisor = [u2,s2]
        long diffScale = ((long) this.scale - divisor.scale) - (long) scale;
        int sign = this.signum() * divisor.signum(); // sign of the result
        int compRem; // 'compare to remainder'
        BigInteger quotAndRem[] = { this.unscaledValue }; // quotient and remainder
        BigInteger scaledDivisor = divisor.unscaledValue; // for scaling of 'u2'

        if (roundingMode == null) {
            throw new NullPointerException();
        }
        if (divisor.signum() == 0) {
            throw new ArithmeticException("BigInteger divide by zero");
        }
        if (diffScale > 0) {
            // Multiply 'u2'  by:  10^((s1 - s2) - scale)
            scaledDivisor = scaledDivisor.multiply(powerOf10(diffScale));
        } else if (diffScale < 0) {
            // Multiply 'u1'  by:  10^(scale - (s1 - s2))
            quotAndRem[0] = quotAndRem[0].multiply(powerOf10(-diffScale));
        }
        quotAndRem = quotAndRem[0].divideAndRemainder(scaledDivisor);
        // If after division there is a remainder...
        if (quotAndRem[1].signum() != 0) {
            // Checking if:  remainder * 2 >= scaledDivisor 
            compRem = quotAndRem[1].abs().shiftLeft(1).compareTo(
                    scaledDivisor.abs());
            compRem = roundingBehavior(quotAndRem[0].testBit(0) ? 1 : 0, sign
                    * (5 + compRem), roundingMode);
            if (compRem != 0) {
                quotAndRem[0] = quotAndRem[0].add(BigInteger.valueOf(compRem));
            }
        }
        // Constructing the result with the appropriate unscaled value
        return new BigDecimal(quotAndRem[0], scale);
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal divide(BigDecimal divisor, int roundingMode) {
        return divide(divisor, scale, RoundingMode.valueOf(roundingMode));
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal divide(BigDecimal divisor, RoundingMode roundingMode) {
        return divide(divisor, scale, roundingMode);
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal divide(BigDecimal divisor) {
        BigInteger p = this.unscaledValue;
        BigInteger q = divisor.unscaledValue;
        BigInteger gcd; // greatest common divisor between 'p' and 'q'
        BigInteger quotAndRem[];
        long diffScale = (long) scale - divisor.scale;
        int newScale; // the new scale for final quotient
        int k; // number of factors "2" in 'q'
        int l = 0; // number of factors "5" in 'q'
        int i = 1;
        int lastPow = FIVE_POW.length - 1;

        if (divisor.signum() == 0) {
            throw new ArithmeticException("BigInteger divide by zero");
        }
        if (p.signum() == 0) {
            return zeroScaledBy(diffScale);
        }
        // To divide both by the GCD
        gcd = p.gcd(q);
        p = p.divide(gcd);
        q = q.divide(gcd);
        // To simplify all "2" factors of q, dividing by 2^k
        k = q.getLowestSetBit();
        q = q.shiftRight(k);
        // To simplify all "5" factors of q, dividing by 5^l
        do {
            quotAndRem = q.divideAndRemainder(FIVE_POW[i]);
            if (quotAndRem[1].signum() == 0) {
                l += i;
                if (i < lastPow) {
                    i++;
                }
                q = quotAndRem[0];
            } else {
                if (i == 1) {
                    break;
                }
                i = 1;
            }
        } while (true);
        // If  abs(q) != 1  then the quotient is periodic
        if (!q.abs().equals(BigInteger.ONE)) {
            throw new ArithmeticException("Non-terminating decimal expansion;"
                    + " no exact representable decimal result.");
        }
        // The sign of the is fixed and the quotient will be saved in 'p'
        if (q.signum() < 0) {
            p = p.negate();
        }
        // Checking if the new scale is out of range
        newScale = toIntScale(diffScale + Math.max(k, l));
        // k >= 0  and  l >= 0  implies that  k - l  is in the 32-bit range
        i = k - l;
        p = (i >= FIVE_POW.length) ? p.multiply(FIVE_POW[1].pow(i))
                : (i > 0) ? p.multiply(FIVE_POW[i]) : p.shiftLeft(i);
        return new BigDecimal(p, newScale);
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal divide(BigDecimal divisor, MathContext mc) {
        /* Calculating how many zeros must be append to 'dividend'
         * to obtain a  quotient with at least 'mc.precision()' digits */
        long traillingZeros = (long) mc.getPrecision() + 2L
                + (long) divisor.aproxPrecision() - (long) aproxPrecision();
        long diffScale = (long) scale - divisor.scale;
        long newScale = diffScale; // scale of the final quotient
        int compRem; // to compare the remainder
        int i = 1; // index   
        int lastPow = TEN_POW.length - 1; // last power of ten
        BigInteger integerQuot; // for temporal results
        BigInteger quotAndRem[] = { unscaledValue };
        // In special cases it reduces the problem to call the dual method
        if ((mc.getPrecision() == 0) || (this.signum() == 0)
                || (divisor.signum() == 0)) {
            return this.divide(divisor);
        }
        if (traillingZeros > 0) {
            // To append trailing zeros at end of dividend
            quotAndRem[0] = unscaledValue.multiply(powerOf10(traillingZeros));
            newScale += traillingZeros;
        }
        quotAndRem = quotAndRem[0].divideAndRemainder(divisor.unscaledValue);
        integerQuot = quotAndRem[0];
        // Calculating the exact quotient with at least 'mc.precision()' digits
        if (quotAndRem[1].signum() != 0) {
            // Checking if:   2 * remainder >= divisor ?
            compRem = quotAndRem[1].shiftLeft(1).compareTo(
                    divisor.unscaledValue);
            // quot := quot * 10 + r;     with 'r' in {-6,-5,-4, 0,+4,+5,+6}
            integerQuot = integerQuot.multiply(BigInteger.TEN).add(
                    BigInteger.valueOf(quotAndRem[0].signum() * (5 + compRem)));
            newScale++;
        } else {
            // To strip trailing zeros until the preferred scale is reached
            while (!integerQuot.testBit(0)) {
                quotAndRem = integerQuot.divideAndRemainder(TEN_POW[i]);
                if ((quotAndRem[1].signum() == 0)
                        && (newScale - i >= diffScale)) {
                    newScale -= i;
                    if (i < lastPow) {
                        i++;
                    }
                    integerQuot = quotAndRem[0];
                } else {
                    if (i == 1) {
                        break;
                    }
                    i = 1;
                }
            }
        }
        // To perform rounding
        return new BigDecimal(integerQuot, toIntScale(newScale), mc);
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal divideToIntegralValue(BigDecimal divisor) {
        BigInteger integralValue; // the integer of result
        BigInteger powerOfTen; // some power of ten
        BigInteger quotAndRem[] = { unscaledValue };
        long newScale = (long) this.scale - divisor.scale;
        long tempScale = 0;
        int i = 1;
        int lastPow = TEN_POW.length - 1;

        if (divisor.signum() == 0) {
            throw new ArithmeticException("BigInteger divide by zero");
        }
        if ((divisor.aproxPrecision() + newScale > this.aproxPrecision() + 1L)
                || (this.signum() == 0)) {
            /* If the divisor's integer part is greater than this's integer part,
             * the result must be zero with the apropriate scale */
            integralValue = BigInteger.ZERO;
        } else if (newScale == 0) {
            integralValue = unscaledValue.divide(divisor.unscaledValue);
        } else if (newScale > 0) {
            powerOfTen = powerOf10(newScale);
            integralValue = unscaledValue.divide(divisor.unscaledValue
                    .multiply(powerOfTen));
            integralValue = integralValue.multiply(powerOfTen);
        } else {// (newScale < 0)
            powerOfTen = powerOf10(-newScale);
            integralValue = unscaledValue.multiply(powerOfTen).divide(
                    divisor.unscaledValue);
            // To strip trailing zeros aproximating to the preferred scale
            while (!integralValue.testBit(0)) {
                quotAndRem = integralValue.divideAndRemainder(TEN_POW[i]);
                if ((quotAndRem[1].signum() == 0)
                        && (tempScale - i >= newScale)) {
                    tempScale -= i;
                    if (i < lastPow) {
                        i++;
                    }
                    integralValue = quotAndRem[0];
                } else {
                    if (i == 1) {
                        break;
                    }
                    i = 1;
                }
            }
            newScale = tempScale;
        }
        return ((integralValue.signum() == 0) ? zeroScaledBy(newScale)
                : new BigDecimal(integralValue, toIntScale(newScale)));
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal divideToIntegralValue(BigDecimal divisor, MathContext mc) {
        int mcPrecision = mc.getPrecision();
        int diffPrecision = this.precision() - divisor.precision();
        int lastPow = TEN_POW.length - 1;
        long diffScale = (long) this.scale - divisor.scale;
        long newScale = diffScale;
        long quotPrecision = diffPrecision - diffScale + 1;
        BigInteger quotAndRem[] = new BigInteger[2];
        // In special cases it call the dual method
        if ((mcPrecision == 0) || (this.signum() == 0)
                || (divisor.signum() == 0)) {
            return this.divideToIntegralValue(divisor);
        }
        // Let be:   this = [u1,s1]   and   divisor = [u2,s2]
        if (quotPrecision <= 0) {
            quotAndRem[0] = BigInteger.ZERO;
        } else if (diffScale == 0) {
            // CASE s1 == s2:  to calculate   u1 / u2 
            quotAndRem[0] = this.unscaledValue.divide(divisor.unscaledValue);
        } else if (diffScale > 0) {
            // CASE s1 >= s2:  to calculate   u1 / (u2 * 10^(s1-s2)  
            quotAndRem[0] = this.unscaledValue.divide(divisor.unscaledValue
                    .multiply(powerOf10(diffScale)));
            // To chose  10^newScale  to get a quotient with at least 'mc.precision()' digits
            newScale = Math.min(diffScale, Math.max((long) mcPrecision
                    - quotPrecision + 1, 0));
            // To calculate: (u1 / (u2 * 10^(s1-s2)) * 10^newScale
            quotAndRem[0] = quotAndRem[0].multiply(powerOf10(newScale));
        } else {// CASE s2 > s1:   
            /* To calculate the minimus power of ten, such that the quotient 
             *   (u1 * 10^exp) / u2   has at least 'mc.precision()' digits. */
            long exp = Math.min(-diffScale, Math.max((long) mcPrecision
                    - diffPrecision, 0));
            long compRemDiv;
            // Let be:   (u1 * 10^exp) / u2 = [q,r]  
            quotAndRem = this.unscaledValue.multiply(powerOf10(exp))
                    .divideAndRemainder(divisor.unscaledValue);
            newScale += exp; // To fix the scale
            exp = -newScale; // The remaining power of ten
            // If after division there is a remainder...
            if ((quotAndRem[1].signum() != 0) && (exp > 0)) {
                // Log10(r) + ((s2 - s1) - exp) > mc.precision ?
                compRemDiv = (new BigDecimal(quotAndRem[1])).precision() + exp
                        - (long) divisor.precision();
                if (compRemDiv == 0) {
                    // To calculate:  (r * 10^exp2) / u2
                    quotAndRem[1] = quotAndRem[1].multiply(powerOf10(exp))
                            .divide(divisor.unscaledValue);
                    compRemDiv = Math.abs(quotAndRem[1].signum());
                }
                if (compRemDiv > 0) {
                    // The quotient won't fit in 'mc.precision()' digits
                    throw new ArithmeticException("Division impossible");
                }
            }
        }
        // Fast return if the quotient is zero
        if (quotAndRem[0].signum() == 0) {
            return zeroScaledBy(diffScale);
        }
        BigInteger strippedBI = quotAndRem[0];
        BigDecimal integralValue = new BigDecimal(quotAndRem[0]);
        long resultPrecision = integralValue.precision();
        int i = 1;
        // To strip trailing zeros until the specified precision is reached
        while (!strippedBI.testBit(0)) {
            quotAndRem = strippedBI.divideAndRemainder(TEN_POW[i]);
            if ((quotAndRem[1].signum() == 0)
                    && ((resultPrecision - i >= mcPrecision) || (newScale - i >= diffScale))) {
                resultPrecision -= i;
                newScale -= i;
                if (i < lastPow) {
                    i++;
                }
                strippedBI = quotAndRem[0];
            } else {
                if (i == 1) {
                    break;
                }
                i = 1;
            }
        }
        // To check if the result fit in 'mc.precision()' digits
        if (resultPrecision > mcPrecision) {
            throw new ArithmeticException("Division impossible");
        } else {
            integralValue.unscaledValue = strippedBI;
            integralValue.scale = toIntScale(newScale);
            return integralValue;
        }
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal remainder(BigDecimal divisor) {
        return divideAndRemainder(divisor)[1];
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal remainder(BigDecimal divisor, MathContext mc) {
        return divideAndRemainder(divisor, mc)[1];
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal[] divideAndRemainder(BigDecimal divisor) {
        BigDecimal quotAndRem[] = new BigDecimal[2];

        quotAndRem[0] = this.divideToIntegralValue(divisor);
        quotAndRem[1] = this.subtract(quotAndRem[0].multiply(divisor));
        return quotAndRem;
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal[] divideAndRemainder(BigDecimal divisor, MathContext mc) {
        BigDecimal quotAndRem[] = new BigDecimal[2];

        quotAndRem[0] = this.divideToIntegralValue(divisor, mc);
        quotAndRem[1] = this.subtract(quotAndRem[0].multiply(divisor));
        return quotAndRem;
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal pow(int n) {
        if (n == 0) {
            return ONE;
        }
        if ((n < 0) || (n > 999999999)) {
            throw new ArithmeticException("Invalid operation");
        }
        long newScale = scale * (long) n;
        // Let be: this = [u,s]   so:  this^n = [u^n, s*n]
        return ((signum() == 0) ? zeroScaledBy(newScale) : new BigDecimal(
                unscaledValue.pow(n), toIntScale(newScale)));
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal pow(int n, MathContext mc) {
        // The ANSI standard X3.274-1996 algorithm
        int m = Math.abs(n);
        int mcPrecision = mc.getPrecision();
        int elength = (int) Math.log10(m) + 1; // decimal digits in 'n' 
        int oneBitMask; // mask of bits
        BigDecimal accum; // the single accumulator
        MathContext newPrecision = mc; // MathContext by default

        // In particular cases, it reduces the problem to call the other 'pow()'
        if ((n == 0) || ((signum() == 0) && (n > 0))) {
            return pow(n);
        }
        if ((m > 999999999) || ((mcPrecision == 0) && (n < 0))
                || ((mcPrecision > 0) && (elength > mcPrecision))) {
            throw new ArithmeticException("Invalid Operation");
        }
        if (mcPrecision > 0) {
            newPrecision = new MathContext(mcPrecision + elength + 1, mc
                    .getRoundingMode());
        }
        // The result is calculated as if 'n' were positive        
        accum = round(newPrecision);
        oneBitMask = Integer.highestOneBit(m) >> 1;

        while (oneBitMask > 0) {
            accum = accum.multiply(accum, newPrecision);
            if ((m & oneBitMask) == oneBitMask) {
                accum = accum.multiply(this, newPrecision);
            }
            oneBitMask >>= 1;
        }
        // If 'n' is negative, the value is divided into 'ONE'
        if (n < 0) {
            accum = ONE.divide(accum, newPrecision);
        }
        // The final value is rounded to the destination precision
        accum.inplaceRound(mc);
        return accum;
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal abs() {
        return ((signum() < 0) ? negate() : this);
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal abs(MathContext mc) {
        return round(mc).abs();
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal negate() {
        return new BigDecimal(unscaledValue.negate(), scale);
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal negate(MathContext mc) {
        return round(mc).negate();
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal plus() {
        return this;
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal plus(MathContext mc) {
        return round(mc);
    }

    /** @ar.org.fitc.spec_ref */
    public int signum() {
        return unscaledValue.signum();
    }

    /** @ar.org.fitc.spec_ref */
    public int scale() {
        return scale;
    }

    /** @ar.org.fitc.spec_ref */
    public int precision() {
        // Checking if the precision already was calculated
        if (precision > 0) {
            return precision;
        }
        int bitLength = unscaledValue.bitLength();
        int decimalDigits = 1; // the precision to be calculated
        double doubleUnsc = 1; // unscaledValue in 'double'

        if (bitLength < 1024) {
            // To calculate the precision for small numbers
            if (bitLength >= 64) {
                doubleUnsc = unscaledValue.doubleValue();
            } else if (bitLength >= 32) {
                doubleUnsc = unscaledValue.longValue();
            } else if (bitLength >= 1) {
                doubleUnsc = unscaledValue.intValue();
            }
            decimalDigits += Math.log10(Math.abs(doubleUnsc));
        } else {// (bitLength >= 1024)
            /* To calculate the precision for large numbers
             * Note that: 2 ^(bitlength() - 1) <= unscaledValue < 10 ^(precision()) */
            decimalDigits += (bitLength - 1) * LOG10_2;
            // If after division the number isn't zero, exists an aditional digit
            if (unscaledValue.divide(powerOf10(decimalDigits)).signum() != 0) {
                decimalDigits++;
            }
        }
        precision = decimalDigits;
        return precision;
    }

    /** @ar.org.fitc.spec_ref */
    public BigInteger unscaledValue() {
        return unscaledValue;
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal round(MathContext mc) {
        BigDecimal thisBD = new BigDecimal(unscaledValue, scale);

        thisBD.inplaceRound(mc);
        return thisBD;
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal setScale(int newScale, RoundingMode roundingMode) {
        long diffScale = newScale - (long) scale;

        if (roundingMode == null) {
            throw new NullPointerException();
        }
        // Let be:  'this' = [u,s]        
        return ((diffScale >= 0)
        // return  [u * 10^(s2 - s), newScale]
                ? new BigDecimal(unscaledValue.multiply(powerOf10(diffScale)),
                        newScale)
                // return  [u,s] / [1,newScale]  with the apropiate scale and rounding
                : this.divide(BigDecimal.ONE, newScale, roundingMode));
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal setScale(int newScale, int roundingMode) {
        return setScale(newScale, RoundingMode.valueOf(roundingMode));
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal setScale(int newScale) {
        return setScale(newScale, RoundingMode.UNNECESSARY);
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal movePointLeft(int n) {
        long newScale = scale + (long) n;

        if (signum() == 0) {
            return zeroScaledBy(Math.max(newScale, 0));
        } else {
            /* When:  'n'== Integer.MIN_VALUE  isn't possible to call to movePointRight(-n)  
             * since  -Integer.MIN_VALUE == Integer.MIN_VALUE */
            return ((newScale >= 0) ? new BigDecimal(unscaledValue,
                    toIntScale(newScale)) : new BigDecimal(unscaledValue
                    .multiply(powerOf10(-newScale)), 0));
        }
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal movePointRight(int n) {
        long newScale = scale - (long) n;

        if (signum() == 0) {
            return zeroScaledBy(Math.max(newScale, 0));
        } else {
            // Here we have the same observation that in the movePointLeft(int) method 
            return ((newScale >= 0) ? new BigDecimal(unscaledValue,
                    toIntScale(newScale)) : new BigDecimal(unscaledValue
                    .multiply(powerOf10(-newScale)), 0));
        }
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal scaleByPowerOfTen(int n) {
        long newScale = scale - (long) n;

        return ((signum() == 0) ? zeroScaledBy(newScale) : new BigDecimal(
                unscaledValue, toIntScale(newScale)));
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal stripTrailingZeros() {
        int i = 1; // 1 <= i <= 18
        int lastPow = TEN_POW.length - 1;
        long newScale = scale;
        BigInteger strippedBI = unscaledValue;
        BigInteger[] quotAndRem;

        if (signum() == 0) {
            return this;
        }
        // while the number is even...
        while (!strippedBI.testBit(0)) {
            // To divide by 10^i
            quotAndRem = strippedBI.divideAndRemainder(TEN_POW[i]);
            // To look the remainder
            if (quotAndRem[1].signum() == 0) {
                // To adjust the scale
                newScale -= i;
                if (i < lastPow) {
                    // To set to the next power
                    i++;
                }
                strippedBI = quotAndRem[0];
            } else {
                if (i == 1) {
                    // 'this' has no more trailing zeros
                    break;
                }
                // To set to the smallest power of ten
                i = 1;
            }
        }
        return new BigDecimal(strippedBI, toIntScale(newScale));
    }

    /** @ar.org.fitc.spec_ref */
    public int compareTo(BigDecimal val) {
        int thisSign = signum();

        if (thisSign > val.signum()) {
            return 1;
        } else if (thisSign < val.signum()) {
            return -1;
        } else {// thisSign == val.signum()
            int diffPrecision = this.aproxPrecision() - val.aproxPrecision();
            long diffScale = (long) this.scale - val.scale;

            if (diffPrecision > diffScale + 1) {
                return thisSign;
            } else if (diffPrecision < diffScale - 1) {
                return -thisSign;
            } else {// thisSign == val.signum()  and  diffPrecision is aprox. diffScale
                BigInteger thisUnscaled = this.unscaledValue;
                BigInteger valUnscaled = val.unscaledValue;
                // If any of both precision is bigger, append zeros to the shorter one
                if (diffScale < 0) {
                    thisUnscaled = thisUnscaled.multiply(powerOf10(-diffScale));
                } else if (diffScale > 0) {
                    valUnscaled = valUnscaled.multiply(powerOf10(diffScale));
                }
                return thisUnscaled.compareTo(valUnscaled);
            }
        }
    }

    /** @ar.org.fitc.spec_ref */
    @Override
    public boolean equals(Object x) {
        return ((x instanceof BigDecimal) && (((BigDecimal) x).scale == scale) && (((BigDecimal) x).unscaledValue
                .equals(unscaledValue)));
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal min(BigDecimal val) {
        return ((compareTo(val) <= 0) ? this : val);
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal max(BigDecimal val) {
        return ((compareTo(val) >= 0) ? this : val);
    }

    /** @ar.org.fitc.spec_ref */
    @Override
    public int hashCode() {
        /* Take the 24 trailing bits of BigInteger hashcode
         * and the 8 trailing bits of scale. */
        return ((unscaledValue.hashCode() << 24) | (0xFF & scale));
    }

    /** @ar.org.fitc.spec_ref */
    @Override
    public String toString() {
        if (toStringImage != null) {
            return toStringImage;
        }
        String intString = unscaledValue.toString();
        if (scale == 0) {
            return intString;
        }
        int begin = (unscaledValue.signum() < 0) ? 2 : 1;
        int end = intString.length();
        long exponent = -(long) scale + end - begin;
        StringBuffer result = new StringBuffer();

        result.append(intString);
        if ((scale > 0) && (exponent >= -6)) {
            if (exponent >= 0) {
                result.insert(end - scale, '.');
            } else {
                result.insert(begin - 1, "0.");
                result.insert(begin + 1, CH_ZEROS, 0, -(int) exponent - 1);
            }
        } else {
            if (end - begin >= 1) {
                result.insert(begin, '.');
                end++;
            }
            result.insert(end, 'E');
            if (exponent > 0) {
                result.insert(++end, '+');
            }
            result.insert(++end, Long.toString(exponent));
        }
        toStringImage = result.toString();
        return toStringImage;
    }

    /** @ar.org.fitc.spec_ref */
    public String toEngineeringString() {
        String intString = unscaledValue.toString();
        if (scale == 0) {
            return intString;
        }
        int begin = (unscaledValue.signum() < 0) ? 2 : 1;
        int end = intString.length();
        long exponent = -(long) scale + end - begin;
        StringBuffer result = new StringBuffer(intString);

        if ((scale > 0) && (exponent >= -6)) {
            if (exponent >= 0) {
                result.insert(end - scale, '.');
            } else {
                result.insert(begin - 1, "0.");
                result.insert(begin + 1, CH_ZEROS, 0, -(int) exponent - 1);
            }
        } else {
            int delta = end - begin;
            int rem = (int) (exponent % 3);

            if (rem != 0) {
                // adjust exponent so it is a multiple of three
                if (unscaledValue.signum() == 0) {
                    // zero value
                    rem = (rem < 0) ? -rem : 3 - rem;
                    exponent += rem;
                } else {
                    // nonzero value
                    rem = (rem < 0) ? rem + 3 : rem;
                    exponent -= rem;
                    begin += rem;
                }
                if (delta < 3) {
                    for (int i = rem - delta; i > 0; i--) {
                        result.insert(end++, '0');
                    }
                    System.out.println("1");
                }
            }
            if (end - begin >= 1) {
                result.insert(begin, '.');
                end++;
            }
            if (exponent != 0) {
                result.insert(end, 'E');
                if (exponent > 0) {
                    result.insert(++end, '+');
                }
                result.insert(++end, Long.toString(exponent));
            }
        }
        return result.toString();
    }

    /** @ar.org.fitc.spec_ref */
    public String toPlainString() {
        String intStr = unscaledValue.toString();
        if ((scale == 0) || ((signum() == 0) && (scale < 0))) {
            return intStr;
        }
        int begin = (signum() < 0) ? 1 : 0;
        int delta = scale;
        // We take space for all digits, plus a possible decimal point, plus 'scale'
        StringBuffer result = new StringBuffer(intStr.length() + 1
                + Math.abs(scale));

        if (begin == 1) {
            // If the number is negative, we insert a '-' character at front 
            result.append('-');
        }
        if (scale > 0) {
            delta -= (intStr.length() - begin);
            if (delta >= 0) {
                result.append("0.");
                // To append zeros after the decimal point
                for (; delta > CH_ZEROS.length; delta -= CH_ZEROS.length) {
                    result.append(CH_ZEROS);
                }
                result.append(CH_ZEROS, 0, delta);
                result.append(intStr.substring(begin));
            } else {
                delta = begin - delta;
                result.append(intStr.substring(begin, delta));
                result.append('.');
                result.append(intStr.substring(delta));
            }
        } else {// (scale <= 0)
            result.append(intStr.substring(begin));
            // To append trailing zeros
            for (; delta < -CH_ZEROS.length; delta += CH_ZEROS.length) {
                result.append(CH_ZEROS);
            }
            result.append(CH_ZEROS, 0, -delta);
        }
        return result.toString();
    }

    /** @ar.org.fitc.spec_ref */
    public BigInteger toBigInteger() {
        if ((scale == 0) || (signum() == 0)) {
            return unscaledValue;
        } else if (scale < 0) {
            return unscaledValue.multiply(powerOf10(-(long) scale));
        } else {// (scale > 0)
            return unscaledValue.divide(powerOf10(scale));
        }
    }

    /** @ar.org.fitc.spec_ref */
    public BigInteger toBigIntegerExact() {
        if ((scale == 0) || (signum() == 0)) {
            return unscaledValue;
        } else if (scale < 0) {
            return unscaledValue.multiply(powerOf10(-(long) scale));
        } else {// (scale > 0)
            BigInteger[] integerAndFraction;
            // An optimization before do a heavy division
            if ((scale > aproxPrecision())
                    || (scale > unscaledValue.getLowestSetBit())) {
                throw new ArithmeticException("Rounding necessary");
            }
            integerAndFraction = unscaledValue
                    .divideAndRemainder(powerOf10(scale));
            if (integerAndFraction[1].signum() != 0) {
                // It exists a non-zero fractional part 
                throw new ArithmeticException("Rounding necessary");
            } else {
                return integerAndFraction[0];
            }
        }
    }

    /** @ar.org.fitc.spec_ref */
    @Override
    public long longValue() {
        /* If scale <= -64 there are at least 64 trailing bits zero in 10^(-scale).
         * If the scale is positive and very large the long value could be zero. */
        return ((scale <= -64) || (scale > aproxPrecision()) ? 0L
                : toBigInteger().longValue());
    }

    /** @ar.org.fitc.spec_ref */
    public long longValueExact() {
        return valueExact(64);
    }

    /** @ar.org.fitc.spec_ref */
    @Override
    public int intValue() {
        /* If scale <= -32 there are at least 32 trailing bits zero in 10^(-scale).
         * If the scale is positive and very large the long value could be zero. */
        return ((scale <= -32) || (scale > aproxPrecision()) ? 0
                : toBigInteger().intValue());
    }

    /** @ar.org.fitc.spec_ref */
    public int intValueExact() {
        return (int) valueExact(32);
    }

    /** @ar.org.fitc.spec_ref */
    public short shortValueExact() {
        return (short) valueExact(16);
    }

    /** @ar.org.fitc.spec_ref */
    public byte byteValueExact() {
        return (byte) valueExact(8);
    }

    /** @ar.org.fitc.spec_ref */
    @Override
    public float floatValue() {
        /* A similar code like in doubleValue() could be repeated here,
         * but this simple implementation is quite efficient. */
        float floatResult = signum();
        long powerOfTwo = unscaledValue.bitLength() - (long) (scale / LOG10_2);
        if ((powerOfTwo < -149) || (floatResult == 0.0f)) {
            // Cases which 'this' is very small
            floatResult *= 0.0f;
        } else if (powerOfTwo > 129) {
            // Cases which 'this' is very large
            floatResult *= Float.POSITIVE_INFINITY;
        } else {
            floatResult = (float) doubleValue();
        }
        return floatResult;
    }

    /** @ar.org.fitc.spec_ref */
    @Override
    public double doubleValue() {
        int sign = signum();
        int exponent = 1076; // bias + 53
        int lowestSetBit;
        int discardedSize;
        long powerOfTwo = unscaledValue.bitLength() - (long) (scale / LOG10_2);
        long bits; // IEEE-754 Standard
        long tempBits; // for temporal calculations     
        BigInteger mantisa;

        if ((powerOfTwo < -1074) || (sign == 0)) {
            // Cases which 'this' is very small            
            return (sign * 0.0d);
        } else if (powerOfTwo > 1025) {
            // Cases which 'this' is very large            
            return (sign * Double.POSITIVE_INFINITY);
        }
        mantisa = unscaledValue.abs();
        // Let be:  this = [u,s], with s > 0
        if (scale <= 0) {
            // mantisa = abs(u) * 10^s
            mantisa = mantisa.multiply(powerOf10(-scale));
        } else {// (scale > 0)
            BigInteger quotAndRem[];
            BigInteger powerOfTen = powerOf10(scale);
            int k = 100 - (int) powerOfTwo;
            int compRem;

            if (k > 0) {
                /* Computing (mantisa * 2^k) , where 'k' is a enough big
                 * power of '2' to can divide by 10^s */
                mantisa = mantisa.shiftLeft(k);
                exponent -= k;
            }
            // Computing (mantisa * 2^k) / 10^s
            quotAndRem = mantisa.divideAndRemainder(powerOfTen);
            // To check if the fractional part >= 0.5
            compRem = quotAndRem[1].shiftLeft(1).compareTo(powerOfTen);
            // To add two rounded bits at end of mantisa
            mantisa = quotAndRem[0].shiftLeft(2).add(
                    BigInteger.valueOf((compRem * (compRem + 3)) / 2 + 1));
            exponent -= 2;
        }
        lowestSetBit = mantisa.getLowestSetBit();
        discardedSize = mantisa.bitLength() - 54;
        if (discardedSize > 0) {// (n > 54)
            // mantisa = (abs(u) * 10^s) >> (n - 54)
            bits = mantisa.shiftRight(discardedSize).longValue();
            tempBits = bits;
            // #bits = 54, to check if the discarded fraction produces a carry             
            if ((((bits & 1) == 1) && (lowestSetBit < discardedSize))
                    || ((bits & 3) == 3)) {
                bits += 2;
            }
        } else {// (n <= 54)
            // mantisa = (abs(u) * 10^s) << (54 - n)                
            bits = mantisa.longValue() << -discardedSize;
            tempBits = bits;
            // #bits = 54, to check if the discarded fraction produces a carry:
            if ((bits & 3) == 3) {
                bits += 2;
            }
        }
        // Testing bit 54 to check if the carry creates a new binary digit
        if ((bits & 0x40000000000000L) == 0) {
            // To drop the last bit of mantisa (first discarded)
            bits >>= 1;
            // exponent = 2^(s-n+53+bias)
            exponent += discardedSize;
        } else {// #bits = 54
            bits >>= 2;
            exponent += discardedSize + 1;
        }
        // To test if the 53-bits number fits in 'double'            
        if (exponent > 2046) {// (exponent - bias > 1023)
            return (sign * Double.POSITIVE_INFINITY);
        } else if (exponent <= 0) {// (exponent - bias <= -1023)
            // Denormalized numbers (having exponent == 0)
            if (exponent < -53) {// exponent - bias < -1076
                return (sign * 0.0d);
            } else {// -1076 <= exponent - bias <= -1023 
                // To discard '- exponent + 1' bits
                bits = tempBits >> 1;
                tempBits = bits & (-1L >>> (63 + exponent));
                bits >>= (-exponent);
                // To test if after discard bits, a new carry is generated
                if (((bits & 3) == 3)
                        || (((bits & 1) == 1) && (tempBits != 0) && (lowestSetBit < discardedSize))) {
                    bits += 1;
                }
                exponent = 0;
                bits >>= 1;
            }
        }
        // Construct the 64 double bits: [sign(1), exponent(11), mantisa(52)]
        bits = (sign & 0x8000000000000000L) | ((long) exponent << 52)
                | (bits & 0xFFFFFFFFFFFFFL);
        return Double.longBitsToDouble(bits);
    }

    /** @ar.org.fitc.spec_ref */
    public BigDecimal ulp() {
        return new BigDecimal(BigInteger.ONE, scale);
    }

    /* Private Methods */

    /**
     * It does all rounding work of the public method <code>round(MathContext)</code>, 
     * performing an inplace rounding without creating a new object.
     * @param mc the <code>MathContext</code> for perform the rounding.
     * @see #round(MathContext).
     */
    private void inplaceRound(MathContext mc) {
        int mcPrecision = mc.getPrecision();
        int discardedPrecision = precision() - mcPrecision;
        // If no rounding is necessary it returns inmediatly
        if ((discardedPrecision <= 0) || (mcPrecision == 0)) {
            return;
        }
        // When the number is small perform an efficient rounding
        if (unscaledValue.bitLength() < 64) {
            smallRound(mc, discardedPrecision);
            return;
        }
        // Getting the interger part and the discarded fraction
        BigInteger sizeOfFraction = powerOf10(discardedPrecision);
        BigInteger[] integerAndFraction = unscaledValue
                .divideAndRemainder(sizeOfFraction);
        long newScale = (long) scale - discardedPrecision;
        int compRem;
        BigDecimal tempBD;
        // If the discarded fraction is non-zero, perform rounding
        if (integerAndFraction[1].signum() != 0) {
            // To check if the discarded fraction >= 0.5
            compRem = (integerAndFraction[1].abs().shiftLeft(1)
                    .compareTo(sizeOfFraction));
            // To look if there is a carry
            compRem = roundingBehavior(
                    integerAndFraction[0].testBit(0) ? 1 : 0,
                    integerAndFraction[1].signum() * (5 + compRem), mc
                            .getRoundingMode());
            if (compRem != 0) {
                integerAndFraction[0] = integerAndFraction[0].add(BigInteger
                        .valueOf(compRem));
            }
            tempBD = new BigDecimal(integerAndFraction[0]);
            // If after to add the increment the precision changed, we normalize the size
            if (tempBD.precision() > mcPrecision) {
                integerAndFraction[0] = integerAndFraction[0]
                        .divide(BigInteger.TEN);
                newScale--;
            }
        }
        // To update all inernal fields
        scale = toIntScale(newScale);
        unscaledValue = integerAndFraction[0];
        precision = mcPrecision;
    }

    /**
     * This method implements an efficient rounding for numbers which unscaled 
     * value fits in the type <code>long</code>.
     * @param mc the context to use.
     * @param discardedPrecision the number of decimal digits that are discarded.
     * @see #round(MathContext).
     */
    private void smallRound(MathContext mc, int discardedPrecision) {
        long sizeOfFraction = TEN_POW[discardedPrecision].longValue();
        long newScale = (long) scale - discardedPrecision;
        long unscaledVal = unscaledValue.longValue();
        // Getting the interger part and the discarded fraction
        long integer = unscaledVal / sizeOfFraction;
        long fraction = unscaledVal % sizeOfFraction;
        int compRem;
        // If the discarded fraction is non-zero perform rounding
        if (fraction != 0) {
            // To check if the discarded fraction >= 0.5
            compRem = ((new Long(Math.abs(fraction) << 1))
                    .compareTo(sizeOfFraction));
            // To look if there is a carry
            integer += roundingBehavior(((int) integer) & 1, Long
                    .signum(fraction)
                    * (5 + compRem), mc.getRoundingMode());
            // If after to add the increment the precision changed, we normalize the size
            if (Math.log10(Math.abs(integer)) >= mc.getPrecision()) {
                integer /= 10;
                newScale--;
            }
        }
        // To update all inernal fields
        scale = toIntScale(newScale);
        unscaledValue = BigInteger.valueOf(integer);
        precision = mc.getPrecision();
    }

    /**
     * Return an increment that can be -1,0 or 1, depending of <code>roundingMode</code>.
     * @param parityBit can be 0 or 1, it's only used in the case <code>HALF_EVEN</code>.  
     * @param fraction the mantisa to be analized.
     * @param roundingMode the type of rounding.
     * @return the carry propagated after rounding.
     */
    private static int roundingBehavior(int parityBit, int fraction,
            RoundingMode roundingMode) {
        int increment = 0; // the carry after rounding

        switch (roundingMode) {
            case UNNECESSARY:
                if (fraction != 0) {
                    throw new ArithmeticException("Rounding necessary");
                }
                break;
            case UP:
                increment = Integer.signum(fraction);
                break;
            case DOWN:
                break;
            case CEILING:
                increment = Math.max(Integer.signum(fraction), 0);
                break;
            case FLOOR:
                increment = Math.min(Integer.signum(fraction), 0);
                break;
            case HALF_UP:
                if (Math.abs(fraction) >= 5) {
                    increment = Integer.signum(fraction);
                }
                break;
            case HALF_DOWN:
                if (Math.abs(fraction) > 5) {
                    increment = Integer.signum(fraction);
                }
                break;
            case HALF_EVEN:
                if (Math.abs(fraction) + parityBit > 5) {
                    increment = Integer.signum(fraction);
                }
                break;
        }
        return increment;
    }

    /**
     * If <code>unscaledValue</code> has a fractional part throws an exception, 
     * otherwise it counts the number of bits of value and checks if it's out 
     * of the range of the primitive type. If the number fits in the primitive
     * type returns this number as <code>long</code>, otherwise throws an
     * exception. 
     * @param bitLengthOfType number of bits of the type whose value will be 
     *         calculated exactly.
     * @return the exact value of the integer part of <code>BigDecimal</code>
     *         when is possible.
     * @throws <code>ArithmeticException</code> when rounding is necessary or
     *      the number don't fit in the primitive type.        
     */
    private long valueExact(int bitLengthOfType) {
        BigInteger bigInteger = toBigIntegerExact();

        if (bigInteger.bitLength() < bitLengthOfType) {
            // It fits in the primitive type
            return bigInteger.longValue();
        } else {
            throw new ArithmeticException("Rounding necessary");
        }
    }

    /**
     * If the precion already was calculated it returns that value, otherwise
     * it calculates a very good aproximization efficiently . Note that this 
     * value will be <code>precision()</code> or <code>precision()-1</code>
     * in the worst case.
     * @return an aproximization of <code>precision()</code> value
     */
    private int aproxPrecision() {
        return ((precision > 0) ? precision
                : (int) ((unscaledValue.bitLength() - 1) * LOG10_2)) + 1;
    }

    /**
     * It calculates a power of ten, which exponent could be out of 32-bit range.
     * Note that internally this method will be used in the worst case with
     * an exponent equals to: <code>Integer.MAX_VALUE - Integer.MIN_VALUE</code>.
     * @param exp the exponent of power of ten, it must be positive.
     * @return a <code>BigInteger</code> with value <code>10^exp</code>.
     */
    private static BigInteger powerOf10(long exp) {
        // PRE: exp >= 0
        int intExp = (int) exp;
        // "SMALL POWERS"
        if (exp < TEN_POW.length) {
            // The largest power that fit in 'long' type
            return TEN_POW[intExp];
        } else if (exp <= 50) {
            // To calculate:    10^exp
            return BigInteger.TEN.pow(intExp);
        } else if (exp <= 1000) {
            // To calculate:    5^exp * 2^exp 
            return FIVE_POW[1].pow(intExp).shiftLeft(intExp);
        }
        // "LARGE POWERS"  
        /* To check if there is free memory to allocate a BigInteger
         * of the estimated size (measured in bytes) */
        long byteArraySize = 1 + (long) (exp / (8 * LOG10_2));

        if (byteArraySize > Runtime.getRuntime().freeMemory()) {
            throw new OutOfMemoryError("power of ten too big");
        }
        if (exp <= Integer.MAX_VALUE) {
            // To calculate:    5^exp * 2^exp
            return FIVE_POW[1].pow(intExp).shiftLeft(intExp);
        } else {/* "HUGE POWERS"
         * Probably this branch won't be executed  
         * since the power of ten is too big. */
            // To calculate:    5^exp
            BigInteger powerOfFive = FIVE_POW[1].pow(Integer.MAX_VALUE);
            BigInteger res = powerOfFive;
            long longExp = exp - Integer.MAX_VALUE;

            intExp = (int) (exp % Integer.MAX_VALUE);
            while (longExp > Integer.MAX_VALUE) {
                res = res.multiply(powerOfFive);
                longExp -= Integer.MAX_VALUE;
            }
            res = res.multiply(FIVE_POW[1].pow(intExp));
            // To calculate:    5^exp << exp             
            res = res.shiftLeft(Integer.MAX_VALUE);
            longExp = exp - Integer.MAX_VALUE;
            while (longExp > Integer.MAX_VALUE) {
                res = res.shiftLeft(Integer.MAX_VALUE);
                longExp -= Integer.MAX_VALUE;
            }
            res = res.shiftLeft(intExp);
            return res;
        }
    }

    /**
     * It tests if a scale of type <code>long</code> fits in 32 bits. 
     * It returns the same scale being casted to <code>int</code> type when 
     * is possible, otherwise throws an exception.
     * @param longScale a 64 bit scale.
     * @return a 32 bit scale when is possible.
     * @throws <code>ArithmeticException</code> when <code>scale</code> 
     *      doesn't fit in <code>int</code> type. 
     * @see #scale     
     */
    private static int toIntScale(long longScale) {
        if (longScale < Integer.MIN_VALUE) {
            throw new ArithmeticException("Overflow");
        } else if (longScale > Integer.MAX_VALUE) {
            throw new ArithmeticException("Underflow");
        } else {
            return (int) longScale;
        }
    }

    /**
     * It returns the value 0 with the most aproximated scale of type 
     * <code>int</code>. if <code>longScale > Integer.MAX_VALUE</code> 
     * the scale will be <code>Integer.MAX_VALUE</code>; if 
     * <code>longScale < Integer.MIN_VALUE</code> the scale will be
     * <code>Integer.MIN_VALUE</code>; otherwise <code>longScale</code> is 
     * casted to the type <code>int</code>. 
     * @param longScale the scale to which the value 0 will be scaled.
     * @return the value 0 scaled by the closer scale of type <code>int</code>.
     * @see #scale
     */
    private static BigDecimal zeroScaledBy(long longScale) {
        if (longScale >= 0) {
            if (longScale < ZERO_SCALED_BY.length) {
                return ZERO_SCALED_BY[(int) longScale];
            } else {
                return new BigDecimal(BigInteger.ZERO,
                        (longScale <= Integer.MAX_VALUE) ? (int) longScale
                                : Integer.MAX_VALUE);
            }
        } else {
            return new BigDecimal(BigInteger.ZERO,
                    (longScale >= Integer.MIN_VALUE) ? (int) longScale
                            : Integer.MIN_VALUE);
        }
    }

    /** @ar.org.fitc.spec_ref */
    private void readObject(ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        in.defaultReadObject();
        if (unscaledValue == null) {
            throw new StreamCorruptedException("null unscaled value");
        }
    }

}
