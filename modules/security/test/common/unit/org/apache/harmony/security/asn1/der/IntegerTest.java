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

package org.apache.harmony.security.asn1.der;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

import org.apache.harmony.security.asn1.ASN1Exception;
import org.apache.harmony.security.asn1.ASN1Integer;
import org.apache.harmony.security.asn1.DerInputStream;
import org.apache.harmony.security.asn1.DerOutputStream;

import junit.framework.TestCase;


/**
 * ASN.1 DER test for INTEGER type
 * 
 * @see http://asn1.elibel.tm.fr/en/standards/index.htm
 */

public class IntegerTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(IntegerTest.class);
    }

    private static BigInteger[] values = new BigInteger[] {
            new BigInteger("0"), new BigInteger("1"), new BigInteger("-1"),
            new BigInteger("1234567890"), new BigInteger("-1234567890") };

    public void testDecode_Valid() throws IOException {

        for (int i = 0; i < values.length; i++) {

            //get encoding
            byte[] encoded = new byte[values[i].toByteArray().length + 2];
            encoded[0] = 0x02;
            encoded[1] = (byte) (encoded.length - 2);
            System
                    .arraycopy(values[i].toByteArray(), 0, encoded, 2,
                            encoded[1]);

            //decode
            DerInputStream in = new DerInputStream(encoded);
            assertTrue("Testcase: " + i, Arrays.equals(values[i].toByteArray(),
                    (byte[]) ASN1Integer.getInstance().decode(in)));
        }
    }

    public void testDecode_Invalid() throws IOException {
        byte[][] invalid = new byte[][] {
        // wrong tag: tag is not 0x02
                new byte[] { 0x01, 0x01, 0x00 },
                // wrong length: length is 0
                new byte[] { 0x02, 0x00 },
                // wrong content: is not encoded in minimum number of octets 
                new byte[] { 0x02, 0x02, 0x00, 0x00 },
                // wrong content: is not encoded in minimum number of octets 
                new byte[] { 0x02, 0x02, (byte) 0xFF, (byte) 0x80 } };

        for (int i = 0; i < invalid.length; i++) {
            try {
                DerInputStream in = new DerInputStream(invalid[i]);
                ASN1Integer.getInstance().decode(in);
                fail("No expected ASN1Exception for:" + i);
            } catch (ASN1Exception e) {
            }
        }
    }

    public void testEncode() throws IOException {

        for (int i = 0; i < values.length; i++) {

            //get encoding
            byte[] encoded = new byte[values[i].toByteArray().length + 2];
            encoded[0] = 0x02;
            encoded[1] = (byte) (encoded.length - 2);
            System
                    .arraycopy(values[i].toByteArray(), 0, encoded, 2,
                            encoded[1]);

            //encode
            DerOutputStream out = new DerOutputStream(
                    ASN1Integer.getInstance(), values[i].toByteArray());
            assertTrue("Testcase: " + i, Arrays.equals(encoded, out.encoded));
        }
    }

    public void testConversion() {
        int[] testcase = new int[] { 0, 1, -1, 127, -127, 128, -128, 32767,
                -32768, Integer.MAX_VALUE, Integer.MIN_VALUE };

        //        for (int j = 0; j < 5; j++) {
        //            long startTime = System.currentTimeMillis();
        //            for (int k = 0; k < 1000000; k++) {
        for (int i = 0; i < testcase.length; i++) {
            assertEquals("Testcase: " + i, testcase[i], ASN1Integer
                    .toIntValue(ASN1Integer.fromIntValue(testcase[i])));
        }
        //            }
        //            long endTime = System.currentTimeMillis();
        //            System.out.println("Time: " + (endTime - startTime));
        //        }
    }
}