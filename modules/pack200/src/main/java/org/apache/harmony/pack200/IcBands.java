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
 * Pack200 Inner Class Bands
 */
public class IcBands extends BandSet {
    
    public static class ICTuple {

        public String C; // this class
        public int F; // flags
        public String C2; // outer class
        public String N; // name

    }

    private ICTuple[] icAll;

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
        // Read IC bands
        int innerClassCount = header.getInnerClassCount();
        String[] icThisClass = parseReferences("ic_this_class", in, Codec.UDELTA5,
                innerClassCount, cpClass);
        int[] icFlags = decodeBandInt("ic_flags", in, Codec.UNSIGNED5, innerClassCount);
        int outerClasses = SegmentUtils.countBit16(icFlags);
        String[] icOuterClass = parseReferences("ic_outer_class", in, Codec.DELTA5,
                outerClasses, cpClass);
        String[] icName = parseReferences("ic_name", in, Codec.DELTA5, outerClasses,
                cpUTF8);
        
        // Construct IC tuples
        icAll = new ICTuple[icThisClass.length];
        int index = 0;
        for (int i = 0; i < icThisClass.length; i++) {
            icAll[i] = new ICTuple();
            icAll[i].C = icThisClass[i];
            icAll[i].F = icFlags[i];
            if((icFlags[i] & 1<<16) != 0) {
                icAll[i].C2 = icOuterClass[index];
                icAll[i].N = icName[index];
                index++;
            }
        }
    }

    public ICTuple[] getIcTuples() {
        return icAll;
    }

  
}