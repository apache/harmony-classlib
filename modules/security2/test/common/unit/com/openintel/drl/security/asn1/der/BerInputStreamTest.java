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
 * @author Stepan M. Mishura
 * @version $Revision$
 */

package com.openintel.drl.security.asn1.der;

import java.io.IOException;
import java.math.BigInteger;

import com.openintel.drl.security.asn1.BerInputStream;

import junit.framework.TestCase;

/**
 * Tests BerInputStream implementation
 * 
 * @see http://asn1.elibel.tm.fr/en/standards/index.htm
 */

public class BerInputStreamTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(BerInputStreamTest.class);
    }

    public void test_LengthConstraints() {

        //
        // Positive testcases
        //
        Object[][] testcase = new Object[][] {
                // length = 0x01
                new Object[] { new byte[] { 0x30, (byte) 0x81, 0x01 },
                        BigInteger.valueOf(1) },
                // length = 0xFF
                new Object[] { new byte[] { 0x30, (byte) 0x81, (byte) 0xFF },
                        BigInteger.valueOf(0xFF) },
                // length = 0x0101
                new Object[] { new byte[] { 0x30, (byte) 0x82, 0x01, 0x01 },
                        BigInteger.valueOf(0x0101) },
                // length = 0xFFFF
                new Object[] {
                        new byte[] { 0x30, (byte) 0x82, (byte) 0xFF,
                                (byte) 0xFF }, BigInteger.valueOf(0xFFFF) },
                // length = 0x0FFFFF
                new Object[] {
                        new byte[] { 0x30, (byte) 0x83, 0x0F, (byte) 0xFF,
                                (byte) 0xFF }, BigInteger.valueOf(0x0FFFFF) },

                // length = 0xFFFFFF
                new Object[] {
                        new byte[] { 0x30, (byte) 0x83, (byte) 0xFF,
                                (byte) 0xFF, (byte) 0xFF },
                        BigInteger.valueOf(0xFFFFFF) },
                // length = 0xFFFFFF
                new Object[] {
                        new byte[] { 0x30, (byte) 0x84, 0x00, (byte) 0xFF,
                                (byte) 0xFF, (byte) 0xFF },
                        BigInteger.valueOf(0xFFFFFF) },
        };

        for (int i = 0; i < testcase.length; i++) {
            try {
                BerInputStream in = new BerInputStream((byte[]) testcase[i][0]);

                int expected = ((BigInteger) testcase[i][1]).intValue();

                if (in.getLength() != expected) {
                    fail("Testcase: " + i + ". Expected: " + expected
                            + ", returned" + in.getLength());
                }
            } catch (IOException e) {
                fail("Testcase: " + i + "\nUnexpected exception." + e);
            }
        }

        //
        // Negative testcases
        //

        try {
            new BerInputStream(new byte[] { 0x30, (byte) 0x84, 0x01, 0x01,
                    0x01, 0x01 });
            fail("No expected IOException");
        } catch (IOException e) {
            assertTrue(e.getMessage().startsWith("Too long"));
        }
    }
}