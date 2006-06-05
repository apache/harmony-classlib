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

package ar.org.fitc.test.integration.crypto.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NullCipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import ar.org.fitc.test.integration.crypto.agreement.ClientDHKeyAgreement;
import ar.org.fitc.test.integration.crypto.util.Authenticator;

/**
 * This class creates the chat client window
 *
 * @author Osvaldo C. Demo
 *
 */
public class EncryptedChatClientV2 {

    private byte[] password = { (byte) 0x00, (byte) 0xaa, (byte) 0x1b, (byte) 0xc2,
            (byte) 0x34, (byte) 0xde, (byte) 0x5f, (byte) 0xa6 };

    private byte[] iv = { 24, 51, -15, 5, 91, -126, 23, 80 };

    private int port = 2000;

    private String ip = "127.0.0.1";

    private Socket client;

    private ObjectOutputStream out;

    private ObjectInputStream in;

    private Message msg;

    private Authenticator ar;

    private Key key;

    private BufferedReader systemIn = new BufferedReader(new InputStreamReader(
            System.in));

    private String[] messages = {
            "This is a test to see what's the minimun size accepted",
            "What's that?", "Are you allright?", "Wich one?",
            "The weight of the world seems lighter",
            "Shut up and dance", "You wont regret"};

    private int messageCounter = 0;

    public EncryptedChatClientV2(String ip, int port) throws Exception {
        this.ip = ip;
        this.port = port;
        startEncryptedChatClientV2();
    }

    public EncryptedChatClientV2() throws ClientError {
        try {
            askFor("IP");
            askFor("port");
            startEncryptedChatClientV2();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (ClientError e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startEncryptedChatClientV2() throws Exception {
        try {

            try {
                client = new Socket(ip, port);
            } catch (SocketException t) {
                for (int i=0; i < 100; i++) {
                    Thread.yield();
                    try {
                        client = new Socket(ip, port);
                        break;
                    } catch (SocketException t1) {
                        if (i == 99) {
                            throw t1;
                        }
                    }
                }

            }


            password = ClientDHKeyAgreement.agree(client);
            iv = new byte[8];
            System.arraycopy(ClientDHKeyAgreement.agree(client), 0, iv, 0, 8);

            ar = new Authenticator(password);
            key = SecretKeyFactory.getInstance("DES").translateKey(new SecretKeySpec(password, "DES"));

//            System.out.print("Initializing OutputStream...");
            out = new ObjectOutputStream(new CipherOutputStream(client
                    .getOutputStream(), getCipher(Cipher.ENCRYPT_MODE)));
//            System.out.print("\tDone");

//            System.out.print("\rInitializing InputStream...");
            in = new ObjectInputStream(new CipherInputStream(client
                    .getInputStream(), getCipher(Cipher.DECRYPT_MODE)));
//            System.out.print("\tDone");

//            System.out.println("\rSending test messages...");
            while (client.isBound() && client.isConnected()) {

                if (messageCounter < messages.length) {
//                    System.out.println("Sending Message nº "+messageCounter);
                    sendMessage(messages[messageCounter]);
                    msg = (Message) in.readObject();
                    checkIntegrity();
                    recieveAck();
                } else {
                    out.writeObject(new Message("GoodBye", "GoodBye".getBytes()));
                    out.close();
                    in.close();
                    client.close();
                }
            }
        } catch (SocketException t) {
//            t.printStackTrace();
//            System.out.println("All test messages were sent... Closing communication.");
        }
    }

    private void recieveAck() throws ClientError {
        if (msg.getText().equals("Message OK")) {
//            System.out.println("Server sent OK for Message " + messageCounter);
        } else {
//            System.out.print("Server sent ERR for Message "
//                    + messageCounter);
            throw new ClientError("Received ERR message from server");
        }
        messageCounter++;
    }

    private void sendMessage(String string) throws IOException {
//        System.out.println("Sending: " + string);
        msg = new Message(string, ar.getHash(string.getBytes()));
        out.writeObject(msg);
    }

    private void checkIntegrity() {
        if (!ar.isHashOk(msg.getHash(), msg.getText().getBytes())) {
//            System.err.println("Message integrity check FAILED");
//            System.err.println("Hash on message: "
//                    + Arrays.toString(msg.getHash()));
//            System.err.println("Hash calculated: "
//                    + Arrays.toString(ar.getHash(msg.getText().getBytes())));
        }
    }

    /**
     * This method prepares a cipher for its use
     *
     * @param mode
     *            Cipher.ENCRYPT_MODE or Cipher.DECRYPT_MODE
     * @return an initialized instance of cipher ready for use
     */
    private Cipher getCipher(int mode) {
        Cipher cipher = new NullCipher();
        try {
            cipher = Cipher.getInstance("DES/CFB8/NoPadding", "SunJCE");
            cipher.init(mode, key, new IvParameterSpec(iv));
        } catch (Throwable t) {
            t.printStackTrace();
//            System.err
//                    .println("Warning: Unable to init a cipher. Communication channel is not encrypted. Using NullCipher...");
            cipher = new NullCipher();
        }
        return cipher;
    }

    /**
     * This method is used for parsing from the console the port, maximun
     * allowed clients and the password
     *
     * @param cause
     *            A string representing the question to the user
     * @throws IOException
     */
    private void askFor(String cause) throws IOException {

        System.out.print("Enter the " + cause
                + " you would like to use for your server: ");

        String currentLine = systemIn.readLine();
        if (!currentLine.equals("")) {

            if (cause.contains("port")) {
                port = Integer.parseInt(currentLine);
            } else if (cause.contains("IP")) {
                ip = currentLine;
            }
        }

    }

    public static void main(String[] args) throws Exception{
            // System.out.println(Arrays.toString("fobrasdfasdfasa".getBytes()));
            // System.out.println("fobrasdfasdfasa".getBytes().length);
            new EncryptedChatClientV2("127.0.0.1", 2000);
    }

}
