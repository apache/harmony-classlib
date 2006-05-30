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

package javax.crypto.spec;

import java.io.Serializable;
import java.security.spec.KeySpec;
import java.util.Arrays;
import javax.crypto.SecretKey;

/**
 * @com.intel.drl.spec_ref
 */
public class SecretKeySpec implements SecretKey, KeySpec, Serializable {

    // The 5.0 spec. doesn't declare this serialVersionUID field
    // In order to be compatible it is explicitly declared here
    // for details see HARMONY-233
    private static final long serialVersionUID = 6577238317307289933L;

    private final byte[] key;
    private final String algorithm;
    private final String format = "RAW";

    /**
     * @com.intel.drl.spec_ref
     */
    public SecretKeySpec(byte[] key, String algorithm) {
    	if (key == null) {
    		throw new IllegalArgumentException("key is null");
    	}
    	if (key.length == 0) {
    		throw new IllegalArgumentException("key is empty");
    	}
        if (algorithm == null) {
            throw new IllegalArgumentException("algorithm is null");
        }

        this.algorithm = algorithm;
        this.key = new byte[key.length];
        System.arraycopy(key, 0, this.key, 0, key.length);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public SecretKeySpec(byte[] key, int offset, int len, String algorithm) {
    	if (key == null) {
    		throw new IllegalArgumentException("key is null");
    	}
    	if (key.length == 0) {
    		throw new IllegalArgumentException("key is empty");
    	}
    	if ((key.length - offset < len)) {
    		throw new IllegalArgumentException("key is too short");
    	}
        if (algorithm == null) {
            throw new IllegalArgumentException("algorithm is null");
        }
        this.algorithm = algorithm;
        this.key = new byte[len];
        System.arraycopy(key, offset, this.key, 0, len);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getAlgorithm() {
        return algorithm;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getFormat() {
        return format;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public byte[] getEncoded() {
        byte[] result = new byte[key.length];
        System.arraycopy(key, 0, result, 0, key.length);
        return result;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int hashCode() {
        int result = algorithm.length();
        for (int i=0; i<key.length; i++) {
            result += key[i];
        }
        return result;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SecretKeySpec)) {
            return false;
        }
        SecretKeySpec ks = (SecretKeySpec) obj;
        return (algorithm.equalsIgnoreCase(ks.algorithm))
            && (Arrays.equals(key, ks.key));
    }
}

