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

public interface Line {

    class Info {
        private Class <?> lineClass;
        
        public Info(Class <?> lineClass) {
            this.lineClass = lineClass;
        }

        public Class <?> getLineClass( ){
            return lineClass;
        }

        public boolean matches(Line.Info info) {
            throw new Error("not yet implemented");
        }
        
        public String toString() {
            throw new Error("not yet implemented");
        }
    }

    void addLineListener(LineListener listener);

    void close();

    Control getControl(Control.Type control);

    Control[] getcontrols();

    Line.Info getLineInfo();

    boolean isControlSupported(Control.Type control);

    boolean isOpen();

    void open();

    void removeLineListener(LineListener listener);
}
