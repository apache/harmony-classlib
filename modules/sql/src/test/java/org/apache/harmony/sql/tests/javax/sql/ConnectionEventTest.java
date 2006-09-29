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

import java.sql.SQLException;

import javax.sql.ConnectionEvent;
import javax.sql.PooledConnection;

import junit.framework.TestCase;

/**
 * JUnit Testcase for the javax.sql.ConnectionEvent class
 * 
 */

public class ConnectionEventTest extends TestCase {

	/*
	 * ConstructorTest
	 */
	public void testConnectionEventPooledConnectionSQLException() {

		PooledConnection[] init1 = { new Impl_PooledConnection(),
				new Impl_PooledConnection(), null, null };
		SQLException[] init2 = { new SQLException("Test SQL Exception"), null,
				new SQLException("Test SQL Exception2"), null };

		PooledConnection[] theFinalStates1 = init1;
		SQLException[] theFinalStates2 = init2;

		Exception[] theExceptions = { null, null,
				new IllegalArgumentException("null source"),
				new IllegalArgumentException("null source") };

		ConnectionEvent aConnectionEvent;
		int loopCount = init1.length;
		for (int i = 0; i < loopCount; i++) {
			try {
				aConnectionEvent = new ConnectionEvent(init1[i], init2[i]);
				if (theExceptions[i] != null)
					fail(i + "Exception missed");
				assertEquals(i + "Final state mismatch", aConnectionEvent
						.getSource(), theFinalStates1[i]);
				assertEquals(i + "Final state mismatch", aConnectionEvent
						.getSQLException(), theFinalStates2[i]);
			} catch (Exception e) {
				if (theExceptions[i] == null)
					fail(i + "Unexpected exception");
				assertEquals(i + "Exception mismatch", e.getClass(),
						theExceptions[i].getClass());
				// assertEquals( i + "Exception mismatch", e.getMessage(),
				// theExceptions[i].getMessage() );
			} // end try
		} // end for

	} // end method testConnectionEventPooledConnectionSQLException

	/*
	 * ConstructorTest
	 */
	public void testConnectionEventPooledConnection() {

		PooledConnection[] init1 = { new Impl_PooledConnection(), null, };

		PooledConnection[] theFinalStates1 = init1;
		SQLException[] theFinalStates2 = { null, null };

		Exception[] theExceptions = { null,
				new IllegalArgumentException("null source") };

		ConnectionEvent aConnectionEvent;
		int loopCount = init1.length;
		for (int i = 0; i < loopCount; i++) {
			try {
				aConnectionEvent = new ConnectionEvent(init1[i]);
				if (theExceptions[i] != null)
					fail(i + "Exception missed");
				assertEquals(i + "Final state mismatch", aConnectionEvent
						.getSource(), theFinalStates1[i]);
				assertEquals(i + "Final state mismatch", aConnectionEvent
						.getSQLException(), theFinalStates2[i]);

			} catch (Exception e) {
				if (theExceptions[i] == null)
					fail(i + "Unexpected exception");
				assertEquals(i + "Exception mismatch", e.getClass(),
						theExceptions[i].getClass());
				// assertEquals( i + "Exception mismatch", e.getMessage(),
				// theExceptions[i].getMessage() );
			} // end try
		} // end for

	} // end method testConnectionEventPooledConnection

	/*
	 * Method test for getSQLException
	 */
	public void testGetSQLException() {

		ConnectionEvent aConnectionEvent;
		PooledConnection[] init1 = { new Impl_PooledConnection(),
				new Impl_PooledConnection() };
		SQLException[] init2 = { new SQLException("Test SQL Exception"), null };

		SQLException theReturn;
		SQLException[] theReturns = init2;

		PooledConnection[] theFinalStates1 = init1;
		SQLException[] theFinalStates2 = init2;

		Exception[] theExceptions = { null,
				new IllegalArgumentException("null source") };

		int loopCount = 1;
		for (int i = 0; i < loopCount; i++) {
			try {
				aConnectionEvent = new ConnectionEvent(init1[i], init2[i]);
				theReturn = aConnectionEvent.getSQLException();
				if (theExceptions[i] != null)
					fail(i + "Exception missed");
				assertTrue(theReturn.equals(theReturns[i]));
				assertEquals(i + "Final state mismatch", aConnectionEvent
						.getSource(), theFinalStates1[i]);
				assertEquals(i + "Final state mismatch", aConnectionEvent
						.getSQLException(), theFinalStates2[i]);

			} catch (Exception e) {
				if (theExceptions[i] == null)
					fail(i + "Unexpected exception");
				assertEquals(i + "Exception mismatch", e.getClass(),
						theExceptions[i].getClass());
				assertEquals(i + "Exception mismatch", e.getMessage(),
						theExceptions[i].getMessage());
			} // end try
		} // end for

	} // end method testGetSQLException

} // end class ConnectionEventTest

