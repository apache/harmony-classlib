/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.apache.harmony.luni.tests.java.util;

import java.util.IdentityHashMap;

import junit.framework.TestCase;

public class IdentityHashMapTest extends TestCase {

	/**
	 * @tests java.util.IdentityHashMap#put(java.lang.Object, java.lang.Object)
	 */
	public void test_putLjava_lang_ObjectLjava_lang_Object() {
		IdentityHashMap<Object, Object> map = new IdentityHashMap<Object, Object>();
		
		// Test null as a key.
		Object value = "Some value";
		map.put(null, value);
		assertSame("Assert 0: Failure getting null key", value, map.get(null));
		
		// Test null as a value
		Object key = "Some key";
		map.put(key, null);
		assertNull("Assert 1: Failure getting null value", map.get(key));
	}

	/**
	 * @tests java.util.IdentityHashMapTest#remove(java.lang.Object)
	 */
	public void test_removeLjava_lang_Object() {
		// Regression for HARMONY-37
		IdentityHashMap<String, String> hashMap = new IdentityHashMap<String, String>();
		hashMap.remove("absent");
		assertEquals("Assert 0: Size is incorrect", 0, hashMap.size());

		hashMap.put("key", "value");
        hashMap.remove("key");
        assertEquals("Assert 1: After removing non-null element size is incorrect", 0, hashMap.size());

        hashMap.put(null, null);
        assertEquals("Assert 2: adding literal null failed", 1, hashMap.size());
        hashMap.remove(null);
        assertEquals("Assert 3: After removing null element size is incorrect", 0, hashMap.size());
	}
}
