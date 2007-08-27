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

import javax.sql.rowset.CachedRowSet;

import junit.framework.TestCase;

public class CachedRowSetImplTest extends TestCase {

    private static final String DERBY_URL = "jdbc:derby:src/test/resources/TESTDB;create=true";

    private Connection conn;

    private Statement st;

    private ResultSet rs;

    private CachedRowSet crset;

    public void setUp() throws IllegalAccessException, InstantiationException,
            ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        conn = DriverManager.getConnection(DERBY_URL);
        st = conn.createStatement();
        rs = conn.getMetaData().getTables(null, null, "USER_INFO", null);
        if (!rs.next()) {
            st
                    .execute("create table USER_INFO (ID INT NOT NULL,NAME VARCHAR(10) NOT NULL)");
        }
        st.executeUpdate("delete from USER_INFO");
        st.executeUpdate("insert into USER_INFO(ID,NAME) values (1,'hermit')");
        st.executeUpdate("insert into USER_INFO(ID,NAME) values (2,'test')");
        rs = st.executeQuery("select * from USER_INFO");
        try {
            crset = (CachedRowSet) Class.forName(
                    "com.sun.rowset.CachedRowSetImpl").newInstance();
        } catch (ClassNotFoundException e) {
            System.out.println("Test Harmony");
            crset = (CachedRowSet) Class.forName(
                    "org.apache.harmony.sql.internal.rowset.CachedRowSetImpl")
                    .newInstance();
        }
//        crset.populate(rs);
//        rs = st.executeQuery("select * from USER_INFO");
    }

    public void tearDown() throws SQLException {
        if (rs != null) {
            rs.close();
        }
    }

    public void testAcceptChanges() throws SQLException {
//        rs.next();
//        assertEquals(1, rs.getInt(1));
//        crset.next();
//        assertEquals(1, crset.getInt(1));
//        crset.updateInt(1, 3);
//        assertEquals(3, crset.getInt(1));
//        try {
//            crset.acceptChanges();
//            fail("should throw SyncProviderException");
//        } catch (SQLException e) {
//            // expected;
//        }
    }

    public void testAcceptChangesConnection() throws SQLException {
//        rs.next();
//        assertEquals(1, rs.getInt(1));
//        crset.first();
//        assertEquals(1, crset.getInt(1));
//        crset.updateInt(1, 3);
//        assertEquals(3, crset.getInt(1));
//        crset.updateRow();
//        crset.acceptChanges(conn);
//        rs = st.executeQuery("select * from USER_INFO");
//        rs.next();
//        assertEquals(3, rs.getInt(1));
    }
}
