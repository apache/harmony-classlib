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

package com.openintel.drl.security.provider.cert;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.CertPath;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.harmony.security.asn1.BerInputStream;

import com.openintel.drl.security.provider.cert.X509CertImplTest;
import com.openintel.drl.security.x509.Certificate;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * X509CertPathImplTest
 */
public class X509CertPathImplTest extends TestCase {
    
    private X509Certificate certificate;
    {
        try {
            X509CertImplTest test = new X509CertImplTest();
            test.setUp();
            certificate = test.certificate;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private X509CertPathImpl certPath;
    private List certList;
        
    protected void setUp() throws java.lang.Exception {
        certList = new ArrayList();
        for (int i=0; i<2; i++) {
            certList.add(certificate);
        }
        certPath = new X509CertPathImpl(certList);
    }
    
    /**
     * X509CertPathImpl(List certificates) method testing.
     */
    public void testX509CertPathImpl1() throws Exception {
        assertTrue("Certificate list size missmatch", 
                certList.size() == certPath.getCertificates().size());
    }
    
    /**
     * getInstance(InputStream in) method testing.
     */
    public void testX509CertPathImpl2() {
        try {
            byte[] encoding = certPath.getEncoded();
            ByteArrayInputStream bais = new ByteArrayInputStream(encoding);
            X509CertPathImpl cpath = X509CertPathImpl.getInstance(bais);
            assertTrue("Certificate list size missmatch", 
                    certList.size() == cpath.getCertificates().size());
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
            fail("Unexpected CertificateEncodingException was thrown:"
                    + e.getMessage());
        } catch (CertificateException e) {
            e.printStackTrace();
            fail("Unexpected CertificateException was thrown:"
                    + e.getMessage());
        }
    }
    
    /**
     * getInstance(byte[] in) method testing.
     */
    public void testX509CertPathImpl3() {
        try {
            byte[] encoding = certPath.getEncoded();
            X509CertPathImpl cpath = X509CertPathImpl.getInstance(encoding);
            assertTrue("Certificate list size missmatch", 
                    certList.size() == cpath.getCertificates().size());
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
            fail("Unexpected CertificateEncodingException was thrown:"
                    + e.getMessage());
        } catch (CertificateException e) {
            e.printStackTrace();
            fail("Unexpected CertificateException was thrown:"
                    + e.getMessage());
        }
    }
        
    /**
     * getCertificates() method testing.
     */
    public void testGetCertificates() {
        try {
            byte[] encoding = certPath.getEncoded();
            X509CertPathImpl cpath = X509CertPathImpl.getInstance(encoding);
            assertTrue("Certificate list size missmatch", 
                    certList.size() == cpath.getCertificates().size());
            cpath.getCertificates().remove(0);
            fail("UnsupportedOperationException should be thrown");
        } catch (UnsupportedOperationException e) {
            //pass
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
            fail("Unexpected CertificateEncodingException was thrown:"
                    + e.getMessage());
        } catch (CertificateException e) {
            e.printStackTrace();
            fail("Unexpected CertificateException was thrown:"
                    + e.getMessage());
        }
    }
    
    /**
     * getEncoded() method testing.
     */
    public void testGetEncoded1() {
        try {
            byte[] encoding = certPath.getEncoded();
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
            fail("Unexpected CertificateEncodingException was thrown:"
                    + e.getMessage());
        } catch (CertificateException e) {
            e.printStackTrace();
            fail("Unexpected CertificateException was thrown:"
                    + e.getMessage());
        }
    }
    
    /**
     * getEncoded(String encoding) method testing.
     */
    public void testGetEncoded2() {
        try {
            certPath.getEncoded("ABRACADABRA");
            fail("CertificateEncodingException should be thrown");
        } catch (CertificateEncodingException e) {
            // pass
        } catch (CertificateException e) {
            e.printStackTrace();
            fail("Unexpected CertificateException was thrown:"
                    + e.getMessage());
        }
    }
    
    /**
     * getEncodings() method testing.
     */
    public void testGetEncodings() {
        try {
            Iterator it = certPath.getEncodings();
            Object encoding  = it.next();
            assertNotNull("Default encodings should not be null", encoding);
            it.remove();
            fail("UnsupportedOperationException should be thrown");
        } catch (UnsupportedOperationException e) {
            // pass
        }
    }
    
    public static Test suite() {
        return new TestSuite(X509CertPathImplTest.class);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}

