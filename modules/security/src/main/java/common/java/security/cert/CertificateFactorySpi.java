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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @com.intel.drl.spec_ref
 * 
 */

public abstract class CertificateFactorySpi {

    /**
     * @com.intel.drl.spec_ref
     */
    public CertificateFactorySpi() {
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract Certificate engineGenerateCertificate(InputStream inStream)
            throws CertificateException;

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract Collection<? extends Certificate> 
        engineGenerateCertificates(InputStream inStream) throws CertificateException;

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract CRL engineGenerateCRL(InputStream inStream)
            throws CRLException;

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract Collection<? extends CRL> 
        engineGenerateCRLs(InputStream inStream) throws CRLException;

    /**
     * @com.intel.drl.spec_ref
     */
    public CertPath engineGenerateCertPath(InputStream inStream)
            throws CertificateException {
        throw new UnsupportedOperationException(
                "Method engineGenerateCertPath(InputStream inStream) is not supported");
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public CertPath engineGenerateCertPath(InputStream inStream, String encoding)
            throws CertificateException {
        throw new UnsupportedOperationException(
                "Method engineGenerateCertPath(InputStream inStream, String encoding) is not supported");
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public CertPath engineGenerateCertPath(List<? extends Certificate>  certificates) 
            throws CertificateException {
        throw new UnsupportedOperationException(
                "Method engineGenerateCertPath(List certificates) is not supported");
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Iterator<String> engineGetCertPathEncodings() {
        throw new UnsupportedOperationException(
                "Method engineGetCertPathEncodings() is not supported");
    }
}
