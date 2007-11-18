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

public class LocalVariableTypeTableAttribute extends Attribute {


    private int local_variable_type_table_length;
    private int[] start_pcs;
    private int[] lengths;
    private int[] name_indexes;
    private int[] signature_indexes;
    private int[] indexes;
    private CPUTF8[] names;
    private CPUTF8[] signatures;

    public LocalVariableTypeTableAttribute(int local_variable_type_table_length,
            int[] start_pcs, int[] lengths, CPUTF8[] names,
            CPUTF8[] signatures, int[] indexes) {
        super("LocalVariableTypeTable");
        this.local_variable_type_table_length = local_variable_type_table_length;
        this.start_pcs = start_pcs;
        this.lengths = lengths;
        this.names = names;
        this.signatures = signatures;
        this.indexes = indexes;
    }

    protected int getLength() {
        return 2 + (10 * local_variable_type_table_length);
    }

    protected void writeBody(DataOutputStream dos) throws IOException {
        dos.writeShort(local_variable_type_table_length);
        for (int i = 0; i < local_variable_type_table_length; i++) {
            dos.writeShort(start_pcs[i]);
            dos.writeShort(lengths[i]);
            dos.writeShort(name_indexes[i]);
            dos.writeShort(signature_indexes[i]);
            dos.writeShort(indexes[i]);
        }
    }

    protected void resolve(ClassConstantPool pool) {
        super.resolve(pool);
        name_indexes = new int[local_variable_type_table_length];
        signature_indexes = new int[local_variable_type_table_length];
        for (int i = 0; i < local_variable_type_table_length; i++) {
            names[i].resolve(pool);
            signatures[i].resolve(pool);
            name_indexes[i] = pool.indexOf(names[i]);
            signature_indexes[i] = pool.indexOf(signatures[i]);
        }
    }
    
    public String toString() {
        return "LocalVariableTypeTable: " + + local_variable_type_table_length + " varaibles";
    }

}
