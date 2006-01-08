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

package java.security;

/**
 * @com.intel.drl.spec_ref 
 */
public class ProtectionDomain {

    // CodeSource for this ProtectionDomain
    private CodeSource codeSource;

    // Static permissions for this ProtectionDomain
    private PermissionCollection permissions;

    // ClassLoader
    private ClassLoader classLoader;

    // Set of principals associated with this ProtectionDomain
    private Principal[] principals;

    // false if this ProtectionDomain was constructed with static 
    // permissions, true otherwise. 
    private boolean dynamicPerms;

    /**
     * @com.intel.drl.spec_ref 
     */
    public ProtectionDomain(CodeSource cs, PermissionCollection permissions) {
        this.codeSource = cs;
        if (permissions != null) {
            permissions.setReadOnly();
        }
        this.permissions = permissions;
        //this.classLoader = null;
        //this.principals = null;
        //dynamicPerms = false;
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public ProtectionDomain(CodeSource cs, PermissionCollection permissions,
            ClassLoader cl, Principal[] principals) {
        this.codeSource = cs;
        if (permissions != null) {
            permissions.setReadOnly();
        }
        this.permissions = permissions;
        this.classLoader = cl;
        if (principals != null) {
            this.principals = new Principal[principals.length];
            System.arraycopy(principals, 0, this.principals, 0,
                    this.principals.length);
        }
        dynamicPerms = true;
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public final ClassLoader getClassLoader() {
        return classLoader;
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public final CodeSource getCodeSource() {
        return codeSource;
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public final PermissionCollection getPermissions() {
        return permissions;
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public final Principal[] getPrincipals() {
        if( principals == null ) {
            return new Principal[0];
        }
        Principal[] tmp = new Principal[principals.length];
        System.arraycopy(principals, 0, tmp, 0, tmp.length);
        return tmp;
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public boolean implies(Permission permission) {
        // First, test with the Policy, as the default Policy.implies() 
        // checks for both dynamic and static collections of the 
        // ProtectionDomain passed...
        if (dynamicPerms
                && Policy.getAccessiblePolicy().implies(this, permission)) {
            return true;
        }

        // ... and we get here if 
        // either the permissiona are static
        // or Policy.implies() did not check for static permissions
        // or the permission is not implied
        return permissions == null ? false : permissions.implies(permission);
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public String toString() {
        //FIXME: 1.5 use StreamBuilder here
        StringBuffer buf = new StringBuffer(200);
        buf.append("ProtectionDomain\n");
        buf.append("CodeSource=").append(
                codeSource == null ? "<null>" : codeSource.toString()).append(
                "\n");
        buf.append("ClassLoader=").append(
                classLoader == null ? "<null>" : classLoader.toString())
                .append("\n");
        if (principals == null || principals.length == 0) {
            buf.append("<no principals>\n");
        } else {
            buf.append("Principals: <\n");
            for (int i = 0; i < principals.length; i++) {
                buf.append("\t").append(
                        principals[i] == null ? "<null>" : principals[i]
                                .toString()).append("\n");
            }
            buf.append(">");
        }

        //permissions here
        buf.append("Permissions:\n");
        if (permissions == null) {
            buf.append("\t\t<no static permissions>\n");
        } else {
            buf.append("\t\tstatic: ").append(permissions.toString()).append(
                    "\n");
        }

        if (dynamicPerms) {
            if (Policy.isSet()) {
                PermissionCollection perms;
                perms = Policy.getAccessiblePolicy().getPermissions(this);
                if (perms == null) {
                    buf.append("\t\t<no dynamic permissions>\n");
                } else {
                    buf.append("\t\tdynamic: ").append(perms.toString())
                            .append("\n");
                }
            } else {
                buf.append("\t\t<no dynamic permissions>\n");
            }
        }
        return buf.toString();
    }
}