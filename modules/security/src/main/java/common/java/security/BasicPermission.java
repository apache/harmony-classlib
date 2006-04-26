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
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/**
 * Superclass of permissions which have names but no action lists.
 * 
 */

public abstract class BasicPermission extends Permission implements
    Serializable {

    private static final long serialVersionUID = 6279438298436773498L;

	/**
	 * Creates an instance of this class with the given name and action list.
	 * 
	 * @param name
	 *            String the name of the new permission.
	 */
    public BasicPermission(String name) {
        super(name);
        checkName(name);
    }

	/**
	 * Creates an instance of this class with the given name and action list.
	 * The action list is ignored.
	 * 
	 * @param name
	 *            String the name of the new permission.
	 * @param action
	 *            String ignored.
	 */
    public BasicPermission(String name, String action) {
        super(name);
        checkName(name);
    }

    /**
     * Checks name parameter
     */ 
    private final void checkName(String name) {
        if (name == null) {
            throw new NullPointerException("name must not be null");
        }
        if (name.length() == 0) {
            throw new IllegalArgumentException("name must not be empty");
        }
    }

	/**
	 * Compares the argument to the receiver, and answers true if they represent
	 * the <em>same</em> object using a class specific comparison. In this
	 * case, the receiver and the object must have the same class and name.
	 * 
	 * @param obj
	 *            the object to compare with this object
	 * @return <code>true</code> if the object is the same as this object
	 *         <code>false</code> if it is different from this object
	 * @see #hashCode
	 */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj != null && obj.getClass() == this.getClass()) {
            return this.getName().equals(((Permission)obj).getName());
        }
        return false;
    }

	/**
	 * Answers an integer hash code for the receiver. Any two objects which
	 * answer <code>true</code> when passed to <code>equals</code> must
	 * answer the same value for this method.
	 * 
	 * @return int the receiver's hash
	 * 
	 * @see #equals
	 */
    public int hashCode() {
        return getName().hashCode();
    }

	/**
	 * Answers the actions associated with the receiver. BasicPermission objects
	 * have no actions, so answer the empty string.
	 * 
	 * @return String the actions associated with the receiver.
	 */
    public String getActions() {
        return "";
    }

	/**
	 * Indicates whether the argument permission is implied by the receiver.
	 * 
	 * @return boolean <code>true</code> if the argument permission is implied
	 *         by the receiver, and <code>false</code> if it is not.
	 * @param permission
	 *            java.security.Permission the permission to check
	 */
    public boolean implies(Permission permission) {
        if (permission != null && permission.getClass() == this.getClass()) {
            return nameImplies(getName(), permission.getName());
        }
        return false;
    }

    /**
     * Checks if <code>thisName</code> implies <code>thatName</code>,
     * accordingly to hierarchical property naming convention.
     * It is assumed that names cannot be null or empty.
     */
    static boolean nameImplies(String thisName, String thatName) {
        if (thisName == thatName) {
            return true;
        }
        int end = thisName.length();
        if (end > thatName.length()) {
            return false;
        }
        if (thisName.charAt(--end) == '*'
            && (end == 0 || thisName.charAt(end - 1) == '.')) {
            //wildcard found
            end--;
        } else if (end != (thatName.length()-1)) {
            //names are not equal
            return false;
        }
        for (int i = end; i >= 0; i--) {
            if (thisName.charAt(i) != thatName.charAt(i)) {
                return false;
            }
        }
        return true;
    }

	/**
	 * Answers a new PermissionCollection for holding permissions of this class.
	 * Answer null if any permission collection can be used.
	 * <p>
	 * Note: For BasicPermission (and subclasses which do not override this
	 * method), the collection which is returned does <em>not</em> invoke the
	 * .implies method of the permissions which are stored in it when checking
	 * if the collection implies a permission. Instead, it assumes that if the
	 * type of the permission is correct, and the name of the permission is
	 * correct, there is a match.
	 * 
	 * @return a new PermissionCollection or null
	 * 
	 * @see java.security.BasicPermissionCollection
	 */
    public PermissionCollection newPermissionCollection() {
        return new BasicPermissionCollection();
    }

    /**
     * Checks name after default deserialization.
     */
    private void readObject(java.io.ObjectInputStream in) throws IOException,
        ClassNotFoundException {
        in.defaultReadObject();
        checkName(this.getName());
    }
}

/**
 * Specific PermissionCollection for storing BasicPermissions of arbitrary type.
 * 
 */

final class BasicPermissionCollection extends PermissionCollection {

    /**
     * @com.intel.drl.spec_ref
     */
    private static final long serialVersionUID = 739301742472979399L;

    private static final ObjectStreamField[] serialPersistentFields = {
        new ObjectStreamField("all_allowed", Boolean.TYPE),
        new ObjectStreamField("permissions", Hashtable.class),
        new ObjectStreamField("permClass", Class.class), };

    //should be final, but because of writeObject() cannot be
    private transient Map items = new HashMap();

    // tru if this Collection contains a BasicPermission with '*' as its permission name
    private transient boolean allEnabled; // = false;

    /**
     * @com.intel.drl.spec_ref
     */
    private Class permClass;

    /**
     * Adds a permission to the collection. The first added permission must be a
     * subclass of BasicPermission, next permissions must be of the same class
     * as the first one.
     * 
     * @see java.security.PermissionCollection#add(java.security.Permission)
     */
    public void add(Permission permission) {
        if (isReadOnly()) {
            throw new SecurityException("collection is read-only");
        }
        if (permission == null) {
            throw new IllegalArgumentException("invalid permission: null");
        }

        Class inClass = permission.getClass();
        if (permClass != null) {
            if (permClass != inClass) {
                throw new IllegalArgumentException("invalid permission: "
                    + permission);
            }
        } else if( !(permission instanceof BasicPermission)) {
            throw new IllegalArgumentException("invalid permission: "
                + permission);
        } else { 
            // this is the first element provided that another thread did not add
            synchronized (items) {
                if (permClass != null && inClass != permClass) {
                    throw new IllegalArgumentException("invalid permission: "
                        + permission);
                }
                permClass = inClass;
            }
        }

        String name = permission.getName();
        items.put(name, permission);
        allEnabled = allEnabled || (name.length() == 1 && '*' == name.charAt(0));
    }

    /**
     * Returns enumeration of contained elements.
     */
    public Enumeration elements() {
        return Collections.enumeration(items.values());
    }

	/**
	 * Indicates whether the argument permission is implied by the receiver.
	 * 
	 * @return boolean <code>true</code> if the argument permission is implied
	 *         by the receiver, and <code>false</code> if it is not.
	 * @param permission
	 *            java.security.Permission the permission to check
	 */
    public boolean implies(Permission permission) {
        if (permission == null || permission.getClass() != permClass) {
            return false;
        }
        if (allEnabled) {
            return true;
        }
        String checkName = permission.getName();
        //first check direct coincidence
        if (items.containsKey(checkName)) {
            return true;
        }
        //now check if there are suitable wildcards
        //suppose we have "a.b.c", let's check "a.b.*" and "a.*" 
        char[] name = checkName.toCharArray();
        //I presume that "a.b.*" does not imply "a.b." 
        //so the dot at end is ignored 
        int pos = name.length - 2; 
        for (; pos >= 0; pos--) {
            if (name[pos] == '.') {
                break;
            }
        }
        while (pos >= 0) {
            name[pos + 1] = '*'; 
            if (items.containsKey(new String(name, 0, pos + 2))) {
                return true;
            }
            for (--pos; pos >= 0; pos--) {
                if (name[pos] == '.') {
                    break;
                }
            }
        }
        return false;
    }

    /**
     * Expected format is the following:
     * <dl>
     * <dt>boolean all_allowed
     * <dd>This is set to true if this BasicPermissionCollection contains a
     * BasicPermission with '*' as its permission name.
     * <dt>Class&lt;T&gt; permClass
     * <dd>The class to which all BasicPermissions in this
     * BasicPermissionCollection belongs.
     * <dt>Hashtable&lt;K,V&gt; permissions
     * <dd>The BasicPermissions in this BasicPermissionCollection. All
     * BasicPermissions in the collection must belong to the same class. The
     * Hashtable is indexed by the BasicPermission name; the value of the
     * Hashtable entry is the permission.
     * </dl>
     */
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("all_allowed", allEnabled);
        fields.put("permissions", new Hashtable(items));
        fields.put("permClass", permClass);
        out.writeFields();
    }

    /**
     * Reads the object from stream and checks its consistency: all contained
     * permissions must be of the same subclass of BasicPermission.
     */
    private void readObject(java.io.ObjectInputStream in) throws IOException,
        ClassNotFoundException {
        ObjectInputStream.GetField fields = in.readFields();

        items = new HashMap();
        synchronized (items) {
            permClass = (Class)fields.get("permClass", null);
            items.putAll((Map)fields.get("permissions", new Hashtable()));
            for (Iterator iter = items.values().iterator(); iter.hasNext();) {
                if (iter.next().getClass() != permClass) {
                    throw new InvalidObjectException(
                        "Inconsistent types of contained permissions");
                }
            }
            allEnabled = fields.get("all_allowed", false);
            if (allEnabled && !items.containsKey("*")) {
                throw new InvalidObjectException(
                    "Invalid state of wildcard flag");
            }
        }
    }
}
