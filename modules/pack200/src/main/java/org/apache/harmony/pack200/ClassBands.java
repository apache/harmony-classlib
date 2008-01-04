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
import java.util.Iterator;

import org.apache.harmony.pack200.IcBands.ICTuple;
import org.apache.harmony.pack200.bytecode.Attribute;
import org.apache.harmony.pack200.bytecode.CPClass;
import org.apache.harmony.pack200.bytecode.CPUTF8;
import org.apache.harmony.pack200.bytecode.ClassConstantPool;
import org.apache.harmony.pack200.bytecode.ConstantValueAttribute;
import org.apache.harmony.pack200.bytecode.ExceptionsAttribute;
import org.apache.harmony.pack200.bytecode.LineNumberTableAttribute;
import org.apache.harmony.pack200.bytecode.LocalVariableTableAttribute;
import org.apache.harmony.pack200.bytecode.LocalVariableTypeTableAttribute;
import org.apache.harmony.pack200.bytecode.SignatureAttribute;
import org.apache.harmony.pack200.bytecode.SourceFileAttribute;

/**
 * 
 */
public class ClassBands extends BandSet {

    private int[] classFieldCount;

    private long[] classFlags;

    private String[][] classInterfaces;

    private int[] classMethodCount;

    private String[] classSuper;

    private String[] classThis;

    private ArrayList[] classAttributes;

    private int[] classVersionMajor;

    private int[] classVersionMinor;

    private IcBands.ICTuple[][] icLocal;

    private ArrayList[] codeAttributes;

    private int[] codeHandlerCount;

    private int[] codeMaxNALocals;

    private int[] codeMaxStack;

    private ArrayList[][] fieldAttributes;

    private String[][] fieldDescr;

    private long[][] fieldFlags;

    private ArrayList[][] methodAttributes;

    private String[][] methodDescr;

    private long[][] methodFlags;

    private AttributeLayoutMap attrMap;

    private CpBands cpBands;

    private SegmentOptions options;

    private int classCount;

    private int[] methodAttrCalls;

    /**
     * @param segment
     */
    public ClassBands(Segment segment) {
        super(segment);
        this.attrMap = segment.getAttrDefinitionBands()
                .getAttributeDefinitionMap();
        this.cpBands = segment.getCpBands();
        this.classCount = header.getClassCount();
        this.options = header.getOptions();

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
        int[] classInterfaceLengths = decodeBandInt("class_interface_count",
                in, Codec.DELTA5, classCount);
        classInterfaces = parseReferences("class_interface", in, Codec.DELTA5,
                classInterfaceLengths, cpBands.getCpClass());
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
        fieldDescr = parseReferences("field_descr", in, Codec.DELTA5,
                classFieldCount, cpBands.getCpDescriptor());
        fieldFlags = parseFlags("field_flags", in, classFieldCount,
                Codec.UNSIGNED5, options.hasFieldFlagsHi());
        int fieldAttrCount = SegmentUtils.countBit16(fieldFlags);
        int[] fieldAttrCounts = decodeBandInt("field_attr_count", in,
                Codec.UNSIGNED5, fieldAttrCount);
        int[][] fieldAttrIndexes = decodeBandInt("field_attr_indexes", in,
                Codec.UNSIGNED5, fieldAttrCounts);
        int callCount = getCallCount(fieldAttrIndexes, fieldFlags,
                AttributeLayout.CONTEXT_FIELD);
        int[] fieldAttrCalls = decodeBandInt("field_attr_calls", in,
                Codec.UNSIGNED5, callCount);

        AttributeLayout constantValueLayout = attrMap.getAttributeLayout(
                "ConstantValue", AttributeLayout.CONTEXT_FIELD);
        int constantCount = SegmentUtils.countMatches(fieldFlags,
                constantValueLayout);
        int[] field_constantValue_KQ = decodeBandInt("field_ConstantValue_KQ",
                in, Codec.UNSIGNED5, constantCount);
        int constantValueIndex = 0;

        AttributeLayout signatureLayout = attrMap.getAttributeLayout(
                AttributeLayout.ATTRIBUTE_SIGNATURE,
                AttributeLayout.CONTEXT_FIELD);
        int signatureCount = SegmentUtils.countMatches(fieldFlags,
                signatureLayout);
        int[] fieldSignatureRS = decodeBandInt("field_Signature_RS", in,
                Codec.UNSIGNED5, signatureCount);
        int signatureIndex = 0;

        fieldAttributes = new ArrayList[classCount][];
        for (int i = 0; i < classCount; i++) {
            fieldAttributes[i] = new ArrayList[fieldFlags[i].length];
            for (int j = 0; j < fieldFlags[i].length; j++) {
                fieldAttributes[i][j] = new ArrayList();
                long flag = fieldFlags[i][j];
                if (constantValueLayout.matches(flag)) {
                    // we've got a value to read
                    long result = field_constantValue_KQ[constantValueIndex];
                    String desc = fieldDescr[i][j];
                    int colon = desc.indexOf(':');
                    String type = desc.substring(colon + 1);
                    if (type.equals("B") || type.equals("S")
                            || type.equals("C") || type.equals("Z"))
                        type = "I";
                    Object value = constantValueLayout.getValue(result, type,
                            cpBands.getConstantPool());
                    fieldAttributes[i][j]
                            .add(new ConstantValueAttribute(value));
                    constantValueIndex++;
                }
                if (signatureLayout.matches(flag)) {
                    // we've got a signature attribute
                    long result = fieldSignatureRS[signatureIndex];
                    String desc = fieldDescr[i][j];
                    int colon = desc.indexOf(':');
                    String type = desc.substring(colon + 1);
                    CPUTF8 value = new CPUTF8((String) signatureLayout.getValue(result, type,
                            cpBands.getConstantPool()), ClassConstantPool.DOMAIN_SIGNATUREASCIIZ);
                    fieldAttributes[i][j]
                            .add(new SignatureAttribute(value));
                    signatureIndex++;
                }
            }
        }
        parseFieldMetadataBands(in, fieldAttrCalls);

        // TODO: Parse other attribute bands
    }

    private void parseMethodBands(InputStream in) throws IOException,
            Pack200Exception {
        methodDescr = parseReferences("method_descr", in, Codec.MDELTA5,
                classMethodCount, cpBands.getCpDescriptor());
        methodFlags = parseFlags("method_flags", in, classMethodCount,
                Codec.UNSIGNED5, options.hasMethodFlagsHi());

        int methodAttrCount = SegmentUtils.countBit16(methodFlags);
        int[] methodAttrCounts = decodeBandInt("method_attr_count", in,
                Codec.UNSIGNED5, methodAttrCount);
        int[][] methodAttrIndexes = decodeBandInt("method_attr_indexes", in,
                Codec.UNSIGNED5, methodAttrCounts);
        int callCount = getCallCount(methodAttrIndexes, methodFlags,
                AttributeLayout.CONTEXT_METHOD);
        methodAttrCalls = decodeBandInt("code_attr_calls", in, Codec.UNSIGNED5,
                callCount);

        // assign empty method attributes
        methodAttributes = new ArrayList[classCount][];
        for (int i = 0; i < classCount; i++) {
            methodAttributes[i] = new ArrayList[methodFlags[i].length];
            for (int j = 0; j < methodFlags[i].length; j++) {
                methodAttributes[i][j] = new ArrayList();
            }
        }
        parseAttributeMethodExceptions(in);
        parseAttributeMethodSigntaure(in);
        parseMethodMetadataBands(in, methodAttrCalls);

        // TODO: Parse other attribute bands
    }

    private int getCallCount(int[][] methodAttrIndexes, long[][] flags,
            int context) throws Pack200Exception {
        int callCount = 0;
        for (int i = 0; i < methodAttrIndexes.length; i++) {
            for (int j = 0; j < methodAttrIndexes[i].length; j++) {
                int index = methodAttrIndexes[i][j];
                AttributeLayout layout = attrMap.getAttributeLayout(index,
                        context);
                callCount += layout.numBackwardsCallables();
            }
        }
        int layoutsUsed = 0;
        for (int i = 0; i < flags.length; i++) {
            for (int j = 0; j < flags[i].length; j++) {
                layoutsUsed |= flags[i][j];
            }
        }
        for (int i = 0; i < 26; i++) {
            if ((layoutsUsed & 1 << i) != 0) {
                AttributeLayout layout = attrMap.getAttributeLayout(i, context);
                callCount += layout.numBackwardsCallables();
            }
        }
        return callCount;
    }

    private void parseAttributeMethodSigntaure(InputStream in)
            throws IOException, Pack200Exception {
        AttributeLayout layout = attrMap.getAttributeLayout(
                AttributeLayout.ATTRIBUTE_SIGNATURE,
                AttributeLayout.CONTEXT_METHOD);
        int count = SegmentUtils.countMatches(methodFlags, layout);
        long[] methodSignatureRS = decodeBandLong("method_signature_RS", in,
                Codec.UNSIGNED5, count);
        int index = 0;
        for (int i = 0; i < methodAttributes.length; i++) {
            for (int j = 0; j < methodAttributes[i].length; j++) {
                long flag = methodFlags[i][j];
                if (layout.matches(flag)) {
                    // we've got a signature attribute
                    long result = methodSignatureRS[index];
                    String desc = methodDescr[i][j];
                    int colon = desc.indexOf(':');
                    String type = desc.substring(colon + 1);
                    // TODO Got to get better at this ... in any case, it should
                    // be e.g. KIB or KIH
                    if (type.equals("B") || type.equals("H"))
                        type = "I";
                    Object value = layout.getValue(result, type, cpBands
                            .getConstantPool());
                    methodAttributes[i][j]
                            .add(new ConstantValueAttribute(value));
                    index++;
                }
            }
        }
    }

    /**
     * @param in
     * @throws Pack200Exception
     * @throws IOException
     */
    private void parseAttributeMethodExceptions(InputStream in)
            throws Pack200Exception, IOException {
        AttributeLayout layout = attrMap.getAttributeLayout("Exceptions",
                AttributeLayout.CONTEXT_METHOD);
        int count = SegmentUtils.countMatches(methodFlags, layout);
        int[] numExceptions = decodeBandInt("method_Exceptions_n", in,
                Codec.UNSIGNED5, count);
        String[][] methodExceptionsRS = parseReferences("method_Exceptions_RC",
                in, Codec.UNSIGNED5, numExceptions, cpBands.getCpClass());
        int index = 0;
        for (int i = 0; i < classCount; i++) {
            for (int j = 0; j < methodFlags[i].length; j++) {
                long flag = methodFlags[i][j];
                if (layout.matches(flag)) {
                    int n = numExceptions[index];
                    String[] exceptions = methodExceptionsRS[index];
                    CPClass[] exceptionClasses = new CPClass[n];
                    for (int k = 0; k < n; k++) {
                        exceptionClasses[k] = new CPClass(exceptions[k]);
                    }
                    methodAttributes[i][j].add(new ExceptionsAttribute(
                            exceptionClasses));
                    index++;
                }
            }
        }
    }

    private void parseClassAttrBands(InputStream in) throws IOException,
            Pack200Exception {
        String[] cpUTF8 = cpBands.getCpUTF8();
        String[] cpClass = cpBands.getCpClass();

        // Prepare empty attribute lists
        classAttributes = new ArrayList[classCount];
        for (int i = 0; i < classCount; i++) {
            classAttributes[i] = new ArrayList();
        }
        
        classFlags = parseFlags("class_flags", in, classCount, Codec.UNSIGNED5,
                options.hasClassFlagsHi());
        int classAttrCount = SegmentUtils.countBit16(classFlags);
        int[] classAttrCounts = decodeBandInt("class_attr_count", in,
                Codec.UNSIGNED5, classAttrCount);
        int[][] classAttrIndexes = decodeBandInt("class_attr_indexes", in,
                Codec.UNSIGNED5, classAttrCounts);
        int callCount = getCallCount(classAttrIndexes, new long[][] {classFlags},
                AttributeLayout.CONTEXT_CLASS);
        int[] classAttrCalls = decodeBandInt("class_attr_calls", in,
                Codec.UNSIGNED5, callCount);

        AttributeLayout sourceFileLayout = attrMap.getAttributeLayout(
                AttributeLayout.ATTRIBUTE_SOURCE_FILE,
                AttributeLayout.CONTEXT_CLASS);
        int sourceFileCount = SegmentUtils.countMatches(classFlags,
                sourceFileLayout);
        int[] classSourceFile = decodeBandInt("class_SourceFile_RUN", in,
                Codec.UNSIGNED5, sourceFileCount);

        AttributeLayout enclosingMethodLayout = attrMap.getAttributeLayout(
                AttributeLayout.ATTRIBUTE_ENCLOSING_METHOD,
                AttributeLayout.CONTEXT_CLASS);
        int enclosingMethodCount = SegmentUtils.countMatches(classFlags,
                enclosingMethodLayout);
        int[] enclosingMethodRC = decodeBandInt("class_EnclosingMethod_RC", in,
                Codec.UNSIGNED5, enclosingMethodCount);
        int[] enclosingMethodRDN = decodeBandInt("class_EnclosingMethod_RDN",
                in, Codec.UNSIGNED5, enclosingMethodCount);

        AttributeLayout signatureLayout = attrMap.getAttributeLayout(
                AttributeLayout.ATTRIBUTE_SIGNATURE,
                AttributeLayout.CONTEXT_CLASS);
        int signatureCount = SegmentUtils.countMatches(classFlags,
                signatureLayout);
        int[] classSignature = decodeBandInt("class_Signature_RS", in,
                Codec.UNSIGNED5, signatureCount);

        parseClassMetadataBands(in, classAttrCalls);

        AttributeLayout innerClassLayout = attrMap.getAttributeLayout(
                AttributeLayout.ATTRIBUTE_INNER_CLASSES,
                AttributeLayout.CONTEXT_CLASS);
        int innerClassCount = SegmentUtils.countMatches(classFlags,
                innerClassLayout);
        int[] classInnerClassesN = decodeBandInt("class_InnerClasses_N", in,
                Codec.UNSIGNED5, innerClassCount);
        int[][] classInnerClassesRC = decodeBandInt("class_InnerClasses_RC",
                in, Codec.UNSIGNED5, classInnerClassesN);
        int[][] classInnerClassesF = decodeBandInt("class_InnerClasses_F", in,
                Codec.UNSIGNED5, classInnerClassesN);
        int flagsCount = 0;
        for (int i = 0; i < classInnerClassesF.length; i++) {
            for (int j = 0; j < classInnerClassesF[i].length; j++) {
                if (classInnerClassesF[i][j] != 0) {
                    flagsCount++;
                }
            }
        }
        int[] classInnerClassesOuterRCN = decodeBandInt(
                "class_InnerClasses_outer_RCN", in, Codec.UNSIGNED5, flagsCount);
        int[] classInnerClassesNameRUN = decodeBandInt(
                "class_InnerClasses_name_RUN", in, Codec.UNSIGNED5, flagsCount);

        AttributeLayout versionLayout = attrMap.getAttributeLayout(
                AttributeLayout.ATTRIBUTE_CLASS_FILE_VERSION,
                AttributeLayout.CONTEXT_CLASS);
        int versionCount = SegmentUtils.countMatches(classFlags, versionLayout);
        int[] classFileVersionMinorH = decodeBandInt(
                "class_file_version_minor_H", in, Codec.UNSIGNED5, versionCount);
        int[] classFileVersionMajorH = decodeBandInt(
                "class_file_version_major_H", in, Codec.UNSIGNED5, versionCount);
        if (versionCount > 0) {
            classVersionMajor = new int[classCount];
            classVersionMinor = new int[classCount];
        }
        int defaultVersionMajor = header.getDefaultClassMajorVersion();
        int defaultVersionMinor = header.getDefaultClassMinorVersion();

        // TODO: Parse other attribute bands

        // Now process the attribute bands we have parsed
        int sourceFileIndex = 0;
        int enclosingMethodIndex = 0;
        int signatureIndex = 0;
        int innerClassIndex = 0;
        int innerClassC2NIndex = 0;
        int versionIndex = 0;
        icLocal = new IcBands.ICTuple[classCount][];
        for (int i = 0; i < classCount; i++) {
            long flag = classFlags[i];

            if (sourceFileLayout.matches(flag)) {
                long result = classSourceFile[sourceFileIndex];
                String value = (String) sourceFileLayout.getValue(result,
                        cpBands.getConstantPool());
                if (value == null) {
                    // Remove package prefix
                    String className = classThis[i].substring(classThis[i]
                            .lastIndexOf('/') + 1);
                    className = className
                            .substring(className.lastIndexOf('.') + 1);

                    // Remove mangled nested class names
                    char[] chars = className.toCharArray();
                    int index = -1;
                    for (int j = 0; j < chars.length; j++) {
                        if (chars[j] <= 0x2D) {
                            index = j;
                            break;
                        }
                    }
                    if (index > -1) {
                        className = className.substring(0, index);
                    }
                    // Add .java to the end
                    value = className + ".java";
                }
                classAttributes[i].add(new SourceFileAttribute(value));
                sourceFileIndex++;
            }
            if (enclosingMethodLayout.matches(flag)) {
                // long result =
            }
            if (signatureLayout.matches(flag)) {
                long result = classSignature[signatureIndex];
                Object value = signatureLayout.getValue(result, cpBands
                        .getConstantPool());
                classAttributes[i].add(new ConstantValueAttribute(value));
                signatureIndex++;
            }
            if (innerClassLayout.matches(flag)) {
                // Just create the tuples for now because the attributes are
                // decided at the end when creating class constant pools
                icLocal[i] = new IcBands.ICTuple[classInnerClassesN[innerClassIndex]];
                for (int j = 0; j < icLocal[i].length; j++) {
                    IcBands.ICTuple icTuple = new IcBands.ICTuple();
                    icTuple.C = cpClass[classInnerClassesRC[innerClassIndex][j]];
                    icTuple.F = classInnerClassesF[innerClassIndex][j];
                    if (icTuple.F != 0) {
                        icTuple.C2 = cpClass[classInnerClassesOuterRCN[innerClassC2NIndex]];
                        icTuple.N = cpUTF8[classInnerClassesNameRUN[innerClassC2NIndex]];
                        innerClassC2NIndex++;
                    } else {
                        // Get from icBands
                        IcBands icBands = segment.getIcBands();
                        ICTuple[] icAll = icBands.getIcTuples();
                        for (int k = 0; k < icAll.length; k++) {
                            if (icAll[k].C.equals(icTuple.C)) {
                                icTuple.C2 = icAll[k].C2;
                                icTuple.N = icAll[k].N;
                                break;
                            }
                        }
                    }
                    icLocal[i][j] = icTuple;
                }
                innerClassIndex++;
            }
            if (versionLayout.matches(flag)) {
                classVersionMajor[i] = classFileVersionMajorH[versionIndex];
                classVersionMinor[i] = classFileVersionMinorH[versionIndex];
                versionIndex++;
            } else if (classVersionMajor != null) {
                // Fill in with defaults
                classVersionMajor[i] = defaultVersionMajor;
                classVersionMinor[i] = defaultVersionMinor;
            }
        }
    }

    private void parseCodeBands(InputStream in) throws Pack200Exception,
            IOException {
        AttributeLayout layout = attrMap.getAttributeLayout(
                AttributeLayout.ATTRIBUTE_CODE, AttributeLayout.CONTEXT_METHOD);

        int codeCount = SegmentUtils.countMatches(methodFlags, layout);
        int[] codeHeaders = decodeBandInt("code_headers", in, Codec.BYTE1,
                codeCount);
        int codeSpecialHeader = 0;
        for (int i = 0; i < codeCount; i++) {
            if (codeHeaders[i] == 0)
                codeSpecialHeader++;
        }
        int[] codeMaxStackSpecials = decodeBandInt("code_max_stack", in,
                Codec.UNSIGNED5, codeSpecialHeader);
        int[] codeMaxNALocalsSpecials = decodeBandInt("code_max_na_locals", in,
                Codec.UNSIGNED5, codeSpecialHeader);
        int[] codeHandlerCountSpecials = decodeBandInt("code_handler_count",
                in, Codec.UNSIGNED5, codeSpecialHeader);

        codeMaxStack = new int[codeCount];
        codeMaxNALocals = new int[codeCount];
        codeHandlerCount = new int[codeCount];
        int special = 0;
        for (int i = 0; i < codeCount; i++) {
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
        int sumCodeHandlerCount = 0;
        for (int i = 0; i < codeHandlerCount.length; i++) {
            sumCodeHandlerCount += codeHandlerCount[i];
        }
        int[] codeHandlerStartP = decodeBandInt("code_handler_start_P", in,
                Codec.BCI5, sumCodeHandlerCount);
        int[] codeHandlerEndPO = decodeBandInt("code_handler_end_PO", in,
                Codec.BRANCH5, sumCodeHandlerCount);
        int[] codeHandlerCatchPO = decodeBandInt("code_handler_catch_PO", in,
                Codec.BRANCH5, sumCodeHandlerCount);
        int[] codeHandlerClassRCN = decodeBandInt("code_handler_class_RCN", in,
                Codec.UNSIGNED5, sumCodeHandlerCount);

        int codeFlagsCount = segment.getSegmentHeader().getOptions()
                .hasAllCodeFlags() ? codeCount : codeSpecialHeader;

        codeAttributes = new ArrayList[codeFlagsCount];
        for (int i = 0; i < codeAttributes.length; i++) {
            codeAttributes[i] = new ArrayList();
        }
        parseCodeAttrBands(in, codeFlagsCount);
    }

    private void parseCodeAttrBands(InputStream in, int codeFlagsCount)
            throws IOException, Pack200Exception {
        long[] codeFlags = parseFlags("code_flags", in, codeFlagsCount,
                Codec.UNSIGNED5, segment.getSegmentHeader().getOptions()
                        .hasCodeFlagsHi());
        int codeAttrCount = SegmentUtils.countBit16(codeFlags);
        int[] codeAttrCounts = decodeBandInt("code_attr_count", in,
                Codec.UNSIGNED5, codeAttrCount);
        int[][] codeAttrIndexes = decodeBandInt("code_attr_indexes", in,
                Codec.UNSIGNED5, codeAttrCounts);
        int callCount = 0;
        for (int i = 0; i < codeAttrIndexes.length; i++) {
            for (int j = 0; j < codeAttrIndexes[i].length; j++) {
                int index = codeAttrIndexes[i][j];
                AttributeLayout layout = attrMap.getAttributeLayout(index,
                        AttributeLayout.CONTEXT_CODE);
                callCount += layout.numBackwardsCallables();
            }
        }
        int[] codeAttrCalls = decodeBandInt("code_attr_calls", in,
                Codec.UNSIGNED5, callCount);

        AttributeLayout lineNumberTableLayout = attrMap.getAttributeLayout(
                AttributeLayout.ATTRIBUTE_LINE_NUMBER_TABLE,
                AttributeLayout.CONTEXT_CODE);
        int lineNumberTableCount = SegmentUtils.countMatches(codeFlags,
                lineNumberTableLayout);
        int[] lineNumberTableN = decodeBandInt("code_LineNumberTable_N", in,
                Codec.UNSIGNED5, lineNumberTableCount);
        int[][] lineNumberTableBciP = decodeBandInt(
                "code_LineNumberTable_bci_P", in, Codec.BCI5, lineNumberTableN);
        int[][] lineNumberTableLine = decodeBandInt(
                "code_LineNumberTable_line", in, Codec.UNSIGNED5,
                lineNumberTableN);

        AttributeLayout localVariableTableLayout = attrMap.getAttributeLayout(
                AttributeLayout.ATTRIBUTE_LOCAL_VARIABLE_TABLE,
                AttributeLayout.CONTEXT_CODE);
        AttributeLayout localVariableTypeTableLayout = attrMap
                .getAttributeLayout(
                        AttributeLayout.ATTRIBUTE_LOCAL_VARIABLE_TYPE_TABLE,
                        AttributeLayout.CONTEXT_CODE);
        
        int lengthLocalVariableNBand = SegmentUtils.countMatches(codeFlags,
                localVariableTableLayout);
        int[] localVariableTableN = decodeBandInt("code_LocalVariableTable_N",
                in, Codec.UNSIGNED5, lengthLocalVariableNBand);
        int[][] localVariableTableBciP = decodeBandInt(
                "code_LocalVariableTable_bci_P", in, Codec.BCI5,
                localVariableTableN);
        int[][] localVariableTableSpanO = decodeBandInt(
                "code_LocalVariableTable_span_O", in, Codec.BRANCH5,
                localVariableTableN);
        CPUTF8[][] localVariableTableNameRU = stringsToCPUTF8(parseReferences(
                "code_LocalVariableTable_name_RU", in, Codec.UNSIGNED5,
                localVariableTableN, cpBands.getCpUTF8()));
        CPUTF8[][] localVariableTableTypeRS = stringsToCPUTF8(parseReferences(
                "code_LocalVariableTable_type_RS", in, Codec.UNSIGNED5,
                localVariableTableN, cpBands.getCpSignature()));
        int[][] localVariableTableSlot = decodeBandInt(
                "code_LocalVariableTable_slot", in, Codec.UNSIGNED5,
                localVariableTableN);

        // Fix up localVariableTableTypeRS - for some reason,
        // native signatures end up in DOMAINNORMALASCIIZ
        // while nonnatives end up in DOMAINSIGNATUREASCIIZ.
        // TODO: is this the right thing to do?
        for(int x=0; x < localVariableTableTypeRS.length; x++) {
            for(int y=0; y < localVariableTableTypeRS[x].length; y++) {
                CPUTF8 element = localVariableTableTypeRS[x][y];
                // TODO: come up with a better test for native vs nonnative signatures?
                if(element.underlyingString().length() > 2) {
                    element.setDomain(ClassConstantPool.DOMAIN_SIGNATUREASCIIZ);
                } else {
                    element.setDomain(ClassConstantPool.DOMAIN_NORMALASCIIZ);
                }
            }
        }
        
        int lengthLocalVariableTypeTableNBand = SegmentUtils.countMatches(
                codeFlags, localVariableTypeTableLayout);
        int[] localVariableTypeTableN = decodeBandInt(
                "code_LocalVariableTypeTable_N", in, Codec.UNSIGNED5,
                lengthLocalVariableTypeTableNBand);
        int[][] localVariableTypeTableBciP = decodeBandInt(
                "code_LocalVariableTypeTable_bci_P", in, Codec.BCI5,
                localVariableTypeTableN);
        int[][] localVariableTypeTableSpanO = decodeBandInt(
                "code_LocalVariableTypeTable_span_O", in, Codec.BRANCH5,
                localVariableTypeTableN);
        CPUTF8[][] localVariableTypeTableNameRU = stringsToCPUTF8(parseReferences(
                "code_LocalVariableTypeTable_name_RU", in, Codec.UNSIGNED5,
                localVariableTypeTableN, cpBands.getCpUTF8()));
        CPUTF8[][] localVariableTypeTableTypeRS = stringsToCPUTF8(parseReferences(
                "code_LocalVariableTypeTable_type_RS", in, Codec.UNSIGNED5,
                localVariableTypeTableN, cpBands.getCpSignature()));
        int[][] localVariableTypeTableSlot = decodeBandInt(
                "code_LocalVariableTypeTable_slot", in, Codec.UNSIGNED5,
                localVariableTypeTableN);

        int lineNumberIndex = 0;
        int lvtIndex = 0;
        int lvttIndex = 0;
        for (int i = 0; i < codeFlagsCount; i++) {
            if (lineNumberTableLayout.matches(codeFlags[i])) {
                LineNumberTableAttribute lnta = new LineNumberTableAttribute(
                        lineNumberTableN[lineNumberIndex],
                        lineNumberTableBciP[lineNumberIndex],
                        lineNumberTableLine[lineNumberIndex]);
                lineNumberIndex++;
                codeAttributes[i].add(lnta);
            }
            if (localVariableTableLayout.matches(codeFlags[i])) {
                LocalVariableTableAttribute lvta = new LocalVariableTableAttribute(
                        localVariableTableN[lvtIndex],
                        localVariableTableBciP[lvtIndex],
                        localVariableTableSpanO[lvtIndex],
                        localVariableTableNameRU[lvtIndex],
                        localVariableTableTypeRS[lvtIndex],
                        localVariableTableSlot[lvtIndex]);
                lvtIndex++;
                codeAttributes[i].add(lvta);
            }
            if (localVariableTypeTableLayout.matches(codeFlags[i])) {
                LocalVariableTypeTableAttribute lvtta = new LocalVariableTypeTableAttribute(
                        localVariableTypeTableN[lvttIndex],
                        localVariableTypeTableBciP[lvttIndex],
                        localVariableTypeTableSpanO[lvttIndex],
                        localVariableTypeTableNameRU[lvttIndex],
                        localVariableTypeTableTypeRS[lvttIndex],
                        localVariableTypeTableSlot[lvttIndex]);
                lvttIndex++;
                codeAttributes[i].add(lvtta);
            }
        }
        // TODO: Parse other attribute bands
    }

    private CPUTF8[][] stringsToCPUTF8(String[][] strings) {
        CPUTF8[][] cpUTF8s = new CPUTF8[strings.length][];
        for (int i = 0; i < strings.length; i++) {
            cpUTF8s[i] = new CPUTF8[strings[i].length];
            for (int j = 0; j < strings[i].length; j++) {
                cpUTF8s[i][j] = new CPUTF8(strings[i][j], ClassConstantPool.DOMAIN_NORMALASCIIZ);
            }
        }
        return cpUTF8s;
    }
    


    private CPUTF8[] stringsToCPUTF8(String[] strings) {
        CPUTF8[] cpUTF8s = new CPUTF8[strings.length];
        for (int i = 0; i < strings.length; i++) {
            cpUTF8s[i] = new CPUTF8(strings[i], ClassConstantPool.DOMAIN_UNDEFINED);
        }
        return cpUTF8s;
    }

    private void parseFieldMetadataBands(InputStream in, int[] fieldAttrCalls)
            throws Pack200Exception, IOException {
        String[] RxA = new String[] { "RVA", "RIA" };

        AttributeLayout rvaLayout = attrMap.getAttributeLayout(
                AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_ANNOTATIONS,
                AttributeLayout.CONTEXT_FIELD);
        AttributeLayout riaLayout = attrMap.getAttributeLayout(
                AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_ANNOTATIONS,
                AttributeLayout.CONTEXT_FIELD);

        int rvaCount = SegmentUtils.countMatches(fieldFlags, rvaLayout);
        int riaCount = SegmentUtils.countMatches(fieldFlags, riaLayout);
        int[] RxACount = new int[] { rvaCount, riaCount };
        int[] backwardsCalls = new int[] {0, 0};
        if(rvaCount > 0) {
            backwardsCalls[0] = fieldAttrCalls[0];
            if(riaCount > 0) {
                backwardsCalls[1] = fieldAttrCalls[1];
            }
        } else if (riaCount > 0) {
            backwardsCalls[1] = fieldAttrCalls[0];
        }
        MetadataBandGroup[] mb = parseMetadata(in, RxA, RxACount, backwardsCalls, "field");
        Iterator rvaAttributesIterator = mb[0].getAttributes().iterator();
        Iterator riaAttributesIterator = mb[1].getAttributes().iterator();
        for (int i = 0; i < fieldFlags.length; i++) {
            for (int j = 0; j < fieldFlags[i].length; j++) {                
                if(rvaLayout.matches(fieldFlags[i][j])) {
                    fieldAttributes[i][j].add(rvaAttributesIterator.next());
                }
                if(riaLayout.matches(fieldFlags[i][j])) {
                    fieldAttributes[i][j].add(riaAttributesIterator.next());
                }
            }
        }
    }

    private MetadataBandGroup[] parseMetadata(InputStream in, String[] RxA, int[] RxACount,
            int[] backwardsCallCounts, String contextName) throws IOException, Pack200Exception {
        MetadataBandGroup[] mbg = new MetadataBandGroup[RxA.length];
        for (int i = 0; i < RxA.length; i++) {
            mbg[i] = new MetadataBandGroup(RxA[i]);
            String rxa = RxA[i];
            if (rxa.indexOf("P") >= 0) {
                mbg[i].param_NB = decodeBandInt(contextName + "_" + rxa
                        + "_param_NB", in, Codec.BYTE1, RxACount[i]);
            }
            int pairCount = 0;
            if (!rxa.equals("AD")) {
                mbg[i].anno_N = decodeBandInt(contextName + "_" + rxa
                        + "_anno_N", in, Codec.UNSIGNED5, RxACount[i]);
                mbg[i].type_RS = stringsToCPUTF8(parseReferences(contextName + "_" + rxa
                        + "_type_RS", in, Codec.UNSIGNED5, mbg[i].anno_N, cpBands.getCpSignature()));
                mbg[i].pair_N = decodeBandInt(contextName + "_" + rxa
                        + "_pair_N", in, Codec.UNSIGNED5, mbg[i].anno_N);
                for (int j = 0; j < mbg[i].pair_N.length; j++) {
                    for (int k = 0; k < mbg[i].pair_N[j].length; k++) {
                        pairCount += mbg[i].pair_N[j][k];
                    }
                }
                
                mbg[i].name_RU = stringsToCPUTF8(parseReferences(contextName + "_" + rxa
                        + "_name_RU", in, Codec.UNSIGNED5, pairCount, cpBands.getCpUTF8()));
            }
            mbg[i].T = decodeBandInt(contextName + "_" + rxa + "_T", in,
                    Codec.BYTE1, pairCount + backwardsCallCounts[i]);
            int ICount = 0, DCount = 0, FCount = 0, JCount = 0, cCount = 0, eCount = 0, sCount = 0, arrayCount = 0, atCount = 0;
            for (int j = 0; j < mbg[i].T.length; j++) {
                char c = (char) mbg[i].T[j];
                switch (c) {
                case 'B':
                case 'C':
                case 'I':
                case 'S':
                case 'Z':
                    ICount++;
                    break;
                case 'D':
                    DCount++;
                    break;
                case 'F':
                    FCount++;
                    break;
                case 'J':
                    JCount++;
                    break;
                case 'c':
                    cCount++;
                    break;
                case 'e':
                    eCount++;
                    break;
                case 's':
                    sCount++;
                    break;
                case '[':
                    arrayCount++;
                    break;
                case '@':
                    atCount++;
                    break;
                }
            }
            mbg[i].caseI_KI = parseCPIntReferences(contextName + "_" + rxa
                    + "_caseI_KI", in, Codec.UNSIGNED5, ICount);
            mbg[i].caseD_KD = parseCPDoubleReferences(contextName + "_" + rxa
                    + "_caseD_KD", in, Codec.UNSIGNED5, DCount);
            mbg[i].caseF_KF = parseCPFloatReferences(contextName + "_" + rxa
                    + "_caseF_KF", in, Codec.UNSIGNED5, FCount);
            mbg[i].caseJ_KJ = parseCPLongReferences(contextName + "_" + rxa
                    + "_caseJ_KJ", in, Codec.UNSIGNED5, JCount);
            mbg[i].casec_RS = parseCPUTF8References(contextName + "_" + rxa
                    + "_casec_RS", in, Codec.UNSIGNED5, cCount);
            mbg[i].caseet_RS = parseReferences(contextName + "_" + rxa
                    + "_caseet_RS", in, Codec.UNSIGNED5, eCount, cpBands.getCpSignature());
            mbg[i].caseec_RU = parseReferences(contextName + "_" + rxa
                    + "_caseec_RU", in, Codec.UNSIGNED5, eCount, cpBands.getCpUTF8());
            mbg[i].cases_RU = parseCPUTF8References(contextName + "_" + rxa
                    + "_cases_RU", in, Codec.UNSIGNED5, sCount);
            mbg[i].casearray_N = decodeBandInt(contextName + "_" + rxa
                    + "_casearray_N", in, Codec.UNSIGNED5, arrayCount);
            mbg[i].nesttype_RS = parseCPUTF8References(contextName + "_" + rxa
                    + "_nesttype_RS", in, Codec.UNSIGNED5, atCount);
            mbg[i].nestpair_N = decodeBandInt(contextName + "_" + rxa
                    + "_nestpair_N", in, Codec.UNSIGNED5, atCount);
            int nestPairCount = 0;
            for (int j = 0; j < mbg[i].nestpair_N.length; j++) {
                nestPairCount += mbg[i].nestpair_N[j];
            }
            mbg[i].nestname_RU = parseCPUTF8References(contextName + "_" + rxa
                    + "_nestname_RU", in, Codec.UNSIGNED5, nestPairCount);
        }
        return mbg;
    }

    private void parseMethodMetadataBands(InputStream in, int[] methodAttrCalls)
            throws Pack200Exception, IOException {
        String[] RxA = new String[] { "RVA", "RIA", "RVPA", "RIPA", "AD" };
        int[] rxaCounts = new int[] { 0, 0, 0, 0, 0 };
        int[] backwardsCalls = new int[5];
        int methodAttrIndex = 0;
        for (int i = 0; i < backwardsCalls.length; i++) {
            if(rxaCounts[i] > 0) {
                backwardsCalls[i] = methodAttrCalls[methodAttrIndex];
                methodAttrIndex++;
            } else {
                backwardsCalls[i] = 0;
            }
        }
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
            rxaCounts[i] = SegmentUtils
                    .countMatches(methodFlags, rxaLayouts[i]);
        }
        MetadataBandGroup[] mbgs = parseMetadata(in, RxA, rxaCounts, backwardsCalls, "method");
        Iterator[] attributeIterators = new Iterator[RxA.length];
        for (int i = 0; i < mbgs.length; i++) {
            attributeIterators[i] = mbgs[i].getAttributes().iterator();
        }
        for (int i = 0; i < methodFlags.length; i++) {
            for (int j = 0; j < methodFlags[i].length; j++) {
                for (int k = 0; k < rxaLayouts.length; k++) {
                    if(rxaLayouts[k].matches(methodFlags[i][j])) {
                        methodAttributes[i][j].add(attributeIterators[k].next());
                    }
                }
            }
        }
    }

    private void parseClassMetadataBands(InputStream in, int[] classAttrCalls) throws Pack200Exception, IOException {
        String[] RxA = new String[] { "RVA", "RIA" };

        AttributeLayout rvaLayout = attrMap.getAttributeLayout(
                AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_ANNOTATIONS,
                AttributeLayout.CONTEXT_CLASS);
        AttributeLayout riaLayout = attrMap.getAttributeLayout(
                AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_ANNOTATIONS,
                AttributeLayout.CONTEXT_CLASS);
        int rvaCount = SegmentUtils.countMatches(classFlags, rvaLayout);
        int riaCount = SegmentUtils.countMatches(classFlags, riaLayout);
        int[] RxACount = new int[] { rvaCount, riaCount };
        int[] backwardsCalls = new int[] {0, 0};
        if(rvaCount > 0) {
            backwardsCalls[0] = classAttrCalls[0];
            if(riaCount > 0) {
                backwardsCalls[1] = classAttrCalls[1];
            }
        } else if (riaCount > 0) {
            backwardsCalls[1] = classAttrCalls[0];
        }
        MetadataBandGroup[] mbgs = parseMetadata(in, RxA, RxACount, backwardsCalls, "class");
        Iterator rvaAttributesIterator = mbgs[0].getAttributes().iterator();
        Iterator riaAttributesIterator = mbgs[1].getAttributes().iterator();
        for (int i = 0; i < classFlags.length; i++) {
            if(rvaLayout.matches(classFlags[i])) {
                classAttributes[i].add(rvaAttributesIterator.next());
            }
            if(riaLayout.matches(classFlags[i])) {
                classAttributes[i].add(riaAttributesIterator.next());
            }
        }
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

    public ArrayList[][] getFieldAttributes() {
        return fieldAttributes;
    }

    public String[][] getFieldDescr() {
        return fieldDescr;
    }

    public long[][] getFieldFlags() {
        return fieldFlags;
    }

    /**
     * Answer an ArrayList of ArrayLists which hold the the code attributes
     * corresponding to all classes in order.
     * 
     * If a class doesn't have any attributes, the corresponding element in this
     * list will be an empty ArrayList.
     * @return ArrayList
     */
    public ArrayList getOrderedCodeAttributes() {
        ArrayList orderedAttributeList = new ArrayList();
        for(int classIndex=0; classIndex < codeAttributes.length; classIndex++) {
            ArrayList currentAttributes = new ArrayList();
            for(int attributeIndex = 0; attributeIndex < codeAttributes[classIndex].size(); attributeIndex++) {
                Attribute attribute = (Attribute)codeAttributes[classIndex].get(attributeIndex);
                currentAttributes.add(attribute);
            }
            orderedAttributeList.add(currentAttributes);
        }
        return orderedAttributeList;
    }

    public ArrayList[][] getMethodAttributes() {
        return methodAttributes;
    }

    public String[][] getMethodDescr() {
        return methodDescr;
    }

    public long[][] getMethodFlags() {
        return methodFlags;
    }

    /**
     * Returns null if all classes should use the default major and minor
     * version or an array of integers containing the major version numberss to
     * use for each class in the segment
     * 
     * @return Class file major version numbers, or null if none specified
     */
    public int[] getClassVersionMajor() {
        return classVersionMajor;
    }

    /**
     * Returns null if all classes should use the default major and minor
     * version or an array of integers containing the minor version numberss to
     * use for each class in the segment
     * 
     * @return Class file minor version numbers, or null if none specified
     */
    public int[] getClassVersionMinor() {
        return classVersionMinor;
    }

}
