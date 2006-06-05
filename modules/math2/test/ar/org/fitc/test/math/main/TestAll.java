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

package ar.org.fitc.test.math.main;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import ar.org.fitc.test.integration.math.TestIntegration;
import ar.org.fitc.test.math.AllTestsMath;
import ar.org.fitc.test.math.bigdecimal.AllTestsBigDecimal;
import ar.org.fitc.test.math.biginteger.AllTestsBigInteger;
import ar.org.fitc.test.math.biginteger.methods.AllTestsBigIntegerMethods;
import ar.org.fitc.test.util.TestSuiteAcumulable;

public class TestAll extends TestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(AllTestsBigInteger.suite());
	}

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for ar.org.fitc.test.math.main");

		suite.addTest(AllTestsBigInteger.suite());
		suite.addTest(AllTestsBigIntegerMethods.suite());
		suite.addTest(AllTestsBigDecimal.suite());
		suite.addTest(AllTestsMath.suite());
		suite.addTest(new TestSuiteAcumulable(TestIntegration.class));

		return suite;
	}
}
