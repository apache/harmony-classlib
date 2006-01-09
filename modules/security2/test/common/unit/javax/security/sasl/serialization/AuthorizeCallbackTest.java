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
* @author Vera Y. Petrashkova
* @version $Revision$
*/

package javax.security.sasl.serialization;

import javax.security.sasl.AuthorizeCallback;
import com.openintel.drl.security.test.SerializationTest;

/**
 * Test for AuthorizeCallback seialization
 * 
 */

public class AuthorizeCallbackTest extends SerializationTest {

    public static String[] msgs = {
            "New String",
            "Long stringID. Long stringID. Long stringID. Long stringID. Long stringID. Long stringID. Long stringID. Long stringID. Long stringID. Long stringID. Long stringID. Long stringID. Long stringID. Long stringID. Long stringID. Long stringID. Long stringID." };

    protected Object[] getData() {
        String msg = null;
        return new Object[] { new AuthorizeCallback(msg, msg),
                new AuthorizeCallback("", null),
                new AuthorizeCallback(null, msgs[0]),
                new AuthorizeCallback(msgs[1], msgs[1]), };
    }

    protected void assertDeserialized(Object oref, Object otest) {
        AuthorizeCallback ref = (AuthorizeCallback) oref;
        AuthorizeCallback test = (AuthorizeCallback) otest;
        String idC = ref.getAuthenticationID();
        String idZ = ref.getAuthorizationID();
        String id = ref.getAuthorizedID();
        boolean is = ref.isAuthorized();
        if (idC == null) {
            assertNull(test.getAuthenticationID());
        } else {
            assertEquals(test.getAuthenticationID(), idC);
        }
        if (idZ == null) {
            assertNull(test.getAuthorizationID());
        } else {
            assertEquals(test.getAuthorizationID(), idZ);
        }
        if (id == null) {
            assertNull(test.getAuthorizedID());
        } else {
            assertEquals(test.getAuthorizedID(), id);
        }
        assertEquals(test.isAuthorized(), is);

    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(AuthorizeCallbackTest.class);
    }
}