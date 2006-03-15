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

package tests.api.java.util.logging;

import java.util.logging.ErrorManager;

import junit.framework.TestCase;

/**
 */
public class ErrorManagerTest extends TestCase {
	private static String className = ErrorManagerTest.class.getName();

	ErrorManager em;

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		em = new ErrorManager();
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Constructor for ErrorManagerTest.
	 * 
	 * @param arg0
	 */
	public ErrorManagerTest(String arg0) {
		super(arg0);
	}

	public void testErrorWithNullParameter() {
		em.error(null, null, -1);
		em.error(null, null, -2);
	}

	public void testError() {
		em.error("test message, pls ignore it", new RuntimeException(
				"test exception, pls ignore it"), ErrorManager.CLOSE_FAILURE);
	}

	public void testConstValue() {
		assertEquals(3, ErrorManager.CLOSE_FAILURE);
		assertEquals(2, ErrorManager.FLUSH_FAILURE);
		assertEquals(5, ErrorManager.FORMAT_FAILURE);
		assertEquals(0, ErrorManager.GENERIC_FAILURE);
		assertEquals(4, ErrorManager.OPEN_FAILURE);
		assertEquals(1, ErrorManager.WRITE_FAILURE);
	}

}
