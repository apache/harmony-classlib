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

package javax.security.auth.login;

import junit.framework.TestCase;

/**
 * Tests CredentialNotFoundException class
 */

public class CredentialNotFoundExceptionTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(CredentialNotFoundExceptionTest.class);
    }

    public final void testCredentialNotFoundException_01() {
        CredentialNotFoundException tE = new CredentialNotFoundException();
        assertEquals (null, tE.getMessage());
        try {
            throw tE;
        }catch (Exception e){
            assertTrue(tE.equals(e));
        }

    }

    public final void testCredentialNotFoundException_02() {
        CredentialNotFoundException tE = new CredentialNotFoundException("message");
        assertEquals ("message", tE.getMessage());
        try {
            throw tE;
        }catch (Exception e){
            assertTrue(tE.equals(e));
        }

    }
    
    public final void testCredentialNotFoundException_03() {
        CredentialNotFoundException tE = new CredentialNotFoundException("message");
        try {
            throw tE;
        }catch (CredentialException e){
            assertTrue(tE.equals(e));
        }
        try {
            throw tE;
        }catch (CredentialNotFoundException e){
            assertTrue(tE.equals(e));
        }
        try {
            throw new CredentialNotFoundException();
        }catch (CredentialException e){
            assertEquals(
                    "javax.security.auth.login.CredentialNotFoundException", e
                            .getClass().getName());
        }
    }
}
