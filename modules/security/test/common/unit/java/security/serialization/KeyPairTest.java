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
* @author Vladimir N. Molotkov
* @version $Revision$
*/

package java.security.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import org.apache.harmony.security.SpiEngUtils;
import org.apache.harmony.security.TestKeyPair;
import org.apache.harmony.security.test.TestUtils;

import junit.framework.TestCase;


/**
 * Tests for <code>KeyPair</code> serialization
 * 
 */
public class KeyPairTest extends TestCase {
    /**
     * KeyFactory algorithm name used during testing
     */
    private static String algorithmName;
    /**
     * Test key pairs holder
     */
    private static TestKeyPair tkp;
    /**
     * Set to true if test init is OK
     */
    private static boolean initOk = false;
    static {
        String[] tryAlg = {"DSA", "RSA"};
        for (int i=0; i<tryAlg.length; i++) {
            try {
                tkp = new TestKeyPair(tryAlg[i]);
                algorithmName = tryAlg[i];
                initOk = true;
                break;
            } catch (NoSuchAlgorithmException e) {
                // try another algorithm
            }
        }
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Constructor for KeyPairTest.
     * @param name
     * @throws NoSuchAlgorithmException
     */
    public KeyPairTest(String name) throws NoSuchAlgorithmException {
        super(name);
    }

    //
    // Tests
    //

    /**
     * Test #2 for <code>KeyPair</code> serialization/deserialization.<br>
     * File streams used.
     *
     * Assertion: deserialized object must contain the same keys as original
     * 
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public final void testSerialization02()
            throws NoSuchAlgorithmException,
                   InvalidKeySpecException,
                   IOException,
                   ClassNotFoundException {
        if (!initOk) {
            System.err.println(getName() +
                    ": no KeyFactory for DSA or RSA - test skipped");
            return;
        }
        // Create object to be compared with deserialized one
        KeyPair kp = new KeyPair(tkp.getPublic(), tkp.getPrivate());
        // This testcase uses File stream
        String fileName = SpiEngUtils.getFileName(TestUtils.TEST_ROOT,
            "java/security/serialization/KeyPair." + algorithmName + ".dat");
        deserializeAndCheck(kp, new FileInputStream(fileName));
    }

    /**
     * Test case start template - serialization
     *
     * @param kp
     * <code>KeyPair</code> object to be serialized
     * @param os
     * Serialization <code>OutputStream</code> for <code>kp</code>
     */
    private void serialize(KeyPair kp, OutputStream os)
            throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(os);
        try {
            // Serialize it to the os
            oos.writeObject(kp);
            oos.flush();
        } finally {
            oos.close();
        }
    }
    /**
     * Test case end template - deserialization and checks
     *
     * @param kp
     * reference <code>KeyPair</code> object
     * @param os
     * Deserialization <code>InputStream</code>
     */
    private void deserializeAndCheck(KeyPair kp, InputStream is)
            throws IOException,
                   ClassNotFoundException {
        // deserialize our object
        ObjectInputStream ois = new ObjectInputStream(is);
        KeyPair kp1 = null;
        try {
            kp1 = (KeyPair)ois.readObject();
        } finally {
            ois.close();
        }

        // check result: compare public keys
        Key kpKey = kp.getPublic();
        Key kp1Key = kp1.getPublic();

        assertTrue("kp key is public",
                kpKey instanceof PublicKey);
        assertTrue("kp1 key is public",
                kp1Key instanceof PublicKey);
        assertTrue("public algorithm",
                kpKey.getAlgorithm().equals(kp1Key.getAlgorithm()));
        assertTrue("public format",
                kpKey.getFormat().equals(kp1Key.getFormat()));
        assertTrue("public encoded",
                Arrays.equals(kpKey.getEncoded(), kp1Key.getEncoded()));

        // check result: compare private keys
        kpKey = kp.getPrivate();
        kp1Key = kp1.getPrivate();

        assertTrue("kp key is private",
                kpKey instanceof PrivateKey);
        assertTrue("kp1 key is private",
                kp1Key instanceof PrivateKey);
        assertTrue("private algorithm",
                kpKey.getAlgorithm().equals(kp1Key.getAlgorithm()));
        assertTrue("private format",
                kpKey.getFormat().equals(kp1Key.getFormat()));
        assertTrue("private encoded",
                Arrays.equals(kpKey.getEncoded(), kp1Key.getEncoded()));
    }
}
