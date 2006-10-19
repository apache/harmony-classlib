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

package javax.sound.sampled;

public class AudioFormat {
    public static class Encoding {

        public static final Encoding ALAW = new Encoding("ALAW"); //$NON-NLS-1$

        public static final Encoding PCM_SIGNED = new Encoding("PCM_SIGNED"); //$NON-NLS-1$

        public static final Encoding PCM_UNSIGNED = new Encoding("PCM_UNSIGNED"); //$NON-NLS-1$

        public static final Encoding ULAW = new Encoding("ULAW"); //$NON-NLS-1$

        private String name;

        public Encoding(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object another) {
            if (this == another) {
                return true;
            }

            if (another == null || !(another instanceof Encoding)) {
                return false;
            }

            Encoding obj = (Encoding) another;
            return name == null ? obj.name == null : name.equals(obj.name);
        }

        @Override
        public final int hashCode() {
            return name == null ? 0 : name.hashCode();
        }

        @Override
        public final String toString() {
            return name;
        }
    }

    protected boolean bigEndian;

    protected int channels;

    protected Encoding encoding;

    protected float frameRate;

    protected int frameSize;

    protected float sampleRate;

    protected int sampleSizeInBits;

    public Encoding getEncoding() {
        throw new Error("not yet implemented");
    }
}
