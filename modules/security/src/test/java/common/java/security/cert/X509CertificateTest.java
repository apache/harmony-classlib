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
* @author Alexander Y. Kleymenov
* @version $Revision$
*/

package java.security.cert;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Set;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * X509CertificateTest
 */
public class X509CertificateTest extends TestCase {

    // has stub implementation for abstract methods
    private static class MyX509Certificate extends X509Certificate {

            public void checkValidity()
                    throws CertificateExpiredException,
                           CertificateNotYetValidException {}

            public void checkValidity(Date date)
                    throws CertificateExpiredException,
                           CertificateNotYetValidException {}

            public int getVersion() {
                return 3;
            }

            public BigInteger getSerialNumber() {
                return null;
            }

            public Principal getIssuerDN() {
                return null;
            }

            public Principal getSubjectDN() {
                return null;
            }

            public Date getNotBefore() {
                return null;
            }

            public Date getNotAfter() {
                return null;
            }

            public byte[] getTBSCertificate()
                                throws CertificateEncodingException
            {
                return null;
            }

            public byte[] getSignature() {
                return null;
            }

            public String getSigAlgName() {
                return null;
            }

            public String getSigAlgOID() {
                return null;
            }

            public byte[] getSigAlgParams() {
                return null;
            }

            public boolean[] getIssuerUniqueID() {
                return null;
            }

            public boolean[] getSubjectUniqueID() {
                return null;
            }

            public boolean[] getKeyUsage() {
                return null;
            }

            public int getBasicConstraints() {
                return 0;
            }

            public void verify(PublicKey key)
                         throws CertificateException, NoSuchAlgorithmException,
                                InvalidKeyException, NoSuchProviderException,
                                SignatureException
            {
            }

            public void verify(PublicKey key,
                                        String sigProvider)
                         throws CertificateException, NoSuchAlgorithmException,
                                InvalidKeyException, NoSuchProviderException,
                                SignatureException
            {
            }

            public String toString() {
                return "";
            }

            public PublicKey getPublicKey() {
                return null;
            }

            public byte[] getEncoded() throws CertificateEncodingException
            {
                return null;
            }

            public Set getNonCriticalExtensionOIDs() {
                return null;
            }

            public Set getCriticalExtensionOIDs() {
                return null;
            }

            public byte[] getExtensionValue(String oid) {
                return null;
            }

            public boolean hasUnsupportedCriticalExtension() {
                return false;
            }
    }

    /**
     * @tests java.security.cert.X509Certificate#getType()
     */
    public void testGetType() {
        assertEquals("X.509", new MyX509Certificate().getType());
    }

    /**
     * getIssuerX500Principal() method testing.
     */
    public void testGetIssuerX500Principal() {
        try {
            new MyX509Certificate().getIssuerX500Principal();
            fail("Runtime exception should be thrown "
                        + "in default implementation");
        } catch (RuntimeException e) {
        }
    }

    /**
     * getSubjectX500Principal() method testing.
     */
    public void testGetSubjectX500Principal() {
        try {
            new MyX509Certificate().getSubjectX500Principal();
            fail("Runtime exception should be thrown "
                        + "in default implementation");
        } catch (RuntimeException e) {
        }
    }

    /**
     * @tests java.security.cert.X509Certificate#getExtendedKeyUsage()
     */
    public void testGetExtendedKeyUsage() throws CertificateParsingException {
        assertNull(new MyX509Certificate().getExtendedKeyUsage());
    }

    /**
     * @tests java.security.cert.X509Certificate#getSubjectAlternativeNames()
     */
    public void testGetSubjectAlternativeNames()
            throws CertificateParsingException {

        assertNull(new MyX509Certificate().getSubjectAlternativeNames());
    }

    /**
     * @tests java.security.cert.X509Certificate#getIssuerAlternativeNames()
     */
    public void testGetIssuerAlternativeNames()
            throws CertificateParsingException {

        assertNull(new MyX509Certificate().getIssuerAlternativeNames());
    }

    public static Test suite() {
        return new TestSuite(X509CertificateTest.class);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}

