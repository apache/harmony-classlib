/* Copyright 2004 The Apache Software Foundation or its licensors, as applicable
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
package org.apache.harmony.jndi.tests.javax.naming;

import javax.naming.NameClassPair;

import junit.framework.TestCase;

public class NameClassPairTest extends TestCase {

	/**
	 * Constructor for TestNameClassPair.
	 * 
	 * @param arg0
	 */
	public NameClassPairTest(String arg0) {
		super(arg0);
	}

	/*
	 * @see TestCase#setUp()
	 */
	@Override
    protected void setUp() throws Exception {
		super.setUp();
	}

	/*
	 * @see TestCase#tearDown()
	 */
	@Override
    protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testConstructor_Simple() {
		NameClassPair p;

		p = new NameClassPair("name1", "class1");
		assertEquals("name1", p.getName());
		assertEquals("class1", p.getClassName());

		p = new NameClassPair("name2", "class2", false);
		assertEquals("name2", p.getName());
		assertEquals("class2", p.getClassName());
		assertEquals(false, p.isRelative());
	}

	public void testConstructor_NullValue() {
		NameClassPair p;

		p = new NameClassPair("name1", null);
		assertEquals("name1", p.getName());
		assertTrue(null == p.getClassName());
	}

	public void testConstructor_DefaultRelativeValue() {
		NameClassPair p;

		p = new NameClassPair("name1", null);
		assertEquals(true, p.isRelative());
	}

	public void testToString() {
		NameClassPair p;

		p = new NameClassPair("name1", null, false);
		assertTrue(p.toString().startsWith("(not relative"));
	}

	public void testGetSetName() {
		NameClassPair p;

		p = new NameClassPair("name1", null, true);
		p.setName("aname");
		assertEquals("aname", p.getName());
	}

	public void testGetSetClassName() {
		NameClassPair p;

		p = new NameClassPair("name1", null, true);
		p.setClassName("aclassname");
		assertEquals("aclassname", p.getClassName());
	}

	public void testGetSetRelative() {
		NameClassPair p;

		p = new NameClassPair("name1", null, true);
		p.setRelative(false);
		assertEquals(false, p.isRelative());
	}
}
