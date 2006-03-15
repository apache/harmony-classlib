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

import java.util.logging.Filter;
import java.util.logging.LogRecord;

import junit.framework.TestCase;

/**
 * This testcase verifies the signature of the interface Filter.
 * 
 */
public class FilterTest extends TestCase {
	private static String className = FilterTest.class.getName();

	public void testFilter() {
		MockFilter f = new MockFilter();
		f.isLoggable(null);
	}

	/*
	 * This inner class implements the interface Filter to verify the signature.
	 */
	private class MockFilter implements Filter {

		public boolean isLoggable(LogRecord record) {
			return false;
		}
	}
}
