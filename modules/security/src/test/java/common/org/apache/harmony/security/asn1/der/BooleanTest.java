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
* @author Vladimir N. Molotkov
* @version $Revision$
*/

package org.apache.harmony.security.asn1.der;

import java.io.IOException;
import java.util.Arrays;

import junit.framework.TestCase;

import org.apache.harmony.security.asn1.ASN1Boolean;
import org.apache.harmony.security.asn1.ASN1Exception;
import org.apache.harmony.security.asn1.DerInputStream;
import org.apache.harmony.security.asn1.DerOutputStream;


/**
 * ASN.1 DER test for Boolean type
 * 
 * @see http://asn1.elibel.tm.fr/en/standards/index.htm
 */
public class BooleanTest extends TestCase {

    /**
     * Constructor for BooleanTest.
     * @param arg0
     */
    public BooleanTest(String arg0) {
        super(arg0);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(BooleanTest.class);
    }

    private static byte[] eFalse = new byte[] { 0x01, 0x01, 0x00 };

    private static byte[] eTrue = new byte[] { 0x01, 0x01, (byte) 0xFF };

    public void testDecode_Valid() throws IOException {

        // decoding false
        DerInputStream in = new DerInputStream(eFalse);
        assertEquals("False", Boolean.FALSE, ASN1Boolean.getInstance().decode(
                in));

        // decoding true
        in = new DerInputStream(eTrue);
        assertEquals("True", Boolean.TRUE, ASN1Boolean.getInstance().decode(in));
    }

    public void testDecode_Invalid() throws IOException {

        byte[][] invalid = new byte[][] {
        // wrong tag: tag is not 0x01
                new byte[] { 0x02, 0x01, 0x00 },
                // wrong length: length is not 1
                new byte[] { 0x01, 0x02, 0x00 },
                // wrong content: content is not 0x01 or 0xFF
                new byte[] { 0x01, 0x01, 0x33 } };

        for (int i = 0; i < invalid.length; i++) {
            try {
                DerInputStream in = new DerInputStream(invalid[i]);
                ASN1Boolean.getInstance().decode(in);
                fail("No expected ASN1Exception for: " + i);
            } catch (ASN1Exception e) {
            }
        }
    }

    public void testEncode() throws IOException {

        // encoding false
        DerOutputStream out = new DerOutputStream(ASN1Boolean.getInstance(),
                Boolean.FALSE);
        assertTrue("False", Arrays.equals(eFalse, out.encoded));

        // encoding true
        out = new DerOutputStream(ASN1Boolean.getInstance(), Boolean.TRUE);
        assertTrue("True", Arrays.equals(eTrue, out.encoded));
    }
}