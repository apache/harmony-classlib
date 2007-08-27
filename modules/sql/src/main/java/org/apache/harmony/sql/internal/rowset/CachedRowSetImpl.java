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

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Map;

import javax.sql.RowSet;
import javax.sql.RowSetEvent;
import javax.sql.RowSetMetaData;
import javax.sql.rowset.BaseRowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetWarning;
import javax.sql.rowset.spi.SyncProvider;
import javax.sql.rowset.spi.SyncProviderException;

import org.apache.harmony.luni.util.NotImplementedException;

public class CachedRowSetImpl extends BaseRowSet implements CachedRowSet {

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

    public void rowSetPopulated(RowSetEvent event, int numRows)
            throws SQLException {
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

    public boolean absolute(int row) throws SQLException {
        throw new NotImplementedException();
        
    }

    public void afterLast() throws SQLException {
        throw new NotImplementedException();

    }

    public void beforeFirst() throws SQLException {
        throw new NotImplementedException();

    }

    public void cancelRowUpdates() throws SQLException {
        throw new NotImplementedException();

    }

    public void clearWarnings() throws SQLException {
        throw new NotImplementedException();

    }

    public void close() throws SQLException {
        throw new NotImplementedException();

    }

    public void deleteRow() throws SQLException {
        throw new NotImplementedException();

    }

    public int findColumn(String columnName) throws SQLException {
        throw new NotImplementedException();
        
    }

    public boolean first() throws SQLException {
        throw new NotImplementedException();
        
    }

    public Array getArray(int columnIndex) throws SQLException {
        throw new NotImplementedException();
        
    }

    public Array getArray(String colName) throws SQLException {
        throw new NotImplementedException();
        
    }

    public InputStream getAsciiStream(int columnIndex) throws SQLException {
        throw new NotImplementedException();
        
    }

    public InputStream getAsciiStream(String columnName) throws SQLException {
        throw new NotImplementedException();
        
    }

    public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
        throw new NotImplementedException();
        
    }

    public BigDecimal getBigDecimal(int columnIndex, int scale)
            throws SQLException {
        throw new NotImplementedException();
        
    }

    public BigDecimal getBigDecimal(String columnName) throws SQLException {
        throw new NotImplementedException();
        
    }

    public BigDecimal getBigDecimal(String columnName, int scale)
            throws SQLException {
        throw new NotImplementedException();
        
    }

    public InputStream getBinaryStream(int columnIndex) throws SQLException {
        throw new NotImplementedException();
        
    }

    public InputStream getBinaryStream(String columnName) throws SQLException {
        throw new NotImplementedException();
        
    }

    public Blob getBlob(int columnIndex) throws SQLException {
        throw new NotImplementedException();
        
    }

    public Blob getBlob(String columnName) throws SQLException {
        throw new NotImplementedException();
        
    }

    public boolean getBoolean(int columnIndex) throws SQLException {
        throw new NotImplementedException();
        
    }

    public boolean getBoolean(String columnName) throws SQLException {
        throw new NotImplementedException();
        
    }

    public byte getByte(int columnIndex) throws SQLException {
        throw new NotImplementedException();
        
    }

    public byte getByte(String columnName) throws SQLException {
        throw new NotImplementedException();
        
    }

    public byte[] getBytes(int columnIndex) throws SQLException {
        throw new NotImplementedException();
        
    }

    public byte[] getBytes(String columnName) throws SQLException {
        throw new NotImplementedException();
        
    }

    public Reader getCharacterStream(int columnIndex) throws SQLException {
        throw new NotImplementedException();
        
    }

    public Reader getCharacterStream(String columnName) throws SQLException {
        throw new NotImplementedException();
        
    }

    public Clob getClob(int columnIndex) throws SQLException {
        throw new NotImplementedException();
        
    }

    public Clob getClob(String colName) throws SQLException {
        throw new NotImplementedException();
        
    }

    public String getCursorName() throws SQLException {
        throw new NotImplementedException();
        
    }

    public Date getDate(int columnIndex) throws SQLException {
        throw new NotImplementedException();
        
    }

    public Date getDate(int columnIndex, Calendar cal) throws SQLException {
        throw new NotImplementedException();
        
    }

    public Date getDate(String columnName) throws SQLException {
        throw new NotImplementedException();
        
    }

    public Date getDate(String columnName, Calendar cal) throws SQLException {
        throw new NotImplementedException();
        
    }

    public double getDouble(int columnIndex) throws SQLException {
        throw new NotImplementedException();
        
    }

    public double getDouble(String columnName) throws SQLException {
        throw new NotImplementedException();
        
    }

    public float getFloat(int columnIndex) throws SQLException {
        throw new NotImplementedException();
        
    }

    public float getFloat(String columnName) throws SQLException {
        throw new NotImplementedException();
        
    }

    public int getInt(int columnIndex) throws SQLException {
        throw new NotImplementedException();
        
    }

    public int getInt(String columnName) throws SQLException {
        throw new NotImplementedException();
        
    }

    public long getLong(int columnIndex) throws SQLException {
        throw new NotImplementedException();
        
    }

    public long getLong(String columnName) throws SQLException {
        throw new NotImplementedException();
        
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        throw new NotImplementedException();
        
    }

    public Object getObject(int columnIndex) throws SQLException {
        throw new NotImplementedException();
        
    }

    public Object getObject(int columnIndex, Map<String, Class<?>> map)
            throws SQLException {
        throw new NotImplementedException();
        
    }

    public Object getObject(String columnName) throws SQLException {
        throw new NotImplementedException();
        
    }

    public Object getObject(String columnName, Map<String, Class<?>> map)
            throws SQLException {
        throw new NotImplementedException();
        
    }

    public Ref getRef(int columnIndex) throws SQLException {
        throw new NotImplementedException();
        
    }

    public Ref getRef(String colName) throws SQLException {
        throw new NotImplementedException();
        
    }

    public int getRow() throws SQLException {
        throw new NotImplementedException();
        
    }

    public short getShort(int columnIndex) throws SQLException {
        throw new NotImplementedException();
        
    }

    public short getShort(String columnName) throws SQLException {
        throw new NotImplementedException();
        
    }

    public Statement getStatement() throws SQLException {
        throw new NotImplementedException();
        
    }

    public String getString(int columnIndex) throws SQLException {
        throw new NotImplementedException();
        
    }

    public String getString(String columnName) throws SQLException {
        throw new NotImplementedException();
        
    }

    public Time getTime(int columnIndex) throws SQLException {
        throw new NotImplementedException();
        
    }

    public Time getTime(int columnIndex, Calendar cal) throws SQLException {
        throw new NotImplementedException();
        
    }

    public Time getTime(String columnName) throws SQLException {
        throw new NotImplementedException();
        
    }

    public Time getTime(String columnName, Calendar cal) throws SQLException {
        throw new NotImplementedException();
        
    }

    public Timestamp getTimestamp(int columnIndex) throws SQLException {
        throw new NotImplementedException();
        
    }

    public Timestamp getTimestamp(int columnIndex, Calendar cal)
            throws SQLException {
        throw new NotImplementedException();
        
    }

    public Timestamp getTimestamp(String columnName) throws SQLException {
        throw new NotImplementedException();
        
    }

    public Timestamp getTimestamp(String columnName, Calendar cal)
            throws SQLException {
        throw new NotImplementedException();
        
    }

    public java.net.URL getURL(int columnIndex) throws SQLException {
        throw new NotImplementedException();
        
    }

    public java.net.URL getURL(String columnName) throws SQLException {
        throw new NotImplementedException();
        
    }

    public InputStream getUnicodeStream(int columnIndex) throws SQLException {
        throw new NotImplementedException();
        
    }

    public InputStream getUnicodeStream(String columnName) throws SQLException {
        throw new NotImplementedException();
        
    }

    public SQLWarning getWarnings() throws SQLException {
        throw new NotImplementedException();
        
    }

    public void insertRow() throws SQLException {
        throw new NotImplementedException();

    }

    public boolean isAfterLast() throws SQLException {
        throw new NotImplementedException();
        
    }

    public boolean isBeforeFirst() throws SQLException {
        throw new NotImplementedException();
        
    }

    public boolean isFirst() throws SQLException {
        throw new NotImplementedException();
        
    }

    public boolean isLast() throws SQLException {
        throw new NotImplementedException();
        
    }

    public boolean last() throws SQLException {
        throw new NotImplementedException();
        
    }

    public void moveToCurrentRow() throws SQLException {
        throw new NotImplementedException();

    }

    public void moveToInsertRow() throws SQLException {
        throw new NotImplementedException();

    }

    public boolean next() throws SQLException {
        throw new NotImplementedException();
        
    }

    public boolean previous() throws SQLException {
        throw new NotImplementedException();
        
    }

    public void refreshRow() throws SQLException {
        throw new NotImplementedException();

    }

    public boolean relative(int rows) throws SQLException {
        throw new NotImplementedException();
        
    }

    public boolean rowDeleted() throws SQLException {
        throw new NotImplementedException();
        
    }

    public boolean rowInserted() throws SQLException {
        throw new NotImplementedException();
        
    }

    public boolean rowUpdated() throws SQLException {
        throw new NotImplementedException();
        
    }

    public void updateArray(int columnIndex, Array x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateArray(String columnName, Array x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateAsciiStream(int columnIndex, InputStream x, int length)
            throws SQLException {
        throw new NotImplementedException();

    }

    public void updateAsciiStream(String columnName, InputStream x, int length)
            throws SQLException {
        throw new NotImplementedException();

    }

    public void updateBigDecimal(int columnIndex, BigDecimal x)
            throws SQLException {
        throw new NotImplementedException();

    }

    public void updateBigDecimal(String columnName, BigDecimal x)
            throws SQLException {
        throw new NotImplementedException();

    }

    public void updateBinaryStream(int columnIndex, InputStream x, int length)
            throws SQLException {
        throw new NotImplementedException();

    }

    public void updateBinaryStream(String columnName, InputStream x, int length)
            throws SQLException {
        throw new NotImplementedException();

    }

    public void updateBlob(int columnIndex, Blob x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateBlob(String columnName, Blob x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateBoolean(int columnIndex, boolean x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateBoolean(String columnName, boolean x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateByte(int columnIndex, byte x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateByte(String columnName, byte x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateBytes(int columnIndex, byte[] x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateBytes(String columnName, byte[] x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateCharacterStream(int columnIndex, Reader x, int length)
            throws SQLException {
        throw new NotImplementedException();

    }

    public void updateCharacterStream(String columnName, Reader reader,
            int length) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateClob(int columnIndex, Clob x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateClob(String columnName, Clob x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateDate(int columnIndex, Date x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateDate(String columnName, Date x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateDouble(int columnIndex, double x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateDouble(String columnName, double x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateFloat(int columnIndex, float x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateFloat(String columnName, float x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateInt(int columnIndex, int x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateInt(String columnName, int x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateLong(int columnIndex, long x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateLong(String columnName, long x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateNull(int columnIndex) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateNull(String columnName) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateObject(int columnIndex, Object x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateObject(int columnIndex, Object x, int scale)
            throws SQLException {
        throw new NotImplementedException();

    }

    public void updateObject(String columnName, Object x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateObject(String columnName, Object x, int scale)
            throws SQLException {
        throw new NotImplementedException();

    }

    public void updateRef(int columnIndex, Ref x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateRef(String columnName, Ref x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateRow() throws SQLException {
        throw new NotImplementedException();

    }

    public void updateShort(int columnIndex, short x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateShort(String columnName, short x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateString(int columnIndex, String x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateString(String columnName, String x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateTime(int columnIndex, Time x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateTime(String columnName, Time x) throws SQLException {
        throw new NotImplementedException();

    }

    public void updateTimestamp(int columnIndex, Timestamp x)
            throws SQLException {
        throw new NotImplementedException();

    }

    public void updateTimestamp(String columnName, Timestamp x)
            throws SQLException {
        throw new NotImplementedException();

    }

    public boolean wasNull() throws SQLException {
        throw new NotImplementedException();
        
    }

    public void execute() throws SQLException {
        throw new NotImplementedException();

    }

}
