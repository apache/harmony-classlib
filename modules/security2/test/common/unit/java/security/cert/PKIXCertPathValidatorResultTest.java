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
* @author Vladimir N. Molotkov
* @version $Revision$
*/

package java.security.cert;

import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

import org.apache.harmony.security.cert.TestUtils;
import org.apache.harmony.security.test.PerformanceTest;


/**
 * Tests for <code>PKIXCertPathValidatorResult</code>
 * 
 */
public class PKIXCertPathValidatorResultTest extends PerformanceTest {
    /**
     * PublicKey stub
     */
    private static PublicKey testPublicKey = new PublicKey() {
        public String getAlgorithm() {
            return "NeverMind";
        }
        public String getFormat() {
            return "NeverMind";
        }
        public byte[] getEncoded() {
            return new byte[] {};
        }
    };

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
     * Constructor for PKIXCertPathValidatorResultTest.
     * @param name
     */
    public PKIXCertPathValidatorResultTest(String name) {
        super(name);
    }

    //
    // Tests
    //

    /**
     * Test #1 for <code>PKIXCertPathValidatorResult(TrustAnchor,
     * PolicyNode, PublicKey)</code> constructor<br>
     * Assertion: creates an instance of
     * <code>PKIXCertPathValidatorResult</code>
     * 
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public final void testPKIXCertPathValidatorResult01()
        throws InvalidKeySpecException,
               NoSuchAlgorithmException {
        TrustAnchor ta = TestUtils.getTrustAnchor();
        if (ta == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor)");
            return;
        }
        PKIXCertPathValidatorResult vr =
            new PKIXCertPathValidatorResult(
                    ta,
                    TestUtils.getPolicyTree(),
                    testPublicKey);
    }

    /**
     * Test #2 for <code>PKIXCertPathValidatorResult(TrustAnchor,
     * PolicyNode, PublicKey)</code> constructor<br>
     * Assertion: <code>NullPointerException</code> if
     * <code>TrustAnchor</code> parameter is <code>null</code>
     */
    public final void testPKIXCertPathValidatorResult02() {
        boolean npeHasBeenThrown = false;
        try {
            // pass null
            PKIXCertPathValidatorResult vr =
                new PKIXCertPathValidatorResult(
                        null,
                        TestUtils.getPolicyTree(),
                        testPublicKey);
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                npeHasBeenThrown = true;
            }
            logln(getName() + ": " + e);
        }
        assertTrue(npeHasBeenThrown);
    }

    /**
     * Test #3 for <code>PKIXCertPathValidatorResult(TrustAnchor,
     * PolicyNode, PublicKey)</code> constructor<br>
     * Assertion: <code>NullPointerException</code> if
     * <code>PublicKey</code> parameter is <code>null</code>
     */
    public final void testPKIXCertPathValidatorResult03() {
        TrustAnchor ta = TestUtils.getTrustAnchor();
        if (ta == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor)");
            return;
        }
        boolean npeHasBeenThrown = false;
        try {
            // pass null
            PKIXCertPathValidatorResult vr =
                new PKIXCertPathValidatorResult(
                        ta,
                        TestUtils.getPolicyTree(),
                        null);
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                npeHasBeenThrown = true;
            }
            logln(getName() + ": " + e);
        }
        assertTrue(npeHasBeenThrown);
    }

    /**
     * Test #4 for <code>PKIXCertPathValidatorResult(TrustAnchor,
     * PolicyNode, PublicKey)</code> constructor<br>
     * Assertion: <code>PolicyNode</code>can be <code>null</code>
     */
    public final void testPKIXCertPathValidatorResult04()
        throws InvalidKeySpecException,
               NoSuchAlgorithmException {
        TrustAnchor ta = TestUtils.getTrustAnchor();
        if (ta == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor)");
            return;
        }
        PKIXCertPathValidatorResult vr =
            new PKIXCertPathValidatorResult(
                    ta,
                    null,
                    testPublicKey);
    }

    /**
     * Test for <code>getTrustAnchor()</code> method<br>
     * Assertion: returns <code>TrustAnchor</code> (never <code>null</code>)
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public final void testGetTrustAnchor()
        throws InvalidKeySpecException,
               NoSuchAlgorithmException {
        TrustAnchor ta = TestUtils.getTrustAnchor();
        if (ta == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor)");
            return;
        }
        PKIXCertPathValidatorResult vr =
            new PKIXCertPathValidatorResult(
                    ta,
                    null,
                    testPublicKey);
        // must return the same reference passed
        // as a parameter to the constructor
        assertSame(ta, vr.getTrustAnchor());
    }

    /**
     * Test for <code>getPublicKey()</code> method<br>
     * Assertion: returns the subject's public key (never <code>null</code>)
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public final void testGetPublicKey()
        throws InvalidKeySpecException,
               NoSuchAlgorithmException {
        TrustAnchor ta = TestUtils.getTrustAnchor();
        if (ta == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor)");
            return;
        }
        PublicKey pk = testPublicKey;
        PKIXCertPathValidatorResult vr =
            new PKIXCertPathValidatorResult(
                    ta,
                    null,
                    pk);
        // must return the same reference passed
        // as a parameter to the constructor
        assertSame(pk, vr.getPublicKey());
    }

    /**
     * Test for <code>getPolicyTree()</code> method<br>
     * Assertion: returns the root node of the valid
     * policy tree or <code>null</code> if there are
     * no valid policies
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public final void testGetPolicyTree01()
        throws InvalidKeySpecException,
               NoSuchAlgorithmException {
        TrustAnchor ta = TestUtils.getTrustAnchor();
        if (ta == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor)");
            return;
        }
        // valid policy tree case;
        PolicyNode pn = TestUtils.getPolicyTree();
        PKIXCertPathValidatorResult vr =
            new PKIXCertPathValidatorResult(
                    ta,
                    pn,
                    testPublicKey);
        // must return the same reference passed
        // as a parameter to the constructor
        assertSame(pn, vr.getPolicyTree());
    }

    /**
     * Test for <code>getPolicyTree()</code> method<br>
     * Assertion: returns the root node of the valid
     * policy tree or <code>null</code> if there are
     * no valid policies
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public final void testGetPolicyTree02()
        throws InvalidKeySpecException,
               NoSuchAlgorithmException {
        TrustAnchor ta = TestUtils.getTrustAnchor();
        if (ta == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor)");
            return;
        }
        // no valid policy tree case (null)
        PKIXCertPathValidatorResult vr =
            new PKIXCertPathValidatorResult(
                    ta,
                    null,
                    testPublicKey);
        // must return the same reference passed
        // as a parameter to the constructor
        assertNull(vr.getPolicyTree());
    }

    /**
     * Test for <code>clone()</code> method<br>
     * Assertion: returns a copy of this object
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public final void testClone()
        throws InvalidKeySpecException,
               NoSuchAlgorithmException {
        TrustAnchor ta = TestUtils.getTrustAnchor();
        if (ta == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor)");
            return;
        }
        PKIXCertPathValidatorResult vr1 =
            new PKIXCertPathValidatorResult(
                    ta,
                    TestUtils.getPolicyTree(),
                    testPublicKey);
        PKIXCertPathValidatorResult vr2 =
            (PKIXCertPathValidatorResult) vr1.clone();
        // check that method makes shallow copy
        assertNotSame("notSame", vr1, vr2);
        assertSame("trustAncor", vr1.getTrustAnchor(), vr2.getTrustAnchor());
        assertSame("policyTree", vr1.getPolicyTree(), vr2.getPolicyTree());
        assertSame("publicKey", vr1.getPublicKey(), vr2.getPublicKey());
    }

    /**
     * Test #1 for <code>toString()</code> method<br>
     * Assertion: Returns a formatted string describing this object
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public final void testToString01()
        throws InvalidKeySpecException,
               NoSuchAlgorithmException {
        TrustAnchor ta = TestUtils.getTrustAnchor();
        if (ta == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor)");
            return;
        }
        PKIXCertPathValidatorResult vr =
            new PKIXCertPathValidatorResult(
                    ta,
                    TestUtils.getPolicyTree(),
                    testPublicKey);
        String rep = vr.toString();
        logln(getName() + ": " + rep);
        assertNotNull(rep);
    }

    /**
     * Test #2 for <code>toString()</code> method<br>
     * Assertion: Returns a formatted string describing this object
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public final void testToString02()
        throws InvalidKeySpecException,
               NoSuchAlgorithmException {
        TrustAnchor ta = TestUtils.getTrustAnchor();
        if (ta == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor)");
            return;
        }
        PKIXCertPathValidatorResult vr =
            new PKIXCertPathValidatorResult(
                    ta,
                    null,
                    testPublicKey);
        String rep = vr.toString();
        logln(getName() + ": " + rep);
        assertNotNull(rep);
    }

}
