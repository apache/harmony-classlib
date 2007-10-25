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
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 */
public class IcBands extends BandSet {
    
    private int[] icFlags;

    private Object icName;

    private String[] icOuterClass;

    private String[] icThisClass;

    private String[] cpUTF8;

    private String[] cpClass;

    /**
     * @param header
     */
    public IcBands(Segment segment) {
        super(segment);
        this.cpClass = segment.getCpBands().getCpClass();
        this.cpUTF8 = segment.getCpBands().getCpUTF8();
    }

    /* (non-Javadoc)
     * @see org.apache.harmony.pack200.BandSet#pack(java.io.OutputStream)
     */
    public void pack(OutputStream outputStream) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.apache.harmony.pack200.BandSet#unpack(java.io.InputStream)
     */
    public void unpack(InputStream in) throws IOException,
            Pack200Exception {
        int innerClassCount = header.getInnerClassCount();
        icThisClass = parseReferences("ic_this_class", in, Codec.UDELTA5,
                innerClassCount, cpClass);
        icFlags = decodeBandInt("ic_flags", in, Codec.UNSIGNED5, innerClassCount);
        int outerClasses = 0;
        for (int i = 0; i < innerClassCount; i++) {
            if ((icFlags[i] & 1 << 16) != 0)
                outerClasses++;
        }
        icOuterClass = parseReferences("ic_outer_class", in, Codec.DELTA5,
                outerClasses, cpClass);
        icName = parseReferences("ic_name", in, Codec.DELTA5, outerClasses,
                cpUTF8);
    }

}
