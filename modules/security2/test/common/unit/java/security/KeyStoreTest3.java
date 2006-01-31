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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Provider;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;

import org.apache.harmony.security.SpiEngUtils;
import junit.framework.TestCase;


/**
 * Tests for <code>KeyStore</code> constructor and methods
 * 
 */

public class KeyStoreTest3 extends TestCase {

    private static final String KeyStoreProviderClass = "java.security.MyKeyStore";

    private static final String defaultType = "KeyStore";

    public static boolean KSSupported = false;

    public static String defaultProviderName = null;

    public static Provider defaultProvider = null;

    private static String NotSupportMsg = "Default KeyStore type is not supported";

    Provider mProv;

    public KeyStore[] createKS() {
        if (!KSSupported) {
            fail(NotSupportMsg);
            return null;
        }
        KeyStore[] kpg = new KeyStore[3];
        try {
            kpg[0] = KeyStore.getInstance(defaultType);
            kpg[1] = KeyStore.getInstance(defaultType, defaultProvider);
            kpg[2] = KeyStore.getInstance(defaultType, defaultProviderName);
            return kpg;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void setUp() throws Exception {
        super.setUp();
        mProv = (new SpiEngUtils()).new MyProvider("MyKSProvider",
                "Testing provider", KeyStoreTest1.srvKeyStore.concat(".")
                        .concat(defaultType), KeyStoreProviderClass);
        Security.insertProviderAt(mProv, 2);
        defaultProvider = SpiEngUtils.isSupport(defaultType,
                KeyStoreTest1.srvKeyStore);
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
     * Constructor for SecurityManagerFactoryTest2.
     * 
     * @param arg0
     */
    public KeyStoreTest3(String arg0) {
        super(arg0);
    }

    /**
     * Test for <code>load(InputStream stream, char[] password)</code> 
     * <code>store(InputStream stream, char[] password)</code> 
     * <code>size()</code>
     * <code>getCreationDate(String alias)</code>
     * methods 
     * Assertions: store(...) throws NullPointerException when stream or
     * password is null; 
     * getCreationDate(...) throws NullPointerException when alias is null;
     * stores KeyStore and then loads it;
     */

    public void testLoadStore01() throws NoSuchAlgorithmException, IOException,
            CertificateException, KeyStoreException, InvalidKeySpecException,
            UnrecoverableEntryException, UnrecoverableKeyException {
        if (!KSSupported) {
            fail(NotSupportMsg);
            return;
        }
        String tType = "TestType";
        KeyStoreTest1 KeyStoreT1 = new KeyStoreTest1("proba");
        KeyStore.TrustedCertificateEntry tCert = new KeyStore.TrustedCertificateEntry(
                KeyStoreT1.new MCertificate("type", new byte[0]));

        Certificate certs[] = {
                (Certificate) KeyStoreT1.new MCertificate(tType, new byte[10]),
                (Certificate) KeyStoreT1.new MCertificate(tType, new byte[20]) };
        PrivateKey pk = KeyStoreT1.new MyPrivateKey(tType, "", new byte[10]);
        KeyStore.PrivateKeyEntry pKey = new KeyStore.PrivateKeyEntry(pk, certs);
        char[] pwd = { 'p', 'a', 's', 's', 'w', 'd' };
        KeyStore.PasswordProtection pPath = new KeyStore.PasswordProtection(pwd);
        String[] aliases = { "Alias1", "Alias2", "Alias3", "Alias4", "Alias5" };

        KeyStore[] kss = createKS();
        KeyStore[] kss1 = new KeyStore[kss.length];
        assertNotNull("KeyStore objects were not created", kss);

        for (int i = 0; i < kss.length; i++) {
            kss1[i] = kss[i];
            kss[i].load(null, null);
            kss[i].setEntry(aliases[0], tCert, null);
            kss[i].setEntry(aliases[1], pKey, pPath);
            kss[i].setEntry(aliases[2], pKey, pPath);
            try {
                kss[i].getCreationDate(null);
                fail("NullPointerException should be thrown when alias is null");
            } catch (NullPointerException e) {
            }

            kss[i].setKeyEntry(aliases[3], pk, pwd, certs);
            kss[i].setCertificateEntry(aliases[4], certs[0]);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                kss[i].store(null, pwd);
                fail("IOException or NullPointerException should be thrown when stream is null");
            } catch (IOException e) {
            } catch (NullPointerException e) {
            }

            try {
                kss[i].store(bos, null);
                fail("store(...) does not throw any exception when password is null");
            } catch (IOException e) {
            }

            kss[i].store(bos, pwd);
            ByteArrayInputStream bis = new ByteArrayInputStream(bos
                    .toByteArray());
            kss1[i].load(bis, pwd);
            assertEquals("Incorrect size", kss1[i].size(), kss[i].size());
            KeyStore.Entry en, en1;
            for (int j = 0; j < 3; j++) {
                en = kss[i].getEntry(aliases[j], (j == 0 ? null : pPath));
                en1 = kss1[i].getEntry(aliases[j], (j == 0 ? null : pPath));
                if (en instanceof KeyStore.TrustedCertificateEntry) {
                    assertTrue("Incorrect entry 1",
                            en1 instanceof KeyStore.TrustedCertificateEntry);
                    assertEquals("Incorrect Certificate",
                            ((KeyStore.TrustedCertificateEntry) en)
                                    .getTrustedCertificate(),
                            ((KeyStore.TrustedCertificateEntry) en1)
                                    .getTrustedCertificate());
                } else {
                    if (en instanceof KeyStore.PrivateKeyEntry) {
                        assertTrue("Incorrect entry 2",
                                en1 instanceof KeyStore.PrivateKeyEntry);
                        assertEquals(
                                "Incorrect Certificate",
                                ((KeyStore.PrivateKeyEntry) en).getPrivateKey(),
                                ((KeyStore.PrivateKeyEntry) en1)
                                        .getPrivateKey());
                    } else {
                        if (en instanceof KeyStore.SecretKeyEntry) {
                            assertTrue("Incorrect entry 3",
                                    en1 instanceof KeyStore.SecretKeyEntry);
                            assertEquals("Incorrect Certificate",
                                    ((KeyStore.SecretKeyEntry) en)
                                            .getSecretKey(),
                                    ((KeyStore.SecretKeyEntry) en1)
                                            .getSecretKey());
                        }
                    }
                }

                assertEquals("Incorrect date", kss[i]
                        .getCreationDate(aliases[j]), kss1[i]
                        .getCreationDate(aliases[j]));
            }
            assertEquals("Incorrect entry", kss[i].getKey(aliases[3], pwd),
                    kss1[i].getKey(aliases[3], pwd));
            assertEquals("Incorrect date", kss[i].getCreationDate(aliases[3]),
                    kss1[i].getCreationDate(aliases[3]));
            assertEquals("Incorrect entry", kss[i].getCertificate(aliases[4]),
                    kss1[i].getCertificate(aliases[4]));
            assertEquals("Incorrect date", kss[i].getCreationDate(aliases[4]),
                    kss1[i].getCreationDate(aliases[4]));
        }
    }

    /**
     * Test for <code>load(LoadStoreParameter param)</code> 
     * <code>store(LoadStoreParameter param)</code>
     * methods 
     * Assertions: throw IllegalArgumentException if param is null;
     */
    public void testLoadStore02() throws NoSuchAlgorithmException, IOException,
            CertificateException, KeyStoreException {
        if (!KSSupported) {
            fail(NotSupportMsg);
            return;
        }
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
    public void testSetKeyEntry() throws NoSuchAlgorithmException, IOException,
            CertificateException, KeyStoreException, UnrecoverableKeyException {
        if (!KSSupported) {
            fail(NotSupportMsg);
            return;
        }
        KeyStore[] kss = createKS();
        assertNotNull("KeyStore objects were not created", kss);
        byte[] kk = { (byte) 1, (byte) 2, (byte) 127, (byte) 77 };
        String alias = "keyEntry";
        char[] pwd = new char[0];
        byte[] res;
        KeyStoreTest1 KeyStoreT1 = new KeyStoreTest1("proba");
        Certificate certs[] = {
                (Certificate) KeyStoreT1.new MCertificate(alias, kk),
                (Certificate) KeyStoreT1.new MCertificate(alias, kk) };
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
    
    public static void main(String args[]) {
        junit.textui.TestRunner.run(KeyStoreTest3.class);    
    }
}
