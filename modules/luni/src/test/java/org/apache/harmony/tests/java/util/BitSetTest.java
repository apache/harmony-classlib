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

import java.util.BitSet;

import junit.framework.TestCase;

public class BitSetTest extends TestCase {

	/**
	 * @tests java.util.BitSet#clear(int, int)
	 */
	public void test_clearII() {
		// Regression for HARMONY-98
		BitSet bitset = new BitSet();
		for (int i = 0; i < 20; i++) {
			bitset.set(i);
		}
		bitset.clear(10, 10);
	}

	/**
	 * @tests java.util.BitSet#clear(int, int)
	 */
	public void test_flipII() {
		BitSet bitset = new BitSet();
		for (int i = 0; i < 20; i++) {
			bitset.set(i);
		}
		bitset.flip(10, 10);
	}

	/**
	 * @tests java.util.BitSet#get(int, int)
	 */
	public void test_getII() {
		BitSet bitset = new BitSet(30);
		bitset.get(3, 3);
	}

	/**
	 * @tests java.util.BitSet#set(int, int)
	 */
	public void test_setII() {
		BitSet bitset = new BitSet(30);
		bitset.set(29, 29);
	}
}
