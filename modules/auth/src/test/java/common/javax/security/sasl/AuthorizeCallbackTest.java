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
* @author Vera Y. Petrashkova
* @version $Revision$
*/

package javax.security.sasl;

import junit.framework.TestCase;

/**
 * Tests for constructor and methods of AuthorizeCallback class
 * 
 */

public class AuthorizeCallbackTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(AuthorizeCallbackTest.class);
    }

    /**
     * Test for <code>AuthorizeCallback(String authnID, String authzID)</code>
     * and get/set methods
     */
    public void test01() {
        AuthorizeCallback auth = new AuthorizeCallback(null, null);
        assertNull(auth.getAuthenticationID());
        assertNull(auth.getAuthorizationID());
        assertNull(auth.getAuthorizedID());
        assertFalse(auth.isAuthorized());

        auth.setAuthorized(true);
        assertTrue(auth.isAuthorized());
        assertNull(auth.getAuthorizedID());

        auth.setAuthorized(false);
        assertNull(auth.getAuthorizedID());
        assertFalse(auth.isAuthorized());

        auth.setAuthorizedID("ZZZ");
        auth.setAuthorized(true);
        assertEquals(auth.getAuthorizedID(), "ZZZ");
        assertNull(auth.getAuthorizationID());
        assertTrue(auth.isAuthorized());
    }

    /**
     * Test for <code>AuthorizeCallback(String authnID, String authzID)</code>
     * and get/set methods
     */
    public void test02() {
        String[] authenticationIDs = {
                "",
                "authenticationIDs",
                "Long String LongString Long String LongString Long String LongString Long String LongString Long String LongString Long String LongString" };
        String[] authorizedIDs = {
                "",
                "authorizedIDs",
                "Long String LongString Long String LongString Long String LongString Long String LongString Long String LongString Long String LongString" };
        String[] newAuthorizedIDs = {
                "new authorizedIDs",
                "another authorizedIDs",
                "some long string for authorized IDs some long string for authorized IDs some long string for authorized IDs" };
        AuthorizeCallback auth;
        for (String element : authenticationIDs) {
            for (String element0 : authorizedIDs) {
                auth = new AuthorizeCallback(element,
                        element0);
                assertEquals(auth.getAuthenticationID(), element);
                assertEquals(auth.getAuthorizationID(), element0);
                assertNull(auth.getAuthorizedID());
                assertFalse(auth.isAuthorized());

                auth.setAuthorized(true);
                assertTrue(auth.isAuthorized());
                assertEquals(auth.getAuthorizedID(), auth.getAuthorizationID());

                auth.setAuthorized(false);
                assertNull(auth.getAuthorizedID());
                assertFalse(auth.isAuthorized());

                for (String element1 : newAuthorizedIDs) {
                    auth.setAuthorizedID(element1);
                    assertNull(auth.getAuthorizedID());
                    auth.setAuthorized(true);
                    assertFalse(auth.getAuthorizedID().equals(
                            auth.getAuthorizationID()));
                    assertEquals(auth.getAuthorizedID(), element1);
                    auth.setAuthorizedID(element1 + " ZZZ");
                    assertFalse(auth.getAuthorizedID().equals(
                            auth.getAuthorizationID()));
                    assertFalse(auth.getAuthorizedID().equals(
                            element1));
                    assertEquals(auth.getAuthorizedID(), element1
                            + " ZZZ");

                    auth.setAuthorized(false);
                }

            }
        }
    }
}
