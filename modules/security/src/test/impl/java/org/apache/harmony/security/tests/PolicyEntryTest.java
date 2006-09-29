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

package org.apache.harmony.security.tests;

import java.net.URL;
import java.security.cert.Certificate;
import java.security.AllPermission;
import java.security.CodeSource;
import java.security.Permission;
import java.security.Principal;
import java.security.SecurityPermission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.apache.harmony.security.PolicyEntry;
import org.apache.harmony.security.UnresolvedPrincipal;
import junit.framework.TestCase;


/**
 * TODO Put your class description here
 * 
 */

public class PolicyEntryTest extends TestCase {

    /** 
     * Tests constructor and accessors of PolicyEntry 
     */
    public void testCtor() {
        PolicyEntry pe = new PolicyEntry(null, null, null);
        assertTrue(pe.isVoid());
        assertNull(pe.getPermissions());

        pe = new PolicyEntry(new CodeSource(null, (Certificate[])null),
            new ArrayList(), new ArrayList());
        assertTrue(pe.isVoid());
        assertNull(pe.getPermissions());

        Collection perms = Arrays.asList(new Permission[] {
            new SecurityPermission("dsfg"), new AllPermission() });
        pe = new PolicyEntry(null, null, perms);
        assertFalse(pe.isVoid());
        assertEquals(perms, new ArrayList(pe.getPermissions()));
    }

    /**
     * Null CodeSource of PolicyEntry implies any CodeSource; non-null
     * CodeSource should delegate to its own imply() functionality
     */
    public void testImpliesCodeSource() throws Exception {
        CodeSource cs1 = new CodeSource(null, (Certificate[])null);
        CodeSource cs2 = new CodeSource(new URL("file://*"),
            (Certificate[])null);
        PolicyEntry pe = new PolicyEntry(null, null, null);

        assertTrue(pe.impliesCodeSource(null));
        assertTrue(pe.impliesCodeSource(cs1));
        assertTrue(pe.impliesCodeSource(cs2));

        pe = new PolicyEntry(cs2, null, null);
        assertFalse(pe.impliesCodeSource(null));
        assertFalse(pe.impliesCodeSource(cs1));
        assertTrue(pe.impliesCodeSource(cs2));
        assertTrue(pe.impliesCodeSource(new CodeSource(new URL("file://-"),
            (Certificate[])null)));
    }

    /**
     * Null or empty set of Principals of PolicyEntry implies any Principals;
     * otherwise tested set must contain all Principals of PolicyEntry.
     */
    public void testImpliesPrincipals() {
        PolicyEntry pe = new PolicyEntry(null, null, null);
        Principal[] pp1 = new Principal[] {};
        Principal[] pp2 = new Principal[] { new UnresolvedPrincipal("a.b.c",
            "XXX") };
        Principal[] pp3 = new Principal[] {
            new UnresolvedPrincipal("a.b.c", "YYY"),
            new UnresolvedPrincipal("a.b.c", "XXX"),
            new UnresolvedPrincipal("e.f.g", "ZZZ") };

        assertTrue(pe.impliesPrincipals(null));
        assertTrue(pe.impliesPrincipals(pp1));

        pe = new PolicyEntry(null, new HashSet(), null);
        assertTrue(pe.impliesPrincipals(pp3));

        pe = new PolicyEntry(null, Arrays.asList(pp2), null);
        assertFalse(pe.impliesPrincipals(null));
        assertFalse(pe.impliesPrincipals(pp1));
        assertTrue(pe.impliesPrincipals(pp3));
    }
}
