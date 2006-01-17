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
import java.util.Arrays;

import org.apache.harmony.security.asn1.ASN1Exception;
import org.apache.harmony.security.asn1.ASN1Oid;
import org.apache.harmony.security.asn1.DerInputStream;
import org.apache.harmony.security.asn1.DerOutputStream;

import junit.framework.TestCase;


/**
 * ASN.1 DER test for OID type
 * 
 * @see http://asn1.elibel.tm.fr/en/standards/index.htm
 */

public class OidTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(OidTest.class);
    }

    private static Object[][] oid = new Object[][] {
    //oid array format: string / int array / byte array
            // OID=0.0 
            new Object[] { "OID=0.0", // as string 
                    new int[] { 0, 0 }, // as int array
                    new byte[] { 0x06, 0x01, 0x00 } },
            // OID=0.5 
            new Object[] { "OID=0.5", // as string
                    new int[] { 0, 5 }, // as int array
                    new byte[] { 0x06, 0x01, 0x05 } },
            // OID=1.1 
            new Object[] { "OID=1.1", // as string
                    new int[] { 1, 1 }, // as int array
                    new byte[] { 0x06, 0x01, 0x29 } },
            // OID=2.47 
            new Object[] { "OID=2.47", // as string
                    new int[] { 2, 47 }, // as int array
                    new byte[] { 0x06, 0x01, 0x7F } },
            // OID=2.48
            new Object[] { "OID=2.48", // as string
                    new int[] { 2, 48 }, // as int array
                    new byte[] { 0x06, 0x02, (byte) 0x81, 0x00 } },
            // OID=2.48.5 
            new Object[] { "OID=2.48.5", // as string
                    new int[] { 2, 48, 5 }, // as int array
                    new byte[] { 0x06, 0x03, (byte) 0x81, 0x00, 0x05 } }

    };

    public void testDecode_Valid() throws IOException {

        for (int i = 0; i < oid.length; i++) {
            DerInputStream in = new DerInputStream((byte[]) oid[i][2]);
            assertTrue((String) oid[i][0], Arrays.equals((int[]) oid[i][1],
                    (int[]) ASN1Oid.getInstance().decode(in)));
        }
    }

    public void testDecode_Invalid() throws IOException {
        byte[][] invalid = new byte[][] {
        // wrong tag: tag is not 0x06
                new byte[] { 0x02, 0x01, 0x00 },
                // wrong length: length is 0
                new byte[] { 0x06, 0x00 },
                // wrong content: bit 8 of the last byte is not 0 
                new byte[] { 0x06, 0x02, (byte) 0x81, (byte) 0x80 },
        // wrong content: is not encoded in fewest number of bytes 
        //FIXME new byte[] { 0x06, 0x02, (byte) 0x80, (byte) 0x01 }
        };

        for (int i = 0; i < invalid.length; i++) {
            try {
                DerInputStream in = new DerInputStream(invalid[i]);
                ASN1Oid.getInstance().decode(in);
                fail("No expected ASN1Exception for:" + i);
            } catch (ASN1Exception e) {
            }
        }
    }

    public void testEncode() throws IOException {

        for (int i = 0; i < oid.length; i++) {
            DerOutputStream out = new DerOutputStream(ASN1Oid.getInstance(),
                    (int[]) oid[i][1]);
            assertTrue((String) oid[i][0], Arrays.equals((byte[]) oid[i][2],
                    out.encoded));
        }
    }

    public void test_MappingToString() throws IOException {

        //
        // Test cases
        //

        Object[][] stringOids = new Object[][] {
        //oid array format: string / byte array
                // OID=0.0 
                new Object[] { "0.0", // as string 
                        new byte[] { 0x06, 0x01, 0x00 } },
                // OID=0.5 
                new Object[] { "0.5", // as string
                        new byte[] { 0x06, 0x01, 0x05 } },
                // OID=1.1 
                new Object[] { "1.1", // as string
                        new byte[] { 0x06, 0x01, 0x29 } },
                // OID=2.47 
                new Object[] { "2.47", // as string
                        new byte[] { 0x06, 0x01, 0x7F } },
                // OID=2.48
                new Object[] { "2.48", // as string
                        new byte[] { 0x06, 0x02, (byte) 0x81, 0x00 } },
                // OID=2.48.5 
                new Object[] { "2.48.5", // as string
                        new byte[] { 0x06, 0x03, (byte) 0x81, 0x00, 0x05 } }

        };

        //
        // Decoding
        //
        for (int i = 0; i < stringOids.length; i++) {
            DerInputStream in = new DerInputStream((byte[]) stringOids[i][1]);
            assertEquals((String) oid[i][0], stringOids[i][0], ASN1Oid
                    .getInstanceForString().decode(in));
        }

        //
        // Encoding
        //
        for (int i = 0; i < stringOids.length; i++) {
            DerOutputStream out = new DerOutputStream(ASN1Oid
                    .getInstanceForString(), stringOids[i][0]);
            assertTrue((String) oid[i][0], Arrays.equals((byte[]) stringOids[i][1],
                    out.encoded));
        }
    }
}