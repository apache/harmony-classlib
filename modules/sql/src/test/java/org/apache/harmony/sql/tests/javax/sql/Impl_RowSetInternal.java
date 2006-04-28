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

package org.apache.harmony.sql.tests.javax.sql;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.sql.RowSetInternal;
import javax.sql.RowSetMetaData;

public class Impl_RowSetInternal implements RowSetInternal {

	public Impl_RowSetInternal() {
		super();
	} // end constructor

	public Connection getConnection() {

		return null;
	} // end method getConnection

	public ResultSet getOriginal() {

		return null;
	} // end method getOriginal

	public ResultSet getOriginalRow() {

		return null;
	} // end method getOriginalRow

	public Object[] getParams() {

		return null;
	} // end method getParams

	public void setMetaData(RowSetMetaData parm1) {

	} // end method setMetaData

} // end class RowSetInternalTest
