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
import java.rmi.server.RemoteObject;
import java.rmi.server.RemoteRef;
import junit.framework.TestCase;
import ar.org.fitc.test.rmi.object2test.Echo;
import ar.org.fitc.test.rmi.object2test.EchoWithStubUnicast_Imp;
import ar.org.fitc.test.rmi.object2test.EchoUnicast_Imp;
import ar.org.fitc.test.rmi.object2test.MyRemoteRef;

public class TestRemoteObject extends TestCase {

    private class MyRemoteObject extends RemoteObject {
        private static final long serialVersionUID = -1684084133953032001L;

        public MyRemoteObject(RemoteRef r) {
            super(r);
        }

        public MyRemoteObject() {
            super();
        }
    }

    public RemoteObject constructor() {
        return new MyRemoteObject();
    }

    public RemoteObject constructor(RemoteRef r) {
        return new MyRemoteObject(r);
    }

    /*
     * Test method for 'java.rmi.server.RemoteObject.getRef()'
     */
    public void testGetRef001() {
        try {
            RemoteObject ro = constructor();
            assertNull("not ref, make getRef to return null", ro.getRef());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testGetRef002() {
        try {
            RemoteRef rr = new MyRemoteRef();
            RemoteObject ro = constructor(rr);
            assertSame("getRef return initial RemoteRef", rr, ro.getRef());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'java.rmi.server.RemoteObject.equals(Object)'
     */
    public void testEquals001() {
        try {
            RemoteObject ro = constructor();
            assertEquals("getRef return initial RemoteRef", ro.equals(ro), true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testEquals002() {
        try {
            RemoteObject ro = constructor();
            assertEquals("getRef return initial RemoteRef", ro
                    .equals(constructor()), false);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testEquals003() {
        try {
            MyRemoteRef rr = new MyRemoteRef();
            rr.bRemoteEquals = false;
            assertEquals("equals is ref.remoteEquals", constructor(rr).equals(
                    constructor()), rr.bRemoteEquals);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testEquals004() {
        try {
            MyRemoteRef rr = new MyRemoteRef();
            rr.bRemoteEquals = true;
            assertEquals("equals is ref.remoteEquals", constructor(rr).equals(
                    constructor()), rr.bRemoteEquals);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testEquals005() {
        try {
            MyRemoteRef rr = new MyRemoteRef();
            rr.bRemoteEquals = false;
            assertEquals("equals is ref.remoteEquals", constructor(rr).equals(
                    new EchoUnicast_Imp()), rr.bRemoteEquals);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testEquals006() {
        try {
            MyRemoteRef rr = new MyRemoteRef();
            rr.bRemoteEquals = true;
            assertEquals("equals is ref.remoteEquals", constructor(rr).equals(
                    new EchoUnicast_Imp()), rr.bRemoteEquals);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testEquals007() {
        try {
            MyRemoteRef rr = new MyRemoteRef();
            rr.bRemoteEquals = false;
            assertEquals("equals is ref.remoteEquals", constructor(rr).equals(
                    constructor(rr)), rr.bRemoteEquals);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testEquals008() {
        try {
            MyRemoteRef rr = new MyRemoteRef();
            rr.bRemoteEquals = true;
            assertEquals("equals is ref.remoteEquals", constructor(rr).equals(
                    constructor(rr)), rr.bRemoteEquals);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testEquals009() {
        try {
            MyRemoteRef rr = new MyRemoteRef();
            rr.bRemoteEquals = false;
            assertEquals("equals is ref.remoteEquals", constructor(rr).equals(
                    null), rr.bRemoteEquals);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testEquals010() {
        try {
            MyRemoteRef rr = new MyRemoteRef();
            rr.bRemoteEquals = true;
            assertEquals("equals is ref.remoteEquals", constructor(rr).equals(
                    null), false);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'java.rmi.server.RemoteObject.hashCode()'
     */
    public void testHashCode001() {
        try {
            RemoteObject ro = constructor();
            assertEquals("HashCode may preserve", ro.hashCode(), ro.hashCode());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }

    }

    public void testHashCode002() {
        try {
            MyRemoteRef rr = new MyRemoteRef();
            RemoteObject ro = constructor(rr);
            rr.iHashCode = -5;
            assertEquals("hashCode is ref.remoteHashCode", ro.hashCode(),
                    rr.iHashCode);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }

    }

    public void testHashCode003() {
        try {
            MyRemoteRef rr = new MyRemoteRef();
            RemoteObject ro = constructor(rr);
            rr.iHashCode = 100123012;
            assertEquals("hashCode is ref.remoteHashCode", ro.hashCode(),
                    rr.iHashCode);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }

    }

    /*
     * Test method for 'java.rmi.server.RemoteObject.toString()'
     */
    public void testToString001() {
        try {
            RemoteObject ro = constructor();
            assertEquals("toString must be equals RemoteObject casting", ro
                    .toString(), "" + ro);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }

    }

    public void testToString002() {
        try {
            MyRemoteRef rr = new MyRemoteRef();
            RemoteObject ro = constructor(rr);
            rr.sToString = "Hello";
            assertTrue("toString contains remoteToString", ro.toString()
                    .contains(rr.sToString));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }

    }

    public void testToString003() {
        try {
            MyRemoteRef rr = new MyRemoteRef();
            RemoteObject ro = constructor(rr);
            rr.sToString = "";
            assertTrue("toString contains remoteToString", ro.toString()
                    .contains(rr.sToString));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }

    }

    public void testToString004() {
        try {
            MyRemoteRef rr = new MyRemoteRef();
            RemoteObject ro = constructor(rr);
            rr.sToString = "This is a long phrase, very very long, sufficient like bothering a little.  I Repite :"
                    + "This is a long phrase, very very long, sufficient like bothering a little.  I Repite :"
                    + "This is a long phrase, very very long, sufficient like bothering a little.  I Repite ";
            assertTrue("toString contains remoteToString", ro.toString()
                    .contains(rr.sToString));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }

    }

    /*
     * Test method for 'java.rmi.server.RemoteObject.toStub(Remote)'
     */
    public void testToStub001() {
        try {
            Echo e = new EchoUnicast_Imp();
            Remote r = RemoteObject.toStub(e);
            assertTrue("The remote return is an instance of Echo",
                    r instanceof Echo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testToStub002() {
        try {
            RemoteObject.toStub(constructor());
            fail("Non export object make throw a exception");
        } catch (NoSuchObjectException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testToStub003() {
        try {
            RemoteObject.toStub(null);
            fail("null make throw a exception");
        } catch (NoSuchObjectException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testToStub004() {
        try {
            Echo e = new EchoUnicast_Imp();
            Remote r = RemoteObject.toStub(e);
            assertNotSame("Stub is not the object", r, e);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testToStub005() {
        try {
            Echo e = new EchoUnicast_Imp();
            assertEquals("Message count initial 0", 0,
                    ((EchoUnicast_Imp) e).msgCount);
            Remote r = RemoteObject.toStub(e);
            assertNotSame("Stub is not the object", r, e);
            ((Echo) r).echo("hi");
            assertEquals("Message count finally 1", 1,
                    ((EchoUnicast_Imp) e).msgCount);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testToStub006() {
        try {
            Echo e = new EchoWithStubUnicast_Imp();
            Remote r = RemoteObject.toStub(e);
            assertEquals("Stub is not the object", e, r);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
}
