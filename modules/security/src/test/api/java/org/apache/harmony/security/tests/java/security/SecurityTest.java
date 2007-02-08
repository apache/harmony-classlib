/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
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
* @author Boris V. Kuznetsov
* @version $Revision$
*/

package org.apache.harmony.security.tests.java.security;

import java.security.*;

import org.apache.harmony.security.tests.support.TestKeyPair;

import junit.framework.TestCase;

/**
 * Tests for <code>Security</code> constructor and methods
 */
public class SecurityTest extends TestCase {

    public final void testMixed() {

        TestKeyPair tkp = null;
        try {
            tkp = new TestKeyPair("DSA"); 
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
            return;
        }

        try {
            MessageDigest.getInstance("SHA-1");
            KeyFactory.getInstance("DSA");
            Signature ss =Signature.getInstance("DSA");
            ss.initSign(tkp.getPrivate());
            Signature.getInstance("aaaaaaaaaaaa");
        } catch (Exception e) {
            // ignore
        }    

    }
  
    public final void testInsertProviderAt() {
        Provider p = new MyProvider();
        int position;
        int newposition;
        Provider providers[] = Security.getProviders();
        int providersNumber = providers.length;
        
        try {

            // Insert at position -1
            position = -1;
            newposition = Security.insertProviderAt(p, position);
            assertEquals(providersNumber + 1, newposition);

            providers = Security.getProviders();
            assertSame("Provider not inserted at position " + newposition, p,
                    providers[newposition - 1]);
        
            // A provider cannot be added if it is already installed
            newposition = Security.insertProviderAt(p, 1);
            assertEquals(-1, newposition);
        
            Security.removeProvider(p.getName());
        
            // insert at the end
            position = providersNumber + 100;
            newposition = Security.insertProviderAt(p, position);
            assertEquals(providersNumber + 1, newposition);

            providers = Security.getProviders();
            assertSame("Provider not inserted at position " + newposition, p,
                    providers[newposition - 1]);
            
            Security.removeProvider(p.getName());
            
            // insert at the first position
            position = 1;
            newposition = Security.insertProviderAt(p, position);
            assertEquals(position, newposition);

            providers = Security.getProviders();
            assertSame("Provider not inserted at position " + newposition, p,
                    providers[newposition - 1]);
        
            try {
                Security.insertProviderAt(null, position);
                fail("No expected NullPointerException.");
            } catch (NullPointerException e) {
            }
        } finally { //clean up
            Security.removeProvider(p.getName());
        }
    }

    public final void testAddProvider() {

        Provider p = new MyProvider();
        int newposition;
        Provider providers[] = Security.getProviders();
        int providersNumber = providers.length;
        
        try {
            // add
            newposition = Security.addProvider(p);
            assertEquals(providersNumber + 1, newposition);

            providers = Security.getProviders();
            assertSame("Provider not inserted at position " + newposition, p,
                    providers[newposition - 1]);
        
            // A provider cannot be added if it is already installed
            newposition = Security.addProvider(p);
            assertEquals(-1, newposition);
        
            try {
                Security.addProvider(null);
                fail("No expected NullPointerException.");
            } catch (NullPointerException e) {
            }
        } finally { //clean up
            Security.removeProvider(p.getName());
        }
        
    }

    public final void testRemoveProvider() {
        Provider[] providers;
        Provider[] providers1;
        
        providers = Security.getProviders();

        try {
            for (int i = 0; i < providers.length; i++) {
                Security.removeProvider(providers[i].getName());
            }
            assertEquals("Providers not removed", 0,
                    Security.getProviders().length);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {    // restore providers
            for (int i = 0; i < providers.length; i++) {
                Security.addProvider(providers[i]);
            }
            providers1 = Security.getProviders();
            for (int i = 0; i < providers1.length; i++) {
                assertEquals("Providers not restored correctly", providers[i],
                        providers1[i]);
            }
        }
    }

    /**
     * @tests java.security.Security#getProvider(String)
     */
    public final void test_getProviderLjava_lang_String() {

        // Returns null if no provider with the specified name is installed 
        assertNull(Security.getProvider("SOMEINCORRECTPROVIDERNAME"));

        // Returns null if name is null
        assertNull(Security.getProvider(null));

        // test for existing providers
        Provider[] providers = Security.getProviders();
        assertTrue("getProviders returned zero length array",
                providers.length > 0);
        for (Provider p : providers) {
            String providerName = p.getName();
            assertSame(p, Security.getProvider(providerName));
        }

        // test for newly installed provider
        Provider p = new MyProvider();
        try {
            Security.addProvider(p);

            assertSame(p, Security.getProvider(p.getName()));
        } finally { //clean up
            Security.removeProvider(p.getName());
        }
    }

    public final void testSetGetProperty() {
        try {
            Security.getProperty(null);
            fail("Case 1. No expected NullPointerException.");
        } catch (NullPointerException e) {        
        }
        
        try {
            Security.setProperty(null, "");
            fail("Case 2. No expected NullPointerException.");
        } catch (NullPointerException e) {        
        }
        
        try {
            Security.setProperty("", null);
            fail("Case 3. No expected NullPointerException.");
        } catch (NullPointerException e) {        
        }
        
        Security.setProperty("", "");
        assertEquals("Empty property test failed", "", Security.getProperty(""));
        
        Security.setProperty("My Test Property", "My property value");
        assertEquals("My property value", Security
                .getProperty("My Test Property"));
    }
    
    class MyProvider extends Provider {
        MyProvider() {
            super("MyProvider", 1.0, "Provider for testing");
            put("MessageDigest.SHA-1", "SomeClassName");
            put("MyService.MyAlgorithm", "SomeClassName");
            put("MyService.MyAlgorithm KeySize", "1024");
        }
    }

}
