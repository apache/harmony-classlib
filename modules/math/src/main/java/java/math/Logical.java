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
 * The library implements some logical operations over {@code BigInteger}. The
 * operations that porvides are listed below.
 * <ul type="circle">
 * <li>not</li>
 * <li>and</li>
 * <li>or</li>
 * <li>xor</li>
 * </ul>
 * 
 * @author Daniel Fridlender
 * @author Matthias Gallé
 * @author Mariano Heredia
 * @author Miguel Vasquez
 */
class Logical {

    /** Just to denote that this class can't be instantied. */
    private Logical() {}

    /**
     * Implements the element by element bitwise operations on two integer
     * arrays. Arrays can be processed partially starting from 0 element.
     * 
     * @param dest the result array
     * @param a the first source array
     * @param aLen the number of elements of 'a' to be processed
     * @param b the second source array
     * @param bLen the number of elements of 'b' to be processed
     */
    static void bitOperation(int dest[], int a[], int aLen, int b[], int bLen,
            int operation) {
        int i = 0;

        switch (operation) {
            case 1: // and
                for (; i < bLen; i++) {
                    dest[i] = a[i] & b[i];
                }
                break;
            case 2: // not
                for (; i < bLen; i++) {
                    dest[i] = ~b[i];
                }
                break;
            case 3: // or
                for (; i < bLen; i++) {
                    dest[i] = a[i] | b[i];
                }
                break;
            case 4: // xor
                for (; i < bLen; i++) {
                    dest[i] = a[i] ^ b[i];
                }
                break;
        }
        while (i < aLen) {
            dest[i] = a[i++];
        }
    }

    /** @see BigInteger#not() */
    static BigInteger not(BigInteger val) {
        if (val.sign == 0) {
            return BigInteger.MINUS_ONE;
        }
        int resDigits[] = new int[val.numberLength + 1];
        int i;

        if (val.sign > 0) {
            // ~val = -val + 1
            if (val.digits[val.numberLength - 1] != -1) {
                for (i = 0; val.digits[i] == -1; i++)
                    ;
            } else {
                for (i = 0; (i < val.numberLength) && (val.digits[i] == -1); i++)
                    ;
                if (i == val.numberLength) {
                    resDigits[i] = 1;
                    return new BigInteger(-val.sign, i + 1, resDigits);
                }
            }
            // Here a carry 1 was generated
        } else {// (val.sign < 0)
            // ~val = -val - 1
            for (i = 0; val.digits[i] == 0; i++) {
                resDigits[i] = -1;
            }
            // Here a borrow -1 was generated
        }
        // Now, the carry/borrow can be absorbed
        resDigits[i] = val.digits[i] + val.sign;
        // Coping the remaining unchanged digit
        for (i++; i < val.numberLength; i++) {
            resDigits[i] = val.digits[i];
        }
        return new BigInteger(-val.sign, i, resDigits);
    }

    /** @see BigInteger#and(BigInteger) */
    static BigInteger and(BigInteger val, BigInteger that) {
        // This let us to throw an eventual NullPointerException if (that ==
        // null)
        if (that.sign == 0) {
            return BigInteger.ZERO;
        }
        if (val.sign == 0) {
            return BigInteger.ZERO;
        }
        int resSign = (val.sign & that.sign);
        int resLength;
        int resDigits[];
        int valNeg[] = null;
        int thatNeg[] = null;

        if (val.sign < 0) {
            valNeg = new int[val.numberLength];
            System.arraycopy(val.digits, 0, valNeg, 0, val.numberLength);
        }
        if (that.sign < 0) {
            thatNeg = new int[that.numberLength];
            System.arraycopy(that.digits, 0, thatNeg, 0, that.numberLength);
        }
        // some cases when either val or that is positive
        if ((val.sign > 0) || (that.sign > 0)) {
            if ((val.sign > 0) && (that.sign > 0)) {
                // both are positive;
                // the number of digits to AND is the length of the shorter
                // array
                int andLen = Math.min(val.numberLength, that.numberLength);
                resLength = andLen;
                resDigits = new int[resLength];
                bitOperation(resDigits, val.digits, andLen, that.digits,
                        andLen, 1);
            } else {
                if (val.numberLength > that.numberLength) {
                    // val is longer than that
                    if (val.sign > 0) {
                        // val is positive and that is negative;
                        // all digits should be and'ed
                        resLength = val.numberLength;
                        resDigits = new int[resLength];
                        BitLevel.setTrueCoded(thatNeg, that.numberLength);
                        bitOperation(resDigits, val.digits, val.numberLength,
                                thatNeg, that.numberLength, 1);
                    } else {
                        // val is negative and that is positive
                        // the number of digits to AND is the length of the
                        // 'that' array
                        resLength = that.numberLength;
                        resDigits = new int[resLength];
                        BitLevel.setTrueCoded(valNeg, val.numberLength);
                        bitOperation(resDigits, valNeg, that.numberLength,
                                that.digits, that.numberLength, 1);
                    }
                } else {
                    // val is shorter than that
                    if (val.sign > 0) {
                        // val is positive and that is negative;
                        // the number of digits to AND is the length of the
                        // 'val' array
                        resLength = val.numberLength; // + 1?
                        resDigits = new int[resLength];
                        BitLevel.setTrueCoded(thatNeg, that.numberLength);
                        bitOperation(resDigits, thatNeg, val.numberLength,
                                val.digits, val.numberLength, 1);
                    } else {
                        // val is negative and that is positive
                        // the number of digits to AND is the length of the
                        // 'that' array
                        resLength = that.numberLength;
                        resDigits = new int[resLength];
                        BitLevel.setTrueCoded(valNeg, val.numberLength);
                        bitOperation(resDigits, that.digits, that.numberLength,
                                valNeg, val.numberLength, 1);
                    }
                }
            }
        } else {
            // cases when both numbers are negative or
            // either val or that is positive but both are of the same length
            if (val.sign < 0) {
                BitLevel.setTrueCoded(valNeg, val.numberLength);
            }
            if (that.sign < 0) {
                BitLevel.setTrueCoded(thatNeg, that.numberLength);
            }
            // The resulting length should have one extra element
            // for possible carry = 1 returned from BitLevel.setTrueCoded for a
            // negative number
            resLength = 1 + Math.max(val.numberLength, that.numberLength);
            resDigits = new int[resLength];
            if (val.numberLength >= that.numberLength) {
                bitOperation(resDigits, (valNeg == null) ? val.digits : valNeg,
                        val.numberLength, (thatNeg == null) ? that.digits
                                : thatNeg, that.numberLength, 1);
            } else {
                bitOperation(resDigits, (thatNeg == null) ? that.digits
                        : thatNeg, that.numberLength,
                        (valNeg == null) ? val.digits : valNeg,
                        val.numberLength, 1);
            }
        }
        if (resSign < 0) {
            resDigits[resLength - 1] = BitLevel.setTrueCoded(resDigits,
                    resLength);
        }
        BigInteger result = new BigInteger(resSign, resLength, resDigits);
        result.cutOffLeadingZeroes();
        return result;
    }

    /** @see BigInteger#or(BigInteger) */
    static BigInteger or(BigInteger val, BigInteger that) {
        if (that.sign == 0) {
            return val;
        }
        if (val.sign == 0) {
            return that;
        }
        int resSign = (val.sign | that.sign);
        int resLength;
        int resDigits[];
        int valNeg[] = null;
        int thatNeg[] = null;

        // some cases when either val or that is negative
        if ((val.sign < 0) || (that.sign < 0)) {
            if (val.sign < 0) {
                valNeg = new int[val.numberLength];
                System.arraycopy(val.digits, 0, valNeg, 0, val.numberLength);
            }
            if (that.sign < 0) {
                thatNeg = new int[that.numberLength];
                System.arraycopy(that.digits, 0, thatNeg, 0, that.numberLength);
            }
            if ((val.sign < 0) && (that.sign < 0)) {
                // both are negative
                int orLen = Math.min(val.numberLength, that.numberLength);
                resLength = orLen + 1;
                resDigits = new int[resLength];
                BitLevel.setTrueCoded(valNeg, orLen);
                BitLevel.setTrueCoded(thatNeg, orLen);
                bitOperation(resDigits, valNeg, orLen, thatNeg, orLen, 3);
                resDigits[resLength - 1] = -1;
            } else {
                if (val.numberLength > that.numberLength) {
                    // val is longer than that
                    if (val.sign > 0) {
                        // val is positive and that is negative
                        resLength = that.numberLength + 1;
                        resDigits = new int[resLength];
                        BitLevel.setTrueCoded(thatNeg, that.numberLength);
                        bitOperation(resDigits, val.digits, that.numberLength,
                                thatNeg, that.numberLength, 3);
                        resDigits[resLength - 1] = -1;
                    } else {
                        // val is negative and that is positive
                        resLength = val.numberLength;
                        resDigits = new int[resLength];
                        BitLevel.setTrueCoded(valNeg, val.numberLength);
                        bitOperation(resDigits, valNeg, val.numberLength,
                                that.digits, that.numberLength, 3);
                    }
                } else {
                    // that is longer than val
                    if (val.sign > 0) {
                        // val is positive and that is negative
                        resLength = that.numberLength; // + 1?
                        resDigits = new int[resLength];
                        BitLevel.setTrueCoded(thatNeg, that.numberLength);
                        bitOperation(resDigits, thatNeg, that.numberLength,
                                val.digits, val.numberLength, 3);
                    } else {
                        // that is positive and val is negative
                        resLength = val.numberLength + 1;
                        resDigits = new int[resLength];
                        BitLevel.setTrueCoded(valNeg, val.numberLength);
                        bitOperation(resDigits, that.digits, val.numberLength,
                                valNeg, val.numberLength, 3);
                        resDigits[resLength - 1] = -1;
                    }
                }
            }
        } else {
            // cases when both numbers are positive and
            // either val or that is negative but both are of the same length
            resLength = Math.max(val.numberLength, that.numberLength);
            resDigits = new int[resLength];
            if (val.numberLength >= that.numberLength) {
                bitOperation(resDigits, (valNeg == null) ? val.digits : valNeg,
                        val.numberLength, (thatNeg == null) ? that.digits
                                : thatNeg, that.numberLength, 3);
            } else {
                bitOperation(resDigits, (thatNeg == null) ? that.digits
                        : thatNeg, that.numberLength,
                        (valNeg == null) ? val.digits : valNeg,
                        val.numberLength, 3);
            }
        }
        if (resSign < 0) {
            BitLevel.setTrueCoded(resDigits, resLength);
        }
        BigInteger result = new BigInteger(resSign, resLength, resDigits);
        result.cutOffLeadingZeroes();
        return result;
    }

    /** @see BigInteger#xor(BigInteger) */
    static BigInteger xor(BigInteger val, BigInteger that) {
        if (that.sign == 0) {
            return val;
        }
        if (val.sign == 0) {
            return that;
        }
        int resSign = (val.sign == that.sign) ? 1 : -1;
        int resLength = Math.max(val.numberLength, that.numberLength);
        int resDigits[] = new int[resLength];
        int valNeg[] = null;
        int thatNeg[] = null;
        if (val.sign < 0) {
            valNeg = new int[val.numberLength];
            System.arraycopy(val.digits, 0, valNeg, 0, val.numberLength);
            BitLevel.setTrueCoded(valNeg, val.numberLength);
        }
        if (that.sign < 0) {
            thatNeg = new int[that.numberLength];
            System.arraycopy(that.digits, 0, thatNeg, 0, that.numberLength);
            BitLevel.setTrueCoded(thatNeg, that.numberLength);
        }
        // First make XOR for N elements where N is the length of a shorter
        // array.
        int opLen = Math.min(val.numberLength, that.numberLength);
        bitOperation(resDigits, (valNeg == null) ? val.digits : valNeg, opLen,
                (thatNeg == null) ? that.digits : thatNeg, opLen, 4);
        // Then define top elements.
        if (val.numberLength != that.numberLength) {
            int i;
            for (i = opLen; i < resLength; i++) {
                if (val.sign == that.sign) {
                    // the numbers have the same signs
                    resDigits[i] = ((val.numberLength > that.numberLength) ? (valNeg == null) ? val.digits[i]
                            : ~valNeg[i]
                            : (thatNeg == null) ? that.digits[i] : ~thatNeg[i]);
                } else {
                    // the numbers have different signs
                    resDigits[i] = ((val.numberLength >= that.numberLength) ? (val.sign < 0) ? valNeg[i]
                            : ~val.digits[i]
                            : (val.sign < 0) ? ~that.digits[i] : thatNeg[i]);
                }
            }
        }
        if (resSign < 0) {
            BitLevel.setTrueCoded(resDigits, resLength);
        }
        BigInteger result = new BigInteger(resSign, resLength, resDigits);
        result.cutOffLeadingZeroes();
        return result;
    }
}
