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

package javax.crypto.spec;

import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKey;

/**
 * 
 * 
 * @author Diego Raúl Mercado
 * @version 1.2
 * @ar.org.fitc.spec_ref
 */
public class SecretKeySpec implements KeySpec, SecretKey {
	private static final long serialVersionUID = 6577238317307289933L;

    /** Constant that indicates this format */
    private static final String FORMAT_RAW = "RAW";

    private String algorithm;

    private byte[] key;
    
    /** @ar.org.fitc.spec_ref */
    public SecretKeySpec(byte[] key, int offset, int len, String algorithm) {
    	if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        if (algorithm == null) {
            throw new IllegalArgumentException("algorithm cannot be null");
        }
        if (key.length == 0) {
            throw new IllegalArgumentException("key cannot be empty");
        }
        if (len < 0) {
            throw new ArrayIndexOutOfBoundsException("len is negative");
        }
        if (key.length - offset < len) {
            throw new IllegalArgumentException(
                    "Invalid offset/length combination");
        }

        this.key = new byte[len];
        System.arraycopy(key, offset, this.key, 0, len);
        this.algorithm = algorithm;
    }

    /** @ar.org.fitc.spec_ref */
    public SecretKeySpec(byte[] key, String algorithm) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        if (algorithm == null) {
            throw new IllegalArgumentException("algorithm cannot be null");
        }
        if (key.length == 0) {
            throw new IllegalArgumentException("key cannot be empty");
        }
        this.key = key.clone();
        this.algorithm = algorithm;
    }

    /** @ar.org.fitc.spec_ref */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj != null && obj instanceof SecretKey) {
            SecretKey copy = (SecretKey) obj;
            if (algorithm.equalsIgnoreCase(copy.getAlgorithm()) 
                    && Arrays.equals(key, copy.getEncoded())) {
                return true;
            }
        }
        return false;
    }

    /** @ar.org.fitc.spec_ref */
    public String getAlgorithm() {
        return algorithm;
    }

    /** @ar.org.fitc.spec_ref */
    public byte[] getEncoded() {
        return key.clone();
    }

    /** @ar.org.fitc.spec_ref */
    public String getFormat() {
        return FORMAT_RAW;
    }

    /** @ar.org.fitc.spec_ref */
    public int hashCode() {
        return Arrays.hashCode(key) ^ algorithm.hashCode();
    }
}
