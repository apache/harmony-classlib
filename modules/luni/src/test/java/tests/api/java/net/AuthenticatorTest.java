/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tests.api.java.net;

import java.net.Authenticator;
import java.net.Authenticator.RequestorType;

import junit.framework.TestCase;

public class AuthenticatorTest extends TestCase {

    /**
     * @tests java.net.Authenticator.RequestorType#valueOf(String)
     */
    public void test_RequestorType_valueOfLjava_lang_String() throws Exception {
        assertEquals(RequestorType.PROXY, Authenticator.RequestorType
                .valueOf("PROXY"));
        assertEquals(RequestorType.SERVER, Authenticator.RequestorType
                .valueOf("SERVER"));
        try {
            RequestorType rt = Authenticator.RequestorType.valueOf("BADNAME");
            fail("Must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // correct
        }
        // Some old RIs,which throw IllegalArgumentException, will fail 
		// this test case. Latest RIs throw NullPointerException.
        try {
            RequestorType rt = Authenticator.RequestorType.valueOf(null);
            fail("Must throw NullPointerException");
        } catch (NullPointerException e) {
            // correct
        }
    }

    /**
     * @tests java.net.Authenticator.RequestorType#values()
     */
    public void test_RequestorType_values() throws Exception {        
        RequestorType[] rt = RequestorType.values();
        assertEquals(RequestorType.PROXY, rt[0]);
        assertEquals(RequestorType.SERVER, rt[1]);
    }
}
