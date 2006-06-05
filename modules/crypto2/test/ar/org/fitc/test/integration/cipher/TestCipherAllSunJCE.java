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
import ar.org.fitc.test.util.crypto.AllTransformation;

public class TestCipherAllSunJCE extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestCipherAllBouncyCastle.suite());
    }

    /**
     * This test should run with a java heap space greater than the default
     *
     */

    public static Test suite() {
        TestSuite suite = new TestSuite("CipherAllTests");
        AllTransformation t = new AllTransformation("Cipher","SunJCE");
        // BlockTransformation t = new BlockTransformation("Cipher");
        while (t.hasNext()) {
            // System.out.println("Algoritmo: "+t.getAlgorithm());
            // System.out.println("Modo: "+t.getMode());
            // System.out.println("Relleno: "+t.getPadding());
            // System.out.println("Proveedor: "+t.getProvider());
            // System.out.println("Clave:
            // "+Arrays.toString(t.getKeyObject().getEncoded()));
            suite.addTest(new CipherTestSetup(new TestSuite(
                    TestCipherDinamic.class), t));
        }
        return suite;
    }
}
