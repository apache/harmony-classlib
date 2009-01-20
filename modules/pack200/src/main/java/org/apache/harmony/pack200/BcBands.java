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
import java.util.List;
import java.util.Map;

import org.objectweb.asm.Label;

/**
 * Bytecode bands
 */
public class BcBands extends BandSet {

    private static final Integer MULTIANEWARRAY = new Integer(197);
    private static final Integer ALOAD_0 = new Integer(42);
    private final CpBands cpBands;
    private final Segment segment;

    public BcBands(CpBands cpBands, Segment segment) {
        this.cpBands = cpBands;
        this.segment = segment;
    }

    private final List bcCodes = new ArrayList();
    private final List bcCaseCount = new ArrayList();
    private final List bcCaseValue = new ArrayList();
    private final List bcByte = new ArrayList();
    private final List bcShort = new ArrayList();
    private final List bcLocal = new ArrayList();
    private final List bcLabel = new ArrayList();
    private final List bcIntref = new ArrayList();
    private final List bcFloatRef = new ArrayList();
    private final List bcLongRef = new ArrayList();
    private final List bcDoubleRef = new ArrayList();
    private final List bcStringRef = new ArrayList();
    private final List bcClassRef = new ArrayList();
    private final List bcFieldRef = new ArrayList();
    private final List bcMethodRef = new ArrayList();
    private final List bcIMethodRef = new ArrayList();
    private List bcThisField = new ArrayList();
    private final List bcSuperField = new ArrayList();
    private List bcThisMethod = new ArrayList();
    private final List bcSuperMethod = new ArrayList();
    private final List bcInitRef = new ArrayList();

    private String currentClass;
    private String superClass;
    private static final Integer WIDE = new Integer(196);
    private static final Integer INVOKEINTERFACE = new Integer(185);
    private static final Integer TABLESWITCH = new Integer(170);
    private static final Integer IINC = new Integer(132);
    private static final Integer LOOKUPSWITCH = new Integer(171);
    private static final Integer endMarker = new Integer(255);

    private final List bciRenumbering = new ArrayList();
    private final Map labelsToOffsets = new HashMap();
    private int byteCodeOffset;
    private int renumberedOffset;
    private final List bcLabelRelativeOffsets = new ArrayList();

    public void setCurrentClass(String name) {
        currentClass = name;
    }

    public void setSuperClass(String superName) {
        superClass = superName;
    }

    public void finaliseBands() {
        bcThisField = getIndexInClass(bcThisField);
        bcThisMethod = getIndexInClass(bcThisMethod);
    }

    public void pack(OutputStream out) throws IOException, Pack200Exception {
        out.write(encodeBandInt("bcCodes", listToArray(bcCodes), Codec.BYTE1));
        out.write(encodeBandInt("bcCaseCount", listToArray(bcCaseCount),
                Codec.UNSIGNED5));
        out.write(encodeBandInt("bcCaseValue", listToArray(bcCaseValue),
                Codec.DELTA5));
        out.write(encodeBandInt("bcByte", listToArray(bcByte), Codec.BYTE1));
        out.write(encodeBandInt("bcShort", listToArray(bcShort), Codec.DELTA5));
        out.write(encodeBandInt("bcLocal", listToArray(bcLocal),
                Codec.UNSIGNED5));
        out
                .write(encodeBandInt("bcLabel", listToArray(bcLabel),
                        Codec.BRANCH5));
        out.write(encodeBandInt("bcIntref", cpEntryListToArray(bcIntref),
                Codec.DELTA5));
        out.write(encodeBandInt("bcFloatRef", cpEntryListToArray(bcFloatRef),
                Codec.DELTA5));
        out.write(encodeBandInt("bcLongRef", cpEntryListToArray(bcLongRef),
                Codec.DELTA5));
        out.write(encodeBandInt("bcDoubleRef", cpEntryListToArray(bcDoubleRef),
                Codec.DELTA5));
        out.write(encodeBandInt("bcStringRef", cpEntryListToArray(bcStringRef),
                Codec.DELTA5));
        out.write(encodeBandInt("bcClassRef",
                cpEntryOrNullListToArray(bcClassRef), Codec.UNSIGNED5));
        out.write(encodeBandInt("bcFieldRef", cpEntryListToArray(bcFieldRef),
                Codec.DELTA5));
        out.write(encodeBandInt("bcMethodRef", cpEntryListToArray(bcMethodRef),
                Codec.UNSIGNED5));
        out.write(encodeBandInt("bcIMethodRef",
                cpEntryListToArray(bcIMethodRef), Codec.DELTA5));
        out.write(encodeBandInt("bcThisField", listToArray(bcThisField),
                Codec.UNSIGNED5));
        out.write(encodeBandInt("bcSuperField", listToArray(bcSuperField),
                Codec.UNSIGNED5));
        out.write(encodeBandInt("bcThisMethod", listToArray(bcThisMethod),
                Codec.UNSIGNED5));
        out.write(encodeBandInt("bcSuperMethod",
                listToArray(bcSuperMethod), Codec.UNSIGNED5));
        out.write(encodeBandInt("bcInitRef", listToArray(bcInitRef),
                Codec.UNSIGNED5));
        // out.write(encodeBandInt(cpEntryListToArray(bcEscRef),
        // Codec.UNSIGNED5));
        // out.write(encodeBandInt(listToArray(bcEscRefSize), Codec.UNSIGNED5));
        // out.write(encodeBandInt(listToArray(bcEscSize), Codec.UNSIGNED5));
        // out.write(encodeBandInt(listToArray(bcEscByte), Codec.BYTE1));
    }

    private List getIndexInClass(List cPMethodOrFieldList) {
        List indices = new ArrayList(cPMethodOrFieldList.size());
        for (int i = 0; i < cPMethodOrFieldList.size(); i++) {
            CPMethodOrField cpMF = (CPMethodOrField) cPMethodOrFieldList.get(i);
            indices.add(new Integer(cpMF.getIndexInClass()));
        }
        return indices;
    }

    public void visitEnd() {
        for (int i = 0; i < bciRenumbering.size(); i++) {
            if (bciRenumbering.get(i) == null) {
                bciRenumbering.remove(i);
                bciRenumbering.add(i, new Integer(++renumberedOffset));
            }
        }
        if (renumberedOffset != 0) {
            if(renumberedOffset + 1 != bciRenumbering.size()) {
                throw new RuntimeException("Mistake made with renumbering");
            }
            for (int i = bcLabel.size() - 1; i >= 0; i--) {
                Object label = bcLabel.get(i);
                if (label instanceof Integer) {
                    break;
                } else if (label instanceof Label) {
                    bcLabel.remove(i);
                    Integer offset = (Integer) labelsToOffsets.get(label);
                    Integer relativeOffset = (Integer) bcLabelRelativeOffsets.get(i);
                    bcLabel.add(i, new Integer(((Integer)bciRenumbering.get(offset.intValue())).intValue() - ((Integer)bciRenumbering.get(relativeOffset.intValue())).intValue()));
                }
            }
            bcCodes.add(endMarker);
            segment.getClassBands().doBciRenumbering(bciRenumbering,
                    labelsToOffsets);
            bciRenumbering.clear();
            labelsToOffsets.clear();
            byteCodeOffset = 0;
            renumberedOffset = 0;
        }
    }

    public void visitLabel(Label label) {
        labelsToOffsets.put(label, new Integer(byteCodeOffset));
    }

    public void visitFieldInsn(int opcode, String owner, String name,
            String desc) {
        byteCodeOffset += 3;
        updateRenumbering();
        boolean aload_0 = false;
        if (bcCodes.size() > 0
                && ((Integer) bcCodes.get(bcCodes.size() - 1)).equals(ALOAD_0)) {
            bcCodes.remove(bcCodes.size() - 1);
            aload_0 = true;
        }
        CPMethodOrField cpField = cpBands.getCPField(owner, name, desc);
        if (aload_0) {
            opcode += 7;
        }
        if (owner.equals(currentClass)) {
            opcode += 24; // change to getstatic_this, putstatic_this etc.
            bcThisField.add(cpField);
//        } else if (owner.equals(superClass)) {
//            opcode += 38; // change to getstatic_super etc.
//            bcSuperField.add(cpField);
        } else {
            if (aload_0) {
                opcode -= 7;
                bcCodes.add(ALOAD_0); // add aload_0 back in because
                // there's no special rewrite in
                // this case.
            }
            bcFieldRef.add(cpField);
        }
        aload_0 = false;
        bcCodes.add(new Integer(opcode));
    }

    private void updateRenumbering() {
        if(bciRenumbering.isEmpty()) {
            bciRenumbering.add(new Integer(0));
        }
        renumberedOffset ++;
        for (int i = bciRenumbering.size(); i < byteCodeOffset; i++) {
            bciRenumbering.add(null);
        }
        bciRenumbering.add(new Integer(renumberedOffset));
    }

    public void visitIincInsn(int var, int increment) {
        if (increment > Byte.MAX_VALUE) {
            byteCodeOffset += 6;
            bcCodes.add(WIDE);
            bcCodes.add(IINC);
            bcLocal.add(new Integer(var));
            bcShort.add(new Integer(increment));
        } else {
            byteCodeOffset += 3;
            bcCodes.add(IINC);
            bcLocal.add(new Integer(var));
            bcByte.add(new Integer(increment & 0xFF));
        }
        updateRenumbering();
    }

    public void visitInsn(int opcode) {
        if (opcode >= 202) {
            throw new RuntimeException(
                    "Non-standard bytecode instructions not supported");
        } else {
            bcCodes.add(new Integer(opcode));
            byteCodeOffset++;
            updateRenumbering();
        }
    }

    public void visitIntInsn(int opcode, int operand) {
        switch (opcode) {
        case 17: // sipush
            bcCodes.add(new Integer(opcode));
            bcShort.add(new Integer(operand));
            byteCodeOffset += 3;
            break;
        case 16: // bipush
        case 188: // newarray
            bcCodes.add(new Integer(opcode));
            bcByte.add(new Integer(operand & 0xFF));
            byteCodeOffset += 2;
        }
        updateRenumbering();
    }

    public void visitJumpInsn(int opcode, Label label) {
        bcCodes.add(new Integer(opcode));
        bcLabel.add(label);
        bcLabelRelativeOffsets.add(new Integer(byteCodeOffset));
        byteCodeOffset += 3;
        updateRenumbering();
    }

    public void visitLdcInsn(Object cst) {
        CPConstant constant = cpBands.getConstant(cst);
        if (segment.lastConstantHadWideIndex() || constant instanceof CPLong
                || constant instanceof CPDouble) {
            byteCodeOffset += 3;
            if (constant instanceof CPInt) {
                bcCodes.add(new Integer(237)); // ildc_w
                bcIntref.add(constant);
            } else if (constant instanceof CPFloat) {
                bcCodes.add(new Integer(238)); // fldc
                bcFloatRef.add(constant);
            } else if (constant instanceof CPLong) {
                bcCodes.add(new Integer(20)); // lldc2_w
                bcLongRef.add(constant);
            } else if (constant instanceof CPDouble) {
                bcCodes.add(new Integer(239)); // dldc2_w
                bcDoubleRef.add(constant);
            } else if (constant instanceof CPString) {
                bcCodes.add(new Integer(19)); // aldc
                bcStringRef.add(constant);
            } else if (constant instanceof CPClass) {
                bcCodes.add(new Integer(236)); // cldc
                bcClassRef.add(constant);
            } else {
                throw new RuntimeException("Constant should not be null");
            }
        } else {
            byteCodeOffset += 2;
            if (constant instanceof CPInt) {
                bcCodes.add(new Integer(234)); // ildc
                bcIntref.add(constant);
            } else if (constant instanceof CPFloat) {
                bcCodes.add(new Integer(235)); // fldc
                bcFloatRef.add(constant);
            } else if (constant instanceof CPString) {
                bcCodes.add(new Integer(18)); // aldc
                bcStringRef.add(constant);
            } else if (constant instanceof CPClass) {
                bcCodes.add(new Integer(233)); // cldc
                bcClassRef.add(constant);
            }
        }
        updateRenumbering();
    }

    public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
        bcCodes.add(LOOKUPSWITCH);
        bcLabel.add(dflt);
        bcLabelRelativeOffsets.add(new Integer(byteCodeOffset));
        bcCaseCount.add(new Integer(keys.length));
        for (int i = 0; i < labels.length; i++) {
            bcCaseValue.add(new Integer(keys[i]));
            bcLabel.add(labels[i]);
            bcLabelRelativeOffsets.add(new Integer(byteCodeOffset));
        }
        int padding = (byteCodeOffset + 1) % 4 == 0 ? 0 : 4 - ((byteCodeOffset + 1) % 4);
        byteCodeOffset += padding + 8 + 8 * keys.length;
        updateRenumbering();
    }

    public void visitMethodInsn(int opcode, String owner, String name,
            String desc) {
        byteCodeOffset += 3;
        updateRenumbering();
        switch (opcode) {
        case 182: // invokevirtual
        case 183: // invokespecial
        case 184: // invokestatic
            boolean aload_0 = false;
            if (bcCodes.size() > 0
                    && ((Integer) bcCodes.get(bcCodes.size() - 1))
                            .equals(ALOAD_0)) {
                bcCodes.remove(bcCodes.size() - 1);
                aload_0 = true;
                opcode += 7;
            }

//            if (opcode == 183 && name.equals("<init>") && !aload_0
//                    && owner.equals(currentClass)) {
//                opcode = 230;
//            } else if (opcode == 183 && name.equals("<init>") && !aload_0
//                    && owner.equals(superClass)) {
//                opcode = 231;
//                // TODO: 232
//            } else
            if (owner.equals(currentClass)) {
                opcode += 24; // change to invokevirtual_this,
                // invokespecial_this etc.
                bcThisMethod.add(cpBands.getCPMethod(owner, name, desc));
//            } else if (owner.equals(superClass)) { // TODO
//                opcode += 38; // change to invokevirtual_super,
//                // invokespecial_super etc.
//                bcSuperMethod.add(cpBands.getCPMethod(owner, name, desc));
            } else {
                if (aload_0) {
                    opcode -= 7;
                    bcCodes.add(ALOAD_0); // add aload_0 back in
                    // because there's no
                    // special rewrite in this
                    // case.
                }
                bcMethodRef.add(cpBands.getCPMethod(owner, name, desc));
            }
            bcCodes.add(new Integer(opcode));
            break;
        case 185: // invokeinterface
            CPMethodOrField cpIMethod = cpBands.getCPIMethod(owner, name, desc);
            bcIMethodRef.add(cpIMethod);
            bcCodes.add(INVOKEINTERFACE);
            break;
        }
    }

    public void visitMultiANewArrayInsn(String desc, int dimensions) {
        byteCodeOffset += 4;
        updateRenumbering();
        bcCodes.add(MULTIANEWARRAY);
        bcClassRef.add(cpBands.getCPClass(desc));
        bcByte.add(new Integer(dimensions & 0xFF));
    }

    public void visitTableSwitchInsn(int min, int max, Label dflt,
            Label[] labels) {
        bcCodes.add(TABLESWITCH);
        bcLabel.add(dflt);
        bcLabelRelativeOffsets.add(new Integer(byteCodeOffset));
        bcCaseValue.add(new Integer(min));
        int count = labels.length;
        bcCaseCount.add(new Integer(count));
        for (int i = 0; i < count; i++) {
            bcLabel.add(labels[i]);
            bcLabelRelativeOffsets.add(new Integer(byteCodeOffset));
        }
        int padding = (byteCodeOffset + 1) % 4 == 0 ? 0 : 4 - ((byteCodeOffset + 1) % 4);
        byteCodeOffset+= (padding + 12 + 4 * labels.length);
        updateRenumbering();
    }

    public void visitTypeInsn(int opcode, String type) {
        // NEW, ANEWARRAY, CHECKCAST or INSTANCEOF
        byteCodeOffset += 3;
        updateRenumbering();
        bcCodes.add(new Integer(opcode));
        bcClassRef.add(cpBands.getCPClass(type));
    }

    public void visitVarInsn(int opcode, int var) {
        // ILOAD, LLOAD, FLOAD, DLOAD, ALOAD, ISTORE, LSTORE, FSTORE, DSTORE, ASTORE or RET
        if (var > Byte.MAX_VALUE) {
            byteCodeOffset += 4;
            bcCodes.add(WIDE);
            bcCodes.add(new Integer(opcode));
            bcLocal.add(new Integer(var));
        } else {
            if(var > 3 || opcode == 169 /* RET */) {
                byteCodeOffset += 2;
                bcCodes.add(new Integer(opcode));
                bcLocal.add(new Integer(var));
            } else {
                byteCodeOffset +=1;
                switch(opcode) {
                case 21: // ILOAD
                case 54: // ISTORE
                    bcCodes.add(new Integer(opcode + 5 + var));
                    break;
                case 22: // LLOAD
                case 55: // LSTORE
                    bcCodes.add(new Integer(opcode + 8 + var));
                    break;
                case 23: // FLOAD
                case 56: // FSTORE
                    bcCodes.add(new Integer(opcode + 11 + var));
                    break;
                case 24: // DLOAD
                case 57: // DSTORE
                    bcCodes.add(new Integer(opcode + 14 + var));
                    break;
                case 25: // A_LOAD
                case 58: // A_STORE
                    bcCodes.add(new Integer(opcode + 17 + var));
                    break;
                }
            }
        }
        updateRenumbering();
    }

}
