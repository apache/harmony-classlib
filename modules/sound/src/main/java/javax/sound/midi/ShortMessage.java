/*
 *  Copyright 2006 The Apache Software Foundation or its licensors, as applicable.
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

package javax.sound.midi;

public class ShortMessage extends MidiMessage {
    public static final int ACTIVE_SENSING = 254;

    public static final int CHANNEL_PRESSURE = 208;

    public static final int CONTINUE = 251;

    public static final int CONTROL_CHANGE = 176;

    public static final int END_OF_EXCLUSIVE = 247;

    public static final int MIDI_TIME_CODE = 241;

    public static final int NOTE_OFF = 128;

    public static final int NOTE_ON = 144;

    public static final int PITCH_BEND = 224;

    public static final int POLY_PRESSURE = 160;

    public static final int PROGRAM_CHANGE = 192;

    public static final int SONG_POSITION_POINTER = 242;

    public static final int SONG_SELECT = 243;

    public static final int START = 250;

    public static final int STOP = 252;

    public static final int SYSTEM_RESET = 255;

    public static final int TIMING_CLOCK = 248;

    public static final int TUNE_REQUEST = 246;

    public ShortMessage() {
        //TODO
        super(null);
    }

    protected ShortMessage(byte[] data) {
        //TODO
        super(data);
    }

    public Object clone() {
        //TODO
        return null;
    }

    public int getChannel() {
        //TODO
        return 1;
    }

    public int getCommand() {
        //TODO
        return 1;
    }

    public int getData1() {
        //TODO
        return 1;
    }

    public int getData2() {
        //TODO
        return 1;
    }

    protected final int getDataLength(int status) throws InvalidMidiDataException {
        //TODO
        return 1;
    }

    public void setMessage(int status) throws InvalidMidiDataException {
        //TODO
    }

    public void setMessage(int status, int data1, int data2) throws InvalidMidiDataException {
        //TODO
    }

    public void setMessage(int command, int channel, int data1, int data2)
            throws InvalidMidiDataException {
        //TODO
    }

}
