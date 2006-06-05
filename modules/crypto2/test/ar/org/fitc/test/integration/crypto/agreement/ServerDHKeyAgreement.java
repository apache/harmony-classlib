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

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Arrays;

import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;

import ar.org.fitc.test.integration.crypto.wrapunwrap.SecretKeyWrapUnwrap_imp;
import ar.org.fitc.test.integration.crypto.wrapunwrap.WrapUnwrap;



public class ServerDHKeyAgreement extends DHKeyAgreement {

    final private WrapUnwrap ci;
    private InputStream in;
    private OutputStream out;
    private byte[] b;

    public static void main(String[] arg) {
        try {
            
            ServerSocket server = new ServerSocket(2000, 1);
            System.out.println("Server start");
            new SecretKeyWrapUnwrap_imp();
            while (true) {
                Socket s = server.accept();
                System.out.println(Arrays.toString(agree(s)));
                s.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ServerDHKeyAgreement(Socket s) throws IOException, NoSuchAlgorithmException, AlreadyBoundException, NotBoundException {
        super(2);
        in = s.getInputStream();
        out = s.getOutputStream();
        //ci = (WrapUnwrap) RSACipherWrap.getInstance();
        ci =  (WrapUnwrap) Naming.lookup("SecretKeyWrapUnwrap");
    }

    @Override
    public void sendKey(PublicKey key) throws Exception {
        b = ci.wrap(key);
        DataOutputStream out = new DataOutputStream(this.out); 
        out.writeInt(b.length);
        out.write(b);
    }

    @Override
    public PublicKey recieveKey() throws Exception {
        ObjectInputStream in = new ObjectInputStream(this.in);
        SealedObject encryptedKey = (SealedObject) in.readObject();
        return (PublicKey) encryptedKey.getObject(SecretKeyFactory.getInstance("DES").translateKey(new SecretKeySpec(b, "DES")));
        
    }

    public static SecretKey agree(Socket s, String alg) throws Exception {
        ServerDHKeyAgreement kagree = new ServerDHKeyAgreement(s);
        return kagree.applyDH().generateSecret(alg);
    }
    public static byte[] agree(Socket s) throws Exception {
        ServerDHKeyAgreement kagree = new ServerDHKeyAgreement(s);
        return kagree.applyDH().generateSecret();
    }
}
