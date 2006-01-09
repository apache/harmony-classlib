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
* @author Aleksei Y. Semenov
* @version $Revision$
*/

package com.openintel.drl.security.test;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

/**
 *
 * Base class for security unit tests
 */
public class PerformanceTest extends TestCase {

	public static final String PERFORMANCE_MODE_KEY = "test.mode";
	public static final String PERFORMANCE_MODE_NOPRN = "no.print";
	public static final String PERFORMANCE_MODE_PERF = "performance";
	public static final String PERFORMANCE_MODE_REF = "performance.reference";
	public static final String PERFORMANCE_MODE_CHECK = "performance.check";
	public static final String PERFORMANCE_REPEAT_COUNT = "test.repeat.count";
	
	public static final int REPEAT_COUNT_DEFAULT = 1000000;
	protected static final long REPEAT_COUNT = Integer.getInteger(PERFORMANCE_REPEAT_COUNT, REPEAT_COUNT_DEFAULT).intValue();
	
	public static final String PERFORMANCE_FILE_KEY = "test.performance.report";
	
	protected static final String COLUMN_SEPARATOR = " : "; 
	
	public static String mode = System.getProperty(PERFORMANCE_MODE_KEY);
	
	private static boolean printAllowed = !(PERFORMANCE_MODE_REF.equals(mode) || PERFORMANCE_MODE_CHECK.equals(mode) || PERFORMANCE_MODE_NOPRN.equals(mode));
	
	public static PrintStream report = null;
	public static Map golden = null;
	
	
	public PerformanceTest(String name) {
		super(name);
	}
	
	public PerformanceTest() {
		super();
	}
	
	public long getRepeatCount() {
	    return REPEAT_COUNT;  
	}
	
	public static void initReport() {
		if (PERFORMANCE_MODE_REF.equals(mode)) {
			if (report==null)  {
				try {
					report = new PrintStream(new FileOutputStream(System.getProperty(PERFORMANCE_FILE_KEY), true));
				} catch (Exception e) {
					report = System.out;
				}
			}
		} else if (PERFORMANCE_MODE_CHECK.equals(mode)){
			if (golden==null) {
				try {
				    report = new PrintStream(new FileOutputStream(System.getProperty(PERFORMANCE_FILE_KEY)+".diff", true));
				    
				    BufferedReader goldenReader = new BufferedReader(new java.io.FileReader(System.getProperty(PERFORMANCE_FILE_KEY)));
				    golden = new HashMap();
				    String line = null;
				    while ((line = goldenReader.readLine()) != null) {
				        //System.err.println("golden line="+line);
						String w[] = line.split(COLUMN_SEPARATOR.replaceAll("\\s","\\\\s")/*"\\s:\\s"*/);
						System.err.println(java.util.Arrays.asList(w));
						golden.put(w[0], w);
				    }
				    goldenReader.close();
				} catch (Exception e) {
					report = System.out;
				}			
			}
		}
	}
	
	public void runBare() throws Throwable {
		//System.err.println("runTest(): "+getName());
		
		if ((mode!=null) && mode.startsWith(PERFORMANCE_MODE_PERF)) {
			initReport();
			boolean testResult = false;
			long t1 = System.currentTimeMillis();
			try {
			    
			    long repeatCount = getRepeatCount();
			    //System.err.println("REPEAT_COUNT: "+repeatCount);
				for (long i=0; i<repeatCount; i++) { 
					super.runBare();
				}
				testResult = true;
			} finally {
				long t2 = System.currentTimeMillis();
				String testName = this.getClass().getName()+"."+getName();
				long testTime = t2-t1;
				if (mode.equals(PERFORMANCE_MODE_REF)) {
					if (report!=null) {
						report.println(testName+COLUMN_SEPARATOR+testResult+COLUMN_SEPARATOR+testTime);
					}
				} else if (mode.equals(PERFORMANCE_MODE_CHECK) && golden!=null){
					
					String reportLine = testName+COLUMN_SEPARATOR;
					try {
					    String[] w = (String[])golden.get(testName);
						if (w != null) {
						  	//System.err.println(java.util.Arrays.asList(w));
							if (w.length>=3 && w[0].equals(testName)) {						
								boolean refResult = Boolean.valueOf(w[1]).booleanValue();
								long refTime = Long.parseLong(w[2]);						
								reportLine+=refResult+COLUMN_SEPARATOR+refTime+COLUMN_SEPARATOR;
								//System.out.println(reportLine);
								assertTrue("Performance degradation: testTime: "+testTime+ " refTime: "+refTime, !refResult || !testResult || (testTime<=refTime));
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						reportLine+=testTime;
						
						if (report!=null) {
							report.println(reportLine);
						}
					}
					
				}
				if (report!=null) {
				    report.flush();
				}
			}
		} else {
			super.runBare();
		}
		//System.err.println(getName()+" time:" + (t2-t1));
	}
	
	public static void log(String message) {
		if (printAllowed) System.out.print(message);
	}

	public static void logln(String message) {
		log(message+"\r\n");
	}
	
	public static void logError(String message) {
		if (printAllowed) System.err.print(message);
	}

	public static void loglnError(String message) {
		logError(message+"\r\n");
	}
	
}
