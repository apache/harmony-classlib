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

package tests.api.java.security;

import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.Policy;

public class PolicyTest extends junit.framework.TestCase {

	static class TestPolicy extends Policy {
		public PermissionCollection getPermissions(CodeSource cs) {
			return null;
		}

		public void refresh() {
		}
	}

	/**
	 * @tests java.security.Policy#Policy()
	 */
	public void test_Constructor() {
		try {
			new TestPolicy();
		} catch (Exception e) {
			fail("Caught exception: " + e + " during construction");
		}
	}

	/**
	 * @tests java.security.Policy#getPolicy()
	 */
	public void test_getPolicy() {
		try {
			assertNotNull("Got a null system security policy", Policy
					.getPolicy());
		} catch (Exception e) {
			fail("Caught a " + e.toString() + " exception!");
		}

		try {
			// set a security manager and then try getting the policy
			System.setSecurityManager(new SecurityManager());

			assertNotNull("Got a null system security policy", Policy
					.getPolicy());
			fail("We shouldn't have been able to get the policy "
					+ "with a SecurityManager in place!");
		} catch (Exception e) {
			assertTrue("Policy.getPolicy() should have thrown "
					+ "a: java.security.AccessControlException but "
					+ "instead threw a: " + e.toString(),
					e instanceof java.security.AccessControlException);
		} finally {
			System.setSecurityManager(null);
		}
	}

	/**
	 * @tests java.security.Policy#setPolicy(java.security.Policy)
	 */
	public void test_setPolicyLjava_security_Policy() {
		// get the policy
		Policy sysPolicy = Policy.getPolicy();
		try {
			Policy newPolicy = new TestPolicy();
			// set the policy
			Policy.setPolicy(newPolicy);
			// make sure it was set
			assertEquals("Policy could not be set!", newPolicy, Policy
					.getPolicy());
		} catch (Exception e) {
			fail("Unexpected exception : " + e);
		} finally {
			// restore the policy
			Policy.setPolicy(sysPolicy);
		}
	}

	protected void setUp() {
	}

	protected void tearDown() {
	}
}