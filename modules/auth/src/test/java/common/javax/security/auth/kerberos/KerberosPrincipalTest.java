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

package javax.security.auth.kerberos;

import junit.framework.TestCase;

/**
 * Tests KerberosPrincipal class implementation.
 */
public class KerberosPrincipalTest extends TestCase {

    /**
     * @tests javax.security.auth.kerberos.KerberosPrincipal#KerberosPrincipal(
     *        java.lang.String)
     */
    public void test_Ctor1() {

        // null value is invalid
        try {
            new KerberosPrincipal(null);
            fail("No expected IllegalArgumentException for null");
        } catch (IllegalArgumentException e) {
        }

        // testing illegal kerberos principal names
        String[] illegalNames = new String[] { "bbb@a:a.com", // ':' char
                "bbb@a/a.com", // '/' char
                "bbb@a\0a.com",// null char
                "@/" // Regression for HARMONY-770
        };
        for (String illegalName : illegalNames) {
            try {
                new KerberosPrincipal(illegalName);

                fail("No expected IllegalArgumentException for: " + illegalName);
            } catch (IllegalArgumentException e) {
            }
        }

        // valid values
        KerberosPrincipal principal = new KerberosPrincipal("name@apache.org");

        assertEquals("name@apache.org", principal.getName());
        assertEquals("apache.org", principal.getRealm());
        assertEquals(KerberosPrincipal.KRB_NT_PRINCIPAL, principal
                .getNameType());

    }

    /**
     * @tests javax.security.auth.kerberos.KerberosPrincipal#KerberosPrincipal(
     *        java.lang.String, int)
     */
    public void test_Ctor2() {

        // null value is invalid
        try {
            new KerberosPrincipal(null, KerberosPrincipal.KRB_NT_UNKNOWN);
            fail("No expected IllegalArgumentException for null");
        } catch (IllegalArgumentException e) {
        }

        // '-1' nameType value is invalid
        try {
            new KerberosPrincipal("name@apache.org", -1);
            fail("No expected IllegalArgumentException for -1 nameType value");
        } catch (IllegalArgumentException e) {
        }

        // '6' nameType value is invalid
        try {
            new KerberosPrincipal("name@apache.org", 6);
            fail("No expected IllegalArgumentException 6 nameType value");
        } catch (IllegalArgumentException e) {
        }

        // valid values
        KerberosPrincipal principal = new KerberosPrincipal("name@apache.org",
                KerberosPrincipal.KRB_NT_UNKNOWN);

        assertEquals("name@apache.org", principal.getName());
        assertEquals("apache.org", principal.getRealm());
        assertEquals(KerberosPrincipal.KRB_NT_UNKNOWN, principal.getNameType());
    }

    /**
     * @tests javax.security.auth.kerberos.KerberosPrincipal#hashCode()
     */
    public void test_hashCode() {
        KerberosPrincipal principal = new KerberosPrincipal("name@apache.org");

        assertEquals(principal.getName().hashCode(), principal.hashCode());
    }

    /**
     * @tests javax.security.auth.kerberos.KerberosPrincipal#toString()
     */
    public void test_toString() {
        // FIXME
        // KerberosPrincipal principal = new
        // KerberosPrincipal("name@apache.org");
        //
        // assertEquals("javax.security.auth.kerberos.KerberosPrincipal@"
        // + Integer.toHexString(principal.hashCode()), principal
        // .toString());
    }

    /**
     * @tests javax.security.auth.kerberos.KerberosPrincipal#equals(Object)
     */
    public void test_equals() {
        // Regression for HARMONY-744
        assertFalse(new KerberosPrincipal("A@B").equals(null));
        assertFalse(new KerberosPrincipal("A@B").equals(new Object()));
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(KerberosPrincipalTest.class);
    }
}
