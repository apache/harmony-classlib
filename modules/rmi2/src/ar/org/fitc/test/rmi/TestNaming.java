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
package ar.org.fitc.test.rmi;

import java.net.MalformedURLException;

import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import junit.framework.TestCase;
import ar.org.fitc.test.rmi.object2test.Echo;
import ar.org.fitc.test.rmi.object2test.EchoUnicast_Imp;

class ORemote extends Object implements Remote {
}

public class TestNaming extends TestCase {

    private EchoUnicast_Imp e;

    String ipString = null;

    private static Registry reg = null;

    public static void main(String[] args) {
    }

    public TestNaming(String name) throws RemoteException {
        super(name);

        if (reg == null) {
            reg = LocateRegistry.createRegistry(1099);
        }
    }

    protected void setUp() throws Exception {
        super.setUp();

    }

    protected void tearDown() throws Exception {
        super.tearDown();

    }

    /*
     * Test method for 'java.rmi.Naming.bind(String, Remote)'
     */
    @SuppressWarnings("unchecked")
    public void testBind001() throws RemoteException, MalformedURLException,
            NotBoundException {
        try {
            e = new EchoUnicast_Imp();
            Naming.bind("echo", e);

            Collection<String> cs = null;
            cs = (Collection<String>) new CopyOnWriteArrayList(Naming.list(""));
            Iterator<String> it = cs.iterator();

            while (!it.next().endsWith("echo")) {
                assertTrue("In Naming List is Echo_Imp", it.hasNext());
            }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        } finally {

            Naming.unbind("echo");
        }

    }

    @SuppressWarnings("unchecked")
    public void testBind002() throws RemoteException, MalformedURLException,
            NotBoundException {
        try {
            e = new EchoUnicast_Imp();
            Naming.bind("//localhost:1099/echo", e);
            Collection<String> cs = null;
            cs = (Collection<String>) new CopyOnWriteArrayList(Naming.list(""));
            assertFalse("There is same thing in the list", cs.isEmpty());
            Iterator<String> it = cs.iterator();
            while (!it.next().endsWith("echo")) {

                assertTrue("In Naming List is Echo_Imp", it.hasNext());
            }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        } finally {
            Naming.unbind("echo");
        }
    }

    @SuppressWarnings("unchecked")
    public void testBind003() throws RemoteException, MalformedURLException,
            NotBoundException {
        try {
            e = new EchoUnicast_Imp();
            Naming.bind("//:1099/echo", e);
            Collection<String> cs = null;
            cs = (Collection<String>) new CopyOnWriteArrayList(Naming.list(""));
            Iterator<String> it = cs.iterator();
            while (!it.next().endsWith("echo")) {

                assertTrue("In Naming List is Echo_Imp", it.hasNext());
            }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        } finally {

            Naming.unbind("echo");
        }
    }

    @SuppressWarnings("unchecked")
    public void testBind004() throws RemoteException, MalformedURLException,
            NotBoundException {
        try {
            e = new EchoUnicast_Imp();
            Naming.bind("//localhost/echo", e);
            Collection<String> cs = null;
            cs = (Collection<String>) new CopyOnWriteArrayList(Naming.list(""));
            Iterator<String> it = cs.iterator();
            while (!it.next().endsWith("echo")) {

                assertTrue("In Naming List is Echo_Imp", it.hasNext());
            }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        } finally {

            Naming.unbind("echo");
        }
    }

    public void testBind005() throws RemoteException, MalformedURLException,
            NotBoundException {
        try {
            EchoUnicast_Imp e = new EchoUnicast_Imp();
            Naming.bind("echo", e);
            Naming.bind("echo", e);
            fail("Two bind to the same port make a exception that is missing");
        } catch (AlreadyBoundException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        } finally {
            Naming.unbind("echo");
        }

    }

    public void testBind006() {
        try {
            e = new EchoUnicast_Imp();
            Naming.bind("???··~~$$echo", e);
            fail("The URL is mal formed");
        } catch (MalformedURLException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testBind008() {
        try {
            e = new EchoUnicast_Imp();
            Naming.bind(null, e);
            fail("The URL can't be null");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testBind009() {
        try {
            Naming.bind("echo", null);
            fail("The remoteObject can't be null");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testBind010() {
        try {
            Naming.bind(null, null);
            fail("The remoteObject and URL can't be null");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testBind011() {

        try {
            Naming.bind("echo", new ORemote());
            fail("The remoteObject can't be null");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'java.rmi.Naming.list(String)'
     */
    public void testList001() {
        try {
            assertEquals("Must be an empty array", 0, Naming.list("").length);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testList002() throws RemoteException, MalformedURLException,
            NotBoundException {
        try {
            e = new EchoUnicast_Imp();
            Naming.bind("echo", e);
            assertEquals("Must be an unitary array", 1, Naming.list("").length);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        } finally {
            Naming.unbind("echo");
        }
    }

    public void testList003() throws RemoteException, MalformedURLException,
            NotBoundException {
        try {
            e = new EchoUnicast_Imp();
            for (int i = 0; i < 30; i++) {
                Naming.bind("echo" + i, e);
            }
            assertEquals("Must be an unitary array", 30, Naming.list("").length);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        } finally {
            for (int i = 0; i < 30; i++) {
                Naming.unbind("echo" + i);
            }
        }
    }

    public void testList004() {
        try {
            Naming.list(null);
            fail("Can't execute with null String");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testList005() {
        try {
            Naming.list("???··~~$$echo");
            fail("The URL is mal formed");
        } catch (MalformedURLException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'java.rmi.Naming.lookup(String)'
     */
    public void testLookup001() {
        try {
            Naming.lookup("echo");
            fail("Non bind object");
        } catch (NotBoundException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testLookup002() throws RemoteException, MalformedURLException,
            NotBoundException {
        try {

            e = new EchoUnicast_Imp();
            Naming.bind("echo", e);
            Echo f = (Echo) Naming.lookup("echo");
            assertTrue("Must be an unitary array", f instanceof Echo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        } finally {
            Naming.unbind("echo");
        }

    }

    public void testLookup003() {
        try {
            Naming.lookup("#$%$%echo");
            fail("Mal formed URL");
        } catch (MalformedURLException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'java.rmi.Naming.unbind(String)'
     */
    public void testUnbind001() {
        try {
            e = new EchoUnicast_Imp();
            Naming.bind("echo", e);

            Naming.unbind("echo");
            assertEquals("Must be a empty array", 0, Naming.list("").length);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }

    }

    public void testUnbind002() {
        try {
            e = new EchoUnicast_Imp();
            Naming.bind("//localhost:1099/echo", e);
            Naming.unbind("echo");
            assertEquals("Must be an empty array", 0, Naming.list("").length);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    @SuppressWarnings("unchecked")
    public void testUnbind003() {
        try {
            e = new EchoUnicast_Imp();
            Naming.bind("//:1099/echo", e);
            Naming.unbind("echo");
            assertEquals("Must be an empty array", 0, Naming.list("").length);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    @SuppressWarnings("unchecked")
    public void testUnbind004() {
        try {
            e = new EchoUnicast_Imp();
            Naming.bind("//localhost/echo", e);
            Naming.unbind("echo");
            assertEquals("Must be an empty array", 0, Naming.list("").length);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        } finally {

        }
    }

    public void testUnbind005() {
        try {
            Naming.unbind(":$#$%echo");
        } catch (MalformedURLException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testUnbind006() {
        try {
            Naming.unbind(null);
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testUnbind007() {
        try {
            Naming.unbind("echo");
        } catch (NotBoundException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'java.rmi.Naming.rebind(String, Remote)'
     */
    @SuppressWarnings("unchecked")
    public void testRebind001() throws RemoteException, MalformedURLException,
            NotBoundException {
        try {
            e = new EchoUnicast_Imp();
            Naming.rebind("echo", e);

            Collection<String> cs = null;
            cs = (Collection<String>) new CopyOnWriteArrayList(Naming.list(""));
            Iterator<String> it = cs.iterator();

            while (!it.next().endsWith("echo")) {
                assertTrue("In Naming List is Echo_Imp", it.hasNext());
            }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        } finally {

            Naming.unbind("echo");
        }

    }

    @SuppressWarnings("unchecked")
    public void testRebind002() throws RemoteException, MalformedURLException,
            NotBoundException {
        try {
            e = new EchoUnicast_Imp();
            Naming.rebind("//localhost:1099/echo", e);
            Collection<String> cs = null;
            cs = (Collection<String>) new CopyOnWriteArrayList(Naming.list(""));
            assertFalse("There is same thing in the list", cs.isEmpty());
            Iterator<String> it = cs.iterator();
            while (!it.next().endsWith("echo")) {

                assertTrue("In Naming List is Echo_Imp", it.hasNext());
            }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        } finally {

            Naming.unbind("echo");
        }
    }

    @SuppressWarnings("unchecked")
    public void testRebind003() throws RemoteException, MalformedURLException,
            NotBoundException {
        try {
            e = new EchoUnicast_Imp();
            Naming.rebind("//:1099/echo", e);
            Collection<String> cs = null;
            cs = (Collection<String>) new CopyOnWriteArrayList(Naming.list(""));
            Iterator<String> it = cs.iterator();
            while (!it.next().endsWith("echo")) {

                assertTrue("In Naming List is Echo_Imp", it.hasNext());
            }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        } finally {

            Naming.unbind("echo");
        }
    }

    @SuppressWarnings("unchecked")
    public void testRebind004() throws RemoteException, MalformedURLException,
            NotBoundException {
        try {
            e = new EchoUnicast_Imp();
            Naming.rebind("//localhost/echo", e);
            Collection<String> cs = null;
            cs = (Collection<String>) new CopyOnWriteArrayList(Naming.list(""));
            Iterator<String> it = cs.iterator();
            while (!it.next().endsWith("echo")) {

                assertTrue("In Naming List is Echo_Imp", it.hasNext());
            }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        } finally {

            Naming.unbind("echo");
        }
    }

    public void testRebind005() throws RemoteException, MalformedURLException,
            NotBoundException {
        try {
            EchoUnicast_Imp e = new EchoUnicast_Imp();
            Naming.rebind("echo", e);
            Naming.rebind("echo", e);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        } finally {
            Naming.unbind("echo");
        }
    }

    public void testRebind006() {
        try {
            e = new EchoUnicast_Imp();
            Naming.rebind("???··~~$$echo", e);
            fail("The URL is mal formed");
        } catch (MalformedURLException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testRebind008() {
        try {
            e = new EchoUnicast_Imp();
            Naming.rebind(null, e);
            fail("The URL can't be null");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testRebind009() {
        try {
            Naming.rebind("echo", null);
            fail("The remoteObject can't be null");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testRebind010() {
        try {
            Naming.rebind(null, null);
            fail("The remoteObject and URL can't be null");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testRebind011() {
        try {
            Naming.rebind("echo", new ORemote());
            fail("The remoteObject can't be null");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

}
