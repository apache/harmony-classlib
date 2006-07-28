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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.cert.CertPathBuilder;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertPathBuilderResult;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.CertificateException;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.security.auth.x500.X500Principal;

/**
 * A class for checking X.509 certifcicates and building and verifying X.509
 * certificate chains.
 */
public class CertChainVerifier {
    private final static String strFailed = "Failed to build a certificate chain from reply.\n";

    /**
     * A cerificate chain is built by looking up the certificate of the issuer
     * of the current certificate. If a sertificate is self-signed it is assumed
     * to be the root CA. After that the certificates are searched in the lists
     * of revoked certificates. Certificate signatures are checked and
     * certificate path is built in the same way as in import operation. If an
     * error occurs the flow is not stopped but an attempt to continue is made.
     * The results of the verification are printed to stdout.
     */
    static void verifyChain(KeytoolParameters param){
        // TODO
        throw new RuntimeException("Method is not implemented yet.");
    }
    
    /**
     * Builds a certificate chain from the given X509Certificate newCert to a
     * self-signed root CA whose certificate is contained in the keystore or
     * cacerts file (if "-trustcacerts" option is specified).
     * 
     * @param param -
     *            specifies the keystore, provider name and other options (such
     *            as "-trustcacerts").
     * @param newCert -
     *            certificate to start the chain
     * @return the chain as an array of X509Certificate-s. If the chain cannot
     *         be built for some reason an exception is thrown.
     * @throws KeyStoreException
     * @throws FileNotFoundException
     * @throws NoSuchAlgorithmException
     * @throws CertificateException
     * @throws IOException
     * @throws KeytoolException
     * @throws NoSuchProviderException
     * @throws CertPathBuilderException
     */
    static X509Certificate[] buildFullCertPath(KeytoolParameters param,
            X509Certificate newCert) throws KeyStoreException,
            FileNotFoundException, NoSuchAlgorithmException,
            CertificateException, IOException, KeytoolException,
            CertPathBuilderException, NoSuchProviderException {

        X509CertSelector selector = new X509CertSelector();
        selector.setCertificate(newCert);

        Collection[] trustedSeparated = separateTrusted(param);
        Set selfSignedTAs = (Set) trustedSeparated[0];
        Collection trustedCerts = trustedSeparated[1];
        Collection selfSignedTAsCerts = trustedSeparated[2];

        CertPathBuilderResult bldResult = buildCertPath(param.getProvider(),
                newCert, selfSignedTAs, trustedCerts);

        // The validation of the certificate path.
        // According to black-box testing, RI keytool doesn't perform
        // the certificate path validation procedure. Therefore this
        // implementation also doesn't validate the certificate chain
        // The path validation procedure can be done in future as an
        // extension of the current functionality.

        // The imported certificate should be included in the chain;
        // The root CA is not included in it.
        X509Certificate[] newChainNoCA = (X509Certificate[]) bldResult
                .getCertPath().getCertificates()
                .toArray(new X509Certificate[0]);

        // get the subject of the root CA which will be the last element of the
        // chain
        X500Principal caSubject = newChainNoCA[newChainNoCA.length - 1]
                .getIssuerX500Principal();

        // set the search parameter for the root CA
        selector.setCertificate(null);
        selector.setSubject(caSubject);

        // add all root CAs to the CertStore to search through them
        CollectionCertStoreParameters rootCAs = new CollectionCertStoreParameters(
                selfSignedTAsCerts);
        CertStore rootCAsCertStore;
        try {
            rootCAsCertStore = CertStore.getInstance("Collection", rootCAs);
        } catch (Exception e) {
            throw new KeytoolException(strFailed, e);
        }

        // find certificate to add to the end of the certificate chain
        X509Certificate rootCA;
        try {
            rootCA = (X509Certificate) rootCAsCertStore.getCertificates(
                    selector).iterator().next();
        } catch (CertStoreException e) {
            throw new KeytoolException(strFailed, e);
        }
        // create a new array of certificates with the root CA in it.
        X509Certificate[] newChain = new X509Certificate[newChainNoCA.length + 1];
        System.arraycopy(newChainNoCA, 0, newChain, 0, newChainNoCA.length);
        newChain[newChain.length - 1] = rootCA;

        return newChain;
    }

    /**
     * Build a certificte chain up to the trust anchor, based on trusted
     * certificates contained in the keystore and possibly cacerts file (if
     * param.isTrustCACerts() returns true).
     * 
     * @param param
     * @param newCert
     * @return
     * @throws NoSuchAlgorithmException
     * @throws CertificateException
     * @throws KeyStoreException
     * @throws CertPathBuilderException
     * @throws IOException
     * @throws KeytoolException
     * @throws NoSuchProviderException
     */
    static CertPathBuilderResult buildCertPath(KeytoolParameters param,
            X509Certificate newCert) throws NoSuchAlgorithmException,
            CertificateException, KeyStoreException, CertPathBuilderException,
            IOException, KeytoolException, NoSuchProviderException {
        Collection[] trustedSeparated = separateTrusted(param);
        Set selfSignedTAs = (Set) trustedSeparated[0];
        Collection trustedCerts = trustedSeparated[1];

        return buildCertPath(param.getProvider(), newCert, selfSignedTAs,
                trustedCerts);
    }

    
    // Build a certificte chain up to the self-signed trust anchor, based on
    // trusted certificates given.
    // 
    // @param provider
    // @param newCert
    //            is a certificate to build chain for.
    // @param selfSignedTAs
    //            are used as trust anchors.
    // @param trustedCerts
    //            elements of trustedCerts are used as chain links It can be
    //            null if no intermediate certifictaes allowed.
    private static CertPathBuilderResult buildCertPath(String provider,
            X509Certificate newCert, Set selfSignedTAs, Collection trustedCerts)
            throws NoSuchAlgorithmException, CertificateException, IOException,
            KeyStoreException, CertPathBuilderException, KeytoolException,
            NoSuchProviderException {

        X509CertSelector selector = new X509CertSelector();
        selector.setCertificate(newCert);

        String strPKIX = "PKIX";
        String strNoSelfSigned = "Possibly, keystore doesn't "
                + "contain any self-signed (root CA) trusted certificates. ";

        // this parameter will be used to generate the certificate chain
        PKIXBuilderParameters builderParam = null;
        try {
            // set the search parameters with selector
            // and TrustAnchors with selfSignedTAs
            builderParam = new PKIXBuilderParameters(selfSignedTAs, selector);
        } catch (InvalidAlgorithmParameterException e) {
            throw new KeytoolException(strFailed + strNoSelfSigned, e);
        }

        if (trustedCerts != null) {
            CollectionCertStoreParameters trustedCertsCCSParams = 
                    new CollectionCertStoreParameters(trustedCerts);
            CertStore trustedCertStore;
            try {
                trustedCertStore = CertStore.getInstance("Collection",
                        trustedCertsCCSParams);
            } catch (Exception e) {
                throw new KeytoolException(strFailed, e);
            }

            // add certificates to use as chain links
            builderParam.addCertStore(trustedCertStore);
        }

        // disable the revocation checking
        builderParam.setRevocationEnabled(false);

        CertPathBuilder cpBuilder;
        try {
            if (provider == null) {
                cpBuilder = CertPathBuilder.getInstance(strPKIX);
            } else {
                cpBuilder = CertPathBuilder.getInstance(strPKIX, provider);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException("The algorithm " + strPKIX
                    + " is not available.", e);
        } catch (NoSuchProviderException e) {
            throw (NoSuchProviderException) new NoSuchProviderException(
                    "The provider " + provider
                            + " is not found in the environment.").initCause(e);
        }

        CertPathBuilderResult bldResult = null;
        try {
            // the actual building of the certificate chain is done here
            bldResult = cpBuilder.build(builderParam);
        } catch (CertPathBuilderException e) {
            throw new CertPathBuilderException(strFailed, e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new KeytoolException(strFailed + strNoSelfSigned, e);
        }

        return bldResult;
    }

    
    // Separates the trusted certificates from keystore (and cacerts file if
    // "-trustcacerts" option is specified) into self-signed certificate
    // authority certificates and non-self-signed certifcates.
    // @return - Returns an array of Collection-s.
    // The first element of the array is Set<TrustAnchors> - self-signed CAs.
    // The second - ArrayList of non-self-signed trusted certifcates.
    // The third - ArrayList of self-signed certificates which correspond to
    // TrustAnchors containied in the first element of the array.
    private static Collection[] separateTrusted(KeytoolParameters param)
            throws KeyStoreException, FileNotFoundException, IOException,
            NoSuchAlgorithmException, CertificateException, KeytoolException,
            NoSuchProviderException {
        // Are there any trusted certificates in the keyStore?
        boolean trustedExistInKS = false;
        // Is "-trustcacerts" option specified?
        boolean trustCaCerts = param.isTrustCACerts();
        String strNoTrusted = "Possibly, keystore doesn't "
                + "contain any trusted certificates. ";

        // This one is temporary. Used just to get trusted certificates
        // from keyStore.
        PKIXBuilderParameters keyStoreBuilderParam = null;
        X509CertSelector selector = new X509CertSelector();

        // After getting the trusted certificates, they will be sorted into
        // self-signed (they are considered to be CAs) and interim trusted
        // certs.

        // self-signed trust anchors. The CertPath is ok if it finishes
        // on such trust anchor.
        Set selfSignedTAs = null;
        // certificates of selfSignedTAs
        Collection selfSignedTAsCerts = null;
        // trusted certiifcates which can be the chain links of the CertPath
        Collection trustedCerts = null;
        try {
            keyStoreBuilderParam = new PKIXBuilderParameters(param
                    .getKeyStore(), selector);

            // won't come here if exception is thrown
            trustedExistInKS = true;
        } catch (InvalidAlgorithmParameterException e) {
            // if "-trustcacerts" option is NOT specified
            if (!trustCaCerts) {
                throw new KeytoolException(strFailed + strNoTrusted);
            }
        }

        // if there are trusted certificates in the keyStore
        if (keyStoreBuilderParam != null) {
            // trustAnchorsSet is a set of trusted certificates
            // contained in keyStore
            Set trustAnchorsSet = keyStoreBuilderParam.getTrustAnchors();
            int halfSize = trustAnchorsSet.size() / 2;
            selfSignedTAs = new HashSet(halfSize);
            selfSignedTAsCerts = new ArrayList(halfSize);
            trustedCerts = new ArrayList(halfSize);

            Iterator trustAnchorsIter = trustAnchorsSet.iterator();
            while (trustAnchorsIter.hasNext()) {
                TrustAnchor ta = (TrustAnchor) trustAnchorsIter.next();
                X509Certificate trCert = ta.getTrustedCert();
                // if the trusted certificate is self-signed,
                // add it to the selfSignedTAs.
                if (Arrays.equals(
                        trCert.getSubjectX500Principal().getEncoded(), trCert
                                .getIssuerX500Principal().getEncoded())) {
                    selfSignedTAs.add(ta);
                    selfSignedTAsCerts.add(trCert);
                } else {// otherwise just add it to the list of
                    // trusted certs
                    trustedCerts.add(trCert);
                }
            }
        }
        
        // if "-trustcacerts" is specified, add CAs from cacerts
        if (trustCaCerts) {
            KeyStore cacertsFile = null;
            try {
                cacertsFile = param.getCacerts();
            } catch (Exception e) {
                if (trustedExistInKS) {
                    // if there are trusted certificates in keyStore,
                    // just print the notification
                    System.err.println(e.getMessage());
                } else {// otherwise quit
                    throw new KeytoolException(strFailed, e);
                }
            }

            // if cacerts loaded
            if (cacertsFile != null) {
                PKIXBuilderParameters cacertsBuilderParam = null;
                try {
                    cacertsBuilderParam = new PKIXBuilderParameters(
                            cacertsFile, selector);
                } catch (InvalidAlgorithmParameterException e) {
                    if (!trustedExistInKS) {
                        throw new KeytoolException(strFailed + strNoTrusted);
                    } else {
                        // if there are trusted certificates in keyStore,
                        // just return what have now
                        return new Collection[] { selfSignedTAs, trustedCerts,
                                selfSignedTAsCerts };
                    }
                }

                Set<TrustAnchor> cacertsCAs = cacertsBuilderParam
                        .getTrustAnchors();

                // if there are no trusted certificates in the
                // keyStore
                if (!trustedExistInKS) {
                    Set trustAnchorsSet = cacertsBuilderParam.getTrustAnchors();
                    int size = trustAnchorsSet.size();
                    // usually only self-signed CAs are in the
                    // cacerts file, so selfSignedTAs is of the
                    // same size as trustAnchorsSet.
                    selfSignedTAs = new HashSet(size);
                    selfSignedTAsCerts = new HashSet(size);
                    trustedCerts = new ArrayList();
                }

                Iterator cacertsCAsIter = cacertsCAs.iterator();
                while (cacertsCAsIter.hasNext()) {
                    TrustAnchor ta = (TrustAnchor) cacertsCAsIter.next();
                    X509Certificate trCert = ta.getTrustedCert();
                    // if the trusted certificate is self-signed,
                    // add it to the selfSignedTAs.
                    if (Arrays.equals(trCert.getSubjectX500Principal()
                            .getEncoded(), trCert.getIssuerX500Principal()
                            .getEncoded())) {
                        selfSignedTAs.add(ta);
                        selfSignedTAsCerts.add(trCert);
                    } else {// otherwise just add it to the list of
                        // trusted certs
                        trustedCerts.add(trCert);
                    }
                }
            }// if (cacertsFile != null)...
        }// if (trustCacerts)...
        return new Collection[] { selfSignedTAs, trustedCerts,
                selfSignedTAsCerts };
    }

    /**
     * Orders a collection of certificates into a certificate chain beginning
     * with the certificate which has public key equal to aliasPubKey.
     * 
     * @throws KeytoolException
     */
    static X509Certificate[] orderChain(Collection<X509Certificate> certs,
            PublicKey aliasPubKey) throws KeytoolException {

        String strOrderFailed = "Failed to order the certiticate chain.";

        // add certificates to the certstore to ease the search
        CollectionCertStoreParameters chainCCSParams = new CollectionCertStoreParameters(
                certs);
        CertStore certStore;
        try {
            certStore = CertStore.getInstance("Collection", chainCCSParams);
        } catch (Exception e) {
            throw new KeytoolException(strOrderFailed, e);
        }

        // set up selector to search the certificates
        X509CertSelector selector = new X509CertSelector();
        // try to find the first certificate in the chain
        selector.setSubjectPublicKey(aliasPubKey);

        // current certificate
        X509Certificate current = null;
        try {
            current = (X509Certificate) certStore.getCertificates(selector)
                    .iterator().next();
        } catch (CertStoreException e) {
            // do nothing
        } catch (NoSuchElementException e) {
            // do nothing
        }

        if (current == null) {
            throw new KeytoolException(
                    "Failed to find the requested public key "
                            + "in certificate reply.");
        }
        // number of certificates in collection
        int colSize = certs.size();
        // new chain to return
        X509Certificate[] ordered = new X509Certificate[colSize];
        ordered[0] = current;
        selector.setSubjectPublicKey((PublicKey) null);

        // counter of ordered certificates
        // it will be incremented later
        int orderedCnt = 0;

        if (!Arrays.equals(current.getSubjectX500Principal().getEncoded(),
                current.getIssuerX500Principal().getEncoded())) {
            // orderedCnt = 1, because the first certificate is already in
            // the resulting array
            for (orderedCnt = 1; orderedCnt < colSize; orderedCnt++) {
                // set new filter
                selector.setSubject(current.getIssuerX500Principal());
                try {
                    // get issuer's certificate
                    current = (X509Certificate) certStore.getCertificates(
                            selector).iterator().next();
                } catch (CertStoreException e) {
                    throw new KeytoolException(strOrderFailed, e);
                } catch (NoSuchElementException e) {
                    break;
                }

                if (Arrays.equals(current.getSubjectX500Principal()
                        .getEncoded(), current.getIssuerX500Principal()
                        .getEncoded())) {
                    // if self-signed, save it and quit. It is the last.
                    ordered[orderedCnt] = current;
                    break;
                } else {
                    // add current certificate to the chain and continue
                    ordered[orderedCnt] = current;
                }
            }
        }

        // If the certificate collection contains certificates which
        // are not a part of the chain.
        // ++orderedCnt is used because 'break's don't let the
        // variable be incremented when it should be.
        if (++orderedCnt < colSize) {
            X509Certificate[] orderedShort = new X509Certificate[orderedCnt];
            System.arraycopy(ordered, 0, orderedShort, 0, orderedCnt);
            return orderedShort;
        }

        return ordered;
    }
    
    // orders a chain without a starting element given
    static X509Certificate[] orderChain(Collection<X509Certificate> certs)
            throws KeytoolException {

        int certsLen = certs.size();
        int startPos = -1;

        List<X509Certificate> certsList = new ArrayList<X509Certificate>(certs);

        // searching for the first element of the chain
        for (int i = 0; i < certsLen; i++) {
            X509Certificate curCert = certsList.get(i);
            for (int j = 0; j < certsLen; j++) {
                if (j != i) {
                    // if the subject is the issuer of another cert, it is not
                    // the first in the chain.
                    if (Arrays.equals(curCert.getSubjectX500Principal()
                            .getEncoded(), certsList.get(j)
                            .getIssuerX500Principal().getEncoded())) {
                        break;
                    }
                }
                // If the certificate's subject is not found to be an issuer to
                // any other cert in this chain, then it is the first element.
                if (j == certsLen - 1) {
                    startPos = i;
                    break;
                }
            }
            // don't search any more, if the first element is found.
            if (startPos > -1) {
                break;
            }
        }

        return orderChain(certsList, certsList.get(startPos).getPublicKey());
    }
            
    
    /**
     * Checks if the X509Certificate cert is contained as a trusted certificate
     * entry in keystore and possibly cacerts file (if "-trustcacerts" option is
     * specified).
     * 
     * @param param
     * @param cert
     * @return true if the certificate is trused, false - otherwise.
     * @throws FileNotFoundException
     * @throws NoSuchAlgorithmException
     * @throws CertificateException
     * @throws IOException
     * @throws KeytoolException
     * @throws KeyStoreException
     * @throws NoSuchProviderException
     */
    static boolean isTrusted(KeytoolParameters param, X509Certificate cert)
            throws FileNotFoundException, NoSuchAlgorithmException,
            CertificateException, IOException, KeytoolException,
            KeyStoreException, NoSuchProviderException {
        // check the main keyStore
        KeyStore keyStore = param.getKeyStore();
        String alias = keyStore.getCertificateAlias(cert);
        if (alias != null) {
            if (keyStore.isCertificateEntry(alias)) {
                return true;
            }
        }

        if (!param.isTrustCACerts()) {
            return false;
        } else {// check cacerts file
            KeyStore cacerts = param.getCacerts();
            alias = cacerts.getCertificateAlias(cert);
            if (alias != null) {
                if (cacerts.isCertificateEntry(alias)) {
                    return true;
                }
            }
            return false;
        }
    }
}
