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

package org.apache.harmony.crypto.tests.javax.crypto;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;

import javax.crypto.KeyAgreement;
import javax.crypto.KeyAgreementSpi;

import org.apache.harmony.crypto.tests.support.MyKeyAgreementSpi;
import org.apache.harmony.security.SpiEngUtils;

import junit.framework.TestCase;

/**
 * Tests for KeyAgreement constructor and methods
 * 
 */

public class KeyAgreement_Impl1Test extends TestCase {

    public static final String srvKeyAgreement = "KeyAgreement";

    private static String defaultAlgorithm = "DH";


    private static Provider defaultProvider = null;

    private static boolean DEFSupported = false;

    private static final String NotSupportMsg = "There is no suitable provider for KeyAgreement";


    static {
        defaultProvider = SpiEngUtils.isSupport(defaultAlgorithm,
                srvKeyAgreement);
        DEFSupported = (defaultProvider != null);
    }

    /**
     * Test for <code>KeyAgreement</code> constructor Assertion: returns
     * KeyAgreement object
     */
    public void testKeyAgreement() throws NoSuchAlgorithmException,
            InvalidKeyException, IllegalStateException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        KeyAgreementSpi spi = new MyKeyAgreementSpi();
        KeyAgreement keyA = new myKeyAgreement(spi, defaultProvider,
                defaultAlgorithm);
        assertEquals("Incorrect algorithm", keyA.getAlgorithm(),
                defaultAlgorithm);
        assertEquals("Incorrect provider", keyA.getProvider(), defaultProvider);
        assertNull("Incorrect result", keyA.doPhase(null, true));
        assertEquals("Incorrect result", keyA.generateSecret().length, 0);

        keyA = new myKeyAgreement(null, null, null);
        assertNull("Algorithm must be null", keyA.getAlgorithm());
        assertNull("Provider must be null", keyA.getProvider());
        try {
            keyA.doPhase(null, true);
            fail("NullPointerEXception must be thrown");
        } catch (NullPointerException e) {
        }
    }
}

/**
 * Additional class for KeyAgreement constructor verification
 */

class myKeyAgreement extends KeyAgreement {

    public myKeyAgreement(KeyAgreementSpi keyAgreeSpi, Provider provider,
            String algorithm) {
        super(keyAgreeSpi, provider, algorithm);
    }
}
