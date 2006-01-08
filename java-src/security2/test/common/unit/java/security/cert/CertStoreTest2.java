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

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.util.Collection;

import com.openintel.drl.security.SpiEngUtils;
import com.openintel.drl.security.test.PerformanceTest;

/**
 * Tests for <code>CertStore</code> class constructors and methods
 * 
 */

public class CertStoreTest2 extends PerformanceTest {
    private static final String defaultAlg = "CertStore";
    private static final String CertStoreProviderClass = "java.security.cert.MyCertStoreSpi";

    private static final String[] invalidValues = SpiEngUtils.invalidValues;

    private static final String[] validValues;

    static {
        validValues = new String[4];
        validValues[0] = defaultAlg;
        validValues[1] = defaultAlg.toLowerCase();
        validValues[2] = "CeRTSTore";
        validValues[3] = "CERTstore";
    }

    Provider mProv;

    protected void setUp() throws Exception {
        super.setUp();
        mProv = (new SpiEngUtils()).new MyProvider("MyCertStoreProvider",
                "Provider for testing", CertStoreTest1.srvCertStore
                        .concat(".").concat(defaultAlg),
                CertStoreProviderClass);  
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
     * Constructor for CertStoreTest2.
     * 
     * @param arg0
     */
    public CertStoreTest2(String arg0) {
        super(arg0);
    }

    private void checkResult(CertStore certS)   throws CertStoreException, 
            InvalidAlgorithmParameterException {
        CertSelector certSelector = new X509CertSelector();
        CRLSelector crlSelector = new X509CRLSelector();
        Collection collection = certS.getCertificates(certSelector);
        assertNull("Not null collection", collection);
        collection = certS.getCRLs(crlSelector);
        assertNull("Not null collection", collection);
    }

    /**
     * Test for method  
     * <code>getInstance(String type, CertStoreParameters params)</code>  
     * Assertions:
     * throws NullPointerException when type is null
     * throws NoSuchAlgorithmException when type is incorrect; 
     * throws InvalidAlgorithmParameterException  when params is null 
     * or has incorrect value; 
     * returns CertStore object
     */
    public void testGetInstance01() throws NoSuchAlgorithmException,
        InvalidAlgorithmParameterException, CertStoreException {
        CertStoreParameters p = new MyCertStoreParameters();
        mPar mp = new mPar("CertStore");
        try {
            CertStore.getInstance(null, p);
            fail("NullPointerException or NoSuchAlgorithmException must be thrown when type is null");
        } catch (NullPointerException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                CertStore.getInstance(invalidValues[i], p);
                fail("NoSuchAlgorithmException must be thrown (type: ".concat(
                        invalidValues[i]).concat(")"));
            } catch (NoSuchAlgorithmException e) {
            }
        }
        for (int i = 0; i < validValues.length; i++) {
            try {
                CertStore.getInstance(validValues[i], null);
                fail("InvalidAlgorithmParameterException must be thrown when params is null");
            } catch (InvalidAlgorithmParameterException e) {
            }
            try {
                CertStore.getInstance(validValues[i], mp);
                fail("InvalidAlgorithmParameterException must be thrown when params is incorrect");
            } catch (InvalidAlgorithmParameterException e) {
            }
        }
        
        CertStore certS;
        for (int i = 0; i < validValues.length; i++) {
            certS = CertStore.getInstance(validValues[i], p);      
            assertTrue("Not instanceof CertStore object",
                    certS instanceof CertStore);
            assertEquals("Incorrect type", certS.getType(), validValues[i]);
            assertEquals("Incorrect provider", certS.getProvider(), mProv);
            assertTrue("Invalid parameters",certS.getCertStoreParameters() instanceof MyCertStoreParameters);
            checkResult(certS);
        }        
    }

    /**
     * Test for method
     * <code>getInstance(String type, CertStoreParameters params, String provider)</code>
     * Assertions: 
     * throws NullPointerException when type is null
     * throws NoSuchAlgorithmException when type is incorrect; 
     * throws IllegalArgumentException when provider is null or empty; 
     * throws NoSuchProviderException when provider is available; 
     * throws InvalidAlgorithmParameterException  when params is null 
     * returns CertStore object
     */
    public void testGetInstance02() throws NoSuchAlgorithmException,
            NoSuchProviderException, IllegalArgumentException,
            InvalidAlgorithmParameterException, CertStoreException {
        CertStoreParameters p = new MyCertStoreParameters();
        mPar mp = new mPar("CertStore");
        try {
            CertStore.getInstance(null, p, mProv.getName());
            fail("NullPointerException or NoSuchAlgorithmException must be thrown when type is null");
        } catch (NullPointerException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                CertStore.getInstance(invalidValues[i], p, mProv
                        .getName());
                fail("NoSuchAlgorithmException must be thrown (type: ".concat(
                        invalidValues[i]).concat(")"));
            } catch (NoSuchAlgorithmException e) {
            }
        }
        String prov = null;
        for (int i = 0; i < validValues.length; i++) {
            try {
                CertStore.getInstance(validValues[i], p, prov);
                fail("IllegalArgumentException must be thrown when provider is null (type: "
                        .concat(validValues[i]).concat(")"));
            } catch (IllegalArgumentException e) {
            }
            try {
                CertStore.getInstance(validValues[i], p, "");
                fail("IllegalArgumentException must be thrown when provider is empty (type: "
                        .concat(validValues[i]).concat(")"));
            } catch (IllegalArgumentException e) {
            }
        }
        for (int i = 0; i < validValues.length; i++) {
            for (int j = 1; j < invalidValues.length; j++) {
                try {
                    CertStore.getInstance(validValues[i], p, 
                            invalidValues[j]);
                    fail("NoSuchProviderException must be thrown (type: "
                            .concat(validValues[i]).concat(" provider: ")
                            .concat(invalidValues[j]).concat(")"));
                } catch (NoSuchProviderException e) {
                }
            }
        }
        for (int i = 0; i < validValues.length; i++) {
            try {
                CertStore.getInstance(validValues[i], null, mProv.getName()); 
                fail("InvalidAlgorithmParameterException must be thrown when params is null");
            } catch (InvalidAlgorithmParameterException e) {
            }
            try {
                CertStore.getInstance(validValues[i], mp, mProv.getName());
                fail("InvalidAlgorithmParameterException must be thrown when params is incorrect");
            } catch (InvalidAlgorithmParameterException e) {
            }
        }
        CertStore certS;
        for (int i = 0; i < validValues.length; i++) {
            certS = CertStore.getInstance(validValues[i], p, mProv
                    .getName());
            assertTrue("Not instanceof CertStore object",
                    certS instanceof CertStore);
            assertEquals("Incorrect type", certS.getType(), validValues[i]);
            assertEquals("Incorrect provider", certS.getProvider().getName(),
                    mProv.getName());
            assertTrue("Invalid parameters",certS.getCertStoreParameters() instanceof MyCertStoreParameters);
            checkResult(certS);
        }
    }

    /**
     * Test for method
     * <code>getInstance(String type, CertStoreParameters params, Provider provider)</code>
     * Assertions: 
     * throws NullPointerException when type is null
     * throws NoSuchAlgorithmException when type is incorrect; 
     * throws IllegalArgumentException when provider is null;
     * throws InvalidAlgorithmParameterException when params is null or has incorrect value 
     * returns CertStore object
     */
    public void testGetInstance03() throws NoSuchAlgorithmException,
            IllegalArgumentException,
            InvalidAlgorithmParameterException, CertStoreException {
        CertStoreParameters p = new MyCertStoreParameters();
        mPar mp = new mPar("CertStore");
        try {
            CertStore.getInstance(null, p, mProv);
            fail("NullPointerException or NoSuchAlgorithmException must be thrown when type is null");
        } catch (NullPointerException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        for (int i = 0; i < invalidValues.length; i++) {
            try {
                CertStore.getInstance(invalidValues[i], p, mProv);
                fail("NoSuchAlgorithmException must be thrown (type: ".concat(
                        invalidValues[i]).concat(")"));
            } catch (NoSuchAlgorithmException e) {
            }
        }
        Provider prov = null;
        for (int i = 0; i < validValues.length; i++) {
            try {
                CertStore.getInstance(validValues[i], p, prov);
                fail("IllegalArgumentException must be thrown when provider is null (type: "
                        .concat(validValues[i]).concat(")"));
            } catch (IllegalArgumentException e) {
            }
        }
        for (int i = 0; i < validValues.length; i++) {
            try {
                CertStore.getInstance(validValues[i], null, mProv); 
                fail("InvalidAlgorithmParameterException must be thrown when params is null");
            } catch (InvalidAlgorithmParameterException e) {
            }
            try {
                CertStore.getInstance(validValues[i], mp, mProv);
                fail("InvalidAlgorithmParameterException must be thrown when params is incorrect");
            } catch (InvalidAlgorithmParameterException e) {
            }
        }
        CertStore certS;
        for (int i = 0; i < validValues.length; i++) {
            certS = CertStore.getInstance(validValues[i], p, mProv);
            assertTrue("Not instanceof CertStore object",
                    certS instanceof CertStore);
            assertEquals("Incorrect type", certS.getType(), validValues[i]);
            assertEquals("Incorrect provider", certS.getProvider(), mProv);
            assertTrue("Invalid parameters",certS.getCertStoreParameters() instanceof MyCertStoreParameters);
            checkResult(certS);
        }
    }
}
class mPar implements CertStoreParameters {
    String par = "";
    public mPar() {
        super();
    }
    public mPar(String s) {
        super();
        par = s;
    }
    
    public Object clone() {
        if (par == "") {
            return null;
        }
        try {
            return (Object)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}