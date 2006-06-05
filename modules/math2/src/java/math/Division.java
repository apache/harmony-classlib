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
 */
class Division {
    /**Flag for knuthDivision.*/
    static final int QUOTIENT = 0x01;
    /**Flag for knuthDivision.*/
    static final int REMAINDER = 0x02;
    //Any ocurrence of BASE can be replaced with the correspondent constant value
    /**The current numeric base.*/
    private static final long BASE = 0x100000000l;
    
    
    /** Just to denote that can't be instantied*/
    private Division(){
    }
    
    /**
     * Divide a <code>BigInteger</code> by a signed <code>int</code> and returns the remainder
     * @param dividend the BigInteger to be divided. Must be non-negative.
     * @param divisor a signed int
     * @return divide % divisor
     */
    static int remainder(BigInteger dividend, int divisor){
        BigInteger ret = dividend.clone();
        return inPlaceDivideAndRemainderPositive(ret, divisor);
    }
    
    /**
     * Performs dividend = dividend / divisor and returns dividend % divisor
     * Suppose dividend >= 0 && divisor >= 0
     *
     * @param dividend the BigInteger to be divided, also stores the result.
     * @param divisor a signed int
     * @return the remainder of the division of dividend by divisor
     *
     * @see "The Art of Computer Programming Volume 2, Donald Knuth. Exercise 4.3.1.16"
     * @throws IllegalArgumentException dividend.signum() < 0 or divisor < 0
     */
    static int inPlaceDivideAndRemainderPositive(BigInteger dividend, int divisor) throws IllegalArgumentException{
        if (dividend.signum() < 0 || divisor < 0){
            throw new IllegalArgumentException("negative parameters");
        }
        int index;
        long remainder = 0l;
        int[] number;
        long tmp;
        
        number = dividend.digits;
        
        for(index = dividend.first; index >= 0; index--){
                    /* ret[i] = (r*b+u[i]) / divisor
                     *      r = (r*b+u[i]) % divisor
                     */
            tmp = (remainder << 32) | (number[index] & 0xFFFFFFFFl);
            number[index] = (int) (tmp/(long)divisor);
            remainder = tmp % divisor;
        }
        
        dividend.updFirst();
        
        return (int) remainder;
    }
    
    /**
     * @param base
     * @param exponent could be negative
     * @param modulos must be non negative
     * @return base<sup>exponent</sup> 'mod' modulos
     * @see BigInteger#modPow(BigInteger, BigInteger)
     */
    static BigInteger modPow(BigInteger base, BigInteger exponent, BigInteger modulos){
        BigInteger res = BigInteger.ONE;
        BigInteger fixMod = base.mod(modulos);
        
        for (int i = exponent.bitLength(); i >= 0; i--) {
            res = res.multiply(res.mod(modulos));
            if (exponent.testBit(i)){
                res = res.multiply(fixMod).mod(modulos);
            }
        }
        return res.mod(modulos);
    }    
    
    /**
     * Performs the division with the algorithm exposed in "D. Knuth, The Art of Computer Programming Vo.2"
     * Not only take the two BigInteger necessary to perform the division, but also a third parameter who indicate what should be
     * calclulated:
     * options == {@link #QUOTIENT}: calculate only the quotient
     * options == {@link #REMAINDER}: calculate only the remainder
     * options == {@link #REMAINDER} | {@link #REMAINDER}: calculate both
     *
     * @param dividend
     * @param divisor
     * @param options indicate in what there is interest: {@link #QUOTIENT}, {@link #REMAINDER} or both.
     * @return [quotient, remainder], where one (or both) of that values could be null (depending of <code>options</code>)
     * @see BigInteger#divideAndRemainder(BigInteger)
     */
    static BigInteger[] knuthDivision(final BigInteger dividend, final BigInteger divisor, int options){
        //Quick exits
        if( divisor.bitLength() > dividend.bitLength() ){
            return new BigInteger[] {BigInteger.ZERO, dividend};
        }
        if( divisor.bitLength() == dividend.bitLength() ){
            if( dividend.compareTo(divisor) < 0 ) {
                return new BigInteger[] {BigInteger.ZERO, dividend};
            }
        }
        if (divisor.first == 0 || (divisor.first == 1 && divisor.digits[1] == 0) ){
            // equivalent to ask divisor.bitLength() < 33
            return Division.divideAndRemainderLongPositive( dividend, divisor.intValue() );
        }
        
        // Step 1): normalize: ensure that v[n-1] >= BASE/2
        int toShift = Integer.numberOfLeadingZeros(divisor.digits[divisor.first]);
        final int dividendMargin = Integer.numberOfLeadingZeros( dividend.digits[ dividend.first ] );
        final int dividendLength;						// amount of digits of dividend
        final BigInteger u;
        final BigInteger v;
        //Dividend will have alwas a [0] = 0 digit (to make dividend.first=n+m)
        if (toShift != 32){
            //See if shifting the dividend will need an aditional digit
            u = new BigInteger( dividend.first + (toShift > dividendMargin ? 2 : 1) );
            dividendLength = u.first;
            Bitlevel.inplaceShiftLeft(u, dividend, toShift);
            v = Bitlevel.shiftLeft(divisor, toShift);
        } else{
            //This is dividend.first += 0 || 1 (if dividend.digits[0]==0 then don't touch first) )
            u = new BigInteger( dividend.first + (dividend.digits[dividend.first] >> (31-dividendMargin))  );
            dividendLength = u.first;
            BigInteger.copy( dividend, u );
            v = divisor;
            toShift = 0;
        }        
        // Step 2): initialization
        final int divisorLength  = v.first; 		// amount of digits of divisor ('n' in Knuth algorithm)
        final long div = v.digits[divisorLength-1] & 0xFFFFFFFFl; 	// first digit of the divisor
        final long divisorDigit_n_2 = v.digits[divisorLength-2] & 0xFFFFFFFFl;// second digit of the divisor
        long first, second;								// first and second digit of the dividend (will change)
        long[] qAndR;
        long q,r;        
        int j = dividendLength;
        int i = dividendLength - divisorLength;
        final int[] resDigits = new int[i+1];			// will hold the result
        
        for(j = dividendLength; i >= 0; i--,j-- ){
            if( u.digits[j] == div ){
                //This is BASE - 1 (Will avoid to ask q == BASE) in the else branch
                q = 0xFFFFFFFFL;
            }else{
                // Step 3): calculate quotient = (first * BASE + second) / div
                first = ( ( (long) u.digits[ j ] ) << 32 );	// its supposed that dividend have n+m digits, so the virtual (n+m)th digit is 0
                second  = ( (long) u.digits[j-1] ) & 0xFFFFFFFFl;
                
                // note that qAndR[i] fit in an int, because div >= BASE/2
                qAndR = Division.unsignedDivideAndRemainderPositive(first | second, div);
                q = qAndR[0];
                r = qAndR[1];
                
                // adjust the quotient if I've missed it (Twice at most)
                if (  greater( q * divisorDigit_n_2, ( (long)r << 32) | (u.digits[j-2] & 0xFFFFFFFFl) ) ){
                    q--;
                    r+=div;
                    if ( r < BASE ){
                        // again the same (This will be the last possible correction)
                        if (  greater(q * divisorDigit_n_2, ((long)r << 32) | ( u.digits[j-2] & 0xFFFFFFFFl) ) ){
                            q--;
                        }
                    }
                }
            }
            
            // Step 4): multiply and subtract to control
            if ( !multiplyAndSubtract(u.digits, i, divisorLength, v.digits, q) ){
                // Step 5) and 6). Test remainder: probability of enter here is about 2^-31
                q--;
                Elementary.inplaceAdd(u.digits, i, divisorLength, v.digits);
            }
            resDigits[i] = (int) q;
        }
        
        BigInteger quotient=null, remainder=null;
        //Unmask options
        if ( (options & QUOTIENT) != 0 ){
            quotient = new BigInteger( resDigits ); // not necessary to be unnormalized: (a*c)/(b*c) == a/b
        }
        
        if ( (options & REMAINDER) != 0 ){
            remainder = new BigInteger(divisorLength);
            System.arraycopy(u.digits,0,remainder.digits,0,divisorLength);
            remainder.updFirst();
            Bitlevel.inplaceShiftRight(remainder, toShift);	// necessary to unnormalize
        }
        
        return new BigInteger[] {quotient,remainder};
    }
    
    /**
     * Returns dividend = dividend / divisor
     *
     * Note that the difference to {@link #inPlaceDivideAndRemainderPositive(BigInteger, int)} is
     * that it divides an unsigned int.
     * Suppose dividend >= 0 && divisor >= 0
     *
     * @param dividend the BigInteger to be divided.
     * @param divisor a unsigned int
     * @return dividend / divisor
     *
     * @see "The Art of Computer Programming Volume 2, Donald Knuth. Exercise 4.3.1.16"
     */
    static BigInteger[] divideAndRemainderLongPositive(BigInteger dividend, int divisor) throws IllegalArgumentException{
        //pre:dividend > 0
        /*if (dividend.signum() < 0){
            throw new IllegalArgumentException("negative parameters");
        }*/
        int index;
        long tmp;
        long storage[] = new long[2];
        BigInteger res = new BigInteger(dividend.first);
        long realDivisor = divisor & 0xFFFFFFFFl;
        
        for(index = dividend.first; index >= 0; index--){
                    /* res[i] = (r*b+u[i]) / divisor
                     *      r = (r*b+u[i]) % divisor
                     */
            tmp = (storage[1] << 32) | (dividend.digits[index] & 0xFFFFFFFFl);
            storage = unsignedDivideAndRemainderPositive(tmp,realDivisor);
            res.digits[index] = (int) storage[0];
        }
        res.updFirst();
        return new BigInteger[] {res, BigInteger.valueOf(storage[1] & 0xFFFFFFFFl)};
    }
//	Auxiliary methods
    /** Perfomrs an unsigned y/z division.
     * Reference Addison-Wesley Hacker's Delight Chapeter 9 Section 3 (Unsigned
     * Short Division). z must be > 0, y could be any {@code long} value.
     *
     * @return An array containing the quotient [0], and the remainder [1]
     */
    static long[] unsignedDivideAndRemainderPositive( long y, long z) {
        //pre:  z > 0
        //TODO: improve ->(when called bye knutDivision pre: z >= 2^31 && z <= 2^32-1
        //The most significative bit (sign bit) could is turned on
        long[] qAndR = new long[2];
        //Both are > 0, the apply java operators
        if( y >= 0L ){
            qAndR[0]=y/z;
            qAndR[1]= y%z;
        }else{
            //this is (y/(2*z) * 2) (to aproximate q)
            qAndR[0] = ( (y>>>1) / z ) << 1;
            qAndR[1] = y - ( qAndR[0] * z );
            //this is if(r >= z)
            if( qAndR[1] < 0 || qAndR[1] >= z ){
                //Fixing the error in q and r
                qAndR[0]++;
                qAndR[1] -= z;
            }
        }
        
        return qAndR;
    }    
    
    /**
     * Unsigned long comparison.
     *
     *@return a > b
     */
    private static boolean greater(long a, long b){
        //Discaring sign bit
        long x = a>>>1;
        long z = b>>>1;
        //Comparing the remaining bits, if equals compare the bit losed
        return ( x > z ) || ( x == z && (a & 1) > (b & 1)  );
    }   
    
    /**
     * a[aFirst...aFirst+length] = a[aFirst...aFirst+length] - b * q
     * PRE: b.length == length
     * @param a	with a.length >= a.length+aFirst. If this two values are equal, a[a.length+aFirst] is supposed to be 0.
     * @param aFirst
     * @param length
     * @param b b.length == length
     * @param q q < 2<sup>33</sup>
     * @return false iif the result is negative
     */
    private static boolean multiplyAndSubtract(int[] a, int aFirst, int length, int[] b, long q){
        /*pre b.length > length;
        pre q < ( 1l << 33 ) ;
        pre aFirst+length < a.length;*/
        
        long mulCarry=0;
        long borrow=0;
        int i;
        for(i=0; i <= length; i++){
            // perform multiplication and subtract
            mulCarry = ( b[i] & 0xFFFFFFFFl ) * q + ( (mulCarry >>> 32) & 0xFFFFFFFFl );
            borrow = ( a[ i + aFirst ] & 0xFFFFFFFFl ) - ( mulCarry & 0xFFFFFFFFl ) - borrow;
            a[ i+aFirst ] = (int) borrow;
            borrow = borrow >>> 63L;
        }
        return a[ length + aFirst ] >= 0;
        
    }
}
