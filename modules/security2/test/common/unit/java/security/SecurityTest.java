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

package java.security;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.harmony.security.TestKeyPair;
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
    
    public final void testGetAlgorithmProperty() {
        if (Security.getAlgorithmProperty(null, "MyService") != null ||
                Security.getAlgorithmProperty("MyAlgorithm", null) != null) {
            fail("Incorrect result on null parameter");
        }
        if (Security.getAlgorithmProperty("MyAlgorithm", "MyService") != null) {
            fail("Incorrect result (provider not added)");
        }
        Provider p = new MyProvider();
        Security.addProvider(p);
        try {
            if (!"SomeClassName".equals(Security.getAlgorithmProperty("MyAlGoriThm", "MySerVicE"))) {
                fail("Incorrect result (provider added)");
            }        
        } finally { //clean up
            Security.removeProvider(p.getName());
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
     * Class under test for Provider[] getProviders()
     */
    public final void testGetProviders() {
        Provider[] providers;
        
        providers = Security.getProviders();
        for (int i = 0; i < providers.length; i++) {
            if (providers[i].getProviderNumber() != i+1) { // position is 1-based
                fail("Incorrect provider number");
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

    /*
     * Class under test for Provider[] getProviders(String)
     */
    public final void testGetProvidersString() {
        Provider[] providers;
        try {
            Security.getProviders("");
            fail("No expected InvalidParameterException");
        } catch (InvalidParameterException e) {    
        }
        
        
        try {
            Security.getProviders((String)null);
            fail("No expected NullPointerException");
        } catch (NullPointerException e) {    
        }
        
        try {
            Security.getProviders("AAA.BBB CCC");
            fail("AAA.BBB CCC: No expected InvalidParameterException");
        } catch (InvalidParameterException  e) {    
        }
        
        Provider p = new MyProvider();
        
        try {
            Security.addProvider(p);
            providers = Security.getProviders("MyService.MyAlgorithm");
            if (providers == null ||
                    providers.length != 1 || 
                    providers[0] != p) {
                fail("fail for MyService.MyAlgorithm");
            }
            
            providers = Security.getProviders("MyService.MyAlgorithm KeySize:512");
            if (providers == null ||
                    providers.length != 1 ||
                    providers[0] != p) {
                fail("fail for MyService.MyAlgorithm KeySize:512");
            }
            
            providers = Security.getProviders("MyService.MyAlgorithm KeySize:1025");
            if (providers != null) {
                fail("fail for MyService.MyAlgorithm KeySize:1025");
            }
        } finally { //clean up
            Security.removeProvider(p.getName());
        }
    }

    /*
     * Class under test for Provider[] getProviders(Map)
     */
    public final void testGetProvidersMap() {
        Provider[] providers;
        Map m = new Properties();
        Security.getProviders(m);
        if (Security.getProviders(m) != null) {
            fail("Not null result on empty map");
        }
                
        try {
            Security.getProviders((Map)null);
            fail("No expected NullPointerException");
        } catch (NullPointerException e) {    
        }
        
        m.clear();
        m.put("AAA.BBB CCC", "");
        m.put("AAA.BBB", "");
        try {
            Security.getProviders(m);
            fail("attribute value is empty string: No expected InvalidParameterException");
        } catch (InvalidParameterException  e) {    
        }
        
        m.clear();
        m.put("AAA.BBB.CCC", "aaaa");
        m.put("AAA.BBB", "");
        try {
            Security.getProviders(m);
            fail("value associated with the key is not an empty string: No expected InvalidParameterException");
        } catch (InvalidParameterException  e) {    
        }
        
        Provider p = new MyProvider();
        try {
            Security.addProvider(p);
            m.clear();
            m.put("MyService.MyAlgorithm", "");
            m.put("MessageDigest.SHA-1", "");
            providers = Security.getProviders(m);
            if (providers == null || 
                    providers.length != 1 || 
                    providers[0] != p) {
                fail("fail for MyService.MyAlgorithm");
            }
            
            m.clear();
            m.put("MyService.MyAlgorithm KeySize", "512");
            m.put("MessageDigest.SHA-1", "");
            providers = Security.getProviders(m);
            if (providers == null ||
                    providers.length != 1 || 
                    providers[0] != p) {
                fail("fail for MyService.MyAlgorithm KeySize:512");
            }
            
            m.clear();
            m.put("MyService.MyAlgorithm KeySize", "1025");
            m.put("MessageDigest.SHA-1", "");
            providers = Security.getProviders(m);
            if (providers != null) {
                fail("fail for MyService.MyAlgorithm KeySize:1025");
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

    public final void testGetAlgorithms() {
        Set alg1;
        Set alg2;
        
        alg1 = Security.getAlgorithms("AAAAAAAAAAAAAAA");
        if (alg1 == null || alg1.size() != 0) {
            fail("fail for non-existent service");
        }
        
        alg1 = Security.getAlgorithms("SecureRandom");
        alg2 = Security.getAlgorithms("seCuReranDom");
        if (alg1.size() != alg2.size()) {
            fail("different size");
        }
        if (!alg2.containsAll(alg1)) {
            fail("different content");
        }
        
        Provider p = new MyProvider();
        
        try {
            Security.addProvider(p);
            alg1 = Security.getAlgorithms("MyService");
            if (alg1.size() != 1 || !alg1.contains("MyAlgorithm")) {
                fail("fail for MyService");
            }    
        } finally { //clean up
            Security.removeProvider(p.getName());
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
