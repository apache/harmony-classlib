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

package javax.crypto;

import javax.crypto.ExemptionMechanismException;

import junit.framework.TestCase;


/**
 * Tests for <code>ExemptionMechanismException</code> class constructors and
 * methods.
 * 
 */
public class ExemptionMechanismExceptionTest extends TestCase {

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
     * Constructor for ExemptionMechanismExceptionTests.
     * 
     * @param arg0
     */
    public ExemptionMechanismExceptionTest(String arg0) {
        super(arg0);
    }

    static String[] msgs = {
            "",
            "Check new message",
            "Check new message Check new message Check new message Check new message Check new message" };

    static Throwable tCause = new Throwable("Throwable for exception");

    private static String errNotExc = "Exception is not ExemptionMechanismException";

    static String createErr(Exception tE, Exception eE) {
        return "ExemptionMechanismException: ".concat(tE.toString()).concat(
                " is not equal to caught exception: ").concat(eE.toString());
    }

    /**
     * Test for <code>ExemptionMechanismException()</code> constructor
     * Assertion: constructs ExemptionMechanismException with no detail message
     */
    public void testExemptionMechanismException01() {
        ExemptionMechanismException tE = new ExemptionMechanismException();
        assertNull("getMessage() must return null.", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
        try {
            throw tE;
        } catch (Exception e) {
            assertTrue(createErr(tE, e), tE.equals(e));
        }
    }

    /**
     * Test for <code>ExemptionMechanismException(String)</code> constructor
     * Assertion: constructs ExemptionMechanismException with detail message
     * msg. Parameter <code>msg</code> is not null.
     */
    public void testExemptionMechanismException02() {
        ExemptionMechanismException tE;
        for (int i = 0; i < msgs.length; i++) {
            tE = new ExemptionMechanismException(msgs[i]);
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
     * Test for <code>ExemptionMechanismException(String)</code> constructor
     * Assertion: constructs ExemptionMechanismException when <code>msg</code>
     * is null
     */
    public void testExemptionMechanismException03() {
        String msg = null;
        ExemptionMechanismException tE = new ExemptionMechanismException(msg);
        assertNull("getMessage() must return null.", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
        try {
            throw tE;
        } catch (Exception e) {
            assertTrue(createErr(tE, e), tE.equals(e));
        }
    }

}
