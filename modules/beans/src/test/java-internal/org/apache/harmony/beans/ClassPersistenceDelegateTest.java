/* Copyright 2005 The Apache Software Foundation or its licensors, as applicable
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

package org.apache.harmony.beans;

import java.beans.Encoder;
import java.beans.Expression;

import junit.framework.TestCase;

/**
 * Test the internal class java.beans.ClassPersistenceDelegate.
 */
public class ClassPersistenceDelegateTest extends TestCase {

	private java_lang_ClassPersistenceDelegate pd = null;

	protected void setUp() throws Exception {
		super.setUp();
		pd = new java_lang_ClassPersistenceDelegate();
	}

	public void testMutates() {
		assertFalse(pd.mutatesTo(Class.class, null));
		assertFalse(pd.mutatesTo(null, null));
		assertFalse(pd.mutatesTo(Class.class, String.class));
		assertTrue(pd.mutatesTo(String.class, String.class));
	}

	public void testInitialize() {
		pd.initialize(null, null, null, null);
	}

	public void testInstantiate_Normal() throws Exception {
		Expression exp = pd.instantiate(Integer.class, new Encoder());
		assertSame(Integer.class, exp.getValue());
		assertTrue(exp.getTarget() instanceof Class);
		assertEquals("forName", exp.getMethodName());
		assertEquals(1, exp.getArguments().length);
		assertEquals("java.lang.Integer", exp.getArguments()[0]);
	}

	public void testInstantiate_Class() throws Exception {
		Expression exp = pd.instantiate(Class.class, new Encoder());
		assertSame(Class.class, exp.getValue());
		assertTrue(exp.getTarget() instanceof Class);
		assertEquals("forName", exp.getMethodName());
		assertEquals(1, exp.getArguments().length);
		assertEquals("java.lang.Class", exp.getArguments()[0]);
	}

	public void testInstantiate_String() throws Exception {
		Expression exp = pd.instantiate(String.class, new Encoder());
		assertSame(String.class, exp.getValue());
		assertSame("", exp.getTarget());
		assertEquals("getClass", exp.getMethodName());
		assertEquals(0, exp.getArguments().length);
	}
}
