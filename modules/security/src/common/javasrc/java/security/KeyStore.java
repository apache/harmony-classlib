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
* @author Vera Y. Petrashkova
* @version $Revision$
*/

package java.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;

import javax.crypto.SecretKey;
import javax.security.auth.DestroyFailedException;
import javax.security.auth.Destroyable;
import javax.security.auth.callback.CallbackHandler;

import org.apache.harmony.security.fortress.Engine;


/**
 * @com.intel.drl.spec_ref
 * 
 */

public class KeyStore {

    // Store KeyStore SERVICE name
    private static final String SERVICE = "KeyStore";

    // Used to access common engine functionality
    private static Engine engine = new Engine(SERVICE);

    //  Store KeyStore property name
    private static final String PROPERTYNAME = "keystore.type";

    //  Store default KeyStore type
    private static final String DEFAULT_KEYSTORE_TYPE = "jks";

    // Message to report about non-initialized key store object
    private static final String NOTINITKEYSTORE = "KeyStore was not initialized";

    // Store KeyStore state (initialized or not)
    private boolean isInit;

    // Store used KeyStoreSpi
    private final KeyStoreSpi implSpi;

    // Store used provider
    private final Provider provider;

    // Store used type
    private final String type;

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    protected KeyStore(KeyStoreSpi keyStoreSpi, Provider provider, String type) {
        this.type = type;
        this.provider = provider;
        this.implSpi = keyStoreSpi;
        isInit = false;
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     * throws NullPointerException if type is null
     */
    public static KeyStore getInstance(String type) throws KeyStoreException {
        if (type == null) {
            throw new NullPointerException("type is null");
        }
        synchronized (engine) {
            try {
                engine.getInstance(type, null);
                return new KeyStore((KeyStoreSpi) engine.spi, engine.provider, type);
            } catch (NoSuchAlgorithmException e) {
                throw new KeyStoreException(e.getMessage());
            }
        }
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     * throws NullPointerException if type is null (instead of
     * NoSuchAlgorithmException) as in 1.4 release
     */
    public static KeyStore getInstance(String type, String provider)
            throws KeyStoreException, NoSuchProviderException {
        if ((provider == null) || (provider.length() == 0)) {
            throw new IllegalArgumentException("Provider is null or empty");
        }
        Provider impProvider = Security.getProvider(provider);
        if (impProvider == null) {
            throw new NoSuchProviderException(provider);
        }
        try {
            return getInstance(type, impProvider);
        } catch (Exception e) {
            throw new KeyStoreException(e.getMessage(), e);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     * throws NullPointerException if type is null (instead of
     * NoSuchAlgorithmException) as in 1.4 release
     */
    public static KeyStore getInstance(String type, Provider provider)
            throws KeyStoreException {
        // check parameters
        if (provider == null) {
            throw new IllegalArgumentException("Provider is null");
        }
        if (type == null) {
            throw new NullPointerException("type is null");
        }
        // return KeyStore instance
        synchronized (engine) {
            try {
                engine.getInstance(type, provider, null);
                return new KeyStore((KeyStoreSpi) engine.spi, provider, type);
            } catch (Exception e) {
            // override exception
                throw new KeyStoreException(e.getMessage());
            }
        }
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static final String getDefaultType() {
        String dt = (String) AccessController.doPrivileged(
                new java.security.PrivilegedAction() {
                    public Object run() {
                        return Security.getProperty(PROPERTYNAME);
                    }
                }
            );
        return (dt == null ? DEFAULT_KEYSTORE_TYPE : dt);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final Provider getProvider() {
        return provider;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final String getType() {
        return type;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final Key getKey(String alias, char[] password)
            throws KeyStoreException, NoSuchAlgorithmException,
            UnrecoverableKeyException {
        if (!isInit) {
            throw new KeyStoreException(NOTINITKEYSTORE);
        }
        return implSpi.engineGetKey(alias, password);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final Certificate[] getCertificateChain(String alias)
            throws KeyStoreException {
        if (!isInit) {
            throw new KeyStoreException(NOTINITKEYSTORE);
        }
        return implSpi.engineGetCertificateChain(alias);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final Certificate getCertificate(String alias)
            throws KeyStoreException {
        if (!isInit) {
            throw new KeyStoreException(NOTINITKEYSTORE);
        }
        return implSpi.engineGetCertificate(alias);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final Date getCreationDate(String alias) throws KeyStoreException {
        if (!isInit) {
            throw new KeyStoreException(NOTINITKEYSTORE);
        }
        return implSpi.engineGetCreationDate(alias);
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     * 1.4.2 and 1.5 releases throw unspecified NullPointerException -
     * when alias is null IllegalArgumentException - when password is null
     * IllegalArgumentException - when key is instance of PrivateKey and chain
     * is null or empty
     */
    public final void setKeyEntry(String alias, Key key, char[] password,
            Certificate[] chain) throws KeyStoreException {
        if (!isInit) {
            throw new KeyStoreException(NOTINITKEYSTORE);
        }
        if (alias == null) {
            throw new NullPointerException("alias is null");
        }
        if (key == null) {
            throw new KeyStoreException("key is null");
        }
        // Certificate chain is required for PrivateKey
        if ((key instanceof PrivateKey)
                && ((chain == null) || chain.length == 0)) {
            throw new KeyStoreException(
                    "Certificate chain is not defined for Private key ");
        }
        implSpi.engineSetKeyEntry(alias, key, password, chain);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final void setKeyEntry(String alias, byte[] key, Certificate[] chain)
            throws KeyStoreException {
        if (!isInit) {
            throw new KeyStoreException(NOTINITKEYSTORE);
        }
        implSpi.engineSetKeyEntry(alias, key, chain);
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     * 1.4.2 and 1.5 releases throw unspecified NullPointerExcedption
     * when alias is null
     */
    public final void setCertificateEntry(String alias, Certificate cert)
            throws KeyStoreException {
        if (!isInit) {
            throw new KeyStoreException(NOTINITKEYSTORE);
        }
        if (alias == null) {
            throw new NullPointerException("alias is null");
        }
        implSpi.engineSetCertificateEntry(alias, cert);
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     * 1.4.2 and 1.5 releases throw NullPointerExcedption when alias is null
     */
    public final void deleteEntry(String alias) throws KeyStoreException {
        if (!isInit) {
            throw new KeyStoreException(NOTINITKEYSTORE);
        }
        if (alias == null) {
            throw new NullPointerException("alias is null");
        }
        implSpi.engineDeleteEntry(alias);
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     * FIXME: update to Enumeration <String>
     */
    public final Enumeration aliases() throws KeyStoreException {
        if (!isInit) {
            throw new KeyStoreException(NOTINITKEYSTORE);
        }
        return implSpi.engineAliases();
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     * 1.4.2 and 1.5 releases throw unspecified NullPointerException when
     * alias is null
     */
    public final boolean containsAlias(String alias) throws KeyStoreException {
        if (!isInit) {
            throw new KeyStoreException(NOTINITKEYSTORE);
        }
        if (alias == null) {
            throw new NullPointerException("alias is null");
        }
        return implSpi.engineContainsAlias(alias);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final int size() throws KeyStoreException {
        if (!isInit) {
            throw new KeyStoreException(NOTINITKEYSTORE);
        }
        return implSpi.engineSize();
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     * jdk1.4.2 and 1.5 releases throw unspecified NullPointerException
     * when alias is null
     */
    public final boolean isKeyEntry(String alias) throws KeyStoreException {
        if (!isInit) {
            throw new KeyStoreException(NOTINITKEYSTORE);
        }
        if (alias == null) {
            throw new NullPointerException("alias is null");
        }
        return implSpi.engineIsKeyEntry(alias);
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     * jdk1.4.2 and 1.5 releases throw unspecified NullPointerException
     * when alias is null
     */
    public final boolean isCertificateEntry(String alias)
            throws KeyStoreException {
        if (!isInit) {
            throw new KeyStoreException(NOTINITKEYSTORE);
        }
        if (alias == null) {
            throw new NullPointerException("alias is null");
        }
        return implSpi.engineIsCertificateEntry(alias);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final String getCertificateAlias(Certificate cert)
            throws KeyStoreException {
        if (!isInit) {
            throw new KeyStoreException(NOTINITKEYSTORE);
        }
        return implSpi.engineGetCertificateAlias(cert);
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     * throws IOException when stream or password is null
     */
    public final void store(OutputStream stream, char[] password)
            throws KeyStoreException, IOException, NoSuchAlgorithmException,
            CertificateException {
        if (!isInit) {
            throw new KeyStoreException(NOTINITKEYSTORE);
        }
        if (stream == null) {
            throw new IOException("stream is null");
        }
        if (password == null) {
            throw new IOException("password is null");
        }
        implSpi.engineStore(stream, password);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final void store(LoadStoreParameter param) throws KeyStoreException,
            IOException, NoSuchAlgorithmException, CertificateException {
        if (!isInit) {
            throw new KeyStoreException(NOTINITKEYSTORE);
        }
        if (param == null) {
            throw new IOException("LoadSroreParameter is null");
        }
        implSpi.engineStore(param);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final void load(InputStream stream, char[] password)
            throws IOException, NoSuchAlgorithmException, CertificateException {
        implSpi.engineLoad(stream, password);
        isInit = true;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final void load(LoadStoreParameter param) throws IOException,
            NoSuchAlgorithmException, CertificateException {
        if (param == null) {
            throw new IOException("LoadSroreParameter is null");
        }
        implSpi.engineLoad(param);
        isInit = true;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public final Entry getEntry(String alias, ProtectionParameter param)
            throws NoSuchAlgorithmException, UnrecoverableEntryException,
            KeyStoreException {
        if (alias == null) {
            throw new NullPointerException("alias is null");
        }
        if (!isInit) {
            throw new KeyStoreException(NOTINITKEYSTORE);
        }
        return implSpi.engineGetEntry(alias, param);
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     * 1.5 release throws unspecified NullPointerExcedption when alias or
     * entry is null
     */
    public final void setEntry(String alias, Entry entry,
            ProtectionParameter param) throws KeyStoreException {
        if (!isInit) {
            throw new KeyStoreException(NOTINITKEYSTORE);
        }
        if (alias == null) {
            throw new NullPointerException("alias is null");
        }
        if (entry == null) {
            throw new NullPointerException("entry is null");
        }
        implSpi.engineSetEntry(alias, entry, param);
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     * FIXME: for 1.5 update to Class <? extends KeyStore.Entry>
     */
    public final boolean entryInstanceOf(String alias, Class entryClass)
            throws KeyStoreException {
        if (alias == null)
            throw new NullPointerException("alias is null");
        if (entryClass == null)
            throw new NullPointerException("entryClass is null");

        if (!isInit) {
            throw new KeyStoreException(NOTINITKEYSTORE);
        }
        return implSpi.engineEntryInstanceOf(alias, entryClass);
    }

    /**
     * 
     * @com.intel.drl.spec_ref
     * 
     */
    public abstract static class Builder {
        /**
         * @com.intel.drl.spec_ref
         *  
         */
        protected Builder() {
        }

        /**
         * @com.intel.drl.spec_ref
         *  
         */
        public abstract KeyStore getKeyStore() throws KeyStoreException;

        /**
         * @com.intel.drl.spec_ref
         *  
         */
        public abstract ProtectionParameter getProtectionParameter(String alise)
                throws KeyStoreException;

        /**
         * @com.intel.drl.spec_ref
         *  
         */
        public static Builder newInstance(KeyStore keyStore,
                ProtectionParameter protectionParameter) {
            if (keyStore == null)
                throw new NullPointerException("keystore is null");
            if (protectionParameter == null)
                throw new NullPointerException("protectionParameter is null");

            if (!keyStore.isInit) {
                throw new IllegalArgumentException(NOTINITKEYSTORE);
            }
            return (Builder) new BuilderImpl(keyStore, protectionParameter,
                    null, null, null, null);
        }

        /**
         * @com.intel.drl.spec_ref
         *  
         */
        public static Builder newInstance(String type, Provider provider,
                File file, ProtectionParameter protectionParameter) {
            // check null parameters
            if (type == null) {
                throw new NullPointerException("type  is null");
            }
            if (protectionParameter == null) {
                throw new NullPointerException("protectionParameter is null");
            }
            if (file == null) {
                throw new NullPointerException("file is null");
            }
            // protection parameter should be PasswordProtection or
            // CallbackHandlerProtection
            if (!(protectionParameter instanceof PasswordProtection)
                    && !(protectionParameter instanceof CallbackHandlerProtection)) {
                throw new IllegalArgumentException(
                        "protectionParameter is neither PasswordProtection nor CallbackHandlerProtection instance");
            }
            // check file parameter
            if (!file.exists()) {
                throw new IllegalArgumentException("File: " + file.getName()
                        + " does not exist");
            }
            if (!file.isFile()) {
                throw new IllegalArgumentException(file.getName()
                        + " does not refer to a normal file");
            }
            // create new instance
            return (Builder) new BuilderImpl(null, protectionParameter, file,
                    type, provider, AccessController.getContext());
        }

        /**
         * @com.intel.drl.spec_ref
         *  
         */
        public static Builder newInstance(String type, Provider provider,
                ProtectionParameter protectionParameter) {
            if (type == null) {
                throw new NullPointerException("type is null");
            }
            if (protectionParameter == null) {
                throw new NullPointerException("protectionParameter is null");
            }
            return (Builder) new BuilderImpl(null, protectionParameter, null,
                    type, provider, AccessController.getContext());
        }

        /*
         * This class is implemenation of abstract class KeyStore.Builder
         * 
         * @author Vera Petrashkova
         * 
         * @version
         */
        private static class BuilderImpl extends Builder {
            // Store used KeyStore
            private KeyStore keyStore;

            // Store used ProtectionParameter
            private ProtectionParameter protParameter;

            // Store used KeyStore type
            private final String typeForKeyStore;

            // Store used KeyStore provider
            private final Provider providerForKeyStore;

            // Store used file for KeyStore loading
            private final File fileForLoad;

            // Store getKeyStore method was invoked or not for KeyStoreBuilder
            private boolean isGetKeyStore = false;

            // Store last Exception in getKeyStore()
            private KeyStoreException lastException;

            // Store AccessControlContext which is used in getKeyStore() method
            private final AccessControlContext accControlContext;

            //
            // Constructor BuilderImpl initializes private fields: keyStore,
            // protParameter, typeForKeyStore providerForKeyStore fileForLoad,
            // isGetKeyStore
            //
            BuilderImpl(KeyStore ks, ProtectionParameter pp, File file,
                    String type, Provider provider, AccessControlContext context) {
                super();
                keyStore = ks;
                protParameter = pp;
                fileForLoad = file;
                typeForKeyStore = type;
                providerForKeyStore = provider;
                isGetKeyStore = false;
                lastException = null;
                accControlContext = context;
            }

            //
            // Implementation of abstract getKeyStore() method If
            // KeyStoreBuilder encapsulates KeyStore object then this object is
            // returned
            // 
            // If KeyStoreBuilder encapsulates KeyStore type and provider then
            // KeyStore is created using these parameters. If KeyStoreBuilder
            // encapsulates file and ProtectionParameter then KeyStore data are
            // loaded from FileInputStream that is created on file. If file is
            // not defined then KeyStore object is initialized with null
            // InputStream and null password.
            // 
            // Result KeyStore object is returned.
            //
            public synchronized KeyStore getKeyStore() throws KeyStoreException {
                // If KeyStore was created but in final block some exception was
                // thrown
                // then it was stored in lastException variable and will be
                // thrown
                // all subsequent calls of this method.
                if (lastException != null) {
                    throw lastException;
                }
                if (keyStore != null) {
                    isGetKeyStore = true;
                    return keyStore;
                }
                KeyStore ks;
                char[] passwd = null;
                FileInputStream fis = null;
                try {
                    // get KeyStore instance using type or type and provider
                    ks = (providerForKeyStore == null ? KeyStore
                            .getInstance(typeForKeyStore) : KeyStore
                            .getInstance(typeForKeyStore, providerForKeyStore));
                    // protection parameter should be PasswordProtection
                    // or CallbackHandlerProtection
                    if (protParameter instanceof PasswordProtection) {
                        PasswordProtection pwp = (PasswordProtection) protParameter;
                        passwd = ((PasswordProtection) protParameter)
                                .getPassword();
                    } else if (protParameter instanceof CallbackHandlerProtection) {
                        passwd = KeyStoreSpi
                                .getPasswordFromCallBack(protParameter);
                    } else {
                        throw new KeyStoreException(
                                "ProtectionParameter object is not PasswordProtection "
                                        + "and  CallbackHandlerProtection");
                    }
                    // load KeyStore from file
                    if (fileForLoad != null) {
                        fis = new FileInputStream(fileForLoad);
                        ks.load(fis, passwd);
                    } else {
                        ks.load(new TmpLSParameter(protParameter));
                    }
                    isGetKeyStore = true;
                    keyStore = ks;
                    return keyStore;
                } catch (KeyStoreException e) {
                    // Store exception
                    lastException = e;
                    throw lastException = e;
                } catch (Exception e) {
                    // Override exception
                    throw lastException = new KeyStoreException(e);
                } finally {
                    // close file input stream
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            throw lastException = new KeyStoreException(e);
                        }
                    }
                }
            }

            //
            // This is implementation of abstract method
            // getProtectionParameter(String alias)
            // 
            // Return: ProtectionParameter to get Entry which was saved in
            // KeyStore with defined alias
            //
            public synchronized ProtectionParameter getProtectionParameter(
                    String alias) throws KeyStoreException {
                if (alias == null) {
                    throw new NullPointerException("alias is null");
                }
                if (!isGetKeyStore) {
                    throw new IllegalStateException(
                            "getKeyStore() was not invoked");
                }
                return protParameter;
            }
        }

        /*
         * Implementation of LoadStoreParameter interface
         * 
         * @author Vera Petrashkova
         * 
         * @version
         */
        private class TmpLSParameter implements LoadStoreParameter {

            // Store used protection parameter
            private final ProtectionParameter protPar;

            /**
             * Creates TmpLoadStoreParameter object
             */
            public TmpLSParameter(ProtectionParameter protPar) {
                this.protPar = protPar;
            }

            /**
             * This method returns protection parameter
             */
            public ProtectionParameter getProtectionParameter() {
                return protPar;
            }
        }
    }

    /**
     * 
     * @com.intel.drl.spec_ref
     * 
     */
    public static class CallbackHandlerProtection implements
            ProtectionParameter {
        // Store CallbackHandler
        private final CallbackHandler callbackHandler;

        /**
         * @com.intel.drl.spec_ref
         *  
         */
        public CallbackHandlerProtection(CallbackHandler handler) {
            if (handler == null) {
                throw new NullPointerException("handler is null");
            }
            this.callbackHandler = handler;
        }

        /**
         * @com.intel.drl.spec_ref
         *  
         */
        public CallbackHandler getCallbackHandler() {
            return callbackHandler;
        }
    }

    /**
     * 
     * @com.intel.drl.spec_ref
     * 
     */
    public static interface Entry {
    }

    /**
     * 
     * @com.intel.drl.spec_ref
     * 
     */
    public static interface LoadStoreParameter {
        /**
         * @com.intel.drl.spec_ref
         *  
         */
        public ProtectionParameter getProtectionParameter();
    }

    /**
     * 
     * @com.intel.drl.spec_ref
     * 
     */
    public static class PasswordProtection implements ProtectionParameter,
            Destroyable {

        // Store passwoed
        private char[] password;

        /**
         * @com.intel.drl.spec_ref
         *  
         */
        public PasswordProtection(char[] password) {
            this.password = password;
        }

        /**
         * @com.intel.drl.spec_ref
         *  
         */
        public synchronized char[] getPassword() {
            if (password == null) {
                throw new IllegalStateException("password was destroyed");
            }
            return password;
        }

        /**
         * @com.intel.drl.spec_ref
         *  
         */
        public synchronized void destroy() throws DestroyFailedException {
            Arrays.fill(password, '\u0000');
            password = null;
        }

        /**
         * @com.intel.drl.spec_ref
         *  
         */
        public synchronized boolean isDestroyed() {
            return (password == null);
        }
    }

    /**
     * 
     * @com.intel.drl.spec_ref
     * 
     */
    public static interface ProtectionParameter {
    }

    /**
     * 
     * @com.intel.drl.spec_ref
     * 
     */
    public static final class PrivateKeyEntry implements Entry {
        // Store Certificate chain
        private Certificate[] chain;

        // Store PrivateKey
        private PrivateKey privateKey;

        /**
         * @com.intel.drl.spec_ref
         *  
         */
        public PrivateKeyEntry(PrivateKey privateKey, Certificate[] chain) {
            if (privateKey == null)
                throw new NullPointerException("privateKey is null");
            if (chain == null)
                throw new NullPointerException("chain is null");

            if (chain.length == 0) {
                throw new IllegalArgumentException("chain length equals 0");
            }
            // Match algorithm of private key and algorithm of publick key from
            // the end certificate
            String s = chain[0].getType();
            if (!(chain[0].getPublicKey().getAlgorithm()).equals(privateKey
                    .getAlgorithm())) {
                throw new IllegalArgumentException(
                        "Algorithm of private key does not "
                                + "match algorithm of public key in end certificate of entry "
                                + "(with index number: 0)");
            }
            // Match certificate types
            for (int i = 1; i < chain.length; i++) {
                if (!s.equals(chain[i].getType())) {
                    throw new IllegalArgumentException(
                            "Certificates from the given chain have different types");
                }
            }
            // clone chain - this.chain = (Certificate[])chain.clone();
            this.chain = new Certificate[chain.length];
            System.arraycopy(chain, 0, this.chain, 0, chain.length);
            this.privateKey = privateKey;
        }

        /**
         * @com.intel.drl.spec_ref
         *  
         */
        public PrivateKey getPrivateKey() {
            return privateKey;
        }

        /**
         * @com.intel.drl.spec_ref
         *  
         */
        public Certificate[] getCertificateChain() {
            return chain;
        }

        /**
         * @com.intel.drl.spec_ref
         *  
         */
        public Certificate getCertificate() {
            return chain[0];
        }

        /**
         * @com.intel.drl.spec_ref
         *  
         */
        public String toString() {
            StringBuffer sb = new StringBuffer(
                    "PrivateKeyEntry: number of elements in certificate chain is ");
            sb.append(Integer.toString(chain.length));
            sb.append("\n");
            for (int i = 0; i < chain.length; i++) {
                sb.append(chain[i].toString());
                sb.append("\n");
            }
            return sb.toString();
        }
    }

    /**
     * 
     * @com.intel.drl.spec_ref
     * 
     */
    public static final class SecretKeyEntry implements Entry {

        // Store SecretKey
        private final SecretKey secretKey;

        /**
         * @com.intel.drl.spec_ref
         *  
         */
        public SecretKeyEntry(SecretKey secretKey) {
            if (secretKey == null) {
                throw new NullPointerException("secretKey is null");
            }
            this.secretKey = secretKey;
        }

        /**
         * @com.intel.drl.spec_ref
         *  
         */
        public SecretKey getSecretKey() {
            return secretKey;
        }

        /**
         * @com.intel.drl.spec_ref
         *  
         */
        public String toString() {
            StringBuffer sb = new StringBuffer("SecretKeyEntry: algorithm - ");
            sb.append(secretKey.getAlgorithm());
            return sb.toString();
        }
    }

    /**
     * 
     * @com.intel.drl.spec_ref
     * 
     */
    public static final class TrustedCertificateEntry implements Entry {

        // Store trusted Certificate
        private final Certificate trustCertificate;

        /**
         * @com.intel.drl.spec_ref
         *  
         */
        public TrustedCertificateEntry(Certificate trustCertificate) {
            if (trustCertificate == null) {
                throw new NullPointerException("trustCertificate is null");
            }
            this.trustCertificate = trustCertificate;
        }

        /**
         * @com.intel.drl.spec_ref
         *  
         */
        public Certificate getTrustedCertificate() {
            return trustCertificate;
        }

        /**
         * @com.intel.drl.spec_ref
         *  
         */
        public String toString() {
            return "TrustedCertificateEntry: \n".concat(trustCertificate
                    .toString());
        }
    }
}