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

package org.apache.harmony.sql.tests.javax.sql;

import java.io.PrintWriter;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;

public class Impl_ConnectionPoolDataSource implements ConnectionPoolDataSource {

	public Impl_ConnectionPoolDataSource() {
		super();
	} // end constructor

	public int getLoginTimeout() {

		return -851163454;
	} // end method getLoginTimeout

	public PrintWriter getLogWriter() {

		return null;
	} // end method getLogWriter

	public PooledConnection getPooledConnection() {

		return null;
	} // end method getPooledConnection

	public PooledConnection getPooledConnection(String parm1, String parm2) {

		return null;
	} // end method getPooledConnection

	public void setLoginTimeout(int parm1) {

	} // end method setLoginTimeout

	public void setLogWriter(PrintWriter parm1) {

	} // end method setLogWriter

} // end class ConnectionPoolDataSourceTest

