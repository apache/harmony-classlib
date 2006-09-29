/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
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
* @author Vladimir N. Molotkov
* @version $Revision$
*/

package org.apache.harmony.crypto.utils;

import java.security.Provider;
import java.security.Security;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.harmony.security.asn1.ObjectIdentifier;

/**
 * Provides Algorithm Name to OID
 * and OID to Algorithm Name mappings.
 * Some known mappings are hardcoded.
 * Tries to obtain additional mappings
 * from installed providers during initialization.
 * 
 * Hardcoded mappings
 * (source: http://asn1.elibel.tm.fr):
 * 
 * 1.2.840.10040.4.1 -> DSA
 * 
 * 1.2.840.113549.1.1.1 -> RSA
 * 
 * 1.2.840.113549.1.3.1 -> DiffieHellman
 * 
 * 1.2.840.113549.1.5.3 -> PBEWithMD5AndDES
 * 
 * 1.2.840.113549.1.12.1.3 -> pbeWithSHAAnd3-KeyTripleDES-CBC
 * 1.2.840.113549.1.12.1.3 -> PBEWithSHA1AndDESede
 * 1.2.840.113549.1.12.1.3 -> PBEWithSHA1AndTripleDES
 * 
 * 1.2.840.113549.1.12.1.6 -> pbeWithSHAAnd40BitRC2-CBC
 * 1.2.840.113549.1.12.1.6 -> PBEWithSHA1AndRC2_40
 * 
 * 1.2.840.113549.3.2 -> RC2-CBC
 * 1.2.840.113549.3.3 -> RC2-EBC
 * 1.2.840.113549.3.4 -> RC4
 * 1.2.840.113549.3.5 -> RC4WithMAC
 * 1.2.840.113549.3.6 -> DESx-CBC
 * 1.2.840.113549.3.7 -> TripleDES-CBC
 * 1.2.840.113549.3.8 -> rc5CBC
 * 1.2.840.113549.3.9 -> RC5-CBC
 * 1.2.840.113549.3.10 -> DESCDMF (CDMFCBCPad)
 *  
 */
public class AlgNameMapper {
    
    // Will search OID mappings for these services
    private static final String[] serviceName = {
            "Cipher", //$NON-NLS-1$
            "AlgorithmParameters", //$NON-NLS-1$
            "Signature" //$NON-NLS-1$
    };

    // These mappings CAN NOT be overridden
    // by the ones from available providers
    // during maps initialization
    // (source: http://asn1.elibel.tm.fr):
    private static final String[][] knownAlgMappings = {
        {"1.2.840.10040.4.1",       "DSA"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"1.2.840.10040.4.3",       "SHA1withDSA"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"1.2.840.113549.1.1.1",    "RSA"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"1.2.840.113549.1.1.2",    "MD2withRSA"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"1.2.840.113549.1.1.4",    "MD5withRSA"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"1.2.840.113549.1.1.5",    "SHA1withRSA"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"1.2.840.113549.1.3.1",    "DiffieHellman"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"1.2.840.113549.1.5.3",    "pbeWithMD5AndDES-CBC"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"1.2.840.113549.1.12.1.3", "pbeWithSHAAnd3-KeyTripleDES-CBC"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"1.2.840.113549.1.12.1.6", "pbeWithSHAAnd40BitRC2-CBC"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"1.2.840.113549.3.2",      "RC2-CBC"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"1.2.840.113549.3.3",      "RC2-EBC"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"1.2.840.113549.3.4",      "RC4"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"1.2.840.113549.3.5",      "RC4WithMAC"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"1.2.840.113549.3.6",      "DESx-CBC"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"1.2.840.113549.3.7",      "TripleDES-CBC"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"1.2.840.113549.3.8",      "rc5CBC"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"1.2.840.113549.3.9",      "RC5-CBC"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"1.2.840.113549.3.10",     "DESCDMF"}, //$NON-NLS-1$ //$NON-NLS-2$
        {"2.23.42.9.11.4.1",        "ECDSA"}, //$NON-NLS-1$ //$NON-NLS-2$
    };
    // Maps alg name to OID
    private static final HashMap alg2OidMap = new HashMap();
    // Maps OID to alg name
    private static final HashMap oid2AlgMap = new HashMap();
    // Maps aliases to alg names
    private static final HashMap algAliasesMap = new HashMap();

    static {
        // put known mappings before
        for (int i = 0; i<knownAlgMappings.length; i++) {
            String algUC = knownAlgMappings[i][1].toUpperCase();
            alg2OidMap.put(algUC, knownAlgMappings[i][0]);
            oid2AlgMap.put(knownAlgMappings[i][0], algUC);
            // map upper case alg name to its original name
            algAliasesMap.put(algUC, knownAlgMappings[i][1]);
        }
        //
        // Now search providers for mappings like
        // Alg.Alias.<service>.<OID-INTS-DOT-SEPARATED>=<alg-name>
        //  or
        // Alg.Alias.<service>.OID.<OID-INTS-DOT-SEPARATED>=<alg-name>
        //
        Provider[] pl = Security.getProviders();
        for (int i = 0; i<pl.length; i++) {
            selectEntries(pl[i]);
        }
    }

    // No instances 
    private AlgNameMapper() {
    }
  
    /**
     * Returns OID for algName
     *
     * @param algName algorithm name to be mapped
     * @return OID as String
     */
    public static String map2OID(String algName) {
        // alg2OidMap map contains upper case keys
        return (String)alg2OidMap.get(algName.toUpperCase());
    }

    /**
     * Returns algName for OID
     *
     * @param oid OID to be mapped
     * @return algorithm name
     */
    public static String map2AlgName(String oid) {
        // oid2AlgMap map contains upper case values
        String algUC = (String)oid2AlgMap.get(oid);
        // if not null there is always map UC->Orig
        return algUC == null ? null : (String)algAliasesMap.get(algUC);
    }

    /**
     * Returns Algorithm name for given algorithm alias
     *
     * @param algName - alias
     * @return algorithm name
     */
    public static String getStandardName(String algName) {
        return (String)algAliasesMap.get(algName.toUpperCase());
    }

    // Searches given provider for mappings like
    // Alg.Alias.<service>.<OID-INTS-DOT-SEPARATED>=<alg-name>
    //  or
    // Alg.Alias.<service>.OID.<OID-INTS-DOT-SEPARATED>=<alg-name>
    // Puts mappings found into appropriate internal maps
    private static void selectEntries(Provider p) {
        Set entrySet = p.entrySet();
        for (int i=0; i<serviceName.length; i++) {
            String keyPrfix2find = "Alg.Alias." + serviceName[i] + ".";  //$NON-NLS-1$ //$NON-NLS-2$
            for (Iterator it = entrySet.iterator(); it.hasNext();) {
                Map.Entry me = (Map.Entry)it.next();
                String key = (String)me.getKey();
                if (key.startsWith(keyPrfix2find)) {
                    String alias = key.substring(keyPrfix2find.length());
                    String alg = (String)me.getValue();
                    String algUC = alg.toUpperCase();
                    if (isOID(alias)) {
                        if (alias.startsWith("OID.")) { //$NON-NLS-1$
                            alias = alias.substring(4);
                        }
                        // Do not overwrite already known mappings
                        boolean oid2AlgContains = oid2AlgMap.containsKey(alias);
                        boolean alg2OidContains = alg2OidMap.containsKey(algUC);
                        if (!oid2AlgContains || !alg2OidContains) {
                            if (!oid2AlgContains) {
                                oid2AlgMap.put(alias, algUC);
                            } 
                            if (!alg2OidContains) {
                                alg2OidMap.put(algUC, alias);
                            }
                            // map upper case alg name to its original name
                            algAliasesMap.put(algUC, alg);
                        }
                           // Do not override known standard names
                    } else if (!algAliasesMap.containsKey(alias.toUpperCase())) {
                        algAliasesMap.put(alias.toUpperCase(), alg);
                    }
                }
            }
        }
    }
    
    /**
     * Checks if parameter represents OID
     *
     * @param alias alias to be checked
     * @return 'true' if parameter represents OID 
     */
    public static boolean isOID(String alias) {
        try {
            // The method makes all needed checks in it.
            // If alias is not an OID, exception is thrown.
            ObjectIdentifier.toIntArray(normalize(alias));
            
            // will not come here if exception is thrown 
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        } 
    }

    /**
     * Removes leading "OID." from oid String passed
     *
     * @param oid string that may contain leading "OID."
     * @return string passed without leading "OID." 
     */
    public static String normalize(String oid) {
        return oid.startsWith("OID.") //$NON-NLS-1$
            ? oid.substring(4)
            : oid;
    }

    /**
     * Present all internal maps as formatted string
     * @return Internal maps String representation
     */
    public static String dump() {
        StringBuffer sb = new StringBuffer("alg2OidMap: "); //$NON-NLS-1$
        sb.append(alg2OidMap);
        sb.append("\noid2AlgMap: "); //$NON-NLS-1$
        sb.append(oid2AlgMap);
        sb.append("\nalgAliasesMap: "); //$NON-NLS-1$
        sb.append(algAliasesMap);
        return sb.toString();
    }
}
