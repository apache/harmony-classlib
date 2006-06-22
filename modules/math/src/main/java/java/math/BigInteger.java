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
import java.io.ObjectOutputStream;
import java.util.Random;
import java.io.Serializable;

/**
 * BigInteger objects represent arbitrary precision decimal integers. They
 * contain values that cannot be changed. Thus, most operations on the
 * BigInteger objects yield new instances of BigInteger.
 */
public class BigInteger extends Number implements Comparable<BigInteger>, Serializable {
    private static final int EQUALS = 0;
    private static final int GREATER = 1;
    private static final int LESS = -1;
    static final BigInteger NEG_ONE = new BigInteger (-1, 1);

    // bigRadices values are precomputed maximal powers of radices
    // (integer numbers from 2 to 36) that fit into unsigned int (32 bits).
    // bigRadices[0] = 2 ^ 31, bigRadices[8] = 10 ^ 9, etc.
    private static final int bigRadices[] = {
            -2147483648, 1162261467, 1073741824, 1220703125, -2118184960,
            1977326743,1073741824, 387420489, 1000000000, 214358881, 429981696,
            815730721, 1475789056, 170859375, 268435456, 410338673, 612220032,
            893871739, 1280000000, 1801088541, 113379904, 148035889, 191102976,
            244140625, 308915776, 387420489, 481890304, 594823321, 729000000,
            887503681, 1073741824, 1291467969, 1544804416, 1838265625, 60466176
    };

    static final int tenPows[] = {1,10,100,1000,10000,100000,1000000,
            10000000,100000000,1000000000};
    // the first 54 prime numbers were copied from the published sources,
    // e.g. http://en.wikipedia.org/wiki/List_of_prime_numbers#The_first_500_prime_numbers
    private static final int primes[] = {
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 
            61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 
            131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193,
            197, 199, 211, 223, 227, 229, 233, 239, 241, 251
    };

    /**
     * @com.intel.drl.spec_ref
     */
    private static final long serialVersionUID = -8287574255936472291L; 

    // serialized fields
    /**
     * @com.intel.drl.spec_ref
     */
    private byte[] magnitude;
    
    /**
     * @com.intel.drl.spec_ref
     */
    private int signum;
    
    /**
     * @com.intel.drl.spec_ref
     */
    public static final BigInteger  ONE  = new BigInteger (1, 1);
    
    /**
     * @com.intel.drl.spec_ref
     */
    public static final BigInteger  TEN  = new BigInteger (1, 10); 
    
    /**
     * @com.intel.drl.spec_ref
     */
    public static final BigInteger  ZERO = new BigInteger (0, 0); 

    static final BigInteger  FIVE = new BigInteger (1, 5);

    static final BigInteger[] SMALL_VALUES = {ZERO,ONE,new BigInteger(1,2),
         new BigInteger(1,3), new BigInteger(1,4), FIVE,
         new BigInteger(1,6), new BigInteger(1,7), new BigInteger(1,8),
         new BigInteger(1,9), TEN   };

    private static final BigInteger[] bigTenPows = new BigInteger [32];

    static {

        bigTenPows[0] = ONE;
        bigTenPows[1] = TEN;
        int i=2;
        long val = 100;
        for(;i<=18;i++) {
            bigTenPows[i] = valueOf(val);
            val*=10L;
        }
        for(;i<bigTenPows.length;i++) {
            bigTenPows[i] = bigTenPows[i-1].multiply(BigInteger.TEN);
        }
    }

    /**
     * The magnitude of this in the little-endian representation
     */
    transient int digits[];
    
    /**
     * The length of this in measured in ints. Can be less than digits.length().
     */
    transient int numberLength;
    
    /**
     * The sign of this.
     */
    transient int sign;

    /**
     * Adds two arrays element by element taking carry into consideration.
     * It is assumed that the length of a is not less than the length of b.
     */
    private static int[] add(int a[], int aSize, int b[], int bSize) {
        int i;
        int res[] = new int[aSize + 1];
        long k = ((long)a[0] & 0x0ffffffffL) + ((long)b[0] & 0x0ffffffffL);
        res[0] = (int)k;
        k >>= 32;
        for (i = 1; i < bSize; i++) {
            k += ((long)a[i] & 0x0ffffffffL) + ((long)b[i] & 0x0ffffffffL);
            res[i] = (int)k;
            k >>= 32;
        }
        for (; i < aSize; i++) {
            k += (long)a[i] & 0x0ffffffffL;
            res[i] = (int)k;
            k >>= 32;
        }
        res[i] = (int)k;
        return res;
    }

    static int[] incInPlace(int a[]) {
        int size = a.length;
        long k = ((long)a[0] & 0x0ffffffffL) + 1L;
        a[0]=(int)k;
        k >>= 32;
        int i=1;
        for (; (i < size)&& k!=0; i++) {
            k += (long)a[i] & 0x0ffffffffL;
            a[i] = (int)k;
            k >>= 32;
        }
        if(k!=0 && i==size) {
            int res[] = new int[size + 1];
            for(int j=0; j<size; j++) res[j]=a[j];
            res[i] = (int)k;
            return res;
        } else {
            return a;
        }
    }

    /**
     * Adds an integer value to the array of integers remembering carry. 
     * Returns carry.
     */
    private static int addIntToArray(int intArray[], final int arrayLength,
            final int addend) {
        long carry = (long) addend & 0xffffffffL;
        for (int i = 0; i < arrayLength; i++) {
            carry += ((long) intArray[i] & 0xffffffffL);
            intArray[i] = (int) carry;
            carry >>= 32;
            if (carry == 0) {
                break;
            }
        }
        return (int)carry;
    }

    /**
     * Implements the element by element bitwise operations on two integer arrays.
     * Arrays can be processed partially starting from 0 element.
     * @param dest the result array
     * @param a the first source array
     * @param aLen the number of elements of 'a' to be processed
     * @param b the second source array
     * @param bLen the number of elements of 'b' to be processed
     */
    private static void bitOperation(int dest[], int a[], int aLen, int b[],
            int bLen, int operation) {
        int i;
        for (i = 0; i < bLen; i++) {
            switch (operation) {
                case 1:        // and
                    dest[i] = a[i] & b[i];
                    break;
                case 2:        // not
                    dest[i] = ~b[i];
                    break;
                case 3:        // or
                    dest[i] = a[i] | b[i];
                    break;
                case 4:        // xor
                    dest[i] = a[i] ^ b[i];
                    break;
            }
        }
        while (i < aLen) {
            dest[i] = a[i++];
        }
    }

    /**
     * Compares two arrays.
     * All elements are treated as unsigned integers.
     * The magnitude is the bit chain of elements in big-endian order.
     * @param a the first array
     * @param b the second array
     * @param size the size of arrays
     * @return 1 if a > b, -1 if a < b, 0 if a == b
     */
    static int compareArrays(final int[] a, final int[] b, final int size) {
        for (int i = size - 1; i >= 0; i--) {
            long aValue = ((long)a[i] & 0x0ffffffffL);
            long bValue = ((long)b[i] & 0x0ffffffffL);
            if (aValue < bValue) {
                return LESS;
            }
            if (aValue > bValue) {
                return GREATER;
            }
        }
        return EQUALS;
    }

    /**
     * Counts leading zero bits in an integer value
     */
    private static int countLeadingZeroBits(int value) {
        int i;
        for (i = 0; i < 32; i++) {
            if (value == 0) {
                break;
            }
            value >>= 1;
        }
        return 32 - i;    
    }

    /**
     * Divides the array 'a' by the array 'b' and gets the quotient and the remainder.
     * Implements the Knuth's division algorithm.
     * See D. Knuth, The Art of Computer Programming, vol. 2.
     * Steps D1-D8 correspond the steps in the algorithm description.
     * @param quot the quotient
     * @param quotLength the quotient's length
     * @param a the dividend
     * @param aLength the dividend's length
     * @param b the divisor
     * @param bLength the divisor's length
     * @return the remainder
     */
    static int[] divide(int quot[], int quotLength, int a[],
            int aLength, int b[], int bLength) {
        int normA[] = new int[aLength + 1];    // the normalized dividend
        // an extra byte is needed for correct shift
        int normB[] = new int[bLength + 1];    // the normalized divisor; 
        int normBLength = bLength;            

        // Step D1: normalize a and b and put the results to a1 and b1
        // the normalized divisor's first digit must be >= 2^31
        
        int divisorShift = countLeadingZeroBits(b[bLength - 1]);
        if (divisorShift != 0) {
            shiftLeft(normB, b, 0, divisorShift);
            shiftLeft(normA, a, 0, divisorShift); 
        } else {
            System.arraycopy(a, 0, normA, 0, aLength);
            System.arraycopy(b, 0, normB, 0, bLength);
        }
        int firstDivisorDigit = normB[normBLength - 1];
        
        // Step D2: set the quotient index
        int i = quotLength - 1;    
        int j = aLength;
        
        while (i >= 0) {
            // Step D3: calculate a guess digit guessDigit
            int guessDigit = 0;
            if (normA[j] == firstDivisorDigit) {
                // set guessDigit to the largest unsigned int value
                guessDigit = -1;
            } else {
                long product = ((((long) normA[j] & 0xffffffffL) << 32) + 
                        ((long) normA[j - 1] & 0xffffffffL));
                long res = divideLongByInt(product, firstDivisorDigit);
                guessDigit = (int)res;        // the quotient of divideLongByInt 
                int rem = (int)(res >> 32); // the remainder of divideLongByInt
                // decrease guessDigit by 1 while leftHand > rightHand 
                if (guessDigit != 0) {
                    long leftHand = 0;
                    long rightHand = 0;
                    boolean rOverflowed = false;
                    guessDigit++; // to have the proper value in the loop below
                    do {
                        guessDigit--;
                        if (rOverflowed) {
                            break;
                        }
                        // leftHand always fits in an unsigned long 
                        leftHand = ((long)guessDigit & 0xffffffffL) * 
                            ((long)normB[normBLength - 2] & 0xffffffffL);
                        // rightHand can overflow; in this case the loop condition 
                        // will be true in the next step of the loop
                        rightHand = ((long)rem << 32) + 
                            ((long)normA[j - 2] & 0xffffffffL);
                        long longR = ((long)rem & 0xffffffffL) + 
                            ((long)firstDivisorDigit & 0xffffffffL);
                    
                        // check that longR does not fit in an unsigned int;
                        // this ensures that rightHand will overflow unsigned long
                        // in the next step
                        if (countLeadingZeroBits((int)(longR >>> 32)) < 32) {
                            rOverflowed = true;
                        } else {
                            rem = (int)longR;
                        }
                    } while (((leftHand ^ 0x8000000000000000L) > 
                            (rightHand ^ 0x8000000000000000L)));
                }
            }
            
            // Step D4: multiply normB by guessDigit 
            // and subtract the production from normA.
            if (guessDigit != 0) {
                int borrow = multiplyAndSubtract(normA, j - normBLength, normB,
                        normBLength, (long) guessDigit & 0xffffffffL);

                // Step D5: check the borrow
                if (borrow != 0) {
                    // Step D6: compensating addition
                    guessDigit--;
                    long carry = 0;
                    for (int k = 0; k < normBLength; k++) {
                        carry += ((long)normA[j - normBLength + k] & 0xffffffffL) + 
                            ((long)normB[k] & 0xffffffffL);
                        normA[j - normBLength + k] = (int)carry;
                        carry >>>= 32;
                    }
                }
            }
            if (quot != null) {        
                quot[i] = (int)guessDigit;
            }
            // Step D7
            j--;
            i--;
        }
        // Step D8: we got the remainder in normA.
        // Denormalize it id needed 
        if (divisorShift != 0) {
            shiftRight(normB, normBLength, normA, 0, divisorShift); // reuse normB
            return normB;
        } else {
            System.arraycopy(normA, 0, normB, 0, bLength);
            return normA;
        }
    }

    /**
     * Divides an array by an integer value.
     * Implements the Knuth's division algorithm.
     * See D. Knuth, The Art of Computer Programming, vol. 2.
     * @param dest the quotient
     * @param src the dividend
     * @param srcLength the length of the dividend
     * @param divisor the divisor
     * @return remainder
     */
    static int divideArrayByInt(int dest[], int src[],
            final int srcLength, final int divisor) {
        long rem = 0;
        long bLong = (long)divisor & 0xffffffffL;
        for (int i = srcLength - 1; i >= 0; i--) {
            long temp = (rem << 32) | ((long)src[i] & 0xffffffffL);
            long quot;
            if (temp >= 0) {
                quot = (temp / bLong);
                rem = (temp % bLong);
            } else {
                // make the dividend positive shifting it right by 1 bit
                // then get the quotient an remainder and correct them properly
                long aPos = temp >>> 1;
                long bPos = divisor >>> 1;
                quot = aPos / bPos;
                rem = aPos % bPos;
                // double the remainder and add 1 if a is odd
                rem = (rem << 1) + (temp & 1);
                if ((divisor & 1) != 0) { // the divisor is odd
                    if (quot <= rem) {
                        rem -= quot;
                    } else {
                        if (quot - rem <= bLong) {
                            rem += bLong - quot;
                            quot -= 1;
                        } else {
                            rem += (bLong << 1) - quot;
                            quot -=2;
                        }
                    }
                }
            }
            dest[i] = (int)(quot & 0xffffffffL);
        }
        return (int)rem;
    }

    /**
     * Divides an array by an integer value.
     * Implements the Knuth's division algorithm.
     * See D. Knuth, The Art of Computer Programming, vol. 2.
     * @param src the dividend
     * @param srcLength the length of the dividend
     * @param divisor the divisor
     * @return remainder
     */
    private static int remainderArrayByInt(int src[],
            final int srcLength, final int divisor) {
        long result = 0;
        for (int i = srcLength - 1; i >= 0; i--) {
            long temp = (result << 32) + ((long)src[i] & 0xffffffffL); 
            long res = divideLongByInt(temp, divisor);
            result = (int)(res >> 32);
        }
        return (int)result;
    }
    
    /**
     * Divides an unsigned long a by an unsigned int b
     * It is supposed that the most significant bit of b is set to 1, i.e. b < 0
     * @param a the dividend
     * @param b the divisor
     * @return the long value containing the unsigned integer remainder 
     *     in the left half and the unsigned integer quotient in the right half
     */
    private static long divideLongByInt(long a, int b) {
        long quot;
        long rem;
        long bLong = (long)b & 0xffffffffL;
        if (a >= 0) {
            quot = (a / bLong);
            rem = (a % bLong);
        } else {
            // make the dividend positive shifting it right by 1 bit 
            // then get the quotient an remainder and correct them properly
            long aPos = a >>> 1;
            long bPos = b >>> 1;
            quot = aPos / bPos;
            rem = aPos % bPos;
            // double the remainder and add 1 if a is odd
            rem = (rem << 1) + (a & 1);
            if ((b & 1) != 0) { // the divisor is odd
                if (quot <= rem) {
                    rem -= quot;
                } else {
                    if (quot - rem <= bLong) {
                        rem += bLong - quot;
                        quot -= 1;
                    } else {
                        rem += (bLong << 1) - quot;
                        quot -=2;
                    }
                }
            }
        }
        return (rem << 32) | (quot & 0xffffffffL);
    }

    /**
     * Gets the number of radix-based digits that fits in an integer
     * @param radix the radix of the numerical system
     * @return the number of digits 
     */
    private static int getCharsPerInt(final int radix) {
        if (radix > 8) {
            if (radix <= 10) {
                return 9;            // 9 and 10
            } else if (radix <= 14) {
                return 8;            // 11 - 14
            } else if (radix <= 21) {
                return 7;            // 15 - 21
            } else if (radix <= 35){
                return 6;            // 22 - 35
            } else {
                return 5;            // 36
            }
        } else if (radix == 2) {
            return 31;
        } else if (radix == 3) {
            return 19;
        } else if (radix == 4) {
            return 15;
        } else if (radix <= 7) {    // 5, 6, and 7
            return 18 - radix;
        } else {
            return 10;                // 8
        }
    }

    /**
     * Multipies an array by int and subtracts it from a subarray of another array.
     * @param a the array to subtract from
     * @param start the start element of the subarray of a
     * @param b the array to be multiplied and subtracted
     * @param bLen the length of b
     * @param c the multiplier of b
     * @return the carry element of subtraction
     */
    private static int multiplyAndSubtract(int a[], int start, int b[],
            int bLen, long c) {
        int i;
        int carry = 0;
        long product;
        for (i = 0; i < bLen; i++) {
            product = c * ((long)b[i] & 0xffffffffL);
            int productInt = (int)product;
            productInt += carry;
            carry = (int)(product >> 32) + ((productInt ^ 0x80000000) <
                    (carry ^ 0x80000000) ? 1 : 0);
            productInt = a[start + i] - productInt;
            if ((productInt ^ 0x80000000) > (a[start + i] ^ 0x80000000)) {
                carry++;
            }
            a[start + i] = productInt;
        }

        product = ((long)a[start + i] & 0xffffffffL) - ((long)carry  & 0xffffffffL);
        a[start + i] = (int)product;
        carry = (int)(product >> 32); // -1 or 0

        return carry;
    }

    /**
     * Multiplies an array of integers by an integer value.
     * @param intArray the array of integers
     * @param arrayLength the number of elements of intArray to be multiplied
     * @param factor the multiplier
     * @return the top digit of production
     */
    private static int multiplyByInt(int intArray[], final int arrayLength,
            final int factor) {
        long carry = 0;
        for (int i = 0; i < arrayLength; i++) {
            carry += ((long) intArray[i] & 0xffffffffL)
                    * ((long) factor & 0xffffffffL);
            intArray[i] = (int) carry;
            carry >>>= 32;
        }
        return (int) carry;
    }

    /**
     * Converts bits represented by an int array 
     * from two's complementary code to true one and vice versa.
     * Performs ~value + 1. 
     */
    private static int setTrueCoded(int arr[], final int length) {
        int i;
        long carry = 1;
        for (i = 0; i < length; i++) {
            carry += (long)(~arr[i]) & 0xffffffffL;
            arr[i] = (int)carry;
            carry >>= 32;
            if (carry == 0) {
                break;
            }
        }
        for(++i; i < length; i++) {
            arr[i] = ~arr[i];
        }
        return (int)carry;
    }

    /**
     * Shifts left an array of integers.
     * Total shift distance in bits is intCount * 32 + count
     * @param result the destination array
     * @param source the source array
     * @param intCount the shift distance in integers
     * @param count an additional shift distance in bits
     */
    private static void shiftLeft(int result[], int source[], int intCount,
            int count) {
        int i;
        if (count == 0) {
            for (i = result.length - 1; i >= intCount; i--) {
                result[i] = source[i - intCount];
            }
        } else {
            result[result.length - 1] = 0;
            int rightShiftCount = 32 - count;
            for (i = result.length - 1; i > intCount; i--) {
                result[i] |= source[i - intCount - 1] >>> rightShiftCount;
                result[i - 1] = source[i - intCount - 1] << count;
            }
        }
    }

    /**
     * Shifts right an array of integers. 
     * Total shift distance in bits is intCount * 32 + count.
     * @param result the destination array
     * @param resultLen the destination array's length
     * @param source the source array
     * @param intCount the number of elements to be shifted
     * @param count the number of bits to be shifted
     */
    private static void shiftRight(int result[], int resultLen, int source[],
            int intCount, int count) {
        int i = 0;
        if (count == 0) {
            for (i = 0; i < resultLen; i++) {
                result[i] = source[i + intCount];
            }
        } else {
            int leftShiftCount = 32 - count;
            while (i < resultLen - 1) {
                result[i] = source[i + intCount] >>> count | source[i + intCount + 1] << leftShiftCount;
                i++;
            }
            result[i] = source[i + intCount] >>> count;
        }
    }

    /**
     * Subtracts the value represented by b from the value represented by a.
     * It is assumed that the magnitude of a is not less than the magnitude of b.
     */
    private static int[] subtract(int a[], int aSize, int b[], int bSize) {
        int i;
        int res[] = new int[aSize]; 
        long carry = 0;
        for (i = 0; i < bSize; i++) { 
            carry += ((long)a[i] & 0x0ffffffffL) - ((long)b[i] & 0x0ffffffffL);
            res[i] = (int)carry;
            carry >>= 32; // -1 or 0
        }
        for (; i < aSize; i++) { 
            carry +=  ((long)a[i] & 0x0ffffffffL);
            res[i] = (int)carry;
            carry >>= 32; // -1 or 0
        }
        return res;
    }

    /**
     * Answers a new instance of this class whose value is greater
     * than zero, length is the given number of bits, and whose
     * likeyhood of being prime is not less than 2 ^ -100.
     * 
     * @param length
     *            the number of bits contained in the returned instance.
     * @param rand
     *            a random source of bits for selection of the probable prime.
     * @return the probable prime number.
     */
    public static BigInteger probablePrime(int length, Random rand) {
        return new BigInteger(length, 100, rand);
    }

    /**
     * Answers a BigInteger with the same value as longValue
     * 
     * @return BigInteger (BigInteger) longValue
     */
    public static BigInteger valueOf(long longValue) {
        if (longValue < 0) {
            longValue = -longValue;
            int valueLo = (int) longValue;
            int valueHi = (int) (longValue >>> 32);
            if (valueHi == 0) {
                return new BigInteger(-1, valueLo);
            } else {
                return new BigInteger(-1, 2, new int[]{valueLo, valueHi});
        }
        } else if (longValue <= 10) {
            return SMALL_VALUES[(int) longValue];
        } else {
            int valueLo = (int) longValue;
            int valueHi = (int) (longValue >> 32);
            if (valueHi == 0) {
                return new BigInteger(1, valueLo);
            } else {
                return new BigInteger(1, 2, new int[]{valueLo, valueHi});
            }
        }
    }

    BigInteger (int sign, int value) {
        this.sign = sign;
        numberLength = 1;
        digits = new int[1];
        this.digits[0] = value;
    }

    BigInteger (int sign, int length, int[] value) {
        this.sign = sign;
        numberLength = length;
        digits = value;
    }

    /**
     * Constructs a new instance of this class given an array
     * containing bytes representing the bit pattern for the
     * answer.
     * 
     * @param byteValues
     *            byte[] the bits of the value of the new instance.
     */
    public BigInteger(byte[] byteValues) {
        if (byteValues.length == 0) {
            throw new NumberFormatException("zero length array");
        }
        boolean isNegative = byteValues[0] < 0;
        sign = 1;
        putBytesToIntegers(byteValues, isNegative);
        if (isNegative) {
            sign = -1;
            setTrueCoded(digits, numberLength);
        }        
        cutOffLeadingZeroes();
    }

    /**
     * Constructs a new instance of this class given an array
     * containing bytes representing the bit pattern for the
     * answer, and a sign flag.
     * 
     * @param signum
     *            int the sign of the result.
     * @param byteValues
     *            byte[] the bits of the value of the new instance.
     */
    public BigInteger(int signum, byte[] byteValues) {
        if (signum < -1 || signum > 1) {
            throw new NumberFormatException("sign must be -1, 0, or 1");
        }
        if (signum == 0) {
            for (int i = 0; i < byteValues.length; i++) {
                if (byteValues[i] != 0) {
                    throw new NumberFormatException(
                            "zero sign with non-zero magnitude");
                }
            }
        }
        if (byteValues.length != 0) {
            sign = signum;
            boolean createAsNegative = true;
            putBytesToIntegers(byteValues, !createAsNegative);
            cutOffLeadingZeroes();
            return;
        } 
        sign = 0;
        numberLength = 1;
        digits = new int[1];
        digits[0] = 0;
    }

    /**
     * Constructs a new instance of this class of the specified
     * length, whose content is produced by acquiring random bits
     * from the specified random number generator.
     * 
     * @param numberBitLength
     *            int the number of bits to have in the result.
     * @param rand
     *            Random the generator to produce the bits.
     */
    public BigInteger(int numberBitLength, Random rand) {
        if (numberBitLength < 0) {
            throw new IllegalArgumentException("negative bit length");
        }
        boolean stripLeadingZeroes = numberBitLength > 0;
        constructRandomly(numberBitLength, rand, stripLeadingZeroes);
    }

    /**
     * Constructs a new instance of this class of the specified
     * length, whose content is produced by acquiring random bits
     * from the specified random number generator. The result is
     * guaranteed to be prime up to the given degree of certainty.
     * 
     * @param numberBitLength
     *            int the number of bits to have in the result.
     * @param certainty
     *            int the degree of certainty required that the result is prime.
     * @param rand
     *            Random the generator to produce the bits.
     */
    public BigInteger(int numberBitLength, int certainty, Random rand) {
        if (numberBitLength < 2) {
            throw new ArithmeticException("bit length is less than 2");
        }
        boolean stripLeadingZeroes = true;
        do {
            constructRandomly(numberBitLength, rand, !stripLeadingZeroes);

            // set the high-order bit to 1 to ensure 
            // that the number is of the required length
            int numberOfBitsInHighInteger = numberBitLength & 0x1f;
            digits[numberLength - 1] |= 1 << (numberOfBitsInHighInteger - 1);
            if (numberBitLength == 2) {
                return;
            }
            // set the low-order bit to 1 to ensure that the number is odd
            digits[0] |= 1;    
        } while (!isProbablePrime(certainty));
    }

    /**
     * Constructs a new instance of this class given a string containing a
     * representation of a decimal number.
     * 
     * @param value
     *            String the decimal digits of the answer.
     */
    public BigInteger(String value) {
        this(value, 10);
    }

    /**
     * Constructs a new instance of this class given a string
     * containing digits in the specified radix.
     * 
     * @param stringValue
     *            String the digits of the answer.
     * @param radix
     *            int the radix to use for conversion.
     */
    public BigInteger(String stringValue, int radix) {
        if (radix < Character.MIN_RADIX || radix > Character.MAX_RADIX) {
            throw new NumberFormatException("radix is out of range");
        }
        int stringLength = stringValue.length();
        if (stringLength == 0) {
            throw new NumberFormatException("zero length string");
        }
        int startChar;
        int endChar = stringLength;
        if (stringValue.charAt(0) == '-') {
            sign = -1;
            startChar = 1;
            stringLength--;
        } else {
            sign = 1;
            startChar = 0;
        }
        
        // We use the following algorithm: split a string into portions 
        // of n characters and convert each portion to an integer
        // according to the radix.
        // Then convert an exp(radix, n) based number to binary using 
        // the multiplication method.
        // See D. Knuth, The Art of Computer Programming, vol. 2.

        int charsPerInt = getCharsPerInt(radix);
        int bigRadixDigitsLength = stringLength / charsPerInt;
        int topChars = stringLength % charsPerInt;
        if (topChars != 0 ) {
            bigRadixDigitsLength++;
        }
        digits = new int[bigRadixDigitsLength];
        // get the maximal power of radix that fits in int
        int bigRadix = bigRadices[radix - 2];

        // parse an input string and accumulate the BigInteger's magnitude

        int digitIndex = 0; // index of digits array
        int substrEnd = startChar + ((topChars == 0) ? charsPerInt : topChars);
        int newDigit;
        for (int substrStart = startChar; substrStart < endChar; 
                substrStart = substrEnd, 
                substrEnd = substrStart + charsPerInt) {
            int bigRadixDigit = 
                Integer.parseInt(stringValue.substring(substrStart, substrEnd),
                                 radix);
            newDigit = multiplyByInt(digits, digitIndex, bigRadix);
            newDigit += addIntToArray(digits, digitIndex, bigRadixDigit);
            digits[digitIndex++] = newDigit;
        }
        numberLength = digitIndex;
        cutOffLeadingZeroes();
    }

    /**
     * Answers the absolute value of the receiver
     * 
     * @return BigInteger absolute value of the receiver
     */
    public BigInteger abs() {
        if (this.sign >= 0) {
            return this;
        }
        return new BigInteger(1, numberLength, digits);
    }

    /**
     * Answers the sum of the receiver and a BigInteger
     * 
     * @param that
     *            a BigInteger to add
     * 
     * @return BigInteger this + that
     */
    public BigInteger add(BigInteger that) {
        int resDigits[];
        int resSign;
        int thisSign = this.sign;
        int thatSign = that.sign;
        if (thisSign == 0 ) {
            return that;
        }
        if(thatSign == 0){
            return this;
        }
        int thisLen = this.numberLength;
        int thatLen = that.numberLength;
        if(thisLen + thatLen == 2) {
            long a = ((long) this.digits[0] & 0xffffffffL);
            long b = ((long) that.digits[0] & 0xffffffffL);
            long res;
            if (thisSign == thatSign) {
                res = a+b;
                int valueLo = (int) res;
                int valueHi = (int) (res >>> 32);
                if (valueHi == 0) {
                    return new BigInteger(thisSign, valueLo);
                } else {
                    return new BigInteger(thisSign, 2, new int[]{valueLo, valueHi});
                }
            } else {
                if (thisSign < 0 ) {
                    res = b-a;
                } else {
                    res = a-b;
                }
                return valueOf( res );
            }
        } else if (thisSign == thatSign) {
            resSign = thisSign;
            // an augend should not be shorter than addend
            if (thisLen >= thatLen) {
                resDigits = add(this.digits, thisLen,
                                that.digits, thatLen);
            } else {
                resDigits = add(that.digits, thatLen,
                                this.digits, thisLen);
            }
        } else {    // signs are different

            int cmp = (thisLen != thatLen ?
                       ((thisLen > thatLen) ? 1 : -1) :
                       compareArrays(this.digits,that.digits,thisLen ));

            if (cmp == EQUALS) {
                return ZERO;
            }
            // a minuend should not be shorter than subtrahend
            if (cmp == GREATER) {
                resSign = thisSign;
                resDigits = subtract(this.digits, thisLen,
                                     that.digits, thatLen);
            } else {
                resSign = thatSign;
                resDigits = subtract(that.digits, thatLen,
                                     this.digits, thisLen);
            }
        }
        BigInteger res = new BigInteger(resSign, resDigits.length, resDigits);
        res.cutOffLeadingZeroes();
        return res;
    }

    /**
     * Answers the bitwise AND of the receiver and the argument.
     * 
     * @param that
     *            BigInteger the value to AND.
     * @return BigInteger this & that
     */
    public BigInteger and(BigInteger that) {
        if (this.sign == 0 || that.sign == 0) {
            return ZERO;
        }
        int resSign = (this.sign == -1 && that.sign == -1) ? -1 : 1;
        int resLength;
        int resDigits[];
        int thisNeg[] = null;
        int thatNeg[] = null;
        if (this.sign == -1) {
            thisNeg = new int[this.numberLength]; 
            System.arraycopy(this.digits, 0, thisNeg, 0, this.numberLength);
        }
        if (that.sign == -1) {
            thatNeg = new int[that.numberLength];
            System.arraycopy(that.digits, 0, thatNeg, 0, that.numberLength);
        }
        // some cases when either this or that is positive
        if (this.sign == 1 || that.sign == 1) {
            if (this.sign == 1 && that.sign == 1) { 
                // both are positive;
                // the number of digits to AND is the length of the shorter array 
                int andLen = this.numberLength > that.numberLength ? that.numberLength :
                        this.numberLength;
                resLength = andLen;
                resDigits = new int[resLength];
                bitOperation(resDigits, this.digits, andLen, that.digits,
                        andLen, 1);
            } else {    
                if (this.numberLength > that.numberLength) {
                    // this is longer than that
                    if (this.sign == 1) {
                        // this is positive and that is negative;
                        // all digits should be and'ed
                        resLength = this.numberLength;
                        resDigits = new int[resLength];
                        setTrueCoded(thatNeg, that.numberLength);
                        bitOperation(resDigits, this.digits,
                                this.numberLength, thatNeg, that.numberLength, 1);
                    } else {
                        // this is negative and that is positive 
                        // the number of digits to AND is the length of the 'that' array 
                        resLength = that.numberLength;
                        resDigits = new int[resLength];
                        setTrueCoded(thisNeg, this.numberLength);
                        bitOperation(resDigits, thisNeg, that.numberLength,
                                that.digits, that.numberLength, 1);
                    }
                } else {
                    // this is shorter than that
                    if (this.sign == 1) {
                        // this is positive and that is negative;
                        // the number of digits to AND is the length of the 'this' array 
                        resLength= this.numberLength; // + 1?
                        resDigits = new int[resLength];
                        setTrueCoded(thatNeg, that.numberLength);
                        bitOperation(resDigits, thatNeg, this.numberLength,
                                this.digits, this.numberLength, 1);
                    } else {
                        // this is negative and that is positive
                        // the number of digits to AND is the length of the 'that' array 
                        resLength = that.numberLength;
                        resDigits = new int[resLength];
                        setTrueCoded(thisNeg, this.numberLength);
                        bitOperation(resDigits, that.digits,
                                that.numberLength, thisNeg, this.numberLength, 1);
                    }
                }
            }
        } else {
            // cases when both numbers are negative or
            // either this or that is positive but both are of the same length
            if (this.sign == -1) {
                setTrueCoded(thisNeg, this.numberLength);
            }
            if (that.sign == -1) {
                setTrueCoded(thatNeg, that.numberLength);
            }
            // The resulting length should have one extra element
            // for possible carry = 1 returned from setTrueCoded for a negative number
            resLength = this.numberLength >= that.numberLength ? this.numberLength + 1 :
                    that.numberLength + 1;
            resDigits = new int[resLength];
            if (this.numberLength >= that.numberLength) {
                bitOperation(resDigits, thisNeg == null ? this.digits : thisNeg, this.numberLength,
                    thatNeg == null ? that.digits : thatNeg, that.numberLength, 1);
            } else {
                bitOperation(resDigits, thatNeg == null ? that.digits : thatNeg, that.numberLength,
                    thisNeg == null ? this.digits : thisNeg, this.numberLength, 1);
            }
        }
        if (resSign == -1) {
            resDigits[resLength - 1] = setTrueCoded(resDigits, resLength);
        }
        BigInteger result = new BigInteger(resSign, resLength, resDigits);
        result.cutOffLeadingZeroes();
        return result;
    } 

    /**
     * Answers the bitwise NAND of the receiver and the argument.
     * 
     * @param that
     *            BigInteger the value to NAND.
     * @return BigInteger this & NOT(that)
     */
    public BigInteger andNot(BigInteger that) {
        return and(that.not());
    } 

    /**
     * Answers the number of set bits in the receiver.
     * 
     * @return int the receiver's bit count.
     */
    public int bitCount() {
        if (sign == 0) {
            return 0;
        }
        boolean isNegative = true;
        if (sign == -1) {
            int negDigits[] = new int[numberLength];
            System.arraycopy(digits, 0, negDigits, 0, numberLength);
            setTrueCoded(negDigits, numberLength);
            return bitCount(negDigits, isNegative);
        } else {
            return bitCount(digits, !isNegative);
        }
    }

    /**
     * Counts bits different from the number's sign
     */
    private int bitCount(int array[], boolean isNegative) {
        int result = 0;
        for (int i = 0; i < numberLength; i++) {
            int bitsInIntCount = 0;
            int unitsCount = 0;
            int one = 1;
            while (bitsInIntCount < 32) {
                if ((array[i] & one) == one) {
                    unitsCount++;
                }
                one <<= 1;
                bitsInIntCount++;
            }
            result += isNegative ? 32 - unitsCount : unitsCount;
        }
        return result;
    }

    /**
     * Answers the length in bits of the receiver.
     * 
     * @return int the receiver's bit length.
     */
    public int bitLength() {
        if (sign == 0) {
            return 0;
        }
        int highDigitIndex = numberLength - 1;
        int highDigit = digits[highDigitIndex];
        int highDigitLength;
        int correction = 0;
        if (sign == -1) {
            // if this is a power of 2, we should decrease the count by 1
            int i;
            for (i = 0; i < 32; i++) {
                if ((highDigit & (1 << i)) == highDigit) {
                    correction = -1;
                    break;
                }
            }
            for (i = 0; i < highDigitIndex && correction == -1; i++) {
                if (digits[i] != 0) {
                    correction = 0;
                }
            }
        }
        // count the bits in the high digit
        for (highDigitLength = 1; highDigitLength < 32; highDigitLength++) {
            highDigit >>>= 1;
            if (highDigit == 0) {
                break;
            }
        }
        return (highDigitLength + (highDigitIndex << 5) + correction);
    }

    /**
     * Changes a bit in the BigInteger depending on the operation.
     * @param intCount the number of an element in the array  to change bit in
     * @param bitNumber the bit's number in the intCount element
     * @param operation's code: 1 for flipBit(), 2 for setBit(), 3 for clearBit()
     */
    private BigInteger changeBit(final int intCount, int bitNumber,
            final int operation) {
        int resSign = sign == 0 ? 1 : sign;
        int resLength;
        if (intCount >= numberLength) {
            resLength = intCount;
        } else {
            resLength = numberLength;
        }
        // increase length for a possible carry in the second setTrueCoded();
        resLength++; 
        int resDigits[] = new int[resLength];
        for (int i = this.numberLength; i < resLength; i++) {
            resDigits[i] = 0;
        }
        System.arraycopy(digits, 0, resDigits, 0, numberLength);
        if (sign == -1) {
            setTrueCoded(resDigits, resLength);
        }
        bitNumber = 1 << bitNumber;
        switch (operation) {
            case 1:        // flip bit
                resDigits[intCount] ^= bitNumber;
                break;
            case 2:        // set bit
                resDigits[intCount] |= bitNumber;
                break;
            case 3:        // clear bit
                resDigits[intCount] &= ~bitNumber;
                break;
        }
        if (sign == -1) {
            setTrueCoded(resDigits, resLength);
        }
        BigInteger result = new BigInteger(resSign, resLength, resDigits);
        result.cutOffLeadingZeroes();
        return result;
    }

    /**
     * Unsets the specified bit in the receiver.
     * 
     * @param bitNumber
     *            int the bit to clear.
     */
    public BigInteger clearBit(int bitNumber) {
        if (bitNumber < 0) {
            throw new ArithmeticException("negative bit number");
        }
        int intCount = bitNumber >> 5; 
        bitNumber &= 0x1f;
        if (sign == 0 || (sign == 1 && intCount >= numberLength)) {
            return this;
        }
        return changeBit(intCount, bitNumber, 3);
    }

    /**
     * Answers an integer indicating the relative positions of the receiver and
     * the argument in the natural order of elements of the receiver's class.
     * 
     * @return int which should be <0 if the receiver should sort before the
     *         argument, 0 if the receiver should sort in the same position as
     *         the argument, and >0 if the receiver should sort after the
     *         argument.
     * @param that
     *            BigInteger an object to compare the receiver to
     * @exception ClassCastException
     *                if the argument can not be converted into something
     *                comparable with the receiver.
     */
    public int compareTo(BigInteger that) {
        int thisSign = this.sign;
        int thatSign = that.sign;
        if (thisSign == 0) {
            return -thatSign;
        }
        if (thisSign == thatSign) {
            int thisLen = this.numberLength;
            int thatLen = that.numberLength;
            int compareResult = (thisLen != thatLen ?
                       ((thisLen > thatLen) ? 1 : -1) :
                       compareArrays(this.digits,that.digits,thisLen ));

            if (thisSign == 1) {
                return compareResult;
            } else { 
                return -compareResult;
            }            
        }
        return thisSign;
    }

    /**
     * Constructs randomly a big integer of the specified bit length.
     * @param numberBitLength the length in bits
     * @param rand source of random bits
     */
    private void constructRandomly(int numberBitLength, Random rand,
            boolean stripLeadingZeroes) {
    	sign = numberBitLength == 0 ? 0 : 1;
        int numberOfDigits = numberBitLength >> 5;
        int numberOfTopBits = numberBitLength & 31;
        if (numberOfTopBits > 0) {
            numberOfDigits++;
        }
        numberLength = numberOfDigits;
        digits = new int[numberOfDigits];
        while (--numberOfDigits >= 0) {
            digits[numberOfDigits] = rand.nextInt();
        }
        if (numberOfTopBits > 0) {
            digits[numberLength - 1] &= ((1 << numberOfTopBits) - 1);
        }
        if (stripLeadingZeroes) {
            cutOffLeadingZeroes();
        }
    }

    /**
     * Decreases numberLength if there are zero high elements.
     */
    final void cutOffLeadingZeroes() {
        while(numberLength > 0 && digits[--numberLength] == 0) {
        }
        if (digits[numberLength++] == 0) {
            sign = 0;
        }
    }

    /**
     * Answers the quotient of the receiver and a BigInteger.
     * 
     * @param that
     *            BigInteger the value to divide
     * @return BigInteger this / that
     * 
     * @exception ArithmeticException
     *                if that is zero.
     */
    public BigInteger divide(BigInteger that) {
        if (that.isZero()) {
            throw new ArithmeticException("division by zero");
        }
        int thatSign = that.sign;
        if (that.isOne()) {
            if (thatSign == 1) {
                return this;
            } else {
                return this.negate();
            }
        }
        int thisSign = this.sign;
        int thisLen = this.numberLength;
        int thatLen = that.numberLength;
        if (thisLen + thatLen == 2) {
            long val = ((long)this.digits[0] & 0xffffffffL)
                    / ((long)that.digits[0] & 0xffffffffL);
            if(thisSign != thatSign) {
                val = -val;
            }
            return valueOf( val );
        } else {
            int cmp = (thisLen != thatLen ?
                       ((thisLen > thatLen) ? 1 : -1) :
                       compareArrays(this.digits,that.digits,thisLen ));

        if (cmp == EQUALS) {
                return ((thisSign == thatSign) ? ONE : NEG_ONE);
        }
        if (cmp == LESS) {
            return ZERO;
        }
            int resLength = thisLen - thatLen + 1;
        int resDigits[] = new int[resLength];
            int resSign = ((thisSign == thatSign) ? 1 : -1);
            if (thatLen == 1) {
                divideArrayByInt(resDigits, this.digits, thisLen,
                    that.digits[0]);
        } else {
                divide(resDigits, resLength, this.digits, thisLen,
                       that.digits, thatLen);
        }
        BigInteger result = new BigInteger(resSign, resLength, resDigits); 
        result.cutOffLeadingZeroes();
        return result;
    }
    }

    /**
     * Answers the quotient and remainder of the receiver divided by a
     * BigInteger.
     * 
     * @param that
     *            BigInteger the value to divide.
     * @return BigInteger[2] {this / that, this % that )}
     * 
     * @exception ArithmeticException
     *                if val is zero.
     */
    public BigInteger[] divideAndRemainder(BigInteger that) {
        int thatSign = that.sign;
        if (thatSign==0) {
            throw new ArithmeticException("division by zero");
        }
        int thatLen = that.numberLength;
        int[] thatDigits = that.digits;
        if(thatLen == 1) {
            return divideAndRemainderByInteger(thatDigits[0],thatSign);
        }
        // res[0] is a quotient and res[1] is a remainder:
        int[] thisDigits = this.digits;
        int thisLen = this.numberLength;
        int cmp = (thisLen != thatLen ?
                   ((thisLen > thatLen) ? 1 : -1) :
                    compareArrays(thisDigits,thatDigits,thisLen ));
        if ( cmp < 0 ) {
            return new BigInteger[]{ZERO, this};
        } else {
            int thisSign = this.sign;
            int quotientLength = thisLen - thatLen + 1;
            int remainderLength = thatLen;
            int quotientSign = ((thisSign == thatSign) ? 1 : -1);
            int quotientDigits[] = new int[quotientLength];
            int remainderDigits[] = divide(quotientDigits, quotientLength,
                                         thisDigits, thisLen,
                                         thatDigits, thatLen);
            BigInteger result0 = new BigInteger(quotientSign, quotientLength,
                                                quotientDigits);
            BigInteger result1 = new BigInteger(thisSign, remainderLength,
                                                remainderDigits);
            result0.cutOffLeadingZeroes();
            result1.cutOffLeadingZeroes();
            return new BigInteger[]{result0, result1};
        }
    }

    BigInteger[] divideAndRemainderByInteger(int that, int thatSign) {
        // res[0] is a quotient and res[1] is a remainder:
        int[] thisDigits = this.digits;
        int thisLen = this.numberLength;
        int thisSign = this.sign;
        if (thisLen == 1) {
            long a = ((long) thisDigits[0] & 0xffffffffL);
            long b = ((long) that & 0xffffffffL);
            long quo = a / b;
            long rem = a % b;
            if (thisSign != thatSign) {
                quo = -quo;
            }
            if (thisSign <0 ) {
                rem = -rem;
            }
            return new BigInteger[]{valueOf(quo), valueOf(rem)};
        } else {
            int quotientLength = thisLen ;
            int quotientSign = ((thisSign == thatSign) ? 1 : -1);
            int quotientDigits[] = new int[quotientLength];
            int remainderDigits[];
            remainderDigits = new int[]{divideArrayByInt(quotientDigits,
                                                             thisDigits, thisLen, that)};
            BigInteger result0 = new BigInteger(quotientSign, quotientLength,
                                                quotientDigits);
            BigInteger result1 = new BigInteger(thisSign, 1,
                                                remainderDigits);
            result0.cutOffLeadingZeroes();
            result1.cutOffLeadingZeroes();
            return new BigInteger[]{result0, result1};
        }
    }

    /**
     * Check if there are 1s in the lowest bits of this BigInteger
     * @param numberOfBits the number of the lowest bits to check
     * @return false if all bits are 0s, true otherwise 
     */
    private boolean nonZeroDroppedBits(int numberOfBits) {
        int intCount = numberOfBits >> 5;
        int bitCount = numberOfBits & 31;
        int i;
        for (i = 0; i < intCount && digits[i] == 0; i++) {
            ;
        }
        if (i != intCount || (digits[i] << (32 - bitCount) != 0)) {
            return true;
        }
        return false;
    }

    /**
     * Answers the double value which the receiver represents
     * 
     * @return double the value of the receiver.
     */
    public double doubleValue() {
        if (sign == 0) {
            return 0.0;
        }
        int bitLen = abs().bitLength();
        if (bitLen <= 63) {
            return longValue();
        }
        if (bitLen > 1024) {
            return sign == -1 ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        }
        long exponent = bitLen - 1;
        int delta = bitLen - 54;
        
        // We need 54 top bits from this. 
        // The 53th bit is always 1 in lVal.
        long lVal = this.abs().shiftRight(delta).longValue();
        
        // Take 53 bits from lVal to mantissa.
        // The least significant bit is needed for rounding.
        long mantissa = lVal & 0x1fffffffffffffL;
        if (exponent == 1023) {
            if (mantissa == 0x1fffffffffffffL) {
                return sign == 1 ? Double.POSITIVE_INFINITY
                    : Double.NEGATIVE_INFINITY;
            }
            if (mantissa == 0x1ffffffffffffeL) {
                return sign == 1 ? Double.MAX_VALUE : -Double.MAX_VALUE;
            }
        }
        // round the mantissa
        if ((mantissa & 1) == 1) {
            if ((mantissa & 2) == 2 || nonZeroDroppedBits(delta)) {
                mantissa += 2;
            }
        }
        mantissa >>= 1; // drop the rounding bit
        long resSign = sign == -1 ? 0x8000000000000000L : 0;
        exponent = ((long)(1023 + exponent) << 52) & 0x7ff0000000000000L;
        long result = resSign | exponent | mantissa;
        return Double.longBitsToDouble(result);
    }

    /**
     * Compares the argument to the receiver, and answers true if they represent
     * the <em>same</em> object using a class specific comparison. In this
     * case the argument must also be a BigInteger which represents the same
     * number
     * 
     * @param anotherObj
     *            Object the object to compare with this object.
     * @return boolean <code>true</code> if the object is the same as this
     *         object <code>false</code> if it is different from this object.
     * @see #hashCode
     */
    public boolean equals(Object anotherObj) {
        if (! (anotherObj instanceof BigInteger)) {
            return false;
        }
        return compareTo((BigInteger)anotherObj) == EQUALS;
    }

    /**
     * Toggles the specified bit in the receiver.
     * 
     * @param bitNumber
     *            int the bit to flip.
     */
    public BigInteger flipBit(int bitNumber) {
        if (bitNumber < 0) {
            throw new ArithmeticException("negative bit number");
        }
        int intCount = bitNumber >> 5;
        bitNumber &= 0x1f;
        return changeBit(intCount, bitNumber, 1);
    }

    /**
     * Answers the float value which the receiver represents
     * 
     * @return float the value of the receiver.
     */
    public float floatValue() {
        return (float)doubleValue();
    }

    /**
     * Answers the greatest common divisor of abs(this) and abs(val), zero if
     * this==val==0
     * 
     * @return BigInteger gcd(abs(this), abs(that))
     */
    public BigInteger gcd(BigInteger that) {
        if (this.isZero()) {
            return that.abs();
        }
        if (that.isZero()) {
            return this.abs();
        }
        // Implements the Euclidean algorithm
        BigInteger firstNumber = this.abs();
        BigInteger secondNumber = that.abs();
        BigInteger result = firstNumber.remainder(secondNumber);
        while (!result.isZero()) {
            firstNumber = secondNumber;
            secondNumber = result;
            result = firstNumber.remainder(secondNumber);
        }
        return secondNumber;
    }

    /**
     * Answers the index of the lowest set bit in the receiver, or -1 if no bits
     * are set.
     * 
     * @return BigInteger the bit index of the least significant set bit in the
     *         receiver.
     */
    public int getLowestSetBit() {
        if (this.isZero()) {
            return -1;
        }
        int intCount;
        for (intCount = 0; intCount < numberLength && digits[intCount] == 0; intCount++) {
            ;
        }
        int bitCount = 0;
        while ((digits[intCount] & (1 << bitCount)) == 0) {
            bitCount++;
        }
        return (intCount << 5) + bitCount;
    }

    /**
     * Answers an integer hash code for the receiver. Any two objects which
     * answer <code>true</code> when passed to <code>.equals</code> must
     * answer the same value for this method.
     * 
     * @return int the receiver's hash.
     * 
     * @see #equals
     */
    public int hashCode() {
        int hashCode = digits[0];
        if (numberLength > 1) {
            hashCode += digits[numberLength - 1] + bitLength();
        } 
        if (numberLength > 2) {
            hashCode += digits[1];
        }
        return (sign == 1 ? hashCode : -hashCode);
    }

    /**
     * Answers the int value which the receiver represents
     * 
     * @return int the value of the receiver.
     */
    public int intValue() {
        return (sign < 0 ? -digits[0] : digits[0]);
    }

    /**
     * Checks if this BigInteger equals to ONE
     */
    private boolean isOne() {
        if (numberLength == 1 && digits[0] == 1) {
            return true;
        }
        return false;
    }

    /**
     * Answers true if the receiver is probably prime to the given degree of
     * certainty.
     * 
     * @param security
     *            int the degree of certainty required.
     * @return boolean true if the receiver is prime and false otherwise.
     */
    public boolean isProbablePrime(int security) {
        if (security <= 0) {
            return true;
        }
        if (digits[0] == 2 && numberLength == 1) {
            return true;
        }
        if ((digits[0] & 1) == 0) { // is even
            return false;
        }
        // check the divisibility by all small primes that are less than 256
        for (int i = 1; i < primes.length; i++) {
            if (numberLength == 1 && digits[0] == primes[i]) {
                return true;
            }
            if (remainderArrayByInt( digits, numberLength, primes[i]) == 0) {
                return false;
            }
        }
        
        // perform the Rabin-Miller test.
        // Define s and r from the following: this - 1 = 2^s * r.
        BigInteger previous = subtract(ONE);
        int s = previous.getLowestSetBit();
        BigInteger r = previous.shiftRight(s);
        BigInteger y;
        
        // The number of bases for the test should be chosen
        // depending on the bitlength and certainty.
        // The following data is published in the 4.49 Note 
        // of the Handbook of Applied Cryptography, Alfred Menezes et al.
        // Each pair of values gives the number of trials for the given bit length.
        // This gives the error probability not greater than (1/2)^80.
        int trialsForLength[][] = {{100, 27}, {150, 18}, {200, 15}, {250, 12}, 
                  {300, 9}, {350, 8}, {400, 7}, {500, 6},
                  {600, 5}, {800, 4}, {1250, 3}, {Integer.MAX_VALUE, 2}
        };
        int bitLen = bitLength();
        int count = 27;
        for (int i = 0; i < trialsForLength.length / 2; i++) {
            if (bitLen <= trialsForLength[i][0]) {
                count = trialsForLength[i][1];
                break;
            }
        }

        // If security is greater than 80, heuristically increase the number of trials.
        // It is known that the error probability of the Rabin-Miller test 
        // is not greater than (1/4)^trials = (1/2)^security.
        // From this it follows that trials is not greater than security/2.
        if (security > 80) {
            int halfSecurity = security / 2;
            if (security <= 100) {
                count = (count * 2 < halfSecurity) ? count * 2 : halfSecurity;
            } else if (security <= 120) {
                count = (count * 3 < halfSecurity) ? count * 3 : halfSecurity;
            } else {
                count = (count * 4 < halfSecurity) ? count * 4 : halfSecurity;
            }
        }

        for (int i = 1; i < count; i++) {
            BigInteger a = new BigInteger(1, primes[i]);
            y = a.modPow(r, this);
            if (y.isOne() || y.equals(previous)) {
                continue;
            }
            for (int j = 1; j < s; j++) {
                if (y.equals(previous)) {
                    continue;
                }
                y = y.multiply(y).mod(this);
                if (y.isOne()) {
                    return false;
                }
            }
            if (!y.equals(previous)) {
                return false;
            }
        }
        return true;
    }
        
    /**
     * Checks if this BigInteger equals to ZERO
     */
    final boolean isZero() {
        return sign==0;
     }

    /**
     * Answers the long value which the receiver represents
     * 
     * @return long the value of the receiver.
     */
    public long longValue() {
        long value = (long)digits[0] & 0xffffffffL;
        if (numberLength == 0) return 0;
        if (numberLength == 1) {
            return (sign >= 1 ? value : -value);
        } else {
            value += (long)digits[1] << 32;
        }
        return (sign < 0 ? -value : value);
    }

    /**
     * Answers the most positive of either the receiver or the argument.
     * 
     * @param that
     *            BigInteger the value to compare.
     * @return BigInteger the larger value.
     */
    public BigInteger max(BigInteger that) {
        if (compareTo(that) > 0) {
            return this;
        } else {
            return that;
        }
    }

    /**
     * Answers the most negative of either the receiver or the argument.
     * 
     * @param that
     *            BigInteger the value to compare.
     * @return BigInteger the smaller value.
     */
    public BigInteger min(BigInteger that) {
        if (compareTo(that) < 0) {
            return this;
        } else {
            return that;
        }
    }

    /**
     * Answers the remainder of the receiver modulo a BigInteger (a positive
     * value).
     * 
     * @param modulus
     *            the value to divide
     * @return BigInteger this (mod modulus)
     * 
     * @exception ArithmeticException
     *                if modulus is zero
     */
    public BigInteger mod(BigInteger modulus) {
        if (modulus.sign <= 0) {
            throw new ArithmeticException("modulus is non-positive");
        }
        BigInteger res = remainder(modulus);
        if (res.sign == -1) {
            return modulus.add(res);
        }
        return res;
    }

    /**
     * Answers the inverse of the receiver modulo a BigInteger, if it exists.
     * 
     * @param m
     *            BigInteger a BigInteger to divide
     * @return BigInteger this^(-1) (mod m)
     * 
     * @exception ArithmeticException
     *                if m is <= 0, or gcd(this,m) != 1
     */
    public BigInteger modInverse(BigInteger m) {
        // implements the extended Euclidean algorithm
        if (m.sign <= 0) {
            throw new ArithmeticException("non-positive modulus");
        }
        BigInteger x = this;
        BigInteger y = m;
        BigInteger a = ONE;
        BigInteger b = ZERO;
        BigInteger c = ZERO;
        BigInteger d = ONE;
        BigInteger nextC;
        BigInteger nextD;
        BigInteger quot = null;
        BigInteger rem;
        while (!y.isZero()) {
            rem = x.remainder(y);
            quot = x.divide(y);
            if (rem.isZero() && !y.isOne()) {
                throw new ArithmeticException("non-invertible BigInteger");
            }
            x = y;
            y = rem;
            nextC = a.subtract(quot.multiply(c));
            nextD = b.subtract(quot.multiply(d));
            a = c;
            b = d;
            c = nextC;
            d = nextD;
        }
        if (a.sign < 0) {
            return a.add(m);
        }
        if (x.sign < 0) {
            return (m.subtract(a));
        }
        return a;
    }

    /**
     * Answers the receiver to the power of exponent modulo a BigInteger
     * 
     * @exception ArithmeticException
     *                modulus is <= 0
     * 
     * @return BigInteger this ^ exp (mod modulus)
     */
    public BigInteger modPow(BigInteger exp, BigInteger modulus) {
        // The square-and-multiply algorithm is used from
        // Handbook of Applied Cryptography, Alfred Menezes et al., p. 2.4.
        
        if (modulus.sign <= 0) {
            throw new ArithmeticException("non-positive modulus");
        }
        BigInteger result = ONE;
        BigInteger a = this;
        int len = exp.abs().bitLength();
        int count = 0;
        while (count < len) {
            if ((exp.digits[count >> 5] & (1 << (count & 0x1f))) != 0) {
                result = result.multiply(a).mod(modulus);
            }
            a = a.multiply(a).mod(modulus);
            count++;
        }
        if (exp.sign == -1) {
            return result.modInverse(modulus);
        }
        return result;
    }

    /**
     * Answers the product of the receiver and a BigInteger.
     * 
     * @param that
     *            BigInteger the value to multiply
     * @return BigInteger this * that
     */
    public BigInteger multiply(BigInteger that) {
        if (that.sign == 0 || this.sign == 0) {
            return ZERO;
        }
        if (this.numberLength >= that.numberLength) {
            return multiply(this, that);
        } else {
            return multiply(that, this);
        }
    }

    /**
     * Multiplies two BigIntegers.
     * Implements traditional scholar algorithm described by Knuth.
     */
    private BigInteger multiply(BigInteger a, BigInteger b) {
        int aLen = a.numberLength;
        int bLen = b.numberLength;
        int resLength = aLen + bLen;
        // a special case when both numbers don't exceed int
        if (resLength == 2) {
            long val = ((long)a.digits[0] & 0xffffffffL)
                    * ((long)b.digits[0] & 0xffffffffL);
            int sign = a.sign != b.sign ? -1 : 1;
            int valueLo = (int) val;
            int valueHi = (int) (val >>> 32);
            if (valueHi == 0) {
                return new BigInteger(sign, valueLo);
            } else {
                return new BigInteger(sign, 2, new int[]{valueLo, valueHi});
            }
        }
        int[] aDigits = a.digits;
        int[] bDigits = b.digits;
        int resDigits[] = new int[resLength];
        // common case
        for (int j = 0; j < bLen; j++) {
            long carry = 0;
            int i;
            for (i = 0; i < aLen; i++) {
                int m = i + j;
                carry += ((long)aDigits[i] & 0xffffffffL)
                         * ((long)bDigits[j] & 0xffffffffL)
                            + ((long)resDigits[m] & 0xffffffffL);
                resDigits[m] = (int)carry;
                carry >>>= 32;
            }
            resDigits[i + j] = (int)carry;
        }
        BigInteger result = new BigInteger((a.sign == b.sign) ? 1 : -1, resLength, resDigits);
        result.cutOffLeadingZeroes();
        return result;
    }

    private static BigInteger multiplyByPositiveInt(BigInteger a, int b) {
        int resSign = a.sign;
        if (resSign == 0) {
            return ZERO;
        }
        int aNumberLength = a.numberLength;
        int[] aDigits = a.digits;
        if (aNumberLength == 1) {
            long res = ((long) aDigits[0] & 0xffffffffL) * ((long) b);
            int resLo = (int) res;
            int resHi = (int) (res >>> 32);
            if (resHi == 0) {
                return new BigInteger(resSign, resLo);
            }
            return new BigInteger(resSign, 2, new int[]{resLo, resHi});
        }
        int resLength = aNumberLength + 1;
        int resDigits[] = new int[resLength];
        long carry = 0;
        // common case
        int i;
        for (i = 0; i < aNumberLength; i++) {
            carry += ((long) aDigits[i] & 0xffffffffL)
                     * ((long) b & 0xffffffffL);
            resDigits[i] = (int) carry;
            carry >>>= 32;
        }
        resDigits[i] = (int) carry;
        BigInteger result = new BigInteger(resSign, resLength, resDigits);
        result.cutOffLeadingZeroes();
        return result;
    }

    BigInteger multiplyByTenPow(int exp) {
        if(exp < 10) {
            return multiplyByPositiveInt(this,tenPows[exp]);
        }
        return multiply(getTenPow(exp));
    }

    static BigInteger getTenPow(int exp) {
        if(exp < bigTenPows.length) {
            return bigTenPows[exp];
        }
        return TEN.pow(exp);
    }

    /**
     * Answers the negative of the receiver
     * 
     * @return BigInteger (-1) * this
     */
    public BigInteger negate() {
        if(isZero()) {
            return this;
        }
        return new BigInteger(-sign, numberLength, digits);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigInteger nextProbablePrime() {
        if (sign == -1) {
            throw new ArithmeticException("the number must be positive");
        }
        if (sign == 0) {
            return SMALL_VALUES[2];
        }
        int resDigits[] = new int[numberLength];
        System.arraycopy(digits, 0, resDigits, 0, numberLength);
        if ((resDigits[0] & 1) == 0) {
            addIntToArray(resDigits, numberLength, 1);
        }
        BigInteger result = new BigInteger(1, numberLength, resDigits);
        while (!result.isProbablePrime(100)) {
            addIntToArray(result.digits, result.numberLength, 2);
        } 
        return result;

    } 

    /**
     * Answers the bitwise negation of the receiver.
     * 
     * @return BigInteger NOT(this)
     */
    public BigInteger not() {
        // one extra element for possible carry returned from setTrueCoded
        int resLength = this.numberLength + 1; 
        int resDigits[] = new int[resLength];
        System.arraycopy(digits, 0, resDigits, 0, numberLength);
        if (sign == -1) {
            setTrueCoded(resDigits, numberLength);
        }
        bitOperation(resDigits, null, 0, resDigits, numberLength, 2);
        int resSign = (sign < 0 ? 1 : -1);
        if (resSign == -1) {
            resDigits[resLength - 1] = setTrueCoded(resDigits, numberLength);
        }
        BigInteger result = new BigInteger(resSign, resLength, resDigits);
        result.cutOffLeadingZeroes();
        return result;
    } 

    /**
     * Answers the bitwise OR of the receiver and the argument.
     * 
     * @param that
     *            BigInteger the value to OR.
     * @return BigInteger this | that
     */
    public BigInteger or(BigInteger that) { 
        if (this.sign == 0) {
            return that;
        }
        if (that.sign == 0) {
            return this;
        }
        int resSign = this.sign == 1 && that.sign == 1 ? 1 : -1;
        int resLength;
        int resDigits[];
        int thisNeg[] = null;
        int thatNeg[] = null;
        
        // some cases when either this or that is negative
        if (this.sign == -1 || that.sign == -1) {
            if (this.sign == -1) {
                thisNeg = new int[this.numberLength];
                System.arraycopy(this.digits, 0, thisNeg, 0, this.numberLength);
            }
            if (that.sign == -1) {
                thatNeg = new int[that.numberLength];
                System.arraycopy(that.digits, 0, thatNeg, 0, that.numberLength);
            }
            if (this.sign == -1 && that.sign == -1) { 
                // both are negative
                int orLen = this.numberLength > that.numberLength ? 
                        that.numberLength : this.numberLength;
                resLength = orLen + 1;// + (this.numberLength == that.numberLength ? 0 : 1);
                resDigits = new int[resLength];
                setTrueCoded(thisNeg, orLen);
                setTrueCoded(thatNeg, orLen);
                bitOperation(resDigits, thisNeg, orLen, thatNeg, orLen, 3);
                resDigits[resLength - 1] = -1;
            } else {    
                if (this.numberLength > that.numberLength) {
                    // this is longer than that
                    if (this.sign == 1) {
                        // this is positive and that is negative
                        resLength = that.numberLength + 1;
                        resDigits = new int[resLength];
                        setTrueCoded(thatNeg, that.numberLength);
                        bitOperation(resDigits, this.digits,
                                that.numberLength, thatNeg, that.numberLength, 3);
                        resDigits[resLength - 1] = -1;
                    } else {
                        // this is negative and that is positive 
                        resLength = this.numberLength;
                        resDigits = new int[resLength];
                        setTrueCoded(thisNeg, this.numberLength);
                        bitOperation(resDigits, thisNeg, this.numberLength,
                                that.digits, that.numberLength, 3);
                    }
                } else {
                    // that is longer than this
                    if (this.sign == 1) {
                        // this is positive and that is negative
                        resLength = that.numberLength; // + 1?
                        resDigits = new int[resLength];
                        setTrueCoded(thatNeg, that.numberLength);
                        bitOperation(resDigits, thatNeg, that.numberLength,
                                this.digits, this.numberLength, 3);
                    } else {
                        // that is positive and this is negative
                        resLength = this.numberLength + 1;
                        resDigits = new int[resLength];
                        setTrueCoded(thisNeg, this.numberLength);
                        bitOperation(resDigits, that.digits,
                                this.numberLength, thisNeg, this.numberLength, 3);
                        resDigits[resLength - 1] = -1;
                    }
                }
            }
        } else {
            // cases when both numbers are positive and
            // either this or that is negative but both are of the same length
            resLength = this.numberLength >= that.numberLength ? this.numberLength
                    : that.numberLength;
            resDigits = new int[resLength];
            if (this.numberLength >= that.numberLength) {
                bitOperation(resDigits, thisNeg == null ? this.digits
                        : thisNeg, this.numberLength,
                        thatNeg == null ? that.digits : thatNeg,
                        that.numberLength, 3);
            } else {
                bitOperation(resDigits, thatNeg == null ? that.digits
                        : thatNeg, that.numberLength,
                        thisNeg == null ? this.digits : thisNeg,
                        this.numberLength, 3);
            }
        }
        if (resSign == -1) {
            setTrueCoded(resDigits, resLength);
        }
        BigInteger result = new BigInteger(resSign, resLength, resDigits);
        result.cutOffLeadingZeroes();
        return result;
    }

    /**
     * Answers the receiver to the power of exponent.
     * 
     * @exception ArithmeticException
     *                if the exp is negative.
     * 
     * @return BigInteger this ^ exp
     */
    public BigInteger pow(int exp) {
        if (exp < 0) {
            throw new ArithmeticException("exponent is negative");
        }
        if (exp == 0) {
            return ONE;
        }
        BigInteger res = ONE;
        do {
            res = this.multiply(res);
        } while (--exp > 0);
        return res;
    } 
    
    /**
     * Puts a big-endian byte array into a little-endian int array representing 
     * non-negative "digits" of a big integer number.  
     */
    private void putBytesToIntegers(byte[] byteValues, boolean isNegative) {
        int bytesLen = byteValues.length;
        int highBytes = bytesLen & 3; 
        this.numberLength = (bytesLen >> 2) + (highBytes == 0 ? 0 : 1); 
        digits = new int[numberLength];
        int i = 0;
        // Set the highest element of an int array negative if needed
        if (isNegative) {
            digits[numberLength - 1] = -1;
        } 
        // Put bytes to the int array starting from the end of the byte array
        while (bytesLen > highBytes)   { 
            digits[i++] = (byteValues[--bytesLen] & 0xff) |
                (byteValues[--bytesLen] & 0xff) <<  8 | 
                (byteValues[--bytesLen] & 0xff) << 16 | 
                (byteValues[--bytesLen] & 0xff) << 24;
        }
        // Put the first bytes in the highest element of the int array
        for (int j = 0; j < bytesLen; j++) {
            digits[i] = digits[i] << 8 | (byteValues[j] & 0xff);
        }
    }

    /**
     * Answers the remainder of the receiver divided by a BigInteger
     * 
     * @param that
     *            a BigInteger to divide
     * 
     * @exception ArithmeticException
     *                if that is zero
     * 
     * @return BigInteger this % that
     */
    public BigInteger remainder(BigInteger that) {
        if (that.equals(ZERO)) {
            throw new ArithmeticException("division by zero");
        }
        int thisLen = this.numberLength;
        int thatLen = that.numberLength;
        if ((thisLen != thatLen ?
                       ((thisLen > thatLen) ? 1 : -1) :
                       compareArrays(this.digits,that.digits,thisLen ))
               == LESS) {
            return this;
        }
        int resLength = thatLen;
        int resDigits[] = new int[resLength];
        if (resLength == 1) {
            resDigits[0] = remainderArrayByInt(this.digits,
                                               thisLen, that.digits[0]);
        } else {
            int qLen = thisLen - thatLen + 1;
            resDigits = divide(null, qLen, this.digits, thisLen,
                               that.digits, thatLen);
        }
        BigInteger result = new BigInteger(this.sign, resLength, resDigits);
        result.cutOffLeadingZeroes();
        return result;
    }

    /**
     * Sets the specified bit in the receiver.
     * 
     * @param number
     *            int the bit to set.
     */
    public BigInteger setBit(int number) {
        if (number < 0) {
            throw new ArithmeticException("negative bit number");
        }
        int intCount = number >> 5; // count of integers: count / 32
        number &= 0x1f;    // count of remaining bits: count % 32
        if (this.sign == -1 && intCount >= this.numberLength) {
            return this;
        }
        return changeBit(intCount, number, 2);
    }

    /**
     * Answers the sign of the receiver
     * 
     * @return BigInteger -1, 0, or 1 if the receiver is negative, zero, or
     *         positive
     */
    public int signum() {
        return this.sign;
    }

    /**
     * Answers a BigInteger with the value of the reciever multiplied by
     * 2^count.
     * 
     * @param count
     *            int the amount to shift the receiver.
     * @return BigInteger this << count
     */
    public BigInteger shiftLeft(int count) {
        if (count == 0) {
            return this;
        }
        if (count > 0) {
            return shiftLeft(this, count);
        }
        return shiftRight(this, -count);
    }
        
    /**
     * Shifts left a BigInteger value. 
     * @param source the value to be shifted
     * @param count the shift distance in bits 
     */
    private BigInteger shiftLeft(BigInteger source, int count) {
        int intCount = count >> 5;
        count &= 0x1f;
        int resLength = source.numberLength + intCount + (count == 0 ? 0 : 1);
        int resDigits[] = new int[resLength];
        shiftLeft(resDigits, source.digits, intCount, count);
        BigInteger result = new BigInteger(source.sign, resLength, resDigits);
        result.cutOffLeadingZeroes();
        return result;
    }

    /**
     * Answers a BigInteger with the value of the reciever divided by
     * 2^count.
     * 
     * @param count
     *            int the amount to shift the receiver.
     * @return BigInteger this >> count
     */
    public BigInteger shiftRight(int count) {
        if (count == 0) {
            return this;
        }
        if (count > 0) {
            return shiftRight(this, count);
        }
        return shiftLeft(this, -count);
    }

    /**
     * Shifts right a BigInteger value. 
     * @param source the BigInteger to be shifted
     * @param count the shift distance in bits
     */
    private BigInteger shiftRight(BigInteger source, int count) {
        int i = 0;
        int intCount = count >> 5; // count of integers
        count &= 0x1f;    // count of remaining bits
        int leftShiftCount = 32 - count;
        if (intCount >= source.numberLength) {
            return (source.sign == -1 ? NEG_ONE : ZERO);
        }
        int resSign = source.sign;
        int resLength = source.numberLength - intCount;
        int resDigits[] = new int[resLength];
        shiftRight(resDigits, resLength, source.digits, intCount, count);
        if (resSign == -1) {
            // check if the dropped bits are zeros (the remainder equals to 0)
            for (i = 0; i < intCount && source.digits[i] == 0; i++) {
                ;
            }
            // if the remainder is not zero, add 1 to the result 
            if (i != intCount || (count != 0 && source.digits[i] << leftShiftCount != 0)) {
                addIntToArray(resDigits, resLength, 1);
            }
        }
        BigInteger result = new BigInteger(resSign, resLength, resDigits);
        result.cutOffLeadingZeroes();
        return result;
    }

    /**
     * Answers the difference of the receiver and a BigInteger.
     * 
     * @param that
     *            BigInteger the value to subtract
     * @return BigInteger this - that
     */
    public BigInteger subtract(BigInteger that) {
        int resSign;
        int resDigits[];
        int thisSign = this.sign;
        int thatSign = that.sign;
        if(thatSign==0) {
            return this;
        }
        if(thisSign==0){
            return that.negate();
        }

        int thisLen = this.numberLength;
        int thatLen = that.numberLength;
        if(thisLen+thatLen == 2){
            long a = ((long) this.digits[0] & 0xffffffffL);
            long b = ((long) that.digits[0] & 0xffffffffL);
            if(thisSign<0) {
            	a=-a;
            }
            if(thatSign<0){
            	b=-b;
            }
            return valueOf( a-b );
        }
        int cmp = (thisLen != thatLen ?
                   ((thisLen > thatLen) ? 1 : -1) :
                   compareArrays(this.digits,that.digits,thisLen ));

        if (cmp == LESS) {
            resSign = -thatSign;
            if (thisSign == thatSign) {
                resDigits = subtract(that.digits, thatLen,
                                     this.digits, thisLen);
            } else {
                resDigits = add(that.digits, thatLen, this.digits,
                                thisLen);
            }
        } else {
            resSign = thisSign;
            if (thisSign == thatSign) {
                if (cmp == EQUALS) {
                    return ZERO;
                }
                resDigits = subtract(this.digits, thisLen,
                                     that.digits, thatLen);
            } else {
                resDigits = add(this.digits, thisLen, that.digits,
                                thatLen);
            }
        }
        BigInteger res = new BigInteger(resSign, resDigits.length, resDigits);
        res.cutOffLeadingZeroes();
        return res;
    }

    /**
     * Answers true if the specified bit is set in the receiver.
     * 
     * @param number
     *            int the bit to check.
     * @return boolean if the specified bit is set.
     */
    public boolean testBit(int number) {
        if (number < 0) {
            throw new ArithmeticException("negative bit number");
        }
        int intCount = number >> 5;
        if (intCount >= numberLength) {
            return (sign == -1);
        }
        number = (1 << (number & 0x1f)); // int with 1 set to the needed position
        if (sign == -1) {
            int negDigits[] = new int[numberLength];
            System.arraycopy(digits, 0, negDigits, 0, numberLength);
            setTrueCoded(negDigits, numberLength);        
            return ((negDigits[intCount] & number) == number);
        }
        return ((digits[intCount] & number) == number);
    }

    /**
     * Answers an array of bytes containing the value of the receiver in the
     * same format used by the matching constructor.
     * 
     * @return byte[] the bits of the value of the receiver.
     */
    public byte[] toByteArray() {
        int bytesLen;
        int bitLen = bitLength();
        if (bitLen == 0) {
            bytesLen = 1;
        } else {
            bytesLen = ((bitLen + 1) >> 3) + (((bitLen + 1) & 7) == 0 ? 0 : 1);
        }
        if (this.sign == -1) {
            int temp[] = new int[numberLength];
            System.arraycopy(this.digits, 0, temp, 0, numberLength);
            // convert from the true coded form to the two's complement one
            setTrueCoded(temp, numberLength);  
            return toByteArray(temp, numberLength, bytesLen);
        } else {
            return toByteArray(digits, numberLength, bytesLen);
        }    
    }

    /**
     * Puts the little-endian int array representing the magnitude 
     * of this BigInteger into the big-endian byte array.
     */
    private byte[] toByteArray(int intArray[], int arrayLength, int bytesLen) {
        byte[] bytes = new byte[bytesLen];
        int firstByteNumber = 0;
        int highBytes;
        int realBytes = arrayLength << 2; // * 4
        if (bytesLen - realBytes == 1) {
            bytes[0] = (byte)(sign == -1 ? -1 : 0);
            highBytes = 4;
            firstByteNumber++;
        } else {
            int hB = bytesLen & 3; // bytesLen % 4
            highBytes = hB == 0 ? 4 : bytesLen & 3;
        }
        int digitIndex = 0;
        int bytesInInteger = 4; 
        while (bytesLen > firstByteNumber) {
            int digit = intArray[digitIndex];
            digitIndex++;
            if (digitIndex == arrayLength) {
                bytesInInteger = highBytes;
            }
            for (int i = 0; i < bytesInInteger; i++, digit >>= 8) {
                bytes[--bytesLen] = (byte) digit;
            }
        }
        return bytes;    
    }

    private static long divideLongByBillion(long a) {
        long quot;
        long rem;
        if (a >= 0) {
            long bLong = 1000000000L; //(long)1000000000 & 0xffffffffL;
            quot = (a / bLong);
            rem = (a % bLong);
        } else {
            // make the dividend positive shifting it right by 1 bit
            // then get the quotient an remainder and correct them properly
            long aPos = a >>> 1;
            long bPos = 1000000000L >>> 1;
            quot = aPos / bPos;
            rem = aPos % bPos;
            // double the remainder and add 1 if a is odd
            rem = (rem << 1) + (a & 1);
        }
        return (rem << 32) | (quot & 0xffffffffL);
    }

    /**
     * Answers a string containing a concise, human-readable description of the
     * receiver. In this case, a string of decimal digits.
     * 
     * @return String a printable representation for the receiver.
     */
    public String toString() {
        return toDecimalScaledString(0);
    }

    String toDecimalScaledString(int scale) {
        int resLengthInChars;
        int currentChar;
        char result[];
        if (sign == 0) {
            switch (scale) {
                case 0: return "0";
                case 1: return "0.0";
                case 2: return "0.00";
                case 3: return "0.000";
                case 4: return "0.0000";
                case 5: return "0.00000";
                case 6: return "0.000000";
                default:
                    StringBuffer result1 = new StringBuffer();
                    if (scale  < 0) {
                        result1.append("0E+");
                    } else {
                        result1.append("0E");
                    }
                    result1.append(-scale);
                    return result1.toString();
            }
        } else {
            // one 32-bit unsigned value may contains 10 decimal digits
            resLengthInChars = numberLength * 10 +1+7;
            // Explanation why +1+7:
            // +1 - one char for sign if needed.
            // +7 - For "special case 2" (see below) we have 7 free chars for
            //  inserting necessary scaled digits.
            result = new char[resLengthInChars+1];
            //  alocated [resLengthInChars+1] charactes.
            // a free latest character may be used for "special case 1" (see below)
            currentChar = resLengthInChars;
            if (numberLength == 1) {
                int highDigit = digits[0];
                if (highDigit < 0) {
                    long v = (long) highDigit & 0xffffffffL;
                    do {
                        long prev = v;
                        v /= 10;
                        result[--currentChar] = (char) (0x0030 + ((int) (prev - v * 10)));
                    } while (v != 0);
                } else {
                    int v = highDigit;
                    do {
                        int prev = v;
                        v /= 10;
                        result[--currentChar] = (char) (0x0030 + (prev - v * 10));
                    } while (v != 0);
                }
            } else {
                int temp[] = new int[numberLength];
                int tempLen = numberLength;
                System.arraycopy(digits, 0, temp, 0, tempLen);
                BIG_LOOP:
                      while (true) {
                          // divide the array of digits by bigRadix and convert remainders
                          // to characters collecting them in the char array
                          long result11 = 0;
                          for (int i1 = tempLen - 1; i1 >= 0; i1--) {
                              long temp1 = (result11 << 32) + ((long) temp[i1] & 0xffffffffL);
                              long res = divideLongByBillion(temp1);
                              temp[i1] = (int) res;
                              result11 = (int) (res >> 32);
                          }
                          int resDigit = (int) result11;
                          int previous = currentChar;
                          do {
                              result[--currentChar] = (char) (0x0030 + (resDigit % 10));
                          } while (((resDigit /= 10) != 0) && (currentChar != 0));
                          int delta = 9 - previous + currentChar;
                          for (int i = 0; i < delta && currentChar > 0; i++) {
                              result[--currentChar] = '0';
                          }
                          int j = tempLen - 1;
                          for (; temp[j] == 0; j--) {
                              if (j == 0) { // means temp[0] == 0
                                  break BIG_LOOP;
                              }
                          }
                          tempLen = j + 1;
                      }
                while (result[currentChar] == '0') {
                    currentChar++;
                }
            }
        }
        boolean negNumber = sign < 0;
        int exponent = resLengthInChars - currentChar - scale - 1;
        if (scale == 0) {
            if (negNumber) {
                result[--currentChar] = '-';
            }
            return new String(result, currentChar, resLengthInChars - currentChar);
        }
        if (scale > 0 && exponent >= -6) {
            if (exponent >= 0) {
                // special case 1
                int insertPoint = currentChar + exponent ;
                for(int j=resLengthInChars-1; j>=insertPoint; j--) {
                    result[j+1] = result[j];
                }
                result[++insertPoint]='.';
                if (negNumber) {
                    result[--currentChar] = '-';
                }
                return new String(result, currentChar, resLengthInChars - currentChar + 1);
            } else {
                // special case 2
                for (int j = 2; j < -exponent + 1; j++) {
                    result[--currentChar] = '0';
                }
                result[--currentChar] = '.';
                result[--currentChar] = '0';
                if (negNumber) {
                    result[--currentChar] = '-';
                }
                return new String(result, currentChar, resLengthInChars - currentChar);
            }
        } else {
            int startPoint = currentChar + 1;
            int endPoint = resLengthInChars;
            StringBuffer result1 = new StringBuffer(16+endPoint-startPoint);
            if (negNumber) {
                result1.append('-');
            }
            if (endPoint - startPoint >= 1) {
                result1.append(result[currentChar]);
                result1.append('.');
                result1.append(result,currentChar+1,resLengthInChars - currentChar-1);
            } else {
                result1.append(result,currentChar,resLengthInChars - currentChar);
            }
            result1.append('E');
            if (exponent > 0) {
                result1.append('+');
            }
            result1.append(Integer.toString(exponent));
            return result1.toString();
        }
    }

    /**
     * Answers a string containing a concise, human-readable description of the
     * receiver as a sequence of digits in the specified radix.
     * 
     * @return String a printable representation for the receiver.
     */
    public String toString(int radix) {
        if (sign == 0) {
            return "0";
        }
        if(numberLength == 1) {
            int highDigit = digits[numberLength-1];
            long v = (long)highDigit&0xffffffffL;
            if (sign < 0) {
                v = -v;
            }
            return Long.toString(v,radix);
        }
        if (radix < Character.MIN_RADIX || radix > Character.MAX_RADIX || radix == 10) {
            return toString();
        }
        double bitsForRadixDigit;
         bitsForRadixDigit = Math.log(radix) / Math.log(2);
        int resLengthInChars = (int)(abs().bitLength() / bitsForRadixDigit
                               + ((sign == -1) ? 1 : 0)) + 1;

        char result[] = new char[resLengthInChars];
        int currentChar = resLengthInChars;
        int resDigit;
        if (radix != 16) {
            int temp[] = new int[numberLength];
            System.arraycopy(digits, 0, temp, 0, numberLength);
            int tempLen = numberLength;
            int charsPerInt = getCharsPerInt(radix);
            int i;
            // get the maximal power of radix that fits in int
            int bigRadix = bigRadices[radix - 2];
            while (true) {
                // divide the array of digits by bigRadix and convert remainders
                // to characters collecting them in the char array
                resDigit = divideArrayByInt(temp, temp, tempLen, bigRadix);
                int previous = currentChar;
                do {
                    result[--currentChar] = 
                        Character.forDigit(resDigit % radix, radix);
                } while (((resDigit /= radix) != 0) && (currentChar != 0));
                int delta = charsPerInt - previous + currentChar;
                for (i = 0; i < delta && currentChar > 0; i++) {
                    result[--currentChar] = '0';
                }
                for (i = tempLen - 1; i > 0 && temp[i] == 0; i--) {
                    ;
                }
                tempLen = i + 1;
                if (tempLen == 1  && temp[0] == 0) { // the quotient is 0
                    break;
                }
            }
        } else {
            // radix == 16
            for (int i = 0; i < numberLength; i++) {
                for (int j = 0; j < 8 && currentChar > 0; j++) {
                    resDigit = digits[i] >> (4 * j) & 0xf;
                    result[--currentChar] = Character.forDigit(resDigit, 16);
                }
            }
        }
        while (result[currentChar] == '0') {
            currentChar++;
        }
        if (sign == -1) {
            result[--currentChar] = '-';
        }
        return new String(result, currentChar, resLengthInChars - currentChar);
    }

    /**
     * Answers the bitwise XOR of the receiver and the argument.
     * 
     * @param that
     *            BigInteger the value to XOR.
     * @return BigInteger this XOR that
     */
    public BigInteger xor(BigInteger that) {
        if (this.sign == 0) {
            return that;
        }
        if (that.sign == 0) {
            return this;
        }
        int resSign = this.sign == that.sign ? 1 : -1;
        int resLength = this.numberLength >= that.numberLength ? 
                this.numberLength : that.numberLength;
        int resDigits[] = new int[resLength];
        int thisNeg[] = null;
        int thatNeg[] = null;
        if (this.sign == -1) {
            thisNeg = new int[this.numberLength];
            System.arraycopy(this.digits, 0, thisNeg, 0, this.numberLength);
            setTrueCoded(thisNeg, this.numberLength);
        }
        if (that.sign == -1) {
            thatNeg = new int[that.numberLength];
            System.arraycopy(that.digits, 0, thatNeg, 0, that.numberLength);
            setTrueCoded(thatNeg, that.numberLength);
        }
        // First make XOR for N elements where N is the length of a shorter array.
        int opLen = this.numberLength >= that.numberLength ? 
                that.numberLength : this.numberLength;
        bitOperation(resDigits, thisNeg == null ? this.digits : thisNeg,
                opLen, thatNeg == null ? that.digits : thatNeg, opLen, 4);
        // Then define top elements.
        if (this.numberLength != that.numberLength) {
            int i;
            for (i = opLen; i < resLength; i++) {
                if (this.sign == that.sign) {
                    // the numbers have the same signs
                    if (this.numberLength > that.numberLength) {
                        resDigits[i] = thisNeg == null ? 
                                this.digits[i] : ~thisNeg[i];
                    } else {
                        resDigits[i] = thatNeg == null ? 
                                that.digits[i] : ~thatNeg[i];
                    }
                } else {
                    // the numbers have different signs
                    if (this.numberLength >= that.numberLength) {
                        resDigits[i] = this.sign < 0 ? 
                                thisNeg[i] : ~this.digits[i];
                    } else {
                        resDigits[i] = this.sign < 0 ? 
                                ~that.digits[i] : thatNeg[i];
                    }
                }
            }
        }
        if (resSign == -1) {
            setTrueCoded(resDigits, resLength);
        }
        BigInteger result = new BigInteger(resSign, resLength, resDigits);
        result.cutOffLeadingZeroes();
        return result;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    private void readObject(ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        in.defaultReadObject();
        sign = signum;
        putBytesToIntegers(magnitude, false);
        cutOffLeadingZeroes();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        signum = signum();
        magnitude = abs().toByteArray();
        out.defaultWriteObject();
    }
}
