/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package tests.api.java.io;

public class FileNotFoundExceptionTest extends junit.framework.TestCase {

	/**
	 * @tests java.io.FileNotFoundException#FileNotFoundException()
	 */
	public void test_Constructor() {
		// Test for method java.io.FileNotFoundException()
		try {
			new java.io.FileInputStream("9://0//l");
		} catch (java.io.FileNotFoundException e) {
			return;
		}
		fail("Failed to generate Exception");
	}

	/**
	 * @tests java.io.FileNotFoundException#FileNotFoundException(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method java.io.FileNotFoundException(java.lang.String)
		try {
			new java.io.FileInputStream("9://0//l");
		} catch (java.io.FileNotFoundException e) {
			return;
		}
		fail("Failed to generate Exception");
	}

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() {
	}

	/**
	 * Tears down the fixture, for example, close a network connection. This
	 * method is called after a test is executed.
	 */
	protected void tearDown() {
	}
}
