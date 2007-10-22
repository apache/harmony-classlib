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
package org.apache.harmony.pack200.tests;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.harmony.pack200.ClassBands;
import org.apache.harmony.pack200.Codec;
import org.apache.harmony.pack200.CpBands;
import org.apache.harmony.pack200.Pack200Exception;
import org.apache.harmony.pack200.Segment;

/**
 *
 */
public class ClassBandsTest extends AbstractBandsTestCase {
    
    private String[] cpClasses;
    private String[] cpDescriptor;
    
    public class MockCpBands extends CpBands {

        public MockCpBands(Segment segment) {
            super(segment);
        }
        
        public String[] getCpClass() {
            return cpClasses;
        }
        
        public String[] getCpDescriptor() {
            return cpDescriptor;
        }

    }

    public class MockSegment extends AbstractBandsTestCase.MockSegment {
        protected CpBands getCpBands() {
            return new MockCpBands(this);
        }       
        
    }
    
    
    
    ClassBands classBands = new ClassBands(new MockSegment());
    
    public void testSimple() throws IOException, Pack200Exception {
        cpClasses = new String[] { "Class1", "Class2", "Class3", "Interface1", "Interface2" };
        byte[] classThis = Codec.DELTA5.encode(1, 0);
        byte[] classSuper = Codec.DELTA5.encode(2, 0);
        byte[] classInterfaceCount = Codec.DELTA5.encode(2, 0);
        byte[] classInterfaceRef1 = classBands.encodeBandLong(new long[] {3, 4}, Codec.DELTA5);
        byte[] classFieldCount = Codec.DELTA5.encode(0, 0);
        byte[] classMethodCount = Codec.DELTA5.encode(0, 0);
        byte[] classFlags = Codec.UNSIGNED5.encode(0, 0);
        byte[][] allArrays = new byte[][] { classThis, classSuper,
                classInterfaceCount, classInterfaceRef1, classFieldCount,
                classMethodCount, classFlags };
        int total = classThis.length + classSuper.length
                + classInterfaceCount.length + classInterfaceRef1.length
                + classFieldCount.length + classMethodCount.length
                + classFlags.length;
        byte[] bytes = new byte[total];
        int index = 0;
        for (int i = 0; i < allArrays.length; i++) {
            for (int j = 0; j < allArrays[i].length; j++) {
                bytes[index] = allArrays[i][j];
                index++;
            }
        }
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        classBands.unpack(in);
        assertEquals(cpClasses[1], classBands.getClassThis()[0]);
        assertEquals(cpClasses[2], classBands.getClassSuper()[0]);
        assertEquals(1, classBands.getClassInterfaces().length);
        assertEquals(2, classBands.getClassInterfaces()[0].length);
        assertEquals(cpClasses[3], classBands.getClassInterfaces()[0][0]);
        assertEquals(cpClasses[4], classBands.getClassInterfaces()[0][1]);
        cpClasses = null;
    }
    
    public void testWithMethods() throws Pack200Exception, IOException {
        cpClasses = new String[] { "Class1", "Class2", "Class3" };
        cpDescriptor = new String[] {"method1", "method2", "method3"};
        byte[] classThis = Codec.DELTA5.encode(1, 0);
        byte[] classSuper = Codec.DELTA5.encode(2, 0);
        byte[] classInterfaceCount = Codec.DELTA5.encode(0, 0);
        byte[] classFieldCount = Codec.DELTA5.encode(0, 0);
        byte[] classMethodCount = Codec.DELTA5.encode(3, 0);
        byte[] methodDescr = classBands.encodeBandLong(new long[]{0, 1, 2}, Codec.MDELTA5);
        byte[] methodFlagsLo = classBands.encodeBandLong(new long[]{0, 0, 0}, Codec.UNSIGNED5);
        byte[] classFlags = Codec.UNSIGNED5.encode(0, 0);
        byte[][] allArrays = new byte[][] { classThis, classSuper,
                classInterfaceCount, classFieldCount, classMethodCount, 
                methodDescr, methodFlagsLo, classFlags, };
        int total = 0;
        for (int i = 0; i < allArrays.length; i++) {
            total += allArrays[i].length;
        }
        byte[] bytes = new byte[total];
        int index = 0;
        for (int i = 0; i < allArrays.length; i++) {
            for (int j = 0; j < allArrays[i].length; j++) {
                bytes[index] = allArrays[i][j];
                index++;
            }
        }
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        classBands.unpack(in);
        assertEquals(cpClasses[1], classBands.getClassThis()[0]);
        assertEquals(cpClasses[2], classBands.getClassSuper()[0]);
        assertEquals(1, classBands.getMethodDescr().length);
        assertEquals(3, classBands.getMethodDescr()[0].length);
        assertEquals(cpDescriptor[0], classBands.getMethodDescr()[0][0]);
        assertEquals(cpDescriptor[1], classBands.getMethodDescr()[0][1]);
        assertEquals(cpDescriptor[2], classBands.getMethodDescr()[0][2]);
        
        cpClasses = null;
        cpDescriptor = null;
    }
    
    public void testWithFields() {
        
    }
    
    
    
}
