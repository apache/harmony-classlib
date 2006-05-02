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
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.security.auth.x500.X500Principal;

import org.apache.harmony.crypto.utils.AlgNameMapper;
import org.apache.harmony.security.x509.Certificate;
import org.apache.harmony.security.x509.Extension;
import org.apache.harmony.security.x509.Extensions;
import org.apache.harmony.security.x509.TBSCertificate;

/**
 * X509CertImpl
 */
public class X509CertImpl extends X509Certificate {
    
    private final Certificate certificate;
    private final TBSCertificate tbsCert;
    private final Extensions extensions;
    
    // cached values
    private long notBefore = -1;
    private long notAfter;
    private BigInteger serialNumber;
    private X500Principal issuer;
    private X500Principal subject;
    private byte[] tbsCertificate;
    private byte[] signature;
    private String sigAlgName;
    private String sigAlgOID;
    private byte[] sigAlgParams;
    private boolean nullSigAlgParams;
    //Values are taken directly from tbsCerttificate:
    // private boolean[] issuerUniqueID;
    // private boolean[] subjectUniqueID;
    private PublicKey publicKey;
    
    private byte[] encoding;
    
    public X509CertImpl(InputStream in) throws CertificateException {
        try {
            this.certificate = (Certificate) Certificate.ASN1.decode(in);
            this.tbsCert = certificate.getTbsCertificate();
            this.extensions = tbsCert.getExtensions();
        } catch (IOException e) {
            throw new CertificateException(e);
        }
    }
    
    public X509CertImpl(Certificate certificate) {
        this.certificate = certificate;
        this.tbsCert = certificate.getTbsCertificate();
        this.extensions = tbsCert.getExtensions();
    }

    public X509CertImpl(byte[] encoding) throws IOException {
        this((Certificate) Certificate.ASN1.decode(encoding)); 
    }


    // 
    // ----------------- Public methods implementations ------------------
    //

    public void checkValidity() throws CertificateExpiredException,
                                       CertificateNotYetValidException {
        if (notBefore == -1) {
            notBefore = tbsCert.getValidity().getNotBefore().getTime();
            notAfter = tbsCert.getValidity().getNotAfter().getTime();
        }
        long time = System.currentTimeMillis();
        if (time < notBefore) {
            throw new CertificateNotYetValidException();
        }
        if (time > notAfter) {
            throw new CertificateExpiredException();
        }
    }

    public void checkValidity(Date date) 
                                throws CertificateExpiredException, 
                                       CertificateNotYetValidException {
        if (notBefore == -1) {
            notBefore = tbsCert.getValidity().getNotBefore().getTime();
            notAfter = tbsCert.getValidity().getNotAfter().getTime();
        }
        long time = date.getTime();
        if (time < notBefore) {
            throw new CertificateNotYetValidException();
        }
        if (time > notAfter) {
            throw new CertificateExpiredException();
        }
    }
    
    public int getVersion() {
        return tbsCert.getVersion() + 1;
    }

    public BigInteger getSerialNumber() {
        if (serialNumber == null) {
            serialNumber = tbsCert.getSerialNumber();
        }
        return serialNumber;
    }

    public Principal getIssuerDN() {
        if (issuer == null) {
            issuer = new X500Principal(
                    tbsCert.getIssuer().getName(X500Principal.RFC2253));
        }
        return issuer;
    }

    public X500Principal getIssuerX500Principal() {
        if (issuer == null) {
            issuer = new X500Principal(
                    tbsCert.getIssuer().getName(X500Principal.RFC2253));
        }
        return issuer;
    }
        
    public Principal getSubjectDN() {
        if (subject == null) {
            subject = new X500Principal(
                    tbsCert.getSubject().getName(X500Principal.RFC2253));
        }
        return subject;
    }

    public X500Principal getSubjectX500Principal() {
        if (subject == null) {
            subject = new X500Principal(
                    tbsCert.getSubject().getName(X500Principal.RFC2253));
        }
        return subject;
    }

    public Date getNotBefore() {
        if (notBefore == -1) {
            notBefore = tbsCert.getValidity().getNotBefore().getTime();
            notAfter = tbsCert.getValidity().getNotAfter().getTime();
        }
        return new Date(notBefore);
    }

    public Date getNotAfter() {
        if (notBefore == -1) {
            notBefore = tbsCert.getValidity().getNotBefore().getTime();
            notAfter = tbsCert.getValidity().getNotAfter().getTime();
        }
        return new Date(notAfter);
    }

    public byte[] getTBSCertificate()
                        throws CertificateEncodingException
    {
        if (tbsCertificate == null) {
            tbsCertificate = tbsCert.getEncoded();
        }
        byte[] result = new byte[tbsCertificate.length];
        System.arraycopy(tbsCertificate, 0, result, 0, tbsCertificate.length);
        return result;
    }

    public byte[] getSignature() {
        if (signature == null) {
            signature = certificate.getSignatureValue();
        }
        byte[] result = new byte[signature.length];
        System.arraycopy(signature, 0, result, 0, signature.length);
        return result;
    }

    public String getSigAlgName() {
        if (sigAlgOID == null) {
            sigAlgOID = tbsCert.getSignature().getAlgorithm();
            sigAlgName = AlgNameMapper.map2AlgName(sigAlgOID);
            if (sigAlgName == null) {
                sigAlgName = sigAlgOID;
            }
        }
        return sigAlgName;
    }

    public String getSigAlgOID() {
        if (sigAlgOID == null) {
            sigAlgOID = tbsCert.getSignature().getAlgorithm();
            sigAlgName = AlgNameMapper.map2AlgName(sigAlgOID);
            if (sigAlgName == null) {
                sigAlgName = sigAlgOID;
            }
        }
        return sigAlgOID;
    }

    public byte[] getSigAlgParams() {
        if (nullSigAlgParams) {
            return null;
        }
        if (sigAlgParams == null) {
            sigAlgParams = tbsCert.getSignature().getParameters();
            if (sigAlgParams == null) {
                nullSigAlgParams = true;
                return null;
            }
        }
        return sigAlgParams;
    }

    /**
     * @return
     */
    public boolean[] getIssuerUniqueID() {
        return tbsCert.getIssuerUniqueID();
    }

    /**
     * @return
     */
    public boolean[] getSubjectUniqueID() {
        return tbsCert.getSubjectUniqueID();
    }

    /**
     * @return
     */
    public boolean[] getKeyUsage() {
        if (extensions == null) {
            return null;
        }
        return extensions.valueOfKeyUsage();
    }

    public List/*<String>*/ getExtendedKeyUsage()
                                throws CertificateParsingException {
        if (extensions == null) {
            return null;
        }
        try {
            return extensions.valueOfExtendedKeyUsage();
        } catch (IOException e) {
            throw new CertificateParsingException(e);
        }
    }

    public int getBasicConstraints() {
        if (extensions == null) {
            return Integer.MAX_VALUE;
        }
        return extensions.valueOfBasicConstrains();
    }

    public Collection/*<List<?>>*/ getSubjectAlternativeNames()
                                throws CertificateParsingException {
        if (extensions == null) {
            return null;
        }
        try {
            return extensions.valueOfSubjectAlternativeName();
        } catch (IOException e) {
            throw new CertificateParsingException(e);
        }
    }

    public Collection/*FIXME <List<?>>*/ getIssuerAlternativeNames()
                                throws CertificateParsingException {
        if (extensions == null) {
            return null;
        }
        try {
            return extensions.valueOfIssuerAlternativeName();
        } catch (IOException e) {
            throw new CertificateParsingException(e);
        }
    }

    // 
    // ----- java.security.cert.Certificate methods implementations ------
    //
    
    public byte[] getEncoded() throws CertificateEncodingException
    {
        if (encoding == null) {
            encoding = certificate.getEncoded();
        }
        byte[] result = new byte[encoding.length];
        System.arraycopy(encoding, 0, result, 0, encoding.length);
        return result;
    }

    public PublicKey getPublicKey() {
        if (publicKey == null) {
            publicKey = tbsCert.getSubjectPublicKeyInfo().getPublicKey();
        }
        return publicKey;
    }

    /**
     * TODO: should be fully implemented.
     * @return
     */
    public String toString() {
        return certificate.toString();
    }
    
    /**
     * TODO
     * @param   key:    PublicKey
     * @return
     * @throwsCertificateException
     * @throwsNoSuchAlgorithmException
     * @throwsInvalidKeyException
     * @throwsNoSuchProviderException
     * @throwsSignatureException    
     */
    public void verify(PublicKey key)
                         throws CertificateException, NoSuchAlgorithmException,
                                InvalidKeyException, NoSuchProviderException,
                                SignatureException
    {
        Signature signature = Signature.getInstance(
                                tbsCert.getSignature().getAlgorithm());
        signature.initVerify(key);
        byte[] tbsCertEncoding = tbsCert.getEncoded();
        signature.update(tbsCertEncoding, 0, tbsCertEncoding.length);
        if (!signature.verify(certificate.getSignatureValue())) {
            throw new SignatureException("Signature was not verified.");
        }
    }

    /**
     * TODO
     * @param   key:    PublicKey
     * @param   sigProvider:    String
     * @return
     * @throwsCertificateException
     * @throwsNoSuchAlgorithmException
     * @throwsInvalidKeyException
     * @throwsNoSuchProviderException
     * @throwsSignatureException    
     */
    public void verify(PublicKey key, String sigProvider)
                         throws CertificateException, NoSuchAlgorithmException,
                                InvalidKeyException, NoSuchProviderException,
                                SignatureException
    {
        Signature signature = Signature.getInstance(
                    tbsCert.getSignature().getAlgorithm(), sigProvider);
        signature.initVerify(key);
        byte[] tbsCertEncoding = tbsCert.getEncoded();
        signature.update(tbsCertEncoding, 0, tbsCertEncoding.length);
        if (!signature.verify(certificate.getSignatureValue())) {
            throw new SignatureException("Signature was not verified.");
        }
    }

    // 
    // ----- java.security.cert.X509Extension methods implementations ----
    //

    public Set getNonCriticalExtensionOIDs() {
        if (extensions == null) {
            return null;
        }
        return extensions.getNonCriticalExtensions();
    }

    public Set getCriticalExtensionOIDs() {
        if (extensions == null) {
            return null;
        }
        return extensions.getCriticalExtensions();
    }

    public byte[] getExtensionValue(String oid) {
        if (extensions == null) {
            return null;
        }
        Extension ext = extensions.getExtensionByOID(oid);
        return (ext == null) ? null : ext.getRawExtnValue();
    }

    public boolean hasUnsupportedCriticalExtension() {
        if (extensions == null) {
            return false;
        }
        return extensions.hasUnsupportedCritical();
    }

}

