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
 * Tests AccountExpiredException class
 */

public class AccountExpiredExceptionTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(AccountExpiredExceptionTest.class);
    }

    public final void testAccountExpiredException_01() {
        AccountExpiredException tE = new AccountExpiredException();
        assertEquals (null, tE.getMessage());
        try {
            throw tE;
        }catch (Exception e){
            assertTrue(tE.equals(e));
        }

    }

    public final void testAccountExpiredException_02() {
        AccountExpiredException tE = new AccountExpiredException("message");
        assertEquals ("message", tE.getMessage());
        try {
            throw tE;
        }catch (Exception e){
            assertTrue(tE.equals(e));
        }

    }
    
    public final void testAccountExpiredException_03() {
        AccountExpiredException tE = new AccountExpiredException("message");
        try {
            throw tE;
        }catch (AccountException e){
            assertTrue(tE.equals(e));
        }
        try {
            throw tE;
        }catch (AccountExpiredException e){
            assertTrue(tE.equals(e));
        }
        try {
            throw new AccountExpiredException();
        }catch (AccountException e){
            assertEquals("javax.security.auth.login.AccountExpiredException", e
                    .getClass().getName());
        }
    }
}
