/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
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

package org.apache.harmony.tests.java.net;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import junit.framework.TestCase;

public class URLConnectionTest extends TestCase {

	/**
	 * @tests java.net.URLConnection#setUseCaches(boolean)
	 */
	public void test_setUseCachesZ() throws MalformedURLException, IOException {
		// Regression for HARMONY-71
		URLConnection u = new URL("http://www.apache.org").openConnection();
		u.connect(); // TODO change to use local server

		try {
			u.setUseCaches(true);
			fail("Assert 0: expected an IllegalStateException");
		} catch (IllegalStateException e) {
			// expected
		}
	}

	/**
	 * @tests java.net.URLConnection#setAllowUserInteraction(boolean)
	 */
	public void test_setAllowUserInteractionZ() throws MalformedURLException,
			IOException {
		// Regression for HARMONY-72
		URLConnection u = new URL("http://www.apache.org").openConnection();
		u.connect(); // TODO change to use local server
		try {
			u.setAllowUserInteraction(false);
			fail("Assert 0: expected an IllegalStateException");
		} catch (IllegalStateException e) {
			// expected
		}
	}
}
