/*
 * Copyright 2006 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.apache.harmony.tools.keytool;

import java.io.File;
import java.security.KeyStore;

/**
 * The class encapsulates paramaters for Keytool most of which are ususally
 * given in command line.
 */
public class KeytoolParameters {
    // TODO
    /**
     * Default location of the keystore. Used when the value is not supplied by
     * the user.
     */
    public static final String defaultKeystorePath = System
            .getProperty("user.home")
            + File.separator + ".keystore";

    /**
     * Location of cacerts file, containing the certificates from root
     * certificate authorities (usually self-signed).
     */
    public static final String cacertsPath = System.getProperty("java.home")
            + File.separator + "lib" + File.separator + "security"
            + File.separator + "cacerts";

    // the keystore to work with
    private KeyStore keyStore;

    // shows should the keystore be saved or not;
    private boolean needSaveKS;

    // path to the keystore or certstore depending on a command.
    private String storePath;

    // type of the store. Default type is set in java.security file.
    private String storeType = KeyStore.getDefaultType();

    // the name of the provider to use
    private String provider;

    // alias to access an entry in keystore
    private String alias;

    // algorithm name to get instance of KeyPairGenerator, KeyFactory, etc.
    private String keyAlg = "DSA";

    // digital signature algorithm
    private String sigAlg;

    // X.500 Distinguished Name to generate a certificate
    private String dName;

    // name of the file to import/export certificates
    private String fileName;

    // alias to access an entry in certstore
    private String certAlias;

    // certstore to keep a CRL in
    private String crlStore;

    // used in keyclone. Shows the destination alias to copy key pair to
    private String destAlias;

    // password to access the store
    private char[] storePass;

    // password to access the key entry
    private char[] keyPass;

    // new password to change the old one to
    private char[] newPasswd;

    // size of the key to generate
    private int keySize = 1024;

    // validity period of the generated certificate in days from the current
    // moment
    private int validity = 90;

    // X.509 protocol version to use when generating a certificate
    private int X509version = 3;

    // certificate serial number
    private int certSerialNr;

    // should the unspecified parameters be prompted for or not
    private boolean noPrompt;

    // used in import. Should the certificates from cacerts file be considered
    // for the chain of trust or not
    private boolean trustCACerts;

    // should the certificate be printed or exported in printable or binary
    // format
    private boolean rfc;

    // should the keytool print the detailed information on the operation or not
    private boolean verbose;

    // should a secret key or a key pair be generated
    private boolean isSecretKey;

    // true if the store worked with is a keystore, false - if a certstore
    private boolean isKeyStore = true;

    // command to perform
    private Command command = Command.HELP;
}
