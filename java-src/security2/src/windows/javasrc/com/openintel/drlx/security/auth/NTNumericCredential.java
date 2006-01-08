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
 * @author Alexander V. Astapchuk
 * @version $Revision$
 */
package com.openintel.drlx.security.auth;

/** 
 * A Credential which stores information about impersonation token. 
 */
public class NTNumericCredential {

    //
    private long token;

    /**
     * The sole ctor.
     */
    public NTNumericCredential(long token) {
        this.token = token;
    }

    /**
     * Tests two objects for equality.<br>
     * Two objects are considered equal if they both represent 
     * NTNumericCredential and they both have the same token value.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof NTNumericCredential) {
            return token == ((NTNumericCredential) obj).token;
        }
        return false;
    }

    /**
     * Returns stored token.
     */
    public long getToken() {
        return token;
    }

    /**
     * Returns hashCode for this object.
     */
    public int hashCode() {
        return (int) token;
    }

    /**
     * Returns String representation of this object.
     */
    public String toString() {
        return "NTNumericCredential: 0x" + Long.toHexString(token);
    }
}