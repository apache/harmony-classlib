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
package org.apache.harmony.pack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Enclosing method class file attribute.
 */
public class EnclosingMethodAttribute extends Attribute {

    private int class_index;
    private int method_index;
    private final CPClass cpClass;
    private final CPNameAndType method;
    private static final CPUTF8 attributeName = new CPUTF8(
            "EnclosingMethod", ClassConstantPool.DOMAIN_ATTRIBUTEASCIIZ); //$NON-NLS-1$

    public EnclosingMethodAttribute(CPClass cpClass, CPNameAndType method) {
        super(attributeName);
        this.cpClass = cpClass;
        this.method = method;
    }

    /* (non-Javadoc)
     * @see org.apache.harmony.pack200.bytecode.Attribute#getLength()
     */
    protected int getLength() {
        return 4;
    }

    protected void resolve(ClassConstantPool pool) {
        super.resolve(pool);
        cpClass.resolve(pool);
        class_index = pool.indexOf(cpClass);
        method.resolve(pool);
        method_index = pool.indexOf(method);
    }

    /* (non-Javadoc)
     * @see org.apache.harmony.pack200.bytecode.Attribute#writeBody(java.io.DataOutputStream)
     */
    protected void writeBody(DataOutputStream dos) throws IOException {
        dos.writeShort(class_index);
        dos.writeShort(method_index);
    }

    /* (non-Javadoc)
     * @see org.apache.harmony.pack200.bytecode.ClassFileEntry#toString()
     */
    public String toString() {
        return "EnclosingMethod";
    }

}
