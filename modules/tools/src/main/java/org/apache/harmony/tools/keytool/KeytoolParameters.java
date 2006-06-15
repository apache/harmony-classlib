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
    private String keyAlg;

    // digital signature algorithm
    private String sigAlg;

    // X.500 Distinguished Name to generate a certificate
    private String dName;

    // name of the file to import/export certificates
    private String fileName;

    // alias to access the issuer's certificate (certificate which a newly
    // generated certificate can be signed with)
    private String issuerAlias;

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

    // password to access the issuer's certificate (see issuerAlias)
    private char [] issuerPass;

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
    
    /**
     * The method sets the fields to default values. If there is no default
     * value the field is set to null.
     */
    void setDefault() {
        keyStore = null;
        needSaveKS = false;
        storePath = null;
        storeType = KeyStore.getDefaultType();
        provider = null;
        storePass = null;
        alias = null;
        keyAlg = null;
        keySize = 1024;
        sigAlg = null;
        dName = null;
        keyPass = null;
        newPasswd = null;
        validity = 90;
        fileName = null;
        noPrompt = false;
        trustCACerts = false;
        rfc = false;
        verbose = false;
        isSecretKey = false;
        issuerAlias = null;
        X509version = 3;
        certSerialNr = 0;
        isKeyStore = true;
        command = Command.HELP;
    }

    // getters and setters down here.
    /**
     * @return Returns the keystore to work with.
     */
    KeyStore getKeyStore() {
        return keyStore;
    }

    /**
     * @param keyStore
     *            The KeyStore to set as keystore worked with.
     */
    void setKeyStore(KeyStore keyStore) {
        this.keyStore = keyStore;
    }

    /**
     * @return Returns true if keystore is to be saved, false - otherwise.
     */
    public boolean isNeedSaveKS() {
        return needSaveKS;
    }

    /**
     * @param needSaveKS -
     *            if true keystore is to be saved, if false - it is not.
     */
    public void setNeedSaveKS(boolean needSaveKS) {
        this.needSaveKS = needSaveKS;
    }

    /**
     * @return Returns the alias used to access the keystore entry.
     */
    String getAlias() {
        return alias;
    }

    /**
     * @param alias
     *            The alias to access the keystore entry.
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * @return Returns the alias to access the issuer's certificate (certificate
     *         which a newly generated certificate can be signed with)
     */
    String getIssuerAlias() {
        return issuerAlias;
    }

    /**
     * @param issuerAlias
     *            The alias to access the issuer's certificate (certificate
     *            which a newly generated certificate can be signed with)
     */
    public void setIssuerAlias(String issuerAlias) {
        this.issuerAlias = issuerAlias;
    }

    /**
     * @return Returns the certstore path to keep CRLs in.
     */
    String getCrlStore() {
        return crlStore;
    }

    /**
     * @param crlStore
     *            certstore path to keep CRLs in.
     */
    public void setCrlStore(String crlStore) {
        this.crlStore = crlStore;
    }

    /**
     * @return Returns the destination alias to copy key pair to
     */
    String getDestAlias() {
        return destAlias;
    }

    /**
     * @param destAlias
     *            The destination alias to copy key pair to
     */
    public void setDestAlias(String destAlias) {
        this.destAlias = destAlias;
    }

    /**
     * @return Returns the certificate serial number
     */
    int getCertSerialNr() {
        return certSerialNr;
    }

    /**
     * @param certSerialNr
     *            The certificate serial number
     */
    public void setCertSerialNr(int certSerialNr) {
        this.certSerialNr = certSerialNr;
    }

    /**
     * @return Returns the command to perform
     */
    Command getCommand() {
        return command;
    }

    /**
     * @param command
     *            The command to perform
     */
    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * @return Returns the X.500 Distinguished Name to generate a certificate
     */
    String getDName() {
        return dName;
    }

    /**
     * @param name
     *            The X.500 Distinguished Name to generate a certificate
     */
    public void setDName(String name) {
        dName = name;
    }

    /**
     * @return Returns the name of the file to import/export certificates
     */
    String getFileName() {
        return fileName;
    }

    /**
     * @param fileName
     *            The name of the file to import/export certificates
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return Returns true if the store to work with is a keystore, false - if
     *         a certstore
     */
    boolean isKeyStore() {
        return isKeyStore;
    }

    /**
     * @param isKeyStore
     *            set true if the store worked with is a keystore, false - if a
     *            certstore
     */
    void setIsKeyStore(boolean isKeyStore) {
        this.isKeyStore = isKeyStore;
    }

    /**
     * @return Returns the algorithm name to get instance of KeyPairGenerator,
     *         KeyFactory, etc.
     */
    String getKeyAlg() {
        return keyAlg;
    }

    /**
     * @param keyAlg
     *            algorithm name to get instance of KeyPairGenerator,
     *            KeyFactory, etc.
     */
    public void setKeyAlg(String keyAlg) {
        this.keyAlg = keyAlg;
    }

    /**
     * @return Returns the password to access the key entry
     */
    char[] getKeyPass() {
        return keyPass;
    }

    /**
     * @param keyPass
     *            password to access the key entry
     */
    public void setKeyPass(char[] keyPass) {
        this.keyPass = keyPass;
    }

    /**
     * @return Returns the size of the key to generate
     */
    int getKeySize() {
        return keySize;
    }

    /**
     * @param keySize
     *            The size of the key to generate
     */
    public void setKeySize(int keySize) {
        this.keySize = keySize;
    }

    /**
     * @return Returns the new password to change the old one to
     */
    char[] getNewPasswd() {
        return newPasswd;
    }

    /**
     * @param newPasswd
     *            The new password to change the old one to
     */
    public void setNewPasswd(char[] newPasswd) {
        this.newPasswd = newPasswd;
    }

    /**
     * @return password to access the issuer's certificate (certificate which a
     *         newly generated certificate can be signed with)
     */
    char[] getIssuerPass() {
        return issuerPass;
    }

    /**
     * @param issuerPass
     *            password to access the issuer's certificate (certificate which
     *            a newly generated certificate can be signed with)
     */
    public void setIssuerPass(char[] issuerPass) {
        this.issuerPass = issuerPass;
    }

    /**
     * @return Returns true if unspecified parameters should be prompted for,
     *         false - otherwise
     */
    boolean isNoPrompt() {
        return noPrompt;
    }

    /**
     * @param noPrompt
     *            Set true if unspecified parameters should be prompted for,
     *            false if not
     */
    public void setNoPrompt(boolean noPrompt) {
        this.noPrompt = noPrompt;
    }

    /**
     * @return Returns the name of the provider to use
     */
    String getProvider() {
        return provider;
    }

    /**
     * @param provider
     *            the name of the provider to use
     */
    public void setProvider(String provider) {
        this.provider = provider;
    }

    /**
     * @return Returns true if the certificate should be printed or exported in
     *         printable format, false - if in binary format
     */
    boolean isRfc() {
        return rfc;
    }

    /**
     * @param rfc
     *            set true if the certificate should be printed or exported in
     *            printable format, false - if in binary format
     */
    public void setRfc(boolean rfc) {
        this.rfc = rfc;
    }

    /**
     * @return Returns true if a secret key should be generated, false - if a
     *         key pair.
     */
    boolean isSecretKey() {
        return isSecretKey;
    }

    /**
     * @param isSecretKey
     *            set true if a secret key should be generated, false - if a key
     *            pair.
     */
    public void setSecretKey(boolean secretKey) {
        this.isSecretKey = secretKey;
    }

    /**
     * @return Returns the digital signature algorithm
     */
    String getSigAlg() {
        return sigAlg;
    }

    /**
     * @param sigAlg
     *            The digital signature algorithm to set.
     */
    public void setSigAlg(String sigAlg) {
        this.sigAlg = sigAlg;
    }

    /**
     * @return Returns the password to access the store
     */
    char[] getStorePass() {
        return storePass;
    }

    /**
     * @param storePass
     *            The password to access the store
     */
    public void setStorePass(char[] storePass) {
        this.storePass = storePass;
    }

    /**
     * @return Returns the type of the store
     */
    String getStoreType() {
        return storeType;
    }

    /**
     * @param storeType
     *            The type of the store to set.
     */
    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    /**
     * @return Returns true if the certificates from cacerts file should be
     *         considered for the chain of trust, false - if not
     */
    boolean isTrustCACerts() {
        return trustCACerts;
    }

    /**
     * @param trustCACerts
     *            set true if the certificates from cacerts file should be
     *            considered for the chain of trust, false - if not.
     */
    public void setTrustCACerts(boolean trustCACerts) {
        this.trustCACerts = trustCACerts;
    }

    /**
     * @return Returns the validity period of the generated certificate in days
     *         from the current moment
     */
    int getValidity() {
        return validity;
    }

    /**
     * @param validity
     *            The validity period of the generated certificate in days from
     *            the current moment
     */
    public void setValidity(int validity) {
        this.validity = validity;
    }

    /**
     * @return Returns true if the keytool should print the detailed information
     *         on the operation, false - if not
     */
    boolean isVerbose() {
        return verbose;
    }

    /**
     * @param verbose
     *            set true if the keytool should print the detailed information
     *            on the operation, false - if not
     */
    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    /**
     * @return Returns the X.509 protocol version to use when generating a
     *         certificate
     */
    int getX509version() {
        return X509version;
    }

    /**
     * @param x509version
     *            The X.509 protocol version to use when generating a
     *            certificate
     */
    public void setX509version(int x509version) {
        X509version = x509version;
    }

    /**
     * @return Returns path to the keystore or certstore depending on a command.
     */
    String getStorePath() {
        return storePath;
    }

    /**
     * @param storePath
     *            The path to the keystore or certstore (depending on a
     *            command).
     */
    public void setStorePath(String storePath) {
        this.storePath = storePath;
    }

}

