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
package ar.org.fitc.test.rmi.server;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;
import java.rmi.server.RemoteRef;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.security.Permission;
import java.util.logging.LoggingPermission;

import ar.org.fitc.test.rmi.object2test.Echo;
import ar.org.fitc.test.rmi.object2test.EchoUnicast_Imp;
import ar.org.fitc.test.util.Net;

public class TestRemoteServer extends TestRemoteObject {

    private class MyRemoteServer extends RemoteServer {
        public MyRemoteServer(RemoteRef r) {
            super(r);
        }

        public MyRemoteServer() {
            super();
        }
    }

    public RemoteObject constructor() {
        return new MyRemoteServer();
    }

    public RemoteObject constructor(RemoteRef r) {
        return new MyRemoteServer(r);
    }

    /*
     * Test method for 'java.rmi.server.RemoteServer.getClientHost()'
     */
    public void testGetClientHost001() {
        try {
            RemoteServer.getClientHost();
            fail("Nobody is using the server");
        } catch (ServerNotActiveException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testGetClientHost002() {
        try {
            EchoUnicast_Imp e = new EchoUnicast_Imp() {
                public String echo(String msg) throws RemoteException {

                    try {
                        assertEquals("The client is localhost", RemoteServer
                                .getClientHost(), Net.ip());
                    } catch (ServerNotActiveException e) {
                        fail("It is working, no exception is expect");
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    return EchoUnicast_Imp.class + " - Have said: " + msg;
                }
            };
            Echo msg = (Echo) RemoteServer.toStub(e);
            msg.echo("hi");
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'java.rmi.server.RemoteServer.getLog()'
     */
    public void testGetLog001() {
        try {
            RemoteServer.getLog();
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }

    }

    /*
     * Test method for 'java.rmi.server.RemoteServer.setLog(OutputStream)'
     */
    public void testSetLog001() {
        try {
            RemoteServer.setLog(null);
            assertNull("Not logger is set", RemoteServer.getLog());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testSetLog002() {
        try {
            RemoteServer.setLog(new ByteArrayOutputStream());
            assertNotNull("One logger is set", RemoteServer.getLog());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testSetLog003() {
        SecurityManager smOld = System.getSecurityManager();
        try {
            SecurityManager sm = new SecurityManager() {
                public void checkPermission(Permission perm) {
                    if (perm instanceof LoggingPermission)
                        throw new SecurityException(
                                "No, No, No, you can't do that.");
                }
            };
            System.setSecurityManager(sm);
            RemoteServer.setLog(null);
            fail("SecurityMAnager not allow LoggingPermission");
        } catch (SecurityException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        } finally {
            System.setSecurityManager(smOld);
        }
    }

    public void testSetLog004() {
        try {
            OutputStream outS = RemoteServer.getLog();
            RemoteServer.setLog(new PrintStream(new ByteArrayOutputStream()));
            RemoteServer.setLog(outS);
            assertSame(outS, RemoteServer.getLog());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
}
