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
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.beans;

import java.beans.Encoder;
import java.beans.Expression;
import java.lang.reflect.Array;

import junit.framework.TestCase;

/**
 * Test the internal class java.beans.ArrayPersistenceDelegate.
 */
public class ArrayPersistenceDelegateTest extends TestCase {

    private ArrayPersistenceDelegate pd = null;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        pd = new ArrayPersistenceDelegate();
    }

    public void testMutates() {
        assertFalse(pd.mutatesTo(new int[] { 1 }, null));
        assertFalse(pd.mutatesTo(null, null));
        assertFalse(pd.mutatesTo(new int[1], new int[2]));
        assertTrue(pd.mutatesTo(new int[] { 1, 3 }, new int[] { 1, 2 }));
    }

    public void testInitialize() {
        // TBD
    }

    public void testInstantiate_Normal() throws Exception {
        Object obj = new int[] { 1, 2, 3 };
        Expression exp = pd.instantiate(obj, new Encoder());
        assertSame(obj, exp.getValue());
        assertSame(Array.class, exp.getTarget());
        assertEquals("newInstance", exp.getMethodName());
        assertEquals(2, exp.getArguments().length);
        assertSame(Integer.TYPE, exp.getArguments()[0]);
        assertEquals(new Integer(3), exp.getArguments()[1]);
    }

}
