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
import java.util.Arrays;

/**
 * @author Daniel Fridlender
 * @author Matthias Gallé
 * @author Mariano Heredia
 * @author Miguel Vasquez
 */

class Bitlevel {
    
    /** Just to denote that BitLevel can't be instantied*/
    private Bitlevel() {
    }
    
    /**
     * returns val >> n
     * @param val BigInteger to be shifted
     * @param n
     * @return val >> n
     * @see BigInteger#shiftRight(int) 
     */
    static BigInteger shiftRight(BigInteger val,  int n) {
        // PRE: n > 0
    	int diff = bitLength(val) - n;
        int first = diff >> 5;
        BigInteger res = new BigInteger(Math.max(first, 0));
        
        inplaceShiftRight(res, val, n, diff);
        return res;
    }
    
    /**
     * returns val << n
     * @param val
     * @param n
     * @see BigInteger#shiftLeft(int) 
     */
    static BigInteger shiftLeft(BigInteger val,  int n) {
        // PRE: n > 0
        int first = (bitLength(val) + n) >>> 5;
        BigInteger res = new BigInteger(first);
        
        inplaceShiftLeft(res, val, n,  first);
        return res;
    }
    
    /**
     * returns the number of bits in the minimal two-complement representation of val
     * @param val
     * @see BigInteger#bitLength() 
     */
    static int bitLength(BigInteger val) {
        int digit = val.digits[val.first];
        int bLength = (val.first + 1) << 5;	// maximum possible result
        
        if (digit < 0) {
            digit = ~digit;   // reduce problem to positive case
        }
        bLength -= Integer.numberOfLeadingZeros(digit);
        return bLength;
    }
    
    /**
     * returns the position of the lowest bit of val that is set
     * @param val
     * @see BigInteger#getLowestSetBit() 
     */
    static int getLowestSetBit( BigInteger val) {
        int i;
        int res;
        
        for (i = 0; (i <= val.first) && (val.digits[i] == 0); i++);
        
        res = ((i > val.first)
            ? res = -1
            : (i << 5) + Integer.numberOfTrailingZeros(val.digits[i]));
        return res;
    }
    
    /**
     * returns the number of bits of val that differ from its sign bit
     * @see BigInteger#bitCount()
     */
    static int bitCount( BigInteger val) {
        int bcount = 0;
        
        for (int i = val.first; i >= 0; i--) {
            bcount += Integer.bitCount(val.digits[i]);
        }
        return ((val.digits[val.first] >= 0) 
                ? bcount 
                : ((val.first + 1) << 5) - bcount);
    }
    
    /**
     * returns a new val with the n-th bit set
     * @see BigInteger#setBit(int)
     */
    static BigInteger setBit(BigInteger val,  int n) {
        // PRE:  n >= 0  &&  ! testBit(val,n)
        int whichDigit = n >> 5;
        int position = n % 32;
        int mask = (1 << position);
        BigInteger res;
        
        if ((whichDigit < val.first) || ((whichDigit == val.first) && (position != 31))) {
            res = val.clone();
            res.digits[whichDigit] |= mask;
        } else {
            res = new BigInteger((position != 31) ? whichDigit : whichDigit + 1);
            System.arraycopy(val.digits, 0, res.digits, 0, val.first + 1);
            // we are asuming here that the remaining positions are initialized automatically in 0
            res.digits[whichDigit] |= mask;
        }
        res.updFirst();
        return res;
    }
    
    /**
     * returns a new val with the n-th bit unset
     * @see BigInteger#clearBit(int)
     */
    static BigInteger clearBit( BigInteger val,  int n) {
        // PRE: n >= 0 && testBit(val,n)
        int whichDigit = n >> 5;
        int position = n % 32;
        int mask = ~(1 << position);
        BigInteger res;
        
        if ((whichDigit < val.first) || ((whichDigit == val.first) && (position != 31))) {
            res = val.clone();
            res.digits[whichDigit] &= mask;
        } else {
            // the nth bit is a sign bit
            res = new BigInteger((position != 31) ? whichDigit : whichDigit + 1);
            System.arraycopy(val.digits, 0, res.digits, 0, val.first + 1);
            Arrays.fill(res.digits, val.first + 1, res.first + 1, -1);
            res.digits[whichDigit] &= mask;
        }
        res.updFirst();
        return res;
    }
    
    
//	 In Place Methods
    
    /**
     * Equal to shiftLeft(BigInteger, int), but this method take a paramether more, the destiny where the
     * result is to be saved. res = val << n.
     * @param res must have enough place to store the result
     * @param val
     * @param n
     */
    static void inplaceShiftLeft(BigInteger res, BigInteger val, int n) {
    	inplaceShiftLeft(res,val,n,(bitLength(val) + n) >> 5);
    }

    /**
     * Performs valRes <<= n.
     */
    static void inplaceShiftLeft(BigInteger valRes, int n) {
        inplaceShiftLeft(valRes, valRes, n);
    }
    
    /**
     * Performs res = val << n
     * 
     * @param res must have enough place to store the result
     * @param val
     * @param n
     * @param first the position of the first digit of res, once performed the operation
     */
    static void inplaceShiftLeft( BigInteger res,  BigInteger val,  int n,  int first) {
        // PRE: n > 0
        // res has enough space and his array is initialized in zero
        int dshift = n >> 5;             // shift in digits
        int bshift1 = n % 32;            // remaining shift in bits
        int bshift2 = 32 - bshift1;      // complement of bshift1
        int i, j;
        //Note: must copy from most significative to least because is an
        //inplace methods, otherwise will erase source digits
        if (n % 32 == 0) {
            // this is an ugly case, but a>>(32*k) == a>>0 == a)
            System.arraycopy(val.digits,0, res.digits,dshift , val.first + 1);
        } else {
            if (dshift+1 > first) {
                i = dshift + 1;
                j = 0;
            } else {
                i = first;
                j = first - dshift - 1;
            }

            if (first > dshift) {
                res.digits[i] = (j == val.first) ? (val.digits[j] >> bshift2)
                : (val.digits[j] >>> bshift2) | (val.digits[j+1] << bshift1);
            }
            
            //Moving ints from most significative, to least
            for (i = first - 1, j = i - dshift - 1; i > dshift; i--, j--) {
                res.digits[i] = (val.digits[j] >>> bshift2) | (val.digits[j+1] << bshift1);
            }
            res.digits[dshift] = val.digits[0] << bshift1;
        }
        // Wachout: we asume that the initial positions hold's 0 values
        res.first = first;
    }
            
    /**
     * Equals to #shiftRight(BigInteger res, BigInteger val, int n), but {@code
     * res} and {@code val} are the same reference. valRes >>= n.
     */
    static void inplaceShiftRight( BigInteger valRes, int n ){
        inplaceShiftRight( valRes, valRes, n );
    } 
    
    /**
     * Equal to shiftRight(BigInteger, int), but this method take a paramether more, the destiny where the
     * result is to be saved. res = val >> n.
     * @param res must have enough space to save the result
     */
    static void inplaceShiftRight(BigInteger res, BigInteger val, int n) {
        inplaceShiftRight(res, val, n, bitLength(val) - n);
    }
    
    /**
     *
     * @param res
     * @param val
     * @param n
     * @param diff == bitLength(val) - n. Takes as a parameter so it must not be calculate again.
     */
    static void inplaceShiftRight( BigInteger res,  BigInteger val,  int n,  int diff) {
        // PRE: n > 0
        int dshift = n >> 5;                 // shift in digits
        int bshift1 = n % 32;                // remaining shift in bits
        int bshift2 = 32 - bshift1;          // complement of bshift1
        int first = diff >> 5;
        int i, j;
        
        if (diff < 0) {
            BigInteger.copy((val.signum() >= 0) ? BigInteger.ZERO : BigInteger.MINUS_ONE, res );
        } else if (n%32 == 0){
            System.arraycopy(val.digits, dshift, res.digits, 0, first + 1);
            res.first = first;
        } else{
            for (i = 0, j = dshift; i < first; i++, j++) {
                res.digits[i] = (val.digits[j] >>> bshift1) | (val.digits[j+1] << bshift2);
            }
            if (j == val.first) {
                res.digits[i] = (val.digits[j] >> bshift1);   // signed shift this time
            } else {
                res.digits[i] = (val.digits[j] >>> bshift1) | (val.digits[j+1] << bshift2);
            }
            res.first = first;
        }
    }

}
