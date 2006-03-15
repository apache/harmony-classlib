/* Copyright 1998, 2005 The Apache Software Foundation or its licensors, as applicable
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
package tests.api.java.lang;

public class SecurityManagerTest extends junit.framework.TestCase {

	/**
	 * @tests java.lang.SecurityManager#checkMemberAccess(java.lang.Class, int)
	 */
	public void test_checkMemberAccessLjava_lang_ClassI() {
		try {
			getClass().getDeclaredFields();
		} catch (SecurityException e) {
			fail("This should not throw a security exception");
		}

		boolean exception = false;
		try {
			Object.class.getDeclaredFields();
		} catch (SecurityException e) {
			exception = true;
		}
		assertTrue("Should throw SecurityException", exception);
	}

	/**
	 * @tests java.lang.SecurityManager#checkPermission(java.security.Permission)
	 */
	public void test_checkPermissionLjava_security_Permission() {
		boolean exception = false;
		try {
			System.getSecurityManager().checkPermission(
					new RuntimePermission("createClassLoader"));
		} catch (SecurityException e) {
			exception = true;
		}
		assertTrue("Should throw SecurityException", exception);
	}

	protected void setUp() {
		System.setSecurityManager(new SecurityManager());
	}

	protected void tearDown() {
		System.setSecurityManager(null);
	}
}
