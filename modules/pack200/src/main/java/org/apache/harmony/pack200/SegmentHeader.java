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

import java.io.IOException;
import java.io.OutputStream;


public class SegmentHeader extends BandSet {

    private static final int[] magic = { 0xCA, 0xFE, 0xD0, 0x0D };

    private int cpUtf8Count;
    private int cpIntCount;
    private int cpFloatCount;
    private int cpLongCount;
    private int cpDoubleCount;
    private int cpStringCount;
    private int cpClassCount;
    private int cpSignatureCount;
    private int cpDescrCount;
    private int cpFieldCount;
    private int cpMethodCount;
    private int cpImethodCount;
    private int attributeDefinitionCount;

    public void pack(OutputStream out) throws IOException {
        out.write(encodeScalar(magic));
    }

    public void setCpUtf8Count(int count) {
        cpUtf8Count = count;
    }

    public void setCpIntCount(int count) {
        cpIntCount = count;
    }

    public void setCpFloatCount(int count) {
        cpFloatCount = count;
    }

    public void setCpLongCount(int count) {
        cpLongCount = count;
    }

    public void setCpDoubleCount(int count) {
        cpDoubleCount = count;
    }

    public void setCpStringCount(int count) {
        cpStringCount = count;
    }

    public void setCpClassCount(int count) {
        cpClassCount = count;
    }

    public void setCpSignatureCount(int count) {
        cpSignatureCount = count;
    }

    public void setCpDescrCount(int count) {
        cpDescrCount = count;
    }

    public void setCpFieldCount(int count) {
        cpFieldCount = count;
    }

    public void setCpMethodCount(int count) {
        cpMethodCount = count;
    }

    public void setCpImethodCount(int count) {
        cpImethodCount = count;
    }

    public void setAttributeDefinitionCount(int count) {
        attributeDefinitionCount = count;
    }

}
