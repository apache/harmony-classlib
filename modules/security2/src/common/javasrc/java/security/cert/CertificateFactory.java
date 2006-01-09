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

package java.security.cert;

import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.openintel.fortress.drl.security.Engine;

/**
 * @com.intel.drl.spec_ref
 * 
 */

public class CertificateFactory {

    // Store CertificateFactory service name
    private static final String SERVICE = "CertificateFactory";

    // Used to accesess common engine functionality
    private static Engine engine = new Engine(SERVICE);

    // Store used provider
    private final Provider provider;

    // Store used CertificateFactorySpi implementation
    private final CertificateFactorySpi spiImpl;

    // Store used type
    private final String type;

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    protected CertificateFactory(CertificateFactorySpi certFacSpi,
            Provider provider, String type) {
        this.provider = provider;
        this.type = type;
        this.spiImpl = certFacSpi;
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     * throws CertificateException using NoSuchAlgorithmException information
     * 
     * throws NullPointerException if algorithm is null (instead of
     * CertificateException as in 1.4 release)
     */
    public static final CertificateFactory getInstance(String type)
            throws CertificateException {
        if (type == null) {
            throw new NullPointerException("type is null");
        }
        try {
            synchronized (engine) {
                engine.getInstance(type, null);
                return new CertificateFactory((CertificateFactorySpi) engine.spi,
                        engine.provider, type);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new CertificateException(e);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     * throws NullPointerException if algorithm is null (instead of
     * CertificateException as in 1.4 release)
     */
    public static final CertificateFactory getInstance(String type,
            String provider) throws CertificateException,
            NoSuchProviderException {
        if ((provider == null) || (provider.length() == 0)) {
            throw new IllegalArgumentException("Provider is null or empty");
        }
        Provider impProvider = Security.getProvider(provider);
        if (impProvider == null) {
            throw new NoSuchProviderException(provider);
        }
        return getInstance(type, impProvider);
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     * throws CertificateException using NoSuchAlgorithmException information
     * 
     * throws NullPointerException if algorithm is null (instead of
     * CertificateException as in 1.4 release)
     */
    public static final CertificateFactory getInstance(String type,
            Provider provider) throws CertificateException {
        if (provider == null) {
            throw new IllegalArgumentException("Proveder is null");
        }
        if (type == null) {
            throw new NullPointerException("type is null");
        }
        try {
            synchronized (engine) {
                engine.getInstance(type, provider, null);
                return new CertificateFactory((CertificateFactorySpi) engine.spi,
                        provider, type);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new CertificateException(e.getMessage());
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final Provider getProvider() {
        return provider;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final String getType() {
        return type;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final Certificate generateCertificate(InputStream inStream)
            throws CertificateException {
        return spiImpl.engineGenerateCertificate(inStream);
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     * FIXME: update to Iterator <String>getCertPathEncodings()
     */
    public final Iterator getCertPathEncodings() {
        return spiImpl.engineGetCertPathEncodings();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final CertPath generateCertPath(InputStream inStream)
            throws CertificateException {
        Iterator it = getCertPathEncodings();
        if (!it.hasNext()) {
            throw new CertificateException("There are no CertPath encodings");
        }
        return spiImpl.engineGenerateCertPath(inStream, (String) it.next());
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final CertPath generateCertPath(InputStream inStream, String encoding)
            throws CertificateException {
        return spiImpl.engineGenerateCertPath(inStream, encoding);
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     * FIXME: update parameter to (List <? extends Certificate> certificates)
     */
    public final CertPath generateCertPath(List certificates)
            throws CertificateException {
        return spiImpl.engineGenerateCertPath(certificates);
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     * FIXME: update returned value to Collection <? extends Certificate>
     */
    public final Collection generateCertificates(InputStream inStream)
            throws CertificateException {
        return spiImpl.engineGenerateCertificates(inStream);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final CRL generateCRL(InputStream inStream) throws CRLException {
        return spiImpl.engineGenerateCRL(inStream);
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     * FIXME: update returned value to Collection <? extends CRL>
     */
    public final Collection generateCRLs(InputStream inStream)
            throws CRLException {
        return spiImpl.engineGenerateCRLs(inStream);
    }
}