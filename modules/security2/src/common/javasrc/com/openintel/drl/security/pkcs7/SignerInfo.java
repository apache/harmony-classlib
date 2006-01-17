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
* @author Boris Kuznetsov
* @version $Revision$
*/
package com.openintel.drl.security.pkcs7;

import java.math.BigInteger;
import java.util.List;

import javax.security.auth.x500.X500Principal;

import org.apache.harmony.security.asn1.ASN1Implicit;
import org.apache.harmony.security.asn1.ASN1Integer;
import org.apache.harmony.security.asn1.ASN1OctetString;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1SetOf;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;

import com.openintel.drl.security.x501.AttributeTypeAndValue;
import com.openintel.drl.security.x501.Name;
import com.openintel.drl.security.x509.AlgorithmIdentifier;

/**
 * As defined in PKCS #7: Cryptographic Message Syntax Standard
 * (http://www.rsasecurity.com/rsalabs/node.asp?id=2129)
 * 
 * SignerInfo ::= SEQUENCE {
 *   version Version,
 *   issuerAndSerialNumber IssuerAndSerialNumber,
 *   digestAlgorithm DigestAlgorithmIdentifier,
 *   authenticatedAttributes
 *     [0] IMPLICIT Attributes OPTIONAL,
 *   digestEncryptionAlgorithm
 *     DigestEncryptionAlgorithmIdentifier,
 *   encryptedDigest EncryptedDigest,
 *   unauthenticatedAttributes
 *     [1] IMPLICIT Attributes OPTIONAL
 *  }
 * 
 */
public class SignerInfo {

    private int version;
    private X500Principal issuer;
    private BigInteger serialNumber;
    
    private AlgorithmIdentifier digestAlgorithm;
    private AuthenticatedAttributes authenticatedAttributes;
    private AlgorithmIdentifier digestEncryptionAlgorithm;
    private byte[] encryptedDigest;
    private List unauthenticatedAttributes;

    public SignerInfo(int version,
            Object[] issuerAndSerialNumber,
            AlgorithmIdentifier digestAlgorithm,
            AuthenticatedAttributes authenticatedAttributes,
            AlgorithmIdentifier digestEncryptionAlgorithm,
            byte[] encryptedDigest,
            List unauthenticatedAttributes
            ) {
        this.version = version;
        this.issuer = new X500Principal(((Name)issuerAndSerialNumber[0]).getEncoded());
        this.serialNumber = BigInteger.valueOf(ASN1Integer.toIntValue(issuerAndSerialNumber[1]));
        this.digestAlgorithm = digestAlgorithm;
        this.authenticatedAttributes = authenticatedAttributes;
        this.digestEncryptionAlgorithm = digestEncryptionAlgorithm;
        this.encryptedDigest = encryptedDigest;
        this.unauthenticatedAttributes = unauthenticatedAttributes;
    }

    public X500Principal getIssuer() {
    	return issuer;
    }
    
    public BigInteger getSerialNumber() {
    	return serialNumber;
    }    
    
    public String getDigestAlgorithm() {
        return digestAlgorithm.getAlgorithm();
    }

    public String getdigestAlgorithm() {
        return digestAlgorithm.getAlgorithm();
    }

    public String getDigestEncryptionAlgorithm() {
        return digestEncryptionAlgorithm.getAlgorithm();
    }

    public List getAuthenticatedAttributes() {
        if (authenticatedAttributes == null) {
            return null;
        }
        return authenticatedAttributes.getAttributes();
    }

    public byte[] getEncodedAuthenticatedAttributes() {
        if (authenticatedAttributes == null) {
            return null;
        }
        return authenticatedAttributes.getEncoded();
    }

    public byte[] getEncryptedDigest() {
        return encryptedDigest;
    }

    
    public String toString() {
        StringBuffer res = new StringBuffer();
        res.append("-- SignerInfo:");
        res.append("\n version : ");
        res.append(version);
        res.append("\nissuerAndSerialNumber:  ");
        res.append(issuer);
        res.append("   ");
        res.append(serialNumber);
        res.append("\ndigestAlgorithm:  ");
        res.append(digestAlgorithm.toString());
        res.append("\nauthenticatedAttributes:  ");
        if (authenticatedAttributes != null) {
            res.append(authenticatedAttributes.toString());
        }
        res.append("\ndigestEncryptionAlgorithm: ");
        res.append(digestEncryptionAlgorithm.toString());
        res.append("\nunauthenticatedAttributes: ");
        if (unauthenticatedAttributes != null) {
            res.append(unauthenticatedAttributes.toString());
        }
        res.append("\n-- SignerInfo End\n");
        return res.toString();
    }

    public static final ASN1Sequence ASN1 = 
        new ASN1Sequence(new ASN1Type[] {
                ASN1Integer.getInstance(),         //version
                new ASN1Sequence(new ASN1Type[]    //issuerAndSerialNumber
                        {Name.ASN1,                    //issuer
                        ASN1Integer.getInstance()      //serialNumber
                        }
                ),
                AlgorithmIdentifier.ASN1,           //digestAlgorithm
                new ASN1Implicit(0, AuthenticatedAttributes.ASN1),//authenticatedAttributes
                AlgorithmIdentifier.ASN1,            //digestEncryptionAlgorithm
                ASN1OctetString.getInstance(),       //encryptedDigest
                 new ASN1Implicit(1, new ASN1SetOf(
                         AttributeTypeAndValue.ASN1)),//unauthenticatedAttributes
                })  {
        {
            setOptional(3); // authenticatedAttributes is optional
            setOptional(6); // unauthenticatedAttributes is optional
        }

        protected Object getDecodedObject(BerInputStream in) {
            Object[] values = (Object[]) in.content;
            return new SignerInfo(
                        ASN1Integer.toIntValue(values[0]),
                        (Object[]) values[1], 
                        (AlgorithmIdentifier) values[2],
                        (AuthenticatedAttributes) values[3], 
                        (AlgorithmIdentifier) values[4], 
                        (byte[]) values[5],
                        (List) values[6]
                    );
        }
   };
}

/**
 * 
 * As defined in PKCS #7: Cryptographic Message Syntax Standard
 * (http://www.rsasecurity.com/rsalabs/node.asp?id=2129):
 * authenticatedAttributes is a set of attributes that are signed (i.e., authenticated) by the signer
 */
class AuthenticatedAttributes {
    private byte[] encoding;
    private List authenticatedAttributes;
    
    public AuthenticatedAttributes(byte[] encoding, List authenticatedAttributes) {
        this.encoding = encoding;
        this.authenticatedAttributes = authenticatedAttributes;
    }
    public List getAttributes() {
        return authenticatedAttributes;
    }

    /**
     * Returns ASN.1 encoded form of this authenticatedAttributes.
     * @return a byte array containing ASN.1 encode form.
     */
    public byte[] getEncoded() {
        if (encoding == null) {
            encoding = ASN1.encode(this);
        }
        return encoding;
    }

    public static final ASN1SetOf ASN1 =
        new ASN1SetOf(AttributeTypeAndValue.ASN1) {
        public Object getDecodedObject(BerInputStream in) {
            Object[] values = (Object[]) in.content;
            return new AuthenticatedAttributes(in.getEncoded(), (List) values[0]);
        }
    };
}