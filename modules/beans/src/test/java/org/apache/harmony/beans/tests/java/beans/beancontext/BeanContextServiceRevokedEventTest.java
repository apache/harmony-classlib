/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
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
import java.beans.beancontext.BeanContextServiceRevokedEvent;
import java.beans.beancontext.BeanContextServices;
import java.beans.beancontext.BeanContextServicesSupport;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;

import junit.framework.TestCase;

import org.apache.harmony.beans.tests.support.beancontext.mock.MockBeanContextDelegateS;
import org.apache.harmony.beans.tests.support.beancontext.mock.MockBeanContextServices;
import org.apache.harmony.testframework.serialization.SerializationTest;
import org.apache.harmony.testframework.serialization.SerializationTest.SerializableAssert;

import tests.util.SerializationTester;

/**
 * Test BeanContextServiceRevokedEvent
 */
@SuppressWarnings("unchecked")
public class BeanContextServiceRevokedEventTest extends TestCase {

    private static class MockBeanContextServiceRevokedEvent extends
            BeanContextServiceRevokedEvent {

        static final long serialVersionUID = -705194281645674622L;

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
            new MockBeanContextServiceRevokedEvent(null, BeanContext.class,
                    true);
            fail("IAE expected");
        } catch (IllegalArgumentException e) {
            // expected
        }

        BeanContextServiceRevokedEvent event = new MockBeanContextServiceRevokedEvent(
                services, null, true);
        assertNull(event.getServiceClass());
        assertSame(services, event.getSource());
        assertSame(services, event.getSourceAsBeanContextServices());
        assertTrue(event.isCurrentServiceInvalidNow());
        try {
            event.isServiceClass(Integer.class);
            fail("NPE expected");
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
        assertTrue(event.isCurrentServiceInvalidNow());
    }

    public void testGetSourceAsBeanContextServices() {
        BeanContextServices services = new MockBeanContextServices();
        BeanContextServiceRevokedEvent event = new MockBeanContextServiceRevokedEvent(
                services, BeanContext.class, true);
        assertSame(services, event.getSource());
        assertSame(services, event.getSourceAsBeanContextServices());

        // Regression for HARMONY-1153
        BeanContextServicesSupport sup = new BeanContextServicesSupport(
                new MockBeanContextServices(), new Locale("ru", "RU"), false,
                false);
        event = new BeanContextServiceRevokedEvent(sup,
                MockBeanContextServices.class, false);
        assertNotNull(event.getSourceAsBeanContextServices());
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

        // Regression for HARMONY-1516
        assertFalse(event.isServiceClass(null));
    }

    public void testIsCurrentServiceInvalidNow() {
        BeanContextServices services = new MockBeanContextServices();
        BeanContextServiceRevokedEvent event = new MockBeanContextServiceRevokedEvent(
                services, BeanContext.class, true);
        assertTrue(event.isCurrentServiceInvalidNow());
        event = new MockBeanContextServiceRevokedEvent(services,
                BeanContext.class, false);
        assertFalse(event.isCurrentServiceInvalidNow());
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
         SerializationTest.verifyGolden(this, event, new SerializableAssert(){
             public void assertDeserialized(Serializable orig, Serializable ser) {
                 assertEqualsSerially((BeanContextServiceRevokedEvent) orig,
                         (BeanContextServiceRevokedEvent) ser);
             }
         });
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
