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
* @author Vladimir N. Molotkov
* @version $Revision$
*/

package java.security;

//import java.io.IOException;
//import java.io.NotSerializableException;
//import java.io.ObjectInputStream;
//import java.io.ObjectStreamException;
import java.io.Serializable;
//import java.security.spec.X509EncodedKeySpec;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.security.spec.InvalidKeySpecException;
//
//import javax.crypto.spec.SecretKeySpec;

/**
 * @com.intel.drl.spec_ref
 * 
 */
public class KeyRep implements Serializable {
//    /**
//     * @com.intel.drl.spec_ref
//     */
//    private static final long serialVersionUID = -4757683898830641853L;
//    // Key type
//    private final Type type;
//    // Key algorithm name
//    private final String algorithm;
//    // Key encoding format
//    private final String format;
//    // Key encoding
//    private byte[] encoded;
//
//    /**
//     * @com.intel.drl.spec_ref
//     */
//    public KeyRep(Type type,
//            String algorithm, String format, byte[] encoded) {
//        this.type = type;
//        this.algorithm = algorithm;
//        this.format = format;
//        this.encoded = encoded;
//        if(this.type == null) {
//            throw new NullPointerException("the type parameter is null");
//        }
//        if(this.algorithm == null) {
//            throw new NullPointerException("the algorithm parameter is null");
//        }
//        if(this.format == null) {
//            throw new NullPointerException("the format parameter is null");
//        }
//        if(this.encoded == null) {
//            throw new NullPointerException("the encoded parameter is null");
//        }
//    }
//
//    /**
//     * @com.intel.drl.spec_ref
//     */
//    protected Object readResolve() throws ObjectStreamException {
//        switch (type) {
//        case SECRET:
//            if ("RAW".equals(format)) {
//                try {
//                    return new SecretKeySpec(encoded, algorithm);
//                } catch (IllegalArgumentException e) {
//                    throw new NotSerializableException(
//                            "Could not create SecretKeySpec: " + e);
//                }
//            }
//            throw new NotSerializableException(
//                "unrecognized type/format combination: " +
//                type + "/" + format);
//        case PUBLIC:
//            if ("X.509".equals(format)) {
//                try {
//                    KeyFactory kf = KeyFactory.getInstance(algorithm);
//                    return kf.generatePublic(new X509EncodedKeySpec(encoded));
//                } catch (NoSuchAlgorithmException e) {
//                    throw new NotSerializableException(
//                            "Could not resolute key: " + e);
//                }
//                catch (InvalidKeySpecException e) {
//                    throw new NotSerializableException(
//                            "Could not resolute key: " + e);
//                }
//            }
//            throw new NotSerializableException(
//                "unrecognized type/format combination: " +
//                type + "/" + format);
//        case PRIVATE:
//            if ("PKCS#8".equals(format)) {
//                try {
//                    KeyFactory kf = KeyFactory.getInstance(algorithm);
//                    return kf.generatePrivate(new PKCS8EncodedKeySpec(encoded));
//                } catch (NoSuchAlgorithmException e) {
//                    throw new NotSerializableException(
//                            "Could not resolute key: " + e);
//                }
//                catch (InvalidKeySpecException e) {
//                    throw new NotSerializableException(
//                            "Could not resolute key: " + e);
//                }
//            }
//            throw new NotSerializableException(
//                "unrecognized type/format combination: " +
//                type + "/" + format);
//        }
//        throw new NotSerializableException("unrecognized key type: " + type);
//    }
//
//    // Makes deffensive copy of key encoding
//    private void readObject(ObjectInputStream is)
//        throws IOException, ClassNotFoundException {
//        is.defaultReadObject();
//        new_encoded = new byte[encoded.length];
//        System.arraycopy(encoded, 0, new_encoded, 0, new_encoded.length);
//        encoded = new_encoded;    
//    }
//
//    /**
//     * Supported key types
//     */
//    public static enum Type {
//        SECRET,
//        PUBLIC,
//        PRIVATE;
//    }
}
