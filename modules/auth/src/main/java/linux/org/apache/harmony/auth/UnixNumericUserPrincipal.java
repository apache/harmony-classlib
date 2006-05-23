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
package org.apache.harmony.auth;

import java.io.Serializable;
import java.security.Principal;

/**
 * This class represents a unix user by its user id. 
 */
public class UnixNumericUserPrincipal implements Serializable, Principal {

    /**
     * @serial
     */
    private static final long serialVersionUID = -8301723108164907822L;

    // User id
    private long uid;

    /**
     * Creates the object using a String representation of uid.
     * @param id string representation of uid
     * throws NullPointerException if uid is null
     */
    public UnixNumericUserPrincipal(String uid) {
        if (uid == null) {
            throw new NullPointerException("uid can not be null");
        }
        this.uid = Long.parseLong(uid);
    }

    /**
     * Creates the object using uid passed.
     * @param uid uid
     */
    public UnixNumericUserPrincipal(long uid) {
        this.uid = uid;
    }

    /**
     * Returns String representation of the stored UID.
     */
    public String getName() {
        return Long.toString(uid);
    }

    /**
     * Returns numeric representation of the stored uid.
     */
    public long longValue() {
        return uid;
    }

    /**
     * Returns String representation of this object.
     */
    public String toString() {
        return "UnixNumericUserPrincipal, uid=" + uid;
    }

    /**
     * Tests objects for equality.<br>
     * The objects are considered equals if they both are of type 
     * UnixNumericUserPrincipal and have the same uid.
     */
    public boolean equals(Object o) {
        if (o instanceof UnixNumericUserPrincipal) {
            return ((UnixNumericUserPrincipal) o).uid == uid;
        }
        return false;
    }

    /**
     * Returns hash code of this object.
     */
    public int hashCode() {
        return (int) uid;
    }
}