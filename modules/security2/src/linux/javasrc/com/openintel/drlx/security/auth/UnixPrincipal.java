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

import java.io.Serializable;
import java.security.Principal;

/** 
 * This class represents a unix user by its name. 
 */
public class UnixPrincipal implements Serializable, Principal {

    // User name
    private String name;

    /**
     * Sole constructor.
     * @param name user name
     * @throws NullPointerException if name is null
     */
    public UnixPrincipal(String name) {
        if (name == null) {
            throw new NullPointerException("name can not be null");
        }
        this.name = name;
    }

    /**
     * @return user name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns string representation of this object
     */
    public String toString() {
        return "UnixPrincipal, name=" + name;
    }

    /**
     * Compares two UnixPrincipal objects.<br>
     * Two pricipal objects are considered equal if they are both of type 
     * UnixPrincipal and their names are equal.
     */
    public boolean equals(Object o) {
        if (o instanceof UnixPrincipal) {
            return name.equals(((UnixPrincipal) o).name);
        }
        return false;
    }

    /**
     * Return hash code of this object.
     */
    public int hashCode() {
        return name.hashCode();
    }
}