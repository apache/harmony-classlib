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
* @author Alexander Y. Kleymenov
* @version $Revision$
*/

package java.security.cert;

import java.math.BigInteger;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Extension;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.security.auth.x500.X500Principal;

/**
 * @com.intel.drl.spec_ref
 */
public abstract class X509Certificate
        extends Certificate implements X509Extension {

    /**
     * @com.intel.drl.spec_ref
     */
    protected X509Certificate() {
        super("X.509");
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract void checkValidity()
            throws CertificateExpiredException, CertificateNotYetValidException;

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract void checkValidity(Date date)
            throws CertificateExpiredException, CertificateNotYetValidException;

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract int getVersion();

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract BigInteger getSerialNumber();

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract Principal getIssuerDN() ;

    /**
     * @com.intel.drl.spec_ref
     */
    public X500Principal getIssuerX500Principal() {
        throw new RuntimeException("Method should be overriden.");
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract Principal getSubjectDN();

    /**
     * @com.intel.drl.spec_ref
     */
    public X500Principal getSubjectX500Principal() {
        throw new RuntimeException("Method should be overriden.");
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract Date getNotBefore();

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract Date getNotAfter();

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract byte[] getTBSCertificate()
                                    throws CertificateEncodingException;

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract byte[] getSignature();

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract String getSigAlgName();

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract String getSigAlgOID();

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract byte[] getSigAlgParams();

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract boolean[] getIssuerUniqueID();

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract boolean[] getSubjectUniqueID();

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract boolean[] getKeyUsage();

    /**
     * @com.intel.drl.spec_ref
     */
    public List/*<String>*/ getExtendedKeyUsage()
                        throws CertificateParsingException {
        return null;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract int getBasicConstraints();

    /**
     * @com.intel.drl.spec_ref
     */
    public Collection/*<List<?>>*/ getSubjectAlternativeNames()
                                    throws CertificateParsingException {
        return null;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Collection/*<List<?>>*/ getIssuerAlternativeNames()
                                    throws CertificateParsingException {
        return null;
    }
}

