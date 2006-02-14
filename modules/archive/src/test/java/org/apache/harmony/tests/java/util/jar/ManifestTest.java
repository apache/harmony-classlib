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

package org.apache.harmony.tests.java.util.jar;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.Manifest;

import junit.framework.TestCase;

public class ManifestTest extends TestCase {

	/**
	 * @tests {@link java.util.jar.Manifest#read(java.io.InputStream)
	 */
	public void test_readLjava_io_InputStream() {
		// Regression for HARMONY-89
		InputStream is = new InputStreamImpl();
		try {
			new Manifest().read(is);
			fail("Assert 0: Should have thrown IOException");
		} catch (IOException e) {
			// expected
		}
	}

	// helper class
	class InputStreamImpl extends InputStream {
		public InputStreamImpl() {
			super();
		}

		public int read() {
			return 0;
		}
	}
}
