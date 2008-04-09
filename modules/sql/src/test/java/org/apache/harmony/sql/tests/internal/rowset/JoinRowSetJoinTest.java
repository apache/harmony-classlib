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

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

public class JoinRowSetJoinTest extends JoinRowSetTestCase {

    public void testJoin_DataEqual() throws Exception {
        boolean dataEquals;
        jrs.addRowSet(crset, 1);
        dataEquals = dataEqualsIgnoreOrder(jrs, crset);
        if (!dataEquals) {
            fail("The data in jrs and crset should be equal.");
        }

        // Creates a new CachedRowSet from BOOKs
        CachedRowSet crset2 = newNoInitialInstance();
        rs = st.executeQuery("select * from BOOKS");
        crset2.populate(rs);
        jrs.addRowSet(crset2, 1);

        CachedRowSet dbJoinCrset = newNoInitialInstance();
        rs = st
                .executeQuery("select USER_INFO.ID, USER_INFO.NAME, USER_INFO.BIGINT_T, USER_INFO.NUMERIC_T, USER_INFO.DECIMAL_T, "
                        + "USER_INFO.SMALLINT_T, USER_INFO.FLOAT_T, USER_INFO.REAL_T, USER_INFO.DOUBLE_T, USER_INFO.DATE_T, USER_INFO.TIME_T, USER_INFO.TIMESTAMP_T, "
                        + "BOOKS.SN, BOOKS.NAME"
                        + " from USER_INFO, BOOKS "
                        + "where USER_INFO.ID = BOOKS.AUTHORID");
        dbJoinCrset.populate(rs);

        dataEquals = dataEqualsIgnoreOrder(jrs, dbJoinCrset);
        if (!dataEquals) {
            fail("The data is jrs and dbJoinCrset should be equal.");
        }

        jrs = newJoinRowSet();
        jrs.addRowSet(crset2, 1);
        dataEquals = dataEqualsIgnoreOrder(crset2, jrs);
        if (!dataEquals) {
            fail("The data is jrs and crset2 should be equal.");
        }

        rs = st
                .executeQuery("select BOOKS.AUTHORID, BOOKS.SN, BOOKS.NAME, USER_INFO.NAME, USER_INFO.BIGINT_T, USER_INFO.NUMERIC_T, USER_INFO.DECIMAL_T, "
                        + "USER_INFO.SMALLINT_T, USER_INFO.FLOAT_T, USER_INFO.REAL_T, USER_INFO.DOUBLE_T, USER_INFO.DATE_T, USER_INFO.TIME_T, USER_INFO.TIMESTAMP_T "
                        + " from USER_INFO, BOOKS "
                        + "where USER_INFO.ID = BOOKS.AUTHORID");
        dbJoinCrset = newNoInitialInstance();
        dbJoinCrset.populate(rs);

        jrs.addRowSet(crset, 1);
        dataEquals = dataEqualsIgnoreOrder(jrs, dbJoinCrset);
        if (!dataEquals) {
            fail("The data is jrs and dbJoinCrset should be equal.");
        }
    }

    public void testJoin_DataEqual_StringColumn() throws Exception {
        boolean dataEquals;
        jrs.addRowSet(crset, 2);

        dataEquals = dataEqualsIgnoreOrder(jrs, crset);
        if (!dataEquals) {
            fail("The data in jrs and crset should be equal.");
        }

        // Creates a new CachedRowSet from BOOKs
        CachedRowSet crset2 = newNoInitialInstance();
        rs = st.executeQuery("select * from BOOKS");
        crset2.populate(rs);

        jrs.addRowSet(crset2, 3);

        CachedRowSet dbJoinCrset = newNoInitialInstance();
        rs = st
                .executeQuery("select USER_INFO.ID, USER_INFO.NAME, USER_INFO.BIGINT_T, USER_INFO.NUMERIC_T, USER_INFO.DECIMAL_T, "
                        + "USER_INFO.SMALLINT_T, USER_INFO.FLOAT_T, USER_INFO.REAL_T, USER_INFO.DOUBLE_T, USER_INFO.DATE_T, USER_INFO.TIME_T, USER_INFO.TIMESTAMP_T, "
                        + "BOOKS.AUTHORID, BOOKS.SN"
                        + " from USER_INFO, BOOKS "
                        + "where USER_INFO.NAME = BOOKS.NAME");
        dbJoinCrset.populate(rs);

        dataEquals = dataEqualsIgnoreOrder(jrs, dbJoinCrset);
        if (!dataEquals) {
            fail("The data is jrs and dbJoinCrset should be equal.");
        }

        jrs = newJoinRowSet();
        jrs.addRowSet(crset2, 3);
        dataEquals = dataEqualsIgnoreOrder(crset2, jrs);
        if (!dataEquals) {
            fail("The data is jrs and crset2 should be equal.");
        }

        rs = st
                .executeQuery("select BOOKS.AUTHORID, BOOKS.SN, BOOKS.NAME, USER_INFO.ID, USER_INFO.BIGINT_T, USER_INFO.NUMERIC_T, USER_INFO.DECIMAL_T, "
                        + "USER_INFO.SMALLINT_T, USER_INFO.FLOAT_T, USER_INFO.REAL_T, USER_INFO.DOUBLE_T, USER_INFO.DATE_T, USER_INFO.TIME_T, USER_INFO.TIMESTAMP_T "
                        + " from USER_INFO, BOOKS "
                        + "where USER_INFO.NAME = BOOKS.NAME");
        dbJoinCrset = newNoInitialInstance();
        dbJoinCrset.populate(rs);

        jrs.addRowSet(crset, 2);
        dataEquals = dataEqualsIgnoreOrder(jrs, dbJoinCrset);
        if (!dataEquals) {
            fail("The data is jrs and dbJoinCrset should be equal.");
        }
    }

    public void testJoin_DataEqual_MoreData() throws Exception {
        insertMoreData(20);
        rs = st.executeQuery("select * from USER_INFO");
        crset = newNoInitialInstance();
        crset.populate(rs);

        boolean dataEquals;
        jrs.addRowSet(crset, 1);
        dataEquals = dataEqualsIgnoreOrder(jrs, crset);
        if (!dataEquals) {
            fail("The data in jrs and crset should be equal.");
        }

        // Creates a new CachedRowSet from BOOKs
        CachedRowSet crset2 = newNoInitialInstance();
        rs = st.executeQuery("select * from BOOKS");
        crset2.populate(rs);

        jrs.addRowSet(crset2, 1);

        CachedRowSet dbJoinCrset = newNoInitialInstance();
        rs = st
                .executeQuery("select USER_INFO.ID, USER_INFO.NAME, USER_INFO.BIGINT_T, USER_INFO.NUMERIC_T, USER_INFO.DECIMAL_T, "
                        + "USER_INFO.SMALLINT_T, USER_INFO.FLOAT_T, USER_INFO.REAL_T, USER_INFO.DOUBLE_T, USER_INFO.DATE_T, USER_INFO.TIME_T, USER_INFO.TIMESTAMP_T, "
                        + "BOOKS.SN, BOOKS.NAME"
                        + " from USER_INFO, BOOKS "
                        + "where USER_INFO.ID = BOOKS.AUTHORID");
        dbJoinCrset.populate(rs);

        dataEquals = dataEqualsIgnoreOrder(jrs, dbJoinCrset);
        if (!dataEquals) {
            fail("The data is jrs and dbJoinCrset should be equal.");
        }

        jrs = newJoinRowSet();
        jrs.addRowSet(crset2, 1);
        dataEquals = dataEqualsIgnoreOrder(crset2, jrs);
        if (!dataEquals) {
            fail("The data is jrs and crset2 should be equal.");
        }

        rs = st
                .executeQuery("select BOOKS.AUTHORID, BOOKS.SN, BOOKS.NAME, USER_INFO.NAME, USER_INFO.BIGINT_T, USER_INFO.NUMERIC_T, USER_INFO.DECIMAL_T, "
                        + "USER_INFO.SMALLINT_T, USER_INFO.FLOAT_T, USER_INFO.REAL_T, USER_INFO.DOUBLE_T, USER_INFO.DATE_T, USER_INFO.TIME_T, USER_INFO.TIMESTAMP_T "
                        + " from USER_INFO, BOOKS "
                        + "where USER_INFO.ID = BOOKS.AUTHORID");
        dbJoinCrset = newNoInitialInstance();
        dbJoinCrset.populate(rs);

        jrs.addRowSet(crset, 1);
        dataEquals = dataEqualsIgnoreOrder(jrs, dbJoinCrset);
        if (!dataEquals) {
            fail("The data is jrs and dbJoinCrset should be equal.");
        }

    }

    public void testJoin_DataEqual_NullData() throws Exception {
        insertNullDataToBooks();
        insertNullDataToUserInfo();

        boolean dataEquals;
        jrs.addRowSet(crset, 2);
        dataEquals = dataEqualsIgnoreOrder(jrs, crset);
        if (!dataEquals) {
            fail("The data in jrs and crset should be equal.");
        }

        // Creates a new CachedRowSet from BOOKs
        CachedRowSet crset2 = newNoInitialInstance();
        rs = st.executeQuery("select * from BOOKS");
        crset2.populate(rs);

        jrs.addRowSet(crset2, 3);

        CachedRowSet dbJoinCrset = newNoInitialInstance();
        rs = st
                .executeQuery("select USER_INFO.ID, USER_INFO.NAME, USER_INFO.BIGINT_T, USER_INFO.NUMERIC_T, USER_INFO.DECIMAL_T, "
                        + "USER_INFO.SMALLINT_T, USER_INFO.FLOAT_T, USER_INFO.REAL_T, USER_INFO.DOUBLE_T, USER_INFO.DATE_T, USER_INFO.TIME_T, USER_INFO.TIMESTAMP_T, "
                        + "BOOKS.AUTHORID, BOOKS.SN"
                        + " from USER_INFO, BOOKS "
                        + "where USER_INFO.NAME = BOOKS.NAME");
        dbJoinCrset.populate(rs);

        dataEquals = dataEqualsIgnoreOrderAndNullInR2(jrs, dbJoinCrset, 2);
        if (!dataEquals) {
            fail("The data is jrs and dbJoinCrset should be equal.");
        }

        jrs = newJoinRowSet();
        jrs.addRowSet(crset2, 3);
        dataEquals = dataEqualsIgnoreOrder(crset2, jrs);
        if (!dataEquals) {
            fail("The data is jrs and crset2 should be equal.");
        }

        rs = st
                .executeQuery("select BOOKS.AUTHORID, BOOKS.SN, BOOKS.NAME, USER_INFO.ID, USER_INFO.BIGINT_T, USER_INFO.NUMERIC_T, USER_INFO.DECIMAL_T, "
                        + "USER_INFO.SMALLINT_T, USER_INFO.FLOAT_T, USER_INFO.REAL_T, USER_INFO.DOUBLE_T, USER_INFO.DATE_T, USER_INFO.TIME_T, USER_INFO.TIMESTAMP_T "
                        + " from USER_INFO, BOOKS "
                        + "where USER_INFO.NAME = BOOKS.NAME");
        dbJoinCrset = newNoInitialInstance();
        dbJoinCrset.populate(rs);

        jrs.addRowSet(crset, 2);
        dataEquals = dataEqualsIgnoreOrderAndNullInR2(jrs, dbJoinCrset, 3);
        if (!dataEquals) {
            fail("The data is jrs and dbJoinCrset should be equal.");
        }
    }

    public void testJoin_SameTable() throws Exception {
        CachedRowSet crset2 = newNoInitialInstance();
        rs = st.executeQuery("select * from BOOKS");
        crset2.populate(rs);

        CachedRowSet dbJoinCrset = newNoInitialInstance();
        rs = st.executeQuery("select BOOKS1.AUTHORID, BOOKS1.SN, BOOKS1.NAME, "
                + "BOOKS.AUTHORID, BOOKS.NAME"
                + " from BOOKS as BOOKS1, BOOKS "
                + "where BOOKS1.SN = BOOKS.SN");
        dbJoinCrset.populate(rs);

        jrs.addRowSet(crset2, "SN");
        jrs.addRowSet(crset2, "SN");

        boolean dataEquals = dataEqualsIgnoreOrder(jrs, dbJoinCrset);
        if (!dataEquals) {
            fail("The data is jrs and dbJoinCrset should be equal.");
        }

    }

    public void testJoin_Unsortable() throws Exception {
        addUnsortableToBooksTable();

        CachedRowSet crset2 = newNoInitialInstance();
        rs = st.executeQuery("select * from BOOKS");
        crset2.populate(rs);

        boolean dataEquals;
        try {
            jrs.addRowSet(crset2, 4);
            if ("true".equals(System.getProperty("Testing Harmony"))) {
                fail("Should throw SQLException here.(In harmony)");
            } else {

            }
        } catch (SQLException e) {
            if (!"true".equals(System.getProperty("Testing Harmony"))) {
                fail("Should not throw SQLException here.(In RI)");
            }
        }
        /*
         * dataEquals = dataEqualsIgnoreOrder(jrs, crset2); if (!dataEquals) {
         * fail("The data in jrs and crset2 should be equal."); }
         */
        try {
            jrs.addRowSet(crset2, 4);
            fail("Should throw SQLException here.");
        } catch (SQLException e) {
            // Expected.
        }
    }

    // It proves that when the rowset is the first one added to the joinRowSet,
    // it cursor doesn't move.
    // Else, the cursor moves to the last one (not afterLast).
    public void testJoin_OriginalCursorPosition() throws Exception {
        // Create crset2.
        rs = st.executeQuery("select * from BOOKS");
        CachedRowSet crset2 = newNoInitialInstance();
        crset2.populate(rs);

        assertTrue(crset.isBeforeFirst());
        jrs.addRowSet(crset, 1);
        assertTrue(crset.isBeforeFirst());

        crset.absolute(3);

        jrs.addRowSet(crset, 1);
        assertTrue(crset.isLast());

        crset.last();
        jrs.addRowSet(crset, 1);
        assertTrue(crset.isLast());

        crset.afterLast();
        jrs.addRowSet(crset, 1);
        assertTrue(crset.isLast());

        crset.beforeFirst();
        jrs.addRowSet(crset, 1);
        assertTrue(crset.isLast());

        crset.first();
        crset2.beforeFirst();
        jrs.addRowSet(crset2, 1);
        assertTrue(crset2.isLast());
        assertTrue(crset.isFirst());

        jrs = newJoinRowSet();
        crset.first();
        jrs.addRowSet(crset, 1);
        assertTrue(crset.isFirst());

        jrs = newJoinRowSet();
        crset.absolute(2);
        jrs.addRowSet(crset, 1);
        assertEquals(2, crset.getInt(1));
    }

    public void testJoin_CursorPosition() throws Exception {
        rs = st.executeQuery("select * from BOOKS");
        CachedRowSet crset2 = newNoInitialInstance();
        crset2.populate(rs);

        // TODO
        // Tests when there is no rowSet.
        // Not determined in harmony.
        // assertTrue(jrs.isLast());
        // assertTrue(jrs.isFirst());

        // Tests when there is only one rowSet.
        jrs.addRowSet(crset, 1);
        assertTrue(jrs.isBeforeFirst());

        jrs = newJoinRowSet();
        crset.last();
        jrs.addRowSet(crset, 1);
        assertTrue(jrs.isLast());

        // Tests where there are two rowset.
        jrs = newJoinRowSet();
        crset.last();
        jrs.addRowSet(crset, 1);
        assertTrue(jrs.last());
        jrs.addRowSet(crset2, 1);
        assertTrue(jrs.isBeforeFirst());

        jrs = newJoinRowSet();
        jrs.addRowSet(crset, 1);
        crset2.absolute(1);
        jrs.addRowSet(crset2, 1);
        assertTrue(jrs.isBeforeFirst());

        crset.last();
        jrs = newJoinRowSet();
        jrs.addRowSet(crset, 1);
        assertTrue(jrs.isLast());
        jrs.addRowSet(crset2, 1);
        assertTrue(jrs.isBeforeFirst());

        crset.last();
        jrs = newJoinRowSet();
        jrs.addRowSet(crset, 1);
        assertTrue(jrs.isLast());
        crset2.last();
        jrs.addRowSet(crset2, 1);
        assertTrue(jrs.isBeforeFirst());

        crset.last();
        jrs = newJoinRowSet();
        jrs.addRowSet(crset, 1);
        assertTrue(jrs.isLast());
        crset2.absolute(1);
        jrs.addRowSet(crset2, 1);
        assertTrue(jrs.isBeforeFirst());
    }

    public void testJoin_Update() throws Exception {
        rs = st.executeQuery("select * from BOOKS");
        CachedRowSet crset2 = newNoInitialInstance();
        crset2.populate(rs);

        crset.first();
        crset.updateString(2, "Updated");

        jrs.addRowSet(crset, 1);
        jrs.beforeFirst();
        int count = 0;
        while (jrs.next()) {
            if (jrs.getInt(1) == 1) {
                count++;
                assertEquals("Updated", jrs.getString(2));
            }
        }
        assertEquals(1, count);

        crset2.first();
        crset2.updateString(2, "Updated2");

    }

    protected int getVisibleSize(ResultSet rs) throws SQLException {
        rs.beforeFirst();
        int rowNum = 0;
        while (rs.next()) {
            rowNum++;
        }
        return rowNum;
    }

}
