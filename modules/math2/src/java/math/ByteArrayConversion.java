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


/**
 * Provides translations between BigInteger's and byte array's holding the binary
 * two complement notation of a number
 *
 */
class ByteArrayConversion {
    /** Just to denote that can't be instantied*/
    private ByteArrayConversion(){
    }
    
    /**
     * Translate the number represented in a byte array (in binary two complement notation)
     * into a BigInteger.
     * @param res BigInteger that will hold the result (must have enough space)
     * @param val representing the a number in two complement.
     * @see BigInteger#BigInteger(byte[])
     */
    static void fromByteArray(BigInteger res, byte[] val) {
        // pre: val.length > 0, res created with the necessary length
        
        int j = val.length - 1;         // index of the last byte of val (big-endian)
        int digit;
        
        // set all except the leading digit
        for (int i = 0; i < res.first; i++, j = j - 4) {
            // digit = magnitude[j-3...j]
            digit = (int) val[j - 3] & 0xFF;
            digit <<= 8;
            digit |= val[j - 2] & 0xFF;
            digit <<= 8;
            digit |= val[j - 1] & 0xFF;
            digit <<= 8;
            digit |= val[j] & 0xFF;
            res.digits[i] = digit;
        }
        
        // set the leading digit
        digit = val[0]; // don't apply mask: preserves sign
        for (int k = 1; k <= j; k++) {
            digit <<= 8;
            digit |= val[k] & 0xFF;
        }
        res.digits[res.first] = digit;
        
        res.updFirst();
    }
    
    /**
     * Determines if all bytes of an array are 0
     * @param bs array to be tested
     * @return true iif (\forall i : 0<=i<bs.length : bs[i] == 0)
     */
    static boolean allZero(byte[] bs){
        boolean res = true;
        for (int i = 0; i < bs.length && res; i++) {
            res = (bs[i] == 0);
        }
        return res;
    }
    
    /**
     * Translate the magnitude represented in <code>magnitude</code> to <code>res</code>,
     * representing it as a negative two-complement number.
     *
     * @param res BigInteger that will hold the result (must have enough space)
     * @param magnitude byte array representing the magnitude of a negative number in two complement.
     * @see BigInteger#BigInteger(int,byte[])
     */
    static void fromMinusMagnitude(BigInteger res, byte[] magnitude){
        // pre: magnitude.length > 0, res created with necessary length
        
        int j = magnitude.length - 1; // index of the last byte of magnitude (big-endian)
        int digit;
        boolean startNegate = false;
        
        // set all except leading digit
        for (int i = 0; i < res.first; i++, j = j - 4) {
            // this loop not only pack four byte in one int, but also negate the TwoComplement number on the fly
            digit = (int) magnitude[j - 3] & 0xFF;
            digit <<= 8;
            digit |= magnitude[j - 2] & 0xFF;
            digit <<= 8;
            digit |= magnitude[j - 1] & 0xFF;
            digit <<= 8;
            digit |= magnitude[j] & 0xFF;
            
            if (!startNegate) {
                if (digit != 0) {
                    startNegate = true;
                    res.digits[i] = -digit;
                }
            } else {
                res.digits[i] = ~digit;
            }
        }
        
        // set leading digit
        digit = 0;
        for (int k = 0; k <= j; k++) {
            digit <<= 8;
            digit |= magnitude[k] & 0xFF;
        }
        res.digits[res.first] = startNegate ? ~digit : -digit;
        res.updFirst();
    }
    
    /**
     * Translate the magnitude represented in <code>magnitude</code> to <code>res</code>,
     * representing it as a positive two-complement number.
     *
     * @param res BigInteger that will hold the result (must have enough space)
     * @param magnitude byte array representing the magnitude of a positive number in two complement.
     * @see BigInteger#BigInteger(int,byte[])
     */
    static void fromPlusMagnitude(BigInteger res, byte[] magnitude) {
        // pre: magnitude.length > 0, res created with necessary length
        
        int j = magnitude.length - 1; // index of the last byte of magnitude (big-endian)
        int digit;
        
        // set all except leading digit
        for (int i = 0; i < res.first; i++, j = j - 4) {
            // digit = magnitude[j-3...j]
            digit = (int) magnitude[j - 3] & 0xFF;
            digit <<= 8;
            digit |= magnitude[j - 2] & 0xFF;
            digit <<= 8;
            digit |= magnitude[j - 1] & 0xFF;
            digit <<= 8;
            digit |= magnitude[j] & 0xFF;
            res.digits[i] = digit;
        }
        
        // set leading digit
        digit = 0;
        for (int k = 0; k <= j; k++) {
            digit <<= 8;
            digit |= magnitude[k] & 0xFF;
        }
        res.digits[res.first] = digit;
        res.updFirst();
    }
    
    
    /**
     * Converts a MyInt into a byte array, holding the same number in two complement notation.
     * Performs the inverse of {@link #fromByteArray(BigInteger, byte[])}
     * @param val the MyInt to be converted
     * @return a byte array numerical equal to {@code val}
     * @see #fromByteArray(BigInteger, byte[])
     * @see BigInteger#toByteArray()
     */
    static byte[] toByteArray(BigInteger val){
        byte[] res;
        int digit;
        
        // FIRST: create the minimal byte array for the result
        int j = val.bitLength()/8 + 1; // number of bytes to accommodate: ceil(bitLength+1)/8
        
        res = new byte[j];
        
        j--; // index of last byte (big-endian)
        
        // SECOND: set all bytes except those corresponding to the leading digit
        for (int i = 0; i < val.first; i++, j = j - 4) {
            digit = val.digits[i];
            res[j] = (byte) digit;
            digit >>= 8;
            res[j - 1] = (byte) digit;
            digit >>= 8;
            res[j - 2] = (byte) digit;
            digit >>= 8;
            res[j - 3] = (byte) digit;
        }
        
        // THIRD: set bytes corresponding to the leading digit
        digit = val.digits[val.first];
        res[j] = (byte) digit;
        for (int k = j - 1; k >= 0; k--) {
            digit >>= 8;
            res[k] = (byte) digit;
        }
        
        // LAST: return result
        return res;
    }
    
}

