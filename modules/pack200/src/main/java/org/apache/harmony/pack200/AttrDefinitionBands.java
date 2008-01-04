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

/**
 *
 */
public class AttrDefinitionBands extends BandSet {

    private int[] attributeDefinitionHeader;

    private String[] attributeDefinitionLayout;    

    private String[] attributeDefinitionName;

    private AttributeLayoutMap attributeDefinitionMap;

    private String[] cpUTF8;
    
    public AttrDefinitionBands(Segment segment) {
        super(segment);
        this.cpUTF8 = segment.getCpBands().getCpUTF8();
    }

    /* (non-Javadoc)
     * @see org.apache.harmony.pack200.BandSet#unpack(java.io.InputStream)
     */
    public void unpack(InputStream in) throws IOException,
            Pack200Exception {
        int attributeDefinitionCount = header.getAttributeDefinitionCount();
        attributeDefinitionHeader = decodeBandInt("attr_definition_headers",
                in, Codec.BYTE1, attributeDefinitionCount);
        attributeDefinitionName = parseReferences("attr_definition_name", in,
                Codec.UNSIGNED5, attributeDefinitionCount, cpUTF8);
        attributeDefinitionLayout = parseReferences("attr_definition_layout",
                in, Codec.UNSIGNED5, attributeDefinitionCount, cpUTF8);
        
        attributeDefinitionMap = new AttributeLayoutMap();
        
        int overflowIndex = 32;
        if(segment.getSegmentHeader().getOptions().hasClassFlagsHi()) {
            overflowIndex = 63;
        }
        for (int i = 0; i < attributeDefinitionCount; i++) {
            int context = attributeDefinitionHeader[i] & 0x03;
            int index = (attributeDefinitionHeader[i] >> 2) - 1;
            if(index == -1) {
                index = overflowIndex++;
            }
            AttributeLayout layout = new AttributeLayout(
                    attributeDefinitionName[i], context,
                    attributeDefinitionLayout[i], index, false);
            NewAttributeBands newBands = new NewAttributeBands(segment, layout);
            attributeDefinitionMap.add(layout, newBands);
        }
        attributeDefinitionMap.checkMap();
    }

    public AttributeLayoutMap getAttributeDefinitionMap() {
        return attributeDefinitionMap;
    }

}
