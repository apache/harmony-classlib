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

import org.apache.bcel.Constants;
import org.apache.bcel.classfile.Attribute;
import org.apache.bcel.classfile.Code;
import org.apache.bcel.classfile.CodeException;
import org.apache.bcel.classfile.Constant;
import org.apache.bcel.classfile.ConstantValue;
import org.apache.bcel.classfile.Deprecated;
import org.apache.bcel.classfile.ExceptionTable;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.InnerClasses;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.LineNumberTable;
import org.apache.bcel.classfile.LocalVariableTable;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.classfile.Signature;
import org.apache.bcel.classfile.SourceFile;
import org.apache.bcel.classfile.Unknown;

public class ClassBands extends BandSet {

    private SegmentHeader header;
    private final CpBands cpBands;
    private AttributeDefinitionBands attrBands;

    private final CPClass[] class_this;
    private final CPClass[] class_super;
    private final CPClass[][] class_interface;
    private final int[] class_interface_count;

    private final int[] major_versions;
    private final int[] minor_versions;

    private long[] class_flags;
    private List classSourceFile = new ArrayList();
    private List classEnclosingMethod = new ArrayList();
    private List classSignature = new ArrayList();

    private List classFileVersionMinor = new ArrayList();
    private List classFileVersionMajor = new ArrayList();


    private final int[] class_field_count;
    private final CPNameAndType[][] field_descr;
    private List fieldFlags = new ArrayList();
    private List fieldConstantValueKQ = new ArrayList();

    private final int[] class_method_count;
    private final CPNameAndType[][] method_descr;
    private List methodFlags = new ArrayList();

    private List codeHeaders = new ArrayList();
    private List codeMaxStack = new ArrayList();
    private List codeMaxLocals = new ArrayList();
    private List codeHandlerCount = new ArrayList();
    private List codeHandlerStartP = new ArrayList();
    private List codeHandlerEndPO = new ArrayList();
    private List codeHandlerCatchPO = new ArrayList();
    private List codeHandlerClass = new ArrayList();
    private List codeFlags = new ArrayList();



    public ClassBands(SegmentHeader header, CpBands cpBands, AttributeDefinitionBands attrBands, int numClasses) {
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
        method_descr = new CPNameAndType[numClasses][];
        minor_versions = new int[numClasses];
        major_versions = new int[numClasses];
        class_flags = new long[numClasses];
    }

    private int index = 0;

    public void addClass(JavaClass obj) {
        class_this[index] = cpBands.getCPClass(obj.getClassName());
        class_super[index] = cpBands.getCPClass(obj.getSuperclassName());
        String[] interfaces = obj.getInterfaceNames();
        class_interface_count[index] = interfaces.length;
        for (int i = 0; i < interfaces.length; i++) {
            class_interface[index][i] = cpBands.getCPClass(interfaces[i]);
        }
        addClassAttributes(obj);

        addFields(obj.getFields());

        Method[] methods = obj.getMethods();
        addMethods(methods);
        addCode(methods);
        index++;
    }

    private void addClassAttributes(JavaClass obj) {
        int flags = obj.getAccessFlags();
        Attribute[] attributes = obj.getAttributes();
        for (int i = 0; i < attributes.length; i++) {
            if(attributes[i] instanceof SourceFile) {
                flags |= (1<<17);
                String sourceFileName = ((SourceFile)attributes[i]).getSourceFileName();
                if(isPredictableSourceFileName(obj.getClassName(), sourceFileName)) {
                    classSourceFile.add(null);
                } else {
                    classSourceFile.add(cpBands.getCPUtf8(sourceFileName));
                }
//            } else if (attributes[i] instanceof EnclosingMethod) {
//                flags |= (1<<18);
            } else if (attributes[i] instanceof Signature) {
                flags |= (1<<19);
                classSignature.add(cpBands.getCPSignature(((Signature)attributes[i]).getSignature()));
            } else if (attributes[i] instanceof Deprecated) {
                flags |= (1<<20);
//            } else if (attributes[i] instanceof RuntimeVisibleAnnotations) {
//                flags |= (1<<21);
//            } else if (attributes[i] instanceof RuntimeInvisibleAnnotations) {
//                flags |= (1<<22);
            } else if (attributes[i] instanceof InnerClasses) {
                flags |= (1<<23);
            } else if (attributes[i] instanceof Unknown) {
                Unknown attr = (Unknown)attributes[i];
                int index = attrBands.getIndex(attr);
                flags |= (1<<index);

            }
        }
        minor_versions[index] = obj.getMinor();
        major_versions[index] = obj.getMajor();
        class_flags[index] = flags;

    }

    private void addMethods(Method[] methods) {
        class_method_count[index] = methods.length;
        method_descr[index] = new CPNameAndType[methods.length];
        for (int i = 0; i < methods.length; i++) {
            method_descr[index][i] = cpBands.getCPNameAndType(methods[i]
                    .getName(), methods[i].getSignature());

            int flags = methods[i].getAccessFlags();
            Attribute[] attributes = methods[i].getAttributes();
            for (int j = 0; j < attributes.length; j++) {
                if (attributes[j] instanceof Code) {
                    flags |= (1<<17);
                } else if (attributes[j] instanceof ExceptionTable) {
                    flags |= (1<<18);
                } else if (attributes[j] instanceof Signature) {
                    flags |= (1<<19);
                } else if (attributes[j] instanceof Deprecated) {
                    flags |= (1<<20);
//                } else if (attributes[j] instanceof RuntimeVisibleAnnotations) {
//                    flags |= (1<<21);
//                } else if (attributes[j] instanceof RuntimeInvisibleAnnotations) {
//                    flags |= (1<<22);
//                } else if (attributes[j] instanceof RuntimeVisibleParameterAnnotations) {
//                    flags |= (1<<23);
//                } else if (attributes[j] instanceof RuntimeInvisibleParameterAnnotations) {
//                    flags |= (1<<24);
//                } else if (attributes[j] instanceof AnnotationDefault) {
//                    flags |= (1<<25);
                } else if (attributes[j] instanceof Unknown) {
                    Unknown attr = (Unknown)attributes[i];
                    int index = attrBands.getIndex(attr);
                    flags |= (1<<index);

                }
            }
            methodFlags.add(new Long(flags));
        }
    }

    private void addFields(Field[] fields) {
        class_field_count[index] = fields.length;
        field_descr[index] = new CPNameAndType[fields.length];
        for (int i = 0; i < fields.length; i++) {
            field_descr[index][i] = cpBands.getCPNameAndType(fields[i]
                    .getName(), fields[i].getSignature());
            int flags = fields[i].getAccessFlags();
            Attribute[] attributes = fields[i].getAttributes();
            for (int j = 0; j < attributes.length; j++) {
                if (attributes[j] instanceof ConstantValue) {
                    flags |= (1<<17);
                    ConstantValue constV = ((ConstantValue)attributes[j]);
                    Constant theConstant = constV.getConstantPool().getConstant(constV.getConstantValueIndex());
                    CPConstant cpConstant = cpBands.getCPConstant(theConstant, constV.getConstantPool());
                    fieldConstantValueKQ.add(cpConstant);
                } else if (attributes[j] instanceof Signature) {
                    flags |= (1<<19);
                } else if (attributes[j] instanceof Deprecated) {
                    flags |= (1<<20);
//                } else if (attributes[j] instanceof RuntimeVisibleAnnotations) {
//                    flags |= (1<<21);
//                } else if (attributes[j] instanceof RuntimeInvisibleAnnotations) {
//                    flags |= (1<<22);
                } else if (attributes[j] instanceof Unknown) {
                    Unknown attr = (Unknown)attributes[i];
                    int index = attrBands.getIndex(attr);
                    flags |= (1<<index);

                }
            }
            fieldFlags.add(new Long(flags));
        }

    }

    private void addCode(Method[] methods) {

        for (int i = 0; i < methods.length; i++) {
            Code code = methods[i].getCode();
            if(code != null) {
                int header = 0;
                int maxLocals = code.getMaxLocals();
                int maxStack = code.getMaxStack();
                CodeException[] exceptions = code.getExceptionTable();
                int handlers = exceptions.length;
                if(handlers <= 2) {
                    if (handlers == 0) {
                        if(maxLocals < 11 && maxStack < 11) {
                            header = 12*maxLocals + maxStack + 1;
                        }
                    } else if (handlers == 1) {
                        if(maxLocals < 7 && maxStack < 7) {
                            header = 8*maxLocals + maxStack + 145;
                        }
                    } else if (handlers == 2) {
                        if(maxLocals < 6 && maxStack < 6) {
                            header = 7*maxLocals + maxStack + 209;
                        }
                    }
                }
                codeHeaders.add(new Integer(header));
                if(header == 0) {
                    codeMaxStack.add(new Integer(maxStack));
                    codeMaxLocals.add(new Integer(maxLocals));
                    codeHandlerCount.add(new Integer(handlers));
                }
                for (int j = 0; j < exceptions.length; j++) {
                    int startPC = exceptions[j].getStartPC();
                    int endPC = exceptions[j].getEndPC();
                    int catchPC = exceptions[j].getHandlerPC();
                    int[] renumbered = renumberBCI(code.getCode());
                    int renumberedStart = renumbered[startPC];
                    int renumberedEnd = renumbered[endPC] - renumberedStart;
                    int renumberedCatch = renumbered[catchPC] - renumberedEnd;
                    codeHandlerStartP.add(new Integer(renumberedStart));
                    codeHandlerEndPO.add(new Integer(renumberedEnd));
                    codeHandlerCatchPO.add(new Integer(renumberedCatch));
                    int catchType = exceptions[j].getCatchType();
                    if(catchType == 0) {
                        codeHandlerClass.add(null);
                    } else {
                        String className = methods[i].getConstantPool().getConstantString(catchType, Constants.CONSTANT_Class);
                        codeHandlerClass.add(cpBands.getCPClass(className));
                    }
                }

                int flags = 0;
                Attribute[] attributes = methods[i].getAttributes();
                for (int j = 0; j < attributes.length; j++) {
                    if (attributes[j] instanceof LineNumberTable) {
                        flags |= (1<<1);
                    } else if (attributes[j] instanceof LocalVariableTable) {
                        flags |= (1<<2);
//                    } else if (attributes[j] instanceof LocalVariableTypeTable) {
//                        flags |= (1<<3);
                    } else if (attributes[j] instanceof Unknown) {
                        Unknown attr = (Unknown)attributes[i];
                        int index = attrBands.getIndex(attr);
                        flags |= (1<<index);

                    }
                }
                codeFlags.add(new Long(flags));
            }
        }
    }

    private int[] listToArray(List integerList) {
        int[] array = new int[integerList.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = ((Integer)integerList.get(i)).intValue();
        }
        return array;
    }

    private long[] longListToArray(List longList) {
        long[] array = new long[longList.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = ((Long)longList.get(i)).longValue();
        }
        return array;
    }

    private int[] renumberBCI(byte[] code) {
        int[] instructionBoundaries = getInstructionBoundaries(code);
        int[] renumbered = new int[code.length];
        int precedence = 1;
        for (int i = 1; i < instructionBoundaries.length; i++) {
            renumbered[instructionBoundaries[i]] = precedence;
            precedence++;
        }
        for (int i = 1; i < renumbered.length; i++) {
            if(renumbered[i] == 0) {
                renumbered[i] = precedence;
                precedence++;
            }
        }
        return renumbered;
    }

    private int[] getInstructionBoundaries(byte[] code) {
        List boundariesList = new ArrayList();
        boolean wide = false;
        for (int i = 0; i < code.length; i++) {
            int b = code[i] & 0xFF;
            boundariesList.add(new Integer(b));
            if(b == Constants.WIDE) {
                wide = true;
            } else if (b == Constants.TABLESWITCH) {
                int padding = (i + 1) % 4 == 0 ? 0 : 4 - i + 1;
                i+= padding;
                i+= 4;
                byte b1 = code[i];
                byte b2 = code[++i];
                byte b3 = code[++i];
                byte b4 = code[++i];
                int low = (b1 << 24) | (b2 << 16) | (b3 << 8) | b4;
                b1 = code[++i];
                b2 = code[++i];
                b3 = code[++i];
                b4 = code[++i];
                int high = (b1 << 24) | (b2 << 16) | (b3 << 8) | b4;
                int jumpTableSize = 4 * (high - low + 1);
                i += jumpTableSize;
            } else if (b == Constants.LOOKUPSWITCH) {
                int padding = (i + 1) % 4 == 0 ? 0 : 4 - i + 1;
                i+= padding;
                i+= 4;
                byte b1 = code[i];
                byte b2 = code[++i];
                byte b3 = code[++i];
                byte b4 = code[++i];
                int npairs = (b1 << 24) | (b2 << 16) | (b3 << 8) | b4;
                i += 8 * npairs;
            } else {
                if(b == 16 || b == 18 || (b >= 21 && b <= 25) || (b >= 54 && b <= 58) || b == 188) {
                    i++;
                    if(wide) {
                        i++;
                        wide = false;
                    }
                } else if (b == 17 || b == 19 || b == 20 || b== 132 || (b >= 153 && b <= 168)|| (b >= 178 && b <= 184) || b == 187 || b == 188 || b == 192 || b == 193 || b == 198 || b == 199) {
                    i+=2;
//                    don't think we need this...
//                    if(wide) {
//                        i+=2;
//                        wide = false;
//                    }
                } else if (b == 185 || b == 200 || b == 201) {
                    i+=4;
                }
            }
        }
        return listToArray(boundariesList);
    }

    public void finaliseBands() {
        int defaultMinorVersion = header.getDefaultMinorVersion();
        int defaultMajorVersion = header.getDefaultMajorVersion();

        for (int i = 0; i < class_flags.length; i++) {
            int major = major_versions[i];
            int minor = minor_versions[i];
            if(major != defaultMajorVersion || minor != defaultMinorVersion) {
                class_flags[i] |= 1 << 24;
                classFileVersionMajor.add(new Integer(major));
                classFileVersionMinor.add(new Integer(minor));
            }
        }
    }

    public void pack(OutputStream out) throws IOException, Pack200Exception {
        int[] classThis = new int[class_this.length];
        for (int i = 0; i < classThis.length; i++) {
            classThis[i] = class_this[i].getIndex();
        }
        out.write(encodeBandInt(classThis, Codec.DELTA5));

        int[] classSuper = new int[class_super.length];
        for (int i = 0; i < classSuper.length; i++) {
            classSuper[i] = class_super[i].getIndex();
        }
        out.write(encodeBandInt(classSuper, Codec.DELTA5));
        out.write(encodeBandInt(class_interface_count, Codec.DELTA5));

        int totalInterfaces = sum(class_interface_count);
        int[] classInterface = new int[totalInterfaces];
        int k = 0;
        for (int i = 0; i < class_interface.length; i++) {
            if(class_interface[i] != null) {
                for (int j = 0; j < class_interface[i].length; j++) {
                    CPClass cpClass = class_interface[i][j];
                    classInterface[k] = cpClass.getIndex();
                    k++;
                }
            }
        }
        out.write(encodeBandInt(classInterface, Codec.DELTA5));
        out.write(encodeBandInt(class_field_count, Codec.DELTA5));
        out.write(encodeBandInt(class_method_count, Codec.DELTA5));

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
        out.write(encodeBandInt(fieldDescr, Codec.DELTA5));
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
        out.write(encodeBandInt(methodDescr, Codec.DELTA5));

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

    private void writeFieldAttributeBands(OutputStream out) throws IOException, Pack200Exception {
        long[] field_flags = longListToArray(fieldFlags);
        int[] field_constant_value = listToArray(fieldConstantValueKQ);

        out.write(encodeFlags(field_flags, Codec.UNSIGNED5, Codec.UNSIGNED5, header.have_field_flags_hi()));
    }

    private void writeMethodAttributeBands(OutputStream out) throws IOException, Pack200Exception {
        long[] method_flags = longListToArray(methodFlags);

        out.write(encodeFlags(method_flags, Codec.UNSIGNED5, Codec.UNSIGNED5, header.have_method_flags_hi()));
    }

    private void writeClassAttributeBands(OutputStream out) throws IOException, Pack200Exception {
        out.write(encodeFlags(class_flags, Codec.UNSIGNED5, Codec.UNSIGNED5, header.have_class_flags_hi()));
        int[] class_source_file = new int[classSourceFile.size()];
        for (int i = 0; i < class_source_file.length; i++) {
            CPUTF8 sourceFile = (CPUTF8) classSourceFile.get(i);
            if(sourceFile == null) {
                class_source_file[i] = 0;
            } else {
                class_source_file[i] = sourceFile.getIndex() + 1;
            }
        }
        out.write(encodeBandInt(class_source_file, Codec.UNSIGNED5));

    }

    private void writeCodeBands(OutputStream out) throws IOException, Pack200Exception {
        int[] code_headers = listToArray(codeHeaders);
        int[] code_max_stack = listToArray(codeMaxStack);
        int[] code_max_na_locals = listToArray(codeMaxLocals);
        int[] code_handler_count = listToArray(codeHandlerCount);
        int[] code_handler_start_P = listToArray(codeHandlerStartP);
        int[] code_handler_end_PO = listToArray(codeHandlerEndPO);
        int[] code_handler_catch_PO = listToArray(codeHandlerCatchPO);
        int[] code_handler_class = new int[codeHandlerClass.size()];
        for (int j = 0; j < code_handler_class.length; j++) {
            CPClass cpClass = (CPClass) codeHandlerClass.get(j);
            code_handler_class[j] = cpClass == null ? 0 : cpClass.getIndex() + 1;
        }
        out.write(encodeBandInt(code_headers, Codec.BYTE1));
        out.write(encodeBandInt(code_max_stack, Codec.UNSIGNED5));
        out.write(encodeBandInt(code_max_na_locals, Codec.UNSIGNED5));
        out.write(encodeBandInt(code_handler_count, Codec.UNSIGNED5));
        out.write(encodeBandInt(code_handler_start_P, Codec.BCI5));
        out.write(encodeBandInt(code_handler_end_PO, Codec.BRANCH5));
        out.write(encodeBandInt(code_handler_catch_PO, Codec.BRANCH5));
        out.write(encodeBandInt(code_handler_class, Codec.UNSIGNED5));
        writeCodeAttributeBands(out);
    }

    private int[] flatten(int[][] twoDInts) {
        int[] ints = new int[totalSize(twoDInts)];
        int index = 0;
        for (int i = 0; i < twoDInts.length; i++) {
            for (int j = 0; j < twoDInts[i].length; j++) {
                ints[index] = twoDInts[i][j];
                index++;
            }
        }
        return ints;
    }

    private int totalSize(int[][] ints) {
        int count = 0;
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
                count++;
            }
        }
        return count;
    }

    private void writeCodeAttributeBands(OutputStream out) throws IOException, Pack200Exception {
        long[] code_flags = longListToArray(codeFlags);

        out.write(encodeFlags(code_flags, Codec.UNSIGNED5, Codec.UNSIGNED5, header.have_code_flags_hi()));
    }

}
