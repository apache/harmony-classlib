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

package org.apache.harmony.tests.internal.net.www.protocol.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;
import java.net.URL;

import junit.framework.TestCase;


/**
 * Tests for <code>HTTPURLConnection</code> class constructors and methods.
 * 
 */
public class HttpURLConnectionTest extends TestCase {
    
    private final Object bound = new Object();

    //TODO: replace with connection to a mock server
    Thread httpServer = new Thread(new Runnable() {
        public void run() {
            try {
                ServerSocket ss = new ServerSocket(port);
                synchronized(bound) {
                    bound.notify();
                }
                ss.setSoTimeout(1000);
                try {
                    ss.accept().close();
                } catch (SocketTimeoutException ignore) {
                }
                ss.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }, "ServerSocket for HttpURLConnectionTest");

    private int port = 34567;
     
    public void setUp() throws Exception {
        super.setUp();
        httpServer.start();
    }

    public void tearDown() throws Exception {
        super.tearDown();
        httpServer.join();
    }

    /**
     * @tests org.apache.harmony.luni.internal.net.www.http.getOutputStream()
     */
    public void testGetOutputStream() throws Exception {
        // Regression for HARMONY-482
        synchronized(bound) {
            bound.wait(5000);
        }
        HttpURLConnection c = (HttpURLConnection) new URL("http://127.0.0.1:"
                + port).openConnection();
        c.setDoOutput(true);
        //use new String("POST") instead of simple "POST" to obtain other 
        //object instances then those that are in HttpURLConnection classes 
        c.setRequestMethod(new String("POST"));
        c.getOutputStream();
    }
}
