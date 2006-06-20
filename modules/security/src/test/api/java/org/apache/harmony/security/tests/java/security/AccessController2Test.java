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

package org.apache.harmony.security.tests.java.security;

import java.security.AccessController;
import java.security.AllPermission;
import java.security.PrivilegedAction;

public class AccessController2Test extends junit.framework.TestCase {

	/**
	 * @tests java.security.AccessController#doPrivileged(java.security.PrivilegedAction)
	 */
	public void test_doPrivilegedLjava_security_PrivilegedAction() {
		// Test for method java.lang.Object
		// java.security.AccessController.doPrivileged(java.security.PrivilegedAction)

		// Pass fail flag...
		Boolean pass;

		// First test 1 argument version (TBD).

		// Then test 2 argument version. 
		pass = (Boolean) AccessController.doPrivileged(new PrivilegedAction() {
			public Object run() {
				try {
					AccessController.checkPermission(new AllPermission());
					return new Boolean(false);
				} catch (SecurityException ex) {
					return new Boolean(true);
				}
			}
		}, null);
		assertTrue("Got AllPermission by passing in a null PD", pass
				.booleanValue());

	}
}