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
import java.lang.reflect.Field;
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
import java.sql.Struct;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.sql.RowSet;
import javax.sql.RowSetEvent;
import javax.sql.RowSetInternal;
import javax.sql.RowSetListener;
import javax.sql.RowSetMetaData;
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

    private SQLWarning sqlwarn;

    private Class[] columnTypes;

    private static Map<Integer, Class> TYPE_MAPPING = initialTypeMapping();

    public static final String PROVIDER_ID = "Apache Harmony HYOptimisticProvider"; //$NON-NLS-1$

    public CachedRowSetImpl(String providerID) throws SyncFactoryException {
        syncProvider = SyncFactory.getInstance(providerID);
        initialProperties();
    }

    private static Map<Integer, Class> initialTypeMapping() {
        HashMap<Integer, Class> map = new HashMap<Integer, Class>();
        map.put(Integer.valueOf(Types.ARRAY), Array.class);
        map.put(Integer.valueOf(Types.BIGINT), Long.class);
        map.put(Integer.valueOf(Types.BINARY), byte[].class);
        map.put(Integer.valueOf(Types.BIT), Boolean.class);
        map.put(Integer.valueOf(Types.BLOB), Blob.class);
        map.put(Integer.valueOf(Types.BOOLEAN), Boolean.class);
        map.put(Integer.valueOf(Types.CHAR), String.class);
        map.put(Integer.valueOf(Types.CLOB), Clob.class);
        map.put(Integer.valueOf(Types.DATE), Date.class);
        map.put(Integer.valueOf(Types.DECIMAL), BigDecimal.class);
        map.put(Integer.valueOf(Types.DOUBLE), Double.class);
        map.put(Integer.valueOf(Types.FLOAT), Double.class);
        map.put(Integer.valueOf(Types.INTEGER), Integer.class);
        map.put(Integer.valueOf(Types.LONGVARBINARY), byte[].class);
        map.put(Integer.valueOf(Types.LONGVARCHAR), String.class);
        map.put(Integer.valueOf(Types.NUMERIC), BigDecimal.class);
        map.put(Integer.valueOf(Types.REAL), Float.class);
        map.put(Integer.valueOf(Types.REF), Ref.class);
        map.put(Integer.valueOf(Types.SMALLINT), Short.class);
        map.put(Integer.valueOf(Types.STRUCT), Struct.class);
        map.put(Integer.valueOf(Types.TIME), Time.class);
        map.put(Integer.valueOf(Types.TIMESTAMP), Timestamp.class);
        map.put(Integer.valueOf(Types.TINYINT), Byte.class);
        map.put(Integer.valueOf(Types.VARBINARY), byte[].class);
        map.put(Integer.valueOf(Types.VARCHAR), String.class);

        return map;
    }

    private void initialProperties() {
        try {
            setEscapeProcessing(true);
            setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            setConcurrency(ResultSet.CONCUR_UPDATABLE);
            setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
            setMaxRows(0);
            setQueryTimeout(0);
            setShowDeleted(false);
            setUsername(null);
            setPassword(null);
            setMaxFieldSize(0);
            setTypeMap(null);
            setFetchSize(0);
        } catch (SQLException e) {
            // ignore, never reached
        }

    }

    public CachedRowSetImpl() throws SyncFactoryException {
        this(PROVIDER_ID);
    }

    public void setRows(ArrayList<CachedRow> data, int cloumnCount) {
        rows = data;
        this.columnCount = cloumnCount;
    }

    public void acceptChanges() throws SyncProviderException {
        if (currentRow == insertRow && currentRow != null) {
            // TODO add error messages
            throw new SyncProviderException();
        }

        try {
            acceptChanges(getConnection());
        } catch (SQLException e) {
            SyncProviderException ex = new SyncProviderException();
            ex.initCause(e);
            throw ex;
        }
    }

    public void acceptChanges(Connection con) throws SyncProviderException {
        if (currentRow == insertRow && currentRow != null) {
            // TODO add error messages
            throw new SyncProviderException();
        }

        try {
            CachedRowSetWriter rowSetWriter = (CachedRowSetWriter) syncProvider
                    .getRowSetWriter();
            rowSetWriter.setConnection(con);
            int beforeWriteIndex = currentRowIndex;
            rowSetWriter.writeData(this);
            absolute(beforeWriteIndex);

            boolean isChanged = false;
            /*
             * FIXME: if no conflicts happen when writeData, then call
             * setOriginalRow()
             */
            for (int i = rows.size() - 1; i >= 0; i--) {
                currentRow = rows.get(i);
                if (rowDeleted()) {
                    isChanged = true;
                    setOriginalRow();
                } else if (rowInserted() || rowUpdated()) {
                    isChanged = true;
                    setOriginalRow();
                }
            }
            // Set originalResultSet
            if (isChanged) {
                try {
                    ArrayList<CachedRow> nowRows = new ArrayList<CachedRow>();
                    for (int i = 0; i < rows.size(); i++) {
                        nowRows.add(rows.get(i).createClone());
                        nowRows.get(i).restoreOriginal();
                    }
                    originalResultSet.setRows(nowRows, columnCount);
                } catch (CloneNotSupportedException cloneE) {
                    // TODO how to deal with the CloneNotSupportedException
                    throw new SyncProviderException(cloneE.getMessage());
                }
            }

            if (currentRowIndex > rows.size()) {
                afterLast();
            } else if (currentRowIndex <= 0) {
                beforeFirst();
            } else {
                absolute(currentRowIndex);
            }

            notifyRowSetChanged();

        } catch (SyncProviderException e) {
            throw e;
        } catch (SQLException e) {
            SyncProviderException ex = new SyncProviderException();
            ex.initCause(e);
            throw ex;
        }
    }

    public boolean columnUpdated(int idx) throws SQLException {
        if (currentRow == null || idx > meta.getColumnCount() || idx <= 0
                || currentRow == insertRow) {
            // rowset.0 = Not a valid position
            throw new SQLException(Messages.getString("rowset.0")); //$NON-NLS-1$
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
        throw new SQLException(Messages.getString("rowset.1")); //$NON-NLS-1$
    }

    public void commit() throws SQLException {
        getConnection().commit();
    }

    public CachedRowSet createCopy() throws SQLException {
        CachedRowSetImpl output;
        try {
            /*
             * the attribute of BaseRowSet which are needed to deep copy
             */
            // BaseRowSet.params <Hashtable>
            Object[] paramsObjArray = super.getParams();
            Hashtable<Object, Object> paramsHashtable = new Hashtable<Object, Object>();
            for (int i = 0; i < paramsObjArray.length; i++) {
                paramsHashtable.put(Integer.valueOf(i), paramsObjArray[i]);
            }
            // BaseRowSet.listeners <Vector>
            Vector<RowSetListener> listeners = new Vector<RowSetListener>();

            /*
             * deep copy BaseRowSet
             */
            output = (CachedRowSetImpl) super.clone();
            // BaseRowSet.params
            Field paramsField = output.getClass().getSuperclass()
                    .getDeclaredField("params");
            paramsField.setAccessible(true);
            paramsField.set(output, paramsHashtable);
            // BaseRowSet.listeners
            Field listenersField = output.getClass().getSuperclass()
                    .getDeclaredField("listeners");
            listenersField.setAccessible(true);
            listenersField.set(output, listeners);
            // BaseRowSet.map
            if (super.getTypeMap() != null) {
                Map<String, Class<?>> originalTypeMap = super.getTypeMap();
                Map<String, Class<?>> copyTypeMap = new HashMap<String, Class<?>>();
                copyTypeMap.putAll(originalTypeMap);
                output.setTypeMap(copyTypeMap);
            }

            /*
             * deep copy CachedRowSetImpl
             */
            // CachedRowSetImpl.rows <ArrayList>
            ArrayList<CachedRow> copyRows = new ArrayList<CachedRow>();
            for (int i = 0; i < rows.size(); i++) {
                copyRows.add(rows.get(i).createClone());
            }
            output.setRows(copyRows, columnCount);
            // CachedRowSetImpl.meta <RowSetMetaData>
            output.setMetaData(copyMetaData(getMetaData()));
            // set currentRow
            if ((currentRowIndex > 0) && (currentRowIndex <= rows.size())) {
                output.absolute(currentRowIndex);
            }
            // others
            if (getKeyColumns() != null) {
                output.setKeyColumns(getKeyColumns().clone());
            }
            // CachedRowSetImpl.originalResultSet
            CachedRowSetImpl copyOriginalRs = new CachedRowSetImpl();
            copyOriginalRs.populate(getOriginal());
            output.originalResultSet = copyOriginalRs;

            /*
             * TODO uncomment after getMatchColumnIndexes and
             * getMatchColumnNames implemented
             */
            // if (getMatchColumnIndexes() != null) {
            // output.setMatchColumn(getMatchColumnIndexes().clone());
            // }
            // if (getMatchColumnNames() != null) {
            // output.setMatchColumn(getMatchColumnNames().clone());
            // }
            output.setSyncProvider(getSyncProvider().getProviderID());
            if (insertRow != null) {
                output.insertRow = insertRow.createClone();
            }

            return output;
        } catch (CloneNotSupportedException e) {
            // TODO add error message
            throw new SQLException();
        } catch (NoSuchFieldException e) {
            // TODO add error message
            throw new SQLException();
        } catch (IllegalAccessException e) {
            // TODO add error message
            throw new SQLException();
        }
    }

    public CachedRowSet createCopyNoConstraints() throws SQLException {
        CachedRowSetImpl output = (CachedRowSetImpl) createCopy();
        output.initialProperties();
        return output;
    }

    public CachedRowSet createCopySchema() throws SQLException {
        CachedRowSetImpl output = (CachedRowSetImpl) createCopy();

        // clean up rows data
        output.currentRow = null;
        output.currentRowIndex = 0;
        output.insertRow = null;
        output.pageNumber = 1;
        output.rememberedCursorPosition = 0;
        output.rows = new ArrayList<CachedRow>();
        output.sqlwarn = null;

        return output;
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
            doPopulate(ps.getResultSet(), true);
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

        CachedRowSetImpl originalRowRset = new CachedRowSetImpl();
        ArrayList<CachedRow> data = new ArrayList<CachedRow>();
        CachedRow originalCachedRow = rows.get(getRow() - 1).getOriginal();
        data.add(originalCachedRow);
        originalRowRset.setRows(data, columnCount);
        originalRowRset.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
        originalRowRset.setConcurrency(ResultSet.CONCUR_UPDATABLE);
        return originalRowRset;
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

    public void populate(ResultSet rs) throws SQLException {
        doPopulate(rs, false);
    }

    public void populate(ResultSet rs, int startRow) throws SQLException {
        if (startRow == 1) {
            rs.beforeFirst();
        } else if (startRow <= 0 || !rs.absolute(startRow - 1)) {
            // rowset.7=Not a valid cursor
            throw new SQLException(Messages.getString("rowset.7")); //$NON-NLS-1$
        }

        doPopulate(rs, true);
    }

    private void doPopulate(ResultSet rs, boolean isPaging) throws SQLException {
        meta = copyMetaData(rs.getMetaData());

        columnCount = meta.getColumnCount();
        // initial columnTypes
        columnTypes = new Class[columnCount];
        for (int i = 1; i <= columnTypes.length; ++i) {
            columnTypes[i - 1] = TYPE_MAPPING.get(Integer.valueOf(meta
                    .getColumnType(i)));
        }

        /*
         * this method not support paging, so before readData set pageSize and
         * maxRowsto 0 and restore previous values after readData
         */
        if (!isPaging) {
            int prePageSize = getPageSize();
            setPageSize(0);
            int preMaxRows = getMaxRows();
            setMaxRows(0);
            // FIXME use SyncProvider to get RowSetReader
            new CachedRowSetReader(rs).readData(this);
            setPageSize(prePageSize);
            setMaxRows(preMaxRows);
        } else {
            // FIXME use SyncProvider to get RowSetReader
            new CachedRowSetReader(rs).readData(this);
        }

        setTableName(rs.getMetaData().getTableName(1));

        originalResultSet = new CachedRowSetImpl();
        // FIXME use SyncProvider to get RowSetReader
        new CachedRowSetReader(this).readData(originalResultSet);
        originalResultSet.setMetaData((RowSetMetaData) (getMetaData()));

        // recovery the states
        beforeFirst();
    }

    // deep copy of ResultSetMetaData
    private RowSetMetaData copyMetaData(ResultSetMetaData metaData)
            throws SQLException {
        RowSetMetaDataImpl rowSetMetaData = new RowSetMetaDataImpl();
        rowSetMetaData.setColumnCount(metaData.getColumnCount());
        for (int columnIndex = 1; columnIndex <= metaData.getColumnCount(); columnIndex++) {
            rowSetMetaData.setAutoIncrement(columnIndex, metaData
                    .isAutoIncrement(columnIndex));
            rowSetMetaData.setCaseSensitive(columnIndex, metaData
                    .isCaseSensitive(columnIndex));
            rowSetMetaData.setCatalogName(columnIndex, metaData
                    .getCatalogName(columnIndex));
            rowSetMetaData.setColumnDisplaySize(columnIndex, metaData
                    .getColumnDisplaySize(columnIndex));
            rowSetMetaData.setColumnLabel(columnIndex, metaData
                    .getColumnLabel(columnIndex));
            rowSetMetaData.setColumnName(columnIndex, metaData
                    .getColumnName(columnIndex));
            rowSetMetaData.setColumnType(columnIndex, metaData
                    .getColumnType(columnIndex));
            rowSetMetaData.setColumnTypeName(columnIndex, metaData
                    .getColumnTypeName(columnIndex));
            rowSetMetaData.setCurrency(columnIndex, metaData
                    .isCurrency(columnIndex));
            rowSetMetaData.setNullable(columnIndex, metaData
                    .isNullable(columnIndex));
            rowSetMetaData.setPrecision(columnIndex, metaData
                    .getPrecision(columnIndex));
            rowSetMetaData
                    .setScale(columnIndex, metaData.getScale(columnIndex));
            rowSetMetaData.setSchemaName(columnIndex, metaData
                    .getSchemaName(columnIndex));
            rowSetMetaData.setSearchable(columnIndex, metaData
                    .isSearchable(columnIndex));
            rowSetMetaData.setSigned(columnIndex, metaData
                    .isSigned(columnIndex));
            rowSetMetaData.setTableName(columnIndex, metaData
                    .getTableName(columnIndex));
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

        if (rowDeleted()) {
            rows.remove(currentRow);
        } else if (rowUpdated() || rowInserted()) {
            currentRow.setOriginal();
        }
    }

    public void setPageSize(int size) throws SQLException {
        if (size < 0) {
            // rowset.2=Negative page size
            throw new SQLException(Messages.getString("rowset.2")); //$NON-NLS-1$
        }
        if ((getMaxRows() != 0) && (getMaxRows() < size)) {
            // rowset.9=PageSize can not larger than MaxRows
            throw new SQLException(Messages.getString("rowset.9")); //$NON-NLS-1$
        }
        pageSize = size;
    }

    public void setSyncProvider(String provider) throws SQLException {
        syncProvider = SyncFactory.getInstance(provider);
    }

    public void setTableName(String tabName) throws SQLException {
        if (tabName == null) {
            // rowset.3=Table name should not be null
            throw new SQLException("rowset.3"); //$NON-NLS-1$
        }
        tableName = tabName;
    }

    public int size() {
        if (rows == null) {
            return 0;
        }
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
        if (currentRow == null) {
            // TODO add error message
            throw new SQLException();
        }

        if (currentRow == insertRow) {
            currentRow = new CachedRow(new Object[columnCount]);
        } else if (rowUpdated()) {
            currentRow.restoreOriginal();
        }
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
        return doAbsolute(row, true);
    }

    /**
     * internal implement of absolute
     * 
     * @param row
     *            index of row cursor to move
     * @param checkType
     *            whether to check property ResultSet.TYPE_FORWARD_ONLY
     * @return whether the cursor is on result set
     * @throws SQLException
     */
    private boolean doAbsolute(int row, boolean checkType) throws SQLException {
        // FIXME need to consider getShowDeleted()
        if (rows == null || rows.size() == 0) {
            return false;
        }

        if (checkType && getType() == ResultSet.TYPE_FORWARD_ONLY) {
            // rowset.8=The Result Set Type is TYPE_FORWARD_ONLY
            throw new SQLException(Messages.getString("rowset.8")); //$NON-NLS-1$
        }

        if (row < 0) {
            row = rows.size() + row + 1;
        }

        if (row <= 0) {
            currentRowIndex = 0;
            currentRow = null;
            return false;
        }

        if (row > rows.size()) {
            currentRowIndex = rows.size() + 1;
            currentRow = null;
            return false;
        }

        currentRowIndex = row;
        currentRow = rows.get(currentRowIndex - 1);
        return true;
    }

    public void afterLast() throws SQLException {
        if (rows == null) {
            return;
        }

        doAbsolute(rows.size() + 1, true);
    }

    public void beforeFirst() throws SQLException {
        doAbsolute(0, true);
    }

    public void cancelRowUpdates() throws SQLException {
        if (currentRow == null || currentRow == insertRow) {
            // TODO add error message
            throw new SQLException();
        }

        if (rowUpdated()) {
            currentRow.restoreOriginal();
        }
    }

    public void clearWarnings() throws SQLException {
        throw new NotImplementedException();
    }

    public void close() throws SQLException {

        // TODO need more concerns!
        if (rows != null) {
            rows.clear();
        }
        currentRowIndex = 0;
        currentRow = null;
        meta = null;

    }

    public void deleteRow() throws SQLException {
        checkValidRow();
        if (currentRow == insertRow) {
            // TODO add error message
            throw new SQLException();
        }
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
        return doAbsolute(1, true);
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
        // TODO re-implement it
        return (BigDecimal) getObject(columnIndex);
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
        // TODO re-implement it
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
        // FIXME need more tests
        if (currentRow == null) {
            return 0;
        }

        return currentRowIndex;
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

    private void checkColumnValid(int columnIndex) throws SQLException {
        if (columnIndex <= 0 || columnIndex > meta.getColumnCount()) {
            // sql.27=Invalid column index :{0}
            throw new SQLException(Messages.getString("sql.27", Integer //$NON-NLS-1$
                    .valueOf(columnIndex)));
        }
    }

    private void checkCursorValid() throws SQLException {
        if ((currentRowIndex <= 0) || (currentRowIndex > rows.size())) {
            // rowset.7=Not a valid cursor
            throw new SQLException(Messages.getString("rowset.7")); //$NON-NLS-1$
        }
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
            throw new SQLException(Messages.getString("rowset.4")); //$NON-NLS-1$
        }
        insertRow.setInsert();
        rows.add(insertRow);
        insertRow = null;
    }

    public boolean isAfterLast() throws SQLException {
        if (rows == null || rows.size() == 0) {
            return false;
        }

        return currentRowIndex > rows.size();
    }

    public boolean isBeforeFirst() throws SQLException {
        if (rows == null || rows.size() == 0) {
            return false;
        }

        return currentRowIndex == 0;
    }

    public boolean isFirst() throws SQLException {
        return currentRowIndex == 1;
    }

    public boolean isLast() throws SQLException {
        if (rows == null || rows.size() == 0) {
            return false;
        }

        return currentRowIndex == rows.size();
    }

    public boolean last() throws SQLException {
        return doAbsolute(-1, true);
    }

    public void moveToCurrentRow() throws SQLException {
        if (rememberedCursorPosition != -1) {
            currentRowIndex = rememberedCursorPosition;
            if (currentRowIndex >= 1 && currentRowIndex <= size()) {
                currentRow = rows.get(currentRowIndex - 1);
            } else {
                currentRow = null;
            }
            rememberedCursorPosition = -1;
        }
    }

    public void moveToInsertRow() throws SQLException {
        insertRow = new CachedRow(new Object[columnCount]);
        currentRow = insertRow;
        rememberedCursorPosition = currentRowIndex;
        currentRowIndex = -1;
    }

    public boolean next() throws SQLException {
        /*
         * spec next() is identical with relative(1), but they can't:
         * 
         * next() doesn't check TYPE_FORWARD_ONLY property, relative(1) does.
         */
        return doAbsolute(currentRowIndex + 1, false);
    }

    public boolean previous() throws SQLException {
        return doAbsolute(currentRowIndex - 1, true);
    }

    public void refreshRow() throws SQLException {
        throw new NotImplementedException();
    }

    public boolean relative(int moveRows) throws SQLException {
        if (currentRow == null) {
            // TODO add error message
            throw new SQLException();
        }

        int index = getRow() + moveRows;
        if (index <= 0) {
            beforeFirst();
            return false;
        }

        if (index > rows.size()) {
            afterLast();
            return false;
        }

        return doAbsolute(index, true);
    }

    public boolean rowDeleted() throws SQLException {
        checkValidRow();
        return currentRow.isDelete();
    }

    public boolean rowInserted() throws SQLException {
        if (currentRow == null || currentRow == insertRow) {
            // TODO add error message
            throw new SQLException();
        }
        return currentRow.isInsert();
    }

    public boolean rowUpdated() throws SQLException {

        if (currentRow == null || currentRow == insertRow) {
            // TODO add error message
            throw new SQLException();
        }

        if (!currentRow.isUpdate()) {
            return false;
        }

        boolean sign = false;
        for (int i = 0; i < meta.getColumnCount(); ++i) {
            sign = currentRow.getUpdateMask(i) | sign;
        }
        return sign;
    }

    public void updateArray(int columnIndex, Array x) throws SQLException {
        updateByType(columnIndex, x);
    }

    public void updateArray(String columnName, Array x) throws SQLException {
        updateArray(getIndexByName(columnName), x);
    }

    public void updateAsciiStream(int columnIndex, InputStream x, int length)
            throws SQLException {
        throw new NotImplementedException();
    }

    public void updateAsciiStream(String columnName, InputStream x, int length)
            throws SQLException {
        updateAsciiStream(getIndexByName(columnName), x, length);
    }

    public void updateBigDecimal(int columnIndex, BigDecimal x)
            throws SQLException {
        if (x == null) {
            throw new NullPointerException();
        }

        updateByType(columnIndex, x);
    }

    public void updateBigDecimal(String columnName, BigDecimal x)
            throws SQLException {
        updateBigDecimal(getIndexByName(columnName), x);
    }

    public void updateBinaryStream(int columnIndex, InputStream x, int length)
            throws SQLException {
        throw new NotImplementedException();
    }

    public void updateBinaryStream(String columnName, InputStream x, int length)
            throws SQLException {
        updateBinaryStream(getIndexByName(columnName), x, length);
    }

    public void updateBlob(int columnIndex, Blob x) throws SQLException {
        updateByType(columnIndex, x);
    }

    public void updateBlob(String columnName, Blob x) throws SQLException {
        updateBlob(getIndexByName(columnName), x);
    }

    public void updateBoolean(int columnIndex, boolean x) throws SQLException {
        updateByType(columnIndex, Boolean.valueOf(x));
    }

    public void updateBoolean(String columnName, boolean x) throws SQLException {
        updateBoolean(getIndexByName(columnName), x);
    }

    public void updateByte(int columnIndex, byte x) throws SQLException {
        updateByType(columnIndex, Byte.valueOf(x));
    }

    public void updateByte(String columnName, byte x) throws SQLException {
        updateByte(getIndexByName(columnName), x);
    }

    public void updateBytes(int columnIndex, byte[] x) throws SQLException {
        updateByType(columnIndex, x);
    }

    public void updateBytes(String columnName, byte[] x) throws SQLException {
        updateBytes(getIndexByName(columnName), x);
    }

    public void updateCharacterStream(int columnIndex, Reader x, int length)
            throws SQLException {
        throw new NotImplementedException();
    }

    public void updateCharacterStream(String columnName, Reader reader,
            int length) throws SQLException {
        updateCharacterStream(getIndexByName(columnName), reader, length);
    }

    public void updateClob(int columnIndex, Clob x) throws SQLException {
        updateByType(columnIndex, x);
    }

    public void updateClob(String columnName, Clob x) throws SQLException {
        updateClob(getIndexByName(columnName), x);
    }

    public void updateDate(int columnIndex, Date x) throws SQLException {
        updateByType(columnIndex, x);
    }

    public void updateDate(String columnName, Date x) throws SQLException {
        updateDate(getIndexByName(columnName), x);
    }

    public void updateDouble(int columnIndex, double x) throws SQLException {
        updateByType(columnIndex, Double.valueOf(x));
    }

    public void updateDouble(String columnName, double x) throws SQLException {
        updateDouble(getIndexByName(columnName), x);
    }

    public void updateFloat(int columnIndex, float x) throws SQLException {
        updateByType(columnIndex, Float.valueOf(x));
    }

    public void updateFloat(String columnName, float x) throws SQLException {
        updateFloat(getIndexByName(columnName), x);
    }

    public void updateInt(int columnIndex, int x) throws SQLException {
        updateByType(columnIndex, Integer.valueOf(x));
    }

    public void updateInt(String columnName, int x) throws SQLException {
        updateInt(getIndexByName(columnName), x);
    }

    public void updateLong(int columnIndex, long x) throws SQLException {
        updateByType(columnIndex, Long.valueOf(x));
    }

    public void updateLong(String columnName, long x) throws SQLException {
        updateLong(getIndexByName(columnName), x);
    }

    public void updateNull(int columnIndex) throws SQLException {
        checkCursorValid();
        checkColumnValid(columnIndex);
        currentRow.updateObject(columnIndex, null);
    }

    public void updateNull(String columnName) throws SQLException {
        updateNull(getIndexByName(columnName));
    }

    /**
     * note check type compatibility
     */
    public void updateObject(int columnIndex, Object x) throws SQLException {
        checkValidRow();
        checkColumnValid(columnIndex);
        currentRow.updateObject(columnIndex, x);
    }

    public void updateObject(int columnIndex, Object x, int scale)
            throws SQLException {
        checkValidRow();
        checkColumnValid(columnIndex);
        Class type = columnTypes[columnIndex - 1];
        // ava.sql.Types.DECIMA or java.sql.Types.NUMERIC types
        if (type.equals(BigDecimal.class)) {
            /*
             * TODO ri doesn't check type of x and only support BigDecimal,
             * should we follow ri here? If not, uncomment below fragment of
             * code
             */
            // if (x instanceof BigDecimal) {
            x = ((BigDecimal) x).setScale(scale);
            // } else if (x instanceof Double) {
            // x = new BigDecimal(((Double) x).doubleValue());
            // x = ((BigDecimal) x).setScale(scale);
            // } else if (x instanceof Float) {
            // x = new BigDecimal(((Float) x).doubleValue());
            // x = ((BigDecimal) x).setScale(scale);
            // }
        }

        currentRow.updateObject(columnIndex, x);
    }

    public void updateObject(String columnName, Object x) throws SQLException {
        updateObject(getIndexByName(columnName), x);
    }

    public void updateObject(String columnName, Object x, int scale)
            throws SQLException {
        updateObject(getIndexByName(columnName), x, scale);
    }

    public void updateRef(int columnIndex, Ref x) throws SQLException {
        updateByType(columnIndex, x);
    }

    public void updateRef(String columnName, Ref x) throws SQLException {
        updateRef(getIndexByName(columnName), x);
    }

    public void updateRow() throws SQLException {
        if ((currentRow == insertRow)
                || (getConcurrency() == (ResultSet.CONCUR_READ_ONLY))) {
            // TODO add error messages
            throw new SQLException();
        }
        currentRow.setUpdate();
        notifyRowChanged();
    }

    public void updateShort(int columnIndex, short x) throws SQLException {
        updateByType(columnIndex, Short.valueOf(x));
    }

    public void updateShort(String columnName, short x) throws SQLException {
        updateShort(getIndexByName(columnName), x);
    }

    public void updateString(int columnIndex, String x) throws SQLException {
        updateByType(columnIndex, x);
    }

    /**
     * Check type compatibility and update value
     * 
     * @param columnIndex
     * @param value
     * @throws SQLException
     */
    private void updateByType(int columnIndex, Object value)
            throws SQLException {
        checkValidRow();
        checkColumnValid(columnIndex);
        currentRow.updateObject(columnIndex, convertUpdateValue(columnIndex,
                value));
    }

    /**
     * Convert <code>value</code> to the JDBC type of the
     * <code>columnIndex</code>. The columnIndex is not checked in this
     * method, so caller must be sure the <code>columnIndex</code> is valid,
     * or invoke <code>checkColumnValid</code> before invoke this method.
     * 
     * TODO any better ways to do this?
     * 
     * @param columnIndex
     *            index of column to be updated
     * @param value
     *            the new value to be updated
     */
    private Object convertUpdateValue(int columnIndex, Object value)
            throws SQLException {

        if (value == null) {
            return value;
        }

        Class type = columnTypes[columnIndex - 1];

        /*
         * TODO if type == null, the type mapping is not supported by Harmony
         * now, leave this type check to JDBC driver
         */

        if (type == null || type.isInstance(value)) {
            return value;
        }

        if (type.equals(Integer.class)) {
            if (value instanceof Integer || value instanceof Short
                    || value instanceof Byte) {
                return value;
            }

            if (value instanceof Long) {
                long l = ((Long) value).longValue();
                if (l >= Integer.MIN_VALUE && l <= Integer.MAX_VALUE) {
                    return (int) l;
                }
            }

            if (value instanceof BigDecimal) {
                BigDecimal bigDecimal = (BigDecimal) value;
                try {
                    return bigDecimal.intValueExact();

                } catch (ArithmeticException e) {
                    // TODO load from resource file
                    throw new SQLException("Data Type Mismatch"); //$NON-NLS-1$
                }
            }

            if (value instanceof String) {
                return value;
            }
        }

        if (type.equals(Short.class)) {
            if (value instanceof Short || value instanceof Byte) {
                return value;
            }
            if (value instanceof Long) {
                long l = ((Long) value).longValue();
                if (l >= Short.MIN_VALUE && l <= Short.MAX_VALUE) {
                    return (short) l;
                }
            }
            if (value instanceof Integer) {
                int i = ((Integer) value).intValue();
                if (i >= Short.MIN_VALUE && i <= Short.MAX_VALUE) {
                    return (short) i;
                }
            }
            if (value instanceof BigDecimal) {
                BigDecimal bigDecimal = (BigDecimal) value;
                try {
                    return bigDecimal.intValueExact();

                } catch (ArithmeticException e) {
                    // TODO load from resource file
                    throw new SQLException("Data Type Mismatch"); //$NON-NLS-1$
                }
            }
            if (value instanceof String) {
                return value;
            }
        }

        if (type.equals(Byte.class)) {
            if (value instanceof Byte) {
                return value;
            }
            if (value instanceof Long) {
                long l = ((Long) value).longValue();
                if (l >= Byte.MIN_VALUE && l <= Byte.MAX_VALUE) {
                    return (byte) l;
                }
            }
            if (value instanceof Integer) {
                int i = ((Integer) value).intValue();
                if (i >= Byte.MIN_VALUE && i <= Byte.MAX_VALUE) {
                    return (byte) i;
                }
            }
            if (value instanceof Short) {
                int i = ((Short) value).shortValue();
                if (i >= Byte.MIN_VALUE && i <= Byte.MAX_VALUE) {
                    return (byte) i;
                }
            }
            if (value instanceof BigDecimal) {
                BigDecimal bigDecimal = (BigDecimal) value;
                try {
                    return bigDecimal.byteValueExact();

                } catch (ArithmeticException e) {
                    // TODO load from resource file
                    throw new SQLException("Data Type Mismatch"); //$NON-NLS-1$
                }
            }
            if (value instanceof String) {
                return value;
            }
        }

        if (type.equals(Long.class)) {
            if (value instanceof Integer || value instanceof Short
                    || value instanceof Byte || value instanceof Long) {
                return value;
            }
            if (value instanceof BigDecimal) {
                BigDecimal bigDecimal = (BigDecimal) value;
                try {
                    return bigDecimal.longValueExact();

                } catch (ArithmeticException e) {
                    // rowset.10=Data Type Mismatch
                    throw new SQLException(Messages.getString("rowset.10")); //$NON-NLS-1$
                }
            }
            if (value instanceof String) {
                return value;
            }
        }

        if (type.equals(Float.class) || type.equals(Double.class)) {
            if (value instanceof Float || value instanceof Double
                    || value instanceof BigDecimal) {
                return value;
            }
            if (value instanceof Number) {
                return ((Number) value).longValue();
            }
            if (value instanceof String) {
                return value;
            }
        }

        if (type.equals(BigDecimal.class)) {
            return value;
        }

        // rowset.10=Data Type Mismatch
        throw new SQLException(Messages.getString("rowset.10")); //$NON-NLS-1$
    }

    public void updateString(String columnName, String x) throws SQLException {
        updateString(getIndexByName(columnName), x);
    }

    public void updateTime(int columnIndex, Time x) throws SQLException {
        updateByType(columnIndex, x);
    }

    public void updateTime(String columnName, Time x) throws SQLException {
        updateTime(getIndexByName(columnName), x);
    }

    public void updateTimestamp(int columnIndex, Timestamp x)
            throws SQLException {
        updateByType(columnIndex, x);
    }

    public void updateTimestamp(String columnName, Timestamp x)
            throws SQLException {
        updateTimestamp(getIndexByName(columnName), x);
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
