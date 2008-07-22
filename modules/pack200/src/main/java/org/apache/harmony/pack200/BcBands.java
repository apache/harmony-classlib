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

import org.apache.bcel.classfile.Code;
import org.apache.bcel.classfile.Constant;
import org.apache.bcel.classfile.ConstantClass;
import org.apache.bcel.classfile.ConstantDouble;
import org.apache.bcel.classfile.ConstantFieldref;
import org.apache.bcel.classfile.ConstantFloat;
import org.apache.bcel.classfile.ConstantInteger;
import org.apache.bcel.classfile.ConstantInterfaceMethodref;
import org.apache.bcel.classfile.ConstantLong;
import org.apache.bcel.classfile.ConstantMethodref;
import org.apache.bcel.classfile.ConstantNameAndType;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.ConstantString;

/**
 * Bytecode bands
 */
public class BcBands extends BandSet {

    private CpBands cpBands;

    public BcBands(CpBands cpBands) {
        this.cpBands = cpBands;
    }

    private List bcCodes = new ArrayList();
    private List bcCaseCount = new ArrayList();
    private List bcCaseValue = new ArrayList();
    private List bcByte = new ArrayList();
    private List bcShort = new ArrayList();
    private List bcLocal = new ArrayList();
    private List bcLabel = new ArrayList();
    private List bcIntref = new ArrayList();
    private List bcFloatRef = new ArrayList();
    private List bcLongRef = new ArrayList();
    private List bcDoubleRef = new ArrayList();
    private List bcStringRef = new ArrayList();
    private List bcClassRef = new ArrayList();
    private List bcFieldRef = new ArrayList();
    private List bcMethodRef = new ArrayList();
    private List bcIMethodRef = new ArrayList();
    private List bcThisField = new ArrayList();
    private List bcSuperField = new ArrayList();
    private List bcThisMethod = new ArrayList();
    private List bcSuperMethod = new ArrayList();
    private List bcInitRef = new ArrayList();
//    private List bcEscRef = new ArrayList();
//    private List bcEscRefSize = new ArrayList();
//    private List bcEscSize = new ArrayList();
//    private List bcEscByte = new ArrayList();

    public void addCode(Code obj, String thisClass, String superClass) {
        ConstantPool cp = obj.getConstantPool();
        byte[] bytecodes = obj.getCode();
        boolean aload_0 = false;
        boolean wide = false;
        for (int i = 0; i < bytecodes.length; i++) {
            int bytecode = 0xff & bytecodes[i];
            switch (bytecode) {
            case 16: // bipush
            case 17: // sipush
                bcCodes.add(new Integer(bytecode));
                byte b1 = bytecodes[++i];
                byte b2 = bytecodes[++i];
                short s = (short) (b1 << 8 | b2);
                bcShort.add(new Integer(s));
                break;
            case 18: // ldc
                Constant constant = cp.getConstant(bytecodes[++i] & 0xFF);
                if (constant instanceof ConstantInteger) {
                    bcCodes.add(new Integer(234)); // ildc
                    bcIntref.add(cpBands.getCPConstant(constant, cp));
                } else if (constant instanceof ConstantFloat) {
                    bcCodes.add(new Integer(235)); // fldc
                    bcFloatRef.add(cpBands.getCPConstant(constant, cp));
                } else if (constant instanceof ConstantString) {
                    bcCodes.add(new Integer(18)); // aldc
                    bcStringRef.add(cpBands.getCPConstant(constant, cp));
                } else if (constant instanceof ConstantClass) {
                    bcCodes.add(new Integer(233)); // cldc
                    bcClassRef.add(cpBands.getCPConstant(constant, cp));
                }
                break;
            case 19: // aldc_w
                b1 = bytecodes[++i];
                b2 = bytecodes[++i];
                int index = b1 << 8 | b2;
                constant = cp.getConstant(index);
                if (constant instanceof ConstantInteger) {
                    bcCodes.add(new Integer(237)); // ildc_w
                    bcIntref.add(cpBands.getCPConstant(constant, cp));
                } else if (constant instanceof ConstantFloat) {
                    bcCodes.add(new Integer(238)); // fldc_w
                    bcFloatRef.add(cpBands.getCPConstant(constant, cp));
                } else if (constant instanceof ConstantString) {
                    bcCodes.add(new Integer(19)); // aldc_w
                    bcStringRef.add(cpBands.getCPConstant(constant, cp));
                } else if (constant instanceof ConstantClass) {
                    bcCodes.add(new Integer(236)); // cldc_w
                    bcClassRef.add(cpBands.getCPConstant(constant, cp));
                }
                break;
            case 20: // ldc2_w
                b1 = bytecodes[++i];
                b2 = bytecodes[++i];
                index = b1 << 8 | b2;
                constant = cp.getConstant(index);
                if (constant instanceof ConstantLong) {
                    bcCodes.add(new Integer(20)); // lldc2_w
                    bcLongRef.add(cpBands.getCPConstant(constant, cp));
                } else if (constant instanceof ConstantDouble) {
                    bcCodes.add(new Integer(239)); // dldc2_w
                    bcDoubleRef.add(cpBands.getCPConstant(constant, cp));
                }
                break;
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
                bcCodes.add(new Integer(bytecode));
                if(wide) {
                    b1 = bytecodes[++i];
                    b2 = bytecodes[++i];
                    index = b1 << 8 | b2;
                    bcLocal.add(new Integer(index));
                    wide = false;
                } else {
                    bcLocal.add(new Integer(0xff & bytecodes[++i]));
                }
                break;
            case 42:
                int next = 0;
                if(bytecodes.length >= i) {
                    next = bytecodes[i+1] & 0xFF;
                }
                if(next >= 178 && next <= 184) {
                    aload_0 = true;
                } else {
                    bcCodes.add(new Integer(42));
                }
                break;
            case 132: // iinc
                bcCodes.add(new Integer(132));
                if(wide) {
                    b1 = bytecodes[++i];
                    b2 = bytecodes[++i];
                    index = b1 << 8 | b2;
                    b1 = bytecodes[++i];
                    b2 = bytecodes[++i];
                    short cnst = (short) (b1 << 8 | b2);
                    bcLocal.add(new Integer(index));
                    bcShort.add(new Integer(cnst));
                    wide = false;
                } else {
                    index = bytecodes[++i];
                    byte cnst = bytecodes[++i];
                    bcLocal.add(new Integer(index));
                    bcByte.add(new Integer(cnst));
                }
                break;
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
            case 159:
            case 160:
            case 161:
            case 162:
            case 163:
            case 164:
            case 165:
            case 166:
            case 198:
            case 199:
                bcCodes.add(new Integer(bytecode));
                b1 = bytecodes[++i];
                b2 = bytecodes[++i];
                index = b1 << 8 | b2;
                bcLabel.add(new Integer(index));
                break;
            case 167: // goto
            case 168: // jsr
                bcCodes.add(new Integer(bytecode));
                b1 = bytecodes[++i];
                b2 = bytecodes[++i];
                int offset = b1 << 8 | b2;
                bcLabel.add(new Integer(offset));
                break;
            case 169: // ret
                bcCodes.add(new Integer(bytecode));
                if(wide) {
                    b1 = bytecodes[++i];
                    b2 = bytecodes[++i];
                    index = b1 << 8 | b2;
                    bcLocal.add(new Integer(index));
                    wide = false;
                } else {
                    bcLocal.add(new Integer(0xff & bytecodes[++i]));
                }
                break;
            case 170: // tableswitch
                bcCodes.add(new Integer(bytecode));
                int padding = (i + 1) % 4 == 0 ? 0 : 4 - i + 1;
                i+= padding;
                b1 = bytecodes[i];
                b2 = bytecodes[++i];
                byte b3 = bytecodes[++i];
                byte b4 = bytecodes[++i];
                int defaultValue = (b1 << 24) | (b2 << 16) | (b3 << 8) | b4;
                b1 = bytecodes[++i];
                b2 = bytecodes[++i];
                b3 = bytecodes[++i];
                b4 = bytecodes[++i];
                int low = (b1 << 24) | (b2 << 16) | (b3 << 8) | b4;
                b1 = bytecodes[++i];
                b2 = bytecodes[++i];
                b3 = bytecodes[++i];
                b4 = bytecodes[++i];
                int high = (b1 << 24) | (b2 << 16) | (b3 << 8) | b4;
                bcLabel.add(new Integer(defaultValue));
                bcCaseValue.add(new Integer(low));
                int count = high - low + 1;
                bcCaseCount.add(new Integer(count));
                for (int j = 0; j < count; j++) {
                    b1 = bytecodes[++i];
                    b2 = bytecodes[++i];
                    b3 = bytecodes[++i];
                    b4 = bytecodes[++i];
                    int label = (b1 << 24) | (b2 << 16) | (b3 << 8) | b4;
                    bcLabel.add(new Integer(label));
                }
                break;
            case 171: // lookupswitch
                bcCodes.add(new Integer(bytecode));
                padding = (i + 1) % 4 == 0 ? 0 : 4 - i + 1;
                i+= padding;
                b1 = bytecodes[i];
                b2 = bytecodes[++i];
                b3 = bytecodes[++i];
                b4 = bytecodes[++i];
                defaultValue = (b1 << 24) | (b2 << 16) | (b3 << 8) | b4;
                bcLabel.add(new Integer(defaultValue));
                b1 = bytecodes[++i];
                b2 = bytecodes[++i];
                b3 = bytecodes[++i];
                b4 = bytecodes[++i];
                int npairs = (b1 << 24) | (b2 << 16) | (b3 << 8) | b4;
                bcCaseCount.add(new Integer(npairs));
                for (int j = 0; j < npairs; j++) {
                    b1 = bytecodes[++i];
                    b2 = bytecodes[++i];
                    b3 = bytecodes[++i];
                    b4 = bytecodes[++i];
                    int caseValue = (b1 << 24) | (b2 << 16) | (b3 << 8) | b4;
                    bcCaseValue.add(new Integer(caseValue));
                    b1 = bytecodes[++i];
                    b2 = bytecodes[++i];
                    b3 = bytecodes[++i];
                    b4 = bytecodes[++i];
                    int label = (b1 << 24) | (b2 << 16) | (b3 << 8) | b4;
                    bcLabel.add(new Integer(label));
                }
                break;
            case 178: // getstatic
            case 179: // putstatic
            case 180: // getfield
            case 181: // putfield
                b1 = bytecodes[++i];
                b2 = bytecodes[++i];
                index = b1 << 8 | b2;
                ConstantFieldref cfr = (ConstantFieldref) cp.getConstant(index);
                String className = cfr.getClass(cp);
                CPMethodOrField cpField = cpBands.getCPMethodOrField(cfr);
                if(aload_0) {
                    bytecode += 7;
                }
                if(className.equals(thisClass)) {
                    bytecode += 24; // change to getstatic_this, putstatic_this etc.
                    bcThisField.add(cpField);
                } else if (className.equals(superClass)){
                    bytecode += 38; // change to getstatic_super etc.
                    bcSuperField.add(cpField);
                } else {
                    if(aload_0) {
                        bytecode -= 7;
                        bcCodes.add(new Integer(42)); // add aload_0 back in because there's no special rewrite in this case.
                    }
                    bcFieldRef.add(cpField);
                }
                aload_0 = false;
                bcCodes.add(new Integer(bytecode));
                break;
            case 182: // invokevirtual
            case 183: // invokespecial
            case 184: // invokestatic
                b1 = bytecodes[++i];
                b2 = bytecodes[++i];
                index = b1 << 8 | b2;
                ConstantMethodref cmr = (ConstantMethodref) cp.getConstant(index);
                className = cmr.getClass(cp);
                CPMethodOrField cpMethod = cpBands.getCPMethodOrField(cmr);
                if(aload_0) {
                    bytecode += 7;
                }
                if(className.equals(thisClass)) {
                    if(bytecode == 183) { // invokespecial
                        ConstantNameAndType cnat = (ConstantNameAndType) cp.getConstant(cmr.getNameAndTypeIndex());
                        String name = cnat.getName(cp);
                        if(name.equals("this")) {

                        }
                    }
                    bytecode += 24; // change to invokevirtual_this, invokespecial_this etc.
                    bcThisMethod.add(cpMethod);
                } else if(className.equals(superClass)) {
                    bytecode += 38; // change to invokevirtual_super, invokespecial_super etc.
                    bcSuperMethod.add(cpMethod);
                } else {
                    if(aload_0) {
                        bytecode -= 7;
                        bcCodes.add(new Integer(42)); // add aload_0 back in because there's no special rewrite in this case.
                    }
                    bcMethodRef.add(cpMethod);
                }
                aload_0 = false;
                bcCodes.add(new Integer(bytecode));
                break;
            case 185: // invokeinterface
                b1 = bytecodes[++i];
                b2 = bytecodes[++i];
                index = b1 << 8 | b2;
                ConstantInterfaceMethodref cmir = (ConstantInterfaceMethodref) cp.getConstant(index);
                className = cmir.getClass(cp);
                CPMethodOrField cpIMethod = cpBands.getCPMethodOrField(cmir);
                bcIMethodRef.add(cpIMethod);
                bcCodes.add(new Integer(bytecode));
                i+= 2; // ignore count and zero fields as this can be recreated by the decompressor
                break;
            case 187: // new
            case 189: // anewarray
            case 192: // checkcast
            case 193: // instanceof
                bcCodes.add(new Integer(bytecode));
                b1 = bytecodes[++i];
                b2 = bytecodes[++i];
                index = b1 << 8 | b2;
                ConstantClass constantClass = (ConstantClass) cp.getConstant(index);
                bcClassRef.add(cpBands.getCPClass(constantClass.getBytes(cp)));
                break;
            case 188: // newarray
                bcCodes.add(new Integer(bytecode));
                bcByte.add(new Integer(0xff & bytecodes[++i]));
                break;
            case 196: // wide
                bcCodes.add(new Integer(196));
                wide = true;
            case 197: // multianewarray
                bcCodes.add(new Integer(bytecode));
                b1 = bytecodes[++i];
                b2 = bytecodes[++i];
                index = b1 << 8 | b2;
                constantClass = (ConstantClass) cp.getConstant(index);
                bcClassRef.add(cpBands.getCPClass(constantClass.getBytes(cp)));
                byte dimensions = bytecodes[++i];
                bcByte.add(new Integer(0xff & dimensions));
                break;
            case 200: // goto_w
            case 201: // jsr_w
                bcCodes.add(new Integer(bytecode));
                b1 = bytecodes[++i];
                b2 = bytecodes[++i];
                b3 = bytecodes[++i];
                b4 = bytecodes[++i];
                offset = b1 << 24 | b2 << 16 | b3 << 8 | b4;
                bcLabel.add(new Integer(offset));
                break;
//   TODO         case 230: // invokespecial_this_init
//            case 231: // invokespecial_super_init
//            case 232: // invokespecial_new_init
//                bcInitRefCount++;
//                break;
            default:
                if(bytecode >= 202) {
                    throw new RuntimeException("Non-standard bytecode instructions not supported");
                } else {
                    bcCodes.add(new Integer(bytecode));
                }
            }
        }
    }

    public void finaliseBands() {

    }

    public void pack(OutputStream out) throws IOException, Pack200Exception {
        out.write(encodeBandInt(listToArray(bcCodes), Codec.BYTE1));
        out.write(encodeBandInt(listToArray(bcCaseCount), Codec.UNSIGNED5));
        out.write(encodeBandInt(listToArray(bcCaseValue), Codec.DELTA5));
        out.write(encodeBandInt(listToArray(bcByte), Codec.BYTE1));
        out.write(encodeBandInt(listToArray(bcShort), Codec.DELTA5));
        out.write(encodeBandInt(listToArray(bcLocal), Codec.UNSIGNED5));
        out.write(encodeBandInt(listToArray(bcLabel), Codec.BRANCH5));
        out.write(encodeBandInt(cpEntryListToArray(bcIntref), Codec.DELTA5));
        out.write(encodeBandInt(cpEntryListToArray(bcFloatRef), Codec.DELTA5));
        out.write(encodeBandInt(cpEntryListToArray(bcLongRef), Codec.DELTA5));
        out.write(encodeBandInt(cpEntryListToArray(bcDoubleRef), Codec.DELTA5));
        out.write(encodeBandInt(cpEntryListToArray(bcStringRef), Codec.DELTA5));
        out.write(encodeBandInt(cpEntryOrNullListToArray(bcClassRef), Codec.UNSIGNED5));
        out.write(encodeBandInt(cpEntryListToArray(bcFieldRef), Codec.DELTA5));
        out.write(encodeBandInt(cpEntryListToArray(bcMethodRef), Codec.UNSIGNED5));
        out.write(encodeBandInt(cpEntryListToArray(bcIMethodRef), Codec.DELTA5));
        out.write(encodeBandInt(listToArray(bcThisField), Codec.UNSIGNED5));
        out.write(encodeBandInt(listToArray(bcSuperField), Codec.UNSIGNED5));
        out.write(encodeBandInt(listToArray(bcThisMethod), Codec.UNSIGNED5));
        out.write(encodeBandInt(listToArray(bcSuperMethod), Codec.UNSIGNED5));
        out.write(encodeBandInt(listToArray(bcInitRef), Codec.UNSIGNED5));
//        out.write(encodeBandInt(cpEntryListToArray(bcEscRef), Codec.UNSIGNED5));
//        out.write(encodeBandInt(listToArray(bcEscRefSize), Codec.UNSIGNED5));
//        out.write(encodeBandInt(listToArray(bcEscSize), Codec.UNSIGNED5));
//        out.write(encodeBandInt(listToArray(bcEscByte), Codec.BYTE1));
    }

}
