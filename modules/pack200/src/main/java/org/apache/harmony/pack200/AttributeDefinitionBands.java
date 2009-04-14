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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Attribute Definition bands define how any unknown attributes should be
 * read by the decompressor.
 */
public class AttributeDefinitionBands extends BandSet {

    public static final int CONTEXT_CLASS = 0;
    public static final int CONTEXT_CODE = 3;
    public static final int CONTEXT_FIELD = 1;
    public static final int CONTEXT_METHOD = 2;

    private final Map layouts = new HashMap();

    private final Map classAttributes = new HashMap();
    private final Map methodAttributes = new HashMap();
    private final Map fieldAttributes = new HashMap();
    private final Map codeAttributes = new HashMap();

    private final List attributeDefinitions = new ArrayList();

    private final CpBands cpBands;
    private final Segment segment;

    public AttributeDefinitionBands(Segment segment, int effort) {
        super(effort, segment.getSegmentHeader());
        this.cpBands = segment.getCpBands();
        this.segment = segment;
    }

    public void finaliseBands() {
        addSyntheticDefinitions();
        segmentHeader.setAttribute_definition_count(classAttributes.keySet()
                .size()
                + methodAttributes.keySet().size()
                + fieldAttributes.keySet().size()
                + codeAttributes.keySet().size()
                + attributeDefinitions.size());
        if (classAttributes.keySet().size() > 7) {
            segmentHeader.setHave_class_flags_hi(true);
        }
        if(methodAttributes.keySet().size() > 6) {
            segmentHeader.setHave_method_flags_hi(true);
        }
        if(fieldAttributes.keySet().size() > 10) {
            segmentHeader.setHave_field_flags_hi(true);
        }
        if(codeAttributes.keySet().size() > 15) {
            segmentHeader.setHave_code_flags_hi(true);
        }
    }

    public void pack(OutputStream out) throws IOException, Pack200Exception {
        int[] availableClassIndices = new int[] {25, 26, 27, 28, 29, 30, 31};
        if(classAttributes.size() > 7) {
            availableClassIndices = addHighIndices(availableClassIndices);
        }
        addAttributeDefinitions(classAttributes, availableClassIndices, CONTEXT_CLASS);
        int[] availableMethodIndices = new int[] {26, 27, 28, 29, 30, 31};
        if(methodAttributes.size() > 6) {
            availableMethodIndices = addHighIndices(availableMethodIndices);
        }
        addAttributeDefinitions(methodAttributes, availableMethodIndices, CONTEXT_METHOD);
        int[] availableFieldIndices = new int[] {18, 23, 24, 25, 26, 27, 28, 29, 30, 31};
        if(fieldAttributes.size() > 10) {
            availableFieldIndices = addHighIndices(availableFieldIndices);
        }
        addAttributeDefinitions(fieldAttributes, availableFieldIndices, CONTEXT_FIELD);
        int[] availableCodeIndices = new int[] {17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
        if(codeAttributes.size() > 15) {
            availableCodeIndices = addHighIndices(availableCodeIndices);
        }
        addAttributeDefinitions(codeAttributes, availableCodeIndices, CONTEXT_CODE);

        int[] attributeDefinitionHeader = new int[attributeDefinitions.size()];
        int[] attributeDefinitionName = new int[attributeDefinitions.size()];
        int[] attributeDefinitionLayout = new int[attributeDefinitions.size()];
        for (int i = 0; i < attributeDefinitionLayout.length; i++) {
            AttributeDefinition def = (AttributeDefinition) attributeDefinitions.get(i);
            attributeDefinitionHeader[i] = def.contextType | (def.index + 1 << 2);
            attributeDefinitionName[i] = def.name.getIndex();
            attributeDefinitionLayout[i] = def.layout.getIndex();
        }

        out.write(encodeBandInt("attributeDefinitionHeader", attributeDefinitionHeader, Codec.BYTE1));
        out.write(encodeBandInt("attributeDefinitionName", attributeDefinitionName, Codec.UNSIGNED5));
        out.write(encodeBandInt("attributeDefinitionLayout", attributeDefinitionLayout, Codec.UNSIGNED5));
    }

    private void addSyntheticDefinitions() {
        boolean anySytheticClasses = segment.getClassBands().isAnySyntheticClasses();
        boolean anySyntheticMethods = segment.getClassBands().isAnySyntheticMethods();
        boolean anySyntheticFields = segment.getClassBands().isAnySyntheticFields();
        if(anySytheticClasses || anySyntheticMethods || anySyntheticFields) {
            CPUTF8 syntheticUTF = cpBands.getCPUtf8("Synthetic");
            CPUTF8 emptyUTF = cpBands.getCPUtf8("");
            if(anySytheticClasses) {
                attributeDefinitions.add(new AttributeDefinition(12, CONTEXT_CLASS, syntheticUTF, emptyUTF));
            }
            if(anySyntheticMethods) {
                attributeDefinitions.add(new AttributeDefinition(12, CONTEXT_METHOD, syntheticUTF, emptyUTF));
            }
            if(anySyntheticFields) {
                attributeDefinitions.add(new AttributeDefinition(12, CONTEXT_FIELD, syntheticUTF, emptyUTF));
            }
        }
    }

    private int[] addHighIndices(int[] availableIndices) {
        int[] temp = new int[availableIndices.length + 32];
        for (int i = 0; i < availableIndices.length; i++) {
            temp[i] = availableIndices[i];
        }
        int j = 32;
        for (int i = availableIndices.length; i < temp.length ; i++) {
            temp[i] = j;
            j++;
        }
        return temp;
    }

    private void addAttributeDefinitions(Map attributes,
            int[] availableIndices, int contextType) {
        int i = 0;
        for (Iterator iterator = attributes.keySet().iterator(); iterator.hasNext();) {
            String name = (String) iterator.next();
            String layout = (String) layouts.get(name);
            int index = availableIndices[i];
            attributeDefinitions.add(new AttributeDefinition(index, contextType, cpBands.getCPUtf8(name), cpBands.getCPUtf8(layout)));
        }
    }

    public void addLayout(String name, String layout) {
        layouts.put(name, layout);
    }

    private static class AttributeDefinition {

        public int index;
        public int contextType;
        public CPUTF8 name;
        public CPUTF8 layout;

        public AttributeDefinition(int index, int contextType, CPUTF8 name,
                CPUTF8 layout) {
            this.index = index;
            this.contextType = contextType;
            this.name = name;
            this.layout = layout;
        }

    }
}
