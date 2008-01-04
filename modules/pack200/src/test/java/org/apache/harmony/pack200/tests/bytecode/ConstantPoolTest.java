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
package org.apache.harmony.pack200.tests.bytecode;

import junit.framework.TestCase;

import org.apache.harmony.pack200.bytecode.CPMember;
import org.apache.harmony.pack200.bytecode.CPUTF8;
import org.apache.harmony.pack200.bytecode.ClassConstantPool;

public class ConstantPoolTest extends TestCase {
	private ClassConstantPool pool;

	public void setUp() {
		pool = new ClassConstantPool();
	}
	public void testDuplicateUTF8() {
		CPUTF8 u1 = new CPUTF8("thing", ClassConstantPool.DOMAIN_UNDEFINED);
		CPUTF8 u2 = new CPUTF8("thing", ClassConstantPool.DOMAIN_UNDEFINED);
		pool.add(u1);
		pool.add(u2);
		assertEquals(1,pool.size());
	}
	public void testDuplicateField() {
		CPMember cp1 = new CPMember("name:I",0,null);
		pool.add(cp1);
		assertEquals(2,pool.size());
		CPMember cp2 = new CPMember("name:I",0,null);
		pool.add(cp2);
		assertEquals(2,pool.size());
	}
	public void testIndex() {
		pool.add(new CPUTF8("OtherThing", ClassConstantPool.DOMAIN_UNDEFINED));
		CPUTF8 u1 = new CPUTF8("thing", ClassConstantPool.DOMAIN_UNDEFINED);
		pool.add(u1);
		pool.resolve();
		assertTrue(pool.indexOf(u1) > 0);
	}
}
