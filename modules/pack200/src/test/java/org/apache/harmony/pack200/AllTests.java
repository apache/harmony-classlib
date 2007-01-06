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
package org.apache.harmony.archive.tests.internal.pack200;
// NOTE: Do not use generics in this code; it needs to run on JVMs < 1.5
// NOTE: Do not extract strings as messages; this code is still a work-in-progress
// NOTE: Also, don't get rid of 'else' statements for the hell of it ...
import org.apache.harmony.archive.tests.internal.pack200.bytecode.ClassFileEntryTest;
import org.apache.harmony.archive.tests.internal.pack200.bytecode.ConstantPoolTest;

import junit.framework.Test;
import junit.framework.TestSuite;
/**
 * Test suite for org.apache.harmony.archive.internal.pack200 package.
 */
public class AllTests {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

	public static Test suite() {
		TestSuite suite = new TestSuite(
                "Suite org.apache.harmony.archive.tests.internal.pack200");
		// $JUnit-BEGIN$
		suite.addTestSuite(AttributeLayoutMapTest.class);
		suite.addTestSuite(AttributeLayoutTest.class);
		suite.addTestSuite(ClassFileEntryTest.class);
		suite.addTestSuite(CodecEncodingTest.class);
		suite.addTestSuite(CodecTest.class);
		suite.addTestSuite(ConstantPoolTest.class);
		suite.addTestSuite(PopulationCodecTest.class);
		suite.addTestSuite(SegmentOptionsTest.class);
		suite.addTestSuite(SegmentTest.class);
		// $JUnit-END$
		return suite;
	}
}
