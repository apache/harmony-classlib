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
import java.util.Iterator;
import java.util.List;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

public class Segment implements ClassVisitor {

    private SegmentHeader segmentHeader;
    private CpBands cpBands;
    private AttributeDefinitionBands attributeDefinitionBands;
    private IcBands icBands;
    private ClassBands classBands;
    private BcBands bcBands;
    private FileBands fileBands;

    // The current class - only to be used when processing the classes
    private String currentClass;
    private String superClass;

    private final SegmentFieldVisitor fieldVisitor = new SegmentFieldVisitor();
    private final SegmentMethodVisitor methodVisitor = new SegmentMethodVisitor();
    private final SegmentAnnotationVisitor annotationVisitor = new SegmentAnnotationVisitor();
    private Pack200ClassReader currentClassReader;

    public void pack(List classes, List classNames, List classModTimes,
            List files, OutputStream out) throws IOException, Pack200Exception {
        segmentHeader = new SegmentHeader();
        segmentHeader.setFile_count(classes.size() + files.size()); // TODO:
                                                                    // files
        cpBands = new CpBands(segmentHeader);
        attributeDefinitionBands = new AttributeDefinitionBands(segmentHeader,
                cpBands);
        icBands = new IcBands(segmentHeader, cpBands);
        classBands = new ClassBands(segmentHeader, cpBands,
                attributeDefinitionBands, classes.size());
        bcBands = new BcBands(cpBands, this);
        fileBands = new FileBands(cpBands, segmentHeader, classNames,
                classModTimes, files);

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
            classReader.accept(this, 0);
        }
    }

    public void visit(int version, int access, String name, String signature,
            String superName, String[] interfaces) {
        currentClass = name;
        superClass = superName;
        bcBands.setCurrentClass(name);
        bcBands.setSuperClass(superName);
        segmentHeader.addMajorVersion(version);
        classBands.addClass(version, access, name, superName, interfaces);
    }

    public void visitSource(String source, String debug) {
        classBands.addSourceFile(source);
    }

    public void visitOuterClass(String owner, String name, String desc) {
        classBands.addEnclosingMethod(owner, name, desc);

    }

    public AnnotationVisitor visitAnnotation(String arg0, boolean arg1) {
        return annotationVisitor;
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

        public AnnotationVisitor visitAnnotation(String arg0, boolean arg1) {
            return annotationVisitor;
        }

        public AnnotationVisitor visitAnnotationDefault() {
            return annotationVisitor;
        }

        public void visitAttribute(Attribute arg0) {
            classBands.addUnknownMethodAttribute(arg0);
        }

        public void visitCode() {
            classBands.addCode();
        }

        public void visitFrame(int arg0, int arg1, Object[] arg2, int arg3,
                Object[] arg4) {
            // TODO Auto-generated method stub

        }

        public void visitLabel(Label label) {
            bcBands.visitLabel(label);
        }

        public void visitLineNumber(int line, Label start) {
            classBands.addLineNumber(line, start);
        }

        public void visitLocalVariable(String name, String desc,
                String signature, Label start, Label end, int index) {
            classBands.addLocalVariable(name, desc, signature, start, end,
                    index);
        }

        public void visitMaxs(int maxStack, int maxLocals) {
            classBands.addMaxStack(maxStack, maxLocals);
        }

        public AnnotationVisitor visitParameterAnnotation(int arg0,
                String arg1, boolean arg2) {
            return annotationVisitor;
        }

        public void visitTryCatchBlock(Label start, Label end, Label handler,
                String type) {
            classBands.addHandler(start, end, handler, type);
        }

        public void visitEnd() {
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

        public void visit(String arg0, Object arg1) {
            // TODO Auto-generated method stub

        }

        public AnnotationVisitor visitAnnotation(String arg0, String arg1) {
            // TODO Auto-generated method stub
            return null;
        }

        public AnnotationVisitor visitArray(String arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        public void visitEnd() {
            // TODO Auto-generated method stub

        }

        public void visitEnum(String arg0, String arg1, String arg2) {
            // TODO Auto-generated method stub

        }
    }

    public class SegmentFieldVisitor implements FieldVisitor {

        public AnnotationVisitor visitAnnotation(String arg0, boolean arg1) {
            return annotationVisitor;
        }

        public void visitAttribute(Attribute arg0) {
            classBands.addUnknownFieldAttribute(arg0);
        }

        public void visitEnd() {
            // TODO Auto-generated method stub

        }
    }

    public boolean lastConstantHadWideIndex() {
        return currentClassReader.lastConstantHadWideIndex();
    }
}
