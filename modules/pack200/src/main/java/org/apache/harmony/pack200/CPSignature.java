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

import java.util.List;

public class CPSignature extends ConstantPoolEntry implements Comparable {

    private final CPUTF8 signatureForm;
    private final List classes;
    private final String signature;

    public CPSignature(String signature, CPUTF8 signatureForm, List classes) {
        this.signature = signature;
        this.signatureForm = signatureForm;
        this.classes = classes;
    }

    public int compareTo(Object arg0) {
        return signature.compareTo(((CPSignature)arg0).signature);
    }

    public int getIndexInCpUtf8() {
        return signatureForm.getIndex();
    }

    public List getClasses() {
        return classes;
    }

    public String toString() {
        return signature;
    }

    public String getUnderlyingString() {
        return signature;
    }

    public CPUTF8 getSignatureForm() {
        return signatureForm;
    }
}
