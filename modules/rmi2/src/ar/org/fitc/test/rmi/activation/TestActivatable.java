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
package ar.org.fitc.test.rmi.activation;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.activation.Activatable;
import java.rmi.activation.ActivationException;
import java.rmi.activation.ActivationID;
import java.rmi.server.RMISocketFactory;
import java.rmi.server.RemoteObject;
import ar.org.fitc.test.rmi.object2test.Echo;
import ar.org.fitc.test.rmi.object2test.EchoActivatable_Imp;
import ar.org.fitc.test.rmi.object2test.EchoUnicast_Imp;
import ar.org.fitc.test.rmi.object2test.EchoWithStubActivatable_Imp;
import ar.org.fitc.test.rmi.object2test.EchoWithStubUnicast_Imp;
import ar.org.fitc.test.rmi.object2test.EchoWithStub_Imp;
import ar.org.fitc.test.rmi.object2test.Echo_Imp;
import ar.org.fitc.test.rmi.object2test.MyActivator;
import ar.org.fitc.test.util.Messages;
import junit.framework.TestCase;


public class TestActivatable extends TestCase implements Messages {
    static boolean justDo = false;

    private MyActivator ac = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestActivatable.class);
    }

    public TestActivatable(String name) {
        super(name);
    }

    /*
     * private class MyActivatable extends Activatable { private static final
     * long serialVersionUID = 1L; private static ActivationID id; public
     * MyActivatable () throws RemoteException { super(id, 0);
     * 
     * this.ref = null; } public MyActivatable (RemoteRef r) throws
     * RemoteException { super(id, 0, null, null); this.ref = r; } public void
     * nullRef() { ref = null; } }
     * 
     * public RemoteObject constructor() { try { return new MyActivatable(true); }
     * catch (RemoteException e) { fail("Couldn't create an Activatable");
     * return null; } } public RemoteObject constructor(RemoteRef r) { try {
     * return new MyActivatable(r); } catch (RemoteException e) { fail("Couldn't
     * create an Activatable"); return null; } }
     */

    protected void setUp() throws Exception {
        super.setUp();
        ac = new MyActivator();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'java.rmi.activation.Activatable.unexportObject(Remote,
     * boolean)'
     */
    public void testUnexportObject() {

    }

    /*
     * Test method for
     * 'java.rmi.activation.Activatable.register(ActivationDesc)'
     */
    public void testRegister() {

    }

    /*
     * Test method for 'java.rmi.activation.Activatable.inactive(ActivationID)'
     */
    public void testInactive001() {
        try {
            Activatable.inactive(null);
            fail(msgRaise + "ActivationException");
        } catch (ActivationException ae) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testInactive002() {
        try {
            Activatable.inactive(new ActivationID(null));
            fail(msgRaise + "ActivationException");
        } catch (ActivationException ae) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testInactive003() {
        try {
            ActivationID aid = new ActivationID(ac);
            aid.activate(false);
            Activatable.inactive(aid);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for
     * 'java.rmi.activation.Activatable.unregister(ActivationID)'
     */
    public void testUnregister() {

    }

    /*
     * Test method for 'java.rmi.activation.Activatable.exportObject(Remote,
     * String, MarshalledObject, boolean, int)'
     */
    public void testExportObjectRemoteStringMarshalledObjectBooleanInt() {

    }

    /*
     * Test method for 'java.rmi.activation.Activatable.exportObject(Remote,
     * String, MarshalledObject, boolean, int, RMIClientSocketFactory,
     * RMIServerSocketFactory)'
     */
    public void testExportObjectRemoteStringMarshalledObjectBooleanIntRMIClientSocketFactoryRMIServerSocketFactory() {

    }

    /*
     * Test method for 'java.rmi.activation.Activatable.exportObject(Remote,
     * ActivationID, int)'
     */

    public void testExportObjectRemoteActivationIDInt001() {
        try {
            Activatable.exportObject(new Echo_Imp(), null, 0);
            fail("There isn't stub");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt002() {
        try {
            Activatable.exportObject(new EchoUnicast_Imp(), null, 0);
            fail("There isn't stub and object has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt003() {
        try {
            Activatable.exportObject(new EchoWithStubUnicast_Imp(), null, 0);
            fail("object has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt004() {
        try {
            Activatable.exportObject(null, null, 0);
            fail("null make throw a exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt005() {
        try {
            Echo e = new EchoWithStub_Imp();
            Remote r = Activatable.exportObject(e, null, 0);
            assertTrue("Must be a implement of Echo", r instanceof Echo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt006() {
        try {
            Echo e = new EchoWithStub_Imp();
            Remote r = Activatable.exportObject(e, null, 0);
            assertNotSame("Stub is not the object", r, e);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt007() {
        try {
            Echo e = new EchoWithStub_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            justDo = false;
            Remote r = Activatable.exportObject(e, null, 0);
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt008() {
        try {
            Echo e = new EchoWithStubUnicast_Imp();
            @SuppressWarnings("unused")
            Remote r = Activatable.exportObject(e, null, 0);
            Activatable.exportObject(e, null, 0);
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /**
     * Export a EchoActivatable object. This methods finish with
     * RemoteException, because there is not a stub and the object has just
     * exported
     * 
     */

    public void testExportObjectRemoteActivationIDInt009() {
        try {
            Activatable.exportObject(new EchoActivatable_Imp(), null, 0);
            fail("There isn't stub and object has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /**
     * Export a EchoWithStubActivatable object. This methods finish with
     * RemoteException, because the object has just exported.
     * 
     */
    public void testExportObjectRemoteActivationIDInt010() {
        try {
            Activatable
                    .exportObject(new EchoWithStubActivatable_Imp(), null, 0);
            fail("object has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt011() {
        try {
            Activatable.exportObject(new Echo_Imp(), new ActivationID(null), 0);
            fail("There isn't stub");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt012() {
        try {
            Activatable.exportObject(new EchoUnicast_Imp(), new ActivationID(
                    null), 0);
            fail("There isn't stub and object has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt013() {
        try {
            Activatable.exportObject(new EchoWithStubUnicast_Imp(),
                    new ActivationID(null), 0);
            fail("object has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt014() {
        try {
            Activatable.exportObject(null, new ActivationID(null), 0);
            fail("new ActivationID(null) make throw a exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt015() {
        try {
            Echo e = new EchoWithStub_Imp();
            Remote r = Activatable.exportObject(e, new ActivationID(null), 0);
            assertTrue("Must be a implement of Echo", r instanceof Echo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt016() {
        try {
            Echo e = new EchoWithStub_Imp();
            Remote r = Activatable.exportObject(e, new ActivationID(null), 0);
            assertNotSame("Stub is not the object", r, e);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt017() {
        try {
            Echo e = new EchoWithStub_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            justDo = false;
            Remote r = Activatable.exportObject(e, new ActivationID(null), 0);
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt018() {
        try {
            Echo e = new EchoWithStubUnicast_Imp();
            @SuppressWarnings("unused")
            Remote r = Activatable.exportObject(e, new ActivationID(null), 0);
            Activatable.exportObject(e, new ActivationID(null), 0);
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /**
     * Export a EchoActivatable object. This methods finish with
     * RemoteException, because there is not a stub and the object has just
     * exported
     * 
     */

    public void testExportObjectRemoteActivationIDInt019() {
        try {
            Activatable.exportObject(new EchoActivatable_Imp(),
                    new ActivationID(null), 0);
            fail("There isn't stub and object has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /**
     * Export a EchoWithStubActivatable object. This methods finish with
     * RemoteException, because the object has just exported.
     * 
     */
    public void testExportObjectRemoteActivationIDInt020() {
        try {
            Activatable.exportObject(new EchoWithStubActivatable_Imp(),
                    new ActivationID(null), 0);
            fail("object has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt021() {
        try {
            Activatable.exportObject(new Echo_Imp(), new ActivationID(ac), 0);
            fail("There isn't stub");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt022() {
        try {
            Activatable.exportObject(new EchoUnicast_Imp(),
                    new ActivationID(ac), 0);
            fail("There isn't stub and object has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt023() {
        try {
            Activatable.exportObject(new EchoWithStubUnicast_Imp(),
                    new ActivationID(ac), 0);
            fail("object has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt024() {
        try {
            Activatable.exportObject(null, new ActivationID(ac), 0);
            fail("null make throw a exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt025() {
        try {
            Echo e = new EchoWithStub_Imp();
            Remote r = Activatable.exportObject(e, new ActivationID(ac), 0);
            assertTrue("Must be a implement of Echo", r instanceof Echo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt026() {
        try {
            Echo e = new EchoWithStub_Imp();
            Remote r = Activatable.exportObject(e, new ActivationID(ac), 0);
            assertNotSame("Stub is not the object", r, e);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt027() {
        try {
            Echo e = new EchoWithStub_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            justDo = false;
            Remote r = Activatable.exportObject(e, new ActivationID(ac), 0);
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt028() {
        try {
            Echo e = new EchoWithStubUnicast_Imp();
            @SuppressWarnings("unused")
            Remote r = Activatable.exportObject(e, new ActivationID(ac), 0);
            Activatable.exportObject(e, null, 0);
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /**
     * Export a EchoActivatable object. This methods finish with
     * RemoteException, because there is not a stub and the object has just
     * exported
     * 
     */

    public void testExportObjectRemoteActivationIDInt029() {
        try {
            Activatable.exportObject(new EchoActivatable_Imp(),
                    new ActivationID(ac), 0);
            fail("There isn't stub and object has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /**
     * Export a EchoWithStubActivatable object. This methods finish with
     * RemoteException, because the object has just exported.
     * 
     */
    public void testExportObjectRemoteActivationIDInt030() {
        try {
            Activatable.exportObject(new EchoWithStubActivatable_Imp(),
                    new ActivationID(ac), 0);
            fail("object has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt031() {
        try {
            Activatable.exportObject(new Echo_Imp(), null, 139);
            fail("There isn't stub");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt032() {
        try {
            Activatable.exportObject(new EchoUnicast_Imp(), null, 139);
            fail("There isn't stub and object has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt033() {
        try {
            Activatable.exportObject(new EchoWithStubUnicast_Imp(), null, 139);
            fail("object has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt034() {
        try {
            Activatable.exportObject(null, null, 139);
            fail("null make throw a exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt035() {
        try {
            Echo e = new EchoWithStub_Imp();
            Remote r = Activatable.exportObject(e, null, 139);
            assertTrue("Must be a implement of Echo", r instanceof Echo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt036() {
        try {
            Echo e = new EchoWithStub_Imp();
            Remote r = Activatable.exportObject(e, null, 139);
            assertNotSame("Stub is not the object", r, e);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt037() {
        try {
            Echo e = new EchoWithStub_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            justDo = false;
            Remote r = Activatable.exportObject(e, null, 139);
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt038() {
        try {
            Echo e = new EchoWithStubUnicast_Imp();
            @SuppressWarnings("unused")
            Remote r = Activatable.exportObject(e, null, 139);
            Activatable.exportObject(e, null, 139);
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /**
     * Export a EchoActivatable object. This methods finish with
     * RemoteException, because there is not a stub and the object has just
     * exported
     * 
     */

    public void testExportObjectRemoteActivationIDInt039() {
        try {
            Activatable.exportObject(new EchoActivatable_Imp(), null, 139);
            fail("There isn't stub and object has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /**
     * Export a EchoWithStubActivatable object. This methods finish with
     * RemoteException, because the object has just exported.
     * 
     */
    public void testExportObjectRemoteActivationIDInt040() {
        try {
            Activatable.exportObject(new EchoWithStubActivatable_Imp(), null,
                    139);
            fail("object has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt041() {
        try {
            Activatable.exportObject(new Echo_Imp(), new ActivationID(null),
                    139);
            fail("There isn't stub");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt042() {
        try {
            Activatable.exportObject(new EchoUnicast_Imp(), new ActivationID(
                    null), 139);
            fail("There isn't stub and object has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt043() {
        try {
            Activatable.exportObject(new EchoWithStubUnicast_Imp(),
                    new ActivationID(null), 139);
            fail("object has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt044() {
        try {
            Activatable.exportObject(null, new ActivationID(null), 139);
            fail("new ActivationID(null) make throw a exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt045() {
        try {
            Echo e = new EchoWithStub_Imp();
            Remote r = Activatable.exportObject(e, new ActivationID(null), 139);
            assertTrue("Must be a implement of Echo", r instanceof Echo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt046() {
        try {
            Echo e = new EchoWithStub_Imp();
            Remote r = Activatable.exportObject(e, new ActivationID(null), 139);
            assertNotSame("Stub is not the object", r, e);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt047() {
        try {
            Echo e = new EchoWithStub_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            justDo = false;
            Remote r = Activatable.exportObject(e, new ActivationID(null), 139);
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt048() {
        try {
            Echo e = new EchoWithStubUnicast_Imp();
            @SuppressWarnings("unused")
            Remote r = Activatable.exportObject(e, new ActivationID(null), 139);
            Activatable.exportObject(e, new ActivationID(null), 139);
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /**
     * Export a EchoActivatable object. This methods finish with
     * RemoteException, because there is not a stub and the object has just
     * exported
     * 
     */

    public void testExportObjectRemoteActivationIDInt049() {
        try {
            Activatable.exportObject(new EchoActivatable_Imp(),
                    new ActivationID(null), 139);
            fail("There isn't stub and object has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /**
     * Export a EchoWithStubActivatable object. This methods finish with
     * RemoteException, because the object has just exported.
     * 
     */
    public void testExportObjectRemoteActivationIDInt050() {
        try {
            Activatable.exportObject(new EchoWithStubActivatable_Imp(),
                    new ActivationID(null), 139);
            fail("object has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt051() {
        try {
            Activatable.exportObject(new Echo_Imp(), new ActivationID(ac), 139);
            fail("There isn't stub");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt052() {
        try {
            Activatable.exportObject(new EchoUnicast_Imp(),
                    new ActivationID(ac), 139);
            fail("There isn't stub and object has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt053() {
        try {
            Activatable.exportObject(new EchoWithStubUnicast_Imp(),
                    new ActivationID(ac), 139);
            fail("object has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt054() {
        try {
            Activatable.exportObject(null, new ActivationID(ac), 139);
            fail("null make throw a exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt055() {
        try {
            Echo e = new EchoWithStub_Imp();
            Remote r = Activatable.exportObject(e, new ActivationID(ac), 139);
            assertTrue("Must be a implement of Echo", r instanceof Echo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt056() {
        try {
            Echo e = new EchoWithStub_Imp();
            Remote r = Activatable.exportObject(e, new ActivationID(ac), 139);
            assertNotSame("Stub is not the object", r, e);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt057() {
        try {
            Echo e = new EchoWithStub_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            justDo = false;
            Remote r = Activatable.exportObject(e, new ActivationID(ac), 139);
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt058() {
        try {
            Echo e = new EchoWithStubUnicast_Imp();
            @SuppressWarnings("unused")
            Remote r = Activatable.exportObject(e, new ActivationID(ac), 139);
            Activatable.exportObject(e, null, 139);
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /**
     * Export a EchoActivatable object. This methods finish with
     * RemoteException, because there is not a stub and the object has just
     * exported
     * 
     */

    public void testExportObjectRemoteActivationIDInt059() {
        try {
            Activatable.exportObject(new EchoActivatable_Imp(),
                    new ActivationID(ac), 139);
            fail("There isn't stub and object has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /**
     * Export a EchoWithStubActivatable object. This methods finish with
     * RemoteException, because the object has just exported.
     * 
     */
    public void testExportObjectRemoteActivationIDInt060() {
        try {
            Activatable.exportObject(new EchoWithStubActivatable_Imp(),
                    new ActivationID(ac), 139);
            fail("object has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt061() {
        try {
            Echo e = new Echo_Imp();
            Remote r = Activatable.exportObject(e, null, 1100);
            assertTrue("The remote return is instance of Echo",
                    r instanceof Echo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt062() {
        try {
            Activatable.exportObject(new EchoUnicast_Imp(), null, 1100);
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt063() {
        try {
            Activatable.exportObject(null, null, 1100);
            fail("null make throw a exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt064() {
        try {
            Echo e = new Echo_Imp();
            Remote r = Activatable.exportObject(e, null, 1100);
            assertNotSame("Stub is not the object", r, e);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt065() {
        try {
            justDo = false;
            Echo e = new Echo_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            assertEquals("Non work is do it yet", false, justDo);
            Remote r = Activatable.exportObject(e, null, 1100);
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt066() {
        try {
            justDo = false;
            Echo e = new EchoWithStub_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            assertEquals("Non work is do it yet", false, justDo);
            Remote r = Activatable.exportObject(e, null, 1100);
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
            justDo = false;
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", r, RemoteObject.toStub(e));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt067() {
        try {
            Echo e = new Echo_Imp();
            @SuppressWarnings("unused")
            Remote r = Activatable.exportObject(e, null, -1100);
            fail("Non negative number may be a port");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt068() {
        try {
            @SuppressWarnings("unused")
            Remote r = Activatable.exportObject(new EchoUnicast_Imp(), null,
                    -111);
            fail("Or object exported must raise a exception, or negative port must raise a exception");
        } catch (RemoteException e) {
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt069() {
        try {
            Echo e = new Echo_Imp();
            @SuppressWarnings("unused")
            Remote r = Activatable.exportObject(e, null, 80);
            fail("Port already in use, wait to permission denied");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt070() {
        try {
            Echo e = new Echo_Imp();
            @SuppressWarnings("unused")
            Remote r = Activatable.exportObject(e, null, 60340345);
            fail("to big port, must be out of range");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /**
     * Export a EchoActivatable object form the port 1100. This methods finish
     * with RemoteException, because the object has just exported.
     * 
     */
    public void testExportObjectRemoteActivationIDInt071() {
        try {
            Activatable.exportObject(new EchoActivatable_Imp(), null, 1100);
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /**
     * Export a EchoWithStubActivatable object form the port 1100. This methods
     * finish with RemoteException, because the object has just exported.
     * 
     */
    public void testExportObjectRemoteActivationIDInt072() {
        try {
            Activatable.exportObject(new EchoWithStubActivatable_Imp(), null,
                    1100);
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /**
     * Export a EchoWithStubUnicast object form the port 1100. This methods
     * finish with RemoteException, because the object has just exported.
     * 
     */
    public void testExportObjectRemoteActivationIDInt073() {
        try {
            Activatable.exportObject(new EchoWithStubUnicast_Imp(), null, 1100);
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt074() {
        try {
            Echo e = new Echo_Imp();
            Remote r = Activatable
                    .exportObject(e, new ActivationID(null), 1100);
            assertTrue("The remote return is instance of Echo",
                    r instanceof Echo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt075() {
        try {
            Activatable.exportObject(new EchoUnicast_Imp(), new ActivationID(
                    null), 1100);
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt076() {
        try {
            Activatable.exportObject(null, new ActivationID(null), 1100);
            fail("null make throw a exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt077() {
        try {
            Echo e = new Echo_Imp();
            Remote r = Activatable
                    .exportObject(e, new ActivationID(null), 1100);
            assertNotSame("Stub is not the object", r, e);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt078() {
        try {
            justDo = false;
            Echo e = new Echo_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            assertEquals("Non work is do it yet", false, justDo);
            Remote r = Activatable
                    .exportObject(e, new ActivationID(null), 1100);
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt079() {
        try {
            justDo = false;
            Echo e = new EchoWithStub_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            assertEquals("Non work is do it yet", false, justDo);
            Remote r = Activatable
                    .exportObject(e, new ActivationID(null), 1100);
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
            justDo = false;
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", r, RemoteObject.toStub(e));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt080() {
        try {
            Echo e = new Echo_Imp();
            @SuppressWarnings("unused")
            Remote r = Activatable.exportObject(e, new ActivationID(null),
                    -1100);
            fail("Non negative number may be a port");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt081() {
        try {
            @SuppressWarnings("unused")
            Remote r = Activatable.exportObject(new EchoUnicast_Imp(),
                    new ActivationID(null), -111);
            fail("Or object exported must raise a exception, or negative port must raise a exception");
        } catch (RemoteException e) {
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt082() {
        try {
            Echo e = new Echo_Imp();
            @SuppressWarnings("unused")
            Remote r = Activatable.exportObject(e, new ActivationID(null), 80);
            fail("Port already in use, wait to permission denied");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt083() {
        try {
            Echo e = new Echo_Imp();
            @SuppressWarnings("unused")
            Remote r = Activatable.exportObject(e, new ActivationID(null),
                    60340345);
            fail("to big port, must be out of range");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /**
     * Export a EchoActivatable object form the port 1100. This methods finish
     * with RemoteException, because the object has just exported.
     * 
     */
    public void testExportObjectRemoteActivationIDInt084() {
        try {
            Activatable.exportObject(new EchoActivatable_Imp(),
                    new ActivationID(null), 1100);
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /**
     * Export a EchoWithStubActivatable object form the port 1100. This methods
     * finish with RemoteException, because the object has just exported.
     * 
     */
    public void testExportObjectRemoteActivationIDInt085() {
        try {
            Activatable.exportObject(new EchoWithStubActivatable_Imp(),
                    new ActivationID(null), 1100);
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /**
     * Export a EchoWithStubUnicast object form the port 1100. This methods
     * finish with RemoteException, because the object has just exported.
     * 
     */
    public void testExportObjectRemoteActivationIDInt086() {
        try {
            Activatable.exportObject(new EchoWithStubUnicast_Imp(),
                    new ActivationID(null), 1100);
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt087() {
        try {
            Echo e = new Echo_Imp();
            Remote r = Activatable.exportObject(e, new ActivationID(ac), 1100);
            assertTrue("The remote return is instance of Echo",
                    r instanceof Echo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt088() {
        try {
            Activatable.exportObject(new EchoUnicast_Imp(),
                    new ActivationID(ac), 1100);
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt089() {
        try {
            Activatable.exportObject(null, new ActivationID(ac), 1100);
            fail("null make throw a exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt090() {
        try {
            Echo e = new Echo_Imp();
            Remote r = Activatable.exportObject(e, new ActivationID(ac), 1100);
            assertNotSame("Stub is not the object", r, e);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt091() {
        try {
            justDo = false;
            Echo e = new Echo_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            assertEquals("Non work is do it yet", false, justDo);
            Remote r = Activatable.exportObject(e, new ActivationID(ac), 1100);
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt092() {
        try {
            justDo = false;
            Echo e = new EchoWithStub_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            assertEquals("Non work is do it yet", false, justDo);
            Remote r = Activatable.exportObject(e, new ActivationID(ac), 1100);
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
            justDo = false;
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", r, RemoteObject.toStub(e));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt093() {
        try {
            Echo e = new Echo_Imp();
            @SuppressWarnings("unused")
            Remote r = Activatable.exportObject(e, new ActivationID(ac), -1100);
            fail("Non negative number may be a port");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt094() {
        try {
            @SuppressWarnings("unused")
            Remote r = Activatable.exportObject(new EchoUnicast_Imp(),
                    new ActivationID(ac), -111);
            fail("Or object exported must raise a exception, or negative port must raise a exception");
        } catch (RemoteException e) {
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt095() {
        try {
            Echo e = new Echo_Imp();
            @SuppressWarnings("unused")
            Remote r = Activatable.exportObject(e, new ActivationID(ac), 80);
            fail("Port already in use, wait to permission denied");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDInt096() {
        try {
            Echo e = new Echo_Imp();
            @SuppressWarnings("unused")
            Remote r = Activatable.exportObject(e, new ActivationID(ac),
                    60340345);
            fail("to big port, must be out of range");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /**
     * Export a EchoActivatable object form the port 1100. This methods finish
     * with RemoteException, because the object has just exported.
     * 
     */
    public void testExportObjectRemoteActivationIDInt097() {
        try {
            Activatable.exportObject(new EchoActivatable_Imp(),
                    new ActivationID(ac), 1100);
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /**
     * Export a EchoWithStubActivatable object form the port 1100. This methods
     * finish with RemoteException, because the object has just exported.
     * 
     */
    public void testExportObjectRemoteActivationIDInt098() {
        try {
            Activatable.exportObject(new EchoWithStubActivatable_Imp(),
                    new ActivationID(ac), 1100);
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /**
     * Export a EchoWithStubUnicast object form the port 1100. This methods
     * finish with RemoteException, because the object has just exported.
     * 
     */
    public void testExportObjectRemoteActivationIDInt099() {
        try {
            Activatable.exportObject(new EchoWithStubUnicast_Imp(),
                    new ActivationID(ac), 1100);
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'java.rmi.activation.Activatable.exportObject(Remote,
     * ActivationID, int, RMIClientSocketFactory, RMIServerSocketFactory)'
     */

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory001() {
        try {
            Echo e = new Echo_Imp();
            Remote r = Activatable.exportObject(e, null, 1100, RMISocketFactory
                    .getSocketFactory(), RMISocketFactory.getSocketFactory());
            assertTrue("The remote return is an instance of Echo",
                    r instanceof Echo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory002() {
        try {
            Activatable.exportObject(new EchoUnicast_Imp(), null, 1100,
                    RMISocketFactory.getSocketFactory(), RMISocketFactory
                            .getSocketFactory());
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory003() {
        try {
            Activatable.exportObject(null, null, 1100, RMISocketFactory
                    .getSocketFactory(), RMISocketFactory.getSocketFactory());
            fail("null make throw a exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory004() {
        try {
            Echo e = new Echo_Imp();
            Remote r = Activatable.exportObject(e, null, 1100, RMISocketFactory
                    .getSocketFactory(), RMISocketFactory.getSocketFactory());
            assertNotSame("Stub is not the object", r, e);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory005() {
        try {
            justDo = false;
            Echo e = new Echo_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            assertEquals("Non work is do it yet", false, justDo);
            Remote r = Activatable.exportObject(e, null, 1100, RMISocketFactory
                    .getSocketFactory(), RMISocketFactory.getSocketFactory());
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory006() {
        try {
            justDo = false;
            Echo e = new Echo_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            assertEquals("Non work is do it yet", false, justDo);
            Remote r = Activatable.exportObject(e, null, 1100, RMISocketFactory
                    .getSocketFactory(), RMISocketFactory.getSocketFactory());
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
            justDo = false;
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", r, RemoteObject.toStub(e));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory007() {
        try {
            Echo e = new Echo_Imp();
            Activatable.exportObject(e, null, -1100,
                    RMISocketFactory.getSocketFactory(), RMISocketFactory
                            .getSocketFactory());
            fail("Non negative number may be a port");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory008() {
        try {
            Activatable.exportObject(new EchoUnicast_Imp(), null,
                    -111, RMISocketFactory.getSocketFactory(), RMISocketFactory
                            .getSocketFactory());
            fail("Or object exported must raise a exception, or negative port must raise a exception");
        } catch (RemoteException e) {
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory009() {
        try {
            Echo e = new Echo_Imp();
           Activatable.exportObject(e, null, 80, RMISocketFactory
                    .getSocketFactory(), RMISocketFactory.getSocketFactory());
            fail("Port already in use, wait to permission denied");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory010() {
        try {
            Echo e = new Echo_Imp();
             Activatable.exportObject(e, null, 60340345,
                    RMISocketFactory.getSocketFactory(), RMISocketFactory
                            .getSocketFactory());
            fail("to big port, must be out of range");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory011() {
        try {
            Echo e = new Echo_Imp();
             Activatable.exportObject(e, null, 60340345,
                    RMISocketFactory.getSocketFactory(), null);
            fail("to big port, must be out of range");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory012() {
        try {
            Echo e = new Echo_Imp();
            Remote r = Activatable.exportObject(e, null, 1100, RMISocketFactory
                    .getSocketFactory(), null);
            assertTrue("The remote return is instance of Echo",
                    r instanceof Echo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory013() {
        try {
            Activatable.exportObject(new EchoUnicast_Imp(), null, 1100,
                    RMISocketFactory.getSocketFactory(), null);
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory014() {
        try {
            Activatable.exportObject(null, null, 1100, RMISocketFactory
                    .getSocketFactory(), null);
            fail("null make throw a exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory015() {
        try {
            Echo e = new Echo_Imp();
            Remote r = Activatable.exportObject(e, null, 1100, RMISocketFactory
                    .getSocketFactory(), null);
            assertNotSame("Stub is not the object", r, e);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory016() {
        try {
            justDo = false;
            Echo e = new Echo_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            assertEquals("Non work is do it yet", false, justDo);
            Remote r = Activatable.exportObject(e, null, 1100, RMISocketFactory
                    .getSocketFactory(), null);
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory017() {
        try {
            justDo = false;
            Echo e = new Echo_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            assertEquals("Non work is do it yet", false, justDo);
            Remote r = Activatable.exportObject(e, null, 1100, RMISocketFactory
                    .getSocketFactory(), null);
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
            justDo = false;
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", r, RemoteObject.toStub(e));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory018() {
        try {
            Echo e = new Echo_Imp();
            Activatable.exportObject(e, null, -1100,
                    RMISocketFactory.getSocketFactory(), null);
            fail("Non negative number may be a port");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory019() {
        try {
           Activatable.exportObject(new EchoUnicast_Imp(), null,
                    -111, RMISocketFactory.getSocketFactory(), null);
            fail("Or object exported must raise a exception, or negative port must raise a exception");
        } catch (RemoteException e) {
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory020() {
        try {
            Echo e = new Echo_Imp();
           Activatable.exportObject(e, null, 80, RMISocketFactory
                    .getSocketFactory(), null);
            fail("Port already in use, wait to permission denied");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory021() {
        try {
            Echo e = new Echo_Imp();
            Activatable.exportObject(e, null, 60340345, null,
                    RMISocketFactory.getSocketFactory());
            fail("to big port, must be out of range");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory022() {
        try {
            Echo e = new Echo_Imp();
            Remote r = Activatable.exportObject(e, null, 1100, null,
                    RMISocketFactory.getSocketFactory());
            assertTrue("The remote return is instance of Echo",
                    r instanceof Echo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory023() {
        try {
            Activatable.exportObject(new EchoUnicast_Imp(), null, 1100, null,
                    RMISocketFactory.getSocketFactory());
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory024() {
        try {
            Activatable.exportObject(null, null, 1100, null, RMISocketFactory
                    .getSocketFactory());
            fail("null make throw a exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory025() {
        try {
            Echo e = new Echo_Imp();
            Remote r = Activatable.exportObject(e, null, 1100, null,
                    RMISocketFactory.getSocketFactory());
            assertNotSame("Stub is not the object", r, e);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory026() {
        try {
            justDo = false;
            Echo e = new Echo_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            assertEquals("Non work is do it yet", false, justDo);
            Remote r = Activatable.exportObject(e, null, 1100, null,
                    RMISocketFactory.getSocketFactory());
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory027() {
        try {
            justDo = false;
            Echo e = new Echo_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            assertEquals("Non work is do it yet", false, justDo);
            Remote r = Activatable.exportObject(e, null, 1100, null,
                    RMISocketFactory.getSocketFactory());
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
            justDo = false;
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", r, RemoteObject.toStub(e));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory028() {
        try {
            Echo e = new Echo_Imp();
            Activatable.exportObject(e, null, -1100, null,
                    RMISocketFactory.getSocketFactory());
            fail("Non negative number may be a port");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory029() {
        try {
            Activatable.exportObject(new EchoUnicast_Imp(), null,
                    -111, null, RMISocketFactory.getSocketFactory());
            fail("Or object exported must raise a exception, or negative port must raise a exception");
        } catch (RemoteException e) {
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory030() {
        try {
            Echo e = new Echo_Imp();
            Activatable.exportObject(e, null, 80, null,
                    RMISocketFactory.getSocketFactory());
            fail("Port already in use, wait to permission denied");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory031() {
        try {
            Echo e = new Echo_Imp();
            Remote r = Activatable.exportObject(e, new ActivationID(null),
                    1100, RMISocketFactory.getSocketFactory(), RMISocketFactory
                            .getSocketFactory());
            assertTrue("The remote return is an instance of Echo",
                    r instanceof Echo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory032() {
        try {
            Activatable.exportObject(new EchoUnicast_Imp(), new ActivationID(
                    null), 1100, RMISocketFactory.getSocketFactory(),
                    RMISocketFactory.getSocketFactory());
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory033() {
        try {
            Activatable.exportObject(null, new ActivationID(null), 1100,
                    RMISocketFactory.getSocketFactory(), RMISocketFactory
                            .getSocketFactory());
            fail("null make throw a exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory034() {
        try {
            Echo e = new Echo_Imp();
            Remote r = Activatable.exportObject(e, new ActivationID(null),
                    1100, RMISocketFactory.getSocketFactory(), RMISocketFactory
                            .getSocketFactory());
            assertNotSame("Stub is not the object", r, e);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory035() {
        try {
            justDo = false;
            Echo e = new Echo_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            assertEquals("Non work is do it yet", false, justDo);
            Remote r = Activatable.exportObject(e, new ActivationID(null),
                    1100, RMISocketFactory.getSocketFactory(), RMISocketFactory
                            .getSocketFactory());
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory036() {
        try {
            justDo = false;
            Echo e = new Echo_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            assertEquals("Non work is do it yet", false, justDo);
            Remote r = Activatable.exportObject(e, new ActivationID(null),
                    1100, RMISocketFactory.getSocketFactory(), RMISocketFactory
                            .getSocketFactory());
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
            justDo = false;
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", r, RemoteObject.toStub(e));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory037() {
        try {
            Echo e = new Echo_Imp();
            Activatable.exportObject(e, new ActivationID(null),
                    -1100, RMISocketFactory.getSocketFactory(),
                    RMISocketFactory.getSocketFactory());
            fail("Non negative number may be a port");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory038() {
        try {
            Activatable.exportObject(new EchoUnicast_Imp(),
                    new ActivationID(null), -111, RMISocketFactory
                            .getSocketFactory(), RMISocketFactory
                            .getSocketFactory());
            fail("Or object exported must raise a exception, or negative port must raise a exception");
        } catch (RemoteException e) {
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory039() {
        try {
            Echo e = new Echo_Imp();
            Activatable.exportObject(e, new ActivationID(null), 80,
                    RMISocketFactory.getSocketFactory(), RMISocketFactory
                            .getSocketFactory());
            fail("Port already in use, wait to permission denied");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory040() {
        try {
            Echo e = new Echo_Imp();
            Activatable.exportObject(e, new ActivationID(null),
                    60340345, RMISocketFactory.getSocketFactory(),
                    RMISocketFactory.getSocketFactory());
            fail("to big port, must be out of range");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory041() {
        try {
            Echo e = new Echo_Imp();
            Activatable.exportObject(e, new ActivationID(null),
                    60340345, RMISocketFactory.getSocketFactory(), null);
            fail("to big port, must be out of range");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory042() {
        try {
            Echo e = new Echo_Imp();
            Remote r = Activatable.exportObject(e, new ActivationID(null),
                    1100, RMISocketFactory.getSocketFactory(), null);
            assertTrue("The remote return is instance of Echo",
                    r instanceof Echo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory043() {
        try {
            Activatable.exportObject(new EchoUnicast_Imp(), new ActivationID(
                    null), 1100, RMISocketFactory.getSocketFactory(), null);
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory044() {
        try {
            Activatable.exportObject(null, new ActivationID(null), 1100,
                    RMISocketFactory.getSocketFactory(), null);
            fail("null make throw a exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory045() {
        try {
            Echo e = new Echo_Imp();
            Remote r = Activatable.exportObject(e, new ActivationID(null),
                    1100, RMISocketFactory.getSocketFactory(), null);
            assertNotSame("Stub is not the object", r, e);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory046() {
        try {
            justDo = false;
            Echo e = new Echo_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            assertEquals("Non work is do it yet", false, justDo);
            Remote r = Activatable.exportObject(e, new ActivationID(null),
                    1100, RMISocketFactory.getSocketFactory(), null);
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory047() {
        try {
            justDo = false;
            Echo e = new Echo_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            assertEquals("Non work is do it yet", false, justDo);
            Remote r = Activatable.exportObject(e, new ActivationID(null),
                    1100, RMISocketFactory.getSocketFactory(), null);
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
            justDo = false;
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", r, RemoteObject.toStub(e));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory048() {
        try {
            Echo e = new Echo_Imp();
            Activatable.exportObject(e, new ActivationID(null),
                    -1100, RMISocketFactory.getSocketFactory(), null);
            fail("Non negative number may be a port");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory049() {
        try {
             Activatable.exportObject(new EchoUnicast_Imp(),
                    new ActivationID(null), -111, RMISocketFactory
                            .getSocketFactory(), null);
            fail("Or object exported must raise a exception, or negative port must raise a exception");
        } catch (RemoteException e) {
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory050() {
        try {
            Echo e = new Echo_Imp();
            Activatable.exportObject(e, new ActivationID(null), 80,
                    RMISocketFactory.getSocketFactory(), null);
            fail("Port already in use, wait to permission denied");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory051() {
        try {
            Echo e = new Echo_Imp();
            Activatable.exportObject(e, new ActivationID(null),
                    60340345, null, RMISocketFactory.getSocketFactory());
            fail("to big port, must be out of range");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory052() {
        try {
            Echo e = new Echo_Imp();
            Remote r = Activatable.exportObject(e, new ActivationID(null),
                    1100, null, RMISocketFactory.getSocketFactory());
            assertTrue("The remote return is instance of Echo",
                    r instanceof Echo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory053() {
        try {
            Activatable.exportObject(new EchoUnicast_Imp(), new ActivationID(
                    null), 1100, null, RMISocketFactory.getSocketFactory());
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory054() {
        try {
            Activatable.exportObject(null, new ActivationID(null), 1100, null,
                    RMISocketFactory.getSocketFactory());
            fail("null make throw a exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory055() {
        try {
            Echo e = new Echo_Imp();
            Remote r = Activatable.exportObject(e, new ActivationID(null),
                    1100, null, RMISocketFactory.getSocketFactory());
            assertNotSame("Stub is not the object", r, e);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory056() {
        try {
            justDo = false;
            Echo e = new Echo_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            assertEquals("Non work is do it yet", false, justDo);
            Remote r = Activatable.exportObject(e, new ActivationID(null),
                    1100, null, RMISocketFactory.getSocketFactory());
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory057() {
        try {
            justDo = false;
            Echo e = new Echo_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            assertEquals("Non work is do it yet", false, justDo);
            Remote r = Activatable.exportObject(e, new ActivationID(null),
                    1100, null, RMISocketFactory.getSocketFactory());
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
            justDo = false;
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", r, RemoteObject.toStub(e));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory058() {
        try {
            Echo e = new Echo_Imp();
            Activatable.exportObject(e, new ActivationID(null),
                    -1100, null, RMISocketFactory.getSocketFactory());
            fail("Non negative number may be a port");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory059() {
        try {
            Activatable.exportObject(new EchoUnicast_Imp(),
                    new ActivationID(null), -111, null, RMISocketFactory
                            .getSocketFactory());
            fail("Or object exported must raise a exception, or negative port must raise a exception");
        } catch (RemoteException e) {
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory060() {
        try {
            Echo e = new Echo_Imp();
            Activatable.exportObject(e, new ActivationID(null), 80,
                    null, RMISocketFactory.getSocketFactory());
            fail("Port already in use, wait to permission denied");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory061() {
        try {
            Echo e = new Echo_Imp();
            Remote r = Activatable.exportObject(e, new ActivationID(ac), 1100,
                    RMISocketFactory.getSocketFactory(), RMISocketFactory
                            .getSocketFactory());
            assertTrue("The remote return is an instance of Echo",
                    r instanceof Echo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory062() {
        try {
            Activatable.exportObject(new EchoUnicast_Imp(),
                    new ActivationID(ac), 1100, RMISocketFactory
                            .getSocketFactory(), RMISocketFactory
                            .getSocketFactory());
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory063() {
        try {
            Activatable.exportObject(null, new ActivationID(ac), 1100,
                    RMISocketFactory.getSocketFactory(), RMISocketFactory
                            .getSocketFactory());
            fail("null make throw a exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory064() {
        try {
            Echo e = new Echo_Imp();
            Remote r = Activatable.exportObject(e, new ActivationID(ac), 1100,
                    RMISocketFactory.getSocketFactory(), RMISocketFactory
                            .getSocketFactory());
            assertNotSame("Stub is not the object", r, e);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory065() {
        try {
            justDo = false;
            Echo e = new Echo_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            assertEquals("Non work is do it yet", false, justDo);
            Remote r = Activatable.exportObject(e, new ActivationID(ac), 1100,
                    RMISocketFactory.getSocketFactory(), RMISocketFactory
                            .getSocketFactory());
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory066() {
        try {
            justDo = false;
            Echo e = new Echo_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            assertEquals("Non work is do it yet", false, justDo);
            Remote r = Activatable.exportObject(e, new ActivationID(ac), 1100,
                    RMISocketFactory.getSocketFactory(), RMISocketFactory
                            .getSocketFactory());
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
            justDo = false;
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", r, RemoteObject.toStub(e));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory067() {
        try {
            Echo e = new Echo_Imp();
            Activatable.exportObject(e, new ActivationID(ac), -1100,
                    RMISocketFactory.getSocketFactory(), RMISocketFactory
                            .getSocketFactory());
            fail("Non negative number may be a port");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory068() {
        try {
            Activatable.exportObject(new EchoUnicast_Imp(),
                    new ActivationID(ac), -111, RMISocketFactory
                            .getSocketFactory(), RMISocketFactory
                            .getSocketFactory());
            fail("Or object exported must raise a exception, or negative port must raise a exception");
        } catch (RemoteException e) {
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory069() {
        try {
            Echo e = new Echo_Imp();
            Activatable.exportObject(e, new ActivationID(ac), 80,
                    RMISocketFactory.getSocketFactory(), RMISocketFactory
                            .getSocketFactory());
            fail("Port already in use, wait to permission denied");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory070() {
        try {
            Echo e = new Echo_Imp();
            Activatable.exportObject(e, new ActivationID(ac),
                    60340345, RMISocketFactory.getSocketFactory(),
                    RMISocketFactory.getSocketFactory());
            fail("to big port, must be out of range");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory071() {
        try {
            Echo e = new Echo_Imp();
            Activatable.exportObject(e, new ActivationID(ac),
                    60340345, RMISocketFactory.getSocketFactory(), null);
            fail("to big port, must be out of range");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory072() {
        try {
            Echo e = new Echo_Imp();
            Remote r = Activatable.exportObject(e, new ActivationID(ac), 1100,
                    RMISocketFactory.getSocketFactory(), null);
            assertTrue("The remote return is instance of Echo",
                    r instanceof Echo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory073() {
        try {
            Activatable.exportObject(new EchoUnicast_Imp(),
                    new ActivationID(ac), 1100, RMISocketFactory
                            .getSocketFactory(), null);
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory074() {
        try {
            Activatable.exportObject(null, new ActivationID(ac), 1100,
                    RMISocketFactory.getSocketFactory(), null);
            fail("null make throw a exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory075() {
        try {
            Echo e = new Echo_Imp();
            Remote r = Activatable.exportObject(e, new ActivationID(ac), 1100,
                    RMISocketFactory.getSocketFactory(), null);
            assertNotSame("Stub is not the object", r, e);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory076() {
        try {
            justDo = false;
            Echo e = new Echo_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            assertEquals("Non work is do it yet", false, justDo);
            Remote r = Activatable.exportObject(e, new ActivationID(ac), 1100,
                    RMISocketFactory.getSocketFactory(), null);
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory077() {
        try {
            justDo = false;
            Echo e = new Echo_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            assertEquals("Non work is do it yet", false, justDo);
            Remote r = Activatable.exportObject(e, new ActivationID(ac), 1100,
                    RMISocketFactory.getSocketFactory(), null);
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
            justDo = false;
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", r, RemoteObject.toStub(e));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory078() {
        try {
            Echo e = new Echo_Imp();
            Activatable.exportObject(e, new ActivationID(ac), -1100,
                    RMISocketFactory.getSocketFactory(), null);
            fail("Non negative number may be a port");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory079() {
        try {
            Activatable.exportObject(new EchoUnicast_Imp(),
                    new ActivationID(ac), -111, RMISocketFactory
                            .getSocketFactory(), null);
            fail("Or object exported must raise a exception, or negative port must raise a exception");
        } catch (RemoteException e) {
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory080() {
        try {
            Echo e = new Echo_Imp();
            Activatable.exportObject(e, null, 80, RMISocketFactory
                    .getSocketFactory(), null);
            fail("Port already in use, wait to permission denied");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory081() {
        try {
            Echo e = new Echo_Imp();
            Activatable.exportObject(e, new ActivationID(ac),
                    60340345, null, RMISocketFactory.getSocketFactory());
            fail("to big port, must be out of range");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory082() {
        try {
            Echo e = new Echo_Imp();
            Remote r = Activatable.exportObject(e, new ActivationID(ac), 1100,
                    null, RMISocketFactory.getSocketFactory());
            assertTrue("The remote return is instance of Echo",
                    r instanceof Echo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory083() {
        try {
            Activatable.exportObject(new EchoUnicast_Imp(),
                    new ActivationID(ac), 1100, null, RMISocketFactory
                            .getSocketFactory());
            fail("Non export object who has just exported");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory084() {
        try {
            Activatable.exportObject(null, new ActivationID(ac), 1100, null,
                    RMISocketFactory.getSocketFactory());
            fail("null make throw a exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory085() {
        try {
            Echo e = new Echo_Imp();
            Remote r = Activatable.exportObject(e, new ActivationID(ac), 1100,
                    null, RMISocketFactory.getSocketFactory());
            assertNotSame("Stub is not the object", r, e);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory086() {
        try {
            justDo = false;
            Echo e = new Echo_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            assertEquals("Non work is do it yet", false, justDo);
            Remote r = Activatable.exportObject(e, new ActivationID(ac), 1100,
                    null, RMISocketFactory.getSocketFactory());
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory087() {
        try {
            justDo = false;
            Echo e = new Echo_Imp() {
                public String echo(String msg) {
                    justDo = true;
                    return Echo_Imp.class + " - Ha dicho: " + msg;
                }
            };
            assertEquals("Non work is do it yet", false, justDo);
            Remote r = Activatable.exportObject(e, new ActivationID(ac), 1100,
                    null, RMISocketFactory.getSocketFactory());
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", true, justDo);
            justDo = false;
            ((Echo) r).echo("hi");
            assertEquals("Same work has just done", r, RemoteObject.toStub(e));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory088() {
        try {
            Echo e = new Echo_Imp();
            Activatable.exportObject(e, new ActivationID(ac), -1100,
                    null, RMISocketFactory.getSocketFactory());
            fail("Non negative number may be a port");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory089() {
        try {
            Activatable.exportObject(new EchoUnicast_Imp(),
                    new ActivationID(ac), -111, null, RMISocketFactory
                            .getSocketFactory());
            fail("Or object exported must raise a exception, or negative port must raise a exception");
        } catch (RemoteException e) {
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testExportObjectRemoteActivationIDIntRMIClientSocketFactoryRMIServerSocketFactory090() {
        try {
            Echo e = new Echo_Imp();
            Activatable.exportObject(e, new ActivationID(ac), 80,
                    null, RMISocketFactory.getSocketFactory());
            fail("Port already in use, wait to permission denied");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

}
