/*
 *  Copyright 2006 The Apache Software Foundation or its licensors, 
 *  as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.harmony.archive.tests.internal.pack200;

import org.apache.harmony.archive.internal.pack200.Segment;

import junit.framework.TestCase;

/**
 * @author Alex Blewitt
 * @version $Revision: $
 */
public class SegmentTest extends TestCase {
	/**
	 * @param args
	 * @throws Exception
	 */
	public void testHelloWorld() throws Exception {
		assertNotNull(Segment.parse(Segment.class
				.getResourceAsStream("/org/apache/harmony/archive/tests/internal/pack200/HelloWorld.pack")));
	}
	/**
	 * @param args
	 * @throws Exception
	 */
	public void testJustResources() throws Exception {
		assertNotNull(Segment.parse(Segment.class
				.getResourceAsStream("/org/apache/harmony/archive/tests/internal/pack200/JustResources.pack")));
	}

}
