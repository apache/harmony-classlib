/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package javax.sql.rowset;

import java.io.Serializable;
import java.sql.SQLException;

import javax.sql.RowSetMetaData;

import org.apache.harmony.luni.util.NotImplementedException;
import org.apache.harmony.sql.internal.nls.Messages;

/**
 * This class is a concrete implementation of javax.sql.RowSetMetatData, which
 * provides methods that get and set column information.
 * 
 * A RowSetMetaDataImpl object can be obtained by the getMetaData() method in
 * javax.sql.RowSet.
 * 
 */
public class RowSetMetaDataImpl implements RowSetMetaData, Serializable {

    private static final String EMPTY_STRING = ""; //$NON-NLS-1$

    private static final int DEFAULT_COLUMN_COUNT = 4;

    private static final long serialVersionUID = 6893806403181801867L;

    private int colCount;

    private ColInfo[] colInfo;

    /**
     * The default constructor. 
     */
    public RowSetMetaDataImpl() {
        // do nothing
    }
    
    private void checkColumnIndex(int arrayIndex) throws SQLException {
        if (null == colInfo || arrayIndex < 0 || arrayIndex >= colInfo.length) {
            throw new SQLException(Messages.getString("sql.27", arrayIndex + 1)); //$NON-NLS-1$
        }
        // lazy initialization
        if (null == colInfo[arrayIndex]) {
            colInfo[arrayIndex] = new ColInfo();
        }
    }
    
    /**
     * {@inheritDoc}
     * 
     * @see javax.sql.RowSetMetaData#setColumnCount(int)
     */
    public void setColumnCount(int columnCount) throws SQLException {
        if (columnCount <= 0) {
            throw new SQLException(Messages.getString("sql.26")); //$NON-NLS-1$
        }
        try {
            colInfo = new ColInfo[columnCount];            
        } catch (OutOfMemoryError e) {
            // For compatibility, use same default value as RI
            colInfo = new ColInfo[DEFAULT_COLUMN_COUNT];
        }                        
        colCount = columnCount;
    }

    /**
     * {@inheritDoc}
     * 
     * @see javax.sql.RowSetMetaData#setAutoIncrement(int, boolean)
     */
    public void setAutoIncrement(int columnIndex, boolean property)
            throws SQLException {
        int arrayIndex = columnIndex - 1;
        checkColumnIndex(arrayIndex);
        colInfo[arrayIndex].autoIncrement = property;
    }

    /**
     * {@inheritDoc}
     * 
     * @see javax.sql.RowSetMetaData#setCaseSensitive(int, boolean)
     */
    public void setCaseSensitive(int columnIndex, boolean property)
            throws SQLException {
        int arrayIndex = columnIndex - 1;
        checkColumnIndex(arrayIndex);
        colInfo[arrayIndex].caseSensitive = property;
    }

    /**
     * {@inheritDoc}
     * 
     * @see javax.sql.RowSetMetaData#setSearchable(int, boolean)
     */
    public void setSearchable(int columnIndex, boolean property)
            throws SQLException {
        int arrayIndex = columnIndex - 1;
        checkColumnIndex(arrayIndex);
        colInfo[arrayIndex].searchable = property;
    }

    /**
     * {@inheritDoc}
     * 
     * @see javax.sql.RowSetMetaData#setCurrency(int, boolean)
     */
    public void setCurrency(int columnIndex, boolean property)
            throws SQLException {
        int arrayIndex = columnIndex - 1;
        checkColumnIndex(arrayIndex);
        colInfo[arrayIndex].currency = property;
    }

    public void setNullable(int columnIndex, int property) throws SQLException {
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     * 
     * @see javax.sql.RowSetMetaData#setSigned(int, boolean)
     */
    public void setSigned(int columnIndex, boolean property)
            throws SQLException {
        int arrayIndex = columnIndex - 1;
        checkColumnIndex(arrayIndex);
        colInfo[arrayIndex].signed = property;
    }

    public void setColumnDisplaySize(int columnIndex, int size)
            throws SQLException {
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     * 
     * @see javax.sql.RowSetMetaData#setColumnLabel(int, String)
     */
    public void setColumnLabel(int columnIndex, String label)
            throws SQLException {
        int arrayIndex = columnIndex - 1;
        checkColumnIndex(arrayIndex);
        colInfo[arrayIndex].columnLabel = label == null ? EMPTY_STRING : label;
    }

    public void setColumnName(int columnIndex, String columnName)
            throws SQLException {
        throw new NotImplementedException();
    }

    public void setSchemaName(int columnIndex, String schemaName)
            throws SQLException {
        throw new NotImplementedException();
    }

    public void setPrecision(int columnIndex, int precision)
            throws SQLException {
        throw new NotImplementedException();
    }

    public void setScale(int columnIndex, int scale) throws SQLException {
        throw new NotImplementedException();
    }

    public void setTableName(int columnIndex, String tableName)
            throws SQLException {
        throw new NotImplementedException();
    }

    public void setCatalogName(int columnIndex, String catalogName)
            throws SQLException {
        throw new NotImplementedException();
    }

    public void setColumnType(int columnIndex, int SQLType) throws SQLException {
        throw new NotImplementedException();
    }

    public void setColumnTypeName(int columnIndex, String typeName)
            throws SQLException {
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.ResultSetMetaData#getColumnCount()
     */
    public int getColumnCount() throws SQLException {
        return colCount;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.ResultSetMetaData#isAutoIncrement(int)
     */
    public boolean isAutoIncrement(int columnIndex) throws SQLException {
        int arrayIndex = columnIndex - 1;
        checkColumnIndex(arrayIndex);
        return colInfo[arrayIndex].autoIncrement; 
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.ResultSetMetaData#isCaseSensitive(int)
     */
    public boolean isCaseSensitive(int columnIndex) throws SQLException {
        int arrayIndex = columnIndex - 1;
        checkColumnIndex(arrayIndex);
        return colInfo[arrayIndex].caseSensitive;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.ResultSetMetaData#isSearchable(int)
     */
    public boolean isSearchable(int columnIndex) throws SQLException {
        int arrayIndex = columnIndex - 1;
        checkColumnIndex(arrayIndex);
        return colInfo[arrayIndex].searchable;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.ResultSetMetaData#isCurrency(int)
     */
    public boolean isCurrency(int columnIndex) throws SQLException {
        int arrayIndex = columnIndex - 1;
        checkColumnIndex(arrayIndex);
        return colInfo[arrayIndex].currency;
    }

    public int isNullable(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.ResultSetMetaData#isSigned(int)
     */
    public boolean isSigned(int columnIndex) throws SQLException {
        int arrayIndex = columnIndex - 1;
        checkColumnIndex(arrayIndex);
        return colInfo[arrayIndex].signed;
    }

    public int getColumnDisplaySize(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.ResultSetMetaData#getColumnLabel(int)
     */
    public String getColumnLabel(int columnIndex) throws SQLException {
        int arrayIndex = columnIndex - 1;
        checkColumnIndex(arrayIndex);
        return colInfo[arrayIndex].columnLabel;
    }

    public String getColumnName(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    public String getSchemaName(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    public int getPrecision(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    public int getScale(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    public String getTableName(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    public String getCatalogName(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    public int getColumnType(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    public String getColumnTypeName(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.ResultSetMetaData#isReadOnly(int)
     */
    public boolean isReadOnly(int columnIndex) throws SQLException {
        return !isWritable(columnIndex);
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.ResultSetMetaData#isWritable(int)
     */
    public boolean isWritable(int columnIndex) throws SQLException {
        int arrayIndex = columnIndex - 1;
        checkColumnIndex(arrayIndex);
        return colInfo[arrayIndex].writeable; 
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.sql.ResultSetMetaData#isDefinitelyWritable(int)
     */    
    public boolean isDefinitelyWritable(int columnIndex) throws SQLException {
        int arrayIndex = columnIndex - 1;
        checkColumnIndex(arrayIndex);
        return colInfo[arrayIndex].definiteWritable; 
    }

    public String getColumnClassName(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    /**
     * The inner class to store meta information of columns.
     */
    private class ColInfo {
        
        public boolean autoIncrement;

        public boolean caseSensitive;
        
        public boolean currency;

        public boolean signed;

        public boolean searchable;

        public boolean writeable = true;

        public boolean definiteWritable = true;
        
        public String columnLabel;
    }
}