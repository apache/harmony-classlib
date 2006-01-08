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

package java.security.cert;

import junit.framework.TestCase;
import java.security.InvalidAlgorithmParameterException;

/**
 * Tests for <code>CertPathBuilderSpi</code> class constructors and methods.
 * 
 */

public class CertPathBuilderSpiTests extends TestCase {

    /**
     * Constructor for CertPathBuilderSpiTests.
     * 
     * @param arg0
     */
    public CertPathBuilderSpiTests(String arg0) {
        super(arg0);
    }

    /**
     * Test for <code>CertPathBuilderSpi</code> constructor Assertion:
     * constructs CertPathBuilderSpi
     */
    public void testCertPathBuilderSpi01() throws CertPathBuilderException,
            InvalidAlgorithmParameterException {
        CertPathBuilderSpi certPathBuilder = (CertPathBuilderSpi) new MyCertPathBuilderSpi();
        assertTrue(certPathBuilder instanceof CertPathBuilderSpi);
        CertPathParameters cpp = null;
        try {
            certPathBuilder.engineBuild(cpp);
            fail("CertPathBuilderException must be thrown");
        } catch (CertPathBuilderException e) {
        }    
        CertPathBuilderResult cpbResult = certPathBuilder.engineBuild(cpp);
        assertNull("Not null CertPathBuilderResult", cpbResult);
    }
}