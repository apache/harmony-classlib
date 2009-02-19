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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.harmony.pack200.IcBands.IcTuple;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;

public class ClassBands extends BandSet {

    private final SegmentHeader header;
    private final CpBands cpBands;
    private final AttributeDefinitionBands attrBands;

    private final CPClass[] class_this;
    private final CPClass[] class_super;
    private final CPClass[][] class_interface;
    private final int[] class_interface_count;

    private final int[] major_versions;

    private final long[] class_flags;
    private int[] class_attr_calls;
    private final List classSourceFile = new ArrayList();
    private final List classEnclosingMethodClass = new ArrayList();
    private final List classEnclosingMethodDesc = new ArrayList();
    private final List classSignature = new ArrayList();

    private final IntList classFileVersionMinor = new IntList();
    private final IntList classFileVersionMajor = new IntList();

    private final int[] class_field_count;
    private final CPNameAndType[][] field_descr;
    private final long[][] field_flags;
    private int[] field_attr_calls;
    private final List fieldConstantValueKQ = new ArrayList();
    private final List fieldSignature = new ArrayList();

    private final int[] class_method_count;
    private final CPNameAndType[][] method_descr;
    private final long[][] method_flags;
    private int[] method_attr_calls;
    private final List methodSignature = new ArrayList();
    private final IntList methodExceptionNumber = new IntList();
    private final List methodExceptionClasses = new ArrayList();

    private int[] codeHeaders;
    private final IntList codeMaxStack = new IntList();
    private final IntList codeMaxLocals = new IntList();
    private final IntList codeHandlerCount = new IntList();
    private final List codeHandlerStartP = new ArrayList();
    private final List codeHandlerEndPO = new ArrayList();
    private final List codeHandlerCatchPO = new ArrayList();
    private final List codeHandlerClass = new ArrayList();
    private final List codeFlags = new ArrayList();
    private final IntList codeLineNumberTableN = new IntList();
    private final List codeLineNumberTableBciP = new ArrayList();
    private final IntList codeLineNumberTableLine = new IntList();
    private final IntList codeLocalVariableTableN = new IntList();
    private final List codeLocalVariableTableBciP = new ArrayList();
    private final List codeLocalVariableTableSpanO = new ArrayList();
    private final List codeLocalVariableTableNameRU = new ArrayList();
    private final List codeLocalVariableTableTypeRS = new ArrayList();
    private final IntList codeLocalVariableTableSlot = new IntList();
    private final IntList codeLocalVariableTypeTableN = new IntList();
    private final List codeLocalVariableTypeTableBciP = new ArrayList();
    private final List codeLocalVariableTypeTableSpanO = new ArrayList();
    private final List codeLocalVariableTypeTableNameRU = new ArrayList();
    private final List codeLocalVariableTypeTableTypeRS = new ArrayList();
    private final IntList codeLocalVariableTypeTableSlot = new IntList();

    private final MetadataBandGroup class_RVA_bands;
    private final MetadataBandGroup class_RIA_bands;
    private final MetadataBandGroup field_RVA_bands;
    private final MetadataBandGroup field_RIA_bands;
    private final MetadataBandGroup method_RVA_bands;
    private final MetadataBandGroup method_RIA_bands;
    private final MetadataBandGroup method_RVPA_bands;
    private final MetadataBandGroup method_RIPA_bands;
    private final MetadataBandGroup method_AD_bands;

    private final List tempFieldFlags = new ArrayList();
    private final List tempFieldDesc = new ArrayList();
    private final List tempMethodFlags = new ArrayList();
    private final List tempMethodDesc = new ArrayList();

    private boolean anySyntheticClasses = false;
    private boolean anySyntheticFields = false;
    private boolean anySyntheticMethods = false;
    private final Segment segment;

    private final Map classReferencesInnerClass = new HashMap();

    public ClassBands(Segment segment, int numClasses) {
        this.segment = segment;
        this.header = segment.getSegmentHeader();
        this.cpBands = segment.getCpBands();
        this.attrBands = segment.getAttrBands();
        class_this = new CPClass[numClasses];
        class_super = new CPClass[numClasses];
        class_interface_count = new int[numClasses];
        class_interface = new CPClass[numClasses][];
        class_field_count = new int[numClasses];
        class_method_count = new int[numClasses];
        field_descr = new CPNameAndType[numClasses][];
        field_flags = new long[numClasses][];
        method_descr = new CPNameAndType[numClasses][];
        method_flags = new long[numClasses][];
        // minor_versions = new int[numClasses];
        major_versions = new int[numClasses];
        class_flags = new long[numClasses];

        class_RVA_bands = new MetadataBandGroup("RVA", MetadataBandGroup.CONTEXT_CLASS, cpBands);
        class_RIA_bands = new MetadataBandGroup("RIA", MetadataBandGroup.CONTEXT_CLASS, cpBands);
        field_RVA_bands = new MetadataBandGroup("RVA", MetadataBandGroup.CONTEXT_FIELD, cpBands);
        field_RIA_bands = new MetadataBandGroup("RIA", MetadataBandGroup.CONTEXT_FIELD, cpBands);
        method_RVA_bands = new MetadataBandGroup("RVA", MetadataBandGroup.CONTEXT_METHOD, cpBands);
        method_RIA_bands = new MetadataBandGroup("RIA", MetadataBandGroup.CONTEXT_METHOD, cpBands);
        method_RVPA_bands = new MetadataBandGroup("RVPA", MetadataBandGroup.CONTEXT_METHOD, cpBands);
        method_RIPA_bands = new MetadataBandGroup("RIPA", MetadataBandGroup.CONTEXT_METHOD, cpBands);
        method_AD_bands = new MetadataBandGroup("AD", MetadataBandGroup.CONTEXT_METHOD, cpBands);
    }

    private int index = 0;

    private int numMethodArgs = 0;
    private int[] class_InnerClasses_N;
    private CPClass[] class_InnerClasses_RC;
    private int[] class_InnerClasses_F;
    private List classInnerClassesOuterRCN;
    private List classInnerClassesNameRUN;

    public void addClass(int major, int flags, String className,
            String signature, String superName, String[] interfaces) {
        class_this[index] = cpBands.getCPClass(className);
        class_super[index] = cpBands.getCPClass(superName);
        class_interface_count[index] = interfaces.length;
        class_interface[index] = new CPClass[interfaces.length];
        for (int i = 0; i < interfaces.length; i++) {
            class_interface[index][i] = cpBands.getCPClass(interfaces[i]);
        }
        major_versions[index] = major;
        class_flags[index] = flags;
        if(!anySyntheticClasses && ((flags & (1 << 12)) != 0) && segment.getCurrentClassReader().hasSyntheticAttributes()) {
            cpBands.addCPUtf8("Synthetic");
            anySyntheticClasses = true;
        }
        if((flags & Opcodes.ACC_DEPRECATED) != 0) { // ASM uses (1<<17) flag for deprecated
            flags = flags & ~Opcodes.ACC_DEPRECATED;
            flags = flags | (1<<20);
        }
        if(signature != null) {
            class_flags[index] |= (1 << 19);
            classSignature.add(cpBands.getCPSignature(signature));
        }
    }

    public void currentClassReferencesInnerClass(CPClass inner) {
        if(!(index >= class_this.length)) {
            CPClass currentClass = class_this[index];
            if(currentClass != null && !currentClass.equals(inner) && !isInnerClassOf(currentClass, inner)) {
                Set referencedInnerClasses = (Set)classReferencesInnerClass.get(currentClass);
                if(referencedInnerClasses == null) {
                    referencedInnerClasses = new HashSet();
                    classReferencesInnerClass.put(currentClass, referencedInnerClasses);
                }
                referencedInnerClasses.add(inner);
            }
        }
    }

    private boolean isInnerClassOf(CPClass possibleInner, CPClass possibleOuter) {
        String currentClassName = possibleInner.toString();
        if(possibleInner.isInnerClass()) {
            String superClassName = currentClassName.substring(0, currentClassName.lastIndexOf('$'));
            return superClassName.equals(possibleOuter.toString());
        }
        return false;
    }

    public void addField(int flags, String name, String desc, String signature,
            Object value) {
        flags = flags & 0xFFFF;
        CPMethodOrField field = cpBands.addCPField(class_this[index], name,
                desc);
        tempFieldDesc.add(field.getDesc());
        if (signature != null) {
            fieldSignature.add(cpBands.getCPSignature(signature));
            flags |= (1 << 19);
        }
        if((flags & Opcodes.ACC_DEPRECATED) != 0) { // ASM uses (1<<17) flag for deprecated
            flags = flags & ~Opcodes.ACC_DEPRECATED;
            flags = flags | (1<<20);
        }
        if (value != null) {
            fieldConstantValueKQ.add(cpBands.getConstant(value));
            flags |= (1 << 17);
        }
        if(!anySyntheticFields && ((flags & (1 << 12)) != 0) && segment.getCurrentClassReader().hasSyntheticAttributes()) {
            cpBands.addCPUtf8("Synthetic");
            anySyntheticFields = true;
        }
        tempFieldFlags.add(new Long(flags));
    }

    public void finaliseBands() {
        int defaultMajorVersion = header.getDefaultMajorVersion();
        for (int i = 0; i < class_flags.length; i++) {
            int major = major_versions[i];
            if (major != defaultMajorVersion) {
                class_flags[i] |= 1 << 24;
                classFileVersionMajor.add(major);
                classFileVersionMinor.add(0);
            }
        }
        // Calculate code headers
        codeHeaders = new int[codeHandlerCount.size()];
        int removed = 0;
        for (int i = 0; i < codeHeaders.length; i++) {
            int numHandlers = codeHandlerCount.get(i - removed);
            int maxLocals = codeMaxLocals.get(i - removed);
            int maxStack = codeMaxStack.get(i - removed);
            if (numHandlers == 0) {
                int header = maxLocals * 12 + maxStack + 1;
                if (header < 145 && maxStack < 12) {
                    codeHeaders[i] = header;
                }
            } else if (numHandlers == 1) {
                int header = maxLocals * 8 + maxStack + 145;
                if (header < 209 && maxStack < 8) {
                    codeHeaders[i] = header;
                }
            } else if (numHandlers == 2) {
                int header = maxLocals * 7 + maxStack + 209;
                if (header < 256 && maxStack < 7) {
                    codeHeaders[i] = header;
                }
            }
            if (codeHeaders[i] != 0) { // Remove the redundant values from
                                        // codeHandlerCount, codeMaxLocals and
                                        // codeMaxStack
                codeHandlerCount.remove(i - removed);
                codeMaxLocals.remove(i - removed);
                codeMaxStack.remove(i - removed);
                removed++;
            } else if (!segment.getSegmentHeader().have_all_code_flags()) {
                codeFlags.add(new Long(0));
            }
        }

        // Compute any required IcLocals
        IntList innerClassesN = new IntList();
        List icLocal = new ArrayList();
        for (int i = 0; i < class_this.length; i++) {
            CPClass cpClass = class_this[i];
            Set referencedInnerClasses = (Set) classReferencesInnerClass.get(cpClass);
            if(referencedInnerClasses != null) {
                int innerN = 0;
                List innerClasses = segment.getIcBands().getInnerClassesForOuter(cpClass.toString());
                if(innerClasses != null) {
                    for (Iterator iterator2 = innerClasses.iterator(); iterator2
                            .hasNext();) {
                        referencedInnerClasses.remove(((IcTuple)iterator2.next()).C);
                    }
                }
                for (Iterator iterator2 = referencedInnerClasses.iterator(); iterator2
                        .hasNext();) {
                    CPClass inner = (CPClass) iterator2.next();
                    IcTuple icTuple = segment.getIcBands().getIcTuple(inner);
                    if(icTuple != null) {
                        // should transmit an icLocal entry
                        icLocal.add(icTuple);
                        innerN++;
                    }
                }
                if(innerN != 0) {
                    innerClassesN.add(innerN);
                    class_flags[i] |= (1 << 23);
                }
            }
        }
        class_InnerClasses_N = innerClassesN.toArray();
        class_InnerClasses_RC = new CPClass[icLocal.size()];
        class_InnerClasses_F = new int[icLocal.size()];
        classInnerClassesOuterRCN = new ArrayList();
        classInnerClassesNameRUN = new ArrayList();
        for (int i = 0; i < class_InnerClasses_RC.length; i++) {
            IcTuple icTuple = (IcTuple) icLocal.get(i);
            class_InnerClasses_RC[i] = (icTuple.C);
            if(icTuple.C2 == null && icTuple.N == null) {
                class_InnerClasses_F[i] = 0;
            } else {
                if (icTuple.F == 0) {
                    class_InnerClasses_F[i] = 0x00010000;
                } else {
                    class_InnerClasses_F[i] = icTuple.F;
                }
                classInnerClassesOuterRCN.add(icTuple.C2);
                classInnerClassesNameRUN.add(icTuple.N);
            }
        }
        // Calculate any backwards calls from metadata bands
        IntList classAttrCalls = new IntList();
        IntList fieldAttrCalls = new IntList();
        IntList methodAttrCalls = new IntList();
        if(class_RVA_bands.hasContent()) {
            classAttrCalls.add(class_RVA_bands.numBackwardsCalls());
        }
        if(class_RIA_bands.hasContent()) {
            classAttrCalls.add(class_RIA_bands.numBackwardsCalls());
        }
        if(field_RVA_bands.hasContent()) {
            fieldAttrCalls.add(field_RVA_bands.numBackwardsCalls());
        }
        if(field_RIA_bands.hasContent()) {
            fieldAttrCalls.add(field_RIA_bands.numBackwardsCalls());
        }
        if(method_RVA_bands.hasContent()) {
            methodAttrCalls.add(method_RVA_bands.numBackwardsCalls());
        }
        if(method_RIA_bands.hasContent()) {
            methodAttrCalls.add(method_RIA_bands.numBackwardsCalls());
        }
        if(method_RVPA_bands.hasContent()) {
            methodAttrCalls.add(method_RVPA_bands.numBackwardsCalls());
        }
        if(method_RIPA_bands.hasContent()) {
            methodAttrCalls.add(method_RIPA_bands.numBackwardsCalls());
        }
        if(method_AD_bands.hasContent()) {
            methodAttrCalls.add(method_AD_bands.numBackwardsCalls());
        }
        class_attr_calls = classAttrCalls.toArray();
        field_attr_calls = fieldAttrCalls.toArray();
        method_attr_calls = methodAttrCalls.toArray();
    }

    public void pack(OutputStream out) throws IOException, Pack200Exception {
        out.write(encodeBandInt("class_this", getInts(class_this), Codec.DELTA5));
        out.write(encodeBandInt("class_super", getInts(class_super), Codec.DELTA5));
        out.write(encodeBandInt("class_interface_count", class_interface_count,
                Codec.DELTA5));

        int totalInterfaces = sum(class_interface_count);
        int[] classInterface = new int[totalInterfaces];
        int k = 0;
        for (int i = 0; i < class_interface.length; i++) {
            if (class_interface[i] != null) {
                for (int j = 0; j < class_interface[i].length; j++) {
                    CPClass cpClass = class_interface[i][j];
                    classInterface[k] = cpClass.getIndex();
                    k++;
                }
            }
        }
        out.write(encodeBandInt("class_interface", classInterface,
                Codec.DELTA5));
        out.write(encodeBandInt("class_field_count", class_field_count,
                Codec.DELTA5));
        out.write(encodeBandInt("class_method_count", class_method_count,
                Codec.DELTA5));

        int totalFields = sum(class_field_count);
        int[] fieldDescr = new int[totalFields];
        k = 0;
        for (int i = 0; i < field_descr.length; i++) {
            for (int j = 0; j < field_descr[i].length; j++) {
                CPNameAndType descr = field_descr[i][j];
                fieldDescr[k] = descr.getIndex();
                k++;
            }
        }
        out.write(encodeBandInt("field_descr", fieldDescr, Codec.DELTA5));
        writeFieldAttributeBands(out);

        int totalMethods = sum(class_method_count);
        int[] methodDescr = new int[totalMethods];
        k = 0;
        for (int i = 0; i < method_descr.length; i++) {
            for (int j = 0; j < method_descr[i].length; j++) {
                CPNameAndType descr = method_descr[i][j];
                methodDescr[k] = descr.getIndex();
                k++;
            }
        }
        out.write(encodeBandInt("method_descr", methodDescr, Codec.MDELTA5));

        writeMethodAttributeBands(out);
        writeClassAttributeBands(out);
        writeCodeBands(out);
    }

    private int sum(int[] ints) {
        int sum = 0;
        for (int i = 0; i < ints.length; i++) {
            sum += ints[i];
        }
        return sum;
    }

    private void writeFieldAttributeBands(OutputStream out) throws IOException,
            Pack200Exception {
        out.write(encodeFlags("field_flags", field_flags, Codec.UNSIGNED5,
                Codec.UNSIGNED5, header.have_field_flags_hi()));
//        *field_attr_count :UNSIGNED5 [COUNT(1<<16,...)]
//        *field_attr_indexes :UNSIGNED5 [SUM(*field_attr_count)]
        out.write(encodeBandInt("field_attr_calls", field_attr_calls, Codec.UNSIGNED5));
        out.write(encodeBandInt("fieldConstantValueKQ",
                cpEntryListToArray(fieldConstantValueKQ), Codec.UNSIGNED5));
        out.write(encodeBandInt("fieldSignature",
                cpEntryListToArray(fieldSignature), Codec.UNSIGNED5));
        field_RVA_bands.pack(out);
        field_RIA_bands.pack(out);
    }

    private void writeMethodAttributeBands(OutputStream out)
            throws IOException, Pack200Exception {
        out.write(encodeFlags("method_flags", method_flags, Codec.UNSIGNED5,
                Codec.UNSIGNED5, header.have_method_flags_hi()));
//        *method_attr_count :UNSIGNED5 [COUNT(1<<16,...)]
//        *method_attr_indexes :UNSIGNED5 [SUM(*method_attr_count)]
        out.write(encodeBandInt("method_attr_calls", method_attr_calls, Codec.UNSIGNED5));
        out.write(encodeBandInt("methodExceptionNumber",
                methodExceptionNumber.toArray(), Codec.UNSIGNED5));
        out.write(encodeBandInt("methodExceptionClasses",
                cpEntryListToArray(methodExceptionClasses), Codec.UNSIGNED5));
        out.write(encodeBandInt("methodSignature",
                cpEntryListToArray(methodSignature), Codec.UNSIGNED5));
        method_RVA_bands.pack(out);
        method_RIA_bands.pack(out);
        method_RVPA_bands.pack(out);
        method_RIPA_bands.pack(out);
        method_AD_bands.pack(out);
    }

    private void writeClassAttributeBands(OutputStream out) throws IOException,
            Pack200Exception {
        out.write(encodeFlags("class_flags", class_flags, Codec.UNSIGNED5,
                Codec.UNSIGNED5, header.have_class_flags_hi()));
//        *class_attr_count :UNSIGNED5 [COUNT(1<<16,...)]
//        *class_attr_indexes :UNSIGNED5 [SUM(*class_attr_count)]
        out.write(encodeBandInt("class_attr_calls", class_attr_calls, Codec.UNSIGNED5));
        out.write(encodeBandInt("classSourceFile",
                cpEntryOrNullListToArray(classSourceFile), Codec.UNSIGNED5));
        out.write(encodeBandInt("class_enclosing_method_RC",
                 cpEntryListToArray(classEnclosingMethodClass),
                 Codec.UNSIGNED5));
        out.write(encodeBandInt("class_EnclosingMethod_RDN",
                cpEntryOrNullListToArray(classEnclosingMethodDesc),
                Codec.UNSIGNED5));
        out.write(encodeBandInt("class_Signature_RS",
                cpEntryListToArray(classSignature), Codec.UNSIGNED5));
        class_RVA_bands.pack(out);
        class_RIA_bands.pack(out);
        out.write(encodeBandInt("class_InnerClasses_N", class_InnerClasses_N, Codec.UNSIGNED5));
        out.write(encodeBandInt("class_InnerClasses_RC", getInts(class_InnerClasses_RC), Codec.UNSIGNED5));
        out.write(encodeBandInt("class_InnerClasses_F", class_InnerClasses_F, Codec.UNSIGNED5));
        out.write(encodeBandInt("class_InnerClasses_outer_RCN", cpEntryOrNullListToArray(classInnerClassesOuterRCN), Codec.UNSIGNED5));
        out.write(encodeBandInt("class_InnerClasses_name_RUN", cpEntryOrNullListToArray(classInnerClassesNameRUN), Codec.UNSIGNED5));
        out.write(encodeBandInt("classFileVersionMinor",
                classFileVersionMinor.toArray(), Codec.UNSIGNED5));
        out.write(encodeBandInt("classFileVersionMajor",
                classFileVersionMajor.toArray(), Codec.UNSIGNED5));
    }

    private int[] getInts(CPClass[] cpClasses) {
        int[] ints = new int[cpClasses.length];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = cpClasses[i].getIndex();
        }
        return ints;
    }

    private void writeCodeBands(OutputStream out) throws IOException,
            Pack200Exception {
        out.write(encodeBandInt("codeHeaders", codeHeaders, Codec.BYTE1));
        out.write(encodeBandInt("codeMaxStack", codeMaxStack.toArray(),
                Codec.UNSIGNED5));
        out.write(encodeBandInt("codeMaxLocals", codeMaxLocals.toArray(),
                Codec.UNSIGNED5));
        out.write(encodeBandInt("codeHandlerCount",
                codeHandlerCount.toArray(), Codec.UNSIGNED5));
        out.write(encodeBandInt("codeHandlerStartP",
                listToArray(codeHandlerStartP), Codec.BCI5));
        out.write(encodeBandInt("codeHandlerEndPO",
                listToArray(codeHandlerEndPO), Codec.BRANCH5));
        out.write(encodeBandInt("codeHandlerCatchPO",
                listToArray(codeHandlerCatchPO), Codec.BRANCH5));
        out.write(encodeBandInt("codeHandlerClass",
                cpEntryOrNullListToArray(codeHandlerClass), Codec.UNSIGNED5));
        writeCodeAttributeBands(out);
    }

    private void writeCodeAttributeBands(OutputStream out) throws IOException,
            Pack200Exception {
        out.write(encodeFlags("codeFlags", longListToArray(codeFlags),
                Codec.UNSIGNED5, Codec.UNSIGNED5, header.have_code_flags_hi()));

        // *code_attr_count :UNSIGNED5 [COUNT(1<<16,...)]
        // *code_attr_indexes :UNSIGNED5 [SUM(*code_attr_count)]
        // *code_attr_calls :UNSIGNED5 [...]
        out.write(encodeBandInt("code_LineNumberTable_N",
                codeLineNumberTableN.toArray(), Codec.UNSIGNED5));
        out.write(encodeBandInt("code_LineNumberTable_bci_P",
                listToArray(codeLineNumberTableBciP), Codec.BCI5));
        out.write(encodeBandInt("code_LineNumberTable_line",
                codeLineNumberTableLine.toArray(), Codec.UNSIGNED5));
        out.write(encodeBandInt("code_LocalVariableTable_N",
                codeLocalVariableTableN.toArray(), Codec.UNSIGNED5));
        out.write(encodeBandInt("code_LocalVariableTable_bci_P",
                listToArray(codeLocalVariableTableBciP), Codec.BCI5));
        out.write(encodeBandInt("code_LocalVariableTable_span_O",
                listToArray(codeLocalVariableTableSpanO), Codec.BRANCH5));
        out.write(encodeBandInt("code_LocalVariableTable_name_RU",
                cpEntryListToArray(codeLocalVariableTableNameRU),
                Codec.UNSIGNED5));
        out.write(encodeBandInt("code_LocalVariableTable_type_RS",
                cpEntryListToArray(codeLocalVariableTableTypeRS),
                Codec.UNSIGNED5));
        out.write(encodeBandInt("code_LocalVariableTable_slot",
                codeLocalVariableTableSlot.toArray(), Codec.UNSIGNED5));
        out.write(encodeBandInt("code_LocalVariableTypeTable_N",
                codeLocalVariableTypeTableN.toArray(), Codec.UNSIGNED5));
        out.write(encodeBandInt("code_LocalVariableTypeTable_bci_P",
                listToArray(codeLocalVariableTypeTableBciP), Codec.BCI5));
        out.write(encodeBandInt("code_LocalVariableTypeTable_span_O",
                listToArray(codeLocalVariableTypeTableSpanO), Codec.BRANCH5));
        out.write(encodeBandInt("code_LocalVariableTypeTable_name_RU",
                cpEntryListToArray(codeLocalVariableTypeTableNameRU),
                Codec.UNSIGNED5));
        out.write(encodeBandInt("code_LocalVariableTypeTable_type_RS",
                cpEntryListToArray(codeLocalVariableTypeTableTypeRS),
                Codec.UNSIGNED5));
        out.write(encodeBandInt("code_LocalVariableTypeTable_slot",
                codeLocalVariableTypeTableSlot.toArray(), Codec.UNSIGNED5));

    }

    public void addMethod(int flags, String name, String desc,
            String signature, String[] exceptions) {
        CPMethodOrField method = cpBands.addCPMethod(class_this[index], name,
                desc);
        tempMethodDesc.add(method.getDesc());
        if (signature != null) {
            methodSignature.add(cpBands.getCPSignature(signature));
            flags |= (1 << 19);
        }
        if (exceptions != null) {
            methodExceptionNumber.add(exceptions.length);
            for (int i = 0; i < exceptions.length; i++) {
                methodExceptionClasses.add(cpBands.getCPClass(exceptions[i]));
            }
            flags |= (1 << 18);
        }
        if((flags & Opcodes.ACC_DEPRECATED) != 0) { // ASM uses (1<<17) flag for deprecated
            flags = flags & ~Opcodes.ACC_DEPRECATED;
            flags = flags | (1<<20);
        }
        tempMethodFlags.add(new Long(flags));
        numMethodArgs = countArgs(desc);
        if(!anySyntheticMethods && ((flags & (1 << 12)) != 0) && segment.getCurrentClassReader().hasSyntheticAttributes()) {
            cpBands.addCPUtf8("Synthetic");
            anySyntheticMethods = true;
        }
    }

    public void endOfMethod() {
        if(codeFlags.size() > 0) {
            long latestCodeFlag = ((Long)codeFlags.get(codeFlags.size() - 1)).longValue();
            int latestLocalVariableTableN = codeLocalVariableTableN.get(codeLocalVariableTableN.size() - 1);
            if(latestCodeFlag == (1 << 2) && latestLocalVariableTableN == 0) {
                codeLocalVariableTableN.remove(codeLocalVariableTableN.size() - 1);
                codeFlags.remove(codeFlags.size() - 1);
                codeFlags.add(new Integer(0));
            }
        }
    }

    protected static int countArgs(String descriptor) {
        int bra = descriptor.indexOf('(');
        int ket = descriptor.indexOf(')');
        if (bra == -1 || ket == -1 || ket < bra)
            throw new IllegalArgumentException("No arguments");

        boolean inType = false;
        boolean consumingNextType = false;
        int count = 0;
        for (int i = bra + 1; i < ket; i++) {
            char charAt = descriptor.charAt(i);
            if (inType && charAt == ';') {
                inType = false;
                consumingNextType = false;
            } else if (!inType && charAt == 'L') {
                inType = true;
                count++;
            } else if (charAt == '[') {
                consumingNextType = true;
            } else if (inType) {
                // NOP
            } else {
                if (consumingNextType) {
                    count++;
                    consumingNextType = false;
                } else {
                    if (charAt == 'D' || charAt == 'J') {
                        count += 2;
                    } else {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public void endOfClass() { // All the data for the current class has been
                                // read
        int numFields = tempFieldDesc.size();
        class_field_count[index] = numFields;
        field_descr[index] = new CPNameAndType[numFields];
        field_flags[index] = new long[numFields];
        for (int i = 0; i < numFields; i++) {
            field_descr[index][i] = (CPNameAndType) tempFieldDesc.get(i);
            field_flags[index][i] = ((Long) tempFieldFlags.get(i)).longValue();
        }
        int numMethods = tempMethodDesc.size();
        class_method_count[index] = numMethods;
        method_descr[index] = new CPNameAndType[numMethods];
        method_flags[index] = new long[numMethods];
        for (int i = 0; i < numMethods; i++) {
            method_descr[index][i] = (CPNameAndType) tempMethodDesc.get(i);
            method_flags[index][i] = ((Long) tempMethodFlags.get(i))
                    .longValue();
        }
        tempFieldDesc.clear();
        tempFieldFlags.clear();
        tempMethodDesc.clear();
        tempMethodFlags.clear();
        index++;
    }

    public void addSourceFile(String source) {
        String implicitSourceFileName = class_this[index].toString();
        if(implicitSourceFileName.indexOf('$') != -1) {
            implicitSourceFileName = implicitSourceFileName.substring(0, implicitSourceFileName.indexOf('$'));
        }
        implicitSourceFileName = implicitSourceFileName
                .substring(implicitSourceFileName.lastIndexOf('/') + 1)
                + ".java";
        if (source.equals(implicitSourceFileName)) {
            classSourceFile.add(null);
        } else {
            classSourceFile.add(cpBands.getCPUtf8(source));
        }
        class_flags[index] |= (1 << 17);
    }

    public void addEnclosingMethod(String owner, String name, String desc) {
        class_flags[index] |= (1 << 18);
        classEnclosingMethodClass.add(cpBands.getCPClass(owner));
        classEnclosingMethodDesc.add(name == null ? null : cpBands
                .getCPNameAndType(name, desc));
    }

    public void addUnknownFieldAttribute(Attribute arg0) {
        // TODO Auto-generated method stub

    }

    public void addUnknownMethodAttribute(Attribute arg0) {
        // TODO Auto-generated method stub

    }

    public void addMaxStack(int maxStack, int maxLocals) {
        Long latestFlag = (Long) tempMethodFlags
                .remove(tempMethodFlags.size() - 1);
        Long newFlag = new Long(latestFlag.intValue() | (1 << 17));
        tempMethodFlags.add(newFlag);
        codeMaxStack.add(maxStack);
        if ((newFlag.longValue() & (1 << 3)) == 0) { // not static
            maxLocals--; // minus 'this' local
        }
        maxLocals -= numMethodArgs;
        codeMaxLocals.add(maxLocals);
    }

    public void addCode(boolean stripDebug) {
        codeHandlerCount.add(0);
        if(!stripDebug) {
            codeFlags.add(new Long((1 << 2))); // TODO: What if there's no debug information?
            codeLocalVariableTableN.add(0);
        }
    }

    public void addHandler(Label start, Label end, Label handler, String type) {
        int handlers = codeHandlerCount.remove(codeHandlerCount
                .size() - 1);
        codeHandlerCount.add(handlers + 1);
        codeHandlerStartP.add(start);
        codeHandlerEndPO.add(end);
        codeHandlerCatchPO.add(handler);
        codeHandlerClass.add(type == null ? null : cpBands.getCPClass(type));
    }

    public void addLineNumber(int line, Label start) {
        Long latestCodeFlag = (Long) codeFlags.get(codeFlags.size() - 1);
        if ((latestCodeFlag.intValue() & (1 << 1)) == 0) {
            codeFlags.remove(codeFlags.size() - 1);
            codeFlags.add(new Long(latestCodeFlag.intValue() | (1 << 1)));
            codeLineNumberTableN.add(1);
        } else {
            codeLineNumberTableN
                    .increment(codeLineNumberTableN.size() - 1);
        }
        codeLineNumberTableLine.add(line);
        codeLineNumberTableBciP.add(start);
        // TODO: bci renumbering
    }

    public void addLocalVariable(String name, String desc, String signature,
            Label start, Label end, int indx) {
        if (signature != null) { // LocalVariableTypeTable attribute
            Long latestCodeFlag = (Long) codeFlags.get(codeFlags.size() - 1);
            if ((latestCodeFlag.intValue() & (1 << 3)) == 0) {
                codeFlags.remove(codeFlags.size() - 1);
                codeFlags.add(new Long(latestCodeFlag.intValue() | (1 << 3)));
                codeLocalVariableTypeTableN.add(1);
            } else {
                codeLocalVariableTypeTableN
                        .increment(codeLocalVariableTypeTableN.size() - 1);
            }
            codeLocalVariableTypeTableBciP.add(start);
            codeLocalVariableTypeTableSpanO.add(end);
            codeLocalVariableTypeTableNameRU.add(cpBands.getCPUtf8(name));
            codeLocalVariableTypeTableTypeRS.add(cpBands
                    .getCPSignature(signature));
            codeLocalVariableTypeTableSlot.add(indx);
        }
        // LocalVariableTable attribute
        codeLocalVariableTableN
                .increment(codeLocalVariableTableN.size() - 1);
        codeLocalVariableTableBciP.add(start);
        codeLocalVariableTableSpanO.add(end);
        codeLocalVariableTableNameRU.add(cpBands.getCPUtf8(name));
        codeLocalVariableTableTypeRS.add(cpBands.getCPSignature(desc));
        codeLocalVariableTableSlot.add(indx);
    }

    public void doBciRenumbering(IntList bciRenumbering, Map labelsToOffsets) {
        renumberBci(codeLineNumberTableBciP, bciRenumbering, labelsToOffsets);
        renumberBci(codeLocalVariableTableBciP, bciRenumbering, labelsToOffsets);
        renumberOffsetBci(codeLocalVariableTableBciP,
                codeLocalVariableTableSpanO, bciRenumbering, labelsToOffsets);
        renumberBci(codeLocalVariableTypeTableBciP, bciRenumbering,
                labelsToOffsets);
        renumberOffsetBci(codeLocalVariableTypeTableBciP,
                codeLocalVariableTypeTableSpanO, bciRenumbering,
                labelsToOffsets);
        renumberBci(codeHandlerStartP, bciRenumbering, labelsToOffsets);
        renumberOffsetBci(codeHandlerStartP, codeHandlerEndPO,
                bciRenumbering, labelsToOffsets);
        renumberDoubleOffsetBci(codeHandlerStartP, codeHandlerEndPO, codeHandlerCatchPO,
                bciRenumbering, labelsToOffsets);
    }

    private void renumberBci(List list, IntList bciRenumbering, Map labelsToOffsets) {
        for (int i = list.size() - 1; i >= 0; i--) {
            Object label = list.get(i);
            if (label instanceof Integer) {
                break;
            } else if (label instanceof Label) {
                list.remove(i);
                Integer bytecodeIndex = (Integer) labelsToOffsets.get(label);
                list.add(i, new Integer(bciRenumbering.get(bytecodeIndex.intValue())));
            }
        }
    }

    private void renumberOffsetBci(List relative, List list,
            IntList bciRenumbering, Map labelsToOffsets) {
        for (int i = list.size() - 1; i >= 0; i--) {
            Object label = list.get(i);
            if (label instanceof Integer) {
                break;
            } else if (label instanceof Label) {
                list.remove(i);
                Integer bytecodeIndex = (Integer) labelsToOffsets.get(label);
                Integer renumberedOffset = new Integer(bciRenumbering
                        .get(bytecodeIndex.intValue())
                        - ((Integer) relative.get(i)).intValue());
                list.add(i, renumberedOffset);
            }
        }
    }

    private void renumberDoubleOffsetBci(List relative, List firstOffset, List list,
            IntList bciRenumbering, Map labelsToOffsets) {
        // TODO: There's probably a nicer way of doing this...
        for (int i = list.size() - 1; i >= 0; i--) {
            Object label = list.get(i);
            if (label instanceof Integer) {
                break;
            } else if (label instanceof Label) {
                list.remove(i);
                Integer bytecodeIndex = (Integer) labelsToOffsets.get(label);
                Integer renumberedOffset = new Integer(bciRenumbering
                        .get(bytecodeIndex.intValue())
                        - ((Integer) relative.get(i)).intValue() - ((Integer) firstOffset.get(i)).intValue());
                list.add(i, renumberedOffset);
            }
        }
    }

    public boolean isAnySyntheticClasses() {
        return anySyntheticClasses;
    }

    public boolean isAnySyntheticFields() {
        return anySyntheticFields;
    }

    public boolean isAnySyntheticMethods() {
        return anySyntheticMethods;
    }

    public void addParameterAnnotation(int parameter, String desc,
            boolean visible, List nameRU, List t, List values, List caseArrayN, List nestTypeRS, List nestNameRU, List nestPairN) {
        if(visible) {
            method_RVPA_bands.addAnnotation(desc, nameRU, t, values, caseArrayN, nestTypeRS, nestNameRU, nestPairN);
            Integer flag = (Integer) tempMethodFlags.remove(tempMethodFlags.size() - 1);
            if((flag.intValue() & (1<<23)) != 0) {
                method_RVPA_bands.incrementAnnoN();
            } else {
                method_RVPA_bands.newEntryInAnnoN();
            }
            tempMethodFlags.add(new Integer(flag.intValue() | (1<<23)));
        } else {
            method_RIPA_bands.addAnnotation(desc, nameRU, t, values, caseArrayN, nestTypeRS, nestNameRU, nestPairN);
            Integer flag = (Integer) tempMethodFlags.remove(tempMethodFlags.size() - 1);
            if((flag.intValue() & (1<<24)) != 0) {
                method_RIPA_bands.incrementAnnoN();
            } else {
                method_RIPA_bands.newEntryInAnnoN();
            }
            tempMethodFlags.add(new Integer(flag.intValue() | (1<<24)));
        }
    }

    public void addAnnotation(int context, String desc, boolean visible, List nameRU, List t, List values, List caseArrayN, List nestTypeRS, List nestNameRU, List nestPairN) {
        switch (context) {
        case MetadataBandGroup.CONTEXT_CLASS:
            if(visible) {
                class_RVA_bands.addAnnotation(desc, nameRU, t, values, caseArrayN, nestTypeRS, nestNameRU, nestPairN);
                if((class_flags[index] & (1<<21)) != 0) {
                    class_RVA_bands.incrementAnnoN();
                } else {
                    class_RVA_bands.newEntryInAnnoN();
                    class_flags[index] = class_flags[index] | (1<<21);
                }
            } else {
                class_RIA_bands.addAnnotation(desc, nameRU, t, values, caseArrayN, nestTypeRS, nestNameRU, nestPairN);
                if((class_flags[index] & (1<<22)) != 0) {
                    class_RIA_bands.incrementAnnoN();
                } else {
                    class_RIA_bands.newEntryInAnnoN();
                    class_flags[index] = class_flags[index] | (1<<22);
                }
            }
            break;
        case MetadataBandGroup.CONTEXT_FIELD:
            if(visible) {
                field_RVA_bands.addAnnotation(desc, nameRU, t, values, caseArrayN, nestTypeRS, nestNameRU, nestPairN);
                Long flag = (Long) tempFieldFlags.remove(tempFieldFlags.size() - 1);
                if((flag.intValue() & (1<<21)) != 0) {
                    field_RVA_bands.incrementAnnoN();
                } else {
                    field_RVA_bands.newEntryInAnnoN();
                }
                tempFieldFlags.add(new Long(flag.intValue() | (1<<21)));
            } else {
                field_RIA_bands.addAnnotation(desc, nameRU, t, values, caseArrayN, nestTypeRS, nestNameRU, nestPairN);
                Long flag = (Long) tempFieldFlags.remove(tempFieldFlags.size() - 1);
                if((flag.intValue() & (1<<22)) != 0) {
                    field_RIA_bands.incrementAnnoN();
                } else {
                    field_RIA_bands.newEntryInAnnoN();
                }
                tempFieldFlags.add(new Long(flag.intValue() | (1<<22)));
            }
            break;
        case MetadataBandGroup.CONTEXT_METHOD:
            if(visible) {
                method_RVA_bands.addAnnotation(desc, nameRU, t, values, caseArrayN, nestTypeRS, nestNameRU, nestPairN);
                Long flag = (Long) tempMethodFlags.remove(tempMethodFlags.size() - 1);
                if((flag.intValue() & (1<<21)) != 0) {
                    method_RVA_bands.incrementAnnoN();
                } else {
                    method_RVA_bands.newEntryInAnnoN();
                }
                tempMethodFlags.add(new Long(flag.intValue() | (1<<21)));
            } else {
                method_RIA_bands.addAnnotation(desc, nameRU, t, values, caseArrayN, nestTypeRS, nestNameRU, nestPairN);
                Long flag = (Long) tempMethodFlags.remove(tempMethodFlags.size() - 1);
                if((flag.intValue() & (1<<22)) != 0) {
                    method_RIA_bands.incrementAnnoN();
                } else {
                    method_RIA_bands.newEntryInAnnoN();
                }
                tempMethodFlags.add(new Long(flag.intValue() | (1<<22)));
            }
            break;
        }
    }

    public void addAnnotationDefault(List nameRU, List t, List values, List caseArrayN, List nestTypeRS, List nestNameRU, List nestPairN) {
        method_AD_bands.addAnnotation(null, nameRU, t, values, caseArrayN, nestTypeRS, nestNameRU, nestPairN);
        Integer flag = (Integer) tempMethodFlags.remove(tempMethodFlags.size() - 1);
        tempMethodFlags.add(new Integer(flag.intValue() | (1<<25)));
    }
}
