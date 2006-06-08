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

package org.apache.harmony.beans.tests.java.beans.beancontext.mock;

import java.beans.beancontext.BeanContextChild;
import java.beans.beancontext.BeanContextProxy;
import java.io.Serializable;

/**
 * Mock of BeanContextProxy
 */
public class MockBeanContextProxyS implements BeanContextProxy, Serializable {

	private String id;

	private BeanContextChild bcc;

	public MockBeanContextProxyS(String id, BeanContextChild bcc) {
		this.id = id;
		this.bcc = bcc;
	}

	public boolean equals(Object o) {
		if (o instanceof MockBeanContextProxyS) {
			MockBeanContextProxyS other = (MockBeanContextProxyS) o;
			return id.equals(other.id) && bcc.equals(other.bcc);
		}
		return false;
	}

	public int hashCode() {
		return id.hashCode() + bcc.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.beancontext.BeanContextProxy#getBeanContextProxy()
	 */
	public BeanContextChild getBeanContextProxy() {
		return bcc;
	}

}
