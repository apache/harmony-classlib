/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.List;

import org.apache.harmony.security.fortress.PolicyUtils;
import org.apache.harmony.security.internal.nls.Messages;

/**
 * Holds permissions which are of an unknown type when a policy file is read.
 *
 * Technically, the resolution of UnresolvedPermissions and
 * substitution by actual permissions takes place in the
 * <code>implies()</code> method of a <code>Permissions</code>
 * collection, right before actual checking.
 * 
 */
public final class UnresolvedPermission extends Permission
    implements Serializable {

    /** 
     * @com.intel.drl.spec_ref 
     */
    private static final long serialVersionUID = -4821973115467008846L;


    private String type;    
    
    private String name;
    
    private String actions;

    // The signer certificates 
    private transient Certificate[] targetCerts;

    // Cached hash value
    private transient int hash;

	/**
	 * Constructs a new instance of this class with its type, name, and
	 * certificates set to the arguments by definition, actions are ignored
	 * 
	 * @param type
	 *            class of permission object
	 * @param name
	 *            identifies the permission that could not be resolved
	 * @param actions
	 * @param certs
	 */
    public UnresolvedPermission(String type, String name, String actions,
                                Certificate[] certs) {
        super(type);
        checkType(type);
        this.type = type;
        this.name = name;
        this.actions = actions;
        if (certs != null && certs.length != 0) {
            //TODO filter non-signer certificates ???
            List tmp = new ArrayList();
            for (int i = 0; i < certs.length; i++) {
                if (certs[i] != null) {
                    tmp.add(certs[i]);
                }
            }
            if (tmp.size() != 0) {
                targetCerts = (Certificate[])tmp.toArray(
                                new Certificate[tmp.size()]);
            }
        }
        hash = 0;
    }

    // Check type parameter
    private final void checkType(String type) {
        if (type == null) {
            throw new NullPointerException(Messages.getString("security.2F")); //$NON-NLS-1$
        }

        // type is the class name of the Permission class.
        // Empty string is inappropriate for class name.
        // But this check is commented out for compatibility with RI.
        // see JIRA issue HARMONY-733
        // if (type.length() == 0) {
        //     throw new IllegalArgumentException("type cannot be empty");
        // }
    }

	/**
	 * Compares the argument to the receiver, and answers true if they represent
	 * the <em>same</em> object using a class specific comparison. In this
	 * case, the receiver and the object must have the same class, permission
	 * name, actions, and certificates
	 * 
	 * @param obj
	 *            the object to compare with this object
	 * @return <code>true</code> if the object is the same as this object,
	 *         <code>false</code> otherwise.
	 * 
	 * @see #hashCode
	 */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof UnresolvedPermission) {
            UnresolvedPermission that = (UnresolvedPermission)obj;
            if (getName().equals(that.getName())
                && (name == null ? that.name == null 
                    : name.equals(that.name))
                && (actions == null ? that.actions == null
                    : actions.equals(that.actions))
                && (PolicyUtils.matchSubset(targetCerts, that.targetCerts) 
                    && PolicyUtils.matchSubset(that.targetCerts, targetCerts))) {
                return true;
            }
        }
        return false;
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
        if (hash == 0) {
            hash = getName().hashCode();
            if (name != null) {
                hash ^= name.hashCode();
            }
            if (actions != null) {
                hash ^= actions.hashCode();
            }
        }
        return hash;
    }

	/**
	 * Answers the actions associated with the receiver. Since
	 * UnresolvedPermission objects have no actions, answer the empty string.
	 * 
	 * @return the actions associated with the receiver.
	 */
    public String getActions() {
        return ""; //$NON-NLS-1$
    }

    /** 
     * @com.intel.drl.spec_ref 
     */
    public String getUnresolvedName() {
        return name;
    }

    /** 
     * @com.intel.drl.spec_ref 
     */
    public String getUnresolvedActions() {
        return actions;
    }

    /** 
     * @com.intel.drl.spec_ref 
     */
    public String getUnresolvedType() {
        return super.getName();
    }

    /** 
     * @com.intel.drl.spec_ref 
     */
    public Certificate[] getUnresolvedCerts() {
        if (targetCerts != null) {
            Certificate[] certs = new Certificate[targetCerts.length];
            System.arraycopy(targetCerts, 0, certs, 0, certs.length);
            return certs;
        }
        return null;
    }

	/**
	 * Indicates whether the argument permission is implied by the
	 * receiver.  UnresolvedPermission objects imply nothing
	 * because nothing is known about them yet.
	 * 
         * Before actual implication checking, this method tries to
         * resolve UnresolvedPermissions (if any) against the passed
         * instance. Successfully resolved permissions (if any) are
         * taken into account during further processing.
         *
	 * @param permission
	 *            the permission to check
	 * @return always replies false
	 */
    public boolean implies(Permission permission) {
        return false;
    }

	/**
	 * Answers a string containing a concise, human-readable description of the
	 * receiver.
	 * 
	 * @return a printable representation for the receiver.
	 */
    public String toString() {
        return "(unresolved " + type + " " + name + " " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            + actions + ")"; //$NON-NLS-1$
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
        return new UnresolvedPermissionCollection();
    }

    /**
     * Tries to resolve this permission into the specified class. It is assumed
     * that the class has a proper name (as returned by <code>getName()</code>
     * of this unresolved permission), so no check is performed to verify this.
     * However, the class must have all required certificates (as per
     * <code>getUnresolvedCerts()</code>) among the passed collection of
     * signers. If it does, a zero, one, and/or two-argument constructor is
     * tried to instantiate a new permission, which is then returned. <br>
     * If an appropriate constructor is not available or the class is
     * improperly signed, <code>null</code> is returned.
     * 
     * @param targetType - a target class instance, must not be
     *        <code>null</code>
     * @param signers - actual signers of the targetType
     * @return resolved permission or null
     */
    Permission resolve(Class targetType) {
        // check signers at first
        if (PolicyUtils.matchSubset(targetCerts, targetType.getSigners())) {
            try {
                return PolicyUtils.instantiatePermission(targetType,
                                                         name,
                                                         actions);
            } catch (Exception ignore) {
                //TODO log warning?
            }
        }
        return null;
    }

    /**
     * @com.intel.drl.spec_ref
     * 
     * Outputs <code>type</code>,<code>name</code>,<code>actions</code>
     * fields via default mechanism; next manually writes certificates in the
     * following format: <br>
     *
     * <ol>
     * <li> int : number of certs or zero </li>
     * <li> each cert in the following format
     *     <ol>
     *     <li> String : certificate type </li>
     *     <li> int : length in bytes of certificate </li>
     *     <li> byte[] : certificate encoding </li>
     *     </ol>
     * </li>
     * </ol>
     *
     *  @see  <a href="http://java.sun.com/j2se/1.5.0/docs/api/serialized-form.html#java.security.UnresolvedPermission">Java Spec</a>
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        if (targetCerts == null) {
            out.writeInt(0);
        } else {
            out.writeInt(targetCerts.length);
            for (int i = 0; i < targetCerts.length; i++) {
                try {
                    byte[] enc = targetCerts[i].getEncoded();
                    out.writeUTF(targetCerts[i].getType());
                    out.writeInt(enc.length);
                    out.write(enc);
                } catch (CertificateEncodingException cee) {
                    throw ((IOException)new NotSerializableException(
                        Messages.getString("security.30",  //$NON-NLS-1$
                        targetCerts[i])).initCause(cee));
                }
            }
        }
    }

    /** 
     * @com.intel.drl.spec_ref
     * 
     * Reads the object from stream and checks target type for validity. 
     */
    private void readObject(ObjectInputStream in) throws IOException,
        ClassNotFoundException {
        in.defaultReadObject();        
        checkType(getUnresolvedType());      
        int certNumber = in.readInt();
        if (certNumber != 0) {
            targetCerts = new Certificate[certNumber];
            for (int i = 0; i < certNumber; i++) {
                try {
                    String type = in.readUTF();
                    int length = in.readInt();
                    byte[] enc = new byte[length];
                    in.readFully(enc, 0, length);
                    targetCerts[i] = CertificateFactory.getInstance(type)
                        .generateCertificate(new ByteArrayInputStream(enc));
                } catch (CertificateException cee) {
                    throw ((IOException)new IOException(
                        Messages.getString("security.32")).initCause(cee)); //$NON-NLS-1$
                }
            }
        }
    }
}
