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

package javax.crypto;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.DSAParameterSpec;

import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.spec.DHParameterSpec;

import org.apache.harmony.security.SpiEngUtils;
import org.apache.harmony.security.TestKeyPair;

import com.openintel.drl.security.test.PerformanceTest;

/**
 * Tests for KeyAgreement constructor and methods
 * 
 */

public class KeyAgreementTest1 extends PerformanceTest {

    /**
     * Constructor for KeyAgreementTest1.
     * 
     * @param arg0
     */
    public KeyAgreementTest1(String arg0) {
        super(arg0);
    }

    public static final String srvKeyAgreement = "KeyAgreement";

    private static String defaultAlgorithm = "DH";

    private static String defaultProviderName = null;

    private static Provider defaultProvider = null;

    private static boolean DEFSupported = false;

    private static final String NotSupportMsg = "There is no suitable provider for KeyAgreement";

    private static final String[] invalidValues = SpiEngUtils.invalidValues;

    private static String[] validValues = { "DH", "dH",
            "Dh", "dh" };

    private static PrivateKey privKey = null;

    private static PublicKey publKey = null;

    private static boolean initKeys = false;

    static {
        defaultProvider = SpiEngUtils.isSupport(defaultAlgorithm,
                srvKeyAgreement);
        DEFSupported = (defaultProvider != null);
        defaultProviderName = (DEFSupported ? defaultProvider.getName() : null);
    }

    private boolean createKeys() {
        if (initKeys) {
            return true;
        }
        try {
            TestKeyPair tkp = new TestKeyPair(defaultAlgorithm);
            privKey = tkp.getPrivate();
            publKey = tkp.getPublic();
            initKeys = true;
            return true;
        } catch (Exception e) {
            logln("Unexpected: " + e.toString());
            initKeys = false;
            return false;
        }

    }

    private KeyAgreement[] createKAs() {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return null;
        }
        try {
            KeyAgreement[] ka = new KeyAgreement[3];
            ka[0] = KeyAgreement.getInstance(defaultAlgorithm);
            ka[1] = KeyAgreement.getInstance(defaultAlgorithm, defaultProvider);
            ka[2] = KeyAgreement.getInstance(defaultAlgorithm,
                    defaultProviderName);
            return ka;
        } catch (Exception e) {
            logln(e.toString());
            return null;
        }
    }

    public static String getDefAlg() {
        return defaultAlgorithm;
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
        assertTrue("Not KeyAgreement object", keyA instanceof KeyAgreement);
        assertEquals("Incorrect algorithm", keyA.getAlgorithm(),
                defaultAlgorithm);
        assertEquals("Incorrect provider", keyA.getProvider(), defaultProvider);
        assertNull("Incorrect result", keyA.doPhase(null, true));
        assertEquals("Incorrect result", keyA.generateSecret().length, 0);

        keyA = new myKeyAgreement(null, null, null);
        assertTrue("Not KeyAgreement object", keyA instanceof KeyAgreement);
        assertNull("Algorithm must be null", keyA.getAlgorithm());
        assertNull("Provider must be null", keyA.getProvider());
        try {
            keyA.doPhase(null, true);
            fail("NullPointerEXception must be thrown");
        } catch (NullPointerException e) {
        }
    }

    /**
     * Test for <code> getInstance(String algorithm) </code> method Assertions:
     * throws NullPointerException when algorithm is null throws
     * NoSuchAlgorithmException when algorithm isnot available
     */
    public void testGetInstanceString01() throws NoSuchAlgorithmException {
        try {
            KeyAgreement.getInstance(null);
            fail("NullPointerException or NoSuchAlgorithmException should be thrown if algorithm is null");
        } catch (NullPointerException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                KeyAgreement.getInstance(invalidValues[i]);
                fail("NoSuchAlgorithmException must be thrown");
            } catch (NoSuchAlgorithmException e) {
            }
        }
    }

    /**
     * Test for <code> getInstance(String algorithm) </code> method Assertions:
     * returns KeyAgreement object
     */
    public void testGetInstanceString02() throws NoSuchAlgorithmException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        KeyAgreement keyA;
        for (int i = 0; i < validValues.length; i++) {
            keyA = KeyAgreement.getInstance(validValues[i]);
            assertTrue("Not KeyAgreement object", keyA instanceof KeyAgreement);
            assertEquals("Incorrect algorithm", keyA.getAlgorithm(),
                    validValues[i]);
        }
    }

    /**
     * Test for <code> getInstance(String algorithm, String provider)</code>
     * method Assertions: throws NullPointerException when algorithm is null
     * throws NoSuchAlgorithmException when algorithm is not available
     */
    public void testGetInstanceStringString01()
            throws NoSuchAlgorithmException, IllegalArgumentException,
            NoSuchProviderException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        try {
            KeyAgreement.getInstance(null, defaultProviderName);
            fail("NullPointerException or NoSuchAlgorithmException should be thrown if algorithm is null");
        } catch (NullPointerException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                KeyAgreement.getInstance(invalidValues[i], defaultProviderName);
                fail("NoSuchAlgorithmException must be thrown");
            } catch (NoSuchAlgorithmException e) {
            }
        }
    }

    /**
     * Test for <code> getInstance(String algorithm, String provider)</code>
     * method Assertions: throws IllegalArgumentException when provider is null
     * or empty throws NoSuchProviderException when provider has not be
     * configured
     */
    public void testGetInstanceStringString02()
            throws IllegalArgumentException, NoSuchAlgorithmException,
            NoSuchProviderException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        String provider = null;
        for (int i = 0; i < validValues.length; i++) {
            try {
                KeyAgreement.getInstance(validValues[i], provider);
                fail("IllegalArgumentException must be thrown when provider is null");
            } catch (IllegalArgumentException e) {
            }
            try {
                KeyAgreement.getInstance(validValues[i], "");
                fail("IllegalArgumentException must be thrown when provider is empty");
            } catch (IllegalArgumentException e) {
            }
            for (int j = 1; j < invalidValues.length; j++) {
                try {
                    KeyAgreement.getInstance(validValues[i], invalidValues[j]);
                    fail("NoSuchProviderException must be thrown (algorithm: "
                            .concat(validValues[i]).concat(" provider: ")
                            .concat(invalidValues[j]).concat(")"));
                } catch (NoSuchProviderException e) {
                }
            }
        }
    }

    /**
     * Test for <code> getInstance(String algorithm, String provider)</code>
     * method Assertions: returns KeyAgreement object
     */
    public void testGetInstanceStringString03()
            throws IllegalArgumentException, NoSuchAlgorithmException,
            NoSuchProviderException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        KeyAgreement keyA;
        for (int i = 0; i < validValues.length; i++) {
            keyA = KeyAgreement
                    .getInstance(validValues[i], defaultProviderName);
            assertTrue("Not KeyAgreement object", keyA instanceof KeyAgreement);
            assertEquals("Incorrect algorithm", keyA.getAlgorithm(),
                    validValues[i]);
            assertEquals("Incorrect provider", keyA.getProvider().getName(),
                    defaultProviderName);
        }
    }

    /**
     * Test for <code> getInstance(String algorithm, Provider provider)</code>
     * method Assertions: throws NullPointerException when algorithm is null
     * throws NoSuchAlgorithmException when algorithm isnot available
     */
    public void testGetInstanceStringProvider01()
            throws NoSuchAlgorithmException, IllegalArgumentException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        try {
            KeyAgreement.getInstance(null, defaultProvider);
            fail("NullPointerException or NoSuchAlgorithmException should be thrown if algorithm is null");
        } catch (NullPointerException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                KeyAgreement.getInstance(invalidValues[i], defaultProvider);
                fail("NoSuchAlgorithmException must be thrown");
            } catch (NoSuchAlgorithmException e) {
            }
        }
    }

    /**
     * Test for <code> getInstance(String algorithm, Provider provider)</code>
     * method Assertions: throws IllegalArgumentException when provider is null
     */
    public void testGetInstanceStringProvider02()
            throws NoSuchAlgorithmException, IllegalArgumentException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        Provider provider = null;
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                KeyAgreement.getInstance(invalidValues[i], provider);
                fail("IllegalArgumentException must be thrown");
            } catch (IllegalArgumentException e) {
            }
        }
    }

    /**
     * Test for <code> getInstance(String algorithm, Provider provider)</code>
     * method Assertions: returns KeyAgreement object
     */
    public void testGetInstanceStringProvider03()
            throws IllegalArgumentException, NoSuchAlgorithmException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        KeyAgreement keyA;
        for (int i = 0; i < validValues.length; i++) {
            keyA = KeyAgreement.getInstance(validValues[i], defaultProvider);
            assertTrue("Not KeyAgreement object", keyA instanceof KeyAgreement);
            assertEquals("Incorrect algorithm", keyA.getAlgorithm(),
                    validValues[i]);
            assertEquals("Incorrect provider", keyA.getProvider(),
                    defaultProvider);
        }
    }

    /**
     * Test for the methods: <code>init(Key key)</code>
     * <code>generateSecret()</code> 
     * <code>generateSecret(byte[] sharedsecret, int offset)</code>
     * <code>generateSecret(String algorithm)</code>
     * Assertions: initializes KeyAgreement; returns sharedSecret; puts
     * sharedsecret in buffer and return numbers of bytes; returns SecretKey
     * object
     */
    public void testGenerateSecret03() throws ShortBufferException,
            InvalidKeyException, NoSuchAlgorithmException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        assertTrue("Keys were not created", createKeys());
        KeyAgreement[] kAgs = createKAs();
        assertNotNull("KeyAgreement objects were not created", kAgs);
        byte[] bb;
        byte[] bb1 = new byte[10];
        int t;
        for (int i = 0; i < kAgs.length; i++) {
            kAgs[i].init(privKey);
            kAgs[i].doPhase(publKey, true);
            bb = kAgs[i].generateSecret();
            kAgs[i].init(privKey);
            kAgs[i].doPhase(publKey, true);
            bb1 = new byte[bb.length + 10];
            t = kAgs[i].generateSecret(bb1, 9);
            kAgs[i].init(privKey);
            kAgs[i].doPhase(publKey, true);
            assertTrue("Not SecretKey object",
                    kAgs[i].generateSecret("DES") instanceof SecretKey);
        }
    }

    /**
     * Test for <code>doPhase(Key key, boolean lastPhase)</code> method
     * Assertion: throws InvalidKeyException if key is not appropriate
     */
    public void testDoPhase() throws ShortBufferException, InvalidKeyException,
            NoSuchAlgorithmException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        assertTrue("Keys were not created", createKeys());
        KeyAgreement[] kAgs = createKAs();
        assertNotNull("KeyAgreement objects were not created", kAgs);
        for (int i = 0; i < kAgs.length; i++) {
            kAgs[i].init(privKey);
            try {
                kAgs[i].doPhase(privKey, false);
                fail("InvalidKeyException must be throw");
            } catch (InvalidKeyException e) {
            }

            try {
                kAgs[i].doPhase(privKey, true);
                fail("InvalidKeyException must be throw");
            } catch (InvalidKeyException e) {
            }
        }
    }

    /**
     * Test for the methods <code>init(Key key)</code>
     * <code>init(Key key, SecureRandom random)</code>
     * <code>init(Key key, AlgorithmParameterSpec params)</code>
     * <code>init(Key key, AlgorithmParameterSpec params, SecureRandom random)</code>
     * Assertion: throws InvalidKeyException when key is inapporopriate
     */
    public void testInit01() throws InvalidAlgorithmParameterException,
            InvalidKeyException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        assertTrue("Keys were not created", createKeys());
        KeyAgreement[] kAgs = createKAs();
        assertNotNull("KeyAgreement objects were not created", kAgs);
        SecureRandom random = null;
        AlgorithmParameterSpec aps = null;
        DHParameterSpec dhPs = new DHParameterSpec(new BigInteger("56"),
                new BigInteger("56"));
        for (int i = 0; i < kAgs.length; i++) {
            try {
                kAgs[i].init(publKey);
                fail("InvalidKeyException must be throw");
            } catch (InvalidKeyException e) {
            }
            try {
                kAgs[i].init(publKey, new SecureRandom());
                fail("InvalidKeyException must be throw");
            } catch (InvalidKeyException e) {
            }
            try {
                kAgs[i].init(publKey, random);
                fail("InvalidKeyException must be throw");
            } catch (InvalidKeyException e) {
            }
            try {
                kAgs[i].init(publKey, dhPs);
                fail("InvalidKeyException must be throw");
            } catch (InvalidKeyException e) {
            }
            try {
                kAgs[i].init(publKey, aps);
                fail("InvalidKeyException must be throw");
            } catch (InvalidKeyException e) {
            }
            try {
                kAgs[i].init(publKey, dhPs, new SecureRandom());
                fail("InvalidKeyException must be throw");
            } catch (InvalidKeyException e) {
            }
        }
    }

    /**
     * Test for the methods
     * <code>init(Key key, AlgorithmParameterSpec params)</code>
     * <code>init(Key key, AlgorithmParameterSpec params, SecureRandom random)</code>
     * Assertion: throws AlgorithmParameterException when params are
     * inapporopriate
     */
    public void testInit02() throws InvalidKeyException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        assertTrue("Keys were not created", createKeys());
        KeyAgreement[] kAgs = createKAs();
        assertNotNull("KeyAgreement objects were not created", kAgs);
        SecureRandom random = null;
        AlgorithmParameterSpec aps = null;
        DSAParameterSpec dsa = new DSAParameterSpec(new BigInteger("56"),
                new BigInteger("56"), new BigInteger("56"));
        for (int i = 0; i < kAgs.length; i++) {
            try {
                kAgs[i].init(privKey, dsa);
                fail("InvalidAlgorithmParameterException or InvalidKeyException must be throw");
            } catch (InvalidAlgorithmParameterException e) {
            } catch (InvalidKeyException e) {
            }
            try {
                kAgs[i].init(privKey, dsa, new SecureRandom());
                fail("InvalidAlgorithmParameterException or InvalidKeyException must be throw");
            } catch (InvalidAlgorithmParameterException e) {
            } catch (InvalidKeyException e) {
            }
            try {
                kAgs[i].init(privKey, dsa, random);
                fail("InvalidAlgorithmParameterException must be throw");
                fail("InvalidAlgorithmParameterException or InvalidKeyException must be throw");
            } catch (InvalidAlgorithmParameterException e) {
            } catch (InvalidKeyException e) {
            }
        }
    }

    /**
     * Test for the methods: <code>init(Key key)</code>
     * <code>init(Key key, SecureRandom random)</code>
     * <code>generateSecret()</code>
     * Assertions: initializes KeyAgreement and returns byte array
     */
    public void testInit03() throws InvalidKeyException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        assertTrue("Keys were not created", createKeys());
        KeyAgreement[] kAgs = createKAs();
        assertNotNull("KeyAgreement objects were not created", kAgs);
        byte[] bbRes1;
        byte[] bbRes2;
        byte[] bbRes3;
        byte[] bb1 = new byte[10];
        int t;
        SecureRandom randomNull = null;
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < kAgs.length; i++) {
            kAgs[i].init(privKey);
            kAgs[i].doPhase(publKey, true);
            bbRes1 = kAgs[i].generateSecret();
            kAgs[i].init(privKey, random);
            kAgs[i].doPhase(publKey, true);
            bbRes2 = kAgs[i].generateSecret();
            assertEquals("Incorrect byte array length", bbRes1.length,
                    bbRes2.length);
            for (int j = 0; j < bbRes1.length; j++) {
                assertEquals("Incorrect byte (index: ".concat(
                        Integer.toString(i)).concat(")"), bbRes1[j], bbRes2[j]);
            }
            kAgs[i].init(privKey, randomNull);
            kAgs[i].doPhase(publKey, true);
            bbRes3 = kAgs[i].generateSecret();
            assertEquals("Incorrect byte array length", bbRes1.length,
                    bbRes3.length);
            for (int j = 0; j < bbRes1.length; j++) {
                assertEquals("Incorrect byte (index: ".concat(
                        Integer.toString(i)).concat(")"), bbRes1[j], bbRes3[j]);
            }
        }
    }

    /**
     * Test for the methods:
     * <code>init(Key key, AlgorithmParameterSpec params)</code>
     * <code>init(Key key, AlgorithmParameterSpec params, SecureRandom random)</code>
     * <code>generateSecret()</code>
     * Assertions: initializes KeyAgreement and returns byte array
     */
    public void testInit04() throws InvalidKeyException,
            InvalidAlgorithmParameterException {
        if (!DEFSupported) {
            fail(NotSupportMsg);
            return;
        }
        assertTrue("Keys were not created", createKeys());
        KeyAgreement[] kAgs = createKAs();
        assertNotNull("KeyAgreement objects were not created", kAgs);
        DHParameterSpec dhPs = ((DHPrivateKey) privKey).getParams();

        byte[] bbRes1;
        byte[] bbRes2;
        byte[] bbRes3;
        byte[] bb1 = new byte[10];
        int t;
        SecureRandom randomNull = null;
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < kAgs.length; i++) {
            kAgs[i].init(privKey, dhPs);
            kAgs[i].doPhase(publKey, true);
            bbRes1 = kAgs[i].generateSecret();
            kAgs[i].init(privKey, dhPs, random);
            kAgs[i].doPhase(publKey, true);
            bbRes2 = kAgs[i].generateSecret();
            assertEquals("Incorrect byte array length", bbRes1.length,
                    bbRes2.length);
            for (int j = 0; j < bbRes1.length; j++) {
                assertEquals("Incorrect byte (index: ".concat(
                        Integer.toString(i)).concat(")"), bbRes1[j], bbRes2[j]);
            }
            kAgs[i].init(privKey, dhPs, randomNull);
            kAgs[i].doPhase(publKey, true);
            bbRes3 = kAgs[i].generateSecret();
            assertEquals("Incorrect byte array length", bbRes1.length,
                    bbRes3.length);
            for (int j = 0; j < bbRes1.length; j++) {
                assertEquals("Incorrect byte (index: ".concat(
                        Integer.toString(i)).concat(")"), bbRes1[j], bbRes3[j]);
            }
        }
    }

    public static void main(String args[]) {
        junit.textui.TestRunner.run(KeyAgreementTest1.class);
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