/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
* @author Alexey V. Varlamov
* @version $Revision$
*/

package org.apache.harmony.security.tests.java.security;
import java.security.*;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;

import junit.framework.TestCase;

import org.apache.harmony.security.tests.support.SecurityChecker;


/**
 * Tests for <code>Policy</code>
 * 
 */

public class PolicyTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(PolicyTest.class);
    }

    /**
     * Tests that setPolicy() is properly secured via SecurityManager.
     */
    public void testSetPolicy() {
        SecurityManager old = System.getSecurityManager();
        Policy oldPolicy = Policy.getPolicy();
        try {
            SecurityChecker checker = new SecurityChecker(
                new SecurityPermission("setPolicy"), true);
            System.setSecurityManager(checker);
            Policy custom = new TestProvider();
            Policy.setPolicy(custom);
            assertTrue(checker.checkAsserted);
            assertSame(custom, Policy.getPolicy());

            checker.reset();
            checker.enableAccess = false;
            try {
                Policy.setPolicy(new TestProvider());
                fail("SecurityException is intercepted");
            } catch (SecurityException ok) {
            }
        } finally {
            System.setSecurityManager(old);
            Policy.setPolicy(oldPolicy);
        }
    }

    /**
     * Tests that getPolicy() is properly secured via SecurityManager.
     */
    public void testGetPolicy_CheckPermission() {
        SecurityManager old = System.getSecurityManager();
        Policy oldPolicy = Policy.getPolicy();
        try {
            Policy.setPolicy(new TestProvider());
            SecurityChecker checker = new SecurityChecker(
                new SecurityPermission("getPolicy"), true);
            System.setSecurityManager(checker);
            Policy.getPolicy();
            assertTrue(checker.checkAsserted);

            checker.reset();
            checker.enableAccess = false;
            try {
                Policy.getPolicy();
                fail("SecurityException is intercepted");
            } catch (SecurityException ok) {
            }
        } finally {
            System.setSecurityManager(old);
            Policy.setPolicy(oldPolicy);
        }
    }

    public static class TestProvider extends Policy {

        PermissionCollection pc;

        public PermissionCollection getPermissions(CodeSource cs) {
            return pc;
        }

        public void refresh() {
        }
    }
    
    /** 
     * Tests that getPermissions() does proper permission evaluation.
     */
    public void testGetPermissions() {
        SecurityPermission sp = new SecurityPermission("abc");
        SecurityPermission sp2 = new SecurityPermission("fbdf");
        PermissionCollection spc = sp.newPermissionCollection();
        spc.add(sp2);
        ProtectionDomain pd = new ProtectionDomain(null, null);
        ProtectionDomain pd2 = new ProtectionDomain(null, spc);
        TestProvider policy = new TestProvider();
        policy.pc = sp.newPermissionCollection();
                 
        //case1: empty policy, no static permissions in PD
        PermissionCollection pc4pd = policy.getPermissions(pd);
        assertNotNull(pc4pd);
        Enumeration en = pc4pd.elements();
        assertFalse(en.hasMoreElements());
        
        //case2: empty policy, some static permissions in PD
        pc4pd = policy.getPermissions(pd2);
        assertNotNull(pc4pd);
        //no check for static permissions
        
        //case3: non-empty policy, no static permissions in PD
        policy.pc.add(sp);
        pc4pd = policy.getPermissions(pd);
        assertNotNull(pc4pd);
        Collection c = new HashSet();
        for (en = pc4pd.elements();en.hasMoreElements(); c.add(en.nextElement())) {
        }

        assertTrue(c.contains(sp));
        
        //case4: non-empty policy, some static permissions in PD
        pc4pd = policy.getPermissions(pd2);
        assertNotNull(pc4pd);
        c = new HashSet();
        for (en = pc4pd.elements();en.hasMoreElements(); c.add(en.nextElement())) {
        }

        assertTrue(c.contains(sp));
        //no check for static permissions
    }
}
