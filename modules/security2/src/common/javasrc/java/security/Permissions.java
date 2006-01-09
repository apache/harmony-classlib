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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @com.intel.drl.spec_ref
 */
public final class Permissions extends PermissionCollection implements
    Serializable {

    /**
     * @com.intel.drl.spec_ref
     */
    private static final long serialVersionUID = 4858622370623524688L;

    private static final ObjectStreamField[] serialPersistentFields = {
        new ObjectStreamField("perms", Hashtable.class),
        new ObjectStreamField("allPermission", PermissionCollection.class), };

    // Hash to store PermissionCollection's
    private transient Map klasses = new HashMap();

    /**
     * @com.intel.drl.spec_ref
     */
    private boolean allEnabled;  // = false;

    /**
     * @com.intel.drl.spec_ref
     */
    public void add(Permission permission) {
        if (isReadOnly()) {
            throw new SecurityException("collection is read-only");
        }

        if (permission == null) {
            throw new NullPointerException("invalid null permission");
        }

        Class klass = permission.getClass();
        PermissionCollection klassMates = (PermissionCollection)klasses
            .get(klass);

        if (klassMates == null) {
            synchronized (klasses) {
                klassMates = (PermissionCollection)klasses.get(klass);
                if (klassMates == null) {

                    klassMates = permission.newPermissionCollection();
                    if (klassMates == null) {
                        klassMates = new PermissionsHash();
                    }
                    klasses.put(klass, klassMates);
                }
            }
        }
        klassMates.add(permission);

        if (klass == AllPermission.class) {
            allEnabled = true;
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Enumeration elements() {
        return new MetaEnumeration(klasses.values().iterator());
    }

    /**
     * An auxiliary implementation for enumerating individual permissions from a
     * collection of PermissionCollections.
     * 
     */
    final static class MetaEnumeration implements Enumeration {

        private Iterator pcIter;

        private Enumeration current;

        /**
         * Initiates this enumeration.
         * 
         * @param outer an iterator over external collection of
         *        PermissionCollections
         */
        public MetaEnumeration(Iterator outer) {
            pcIter = outer;
            current = getNextEnumeration();
        }

        private Enumeration getNextEnumeration() {
            while (pcIter.hasNext()) {
                Enumeration en = ((PermissionCollection)pcIter.next())
                    .elements();
                if (en.hasMoreElements()) {
                    return en;
                }
            }
            return null;
        }

        /**
         * Indicates if there are more elements to enumerate.
         */
        public boolean hasMoreElements() {
            return current != null /* && current.hasMoreElements() */;
        }

        /**
         * Returns next element.
         */
        public Object nextElement() {
            if (current != null) {
                //assert current.hasMoreElements();
                Object next = current.nextElement();
                if (!current.hasMoreElements()) {
                    current = getNextEnumeration();
                }

                return next;
            }
            throw new NoSuchElementException("no more elements");
        }
    }

    /**
     * @com.intel.drl.spec_ref 
     * Before actual implication checking, this method
     * tries to resolve UnresolvedPermissions (if any)
     * against the passed instance. Successfully
     * resolved permissions (if any) are taken into
     * account during further processing.
     */
    public boolean implies(Permission permission) {
        if (allEnabled) {
            return true;
        }
        if (permission == null) {
            return false;
        }
        Class klass = permission.getClass();
        PermissionCollection klassMates = null;

        UnresolvedPermissionCollection billets = (UnresolvedPermissionCollection)klasses
            .get(UnresolvedPermission.class);
        if (billets != null && billets.hasUnresolved(permission)) {
            // try to fill up klassMates with freshly resolved permissions
            synchronized (klasses) {
                klassMates = (PermissionCollection)klasses.get(klass);
                try {
                    klassMates = billets.resolveCollection(permission,
                                                           klassMates);
                } catch (Exception ignore) {
                    //TODO log warning
                    ignore.printStackTrace();
                }

                if (klassMates != null) {
                    //maybe klassMates were just created
                    // so put them into common map
                    klasses.put(klass, klassMates);
                    // very uncommon case, but not improbable one
                    if (klass == AllPermission.class) {
                        allEnabled = true;
                    }
                }
            }
        } else {
            klassMates = (PermissionCollection)klasses.get(klass);
        }

        if (klassMates != null) {
            return klassMates.implies(permission);
        }
        return false;
    }

    /**
     * Reads the object from stream and checks for consistency.
     */
    private void readObject(java.io.ObjectInputStream in) throws IOException,
        ClassNotFoundException {
        ObjectInputStream.GetField fields = in.readFields();
        Map perms = (Map)fields.get("perms", null);
        klasses = new HashMap();
        synchronized (klasses) {
            for (Iterator iter = perms.keySet().iterator(); iter.hasNext();) {
                Class key = (Class)iter.next();
                PermissionCollection pc = (PermissionCollection)perms.get(key);
                if (key != pc.elements().nextElement().getClass()) {
                    throw new InvalidObjectException("collection is corrupted");
                }
                klasses.put(key, pc);
            }
        }
        allEnabled = fields.get("allPermission", null) != null;
        if (allEnabled && !klasses.containsKey(AllPermission.class)) {
            throw new InvalidObjectException("all-enabled flag is corrupted");
        }
    }

    /**
     * Outputs fields via default mechanism.
     */
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("perms", new Hashtable(klasses));
        fields.put("allPermission", allEnabled ? klasses
            .get(AllPermission.class) : null);
        out.writeFields();
    }
}

/**
 * A default PermissionCollection implementation that uses a hashtable. Each
 * hashtable entry stores a Permission object as both the key and the value.
 * <br>
 * This PermissionCollection is intended for storing &quot;neutral&quot;
 * permissions which do not require special collection.
 * 
 */

final class PermissionsHash extends PermissionCollection {

    /**
     * @com.intel.drl.spec_ref
     */
    private static final long serialVersionUID = -8491988220802933440L;

    /**
     * @com.intel.drl.spec_ref
     */
    private final Hashtable perms = new Hashtable();

    /**
     * Adds passed permission (of any type) to the collection. The read-only
     * flag is ignored, as this class is used as internal storage only.
     */
    public void add(Permission permission) {
        perms.put(permission, permission);
    }

    /**
     * Returns enumeration of contained elements.
     */
    public Enumeration elements() {
        return perms.elements();
    }

    /**
     * Checks if this collection implies the particular permission.
     * 
     * @return true if some of contained permissions implies the passed
     *         permission, false otherwise
     */
    public boolean implies(Permission permission) {
        for (Enumeration elements = elements(); elements.hasMoreElements();) {
            if (((Permission)elements.nextElement()).implies(permission)) {
                return true;
            }
        }
        return false;
    }
}