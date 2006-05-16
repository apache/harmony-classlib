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

import java.lang.reflect.Proxy;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.UnexpectedException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteObject;
import java.rmi.server.RemoteObjectInvocationHandler;
import java.rmi.server.RemoteRef;
import java.rmi.server.RemoteStub;
import java.rmi.server.UnicastRemoteObject;
import junit.framework.TestCase;
import ar.org.fitc.test.rmi.object2test.BigEcho;
import ar.org.fitc.test.rmi.object2test.BigEcho_imp;
import ar.org.fitc.test.rmi.object2test.Echo;
import ar.org.fitc.test.rmi.object2test.EchoUnicast_Imp;
import ar.org.fitc.test.rmi.object2test.Echo_Imp;
import ar.org.fitc.test.rmi.server.testclasses.LoaderClassTest003;
import ar.org.fitc.test.rmi.server.testclasses.LoaderRemoteClassTest;
import ar.org.fitc.test.util.Messages;

public class TestRemoteObjectInvocationHandler extends TestCase implements
        Messages {

    private RemoteObjectInvocationHandler r;

    private Registry reg;

    protected void setUp() throws Exception {
        super.setUp();
        reg = LocateRegistry.createRegistry(1099);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        UnicastRemoteObject.unexportObject(reg, true);
    }

    /*
     * Test method for
     * 'java.rmi.server.RemoteObjectInvocationHandler.RemoteObjectInvocationHandler(RemoteRef)'
     */
    public final void testRemoteObjectInvocationHandler001() {
        try {

            new RemoteObjectInvocationHandler(null);
            fail("Shoud raise NullPointerException");
        } catch (NullPointerException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRemoteObjectInvocationHandler002() {
        try {
            RemoteRef lightStatusRef = null;

            new RemoteObjectInvocationHandler(lightStatusRef);
            fail("Shoud raise NullPointerException");
        } catch (NullPointerException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRemoteObjectInvocationHandler003()
            throws RemoteException {
        Registry registry = LocateRegistry.getRegistry();
        try {
            r = new RemoteObjectInvocationHandler(((RemoteStub) registry)
                    .getRef());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for
     * 'java.rmi.server.RemoteObjectInvocationHandler.invoke(Object, Method,
     * Object[])'
     */
    public final void testInvoke001() throws RemoteException {
        Registry registry = LocateRegistry.getRegistry();
        r = new RemoteObjectInvocationHandler(((RemoteStub) registry).getRef());
        LoaderClassTest003 test = new LoaderClassTest003();
        Object o[] = null;

        try {
            r.invoke(test, test.getClass().getMethod("proxy1"), o);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Should raise IllegalArgumentException but raised:" + e);
        }
    }

    public final void testInvoke002() throws RemoteException {
        Registry registry = LocateRegistry.getRegistry();
        r = new RemoteObjectInvocationHandler(((RemoteStub) registry).getRef());
        LoaderRemoteClassTest test = new LoaderRemoteClassTest();
        Object params[] = null;
        try {
            r.invoke(test, test.getClass().getMethod("getValue"), params);
            fail("Should raise UnexpectedException");
        } catch (UnexpectedException e) {
        } catch (Throwable e) {
            fail("Should raise UnexpectedException but raised:" + e);
        }
    }

    public final void testInvoke003() throws RemoteException {
        EchoUnicast_Imp eui = new EchoUnicast_Imp() {
            public String echo(String msg) throws RemoteException {
                return msg;
            }
        };
        RemoteRef ref = eui.getRef();
        Echo proxy = (Echo) RemoteObject.toStub(eui);
        try {
            r = new RemoteObjectInvocationHandler(ref);

            r.invoke(proxy, Echo.class.getMethods()[0], new Object[] {});

            fail("Same arguments is missing");
        } catch (RuntimeException e) {

        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testInvoke004() throws RemoteException {
        EchoUnicast_Imp eui = new EchoUnicast_Imp() {
            public String echo(String msg) throws RemoteException {
                return msg;
            }
        };
        RemoteRef ref = eui.getRef();
        Echo proxy = (Echo) RemoteObject.toStub(eui);
        String toSend = "hola invoquer";
        try {
            r = new RemoteObjectInvocationHandler(ref);
            assertEquals("The result is what we expect", toSend, r.invoke(
                    proxy, Echo.class.getMethods()[0], new Object[] { toSend }));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testInvoke005() throws RemoteException {
        EchoUnicast_Imp eui = new EchoUnicast_Imp() {
            public String echo(String msg) throws RemoteException {
                throw new RemoteException(msg);
            }
        };
        RemoteRef ref = eui.getRef();
        Echo proxy = (Echo) RemoteObject.toStub(eui);
        String toSend = "hola invoquer";
        try {
            r = new RemoteObjectInvocationHandler(ref);
            r
                    .invoke(proxy, Echo.class.getMethods()[0],
                            new Object[] { toSend });
        } catch (RemoteException e) {
            assertEquals("Exception must contain the msg", toSend, e.getCause()
                    .getMessage());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testInvoke006() throws RemoteException {
        Echo_Imp eui = new Echo_Imp() {
            public String echo(String msg) throws RemoteException {
                return msg;
            }
        };
        Remote proxy = UnicastRemoteObject.exportObject(eui, 1100);
        String toSend = "hola invoquer";
        r = (RemoteObjectInvocationHandler) Proxy.getInvocationHandler(proxy);
        try {
            assertEquals("The result is what we expect", toSend, r.invoke(
                    proxy, Echo.class.getMethods()[0], new Object[] { toSend }));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testInvoke007() throws RemoteException,
            AlreadyBoundException, NotBoundException {
        Echo_Imp eui = new Echo_Imp() {
            public String echo(String msg) throws RemoteException {
                throw new RemoteException(msg);
            }
        };
        Remote proxy = UnicastRemoteObject.exportObject(eui, 1100);
        String toSend = "hola invoquer";
        r = (RemoteObjectInvocationHandler) Proxy.getInvocationHandler(proxy);
        try {

            r
                    .invoke(proxy, Echo.class.getMethods()[0],
                            new Object[] { toSend });
        } catch (RemoteException e) {
            assertEquals("Exception must contain the msg", toSend, e.getCause()
                    .getMessage());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testInvoke008() throws RemoteException {
        EchoUnicast_Imp eui = new EchoUnicast_Imp() {
            public String echo(String msg) throws RemoteException {
                return msg;
            }
        };
        RemoteRef ref = eui.getRef();
        Echo proxy = (Echo) RemoteObject.toStub(eui);
        String toSend = "hola invoquer";
        try {
            r = new RemoteObjectInvocationHandler(ref);
            assertEquals("The result is what we expected", toSend, r.invoke(
                    proxy, Echo.class.getMethods()[0], new Object[] { toSend,
                            toSend }));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testInvoke009() throws RemoteException {
        EchoUnicast_Imp eui = new EchoUnicast_Imp() {
            public String echo(String msg) throws RemoteException {
                return msg;
            }
        };
        RemoteRef ref = eui.getRef();
        Echo proxy = (Echo) RemoteObject.toStub(eui);
        try {
            r = new RemoteObjectInvocationHandler(ref);
            r.invoke(proxy, Echo.class.getMethods()[0], null);
            fail("Same arguments are missing");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testInvoke010() throws RemoteException {
        BigEcho_imp bei = new BigEcho_imp();
        RemoteRef ref = bei.getRef();
        BigEcho proxy = (BigEcho) RemoteObject.toStub(bei);
        try {
            r = new RemoteObjectInvocationHandler(ref);
            assertNull(r.invoke(proxy, BigEcho.class.getMethod("echo"), null));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testInvoke011() throws RemoteException {
        BigEcho_imp bei = new BigEcho_imp() {
            public void echo() throws RemoteException {
                throw new RemoteException("Always pass throw here");
            }

            public void echo(Object... objs) throws RemoteException {
                if (objs == null) {
                    throw new RemoteException("Objs Null");
                }
            }
        };
        RemoteRef ref = bei.getRef();
        BigEcho proxy = (BigEcho) RemoteObject.toStub(bei);
        try {
            r = new RemoteObjectInvocationHandler(ref);
            assertNull(r.invoke(proxy, BigEcho.class.getMethods()[1],
                    new Object[] { new Object[] { 1, 2, 3, 4 } }));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testInvoke012() throws RemoteException {
        BigEcho_imp bei = new BigEcho_imp() {
            public void echo() throws RemoteException {
                throw new RemoteException("Always pass throw here");
            }

            public void echo(Object... objs) throws RemoteException {
                if (objs == null) {
                    throw new RemoteException("Objs Null");
                }
            }
        };
        RemoteRef ref = bei.getRef();
        BigEcho proxy = (BigEcho) RemoteObject.toStub(bei);
        try {
            r = new RemoteObjectInvocationHandler(ref);
            assertNull(r.invoke(proxy, BigEcho.class.getMethods()[1],
                    new Object[] { new Object[] {} }));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testInvoke013() throws RemoteException {
        EchoUnicast_Imp eui = new EchoUnicast_Imp() {
            public String echo(String msg) throws RemoteException {
                return msg;
            }
        };
        RemoteRef ref = eui.getRef();
        Echo proxy = (Echo) RemoteObject.toStub(eui);
        try {
            r = new RemoteObjectInvocationHandler(ref);
            r.invoke(proxy, Echo.class.getMethods()[0], new Object[] { 3 });
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testInvoke014() throws RemoteException {
        EchoUnicast_Imp eui = new EchoUnicast_Imp() {
            public String echo(String msg) throws RemoteException {
                return msg;
            }
        };
        RemoteRef ref = eui.getRef();
        Echo proxy = (Echo) RemoteObject.toStub(eui);
        try {
            r = new RemoteObjectInvocationHandler(ref);
            r.invoke(proxy, Echo.class.getMethods()[0], new Object[] { null });
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
}
