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

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.HashSet;
import java.util.Set;

import org.apache.harmony.security.cert.TestUtils;
import org.apache.harmony.security.test.PerformanceTest;


/**
 * Tests for <code>PKIXBuilderParameters</code> fields and methods
 * 
 */
public class PKIXBuilderParametersTest extends PerformanceTest {
    private static final int DEFAULT_MAX_PATH_LEN = 5;
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
     * Constructor for PKIXBuilderParametersTest.
     * @param name
     */
    public PKIXBuilderParametersTest(String name) {
        super(name);
    }

    /**
     * Test #1 for <code>PKIXBuilderParameters(Set, CertSelector)</code>
     * constructor<br>
     * Assertion: creates an instance of <code>PKIXBuilderParameters</code>
     * @throws InvalidAlgorithmParameterException
     */
    public final void testPKIXBuilderParametersSetCertSelector01()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        // both parameters are valid and non-null
        PKIXParameters p =
            new PKIXBuilderParameters(taSet, new X509CertSelector());
        assertTrue("instanceOf", p instanceof PKIXBuilderParameters);
        assertNotNull("certSelector", p.getTargetCertConstraints());
    }

    /**
     * Test #2 for <code>PKIXBuilderParameters(Set, CertSelector)</code>
     * constructor<br>
     * Assertion: creates an instance of <code>PKIXBuilderParameters</code>
     * @throws InvalidAlgorithmParameterException
     */
    public final void testPKIXBuilderParametersSetCertSelector02()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        // both parameters are valid but CertSelector is null
        PKIXParameters p = new PKIXBuilderParameters(taSet, null);
        assertTrue("instanceOf", p instanceof PKIXBuilderParameters);
        assertNull("certSelector", p.getTargetCertConstraints());
    }

    /**
     * Test #3 for <code>PKIXBuilderParameters(Set, CertSelector)</code>
     * constructor<br>
     * Assertion: ... the <code>Set</code> is copied to protect against
     * subsequent modifications
     * @throws InvalidAlgorithmParameterException
     */
    public final void testPKIXBuilderParametersSetCertSelector03()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        HashSet originalSet = (HashSet)taSet;
        HashSet originalSetCopy = (HashSet)originalSet.clone();
        // create test object using originalSet 
        PKIXBuilderParameters pp =
            new PKIXBuilderParameters(originalSetCopy, null);
        // modify originalSet
        originalSetCopy.clear();
        // check that test object's internal state
        // has not been affected by the above modification
        Set returnedSet = pp.getTrustAnchors();
        assertEquals(originalSet, returnedSet);
    }

    /**
     * Test #4 for <code>PKIXBuilderParameters(Set, CertSelector)</code>
     * constructor<br>
     * Assertion: <code>NullPointerException</code> -
     * if the specified <code>Set</code> is null
     */
    public final void testPKIXBuilderParametersSetCertSelector04() {
        boolean npeHasBeenThrown = false;
        try {
            // pass null
            PKIXBuilderParameters pp =
                new PKIXBuilderParameters((Set)null, null);
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                npeHasBeenThrown = true;
            }
            logln(getName() + ": " + e);
        }
        assertTrue(npeHasBeenThrown);
    }

    /**
     * Test #5 for <code>PKIXBuilderParameters(Set, CertSelector)</code>
     * constructor<br>
     * Assertion: <code>InvalidAlgorithmParameterException</code> -
     * if the specified <code>Set</code> is empty
     * (<code>trustAnchors.isEmpty() == true</code>)
     */
    public final void testPKIXBuilderParametersSetCertSelector05() {
        boolean iapeHasBeenThrown = false;
        try {
            // use empty set
            PKIXBuilderParameters pp =
                new PKIXBuilderParameters(new HashSet(), null);
        } catch (Exception e) {
            if (e instanceof InvalidAlgorithmParameterException) {
                iapeHasBeenThrown = true;
            }
            logln(getName() + ": " + e);
        }
        assertTrue(iapeHasBeenThrown);
    }

    /**
     * Test #6 for <code>PKIXBuilderParameters(Set, CertSelector)</code>
     * constructor<br>
     * Assertion: <code>ClassCastException</code> -
     * if any of the elements in the <code>Set</code> are not of type
     * <code>java.security.cert.TrustAnchor</code>
     */
    public final void testPKIXBuilderParametersSetCertSelector06() {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        // add wrong object to valid set
        assertTrue(taSet.add(new Object()));
        boolean cceHasBeenThrown = false;
        try {
            PKIXBuilderParameters pp = new PKIXBuilderParameters(taSet, null);
        } catch (Exception e) {
            if (e instanceof ClassCastException) {
                cceHasBeenThrown = true;
            }
            logln(getName() + ": " + e);
        }
        assertTrue(cceHasBeenThrown);
    }

    /**
     * Test #1 for <code>PKIXBuilderParameters(KeyStore, CertSelector)</code>
     * constructor<br>
     * Assertion: creates an instance of <code>PKIXBuilderParameters</code>
     * @throws InvalidAlgorithmParameterException
     * @throws KeyStoreException
     */
    public final void testPKIXBuilderParametersKeyStoreCertSelector01()
        throws KeyStoreException,
               InvalidAlgorithmParameterException {
        KeyStore ks = TestUtils.getKeyStore(true, TestUtils.TRUSTED);
        if (ks == null) {
            fail(getName() + ": PASSED (could not create test KeyStore)");
            return;
        }
        // both parameters are valid and non-null
        PKIXParameters p =
            new PKIXBuilderParameters(ks, new X509CertSelector());
        assertTrue("instanceOf", p instanceof PKIXBuilderParameters);
        assertNotNull("certSelector", p.getTargetCertConstraints());
    }

    /**
     * Test #2 for <code>PKIXBuilderParameters(KeyStore, CertSelector)</code>
     * constructor<br>
     * Assertion: creates an instance of <code>PKIXBuilderParameters</code>
     * @throws InvalidAlgorithmParameterException
     * @throws KeyStoreException
     */
    public final void testPKIXBuilderParametersKeyStoreCertSelector02()
        throws KeyStoreException,
               InvalidAlgorithmParameterException {
        KeyStore ks = TestUtils.getKeyStore(true, TestUtils.TRUSTED);
        if (ks == null) {
            fail(getName() + ": PASSED (could not create test KeyStore)");
            return;
        }
        // both parameters are valid but CertSelector is null
        PKIXParameters p =
            new PKIXBuilderParameters(ks, null);
        assertTrue("instanceOf", p instanceof PKIXBuilderParameters);
        assertNull("certSelector", p.getTargetCertConstraints());
    }

    /**
     * Test #3 for <code>PKIXBuilderParameters(KeyStore, CertSelector)</code>
     * constructor<br>
     * Assertion: Only keystore entries that contain trusted
     * <code>X509Certificates</code> are considered; all other
     * certificate types are ignored
     * @throws InvalidAlgorithmParameterException
     * @throws KeyStoreException
     */
    public final void testPKIXBuilderParametersKeyStoreCertSelector03()
        throws KeyStoreException,
               InvalidAlgorithmParameterException {
        KeyStore ks = TestUtils.getKeyStore(true, TestUtils.TRUSTED_AND_UNTRUSTED);
        if (ks == null) {
            fail(getName() + ": PASSED (could not create test KeyStore)");
            return;
        }
        // both parameters are valid but CertSelector is null
        PKIXParameters p =
            new PKIXBuilderParameters(ks, null);
        assertTrue("instanceof", p instanceof PKIXBuilderParameters);
        assertEquals("size", 1, p.getTrustAnchors().size());
    }

    /**
     * Test #4 for <code>PKIXBuilderParameters(KeyStore, CertSelector)</code>
     * constructor<br>
     * Assertion: <code>NullPointerException</code> -
     * if the <code>keystore</code> is <code>null</code>
     */
    public final void testPKIXBuilderParametersKeyStoreCertSelector04() {
        boolean npeHasBeenThrown = false;
        try {
            // pass null
            PKIXBuilderParameters pp =
                new PKIXBuilderParameters((KeyStore)null, null);
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                npeHasBeenThrown = true;
            }
            logln(getName() + ": " + e);
        }
        assertTrue(npeHasBeenThrown);
    }

    /**
     * Test #5 for <code>PKIXBuilderParameters(KeyStore, CertSelector)</code>
     * constructor<br>
     * Assertion: <code>KeyStoreException</code> -
     * if the <code>keystore</code> has not been initialized
     */
    public final void testPKIXBuilderParametersKeyStoreCertSelector05() {
        KeyStore ks = TestUtils.getKeyStore(false, 0);
        if (ks == null) {
            fail(getName() + ": PASSED (could not create test KeyStore)");
            return;
        }
        boolean kseHasBeenThrown = false;
        try {
            // pass not initialized KeyStore
            PKIXBuilderParameters pp = new PKIXBuilderParameters(ks, null);
        } catch (Exception e) {
            if (e instanceof KeyStoreException) {
                kseHasBeenThrown = true;
            }
            logln(getName() + ": " + e);
        }
        assertTrue(kseHasBeenThrown);
    }

    /**
     * Test #6 for <code>PKIXBuilderParameters(KeyStore, CertSelector)</code>
     * constructor<br>
     * Assertion: <code>InvalidAlgorithmParameterException</code> -
     * if the <code>keystore</code> does not contain at least one
     * trusted certificate entry
     */
    public final void testPKIXBuilderParametersKeyStoreCertSelector06() {
        KeyStore ks = TestUtils.getKeyStore(true, TestUtils.UNTRUSTED);
        if (ks == null) {
            fail(getName() + ": PASSED (could not create test KeyStore)");
            return;
        }
        boolean iapeHasBeenThrown = false;
        try {
            // pass KeyStore that does not contain trusted certificates
            PKIXBuilderParameters pp = new PKIXBuilderParameters(ks, null);
        } catch (Exception e) {
            if (e instanceof InvalidAlgorithmParameterException) {
                iapeHasBeenThrown = true;
            }
            logln(getName() + ": " + e);
        }
        assertTrue(iapeHasBeenThrown);
    }

    /**
     * Test for <code>getMaxPathLength()</code> method<br>
     * Assertion: The default maximum path length, if not specified, is 5
     * @throws KeyStoreException
     * @throws InvalidAlgorithmParameterException
     */
    public final void testGetMaxPathLength01()
        throws KeyStoreException,
               InvalidAlgorithmParameterException {
        KeyStore ks = TestUtils.getKeyStore(true, TestUtils.TRUSTED);
        if (ks == null) {
            fail(getName() + ": PASSED (could not create test KeyStore)");
            return;
        }
        PKIXBuilderParameters p = new PKIXBuilderParameters(ks, null);
        assertEquals(DEFAULT_MAX_PATH_LEN, p.getMaxPathLength());
    }

    /**
     * Test #1 for <code>setMaxPathLength(int)</code> method<br>
     * Assertion: sets the maximum number of non-self-signed certificates
     * in the cert path
     * @throws KeyStoreException
     * @throws InvalidAlgorithmParameterException
     */
    public final void testSetMaxPathLength01()
        throws KeyStoreException,
               InvalidAlgorithmParameterException {
        KeyStore ks = TestUtils.getKeyStore(true, TestUtils.TRUSTED);
        if (ks == null) {
            fail(getName() + ": PASSED (could not create test KeyStore)");
            return;
        }
        // all these VALID maxPathLength values must be
        // set (and get) without exceptions
        int[] testPathLength = new int[] {-1, 0, 1, 999, Integer.MAX_VALUE};
        for (int i=0; i<testPathLength.length; i++) {
            PKIXBuilderParameters p = new PKIXBuilderParameters(ks, null);
            p.setMaxPathLength(testPathLength[i]);
            assertEquals("i="+i, testPathLength[i], p.getMaxPathLength());
        }
    }

    /**
     * Test #2 for <code>setMaxPathLength(int)</code> method<br>
     * Assertion: throws InvalidParameterException if parameter is
     * less than -1
     * @throws InvalidAlgorithmParameterException
     * @throws KeyStoreException
     */
    public final void testSetMaxPathLength02()
        throws KeyStoreException,
               InvalidAlgorithmParameterException {
        KeyStore ks = TestUtils.getKeyStore(true, TestUtils.TRUSTED);
        if (ks == null) {
            fail(getName() + ": PASSED (could not create test KeyStore)");
            return;
        }
        PKIXBuilderParameters p = new PKIXBuilderParameters(ks, null);
        boolean ipeHasBeenThrown = false;
        try {
            // pass parameter less than -1
            p.setMaxPathLength(Integer.MIN_VALUE);
        } catch (Exception e) {
            if (e instanceof InvalidParameterException) {
                ipeHasBeenThrown = true;
            }
            logln(getName() + ": " + e);
        }
        assertTrue(ipeHasBeenThrown);
    }

    /**
     * Test for <code>toString()</code> method<br>
     * Assertion: returns string describing this object
     * @throws InvalidAlgorithmParameterException
     * @throws KeyStoreException
     */
    public final void testToString()
        throws KeyStoreException,
               InvalidAlgorithmParameterException {
        KeyStore ks = TestUtils.getKeyStore(true,TestUtils.TRUSTED_AND_UNTRUSTED);
        if (ks == null) {
            fail(getName() + ": PASSED (could not create test KeyStore)");
            return;
        }
        PKIXBuilderParameters p =
            new PKIXBuilderParameters(ks, new X509CertSelector());
        String rep = p.toString();
        logln(getName() + ": " + rep);
        assertNotNull(rep);
    }

}
