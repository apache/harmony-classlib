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

import java.security.DigestException;

import org.apache.harmony.security.test.PerformanceTest;


/**
 * Tests for <code>DigestException</code> class constructors and methods.
 * 
 */
public class DigestExceptionTest extends PerformanceTest {

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
     * Constructor for DigestExceptionTests.
     * 
     * @param arg0
     */
    public DigestExceptionTest(String arg0) {
        super(arg0);
    }

    private static String[] msgs = {
            "",
            "Check new message",
            "Check new message Check new message Check new message Check new message Check new message" };

    private static Throwable tCause = new Throwable("Throwable for exception");

    private static String errNotExc = "Exception is not DigestException";

    static String createErr(Exception tE, Exception eE) {
        return "DigestException:  ".concat(tE.toString()).concat(
                " is not equal to caught exception: ").concat(eE.toString());
    }

    /**
     * Test for <code>DigestException()</code> constructor Assertion:
     * constructs DigestException with no detail message
     */
    public void testDigestException01() {
        logln("==test_01: DigestException==");

        DigestException tE = new DigestException();
        assertTrue(errNotExc, tE instanceof DigestException);
        assertNull("getMessage() must return null.", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
        try {
            throw tE;
        } catch (Exception e) {
            assertTrue(createErr(tE, e), tE.equals(e));
        }
    }

    /**
     * Test for <code>DigestException(String)</code> constructor Assertion:
     * constructs DigestException with detail message msg. Parameter
     * <code>msg</code> is not null.
     */
    public void testDigestException02() {
        logln("==test_02: DigestException==");

        DigestException tE;
        for (int i = 0; i < msgs.length; i++) {
            tE = new DigestException(msgs[i]);
            assertTrue(errNotExc.concat(" (msg: ").concat(msgs[i]).concat(")"),
                    tE instanceof DigestException);
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
     * Test for <code>DigestException(String)</code> constructor Assertion:
     * constructs DigestException when <code>msg</code> is null
     */
    public void testDigestException03() {
        logln("==test_03: DigestException==");

        String msg = null;
        DigestException tE = new DigestException(msg);
        assertTrue(errNotExc, tE instanceof DigestException);
        assertNull("getMessage() must return null.", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
        try {
            throw tE;
        } catch (Exception e) {
            assertTrue(createErr(tE, e), tE.equals(e));
        }
    }

    /**
     * Test for <code>DigestException(Throwable)</code> constructor Assertion:
     * constructs DigestException when <code>cause</code> is null
     */
    public void testDigestException04() {
        logln("==test_04: DigestException==");

        Throwable cause = null;
        DigestException tE = new DigestException(cause);
        assertTrue(errNotExc, tE instanceof DigestException);
        assertNull("getMessage() must return null.", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
        try {
            throw tE;
        } catch (Exception e) {
            assertTrue(createErr(tE, e), tE.equals(e));
        }
    }

    /**
     * Test for <code>DigestException(Throwable)</code> constructor Assertion:
     * constructs DigestException when <code>cause</code> is not null
     */
    public void testDigestException05() {
        logln("==test_05: DigestException==");

        DigestException tE = new DigestException(tCause);
        assertTrue(errNotExc, tE instanceof DigestException);
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
     * Test for <code>DigestException(String, Throwable)</code> constructor
     * Assertion: constructs DigestException when <code>cause</code> is null
     * <code>msg</code> is null
     */
    public void testDigestException06() {
        logln("==test_06: DigestException==");

        DigestException tE = new DigestException(null, null);
        assertTrue(errNotExc, tE instanceof DigestException);
        assertNull("getMessage() must return null", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
        try {
            throw tE;
        } catch (Exception e) {
            assertTrue(createErr(tE, e), tE.equals(e));
        }
    }

    /**
     * Test for <code>DigestException(String, Throwable)</code> constructor
     * Assertion: constructs DigestException when <code>cause</code> is null
     * <code>msg</code> is not null
     */
    public void testDigestException07() {
        logln("==test_07: DigestException==");

        DigestException tE;
        for (int i = 0; i < msgs.length; i++) {
            tE = new DigestException(msgs[i], null);
            assertTrue(errNotExc.concat(" (msg: ").concat(msgs[i]).concat(")"),
                    tE instanceof DigestException);
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
     * Test for <code>DigestException(String, Throwable)</code> constructor
     * Assertion: constructs DigestException when <code>cause</code> is not
     * null <code>msg</code> is null
     */
    public void testDigestException08() {
        logln("==test_08: DigestException==");

        DigestException tE = new DigestException(null, tCause);
        assertTrue(errNotExc, tE instanceof DigestException);
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
     * Test for <code>DigestException(String, Throwable)</code> constructor
     * Assertion: constructs DigestException when <code>cause</code> is not
     * null <code>msg</code> is not null
     */
    public void testDigestException09() {
        logln("==test_09: DigestException==");

        DigestException tE;
        for (int i = 0; i < msgs.length; i++) {
            tE = new DigestException(msgs[i], tCause);
            assertTrue(errNotExc.concat(" (msg: ").concat(msgs[i]).concat(")"),
                    tE instanceof DigestException);
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