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
import java.security.cert.CRL;
import java.security.cert.CRLException;
import java.security.cert.CertPath;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.harmony.security.pkcs7.ContentInfo;
import org.apache.harmony.security.pkcs7.SignedData;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @com.intel.drl.spec_ref
 */
public class X509CertFactoryImplTest extends TestCase {


    /**
     * engineGenerateCertificate(InputStream inStream) method testing.
     */
    public void testEngineGenerateCertificate() {
        X509CertFactoryImpl certFactory = new X509CertFactoryImpl();
        Certificate cert;
        
        // DER encoded certificate generation testing
        ByteArrayInputStream bais = 
            new ByteArrayInputStream(
                    CertFactoryTestData.getCertEncoding());
        try {
            cert = certFactory.engineGenerateCertificate(bais);
            assertNotNull("First generated certificate is null", cert);
            cert = certFactory.engineGenerateCertificate(bais);
            assertNotNull("Second generated certificate is null", cert);
        } catch (CertificateException e) {
            e.printStackTrace();
            fail("Unexpected CertificateException: " + e.getMessage());
        }
        
        try {
            certFactory.engineGenerateCertificate(bais);
            fail("Expected CertificateException was not thrown.");
        } catch (CertificateException e) {
        }

        // Base64 testing
        bais = new ByteArrayInputStream(
                CertFactoryTestData.getBase64CertEncoding());
        try {
            cert = certFactory.engineGenerateCertificate(bais);
            assertNotNull("First generated certificate is null", cert);
            cert = certFactory.engineGenerateCertificate(bais);
            assertNotNull("Second generated certificate is null", cert);
        } catch (CertificateException e) {
            e.printStackTrace();
            fail("Unexpected CertificateException: " + e.getMessage());
        }
        
        try {
            certFactory.engineGenerateCertificate(bais);
            fail("Expected CertificateException was not thrown.");
        } catch (CertificateException e) {
        }
    }
    
    /**
     * engineGenerateCertificates(InputStream inStream) method testing.
     */
    public void testEngineGenerateCertificates() {
        X509CertFactoryImpl certFactory = new X509CertFactoryImpl();
        Certificate cert;
        
        // DER encoded certificate generation testing
        ByteArrayInputStream bais = 
            new ByteArrayInputStream(
                    CertFactoryTestData.getCertEncoding());
        try {
            assertTrue("The size of collection is not correct",
                    certFactory.engineGenerateCertificates(bais).size() == 2);
        } catch (CertificateException e) {
            e.printStackTrace();
            fail("Unexpected CertificateException: " + e.getMessage());
        }
        
        // Base64 testing
        bais = new ByteArrayInputStream(
                CertFactoryTestData.getBase64CertEncoding());
        try {
            assertTrue("The size of collection is not correct",
                    certFactory.engineGenerateCertificates(bais).size() == 2);
        } catch (CertificateException e) {
            e.printStackTrace();
            fail("Unexpected CertificateException: " + e.getMessage());
        }
        
    }
    
    /**
     * engineGenerateCRL(InputStream inStream) method testing.
     */
    public void testEngineGenerateCRL() {
        X509CertFactoryImpl certFactory = new X509CertFactoryImpl();
        CRL crl;
        
        // DER encoded crt generation testing
        ByteArrayInputStream bais = 
            new ByteArrayInputStream(
                    CertFactoryTestData.getCRLEncoding());
        try {
            crl = certFactory.engineGenerateCRL(bais);
            assertNotNull("First generated certificate is null", crl);
            crl = certFactory.engineGenerateCRL(bais);
            assertNotNull("Second generated certificate is null", crl);
        } catch (CRLException e) {
            e.printStackTrace();
            fail("Unexpected CRLException: " + e.getMessage());
        }
        
        try {
            certFactory.engineGenerateCRL(bais);
            fail("Expected CRLException was not thrown.");
        } catch (CRLException e) {
        }
    }
    
    /**
     * engineGenerateCRLs(InputStream inStream) method testing.
     */
    public void testEngineGenerateCRLs() {
        X509CertFactoryImpl certFactory = new X509CertFactoryImpl();
        CRL crl;
        
        // DER encoded crt generation testing
        ByteArrayInputStream bais = 
            new ByteArrayInputStream(
                    CertFactoryTestData.getCRLEncoding());
        try {
            assertTrue("The size of collection is not correct",
                    certFactory.engineGenerateCRLs(bais).size() == 2);
        } catch (CRLException e) {
            e.printStackTrace();
            fail("Unexpected CRLException: " + e.getMessage());
        }
    }
    
    /**
     * engineGenerateCertPath(InputStream inStream) method testing.
     */
    public void testEngineGenerateCertPath() {
        X509CertFactoryImpl certFactory = new X509CertFactoryImpl();
        ByteArrayInputStream bais = 
                new ByteArrayInputStream(
                        CertFactoryTestData.getCertPathPkiPathEncoding());
        try {
            certFactory.engineGenerateCertPath(bais);
        } catch (CertificateException e) {
            e.printStackTrace();
            fail("Unexpected CertificateException was thrown");
        }

        try {
            certFactory.engineGenerateCertPath(bais);
            fail("Expected CertificateException was not thrown.");
        } catch (CertificateException e) {
        }
    }
    
    /**
     * engineGenerateCertPath(InputStream inStream, String encoding) method
     * testing.
     */
    public void testEngineGenerateCertPath1() {
        X509CertFactoryImpl certFactory = new X509CertFactoryImpl();
        ByteArrayInputStream bais = 
                new ByteArrayInputStream(
                        CertFactoryTestData.getCertPathPKCS7Encoding());
        try {
            certFactory.engineGenerateCertPath(bais, "PKCS7");
        } catch (CertificateException e) {
            e.printStackTrace();
            fail("Unexpected CertificateException was thrown");
        }

        try {
            certFactory.engineGenerateCertPath(bais, "PKCS7");
            fail("Expected CertificateException was not thrown.");
        } catch (CertificateException e) {
        }
    }
    
    /**
     * engineGenerateCertPath(List certificates) method testing.
     */
    public void testEngineGenerateCertPath2() {
        X509Certificate certificate = null;
        try {
            X509CertImplTest test = new X509CertImplTest();
            test.setUp();
            certificate = test.certificate;
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unexpected Exception was thrown: "+e.getMessage());
        }
        ArrayList certList = new ArrayList();
        for (int i=0; i<2; i++) {
            certList.add(certificate);
        }
        X509CertFactoryImpl certFactory = null;
        try {
            certFactory = new X509CertFactoryImpl();
            certFactory.engineGenerateCertPath(certList);
        } catch (CertificateException e) {
            e.printStackTrace();
            fail("Unexpected CertificateException was thrown: "+e.getMessage());
        }
        certList.add(new Integer(5));
        try {
            certFactory.engineGenerateCertPath(certList);
            fail("Expected CertificateException was not thrown.");
        } catch (CertificateException e) {
        }
    }
    
    /**
     * engineGetCertPathEncodings() method testing.
     */
    public void testEngineGetCertPathEncodings() {
        try {
            Iterator it = 
                new X509CertFactoryImpl().engineGetCertPathEncodings();
            Object encoding  = it.next();
            assertNotNull("Default encodings should not be null", encoding);
            it.remove();
            fail("UnsupportedOperationException should be thrown");
        } catch (UnsupportedOperationException e) {
            // pass
        }
    }
    
    public static Test suite() {
        return new TestSuite(X509CertFactoryImplTest.class);
    }

    public static void main(String[] args) {
        /*
        X509CertFactoryImplTest test = new X509CertFactoryImplTest();
        long startTime = System.currentTimeMillis();
        for (int i=0; i<10000; i++) {
            test.testEngineGenerateCertificate();
        }
        System.out.println("time: "+(System.currentTimeMillis() - startTime));
        */
        junit.textui.TestRunner.run(suite());
    }
}

