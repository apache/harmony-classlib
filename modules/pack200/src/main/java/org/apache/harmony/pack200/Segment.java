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
import java.util.Iterator;
import java.util.List;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

public class Segment implements ClassVisitor {

    private SegmentHeader segmentHeader;
    private CpBands cpBands;
    private AttributeDefinitionBands attributeDefinitionBands;
    private IcBands icBands;
    private ClassBands classBands;
    private BcBands bcBands;
    private FileBands fileBands;

    private final SegmentFieldVisitor fieldVisitor = new SegmentFieldVisitor();
    private final SegmentMethodVisitor methodVisitor = new SegmentMethodVisitor();
    private Pack200ClassReader currentClassReader;
    private boolean stripDebug;
    private int effort;

    public void pack(List classes, List files, OutputStream out, boolean stripDebug, int effort)
            throws IOException, Pack200Exception {
        this.effort = effort;
        this.stripDebug = stripDebug;
        segmentHeader = new SegmentHeader();
        segmentHeader.setFile_count(files.size());
        segmentHeader.setHave_all_code_flags(!stripDebug);
        cpBands = new CpBands(this, effort);
        attributeDefinitionBands = new AttributeDefinitionBands(this, effort);
        icBands = new IcBands(segmentHeader, cpBands, effort);
        classBands = new ClassBands(this, classes.size(), effort);
        bcBands = new BcBands(cpBands, this, effort);
        fileBands = new FileBands(cpBands, segmentHeader, files, classes, effort);

        processClasses(classes);

        cpBands.finaliseBands();
        attributeDefinitionBands.finaliseBands();
        icBands.finaliseBands();
        classBands.finaliseBands();
        bcBands.finaliseBands();
        fileBands.finaliseBands();

        segmentHeader.pack(out);
        cpBands.pack(out);
        attributeDefinitionBands.pack(out);
        icBands.pack(out);
        classBands.pack(out);
        bcBands.pack(out);
        fileBands.pack(out);
    }

    private void processClasses(List classes) {
        segmentHeader.setClass_count(classes.size());
        for (Iterator iterator = classes.iterator(); iterator.hasNext();) {
            Pack200ClassReader classReader = (Pack200ClassReader) iterator
                    .next();
            currentClassReader = classReader;
            int flags = ClassReader.SKIP_FRAMES;
            if(stripDebug) {
                flags |= ClassReader.SKIP_DEBUG;
            }
            classReader.accept(this, flags);
        }
    }

    public void visit(int version, int access, String name, String signature,
            String superName, String[] interfaces) {
        bcBands.setCurrentClass(name, superName);
        segmentHeader.addMajorVersion(version);
        classBands.addClass(version, access, name, signature, superName,
                interfaces);
    }

    public void visitSource(String source, String debug) {
        if(!stripDebug) {
            classBands.addSourceFile(source);
        }
    }

    public void visitOuterClass(String owner, String name, String desc) {
        classBands.addEnclosingMethod(owner, name, desc);

    }

    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        return new SegmentAnnotationVisitor(MetadataBandGroup.CONTEXT_CLASS,
                desc, visible);
    }

    public void visitAttribute(Attribute arg0) {
    }

    public void visitInnerClass(String name, String outerName,
            String innerName, int flags) {
        icBands.addInnerClass(name, outerName, innerName, flags);
    }

    public FieldVisitor visitField(int flags, String name, String desc,
            String signature, Object value) {
        classBands.addField(flags, name, desc, signature, value);
        return fieldVisitor;
    }

    public MethodVisitor visitMethod(int flags, String name, String desc,
            String signature, String[] exceptions) {
        classBands.addMethod(flags, name, desc, signature, exceptions);
        return methodVisitor;
    }

    public void visitEnd() {
        classBands.endOfClass();
    }

    /*
     * This class delegates to BcBands for bytecode related visits and to
     * ClassBands for everything else
     */
    public class SegmentMethodVisitor implements MethodVisitor {

        public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
            return new SegmentAnnotationVisitor(
                    MetadataBandGroup.CONTEXT_METHOD, desc, visible);
        }

        public AnnotationVisitor visitAnnotationDefault() {
            return new SegmentAnnotationVisitor(MetadataBandGroup.CONTEXT_METHOD);
        }

        public void visitAttribute(Attribute arg0) {
            classBands.addUnknownMethodAttribute(arg0);
        }

        public void visitCode() {
            classBands.addCode(stripDebug);
        }

        public void visitFrame(int arg0, int arg1, Object[] arg2, int arg3,
                Object[] arg4) {
            // TODO Auto-generated method stub

        }

        public void visitLabel(Label label) {
            bcBands.visitLabel(label);
        }

        public void visitLineNumber(int line, Label start) {
            if(!stripDebug) {
                classBands.addLineNumber(line, start);
            }
        }

        public void visitLocalVariable(String name, String desc,
                String signature, Label start, Label end, int index) {
            if(!stripDebug) {
                classBands.addLocalVariable(name, desc, signature, start, end,
                        index);
            }
        }

        public void visitMaxs(int maxStack, int maxLocals) {
            classBands.addMaxStack(maxStack, maxLocals);
        }

        public AnnotationVisitor visitParameterAnnotation(int parameter,
                String desc, boolean visible) {
            return new SegmentAnnotationVisitor(
                    MetadataBandGroup.CONTEXT_METHOD, parameter, desc, visible);
        }

        public void visitTryCatchBlock(Label start, Label end, Label handler,
                String type) {
            classBands.addHandler(start, end, handler, type);
        }

        public void visitEnd() {
            classBands.endOfMethod();
            bcBands.visitEnd();
        }

        public void visitFieldInsn(int opcode, String owner, String name,
                String desc) {
            bcBands.visitFieldInsn(opcode, owner, name, desc);
        }

        public void visitIincInsn(int var, int increment) {
            bcBands.visitIincInsn(var, increment);
        }

        public void visitInsn(int opcode) {
            bcBands.visitInsn(opcode);
        }

        public void visitIntInsn(int opcode, int operand) {
            bcBands.visitIntInsn(opcode, operand);
        }

        public void visitJumpInsn(int opcode, Label label) {
            bcBands.visitJumpInsn(opcode, label);
        }

        public void visitLdcInsn(Object cst) {
            bcBands.visitLdcInsn(cst);
        }

        public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
            bcBands.visitLookupSwitchInsn(dflt, keys, labels);
        }

        public void visitMethodInsn(int opcode, String owner, String name,
                String desc) {
            bcBands.visitMethodInsn(opcode, owner, name, desc);
        }

        public void visitMultiANewArrayInsn(String desc, int dimensions) {
            bcBands.visitMultiANewArrayInsn(desc, dimensions);
        }

        public void visitTableSwitchInsn(int min, int max, Label dflt,
                Label[] labels) {
            bcBands.visitTableSwitchInsn(min, max, dflt, labels);
        }

        public void visitTypeInsn(int opcode, String type) {
            bcBands.visitTypeInsn(opcode, type);
        }

        public void visitVarInsn(int opcode, int var) {
            bcBands.visitVarInsn(opcode, var);
        }

    }

    public ClassBands getClassBands() {
        return classBands;
    }

    public class SegmentAnnotationVisitor implements AnnotationVisitor {

        private int context = -1;
        private int parameter = -1;
        private String desc;
        private boolean visible;

        private final List nameRU = new ArrayList();
        private final List T = new ArrayList(); // tags
        private final List values = new ArrayList();
        private final List caseArrayN = new ArrayList();
        private final List nestTypeRS = new ArrayList();
        private final List nestNameRU = new ArrayList();
        private final List nestPairN = new ArrayList();


        public SegmentAnnotationVisitor(int context, String desc,
                boolean visible) {
            this.context = context;
            this.desc = desc;
            this.visible = visible;
        }

        public SegmentAnnotationVisitor(int context) {
            this.context = context;
        }

        public SegmentAnnotationVisitor(int context, int parameter,
                String desc, boolean visible) {
            this.context = context;
            this.parameter = parameter;
            this.desc = desc;
            this.visible = visible;
        }

        public void visit(String name, Object value) {
            if (name == null) {
                name = "";
            }
            nameRU.add(name);
            values.add(value);
            addTag(value);
        }

        private void addTag(Object value) {
            if(value instanceof Integer) {
                T.add("I");
            } else if (value instanceof Double) {
                T.add("D");
            } else if (value instanceof Float) {
                T.add("F");
            } else if (value instanceof Long) {
                T.add("J");
            } else if (value instanceof Byte) {
                T.add("B");
            } else if (value instanceof Character) {
                T.add("C");
            } else if (value instanceof Short) {
                T.add("S");
            } else if (value instanceof Boolean) {
                T.add("Z");
            } else if (value instanceof String) {
                T.add("s");
            } else if (value instanceof Type) {
                T.add("c");
            }
        }

        public AnnotationVisitor visitAnnotation(String name, String desc) {
            T.add("@");
            if (name == null) {
                name = "";
            }
            nameRU.add(name);
            nestTypeRS.add(desc);
            nestPairN.add(new Integer(0));
            return new AnnotationVisitor() {
                public void visit(String name, Object value) {
                    Integer numPairs = (Integer) nestPairN.remove(nestPairN.size() - 1);
                    nestPairN.add(new Integer(numPairs.intValue() + 1));
                    nestNameRU.add(name);
                    values.add(value);
                    addTag(value);
                }

                public AnnotationVisitor visitAnnotation(String arg0,
                        String arg1) {
                    throw new RuntimeException("Not yet supported");
//                    return null;
                }

                public AnnotationVisitor visitArray(String arg0) {
                    throw new RuntimeException("Not yet supported");
//                    return null;
                }

                public void visitEnd() {
                    throw new RuntimeException("Not yet supported");
                }

                public void visitEnum(String name, String desc, String value) {
                    Integer numPairs = (Integer) nestPairN.remove(nestPairN.size() - 1);
                    nestPairN.add(new Integer(numPairs.intValue() + 1));
                    T.add("e");
                    nestNameRU.add(name);
                    values.add(desc);
                    values.add(value);
                }
            };
        }

        public AnnotationVisitor visitArray(String name) {
            T.add("[");
            if (name == null) {
                name = "";
            }
            nameRU.add(name);
            caseArrayN.add(new Integer(0));
            return new AnnotationVisitor() {
                public void visit(String name, Object value) {
                    Integer numCases = (Integer) caseArrayN.remove(caseArrayN.size() - 1);
                    caseArrayN.add(new Integer(numCases.intValue() + 1));
                    if (name == null) {
                        name = "";
                    }
                    nameRU.add(name);
                    values.add(value);
                    addTag(value);
                }

                public AnnotationVisitor visitAnnotation(String arg0,
                        String arg1) {
                    throw new RuntimeException("Not yet supported");
                }

                public AnnotationVisitor visitArray(String arg0) {
                    throw new RuntimeException("Not yet supported");
//                    return null;
                }

                public void visitEnd() {
                }

                public void visitEnum(String name, String desc, String value) {
                    Integer numCases = (Integer) caseArrayN.remove(caseArrayN.size() - 1);
                    caseArrayN.add(new Integer(numCases.intValue() + 1));
                    T.add("e");
                    if(name == null) {
                        name = "";
                    }
                    nameRU.add(name);
                    values.add(desc);
                    values.add(value);
                }
            };
        }

        public void visitEnd() {
            if (desc == null) {
                Segment.this.classBands.addAnnotationDefault(nameRU, T, values, caseArrayN, nestTypeRS, nestNameRU, nestPairN);
            } else if(parameter != -1) {
                Segment.this.classBands.addParameterAnnotation(parameter, desc, visible, nameRU, T, values, caseArrayN, nestTypeRS, nestNameRU, nestPairN);
            } else {
                Segment.this.classBands.addAnnotation(context, desc, visible, nameRU, T, values, caseArrayN, nestTypeRS, nestNameRU, nestPairN);
            }
        }

        public void visitEnum(String name, String desc, String value) {
            T.add("e");
            if (name == null) {
                name = "";
            }
            nameRU.add(name);
            values.add(desc);
            values.add(value);
        }
    }

    public class SegmentFieldVisitor implements FieldVisitor {

        public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
            return new SegmentAnnotationVisitor(MetadataBandGroup.CONTEXT_FIELD,
                    desc, visible);
        }

        public void visitAttribute(Attribute arg0) {
            classBands.addUnknownFieldAttribute(arg0);
        }

        public void visitEnd() {
        }
    }

    public boolean lastConstantHadWideIndex() {
        return currentClassReader.lastConstantHadWideIndex();
    }

    public CpBands getCpBands() {
        return cpBands;
    }

    public SegmentHeader getSegmentHeader() {
        return segmentHeader;
    }

    public AttributeDefinitionBands getAttrBands() {
        return attributeDefinitionBands;
    }

    public IcBands getIcBands() {
        return icBands;
    }

    public Pack200ClassReader getCurrentClassReader() {
        return currentClassReader;
    }
}
