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
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Vector;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NullCipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import ar.org.fitc.test.integration.crypto.util.Authenticator;

/**
 *
 * Main server class, it listen for new clients and adds them to a client vector.
 * This version is interactive.
 *
 * @author Osvaldo C. Demo
 *
 */

public class EncryptedChatServer {

    private byte[] salt = { (byte) 0x00, (byte) 0xaa, (byte) 0x1b, (byte) 0xc2,
            (byte) 0x34, (byte) 0xde, (byte) 0x5f, (byte) 0xa6 };

    private char[] password;

    private int port = 1024, maxClients = 1024, count = 20;

    private ServerSocket server;

    private Vector clients;

    private Message msg;

    private String currentLine = new String();

    private BufferedReader in = new BufferedReader(new InputStreamReader(
            System.in));

    private Authenticator ar;

    /**
     * Used to start the server
     *
     * @param newport the port to listen connections
     * @param maxclients the maximun number of clients allowed to connect
     */
        @SuppressWarnings("unchecked")
    private EncryptedChatServer(int newport, int maxclients) {
        try {
            port = newport;
            maxClients = maxclients;
            askFor("password");
            ar = new Authenticator(password.toString().getBytes());
            System.out.println("Server started.");
            server = new ServerSocket(port, maxClients);
            clients = new Vector();
            while (true) {
//                ThreadedSocket t =  new  ThreadedSocket(server.accept());
//                clients.addElement(t);
//                t.run();
                clients.addElement(new ThreadedSocket(server.accept()));
            }

        } catch (Throwable t) {
        }
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
        currentLine = in.readLine();
        if (!currentLine.equals("")) {
            if (cause.equalsIgnoreCase("port")) {
                port = Integer.parseInt(currentLine);
            } else if (cause.equalsIgnoreCase("maxClients")) {
                maxClients = Integer.parseInt(currentLine);
            } else if (cause.equalsIgnoreCase("password")) {
                password = currentLine.toCharArray();
            }
        }
    }

    public static void main(String[] args) {
        try {
            new EncryptedChatServer(2000, 3);
        } catch (Throwable t) {
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
        Cipher PBECipher = new NullCipher();

        try {
            PBECipher = Cipher.getInstance("PBEWithMD5AndDES");
            PBECipher.init(mode, SecretKeyFactory.getInstance(
                    "PBEWithMD5AndDES")
                    .generateSecret(new PBEKeySpec(password)),
                    new PBEParameterSpec(salt, count));
        } catch (Throwable t) {
        }
        return PBECipher;
    }

    /**
     * This method sends a message to all clients registered
     *
     * @param message
     *            A Message instance
     */

    private synchronized void sendAll(Message message) {
        try {
            message.setHash(ar.getHash(message.toString().getBytes()));
            System.err.println(message.toString());
            System.err.println(Arrays.toString(message.getHash()));
            for (int clientNumber = 0; clientNumber < clients.size(); clientNumber++) {
                    ((ThreadedSocket) clients.elementAt(clientNumber)).out
                        .writeObject(message);
                    ((ThreadedSocket) clients.elementAt(clientNumber)).out.flush();

            }

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * This class is used to create a thread for each client connected
     *
     * @author Osvaldo C. Demo
     *while (true)
     */

    private class ThreadedSocket extends Thread {

        private Socket client;

        private ObjectOutputStream out;

        private ObjectInputStream in;

        private byte[] hash;

        public ThreadedSocket(Socket socketChosen) {
            try {
                client = socketChosen;
                out = new ObjectOutputStream(new CipherOutputStream(client
                        .getOutputStream(), getCipher(Cipher.ENCRYPT_MODE)));

                in = new ObjectInputStream(new CipherInputStream(client
                        .getInputStream(), getCipher(Cipher.DECRYPT_MODE)));
                start();
            } catch (StreamCorruptedException t) {
                System.out.println("Someone is using a wrong password from "
                        + client.getInetAddress());
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

        public void run() {
            try {
                String nick = "";
//                boolean flag = true;

                msg = (Message) in.readObject();
                nick = msg.getText();
                System.err.println(nick);
                sendAll(new Message("Server: " + nick + " ["
                        + client.getInetAddress().getHostName()
                        + "] has joined this server.", hash));

                while (client.isBound() && client.isConnected()) {
                    msg = (Message) in.readObject();
                    hash = ar.getHash(msg.getText().getBytes());
                    if (!ar.isHashOk(hash, msg.getText().getBytes())) {
                        System.out
                                .println("Message integrity check FAILED from "
                                        + client.getInetAddress());
                    }

////                    if (flag) {
//                        nick = msg.getText();
//                        sendAll(new Message("Server: " + nick + " ["
//                                + client.getInetAddress().getHostName()
//                                + "] has joined this server.", hash));
//                        flag = false;
//                    } else {
                        sendAll(msg);
//                    }
                }
                msg = new Message("Server: " + nick + " has quit.", hash);
                sendAll(msg);
                System.out.println(msg.toString());
                client.close();
                out.close();
                in.close();
            } catch (EOFException t) {
                System.out.println("Lost connection with: "
                        + client.getInetAddress());
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
}
