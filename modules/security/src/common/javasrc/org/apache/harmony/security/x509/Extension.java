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

package org.apache.harmony.security.x509;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.security.auth.x500.X500Principal;

import org.apache.harmony.security.asn1.ASN1Boolean;
import org.apache.harmony.security.asn1.ASN1Enumerated;
import org.apache.harmony.security.asn1.ASN1GeneralizedTime;
import org.apache.harmony.security.asn1.ASN1Integer;
import org.apache.harmony.security.asn1.ASN1OctetString;
import org.apache.harmony.security.asn1.ASN1Oid;
import org.apache.harmony.security.asn1.ASN1Sequence;
import org.apache.harmony.security.asn1.ASN1SequenceOf;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;
import org.apache.harmony.security.asn1.BitString;
import org.apache.harmony.security.asn1.ObjectIdentifier;

import org.apache.harmony.security.asn1.ASN1BitString;
import org.apache.harmony.security.x501.Name;

/**
 * The class incapsulates the ASN.1 DER encoding/decoding work 
 * with the Extension part of X.509 certificate
 * (as specified in RFC 3280 -
 *  Internet X.509 Public Key Infrastructure.
 *  Certificate and Certificate Revocation List (CRL) Profile.
 *  http://www.ietf.org/rfc/rfc3280.txt):
 *
 * <pre>
 *  Extension  ::=  SEQUENCE  {
 *       extnID      OBJECT IDENTIFIER,
 *       critical    BOOLEAN DEFAULT FALSE,
 *       extnValue   OCTET STRING  
 *  }
 * </pre>
 */

public class Extension {
    // critical constants
    public static final boolean CRITICAL = true;
    public static final boolean NON_CRITICAL = false;
    
    // constants: the extension OIDs
    // certificate extensions:
    public static final int[] KEY_USAGE = new int[] {2, 5, 29, 15};
    public static final int[] EXTENDED_KEY_USAGE = new int[] {2, 5, 29, 37};
    public static final int[] BASIC_CONSTRAINTS = new int[] {2, 5, 29, 19};
    public static final int[] SUBJECT_ALT_NAME = new int[] {2, 5, 29, 17};
    public static final int[] CRL_DISTR_POINTS = new int[] {2, 5, 29, 31};
    public static final int[] AUTH_KEY_ID = new int[] {2, 5, 29, 35};
    // crl extensions:
    public static final int[] ISSUING_DISTR_POINT = new int[] {2, 5, 29, 28};
    // crl entry extensions:
    public static final int[] CERTIFICATE_ISSUER = new int[] {2, 5, 29, 29};
    public static final int[] INVALIDITY_DATE = new int[] {2, 5, 29, 24};
    public static final int[] REASON_CODE = new int[] {2, 5, 29, 21};
    
    // the value of extnID field of the structure
    private final int[] extnID;
    private String extnID_str;
    // the value of critical field of the structure
    private final boolean critical;
    // the value of extnValue field of the structure
    private final byte[] extnValue;
    // the ASN.1 encoded form of Extension
    private byte[] encoding;
    // the raw (not decoded) value of extnValue field of the structure
    private byte[] rawExtnValue;

    /**
     * TODO
     * @param   extnID: String
     * @param   critical:   boolean
     * @param   extnValue:  byte[]
     */
    public Extension(String extnID, boolean critical, byte[] extnValue) {
        this.extnID_str = extnID;
        this.extnID = ObjectIdentifier.toIntArray(extnID);
        this.critical = critical;
        this.extnValue = extnValue;
    }
        
    /**
     * TODO
     * @param   extnID: int[]
     * @param   critical:   boolean
     * @param   extnValue:  byte[]
     */
    public Extension(int[] extnID, boolean critical, byte[] extnValue) {
        this.extnID = extnID;
        this.critical = critical;
        this.extnValue = extnValue;
    }
        
    /**
     * TODO
     * @param   extnID: String
     * @param   extnValue:  byte[]
     */
    public Extension(String extnID, byte[] extnValue) {
        this(extnID, NON_CRITICAL, extnValue);
    }

    /**
     * TODO
     * @param   extnID: int[]
     * @param   extnValue:  byte[]
     */
    public Extension(int[] extnID, byte[] extnValue) {
        this(extnID, NON_CRITICAL, extnValue);
    }

    // 
    // TODO
    // @param   extnID: String
    // @param   critical:   boolean
    // @param   extnValue:  byte[]
    // @param   encoding:   byte[]
    // 
    private Extension(String extnID, boolean critical, byte[] extnValue,
            byte[] rawExtnValue, byte[] encoding) {
        this(extnID, critical, extnValue);
        this.rawExtnValue = rawExtnValue;
        this.encoding = encoding;
    }
    
    // 
    // TODO
    // @param   extnID: int[]
    // @param   critical:   boolean
    // @param   extnValue:  byte[]
    // @param   encoding:   byte[]
    // 
    private Extension(int[] extnID, boolean critical, byte[] extnValue,
            byte[] rawExtnValue, byte[] encoding) {
        this(extnID, critical, extnValue);
        this.rawExtnValue = rawExtnValue;
        this.encoding = encoding;
    }
    
    /**
     * Returns the value of extnID field of the structure.
     * @return  extnID
     */
    public String getExtnID() {
        if (extnID_str == null) {
            extnID_str = ObjectIdentifier.toString(extnID);
        }
        return extnID_str;
    }
    
    /**
     * Returns the value of critical field of the structure.
     * @return  critical
     */
    public boolean getCritical() {
        return critical;
    }

    /**
     * Returns the value of extnValue field of the structure.
     * @return  extnValue
     */
    public byte[] getExtnValue() {
        return extnValue;
    }

    /**
     * Returns the raw (undecoded octet string) value of extnValue 
     * field of the structure.
     * @return  rawExtnValue
     */
    public byte[] getRawExtnValue() {
        if (rawExtnValue == null) {
            rawExtnValue = ASN1OctetString.getInstance().encode(extnValue);
        }
        return rawExtnValue;
    }

    /**
     * Returns ASN.1 encoded form of this X.509 Extension value.
     * @return a byte array containing ASN.1 encode form.
     */
    public byte[] getEncoded() {
        if (encoding == null) {
            encoding = Extension.ASN1.encode(this);
        }
        return encoding;
    }

    public boolean equals(Object ext) {
        if (!(ext instanceof Extension)) {
            return false;
        }
        Extension extn = (Extension) ext;
        return Arrays.equals(extnID, extn.extnID) 
            && (critical == extn.critical)
            && Arrays.equals(extnValue, extn.extnValue);
    }
    
    // -------------------------------------------------------------------------
    //
    // The values of some extensions should be decoded on the certificate
    // decoding stage. Following are the classes of such extensions.
    // 
    
    /**
     * Key Usage Extension (OID = 2.5.29.15).
     *
     * The ASN.1 definitionn for Key Usage Extension is:
     *
     * <pre>
     * id-ce-keyUsage OBJECT IDENTIFIER ::=  { id-ce 15 }
     *
     * KeyUsage ::= BIT STRING {
     *     digitalSignature        (0),
     *     nonRepudiation          (1),
     *     keyEncipherment         (2),
     *     dataEncipherment        (3),
     *     keyAgreement            (4),
     *     keyCertSign             (5),
     *     cRLSign                 (6),
     *     encipherOnly            (7),
     *     decipherOnly            (8) 
     * }
     * (as specified in RFC 3280)
     * </pre>
     */
    public static class KeyUsage extends Extension {
        // the value of extension
        private final boolean[] keyUsage;
        public static final ASN1Type ASN1 = new ASN1BitString.ASN1NamedBitList(9);
        
        
        public KeyUsage(boolean[] keyUsage) {
            super(KEY_USAGE, true, 
                ASN1BitString.getInstance().encode(new BitString(keyUsage)));
            this.keyUsage = keyUsage;
        }

        private KeyUsage(byte[] extnValue, byte[] rawExtnValue, byte[] encoding)
                throws IOException {
            super(KEY_USAGE, true, extnValue, 
                    rawExtnValue, encoding);
            this.keyUsage = (boolean[]) ASN1.decode(extnValue);
        }

        public boolean[] getKeyUsage() {
            return keyUsage;
        }
    }
    
    /**
     * Extended Key Usage Extension (OID == 2.5.29.37).
     *
     * The ASN.1 definitionn for Extended Key Usage Extension is:
     *
     * <pre> 
     *  id-ce-extKeyUsage OBJECT IDENTIFIER ::= { id-ce 37 }
     *  
     *  ExtKeyUsageSyntax ::= SEQUENCE SIZE (1..MAX) OF KeyPurposeId
     *  
     *  KeyPurposeId ::= OBJECT IDENTIFIER
     * </pre>
     * (as specified in RFC 3280)
     */
    public static class ExtendedKeyUsage extends Extension {
        // the value of extension
        private final List keys;
        
        public ExtendedKeyUsage(List keys, boolean critical) {
            super(EXTENDED_KEY_USAGE, critical,
                ASN1BitString.getInstance().encode(ASN1.encode(keys)));
            this.keys = keys;
        }

        private ExtendedKeyUsage(boolean critical, byte[] extnValue, 
                byte[] rawExtnValue, byte[] encoding) throws IOException {
            super(EXTENDED_KEY_USAGE, critical, 
                    extnValue, rawExtnValue, encoding);
            this.keys = (List) ASN1.decode(extnValue);
        }

        public List getExtendedKeyUsage() {
            return keys;
        }

        public static ASN1Type ASN1 = 
            new ASN1SequenceOf(new ASN1Oid() {
                
                public Object getDecodedObject(BerInputStream in)
                        throws IOException {
                    int[] oid = (int[]) super.getDecodedObject(in);
                    return ObjectIdentifier.toString(oid);
                }
            
            });
    }

    
    /**
     * Basic Constraints Extension (OID == 2.5.29.19).
     *
     * The ASN.1 definitionn for Basic Constraints Extension is:
     *
     * <pre> 
     *   id-ce-basicConstraints OBJECT IDENTIFIER ::=  { id-ce 19 }
     *
     *   BasicConstraints ::= SEQUENCE {
     *        cA                      BOOLEAN DEFAULT FALSE,
     *        pathLenConstraint       INTEGER (0..MAX) OPTIONAL 
     *   }
     * </pre>
     * (as specified in RFC 3280)
     */
    public static class BasicConstraints extends Extension {
        // the value of extension
        private boolean cA = false;
        private int pathLenConstraint = Integer.MAX_VALUE;
            
        // TODO: Constructor for creating the extension without 
        // encoding provided
        //
        // public BasicConstraints(List keys, boolean critical) {
        //     super(ASN1Oid.toString(BASIC_CONSTRAINTS), critical,
        //    // make extnValue     ASN1.encode(keys));
        // }

        private BasicConstraints(byte[] extnValue, byte[] rawExtnValue, 
                byte[] encoding) throws IOException {
            super(BASIC_CONSTRAINTS, true, 
                    extnValue, rawExtnValue, encoding);
            Object[] values = (Object[]) ASN1.decode(extnValue);
            cA = ((Boolean) values[0]).booleanValue();
            if (values[1] != null) {
                pathLenConstraint = new BigInteger((byte[]) values[1]).intValue();
            }
        }

        public boolean getCA() {
            return cA;
        }

        public int getPathLenConstraint() {
            return pathLenConstraint;
        }

        public static ASN1Type ASN1 = new ASN1Sequence(new ASN1Type[] {
                ASN1Boolean.getInstance(), ASN1Integer.getInstance() }) {
            {
                setDefault(Boolean.FALSE, 0);
                setOptional(1);
            }
            
            public Object getDecodedObject(BerInputStream in)
                    throws IOException {
                return in.content;
            }

            protected void getValues(Object object, Object[] values) {
                Object[] vals = (Object[]) object;
                values[0] = vals[0];
                values[1] = ((BigInteger) vals[1]).toByteArray();
            }
                
        };
    }

    /*
     * CRL Distribution Points Extension (OID = 2.5.29.31). 
     * <pre>
     *  id-ce-cRLDistributionPoints OBJECT IDENTIFIER ::=  { id-ce 31 }
     *
     *  CRLDistributionPoints ::= SEQUENCE SIZE (1..MAX) OF DistributionPoint
     * 
     *  DistributionPoint ::= SEQUENCE {
     *       distributionPoint       [0]     DistributionPointName OPTIONAL,
     *       reasons                 [1]     ReasonFlags OPTIONAL,
     *       cRLIssuer               [2]     GeneralNames OPTIONAL }
     *
     *  DistributionPointName ::= CHOICE {
     *       fullName                [0]     GeneralNames,
     *       nameRelativeToCRLIssuer [1]     RelativeDistinguishedName }
     *  
     *  ReasonFlags ::= BIT STRING {
     *       unused                  (0),
     *       keyCompromise           (1),
     *       cACompromise            (2),
     *       affiliationChanged      (3),
     *       superseded              (4),
     *       cessationOfOperation    (5),
     *       certificateHold         (6),
     *       privilegeWithdrawn      (7),
     *       aACompromise            (8) 
     *  }
     *
     * </pre>
     * (as specified in RFC 3280)
    public static class CRLDistributionPoints extends Extension {

        private final CRLDistributionPoints distr_points;
        
        public CRLDistributionPoints(CRLDistributionPoints distr_points) {
            super(CRL_DISTR_POINTS, NON_CRITICAL, 
                    distr_points.getEncoded());
            this.distr_points = distr_points;
        }
        
        public static ASN1Type ASN1 = CRLDistributionPoints.ASN1;
    }
     */
   
    
    /*
     * Subject Alternative Name Extension (OID == 2.5.29.17).
     *
     * The ASN.1 definitionn for Subject Alternative Name is:
     *
     * <pre> 
     *  id-ce-subjectAltName OBJECT IDENTIFIER ::=  { id-ce 17 }
     *  
     *  SubjectAltName ::= GeneralNames
     * </pre>
     * (as specified in RFC 3280)
    public static class SubjectAlternativeNames extends Extension {
        // the value of extension
        private GeneralNames generalNames;
            
        // TODO: Constructor for creating the extension without 
        // encoding provided

        private SubjectAlternativeNames(byte[] extnValue, 
                byte[] encoding) throws IOException {
            super(ASN1Oid.toString(SUBJECT_ALT_NAME), true, 
                    extnValue, encoding);
            generalNames = (GeneralNames) GeneralNames.ASN1.decode(extnValue);
        }

        public List getGeneralNames() {
            return generalNames.getPairsList();
        }
    }
     */
   
    /**
     * Authority Key Identifier Extension (OID = 2.5.29.35). 
     * <pre>
     *   id-ce-authorityKeyIdentifier OBJECT IDENTIFIER ::=  { id-ce 35 }
     *
     *   AuthorityKeyIdentifier ::= SEQUENCE {
     *      keyIdentifier             [0] KeyIdentifier           OPTIONAL,
     *      authorityCertIssuer       [1] GeneralNames            OPTIONAL,
     *      authorityCertSerialNumber [2] CertificateSerialNumber OPTIONAL  }
     *
     *   KeyIdentifier ::= OCTET STRING
     * </pre>
     * (as specified in RFC 3280)
     */
    public static class AuthKeyId extends Extension {

        private final AuthorityKeyIdentifier authKeyID;
        
        public AuthKeyId(AuthorityKeyIdentifier authKeyID) {
            super(AUTH_KEY_ID, NON_CRITICAL, authKeyID.getEncoded());
            this.authKeyID = authKeyID;
        }
    }
   
    /**
     * CRL Entry's Certificate Issuer Extension (OID = 2.5.29.29). 
     * It is a CRL entry extension and contains the GeneralNames describing
     * the issuer of revoked certificate. Its ASN.1 notation is as follows:
     * <pre>
     *   id-ce-certificateIssuer   OBJECT IDENTIFIER ::= { id-ce 29 }
     *
     *   certificateIssuer ::=     GeneralNames
     * </pre>
     * (as specified in RFC 3280)
     * In java implementation it is presumed that GeneralNames cansist of
     * one element and its type is directoryName.
     */
    public static class CertificateIssuer extends Extension {

        private final GeneralName issuer;
        
        public CertificateIssuer(GeneralName issuer) {
            super(CERTIFICATE_ISSUER, CRITICAL, ASN1.encode(issuer));
            this.issuer = issuer;
        }
        
        public static ASN1Type ASN1 = new ASN1Sequence(new ASN1Type[] {
            GeneralName.ASN1
        }) {
            public Object getDecodedObject(BerInputStream in) {
                return new X500Principal(
                    ((Name) ((GeneralName)((Object[]) in.content)[0])
                     .getName()).getName(X500Principal.RFC2253)
                    );
            }

            protected void getValues(Object object, Object[] values) {
                values[0] = object;
            }
        };
    }
   
    /**
     * CRL Entry's Invalidity Date Extension (OID = 2.5.29.24). 
     * <pre>
     *   id-ce-invalidityDate OBJECT IDENTIFIER ::= { id-ce 24 }
     *
     *   invalidityDate ::=  GeneralizedTime
     * </pre>
     * (as specified in RFC 3280)
     */
    public static class InvalidityDate extends Extension {

        private final Date date;
        
        public InvalidityDate(Date date) {
            super(INVALIDITY_DATE, NON_CRITICAL, ASN1.encode(date));
            this.date = date;
        }
        
        public static ASN1Type ASN1 = ASN1GeneralizedTime.getInstance();
    }
   
    /**
     * CRL Entry's Reason Code Extension (OID = 2.5.29.21). 
     * <pre>
     *  id-ce-cRLReason OBJECT IDENTIFIER ::= { id-ce 21 }
     *  
     *  -- reasonCode ::= { CRLReason }
     *  CRLReason ::= ENUMERATED {
     *       unspecified             (0),
     *       keyCompromise           (1),
     *       cACompromise            (2),
     *       affiliationChanged      (3),
     *       superseded              (4),
     *       cessationOfOperation    (5),
     *       certificateHold         (6),
     *       removeFromCRL           (8),
     *       privilegeWithdrawn      (9),
     *       aACompromise           (10) 
     *  }
     * </pre>
     * (as specified in RFC 3280)
     */
    public static class ReasonCode extends Extension {

        private final int code;
        
        public ReasonCode(int code) {
            super(REASON_CODE, NON_CRITICAL, 
                    ASN1.encode(new byte[] {(byte) code}));
            this.code = code;
        }
        
        public static ASN1Type ASN1 = ASN1Enumerated.getInstance();
    }

    /*
     * CRL's Issuing Distribution Point Extension (OID = 2.5.29.28). 
     * <pre>
     *   id-ce-issuingDistributionPoint OBJECT IDENTIFIER ::= { id-ce 28 }
     *
     *   issuingDistributionPoint ::= SEQUENCE {
     *      distributionPoint          [0] DistributionPointName OPTIONAL,
     *      onlyContainsUserCerts      [1] BOOLEAN DEFAULT FALSE,
     *      onlyContainsCACerts        [2] BOOLEAN DEFAULT FALSE,
     *      onlySomeReasons            [3] ReasonFlags OPTIONAL,
     *      indirectCRL                [4] BOOLEAN DEFAULT FALSE,
     *      onlyContainsAttributeCerts [5] BOOLEAN DEFAULT FALSE 
     *   }
     * </pre>
     * (as specified in RFC 3280)
    public static class IssuingDistributionPoint extends Extension {

        private final int code;
        
        public IssuingDistributionPoint(int code) {
            super(ISSUING_DISTR_POINT, CRITICAL, 
                    ASN1.encode(new byte[] {(byte) code}));
            this.code = code;
        }
        
        public static ASN1Type ASN1 = new ASN1Sequence(
                new ASN1Type[] {
                    new ASN1Implicit(0, DistributionPointName.ASN1),
                    new ASN1Implicit(1, ASN1Boolean.getInstance()),
                    new ASN1Implicit(2, ASN1Boolean.getInstance()),
                    new ASN1Implicit(3, ReasonFlags.ASN1),
                    new ASN1Implicit(4, ASN1Boolean.getInstance()),
                    new ASN1Implicit(5, ASN1Boolean.getInstance())
                }) {
                // TODO: tbd
        };
    }
     */



    // Compares two OIDs
    private static boolean oidEquals(int[] oid1, int[] oid2) {
        int length = oid1.length;
        if (length != oid2.length) {
            return false;
        }
        while (length > 0) {
            if (oid1[--length] != oid2[length]) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * X.509 Extension encoder/decoder.
     */
    public static final ASN1Sequence ASN1 = new ASN1Sequence(new ASN1Type[] {
            ASN1Oid.getInstance(), 
            ASN1Boolean.getInstance(),
            new ASN1OctetString() {
                public Object getDecodedObject(BerInputStream in) 
                                                throws IOException {
                    // first - decoded octet string,
                    // second - raw encoding of octet string
                    return new Object[] 
                        {super.getDecodedObject(in), in.getEncoded()};
                }
            }
        }) {
        {
            setDefault(Boolean.FALSE, 1);
        }

        protected Object getDecodedObject(BerInputStream in) throws IOException {
            Object[] values = (Object[]) in.content;

            int[] oid = (int[]) values[0];
            byte[] extnValue = (byte[]) ((Object[]) values[2])[0];
            byte[] rawExtnValue = (byte[]) ((Object[]) values[2])[1];
            
            if (oidEquals(oid, KEY_USAGE)) {
                return new KeyUsage(extnValue, rawExtnValue, in.getEncoded());
            } else if (oidEquals(oid, BASIC_CONSTRAINTS)) {
                return new BasicConstraints(
                                extnValue, rawExtnValue, in.getEncoded());
            }

            return 
                new Extension((int[]) values[0],
                    ((Boolean) values[1]).booleanValue(),
                    extnValue, rawExtnValue, in.getEncoded());
        }

        protected void getValues(Object object, Object[] values) {

            Extension ext = (Extension) object;

            values[0] = ext.extnID;
            values[1] = (ext.critical) ? Boolean.TRUE : Boolean.FALSE;
            values[2] = ext.extnValue;
        }
    };
}

