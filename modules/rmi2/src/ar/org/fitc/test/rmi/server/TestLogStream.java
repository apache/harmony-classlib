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

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.rmi.server.LogStream;

import junit.framework.TestCase;
import ar.org.fitc.test.util.Messages;

public class TestLogStream extends TestCase implements Messages {
    LogStream ls = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestLogStream.class);
    }

    public TestLogStream(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'java.rmi.server.LogStream.log(String)'
     */

    public final void testLog001() {
        assertNotNull(msgNotNull, LogStream.log(""));
    }

    public final void testLog002() {
        assertNotNull(msgNotNull, LogStream
                .log("p´¿'269572´y t{+oñ_ÑL=)\"&!(0%=)"));
    }

    public final void testLog003() {
        assertNotNull(msgNotNull, LogStream.log("no"));
    }

    public final void testLog004() {
        assertNotNull(msgNotNull, LogStream.log("brief"));
    }

    public final void testLog005() {
        assertNotNull(msgNotNull, LogStream.log("verbose"));
    }

    /*
     * Test method for 'java.rmi.server.LogStream.getDefaultStream()'
     */

    public final void testGetDefaultStream001() {
        byte[] ba = { 11, 25, 64, 78, 30, 15, 65, 12, 34, 79, 66, 50, 01, 00,
                16 };
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        try {
            bas.write(ba);
            PrintStream ps1 = new PrintStream(bas);
            ls = LogStream.log("tcp");
            ls.setDefaultStream(ps1);
            PrintStream ps2 = ls.getDefaultStream();
            ps2.flush();
            assertEquals(ps1, ps2);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testGetDefaultStream002() {
        byte[] ba = new byte[0];
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        try {
            bas.write(ba);
            PrintStream ps1 = new PrintStream(bas);
            ls = LogStream.log("tcp");
            ls.setDefaultStream(ps1);
            PrintStream ps2 = ls.getDefaultStream();
            assertEquals(ps1, ps2);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testGetDefaultStream003() {
        byte[] ba = { 11, 25, 64, 78, 30, 15, 65, 12, 34, 79, 66, 50, 01, 00,
                16 };
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        try {
            PrintStream ps1 = new PrintStream(bas);
            ls = LogStream.log("tcp");

            PrintStream ps2 = ls.getDefaultStream();
            ps2.flush();
            int i = 1;
            assertNotNull(ps2);

        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'java.rmi.server.LogStream.toString()'
     */
    public final void testToString001() {
        try {
            assertEquals(LogStream.log("no").toString(), "no");
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testToString002() {
        assertEquals(LogStream.log("verbose").toString(), "verbose");
    }

    public final void testToString003() {
        assertEquals(LogStream.log("VERBOSE").toString(), "VERBOSE");
    }

    public final void testToString004() {
        try {
            assertEquals(LogStream.log("").toString(), "");
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'java.rmi.server.LogStream.write(int)'
     */
    public final void testWriteInt() {
        try {

        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'java.rmi.server.LogStream.write(byte[], int, int)'
     */
    public final void testWriteByteArrayIntInt() {
        try {

        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'java.rmi.server.LogStream.getOutputStream()'
     */
    public final void testGetOutputStream() {
        try {

        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'java.rmi.server.LogStream.setOutputStream(OutputStream)'
     */
    public final void testSetOutputStream() {
        try {
            FileOutputStream tcpLogFile = new FileOutputStream("C:\\tcplog.txt");
            ls = LogStream.log("tcp");
            ls.setOutputStream(tcpLogFile);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'java.rmi.server.LogStream.parseLevel(String)'
     */

    public final void testParseLevel001() {
        try {
            ls = LogStream.log("tcp");
            int i = ls.parseLevel("VERBOSE");
            assertEquals(i, 20);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testParseLevel002() {
        try {
            ls = LogStream.log("tcp");
            int i = ls.parseLevel("BRIEF");
            assertEquals(i, 10);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testParseLevel003() {
        try {
            ls = LogStream.log("tcp");
            int i = ls.parseLevel("SILENT");
            assertEquals(i, 0);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testParseLevel004() {
        try {
            int i = LogStream.log("tcp").parseLevel("tcp");
            assertEquals(i, -1);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
}
