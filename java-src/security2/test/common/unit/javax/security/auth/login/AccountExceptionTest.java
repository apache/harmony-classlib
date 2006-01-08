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

import com.openintel.drl.security.test.PerformanceTest;

/**
 * Tests AccountException class
 */

public class AccountExceptionTest extends PerformanceTest {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(AccountExceptionTest.class);
    }

    public final void testAccountException_01() {
        AccountException tE = new AccountException();
        assertEquals (null, tE.getMessage());
        try {
            throw tE;
        }catch (Exception e){
            assertTrue(tE.equals(e));
        }

    }

    public final void testAccountException_02() {
        AccountException tE = new AccountException("message");
        assertEquals ("message", tE.getMessage());
        try {
            throw tE;
        }catch (Exception e){
            assertTrue(tE.equals(e));
        }
    }

    public final void testAccountException_03() {
        try {
            throw new AccountException();
        }catch (Exception e){
            assertEquals("javax.security.auth.login.AccountException", e
                    .getClass().getName());
        }
    }
}
