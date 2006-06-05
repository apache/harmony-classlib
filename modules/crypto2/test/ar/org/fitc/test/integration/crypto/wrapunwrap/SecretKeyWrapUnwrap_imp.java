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
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * SecretKeyWrapUnwrap implementation.
 * Work with any key who's algorithm may use in Cipher.
 *  By defualt is use DES.
 */
public class SecretKeyWrapUnwrap_imp extends UnicastRemoteObject implements
        SecretKeyWrapUnwrap {

    private static final long serialVersionUID = 1L;
    private SecretKey kp;
    private int tipo = -1;
    private String alg = "";

    private void init() throws NoSuchAlgorithmException, RemoteException, MalformedURLException, InterruptedException {
        KeyGenerator kg = KeyGenerator.getInstance("DES");
        kg.init(new SecureRandom());
        kp = kg.generateKey();
        Naming.rebind("SecretKeyWrapUnwrap", this);
    }

    public SecretKeyWrapUnwrap_imp() throws RemoteException, MalformedURLException, NoSuchAlgorithmException, InterruptedException {
        super();
        init();
    }

    public SecretKeyWrapUnwrap_imp(int arg0) throws RemoteException, NoSuchAlgorithmException, MalformedURLException, InterruptedException {
        super(arg0);
        init();
    }

    public SecretKeyWrapUnwrap_imp(int arg0, RMIClientSocketFactory arg1,
            RMIServerSocketFactory arg2) throws RemoteException, NoSuchAlgorithmException, MalformedURLException, InterruptedException {
        super(arg0, arg1, arg2);
        init();
    }

    public byte[] wrap(Key k) throws RemoteException {
        try {
            Cipher ci = Cipher.getInstance(kp.getAlgorithm());
            ci.init(Cipher.WRAP_MODE, kp);
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
            Cipher ci = Cipher.getInstance(kp.getAlgorithm());
            ci.init(Cipher.UNWRAP_MODE, kp);
            return ci.unwrap(k, alg, tipo);
        } catch (NoSuchAlgorithmException e) {
            throw new RemoteException(e.getMessage());
        } catch (NoSuchPaddingException e) {
            throw new RemoteException(e.getMessage());
        } catch (InvalidKeyException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    public void setSecretKey(SecretKey key) throws RemoteException {
        kp = key;
    }

    public SecretKey getSecretKey() throws RemoteException {
        return kp;
    }

  
}
