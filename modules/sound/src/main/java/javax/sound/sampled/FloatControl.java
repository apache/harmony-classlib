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

import org.apache.harmony.luni.util.NotImplementedException;

public abstract class FloatControl extends Control {

    public static class Type extends Control.Type {
        public static final Type MASTER_GAIN = new Type("MASTER_GAIN");

        public static final Type AUX_SEND = new Type("AUX_SEND");

        public static final Type AUX_RETURN = new Type("AUX_RETURN");

        public static final Type REVERB_SEND = new Type("REVERB_SEND");

        public static final Type REVERB_RETURN = new Type("REVERB_RETURN");

        public static final Type VOLUME = new Type("VOLUME");

        public static final Type PAN = new Type("PAN");

        public static final Type BALANCE = new Type("BALANCE");

        public static final Type SAMPLE_RATE = new Type("SAMPLE_RATE");

        protected Type(String name) {
            super(name);
        }
    }

    protected FloatControl(FloatControl.Type type, float minimum,
            float maximum, float precision, int updatePeriod,
            float initialValue, String units, String minLabel, String midLabel,
            String maxLabel) {
        super(type);
        this.maximum = maximum;
        this.maxLabel = maxLabel;
        this.midLabel = midLabel;
        this.minLabel = minLabel;
        this.minimum = minimum;
        this.precision = precision;
        this.units = units;
        this.updatePeriod = updatePeriod;
        this.value = initialValue;
    }

    protected FloatControl(FloatControl.Type type, float minimum,
            float maximum, float precision, int updatePeriod,
            float initialValue, String units) {
        super(type);
        this.maximum = maximum;
        this.maxLabel = "";
        this.midLabel = "";
        this.minLabel = "";
        this.minimum = minimum;
        this.precision = precision;
        this.units = units;
        this.updatePeriod = updatePeriod;
        this.value = initialValue;
    }

    private float value;

    private float maximum;

    private float minimum;

    private String units;

    private String minLabel;

    private String midLabel;

    private String maxLabel;

    private float precision;

    private int updatePeriod;

    public void setValue(float newValue) {
        this.value = newValue;
    }

    public float getValue() {
        return this.value;
    }

    public float getMaximum() {
        return this.maximum;
    }

    public float getMinimum() {
        return this.minimum;
    }

    public String getUnits() {
        return this.units;
    }

    public String getMinLabel() {
        return this.minLabel;
    }

    public String getMidLabel() {
        return this.midLabel;
    }

    public String getMaxLabel() {
        return this.maxLabel;
    }

    public float getPrecision() {
        return this.precision;
    }

    public int getUpdatePeriod() {
        return this.updatePeriod;
    }

    public void shift(float from, float to, int microseconds) throws NotImplementedException {
        throw new NotImplementedException("not yet implemented");
    }

    public String toString() throws NotImplementedException {
        throw new NotImplementedException("not yet implemented");
    }

}
