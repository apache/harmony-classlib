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


class Conversion {
    /** Just to denote that can't be instantied*/
    private Conversion(){
    }
    /**
     * Private fields to determine Alphabetical digits in {@code int} type values.
     * @see #digit(char, int)
     */
    private static final int aPlusTen = 'a' - 10;
    /**
     * Private fields to determine Alphabetical digits in {@code int} type values.
     * @see #digit(char, int)
     */
    private static final int APlusTen = 'A' - 10;
    
    /** Holds the maximal exponent for each radix, so that radix<sup>digitFitInInt[radix]</sup> fit in an int (32 bits)*/
    private static final int[] digitFitInInt = {-1, -1, 30, 19, 15, 13, 11, 11, 10, 9, 9, 8,
    8, 8, 8, 7, 7, 7, 7, 7, 7, 7, 6, 6, 6, 6,
    6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 5};
    
    /** Holds n<sup>digitFitInInt[n]</sup> */
    private static final int[] newRadixes = { -1, -1, 1073741824, 1162261467,
       1073741824, 1220703125, 362797056, 1977326743, 1073741824,
       387420489, 1000000000, 214358881, 429981696, 815730721, 1475789056,
       170859375, 268435456, 410338673, 612220032, 893871739, 1280000000,   
       1801088541, 113379904, 148035889, 191102976, 244140625, 308915776,
       387420489, 481890304, 594823321, 729000000, 887503681, 1073741824,
       1291467969, 1544804416, 1838265625, 60466176 };
    
    
    /**
     * Returns the string representantion of a BigInteger in the specified radix
     * @see BigInteger#toString(int)
     */
    static String bigInteger2String(BigInteger toConvert, int radix){
        int digitsInInt = digitFitInInt[ radix ] ;
        int newRadix = newRadixes[ radix ] ;
        int size = (32*(toConvert.first+1)) / (31-Integer.numberOfLeadingZeros(radix)) + digitsInInt;
        /* this formula comes from (2^32)^(first+1) == radix^size
         * I add radixInInt, because the eventual leading zero's.
         */
        String digit;
        int offset = 0;
        StringBuilder sb = new StringBuilder(size);
        
        sb.setLength( sb.capacity() );
        //Making all digits to be 0,
        //TODO: see the way to fill only the necessary 0, not all
        fill( sb, '0', sb.capacity() );
        
        BigInteger calc = toConvert.clone();
        Elementary.inplaceAbs(calc);
        do {
            digit = Integer.toString( Division.inPlaceDivideAndRemainderPositive(calc, newRadix), radix);
            offset += digit.length() ;
            //Inserting the new "digit" in the final resulting StingBuider
            fill(sb, digit , sb.capacity()-offset );
            //With this step in offset, we are filling the correspondent zeros
            offset += (digitsInInt - digit.length() );
            //TODO: see the way to fill only the necessary 0
        } while(calc.signum() != 0);
        
        // the zeros inserted last fill( sb, '0', sb.capacity() ) must be removed
        removeLeadingZeros(sb);
        
        if(toConvert.signum() < 0){
            sb.insert(0,'-');
        }
        
        return sb.toString();
    }
    
    /**
     * Performs fill(dest, toFill, 0, len).
     * @see #fill(StringBuilder, char, int, int)
     */
    private static void fill(final StringBuilder dest, final char toFill, final int len){
        fill(dest, toFill, 0, len);
    }
    
    
    /**
     * Inserts (without modifying the capacity or length of {@code dest} properties)  {@code len toFill chars}
     * into {@code dest}. Note that this insertion will erase the existent data in the
     * range [offset, offset + len].
     *
     * @throws IndexOutOfBoundException if {@code offset + len > dest
     * .capacity() || offset + len > dest.length() }
     */
    private static void fill(final StringBuilder dest, final char toFill,final int offset, final int len){
        //pre: len <= dest.capacity()
        for(int i = 0; i < len; i++){
            dest.setCharAt(i,toFill);
        }
    }
    /**
     * Inserts (without modifying the capacity or length of {@code dest} properties) {@code toFill}
     * into {@code dest}. Note that this insertion will erase the existent data in the
     * range [offset, offset + toFill.length].
     *
     * @throws IndexOutOfBoundException if {@code offset + toFill.length() > dest
     * .capacity()greater || offset + toFill.length() > dest.length() }
     */
    private static void fill(final StringBuilder dest, final String toFill, final int offset){
        //assert dest.capacity() - offset >= toFill.length();
        int len = toFill.length() + offset;
        for(int i = offset; i < len; i++){
            dest.setCharAt( i, toFill.charAt( i - offset  ) );
        }
    }
    
    /**
     * remove leading zero's of a StringBuffer, leaving one if there are all 0's.
     * @param sb
     */
    private static void removeLeadingZeros(final StringBuilder sb){
        int i;
        for(i = 0; i < sb.length() && sb.charAt(i) == '0'; i++) ;
        if (i == sb.length()){
            sb.setLength(1);
        } else{
            sb.delete(0,i);
        }
    }
    
    /**
     * Implementation of String -> BigInteger with radix maximal and String.
     * @see BigInteger#BigInteger(String, int)
     */
    static BigInteger string2BigInteger(String val, int radix){
        int index = 0;
        int digit;								// holds a digit (in radix #newRadix)
        BigInteger res;							// holds the temporary and final result
        int newRadix = newRadixes[radix];		// radix.pow(radixInInt)
        int digitsInInt = digitFitInInt[radix]; // how much digits of radix fit in one int
        char valChar[] = val.toCharArray();
        boolean negative;
        
        if (valChar.length == 0){
            throw new NumberFormatException("Zero length");
        }
        
        negative = valChar[0] == '-';
        if (negative){
            index++;
        }
        res = new BigInteger(0); // == BigInteger.ZERO.clone()
        
        // "c1 c2 c3 .. cn" == (..(((c1*radix)+c2)*radix + c3)...+cn)
        for (; index + digitsInInt < valChar.length; index += digitsInInt) {
            res = Multiplication.multiply(res, newRadix);
            digit = parseInt(valChar, index, digitsInInt, radix);
            Elementary.inplaceAdd(res,digit);
        }
        // finally, I parse the rest of the string (have < than radixInInt digits)
        res = Multiplication.multiply(res, (int) Math.pow(radix,valChar.length-index));
        digit = parseInt(valChar, index, valChar.length-index,radix);
        Elementary.inplaceAdd(res,digit);
        
        return negative ? res.negate() : res;
    }
    
    /**
     * Parse a <code>int</code> from a array of character. Must be an array of digits (cannot contain
     * a '-'. Don't check if the number don't fit in a <code>int</code>.
     * @param valChar src for the long <code> val.length < offset + count </code>
     * @param offset first index
     * @param count amount of digits to parsed
     * @param radix
     * @return an int of value val[offset, offset+count)
     * @see #string2BigInteger(String,int)
     */
    private static int parseInt(final char[] valChar, final int offset, final int count, final int radix) throws NumberFormatException {
        int res = 0;
        int limit = count + offset;
        int digit;
        
        for (int i = offset; i < limit; i++) {
            digit = /*Character.*/digit(valChar[i],radix);
            if (digit == -1) {
                throw new NumberFormatException("For input string: " + String.valueOf(valChar, offset, count));
            }
            res = res * radix + digit;
        }
        return res;
    }
    /**
     * Returns the {@code int} value of {@code digit} in the specified {@code radix}.
     * @see #parseInt(char[],int,int,int)
     */
    private static int digit(final char digit, final int radix){
        //pre: radix >= Character.MIN_RADIX && radix <= Character.MAX_RADIX
        int value=-1;
        if( digit >= '0' && digit <= '9'  ){
            value = digit - '0';
        }
        //Lowercases
        else if( digit >= 'a' && digit <= 'z' ){
            value = digit - aPlusTen;
        } else if( digit >= 'A' && digit <= 'Z' ){
            value = digit - APlusTen;
        }
        
        return (value < radix && value >=0) ? value : -1;
    }
    
    /**
     * Builds the correspondent double representation of {@code value}.
     *
     * @see BigInteger#doubleValue()
     */
    static double bigInteger2Double(BigInteger value){
        int sign = value.signum();
        
        if (sign == 0) {
            return 0.0d;
        }
        if (value.first > 32) {
            /* Many cases which 'value' is very large,
             * (value.first > 32)  implies  (bitLength > 1024) */
            return ((sign > 0) ? Double.POSITIVE_INFINITY
                    : Double.NEGATIVE_INFINITY);
        }
        BigInteger mantisa = value.abs();
        int n = mantisa.bitLength();
        int exponent = 1076;  // bias + 53
        int lowestSetBit = mantisa.getLowestSetBit();
        int discardedSize = n - 54;
        long bits;   // IEEE-754 Standard
        
        if (discardedSize > 0) {// (n > 54)
            // mantisa = abs(value) >> (n - 54)
            bits = mantisa.shiftRight(discardedSize).longValue();
            // #bits = 54, to check if the discarded fraction produces a carry:
            if ((((bits & 1) == 1) && (lowestSetBit < discardedSize))
            || ((bits & 3) == 3)) {
                bits += 2;
            }
        } else {// (n <= 54)
            // mantisa = abs(value) << (54 - n)
            bits = mantisa.longValue() << -discardedSize;
            // #bits = 54, to check if the discarded fraction produces a carry:
            if ((bits & 3) == 3) {
                bits += 2;
            }
        }
        // test bit 54 to check if the carry creates a new binary digit
        if ((bits & 0x40000000000000L) == 0) {
            // to drop the last bit of mantisa (first discarded)
            bits >>= 1;
            // exponent = 2^(-n+53+bias)
            exponent += discardedSize;
        } else {// #bits = 54
            bits >>= 2;
            exponent += discardedSize + 1;
        }
        // To test if the 53-bits number fits in a 'double'
        if (exponent > 2046) {// (exponent - bias > 1023)
            return ((sign > 0) ? Double.POSITIVE_INFINITY
                    : Double.NEGATIVE_INFINITY);
        }
        // Construct the 64 double bits: [sign(1), exponent(11), mantisa(52)]
        bits = (sign & 0x8000000000000000L) | ((long)exponent << 52) | (bits & 0xFFFFFFFFFFFFFL);
        return Double.longBitsToDouble(bits);
    }
    
}
