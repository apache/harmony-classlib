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

import java.io.OutputStream;

import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;


public class ClassBands extends BandSet {

    private final CpBands cpBands;

    public ClassBands(CpBands cpBands, int numClasses) {
        this.cpBands = cpBands;
        class_this = new CPClass[numClasses];
        class_super = new CPClass[numClasses];
        class_interface_count = new int[numClasses];
        class_interface = new CPClass[numClasses][];
        class_field_count = new int[numClasses];
        class_method_count = new int[numClasses];
        field_descr = new CPNameAndType[numClasses][];
        method_descr = new CPNameAndType[numClasses][];
    }

    private int index = 0;

    private final CPClass[] class_this;
    private final CPClass[] class_super;
    private final int[] class_interface_count;
    private final CPClass[][] class_interface;
    private final int[] class_field_count;
    private final int[] class_method_count;
    private final CPNameAndType[][] field_descr;
    private final CPNameAndType[][] method_descr;

    public void finaliseBands() {
        
    }

    public void pack(OutputStream out) {
        // TODO Auto-generated method stub

    }

    public void addClass(JavaClass obj) {
        class_this[index] = cpBands.getCPClass(obj.getClassName());
        class_super[index] = cpBands.getCPClass(obj.getSuperclassName());
        String[] interfaces = obj.getInterfaceNames();
        class_interface_count[index] = interfaces.length;
        for (int i = 0; i < interfaces.length; i++) {
            class_interface[index][i] = cpBands.getCPClass(interfaces[i]);
        }

        Field[] fields = obj.getFields();
        class_field_count[index] = fields.length;
        field_descr[index] = new CPNameAndType[fields.length];
        for (int i = 0; i < fields.length; i++) {
            field_descr[index][i] = cpBands.getCPNameAndType(fields[i].getName(), fields[i].getSignature());
        }

        Method[] methods = obj.getMethods();
        class_method_count[index] = methods.length;
        method_descr[index] = new CPNameAndType[methods.length];
        for (int i = 0; i < methods.length; i++) {
            method_descr[index][i] = cpBands.getCPNameAndType(methods[i].getName(), methods[i].getSignature());
        }

        index++;
    }

}
