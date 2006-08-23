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
* @author Maxim V. Makarov
* @version $Revision$
*/

package org.apache.harmony.auth.tests.javax.security.auth.login;

import javax.security.auth.login.CredentialException;
import javax.security.auth.login.CredentialExpiredException;

import junit.framework.TestCase;

/**
 * Tests CredentialExpiredException class
 */

public class CredentialExpiredExceptionTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(CredentialExpiredException.class);
    }

    public final void testCredentialExpiredException_01() {
        CredentialExpiredException tE = new CredentialExpiredException();
        assertEquals (null, tE.getMessage());
        try {
            throw tE;
        }catch (Exception e){
            assertTrue(tE.equals(e));
        }

    }

    public final void testCredentialExpiredException_02() {
        CredentialExpiredException tE = new CredentialExpiredException("message");
        assertEquals ("message", tE.getMessage());
        try {
            throw tE;
        }catch (Exception e){
            assertTrue(tE.equals(e));
        }

    }
    
    public final void testCredentialExpiredException_03() {
        CredentialExpiredException tE = new CredentialExpiredException("message");
        try {
            throw tE;
        }catch (CredentialException e){
              assertTrue(tE.equals(e));
        }
        try {
            throw tE;
        }catch (CredentialExpiredException e){
              assertTrue(tE.equals(e));
        }
        try {
            throw new CredentialExpiredException();
        }catch (CredentialException e){
            assertEquals(
                    "javax.security.auth.login.CredentialExpiredException", e
                            .getClass().getName());
        }
    }
}
