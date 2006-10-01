/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
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

package javax.security.sasl;


import junit.framework.TestCase;

/**
 * Tests for constructors and methods of SaslException class
 * 
 */
public class SaslExceptionTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(SaslExceptionTest.class);
    }

    /**
     * Constructor for SaslExceptionTests.
     * 
     * @param arg0
     */
    public SaslExceptionTest(String arg0) {
        super(arg0);
    }

    static String[] msgs = {
            "",
            "Check new message",
            "Check new message Check new message Check new message Check new message Check new message" };

    private static final Throwable[] thUpd = { 
            new Exception("New exception"),
            new Exception(), 
            new Exception("exception", new Throwable()),
            new Throwable("throwable", new Throwable("New throwable")),
            new Exception(new Exception("Another exception"))
    };
 
    static Throwable tCause = new Throwable("Throwable for exception");

    static String createErr(Exception tE, Exception eE) {
        return "SaslException: ".concat(tE.toString()).concat(
                " is not equal to caught exception: ").concat(eE.toString());
    }

    /**
     * Test for <code>SaslException()</code> constructor 
     * Assertion: constructs SaslException with null message and 
     * null root exception. 
     */
    public void testSaslException01() {
        SaslException tE;
        tE = new SaslException();
        assertNull("getMessage() must return null", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
    }

    /**
     * Test for <code>SaslException(String detail)</code> constructor 
     * Assertion:
     * constructs SaslException with defined detail message. 
     * Parameter <code>detail</code> is not null.
     */
    public void testSaslException02() {
        SaslException tE;
        for (String element : msgs) {
            tE = new SaslException(element);
            assertEquals("getMessage() must return: ".concat(element), tE
                    .getMessage(), element);
            assertNull("getCause() must return null", tE.getCause());
            try {
                throw tE;
            } catch (Exception e) {
                assertTrue(createErr(tE, e), tE.equals(e));
            }
        }
    }

    /**
     * Test for <code>SaslException(String detail)</code> constructor 
     * Assertion: constructs SaslException when <code>detail</code> is null
     */
    public void testSaslException03() {
        String msg = null;
        SaslException tE = new SaslException(msg);
        assertNull("getMessage() must return null.", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
        try {
            throw tE;
        } catch (Exception e) {
            assertTrue(createErr(tE, e), tE.equals(e));
        }
    }

    /**
     * Test for <code>SaslException(String detail, Throwable ex)</code> constructor
     * Assertion: constructs SaslException when <code>ex</code> is null
     * <code>detail</code> is null
     */
    public void testSaslException04() {
        SaslException tE = new SaslException(null, null);
        assertNull("getMessage() must return null", tE.getMessage());
        assertNull("getCause() must return null", tE.getCause());
        try {
            throw tE;
        } catch (Exception e) {
            assertTrue(createErr(tE, e), tE.equals(e));
        }
    }

    /**
     * Test for <code>SaslException(String detail, Throwable ex)</code> constructor
     * Assertion: constructs SaslException when <code>ex</code> is null
     * <code>detail</code> is not null
     */
    public void testSaslException05() {
        SaslException tE;
        for (String element : msgs) {
            tE = new SaslException(element, null);
            assertEquals("getMessage() must return: ".concat(element), tE
                    .getMessage(), element);
            assertNull("getCause() must return null", tE.getCause());
            try {
                throw tE;
            } catch (Exception e) {
                assertTrue(createErr(tE, e), tE.equals(e));
            }
        }
    }

    /**
     * Test for <code>SaslException(String detail, Throwable  ex)</code> constructor
     * Assertion: constructs SaslException when <code>ex</code> is not null
     * <code>detail</code> is null
     */
    public void testSaslException06() {        
        SaslException tE = new SaslException(null, tCause);
        if (tE.getMessage() != null) {
            String toS = tCause.toString();
            String getM = tE.getMessage();
            assertTrue("getMessage() must should ".concat(toS), (getM
                    .indexOf(toS) != -1));
        }
        // SaslException is subclass of IOException, but IOException has not
        // constructors with Throwable parameters
        if (tE.getCause() != null) {
            //	assertNotNull("getCause() must not return null", tE.getCause());
            assertEquals("getCause() must return ".concat(tCause.toString()),
                    tE.getCause(), tCause);
        }
        try {
            throw tE;
        } catch (Exception e) {
            assertTrue(createErr(tE, e), tE.equals(e));
        }
    }

    /**
     * Test for <code>SaslException(String detail, Throwable ex)</code> constructor
     * Assertion: constructs SaslException when <code>ex</code> is not null
     * <code>detail</code> is not null
     */
    public void testSaslException07() {
        SaslException tE;
        for (String element : msgs) {
            tE = new SaslException(element, tCause);
            String getM = tE.getMessage();
            String toS = tCause.toString();
            if (element.length() > 0) {
                assertTrue("getMessage() must contain ".concat(element), getM
                        .indexOf(element) != -1);
                if (!getM.equals(element)) {
                    assertTrue("getMessage() should contain ".concat(toS), getM
                            .indexOf(toS) != -1);
                }
            }
            // SaslException is subclass of IOException, but IOException has not
            // constructors with Throwable parameters
            if (tE.getCause() != null) {
                //	assertNotNull("getCause() must not return null",
                // tE.getCause());
                assertEquals("getCause() must return "
                        .concat(tCause.toString()), tE.getCause(), tCause);
            }
            try {
                throw tE;
            } catch (Exception e) {
                assertTrue(createErr(tE, e), tE.equals(e));
            }
        }
    }
    
    /**
     * Test for <code>toString()</code> method
     * Assertion: returns not null string
     */
    public void testToString() {
        Throwable[] th = {
                null,
                new Exception(
                        "New Exception for toString() method verification"),
                new Throwable(), new Exception("exception", new Exception()) };

        SaslException eT;
        eT = new SaslException();
        assertNotNull("Incorrect null string", eT.toString());
        for (String element : msgs) {
            eT = new SaslException(element);
            assertTrue("Incorrect result string", eT.toString()
                    .indexOf(element) >= 0);

            for (Throwable element0 : th) {
                eT = new SaslException(element, element0);
                assertTrue("Incorrect result string", eT.toString().indexOf(
                        element) >= 0);
                if (element0 != null) {
                    assertTrue("Incorrect result string", eT.toString()
                            .indexOf(element0.toString()) >= 0);
                }
            }
        }
    }
    
    /**
     * Test for <code>getCause()</code> and <code>initCause(Throwable cause)</code> 
     * methods
     * Assertion: return cause
     */
    public void testInitCause01() {
        SaslException eT;
        Throwable eT1;
        eT = new SaslException();

        for (int l = 0; l < thUpd.length; l++) {
            try {
                eT1 = eT.initCause(thUpd[l]);
                assertEquals("Incorrect throwable", eT1, eT);
                assertEquals("Incorrect cause", eT.getCause(), thUpd[l]);
            } catch (IllegalStateException e) {
                if (l == 0) {
                    fail("Unexpected exception " + e);
                }
            }
        }
    }
    
    /**
     * Test for <code>getCause()</code> and <code>initCause(Throwable cause)</code> 
     * methods
     * Assertion: return cause
     */
    public void testInitCause02() {
        SaslException eT;
        Throwable eT1;
        eT = new SaslException();

        for (String element : msgs) {
            eT = new SaslException(element);

            for (int l = (thUpd.length - 1); l >= 0; l--) {
                try {
                    eT1 = eT.initCause(thUpd[l]);
                    assertEquals("Incorrect throwable", eT1, eT);
                    assertEquals("Incorrect cause", eT.getCause(), thUpd[l]);
                } catch (IllegalStateException e) {
                    if (l == (thUpd.length - 1)) {
                        fail("Unexpected exception " + e);
                    }
                }
            }
        }
    }

    /**
     * Test for <code>getCause()</code> and <code>initCause(Throwable cause)</code> 
     * methods
     * Assertion: return cause
     */
    public void testInitCause03() {
        Throwable[] th = { null,
                new Exception("Long Exception Message long exception message"),
                new Throwable(), new Exception("New msg", new Exception()) };

        SaslException eT;
        Throwable eT1;
        eT = new SaslException();

        boolean mod = false;
        for (String element : msgs) {
            for (Throwable element0 : th) {
                mod = false;
                for (Throwable element1 : thUpd) {
                    eT = new SaslException(element, element0);
                    try {
                        eT1 = eT.initCause(element1);
                        assertEquals(eT1, eT);
                        mod = true;
                        if ((element0 == null) && !mod) {
                            assertEquals("Incorrect cause", eT.getCause(),
                                    element1);
                        }
                    } catch (IllegalStateException e) {
                        if ((element0 == null) && !mod) {
                            fail("Unexpected exception: " + e);
                        }
                    }
                }
            }
        }
    }
}
