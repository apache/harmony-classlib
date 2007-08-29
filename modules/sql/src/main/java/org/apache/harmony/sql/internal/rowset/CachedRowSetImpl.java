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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Collection;

import javax.sql.RowSet;
import javax.sql.RowSetEvent;
import javax.sql.RowSetMetaData;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetWarning;
import javax.sql.rowset.spi.SyncProvider;
import javax.sql.rowset.spi.SyncProviderException;

import org.apache.harmony.luni.util.NotImplementedException;

public class CachedRowSetImpl extends AbstractRowSetImpl implements
        CachedRowSet {

    public void acceptChanges() throws SyncProviderException {
        throw new NotImplementedException();
    }

    public void acceptChanges(Connection con) throws SyncProviderException {
        throw new NotImplementedException();
    }

    public boolean columnUpdated(int idx) throws SQLException {
        throw new NotImplementedException();
    }

    public boolean columnUpdated(String columnName) throws SQLException {
        throw new NotImplementedException();
    }

    public void commit() throws SQLException {
        throw new NotImplementedException();
    }

    public CachedRowSet createCopy() throws SQLException {
        throw new NotImplementedException();
    }

    public CachedRowSet createCopyNoConstraints() throws SQLException {
        throw new NotImplementedException();
    }

    public CachedRowSet createCopySchema() throws SQLException {
        throw new NotImplementedException();
    }

    public RowSet createShared() throws SQLException {
        throw new NotImplementedException();
    }

    public void execute(Connection conn) throws SQLException {
        throw new NotImplementedException();
    }

    public int[] getKeyColumns() throws SQLException {
        throw new NotImplementedException();
    }

    public ResultSet getOriginal() throws SQLException {
        throw new NotImplementedException();
    }

    public ResultSet getOriginalRow() throws SQLException {
        throw new NotImplementedException();
    }

    public int getPageSize() {
        throw new NotImplementedException();
    }

    public RowSetWarning getRowSetWarnings() throws SQLException {
        throw new NotImplementedException();
    }

    public SyncProvider getSyncProvider() throws SQLException {
        throw new NotImplementedException();
    }

    public String getTableName() throws SQLException {
        throw new NotImplementedException();
    }

    public boolean nextPage() throws SQLException {
        throw new NotImplementedException();
    }

    public void populate(ResultSet data) throws SQLException {
        throw new NotImplementedException();
    }

    public void populate(ResultSet rs, int startRow) throws SQLException {
        throw new NotImplementedException();
    }

    public boolean previousPage() throws SQLException {
        throw new NotImplementedException();
    }

    public void release() throws SQLException {
        throw new NotImplementedException();
    }

    public void restoreOriginal() throws SQLException {
        throw new NotImplementedException();
    }

    public void rollback() throws SQLException {
        throw new NotImplementedException();
    }

    public void rollback(Savepoint s) throws SQLException {
        throw new NotImplementedException();
    }

    public void rowSetPopulated(RowSetEvent event, int numRows) throws SQLException {
        throw new NotImplementedException();
    }

    public void setKeyColumns(int[] keys) throws SQLException {
        throw new NotImplementedException();
    }

    public void setMetaData(RowSetMetaData md) throws SQLException {
        throw new NotImplementedException();
    }

    public void setOriginalRow() throws SQLException {
        throw new NotImplementedException();
    }

    public void setPageSize(int size) throws SQLException {
        throw new NotImplementedException();
    }

    public void setSyncProvider(String provider) throws SQLException {
        throw new NotImplementedException();
    }

    public void setTableName(String tabName) throws SQLException {
        throw new NotImplementedException();
    }

    public int size() {
        throw new NotImplementedException();
    }

    public Collection<?> toCollection() throws SQLException {
        throw new NotImplementedException();
    }

    public Collection<?> toCollection(int column) throws SQLException {
        throw new NotImplementedException();
    }

    public Collection<?> toCollection(String column) throws SQLException {
        throw new NotImplementedException();
    }

    public void undoDelete() throws SQLException {
        throw new NotImplementedException();
    }

    public void undoInsert() throws SQLException {
        throw new NotImplementedException();
    }

    public void undoUpdate() throws SQLException {
        throw new NotImplementedException();
    }

    public int[] getMatchColumnIndexes() throws SQLException {
        throw new NotImplementedException();
    }

    public String[] getMatchColumnNames() throws SQLException {
        throw new NotImplementedException();
    }

    public void setMatchColumn(int columnIdx) throws SQLException {
        throw new NotImplementedException();
    }

    public void setMatchColumn(int[] columnIdxes) throws SQLException {
        throw new NotImplementedException();
    }

    public void setMatchColumn(String columnName) throws SQLException {
        throw new NotImplementedException();
    }

    public void setMatchColumn(String[] columnNames) throws SQLException {
        throw new NotImplementedException();
    }

    public void unsetMatchColumn(int columnIdx) throws SQLException {
        throw new NotImplementedException();
    }

    public void unsetMatchColumn(int[] columnIdxes) throws SQLException {
        throw new NotImplementedException();
    }

    public void unsetMatchColumn(String columnName) throws SQLException {
        throw new NotImplementedException();
    }

    public void unsetMatchColumn(String[] columnName) throws SQLException {
        throw new NotImplementedException();
    }

   

}
