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

public class CachedRowSetMatchColumnTest extends CachedRowSetTestCase {

    public void testSetMatchColumn_Name() throws Exception {
        String name = null;
        /*
         * TODO spec: throw SQLException, RI throw NullPointerException, Harmony
         * follow spec
         */
        if (!"true".equals(System.getProperty("Testing Harmony"))) {
            try {
                noInitialCrset.setMatchColumn(name);
                fail("Should throw SQLException");
            } catch (NullPointerException e) {
                // expected
            }
        } else {

            try {
                noInitialCrset.setMatchColumn(name);
                fail("Should throw SQLException");
            } catch (SQLException e) {
                // expected, Match columns should not be empty or null string
            }
        }

        try {
            noInitialCrset.setMatchColumn("");
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Match columns should not be empty or null string
        }

        noInitialCrset.setMatchColumn("not exist");
        String[] names = noInitialCrset.getMatchColumnNames();
        assertNotNull(names);
        assertEquals(10, names.length);
        assertEquals("not exist", names[0]);
        for (int i = 1; i < names.length; i++) {
            assertNull(names[i]);
        }

        noInitialCrset.setMatchColumn("id");
        names = noInitialCrset.getMatchColumnNames();
        assertNotNull(names);
        assertEquals(10, names.length);
        assertEquals("id", names[0]);
        for (int i = 1; i < names.length; i++) {
            assertNull(names[i]);
        }

        noInitialCrset.setMatchColumn(new String[] { "ID", "NAME" });

        names = null;

        try {
            noInitialCrset.setMatchColumn(names);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        names = noInitialCrset.getMatchColumnNames();
        assertNotNull(names);
        assertEquals(12, names.length);
        assertEquals("ID", names[0]);
        assertEquals("NAME", names[1]);
        assertEquals("id", names[2]);

        for (int i = 3; i < names.length; i++) {
            assertNull(names[i]);
        }

        try {
            noInitialCrset.setMatchColumn(new String[] { "ID", "" });
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Match columns should not be empty or null string
        }

        try {
            noInitialCrset.setMatchColumn(new String[] { "ID", null });
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Match columns should not be empty or null string
        }

        noInitialCrset.setMatchColumn("NAME");
        names = noInitialCrset.getMatchColumnNames();
        assertNotNull(names);
        assertEquals(12, names.length);
        assertEquals("NAME", names[0]);
        assertEquals("NAME", names[1]);
        assertEquals("id", names[2]);

        for (int i = 3; i < names.length; i++) {
            assertNull(names[i]);
        }

        if (!"true".equals(System.getProperty("Testing Harmony"))) {
            try {
                crset.setMatchColumn(name);
                fail("Should throw SQLException");
            } catch (NullPointerException e) {
                // expected
            }
        } else {

            try {
                crset.setMatchColumn(name);
                fail("Should throw SQLException");
            } catch (SQLException e) {
                // expected, Match columns should not be empty or null string
            }
        }

        try {
            crset.setMatchColumn("");
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Match columns should not be empty or null string
        }

        crset.setMatchColumn("not exist");
        names = crset.getMatchColumnNames();
        assertNotNull(names);
        assertEquals(10, names.length);
        assertEquals("not exist", names[0]);
        for (int i = 1; i < names.length; i++) {
            assertNull(names[i]);
        }

        crset.setMatchColumn("id");
        names = crset.getMatchColumnNames();
        assertNotNull(names);
        assertEquals(10, names.length);
        assertEquals("id", names[0]);
        for (int i = 1; i < names.length; i++) {
            assertNull(names[i]);
        }

        crset.setMatchColumn(new String[] { "ID", "NAME" });
        names = crset.getMatchColumnNames();
        assertNotNull(names);
        assertEquals(12, names.length);
        assertEquals("ID", names[0]);
        assertEquals("NAME", names[1]);
        assertEquals("id", names[2]);

        for (int i = 3; i < names.length; i++) {
            assertNull(names[i]);
        }

        try {
            crset.setMatchColumn(new String[] { "ID", "" });
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Match columns should not be empty or null string
        }

        try {
            crset.setMatchColumn(new String[] { "ID", null });
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Match columns should not be empty or null string
        }

        crset.setMatchColumn("NAME");
        names = crset.getMatchColumnNames();
        assertNotNull(names);
        assertEquals(12, names.length);
        assertEquals("NAME", names[0]);
        assertEquals("NAME", names[1]);
        assertEquals("id", names[2]);

        for (int i = 3; i < names.length; i++) {
            assertNull(names[i]);
        }

    }

    public void testSetMatchColumn_Index() throws Exception {
        try {
            noInitialCrset.setMatchColumn(-2);
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Match columns should be greater than 0
        }

        int[] indexes = null;

        try {
            noInitialCrset.setMatchColumn(indexes);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        // TODO 0 is valid index 0f column?
        noInitialCrset.setMatchColumn(0);
        indexes = noInitialCrset.getMatchColumnIndexes();

        assertNotNull(indexes);
        assertEquals(10, indexes.length);
        for (int i = 0; i < indexes.length; i++) {
            if (i == 0) {
                assertEquals(0, indexes[i]);
            } else {
                assertEquals(-1, indexes[i]);
            }
        }

        noInitialCrset.setMatchColumn(1);
        indexes = noInitialCrset.getMatchColumnIndexes();
        assertNotNull(indexes);
        assertEquals(10, indexes.length);
        for (int i = 0; i < indexes.length; i++) {
            if (i == 0) {
                assertEquals(1, indexes[i]);
            } else {
                assertEquals(-1, indexes[i]);
            }
        }

        noInitialCrset.setMatchColumn(new int[] { 3, 4, 5 });
        indexes = noInitialCrset.getMatchColumnIndexes();

        try {
            noInitialCrset.setMatchColumn(new int[] { 3, -3 });
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Match columns should be greater than 0
        }

        assertNotNull(indexes);
        assertEquals(13, indexes.length);
        assertEquals(3, indexes[0]);
        assertEquals(4, indexes[1]);
        assertEquals(5, indexes[2]);
        assertEquals(1, indexes[3]);

        for (int i = 4; i < indexes.length; i++) {
            assertEquals(-1, indexes[i]);
        }

        noInitialCrset.setMatchColumn(6);
        indexes = noInitialCrset.getMatchColumnIndexes();
        assertNotNull(indexes);
        assertEquals(13, indexes.length);
        assertEquals(6, indexes[0]);
        assertEquals(4, indexes[1]);
        assertEquals(5, indexes[2]);
        assertEquals(1, indexes[3]);

        noInitialCrset.setMatchColumn(new int[] { 7, 8 });
        indexes = noInitialCrset.getMatchColumnIndexes();
        assertNotNull(indexes);
        assertEquals(15, indexes.length);
        assertEquals(7, indexes[0]);
        assertEquals(8, indexes[1]);
        assertEquals(6, indexes[2]);
        assertEquals(4, indexes[3]);
        assertEquals(5, indexes[4]);
        assertEquals(1, indexes[5]);

        for (int i = 6; i < indexes.length; i++) {
            assertEquals(-1, indexes[i]);
        }

        noInitialCrset.setMatchColumn(9);
        indexes = noInitialCrset.getMatchColumnIndexes();
        assertNotNull(indexes);
        assertEquals(15, indexes.length);
        assertEquals(9, indexes[0]);
        assertEquals(8, indexes[1]);
        assertEquals(6, indexes[2]);
        assertEquals(4, indexes[3]);
        assertEquals(5, indexes[4]);
        assertEquals(1, indexes[5]);

        for (int i = 6; i < indexes.length; i++) {
            assertEquals(-1, indexes[i]);
        }

        try {
            crset.setMatchColumn(-2);
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Match columns should be greater than 0
        }

        indexes = null;

        try {
            crset.setMatchColumn(indexes);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }

        crset.setMatchColumn(0);
        indexes = crset.getMatchColumnIndexes();

        assertNotNull(indexes);
        assertEquals(10, indexes.length);
        for (int i = 0; i < indexes.length; i++) {
            if (i == 0) {
                assertEquals(0, indexes[i]);
            } else {
                assertEquals(-1, indexes[i]);
            }
        }

        crset.setMatchColumn(1);
        indexes = crset.getMatchColumnIndexes();
        assertNotNull(indexes);
        assertEquals(10, indexes.length);
        for (int i = 0; i < indexes.length; i++) {
            if (i == 0) {
                assertEquals(1, indexes[i]);
            } else {
                assertEquals(-1, indexes[i]);
            }
        }

        crset.setMatchColumn(new int[] { 3, 4, 5 });
        indexes = crset.getMatchColumnIndexes();

        assertNotNull(indexes);
        assertEquals(13, indexes.length);
        assertEquals(3, indexes[0]);
        assertEquals(4, indexes[1]);
        assertEquals(5, indexes[2]);
        assertEquals(1, indexes[3]);

        for (int i = 4; i < indexes.length; i++) {
            assertEquals(-1, indexes[i]);
        }

        crset.setMatchColumn(6);
        indexes = crset.getMatchColumnIndexes();
        assertNotNull(indexes);
        assertEquals(13, indexes.length);
        assertEquals(6, indexes[0]);
        assertEquals(4, indexes[1]);
        assertEquals(5, indexes[2]);
        assertEquals(1, indexes[3]);

        for (int i = 4; i < indexes.length; i++) {
            assertEquals(-1, indexes[i]);
        }

        crset.setMatchColumn(new int[] { 7, 8 });
        indexes = crset.getMatchColumnIndexes();
        assertNotNull(indexes);
        assertEquals(15, indexes.length);
        assertEquals(7, indexes[0]);
        assertEquals(8, indexes[1]);
        assertEquals(6, indexes[2]);
        assertEquals(4, indexes[3]);
        assertEquals(5, indexes[4]);
        assertEquals(1, indexes[5]);

        for (int i = 6; i < indexes.length; i++) {
            assertEquals(-1, indexes[i]);
        }

        crset.setMatchColumn(9);
        indexes = crset.getMatchColumnIndexes();
        assertNotNull(indexes);
        assertEquals(15, indexes.length);
        assertEquals(9, indexes[0]);
        assertEquals(8, indexes[1]);
        assertEquals(6, indexes[2]);
        assertEquals(4, indexes[3]);
        assertEquals(5, indexes[4]);
        assertEquals(1, indexes[5]);

        for (int i = 6; i < indexes.length; i++) {
            assertEquals(-1, indexes[i]);
        }

        // exceed column count
        crset.setMatchColumn(100);
        assertEquals(100, crset.getMatchColumnIndexes()[0]);

        noInitialCrset = newNoInitialInstance();

        noInitialCrset.setMatchColumn(new int[] { 1, 2, 3 });
        indexes = noInitialCrset.getMatchColumnIndexes();
        assertNotNull(indexes);
        assertEquals(13, indexes.length);
        assertEquals(1, indexes[0]);
        assertEquals(2, indexes[1]);
        assertEquals(3, indexes[2]);
        for (int i = 3; i < indexes.length; i++) {
            assertEquals(-1, indexes[i]);
        }

        noInitialCrset = newNoInitialInstance();

    }

    public void testGetMatchColumn() throws Exception {
        try {
            noInitialCrset.getMatchColumnIndexes();
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Set Match columns before getting them
        }

        try {
            noInitialCrset.getMatchColumnNames();
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Set Match columns before getting them
        }

        // mantains match column indexes and match column names separately
        noInitialCrset.setMatchColumn(0);
        int[] indexes = noInitialCrset.getMatchColumnIndexes();

        try {
            noInitialCrset.getMatchColumnNames();
            fail("Should throw SQLException");
        } catch (SQLException e) {
            // expected, Set Match columns before getting them
        }

        noInitialCrset.setMatchColumn("test");
        String[] names = noInitialCrset.getMatchColumnNames();

        assertNotSame(noInitialCrset.getMatchColumnIndexes(), noInitialCrset
                .getMatchColumnIndexes());
        assertNotSame(noInitialCrset.getMatchColumnNames(), noInitialCrset
                .getMatchColumnNames());

    }

}
