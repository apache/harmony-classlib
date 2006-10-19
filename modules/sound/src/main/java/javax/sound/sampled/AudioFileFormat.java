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

public class AudioFileFormat {

    public static class Type {

        private String name;

        private String extension;

        public static final Type AIFC = new Type("AIFF-C", "aifc"); //$NON-NLS-1$ //$NON-NLS-2$

        public static final Type AIFF = new Type("AIFF", "aif"); //$NON-NLS-1$ //$NON-NLS-2$

        public static final Type AU = new Type("AU", "au"); //$NON-NLS-1$ //$NON-NLS-2$

        public static final Type SND = new Type("SND", "snd"); //$NON-NLS-1$ //$NON-NLS-2$

        public static final Type WAVE = new Type("WAVE", "wav"); //$NON-NLS-1$ //$NON-NLS-2$

        public Type(String name, String extension) {
            this.name = name;
            this.extension = extension;
        }

        /*
         * according to the spec it should return true when objects are same but
         * RI seem to compare internals
         * 
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public final boolean equals(Object another) {
            if (this == another) {
                return true;
            }

            if (another == null || !(another instanceof Type)) {
                return false;
            }

            Type obj = (Type) another;
            return (name == null ? obj.name == null : name.equals(obj.name))
                    && (extension == null ? obj.extension == null : extension
                            .equals(obj.extension));
        }

        public String getExtension() {
            return extension;
        }

        @Override
        public final int hashCode() {
            return (name == null ? 0 : name.hashCode()) + 
                    (extension == null ? 0 : extension.hashCode());
        }

        @Override
        public final String toString() {
            return name;
        }
    }
}
