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
import org.apache.harmony.pack200.Segment;

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
// Commented on request in HARMONY-2246
// Will be uncommented later     
//	public void testHelloWorld() throws Exception {
//		assertNotNull(Segment.parse(Segment.class
//				.getResourceAsStream("/org/apache/harmony/pack200/tests/HelloWorld.pack")));
//	}
	/**
	 * @param args
	 * @throws Exception
	 */
	public void testJustResources() throws Exception {
		assertNotNull(Segment.parse(Segment.class
				.getResourceAsStream("/org/apache/harmony/pack200/tests/JustResources.pack")));
	}

}
