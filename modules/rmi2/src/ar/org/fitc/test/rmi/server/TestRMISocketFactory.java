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

import java.io.IOException;
import java.rmi.server.RMISocketFactory;
import java.security.Permission;

import junit.framework.TestCase;
import ar.org.fitc.test.rmi.server.testclasses.MyRMISocketFactory;
import ar.org.fitc.test.util.Messages;

public class TestRMISocketFactory extends TestCase implements Messages {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestRMISocketFactory.class);
    }

    public TestRMISocketFactory(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for
     * 'java.rmi.server.RMISocketFactory.getDefaultSocketFactory()'
     */
    public final void testGetDefaultSocketFactory001() {
        assertNotNull(RMISocketFactory.getDefaultSocketFactory().getClass()
                .getCanonicalName());
    }

    /*
     * Test method for 'java.rmi.server.RMISocketFactory.getFailureHandler()'
     */
    public final void testGetFailureHandler001() {
        assertNotNull(msgNotNull, RMISocketFactory.getDefaultSocketFactory());
    }

    public final void testGetFailureHandler002() {
        RMISocketFactory.setFailureHandler(null);
        assertNotNull(msgNotNull, RMISocketFactory.getDefaultSocketFactory());
    }

    /*
     * Test method for
     * 'java.rmi.server.RMISocketFactory.setFailureHandler(RMIFailureHandler)'
     */
    public final void testSetFailureHandler002() {
        RMISocketFactory.setFailureHandler(null);
        assertNotNull(msgNotNull, RMISocketFactory.getDefaultSocketFactory());
    }

    /*
     * Test method for 'java.rmi.server.RMISocketFactory.setSocketFactory()'
     */

    public final void testSetSocketFactory001() {
        SecurityManager smOld = System.getSecurityManager();
        try {
            SecurityManager sm = new SecurityManager() {
                public void checkPermission(Permission perm) {
                    boolean allow = !perm.implies(new RuntimePermission(
                            "setFactory"));
                    if (!allow) {
                        throw new SecurityException(
                                "No, No, No, you can't do that.");
                    }
                }
            };
            System.setSecurityManager(sm);
            RMISocketFactory.setSocketFactory(new MyRMISocketFactory());
            fail("Should raise SecurityException");
        } catch (SecurityException e) {
        } catch (Throwable e) {
            fail("Should raise SecurityException but raised: " + e);
        } finally {
            System.setSecurityManager(smOld);
        }
    }

    public final void testSetSocketFactory002() {
        try {
            RMISocketFactory.setSocketFactory(new MyRMISocketFactory());
        } catch (Throwable e) {
            fail("Should not raise an exception but raised: " + e);
        }
    }

    public final void testSetSocketFactory003() throws IOException {
        try {
            RMISocketFactory.setSocketFactory(null);
            fail("Should raise IOException");
        } catch (IOException e) {
        } catch (Throwable e) {
            fail("Should raise IOException but raised: " + e);
        }
    }
}
