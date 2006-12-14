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
import java.security.Security;
import java.security.SecurityPermission;
import java.security.AllPermission;
import tests.support.Support_Exec;

import junit.framework.TestCase;

public class SecurityManagerTest extends TestCase {
    
    /**
     * @tests java.lang.SecurityManager#checkPackageAccess(String)
     */
    public void test_checkPackageAccessLjava_lang_String() {
        final String old = Security.getProperty("package.access");
        Security.setProperty("package.access", "a.,bbb, c.d.");

        MutableSecurityManager sm = new MutableSecurityManager();
        sm.denyPermission(new RuntimePermission("accessClassInPackage.*"));
        
        try {
            sm.checkPackageAccess("z.z.z");
            sm.checkPackageAccess("aa");
            sm.checkPackageAccess("bb");
            sm.checkPackageAccess("c");

            try {
                sm.checkPackageAccess("a");
                fail("This should throw a SecurityException.");
            } catch (SecurityException ok) {}
            
            try {
                sm.checkPackageAccess("bbb");
                fail("This should throw a SecurityException.");
            } catch (SecurityException ok) {}

            try {
                sm.checkPackageAccess("c.d.e");
                fail("This should throw a SecurityException.");
            } catch (SecurityException ok) {}
            
            Security.setProperty("package.access", "QWERTY");
            sm.checkPackageAccess("a");
            sm.checkPackageAccess("qwerty");
            try {
                sm.checkPackageAccess("QWERTY");
                fail("This should throw a SecurityException.");
            } catch (SecurityException ok) {}

        } finally {
            Security.setProperty("package.access", 
                    old == null ? "" : old);
        }
    }

    /**
     * @tests java.lang.SecurityManager#checkPackageDefinition(String)
     */
    public void test_checkPackageDefinitionLjava_lang_String() {
        final String old = Security.getProperty("package.definition");
        Security.setProperty("package.definition", "a.,bbb, c.d.");

        MutableSecurityManager sm = new MutableSecurityManager();
        sm.denyPermission(new RuntimePermission("defineClassInPackage.*"));
        
        try {
            sm.checkPackageDefinition("z.z.z");
            sm.checkPackageDefinition("aa");
            sm.checkPackageDefinition("bb");
            sm.checkPackageDefinition("c");

            try {
                sm.checkPackageDefinition("a");
                fail("This should throw a SecurityException.");
            } catch (SecurityException ok) {}
            
            try {
                sm.checkPackageDefinition("bbb");
                fail("This should throw a SecurityException.");
            } catch (SecurityException ok) {}

            try {
                sm.checkPackageDefinition("c.d.e");
                fail("This should throw a SecurityException.");
            } catch (SecurityException ok) {}

            Security.setProperty("package.definition", "QWERTY");
            sm.checkPackageDefinition("a");
            sm.checkPackageDefinition("qwerty");
            try {
                sm.checkPackageDefinition("QWERTY");
                fail("This should throw a SecurityException.");
            } catch (SecurityException ok) {}

        } finally {
            Security.setProperty("package.definition", 
                    old == null ? "" : old);
        }
    }

    /**
     * @tests java.lang.SecurityManager#checkMemberAccess(java.lang.Class, int)
     */
    public void test_checkMemberAccessLjava_lang_ClassI() {
        MutableSecurityManager sm = new MutableSecurityManager();
        // enable all but access check
        sm.addPermission(new AllPermission());
        sm.denyPermission(new RuntimePermission("accessDeclaredMembers"));
        System.setSecurityManager(sm);
        try {
            getClass().getDeclaredFields();

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
            System.setSecurityManager(sm);
            try {
                System.getSecurityManager().checkPermission(
                    new RuntimePermission("createClassLoader"));
                fail("This should throw a SecurityException");
            } catch (SecurityException ok) {}
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
