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

/**
 * This class is a concrete implementation of javax.sql.RowSetMetatData, which
 * provides methods that get and set column information.
 * 
 * A RowSetMetaDataImpl object can be obtained by the getMetaData() method in
 * javax.sql.RowSet.
 * 
 */
public class RowSetMetaDataImpl implements RowSetMetaData, Serializable {

    private static final long serialVersionUID = 6893806403181801867L;

    private int colCount;

    private ColInfo[] colInfo;

    public RowSetMetaDataImpl() {
        throw new NotImplementedException();
    }

    public void setColumnCount(int columnCount) throws SQLException {
        throw new NotImplementedException();
    }

    public void setAutoIncrement(int columnIndex, boolean property)
            throws SQLException {
        throw new NotImplementedException();
    }

    public void setCaseSensitive(int columnIndex, boolean property)
            throws SQLException {
        throw new NotImplementedException();
    }

    public void setSearchable(int columnIndex, boolean property)
            throws SQLException {
        throw new NotImplementedException();
    }

    public void setCurrency(int columnIndex, boolean property)
            throws SQLException {
        throw new NotImplementedException();
    }

    public void setNullable(int columnIndex, int property) throws SQLException {
        throw new NotImplementedException();
    }

    public void setSigned(int columnIndex, boolean property)
            throws SQLException {
        throw new NotImplementedException();
    }

    public void setColumnDisplaySize(int columnIndex, int size)
            throws SQLException {
        throw new NotImplementedException();
    }

    public void setColumnLabel(int columnIndex, String label)
            throws SQLException {
        throw new NotImplementedException();
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

    public int getColumnCount() throws SQLException {
        throw new NotImplementedException();
    }

    public boolean isAutoIncrement(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    public boolean isCaseSensitive(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    public boolean isSearchable(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    public boolean isCurrency(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    public int isNullable(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    public boolean isSigned(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    public int getColumnDisplaySize(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    public String getColumnLabel(int columnIndex) throws SQLException {
        throw new NotImplementedException();
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

    public boolean isReadOnly(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    public boolean isWritable(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    public boolean isDefinitelyWritable(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    public String getColumnClassName(int columnIndex) throws SQLException {
        throw new NotImplementedException();
    }

    private class ColInfo {

    }
}