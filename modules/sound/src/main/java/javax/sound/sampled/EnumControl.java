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

public abstract class EnumControl extends Control {
    public static class Type extends Control.Type {
        public static final Type REVERB = new Type("REVERB");

        protected Type(String name) {
            super(name);
        }
    }

    private Object[] values;

    private Object value;

    protected EnumControl(EnumControl.Type type, Object[] values, Object value) {
        super(type);
        this.value = value;
        this.values = values;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public Object[] getValues() {
        return values;
    }

    public String toString() {
        throw new Error("Not yet implemented");
    }
}
