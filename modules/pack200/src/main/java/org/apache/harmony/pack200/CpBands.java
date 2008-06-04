/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.apache.harmony.pack200;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
import org.apache.bcel.classfile.ConstantUtf8;

public class CpBands extends BandSet {

    private final SegmentHeader segmentHeader;

    private final Set cp_Utf8 = new TreeSet();
    private final Set cp_Int = new TreeSet();
    private final Set cp_Float = new TreeSet();
    private final Set cp_Long = new TreeSet();
    private final Set cp_Double = new TreeSet();
    private final Set cp_String = new TreeSet();
    private final Set cp_Class = new TreeSet();
    private final Set cp_Signature = new TreeSet();
    private final Set cp_Descr = new TreeSet();
    private final Set cp_Field = new TreeSet();
    private final Set cp_Method = new TreeSet();
    private final Set cp_Imethod = new TreeSet();

    private ConstantPool currentConstantPool;
    private final Map stringsToCpClass = new HashMap();
    private final Map stringsToCpNameAndType = new HashMap();
    private final Map stringsToCpString = new HashMap();
    private final Map stringsToCpSignature = new HashMap();
    
    
    public CpBands(SegmentHeader segmentHeader) {
        this.segmentHeader = segmentHeader;
    }

    public void pack(OutputStream out) {
        // TODO Auto-generated method stub

    }

    public void setCurrentConstantPool(ConstantPool cp) {
        this.currentConstantPool = cp;
    }

    public void finaliseBands() {

        System.out.println("pool");
        
        segmentHeader.setCp_Utf8_count(cp_Utf8.size());
        segmentHeader.setCp_Int_count(cp_Int.size());
        segmentHeader.setCp_Float_count(cp_Float.size());
        segmentHeader.setCp_Long_count(cp_Long.size());
        segmentHeader.setCp_Double_count(cp_Double.size());
        segmentHeader.setCp_String_count(cp_String.size());
        segmentHeader.setCp_Class_count(cp_Class.size());
        segmentHeader.setCp_Signature_count(cp_Signature.size());
        segmentHeader.setCp_Descr_count(cp_Descr.size());
        segmentHeader.setCp_Field_count(cp_Field.size());
        segmentHeader.setCp_Method_count(cp_Method.size());
        segmentHeader.setCp_Imethod_count(cp_Imethod.size());
    }

    public void addConstantClass(ConstantClass constant) {
        String className = constant.getBytes(currentConstantPool);
        if(stringsToCpClass.get(className) == null) {
            CPClass cpClass = new CPClass(className);
            cp_Class.add(cpClass);
            stringsToCpClass.put(className, cpClass);
        }
    }

    public void addConstantDouble(ConstantDouble constant) {
        cp_Double.add(new Double((constant).getBytes()));
    }

    public void addConstantFieldref(ConstantFieldref constant) {
        ConstantFieldref cf = constant;
        ConstantNameAndType cnat = (ConstantNameAndType) currentConstantPool
                .getConstant(cf.getNameAndTypeIndex());
        CPNameAndType nat = new CPNameAndType(cnat.getName(currentConstantPool),
                cnat.getSignature(currentConstantPool));
        cp_Signature.add(cnat.getSignature(currentConstantPool));
        cp_Field.add(new MethodOrField(cf.getClass(currentConstantPool), nat));
    }

    public void addConstantFloat(ConstantFloat constant) {
        cp_Float.add(new Float((constant).getBytes()));
    }

    public void addConstantInteger(ConstantInteger constant) {
        cp_Int.add(new Integer((constant).getBytes()));
    }

    public void addConstantInterfaceMethodref(
            ConstantInterfaceMethodref constant) {
        ConstantNameAndType cnat = (ConstantNameAndType) currentConstantPool
                .getConstant(constant.getNameAndTypeIndex());
        String signature = cnat.getSignature(currentConstantPool);
        cp_Signature.add(signature);
        CPNameAndType nat = new CPNameAndType(cnat.getName(currentConstantPool), signature);
        cp_Imethod.add(new MethodOrField(constant.getClass(currentConstantPool), nat));
    }

    public void addConstantLong(ConstantLong constant) {
        cp_Long.add(new Long((constant).getBytes()));
    }

    public void addConstantMethodref(ConstantMethodref constant) {
        ConstantNameAndType cnat = (ConstantNameAndType) currentConstantPool
                .getConstant(constant.getNameAndTypeIndex());
        String signature = cnat.getSignature(currentConstantPool);
        cp_Signature.add(signature);
        CPNameAndType nat = new CPNameAndType(cnat.getName(currentConstantPool),
                signature);
        cp_Method.add(new MethodOrField(constant.getClass(currentConstantPool),
                nat));
    }

    public void addConstantNameAndType(ConstantNameAndType constant) {
        String name = constant.getName(currentConstantPool);
        String signature = constant.getSignature(currentConstantPool);
        String descr = name + ":" + signature;
        if(stringsToCpNameAndType.get(descr) != null) {
            cp_Signature.add(signature);
            CPNameAndType nameAndType = new CPNameAndType(name,
                    signature);
            stringsToCpNameAndType.put(descr, nameAndType);
            cp_Descr.add(nameAndType);
        }
    }

    public void addConstantString(ConstantString constant) {
        String string = constant.getBytes(currentConstantPool);
        CPString cpString = (CPString) stringsToCpString.get(string);
        if(cpString == null) {
            cpString = new CPString(string);
            cp_String.add(cpString);
            stringsToCpString.put(string, cpString);
        }
    }

    public void addConstantUtf8(ConstantUtf8 constant) {
        cp_Utf8.add((constant).getBytes());
    }

    public void addDesc(String name, String signature) {
        cp_Signature.add(signature);
        cp_Descr.add(new CPNameAndType(name, signature));
    }

    public void addSignature(String signature) {
        cp_Signature.add(signature);
    }

    public CPClass getCPClass(String className) {
        CPClass cpClass = (CPClass) stringsToCpClass.get(className);
        if(cpClass == null) {
            cpClass = new CPClass(className);
            cp_Class.add(cpClass);
            stringsToCpClass.put(className, cpClass);
        }
        return cpClass;
    }

    public CPNameAndType getCPNameAndType(String name, String signature) {
        String descr = name + ":" + signature;
        CPNameAndType nameAndType = (CPNameAndType) stringsToCpNameAndType.get(descr);
        if (nameAndType == null) {
            cp_Signature.add(signature);
            nameAndType = new CPNameAndType(name, signature);
            stringsToCpNameAndType.put(descr, nameAndType);
            cp_Descr.add(nameAndType);
        }
        return nameAndType;
    }

}
