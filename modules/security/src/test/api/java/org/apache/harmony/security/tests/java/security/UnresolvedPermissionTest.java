/*
 *  Copyright 2006 The Apache Software Foundation or its licensors, as applicable.
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

package org.apache.harmony.security.tests.java.security;

import java.security.AllPermission;
import java.security.SecurityPermission;
import java.security.UnresolvedPermission;

import junit.framework.TestCase;

/**
 * Tests for <code>UnresolvedPermission</code> class fields and methods
 * 
 */

public class UnresolvedPermissionTest extends TestCase {
    
    /**
     * Creates an Object with given name, type, action, certificaties. 
     * Empty or null type is not allowed - exception should be thrown.
     */
    public void testCtor()
    {
        String type = "laskjhlsdk 2345346";
        String name = "^%#UHVKU^%V  887y";
        String action = "JHB ^%(*&T klj3h4";
        UnresolvedPermission up = new UnresolvedPermission(type, name, action, null);
        assertEquals(type, up.getName());
        assertEquals("", up.getActions());
        assertEquals("(unresolved " + type + " " + name + " " + action + ")", up.toString());
        
        up = new UnresolvedPermission(type, null, null, null);
        assertEquals(type, up.getName());
        assertEquals("", up.getActions());
        assertEquals("(unresolved " + type + " null null)", up.toString());
        
        up = new UnresolvedPermission(type, "", "", new java.security.cert.Certificate[0]);
        assertEquals(type, up.getName());
        assertEquals("", up.getActions());
        assertEquals("(unresolved " + type + "  )", up.toString());
        
        try {
            new UnresolvedPermission(null, name, action, null);
            fail("exception is not thrown on null type");
        }
        catch (Exception ok) {}

        //Regression for HARMONY-733
        assertNotNull(new UnresolvedPermission("", name, action, null));
    }
    
    /** 
     * UnresolvedPermission never implies any other permission.
     */
    public void testImplies()
    {
        UnresolvedPermission up = new UnresolvedPermission("java.security.SecurityPermission", "a.b.c", null, null);
        assertFalse(up.implies(up));
        assertFalse(up.implies(new AllPermission()));
        assertFalse(up.implies(new SecurityPermission("a.b.c")));
    }
}
