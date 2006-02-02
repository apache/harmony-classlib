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

package org.apache.harmony.tests.java.lang;

import junit.framework.TestCase;

public class SecurityManagerTest extends TestCase {

	/**
	 * @tests java.lang.SecurityManager#checkAccess(java.lang.Thread)
	 */
	public void test_checkAccessLjava_lang_Thread() throws InterruptedException {
		// Regression for HARMONY-66
		Thread t = new Thread() {
			public void run() {
			};
		};
		t.start();
		t.join();
		new SecurityManager().checkAccess(t);
	}
}
