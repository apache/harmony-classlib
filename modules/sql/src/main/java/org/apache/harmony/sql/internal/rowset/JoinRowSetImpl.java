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

import java.sql.SQLException;
import java.util.Collection;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.Joinable;

import org.apache.harmony.luni.util.NotImplementedException;

public class JoinRowSetImpl extends WebRowSetImpl implements JoinRowSet {

    public void addRowSet(Joinable rowset) throws SQLException {
        throw new NotImplementedException();
    }

    public void addRowSet(RowSet rowset, int columnIdx) throws SQLException {
        throw new NotImplementedException();
    }

    public void addRowSet(RowSet rowset, String columnName) throws SQLException {
        throw new NotImplementedException();
    }

    public void addRowSet(RowSet[] rowset, int[] columnIdx) throws SQLException {
        throw new NotImplementedException();
    }

    public void addRowSet(RowSet[] rowset, String[] columnName)
            throws SQLException {
        throw new NotImplementedException();
    }

    public int getJoinType() throws SQLException {
        throw new NotImplementedException();
    }

    public String[] getRowSetNames() throws SQLException {
        throw new NotImplementedException();
    }

    public Collection<?> getRowSets() throws SQLException {
        throw new NotImplementedException();
    }

    public String getWhereClause() throws SQLException {
        throw new NotImplementedException();
    }

    public void setJoinType(int joinType) throws SQLException {
        throw new NotImplementedException();
    }

    public boolean supportsCrossJoin() {
        throw new NotImplementedException();
    }

    public boolean supportsFullJoin() {
        throw new NotImplementedException();
    }

    public boolean supportsInnerJoin() {
        throw new NotImplementedException();
    }

    public boolean supportsLeftOuterJoin() {
        throw new NotImplementedException();
    }

    public boolean supportsRightOuterJoin() {
        throw new NotImplementedException();
    }

    public CachedRowSet toCachedRowSet() throws SQLException {
        throw new NotImplementedException();
    }

}
