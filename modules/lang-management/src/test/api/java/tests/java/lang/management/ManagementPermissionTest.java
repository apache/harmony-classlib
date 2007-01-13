/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * 
 */

package tests.java.lang.management;

import java.lang.management.ManagementPermission;

import junit.framework.TestCase;

public class ManagementPermissionTest extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for
     * 'java.lang.management.ManagementPermission.ManagementPermission(String)'
     */
    public void testManagementPermissionString() {
        // Normal cases.
        ManagementPermission mp = new ManagementPermission("monitor");
        assertNotNull(mp);
        mp = new ManagementPermission("control");
        assertNotNull(mp);

        // Bad input - null name
        try {
            mp = new ManagementPermission(null);
            fail("Should have thrown NPE for null name.");
        } catch (NullPointerException ignore) {
        }

        // Bad input - unwanted name
        try {
            mp = new ManagementPermission("Sunset");
            fail("Should have thrown IllegalArgumentException for incorrect name.");
        } catch (IllegalArgumentException ignore) {
        }

        // Bad input - correct name but in incorrect type
        try {
            mp = new ManagementPermission("Monitor");
            fail("Should have thrown IllegalArgumentException for upper-case name.");
        } catch (IllegalArgumentException ignore) {
        }
    }

    /*
     * Test method for
     * 'java.lang.management.ManagementPermission.ManagementPermission(String,
     * String)'
     */
    public void testManagementPermissionStringString() {
        // Normal cases.
        ManagementPermission mp = new ManagementPermission("monitor", "");
        assertNotNull(mp);
        mp = new ManagementPermission("control", null);
        assertNotNull(mp);

        // Bad input - null name
        try {
            mp = new ManagementPermission(null, null);
            fail("Should have thrown NPE for null name.");
        } catch (NullPointerException ignore) {
        }

        // Bad input - unwanted name
        try {
            mp = new ManagementPermission("Sunset", null);
            fail("Should have thrown IllegalArgumentException for incorrect name.");
        } catch (IllegalArgumentException ignore) {
        }

        // Bad input - correct name but in incorrect type
        try {
            mp = new ManagementPermission("Monitor", null);
            fail("Should have thrown IllegalArgumentException for upper-case name.");
        } catch (IllegalArgumentException ignore) {
        }

        // Bad input - action not one of "" or null
        try {
            mp = new ManagementPermission("monitor",
                    "You broke my heart Fredo.");
            fail("Should have thrown IllegalArgumentException for bad action.");
        } catch (IllegalArgumentException ignore) {
        }
    }

    /*
     * Test method for 'java.security.BasicPermission.equals(Object)'
     */
    public void testEquals() {
        ManagementPermission mp1 = new ManagementPermission("monitor");
        ManagementPermission mp2 = new ManagementPermission("monitor");
        assertEquals(mp1, mp2);

        mp1 = new ManagementPermission("control", "");
        mp2 = new ManagementPermission("control", "");
        assertEquals(mp1, mp2);

        mp1 = new ManagementPermission("monitor", null);
        mp2 = new ManagementPermission("monitor", null);
        assertEquals(mp1, mp2);
        
        mp1 = new ManagementPermission("monitor");
        mp2 = new ManagementPermission("control");
        assertFalse(mp1.equals(mp2));
    }

    /*
     * Test method for 'java.security.BasicPermission.implies(Permission)'
     */
    public void testImplies() {
        ManagementPermission mp1 = new ManagementPermission("monitor");
        ManagementPermission mp2 = new ManagementPermission("control");
        ManagementPermission mp3 = new ManagementPermission("monitor", "");
        assertTrue(mp1.implies(mp1));
        assertTrue(mp1.implies(mp3));
        assertFalse(mp1.implies(mp2));
        assertFalse(mp2.implies(mp1));
    }

    /*
     * Test method for 'java.security.BasicPermission.getActions()'
     */
    public void testGetActions() {
        ManagementPermission mp1 = new ManagementPermission("monitor");
        assertEquals("", mp1.getActions());
        
        mp1 = new ManagementPermission("control", null);
        assertEquals("", mp1.getActions());
    }
}
