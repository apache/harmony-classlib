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
     * Tests that Classloader.defineClass() assigns appropriate 
     * default domains to the defined classes.
     */
    public void test_defineClass_defaultDomain() throws Exception {
        // Regression for HARMONY-765 
        DynamicPolicy plc = new DynamicPolicy();
        Policy back = Policy.getPolicy();
        try {
            Policy.setPolicy(plc);

            Class<?> a = new Ldr().define();

            Permission p = new SecurityPermission("abc");
            assertFalse("impossible! misconfiguration?", a.getProtectionDomain().implies(p));

            plc.pc = p.newPermissionCollection();
            plc.pc.add(p);
            assertTrue("default domain is not dynamic", a.getProtectionDomain().implies(p));
        } finally {
            Policy.setPolicy(back);
        }
    }

    /**
     * @tests java.lang.ClassLoader#getResource(java.lang.String)
     */
    public void test_getResourceLjava_lang_String() {
        // Test for method java.net.URL
        // java.lang.ClassLoader.getResource(java.lang.String)
        java.net.URL u = ClassLoader.getSystemClassLoader().getResource("hyts_Foo.c");
        assertNotNull("Unable to find resource", u);
        java.io.InputStream is = null;
        try {
            is = u.openStream();
            assertNotNull("Resource returned is invalid", is);
            is.close();
        } catch (java.io.IOException e) {
            fail("IOException getting stream for resource : " + e.getMessage());
        }
    }

    /**
     * @tests java.lang.ClassLoader#getResourceAsStream(java.lang.String)
     */
    public void test_getResourceAsStreamLjava_lang_String() {
        // Test for method java.io.InputStream
        // java.lang.ClassLoader.getResourceAsStream(java.lang.String)
        // Need better test...

        java.io.InputStream is = null;
        assertNotNull("Failed to find resource: hyts_Foo.c", (is = ClassLoader
                .getSystemClassLoader().getResourceAsStream("hyts_Foo.c")));
        try {
            is.close();
        } catch (java.io.IOException e) {
            fail("Exception during getResourceAsStream: " + e.toString());
        }
    }

    /**
     * @tests java.lang.ClassLoader#getSystemClassLoader()
     */
    public void test_getSystemClassLoader() {
        // Test for method java.lang.ClassLoader
        // java.lang.ClassLoader.getSystemClassLoader()
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        java.io.InputStream is = cl.getResourceAsStream("hyts_Foo.c");
        assertNotNull("Failed to find resource from system classpath", is);
        try {
            is.close();
        } catch (java.io.IOException e) {
        }

    }

    /**
     * @tests java.lang.ClassLoader#getSystemResource(java.lang.String)
     */
    public void test_getSystemResourceLjava_lang_String() {
        // Test for method java.net.URL
        // java.lang.ClassLoader.getSystemResource(java.lang.String)
        // Need better test...
        assertNotNull("Failed to find resource: hyts_Foo.c", ClassLoader
                .getSystemResource("hyts_Foo.c"));
    }
}

class DynamicPolicy extends Policy {

    public PermissionCollection pc;

    @Override
    public PermissionCollection getPermissions(ProtectionDomain pd) {
        return pc;
    }

    @Override
    public PermissionCollection getPermissions(CodeSource codesource) {
        return pc;
    }

    @Override
    public void refresh() {
    }
}

class A {
}

class Ldr extends ClassLoader {
    @SuppressWarnings("deprecation")
    public Class<?> define() throws Exception {
        Package p = getClass().getPackage();
        String path = p == null ? "" : p.getName().replace('.', File.separatorChar)
                + File.separator;
        InputStream is = getResourceAsStream(path + "A.class");
        byte[] buf = new byte[512];
        int len = is.read(buf);
        return defineClass(buf, 0, len);
    }
}
