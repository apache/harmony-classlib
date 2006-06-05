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

import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;

/**
 * 
 * 
 * @author Diego Raúl Mercado
 * @version 1.2
 * @ar.org.fitc.spec_ref
 */
public class RC5ParameterSpec implements AlgorithmParameterSpec {
    private int version;

    private int rounds;

    private int wordSize;

    private byte[] iv;

    /** @ar.org.fitc.spec_ref */
    public RC5ParameterSpec(int version, int rounds, int wordSize) {
        this.version = version;
        this.rounds = rounds;
        this.wordSize = wordSize;
    }

    /** @ar.org.fitc.spec_ref */
    public RC5ParameterSpec(int version, int rounds, int wordSize, byte[] iv) {
        this(version, rounds, wordSize, iv, 0);
    }

    /** @ar.org.fitc.spec_ref */
    public RC5ParameterSpec(int version, int rounds, int wordSize, byte[] iv,
            int offset) {
		if (iv == null) {
            throw new IllegalArgumentException("IV missing");
        }
        int size = 2 * (wordSize / 8);
        if (offset >= iv.length || iv.length - offset < size) {
            throw new IllegalArgumentException("IV too short");
        }
        this.iv = new byte[size];
        System.arraycopy(iv, offset, this.iv, 0, size);
        this.version = version;
        this.rounds = rounds;
        this.wordSize = wordSize;
    }

    /** @ar.org.fitc.spec_ref */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof RC5ParameterSpec) {
            RC5ParameterSpec rc5 = (RC5ParameterSpec) obj;
            if (rc5.version == version && rc5.rounds == rounds
                    && rc5.wordSize == wordSize
                    && Arrays.equals(rc5.iv, iv)) {
                return true;
            }
        }

        return false;
    }

    /** @ar.org.fitc.spec_ref */
    public byte[] getIV() {
        return ((iv == null) ? null : iv.clone());
    }

    /** @ar.org.fitc.spec_ref */
    public int getRounds() {
        return rounds;
    }

    /** @ar.org.fitc.spec_ref */
    public int getVersion() {
        return version;
    }

    /** @ar.org.fitc.spec_ref */
    public int getWordSize() {
        return wordSize;
    }

    /** @ar.org.fitc.spec_ref */
    public int hashCode() {
        return Arrays.hashCode(iv) ^ (version ^ rounds ^ wordSize);
    }
}
