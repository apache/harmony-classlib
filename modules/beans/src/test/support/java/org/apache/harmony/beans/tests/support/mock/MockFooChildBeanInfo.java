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

package org.apache.harmony.beans.tests.support.mock;

import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;
import java.lang.reflect.Method;


/**
 * test Introspector
 */
public class MockFooChildBeanInfo extends SimpleBeanInfo {

	Class clazz = MockFooChild.class;

	String suffix = ".MockFooChildBeanInfo";

	public PropertyDescriptor[] getPropertyDescriptors() {
		PropertyDescriptor[] pds = new PropertyDescriptor[1];
		try {
			PropertyDescriptor pd = new PropertyDescriptor("childName", clazz);
			pd.setName(pd.getName() + suffix);
			pds[0] = pd;
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return pds;
	}

	public MethodDescriptor[] getMethodDescriptors() {
		MethodDescriptor[] mds = new MethodDescriptor[2];
		try {
			Method getMethod = clazz.getMethod("getChildName", (Class[])null);
			Method setMethod = clazz.getMethod("setChildName",
					new Class[] { String.class });
			mds[0] = new MethodDescriptor(getMethod);
			mds[0].setName(mds[0].getName() + suffix);

			mds[1] = new MethodDescriptor(setMethod);
			mds[1].setName(mds[1].getName() + suffix);

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return mds;
	}

	public EventSetDescriptor[] getEventSetDescriptors() {
		EventSetDescriptor[] esds = new EventSetDescriptor[1];
		try {
			EventSetDescriptor esd = new EventSetDescriptor(clazz,
					"mockPropertyChange", MockPropertyChangeListener.class,
					"mockPropertyChange");
			esd.setName(esd.getName() + suffix);
			esds[0] = esd;
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return esds;
	}
}
