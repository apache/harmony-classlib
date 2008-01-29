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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.harmony.pack200.bytecode.CPClass;
import org.apache.harmony.pack200.bytecode.ClassConstantPool;

/**
 * Pack200 Inner Class Bands
 */
public class IcBands extends BandSet {
    

    private IcTuple[] icAll;

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
        icAll = new IcTuple[icThisClass.length];
        int index = 0;
        for (int i = 0; i < icThisClass.length; i++) {
            icAll[i] = new IcTuple();
            icAll[i].C = icThisClass[i];
            icAll[i].F = icFlags[i];
            if((icFlags[i] & 1<<16) != 0) {
                icAll[i].C2 = icOuterClass[index];
                icAll[i].N = icName[index];
                index++;
            }
        }
    }

    public IcTuple[] getIcTuples() {
        return icAll;
    }

    /**
     * Answer the relevant IcTuples for the specified className
     * and class constant pool.
     * @param className String name of the class X for ic_relevant(X)
     * @param cp ClassConstantPool used to generate ic_relevant(X)
     * @return array of IcTuple
     */
    public IcTuple[] getRelevantIcTuples(String className, ClassConstantPool cp) {
        List relevantTuples = new ArrayList();
        IcTuple[] allTuples = getIcTuples();
        int allTuplesSize = allTuples.length;
        SegmentUtils.debug("-------\nRelevant() " + className);
        for(int index=0; index < allTuplesSize; index++) {
            if(allTuples[index].outerClassString().equals(className)) {
                relevantTuples.add(allTuples[index]);
            }
        }

        SegmentUtils.debug("self halt");
        List classPoolClasses = cp.allClasses();
        boolean changed = true;
        // For every class in both ic_this_class and cp,
        // add it to ic_relevant. Repeat until no more
        // changes to ic_relevant.
        while(changed) {
            changed = false;
            for(int allTupleIndex=0; allTupleIndex < allTuplesSize; allTupleIndex++) {
                Iterator it = classPoolClasses.iterator();
                SegmentUtils.debug("\n\n----\nLooking through class pool for: " + allTuples[allTupleIndex].thisClassString());
                while(it.hasNext()) {
                    CPClass classInPool = (CPClass)it.next();
                    String poolClassName = classInPool.name;
                    SegmentUtils.debug("    " + poolClassName);
                    if(poolClassName.equals(allTuples[allTupleIndex].thisClassString())) {
                        // If the tuple isn't already in there, then add it
                        SegmentUtils.debug("     -> match");
                        if(relevantTuples.indexOf(allTuples[allTupleIndex]) == -1) {
                            SegmentUtils.debug("        -> added");
                            relevantTuples.add(allTuples[allTupleIndex]);
                            changed = true;
                        }
                    }
                    // TODO: is this right?
                    if(poolClassName.equals(allTuples[allTupleIndex].outerClassString())) {
                        // If the tuple isn't already in there, then add it
                        SegmentUtils.debug("     -> omatch");
                        if(relevantTuples.indexOf(allTuples[allTupleIndex]) == -1) {
                            SegmentUtils.debug("        -> oadded");
                            relevantTuples.add(allTuples[allTupleIndex]);
                            changed = true;
                        }
                    }
                }
            }
        }
        
        IcTuple[] result = new IcTuple[relevantTuples.size()];
        for(int index=0; index < result.length; index++) {
            result[index] = (IcTuple)relevantTuples.get(index);
            SegmentUtils.debug("Returning relevantTuple: " + result[index].thisClassString());
        }
        return result;
    }
  
}