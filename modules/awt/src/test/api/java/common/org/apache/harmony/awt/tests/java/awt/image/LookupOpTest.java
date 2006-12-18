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

package org.apache.harmony.awt.tests.java.awt.image;

import java.awt.RenderingHints;
import java.awt.image.LookupOp;
import java.awt.image.LookupTable;

import junit.framework.TestCase;

public class LookupOpTest extends TestCase {

    public LookupOpTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'java.awt.image.LookupOp.LookupOp(LookupTable, RenderingHints)'
     * when the LookupTable argument is null.
     */
    public final void test_LookupOp_LookupTableRenderingHints_NullLookupTable() {
        // regression test for Harmony-1629
        RenderingHints hints = new RenderingHints(null);
        try {
            LookupOp returnValue = new LookupOp((LookupTable)null, hints);
            fail("NullPointerException expected");
        } catch (NullPointerException npe) {
            // expected
        }
    }

    

}
