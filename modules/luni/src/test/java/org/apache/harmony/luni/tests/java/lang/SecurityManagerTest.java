/* Licensed to the Apache Software Foundation (ASF) under one or more
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

package org.apache.harmony.luni.tests.java.lang;

import java.io.File;

import tests.support.Support_Exec;

import junit.framework.TestCase;

public class SecurityManagerTest extends TestCase {
    
    /**
     * @tests java.lang.SecurityManager#checkMemberAccess(java.lang.Class, int)
     */
    public void test_checkMemberAccessLjava_lang_ClassI() {
        MutableSecurityManager sm = new MutableSecurityManager();
        sm.addPermission(MutableSecurityManager.SET_SECURITY_MANAGER);
        sm.denyPermission("accessDeclaredMembers");
        System.setSecurityManager(sm);
        try {
            try {
                getClass().getDeclaredFields();
            } catch (SecurityException e) {
                fail("This should not throw a security exception");
            }

            try {
                Object.class.getDeclaredFields();
                fail("This should throw a SecurityException.");
            } catch (SecurityException e) {
            }

        } finally {
            System.setSecurityManager(null);
        }
    }

    /**
     * @tests java.lang.SecurityManager#checkPermission(java.security.Permission)
     */
    public void test_checkPermissionLjava_security_Permission()
            throws Exception {

        // tmp user home to avoid presence of ${user.home}/.java.policy
        String tmpUserHome = System.getProperty("java.io.tmpdir")
                + File.separatorChar + "tmpUserHomeForSecurityManagerTest";
        File dir = new File(tmpUserHome);
        if (!dir.exists()) {
            dir.mkdirs();
            dir.deleteOnExit();
        }
        String javaPolycy = tmpUserHome + File.separatorChar + ".java.policy";
        assertFalse("There should be no java policy file: " + javaPolycy,
                new File(javaPolycy).exists());

        String[] arg = new String[] { "-Duser.home=" + tmpUserHome,
                checkPermissionLjava_security_PermissionTesting.class.getName() };

        Support_Exec.execJava(arg, null, true);
    }

    private static class checkPermissionLjava_security_PermissionTesting {
        public static void main(String[] args) {
            MutableSecurityManager sm = new MutableSecurityManager();
            sm.addPermission(MutableSecurityManager.SET_SECURITY_MANAGER);
            System.setSecurityManager(sm);
            try {
                try {
                    System.getSecurityManager().checkPermission(
                            new RuntimePermission("createClassLoader"));
                    fail("This should throw a SecurityException");
                } catch (SecurityException e) {
                }
            } finally {
                System.setSecurityManager(null);
            }
        }
    }

    /**
     * @tests java.lang.SecurityManager#checkAccess(java.lang.Thread)
     */
    public void test_checkAccessLjava_lang_Thread() throws InterruptedException {
        // Regression for HARMONY-66
        Thread t = new Thread() {
            @Override
            public void run() {
            };
        };
        t.start();
        t.join();
        new SecurityManager().checkAccess(t);
    }
}
