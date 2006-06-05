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
 * @author Hugo Beilis
 * @author Osvaldo Demo
 * @author Jorge Rafael
 * @version 1.0
 */

package ar.org.fitc.test.util;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.cert.X509Certificate;
import java.util.Date;

import javax.security.auth.x500.X500Principal;

import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.x509.X509V3CertificateGenerator;

public class CertGen {
    
    private int keyPairLength;
    private int keyusageparameters = 0;
    private boolean criticalkeyusage;
    private String issuer = null;
    private KeyPair pair; 
    
    public CertGen() {
        setKeyPairLength(1024);
        setKeyusageparameters(KeyUsage.digitalSignature | KeyUsage.keyEncipherment);
        setCriticalkeyusage(true);
        setIssuer("ITC Testing Group");
        try {
            setKeyPair(generateRSAKeyPair(getKeyPairLength()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public CertGen(String file) {
        
        
    }
    
    public X509Certificate getCertificate() throws InvalidKeyException, NoSuchProviderException, SecurityException, SignatureException {
        if (getKeyusageparameters() == 0) {
            throw new SecurityException("No KeyUsageParameters defined...");
        }
        if (getIssuer() == null) {
            throw new SecurityException("No certificate authority and/or entity associated with the public key");
        }
        X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();
        certGen.setSerialNumber(BigInteger.valueOf(System.currentTimeMillis()));
        certGen.setIssuerDN(new X500Principal("CN="+getIssuer()));
        certGen.setNotBefore(new Date(System.currentTimeMillis() - 50000));
        certGen.setNotAfter(new Date(System.currentTimeMillis() + 500000000));
        certGen.setSubjectDN(new X500Principal("CN="+getIssuer()));
        certGen.setPublicKey(pair.getPublic( ));
        certGen.setSignatureAlgorithm("SHA256WithRSAEncryption");
        certGen.addExtension(X509Extensions.BasicConstraints, true, new BasicConstraints(false));
        certGen.addExtension(X509Extensions.KeyUsage, isCriticalkeyusage(), new KeyUsage(getKeyusageparameters()));
        //certGen.addExtennullsion(X509Extensions.ExtendedKeyUsage, true, new ExtendedKeyUsage(KeyPurposeId.id_kp_serverAuth));    
        certGen.addExtension(X509Extensions.SubjectAlternativeName, false, new GeneralNames(new GeneralName(GeneralName.rfc822Name,"test@testing.fitc.org.ar")));
        return certGen.generateX509Certificate(pair.getPrivate(), "BC");
    }
    
    private KeyPair generateRSAKeyPair(int keylength) throws Exception {
        KeyPairGenerator  kpGen = KeyPairGenerator.getInstance("RSA", "BC");
        kpGen.initialize(keylength, new SecureRandom());
        return kpGen.generateKeyPair();
    }
   
    public void setKeyusageparameters(int keyusageparameters) {
        this.keyusageparameters = keyusageparameters;
    }

    public int getKeyusageparameters() {
        return keyusageparameters;
    }

    public void setCriticalkeyusage(boolean criticalkeyusage) {
        this.criticalkeyusage = criticalkeyusage;
    }

    public boolean isCriticalkeyusage() {
        return criticalkeyusage;
    }

    public void setKeyPairLength(int keyPairLength) {
        this.keyPairLength = keyPairLength;
    }

    public int getKeyPairLength() {
        return keyPairLength;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setKeyPair(KeyPair pair) {
        this.pair = pair;
    }

    public KeyPair getKeyPair() {
        return pair;
    }
}