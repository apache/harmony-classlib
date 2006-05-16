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
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.rmi.server.UID;
import java.util.StringTokenizer;

import junit.framework.TestCase;
import ar.org.fitc.test.util.Messages;

public class TestUID extends TestCase implements Messages {

    UID uid = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestUID.class);
    }

    public TestUID(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'java.rmi.server.UID.UID()'
     */
    public final void testUID001() {
        assertNotNull(msgNotNull, new UID());
    }

    public final void testUID002() {
        uid = new UID();
        for (int i = 0; i < 15; i++) {
            assertNotSame(uid.toString(), new UID().toString());
        }
    }

    /*
     * Test method for 'java.rmi.server.UID.UID(short)'
     */
    public final void testUIDShort001() {
        short s = 0;
        assertNotNull(msgNotNull, new UID(s));
    }

    public final void testUIDShort002() {
        short s = Short.MIN_VALUE;
        assertNotNull(msgNotNull, new UID(s));
    }

    public final void testUIDShort003() {
        short s = Short.MAX_VALUE;
        assertNotNull(msgNotNull, new UID(s));
    }

    public final void testUIDShort004() {
        short s = 5594;
        assertNotNull(msgNotNull, new UID(s));
    }

    public final void testUIDShort005() {
        short s = 0;
        uid = new UID(s);
        for (int i = 0; i < 15; i++) {
            assertNotSame(uid.toString(), new UID(s).toString());
        }

    }

    public final void testUIDShort006() {
        short s = Short.MIN_VALUE;
        uid = new UID(s);
        for (int i = 0; i < 15; i++) {
            assertNotSame(uid.toString(), new UID(s).toString());
        }
    }

    public final void testUIDShort007() {
        short s = Short.MAX_VALUE;
        uid = new UID(s);
        for (int i = 0; i < 15; i++) {
            assertNotSame(uid.toString(), new UID(s).toString());
        }
    }

    public final void testUIDShort008() {
        short s = 5594;
        uid = new UID(s);
        for (int i = 0; i < 15; i++) {
            assertNotSame(uid.toString(), new UID(s).toString());
        }
    }

    /*
     * Test method for 'java.rmi.server.UID.hashCode()'
     */
    public final void testHashCode001() {
        assertNotSame("must not be equals", new UID().hashCode(), new UID()
                .hashCode());
    }

    public final void testHashCode002() {
        uid = new UID();
        int hc = uid.hashCode();
        for (int i = 1; i < 15; i++) {
            assertEquals("must be equals", uid.hashCode(), hc);
        }
    }

    public final void testHashCode003() {
        short s = 555;
        assertNotSame("must not be equals", new UID(s).hashCode(), new UID()
                .hashCode());
    }

    public final void testHashCode004() {

        uid = new UID();
        int hc = uid.hashCode();
        for (int i = 1; i < 15; i++) {
            assertEquals("must be equals", uid.hashCode(), hc);
        }
    }

    /*
     * Test method for 'java.rmi.server.UID.equals(Object)'
     */
    public void testEquals001() {

        uid = new UID();
        assertTrue("must be equals", uid.equals(uid));

    }

    public void testEquals002() {
        uid = new UID();
        assertFalse("mustn't be equals", uid.equals(null));

    }

    public void testEquals003() {
        uid = new UID();
        assertFalse("mustn't be equals", uid.equals(new UID()));

    }

    public void testEquals004() {
        uid = new UID();
        UID uid2 = new UID();
        assertEquals("must be equals", uid2.equals(uid), uid.equals(uid2));

    }

    public void testEquals005() {
        uid = new UID();
        Object o = new Object();
        assertFalse("must not be equals", uid.equals(o));

    }

    public void testEquals006() {
        uid = new UID();
        assertFalse("must not be equals", uid.equals(null));

    }

    public void testEquals007() {
        uid = new UID();
        for (int i = 1; i < 10; i++) {
            assertTrue("must be equals", uid.equals(uid));
        }

    }

    public void testEquals008() {
        uid = new UID();
        for (int i = 1; i < 10; i++) {
            assertFalse("mustn't be equals", uid.equals(null));
        }

    }

    public void testEquals009() {
        uid = new UID();
        for (int i = 1; i < 10; i++) {
            assertFalse("mustn't be equals", uid.equals(new UID()));
        }

    }

    public void testEquals010() {
        uid = new UID();
        UID uid2 = new UID();
        for (int i = 1; i < 10; i++) {
            assertEquals("must be equals", uid2.equals(uid), uid.equals(uid2));
        }

    }

    public void testEquals011() {
        uid = new UID();
        Object o = new Object();
        for (int i = 1; i < 10; i++) {
            assertFalse("must not be equals", uid.equals(o));
        }

    }

    public void testEquals012() {
        uid = new UID();
        for (int i = 1; i < 10; i++) {
            assertFalse("must not be equals", uid.equals(null));
        }

    }

    public void testEquals013() {
        short s = 555;
        uid = new UID(s);
        assertTrue("must be equals", uid.equals(uid));

    }

    public void testEquals014() {
        short s = 555;
        uid = new UID(s);
        assertFalse("mustn't be equals", uid.equals(null));

    }

    public void testEquals015() {
        short s = 555;

        uid = new UID(s);
        assertTrue("mustn't be equals", uid.equals(new UID(s)));

    }

    public void testEquals016() {
        short s = 555;
        uid = new UID(s);
        UID uid2 = new UID(s);
        assertEquals("must be equals", uid2.equals(uid), uid.equals(uid2));

    }

    public void testEquals017() {
        short s = 555;
        uid = new UID(s);
        Object o = new Object();
        assertFalse("must not be equals", uid.equals(o));

    }

    public void testEquals018() {
        short s = 555;
        uid = new UID(s);
        assertFalse("must not be equals", uid.equals(null));

    }

    public void testEquals019() {
        short s = 555;
        uid = new UID(s);
        for (int i = 1; i < 10; i++) {
            assertTrue("must be equals", uid.equals(uid));
        }

    }

    public void testEquals020() {
        short s = 555;
        uid = new UID(s);
        for (int i = 1; i < 10; i++) {
            assertFalse("mustn't be equals", uid.equals(null));
        }

    }

    public void testEquals021() {
        short s = 555;
        uid = new UID(s);
        for (int i = 1; i < 10; i++) {
            assertTrue("must be equals", uid.equals(new UID(s)));
        }

    }

    public void testEquals022() {
        short s = 555;
        uid = new UID(s);
        UID uid2 = new UID(s);
        for (int i = 1; i < 10; i++) {
            assertEquals("must be equals", uid2.equals(uid), uid.equals(uid2));
        }

    }

    public void testEquals023() {
        short s = 555;
        uid = new UID(s);
        Object o = new Object();
        for (int i = 1; i < 10; i++) {
            assertFalse("must not be equals", uid.equals(o));
        }

    }

    public void testEquals024() {
        short s = 555;
        uid = new UID(s);
        for (int i = 1; i < 10; i++) {
            assertFalse("must not be equals", uid.equals(null));
        }
    }

    /*
     * Test method for 'java.rmi.server.UID.toString()'
     */
    public final void testToString001() {
        short s = 0;
        uid = new UID(s);

        String string = "0:0:0";
        assertEquals(uid.toString(), string);
    }

    public final void testToString002() {
        short s = 555;
        uid = new UID(s);

        String string = "0:0:22b";
        assertEquals(uid.toString(), string);
    }

    public final void testToString003() {
        short s = Short.MIN_VALUE;
        uid = new UID(s);

        String string = "0:0:-8000";
        assertEquals(uid.toString(), string);
    }

    public final void testToString004() {
        short s = Short.MAX_VALUE;
        uid = new UID(s);

        String string = "0:0:7fff";
        assertEquals(uid.toString(), string);
    }

    public final void testToString005() {

        uid = new UID();
        StringTokenizer st = new StringTokenizer(uid.toString(), ":");
        String s[] = new String[5];
        for (int i = 0; i < 3; i++) {
            s[i] = st.nextToken(":");
        }
        UID uid2 = new UID();
        StringTokenizer st2 = new StringTokenizer(uid2.toString(), ":");
        String s2[] = new String[5];
        for (int i = 0; i < 3; i++) {
            s2[i] = st2.nextToken(":");
        }
        for (int i = 0; i < 2; i++) {
            assertEquals(s[i], s2[i]);
        }
        assertNotSame(s[2], s2[2]);
    }

    /*
     * Test method for 'java.rmi.server.UID.write(DataOutput)'
     */
    public final void testWrite001() {
        try {
            uid = new UID();
            ByteArrayOutputStream baops = new ByteArrayOutputStream();
            DataOutputStream dops = new DataOutputStream(baops);
            uid.write(dops);

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

    public final void testWrite002() {
        try {
            uid = new UID();
            File file = new File("c:\\xx.bin");
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            uid.write(raf);
            fail("Should raise IOException");
        } catch (IOException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testWrite003() {
        try {

            short s = 0;
            uid = new UID(s);
            ByteArrayOutputStream baops = new ByteArrayOutputStream();
            DataOutputStream dops = new DataOutputStream(baops);
            uid.write(dops);

            for (int i = 1; i < baops.toByteArray().length; i++) {
                assertEquals(baops.toByteArray()[i], 0);
            }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testWrite004() {
        try {
            short s = 0;
            uid = new UID(s);
            File file = new File("c:\\xx.bin");
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            uid.write(raf);
            fail("Should raise IOException");
        } catch (IOException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testWrite005() {
        try {

            short s = Short.MIN_VALUE;
            uid = new UID(s);
            ByteArrayOutputStream baops = new ByteArrayOutputStream();
            DataOutputStream dops = new DataOutputStream(baops);
            uid.write(dops);
            byte[] ba = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -128, 0, 0 };
            for (int i = 1; i < baops.toByteArray().length; i++) {

                assertEquals(baops.toByteArray()[i], ba[i]);
            }
        } catch (IOException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testWrite006() {
        try {
            short s = Short.MIN_VALUE;
            uid = new UID(s);
            File file = new File("c:\\xx.bin");
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            uid.write(raf);
            fail("Should raise IOException");
        } catch (IOException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testWrite007() {
        try {

            short s = Short.MAX_VALUE;
            uid = new UID(s);
            ByteArrayOutputStream baops = new ByteArrayOutputStream();
            DataOutputStream dops = new DataOutputStream(baops);
            uid.write(dops);
            byte[] ba = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 127, -1, 0 };
            for (int i = 1; i < baops.toByteArray().length; i++) {
                assertEquals(baops.toByteArray()[i], ba[i]);
            }
            System.out.println();
        } catch (IOException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testWrite008() {
        try {
            short s = Short.MAX_VALUE;
            uid = new UID(s);
            File file = new File("c:\\xx.bin");
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            uid.write(raf);
            fail("Should raise IOException");
        } catch (IOException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testWrite009() {
        try {

            short s = 5594;
            uid = new UID(s);
            ByteArrayOutputStream baops = new ByteArrayOutputStream();
            DataOutputStream dops = new DataOutputStream(baops);
            uid.write(dops);
            byte[] ba = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, -38 };
            for (int i = 1; i < baops.toByteArray().length; i++) {
                assertEquals(baops.toByteArray()[i], ba[i]);
            }
        } catch (IOException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testWrite010() {
        try {
            short s = 5594;
            uid = new UID(s);
            File file = new File("c:\\xx.bin");
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            uid.write(raf);
            fail("Should raise IOException");
        } catch (IOException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'java.rmi.server.UID.read(DataInput)'
     */
    public final void testRead001() {
        try {
            byte[] ba = { -5, 67, 99, -111, 125, 124, 33, -11, 45, 63, 52, 14,
                    21, -38 };
            ByteArrayInputStream baops = new ByteArrayInputStream(ba);
            DataInputStream dis = new DataInputStream(baops);
            uid = UID.read(dis);
            assertEquals(uid.toString(), "-4bc9c6f:7d7c21f52d3f340e:15da");

        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRead002() {
        try {
            byte[] ba = { 5, 67, 0, 33, 125, -124, 33, -11, 45, 63, 52, 0, 0, 0 };
            ByteArrayInputStream baops = new ByteArrayInputStream(ba);
            DataInputStream dis = new DataInputStream(baops);
            uid = UID.read(dis);

            assertEquals(uid.toString(), "5430021:7d8421f52d3f3400:0");
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRead003() {
        try {
            byte[] ba = { 4, -33, 57, 13, 0, 0, 0, 0, 0, 0, 0, 0, -21, -38 };
            ByteArrayInputStream baops = new ByteArrayInputStream(ba);
            DataInputStream dis = new DataInputStream(baops);
            uid = UID.read(dis);

            assertEquals(uid.toString(), "4df390d:0:-1426");
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRead004() {
        byte[] ba = { 0, 0, 0, 0, -22, 124, 33, -11, 55, 25, 1, 111, 99, -99 };
        ByteArrayInputStream baops = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(baops);
        try {
            uid = UID.read(dis);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
        assertEquals(uid.toString(), "0:-1583de0ac8e6fe91:639d");
    }

    public final void testRead005() {
        byte[] ba = { -5, 67, 99, -111, 125, 38, 33, -11, 45, 63, 52, 14, 21,
                -38 };
        ByteArrayInputStream baops = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(baops);
        try {
            uid = UID.read(dis);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
        assertEquals(uid.toString(), "-4bc9c6f:7d2621f52d3f340e:15da");
    }

    public final void testRead006() {
        byte[] ba = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        ByteArrayInputStream baops = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(baops);
        try {
            uid = UID.read(dis);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
        assertEquals(uid.toString(), "0:0:0");
    }

    public final void testRead007() throws FileNotFoundException {
        File file = new File("c:\\xxxx.bin");
        RandomAccessFile raf = new RandomAccessFile(file, "rwd");
        try {

            UID.read(raf);
            fail("Should raise IOException");
        } catch (IOException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
}
