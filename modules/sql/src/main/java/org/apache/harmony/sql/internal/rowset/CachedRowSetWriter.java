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
package org.apache.harmony.sql.internal.rowset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.RowSetInternal;
import javax.sql.RowSetWriter;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.spi.SyncProviderException;

public class CachedRowSetWriter implements RowSetWriter {

    private CachedRowSet originalRowSet;

    private CachedRowSetImpl currentRowSet;

    private Connection originalConnection;

    private String tableName;

    private String[] colNames;

    private int columnCount;

    public void setConnection(Connection conn) {
        originalConnection = conn;
    }

    public Connection getConnection() {
        return originalConnection;
    }

    /**
     * TODO add transaction
     */
    public boolean writeData(RowSetInternal theRowSet) throws SQLException {
        initial(theRowSet);
        // analyse every row and do responsible task.
        currentRowSet.beforeFirst();// currentRowSet.first();
        originalRowSet.beforeFirst();// originalRowSet.first();
        while (currentRowSet.next()) {
            if (currentRowSet.rowInserted()) {
                insertCurrentRow();
            } else if (currentRowSet.rowDeleted()) {
                if (isConflictExistForCurrentRow()) {
                    // TODO: conflict exists, should throw SyncProviderException
                    throw new SyncProviderException();
                }

                deleteCurrentRow();

            } else if (currentRowSet.rowUpdated()) {
                if (isConflictExistForCurrentRow()) {
                    // TODO: conflict exists, should throw SyncProviderException
                    throw new SyncProviderException();
                }
                
                updateCurrentRow();
            }
        }
        // TODO release resource
        return true;
    }

    /**
     * Insert the RowSet's current row to DB
     * 
     * @throws SQLException
     */
    @SuppressWarnings("nls")
    private void insertCurrentRow() throws SQLException {
        /*
         * the first step: generate the insert SQL
         */
        StringBuffer insertSQL = new StringBuffer("INSERT INTO " + tableName
                + "(");
        StringBuffer insertColNames = new StringBuffer();
        StringBuffer insertPlaceholder = new StringBuffer();
        Object[] insertColValues = new Object[columnCount];

        int updateCount = 0;
        for (int i = 1; i <= columnCount; i++) {
            boolean isColUpdate = currentRowSet.columnUpdated(i);
            if (isColUpdate) {
                insertColNames.append(colNames[i - 1] + ",");
                insertPlaceholder.append("?,");
                insertColValues[updateCount] = currentRowSet.getObject(i);
                updateCount++;
            }
        }
        if (updateCount == 0) {
            return;
        }

        insertSQL.append(subStringN(insertColNames.toString(), 1));
        insertSQL.append(") values (");
        insertSQL.append(subStringN(insertPlaceholder.toString(), 1));
        insertSQL.append(")");

        /*
         * the second step: execute SQL
         */
        PreparedStatement preSt = getConnection().prepareStatement(
                insertSQL.toString());
        for (int i = 0; i < updateCount; i++) {
            preSt.setObject(i + 1, insertColValues[i]);
        }
        try {
            preSt.executeUpdate();
        } catch (SQLException e) {
            // TODO generate SyncProviderException
            throw new SyncProviderException();
        } finally {
            preSt.close();
        }
    }

    /**
     * Delete the current row from DB
     * 
     * @throws SQLException
     */
    private void deleteCurrentRow() throws SQLException {
        /*
         * the first step: generate the delete SQL
         */
        StringBuffer deleteSQL = new StringBuffer("DELETE FROM " + tableName //$NON-NLS-1$
                + " WHERE "); //$NON-NLS-1$
        deleteSQL.append(generateQueryCondition());

        /*
         * the second step: execute SQL
         */
        PreparedStatement preSt = getConnection().prepareStatement(
                deleteSQL.toString());
        fillParamInPreStatement(preSt, 1);
        preSt.executeUpdate();
    }

    /**
     * Update the current row to DB
     * 
     * @throws SQLException
     */
    @SuppressWarnings("nls")
    private void updateCurrentRow() throws SQLException {
        /*
         * the first step: generate the delete SQL
         */
        StringBuffer updateSQL = new StringBuffer("UPDATE " + tableName
                + " SET ");
        StringBuffer updateCols = new StringBuffer();
        Object[] updateColValues = new Object[columnCount];
        int[] updateColIndexs = new int[columnCount];

        int updateCount = 0;
        for (int i = 1; i <= columnCount; i++) {
            boolean isColUpdate = currentRowSet.columnUpdated(i);
            if (isColUpdate) {
                updateCols.append(colNames[i - 1] + " = ?, ");
                updateColValues[updateCount] = currentRowSet.getObject(i);
                updateColIndexs[updateCount] = i;
                updateCount++;
            }
        }
        if (updateCount == 0) {
            return;
        }
        updateSQL.append(subStringN(updateCols.toString(), 2));
        updateSQL.append(" WHERE ");
        updateSQL.append(generateQueryCondition());

        /*
         * the second step: execute SQL
         */
        PreparedStatement preSt = getConnection().prepareStatement(
                updateSQL.toString());
        // the SET part of SQL
        for (int i = 0; i < updateCount; i++) {
            if (updateColValues[i] == null) {
                preSt.setNull(i + 1, currentRowSet.getMetaData().getColumnType(
                        updateColIndexs[i]));
            } else {
                preSt.setObject(i + 1, updateColValues[i]);
            }
        }
        // the WHERE part of SQL
        fillParamInPreStatement(preSt, updateCount + 1);
        try {
            preSt.executeUpdate();
        } catch (SQLException e) {
            // TODO generate SyncProviderException
            throw new SyncProviderException();
        } finally {
            preSt.close();
        }
    }

    private void initial(RowSetInternal theRowSet) throws SQLException {
        currentRowSet = (CachedRowSetImpl) theRowSet;
        // initial environment
        originalRowSet = (CachedRowSet) currentRowSet.getOriginal();
        // originalConnection = currentRowSet.getConnection();
        tableName = currentRowSet.getTableName();
        columnCount = currentRowSet.getMetaData().getColumnCount();
        colNames = new String[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            colNames[i - 1] = currentRowSet.getMetaData().getColumnName(i);
        }
    }

    /**
     * Compare the current row's data between database and CachedRowSet to check
     * whether it has been changed in database.
     * 
     * @return if conflict exists, return true; else, return false
     * @throws SQLException
     */
    private boolean isConflictExistForCurrentRow() throws SQLException {
        boolean isExist = true;
        originalRowSet.absolute(currentRowSet.getRow()); // the original data

        StringBuffer querySQL = new StringBuffer("SELECT COUNT(*) FROM " //$NON-NLS-1$
                + tableName + " WHERE "); //$NON-NLS-1$
        querySQL.append(generateQueryCondition());

        PreparedStatement preSt = getConnection().prepareStatement(
                querySQL.toString());
        fillParamInPreStatement(preSt, 1);
        ResultSet queryRs = preSt.executeQuery();
        if (queryRs.next()) {
            if (queryRs.getInt(1) == 1) {
                isExist = false;
            }
        }
        queryRs.close();
        preSt.close();

        return isExist;
    }

    /**
     * Generate the query condition after the keyword "WHERE" in SQL. Expression
     * likes as: COLUMN1 = ? AND COLUMN2 = ?
     * 
     * @return the SQL query expression
     */
    @SuppressWarnings("nls")
    private String generateQueryCondition() throws SQLException {
        StringBuffer queryCondtion = new StringBuffer(" ");
        for (int i = 0; i < colNames.length; i++) {
            if (originalRowSet.getObject(i + 1) == null) {
                queryCondtion.append(colNames[i] + " is null ");
            } else {
                queryCondtion.append(colNames[i] + " = ? ");
            }
            if (i != colNames.length - 1) {
                queryCondtion.append(" and ");
            }
        }
        return queryCondtion.toString();
    }

    /**
     * Fill all the parameters in PreparedStatement
     * 
     * @param preSt
     *            PreparedStatement
     * @param fromIndex
     *            It must be greater than 0
     * @throws SQLException
     */
    private void fillParamInPreStatement(PreparedStatement preSt, int fromIndex)
            throws SQLException {
        int notNullCount = fromIndex;
        for (int i = 1; i <= columnCount; i++) {
            if (originalRowSet.getObject(i) != null) {
                preSt.setObject(notNullCount, originalRowSet.getObject(i));
                notNullCount++;
            }
        }
    }

    private String subStringN(String input, int n) {
        return input.substring(0, input.length() - n);
    }
}
