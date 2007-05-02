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

package org.apache.harmony.sql.tests.javax.sql.rowset;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

import javax.sql.rowset.BaseRowSet;
import javax.sql.rowset.serial.SerialArray;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialRef;

import junit.framework.TestCase;

public class BaseRowSetTest extends TestCase {

    public void testGetParams() throws Exception {
        BaseRowSetImpl brs = new BaseRowSetImpl();
        Object[] params = brs.getParams();
        assertNotNull(params);
        assertEquals(0, params.length);
    }
    
    public void testSetNullintint() throws Exception {
        BaseRowSetImpl brs = new BaseRowSetImpl();
        try {
            brs.setNull(1, Types.BINARY);
            fail("sql exception expected");
        } catch (SQLException e) {
        }
        
        brs.initParams();
        
        try {
            brs.setNull(0, Types.BINARY);
            fail("sql exception expected");
        } catch (SQLException e) {
        }
        
        brs.setNull(1, Types.BINARY);
        Object[] params = brs.getParams();
        assertNotNull(params);
        Object[] param = (Object[])params[0];
        assertNotNull(param);
        assertEquals(2, param.length);
        assertNull(param[0]);
        assertEquals(Integer.valueOf(Types.BINARY), param[1]);
    }
    
    public void testSetNullintintString() throws Exception {
        BaseRowSetImpl brs = new BaseRowSetImpl();
        try {
            brs.setNull(1, Types.BINARY, "java.lang.Boolean");
            fail("sql exception expected");
        } catch (SQLException e) {
        }
        
        brs.initParams();
        
        try {
            brs.setNull(0, Types.BINARY, "java.lang.Boolean");
            fail("sql exception expected");
        } catch (SQLException e) {
        }
        
        brs.setNull(1, Types.BINARY, "java.lang.Boolean");
        Object[] params = brs.getParams();
        assertNotNull(params);
        Object[] param = (Object[])params[0];
        assertNotNull(param);
        assertEquals(3, param.length);
        assertNull(param[0]);
        assertEquals(Integer.valueOf(Types.BINARY), param[1]);
        assertEquals("java.lang.Boolean", param[2]);
    }
    
    public void testSetBoolean() throws Exception {
        BaseRowSetImpl brs = new BaseRowSetImpl();
        try {
            brs.setBoolean(1, true);
            fail("sql exception expected");
        } catch (SQLException e) {
        }
        
        brs.initParams();
        
        try {
            brs.setBoolean(0, true);
            fail("sql exception expected");
        } catch (SQLException e) {
        }
        
        brs.setBoolean(1, true);
        Object[] params = brs.getParams();
        assertNotNull(params);
        assertEquals(1, params.length);
        assertEquals(Boolean.TRUE, params[0]);
    }
    
    public void testSetByte() throws Exception {
        BaseRowSetImpl brs = new BaseRowSetImpl();
        try {
            brs.setByte(1, (byte)1);
            fail("sql exception expected");
        } catch (SQLException e) {
        }
        
        brs.initParams();
        
        try {
            brs.setByte(0, (byte)1);
            fail("sql exception expected");
        } catch (SQLException e) {
        }
        
        brs.setByte(1, (byte)1);
        Object[] params = brs.getParams();
        assertNotNull(params);
        assertEquals(1, params.length);
        assertEquals(Byte.valueOf((byte)1), params[0]);
    }
    
    public void testSetShort() throws Exception {
        BaseRowSetImpl brs = new BaseRowSetImpl();
        try {
            brs.setShort(1, (short)1);
            fail("sql exception expected");
        } catch (SQLException e) {
        }
        
        brs.initParams();
        
        try {
            brs.setShort(0, (short)1);
            fail("sql exception expected");
        } catch (SQLException e) {
        }
        
        brs.setShort(1, (byte)1);
        Object[] params = brs.getParams();
        assertNotNull(params);
        assertEquals(1, params.length);
        assertEquals(Short.valueOf((short)1), params[0]);
    }
    
    public void testSetArray() throws SQLException {
        BaseRowSetImpl brs = new BaseRowSetImpl();
        brs.initParams();
        Array a = new MockArray();
        brs.setArray(1, a);
        Object[] params = brs.getParams();
        assertNotNull(params);
        assertEquals(1, params.length);
        assertTrue("Should have stored a SerialArray", params[0] instanceof SerialArray);
    }
    
    public void testSetBlob() throws SQLException {
        BaseRowSetImpl brs = new BaseRowSetImpl();
        brs.initParams();
        Blob b = new MockBlob();
        brs.setBlob(1, b);
        Object[] params = brs.getParams();
        assertNotNull(params);
        assertEquals(1, params.length);
        assertTrue("Should have stored a SerialBlob", params[0] instanceof SerialBlob);
    }
    
    public void testSetClob() throws SQLException {
        BaseRowSetImpl brs = new BaseRowSetImpl();
        brs.initParams();
        Clob c = new MockClob();
        brs.setClob(1, c);
        Object[] params = brs.getParams();
        assertNotNull(params);
        assertEquals(1, params.length);
        assertTrue(c != params[0]);
        assertTrue("Should have stored a SerialClob", params[0] instanceof SerialClob);
    }
    
    public void testSetRef() throws SQLException {
        BaseRowSetImpl brs = new BaseRowSetImpl();
        brs.initParams();
        Ref r = new MockRef();
        brs.setRef(1, r);
        Object[] params = brs.getParams();
        assertNotNull(params);
        assertEquals(1, params.length);
        assertTrue(r != params[0]);
        assertTrue("Should have stored a SerialRef", params[0] instanceof SerialRef);        
    }
    
    private static final class BaseRowSetImpl extends BaseRowSet {
        private static final long serialVersionUID = 1L;

        @Override
        protected void initParams() {
            super.initParams();
        }
    }
    
    static class MockArray implements Array {

        public Object getArray() throws SQLException {
            return new Object[0];
        }

        public Object getArray(long index, int count) throws SQLException {
            return null;
        }

        public Object getArray(long index, int count, Map<String, Class<?>> map)
                throws SQLException {
            return null;
        }

        public Object getArray(Map<String, Class<?>> map) throws SQLException {
            return null;
        }

        public int getBaseType() throws SQLException {
            return 0;
        }

        public String getBaseTypeName() throws SQLException {
            return null;
        }

        public ResultSet getResultSet() throws SQLException {
            return null;
        }

        public ResultSet getResultSet(long index, int count)
                throws SQLException {
            return null;
        }

        public ResultSet getResultSet(long index, int count,
                Map<String, Class<?>> map) throws SQLException {
            return null;
        }

        public ResultSet getResultSet(Map<String, Class<?>> map)
                throws SQLException {
            return null;
        }

    }
    
    static class MockBlob implements Blob {

        public InputStream getBinaryStream() throws SQLException {
            return null;
        }

        public byte[] getBytes(long pos, int length) throws SQLException {
            return new byte[0];
        }

        public long length() throws SQLException {
            return 0;
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
    
    static class MockClob implements Clob {
        
        public Reader characterStreamReader = new StringReader("xys");
        public InputStream asciiInputStream = new ByteArrayInputStream("hello".getBytes());
        
        public InputStream getAsciiStream() throws SQLException {
            return asciiInputStream;
        }

        public Reader getCharacterStream() throws SQLException {
            return characterStreamReader;
        }

        public String getSubString(long pos, int length) throws SQLException {
            return null;
        }

        public long length() throws SQLException {
            return 3;
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

    public class MockRef implements Ref {

        public String getBaseTypeName() throws SQLException {
            return "ref";
        }

        public Object getObject() throws SQLException {
            return null;
        }

        public Object getObject(Map<String, Class<?>> map) throws SQLException {
            return null;
        }

        public void setObject(Object value) throws SQLException {
        }
    }
}
