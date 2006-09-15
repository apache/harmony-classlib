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

import javax.sql.RowSet;
import javax.sql.RowSetEvent;

import junit.framework.TestCase;

/**
 * JUnit Testcase for the javax.sql.RowSetEvent class
 * 
 */

public class RowSetEventTest extends TestCase {

	/*
	 * ConstructorTest
	 */
	public void testRowSetEventRowSet() {

		RowSet[] init1 = { new Impl_RowSet(), null };

		RowSet[] theFinalStates = init1;

		Exception[] theExceptions = { null,
				new IllegalArgumentException("null source") };

		RowSetEvent aRowSetEvent;
		int loopCount = init1.length;
		for (int i = 0; i < loopCount; i++) {
			try {
				aRowSetEvent = new RowSetEvent(init1[i]);
				if (theExceptions[i] != null)
					fail(i + "Exception missed");
				assertEquals(i + "Final state mismatch", aRowSetEvent
						.getSource(), theFinalStates[i]);

			} catch (Exception e) {
				if (theExceptions[i] == null)
					fail(i + "Unexpected exception");
				assertEquals(i + "Exception mismatch", e.getClass(),
						theExceptions[i].getClass());
				// assertEquals( i + "Exception mismatch", e.getMessage(),
				// theExceptions[i].getMessage() );
			} // end try
		} // end for

	} // end method testRowSetEventRowSet

} // end class RowSetEventTest

