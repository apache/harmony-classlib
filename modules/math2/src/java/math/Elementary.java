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


class Elementary {
    /** Just to denote that can't be instantied*/
    private Elementary(){
    }
    
    /**
     * @param val
     * @return -val
     * @see BigInteger#negate()
     */
    static BigInteger negate(BigInteger val) {
        BigInteger res;
        int i;
        
        if ((val.first == 0) && (val.digits[0] == 0)) { // -0 = 0
            res = BigInteger.ZERO;
        } else {
            int first = val.first + 1;
            res = new BigInteger(first);
            
            // FIRST: until the first set bit, the bits are equal (i.e. 0)
            for (i = 0; val.digits[i] == 0; i++);
            // we are asuming here that the positions are initialized automatically in 0
            
            // SECOND: the digit that cointains the first set bit is (arithmetic) negated
            res.digits[i] = -val.digits[i];
            
            // THIRD: from this position, the bits are (boolean) negated
            for (i++; i <= val.first; i++) {
                res.digits[i] = ~val.digits[i];
            }
            // negate invert the sign
            res.digits[first] = (val.digits[val.first] < 0) ? 0 : -1;
            res.updFirst();
        }
        return res;
    }
    
    /**
     * @param val1
     * @param val2
     * @return val1 + val2
     * @see BigInteger#add(BigInteger)
     */
    static BigInteger add(BigInteger val1, BigInteger val2) {
        // pre: val1 is at least as long as val2
        int first = val1.first + 1;
        BigInteger res = new BigInteger(first);
        
        inplaceAdd( res, val1, val2 );
        
        return res;
    }
    
    
    
    /**
     * Performs val1 - val2. Note that {@code val1.first} <b>must</b> be greater/equal than {@code val2.first}.
     * @see BigInteger#subtract(BigInteger)
     */
    static BigInteger subtractShorter(BigInteger val1, BigInteger val2) {
        // pre: val1 is at least as long as val2
        
        int first = val1.first + 1;
        BigInteger res = new BigInteger(first);
        int borrow = 0;
        int x;
        int y;
        int z;
        int i;
        
        for (i = 0; i <= val2.first; i++) {
            x = val1.digits[i];
            y = val2.digits[i];
            z = (x - y) - borrow;
            x = ~x;
            borrow = ((x & y) | ((x ^ y) & z)) >>> 31;
            res.digits[i] = z;
        }
        y = val2.digits[val2.first] >> 31;
        for (; (i <= val1.first) && (borrow + y != 0); i++) {
            x = val1.digits[i];
            z = (x - y) - borrow;
            x = ~x;
            borrow = ((x & y) | ((x ^ y) & z)) >>> 31;
            res.digits[i] = z;
        }
        if (i > val1.first) {
            x = val1.digits[val1.first] >> 31;
            z = (x - y) - borrow;
            res.digits[first] = z;
        } else { // borrow + y == 0
            for (; i <= val1.first; i++) {
                res.digits[i] = val1.digits[i];
            }
            res.first--;
        }
        res.updFirst();
        return res;
    }
    
    
    /**
     * Performs val1 - val2. Note that {@code val2.first} <b>must</b> be greater than {@code val1.first}.
     * @see BigInteger#subtract(BigInteger)
     */
    static BigInteger subtractLonger(BigInteger val1, BigInteger val2) {
        // pre: val2 is longer than val1
        int first = val2.first + 1;
        BigInteger res = new BigInteger(first);
        int borrow = 0;
        int x;
        int nx;
        int y;
        int z;
        int i;
        
        for (i = 0; i <= val1.first; i++) {
            x = val1.digits[i];
            y = val2.digits[i];
            z = (x - y) - borrow;
            x = ~x;
            borrow = ((x & y) | ((x ^ y) & z)) >>> 31;
            res.digits[i] = z;
        }
        x = val1.digits[val1.first] >> 31;
        nx = ~x;
        for ( ; i <= val2.first; i++) {
            y = val2.digits[i];
            z = (x - y) - borrow;
            borrow = ((nx & y) | ((nx ^ y) & z)) >>> 31;
            res.digits[i] = z;
        }
        y = val2.digits[val2.first] >> 31;
        z = (x - y) - borrow;
        res.digits[first] = z;
        res.updFirst();
        return res;
    }
    
    /**
     * @see BigInteger#compareTo(BigInteger)
     */
    static int compare(BigInteger val1, BigInteger val2) {
        int res;
        int s1 = val1.signum();
        
        int s2 = val2.signum();
        int f1 = val1.first;
        int f2 = val2.first;
        
        if (s1 > s2) {
            res = 1;
        } else if (s1 < s2) {
            res = -1;
        } else { // from now on, s1 == s2
            if ((f1 > f2) || ((f1 == f2) && (s1 * val1.digits[f1] > s1 * val2.digits[f2]))) {
                res = s1;
            } else if ((f1 < f2) || ((f1 == f2) && (s1 * val1.digits[f1] < s1 * val2.digits[f2]))) {
                res = -s1;
            } else { // from now on, (s1 == s2) && (f1 == f2) && (val1.digits[f1] == val2.digits[f2])
                res = compareBits(val1, val2);
            }
        }
        return res;
    }
    
    /**
      @see BigInteger#compareTo(BigInteger)
     */
    static private int compareBits(BigInteger val1, BigInteger val2) {
        /* pre: val1 and val2 of equal sign, equal length and equal first digit,
           the first bit difference decides */
        int res;
        int i;
        
        // skip equal digits
        for (i = val1.first - 1; i >= 0 && val1.digits[i] == val2.digits[i]; i--);
        
        if (i == -1) {
            res = 0;
        } else { // from now on i is the greatest such that val1.digits[i] != val2.digits[i]
            if ((val1.digits[i] < 0) && (val2.digits[i] >= 0)) { // compare their first bit
                res = 1;
            } else if ((val1.digits[i] >= 0) && (val2.digits[i] < 0)) { // compare their first bit
                res = -1;
            } else { // from now on val1.digits[i] and val2.digits[i] have the same sign (first bit)
                // compare remaining bits
                res = (val1.digits[i] > val2.digits[i]) ? 1 : -1;
            }
        }
        return res;
    }
    
    
    
//*************Inplace methods**************************************************
    
    /**
     * Performs a in-place negate. Create a new BigInteger only if <code>val</code> is a weird number
     *
     * Performs val = -val
     * @param val
     * @see #isWeirdNumber(BigInteger)
     */
    static void inplaceNegate(BigInteger val){
        BigInteger res;
        int i;
        
        if ((val.first == 0) && (val.digits[0] == 0)) { // -0 = 0
            res = BigInteger.ZERO;
        } else {
            int first;
            boolean isWeirdNumber = isWeirdNumber(val);
            
            if (isWeirdNumber){
                first = val.first + 1;
                if (val.first == val.digits.length-1){
                    res = new BigInteger(first);
                } else{
                    res = val;
                }
            } else{
                res = val;
                first = val.first;
            }
            
            for (i = 0; val.digits[i] == 0; i++);
            // we are asuming here that the positions are initialized automatically in 0
            res.digits[i] = -val.digits[i];
            for (i++; i <= val.first; i++) {
                res.digits[i] = ~val.digits[i];
            }
            if (isWeirdNumber) {
                res.digits[first] = (val.digits[val.first] < 0) ? 0 : -1;
                val.digits = res.digits;
                val.first = first;
            }
        }
        val.updFirst();
    }
    
    /**
     * return true iif the number is of the form 10....0 (without leading 0's)
     * @param val BigInteger to be tested
     * @return true iif val is a weird number
     */
    private static boolean isWeirdNumber(BigInteger val){
        boolean res = (val.digits[val.first] == Integer.MIN_VALUE);
        //If the first digit is the 100....0 then, could be the weird number
        if (res) {
            int index;
            for (index = 0; (index < val.first) && (val.digits[index] == 0); index++);
            //The first digit was reached and all digits are 0.............0
            res = (index == val.first);
        }
        return res;
    }
    
    /**
     * Performs val = |val|
     * @param val
     */
    static void inplaceAbs(BigInteger val) {
        if (val.signum() < 0) {
            Elementary.inplaceNegate(val);
        }
    }
    
    /**
     *Performs valRes += val2
     *@see #inplaceAdd(BigInteger, BigInteger, BigInteger)
     */
    static void inplaceAdd(BigInteger valRes, BigInteger val2){
        inplaceAdd( valRes, valRes, val2 );
    }
    
    /**
     * Perfomrs res = val + val2;
     */
    static void inplaceAdd(BigInteger res, BigInteger val1, BigInteger val2) {
        // pre: val1 is at least as long as val2
        int first = val1.first + 1;
        
        long carry = 0L;
        long s1 = (val1.digits[val1.first] >= 0) ? 0L : -1L;
        long s2 = (val2.digits[val2.first] >= 0) ? 0L : -1L;
        int i;
        
        for (i = 0; i <= val2.first; i++) {
            carry += (val1.digits[i] & 0xFFFFFFFFL)
            + (val2.digits[i] & 0xFFFFFFFFL);
            res.digits[i] = (int)carry;
            carry >>>= 32;
        }
        s2 &= 0xFFFFFFFFL;
        for (; (i <= val1.first) && (carry + s2 != 0); i++) {
            carry += (val1.digits[i] & 0xFFFFFFFFL) + s2;
            res.digits[i] = (int)carry;
            carry >>>= 32;
        }
        if (i > val1.first) {
            s1 &= 0xFFFFFFFFL;
            carry += s1 + s2;
            res.digits[first] = (int)carry;
        } else { // carry + s2 == 0
            for (; i <= val1.first; i++) {
                res.digits[i] = val1.digits[i];
            }
            res.first--;
        }
        res.updFirst();
    }
    
    
    /**
     * Perfomns addend := addend + augend
     * @param addend The BigInteger which will result in addend + augend
     * @param augend an int, that is interpreted as the signed value
     */
    static void inplaceAdd(BigInteger addend, int augend){
        assert augend >= 0;
        
        long carry;
        int i;
        long mask = (addend.first == 0) ? -1l : 0xFFFFFFFFL; // if there is more then one digit, then I must extend the sign
        carry = (((long) addend.digits[0]) & mask)
        + ((long) augend );
        addend.digits[0] = (int)carry;
        carry >>= 32;
        
        for (i = 1; (i <= addend.first) && (carry != 0); i++) {
            carry += (((long) addend.digits[i]) & 0xFFFFFFFFL);
            addend.digits[i] = (int)carry;
            carry >>= 32;
        }
        if (i > addend.first) {
            if (i < addend.digits.length){
                // There is some extra space
                addend.digits[i] = (int)carry;
                addend.first = i;
            } else{
                // What a pitty... the whole array must be copied
                int[] digits = new int[addend.first+2];
                System.arraycopy(addend.digits, 0, digits, 0, addend.digits.length);
                digits[addend.first+1] = (int) carry;
                addend.first++;
                addend.digits = digits;
            }
        }
        addend.updFirst();
        
    }
    
    /**
     * Performs a[aFirst...aFirst+length] = a[aFirst...aFirst+length] + b
     *
     * @param a with a.length >= a.length+aFirst. If this two values are equal, a[a.length+aFirst] is supposed to be 0.
     * @param aFirst
     * @param length
     * @param b b.length == length
     * @see Division#knuthDivision(BigInteger,BigInteger, int )
     */
    static void inplaceAdd(int[] a, int aFirst, int length, int[] b){
        int i;
        long carry=0;
        
        if (length+aFirst == a.length){
            length--;
        }        
        for(i=0; i <= length; i++){
            carry += (a[i+aFirst] & 0xFFFFFFFFl)+ (b[i] & 0xFFFFFFFFl);
            a[i+aFirst] = (int) carry;
            carry >>>= 32;
        }
    }
    
    /**
     * Performs a in-place substract: val1 = val1 - val2
     * val1 must have enough place to store the result (i.e. val1.bitLength() >= val2.bitLength())
     * Both should be positive (i.e. val1 >= val2)
     * @param val1 The input minuend, and the ouput result after perform val1 - val2
     * @param val2 The subtrahend
     */
    static void inplaceSubtract(BigInteger val1, BigInteger val2) {
        BigInteger res = val1;
        int borrow = 0;
        int x;
        int y;
        int z;
        int i;
        
        for (i = 0; i <= val2.first; i++) {
            x = val1.digits[i];
            y = val2.digits[i];
            z = (x - y) - borrow;
            x = ~x;
            borrow = ((x & y) | ((x ^ y) & z)) >>> 31;
            res.digits[i] = z;
        }
        y = val2.digits[val2.first] >> 31;
        for (; (i <= val1.first) && (borrow + y != 0); i++) {
            x = val1.digits[i];
            z = (x - y) - borrow;
            x = ~x;
            borrow = ((x & y) | ((x ^ y) & z)) >>> 31;
            res.digits[i] = z;
        }
        if (i > val1.first) {
            // op1 >= op2 always, so I do nothing
        } else { // borrow + y == 0
            for ( ; i <= val1.first; i++) {
                res.digits[i] = val1.digits[i];
            }
        }
        res.updFirst();
    }

}

