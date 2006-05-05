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
import java.io.Serializable;
import java.util.Arrays;

import javax.crypto.SecretKey;
import javax.security.auth.DestroyFailedException;
import javax.security.auth.Destroyable;

import org.apache.harmony.security.utils.Array;

/**
 * @com.intel.drl.spec_ref
 * 
 */
public class KerberosKey implements SecretKey, Destroyable {

    private static final long serialVersionUID = -4625402278148246993L;
    
    //principal    
    private KerberosPrincipal principal;

    //key version number
    private int versionNum;
    
    //raw bytes for the sicret key
    private KeyImpl key;
    
    // indicates the ticket state
    private transient boolean destroyed;
    
    
    
    /**
     * @com.intel.drl.spec_ref
     */
    public KerberosKey(KerberosPrincipal principal, byte[] keyBytes,
                       int keyType, int versionNumber) {

        if (keyBytes == null) {
            throw new NullPointerException("key is null");
        }

        this.principal = principal;
        this.versionNum = versionNumber;
        
        this.key = new KeyImpl(keyBytes, keyType);
        
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public KerberosKey(KerberosPrincipal principal, char[] password,
                       String algorithm) {
        
        this.principal = principal;

        this.key = new KeyImpl(principal, password, algorithm);
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public final KerberosPrincipal getPrincipal() {
        checkState();
        return principal;
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public final String getAlgorithm() {
        checkState();
        return key.getAlgorithm();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final String getFormat() {
        checkState();
        return key.getFormat();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final int getKeyType() {
        checkState();
        return key.getKeyType();
    }  
    /**
     * @com.intel.drl.spec_ref
     */
    public final byte[] getEncoded() {
        checkState();
        return key.getEncoded();
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public final int getVersionNumber()  {
        checkState();
        return versionNum;
    } 

    /**
     * @com.intel.drl.spec_ref
     */
    public void destroy() throws DestroyFailedException {
        if (!destroyed) {
            this.principal = null;
            key.destroy();
            this.destroyed = true;
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean isDestroyed() {
        return destroyed;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String toString() {
        checkState();
        StringBuffer sb = new StringBuffer();
        sb.append("KerberosPrincipal ").append(principal.getName()).append("\n");
        sb.append("KeyVersion ").append(versionNum).append("\n");
        sb.append(key.toString());
        return sb.toString();
    } 
    
    // if a key is destroyed then IllegalStateException must be thrown 
    private void checkState() {
        if (destroyed) {
            throw new IllegalStateException ("The key is destroyed");
        }
    }
}

/**
 * This class encapsulates a Kerberos encryption key.
 * 
 */
class KeyImpl implements SecretKey, Destroyable, Serializable {

    private transient byte[] keyBytes;

    private transient int keyType;
    
    //  indicates the ticket state
    private transient boolean destroyed;
    
    /**
     * creates a secret key from a given raw bytes
     * 
     * @param keyBytes
     * @param keyType
     */
    public KeyImpl(byte[] keyBytes, int keyType) {
        this.keyBytes = new byte[keyBytes.length];
        System.arraycopy(keyBytes , 0, this.keyBytes, 0, this.keyBytes.length); 
        this.keyType = keyType;
    }
    /**
     * creates a secret key from a given password
     * 
     * @param principal
     * @param password
     * @param algorithm
     */
    public KeyImpl(KerberosPrincipal principal, char[] password, String algorithm) {

        if (principal == null || password == null) {
            throw new NullPointerException();
        }

        if (algorithm != null && "DES".compareTo(algorithm) != 0) {
            throw new IllegalArgumentException("Unsupported algorithm");
        }

        keyType = 3; // DES algorithm

        //FIXME: implement grenerating key from password 
    }
    
    /**
     * Method is described in 
     * <code>getAlgorithm</code> in interface <code>Key</code>
     */
    public final String getAlgorithm() {
        checkState();
        if (keyType == 0) {
            return "NULL";
        }
        return "DES";
    }
    
    /**
     * Method is described in
     * <code>getFormat</code> in interface <code>Key</code>
     */
    public final String getFormat() {
        checkState();
        return "RAW";
    }
   
    /**
     * Method is described in
     * <code>getEncoded</code> in interface <code>Key</code>
     */
    public final byte[] getEncoded() {
        checkState();
        byte[] tmp = new byte[keyBytes.length];
        System.arraycopy(keyBytes, 0, tmp, 0, tmp.length);
        return tmp;
    }

    /**
     * Returns the key type for this key
     */
    public final int getKeyType() {
        checkState();
        return keyType;
    }

    /**
     * Destroys this key
     */
    public void destroy() throws DestroyFailedException {
        if (!destroyed) {
            Arrays.fill(keyBytes, (byte) 0); 
            destroyed = true;
        }
        
    }
    /**
     * Determines if this key has been destroyed 
     */
   public boolean isDestroyed() {
        return destroyed;
    }

   /**
    * A string representation of this key
    */
   public String toString() {
       String s_key = null;
       StringBuffer sb = new StringBuffer();
       
       if (keyBytes.length == 0) {
           s_key = "Empty Key";
       } else {
           s_key = Array.toString(keyBytes," ");
       }
       sb.append("EncryptionKey: ").append("KeyType = ").append(keyType);
       sb.append("KeyBytes (Hex dump) = ").append(s_key);
       return sb.toString();
   }
   
   /**
    * if a key is destroyed then IllegalStateException should be thrown
    */  
   private void checkState() {
       if (destroyed) {
           throw new IllegalStateException ("The key is destroyed");
       }
   }

   // TODO: read a object from a stream
   private void readObject(ObjectInputStream s) throws IOException,
       ClassNotFoundException {
       s.defaultReadObject();
   }

   // TODO: write a object to a stream
   private void writeObject(ObjectOutputStream s) throws IOException {
       s.defaultWriteObject();
   }

}