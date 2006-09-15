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

package org.apache.harmony.tests.java.math;

import java.math.BigInteger;

import junit.framework.TestCase;

/**
 * Class:   java.math.BigInteger
 * Method: hashCode()
 */
public class BigIntegerHashCodeTest extends TestCase {
    /**
     * Test hash codes for the same object
     */
    public void testSameObject() {
        String value1 = "12378246728727834290276457386374882976782849";
        String value2 = "-5634562095872038262928728727834290276457386374882976782849";
        BigInteger aNumber1 = new BigInteger(value1);
        BigInteger aNumber2 = new BigInteger(value2);
        int code1 = aNumber1.hashCode();
        aNumber1.add(aNumber2).shiftLeft(125);
        aNumber1.subtract(aNumber2).shiftRight(125);
        aNumber1.multiply(aNumber2).toByteArray();
        aNumber1.divide(aNumber2).bitLength();
        aNumber1.gcd(aNumber2).pow(7);
        int code2 = aNumber1.hashCode();
        assertTrue("hash codes for the same object differ", code1 == code2);
    }

    /**
     * Test hash codes for equal objects.
     */
    public void testEqualObjects() {
        String value1 = "12378246728727834290276457386374882976782849";
        String value2 = "12378246728727834290276457386374882976782849";
        BigInteger aNumber1 = new BigInteger(value1);
        BigInteger aNumber2 = new BigInteger(value2);
        int code1 = aNumber1.hashCode();
        int code2 = aNumber2.hashCode();
        if (aNumber1.equals(aNumber2)) {
            assertTrue("hash codes for equal objects are unequal", code1 == code2);
        }
    }

    /**
     * Test hash codes for unequal objects.
     * The codes are unequal.
     */
    public void testUnequalObjectsUnequal() {
        String value1 = "12378246728727834290276457386374882976782849";
        String value2 = "-5634562095872038262928728727834290276457386374882976782849";
        BigInteger aNumber1 = new BigInteger(value1);
        BigInteger aNumber2 = new BigInteger(value2);
        int code1 = aNumber1.hashCode();
        int code2 = aNumber2.hashCode();
        if (!aNumber1.equals(aNumber2)) {
            assertTrue("hash codes for unequal objects are equal", code1 != code2);
        }
    }
    
    /**
     * Test hash codes for unequal objects.
     * The codes are equal.
     */
    public void testUnequalObjectsEqual() {
        byte aBytes[] = {56, 100, -2, -76, 98, 54, 19, 3, -15, 45, 89, -111, 69, 103, 8, -9};
        byte bBytes[] = {56, 100, -2, -76, 89, 45, 91, 3, -15, 45, 89, -111, 69, 103, 8, -9};
        int aSign = 1;
        int bSign = 1;
        BigInteger aNumber = new BigInteger(aSign, aBytes);
        BigInteger bNumber = new BigInteger(aSign, bBytes);
        int code1 = aNumber.hashCode();
        int code2 = bNumber.hashCode();
        if (!aNumber.equals(bNumber)) {
            assertTrue("hash codes for these unequal objects should be equal", code1 == code2);
        }
    }
}
