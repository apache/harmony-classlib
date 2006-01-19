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

package javax.crypto;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.spec.SecretKeySpec;

import org.apache.harmony.security.SpiEngUtils;

import com.openintel.drl.security.test.PerformanceTest;

/**
 * Tests for <code>ExemptionMechanism</code> class constructors and methods
 * 
 */

public class ExemptionMechanismTest1 extends PerformanceTest {
    
    public static final String srvExemptionMechanism = "ExemptionMechanism";
    
    private static final String defaultAlg = "EMech";
    
    private static final String ExemptionMechanismProviderClass = "javax.crypto.MyExemptionMechanismSpi";

    private static final String[] invalidValues = SpiEngUtils.invalidValues;

    private static final String[] validValues;

    static {
        validValues = new String[4];
        validValues[0] = defaultAlg;
        validValues[1] = defaultAlg.toLowerCase();
        validValues[2] = "eMEch";
        validValues[3] = "emecH";
    }

    Provider mProv;

    protected void setUp() throws Exception {
        super.setUp();
        mProv = (new SpiEngUtils()).new MyProvider("MyExMechProvider", "Provider for ExemptionMechanism testing", 
                srvExemptionMechanism.concat(".").concat(defaultAlg), 
                ExemptionMechanismProviderClass);
        Security.insertProviderAt(mProv, 1);
    }
    
    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
        Security.removeProvider(mProv.getName());
    }

    /**
     * Constructor for SecurityManagerFactoryTest2.
     * 
     * @param arg0
     */
    public ExemptionMechanismTest1(String arg0) {
        super(arg0);
    }

    private void checkResult(ExemptionMechanism exMech) 
            throws ExemptionMechanismException, ShortBufferException,
            InvalidKeyException, InvalidAlgorithmParameterException  {
        Key key = new MyExemptionMechanismSpi().new tmpKey("Proba", new byte[0]);
        byte [] emptyA = new byte[0];
        int len = MyExemptionMechanismSpi.getLength();
        byte [] byteA = new byte[len];
        try {
            exMech.genExemptionBlob();
            fail("IllegalStateException must be thrown");
        } catch (IllegalStateException e) {            
        }
        try {
            exMech.genExemptionBlob(byteA);
            fail("IllegalStateException must be thrown");
        } catch (IllegalStateException e) {            
        }
        try {
            exMech.genExemptionBlob(byteA, 1);
            fail("IllegalStateException must be thrown");
        } catch (IllegalStateException e) {            
        }        
        try {
            exMech.getOutputSize(0);
            fail("IllegalStateException must be thrown");
        } catch (IllegalStateException e) {            
        }        
        
        exMech.init(key);
        byte [] bbRes = exMech.genExemptionBlob();
        assertEquals("Incorrect length", bbRes.length, len);
        assertEquals("Incorrect result", exMech.genExemptionBlob(new byte[5]), 5);
        assertEquals("Incorrect result", exMech.genExemptionBlob(bbRes), len);
        try {
            exMech.genExemptionBlob(new byte[1], len);
            fail("ShortBufferException must be thrown");
        } catch (ShortBufferException e) {            
        }        
        try {
            exMech.genExemptionBlob(emptyA);
            fail("ShortBufferException must be thrown");
        } catch (ShortBufferException e) {            
        }
        
        assertEquals("Incorrect result", exMech.genExemptionBlob(byteA, 1), 9);
        assertEquals("Incorrect result", exMech.genExemptionBlob(new byte[20], (len - 2)), len);
                
        assertEquals("Incorrect output size", exMech.getOutputSize(100), 5);
        
        AlgorithmParameters params = null;
        exMech.init(key, params);
        AlgorithmParameterSpec parSpec = null;
        exMech.init(key, parSpec);
        key = new SecretKeySpec(new byte[10], "DSA");
        try {
            exMech.init(key);
            fail("ExemptionMechanismException must be throwm");
        } catch (ExemptionMechanismException e) {
            assertTrue("Empty message", (e.getMessage().length() > 0));
        }
        try {
            exMech.init(key, params);
            fail("ExemptionMechanismException must be throwm");
        } catch (ExemptionMechanismException e) {
            assertTrue("Empty message", (e.getMessage().length() > 0));
        }
        try {
            exMech.init(key, parSpec);
            fail("ExemptionMechanismException must be throwm");
        } catch (ExemptionMechanismException e) {
            assertTrue("Empty message", (e.getMessage().length() > 0));
        }
    }
    
    /**
     * Test for <code>getInstance(String algorithm)</code> method
     * Assertions:
     * throws NullPointerException when algorithm is null;
     * throws NoSuchAlgorithmException when algorithm is incorrect;
     * returns ExemptionMechanism object
     */
    public void testGetInstance01() throws NoSuchAlgorithmException,
            ExemptionMechanismException, InvalidAlgorithmParameterException,
            ShortBufferException, InvalidKeyException {
        try {
            ExemptionMechanism.getInstance(null);
            fail("NullPointerException or NoSuchAlgorithmException should be thrown if algorithm is null");
        } catch (NullPointerException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                ExemptionMechanism.getInstance(invalidValues[i]);
                fail("NoSuchAlgorithmException must be thrown (algorithm: "
                        .concat(invalidValues[i]).concat(")"));
            } catch (NoSuchAlgorithmException e) {
            }
        }
        ExemptionMechanism exMech;
        for (int i = 0; i < validValues.length; i++) {
            exMech = ExemptionMechanism.getInstance(validValues[i]);
            assertTrue("Not instanceof ExemptionMechanism object",
                    exMech instanceof ExemptionMechanism);
            assertEquals("Incorrect algorithm", exMech.getName(),
                    validValues[i]);
            assertEquals("Incorrect provider", exMech.getProvider(), mProv);
            checkResult(exMech);
        }
    }

    /**
     * Test for <code>getInstance(String algorithm, String provider)</code>
     * method
     * Assertions: 
     * throws NullPointerException when algorithm is null;
     * throws NoSuchAlgorithmException when algorithm is null or incorrect;
     * throws IllegalArgumentException when provider is null;
     * throws NoSuchProviderException when provider is available;
     * returns ExemptionMechanism object
     */
    public void testGetInstance02() throws NoSuchAlgorithmException,
            NoSuchProviderException, IllegalArgumentException,
            ExemptionMechanismException, InvalidAlgorithmParameterException,
            ShortBufferException, InvalidKeyException {        
        try {
            ExemptionMechanism.getInstance(null, mProv.getName());
            fail("NullPointerException or NoSuchAlgorithmException should be thrown if algorithm is null");
        } catch (NullPointerException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                ExemptionMechanism.getInstance(invalidValues[i], mProv
                        .getName());
                fail("NoSuchAlgorithmException must be thrown (algorithm: "
                        .concat(invalidValues[i]).concat(")"));
            } catch (NoSuchAlgorithmException e) {
            }
        }
        String prov = null;
        for (int i = 0; i < validValues.length; i++) {
            try {
                ExemptionMechanism.getInstance(validValues[i], prov);
                fail("IllegalArgumentException must be thrown when provider is null (algorithm: "
                        .concat(invalidValues[i]).concat(")"));
            } catch (IllegalArgumentException e) {
            }
        }
        for (int i = 0; i < validValues.length; i++) {
            for (int j = 1; j < invalidValues.length; j++) {
                try {
                    ExemptionMechanism.getInstance(validValues[i],
                            invalidValues[j]);
                    fail("NoSuchProviderException must be thrown (algorithm: "
                            .concat(invalidValues[i]).concat(" provider: ")
                            .concat(invalidValues[j]).concat(")"));
                } catch (NoSuchProviderException e) {
                }
            }
        }
        ExemptionMechanism exMech;
        for (int i = 0; i < validValues.length; i++) {
            exMech = ExemptionMechanism.getInstance(validValues[i], mProv
                    .getName());
            assertTrue("Not instanceof ExemptionMechanism object",
                    exMech instanceof ExemptionMechanism);
            assertEquals("Incorrect algorithm", exMech.getName(),
                    validValues[i]);
            assertEquals("Incorrect provider", exMech.getProvider().getName(),
                    mProv.getName());
            checkResult(exMech);
        }
    }

    /**
     * Test for <code>getInstance(String algorithm, Provider provider)</code>
     * method
     * Assertions:
     * throws NullPointerException when algorithm is null;
     * throws NoSuchAlgorithmException when algorithm is null or incorrect;
     * throws IllegalArgumentException when provider is null;
     * returns ExemptionMechanism object
     */
    public void testGetInstance03() throws NoSuchAlgorithmException,
            IllegalArgumentException,
            ExemptionMechanismException, InvalidAlgorithmParameterException,
            ShortBufferException, InvalidKeyException {
        try {
            ExemptionMechanism.getInstance(null, mProv);
            fail("NullPointerException or NoSuchAlgorithmException should be thrown if algorithm is null");
        } catch (NullPointerException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                ExemptionMechanism.getInstance(invalidValues[i], mProv);
                fail("NoSuchAlgorithmException must be thrown (algorithm: "
                        .concat(invalidValues[i]).concat(")"));
            } catch (NoSuchAlgorithmException e) {
            }
        }
        Provider prov = null;
        for (int i = 0; i < validValues.length; i++) {
            try {
                ExemptionMechanism.getInstance(validValues[i], prov);
                fail("IllegalArgumentException must be thrown when provider is null (algorithm: "
                        .concat(invalidValues[i]).concat(")"));
            } catch (IllegalArgumentException e) {
            }
        }
        ExemptionMechanism exMech;
        SecretKey sk;
        KeySpec keySpec;
        for (int i = 0; i < validValues.length; i++) {
            exMech = ExemptionMechanism.getInstance(validValues[i], mProv);
            assertTrue("Not instanceof ExemptionMechanism object",
                    exMech instanceof ExemptionMechanism);
            assertEquals("Incorrect algorithm", exMech.getName(),
                    validValues[i]);
            assertEquals("Incorrect provider", exMech.getProvider(), mProv);
            checkResult(exMech);
        }
    }
    
    /**
     * Test for <code>ExemptionMechanism</code> constructor 
     * Assertion: cretes new object using provider and mechanism name
     */
    public void testExemptionMechanism() throws NoSuchAlgorithmException,
            ExemptionMechanismException, ShortBufferException, InvalidKeyException {
        ExemptionMechanismSpi spi = new MyExemptionMechanismSpi();
        ExemptionMechanism em = new myEM(spi, mProv, defaultAlg);
        
        assertTrue("Not ExemptionMechanism object", em instanceof ExemptionMechanism);
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
        em = new myEM(null, null, null);
        assertTrue("Not ExemptionMechanism object", em instanceof ExemptionMechanism);
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

class myEM extends ExemptionMechanism {

    public myEM(ExemptionMechanismSpi spi, Provider prov, String mechanism) {
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