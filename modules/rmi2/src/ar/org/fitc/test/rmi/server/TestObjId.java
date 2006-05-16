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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.rmi.server.ObjID;

import junit.framework.TestCase;
import ar.org.fitc.test.util.Messages;

public class TestObjId extends TestCase implements Messages {
    ObjID oid = null;

    int i = 0;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestObjId.class);
    }

    public TestObjId(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'java.rmi.server.ObjID.ObjID()'
     */
    public final void testObjID001() {
        assertNotNull(msgNotNull, new ObjID());
    }

    public final void testObjID002() {
        oid = new ObjID();
        for (int i = 0; i < 15; i++) {
            assertNotSame(oid.toString(), new ObjID().toString());
        }
    }

    /*
     * Test method for 'java.rmi.server.ObjID.ObjID(int)'
     */
    public final void testObjIDInt001() {
        assertNotNull(msgNotNull, new ObjID(i));
    }

    public final void testObjIDInt002() {
        i = Integer.MIN_VALUE;
        assertNotNull(msgNotNull, new ObjID(i));
    }

    public final void testObjIDInt003() {
        i = Integer.MAX_VALUE;
        assertNotNull(msgNotNull, new ObjID(i));
    }

    public final void testObjIDInt004() {
        i = 5594;
        assertNotNull(msgNotNull, new ObjID(i));
    }

    public final void testObjIDInt005() {
        i = 0;
        oid = new ObjID(i);
        for (int i = 0; i < 15; i++) {
            assertNotSame(oid.toString(), new ObjID(i).toString());
        }
    }

    public final void testObjIDInt006() {
        i = Integer.MIN_VALUE;
        oid = new ObjID(i);
        for (int i = 0; i < 15; i++) {
            assertNotSame(oid.toString(), new ObjID(i).toString());
        }
    }

    public final void testObjIDInt007() {
        i = Integer.MAX_VALUE;
        oid = new ObjID(i);
        for (int i = 0; i < 15; i++) {
            assertNotSame(oid.toString(), new ObjID(i).toString());
        }
    }

    public final void testObjIDInt008() {
        i = 5594;
        oid = new ObjID(i);
        for (int i = 0; i < 15; i++) {
            assertNotSame(oid.toString(), new ObjID(i).toString());
        }
    }

    /*
     * Test method for 'java.rmi.server.ObjID.hashCode()'
     */
    public final void testHashCode001() {
        assertNotSame("must not be equals", new ObjID().hashCode(), new ObjID()
                .hashCode());
    }

    public final void testHashCode002() {
        oid = new ObjID();
        int hc = oid.hashCode();
        for (int i = 1; i < 15; i++) {
            assertEquals("must be equals", oid.hashCode(), hc);
        }
    }

    public final void testHashCode003() {
        i = 555;
        assertNotSame("must not be equals", new ObjID(i).hashCode(),
                new ObjID().hashCode());
    }

    public final void testHashCode004() {
        i = 555;
        oid = new ObjID();
        int hc = oid.hashCode();
        for (int i = 1; i < 15; i++) {
            assertEquals("must be equals", oid.hashCode(), hc);
        }
    }

    /*
     * Test method for 'java.rmi.server.ObjID.equals(Object)'
     */
    public void testEquals001() {

        oid = new ObjID();
        assertTrue("must be equals", oid.equals(oid));

    }

    public void testEquals002() {
        oid = new ObjID();
        assertFalse("mustn't be equals", oid.equals(null));

    }

    public void testEquals003() {
        oid = new ObjID();
        assertFalse("mustn't be equals", oid.equals(new ObjID()));

    }

    public void testEquals004() {
        oid = new ObjID();
        ObjID uid2 = new ObjID();
        assertEquals("must be equals", uid2.equals(oid), oid.equals(uid2));

    }

    public void testEquals005() {
        oid = new ObjID();
        Object o = new Object();
        assertFalse("must not be equals", oid.equals(o));

    }

    public void testEquals006() {
        oid = new ObjID();
        assertFalse("must not be equals", oid.equals(null));

    }

    public void testEquals007() {
        oid = new ObjID();
        for (int i = 1; i < 10; i++) {
            assertTrue("must be equals", oid.equals(oid));
        }

    }

    public void testEquals008() {
        oid = new ObjID();
        for (int i = 1; i < 10; i++) {
            assertFalse("mustn't be equals", oid.equals(null));
        }

    }

    public void testEquals009() {
        oid = new ObjID();
        for (int i = 1; i < 10; i++) {
            assertFalse("mustn't be equals", oid.equals(new ObjID()));
        }

    }

    public void testEquals010() {
        oid = new ObjID();
        ObjID uid2 = new ObjID();
        for (int i = 1; i < 10; i++) {
            assertEquals("must be equals", uid2.equals(oid), oid.equals(uid2));
        }

    }

    public void testEquals011() {
        oid = new ObjID();
        Object o = new Object();
        for (int i = 1; i < 10; i++) {
            assertFalse("must not be equals", oid.equals(o));
        }

    }

    public void testEquals012() {
        oid = new ObjID();
        for (int i = 1; i < 10; i++) {
            assertFalse("must not be equals", oid.equals(null));
        }

    }

    public void testEquals013() {
        i = 555;
        oid = new ObjID(i);
        assertTrue("must be equals", oid.equals(oid));

    }

    public void testEquals014() {
        i = 555;
        oid = new ObjID(i);
        assertFalse("mustn't be equals", oid.equals(null));

    }

    public void testEquals015() {
        i = 555;

        oid = new ObjID(i);
        assertTrue("mustn't be equals", oid.equals(new ObjID(i)));

    }

    public void testEquals016() {
        i = 555;
        oid = new ObjID(i);
        ObjID uid2 = new ObjID(i);
        assertEquals("must be equals", uid2.equals(oid), oid.equals(uid2));

    }

    public void testEquals017() {
        i = 555;
        oid = new ObjID(i);
        Object o = new Object();
        assertFalse("must not be equals", oid.equals(o));

    }

    public void testEquals018() {
        i = 555;
        oid = new ObjID(i);
        assertFalse("must not be equals", oid.equals(null));

    }

    public void testEquals019() {
        i = 555;
        oid = new ObjID(i);
        for (int i = 1; i < 10; i++) {
            assertTrue("must be equals", oid.equals(oid));
        }

    }

    public void testEquals020() {
        i = 555;
        oid = new ObjID(i);
        for (int i = 1; i < 10; i++) {
            assertFalse("mustn't be equals", oid.equals(null));
        }

    }

    public void testEquals021() {
        i = 555;
        oid = new ObjID(i);
        for (int i = 1; i < 10; i++) {
            assertFalse("mustn't be equals", oid.equals(new ObjID(i)));
        }

    }

    public void testEquals022() {
        i = 555;
        oid = new ObjID(i);
        ObjID uid2 = new ObjID(i);
        for (int i = 1; i < 10; i++) {
            assertEquals("must be equals", uid2.equals(oid), oid.equals(uid2));
        }

    }

    public void testEquals023() {
        i = 555;
        oid = new ObjID(i);
        Object o = new Object();
        for (int i = 1; i < 10; i++) {
            assertFalse("must not be equals", oid.equals(o));
        }

    }

    public void testEquals024() {
        i = 555;
        oid = new ObjID(i);
        for (int i = 1; i < 10; i++) {
            assertFalse("must not be equals", oid.equals(null));
        }
    }

    /*
     * Test method for 'java.rmi.server.ObjID.toString()'
     */
    public final void testToString001() {
        i = 0;
        oid = new ObjID(i);

        String string = "[0:0:0, 0]";
        assertEquals(oid.toString(), string);
    }

    public final void testToString002() {
        i = 555;
        oid = new ObjID(i);

        String string = "[0:0:0, 555]";
        assertEquals(oid.toString(), string);
    }

    public final void testToString003() {
        i = Short.MIN_VALUE;
        oid = new ObjID(i);

        String string = "[0:0:0, -32768]";

        assertEquals(oid.toString(), string);

    }

    public final void testToString004() {
        i = Short.MAX_VALUE;
        oid = new ObjID(i);

        String string = "[0:0:0, 32767]";

        assertEquals(oid.toString(), string);

    }

    public final void testToString005() {
        oid = new ObjID();

        String string = "[46]";
        assertEquals(string, oid.toString());
        ObjID oid2 = new ObjID();
        String string2 = "[47]";
        assertEquals(string2, oid2.toString());
    }

    /*
     * Test method for 'java.rmi.server.ObjID.write(ObjectOutput)'
     */
    public final void testWrite001() {
        try {
            oid = new ObjID();
            ByteArrayOutputStream baops = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baops);
            oid.write(oos);
            boolean zero = true;
            for (int i = 0; i < baops.toByteArray().length; i++) {

                if (baops.toByteArray()[i] != 0) {
                    zero = false;
                }
            }

            assertFalse(zero);
        }

        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testWrite003() {
        try {

            int s = 0;
            oid = new ObjID(s);
            ByteArrayOutputStream baops = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baops);
            oid.write(oos);

            boolean zero = true;
            for (int i = 0; i < baops.toByteArray().length; i++) {
                // System.out.println(baops.toByteArray()[i]);
                if (baops.toByteArray()[i] != 0) {
                    zero = false;
                }
            }

            assertFalse(zero);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testWrite005() {
        try {
            int s = 1;
            oid = new ObjID(s);
            ByteArrayOutputStream baops = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baops);
            oid.write(oos);
            boolean zero = true;
            for (int i = 0; i < baops.toByteArray().length; i++) {
                if (baops.toByteArray()[i] != 0) {
                    zero = false;
                }
            }
            assertFalse(zero);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testWrite007() {
        try {

            int s = Integer.MAX_VALUE;
            oid = new ObjID(s);
            ByteArrayOutputStream baops = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baops);
            oid.write(oos);
            boolean zero = true;
            for (int i = 0; i < baops.toByteArray().length; i++) {

                if (baops.toByteArray()[i] != 0) {
                    zero = false;
                }
            }
            assertFalse(zero);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testWrite009() {
        try {

            int s = 5594;
            oid = new ObjID(s);
            ByteArrayOutputStream baops = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baops);
            oid.write(oos);
            boolean zero = true;
            for (int i = 0; i < baops.toByteArray().length; i++) {

                if (baops.toByteArray()[i] != 0) {
                    zero = false;
                }
            }

            assertFalse(zero);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'java.rmi.server.ObjID.read(ObjectInput)'
     */

    public final void testRead001() {
        try {

            byte[] b = { -84, -19, 0, 5, 119, 22, 0, 0, 0, 0, 127, -1, -1, -1,
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            ByteArrayInputStream baops = new ByteArrayInputStream(b);
            ObjectInputStream dis = new ObjectInputStream(baops);
            ObjID.read(dis);
            // assertEquals(oid.toString(),"-4bc9c6f:7d7c21f52d3f340e:15da");
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRead002() {
        try {

            byte[] b = { -84, -19, 0, 5, 119, 22, 0, 0, 0, 0, 0, 0, 21, -38, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            ByteArrayInputStream baops = new ByteArrayInputStream(b);
            ObjectInputStream dis = new ObjectInputStream(baops);
            oid = ObjID.read(dis);
            assertEquals(oid.toString(), "[0:0:0, 5594]");
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRead003() {
        try {
            PipedInputStream pi = new PipedInputStream();
            PipedOutputStream po = new PipedOutputStream(pi);
            ObjectOutputStream oo = new ObjectOutputStream(po);
            ObjectInputStream oi = new ObjectInputStream(pi);
            oid = new ObjID(3452432);
            oid.write(oo);
            oo.flush();
            assertEquals(oid, ObjID.read(oi));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRead004() {
        final String myException = "algo especial que decir??";
        try {

            oid = ObjID.read(new ObjectInputStream(new InputStream() {
                public int read() throws IOException {
                    throw new IOException(myException);
                };
            }));
            fail("Must fail");

        } catch (IOException e) {
            assertEquals(myException, e.getMessage());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRead006() {
        try {
            byte[] b = { -84, -19, 0, 5, 119, 22, 0, 0, 0, 0, 0, 0, 21, -38, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            ByteArrayInputStream baops = new ByteArrayInputStream(b);
            oid = ObjID.read(new ObjectInputStream(baops));
            baops.reset();
            assertEquals(oid, ObjID.read(new ObjectInputStream(baops)));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRead007() {
        try {
            byte[] b = { -84, -19, 0, 5, 119, 22, 0, 0, 0, 0, 0, 0, 21, -38, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            ByteArrayInputStream baops = new ByteArrayInputStream(b);
            oid = ObjID.read(new ObjectInputStream(baops));
            baops.reset();
            assertNotSame(oid, ObjID.read(new ObjectInputStream(baops)));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

}
