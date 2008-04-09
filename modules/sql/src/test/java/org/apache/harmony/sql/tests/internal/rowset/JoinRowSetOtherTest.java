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

import javax.sql.rowset.JoinRowSet;

public class JoinRowSetOtherTest extends JoinRowSetTestCase {

    /**
     * @throws SQLException
     * @tests javax.sql.rowset.JoinRowSet.supportsInnerJoin();
     */
    public void testSupportsInnerJoin() throws SQLException {
        boolean isSupported = jrs.supportsInnerJoin();
        assertTrue(isSupported);

        jrs.addRowSet(crset, 1);
        isSupported = jrs.supportsInnerJoin();
        assertTrue(isSupported);
    }

    /**
     * @throws SQLException
     * @tests javax.sql.rowset.JoinRowSet.supportsCrossJoin();
     */
    public void testSupportsCrossJoin() throws SQLException {
        boolean isSupported = jrs.supportsCrossJoin();
        assertFalse(isSupported);

        jrs.addRowSet(crset, 1);
        isSupported = jrs.supportsCrossJoin();
        assertFalse(isSupported);
    }

    /**
     * @throws SQLException
     * @tests javax.sql.rowset.JoinRowSet.supportsLeftOuterJoin();
     */
    public void testSupportsLeftOuterJoin() throws SQLException {
        boolean isSupported = jrs.supportsLeftOuterJoin();
        assertFalse(isSupported);

        jrs.addRowSet(crset, 1);
        isSupported = jrs.supportsLeftOuterJoin();
        assertFalse(isSupported);
    }

    /**
     * @throws SQLException
     * @tests javax.sql.rowset.JoinRowSet.supportsRightOuterJoin();
     */
    public void testSupportsRightOuterJoin() throws SQLException {
        boolean isSupported = jrs.supportsRightOuterJoin();
        assertFalse(isSupported);

        jrs.addRowSet(crset, 1);
        isSupported = jrs.supportsRightOuterJoin();
        assertFalse(isSupported);
    }

    /**
     * @throws SQLException
     * @tests javax.sql.rowset.JoinRowSet#supportsFullJoin();
     */
    public void testSupportsFullJoin() throws SQLException {
        boolean isSupported = jrs.supportsFullJoin();
        assertFalse(isSupported);

        jrs.addRowSet(crset, 1);
        isSupported = jrs.supportsFullJoin();
        assertFalse(isSupported);
    }

    /**
     * @tests java.sql.rowset.joinRowSet#setJoinType(int) and getJoinType();
     * @throws SQLException
     */
    public void testSetJoinTypeAndGetJoinType() throws SQLException {
        int joinType;

        // Tests default join type.
        if (System.getProperty("Testing Harmony") == "true") {
            joinType = jrs.getJoinType();
            assertEquals(JoinRowSet.INNER_JOIN, joinType);
        } else {
            try {
                joinType = jrs.getJoinType();
                fail("Should throw ArrayIndexOutOfBoundsException");
            } catch (ArrayIndexOutOfBoundsException e) {
                // Expected.
            }
        }

        // Adds a rowset, then tests getJoinType().
        jrs.addRowSet(crset, 1);

        if (System.getProperty("Testing Harmony") == "true") {
            joinType = jrs.getJoinType();
            assertEquals(JoinRowSet.INNER_JOIN, joinType);
        } else {
            try {
                joinType = jrs.getJoinType();
                fail("Should throw ArrayIndexOutOfBoundsException");
            } catch (ArrayIndexOutOfBoundsException e) {
                // Expected.
            }
        }

        // Tests setJoinType(CROSS_JOIN)
        if (System.getProperty("Testing Harmony") == "true") {
            try {
                jrs.setJoinType(JoinRowSet.CROSS_JOIN);
                fail("Should throw SQLException according to spec.");
            } catch (SQLException e) {
                // Expected.
            }
        } else {
            try {
                jrs.setJoinType(JoinRowSet.CROSS_JOIN);
                fail("Should throw UnsupportedOperationException in RI");
            } catch (UnsupportedOperationException e) {
                // Expected
            }
        }

        // Tests setJoinType(INNER_JOIN)
        jrs.setJoinType(JoinRowSet.INNER_JOIN);
        joinType = jrs.getJoinType();
        assertEquals(JoinRowSet.INNER_JOIN, joinType);

        // Tests setJoinType(FULL_JOIN)
        if (System.getProperty("Testing Harmony") == "true") {
            try {
                jrs.setJoinType(JoinRowSet.FULL_JOIN);
                fail("Should throw SQLException according to spec.");
            } catch (SQLException e) {
                // Expected.
            }
        } else {
            try {
                jrs.setJoinType(JoinRowSet.FULL_JOIN);
                fail("Should throw UnsupportedOperationException in RI");
            } catch (UnsupportedOperationException e) {
                // Expected
            }
        }

        // Tests setJoinType(LEFT_OUTER_JOIN)
        if (System.getProperty("Testing Harmony") == "true") {
            try {
                jrs.setJoinType(JoinRowSet.LEFT_OUTER_JOIN);
                fail("Should throw SQLException according to spec.");
            } catch (SQLException e) {
                // Expected.
            }
        } else {
            try {
                jrs.setJoinType(JoinRowSet.LEFT_OUTER_JOIN);
                fail("Should throw UnsupportedOperationException in RI");
            } catch (UnsupportedOperationException e) {
                // Expected
            }
        }

        // Tests setJoinType(RIGHT_OUTER_JOIN)
        if (System.getProperty("Testing Harmony") == "true") {
            try {
                jrs.setJoinType(JoinRowSet.RIGHT_OUTER_JOIN);
                fail("Should throw SQLException according to spec.");
            } catch (SQLException e) {
                // Expected.
            }
        } else {
            try {
                jrs.setJoinType(JoinRowSet.RIGHT_OUTER_JOIN);
                fail("Should throw UnsupportedOperationException in RI");
            } catch (UnsupportedOperationException e) {
                // Expected
            }
        }

        // Tests setJoinType(-1)
        try {
            jrs.setJoinType(-1);
            fail("Should throw SQLException since -1 is a invalid type");
        } catch (SQLException e) {
            // Expected.
        }
    }

}
