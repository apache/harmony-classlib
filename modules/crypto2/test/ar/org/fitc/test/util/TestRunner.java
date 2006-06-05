package ar.org.fitc.test.util;


import java.io.PrintStream;

import bwbunit.whitebox.PrivateProxy;
import bwbunit.whitebox.PrivateProxyFactory;
import junit.framework.*;
import junit.runner.*;
import junit.textui.ResultPrinter;

/**
 * A command line based tool to run tests.
 * <pre>
 * java junit.textui.TestRunner [-wait] TestCaseClass
 * </pre>
 * TestRunner expects the name of a TestCase class as argument.
 * If this class defines a static <code>suite</code> method it
 * will be invoked and the returned test is run. Otherwise all
 * the methods starting with "test" having no arguments are run.
 * <p>
 * When the wait command line argument is given TestRunner
 * waits until the users types RETURN.
 * <p>
 * TestRunner prints a trace as the tests are executed followed by a
 * summary at the end.
 */
public class TestRunner extends BaseTestRunner {
    private ResultPrinter fPrinter;
    private PrivateProxy proxy;

    public static final int SUCCESS_EXIT= 0;
    public static final int FAILURE_EXIT= 1;
    public static final int EXCEPTION_EXIT= 2;

    /**
     * Constructs a TestRunner.
     */
    public TestRunner() {
        this(System.out);
    }

    /**
     * Constructs a TestRunner using the given stream for all the output
     */
    public TestRunner(PrintStream writer) {
        this(new ResultPrinter(writer));
    }

    /**
     * Constructs a TestRunner using the given ResultPrinter all the output
     */
    public TestRunner(ResultPrinter printer) {
        fPrinter= printer;
        proxy = PrivateProxyFactory.createPrivateProxy(fPrinter);
    }

    /**
     * Runs a suite extracted from a TestCase subclass.
     * @throws Exception
     */
    static public void run(Class testClass) throws Exception {
        run(new TestSuite(testClass));
    }

    /**
     * Runs a single test and collects its results.
     * This method can be used to start a test run
     * from your program.
     * <pre>
     * public static void main (String[] args) {
     *     test.textui.TestRunner.run(suite());
     * }
     * </pre>
     * @throws Exception
     */
    static public TestResult run(Test test) throws Exception {
        TestRunner runner= new TestRunner();
        return runner.doRun(test);
    }

    /**
     * Runs a single test and waits until the user
     * types RETURN.
     * @throws Exception
     */
    static public void runAndWait(Test suite) throws Exception {
        TestRunner aTestRunner= new TestRunner();
        aTestRunner.doRun(suite, true);
    }

    /**
     * Always use the StandardTestSuiteLoader. Overridden from
     * BaseTestRunner.
     */
    public TestSuiteLoader getLoader() {
        return new StandardTestSuiteLoader();
    }

    public void testFailed(int status, Test test, Throwable t) {
    }

    public void testStarted(String testName) {
    }

    public void testEnded(String testName) {
    }

    /**
     * Creates the TestResult to be used for the test run.
     */
    protected TestResult createTestResult() {
        return new TestResult();
    }

    public TestResult doRun(Test test) throws Exception {
        return doRun(test, false);
    }

    public TestResult doRun(Test suite, boolean wait) throws Exception {
        TestResult result= createTestResult();
        result.addListener(fPrinter);
        long startTime= System.currentTimeMillis();
        suite.run(result);
        long endTime= System.currentTimeMillis();
        long runTime= endTime-startTime;
        proxy.call("print",new Object[] {result, runTime}); // fPrinter non accessible method
        pause(wait);
        return result;
    }

    protected void pause(boolean wait) throws Exception {
        if (!wait) return;
        proxy.call("printWaitPrompt",null); // fPrinter non accessible method
        try {
            System.in.read();
        }
        catch(Exception e) {
        }
    }

    public static void main(String args[]) {
        TestRunner aTestRunner= new TestRunner();
        try {
            TestResult r= aTestRunner.start(args);
            if (!r.wasSuccessful())
                System.exit(FAILURE_EXIT);
            System.exit(SUCCESS_EXIT);
        } catch(Exception e) {
            System.err.println(e.getMessage());
            System.exit(EXCEPTION_EXIT);
        }
    }

    /**
     * Starts a test run. Analyzes the command line arguments
     * and runs the given test suite.
     */
    protected TestResult start(String args[]) throws Exception {
        String testCase= "";
        boolean wait= false;

        for (int i= 0; i < args.length; i++) {
            if (args[i].equals("-wait"))
                wait= true;
            else if (args[i].equals("-c"))
                testCase= extractClassName(args[++i]);
            else if (args[i].equals("-v"))
                System.err.println("JUnit "+Version.id()+" by Kent Beck and Erich Gamma");
            else
                testCase= args[i];
        }

        if (testCase.equals(""))
            throw new Exception("Usage: TestRunner [-wait] testCaseName, where name is the name of the TestCase class");

        try {
            Test suite= getTest(testCase);
            return doRun(suite, wait);
        }
        catch(Exception e) {
            throw new Exception("Could not create and run test suite: "+e);
        }
    }

    protected void runFailed(String message) {
        System.err.println(message);
        System.exit(FAILURE_EXIT);
    }

    public void setPrinter(ResultPrinter printer) {
        fPrinter= printer;
    }


}