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
 * A New (i.e. non-predefined) Class File attribute
 */
public class NewAttribute extends Attribute {

    public NewAttribute(String attributeName) {
        super(attributeName);
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see org.apache.harmony.pack200.bytecode.Attribute#getLength()
     */
    protected int getLength() {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see org.apache.harmony.pack200.bytecode.Attribute#writeBody(java.io.DataOutputStream)
     */
    protected void writeBody(DataOutputStream dos) throws IOException {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.apache.harmony.pack200.bytecode.ClassFileEntry#toString()
     */
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }

    public void addInteger(int length, long value) {
        
    }

    public void addBCOffset(int length, long value) {
        // TODO Auto-generated method stub
        
    }

    public void addBCIndex(int length, long value) {
        // TODO Auto-generated method stub
        
    }

    public void addBCLength(int length, long value) {
        // TODO Auto-generated method stub
        
    }

    public void addCPConstant(int length, CPConstant constant) {
        // TODO Auto-generated method stub
        
    }

    public void addCPClass(int length, CPClass class1) {
        // TODO Auto-generated method stub
        
    }

    public void addCPUTF8(int length, CPUTF8 cputf8) {
        // TODO Auto-generated method stub
        
    }

    public void addCPNameAndType(int length, CPNameAndType type) {
        // TODO Auto-generated method stub
        
    }

    public void addCPFieldRef(int length, CPFieldRef ref) {
        // TODO Auto-generated method stub
        
    }

    public void addCPMethodRef(int length, CPMethodRef ref) {
        // TODO Auto-generated method stub
        
    }

    public void addCPIMethodRef(int length, CPInterfaceMethodRef ref) {
        // TODO Auto-generated method stub
        
    }
    
    
    

}
