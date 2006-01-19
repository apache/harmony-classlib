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

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.apache.harmony.security.SpiEngUtils;

import com.openintel.drl.security.test.PerformanceTest;

/**
 * Tests for KeyPairGenerator class
 * 
 */

public class KeyPairGeneratorTest3 extends PerformanceTest {

    /**
     * Constructor for KeyPairGeneratorTest3.
     * 
     * @param arg0
     */
    public KeyPairGeneratorTest3(String arg0) {
        super(arg0);
    }

    private static String validProviderName = null;

    public static Provider validProvider = null;;

    private static boolean DSASupported = false;
    
    private static String NotSupportMsg = KeyPairGeneratorTest1.NotSupportMsg;

    static {
        validProvider = SpiEngUtils.isSupport(
                KeyPairGeneratorTest1.validAlgName,
                KeyPairGeneratorTest1.srvKeyPairGenerator);
        DSASupported = (validProvider != null);
        validProviderName = (DSASupported ? validProvider.getName() : null);
    }

    protected KeyPairGenerator[] createKPGen() {
        if (!DSASupported) {
            fail(KeyPairGeneratorTest1.validAlgName
                    + " algorithm is not supported");
            return null;
        }
        KeyPairGenerator[] kpg = new KeyPairGenerator[3];
        try {
            kpg[0] = KeyPairGenerator
                    .getInstance(KeyPairGeneratorTest1.validAlgName);
            kpg[1] = KeyPairGenerator.getInstance(
                    KeyPairGeneratorTest1.validAlgName, validProvider);
            kpg[2] = KeyPairGenerator.getInstance(
                    KeyPairGeneratorTest1.validAlgName, validProviderName);
            return kpg;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
    /**
     * Test for <code>generateKeyPair()</code> and <code>genKeyPair()</code>
     * methods
     * Assertion: KeyPairGenerator was initialized before the invocation 
     * of these methods
     */
    public void testGenKeyPair01() throws NoSuchAlgorithmException,
            NoSuchProviderException, IllegalArgumentException {
        if (!DSASupported) {
            fail(NotSupportMsg);
            return;
        }
        KeyPairGenerator[] kpg = createKPGen();
        assertNotNull("KeyPairGenerator objects were not created", kpg);
        KeyPair kp, kp1;
        SecureRandom rr = new SecureRandom();
        for (int i = 0; i < kpg.length; i++) {
            kpg[i].initialize(512, rr);
            kp = kpg[i].generateKeyPair();
            assertTrue("Not KeyPair object", kp instanceof KeyPair);
            kp1 = kpg[i].genKeyPair();
            assertTrue("Not KeyPair object", kp1 instanceof KeyPair);
            assertFalse("Incorrect private key", kp.getPrivate().equals(
                    kp1.getPrivate()));
            assertFalse("Incorrect public key", kp.getPublic().equals(
                    kp1.getPublic()));
        }
    }
    
    /**
     * Test for <code>generateKeyPair()</code> and <code>genKeyPair()</code>
     * methods
     * Assertion: these methods are used without previously initialization
     */
    public void testGenKeyPair02() throws NoSuchAlgorithmException,
            NoSuchProviderException, IllegalArgumentException {
        if (!DSASupported) {
            fail(NotSupportMsg);
            return;
        }
        KeyPairGenerator[] kpg = createKPGen();
        assertNotNull("KeyPairGenerator objects were not created", kpg);
        KeyPair kp, kp1;   
        for (int i = 0; i < kpg.length; i++) {
            try {
                kp = kpg[i].generateKeyPair();
                assertTrue("Not KeyPair object", kp instanceof KeyPair);
                kp1 = kpg[i].genKeyPair();
                assertTrue("Not KeyPair object", kp1 instanceof KeyPair);
                assertFalse("Incorrect private key", kp.getPrivate().equals(
                    kp1.getPrivate()));
                assertFalse("Incorrect public key", kp.getPublic().equals(
                    kp1.getPublic()));
            } catch (Exception e) {
                logln("Unexpected exception was thrown during KeyPair "
                        + " generation without previous initialization");
            }
        }
    }

    public static void main(String args[]) {
        junit.textui.TestRunner.run(KeyPairGeneratorTest3.class);    
    }
    
}