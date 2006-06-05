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

package javax.crypto;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Provider;
import java.security.Security;
import java.security.Provider.Service;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * This class is used for internal purposes: getting services from providers and
 * provider sing's verification
 * 
 * @author Diego Raúl Mercado
 * @version 1.2
 */
final class Util {
    
    /** Stores the providers that has been checked */
    private static List<Provider> providers = new ArrayList<Provider>();
    
    /** Represents the SUN JCE Code Signing CA certificate (in base 64) */
    private static final String SUN_JCE_CODE_SIGNING_CA = "MIIDwDCCA36gAwIBAg" 
        + "IBEDALBgcqhkjOOAQDBQAwgZAxCzAJBgNVBAYTAlVTMQswCQYDVQQIEwJDQTESMB" 
        + "AGA1UEBxMJUGFsbyBBbHRvMR0wGwYDVQQKExRTdW4gTWljcm9zeXN0ZW1zIEluYz" 
        + "EjMCEGA1UECxMaSmF2YSBTb2Z0d2FyZSBDb2RlIFNpZ25pbmcxHDAaBgNVBAMTE0" 
        + "pDRSBDb2RlIFNpZ25pbmcgQ0EwHhcNMDEwNDI1MDcwMDAwWhcNMjAwNDI1MDcwMD" 
        + "AwWjCBkDELMAkGA1UEBhMCVVMxCzAJBgNVBAgTAkNBMRIwEAYDVQQHEwlQYWxvIE" 
        + "FsdG8xHTAbBgNVBAoTFFN1biBNaWNyb3N5c3RlbXMgSW5jMSMwIQYDVQQLExpKYX" 
        + "ZhIFNvZnR3YXJlIENvZGUgU2lnbmluZzEcMBoGA1UEAxMTSkNFIENvZGUgU2lnbm" 
        + "luZyBDQTCCAbcwggEsBgcqhkjOOAQBMIIBHwKBgQDrrzcEHspRHmldsPKP9rVJH8" 
        + "akmQXXKb90t2r1Gdge5Bv4CgGamP9wq+JKVoZsU7P84ciBjDHwxPOwi+ZwBuz3aW" 
        + "jbg0xyKYkpNhdcO0oHoCACKkaXUR1wyAgYC84Mbpt29wXj5/vTYXnhYJokjQaVgz" 
        + "xRIOEwzzhXgqYacg3O0wIVAIQlReG6ualiq3noWzC4iWsb/3t1AoGBAKvJdHt07+" 
        + "5CtWpTTTvdkAZyaJEPC6Qpdi5VO9WuTWVcfio6BKZnptBxqqXXt+LBcg2k0aoekl" 
        + "RMIAAJorAJQRkzALLDXK5C+LGLynyW2BB/N0Rbqsx4yNdydjdrQJmoVWb6qAMei0" 
        + "oRAmnLTLglBhygd9LJrNI96QoQ+nZwt/vcA4GEAAKBgC0JmFysuJzHmX7uIBkqNJ" 
        + "D516urrt1rcpUNZvjvJ49Esu0oRMf+r7CmJ28AZ0WCWweoVlY70ilRYV5pOdcudH" 
        + "cSzxlK9S3Iy3JhxE5v+kdDPxS7+rwYZijC2WaLei0vwmCSSxT+WD4hf2hivmxISf" 
        + "mgS16FnRkQ+RVFURtx1PcLo2YwZDARBglghkgBhvhCAQEEBAMCAAcwDwYDVR0TAQ" 
        + "H/BAUwAwEB/zAfBgNVHSMEGDAWgBRl4vSGydNO8JFOWKJq9dh4WprBpjAdBgNVHQ" 
        + "4EFgQUZeL0hsnTTvCRTliiavXYeFqawaYwCwYHKoZIzjgEAwUAAy8AMCwCFCr3zz" 
        + "yXXfl4tgjXQbTZDUVM5LScAhRFzXVpDiH6HdazKbLp9zMdM/38SQ=3D=3D";

    /** Add here de IBM Jce Code Signing CA certificate and/or others */
    private static final X509Certificate[] TRUSTED_CA_CERTS = 
        new X509Certificate[] { getCertificate(SUN_JCE_CODE_SIGNING_CA) };

    /**
     * Gets a service from a provider given a specific type and algorithm
     * 
     * @param type
     *            this enum groups types of services
     * @param algorithm
     *            a specific algorithm (such as DES, AES, etc.)
     * @param provider
     *            a provider of the requested type and algorithm
     * @return a service from a provider given a specific type and algorithm or
     *         <code>null</code> if the type-algorithm is not provided by the
     *         specific provider or
     * @throws NullPointerException
     *             if provider is null
     */
    static final Service getService(Types type, String algorithm,
            Provider provider) {
        Service service = null;
        if (algorithm == null) {
            throw new NullPointerException("Missing Algorithm");
        }
        if (provider != null) {
            checkProvider(provider);
            service = provider.getService(type.toString(), algorithm);
        } else {
            for (Provider prov : Security.getProviders()) {
                service = prov.getService(type.toString(), algorithm);
                if (service != null) {
                    checkProvider(prov);
                    break;
                }
            }
        }
        return service;
    }

    /**
     * Constructs a jar file from the provider's location of the code source
     * (url) and then calls the <code>verifyjar</code> method
     * 
     * @param p
     *            a requested provider
     */
    static final void checkProvider(Provider p) {
        if (!providers.contains(p)) { //if wasn't previously checked...
            final Provider p1 = p;
            URL pURL = AccessController.doPrivileged(new PrivilegedAction<URL>() {
                public URL run() {
                    return p1.getClass().getProtectionDomain().getCodeSource()
                    .getLocation();
                }
                
            });
            try {
                verifyjar(new JarFile(new File(pURL.toURI()), true));
            } catch (IOException e) {
                throw new SecurityException(e);
            } catch (URISyntaxException e) {
                throw new SecurityException(e);
            }
            providers.add(p);
        }
    }

    /**
     * Verifies that the jar file is correctly signed and the finals (roots)
     * certificates of each chain of each jar entry are trusted. <br>
     * Throw a <code>SecurityException</code> if it fails. Returns silently.
     * 
     * @param jf
     *            the jar file to verify
     */
    private static void verifyjar(JarFile jf) {
        // if MANIFEST exists implies that is signed
        try {
            if (jf.getManifest() == null) {
                throw new SecurityException("JAR not signed");
            }
        } catch (IOException e) {
            throw new SecurityException(e);
        }

        // verify that each entry has the correct sign and digest
        List<JarEntry> lst = new ArrayList<JarEntry>();
        Enumeration<JarEntry> e = jf.entries();
        while (e.hasMoreElements()) {
            JarEntry je = e.nextElement();
            if (!je.getName().startsWith("META-INF")) {
                try {
                    lst.add(je); // for further verification
                    InputStream is = 
                        jf.getInputStream(jf.getEntry(je.getName()));
                    byte[] buffer = new byte[8192];
                    @SuppressWarnings("unused")
                    int n;
                    while ((n = is.read(buffer, 0, buffer.length)) != -1) {
                        // we read until the final of the stream to force sign's
                        // verification and to get the certificate chain of each 
                        // jarEntry
                    }
                } catch (IOException e1) {
                    throw new SecurityException(e1);
                }
            }
        }

        // Verify that the chainRoot is trusted by any of the certificate
        // contained by the TRUSTED_CA_CERTS array
        for (JarEntry je : lst) {
            if (!je.isDirectory()) {
                Certificate[] certs = je.getCertificates();
                if (certs == null || certs.length == 0) {
                    throw new SecurityException(
                            "Provider's JAR has not been signed");
                }
                Certificate[] chainRoots = getChainRoots(certs);
                boolean signed = false;

                for (int i = 0; i < chainRoots.length; i++) {
                    if (isTrusted((X509Certificate) chainRoots[i])) {
                        signed = true;
                        break;
                    }
                }
                if (!signed) {
                    throw new SecurityException("Untrusted provider's JAR");
                }
            }
        }
    }

    /**
     * Get a X509 certificate from a base64 encoded format
     * 
     * @param base64
     *            represent a base64 encoded format
     * @return a X509 certificate from the base64 encoded format
     */
    private static synchronized X509Certificate getCertificate(String base64) {
        try {
            ByteArrayInputStream inStream = new ByteArrayInputStream(
                    new sun.misc.BASE64Decoder().decodeBuffer(base64));
            X509Certificate cert = (X509Certificate) CertificateFactory
            .getInstance("X.509").generateCertificate(inStream);
            inStream.close();
            return cert;
        } catch (CertificateException e) {
            throw new AssertionError(e);
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * @return true if the cert is in TRUSTED_CA_CERTS or cert is issued by a
     *         trusted CA (if the name is the same then we call equals() method)
     */
    private static boolean isTrusted(X509Certificate cert) {

        // Check whether the cert is in the TRUSTED_CA_CERTS
        // because verify method is expensive
        for (int i = 0; i < TRUSTED_CA_CERTS.length; i++) {
            if (cert.getSubjectDN().equals(TRUSTED_CA_CERTS[i].getSubjectDN())) {
                if (cert.equals(TRUSTED_CA_CERTS[i])) {
                    return true;
                }
            }
        }

        // At least one certificate must be trusted by one of the
        // TRUSTED_CA_CERTS
        for (int i = 0; i < TRUSTED_CA_CERTS.length; i++) {
            if (cert.getIssuerDN().equals(TRUSTED_CA_CERTS[i].getSubjectDN())) {
                try {
                    cert.verify(TRUSTED_CA_CERTS[i].getPublicKey());
                    return true;
                } catch (Exception e) {
                }
            }
        }
        return false;
    }

    /**
     * Returns an arrays of certificates with the final (root) certificate of
     * each chain
     * 
     * @param certs
     *            an array of X509 certificate's
     * @return an array of X509 certificate's with the root certificate of each
     *         chain
     */
    private static Certificate[] getChainRoots(Certificate[] certs) {
        ArrayList<Certificate> lst = new ArrayList<Certificate>();
        for (int i = 0; i < certs.length - 1; i++) {
            if (!((X509Certificate) certs[i + 1]).getSubjectDN().equals(
                    ((X509Certificate) certs[i]).getIssuerDN())) {
                lst.add(certs[i]); // end of a chain
            }
        }
        // The final entry in the certs array is always a "root" certificate
        lst.add(certs[certs.length - 1]);

        // copy arraylist's content into an array
        Certificate[] ret = new Certificate[lst.size()];
        int i = 0;
        for (Certificate c : lst) {
            ret[i++] = c;
        }
        return ret;
    }
    
    /**
     * Prevents instantiation.
     */
    private Util() {}
}
