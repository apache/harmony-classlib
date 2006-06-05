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

package ar.org.fitc.test.integration.crypto.agreement;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;

import ar.org.fitc.test.integration.crypto.wrapunwrap.WrapUnwrap;

public class ClientDHKeyAgreement extends DHKeyAgreement {

    final private WrapUnwrap ci;
    private InputStream in;
    private OutputStream out;
    private byte[] b;
    
    public static void main(String[] arg) {
        try {
            Socket client = new Socket("localhost", 2000);
            System.out.println(Arrays.toString(agree(client)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ClientDHKeyAgreement(Socket s) throws IOException, NoSuchAlgorithmException, NotBoundException {
        super(2);
        in = s.getInputStream();
        out = s.getOutputStream();
        //ci = (WrapUnwrap) RSACipherWrap.getInstance();
        ci = (WrapUnwrap) Naming.lookup("SecretKeyWrapUnwrap");
    }

    
    @Override
    public void sendKey(PublicKey key) throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(this.out);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, SecretKeyFactory.getInstance("DES").translateKey(new SecretKeySpec(b, "DES")));
        SealedObject encryptedKey = new SealedObject(key, cipher);
        out.writeObject(encryptedKey);

    }

    @Override
    public PublicKey recieveKey() throws Exception {
        DataInputStream in = new DataInputStream(this.in);
        int l = in.readInt();
        b = new byte[l];
        in.read(b);
        
        return (PublicKey) ci.unwrap(b);
        
    }

    public static SecretKey agree(Socket s, String alg) throws Exception {
        ClientDHKeyAgreement kagree = new ClientDHKeyAgreement(s);
        return kagree.applyDH(kagree.recieveKey()).generateSecret(alg);
    }
    public static byte[] agree(Socket s) throws Exception {
        ClientDHKeyAgreement kagree = new ClientDHKeyAgreement(s);
        return kagree.applyDH(kagree.recieveKey()).generateSecret();
    }
}
