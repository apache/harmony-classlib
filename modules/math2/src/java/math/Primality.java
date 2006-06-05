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
import java.util.Random;

/**
 * @author Daniel Fridlender
 * @author Matthias Gallé
 * @author Mariano Heredia
 * @author Miguel Vasquez
 */
class Primality {
    
    /** 
     * Just to denote that this class can't be instanced.
     */
    private Primality() {
    }

    /*  Private Fields */
            
    /**
     * All prime numbers with bit length lesser to 10 bits.
     */
    private static final int primes[] = {
        2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 
        71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 
        151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 
        233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 
        317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 
        419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 
        503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 
        607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 
        701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 
        811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 
        911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997, 1009, 
        1013, 1019, 1021};

    /**
     * All <code>BigInteger</code> prime numbers with bit length 
     * lesser than <code>8</code> bits.
     */
    private static final BigInteger BIprimes[] = new BigInteger[primes.length];

    /**
     * It encode how many iterations of Miller-Rabin test are need to get an 
     * error bound not greater than <code>2^(-100)</code>. For example: for 
     * a 1000-bit number we need 4 iterations, since BITS[3] &lt; 1000 &le; BITS[4] 
     */
    private static final int[] BITS = {
        0, 0, 1854, 1233, 927, 747, 627, 543, 480, 431, 393, 361, 335, 314, 
        295, 279, 265, 253, 242, 232, 223, 216, 181, 169, 158, 150, 145, 140, 
        136, 132, 127, 123, 119, 114, 110, 105, 101, 96, 92, 87, 83, 78, 73, 
        69, 64, 59, 54, 49, 44, 38, 32, 26, 1};
        
    /**
     * It encode how many i-bit primes there are in the table for i=2,...,10.
     * For example <code>offsetPrimes[6]</code> says that from index 11 exists 
     * <code>7</code> consecutive 6-bit prime numbers in the array. 
     */
    private static final int[][] offsetPrimes = {
        null, null, {0,2}, {2,2}, {4,2}, {6,5}, {11,7}, {18,13}, {31,23}, {54,43}, {97,75}};
    
    static {// To initialize the dual table of BigInteger primes
        for (int i = 0; i < primes.length; i++) {
            BIprimes[i] = BigInteger.valueOf(primes[i]);
        }
    }
    
    /* Package Methods */

    /**
     * It use the sieve of Eratosthenes to discard composite numbers.
     * @see BigInteger#nextProbablePrime()
     */
    static BigInteger nextProbablePrime(BigInteger n) {
        if (n.signum() < 0) {
            throw new ArithmeticException("start < 0: " + n);
        }
        int i;
        int j;
        int certainty;
        int gapSize = 1024; // for searching of the next probable prime number
        int modules[] = new int[primes.length];
        boolean isDivisible[] = new boolean[gapSize];
        BigInteger startPoint;
        BigInteger probPrime;
        // If  n < "last prime of table"  searches next prime in the table
        if (n.compareTo(BIprimes[primes.length - 1]) < 0) {
            j = n.intValue();
            for (i = 0; j >= primes[i]; i++);
            return BIprimes[i];
        }
        // To set the improved certainly of Miller-Rabin
        j = n.bitLength();
        for (certainty = 2; j < BITS[certainty]; certainty++);
        // To fix N to the "next odd number"
        startPoint = (n.testBit(0)) ? n.add(BIprimes[0]) : n.setBit(0);
        // To calculate modules:   N mod p1, N mod p2, ...  for first primes.
        for (i = 0; i < primes.length; i++) {
            modules[i] = (startPoint.mod(BIprimes[i])).intValue() - gapSize;
        }
        while (true) {
            // At this point, all numbers in the gap are initializated as probably primes
            Arrays.fill(isDivisible, false);
            // To discard multiples of first primes
            for (i = 0; i < primes.length; i++) {
                modules[i] = (modules[i] + gapSize) % primes[i];
                j = (modules[i] == 0) ? 0 : (primes[i] - modules[i]);
                for (; j < gapSize; j += primes[i]) {
                    isDivisible[j] = true;
                }
            }
            // To execute Miller-Rabin for non-divisibles numbers by all first primes
            for (j = 0; j < gapSize; j++) {
                if (!isDivisible[j]) {
                    probPrime = startPoint.add(BigInteger.valueOf(j));
                    if (millerRabin(probPrime, certainty)) {
                        return probPrime;
                    }
                }
            }
            startPoint = startPoint.add(BigInteger.valueOf(gapSize));
        }
    }
    
    /**
     * Returns a random generated, probably prime <code>BigInteger</code>.
     * @see BigInteger#BigInteger(int, int, Random)
     */
    static BigInteger consBigInteger(int bitLength, int certainty, Random rnd) {
        // PRE: bitLength >= 2;
        // For small numbers get a random prime from the table
        if (bitLength <= 10) {
            int rp[] = offsetPrimes[bitLength];
            return BIprimes[rp[0] + rnd.nextInt(rp[1])];
        }
        int first = (bitLength / 32);
        BigInteger n = new BigInteger(first);
        int randomIntArray[] = n.digits;
        int sizeOfShift = 32 - (bitLength % 32);

        do {// To fill the array with random integers
            for (int i = 0; i < randomIntArray.length; i++) {
                randomIntArray[i] = rnd.nextInt();
            }
            if (sizeOfShift == 32) {
                randomIntArray[first] = 0;
                randomIntArray[first-1] |= 0x80000000;
            } else {// To ensure bitlength
                randomIntArray[first] |= 0x80000000;
                randomIntArray[first] >>>= sizeOfShift;
            }
            // To creates an odd number
            randomIntArray[0] |= 1;
        } while (!isProbablePrime(n, certainty));
        return n;
    }

    /**
     * @see BigInteger#isProbablePrime(int)
     */
    static boolean isProbablePrime(BigInteger n, int certainty) {
        // PRE: n >= 0;
        if (certainty <= 0) {
            return true;
        }
        // To discard all even numbers, except '2'
        if (!n.testBit(0)) {
            return n.equals(BIprimes[0]);
        }
        // To check if 'n' exists in the table
        if (n.bitLength() <= 10) {
            return (Arrays.binarySearch(primes, n.intValue()) >= 0);
        }
        // To check if 'n' is divisible by some prime of the table
        for (int i = 0; i < primes.length; i++) {
            if (n.mod(BIprimes[i]).signum() == 0) {
                return false;
            }
        } 
        // To set the number of iterations for Miller-Rabin test
        int i;
        int bitLength = bitLength = n.bitLength();
        
        for (i = 2; bitLength < BITS[i]; i++);
        certainty = Math.min(i, 1 + ((certainty - 1) >> 1));
        return millerRabin(n, certainty);
    }
    
    /* Private Methods */
    
    /**
     * The Miller-Rabin primality test.
     * @param n the input number to be tested
     * @param t the number of trials
     * @return <code>false</code> if the number is definitily compose, otherwise
     *          <code>true</code> with probability <code>1 - 4^(-t)</code>.
     */
    private static boolean millerRabin(BigInteger n, int t) {
        // PRE: t >= 0
        BigInteger x;    // x := UNIFORM{2...n-1}
        BigInteger y;    // y := x^(q * 2^j) mod n
        BigInteger n_minus_1 = n.subtract(BigInteger.ONE); 	// n-1
        int bitLength = n_minus_1.bitLength();  			// ~ log2(n-1)
        // (q,k)   such that:   n-1 = q * 2^k   and q is odd
        int k = n_minus_1.getLowestSetBit();
        BigInteger q = n_minus_1.shiftRight(k);
        Random randomSource = new Random();

        for (int i = 0; i < t; i++) {
            // To generate a witness 'x', first it use the primes of table
            if (i < primes.length) {
                x = BIprimes[i];
            } else {/* It generates random witness only if it's necesssary.
                     * Note that all methods would call Miller-Rabin with t <= 50
                     * so this part is only to do more robust the algorithm */
                do {
                    x = new BigInteger(bitLength, randomSource);
                } while ((x.compareTo(n) >= 0) || (x.compareTo(BigInteger.ONE) <= 0));
            }
            y = x.modPow(q, n);
            if (!y.equals(BigInteger.ONE) && !y.equals(n_minus_1)) {
                for (int j = 1; (j < k) && !y.equals(n_minus_1); j++) {
                    y = y.multiply(y).mod(n);
                    if (y.equals(BigInteger.ONE)) {
                        return false;
                    }
                }
                if (!y.equals(n_minus_1)) {
                    return false;
                }
            }
        }
        return true;
    }    

}

