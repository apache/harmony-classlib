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

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.sql.rowset.serial.SQLOutputImpl;

import junit.framework.TestCase;

public class SQLOutputImplTest extends TestCase {
    
    private static SQLOutputImpl impl;
    
    private static Vector attr;
    
    /**
     * @tests {@link javax.sql.rowset.serial.SQLOutputImpl#SQLOutputImpl(Vector, Map)}
     */
    public void test_ConstructorLjava_util_VectorLjava_util_Map() {
        assertNotNull(impl);
        
        try {
            new SQLOutputImpl(null, new HashMap());
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
        
        try {
            new SQLOutputImpl(null, null);
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
        
        try {
            new SQLOutputImpl(new Vector(), null);
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
    }

    public void testWriteArray() {

    }

    public void testWriteAsciiStream() {

    }

    public void testWriteBigDecimal() {

    }

    public void testWriteBinaryStream() {

    }

    public void testWriteBlob() {

    }

    /**
     * @tests {@link javax.sql.rowset.serial.SQLOutputImpl#writeBoolean(boolean)}
     */
    public void test_writeBooleanZ() throws SQLException {
        impl.writeBoolean(true);
        assertTrue(((Boolean)attr.get(0)).booleanValue());
        
        impl.writeBoolean(false);
        assertFalse(((Boolean)attr.get(1)).booleanValue());
        
        impl.writeBoolean(true);
        assertTrue(((Boolean)attr.get(2)).booleanValue());
    }

    /**
     * @tests {@link javax.sql.rowset.serial.SQLOutputImpl#writeByte(byte)}
     */
    public void test_writeByteB() throws SQLException {
        assertEquals(0, attr.size());
        impl.writeByte((byte)1);
        assertEquals(1, attr.size());
        assertEquals(Byte.parseByte("1"), ((Byte)attr.get(0)).byteValue());
        
        impl.writeByte((byte) 256);
        assertEquals(2, attr.size());
        assertEquals(Byte.parseByte("0"), ((Byte)attr.get(1)).byteValue());
    }

    /**
     * @tests {@link javax.sql.rowset.serial.SQLOutputImpl#writeBytes(byte[])}
     */
    public void test_writeBytes$B() throws SQLException {
        byte[] bytes = new byte[] {4, 5, (byte)256};
        impl.writeBytes(bytes);
        assertEquals(1, attr.size());
        
        byte[] attrBytes = (byte[])attr.get(0);
        assertEquals((byte)4, attrBytes[0]);
        assertEquals((byte)0, attrBytes[2]);
    }

    public void testWriteCharacterStream() {

    }

    public void testWriteClob() {

    }

    public void testWriteDate() {

    }

    /**
     * @tests {@link javax.sql.rowset.serial.SQLOutputImpl#writeDouble(double)}
     */
    public void test_writeDoubleD() throws SQLException {
        Object obj = new Object();
        attr.add(obj);
        impl.writeDouble(3.1415926);
        assertEquals(2, attr.size());
        
        assertEquals(obj, attr.get(0));
        assertEquals(3.1415926, ((Double)attr.get(1)).doubleValue(), 0);
    }

    /**
     * @tests {@link javax.sql.rowset.serial.SQLOutputImpl#writeFloat(float)}
     */
    public void test_writeFloatF() throws SQLException {
        impl.writeFloat(3.14f);
        assertEquals(3.14f, ((Float) attr.get(0)).floatValue(), 0);

        impl.writeFloat(Float.MAX_VALUE);
        assertEquals(3.14f, ((Float) attr.get(0)).floatValue(), 0);
        assertEquals(Float.MAX_VALUE, ((Float) attr.get(1)).floatValue(), 0);
    }

    /**
     * @tests {@link javax.sql.rowset.serial.SQLOutputImpl#writeInt(int)}
     */
    public void test_writeIntI() throws SQLException {
        impl.writeInt(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, ((Integer)attr.get(0)).intValue());
    }

    /**
     * @tests {@link javax.sql.rowset.serial.SQLOutputImpl#writeLong(long)}
     */
    public void test_writeLongJ() throws SQLException {
        impl.writeInt(Integer.MIN_VALUE);
        impl.writeLong(Long.MAX_VALUE);
        assertEquals(Long.MAX_VALUE, ((Long)attr.get(1)).longValue());
    }

    public void testWriteObject() {

    }

    public void testWriteRef() {

    }

    /**
     * @tests {@link javax.sql.rowset.serial.SQLOutputImpl#writeShort(short)}
     */
    public void test_writeShortS() throws SQLException {
        impl.writeShort((short) 12823);
        assertEquals((short) 12823, ((Short) attr.get(0)).shortValue());
        impl.writeShort((short) 32768);
        assertEquals((short) -32768, ((Short) attr.get(1)).shortValue());
    }

    public void testWriteString() {

    }

    public void testWriteStruct() {

    }

    public void testWriteTime() {

    }

    public void testWriteTimestamp() {

    }

    public void testWriteURL() {

    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        attr = new Vector();
        Map<String, Class<?>> map = new HashMap<String, Class<?>>();
        impl = new SQLOutputImpl(attr, map);
    }

    @Override
    protected void tearDown() throws Exception {
        impl = null;
        attr = null;
        super.tearDown();
    }
}
