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

import org.suiterunner.Runner;
import ar.org.fitc.test.util.Configuration;
import ar.org.fitc.test.util.SuiteRunner;

public class LaunchAlltestsMath {
	protected static String RESULT_XML = "C:\\TestMath\\ResultXML\\AllTestsMath.xml";

	protected static String RESULT_FAILED = "C:\\TestMath\\Failed\\FailedAllTestsMath.rtf";

	protected static String TEST_CLASS = "ar.org.fitc.test.math.main.TestAll";

	public static void run() {
		Configuration conf = Configuration.getInstance();
		conf.setResultXML(RESULT_XML);
		conf.setResultFailed(RESULT_FAILED);
		SuiteRunner sr = new SuiteRunner(TEST_CLASS, true, conf);
		Runner.main(sr.getParameters());
	}

	public static void main(String[] args) {
		run();
	}
}
