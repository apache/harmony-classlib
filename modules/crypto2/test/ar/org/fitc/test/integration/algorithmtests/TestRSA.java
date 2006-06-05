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
import java.security.SecureRandom;
import java.security.Signature;

import javax.crypto.Cipher;



class TestRSA
extends Test
{
    @SuppressWarnings("unused")
    private static SecureRandom random = new SecureRandom();

    
    TestRSA() 
    {
        super("RSA");
    }
    
    
    protected void doIt()
    {
        int PASSES=50;
        for(int i=0; i<PASSES; i++)
        {
            
            String pass = "(pass " + i + "/" + PASSES + ")";
            doItPrivate2(pass);
            doItPrivate1(pass);
            finish = System.nanoTime();
            
        }
    }
    
    
    private void doItPrivate1(String pass)
    {
        beginTest("RSA Signing " + pass);
        start = System.nanoTime();
        boolean res = false;
        try
        {
            KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
            gen.initialize(768);
            
            KeyPair pair = gen.generateKeyPair();
            
            Signature sig = Signature.getInstance("SHA1withRSA");
            
            sig.initSign(pair.getPrivate());
            
            sig.update((byte)0);
            
            byte[] signed = sig.sign();
            
            sig.initVerify(pair.getPublic());
            
            sig.update((byte)0);
            
            
            signed[0] = (byte)~signed[0];

            res = true;
        } 
        catch(Throwable t)
        {
            t.printStackTrace();
        }
        finish = System.nanoTime();
        passIf(res);
    }
    
    private void doItPrivate2(String pass)
    {
        beginTest("RSA Crypting " + pass);
        start = System.nanoTime();
        boolean res = false;
        try
        {
            KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
            gen.initialize(768);
            
            @SuppressWarnings("unused") KeyPair pair = gen.generateKeyPair();
            
            if (provider.equals("CryptixCrypto")) {
                @SuppressWarnings("unused") 
                Cipher c = Cipher.getInstance("RSA/ECB/PKCS#1",provider);
            } else {
                @SuppressWarnings("unused") 
                Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding",provider);
            }
            res = true;
        } 
        catch(Throwable t)
        {
            t.printStackTrace();
        }
        finish = System.nanoTime();
        passIf(res);
    }
}       
