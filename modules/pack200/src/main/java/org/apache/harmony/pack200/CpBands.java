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

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
import org.apache.bcel.classfile.ConstantUtf8;
import org.apache.bcel.classfile.JavaClass;

/**
 * Pack200 Constant Pool Bands
 */
public class CpBands extends BandSet {

    private final SegmentHeader segmentHeader;

    // Don't need to include default attribute names in the constant pool bands
    private final Set defaultAttributeNames = new HashSet();

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

    private final Map stringsToCpUtf8 = new HashMap();
    private final Map stringsToCpNameAndType = new HashMap();
    // private final Map stringsToCpString = new HashMap();
    private final Map stringsToCpClass = new HashMap();
    private final Map stringsToCpSignature = new HashMap();

    private final Map constantsToCPConstant = new HashMap();

    private ConstantPool currentConstantPool;
    private JavaClass currentClass;

    public CpBands(SegmentHeader segmentHeader) {
        this.segmentHeader = segmentHeader;
        defaultAttributeNames.add("AnnotationDefault");
        defaultAttributeNames.add("RuntimeVisibleAnnotations");
        defaultAttributeNames.add("RuntimeInvisibleAnnotations");
        defaultAttributeNames.add("RuntimeVisibleParameterAnnotations");
        defaultAttributeNames.add("RuntimeInvisibleParameterAnnotations");
        defaultAttributeNames.add("Code");
        defaultAttributeNames.add("LineNumberTable");
        defaultAttributeNames.add("LocalVariableTable");
        defaultAttributeNames.add("LocalVariableTypeTable");
        defaultAttributeNames.add("ConstantValue");
        defaultAttributeNames.add("Deprecated");
        defaultAttributeNames.add("EnclosingMethod");
        defaultAttributeNames.add("Exceptions");
        defaultAttributeNames.add("InnerClasses");
        defaultAttributeNames.add("Signature");
        defaultAttributeNames.add("SourceFile");
    }

    public void pack(OutputStream out) throws IOException, Pack200Exception {
        writeCpUtf8(out);
        writeCpInt(out);
        writeCpFloat(out);
        writeCpLong(out);
        writeCpDouble(out);
        writeCpString(out);
        writeCpClass(out);
        writeCpSignature(out);
        writeCpDescr(out);
        writeCpMethodOrField(cp_Field, out);
        writeCpMethodOrField(cp_Method, out);
        writeCpMethodOrField(cp_Imethod, out);
    }

    private void writeCpUtf8(OutputStream out) throws IOException,
            Pack200Exception {
        int[] cpUtf8Prefix = new int[cp_Utf8.size() - 2];
        int[] cpUtf8Suffix = new int[cp_Utf8.size() - 1];
        List chars = new ArrayList();
        List bigSuffix = new ArrayList();
        List bigChars = new ArrayList();
        Object[] cpUtf8Array = cp_Utf8.toArray();
        String first = ((CPUTF8) cpUtf8Array[1]).getUnderlyingString();
        cpUtf8Suffix[0] = first.length();
        addCharacters(chars, first.toCharArray());
        for (int i = 2; i < cpUtf8Array.length; i++) {
            char[] previous = ((CPUTF8) cpUtf8Array[i - 1])
                    .getUnderlyingString().toCharArray();
            String currentStr = ((CPUTF8) cpUtf8Array[i]).getUnderlyingString();
            char[] current = currentStr.toCharArray();
            int prefix = 0;
            for (int j = 0; j < previous.length; j++) {
                if (previous[j] == current[j]) {
                    prefix++;
                } else {
                    break;
                }
            }
            cpUtf8Prefix[i - 2] = prefix;
            currentStr = currentStr.substring(prefix);
            char[] suffix = currentStr.toCharArray();
            if (suffix.length > 100) { // big suffix (100 is arbitrary - can we
                                        // do better?)
                cpUtf8Suffix[i - 1] = 0;
                bigSuffix.add(new Integer(suffix.length));
                addCharacters(bigChars, suffix);
            } else {
                cpUtf8Suffix[i - 1] = suffix.length;
                addCharacters(chars, suffix);
            }
        }
        int[] cpUtf8Chars = new int[chars.size()];
        int[] cpUtf8BigSuffix = new int[bigSuffix.size()];
        int[] cpUtf8BigChars = new int[bigChars.size()];
        for (int i = 0; i < cpUtf8Chars.length; i++) {
            cpUtf8Chars[i] = ((Character) chars.get(i)).charValue();
        }
        for (int i = 0; i < cpUtf8BigSuffix.length; i++) {
            cpUtf8BigSuffix[i] = ((Integer) bigSuffix.get(i)).intValue();
        }
        for (int i = 0; i < cpUtf8BigChars.length; i++) {
            cpUtf8BigChars[i] = ((Character) bigChars.get(i)).charValue();
        }
        out.write(encodeBandInt(cpUtf8Prefix, Codec.DELTA5));
        out.write(encodeBandInt(cpUtf8Suffix, Codec.UNSIGNED5));
        out.write(encodeBandInt(cpUtf8Chars, Codec.CHAR3));
        out.write(encodeBandInt(cpUtf8BigSuffix, Codec.DELTA5));
        out.write(encodeBandInt(cpUtf8BigChars, Codec.DELTA5));
    }

    private void addCharacters(List chars, char[] charArray) {
        for (int i = 0; i < charArray.length; i++) {
            chars.add(new Character(charArray[i]));
        }
    }

    private void writeCpInt(OutputStream out) throws IOException,
            Pack200Exception {
        int[] cpInt = new int[cp_Int.size()];
        int i = 0;
        for (Iterator iterator = cp_Int.iterator(); iterator.hasNext();) {
            CPInt integer = (CPInt) iterator.next();
            cpInt[i] = integer.getInt();
            i++;
        }
        out.write(encodeBandInt(cpInt, Codec.UDELTA5));
    }

    private void writeCpFloat(OutputStream out) throws IOException,
            Pack200Exception {
        int[] cpFloat = new int[cp_Float.size()];
        int i = 0;
        for (Iterator iterator = cp_Float.iterator(); iterator.hasNext();) {
            CPFloat fl = (CPFloat) iterator.next();
            cpFloat[i] = Float.floatToIntBits(fl.getFloat());
            i++;
        }
        out.write(encodeBandInt(cpFloat, Codec.UDELTA5));
    }

    private void writeCpLong(OutputStream out) throws IOException,
            Pack200Exception {
        int[] highBits = new int[cp_Long.size()];
        int[] loBits = new int[cp_Long.size()];
        int i = 0;
        for (Iterator iterator = cp_Long.iterator(); iterator.hasNext();) {
            CPLong lng = (CPLong) iterator.next();
            long l = lng.getLong();
            highBits[i] = (int) (l >> 32);
            loBits[i] = (int) l;
            i++;
        }
        out.write(encodeBandInt(highBits, Codec.UDELTA5));
        out.write(encodeBandInt(loBits, Codec.DELTA5));
    }

    private void writeCpDouble(OutputStream out) throws IOException,
            Pack200Exception {
        int[] highBits = new int[cp_Double.size()];
        int[] loBits = new int[cp_Double.size()];
        int i = 0;
        for (Iterator iterator = cp_Double.iterator(); iterator.hasNext();) {
            CPDouble dbl = (CPDouble) iterator.next();
            long l = Double.doubleToLongBits(dbl.getDouble());
            highBits[i] = (int) (l >> 32);
            loBits[i] = (int) l;
            i++;
        }
        out.write(encodeBandInt(highBits, Codec.UDELTA5));
        out.write(encodeBandInt(loBits, Codec.DELTA5));
    }

    private void writeCpString(OutputStream out) throws IOException,
            Pack200Exception {
        int[] cpString = new int[cp_String.size()];
        int i = 0;
        for (Iterator iterator = cp_String.iterator(); iterator.hasNext();) {
            CPString cpStr = (CPString) iterator.next();
            cpString[i] = cpStr.getIndexInCpUtf8();
            i++;
        }
        out.write(encodeBandInt(cpString, Codec.UDELTA5));
    }

    private void writeCpClass(OutputStream out) throws IOException,
            Pack200Exception {
        int[] cpClass = new int[cp_Class.size()];
        int i = 0;
        for (Iterator iterator = cp_Class.iterator(); iterator.hasNext();) {
            CPClass cpCl = (CPClass) iterator.next();
            cpClass[i] = cpCl.getIndexInCpUtf8();
            i++;
        }
        out.write(encodeBandInt(cpClass, Codec.UDELTA5));
    }

    private void writeCpSignature(OutputStream out) throws IOException,
            Pack200Exception {
        int[] cpSignatureForm = new int[cp_Signature.size()];
        List classes = new ArrayList();
        int i = 0;
        for (Iterator iterator = cp_Signature.iterator(); iterator.hasNext();) {
            CPSignature cpS = (CPSignature) iterator.next();
            classes.addAll(cpS.getClasses());
            cpSignatureForm[i] = cpS.getIndexInCpUtf8();
            i++;
        }
        int[] cpSignatureClasses = new int[classes.size()];
        for (int j = 0; j < cpSignatureClasses.length; j++) {
            cpSignatureClasses[j] = ((CPClass) classes.get(j)).getIndex();
        }
        out.write(encodeBandInt(cpSignatureForm, Codec.DELTA5));
        out.write(encodeBandInt(cpSignatureClasses, Codec.UDELTA5));
    }

    private void writeCpDescr(OutputStream out) throws IOException,
            Pack200Exception {
        int[] cpDescrName = new int[cp_Descr.size()];
        int[] cpDescrType = new int[cp_Descr.size()];
        int i = 0;
        for (Iterator iterator = cp_Descr.iterator(); iterator.hasNext();) {
            CPNameAndType nameAndType = (CPNameAndType) iterator.next();
            cpDescrName[i] = nameAndType.getNameIndex();
            cpDescrType[i] = nameAndType.getTypeIndex();
            i++;
        }
        out.write(encodeBandInt(cpDescrName, Codec.DELTA5));
        out.write(encodeBandInt(cpDescrType, Codec.UDELTA5));
    }

    private void writeCpMethodOrField(Set cp, OutputStream out)
            throws IOException, Pack200Exception {
        int[] cp_methodOrField_class = new int[cp.size()];
        int[] cp_methodOrField_desc = new int[cp.size()];
        int i = 0;
        for (Iterator iterator = cp.iterator(); iterator.hasNext();) {
            CPMethodOrField mOrF = (CPMethodOrField) iterator.next();
            cp_methodOrField_class[i] = mOrF.getClassIndex();
            cp_methodOrField_desc[i] = mOrF.getDescIndex();
            i++;
        }
        out.write(encodeBandInt(cp_methodOrField_class, Codec.DELTA5));
        out.write(encodeBandInt(cp_methodOrField_desc, Codec.UDELTA5));
    }

    public void setCurrentClass(JavaClass javaClass) {
        this.currentClass = javaClass;
        this.currentConstantPool = javaClass.getConstantPool();
    }

    public void finaliseBands() {
        addCPUtf8("");
        removeSignaturesFromCpUTF8();
        addIndices();
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

    private void removeSignaturesFromCpUTF8() {
        for (Iterator iterator = cp_Signature.iterator(); iterator.hasNext();) {
            CPSignature signature = (CPSignature) iterator.next();
            String sigStr = signature.getUnderlyingString();
            CPUTF8 utf8 = signature.getSignatureForm();
            String form = utf8.getUnderlyingString();
            if (!sigStr.equals(form)) {
                removeCpUtf8(sigStr);
            }
        }
    }

    private void addIndices() {
        Set[] sets = new Set[] { cp_Utf8, cp_String, cp_Class, cp_Signature,
                cp_Descr };
        for (int i = 0; i < sets.length; i++) {
            int j = 0;
            for (Iterator iterator = sets[i].iterator(); iterator.hasNext();) {
                ConstantPoolEntry entry = (ConstantPoolEntry) iterator.next();
                entry.setIndex(j);
                j++;
            }
        }
    }

    private void removeCpUtf8(String string) {
        CPUTF8 utf8 = (CPUTF8) stringsToCpUtf8.get(string);
        if (utf8 != null) {
            stringsToCpUtf8.remove(string);
            cp_Utf8.remove(utf8);
        }
    }

    public void addConstantClass(ConstantClass constant) {
        String className = constant.getBytes(currentConstantPool);
        addCPClass(className);
    }

    public void addConstantDouble(ConstantDouble constant) {
        double d = (constant).getBytes();
        Double bigD = new Double(d);
        CPDouble cpd = (CPDouble) constantsToCPConstant.get(bigD);
        if(cpd == null) {
            cpd = new CPDouble(d);
            cp_Double.add(cpd);
            constantsToCPConstant.put(bigD, cpd);
        }
    }

    public void addConstantFieldref(ConstantFieldref constant) {
        ConstantFieldref cf = constant;
        ConstantNameAndType cnat = (ConstantNameAndType) currentConstantPool
                .getConstant(cf.getNameAndTypeIndex());
        CPNameAndType nat = getCPNameAndType(cnat.getName(currentConstantPool),
                cnat.getSignature(currentConstantPool));
        cp_Field.add(new CPMethodOrField(getCPClass(cf
                .getClass(currentConstantPool)), nat));
    }

    public void addConstantFloat(ConstantFloat constant) {
        float f = (constant).getBytes();
        Float bigF = new Float(f);
        CPFloat cpf = (CPFloat) constantsToCPConstant.get(bigF);
        if(cpf == null) {
            cpf = new CPFloat(f);
            cp_Float.add(cpf);
            constantsToCPConstant.put(bigF, cpf);
        }
    }

    public void addConstantInteger(ConstantInteger constant) {
        int i = (constant).getBytes();
        Integer bigI = new Integer(i);
        CPInt cpi = (CPInt) constantsToCPConstant.get(bigI);
        if(cpi == null) {
            cpi = new CPInt(i);
            cp_Int.add(cpi);
            constantsToCPConstant.put(bigI, cpi);
        }
    }

    public void addConstantInterfaceMethodref(
            ConstantInterfaceMethodref constant) {
        ConstantNameAndType cnat = (ConstantNameAndType) currentConstantPool
                .getConstant(constant.getNameAndTypeIndex());
        String signature = cnat.getSignature(currentConstantPool);
        CPNameAndType nat = getCPNameAndType(cnat.getName(currentConstantPool),
                signature);
        cp_Imethod.add(new CPMethodOrField(getCPClass(constant
                .getClass(currentConstantPool)), nat));
    }

    public void addConstantLong(ConstantLong constant) {
        long l = (constant).getBytes();
        Long bigL = new Long(l);
        CPLong cpl = (CPLong) constantsToCPConstant.get(bigL);
        if(cpl == null) {
            cpl = new CPLong(l);
            cp_Long.add(cpl);
            constantsToCPConstant.put(bigL, cpl);
        }
    }

    public void addConstantMethodref(ConstantMethodref constant) {
        ConstantNameAndType cnat = (ConstantNameAndType) currentConstantPool
                .getConstant(constant.getNameAndTypeIndex());
        String signature = cnat.getSignature(currentConstantPool);
        CPNameAndType nat = getCPNameAndType(cnat.getName(currentConstantPool),
                signature);
        cp_Method.add(new CPMethodOrField(getCPClass(constant
                .getClass(currentConstantPool)), nat));
    }

    public void addConstantNameAndType(ConstantNameAndType constant) {
        String name = constant.getName(currentConstantPool);
        String signature = constant.getSignature(currentConstantPool);
        addCPNameAndType(name, signature);
    }

    public void addConstantString(ConstantString constant) {
        String string = constant.getBytes(currentConstantPool);
        CPString cpString = (CPString) constantsToCPConstant.get(string);
        if (cpString == null) {
            cpString = new CPString(getCPUtf8(string));
            cp_String.add(cpString);
            constantsToCPConstant.put(string, cpString);
        }
    }

    public void addConstantUtf8(ConstantUtf8 constant) {
        String utf8 = constant.getBytes();
        if (!defaultAttributeNames.contains(utf8)) {
            if (utf8.endsWith(".java")) {
                if (!isPredictableSourceFileName(currentClass.getClassName(), utf8)) {
                    addCPUtf8(utf8);
                }
            } else {
                addCPUtf8(utf8);
            }
        }
    }

    private void addCPUtf8(String utf8) {
        getCPUtf8(utf8);
    }

    public CPUTF8 getCPUtf8(String utf8) {
        CPUTF8 cpUtf8 = (CPUTF8) stringsToCpUtf8.get(utf8);
        if (cpUtf8 == null) {
            cpUtf8 = new CPUTF8(utf8);
            cp_Utf8.add(cpUtf8);
            stringsToCpUtf8.put(utf8, cpUtf8);
        }
        return cpUtf8;
    }

    public void addCPNameAndType(String name, String signature) {
        getCPNameAndType(name, signature);
    }

    public void addCPSignature(String signature) {
        getCPSignature(signature);
    }

    public CPSignature getCPSignature(String signature) {
        CPSignature cpS = (CPSignature) stringsToCpSignature.get(signature);
        if (cpS == null) {
            List cpClasses = new ArrayList();
            CPUTF8 signatureUTF8;
            if (signature.length() > 1 && signature.indexOf("L") != -1) {
                List classes = new ArrayList();
                char[] chars = signature.toCharArray();
                StringBuffer signatureString = new StringBuffer();
                for (int i = 0; i < chars.length; i++) {
                    signatureString.append(chars[i]);
                    if (chars[i] == 'L') {
                        StringBuffer className = new StringBuffer();
                        for (int j = i + 1; j < chars.length; j++) {
                            char c = chars[j];
                            if (Character.isLetter(c) || Character.isDigit(c)
                                    || c == '/') {
                                className.append(c);
                            } else {
                                classes.add(className.toString());
                                i = j - 1;
                                break;
                            }
                        }
                    }
                }
                removeCpUtf8(signature);
                for (Iterator iterator2 = classes.iterator(); iterator2
                        .hasNext();) {
                    cpClasses.add(getCPClass((String) iterator2.next()));
                }

                signatureUTF8 = getCPUtf8(signatureString.toString());
            } else {
                signatureUTF8 = getCPUtf8(signature);
            }
            cpS = new CPSignature(signature, signatureUTF8, cpClasses);
            cp_Signature.add(cpS);
            stringsToCpSignature.put(signature, cpS);
        }
        return cpS;
    }

    public CPClass getCPClass(String className) {
        className = className.replace('.', '/');
        CPClass cpClass = (CPClass) stringsToCpClass.get(className);
        if (cpClass == null) {
            CPUTF8 cpUtf8 = getCPUtf8(className);
            cpClass = new CPClass(cpUtf8);
            cp_Class.add(cpClass);
            stringsToCpClass.put(className, cpClass);
        }
        return cpClass;
    }

    public void addCPClass(String className) {
        getCPClass(className);
    }

    public CPNameAndType getCPNameAndType(String name, String signature) {
        String descr = name + ":" + signature;
        CPNameAndType nameAndType = (CPNameAndType) stringsToCpNameAndType
                .get(descr);
        if (nameAndType == null) {
            nameAndType = new CPNameAndType(getCPUtf8(name),
                    getCPSignature(signature));
            stringsToCpNameAndType.put(descr, nameAndType);
            cp_Descr.add(nameAndType);
        }
        return nameAndType;
    }

    public CPConstant getCPConstant(Constant theConstant, ConstantPool cp) {
        Object key;
        if(theConstant instanceof ConstantDouble) {
            key = new Double(((ConstantDouble)theConstant).getBytes());
        } else if (theConstant instanceof ConstantFloat) {
            key = new Float(((ConstantFloat)theConstant).getBytes());
        } else if (theConstant instanceof ConstantInteger) {
            key = new Integer(((ConstantInteger)theConstant).getBytes());
        } else if (theConstant instanceof ConstantLong) {
            key = new Long(((ConstantLong)theConstant).getBytes());
        } else if (theConstant instanceof ConstantString) {
            key = ((ConstantString)theConstant).getBytes(cp);
        } else {
            throw new RuntimeException("Unexpected constant type: " + theConstant.getClass());
        }
        return (CPConstant) constantsToCPConstant.get(key);
    }

}
