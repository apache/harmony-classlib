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

package org.apache.harmony.tests.java.util.zip;

import java.util.zip.Inflater;

import junit.framework.TestCase;

public class InflaterTest extends TestCase {

	/**
	 * @tests java.util.zip.Inflater#needsDictionary()
	 */
    public void test_needsDictionary () {
    	// Regression test for HARMONY-86
        Inflater inf = new Inflater();
        assertFalse(inf.needsDictionary());
    } 
}
