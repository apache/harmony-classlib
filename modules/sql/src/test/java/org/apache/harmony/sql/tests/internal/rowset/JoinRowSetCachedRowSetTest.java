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
import java.util.Collection;
import java.util.Iterator;

public class JoinRowSetCachedRowSetTest extends JoinRowSetTestCase {

    public void testGetCommandAndUrl() throws Exception {
        assertNull(jrs.getCommand());
        assertNull(jrs.getUrl());
        assertNull(jrs.getUsername());
        assertNull(jrs.getPassword());

        String sqlCommand = "select * from USER_INFO";
        noInitialCrset = newNoInitialInstance();
        noInitialCrset.setCommand(sqlCommand);
        noInitialCrset.setUrl(DERBY_URL);
        noInitialCrset.execute();
        assertEquals(sqlCommand, noInitialCrset.getCommand());
        assertEquals(DERBY_URL, noInitialCrset.getUrl());
        assertTrue(noInitialCrset.first());
        assertEquals(1, noInitialCrset.getInt(1));

        // add CachedRowSet to JoinRowSet
        jrs.addRowSet(noInitialCrset, 1);
        assertTrue(jrs.first());
        assertEquals(1, jrs.getInt(1));

        // check command and url
        assertNull(jrs.getCommand());
        assertNull(jrs.getUrl());
        assertNull(jrs.getUsername());
        assertNull(jrs.getPassword());
    }

    public void testSetCommandAndUrl() throws Exception {
        // Test empty JoinRowSet
        String sqlCommand = "select * from CUSTOMER_INFO";
        jrs.setCommand(sqlCommand);
        jrs.setUrl(DERBY_URL);
        try {
            jrs.execute();
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
        assertEquals(sqlCommand, jrs.getCommand());
        assertEquals(DERBY_URL, jrs.getUrl());

        // Test JoinRowSet filled with CachedRowSet
        jrs = newJoinRowSet();
        noInitialCrset = newNoInitialInstance();
        noInitialCrset.setCommand(sqlCommand);
        noInitialCrset.setUrl(DERBY_URL);
        noInitialCrset.execute();
        jrs.addRowSet(noInitialCrset, 1);
        assertTrue(jrs.first());
        assertEquals(1111, jrs.getInt(1));
        assertNull(jrs.getCommand());
        assertNull(jrs.getUrl());
        // call JoinRowSet.execute()
        jrs.beforeFirst();
        jrs.execute();
        // check data
        int index = 0;
        noInitialCrset.beforeFirst();
        while (jrs.next() && noInitialCrset.next()) {
            index++;
            for (int i = 1; i <= jrs.getMetaData().getColumnCount(); i++) {
                assertEquals(jrs.getObject(i), noInitialCrset.getObject(i));
            }
        }

        // set command and url
        sqlCommand = "select * from USER_INFO";
        jrs.setCommand(sqlCommand);
        jrs.setUrl(DERBY_URL);
        assertEquals(sqlCommand, jrs.getCommand());
        assertEquals(DERBY_URL, jrs.getUrl());
        // call JoinRowSet.execute() after set cmd and url
        jrs.execute();
        /*
         * Check data. Though the sql command is changed, the data of JoinRowSet
         * remains the same.
         */
        jrs.beforeFirst();
        noInitialCrset.beforeFirst();
        while (jrs.next() && noInitialCrset.next()) {
            for (int i = 1; i <= jrs.getMetaData().getColumnCount(); i++) {
                assertEquals(jrs.getObject(i), noInitialCrset.getObject(i));
            }
        }
        assertEquals(sqlCommand, jrs.getCommand());
        assertEquals(DERBY_URL, jrs.getUrl());
    }

    public void testPopulate() throws Exception {
        /*
         * JoinRowSet.populate(ResultSet) won't throw exception. However,
         * JoinRowSet is still empty.
         */
        rs = st.executeQuery("select * from USER_INFO");
        jrs.populate(rs);
        assertNull(jrs.getMetaData());
        jrs.beforeFirst();
        assertFalse(jrs.next());
        assertFalse(jrs.first());

        /*
         * Test JoinRowSet which has added a CachedRowSet
         */
        jrs = newJoinRowSet();
        jrs.addRowSet(crset, 1);
        assertTrue(jrs.first());
        assertEquals(1, jrs.getInt(1));

        rs = st.executeQuery("select * from BOOKS");
        // populate() still has no effect here
        jrs.populate(rs);
        jrs.beforeFirst();
        rs = st.executeQuery("select * from USER_INFO");
        while (jrs.next() && rs.next()) {
            for (int i = 1; i <= jrs.getMetaData().getColumnCount(); i++) {
                assertEquals(rs.getObject(i), jrs.getObject(i));
            }
        }
    }

    public void testExecuteWithoutConn_Normal() throws Exception {
        /*
         * Add a CachedRowSet which has set command and url but not call execute
         * to JoinRowSet
         */
        rs = st.executeQuery("select * from USER_INFO");
        noInitialCrset = newNoInitialInstance();
        noInitialCrset.setCommand("select * from BOOKS");
        noInitialCrset.setUrl(DERBY_URL);
        noInitialCrset.populate(rs);
        assertTrue(noInitialCrset.first());
        assertEquals("hermit", noInitialCrset.getString(2));
        assertEquals("select * from BOOKS", noInitialCrset.getCommand());
        assertEquals(DERBY_URL, noInitialCrset.getUrl());
        // add to JoinRowSet
        jrs = newJoinRowSet();
        jrs.addRowSet(noInitialCrset, 1);
        assertNull(jrs.getStatement());
        // check data
        assertSame(noInitialCrset.getMetaData(), jrs.getMetaData());
        rs = st.executeQuery("select * from USER_INFO");
        jrs.beforeFirst();
        int index = 0;
        while (jrs.next() && rs.next()) {
            index++;
            for (int i = 1; i <= jrs.getMetaData().getColumnCount(); i++) {
                assertEquals(jrs.getObject(i), rs.getObject(i));
            }
        }
        assertEquals(4, index);
        // call JoinRowSet.execute()
        assertNull(jrs.getCommand());
        jrs.execute();
        assertNull(jrs.getStatement());
        assertNull(jrs.getCommand());
        // check JoinRowSet's data
        assertNotSame(noInitialCrset.getMetaData(), jrs.getMetaData());
        rs = st.executeQuery("select * from BOOKS");
        jrs.beforeFirst();
        index = 0;
        while (jrs.next() && rs.next()) {
            index++;
            for (int i = 1; i <= jrs.getMetaData().getColumnCount(); i++) {
                assertEquals(jrs.getObject(i), rs.getObject(i));
            }
        }
        assertEquals(7, index);
        // check noInitialCrset's data
        rs = st.executeQuery("select * from USER_INFO");
        noInitialCrset.beforeFirst();
        index = 0;
        while (noInitialCrset.next() && rs.next()) {
            index++;
            for (int i = 1; i <= noInitialCrset.getMetaData().getColumnCount(); i++) {
                assertEquals(noInitialCrset.getObject(i), rs.getObject(i));
            }
        }
        assertEquals(4, index);
        // call noInitialCrset's execute()
        assertEquals("select * from BOOKS", noInitialCrset.getCommand());
        noInitialCrset.execute();
        // check noInitialCrset's data
        jrs.beforeFirst();
        noInitialCrset.beforeFirst();
        index = 0;
        while (noInitialCrset.next() && jrs.next()) {
            index++;
            for (int i = 1; i <= noInitialCrset.getMetaData().getColumnCount(); i++) {
                assertEquals(noInitialCrset.getObject(i), jrs.getObject(i));
            }
        }
        assertEquals(7, index);

        // change noInitialCrset's command
        noInitialCrset.setCommand("select * from CUSTOMER_INFO");
        // call JoinRowSet.execute()
        jrs.execute();
        // check data
        jrs.beforeFirst();
        noInitialCrset.beforeFirst();
        index = 0;
        while (noInitialCrset.next() && jrs.next()) {
            index++;
            for (int i = 1; i <= noInitialCrset.getMetaData().getColumnCount(); i++) {
                assertEquals(noInitialCrset.getObject(i), jrs.getObject(i));
            }
        }
        assertEquals(7, index);

        /*
         * Add a CachedRowSet which has set command and url to JoinRowSet
         */
        crset = newNoInitialInstance();
        crset.setCommand("select * from USER_INFO");
        crset.setUrl(DERBY_URL);
        crset.execute();
        assertTrue(crset.absolute(1));
        assertEquals("hermit", crset.getString(2));
        // add to JoinRowSet
        jrs = newJoinRowSet();
        jrs.addRowSet(crset, 1);
        // check data
        assertSame(crset.getMetaData(), jrs.getMetaData());
        assertTrue(jrs.first());
        assertEquals("hermit", jrs.getString(2));
        // call JoinRowSet.execute()
        jrs.execute();
        // check data
        assertNotSame(crset.getMetaData(), jrs.getMetaData());
        isMetaDataEquals(crset.getMetaData(), jrs.getMetaData());
        assertTrue(jrs.absolute(1));
        assertEquals("hermit", jrs.getString(2));

        // set command and url for JoinRowSet, then call execute() again
        jrs.setCommand("select * from BOOKS");
        jrs.setUrl(DERBY_URL);
        jrs.execute();
        // the data remains the same as crset's
        assertNotSame(crset.getMetaData(), jrs.getMetaData());
        isMetaDataEquals(crset.getMetaData(), jrs.getMetaData());
        assertTrue(jrs.absolute(1));
        assertEquals("hermit", jrs.getString(2));

        // change command for CachedRowSet, then call JoinRowSet.execute()
        crset.setCommand("select * from BOOKS");
        jrs.execute();
        assertNotSame(crset.getMetaData(), jrs.getMetaData());
        isMetaDataEquals(crset.getMetaData(), jrs.getMetaData());
        assertTrue(jrs.absolute(1));
        assertEquals("hermit", jrs.getString(2));
        // call CachedRowSet.execute()
        crset.execute();
        assertTrue(crset.first());
        assertEquals("sn1-1", crset.getString(2));
        assertTrue(jrs.absolute(1));
        assertEquals("hermit", jrs.getString(2));

        /*
         * Add another CachedRowSet which also can call execute() sucessfully to
         * JoinRowSet
         */
        noInitialCrset = newNoInitialInstance();
        noInitialCrset.setCommand("select * from BOOKS");
        noInitialCrset.setUrl(DERBY_URL);
        noInitialCrset.execute();
        /*
         * TODO The match column will lost after call execute(). Therefore, it
         * would throw exception and prompts that no match column. It's need to
         * test Joinable.
         */
        if ("true".equals(System.getProperty("Testing Harmony"))) {
            // TODO
        } else {
            try {
                jrs.addRowSet(noInitialCrset, 1);
                fail("should throw SQLException");
            } catch (SQLException e) {
                // expected
            }
        }
    }

    public void testExecuteWithoutConn_Exception() throws Exception {
        /*
         * An empty JoinRowSet call execute() would throw SQLException even when
         * command and url are set.
         */
        try {
            jrs.execute();
            fail("should throw SQLException.");
        } catch (SQLException e) {
            // expected
        }

        String sqlCommand = "select * from USER_INFO";
        jrs.setCommand(sqlCommand);
        jrs.setUrl(DERBY_URL);
        try {
            jrs.execute();
            fail("should throw SQLException.");
        } catch (SQLException e) {
            // expected
        }

        /*
         * Add a CachedRowSet without set command and url to JoinRowSet
         */
        rs = st.executeQuery("select * from USER_INFO");
        noInitialCrset = newNoInitialInstance();
        noInitialCrset.populate(rs);
        assertTrue(noInitialCrset.first());
        assertEquals(1, noInitialCrset.getInt(1));
        // add to JoinRowSet
        jrs = newJoinRowSet();
        jrs.addRowSet(noInitialCrset, 1);
        assertTrue(jrs.last());
        assertEquals(4, jrs.getInt(1));
        // call execute()
        try {
            jrs.execute();
            fail("should throw SQLException.");
        } catch (SQLException e) {
            // expected
        }
        // if execute() fail, then the JoinRowSet's data would lose
        assertNotNull(jrs.getMetaData());
        assertSame(noInitialCrset.getMetaData(), jrs.getMetaData());
        assertFalse(jrs.first());
        assertFalse(jrs.absolute(3));
        assertTrue(noInitialCrset.first());
        assertEquals("hermit", noInitialCrset.getString(2));

        /*
         * Add two CachedRowSets which both can call execute()
         */
        crset = newNoInitialInstance();
        crset.setCommand("select * from USER_INFO");
        crset.setUrl(DERBY_URL);
        crset.execute();
        noInitialCrset = newNoInitialInstance();
        noInitialCrset.setCommand("select * from BOOKS");
        noInitialCrset.setUrl(DERBY_URL);
        noInitialCrset.execute();
        // add to JoinRowSet
        jrs = newJoinRowSet();
        jrs.addRowSet(crset, 1);
        jrs.addRowSet(noInitialCrset, 1);
        // it would throw SQLException when more than one RowSet is added
        try {
            jrs.execute();
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
        assertNotNull(jrs.getMetaData());
        assertFalse(jrs.first());
    }

    public void testExecuteWithConn_Normal() throws Exception {
        assertNull(jrs.getStatement());
        /*
         * Add a CachedRowSet which has set command and call execute() to
         * JoinRowSet
         */
        crset = newNoInitialInstance();
        crset.setCommand("select * from USER_INFO");
        crset.execute(conn);
        assertTrue(crset.absolute(1));
        assertEquals("hermit", crset.getString(2));
        assertEquals(1, crset.getRow());
        // add to JoinRowSet
        jrs = newJoinRowSet();
        jrs.addRowSet(crset, 1);
        assertNull(jrs.getStatement());
        // check data
        assertSame(crset.getMetaData(), jrs.getMetaData());
        assertTrue(jrs.absolute(1));
        assertEquals("hermit", jrs.getString(2));
        assertEquals(1, jrs.getRow());
        assertTrue(jrs.last());
        assertEquals(4, jrs.getRow());
        // call JoinRowSet.execute()
        jrs.execute(conn);
        assertNull(jrs.getStatement());
        // check data
        assertNotSame(crset.getMetaData(), jrs.getMetaData());
        isMetaDataEquals(crset.getMetaData(), jrs.getMetaData());
        rs = st.executeQuery("select * from USER_INFO");
        jrs.beforeFirst();
        int index = 0;
        while (jrs.next() && rs.next()) {
            index++;
            for (int i = 1; i <= jrs.getMetaData().getColumnCount(); i++) {
                assertEquals(jrs.getObject(i), rs.getObject(i));
            }
            assertEquals(index, jrs.getRow());
        }
        assertEquals(4, index);

        // set command and url for JoinRowSet, then call execute() again
        jrs.setCommand("select * from BOOKS");
        jrs.execute(conn);
        assertNull(jrs.getStatement());
        // the data remains the same as crset's
        assertNotSame(crset.getMetaData(), jrs.getMetaData());
        isMetaDataEquals(crset.getMetaData(), jrs.getMetaData());
        rs = st.executeQuery("select * from USER_INFO");
        jrs.beforeFirst();
        index = 0;
        while (jrs.next() && rs.next()) {
            index++;
            for (int i = 1; i <= jrs.getMetaData().getColumnCount(); i++) {
                assertEquals(jrs.getObject(i), rs.getObject(i));
            }
        }
        assertEquals(4, index);
    }

    public void testExecuteWithConn_Exception() throws Exception {
        /*
         * Test empty JoinRowSet
         */
        try {
            jrs.execute(conn);
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }

        jrs.setCommand("select * from USER_INFO");
        try {
            jrs.execute(conn);
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }

        /*
         * Add a CachedRowSet without set command and url to JoinRowSet
         */
        rs = st.executeQuery("select * from USER_INFO");
        noInitialCrset = newNoInitialInstance();
        noInitialCrset.populate(rs);
        assertTrue(noInitialCrset.first());
        assertEquals("hermit", noInitialCrset.getString(2));
        // add to JoinRowSet
        jrs = newJoinRowSet();
        jrs.addRowSet(noInitialCrset, 1);
        // check data
        assertSame(noInitialCrset.getMetaData(), jrs.getMetaData());
        assertTrue(jrs.last());
        assertEquals("test4", jrs.getString(2));
        // call execute(conn)
        jrs.setCommand("select * from USER_INFO");
        try {
            jrs.execute(conn);
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
        // check data
        assertSame(noInitialCrset.getMetaData(), jrs.getMetaData());
        assertFalse(jrs.first());
        assertTrue(noInitialCrset.first());
        assertEquals("hermit", noInitialCrset.getString(2));

        /*
         * Add two CachedRowSets which both can call execute(conn)
         */
        crset = newNoInitialInstance();
        crset.setCommand("select * from USER_INFO");
        crset.execute(conn);
        noInitialCrset = newNoInitialInstance();
        noInitialCrset.setCommand("select * from BOOKS");
        noInitialCrset.execute(conn);
        // add to JoinRowSet
        jrs = newJoinRowSet();
        jrs.addRowSet(crset, 1);
        jrs.addRowSet(noInitialCrset, 1);
        // it would throw SQLException when more than one RowSet is added
        try {
            jrs.execute(conn);
            fail("should throw SQLException");
        } catch (SQLException e) {
            // expected
        }
        // check data
        assertNotNull(jrs.getMetaData());
        assertFalse(jrs.first());
    }
}
