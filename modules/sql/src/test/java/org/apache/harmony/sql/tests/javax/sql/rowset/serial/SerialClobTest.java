/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.harmony.sql.tests.javax.sql.rowset.serial;

import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Clob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import junit.framework.TestCase;

public class SerialClobTest extends TestCase {

    public void testSerialClob$C() throws Exception {
        char[] buf = new char[8];
        SerialClob serialClob = new SerialClob(buf);

        assertEquals(8, serialClob.length());

        try {
            new SerialClob((char[]) null);
            fail("should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }
    }

    public void testSerialClobLClob() throws Exception {
        SerialClob serialClob;
        MockSerialClob mockClob = new MockSerialClob();

        mockClob.characterStream = new CharArrayReader(mockClob.buf);
        mockClob.asciiStream = new ByteArrayInputStream(new byte[] { 1 });
        serialClob = new SerialClob(mockClob);
        assertEquals(mockClob.buf.length, serialClob.length());

        mockClob.characterStream = null;
        mockClob.asciiStream = new ByteArrayInputStream(new byte[] { 1 });
        try {
            new SerialClob(mockClob);
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }

        mockClob.characterStream = new CharArrayReader(new char[] { 1 });
        mockClob.asciiStream = null;
        try {
            new SerialClob(mockClob);
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }

        mockClob.characterStream = new MockAbnormalReader();
        mockClob.asciiStream = new ByteArrayInputStream(new byte[] { 1 });
        try {
            new SerialClob(mockClob);
            fail("should throw SerialException");
        } catch (SerialException e) {
            // expected
        }        
        
        try {
            new SerialClob((Clob) null);
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }

    }

    public void testLength() {
        // TODO: Not yet implemented
    }

    public void testGetAsciiStream() {
        // TODO: Not yet implemented
    }

    public void testGetCharacterStream() {
        // TODO: Not yet implemented
    }

    public void testGetSubString() throws Exception {
        String s = "hello";
        char[] buf = s.toCharArray();
        SerialClob serialClob = new SerialClob(buf);

        String sub = serialClob.getSubString(1, 5);
        assertEquals("hello", sub);

        sub = serialClob.getSubString(2, 3);
        assertEquals("ell", sub);

        try {
            sub = serialClob.getSubString(0, 6);
            fail("should throw SerialException");
        } catch (SerialException e) {
            // expected
        }
        
        try {
            sub = serialClob.getSubString(7, 1);
            fail("should throw SerialException");
        } catch (SerialException e) {
            // expected
        }
        
        try {
            sub = serialClob.getSubString(1, 7);
            fail("should throw SerialException");
        } catch (SerialException e) {
            // expected
        }
        
        try {
            sub = serialClob.getSubString(1, -2);
            fail("should throw SerialException");
        } catch (SerialException e) {
            // expected
        }

    }

    public void testPositionLClobJ() {
        // TODO: Not yet implemented
    }

    public void testPositionLStringJ() throws Exception {
        String s = "helloo";
        char[] buf = s.toCharArray();
        SerialClob serialClob = new SerialClob(buf);
        
        long pos = serialClob.position("llo", 1);
        assertEquals(3, pos);
        
        pos = serialClob.position("llo", 3);
        assertEquals(3, pos);
        
        pos = serialClob.position("o", 6);
        assertEquals(6, pos);
        
        pos = serialClob.position("ooooooo", 1);
        assertEquals(-1, pos);
        
        pos = serialClob.position("llo", 4);
        assertEquals(-1, pos);
        
        pos = serialClob.position("llo", 0);
        assertEquals(-1, pos);
        
        pos = serialClob.position("llo", -1);
        assertEquals(-1, pos);
        
        pos = serialClob.position("llo", 10);
        assertEquals(-1, pos);
    }

    public void testSetAsciiStream() {
        // TODO: Not yet implemented
    }

    public void testSetCharacterStream() {
        // TODO: Not yet implemented
    }

    public void testSetStringJLString() throws Exception {
        String s = "hello";
        char[] buf = s.toCharArray();
        SerialClob serialClob = new SerialClob(buf);
        
        int count = serialClob.setString(1, "olleh");
        String sub = serialClob.getSubString(1, 5);
        assertEquals("olleh", sub);
        assertEquals(5, count);
        
        count = serialClob.setString(2, "mm");
        sub = serialClob.getSubString(1, 5);
        assertEquals("ommeh", sub);
        assertEquals(2, count);
        
        try {
            serialClob.setString(-1, "hello");
            fail("should throw SerialException");
        } catch (SerialException e) {
            // expected
        }
        
        try {
            serialClob.setString(6, "hello");
            fail("should throw SerialException");
        } catch (SerialException e) {
            // expected
        }
        
        // Harmony-3335, non bug difference from RI
        try {
            serialClob.setString(2, "hello");
            fail("should throw SerialException");
        } catch (SerialException e) {
            // expected
        }
    }

    public void testSetStringJLStringII() throws Exception {
        String s = "hello";
        char[] buf = s.toCharArray();
        SerialClob serialClob = new SerialClob(buf);
        
        int count = serialClob.setString(1, "olleh", 0, 5);
        String sub = serialClob.getSubString(1, 5);
        assertEquals("olleh", sub);
        assertEquals(5, count);
        
        count = serialClob.setString(2, "mmnn",1, 2);
        sub = serialClob.getSubString(1, 5);
        // RI's bug
        assertEquals(2, count);
        assertEquals("omneh", sub);
        
        try {
            serialClob.setString(-1, "hello", 0, 5);
            fail("should throw SerialException");
        } catch (SerialException e) {
            // expected
        }
        
        try {
            serialClob.setString(6, "hello", 0, 5);
            fail("should throw SerialException");
        } catch (SerialException e) {
            // expected
        }
        
        try {
            serialClob.setString(1, "hello", 0, 6);
            fail("should throw SerialException");
        } catch (SerialException e) {
            // expected
        }
        
        // Harmony-3335, non bug difference from RI
        try {
            serialClob.setString(2, "hello", 0, 5);
            fail("should throw SerialException");
        } catch (SerialException e) {
            // expected
        }
        
        try {
            // Harmony-3335
            serialClob.setString(1, "hello", -1, 5);
            fail("should throw SerialException");
        } catch (SerialException e) {
            // expected
        }
    }

    public void testTruncate() {
        // TODO: Not yet implemented
    }

    static class MockSerialClob implements Clob {

        char[] buf = { 1, 2, 3 };

        public Reader characterStream;

        public InputStream asciiStream;

        public MockSerialClob() {
        }

        public InputStream getAsciiStream() throws SQLException {
            return asciiStream;
        }

        public Reader getCharacterStream() throws SQLException {
            return characterStream;
        }

        public String getSubString(long pos, int length) throws SQLException {
            return null;
        }

        public long length() throws SQLException {
            return buf.length;
        }

        public long position(Clob searchstr, long start) throws SQLException {
            return 0;
        }

        public long position(String searchstr, long start) throws SQLException {
            return 0;
        }

        public OutputStream setAsciiStream(long pos) throws SQLException {
            return null;
        }

        public Writer setCharacterStream(long pos) throws SQLException {
            return null;
        }

        public int setString(long pos, String str) throws SQLException {
            return 0;
        }

        public int setString(long pos, String str, int offset, int len)
                throws SQLException {
            return 0;
        }

        public void truncate(long len) throws SQLException {

        }
    }

    static class MockAbnormalReader extends java.io.Reader {
        public int read(char[] cbuf, int off, int len) throws IOException {
            throw new IOException();
        }

        public void close() throws IOException {

        }
    }
}
