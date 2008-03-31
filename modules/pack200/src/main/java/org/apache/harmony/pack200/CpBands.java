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
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.harmony.pack200.bytecode.CPClass;
import org.apache.harmony.pack200.bytecode.CPDouble;
import org.apache.harmony.pack200.bytecode.CPFloat;
import org.apache.harmony.pack200.bytecode.CPInteger;
import org.apache.harmony.pack200.bytecode.CPLong;
import org.apache.harmony.pack200.bytecode.CPNameAndType;
import org.apache.harmony.pack200.bytecode.CPString;
import org.apache.harmony.pack200.bytecode.CPUTF8;
import org.apache.harmony.pack200.bytecode.ClassConstantPool;

public class CpBands extends BandSet {

    public SegmentConstantPool getConstantPool() {
        return pool;
    }

    private final SegmentConstantPool pool = new SegmentConstantPool(this);

    private String[] cpClass;
    private String[] cpDescriptor;
    private double[] cpDouble;
    private String[] cpFieldClass;
    private String[] cpFieldDescriptor;
    private float[] cpFloat;
    private String[] cpIMethodClass;
    private String[] cpIMethodDescriptor;
    private int[] cpInt;
    private long[] cpLong;
    private String[] cpMethodClass;
    private String[] cpMethodDescriptor;
    private String[] cpSignature;
    private String[] cpString;
    private String[] cpUTF8;

    private final HashMap[] stringsToCPUTF8 = new HashMap[ClassConstantPool.NUM_DOMAINS];
    private final HashMap stringsToCPStrings = new HashMap();
    private final HashMap longsToCPLongs = new HashMap();
    private final HashMap integersToCPIntegers = new HashMap();
    private final HashMap floatsToCPFloats = new HashMap();
    private final HashMap stringsToCPClass = new HashMap();
    private final HashMap doublesToCPDoubles = new HashMap();
    private final HashMap descriptorsToCPNameAndTypes = new HashMap();


    public CpBands(Segment segment) {
        super(segment);
    }

    public void unpack(InputStream in) throws IOException, Pack200Exception {
        parseCpUtf8(in);
        parseCpInt(in);
        parseCpFloat(in);
        parseCpLong(in);
        parseCpDouble(in);
        parseCpString(in);
        parseCpClass(in);
        parseCpSignature(in);
        parseCpDescriptor(in);
        parseCpField(in);
        parseCpMethod(in);
        parseCpIMethod(in);
    }

    /**
     * Parses the constant pool class names, using {@link #cpClassCount} to
     * populate {@link #cpClass} from {@link #cpUTF8}.
     *
     * @param in
     *            the input stream to read from
     * @throws IOException
     *             if a problem occurs during reading from the underlying stream
     * @throws Pack200Exception
     *             if a problem occurs with an unexpected value or unsupported
     *             codec
     */
    private void parseCpClass(InputStream in) throws IOException,
            Pack200Exception {
        int cpClassCount = header.getCpClassCount();
        cpClass = parseReferences("cp_Class", in, Codec.UDELTA5, cpClassCount,
                cpUTF8);
    }


    /**
     * Parses the constant pool descriptor definitions, using
     * {@link #cpDescriptorCount} to populate {@link #cpDescriptor}. For ease
     * of use, the cpDescriptor is stored as a string of the form <i>name:type</i>,
     * largely to make it easier for representing field and method descriptors
     * (e.g. <code>out:java.lang.PrintStream</code>) in a way that is
     * compatible with passing String arrays.
     *
     * @param in
     *            the input stream to read from
     * @throws IOException
     *             if a problem occurs during reading from the underlying stream
     * @throws Pack200Exception
     *             if a problem occurs with an unexpected value or unsupported
     *             codec
     */
    private void parseCpDescriptor(InputStream in) throws IOException,
            Pack200Exception {
        int cpDescriptorCount = header.getCpDescriptorCount();
        String[] cpDescriptorNames = parseReferences("cp_Descr_name", in,
                Codec.DELTA5, cpDescriptorCount, cpUTF8);
        String[] cpDescriptorTypes = parseReferences("cp_Descr_type", in,
                Codec.UDELTA5, cpDescriptorCount, cpSignature);
        cpDescriptor = new String[cpDescriptorCount];
        for (int i = 0; i < cpDescriptorCount; i++) {
            cpDescriptor[i] = cpDescriptorNames[i] + ":" + cpDescriptorTypes[i]; //$NON-NLS-1$
        }
    }

    private void parseCpDouble(InputStream in) throws IOException,
            Pack200Exception {
        int cpDoubleCount = header.getCpDoubleCount();
        long[] band = parseFlags("cp_Double", in, cpDoubleCount,
                Codec.UDELTA5, Codec.DELTA5);
        cpDouble = new double[band.length];
        for (int i = 0; i < band.length; i++) {
			cpDouble[i] = Double.longBitsToDouble(band[i]);
		}
    }

    /**
     * Parses the constant pool field definitions, using {@link #cpFieldCount}
     * to populate {@link #cpFieldClass} and {@link #cpFieldDescriptor}.
     *
     * @param in
     *            the input stream to read from
     * @throws IOException
     *             if a problem occurs during reading from the underlying stream
     * @throws Pack200Exception
     *             if a problem occurs with an unexpected value or unsupported
     *             codec
     */
    private void parseCpField(InputStream in) throws IOException,
            Pack200Exception {
        int cpFieldCount = header.getCpFieldCount();
        cpFieldClass = parseReferences("cp_Field_class", in, Codec.DELTA5,
                cpFieldCount, cpClass);
        cpFieldDescriptor = parseReferences("cp_Field_desc", in, Codec.UDELTA5,
                cpFieldCount, cpDescriptor);
    }

    private void parseCpFloat(InputStream in) throws IOException,
            Pack200Exception {
        int cpFloatCount = header.getCpFloatCount();
        cpFloat = new float[cpFloatCount];
        int floatBits[] = decodeBandInt("cp_Float", in, Codec.UDELTA5,
                cpFloatCount);
        for (int i = 0; i < cpFloatCount; i++) {
            cpFloat[i] = Float.intBitsToFloat(floatBits[i]);
        }
    }

    /**
     * Parses the constant pool interface method definitions, using
     * {@link #cpIMethodCount} to populate {@link #cpIMethodClass} and
     * {@link #cpIMethodDescriptor}.
     *
     * @param in
     *            the input stream to read from
     * @throws IOException
     *             if a problem occurs during reading from the underlying stream
     * @throws Pack200Exception
     *             if a problem occurs with an unexpected value or unsupported
     *             codec
     */
    private void parseCpIMethod(InputStream in) throws IOException,
            Pack200Exception {
        int cpIMethodCount = header.getCpIMethodCount();
        cpIMethodClass = parseReferences("cp_Imethod_class", in, Codec.DELTA5,
                cpIMethodCount, cpClass);
        cpIMethodDescriptor = parseReferences("cp_Imethod_desc", in,
                Codec.UDELTA5, cpIMethodCount, cpDescriptor);
    }

    private void parseCpInt(InputStream in) throws IOException,
            Pack200Exception {
        int cpIntCount = header.getCpIntCount();
        cpInt = decodeBandInt("cpInt", in, Codec.UDELTA5, cpIntCount);
    }

    private void parseCpLong(InputStream in) throws IOException,
            Pack200Exception {
        int cpLongCount = header.getCpLongCount();
        cpLong = parseFlags("cp_Long", in, cpLongCount,
                Codec.UDELTA5, Codec.DELTA5);
    }

    /**
     * Parses the constant pool method definitions, using {@link #cpMethodCount}
     * to populate {@link #cpMethodClass} and {@link #cpMethodDescriptor}.
     *
     * @param in
     *            the input stream to read from
     * @throws IOException
     *             if a problem occurs during reading from the underlying stream
     * @throws Pack200Exception
     *             if a problem occurs with an unexpected value or unsupported
     *             codec
     */
    private void parseCpMethod(InputStream in) throws IOException,
            Pack200Exception {
        int cpMethodCount = header.getCpMethodCount();
        cpMethodClass = parseReferences("cp_Method_class", in, Codec.DELTA5,
                cpMethodCount, cpClass);
        cpMethodDescriptor = parseReferences("cp_Method_desc", in,
                Codec.UDELTA5, cpMethodCount, cpDescriptor);
    }

    /**
     * Parses the constant pool signature classes, using
     * {@link #cpSignatureCount} to populate {@link #cpSignature}. A signature
     * form is akin to the bytecode representation of a class; Z for boolean, I
     * for int, [ for array etc. However, although classes are started with L,
     * the classname does not follow the form; instead, there is a separate
     * array of classes. So an array corresponding to
     * <code>public static void main(String args[])</code> has a form of
     * <code>[L(V)</code> and a classes array of
     * <code>[java.lang.String]</code>. The {@link #cpSignature} is a string
     * representation identical to the bytecode equivalent
     * <code>[Ljava/lang/String;(V)</code> TODO Check that the form is as
     * above and update other types e.g. J
     *
     * @param in
     *            the input stream to read from
     * @throws IOException
     *             if a problem occurs during reading from the underlying stream
     * @throws Pack200Exception
     *             if a problem occurs with an unexpected value or unsupported
     *             codec
     */
    private void parseCpSignature(InputStream in) throws IOException,
            Pack200Exception {
        int cpSignatureCount = header.getCpSignatureCount();
        String[] cpSignatureForm = parseReferences("cp_Signature_form", in,
                Codec.DELTA5, cpSignatureCount, cpUTF8);
        cpSignature = new String[cpSignatureCount];
        int lCount = 0;
        for (int i = 0; i < cpSignatureCount; i++) {
            String form = cpSignatureForm[i];
            char[] chars = form.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if(chars[j] == 'L') {
                    lCount++;
                }
            }
        }
        String[] cpSignatureClasses = parseReferences("cp_Signature_classes", in, Codec.UDELTA5, lCount, cpClass);
        int index = 0;
        for (int i = 0; i < cpSignatureCount; i++) {
            String form = cpSignatureForm[i];
            int len = form.length();
            StringBuffer signature = new StringBuffer(64);
            ArrayList list = new ArrayList();
            for (int j = 0; j < len; j++) {
                char c = form.charAt(j);
                signature.append(c);
                if (c == 'L') {
                    String className = cpSignatureClasses[index];
                    list.add(className);
                    signature.append(className);
                    index++;
                }
            }
            cpSignature[i] = signature.toString();
        }
    }

    /**
     * Parses the constant pool strings, using {@link #cpStringCount} to
     * populate {@link #cpString} from indexes into {@link #cpUTF8}.
     *
     * @param in
     *            the input stream to read from
     * @throws IOException
     *             if a problem occurs during reading from the underlying stream
     * @throws Pack200Exception
     *             if a problem occurs with an unexpected value or unsupported
     *             codec
     */
    private void parseCpString(InputStream in) throws IOException,
            Pack200Exception {
        int cpStringCount = header.getCpStringCount();
        cpString = parseReferences("cp_String", in, Codec.UDELTA5, cpStringCount, cpUTF8);
    }

    private void parseCpUtf8(InputStream in) throws IOException,
            Pack200Exception {
        int cpUTF8Count = header.getCpUTF8Count();
        cpUTF8 = new String[cpUTF8Count];
        cpUTF8[0] = ""; //$NON-NLS-1$
        int[] prefix = decodeBandInt("cpUTF8Prefix", in, Codec.DELTA5, cpUTF8Count-2);
        int charCount = 0;
        int bigSuffixCount = 0;
        int[] suffix = decodeBandInt("cpUTF8Suffix", in, Codec.UNSIGNED5, cpUTF8Count-1);

        for (int i = 0; i < suffix.length; i++) {
            if (suffix[i] == 0) {
                bigSuffixCount++;
            } else {
                charCount += suffix[i];
            }
        }
        char[] data = new char[charCount];
        int[] dataBand = decodeBandInt("cp_Utf8_chars", in, Codec.CHAR3, charCount);
        for (int i = 0; i < data.length; i++) {
            data[i] = (char) dataBand[i];
        }

        // Read in the big suffix data
        int[] bigSuffixCounts = decodeBandInt("cp_Utf8_big_suffix", in, Codec.DELTA5, bigSuffixCount);
        int[][] bigSuffixDataBand = new int[bigSuffixCount][];
        for (int i = 0; i < bigSuffixDataBand.length; i++) {
			bigSuffixDataBand[i] = decodeBandInt("cp_Utf8_big_chars " + i, in, Codec.DELTA5, bigSuffixCounts[i]);
		}

        // Convert big suffix data to characters
        char bigSuffixData[][] = new char[bigSuffixCount][];
        for (int i = 0; i < bigSuffixDataBand.length; i++) {
            bigSuffixData[i] = new char[bigSuffixDataBand[i].length];
            for (int j = 0; j < bigSuffixDataBand[i].length; j++) {
                bigSuffixData[i][j] = (char) bigSuffixDataBand[i][j];
            }
        }
        // Go through the strings
        charCount = 0;
        bigSuffixCount = 0;
        for (int i = 1; i < cpUTF8Count; i++) {
            String lastString = cpUTF8[i - 1];
            if (suffix[i-1] == 0) {
                // The big suffix stuff hasn't been tested, and I'll be
                // surprised if it works first time w/o errors ...
                cpUTF8[i] = lastString.substring(0, i>1 ? prefix[i-2] : 0)
                        + new String(bigSuffixData[bigSuffixCount++]);
            } else {
                cpUTF8[i] = lastString.substring(0, i>1 ? prefix[i-2]: 0)
                        + new String(data, charCount, suffix[i-1]);
                charCount += suffix[i-1];
            }
        }
    }

    public String[] getCpClass() {
        return cpClass;
    }

    public String[] getCpDescriptor() {
        return cpDescriptor;
    }

    public double[] getCpDouble() {
        return cpDouble;
    }

    public String[] getCpFieldClass() {
        return cpFieldClass;
    }

    public String[] getCpFieldDescriptor() {
        return cpFieldDescriptor;
    }

    public float[] getCpFloat() {
        return cpFloat;
    }

    public String[] getCpIMethodClass() {
        return cpIMethodClass;
    }

    public String[] getCpIMethodDescriptor() {
        return cpIMethodDescriptor;
    }

    public int[] getCpInt() {
        return cpInt;
    }

    public long[] getCpLong() {
        return cpLong;
    }

    public String[] getCpMethodClass() {
        return cpMethodClass;
    }

    public String[] getCpMethodDescriptor() {
        return cpMethodDescriptor;
    }

    public String[] getCpSignature() {
        return cpSignature;
    }

    public String[] getCpString() {
        return cpString;
    }

    public String[] getCpUTF8() {
        return cpUTF8;
    }

    public CPUTF8 cpUTF8Value(String string, int domain) {
        if(stringsToCPUTF8[domain] == null) {
            stringsToCPUTF8[domain] = new HashMap();
        }
        CPUTF8 cputf8 = (CPUTF8) stringsToCPUTF8[domain].get(string);
        if(cputf8 == null) {
            cputf8 = new CPUTF8(string, domain);
            stringsToCPUTF8[domain].put(string, cputf8);
        }
        return cputf8;
    }

    public CPString cpStringValue(String string) {
        CPString cpString = (CPString) stringsToCPStrings.get(string);
        if(cpString == null) {
            cpString = new CPString(cpUTF8Value(string, ClassConstantPool.DOMAIN_NORMALASCIIZ));
            stringsToCPStrings.put(string, cpString);
        }
        return cpString;
    }

    public CPLong cpLongValue(Long l) {
        CPLong cpLong = (CPLong) longsToCPLongs.get(l);
        if(cpLong == null) {
            cpLong = new CPLong(l);
            longsToCPLongs.put(l, cpLong);
        }
        return cpLong;
    }

    public CPInteger cpIntegerValue(Integer i) {
        CPInteger cpInteger = (CPInteger) integersToCPIntegers.get(i);
        if(cpInteger == null) {
            cpInteger = new CPInteger(i);
            integersToCPIntegers.put(i, cpInteger);
        }
        return cpInteger;
    }

    public CPFloat cpFloatValue(Float f) {
        CPFloat cpFloat = (CPFloat) floatsToCPFloats.get(f);
        if(cpFloat == null) {
            cpFloat = new CPFloat(f);
            floatsToCPFloats.put(f, cpFloat);
        }
        return cpFloat;
    }

    public CPClass cpClassValue(String string) {
        CPClass cpString = (CPClass) stringsToCPClass.get(string);
        if(cpString == null) {
            cpString = new CPClass(cpUTF8Value(string, ClassConstantPool.DOMAIN_NORMALASCIIZ));
            stringsToCPClass.put(string, cpString);
        }
        return cpString;
    }

    public CPDouble cpDoubleValue(Double dbl) {
        CPDouble cpDouble = (CPDouble) doublesToCPDoubles.get(dbl);
        if(cpDouble == null) {
            cpDouble = new CPDouble(dbl);
            doublesToCPDoubles.put(dbl, cpDouble);
        }
        return cpDouble;
    }

    public CPNameAndType cpNameAndTypeValue(String descriptor) {
        CPNameAndType cpNameAndType = (CPNameAndType) descriptorsToCPNameAndTypes.get(descriptor);
        if(cpNameAndType == null) {
            int descriptorDomain = ClassConstantPool.DOMAIN_UNDEFINED;
            int colon = descriptor.indexOf(':');
            String nameString = descriptor.substring(0,colon);
            String descriptorString = descriptor.substring(colon+1);
            // For some reason, descriptors which have just plain
            // native types are stored in DOMAIN_NORMALASCIIZ rather
            // than in DOMAIN_SIGNATUREASCIIZ. This might indicate
            // that DOMAIN_SIGNATUREASCIIZ is poorly named.
            boolean nativeDescriptor = true;
            for(int index=0; index < descriptorString.length(); index++) {
                char currentChar = descriptorString.charAt(index);
                if(Character.isLetter(currentChar)) {
                    if(currentChar == 'L') {
                        nativeDescriptor = false;
                    }
                    break;
                }
            }
            int domain = ClassConstantPool.DOMAIN_NAMEANDTYPE;
            CPUTF8 name = cpUTF8Value(nameString, ClassConstantPool.DOMAIN_NORMALASCIIZ);
            if( nativeDescriptor ) {
                // Native signatures are stored in DOMAIN_NORMALASCIIZ, not
                // DOMAIN_SIGNATUREASCIIZ for some reason.
                descriptorDomain = ClassConstantPool.DOMAIN_NORMALASCIIZ;
            } else {
                descriptorDomain = ClassConstantPool.DOMAIN_SIGNATUREASCIIZ;
            }
            CPUTF8 descriptorU = cpUTF8Value(descriptorString, descriptorDomain);
            cpNameAndType = new CPNameAndType(name, descriptorU, domain);
            descriptorsToCPNameAndTypes.put(descriptor, cpNameAndType);
        }
        return cpNameAndType;
    }

}