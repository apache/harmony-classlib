/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.sql.tests.internal.rowset;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.sql.rowset.spi.SyncProviderException;

public class CachedRowSetUpdateTest extends CachedRowSetTestCase {
    public void testUpdateValue() throws Exception {

        try {
            crset.updateString(1, "");
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Invalid cursor position
        }

        try {
            crset.updateString(-1, "");
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Invalid column index
        }

        crset.moveToInsertRow();

        try {
            crset.updateDate(1, new Date(10000));
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Data Type Mismatch
        }

        crset.updateInt(1, 100);
        crset.updateString(2, "test8");

        try {
            crset.updateBytes(2, "hello".getBytes());
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Data Type Mismatch
        }

        crset.updateLong(3, 444423L);
        crset.updateBigDecimal(4, new BigDecimal(12));
        crset.updateBigDecimal(5, new BigDecimal(23));
        crset.updateInt(6, 41);
        crset.updateFloat(7, 4.8F);
        crset.updateFloat(8, 4.888F);
        crset.updateDouble(9, 4.9999);
        crset.updateDate(10, new Date(965324512));
        crset.updateTime(11, new Time(452368512));
        crset.updateTimestamp(12, new Timestamp(874532105));

        crset.insertRow();
        crset.moveToCurrentRow();
        crset.acceptChanges(conn);

    }

    public void testUpdateLong() throws SQLException {
        crset.moveToInsertRow();

        crset.updateInt(1, 100);
        crset.updateString(2, "update");

        crset.updateLong(3, 444423L);
        crset.updateInt(3, 100);
        crset.updateShort(3, (short) 100);
        crset.updateByte(3, (byte) 10);
        crset.updateBigDecimal(3, new BigDecimal(Long.MAX_VALUE));
        crset.updateString(3, "1000");

        try {
            crset.updateBigDecimal(3, new BigDecimal(Double.MAX_VALUE));
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Data Type Mismatch
        }

        try {
            crset.updateFloat(3, 80.98F);
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Data Type Mismatch
        }

        try {
            crset.updateDouble(3, 80.98);
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Data Type Mismatch
        }

        try {
            crset.updateBoolean(3, false);
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Data Type Mismatch
        }

        crset.updateBigDecimal(4, new BigDecimal(12));
        crset.updateBigDecimal(5, new BigDecimal(23));
        crset.updateInt(6, 41);
        crset.updateFloat(7, 4.8F);
        crset.updateFloat(8, 4.888F);
        crset.updateDouble(9, 4.9999);
        crset.updateDate(10, new Date(965324512));
        crset.updateTime(11, new Time(452368512));
        crset.updateTimestamp(12, new Timestamp(874532105));
        crset.insertRow();
        crset.moveToCurrentRow();
        crset.acceptChanges(conn);

    }

    public void testUpdateInt() throws SQLException {
        crset.moveToInsertRow();

        crset.updateInt(1, 50);
        crset.updateByte(1, (byte) 10);
        crset.updateShort(1, (short) 60);
        crset.updateLong(1, 100L);
        crset.updateBigDecimal(1, new BigDecimal(50));
        crset.updateString(1, "100");

        try {
            crset.updateBigDecimal(1, new BigDecimal(Double.MAX_VALUE));
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Data Type Mismatch
        }

        try {
            crset.updateLong(1, 100000000000L);
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Data Type Mismatch
        }

        try {
            crset.updateFloat(1, 80.98F);
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Data Type Mismatch
        }

        try {
            crset.updateDouble(1, 80.98);
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Data Type Mismatch
        }

        try {
            crset.updateBoolean(1, false);
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Data Type Mismatch
        }

        crset.updateString(2, "test100");
        crset.updateLong(3, 444423L);
        crset.updateBigDecimal(4, new BigDecimal(12));
        crset.updateBigDecimal(5, new BigDecimal(23));
        crset.updateInt(6, 41);
        crset.updateFloat(7, 4.8F);
        crset.updateFloat(8, 4.888F);
        crset.updateDouble(9, 4.9999);
        crset.updateDate(10, new Date(965324512));
        crset.updateTime(11, new Time(452368512));
        crset.updateTimestamp(12, new Timestamp(874532105));
        crset.insertRow();
        crset.moveToCurrentRow();
        crset.acceptChanges(conn);

        ResultSet rs = st
                .executeQuery("SELECT * FROM USER_INFO WHERE ID = 100");
        assertTrue(rs.next());
    }

    public void testUpdateShort() throws SQLException {
        crset.moveToInsertRow();

        crset.updateInt(1, 50);
        crset.updateString(2, "test100");
        crset.updateLong(3, 444423L);
        crset.updateBigDecimal(4, new BigDecimal(12));
        crset.updateBigDecimal(5, new BigDecimal(23));

        crset.updateShort(6, (short) 10);
        crset.updateByte(6, (byte) 10);
        crset.updateInt(6, 41);
        crset.updateLong(6, 33);
        crset.updateBigDecimal(6, new BigDecimal(23));

        try {
            crset.updateBigDecimal(6, new BigDecimal(Double.MAX_VALUE));
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Data Type Mismatch
        }

        try {
            crset.updateLong(6, 100000000000L);
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Data Type Mismatch
        }

        try {
            crset.updateInt(6, Integer.MAX_VALUE);
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Data Type Mismatch
        }

        try {
            crset.updateFloat(6, 80.98F);
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Data Type Mismatch
        }

        try {
            crset.updateDouble(6, 80.98);
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Data Type Mismatch
        }

        try {
            crset.updateBoolean(6, false);
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Data Type Mismatch
        }

        crset.updateFloat(7, 4.8F);
        crset.updateFloat(8, 4.888F);
        crset.updateDouble(9, 4.9999);
        crset.updateDate(10, new Date(965324512));
        crset.updateTime(11, new Time(452368512));
        crset.updateTimestamp(12, new Timestamp(874532105));
        crset.insertRow();
        crset.moveToCurrentRow();
        crset.acceptChanges(conn);
    }

    public void testUpdateFloat() throws SQLException {
        crset.moveToInsertRow();

        crset.updateInt(1, 50);
        crset.updateString(2, "test100");
        crset.updateLong(3, 444423L);
        crset.updateBigDecimal(4, new BigDecimal(12));
        crset.updateBigDecimal(5, new BigDecimal(23));
        crset.updateInt(6, 41);

        crset.updateFloat(7, 4.8F);
        crset.updateDouble(7, 80.98);

        crset.updateInt(7, 10000);
        crset.updateLong(7, 10000);
        crset.updateShort(7, (short) 10);
        crset.updateByte(7, (byte) 10);
        crset.updateBigDecimal(7, new BigDecimal(100));
        crset.updateDouble(7, Float.MAX_VALUE);

        // throw exception when acceptChange
        // crset.updateDouble(7, Double.MAX_VALUE);
        // crset.updateBigDecimal(7, new BigDecimal(Double.MAX_VALUE));

        try {
            crset.updateBoolean(1, false);
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Data Type Mismatch
        }

        crset.updateFloat(8, 4.888F);
        crset.updateDouble(9, 4.9999);
        crset.updateDate(10, new Date(965324512));
        crset.updateTime(11, new Time(452368512));
        crset.updateTimestamp(12, new Timestamp(874532105));
        crset.insertRow();
        crset.moveToCurrentRow();
        crset.acceptChanges(conn);
    }

    public void testUpdateFloat_Exception() throws SQLException {
        crset.moveToInsertRow();
        crset.updateInt(1, 50);
        crset.updateString(2, "test100");
        crset.updateLong(3, 444423L);
        crset.updateBigDecimal(4, new BigDecimal(12));
        crset.updateBigDecimal(5, new BigDecimal(23));
        crset.updateInt(6, 41);

        // over range
        crset.updateDouble(7, Double.MAX_VALUE);

        crset.updateFloat(8, 4.888F);
        crset.updateDouble(9, 4.9999);
        crset.updateDate(10, new Date(965324512));
        crset.updateTime(11, new Time(452368512));
        crset.updateTimestamp(12, new Timestamp(874532105));
        crset.insertRow();
        crset.moveToCurrentRow();

        try {
            crset.acceptChanges(conn);
            fail("Should throw SyncProviderException");
        } catch (SyncProviderException e) {
            // expected
        }
    }

    public void testUpdateBigDecimal() throws SQLException {
        crset.moveToInsertRow();
        crset.updateInt(1, 50);
        crset.updateString(2, "test100");
        crset.updateLong(3, 444423L);

        crset.updateBigDecimal(4, new BigDecimal(12));
        crset.updateByte(4, (byte) 10);
        crset.updateShort(4, (short) 10);
        crset.updateInt(4, 300);
        crset.updateLong(4, 2000);
        crset.updateFloat(4, 3.59F);
        crset.updateDouble(4, 3.4994);

        crset.updateBigDecimal(5, new BigDecimal(23));
        crset.updateInt(6, 41);
        crset.updateDouble(7, Float.MAX_VALUE);
        crset.updateFloat(8, 4.888F);
        crset.updateDouble(9, 4.9999);
        crset.updateDate(10, new Date(965324512));
        crset.updateTime(11, new Time(452368512));
        crset.updateTimestamp(12, new Timestamp(874532105));

        crset.insertRow();
        crset.moveToCurrentRow();

        crset.acceptChanges(conn);
    }

    public void testUpdateObject() throws SQLException {
        crset.moveToInsertRow();

        // updateObject doesn't check type compatibility
        crset.updateObject(1, new Date(1000));
        crset.updateObject(2, new ArrayList<Object>());

        try {
            crset.updateDate(1, new Date(1000));
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Data Type Mismatch
        }

        crset.updateObject(1, null);

        try {
            crset.updateBigDecimal(7, null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        crset.updateString(2, null);

        // TODO test Array, Blob, Clob
        // crset.updateArray(3, null);
    }

    public void testUpdateObject_I_LObject_I() throws Exception {
        crset.moveToInsertRow();

        crset.updateInt(1, 50);
        crset.updateString(2, "test100");
        crset.updateLong(3, 444423L);
        crset.updateBigDecimal(4, new BigDecimal(12));

        crset.updateObject(5, new BigDecimal("3.1200"), 3);

        BigDecimal bigDecimal = new BigDecimal("3.1200");
        BigDecimal scaled = bigDecimal.setScale(3);

        if ("true".equals(System.getProperty("Testing Harmony"))) {
            assertEquals(scaled, crset.getBigDecimal(5));
            assertEquals(scaled, crset.getObject(5));
        } else {
            /*
             * seems ri doesn't do scale
             */
            assertEquals(bigDecimal, crset.getBigDecimal(5));
            assertEquals(bigDecimal, crset.getObject(5));
        }

        try {
            crset.updateObject(5, new BigDecimal("3.1200"), 1);
            fail("Should throw ArithmeticException");
        } catch (ArithmeticException e) {
            // expected
        }

        try {
            crset.updateObject(5, null, 1);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        try {
            crset.updateObject(5, Double.valueOf(3.0000), 1);
            fail("Should throw ClassCastException");
        } catch (ClassCastException e) {
            // ri throw ClassCastException, we follow ri
        }

        try {
            crset.updateObject(5, Float.valueOf(3.0000F), 3);
            fail("Should throw ClassCastException");
        } catch (ClassCastException e) {
            // ri throw ClassCastException, we follow ri
        }

        try {
            crset.updateObject(5, Integer.valueOf(3), 3);
            fail("Should throw ClassCastException");
        } catch (ClassCastException e) {
            // ri throw ClassCastException, we follow ri
        }

        try {
            crset.updateObject(5, new ArrayList<Object>(), 3);
            fail("Should throw ClassCastException");
        } catch (ClassCastException e) {
            // ri throw ClassCastException, we follow ri
        }

        crset.updateInt(6, 41);
        crset.updateFloat(7, 4.8F);
        crset.updateFloat(8, 4.888F);
        crset.updateDouble(9, 4.9999);
        crset.updateDate(10, new Date(965324512));
        crset.updateTime(11, new Time(452368512));
        crset.updateTimestamp(12, new Timestamp(874532105));
        crset.insertRow();
        crset.moveToCurrentRow();
        crset.acceptChanges(conn);
    }
}
