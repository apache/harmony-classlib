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
* @author Alexey V. Varlamov
* @version $Revision$
*/

package java.security;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.util.Enumeration;
import java.util.NoSuchElementException;

/**
 * @com.intel.drl.spec_ref
 */
public final class AllPermission extends Permission {

    // Pemission name
    private static final String ALL_PERMISSIONS = "<all permissions>";

    // Actions name
    private static final String ALL_ACTIONS = "<all actions>";

    /**
     * @com.intel.drl.spec_ref
     */
    public AllPermission(String name, String actions) {
        super(ALL_PERMISSIONS);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public AllPermission() {
        super(ALL_PERMISSIONS);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean equals(Object obj) {
        return (obj instanceof AllPermission);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int hashCode() {
        return 1;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getActions() {
        return ALL_ACTIONS;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean implies(Permission permission) {
        return true;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public PermissionCollection newPermissionCollection() {
        return new AllPermissionCollection();
    }
}

/**
 * Specific PermissionCollection for storing AllPermissions. All instances of
 * AllPermission are equivalent, so it is enough to store a single added
 * instance.
 * 
 */

final class AllPermissionCollection extends PermissionCollection {

    /**
     * @com.intel.drl.spec_ref
     */
    private static final long serialVersionUID = -4023755556366636806L;

    private static final ObjectStreamField[] serialPersistentFields = { new ObjectStreamField(
        "all_allowed", Boolean.TYPE), };

    // Single element of collection.
    private transient Permission all;

    /**
     * Adds an AllPermission to the collection.
     */
    public void add(Permission permission) {
        if (isReadOnly()) {
            throw new SecurityException("collection is read-only");
        }
        if (!(permission instanceof AllPermission)) {
            throw new IllegalArgumentException("invalid permission: "
                + permission);
        }
        all = permission;
    }

    /**
     * Returns enumeration of the collection.
     */
    public Enumeration elements() {
        return new SingletonEnumeration(all);
    }

    /**
     * An auxiliary implementation for enumerating a single object.
     * 
     */
    final static class SingletonEnumeration implements Enumeration {

        private Object element;

        /**
         * Constructor taking the single element.
         */
        public SingletonEnumeration(Object single) {
            element = single;
        }

        /**
         * Returns true if the element is not enumerated yet.
         */
        public boolean hasMoreElements() {
            return element != null;
        }

        /**
         * Returns the element and clears internal reference to it.
         */
        public Object nextElement() {
            if (element == null) {
                throw new NoSuchElementException("no more elements");
            }
            Object last = element;
            element = null;
            return last;
        }
    }

    /**
     * Returns true if this collection is not empty.
     */
    public boolean implies(Permission permission) {
        return all != null;
    }

    /**
     * Writes accordingly to expected format:
     * <dl>
     * <dt>boolean all_allowed
     * <dd>This is set to true if this collection is not empty
     * </dl>
     */
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("all_allowed", all != null);
        out.writeFields();
    }

    /**
     * Restores internal state.
     */
    private void readObject(java.io.ObjectInputStream in) throws IOException,
        ClassNotFoundException {
        ObjectInputStream.GetField fields = in.readFields();
        if (fields.get("all_allowed", false)) {
            all = new AllPermission();
        }
    }
}