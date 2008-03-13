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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
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

    /**
     * current row index include deleted rows, start from 1
     */
    private int currentRowIndex;

    // the number of the rows in one "page"
    private int pageSize;

    /**
     * used for paging, record row index for next page in ResultSet, start from
     * 1
     */
    private int nextPageRowIndex = -1;

    /**
     * used for paging, record row index for previous page in ResultSet, start
     * from 1
     */
    private int previousPageRowIndex = -1;

    /**
     * cached ResultSet for paging in memory
     */
    private CachedRowSet cachedResultSet = null;

    private String tableName;

    private int rememberedCursorPosition;

    private CachedRow insertRow;

    private boolean isCursorOnInsert;

    // TODO remember to evalute it in CachedRowSetReader
    private int[] keyCols;

    private int columnCount;

    private int deletedRowCount;

    private SyncProvider syncProvider;

    private CachedRowSetImpl originalResultSet;

    private SQLWarning sqlwarn;

    private Class[] columnTypes;

    private String[] matchColumnNames;

    private int[] matchColumnIndexes;

    private String cursorName;

    private boolean isLastColNull;

    private Connection conn;

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
        if (isCursorOnInsert) {
            // rowset.11=Illegal operation on an insert row
            throw new SyncProviderException(Messages.getString("rowset.11"));
        }

        Connection currentConn = null;
        try {
            currentConn = getConnection();
            acceptChanges(currentConn);
        } catch (SQLException e) {
            try {
                currentConn.rollback();
            } catch (SQLException ex) {
                // ignore
            }
            SyncProviderException ex = new SyncProviderException();
            ex.initCause(e);
            throw ex;
        } finally {
            conn = null;
            if (currentConn != null) {
                try {
                    currentConn.commit();
                    currentConn.close();
                } catch (SQLException ex) {
                    SyncProviderException spe = new SyncProviderException();
                    spe.initCause(ex);
                    throw spe;
                }
            }
        }
    }

    public void acceptChanges(Connection con) throws SyncProviderException {
        if (isCursorOnInsert) {
            // rowset.11=Illegal operation on an insert row
            throw new SyncProviderException(Messages.getString("rowset.11")); //$NON-NLS-1$
        }

        conn = con;
        try {
            conn.setAutoCommit(false);
            CachedRowSetWriter rowSetWriter = (CachedRowSetWriter) syncProvider
                    .getRowSetWriter();
            rowSetWriter.setConnection(con);
            int beforeWriteIndex = currentRowIndex;
            boolean isShowDeleted = getShowDeleted();
            // writer use next navigate rowset, so must make all rows visible
            setShowDeleted(true);

            rowSetWriter.writeData(this);

            // must reset curosr before reset showDeleted
            absolute(beforeWriteIndex);
            setShowDeleted(isShowDeleted);

            // record to the next visible row index
            int index = getRow();
            if (index == 0) {
                next();
                index = getRow();
                if (index == 0) {
                    index = rows.size() + 1;
                }
            }

            boolean isChanged = false;
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
                    throw new SyncProviderException(cloneE.getMessage());
                }
            }

            deletedRowCount = 0;

            // move cursor
            if (index > rows.size()) {
                afterLast();
            } else if (index <= 0) {
                beforeFirst();
            } else {
                absolute(index);
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
        if (meta == null || columnName == null) {
            throw new NullPointerException();
        }

        for (int i = 1; i <= meta.getColumnCount(); i++) {
            if (columnName.equalsIgnoreCase(meta.getColumnName(i))) {
                return i;
            }
        }
        // rowset.1=Not a valid column name
        throw new SQLException(Messages.getString("rowset.1")); //$NON-NLS-1$
    }

    public void commit() throws SQLException {
        if (conn == null) {
            throw new NullPointerException();
        }
        conn.commit();
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
        output.isCursorOnInsert = false;
        output.isLastColNull = false;
        output.nextPageRowIndex = -1;
        output.rememberedCursorPosition = 0;
        output.rows = new ArrayList<CachedRow>();
        output.sqlwarn = null;
        output.deletedRowCount = 0;

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
        String localCommand = getCommand();
        if (localCommand == null || getParams() == null) {
            // rowset.16=Not a valid command
            throw new SQLException(Messages.getString("rowset.16"));
        }

        this.conn = conn;
        PreparedStatement ps = conn.prepareStatement(localCommand);
        Object[] params = getParams();
        for (int i = 0; i < params.length; i++)
            ps.setObject(i + 1, params[i]);

        if (ps.execute()) {
            doPopulate(ps.getResultSet(), true);
        }

        if (getPageSize() != 0) {
            nextPageRowIndex = rows.size() + 1;
            previousPageRowIndex = 0;
            cachedResultSet = null;
        } else {
            previousPageRowIndex = -1;
            nextPageRowIndex = -1;
            cachedResultSet = null;
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
        CachedRow originalCachedRow = currentRow.getOriginal();
        data.add(originalCachedRow);
        originalRowRset.setMetaData(meta);
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
     * TODO refactor paging
     */
    public boolean nextPage() throws SQLException {
        if (rows == null || nextPageRowIndex == -1 || getPageSize() == 0) {
            // TODO load message from resource file
            throw new SQLException(
                    "Using execute() method populate data before calling");
        }

        if (cachedResultSet == null) {
            // TODO ensure the getConnection can works!
            String localCommand = getCommand();
            if (localCommand == null || getParams() == null) {
                // TODO add error messages
                throw new SQLException();
            }

            PreparedStatement ps = getConnection().prepareStatement(
                    localCommand);
            Object[] params = getParams();
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            if (ps.execute()) {
                ResultSet rs = ps.getResultSet();
                int index = 1;
                while (rs.next() && index < nextPageRowIndex - 1) {
                    index++;
                }

                doPopulate(rs, true);

                if (rows.size() == 0) {
                    return false;
                }
                previousPageRowIndex = nextPageRowIndex - 1;
                nextPageRowIndex += rows.size();
                return true;

            }
            return false;

        }

        if (cachedResultSet.absolute(nextPageRowIndex)) {
            cachedResultSet.previous();
            doPopulate(cachedResultSet, true);

            if (rows.size() == 0) {
                return false;
            }
            previousPageRowIndex = nextPageRowIndex - 1;
            nextPageRowIndex += rows.size();
            return true;
        }
        return false;

    }

    public void populate(ResultSet rs) throws SQLException {
        doPopulate(rs, false);
        previousPageRowIndex = -1;
        nextPageRowIndex = -1;
        cachedResultSet = null;
    }

    public void populate(ResultSet rs, int startRow) throws SQLException {
        if (rs == null) {
            // TODO add error messages
            throw new SQLException();
        }

        if (startRow == 1) {
            rs.beforeFirst();
            // TODO use next move
        } else if (startRow <= 0 || !rs.absolute(startRow - 1)) {
            // rowset.7=Not a valid cursor
            throw new SQLException(Messages.getString("rowset.7")); //$NON-NLS-1$
        }

        // paging in memory
        if (getPageSize() != 0) {
            cachedResultSet = new CachedRowSetImpl();
            cachedResultSet.setMaxRows(getMaxRows());
            cachedResultSet.populate(rs, startRow);
            doPopulate(cachedResultSet, true);

            nextPageRowIndex = rows.size() + 1;
            previousPageRowIndex = 0;

        } else {
            doPopulate(rs, true);
            previousPageRowIndex = -1;
            nextPageRowIndex = -1;
            cachedResultSet = null;
        }
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
        try {
            cursorName = rs.getCursorName();
        } catch (SQLException e) {
            cursorName = null;
            // ignore
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
        if (rows == null || previousPageRowIndex == -1 || getPageSize() == 0) {
            // TODO load message from resource file
            throw new SQLException(
                    "Using execute() method populate data before calling");
        }

        if (previousPageRowIndex == 0) {
            return false;
        }

        if (cachedResultSet == null) {
            // TODO ensure the getConnection can works!
            String localCommand = getCommand();
            if (localCommand == null || getParams() == null) {
                // TODO add error messages
                throw new SQLException();
            }

            PreparedStatement ps = getConnection().prepareStatement(
                    localCommand);
            Object[] params = getParams();
            for (int i = 0; i < params.length; i++)
                ps.setObject(i + 1, params[i]);

            if (ps.execute()) {
                ResultSet rs = ps.getResultSet();
                int startIndex = previousPageRowIndex - getPageSize() + 1;

                if (startIndex <= 0) {
                    startIndex = 1;
                }

                int index = 0;
                while (index < startIndex - 1) {
                    if (!rs.next()) {
                        break;
                    }
                    index++;
                }

                int prePageSize = getPageSize();
                if (prePageSize != 0
                        && previousPageRowIndex - startIndex + 1 != prePageSize) {
                    setPageSize(previousPageRowIndex - startIndex + 1);
                }
                doPopulate(rs, true);

                setPageSize(prePageSize);

                if (rows.size() == 0) {
                    return false;
                }
                nextPageRowIndex = previousPageRowIndex + 1;
                previousPageRowIndex = startIndex - 1;
                return true;
            }

            return false;
        }

        int startIndex = previousPageRowIndex - getPageSize() + 1;

        if (startIndex <= 0) {
            startIndex = 1;
        }

        if (!cachedResultSet.absolute(startIndex)) {
            return false;
        }

        cachedResultSet.previous();

        int prePageSize = getPageSize();
        if (prePageSize != 0
                && previousPageRowIndex - startIndex + 1 != prePageSize) {
            setPageSize(previousPageRowIndex - startIndex + 1);
        }

        doPopulate(cachedResultSet, true);

        setPageSize(prePageSize);

        if (rows.size() == 0) {
            return false;
        }
        nextPageRowIndex = previousPageRowIndex + 1;
        previousPageRowIndex = startIndex - 1;
        return true;
    }

    public void release() throws SQLException {
        // TODO send a rowSetChanged event to all listeners
        rows = new ArrayList<CachedRow>();
    }

    public void restoreOriginal() throws SQLException {
        if (rows == null) {
            return;
        }

        List<CachedRow> insertedRows = new ArrayList<CachedRow>();
        for (CachedRow row : rows) {
            if (row.isInsert()) {
                insertedRows.add(row);
            } else if (row.isDelete() || row.isUpdate()) {
                row.restoreOriginal();
            }
        }
        rows.removeAll(insertedRows);
        insertRow = null;
        isCursorOnInsert = false;
        deletedRowCount = 0;

        first();

        // TODO fire rowSetChanged event
    }

    public void rollback() throws SQLException {
        if (conn == null) {
            throw new NullPointerException();
        }
        conn.rollback();
    }

    public void rollback(Savepoint s) throws SQLException {
        if (conn == null) {
            throw new NullPointerException();
        }
        conn.rollback(s);
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
            deletedRowCount--;
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
        if (rows == null) {
            // sql.38=Object is invalid
            throw new SQLException(Messages.getString("sql.38"));
        }
        List<Vector> list = new ArrayList<Vector>();
        if (rows.size() > 0) {
            Vector<Object> vector = null;
            for (int i = 0; i < rows.size(); i++) {
                CachedRow row = rows.get(i);
                vector = new Vector<Object>();
                for (int j = 1; j <= columnCount; j++) {
                    vector.add(row.getObject(j));
                }
                list.add(vector);
            }
        }
        return list;
    }

    public Collection<?> toCollection(int column) throws SQLException {
        if (rows == null) {
            // sql.38=Object is invalid
            throw new SQLException(Messages.getString("sql.38"));
        }

        if (column <= 0 || column > columnCount) {
            // sql.42=Illegal Argument
            throw new SQLException(Messages.getString("sql.42"));
        }

        Vector<Object> vector = new Vector<Object>();
        if (rows.size() > 0) {
            for (int i = 0; i < rows.size(); i++) {
                vector.add(rows.get(i).getObject(column));
            }
        }
        return vector;
    }

    public Collection<?> toCollection(String column) throws SQLException {
        return toCollection(getIndexByName(column));
    }

    public void undoDelete() throws SQLException {
        if (isAfterLast() || isBeforeFirst()) {
            // TODO add error messages
            throw new SQLException();
        }

        if (currentRow != null && !currentRow.isDelete()) {
            // TODO add error messages
            throw new SQLException();
        }

        if (currentRow != null && currentRow.isDelete()) {
            currentRow.undoDelete();
            deletedRowCount--;
        }
    }

    public void undoInsert() throws SQLException {
        // TODO notify listener
        checkValidRow();
        if (isCursorOnInsert) {
            // rowset.11=Illegal operation on an insert row
            throw new SQLException(Messages.getString("rowset.11"));
        }
        if (!rowInserted()) {
            // rowset.4=Not an insert row
            throw new SQLException(Messages.getString("rowset.4"));
        }
        rows.remove(currentRow);
        next();
    }

    public void undoUpdate() throws SQLException {
        checkValidRow();
        if (isCursorOnInsert && insertRow == null) {
            // rowset.11=Illegal operation on an insert row
            throw new SQLException(Messages.getString("rowset.11"));
        }
        if (currentRow == insertRow) {
            currentRow = new CachedRow(new Object[columnCount]);
        } else if (rowUpdated()) {
            currentRow.restoreOriginal();
        }
    }

    public int[] getMatchColumnIndexes() throws SQLException {
        if (matchColumnIndexes == null || matchColumnIndexes.length == 0
                || matchColumnIndexes[0] == -1) {
            // rowset.13=Set Match columns before getting them
            throw new SQLException(Messages.getString("rowset.13")); //$NON-NLS-1$
        }

        return matchColumnIndexes.clone();
    }

    public String[] getMatchColumnNames() throws SQLException {
        if (matchColumnNames == null || matchColumnNames.length == 0
                || matchColumnNames[0] == null) {
            // rowset.13=Set Match columns before getting them
            throw new SQLException(Messages.getString("rowset.13")); //$NON-NLS-1$
        }
        return matchColumnNames.clone();
    }

    public void setMatchColumn(int columnIdx) throws SQLException {
        if (columnIdx < 0) {
            // TODO why is 0 valid? load message from resource files
            throw new SQLException("Match columns should be greater than 0");
        }

        if (matchColumnIndexes == null) {
            /*
             * FIXME initial match column, the default length of array is 10 in
             * RI, we don't know why, just follow now
             */
            matchColumnIndexes = new int[10];
            Arrays.fill(matchColumnIndexes, -1);
        }

        matchColumnIndexes[0] = columnIdx;
    }

    public void setMatchColumn(int[] columnIdxes) throws SQLException {
        if (columnIdxes == null) {
            throw new NullPointerException();
        }

        for (int i : columnIdxes) {
            if (i < 0) {
                // TODO why is 0 valid? load message from resource files
                throw new SQLException("Match columns should be greater than 0");
            }
        }

        if (matchColumnIndexes == null) {
            /*
             * FIXME initial match column, the default length of array is 10 in
             * RI, we don't know why, just follow now
             */
            matchColumnIndexes = new int[10];
            Arrays.fill(matchColumnIndexes, -1);
        }

        int[] newValue = new int[matchColumnIndexes.length + columnIdxes.length];
        System.arraycopy(columnIdxes, 0, newValue, 0, columnIdxes.length);
        System.arraycopy(matchColumnIndexes, 0, newValue, columnIdxes.length,
                matchColumnIndexes.length);

        matchColumnIndexes = newValue;
    }

    public void setMatchColumn(String columnName) throws SQLException {
        if (columnName == null || columnName.equals("")) { //$NON-NLS-1$
            // rowset.12=Match columns should not be empty or null string
            throw new SQLException(Messages.getString("rowset.12")); //$NON-NLS-1$
        }

        if (matchColumnNames == null) {
            /*
             * FIXME initial match column, the default length of array is 10 in
             * RI, we don't know why, just follow now
             */
            matchColumnNames = new String[10];
        }

        matchColumnNames[0] = columnName;
    }

    public void setMatchColumn(String[] columnNames) throws SQLException {
        if (columnNames == null) {
            throw new NullPointerException();
        }
        for (String name : columnNames) {
            if (name == null || name.equals("")) { //$NON-NLS-1$
                // rowset.12=Match columns should not be empty or null string
                throw new SQLException(Messages.getString("rowset.12")); //$NON-NLS-1$
            }
        }

        if (matchColumnNames == null) {
            /*
             * FIXME initial match column, the default length of array is 10 in
             * RI, we don't know why, just follow now
             */
            matchColumnNames = new String[10];
        }

        String[] newValue = new String[matchColumnNames.length
                + columnNames.length];
        System.arraycopy(columnNames, 0, newValue, 0, columnNames.length);
        System.arraycopy(matchColumnNames, 0, newValue, columnNames.length,
                matchColumnNames.length);

        matchColumnNames = newValue;
    }

    public void unsetMatchColumn(int columnIdx) throws SQLException {

        if (matchColumnIndexes == null || matchColumnIndexes.length == 0
                || matchColumnIndexes[0] != columnIdx) {
            throw new SQLException(Messages.getString("rowset.15")); //$NON-NLS-1$
        }

        matchColumnIndexes[0] = -1;
    }

    public void unsetMatchColumn(int[] columnIdxes) throws SQLException {
        if (columnIdxes == null) {
            throw new NullPointerException();
        }

        if (columnIdxes.length == 0) {
            return;
        }

        if (matchColumnIndexes == null
                || matchColumnIndexes.length < columnIdxes.length) {
            throw new SQLException(Messages.getString("rowset.15")); //$NON-NLS-1$
        }

        for (int i = 0; i < columnIdxes.length; i++) {
            if (matchColumnIndexes[i] != columnIdxes[i]) {
                throw new SQLException(Messages.getString("rowset.15")); //$NON-NLS-1$    
            }
        }

        Arrays.fill(matchColumnIndexes, 0, columnIdxes.length, -1);
    }

    public void unsetMatchColumn(String columnName) throws SQLException {
        if (matchColumnNames == null || matchColumnNames.length == 0
                || !matchColumnNames[0].equals(columnName)) {
            throw new SQLException(Messages.getString("rowset.15")); //$NON-NLS-1$
        }

        matchColumnNames[0] = null;

    }

    public void unsetMatchColumn(String[] columnName) throws SQLException {
        if (columnName == null) {
            throw new NullPointerException();
        }

        if (columnName.length == 0) {
            return;
        }

        if (matchColumnNames == null
                || matchColumnNames.length < columnName.length) {
            throw new SQLException(Messages.getString("rowset.15")); //$NON-NLS-1$
        }

        for (int i = 0; i < columnName.length; i++) {
            if (matchColumnNames[i] != columnName[i]) {
                throw new SQLException(Messages.getString("rowset.15")); //$NON-NLS-1$    
            }
        }

        Arrays.fill(matchColumnNames, 0, columnName.length, null);
    }

    public boolean absolute(int row) throws SQLException {
        return doAbsolute(getIndexIncludeDeletedRows(row), true);
    }

    /**
     * internal implement of absolute
     * 
     * @param row
     *            index of row cursor to move, include deleted rows
     * @param checkType
     *            whether to check property ResultSet.TYPE_FORWARD_ONLY
     * @return whether the cursor is on result set
     * @throws SQLException
     */
    private boolean doAbsolute(int row, boolean checkType) throws SQLException {
        if (isCursorOnInsert) {
            // rowset.0=Not a valid position
            throw new SQLException(Messages.getString("rowset.0")); //$NON-NLS-1$
        }
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
        String username = getUsername();
        String password = getPassword();
        initialProperties();
        setUsername(username);
        setPassword(password);

        rows = new ArrayList<CachedRow>();
        currentRowIndex = 0;
        currentRow = null;
        deletedRowCount = 0;
        isCursorOnInsert = false;
        isLastColNull = false;
        matchColumnNames = null;
        matchColumnIndexes = null;
        conn = null;
    }

    public void deleteRow() throws SQLException {
        checkValidRow();
        if (currentRow == insertRow) {
            // TODO add error message
            throw new SQLException();
        }
        currentRow.setDelete();
        deletedRowCount++;
    }

    private void checkValidRow() throws SQLException {
        if (currentRow == null) {
            // rowset.7=Not a valid cursor
            throw new SQLException(Messages.getString("rowset.7")); //$NON-NLS-1$
        }
    }

    /**
     * convert <code>index</code> consider property <code>showDeleted</code>.
     * If <code>showDeleted</code> is true, do nothing, otherwise, re-compute
     * <code>index</code> add deleted rows.
     * 
     * @param index
     *            maybe negative, indicate the row number counting from the end
     *            of the result set
     * @return row index include delted rows
     */
    private int getIndexIncludeDeletedRows(int index) throws SQLException {
        if (getShowDeleted()) {
            return index;
        }

        if (index == 0) {
            return 0;
        }

        if (index > 0) {
            int indexIncludeDeletedRows = 0;
            for (; index > 0; ++indexIncludeDeletedRows) {
                if (indexIncludeDeletedRows == rows.size()) {
                    indexIncludeDeletedRows += index;
                    break;
                }

                if (!rows.get(indexIncludeDeletedRows).isDelete()) {
                    index--;
                }
            }
            return indexIncludeDeletedRows;
        }

        // index < 0
        int indexIncludeDeletedRows = rows.size();
        for (; index < 0; --indexIncludeDeletedRows) {
            if (indexIncludeDeletedRows == 0) {
                break;
            }

            if (!rows.get(indexIncludeDeletedRows - 1).isDelete()) {
                index++;
            }
        }
        if (indexIncludeDeletedRows != 0) {
            indexIncludeDeletedRows++;
        }

        return indexIncludeDeletedRows;
    }

    /**
     * If <code>showDeleted</code> property is true, return the rows size
     * include deleted rows. Otherwise not include deleted rows.
     * 
     * @return
     * @throws SQLException
     */
    private int getValidRowSize() throws SQLException {
        if (rows == null) {
            return 0;
        }

        if (getShowDeleted()) {
            return rows.size();
        }

        return rows.size() - deletedRowCount;
    }

    public int findColumn(String columnName) throws SQLException {
        return getIndexByName(columnName);
    }

    public boolean first() throws SQLException {
        return doAbsolute(getIndexIncludeDeletedRows(1), true);
    }

    public Array getArray(int columnIndex) throws SQLException {
        Object obj = getObject(columnIndex);
        if (obj == null) {
            isLastColNull = true;
            return null;
        }
        isLastColNull = false;
        try {
            return (Array) obj;
        } catch (ClassCastException e) {
            // rowset.10=Data Type Mismatch
            throw new SQLException(Messages.getString("rowset.10"));
        }
    }

    public Array getArray(String colName) throws SQLException {
        return getArray(getIndexByName(colName));
    }

    public InputStream getAsciiStream(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    public InputStream getAsciiStream(String columnName) throws SQLException {
        throw new NotImplementedException();
    }

    public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
        Object obj = getObject(columnIndex);
        if (obj == null) {
            isLastColNull = true;
            return null;
        }
        isLastColNull = false;
        try {
            return new BigDecimal(obj.toString());
        } catch (NumberFormatException e) {
            // rowset.10=Data Type Mismatch
            throw new SQLException(Messages.getString("rowset.10"));
        }
    }

    public BigDecimal getBigDecimal(int columnIndex, int scale)
            throws SQLException {
        throw new NotImplementedException();
    }

    public BigDecimal getBigDecimal(String columnName) throws SQLException {
        return getBigDecimal(getIndexByName(columnName));
    }

    public BigDecimal getBigDecimal(String columnName, int scale)
            throws SQLException {
        return getBigDecimal(getIndexByName(columnName), scale);
    }

    public InputStream getBinaryStream(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    public InputStream getBinaryStream(String columnName) throws SQLException {
        throw new NotImplementedException();
    }

    public Blob getBlob(int columnIndex) throws SQLException {
        Object obj = getObject(columnIndex);
        if (obj == null) {
            isLastColNull = true;
            return null;
        }
        isLastColNull = false;
        try {
            return (Blob) obj;
        } catch (ClassCastException e) {
            // rowset.10=Data Type Mismatch
            throw new SQLException(Messages.getString("rowset.10"));
        }
    }

    public Blob getBlob(String columnName) throws SQLException {
        return getBlob(getIndexByName(columnName));
    }

    public boolean getBoolean(int columnIndex) throws SQLException {
        Object obj = getObject(columnIndex);
        if (obj == null) {
            isLastColNull = true;
            return false;
        }
        isLastColNull = false;
        try {
            return Boolean.parseBoolean(obj.toString());
        } catch (Exception e) {
            // rowset.10=Data Type Mismatch
            throw new SQLException(Messages.getString("rowset.10"));
        }
    }

    public boolean getBoolean(String columnName) throws SQLException {
        return getBoolean(getIndexByName(columnName));
    }

    public byte getByte(int columnIndex) throws SQLException {
        Object obj = getObject(columnIndex);
        if (obj == null) {
            isLastColNull = true;
            return 0;
        }
        isLastColNull = false;
        try {
            return Byte.parseByte(obj.toString());
        } catch (NumberFormatException e) {
            // rowset.10=Data Type Mismatch
            throw new SQLException(Messages.getString("rowset.10"));
        }
    }

    public byte getByte(String columnName) throws SQLException {
        return getByte(getIndexByName(columnName));
    }

    public byte[] getBytes(int columnIndex) throws SQLException {
        Object obj = getObject(columnIndex);
        if (obj == null) {
            isLastColNull = true;
            return null;
        }
        isLastColNull = false;
        try {
            return (byte[]) obj;
        } catch (ClassCastException e) {
            // rowset.10=Data Type Mismatch
            throw new SQLException(Messages.getString("rowset.10"));
        }
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
        Object obj = getObject(columnIndex);
        if (obj == null) {
            isLastColNull = true;
            return null;
        }
        isLastColNull = false;
        try {
            return (Clob) obj;
        } catch (ClassCastException e) {
            // rowset.10=Data Type Mismatch
            throw new SQLException(Messages.getString("rowset.10"));
        }
    }

    public Clob getClob(String colName) throws SQLException {
        return getClob(getIndexByName(colName));
    }

    public String getCursorName() throws SQLException {
        /*
         * FIXME not sure how to implement it.
         */
        if (cursorName == null) {
            // rowset.14=Positioned updates not supported
            throw new SQLException(Messages.getString("rowset.14"));
        }
        return cursorName;
    }

    public Date getDate(int columnIndex) throws SQLException {
        Object obj = getObject(columnIndex);
        if (obj == null) {
            isLastColNull = true;
            return null;
        }
        isLastColNull = false;
        try {
            if (obj instanceof Date) {
                return (Date) obj;
            } else if (obj instanceof Timestamp) {
                return new Date(((Timestamp) obj).getTime());
            }
            // rowset.10=Data Type Mismatch
            throw new SQLException(Messages.getString("rowset.10"));
        } catch (Exception e) {
            // rowset.10=Data Type Mismatch
            throw new SQLException(Messages.getString("rowset.10"));
        }
    }

    public Date getDate(int columnIndex, Calendar cal) throws SQLException {
        throw new NotImplementedException();
    }

    public Date getDate(String columnName) throws SQLException {
        return getDate(getIndexByName(columnName));
    }

    public Date getDate(String columnName, Calendar cal) throws SQLException {
        return getDate(getIndexByName(columnName), cal);
    }

    public double getDouble(int columnIndex) throws SQLException {
        Object obj = getObject(columnIndex);
        if (obj == null) {
            isLastColNull = true;
            return 0;
        }
        isLastColNull = false;
        try {
            return Double.parseDouble(obj.toString());
        } catch (NumberFormatException e) {
            // rowset.10=Data Type Mismatch
            throw new SQLException(Messages.getString("rowset.10"));
        }
    }

    public double getDouble(String columnName) throws SQLException {
        return getDouble(getIndexByName(columnName));
    }

    public float getFloat(int columnIndex) throws SQLException {
        Object obj = getObject(columnIndex);
        if (obj == null) {
            isLastColNull = true;
            return 0;
        }
        isLastColNull = false;
        try {
            return Float.parseFloat(obj.toString());
        } catch (NumberFormatException e) {
            // rowset.10=Data Type Mismatch
            throw new SQLException(Messages.getString("rowset.10"));
        }
    }

    public float getFloat(String columnName) throws SQLException {
        return getFloat(getIndexByName(columnName));
    }

    public int getInt(int columnIndex) throws SQLException {
        Object obj = getObject(columnIndex);
        if (obj == null) {
            isLastColNull = true;
            return 0;
        }
        isLastColNull = false;
        try {
            return Integer.parseInt(obj.toString());
        } catch (NumberFormatException e) {
            // rowset.10=Data Type Mismatch
            throw new SQLException(Messages.getString("rowset.10"));
        }
    }

    public int getInt(String columnName) throws SQLException {
        return getInt(getIndexByName(columnName));
    }

    public long getLong(int columnIndex) throws SQLException {
        Object obj = getObject(columnIndex);
        if (obj == null) {
            isLastColNull = true;
            return 0;
        }
        isLastColNull = false;
        try {
            return Long.parseLong(obj.toString());
        } catch (NumberFormatException e) {
            // rowset.10=Data Type Mismatch
            throw new SQLException(Messages.getString("rowset.10"));
        }
    }

    public long getLong(String columnName) throws SQLException {
        return getLong(getIndexByName(columnName));
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        return meta;
    }

    public Object getObject(int columnIndex) throws SQLException {
        if (meta == null || currentRow == null) {
            // rowset.7=Not a valid cursor
            throw new SQLException(Messages.getString("rowset.7"));
        }
        if (columnIndex <= 0 || columnIndex > columnCount) {
            // sql.27=Invalid column index :{0}
            throw new SQLException(Messages.getString("sql.27", columnIndex));
        }
        Object obj = currentRow.getObject(columnIndex);
        if (obj == null) {
            isLastColNull = true;
        } else {
            isLastColNull = false;
        }
        return obj;
    }

    public Object getObject(int columnIndex, Map<String, Class<?>> map)
            throws SQLException {
        throw new NotImplementedException();
    }

    public Object getObject(String columnName) throws SQLException {
        return getObject(getIndexByName(columnName));
    }

    public Object getObject(String columnName, Map<String, Class<?>> map)
            throws SQLException {
        return getObject(getIndexByName(columnName), map);
    }

    public Ref getRef(int columnIndex) throws SQLException {
        Object obj = getObject(columnIndex);
        if (obj == null) {
            isLastColNull = true;
            return null;
        }
        isLastColNull = false;
        try {
            return (Ref) obj;
        } catch (Exception e) {
            // rowset.10=Data Type Mismatch
            throw new SQLException(Messages.getString("rowset.10"));
        }
    }

    public Ref getRef(String colName) throws SQLException {
        return getRef(getIndexByName(colName));
    }

    public int getRow() throws SQLException {
        // FIXME need more tests
        if (currentRow == null || rows == null || isCursorOnInsert) {
            return 0;
        }

        if (!getShowDeleted() && currentRow.isDelete()) {
            return 0;
        }

        if (getShowDeleted() || currentRowIndex == 0) {
            return currentRowIndex;
        }

        // doesn't show deleted rows, skip them
        int index = 0;
        for (int i = 0; i < currentRowIndex; ++i) {
            if (!rows.get(i).isDelete()) {
                index++;
            }
        }
        return index;

    }

    public short getShort(int columnIndex) throws SQLException {
        Object obj = getObject(columnIndex);
        if (obj == null) {
            isLastColNull = true;
            return 0;
        }
        isLastColNull = false;
        try {
            return Short.parseShort(obj.toString());
        } catch (NumberFormatException e) {
            // rowset.10=Data Type Mismatch
            throw new SQLException(Messages.getString("rowset.10"));
        }
    }

    public short getShort(String columnName) throws SQLException {
        return getShort(getIndexByName(columnName));
    }

    public Statement getStatement() throws SQLException {
        return null;
    }

    // columnIndex: from 1 rather than 0
    public String getString(int columnIndex) throws SQLException {
        Object obj = getObject(columnIndex);
        if (obj == null) {
            isLastColNull = true;
            return null;
        }
        isLastColNull = false;
        try {
            return obj.toString();
        } catch (Exception e) {
            // rowset.10=Data Type Mismatch
            throw new SQLException(Messages.getString("rowset.10"));
        }
    }

    private void checkColumnValid(int columnIndex) throws SQLException {
        if (columnIndex <= 0 || columnIndex > meta.getColumnCount()) {
            // sql.27=Invalid column index :{0}
            throw new SQLException(Messages.getString("sql.27", Integer //$NON-NLS-1$
                    .valueOf(columnIndex)));
        }
    }

    public String getString(String columnName) throws SQLException {
        return getString(getIndexByName(columnName));
    }

    public Time getTime(int columnIndex) throws SQLException {
        Object obj = getObject(columnIndex);
        if (obj == null) {
            isLastColNull = true;
            return null;
        }
        isLastColNull = false;
        try {
            if (obj instanceof Time) {
                return (Time) obj;
            } else if (obj instanceof Timestamp) {
                return new Time(((Timestamp) obj).getTime());
            }
            // rowset.10=Data Type Mismatch
            throw new SQLException(Messages.getString("rowset.10"));
        } catch (Exception e) {
            // rowset.10=Data Type Mismatch
            throw new SQLException(Messages.getString("rowset.10"));
        }
    }

    public Time getTime(int columnIndex, Calendar cal) throws SQLException {
        throw new NotImplementedException();
    }

    public Time getTime(String columnName) throws SQLException {
        return getTime(getIndexByName(columnName));
    }

    public Time getTime(String columnName, Calendar cal) throws SQLException {
        return getTime(getIndexByName(columnName), cal);
    }

    public Timestamp getTimestamp(int columnIndex) throws SQLException {
        Object obj = getObject(columnIndex);
        if (obj == null) {
            isLastColNull = true;
            return null;
        }
        isLastColNull = false;
        try {
            if (obj instanceof Date) {
                return new Timestamp(((Date) obj).getTime());
            } else if (obj instanceof Time) {
                return new Timestamp(((Time) obj).getTime());
            }
            return Timestamp.valueOf(obj.toString());
        } catch (Exception e) {
            // rowset.10=Data Type Mismatch
            throw new SQLException(Messages.getString("rowset.10"));
        }
    }

    public Timestamp getTimestamp(int columnIndex, Calendar cal)
            throws SQLException {
        throw new NotImplementedException();
    }

    public Timestamp getTimestamp(String columnName) throws SQLException {
        return getTimestamp(getIndexByName(columnName));
    }

    public Timestamp getTimestamp(String columnName, Calendar cal)
            throws SQLException {
        return getTimestamp(getIndexByName(columnName), cal);
    }

    public java.net.URL getURL(int columnIndex) throws SQLException {
        Object obj = getObject(columnIndex);
        if (obj == null) {
            isLastColNull = true;
            return null;
        }
        isLastColNull = false;
        try {
            return (java.net.URL) obj;
        } catch (Exception e) {
            // rowset.10=Data Type Mismatch
            throw new SQLException(Messages.getString("rowset.10"));
        }
    }

    public java.net.URL getURL(String columnName) throws SQLException {
        return getURL(getIndexByName(columnName));
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
        boolean isValueSet = false;
        for (int i = 1; i <= columnCount; i++) {
            if (currentRow.getUpdateMask(i)) {
                isValueSet = true;
                break;
            }
        }
        if (!isValueSet) {
            // TODO add error message
            throw new SQLException();
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
        return getRow() == 1;
    }

    public boolean isLast() throws SQLException {
        if (rows == null || rows.size() == 0) {
            return false;
        }

        return getRow() == getValidRowSize();
    }

    public boolean last() throws SQLException {
        return doAbsolute(getIndexIncludeDeletedRows(-1), true);
    }

    public void moveToCurrentRow() throws SQLException {
        if (isCursorOnInsert) {
            currentRowIndex = rememberedCursorPosition;
            if (currentRowIndex >= 1 && currentRowIndex <= rows.size()) {
                currentRow = rows.get(currentRowIndex - 1);
            } else {
                currentRow = null;
            }
            rememberedCursorPosition = -1;
            isCursorOnInsert = false;
        }
    }

    public void moveToInsertRow() throws SQLException {
        if (meta == null) {
            // TODO add error message
            throw new SQLException();
        }
        insertRow = new CachedRow(new Object[columnCount]);
        currentRow = insertRow;
        rememberedCursorPosition = currentRowIndex;
        currentRowIndex = -1;
        isCursorOnInsert = true;
    }

    public boolean next() throws SQLException {
        /*
         * spec next() is identical with relative(1), but they can't:
         * 
         * next() doesn't check TYPE_FORWARD_ONLY property, relative(1) does.
         */
        return doAbsolute(findNextValidRow(), false);
    }

    /**
     * Valid row is row which is visible to users. If <code>showDeleted</code>
     * is true, deleted rows are valid rows, otherwise deleted rows are invalid.
     * 
     * @return next valid row
     * @throws SQLException
     */
    private int findNextValidRow() throws SQLException {
        int index = currentRowIndex + 1;

        if (getShowDeleted()) {
            return index;
        }

        if (index > rows.size()) {
            return rows.size() + 1;
        }

        while (index <= rows.size()) {
            if (!rows.get(index - 1).isDelete()) {
                break;
            }
            index++;
        }

        return index;
    }

    public boolean previous() throws SQLException {
        return doAbsolute(findPreviousValidRow(), true);
    }

    /**
     * Valid row is row which is visible to users. If <code>showDeleted</code>
     * is true, deleted rows are valid rows, otherwise deleted rows are invalid.
     * 
     * @return previous valid row
     * @throws SQLException
     */
    private int findPreviousValidRow() throws SQLException {
        int index = currentRowIndex - 1;

        if (index <= 0) {
            return 0;
        }

        if (getShowDeleted()) {
            return index;
        }

        while (index > 0) {
            if (!rows.get(index - 1).isDelete()) {
                break;
            }
            index--;
        }

        return index;
    }

    public void refreshRow() throws SQLException {
        checkValidRow();
        if (isCursorOnInsert) {
            // rowset.0=Not a valid position
            throw new SQLException(Messages.getString("rowset.0"));
        }
    }

    public boolean relative(int moveRows) throws SQLException {
        checkValidRow();
        // TODO use more effective way to move cursor
        int index = getRow() + moveRows;

        if (isCursorOnInsert || currentRow.isDelete()) {
            if (moveRows > 0) {
                if (next()) {
                    index = getRow() + moveRows - 1;
                } else {
                    return false;
                }
            }

            if (moveRows < 0) {
                if (previous()) {
                    index = getRow() + moveRows + 1;
                } else {
                    return false;
                }
            }
        }

        if (index <= 0) {
            beforeFirst();
            return false;
        }

        if (index > rows.size()) {
            afterLast();
            return false;
        }

        return doAbsolute(getIndexIncludeDeletedRows(index), true);
    }

    public boolean rowDeleted() throws SQLException {
        checkValidRow();
        return currentRow.isDelete();
    }

    public boolean rowInserted() throws SQLException {
        if (currentRow == null || isCursorOnInsert) {
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
        checkValidRow();
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
        if (isCursorOnInsert && insertRow == null) {
            insertRow = new CachedRow(new Object[columnCount]);
            currentRow = insertRow;
        }
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
        if (isCursorOnInsert && insertRow == null) {
            insertRow = new CachedRow(new Object[columnCount]);
            currentRow = insertRow;
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
        if (isCursorOnInsert && insertRow == null) {
            insertRow = new CachedRow(new Object[columnCount]);
            currentRow = insertRow;
        }
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
                    // rowset.10=Data Type Mismatch
                    throw new SQLException(Messages.getString("rowset.10")); //$NON-NLS-1$
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
                    // rowset.10=Data Type Mismatch
                    throw new SQLException(Messages.getString("rowset.10")); //$NON-NLS-1$
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
                    // rowset.10=Data Type Mismatch
                    throw new SQLException(Messages.getString("rowset.10")); //$NON-NLS-1$
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
        return isLastColNull;
    }

    public void execute() throws SQLException {
        execute(getConnection());
        conn = null;
    }

    public Connection getConnection() throws SQLException {
        // TODO consider user name, password and datasource
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
