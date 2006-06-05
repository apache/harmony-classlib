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

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.math.BigInteger;
import java.rmi.Naming;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.KeyAgreement;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;

import ar.org.fitc.test.integration.crypto.wrapunwrap.SecretKeyWrapUnwrap_imp;
import ar.org.fitc.test.integration.crypto.wrapunwrap.WrapUnwrap;

/**
 *
 * This abstract class implemente a Diffie-Hellman Key Agreement protocol.
 *
 * At inicialization time it set how many actores will use, by default is two.
 *
 * Also implements Runable to make multithread aplication (test).
 *
 * The pass key mechanism isn't define, but to abstract funtion may do that job.
 *
 *
 */

public abstract class DHKeyAgreement implements Runnable {

    /**
     * Simple test of two actores with individual thread excecuting
     *
     * Anonymous implementation is use. where piped Stream make the connection.
     *
     */

    static public void main(String[] argv) {
        try {
            System.out.println("DHKeyAgreement main");
            new SecretKeyWrapUnwrap_imp();
            final WrapUnwrap ci = (WrapUnwrap) Naming.lookup("SecretKeyWrapUnwrap");
            
            InputStream in1 = new PipedInputStream();
            OutputStream out1 = new PipedOutputStream((PipedInputStream) in1);
            final ObjectOutputStream oout1 = new ObjectOutputStream(out1);
            final ObjectInputStream oin1 = new ObjectInputStream(in1);


            InputStream in2 = new PipedInputStream();
            OutputStream out2 = new PipedOutputStream((PipedInputStream) in2);
            final ObjectOutputStream oout2 = new ObjectOutputStream(out2);
            final ObjectInputStream oin2 = new ObjectInputStream(in2);

            System.out.println("Stream created");
            DHKeyAgreement ka12 = new DHKeyAgreement() {

                @Override
                public void sendKey(PublicKey key) throws Exception {
                    System.err.println("ka12: send");
                    oout1.writeObject(ci.wrap(key));
                    //oout1.write();

                }

                @Override
                public PublicKey recieveKey() throws Exception {
                    System.err.println("ka12: recieve");
                    return (PublicKey) ci.unwrap((byte[]) oin2.readObject());
                }

            };
            DHKeyAgreement ka21 = new DHKeyAgreement() {

                @Override
                public void sendKey(PublicKey key) throws Exception {
                    System.err.println("ka21: send");
                    oout2.writeObject(ci.wrap(key));

                }

                @Override
                public PublicKey recieveKey() throws Exception {
                    System.err.println("ka21: recieve");
                    return (PublicKey) ci.unwrap((byte[]) oin1.readObject());
                }

            };
            Thread t = new Thread(ka12);
            Thread r = new Thread(ka21);

            t.start();
            r.start();
            t.join();
            r.join();

            oout1.close();
            oin1.close();
            oout2.close();
            oin2.close();

            byte[] code12 = ka12.getResult();
            byte[] code21 = ka21.getResult();
            for (int i = 0; i < code12.length; i++) {
                if (code12[i] != code21[i]) {
                    throw new Exception("no same Secret generate");
                }
            }
            System.out.println("DHKeyAgreement main finish");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * Just call ''applyDH''  and generateSecret and store the result.
     *
     * @see DHKeyAgreement#applyDH()
     * @see java.lang.Runnable#run()
     * @see KeyAgreement#generateSecret()
     */
    public void run() {
        try {
            result = applyDH().generateSecret();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The needed iterate of doPhase, one less than actores.
     *
     * @see javax.crypto.KeyAgreement#doPhase(java.security.Key, boolean)
     */
    protected int iterate = 1;

    /**
     * The result obtein in run execution
     *
     * @see DHKeyAgreement#run()
     */
    protected byte[] result = null;

    /**
     * Obtein the result that execution of run run.
     *
     * @see DHKeyAgreement#run()
     */
    public byte[] getResult() {
        return result;
    }

    /**
     * Simple constructor with two actores default.
     *
     */
    public DHKeyAgreement() {
        super();

    }

    /**
     * Simple contructor.
     *
     * @param actores who many actores will be use.
     */

    public DHKeyAgreement(int actores) {
        super();
        this.iterate = actores-1;
    }


    private KeyPair genDHKeyPair() throws GeneralSecurityException {
        DHParameterSpec dhSkipParamSpec = new DHParameterSpec(skip1024Modulus,
                skip1024Base);

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DH");
        kpg.initialize(dhSkipParamSpec);

        return kpg.generateKeyPair();
    }

    private KeyPair genDHKeyPair(PublicKey key)
            throws GeneralSecurityException {

        DHParameterSpec dhSkipParamSpec = ((DHPublicKey) key).getParams();

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DH");
        kpg.initialize(dhSkipParamSpec);

        return kpg.generateKeyPair();
    }

    /**
     *
     * This function is use when a key is ready to exchenge to other actor.
     * Who do that?, is the simple cuastion.
     *
     * @param key the exchange key
     * @throws Exception free to throw at any problem
     */

    abstract public void sendKey(PublicKey key) throws Exception;

    /**
     * When a new key is needed, this function is call.
     *
     * @return the exchange key.
     * @throws Exception free to throw at any problem
     */
    abstract public PublicKey recieveKey() throws Exception;

    private KeyAgreement mainWork(PrivateKey kp, int iterate, PublicKey otherKey)
            throws Exception {
        KeyAgreement kagree = KeyAgreement.getInstance("DH");
        kagree.init(kp);

        PublicKey key = otherKey;
        for (int i = 1; i < iterate; i++) {
            key = (PublicKey) kagree.doPhase(key, false);
            sendKey(key);
            key = recieveKey();
        }
        kagree.doPhase(key, true);
        return kagree;
    }
    /**
     * the main function. Key pair is generate, call doPhase and return a KeyAgreement ready to make generateSecret.
     *
     * @return KeyAgreement ready to make generateSecret.
     * @throws Exception
     * @see javax.crypto.KeyAgreement#doPhase(java.security.Key, boolean)
     */
    public KeyAgreement applyDH() throws Exception {
        KeyPair kp = genDHKeyPair();
        PublicKey key = kp.getPublic();
        sendKey(key);
        key = recieveKey();
        return mainWork(kp.getPrivate(), iterate, key);
    }

    /**
     * Similar to applyDH, but this recieve the first public key by parameter.
     * The key pair generate have the same spec.
     *
     * @param otherKey the first publi key
     * @return KeyAgreement ready to make generateSecret.
     * @throws Exception
     * @see DHKeyAgreement#applyDH()
     * @see javax.crypto.KeyAgreement#doPhase(java.security.Key, boolean)
     */
    public KeyAgreement applyDH(PublicKey otherKey) throws Exception {
        KeyPair kp = genDHKeyPair(otherKey);
        PublicKey key = kp.getPublic();
        sendKey(key);
        key = otherKey;
        return mainWork(kp.getPrivate(), iterate, key);
    }

    private static final byte skip1024ModulusBytes[] = { (byte) 0xF4,
        (byte) 0x88, (byte) 0xFD, (byte) 0x58, (byte) 0x4E, (byte) 0x49,
        (byte) 0xDB, (byte) 0xCD, (byte) 0x20, (byte) 0xB4, (byte) 0x9D,
        (byte) 0xE4, (byte) 0x91, (byte) 0x07, (byte) 0x36, (byte) 0x6B,
        (byte) 0x33, (byte) 0x6C, (byte) 0x38, (byte) 0x0D, (byte) 0x45,
        (byte) 0x1D, (byte) 0x0F, (byte) 0x7C, (byte) 0x88, (byte) 0xB3,
        (byte) 0x1C, (byte) 0x7C, (byte) 0x5B, (byte) 0x2D, (byte) 0x8E,
        (byte) 0xF6, (byte) 0xF3, (byte) 0xC9, (byte) 0x23, (byte) 0xC0,
        (byte) 0x43, (byte) 0xF0, (byte) 0xA5, (byte) 0x5B, (byte) 0x18,
        (byte) 0x8D, (byte) 0x8E, (byte) 0xBB, (byte) 0x55, (byte) 0x8C,
        (byte) 0xB8, (byte) 0x5D, (byte) 0x38, (byte) 0xD3, (byte) 0x34,
        (byte) 0xFD, (byte) 0x7C, (byte) 0x17, (byte) 0x57, (byte) 0x43,
        (byte) 0xA3, (byte) 0x1D, (byte) 0x18, (byte) 0x6C, (byte) 0xDE,
        (byte) 0x33, (byte) 0x21, (byte) 0x2C, (byte) 0xB5, (byte) 0x2A,
        (byte) 0xFF, (byte) 0x3C, (byte) 0xE1, (byte) 0xB1, (byte) 0x29,
        (byte) 0x40, (byte) 0x18, (byte) 0x11, (byte) 0x8D, (byte) 0x7C,
        (byte) 0x84, (byte) 0xA7, (byte) 0x0A, (byte) 0x72, (byte) 0xD6,
        (byte) 0x86, (byte) 0xC4, (byte) 0x03, (byte) 0x19, (byte) 0xC8,
        (byte) 0x07, (byte) 0x29, (byte) 0x7A, (byte) 0xCA, (byte) 0x95,
        (byte) 0x0C, (byte) 0xD9, (byte) 0x96, (byte) 0x9F, (byte) 0xAB,
        (byte) 0xD0, (byte) 0x0A, (byte) 0x50, (byte) 0x9B, (byte) 0x02,
        (byte) 0x46, (byte) 0xD3, (byte) 0x08, (byte) 0x3D, (byte) 0x66,
        (byte) 0xA4, (byte) 0x5D, (byte) 0x41, (byte) 0x9F, (byte) 0x9C,
        (byte) 0x7C, (byte) 0xBD, (byte) 0x89, (byte) 0x4B, (byte) 0x22,
        (byte) 0x19, (byte) 0x26, (byte) 0xBA, (byte) 0xAB, (byte) 0xA2,
        (byte) 0x5E, (byte) 0xC3, (byte) 0x55, (byte) 0xE9, (byte) 0x2F,
        (byte) 0x78, (byte) 0xC7 };

    private static final BigInteger skip1024Modulus = new BigInteger(1,
        skip1024ModulusBytes), skip1024Base = BigInteger.valueOf(2);
}
