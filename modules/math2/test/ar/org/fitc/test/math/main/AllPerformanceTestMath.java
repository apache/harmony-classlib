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

import java.io.FileNotFoundException;
import java.io.PrintStream;

import ar.org.fitc.test.util.AllPerformanceTest;

public class AllPerformanceTestMath extends AllPerformanceTest {
	public static String FILE_NAME = "C:\\TestMath\\Performance\\performanRunAllTest.xml";

	public static final int ITERATION = 50;

	public AllPerformanceTestMath() {
		setIteration(ITERATION);
		setTest(TestAll.suite());
	}

	public static void main(String[] args) {
		AllPerformanceTestMath ap = new AllPerformanceTestMath();

		try {
			ap.out = new PrintStream(FILE_NAME);
		} catch (FileNotFoundException e) {
	
			e.printStackTrace();
			System.out.println("ERROR FILE");
			return;
		}
		try {
			ap.run();
		} catch (Throwable t) {
			// in case that fails close the xml.
			ap.endXML();
			System.out.println("ERROR");
		} finally {
			ap.out.close();
		}
	}

}
