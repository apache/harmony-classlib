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
* @author Alexander V. Astapchuk
* @version $Revision$
*/

package java.security;

import java.security.cert.CertPath;
import java.util.Date;

import org.apache.harmony.security.TestCertUtils;

import com.openintel.drl.security.test.PerformanceTest;

/**
 * Unit test for CodeSigner. 
 */

public class CodeSignerTest extends PerformanceTest {

    /**
     * Entry point for standalone runs.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        junit.textui.TestRunner.run(CodeSignerTest.class);
    }

    private CertPath cpath = TestCertUtils.genCertPath(3, 0);
    private Date now = new Date();

    private Timestamp ts = new Timestamp(now, cpath);

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
     * Tests CodeSigner.hashCode()
     */
    public void testHashCode() {
        assertTrue(new CodeSigner(cpath, ts).hashCode() == (cpath.hashCode() ^ ts
                .hashCode()));
        assertTrue(new CodeSigner(cpath, null).hashCode() == cpath.hashCode());
    }

    /**
     * must throw NPE if signerCertPath is null
     */
    public void testCodeSigner_00() {
        try {
            new CodeSigner(null, ts);
            fail("must not accept null");
        } catch (NullPointerException ex) {
            /* it's ok */
        }
    }

    /**
     * timestamp can be null
     */
    public final void testCodeSigner_01() {
        new CodeSigner(cpath, null);
    }

    /**
     * Test various assertions about equals()
     */
    public final void testEqualsObject() {
        
        CodeSigner one = new CodeSigner(cpath, ts);
        CodeSigner two = new CodeSigner(cpath, ts);
        CodeSigner three = new CodeSigner(cpath, null);
        
        CertPath cpath2 = TestCertUtils.genCertPath(5, 3);
        CodeSigner four = new CodeSigner(cpath2, null);

        assertTrue(one.equals(one));
        assertTrue(one.equals(two));
        assertTrue(two.equals(one));
        assertFalse(one.equals(three));
        assertFalse(three.equals(one));
        assertTrue(three.equals(three));
        // different CertPaths
        assertFalse( three.equals(four));
        // special cases
        assertFalse( one.equals(null) );
        assertFalse( one.equals(new Object()) );
    }

    /**
     * Tests CodeSigner.getSignerCertPath()
     */
    public void testGetSignerCertPath() {
        assertSame(new CodeSigner(cpath, null).getSignerCertPath(), cpath);
    }

    /**
     * Tests CodeSigner.getTimeStamp()
     */
    public void testGetTimestamp() {
        assertNull(new CodeSigner(cpath, null).getTimestamp());
        assertSame(new CodeSigner(cpath, ts).getTimestamp(), ts);
    }

    /**
     * Tests CodeSigner.toString()
     */
    public void testToString() {
        new CodeSigner(cpath, null).toString();
        new CodeSigner(cpath, ts).toString();
    }

}