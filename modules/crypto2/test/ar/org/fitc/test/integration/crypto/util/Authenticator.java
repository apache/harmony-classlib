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
 * @author Hugo Beilis
 * @author Osvaldo Demo
 * @author Jorge Rafael
 * @version 1.0
 */

package ar.org.fitc.test.integration.crypto.util;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Authenticator {

    class K implements Key {

        static final long serialVersionUID = 2L;

        private final byte[] keyBytes;

        private final String alg;

        private final String format;

        public K(String string, byte[] salt) {
            this.alg = string;
            this.keyBytes = salt;
            this.format = "RAW";
        }

        public String getAlgorithm() {
            return alg;
        }

        public String getFormat() {
            return format;
        }

        public byte[] getEncoded() {
            return (byte[]) keyBytes.clone();
        }

    }

    @SuppressWarnings("unused")
    private byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,
            (byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };

    private Mac mac;

    private Key key;

    private byte[] hash;

    public Authenticator() {
        this( new byte[ ]{ (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,
            (byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 } );
        
    }

    public Authenticator(byte[] salt) {
        try {
            key = new SecretKeySpec(salt, "HmacMD5");
            mac = Mac.getInstance(key.getAlgorithm());
            mac.init(key);
            hash = new byte[mac.getMacLength()];
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
         
        } catch (InvalidKeyException e) {
            e.printStackTrace();
         
        }
    }

    /**
     * This method adds a hash at the begining of the data
     *
     * @param data an array of bytes with some data
     * @return the hash + the input data
     */
    public byte[] addAuthInfo(byte[] data) {
        byte[] temp = new byte[data.length + mac.getMacLength()];
        mac.reset();
        hash = mac.doFinal(data);
        for (int i = 0; i < temp.length; i++) {

            if (i < mac.getMacLength()) {
                temp[i] = hash[i];
            } else {
                temp[i] = data[i-mac.getMacLength()];
            }
        }
        return temp;
    }

    /**
     *
     * @return A String representation of the MAC algorithm used to compute the hash
     */
    public String getAlgorithm() {
        return key.getAlgorithm();
    }

    public byte[] getHash(byte[] data) {
        try {
            return mac.doFinal(data);
        } finally {
            mac.reset();
        }
    }

    public Key getKey() {
        return key;
    }

    /**
     * Checks if the hash is the same for the given data
     *
     * @param hash byte array representing the hash
     * @param data byte array representing the data
     * @return true if the hash are the same, false otherwise
     */

    public boolean isHashOk(byte[] hash,byte[] data) {
        byte[] hashtemp = new byte[mac.getMacLength()];
        hashtemp = mac.doFinal(data);
        mac.reset();
        boolean equal = true;
        for (int i=0;i< hashtemp.length;i++) {
            if (hash[i] != hashtemp[i]) {
                equal = false;
            }
        }
        return equal;
    }

    public int getHashLength() {
        return mac.getMacLength();
    }

}
