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

import java.util.List;

import org.apache.harmony.security.asn1.ASN1Any;
import org.apache.harmony.security.asn1.ASN1Implicit;
import org.apache.harmony.security.asn1.ASN1Integer;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1SetOf;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;

import com.openintel.drl.security.x509.AlgorithmIdentifier;
import com.openintel.drl.security.x509.Certificate;

/**
 * As defined in PKCS #7: Cryptographic Message Syntax Standard
 * (http://www.rsasecurity.com/rsalabs/node.asp?id=2129)
 * 
 * SignedData ::= SEQUENCE { 
 *   version Version, 
 *   digestAlgorithms DigestAlgorithmIdentifiers,
 *   contentInfo ContentInfo,
 *   certificates
 *     [0] IMPLICIT ExtendedCertificatesAndCertificates OPTIONAL,
 *   crls 
 *     [1] IMPLICIT CertificateRevocationLists OPTIONAL,
 *   signerInfos SignerInfos }
 *  
 */

public class SignedData {

    private int version;

    private List digestAlgorithms;
    private ContentInfo contentInfo;
    private List certificates;
    private List crls;
    private List signerInfos;

    public SignedData(int version, List digestAlgorithms, ContentInfo contentInfo,
            List certificates, List crls, List signerInfos) {
        this.version = version;
        this.digestAlgorithms = digestAlgorithms;
        this.contentInfo = contentInfo;
        this.certificates = certificates;
        this.crls = crls;
        this.signerInfos = signerInfos;
    }

    public List getCertificates() {
        return certificates;
    }

    public List getSignerInfos() {
        return signerInfos;
    }

    public String toString() {
        StringBuffer res = new StringBuffer();
        res.append("---- SignedData:");
        res.append("\nversion: ");
        res.append(version);
        res.append("\ndigestAlgorithms: ");
        res.append(digestAlgorithms.toString());
        res.append("\ncontentInfo: ");
        res.append(contentInfo.toString());
        res.append("\ncertificates: ");
        if (certificates != null) {
            res.append(certificates.toString());
        }
        res.append("\ncrls: ");
        if (crls != null) {
            res.append(crls.toString());
        }
        res.append("\nsignerInfos:\n");
        res.append(signerInfos.toString());
        res.append("\n---- SignedData End\n]");
        return res.toString();
    }

    public static final ASN1Sequence ASN1 = new ASN1Sequence(new ASN1Type[] {
            ASN1Integer.getInstance(), 
            new ASN1SetOf(AlgorithmIdentifier.ASN1),
            ContentInfo.ASN1,
            new ASN1Implicit(0, new ASN1SetOf(Certificate.ASN1)),
            new ASN1Implicit(1, new ASN1SetOf(ASN1Any.getInstance())),
            new ASN1SetOf(SignerInfo.ASN1) 
			}) {
        {
            setOptional(3); // certificates is optional
            setOptional(4); // crls is optional
        }

        protected Object getDecodedObject(BerInputStream in) {
            Object[] values = (Object[]) in.content;
            return new SignedData(
                        ASN1Integer.toIntValue(values[0]),
                        (List) values[1], 
                        (ContentInfo) values[2],
                        (List) values[3], 
                        (List) values[4], 
                        (List) values[5]
                    );
        }
    };

}