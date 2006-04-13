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

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.Set;

/**
 * @com.intel.drl.spec_ref
 * 
 */
public class PKIXBuilderParameters extends PKIXParameters {
    // Maximum certificate path length (5 by default)
    private int maxPathLength = 5;

    /**
     * @com.intel.drl.spec_ref
     */
    public PKIXBuilderParameters(Set<TrustAnchor> trustAnchors,
            CertSelector targetConstraints)
        throws InvalidAlgorithmParameterException {
        super(trustAnchors);
        super.setTargetCertConstraints(targetConstraints);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public PKIXBuilderParameters(KeyStore keyStore,
            CertSelector targetConstraints)
        throws KeyStoreException,
               InvalidAlgorithmParameterException {
        super(keyStore);
        super.setTargetCertConstraints(targetConstraints);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int getMaxPathLength() {
        return maxPathLength;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setMaxPathLength(int maxPathLength) {
        if (maxPathLength < -1) {
            throw new InvalidParameterException(
                    "the maxPathLength parameter is less than -1");
        }
        this.maxPathLength = maxPathLength;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String toString() {
        StringBuffer sb = new StringBuffer("[\n");
        sb.append(super.toString());
        sb.append(" Max Path Length: ");
        sb.append(maxPathLength);
        sb.append("\n]");
        return sb.toString();
    }
}
