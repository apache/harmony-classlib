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

package javax.net.ssl.serialization;

import javax.net.ssl.SSLException;

import org.apache.harmony.security.test.SerializationTest;


/**
 * Test for SSLException serialization
 * 
 */

public class SSLExceptionTest extends SerializationTest {

    public static String[] msgs = {
            "New message",
            "Long message for Exception. Long message for Exception. Long message for Exception." };

    protected Object[] getData() {
        String msg = null;
        Exception cause = new Exception(msgs[1]);
        SSLException excSSL = new SSLException(msgs[0]);
        return new Object[] { new SSLException(msg), new SSLException(msgs[1]),
                new SSLException(excSSL), new SSLException(cause),
                new SSLException(msgs[1], cause) };
    }

    protected void assertDeserialized(Object oref, Object otest) {
        SSLException ref = (SSLException) oref;
        SSLException test = (SSLException) otest;
        Throwable th = ref.getCause();
        String s = ref.getMessage();
        String rS = test.getMessage();
        if (s == null) {
            assertNull("Not null result message", rS);
        } else {
            assertEquals(test.getMessage(), s);
        }
        if (th == null) {
            assertNull(test.getCause());
        } else {
            Throwable th1 = test.getCause();
            assertEquals(th1.getClass(), th.getClass());
            String s1 = th.getMessage();
            if (s1 == null) {
                assertNull(th1.getMessage());
            } else {
                assertEquals(th1.getMessage(), s1);
            }
        }
    }
}