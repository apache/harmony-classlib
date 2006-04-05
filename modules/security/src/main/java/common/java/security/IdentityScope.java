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
* @author Aleksei Y. Semenov
* @version $Revision$
*/

package java.security;

import java.util.Enumeration;

import org.apache.harmony.security.SystemScope;


/**
 * @com.intel.drl.spec_ref 
 * @deprecated
 */
public abstract class IdentityScope extends Identity {

    /**
     * @com.intel.drl.spec_ref
     */
    private static final long serialVersionUID = -2337346281189773310L;

    // systemScope holds reference to the current system scope
    private static IdentityScope systemScope;

    /**
     * @com.intel.drl.spec_ref 
     */
    protected IdentityScope() {
        super();
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public IdentityScope(String name) {
        super(name);
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public IdentityScope(String name, IdentityScope scope)
            throws KeyManagementException {
        super(name, scope);
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public static IdentityScope getSystemScope() {

        if (systemScope == null) {
            systemScope = new SystemScope("System Scope");
        }
        return systemScope;
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    protected static void setSystemScope(IdentityScope scope) {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkSecurityAccess("setSystemScope");
        }
        systemScope = scope;
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public abstract int size();

    /**
     * @com.intel.drl.spec_ref 
     */
    public abstract Identity getIdentity(String name);

    /**
     * @com.intel.drl.spec_ref 
     */
    public Identity getIdentity(Principal principal) {
        return getIdentity(principal.getName());
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public abstract Identity getIdentity(PublicKey key);

    /**
     * @com.intel.drl.spec_ref 
     */
    public abstract void addIdentity(Identity identity)
            throws KeyManagementException;

    /**
     * @com.intel.drl.spec_ref 
     */
    public abstract void removeIdentity(Identity identity)
            throws KeyManagementException;

    /**
     * @com.intel.drl.spec_ref 
     * TODO change in 1.5
     */
    public abstract Enumeration identities();

    /**
     * @com.intel.drl.spec_ref 
     */
    public String toString() {
        return new StringBuffer(super.toString())
                .append("[").append(size()).append("]").toString();
    }
}