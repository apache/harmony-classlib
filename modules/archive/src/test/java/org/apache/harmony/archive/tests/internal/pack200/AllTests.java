/* Copyright 2004 The Apache Software Foundation or its licensors, as applicable
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

package org.apache.harmony.archive.tests.internal.pack200;

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
		suite.addTestSuite(CodecEncodingTest.class);
		suite.addTestSuite(CodecTest.class);
		suite.addTestSuite(PopulationCodecTest.class);
		suite.addTestSuite(SegmentOptionsTest.class);
		suite.addTestSuite(SegmentTest.class);
		// $JUnit-END$
		return suite;
	}
}
