/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
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
* @author Alexey V. Varlamov
* @version $Revision$
*/

package java.security;

import junit.framework.TestCase;

/**
 * Tests for <code>UnresolvedPermission</code> class fields and methods
 * 
 */

public class UnresolvedPermission_ImplTest extends TestCase {  
    
    /**
     * This test is valid since 1.5 release only. Checks that UnresolvedPermission returns the proper 
     * data for target permission. For non-empty certificates array, 
     * returns a new array each time this method is called.
     */
    public void testTargetData()
    {
        String type = "laskjhlsdk 2345346";
        String name = "^%#UHVKU^%V  887y";
        String action = "JHB ^%(*&T klj3h4";
        UnresolvedPermission up = new UnresolvedPermission(type, name, action, null);
        assertEquals(type, up.getUnresolvedType());
        assertEquals(name, up.getUnresolvedName()); 
        assertEquals(action, up.getUnresolvedActions()); 
        assertNull(up.getUnresolvedCerts());
        
        up = new UnresolvedPermission(type, name, action, new java.security.cert.Certificate[0]);
        assertNull("Empty array should be the same as null", up.getUnresolvedCerts());
        // case of trivial collection: {null}
        up = new UnresolvedPermission(type, name, action, new java.security.cert.Certificate[3]);
        assertNull(up.getUnresolvedCerts());
        //assertNotSame(up.getUnresolvedCerts(), up.getUnresolvedCerts());
        //assertEquals(1, up.getUnresolvedCerts().length);
    }
    
    public void testEquals()
    {
        String type = "KJHGUiy 24y";
        String name = "kjhsdkfj ";
        String action = "T klj3h4";
        UnresolvedPermission up = new UnresolvedPermission(type, name, action, null);
        UnresolvedPermission up2 = new UnresolvedPermission(type, name, action, null);
        assertFalse(up.equals(null));
        assertFalse(up.equals(new Object()));
        assertFalse(up.equals(new BasicPermission("df"){}));
        assertTrue(up.equals(up));
        assertTrue(up.equals(up2));
        assertTrue(up.hashCode() == up2.hashCode());
        up2 = new UnresolvedPermission(type, name, action, new java.security.cert.Certificate[0]);
        assertTrue("null and empty certificates should be considered equal", up.equals(up2));
        assertTrue(up.hashCode() == up2.hashCode());
        up2 = new UnresolvedPermission(type, name, action, new java.security.cert.Certificate[2]);
        assertTrue(up.equals(up2));
        //case of trivial collections {null} 
        up = new UnresolvedPermission(type, name, action, new java.security.cert.Certificate[10]);
        assertTrue(up.equals(up2));
        assertTrue(up.hashCode() == up2.hashCode());
    }

    /**
     * resolve the unresolved permission to the permission of specified class.
     */
    public void testResolve()
    {
        String name = "abc";
        UnresolvedPermission up = new UnresolvedPermission("java.security.SecurityPermission", name, null, null);
        Permission expected = new SecurityPermission(name);
        //test valid input
        assertEquals(expected, up.resolve(SecurityPermission.class));
        
        //test invalid class
        assertNull(up.resolve(Object.class));
        
        //test invalid signers
        //up = new UnresolvedPermission("java.security.SecurityPermission", name, null, new java.security.cert.Certificate[1]);
        //assertNull(up.resolve(SecurityPermission.class));
        
        //another valid case
        up = new UnresolvedPermission("java.security.AllPermission", null, null, new java.security.cert.Certificate[0]);
        assertEquals(new AllPermission(name, ""), up.resolve(AllPermission.class));
    }
}
