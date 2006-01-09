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

package java.security.spec;

import java.math.BigInteger;

/**
 * @com.intel.drl.spec_ref
 * 
 */
public class ECFieldFp implements ECField {
    // Prime
    private final BigInteger p;

    /**
     * @com.intel.drl.spec_ref
     */
    public ECFieldFp(BigInteger p) {
        this.p = p;

        if (this.p == null) {
            throw new NullPointerException("the p is null");
        }
        if (this.p.signum() != 1) {
            throw new IllegalArgumentException("the p is not positive");
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int getFieldSize() {
        return p.bitLength();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BigInteger getP() {
        return p;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean equals(Object obj) {
        // object equals itself
        if (this == obj) {
            return true;
        }
        if (obj instanceof ECFieldFp) {
            return (this.p.equals(((ECFieldFp)obj).p));
        }
        return false;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int hashCode() {
        return p.hashCode();
    }
}
