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
* @author Boris V. Kuznetsov
* @version $Revision$
*/

package org.apache.harmony.security.tests.java.security;

import java.security.*;

import org.apache.harmony.security.tests.support.TestKeyPair;

import junit.framework.TestCase;


/**
 * Tests for <code>Security</code> constructor and methods
 * 
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
            if (newposition != (providersNumber + 1)) {
                fail("Case 1. Newposition is " + newposition + ", should be " + (providersNumber + 1));
            }
            providers = Security.getProviders();
            if (providers[newposition-1] != p) {
                fail("Case 1. Provider not inserted at position " + newposition);
            }
        
            // A provider cannot be added if it is already installed
            newposition = Security.insertProviderAt(p, 1);
            if (newposition != -1) {
                fail("Case 2. Newposition is " + newposition + ", should be -1");
            }
        
            Security.removeProvider(p.getName());
        
            // insert at the end
            position = providersNumber + 100;
            newposition = Security.insertProviderAt(p, position);
            if (newposition != (providersNumber + 1)) {
                fail("Case 3. Newposition is " + newposition + ", should be " + (providersNumber + 1));
            }
            providers = Security.getProviders();
            if (providers[newposition-1] != p) {
                fail("Case 3. Provider not inserted at position " + newposition);
            }
            
            Security.removeProvider(p.getName());
            
            // insert at the first position
            position = 1;
            newposition = Security.insertProviderAt(p, position);
            if (newposition != position) {
                fail("Case 4. Newposition is " + newposition + ", should be " + position);
            }
            providers = Security.getProviders();
            if (providers[newposition-1] != p) {
                fail("Case 4. Provider not inserted at position " + newposition);
            }
        
            try {
                Security.insertProviderAt(null, position);
                fail("Case 5. No expected NullPointerException.");
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
            if (newposition != (providersNumber + 1)) {
                fail("Case 1. Newposition is " + newposition + ", should be " + (providersNumber + 1));
            }
            providers = Security.getProviders();
            if (providers[newposition-1] != p) {
                fail("Case 1. Provider not inserted at position " + newposition);
            }
        
            // A provider cannot be added if it is already installed
            newposition = Security.addProvider(p);
            if (newposition != -1) {
                fail("Case 2. Newposition is " + newposition + ", should be -1");
            }
        
            try {
                Security.addProvider(null);
                fail("Case 3. No expected NullPointerException.");
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
            if (Security.getProviders().length != 0) {
                fail("Providers not removed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {    // restore providers
            for (int i = 0; i < providers.length; i++) {
                Security.addProvider(providers[i]);
            }
            providers1 = Security.getProviders();
            for (int i = 0; i < providers1.length; i++) {
                if (providers[i] != providers1[i]) {
                    fail("Providers not restored correctly");
                }
            }
        }
    }

    /*
     * Class under test for Provider getProvider(String)
     */
    public final void testGetProvider() {
        Provider p = new MyProvider();
        Provider p1;
        
        try {
            Security.addProvider(p);
            
            p1 = Security.getProvider(p.getName());
            if (p1 != p) {
                fail("Case 1. Incorrect provider is returned");
            }
        
            // Returns null if no provider with the specified name is installed 
            p1 = Security.getProvider("SOMEINCORRECTPROVIDERNAME");
            if (p1 != null) {
                fail("Case 2. Incorrect provider is returned");
            }
        
            // Returns null if name is null
            p1 = Security.getProvider(null);
            if (p1 != null) {
                fail("Case 2. Incorrect provider is returned");
            }
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
        if (!"".equals(Security.getProperty("")) ) {
            fail("Case 4. Empty property test failed");            
        }
        
        Security.setProperty("My Test Property", "My property value");
        if (!"My property value".equals(Security.getProperty("My Test Property")) ) {
            fail("Case 5. Not empty property test failed");            
        }
    
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
