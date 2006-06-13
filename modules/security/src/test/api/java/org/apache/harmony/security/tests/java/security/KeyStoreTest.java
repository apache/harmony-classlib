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

package org.apache.harmony.security.tests.java.security;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.Provider;
import java.security.Security;
import java.security.cert.Certificate;

import org.apache.harmony.security.tests.support.KeyStoreTestSupport;
import org.apache.harmony.security.tests.support.MyLoadStoreParams;
import org.apache.harmony.security.tests.support.SpiEngUtils;

import junit.framework.TestCase;

/**
 * Tests for <code>KeyStore</code> constructor and methods
 * 
 */

public class KeyStoreTest extends TestCase {

    private static final String KeyStoreProviderClass = "org.apache.harmony.security.tests.support.MyKeyStore";

    private static final String defaultType = "KeyStore";

    public static boolean KSSupported = false;

    public static String defaultProviderName = null;

    public static Provider defaultProvider = null;

    private static String NotSupportMsg = "Default KeyStore type is not supported";

    Provider mProv;

    public KeyStore[] createKS() throws Exception {
        assertTrue(NotSupportMsg, KSSupported);
        KeyStore[] kpg = new KeyStore[3];

        kpg[0] = KeyStore.getInstance(defaultType);
        kpg[1] = KeyStore.getInstance(defaultType, defaultProvider);
        kpg[2] = KeyStore.getInstance(defaultType, defaultProviderName);
        return kpg;
    }

    protected void setUp() throws Exception {
        super.setUp();
        mProv = (new SpiEngUtils()).new MyProvider("MyKSProvider",
                "Testing provider", KeyStoreTestSupport.srvKeyStore.concat(".")
                        .concat(defaultType), KeyStoreProviderClass);
        Security.insertProviderAt(mProv, 2);
        defaultProvider = SpiEngUtils.isSupport(defaultType,
                KeyStoreTestSupport.srvKeyStore);
        KSSupported = (defaultProvider != null);
        defaultProviderName = (KSSupported ? defaultProvider.getName() : null);
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        Security.removeProvider(mProv.getName());
    }

    /**
     * Test for <code>load(LoadStoreParameter param)</code> 
     * <code>store(LoadStoreParameter param)</code>
     * methods 
     * Assertions: throw IllegalArgumentException if param is null;
     */
    public void testLoadStore02() throws Exception {
        assertTrue(NotSupportMsg, KSSupported);

        KeyStore[] kss = createKS();
        assertNotNull("KeyStore objects were not created", kss);

        for (int i = 0; i < kss.length; i++) {
            try {
                kss[i].load(null);
                fail("IOException or IllegalArgumentException should be thrown for null parameter");
            } catch (IOException e) {
            } catch (IllegalArgumentException e) {
            }
            kss[i].load(null, null);
            try {
                kss[i].store(null);
                fail("IOException or IllegalArgumentException should be thrown for nill parameter");
            } catch (IOException e) {
            } catch (IllegalArgumentException e) {
            }
        }
        KeyStore.LoadStoreParameter lParam = new MyLoadStoreParams(
                new KeyStore.PasswordProtection(new char[0]));
        for (int i = 0; i < kss.length; i++) {
            kss[i].load(lParam);
            assertEquals("Incorrect result", kss[i].size(), 0);
            kss[i].store(lParam);
        }
    }

    
    /**
     * Test for <code>setKeyEntry(String alias, bute[] key, Certificate[] chain)</code> 
     * method 
     * Assertion: stores KeyEntry.
     */
    public void testSetKeyEntry() throws Exception {
        assertTrue(NotSupportMsg, KSSupported);
        
        KeyStore[] kss = createKS();
        assertNotNull("KeyStore objects were not created", kss);
        byte[] kk = { (byte) 1, (byte) 2, (byte) 127, (byte) 77 };
        String alias = "keyEntry";
        char[] pwd = new char[0];
        byte[] res;
        Certificate certs[] = {
                new KeyStoreTestSupport.MCertificate(alias, kk),
                new KeyStoreTestSupport.MCertificate(alias, kk) };
        for (int i = 0; i < kss.length; i++) {
            kss[i].load(null, null);
            try {
                kss[i].setKeyEntry("proba", null, null);
                fail("KeyStoreException must be thrown");
            } catch (KeyStoreException e) {
            }
            kss[i].setKeyEntry(alias, kk, certs);
            res = kss[i].getKey(alias, pwd).getEncoded();
            assertEquals(kk.length, res.length);
            for (int j = 0; j < res.length; j++) {
                assertEquals(res[j], kk[j]);
            }
            assertEquals(kss[i].getCertificateChain(alias).length, certs.length);
            kss[i].setKeyEntry(alias, kk, null);
            res = kss[i].getKey(alias, pwd).getEncoded();
            assertEquals(kk.length, res.length);
            for (int j = 0; j < res.length; j++) {
                assertEquals(res[j], kk[j]);
            }
            assertNull(kss[i].getCertificateChain(alias));
        }
    }

    /**
     * Test for <code>getDefaultType()</code> method Assertion: returns
     * default security key store type or "jks" string
     */
    public void testKeyStore01() {
        String propName = "keystore.type";
        String defKSType = Security.getProperty(propName);
        String dType = KeyStore.getDefaultType();
        String resType = defKSType;
        if (resType == null) {
            resType = defaultType;
        }
        assertNotNull("Default type have not be null", dType);
        assertEquals("Incorrect default type", dType, resType);
        
        if (defKSType == null) {
            Security.setProperty(propName, defaultType);
            dType = KeyStore.getDefaultType();
            resType = Security.getProperty(propName);
            assertNotNull("Incorrect default type", resType);
            assertNotNull("Default type have not be null", dType);
            assertEquals("Incorrect default type", dType, resType);
        }
    }

    /**
     * Test for <code>getInstance(String type)</code> method 
     * Assertion: 
     * throws NullPointerException when type is null 
     * throws KeyStoreException when type is not available
     * 
     */
    public void testKeyStore02() throws KeyStoreException {
        String[] invalidValues =  SpiEngUtils.invalidValues;
        try {
            KeyStore.getInstance(null);
            fail("NullPointerException must be thrown when type is null");
        } catch (NullPointerException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                KeyStore.getInstance(invalidValues[i]);
                fail("KeyStoreException must be thrown (type: ".concat(
                        invalidValues[i]).concat(" )"));
            } catch (KeyStoreException e) {
            }
        }
    }
}
