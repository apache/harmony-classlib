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

import java.beans.beancontext.BeanContextServiceAvailableEvent;
import java.beans.beancontext.BeanContextServiceRevokedEvent;
import java.beans.beancontext.BeanContextServicesListener;
import java.io.Serializable;

/**
 * Mock of BeanContextServicesListener
 */
public class MockBeanContextServicesListenerS implements
		BeanContextServicesListener, Serializable {

	public String id;

	public MockBeanContextServicesListenerS(String id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.beancontext.BeanContextServicesListener#serviceAvailable(java.beans.beancontext.BeanContextServiceAvailableEvent)
	 */
	public void serviceAvailable(BeanContextServiceAvailableEvent bcsae) {
		// nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.beancontext.BeanContextServiceRevokedListener#serviceRevoked(java.beans.beancontext.BeanContextServiceRevokedEvent)
	 */
	public void serviceRevoked(BeanContextServiceRevokedEvent bcsre) {
		// nothing
	}

}
