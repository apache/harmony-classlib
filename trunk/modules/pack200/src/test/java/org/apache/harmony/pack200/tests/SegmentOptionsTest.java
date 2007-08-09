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
package org.apache.harmony.pack200.tests;
//NOTE: Do not use generics in this code; it needs to run on JVMs < 1.5
//NOTE: Do not extract strings as messages; this code is still a work-in-progress
//NOTE: Also, don't get rid of 'else' statements for the hell of it ...
import junit.framework.TestCase;

import org.apache.harmony.pack200.Pack200Exception;
import org.apache.harmony.pack200.SegmentOptions;

/**
 * @author Alex Blewitt
 * @version $Revision: $
 */
public class SegmentOptionsTest extends TestCase {
	public void testUnused() {
		int[] unused = new int[] { 3, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22,
				23, 24, 25, 26, 27, 28, 29, 30, 31 };
		for (int i = 0; i < unused.length; i++) {
			try {
				new SegmentOptions(1 << unused[i]);
				fail("Bit " + unused[i] + " should be unused, but it's not caught during construction");
			} catch (Pack200Exception e) {
				assertTrue(true);
			}
		}
	}
}
