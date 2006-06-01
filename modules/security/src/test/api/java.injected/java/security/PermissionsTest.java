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
* @author Alexey V. Varlamov
* @version $Revision$
*/

package java.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.NoSuchElementException;

import junit.framework.TestCase;


/**
 * Tests for <code>Permissions</code>
 * 
 */

public class PermissionsTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(PermissionsTest.class);
    }

    /**
     * Can add any type of permissions. Cannot add if collection is read-only.
     */
    public void testAdd() {
        Permissions ps = new Permissions();
        Permission ap = new AllPermission();
        Permission bp = new BasicPermission("jhb23jhg5") {
        };
        Permission sp0 = new SecurityPermission("abc");
        Permission sp1 = new SecurityPermission("a.b.c");
        Permission sp2 = new SecurityPermission("a.b.*");
        Permission sp3 = new SecurityPermission("a.*");
        Permission up1 = new UnresolvedPermission("131234", null, null, null);
        Permission up2 = new UnresolvedPermission("KUJKHVKJgyuygjhb", "xcv456",
            "26r ytf", new java.security.cert.Certificate[0]);
        Permission[] arr = new Permission[] {
            up1, up2, ap, bp, sp0, sp1, sp2, sp3,  };
        for (int i = 0; i < arr.length; i++) {
            ps.add(arr[i]);
        }

        //test add duplicate
        ps.add(up1);
        ps.add(sp0);

        ps.setReadOnly();
        try {
            ps.add(up1);
            fail("read-only flag is ignored");
        } catch (SecurityException ok) {
        }
    }

    /**
     * A permission is implied by this collection, if either of the following is
     * true:
     * <ul>
     * <li>This collection contains AllPermission;
     * <li>This collection has elements of the same type as the permission
     * being checked, and they imply it;
     * <li>This collection has UnresolvedPermissions which can be resolved to
     * the checked type, and after resolving they imply the checked one.
     * </ul>
     * The only exception is UnresolvedPermission itself, which is effectively
     * implied only by AllPermission
     */
    public void testImplies() {
        Permissions ps = new Permissions();
        Permission ap = new AllPermission();
        Permission bp1 = new BasicPermission("jhb23jhg5") {
        };
        Permission bp2 = new BasicPermission("&%#&^$HJVH") {

            public PermissionCollection newPermissionCollection() {
                return null;
            }
        };
        Permission sp1 = new SecurityPermission("a.b.c");
        Permission sp2 = new SecurityPermission("a.b.*");
        Permission sp3 = new SecurityPermission("a.*");
        Permission up = new UnresolvedPermission(
            "java.security.SecurityPermission", "*", null, null);

        Permission[] arr = new Permission[] {
            ap, bp1, bp2, sp1, sp2, up };
        for (int i = 0; i < arr.length; i++) {
            assertFalse(ps.implies(arr[i]));
        }

        ps.add(bp1);
        assertTrue(ps.implies(bp1));
        assertFalse(ps.implies(bp2));
        assertFalse(ps.implies(ap));
        assertFalse(ps.implies(sp1));

        ps.add(sp2);
        assertTrue(ps.implies(sp1));
        assertTrue(ps.implies(sp2));
        assertFalse(ps.implies(sp3));

        ps.add(up);
        assertFalse(ps.implies(up));
        assertTrue(ps.implies(sp1));
        assertTrue(ps.implies(sp2));
        assertTrue(ps.implies(sp3));

        assertFalse(ps.implies(null));
        ps.add(ap);
        for (int i = 0; i < arr.length; i++) {
            assertTrue(ps.implies(arr[i]));
        }
        assertTrue(ps.implies(null));
    }

    /**
     * Should return non-null empty enumeration for empty collection. For
     * non-empty collection, should always return enumeration over unique
     * elements.
     */
    public void testElements() {
        Permissions ps = new Permissions();
        Permission ap = new AllPermission();
        Permission bp = new BasicPermission("jhb23jhg5") {

            public PermissionCollection newPermissionCollection() {
                return null;
            }
        };
        Permission sp = new SecurityPermission("abc");
        Permission up1 = new UnresolvedPermission("131234", null, null, null);
        Permission up2 = new UnresolvedPermission("KUJKHVKJgyuygjhb", "xcv456",
            "26r ytf", new java.security.cert.Certificate[0]);

        Enumeration en = ps.elements();
        assertNotNull(en);
        assertFalse(en.hasMoreElements());
        
        ps.add(up1);
        en = ps.elements();
        assertTrue(en.hasMoreElements());
        assertTrue(up1.equals(en.nextElement()));
        assertFalse(en.hasMoreElements());

        ps.add(up1);
        en = ps.elements();
        assertTrue(en.hasMoreElements());
        assertTrue(up1.equals(en.nextElement()));
        //assertFalse(en.hasMoreElements());

        Permission[] arr = new Permission[] {
            ap, bp, sp, up1, up2 };
        for (int i = 0; i < arr.length; i++) {
            ps.add(arr[i]);
        }
        en = ps.elements();
        Collection els = new ArrayList();
        while (en.hasMoreElements()) {
            els.add(en.nextElement());
        }
        //assertEquals(5, els.size());
        assertTrue(els.containsAll(Arrays.asList(arr)));
    }
    
     
    /**
     * input parameter is null 
     */
    public void testNull(){
    	Permissions ps = new Permissions();
    	try {
    		ps.elements().nextElement();
    		fail("should throw NoSuchElementException");
    	} catch (NoSuchElementException e) {}
    	try {
    		assertFalse(ps.implies(null));
    		//fail("should throw NPE");
    	} catch (NullPointerException e){
    	    fail("implies() should not fail on null argument");
    	}
    	
    	try {	
    		ps.add(null);
    		fail("should throw NullPointerException");
    	} catch (NullPointerException e){}

    }

 }
