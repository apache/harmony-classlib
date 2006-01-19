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

import org.apache.harmony.security.spec.MyEncodedKeySpec;
import org.apache.harmony.security.test.PerformanceTest;


/**
 * Tests for <code>EncodedKeySpec</code> class fields and methods.
 * 
 */

public class EncodedKeySpecTest extends PerformanceTest {

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
     * Constructor for EncodedKeySpecTest.
     * @param name
     */
    public EncodedKeySpecTest(String name) {
        super(name);
    }
    
    /**
     * Tests that <code>getEncoded()</code> method
     * returns valid byte array
     */
    public final void testGetEncoded() {
        
        byte[] encodedKey = new byte[] {(byte)1,(byte)2,(byte)3,(byte)4};
        EncodedKeySpec meks = new MyEncodedKeySpec(encodedKey);
        
        /* Get encoded key */
        byte[] ek = meks.getEncoded();
        
        /* Check returned array */
        boolean result = true;
        for (int i=0; i<encodedKey.length; i++) {
            if (encodedKey[i] != ek[i]) {
                /* indicate failure */
                result = false;
            }
        }
        /* passed */
        assertTrue(result);
    }
    
    /**
     * Tests that internal state of the object
     * can not be modified by modifying initial array value
     */
    public final void testIsStatePreserved1() {
        /* Create initial byte array */
        byte[] encodedKey = new byte[] {(byte)1,(byte)2,(byte)3,(byte)4};
        
        EncodedKeySpec meks = new MyEncodedKeySpec(encodedKey);
        
        /* Modify initial array's value */
        encodedKey[3] = (byte)5;
        
        /* Get encoded key */
        byte[] ek = meks.getEncoded();
        
        /* Check that byte value has not been changed */
        assertTrue(ek[3] == (byte)4);
    }
    
    /**
     * Tests that internal state of the object
     * can not be modified using returned value
     * of <code>getEncoded()</code> method 
    */
    public final void testIsStatePreserved2() {
        
        byte[] encodedKey = new byte[] {(byte)1,(byte)2,(byte)3,(byte)4};
        EncodedKeySpec meks = new MyEncodedKeySpec(encodedKey);
        
        /* Get encoded key */
        byte[] ek = meks.getEncoded();        
        /* Modify returned value */
        ek[3] = (byte)5;
        /* Get encoded key again */
        byte[] ek1 = meks.getEncoded();
        
        /* Check that byte value has not been changed */
        assertTrue(ek1[3] == (byte)4);
    }
 
}
