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
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;

import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
import javax.sql.RowSetMetaData;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.spi.SyncProviderException;

import junit.framework.TestCase;

public class CachedRowSetImplTest extends TestCase {

    private static final String DERBY_URL_Create = "jdbc:derby:src/test/resources/TESTDB;create=true";

    private static final String DERBY_URL = "jdbc:derby:src/test/resources/TESTDB";

    private Connection conn = null;

    private Statement st;

    private ResultSet rs;

    private CachedRowSet crset;

    private final static int DEFAULT_COLUMN_COUNT = 12;

    private final static int DEFAULT_ROW_COUNT = 4;

    public void setUp() throws Exception {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

        try {
            conn = DriverManager.getConnection(DERBY_URL);
        } catch (SQLException e) {
            try {
                conn = DriverManager.getConnection(DERBY_URL_Create);
            } catch (SQLException ee) {
                throw new SQLException("Create DB Failure!");
            }
        }

        st = conn.createStatement();
        rs = conn.getMetaData().getTables(null, "APP", "USER_INFO", null);
        String createTableSQL = "create table USER_INFO (ID INTEGER NOT NULL,NAME VARCHAR(10) NOT NULL, BIGINT_T BIGINT, "
                + "NUMERIC_T NUMERIC, DECIMAL_T DECIMAL, SMALLINT_T SMALLINT, FLOAT_T FLOAT, REAL_T REAL, DOUBLE_T DOUBLE,"
                + "DATE_T DATE, TIME_T TIME, TIMESTAMP_T TIMESTAMP)";
        String alterTableSQL = "ALTER TABLE USER_INFO  ADD CONSTRAINT USER_INFO_PK Primary Key (ID)";

        if (!rs.next()) {
            st.execute(createTableSQL);
            st.execute(alterTableSQL);
        }

        insertData();
        rs = st.executeQuery("select * from USER_INFO");
        try {
            crset = (CachedRowSet) Class.forName(
            "com.sun.rowset.CachedRowSetImpl").newInstance();
        } catch (ClassNotFoundException e) {

            crset = (CachedRowSet) Class.forName(
                    "org.apache.harmony.sql.internal.rowset.CachedRowSetImpl")
                    .newInstance();

            System.setProperty("Testing Harmony", "true");
        }
        crset.populate(rs);
        rs = st.executeQuery("select * from USER_INFO");
        crset.setUrl(DERBY_URL);
    }

    private void reloadCachedRowSet() throws SQLException {
        rs = st.executeQuery("select * from USER_INFO");
        crset.populate(rs);
        rs = st.executeQuery("select * from USER_INFO");
        crset.setUrl(DERBY_URL);
    }

    public void tearDown() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (crset != null) {
            crset.close();
        }
        if (st != null) {
            st.close();
        }
        if (conn != null) {
            /*
             * if doesn't call rollback, ri will throw exception then block
             * java.sql.SQLException: Invalid transaction state.
             */
            conn.rollback();
            conn.close();
        }
    }

    public void testSetSyncProvider() throws Exception {
        if (System.getProperty("Testing Harmony") == "true") {
            String mySyncProvider = "org.apache.harmony.sql.internal.rowset.HYOptimisticProvider";
            crset.setSyncProvider(mySyncProvider);
            assertEquals(crset.getSyncProvider().getClass().getCanonicalName(),
                    mySyncProvider);
        }
    }

    public void testColumnUpdatedInt() throws SQLException {
        crset.first();
        // try {
        // assertFalse(crset.columnUpdated(1));
        // fail("should throw SQLException");
        // } catch (SQLException e) {
        // // expected;
        // }
        crset.next();
        try {
            crset.columnUpdated(-1);
            fail("should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // expected;
        }
        try {
            crset.columnUpdated(0);
            fail("should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // expected;
        }
        assertFalse(crset.columnUpdated(1));
    }

    public void testColumnUpdatedString() throws SQLException {
        crset.first();
        // try {
        // assertFalse(crset.columnUpdated("ID"));
        // fail("should throw SQLException");
        // } catch (SQLException e) {
        // // expected;
        // }
        crset.next();
        try {
            assertFalse(crset.columnUpdated("Incorrect"));
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected;
        }
        assertFalse(crset.columnUpdated("NAME"));
    }

    public void testGetPageSize() throws SQLException {
        assertEquals(0, crset.getPageSize());
        crset.setPageSize(1);
        assertEquals(1, crset.getPageSize());
    }

    public void testSetPageSize() throws SQLException {
        try {
            crset.setPageSize(-1);
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected;
        }
        crset.setPageSize(0);
        crset.setPageSize(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, crset.getPageSize());
    }

    public void testGetTableName() throws SQLException {

        crset.setTableName("USER");
        assertEquals("USER", crset.getTableName());
    }

    public void testSetTableName() throws SQLException {
        try {
            crset.setTableName(null);
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected;
        }
    }

    public void testSize() throws Exception {
        assertEquals(DEFAULT_ROW_COUNT, crset.size());
    }

    public void testDeleteRow() throws SQLException {
        crset.first();
        // try {
        // crset.deleteRow();
        // fail("should throw SQLException");
        // } catch (SQLException e) {
        // // expected;
        // }
        crset.next();
        assertFalse(crset.rowDeleted());
        crset.deleteRow();
        assertEquals(DEFAULT_ROW_COUNT, crset.size());
        assertTrue(crset.rowDeleted());
    }

    public void testRowDeleted() throws SQLException {
        // try {
        // crset.rowDeleted();
        // fail("should throw SQLException");
        // } catch (SQLException e) {
        // // expected;
        // }
    }

    public void testInsertRow() throws SQLException {
        crset.first();
        try {
            crset.insertRow();
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected;
        }
        crset.next();
        try {
            crset.insertRow();
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected;
        }
        crset.moveToInsertRow();
        crset.updateString("Name", "TonyWu");
        crset.updateInt("ID", 3);
        crset.insertRow();
        assertEquals("TonyWu", crset.getString(2));
        assertEquals("TonyWu", crset.getString("Name"));
        assertEquals(3, crset.getInt(1));
        assertEquals(3, crset.getInt("ID"));

        crset.moveToCurrentRow();
        assertFalse(crset.rowInserted());

    }

    public void testAcceptChanges() throws SQLException {
        crset.setTableName("USER_INFO");
        // FIXME: if the value of column is null, it would go wrong when
        // call acceptChanges(). And if one method in TestCase throws
        // SQLException, the following method will be affected.
        rs.next();
        assertEquals(1, rs.getInt(1));
        assertEquals("hermit", rs.getString(2));

        crset.absolute(3);
        assertEquals(3, crset.getInt(1));
        assertEquals("test3", crset.getString(2));
        crset.updateString(2, "HarmonY");

        crset.moveToInsertRow();
        crset.updateInt(1, 16);
        crset.updateString(2, "Apache");
        crset.insertRow();
        crset.moveToCurrentRow();

        crset.deleteRow();
        /*
         * RI will print the change result
         */
        crset.acceptChanges();

        rs = st.executeQuery("select * from USER_INFO");
        rs.next();
        assertEquals(rs.getString(2), "hermit");
        rs.next();
        rs.next();
        assertEquals(rs.getString(2), "test4");

    }

    public void testExecute() throws SQLException {
        crset.setCommand("Update User_INFO set Name= ? Where ID= ? ");
        crset.setString(1, "executed!");
        crset.setInt(2, 1);
        crset.execute();

        crset.setCommand("SELECT ID, NAME FROM USER_INFO" + " WHERE ID = ? ");
        crset.setInt(1, 1);
        crset.execute();

        crset.first();
        assertEquals("executed!", crset.getString(2));

        crset.setCommand("Update User_INFO set Name= ? Where ID= ? ");
        crset.setString(1, "executed!");
        crset.setInt(2, 1);
        crset.execute(DriverManager.getConnection(DERBY_URL));

        crset.setCommand("SELECT ID, NAME FROM USER_INFO" + " WHERE ID = ? ");
        crset.setInt(1, 1);
        crset.execute(DriverManager.getConnection(DERBY_URL));
    }

    public void testExecute2() throws Exception {
        crset.setCommand("SELECT ID, NAME FROM USER_INFO" + " WHERE ID = ? ");
        crset.setInt(1, 1);
        crset.execute();

        crset.first();
        assertEquals("hermit", crset.getString(2));
    }

    public void testCreateShared() throws Exception {
        crset.setUsername("testUsername");
        crset.setPassword("testPassword");
        crset.setPageSize(5);
        Listener listener = new Listener(); // a class implements RowSetListener
        crset.addRowSetListener(listener);
        crset.absolute(3); // move to the third row for testing
        // TODO: when the cursor moved, notifyCursorMoved() should be called
        // assertEquals("cursorMoved", listener.getTag());

        CachedRowSet crsetShared = (CachedRowSet) crset.createShared();
        assertEquals("testUsername", crsetShared.getUsername());
        assertEquals("testPassword", crsetShared.getPassword());
        assertEquals(5, crsetShared.getPageSize());
        // check whether modify the attribute of the original is visible to the
        // duplicate
        crset.setUsername("modifyUsername");
        crset.setPageSize(10);
        assertEquals("modifyUsername", crset.getUsername());
        assertEquals("testUsername", crsetShared.getUsername());
        assertEquals(10, crset.getPageSize());
        assertEquals(5, crsetShared.getPageSize());

        // compare the current row, that is the third row
        assertEquals(3, crset.getInt(1));
        assertEquals("test3", crset.getString(2));
        assertEquals(3, crsetShared.getInt(1));
        assertEquals("test3", crsetShared.getString(2));
        // check whether update the duplicate is visible to the original
        crsetShared.updateString(2, "modify3");
        assertEquals("modify3", crsetShared.getString(2));
        assertEquals("modify3", crset.getString(2));
        crsetShared.updateRow();
        crsetShared.acceptChanges();
        assertEquals("rowSetChanged", listener.getTag());

        // when move the duplicate's cursor, the original shouldn't be affected
        crsetShared.absolute(1);
        assertEquals(1, crsetShared.getInt(1));
        assertEquals(3, crset.getInt(1));
    }

    public void testcreateCopyNoConstraints() throws Exception {
        crset.first();

        CachedRowSet crsetCopyNoConstraints = (CachedRowSet) crset
                .createCopyNoConstraints();

        // Modify he constraints
        crset.setReadOnly(false);
        crset.setConcurrency(ResultSet.CONCUR_READ_ONLY);
        crset.setType(ResultSet.TYPE_FORWARD_ONLY);

        crsetCopyNoConstraints.first();
        crsetCopyNoConstraints.updateString(2, "copyTest2");
        assertEquals(crsetCopyNoConstraints.getString(2), "copyTest2");
        assertEquals(crset.getString(2), "hermit");
        // the copyNoConstraints keep the default value of the CachedRowSet
        assertEquals(crsetCopyNoConstraints.isReadOnly(), true);
        assertEquals(crsetCopyNoConstraints.getConcurrency(),
                ResultSet.CONCUR_UPDATABLE);
        assertEquals(crsetCopyNoConstraints.getType(),
                ResultSet.TYPE_SCROLL_INSENSITIVE);

    }

    public void testCopySchema() throws Exception {
        // the original's addtribute and meta data
        crset.setCommand("testCommand");
        crset.setConcurrency(ResultSet.CONCUR_UPDATABLE);
        crset.setDataSourceName("testDataSource");
        crset.setFetchDirection(ResultSet.FETCH_UNKNOWN);
        crset.setPageSize(20);
        crset.setMaxRows(20);
        crset.setTableName("USER_INFO");
        /*
         * NOTICE: spec say copy must not has any content, but when run on RI,
         * if call next() before call createCopySchema(), the copy can get the
         * current row's data
         */

        /*
         * NOTICE: when run on RI, if add the listener first, then it will go
         * wrong when call createCopySchema().It's said that clone failed.
         */
        // Listener listener = new Listener();
        // crset.addRowSetListener(listener);
        RowSetMetaData rsmd = (RowSetMetaData) crset.getMetaData();
        // the copy
        CachedRowSet crsetCopySchema = crset.createCopySchema();
        RowSetMetaData rsmdCopySchema = (RowSetMetaData) crsetCopySchema
                .getMetaData();

        // compare the meta data between the duplicate and the original
        assertNotSame(crset.getMetaData(), crsetCopySchema.getMetaData());
        assertNotSame(crset.getOriginal(), crsetCopySchema.getOriginal());
        // assertNotSame(crset.getSyncProvider(), crsetCopySchema
        // .getSyncProvider());

        assertEquals("USER_INFO", crset.getTableName());
        assertEquals("USER_INFO", rsmdCopySchema.getTableName(1));
        assertEquals(DEFAULT_COLUMN_COUNT, rsmdCopySchema.getColumnCount());
        assertEquals(rsmd.getColumnName(1), rsmdCopySchema.getColumnName(1));
        // check the primary key
        // TODO: RI doesn't evalute the keyColumns. The value of
        // crset.getKeyColumns() is null.
        assertEquals(crset.getKeyColumns(), crsetCopySchema.getKeyColumns());

        // check the attributes in the duplicate. These are supposed to be the
        // same as the original
        // System.out.println("crsetCopySchema: " + crsetCopySchema.getInt(1));
        assertFalse(crsetCopySchema.next());
        assertEquals("testCommand", crsetCopySchema.getCommand());
        assertEquals(ResultSet.CONCUR_UPDATABLE, crsetCopySchema
                .getConcurrency());
        assertEquals("testDataSource", crsetCopySchema.getDataSourceName());
        assertEquals(ResultSet.FETCH_UNKNOWN, crsetCopySchema
                .getFetchDirection());
        assertEquals(20, crsetCopySchema.getPageSize());
        assertEquals(20, crsetCopySchema.getMaxRows());

        // fill the duplicate CachedRowSet with data, check the listener
        Listener listener = new Listener();
        crsetCopySchema.addRowSetListener(listener);
        assertNull(listener.getTag());
        rs = st.executeQuery("select * from USER_INFO");
        crsetCopySchema.populate(rs);
        // TODO: in the Harmony implementation, need to call notifyRowSetChanged
        // at the suitable place
        // assertEquals("rowSetChanged", listener.getTag());
        listener.setTag(null);
        // the move of the original's cursor shouldn't affect the duplicate
        crset.next();
        assertNull(listener.getTag());
    }

    public void testCreateCopy() throws Exception {

        crset.absolute(3);

        CachedRowSet crsetCopy = crset.createCopy();

        crsetCopy.updateString(2, "copyTest3");
        crsetCopy.updateRow();
        crsetCopy.acceptChanges();

        assertEquals(crsetCopy.getString(2), "copyTest3");

        assertEquals(crset.getString(2), "test3");

        rs = st.executeQuery("select * from USER_INFO");
        rs.next();
        rs.next();
        rs.next();
        assertEquals(rs.getString(2), "copyTest3");

        reloadCachedRowSet();
        crset.absolute(2);

        crsetCopy = crset.createCopy();

        assertEquals(crset.isReadOnly(), crsetCopy.isReadOnly());
        // TODO uncomment when isBeforeFirst is implemented
        // assertEquals(crset.isBeforeFirst(), crsetCopy.isBeforeFirst());
        // TODO uncomment when isAfterLast is implemented
        // assertEquals(crset.isAfterLast(), crsetCopy.isAfterLast());
        // TODO uncomment when isFirst is implemented
        // assertEquals(crset.isFirst(), crsetCopy.isFirst());
        // TODO uncomment when isLast is implemented
        // assertEquals(crset.isLast(), crsetCopy.isLast());

        assertEquals(crset.size(), crsetCopy.size());
        // different metaData object
        assertNotSame(crset.getMetaData(), crsetCopy.getMetaData());

        isMetaDataEquals(crset.getMetaData(), crsetCopy.getMetaData());

        assertEquals(crset.getCommand(), crsetCopy.getCommand());
        assertEquals(crset.getConcurrency(), crsetCopy.getConcurrency());

        // uncomment after implemented
        // try {
        // assertEquals(crset.getCursorName(), crsetCopy.getCursorName());
        // fail("Should throw SQLException");
        // } catch (SQLException e) {
        // // expected
        // }
        // try {
        // assertEquals(crset.getMatchColumnIndexes(), crsetCopy
        // .getMatchColumnIndexes());
        // fail("Should throw SQLException");
        // } catch (SQLException e) {
        // // expected
        // }
        //
        // try {
        // assertEquals(crset.getMatchColumnNames(), crsetCopy
        // .getMatchColumnNames());
        // } catch (SQLException e) {
        // // expected
        // }
        // assertEquals(crset.getRow(), crsetCopy.getRow());
        // assertEquals(crset.getStatement(), crsetCopy.getStatement());
        // assertNotSame(crset.getWarnings(), crsetCopy.getWarnings());

        assertEquals(crset.getEscapeProcessing(), crsetCopy
                .getEscapeProcessing());
        assertEquals(crset.getFetchDirection(), crsetCopy.getFetchDirection());
        assertEquals(crset.getFetchSize(), crsetCopy.getFetchSize());
        if (crset.getKeyColumns() == null) {
            assertNull(crsetCopy.getKeyColumns());
        } else {
            int[] keyColumns = crset.getKeyColumns();
            int[] copyKeyColumns = crsetCopy.getKeyColumns();

            assertEquals(keyColumns.length, copyKeyColumns.length);
            for (int i = 0; i < keyColumns.length; i++) {
                assertEquals(keyColumns[i], copyKeyColumns[i]);
            }
            assertEquals(crset.getKeyColumns(), crsetCopy.getKeyColumns());
        }

        assertEquals(crset.getMaxFieldSize(), crsetCopy.getMaxFieldSize());
        assertEquals(crset.getMaxRows(), crsetCopy.getMaxRows());

        assertEquals(crset.getPageSize(), crsetCopy.getPageSize());
        assertEquals(crset.getPassword(), crsetCopy.getPassword());
        assertEquals(crset.getQueryTimeout(), crsetCopy.getQueryTimeout());
        assertEquals(crset.getShowDeleted(), crsetCopy.getShowDeleted());

        assertEquals(crset.getSyncProvider().getProviderID(), crsetCopy
                .getSyncProvider().getProviderID());
        assertEquals(crset.getSyncProvider().getProviderGrade(), crsetCopy
                .getSyncProvider().getProviderGrade());
        assertEquals(crset.getSyncProvider().getDataSourceLock(), crsetCopy
                .getSyncProvider().getDataSourceLock());
        assertEquals(crset.getSyncProvider().getVendor(), crsetCopy
                .getSyncProvider().getVendor());
        assertEquals(crset.getSyncProvider().getVersion(), crsetCopy
                .getSyncProvider().getVersion());

        assertEquals(crset.getTableName(), crsetCopy.getTableName());
        assertEquals(crset.getTransactionIsolation(), crsetCopy
                .getTransactionIsolation());
        assertEquals(crset.getType(), crsetCopy.getType());

        assertEquals(crset.getUrl(), crsetCopy.getUrl());
        assertEquals(crset.getUsername(), crsetCopy.getUsername());

    }

    public void testCreateCopy2() throws Exception {

        CachedRowSet copy = crset.createCopy();

        copy.absolute(3);
        crset.absolute(3);

        copy.updateString(2, "updated");
        assertEquals("updated", copy.getString(2));
        assertEquals("test3", crset.getString(2));
        copy.updateRow();
        copy.acceptChanges();

        assertEquals(copy.getString(2), "updated");
        assertEquals(crset.getString(2), "test3");

        crset.updateString(2, "again");

        assertEquals(copy.getString(2), "updated");
        assertEquals(crset.getString(2), "again");

        crset.updateRow();
        try {
            /*
             * seems ri doesn't release lock when expception throw from
             * acceptChanges(), which will cause test case block at insertData()
             * when next test case setUp, so we must pass current connection to
             * it, and all resource would be released after connection closed.
             */
            crset.acceptChanges(conn);
            // TODO: wait the implementation of Writer
            // fail("Should throw SyncProviderException");
        } catch (SyncProviderException e) {
            // expected
        }

        assertEquals(copy.getString(2), "updated");

        crset.absolute(3);
        // data doesn't change
        assertEquals("again", crset.getString(2));
    }

    public void testCreateCopy3() throws Exception {
        crset.setCommand("SELECT * FROM USER_INFO WHERE ID = ?");
        crset.setInt(1, 3);
        crset.execute();

        assertEquals(12, crset.getMetaData().getColumnCount());
        assertTrue(crset.next());
        assertEquals("test3", crset.getString(2));
        assertFalse(crset.next());

        CachedRowSet crsetCopy = crset.createCopy();
        crsetCopy.execute();
        assertEquals(12, crsetCopy.getMetaData().getColumnCount());
        assertTrue(crsetCopy.next());
        assertEquals("test3", crsetCopy.getString(2));
        assertFalse(crsetCopy.next());

        crsetCopy.setCommand("SELECT * FROM USER_INFO WHERE NAME = ?");
        crsetCopy.setString(1, "test4");
        crsetCopy.execute();
        assertTrue(crsetCopy.next());
        assertEquals(4, crsetCopy.getInt(1));
        assertFalse(crsetCopy.next());

        crset.execute();
        assertTrue(crset.next());
        assertEquals("test3", crset.getString(2));
        assertFalse(crset.next());
    }

    private void isMetaDataEquals(ResultSetMetaData expected,
            ResultSetMetaData actual) throws SQLException {
        assertEquals(expected.getColumnCount(), actual.getColumnCount());

        int columnCount = expected.getColumnCount();

        for (int column = 1; column <= columnCount; column++) {
            assertEquals(expected.isAutoIncrement(column), actual
                    .isAutoIncrement(column));
            assertEquals(expected.isCaseSensitive(column), actual
                    .isCaseSensitive(column));
            assertEquals(expected.isCurrency(column), actual.isCurrency(column));
            assertEquals(expected.isDefinitelyWritable(column), actual
                    .isDefinitelyWritable(column));
            assertEquals(expected.isReadOnly(column), actual.isReadOnly(column));
            assertEquals(expected.isSearchable(column), actual
                    .isSearchable(column));
            assertEquals(expected.isSigned(column), actual.isSigned(column));
            assertEquals(expected.isWritable(column), actual.isWritable(column));
            assertEquals(expected.isNullable(column), actual.isNullable(column));
            assertEquals(expected.getCatalogName(column), actual
                    .getCatalogName(column));
            assertEquals(expected.getColumnClassName(column), actual
                    .getColumnClassName(column));
            assertEquals(expected.getColumnDisplaySize(column), actual
                    .getColumnDisplaySize(column));
            assertEquals(expected.getColumnLabel(column), actual
                    .getColumnLabel(column));
            assertEquals(expected.getColumnName(column), actual
                    .getColumnName(column));
            assertEquals(expected.getColumnType(column), actual
                    .getColumnType(column));
            assertEquals(expected.getColumnTypeName(column), actual
                    .getColumnTypeName(column));
            assertEquals(expected.getPrecision(column), actual
                    .getPrecision(column));
            assertEquals(expected.getScale(column), actual.getScale(column));
            assertEquals(expected.getSchemaName(column), actual
                    .getSchemaName(column));
            assertEquals(expected.getTableName(column), actual
                    .getTableName(column));
        }
    }

    public void testAfterLast() throws Exception {
        try {
            rs.afterLast();
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected
        }

        crset.afterLast();
        crset.previous();
        assertEquals(4, crset.getInt(1));
    }

    public void testNextandPreviousPage() throws Exception {

        st.executeUpdate("delete from USER_INFO");
        st.executeUpdate("insert into USER_INFO(ID,NAME) values (1,'1')");
        st.executeUpdate("insert into USER_INFO(ID,NAME) values (2,'2')");
        st.executeUpdate("insert into USER_INFO(ID,NAME) values (3,'3')");
        st.executeUpdate("insert into USER_INFO(ID,NAME) values (4,'4')");
        rs = st.executeQuery("select * from USER_INFO");

        crset.setPageSize(2);
        crset.setCommand("SELECT ID FROM USER_INFO");
        crset.execute();

        for (int j = 0; j < 2; j++)
            crset.next();
        assertFalse(crset.next());

        int i = 0;

        crset.beforeFirst();
        while (crset.nextPage()) {
            while (crset.next()) {
                assertEquals(++i, crset.getInt(1));
            }
        }

        while (crset.previousPage()) {
            crset.afterLast();
            while (crset.previous()) {
                assertEquals(i--, crset.getInt(1));
            }
        }

        while (crset.previousPage()) {
            i = i - crset.getPageSize();
            int j = i;
            while (crset.next()) {
                assertEquals(++j, crset.getInt(1));
            }
        }
    }

    public void testPopulate() throws Exception {
        CachedRowSet cc = crset.createCopy();

        try {
            crset.populate(rs, 0);
            fail("should throw exception");
        } catch (Exception e) {
            // expected
        }
        crset.populate(rs);
        crset.first();
        assertEquals("hermit", crset.getString(2));

        crset.populate(cc, 2);
        crset.first();
        assertEquals("test", crset.getString(2));

        crset.populate(cc, 1);
        crset.first();
        assertEquals("hermit", crset.getString(2));
    }

    private void insertData() throws Exception {

        st.executeUpdate("delete from USER_INFO");

        // first row
        st.executeUpdate("insert into USER_INFO(ID,NAME) values (1,'hermit')");
        // second row
        st.executeUpdate("insert into USER_INFO(ID,NAME) values (2,'test')");

        String insertSQL = "INSERT INTO USER_INFO(ID, NAME, BIGINT_T, NUMERIC_T, DECIMAL_T, SMALLINT_T, "
                + "FLOAT_T, REAL_T, DOUBLE_T, DATE_T, TIME_T, TIMESTAMP_T) VALUES(?, ?, ?, ?, ?, ?,"
                + "?, ?, ?, ?, ?, ? )";
        PreparedStatement preStmt = conn.prepareStatement(insertSQL);
        // third row
        preStmt.setInt(1, 3);
        preStmt.setString(2, "test3");
        preStmt.setLong(3, 3333L);
        preStmt.setBigDecimal(4, new BigDecimal(123));
        preStmt.setBigDecimal(5, new BigDecimal(23));
        preStmt.setInt(6, 13);
        preStmt.setFloat(7, 3.7F);
        preStmt.setFloat(8, 3.888F);
        preStmt.setDouble(9, 3.9999);
        preStmt.setDate(10, new Date(523654123));
        preStmt.setTime(11, new Time(966554221));
        preStmt.setTimestamp(12, new Timestamp(521342100));
        preStmt.executeUpdate();
        // fourth row
        preStmt.setInt(1, 4);
        preStmt.setString(2, "test4");
        preStmt.setLong(3, 444423L);
        preStmt.setBigDecimal(4, new BigDecimal(12));
        preStmt.setBigDecimal(5, new BigDecimal(23));
        preStmt.setInt(6, 41);
        preStmt.setFloat(7, 4.8F);
        preStmt.setFloat(8, 4.888F);
        preStmt.setDouble(9, 4.9999);
        preStmt.setDate(10, new Date(965324512));
        preStmt.setTime(11, new Time(452368512));
        preStmt.setTimestamp(12, new Timestamp(874532105));
        preStmt.executeUpdate();

        if (preStmt != null) {
            preStmt.close();
        }
    }

    public class Listener implements RowSetListener, Cloneable {

        private String tag = null;

        public void cursorMoved(RowSetEvent theEvent) {
            tag = "cursorMoved";
        }

        public void rowChanged(RowSetEvent theEvent) {
            tag = "rowChanged";
        }

        public void rowSetChanged(RowSetEvent theEvent) {
            tag = "rowSetChanged";
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public Listener clone() throws CloneNotSupportedException {
            Listener listener = (Listener) super.clone();
            listener.tag = tag;
            return listener;
        }
    }
}
