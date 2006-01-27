/*
 * Copyright 2006 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.apache.harmony.tests.java.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

public class CollectionsTest extends TestCase {

	/**
	 * @tests java.util.Collections#rotate(java.util.List, int)
	 */
	public void test_rotateLjava_util_ListI() {
		// Regression for HARMONY-19 Rotate an *empty* list
		Collections.rotate(new ArrayList(), 25);
		
		// Regression for HARMONY-20
		List list = new ArrayList();
		list.add(0, "zero");
		list.add(1, "one");
		list.add(2, "two");
		list.add(3, "three");
		list.add(4, "four");

		Collections.rotate(list, Integer.MIN_VALUE);
		assertEquals("Rotated incorrectly at position 0, ", "three",
				(String) list.get(0));
		assertEquals("Rotated incorrectly at position 1, ", "four",
				(String) list.get(1));
		assertEquals("Rotated incorrectly at position 2, ", "zero",
				(String) list.get(2));
		assertEquals("Rotated incorrectly at position 3, ", "one",
				(String) list.get(3));
		assertEquals("Rotated incorrectly at position 4, ", "two",
				(String) list.get(4));
	}
}
