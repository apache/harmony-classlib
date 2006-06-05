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

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Vector;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NullCipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import ar.org.fitc.test.integration.crypto.agreement.ServerDHKeyAgreement;
import ar.org.fitc.test.integration.crypto.util.Authenticator;
import ar.org.fitc.test.integration.crypto.wrapunwrap.SecretKeyWrapUnwrap_imp;

/**
 * 
 * Main server class, it listen for new clients and adds them to a client
 * vector. This version is for testing purposes and do not require user
 * intervention.
 * 
 * @author Osvaldo C. Demo
 * 
 */
public class EncryptedChatServerV2 {

    private int port = 1024, maxClients = 1024;

    private ServerSocket server;

    private Vector clients;

    private Message msg;

    private int messageCounter = 0;

    OutputStream debugOut;

    /**
     * Used to start the server EncryptedChatServerV2
     * 
     * @param newport
     *            the port to listen connections
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InterruptedException
     */

    public @SuppressWarnings("unchecked")
    EncryptedChatServerV2(int newport) throws Exception {

        port = newport;
        // System.out.println("Server started.");
        server = new ServerSocket(port, maxClients);
        clients = new Vector();
        // while (true) {
        clients.addElement(new ThreadedSocket(server.accept()));
        // }
    }

    public static void main(String[] args) throws Exception {

        try {
            Registry reg = LocateRegistry.createRegistry(1099);

            try {
                @SuppressWarnings("unused")
                Object o = (Object) new SecretKeyWrapUnwrap_imp();
                new EncryptedChatServerV2(2000);
            } catch (java.rmi.ConnectException e) {
                System.err.println("RMIRegistry is required. Run: ");
                System.err
                        .println("\t rmiregistry -J-Djava.rmi.server.codebase=file:<project home>");
            } finally {
                UnicastRemoteObject.unexportObject(reg,true);
            }
        } catch (IOException e) {

            try {
                @SuppressWarnings("unused")
                Object o = (Object) new SecretKeyWrapUnwrap_imp();
                new EncryptedChatServerV2(2000);
            } catch (java.rmi.ConnectException t) {
                System.err.println("RMIRegistry is required. Run: ");
                System.err
                        .println("\t rmiregistry -J-Djava.rmi.server.codebase=file:<project home>");
            }
        }

    }

    /**
     * This class is used to create a thread for each client connected
     * 
     * @author Osvaldo C. Demo
     * 
     */

    private class ThreadedSocket extends Thread {

        private Socket client;

        private byte[] password = { (byte) 0x00, (byte) 0xaa, (byte) 0x1b,
                (byte) 0xc2, (byte) 0x34, (byte) 0xde, (byte) 0x5f, (byte) 0xa6 };;

        private byte[] iv = { 24, 51, -15, 5, 91, -126, 23, 80 };

        private Key key;

        private Authenticator ar;

        private ObjectOutputStream out;

        private ObjectInputStream in;

        private String[] messages = {
                "This is a test to see what's the minimun size accepted",
                "What's that?", "Are you allright?", "Wich one?",
                "The weight of the world seems lighter", "Shut up and dance",
                "You wont regret" };

        public ThreadedSocket(Socket socketChosen) throws Exception {

            client = socketChosen;

            password = ServerDHKeyAgreement.agree(socketChosen);
            iv = new byte[8];
            System.arraycopy(ServerDHKeyAgreement.agree(socketChosen), 0, iv,
                    0, 8);
            ar = new Authenticator(password);
            key = SecretKeyFactory.getInstance("DES").translateKey(
                    new SecretKeySpec(password, "DES"));
            out = new ObjectOutputStream(new CipherOutputStream(client
                    .getOutputStream(), getCipher(Cipher.ENCRYPT_MODE)));

            // System.out.println("Step 1...Done");

            in = new ObjectInputStream(new CipherInputStream(client
                    .getInputStream(), getCipher(Cipher.DECRYPT_MODE)));
            // System.out.println("Step 2...Done");
            runWithException();
        }

        public void runWithException() throws Exception {
            while (messageCounter < messages.length) {
                msg = (Message) in.readObject();
                // System.out.println("Recieved Message " + messageCounter + ":
                // "
                // + msg.getText());
                checkIntegrity();
                checkMsg(messages[messageCounter]);
            }
            msg = (Message) in.readObject();
            if (msg.getText().equals("GoodBye")) {
                // main System.out.println("Client closed connection");
                client.close();
                out.close();
                in.close();
            }
        }

        public void run() {
            try {
                runWithException();
            } catch (EOFException t) {
                // System.out.println("Lost connection with: "
                // + client.getInetAddress());
            } catch (Throwable t) {
                t.printStackTrace();
            }

        }

        private void checkMsg(String string) throws Exception {
            if (msg.getText().equals(string)) {
                sendAck(out);
            } else {
                sendErr(out);
                throw new ServerError("Received ERR message from server");
            }
            messageCounter++;
        }

        private void checkIntegrity() {
            if (!ar.isHashOk(msg.getHash(), msg.getText().getBytes())) {
                // System.err.println("Message integrity check FAILED");
                // System.err.println("Hash on message: "
                // + Arrays.toString(msg.getHash()));
                // System.err.println("Hash calculated: "
                // + Arrays.toString(ar.getHash(msg.getText().getBytes())));
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
                // System.out.println("Cipher BlockSize:
                // "+cipher.getBlockSize());

            } catch (Throwable t) {
                // System.out.println("Unable to init a cipher. Using
                // NullCipher...");
                cipher = new NullCipher();
            }
            return cipher;
        }

        private void sendErr(ObjectOutputStream out) {
            sendMessage(new Message("Message ERR", ar.getHash(("Message ERR")
                    .getBytes())), out);
        }

        private void sendAck(ObjectOutputStream out) {
            sendMessage(new Message("Message OK", ar.getHash(("Message OK")
                    .getBytes())), out);
        }

        /**
         * This method sends a message to all clients registered
         * 
         * @param message
         *            A Message instance
         */

        private void sendMessage(Message message, ObjectOutputStream out) {
            try {
                message.setHash(ar.getHash(message.toString().getBytes()));
                // System.err.println("Sending: "+message.toString());
                // System.err.println(Arrays.toString(message.getHash()));
                out.writeObject(message);
                out.flush();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

    }

}
