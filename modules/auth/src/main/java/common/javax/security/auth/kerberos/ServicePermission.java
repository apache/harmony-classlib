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
import java.security.Permission;
import java.security.PermissionCollection;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * @com.intel.drl.spec_ref
 * 
 */

public final class ServicePermission extends Permission implements Serializable {

    /** 
     * @com.intel.drl.spec_ref 
     */
    private static final long serialVersionUID = -1227585031618624935L;

    private static final String INITIATE = "initiate";
    private static final String ACCEPT = "accept";
    private static final String INITIATE_ACCEPT = "initiate,accept";
    private static final String[] ACTIONS_TABLE = {"", ACCEPT, INITIATE, INITIATE_ACCEPT};

    private final static char ACCEPT_MASK = 1;
    private final static char INITIATE_MASK = 2;

    private static final int INITIATE_LEN = INITIATE.length();
    private static final int ACCEPT_LEN = ACCEPT.length();
    private static final int MIN_LEN = Math.min(INITIATE_LEN,ACCEPT_LEN); 

    private static Class thisClass = ServicePermission.class;

    /** 
     * ACCEPT_MASK, INITIATE_ACCEPT or (INITIATE_ACCEPT | ACCEPT_MASK)
     */
    private String actions;

    // initialization of actions
    private void initActions(String actions) {
        if (actions == null || actions.length() < MIN_LEN) {
            throw new IllegalArgumentException("Invalid actions mask");
        }

        char[] c_acts = actions.toCharArray();

        int result = 0;
        int ptr = 0;

        int len6 = c_acts.length - ACCEPT_LEN;
        int len8 = c_acts.length - INITIATE_LEN;

        do {
            //skipping whitespaces
            while (ptr <= len6
                    && (c_acts[ptr] == ' ' || c_acts[ptr] == '\t'
                            || c_acts[ptr] == '\n' || c_acts[ptr] == 0x0B
                            || c_acts[ptr] == '\f' || c_acts[ptr] == '\r')) {
                ++ptr;
            }

            if (ptr > len6) {
                // expect string "accept" or "initiate", not just white
                // spaces
                throw new IllegalArgumentException("Invalid actions mask");
            }

            //parsing string
            if ((c_acts[ptr] == 'a' || c_acts[ptr] == 'A')
                    && (c_acts[ptr + 1] == 'c' || c_acts[ptr + 1] == 'C')
                    && (c_acts[ptr + 2] == 'c' || c_acts[ptr + 2] == 'C')
                    && (c_acts[ptr + 3] == 'e' || c_acts[ptr + 3] == 'E')
                    && (c_acts[ptr + 4] == 'p' || c_acts[ptr + 4] == 'P')
                    && (c_acts[ptr + 5] == 't' || c_acts[ptr + 5] == 'T')) {
                result |= ACCEPT_MASK;
                ptr += ACCEPT_LEN;
            } else if (ptr <= len8
                    && (c_acts[ptr] == 'i' || c_acts[ptr] == 'I')
                    && (c_acts[ptr + 1] == 'n' || c_acts[ptr + 1] == 'N')
                    && (c_acts[ptr + 2] == 'i' || c_acts[ptr + 2] == 'I')
                    && (c_acts[ptr + 3] == 't' || c_acts[ptr + 3] == 'T')
                    && (c_acts[ptr + 4] == 'i' || c_acts[ptr + 4] == 'I')
                    && (c_acts[ptr + 5] == 'a' || c_acts[ptr + 5] == 'A')
                    && (c_acts[ptr + 6] == 't' || c_acts[ptr + 6] == 'T')
                    && (c_acts[ptr + 7] == 'e' || c_acts[ptr + 7] == 'E')) {
                result |= INITIATE_MASK;
                ptr += INITIATE_LEN;
            } else {
                throw new IllegalArgumentException("Invalid actions mask");
            }

            //skipping trailing whitespaces
            while (ptr < c_acts.length
                    && (c_acts[ptr] == ' ' || c_acts[ptr] == '\t'
                            || c_acts[ptr] == '\n' || c_acts[ptr] == 0x0B
                            || c_acts[ptr] == '\f' || c_acts[ptr] == '\r')) {
                ptr++;
            }

            if (ptr == c_acts.length) {
                this.actions = ACTIONS_TABLE[result];
                return;
            }
        } while (c_acts[ptr++] == ',');

        // unknown trailing symbol
        throw new IllegalArgumentException("Invalid actions mask");
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public ServicePermission(String name, String actions) {
        super(name);

        if (name == null) {
            throw new NullPointerException("service principal is null");
        }
        if (name.trim().length() == 0) {
            throw new IllegalArgumentException("service principal is empty");
        }

        initActions(actions);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || thisClass != obj.getClass()) {
            return false;
        }
        ServicePermission sp = (ServicePermission) obj;

        return actions == sp.actions && getName().equals(sp.getName());
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int hashCode() {
        return getName().hashCode() * actions.length();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getActions() {
        return actions;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean implies(Permission permission) {
        if (this == permission) {
            return true;
        }

        if (permission == null || thisClass != permission.getClass()) {
            return false;
        }

        ServicePermission sp = (ServicePermission) permission;
        String name = getName();

        return (actions == INITIATE_ACCEPT || actions == sp.actions)
				&& (name.length() == 1 && name.charAt(0) == '*' || name.equals(permission.getName()));
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public PermissionCollection newPermissionCollection() {
        return new KrbServicePermissionCollection();
    }

    /** 
     * @com.intel.drl.spec_ref 
     */
    private synchronized void writeObject(java.io.ObjectOutputStream s)
            throws IOException {
        s.defaultWriteObject();
    }

    /** 
     * @com.intel.drl.spec_ref 
     */
    private synchronized void readObject(java.io.ObjectInputStream s)
            throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        initActions(getActions());
    }
}
/**
 * Specific PermissionCollection for storing ServicePermissions
 * 
 */

final class KrbServicePermissionCollection extends PermissionCollection
        implements Serializable {

    private static final long serialVersionUID = -4118834211490102011L;

    private static final ObjectStreamField[] serialPersistentFields = { new ObjectStreamField(
            "permissions", Vector.class) };

    private transient ServicePermission[] items = new ServicePermission[10];

    private transient int offset;

    // initialization of a collection
    KrbServicePermissionCollection() {
    }

    /**
     * Adds a ServicePermission to the collection.
     */
    public void add(Permission permission) {

        if (isReadOnly()) {
            throw new SecurityException("collection is read-only");
        }

        if (permission == null || !(permission instanceof ServicePermission)) {
            throw new IllegalArgumentException("invalid permission: "
                    + permission);
        }
        synchronized (this) {
            if (offset == items.length) {
                ServicePermission[] sp = new ServicePermission[items.length * 2];
                System.arraycopy(items, 0, sp, 0, offset);
                items = sp;
            }
            items[offset++] = (ServicePermission) permission;
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

        if (permission == null || !(permission instanceof ServicePermission)) {
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

    // white collection to stream
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        Vector permissions;
        synchronized (this) {
            permissions = new Vector(offset);
            for (int i = 0; i < offset; permissions.add(items[i++])) {
            }
        }
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("permissions", permissions);
        out.writeFields();
    }

    // read collection from stream
    private void readObject(java.io.ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        ObjectInputStream.GetField fields = in.readFields();
        Vector permissions = (Vector) fields.get("permissions", null);
        items = new ServicePermission[permissions.size() * 2];
        for (offset = 0; offset < items.length / 2;) {
            Object obj = permissions.get(offset);
            if (obj == null || !(obj instanceof ServicePermission)) {
                throw new IllegalArgumentException("invalid permission: " + obj);
            }
            items[offset++] = (ServicePermission) obj;
        }
    }
}