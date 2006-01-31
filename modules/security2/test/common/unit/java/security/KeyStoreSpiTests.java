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

import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Date;

import org.apache.harmony.security.cert.MyCertificate;
import junit.framework.TestCase;


/**
 * Tests for <code>KeyStoreSpi</code> constructor and methods
 * 
 */

public class KeyStoreSpiTests extends TestCase {

    /**
     * Constructor for KeyStoreSpi.
     * 
     * @param arg0
     */
    public KeyStoreSpiTests(String arg0) {
        super(arg0);
    }

    /**
     * Test for <code>KeyStoreSpi()</code> constructor 
     * and the following methods: 
     * <code>engineLoad(KeyStore.LoadStoreParameter param)</code>
     * <code>engineStore(KeyStore.LoadStoreParameter param)</code>
     * <code>engineGetEntry(String alias, KeyStore.ProtectionParameter param)</code>
     * <code>engineSetEntry(String alias, KeyStore.Entry entry, KeyStore.ProtectionParameter param)</code>
     * Assertions: 
     * creates new KeyStoreSpi object;
     * engineGetEntry(..) returns null entry;
     * engineStore(..) throws UnexpectedOperationException;
     * engineSetEntry(..) throws KeyStoreException or NullPointerException 
     */
    public void testKeyStoteSpi01() throws IOException,
            NoSuchAlgorithmException, CertificateException,
            UnrecoverableEntryException, KeyStoreException {
        KeyStoreSpi ksSpi = new MyKeyStoreSpi();

        tmpEntry entry = new tmpEntry();
        tmpProtection pPar = new tmpProtection();
        try {
            ksSpi.engineLoad(null);
        } catch (UnsupportedOperationException e) {
        }

        try {
            ksSpi.engineStore(null);
        } catch (UnsupportedOperationException e) {
        }
        assertNull("Not null entry", ksSpi.engineGetEntry("aaa", null));
        assertNull("Not null entry", ksSpi.engineGetEntry(null, pPar));
        assertNull("Not null entry", ksSpi.engineGetEntry("aaa", pPar));

        try {
            ksSpi.engineSetEntry("", null, null);
            fail("KeyStoreException or NullPointerException must be thrown");
        } catch (KeyStoreException e) {
        } catch (NullPointerException e) {
        }

        try {
            ksSpi.engineSetEntry("", new KeyStore.TrustedCertificateEntry(
                    new MyCertificate("type", new byte[0])), null);
            fail("KeyStoreException must be thrown");
        } catch (KeyStoreException e) {            
        }

        try {
            ksSpi.engineSetEntry("aaa", entry, null);
            fail("KeyStoreException must be thrown");
        } catch (KeyStoreException e) {
        }
    }


    /**
     * Test for <code>KeyStoreSpi()</code> constructor 
     * and abstract engine methods.
     * Assertion: creates new KeyStoreSpi object.
     */
    public void testKeyStoteSpi02() throws NoSuchAlgorithmException,
            UnrecoverableKeyException, CertificateException {
        KeyStoreSpi ksSpi = new MyKeyStoreSpi();
        assertNull("engineGetKey(..) must return null", ksSpi.engineGetKey("",
                new char[0]));
        assertNull("engineGetCertificateChain(..) must return null", ksSpi
                .engineGetCertificateChain(""));
        assertNull("engineGetCertificate(..) must return null", ksSpi
                .engineGetCertificate(""));
        assertEquals("engineGetCreationDate(..) must return Date(0)", 
                new Date(0), ksSpi.engineGetCreationDate(""));
        try {
            ksSpi.engineSetKeyEntry("", null, new char[0], new Certificate[0]);
            fail("KeyStoreException must be thrown from engineSetKeyEntry(..)");
        } catch (KeyStoreException e) {
        }
        try {
            ksSpi.engineSetKeyEntry("", new byte[0], new Certificate[0]);
            fail("KeyStoreException must be thrown from engineSetKeyEntry(..)");
        } catch (KeyStoreException e) {
        }
        try {
            ksSpi.engineSetCertificateEntry("", null);
            fail("KeyStoreException must be thrown from engineSetCertificateEntry(..)");
        } catch (KeyStoreException e) {
        }
        try {
            ksSpi.engineDeleteEntry("");
            fail("KeyStoreException must be thrown from engineDeleteEntry(..)");
        } catch (KeyStoreException e) {
        }
        assertNull("engineAliases() must return null", ksSpi.engineAliases());
        assertFalse("engineConatinsAlias(..) must return false", ksSpi
                .engineContainsAlias(""));
        assertEquals("engineSize() must return 0", 0, ksSpi.engineSize());
        try {
            ksSpi.engineStore(null, null);
            fail("IOException must be thrown");
        } catch (IOException e) {
        }
    }
    
    public static void main(String args[]) {
        junit.textui.TestRunner.run(KeyStoreSpiTests.class);
    }
}
/**
 * Additional class implements KeyStore.Entry interface
 */
class tmpEntry  implements KeyStore.Entry {  
}
class tmpProtection implements KeyStore.ProtectionParameter {
}
