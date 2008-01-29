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

import junit.framework.TestCase;
import org.apache.harmony.pack200.IcTuple;

public class ICTupleTest extends TestCase {
    
    public void testPredictedClassTupleParsing() {
        IcTuple tuple = new IcTuple();
        tuple.C = "orw/SimpleHelloWorld$SimpleHelloWorldInner";
        tuple.C2 = null;
        tuple.F = 0;
        tuple.N = null;
        assertEquals("SimpleHelloWorldInner", tuple.simpleClassName());
        assertEquals("orw/SimpleHelloWorld", tuple.outerClassString());

        tuple = new IcTuple();
        tuple.C = "java/util/AbstractList$2$Local";
        tuple.C2 = null;
        tuple.F = 0;
        tuple.N = null;
        assertEquals("Local", tuple.simpleClassName());
        assertEquals("java/util/AbstractList$2", tuple.outerClassString());

        tuple = new IcTuple();
        tuple.C = "java/util/AbstractList#2#Local";
        tuple.C2 = null;
        tuple.F = 0;
        tuple.N = null;
        assertEquals("Local", tuple.simpleClassName());
        assertEquals("java/util/AbstractList$2", tuple.outerClassString());

        tuple = new IcTuple();
        tuple.C = "java/util/AbstractList$1";
        tuple.C2 = null;
        tuple.F = 0;
        tuple.N = null;
        assertEquals("1", tuple.simpleClassName());
        assertEquals("java/util/AbstractList", tuple.outerClassString());
    }
    
    public void testExplicitClassTupleParsing() {
        IcTuple tuple = new IcTuple();
        tuple.C = "Foo$$2$Local";
        tuple.C2 = null;
        tuple.F = IcTuple.NESTED_CLASS_FLAG;
        tuple.N = "$2$Local";
        assertEquals("$2$Local", tuple.simpleClassName());
        assertEquals(null, tuple.outerClassString());

        tuple = new IcTuple();
        tuple.C = "Red$Herring";
        tuple.C2 = "Red$Herring";
        tuple.F = IcTuple.NESTED_CLASS_FLAG;
        tuple.N = null;
        assertEquals(null, tuple.simpleClassName());
        assertEquals("Red$Herring", tuple.outerClassString());

        tuple = new IcTuple();
        tuple.C = "X$1$Q";
        tuple.C2 = "X$1";
        tuple.F = IcTuple.NESTED_CLASS_FLAG;
        tuple.N = "Q";
        assertEquals("Q", tuple.simpleClassName());
        assertEquals("X$1", tuple.outerClassString());
    }
}
