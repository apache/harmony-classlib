/* Copyright 1998, 2006 The Apache Software Foundation or its licensors, as applicable
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

package org.apache.harmony.luni.tests.java.lang;

public class OutOfMemoryErrorTest extends junit.framework.TestCase {

	/**
	 * @tests java.lang.OutOfMemoryError#OutOfMemoryError()
	 */
	public void test_Constructor() {
		// Test for method java.lang.OutOfMemoryError()
	    Error e = new OutOfMemoryError();
        assertEquals(null, e.getCause());
        assertEquals(null, e.getMessage());
	}

	/**
	 * @tests java.lang.OutOfMemoryError#OutOfMemoryError(java.lang.String)
	 */
	public void test_ConstructorLjava_lang_String() {
		// Test for method java.lang.OutOfMemoryError(java.lang.String)
		Error e = new OutOfMemoryError(null);
        assertEquals(null, e.getMessage());
        assertEquals(null, e.getCause());
        
        e= new OutOfMemoryError("msg");
        assertEquals("msg", e.getMessage());
        assertEquals(null, e.getCause());
	}

}
