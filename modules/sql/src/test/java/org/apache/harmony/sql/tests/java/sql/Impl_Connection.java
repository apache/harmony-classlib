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

package org.apache.harmony.sql.tests.java.sql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Map;

public class Impl_Connection implements Connection {
    public Impl_Connection() {
        super();
    }

    public Statement createStatement() {
        return null;
    }

    public PreparedStatement prepareStatement(String parm1) {
        return null;
    }

    public CallableStatement prepareCall(String parm1) {
        return null;
    }

    public String nativeSQL(String parm1) {
        return null;
    }

    public void setAutoCommit(boolean parm1) {
    }

    public boolean getAutoCommit() {
        return false;
    }

    public void commit() {
    }

    public void rollback() {
    }

    public void close() {
    }

    public boolean isClosed() {
        return false;
    }

    public DatabaseMetaData getMetaData() {
        return null;
    }

    public void setReadOnly(boolean parm1) {
    }

    public boolean isReadOnly() {
        return true;
    }

    public void setCatalog(String parm1) {
    }

    public String getCatalog() {
        return null;
    }

    public void setTransactionIsolation(int parm1) {
    }

    public int getTransactionIsolation() {
        return -1980410800;
    }

    public SQLWarning getWarnings() {
        return null;
    }

    public void clearWarnings() {
    }

    public Statement createStatement(int parm1, int parm2) {
        return null;
    }

    public PreparedStatement prepareStatement(String parm1, int parm2, int parm3) {
        return null;
    }

    public CallableStatement prepareCall(String parm1, int parm2, int parm3) {
        return null;
    }

    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return null;
    }

    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
    }

    public void setHoldability(int parm1) {
    }

    public int getHoldability() {
        return 632720050;
    }

    public Savepoint setSavepoint() {
        return null;
    }

    public Savepoint setSavepoint(String parm1) {
        return null;
    }

    public void rollback(Savepoint parm1) {
    }

    public void releaseSavepoint(Savepoint parm1) {
    }

    public Statement createStatement(int parm1, int parm2, int parm3) {
        return null;
    }

    public PreparedStatement prepareStatement(String parm1, int parm2, int parm3, int parm4) {
        return null;
    }

    public CallableStatement prepareCall(String parm1, int parm2, int parm3, int parm4) {
        return null;
    }

    public PreparedStatement prepareStatement(String parm1, int parm2) {
        return null;
    }

    public PreparedStatement prepareStatement(String parm1, int[] parm2) {
        return null;
    }

    public PreparedStatement prepareStatement(String parm1, String[] parm2) {
        return null;
    }
}
