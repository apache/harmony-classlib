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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.RowSetMetaData;
import javax.sql.rowset.CachedRowSet;

import junit.framework.TestCase;

public class CachedRowSetImplTest extends TestCase {

    private static final String DERBY_URL_Create = "jdbc:derby:src/test/resources/TESTDB;create=true";

    private static final String DERBY_URL = "jdbc:derby:src/test/resources/TESTDB";

    private Connection conn;

    private Statement st;

    private ResultSet rs;

    private CachedRowSet crset;

    public void setUp() throws IllegalAccessException, InstantiationException,
            ClassNotFoundException, SQLException {
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
        ;

        st = conn.createStatement();

        rs = conn.getMetaData().getTables(null, "APP", "USER_INFO", null);
        // careful: Integer, rather than int!

        if (!rs.next()) {
            st
                    .execute("create table USER_INFO (ID INTEGER NOT NULL,NAME VARCHAR(10) NOT NULL)");
            st
                    .execute("ALTER TABLE USER_INFO  ADD CONSTRAINT USER_INFO_PK Primary Key (ID)");
        }

        st.executeUpdate("delete from USER_INFO");
        st.executeUpdate("insert into USER_INFO(ID,NAME) values (1,'hermit')");
        st.executeUpdate("insert into USER_INFO(ID,NAME) values (2,'test')");
        rs = st.executeQuery("select * from USER_INFO");
        try {
            crset = (CachedRowSet) Class.forName(

            "com.sun.rowset.CachedRowSetImpl").newInstance();

            System.out.println("Testing RI");
        } catch (ClassNotFoundException e) {

            crset = (CachedRowSet) Class.forName(
                    "org.apache.harmony.sql.internal.rowset.CachedRowSetImpl")
                    .newInstance();

            System.setProperty("Testing Harmony", "true");
            System.out.println("Testing Harmony");

        }
        crset.populate(rs);
        rs = st.executeQuery("select * from USER_INFO");
        crset.setUrl(DERBY_URL);
    }

    public void tearDown() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (crset != null)
            crset.close();
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

    public void testSize() {
        assertEquals(2, crset.size());
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
        assertEquals(2, crset.size());
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
        rs.next();
        assertEquals(1, rs.getInt(1));

        assertEquals("hermit", rs.getString(2));
        crset.first();

        assertEquals(1, crset.getInt(1));

        assertEquals("hermit", crset.getString(2));
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
        assertEquals(rs.getString(2), "test");
        rs.next();
        assertEquals(rs.getString(2), "Apache");

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
        CachedRowSet crsetShared = (CachedRowSet) crset.createShared();
        crsetShared.first();
        crset.first();
        crsetShared.updateString(2, "copyTest2");
        assertEquals(crsetShared.getString(2), "copyTest2");
        assertEquals(crset.getString(2), "copyTest2");
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
        crset.first();
        CachedRowSet crsetCopySchema = (CachedRowSet) crset.createCopySchema();

        RowSetMetaData rsmCopySchema = (RowSetMetaData) crsetCopySchema
                .getMetaData();
        assertEquals("USER_INFO", rsmCopySchema.getTableName(1));
        assertEquals(2, rsmCopySchema.getColumnCount());

        RowSetMetaData rsm = (RowSetMetaData) crset.getMetaData();
        rsm.setTableName(1, "newBorn");
        assertEquals("newBorn", rsm.getTableName(1));

        crset.setTableName("test");
        assertEquals("test", crset.getTableName());
    }

    public void testCreateCopy() throws Exception {
        crset.first();
        assertEquals(crset.getString(2), "hermit");
        crset.updateString(2, "copyTest");
        crset.updateRow();
        crset.acceptChanges();

        rs = st.executeQuery("select * from USER_INFO");
        rs.next();
        assertEquals(rs.getString(2), "copyTest");

        CachedRowSet crsetCopy = (CachedRowSet) crset.createCopy();

        crsetCopy.first();
        crsetCopy.updateString(2, "copyTest2");
        crsetCopy.updateRow();
        crsetCopy.acceptChanges();

        assertEquals(crsetCopy.getString(2), "copyTest2");
        assertEquals(crset.getString(2), "copyTest");

        rs = st.executeQuery("select * from USER_INFO");
        rs.next();
        assertEquals(rs.getString(2), "copyTest2");

        crset.first();
        assertEquals(crset.getString(2), "copyTest");
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
        assertEquals(2, crset.getInt(1));
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
}
