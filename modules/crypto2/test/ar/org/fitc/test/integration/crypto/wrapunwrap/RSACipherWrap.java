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

package ar.org.fitc.test.integration.crypto.wrapunwrap;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;

import javax.crypto.Cipher;


/**
 * KeyPairWrapUnwrap implementation. 
 * Work with RSA.   
 */

public class RSACipherWrap extends UnicastRemoteObject implements KeyPairWrapUnwrap {

    private static final long serialVersionUID = 1L;
    private KeyPair kp; 
    private int tipo = -1;
    private String alg = "";
    
    public synchronized static KeyPairWrapUnwrap getInstance() throws MalformedURLException, RemoteException, NotBoundException  {
        return (KeyPairWrapUnwrap) Naming.lookup("RSAWrapUnwrap");
    }
    
    public RSACipherWrap() throws RemoteException, NoSuchAlgorithmException, MalformedURLException, AlreadyBoundException {
        super();
        Naming.rebind("RSAWrapUnwrap", this);
        KeyPairGenerator kgen = KeyPairGenerator.getInstance("RSA");
        kgen.initialize(1024);
        kp = kgen.genKeyPair();
        
    }
    
    public void setKeyPair(KeyPair kp) throws RemoteException {
        if (kp.getPrivate() instanceof RSAPrivateKey) {
            this.kp = kp; 
        } else {
            throw new RemoteException("The Key Pair must be RSA keys");
        }
        
    }

    public KeyPair getKeyPair() throws RemoteException {
        return kp;
    }

    public byte[] wrap(Key k) throws RemoteException {
        try {
            Cipher ci = Cipher.getInstance("RSA");
            ci.init(Cipher.WRAP_MODE, kp.getPrivate());
            if (k instanceof PublicKey) {
                tipo = Cipher.PUBLIC_KEY;
            } else if (k instanceof PrivateKey) {
                tipo = Cipher.PRIVATE_KEY;
            } else {
                tipo = Cipher.SECRET_KEY;
            }
            alg = k.getAlgorithm(); 
            return ci.wrap(k);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        } 
    }

    public Key unwrap(byte[] k) throws RemoteException {
        try {
            Cipher ci = Cipher.getInstance("RSA");
            ci.init(Cipher.UNWRAP_MODE, kp.getPublic());
            return ci.unwrap(k, alg, tipo);
        } catch (Exception e) {
            throw new RemoteException(e.getMessage());
        } 
    }
    

}
