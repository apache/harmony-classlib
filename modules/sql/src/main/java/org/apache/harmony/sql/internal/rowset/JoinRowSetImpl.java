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

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.RowSet;
import javax.sql.RowSetMetaData;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.Joinable;
import javax.sql.rowset.RowSetMetaDataImpl;
import javax.sql.rowset.spi.SyncFactoryException;

import org.apache.harmony.luni.util.NotImplementedException;
import org.apache.harmony.sql.internal.nls.Messages;

public class JoinRowSetImpl extends WebRowSetImpl implements JoinRowSet {

    private List<RowSet> rsList;

    private List<Integer> matchColIndexs;

    private List<String> matchColNames;

    private int joinType;

    public JoinRowSetImpl() throws SyncFactoryException {
        super();
        initProperties();
    }

    public JoinRowSetImpl(String providerID) throws SyncFactoryException {
        super(providerID);
        initProperties();
    }

    private void initProperties() {
        rsList = new ArrayList<RowSet>();
        matchColIndexs = new ArrayList<Integer>();
        matchColNames = new ArrayList<String>();
        joinType = INNER_JOIN;
    }

    private void composeMetaData(ResultSetMetaData rsmd, int matchColumn)
            throws SQLException {
        if (getMetaData() == null) {
            if (rsmd instanceof RowSetMetaData) {
                setMetaData((RowSetMetaData) rsmd);
            } else {
                setMetaData(copyMetaData(rsmd));
            }
        } else {
            int colCount = getMetaData().getColumnCount()
                    + rsmd.getColumnCount() - 1;
            RowSetMetaData rowSetMetaData = new RowSetMetaDataImpl();
            rowSetMetaData.setColumnCount(colCount);
            for (int i = 1; i <= getMetaData().getColumnCount(); i++) {
                doCopyMetaData(rowSetMetaData, i, getMetaData(), i);
                if (i == matchColIndexs.get(0).intValue()) {
                    rowSetMetaData.setColumnName(i, "MergedCol"); //$NON-NLS-1$
                }
            }
            int index = 0;
            for (int j = 1; j <= rsmd.getColumnCount(); j++) {
                if (j == matchColumn) {
                    continue;
                }
                index++;
                doCopyMetaData(rowSetMetaData, getMetaData().getColumnCount()
                        + index, rsmd, j);
            }
            setMetaData(rowSetMetaData);
        }
    }

    public void addRowSet(Joinable rowset) throws SQLException {
        if (rowset == null || !(rowset instanceof RowSet)) {
            // rowset.33=Not a rowset
            throw new SQLException(Messages.getString("rowset.33")); //$NON-NLS-1$
        }

        RowSet currentRs = (RowSet) rowset;
        if (currentRs.getMetaData() == null) {
            // rowset.32=The given rowset is empty
            throw new SQLException(Messages.getString("rowset.32")); //$NON-NLS-1$
        }

        int matchCol = -1;
        try {
            if (rowset.getMatchColumnIndexes() != null
                    && rowset.getMatchColumnIndexes().length > 0) {
                matchCol = rowset.getMatchColumnIndexes()[0];
                if (matchCol <= 0
                        || matchCol > currentRs.getMetaData().getColumnCount()) {
                    matchCol = -2;
                }
            }
        } catch (SQLException e) {
            try {
                if (rowset.getMatchColumnNames() != null
                        && rowset.getMatchColumnNames().length > 0) {
                    try {
                        matchCol = currentRs.findColumn(rowset
                                .getMatchColumnNames()[0]);
                    } catch (SQLException e1) {
                        matchCol = -3;
                    }
                }
            } catch (SQLException e2) {
                // ignore
            }
        } finally {
            if (matchCol == -1) {
                // rowset.34=Not set a match column
                throw new SQLException(Messages.getString("rowset.34")); //$NON-NLS-1$
            } else if (matchCol == -2) {
                // rowset.35=Not a valid match olumn index
                throw new SQLException(Messages.getString("rowset.35")); //$NON-NLS-1$
            } else if (matchCol == -3) {
                // rowset.1=Not a valid column name
                throw new SQLException(Messages.getString("rowset.1")); //$NON-NLS-1$
            }
        }
        addRowSet(currentRs, matchCol);
    }

    public void addRowSet(RowSet rowset, int columnIdx) throws SQLException {
        if (rowset == null) {
            throw new NullPointerException();
        }
        if (rowset.getMetaData() == null) {
            // rowset.32=The given rowset is empty
            throw new SQLException(Messages.getString("rowset.32")); //$NON-NLS-1$
        }
        if (columnIdx <= 0 || columnIdx > rowset.getMetaData().getColumnCount()) {
            // rowset.35=Not a valid match olumn index
            throw new SQLException(Messages.getString("rowset.35")); //$NON-NLS-1$
        }

        // TODO to be continue
        rsList.add(rowset);
        if (rsList.size() == 1) {
            setMatchColumn(columnIdx);
        }

        if (getMetaData() != null
                && getMetaData().getColumnType(getMatchColumnIndexes()[0]) != rowset
                        .getMetaData().getColumnType(columnIdx)) {
            setMetaData(null);
            rows = null;
            throw new SQLException(Messages.getString("rowset.10")); //$NON-NLS-1$
        }

        composeMetaData(rowset.getMetaData(), columnIdx);
        matchColIndexs.add(columnIdx);

        // TODO join data

    }

    public void addRowSet(RowSet rowset, String columnName) throws SQLException {
        if (rowset == null) {
            throw new NullPointerException();
        }
        if (rowset.getMetaData() == null) {
            // rowset.32=The given rowset is empty
            throw new SQLException(Messages.getString("rowset.32")); //$NON-NLS-1$
        }

        int columnIdx = rowset.findColumn(columnName);
        addRowSet(rowset, columnIdx);
    }

    public void addRowSet(RowSet[] rowset, int[] columnIdx) throws SQLException {
        if (rowset == null || columnIdx == null || rowset.length == 0
                || columnIdx.length == 0) {
            throw new NullPointerException();
        }
        if (rowset.length != columnIdx.length) {
            // rowset.36=Number of elements of two arrays don't equal
            throw new SQLException(Messages.getString("rowset.36")); //$NON-NLS-1$
        }
        for (int i = 0; i < rowset.length; i++) {
            addRowSet(rowset[i], columnIdx[i]);
        }
    }

    public void addRowSet(RowSet[] rowset, String[] columnName)
            throws SQLException {
        if (rowset == null || columnName == null || rowset.length == 0
                || columnName.length == 0) {
            throw new NullPointerException();
        }
        if (rowset.length != columnName.length) {
            // rowset.36=Number of elements of two arrays don't equal
            throw new SQLException(Messages.getString("rowset.36")); //$NON-NLS-1$
        }
        for (int i = 0; i < rowset.length; i++) {
            addRowSet(rowset[i], columnName[i]);
        }
    }

    public int getJoinType() throws SQLException {
        return joinType;
    }

    public String[] getRowSetNames() throws SQLException {
        if (rsList == null || rsList.size() == 0) {
            return new String[0];
        }
        String[] rowsetNames = new String[rsList.size()];
        for (int i = 0; i < rsList.size(); i++) {
            if (rsList.get(i) instanceof CachedRowSet) {
                CachedRowSet cachedRs = (CachedRowSet) rsList.get(i);
                if (cachedRs.getTableName() == null) {
                    // rowset.37=The RowSet doesn't set the table name
                    throw new SQLException(Messages.getString("rowset.37")); //$NON-NLS-1$
                }
                rowsetNames[i] = cachedRs.getTableName();
            } else {
                // rowset.37=The RowSet doesn't set the table name
                throw new SQLException(Messages.getString("rowset.37")); //$NON-NLS-1$
            }
        }
        return rowsetNames;
    }

    public Collection<?> getRowSets() throws SQLException {
        throw new NotImplementedException();
    }

    public String getWhereClause() throws SQLException {
        throw new NotImplementedException();
    }

    public void setJoinType(int joinType) throws SQLException {
        if (supportsJoinType(joinType)) {
            this.joinType = joinType;
        } else {
            throw new SQLException("This type of join is not supported."); //$NON-NLS-1$
        }
    }

    public boolean supportsCrossJoin() {
        return supportsJoinType(CROSS_JOIN);
    }

    public boolean supportsFullJoin() {
        return supportsJoinType(FULL_JOIN);
    }

    public boolean supportsInnerJoin() {
        return supportsJoinType(INNER_JOIN);
    }

    public boolean supportsLeftOuterJoin() {
        return supportsJoinType(LEFT_OUTER_JOIN);
    }

    public boolean supportsRightOuterJoin() {
        return supportsJoinType(RIGHT_OUTER_JOIN);
    }

    private boolean supportsJoinType(int joinType) {
        if (joinType == INNER_JOIN) {
            return true;
        }
        return false;
    }

    public CachedRowSet toCachedRowSet() throws SQLException {
        throw new NotImplementedException();
    }

}
