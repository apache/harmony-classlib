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

import java.security.ProviderException;

import org.apache.harmony.security.test.PerformanceTest;


/**
 * Tests for <code>ProviderException</code> class constructors and methods.
 * 
 */
public class ProviderExceptionTest extends PerformanceTest {

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
     * Constructor for ProviderExceptionTests.
     * 
     * @param arg0
     */
    public ProviderExceptionTest(String arg0) {
        super(arg0);
    }

    private static String[] msgs = {
            "",
            "Check new message",
            "Check new message Check new message Check new message Check new message Check new message" };

    private static Throwable tCause = new Throwable("Throwable for exception");

    private static String errNotExc = "Exception is not ProviderException";

    static String createErr(Exception tE, Exception eE) {
        return "ProviderException: ".concat(tE.toString()).concat(
                " is not equal to caught exception: ").concat(eE.toString());
    }

    /**
     * Test for <code>ProviderException()</code> constructor Assertion:
     * constructs ProviderException with no detail message
     */
    public void testProviderException01() {
        logln("==test_01: ProviderException==");

        ProviderException tE = new ProviderException();
        assertTrue(errNotExc, tE instanceof ProviderException);
        assertNull("getMessage() must return null.", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
        try {
            throw tE;
        } catch (Exception e) {
            assertTrue(createErr(tE, e), tE.equals(e));
        }
    }

    /**
     * Test for <code>ProviderException(String)</code> constructor Assertion:
     * constructs ProviderException with detail message msg. Parameter
     * <code>msg</code> is not null.
     */
    public void testProviderException02() {
        logln("==test_02: ProviderException==");

        ProviderException tE;
        for (int i = 0; i < msgs.length; i++) {
            tE = new ProviderException(msgs[i]);
            assertTrue(errNotExc.concat(" (msg: ").concat(msgs[i]).concat(")"),
                    tE instanceof ProviderException);
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
     * Test for <code>ProviderException(String)</code> constructor Assertion:
     * constructs ProviderException when <code>msg</code> is null
     */
    public void testProviderException03() {
        logln("==test_03: ProviderException==");

        String msg = null;
        ProviderException tE = new ProviderException(msg);
        assertTrue(errNotExc, tE instanceof ProviderException);
        assertNull("getMessage() must return null.", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
        try {
            throw tE;
        } catch (Exception e) {
            assertTrue(createErr(tE, e), tE.equals(e));
        }
    }

    /**
     * Test for <code>ProviderException(Throwable)</code> constructor
     * Assertion: constructs ProviderException when <code>cause</code> is null
     */
    public void testProviderException04() {
        logln("==test_04: ProviderException==");

        Throwable cause = null;
        ProviderException tE = new ProviderException(cause);
        assertTrue(errNotExc, tE instanceof ProviderException);
        assertNull("getMessage() must return null.", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
        try {
            throw tE;
        } catch (Exception e) {
            assertTrue(createErr(tE, e), tE.equals(e));
        }
    }

    /**
     * Test for <code>ProviderException(Throwable)</code> constructor
     * Assertion: constructs ProviderException when <code>cause</code> is not
     * null
     */
    public void testProviderException05() {
        logln("==test_05: ProviderException==");

        ProviderException tE = new ProviderException(tCause);
        assertTrue(errNotExc, tE instanceof ProviderException);
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
     * Test for <code>ProviderException(String, Throwable)</code> constructor
     * Assertion: constructs ProviderException when <code>cause</code> is null
     * <code>msg</code> is null
     */
    public void testProviderException06() {
        logln("==test_06: ProviderException==");

        ProviderException tE = new ProviderException(null, null);
        assertTrue(errNotExc, tE instanceof ProviderException);
        assertNull("getMessage() must return null", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
        try {
            throw tE;
        } catch (Exception e) {
            assertTrue(createErr(tE, e), tE.equals(e));
        }
    }

    /**
     * Test for <code>ProviderException(String, Throwable)</code> constructor
     * Assertion: constructs ProviderException when <code>cause</code> is null
     * <code>msg</code> is not null
     */
    public void testProviderException07() {
        logln("==test_07: ProviderException==");

        ProviderException tE;
        for (int i = 0; i < msgs.length; i++) {
            tE = new ProviderException(msgs[i], null);
            assertTrue(errNotExc.concat(" (msg: ").concat(msgs[i]).concat(")"),
                    tE instanceof ProviderException);
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
     * Test for <code>ProviderException(String, Throwable)</code> constructor
     * Assertion: constructs ProviderException when <code>cause</code> is not
     * null <code>msg</code> is null
     */
    public void testProviderException08() {
        logln("==test_08: ProviderException==");

        ProviderException tE = new ProviderException(null, tCause);
        assertTrue(errNotExc, tE instanceof ProviderException);
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
     * Test for <code>ProviderException(String, Throwable)</code> constructor
     * Assertion: constructs ProviderException when <code>cause</code> is not
     * null <code>msg</code> is not null
     */
    public void testProviderException09() {
        logln("==test_09: ProviderException==");

        ProviderException tE;
        for (int i = 0; i < msgs.length; i++) {
            tE = new ProviderException(msgs[i], tCause);
            assertTrue(errNotExc.concat(" (msg: ").concat(msgs[i]).concat(")"),
                    tE instanceof ProviderException);
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