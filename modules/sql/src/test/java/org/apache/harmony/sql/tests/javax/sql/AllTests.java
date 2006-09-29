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

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Test suite for the javax.sql package
 * 
 */
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
                "Suite for org.apache.harmony.sql.tests.javax.sql");
		// $JUnit-BEGIN$
		suite.addTestSuite(ConnectionEventTest.class);
		suite.addTestSuite(RowSetEventTest.class);
		// Tests needed for the following interfaces are not mandatory
		// are supplied to check Public Static values:
		suite.addTestSuite(ConnectionEventListenerTest.class);
		suite.addTestSuite(ConnectionPoolDataSourceTest.class);
		suite.addTestSuite(DataSourceTest.class);
		suite.addTestSuite(PooledConnectionTest.class);
		suite.addTestSuite(RowSetInternalTest.class);
		suite.addTestSuite(RowSetListenerTest.class);
		suite.addTestSuite(RowSetMetaDataTest.class);
		suite.addTestSuite(RowSetReaderTest.class);
		suite.addTestSuite(RowSetTest.class);
		suite.addTestSuite(RowSetWriterTest.class);
		suite.addTestSuite(XAConnectionTest.class);
		suite.addTestSuite(XADataSourceTest.class);

		// $JUnit-END$
		return suite;
	}
}
