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

package javax.security.sasl;


import java.security.Provider;
import java.security.Security;
import java.util.Enumeration;

import junit.framework.TestCase;

/**
 * Test for Sasl class
 * 
 */

public class SaslTest1 extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(SaslTest1.class);
    }

    private Provider [] provs;
    private boolean initProvs = false;
    /**
     * Constructor for SaslTest2.
     * 
     * @param arg0
     */
    public SaslTest1(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        if (!initProvs) {
            provs = Security.getProviders();
            initProvs = true;
        }
        if (provs != null) {
            for (int i = 0; i < provs.length; i++) {
                Security.removeProvider(provs[i].getName());
            }
        }
    }
    
    protected void tearDown() throws Exception {
        super.tearDown();
        if (provs != null) {
            for (int i = 0; i < provs.length; i++) {
                Security.insertProviderAt(provs[i], (i+1));
            }
        }
    }

    /**
     * Test for <code>getSaslClientFactories()</code> method 
     * 
     * Assertion:
     * returns enumeration without any element
     * 
     * All providers are previously removed.
     */
    public void testGetClient() {    
        Enumeration en = Sasl.getSaslClientFactories();
        assertNotNull("List of SaslClientFactories should not be null", en);
        assertFalse("List of SaslClientFactories should not haves elements", en
                .hasMoreElements());
    }

    /**
     * Test for <code>getSaslServerFactories()</code> method 
     * 
     * Assertion:
     * returns enumeration without any element
     * 
     * All providers are previously removed.
     */
    public void testGetSertver() {
        Enumeration en = Sasl.getSaslServerFactories();
        assertNotNull("List of SaslServerFactories should not be null", en);
        assertFalse("List of SaslServerFactories should not have elements", en
                .hasMoreElements());
    }
}
