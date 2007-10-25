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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.RowSetInternal;
import javax.sql.RowSetWriter;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.spi.SyncProviderException;

import org.apache.harmony.sql.internal.nls.Messages;

public class CachedRowSetWriter implements RowSetWriter {

    private ResultSet primaryKeys;

    private CachedRowSet originalRowSet;

    private CachedRowSet cachedKeySet;

    private CachedRowSetImpl currentRowSet;

    private Connection originalConnection;

    private String tableName;

    private String sql;

    private int columnCount, keysCount, keyColumnNumber;

    private int signal = 0;

    private Statement statement;

    private String keyColumnName, whereStatementForOriginal,
            whereStatementForCurrent;

    public boolean writeData(RowSetInternal theRowSet) throws SQLException {
        // use an optimistic concurrency control mechanism
        initial(theRowSet);
        // analyse every row and do responsible task.
        currentRowSet.first();
        originalRowSet.first();
        while (currentRowSet.next()) {
            // rolling with currentRowSet
            if (originalRowSet.next()) {
                // deal with updated or deleted row which need do conflict check
                if (checkConflictNotExist()) {
                    writeRowData();
                } else {
                    cleanEnvironment();
                    throw new SyncProviderException(Messages
                            .getString("rowset.5"));
                }
            } else {
                // deal with inserted row which was added comparing the
                // originalDataSet
                // the data can be inserted directly
                // FIXME: need pre-check before insert into database?
                writeRowData();
            }
        }

        cleanEnvironment();
        return true;
    }

    private void initial(RowSetInternal theRowSet) throws SQLException {
        currentRowSet = (CachedRowSetImpl) theRowSet;
        // initial environment
        originalRowSet = (CachedRowSet) currentRowSet.getOriginal();
        originalConnection = currentRowSet.getConnection();
        cachedKeySet = new CachedRowSetImpl();
        tableName = currentRowSet.getTableName();
        columnCount = currentRowSet.getMetaData().getColumnCount();
        primaryKeys = originalConnection.getMetaData().getPrimaryKeys("",
                currentRowSet.getMetaData().getSchemaName(1), tableName);
        cachedKeySet.populate(primaryKeys);
        keysCount = cachedKeySet.getMetaData().getColumnCount();
    }

    private void writeRowData() throws SQLException {
        try {
            createScriptForWriteBack();
            statement = originalConnection.prepareStatement(sql);
            switch (signal) {
            case 0:
                fillParasOfKeys(currentRowSet, 0);
                break;
            case 1:
                fillParasOfAllColumn();
                break;
            case 2:
                fillParasOfAllColumn();
                fillParasOfKeys(currentRowSet, columnCount);
                break;
            default:
                break;
            }
            ((PreparedStatement) statement).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            new SQLException(Messages.getString("rowset.6"));
        }
    }

    private void createScriptForWriteBack() throws SQLException {
        cachedKeySet.first();
        whereStatementForCurrent = "";
        String insertCollector = "", updateCollector = "";
        // FIXME:uses getUpdateMask()

        while (cachedKeySet.next()) {
            keyColumnName = cachedKeySet.getString("COLUMN_NAME");
            whereStatementForCurrent = whereStatementForCurrent + keyColumnName
                    + " = ? " + " and ";
        }

        whereStatementForCurrent = subStringN(whereStatementForCurrent, 5);

        // insertCollector: all column
        for (int i = 1; i <= columnCount; i++) {
            insertCollector = insertCollector + " ? "+" , ";            
        }
        insertCollector = subStringN(insertCollector, 3);
        
        // update: all column
        ResultSetMetaData tempRSMD = currentRowSet.getMetaData();
        for (int i = 1; i <= columnCount; i++) {
            updateCollector = updateCollector + tempRSMD.getColumnName(i)
                    + "= ? , ";
        }
        updateCollector = subStringN(updateCollector, 3);

        if (currentRowSet.getCurrentRow().getDelete()) {
            // paras of where: pks
            sql = " delete from " + tableName + " where "
                    + whereStatementForCurrent;
            signal = 0;
        } else if (currentRowSet.getCurrentRow().getInsert()) {
            // paras of insert : all
            sql = " insert into " + tableName + " values " + " ( "
                    + insertCollector + " ) ";
            signal = 1;
        } else {
            // paras of update and where : all + pks
            sql = " update " + tableName + " set " + updateCollector
                    + " where " + whereStatementForCurrent;
            signal = 2;
        }
    }

    private String subStringN(String input, int n) {
        input = input.substring(0, input.length() - n);
        return input;
    }

    private void createScriptForCheck() throws SQLException {
        // formulate the Query SQL
        String tempSelector = "";
        whereStatementForOriginal = "";
        cachedKeySet.first();
        for (int i = 1; i <= columnCount; i++)
            tempSelector = tempSelector
                    + originalRowSet.getMetaData().getColumnName(i) + ", ";
        tempSelector = tempSelector.substring(0, tempSelector.length() - 2);
        sql = "select " + tempSelector + " from " + tableName + " where ";
        while (cachedKeySet.next()) {
            keyColumnName = cachedKeySet.getString("COLUMN_NAME");
            whereStatementForOriginal = whereStatementForOriginal
                    + keyColumnName + " = ? " + " and ";
            whereStatementForOriginal = subStringN(whereStatementForOriginal, 5);
        }
        cachedKeySet.first();
        sql = sql + whereStatementForOriginal;
    }

    private void fillParasOfKeys(CachedRowSet inputRS, int from)
            throws SQLException {
        cachedKeySet.first();
        int i = from+1;        
        while (cachedKeySet.next()) {
            keyColumnName = cachedKeySet.getString("COLUMN_NAME");
            ((PreparedStatement) statement).setObject(i++, inputRS
                    .getObject(keyColumnName));
        }
    }

    private void fillParasOfAllColumn() throws SQLException {
        for (int i = 1; i <= columnCount; i++)
            ((PreparedStatement) statement).setObject(i, currentRowSet
                    .getObject(i));
    }

    private boolean checkConflictNotExist() {
        try {
            createScriptForCheck();
            statement = originalConnection.prepareStatement(sql);
            fillParasOfKeys(originalRowSet, 0);
            ResultSet dataInDB = ((PreparedStatement) statement).executeQuery();
            sql = "";
            // compare line by line, column by column
            if (dataInDB.next()) {
                for (int i = 1; i <= dataInDB.getMetaData().getColumnCount(); i++) {
                    if (!(dataInDB.getObject(i).equals(originalRowSet
                            .getObject(i))))
                        return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private void cleanEnvironment() {
        try {
            originalRowSet.close();
            originalConnection.close();
            cachedKeySet.close();
            statement.close();
            currentRowSet.close();
            primaryKeys.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // end class
}
