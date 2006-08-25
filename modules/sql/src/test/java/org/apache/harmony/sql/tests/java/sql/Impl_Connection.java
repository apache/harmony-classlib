/* Copyright 2004 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
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
import java.sql.SQLWarning;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Map;

public class Impl_Connection implements Connection {

	public Impl_Connection() {
		super();
	} // end constructor

	public Statement createStatement() {

		return null;
	} // end method createStatement

	public PreparedStatement prepareStatement(String parm1) {

		return null;
	} // end method prepareStatement

	public CallableStatement prepareCall(String parm1) {

		return null;
	} // end method prepareCall

	public String nativeSQL(String parm1) {

		return null;
	} // end method nativeSQL

	public void setAutoCommit(boolean parm1) {

	} // end method setAutoCommit

	public boolean getAutoCommit() {

		return false;
	} // end method getAutoCommit

	public void commit() {

	} // end method commit

	public void rollback() {

	} // end method rollback

	public void close() {

	} // end method close

	public boolean isClosed() {

		return false;
	} // end method isClosed

	public DatabaseMetaData getMetaData() {

		return null;
	} // end method getMetaData

	public void setReadOnly(boolean parm1) {

	} // end method setReadOnly

	public boolean isReadOnly() {

		return true;
	} // end method isReadOnly

	public void setCatalog(String parm1) {

	} // end method setCatalog

	public String getCatalog() {

		return null;
	} // end method getCatalog

	public void setTransactionIsolation(int parm1) {

	} // end method setTransactionIsolation

	public int getTransactionIsolation() {

		return -1980410800;
	} // end method getTransactionIsolation

	public SQLWarning getWarnings() {

		return null;
	} // end method getWarnings

	public void clearWarnings() {

	} // end method clearWarnings

	public Statement createStatement(int parm1, int parm2) {

		return null;
	} // end method createStatement

	public PreparedStatement prepareStatement(String parm1, int parm2, int parm3) {

		return null;
	} // end method prepareStatement

	public CallableStatement prepareCall(String parm1, int parm2, int parm3) {

		return null;
	} // end method prepareCall

	public Map getTypeMap() {

		return null;
	} // end method getTypeMap

	public void setTypeMap(Map parm1) {

	} // end method setTypeMap

	public void setHoldability(int parm1) {

	} // end method setHoldability

	public int getHoldability() {

		return 632720050;
	} // end method getHoldability

	public Savepoint setSavepoint() {

		return null;
	} // end method setSavepoint

	public Savepoint setSavepoint(String parm1) {

		return null;
	} // end method setSavepoint

	public void rollback(Savepoint parm1) {

	} // end method rollback

	public void releaseSavepoint(Savepoint parm1) {

	} // end method releaseSavepoint

	public Statement createStatement(int parm1, int parm2, int parm3) {

		return null;
	} // end method createStatement

	public PreparedStatement prepareStatement(String parm1, int parm2,
			int parm3, int parm4) {

		return null;
	} // end method prepareStatement

	public CallableStatement prepareCall(String parm1, int parm2, int parm3,
			int parm4) {

		return null;
	} // end method prepareCall

	public PreparedStatement prepareStatement(String parm1, int parm2) {

		return null;
	} // end method prepareStatement

	public PreparedStatement prepareStatement(String parm1, int[] parm2) {

		return null;
	} // end method prepareStatement

	public PreparedStatement prepareStatement(String parm1, String[] parm2) {

		return null;
	} // end method prepareStatement

} // end class ConnectionTest

