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

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NullCipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import ar.org.fitc.test.integration.crypto.util.Authenticator;

/**
 * This class creates the chat client window. This version requires user interaction.
 * @author Osvaldo C. Demo
 *
 */
public class EncryptedChatClient extends JFrame {

    private static final long serialVersionUID = 5951259929606452222L;

    private byte[] salt = { (byte) 0x00, (byte) 0xaa, (byte) 0x1b, (byte) 0xc2,
            (byte) 0x34, (byte) 0xde, (byte) 0x5f, (byte) 0xa6 };

    private char[] password;

    private int port = 2000, count = 20;

    private String ip = "127.0.0.1", nick;

    private JTextArea textArea;

    private JTextField textField;

    private Socket client;

    private ObjectOutputStream out;

    private ObjectInputStream in;

    private Message msg;

    private Authenticator ar;

    private BufferedReader systemIn = new BufferedReader(new InputStreamReader(
            System.in));

    private EncryptedChatClient() {
        super("Encrypted Chat Client");

        try {
            askFor("IP");
            askFor("port");
            askFor("nick for");
            askFor("password of");

            ar = new Authenticator(password.toString().getBytes());

            textArea = new JTextArea();
            textField = new JTextField();
            JPanel panel = new JPanel(new BorderLayout());
            textArea.setEditable(false);

            textField.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    try {
                        System.err.println("ActionEvent: " + e.toString() + ", clase: " + e.getClass().toString());
                        String message = nick + ": " + textField.getText();
                        msg = new Message(message,ar.getHash(message.getBytes()));

                        System.err.println(msg.getText());
                        System.err.println(java.util.Arrays.toString(msg.getHash()));

                        out.writeObject(msg);
                        textField.setText("");
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
            });

            panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
            panel.add(textField, BorderLayout.SOUTH);

            panel.setBorder(new TitledBorder(new EtchedBorder(),
                    nick+"'s encrypted chat session"));

            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    try {
                        System.exit(0);
                    } catch (Throwable t) {
                    }
                }
            });

            getContentPane().add(panel);
            setSize(640, 480);
            setVisible(true);

            client = new Socket(ip, port);
            
            
            
            out = new ObjectOutputStream(new CipherOutputStream(client
                    .getOutputStream(), getCipher(Cipher.ENCRYPT_MODE)));
            in = new ObjectInputStream(new CipherInputStream(client
                    .getInputStream(), getCipher(Cipher.DECRYPT_MODE)));

            msg = new Message("init."+nick,ar.getHash(("init."+nick).getBytes()));
            out.writeObject(msg);
            out.flush();

            while (client.isBound() && client.isConnected()) {
                msg = (Message) in.readObject();
                if (ar.isHashOk(msg.getHash(),msg.getText().getBytes())) {
                    System.err.println("Message integrity check FAILED");
                    System.err.println("Hash on message: "+Arrays.toString(msg.getHash()));
                    System.err.println("Hash calculated: "+Arrays.toString(ar.getHash(msg.getText().getBytes())));
                }
                textArea.append(msg.getText() + "\n");
            }
        } catch (SocketException t) {
            textArea.append("Possible wrong password or server down...");
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            new EncryptedChatClient();
        } catch (Throwable t) {
            t.printStackTrace();
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
        Cipher pbeCipher = new NullCipher();
        try {
            pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
            pbeCipher.init(mode, SecretKeyFactory.getInstance(
                    "PBEWithMD5AndDES")
                    .generateSecret(new PBEKeySpec(password)),
                    new PBEParameterSpec(salt, count));
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return pbeCipher;
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
                +" you would like to use for your server: ");

        String currentLine = systemIn.readLine();
        if (!currentLine.equals("")) {

            if (cause.contains("port")) {
                port = Integer.parseInt(currentLine);
            } else if (cause.contains("IP")) {
                ip = currentLine;
            } else if (cause.contains("password")) {
                password = currentLine.toCharArray();
            } else if (cause.contains("nick")) {
                nick = currentLine;
            }
        }

    }
}
