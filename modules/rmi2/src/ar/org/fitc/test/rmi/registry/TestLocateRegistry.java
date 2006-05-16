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
package ar.org.fitc.test.rmi.registry;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMISocketFactory;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.rmi.ssl.SslRMIServerSocketFactory;

import junit.framework.TestCase;
import ar.org.fitc.test.util.Messages;

public class TestLocateRegistry extends TestCase implements Messages {

    private static int port = 1999;

    public static void main(String[] args) {
    }

    public TestLocateRegistry(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        port += 1;
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'java.rmi.registry.LocateRegistry.createRegistry(int)'
     */
    public void testCreateRegistryInt001() {
        try {
            assertNotNull(msgNotNull, LocateRegistry.createRegistry(port));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testCreateRegistryInt002() {
        try {
            LocateRegistry.createRegistry(port);
            LocateRegistry.createRegistry(port);
            fail("Two registry with same port is imposible");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testCreateRegistryInt003() {
        try {
            LocateRegistry.createRegistry(-port);
            fail("non negative number can't be port");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testCreateRegistryInt004() {
        try {
            LocateRegistry.createRegistry(99999);
            fail("The port is bounded of top");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    // REGISTRY_PORT=1099
    public void testCreateRegistryInt005() {
        try {

            assertNotNull(LocateRegistry.createRegistry(Registry.REGISTRY_PORT));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'java.rmi.registry.LocateRegistry.createRegistry(int,
     * RMIClientSocketFactory, RMIServerSocketFactory)'
     */

    public void testCreateRegistryIntRMIClientSocketFactoryRMIServerSocketFactory001() {
        try {
            assertNotNull(msgNotNull, LocateRegistry.createRegistry(port,
                    RMISocketFactory.getDefaultSocketFactory(),
                    RMISocketFactory.getDefaultSocketFactory()));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testCreateRegistryIntRMIClientSocketFactoryRMIServerSocketFactory002() {
        try {
            LocateRegistry.createRegistry(port, RMISocketFactory
                    .getDefaultSocketFactory(), RMISocketFactory
                    .getDefaultSocketFactory());
            LocateRegistry.createRegistry(port, RMISocketFactory
                    .getDefaultSocketFactory(), RMISocketFactory
                    .getDefaultSocketFactory());
            fail("Two registry with same port is imposible");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testCreateRegistryIntRMIClientSocketFactoryRMIServerSocketFactory003() {
        try {
            LocateRegistry.createRegistry(-port, RMISocketFactory
                    .getDefaultSocketFactory(), RMISocketFactory
                    .getDefaultSocketFactory());
            fail("non negative number can't be port");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testCreateRegistryIntRMIClientSocketFactoryRMIServerSocketFactory004() {
        try {
            LocateRegistry.createRegistry(99999, RMISocketFactory
                    .getDefaultSocketFactory(), RMISocketFactory
                    .getDefaultSocketFactory());
            fail("The port is bounded of top");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testCreateRegistryIntRMIClientSocketFactoryRMIServerSocketFactory005() {
        try {
            assertNotNull(LocateRegistry.createRegistry(Registry.REGISTRY_PORT,
                    RMISocketFactory.getDefaultSocketFactory(),
                    RMISocketFactory.getDefaultSocketFactory()));
        } catch (RemoteException re) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testCreateRegistryIntRMIClientSocketFactoryRMIServerSocketFactory006() {
        try {
            assertNotNull(msgNotNull, LocateRegistry.createRegistry(port,
                    new SslRMIClientSocketFactory(), RMISocketFactory
                            .getDefaultSocketFactory()));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }

    }

    public void testCreateRegistryIntRMIClientSocketFactoryRMIServerSocketFactory007() {
        try {
            LocateRegistry.createRegistry(port,
                    new SslRMIClientSocketFactory(), RMISocketFactory
                            .getDefaultSocketFactory());
            LocateRegistry.createRegistry(port,
                    new SslRMIClientSocketFactory(), RMISocketFactory
                            .getDefaultSocketFactory());
            fail("Two registry with same port is imposible");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testCreateRegistryIntRMIClientSocketFactoryRMIServerSocketFactory008() {
        try {
            LocateRegistry.createRegistry(-port,
                    new SslRMIClientSocketFactory(), RMISocketFactory
                            .getDefaultSocketFactory());
            fail("non negative number can't be port");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testCreateRegistryIntRMIClientSocketFactoryRMIServerSocketFactory009() {
        try {
            LocateRegistry.createRegistry(99999,
                    new SslRMIClientSocketFactory(), RMISocketFactory
                            .getDefaultSocketFactory());
            fail("The port is bounded of top");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testCreateRegistryIntRMIClientSocketFactoryRMIServerSocketFactory010() {
        try {
            assertNotNull(LocateRegistry.createRegistry(Registry.REGISTRY_PORT,
                    new SslRMIClientSocketFactory(), RMISocketFactory
                            .getDefaultSocketFactory()));
        } catch (RemoteException re) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testCreateRegistryIntRMIClientSocketFactoryRMIServerSocketFactory011() {
        try {
            assertNotNull(msgNotNull, LocateRegistry.createRegistry(port,
                    RMISocketFactory.getDefaultSocketFactory(),
                    new SslRMIServerSocketFactory()));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }

    }

    public void testCreateRegistryIntRMIClientSocketFactoryRMIServerSocketFactory012() {
        try {
            LocateRegistry.createRegistry(port, RMISocketFactory
                    .getDefaultSocketFactory(), RMISocketFactory
                    .getDefaultSocketFactory());
            LocateRegistry.createRegistry(port, RMISocketFactory
                    .getDefaultSocketFactory(), RMISocketFactory
                    .getDefaultSocketFactory());
            fail("Two registry with same port is imposible");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testCreateRegistryIntRMIClientSocketFactoryRMIServerSocketFactory013() {
        try {
            LocateRegistry.createRegistry(-port, RMISocketFactory
                    .getDefaultSocketFactory(), RMISocketFactory
                    .getDefaultSocketFactory());
            fail("non negative number can't be port");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testCreateRegistryIntRMIClientSocketFactoryRMIServerSocketFactory014() {
        try {
            LocateRegistry.createRegistry(99999, RMISocketFactory
                    .getDefaultSocketFactory(), RMISocketFactory
                    .getDefaultSocketFactory());
            fail("The port is bounded of top");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testCreateRegistryIntRMIClientSocketFactoryRMIServerSocketFactory015() {
        try {
            assertNotNull(LocateRegistry.createRegistry(Registry.REGISTRY_PORT,
                    RMISocketFactory.getDefaultSocketFactory(),
                    new SslRMIServerSocketFactory()));
        } catch (RemoteException re) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testCreateRegistryIntRMIClientSocketFactoryRMIServerSocketFactory016() {
        try {
            assertNotNull(msgNotNull, LocateRegistry.createRegistry(port,
                    new SslRMIClientSocketFactory(),
                    new SslRMIServerSocketFactory()));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }

    }

    public void testCreateRegistryIntRMIClientSocketFactoryRMIServerSocketFactory017() {
        try {
            LocateRegistry.createRegistry(port,
                    new SslRMIClientSocketFactory(), RMISocketFactory
                            .getDefaultSocketFactory());
            LocateRegistry.createRegistry(port,
                    new SslRMIClientSocketFactory(), RMISocketFactory
                            .getDefaultSocketFactory());
            fail("Two registry with same port is imposible");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testCreateRegistryIntRMIClientSocketFactoryRMIServerSocketFactory018() {
        try {
            LocateRegistry.createRegistry(-port,
                    new SslRMIClientSocketFactory(), RMISocketFactory
                            .getDefaultSocketFactory());
            fail("non negative number can't be port");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testCreateRegistryIntRMIClientSocketFactoryRMIServerSocketFactory019() {
        try {
            LocateRegistry.createRegistry(99999,
                    new SslRMIClientSocketFactory(), RMISocketFactory
                            .getDefaultSocketFactory());
            fail("The port is bounded of top");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testCreateRegistryIntRMIClientSocketFactoryRMIServerSocketFactory020() {
        try {
            assertNotNull(LocateRegistry.createRegistry(Registry.REGISTRY_PORT,
                    new SslRMIClientSocketFactory(),
                    new SslRMIServerSocketFactory()));
            fail("Should raise RemoteException");
        } catch (RemoteException re) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testCreateRegistryIntRMIClientSocketFactoryRMIServerSocketFactory021() {
        try {
            assertNotNull(LocateRegistry.createRegistry(Registry.REGISTRY_PORT,
                    new SslRMIClientSocketFactory(),
                    new SslRMIServerSocketFactory()));
            fail("Should raise RemoteException");
        } catch (RemoteException re) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'java.rmi.registry.LocateRegistry.getRegistry()'
     */
    public void testGetRegistry001() {
        try {
            assertNotNull(LocateRegistry.getRegistry());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'java.rmi.registry.LocateRegistry.getRegistry(int)'
     */
    public void testGetRegistryInt001() {
        try {
            assertNotNull(msgNotNull, LocateRegistry.getRegistry(port));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testGetRegistryInt002() {
        try {
            assertNotNull(msgNotNull, LocateRegistry.getRegistry(port));
            assertNotNull(msgNotNull, LocateRegistry.getRegistry(port));

        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testGetRegistryInt003() {
        try {
            assertNotNull(msgNotNull, LocateRegistry.getRegistry(-port));

        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testGetRegistryInt004() {
        try {
            assertNotNull(msgNotNull, LocateRegistry.getRegistry(99999));

        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    // REGISTRY_PORT=1099
    public void testGetRegistryInt005() {
        try {
            assertNotNull(LocateRegistry.getRegistry(Registry.REGISTRY_PORT));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'java.rmi.registry.LocateRegistry.getRegistry(String)'
     */
    public void testGetRegistryString001() {
        try {
            assertNotNull(LocateRegistry.getRegistry("localhost"));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testGetRegistryString002() {
        try {
            assertNotNull(LocateRegistry.getRegistry("ltrsjyat"));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'java.rmi.registry.LocateRegistry.getRegistry(String,
     * int)'
     */
    public void testGetRegistryStringInt001() throws RemoteException,
            MalformedURLException {
        try {
            assertNotNull(LocateRegistry.getRegistry("ltrsjyat", 1));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testGetRegistryStringInt002() throws RemoteException,
            MalformedURLException {
        try {
            assertNotNull(LocateRegistry.getRegistry("ltrsjyat", 1000));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testGetRegistryStringInt003() throws RemoteException,
            MalformedURLException {
        try {
            assertNotNull(LocateRegistry.getRegistry("localhost", 1099));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testGetRegistryStringInt004() throws RemoteException,
            MalformedURLException {
        try {
            assertNotNull(LocateRegistry.getRegistry("localhost", -999));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testGetRegistryStringInt005() throws RemoteException,
            MalformedURLException {
        try {
            assertNotNull(LocateRegistry.getRegistry("localhost", 0));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'java.rmi.registry.LocateRegistry.getRegistry(String, int, RMIClientSocketFactory)'
     */
    public void testGetRegistryStringIntRMIClientSocketFactory001() {
        try {
            assertNotNull(LocateRegistry.getRegistry("ltrsjyat", 1,
                    new SslRMIClientSocketFactory()));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

}
