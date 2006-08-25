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

public interface Mixer {
    static class Info{
        private String name;
        private String vendor;
        private String description;
        private String version;        
        
        public Info(String name, String vendor, String description, String version) {
            this.name = name;
            this.vendor = vendor;
            this.description = description;
            this.version = version;
        }
        
        public final boolean equals(Object another) {
            return this == another;
        }
        
        public String getDescription() {
            return description;
        }
        
        public String getName() {
            return name;
        }
        
        public String getVendor() {
            return vendor;
        }
        
        public String getVersion() {
            return version;
        }
        
        public final int hashCode() {
            return name.hashCode() + vendor.hashCode() + description.hashCode() + version.hashCode();
        }
        
        public String toString() {
            throw new Error("not yet implemented");
        }
    }
}
