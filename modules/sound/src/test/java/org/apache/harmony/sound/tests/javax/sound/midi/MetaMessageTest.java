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

package org.apache.harmony.sound.tests.javax.sound.midi;

import javax.sound.midi.MetaMessage;
import javax.sound.midi.InvalidMidiDataException;
import junit.framework.TestCase;

public class MetaMessageTest extends TestCase {
    /**
     * Test constructors of class MetaMessage
     * with parameters and without its.
     */
    public void test_constructor_MetaMessage() throws Exception {
        MetaMessage meta = new MetaMessage();
        assertEquals(2, meta.getLength());
        assertEquals(255, meta.getStatus());
        assertEquals(0, meta.getType());
        assertEquals(2, meta.getMessage().length);
        assertEquals(-1, meta.getMessage()[0]);
        assertEquals(0, meta.getMessage()[1]);
        assertEquals(0, meta.getData().length);

        /**
         * the condition of the object must be the same
         * after throw out exception
         */
        try {
            meta = new MetaMessage1(null);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {}

        assertEquals(2, meta.getLength());
        assertEquals(255, meta.getStatus());
        assertEquals(0, meta.getType());
        assertEquals(2, meta.getMessage().length);
        assertEquals(-1, meta.getMessage()[0]);
        assertEquals(0, meta.getMessage()[1]);
        assertEquals(0, meta.getData().length);

        byte[] bt = new byte[] {-87, 19, 4};
        MetaMessage1 meta3 = new MetaMessage1(bt);
        assertEquals(3, meta3.getLength());
        assertEquals(169, meta3.getStatus());
        assertEquals(19, meta3.getType());
        assertEquals(3, meta3.getMessage().length);
        assertEquals(-87, meta3.getMessage()[0]);
        assertEquals(19, meta3.getMessage()[1]);
        assertEquals(4, meta3.getMessage()[2]);
        assertEquals(0, meta3.getData().length);
        bt[0] = 10;
        bt[1] = 20;
        bt[2] = 30;
        /**
         * values change!!!
         */
        assertEquals( meta3.getStatus(), 10 );
        assertEquals( meta3.getType(), 20 );
        assertEquals( meta3.getMessage().length, 3 );
        assertEquals( meta3.getMessage()[0], 10 );
        assertEquals( meta3.getMessage()[1], 20 );
        assertEquals( meta3.getMessage()[2], 30 );
        assertEquals( meta3.getData().length, 0 );

        bt = new byte[] {17, -9, 23, 45, 56};
        MetaMessage1 meta1 = new MetaMessage1(bt);
        assertEquals(5, meta1.getLength());
        assertEquals(17, meta1.getStatus());
        assertEquals(247, meta1.getType());
        assertEquals(5, meta1.getMessage().length);
        assertEquals(17, meta1.getMessage()[0]);
        assertEquals(-9, meta1.getMessage()[1]);
        assertEquals(23, meta1.getMessage()[2]);
        assertEquals(45, meta1.getMessage()[3]);
        assertEquals(56, meta1.getMessage()[4]);
        assertEquals(2, meta1.getData().length);
        assertEquals(45, meta1.getData()[0]);
        assertEquals(56, meta1.getData()[1]);
        bt[0] = 98;
        bt[2] = -56;
        bt[3] = -16;
        bt[4] = -3;
        /**
         * values change, but the begin of array that
         * return by method getData() doesn't displace!!!
         */
        assertEquals( meta1.getLength(), 5 );
        assertEquals( meta1.getStatus(), 98 );
        assertEquals( meta1.getType(), 247 );
        assertEquals( meta1.getMessage().length, 5 );
        assertEquals( meta1.getMessage()[0], 98 );
        assertEquals( meta1.getMessage()[1], -9 );
        assertEquals( meta1.getMessage()[2], -56 );
        assertEquals( meta1.getMessage()[3], -16 );
        assertEquals( meta1.getMessage()[4], -3 );
        assertEquals( 2, meta1.getData().length ); //!!!
        assertEquals( meta1.getData()[0], -16 );   //!!! 
        assertEquals( meta1.getData()[1], -3 );    //!!!
        byte[] nb = meta1.getData();
        nb[0] = 1;
        nb[1] = 2;
        /**
         * and here values don't change...
         */
        assertEquals( meta1.getData()[0], -16 );
        assertEquals( meta1.getData()[1], -3 );
        
        bt = new byte[] {17, -9, -23, -45, -56};
        MetaMessage1 meta4 = new MetaMessage1(bt);
        assertEquals(5, meta4.getLength());
        assertEquals(17, meta4.getStatus());
        assertEquals(247, meta4.getType());
        assertEquals(5, meta4.getMessage().length);
        assertEquals(17, meta4.getMessage()[0]);
        assertEquals(-9, meta4.getMessage()[1]);
        assertEquals(-23, meta4.getMessage()[2]);
        assertEquals(-45, meta4.getMessage()[3]);
        assertEquals(-56, meta4.getMessage()[4]);
        try {
            assertEquals(0, meta4.getData().length);
            fail("NegativeArraySizeException expected");
        } catch (NegativeArraySizeException e) {}

        meta.setMessage(10, new byte[100000], 16385);

        meta1 = new MetaMessage1(meta.getMessage());
        assertEquals(16390, meta1.getLength());
        assertEquals(255, meta1.getStatus());
        assertEquals(10, meta1.getType());
        assertEquals(16390, meta1.getMessage().length);
        assertEquals(-1, meta1.getMessage()[0]);
        assertEquals(10, meta1.getMessage()[1]);
        assertEquals(-127, meta1.getMessage()[2]);
        assertEquals(-128, meta1.getMessage()[3]);
        assertEquals(1, meta1.getMessage()[4]);
        assertEquals(16385, meta1.getData().length);
        assertEquals(0, meta1.getData()[0]);
        assertEquals(0, meta1.getData()[1]);

    }

    /**
     * Test constant
     */
    public void test_constants() {
        assertEquals("Value of META", 255, MetaMessage.META);
    }

    /**
     * Test method setMessage( int, byte[], int ) of 
     * class MetaMessage with parameters and without its.
     * Class was created using constructor MetaMessage()
     */
    public void test_setMessage1() throws Exception {
        MetaMessage meta = new MetaMessage();
        try {
            meta.setMessage(10, new byte[] { 1, 2, 3 }, 4);
            fail("InvalidMidiDataException expected");
        } catch (InvalidMidiDataException e) {
        }

        try {
            meta.setMessage(10, new byte[] { 1, 2, 3 }, -5);
            fail("InvalidMidiDataException expected");
        } catch (InvalidMidiDataException e) {
        }

        try {
            meta.setMessage(-1, new byte[] { 1, 2, 3 }, 3);
            fail("InvalidMidiDataException expected");
        } catch (InvalidMidiDataException e) {
        }

        try {
            meta.setMessage(128, new byte[] { 1, 2, 3 }, 3);
            fail("InvalidMidiDataException expected");
        } catch (InvalidMidiDataException e) {
        }

        meta.setMessage(18, new byte[] { 1, 2, 3 }, 0);

        assertEquals(meta.getLength(), 3);
        assertEquals(meta.getMessage().length, 3);
        assertEquals(meta.getMessage()[0], -1);
        assertEquals(meta.getMessage()[1], 18);
        assertEquals(meta.getMessage()[2], 0);
        assertEquals(meta.getData().length, 0);

        meta.setMessage(10, new byte[100000], 65535);
        assertEquals(meta.getLength(), 65540);
        assertEquals(meta.getMessage().length, 65540);
        assertEquals(meta.getMessage()[0], -1);
        assertEquals(meta.getMessage()[1], 10);
        assertEquals(meta.getMessage()[2], -125);
        assertEquals(meta.getMessage()[3], -1);
        assertEquals(meta.getMessage()[4], 127);

        meta.setMessage(10, new byte[100000], 16385);
        assertEquals(meta.getLength(), 16390);
        assertEquals(meta.getMessage().length, 16390);
        assertEquals(meta.getMessage()[0], -1);
        assertEquals(meta.getMessage()[1], 10);
        assertEquals(meta.getMessage()[2], -127);
        assertEquals(meta.getMessage()[3], -128);
        assertEquals(meta.getMessage()[4], 1);

        byte[] bt = new byte[] { 1, 2, 3, 4 };
        meta.setMessage(10, bt, 4);
        assertEquals(meta.getLength(), 7);
        assertEquals(meta.getStatus(), 255);
        assertEquals(meta.getType(), 10);
        assertEquals(4, meta.getData().length);
        assertEquals(meta.getData()[0], 1);
        assertEquals(meta.getData()[1], 2);
        assertEquals(meta.getData()[2], 3);
        assertEquals(meta.getData()[3], 4);
        assertEquals(meta.getMessage().length, 7);
        assertEquals(meta.getMessage()[0], -1);
        assertEquals(meta.getMessage()[1], 10);
        assertEquals(meta.getMessage()[2], 4);
        assertEquals(meta.getMessage()[3], 1);
        assertEquals(meta.getMessage()[4], 2);
        assertEquals(meta.getMessage()[5], 3);
        assertEquals(meta.getMessage()[6], 4);
        bt[0] = 0;
        bt[1] = 1;
        bt[2] = 2;
        bt[3] = 3;
        /**
         * values don't change
         */
        assertEquals( meta.getLength(), 7 );
        assertEquals( meta.getStatus(), 255 );
        assertEquals( meta.getType(), 10 );
        assertEquals( meta.getData().length, 4 );
        assertEquals( meta.getData()[0], 1 );
        assertEquals( meta.getData()[1], 2 );
        assertEquals( meta.getData()[2], 3 );
        assertEquals( meta.getData()[3], 4 );
        assertEquals( meta.getMessage().length, 7 );
        assertEquals( meta.getMessage()[0], -1 );
        assertEquals( meta.getMessage()[1], 10 );
        assertEquals( meta.getMessage()[2], 4 );
        assertEquals( meta.getMessage()[3], 1 );
        assertEquals( meta.getMessage()[4], 2 );
        assertEquals( meta.getMessage()[5], 3 );
        assertEquals( meta.getMessage()[6], 4 );

        meta.setMessage(12, null, 0);

        assertEquals(meta.getLength(), 3);
        assertEquals(meta.getStatus(), 255);
        assertEquals(meta.getType(), 12);
        assertEquals(meta.getData().length, 0);
        assertEquals(meta.getMessage().length, 3);
        assertEquals(meta.getMessage()[0], -1);
        assertEquals(meta.getMessage()[1], 12);
        assertEquals(meta.getMessage()[2], 0);

        /**
         * I will testing following condition after throw out exception;
         * it must be the same after it 
         */
        try {
            meta.setMessage(10, new byte[] { 1, 2, 3, 4 }, 4);
        } catch (InvalidMidiDataException e) {}

        try {
            meta.setMessage(12, new byte[0], 9);
            fail("InvalidMidiDataException expected");
        } catch (InvalidMidiDataException e) {}
        assertEquals(meta.getLength(), 7);
        assertEquals(meta.getStatus(), 255);
        assertEquals(meta.getType(), 10);
        assertEquals(meta.getData().length, 4);
        assertEquals(meta.getData()[0], 1);
        assertEquals(meta.getData()[1], 2);
        assertEquals(meta.getData()[2], 3);
        assertEquals(meta.getData()[3], 4);
        assertEquals(meta.getMessage().length, 7);
        assertEquals(meta.getMessage()[0], -1);
        assertEquals(meta.getMessage()[1], 10);
        assertEquals(meta.getMessage()[2], 4);
        assertEquals(meta.getMessage()[3], 1);
        assertEquals(meta.getMessage()[4], 2);
        assertEquals(meta.getMessage()[5], 3);
        assertEquals(meta.getMessage()[6], 4);

        try {
            meta.setMessage(12, null, 9);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {}
        assertEquals(meta.getLength(), 7);
        assertEquals(meta.getStatus(), 255);
        assertEquals(meta.getType(), 10);
        assertEquals(meta.getData().length, 4);
        assertEquals(meta.getData()[0], 1);
        assertEquals(meta.getData()[1], 2);
        assertEquals(meta.getData()[2], 3);
        assertEquals(meta.getData()[3], 4);
        assertEquals(meta.getMessage().length, 7);
        assertEquals(meta.getMessage()[0], -1);
        assertEquals(meta.getMessage()[1], 10);
        assertEquals(meta.getMessage()[2], 4);
        assertEquals(meta.getMessage()[3], 1);
        assertEquals(meta.getMessage()[4], 2);
        assertEquals(meta.getMessage()[5], 3);
        assertEquals(meta.getMessage()[6], 4);

        try {
            meta.setMessage(12, null, -9);
            fail("InvalidMidiDataException expected");
        } catch (InvalidMidiDataException e) {}
        assertEquals(meta.getLength(), 7);
        assertEquals(meta.getStatus(), 255);
        assertEquals(meta.getType(), 10);
        assertEquals(meta.getData().length, 4);
        assertEquals(meta.getData()[0], 1);
        assertEquals(meta.getData()[1], 2);
        assertEquals(meta.getData()[2], 3);
        assertEquals(meta.getData()[3], 4);
        assertEquals(meta.getMessage().length, 7);
        assertEquals(meta.getMessage()[0], -1);
        assertEquals(meta.getMessage()[1], 10);
        assertEquals(meta.getMessage()[2], 4);
        assertEquals(meta.getMessage()[3], 1);
        assertEquals(meta.getMessage()[4], 2);
        assertEquals(meta.getMessage()[5], 3);
        assertEquals(meta.getMessage()[6], 4);
    }

    /**
     * Test method setMessage( int, byte[], int ) of 
     * class MetaMessage with parameters and without its.
     * Class was created using constructor MetaMessage( byte[] )
     */
    public void test_setMessage2() throws Exception {
        MetaMessage1 meta = new MetaMessage1(new byte[] { -23, 18, 87, 34, -90 });
        try {
            meta.setMessage(10, new byte[] { 1, 2, 3 }, 4);
            fail("InvalidMidiDataException expected");
        } catch (InvalidMidiDataException e) {}

        try {
            meta.setMessage(10, new byte[] { 1, 2, 3 }, -5);
            fail("InvalidMidiDataException expected");
        } catch (InvalidMidiDataException e) {}

        try {
            meta.setMessage(-1, new byte[] { 1, 2, 3 }, 3);
            fail("InvalidMidiDataException expected");
        } catch (InvalidMidiDataException e) {}

        try {
            meta.setMessage(128, new byte[] { 1, 2, 3 }, 3);
            fail("InvalidMidiDataException expected");
        } catch (InvalidMidiDataException e) {}

        meta.setMessage(10, new byte[] { 1, 2, 3, 4 }, 4);

        assertEquals(meta.getLength(), 7);
        assertEquals(meta.getStatus(), 255);
        assertEquals(meta.getType(), 10);
        assertEquals(meta.getData().length, 4);
        assertEquals(meta.getData()[0], 1);
        assertEquals(meta.getData()[1], 2);
        assertEquals(meta.getData()[2], 3);
        assertEquals(meta.getData()[3], 4);
        assertEquals(meta.getMessage().length, 7);
        assertEquals(meta.getMessage()[0], -1);
        assertEquals(meta.getMessage()[1], 10);
        assertEquals(meta.getMessage()[2], 4);
        assertEquals(meta.getMessage()[3], 1);
        assertEquals(meta.getMessage()[4], 2);
        assertEquals(meta.getMessage()[5], 3);
        assertEquals(meta.getMessage()[6], 4);

        meta.setMessage(10, new byte[] { 1, 2, 3, 4 }, 2);

        assertEquals(meta.getLength(), 5);
        assertEquals(meta.getStatus(), 255);
        assertEquals(meta.getType(), 10);
        assertEquals(meta.getData().length, 2);
        assertEquals(meta.getData()[0], 1);
        assertEquals(meta.getData()[1], 2);
        assertEquals(meta.getMessage().length, 5);
        assertEquals(meta.getMessage()[0], -1);
        assertEquals(meta.getMessage()[1], 10);
        assertEquals(meta.getMessage()[2], 2);
        assertEquals(meta.getMessage()[3], 1);
        assertEquals(meta.getMessage()[4], 2);

        meta.setMessage(12, null, 0);
        assertEquals(meta.getLength(), 3);
        assertEquals(meta.getStatus(), 255);
        assertEquals(meta.getType(), 12);
        assertEquals(meta.getData().length, 0);
        assertEquals(meta.getMessage().length, 3);
        assertEquals(meta.getMessage()[0], -1);
        assertEquals(meta.getMessage()[1], 12);
        assertEquals(meta.getMessage()[2], 0);

    }

    /**
     * Test method getType() of class MetaMessage
     * 
     */
    public void test_getType() throws Exception {
        MetaMessage meta = new MetaMessage();
        assertEquals(meta.getType(), 0);

        byte[] bt = new byte[] { 9, -4, 34, 18 };
        MetaMessage1 meta2 = new MetaMessage1(bt);
        assertEquals(meta2.getType(), 252);
        bt[1] = 5;
        /**
         * value change
         */
        assertEquals(5, meta2.getType());
        
        meta.setMessage(10, new byte[] { 1, 2, 3, 4 }, 4);
        assertEquals(meta.getType(), 10);

        meta.setMessage(27, null, 0);
        assertEquals(meta.getType(), 27);
    }

    /**
     * Test method getData() of class MetaMessage
     *
     */
    public void test_getData() throws Exception {
        MetaMessage meta = new MetaMessage();
        assertEquals(0, meta.getData().length);

        byte[] bt = new byte[] { 18, 43, 27, -90, 4 };
        MetaMessage1 meta1 = new MetaMessage1(bt);
        assertEquals(2, meta1.getData().length);
        assertEquals(-90, meta1.getData()[0]);
        assertEquals(4, meta1.getData()[1]);
        bt[3] = 67;
        bt[4] = -16;
        /**
         * values change
         */
        assertEquals(67, meta1.getData()[0]);
        assertEquals(-16, meta1.getData()[1]);

        MetaMessage1 meta2 = new MetaMessage1(new byte[] { 18, 43, 27 });
        assertEquals(0, meta2.getData().length);

        bt = new byte[] { 1, 2, 3, 4 };
        meta.setMessage(10, bt, 4);
        assertEquals(4, meta.getData().length);
        assertEquals(1, meta.getData()[0]);
        assertEquals(2, meta.getData()[1]);
        assertEquals(3, meta.getData()[2]);
        assertEquals(4, meta.getData()[3]);
        bt[0] = 34;
        bt[1] = -17;
        bt[2] = 90;
        bt[3] = -6;
        /**
         * values don't change
         */
        assertEquals(1, meta.getData()[0]);
        assertEquals(2, meta.getData()[1]);
        assertEquals(3, meta.getData()[2]);
        assertEquals(4, meta.getData()[3]);

        meta.setMessage(12, null, 0);
        assertNotNull(meta.getData());
        assertEquals(0, meta.getData().length);
    }

    /**
     * Test method clone() of class MetaMessage.
     */
    public void test_clone1() {
        MetaMessage meta = new MetaMessage();
        assertTrue(meta.clone() != meta);
        assertEquals(meta.clone().getClass(), meta.getClass());
        MetaMessage tmeta;
        tmeta = (MetaMessage) meta.clone();
        assertEquals(meta.getLength(), tmeta.getLength());
        assertEquals(meta.getMessage().length, tmeta.getMessage().length);
        assertEquals(meta.getData().length, tmeta.getData().length);
        if (meta.getData().length != 0) {
            for (int i = 0; i < meta.getData().length; i++) {
                assertEquals(meta.getData()[i], tmeta.getData()[i]);
            }
        }
        if (meta.getMessage().length != 0) {
            for (int i = 0; i < meta.getMessage().length; i++) {
                assertEquals(meta.getMessage()[i], tmeta.getMessage()[i]);
            }
        }
    }

    /**
     * Test method clone() of class MetaMessage.
     */
    public void test_clone2() throws Exception {
        MetaMessage meta = new MetaMessage();

        meta.setMessage(23, new byte[] { 23, 45, -90 }, 2);

        assertTrue(meta.clone() != meta);
        assertEquals(meta.clone().getClass(), meta.getClass());
        MetaMessage tmeta;
        tmeta = (MetaMessage) meta.clone();
        assertEquals(meta.getLength(), tmeta.getLength());
        assertEquals(meta.getMessage().length, tmeta.getMessage().length);
        assertEquals(meta.getData().length, tmeta.getData().length);
        if (meta.getData().length != 0) {
            for (int i = 0; i < meta.getData().length; i++) {
                assertEquals(meta.getData()[i], tmeta.getData()[i]);
            }
        }
        if (meta.getMessage().length != 0) {
            for (int i = 0; i < meta.getMessage().length; i++) {
                assertEquals(meta.getMessage()[i], tmeta.getMessage()[i]);
            }
        }
    }

    /**
     * Test method clone() of class MetaMessage.
     */
    public void test_clone3() throws Exception {
        MetaMessage meta = new MetaMessage();
        meta.setMessage(23, null, 0);

        assertTrue(meta.clone() != meta);
        assertEquals(meta.clone().getClass(), meta.getClass());
        MetaMessage tmeta;
        tmeta = (MetaMessage) meta.clone();
        assertEquals(meta.getLength(), tmeta.getLength());
        assertEquals(meta.getMessage().length, tmeta.getMessage().length);
        assertEquals(meta.getData().length, tmeta.getData().length);
        if (meta.getData().length != 0) {
            for (int i = 0; i < meta.getData().length; i++) {
                assertEquals(meta.getData()[i], tmeta.getData()[i]);
            }
        }
        if (meta.getMessage().length != 0) {
            for (int i = 0; i < meta.getMessage().length; i++) {
                assertEquals(meta.getMessage()[i], tmeta.getMessage()[i]);
            }
        }
    }
    
    public void test_clone4() throws Exception {
        byte[] bt = new byte[] {1, 2, 3, 4};
        MetaMessage1 meta = new MetaMessage1(bt);
        assertTrue(meta.clone() != meta);
        
        MetaMessage tmeta;
        tmeta = (MetaMessage) meta.clone();
        bt[0] = 34;
        bt[1] = 15;
        bt[2] = 89;
        bt[3] = 1;
        assertEquals(meta.getLength(), tmeta.getLength());
        assertEquals(meta.getMessage().length, tmeta.getMessage().length);
        assertEquals(meta.getData().length, tmeta.getData().length);
        /**
         * 'real' MetaMessage change, but 'clone' not
         */
        assertEquals(1, meta.getData()[0]);
        assertEquals(34, meta.getMessage()[0]);
        assertEquals(15, meta.getMessage()[1]);
        assertEquals(89, meta.getMessage()[2]);
        assertEquals(1, meta.getMessage()[3]);
        
        assertEquals(4, tmeta.getData()[0]);
        assertEquals(1, tmeta.getMessage()[0]);
        assertEquals(2, tmeta.getMessage()[1]);
        assertEquals(3, tmeta.getMessage()[2]);
        assertEquals(4, tmeta.getMessage()[3]);
    }

    /**
     * Subsidiary class in order to testing constructor
     * of class Instrument, because it declared as protected
     */
    static class MetaMessage1 extends MetaMessage {
        MetaMessage1(byte[] data) {
            super(data);
        }
    }
}
