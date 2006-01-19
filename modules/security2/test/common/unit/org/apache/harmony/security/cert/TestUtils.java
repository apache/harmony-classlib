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
* @author Vladimir N. Molotkov
* @version $Revision$
*/

package org.apache.harmony.security.cert;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PolicyNode;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * java.security.cert test utilities
 * 
 */
public class TestUtils {
    // Certificate type used during testing
    private static final String certType = "X.509";
    // The file name to read certificate from
    private static final String certFileName =
        org.apache.harmony.security.test.TestUtils.TEST_ROOT +
        "java/security/cert/serialization/Certificate." + certType;
    // Key store type used during testing
    private static final String keyStoreType = "BKS";
    // The file name prefix to load keystore from
    private static final String keyStoreFileName =
        org.apache.harmony.security.test.TestUtils.TEST_ROOT +
        "java/security/cert/serialization/test." + keyStoreType + ".ks";
    //
    // The file name suffixes to load keystore from
    //  *.ks1 - keystore containing untrusted cerificates only
    //  *.ks2 - keystore containing trusted cerificates only
    //  *.ks3 - keystore containing both trusted and untrusted cerificates
    //
    public static final int UNTRUSTED = 1;
    public static final int TRUSTED = 2;
    public static final int TRUSTED_AND_UNTRUSTED = 3;
    //
    // Common passwords for all test keystores
    //
    private final static char[] storepass =
        new char[] {'s','t','o','r','e','p','w','d'};

    /**
     * Creates <code>TrustAnchor</code> instance
     * constructed using self signed test certificate
     *
     * @return <code>TrustAnchor</code> instance
     * @throws CertificateException
     * @throws FileNotFoundException
     */
    public static TrustAnchor getTrustAnchor() {
        CertificateFactory cf = null;
        try {
            cf = CertificateFactory.getInstance(certType);
        } catch (CertificateException e) {
            // requested cert type is not available in the
            // default provider package or any of the other provider packages
            // that were searched
            throw new RuntimeException(e);
        }
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(certFileName));
            X509Certificate c1 = (X509Certificate)cf.generateCertificate(bis);

            return new TrustAnchor(c1, null);
        } catch (Exception e) {
            // all failures are fatal
            throw new RuntimeException(e);
        } finally {
            if (bis != null) {
                try {
                    bis.close() ;
                } catch (IOException ign) {}
            }
        }
    }

    /**
     * Creates <code>Set</code> of <code>TrustAnchor</code>s
     * containing single element (self signed test certificate).
     * @return Returns <code>Set</code> of <code>TrustAnchor</code>s
     */
    public static Set getTrustAnchorSet() {
        TrustAnchor ta = getTrustAnchor();
        if (ta == null) {
            return null;
        }
        HashSet set = new HashSet();
        if (!set.add(ta)) {
            throw new RuntimeException("Could not create trust anchor set");
        }
        return set;
    }

    /**
     * Creates test <code>KeyStore</code> instance
     * 
     * @param initialize
     *  Do not initialize returned <code>KeyStore</code> if false
     * 
     * @param testKeyStoreType 
     *  this parameter ignored if <code>initialize</code> is false;
     *  The following types supported:<br>
     *  1 - <code>KeyStore</code> with untrusted certificates only<br>
     *  2 - <code>KeyStore</code> with trusted certificates only<br>
     *  3 - <code>KeyStore</code> with botht trusted and untrusted certificates
     * 
     * @return Returns test <code>KeyStore</code> instance
     */
    public static KeyStore getKeyStore(boolean initialize,
            int testKeyStoreType) {
        BufferedInputStream bis = null;
        try {
            KeyStore ks = KeyStore.getInstance(keyStoreType);
            if (initialize) {
                String fileName = keyStoreFileName + testKeyStoreType;
                bis = new BufferedInputStream(
                            new FileInputStream(fileName));
                ks.load(bis, storepass);
            }
            return ks;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (initialize && bis != null) {
                try {
                    bis.close();
                } catch (IOException ign) {}
            }
        }
    }

    /**
     * Creates <code>List</code> of <code>CollectionCertStores</code>
     *
     * @return The list created
     * 
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchAlgorithmException
     */
    public static List getCollectionCertStoresList()
        throws InvalidAlgorithmParameterException,
               NoSuchAlgorithmException {
        CertStore cs = CertStore.getInstance("Collection",
                new CollectionCertStoreParameters());
        ArrayList l = new ArrayList();
        if (!l.add(cs)) {
            throw new RuntimeException("Could not create cert stores list");
        }
        return l;
    }

    /**
     * Creates stub implementation of the <code>PKIXCertPathChecker</code>
     *
     * @return Stub implementation of the <code>PKIXCertPathChecker</code>
     */
    public static PKIXCertPathChecker getTestCertPathChecker() {
        // stub implementation for testing purposes only
        return new PKIXCertPathChecker() {
            private boolean forward = false;

            public void check(Certificate arg0, Collection arg1)
                    throws CertPathValidatorException {
            }

            public Set getSupportedExtensions() {
                return null;
            }

            public void init(boolean arg0) throws CertPathValidatorException {
                forward = arg0;
            }

            public boolean isForwardCheckingSupported() {
                // just to check this checker state
                return forward;
            }
        };
    }

    /**
     * Creates policy tree stub containing two <code>PolicyNode</code>s
     * for testing purposes
     *
     * @return root <code>PolicyNode</code> of the policy tree
     */
    public static PolicyNode getPolicyTree() {
        return new PolicyNode() {
            final PolicyNode parent = this;
            public int getDepth() {
                // parent
                return 0;
            }

            public boolean isCritical() {
                return false;
            }

            public String getValidPolicy() {
                return null;
            }

            public PolicyNode getParent() {
                return null;
            }

            public Iterator getChildren() {
                PolicyNode child = new PolicyNode() {
                    public int getDepth() {
                        // child
                        return 1;
                    }

                    public boolean isCritical() {
                        return false;
                    }

                    public String getValidPolicy() {
                        return null;
                    }

                    public PolicyNode getParent() {
                        return parent;
                    }

                    public Iterator getChildren() {
                        return null;
                    }

                    public Set getExpectedPolicies() {
                        return null;
                    }

                    public Set getPolicyQualifiers() {
                        return null;
                    }
                };
                HashSet s = new HashSet();
                s.add(child);
                return s.iterator();
            }

            public Set getExpectedPolicies() {
                return null;
            }

            public Set getPolicyQualifiers() {
                return null;
            }
        };
    }
}
