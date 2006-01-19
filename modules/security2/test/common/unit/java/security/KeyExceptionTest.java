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

import java.security.KeyException;

import org.apache.harmony.security.test.PerformanceTest;


/**
 * Tests for <code>KeyException</code> class constructors and methods.
 * 
 */
public class KeyExceptionTest extends PerformanceTest {

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
     * Constructor for KeyExceptionTests.
     * 
     * @param arg0
     */
    public KeyExceptionTest(String arg0) {
        super(arg0);
    }

    private static String[] msgs = {
            "",
            "Check new message",
            "Check new message Check new message Check new message Check new message Check new message" };

    private static Throwable tCause = new Throwable("Throwable for exception");

    private static String errNotExc = "Exception is not KeyException";

    static String createErr(Exception tE, Exception eE) {
        return "KeyException: ".concat(tE.toString()).concat(
                " is not equal to caught exception: ").concat(eE.toString());
    }

    /**
     * Test for <code>KeyException()</code> constructor Assertion: constructs
     * KeyException with no detail message
     */
    public void testKeyException01() {
        logln("==test_01: KeyException==");

        KeyException tE = new KeyException();
        assertTrue(errNotExc, tE instanceof KeyException);
        assertNull("getMessage() must return null.", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
        try {
            throw tE;
        } catch (Exception e) {
            assertTrue(createErr(tE, e), tE.equals(e));
        }
    }

    /**
     * Test for <code>KeyException(String)</code> constructor Assertion:
     * constructs KeyException with detail message msg. Parameter
     * <code>msg</code> is not null.
     */
    public void testKeyException02() {
        logln("==test_02: KeyException==");

        KeyException tE;
        for (int i = 0; i < msgs.length; i++) {
            tE = new KeyException(msgs[i]);
            assertTrue(errNotExc.concat(" (msg: ").concat(msgs[i]).concat(")"),
                    tE instanceof KeyException);
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
     * Test for <code>KeyException(String)</code> constructor Assertion:
     * constructs KeyException when <code>msg</code> is null
     */
    public void testKeyException03() {
        logln("==test_03: KeyException==");

        String msg = null;
        KeyException tE = new KeyException(msg);
        assertTrue(errNotExc, tE instanceof KeyException);
        assertNull("getMessage() must return null.", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
        try {
            throw tE;
        } catch (Exception e) {
            assertTrue(createErr(tE, e), tE.equals(e));
        }
    }

    /**
     * Test for <code>KeyException(Throwable)</code> constructor Assertion:
     * constructs KeyException when <code>cause</code> is null
     */
    public void testKeyException04() {
        logln("==test_04: KeyException==");

        Throwable cause = null;
        KeyException tE = new KeyException(cause);
        assertTrue(errNotExc, tE instanceof KeyException);
        assertNull("getMessage() must return null.", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
        try {
            throw tE;
        } catch (Exception e) {
            assertTrue(createErr(tE, e), tE.equals(e));
        }
    }

    /**
     * Test for <code>KeyException(Throwable)</code> constructor Assertion:
     * constructs KeyException when <code>cause</code> is not null
     */
    public void testKeyException05() {
        logln("==test_05: KeyException==");

        KeyException tE = new KeyException(tCause);
        assertTrue(errNotExc, tE instanceof KeyException);
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
     * Test for <code>KeyException(String, Throwable)</code> constructor
     * Assertion: constructs KeyException when <code>cause</code> is null
     * <code>msg</code> is null
     */
    public void testKeyException06() {
        logln("==test_06: KeyException==");

        KeyException tE = new KeyException(null, null);
        assertTrue(errNotExc, tE instanceof KeyException);
        assertNull("getMessage() must return null", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
        try {
            throw tE;
        } catch (Exception e) {
            assertTrue(createErr(tE, e), tE.equals(e));
        }
    }

    /**
     * Test for <code>KeyException(String, Throwable)</code> constructor
     * Assertion: constructs KeyException when <code>cause</code> is null
     * <code>msg</code> is not null
     */
    public void testKeyException07() {
        logln("==test_07: KeyException==");

        KeyException tE;
        for (int i = 0; i < msgs.length; i++) {
            tE = new KeyException(msgs[i], null);
            assertTrue(errNotExc.concat(" (msg: ").concat(msgs[i]).concat(")"),
                    tE instanceof KeyException);
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
     * Test for <code>KeyException(String, Throwable)</code> constructor
     * Assertion: constructs KeyException when <code>cause</code> is not null
     * <code>msg</code> is null
     */
    public void testKeyException08() {
        logln("==test_08: KeyException==");

        KeyException tE = new KeyException(null, tCause);
        assertTrue(errNotExc, tE instanceof KeyException);
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
     * Test for <code>KeyException(String, Throwable)</code> constructor
     * Assertion: constructs KeyException when <code>cause</code> is not null
     * <code>msg</code> is not null
     */
    public void testKeyException09() {
        logln("==test_09: KeyException==");

        KeyException tE;
        for (int i = 0; i < msgs.length; i++) {
            tE = new KeyException(msgs[i], tCause);
            assertTrue(errNotExc.concat(" (msg: ").concat(msgs[i]).concat(")"),
                    tE instanceof KeyException);
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