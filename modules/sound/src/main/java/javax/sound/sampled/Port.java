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

import javax.sound.sampled.Line;

import org.apache.harmony.luni.util.NotImplementedException;

public interface Port extends Line {

    public static class Info extends Line.Info {

        private String name;

        private boolean isSource;

        public static final Info MICROPHONE = new Info(Port.class,
                "MICROPHONE", true);

        public static final Info LINE_IN = new Info(Port.class, "LINE_IN", true);

        public static final Info COMPACT_DISC = new Info(Port.class,
                "COMPACT_DISC", true);

        public static final Info SPEAKER = new Info(Port.class, "SPEAKER",
                false);

        public static final Info HEADPHONE = new Info(Port.class, "HEADPHONES",
                false);

        public static final Info LINE_OUT = new Info(Port.class, "LINE_OUT",
                false);

        public Info(Class<?> lineClass, String name, boolean isSource) {
            super(lineClass);
            this.name = name;
            this.isSource = isSource;
        }

        public String getName() {
            return this.name;
        }

        public boolean isSource() {
            return this.isSource;
        }

        public boolean matches(Line.Info info) throws NotImplementedException {
            throw new NotImplementedException("not yet implemented");
        }

        public final boolean equals(Object obj) throws NotImplementedException {
            throw new NotImplementedException("not yet implemented");
        }

        public final int hashCode() throws NotImplementedException {
            throw new NotImplementedException("not yet implemented");
        }

        public final String toString() throws NotImplementedException {
            throw new NotImplementedException("not yet implemented");
        }
    }
}
