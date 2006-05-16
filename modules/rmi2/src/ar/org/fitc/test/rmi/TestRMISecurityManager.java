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
package ar.org.fitc.test.rmi;

import java.rmi.RMISecurityManager;
import java.security.Permission;

import junit.framework.TestCase;

public class TestRMISecurityManager extends TestCase {

    public static void main(String[] args) {
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'java.rmi.RMISecurityManager.RMISecurityManager()'
     */
    public void testRMISecurityManager001() {
        try {
            assertTrue(new RMISecurityManager() instanceof RMISecurityManager);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testRMISecurityManager002() {
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
            new RMISecurityManager();
            fail("SecurityMAnager not allow the first use");
        } catch (SecurityException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        } finally {
            System.setSecurityManager(smOld);
        }
    }

}
