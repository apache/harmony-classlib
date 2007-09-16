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
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Map;

import javax.sql.RowSet;
import javax.sql.RowSetEvent;
import javax.sql.RowSetInternal;
import javax.sql.RowSetMetaData;
import javax.sql.RowSetWriter;
import javax.sql.rowset.BaseRowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetMetaDataImpl;
import javax.sql.rowset.RowSetWarning;
import javax.sql.rowset.spi.SyncFactory;
import javax.sql.rowset.spi.SyncFactoryException;
import javax.sql.rowset.spi.SyncProvider;
import javax.sql.rowset.spi.SyncProviderException;

import org.apache.harmony.luni.util.NotImplementedException;
import org.apache.harmony.sql.internal.nls.Messages;

public class CachedRowSetImpl extends BaseRowSet implements CachedRowSet,
        RowSetInternal {

    private ArrayList<CachedRow> rows;

    private RowSetMetaData meta;

    private CachedRow currentRow;

    // start from : 0 rather than 1.
    private int currentRowIndex;

    private int pageSize;

    private String tableName;

    private int rememberedCursorPosition;

    private CachedRow insertRow;

    private int columnCount;

    private SyncProvider syncProvider;

    private String dataBaseURL;

    public CachedRowSetImpl(String providerID) throws SyncFactoryException {
        syncProvider = SyncFactory.getInstance(providerID);
    }

    public CachedRowSetImpl() throws SyncFactoryException {
        // this("org.apache.harmony.sql.rowset.HYOptimisticProvider");
    }

    public void setRows(ArrayList<CachedRow> data, int cloumnCount) {
        this.rows = data;
        this.columnCount = cloumnCount;
    }

    public void acceptChanges() throws SyncProviderException {
        // TODO:
        // ?? 1. use the provider
        // 2. use the connections defined in the resultset
        try {
            RowSetWriter rowSetWriter = syncProvider.getRowSetWriter();
            rowSetWriter.writeData(this);
            // acceptChanges(this.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SyncProviderException();
        }
    }

    public void acceptChanges(Connection con) throws SyncProviderException {
        if (currentRow == insertRow)
            throw new SyncProviderException();
        throw new NotImplementedException();
    }

    public boolean columnUpdated(int idx) throws SQLException {
        if (currentRow == null || idx > meta.getColumnCount()) {
            // rowset.0 = Not a valid position
            throw new SQLException(Messages.getString("rowset.0"));
        }
        return currentRow.getUpdateMask(idx - 1);
    }

    public boolean columnUpdated(String columnName) throws SQLException {
        return columnUpdated(getIndexByName(columnName));
    }

    private int getIndexByName(String columnName) throws SQLException {
        for (int i = 1; i <= meta.getColumnCount(); i++) {
            if (columnName.equalsIgnoreCase(meta.getColumnName(i))) {
                return i;
            }
        }
        // rowset.1=Not a valid column name
        throw new SQLException(Messages.getString("rowset.1"));
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
        if (currentRow == null)
            throw new SQLException();
        // ResultSet originalRow.s
        throw new NotImplementedException();
    }

    public int getPageSize() {
        return pageSize;
    }

    public RowSetWarning getRowSetWarnings() throws SQLException {
        throw new NotImplementedException();
    }

    public SyncProvider getSyncProvider() throws SQLException {
        return this.syncProvider;
    }

    public String getTableName() throws SQLException {
        return tableName;
    }

    public boolean nextPage() throws SQLException {
        throw new NotImplementedException();
    }

    public void populate(ResultSet data) throws SQLException {
        populate(data, 0);
    }

    public void populate(ResultSet rs, int startRow) throws SQLException {
        new CachedRowSetReader(rs, startRow).readData(this);
        composeMetaData(rs.getMetaData());
        dataBaseURL = rs.getStatement().getConnection().getMetaData().getURL();
    }

    private void composeMetaData(ResultSetMetaData metaData)
            throws SQLException {
        RowSetMetaDataImpl rowSetMetaData = new RowSetMetaDataImpl();
        rowSetMetaData.setColumnCount(metaData.getColumnCount());
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            rowSetMetaData.setColumnName(i, metaData.getColumnName(i));
        }
        // TODO set other meta info when necessary
        this.meta = rowSetMetaData;
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
        this.meta = md;
    }

    public void setOriginalRow() throws SQLException {
        throw new NotImplementedException();
    }

    public void setPageSize(int size) throws SQLException {
        if (size < 0) {
            // rowset.2=Negative page size
            throw new SQLException(Messages.getString("rowset.2"));
        }
        this.pageSize = size;
    }

    public void setSyncProvider(String provider) throws SQLException {
        // If a different concurrency control mechanism is desired, a different
        // implementation of SyncProvider can be plugged in using the method
        // setSyncProvider
        syncProvider = SyncFactory.getInstance(provider);
    }

    public void setTableName(String tabName) throws SQLException {
        if (tabName == null) {
            // rowset.3=Table name should not be null
            throw new SQLException("rowset.3");
        }
        this.tableName = tabName;
    }

    public int size() {
        return rows.size();
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
        checkValidRow();
        currentRow.setDelete();
    }

    private void checkValidRow() throws SQLException {
        if (currentRow == null) {
            // rowset.0 = Not a valid position
            throw new SQLException(Messages.getString("rowset.0"));
        }
    }

    public int findColumn(String columnName) throws SQLException {
        throw new NotImplementedException();
    }

    public boolean first() throws SQLException {
        if (rows.size() == 0)
            return false;
        this.currentRowIndex = 0;
        this.currentRow = (CachedRow) rows.get(0);
        return true;
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
        return this.currentRow.getInt(columnIndex);
    }

    public int getInt(String columnName) throws SQLException {
        return this.currentRow.getInt(getIndexByName(columnName));
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

    // columnIndex: from 1 rather than 0
    public String getString(int columnIndex) throws SQLException {
        return currentRow.getString(columnIndex);
    }

    public String getString(String columnName) throws SQLException {
        return currentRow.getString(getIndexByName(columnName));
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
        checkValidRow();
        if (currentRow != insertRow) {
            // rowset.4=Not an insert row
            throw new SQLException(Messages.getString("rowset.4"));
        }
        currentRow.setInsert();
        rows.add(insertRow);
        currentRowIndex++;
        // TODO insert the data into database
        // insertRowToDB(rows);
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
        this.currentRowIndex = rememberedCursorPosition;
        this.currentRow = rows.get(currentRowIndex);
    }

    public void moveToInsertRow() throws SQLException {
        insertRow = new CachedRow(new Object[columnCount]);
        this.currentRow = insertRow;
        this.rememberedCursorPosition = this.currentRowIndex;
        this.currentRowIndex = rows.size();
    }

    public boolean next() throws SQLException {
        currentRowIndex++;
        if (rows.size() < currentRowIndex) {
            return false;
        }
        currentRow = rows.get(currentRowIndex - 1);
        return true;
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
        checkValidRow();
        return currentRow.getDelete();
    }

    public boolean rowInserted() throws SQLException {
        boolean sign = false;
        for (int i = 0; i < meta.getColumnCount(); ++i)
            sign = this.currentRow.getUpdateMask(i) | sign;
        return sign;
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
        currentRow.updateInt(columnIndex, x);
    }

    public void updateInt(String columnName, int x) throws SQLException {
        currentRow.updateInt(getIndexByName(columnName), x);
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
        if ((currentRow == insertRow)
                || (this.getConcurrency() == (ResultSet.CONCUR_READ_ONLY)))
            throw new SQLException();
        rows.set(currentRowIndex, currentRow);
    }

    public void updateShort(int columnIndex, short x) throws SQLException {
        throw new NotImplementedException();
    }

    public void updateShort(String columnName, short x) throws SQLException {
        throw new NotImplementedException();
    }

    public void updateString(int columnIndex, String x) throws SQLException {
        currentRow.updateString(columnIndex, x);
    }

    public void updateString(String columnName, String x) throws SQLException {
        currentRow.updateString(getIndexByName(columnName), x);
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

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dataBaseURL);
    }

    public void updateNCharacterStream(String columnLabel, Reader reader){
        throw new NotImplementedException();
    }
    
    public void updateNCharacterStream(int columnIndex, Reader reader){
        throw new NotImplementedException();
    }
    
    public void updateNCharacterStream(String columnLabel, Reader reader, long x){
        throw new NotImplementedException();
    }
    
    public void updateNCharacterStream(int columnIndex, Reader reader, long x){
        throw new NotImplementedException();
    }
    
    public void updateCharacterStream(String columnLabel, Reader reader){
        throw new NotImplementedException();
    }
    
    public void updateCharacterStream(int columnIndex, Reader reader){
        throw new NotImplementedException();
    }
    
    public void updateCharacterStream(String columnLabel, Reader reader, long x){
        throw new NotImplementedException();
    }
    
    public void updateCharacterStream(int columnIndex, Reader reader, long x){
        throw new NotImplementedException();
    }
    
    public void updateBinaryStream(String columnLabel, InputStream stream){
        throw new NotImplementedException();
    }
    
    public void updateBinaryStream(int columnIndex, InputStream stream){
        throw new NotImplementedException();
    }
    
    public void updateBinaryStream(String columnLabel, InputStream stream, long x){
        throw new NotImplementedException();
    }
    
    public void updateBinaryStream(int columnIndex, InputStream stream, long x){
        throw new NotImplementedException();
    }
    
    public void updateRowId(String columnLabel, RowId x){
        throw new NotImplementedException();
    }
    
    public void updateRowId(int columnIndex, RowId x){
        throw new NotImplementedException();
    }
    
    public void updateNClob(String columnLabel, Reader reader){
        throw new NotImplementedException();
    }
    
    public void updateNClob(int columnIndex, Reader reader){
        throw new NotImplementedException();
    }
    
    public void updateNClob(String columnLabel, Reader reader, long x){
        throw new NotImplementedException();
    }
    
    public void updateNClob(int columnIndex, Reader reader, long x){
        throw new NotImplementedException();
    }
    
    public void updateNClob(String columnLabel, NClob nClob){
        throw new NotImplementedException();
    }
    
    public void updateNClob(int columnIndex, NClob nClob){
        throw new NotImplementedException();
    }
    
    public void updateNString(String columnLabel, String nString){
        throw new NotImplementedException();
    }
    
    public void updateNString(int columnIndex, String nString){
        throw new NotImplementedException();
    }
    
    public void updateAsciiStream(String columnLabel, InputStream stream){
        throw new NotImplementedException();
    }
    
    public void updateAsciiStream(int columnIndex, InputStream stream){
        throw new NotImplementedException();
    }
    
    public void updateAsciiStream(String columnLabel, InputStream stream, long x){
        throw new NotImplementedException();
    }
    
    public void updateAsciiStream(int columnIndex, InputStream stream, long x){
        throw new NotImplementedException();
    }
    
    public void updateSQLXML(String columnLabel, SQLXML xmlObject){
        throw new NotImplementedException();
    }
    
    public void updateSQLXML(int columnIndex, SQLXML xmlObject){
        throw new NotImplementedException();
    }
    
    public void updateBlob(String columnLabel, InputStream stream){
        throw new NotImplementedException();
    }
    
    public void updateBlob(int columnIndex, InputStream stream){
        throw new NotImplementedException();
    }
    
    public void updateBlob(String columnLabel, InputStream stream, long x){
        throw new NotImplementedException();
    }
    
    public void updateBlob(int columnIndex, InputStream stream, long x){
        throw new NotImplementedException();
    }
    
    public void updateClob(String columnLabel, Reader reader){
        throw new NotImplementedException();
    }
    
    public void updateClob(int columnIndex, Reader reader){
        throw new NotImplementedException();
    }
    
    public void updateClob(String columnLabel, Reader reader, long x){
        throw new NotImplementedException();
    }
    
    public void updateClob(int columnIndex, Reader reader, long x){
        throw new NotImplementedException();
    }
    
    public String getNString(int columnIndex){
        throw new NotImplementedException();
    }
    
    public String getNString(String columnLabel){
        throw new NotImplementedException();
    }
    
    public RowId getRowId(int columnIndex){
        throw new NotImplementedException();
    }
    
    public RowId getRowId(String columnLabel){
        throw new NotImplementedException();
    }
    
    public boolean isWrapperFor(Class<?> iface){
        throw new NotImplementedException();
    }
    
    public Reader getNCharacterStream(int columnIndex){
        throw new NotImplementedException();
    }
    
    public Reader getNCharacterStream(String columnLabel){
        throw new NotImplementedException();
    }
    
    public <T> T unwrap(Class<T> iface){
        throw new NotImplementedException();
    }
    
    public int getHoldability(){
        throw new NotImplementedException();
    }

    public NClob getNClob(int columnIndex) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public NClob getNClob(String columnLabel) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public SQLXML getSQLXML(int columnIndex) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public SQLXML getSQLXML(String columnLabel) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean isClosed() throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

}
