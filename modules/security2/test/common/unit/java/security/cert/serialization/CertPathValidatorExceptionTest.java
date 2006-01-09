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

package java.security.cert.serialization;

import java.security.cert.CertPath;
import java.security.cert.CertPathValidatorException;
import java.security.cert.myCertPath;

import com.openintel.drl.security.test.SerializationTest;

/**
 * Test for CertPathValidatorException seialization
 * 
 */

public class CertPathValidatorExceptionTest extends SerializationTest {

    public static String[] msgs = {
            "New message",
            "Long message for Exception. Long message for Exception. Long message for Exception." };

    protected Object[] getData() {
        Exception cause = new Exception(msgs[1]);
        CertPathValidatorException dExc = new CertPathValidatorException(
                msgs[0], cause);
        String msg = null;
        Throwable th = null;
        byte[] enc = { (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4 };
        //        MyCertPath mcp = new MyCertPath(enc);
        myCertPath mcp = new myCertPath("X.509", "");
        return new Object[] { new CertPathValidatorException(),
                new CertPathValidatorException(msg),
                new CertPathValidatorException(msgs[1]),
                new CertPathValidatorException(new Throwable()),
                new CertPathValidatorException(th),
                new CertPathValidatorException(msgs[1], dExc),
                new CertPathValidatorException(msgs[1], dExc, null, -1) };
    }

    protected void assertDeserialized(Object oref, Object otest) {
        CertPathValidatorException ref = (CertPathValidatorException) oref;
        CertPathValidatorException test = (CertPathValidatorException) otest;
        String s = ref.getMessage();
        Throwable th = ref.getCause();
        if (s == null) {
            assertNull(test.getMessage());
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
        CertPath cp = ref.getCertPath();
        int ind = ref.getIndex();
        assertEquals("Incorrect index", test.getIndex(), ind);
        if (cp == null) {
            assertNull("getCertPath() must return null", test.getCertPath());
        } else {
            CertPath res = test.getCertPath();
            assertEquals("Incorrect CertPath", res.getClass(), cp.getClass());
        }
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(CertPathValidatorExceptionTest.class);
    }
}