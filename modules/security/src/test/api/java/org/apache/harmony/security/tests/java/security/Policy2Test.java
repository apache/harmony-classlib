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

package org.apache.harmony.security.tests.java.security;

import java.security.AccessControlException;
import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.Policy;

public class Policy2Test extends junit.framework.TestCase {

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
		new TestPolicy();
	}

	/**
	 * @tests java.security.Policy#getPolicy()
	 */
	public void test_getPolicy() {
		assertNotNull("Got a null system security policy", Policy.getPolicy());

        SecurityManager sm = new SecurityManager() {
            public void checkPermission(Permission p) {
                if ("setSecurityManager".equals(p.getName())) {
                    return;
                }
                if ("getPolicy".equals(p.getName())) {
                    throw new AccessControlException("getPolicy");
                }
                super.checkPermission(p);
            }
        };
        
        // set a security manager and then try getting the policy
        System.setSecurityManager(sm);

        try {
			Policy.getPolicy();
			fail("We shouldn't have been able to get the policy "
					+ "with a SecurityManager in place!");
		} catch (AccessControlException e) {
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
		} finally {
			// restore the policy
			Policy.setPolicy(sysPolicy);
		}
	}
}