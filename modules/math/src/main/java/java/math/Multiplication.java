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
 * Static library that provides all multiplication of {@link BigInteger} methods.
 *
 * @author Daniel Fridlender
 * @author Matthias Gallé
 * @author Mariano Heredia
 * @author Miguel Vasquez
 */
class Multiplication {

    /** Just to denote that this class can't be instantied. */
    private Multiplication() {}

    /**
     * Break point in digits (number of {@code int} elements)
     * between Karatsuba and Pencil and Paper multiply.
     */
    static final int whenUseKaratsuba = 63; // an heuristic value

    static final int tenPows[] = { 1, 10, 100, 1000, 10000, 100000, 1000000,
            10000000, 100000000, 1000000000 };

    static final BigInteger[] bigTenPows = new BigInteger[32];

    static {
        int i = 0;
        long val = 1;
        for (; i <= 18; i++) {
            bigTenPows[i] = BigInteger.valueOf(val);
            val *= 10L;
        }
        for (; i < bigTenPows.length; i++) {
            bigTenPows[i] = bigTenPows[i - 1].multiply(BigInteger.TEN);
        }
    }

    /**
     * Performs a multiplication of two BigInteger and hides the algorithm used.
     * @see BigInteger#multiply(BigInteger)
     */
    static BigInteger multiply(BigInteger x, BigInteger y) {
        return karatsuba(x, y);
    }

    /**
     * Performs the multiplication with the Karatsuba's algorithm.
     * <b>Karatsuba's algorithm:</b>
     *<tt>
     *             u = u<sub>1</sub> * B + u<sub>0</sub><br>
     *             v = v<sub>1</sub> * B + v<sub>0</sub><br>
     *
     *
     *  u*v = (u<sub>1</sub> * v<sub>1</sub>) * B<sub>2</sub> + ((u<sub>1</sub> - u<sub>0</sub>) * (v<sub>0</sub> - v<sub>1</sub>) + u<sub>1</sub> * v<sub>1</sub> +
     *  u<sub>0</sub> * v<sub>0</sub> ) * B + u<sub>0</sub> * v<sub>0</sub><br>
     *</tt>
     * @param op1 first factor of the product
     * @param op2 second factor of the product
     * @return op1*op2
     * @see #multiply(BigInteger, BigInteger)
     */
    static BigInteger karatsuba(BigInteger op1, BigInteger op2) {
        if (Math.min(op1.numberLength, op2.numberLength) < whenUseKaratsuba) {
            return multiplyPAP(op2, op1);
        }
        BigInteger upperOp1, lowerOp1, upperOp2, lowerOp2;
        int bitndiv2 = Math.max(op1.numberLength, op2.numberLength) >> 1;
        int ndiv2 = bitndiv2 << 5;
        /*  Karatsuba:  u = u1*B + u0
         *              v = v1*B + v0
         *
         *  u*v = (u1*v1)*B^2 + ((u1-u0)*(v0-v1) + u1*v1 + u0*v0)*B + u0*v0
         */
        upperOp1 = op1.shiftRight(ndiv2);
        upperOp2 = op2.shiftRight(ndiv2);
        lowerOp1 = op1.subtract(upperOp1.shiftLeft(ndiv2));
        lowerOp2 = op2.subtract(upperOp2.shiftLeft(ndiv2));

        BigInteger upper = karatsuba(upperOp1, upperOp2);
        BigInteger lower = karatsuba(lowerOp1, lowerOp2);
        BigInteger middle = karatsuba(upperOp1.subtract(lowerOp1), lowerOp2
                .subtract(upperOp2));
        middle = middle.add(upper).add(lower);
        middle = middle.shiftLeft(ndiv2);
        upper = upper.shiftLeft(ndiv2 << 1);

        return upper.add(middle).add(lower);
    }

    /**
     * Multiplies two BigIntegers.
     * Implements traditional scholar algorithm described by Knuth.
     *
     * <br><tt>
     *         <table border="0">
     * <tbody>
     *
     *
     * <tr>
     * <td align="center">A=</td>
     * <td>a<sub>3</sub></td>
     * <td>a<sub>2</sub></td>
     * <td>a<sub>1</sub></td>
     * <td>a<sub>0</sub></td>
     * <td></td>
     * <td></td>
     * </tr>
     *
     *<tr>
     * <td align="center">B=</td>
     * <td></td>
     * <td>b<sub>2</sub></td>
     * <td>b<sub>1</sub></td>
     * <td>b<sub>1</sub></td>
     * <td></td>
     * <td></td>
     * </tr>
     *
     * <tr>
     * <td></td>
     * <td></td>
     * <td></td>
     * <td>b<sub>0</sub>*a<sub>3</sub></td>
     * <td>b<sub>0</sub>*a<sub>2</sub></td>
     * <td>b<sub>0</sub>*a<sub>1</sub></td>
     * <td>b<sub>0</sub>*a<sub>0</sub></td>
     * </tr>
     *
     * <tr>
     * <td></td>
     * <td></td>
     * <td>b<sub>1</sub>*a<sub>3</sub></td>
     * <td>b<sub>1</sub>*a<sub>2</sub></td>
     * <td>b<sub>1</sub>*a1</td>
     * <td>b<sub>1</sub>*a0</td>
     * </tr>
     *
     * <tr>
     * <td>+</td>
     * <td>b<sub>2</sub>*a<sub>3</sub></td>
     * <td>b<sub>2</sub>*a<sub>2</sub></td>
     * <td>b<sub>2</sub>*a<sub>1</sub></td>
     * <td>b<sub>2</sub>*a<sub>0</sub></td>
     * </tr>
     *
     *<tr>
     * <td></td>
     *<td>______</td>
     * <td>______</td>
     * <td>______</td>
     * <td>______</td>
     * <td>______</td>
     * <td>______</td>
     *</tr>
     *
     * <tr>
     *
     * <td align="center">A*B=R=</td>
     * <td align="center">r<sub>5</sub></td>
     * <td align="center">r<sub>4</sub></td>
     * <td align="center">r<sub>3</sub></td>
     * <td align="center">r<sub>2</sub></td>
     * <td align="center">r<sub>1</sub></td>
     * <td align="center">r<sub>0</sub></td>
     * <td></td>
     * </tr>
     *
     * </tbody>
     * </table>
     *
     *</tt>
     *
     * @param op1 first factor of the multiplication <code> op1 >= 0 </code>
     * @param op2 second factor of the multiplication <code> op2 >= 0 </code>
     * @return a <code>BigInteger</code> of value <code> op1 * op2 </code>
     */
    static BigInteger multiplyPAP(BigInteger a, BigInteger b) {
        BigInteger tmp;
        if (a.numberLength < b.numberLength) {
            tmp = a;
            a = b;
            b = tmp;
        }
        int aLen = a.numberLength;
        int bLen = b.numberLength;
        int resLength = aLen + bLen;
        // a special case when both numbers don't exceed int
        if (resLength == 2) {
            long val = ((long) a.digits[0] & 0xFFFFFFFFL)
                    * ((long) b.digits[0] & 0xFFFFFFFFL);
            int sign = a.sign != b.sign ? -1 : 1;
            int valueLo = (int) val;
            int valueHi = (int) (val >>> 32);
            return ((valueHi == 0) ? new BigInteger(sign, valueLo)
                    : new BigInteger(sign, 2, new int[] { valueLo, valueHi }));
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
                carry += ((long) aDigits[i] & 0xFFFFFFFFL)
                        * ((long) bDigits[j] & 0xFFFFFFFFL)
                        + ((long) resDigits[m] & 0xFFFFFFFFL);
                resDigits[m] = (int) carry;
                carry >>>= 32;
            }
            resDigits[i + j] = (int) carry;
        }
        BigInteger result = new BigInteger((a.sign == b.sign) ? 1 : -1,
                resLength, resDigits);
        result.cutOffLeadingZeroes();
        return result;
    }

    /**
     * Multiplies an array of integers by an integer value.
     * @param intArray the array of integers
     * @param arrayLength the number of elements of intArray to be multiplied
     * @param factor the multiplier
     * @return the top digit of production
     */
    static int multiplyByInt(int intArray[], final int arrayLength,
            final int factor) {
        long carry = 0;

        for (int i = 0; i < arrayLength; i++) {
            carry += ((long) intArray[i] & 0xFFFFFFFFL)
                    * ((long) factor & 0xFFFFFFFFL);
            intArray[i] = (int) carry;
            carry >>>= 32;
        }
        return (int) carry;
    }

    static BigInteger multiplyByPositiveInt(BigInteger a, int b) {
        int resSign = a.sign;
        if (resSign == 0) {
            return BigInteger.ZERO;
        }
        int aNumberLength = a.numberLength;
        int[] aDigits = a.digits;
        if (aNumberLength == 1) {
            long res = ((long) aDigits[0] & 0xFFFFFFFFL) * ((long) b);
            int resLo = (int) res;
            int resHi = (int) (res >>> 32);
            if (resHi == 0) {
                return new BigInteger(resSign, resLo);
            }
            return new BigInteger(resSign, 2, new int[] { resLo, resHi });
        }
        int resLength = aNumberLength + 1;
        int resDigits[] = new int[resLength];
        long carry = 0;
        // common case
        int i;
        for (i = 0; i < aNumberLength; i++) {
            carry += ((long) aDigits[i] & 0xFFFFFFFFL)
                    * ((long) b & 0xFFFFFFFFL);
            resDigits[i] = (int) carry;
            carry >>>= 32;
        }
        resDigits[i] = (int) carry;
        BigInteger result = new BigInteger(resSign, resLength, resDigits);
        result.cutOffLeadingZeroes();
        return result;
    }

    /**
     * Multiplies a number by a power of ten.
     * This method could be used in {@code BigDecimal} class.
     * @param val the number to be scaled
     * @param exp a positive exponent
     * @return {@code val * 10<sup>exp</sup>}
     */
    static BigInteger multiplyByTenPow(BigInteger val, int exp) {
        return ((exp < 10) ? multiplyByPositiveInt(val, tenPows[exp]) : val
                .multiply(getTenPow(exp)));
    }

    /**
     * Generates a power of ten, using the first powers cached in an array. 
     * @param exp a positive exponent
     * @return {@code 10<sup>exp</sup>}
     */
    static BigInteger getTenPow(int exp) {
        return ((exp < bigTenPows.length) ? bigTenPows[exp] : BigInteger.TEN
                .pow(exp));
    }

    /**
     * Exponentiation algorithm using squaring algorithm.
     * @param base
     * @param exponent must be positive
     * @ar.org.fitc.ref "http://en.wikipedia.org/wiki/Exponentiating_by_squaring"
     */
    static BigInteger pow(BigInteger base, int exponent) {
        // PRE: exp > 0
        BigInteger res = BigInteger.ONE;
        BigInteger acc = base;

        for (; exponent != 1; exponent >>= 1) {
            if ((exponent & 1) != 0) {
                // if odd, multiply one more time by acc
                res = res.multiply(acc);
            }
            // acc = base^(2^i)
            acc = acc.multiply(acc); // squaring            
        }
        // exponent == 1, multiply one more time
        res = res.multiply(acc);
        return res;
    }

}