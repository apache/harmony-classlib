/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author  Mikhail A. Markov
 * @version $Revision: 1.1.2.1 $
 */
package org.apache.harmony.rmi;

import java.rmi.MarshalledObject;

import java.util.Hashtable;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Unit test for java.rmi.MarshalledObject class.
 *
 * @author  Mikhail A. Markov
 * @version $Revision: 1.1.2.1 $
 */
public class MarshalledObjectTest extends TestCase {

    /**
     * No-arg constructor to enable serialization.
     */
    public MarshalledObjectTest() {
        super();
    }

    /**
     * Constructs this test case with the given name.
     *
     * @param   name
     *          Name for this test case.
     */
    public MarshalledObjectTest(String name) {
        super(name);
    }

    /**
     * Tests {@link MarshalledObject#equals(Object)} method.
     *
     * @throws  Exception
     *          If some error occurs.
     */
    public void testEquals() throws Exception {
        String str = new String("TEST");
        String str1 = new String("TEST");
        String str2 = new String("TEST2");

        assertTrue(new MarshalledObject(str).equals(
                new MarshalledObject(str1)));
        assertTrue(! new MarshalledObject(str).equals(
                new MarshalledObject(str2)));
    }

    /**
     * Tests {@link MarshalledObject#get()} method.
     *
     * @throws  Exception
     *          If some error occurs.
     */
    public void testGet() throws Exception {
        assertTrue(new MarshalledObject(null).get() == null);
        String str = new String("TEST");
        assertEquals(str, new MarshalledObject(str).get());
        Hashtable ht = new Hashtable();
        ht.put(new Integer(1), str);
        ht.put(new Integer(2), "TEST1");
        MarshalledObject mo = new MarshalledObject(ht);
        assertEquals(ht, mo.get());
        ht.put(new Integer(2), "TEST2");
        assertTrue(! ht.equals(mo.get()));
    }

    /**
     * Returns test suite for this class.
     *
     * @return  Test suite for this class.
     */
    public static Test suite() {
        return new TestSuite(MarshalledObjectTest.class);
    }

    /**
     * Starts the testing from the command line.
     *
     * @param   args
     *          Command line parameters.
     */
    public static void main(String args[]) {
        junit.textui.TestRunner.run(suite());
    }
}
