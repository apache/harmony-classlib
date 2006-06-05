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


import java.security.SecureRandom;
import java.security.AlgorithmParameters;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;

final class TestAlgorithmParameters extends Test {

    private static final String name = "AlgorithmParameters";

    private Cipher c;

    private SecretKey sk;

    private String algo;

    private int KEY_SIZE;

    protected TestAlgorithmParameters(String algo, int KEY_SIZE) {
        super(name);
        this.algo = algo;
        this.KEY_SIZE = KEY_SIZE;
    }

    protected TestAlgorithmParameters() {
        super(name);
        this.algo = "DES";
        this.KEY_SIZE = 56;
    }

    protected void doIt() throws Exception {
        init();

        beginTest("test with parameters only AlgorithmParameters");

        passIf(test1());

        beginTest("test with parameters from Cipher");

        passIf(test2(c) && test3(c));

        return;
    }

    private void init() throws Exception {
        // Generate key
        KeyGenerator kg = KeyGenerator.getInstance(algo, provider);
        kg.init(KEY_SIZE);
        sk = kg.generateKey();

        c = Cipher.getInstance(algo + "/CBC/NoPadding", provider);
        c.init(Cipher.ENCRYPT_MODE, sk);

        return;
    }

    private boolean test1() throws Exception {
        start = System.nanoTime();
        byte[] iv = new byte[8];
        new SecureRandom().nextBytes(iv);

        AlgorithmParameters ap1 = AlgorithmParameters.getInstance("DES",
                provider);
        ap1.init(new IvParameterSpec(iv));
        String a = ap1.getAlgorithm();
        String ivs = ap1.toString();
        String p = ap1.getProvider().getName();
        byte[] enc = ap1.getEncoded();

        AlgorithmParameters ap2 = AlgorithmParameters.getInstance(a, p);
        ap2.init(enc);
        finish = System.nanoTime();
        return (a.equals(ap2.getAlgorithm()) && ivs.equals(ap2.toString()) && p
                .equals(ap2.getProvider().getName()));
    }

    private boolean test2(Cipher c) throws Exception {
        start = System.nanoTime();
        AlgorithmParameters ap1 = c.getParameters();
        String a = ap1.getAlgorithm();
        String ivs = ap1.toString();
        String p = ap1.getProvider().getName();
        byte[] enc = ap1.getEncoded();

        AlgorithmParameters ap2 = AlgorithmParameters.getInstance(algo,
                provider);
        ap2.init(enc);

        return (a.equals(ap2.getAlgorithm()) && ivs.equals(ap2.toString()) && p
                .equals(ap2.getProvider().getName()));
    }

    private boolean test3(Cipher c) throws Exception {
        byte[] iv = c.getIV();
        AlgorithmParameters ap1 = c.getParameters();
        String a = ap1.getAlgorithm();
        String ivs = ap1.toString();
        String p = ap1.getProvider().getName();

        AlgorithmParameters ap2 = AlgorithmParameters.getInstance(algo,
                provider);
        ap2.init(new IvParameterSpec(iv));
        finish = System.nanoTime();
        return (a.equals(ap2.getAlgorithm()) && ivs.equals(ap2.toString()) && p
                .equals(ap2.getProvider().getName()));
    }
}
