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

package ar.org.fitc.test.integration.algorithmtests;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

@SuppressWarnings("static-access")
abstract class CipherTest extends Test {

    private static SecureRandom random = new SecureRandom();

    private final String alg;

    private final boolean testStreamCipher;

    
    protected CipherTest(String name, String prov) {
        super(name);
        this.alg = name;
        this.provider = prov;
        this.testStreamCipher = false;
    }

    protected CipherTest(String name, String prov, boolean testStreamCipher) {
        super(name);
        this.alg = name;
        this.provider = prov;
        this.testStreamCipher = testStreamCipher;
    }

    protected void testExistence(String alg, String prov) throws Exception {
        beginTest("Algorithm available as " + alg);
        start = System.nanoTime();
        boolean res = true;
        try {
            @SuppressWarnings("unused") Cipher c = Cipher.getInstance(alg, prov);
        } catch (NoSuchAlgorithmException e) {
            res = false;
        }
        finish = System.nanoTime();
        passIf(res);
    }

    protected void testValuesCFB(String alg, String prov, String[][] testValues)
            throws Exception {
        beginTest("CFB Test Values (" + alg + ")");
        start = System.nanoTime();
        boolean res = true;
        try {
            for (int i = 0; i < testValues.length; i++) {

                byte[] key = Util.hexFromString(testValues[i][0]);
                byte[] iv = Util.hexFromString(testValues[i][1]);
                byte[] pt = Util.hexFromString(testValues[i][2]);
                byte[] ct = Util.hexFromString(testValues[i][3]);

                Key k = new K(alg.substring(0, alg.indexOf('/')), key);
                IvParameterSpec ivs = new IvParameterSpec(iv);
                Cipher c = Cipher.getInstance(alg, prov);

                c.init(Cipher.ENCRYPT_MODE, k, ivs);
                byte[] trial_ct = c.doFinal(pt);

                c.init(Cipher.DECRYPT_MODE, k, ivs);
                byte[] trial_pt = c.doFinal(ct);

                if (!Util.areEqual(ct, trial_ct)
                        || !Util.areEqual(pt, trial_pt)) {
                    res = false;
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
            res = false;
        }
        finish = System.nanoTime();
        passIf(res);
    }

    protected void testValuesECB(String alg, String prov, String[][] testValues)
            throws Exception {
        beginTest("ECB Test Values");
        start = System.nanoTime();
        boolean res = true;
        try {
            for (int i = 0; i < testValues.length; i++) {
                byte[] key = Util.hexFromString(testValues[i][0]);
                byte[] pt = Util.hexFromString(testValues[i][1]);
                byte[] ct = Util.hexFromString(testValues[i][2]);

                if (key.length == 8) {
                    String transform = alg + "/ECB/NoPadding";
                    if (testStreamCipher)
                        transform = alg;

                    Cipher c = Cipher.getInstance(transform, prov);
                    c.init(Cipher.ENCRYPT_MODE, new K(alg, key));
                    byte[] trial_ct = c.doFinal(pt);

                    c.init(Cipher.DECRYPT_MODE, new K(alg, key));
                    byte[] trial_pt = c.doFinal(ct);

                    if (!Util.areEqual(ct, trial_ct)
                            || !Util.areEqual(pt, trial_pt)) {
                        System.out.println(testValues[i][0]);
                        res = false;
                    }
                }
            }

        } catch (Throwable e) {
            e.printStackTrace();
            res = false;
        }
        finish = System.nanoTime();
        passIf(res);
    }

    protected void testKeyGenExistence(String alg, String prov)
            throws Exception {
        beginTest("KeyGenerator available");
        start = System.nanoTime();
        boolean res = true;
        try {
            @SuppressWarnings("unused") KeyGenerator keygen = KeyGenerator.getInstance(alg, prov);
        } catch (NoSuchAlgorithmException e) {
            res = false;
        }
        finish = System.nanoTime();
        passIf(res);
    }

    /**
     * Test whether the KeyGenerator for this algorithm works. Try and generate
     * a lot of keys and for each key test whether x = Dk(Ek(x)).
     */
    protected void testKeyGenWorks(String alg, String prov) throws Exception {
        boolean res = true;
        beginTest("KeyGenerator works");
        start = System.nanoTime();
        try {
            KeyGenerator keygen = KeyGenerator.getInstance(alg, prov);
            keygen.init(random);

            String transform = alg + "/ECB/NoPadding";
            if (testStreamCipher)
                transform = alg;

            Cipher c = Cipher.getInstance(transform, prov);
            int block_size = c.getBlockSize();
            byte[] pt = new byte[block_size];
            for (int i = 0; i < pt.length; i++)
                pt[i] = (byte) i;

            for (int i = 0; i < 1000; i++) {
                SecretKey k = keygen.generateKey();
                c.init(Cipher.ENCRYPT_MODE, k);
                byte[] tmp = c.doFinal(pt);
                c.init(Cipher.DECRYPT_MODE, k);
                tmp = c.doFinal(tmp);
                res = Util.areEqual(tmp, pt);
                if (!res)
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            res = false;
        }
        finish = System.nanoTime();
        passIf(res);
    }

    protected void testCipher(String prov, String[][] testValues)
            throws Exception {
        testExistence(alg, prov);
        testValuesECB(alg, prov, testValues);
        testKeyGenExistence(alg, prov);
        testKeyGenWorks(alg, prov);
    }

    private class K implements Key {
       
        private static final long serialVersionUID = 1L;

        private final byte[] keyBytes;

        private final String alg;

        K(String alg, byte[] keyBytes) {
            this.alg = alg;
            this.keyBytes = keyBytes;
        }

        public String getAlgorithm() {
            return alg;
        }

        public String getFormat() {
            return "RAW";
        }

        public byte[] getEncoded() {
            return (byte[]) keyBytes.clone();
        }
    }
}
