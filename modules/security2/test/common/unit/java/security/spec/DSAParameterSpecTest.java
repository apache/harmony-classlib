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

package java.security.spec;

import java.math.BigInteger;

import com.openintel.drl.security.test.PerformanceTest;

/**
 * Tests for <code>DSAParameterSpec</code>
 * 
 */
public class DSAParameterSpecTest extends PerformanceTest {

    /**
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
    }

    /**
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Constructor for DSAParameterSpecTest.
     * @param name
     */
    public DSAParameterSpecTest(String name) {
        super(name);
    }

    /**
     * Ctor test 
     */
    public final void testDSAParameterSpec() {
        AlgorithmParameterSpec aps = new DSAParameterSpec(
                new BigInteger("1"),
                new BigInteger("2"),
                new BigInteger("3"));
        
        assertTrue(aps instanceof DSAParameterSpec);
    }

    /**
     * getG() test
     */
    public final void testGetG() {
        DSAParameterSpec dps = new DSAParameterSpec(
                new BigInteger("1"),
                new BigInteger("2"),
				new BigInteger("3"));
        
        assertTrue(dps.getG().intValue() == 3);        
    }

    /**
     * getP() test
     */
    public final void testGetP() {
        DSAParameterSpec dps = new DSAParameterSpec(
                new BigInteger("1"),
                new BigInteger("2"),
                new BigInteger("3"));
        
        assertTrue(dps.getP().intValue() == 1);        
    }

    /**
     * getQ() test 
     */
    public final void testGetQ() {
        DSAParameterSpec dps = new DSAParameterSpec(
                new BigInteger("1"),
                new BigInteger("2"),
                new BigInteger("3"));
        
        assertTrue(dps.getQ().intValue() == 2);        
    }
}
