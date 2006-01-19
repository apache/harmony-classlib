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
* @author Vera Y. Petrashkova
* @version $Revision$
*/

package java.security;

import java.security.InvalidParameterException;

import org.apache.harmony.security.test.PerformanceTest;


/**
 * Tests for <code>InvalidParameterException</code> class constructors and
 * methods.
 * 
 */
public class InvalidParameterExceptionTest extends PerformanceTest {

    public static void main(String[] args) {
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Constructor for InvalidParameterExceptionTests.
     * 
     * @param arg0
     */
    public InvalidParameterExceptionTest(String arg0) {
        super(arg0);
    }

    static String[] msgs = {
            "",
            "Check new message",
            "Check new message Check new message Check new message Check new message Check new message" };

    static Throwable tCause = new Throwable("Throwable for exception");

    private static String errNotExc = "Exception is not InvalidParameterException";

    static String createErr(Exception tE, Exception eE) {
        return "InvalidParameterException: ".concat(tE.toString()).concat(
                " is not equal to caught exception: ").concat(eE.toString());
    }

    /**
     * Test for <code>InvalidParameterException()</code> constructor
     * Assertion: constructs InvalidParameterException with no detail message
     */
    public void testInvalidParameterException01() {
        logln("==test_01: InvalidParameterException==");

        InvalidParameterException tE = new InvalidParameterException();
        assertTrue(errNotExc, tE instanceof InvalidParameterException);
        assertNull("getMessage() must return null.", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
        try {
            throw tE;
        } catch (Exception e) {
            assertTrue(createErr(tE, e), tE.equals(e));
        }
    }

    /**
     * Test for <code>InvalidParameterException(String)</code> constructor
     * Assertion: constructs InvalidParameterException with detail message msg.
     * Parameter <code>msg</code> is not null.
     */
    public void testInvalidParameterException02() {
        logln("==test_02: InvalidParameterException==");

        InvalidParameterException tE;
        for (int i = 0; i < msgs.length; i++) {
            tE = new InvalidParameterException(msgs[i]);
            assertTrue(errNotExc.concat(" (msg: ").concat(msgs[i]).concat(")"),
                    tE instanceof InvalidParameterException);
            assertEquals("getMessage() must return: ".concat(msgs[i]), tE
                    .getMessage(), msgs[i]);
            assertNull("getCause() must return null", tE.getCause());
            try {
                throw tE;
            } catch (Exception e) {
                assertTrue(createErr(tE, e), tE.equals(e));
            }
        }
    }

    /**
     * Test for <code>InvalidParameterException(String)</code> constructor
     * Assertion: constructs InvalidParameterException when <code>msg</code>
     * is null
     */
    public void testInvalidParameterException03() {
        logln("==test_03: InvalidParameterException==");

        String msg = null;
        InvalidParameterException tE = new InvalidParameterException(msg);
        assertTrue(errNotExc, tE instanceof InvalidParameterException);
        assertNull("getMessage() must return null.", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
        try {
            throw tE;
        } catch (Exception e) {
            assertTrue(createErr(tE, e), tE.equals(e));
        }
    }

}