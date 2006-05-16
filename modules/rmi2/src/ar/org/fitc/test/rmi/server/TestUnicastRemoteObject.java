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

import java.rmi.NoSuchObjectException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.RMISocketFactory;
import java.rmi.server.RemoteObject;
import java.rmi.server.RemoteRef;
import java.rmi.server.RemoteStub;
import java.rmi.server.UnicastRemoteObject;

import ar.org.fitc.test.rmi.object2test.Echo;
// import ar.org.fitc.test.rmi.object2test.EchoActivatable_Imp;
import ar.org.fitc.test.rmi.object2test.EchoUnicast_Imp;
// import ar.org.fitc.test.rmi.object2test.EchoWithStubActivatable_Imp;
import ar.org.fitc.test.rmi.object2test.EchoWithStubUnicast_Imp;
import ar.org.fitc.test.rmi.object2test.EchoWithStub_Imp;
import ar.org.fitc.test.rmi.object2test.Echo_Imp;

public class TestUnicastRemoteObject extends TestRemoteServer {

    static boolean justDo = false;

    private class MyUnicastRemoteObject extends UnicastRemoteObject implements
            Cloneable {
        private static final long serialVersionUID = 1L;

        public MyUnicastRemoteObject(boolean b) throws RemoteException {
            super();
            if (b)
                this.ref = null;
        }

        public MyUnicastRemoteObject(RemoteRef r) throws RemoteException {
            super();
            this.ref = r;
        }

        public void nullRef() {
            ref = null;
        }
    }

    public RemoteObject constructor() {
        try {
            return new MyUnicastRemoteObject(true);
        } catch (RemoteException e) {
            fail("Couldn't create an UnicastRemoteObject");
            return null;
        }
    }

    public RemoteObject constructor(RemoteRef r) {
        try {
            return new MyUnicastRemoteObject(r);
        } catch (RemoteException e) {
            fail("Couldn't create an UnicastRemoteObject");
            return null;
        }
    }

    public synchronized void testToStub002() {
        // This test can't be done for UnicastRemoteObject
    }

    /*
     * Test method for 'java.rmi.server.UnicastRemoteObject.clone()'
     */

    /**
     * This test instance an object that extends UnicasRemoteObject and
     * implements clonable without ref. Checks that this object is not the same
     * as its clone.
     */
    public void testClone001() {
        try {
            MyUnicastRemoteObject uro = new MyUnicastRemoteObject(false);
            assertNotSame("A clone not is the same object", uro, uro.clone());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /**
     * This test instance an object that extends UnicasRemoteObject and
     * implements clonable without ref. Checks that this object is not equal as
     * its clone.
     */
    public void testClone002() {
        try {
            MyUnicastRemoteObject uro = new MyUnicastRemoteObject(false);
            assertFalse("A clone is not equal", uro.equals(uro.clone()));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /**
     * This test instance an object that extends UnicasRemoteObject and
     * implements clonable. Checks that this object is not the same as its
     * clone.
     */
    public void testClone003() {
        try {
            MyUnicastRemoteObject uro = new MyUnicastRemoteObject(true);
            assertNotSame("A clone not is the same object", uro, uro.clone());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /**
     * This test instance an object that extends UnicasRemoteObject and
     * implements clonable. Checks that this object is not equal as its clone.
     */
    public void testClone004() {
        try {
            MyUnicastRemoteObject uro = new MyUnicastRemoteObject(true);
            assertFalse("A clone is not equal", uro.equals(uro.clone()));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /**
     * This test instance an object that extends UnicasRemoteObject and does not
     * implements clonable. if a clone is called, CloneNotSupportedException is
     * thrown.
     */
    public void testClone005() {
        try {
            UnicastRemoteObject uro = new EchoUnicast_Imp();
            uro.clone();
            fail("clone not supported, this class does not implements Clonable");
        } catch (CloneNotSupportedException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for
     * 'java.rmi.server.UnicastRemoteObject.exportObject(Remote)'
     */

    public void testExportObjectRemote001() {
        try {
            UnicastRemoteObject.exportObject(new Echo_Imp());
            fail("There isn't stub");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemote002() {
        try {
            UnicastRemoteObject.exportObject(new EchoUnicast_Imp());
            fail("There isn't stub and object has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemote003() {
        try {
            UnicastRemoteObject.exportObject(new EchoWithStubUnicast_Imp());
            fail("object has been exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemote004() {
        try {
            UnicastRemoteObject.exportObject(null);
            fail("null shoud throw an exception");
        } catch (RemoteException e) {
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemote005() {
        try {
            Echo e = new EchoWithStub_Imp();
            RemoteStub rs = UnicastRemoteObject.exportObject(e);
            assertTrue("Must be an implementation of Echo", rs instanceof Echo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemote006() {
        try {
            Echo e = new EchoWithStub_Imp();
            RemoteStub rs = UnicastRemoteObject.exportObject(e);
            assertNotSame("Stub is not the object", rs, e);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemote007() {
        try {
            Echo e = new EchoWithStub_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Have said: " + msg;
                }
            };
            justDo = false;
            Remote r = UnicastRemoteObject.exportObject(e);
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemote008() {
        try {
            Echo e = new EchoWithStubUnicast_Imp();
            UnicastRemoteObject.exportObject(e);
            UnicastRemoteObject.exportObject(e);
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    // /**
    // * Exports an EchoActivatable object.
    // * This methods finishes with RemoteException,
    // * because there is not a stub and the object has just been exported
    // *
    // */
    //  
    // public void testExportObjectRemote009() {
    // try{
    // UnicastRemoteObject.exportObject(new EchoActivatable_Imp());
    // fail("There isn't stub and object has just exported");
    // } catch (RemoteException e) {
    // } catch (Throwable e) {
    // e.printStackTrace();
    // fail("Failed with:" + e);
    // }
    // }

    // /**
    // * Exports an EchoWithStubActivatable object.
    // * This methods finishes with RemoteException,
    // * because the object has just been exported.
    // *
    // */
    // public void testExportObjectRemote010() {
    // try{
    // UnicastRemoteObject.exportObject(new EchoWithStubActivatable_Imp());
    // fail("object has just been exported");
    // } catch (RemoteException e) {
    // } catch (Throwable e) {
    // fail("Failed with:" + e);
    // }
    // }

    /*
     * Test method for 'java.rmi.server.UnicastRemoteObject.exportObject(Remote,
     * int)'
     */

    public void testExportObjectRemoteInt001() {
        try {
            Echo e = new Echo_Imp();
            Remote r = UnicastRemoteObject.exportObject(e, 1100);
            assertTrue("The remote return is an instance of Echo",
                    r instanceof Echo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteInt002() {
        try {
            UnicastRemoteObject.exportObject(new EchoUnicast_Imp(), 1100);
            fail("Non export object who has just been exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteInt003() {
        try {
            UnicastRemoteObject.exportObject(null, 1100);
            fail("null make throws an exception");
        } catch (RuntimeException e) {
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteInt004() {
        try {
            Echo e = new Echo_Imp();
            Remote r = UnicastRemoteObject.exportObject(e, 1100);
            assertNotSame("Stub is not the object", r, e);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteInt005() {
        try {
            justDo = false;
            Echo e = new Echo_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Have said: " + msg;
                }
            };
            assertEquals("Non work is done yet", false, justDo);
            Remote r = UnicastRemoteObject.exportObject(e, 1100);
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just been done", true, justDo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteInt006() {
        try {
            justDo = false;
            Echo e = new EchoWithStub_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Have said: " + msg;
                }
            };
            assertEquals("Non work is done it yet", false, justDo);
            Remote r = UnicastRemoteObject.exportObject(e, 1100);
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just been done", true, justDo);
            justDo = false;
            ((Echo) r).echo("hi");
            assertEquals("Same work has just been done", r, RemoteObject
                    .toStub(e));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteInt007() {
        try {
            Echo e = new Echo_Imp();
            UnicastRemoteObject.exportObject(e, -1100);
            fail("Port should not be negative");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteInt008() {
        try {
            UnicastRemoteObject.exportObject(new EchoUnicast_Imp(), -111);
            fail("Or an exported object must raise an exception, or a negative port must raise an exception");
        } catch (RemoteException e) {
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteInt009() {
        try {
            Echo e = new Echo_Imp();
            UnicastRemoteObject.exportObject(e, 80);
            fail("Port is already in use, wait to permission denied");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteInt010() {
        try {
            Echo e = new Echo_Imp();
            UnicastRemoteObject.exportObject(e, 60340345);
            fail("port is to big, must be out of range");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    // /**
    // * Exports an EchoActivatable object form the port 1100.
    // * This method finishes with RemoteException,
    // * because the object has just been exported.
    // *
    // */
    // public void testExportObjectRemoteInt011() {
    // try{
    // UnicastRemoteObject.exportObject(new EchoActivatable_Imp(),1100);
    // fail("Non exportObject who has just been exported");
    // } catch (RemoteException e) {
    // } catch (Throwable e) {
    // fail("Failed with:" + e);
    // }
    // }

    // /**
    // * Exports an EchoWithStubActivatable object form the port 1100.
    // * This method finishes with RemoteException,
    // * because the object has just been exported.
    // *
    // */
    // public void testExportObjectRemoteInt012() {
    // try{
    // UnicastRemoteObject.exportObject(new EchoWithStubActivatable_Imp(),1100);
    // fail("Non exportObject who has just been exported");
    // } catch (RemoteException e) {
    // } catch (Throwable e) {
    // fail("Failed with:" + e);
    // }
    // }

    /**
     * Export a EchoWithStubUnicast object form the port 1100. This methods
     * finishes with RemoteException, because the object has just exported.
     * 
     */
    public void testExportObjectRemoteInt013() {
        try {
            UnicastRemoteObject.exportObject(new EchoWithStubUnicast_Imp(),
                    1100);
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'java.rmi.server.UnicastRemoteObject.exportObject(Remote,
     * int, RMIClientSocketFactory, RMIServerSocketFactory)'
     */

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory001() {
        try {
            Echo e = new Echo_Imp();
            Remote r = UnicastRemoteObject.exportObject(e, 1100,
                    RMISocketFactory.getSocketFactory(), RMISocketFactory
                            .getSocketFactory());
            assertTrue("The remote object returned is an instance of Echo",
                    r instanceof Echo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory002() {
        try {
            UnicastRemoteObject.exportObject(new EchoUnicast_Imp(), 1100,
                    RMISocketFactory.getSocketFactory(), RMISocketFactory
                            .getSocketFactory());
            fail("Non export object who has just been exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory003() {
        try {
            UnicastRemoteObject.exportObject(null, 1100, RMISocketFactory
                    .getSocketFactory(), RMISocketFactory.getSocketFactory());
            fail("null make throw an exception");
        } catch (RuntimeException e) {
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory004() {
        try {
            Echo e = new Echo_Imp();
            Remote r = UnicastRemoteObject.exportObject(e, 1100,
                    RMISocketFactory.getSocketFactory(), RMISocketFactory
                            .getSocketFactory());
            assertNotSame("Stub is not the object", r, e);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory005() {
        try {
            justDo = false;
            Echo e = new Echo_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Have said: " + msg;
                }
            };
            assertEquals("Non work is done it yet", false, justDo);
            Remote r = UnicastRemoteObject.exportObject(e, 1100,
                    RMISocketFactory.getSocketFactory(), RMISocketFactory
                            .getSocketFactory());
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just been done", true, justDo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory006() {
        try {
            justDo = false;
            Echo e = new Echo_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Have said: " + msg;
                }
            };
            assertEquals("Non work is done it yet", false, justDo);
            Remote r = UnicastRemoteObject.exportObject(e, 1100,
                    RMISocketFactory.getSocketFactory(), RMISocketFactory
                            .getSocketFactory());
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just been done", true, justDo);
            justDo = false;
            ((Echo) r).echo("hi");
            assertEquals("Same work has just been done", r, RemoteObject
                    .toStub(e));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory007() {
        try {
            Echo e = new Echo_Imp();
            UnicastRemoteObject.exportObject(e, -1100, RMISocketFactory
                    .getSocketFactory(), RMISocketFactory.getSocketFactory());
            fail("port shoud not be negative");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory008() {
        try {
            UnicastRemoteObject.exportObject(new EchoUnicast_Imp(), -111,
                    RMISocketFactory.getSocketFactory(), RMISocketFactory
                            .getSocketFactory());
            fail("Or object exported must raise an exception, or negative port must raise an exception");
        } catch (RemoteException e) {
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory009() {
        try {
            Echo e = new Echo_Imp();
            UnicastRemoteObject.exportObject(e, 80, RMISocketFactory
                    .getSocketFactory(), RMISocketFactory.getSocketFactory());
            fail("Port already in use, permission denied");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory010() {
        try {
            Echo e = new Echo_Imp();
            UnicastRemoteObject.exportObject(e, 60340345, RMISocketFactory
                    .getSocketFactory(), RMISocketFactory.getSocketFactory());
            fail("port is to big, must be out of range");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory011() {
        try {
            Echo e = new Echo_Imp();
            UnicastRemoteObject.exportObject(e, 60340345, RMISocketFactory
                    .getSocketFactory(), null);
            fail("port is to big, must be out of range");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory012() {
        try {
            Echo e = new Echo_Imp();
            Remote r = UnicastRemoteObject.exportObject(e, 1100,
                    RMISocketFactory.getSocketFactory(), null);
            assertTrue("The remote return is an instance of Echo",
                    r instanceof Echo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory013() {
        try {
            UnicastRemoteObject.exportObject(new EchoUnicast_Imp(), 1100,
                    RMISocketFactory.getSocketFactory(), null);
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory014() {
        try {
            UnicastRemoteObject.exportObject(null, 1100, RMISocketFactory
                    .getSocketFactory(), null);
            fail("null make throws an exception");
        } catch (RuntimeException e) {
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory015() {
        try {
            Echo e = new Echo_Imp();
            Remote r = UnicastRemoteObject.exportObject(e, 1100,
                    RMISocketFactory.getSocketFactory(), null);
            assertNotSame("Stub is not the object", r, e);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory016() {
        try {
            justDo = false;
            Echo e = new Echo_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Have said: " + msg;
                }
            };
            assertEquals("Non work is do it yet", false, justDo);
            Remote r = UnicastRemoteObject.exportObject(e, 1100,
                    RMISocketFactory.getSocketFactory(), null);
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory017() {
        try {
            justDo = false;
            Echo e = new Echo_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Have said: " + msg;
                }
            };
            assertEquals("Non work is do it yet", false, justDo);
            Remote r = UnicastRemoteObject.exportObject(e, 1100,
                    RMISocketFactory.getSocketFactory(), null);
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just been done", true, justDo);
            justDo = false;
            ((Echo) r).echo("hi");
            assertEquals("Same work has just been done", r, RemoteObject
                    .toStub(e));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory018() {
        try {
            Echo e = new Echo_Imp();
            UnicastRemoteObject.exportObject(e, -1100, RMISocketFactory
                    .getSocketFactory(), null);
            fail("Non negative number may be a port");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory019() {
        try {
            UnicastRemoteObject.exportObject(new EchoUnicast_Imp(), -111,
                    RMISocketFactory.getSocketFactory(), null);
            fail("Or object exported must raise a exception, or negative port must raise a exception");
        } catch (RemoteException e) {
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory020() {
        try {
            Echo e = new Echo_Imp();
            UnicastRemoteObject.exportObject(e, 80, RMISocketFactory
                    .getSocketFactory(), null);
            fail("Port already in use, wait to permission denied");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory021() {
        try {
            Echo e = new Echo_Imp();
            UnicastRemoteObject.exportObject(e, 60340345, null,
                    RMISocketFactory.getSocketFactory());
            fail("to big port, must be out of range");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory022() {
        try {
            Echo e = new Echo_Imp();
            Remote r = UnicastRemoteObject.exportObject(e, 1100, null,
                    RMISocketFactory.getSocketFactory());
            assertTrue("The remote return is instance of Echo",
                    r instanceof Echo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory023() {
        try {
            UnicastRemoteObject.exportObject(new EchoUnicast_Imp(), 1100, null,
                    RMISocketFactory.getSocketFactory());
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory024() {
        try {
            UnicastRemoteObject.exportObject(null, 1100, null, RMISocketFactory
                    .getSocketFactory());
            fail("null make throw a exception");
        } catch (RuntimeException e) {
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory025() {
        try {
            Echo e = new Echo_Imp();
            Remote r = UnicastRemoteObject.exportObject(e, 1100, null,
                    RMISocketFactory.getSocketFactory());
            assertNotSame("Stub is not the object", r, e);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory026() {
        try {
            justDo = false;
            Echo e = new Echo_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Have said: " + msg;
                }
            };
            assertEquals("Non work is do it yet", false, justDo);
            Remote r = UnicastRemoteObject.exportObject(e, 1100, null,
                    RMISocketFactory.getSocketFactory());
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just been done", true, justDo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory027() {
        try {
            justDo = false;
            Echo e = new Echo_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Have said: " + msg;
                }
            };
            assertEquals("Non work is do it yet", false, justDo);
            Remote r = UnicastRemoteObject.exportObject(e, 1100, null,
                    RMISocketFactory.getSocketFactory());
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just been done", true, justDo);
            justDo = false;
            ((Echo) r).echo("hi");
            assertEquals("Same work has just been done", r, RemoteObject
                    .toStub(e));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory028() {
        try {
            Echo e = new Echo_Imp();
            UnicastRemoteObject.exportObject(e, -1100, null, RMISocketFactory
                    .getSocketFactory());
            fail("port should not be negative");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory029() {
        try {
            UnicastRemoteObject.exportObject(new EchoUnicast_Imp(), -111, null,
                    RMISocketFactory.getSocketFactory());
            fail("Or object exported must raise an exception, or negative port must raise an exception");
        } catch (RemoteException e) {
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteIntRMIClientSocketFactoryRMIServerSocketFactory030() {
        try {
            Echo e = new Echo_Imp();
            UnicastRemoteObject.exportObject(e, 80, null, RMISocketFactory
                    .getSocketFactory());
            fail("Port already in use, wait to permission denied");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for
     * 'java.rmi.server.UnicastRemoteObject.unexportObject(Remote, boolean)'
     */

    /**
     * Unexport a Echo without force. This methods finishes with
     * NoSuchObjectException, because the object has not exported yet.
     * 
     */
    public void testUnexportObjectRemoteBoolean001() {
        Echo e = new Echo_Imp();
        try {
            UnicastRemoteObject.unexportObject(e, false);
            fail("The object is not exported ");
        } catch (NoSuchObjectException ex) {
        } catch (Throwable ex) {
            fail("Failed with:" + ex);
        }

    }

    /**
     * Unexport a Echo with stub without force. This methods finishes with
     * NoSuchObjectException, because the object has not exported yet.
     * 
     */
    public void testUnexportObjectRemoteBoolean002() throws RemoteException {
        Echo e = new EchoWithStub_Imp();
        try {
            UnicastRemoteObject.unexportObject(e, false);
            fail("The object is not exported ");
        } catch (NoSuchObjectException ex) {
        } catch (Throwable ex) {
            fail("Failed with:" + ex);
        }

    }

    /**
     * Unexport a Echo extends UnicastRemoteObject without force. This methods
     * finishes normally, and the object has not exported yet.
     * 
     */
    public void testUnexportObjectRemoteBoolean003() throws RemoteException {
        Echo e = new EchoUnicast_Imp();
        try {
            assertTrue("The object can't unexport", UnicastRemoteObject
                    .unexportObject(e, false));
        } catch (Throwable ex) {
            fail("Failed with:" + ex);
        }
        try {
            RemoteObject.toStub(e);
            fail("The object is not exported any more");
        } catch (NoSuchObjectException ex) {
        }
    }

    /**
     * Unexport a Echo extends UnicastRemoteObject with stub without force. This
     * methods finishes normally, and the object has not exported yet.
     * 
     */
    public void testUnexportObjectRemoteBoolean004() throws RemoteException {
        Echo e = new EchoWithStubUnicast_Imp();
        try {
            assertTrue("The object can't unexport", UnicastRemoteObject
                    .unexportObject(e, false));
        } catch (Throwable ex) {
            fail("Failed with:" + ex);
        }
        try {
            RemoteObject.toStub(e);
            fail("The object is not exported any more");
        } catch (NoSuchObjectException ex) {
        }
    }

    // /**
    // * Unexport an Echo extends Activatable without force.
    // * This methods finishes normally,
    // * and the object has not exported yet.
    // *
    // */
    // public void testUnexportObjectRemoteBoolean005() throws IOException {
    // Echo e = new EchoActivatable_Imp();
    // try {
    // assertTrue("The object can't unexport",
    // UnicastRemoteObject.unexportObject(e,false));
    // } catch (Throwable ex) {
    // fail("Failed with:" + ex);
    // }
    // try {
    // RemoteObject.toStub(e);
    // fail("The object is not exported any more");
    // } catch (NoSuchObjectException ex) {
    // }
    // }

    // /**
    // * Unexport an Echo extends Activatable with stub without force.
    // * This methods finishes normally,
    // * and the object has not exported yet.
    // *
    // */
    // public void testUnexportObjectRemoteBoolean006() throws IOException {
    // Echo e = new EchoWithStubActivatable_Imp();
    // try {
    // assertTrue("The object can't unexport",
    // UnicastRemoteObject.unexportObject(e,false));
    // } catch (Throwable ex) {
    // fail("Failed with:" + ex);
    // }
    // try {
    // RemoteObject.toStub(e);
    // fail("The object is not exported any more");
    // } catch (NoSuchObjectException ex) {
    // }
    // }

    /**
     * Unexport an Echo extends UnicastRemoteObject without force in the middle
     * of a call. This methods finishes normally, and the object continuous
     * exported.
     * 
     */

    public void testUnexportObjectRemoteBoolean007() throws RemoteException {
        Echo e = new EchoUnicast_Imp() {
            private static final long serialVersionUID = 1L;

            public String echo(String msg) throws RemoteException {
                msgCount += 1;
                try {
                    assertFalse("The object can't unexport",
                            UnicastRemoteObject.unexportObject(this, false));
                } catch (Throwable ex) {
                    fail("Failed with:" + ex);
                }
                return EchoUnicast_Imp.class + " - Have said: " + msg;
            }
        };

        ((Echo) RemoteObject.toStub(e)).echo("hello");

    }

    /**
     * Unexport an Echo extends UnicastRemoteObject with stub without force in
     * the middle of a call. This methods finishes normally, and the object
     * continuous exported.
     * 
     */
    public void testUnexportObjectRemoteBoolean008() throws RemoteException {
        Echo e = new EchoWithStubUnicast_Imp() {
            private static final long serialVersionUID = 1L;

            public String echo(String msg) throws RemoteException {
                // ("Se recibio mensaje: " + msg);
                msgCount += 1;
                try {
                    assertFalse("The object can't unexport",
                            UnicastRemoteObject.unexportObject(this, false));
                } catch (Throwable ex) {
                    fail("Failed with:" + ex);
                }
                return EchoUnicast_Imp.class + " - Have said: " + msg;
            }
        };

        ((Echo) RemoteObject.toStub(e)).echo("hello");

    }

    // /**
    // * Unexport a Echo extends Activatable without force in the middle of a
    // call.
    // * This methods finishes normally,
    // * and the object continuous exported.
    // *
    // */
    // public void testUnexportObjectRemoteBoolean009() throws IOException {
    // Echo e = new EchoActivatable_Imp() {
    // private static final long serialVersionUID = 1L;
    // public String echo(String msg) throws RemoteException {
    // msgCount += 1;
    // try {
    // assertFalse("The object can't unexport",
    // UnicastRemoteObject.unexportObject(this,false));
    // } catch (Throwable ex) {
    // fail("Failed with:" + ex);
    // }
    // return EchoUnicast_Imp.class + " - Have said: " + msg;
    // }
    // };
    //  
    // ((Echo)RemoteObject.toStub(e)).echo("hello");
    // }

    // /**
    // * Unexport a Echo extends Activatable with stub without force in the
    // middle of a call.
    // * This methods finishes normally,
    // * and the object continuous exported.
    // *
    // */
    // public void testUnexportObjectRemoteBoolean010() throws IOException {
    // Echo e = new EchoWithStubActivatable_Imp() {
    // private static final long serialVersionUID = 1L;
    // public String echo(String msg) throws RemoteException {
    //  
    // msgCount += 1;
    // try {
    // assertFalse("The object can't unexport",
    // UnicastRemoteObject.unexportObject(this,false));
    // } catch (Throwable ex) {
    // fail("Failed with:" + ex);
    // }
    // return EchoUnicast_Imp.class + " - Have said: " + msg;
    // }
    // };
    //  
    // ((Echo)RemoteObject.toStub(e)).echo("hello");
    // }

    /**
     * Unexport a Echo with force. This methods finishes with
     * NoSuchObjectException, because the object has not been exported yet.
     * 
     */
    public void testUnexportObjectRemoteBoolean011() {
        Echo e = new Echo_Imp();
        try {
            UnicastRemoteObject.unexportObject(e, true);
            fail("The object is not exported ");
        } catch (NoSuchObjectException ex) {
        } catch (Throwable ex) {
            fail("Failed with:" + ex);
        }

    }

    /**
     * Unexport a Echo with stub with force. This methods finishes with
     * NoSuchObjectException, because the object has not been exported yet.
     * 
     */
    public void testUnexportObjectRemoteBoolean012() throws RemoteException {
        Echo e = new EchoWithStub_Imp();
        try {
            UnicastRemoteObject.unexportObject(e, true);
            fail("The object is not exported ");
        } catch (NoSuchObjectException ex) {
        } catch (Throwable ex) {
            fail("Failed with:" + ex);
        }

    }

    /**
     * Unexport a Echo extends UnicastRemoteObject with force. This method
     * finishes normally, and the object has not been exported yet.
     * 
     */
    public void testUnexportObjectRemoteBoolean013() throws RemoteException {
        Echo e = new EchoUnicast_Imp();
        try {
            assertTrue("The object can't unexport", UnicastRemoteObject
                    .unexportObject(e, true));
        } catch (Throwable ex) {
            fail("Failed with:" + ex);
        }
        try {
            RemoteObject.toStub(e);
            fail("The object is not exported any more");
        } catch (NoSuchObjectException ex) {
        }
    }

    /**
     * Unexport a Echo extends UnicastRemoteObject with stub with force. This
     * methods finishes normally, and the object has not been exported yet.
     * 
     */
    public void testUnexportObjectRemoteBoolean014() throws RemoteException {
        Echo e = new EchoWithStubUnicast_Imp();
        try {
            assertTrue("The object can't unexport", UnicastRemoteObject
                    .unexportObject(e, true));
        } catch (Throwable ex) {
            fail("Failed with:" + ex);
        }
        try {
            RemoteObject.toStub(e);
            fail("The object is not exported any more");
        } catch (NoSuchObjectException ex) {
        }
    }

    // /**
    // * Unexports an Echo extends Activatable with force.
    // * This method finishes normally,
    // * and the object has not been exported yet.
    // *
    // */
    // public void testUnexportObjectRemoteBoolean015() throws IOException {
    // Echo e = new EchoActivatable_Imp();
    // try {
    // assertTrue("The object can't unexport",
    // UnicastRemoteObject.unexportObject(e,true));
    // } catch (Throwable ex) {
    // fail("Failed with:" + ex);
    // }
    // try {
    // RemoteObject.toStub(e);
    // fail("The object is not exported any more");
    // } catch (NoSuchObjectException ex) {
    // }
    // }

    // /**
    // * Unexports an Echo extends Activatable with stub with force.
    // * This method finishes normally,
    // * and the object has not been exported yet.
    // *
    // */
    // public void testUnexportObjectRemoteBoolean016() throws IOException {
    // Echo e = new EchoWithStubActivatable_Imp();
    // try {
    // assertTrue("The object can't unexport",
    // UnicastRemoteObject.unexportObject(e,true));
    // } catch (Throwable ex) {
    // fail("Failed with:" + ex);
    // }
    // try {
    // RemoteObject.toStub(e);
    // fail("The object is not exported any more");
    // } catch (NoSuchObjectException ex) {
    // }
    // }

    /**
     * Unexport a Echo extends UnicastRemoteObject without force in the middle
     * of a call. This method finishes normally, and the object has not been
     * exported yet.
     * 
     */
    public void testUnexportObjectRemoteBoolean017() throws RemoteException {
        Echo e = new EchoUnicast_Imp() {
            private static final long serialVersionUID = 1L;

            public String echo(String msg) throws RemoteException {

                msgCount += 1;
                try {
                    assertTrue("The object unexport", UnicastRemoteObject
                            .unexportObject(this, true));
                } catch (Throwable ex) {
                    fail("Failed with:" + ex);
                }
                return EchoUnicast_Imp.class + " - Have said: " + msg;
            }
        };

        ((Echo) RemoteObject.toStub(e)).echo("hello");
        try {
            RemoteObject.toStub(e);
            fail("The object is not exported any more");
        } catch (NoSuchObjectException ex) {
        }

    }

    /**
     * Unexport an Echo extends UnicastRemoteObject with stub with force in the
     * middle of a call. This method finishes normally, and the object has not
     * been exported yet.
     * 
     */
    public void testUnexportObjectRemoteBoolean018() throws RemoteException {
        Echo e = new EchoWithStubUnicast_Imp() {
            private static final long serialVersionUID = 1L;

            public String echo(String msg) throws RemoteException {

                msgCount += 1;
                try {
                    assertTrue("The object unexport", UnicastRemoteObject
                            .unexportObject(this, true));
                } catch (Throwable ex) {
                    fail("Failed with:" + ex);
                }
                return EchoUnicast_Imp.class + " - Have said: " + msg;
            }
        };

        ((Echo) RemoteObject.toStub(e)).echo("hello");
        try {
            RemoteObject.toStub(e);
            fail("The object is not exported any more");
        } catch (NoSuchObjectException ex) {
        }

    }

    // /**
    // * Unexports an Echo extends Activatable with force in the middle of a
    // call.
    // * This method finishes normally,
    // * and the object has not exported yet.
    // *
    // */
    // public void testUnexportObjectRemoteBoolean019() throws IOException {
    // Echo e = new EchoActivatable_Imp() {
    // private static final long serialVersionUID = 1L;
    // public String echo(String msg) throws RemoteException {
    //  
    // msgCount += 1;
    // try {
    // assertTrue("The object unexport",
    // UnicastRemoteObject.unexportObject(this,true));
    // } catch (Throwable ex) {
    // fail("Failed with:" + ex);
    // }
    // return EchoUnicast_Imp.class + " - Have said: " + msg;
    // }
    // };
    //  
    // ((Echo)RemoteObject.toStub(e)).echo("hello");
    // try {
    // RemoteObject.toStub(e);
    // fail("The object is not exported any more");
    // } catch (NoSuchObjectException ex) {
    // }
    // }
    // /**
    // * Unexports an Echo extends Activatable with stub with force in the
    // middle of a call.
    // * This method finishes normally,
    // * and the object has not exported yet.
    // *
    // */
    // public void testUnexportObjectRemoteBoolean020() throws IOException {
    // Echo e = new EchoWithStubActivatable_Imp() {
    // private static final long serialVersionUID = 1L;
    // public String echo(String msg) throws RemoteException {
    //  
    // msgCount += 1;
    // try {
    //  assertTrue("The object unexport", UnicastRemoteObject.unexportObject(this,true));
    //  } catch (Throwable ex) {
    //  fail("Failed with:" + ex);
    //  }
    //  return EchoUnicast_Imp.class + " - Have said: " + msg;
    //  }
    //  };
    //  
    //  ((Echo)RemoteObject.toStub(e)).echo("hello");
    //  try {
    //  RemoteObject.toStub(e);
    //  fail("The object is not exported any more");
    //  } catch (NoSuchObjectException ex) {
    //  }
    //  }

}
