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

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.harmony.security.cert.TestUtils;
import org.apache.harmony.security.test.PerformanceTest;


/**
 * Tests for <code>PKIXParameters</code> fields and methods
 * 
 */
public class PKIXParametersTest extends PerformanceTest {
    /**
     * Some valid issuer name
     */
    private final static String testIssuer =
        "CN=VM,OU=DRL Security,O=Intel,L=Novosibirsk,ST=NSO,C=RU";

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
     * Constructor for PKIXParametersTest.
     * @param name
     */
    public PKIXParametersTest(String name) {
        super(name);
    }

    //
    // Tests
    //

    /**
     * Test #1 for <code>PKIXParameters(Set)</code> constructor<br>
     * Assertion: Creates an instance of <code>PKIXParameters</code> with the
     * specified <code>Set</code> of most-trusted CAs. Each element of the set
     * is a <code>TrustAnchor</code>
     * @throws InvalidAlgorithmParameterException
     */
    public final void testPKIXParametersSet01()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        // use valid parameter
        CertPathParameters cpp = new PKIXParameters(taSet);
        assertTrue(cpp instanceof PKIXParameters);
    }

    /**
     * Test #2 for <code>PKIXParameters(Set)</code> constructor<br>
     * Assertion: ... the <code>Set</code> is copied to protect against
     * subsequent modifications
     * @throws InvalidAlgorithmParameterException
     */
    public final void testPKIXParametersSet02()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        HashSet originalSet = (HashSet)taSet;
        HashSet originalSetCopy = (HashSet)originalSet.clone();
        // create test object using originalSet 
        PKIXParameters pp = new PKIXParameters(originalSetCopy);
        // modify originalSet
        originalSetCopy.clear();
        // check that test object's internal state
        // has not been affected by the above modification
        Set returnedSet = pp.getTrustAnchors();
        assertEquals(originalSet, returnedSet);
    }

    /**
     * Test #3 for <code>PKIXParameters(Set)</code> constructor<br>
     * Assertion: <code>NullPointerException</code> -
     * if the specified <code>Set</code> is null
     */
    public final void testPKIXParametersSet03() {
        boolean npeHasBeenThrown = false;
        try {
            // pass null
            PKIXParameters pp = new PKIXParameters((Set)null);
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                npeHasBeenThrown = true;
            }
            logln(getName() + ": " + e);
        }
        assertTrue(npeHasBeenThrown);
    }

    /**
     * Test #4 for <code>PKIXParameters(Set)</code> constructor<br>
     * Assertion: <code>InvalidAlgorithmParameterException</code> -
     * if the specified <code>Set</code> is empty
     * (<code>trustAnchors.isEmpty() == true</code>)
     */
    public final void testPKIXParametersSet04() {
        boolean iapeHasBeenThrown = false;
        try {
            // use empty set
            PKIXParameters pp = new PKIXParameters(new HashSet());
        } catch (Exception e) {
            if (e instanceof InvalidAlgorithmParameterException) {
                iapeHasBeenThrown = true;
            }
            logln(getName() + ": " + e);
        }
        assertTrue(iapeHasBeenThrown);
    }

    /**
     * Test #5 for <code>PKIXParameters(Set)</code> constructor<br>
     * Assertion: <code>ClassCastException</code> -
     * if any of the elements in the <code>Set</code> are not of type
     * <code>java.security.cert.TrustAnchor</code>
     */
    public final void testPKIXParametersSet05() {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        // add wrong object to valid set
        assertTrue(taSet.add(new Object()));
        boolean cceHasBeenThrown = false;
        try {
            PKIXParameters pp = new PKIXParameters(taSet);
        } catch (Exception e) {
            if (e instanceof ClassCastException) {
                cceHasBeenThrown = true;
            }
            logln(getName() + ": " + e);
        }
        assertTrue(cceHasBeenThrown);
    }
    
    /**
     * Test #1 for <code>PKIXParameters(KeyStore)</code> constructor<br>
     * Assertion: Creates an instance of <code>PKIXParameters</code>
     * that populates the set of most-trusted CAs from the trusted
     * certificate entries contained in the specified <code>KeyStore</code>
     * @throws InvalidAlgorithmParameterException
     * @throws KeyStoreException
     */
    public final void testPKIXParametersKeyStore01()
        throws KeyStoreException,
               InvalidAlgorithmParameterException {
        KeyStore ks = TestUtils.getKeyStore(true, TestUtils.TRUSTED);
        if (ks == null) {
            fail(getName() + ": PASSED (could not create test KeyStore)");
            return;
        }
        // use valid parameter - KeyStore containing
        // only trusted X.509 certificates
        CertPathParameters cpp = new PKIXParameters(ks);
        assertTrue(cpp instanceof PKIXParameters);
    }

    /**
     * Test #2 for <code>PKIXParameters(KeyStore)</code> constructor<br>
     * Assertion: Only keystore entries that contain trusted
     * <code>X509Certificates</code> are considered; all other
     * certificate types are ignored
     * @throws InvalidAlgorithmParameterException
     * @throws KeyStoreException
     */
    public final void testPKIXParametersKeyStore02()
        throws KeyStoreException,
               InvalidAlgorithmParameterException {
        KeyStore ks = TestUtils.getKeyStore(true, TestUtils.TRUSTED_AND_UNTRUSTED);
        if (ks == null) {
            fail(getName() + ": PASSED (could not create test KeyStore)");
            return;
        }
        // use valid parameter - KeyStore containing
        // both trusted and untrusted X.509 certificates
        PKIXParameters cpp = new PKIXParameters(ks);
        assertTrue("instanceof", cpp instanceof PKIXParameters);
        assertEquals("size", 1, cpp.getTrustAnchors().size());
    }

    /**
     * Test #3 for <code>PKIXParameters(KeyStore)</code> constructor<br>
     * Assertion: <code>NullPointerException</code> -
     * if the <code>keystore</code> is <code>null</code>
     * @throws InvalidAlgorithmParameterException
     * @throws KeyStoreException
     */
    public final void testPKIXParametersKeyStore03() {
        boolean npeHasBeenThrown = false;
        try {
            // pass null
            PKIXParameters pp = new PKIXParameters((KeyStore)null);
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                npeHasBeenThrown = true;
            }
            logln(getName() + ": " + e);
        }
        assertTrue(npeHasBeenThrown);
    }

    /**
     * Test #4 for <code>PKIXParameters(KeyStore)</code> constructor<br>
     * Assertion: <code>KeyStoreException</code> -
     * if the <code>keystore</code> has not been initialized
     */
    public final void testPKIXParametersKeyStore04() {
        KeyStore ks = TestUtils.getKeyStore(false, 0);
        if (ks == null) {
            fail(getName() + ": PASSED (could not create test KeyStore)");
            return;
        }
        boolean kseHasBeenThrown = false;
        try {
            // pass not initialized KeyStore
            PKIXParameters pp = new PKIXParameters(ks);
        } catch (Exception e) {
            if (e instanceof KeyStoreException) {
                kseHasBeenThrown = true;
            }
            logln(getName() + ": " + e);
        }
        assertTrue(kseHasBeenThrown);
    }

    /**
     * Test #5 for <code>PKIXParameters(KeyStore)</code> constructor<br>
     * Assertion: <code>InvalidAlgorithmParameterException</code> -
     * if the <code>keystore</code> does not contain at least one
     * trusted certificate entry
     */
    public final void testPKIXParametersKeyStore05() {
        KeyStore ks = TestUtils.getKeyStore(true, TestUtils.UNTRUSTED);
        if (ks == null) {
            fail(getName() + ": PASSED (could not create test KeyStore)");
            return;
        }
        boolean iapeHasBeenThrown = false;
        try {
            // pass KeyStore that does not contain trusted certificates
            PKIXParameters pp = new PKIXParameters(ks);
        } catch (Exception e) {
            if (e instanceof InvalidAlgorithmParameterException) {
                iapeHasBeenThrown = true;
            }
            logln(getName() + ": " + e);
        }
        assertTrue(iapeHasBeenThrown);
    }

    /**
     * Test #1 for <code>getPolicyQualifiersRejected()</code> method<br>
     * Assertion: When a <code>PKIXParameters</code> object is created,
     * this flag is set to <code>true</code><br>
     * Assertion: returns the current value of the PolicyQualifiersRejected flag
     * @throws InvalidAlgorithmParameterException
     */
    public final void testGetPolicyQualifiersRejected()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        assertTrue(p.getPolicyQualifiersRejected());
    }

    /**
     * Test for <code>setPolicyQualifiersRejected()</code> method<br>
     * Assertion: set the new value of the
     * <code>PolicyQualifiersRejected</code> flag
     * @throws InvalidAlgorithmParameterException
     */
    public final void testSetPolicyQualifiersRejected()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        p.setPolicyQualifiersRejected(false);
        assertFalse("setFalse",p.getPolicyQualifiersRejected());
        p.setPolicyQualifiersRejected(true);
        assertTrue("setTrue",p.getPolicyQualifiersRejected());
    }

    /**
     * Test for <code>isAnyPolicyInhibited()</code> method<br>
     * Assertion: returns <code>true</code> if the any policy
     * OID is inhibited, <code>false</code> otherwise<br>
     * Assertion: By default, the any policy OID is not inhibited
     * (<code>isAnyPolicyInhibited()</code> returns false).
     * @throws InvalidAlgorithmParameterException
     */
    public final void testIsAnyPolicyInhibited()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        assertFalse(p.isAnyPolicyInhibited());
    }

    /**
     * Test for <code>setAnyPolicyInhibited()</code> method<br>
     * Assertion: sets state to determine if the any policy OID
     * should be processed if it is included in a certificate
     * @throws InvalidAlgorithmParameterException
     */
    public final void testSetAnyPolicyInhibited() 
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        p.setAnyPolicyInhibited(true);
        assertTrue("setTrue", p.isAnyPolicyInhibited());
        p.setAnyPolicyInhibited(false);
        assertFalse("setFalse", p.isAnyPolicyInhibited());
    }

    /**
     * Test for <code>isExplicitPolicyRequired()</code> method<br>
     * Assertion: returns <code>true</code> if explicit policy is required,
     * <code>false</code> otherwise<br>
     * Assertion: by default, the ExplicitPolicyRequired flag is false
     * @throws InvalidAlgorithmParameterException
     */
    public final void testIsExplicitPolicyRequired()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        assertFalse(p.isExplicitPolicyRequired());
    }

    /**
     * Test for <code>setExplicitPolicyRequired()</code> method<br>
     * Assertion: sets the ExplicitPolicyRequired flag
     * @throws InvalidAlgorithmParameterException
     */
    public final void testSetExplicitPolicyRequired() 
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        p.setExplicitPolicyRequired(true);
        assertTrue("setTrue", p.isExplicitPolicyRequired());
        p.setExplicitPolicyRequired(false);
        assertFalse("setFalse", p.isExplicitPolicyRequired());
    }

    /**
     * Test for <code>isPolicyMappingInhibited()</code> method<br>
     * Assertion: returns true if policy mapping is inhibited, false otherwise
     * Assertion: by default, policy mapping is not inhibited (the flag is false)
     * @throws InvalidAlgorithmParameterException
     */
    public final void testIsPolicyMappingInhibited()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        assertFalse(p.isPolicyMappingInhibited());
    }

    /**
     * Test for <code>setPolicyMappingInhibited()</code> method<br>
     * Assertion: sets the PolicyMappingInhibited flag
     * @throws InvalidAlgorithmParameterException
     */
    public final void testSetPolicyMappingInhibited()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        p.setPolicyMappingInhibited(true);
        assertTrue("setTrue", p.isPolicyMappingInhibited());
        p.setPolicyMappingInhibited(false);
        assertFalse("setFalse", p.isPolicyMappingInhibited());
    }

    /**
     * Test for <code>isPolicyMappingInhibited()</code> method<br>
     * Assertion: returns the current value of the RevocationEnabled flag
     * Assertion: when a <code>PKIXParameters</code> object is created,
     * this flag is set to true
     * @throws InvalidAlgorithmParameterException
     */
    public final void testIsRevocationEnabled()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        assertTrue(p.isRevocationEnabled());
    }

    /**
     * Test for <code>isPolicyMappingInhibited()</code> method<br>
     * Assertion: sets the RevocationEnabled flag
     * @throws InvalidAlgorithmParameterException
     */
    public final void testSetRevocationEnabled()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        p.setRevocationEnabled(false);
        assertFalse("setFalse", p.isRevocationEnabled());
        p.setRevocationEnabled(true);
        assertTrue("setTrue", p.isRevocationEnabled());
    }

    /**
     * Test for <code>getSigProvider()</code> method<br>
     * Assertion: returns the signature provider's name,
     * or null if not set
     * @throws InvalidAlgorithmParameterException
     */
    public final void testGetSigProvider()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        assertNull("not set", p.getSigProvider());
        p.setSigProvider("Some Provider");
        assertNotNull("set", p.getSigProvider());
    }

    /**
     * Test for <code>setSigProvider(String)</code> method<br>
     * Assertion: sets the signature provider's name
     */
    public final void testSetSigProvider()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        String sigProviderName = "Some Provider";
        p.setSigProvider(sigProviderName);
        assertTrue("set", sigProviderName.equals(p.getSigProvider()));
        p.setSigProvider(null);
        assertNull("unset", p.getSigProvider());
    }

    /**
     * Test #1 for <code>getTargetCertConstraints()</code> method<br>
     * Assertion: returns a <code>CertSelector</code> specifying
     * the constraints on the target certificate (or <code>null</code>)
     * @throws InvalidAlgorithmParameterException
     */
    public final void testGetTargetCertConstraints01()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        assertNull(p.getTargetCertConstraints());
    }

    /**
     * Test #2 for <code>getTargetCertConstraints()</code> method<br>
     * Assertion: note that the <code>CertSelector</code> returned
     * is cloned to protect against subsequent modifications
     * @throws InvalidAlgorithmParameterException
     * @throws IOException
     */
    public final void testGetTargetCertConstraints02()
        throws InvalidAlgorithmParameterException,
               IOException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        X509CertSelector x509cs = new X509CertSelector();
        PKIXParameters p = new PKIXParameters(taSet);
        p.setTargetCertConstraints(x509cs);
        // get cert selector
        X509CertSelector cs1 = (X509CertSelector)p.getTargetCertConstraints();
        // modify returned selector
        cs1.setIssuer(testIssuer);
        // get cert selector again
        X509CertSelector cs2 = (X509CertSelector)p.getTargetCertConstraints();
        // check that selector is not the same
        assertNotSame("notTheSame", cs1, cs2);
        // check that selector's internal state has
        // not been changed by above modification
        assertFalse("stateNotChanged", testIssuer.equals(cs2.getIssuerAsString()));
    }

    /**
     * Test for <code>setTargetCertConstraints(CertSelector)</code> method<br>
     * Assertion: sets the required constraints on the target certificate.
     * The constraints are specified as an instance of CertSelector<br>
     * Assertion: ... If <code>null</code>, no constraints are defined
     * @throws IOException
     * @throws InvalidAlgorithmParameterException
     */
    public final void testSetTargetCertConstraints01()
        throws IOException,
               InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        X509CertSelector x509cs = new X509CertSelector();
        x509cs.setIssuer(testIssuer);
        PKIXParameters p = new PKIXParameters(taSet);
        p.setTargetCertConstraints(x509cs);
        assertEquals("set",
          testIssuer,
          ((X509CertSelector)p.getTargetCertConstraints()).getIssuerAsString());
        p.setTargetCertConstraints(null);
        assertNull("unset", p.getTargetCertConstraints());
    }

    /**
     * Test #2 for <code>setTargetCertConstraints(CertSelector)</code> method<br>
     * Assertion: ... the CertSelector specified is cloned to protect against
     * subsequent modifications
     * @throws IOException
     * @throws InvalidAlgorithmParameterException
     */
    public final void testSetTargetCertConstraints02()
        throws IOException,
               InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        X509CertSelector x509cs = new X509CertSelector();
        PKIXParameters p = new PKIXParameters(taSet);
        p.setTargetCertConstraints(x509cs);
        // modify selector
        x509cs.setIssuer(testIssuer);
        // get selector
        X509CertSelector x509cs1 = (X509CertSelector)p.getTargetCertConstraints();
        // check that selector's internal state has
        // not been changed by above modification
        assertFalse(testIssuer.equals(x509cs1.getIssuerAsString()));
    }

    /**
     * Test #1 for <code>getCertStores()</code> method<br>
     * Assertion: list ... (may be empty, but never <code>null</code>)
     * @throws InvalidAlgorithmParameterException
     */
    public final void testGetCertStores01()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        assertNotNull("notNull", p.getCertStores());
        assertTrue("isEmpty", p.getCertStores().isEmpty());
    }

    /**
     * Test #2 for <code>getCertStores()</code> method<br>
     * Assertion: returns an immutable <code>List</code>
     * of <code>CertStores</code>
     * @throws InvalidAlgorithmParameterException
     */
    public final void testGetCertStores02()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        List cs = p.getCertStores();
        boolean modificationFailed = false;
        try {
            // try to modify returned list
            cs.add(new Object());
        } catch (Exception e) {
            modificationFailed = true;
            logln(getName() + ": " + e);
        }
        assertTrue(modificationFailed);
    }

    /**
     * Test #1 for <code>setCertStores(List)</code> method<br>
     * Assertion: Sets the list of CertStores ...
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     */
    public final void testSetCertStores01()
        throws InvalidAlgorithmParameterException,
               NoSuchAlgorithmException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        p.setCertStores(TestUtils.getCollectionCertStoresList());
        // check that list has been set
        assertFalse(p.getCertStores().isEmpty());
    }

    /**
     * Test #2 for <code>setCertStores(List)</code> method<br>
     * Assertion: list ... may be <code>null</code>
     * @throws InvalidAlgorithmParameterException
     */
    public final void testSetCertStores02()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        // add null
        p.setCertStores(null);
        // check that we have non null empty list now
        assertNotNull("notNull1", p.getCertStores());
        assertTrue("isEmpty1", p.getCertStores().isEmpty());
        // add empty
        p.setCertStores(new ArrayList());
        assertNotNull("notNull2", p.getCertStores());
        assertTrue("isEmpty2", p.getCertStores().isEmpty());
    }

    /**
     * Test #3 for <code>setCertStores(List)</code> method<br>
     * Assertion: list is copied to protect against subsequent modifications
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     */
    public final void testSetCertStores03()
        throws InvalidAlgorithmParameterException,
               NoSuchAlgorithmException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        List l = TestUtils.getCollectionCertStoresList();
        p.setCertStores(l);
        // modify list just set
        l.clear();
        // check that list maintained internally has
        // not been changed by the above modification
        assertFalse(p.getCertStores().isEmpty());
    }

    /**
     * Test #4 for <code>setCertStores(List)</code> method<br>
     * Assertion: <code>ClassCastException</code> -
     * if any of the elements in the list are not of type
     * <code>java.security.cert.CertStore</code>
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchAlgorithmException
     */
    public final void testSetCertStores04()
        throws InvalidAlgorithmParameterException,
               NoSuchAlgorithmException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        List l = TestUtils.getCollectionCertStoresList();
        // add wrong object to valid set
        assertTrue(l.add(new Object()));
        boolean cceHasBeenThrown = false;
        try {
            p.setCertStores(l);
        } catch (Exception e) {
            if (e instanceof ClassCastException) {
                cceHasBeenThrown = true;
            }
            logln(getName() + ": " + e);
        }
        assertTrue(cceHasBeenThrown);
    }

    /**
     * Test #1 for <code>addCertStore(CertStore)</code> method<br>
     * Assertion: adds a <code>CertStore</code> to the end of the
     * list of <code>CertStores</code> 
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchAlgorithmException
     */
    public final void testAddCertStore01()
        throws InvalidAlgorithmParameterException,
               NoSuchAlgorithmException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        p.addCertStore(CertStore.getInstance("Collection",
                new CollectionCertStoreParameters()));
        assertFalse(p.getCertStores().isEmpty());
    }

    /**
     * Test #2 for <code>addCertStore(CertStore)</code> method<br>
     * Assertion: if <code>null</code>, the store is ignored (not added to list) 
     * @throws InvalidAlgorithmParameterException
     */
    public final void testAddCertStore02()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        p.addCertStore(null);
        assertTrue(p.getCertStores().isEmpty());
    }

    /**
     * Test #1 for <code>getCertPathCheckers()</code> method<br>
     * Assertion: list ... may be empty, but not <code>null</code>
     * @throws InvalidAlgorithmParameterException
     */
    public final void testGetCertPathCheckers01()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        List l = p.getCertPathCheckers();
        assertNotNull("notNull", l);
        assertTrue("isEmpty",l.isEmpty());
    }

    /**
     * Test #2 for <code>getCertPathCheckers()</code> method<br>
     * Assertion: returns an immutable <code>List</code>
     * of <code>PKIXCertPathChecker</code>s
     * @throws InvalidAlgorithmParameterException
     */
    public final void testGetCertPathCheckers02()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        List l = p.getCertPathCheckers();
        boolean modificationFailed = false;
        try {
            // try to modify returned list
            l.add(new Object());
        } catch (Exception e) {
            modificationFailed = true;
            logln(getName() + ": " + e);
        }
        assertTrue(modificationFailed);
    }

    /**
     * Test #3 for <code>getCertPathCheckers()</code> method<br>
     * Assertion: The returned List is immutable, and each
     * <code>PKIXCertPathChecker</code> in the <code>List</code>
     * is cloned to protect against subsequent modifications
     * @throws InvalidAlgorithmParameterException
     * @throws CertPathValidatorException
     */
    public final void testGetCertPathCheckers03()
        throws InvalidAlgorithmParameterException,
               CertPathValidatorException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        PKIXCertPathChecker cpc = TestUtils.getTestCertPathChecker();
        List l = new ArrayList();
        assertTrue("addedOk", l.add(cpc));
        p.setCertPathCheckers(l);
        // retrieve checker and modify it
        PKIXCertPathChecker cpc1 =
            (PKIXCertPathChecker)p.getCertPathCheckers().get(0);
        cpc1.init(true);
        assertTrue("modifiedOk", cpc1.isForwardCheckingSupported());
        // retrieve checker again and check
        // that its state has not been changed
        // by the above modification
        PKIXCertPathChecker cpc2 =
            (PKIXCertPathChecker)p.getCertPathCheckers().get(0);
        assertFalse("isCloned", cpc2.isForwardCheckingSupported());
    }

    /**
     * Test #1 for <code>setCertPathCheckers(List)</code> method<br>
     * Assertion: sets a <code>List</code> of additional
     * certification path checkers
     * @throws InvalidAlgorithmParameterException
     */
    public final void testSetCertPathCheckers01()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        PKIXCertPathChecker cpc = TestUtils.getTestCertPathChecker();
        List l = new ArrayList();
        assertTrue("addedOk", l.add(cpc));
        p.setCertPathCheckers(l);
        List l1 = p.getCertPathCheckers();
        assertNotNull("notNull", l1);
        assertFalse("isNotEmpty", l1.isEmpty());
    }

    /**
     * Test #2 for <code>setCertPathCheckers(List)</code> method<br>
     * Assertion: <code>List</code> ... may be null
     * @throws InvalidAlgorithmParameterException
     */
    public final void testSetCertPathCheckers02()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        p.setCertPathCheckers(null);
        List l1 = p.getCertPathCheckers();
        assertNotNull("notNull1", l1);
        assertTrue("isEmpty1", l1.isEmpty());
        p.setCertPathCheckers(new ArrayList());
        List l2 = p.getCertPathCheckers();
        assertNotNull("notNull2", l2);
        assertTrue("isEmpty2", l2.isEmpty());
    }

    /**
     * Test #3 for <code>setCertPathCheckers(List)</code> method<br>
     * Assertion: <code>List</code> supplied here is copied and each
     * <code>PKIXCertPathChecker</code> in the list is cloned to protect
     * against subsequent modifications
     * @throws InvalidAlgorithmParameterException
     */
    public final void testSetCertPathCheckers03()
        throws InvalidAlgorithmParameterException {
        // checks that list copied
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        PKIXCertPathChecker cpc = TestUtils.getTestCertPathChecker();
        List l = new ArrayList();
        assertTrue("addedOk", l.add(cpc));
        p.setCertPathCheckers(l);
        // modify list
        l.clear();
        // retrieve list and check
        // that its state has not been changed
        // by the above modification
        assertFalse("isCopied", p.getCertPathCheckers().isEmpty());
    }

    /**
     * Test #4 for <code>setCertPathCheckers(List)</code> method<br>
     * Assertion: <code>List</code> supplied here is copied and each
     * <code>PKIXCertPathChecker</code> in the list is cloned to protect
     * against subsequent modifications
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidAlgorithmParameterException
     * @throws CertPathValidatorException
     */
    public final void testSetCertPathCheckers04()
        throws InvalidAlgorithmParameterException,
               CertPathValidatorException {
        // checks that checkers cloned
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        PKIXCertPathChecker cpc = TestUtils.getTestCertPathChecker();
        List l = new ArrayList();
        assertTrue("addedOk", l.add(cpc));
        p.setCertPathCheckers(l);
        // modify checker
        cpc.init(true);
        // retrieve list and check that CertPathChecker's
        // state it contains has not been changed by the
        // above modification
        PKIXCertPathChecker cpc1 =
            (PKIXCertPathChecker)p.getCertPathCheckers().get(0);
        assertFalse("isCopied", cpc1.isForwardCheckingSupported());
    }

    /**
     * Test #5 for <code>setCertPathCheckers(List)</code> method<br>
     * Assertion: <code>ClassCastException</code> -
     * if any of the elements in the list are not of type
     * <code>java.security.cert.PKIXCertPathChecker</code>
     * @throws InvalidAlgorithmParameterException
     */
    public final void testSetCertPathCheckers05()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        PKIXCertPathChecker cpc = TestUtils.getTestCertPathChecker();
        List l = new ArrayList();
        assertTrue("addedOk", l.add(cpc));
        // add wrong object to the list
        assertTrue("addedOk", l.add(new Object()));
        boolean cceHasBeenThrown = false;
        try {
            p.setCertPathCheckers(l);
        } catch (Exception e) {
            if (e instanceof ClassCastException) {
                cceHasBeenThrown = true;
            }
            logln(getName() + ": " + e);
        }
        assertTrue(cceHasBeenThrown);
    }

    /**
     * Test #1 for <code>addCertPathChecker(PKIXCertPathChecker)</code> method<br>
     * Assertion: adds a <code>CertPathChecker</code> to the end of the
     * list of <code>CertPathChecker</code>s 
     * @throws CertPathValidatorException
     */
    public final void testAddCertPathChecker01()
        throws InvalidAlgorithmParameterException,
               CertPathValidatorException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        PKIXCertPathChecker cpc = TestUtils.getTestCertPathChecker();
        List l = new ArrayList();
        assertTrue("addedOk", l.add(cpc));
        p.setCertPathCheckers(l);
        // create one more PKIXCertPathChecker
        PKIXCertPathChecker cpc1 = TestUtils.getTestCertPathChecker();
        cpc1.init(true);
        p.addCertPathChecker(cpc1);
        // check that we have two PKIXCertPathCheckers and
        // they are in right order
        List l1 = p.getCertPathCheckers();
        assertTrue("listSize", l1.size() == 2);
        assertFalse("order1",
                ((PKIXCertPathChecker)l1.get(0)).isForwardCheckingSupported());
        assertTrue("order2",
                ((PKIXCertPathChecker)l1.get(1)).isForwardCheckingSupported());
    }

    /**
     * Test #2 for <code>addCertPathChecker(PKIXCertPathChecker)</code> method<br>
     * Assertion: if null, the checker is ignored (not added to list).
     * @throws InvalidAlgorithmParameterException
     */
    public final void testAddCertPathChecker02()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        PKIXCertPathChecker cpc = TestUtils.getTestCertPathChecker();
        List l = new ArrayList();
        assertTrue("addedOk", l.add(cpc));
        p.setCertPathCheckers(l);
        // try to add null
        p.addCertPathChecker(null);
        // check that we have one PKIXCertPathChecker
        List l1 = p.getCertPathCheckers();
        assertTrue("listSize", l1.size() == 1);
    }

    /**
     * Test #3 for <code>addCertPathChecker(PKIXCertPathChecker)</code> method<br>
     * Assertion: <code>PKIXCertPathChecker</code> is cloned to protect
     * against subsequent modifications
     * @throws InvalidAlgorithmParameterException
     * @throws CertPathValidatorException
     */
    public final void testAddCertPathChecker03()
        throws InvalidAlgorithmParameterException,
               CertPathValidatorException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        // checks that checkers cloned
        PKIXParameters p = new PKIXParameters(taSet);
        PKIXCertPathChecker cpc = TestUtils.getTestCertPathChecker();

        p.addCertPathChecker(cpc);
        // modify checker
        cpc.init(true);
        // retrieve list and check that CertPathChecker's
        // state it contains has not been changed by the
        // above modification
        List l = p.getCertPathCheckers();
        PKIXCertPathChecker cpc1 = (PKIXCertPathChecker)l.get(0);
        assertTrue("listSize", l.size() == 1);
        assertFalse("isCopied", cpc1.isForwardCheckingSupported());
    }

    /**
     * Test #1 for <code>getDate()</code> method<br>
     * Assertion: the <code>Date</code>, or <code>null</code> if not set 
     * @throws InvalidAlgorithmParameterException
     */
    public final void testGetDate01()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        // the Date has not been set
        // the method must return null
        assertNull("null", p.getDate());
        Date currentDate = new Date();
        p.setDate(currentDate);
        // the Date returned must match
        assertEquals("notNull", currentDate, p.getDate());
    }

    /**
     * Test #2 for <code>getDate()</code> method<br>
     * Assertion: <code>Date</code> returned is copied to protect
     * against subsequent modifications 
     * @throws InvalidAlgorithmParameterException
     */
    public final void testGetDate02()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        Date currentDate = new Date();
        p.setDate((Date)currentDate.clone());
        Date ret1 = p.getDate();
        // modify Date returned
        ret1.setTime(0L);
        // check that internal Date has not been
        // changed by the above modification
        assertEquals(currentDate, p.getDate());
    }

    /**
     * Test #1 for <code>setDate(Date)</code> method<br>
     * Assertion: sets the time for which the validity of
     * the certification path should be determined 
     * @throws InvalidAlgorithmParameterException
     */
    public final void testSetDate01()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        p.setDate(new Date(555L));
        assertTrue(p.getDate().getTime() == 555L);
    }

    /**
     * Test #2 for <code>setDate(Date)</code> method<br>
     * Assertion: <code>Date</code> may be <code>null</code> 
     * @throws InvalidAlgorithmParameterException
     */
    public final void testSetDate02()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        p.setDate(null);
        assertNull(p.getDate());
    }

    /**
     * Test #3 for <code>setDate(Date)</code> method<br>
     * Assertion: <code>Date</code> supplied here is copied to protect
     * against subsequent modifications
     */
    public final void testSetDate03()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        Date toBeSet = new Date(555L);
        p.setDate(toBeSet);
        // modify date set
        toBeSet.setTime(0L);
        // check that internal Date has not been
        // changed by the above modification
        assertTrue(p.getDate().getTime() == 555L);
    }

    /**
     * Test #1 for <code>getInitialPolicies()</code> method<br>
     * Assertion: The default return value is an empty <code>Set</code>
     * Assertion: Never returns <code>null</code>
     * @throws InvalidAlgorithmParameterException
     */
    public final void testGetInitialPolicies01()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        assertNotNull("notNull", p.getInitialPolicies());
        assertTrue("isEmpty", p.getInitialPolicies().isEmpty());
    }

    /**
     * Test #2 for <code>getInitialPolicies()</code> method<br>
     * Assertion: returns an immutable <code>Set</code> of initial
     * policy OIDs in <code>String</code> format<br>
     * @throws InvalidAlgorithmParameterException
     */
    public final void testGetInitialPolicies02()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        Set s = p.getInitialPolicies();
        boolean modificationFailed = false;
        try {
            // try to modify returned set
            s.add(new Object());
        } catch (Exception e) {
            modificationFailed = true;
            logln(getName() + ": " + e);
        }
        assertTrue(modificationFailed);
    }

    /**
     * Test #1 for <code>setInitialPolicies(Set)</code> method<br>
     * Assertion: sets the <code>Set</code> of initial policy
     * identifiers (OID strings)
     * @throws InvalidAlgorithmParameterException
     */
    public final void testSetInitialPolicies01()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        Set s = new HashSet();
        s.add("1.2.3.4.5.6.7");
        PKIXParameters p = new PKIXParameters(taSet);
        p.setInitialPolicies(s);
        assertTrue(p.getInitialPolicies().size() == 1);
    }

    /**
     * Test #2 for <code>setInitialPolicies(Set)</code> method<br>
     * Assertion: <code>Set</code> may be <code>null</code>
     * @throws InvalidAlgorithmParameterException
     */
    public final void testSetInitialPolicies02()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        p.setInitialPolicies(null);
        assertTrue(p.getInitialPolicies().isEmpty());
    }

    /**
     * Test #3 for <code>setInitialPolicies(Set)</code> method<br>
     * Assertion: <code>Set</code> may be empty
     */
    public final void testSetInitialPolicies03()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        p.setInitialPolicies(new HashSet());
        assertTrue(p.getInitialPolicies().isEmpty());
    }

    /**
     * Test #4 for <code>setInitialPolicies(Set)</code> method<br>
     * Assertion: <code>Set</code> is copied to protect against
     * subsequent modifications
     */
    public final void testSetInitialPolicies04()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        Set s = new HashSet();
        s.add("1.2.3.4.5.6.7");
        s.add("1.2.3.4.5.6.8");
        PKIXParameters p = new PKIXParameters(taSet);
        p.setInitialPolicies(s);
        // modify original set
        s.clear();
        // check that set maintained internally has
        // not been changed by the above modification
        assertTrue(p.getInitialPolicies().size() == 2);
    }

    /**
     * Test #5 for <code>setInitialPolicies(Set)</code> method<br>
     * Assertion: <code>ClassCastException</code> -
     * if any of the elements in the set are not of type <code>String</code>
     * @throws InvalidAlgorithmParameterException
     */
    public final void testSetInitialPolicies05()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        Set s = new HashSet();
        s.add("1.2.3.4.5.6.7");
        s.add(new Object());
        PKIXParameters p = new PKIXParameters(taSet);
        boolean cceHasBeenThrown = false;
        try {
            p.setInitialPolicies(s);
        } catch (Exception e) {
            if (e instanceof ClassCastException) {
                cceHasBeenThrown = true;
            }
            logln(getName() + ": " + e);
        }
        assertTrue(cceHasBeenThrown);
    }

    /**
     * Test #1 for <code>getTrustAnchors()</code> method<br>
     * Assertion: an immutable <code>Set</code> of <code>TrustAnchors</code>
     * (never <code>null</code>) 
     * @throws InvalidAlgorithmParameterException
     */
    public final void testGetTrustAnchors01()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        assertNotNull("notNull", p.getTrustAnchors());
    }

    /**
     * Test #2 for <code>getTrustAnchors()</code> method<br>
     * Assertion: an immutable <code>Set</code> of <code>TrustAnchors</code>
     * (never <code>null</code>) 
     * @throws InvalidAlgorithmParameterException
     */
    public final void testGetTrustAnchors02()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        Set s = p.getTrustAnchors();
        boolean modificationFailed = false;
        try {
            // try to modify returned set
            s.add(new Object());
        } catch (Exception e) {
            modificationFailed = true;
            logln(getName() + ": " + e);
        }
        assertTrue(modificationFailed);
    }

    /**
     * Test #1 for <code>setTrustAnchors(Set)</code> method<br>
     * Assertion: Sets the <code>Set</code> of most-trusted CAs 
     * @throws InvalidAlgorithmParameterException
     */
    public final void testSetTrustAnchors01()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        Set taSet1 = TestUtils.getTrustAnchorSet();
        PKIXParameters p = new PKIXParameters(taSet);
        p.setTrustAnchors(taSet1);
        assertFalse(p.getTrustAnchors().isEmpty());
    }

    /**
     * Test #2 for <code>setTrustAnchors(Set)</code> method<br>
     * Assertion: <code>InvalidAlgorithmParameterException</code> -
     * if the specified <code>Set</code> is empty
     * (<code>trustAnchors.isEmpty() == true</code>)
     * @throws InvalidAlgorithmParameterException
     */
    public final void testSetTrustAnchors02()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        boolean iapeHasBeenThrown = false;
        try {
            // use empty set
            p.setTrustAnchors(new HashSet());
        } catch (Exception e) {
            if (e instanceof InvalidAlgorithmParameterException) {
                iapeHasBeenThrown = true;
            }
            logln(getName() + ": " + e);
        }
        assertTrue(iapeHasBeenThrown);
    }

    /**
     * Test #3 for <code>setTrustAnchors(Set)</code> method<br>
     * Assertion: <code>NullPointerException</code> -
     * if the specified <code>Set</code> is <code>null</code>)
     */
    public final void testSetTrustAnchors03()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        boolean npeHasBeenThrown = false;
        try {
            // use null
            p.setTrustAnchors(null);
        } catch (Exception e) {
            if (e instanceof NullPointerException) {
                npeHasBeenThrown = true;
            }
            logln(getName() + ": " + e);
        }
        assertTrue(npeHasBeenThrown);
    }

    /**
     * Test #4 for <code>setTrustAnchors(Set)</code> method<br>
     * Assertion: <code>ClassCastException</code> -
     * if any of the elements in the set are not of type
     * <code>java.security.cert.TrustAnchor</code>
     * @throws InvalidAlgorithmParameterException
     */
    public final void testSetTrustAnchors04()
        throws InvalidAlgorithmParameterException {
        Set taSet = TestUtils.getTrustAnchorSet();
        if (taSet == null) {
            fail(getName() + ": PASSED (could not create test TrustAnchor set)");
            return;
        }
        PKIXParameters p = new PKIXParameters(taSet);
        Set s = new HashSet(p.getTrustAnchors());
        s.add(new Object());
        boolean cceHasBeenThrown = false;
        try {
            p.setTrustAnchors(s);
        } catch (Exception e) {
            if (e instanceof ClassCastException) {
                cceHasBeenThrown = true;
            }
            logln(getName() + ": " + e);
        }
        assertTrue(cceHasBeenThrown);
    }

    /**
     * Test #5 for <code>setTrustAnchors(Set)</code> method<br>
     * Assertion: <code>Set</code> is copied to protect against
     * subsequent modifications
     * @throws InvalidAlgorithmParameterException
     * @throws KeyStoreException
     */
    public final void testSetTrustAnchors05()
        throws KeyStoreException,
               InvalidAlgorithmParameterException {
        // use several trusted certs in this test
        KeyStore ks = TestUtils.getKeyStore(true, TestUtils.TRUSTED);
        if (ks == null) {
            fail(getName() + ": PASSED (could not create test KeyStore)");
            return;
        }
        PKIXParameters p = new PKIXParameters(ks);
        // prepare new Set
        HashSet newSet = new HashSet(p.getTrustAnchors());
        HashSet newSetCopy = (HashSet)newSet.clone();
        // set new Set
        p.setTrustAnchors(newSetCopy);
        // modify set - remove one element
        assertTrue("modified", newSetCopy.remove(newSetCopy.iterator().next()));
        // check that set maintained internally has
        // not been changed by the above modification
        assertEquals("isCopied", newSet, p.getTrustAnchors());
    }

    /**
     * Test #1 for <code>clone()</code> method<br>
     * Assertion: Makes a copy of this <code>PKIXParameters</code> object
     * @throws KeyStoreException
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchAlgorithmException
     */
    public final void testClone01()
        throws KeyStoreException,
               InvalidAlgorithmParameterException,
               NoSuchAlgorithmException {
        KeyStore ks = TestUtils.getKeyStore(true, TestUtils.TRUSTED);
        if (ks == null) {
            fail(getName() + ": PASSED (could not create test KeyStore)");
            return;
        }
        
        PKIXParameters p1 = new PKIXParameters(ks);
        // set to some non-default values
        p1.setPolicyQualifiersRejected(false);
        p1.setAnyPolicyInhibited(true);
        p1.setExplicitPolicyRequired(true);
        p1.setPolicyMappingInhibited(true);
        p1.setRevocationEnabled(false);

        String sigProviderName = "Some Provider";
        p1.setSigProvider(sigProviderName);

        X509CertSelector x509cs = new X509CertSelector();
        p1.setTargetCertConstraints(x509cs);

        p1.setCertStores(TestUtils.getCollectionCertStoresList());

        PKIXCertPathChecker cpc = TestUtils.getTestCertPathChecker();
        List l = new ArrayList();
        assertTrue("addedOk", l.add(cpc));
        p1.setCertPathCheckers(l);

        p1.setDate(new Date(555L));

        Set s = new HashSet();
        s.add("1.2.3.4.5.6.7");
        s.add("1.2.3.4.5.6.8");
        p1.setInitialPolicies(s);

        // TrustAnchors already set

        PKIXParameters p2 = (PKIXParameters)p1.clone();

        // check that objects match
        assertEquals("check1", p1.getPolicyQualifiersRejected(),
                p2.getPolicyQualifiersRejected());
        assertEquals("check2", p1.isAnyPolicyInhibited(),
                p2.isAnyPolicyInhibited());
        assertEquals("check3", p1.isExplicitPolicyRequired(),
                p2.isExplicitPolicyRequired());
        assertEquals("check4", p1.isPolicyMappingInhibited(),
                p2.isPolicyMappingInhibited());
        assertEquals("check5", p1.isRevocationEnabled(),
                p2.isRevocationEnabled());
        assertEquals("check6", p1.getSigProvider(), p2.getSigProvider());

        // just check that not null
        assertNotNull("check7", p2.getTargetCertConstraints());

        assertEquals("check8", p1.getCertStores(), p2.getCertStores());

        // just check that not empty
        assertFalse("check9", p2.getCertPathCheckers().isEmpty());

        assertEquals("check10", p1.getDate(), p2.getDate());
        assertEquals("check11", p1.getInitialPolicies(),
                p2.getInitialPolicies());
        assertEquals("check12", p1.getTrustAnchors(), p2.getTrustAnchors());
    }

    /**
     * Test #2 for <code>clone()</code> method<br>
     * Assertion: Changes to the copy will not affect
     * the original and vice versa
     * @throws KeyStoreException
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchAlgorithmException
     */
    public final void testClone02()
        throws KeyStoreException,
               InvalidAlgorithmParameterException,
               NoSuchAlgorithmException {
        PKIXParameters[] p = new PKIXParameters[2];
        KeyStore ks = TestUtils.getKeyStore(true, TestUtils.TRUSTED);
        if (ks == null) {
            fail(getName() + ": PASSED (could not create test KeyStore)");
            return;
        }

        for (int i = 0; i<p.length; i++) {
            p[i] = new PKIXParameters(ks);

            p[i].setCertStores(TestUtils.getCollectionCertStoresList());

            PKIXCertPathChecker cpc = TestUtils.getTestCertPathChecker();
            List l = new ArrayList();
            assertTrue("addedOk", l.add(cpc));
            p[i].setCertPathCheckers(l);

            p[i].setDate(new Date(555L));

            p[(i == 0 ? 1 : 0)] = (PKIXParameters)p[i].clone();

            // modify the first object (original or copy)
            p[1].addCertStore(CertStore.getInstance("Collection",
                    new CollectionCertStoreParameters()));
            p[1].addCertPathChecker(TestUtils.getTestCertPathChecker());
            // check that the second object has not been affected by
            // above modification
            assertTrue("certStores["+i+"]",
                    p[0].getCertStores().size() == 1);
            assertTrue("certPathCheckers["+i+"]",
                    p[0].getCertPathCheckers().size() == 1);
        }
    }

    /**
     * Test for <code>toString()</code> method<br>
     * Assertion: Returns a formatted string describing the parameters
     * @throws InvalidAlgorithmParameterException
     * @throws KeyStoreException
     */
    public final void testToString()
        throws KeyStoreException,
               InvalidAlgorithmParameterException {
        KeyStore ks = TestUtils.getKeyStore(true, TestUtils.TRUSTED_AND_UNTRUSTED);
        if (ks == null) {
            fail(getName() + ": PASSED (could not create test KeyStore)");
            return;
        }
        PKIXParameters p = new PKIXParameters(ks);
        String rep = p.toString();
        logln(getName() + ": " + rep);
        assertNotNull(rep);
    }

}
