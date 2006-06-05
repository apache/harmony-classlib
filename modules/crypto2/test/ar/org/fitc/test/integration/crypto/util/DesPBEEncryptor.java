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

import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NullCipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * Class that encapsulates the encryption of the input and output streams
 *
 * @author Osvaldo C. Demo
 */
public class DesPBEEncryptor {

    byte[] salt = { (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
            (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03 };

    String password;

    int iterationCount = 19;

    public DesPBEEncryptor(String passPhrase) {
        password = passPhrase;
    }

    byte[] buf = new byte[1024];

    public void encrypt(InputStream in, OutputStream out) {
        try {
            out = new CipherOutputStream(out, getCipher(Cipher.ENCRYPT_MODE));
            int numRead = 0;
            while ((numRead = in.read(buf)) >= 0) {
                out.write(buf, 0, numRead);
            }
            out.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public void decrypt(InputStream in, OutputStream out) {
        try {
            in = new CipherInputStream(in, getCipher(Cipher.DECRYPT_MODE));
            int numRead = 0;
            while ((numRead = in.read(buf)) >= 0) {
                out.write(buf, 0, numRead);
            }
            out.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method prepares a cipher for its use
     *
     * @param mode
     *            Cipher.ENCRYPT_MODE or Cipher.DECRYPT_MODE
     * @return an initialized instance of cipher ready for use
     */
    private Cipher getCipher(int mode) {
        Cipher PBECipher = new NullCipher();
        try {
            PBECipher = Cipher.getInstance("PBEWithMD5AndDES");
            PBECipher.init(mode, SecretKeyFactory.getInstance(
                    "PBEWithMD5AndDES")
                    .generateSecret(new PBEKeySpec(password.toCharArray())),
                    new PBEParameterSpec(salt, iterationCount));
        } catch (Throwable t) {
            System.err.println("Cipher was not generated correctly. Null Cipher will be used instead...");
            t.printStackTrace();
        }
        return PBECipher;
    }

}
