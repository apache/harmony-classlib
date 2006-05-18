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
 * Subclass of Permission whose instances imply all other permissions. Granting
 * this permission is equivalent to disabling security.
 * 
 */
public final class AllPermission extends Permission {

    /**
     * @serial
     */
    private static final long serialVersionUID = -2916474571451318075L;

    // Pemission name
    private static final String ALL_PERMISSIONS = "<all permissions>";

    // Actions name
    private static final String ALL_ACTIONS = "<all actions>";

	/**
	 * Constructs a new instance of this class. The two argument version is
	 * provided for class <code>Policy</code> so that it has a consistant call
	 * pattern across all Permissions. The name and action list are both
	 * ignored.
	 * 
	 * @param name
	 *            java.lang.String ignored.
	 * @param actions
	 *            java.lang.String ignored.
	 */
    public AllPermission(String name, String actions) {
        super(ALL_PERMISSIONS);
    }

	/**
	 * Constructs a new instance of this class.
	 */
    public AllPermission() {
        super(ALL_PERMISSIONS);
    }

	/**
	 * Compares the argument to the receiver, and answers true if they represent
	 * the <em>same</em> object using a class specific comparison. All
	 * AllPermissions are equal to eachother.
	 * 
	 * @param obj
	 *            the object to compare with this object
	 * @return <code>true</code> if the object is the same as this object
	 *         <code>false</code> if it is different from this object
	 * @see #hashCode
	 */
    public boolean equals(Object obj) {
        return (obj instanceof AllPermission);
    }

	/**
	 * Answers an integer hash code for the receiver. Any two objects which
	 * answer <code>true</code> when passed to <code>equals</code> must
	 * answer the same value for this method.
	 * 
	 * @return the receiver's hash
	 * 
	 * @see #equals
	 */
    public int hashCode() {
        return 1;
    }

	/**
	 * Answers the actions associated with the receiver. Since AllPermission
	 * objects allow all actions, answer with the string "<all actions>".
	 * 
	 * @return String the actions associated with the receiver.
	 */
    public String getActions() {
        return ALL_ACTIONS;
    }

	/**
	 * Indicates whether the argument permission is implied by the receiver.
	 * AllPermission objects imply all other permissions.
	 * 
	 * @return boolean <code>true</code> if the argument permission is implied
	 *         by the receiver, and <code>false</code> if it is not.
	 * @param permission
	 *            java.security.Permission the permission to check
	 */
    public boolean implies(Permission permission) {
        return true;
    }

	/**
	 * Answers a new PermissionCollection for holding permissions of this class.
	 * Answer null if any permission collection can be used.
	 * 
	 * @return a new PermissionCollection or null
	 * 
	 * @see java.security.BasicPermissionCollection
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
	 * Indicates whether the argument permission is implied by the receiver.
	 * AllPermission objects imply all other permissions.
	 * 
	 * @return boolean <code>true</code> if the argument permission is implied
	 *         by the receiver, and <code>false</code> if it is not.
	 * @param permission
	 *            java.security.Permission the permission to check
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
