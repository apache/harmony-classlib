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
package org.apache.harmony.pack200;

public class SegmentConstantPool {
    /**
     * 
     */
    private CpBands bands;

    /**
     * @param bands
     */
    public SegmentConstantPool(CpBands bands) {
        this.bands = bands;
    }

    public static final int ALL = 0;

    public static final int CP_DOUBLE = 7;

    // define in archive order

    public static final int CP_FLOAT = 4; // TODO Check this

    public static final int CP_INT = 3;

    public static final int CP_LONG = 6;

    public static final int CP_STRING = 5;

    public static final int SIGNATURE = 2; // TODO and more to come --

    public static final int UTF_8 = 1;

    public Object getValue(int cp, long value) throws Pack200Exception {
        int index = (int) value;
        if (index == -1) {
            return null;
        } else if (index < 0) {
            throw new Pack200Exception("Cannot have a negative range");
        } else if (cp == UTF_8) {
            return bands.getCpUTF8()[index];
        } else if (cp == CP_STRING) {
            return bands.getCpString()[index];
        } else if (cp == SIGNATURE) {
            return bands.getCpSignature()[index];
        } else if (cp == CP_INT) {
            return new Integer(bands.getCpInt()[index]);
        } else if (cp == CP_FLOAT) {
            return new Float(bands.getCpFloat()[index]);
        } else if (cp == CP_DOUBLE) {
            return new Double(bands.getCpDouble()[index]);
        } else if (cp == CP_LONG) {
            return new Long(bands.getCpLong()[index]);
        } else {
            // etc
            throw new Error("Get value incomplete");
        }
    }
}
