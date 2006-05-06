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

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.DestroyFailedException;
import javax.security.auth.Destroyable;

import org.apache.harmony.security.utils.Array;

/**
 * @com.intel.drl.spec_ref
 * 
 * @see http://www.ietf.org/rfc/rfc3961.txt
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
        return key.getAlgorithm();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final String getFormat() {
        return key.getFormat();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final int getKeyType() {
        return key.getKeyType();
    }  
    /**
     * @com.intel.drl.spec_ref
     */
    public final byte[] getEncoded() {
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

    private static final long serialVersionUID = -7889313790214321193L;
    
    private transient byte[] keyBytes;

    private transient int keyType;
    
    //  indicates the ticket state
    private transient boolean destroyed;

    // Pre-calculated parity values 
    // TODO the alternative for boolean table - any acceptable algorithm?
    private final static boolean[] PARITY = new boolean[] { false, true, true,
            false, true, false, false, true, true, false, false, true, false,
            true, true, false, true, false, false, true, false, true, true,
            false, false, true, true, false, true, false, false, true, true,
            false, false, true, false, true, true, false, false, true, true,
            false, true, false, false, true, false, true, true, false, true,
            false, false, true, true, false, false, true, false, true, true,
            false, true, false, false, true, false, true, true, false, false,
            true, true, false, true, false, false, true, false, true, true,
            false, true, false, false, true, true, false, false, true, false,
            true, true, false, false, true, true, false, true, false, false,
            true, true, false, false, true, false, true, true, false, true,
            false, false, true, false, true, true, false, false, true, true,
            false, true, false, false, true, true, false, false, true, false,
            true, true, false, false, true, true, false, true, false, false,
            true, false, true, true, false, true, false, false, true, true,
            false, false, true, false, true, true, false, false, true, true,
            false, true, false, false, true, true, false, false, true, false,
            true, true, false, true, false, false, true, false, true, true,
            false, false, true, true, false, true, false, false, true, false,
            true, true, false, true, false, false, true, true, false, false,
            true, false, true, true, false, true, false, false, true, false,
            true, true, false, false, true, true, false, true, false, false,
            true, true, false, false, true, false, true, true, false, false,
            true, true, false, true, false, false, true, false, true, true,
            false, true, false, false, true, true, false, false, true, false,
            true, true, false };

    // Pre-calculated reversed values 
    // TODO any acceptable alternative algorithm instead of table?
    private static final byte[] REVERSE = new byte[] { 0, 64, 32, 96, 16, 80,
            48, 112, 8, 72, 40, 104, 24, 88, 56, 120, 4, 68, 36, 100, 20, 84,
            52, 116, 12, 76, 44, 108, 28, 92, 60, 124, 2, 66, 34, 98, 18, 82,
            50, 114, 10, 74, 42, 106, 26, 90, 58, 122, 6, 70, 38, 102, 22, 86,
            54, 118, 14, 78, 46, 110, 30, 94, 62, 126, 1, 65, 33, 97, 17, 81,
            49, 113, 9, 73, 41, 105, 25, 89, 57, 121, 5, 69, 37, 101, 21, 85,
            53, 117, 13, 77, 45, 109, 29, 93, 61, 125, 3, 67, 35, 99, 19, 83,
            51, 115, 11, 75, 43, 107, 27, 91, 59, 123, 7, 71, 39, 103, 23, 87,
            55, 119, 15, 79, 47, 111, 31, 95, 63, 127 };

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

        //
        // See http://www.ietf.org/rfc/rfc3961.txt for algorithm description
        //
        
        if (principal == null || password == null) {
            throw new NullPointerException();
        }

        if (algorithm != null && "DES".compareTo(algorithm) != 0) {
            throw new IllegalArgumentException("Unsupported algorithm");
        }

        keyType = 3; // DES algorithm
        keyBytes = new byte[8];
        
        String realm = principal.getRealm();
        String pname = principal.getName();

        StringBuffer buf = new StringBuffer();
        buf.append(password);
        buf.append(realm);
        buf.append(pname.substring(0, pname.length() - realm.length() - 1));

        byte[] tmp = buf.toString().getBytes();

        // pad with 0x00 to 8 byte boundary
        byte[] raw = new byte[tmp.length
                + ((tmp.length % 8) == 0 ? 0 : (8 - tmp.length % 8))];
        System.arraycopy(tmp, 0, raw, 0, tmp.length);

        long k1, k2 = 0;
        boolean isOdd = false;
        // for each 8-byte block in raw byte array
        for (int i = 0; i < raw.length; i = i + 8, isOdd = !isOdd) {

            k1 = 0;
            if (isOdd) {
                //reverse
                for (int j = 7; j > -1; j--) {
                    k1 = (k1 << 7) + REVERSE[raw[i + j] & 0x7F];
                }
            } else {
                for (int j = 0; j < 8; j++) {
                    k1 = (k1 << 7) + (raw[i + j] & 0x7F);
                }
            }
            k2 = k2 ^ k1;
        }
        
        // 56-bit long to byte array (8 bytes)
        for (int i = 7; i > -1; i--) {
            keyBytes[i] = (byte) k2;
            keyBytes[i] = (byte) (keyBytes[i] << 1);
            k2 = k2 >> 7;
        }
        keyCorrection(keyBytes);

        // calculate DES-CBC check sum
        try {
            Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");

            // use tmp key as IV
            IvParameterSpec IV = new IvParameterSpec(keyBytes);

            // do DES encryption 
            SecretKey secretKey = new SecretKeySpec(keyBytes, "DES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, IV);
            byte[] enc = cipher.doFinal(raw);

            // final last block is check sum
            System.arraycopy(enc, enc.length - 8, keyBytes, 0, 8);
            
            keyCorrection(keyBytes);

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to generate DES key from password.", e);
        }
    }

    private void keyCorrection(byte[] key) {
        
        // fix parity
        for (int i = 0; i < 8; i++) {
            if (!PARITY[key[i] & 0xFF]) {
                if ((key[i] & 0x01) == 0) {
                    key[i]++;
                } else {
                    key[i]--;
                }
            }
        }
        
        // TODO if is week do XOR
        //if(DESKeySpec.isWeak(keyBytes,0)){
        //}
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
       
       if(destroyed){
           throw new IOException("Key was destroyed");
       }
       s.defaultWriteObject();
   }

}