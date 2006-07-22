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

public abstract class SoundbankResource {
    private Soundbank soundbank;
    private String name;
    private Class<?> dataClass;
    
    protected SoundbankResource(Soundbank soundbank, String name, Class<?> dataClass) {
        super();
        this.soundbank = soundbank;
        this.name = name;
        this.dataClass = dataClass;
    }

    public abstract Object getData();

    public Class<?> getDataClass() {
        return dataClass;
    }

    public String getName() {
        return name;
    }

    public Soundbank getSoundbank() {
        return soundbank;
    }
}
