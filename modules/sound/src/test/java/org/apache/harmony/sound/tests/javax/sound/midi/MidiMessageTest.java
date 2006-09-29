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

import junit.framework.TestCase;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;

public class MidiMessageTest extends TestCase {
    /**
     * test constructor of class MidiMessage
     *
     */
    public void test_constructor_MidiMessage() {
        MidiMessage1 midi = new MidiMessage1(new byte[] { 1, 2, 3, -5 });
        assertTrue(midi.getLength() == 4);
        assertTrue(midi.getStatus() == 1);
        assertTrue(midi.getMessage().length == 4);
        assertTrue(midi.getMessage()[0] == 1);
        assertTrue(midi.getMessage()[1] == 2);
        assertTrue(midi.getMessage()[2] == 3);
        assertTrue(midi.getMessage()[3] == -5);

        MidiMessage1 midi1 = new MidiMessage1(new byte[] { 0, -2 });
        assertTrue(midi1.getLength() == 2);
        assertTrue(midi1.getStatus() == 0);
        assertTrue(midi1.getMessage().length == 2);
        assertTrue(midi1.getMessage()[0] == 0);
        assertTrue(midi1.getMessage()[1] == -2);

        byte[] bt = new byte[] { -1, 87, 19 };
        MidiMessage1 midi2 = new MidiMessage1(bt);
        assertTrue(midi2.getLength() == 3);
        assertTrue(midi2.getStatus() == 255);
        assertTrue(midi2.getMessage().length == 3);
        assertTrue(midi2.getMessage()[0] == -1);
        assertTrue(midi2.getMessage()[1] == 87);
        assertTrue(midi2.getMessage()[2] == 19);
        bt[0] = 45;
        bt[1] = 16;
        bt[2] = -29;
        /**
         * values change
         */
        assertEquals(3, midi2.getLength());
        assertEquals(45, midi2.getStatus());
        assertEquals(3, midi2.getMessage().length);
        assertEquals(45, midi2.getMessage()[0]);
        assertEquals(16, midi2.getMessage()[1]);
        assertEquals(-29, midi2.getMessage()[2]);
        

        MidiMessage1 midi3 = new MidiMessage1(null);
        assertTrue(midi3.getLength() == 0);
        assertTrue(midi3.getStatus() == 0);
        try {
            midi3.getMessage();
            fail("No NPE");
        } catch(NullPointerException e) {
        }
        
        MidiMessage1 midi4 = new MidiMessage1(new byte[0]);
        assertEquals(0, midi4.getLength());
        assertEquals(0, midi4.getStatus());
        assertEquals(0, midi4.getMessage().length);

    }

    /**
     * test method getMessage() of class MidiMessage
     *
     */
    public void test_getMessage() throws Exception {
        byte[] bt = new byte[] {1, 2, 3};
        MidiMessage1 midi = new MidiMessage1(bt);
        assertTrue(midi.getMessage()[0] == 1);
        assertTrue(midi.getMessage()[1] == 2);
        assertTrue(midi.getMessage()[2] == 3);
        bt[0] = 76;
        bt[1] = 13;
        bt[2] = -5;
        /**
         * values change
         */
        assertEquals(76, midi.getMessage()[0]);
        assertEquals(13, midi.getMessage()[1]);
        assertEquals(-5, midi.getMessage()[2]);

        MidiMessage1 midi1 = new MidiMessage1(new byte[] {1});
        assertTrue(midi1.getMessage()[0] == 1);

        MidiMessage1 midi2 = new MidiMessage1(null);
        try {
            midi2.getMessage();
            fail("No NPE");
        } catch(NullPointerException e) {
        }

        midi2.setMessage(new byte[] { 90, 84 }, 2);
        assertTrue(midi2.getMessage().length == 2);
        assertTrue(midi2.getMessage()[0] == 90);
        assertTrue(midi2.getMessage()[1] == 84);
        byte[] nb = midi2.getMessage();
        nb[0] = 1;
        nb[1] = 2;
        /**
         * values don't change
         */
        assertEquals(90, midi2.getMessage()[0]);
        assertEquals(84, midi2.getMessage()[1]);

        midi1.setMessage(new byte[] { -54, 84, -9 }, 1);
        assertTrue(midi1.getMessage().length == 1);
        assertTrue(midi1.getMessage()[0] == -54);

        midi.setMessage(new byte[] { -90, -7, 4 }, 0);
        assertTrue(midi.getMessage().length == 0);
    }

    /**
     * test method getStatus() of class MidiMessage
     *
     */
    public void test_getStatus() throws Exception {
        byte[] bt = new byte[] {1, 2, 3};
        MidiMessage1 midi = new MidiMessage1(bt);
        assertTrue(midi.getStatus() == 1);
        bt[0] = -1;
        /**
         * value change
         */
        assertEquals(255, midi.getStatus());

        MidiMessage1 midi1 = new MidiMessage1(new byte[] { -91, 2, 3 });
        assertTrue(midi1.getStatus() == 165);

        MidiMessage1 midi2 = new MidiMessage1(new byte[] { 1 });
        assertTrue(midi2.getStatus() == 1);

        MidiMessage1 midi3 = new MidiMessage1(null);
        assertTrue(midi3.getStatus() == 0);

        bt = new byte[] {90, 84};
        midi2.setMessage(bt, 2);
        assertEquals(90, midi2.getStatus());
        bt[0] = 35;
        /*
         * value don't change
         */
        assertEquals(90, midi2.getStatus());

        midi1.setMessage(new byte[] {-54, 84, -9}, 1);
        assertTrue(midi1.getStatus() == 202);

        midi.setMessage(new byte[] { -90, -7, 4 }, 0);
        assertTrue(midi.getStatus() == 0);
    }

    /**
     * test method getLength() of class MidiMessage
     *
     */
    public void test_getLength() throws Exception {
        MidiMessage1 midi = new MidiMessage1(new byte[] {1, 2, 3});
        assertTrue(midi.getLength() == 3);

        MidiMessage1 midi1 = new MidiMessage1(null);
        assertTrue(midi1.getLength() == 0);

        MidiMessage1 midi2 = new MidiMessage1(new byte[] {-71, 2, 3, -90});
        assertTrue(midi2.getLength() == 4);

        midi2.setMessage(new byte[] {90, 84}, 2);
        assertTrue(midi2.getLength() == 2);

        midi1.setMessage(new byte[] { 90, 84, -9 }, 2);
        assertTrue(midi1.getLength() == 2);

        midi.setMessage(new byte[] { -90, -7, 4 }, 0);
        assertTrue(midi.getLength() == 0);
    }

    /**
     * test method setMessage( byte[], int ) of class MidiMessage
     *
     */
    public void test_setMessage() throws Exception {
        MidiMessage1 midi = new MidiMessage1(new byte[] {18, 34, 48, -56, 12});
        MidiMessage1 midi1 = new MidiMessage1(new byte[] {18});
        MidiMessage1 midi2 = new MidiMessage1(null);
        
        byte[] bt = new byte[] {90, 84};
        midi2.setMessage(bt, 2);
        assertEquals(2, midi2.getMessage().length);
        assertEquals(90, midi2.getMessage()[0]);
        assertEquals(84, midi2.getMessage()[1]);
        bt[0] = 34;
        bt[1] = -5;
        /*
         * values don't change
         */
        assertEquals(90, midi2.getMessage()[0]);
        assertEquals(84, midi2.getMessage()[1]);

        midi1.setMessage(new byte[] { -54, 84, -9 }, 1);
        assertTrue(midi1.getMessage().length == 1);
        assertTrue(midi1.getMessage()[0] == -54);

        midi.setMessage(new byte[] { -90, -7, 4 }, 0);
        assertTrue(midi.getMessage().length == 0);
        
        try {
            midi1.setMessage(new byte[] { -54, 84, -9 }, 5);
            fail("IndexOutOfBoundsException expected");
        } catch (IndexOutOfBoundsException e) {}
        
        try {
            midi1.setMessage(null, -2);
            fail("IndexOutOfBoundsException expected");
        } catch (IndexOutOfBoundsException e) {}
        
        try {
            midi1.setMessage(null, 0);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {}
        
    }

    /**
     * Subsidiary class in order to testing constructor
     * and method setMessage( byte[], int ) of class Instrument, 
     * because its declared as protected
     */
    static class MidiMessage1 extends MidiMessage {
        MidiMessage1(byte[] data) {
            super(data);
        }

        @Override
        public Object clone() {
            return null;
        }

        @Override
        public void setMessage(byte[] data, int length)
                throws InvalidMidiDataException {
            super.setMessage(data, length);
        }
    }
}
