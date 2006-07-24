/*
 *  Copyright 2006 The Apache Software Software Foundation or its licensors, as applicable.
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


package org.apache.harmony.security.provider.crypto;


import java.security.Provider;
import java.security.AccessController;


/**
 * Implementation of Provider for MessageDigest
 * using a Secure Hash Algorithm, SHA-1;
 * see SECURE HASH STANDARD, FIPS PUB 180-2
 * (http://csrc.nist.gov/publications/fips/fips180-2/fips180-2.pdf) <BR>
 * <BR>
 * The implementation supports "SHA-1" algorithms described in
 * JavaTM Cryptography Architecture, API Specification & Reference
 */


public final class CryptoProvider extends Provider {


    /**
     * Creates a Provider and puts parameters
     */
    public CryptoProvider() {

        super("Crypto", 1.0, "HARMONY CryptoProvider");

    //  names of classes implementing services
    final String MD_NAME = 
                 "org.apache.harmony.security.provider.crypto.SHA1_MessageDigestImpl";

        AccessController.doPrivileged( 
            new java.security.PrivilegedAction() {
                public Object run() {

                    put("MessageDigest.SHA-1", MD_NAME);
                    put("MessageDigest.SHA-1 ImplementedIn", "Software");
                    put("Alg.Alias.MessageDigest.SHA1", "SHA-1");
                    put("Alg.Alias.MessageDigest.SHA",  "SHA-1");

                    return null;
                }
            }
        );
    }
}
