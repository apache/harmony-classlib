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
 * This class represents a unix groupd by its group id. 
 */
public class UnixNumericGroupPrincipal implements Serializable, Principal {

    /**
     * @serial
     */
    private static final long serialVersionUID = -535408497353506159L;

    // Group id
    private long gid;
    // Group name
    private String gname;
    // Shows whether the group is primary for a user or not
    private boolean primary;

    /**
     * Creates the object using a String representation of gid.
     * @param gid string representation of gid
     * @param primary shows whether the group is primary
     * throws NullPointerException if gid is null
     */
    public UnixNumericGroupPrincipal(String gid, boolean primary) {
        if (gid == null) {
            throw new NullPointerException("gid can not be null");
        }
        this.gid = Long.parseLong(gid);
        this.primary = primary;
    }

    /**
     * Creates the object using gid passed.
     * @param gid gid
     * @param primary shows whether the group is primary
     */
    public UnixNumericGroupPrincipal(long gid, boolean primary) {
        this.gid = gid;
        this.primary = primary;
    }

    /**
     * Creates the object using gid and group's name passed.
     * @param gid gid
     * @param gname group name
     * @param primary shows whether the group is primary
     */
    public UnixNumericGroupPrincipal(long gid, String gname, boolean primary) {
        this.gid = gid;
        this.gname = gname;
        this.primary = primary;
    }
    
    /**
     * Returns String representation of the stored GID.
     */
    public String getName() {
        return Long.toString(gid);
    }

    /**
     * Returns group name.
     */
    public String getObjectName() {
        return gname;
    }
    
    /**
     * Returns numeric representation of the stored gid.
     */
    public long longValue() {
        return gid;
    }

    /**
     * Returns String representation of this object.
     */
    public String toString() {
        if( gname == null ) {
            return "UnixNumericGroupPrincipal, gid=" + gid;
        }
        else {
            return "UnixNumericGroupPrincipal, gid=" + gid+"; name="+gname;
        }
    }

    /**
     * Tests objects for equality.<br>
     * The objects are considered equals if they both are of type 
     * UnixNumericGroupPrincipal and have the same gid.
     */
    public boolean equals(Object o) {
        if (o instanceof UnixNumericGroupPrincipal) {
            return ((UnixNumericGroupPrincipal) o).gid == gid;
        }
        return false;
    }

    /**
     * Returns hash code of this object.
     */
    public int hashCode() {
        return (int) gid;
    }
}
