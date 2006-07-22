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

import java.util.Map;

public class MidiFileFormat {
    public static final int UNKNOWN_LENGTH = -1;

    protected int byteLength;

    protected float divisionType;

    protected long microsecondLength;

    protected int resolution;

    protected int type;

    public MidiFileFormat(int type, float divisionType, int resolution, int bytes,
            long microseconds) {
        //TODO
    }

    public MidiFileFormat(int type, float divisionType, int resolution, int bytes,
            long microseconds, Map<String, Object> properties) {
        //TODO
    }

    public int getByteLength() {
        //TODO
        return 1;
    }

    public float getDivisionType() {
        //TODO
        return 1.0F;
    }

    public long getMicrosecondLength() {
        //TODO
        return 1L;
    }

    public Object getProperty(String key) {
        //TODO
        return null;
    }

    public int getResolution() {
        //TODO
        return 1;
    }

    public int getType() {
        //TODO
        return 1;
    }

    public Map<String, Object> properties() {
        //TODO
        return null;
    }
}
