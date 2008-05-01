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
package org.apache.harmony.unpack200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.harmony.unpack200.bytecode.AnnotationDefaultAttribute;
import org.apache.harmony.unpack200.bytecode.Attribute;
import org.apache.harmony.unpack200.bytecode.CPDouble;
import org.apache.harmony.unpack200.bytecode.CPFloat;
import org.apache.harmony.unpack200.bytecode.CPInteger;
import org.apache.harmony.unpack200.bytecode.CPLong;
import org.apache.harmony.unpack200.bytecode.CPUTF8;
import org.apache.harmony.unpack200.bytecode.ClassConstantPool;
import org.apache.harmony.unpack200.bytecode.RuntimeVisibleorInvisibleAnnotationsAttribute;
import org.apache.harmony.unpack200.bytecode.RuntimeVisibleorInvisibleParameterAnnotationsAttribute;
import org.apache.harmony.unpack200.bytecode.AnnotationsAttribute.Annotation;
import org.apache.harmony.unpack200.bytecode.AnnotationsAttribute.ElementValue;
import org.apache.harmony.unpack200.bytecode.RuntimeVisibleorInvisibleParameterAnnotationsAttribute.ParameterAnnotation;

/**
 * A group of metadata bands, such as class_RVA_bands, method_AD_bands etc.
 */
public class MetadataBandGroup {

    private final String type;
    private final CpBands cpBands;
    
    private final CPUTF8 rvaUTF8 = new CPUTF8("RuntimeVisibleAnnotations",
            ClassConstantPool.DOMAIN_ATTRIBUTEASCIIZ);
    private final CPUTF8 riaUTF8 = new CPUTF8("RuntimeInvisibleAnnotations",
            ClassConstantPool.DOMAIN_ATTRIBUTEASCIIZ);
    private final CPUTF8 rvpaUTF8 = new CPUTF8(
            "RuntimeVisibleParameterAnnotations",
            ClassConstantPool.DOMAIN_ATTRIBUTEASCIIZ);
    private final CPUTF8 ripaUTF8 = new CPUTF8(
            "RuntimeInvisibleParameterAnnotations",
            ClassConstantPool.DOMAIN_ATTRIBUTEASCIIZ);

    public MetadataBandGroup(String type, CpBands cpBands) {
        this.type = type;
        this.cpBands = cpBands;
    }

    private List attributes;

    public int[] param_NB;
    public int[] anno_N;
    public CPUTF8[][] type_RS;
    public int[][] pair_N;
    public CPUTF8[] name_RU;
    public int[] T;
    public CPInteger[] caseI_KI;
    public CPDouble[] caseD_KD;
    public CPFloat[] caseF_KF;
    public CPLong[] caseJ_KJ;
    public CPUTF8[] casec_RS;
    public String[] caseet_RS;
    public String[] caseec_RU;
    public CPUTF8[] cases_RU;
    public int[] casearray_N;
    public CPUTF8[] nesttype_RS;
    public int[] nestpair_N;
    public CPUTF8[] nestname_RU;

    private Iterator caseI_Iterator;

    private Iterator caseD_Iterator;

    private Iterator caseF_Iterator;

    private Iterator caseJ_Iterator;

    private Iterator casec_Iterator;

    private Iterator caseet_Iterator;

    private Iterator caseec_Iterator;

    private Iterator cases_Iterator;

    private Iterator casearray_Iterator;

    private Iterator T_iterator;

    private Iterator nesttype_RS_Iterator;

    private Iterator nestpair_N_Iterator;

    private Iterator nestname_RU_Iterator;

    private Iterator anno_N_Iterator;

    private Iterator type_RS_Iterator;

    private Iterator pair_N_Iterator;

    public List getAttributes() {
        if (attributes == null) {
            attributes = new ArrayList();
            if (name_RU != null) {
                Iterator name_RU_Iterator = Arrays.asList(name_RU).iterator();
                if (!type.equals("AD")) {
                    T_iterator = Arrays.asList(boxArray(T)).iterator();
                }
                caseI_Iterator = Arrays.asList(caseI_KI).iterator();
                caseD_Iterator = Arrays.asList(caseD_KD).iterator();
                caseF_Iterator = Arrays.asList(caseF_KF).iterator();
                caseJ_Iterator = Arrays.asList(caseJ_KJ).iterator();
                casec_Iterator = Arrays.asList(casec_RS).iterator();
                caseet_Iterator = Arrays.asList(caseet_RS).iterator();
                caseec_Iterator = Arrays.asList(caseec_RU).iterator();
                cases_Iterator = Arrays.asList(cases_RU).iterator();
                casearray_Iterator = Arrays.asList(boxArray(casearray_N))
                        .iterator();
                nesttype_RS_Iterator = Arrays.asList(nesttype_RS).iterator();
                nestpair_N_Iterator = Arrays.asList(boxArray(nestpair_N))
                        .iterator();
                nestname_RU_Iterator = Arrays.asList(nestname_RU).iterator();
                if (type.equals("RVA") || type.equals("RIA")) {
                    for (int i = 0; i < anno_N.length; i++) {
                        attributes.add(getAttribute(anno_N[i], type_RS[i],
                                pair_N[i], name_RU_Iterator));
                    }
                } else if (type.equals("RVPA") || type.equals("RIPA")) {
                    anno_N_Iterator = Arrays.asList(boxArray(anno_N))
                            .iterator();
                    type_RS_Iterator = Arrays.asList(type_RS).iterator();
                    pair_N_Iterator = Arrays.asList(pair_N).iterator();
                    for (int i = 0; i < param_NB.length; i++) {
                        attributes.add(getParameterAttribute(param_NB[i],
                                name_RU_Iterator));
                    }
                } else { // type.equals("AD")
                    for (int i = 0; i < T.length; i++) {
                        attributes.add(new AnnotationDefaultAttribute(
                                new ElementValue(T[i], getNextValue(T[i]))));
                    }
                }
            }
        }
        return attributes;
    }

    private Attribute getAttribute(int numAnnotations, CPUTF8[] types,
            int[] pairCounts, Iterator namesIterator) {
        Annotation[] annotations = new Annotation[numAnnotations];
        for (int i = 0; i < numAnnotations; i++) {
            annotations[i] = getAnnotation(types[i], pairCounts[i],
                    namesIterator);
        }
        return new RuntimeVisibleorInvisibleAnnotationsAttribute(type
                .equals("RVA") ? rvaUTF8 : riaUTF8, annotations);
    }

    private Attribute getParameterAttribute(int numParameters,
            Iterator namesIterator) {
        ParameterAnnotation[] parameter_annotations = new ParameterAnnotation[numParameters];
        for (int i = 0; i < numParameters; i++) {
            int numAnnotations = ((Integer) anno_N_Iterator.next()).intValue();
            int[] pairCounts = (int[]) pair_N_Iterator.next();
            Annotation[] annotations = new Annotation[numAnnotations];
            for (int j = 0; j < annotations.length; j++) {
                annotations[j] = getAnnotation(
                        (CPUTF8) type_RS_Iterator.next(), pairCounts[j],
                        namesIterator);
            }
            parameter_annotations[i] = new ParameterAnnotation(annotations);
        }
        return new RuntimeVisibleorInvisibleParameterAnnotationsAttribute(type
                .equals("RVPA") ? rvpaUTF8 : ripaUTF8,
                parameter_annotations);
    }

    private Annotation getAnnotation(CPUTF8 type, int pairCount,
            Iterator namesIterator) {
        CPUTF8[] elementNames = new CPUTF8[pairCount];
        ElementValue[] elementValues = new ElementValue[pairCount];
        for (int j = 0; j < elementNames.length; j++) {
            elementNames[j] = (CPUTF8) namesIterator.next();
            int t = ((Integer) T_iterator.next()).intValue();
            elementValues[j] = new ElementValue(t, getNextValue(t));
        }
        return new Annotation(pairCount, type, elementNames, elementValues);
    }

    private Object getNextValue(int t) {
        switch (t) {
        case 'B':
        case 'C':
        case 'I':
        case 'S':
        case 'Z':
            return caseI_Iterator.next();
        case 'D':
            return caseD_Iterator.next();
        case 'F':
            return caseF_Iterator.next();
        case 'J':
            return caseJ_Iterator.next();
        case 'c':
            return casec_Iterator.next();
        case 'e':
            // TODO: check this - it may not work if the first string already
            // has a colon in it
            String enumString = caseet_Iterator.next() + ":"
                    + caseec_Iterator.next();
            return cpBands.cpNameAndTypeValue(enumString);
        case 's':
            return cases_Iterator.next();
        case '[':
            int arraySize = ((Integer) casearray_Iterator.next()).intValue();
            ElementValue[] nestedArray = new ElementValue[arraySize];
            for (int i = 0; i < arraySize; i++) {
                int nextT = ((Integer) T_iterator.next()).intValue();
                nestedArray[i] = new ElementValue(nextT, getNextValue(nextT));
            }
            return nestedArray;
        case '@':
            CPUTF8 type = (CPUTF8) nesttype_RS_Iterator.next();
            int numPairs = ((Integer) nestpair_N_Iterator.next()).intValue();

            return getAnnotation(type, numPairs, nestname_RU_Iterator);
        }
        return null;
    }

    private Integer[] boxArray(int[] unboxed) {
        Integer[] boxed = new Integer[unboxed.length];
        for (int i = 0; i < boxed.length; i++) {
            boxed[i] = new Integer(unboxed[i]);
        }
        return boxed;
    }

}