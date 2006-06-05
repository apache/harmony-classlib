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
 * Library that implement some logical operations over BigInteger.
 *
 * This operations are not, and, or, andNot,
 *
 * * @author Daniel Fridlender
 * @author Matthias Gallé
 * @author Mariano Heredia
 * @author Miguel Vasquez
 */
class Logical {
    /** Just to denote that can't be instantied*/
    private Logical(){
    }
    /**
     * @param val
     * @return ~val
     * @see BigInteger#not()
     */
    static BigInteger not(BigInteger val) {
        BigInteger res = new BigInteger(val.first);
        
        for (int i = 0; i <= val.first; i++) {
            res.digits[i] = ~val.digits[i];
        }
        return res;
    }
    
    /**
     * @param longer
     * @param shorter
     * @return longer & shorter
     * @see BigInteger#and(BigInteger)
     */
    static BigInteger and(BigInteger longer, BigInteger shorter) {
        // pre: longer has at least as many digits as shorter
        BigInteger res = new BigInteger(longer.first);
        int i;
        
        for (i = 0; i <= shorter.first; i++) {
            res.digits[i] = (longer.digits[i] & shorter.digits[i]);
        }
        if (shorter.signum() >= 0) {
            // is positive, so the leading zeros to annul the  other digits
            res.first = shorter.first;
        } else {
            System.arraycopy(longer.digits,i, res.digits,i, longer.first+1-i);
        }
        res.updFirst();
        return res;
    }
    
    /**
     * @param longer
     * @param shorter
     * @return longer | shorter
     * @see BigInteger#or(BigInteger)
     */
    static BigInteger or(BigInteger longer, BigInteger shorter) {
        // pre: longer has at least as many digits as shorter
        BigInteger res = new BigInteger(longer.first);
        int i;
        
        for (i = 0; i <= shorter.first; i++) {
            res.digits[i] = (longer.digits[i] | shorter.digits[i]);
        }
        if (shorter.signum()  >= 0) {
            System.arraycopy(longer.digits,i, res.digits,i, longer.first+1-i);
        } else {
            res.first = shorter.first;
        }
        res.updFirst();
        return res;
    }
    
    /**
     * @param longer
     * @param shorter
     * @return longer ^ shorter
     * @see BigInteger#xor(BigInteger)
     */
    static BigInteger xor(BigInteger longer, BigInteger shorter) {
        // pre: longer has at least as many digits as shorter
        BigInteger res = new BigInteger(longer.first);
        int i;
        
        for (i = 0; i <= shorter.first; i++) {
            res.digits[i] = (longer.digits[i] ^ shorter.digits[i]);
        }
        if (shorter.digits[shorter.first] >= 0) {
            for (; i <= longer.first; i++) {
                System.arraycopy(longer.digits,i, res.digits,i, longer.first+1-i);
            }
        } else {
            for (; i <= longer.first; i++) {
                res.digits[i] = ~longer.digits[i];
            }
        }
        res.updFirst();
        return res;
    }
    
    /**
     * @param longer
     * @param shorter
     * @return longer & ~shorter
     * @see BigInteger#andNot(BigInteger)
     */
    static BigInteger andNotShorter(BigInteger longer, BigInteger shorter) {
        // pre: longer has at least as many digits as shorter
        BigInteger res = new BigInteger(longer.first);
        int i;
        
        for (i = 0; i <= shorter.first; i++) {
            res.digits[i] = (longer.digits[i] & ~shorter.digits[i]);
        }
        if (shorter.digits[shorter.first] >= 0) {
            System.arraycopy(longer.digits,i, res.digits,i, longer.first+1-i);
        } else {
            res.first = shorter.first;
        }
        res.updFirst();
        return res;
    }
    
    /**
     * @param shorter
     * @param longer
     * @return shorter & ~longer
     * @see BigInteger#andNot(BigInteger)
     */
    static BigInteger andNotLonger(BigInteger shorter, BigInteger longer) {
        // pre: longer has strictly more digits than shorter
        BigInteger res = new BigInteger(longer.first);
        int i;
        
        for (i = 0; i <= shorter.first; i++) {
            res.digits[i] = shorter.digits[i] & ~longer.digits[i];
        }
        if (shorter.digits[shorter.first] >= 0) {
            res.first = shorter.first;
        } else {
            for (; i <= longer.first; i++) {
                res.digits[i] = ~longer.digits[i];
            }
        }
        res.updFirst();
        return res;
    }
}
