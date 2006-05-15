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

import java.beans.BeanInfo;
import java.beans.DesignMode;
import java.beans.Visibility;
import java.beans.beancontext.BeanContext;
import java.beans.beancontext.BeanContextChild;
import java.beans.beancontext.BeanContextChildComponentProxy;
import java.beans.beancontext.BeanContextContainerProxy;
import java.beans.beancontext.BeanContextMembershipListener;
import java.beans.beancontext.BeanContextProxy;
import java.beans.beancontext.BeanContextServiceProvider;
import java.beans.beancontext.BeanContextServiceProviderBeanInfo;
import java.beans.beancontext.BeanContextServiceRevokedListener;
import java.beans.beancontext.BeanContextServices;
import java.beans.beancontext.BeanContextServicesListener;
import java.util.Collection;
import java.util.EventListener;

import org.apache.harmony.beans.tests.java.beans.beancontext.mock.MockBeanContext;
import org.apache.harmony.beans.tests.java.beans.beancontext.mock.MockBeanContextChild;
import org.apache.harmony.beans.tests.java.beans.beancontext.mock.MockBeanContextChildComponentProxy;
import org.apache.harmony.beans.tests.java.beans.beancontext.mock.MockBeanContextContainerProxy;
import org.apache.harmony.beans.tests.java.beans.beancontext.mock.MockBeanContextMembershipListener;
import org.apache.harmony.beans.tests.java.beans.beancontext.mock.MockBeanContextProxy;
import org.apache.harmony.beans.tests.java.beans.beancontext.mock.MockBeanContextServiceProvider;
import org.apache.harmony.beans.tests.java.beans.beancontext.mock.MockBeanContextServiceProviderBeanInfo;
import org.apache.harmony.beans.tests.java.beans.beancontext.mock.MockBeanContextServiceRevokedListener;
import org.apache.harmony.beans.tests.java.beans.beancontext.mock.MockBeanContextServices;
import org.apache.harmony.beans.tests.java.beans.beancontext.mock.MockBeanContextServicesListener;
import junit.framework.TestCase;

/**
 * Test all interfaces of package java.beans.beancontext
 */
public class InterfacesTest extends TestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(InterfacesTest.class);
	}

	public void testBeanContext() {
		BeanContext mock = new MockBeanContext();
		assertTrue(mock instanceof BeanContextChild);
		assertTrue(mock instanceof Collection);
		assertTrue(mock instanceof DesignMode);
		assertTrue(mock instanceof Visibility);
	}

	public void testBeanContextChild() {
		BeanContextChild mock = new MockBeanContextChild();
	}

	public void testBeanContextChildComponentProxy() {
		BeanContextChildComponentProxy mock = new MockBeanContextChildComponentProxy();
	}

	public void testBeanContextContainerProxy() {
		BeanContextContainerProxy mock = new MockBeanContextContainerProxy();
	}

	public void testBeanContextMembershipListener() {
		BeanContextMembershipListener mock = new MockBeanContextMembershipListener();
		assertTrue(mock instanceof EventListener);
	}

	public void testBeanContextProxy() {
		BeanContextProxy mock = new MockBeanContextProxy();
	}

	public void testBeanContextServiceProvider() {
		BeanContextServiceProvider mock = new MockBeanContextServiceProvider();
	}

	public void testBeanContextServiceProviderBeanInfo() {
		BeanContextServiceProviderBeanInfo mock = new MockBeanContextServiceProviderBeanInfo();
		assertTrue(mock instanceof BeanInfo);
	}

	public void testBeanContextServiceRevokedListener() {
		BeanContextServiceRevokedListener mock = new MockBeanContextServiceRevokedListener();
		assertTrue(mock instanceof EventListener);
	}

	public void testBeanContextServices() {
		BeanContextServices mock = new MockBeanContextServices();
		assertTrue(mock instanceof BeanContext);
		assertTrue(mock instanceof BeanContextServicesListener);
	}

	public void testBeanContextServicesListener() {
		BeanContextServicesListener mock = new MockBeanContextServicesListener();
		assertTrue(mock instanceof BeanContextServiceRevokedListener);
	}

}
