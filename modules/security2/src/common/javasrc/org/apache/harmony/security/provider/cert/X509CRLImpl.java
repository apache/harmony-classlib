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

import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.security.cert.Certificate;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CRL;
import java.security.cert.CRLException;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509Extension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import javax.security.auth.x500.X500Principal;
import com.openintel.drlx.crypto.utils.AlgNameMapper;

import java.io.InputStream;
import java.io.IOException;
import java.security.Signature;

import com.openintel.drl.security.x509.CertificateList;
import com.openintel.drl.security.x509.Extensions;
import com.openintel.drl.security.x509.TBSCertList;

/**
 * X509CRLImpl
 */
public class X509CRLImpl extends X509CRL {
    
    private final CertificateList crl;
    private final TBSCertList tbsCertList;
    private final Extensions extensions;

    private boolean isIndirectCRL;
    // cached values
    private X500Principal issuer;
    private byte[] encoding;
    private byte[] tbsCertListEncoding;
    private ArrayList entries;
    private int entriesSize;
    private int nonIndirectEntriesSize;
    private boolean entriesRetrieved;
    private byte[] signature;
    private String sigAlgOID;
    private String sigAlgName;
    private byte[] sigAlgParams;
    private boolean nullSigAlgParams;
    
    public X509CRLImpl(CertificateList crl) {
        this.crl = crl;
        this.tbsCertList = crl.getTbsCertList();
        this.extensions = tbsCertList.getCrlExtensions();
    }

    public X509CRLImpl(InputStream in) throws CRLException {
        try {
            this.crl = (CertificateList) CertificateList.ASN1.decode(in);
            this.tbsCertList = crl.getTbsCertList();
            this.extensions = tbsCertList.getCrlExtensions();
        } catch (IOException e) {
            throw new CRLException(e);
        }
    }

    public X509CRLImpl(byte[] encoding) throws IOException {
        this((CertificateList) CertificateList.ASN1.decode(encoding)); 
    }

    /**
     * getEncoded
     */
    public byte[] getEncoded() throws CRLException {
        if (encoding == null) {
            encoding = crl.getEncoded();
        }
        byte[] result = new byte[encoding.length];
        System.arraycopy(encoding, 0, result, 0, encoding.length);
        return result;
    }

    /**
     * getVersion
     */
    public int getVersion() {
        return tbsCertList.getVersion();
    }

    /**
     * getIssuerDN
     */
    public Principal getIssuerDN() {
        if (issuer == null) {
            issuer = new X500Principal(
                    tbsCertList.getIssuer().getName(X500Principal.RFC2253));
        }
        return issuer;
    }

    /**
     * getIssuerX500Principal
     */
    public X500Principal getIssuerX500Principal() {
        if (issuer == null) {
            issuer = new X500Principal(
                    tbsCertList.getIssuer().getName(X500Principal.RFC2253));
        }
        return issuer;
    }

    /**
     * getThisUpdate
     */
    public Date getThisUpdate() {
        return tbsCertList.getThisUpdate();
    }

    /**
     * getNextUpdate
     */
    public Date getNextUpdate() {
        return tbsCertList.getNextUpdate();
    }

    // Retrieves the crl entries and converts it to the X509CRLEntryImpl
    // objects
    private void retirieveEntries() {
        entriesRetrieved = true;
        List rcerts = tbsCertList.getRevokedCertificates();
        if (rcerts == null) {
            return;
        }
        entriesSize = rcerts.size();
        entries = new ArrayList(entriesSize);
        X500Principal rcertIssuer = null; // means that issuer is a CRL issuer
        for (int i=0; i<entriesSize; i++) {
            TBSCertList.RevokedCertificate rcert = 
                (TBSCertList.RevokedCertificate) rcerts.get(i);
            X500Principal iss = rcert.getIssuer();
            if (iss != null) {
                rcertIssuer = iss;
                isIndirectCRL = true;
                nonIndirectEntriesSize = i;
            }
            entries.add(new X509CRLEntryImpl(rcert, rcertIssuer));
        }
    }
    
    /**
     * getRevokedCertificate
     */
    public X509CRLEntry getRevokedCertificate(X509Certificate certificate) {
        if (certificate == null) {
            throw new NullPointerException();
        }
        if (!entriesRetrieved) {
            retirieveEntries();
        }
        if (entries == null) {
            return null;
        }
        BigInteger serialN = certificate.getSerialNumber();
        if (isIndirectCRL) {
            X500Principal certIssuer = certificate.getIssuerX500Principal();
            if (certIssuer.equals(getIssuerX500Principal())) {
                certIssuer = null;
            }
            for (int i=0; i<entriesSize; i++) {
                X509CRLEntry entry = (X509CRLEntry) entries.get(i);
                if (serialN.equals(entry.getSerialNumber())) {
                    X500Principal iss = entry.getCertificateIssuer();
                    if (certIssuer != null) {
                        if (certIssuer.equals(iss)) {
                            //System.out.println("RETURN");
                            return entry;
                        }
                    } else if (iss == null) {
                        return entry;
                    }
                }
            }
        } else {
            for (int i=0; i<entriesSize; i++) {
                X509CRLEntry entry = (X509CRLEntry) entries.get(i);
                if (serialN.equals(entry.getSerialNumber())) {
                    return entry;
                }
            }
        }
        return null;
    }
        
    /**
     * getRevokedCertificate
     */
    public X509CRLEntry getRevokedCertificate(BigInteger serialNumber) {
        if (!entriesRetrieved) {
            retirieveEntries();
        }
        if (entries == null) {
            return null;
        }
        for (int i=0; i<nonIndirectEntriesSize; i++) {
            X509CRLEntry entry = (X509CRLEntry) entries.get(i);
            if (serialNumber.equals(entry.getSerialNumber())) {
                    return entry;
            }
        }
        return null;
    }

    /**
     * getRevokedCertificates
     */
    public Set/*<? extends X509CRLEntry>*/ getRevokedCertificates() {
        if (!entriesRetrieved) {
            retirieveEntries();
        }
        if (entries == null) {
            return null;
        }
        return new HashSet(entries);
    }

    /**
     * getTBSCertList
     */
    public byte[] getTBSCertList() throws CRLException {
        if (tbsCertListEncoding == null) {
            tbsCertListEncoding = tbsCertList.getEncoded();
        }
        byte[] result = new byte[tbsCertListEncoding.length];
        System.arraycopy(tbsCertListEncoding, 0, 
                result, 0, tbsCertListEncoding.length);
        return result;
    }

    /**
     * getSignature
     */
    public byte[] getSignature() {
        if (signature == null) {
            signature = crl.getSignatureValue();
        }
        byte[] result = new byte[signature.length];
        System.arraycopy(signature, 0, result, 0, signature.length);
        return result;
    }

    /**
     * getSigAlgName
     */
    public String getSigAlgName() {
        if (sigAlgOID == null) {
            sigAlgOID = tbsCertList.getSignature().getAlgorithm();
            sigAlgName = AlgNameMapper.map2AlgName(sigAlgOID);
            if (sigAlgName == null) {
                sigAlgName = sigAlgOID;
            }
        }
        return sigAlgName;
    }

    /**
     * getSigAlgOID
     */
    public String getSigAlgOID() {
        if (sigAlgOID == null) {
            sigAlgOID = tbsCertList.getSignature().getAlgorithm();
            sigAlgName = AlgNameMapper.map2AlgName(sigAlgOID);
            if (sigAlgName == null) {
                sigAlgName = sigAlgOID;
            }
        }
        return sigAlgOID;
    }

    /**
     * getSigAlgParams
     */
    public byte[] getSigAlgParams() {
        if (nullSigAlgParams) {
            return null;
        }
        if (sigAlgParams == null) {
            sigAlgParams = tbsCertList.getSignature().getParameters();
            if (sigAlgParams == null) {
                nullSigAlgParams = true;
                return null;
            }
        }
        return sigAlgParams;
    }

    /**
     * verify
     */
    public void verify(PublicKey key)
                     throws CRLException, NoSuchAlgorithmException,
                            InvalidKeyException, NoSuchProviderException,
                            SignatureException {
        Signature signature = Signature.getInstance(
                                tbsCertList.getSignature().getAlgorithm());
        signature.initVerify(key);
        byte[] tbsEncoding = tbsCertList.getEncoded();
        signature.update(tbsEncoding, 0, tbsEncoding.length);
        if (!signature.verify(crl.getSignatureValue())) {
            throw new SignatureException("Signature was not verified.");
        }
    }

    /**
     * verify
     */
    public void verify(PublicKey key, String sigProvider)
                     throws CRLException, NoSuchAlgorithmException,
                            InvalidKeyException, NoSuchProviderException,
                            SignatureException {
        Signature signature = Signature.getInstance(
                    tbsCertList.getSignature().getAlgorithm(), sigProvider);
        signature.initVerify(key);
        byte[] tbsEncoding = tbsCertList.getEncoded();
        signature.update(tbsEncoding, 0, tbsEncoding.length);
        if (!signature.verify(crl.getSignatureValue())) {
            throw new SignatureException("Signature was not verified.");
        }
    }

    // 
    // ----- java.security.cert.CRL methods implementations ------
    //
    
    /**
     * isRevoked
     */
    public boolean isRevoked(Certificate cert) {
        if (!(cert instanceof X509Certificate)) {
            return false;
        }
        return getRevokedCertificate((X509Certificate) cert) != null;
    }

    /**
     * toString
     */
    public String toString() {
        // FIXME
        return "X509CRLImpl:...";
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
        return extensions.getExtensionByOID(oid).getRawExtnValue();
    }

    public boolean hasUnsupportedCriticalExtension() {
        if (extensions == null) {
            return false;
        }
        return extensions.hasUnsupportedCritical();
    }
}

