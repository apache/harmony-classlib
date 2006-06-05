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

package java.math;
/**
 * @author Daniel Fridlender
 * @author Matthias Gallé
 * @author Mariano Heredia
 * @author Miguel Vasquez
 * @ar.org.fitc.spec_ref 
 */
public class BigDecimal extends Number implements Comparable<BigDecimal> {
    
    /* Static Fields */
    
    static final long serialVersionUID = 1L;
    
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
    
    /**
     * The <tt>BigInteger</tt> value -1.
     */
    private static final BigInteger BI_MINUS_ONE = BigInteger.valueOf(-1);
    
    /**
     * The <tt>BigInteger</tt> value 5.
     */
    private static final BigInteger BI_FIVE = BigInteger.valueOf(5);

    /**
     * An array with all powers of ten that fit in <code>long</code> type.
     */
    private static final BigInteger TEN_POW[] = new BigInteger[19];
    
    /**
     * The double closer to <code>Log10(2)</code>.
     */
    private static final double LOG10_2 = 0.3010299956639812;
    
    /**
     * An array filled with '0' characters.
     */
    private static final char[] CH_ZEROS = new char[100];
    
    static {
        // To fill the 'CH_ZEROS' array with characters '0'.
        java.util.Arrays.fill(CH_ZEROS, '0');
        // To fill the 'TEN_POW' array with powers of ten.
        long tenPow = 1;
        for (int i = 0; i <= 18; i++) {
            TEN_POW[i] = BigInteger.valueOf(tenPow);
            tenPow *= 10;
        }
    }

    /**
     * The arbitrary precision integer (unscaled value) in the internal
     * representation of <code>BigDecimal</code>.
     */
    private BigInteger unscaledValue;
    
    /**
     * The 32-bit integer scale in the internal representation of <code>
     * BigDecimal</code>.
     */
    private int scale;
    
    /**
     * Represent the number of decimal digits of the unscaled value. This 
     * precision is calculated the first time, and used in the followings 
     * calls of <code>precision()</code> method. Note that some call to 
     * the private <code>inplaceRound()</code> method could update this field.
     *
     * @see #precision()
     * @see #inplaceRound(MathContext)
     */
    private int precision = 0;
    
    
    /* Constructors */
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal(char[] in, int offset, int len) {
        if (in == null) {
            throw new NullPointerException();
        }
        int begin = offset; 			// first index to be copied
        int last = offset + (len - 1);  // last index to be copied
        String scaleString = null; 		// buffer for scale
        StringBuffer unscaledBuffer; 	// buffer for unscaled value
        long newScale = 0; 				// the new scale
        
        if ((offset < 0) || (len <= 0) || (last >= in.length) || (last < 0)) {
            throw new NumberFormatException();
        }
        unscaledBuffer = new StringBuffer(len);
        // To skip a possible '+' symbol
        if ((offset <= last) && (in[offset] == '+')) {
            offset++;
            begin++;
        }
        // Acumulating all digits until a possible decimal point
        for (; (offset <= last) && (in[offset] != '.')
            && (in[offset] != 'e') && (in[offset] != 'E'); offset++);
        unscaledBuffer.append(in, begin, offset - begin);
        // A decimal point was found
        if ((offset <= last) && (in[offset] == '.')) {
            offset++;
            // Acumulating all digits until a possible exponent
            begin = offset;
            for (; (offset <= last) && (in[offset] != 'e')
                && (in[offset] != 'E'); offset++) {
                newScale++;
            }
            unscaledBuffer.append(in, begin, offset - begin);
        }
        // An exponent was found
        if ((offset <= last) && ((in[offset] == 'e') || (in[offset] == 'E'))) {
            offset++;
            // Checking for a posible sign of scale
            begin = ((offset <= last) && (in[offset] == '+')) ? offset + 1 : offset;
            // Acumulating all reminaining digits
            scaleString = String.valueOf(in, begin, last + 1 - begin);
        }
        // Checking if the scale is defined
        if (scaleString != null) {
            newScale -= Integer.parseInt(scaleString);
            if ((newScale < Integer.MIN_VALUE)
                    || (newScale > Integer.MAX_VALUE)) {
                throw new NumberFormatException("Scale out of range");
            }
        }
        // Parsing the unscaled value
        unscaledValue = new BigInteger(unscaledBuffer.toString());
        scale = (int)newScale;
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal(char[] in, int offset, int len, MathContext mc) {
        this(in, offset, len);
        inplaceRound(mc);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal(char[] in) {
        this(in, 0, in.length);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal(char[] in, MathContext mc) {
        this(in, 0, in.length);
        inplaceRound(mc);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal(String val) {
        this(val.toCharArray(), 0, val.length());
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal(String val, MathContext mc) {
        this(val.toCharArray(), 0, val.length());
        inplaceRound(mc);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal(double val) {
        if (Double.isInfinite(val) || Double.isNaN(val)) {
            throw new NumberFormatException("Infinite or NaN");
        }
        int sizeOfShift;
        long mantisa;
        long bits = Double.doubleToLongBits(val); // IEEE-754
        long sign = ((bits >> 63) == 0) ? 1 : -1;
        // Extracting the exponent, note that the bias is 1023
        scale = 1075 - (int)((bits >> 52) & 0x7FFL);  
        // Extracting the 52 bits of the mantisa.
        mantisa = (scale == 1075) ? (bits & 0xFFFFFFFFFFFFFL) << 1
                                  : (bits & 0xFFFFFFFFFFFFFL) | 0x10000000000000L;
        // To simplify all factors '2' in the mantisa 
        if (scale > 0) {
            sizeOfShift = Math.min(scale, Long.numberOfTrailingZeros(mantisa));
            mantisa >>>= sizeOfShift;
            scale -= sizeOfShift;
        }
        // Calculating the new unscaled value and the new scale
        unscaledValue = BigInteger.valueOf(sign * mantisa);
        if (scale < 0) {
            unscaledValue = unscaledValue.shiftLeft(-scale);
            scale = 0;
        } else if (scale > 0) {
            unscaledValue = unscaledValue.multiply(BI_FIVE.pow(scale));
        }
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal(double val, MathContext mc) {
        this(val);
        inplaceRound(mc);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal(BigInteger val) {
        this(val, 0);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal(BigInteger val, MathContext mc) {
        this(val);
        inplaceRound(mc);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal(BigInteger unscaledVal, int scale) {
        if (unscaledVal == null) {
            throw new NullPointerException();
        }
        unscaledValue = unscaledVal;
        this.scale = scale;
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal(BigInteger unscaledVal, int scale, MathContext mc) {
        this(unscaledVal, scale);
        inplaceRound(mc);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal(int val) {
        this((long)val);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal(int val, MathContext mc) {
        this((long)val);
        inplaceRound(mc);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal(long val) {
        unscaledValue = BigInteger.valueOf(val);
        scale = 0;
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal(long val, MathContext mc) {
        this(val);
        inplaceRound(mc);
    }
    
    /* Public Methods */
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public static BigDecimal valueOf(long unscaledVal, int scale) {
        // It isn't really a "static factory method"
        return new BigDecimal(BigInteger.valueOf(unscaledVal), scale);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public static BigDecimal valueOf(long val) {
        // It isn't really a "static factory method"
        return valueOf(val, 0);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public static BigDecimal valueOf(double val) {
        if (Double.isInfinite(val) || Double.isNaN(val)) {
            throw new NumberFormatException("Infinity or NaN");
        } else {
            return new BigDecimal(Double.toString(val));
        }
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal add(BigDecimal augend) {
        long diffScale = (long)this.scale - augend.scale;
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
            return new BigDecimal(this.unscaledValue.add(augend.unscaledValue), this.scale);
        } else if (diffScale > 0) {
                // case s1 > s2 : [(u1 + u2) * 10 ^ (s1 - s2) , s1]
            return new BigDecimal(this.unscaledValue.add(
                    augend.unscaledValue.multiply(powerOf10(diffScale))), this.scale);
        } else {// case s2 > s1 : [(u2 + u1) * 10 ^ (s2 - s1) , s2]
            return new BigDecimal(augend.unscaledValue.add(
                    this.unscaledValue.multiply(powerOf10(-diffScale))), augend.scale);
        }
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal add(BigDecimal augend, MathContext mc) {
        // Some operand is zero or the precision is infinity  
        if ((augend.signum() == 0) || (this.signum() == 0)
                || (mc.getPrecision() == 0)) {
            return add(augend).round(mc);
        }
        // Now:   this != 0   and   augend != 0
        BigDecimal larger;  // operand with the largest unscaled value
        BigDecimal smaller; // operand with the smallest unscaled value
        BigInteger tempBI;
        long diffScale = (long)this.scale - augend.scale;
        int largerSignum;
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
        // Cases where it isn't necessary to add two numbers with very different scales 
        largerSignum = larger.signum();
        if (largerSignum == smaller.signum()) {
            tempBI = larger.unscaledValue.multiply(BigInteger.TEN)
                                        .add(BigInteger.valueOf(largerSignum));
        } else {
            tempBI = larger.unscaledValue.subtract(BigInteger.valueOf(largerSignum));
            tempBI = tempBI.multiply(BigInteger.TEN).add(BigInteger.valueOf(largerSignum * 9));
        }
        // Rounding the improved adding 
        larger = new BigDecimal(tempBI, larger.scale + 1);
        return larger.round(mc);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal subtract(BigDecimal subtrahend) {
        long diffScale = (long)this.scale - subtrahend.scale;
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
            return new BigDecimal(this.unscaledValue.subtract(subtrahend.unscaledValue), this.scale);
        } else if (diffScale > 0) {
                // case s1 > s2 : [ u1 - u2 * 10 ^ (s1 - s2) , s1 ]
            return new BigDecimal(this.unscaledValue.subtract(
                    subtrahend.unscaledValue.multiply(powerOf10(diffScale))), this.scale);
        } else {// case s2 > s1 : [ u1 * 10 ^ (s2 - s1) - u2 , s2 ]
            return new BigDecimal(this.unscaledValue.multiply(powerOf10(-diffScale))
                    .subtract(subtrahend.unscaledValue), subtrahend.scale);
        }
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal subtract(BigDecimal subtrahend, MathContext mc) {
        // Some operand is zero or the precision is infinity  
        if ((subtrahend.signum() == 0) || (this.signum() == 0)
                || (mc.getPrecision() == 0)) {
            return subtract(subtrahend).round(mc);
        }
        long diffScale = subtrahend.scale - (long)this.scale;
        int thisSignum;
        BigDecimal leftOperand; // it will be only the left operand (this) 
        BigInteger tempBI;
        // Now:   this != 0   and   subtrahend != 0
        if (subtrahend.aproxPrecision() < diffScale - 1) {
            // Cases where it isn't necessary to subtract two numbers with very different scales
            if (mc.getPrecision() < this.aproxPrecision()) {
                thisSignum = this.signum();
                if (thisSignum != subtrahend.signum()) {
                    tempBI = this.unscaledValue.multiply(BigInteger.TEN)
                            .add(BigInteger.valueOf(thisSignum));
                } else {
                    tempBI = this.unscaledValue.subtract(BigInteger.valueOf(thisSignum));
                    tempBI = tempBI.multiply(BigInteger.TEN)
                            .add(BigInteger.valueOf(thisSignum * 9));
                }
                // Rounding the improved substracting
                leftOperand = new BigDecimal(tempBI, this.scale + 1);
                return leftOperand.round(mc);
            }
        }         
        // No optimization is done
        return subtract(subtrahend).round(mc);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal multiply(BigDecimal multiplicand) {
        long newScale = (long)this.scale + multiplicand.scale;
        
        if ((this.signum() == 0) || (multiplicand.signum() == 0)) {
            return zeroScaledBy(newScale);
        } else {
            /* Let be: this = [u1,s1] and multiplicand = [u2,s2] so:
             * this x multiplicand = [ s1 * s2 , s1 + s2 ] */
            return new BigDecimal(this.unscaledValue.multiply(
                    multiplicand.unscaledValue), toIntScale(newScale));
        }
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal multiply(BigDecimal multiplicand, MathContext mc) {
        BigDecimal result = multiply(multiplicand);
        
        result.inplaceRound(mc);
        return result;
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal divide(BigDecimal divisor, int scale, int roundingMode) {
        return divide(divisor, scale, RoundingMode.valueOf(roundingMode));
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal divide(BigDecimal divisor, int scale, RoundingMode roundingMode) {
        if (divisor.signum() == 0) {
            throw new ArithmeticException("Division by zero");
        }
        // Let be: this = [u1,s1]  and  divisor = [u2,s2]
        long diffScale = ((long)this.scale - divisor.scale) - (long)scale;
        int sign = this.signum() * divisor.signum();      // sign of the result
        int compRem;                                      // 'compare to remainder'
        BigInteger quotAndRem[] = {this.unscaledValue};   // quotient and remainder
        BigInteger scaledDivisor = divisor.unscaledValue; // for scaling of 'u2'
        
        if (diffScale > 0) {
            // Multiply 'u2'  by:  10^((s1 - s2) - scale)
            scaledDivisor = scaledDivisor.multiply( powerOf10(diffScale) );
        } else if (diffScale < 0) {
            // Multiply 'u1'  by:  10^(scale - (s1 - s2))
            quotAndRem[0] = quotAndRem[0].multiply( powerOf10(-diffScale) );
        }
        quotAndRem = quotAndRem[0].divideAndRemainder( scaledDivisor );
        // If after division there is a remainder...
        if (quotAndRem[1].signum() != 0) {
            // Checking if:  remainder * 2 >= scaledDivisor 
            compRem = quotAndRem[1].abs().shiftLeft(1).compareTo( scaledDivisor.abs() );
            compRem = roundingBehavior( quotAndRem[0].testBit(0) ? 1 : 0, 
                                        sign * (5 + compRem), roundingMode);
            if (compRem != 0) {
                quotAndRem[0] = quotAndRem[0].add(BigInteger.valueOf(compRem)); 
            }
        }
        // Constructing the result with the appropriate unscaled value
        return new BigDecimal(quotAndRem[0], scale);
    }    
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal divide(BigDecimal divisor, int roundingMode) {
        return divide(divisor, scale, RoundingMode.valueOf(roundingMode));
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal divide(BigDecimal divisor, RoundingMode roundingMode) {
        return divide(divisor, scale, roundingMode);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal divide(BigDecimal divisor) {
        if (divisor.signum() == 0) {
            throw new ArithmeticException("Division by zero");
        }
        BigInteger p = this.unscaledValue;
        BigInteger q = divisor.unscaledValue;
        BigInteger gcd;  // greatest common divisor between 'p' and 'q'
        BigInteger quotAndRem[] = new BigInteger[2];
        long diffScale = (long)scale - divisor.scale;
        int k;           // number of "2" factors in 'q'
        int l;           // number of "5" factors in 'q'
        int newScale;    // the new scale for final quotient
        
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
        l = -1;
        quotAndRem[0] = q;
        do {
            q = quotAndRem[0];
            quotAndRem = q.divideAndRemainder(BI_FIVE);
            l++;
        } while (quotAndRem[1].signum() == 0);
        // If  abs(q) != 1  then the quotient is periodic
        if (!q.equals(BigInteger.ONE) && !q.equals(BI_MINUS_ONE)) {
            throw new ArithmeticException("Non-terminating decimal expansion;" 
                    + " no exact representable decimal result.");
        }
        // The sign of the is fixed and the quotient will be saved in 'p' and the sign is fixed
        if (q.signum() < 0) {
            p = p.negate();
        }
        // Checking if the new scale is out of range
        newScale = toIntScale(diffScale + Math.max(k, l));
        // k >= 0  and  l >= 0  implies that  k - l  is in the 32-bit range
        p = (k > l) ? p.multiply(BI_FIVE.pow(k - l))
                    : p.shiftLeft(l - k);
        return new BigDecimal(p, newScale);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal divide(BigDecimal divisor, MathContext mc) {
        if (mc == null) {
            throw new NullPointerException();
        }
        // In special cases it reduces the problem to call the other divison
        if ((this.signum() == 0) || (divisor.signum() == 0) 
                || (mc.getPrecision() == 0)) {
            return this.divide(divisor);
        }
        /* Calculating how many zeros must be append to 'dividend'
         * to obtain a  quotient with at least 'mc.precision()' digits */
        long traillingZeros = (long)mc.getPrecision() + 2L 
                            + (long)divisor.aproxPrecision() - (long)aproxPrecision();
        long preferredScale = (long)scale - divisor.scale;
        long newScale = preferredScale + 1; // scale of the final quotient
        int compRem;                        // to compare the remainder
        BigInteger strippedBI;              // for temporal results
        BigInteger quotAndRem[] = {unscaledValue};
        
        if (traillingZeros > 0) {
            // To append trailing zeros at end of dividend
            quotAndRem[0] = unscaledValue.multiply( powerOf10(traillingZeros) );
            newScale += traillingZeros;
        }
        quotAndRem = quotAndRem[0].divideAndRemainder( divisor.unscaledValue );
        // Calculating the exact quotient with at least 'mc.precision()' digits
        if (quotAndRem[1].signum() != 0) {
            // Checking if:   2 * remainder >= divisor ?
            compRem = quotAndRem[1].shiftLeft(1).compareTo( divisor.unscaledValue );
            // quot := quot * 10 + r;     with 'r' in {-6,-5,-4, 0,+4,+5,+6}
            quotAndRem[0] = quotAndRem[0].multiply(BigInteger.TEN)
                .add(BigInteger.valueOf(quotAndRem[0].signum() * (5 + compRem)));
        } else {
            do {// Striping trailing zeros, aproximating to the preferred scale
                strippedBI = quotAndRem[0];
                quotAndRem = strippedBI.divideAndRemainder(BigInteger.TEN);
                newScale--;
            } while ((newScale > preferredScale) && (quotAndRem[1].signum() == 0));
            quotAndRem[0] = strippedBI;
        }
        // To perform rounding
        return new BigDecimal(quotAndRem[0], toIntScale(newScale), mc);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal divideToIntegralValue(BigDecimal divisor) {
        if (divisor.signum() == 0) {
            throw new ArithmeticException("Division by zero");
        }
        long newScale = (long)this.scale - divisor.scale;
        long tempScale = 1;         // temporal scale
        BigInteger integralValue;   // the integer of result
        BigInteger powerOfTen;      // some power of ten
        BigInteger quotAndRem[] = {unscaledValue};
        
        if (divisor.aproxPrecision() + newScale > this.aproxPrecision() + 1L) {
            /* If the divisor's integer part is greater than this's integer part,
             * the result must be zero with the apropriate scale */
            integralValue = BigInteger.ZERO;
        } else if (newScale == 0) {
            integralValue = unscaledValue.divide( divisor.unscaledValue );
        } else if (newScale > 0) {
            powerOfTen = powerOf10(newScale);
            integralValue = unscaledValue.divide( divisor.unscaledValue.multiply(powerOfTen) );
            integralValue = integralValue.multiply(powerOfTen);
        } else {// (newScale < 0)
            powerOfTen = powerOf10(-newScale);
            integralValue = unscaledValue.multiply(powerOfTen).divide( divisor.unscaledValue );
            // To strip unnecessaries trailing zeros, aproximating to the preferred scale
            quotAndRem[0] = integralValue;
            do {
                integralValue = quotAndRem[0];
                quotAndRem = integralValue.divideAndRemainder(BigInteger.TEN);
                tempScale--;
            } while ((tempScale > newScale) && (quotAndRem[1].signum() == 0));
            newScale = tempScale;
        }
        return ((integralValue.signum() == 0) 
                ? zeroScaledBy(newScale)
                : new BigDecimal(integralValue, toIntScale(newScale)));
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal divideToIntegralValue(BigDecimal divisor, MathContext mc) {
        // The exact result is calculated
        BigDecimal integralValue = divideToIntegralValue(divisor);
        BigInteger quotAndRem[] = {integralValue.unscaledValue};
        int mcPrecision = mc.getPrecision();
        int resultPrecision;
        long newScale = integralValue.scale + 1L;
        
        // In these cases simply return the exact result
        if ((mcPrecision == 0) || (integralValue.signum() == 0)) {
            return integralValue;
        }
        // To strip trailing zeros until the wished precision is reached
        resultPrecision = integralValue.precision() + 1;
        do {
            integralValue.unscaledValue = quotAndRem[0];
            quotAndRem = integralValue.unscaledValue
                        .divideAndRemainder(BigInteger.TEN);
            newScale--;
            resultPrecision--;
        } while ((resultPrecision > mcPrecision) && (quotAndRem[1].signum() == 0));
        // Checking if the result fit with the specified precision 
        if (resultPrecision > mcPrecision) {
            throw new ArithmeticException("Division impossible");
        } else {
            integralValue.scale = toIntScale(newScale);
            return integralValue;
        }
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal remainder(BigDecimal divisor) {
        return this.subtract( this.divideToIntegralValue(divisor).multiply(divisor) );
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal remainder(BigDecimal divisor, MathContext mc) {
        return this.subtract( this.divideToIntegralValue(divisor, mc).multiply(divisor) );
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal[] divideAndRemainder(BigDecimal divisor) {
        BigDecimal quotAndRem[] = new BigDecimal[2];
        
        quotAndRem[0] = this.divideToIntegralValue(divisor);
        quotAndRem[1] = this.subtract( quotAndRem[0].multiply(divisor) );
        return quotAndRem;
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal[] divideAndRemainder(BigDecimal divisor, MathContext mc) {
        BigDecimal quotAndRem[] = new BigDecimal[2];
        
        quotAndRem[0] = this.divideToIntegralValue(divisor, mc);
        quotAndRem[1] = this.subtract( quotAndRem[0].multiply(divisor) );
        return quotAndRem;
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal pow(int n) {
        if (n == 0) {
            return ONE;
        }
        if ((n < 0) || (n > 999999999)) {
            throw new ArithmeticException("Invalid operation");
        }
        long newScale = scale * (long)n;
        // Let be: this = [u,s]   so: this^n = [u^n, s*n]
        return ((signum() == 0) 
                ? zeroScaledBy(newScale)
                : new BigDecimal(unscaledValue.pow(n), toIntScale(newScale)));
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal pow(int n, MathContext mc) {
        if (mc == null) {
            throw new NullPointerException();
        }
        int m = Math.abs(n); 
        int mcPrecision = mc.getPrecision();
        int elength = (int)Math.log10(m) + 1;   // decimal digits in 'n' 
        int oneBitMask;                         // mask of bits
        BigDecimal accum;                       // the single accumulator
        MathContext newPrecision = mc;          // MathContext by default
        
        // In particular cases, it reduces the problem to call the other 'pow()'
        if ((n == 0) || ((signum() == 0) && (n > 0))) {
            return pow(n);
        }       
        if ((m > 999999999) || ((mcPrecision == 0) && (n < 0)) 
                || ((mcPrecision > 0) && (elength > mcPrecision))) {
            throw new ArithmeticException("Invalid Operation");
        }
        if (mcPrecision > 0) {
            newPrecision = new MathContext( mcPrecision + elength + 1, 
                                            mc.getRoundingMode());
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
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal abs() {
        return ((signum() < 0) ? negate() : this);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal abs(MathContext mc) {
        return round(mc).abs();
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal negate() {
        return new BigDecimal(unscaledValue.negate(), scale);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal negate(MathContext mc) {
        return round(mc).negate();
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal plus() {
        return this;
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal plus(MathContext mc) {
        return round(mc);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public int signum() {
        return unscaledValue.signum();
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public int scale() {
        return scale;
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public int precision() {
        // Checking if the precision already was calculated
        if (precision > 0) {
            return precision;
        }
        int bitLength = unscaledValue.bitLength();
        int decimalDigits = 1;  // the precision to be calculated
        double doubleUnsc = 1;  // unscaledValue in 'double'

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
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigInteger unscaledValue() {
        return unscaledValue;
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal round(MathContext mc) {
        BigDecimal thisBD = new BigDecimal(unscaledValue, scale);
        
        thisBD.inplaceRound(mc);
        return thisBD;
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal setScale(int newScale, RoundingMode roundingMode) {
        if (roundingMode == null) {
            throw new NullPointerException();
        }
        long diffScale = newScale - (long)scale;
        // Let be:  'this' = [u,s]        
        return ((diffScale >= 0) 
                // return  [u * 10^(s2 - s), newScale]
                ? new BigDecimal(unscaledValue.multiply(powerOf10(diffScale)), newScale)
                // return  [u,s] / [1,newScale]  with the apropriate scale and rounding
                : this.divide(BigDecimal.ONE, newScale, roundingMode));
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal setScale(int newScale, int roundingMode) {
        return setScale(newScale, RoundingMode.valueOf(roundingMode));
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal setScale(int newScale) {
        return setScale(newScale, RoundingMode.UNNECESSARY);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal movePointLeft(int n) {
        long newScale = scale + (long)n;

        if (signum() == 0) {
            return zeroScaledBy(Math.max(newScale, 0));
        } else {
            /* When:  'n'== Integer.MIN_VALUE  isn't possible to call to movePointRight(-n)  
             * since  -Integer.MIN_VALUE == Integer.MIN_VALUE */
            return ((newScale >= 0)
                    ? new BigDecimal(unscaledValue, toIntScale(newScale))
                    : new BigDecimal(unscaledValue.multiply(powerOf10(-newScale)), 0));
        }
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal movePointRight(int n) {
        long newScale = scale - (long)n;
        
        if (signum() == 0) {
            return zeroScaledBy(Math.max(newScale, 0));
        } else {
            // Here we have the same observation that in the movePointLeft(int) method 
            return ((newScale >= 0)
                    ? new BigDecimal(unscaledValue, toIntScale(newScale))
                    : new BigDecimal(unscaledValue.multiply(powerOf10(-newScale)), 0));
        }
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal scaleByPowerOfTen(int n) {
        long newScale = scale - (long)n;
        
        return ((signum() == 0)
                ? zeroScaledBy(newScale)
                : new BigDecimal(unscaledValue, toIntScale(newScale)));
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal stripTrailingZeros() {
        int i = 1;      // 1 <= i <= 18
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
                if (i + 1 < TEN_POW.length) {
                    // To set to the next power
                    i++; 
                }
                strippedBI = quotAndRem[0];
            } else {
                if (i == 1) {
                    // 'this' has no more trailing zeros
                    break; 
                }
                // To set to the small power of ten
                i = 1; 
            }
        }
        return new BigDecimal(strippedBI, toIntScale(newScale));
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public int compareTo(BigDecimal val) {
        int thisSign = signum();
        
        if (thisSign > val.signum()) {
            return 1;
        } else if (thisSign < val.signum()) {
            return -1;
        } else {// thisSign == val.signum()
            int diffPrecision = this.aproxPrecision() - val.aproxPrecision();
            long diffScale = (long)this.scale - val.scale;
            
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
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public boolean equals(Object x) {
        return ((x instanceof BigDecimal)
                && (((BigDecimal)x).scale == scale)
                && (((BigDecimal)x).unscaledValue.equals(unscaledValue)));
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal min(BigDecimal val) {
        return ((compareTo(val) <= 0) ? this : val);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal max(BigDecimal val) {
        return ((compareTo(val) >= 0) ? this : val);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public int hashCode() {
         /* Take the 24 trailing bits of BigInteger hashcode
          * and the 8 trailing bits of scale. */
        return ((unscaledValue.hashCode() << 24) | (0xFF & scale));
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public String toString() {
        char unscaledCharArray[] = unscaledValue.toString().toCharArray();
        int begin = (signum() < 0) ? 1 : 0;
        int ulength = unscaledCharArray.length - begin;
        long adjustedExponent = -(long)scale + ulength - 1L;
        StringBuilder resultBuffer = new StringBuilder(ulength + 15);

        if (begin == 1) {
            // if the number is negative, we insert a '-' character at front 
            resultBuffer.append('-');
        }
        if ((scale >= 0) && (adjustedExponent >= -6)) {
            if (scale > 0) {
                if (adjustedExponent >= 0) {
                   /* Note that (scale > 0) and (ulength > 0) implies that
                    * (ulength - scale) always will be in the 32-bit range */
                    resultBuffer.append(unscaledCharArray, begin, ulength - scale);
                    resultBuffer.append('.');
                    resultBuffer.append(unscaledCharArray, begin + ulength - scale, scale);
                } else {// -6 <= adjustedExponent < 0
                    resultBuffer.append("0.");                        
                    // To append zeros
                    resultBuffer.append(CH_ZEROS, 0, scale - ulength);
                    resultBuffer.append(unscaledCharArray, begin, ulength);
                }
            } else {// (scale = 0)
                resultBuffer.append(unscaledCharArray, begin, ulength);
            }
        } else {// The exponential notation is used
            resultBuffer.append(unscaledCharArray[begin]);
            if (ulength > 1) {
                resultBuffer.append('.');
                resultBuffer.append(unscaledCharArray, begin + 1, ulength - 1);
            }
            if (adjustedExponent > 0) {
                resultBuffer.append("E+");
                resultBuffer.append(adjustedExponent);
            } else if (adjustedExponent < 0) {
                resultBuffer.append('E');
                resultBuffer.append(adjustedExponent);
            } // If (adjustedExponent == 0) no exponent is appended
        }
        return resultBuffer.toString();
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public String toEngineeringString() {
        char unscaledCharArray[] = unscaledValue.toString().toCharArray();
        int begin = (signum() < 0) ? 1 : 0;      
        int ulength = unscaledCharArray.length - begin;
        long adjustedExponent = -(long)scale + ulength - 1L;
        StringBuffer resultBuffer = new StringBuffer(ulength + 15);
        int mod3;   // {0,1,2} used to perform the Engineering Notation
        
        if (begin == 1) {
            // If the number is negative, we insert at front a minus character
            resultBuffer.append('-');
        }
        if ((scale >= 0) && (adjustedExponent >= -6)) {
            if (scale > 0) {
                if (adjustedExponent >= 0) {
                   /* Note that (scale > 0) and (ulength > 0) implies that
                    * (ulength - scale) always will be in the 32-bit range */
                    resultBuffer.append(unscaledCharArray, begin, ulength - scale);
                    resultBuffer.append('.');
                    resultBuffer.append(unscaledCharArray, begin + ulength - scale, scale);
                } else {// -6 <= adjustedExponent < 0
                    resultBuffer.append("0.");                        
                    // To append zeros
                    resultBuffer.append(CH_ZEROS, 0, scale - ulength);
                    resultBuffer.append(unscaledCharArray, begin, ulength);
                }
            } else {// (scale = 0)
                resultBuffer.append(unscaledCharArray, begin, ulength);
            }
        } else {// The exponential notation is used
            mod3 = ((int)(adjustedExponent % 3) + 3) % 3;
            
            if ((signum() != 0)) {
                adjustedExponent -= mod3;
                
                if (mod3 == 1) {
                    if (ulength >= 3) {
                        resultBuffer.append(unscaledCharArray, begin, 2);
                        resultBuffer.append('.');
                        resultBuffer.append(unscaledCharArray, begin + 2, ulength - 2);
                    } else if (ulength == 2) {
                        resultBuffer.append(unscaledCharArray, begin, 2);
                    } else {// (ulength == 1)
                        resultBuffer.append(unscaledCharArray[begin]);
                        resultBuffer.append('0');
                    }
                } else if (mod3 == 2) {
                    if (ulength > 3) {
                        resultBuffer.append(unscaledCharArray, begin, 3);
                        resultBuffer.append('.');
                        resultBuffer.append(unscaledCharArray, begin + 3, ulength - 3);
                    } else if (ulength == 3) {
                        resultBuffer.append(unscaledCharArray, begin, 3);
                    } else if (ulength == 2) {
                        resultBuffer.append(unscaledCharArray, begin, 2);
                        resultBuffer.append('0');
                    } else {// (ulength == 1)
                        resultBuffer.append(unscaledCharArray[begin]);
                        resultBuffer.append("00");
                    }
                } else {// (mod3 == 0)
                    resultBuffer.append(unscaledCharArray[begin]);
                    if (ulength > 1) {
                        resultBuffer.append('.');
                        resultBuffer.append(unscaledCharArray, begin + 1, ulength - 1);
                    }
                }
            } else {// (unscaledValue == 0)
                adjustedExponent += (3 - mod3) % 3;
                
                if (mod3 == 1) {
                    resultBuffer.append("0.00");
                } else if (mod3 == 2) {
                    resultBuffer.append("0.0");
                } else {// (mod3 == 0)
                    resultBuffer.append('0');
                }
            }
            // To append the new exponent
            if (adjustedExponent > 0) {
                resultBuffer.append("E+");
                resultBuffer.append(adjustedExponent);
            } else if (adjustedExponent < 0) {
                resultBuffer.append('E');
                resultBuffer.append(adjustedExponent);
            }
        }
        return resultBuffer.toString();
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public String toPlainString() {
        if ((signum() == 0) && (scale <= 0)) {
            return "0";
        }
        char unscaledCharArray[] = unscaledValue.toString().toCharArray();
        int begin = (signum() < 0) ? 1 : 0;
        int ulength = unscaledCharArray.length - begin;
        int numberOfZeros;
        // We take space for all digits, plus a possible decimal point, plus 'scale'
        StringBuffer resultBuffer = new StringBuffer(ulength + 1 + Math.abs(scale));
        
        if (begin == 1) {
            // If the number is negative, we insert a '-' character at front 
            resultBuffer.append('-');
        }
        if (scale > 0) {
            if (scale >= ulength) {
                resultBuffer.append("0.");
                // To append zeros
                numberOfZeros = scale - ulength;
                while (numberOfZeros > CH_ZEROS.length) {
                    resultBuffer.append(CH_ZEROS);
                    numberOfZeros -= CH_ZEROS.length;
                }
                resultBuffer.append(CH_ZEROS, 0, numberOfZeros);                
                resultBuffer.append(unscaledCharArray, begin, ulength);
            } else {
                resultBuffer.append(unscaledCharArray, begin, ulength - scale);
                resultBuffer.append('.');
                resultBuffer.append(unscaledCharArray, begin + ulength - scale, scale);
            }
        } else {// (scale <= 0)
            resultBuffer.append(unscaledCharArray, begin, ulength);
            // To append trailing zeros
            numberOfZeros = scale; 
            while (numberOfZeros < -CH_ZEROS.length) {
                resultBuffer.append(CH_ZEROS);
                numberOfZeros += CH_ZEROS.length;
            }
            resultBuffer.append(CH_ZEROS, 0, -numberOfZeros);
        }
        return resultBuffer.toString();
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigInteger toBigInteger() {
        if ((scale == 0) || (signum() == 0)) {
            return unscaledValue;
        } else if (scale < 0) {
            return unscaledValue.multiply(powerOf10(-(long)scale));
        } else {// (scale > 0)
            return unscaledValue.divide(powerOf10(scale));
        }
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigInteger toBigIntegerExact() {
        if ((scale == 0) || (signum() == 0)) {
            return unscaledValue;
        } else if (scale < 0) {
            return unscaledValue.multiply(powerOf10(-(long)scale));
        } else {// (scale > 0)
            BigInteger[] integerAndFraction;
            // An optimization before do a heavy division
            if ((scale > aproxPrecision()) || (scale > unscaledValue.getLowestSetBit())) {
                throw new ArithmeticException("Rounding necessary");
            }
            integerAndFraction = unscaledValue.divideAndRemainder(powerOf10(scale));
            if (integerAndFraction[1].signum() != 0) {
                // It exists a non-zero fractional part 
                throw new ArithmeticException("Rounding necessary");
            } else {
                return integerAndFraction[0];
            }
        }
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public long longValue() {
        /* If scale <= -64 there are at least 64 trailing bits zero in 10^(-scale).
         * If the scale is positive and very large the long value could be zero. */
        return ((scale <= -64) || (scale > aproxPrecision())
                ? 0L
                : toBigInteger().longValue());
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public long longValueExact() {
        return valueExact(64);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public int intValue() {
        /* If scale <= -32 there are at least 32 trailing bits zero in 10^(-scale).
         * If the scale is positive and very large the long value could be zero. */
        return ((scale <= -32) || (scale > aproxPrecision())
                ? 0
                : toBigInteger().intValue());
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public int intValueExact() {
        return (int)valueExact(32);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public short shortValueExact() {
        return (short)valueExact(16);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public byte byteValueExact() {
        return (byte)valueExact(8);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public float floatValue() {
        /* A similar code could be repeated here as in doubleValue() method,
         * but this simple implementation is quite efficient. */ 
        float floatResult = signum();
        long powerOfTwo = unscaledValue.bitLength() 
                        - (long)(scale / LOG10_2);
        if ((powerOfTwo < -149) || (floatResult == 0.0f)) {
            // Cases which 'this' is very small
            floatResult *= 0.0f;
        } else if (powerOfTwo > 128) {
            // Cases which 'this' is very large
            floatResult *= Float.POSITIVE_INFINITY;
        } else {
            floatResult = (float)doubleValue();
        }
        return floatResult;
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public double doubleValue() {
        int sign = signum();
        int exponent = 1076;  // bias + 53
        int lowestSetBit;
        int discardedSize;
        long powerOfTwo = unscaledValue.bitLength() - (long)(scale / LOG10_2);
        long bits;        // IEEE-754 Standard
        long tempBits;    // for temporal calculations     
        BigInteger mantisa;
        
        if ((powerOfTwo < -1074) || (sign == 0)) {
            // Cases which 'this' is very small            
            return (sign * 0.0d);
        } else if (powerOfTwo > 1024) {
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
            int k = 100 - (int)powerOfTwo;
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
            // #bits = 54, to check if the discarded fraction produces a carr:
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
                bits >>= (-exponent );
                // To test if after discard bits, a new carry is generated
                if (((bits & 3) == 3) || (((bits & 1) == 1) && (tempBits != 0) 
                        && (lowestSetBit < discardedSize))) {
                    bits += 1;
                }
                exponent = 0;
                bits >>= 1;
            }
        }
        // Construct the 64 double bits: [sign(1), exponent(11), mantisa(52)]
        bits = (sign & 0x8000000000000000L) | ((long)exponent << 52) | (bits & 0xFFFFFFFFFFFFFL);
        return Double.longBitsToDouble(bits);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     */
    public BigDecimal ulp() {
        return new BigDecimal(BigInteger.ONE, scale);
    }
    
    /* Private Methods */
    
    /**
     * It does all rounding work of the public <code>round(MathContext)</code>
     * method, performing an inplace rounding without creating a new object. 
     * @param mc the <code>MathContext</code> to perform the rounding
     * @see #round(MathContext)
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
        BigInteger[] integerAndFraction = unscaledValue.divideAndRemainder(sizeOfFraction);
        long newScale = (long)scale - discardedPrecision;
        int compRem;
        BigDecimal tempBD;
        // If the discarded fraction is non-zero, perform rounding
        if (integerAndFraction[1].signum() != 0) {
            // To check if the discarded fraction >= 0.5
            compRem = (integerAndFraction[1].abs().shiftLeft(1).compareTo(sizeOfFraction));
            // To look if there is a carry
            compRem =  roundingBehavior( integerAndFraction[0].testBit(0) ? 1 : 0,
                                         integerAndFraction[1].signum() * (5 + compRem), 
                                         mc.getRoundingMode());
            if (compRem != 0) {
                integerAndFraction[0] = integerAndFraction[0].add(BigInteger.valueOf(compRem)); 
            }
            tempBD = new BigDecimal(integerAndFraction[0]);
            // If after to add the increment the precision changed, we normalize the size
            if (tempBD.precision() > mcPrecision) {
                integerAndFraction[0] = integerAndFraction[0].divide(BigInteger.TEN);
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
     * value fits in <code>long</code> type.
     * @param mc the <code>MathContext</code> to perform the rounding
     * @param discardedPrecision the number of decimal digits that are discarded
     * @see #round(MathContext)
     */
    private void smallRound(MathContext mc, int discardedPrecision) {
        long sizeOfFraction = (long)Math.pow(10, discardedPrecision); 
        long newScale = (long)scale - discardedPrecision;
        long unscaledVal = unscaledValue.longValue();
        // Getting the interger part and the discarded fraction
        long integer = unscaledVal / sizeOfFraction;
        long fraction = unscaledVal - integer * sizeOfFraction;
        int compRem;
        // If the discarded fraction is non-zero perform rounding
        if (fraction != 0) {
            // To check if the discarded fraction >= 0.5
            compRem = ( (new Long(Math.abs(fraction) << 1)).compareTo(sizeOfFraction) );
            // To look if there is a carry
            integer += roundingBehavior( ((int)integer) & 1, 
                                         Long.signum(fraction) * (5 + compRem), 
                                         mc.getRoundingMode());
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
     * The method return an increment that can be -1,0 or 1, depending of 
     *  <code>roundingMode</code>.
     * @param parityBit can be 0 or 1, it's used to chose rounding in the 
     *        <code>HALF_EVEN</code> case 
     * @param fraction the mantisa to be analized
     * @param roundingMode the type of rounding 
     * @return the carry propagated after rounding
     */
    private static int roundingBehavior(int parityBit, int fraction, RoundingMode roundingMode) {
        int increment = 0; // the carry after rounding

        switch (roundingMode) {
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
        case UNNECESSARY:
            if (fraction != 0) {
                throw new ArithmeticException("Rounding necessary");
            }
            break;
        }
        return increment; 
    }
    
    /**
     * If <code>unscaledValue</code> has a fractional part throws an exception, 
     * otherwise to count the number of bits of value and checks if it's out 
     * of the range of the primitive type; if that happened so it throws an 
     * exception, otherwise calculate the exact value and saves it in a 
     * variable of <code>long<code> type.
     * @param bitLengthOfType number of bits of the type whose value will be 
     *              calculated exactly.
     * @return the exact value of the integer part of <code>BigDecimal</code>
     *         when is possible.
     * @throws <code>ArithmeticException</code> when rounding is necessary        
     */
    private long valueExact(int bitLengthOfType) {
        BigInteger bigInteger = toBigIntegerExact();
        
        if (bigInteger.bitLength() < bitLengthOfType) {
            // It fit in the primitive type
            return bigInteger.longValue();
        } else {
            throw new ArithmeticException("Rounding necessary");
        }
    }
    
    /**
     * If the precion already was calculated it return that value, otherwise
     * it calculates a very good and efficient aproximization. Note that this 
     * value could be <code>precision()</code> or <code>precision() - 1</code>.
     * @return an aproximization of <code>precision()</code> value
     */
    private int aproxPrecision() {
        return ((precision > 0) 
                ? precision
                : (int)((unscaledValue.bitLength() - 1) * LOG10_2)) + 1;
    }

    /**
     * It calculates a power of ten, which exponent could be out of 32-bit range.
     * Note that internally this method will be used in the worst case with
     * an exponent equals to: <code>Integer.MAX_VALUE - Integer.MIN_VALUE</code>.
     * @param exp the exponent of power of ten, it should be positive
     * @return a BigInteger with value 10^exp
     */
    private static BigInteger powerOf10(long exp) {
        // PRE: exp >= 0
        int intExp = (int)exp;
        // "SMALL POWERS"
        if (exp <= 18) {
            // The largest power that fit in 'long' type
            return TEN_POW[intExp];
        } else if (exp <= 50) {
            // To calculate:    10^exp
            return BigInteger.TEN.pow(intExp);
        } else if (exp <= 1000) {
            // To calculate:    5^exp * 2^exp 
            return BI_FIVE.pow(intExp).shiftLeft(intExp);
        }
        // "LARGE POWERS"  
        /* To check if there is free memory to allocate a BigInteger
         * of the estimated size (measured in bytes) */
        long byteArraySize = 1 + (long)(exp / (8 * LOG10_2)); 
        
        if (byteArraySize > Runtime.getRuntime().freeMemory()) {
            throw new OutOfMemoryError();
        }
        if (exp <= Integer.MAX_VALUE) {
            // To calculate:    5^exp * 2^exp
            return BI_FIVE.pow(intExp).shiftLeft(intExp);
        } else {/* Probably this branch won't be executed  
                 * since the power of ten is to much big. */
            // To calculate:    5^exp
            BigInteger powerOfFive = BI_FIVE.pow(Integer.MAX_VALUE); 
            BigInteger res = powerOfFive;
            long longExp = exp - Integer.MAX_VALUE;
            
            intExp = (int)(exp % Integer.MAX_VALUE);
            while (longExp > Integer.MAX_VALUE) {
                res = res.multiply(powerOfFive);
                longExp -= Integer.MAX_VALUE;
            }
            res = res.multiply(BI_FIVE.pow(intExp));
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
     * It tests if a scale of <code>long</code> type fit in 32 bits. It returns 
     * the same scale being casted to <code>int</code> type when is possible, 
     * otherwise throws an exception.
     * @param longScale  a 64 bit scale
     * @return a 32 bit scale when is possible
     * @throws <code>ArithmeticException</code> when <code>scale</code> 
     *      doesn't fit in <code>int</code> type. 
     */
    private static int toIntScale(long longScale) {
        if (longScale < Integer.MIN_VALUE) {
            throw new ArithmeticException("OverFlow");
        } else if (longScale > Integer.MAX_VALUE) {
            throw new ArithmeticException("UnderFlow");
        } else {
            return (int)longScale;
        }
    }
    
    /**
     * It returns <code>BigDecimal.ZERO</code> with the most aproximated 
     * scale of <code>int</code> type.
     * @param longScale the scale to which zero will be scaled
     * @return <code>BigDecimal.ZERO</code> scaled by <code>longScale</code>
     */
    private static BigDecimal zeroScaledBy(long longScale) {
        if (longScale < Integer.MIN_VALUE) {
            return new BigDecimal(BigInteger.ZERO, Integer.MIN_VALUE);
        } else if (longScale > Integer.MAX_VALUE) {
            return new BigDecimal(BigInteger.ZERO, Integer.MAX_VALUE);
        } else {
            return new BigDecimal(BigInteger.ZERO, (int)longScale);
        }
    }    

}
