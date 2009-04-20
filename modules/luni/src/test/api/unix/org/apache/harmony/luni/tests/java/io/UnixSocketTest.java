/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.harmony.luni.tests.java.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import junit.framework.TestCase;

public class UnixSocketTest extends TestCase {

    /**
     * @tests java.net.Socket#getInputStream()
     */
    public void test_getInputStream() throws IOException {
        // Simple read/write test over the IO streams
        final ServerSocket pingServer = new ServerSocket(0);
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    Socket worker = pingServer.accept();
                    pingServer.close();
                    InputStream in = worker.getInputStream();
                    in.read();
                    OutputStream out = worker.getOutputStream();
                    out.write(new byte[42]);
                    worker.close();
                } catch (IOException e) {
                    fail(e.getMessage());
                }
            }
        };
        Thread thread = new Thread(runnable, "UnixSocket.getInputStream");
        thread.start();

        Socket pingClient = new Socket(InetAddress.getLocalHost(), pingServer
                .getLocalPort());

        // Busy wait until the client is connected.
        int c = 0;
        while (!pingClient.isConnected()) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // ignore
            }
            if (++c > 4) {
                fail("thread is not alive");
            }
        }

        // Write some data to the server to provoke it
        OutputStream out = pingClient.getOutputStream();
        out.write(new byte[256]);

        InputStream in = pingClient.getInputStream();
        in.read(new byte[42]);
        try {
            in.read();
            fail("Should throw SocketException");
        } catch (SocketException e) {
            // expected
        }
        in.close();

        try {
            in.read();
            fail("Should throw SocketException");
        } catch (SocketException e) {
            // expected
        }
        try {
            in.read(new byte[5]);
            fail("Should throw SocketException");
        } catch (SocketException e) {
            // expected
        }

        pingClient.close();
        pingServer.close();
    }

}
