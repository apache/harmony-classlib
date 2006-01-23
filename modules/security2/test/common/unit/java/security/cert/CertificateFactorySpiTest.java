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

package java.security.cert;


import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.harmony.security.test.PerformanceTest;

/**
 * Tests for <code>CertificateFactorySpi</code> class constructors and methods
 * 
 */

public class CertificateFactorySpiTest extends PerformanceTest {
    /**
     * Constructor for CertStoreSpiTest.
     * 
     * @param arg0
     */
    public CertificateFactorySpiTest(String arg0) {
        super(arg0);
    }

    /**
     * Test for <code>CertificateFactorySpi</code> constructor 
     * Assertion: constructs CertificateFactorySpi
     */
    public void testCertificateFactorySpi01() throws CertificateException,
            CRLException {
        CertificateFactorySpi certFactorySpi = (CertificateFactorySpi) new extCertificateFactorySpi();
        assertTrue(certFactorySpi instanceof CertificateFactorySpi);
        ByteArrayInputStream bais = new ByteArrayInputStream(new byte[0]);
        try {
            certFactorySpi.engineGenerateCertPath((InputStream) bais);
            fail("UnsupportedOperationException must be thrown");
        } catch (UnsupportedOperationException e) {
        }
        try {
            certFactorySpi.engineGenerateCertPath((InputStream) bais, "");
            fail("UnsupportedOperationException must be thrown");
        } catch (UnsupportedOperationException e) {
        }
        try {
            List list = null;
            certFactorySpi.engineGenerateCertPath(list);
            fail("UnsupportedOperationException must be thrown");
        } catch (UnsupportedOperationException e) {
        }
        try {
            certFactorySpi.engineGetCertPathEncodings();
            fail("UnsupportedOperationException must be thrown");
        } catch (UnsupportedOperationException e) {
        }
        Certificate cc = certFactorySpi
                .engineGenerateCertificate((InputStream) bais);
        assertNull("Not null Cerificate", cc);
        try {
            certFactorySpi.engineGenerateCertificate(null);
            fail("CertificateException must be thrown");
        } catch (CertificateException e) {
        }
        Collection col = certFactorySpi
                .engineGenerateCertificates((InputStream) bais);
        assertNull("Not null Collection", col);
        try {
            certFactorySpi.engineGenerateCertificates(null);
            fail("CertificateException must be thrown");
        } catch (CertificateException e) {
        }

        CRL ccCRL = certFactorySpi.engineGenerateCRL((InputStream) bais);
        assertNull("Not null CRL", ccCRL);
        try {
            certFactorySpi.engineGenerateCRL(null);
            fail("CRLException must be thrown");
        } catch (CRLException e) {
        }

        Collection colCRL = certFactorySpi
                .engineGenerateCRLs((InputStream) bais);
        assertNull("Not null CRL", colCRL);
        try {
            certFactorySpi.engineGenerateCRLs(null);
            fail("CRLException must be thrown");
        } catch (CRLException e) {
        }
    }

    /**
     * Test for <code>CertificateFactorySpi</code> constructor 
     * Assertion: constructs CertificateFactorySpi
     */
    public void testCertificateFactorySpi02() throws CertificateException,
            CRLException {
        CertificateFactorySpi certFactorySpi = (CertificateFactorySpi) new MyCertificateFactorySpi();
        MyCertificateFactorySpi.putMode(true);
        assertTrue(certFactorySpi instanceof CertificateFactorySpi);
        ByteArrayInputStream bais = new ByteArrayInputStream(new byte[0]);
        DataInputStream dis = new DataInputStream(bais);
        try {
            certFactorySpi.engineGenerateCertPath((InputStream) bais);
            fail("CertificateException must be thrown");
        } catch (CertificateException e) {
        }
        certFactorySpi.engineGenerateCertPath((InputStream) dis);
        try {
            certFactorySpi.engineGenerateCertPath((InputStream) bais, "aa");
            fail("CertificateException must be thrown");
        } catch (CertificateException e) {
        }
        try {
            certFactorySpi.engineGenerateCertPath((InputStream) dis, "");
            fail("IllegalArgumentException must be thrown");
        } catch (IllegalArgumentException e) {
        }
        certFactorySpi.engineGenerateCertPath((InputStream) dis, "ss");

        try {
            certFactorySpi.engineGenerateCertificate((InputStream) bais);
            fail("CertificateException must be thrown");
        } catch (CertificateException e) {
        }
        try {
            certFactorySpi.engineGenerateCertificates(null);
            fail("CertificateException must be thrown");
        } catch (CertificateException e) {
        }
        Certificate cert = certFactorySpi
                .engineGenerateCertificate((InputStream) dis);
        assertNull("Result must be null", cert);
        Collection col = certFactorySpi
                .engineGenerateCertificates((InputStream) dis);
        assertNull("Result must be null", col);

        try {
            certFactorySpi.engineGenerateCRL((InputStream) bais);
            fail("CRLException must be thrown");
        } catch (CRLException e) {
        }
        try {
            certFactorySpi.engineGenerateCRLs(null);
            fail("CRLException must be thrown");
        } catch (CRLException e) {
        }
        CRL crl = certFactorySpi.engineGenerateCRL((InputStream) dis);
        assertNull("Result must be null", crl);
        col = certFactorySpi.engineGenerateCRLs((InputStream) dis);
        assertNull("Result must be null", col);

        List list = null;
        try { 
            certFactorySpi.engineGenerateCertPath(list);
            fail("NullPointerException must be thrown");
        } catch (NullPointerException e) {            
        }
        Iterator enc = certFactorySpi.engineGetCertPathEncodings();
        assertTrue("Not Iterator object", enc instanceof Iterator);
        assertTrue("Incorrect Iterator", enc.hasNext());
    }
    
    /**
     * Test for <code>CertificateFactorySpi</code> constructor 
     * Assertion: constructs CertificateFactorySpi
     */
    public void testCertificateFactorySpi03() throws CertificateException,
            CRLException {
        CertificateFactorySpi certFactorySpi = (CertificateFactorySpi) new MyCertificateFactorySpi();
        MyCertificateFactorySpi.putMode(false);
        assertTrue(certFactorySpi instanceof CertificateFactorySpi);
        ByteArrayInputStream bais = new ByteArrayInputStream(new byte[0]);
        DataInputStream dis = new DataInputStream(bais);
        try {
            certFactorySpi.engineGenerateCertPath((InputStream) bais);
            fail("CertificateException must be thrown");
        } catch (CertificateException e) {
        }
        try {
            certFactorySpi.engineGenerateCertPath((InputStream) dis);
            fail("CertificateException must be thrown");
        } catch (CertificateException e) {
        }
        try {
            certFactorySpi.engineGenerateCertPath((InputStream) bais, "aa");
            fail("CertificateException must be thrown");
        } catch (CertificateException e) {
        }
        certFactorySpi.engineGenerateCertPath((InputStream) dis, "");
        certFactorySpi.engineGenerateCertPath((InputStream) dis, "ss");

        try {
            certFactorySpi.engineGenerateCertificate((InputStream) bais);
            fail("CertificateException must be thrown");
        } catch (CertificateException e) {
        }
        try {
            certFactorySpi.engineGenerateCertificates(null);
            fail("CertificateException must be thrown");
        } catch (CertificateException e) {
        }
        Certificate cert = certFactorySpi
                .engineGenerateCertificate((InputStream) dis);
        assertNull("Result must be null", cert);
        Collection col = certFactorySpi
                .engineGenerateCertificates((InputStream) dis);
        assertNull("Result must be null", col);

        try {
            certFactorySpi.engineGenerateCRL((InputStream) bais);
            fail("CRLException must be thrown");
        } catch (CRLException e) {
        }
        try {
            certFactorySpi.engineGenerateCRLs(null);
            fail("CRLException must be thrown");
        } catch (CRLException e) {
        }
        CRL crl = certFactorySpi.engineGenerateCRL((InputStream) dis);
        assertNull("Result must be null", crl);
        col = certFactorySpi.engineGenerateCRLs((InputStream) dis);
        assertNull("Result must be null", col);

        List list = null;
        certFactorySpi.engineGenerateCertPath(list);
        Iterator enc = certFactorySpi.engineGetCertPathEncodings();
        assertTrue("Not Iterator object", enc instanceof Iterator);
        assertFalse("Incorrect Iterator", enc.hasNext());
    }

    
    private static class extCertificateFactorySpi extends CertificateFactorySpi {
        public Certificate engineGenerateCertificate(InputStream inStream)
                throws CertificateException {
            if (inStream == null) {
                throw new CertificateException("InputStream null");
            }
            return null;
        }

        public Collection engineGenerateCertificates(InputStream inStream)
                throws CertificateException {
            if (inStream == null) {
                throw new CertificateException("InputStream null");
            }
            return null;
        }

        public CRL engineGenerateCRL(InputStream inStream) throws CRLException {
            if (inStream == null) {
                throw new CRLException("InputStream null");
            }
            return null;
        }

        public Collection engineGenerateCRLs(InputStream inStream)
                throws CRLException {
            if (inStream == null) {
                throw new CRLException("InputStream null");
            }
            return null;
        }
    }
}