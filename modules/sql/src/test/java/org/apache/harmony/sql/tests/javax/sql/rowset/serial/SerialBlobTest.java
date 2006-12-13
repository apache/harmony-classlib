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

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Arrays;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import junit.framework.TestCase;

public class SerialBlobTest extends TestCase {

    public void testConstructorLBlob() throws Exception {
        boolean isAbnormal = false;
        MockSerialBlob mockBlob = new MockSerialBlob(isAbnormal);
        SerialBlob serialBlob = new SerialBlob(mockBlob);
        // SerialBlob constructor initiliases with the data of given blob,
        // therefore, blob.getBytes is invoked.
        assertTrue(mockBlob.isGetBytesInvoked);
        assertEquals(1, serialBlob.length());

        isAbnormal = true;
        mockBlob = new MockSerialBlob(isAbnormal);
        try {
            new SerialBlob(mockBlob);
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }

        try {
            new SerialBlob((Blob) null);
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
    }

    public void testConstructor$B() throws Exception {
        byte[] buf = new byte[8];
        SerialBlob serialBlob = new SerialBlob(buf);
        assertEquals(8, serialBlob.length());

        try {
            new SerialBlob((byte[]) null);
            fail("should throw SQLException");
        } catch (NullPointerException e) {
            // expected
        }
    }

    public void testGetBinaryStream() throws Exception {
        byte[] buf = { 1, 2, 3, 4, 5, 6, 7, 8 };
        SerialBlob serialBlob = new SerialBlob(buf);
        InputStream is = serialBlob.getBinaryStream();
        int i = 0;
        while (true) {
            int b = is.read();
            if (b == -1) {
                if (i < buf.length) {
                    fail("returned input stream contains too few data");
                }
                break;
            }

            if (i > buf.length) {
                fail("returned input stream contains too much data");
            }
            assertEquals(buf[i++], b);
        }
    }

    public void testGetBytesJI() throws Exception {
        byte[] buf = { 1, 2, 3, 4, 5, 6, 7, 8 };
        SerialBlob serialBlob = new SerialBlob(buf);
        byte[] data = serialBlob.getBytes(1, 1);
        assertEquals(1, data.length);
        assertEquals(1, data[0]);

        data = serialBlob.getBytes(2, 3);
        assertEquals(3, data.length);
        assertEquals(2, data[0]);
        assertEquals(3, data[1]);
        assertEquals(4, data[2]);

        data = serialBlob.getBytes(1, 10);
        assertEquals(8, data.length);
        assertTrue(Arrays.equals(buf, data));

        try {
            data = serialBlob.getBytes(2, -1);
            fail("should throw SerialException");
        } catch (SerialException e) {
            // expected
        }

        try {
            data = serialBlob.getBytes(0, 2);
            fail("should throw SerialException");
        } catch (SerialException e) {
            // expected
        }

        try {
            data = serialBlob.getBytes(-1, 2);
            fail("should throw SerialException");
        } catch (SerialException e) {
            // expected
        }

        try {
            data = serialBlob.getBytes(10, 11);
            fail("should throw SerialException");
        } catch (SerialException e) {
            // expected
        }
    }

    static class MockSerialBlob implements Blob {
        public byte buf[] = new byte[1];

        public boolean isGetBytesInvoked;

        public boolean isAbnormal;

        public MockSerialBlob() {

        }

        public MockSerialBlob(boolean isAbnormal) {
            this.isAbnormal = isAbnormal;
        }

        public InputStream getBinaryStream() throws SQLException {
            return null;
        }

        public byte[] getBytes(long pos, int length) throws SQLException {
            isGetBytesInvoked = true;
            if (isAbnormal) {
                throw new SQLException();
            }
            return buf;
        }

        public long length() throws SQLException {
            return buf.length;
        }

        public long position(Blob pattern, long start) throws SQLException {
            return 0;
        }

        public long position(byte[] pattern, long start) throws SQLException {
            return 0;
        }

        public OutputStream setBinaryStream(long pos) throws SQLException {
            return null;
        }

        public int setBytes(long pos, byte[] theBytes) throws SQLException {
            return 0;
        }

        public int setBytes(long pos, byte[] theBytes, int offset, int len)
                throws SQLException {
            return 0;
        }

        public void truncate(long len) throws SQLException {

        }

    }

}
