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

package org.apache.harmony.security.provider.cert;


import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CRL;
import java.security.cert.CRLException;
import java.security.cert.CertPath;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactorySpi;
import java.security.cert.X509CRL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.harmony.luni.util.Base64;
import org.apache.harmony.security.asn1.BerInputStream;

/**
 * X509CertFactoryImpl
 */
public class X509CertFactoryImpl extends CertificateFactorySpi {

    private static Cache CERT_CASHE = new Cache();
    private static Cache CRL_CASHE = new Cache(24);
    
    public X509CertFactoryImpl() {}

    /**
     * engineGenerateCertificate
     */
    public Certificate engineGenerateCertificate(InputStream inStream)
            throws CertificateException {
        try {
            if (inStream.markSupported()) {
            } else {
                inStream = new RestoringInputStream(inStream);
            }
            inStream.mark(32);
            byte[] buff = new byte[28];
            if (inStream.read(buff) < 28) {
                throw new CertificateException(
                        "Input Stream contains not enought data.");
            }
            if ("-----BEGIN CERTIFICATE-----".equals(new String(buff, 0, 27))) {
                int size = inStream.available();
                if (size == 0) {
                    size = 2048;
                }
                buff = new byte[size];
                int index=0, ch;
                while ((ch = inStream.read()) != '-') {
                    if (ch == -1) {
                        throw new CertificateException(
                                "Incorrect Base64 encoding: unexpected EOF.");
                    }
                    buff[index++] = (byte) ch;
                    if (index == size) {
                        byte[] newbuff = new byte[size+1024];
                        System.arraycopy(buff, 0, newbuff, 0, size);
                        buff = newbuff;
                    }
                }
                byte[] tmp = new byte[25];
                inStream.read(tmp);
                if (!new String(tmp).startsWith("----END CERTIFICATE-----")) {
                    throw new CertificateException(
                    "Incorrect Base64 encoding: 'END CERTIFICATE' expected.");
                }
                // skip new line: set the position to the next certificate:
                inStream.mark(1);
                while (((ch = inStream.read()) != -1) && (ch == '\n' || ch == '\r')) {
                    inStream.mark(1);
                }
                inStream.reset();
                buff = Base64.decode(buff, index);
                if (buff == null) {
                    throw new CertificateException("Incorrect Base64 encoding.");
                }
                long hash = CERT_CASHE.getHash(buff);
                if (CERT_CASHE.contains(hash)) {
                    Certificate res = (Certificate) CERT_CASHE.get(hash, buff);
                    if (res != null) {
                        return res;
                    }
                }
                Certificate res = new X509CertImpl(buff);
                CERT_CASHE.put(hash, buff, res);
                return res;
            } else {
                inStream.reset();
                long hash = CERT_CASHE.getHash(buff);
                if (CERT_CASHE.contains(hash)) {
                    byte[] encoding = new byte[BerInputStream.getLength(buff)];
                    inStream.read(encoding);
                    Certificate res = 
                        (Certificate) CERT_CASHE.get(hash, encoding);
                    if (res != null) {
                        return res;
                    }
                    res = new X509CertImpl(encoding);
                    CERT_CASHE.put(hash, encoding, res);
                    return res;
                } else {
                    Certificate res = new X509CertImpl(inStream);
                    CERT_CASHE.put(hash, res.getEncoded(), res);
                    return res;
                }
            }
        } catch (IOException e) {
            throw new CertificateException(e);
        }
    }
    
    /**
     * engineGenerateCertificates
     * FIXME: 1.5 updates are needed Collection <? extends Certificate>
     */
    public Collection engineGenerateCertificates(InputStream inStream)
            throws CertificateException {
        ArrayList result = new ArrayList();
        try {
            if (inStream.markSupported()) {
            } else {
                inStream = new RestoringInputStream(inStream);
            }
            inStream.mark(1);
            // FIXME: Check if it is a PKCS7 structure, if not, do following:
            while (inStream.read() != -1) {
                inStream.reset();
                result.add(engineGenerateCertificate(inStream));
                inStream.mark(1);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new CertificateException(e);
        }
        return result;
    }

    /**
     * engineGenerateCRL
     */
    public CRL engineGenerateCRL(InputStream inStream)
            throws CRLException {
        try {
            if (inStream.markSupported()) {
            } else {
                inStream = new RestoringInputStream(inStream);
            }
            inStream.mark(32);
            byte[] buff = new byte[25]; // take one byte for new line 
            if (inStream.read(buff) < 25) {
                throw new CRLException(
                        "Input Stream contains not enought data.");
            }
            if ("-----BEGIN X509 CRL-----".equals(new String(buff, 0, 24))) {
                int size = inStream.available();
                if (size == 0) {
                    size = 1024;
                }
                buff = new byte[size];
                int index=0, ch;
                while ((ch = inStream.read()) != '-') {
                    if (ch == -1) {
                        throw new CRLException(
                                "Incorrect Base64 encoding: unexpected EOF.");
                    }
                    buff[index++] = (byte) ch;
                    if (index == size) {
                        byte[] newbuff = new byte[size+1024];
                        System.arraycopy(buff, 0, newbuff, 0, size);
                        buff = newbuff;
                    }
                }
                byte[] tmp = new byte[21];
                inStream.read(tmp);
                if (!new String(tmp).startsWith("----END X509 CRL-----")) {
                    throw new CRLException(
                    "Incorrect Base64 encoding: 'END X509 CRL' expected.");
                }
                // skip new line: set the position to the next certificate:
                inStream.mark(1);
                while (((ch = inStream.read()) != -1) && (ch == '\n' || ch == '\r')) {
                    inStream.mark(1);
                }
                inStream.reset();
                buff = Base64.decode(buff, index);
                if (buff == null) {
                    throw new CRLException("Incorrect Base64 encoding.");
                }
                long hash = CRL_CASHE.getHash(buff);
                if (CRL_CASHE.contains(hash)) {
                    X509CRL res = (X509CRL) CRL_CASHE.get(hash, buff);
                    if (res != null) {
                        return res;
                    }
                }
                X509CRL res = new X509CRLImpl(buff);
                CRL_CASHE.put(hash, buff, res);
                return res;
            } else {
                inStream.reset();
                long hash = CRL_CASHE.getHash(buff);
                if (CRL_CASHE.contains(hash)) {
                    byte[] encoding = new byte[BerInputStream.getLength(buff)];
                    inStream.read(encoding);
                    CRL res = 
                        (CRL) CRL_CASHE.get(hash, encoding);
                    if (res != null) {
                        return res;
                    }
                    res = new X509CRLImpl(encoding);
                    CRL_CASHE.put(hash, encoding, res);
                    return res;
                } else {
                    X509CRL res = new X509CRLImpl(inStream);
                    CRL_CASHE.put(hash, res.getEncoded(), res);
                    return res;
                }
            }
        } catch (IOException e) {
            throw new CRLException(e);
        }
    }

    /**
     * engineGenerateCRLs
     * FIXME: 1.5 updates are needed Collection <? extends CRL>
     */
    public Collection engineGenerateCRLs(InputStream inStream)
            throws CRLException {
        if (inStream == null) {
            throw new CRLException("Null input stream provided.");
        }
        ArrayList result = new ArrayList();
        try {
            if (inStream.markSupported()) {
            } else {
                inStream = new RestoringInputStream(inStream);
            }
            inStream.mark(1);
            // FIXME: Check if it is a PKCS7 structure, if not, do following:
            while (inStream.read() != -1) {
                inStream.reset();
                result.add(engineGenerateCRL(inStream));
                inStream.mark(1);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new CRLException(e);
        }
        return result;
    }

    /**
     * engineGenerateCertPath
     */
    public CertPath engineGenerateCertPath(InputStream inStream)
            throws CertificateException {
        return X509CertPathImpl.getInstance(inStream);
    }

    /**
     * engineGenerateCertPath
     */
    public CertPath engineGenerateCertPath(InputStream inStream, String encoding)
            throws CertificateException {
        return X509CertPathImpl.getInstance(inStream, encoding);
    }

    /**
     * engineGenerateCertPath
     */
    public CertPath engineGenerateCertPath(List certificates)
            throws CertificateException {
        return new X509CertPathImpl(certificates);
    }

    /**
     * engineGetCertPathEncodings
     * FIXME: 1.5 updates are needed Iterator <String>
     */
    public Iterator engineGetCertPathEncodings() {
        return X509CertPathImpl.encodings.iterator();
    }

    /**
     * Class represents the stream which supports reset to the
     * marked state with readlimit == BUFF_SIZE.
     */
    private static class RestoringInputStream extends InputStream {

        private final InputStream inStream;
        private final static int BUFF_SIZE = 32;
        private final int[] buff = new int[BUFF_SIZE*2];
        // position in the buffer
        private int pos = -1;
        // the last byte in the buffer
        private int bar = 0;
        // the last cell of the buffer
        private int end = 0;
        
        public RestoringInputStream(InputStream inStream) {
            this.inStream = inStream;
        }

        public int available() throws IOException {
            return (bar - pos) + inStream.available();
        }

        public void close() throws IOException {
            inStream.close();
        }

        public void mark(int readlimit) {
            if (pos < 0) {
                pos = 0;
                bar = 0;
                end = BUFF_SIZE - 1;
            } else {
                end = (pos + BUFF_SIZE - 1) % BUFF_SIZE;
            }
        }

        public boolean markSupported() {
            return true;
        }

        public int read() throws IOException {
            if (pos >= 0) {
                int cur = pos % BUFF_SIZE;
                if (cur < bar) {
                    pos++;
                    return buff[cur];
                }
                if (cur != end) {
                    buff[cur] = inStream.read();
                    bar = cur+1;
                    pos++;
                    return buff[cur];
                } else {
                    pos = -1; // can not operate anymore
                }
            }
            return inStream.read();
        }

        public int read(byte[] b) throws IOException {
            return read(b, 0, b.length);
        }

        public int read(byte[] b, int off, int len) throws IOException {
            int read_b;
            int i;
            for (i=0; i<len; i++) {
                if ((read_b = read()) == -1) {
                    return (i == 0) ? -1 : i; 
                }
                b[off+i] = (byte) read_b;
            }
            return i;
        }

        public void reset() throws IOException {
            if (pos >= 0) {
                pos = (end + 1) % BUFF_SIZE;
            } else {
                throw new IOException("Could not reset the stream: "
                    + "position became invalid or stream has not been marked.");
            }
        }

        public long skip(long n) throws IOException {
            if (pos >= 0) {
                long i = 0;
                int av = available();
                if (av < n) {
                    n = av;
                }
                while ((i < n) && (read() != -1)) {
                    i++;
                }
                return i;
            } else {
                return inStream.skip(n);
            }
        }
    }
}

