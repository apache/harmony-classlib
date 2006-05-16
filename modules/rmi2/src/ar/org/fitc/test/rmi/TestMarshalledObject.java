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

import java.io.IOException;
import java.io.Serializable;
import java.rmi.MarshalledObject;
import java.rmi.RMISecurityManager;

import junit.framework.TestCase;
import ar.org.fitc.test.util.Messages;

public class TestMarshalledObject extends TestCase implements Messages {

    public static void main(String[] args) {
    }

    public TestMarshalledObject(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'java.rmi.MarshalledObject.MarshalledObject(Object)'
     */
    public void testMarshalledObject001() {
        try {
            assertNotNull(msgNotNull, new MarshalledObject(new Double(23.4)));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testMarshalledObject002() {
        try {
            assertNotNull(msgNotNull, new MarshalledObject(null));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testMarshalledObject003() {
        try {
            new MarshalledObject(new RMISecurityManager());
            fail(msgRaise + " IOException");
        } catch (IOException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'java.rmi.MarshalledObject.get()'
     */
    public void testGet001() {
        try {
            Serializable s = new Integer(3);
            MarshalledObject m = new MarshalledObject(s);
            assertEquals("must be equals", s, m.get());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testGet002() {
        try {
            MarshalledObject m = new MarshalledObject(null);
            assertNull("must be null", m.get());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testGet003() {
        try {
            Serializable s = new Integer(3);
            MarshalledObject m = new MarshalledObject(s);
            assertNotSame("must not be same ", m.get(), m.get());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testGet004() {
        try {
            Serializable s = new Integer(3);
            MarshalledObject m = new MarshalledObject(s);
            assertEquals("must not be same ", m.get(), m.get());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testGet005() {
        try {
            Serializable s = new Integer(3);
            MarshalledObject m = new MarshalledObject(s);
            assertNotSame("must not be same ", s, m.get());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'java.rmi.MarshalledObject.equals(Object)'
     */
    public void testEquals001() {
        try {
            Serializable s = new Integer(3);
            MarshalledObject m = new MarshalledObject(s);
            MarshalledObject m2 = new MarshalledObject(s);
            assertTrue("must be equals", m2.equals(m));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testEquals002() {
        try {
            MarshalledObject m = new MarshalledObject(null);
            MarshalledObject m2 = new MarshalledObject(null);
            assertTrue("must be equals", m2.equals(m));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testEquals003() {
        try {
            MarshalledObject m = new MarshalledObject(new Integer(3));
            MarshalledObject m2 = new MarshalledObject(new Integer(4));
            assertFalse("must not be equals", m2.equals(m));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testEquals004() {
        try {
            MarshalledObject m = new MarshalledObject(new Integer(3));
            assertFalse("must not be equals", m.equals(null));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'java.rmi.MarshalledObject.hashCode()'
     */
    public void testHashCode001() {
        try {
            Serializable s = new Integer(3);
            MarshalledObject m = new MarshalledObject(s);
            MarshalledObject m2 = new MarshalledObject(s);
            int hc = m.hashCode();
            for (int i = 1; i < 15; i++) {
                assertEquals("must have same hashCode", m2.hashCode(), hc);
            }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testHashCode002() {
        try {
            MarshalledObject m = new MarshalledObject(null);
            MarshalledObject m2 = new MarshalledObject(null);
            int hc = m.hashCode();
            for (int i = 1; i < 15; i++) {
                assertEquals("must have same hashCode", m2.hashCode(), hc);
            }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public void testHashCode003() {
        try {
            MarshalledObject m = new MarshalledObject(new Integer(3));
            MarshalledObject m2 = new MarshalledObject(new Integer(4));
            assertFalse("must not have same hashCode", m2.hashCode() == m
                    .hashCode());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }

    }

}
