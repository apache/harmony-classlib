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

package ar.org.fitc.test.crypto.spec;

import ar.org.fitc.test.util.TestSuiteAcumulable;
import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteCryptoSpec {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestSuiteCryptoSpec.suite());
    }

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for ar.org.fitc.test.crypto.spec");
        //$JUnit-BEGIN$
        suite.addTest(new TestSuiteAcumulable(TestPBEParameterSpec.class));
        suite.addTest(new TestSuiteAcumulable(TestDESKeySpec.class));
        suite.addTest(new TestSuiteAcumulable(TestDHPublicKeySpec.class));
        suite.addTest(new TestSuiteAcumulable(TestDESedeKeySpec.class));
        suite.addTest(new TestSuiteAcumulable(TestSecretKeySpec.class));
        suite.addTest(new TestSuiteAcumulable(TestDHPrivateKeySpec.class));
        suite.addTest(new TestSuiteAcumulable(TestRC2ParameterSpec.class));
        suite.addTest(new TestSuiteAcumulable(TestDHGenParameterSpec.class));
        suite.addTest(new TestSuiteAcumulable(TestDHParameterSpec.class));
        
        suite.addTest(new TestSuiteAcumulable(TestOAEPParameterSpec.class));
        suite.addTest(new TestSuiteAcumulable(TestRC5ParameterSpec.class));
        suite.addTest(new TestSuiteAcumulable(TestIvParameterSpec.class));
        suite.addTest(new TestSuiteAcumulable(TestPSourcePSpecified.class));
        suite.addTest(new TestSuiteAcumulable(TestPBEKeySpec.class));
        //$JUnit-END$
        return suite;
    }

}
