/*
 * Copyright (C) 2001-2003 Artima Software, Inc. All rights reserved.
 * Licensed under the Open Software License version 1.0.
 *
 * A copy of the Open Software License version 1.0 is available at:
 *     http://www.artima.com/suiterunner/osl10.html
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of Artima Software, Inc. For more
 * information on Artima Software, Inc., please see:
 *     http://www.artima.com/
 */
package ar.org.fitc.test.util;

import java.io.CharArrayWriter;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.suiterunner.Report;
import org.suiterunner.Reporter;

/**
 * A <code>Reporter</code> that formats test results as
 * XML and prints to the standard output stream.
 *
 * <p>An <code>XMLReporter</code> is constructed with a default
 * configuration that reports everything. In other words,
 * an <code>XMLReporter</code> in its default configuration will
 * behave as if a <code>Set</code> containing the following configuration
 * characters was passed to <code>setConfiguration</code>:
 *
 * <UL>
 * <LI><code>Reporter.PRESENT_RUN_STARTING</code>
 * <LI><code>Reporter.PRESENT_TEST_STARTING</code>
 * <LI><code>Reporter.PRESENT_TEST_SUCCEEDED</code>
 * <LI><code>Reporter.PRESENT_TEST_FAILED</code>
 * <LI><code>Reporter.PRESENT_SUITE_STARTING</code>
 * <LI><code>Reporter.PRESENT_SUITE_COMPLETED</code>
 * <LI><code>Reporter.PRESENT_SUITE_ABORTED</code>
 * <LI><code>Reporter.PRESENT_INFO_PROVIDED</code>
 * <LI><code>Reporter.PRESENT_RUN_STOPPED</code>
 * <LI><code>Reporter.PRESENT_RUN_ABORTED</code>
 * <LI><code>Reporter.PRESENT_RUN_COMPLETED</code>
 * </UL>
 *
 * This default configuration can be changed by invoking <code>setConfiguration</code>
 * on the <code>XMLReporter</code>, passing in a different set of configuration
 * characters.
 *
 * @author Bill Venners
 */
public class XMLReporter implements Reporter {
    
    private Configuration conf = Configuration.getInstance();
    
    // A PrintWriter that wraps the standard output
    private PrintWriter pw;

    // A Set that holFILEds this Reporter's current configuration
    private Set configuration;

    // A Set that contains all valid configuration characters, which
    // are defined in interface Reporter.
    private Set validConfigChars;

    /**
     * Construct an <code>XMLReporter</code>, which writes test results
     * in XML to the standard output stream. The <code>XMLReporter</code> is
     * created with a default configuration that includes all valid configuration
     * characters.
     */
    @SuppressWarnings("unchecked")
	public XMLReporter() {

        super();
        try {
            pw = new PrintWriter(conf.getResultXML());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        pw = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(System.out)));

        // Build a set that contains all valid configuration characters
        Set validSet = new HashSet();
        validSet.add(Reporter.PRESENT_INFO_PROVIDED);
        validSet.add(Reporter.PRESENT_RUN_ABORTED);
        validSet.add(Reporter.PRESENT_RUN_COMPLETED);
        validSet.add(Reporter.PRESENT_RUN_STARTING);
        validSet.add(Reporter.PRESENT_RUN_STOPPED);
        validSet.add(Reporter.PRESENT_SUITE_ABORTED);
        validSet.add(Reporter.PRESENT_SUITE_COMPLETED);
        validSet.add(Reporter.PRESENT_SUITE_STARTING);
        validSet.add(Reporter.PRESENT_TEST_FAILED);
        validSet.add(Reporter.PRESENT_TEST_STARTING);
        validSet.add(Reporter.PRESENT_TEST_SUCCEEDED);

        validConfigChars = Collections.unmodifiableSet(validSet);

        // Initialize configuration to validConfigChars, because the default
        // configuration for this Reporter is defined to be everything. (See
        // the JavaDoc comment for the entire class.)
        configuration = new HashSet(validConfigChars);
    }

    /**
     * Prints information indicating that a run with an expected <code>testCount</code>
     * number of tests is starting, if
     * the current configuration includes <code>Reporter.PRESENT_RUN_STARTING</code>.
     * If <code>Reporter.PRESENT_RUN_STARTING</code> is not included in the the
     * current configuration, this method prints nothing.
     *
     * @param testCount the number of tests expected during this run.
     *
     * @exception IllegalArgumentException if <code>testCount</code> is less than zero.
     */
    public void runStarting(int testCount) {

        if (testCount < 0) {
            throw new IllegalArgumentException();
        }

        if (configuration.contains(Reporter.PRESENT_RUN_STARTING)) {

            pw.println("<?xml version=\"1.0\"?> ");
            pw.println("<?xml-stylesheet type=\"text/xsl\" href=\"testreport.xsl\" ?>");
            pw.println("<run>");
        }
    }

    /**
     * Prints information extracted from the specified <code>Report</code>
     * about a test about to be run, if
     * the current configuration includes <code>Reporter.PRESENT_TEST_STARTING</code>.
     * If <code>Reporter.PRESENT_TEST_STARTING</code> is not included in the the
     * current configuration, this method prints nothing.
     *
     * @param report a <code>Report</code> that encapsulates the test starting event to report.
     *
     * @exception NullPointerException if <code>report</code> reference is <code>null</code>
     */
    public synchronized void testStarting (Report report) {

        if (report == null) {
            throw new NullPointerException("Parameter report is null.");
        }

        if (configuration.contains(Reporter.PRESENT_TEST_STARTING)) {

            String stringToReport = "<testStarting>\n";
            stringToReport += printReport(report);
            stringToReport += "</testStarting>";

            pw.println(stringToReport);
        }
    }

    /**
     * Prints information extracted from the specified <code>Report</code>
     * about a test that succeeded, if
     * the current configuration includes <code>Reporter.PRESENT_TEST_SUCCEEDED</code>.
     * If <code>Reporter.PRESENT_TEST_SUCCEEDED</code> is not included in the the
     * current configuration, this method prints nothing.
     *
     * @param report a <code>Report</code> that encapsulates the test succeeded event to report.
     *
     * @exception NullPointerException if <code>report</code> reference is <code>null</code>
     */
    public synchronized void testSucceeded(Report report) {

        if (report == null) {
            throw new NullPointerException("Parameter report is null.");
        }

        if (configuration.contains(Reporter.PRESENT_TEST_SUCCEEDED)) {

            String stringToReport = "<testSucceeded>\n";
            stringToReport += printReport(report);
            stringToReport += "</testSucceeded>";

            pw.println(stringToReport);
        }
    }

    /**
     * Prints information extracted from the specified <code>Report</code>
     * about a test that failed, if
     * the current configuration includes <code>Reporter.PRESENT_TEST_FAILED</code>.
     * If <code>Reporter.PRESENT_TEST_FAILED</code> is not included in the the
     * current configuration, this method prints nothing.
     *
     * @param report a <code>Report</code> that encapsulates the test failed event to report.
     *
     * @exception NullPointerException if <code>report</code> reference is <code>null</code>
     */
    public synchronized void testFailed(Report report) {

        if (report == null) {
            throw new NullPointerException("Parameter report is null.");
        }

        if (configuration.contains(Reporter.PRESENT_TEST_FAILED)) {

            String stringToReport = "<testFailed>\n";
            stringToReport += printReport(report);
            stringToReport += "</testFailed>";

            pw.println(stringToReport);
        }
    }

    /**
     * Prints information extracted from the specified <code>Report</code>, if
     * the current configuration includes <code>Reporter.PRESENT_INFO_PROVIDED</code>.
     * If <code>Reporter.PRESENT_INFO_PROVIDED</code> is not included in the the
     * current configuration, this method prints nothing.
     *
     * @param report a <code>Report</code> that encapsulates the event to report.
     *
     * @exception NullPointerException if <code>report</code> reference is <code>null</code>
     */
    public synchronized void infoProvided(Report report) {

        if (report == null) {
            throw new NullPointerException("Parameter report is null.");
        }

        if (configuration.contains(Reporter.PRESENT_INFO_PROVIDED)) {

            String stringToReport = "<infoProvided>\n";
            stringToReport += printReport(report);
            stringToReport += "</infoProvided>";

            pw.println(stringToReport);
        }
    }

    /**
     * Prints information indicating a suite of tests is about to start executing, if
     * the current configuration includes <code>Reporter.PRESENT_SUITE_STARTING</code>.
     * If <code>Reporter.PRESENT_SUITE_STARTING</code> is not included in the the
     * current configuration, this method prints nothing.
     *
     * @param report a <code>Report</code> that encapsulates the suite starting event to report.
     *
     * @exception NullPointerException if <code>report</code> reference is <code>null</code>
     */
    public void suiteStarting(Report report) {

        if (report == null) {
            throw new NullPointerException("Parameter report is null.");
        }

        if (configuration.contains(Reporter.PRESENT_SUITE_STARTING)) {

            String stringToReport = "<suiteStarting>\n";
            stringToReport += printReport(report);
            stringToReport += "</suiteStarting>";

            pw.println(stringToReport);
        }
    }

    /**
     * Prints information indicating a suite of tests has completed executing, if
     * the current configuration includes <code>Reporter.PRESENT_SUITE_COMPLETED</code>.
     * If <code>Reporter.PRESENT_SUITE_COMPLETED</code> is not included in the the
     * current configuration, this method prints nothing.
     *
     * @param report a <code>Report</code> that encapsulates the suite completed event to report.
     * @exception NullPointerException if <code>report</code> reference is <code>null</code>
     */
    public synchronized void suiteCompleted(Report report) {

        if (report == null) {
            throw new NullPointerException("Parameter report is null.");
        }

        if (configuration.contains(Reporter.PRESENT_SUITE_COMPLETED)) {

            String stringToReport = "<suiteCompleted>\n";
            stringToReport += printReport(report);
            stringToReport += "</suiteCompleted>";

            pw.println(stringToReport);
        }
    }

    /**
     * Prints information indicating the execution of a suite of tests has aborted prior to completion, if
     * the current configuration includes <code>Reporter.PRESENT_SUITE_ABORTED</code>.
     * If <code>Reporter.PRESENT_SUITE_ABORTED</code> is not included in the the
     * current configuration, this method prints nothing.
     *
     * @param report a <code>Report</code> that encapsulates the suite aborted event to report.
     * @exception NullPointerException if <code>report</code> reference is <code>null</code>
     */
    public synchronized void suiteAborted(Report report) {

        if (report == null) {
            throw new NullPointerException("Parameter report is null.");
        }

        if (configuration.contains(Reporter.PRESENT_SUITE_ABORTED)) {

            String stringToReport = "<suiteAborted>\n";
            stringToReport += printReport(report);
            stringToReport += "</suiteAborted>";

            pw.println(stringToReport);
        }
    }

    /**
     * Prints information indicating a runner has stopped running a suite of tests prior to completion, if
     * the current configuration includes <code>Reporter.PRESENT_RUN_STOPPED</code>.
     * If <code>Reporter.PRESENT_RUN_STOPPED</code> is not included in the the
     * current configuration, this method prints nothing.
     */
    public synchronized void runStopped() {

        if (configuration.contains(Reporter.PRESENT_RUN_STOPPED)) {

            String stringToReport = "<runStopped/>\n";
            stringToReport += "</run>";

            pw.println(stringToReport);
            pw.flush();
        }
    }

    /**
     * Prints information indicating a run has aborted prior to completion, if
     * the current configuration includes <code>Reporter.PRESENT_RUN_ABORTED</code>.
     * If <code>Reporter.PRESENT_RUN_ABORTED</code> is not included in the the
     * current configuration, this method prints nothing.
     *
     * @param report a <code>Report</code> that encapsulates the suite aborted event to report.
     * @exception NullPointerException if <code>report</code> reference is <code>null</code>
     */
    public synchronized void runAborted(Report report) {

        if (report == null) {
            throw new NullPointerException("Parameter report is null.");
        }

        if (configuration.contains(Reporter.PRESENT_RUN_ABORTED)) {

            String stringToReport = "<runAborted>\n";
            stringToReport += printReport(report);
            stringToReport += "<runAborted/>\n";
            stringToReport += "</run>";

            pw.println(stringToReport);
            pw.flush();
        }
    }


    /**
     * Configures this <code>XMLReporter</code>. If the specified <code>configuration</code>
     * set is zero size, the <code>XMLReporter</code> will be configured to its
     * default configuration. (The default configuration is described in the main
     * comment for class <code>XMLReporter</code>.)
     *
     * @param configs set of <code>Config</code> objects that indicate the new
     *     configuration for this <code>XMLReporter</code>
     *
     * @exception NullPointerException if <code>configuration</code> reference is <code>null</code>
     * @exception IllegalArgumentException if <code>configuration</code> set contains any objects
     *    whose class isn't <code>org.suiterunner.Config</code>
     */
    @SuppressWarnings("unchecked")
    public synchronized void setConfiguration(Set configs) {

        if (configs == null) {
            throw new NullPointerException("Parameter configs is null.");
        }

        for (Iterator it = configs.iterator(); it.hasNext();) {

            Object o = it.next();

            if (!(o instanceof Character)) {

                throw new IllegalArgumentException("Passed object is not a Character.");
            }

            if (!validConfigChars.contains(o)) {

                throw new IllegalArgumentException("Passed object is not a valid configuration Character.");
            }
        }

        if (configs.size() == 0) {
            // If passsed Set is empty, reset this Reporter to its default
            // configuration, which is to report everything.
            this.configuration = new HashSet(validConfigChars);
        }
        else {
            this.configuration = new HashSet(configs);
        }
    }

    /**
     * Prints information indicating a run has completed, if
     * the current configuration includes <code>Reporter.PRESENT_RUN_COMPLETED</code>.
     * If <code>Reporter.PRESENT_RUN_COMPLETED</code> is not included in the the
     * current configuration, this method prints nothing.
     */
    public synchronized void runCompleted() {

        if (configuration.contains(Reporter.PRESENT_RUN_COMPLETED)) {

            pw.println("</run>");
            pw.flush();
        }
    }

    /**
     * Does nothing, because this object holds no finite non-memory resources.
     */
    public void dispose() {
    }

    /*
     * Replace &, <, >, ", and ', in passed raw String with their
     * XML entity representation.
     */
    private String insertEntities(String raw) {

        if (raw == null) {
            throw new NullPointerException("Parameter raw is null.");
        }

        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < raw.length(); ++i) {
            char c = raw.charAt(i);

            if (c == '&') {
                buf.append("&amp;");
            }
            else if (c == '<') {
                buf.append("&lt;");
            }
            else if (c == '>') {
                buf.append("&gt;");
            }
            else if (c == '"') {
                buf.append("&quot;");
            }
            else if (c == '\'') {
                buf.append("&apos;");
            }
            else {
                buf.append(c);
            }
        }

        return buf.toString();
    }

    /*
     * Print a Report to the standard output.
     */
    private String printReport(Report report) {

        if (report == null) {
            throw new NullPointerException("Parameter report is null.");
        }

        String name = insertEntities(report.getName());
        String message = insertEntities(report.getMessage());
        String dateString = insertEntities(report.getDate().toString());
        String sourceName = "Sorry, Frank and Bill decided to delete Source";
        String threadName = insertEntities((report.getThreadName()));
        Throwable throwable = report.getThrowable();

        String reportString = "    <name>" + name + "</name>\n";
        reportString += "    <message>" + message + "</message>\n";
        reportString += "    <date>" + dateString + "</date>\n";
        reportString += "    <source>" + sourceName + "</source>\n";
        reportString += "    <thread>" + threadName + "</thread>\n";
        if (throwable != null) {
            CharArrayWriter caw = new CharArrayWriter();
            PrintWriter capw = new PrintWriter(caw);
            throwable.printStackTrace(capw);
            capw.close();
            String stackTrace = caw.toString();
            reportString += "    <throwable>\n";
            reportString += "        " + insertEntities(stackTrace);
            reportString += "    </throwable>\n";
        }
        return reportString;
    }
}
