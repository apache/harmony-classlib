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

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

final class TestModeAndPadding extends Test {
    private static final String name = "ModeAndPadding";

    private final String s1 = "ImAliveSecret!",
            s2 = "Secret";

    private Cipher c, d;

    private SecretKey sk;

    private String algo;

    private int KEY_SIZE;

    @SuppressWarnings("unused")
    private String providerStr;

    protected TestModeAndPadding(String algo, String prov, int KEY_SIZE) {
        super(name);
        this.algo = algo;
        this.providerStr = prov;
        this.KEY_SIZE = KEY_SIZE;
    }

    protected TestModeAndPadding() {
        super(name);
        this.algo = "DES";
        this.KEY_SIZE = 56;
    }

    protected void doIt() throws Exception {
        init();

        // ECB
        start = System.nanoTime();
        beginTest("ECB mode with no padding");
        changeMode("ECB", "NoPadding");
        finish = System.nanoTime();
        passIf(test1(c, d, s1) && test2(c, d, s1));
        beginTest("ECB mode with PKCS5 padding");
        start = System.nanoTime();
        changeMode("ECB", "PKCS5Padding");
        finish = System.nanoTime();
        passIf(test1(c, d, s2) && test2(c, d, s2));

        // CBC
        beginTest("CBC mode with no padding");
        start = System.nanoTime();
        changeMode("CBC", "NoPadding");
        finish = System.nanoTime();
        passIf(test1(c, d, s1) && test2(c, d, s1));
        beginTest("CBC mode with PKCS5 padding");
        start = System.nanoTime();
        changeMode("CBC", "PKCS5Padding");
        finish = System.nanoTime();
        passIf(test1(c, d, s2) && test2(c, d, s2));

        // OFB
        beginTest("OFB mode with no padding");
        start = System.nanoTime();
        changeMode("OFB", "NoPadding");
        passIf(test1(c, d, s2) && test2(c, d, s2));
        beginTest("OFB mode with PKCS5 padding");
        start = System.nanoTime();
        changeMode("OFB", "PKCS5Padding");
        passIf(test1(c, d, s2) && test2(c, d, s2));
        // CFB
        beginTest("CFB mode with no padding");
        start = System.nanoTime();
        changeMode("CFB56", "NoPadding");
        passIf(test1(c, d, s2) && test2(c, d, s2));
        beginTest("CFB mode with PKCS5 padding");
        start = System.nanoTime();
        changeMode("CFB", "PKCS5Padding");
        passIf(test1(c, d, s2) && test2(c, d, s2));
        return;
    }

    private void init() throws Exception {
        // Generate key
        KeyGenerator kg = KeyGenerator.getInstance(algo, provider);
        kg.init(KEY_SIZE);
        sk = kg.generateKey();
        return;
    }

    private void changeMode(String mode, String padding) throws Exception {
        // init the ciphers
        String alg = algo + "/" + mode + "/" + padding;
        c = Cipher.getInstance(alg, provider);
        d = Cipher.getInstance(alg, provider);

        c.init(Cipher.ENCRYPT_MODE, sk);
        if (mode.equals("ECB"))
            d.init(Cipher.DECRYPT_MODE, sk);
        else
            d.init(Cipher.DECRYPT_MODE, sk, new IvParameterSpec(c.getIV()));

        return;
    }

    private boolean test1(Cipher c, Cipher d, String s) throws Exception {
        // Test the update + doFinal
        byte[] e = c.update(s.getBytes());
        byte[] f = c.doFinal();
        byte[] g = new byte[e.length + f.length];
        System.arraycopy(e, 0, g, 0, e.length);
        System.arraycopy(f, 0, g, e.length, f.length);

        return s.equals(new String(d.doFinal(g)));
    }

    private boolean test2(Cipher c, Cipher d, String s) throws Exception {
        // Test with doFinal only
        byte[] e = c.doFinal(s.getBytes());
        finish = System.nanoTime();
        return s.equals(new String(d.doFinal(e)));
    }
}
