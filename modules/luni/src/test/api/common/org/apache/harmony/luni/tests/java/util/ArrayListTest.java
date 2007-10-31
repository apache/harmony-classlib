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

package org.apache.harmony.luni.tests.java.util;

import java.util.ArrayList;

import junit.framework.TestCase;

public class ArrayListTest extends TestCase {

    public void test_addAllCollectionOfQextendsE() {
        // Regression for HARMONY-539
        // https://issues.apache.org/jira/browse/HARMONY-539
        ArrayList<String> alist = new ArrayList<String>();
        ArrayList<String> blist = new ArrayList<String>();
        alist.add("a");
        alist.add("b");
        blist.add("c");
        blist.add("d");
        blist.remove(0);
        blist.addAll(0, alist);
        assertEquals("a", blist.get(0));
        assertEquals("b", blist.get(1));
        assertEquals("d", blist.get(2));
    }

}