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

package org.apache.harmony.beans.tests.java.beans;

import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

import org.apache.harmony.beans.tests.java.beans.mock.MockFooLabel;

public class MockFooLabelBeanInfo extends SimpleBeanInfo {
	Class clazz = MockFooLabel.class;

	String suffix = ".MockFooLabelBeanInfo";

	public BeanDescriptor getBeanDescriptor() {
		BeanDescriptor beanDesc = new BeanDescriptor(clazz);
		beanDesc.setName(beanDesc.getName() + suffix);
		return beanDesc;
	}

	public PropertyDescriptor[] getPropertyDescriptors() {
		PropertyDescriptor[] pds = new PropertyDescriptor[1];
		try {
			PropertyDescriptor pd = new PropertyDescriptor("text", clazz);
			pd.setName(pd.getName() + suffix);
			pds[0] = pd;
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return pds;
	}

}
