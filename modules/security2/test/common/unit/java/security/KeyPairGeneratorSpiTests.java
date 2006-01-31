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

package java.security;


import java.security.spec.AlgorithmParameterSpec;

import junit.framework.TestCase;

/**
 * Tests for <code>KeyPairGeneratorSpi</code> class constructors and methods.
 * 
 */

public class KeyPairGeneratorSpiTests extends TestCase {

    /**
     * Constructor for KeyPairGeneratorSpiTests.
     * 
     * @param arg0
     */
    public KeyPairGeneratorSpiTests(String arg0) {
        super(arg0);
    }

    /**
     * Test for <code>KeyPairGeneratorSpi</code> constructor 
     * Assertion: constructs KeyPairGeneratorSpi
     */
    public void testKeyPairGeneratorSpi01()
            throws InvalidAlgorithmParameterException,
            InvalidParameterException {
        KeyPairGeneratorSpi keyPairGen = (KeyPairGeneratorSpi) new MyKeyPairGeneratorSpi();
        assertTrue("Not KeyPairGeneratorSpi object",
                keyPairGen instanceof KeyPairGeneratorSpi);

        AlgorithmParameterSpec pp = null;
        try {
            keyPairGen.initialize(pp, null);
            fail("UnsupportedOperationException must be thrown");
        } catch (UnsupportedOperationException e) {
        }
        keyPairGen.initialize(pp, new SecureRandom());
        keyPairGen.initialize(1024, new SecureRandom());
        try {
            keyPairGen.initialize(-1024, new SecureRandom());
            fail("IllegalArgumentException must be thrown for incorrect keysize");
        } catch (IllegalArgumentException e) {
        }
        try {
            keyPairGen.initialize(1024, null);
            fail("IllegalArgumentException must be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Incorrect exception", e.getMessage(),
                    "Invalid random");
        }
        KeyPair kp = keyPairGen.generateKeyPair();
        assertNull("Not null KeyPair", kp);
    }
    public static void main(String args[]) {
        junit.textui.TestRunner.run(KeyPairGeneratorSpiTests.class);
    }

}
