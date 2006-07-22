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

public class MetaMessage extends MidiMessage {
    public static final int META = 255;
    
    private int type;

    private byte[] data;

    public MetaMessage() {
        super(new byte[] { -1, 0 });
        data = new byte[0];
        type = 0;
    }

    protected MetaMessage(byte[] data) {
        super(data);
        if (data.length >= 2) {
            type = (int) (data[1] & 0xFF);
        } else {
            type = 0;
        }
        if (data.length > 3) {
            int n = 3;
            while (data[n - 1] < 0)
                n++;
            this.data = new byte[data.length - n];
            for (int i = n; i < data.length; i++) {
                this.data[i - n] = data[i];
            }
        } else {
            this.data = new byte[0];
        }

    }

    public Object clone() {
        return new MetaMessage(this.getMessage());
    }

    public byte[] getData() {
        return data.clone();
    }

    public int getType() {
        return type;
    }

    public void setMessage(int type, byte[] data, int length) throws InvalidMidiDataException {
        //FIXME
        if (type < 0 || type >= 128) {
            throw new InvalidMidiDataException("Invalid meta event with type " + type);
        }
        if (length < 0 || (data != null && length > data.length)) {
            throw new InvalidMidiDataException("length out of bounds: " + length);
        }
        try {
            if (data == null) {
                if (length != 0) {
                    throw new NullPointerException();
                }
                super.setMessage(new byte[] { -1, (byte) type, 0 }, 3);
                this.data = new byte[0];
            } else {
                int div = 128;
                int n = 1;
                int ost;
                int sm = 0;
                while (length / div != 0) {
                    n++;
                    div *= 128;
                }
                int ln = n;
                byte[] tdata = new byte[length + ln + 2];
                div = 1;
                ost = (length / div) % 128;
                while (n != 0) {
                    tdata[n - 1 + 2] = (byte) (ost + sm);
                    n--;
                    div *= 128;
                    ost = (length / div) % 128;
                    sm = 128;
                }
                tdata[0] = -1;
                tdata[1] = (byte) type;
                if (length > 0) {
                    for (int i = 0; i < length; i++) {
                        tdata[2 + ln + i] = data[i];
                    }
                }
                super.setMessage(tdata, length + 2 + ln);
                this.data = new byte[length];
                if (length > 0) {
                    for (int i = 0; i < length; i++)
                        this.data[i] = data[i];
                }
            }
        } catch (InvalidMidiDataException e) {
            throw e;
        }
        this.type = type;
    }
}
