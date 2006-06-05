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
class Multiplication {
    /** Just to denote that can't be instantied*/
    private Multiplication(){
    }
    /**
     *Break point in digits (ints) between karatsuba and pencil and paper multiply.
     */
    static final int whenUseKaratsuba = 62; // an heuristic value   
       
    /**
     * Performs the multiplication with the Karatsuba algorithm.
     * @param op1 first factor of the product
     * @param op2 second factor of the product
     * @return op1*op2
     * @see #multiply(BigInteger, BigInteger)
     */
    static BigInteger karatsuba(BigInteger op1, BigInteger op2){
        if (Math.min(op1.first,op2.first) < whenUseKaratsuba){
            return multiplyPAP(op2,op1);
            
        } else{
            BigInteger upperOp1, lowerOp1, upperOp2, lowerOp2;
            int bitndiv2 = Math.max(op1.first, op2.first) / 2;
            int ndiv2 = 32*bitndiv2; 		// shifting by multiple of 32 is faster
            
            /*
             *  Karatsuba: 	u = u1 * B + u0
             *  			v = v1 * B + v0
             *
             *  u*v = (u1*v1)*B^2 + ((u1-u0)*(v0-v1) + u1*v1 + u0*v0 )*B + u0*v0
             */
            upperOp1 = op1.shiftRight(ndiv2);
            upperOp2 = op2.shiftRight(ndiv2);
            lowerOp1 = op1.subtract(upperOp1.shiftLeft(ndiv2));
            lowerOp2 = op2.subtract(upperOp2.shiftLeft(ndiv2));
            
            BigInteger upper = karatsuba(upperOp1, upperOp2);
            BigInteger lower = karatsuba(lowerOp1, lowerOp2);
            BigInteger middle = karatsuba(upperOp1.subtract(lowerOp1), lowerOp2.subtract(upperOp2));
            
            
            middle = middle.add(upper).add(lower);
            middle = middle.shiftLeft(ndiv2);
            upper = upper.shiftLeft(2*ndiv2);
            
            return upper.add(middle).add(lower);
        }
    }
    
    
    static BigInteger multiplyPAP(BigInteger op1, BigInteger op2){
        // initial sign stuff: ensure to use positive operands, save the signum's
        int finalSignum = op1.signum() * op2.signum();
        op1 = op1.abs();
        op2 = op2.abs();
        BigInteger res = unsignedMultiply(op1,op2);
        
        // final sign stuff: adjust sign
        if(finalSignum < 0){
            Elementary.inplaceNegate(res);
        }
        
        return res;
    }
    
    /** Performs a multiplication of two BigInteger's, supposing both non-negative.
     *
     * Use the traditional paper and pencil algorithm:
     *
     * 					a3		a2		a1		a0					= op1
     * 							b2		b1		b0					= op2
     * ---------------------------------------------------------
     *
     * 	b0*a3	b0*a2	b0*a1	a0*b0
     *+			b1*a3	b1*a2	b1*a1	b1*a0
     *+	b2*a3	b2*a2	b2*a1	b2*a0
     * --------------------------------------------------------- (adding)
     * 			..................									= op1*op2
     *
     *
     * @param op1 first factor of the multiplication <code> op1 >= 0 </code>
     * @param op2 second factor of the multiplication <code> op2 >= 0 </code>
     * @return a <code>BigInteger</code> of value <code> op1 * op2 </code>
     */
    private static BigInteger unsignedMultiply(BigInteger op1, BigInteger op2){
        //Pre: op1.signum() >= 0 && op2.signum() >= 0
        int iop1;
        int[] fact1 = op1.digits;
        int[] fact2 = op2.digits;
        BigInteger res = new BigInteger(op1.first + op2.first + 1); // maximal size
        
        int iop2;
        long carry;
        long elemOp1;
        
        for(iop1 = 0; iop1 <= op1.first; iop1++){
        	elemOp1 = fact1[iop1] & 0xFFFFFFFFl;
        	if (fact1[iop1] != 0){ 
	            carry = 0;
	            for(iop2 = 0; iop2 <= op2.first; iop2++){
	                carry +=  (fact2[iop2] & 0xFFFFFFFFl) * elemOp1 + (0xFFFFFFFFl &  res.digits[iop2 + iop1]) ;
	                // adding the current value of res.digits[iop1+iop2], because there could be a carry of a previous iteration
	                res.digits[iop2 + iop1] = (int) carry;
	                carry >>>= 32;
	            }
	            res.digits[iop1 + iop2] = (int) carry; // save the carry in the correct position
        	}
        	// else: 0*x == 0
        }
        
        res.updFirst();
        return res;
    }
    
    /**
     * Performs a multiplication of two BigInteger and hides the algorithm used
     * @param x
     * @param y
     * @return x * y
     */
    static BigInteger multiply(BigInteger x, BigInteger y) {
        return karatsuba(x,y);       
    }
        
    /**
     * This operation is equivalent (but faster) as call: multiply(op1, BigInteger.valueOf(op2 & 0xFFFFFFFF))
     * (this is circa 25% slower)
     * @param op1 the number to be scaled.
     * @param op2Int the scale by witch op1 will be scaled, representing an unsigned number.
     * @return op1 scaled by an int
     */
    static BigInteger multiply(BigInteger op1, int op2Int) {
        // FIRST: save the signum and obtain the positive value
        int op1Signum = op1.signum();
        op1 = op1.abs();
        
        long op2  = (long)op2Int & 0xFFFFFFFFL;
        int[] fact1 = op1.digits;
        long product;
        BigInteger res = new BigInteger(op1.first + 1);
        
        // SECOND: perform the multiplication
        for (int i = 0; i <= op1.first; i++) {
            if (fact1[i] != 0) {
                product = (0xFFFFFFFFL & op2) * (0xFFFFFFFFL & fact1[i])
                + (res.digits[i] & 0xFFFFFFFFL);
                res.digits[i] = (int)product;
                res.digits[i + 1] = (int)(product >>> 32);	// carry
            }
        }
        res.updFirst();
        
        // THIRD: readjust the sign
        if (op1Signum < 0) {
            Elementary.inplaceNegate(res);
        }
        
        return res;
    }
    
    /**
     * Exponentiation algorithm using squaring algorithm
     * @param base
     * @param exponent must be positive
     * @ar.org.fitc.ref "http://en.wikipedia.org/wiki/Exponentiating_by_squaring"
     */
    static BigInteger pow(BigInteger base, int exponent){
        // pre: exp > 0
        
        BigInteger res = BigInteger.ONE;
        BigInteger acc = base;
        
        while(exponent != 1){
            if ( (exponent & 1) != 0 ){
                // if odd, multiply one more time by acc
                res = res.multiply(acc);
            }
            acc = acc.multiply(acc); // squaring
            exponent >>= 1;
        }
        // exponent == 1, multiply one more time
        res = res.multiply(acc);
        return res;
    }
    
    
    
    /**
     * Return the greatest common divisor of op1 and op2
     * @param op1 must be non-zero
     * @param op2 must be non-zero
     * @return a number <code>g</code> so that <code>op1 % g == 0</code>
     * and <code>op2 % g == 0</code>; and, if there exist another number that divide both operandos,
     * than <code>g</code> is the lesser one.
     */
    static BigInteger gcdBinary(BigInteger op1, BigInteger op2){
        // op1 != 0 && op2 != 0
        
        // Get positive copies of op1 and op2
        int pow2Count, lsb1, lsb2;
        
        if (op1.signum() == -1){
            op1 = op1.negate();
        } else{
            op1 = op1.clone();
        }
        if (op2.signum() == -1){
            op2 = op2.negate();
        } else{
            op2 = op2.clone();
        }
        
        // divide both number the maximal possible times by 2 without rounding
        // gcd(2*a, 2*b) = 2 * gcd(a,n)
        lsb1 = op1.getLowestSetBit();
        lsb2 = op2.getLowestSetBit();
        pow2Count = Math.min(lsb1,lsb2);
        if (lsb1 != 0) Bitlevel.inplaceShiftRight(op1,op1,lsb1);
        if (lsb2 != 0) Bitlevel.inplaceShiftRight(op2,op2,lsb2);
        
        
        do{
            // gcd(2*k+1,2*n) = gcd(2*k+1, n)
            lsb1 = op1.getLowestSetBit();
            if (lsb1 != 0) Bitlevel.inplaceShiftRight(op1,op1,lsb1 );
            
            // gcd(2*n,2*k+1) = gsubtractcd(n,2*k+1)
            lsb2 = op2.getLowestSetBit();
            if (lsb2 != 0) Bitlevel.inplaceShiftRight(op2,op2,lsb2);
            
            if(op1.compareTo(op2)>=0){
                // gcd(2*k+1, 2*n+1) = gcd(k-n, 2*n+1)
                Elementary.inplaceSubtract(op1,op2);
            } else {
                // the same
                Elementary.inplaceSubtract(op2,op1);
            }
        } while(! (op1.signum() == 0));
        
        return op2.shiftLeft(pow2Count);
    }
    
    /**
     * Return [x,z] so that x*op1 + y*op2 == z == gcd(op1,op2) for some y
     * @param op1
     * @param op2
     * @see "D. Knuth Art of Computer Programming Vo. 2. Section 4.5.2 ALgorithm X"
     */
    static BigInteger[] modInverse(BigInteger op1, BigInteger op2){
        BigInteger u1,u3;
        BigInteger v1,v3;
        BigInteger t1;
        BigInteger q;
        BigInteger[] quotAndRem;
        
        u1 = BigInteger.ONE;  u3 = op1;
        v1 = BigInteger.ZERO; v3 = op2;
        
        while(v3.signum() != 0){
            quotAndRem = u3.divideAndRemainder(v3);
            q = quotAndRem[0];
            t1 = u1.subtract(v1.multiply(q));
            
            u1 = v1; u3 = v3;
            v1 = t1; v3 = quotAndRem[1];;
        }
        return new BigInteger[] {u1,u3};
    }    
}
