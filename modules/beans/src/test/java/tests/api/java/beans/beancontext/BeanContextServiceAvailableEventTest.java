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
import java.beans.beancontext.BeanContextServiceAvailableEvent;
import java.beans.beancontext.BeanContextServices;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import junit.framework.TestCase;
import tests.api.java.beans.beancontext.mock.MockBeanContextDelegateS;
import tests.api.java.beans.beancontext.mock.MockBeanContextServices;
import tests.util.SerializationTester;

/**
 * Test BeanContextServiceAvailableEvent
 */
public class BeanContextServiceAvailableEventTest extends TestCase {

	private static class MockBeanContextServiceAvailableEvent extends
			BeanContextServiceAvailableEvent {

		/**
		 * @param bcs
		 * @param sc
		 */
		public MockBeanContextServiceAvailableEvent(BeanContextServices bcs,
				Class sc) {
			super(bcs, sc);
			assertSame(sc, this.serviceClass);
		}
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(BeanContextServiceAvailableEventTest.class);
	}

	public void testBeanContextServiceAvailableEvent_NullParam() {
		BeanContextServices services = new MockBeanContextServices();

		try {
			BeanContextServiceAvailableEvent event = new MockBeanContextServiceAvailableEvent(
					null, BeanContext.class);
			fail();
		} catch (IllegalArgumentException e) {
			// expected
		}

		BeanContextServiceAvailableEvent event = new MockBeanContextServiceAvailableEvent(
				services, null);
		assertNull(event.getServiceClass());
		assertSame(services, event.getSource());
		assertSame(services, event.getSourceAsBeanContextServices());
	}

	public void testBeanContextServiceAvailableEvent() {
		BeanContextServices services = new MockBeanContextServices();
		BeanContextServiceAvailableEvent event = new MockBeanContextServiceAvailableEvent(
				services, BeanContext.class);
		assertSame(BeanContext.class, event.getServiceClass());
		assertSame(services, event.getSource());
		assertSame(services, event.getSourceAsBeanContextServices());
		assertTrue(event instanceof BeanContextEvent);
	}

	public void testGetSourceAsBeanContextServices() {
		BeanContextServices services = new MockBeanContextServices();
		BeanContextServiceAvailableEvent event = new MockBeanContextServiceAvailableEvent(
				services, BeanContext.class);
		assertSame(services, event.getSource());
		assertSame(services, event.getSourceAsBeanContextServices());
	}

	public void testGetServiceClass() {
		BeanContextServices services = new MockBeanContextServices();
		BeanContextServiceAvailableEvent event = new MockBeanContextServiceAvailableEvent(
				services, BeanContext.class);
		assertSame(BeanContext.class, event.getServiceClass());
	}

	public void testGetCurrentServiceSelectors() {
		BeanContextServices services = new MockBeanContextServices();
		BeanContextServiceAvailableEvent event = new MockBeanContextServiceAvailableEvent(
				services, BeanContext.class);

		Iterator expectedIt = services
				.getCurrentServiceSelectors(BeanContext.class);
		Iterator it = event.getCurrentServiceSelectors();
		while (expectedIt.hasNext()) {
			assertSame(expectedIt.next(), it.next());
		}
		assertFalse(expectedIt.hasNext());
		assertFalse(it.hasNext());
	}

	public void testSerialization() throws IOException, ClassNotFoundException {
		BeanContextServiceAvailableEvent event = new BeanContextServiceAvailableEvent(
				new MockBeanContextServices(), ArrayList.class);
		event.setPropagatedFrom(new MockBeanContextDelegateS("from ID"));

		assertEqualsSerially(event,
				(BeanContextServiceAvailableEvent) SerializationTester
						.getDeserilizedObject(event));
	}

	public void testSerialization_Compatibility() throws Exception {
		BeanContextServiceAvailableEvent event = new BeanContextServiceAvailableEvent(
				new MockBeanContextServices(), ArrayList.class);
		event.setPropagatedFrom(new MockBeanContextDelegateS("from ID"));

		assertEqualsSerially(
				event,
				(BeanContextServiceAvailableEvent) SerializationTester
						.readObject(event,
								"tests/api/java/beans/beancontext/BeanContextServiceAvailableEvent.ser"));
	}

	private void assertEqualsSerially(BeanContextServiceAvailableEvent orig,
			BeanContextServiceAvailableEvent ser) {
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
	}
}
