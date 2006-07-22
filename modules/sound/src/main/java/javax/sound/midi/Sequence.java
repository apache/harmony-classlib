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

import java.util.Vector;

public class Sequence {
    public static final float PPQ = 0.0f;

    public static final float SMPTE_24 = 24.0f;

    public static final float SMPTE_25 = 25.0f;

    public static final float SMPTE_30 = 30.0f;

    public static final float SMPTE_30DROP = 29.969999313354492f;
    
    protected float divisionType;

    protected int resolution;

    protected Vector<Track> tracks;

    public Sequence(float divisionType, int resolution) throws InvalidMidiDataException {
        //TODO
    }

    public Sequence(float divisionType, int resolution, int numTracks)
            throws InvalidMidiDataException {
        //TODO
    }

    public Track createTrack() {
        //TODO
        return null;
    }

    public boolean deleteTrack(Track track) {
        //TODO
        return false;
    }

    public float getDivisionType() {
        //TODO
        return 1.0f;
    }

    public long getMicrosecondLength() {
        //TODO
        return 1L;
    }

    public Patch[] getPatchList() {
        //TODO
        return null;
    }

    public int getResolution() {
        //TODO
        return 1;
    }

    public long getTickLength() {
        //TODO
        return 1L;
    }

    public Track[] getTracks() {
        //TODO
        return null;
    }

}
