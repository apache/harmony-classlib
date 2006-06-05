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

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;

final class TestRSA_ECB_PKCS1 extends Test {

    private static final String name = "RSA_ECB_PKCS1";
    private Cipher c, d;
    private final String s = "ThisIsASecretSecretMessageOfNull";
    
    private String algo;
    private int KEY_SIZE;
  
    protected TestRSA_ECB_PKCS1 (String algo, int KEY_SIZE) 
    {
        super(name);
        this.algo = algo;
        this.KEY_SIZE = KEY_SIZE;
    }
  
    protected TestRSA_ECB_PKCS1 () 
    {
        super(name);
        this.algo = "RSA";
        this.KEY_SIZE = 1024;
    }

    protected void doIt() 
    throws Exception 
    {
        beginTest("ECB with PKCS1");
        init();
        passIf(test(c,d,s));
          
        return;
    }

    private void init () 
    throws Exception 
    {
        start = System.nanoTime();
        this.algo = "RSA";
        this.KEY_SIZE = 1024;
    
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(algo);
        kpg.initialize(KEY_SIZE);
        KeyPair kp = kpg.generateKeyPair();
        RSAPrivateKey prk = (RSAPrivateKey)kp.getPrivate();
        RSAPublicKey puk = (RSAPublicKey)kp.getPublic();
        
        if (provider.equals("CryptixCrypto")) {
            algo = algo + "/ECB/PKCS#1";
        } else {
            algo = algo + "/ECB/PKCS1Padding";    
        }
        
        c = Cipher.getInstance(algo,provider);
        d = Cipher.getInstance(algo,provider);
    
        c.init(Cipher.ENCRYPT_MODE, puk);
        d.init(Cipher.DECRYPT_MODE, prk);
        return;
    }

    private boolean test(Cipher c, Cipher d, String s) 
    throws Exception 
    {
        byte [] b = s.getBytes(); 
        byte [] ca = d.doFinal(c.doFinal(b));
        finish = System.nanoTime();
        return (new String(ca).equals(s));
    }
}
