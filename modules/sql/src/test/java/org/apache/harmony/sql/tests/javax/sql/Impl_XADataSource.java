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

import java.io.PrintWriter;

import javax.sql.XAConnection;
import javax.sql.XADataSource;

public class Impl_XADataSource implements XADataSource {

	public Impl_XADataSource() {
		super();
	} // end constructor

	public int getLoginTimeout() {

		return -592361557;
	} // end method getLoginTimeout

	public void setLoginTimeout(int parm1) {

	} // end method setLoginTimeout

	public PrintWriter getLogWriter() {

		return null;
	} // end method getLogWriter

	public XAConnection getXAConnection() {

		return null;
	} // end method getXAConnection

	public XAConnection getXAConnection(String parm1, String parm2) {

		return null;
	} // end method getXAConnection

	public void setLogWriter(PrintWriter parm1) {

	} // end method setLogWriter

} // end class XADataSourceTest
