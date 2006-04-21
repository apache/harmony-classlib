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
import java.util.Date;
import java.util.Set;

import javax.security.auth.x500.X500Principal;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * X509CertificateTest
 */
public class X509CertificateTest extends TestCase {

    // certificate encoding was created by hands
    // according to X.509 Certificate ASN.1 notation
    private static final byte[] x509CertEnc = new byte[] {
    // Certificate: SEQUENCE
            0x30, 0x6B,

            //
            // TBSCertificate: SEQUENCE {
            //
            0x30, 0x5C,

            // version: [0] EXPLICIT Version DEFAULT v1
            (byte) 0xA0, 0x03, 0x02, 0x01, 0x00,

            // serialNumber: CertificateSerialNumber
            0x02, 0x01, 0x05,

            // signature: AlgorithmIdentifier
            0x30, 0x07, // SEQUENCE
            0x06, 0x02, 0x03, 0x05,//OID
            0x01, 0x01, 0x07, //ANY

            //issuer: Name
            0x30, 0x0C, 0x31, 0x0A, 0x30, 0x08, 0x06, 0x03, 0x55, 0x04, 0x03,
            0x13, 0x01, 0x5A, // CN=Z

            //validity: Validity
            0x30, 0x1E, // SEQUENCE
            // notBefore: UTCTime
            0x17, 0x0D, 0x39, 0x39, 0x31, 0x32, 0x31, 0x33, 0x31, 0x34, 0x31,
            0x35, 0x31, 0x36, 0x5A, // 13 Dec 1999 14:15:16
            // notAfter:  UTCTime
            0x17, 0x0D, 0x30, 0x30, 0x30, 0x31, 0x30, 0x31, 0x30, 0x30, 0x30,
            0x30, 0x30, 0x30, 0x5A, // 01 Jan 2000 00:00:00

            //subject: Name
            0x30, 0x0C, 0x31, 0x0A, 0x30, 0x08, 0x06, 0x03, 0x55, 0x04, 0x03,
            0x13, 0x01, 0x59, // CN=Y

            //SubjectPublicKeyInfo  ::=  SEQUENCE  {
            //    algorithm            AlgorithmIdentifier,
            //    subjectPublicKey     BIT STRING  }
            0x30, 0x0D, // SEQUENCE
            0x30, 0x07, // SEQUENCE
            0x06, 0x02, 0x03, 0x05,//OID
            0x01, 0x01, 0x07, //ANY
            0x03, 0x02, 0x00, 0x01, // subjectPublicKey

            // issuerUniqueID - missed
            // subjectUniqueID - missed
            // extensions - missed

            // } end TBSCertificate

            //
            // signatureAlgorithm: AlgorithmIdentifier
            //
            0x30, 0x07, // SEQUENCE
            0x06, 0x02, 0x03, 0x05,//OID
            0x01, 0x01, 0x07, //ANY

            //
            // signature: BIT STRING  
            //
            0x03, 0x02, 0x00, 0x01 };

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
     * @tests java.security.cert.X509Certificate#getIssuerX500Principal()
     */
    public void testGetIssuerX500Principal() {
        // return valid encoding
        MyX509Certificate cert = new MyX509Certificate() {
            public byte[] getEncoded() {
                return x509CertEnc;
            };
        };

        assertEquals(new X500Principal("CN=Z"), cert.getIssuerX500Principal());
    }

    /**
     * @tests java.security.cert.X509Certificate#getSubjectX500Principal()
     */
    public void testGetSubjectX500Principal() {
        // return valid encoding
        MyX509Certificate cert = new MyX509Certificate() {
            public byte[] getEncoded() {
                return x509CertEnc;
            };
        };

        assertEquals(new X500Principal("CN=Y"), cert.getSubjectX500Principal());
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

