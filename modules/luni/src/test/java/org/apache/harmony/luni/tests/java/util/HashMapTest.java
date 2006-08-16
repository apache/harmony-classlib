/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.luni.tests.java.util;

import java.util.HashMap;

import junit.framework.TestCase;
import tests.util.SerializationTester;

public class HashMapTest extends TestCase {
    public void test_serialization() throws Exception {
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("key", "value");
        SerializationTester.assertEquals(hm);
    }

    public void test_serializationCompatability() throws Exception {
        HashMap<String, String> hm = new HashMap<String, String>();
        hm.put("key", "value");
        SerializationTester.assertCompabilityEquals(hm,
                "serialization/java/util/HashMapTest.golden.ser");
    }
}
