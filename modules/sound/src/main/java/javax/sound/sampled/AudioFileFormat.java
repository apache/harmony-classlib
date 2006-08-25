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

package javax.sound.sampled;

public class AudioFileFormat {

    public static class Type {

        private String name;

        private String extension;

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

        public final int hashCode() {
            return name.hashCode() + extension.hashCode();
        }

        public final String toString() {
            throw new Error("not yet implemented");
        }
    }
}
