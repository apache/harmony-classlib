package ar.org.fitc.test.main;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Enumeration;

import junit.framework.Test;
import junit.framework.TestResult;
import junit.framework.TestSuite;



public class AllPerformanceTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        AllPerformanceTest ap = new AllPerformanceTest();
//        try {
//            ap.out = new PrintStream("performanCryptoSobreWindows.xml");
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            return;
//        }
        try {

            ap.run(AllTests.suite());
//        	ap.run(new TestSuite(TestDESKeySpec.class));
        } catch (Throwable t) {
            // en caso de error cierro el xml.
            ap.endXML();
        } finally {
            ap.out.close();
        }

    }

    protected PrintStream out = System.out;
    
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
    
    protected void endXML() {
        out.println("</PerformaceTest>");
    }


    public void run(Test test) {
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
