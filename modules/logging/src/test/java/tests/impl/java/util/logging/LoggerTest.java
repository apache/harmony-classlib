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

package tests.impl.java.util.logging;

import java.util.MissingResourceException;
import java.util.logging.LoggerExtension;

import junit.framework.TestCase;

/**
 * Example of a test case that tests the implementation behavior of Logger.
 * 
 */
public class LoggerTest extends TestCase {

	public void testLoadResourceBundle() {
		try {
			// Try a load a non-existant resource bundle.
			LoggerExtension.loadResourceBundle("missinglogger.properties");
			fail("Expected an exception.");
		} catch (MissingResourceException ex) {
			// Expected exception is precisely a MissingResourceException
			assertTrue(ex.getClass() == MissingResourceException.class);
		}
	}
}
