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

import java.io.Serializable;
import java.util.Random;

/**
 * @author Daniel Fridlender
 * @author Matthias Gallé
 * @author Mariano Heredia
 * @author Miguel Vasquez
 * @ar.org.fitc.spec_ref 
 */
public class BigInteger extends Number implements Comparable<BigInteger> {
    
    private static final long serialVersionUID = 1L;
    
    // Internal representation
    
    /** holds the number in two complement*/
    int[] digits;
    /** Index of the first digit of {@link #digits} */
    int first;
    
    /** @ar.org.fitc.spec_ref */
    public static final BigInteger ONE 	= valueOf(1l);
    
    /** @ar.org.fitc.spec_ref */
    public static final BigInteger TEN 	= valueOf(10l);
    
    /** @ar.org.fitc.spec_ref */
    public static final BigInteger ZERO = valueOf(0l);
    
    /** BigInteger constant 2.*/
    static final BigInteger TWO = valueOf(2l);
    /** BigInteger constant -1.*/
    static final BigInteger MINUS_ONE 	= valueOf(-1l);
    
    
    // Constructuros
    
    /**
     * Random constructor
     *
     * @param numBits this \in [0, 2<sup>numBits</sup>-1]
     * @param rnd provider of randomness
     * @ar.org.fitc.spec_ref
     */
    public BigInteger(int numBits, Random rnd) {
        if( rnd == null  ){
            throw new NullPointerException("Null Random");
        }
        if( numBits < 0 ){
            throw new IllegalArgumentException("numBits must be non-negative");
        }
        int i;
        int bits;
        
        //The number of ints - 1  needed to represent an integer of numBits
        first = numBits / 32;
        digits = new int[first + 1];
        
        for (i = 0; i < first; i++) {
            digits[i] = rnd.nextInt();
        }
        bits = numBits % 32;
        if (bits != 0) {
            // applies the correct mask (Using only the necessary bits)
            digits[first] = rnd.nextInt() >>> (32 - bits);
        }
        updFirst();
    }
    
    /** @ar.org.fitc.spec_ref  */
    public BigInteger(int bitLength, int certainity, Random rnd){
        if (bitLength < 2) {
            throw new ArithmeticException("bitLength < 2");
        }
        BigInteger me = Primality.consBigInteger(bitLength, certainity, rnd);
        this.first = me.first;
        this.digits = me.digits;
    }
    
    /**
     * Creates a new BigInteger whose value is equal to the specified
     * <code>long</code>.
     *
     * @param val value of the new BigInteger
     */
    BigInteger(long val) {
        if (val <= Integer.MAX_VALUE && val >= Integer.MIN_VALUE) {
            // fits in one int
            first = 0;
            digits = new int[] {(int) val};
        } else {
            first = 1;
            digits = new int[] {(int) val, (int) (val >> 32)};
        }
    }
    
    /**
     * Creates an empty number big enough to accommodate the leading digit in
     * the given position.
     * Don't confuse with {@link #BigInteger(long)}
     *
     * @param first
     *            position of the leading digit to-be
     */
    BigInteger(int first) {
        this.first = first;
        this.digits = new int[first + 1];
    }
    
    /**
     * Creates a new BigInteger with value equal to the parameter in two-complement notation
     * @param digits don't create a copy, so any changes to the reference will affect the new number
     */
    BigInteger(int digits[]){
        if( digits.length == 0 ){
            digits = new int[]{0};
            first = 0;
        }else{
            this.digits = digits;
            first = digits.length -1;
            updFirst();
        }
    }
    
    /**@ar.org.fitc.spec_ref*/
    public BigInteger(String val) {
        //Building a BigInteger in radix 10
        this(val, 10);
    }
    
    /**@ar.org.fitc.spec_ref*/
    public BigInteger(String val, int radix) {
        if( radix < Character.MIN_RADIX || radix > Character.MAX_RADIX ){
            throw new NumberFormatException("Radix out of range");
        }
        if( val.length() == 0 ){
            throw new NumberFormatException("Zero length BigInteger");
        }
        BigInteger me = Conversion.string2BigInteger(val, radix);
        this.first = me.first;
        this.digits = me.digits;
    }
    
    /**@ar.org.fitc.spec_ref*/
    public BigInteger(int signum, byte[] magnitude) {
        
        if (signum < -1 || signum > 1) {
            throw new NumberFormatException("Invalid signum value");
        }
        
        boolean allZero = ByteArrayConversion.allZero(magnitude);
        
        if (signum == 0 && !allZero) {
            throw new NumberFormatException("signum-magnitude mismatch");
        }
        
        if (allZero) {
            // must be zero regardless the signum
            digits = new int[1];
            first = 0;
        } else {
            first = (magnitude.length - 1) / 4;  	// index of the first digit (little-endian)
            if (magnitude.length % 4 == 0 && magnitude[0] < 0) {
                first++;                    		// case in which an extra signum digit is necessary
            }
            
            digits = new int[first + 1]; 			// array created with the necessary length
            
            if (signum > 0){
                ByteArrayConversion.fromPlusMagnitude(this,magnitude);
            } else{
                ByteArrayConversion.fromMinusMagnitude(this,magnitude);
            }
            updFirst();
        }
    }
    
    /**@ar.org.fitc.spec_ref*/
    public BigInteger(byte[] val) {
        
        if (val.length == 0) {
            throw new NumberFormatException("Zero length BigInteger");
        }
        
        first = ((val.length - 1) / 4);  // index of the first digit (little-endian)
        digits = new int[first + 1];     // array created with the necessary length
        
        ByteArrayConversion.fromByteArray(this,val);
    }
    
    /**
     * @ar.org.fitc.spec_ref
     * At the moment implemented as a <code>(long)</code> constructor.
     */
    public static BigInteger valueOf(long val) {
        return new BigInteger(val);
    }
    
    /**
     * Skips redundant leading digits.
     * @see #first
     */
    void updFirst() {
        // at most one of these loops will actually be executed
        for (; digits[first] == 0 && first > 0 && digits[first - 1] >= 0; first--) ; // skip leading 0's
        for (; digits[first] == -1 && first > 0 && digits[first - 1] < 0; first--) ; // skip leading -1's
    }    
    
    /**@ar.org.fitc.spec_ref*/
    public byte[] toByteArray() {
        return ByteArrayConversion.toByteArray(this);
    }
    
    /**@ar.org.fitc.spec_ref*/
    public BigInteger abs() {
        return (signum() >= 0) ? this : negate();
    }
    
    /**@ar.org.fitc.spec_ref*/
    public BigInteger negate() {
        return Elementary.negate(this);
    }
    
    /**@ar.org.fitc.spec_ref*/
    public BigInteger add(BigInteger val) {
        BigInteger res;
        
        if (first >= val.first ) {
            res = Elementary.add(this, val);
        } else {
            res = Elementary.add(val, this);
        }
        
        return res;
    }
    
    /**@ar.org.fitc.spec_ref*/
    public BigInteger subtract(BigInteger val) {
        BigInteger res;
        
        if (first >= val.first) {
            res = Elementary.subtractShorter(this, val);
        } else {
            res = Elementary.subtractLonger(this, val);
        }
        
        return res;
    }
    
    /**@ar.org.fitc.spec_ref*/
    public int signum() {
        int res = 0; // default, when this == 0
        if (digits[first] < 0) {
            res = -1;
        } else if (first != 0 || digits[0] != 0) {
            res = 1;
        }
        
        return res;
    }
    
    /**@ar.org.fitc.spec_ref*/
    public BigInteger shiftRight(int n) {
        BigInteger res = this; // default, when n == 0 or this == 0
        if( this.signum() != 0){
            if (n > 0) {
                res = Bitlevel.shiftRight(this, n);
            } else if (n < 0) {
                res = Bitlevel.shiftLeft(this,-n);
            }
        }
        return res;
    }
    
    /**@ar.org.fitc.spec_ref*/
    public BigInteger shiftLeft(int n) {
        BigInteger res = this; // default, when n == 0 or this == 0
        if( this.signum() != 0 ){
            if (n > 0) {
                res = Bitlevel.shiftLeft(this, n);
            } else if (n < 0) {
                res = Bitlevel.shiftRight(this,-n);
            }
        }
        return res;
    }
    
    /**@ar.org.fitc.spec_ref*/
    public int bitLength() {
        return Bitlevel.bitLength(this);
    }
    
    /**@ar.org.fitc.spec_ref*/
    public boolean testBit(int n) {
        
        if (n < 0) {
            throw new ArithmeticException("Negative bit address");
        }
        
        // pre n >= 0
        
        int whichDigit = n / 32;
        int position = n % 32;
        int mask = (1 << position);
        boolean res;
        
        if (whichDigit <= first) {
            res = (digits[whichDigit] & mask) != 0;
        } else {
            res = digits[first] < 0;
        }
        
        return res;
    }
    
    /**@ar.org.fitc.spec_ref*/
    public BigInteger setBit(int n) {
        if (n < 0) {
            throw new ArithmeticException("Negative bit address");
        }
        return testBit(n) ? this : Bitlevel.setBit(this,n);
    }
    
    /**@ar.org.fitc.spec_ref*/
    public BigInteger clearBit(int n) {
        if (n < 0) {
            throw new ArithmeticException("Negative bit address");
        }
        return testBit(n) ? Bitlevel.clearBit(this, n) : this;
    }
    
    /**@ar.org.fitc.spec_ref*/
    public BigInteger flipBit(int n) {
        if (n < 0) {
            throw new ArithmeticException("Negative bit address");
        }
        return testBit(n) ? Bitlevel.clearBit(this, n) : Bitlevel.setBit(this, n);
    }
    
    /**@ar.org.fitc.spec_ref*/
    public int getLowestSetBit() {
        return Bitlevel.getLowestSetBit(this);
    }
    
    /**@ar.org.fitc.spec_ref*/
    public int bitCount() {
        return Bitlevel.bitCount(this);
    }
    
    /**@ar.org.fitc.spec_ref*/
    public BigInteger not() {
        return Logical.not(this);
    }
    
    /**@ar.org.fitc.spec_ref*/
    public BigInteger and(BigInteger val) {
        BigInteger res;
        
        if (first >= val.first ) {
            res = Logical.and(this, val);
        } else {
            res = Logical.and(val, this);
        }
        
        return res;
    }
    
    /**@ar.org.fitc.spec_ref*/
    public BigInteger or(BigInteger val) {
        BigInteger res;
        
        if (first >= val.first) {
            res = Logical.or(this, val);
        } else {
            res = Logical.or(val, this);
        }
        
        return res;
    }
    
    /**@ar.org.fitc.spec_ref*/
    public BigInteger xor(BigInteger val) {
        BigInteger res;
        
        if (first >= val.first ) {
            res = Logical.xor(this, val);
        } else {
            res = Logical.xor(val, this);
        }
        
        return res;
    }
    
    /**@ar.org.fitc.spec_ref*/
    public BigInteger andNot(BigInteger val) {
        BigInteger res;
        
        if (first >= val.first ) {
            res = Logical.andNotShorter(this, val);
        } else {
            res = Logical.andNotLonger(this, val);
        }
        
        return res;
    }
    
    /**
     * returns a clone of this
     *
     * @return a copy of this, so that equals(clone()) == true.
     */
    protected BigInteger clone() {
        BigInteger res = new BigInteger(this.first);
        System.arraycopy(this.digits, 0, res.digits, 0, this.first+1);
        return res;
    }
    
    /**
     * Performs <code>dest := src</code>
     * @param src must be not null
     * @param dest must be not null and have been created with enough space
     */
    static void copy(BigInteger src, BigInteger dest){
        dest.first = src.first;
        System.arraycopy(src.digits, 0, dest.digits, 0, src.first+1);
    }
    
    
    /**@ar.org.fitc.spec_ref*/
    @Override
    public int intValue() {
        return digits[0];
    }
    
    /**@ar.org.fitc.spec_ref*/
    @Override
    public long longValue() {
        return first >= 1 ? (((long) digits[1]) << 32) | (0xFFFFFFFFl & digits[0])
        : (long) digits[0];
    }
    /**@ar.org.fitc.spec_ref*/
    @Override
    public float floatValue() {
        return (float) Conversion.bigInteger2Double(this);
    }
    /**@ar.org.fitc.spec_ref*/
    @Override
    public double doubleValue() {
        return Conversion.bigInteger2Double(this);
    }
    
    /**@ar.org.fitc.spec_ref*/
    public int compareTo(BigInteger val) {
        return Elementary.compare(this, val);
    }
    
    /**@ar.org.fitc.spec_ref*/
    public BigInteger min(BigInteger val) {
        return compareTo(val) <= 0 ? this : val;
    }
    
    /**@ar.org.fitc.spec_ref*/
    public BigInteger max(BigInteger val) {
        return compareTo(val) >= 0 ? this : val;
    }
    /**@ar.org.fitc.spec_ref*/
    public int hashCode() {
        return intValue();
    }
    
    /**@ar.org.fitc.spec_ref*/
    public boolean equals(Object x) {
        boolean ret = false;
        int i = -1;
        //To avoid cast in every access to x
        BigInteger other;
        
        if( x instanceof BigInteger ){
            other = (BigInteger)x;
            if(this.first == other.first){
                ret = true;
                for(i=0; i <= first && ret; i++){
                    if(digits[i] != other.digits[i]){
                        ret = false;
                    }
                }
            }
        }
        return ret;
    }
    
    /**@ar.org.fitc.spec_ref*/
    public String toString() {
        return Conversion.bigInteger2String(this,10);
    }
    /**@ar.org.fitc.spec_ref*/
    public String toString(int radix){
        if (radix > Character.MAX_RADIX || radix < Character.MIN_RADIX){
            radix = 10;
        }
        return Conversion.bigInteger2String(this,radix);
    }
    /**@ar.org.fitc.spec_ref*/
    public BigInteger gcd(BigInteger val) {
        if (this.signum() == 0){
            return val.abs();
        } else if (val.signum() == 0){
            return this.abs();
        }
//    	 now it fulfills the precondition
        return Multiplication.gcdBinary(this.abs(),val.abs());
    }
    /**@ar.org.fitc.spec_ref*/
    public BigInteger multiply(BigInteger val) {
        return Multiplication.multiply(this, val);
    }
    /**@ar.org.fitc.spec_ref*/
    public BigInteger pow(int exp) {
        BigInteger res = ONE; // default, when n == 0
        if (exp < 0) {
            throw new ArithmeticException("Negative exponent");
        } else if (exp > 0) {
            res = Multiplication.pow(this, exp);
        }
        return res;
    }
    
    
    /**@ar.org.fitc.spec_ref*/
    public BigInteger[] divideAndRemainder(BigInteger divisor) {
        // the usual initial sign stuff to work with unsigned number
        int ourSignum = this.signum();
        int divSignum = divisor.signum();
        BigInteger[] result;
        BigInteger dividend;
        if (divisor.signum() == 0)
            throw new ArithmeticException("BigInteger divide by zero");
        if (this.signum() == 0)
            return new BigInteger[] {BigInteger.ZERO, BigInteger.ZERO};
        
        dividend = this.abs();
        divisor = divisor.abs();
        
//        result = Division.divide(dividend,divisor);
        result = Division.knuthDivision( dividend, divisor, Division.QUOTIENT | Division.REMAINDER);
        
        // the usual final sign stuff
        if (ourSignum < 0){
            // See "The Java Language Specification" 15.17.3 to see how work the remainder operator (%)
            result[1] = result[1].negate();
        }
        if (ourSignum * divSignum < 0){
            result[0] = result[0].negate();
        }
        return result;
    }
    
    /**@ar.org.fitc.spec_ref*/
    public BigInteger divide(BigInteger divisor){
        // the usual initial sign stuff to work with unsigned number
        int ourSignum = this.signum();
        int divSignum = divisor.signum();
        
        BigInteger result;
        BigInteger dividend;
        if (divisor.signum() == 0)
            throw new ArithmeticException("BigInteger divide by zero");
        if (this.signum() == 0){
            return BigInteger.ZERO;
        }
        
        dividend = this.abs();
        divisor = divisor.abs();
        result = Division.knuthDivision( dividend, divisor, Division.QUOTIENT)[0];
        
        // the usual final sign stuff
        if (ourSignum * divSignum < 0){
            Elementary.inplaceNegate(result);
        }
        return result;
        
    }
    
    /**@ar.org.fitc.spec_ref*/
    public BigInteger remainder(BigInteger divisor){
        // the usual initial sign stuff to work with unsigned number
        int ourSignum = this.signum();
        BigInteger result;
        BigInteger dividend;
        if (divisor.signum() == 0)
            throw new ArithmeticException("BigInteger divide by zero");
        if (this.signum() == 0)
            return BigInteger.ZERO;
        dividend = this.abs();
        divisor = divisor.abs();
        
        result = Division.knuthDivision( dividend, divisor, Division.REMAINDER)[1];
        
        // the usual final sign stuff
        if (ourSignum < 0){
            // See "The Java Language Specification" 15.17.3 to see how work the remainder operator (%)
            result = result.negate();
        }
        
        return result;
        
    }    
    
    /**@ar.org.fitc.spec_ref*/
    public BigInteger modInverse(BigInteger m){
        BigInteger[] inverseAndMore;
        if( m.signum() <= 0 ){
            throw new ArithmeticException("modulus not positive" );
        }
        if( this.equals( ONE ) ){
            return m.equals(ONE) ? ZERO : ONE;
        }
        inverseAndMore = Multiplication.modInverse( this, m );
        
        if (inverseAndMore[1].equals(MINUS_ONE)){
            return inverseAndMore[0].negate().add( m );
        } else if(! inverseAndMore[1].equals(ONE)){
            throw new ArithmeticException("BigInteger not invertible.");
        } else{
            return inverseAndMore[0].mod( m );
        }
    }
    
    
    /**@ar.org.fitc.spec_ref*/
    public BigInteger modPow(BigInteger exponent, BigInteger m){
        if (m.signum() <= 0){
            throw new ArithmeticException("modulus not positive");
        }
        BigInteger base = this;
        if (exponent.signum() < 0){
            base = modInverse(m);
            exponent = exponent.negate();
        }
        BigInteger res = Division.modPow(base.abs(),exponent,m);
        if (exponent.testBit(0) && base.signum() < 0){
            // -b^e mod m == ((-1 mod m) * (-b^e mod m)) mod m
            res = m.subtract(BigInteger.ONE).multiply(res).mod(m);
        }
        // else exponent is even, so base^exp is positive
        return res;
    }
        
    /**
     * Equal to {@link #mod(BigInteger)}, but don't throw ArithmeticException if the modulus is negative
     */
    BigInteger internMod(BigInteger m){
        BigInteger remainder = this.remainder(m);
        if (remainder.signum() < 0){
            // mod is always positive
            remainder = remainder.add(m);
        }
        return remainder;
    }
    
    /**@ar.org.fitc.spec_ref*/
    public BigInteger mod(BigInteger m){
        if (m.signum() <= 0){
            throw new ArithmeticException("modulus not positive");
        }
        return internMod(m);
    }
    
    /**@ar.org.fitc.spec_ref*/
    public boolean isProbablePrime(int certainity){
        return Primality.isProbablePrime(this.abs(),certainity);
    }
    
    /**@ar.org.fitc.spec_ref*/
    public BigInteger nextProbablePrime(){
        return Primality.nextProbablePrime(this);
    }
    
    /**@ar.org.fitc.spec_ref*/
    public static BigInteger probablePrime(int bitLength, Random rnd){
        if( bitLength < 2  ) {
            throw new ArithmeticException( "bitLength < 2" );
        }
        return Primality.consBigInteger(bitLength, 100, rnd);
    }    
}
