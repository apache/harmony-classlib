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

import java.sql.SQLException;
import javax.sql.RowSet;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.WebRowSet;

public class JoinRowSetTestCase extends CachedRowSetTestCase {

    protected JoinRowSet jrs;

    public void setUp() throws Exception {
        super.setUp();
        createBookTable();
        jrs = newJoinRowSet();
    }

    public void insertDataToCustomerTable() throws Exception {
        st.executeUpdate("delete from CUSTOMER_INFO");
        st
                .executeUpdate("insert into CUSTOMER_INFO(ID,NAME) values (1111,'customer_one')");
        st
                .executeUpdate("insert into CUSTOMER_INFO(ID,NAME) values (5555,'customer_two')");
        st
                .executeUpdate("insert into CUSTOMER_INFO(ID,NAME) values (3,'test3')");
        st
                .executeUpdate("insert into CUSTOMER_INFO(ID,NAME) values (4,'test4')");
    }

    public void createBookTable() throws Exception {
        st = conn.createStatement();
        rs = conn.getMetaData().getTables(null, "APP", "BOOKS", null);
        String createTableSQL = "create table BOOKS (AUTHORID INTEGER NOT NULL,SN VARCHAR(30) NOT NULL,NAME VARCHAR(30))";

        if (rs.next()) {
            st.execute("drop table BOOKS");
        }
        st.execute(createTableSQL);

        st
                .executeUpdate("insert into BOOKS(AUTHORID,SN,NAME) values (1,'sn1-1','test1')");
        st
                .executeUpdate("insert into BOOKS(AUTHORID,SN,NAME) values (1,'sn1-2','hermit')");
        st
                .executeUpdate("insert into BOOKS(AUTHORID,SN,NAME) values (2,'sn2-1','test')");
        st
                .executeUpdate("insert into BOOKS(AUTHORID,SN,NAME) values (3,'sn3-1','update3')");
        st
                .executeUpdate("insert into BOOKS(AUTHORID,SN,NAME) values (3,'sn3-1','test3')");
        st
                .executeUpdate("insert into BOOKS(AUTHORID,SN,NAME) values (4,'sn4-1','test4')");
        st
                .executeUpdate("insert into BOOKS(AUTHORID,SN,NAME) values (5,'sn5-1','test5')");
    }

    protected JoinRowSet newJoinRowSet() throws Exception {
        JoinRowSet jrs = null;
        try {
            jrs = (JoinRowSet) Class.forName("com.sun.rowset.JoinRowSetImpl")
                    .newInstance();
        } catch (ClassNotFoundException e) {
            jrs = (JoinRowSet) Class.forName(
                    "org.apache.harmony.sql.internal.rowset.JoinRowSetImpl")
                    .newInstance();
        }
        return jrs;
    }

    protected JdbcRowSet newJdbcRowSet() throws Exception {
        try {
            return (JdbcRowSet) Class.forName("com.sun.rowset.JdbcRowSetImpl")
                    .newInstance();
        } catch (ClassNotFoundException e) {
            return (JdbcRowSet) Class.forName(
                    "org.apache.harmony.sql.internal.rowset.JdbcRowSetImpl")
                    .newInstance();
        }
    }

    protected WebRowSet newWebRowSet() throws Exception {
        try {
            return (WebRowSet) Class.forName("com.sun.rowset.WebRowSetImpl")
                    .newInstance();
        } catch (ClassNotFoundException e) {
            return (WebRowSet) Class.forName(
                    "org.apache.harmony.sql.internal.rowset.WebRowSetImpl")
                    .newInstance();
        }
    }

    protected FilteredRowSet newFilterRowSet() throws Exception {
        try {
            return (FilteredRowSet) Class.forName(
                    "com.sun.rowset.FilteredRowSetImpl").newInstance();
        } catch (ClassNotFoundException e) {
            return (FilteredRowSet) Class
                    .forName(
                            "org.apache.harmony.sql.internal.rowset.FilteredRowSetImpl")
                    .newInstance();
        }
    }

    /*
     * Return the position of the first occurence of value in rowset on column,
     * based from 1. return -1 if not found.
     */
    protected int findValue(Object value, RowSet rowset, int column)
            throws SQLException {
        int index = 1;

        rowset.beforeFirst();

        while (rowset.next()) {
            if (value != null) {
                if (value.equals(rowset.getObject(column))) {
                    return index;
                }
            } else {
                if (rowset.getObject(column) == null) {
                    return index;
                }
            }
            index++;
        }
        return -1;
    }
}
