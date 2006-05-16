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

import java.rmi.RemoteException;
import java.rmi.activation.ActivationException;
import java.rmi.activation.ActivationID;
import java.rmi.activation.UnknownObjectException;

import junit.framework.TestCase;
import ar.org.fitc.test.rmi.object2test.MyActivator;
import ar.org.fitc.test.util.Messages;

public class TestActivationID extends TestCase implements Messages {

    private MyActivator ac = null;

    private ActivationID at;

    public static void main(String[] args) {
    }

    protected void setUp() throws Exception {
        super.setUp();
        ac = new MyActivator();
        at = new ActivationID(ac);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'java.rmi.activation.ActivationID.ActivationID(Activator)'
     */
    public void testActivationID001() {

        assertNotNull(msgNotNull, new ActivationID(ac));

    }

    public void testActivationID002() {

        assertNotNull(msgNotNull, new ActivationID(null));

    }

    /*
     * Test method for 'java.rmi.activation.ActivationID.equals(Object)'
     */
    public void testEquals001() {
        assertTrue("must be equals", at.equals(at));

    }

    public void testEquals002() {
        assertFalse("mustn't be equals", at.equals(null));
    }

    public void testEquals003() {
        assertFalse("mustn't be equals", at.equals(new ActivationID(ac)));
    }

    public void testEquals004() {
        ActivationID at2 = new ActivationID(ac);
        assertEquals("must be equals", at2.equals(at), at.equals(at2));

    }

    public void testEquals005() {
        Object o = new Object();
        assertFalse("must not be equals", at.equals(o));
    }

    public void testEquals006() {
        assertFalse("must not be equals", at.equals(null));
    }

    public void testEquals007() {
        for (int i = 1; i < 10; i++) {
            assertTrue("must be equals", at.equals(at));
        }
    }

    public void testEquals008() {
        for (int i = 1; i < 10; i++) {
            assertFalse("mustn't be equals", at.equals(null));
        }
    }

    public void testEquals009() {
        for (int i = 1; i < 10; i++) {
            assertFalse("mustn't be equals", at.equals(new ActivationID(ac)));
        }
    }

    public void testEquals010() {
        ActivationID at2 = new ActivationID(ac);
        for (int i = 1; i < 10; i++) {
            assertEquals("must be equals", at2.equals(at), at.equals(at2));
        }

    }

    public void testEquals011() {

        Object o = new Object();
        for (int i = 1; i < 10; i++) {
            assertFalse("must not be equals", at.equals(o));
        }

    }

    public void testEquals012() {

        for (int i = 1; i < 10; i++) {
            assertFalse("must not be equals", at.equals(null));
        }

    }

    /*
     * Test method for 'java.rmi.activation.ActivationID.hashCode()'
     */
    public void testHashCode001() {
        ActivationID at2 = new ActivationID(ac);
        assertNotSame("must not be equals", at.hashCode(), at2.hashCode());
    }

    public void testHashCode002() {
        int hc = at.hashCode();
        for (int i = 1; i < 15; i++) {
            assertEquals("must be equals", at.hashCode(), hc);
        }
    }

    /*
     * Test method for 'java.rmi.activation.ActivationID.activate(boolean)'
     */
    public void testActivate001() {
        try {
            at.activate(false);
            assertFalse("The same parameter are give to activator", ac.b);
            fail("Null marshallObject make a RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }

    }

    public void testActivate002() {
        try {
            at.activate(true);
            assertTrue("The same parameter are give to activator", ac.b);
            fail("Null marshallObject make a RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }

    }

    public void testActivate003() {
        try {
            ac.i = MyActivator.ActivationE;
            at.activate(false);
            assertFalse("The same parameter are give to activator", ac.b);
            fail("Null marshallObject make a RuntimeException");
        } catch (ActivationException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testActivate004() {
        try {
            ac.i = MyActivator.ActivationE;
            at.activate(true);
            assertTrue("The same parameter are give to activator", ac.b);
            fail("Null marshallObject make a RuntimeException");
        } catch (ActivationException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }

    }

    public void testActivate005() {
        try {
            ac.i = MyActivator.RemoteE;
            at.activate(false);
            assertFalse("The same parameter are give to activator", ac.b);
            fail("Null marshallObject make a RuntimeException");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }

    }

    public void testActivate006() {
        try {
            ac.i = MyActivator.RemoteE;
            at.activate(true);
            assertTrue("The same parameter are give to activator", ac.b);
            fail("Null marshallObject make a RuntimeException");
        } catch (RemoteException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }

    }

    public void testActivate007() {
        try {
            ac.i = MyActivator.UnknownObjectE;
            at.activate(false);
            assertFalse("The same parameter are give to activator", ac.b);
            fail("Null marshallObject make a RuntimeException");
        } catch (UnknownObjectException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }

    }

    public void testActivate008() {
        try {
            ac.i = MyActivator.UnknownObjectE;
            at.activate(true);
            assertTrue("The same parameter are give to activator", ac.b);
            fail("Null marshallObject make a RuntimeException");
        } catch (UnknownObjectException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }

    }

    public void testActivate009() {
        try {
            ac.i = MyActivator.MySelfInMarshalledObjectR;
            assertEquals(at.activate(false), ac);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testActivate010() {
        try {
            ac.i = MyActivator.MySelfInMarshalledObjectR;
            assertEquals(at.activate(true), ac);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testActivate011() {
        try {
            ac.i = MyActivator.MySelfInMarshalledObjectR;
            assertNotSame(at.activate(false), ac);
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }

    }

    public void testActivate012() {
        try {
            ac.i = MyActivator.MySelfInMarshalledObjectR;
            assertNotSame(at.activate(true), ac);
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }
}
