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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.harmony.security.asn1.ASN1Boolean;
import org.apache.harmony.security.asn1.ASN1Choice;
import org.apache.harmony.security.asn1.ASN1Explicit;
import org.apache.harmony.security.asn1.ASN1Integer;
import org.apache.harmony.security.asn1.ASN1SequenceOf;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.DerInputStream;
import org.apache.harmony.security.asn1.DerOutputStream;

import junit.framework.TestCase;


/**
 * ASN.1 DER test for Choice type
 * 
 * @see http://asn1.elibel.tm.fr/en/standards/index.htm
 */

public class ChoiceTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(ChoiceTest.class);
    }

    private static ASN1SequenceOf sequence = new ASN1SequenceOf(ASN1Boolean
            .getInstance());

    //
    // choice ::= CHOICE {
    //     boolean BOOLEAN,
    //     sequenceof SEQUENCE OF BOOLEAN,
    //     int INTEGER
    // }
    //

    private static ASN1Choice choice = new ASN1Choice(new ASN1Type[] {
            ASN1Boolean.getInstance(), sequence, ASN1Integer.getInstance() }) {

        public int getIndex(Object object) {
            if (object instanceof Boolean) {
                return 0; // ASN1Boolean
            } else if (object instanceof Collection) {
                return 1; // ASN1SequenceOf
            } else {
                return 2; // ASN1Integer
            }
        }

        public Object getObjectToEncode(Object object) {
            return object;
        }
    };

    //
    // Test Cases
    //

    private static Object[][] testcases = new Object[][] {
            // format: object to encode / byte array 

            // choice = Boolean (false)
            new Object[] { Boolean.FALSE, new byte[] { 0x01, 0x01, 0x00 } },

            // choice = Boolean (true)
            new Object[] { Boolean.TRUE, new byte[] { 0x01, 0x01, (byte) 0xFF } },

            // choice = SequenceOf (empty)
            new Object[] { new ArrayList(), new byte[] { 0x30, 0x00 } },

    //TODO add testcase for another ASN.1 type` 

    };

    public void testDecode_Valid() throws IOException {

        for (int i = 0; i < testcases.length; i++) {
            DerInputStream in = new DerInputStream((byte[]) testcases[i][1]);
            assertEquals("Test case: " + i, testcases[i][0], choice.decode(in));
        }
    }

    //FIXME need testcase for decoding invalid encodings

    public void testEncode() throws IOException {

        for (int i = 0; i < testcases.length; i++) {
            DerOutputStream out = new DerOutputStream(choice, testcases[i][0]);
            assertTrue("Test case: " + i, Arrays.equals(
                    (byte[]) testcases[i][1], out.encoded));
        }
    }

    public void testChoiceInSequenceOf() throws IOException {

        ASN1Choice choice = new ASN1Choice(new ASN1Type[] {
                ASN1Boolean.getInstance(), ASN1Integer.getInstance() }) {

            public int getIndex(Object object) {
                if (object instanceof Boolean) {
                    return 0; // ASN1Boolean
                } else {
                    return 1; // ASN1Integer
                }
            }

            public Object getObjectToEncode(Object object) {
                return object;
            }
        };

        ASN1SequenceOf sequenceOf = new ASN1SequenceOf(choice);

        ArrayList list = new ArrayList();
        list.add(Boolean.FALSE);
        list.add(new byte[] { 0x09 });

        byte[] encoded = new byte[] {
        // Sequence Of
                0x30, 0x06,
                // Boolean
                0x01, 0x01, 0x00,
                // Integer
                0x02, 0x01, 0x09 };

        assertTrue("Encoded: ", Arrays.equals(encoded, sequenceOf.encode(list)));

        List values = (List) sequenceOf.decode(encoded);

        assertEquals("Size: ", 2, values.size());
        assertEquals("First: ", Boolean.FALSE, values.get(0));
        assertTrue("Second: ", Arrays.equals(new byte[] { 0x09 },
                (byte[]) values.get(1)));
    }
    
    //
    //
    // 
    //
    //

    public void test_ExplicitChoice() throws IOException {

        ASN1Choice choice = new ASN1Choice(new ASN1Type[] { ASN1Boolean
                .getInstance() }) {

            public Object getObjectToEncode(Object obj) {
                return obj;
            }

            public int getIndex(Object obj) {
                return 0;
            }
        };

        ASN1Explicit explicit = new ASN1Explicit(0, choice);

        byte[] encoded = new byte[] { (byte) 0xA0, 0x03, 0x01, 0x01, 0x00 };

        assertEquals("False: ", Boolean.FALSE, (Boolean) explicit
                .decode(encoded));

        encoded[4] = (byte) 0xFF;

        assertEquals("True: ", Boolean.TRUE, (Boolean) explicit.decode(encoded));
    }
}