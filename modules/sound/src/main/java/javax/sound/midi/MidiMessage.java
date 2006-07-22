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

public abstract class MidiMessage implements Cloneable {
    private int status;

    protected byte[] data;

    protected int length;

    protected MidiMessage(byte[] data) {
        if (data == null || data.length == 0) {
            data = null;
        } else {
            length = data.length;
            status = (int) (data[0] & 0xFF);
            this.data = data.clone();
        }
    }

    public abstract Object clone();

    public int getLength() {
        return length;
    }

    public byte[] getMessage() {
        return data.clone();
    }

    public int getStatus() {
        return status;
    }

    protected void setMessage(byte[] data, int length) throws InvalidMidiDataException {
        //FIXME
        /**
         * this method should throw out IndexOutOfBoundsException when 
         * I use negative length
         */
        if (length < 0)
            throw new IndexOutOfBoundsException();

        byte[] tdata = new byte[length];
        if (length == 0 && data != null) {
            status = 0;
        } else {
            for (int i = 0; i < length; i++) {
                tdata[i] = data[i];
            }
            status = (int) (data[0] & 0xFF);
            this.length = length;
        }
        this.length = length;
        this.data = tdata;

    }
}
