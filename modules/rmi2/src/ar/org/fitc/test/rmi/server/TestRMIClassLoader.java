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
 * @author Hugo Beilis
 * @author Osvaldo Demo
 * @author Jorge Rafael
 * @version 1.0
 */
package ar.org.fitc.test.rmi.server;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.RMIClassLoader;
import java.security.Permission;

import junit.framework.TestCase;
import ar.org.fitc.test.rmi.server.testclasses.LoaderClassTest002;
import ar.org.fitc.test.util.MyClassLoader;

public class TestRMIClassLoader extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestRMIClassLoader.class);
    }

    static final String path = System.getProperties().get("user.dir")
            .toString();

    static final String pathOwnClass = path + File.separatorChar + ".."
            + File.separatorChar + "RMIClassToLoad" + File.separatorChar;

    public TestRMIClassLoader(String name) {
        super(name);

    }

    private void LoadClassStringString(String clase, boolean url) {
        SecurityManager smOld = System.getSecurityManager();
        System.setSecurityManager(new RMISecurityManager() {
            public void checkPermission(Permission perm) {
            }
        });
        try {
            if (url) {
                RMIClassLoader.loadClass(new URL("file://" + pathOwnClass),
                        clase);
            } else {
                RMIClassLoader.loadClass("file://" + pathOwnClass, clase);
            }
        } catch (Throwable e) {
            fail("Failed with: " + e);
        } finally {
            System.setSecurityManager(smOld);
        }

    }

    private void LoadClassStringStringClassLoader(String path, String clase,
            String name) throws MalformedURLException {
        SecurityManager smOld = System.getSecurityManager();
        System.setSecurityManager(new RMISecurityManager() {
            public void checkPermission(Permission perm) {
            }
        });
        try {
            File f = new File(pathOwnClass);
            if (f.exists() && f.isDirectory()) {
                URL list[] = { new URL("file://" + pathOwnClass) };
                try {
                    RMIClassLoader.loadClass(path, clase, new URLClassLoader(
                            list));
                } catch (Throwable e) {
                    fail("Should not raise an exception but raised: " + e);
                }
            } else {
                System.err
                        .println("Directory: " + pathOwnClass + " not found.");
                System.err.println(name + " disabled.");
                fail("TEST DISABLED");
            }
        } finally {
            System.setSecurityManager(smOld);
        }
    }

    protected void setUp() throws Exception {
        super.setUp();
        System.setProperty("java.rmi.server.codebase", "file://" + path);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'java.rmi.server.RMIClassLoader.getClassAnnotation(Class<?>)'
     */
    public final void testGetClassAnnotation001() {
        assertNotNull(RMIClassLoader
                .getClassAnnotation(LoaderClassTest002.class));
    }

    public final void testGetClassAnnotation002() throws RemoteException,
            MalformedURLException, NotBoundException {
        assertEquals("file://" + path, RMIClassLoader
                .getClassAnnotation(LoaderClassTest002.class));
    }

    public final void testGetClassAnnotation003() {
        try {
            RMIClassLoader.getClassAnnotation(null);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
        } catch (Throwable e) {
            fail("Should raise NullPointerException but raised: " + e);
        }
    }

    public final void testGetClassAnnotation004() {
        System.getProperties().remove("java.rmi.server.codebase");
    }

    /*
     * Test method for 'java.rmi.server.RMIClassLoader.getClassLoader(String)'
     */
    public final void testGetClassLoader001() {
        try {
            assertNotNull(RMIClassLoader.getClassLoader(null));
        } catch (Throwable e) {
            fail("Should not raise an exception but raised: " + e);
        }
    }

    public final void testGetClassLoader002() {
        try {

            RMIClassLoader.getClassLoader("any");
            fail("Should raise MalformedURLException");
        } catch (MalformedURLException e) {
        } catch (Throwable e) {
            fail("Should raise MalformedURLException but raised: " + e);
        }
    }

    public final void testGetClassLoader003() throws RemoteException,
            MalformedURLException {
        SecurityManager smOld = System.getSecurityManager();
        try {
            SecurityManager sm = new SecurityManager() {
                boolean allow = false;

                public void checkPermission(Permission perm) {
                    if (!allow) {
                        allow = true;
                        throw new SecurityException("Is it use the Security?");
                    }
                }
            };
            System.setSecurityManager(sm);
            RMIClassLoader.getClassLoader("file://" + pathOwnClass);
            fail("Should raise SecurityException");
        } catch (SecurityException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        } finally {
            try {
                System.setSecurityManager(smOld);
            } catch (SecurityException e) {
                System.setSecurityManager(smOld);
            }
        }

    }

    public final void testGetClassLoader004() {
        try {
            assertNotNull(RMIClassLoader.getClassLoader("file://" + path
                    + " file://" + pathOwnClass));
        } catch (Throwable e) {
            fail("Should not raise an exception but raised: " + e);
        }
    }

    /*
     * Test method for
     * 'java.rmi.server.RMIClassLoader.getDefaultProviderInstance()'
     */
    public final void testGetDefaultProviderInstance001() {
        assertNotNull(RMIClassLoader.getDefaultProviderInstance());
    }

    public final void testGetDefaultProviderInstance002() {

        SecurityManager smOld = System.getSecurityManager();
        try {
            SecurityManager sm = new SecurityManager() {
                boolean allow = false;

                public void checkPermission(Permission perm) {
                    if (!allow) {
                        allow = true;
                        throw new SecurityException(
                                "No, No, No, you can't do that.");
                    }
                }
            };
            System.setSecurityManager(sm);
            RMIClassLoader.getDefaultProviderInstance();
            fail("Should raise SecurityException");
        } catch (SecurityException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        } finally {
            try {
                System.setSecurityManager(smOld);
            } catch (SecurityException e) {
                System.setSecurityManager(smOld);
            }
        }
    }

    /*
     * Test method for 'java.rmi.server.RMIClassLoader.loadClass(String,
     * String)'
     */
    public final void testLoadClassStringString001()
            throws MalformedURLException, ClassNotFoundException {
        LoadClassStringString(
                "ar.org.fitc.test.rmi.server.testclasses.LoaderClassTest001",
                false);
    }

    public final void testLoadClassStringString002() {
        LoadClassStringString(
                "ar.org.fitc.test.rmi.server.testclasses.LoaderClassTest002",
                false);
    }

    public final void testLoadClassStringString003() {
        LoadClassStringString(
                "ar.org.fitc.test.rmi.server.testclasses.LoaderClassTest003",
                false);
    }

    public final void testLoadClassStringString004() {
        try {
            RMIClassLoader
                    .loadClass("file://.",
                            "ar.org.fitc.test.rmi.server.testclasses.LoaderClassTest006");
            fail("Should raise ClassNotFoundException");
        } catch (ClassNotFoundException e) {
        } catch (Throwable e) {
            fail("Should raise ClassNotFoundException but raised: " + e);
        }
    }

    public final void testLoadClassStringString005() {
        try {
            RMIClassLoader
                    .loadClass("????//.??",
                            "ar.org.fitc.test.rmi.server.testclasses.LoaderClassTest001");
            fail("Should raise MalformedURLException");
        } catch (MalformedURLException e) {
        } catch (Throwable e) {
            fail("Should raise MalformedURLException but raised: " + e);
        }
    }

    public final void testLoadClassStringString006() {
        try {
            RMIClassLoader
                    .loadClass("",
                            "ar.org.fitc.test.rmi.server.testclasses.LoaderClassTest002");
        } catch (Throwable e) {
            fail("Should not raise an exception but raised: " + e);
        }
    }

    /*
     * Test method for 'java.rmi.server.RMIClassLoader.loadClass(String, String,
     * ClassLoader)'
     */
    public final void testLoadClassStringStringClassLoader001() {
        try {
            RMIClassLoader
                    .loadClass(
                            "file://.",
                            "ar.org.fitc.test.rmi.server.testclasses.LoaderClassTest002",
                            null);
        } catch (Throwable e) {
            fail("Should not raise an exception but raised: " + e);
        }
    }

    public final void testLoadClassStringStringClassLoader002() {
        try {
            RMIClassLoader
                    .loadClass(
                            "//.",
                            "ar.org.fitc.test.rmi.server.testclasses.LoaderClassTest002",
                            new MyClassLoader());
            fail("Should raise MalformedURLException");
        } catch (MalformedURLException e) {
        } catch (Throwable e) {
            fail("Should raise MalformedURLException but raised: " + e);
        }
    }

    public final void testLoadClassStringStringClassLoader003() {
        try {
            RMIClassLoader
                    .loadClass(
                            "file://.",
                            "ar.org.fitc.test.rmi.server.testclasses.LoaderClassTest016",
                            new MyClassLoader());
            fail("Should raise ClassNotFoundException");
        } catch (ClassNotFoundException e) {
        } catch (Throwable e) {
            fail("Should raise ClassNotFoundException");
        }
    }

    public final void testLoadClassStringStringClassLoader004()
            throws MalformedURLException {
        LoadClassStringStringClassLoader("",
                "ar.org.fitc.test.rmi.server.testclasses.LoaderClassTest001",
                "testLoadClassStringStringClassLoader004");
    }

    public final void testLoadClassStringStringClassLoader005()
            throws MalformedURLException {
        LoadClassStringStringClassLoader("",
                "ar.org.fitc.test.rmi.server.testclasses.LoaderClassTest002",
                "testLoadClassStringStringClassLoader005");
    }

    public final void testLoadClassStringStringClassLoader006() {
        SecurityManager smOld = System.getSecurityManager();
        System.setSecurityManager(new RMISecurityManager() {
            public void checkPermission(Permission perm) {
            }
        });
        try {
            RMIClassLoader
                    .loadClass(
                            "file://" + pathOwnClass,
                            "ar.org.fitc.test.rmi.server.testclasses.LoaderClassTest001",
                            null);
        } catch (Throwable e) {
            fail("Should not raise an exception but raised: " + e);
        } finally {
            System.setSecurityManager(smOld);
        }
    }

    /*
     * Test method for 'java.rmi.server.RMIClassLoader.loadClass(URL, String)'
     */
    public final void testLoadClassURLString001() {
        LoadClassStringString(
                "ar.org.fitc.test.rmi.server.testclasses.LoaderClassTest001",
                true);
    }

    public final void testLoadClassURLString002() {
        LoadClassStringString(
                "ar.org.fitc.test.rmi.server.testclasses.LoaderClassTest002",
                true);
    }

    public final void testLoadClassURLString003() {
        LoadClassStringString(
                "ar.org.fitc.test.rmi.server.testclasses.LoaderClassTest003",
                true);
    }

    public final void testLoadClassURLString004() {
        try {
            RMIClassLoader
                    .loadClass(new URL("file://."),
                            "ar.org.fitc.test.rmi.server.testclasses.LoaderClassTest006");
            fail("Should raise ClassNotFoundException");
        } catch (ClassNotFoundException e) {
        } catch (Throwable e) {
            fail("Should raise ClassNotFoundException but raised: " + e);
        }
    }

    public final void testLoadClassURLString005() {
        try {
            RMIClassLoader
                    .loadClass(new URL("//."),
                            "ar.org.fitc.test.rmi.server.testclasses.LoaderClassTest001");
            fail("Should raise MalformedURLException");
        } catch (MalformedURLException e) {
        } catch (Throwable e) {
            fail("Should raise MalformedURLException but raised: " + e);
        }
    }

    public final void testLoadClassURLString006() {
        try {
            RMIClassLoader
                    .loadClass((URL) null,
                            "ar.org.fitc.test.rmi.server.testclasses.LoaderClassTest002");
        } catch (Throwable e) {
            fail("Failed with: " + e);
        }
    }

    public final void testLoadClassURLString007() {
        try {
            RMIClassLoader
                    .loadClass((URL) null,
                            "ar.org.fitc.test.rmi.server.testclasses.LoaderClassTest001");
            fail("Should raise ClassNotFoundException");
        } catch (ClassNotFoundException e) {
        } catch (Throwable e) {
            fail("Failed with: " + e);
        }
    }

    /*
     * Test method for 'java.rmi.server.RMIClassLoader.loadProxyClass(String,
     * String[], ClassLoader)'
     */
    public final void testLoadProxyClass001() {
        String interfaces[] = {
                "ar.org.fitc.test.rmi.server.testclasses.SuperProxy",
                "ar.org.fitc.test.rmi.server.testclasses.OverProxy" };
        Class c = null;

        try {
            c = RMIClassLoader.loadProxyClass("file://" + pathOwnClass,
                    interfaces, new MyClassLoader());
            try {
                c.getMethod("proxy1");
                c.getMethod("proxy2");
            } catch (NoSuchMethodException e) {
                fail("The class is not implementing all the interfaces");
            }

        } catch (Throwable e) {
            fail("Should not raise an exception but raised: " + e);
        }

    }

    public final void testLoadProxyClass002() {
        String interfaces[] = {
                "ar.org.fitc.test.rmi.server.testclasses.SuperProxy",
                "ar.org.fitc.test.rmi.server.testclasses.OverProxyasdf" };
        try {
            RMIClassLoader.loadProxyClass("file://" + pathOwnClass, interfaces,
                    new MyClassLoader());
            fail("Should raise ClassNotFoundException");
        } catch (ClassNotFoundException e) {

        } catch (Throwable e) {
            fail("Should not raise an exception but raised: " + e);
        }

    }

    public final void testLoadProxyClass003() {
        SecurityManager smOld = System.getSecurityManager();
        System.setSecurityManager(new RMISecurityManager() {
            public void checkPermission(Permission perm) {
            }
        });
        String interfaces[] = {
                "ar.org.fitc.test.rmi.server.testclasses.SuperProxy",
                "ar.org.fitc.test.rmi.server.testclasses.OverProxy",
                "ar.org.fitc.test.rmi.server.testclasses.LoaderInterface" };
        Class c = null;

        try {
            c = RMIClassLoader.loadProxyClass("file://" + pathOwnClass,
                    interfaces, new MyClassLoader());
            try {
                c.getMethod("proxy1");
                c.getMethod("proxy2");
            } catch (NoSuchMethodException e) {
                fail("The class is not implementing all the interfaces");
            }

        } catch (Throwable e) {
            fail("Should not raise an exception but raised: " + e);
        } finally {
            System.setSecurityManager(smOld);
        }

    }

    public final void testLoadProxyClass004() {
        String interfaces[] = {
                "ar.org.fitc.test.rmi.server.testclasses.SuperProxy",
                "ar.org.fitc.test.rmi.server.testclasses.OverProxy",
                "ar.org.fitc.test.rmi.server.testclasses.LoaderInterface" };

        try {
            RMIClassLoader.loadProxyClass("file://" + pathOwnClass, interfaces,
                    new MyClassLoader());
            fail("Should raise ClassNotFoundException");
        } catch (ClassNotFoundException e) {
        } catch (Throwable e) {
            fail("Should not raise an exception but raised: " + e);
        }

    }

    public final void testLoadProxyClass005() {
        SecurityManager smOld = System.getSecurityManager();
        System.setSecurityManager(new RMISecurityManager() {
            public void checkPermission(Permission perm) {
            }
        });
        String interfaces[] = {
                "ar.org.fitc.test.rmi.server.testclasses.SuperProxy",
                "ar.org.fitc.test.rmi.server.testclasses.OverProxy",
                "ar.org.fitc.test.rmi.server.testclasses.LoaderInterface" };
        try {
            RMIClassLoader.loadProxyClass("file://.", interfaces,
                    new MyClassLoader());
            fail("Should raise ClassNotFoundException");
        } catch (ClassNotFoundException e) {
        } catch (Throwable e) {
            fail("Should not raise an exception but raised: " + e);
        } finally {
            System.setSecurityManager(smOld);
        }

    }

    public final void testLoadProxyClass006() {
        String interfaces[] = {
                "ar.org.fitc.test.rmi.server.testclasses.SuperProxy",
                "ar.org.fitc.test.rmi.server.testclasses.OverProxy",
                "ar.org.fitc.test.rmi.server.testclasses.LoaderInterface" };
        try {
            RMIClassLoader.loadProxyClass(null, interfaces, new URLClassLoader(
                    new URL[] { new URL("file://" + pathOwnClass) }));
        } catch (Throwable e) {
            fail("Should not raise an exception but raised: " + e);
        }

    }

    public final void testLoadProxyClass007() {
        String interfaces[] = {
                "ar.org.fitc.test.rmi.server.testclasses.SuperProxy",
                "ar.org.fitc.test.rmi.server.testclasses.OverProxy",
                "ar.org.fitc.test.rmi.server.testclasses.LoaderInterface" };
        try {
            RMIClassLoader.loadProxyClass("file://" + pathOwnClass, interfaces,
                    null);
            fail("Should raise ClassNotFoundException");
        } catch (ClassNotFoundException e) {
        } catch (Throwable e) {
            fail("Should not raise an exception but raised: " + e);
        }

    }

    public final void testLoadProxyClass008() {
        SecurityManager smOld = System.getSecurityManager();
        System.setSecurityManager(new RMISecurityManager() {
            public void checkPermission(Permission perm) {
            }
        });
        String interfaces[] = {
                "ar.org.fitc.test.rmi.server.testclasses.SuperProxy",
                "ar.org.fitc.test.rmi.server.testclasses.OverProxy",
                "ar.org.fitc.test.rmi.server.testclasses.LoaderInterface" };
        Class c = null;

        try {
            c = RMIClassLoader.loadProxyClass("file://" + pathOwnClass,
                    interfaces, null);
            try {
                c.getMethod("proxy1");
                c.getMethod("proxy2");
            } catch (NoSuchMethodException e) {
                fail("The class is not implementing all the interfaces");
            }

        } catch (Throwable e) {
            fail("Should not raise an exception but raised: " + e);
        } finally {
            System.setSecurityManager(smOld);
        }

    }

    public final void testLoadProxyClass009() {

        String interfaces[] = {};
        Class c = null;

        try {
            c = RMIClassLoader.loadProxyClass(null, interfaces, null);
            assertNotNull(c);
        } catch (Throwable e) {
            fail("Should not raise an exception but raised: " + e);
        }
    }
}
