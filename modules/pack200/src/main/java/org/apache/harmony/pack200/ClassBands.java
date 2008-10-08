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
import java.util.List;
import java.util.Map;

import org.objectweb.asm.Attribute;
import org.objectweb.asm.Label;

public class ClassBands extends BandSet {

    private static final Integer ZERO = new Integer(0);
    private final SegmentHeader header;
    private final CpBands cpBands;
    private final AttributeDefinitionBands attrBands;

    private final CPClass[] class_this;
    private final CPClass[] class_super;
    private final CPClass[][] class_interface;
    private final int[] class_interface_count;

    private final int[] major_versions;

    private final long[] class_flags;
    private final List classSourceFile = new ArrayList();
    private final List classEnclosingMethodClass = new ArrayList();
    private final List classEnclosingMethodDesc = new ArrayList();
    private final List classSignature = new ArrayList();

    private final List classFileVersionMinor = new ArrayList();
    private final List classFileVersionMajor = new ArrayList();

    private final int[] class_field_count;
    private final CPNameAndType[][] field_descr;
    private final long[][] field_flags;
    private final List fieldConstantValueKQ = new ArrayList();
    private final List fieldSignature = new ArrayList();

    private final int[] class_method_count;
    private final CPNameAndType[][] method_descr;
    private final long[][] method_flags;
    private final List methodSignature = new ArrayList();
    private final List methodExceptionNumber = new ArrayList();
    private final List methodExceptionClasses = new ArrayList();

    private int[] codeHeaders;
    private final List codeMaxStack = new ArrayList();
    private final List codeMaxLocals = new ArrayList();
    private final List codeHandlerCount = new ArrayList();
    private final List codeHandlerStartP = new ArrayList();
    private final List codeHandlerEndPO = new ArrayList();
    private final List codeHandlerCatchPO = new ArrayList();
    private final List codeHandlerClass = new ArrayList();
    private final List codeFlags = new ArrayList();
    private final List codeLineNumberTableN = new ArrayList();
    private final List codeLineNumberTableBciP = new ArrayList();
    private final List codeLineNumberTableLine = new ArrayList();
    private final List codeLocalVariableTableN = new ArrayList();
    private final List codeLocalVariableTableBciP = new ArrayList();
    private final List codeLocalVariableTableSpanO = new ArrayList();
    private final List codeLocalVariableTableNameRU = new ArrayList();
    private final List codeLocalVariableTableTypeRS = new ArrayList();
    private final List codeLocalVariableTableSlot = new ArrayList();
    private final List codeLocalVariableTypeTableN = new ArrayList();
    private final List codeLocalVariableTypeTableBciP = new ArrayList();
    private final List codeLocalVariableTypeTableSpanO = new ArrayList();
    private final List codeLocalVariableTypeTableNameRU = new ArrayList();
    private final List codeLocalVariableTypeTableTypeRS = new ArrayList();
    private final List codeLocalVariableTypeTableSlot = new ArrayList();

    private final List tempFieldFlags = new ArrayList();
    private final List tempFieldDesc = new ArrayList();
    private final List tempMethodFlags = new ArrayList();
    private final List tempMethodDesc = new ArrayList();

    public ClassBands(SegmentHeader header, CpBands cpBands,
            AttributeDefinitionBands attrBands, int numClasses) {
        this.header = header;
        this.cpBands = cpBands;
        this.attrBands = attrBands;
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
    }

    private int index = 0;

    private int numMethodArgs = 0;

    public void addClass(int major, int flags, String className,
            String superName, String[] interfaces) {
        class_this[index] = cpBands.getCPClass(className);
        class_super[index] = cpBands.getCPClass(superName);
        class_interface_count[index] = interfaces.length;
        class_interface[index] = new CPClass[interfaces.length];
        for (int i = 0; i < interfaces.length; i++) {
            class_interface[index][i] = cpBands.getCPClass(interfaces[i]);
        }
        major_versions[index] = major;
        class_flags[index] = flags;
    }

    public void addField(int flags, String name, String desc, String signature,
            Object value) {
        CPMethodOrField field = cpBands.addCPField(class_this[index], name,
                desc);
        tempFieldDesc.add(field.getDesc());
        if (signature != null) {
            fieldSignature.add(cpBands.getCPSignature(signature));
            flags |= (1 << 19);
        }
        if (value != null) {
            fieldConstantValueKQ.add(cpBands.getConstant(value));
            flags |= (1 << 17);
        }
        tempFieldFlags.add(new Long(flags));
    }

    public void finaliseBands() {
        int defaultMajorVersion = header.getDefaultMajorVersion();
        for (int i = 0; i < class_flags.length; i++) {
            int major = major_versions[i];
            if (major != defaultMajorVersion) {
                class_flags[i] |= 1 << 24;
                classFileVersionMajor.add(new Integer(major));
                classFileVersionMinor.add(ZERO);
            }
        }
        // Calculate code headers
        codeHeaders = new int[codeFlags.size()];
        int removed = 0;
        for (int i = 0; i < codeHeaders.length; i++) {
            int numHandlers = ((Integer) codeHandlerCount.get(i - removed))
                    .intValue();
            int maxLocals = ((Integer) codeMaxLocals.get(i - removed))
                    .intValue();
            int maxStack = ((Integer) codeMaxStack.get(i - removed)).intValue();
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
            }
        }
    }

    public void pack(OutputStream out) throws IOException, Pack200Exception {
        int[] classThis = new int[class_this.length];
        for (int i = 0; i < classThis.length; i++) {
            classThis[i] = class_this[i].getIndex();
        }
        out.write(encodeBandInt("class_this", classThis, Codec.DELTA5));

        int[] classSuper = new int[class_super.length];
        for (int i = 0; i < classSuper.length; i++) {
            classSuper[i] = class_super[i].getIndex();
        }
        out.write(encodeBandInt("class_super", classSuper, Codec.DELTA5));
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
        out
                .write(encodeBandInt("class_interface", classInterface,
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
        out.write(encodeBandInt("fieldConstantValueKQ",
                cpEntryListToArray(fieldConstantValueKQ), Codec.UNSIGNED5));
        out.write(encodeBandInt("fieldSignature",
                cpEntryListToArray(fieldSignature), Codec.UNSIGNED5));
    }

    private void writeMethodAttributeBands(OutputStream out)
            throws IOException, Pack200Exception {
        out.write(encodeFlags("method_flags", method_flags, Codec.UNSIGNED5,
                Codec.UNSIGNED5, header.have_method_flags_hi()));
        out.write(encodeBandInt("methodExceptionNumber",
                listToArray(methodExceptionNumber), Codec.UNSIGNED5));
        out.write(encodeBandInt("methodExceptionClasses",
                cpEntryListToArray(methodExceptionClasses), Codec.UNSIGNED5));
        out.write(encodeBandInt("methodSignature",
                cpEntryListToArray(methodSignature), Codec.UNSIGNED5));
    }

    private void writeClassAttributeBands(OutputStream out) throws IOException,
            Pack200Exception {
        out.write(encodeFlags("class_flags", class_flags, Codec.UNSIGNED5,
                Codec.UNSIGNED5, header.have_class_flags_hi()));
        out.write(encodeBandInt("classSourceFile",
                cpEntryOrNullListToArray(classSourceFile), Codec.UNSIGNED5));
        out.write(encodeBandInt("classSignature",
                cpEntryListToArray(classSignature), Codec.UNSIGNED5));
        out.write(encodeBandInt("classFileVersionMinor",
                listToArray(classFileVersionMinor), Codec.UNSIGNED5));
        out.write(encodeBandInt("classFileVersionMajor",
                listToArray(classFileVersionMajor), Codec.UNSIGNED5));
    }

    private void writeCodeBands(OutputStream out) throws IOException,
            Pack200Exception {
        out.write(encodeBandInt("codeHeaders", codeHeaders, Codec.BYTE1));
        out.write(encodeBandInt("codeMaxStack", listToArray(codeMaxStack),
                Codec.UNSIGNED5));
        out.write(encodeBandInt("codeMaxLocals", listToArray(codeMaxLocals),
                Codec.UNSIGNED5));
        out.write(encodeBandInt("codeHandlerCount",
                listToArray(codeHandlerCount), Codec.UNSIGNED5));
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
                listToArray(codeLineNumberTableN), Codec.UNSIGNED5));
        out.write(encodeBandInt("code_LineNumberTable_bci_P",
                listToArray(codeLineNumberTableBciP), Codec.BCI5));
        out.write(encodeBandInt("code_LineNumberTable_line",
                listToArray(codeLineNumberTableLine), Codec.UNSIGNED5));
        out.write(encodeBandInt("code_LocalVariableTable_N",
                listToArray(codeLocalVariableTableN), Codec.UNSIGNED5));
        out.write(encodeBandInt("code_LocalVariableTable_bci_P",
                listToArray(codeLocalVariableTableBciP), Codec.BCI5));
        out.write(encodeBandInt("code_LocalVariableTable_span_O",
                listToArray(codeLocalVariableTableSpanO), Codec.BRANCH5)); // TODO:
                                                                            // Difference
        out.write(encodeBandInt("code_LocalVariableTable_name_RU",
                cpEntryListToArray(codeLocalVariableTableNameRU),
                Codec.UNSIGNED5));
        out.write(encodeBandInt("code_LocalVariableTable_type_RS",
                cpEntryListToArray(codeLocalVariableTableTypeRS),
                Codec.UNSIGNED5));
        out.write(encodeBandInt("code_LocalVariableTable_slot",
                listToArray(codeLocalVariableTableSlot), Codec.UNSIGNED5));
        out.write(encodeBandInt("code_LocalVariableTypeTable_N",
                listToArray(codeLocalVariableTypeTableN), Codec.UNSIGNED5));
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
                listToArray(codeLocalVariableTypeTableSlot), Codec.UNSIGNED5));

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
            methodExceptionNumber.add(new Integer(exceptions.length));
            for (int i = 0; i < exceptions.length; i++) {
                methodExceptionClasses.add(cpBands.getCPClass(exceptions[i]));
            }
            flags |= (1 << 18);
        }
        tempMethodFlags.add(new Long(flags));
        codeHandlerCount.add(ZERO);
        numMethodArgs = countArgs(desc);
    }

    protected static int countArgs(String descriptor) {
        int bra = descriptor.indexOf("(");
        int ket = descriptor.indexOf(")");
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
        codeMaxStack.add(new Integer(maxStack));
        if ((newFlag.longValue() & (1 << 3)) == 0) { // not static
            maxLocals--; // minus 'this' local
        }
        maxLocals -= numMethodArgs;
        codeMaxLocals.add(new Integer(maxLocals));
    }

    public void addCode() {
        codeFlags.add(new Long(0));
    }

    public void addHandler(Label start, Label end, Label handler, String type) {
        Long latestMethodFlag = (Long) tempMethodFlags.get(tempMethodFlags
                .size() - 1);
        if ((latestMethodFlag.longValue() & (1 << 18)) == 0) {
            tempMethodFlags.remove(tempMethodFlags.size() - 1);
            tempMethodFlags.add(new Long(latestMethodFlag.intValue()
                    | (1 << 18)));
        }
        Integer handlers = (Integer) codeHandlerCount.remove(codeHandlerCount
                .size() - 1);
        codeHandlerCount.add(new Integer(handlers.intValue() + 1));
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
            codeLineNumberTableN.add(new Integer(1));
        } else {
            Integer numLines = (Integer) codeLineNumberTableN
                    .remove(codeLineNumberTableN.size() - 1);
            codeLineNumberTableN.add(new Integer(numLines.intValue() + 1));
        }
        codeLineNumberTableLine.add(new Integer(line));
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
                codeLocalVariableTypeTableN.add(new Integer(1));
            } else {
                Integer numLocals = (Integer) codeLocalVariableTypeTableN
                        .remove(codeLocalVariableTypeTableN.size() - 1);
                codeLocalVariableTypeTableN.add(new Integer(numLocals
                        .intValue() + 1));
            }
            codeLocalVariableTypeTableBciP.add(start);
            codeLocalVariableTypeTableSpanO.add(end);
            codeLocalVariableTypeTableNameRU.add(cpBands.getCPUtf8(name));
            codeLocalVariableTypeTableTypeRS.add(cpBands
                    .getCPSignature(signature));
            codeLocalVariableTypeTableSlot.add(new Integer(indx));
        }
        // LocalVariableTable attribute
        Long latestCodeFlag = (Long) codeFlags.get(codeFlags.size() - 1);
        if ((latestCodeFlag.intValue() & (1 << 2)) == 0) {
            codeFlags.remove(codeFlags.size() - 1);
            codeFlags.add(new Long(latestCodeFlag.intValue() | (1 << 2)));
            codeLocalVariableTableN.add(new Integer(1));
        } else {
            Integer numLocals = (Integer) codeLocalVariableTableN
                    .remove(codeLocalVariableTableN.size() - 1);
            codeLocalVariableTableN.add(new Integer(numLocals.intValue() + 1));
        }
        codeLocalVariableTableBciP.add(start);
        codeLocalVariableTableSpanO.add(end);
        codeLocalVariableTableNameRU.add(cpBands.getCPUtf8(name));
        codeLocalVariableTableTypeRS.add(cpBands.getCPSignature(desc));
        codeLocalVariableTableSlot.add(new Integer(indx));
    }

    public void doBciRenumbering(List bciRenumbering, Map labelsToOffsets) {
        renumberBci(codeLineNumberTableBciP, bciRenumbering, labelsToOffsets);
        renumberBci(codeLocalVariableTableBciP, bciRenumbering, labelsToOffsets);
        renumberBci(codeLocalVariableTableSpanO, bciRenumbering,
                labelsToOffsets);
        renumberBci(codeLocalVariableTypeTableBciP, bciRenumbering,
                labelsToOffsets);
        renumberBci(codeLocalVariableTypeTableSpanO, bciRenumbering,
                labelsToOffsets);
    }

    private void renumberBci(List list, List bciRenumbering, Map labelsToOffsets) {
        for (int i = list.size() - 1; i >= 0; i--) {
            Object label = list.get(i);
            if (label instanceof Integer) {
                break;
            } else if (label instanceof Label) {
                list.remove(i);
                Integer offset = (Integer) labelsToOffsets.get(label);
                list.add(i, bciRenumbering.get(offset.intValue()));
            }
        }
    }
}
