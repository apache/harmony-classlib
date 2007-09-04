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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.harmony.pack200.bytecode.ByteCode;
import org.apache.harmony.pack200.bytecode.CodeAttribute;

/**
 *
 */
public class BcBands extends BandSet {
    
    private byte[][][] methodByteCodePacked;

    /**
     * @param header
     */
    public BcBands(Segment segment) {
        super(segment);
    }

    /* (non-Javadoc)
     * @see org.apache.harmony.pack200.BandSet#pack(java.io.OutputStream)
     */
    public void pack(OutputStream outputStream) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see org.apache.harmony.pack200.BandSet#unpack(java.io.InputStream)
     */
    public void unpack(InputStream in) throws IOException,
            Pack200Exception {
        
       AttributeLayoutMap attributeDefinitionMap = segment.getAttrDefinitionBands().getAttributeDefinitionMap();
       int classCount = header.getClassCount();
       long[][] methodFlags = segment.getClassBands().getMethodFlags();
       int[] codeMaxNALocals = segment.getClassBands().getCodeMaxNALocals();
       int[] codeMaxStack = segment.getClassBands().getCodeMaxStack();
       ArrayList[][] methodAttributes = segment.getClassBands().getMethodAttributes();
       String[][] methodDescr = segment.getClassBands().getMethodDescr();
       
       int bcStringRefCount = 0;
       int bcInitRefCount = 0;
       int bcFieldRefCount = 0;
       int bcThisFieldCount = 0;
       int bcMethodRefCount = 0;
       int bcIMethodRefCount = 0;

       AttributeLayout abstractModifier = attributeDefinitionMap
               .getAttributeLayout(AttributeLayout.ACC_ABSTRACT,
                       AttributeLayout.CONTEXT_METHOD);
       AttributeLayout nativeModifier = attributeDefinitionMap
               .getAttributeLayout(AttributeLayout.ACC_NATIVE,
                       AttributeLayout.CONTEXT_METHOD);
       AttributeLayout staticModifier = attributeDefinitionMap
               .getAttributeLayout(AttributeLayout.ACC_STATIC,
                       AttributeLayout.CONTEXT_METHOD);
       methodByteCodePacked = new byte[classCount][][];
       int bcParsed = 0;
       for (int c = 0; c < classCount; c++) {
           int numberOfMethods = methodFlags[c].length;
           methodByteCodePacked[c] = new byte[numberOfMethods][];
           for (int m = 0; m < numberOfMethods; m++) {
               long methodFlag = methodFlags[c][m];
               if (!abstractModifier.matches(methodFlag)
                       && !nativeModifier.matches(methodFlag)) {
                   ByteArrayOutputStream codeBytes = new ByteArrayOutputStream();
                   byte code;
                   while ((code = (byte) (0xff & in.read())) != -1)
                       codeBytes.write(code);
                   methodByteCodePacked[c][m] = codeBytes.toByteArray();
                   bcParsed += methodByteCodePacked[c][m].length;
                   for (int i = 0; i < methodByteCodePacked[c][m].length; i++) {
                       int codePacked = 0xff & methodByteCodePacked[c][m][i];
                       // TODO a lot of this needs to be encapsulated in the
                       // place that
                       // calculates what the arguments are, since (a) it will
                       // need
                       // to know where to get them, and (b) what to do with
                       // them
                       // once they've been gotten. But that's for another
                       // time.
                       switch (codePacked) {
                       case 18: // (a)ldc
                           bcStringRefCount++;
                           break;
                       case 178: // getstatic
                       case 179: // putstatic
                       case 180: // getfield
                       case 181: // putfield
                           bcFieldRefCount++;
                           break;
                       case 182: // invokevirtual
                       case 183: // invokespecial
                       case 184: // invokestatic
                           bcMethodRefCount++;
                           break;
                       case 185: // invokeinterface
                           bcIMethodRefCount++;
                           break;
                       case 202: // getstatic_this
                       case 203: // putstatic_this
                       case 204: // getfield_this
                       case 205: // putfield_this
                           bcThisFieldCount++;
                           break;
                       case 231: // invoke_special_init
                           bcInitRefCount++;
                           break;

                       default: // unhandled specifically at this stage
                           debug("Found unhandled "
                            + ByteCode.getByteCode(codePacked));
                       }
                   }
               }
            }
        }
       // other bytecode bands
       debug("Parsed *bc_codes (" + bcParsed + ")");
       debug("unimplemented bc_case_count");
       debug("unimplemented bc_case_value");
       debug("unimplemented bc_byte");
       debug("unimplemented bc_short");
       debug("unimplemented bc_local");
       debug("unimplemented bc_label");
       debug("unimplemented bc_intref");
       debug("unimplemented bc_floatref");
       debug("unimplemented bc_longref");
       debug("unimplemented bc_doubleref");
       int[] bcStringRef = decodeBandInt("bc_stringref", in, Codec.DELTA5,
               bcStringRefCount);
       debug("unimplemented bc_classref");
       int[] bcFieldRef = decodeBandInt("bc_fieldref", in, Codec.DELTA5,
               bcFieldRefCount);
       int[] bcMethodRef = decodeBandInt("bc_methodref", in, Codec.UNSIGNED5,
               bcMethodRefCount);
       int[] bcIMethodRef = decodeBandInt("bc_imethodref", in, Codec.DELTA5,
               bcIMethodRefCount);
       int[] bcThisField = decodeBandInt("bc_thisfield", in, Codec.UNSIGNED5,
               bcThisFieldCount);
       debug("unimplemented bc_superfield");
       debug("unimplemented bc_thismethod");
       debug("unimplemented bc_supermethod");
       debug("unimplemented bc_initref");
       int[] bcInitRef = decodeBandInt("bc_initref", in, Codec.UNSIGNED5,
               bcInitRefCount);
       debug("unimplemented bc_escref");
       debug("unimplemented bc_escrefsize");
       debug("unimplemented bc_escsize");
       debug("unimplemented bc_escbyte");
       int i = 0;
       for (int c = 0; c < classCount; c++) {
           int numberOfMethods = methodFlags[c].length;
           for (int m = 0; m < numberOfMethods; m++) {
               long methodFlag = methodFlags[c][m];
               if (!abstractModifier.matches(methodFlag)
                       && !nativeModifier.matches(methodFlag)) {
                   int maxStack = codeMaxStack[i];
                   int maxLocal = codeMaxNALocals[i];
                   if (!staticModifier.matches(methodFlag))
                       maxLocal++; // one for 'this' parameter
                   maxLocal += SegmentUtils.countArgs(methodDescr[c][m]);
                   // TODO Move creation of code attribute until after constant
                   // pool resolved
                   CodeAttribute attr = new CodeAttribute(maxStack, maxLocal,
                           methodByteCodePacked[c][m]);
                   methodAttributes[c][m].add(attr);
                   i++;
               }
           }
       }

    }

}
