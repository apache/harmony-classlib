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

package tests.api.javax.sql;

import java.sql.Connection;

import javax.sql.ConnectionEventListener;
import javax.sql.PooledConnection;

import tests.api.java.sql.TestHelper_Connection1;

/**
 * Helper implementation for the interface javax.sql.PooledConnection
 * 
 */

public class Impl_PooledConnection implements PooledConnection {

	public Impl_PooledConnection() {
		super();
	} // end constructor

	public Connection getConnection() {
		return new TestHelper_Connection1();
	} // end method getConnection

	public void close() {

	} // end method close

	public void addConnectionEventListener(ConnectionEventListener parm1) {

	} // end method addConnectionEventListener

	public void removeConnectionEventListener(ConnectionEventListener parm1) {

	} // end method removeConnectionEventListener

} // end class PooledConnectionTest
