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

package org.apache.harmony.crypto.tests.javax.crypto;

import java.security.InvalidKeyException;
import java.security.Provider;

import javax.crypto.ExemptionMechanism;
import javax.crypto.ExemptionMechanismSpi;

import org.apache.harmony.crypto.tests.support.MyExemptionMechanismSpi;
import org.apache.harmony.security.tests.support.SpiEngUtils;
import org.apache.harmony.security.tests.support.SpiEngUtils.MyProvider;

import junit.framework.TestCase;
/**
 * Tests for <code>ExemptionMechanism</code> class constructors and methods
 * 
 */

public class ExemptionMechanismTest extends TestCase {
    
    public static final String srvExemptionMechanism = "ExemptionMechanism";
    
    private static final String defaultAlg = "EMech";
    
    private static final String ExemptionMechanismProviderClass = "org.apache.harmony.crypto.tests.support.MyExemptionMechanismSpi";

    /**
     * Constructor for SecurityManagerFactoryTest2.
     * 
     * @param arg0
     */
    public ExemptionMechanismTest(String arg0) {
        super(arg0);
    }
   
    /**
     * Test for <code>ExemptionMechanism</code> constructor 
     * Assertion: cretes new object using provider and mechanism name
     */
    public void testExemptionMechanism() throws Exception {
        Provider mProv = (new SpiEngUtils()).new MyProvider("MyExMechProvider", "Provider for ExemptionMechanism testing", 
                srvExemptionMechanism.concat(".").concat(defaultAlg), 
                ExemptionMechanismProviderClass);
        
        ExemptionMechanismSpi spi = new MyExemptionMechanismSpi();
        ExemptionMechanism em = new MyMechanism(spi, mProv, defaultAlg);
        
        assertEquals("Incorrect provider", em.getProvider(), mProv);
        assertEquals("Incorrect algorithm", em.getName(), defaultAlg);
        try {
            em.init(null);
            fail("InvalidKeyException must be thrown");
        } catch (InvalidKeyException e) {
        }
        try {
            em.getOutputSize(100);
            fail("IllegalStateException must be thrown");
        } catch (IllegalStateException e) {
        }        
        em = new MyMechanism(null, null, null);
        assertNull("Incorrect mechanism", em.getName());
        assertNull("Incorrect provider", em.getProvider());
        try {
            em.init(null);
            fail("NullPointerException must be thrown");
        } catch (NullPointerException e) {
        }
        try {
            em.getOutputSize(100);
            fail("IllegalStateException must be thrown");
        } catch (IllegalStateException e) {
        }        
    }
}

class MyMechanism extends ExemptionMechanism {

    public MyMechanism(ExemptionMechanismSpi spi, Provider prov, String mechanism) {
        super(spi, prov, mechanism);
    }

    public void finalize() {
        try {
            super.finalize();
        } catch (Throwable e) {
            throw new RuntimeException("finalize was broken", e);
        }
    }
}
