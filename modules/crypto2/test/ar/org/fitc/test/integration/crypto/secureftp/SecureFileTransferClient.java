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

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import ar.org.fitc.test.integration.crypto.util.DesPBEEncryptor;

/**
 * This class contains the main method for starting the Secure File Transfer client
 *
 * @author Osvaldo C. Demo
 * @version 1.0
 *
 */

public class SecureFileTransferClient {

    public static void main(String args[]) throws Exception {
        Socket soc = new Socket("127.0.0.1", 5217);
        TransferfileClient t = new TransferfileClient(soc);
        t.displayMenu();
    }
}

/**
 * Secure Transfer Client main application
 *
 * @author Osvaldo C. Demo
 *
 */

class TransferfileClient {

    BufferedReader br;

    Socket clientSoc;

    DataInputStream din;

    DataOutputStream dout;

    TransferfileClient(Socket soc) {
        try {
            clientSoc = soc;
            din = new DataInputStream(clientSoc.getInputStream());
            dout = new DataOutputStream(clientSoc.getOutputStream());
            br = new BufferedReader(new InputStreamReader(System.in));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method shows the options menu to the client console and sends the
     * corresponding commands to the server
     *
     * @throws Exception
     */

    public void displayMenu() throws Exception {
        while (true) {
            System.out.println("[ MENU ]");
            System.out.println("1. Send File");
            System.out.println("2. Receive File");
            System.out.println("3. Exit");
            System.out.print("\nEnter Choice :");
            int choice;
            choice = Integer.parseInt(br.readLine());
            if (choice == 1) {
                dout.writeUTF("SEND");
                sendFile();
            } else if (choice == 2) {
                dout.writeUTF("GET");
                receiveFile();
            } else {
                dout.writeUTF("DISCONNECT");
                System.exit(1);
            }
        }
    }

    /**
     * Method used for receiving a file from the server
     *
     * @throws Exception
     */

    void receiveFile() throws Exception {
        String fileName;
        System.out.print("Enter File Name :");
        fileName = br.readLine();
        dout.writeUTF(fileName);
        String msgFromServer = din.readUTF();

        if (msgFromServer.compareTo("File Not Found") == 0) {
            System.out.println("File not found on Server ...");
            return;
        } else if (msgFromServer.compareTo("READY") == 0) {
            System.out.println("Receiving File ...");
            File f = new File(fileName);
            if (f.exists()) {
                String option;
                System.out
                        .println("File Already Exists. Want to OverWrite (Y/N) ?");
                option = br.readLine();
                if (option == "N") {
                    dout.flush();
                    return;
                }
            }
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
            System.out.println(din.readUTF());
        }
    }

    /**
     * Method used for sending a file to the server
     *
     * @throws Exception
     */
    void sendFile() throws Exception {

        String filename;
        System.out.print("Enter File Name :");
        filename = br.readLine();

        File f = new File(filename);
        if (!f.exists()) {
            System.out.println("File not Exists...");
            dout.writeUTF("File not found");
            return;
        }

        dout.writeUTF(filename);

        String msgFromServer = din.readUTF();
        if (msgFromServer.compareTo("File Already Exists") == 0) {
            String option;
            System.out
                    .println("File Already Exists. Want to OverWrite (Y/N) ?");
            option = br.readLine();
            if (option == "Y") {
                dout.writeUTF("Y");
            } else {
                dout.writeUTF("N");
                return;
            }
        }

        System.out.println("Sending File ...");
        System.out.println("Secret key generated ...");

        DesPBEEncryptor encrypter = new DesPBEEncryptor("Any pass you like");
        FileInputStream fino = new FileInputStream(f);
        System.out.println("Initialised ...");

        encrypter.encrypt(fino, new FileOutputStream("text.txt"));
        System.out.println("generated ...");
        fino.close();
        FileInputStream fin = new FileInputStream("text.txt");

        int ch;
        do {
            ch = fin.read();
            dout.writeUTF(String.valueOf(ch));
        } while (ch != -1);
        fin.close();
        System.out.println(din.readUTF());
    }
}
