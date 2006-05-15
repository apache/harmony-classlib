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

package org.apache.harmony.beans.tests.java.beans.beancontext;

import java.beans.beancontext.BeanContext;
import java.beans.beancontext.BeanContextEvent;
import java.beans.beancontext.BeanContextMembershipEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import junit.framework.TestCase;
import org.apache.harmony.beans.tests.java.beans.beancontext.mock.MockBeanContext;
import org.apache.harmony.beans.tests.java.beans.beancontext.mock.MockBeanContextDelegateS;
import tests.util.SerializationTester;

/**
 * Test BeanContextMembershipEvent
 */
public class BeanContextMembershipEventTest extends TestCase {

	private static class MockBeanContextMembershipEvent extends
			BeanContextMembershipEvent {

		/**
		 * @param bc
		 * @param changes
		 */
		public MockBeanContextMembershipEvent(BeanContext bc, Object[] changes) {
			super(bc, changes);
		}

		/**
		 * @param bc
		 * @param changes
		 */
		public MockBeanContextMembershipEvent(BeanContext bc, Collection changes) {
			super(bc, changes);
		}

		public Collection collection() {
			return this.children;
		}
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(BeanContextMembershipEventTest.class);
	}

	public void testBeanContextMembershipEvent_NullParam() {
		BeanContext ctx = new MockBeanContext();
		Collection c = new ArrayList();
		c.add("1");
		c.add("2");
		c.add("3");
		String array[] = new String[] { "1", "2", "3" };

		try {
			MockBeanContextMembershipEvent event = new MockBeanContextMembershipEvent(
					null, c);
			fail();
		} catch (IllegalArgumentException e) {
			// expected
		}

		try {
			MockBeanContextMembershipEvent event = new MockBeanContextMembershipEvent(
					null, array);
			fail();
		} catch (IllegalArgumentException e) {
			// expected
		}

		try {
			MockBeanContextMembershipEvent event = new MockBeanContextMembershipEvent(
					ctx, (Collection) null);
			fail();
		} catch (NullPointerException e) {
			// expected
		}

		try {
			MockBeanContextMembershipEvent event = new MockBeanContextMembershipEvent(
					ctx, (Object[]) null);
			fail();
		} catch (NullPointerException e) {
			// expected
		}
	}

	/*
	 * Class under test for void
	 * BeanContextMembershipEvent(java.beans.beancontext.BeanContext,
	 * java.util.Collection)
	 */
	public void testBeanContextMembershipEventBeanContextCollection() {
		BeanContext ctx = new MockBeanContext();
		Collection c = new ArrayList();
		c.add("1");
		c.add("2");
		c.add("3");
		MockBeanContextMembershipEvent event = new MockBeanContextMembershipEvent(
				ctx, c);
		assertSame(c, event.collection());

		assertTrue(event instanceof BeanContextEvent);
	}

	/*
	 * Class under test for void
	 * BeanContextMembershipEvent(java.beans.beancontext.BeanContext,
	 * java.lang.Object[])
	 */
	public void testBeanContextMembershipEventBeanContextObjectArray() {
		BeanContext ctx = new MockBeanContext();
		String array[] = new String[] { "1", "2", "3" };
		MockBeanContextMembershipEvent event = new MockBeanContextMembershipEvent(
				ctx, array);

		assertTrue(event instanceof BeanContextEvent);
	}

	public void testSize_Collection() {
		BeanContext ctx = new MockBeanContext();
		Collection c = new ArrayList();
		c.add("1");
		c.add("2");
		c.add("3");
		MockBeanContextMembershipEvent event = new MockBeanContextMembershipEvent(
				ctx, c);
		assertEquals(3, event.size());
	}

	public void testSize_Array() {
		BeanContext ctx = new MockBeanContext();
		String array[] = new String[] { "1", "2", "3" };
		MockBeanContextMembershipEvent event = new MockBeanContextMembershipEvent(
				ctx, array);
		assertEquals(3, event.size());
	}

	public void testContains_Collection() {
		BeanContext ctx = new MockBeanContext();
		Collection c = new ArrayList();
		c.add("1");
		c.add("2");
		c.add("3");
		MockBeanContextMembershipEvent event = new MockBeanContextMembershipEvent(
				ctx, c);
		assertTrue(event.contains("1"));
		assertTrue(event.contains("2"));
		assertTrue(event.contains("3"));
		assertFalse(event.contains(null));
		assertFalse(event.contains("4"));
	}

	public void testContains_Array() {
		BeanContext ctx = new MockBeanContext();
		String array[] = new String[] { "1", "2", "3" };
		MockBeanContextMembershipEvent event = new MockBeanContextMembershipEvent(
				ctx, array);
		assertTrue(event.contains("1"));
		assertTrue(event.contains("2"));
		assertTrue(event.contains("3"));
		assertFalse(event.contains(null));
		assertFalse(event.contains("4"));
	}

	public void testToArray_Collection() {
		BeanContext ctx = new MockBeanContext();
		Collection c = new ArrayList();
		c.add("1");
		c.add("2");
		c.add("3");
		MockBeanContextMembershipEvent event = new MockBeanContextMembershipEvent(
				ctx, c);
		Object resultArray[] = event.toArray();
		assertEquals(3, resultArray.length);
		assertEquals("1", resultArray[0]);
		assertEquals("2", resultArray[1]);
		assertEquals("3", resultArray[2]);
	}

	public void testToArray_Array() {
		BeanContext ctx = new MockBeanContext();
		String array[] = new String[] { "1", "2", "3" };
		MockBeanContextMembershipEvent event = new MockBeanContextMembershipEvent(
				ctx, array);
		Object resultArray[] = event.toArray();
		assertEquals(3, resultArray.length);
		assertEquals("1", resultArray[0]);
		assertEquals("2", resultArray[1]);
		assertEquals("3", resultArray[2]);
	}

	public void testIterator_Collection() {
		BeanContext ctx = new MockBeanContext();
		Collection c = new ArrayList();
		c.add("1");
		c.add("2");
		c.add("3");
		MockBeanContextMembershipEvent event = new MockBeanContextMembershipEvent(
				ctx, c);
		Iterator it = event.iterator();
		assertEquals("1", it.next());
		assertEquals("2", it.next());
		assertEquals("3", it.next());
		assertFalse(it.hasNext());
	}

	public void testIterator_Array() {
		BeanContext ctx = new MockBeanContext();
		String array[] = new String[] { "1", "2", "3" };
		MockBeanContextMembershipEvent event = new MockBeanContextMembershipEvent(
				ctx, array);
		Iterator it = event.iterator();
		assertEquals("1", it.next());
		assertEquals("2", it.next());
		assertEquals("3", it.next());
		assertFalse(it.hasNext());
	}

	public void testSerialization() throws IOException, ClassNotFoundException {
		ArrayList things = new ArrayList();
		things.add("1");
		things.add("2");
		things.add("3");
		BeanContextMembershipEvent event = new BeanContextMembershipEvent(
				new MockBeanContext(), things);
		event.setPropagatedFrom(new MockBeanContextDelegateS("from ID"));

		assertEqualsSerially(event,
				(BeanContextMembershipEvent) SerializationTester
						.getDeserilizedObject(event));
	}

	public void testSerialization_Compatibility() throws Exception {
		ArrayList things = new ArrayList();
		things.add("1");
		things.add("2");
		things.add("3");
		BeanContextMembershipEvent event = new BeanContextMembershipEvent(
				new MockBeanContext(), things);
		event.setPropagatedFrom(new MockBeanContextDelegateS("from ID"));

		assertEqualsSerially(
				event,
				(BeanContextMembershipEvent) SerializationTester
						.readObject(event,
								"serialization/java/beans/beancontext/BeanContextMembershipEvent.ser"));
	}

	private void assertEqualsSerially(BeanContextMembershipEvent orig,
			BeanContextMembershipEvent ser) {
		assertNull(ser.getSource());

		// check propagatedFrom
		if (orig.getPropagatedFrom() instanceof Serializable) {
			BeanContext origFrom = orig.getPropagatedFrom();
			BeanContext serFrom = ser.getPropagatedFrom();
			assertEquals(origFrom.getClass(), serFrom.getClass());
			if (origFrom instanceof MockBeanContextDelegateS) {
				assertEquals(((MockBeanContextDelegateS) origFrom).id,
						((MockBeanContextDelegateS) serFrom).id);
			}
		}

		// check children
		Collection origChildren = (Collection) Utils.getField(orig, "children");
		Collection serChildren = (Collection) Utils.getField(ser, "children");
		assertEquals(origChildren, serChildren);
	}

}
