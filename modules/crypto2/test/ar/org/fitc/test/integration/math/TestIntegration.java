/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
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
/**
 * @author Hugo Beilis
 * @author Osvaldo Demo
 * @author Jorge Rafael
 * @version 1.0
 */

package ar.org.fitc.test.integration.math;

import java.io.FileInputStream;
import junit.framework.TestCase;


import ar.org.fitc.test.util.FileCompareInputStream;
import ar.org.fitc.test.util.Messages;

/**
 * Main class for integration tests for math clases
 * 
 *
 */
public class TestIntegration extends TestCase implements Messages {

	public static void main(String[] args) {

		junit.textui.TestRunner.run(TestIntegration.class);
	}

	public TestIntegration(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		Big.main(null);
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

		public void testIntegration001()  {
			try{
				FileInputStream testFile1 = new FileInputStream(Big.FILE1);
				FileCompareInputStream file1 = new FileCompareInputStream(
				"BigInteger.txt");
			assertTrue("Failed in BigInteger", file1.isEqualTo(testFile1));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
		
		public void testIntegration002() {
			try{
			FileInputStream testFile2 = new FileInputStream(Big.FILE2);
			FileCompareInputStream file2 = new FileCompareInputStream(
			"BigDecimal.txt");
			assertTrue("Failed in BigDecimal", file2.isEqualTo(testFile2));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
		
		public void testIntegration003() {
			try{
			FileInputStream testFile3 = new FileInputStream(Big.FILE3);
			FileCompareInputStream file3 = new FileCompareInputStream(
			"BigDecimalBigInteger.txt");
			assertTrue("Failed in BigDecimalBigInteger", file3.isEqualTo(testFile3));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}

		public void testIntegration004() {
			try{
			FileInputStream testFile4 = new FileInputStream(Big.FILE4);
			FileCompareInputStream file4 = new FileCompareInputStream(
			"BigIntegerBigDecimal.txt");
			assertTrue("Failed in BigIntegerBigDecimal", file4.isEqualTo(testFile4));
		} catch (Throwable e) {
			fail(msgNoException + e);
		}
	}
		
	}


