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

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.rowset.serial.SerialArray;
import javax.sql.rowset.serial.SerialException;

import junit.framework.TestCase;

public class SerialArrayTest extends TestCase {

    private Array mock = new MockArray();

    private SerialArray sa;

    String[] strs = new String[2];

    Map<String, Class<?>> map = new HashMap<String, Class<?>>();

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        sa = new SerialArray(mock);
        strs[0] = "test1";
        strs[1] = "test2";
        map.put("String", MockStringSQLData.class);
        map.put("Object", MockObjectSQLData.class);
    }

    public void testConstructor() throws SQLException {
        // OK
        sa = new SerialArray(mock);
        // array.getArray should not return null
        try {
            sa = new SerialArray(new MockNullArray());
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
    }

    public void testConstructor_map() throws SQLException {
        try {
            sa = new SerialArray(mock, null);
        } catch (SQLException e) {
            // expected
        }
        // array.getArray should not return null
        try {
            sa = new SerialArray(new MockNullArray(), null);
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }        
    }

    public void testGetArray() throws SerialException {
        for (int i = 0; i < strs.length; i++) {
            assertEquals(strs[i], ((Object[]) sa.getArray())[i]);
        }
    }

    public void testGetArrayLongInt() throws SerialException {
        for (int i = 0; i < strs.length; i++) {
            assertEquals(strs[i], ((Object[]) sa.getArray(i, 1))[0]);
        }
    }

    public void testGetArrayLongIntMapOfStringClassOfQ() {

    }

    public void testGetArrayMapOfStringClassOfQ() throws SerialException {
        for (int i = 0; i < strs.length; i++) {
            assertEquals(strs[i], ((Object[]) sa.getArray())[i]);
        }
    }

    public void testGetBaseType() throws SerialException {
        assertEquals(Types.ARRAY, sa.getBaseType());
    }

    public void testGetBaseTypeName() throws SQLException {
        assertEquals("BaseName", sa.getBaseTypeName());
    }

    public void testGetResultSet() throws SQLException {
        try {
            sa.getResultSet();
            fail("should throw UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // expected
        }
    }

    public void testGetResultSetLongInt() throws SQLException {
        try {
            sa.getResultSet(0, 1);
            fail("should throw UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // expected
        }
    }

    public void testGetResultSetLongIntMapOfStringClassOfQ()
            throws SQLException {
        try {
            sa.getResultSet(0, 1, null);
            fail("should throw UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // expected
        }
    }

    public void testGetResultSetMapOfStringClassOfQ() throws SerialException {
        try {
            sa.getResultSet(null);
            fail("should throw UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // expected
        }
    }

    class MockArray implements Array {

        public Object getArray() throws SQLException {
            return strs;
        }

        public Object getArray(long index, int count) throws SQLException {
            String[] ret = new String[count];
            for (int i = 0; i < ret.length; i++) {
                ret[i] = strs[(int) index + i];
            }
            return ret;
        }

        public Object getArray(long index, int count, Map<String, Class<?>> map)
                throws SQLException {
            String[] ret = new String[count];
            for (int i = 0; i < ret.length; i++) {
                ret[i] = strs[(int) index + i];
            }
            return ret;
        }

        public Object getArray(Map<String, Class<?>> map) throws SQLException {
            return strs;
        }

        public int getBaseType() throws SQLException {
            return Types.ARRAY;
        }

        public String getBaseTypeName() throws SQLException {
            return "BaseName";
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

    class MockNullArray implements Array {

        public Object getArray() throws SQLException {
            return null;
        }

        public Object getArray(long index, int count) throws SQLException {
            return null;
        }

        public Object getArray(long index, int count, Map<String, Class<?>> map)
                throws SQLException {
            return null;
        }

        public Object getArray(Map<String, Class<?>> map) throws SQLException {
            return strs;
        }

        public int getBaseType() throws SQLException {
            return Types.ARRAY;
        }

        public String getBaseTypeName() throws SQLException {
            return "BaseName";
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

    class MockStringSQLData implements SQLData {

        private String name = "java.lang.String";

        public String getSQLTypeName() throws SQLException {
            return name;
        }

        public void readSQL(SQLInput stream, String typeName)
                throws SQLException {
            return;
        }

        public void writeSQL(SQLOutput stream) throws SQLException {
            return;
        }
    }

    class MockObjectSQLData implements SQLData {

        private String name = "java.lang.Object";

        public String getSQLTypeName() throws SQLException {
            return name;
        }

        public void readSQL(SQLInput stream, String typeName)
                throws SQLException {
            return;
        }

        public void writeSQL(SQLOutput stream) throws SQLException {
            return;
        }
    }
}
