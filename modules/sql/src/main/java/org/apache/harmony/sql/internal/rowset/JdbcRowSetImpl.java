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
import java.sql.Savepoint;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetWarning;

import org.apache.harmony.luni.util.NotImplementedException;

public class JdbcRowSetImpl extends AbstractRowSetImpl implements JdbcRowSet {

    public void commit() throws SQLException {
        if (connection == null) {
            throw new NullPointerException();
        }
        connection.commit();
    }

    public boolean getAutoCommit() throws SQLException {
        if (connection == null) {
            throw new NullPointerException();
        }
        return connection.getAutoCommit();
    }

    public RowSetWarning getRowSetWarnings() throws SQLException {
        return null;
    }

    public void rollback() throws SQLException {
        if (connection == null) {
            throw new NullPointerException();
        }

        connection.rollback();
        statement = null;
        resultSet = null;
    }

    public void rollback(Savepoint s) throws SQLException {
        if (connection == null) {
            throw new NullPointerException();
        }

        connection.rollback(s);
        statement = null;
        resultSet = null;
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException {
        if (connection == null) {
            throw new NullPointerException();
        }

        connection.setAutoCommit(autoCommit);
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
