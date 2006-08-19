package org.apache.harmony.langmanagement.tests.java.lang.management;

import java.lang.management.ManagementPermission;

import junit.framework.TestCase;

public class ManagementPermissionTest extends TestCase {

    public void testManagementPermissionString() {
        ManagementPermission control = new ManagementPermission("control");
        assertEquals("control", control.getName());

        ManagementPermission monitor = new ManagementPermission("monitor");
        assertEquals("monitor", monitor.getName());

        try {
            new ManagementPermission("invalid");
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

    public void testManagementPermissionStringString() {
        ManagementPermission control = new ManagementPermission("control", null);
        assertEquals("control", control.getName());
        
        control = new ManagementPermission("control", "");
        assertEquals("control", control.getName());

        ManagementPermission monitor = new ManagementPermission("monitor", null);
        assertEquals("monitor", monitor.getName());
        
        monitor = new ManagementPermission("monitor", "");
        assertEquals("monitor", monitor.getName());

        try {
            new ManagementPermission("invalid", null);
            fail();
        } catch (IllegalArgumentException e) {
        }
        
        try {
            new ManagementPermission("invalid", "");
            fail();
        } catch (IllegalArgumentException e) {
        }
        
        try {
            new ManagementPermission("control", "actions");
            fail();
        } catch (IllegalArgumentException e) {
        }
        
        try {
            new ManagementPermission("monitor", "actions");
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

}
