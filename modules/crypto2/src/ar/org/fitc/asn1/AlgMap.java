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

package ar.org.fitc.asn1;

import java.util.HashMap;
import java.util.Map;

/**
 * Associates algorithm's oid with the corresponding names of the algorithm
 * 
 * @author Diego Raúl Mercado
 * @version 1.2
 */
class AlgMap {
    /** Associates oid(K) - algorithm(V) */
    private static Map<ASNObjectIdentifier, String> oid2alg = 
        new HashMap<ASNObjectIdentifier, String>();

    /** Associates algorithm(K) - oid(V) */
    private static Map<String, ASNObjectIdentifier> alg2oid = 
        new HashMap<String, ASNObjectIdentifier>();

    /**
     * Constructs a new ASNObjectIdentifier from <code>oid</code>. Each name
     * is inserted into <code>alg2oid</code> as KEY and the
     * ASNObjectIdentifier constructed as VALUE. <br>
     * Finally, inserts into <code>oid2alg</code> the ASNObjectIdentifier
     * constructed as KEY and the first name as VALUE (considered as the
     * canonical name)
     * 
     * @param oid
     *            the oid's string notation
     * @param names
     *            an array of algorithm's aliases
     */
    private static void add(String oid, String... names) {
        assert !oid2alg.containsKey(oid);
        ASNObjectIdentifier asnOID = new ASNObjectIdentifier(oid);
        for (String name : names) {
            assert !alg2oid.containsKey(name);
            alg2oid.put(name.toUpperCase(), asnOID);
        }
        oid2alg.put(asnOID, names[0]);
    }

    /**
     * @param algName
     *            the algorithm's name
     * @return returns from the hashmap <code>alg2oid</code> the
     *         ASNObjectIdentifier that belongs to <code>algName</code>.
     *         Otherwise, its will return null
     */
    static ASNObjectIdentifier getOID(String algName) {
        return alg2oid.get(algName.toUpperCase());
    }

    /**
     * @param oid
     *            the ObjectIdentifier
     * @return returns from the hashmap <code>oid2alg</code> the algorithm's
     *         name (canonical name) that belongs to <code>oid</code>.
     *         Otherwise, its will return null
     */
    static String getAlgName(ASNObjectIdentifier oid) {
        return oid2alg.get(oid);
    }

    /**
     * Completes the table calling add's method
     */
    static {
        /*
         * Completes oidAlg and algOid hashmaps
         */

        /*
         * SUN VM + IBM's aliases
         */
        add("1.2.840.113549.1.1.1", "RSA");
        add("1.2.840.113549.1.1.2", "MD2withRSA", "MD2/RSA");
        add("1.2.840.113549.1.1.4", "MD5withRSA", "MD5/RSA");
        add("1.2.840.113549.1.1.5", "SHA1withRSA", "SHA/RSA", "SHA1withRSA",
                "SHA1/RSA");
        add("1.2.840.113549.1.1.11", "SHA256withRSA");
        add("1.2.840.113549.1.1.12", "SHA384withRSA");
        add("1.2.840.113549.1.1.13", "SHA512withRSA");
        add("1.2.840.113549.1.3.1", "Diffie-Hellman", "DiffieHellman", "DH");
        add("1.2.840.113549.1.5.3", "PBEWithMD5AndDES", "pbeWithMD5AndDES-CBC");
        add("1.2.840.113549.1.12.1.3", "PBEWithSHA1AndDESede",
                "PBEWithSHA-1And3KeyTripleDES", "PBEWithSHA1And3KeyTripleDES",
                "PBEWithSHAAnd3KeyTripleDES");
        add("1.2.840.113549.1.12.1.6", "PBEWithSHA1AndRC2_40",
                "PBEWithSHA1And40BitRC2", "PBEWithSHA-1And40BitRC2",
                "PBEWithSHAAnd40BitRC2");
        add("1.2.840.113549.2.2", "MD2");
        add("1.2.840.113549.2.5", "MD5");
        add("1.2.840.10040.4.1", "DSA");
        add("1.2.840.10040.4.3", "SHA1withDSA", "SHAwithDSA", "DSAWithSHA1",
                "SHA/DSA", "SHA-1/DSA", "DSAwithSHA1", "DSS", "SHA1/DSA");
        add("1.3.14.3.2.12", "DSA");
        add("1.3.14.3.2.13", "SHA1withDSA");
        add("1.3.14.3.2.26", "SHA", "SHA1", "SHA-1");
        add("1.3.14.3.2.27", "SHA1withDSA");
        add("1.3.14.3.2.29", "SHA1withRSA", "SHA1/RSA");
        add("2.16.840.1.101.3.4.2.1", "SHA256", "SHA-256");
        add("2.16.840.1.101.3.4.2.2", "SHA384", "SHA-384");
        add("2.16.840.1.101.3.4.2.3", "SHA512", "SHA-512");

        /*
         * IBM VM
         */
        add("1.2.5.8.1.1", "RSA");
        add("1.2.840.113549.1.5.10", "PBEWithSHAAndDES", "PBEWithSHA1AndDES",
                "PBEWITHSHA1ANDDES");
        add("1.2.840.113549.1.5.11", "PBEWithSHA1AndRC2", "PBEWITHSHA1ANDRC2",
                "PBEWithSHAAndRC2");
        add("1.2.840.113549.1.12.1.1", "PBEWithSHA-1And128BitRC4",
                "PBEWITHSHA1AND128BITRC4", "PBEWithSHA1And128BitRC4",
                "PBEWithSHAAnd128BitRC4");
        add("1.2.840.113549.1.12.1.2", "PBEWithSHA-1And40BitRC4",
                "PBEWITHSHA1AND40BITRC4", "PBEWithSHA1And40BitRC4",
                "PBEWithSHAAnd40BitRC4");
        add("1.2.840.113549.1.12.1.4", "PBEWithSHAAnd2KeyTripleDES",
                "PBEWithSHA1And2KeyTripleDES", "PBEWithSHA-1And2KeyTripleDES");
        add("1.2.840.113549.1.12.1.5", "PBEWithSHA-1And128BitRC2",
                "PBEWithSHAAnd128BitRC2", "PBEWithSHA1And128BitRC2");
        add("1.2.840.113549.3.2", "RC2", "RC2-CBC");
        add("1.2.840.113549.3.7", "TripleDES", "3DES", "DESede");
        add("1.2.840.113533.7.66.13", "PasswordBasedMac", "PBM");
        add("1.3.14.3.2.7", "DES");

        /*
         * http://www.alvestrand.no http://asn1.elibel.tm.fr
         */
        add("1.2.840.113549.1.1.3", "md4WithRSAEncryption");
        add("1.2.840.113549.1.1.6", "RSA-OAEP");
        add("1.2.840.113549.1.5.1", "pbeWithMD2AndDES-CBC");
        add("1.2.840.113549.1.5.4", "pbeWithMD2AndRC2-CBC");
        add("1.2.840.113549.1.5.6", "pbeWithMD5AndRC2-CBC");
        add("1.2.840.113549.1.5.9", "pbeWithMD5AndXOR");
        add("1.2.840.113549.2.4", "MD4");
        add("1.2.840.113549.2.7", "HMAC-SHA-1");
        add("1.2.840.113549.3.4", "RC4");
        add("1.2.840.113549.3.7", "DES-EDE3-CBC");
        add("1.2.840.113549.3.9", "RC5-CBC");
        add("1.2.840.113549.3.10", "CDMFCBCPad");
    }
}
