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

import java.security.InvalidAlgorithmParameterException;

import org.apache.harmony.security.test.PerformanceTest;


/**
 * Tests for <code>InvalidAlgorithmParameterException</code> class
 * constructors and methods.
 * 
 */
public class InvalidAlgorithmParameterExceptionTest extends PerformanceTest {

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
     * Constructor for InvalidAlgorithmParameterExceptionTests.
     * 
     * @param arg0
     */
    public InvalidAlgorithmParameterExceptionTest(String arg0) {
        super(arg0);
    }

    private static String[] msgs = {
            "",
            "Check new message",
            "Check new message Check new message Check new message Check new message Check new message" };

    private static Throwable tCause = new Throwable("Throwable for exception");

    private static String errNotExc = "Exception is not InvalidAlgorithmParameterException";

    static String createErr(Exception tE, Exception eE) {
        return "InvalidAlgorithmParameterException: ".concat(tE.toString())
                .concat(" is not equal to caught exception: ").concat(
                        eE.toString());
    }

    /**
     * Test for <code>InvalidAlgorithmParameterException()</code> constructor
     * Assertion: constructs InvalidAlgorithmParameterException with no detail
     * message
     */
    public void testInvalidAlgorithmParameterException01() {
        InvalidAlgorithmParameterException tE = new InvalidAlgorithmParameterException();
        assertTrue(errNotExc, tE instanceof InvalidAlgorithmParameterException);
        assertNull("getMessage() must return null.", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
        try {
            throw tE;
        } catch (Exception e) {
            assertTrue(createErr(tE, e), tE.equals(e));
        }
    }

    /**
     * Test for <code>InvalidAlgorithmParameterException(String)</code>
     * constructor Assertion: constructs InvalidAlgorithmParameterException with
     * detail message msg. Parameter <code>msg</code> is not null.
     */
    public void testInvalidAlgorithmParameterException02() {
        InvalidAlgorithmParameterException tE;
        for (int i = 0; i < msgs.length; i++) {
            tE = new InvalidAlgorithmParameterException(msgs[i]);
            assertTrue(errNotExc.concat(" (msg: ").concat(msgs[i]).concat(")"),
                    tE instanceof InvalidAlgorithmParameterException);
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
     * Test for <code>InvalidAlgorithmParameterException(String)</code>
     * constructor Assertion: constructs InvalidAlgorithmParameterException when
     * <code>msg</code> is null
     */
    public void testInvalidAlgorithmParameterException03() {
        String msg = null;
        InvalidAlgorithmParameterException tE = new InvalidAlgorithmParameterException(
                msg);
        assertTrue(errNotExc, tE instanceof InvalidAlgorithmParameterException);
        assertNull("getMessage() must return null.", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
        try {
            throw tE;
        } catch (Exception e) {
            assertTrue(createErr(tE, e), tE.equals(e));
        }
    }

    /**
     * Test for <code>InvalidAlgorithmParameterException(Throwable)</code>
     * constructor Assertion: constructs InvalidAlgorithmParameterException when
     * <code>cause</code> is null
     */
    public void testInvalidAlgorithmParameterException04() {
        Throwable cause = null;
        InvalidAlgorithmParameterException tE = new InvalidAlgorithmParameterException(
                cause);
        assertTrue(errNotExc, tE instanceof InvalidAlgorithmParameterException);
        assertNull("getMessage() must return null.", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
        try {
            throw tE;
        } catch (Exception e) {
            assertTrue(createErr(tE, e), tE.equals(e));
        }
    }

    /**
     * Test for <code>InvalidAlgorithmParameterException(Throwable)</code>
     * constructor Assertion: constructs InvalidAlgorithmParameterException when
     * <code>cause</code> is not null
     */
    public void testInvalidAlgorithmParameterException05() {
        InvalidAlgorithmParameterException tE = new InvalidAlgorithmParameterException(
                tCause);
        assertTrue(errNotExc, tE instanceof InvalidAlgorithmParameterException);
        if (tE.getMessage() != null) {
            String toS = tCause.toString();
            String getM = tE.getMessage();
            assertTrue("getMessage() should contain ".concat(toS), (getM
                    .indexOf(toS) != -1));
        }
        assertNotNull("getCause() must not return null", tE.getCause());
        assertEquals("getCause() must return ".concat(tCause.toString()), tE
                .getCause(), tCause);
        try {
            throw tE;
        } catch (Exception e) {
            assertTrue(createErr(tE, e), tE.equals(e));
        }
    }

    /**
     * Test for
     * <code>InvalidAlgorithmParameterException(String, Throwable)</code>
     * constructor Assertion: constructs InvalidAlgorithmParameterException when
     * <code>cause</code> is null <code>msg</code> is null
     */
    public void testInvalidAlgorithmParameterException06() {
        InvalidAlgorithmParameterException tE = new InvalidAlgorithmParameterException(
                null, null);
        assertTrue(errNotExc, tE instanceof InvalidAlgorithmParameterException);
        assertNull("getMessage() must return null", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
        try {
            throw tE;
        } catch (Exception e) {
            assertTrue(createErr(tE, e), tE.equals(e));
        }
    }

    /**
     * Test for
     * <code>InvalidAlgorithmParameterException(String, Throwable)</code>
     * constructor Assertion: constructs InvalidAlgorithmParameterException when
     * <code>cause</code> is null <code>msg</code> is not null
     */
    public void testInvalidAlgorithmParameterException07() {
        InvalidAlgorithmParameterException tE;
        for (int i = 0; i < msgs.length; i++) {
            tE = new InvalidAlgorithmParameterException(msgs[i], null);
            assertTrue(errNotExc.concat(" (msg: ").concat(msgs[i]).concat(")"),
                    tE instanceof InvalidAlgorithmParameterException);
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
     * Test for
     * <code>InvalidAlgorithmParameterException(String, Throwable)</code>
     * constructor Assertion: constructs InvalidAlgorithmParameterException when
     * <code>cause</code> is not null <code>msg</code> is null
     */
    public void testInvalidAlgorithmParameterException08() {
        InvalidAlgorithmParameterException tE = new InvalidAlgorithmParameterException(
                null, tCause);
        assertTrue(errNotExc, tE instanceof InvalidAlgorithmParameterException);
        if (tE.getMessage() != null) {
            String toS = tCause.toString();
            String getM = tE.getMessage();
            assertTrue("getMessage() must should ".concat(toS), (getM
                    .indexOf(toS) != -1));
        }
        assertNotNull("getCause() must not return null", tE.getCause());
        assertEquals("getCause() must return ".concat(tCause.toString()), tE
                .getCause(), tCause);
        try {
            throw tE;
        } catch (Exception e) {
            assertTrue(createErr(tE, e), tE.equals(e));
        }
    }

    /**
     * Test for
     * <code>InvalidAlgorithmParameterException(String, Throwable)</code>
     * constructor Assertion: constructs InvalidAlgorithmParameterException when
     * <code>cause</code> is not null <code>msg</code> is not null
     */
    public void testInvalidAlgorithmParameterException09() {
        InvalidAlgorithmParameterException tE;
        for (int i = 0; i < msgs.length; i++) {
            tE = new InvalidAlgorithmParameterException(msgs[i], tCause);
            assertTrue(errNotExc.concat(" (msg: ").concat(msgs[i]).concat(")"),
                    tE instanceof InvalidAlgorithmParameterException);
            String getM = tE.getMessage();
            String toS = tCause.toString();
            if (msgs[i].length() > 0) {
                assertTrue("getMessage() must contain ".concat(msgs[i]), getM
                        .indexOf(msgs[i]) != -1);
                if (!getM.equals(msgs[i])) {
                    assertTrue("getMessage() should contain ".concat(toS), getM
                            .indexOf(toS) != -1);
                }
            }
            assertNotNull("getCause() must not return null", tE.getCause());
            assertEquals("getCause() must return ".concat(tCause.toString()),
                    tE.getCause(), tCause);

            try {
                throw tE;
            } catch (Exception e) {
                assertTrue(createErr(tE, e), tE.equals(e));
            }
        }
    }
}