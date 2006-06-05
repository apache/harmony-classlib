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

package ar.org.fitc.test.util;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Enumeration;
import junit.framework.Test;
import junit.framework.TestResult;
import junit.framework.TestSuite;

public class AllPerformanceTest {
	
	public static String FILE_NAME = "performanRunTest.xml";
	protected Test test;	
    public Test getTest(){
    	return test;
    }
    
    public void setTest(Test test){
    	this.test = test;
    }
    
    public AllPerformanceTest(Test test, int iteration){
    	this.test = test;
    	this.iteration = iteration;
    }
    
    public AllPerformanceTest(){
    	test = null;
    }
       
    public static void main(String[] args) {
        
        AllPerformanceTest ap = new AllPerformanceTest();
        
        try {
            ap.out = new PrintStream("performanRunTest.xml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        try {
            ap.run();
        } catch (Throwable t) {
            // en caso de error cierro el xml.
            ap.endXML();
        } finally {
            ap.out.close();
        }

    }

    public PrintStream out = System.out;
    
    public static final int ITERATION_DEFAULT_VALUE = 100;
    private int iteration = ITERATION_DEFAULT_VALUE;
    
    public void setIteration(final int iteration) {    	
        this.iteration = iteration;        
    }
    
    public int getIteration() {
        return iteration;
    }
    
    protected void beginXML() {
        out.println("<?xml version=\"1.0\"?>");
        out.println("<PerformaceTest>");
    }
    
    public void endXML() {
        out.println("</PerformaceTest>");
    }


    public void run() {
        beginXML();
        if (test instanceof TestSuite) {
            runTestSuite((TestSuite) test);
        } else {
            runTest(test);
        }
        endXML();
        
    }
    
    protected void runTestSuite(TestSuite test) {
        System.err.println("Suite run" + test.getName()); 
        for (Enumeration e = test.tests(); e.hasMoreElements(); ) {
            Object o = e.nextElement();
            if (o instanceof TestSuite) {
                runTestSuite((TestSuite) o);
            } else {
                runTest((Test) o);
            }
            System.gc();
        }
    }
    public void runTest(Test test) { 
       
        BigDecimal[] muestras = new BigDecimal[iteration];
        BigDecimal min = BigDecimal.valueOf(Long.MAX_VALUE);
        BigDecimal max = BigDecimal.ZERO;
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal esperanza = BigDecimal.ZERO;
        BigDecimal varianza = BigDecimal.ZERO;
        TestResult result = new TestResult();
        TestResult oldresult = result;
        System.err.print(test.toString());
        for (int i=0; i < iteration; i++) {
            System.err.print(".");
            long startTime= System.nanoTime();
            test.run(result);
            long endTime= System.nanoTime();
            BigDecimal runTime= BigDecimal.valueOf(endTime).subtract(BigDecimal.valueOf(startTime));
            //esperanza = esperanza.add(runTime);
            muestras[i] = runTime;
            max = runTime.max(max);
            min = runTime.min(min);
            total = total.add(runTime);
            if (!compareResult(result, oldresult)) {
                /// TODO que hago a resultado distinto??
                System.err.println("ocurrio tengo resultados distintos");
            }
            oldresult = result;
            result = new TestResult();
            
        }
        System.err.println();
        
        
        esperanza = total.divide(BigDecimal.valueOf(iteration));
        for (int i=0; i < iteration; i++) {
            varianza = varianza.add(muestras[i].subtract(esperanza).pow(2));
        }
        varianza = varianza.divide(BigDecimal.valueOf(iteration));
        writeDataXML(test.toString(), min, max, total, esperanza, varianza, iteration, oldresult);
        
    }
    
    synchronized void print(TestResult result) {
        if (result.wasSuccessful()) {
            out.println("<testSuccessful>");
            out.print("<countRunTest>");
            out.print(result.runCount());
            out.println("</countRunTest>");
            out.println("</testSuccessful>");
        } else {
            out.println("<testFailures>");
            out.print("<countRunTest>");
            out.print(result.runCount());
            out.println("</countRunTest>");
            printErrors(result);
            printFailures(result);

            out.println("</testFailures>");
        }
    }
    
    protected void printErrors(TestResult result) {
        printDefects(result.errors(), result.errorCount(), "error");
    }
    
    protected void printFailures(TestResult result) {
        printDefects(result.failures(), result.failureCount(), "failure");
    }
    
    protected void printDefects(Enumeration booBoos, int count, String type) {
        if (count != 0) {
            out.print("<"+type+"Count>");
            out.print(count);
            out.println("</"+type+"Count>");
        }
    }
    
    protected void writeDataXML(String Name, BigDecimal min, BigDecimal max, BigDecimal total, BigDecimal media, BigDecimal varianza, int iteretion, TestResult result) {
        out.println("<test>");
        out.print("<name>");
        out.print(Name);
        out.println("</name>");
        
        out.print("<min>");
        out.print(min);
        out.println("</min>");
        
        out.print("<max>");
        out.print(max);
        out.println("</max>");
        
        out.print("<total>");
        out.print(total);
        out.println("</total>");
        
        out.print("<average>");
        out.print(media);
        out.println("</average>");
        
        out.print("<variance2>");
        out.print(varianza);
        out.println("</variance2>");

        out.print("<iterations>");
        out.print(iteration);
        out.println("</iterations>");
        
        print(result);
        out.println("</test>");
        
    }
    
    protected static boolean compareResult(TestResult r1, TestResult r2) {
        return r1.runCount() == r2.runCount() && r1.errorCount() == r2.errorCount() && r1.failureCount() == r2.failureCount();
    }

    
}
