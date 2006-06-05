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
import javax.crypto.SecretKey;
import javax.crypto.SealedObject;
import javax.crypto.KeyGenerator;

final class TestSealedObject 
extends Test 
{
    private static final String name = "SealedObject";
    private Cipher c, d;
    private SecretKey sk;
    private final String s = "ThisIsASecretSecretMessag";
    
    private String algo;
    private int KEY_SIZE;

    
    protected TestSealedObject (String algo, int KEY_SIZE) 
    {
        super(name);
        this.algo = algo;
        this.KEY_SIZE = KEY_SIZE;
    }


    protected TestSealedObject () 
    {
        super(name);
        this.algo = "DES";
        this.KEY_SIZE = 56;
    }


    protected void doIt() 
    throws Exception 
    {
        init();
        beginTest("Decrypt with the cipher");
        passIf(test1(c,d,s));
        beginTest("Decrypting with the secretkey");
        passIf(test2(c,sk,s));
    
        return;
    }


    private void init () 
    throws Exception 
    {
        KeyGenerator kg = KeyGenerator.getInstance(algo,provider);
        kg.init(KEY_SIZE);
        sk = kg.generateKey();
    
        algo = algo + "/ECB/NoPadding";
        c = Cipher.getInstance(algo,provider);
        d = Cipher.getInstance(algo,provider);
    
        c.init(Cipher.ENCRYPT_MODE, sk);
        d.init(Cipher.DECRYPT_MODE, sk);
        return;
    }

    private boolean test1(Cipher c, Cipher d, String s) 
    throws Exception 
    {
        start = System.nanoTime();
        String rs = "";
        SealedObject so = new SealedObject(s, c);
        rs = (String)so.getObject(d);
        finish = System.nanoTime();
        return rs.equals(s);
    }


    private boolean test2(Cipher c, SecretKey sKey, String s)
    throws Exception 
    {
        start = System.nanoTime();
        String rs = "";
        SealedObject so = new SealedObject(s, c);
        rs = (String)so.getObject(sKey);
        finish = System.nanoTime();
        return rs.equals(s);
    }
}
