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
* @author Maxim V. Makarov
* @version $Revision$
*/

package javax.security.auth.kerberos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.security.BasicPermission;
import java.security.Permission;
import java.security.PermissionCollection;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Vector;

import org.apache.harmony.auth.internal.nls.Messages;

/**
 * @com.intel.drl.spec_ref
 */

public final class DelegationPermission extends BasicPermission implements
        Serializable {

    /**
     * @com.intel.drl.spec_ref
     */
    private static final long serialVersionUID = 883133252142523922L;

    // initialization of a target name
    private static String init(String name) {

        String trName = name.trim();

        int length = trName.length();
        // length MUST be at least 7 characters
        if (length < 7) {
            throw new IllegalArgumentException(
                    Messages.getString("auth.20")); //$NON-NLS-1$

        }

        int index = name.indexOf('"', 2);

        if (trName.charAt(0) != '"' || index == -1
                || (index + 6) > trName.length()
                || trName.charAt(index + 1) != ' '
                || trName.charAt(index + 2) != '"'
                || trName.charAt(trName.length() - 1) != '"') {
            throw new IllegalArgumentException(
                    Messages.getString("auth.20")); //$NON-NLS-1$
        }
        return trName;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public DelegationPermission(String principals) {
        super(init(principals));
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public DelegationPermission(String principals, String action) {
        super(init(principals), action);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        return this.getName().equals(((DelegationPermission) obj).getName());
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean implies(Permission permission) {
        return equals(permission);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int hashCode() {
        return getName().hashCode();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public PermissionCollection newPermissionCollection() {
        return new KrbDelegationPermissionCollection();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    private void writeObject(ObjectOutputStream s) throws IOException,
            ClassNotFoundException {
        s.defaultWriteObject();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    private void readObject(ObjectInputStream s) throws IOException,
            ClassNotFoundException {
        s.defaultReadObject();
        init(getName());
    }

}

/**
 * Specific PermissionCollection for storing DelegationPermissions
 * 
 */

class KrbDelegationPermissionCollection extends PermissionCollection implements
        Serializable {

    private static final long serialVersionUID = -3383936936589966948L;

    private transient DelegationPermission[] items = new DelegationPermission[10];

    private transient int offset;

    private static final ObjectStreamField[] serialPersistentFields = { new ObjectStreamField(
            "permissions", Vector.class) }; //$NON-NLS-1$

    //initialization of a collection
    KrbDelegationPermissionCollection() {
    }

    /**
     * Adds a ServicePermission to the collection.
     */
    public void add(Permission permission) {

        if (isReadOnly()) {
            throw new SecurityException(Messages.getString("auth.21")); //$NON-NLS-1$
        }

        if (permission == null || !(permission instanceof DelegationPermission)) {
            throw new IllegalArgumentException(Messages.getString("auth.22", permission)); //$NON-NLS-1$
        }
        synchronized (this) {
            if (offset == items.length) {
                DelegationPermission[] dp = new DelegationPermission[items.length * 2];
                System.arraycopy(items, 0, dp, 0, offset);
                items = dp;
            }
            items[offset++] = (DelegationPermission) permission;
        }
    }

    /**
     * Returns enumeration of the collection.
     */
    public Enumeration elements() {
        return new Enumeration() {
            private int index = 0;

            public boolean hasMoreElements() {
                return index < offset;
            }

            public Object nextElement() {
                if (index == offset) {
                    throw new NoSuchElementException();
                }
                return items[index++];
            }
        };
    }

    /**
     * Returns true if this collection implies the specified permission. 
     */
    public boolean implies(Permission permission) {
        if (permission == null || !(permission instanceof DelegationPermission)) {
            return false;
        }

        synchronized (this) {
            for (int i = 0; i < offset; i++) {
                if (items[i].implies(permission)) {
                    return true;
                }
            }
        }
        return false;
    }

    // white a collection to stream
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        Vector permissions;
        synchronized (this) {
            permissions = new Vector(offset);
            for (int i = 0; i < offset; permissions.add(items[i++])) {
            }
        }
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("permissions", permissions); //$NON-NLS-1$
        out.writeFields();
    }

    // read a collection from stream
    private void readObject(java.io.ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        ObjectInputStream.GetField fields = in.readFields();
        Vector permissions = (Vector) fields.get("permissions", null); //$NON-NLS-1$
        items = new DelegationPermission[permissions.size() * 2];
        for (offset = 0; offset < items.length / 2;) {
            Object obj = permissions.get(offset);
            if (obj == null || !(obj instanceof DelegationPermission)) {
                throw new IllegalArgumentException(Messages.getString("auth.22", obj)); //$NON-NLS-1$
            }
            items[offset++] = (DelegationPermission) obj;
        }
    }
}