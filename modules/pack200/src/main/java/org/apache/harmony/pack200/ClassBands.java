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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.harmony.pack200.bytecode.CPClass;
import org.apache.harmony.pack200.bytecode.ConstantValueAttribute;
import org.apache.harmony.pack200.bytecode.ExceptionsAttribute;

/**
 * 
 */
public class ClassBands extends BandSet {

    private int classAttrCount;

    private int[] classFieldCount;

    private long[] classFlags;

    private String[][] classInterfaces;

    private int[] classMethodCount;

    private String[] classSuper;

    private String[] classThis;

    private int[] codeHandlerCount;

    private int[] codeMaxNALocals;

    private int[] codeMaxStack;

    private int fieldAttrCount;

    private ArrayList[][] fieldAttributes;

    private String[][] fieldDescr;

    private long[][] fieldFlags;

    private int methodAttrCount;

    private ArrayList[][] methodAttributes;

    private String[][] methodDescr;

    private ExceptionsAttribute[][] methodExceptions;

    private long[][] methodFlags;

    private AttributeLayoutMap attrMap;

    private CpBands cpBands;

    private int codeAttrCount;

    /**
     * @param header
     */
    public ClassBands(Segment segment) {
        super(segment);
        this.attrMap = segment.getAttrDefinitionBands()
                .getAttributeDefinitionMap();
        this.cpBands = segment.getCpBands();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.harmony.pack200.BandSet#pack(java.io.OutputStream)
     */
    public void pack(OutputStream outputStream) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.harmony.pack200.BandSet#unpack(java.io.InputStream)
     */
    public void unpack(InputStream in) throws IOException, Pack200Exception {
        int classCount = header.getClassCount();
        classThis = parseReferences("class_this", in, Codec.DELTA5, classCount,
                cpBands.getCpClass());
        classSuper = parseReferences("class_super", in, Codec.DELTA5,
                classCount, cpBands.getCpClass());
        classInterfaces = new String[classCount][];
        int[] classInterfaceLengths = decodeBandInt("class_interface_count",
                in, Codec.DELTA5, classCount);
        // for (int i = 0; i < classCount; i++) {
        classInterfaces = parseReferences("class_interface", in, Codec.DELTA5,
                classInterfaceLengths, cpBands.getCpClass());
        // }
        classFieldCount = decodeBandInt("class_field_count", in, Codec.DELTA5,
                classCount);
        classMethodCount = decodeBandInt("class_method_count", in,
                Codec.DELTA5, classCount);
        parseFieldBands(in);
        parseMethodBands(in);
        parseClassAttrBands(in);
        parseCodeBands(in);

    }

    private void parseFieldBands(InputStream in) throws IOException,
            Pack200Exception {
        int classCount = header.getClassCount();
        SegmentOptions options = header.getOptions();
        fieldDescr = parseReferences("field_descr", in, Codec.DELTA5,
                classFieldCount, cpBands.getCpDescriptor());
        fieldFlags = parseFlags("field_flags", in, classCount, classFieldCount,
                Codec.UNSIGNED5, options.hasFieldFlagsHi());
        for (int i = 0; i < classCount; i++) {
            for (int j = 0; j < fieldFlags[i].length; j++) {
                long flag = fieldFlags[i][j];
                if ((flag & (1 << 16)) != 0)
                    fieldAttrCount++;
            }
        }
        if (fieldAttrCount > 0)
            throw new Error(
                    "There are attribute flags, and I don't know what to do with them");
        debug("unimplemented field_attr_indexes");
        debug("unimplemented field_attr_calls");
        AttributeLayout layout = attrMap.getAttributeLayout("ConstantValue",
                AttributeLayout.CONTEXT_FIELD);
        Codec codec = layout.getCodec();
        fieldAttributes = new ArrayList[classCount][];
        for (int i = 0; i < classCount; i++) {
            fieldAttributes[i] = new ArrayList[fieldFlags[i].length];
            for (int j = 0; j < fieldFlags[i].length; j++) {
                fieldAttributes[i][j] = new ArrayList();
                long flag = fieldFlags[i][j];
                if (layout.matches(flag)) {
                    // we've got a value to read
                    long result = codec.decode(in);
                    String desc = fieldDescr[i][j];
                    int colon = desc.indexOf(':');
                    // String name = desc.substring(0, colon);
                    String type = desc.substring(colon + 1);
                    // TODO Got to get better at this ... in any case, it should
                    // be e.g. KIB or KIH
                    if (type.equals("B") || type.equals("H"))
                        type = "I";
                    Object value = layout.getValue(result, type, cpBands
                            .getConstantPool());
                    fieldAttributes[i][j]
                            .add(new ConstantValueAttribute(value));
                    debug("Processed value " + value + " for ConstantValue");
                }
            }
        }

        layout = attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_SIGNATURE,
                AttributeLayout.CONTEXT_FIELD);
        codec = layout.getCodec();
//        fieldAttributes = new ArrayList[classCount][];
        for (int i = 0; i < classCount; i++) {
//            fieldAttributes[i] = new ArrayList[fieldFlags[i].length];
            for (int j = 0; j < fieldFlags[i].length; j++) {
//                fieldAttributes[i][j] = new ArrayList();
                long flag = fieldFlags[i][j];
                if (layout.matches(flag)) {
                    // we've got a value to read
                    long result = codec.decode(in);
//                    String desc = fieldDescr[i][j];
//                    int colon = desc.indexOf(':');
//                    // String name = desc.substring(0, colon);
//                    String type = desc.substring(colon + 1);
//                    // TODO Got to get better at this ... in any case, it should
//                    // be e.g. KIB or KIH
//                    if (type.equals("B") || type.equals("H"))
//                        type = "I";
//                    Object value = layout.getValue(result, type, cpBands
//                            .getConstantPool());
//                    fieldAttributes[i][j]
//                            .add(new ConstantValueAttribute(value));
                    debug("Found a signature attribute: " + result);
                }
            }
        }
//        debug("unimplemented field_Signature_RS");
        parseFieldMetadataBands();
    }

    private void parseMethodBands(InputStream in) throws IOException,
            Pack200Exception {
        int classCount = header.getClassCount();
        SegmentOptions options = header.getOptions();
        methodDescr = parseReferences("method_descr", in, Codec.MDELTA5,
                classMethodCount, cpBands.getCpDescriptor());
        methodFlags = parseFlags("method_flags", in, classCount,
                classMethodCount, Codec.UNSIGNED5, options.hasMethodFlagsHi());
        for (int i = 0; i < classCount; i++) {
            for (int j = 0; j < methodFlags[i].length; j++) {
                long flag = methodFlags[i][j];
                if ((flag & (1 << 16)) != 0)
                    methodAttrCount++;
            }
        }
        if (methodAttrCount > 0)
            throw new Error(
                    "There are method attribute flags, and I don't know what to do with them");
        debug("unimplemented method_attr_count");
        debug("unimplemented method_attr_indexes");
        debug("unimplemented method_attr_calls");
        // assign empty method attributes
        methodAttributes = new ArrayList[classCount][];
        for (int i = 0; i < classCount; i++) {
            methodAttributes[i] = new ArrayList[methodFlags[i].length];
            for (int j = 0; j < methodFlags[i].length; j++) {
                methodAttributes[i][j] = new ArrayList();
            }
        }
        parseAttributeMethodExceptions(in);
        parseAttributeMethodSignature(in);
        parseMethodMetadataBands();
    }

    /**
     * @param in
     * @throws Pack200Exception
     * @throws IOException
     */
    private void parseAttributeMethodExceptions(InputStream in)
            throws Pack200Exception, IOException {
        // TODO Should refactor this stuff into the layout somehow
        int classCount = header.getClassCount();
        AttributeLayout layout = attrMap.getAttributeLayout("Exceptions",
                AttributeLayout.CONTEXT_METHOD);
        Codec codec = layout.getCodec();
        methodExceptions = new ExceptionsAttribute[classCount][];
        int[][] numExceptions = new int[classCount][];
        for (int i = 0; i < classCount; i++) {
            numExceptions[i] = new int[methodFlags[i].length];
            for (int j = 0; j < methodFlags[i].length; j++) {
                long flag = methodFlags[i][j];
                if (layout.matches(flag)) {
                    numExceptions[i][j] = (int) codec.decode(in);
                }
            }
        }
        for (int i = 0; i < classCount; i++) {
            methodExceptions[i] = new ExceptionsAttribute[methodFlags[i].length];
            for (int j = 0; j < methodFlags[i].length; j++) {
                long flag = methodFlags[i][j];
                int n = numExceptions[i][j];
                if (n > 0) {
                    CPClass[] exceptions = new CPClass[n];
                    if (layout.matches(flag)) {
                        for (int k = 0; k < n; k++) {
                            long result = codec.decode(in);
                            exceptions[k] = new CPClass(
                                    cpBands.getCpClass()[(int) result]);
                        }
                    }
                    methodExceptions[i][j] = new ExceptionsAttribute(exceptions);
                    methodAttributes[i][j].add(methodExceptions[i][j]);
                }
            }
        }
    }

    /**
     * @param name
     * @param flags
     * @throws Pack200Exception
     */
    private void parseAttributeUnknown(String name, int context, long[][] flags)
            throws Pack200Exception {
        debug("Parsing unknown attributes for " + name);
        AttributeLayout layout = attrMap.getAttributeLayout(name, context);
        int count = SegmentUtils.countMatches(flags, layout);
        if (count > 0)
            throw new Error("We've got data for " + name
                    + " and we don't know what to do with it (yet)");
    }

    /**
     * @param in
     * 
     */
    private void parseAttributeMethodSignature(InputStream in)
            throws Pack200Exception, IOException {
        parseAttributeUnknown(AttributeLayout.ATTRIBUTE_SIGNATURE,
                AttributeLayout.CONTEXT_METHOD, methodFlags);
    }

    private void parseClassAttrBands(InputStream in) throws IOException,
            Pack200Exception {
        int classCount = header.getClassCount();
        SegmentOptions options = header.getOptions();
        classFlags = parseFlags("class_flags", in, classCount, Codec.UNSIGNED5,
                options.hasClassFlagsHi());
        for (int i = 0; i < classCount; i++) {
            long flag = classFlags[i];
            if ((flag & (1 << 16)) != 0)
                classAttrCount++;
        }
        if (classAttrCount > 0)
            throw new Error(
                    "There are attribute flags, and I don't know what to do with them");
        debug("unimplemented class_attr_count");
        debug("unimplemented class_attr_indexes");
        debug("unimplemented class_attr_calls");
        AttributeLayout layout = attrMap.getAttributeLayout(
                AttributeLayout.ATTRIBUTE_SOURCE_FILE,
                AttributeLayout.CONTEXT_CLASS);
        for (int i = 0; i < classCount; i++) {
            long flag = classFlags[i];
            if (layout.matches(flag)) {
                // we've got a value to read
                // TODO File this as a sourcefile attribute and don't generate
                // everything below
                long result = layout.getCodec().decode(in);
                Object value = layout.getValue(result, cpBands
                        .getConstantPool());
                debug("Processed value " + value + " for SourceFile");
            }
        }
        debug("unimplemented class_EnclosingMethod_RC");
        debug("unimplemented class_EnclosingMethod_RDN");
        debug("unimplemented class_Signature_RS");
        parseClassMetadataBands();
        debug("unimplemented class_InnerClasses_N");
        debug("unimplemented class_InnerClasses_RC");
        debug("unimplemented class_InnerClasses_F");
        debug("unimplemented class_InnerClasses_outer_RCN");
        debug("unimplemented class_InnerClasses_inner_RCN");
        debug("unimplemented class_file_version_minor_H");
        debug("unimplemented class_file_version_major_H");
    }

    private void parseCodeBands(InputStream in) throws Pack200Exception,
            IOException {
        AttributeLayout layout = attrMap.getAttributeLayout(
                AttributeLayout.ATTRIBUTE_CODE, AttributeLayout.CONTEXT_METHOD);

        int codeBands = SegmentUtils.countMatches(methodFlags, layout);
        int[] codeHeaders = decodeBandInt("code_headers", in, Codec.BYTE1,
                codeBands);
        int codeSpecialHeader = 0;
        for (int i = 0; i < codeBands; i++) {
            if (codeHeaders[i] == 0)
                codeSpecialHeader++;
        }
        int[] codeMaxStackSpecials = decodeBandInt("code_max_stack", in,
                Codec.UNSIGNED5, codeSpecialHeader);
        int[] codeMaxNALocalsSpecials = decodeBandInt("code_max_na_locals", in,
                Codec.UNSIGNED5, codeSpecialHeader);
        int[] codeHandlerCountSpecials = decodeBandInt("code_handler_count",
                in, Codec.UNSIGNED5, codeSpecialHeader);

        codeMaxStack = new int[codeBands];
        codeMaxNALocals = new int[codeBands];
        codeHandlerCount = new int[codeBands];
        int special = 0;
        for (int i = 0; i < codeBands; i++) {
            int header = 0xff & codeHeaders[i];
            if (header < 0) {
                throw new IllegalStateException("Shouldn't get here");
            } else if (header == 0) {
                codeMaxStack[i] = codeMaxStackSpecials[special];
                codeMaxNALocals[i] = codeMaxNALocalsSpecials[special];
                codeHandlerCount[i] = codeHandlerCountSpecials[special];
                special++;
            } else if (header <= 144) {
                codeMaxStack[i] = (header - 1) % 12;
                codeMaxNALocals[i] = (header - 1) / 12;
                codeHandlerCount[i] = 0;
            } else if (header <= 208) {
                codeMaxStack[i] = (header - 145) % 8;
                codeMaxNALocals[i] = (header - 145) / 8;
                codeHandlerCount[i] = 1;
            } else if (header <= 255) {
                codeMaxStack[i] = (header - 209) % 7;
                codeMaxNALocals[i] = (header - 209) / 7;
                codeHandlerCount[i] = 2;
            } else {
                throw new IllegalStateException("Shouldn't get here either");
            }
        }
        int codeFlagsCount = segment.getSegmentHeader().getOptions().hasAllCodeFlags() ?
                    codeBands : codeSpecialHeader;
        parseCodeAttrBands(in, codeFlagsCount);
    }

    private void parseCodeAttrBands(InputStream in, int codeFlagsCount) throws IOException, Pack200Exception {
        long[] codeFlags = parseFlags("code_flags", in, codeFlagsCount, Codec.UNSIGNED5, segment.getSegmentHeader().getOptions().hasCodeFlagsHi());
        for (int i = 0; i < codeFlagsCount; i++) {
            long flag = codeFlags[i];
            if ((flag & (1 << 16)) != 0)
                codeAttrCount++;
        }
        if (codeAttrCount > 0)
            throw new Error(
                    "There are attribute flags, and I don't know what to do with them");
        debug("unimplemented code_attr_count");
        debug("unimplemented code_attr_indexes");
        debug("unimplemented code_attr_calls");
        
        int lineNumberTableCount = SegmentUtils.countMatches(codeFlags, attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_LINE_NUMBER_TABLE, AttributeLayout.CONTEXT_CODE));
        int[] lineNumberTableN = decodeBandInt("code_LineNumberTable_N", in, Codec.UNSIGNED5, lineNumberTableCount);
        int[][] lineNumberTableBciP = decodeBandInt("code_LineNumberTable_bci_P", in, Codec.BCI5, lineNumberTableN);
        int[][] lineNumberTableLine = decodeBandInt("code_LineNumberTable_line", in, Codec.UNSIGNED5, lineNumberTableN);
        String[] types = { AttributeLayout.ATTRIBUTE_LOCAL_VARIABLE_TABLE, AttributeLayout.ATTRIBUTE_LOCAL_VARIABLE_TYPE_TABLE };
        for (int i = 0; i < types.length; i++) {
            String type = types[i];
            int lengthNBand = SegmentUtils.countMatches(codeFlags, attrMap.getAttributeLayout(type, AttributeLayout.CONTEXT_CODE));
            int[] nBand = decodeBandInt("code_" + type + "_N", in, Codec.UNSIGNED5, lengthNBand);
            int[][] bciP = decodeBandInt("code_" + type + "_bci_P", in, Codec.BCI5, nBand);
            int[][] spanO = decodeBandInt("code_" + type + "_span_O", in, Codec.BRANCH5, nBand);
            String[][] nameRU = parseReferences("code_" + type + "_name_RU", in, Codec.UNSIGNED5, nBand, cpBands.getCpUTF8());
            String[][] typeRS = parseReferences("code_" + type + "_type_RS", in, Codec.UNSIGNED5, nBand, cpBands.getCpSignature());
            int[][] slot = decodeBandInt("code_" + type + "_slot", in, Codec.UNSIGNED5, nBand);
        }
    }



    private void parseFieldMetadataBands() throws Pack200Exception {
        String[] RxA = new String[] { "RVA", "RIA" };
        
        AttributeLayout rvaLayout = attrMap.getAttributeLayout(
                AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_ANNOTATIONS,
                AttributeLayout.CONTEXT_FIELD);
        AttributeLayout riaLayout = attrMap.getAttributeLayout(
                AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_ANNOTATIONS,
                AttributeLayout.CONTEXT_FIELD);

        int rvaCount = SegmentUtils.countMatches(fieldFlags, rvaLayout);
        int riaCount = SegmentUtils.countMatches(fieldFlags, riaLayout);
        if (rvaCount > 0 || riaCount > 0) {
            throw new Error("Field metadata not handled");
        }

        // AttributeLayout layout =
        // map.get(RuntimeVisibleAnnotations,class/field/method as int)
        // foreachheader ...
        // if layout.matches(header[n] or whatever)
        String contextName = "field";
        for (int i = 0; i < RxA.length; i++) {
            String rxa = RxA[i];
            if (rxa.indexOf("P") >= 0) {
                debug("unimplemented " + contextName + "_" + rxa + "_param_NB");
            }
            if (!rxa.equals("AD")) {
                debug("unimplemented " + contextName + "_" + rxa + "_anno_N");
                debug("unimplemented " + contextName + "_" + rxa + "_type_RS");
                debug("unimplemented " + contextName + "_" + rxa + "_pair_N");
                debug("unimplemented " + contextName + "_" + rxa + "_name_RU");
            }
            debug("unimplemented " + contextName + "_" + rxa + "_T");
            debug("unimplemented " + contextName + "_" + rxa + "_caseI_KI");
            debug("unimplemented " + contextName + "_" + rxa + "_caseD_KD");
            debug("unimplemented " + contextName + "_" + rxa + "_caseF_KF");
            debug("unimplemented " + contextName + "_" + rxa + "_caseJ_KJ");
            debug("unimplemented " + contextName + "_" + rxa + "_casec_RS");
            debug("unimplemented " + contextName + "_" + rxa + "_caseet_RS");
            debug("unimplemented " + contextName + "_" + rxa + "_caseec_RU");
            debug("unimplemented " + contextName + "_" + rxa + "_cases_RU");
            debug("unimplemented " + contextName + "_" + rxa + "_casearray_N");
            debug("unimplemented " + contextName + "_" + rxa + "_nesttype_RS");
            debug("unimplemented " + contextName + "_" + rxa + "_nestpair_N");
            debug("unimplemented " + contextName + "_" + rxa + "_nestname_RU");
        }
    }

    private void parseMethodMetadataBands() throws Pack200Exception {
        String[] RxA = new String[] { "RVA", "RIA", "RVPA", "RIPA", "AD" };
        int[] rxaCounts = new int[] { 0, 0, 0, 0, 0 };
        AttributeLayout rvaLayout = attrMap.getAttributeLayout(
                AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_ANNOTATIONS,
                AttributeLayout.CONTEXT_METHOD);
        AttributeLayout riaLayout = attrMap.getAttributeLayout(
                AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_ANNOTATIONS,
                AttributeLayout.CONTEXT_METHOD);
        AttributeLayout rvpaLayout = attrMap
                .getAttributeLayout(
                        AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS,
                        AttributeLayout.CONTEXT_METHOD);
        AttributeLayout ripaLayout = attrMap
                .getAttributeLayout(
                        AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS,
                        AttributeLayout.CONTEXT_METHOD);
        AttributeLayout adLayout = attrMap.getAttributeLayout(
                AttributeLayout.ATTRIBUTE_ANNOTATION_DEFAULT,
                AttributeLayout.CONTEXT_METHOD);
        AttributeLayout[] rxaLayouts = new AttributeLayout[] { rvaLayout,
                riaLayout, rvpaLayout, ripaLayout, adLayout };

        for (int i = 0; i < rxaLayouts.length; i++) {
            rxaCounts[i] = SegmentUtils.countMatches(methodFlags, rxaLayouts[i]);
            if (rxaCounts[i] > 0) {
                throw new Error("Found method metadata");
            }
        }

        // AttributeLayout layout =
        // map.get(RuntimeVisibleAnnotations,class/field/method as int)
        // foreachheader ...
        // if layout.matches(header[n] or whatever)
        String contextName = "method";
        for (int i = 0; i < RxA.length; i++) {
            String rxa = RxA[i];
            if (rxa.indexOf("P") >= 0) {
                debug("unimplemented " + contextName + "_" + rxa + "_param_NB");
            }
            if (!rxa.equals("AD")) {
                debug("unimplemented " + contextName + "_" + rxa + "_anno_N");
                debug("unimplemented " + contextName + "_" + rxa + "_type_RS");
                debug("unimplemented " + contextName + "_" + rxa + "_pair_N");
                debug("unimplemented " + contextName + "_" + rxa + "_name_RU");
            }
            debug("unimplemented " + contextName + "_" + rxa + "_T");
            debug("unimplemented " + contextName + "_" + rxa + "_caseI_KI");
            debug("unimplemented " + contextName + "_" + rxa + "_caseD_KD");
            debug("unimplemented " + contextName + "_" + rxa + "_caseF_KF");
            debug("unimplemented " + contextName + "_" + rxa + "_caseJ_KJ");
            debug("unimplemented " + contextName + "_" + rxa + "_casec_RS");
            debug("unimplemented " + contextName + "_" + rxa + "_caseet_RS");
            debug("unimplemented " + contextName + "_" + rxa + "_caseec_RU");
            debug("unimplemented " + contextName + "_" + rxa + "_cases_RU");
            debug("unimplemented " + contextName + "_" + rxa + "_casearray_N");
            debug("unimplemented " + contextName + "_" + rxa + "_nesttype_RS");
            debug("unimplemented " + contextName + "_" + rxa + "_nestpair_N");
            debug("unimplemented " + contextName + "_" + rxa + "_nestname_RU");
        }
    }

    private void parseClassMetadataBands() throws Pack200Exception {
        String[] RxA = new String[] { "RVA", "RIA" };

        AttributeLayout rvaLayout = attrMap.getAttributeLayout(
                AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_ANNOTATIONS,
                AttributeLayout.CONTEXT_CLASS);
        AttributeLayout riaLayout = attrMap.getAttributeLayout(
                AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_ANNOTATIONS,
                AttributeLayout.CONTEXT_CLASS);
        int rvaCount = SegmentUtils.countMatches(classFlags, rvaLayout);
        int riaCount = SegmentUtils.countMatches(classFlags, riaLayout);
        if (rvaCount > 0 || riaCount > 0) {
            throw new Error("Class metadata not handled");
        }
        // AttributeLayout layout =
        // map.get(RuntimeVisibleAnnotations,class/field/method as int)
        // foreachheader ...
        // if layout.matches(header[n] or whatever)
        String contextName = "class";
        for (int i = 0; i < RxA.length; i++) {
            String rxa = RxA[i];
            if (rxa.indexOf("P") >= 0) {
                debug("unimplemented " + contextName + "_" + rxa + "_param_NB");
            }
            if (!rxa.equals("AD")) {
                debug("unimplemented " + contextName + "_" + rxa + "_anno_N");
                debug("unimplemented " + contextName + "_" + rxa + "_type_RS");
                debug("unimplemented " + contextName + "_" + rxa + "_pair_N");
                debug("unimplemented " + contextName + "_" + rxa + "_name_RU");
            }
            debug("unimplemented " + contextName + "_" + rxa + "_T");
            debug("unimplemented " + contextName + "_" + rxa + "_caseI_KI");
            debug("unimplemented " + contextName + "_" + rxa + "_caseD_KD");
            debug("unimplemented " + contextName + "_" + rxa + "_caseF_KF");
            debug("unimplemented " + contextName + "_" + rxa + "_caseJ_KJ");
            debug("unimplemented " + contextName + "_" + rxa + "_casec_RS");
            debug("unimplemented " + contextName + "_" + rxa + "_caseet_RS");
            debug("unimplemented " + contextName + "_" + rxa + "_caseec_RU");
            debug("unimplemented " + contextName + "_" + rxa + "_cases_RU");
            debug("unimplemented " + contextName + "_" + rxa + "_casearray_N");
            debug("unimplemented " + contextName + "_" + rxa + "_nesttype_RS");
            debug("unimplemented " + contextName + "_" + rxa + "_nestpair_N");
            debug("unimplemented " + contextName + "_" + rxa + "_nestname_RU");
        }
    }

    public int getClassAttrCount() {
        return classAttrCount;
    }

    public int[] getClassFieldCount() {
        return classFieldCount;
    }

    public long[] getClassFlags() {
        return classFlags;
    }

    public String[][] getClassInterfaces() {
        return classInterfaces;
    }

    public int[] getClassMethodCount() {
        return classMethodCount;
    }

    public String[] getClassSuper() {
        return classSuper;
    }

    public String[] getClassThis() {
        return classThis;
    }

    public int[] getCodeMaxNALocals() {
        return codeMaxNALocals;
    }

    public int[] getCodeMaxStack() {
        return codeMaxStack;
    }

    public int getFieldAttrCount() {
        return fieldAttrCount;
    }

    public ArrayList[][] getFieldAttributes() {
        return fieldAttributes;
    }

    public String[][] getFieldDescr() {
        return fieldDescr;
    }

    public long[][] getFieldFlags() {
        return fieldFlags;
    }

    public int getMethodAttrCount() {
        return methodAttrCount;
    }

    public ArrayList[][] getMethodAttributes() {
        return methodAttributes;
    }

    public String[][] getMethodDescr() {
        return methodDescr;
    }

    public ExceptionsAttribute[][] getMethodExceptions() {
        return methodExceptions;
    }

    public long[][] getMethodFlags() {
        return methodFlags;
    }

}
