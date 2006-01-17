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

import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertPath;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.harmony.security.asn1.ASN1Any;
import org.apache.harmony.security.asn1.ASN1SequenceOf;
import org.apache.harmony.security.asn1.BerInputStream;
import org.apache.harmony.security.pkcs7.ContentInfo;
import org.apache.harmony.security.pkcs7.SignedData;

import com.openintel.drl.security.x509.Certificate;
import com.openintel.drl.security.x509.Extensions;

/**
 * X509CertPathImpl
 */
public class X509CertPathImpl extends CertPath {
 
    // supported encoding types:
    public static final int PKI_PATH = 0;
    public static final int PKCS7 = 1;
    
    // supported encoding names:
    private static final String[] encodingsArr = 
                                        new String[] {"PkiPath", "PKCS7"};
    static final List encodings = Collections.unmodifiableList(
                                            Arrays.asList(encodingsArr));;
    private final List certificates;
    private byte[] pkiPathEncoding;
    private byte[] pkcs7Encoding;
    
    public X509CertPathImpl(List certs) throws CertificateException {
        super("X.509");
        // if (certs == null) {
        //     throw new CertificateException(
        //             "Provided list of certificates provided is null.");
        // }
        // throw NullPointerException:
        int size = certs.size();
        certificates = new ArrayList(size);
        for (int i=0; i<size; i++) {
            Object cert = certs.get(i);
            if (!(cert instanceof X509Certificate) ) {
                //    && ("X.509".equals(((X509Certificate) cert).getType()))) ) {
                throw new CertificateException(
                        "One of provided certificates is not X509 certificate");
            }
            certificates.add(cert);
        }
    }

    private X509CertPathImpl(List certs, int type, byte[] encoding) {
        super("X.509");
        if (type == PKI_PATH) {
            this.pkiPathEncoding = encoding;
        } else { // PKCS7
            this.pkcs7Encoding = encoding;
        }
        // We do not need the type check and list cloning here, 
        // because it has been done during decoding.
        certificates = certs;
    }

    public static X509CertPathImpl getInstance(InputStream in) 
                                        throws CertificateException {
        try {
            return (X509CertPathImpl) ASN1.decode(in);
        } catch (IOException e) {
            throw new CertificateException("Incorrect encoded form: "
                    + e.getMessage());
        }
    }

    public static X509CertPathImpl getInstance(InputStream in, String encoding) 
        throws CertificateException {
        if (!encodings.contains(encoding)) {
            throw new CertificateException(
                    "Unsupported encoding: "+encoding);
        }
        try {
            if (encodingsArr[0].equals(encoding)) {
                return (X509CertPathImpl) ASN1.decode(in);
            } else {
                ContentInfo ci = (ContentInfo) ContentInfo.ASN1.decode(in);
                SignedData sd = ci.getSignedData();
                if (sd == null) {
                    throw new CertificateException(
                            "Incorrect PKCS7 encoded form: missing signed data");
                }
                List certs = sd.getCertificates();
                if (certs == null) {
                    certs = new ArrayList();
                }
                return new X509CertPathImpl(certs, PKCS7, ci.getEncoded());
            }
        } catch (IOException e) {
            throw new CertificateException("Incorrect encoded form: "
                    + e.getMessage());
        }
    }

    public static X509CertPathImpl getInstance(byte[] in) 
                                        throws CertificateException {
        try {
            return (X509CertPathImpl) ASN1.decode(in);
        } catch (IOException e) {
            throw new CertificateException("Incorrect encoded form: "
                    + e.getMessage());
        }
    }

    public static X509CertPathImpl getInstance(byte[] in, String encoding) 
        throws CertificateException {
        if (!encodings.contains(encoding)) {
            throw new CertificateException(
                    "Unsupported encoding: "+encoding);
        }
        try {
            if (encodingsArr[0].equals(encoding)) {
                return (X509CertPathImpl) ASN1.decode(in);
            } else { // PKCS7
                ContentInfo ci = (ContentInfo) ContentInfo.ASN1.decode(in);
                SignedData sd = ci.getSignedData();
                if (sd == null) {
                    throw new CertificateException(
                            "Incorrect PKCS7 encoded form: missing signed data");
                }
                List certs = sd.getCertificates();
                if (certs == null) {
                    certs = new ArrayList();
                }
                return new X509CertPathImpl(certs, PKCS7, ci.getEncoded());
            }
        } catch (IOException e) {
            throw new CertificateException("Incorrect encoded form: "
                    + e.getMessage());
        }
    }

    /**
     * getCertificates
     */
    public List getCertificates() {
        return Collections.unmodifiableList(certificates);
    }

    /**
     * getEncoded
     */
    public byte[] getEncoded() throws CertificateEncodingException {
        if (pkiPathEncoding == null) {
            pkiPathEncoding = ASN1.encode(this);
        }
        byte[] result = new byte[pkiPathEncoding.length];
        System.arraycopy(pkiPathEncoding, 0, result, 0, pkiPathEncoding.length);
        return result;
    }

    /**
     * getEncoded
     */
    public byte[] getEncoded(String encoding)
        throws CertificateEncodingException {
        if (!encodings.contains(encoding)) {
            throw new CertificateEncodingException(
                    "Unsupported encoding: "+encoding);
        }
        if (encodingsArr[0].equals(encoding)) {
            return getEncoded();
        } else {
            // FIXME: PKCS7 encoding support
            // PKCS7 encoded form:
            if (pkcs7Encoding == null) {
                SignedData sd = new SignedData(1, new ArrayList(), 
                        new ContentInfo(ContentInfo.DATA, null), certificates,
                        null, new ArrayList());
                ContentInfo ci = new ContentInfo(ContentInfo.SIGNED_DATA, sd);
                pkcs7Encoding = ci.getEncoded();
            }
            byte[] result = new byte[pkiPathEncoding.length];
            System.arraycopy(pkcs7Encoding, 0, result, 0, 
                                        pkcs7Encoding.length);
            return result;
        }
    }

    /**
     * getEncodings
     */
    public Iterator getEncodings() {
        return encodings.iterator();
    }

    public static ASN1SequenceOf ASN1 = new ASN1SequenceOf(ASN1Any.getInstance()) {
   
        public Object getDecodedObject(BerInputStream in) throws IOException {
            List encodings = (List) in.content;
            int size = encodings.size();
            List certificates = new ArrayList(size);
            for (int i=0; i<size; i++) {
                certificates.add(
                        new X509CertImpl((Certificate) 
                            Certificate.ASN1.decode((byte[]) encodings.get(i))));
            }
            return new X509CertPathImpl(certificates, PKI_PATH, in.getEncoded());
        }

        public Collection getValues(Object object) {
            X509CertPathImpl cp = (X509CertPathImpl) object;
            if (cp.certificates == null) {
                return new ArrayList();
            }
            int size = cp.certificates.size();
            List encodings = new ArrayList(size);
            try {
                for (int i=0; i<size; i++) {
                    encodings.add(((X509Certificate) 
                                cp.certificates.get(i)).getEncoded());
                }
            } catch (CertificateEncodingException e) {
                throw new IllegalArgumentException("Encoding Error occured");
            }
            return encodings;
        }
    };
}

