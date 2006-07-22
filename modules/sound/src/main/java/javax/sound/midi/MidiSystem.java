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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class MidiSystem {
    public static MidiDevice getMidiDevice(MidiDevice.Info info)
            throws MidiUnavailableException {
        //TODO
        return null;
    }

    public static MidiDevice.Info[] getMidiDeviceInfo() {
        //TODO
        return null;
    }

    public static MidiFileFormat getMidiFileFormat(File file) throws InvalidMidiDataException,
            IOException {
        //TODO
        return null;
    }

    public static MidiFileFormat getMidiFileFormat(InputStream stream)
            throws InvalidMidiDataException, IOException {
        //TODO
        return null;
    }

    public static MidiFileFormat getMidiFileFormat(URL url) throws InvalidMidiDataException,
            IOException {
        //TODO
        return null;
    }

    public static int[] getMidiFileTypes() {
        //TODO
        return null;
    }

    public static int[] getMidiFileTypes(Sequence sequence) {
        //TODO
        return null;
    }

    public static Receiver getReceiver() throws MidiUnavailableException {
        //TODO
        return null;
    }

    public static Sequence getSequence(File file) throws InvalidMidiDataException, IOException {
        //TODO
        return null;
    }

    public static Sequence getSequence(InputStream stream) throws InvalidMidiDataException,
            IOException {
        //TODO
        return null;
    }

    public static Sequence getSequence(URL url) throws InvalidMidiDataException, IOException {
        //TODO
        return null;
    }

    public static Sequencer getSequencer() throws MidiUnavailableException {
        //TODO
        return null;
    }

    public static Sequencer getSequencer(boolean connected) throws MidiUnavailableException {
        //TODO
        return null;
    }

    public static Soundbank getSoundbank(File file) throws InvalidMidiDataException,
            IOException {
        //TODO
        return null;
    }

    public static Soundbank getSoundbank(InputStream stream) throws InvalidMidiDataException,
            IOException {
        //TODO
        return null;
    }

    public static Soundbank getSoundbank(URL url) throws InvalidMidiDataException, IOException {
        //TODO
        return null;
    }

    public static Synthesizer getSynthesizer() throws MidiUnavailableException {
        //TODO
        return null;
    }

    public static Transmitter getTransmitter() throws MidiUnavailableException {
        //TODO
        return null;
    }

    public static boolean isFileTypeSupported(int fileType) {
        //TODO
        return false;
    }

    public static boolean isFileTypeSupported(int fileType, Sequence sequence) {
        //TODO
        return false;
    }

    public static int write(Sequence in, int type, File out) throws IOException {
        //TODO
        return 1;
    }

    public static int write(Sequence in, int fileType, OutputStream out) throws IOException {
        //TODO
        return 1;
    }
}
