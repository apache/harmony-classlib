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

package javax.crypto.interfaces;

import java.math.BigInteger;

import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;

import org.apache.harmony.security.test.PerformanceTest;


/**
 * Tests for <code>DHPublicKey</code> class field
 * 
 */
public class DHPublicKeyTest extends PerformanceTest {

    public static void main(String[] args) {
    }

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
     * Constructor for DHPublicKey.
     * 
     * @param arg0
     */
    public DHPublicKeyTest(String arg0) {
        super(arg0);
    }

    /**
     * Test for <code>serialVersionUID</code> field
     */
    public void testField() {
        checkDHPublicKey key = new checkDHPublicKey();
        assertEquals("Incorrect serialVersionUID",
                key.getSerVerUID(), //DHPublicKey.serialVersionUID
                -6628103563352519193L);
    }
    public class checkDHPublicKey implements DHPublicKey {
        public String getAlgorithm() {
            return "SecretKey";
        }
        public String getFormat() {
            return "Format";
        }
        public byte[] getEncoded() {
            return new byte[0];
        }
        public long getSerVerUID() {
            return serialVersionUID;
        }
        public BigInteger getY() {
            return null;
        }
        public DHParameterSpec getParams() {
            return null;
        }
    }
}