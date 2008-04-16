/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.harmony.unpack200.tests;

import junit.framework.TestCase;

import org.apache.harmony.pack200.Codec;
import org.apache.harmony.unpack200.AttributeLayout;
import org.apache.harmony.unpack200.Pack200Exception;
import org.apache.harmony.unpack200.Segment;
import org.apache.harmony.unpack200.SegmentConstantPool;

public class AttributeLayoutTest extends TestCase {

    public class TestSegment extends Segment {

        public SegmentConstantPool getConstantPool() {
            final Object[][] data = new Object[][] {
                    {}, // ALL
                    { "Zero", "One", "Two", "Three", "Four", "Five", "Six",
                            "Seven", "Eight", "Nine" }, // UTF-8
                    {},
                    {},
                    {},
                    {},
                    {},
                    {},
                    { "Eins", "Zwei", "Drei", "Vier", "Funf", "Sechs",
                            "Sieben", "Acht", "Neun" }, // Signature
            };
            return new SegmentConstantPool(null) {

                public Object getValue(int cp, long index) {
                    if (index == -1)
                        return null;
                    return data[cp][(int) index];
                }

            };
        }
    }

    public void testBadData() {
        assertTrue(throwsException(null, AttributeLayout.CONTEXT_CLASS, ""));
        assertTrue(throwsException("", AttributeLayout.CONTEXT_CLASS, ""));
        assertTrue(!throwsException("name", AttributeLayout.CONTEXT_CLASS, ""));
        assertTrue(!throwsException("name", AttributeLayout.CONTEXT_METHOD, ""));
        assertTrue(!throwsException("name", AttributeLayout.CONTEXT_FIELD, ""));
        assertTrue(!throwsException("name", AttributeLayout.CONTEXT_CODE, ""));
        assertTrue(throwsException("name", -1, ""));
        assertTrue(throwsException("name", 1234, ""));
    }

    public void testLayoutRU() throws Pack200Exception {
        AttributeLayout layout = new AttributeLayout("RU",
                AttributeLayout.CONTEXT_CLASS, "RU", 1);
        Segment segment = new TestSegment();
        assertNull(layout.getValue(-1, segment.getConstantPool()));
        assertEquals("Zero", layout.getValue(0, segment.getConstantPool()));
        assertEquals("One", layout.getValue(1, segment.getConstantPool()));
    }

    public void testLayoutRUN() throws Pack200Exception {
        AttributeLayout layout = new AttributeLayout("RUN",
                AttributeLayout.CONTEXT_CLASS, "RUN", 1);
        Segment segment = new TestSegment();
        assertNull(layout.getValue(0, segment.getConstantPool()));
        assertEquals("Zero", layout.getValue(1, segment.getConstantPool()));
        assertEquals("One", layout.getValue(2, segment.getConstantPool()));
    }

    public void testLayoutRS() throws Pack200Exception {
        AttributeLayout layout = new AttributeLayout("RS",
                AttributeLayout.CONTEXT_CLASS, "RS", 1);
        Segment segment = new TestSegment();
        assertNull(layout.getValue(-1, segment.getConstantPool()));
        assertEquals("Eins", layout.getValue(0, segment.getConstantPool()));
        assertEquals("Zwei", layout.getValue(1, segment.getConstantPool()));
    }

    public void testLayoutRSN() throws Pack200Exception {
        AttributeLayout layout = new AttributeLayout("RSN",
                AttributeLayout.CONTEXT_CLASS, "RSN", 1);
        Segment segment = new TestSegment();
        assertNull(layout.getValue(0, segment.getConstantPool()));
        assertEquals("Eins", layout.getValue(1, segment.getConstantPool()));
        assertEquals("Zwei", layout.getValue(2, segment.getConstantPool()));
    }

    public void testGetCodec() throws Pack200Exception {
        AttributeLayout layout = new AttributeLayout("O",
                AttributeLayout.CONTEXT_CLASS, "HOBS", 1);
        assertEquals(Codec.BRANCH5, layout.getCodec());
        layout = new AttributeLayout("P", AttributeLayout.CONTEXT_METHOD,
                "PIN", 1);
        assertEquals(Codec.BCI5, layout.getCodec());
        layout = new AttributeLayout("S", AttributeLayout.CONTEXT_FIELD, "HS",
                1);
        assertEquals(Codec.SIGNED5, layout.getCodec());
        layout = new AttributeLayout("RS", AttributeLayout.CONTEXT_CODE,
                "RRRS", 1);
        assertEquals(Codec.UNSIGNED5, layout.getCodec());
        layout = new AttributeLayout("KS", AttributeLayout.CONTEXT_CLASS,
                "RKS", 1);
        assertEquals(Codec.UNSIGNED5, layout.getCodec());
        layout = new AttributeLayout("B", AttributeLayout.CONTEXT_CLASS,
                "TRKSB", 1);
        assertEquals(Codec.BYTE1, layout.getCodec());
    }

    public boolean throwsException(String name, int context, String layout) {
        try {
            new AttributeLayout(name, context, layout, -1);
            return false;
        } catch (Pack200Exception e) {
            return true;
        }
    }
}
