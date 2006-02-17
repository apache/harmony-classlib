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

package java.security.cert;

import java.security.PublicKey;

/**
 * @com.intel.drl.spec_ref
 * 
 */
public class PKIXCertPathBuilderResult extends PKIXCertPathValidatorResult
        implements CertPathBuilderResult {
    // Built and validated certification path
    private final CertPath certPath;

    /**
     * @com.intel.drl.spec_ref
     */
    public PKIXCertPathBuilderResult(CertPath certPath, TrustAnchor trustAnchor,
            PolicyNode policyTree, PublicKey subjectPublicKey) {
        super(trustAnchor, policyTree, subjectPublicKey);
        this.certPath = certPath;
        if (this.certPath == null) {
            throw new NullPointerException("the certPath parameter is null");
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public CertPath getCertPath() {
        return certPath;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.append("\n Certification Path: ");
        sb.append(certPath.toString());
        sb.append("\n]");
        return sb.toString();
    }
}
