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
* @author Alexander V. Astapchuk
* @version $Revision$
*/

package java.security.serialization;

import java.security.CodeSigner;
import java.security.Timestamp;
import java.security.cert.CertPath;
import java.util.Date;

import org.apache.harmony.security.support.SerializationTest;
import org.apache.harmony.security.support.TestCertUtils;


/**
 * Serialization tests for <code>CodeSigner</code>
 * 
 */

public class CodeSignerTest extends SerializationTest {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(CodeSignerTest.class);
    }

    /**
     * @see com.intel.drl.test.SerializationTest#getData()
     */
    protected Object[] getData() {
        CertPath cpath = TestCertUtils.getCertPath();
        Timestamp ts = new Timestamp(new Date(), cpath);
        return new Object[] { new CodeSigner(cpath, ts),
                new CodeSigner(cpath, null) };
    }

    protected void assertDeserialized(Object oref, Object otest) {
        CodeSigner ref = (CodeSigner) oref;
        CodeSigner test = (CodeSigner) otest;
        assertEquals(ref.getSignerCertPath(), test.getSignerCertPath());
        /*
         * this does not work - assertEquals( ref.getTimestamp(),
         * test.getTimestamp()); as the ref always have the current date/time,
         * and the test was stored some time ago, so the dates never match.
         */
    }

}