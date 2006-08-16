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
 * Static library that provides all the <b>bit level</b> operations for
 * {@link BigInteger}. The operations are:
 * <ul type="circle">
 * <li>Left Shifting</li>
 * <li>Right Shifting</li>
 * <li>Bit clearingting</li>
 * <li>Bit setting</li>
 * <li>Bit counting</li>
 * <li>Bit testing</li>
 * <li>Getting of the lowest bit setted</li>
 * </ul>
 * All operations are provided in immutable way, and some in both mutable and
 * immutable.
 * 
 * @author Daniel Fridlender
 * @author Matthias Gallé
 * @author Mariano Heredia
 * @author Miguel Vasquez
 */
class BitLevel {

    /** Just to denote that this class can't be instantied. */
    private BitLevel() {}

    /**
     * Converts bits represented by an int array from two's complementary code
     * to true one and vice versa. Performs ~value + 1.
     */
    static int setTrueCoded(int arr[], final int length) {
        int i;
        // Until the first set bit, the bits are equal (i.e. 0)
        for (i = 0; (i < length) && (arr[i] == 0); i++)
            ;
        if (i == length) {
            return 1;
        }
        // The digit that cointains the first set bit is (arithmetic) negated
        arr[i] = -arr[i];
        // From this position, the bits are (boolean) negated
        for (i++; i < length; i++) {
            arr[i] = ~arr[i];
        }
        return 0;
    }

    /** @see BigInteger#bitLength() */
    static int bitLength(BigInteger val) {
        if (val.sign == 0) {
            return 0;
        }
        int bLength = (val.numberLength << 5);
        int highDigit = val.digits[val.numberLength - 1];
        int i;

        if (val.sign < 0) {
            for (i = 0; val.digits[i] == 0; i++)
                ;
            // We reduce the problem to the positive case.
            if (i == val.numberLength - 1) {
                highDigit--;
            }
        }
        // Subtracting all sign bits
        bLength -= Integer.numberOfLeadingZeros(highDigit);
        return bLength;
    }

    /** @see BigInteger#bitCount() */
    static int bitCount(BigInteger val) {
        int bCount = 0;
        int i;

        if (val.sign >= 0) {
            for (i = 0; i < val.numberLength; i++) {
                bCount += Integer.bitCount(val.digits[i]);
            }
        } else {// (sign < 0)
            for (i = 0; val.digits[i] == 0; i++)
                ;
            // this digit absorbs the carry
            bCount += Integer.bitCount(-val.digits[i]);
            for (i++; i < val.numberLength; i++) {
                bCount += Integer.bitCount(~val.digits[i]);
            }
            // We take the complement sum:
            bCount = (val.numberLength << 5) - bCount;
        }
        return bCount;
    }

    /**
     * Performs a fast bit testing for positive numbers. The bit to to be tested
     * must be in the range {@code [0, val.bitLength()-1]}
     */
    static boolean testBit(BigInteger val, int n) {
        // PRE: 0 <= n < val.bitLength()
        return ((val.digits[n >> 5] & (1 << (n & 31))) != 0);
    }

    /**
     * Changes a bit in the BigInteger depending on the operation.
     * 
     * @param intCount the number of an element in the array to change bit in
     * @param bitNumber the bit's number in the intCount element
     * @param operation's code: 1 for flipBit(), 2 for setBit(), 3 for
     *        clearBit()
     */
    static BigInteger changeBit(BigInteger n, final int intCount,
            int bitNumber, final int operation) {
        int resSign = (n.sign == 0) ? 1 : n.sign;
        int resLength;
        resLength = Math.max(intCount, n.numberLength);
        // increase length for a possible carry in the second
        // BitLevel.setTrueCoded();
        resLength++;
        int resDigits[] = new int[resLength];
        for (int i = n.numberLength; i < resLength; i++) {
            resDigits[i] = 0;
        }
        System.arraycopy(n.digits, 0, resDigits, 0, n.numberLength);
        if (n.sign < 0) {
            BitLevel.setTrueCoded(resDigits, resLength);
        }
        bitNumber = 1 << bitNumber;
        switch (operation) {
            case 1: // flip bit
                resDigits[intCount] ^= bitNumber;
                break;
            case 2: // set bit
                resDigits[intCount] |= bitNumber;
                break;
            case 3: // clear bit
                resDigits[intCount] &= ~bitNumber;
                break;
        }
        if (n.sign < 0) {
            BitLevel.setTrueCoded(resDigits, resLength);
        }
        BigInteger result = new BigInteger(resSign, resLength, resDigits);
        result.cutOffLeadingZeroes();
        return result;
    }

    /**
     * Check if there are 1s in the lowest bits of this BigInteger
     * 
     * @param numberOfBits the number of the lowest bits to check
     * @return false if all bits are 0s, true otherwise
     */
    static boolean nonZeroDroppedBits(int numberOfBits, int digits[]) {
        int intCount = numberOfBits >> 5;
        int bitCount = numberOfBits & 31;
        int i;

        for (i = 0; (i < intCount) && (digits[i] == 0); i++)
            ;
        return ((i != intCount) || (digits[i] << (32 - bitCount) != 0));
    }

    /** @see BigInteger#shiftLeft(int) */
    static BigInteger shiftLeft(BigInteger source, int count) {
        int intCount = count >> 5;
        count &= 31;
        int resLength = source.numberLength + intCount + ((count == 0) ? 0 : 1);
        int resDigits[] = new int[resLength];

        shiftLeft(resDigits, source.digits, intCount, count);
        BigInteger result = new BigInteger(source.sign, resLength, resDigits);
        result.cutOffLeadingZeroes();
        return result;
    }

    /**
     * Performs {@code val <<= count}.
     */
    static void inplaceShiftLeft(BigInteger val, int count) {
        int intCount = count >> 5; // count of integers
        val.numberLength -= intCount;
        shiftLeft(val.digits, val.digits, intCount, count & 31);
        val.cutOffLeadingZeroes();
    }

    /**
     * Shifts left an array of integers. Total shift distance in bits is
     * intCount * 32 + count
     * 
     * @param result the destination array
     * @param source the source array
     * @param intCount the shift distance in integers
     * @param count an additional shift distance in bits
     */
    static void shiftLeft(int result[], int source[], int intCount, int count) {
        if (count == 0) {
            System.arraycopy(source, 0, result, intCount, result.length
                    - intCount);
        } else {
            int rightShiftCount = 32 - count;

            result[result.length - 1] = 0;
            for (int i = result.length - 1; i > intCount; i--) {
                result[i] |= source[i - intCount - 1] >>> rightShiftCount;
                result[i - 1] = source[i - intCount - 1] << count;
            }
        }
    }

    /** @see BigInteger#shiftRight(int) */
    static BigInteger shiftRight(BigInteger source, int count) {
        int intCount = count >> 5; // count of integers
        count &= 31; // count of remaining bits
        if (intCount >= source.numberLength) {
            return ((source.sign < 0) ? BigInteger.MINUS_ONE : BigInteger.ZERO);
        }
        int i;
        int resLength = source.numberLength - intCount;
        int resDigits[] = new int[resLength + 1];

        shiftRight(resDigits, resLength, source.digits, intCount, count);
        if (source.sign < 0) {
            // Checking if the dropped bits are zeros (the remainder equals to
            // 0)
            for (i = 0; (i < intCount) && (source.digits[i] == 0); i++)
                ;
            // If the remainder is not zero, add 1 to the result
            if ((i < intCount)
                    || ((count > 0) && ((source.digits[i] << (32 - count)) != 0))) {
                for (i = 0; (i < resLength) && (resDigits[i] == -1); i++) {
                    resDigits[i] = 0;
                }
                if (i == resLength) {
                    resLength++;
                }
                resDigits[i]++;
            }
        }
        BigInteger result = new BigInteger(source.sign, resLength, resDigits);
        result.cutOffLeadingZeroes();
        return result;
    }

    /**
     * Performs {@code val >>= count} where {@code val} is a positive number.
     */
    static void inplaceShiftRight(BigInteger val, int count) {
        // PRE: val > 0
        int intCount = count >> 5; // count of integers
        val.numberLength -= intCount;
        shiftRight(val.digits, val.numberLength, val.digits, intCount,
                count & 31);
        val.cutOffLeadingZeroes();
    }

    /**
     * Shifts right an array of integers. Total shift distance in bits is
     * intCount * 32 + count.
     * 
     * @param result the destination array
     * @param resultLen the destination array's length
     * @param source the source array
     * @param intCount the number of elements to be shifted
     * @param count the number of bits to be shifted
     */
    static void shiftRight(int result[], int resultLen, int source[],
            int intCount, int count) {
        if (count == 0) {
            System.arraycopy(source, intCount, result, 0, resultLen);
        } else {
            int i;
            int leftShiftCount = 32 - count;

            for (i = 0; i < resultLen - 1; i++) {
                result[i] = (source[i + intCount] >>> count)
                        | (source[i + intCount + 1] << leftShiftCount);
            }
            result[i] = (source[i + intCount] >>> count);
        }
    }

}
