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

package tests.api.java.beans.beancontext;

import java.beans.beancontext.BeanContext;
import java.beans.beancontext.BeanContextEvent;
import java.util.EventObject;

import tests.api.java.beans.beancontext.mock.MockBeanContext;

import junit.framework.TestCase;

/**
 * Test BeanContextEvent
 */
public class BeanContextEventTest extends TestCase {

	private static class MockBeanContextEvent extends BeanContextEvent {

		/**
		 * @param bc
		 */
		protected MockBeanContextEvent(BeanContext bc) {
			super(bc);

			assertSame(bc, getSource());
			assertSame(bc, getBeanContext());
			assertNull(this.propagatedFrom);
		}

	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(BeanContextEventTest.class);
	}

	public void testBeanContextEvent_NullParam() {
		try {
			BeanContextEvent event = new MockBeanContextEvent(null);
			fail();
		} catch (IllegalArgumentException e) {
			// expected
		}
	}

	public void testBeanContextEvent() {
		BeanContext ctx = new MockBeanContext();
		BeanContextEvent event = new MockBeanContextEvent(ctx);
		assertSame(ctx, event.getSource());
		assertSame(ctx, event.getBeanContext());
		assertNull(event.getPropagatedFrom());
		assertFalse(event.isPropagated());

		assertTrue(event instanceof EventObject);
	}

	public void testGetBeanContext() {
		BeanContext ctx = new MockBeanContext();
		BeanContextEvent event = new MockBeanContextEvent(ctx);
		assertSame(ctx, event.getBeanContext());
	}

	public void testGetPropagatedFrom() {
		BeanContext ctx = new MockBeanContext();
		BeanContextEvent event = new MockBeanContextEvent(ctx);
		assertNull(event.getPropagatedFrom());

		BeanContext ctx2 = new MockBeanContext();
		event.setPropagatedFrom(ctx2);
		assertSame(ctx2, event.getPropagatedFrom());

		event.setPropagatedFrom(ctx);
		assertSame(ctx, event.getPropagatedFrom());

		event.setPropagatedFrom(null);
		assertNull(event.getPropagatedFrom());
	}

	public void testIsPropagated() {
		BeanContext ctx = new MockBeanContext();
		BeanContextEvent event = new MockBeanContextEvent(ctx);
		assertFalse(event.isPropagated());

		BeanContext ctx2 = new MockBeanContext();
		event.setPropagatedFrom(ctx2);
		assertTrue(event.isPropagated());

		event.setPropagatedFrom(ctx);
		assertTrue(event.isPropagated());

		event.setPropagatedFrom(null);
		assertFalse(event.isPropagated());
	}

	public void testSetPropagatedFrom() {
		BeanContext ctx = new MockBeanContext();
		BeanContextEvent event = new MockBeanContextEvent(ctx);
		assertNull(event.getPropagatedFrom());

		BeanContext ctx2 = new MockBeanContext();
		event.setPropagatedFrom(ctx2);
		assertSame(ctx2, event.getPropagatedFrom());

		event.setPropagatedFrom(ctx);
		assertSame(ctx, event.getPropagatedFrom());

		event.setPropagatedFrom(null);
		assertNull(event.getPropagatedFrom());
	}

}
