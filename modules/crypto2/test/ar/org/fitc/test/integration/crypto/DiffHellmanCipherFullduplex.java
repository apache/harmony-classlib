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

package ar.org.fitc.test.integration.crypto;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.rmi.Naming;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;

import ar.org.fitc.test.integration.crypto.agreement.DHKeyAgreement;
import ar.org.fitc.test.integration.crypto.util.K;
import ar.org.fitc.test.integration.crypto.wrapunwrap.RSACipherWrap;
import ar.org.fitc.test.integration.crypto.wrapunwrap.WrapUnwrap;

public class DiffHellmanCipherFullduplex {

    private InputStream in;
    private OutputStream out;

    @SuppressWarnings("unused")
    private final static String msg = "eeeeaaaaaaaaaasdfasdfdfasdfaefasdaasdfasdfaefasdaasdfasdfaefasdaa";

    static public String comaSeparate(SecretKey k) {
        byte[] buf = k.getEncoded();
        String result = "Alg: " + k.getAlgorithm() + ", encoded: ";
        for (int i=0; i < buf.length;i++) {
            result += buf[i] + ", ";
        }
        return result;
    }

    public static void initExchangeKeys(InputStream in, OutputStream out) {
        try {





        } catch (Exception e) {

        }

    }

    public static void main(String[] args) {
        try {
            new RSACipherWrap();
            WrapUnwrap ci = (WrapUnwrap) Naming.lookup("RSAWrapUnwrap");



            byte[] encoded = ci.wrap(new K("ALLA", new byte[] {1,2,3,4,5,6,6,8,89,123,123,3,13,32,123,2}));
            System.out.println(Arrays.toString(encoded));
            SecretKeyFactory.getInstance("PBEWithMD5andDES");
//            new X509EncodedKeySpec(kp.getPublic());
//            ci.init(Cipher.UNWRAP_MODE, null);
            Key k = ci.unwrap(encoded);
            System.out.println("Son iguales: " + Arrays.toString(k.getEncoded()) + " algorithm: " +k.getAlgorithm() + ", format: " + k.getFormat());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    public static void main(String[] args) {
//        args = new String[] {msg, msg, msg};
//        try {
//            InputStream in = new PipedInputStream();
//            OutputStream out = new PipedOutputStream((PipedInputStream) in);
//            DiffHellmanCipherFullduplex d = new DiffHellmanCipherFullduplex(in, out);
//            out = d.getOutputStream();
//            in = d.getInputStream();
//
//            PrintStream outP = new PrintStream(out);
//            BufferedReader inP = new BufferedReader(new InputStreamReader(in));
//
//            byte[] b = new byte[32];
//            //for (int i = 0; i < args.length; i++) {
//            out.write(msg.getBytes(), 0 , 32);
//            //    System.out.println(inP.readLine());
//
//            //}
//            out.close();
//            in.read(b,0, 4);
//            System.out.println(b.toString());
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.exit(0);
////        if (args.length == 0) {
////            try {le
////                ServerSocket ss = new ServerSocket(2000);
////                System.out.println("Server Start");
////
////                while (true) {
////                    Socket s = ss.accept();
////                    System.out.println("conect remote");
////                    DiffHellmanCipherFullduplex d = new DiffHellmanCipherFullduplex(s.getInputStream(), s.getOutputStream());
////                    PrintStream out = new PrintStream(d.getOutputStream());
////                    BufferedReader in = new BufferedReader(new InputStreamReader(d.getInputStream()));
////                    System.err.println("I/O initializad");
////
////                    String is = in.readLine();
////                    while (is != null) {
////                        System.out.println("resive: " +is);
////                        out.println("Server get: " + is);
////                        out.flush();
////                        is = in.readLine();
////                    }
////                }
////
////            } catch (IOException e) {
////                // TODO Auto-generated catch block
////                e.printStackTrace();
////            }
////            System.out.println("Server Stop");
////        } else {
////            try {
////                Socket s = new Socket("localhost", 2000);
////                DiffHellmanCipherFullduplex d = new DiffHellmanCipherFullduplex(s.getInputStream(), s.getOutputStream());
////                PrintStream out = new PrintStream(d.getOutputStream());
////                BufferedReader in = new BufferedReader(new InputStreamReader(d.getInputStream()));
////                System.err.println("I/O initializad");
////                for (int i=0; i < args.length; i++) {
////                    System.out.println("Send: " + args[i]);
////                    out.println(args[i]);
////                    out.flush();
////                    System.out.println(in.readLine());
////                }
////                System.err.println("closed socket");
////                s.close();
////            } catch (IOException e) {
////                // TODO Auto-generated catch block
////                e.printStackTrace();
////            }
////        }
//    }

    public DiffHellmanCipherFullduplex(InputStream in, OutputStream out) {
        super();
        this.in = in;
        this.out = out;
        doWork();
        new DHKeyAgreement() {
            @Override
            public void sendKey(PublicKey key) {
            }

            @Override
            public PublicKey recieveKey() {
                return null;
            }

        };

    }



    public InputStream getInputStream() {
        return in;
    }

    public OutputStream getOutputStream() {
        return out;
    }




    private void doWork() {

        KeyPairGenerator kpg = null;
        try {
            kpg = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        kpg.initialize(1024);
        KeyPair kp = kpg.genKeyPair();
        System.err.println("Pair Key generate");
        PublicKey pubkey = null;
        try {
            (new ObjectOutputStream(out)).writeObject(kp.getPublic()) ;
            System.err.println("Public Key send");
            pubkey = (PublicKey) (new ObjectInputStream(in)).readObject() ;
            System.err.println("Public Key resive");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Cipher ci = null;
        try {
            ci = Cipher.getInstance("RSA");
            ci.init(Cipher.ENCRYPT_MODE, kp.getPrivate());
            System.err.println("CipherStream init private Key");
            out = new CipherOutputStream(out, ci);
            ci = Cipher.getInstance("RSA");
            ci.init(Cipher.ENCRYPT_MODE, pubkey);
            System.err.println("CipherStream init public Key resive");
            out = new CipherOutputStream(out, ci);

            ci = Cipher.getInstance("RSA");
            ci.init(Cipher.DECRYPT_MODE, kp.getPrivate());
            System.err.println("CipherStream init private Key");
            in = new CipherInputStream(in, ci);
            ci = Cipher.getInstance("RSA");
            ci.init(Cipher.DECRYPT_MODE, pubkey);
            System.err.println("CipherStream init public Key resive");

            in = new CipherInputStream(in, ci);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
