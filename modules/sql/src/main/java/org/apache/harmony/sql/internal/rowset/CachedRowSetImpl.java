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
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
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

    private static final long serialVersionUID = 1L;

    private ArrayList<CachedRow> rows;

    private RowSetMetaData meta;

    private CachedRow currentRow;

    private CachedRow originalRow;

    // start from : 1.
    private int currentRowIndex;

    // the number of the rows in one "page"
    private int pageSize;

    private int pageNumber = 1;

    private String tableName;

    private int rememberedCursorPosition;

    private CachedRow insertRow;

    // TODO remember to evalute it in CachedRowSetReader
    private int[] keyCols;

    private int columnCount;

    private SyncProvider syncProvider;

    // TODO where is it initialized
    private CachedRowSetImpl originalResultSet;

    private Object currentColumn;

    private SQLWarning sqlwarn;

    public CachedRowSetImpl(String providerID) throws SyncFactoryException {
        syncProvider = SyncFactory.getInstance(providerID);
    }

    public CachedRowSetImpl() throws SyncFactoryException {
        this("Apache Harmony HYOptimisticProvider");
    }

    public void setRows(ArrayList<CachedRow> data, int cloumnCount) {
        rows = data;
        this.columnCount = cloumnCount;
    }

    public void acceptChanges() throws SyncProviderException {
        if (currentRow == insertRow) {
            throw new SyncProviderException();
        }

        try {
            acceptChanges(getConnection());
        } catch (SQLException e) {
            // FIXME deal with the exception, not just print it
            e.printStackTrace();
        }
    }

    public void acceptChanges(Connection con) throws SyncProviderException {
        if (currentRow == insertRow) {
            // TODO add error messages
            throw new SyncProviderException();
        }

        try {
            setUrl(con.getMetaData().getURL());
            RowSetWriter rowSetWriter = syncProvider.getRowSetWriter();
            CachedRowSetImpl input = (CachedRowSetImpl) createCopy();
            rowSetWriter.writeData(input);
            // setOriginalRow();
            notifyRowSetChanged();
        } catch (SQLException e) {
            // TODO deal with the exception
            e.printStackTrace();
            throw new SyncProviderException();
        }
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
        getConnection().commit();
    }

    public CachedRowSet createCopy() throws SQLException {
        CachedRowSetImpl output;
        try {
            output = (CachedRowSetImpl) super.clone();
            // restore "this"'s states
            int temp = currentRowIndex;
            CachedRow cr;
            if (currentRow != null) {
                cr = currentRow.createClone();
            } else {
                cr = null;
            }

            first();

            // Deep Copy
            ArrayList<CachedRow> data = new ArrayList<CachedRow>();

            do {
                data.add(currentRow.createClone());
            } while (next());

            // TODO: should be the same granularity with RI using Debug tool
            // inspect!
            ((CachedRowSetImpl) output).setRows(data, columnCount);
            output.setMetaData(((RowSetMetaDataImpl) getMetaData()));
            output.originalResultSet = originalResultSet;
            output.setUrl(getUrl());
            output.setTableName(getTableName());

            // Constraints
            output.setReadOnly(isReadOnly());
            output.setConcurrency(getConcurrency());
            output.setType(getType());

            // recovery this's state for the modification of the operation
            // first() and next();
            currentRow = cr;
            currentRowIndex = temp;

            return output;
        } catch (CloneNotSupportedException e) {
            // FIXME deal with the exception
            e.printStackTrace();
            return null;
        }
    }

    public CachedRowSet createCopyNoConstraints() throws SQLException {
        CachedRowSetImpl output;
        try {
            output = (CachedRowSetImpl) super.clone();
            // restore "this"'s states
            int temp = currentRowIndex;
            CachedRow cr;
            if (currentRow != null) {
                cr = currentRow.createClone();
            } else {
                cr = null;
            }

            first();

            // Deep Copy
            ArrayList<CachedRow> data = new ArrayList<CachedRow>();
            do {
                data.add(currentRow.createClone());
            } while (next());

            // TODO: should be the same granularity with RI using Debug tool
            // inspect!
            ((CachedRowSetImpl) output).setRows(data, columnCount);
            output.setMetaData((RowSetMetaData) (getMetaData()));
            output.originalResultSet = originalResultSet;
            output.setUrl(getUrl());
            output.setTableName(getTableName());

            // recovery this's state for the modification of the operation
            // first() and next();
            currentRow = cr;
            currentRowIndex = temp;

            return output;
        } catch (CloneNotSupportedException e) {
            // FIXME deal with the exception
            e.printStackTrace();
            return null;
        }
    }

    public CachedRowSet createCopySchema() throws SQLException {
        // For webRowSet: represent the table structure: Columns
        CachedRowSetImpl result;
        try {
            // copy data from BaseRowSet
            result = (CachedRowSetImpl) super.clone();
            // deep copy meta data
            result.meta = copyMetaData(meta);
            result.columnCount = columnCount;
            result.keyCols = keyCols == null ? null : keyCols.clone();
            result.originalResultSet = new CachedRowSetImpl(syncProvider
                    .getProviderID());
            result.pageSize = pageSize;
            result.setSyncProvider(syncProvider.getProviderID());
            result.setTableName(getTableName());

            // clean up rows data
            result.currentColumn = null;
            result.currentRowIndex = 0;
            result.insertRow = null;
            result.originalRow = null;
            result.pageNumber = 1;
            result.rememberedCursorPosition = 0;
            result.rows = new ArrayList<CachedRow>();
            result.sqlwarn = null;
            return result;
        } catch (CloneNotSupportedException e) {
            // TODO add error message
            throw new SQLException();
        }
    }

    public RowSet createShared() throws SQLException {
        // shallow copy
        RowSet result = null;
        try {
            result = (RowSet) super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO add error message
            throw new SQLException();
        }

        return result;
    }

    public void execute(Connection conn) throws SQLException {
        // ensure the getConnection can works!
        String localCommand = getCommand();
        if (localCommand == null || getParams() == null) {
            // TODO add error messages
            throw new SQLException();
        }

        PreparedStatement ps = conn.prepareStatement(localCommand);
        Object[] params = getParams();
        for (int i = 0; i < params.length; i++)
            ps.setObject(i + 1, params[i]);

        if (ps.execute()) {
            populate(ps.getResultSet());
        }
    }

    public int[] getKeyColumns() throws SQLException {
        return keyCols == null ? null : keyCols.clone();
    }

    public ResultSet getOriginal() throws SQLException {
        return originalResultSet;
    }

    public ResultSet getOriginalRow() throws SQLException {
        if (currentRow == null) {
            // TODO add error messages
            throw new SQLException();
        }

        CachedRowSetImpl specialRset = new CachedRowSetImpl();
        ArrayList<CachedRow> data = new ArrayList<CachedRow>();
        data.add(originalRow);
        specialRset.setRows(data, columnCount);
        return specialRset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public RowSetWarning getRowSetWarnings() throws SQLException {
        throw new NotImplementedException();
    }

    public SyncProvider getSyncProvider() throws SQLException {
        return syncProvider;
    }

    public String getTableName() throws SQLException {
        return tableName;
    }

    /**
     * TODO refill the cachedrowset with pagesize, and the previous rowset was
     * replaced
     */
    public boolean nextPage() throws SQLException {
        pageNumber++;
        return false;
    }

    public void populate(ResultSet data) throws SQLException {
        populate(data, -1);
    }

    public void populate(ResultSet rs, int startRow) throws SQLException {
        initParams();
        meta = copyMetaData(rs.getMetaData());

        new CachedRowSetReader(rs, startRow).readData(this);

        setTableName(rs.getMetaData().getTableName(1));

        originalResultSet = new CachedRowSetImpl();
        new CachedRowSetReader(this, startRow).readData(originalResultSet);
        originalResultSet.setMetaData((RowSetMetaData) (getMetaData()));

        // recovery the states
        currentRow = null;
        currentRowIndex = 0;

    }

    // deep copy of ResultSetMetaData
    private RowSetMetaData copyMetaData(ResultSetMetaData metaData)
            throws SQLException {
        RowSetMetaDataImpl rowSetMetaData = new RowSetMetaDataImpl();
        rowSetMetaData.setColumnCount(metaData.getColumnCount());
        for (int columnIndex = 1; columnIndex <= metaData.getColumnCount(); columnIndex++) {
            rowSetMetaData.setAutoIncrement(columnIndex, metaData.isAutoIncrement(columnIndex));
            rowSetMetaData.setCaseSensitive(columnIndex, metaData.isCaseSensitive(columnIndex));
            rowSetMetaData.setCatalogName(columnIndex, metaData.getCatalogName(columnIndex));
            rowSetMetaData.setColumnDisplaySize(columnIndex, metaData
                    .getColumnDisplaySize(columnIndex));
            rowSetMetaData.setColumnLabel(columnIndex, metaData.getColumnLabel(columnIndex));
            rowSetMetaData.setColumnName(columnIndex, metaData.getColumnName(columnIndex));
            rowSetMetaData.setColumnType(columnIndex, metaData.getColumnType(columnIndex));
            rowSetMetaData.setColumnTypeName(columnIndex, metaData.getColumnTypeName(columnIndex));
            rowSetMetaData.setCurrency(columnIndex, metaData.isCurrency(columnIndex));
            rowSetMetaData.setNullable(columnIndex, metaData.isNullable(columnIndex));
            rowSetMetaData.setPrecision(columnIndex, metaData.getPrecision(columnIndex));
            rowSetMetaData.setScale(columnIndex, metaData.getScale(columnIndex));
            rowSetMetaData.setSchemaName(columnIndex, metaData.getSchemaName(columnIndex));
            rowSetMetaData.setSearchable(columnIndex, metaData.isSearchable(columnIndex));
            rowSetMetaData.setSigned(columnIndex, metaData.isSigned(columnIndex));
            rowSetMetaData.setTableName(columnIndex, metaData.getTableName(columnIndex));
        }
        return rowSetMetaData;
    }

    public boolean previousPage() throws SQLException {
        // TODO implement it
        return false;
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
        keyCols = keys.clone();
    }

    public void setMetaData(RowSetMetaData md) throws SQLException {
        meta = md;
    }

    public void setOriginalRow() throws SQLException {

        if (currentRow == null) {
            // TODO add error messages
            throw new SQLException();
        }
        originalRow = currentRow;
        currentRow.setNonUpdateable();

    }

    public void setPageSize(int size) throws SQLException {
        if (size < 0) {
            // rowset.2=Negative page size
            throw new SQLException(Messages.getString("rowset.2"));
        }
        if ((getMaxRows() != 0) && (getMaxRows() < size)) {
            // rowset.9=PageSize can not larger than MaxRows
            throw new SQLException(Messages.getString("rowset.9"));
        }
        pageSize = size;
    }

    public void setSyncProvider(String provider) throws SQLException {
        syncProvider = SyncFactory.getInstance(provider);
    }

    public void setTableName(String tabName) throws SQLException {
        if (tabName == null) {
            // rowset.3=Table name should not be null
            throw new SQLException("rowset.3");
        }
        tableName = tabName;
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
        if (currentRow == null) {
            // TODO add error messages
            throw new SQLException();
        }
        if (currentRow.isDelete()) {
            currentRow.undoDelete();
        }
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
        if (rows.size() == 0) {
            return false;
        }
        if (getType() == ResultSet.TYPE_FORWARD_ONLY) {
            // TODO add error messages
            throw new SQLException();
        }
        if (row < 0) {
            row = rows.size() + row + 1;
        } else if (row == 0) {
            currentRowIndex = row;
            currentRow = null;
            return true;
        }

        currentRowIndex = row;
        currentRow = (CachedRow) rows.get(currentRowIndex - 1);
        return true;
    }

    public void afterLast() throws SQLException {
        if (getType() == TYPE_FORWARD_ONLY) {
            // rowset.8=The Result Set Type is TYPE_FORWARD_ONLY
            throw new SQLException(Messages.getString("rowset.8"));
        }
        currentRowIndex = rows.size() + 1;
        currentRow = null;
    }

    public void beforeFirst() throws SQLException {
        if (getType() == TYPE_FORWARD_ONLY) {
            // rowset.8=The Result Set Type is TYPE_FORWARD_ONLY
            throw new SQLException(Messages.getString("rowset.8"));
        }
        currentRowIndex = 0;
        currentRow = null;
    }

    public void cancelRowUpdates() throws SQLException {
        throw new NotImplementedException();
    }

    public void clearWarnings() throws SQLException {
        throw new NotImplementedException();
    }

    public void close() throws SQLException {

        // TODO need more concerns!
        rows.clear();
        currentRowIndex = 0;
        currentRow = null;
        meta = null;

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
        return absolute(1);
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
        checkValidRow();
        Object value = currentRow.getObject(columnIndex);
        if (value == null) {
            return null;
        }
        return (Blob) value;
    }

    public Blob getBlob(String columnName) throws SQLException {
        return getBlob(getIndexByName(columnName));
    }

    public boolean getBoolean(int columnIndex) throws SQLException {
        checkValidRow();
        Object value = currentRow.getObject(columnIndex);
        if (value == null) {
            return false;
        }
        return ((Boolean) value).booleanValue();
    }

    public boolean getBoolean(String columnName) throws SQLException {
        return getBoolean(getIndexByName(columnName));
    }

    public byte getByte(int columnIndex) throws SQLException {
        checkValidRow();
        Object value = currentRow.getObject(columnIndex);
        if (value == null) {
            return 0;
        }
        return ((Byte) value).byteValue();
    }

    public byte getByte(String columnName) throws SQLException {
        return getByte(getIndexByName(columnName));
    }

    public byte[] getBytes(int columnIndex) throws SQLException {
        checkValidRow();
        Object value = currentRow.getObject(columnIndex);
        if (value == null) {
            return null;
        }
        return (byte[]) value;
    }

    public byte[] getBytes(String columnName) throws SQLException {
        return getBytes(getIndexByName(columnName));
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
        checkCursorValid();
        checkValidRow();
        Object value = currentRow.getObject(columnIndex);
        if (value == null) {
            return 0;
        }
        return ((Integer) value).intValue();
    }

    public int getInt(String columnName) throws SQLException {
        return getInt(getIndexByName(columnName));
    }

    public long getLong(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    public long getLong(String columnName) throws SQLException {
        throw new NotImplementedException();
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        return meta;
    }

    public Object getObject(int columnIndex) throws SQLException {
        return currentRow.getObject(columnIndex);
    }

    public Object getObject(int columnIndex, Map<String, Class<?>> map)
            throws SQLException {
        throw new NotImplementedException();
    }

    public Object getObject(String columnName) throws SQLException {
        return currentRow.getObject(getIndexByName(columnName));
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
        checkValidRow();
        Object value = currentRow.getObject(columnIndex);
        if (value == null) {
            return null;
        }
        return (String) value;
    }

    private boolean checkCursorValid() throws SQLException {
        if ((currentRowIndex <= 0) || (currentRowIndex > rows.size())) {
            // rowset.7=Not a valid cursor
            throw new SQLException(Messages.getString("rowset.7")); //$NON-NLS-1$
        }
        return false;
    }

    public String getString(String columnName) throws SQLException {
        return getString(getIndexByName(columnName));
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
        return absolute(-1);
    }

    public void moveToCurrentRow() throws SQLException {

        if (currentRow == insertRow) {
            currentRowIndex = rememberedCursorPosition;
            currentRow = rows.get(currentRowIndex - 1);
        }

    }

    public void moveToInsertRow() throws SQLException {
        insertRow = new CachedRow(new Object[columnCount]);
        currentRow = insertRow;
        rememberedCursorPosition = currentRowIndex;
        currentRowIndex = rows.size();
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
        currentRowIndex--;
        if (rows.size() < currentRowIndex) {
            return false;
        }
        currentRow = rows.get(currentRowIndex - 1);
        return true;
    }

    public void refreshRow() throws SQLException {
        throw new NotImplementedException();
    }

    public boolean relative(int rows) throws SQLException {
        throw new NotImplementedException();
    }

    public boolean rowDeleted() throws SQLException {
        checkValidRow();
        checkCursorValid();
        return currentRow.isDelete();
    }

    public boolean rowInserted() throws SQLException {

        /**
         * FIXME: Determin the currentRow if have had a insertion 1. Need
         * traverse the rows and find if the data hava been added
         */
        return currentRow.isInsert();
    }

    public boolean rowUpdated() throws SQLException {

        boolean sign = false;
        for (int i = 0; i < meta.getColumnCount(); ++i) {
            sign = currentRow.getUpdateMask(i) | sign;
        }
        return sign;
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
        currentRow.updateObject(columnIndex, x);
    }

    public void updateInt(String columnName, int x) throws SQLException {
        updateInt(getIndexByName(columnName), x);
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
                || (getConcurrency() == (ResultSet.CONCUR_READ_ONLY))) {
            // TODO add error messages
            throw new SQLException();
        }
        rows.set(currentRowIndex, currentRow);
        notifyRowChanged();
    }

    public void updateShort(int columnIndex, short x) throws SQLException {
        throw new NotImplementedException();
    }

    public void updateShort(String columnName, short x) throws SQLException {
        throw new NotImplementedException();
    }

    public void updateString(int columnIndex, String x) throws SQLException {
        currentRow.updateObject(columnIndex, x);
    }

    public void updateString(String columnName, String x) throws SQLException {
        updateString(getIndexByName(columnName), x);
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
        // return (currentColumn instanceof Types(Types.NULL));
        throw new NotImplementedException();
    }

    public void execute() throws SQLException {
        execute(getConnection());
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(getUrl());
    }

    CachedRow getCurrentRow() {
        return currentRow;
    }

    @Override
    public void setCommand(String cmd) throws SQLException {
        initParams();
        super.setCommand(cmd);
    }

}
