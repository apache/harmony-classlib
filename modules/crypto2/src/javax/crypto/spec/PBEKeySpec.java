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

/**
 * 
 * 
 * @author Diego Raúl Mercado
 * @version 1.2
 * @ar.org.fitc.spec_ref
 */
public class PBEKeySpec implements KeySpec {
    private char[] password;

    private byte[] salt;

    private int iterationCount;

    private int keyLength;

    /**
     * It's used as a "private" constructor
     * 
     * @param password
     *            the password
     * @param salt
     *            the salt
     * @param iterationCount
     *            the iteration count
     * @param keyLength
     *            the key's length
     * @throws IllegalArgumentException
     *             if salt's length is cero or <code>iterationCount</code> is
     *             less than 1
     */
    private void pbeKeySpec(char[] password, byte[] salt, int iterationCount,
            int keyLength) {
        if (salt.length == 0) {
            throw new IllegalArgumentException("salt is empty");
        }
        if (iterationCount < 1) {
            throw new IllegalArgumentException("iterationCount is not positive");
        }
        this.salt = salt.clone();
        this.password = (password == null) ? new char[0] : password.clone();
        this.keyLength = keyLength;
        this.iterationCount = iterationCount;
    }

    /** @ar.org.fitc.spec_ref */
    public PBEKeySpec(char[] password) {
        this.password = (password == null) ? new char[0] : password.clone();
    }

    /** @ar.org.fitc.spec_ref */
    public PBEKeySpec(char[] password, byte[] salt, int iterationCount) {
        pbeKeySpec(password, salt, iterationCount, 0);
    }

    /** @ar.org.fitc.spec_ref */
    public PBEKeySpec(char[] password, byte[] salt, int iterationCount,
            int keyLength) {
        pbeKeySpec(password, salt, iterationCount, keyLength);
        if (keyLength < 1) {
            throw new IllegalArgumentException(
                    "KeyLength must be greater than 0");
        }
    }

    /** @ar.org.fitc.spec_ref */
    public final void clearPassword() {
        Arrays.fill(password, (char) 0);
        password = null;
    }

    /** @ar.org.fitc.spec_ref */
    public final int getIterationCount() {
        return iterationCount;
    }

    /** @ar.org.fitc.spec_ref */
    public final int getKeyLength() {
        return keyLength;
    }

    /** @ar.org.fitc.spec_ref */
    public final char[] getPassword() {
        if (password == null) {
            throw new IllegalStateException("password has been wiped out");
        }
        return password.clone();
    }

    /** @ar.org.fitc.spec_ref */
    public final byte[] getSalt() {
        return (salt == null) ? null : salt.clone();
    }
}
