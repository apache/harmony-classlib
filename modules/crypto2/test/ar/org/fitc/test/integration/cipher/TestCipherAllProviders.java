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
 * @author Hugo Beilis
 * @author Osvaldo Demo
 * @author Jorge Rafael
 * @version 1.0
 */

package ar.org.fitc.test.integration.cipher;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import ar.org.fitc.test.crypto.cipher.TestCipherDinamic;
import ar.org.fitc.test.util.TestSuiteAcumulable;
import ar.org.fitc.test.util.crypto.AllTransformation;

public class TestCipherAllProviders extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestCipherAllProviders.suite());
    }

    public static Test suite() {
        TestSuite suite = new TestSuite();
        AllTransformation t = new AllTransformation("Cipher");
        TestSuiteAcumulable suiteToRepit = new TestSuiteAcumulable(TestCipherDinamic.class);
        while(t.hasNext()) {
            suite.addTest(new CipherTestSetup(suiteToRepit, t));
        }
       return suite;
   }
}
