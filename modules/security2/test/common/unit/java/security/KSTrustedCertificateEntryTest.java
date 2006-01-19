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

import java.security.cert.Certificate;

import org.apache.harmony.security.cert.MyCertificate;
import org.apache.harmony.security.test.PerformanceTest;

/**
 * Tests for <code>KeyStore.TrustedCertificateEntry</code> class constructor and methods 
 * 
 */

public class KSTrustedCertificateEntryTest extends PerformanceTest {

    /**
     * Test for <codfe>KeyStore.TrustedCertificateEntry(Certificate trustCert)</code>
     * constructor
     * Assertion: throws NullPointerException when trustCert is null
     */
    public void testTrustedCertificateEntry() {
        Certificate cert = null;
        try {
            new KeyStore.TrustedCertificateEntry(cert);
            fail("NullPointerException must be thrown when trustCert is null");
        } catch (NullPointerException e) {
        }
    }
    
    /**
     * Test for <codfe>getTrustedCertificate()</code> method
     * Assertion: returns trusted Certificate from goven entry 
     */
    public void testGetTrustedCertificate() {
        Certificate cert = new MyCertificate("TEST", new byte[10]);
        KeyStore.TrustedCertificateEntry ksTCE = 
                new KeyStore.TrustedCertificateEntry(cert);
        assertTrue("Not KeyStore.TrustedCertificateEntry object", 
                ksTCE instanceof KeyStore.TrustedCertificateEntry);
        assertEquals("Incorrect certificate", cert, ksTCE.getTrustedCertificate());
    }

    /**
     * Test for <codfe>toString()</code> method
     * Assertion: returns non null string 
     */
    public void testToString() {
        Certificate cert = new MyCertificate("TEST", new byte[10]);
        KeyStore.TrustedCertificateEntry ksTCE = 
                new KeyStore.TrustedCertificateEntry(cert);
        assertTrue("Not KeyStore.TrustedCertificateEntry object", 
                ksTCE instanceof KeyStore.TrustedCertificateEntry);
        assertNotNull("toString() returns null string", ksTCE.toString());
        logln(ksTCE.toString());
    }
}
