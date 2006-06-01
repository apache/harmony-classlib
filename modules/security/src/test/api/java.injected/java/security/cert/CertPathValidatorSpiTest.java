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
 * Tests for <code>CertPathValidatorSpi</code> class constructors and methods.
 * 
 */

public class CertPathValidatorSpiTest extends TestCase {

    /**
     * Constructor for CertPathValidatorSpiTest.
     * 
     * @param arg0
     */
    public CertPathValidatorSpiTest(String arg0) {
        super(arg0);
    }

    /**
     * Test for <code>CertPathValidatorSpi</code> constructor Assertion:
     * constructs CertPathValidatorSpi
     */
    public void testCertPathValidatorSpi01() throws CertPathValidatorException,
            InvalidAlgorithmParameterException {
        CertPathValidatorSpi certPathValid = new MyCertPathValidatorSpi();
        CertPathParameters params = null;
        CertPath certPath = null;
        CertPathValidatorResult cpvResult = certPathValid.engineValidate(
                certPath, params);
        assertNull("Not null CertPathValidatorResult", cpvResult);
        try {
            certPathValid.engineValidate(certPath, params);
            fail("CertPathValidatorException must be thrown");
        } catch (CertPathValidatorException e) {            
        }
        try {
            certPathValid.engineValidate(certPath, params);
            fail("InvalidAlgorithmParameterException must be thrown");
        } catch (InvalidAlgorithmParameterException e) {            
        }
    }
}