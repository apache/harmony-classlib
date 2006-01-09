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

import com.openintel.fortress.drl.security.PolicyUtils;

/**
 * @com.intel.drl.spec_ref 
 * Technically, the resolution of UnresolvedPermissions
 * and substitution by actual permissions takes place in
 * <code>implies()</code> method of a
 * <code>Permissions</code> collection, right before
 * actual checking.
 * 
 */
public final class UnresolvedPermission extends Permission 
    implements Serializable {

    /** 
     * @com.intel.drl.spec_ref 
     */
    private static final long serialVersionUID = -4821973115467008846L;

    private static final ObjectStreamField serialPersistentFields[] = {
        new ObjectStreamField("type", String.class),
        new ObjectStreamField("name", String.class),
        new ObjectStreamField("actions", String.class), };

    // Target name
    private transient String targetName;

    //Target actions
    private transient String targetActions;

    // The signer certificates 
    private transient Certificate[] targetCerts;

    // Cached hash value
    private transient int hash;

    /** 
     * @com.intel.drl.spec_ref 
     */
    public UnresolvedPermission(String type, String name, String actions,
                                Certificate[] certs) {
        super(type);
        checkType(type);
        targetName = name;
        targetActions = actions;
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
            throw new NullPointerException("type cannot be null");
        }    
        if (type.length() == 0) {
            throw new IllegalArgumentException("type cannot be empty");
        }
    }

    /** 
     * @com.intel.drl.spec_ref 
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof UnresolvedPermission) {
            UnresolvedPermission that = (UnresolvedPermission)obj;
            if (getName().equals(that.getName())
                && (targetName == null ? that.targetName == null 
                    : targetName.equals(that.targetName))
                && (targetActions == null ? that.targetActions == null
                    : targetActions.equals(that.targetActions))
                && (PolicyUtils.matchSubset(targetCerts, that.targetCerts) 
                    && PolicyUtils.matchSubset(that.targetCerts, targetCerts))) {
                return true;
            }
        }
        return false;
    }

    /** 
     * @com.intel.drl.spec_ref 
     */
    public int hashCode() {
        if (hash == 0) {
            hash = getName().hashCode();
            if (targetName != null) {
                hash ^= targetName.hashCode();
            }
            if (targetActions != null) {
                hash ^= targetActions.hashCode();
            }
        }
        return hash;
    }

    /** 
     * @com.intel.drl.spec_ref 
     */
    public String getActions() {
        return "";
    }

    /** 
     * @com.intel.drl.spec_ref 
     */
    public String getUnresolvedName() {
        return targetName;
    }

    /** 
     * @com.intel.drl.spec_ref 
     */
    public String getUnresolvedActions() {
        return targetActions;
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
     * @com.intel.drl.spec_ref 
     * The enclosed target permission would be resolved
     * and consulted for implication if this
     * UnresolvedPermission is an element of a
     * <code>Permissions</code> collection and the
     * collection's <code>implies()</code> method is
     * called.
     */
    public boolean implies(Permission permission) {
        return false;
    }

    /** 
     * @com.intel.drl.spec_ref 
     */
    public String toString() {
        return "(unresolved " + getName() + " " + targetName + " "
            + targetActions + ")";
    }

    /** 
     * @com.intel.drl.spec_ref 
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
     * improperrly signed, <code>null</code> is returned.
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
                                                         targetName,
                                                         targetActions);
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
     * an initial <code>int</code> indicating the number of certificates to
     * follow (a value of "zero" denotes that there are no certificates). Each
     * certificate is written out starting with a <code>String</code> denoting
     * the certificate type, followed by an <code>int</code> specifying the
     * length of the certificate encoding, followed by the certificate encoding
     * itself which is written out as an array of bytes.
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("type", getUnresolvedType());
        fields.put("name", getUnresolvedName());
        fields.put("actions", getUnresolvedActions());
        out.writeFields();
        if (targetCerts == null) {
            out.write(0);
        } else {
            out.write(targetCerts.length);
            for (int i = 0; i < targetCerts.length; i++) {
                try {
                    byte[] enc = targetCerts[i].getEncoded();
                    out.writeUTF(targetCerts[i].getType());
                    out.write(enc.length);
                    out.write(enc);
                } catch (CertificateEncodingException cee) {
                    throw ((IOException)new NotSerializableException(
                        "Cannot encode certificate " 
                        + targetCerts[i]).initCause(cee));
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
        checkType(getUnresolvedType());
        ObjectInputStream.GetField fields = in.readFields();
        if (!getUnresolvedType().equals((String)fields.get("type", null))) {
            throw new InvalidObjectException("target type field is corrupted");
        }
        targetName = (String)fields.get("name", null);
        targetActions = (String)fields.get("actions", null);
        int certNumber = in.read();
        if (certNumber != 0) {
            targetCerts = new Certificate[certNumber];
            for (int i = 0; i < certNumber; i++) {
                try {
                    String type = in.readUTF();
                    int length = in.read();
                    byte[] enc = new byte[length];
                    in.readFully(enc, 0, length);
                    targetCerts[i] = CertificateFactory.getInstance(type)
                        .generateCertificate(new ByteArrayInputStream(enc));
                } catch (CertificateException cee) {
                    throw ((IOException)new IOException(
                        "Error decoding certificate").initCause(cee));
                }
            }
        }
    }
}
