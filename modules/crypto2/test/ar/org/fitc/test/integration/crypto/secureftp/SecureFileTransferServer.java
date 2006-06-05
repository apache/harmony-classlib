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

package ar.org.fitc.test.integration.crypto.secureftp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import ar.org.fitc.test.integration.crypto.util.DesPBEEncryptor;

/**
 * This class contains the main method for starting the Secure File Transfer server
 *
 * @author Osvaldo C. Demo
 * @version 1.0
 *
 */
public class SecureFileTransferServer {

    public static void main(String args[]) throws Exception {
        ServerSocket soc = new ServerSocket(5217);
        System.out.println("FTP Server Started on Port Number 5217");
        while (true) {
            System.out.println("Waiting for Connection ...");
            @SuppressWarnings("unused") TransferFile t = new TransferFile(soc.accept());
        }
    }
}

/**
 * Secure Transfer Server main thread
 *
 * @author Osvaldo C. Demo
 *
 */

class TransferFile extends Thread {

    Socket clientSoc;

    DataInputStream din;

    DataOutputStream dout;

    TransferFile(Socket soc) {

        try {
            clientSoc = soc;
            din = new DataInputStream(clientSoc.getInputStream());
            dout = new DataOutputStream(clientSoc.getOutputStream());
            System.out.println("FTP Client Connected ...");
            start();
        } catch (Exception ex) {
        }
    }

    void sendFile() throws Exception {
        String filename = din.readUTF();
        File f = new File(filename);
        if (!f.exists()) {
            dout.writeUTF("File Not Found");
            return;
        } else {
            dout.writeUTF("READY");
            FileInputStream fin = new FileInputStream(f);
            int ch;
            do {
                ch = fin.read();
                dout.writeUTF(String.valueOf(ch));
            } while (ch != -1);
            fin.close();
            dout.writeUTF("File Receive Successfully");
        }
    }

    void receiveFile() throws Exception {
        String filename = din.readUTF();
        if (filename.compareTo("File not found") == 0) {
            return;
        }
        File f = new File(filename);
        String option;

        if (f.exists()) {
            dout.writeUTF("File Already Exists");
            option = din.readUTF();
        } else {
            dout.writeUTF("SendFile");
            option = "Y";
        }

        if (option.compareTo("Y") == 0) {

            DesPBEEncryptor encrypter = new DesPBEEncryptor("Any pass you like");

            @SuppressWarnings("unused") FileOutputStream fouttemp = new FileOutputStream(f);

            FileOutputStream fout = new FileOutputStream(f);

            int ch;
            String temp;
            do {
                temp = din.readUTF();
                ch = Integer.parseInt(temp);
                if (ch != -1) {
                    fout.write(ch);
                }
            } while (ch != -1);
            fout.close();

            encrypter.decrypt(new FileInputStream(f), new FileOutputStream(
                    "servercopy.txt"));

            dout.writeUTF("File Upload Successfull");
        } else {
            return;
        }

    }

    public void run() {
        while (true) {
            try {
                System.out.println("Waiting for Command ...");
                String command = din.readUTF();
                if (command.compareTo("GET") == 0) {
                    System.out.println("\tGET Command Received ...");
                    sendFile();
                    continue;
                } else if (command.compareTo("SEND") == 0) {
                    System.out.println("\tSEND Command Receiced ...");
                    receiveFile();
                    continue;
                } else if (command.compareTo("DISCONNECT") == 0) {
                    System.out.println("\tDisconnect Command Received ...");
                    System.exit(1);
                }
            } catch (Exception ex) {
            }
        }
    }
}
