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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTestsCryptoSpec extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(AllTestsCryptoSpec.suite());
    }

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for ar.org.fitc.test.crypto.spec");
        //$JUnit-BEGIN$
        suite.addTestSuite(TestPBEParameterSpec.class);
        suite.addTestSuite(TestDESKeySpec.class);
        suite.addTestSuite(TestDHPublicKeySpec.class);
        suite.addTestSuite(TestDESedeKeySpec.class);
        suite.addTestSuite(TestSecretKeySpec.class);
        suite.addTestSuite(TestDHPrivateKeySpec.class);
        suite.addTestSuite(TestRC2ParameterSpec.class);
        suite.addTestSuite(TestDHGenParameterSpec.class);
        suite.addTestSuite(TestDHParameterSpec.class);
        
        suite.addTestSuite(TestOAEPParameterSpec.class);
        suite.addTestSuite(TestRC5ParameterSpec.class);
        suite.addTestSuite(TestIvParameterSpec.class);
        suite.addTestSuite(TestPSourcePSpecified.class);
        suite.addTestSuite(TestPBEKeySpec.class);
        //$JUnit-END$
        return suite;
    }

}
