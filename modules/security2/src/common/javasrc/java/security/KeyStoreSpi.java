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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.Enumeration;
import javax.crypto.SecretKey;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.PasswordCallback;

/**
 * @com.intel.drl.spec_ref
 * 
 */

public abstract class KeyStoreSpi {

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public KeyStoreSpi() {
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public abstract Key engineGetKey(String alias, char[] password)
            throws NoSuchAlgorithmException, UnrecoverableKeyException;

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public abstract Certificate[] engineGetCertificateChain(String alias);

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public abstract Certificate engineGetCertificate(String alias);

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public abstract Date engineGetCreationDate(String alias);

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public abstract void engineSetKeyEntry(String alias, Key key,
            char[] password, Certificate[] chain) throws KeyStoreException;

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public abstract void engineSetKeyEntry(String alias, byte[] key,
            Certificate[] chain) throws KeyStoreException;

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public abstract void engineSetCertificateEntry(String alias,
            Certificate cert) throws KeyStoreException;

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public abstract void engineDeleteEntry(String alias)
            throws KeyStoreException;

    /**
     * @com.intel.drl.spec_ref
     * 
     * FIXME: 1.5 updates are needed Enumeration <String>
     */
    public abstract Enumeration engineAliases();

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public abstract boolean engineContainsAlias(String alias);

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public abstract int engineSize();

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public abstract boolean engineIsKeyEntry(String alias);

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public abstract boolean engineIsCertificateEntry(String alias);

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public abstract String engineGetCertificateAlias(Certificate cert);

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public abstract void engineStore(OutputStream stream, char[] password)
            throws IOException, NoSuchAlgorithmException, CertificateException;

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public void engineStore(KeyStore.LoadStoreParameter param)
            throws IOException, NoSuchAlgorithmException, CertificateException {
        throw new UnsupportedOperationException("Not Supported operation");
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public abstract void engineLoad(InputStream stream, char[] password)
            throws IOException, NoSuchAlgorithmException, CertificateException;

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public void engineLoad(KeyStore.LoadStoreParameter param)
            throws IOException, NoSuchAlgorithmException, CertificateException {
        if (param == null) {
            engineLoad(null, null);
            return;
        }
        char[] pwd;
        KeyStore.ProtectionParameter pp = param.getProtectionParameter();
        if (pp == null) {
            throw new IllegalArgumentException(
                    "ProtectionParameter is not defined");
        }
        if (pp instanceof KeyStore.PasswordProtection) {
            try {
                pwd = ((KeyStore.PasswordProtection) pp).getPassword();
                engineLoad(null, pwd);
                return;
            } catch (Exception e) {
                throw new IllegalArgumentException(e.toString());
            }
        }
        if (pp instanceof KeyStore.CallbackHandlerProtection) {
            try {
                pwd = getPasswordFromCallBack(pp);
                engineLoad(null, pwd);
                return;
            } catch (Exception e) {
                throw new IllegalArgumentException(e.toString());
            }
        }
        throw new UnsupportedOperationException(
                "ProtectionParameter is neither PasswordProtection nor CallbackHandlerProtection");
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public KeyStore.Entry engineGetEntry(String alias,
            KeyStore.ProtectionParameter protParam) throws KeyStoreException,
            NoSuchAlgorithmException, UnrecoverableEntryException {
        if (!engineContainsAlias(alias)) {
            return null;
        }
        if (engineIsCertificateEntry(alias)) {
            return new KeyStore.TrustedCertificateEntry(
                    engineGetCertificate(alias));
        }
        char[] passW = null;
        if (protParam != null) {
            if (protParam instanceof KeyStore.PasswordProtection) {
                try {
                    passW = ((KeyStore.PasswordProtection) protParam)
                            .getPassword();
                } catch (IllegalStateException ee) {
                    throw new KeyStoreException("Password was destroyed", ee);
                }
            } else if (protParam instanceof KeyStore.CallbackHandlerProtection) {
                passW = getPasswordFromCallBack(protParam);
            } else {
                throw new UnrecoverableEntryException(
                        "ProtectionParameter object is not PasswordProtection: "
                                + protParam.toString());
            }
        }
        if (engineIsKeyEntry(alias)) {
            try {
                Key key = engineGetKey(alias, passW);
                if (key instanceof PrivateKey) {
                    return new KeyStore.PrivateKeyEntry((PrivateKey) key,
                            engineGetCertificateChain(alias));
                }
                if (key instanceof SecretKey) {
                    return new KeyStore.SecretKeyEntry((SecretKey) key);
                }
            } catch (UnrecoverableKeyException e) {
                throw new KeyStoreException(e);
            }
        }
        throw new NoSuchAlgorithmException("Unknown KeyStore.Entry object");
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public void engineSetEntry(String alias, KeyStore.Entry entry,
            KeyStore.ProtectionParameter protParam) throws KeyStoreException {
        if (entry == null) {
            throw new KeyStoreException("entry is null");
        }
        if (engineContainsAlias(alias)) {
            engineDeleteEntry(alias);
        }
        if (entry instanceof KeyStore.TrustedCertificateEntry) {
            KeyStore.TrustedCertificateEntry trE = (KeyStore.TrustedCertificateEntry) entry;
            engineSetCertificateEntry(alias, trE.getTrustedCertificate());
            return;
        }
        char[] passW = null;
        if (protParam instanceof KeyStore.PasswordProtection) {
            try {
                passW = ((KeyStore.PasswordProtection) protParam).getPassword();
            } catch (IllegalStateException ee) {
                throw new KeyStoreException("Password was destroyed", ee);
            }
        } else {
            if (protParam instanceof KeyStore.CallbackHandlerProtection) {
                try {
                    passW = getPasswordFromCallBack(protParam);
                } catch (Exception e) {
                    throw new KeyStoreException(e);
                }
            } else {
                throw new KeyStoreException(
                        "protParam should be PasswordProtection or CallbackHandlerProtection");
            }
        }
        if (entry instanceof KeyStore.PrivateKeyEntry) {
            KeyStore.PrivateKeyEntry prE = (KeyStore.PrivateKeyEntry) entry;
            engineSetKeyEntry(alias, prE.getPrivateKey(), passW, prE
                    .getCertificateChain());
            return;
        }
        if (entry instanceof KeyStore.SecretKeyEntry) {
            KeyStore.SecretKeyEntry skE = (KeyStore.SecretKeyEntry) entry;
            engineSetKeyEntry(alias, skE.getSecretKey(), passW, null);
            //            engineSetKeyEntry(alias, skE.getSecretKey().getEncoded(), null);
            return;
        }
        throw new KeyStoreException(
                "Entry object is neither PrivateKeyObject nor SecretKeyEntry "
                        + "nor TrustedCertificateEntry: " + entry.toString());
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     * FIXME: 1.5 updates Class <? extends KeyStore.Entry> should be done
     */
    public boolean engineEntryInstanceOf(String alias, Class entryClass) {
        if (!engineContainsAlias(alias)) {
            return false;
        }
        Class cl1 = null;
        Class cl2 = null;
        try {
            if (engineIsCertificateEntry(alias)) {
                cl1 = Class
                        .forName("java.security.KeyStore$TrustedCertificateEntry");
                return ((cl1 != null) ? entryClass.isAssignableFrom(cl1)
                        : false);
            }
            if (engineIsKeyEntry(alias)) {
                cl1 = Class.forName("java.security.KeyStore$PrivateKeyEntry");
                cl2 = Class.forName("java.security.KeyStore$SecretKeyEntry");
            }
            if ((cl1 != null) && entryClass.isAssignableFrom(cl1)) {
                return true;
            }
            if ((cl2 != null) && entryClass.isAssignableFrom(cl2)) {
                return true;
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    //
    // This method returns password wich is incapsulated in
    // CallbackHandlerProtection object
    // If there is no implementation of CallbackHandler then
    // this method returns null
    //
    static char[] getPasswordFromCallBack(KeyStore.ProtectionParameter protParam)
            throws UnrecoverableEntryException {
        if (protParam == null) {
            return null;
        }
        if (!(protParam instanceof KeyStore.CallbackHandlerProtection)) {
            throw new UnrecoverableEntryException(
                    "Incorrect ProtectionParameter");
        }
        String clName = Security
                .getProperty("auth.login.defaultCallbackHandler");
        if (clName == null) {
            throw new UnrecoverableEntryException(
                    "Default CallbackHandler was not defined");

        }
        try {
            Class cl = Class.forName(clName);
            CallbackHandler cbHand = (CallbackHandler) cl.newInstance();
            PasswordCallback[] pwCb = { new PasswordCallback("password: ", true) };
            cbHand.handle(pwCb);
            return pwCb[0].getPassword();
        } catch (Exception e) {
            throw new UnrecoverableEntryException(e.toString());
        }
    }

}