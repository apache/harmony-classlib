/*
 * Copyright 2006 The Apache Software Foundation or its licensors, as
 * applicable
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

package org.apache.harmony.luni.tests.java.lang;

import java.io.File;
import java.io.InputStream;
import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.Policy;
import java.security.ProtectionDomain;
import java.security.SecurityPermission;
import junit.framework.TestCase;


public class ClassLoaderTest extends TestCase {

    /**
     * Tests that Classloader.defineClass() assignes appropriate 
     * default domains to the defined classes.
     */
    public void test_defineClass_defaultDomain() throws Exception {
        // Regression for HARMONY-765 
        DynamicPolicy plc = new DynamicPolicy();
        Policy back = Policy.getPolicy();
        try{
            Policy.setPolicy(plc);

            Class a = new Ldr().define();

            Permission p = new SecurityPermission("abc");
            assertFalse("impossible! misconfiguration?",
                        a.getProtectionDomain().implies(p));
            
            plc.pc = p.newPermissionCollection();
            plc.pc.add(p);
            assertTrue("default domain is not dynamic",
                       a.getProtectionDomain().implies(p));
        } finally {
            Policy.setPolicy(back);
        }
    }

}

class DynamicPolicy extends Policy {

        public PermissionCollection pc;

        public PermissionCollection getPermissions(ProtectionDomain pd) {
            return pc;
        }

        public PermissionCollection getPermissions(CodeSource codesource) {
            return pc;
        }

        public void refresh() {}
}

class A{}

class Ldr extends ClassLoader {
    public Class define() throws Exception{
        Package p = getClass().getPackage();
        String path =
            p == null ? "" : p.getName().replace('.', File.separatorChar) + File.separator;
        InputStream is = getResourceAsStream(path+"A.class");
        byte[] buf = new byte[512];
        int len = is.read(buf);
        return defineClass(buf, 0, len);
    }
}
