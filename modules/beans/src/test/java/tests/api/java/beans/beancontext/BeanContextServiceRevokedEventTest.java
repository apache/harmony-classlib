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
import java.beans.beancontext.BeanContextServiceRevokedEvent;
import java.beans.beancontext.BeanContextServices;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import junit.framework.TestCase;
import tests.api.java.beans.beancontext.mock.MockBeanContextDelegateS;
import tests.api.java.beans.beancontext.mock.MockBeanContextServices;
import tests.util.SerializationTester;

/**
 * Test BeanContextServiceRevokedEvent
 */
public class BeanContextServiceRevokedEventTest extends TestCase {

	private static class MockBeanContextServiceRevokedEvent extends
			BeanContextServiceRevokedEvent {

		/**
		 * @param bcs
		 * @param sc
		 * @param invalidate
		 */
		public MockBeanContextServiceRevokedEvent(BeanContextServices bcs,
				Class sc, boolean invalidate) {
			super(bcs, sc, invalidate);
			assertSame(sc, this.serviceClass);
		}

	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(BeanContextServiceRevokedEventTest.class);
	}

	public void testBeanContextServiceRevokedEvent_NullParam() {
		BeanContextServices services = new MockBeanContextServices();

		try {
			BeanContextServiceRevokedEvent event = new MockBeanContextServiceRevokedEvent(
					null, BeanContext.class, true);
			fail();
		} catch (IllegalArgumentException e) {
			// expected
		}

		try {
			BeanContextServiceRevokedEvent event = new MockBeanContextServiceRevokedEvent(
					services, null, true);
			assertNull(event.getServiceClass());
			assertSame(services, event.getSource());
			assertSame(services, event.getSourceAsBeanContextServices());
			assertEquals(true, event.isCurrentServiceInvalidNow());
			event.isServiceClass(Integer.class);
			fail();
		} catch (NullPointerException e) {
			// expected
		}
	}

	public void testBeanContextServiceRevokedEvent() {
		BeanContextServices services = new MockBeanContextServices();
		BeanContextServiceRevokedEvent event = new MockBeanContextServiceRevokedEvent(
				services, BeanContext.class, true);
		assertSame(BeanContext.class, event.getServiceClass());
		assertSame(services, event.getSource());
		assertSame(services, event.getSourceAsBeanContextServices());
		assertEquals(true, event.isCurrentServiceInvalidNow());
		assertTrue(event instanceof BeanContextEvent);
	}

	public void testGetSourceAsBeanContextServices() {
		BeanContextServices services = new MockBeanContextServices();
		BeanContextServiceRevokedEvent event = new MockBeanContextServiceRevokedEvent(
				services, BeanContext.class, true);
		assertSame(services, event.getSource());
		assertSame(services, event.getSourceAsBeanContextServices());
	}

	public void testGetServiceClass() {
		BeanContextServices services = new MockBeanContextServices();
		BeanContextServiceRevokedEvent event = new MockBeanContextServiceRevokedEvent(
				services, BeanContext.class, true);
		assertSame(BeanContext.class, event.getServiceClass());
	}

	public void testIsServiceClass() {
		BeanContextServices services = new MockBeanContextServices();
		BeanContextServiceRevokedEvent event = new MockBeanContextServiceRevokedEvent(
				services, BeanContext.class, true);
		assertTrue(event.isServiceClass(BeanContext.class));
		assertFalse(event.isServiceClass(Integer.class));
	}

	public void testIsCurrentServiceInvalidNow() {
		BeanContextServices services = new MockBeanContextServices();
		BeanContextServiceRevokedEvent event = new MockBeanContextServiceRevokedEvent(
				services, BeanContext.class, true);
		assertEquals(true, event.isCurrentServiceInvalidNow());
		event = new MockBeanContextServiceRevokedEvent(services,
				BeanContext.class, false);
		assertEquals(false, event.isCurrentServiceInvalidNow());
	}

	public void testSerialization() throws IOException, ClassNotFoundException {
		BeanContextServiceRevokedEvent event = new BeanContextServiceRevokedEvent(
				new MockBeanContextServices(), ArrayList.class, true);
		event.setPropagatedFrom(new MockBeanContextDelegateS("from ID"));

		assertEqualsSerially(event,
				(BeanContextServiceRevokedEvent) SerializationTester
						.getDeserilizedObject(event));
	}

	public void testSerialization_Compatibility() throws Exception {
		BeanContextServiceRevokedEvent event = new BeanContextServiceRevokedEvent(
				new MockBeanContextServices(), ArrayList.class, true);
		event.setPropagatedFrom(new MockBeanContextDelegateS("from ID"));

		assertEqualsSerially(
				event,
				(BeanContextServiceRevokedEvent) SerializationTester
						.readObject(event,
								"tests/api/java/beans/beancontext/BeanContextServiceRevokedEvent.ser"));
	}

	private void assertEqualsSerially(BeanContextServiceRevokedEvent orig,
			BeanContextServiceRevokedEvent ser) {
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

		// check serviceClass
		assertEquals(orig.getServiceClass(), ser.getServiceClass());

		// check invalidateRefs
		assertEquals(orig.isCurrentServiceInvalidNow(), ser
				.isCurrentServiceInvalidNow());
	}
}
