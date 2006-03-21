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
 * @author Sergey A. Krivenko
 * @version $Revision: 1.5.2.2 $
 */
package java.beans.beancontext;

import java.beans.beancontext.BeanContextServices;
import java.beans.beancontext.BeanContextServicesSupport;
import java.beans.beancontext.BeanContextServiceProvider;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Test class for java.beans.beancontext.BeanContextServicesSupport.<p>
 *
 * @author Sergey A. Krivenko
 * @version $Revision: 1.5.2.2 $
 */

public class BeanContextServicesSupportTest extends TestCase {

    /** STANDARD BEGINNING **/

    /**
     * No arguments constructor to enable serialization.<p>
     */
    public BeanContextServicesSupportTest() {
        super();
    }

    /**
     * Constructs this test case with the given name.<p>
     *
     * @param name - The name for this test case.<p>
     */
    public BeanContextServicesSupportTest(String name) {
        super(name);
    }

    /** TEST CONSTRUCTORS **/

    /** 
     * Test constructor with BeanContextServices, Locale, boolean, 
     * boolean parameters.<p>
     *
     * @see BeanContextServicesSupport#BeanContextServicesSupport(
     * BeanContextServices, Locale, boolean, boolean)
     */
    public void testConstructorBeanContextServicesLocalebooleanboolean() {
        try {
            BeanContextServicesSupport sup = 
                new BeanContextServicesSupport(null, null, true, true);
        }
        catch (Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /** 
     * Test constructor with BeanContextServices, Locale, boolean parameters
     *
     * @see BeanContextServicesSupport#BeanContextServicesSupport(
     * BeanContextServices, Locale, boolean)
     */
    public void testConstructorBeanContextServicesLocaleboolean() {
        try {
            BeanContextServicesSupport sup = 
                new BeanContextServicesSupport(null, null, true);
        }
        catch (Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /** 
     * Test constructor with BeanContextServices, Locale parameters.<p>
     *
     * @see BeanContextServicesSupport#BeanContextServicesSupport(
     * BeanContextServices, Locale)
     */
    public void testConstructorBeanContextServicesLocale() {
        try {
            BeanContextServicesSupport sup = 
                new BeanContextServicesSupport(null, null);
        }
        catch (Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /** 
     * Test constructor with BeanContextServices parameter.<p>
     *
     * @see BeanContextServicesSupport#BeanContextServicesSupport(
     * BeanContextServices)
     */
    public void testConstructorBeanContextServices() {
        try {
            BeanContextServicesSupport sup = 
                new BeanContextServicesSupport(null);
        }
        catch (Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /** * Test constructor with no parameters.<p>
     *
     * @see BeanContextServicesSupport#BeanContextServicesSupport()
     */
    public void testConstructor() {
        try {
            BeanContextServicesSupport sup = new BeanContextServicesSupport();
        }
        catch (Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /** TEST METHODS **/

    /**
     * Test method createBCSChild() with Object, Object parameters.<p>
     */
    public void testCreateBCSChildObjectObject() {
        try {
            
            // Just call the method
            BeanContextServicesSupport sup = new BeanContextServicesSupport();
            sup.createBCSChild(new Object(), new Object());
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method addService() with Class, BeanContextServiceProvider, boolean parameters.<p>
     */
    public void testAddServiceClassBeanContextServiceProviderboolean() {
        try {
            
            // Instantiate services and add service
            BeanContextServicesSupport sup = new BeanContextServicesSupport();
            sup.addService(Object.class, getProvider(), true);
            
            if (sup.services.size() != 1) {
                fail("One service should be registered");
            }
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method revokeService() with Class, BeanContextServiceProvider, boolean parameters.<p>
     */
    public void testRevokeServiceClassBeanContextServiceProviderboolean() {
        try {
            
            // Instantiate services, add and remove service
            BeanContextServicesSupport sup = new BeanContextServicesSupport();
            BeanContextServiceProvider pr = getProvider();
            sup.addService(Object.class, pr, true);
            sup.revokeService(Object.class, pr, true);
            
            if (sup.services.size() != 0) {
                fail("No service should be registered");
            }
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method addService() with Class, BeanContextServiceProvider parameters.<p>
     */
    public void testAddServiceClassBeanContextServiceProvider() {
        try {
            
            // Instantiate services and add service
            BeanContextServicesSupport sup = new BeanContextServicesSupport();
            sup.addService(Object.class, getProvider());
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method hasService() with Class parameter.<p>
     */
    public void testHasServiceClass() {
        try {
            
            // Instantiate services and add service
            BeanContextServicesSupport sup = new BeanContextServicesSupport();
            Class cl = new Object().getClass();
            sup.addService(cl, getProvider(), true);
            
            if (!sup.hasService(cl)) {
                fail("Service not found");
            }
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method getBeanContextServicesPeer() with no parameters.<p>
     */
    public void testGetBeanContextServicesPeer() {
        try {
            
            // Instantiate services
            BeanContextServicesSupport sup = new BeanContextServicesSupport();
            
            if (!sup.getBeanContextServicesPeer().equals(sup)) {
                fail("The objects are not equal");
            }
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method releaseBeanContextResources() with no parameters.<p>
     */
    public void testReleaseBeanContextResources() {
        try {
            
            // Instantiate services
            BeanContextServicesSupport sup = new BeanContextServicesSupport();
            sup.releaseBeanContextResources();
            
            if (sup.proxy != null) {
                fail("The resources are not released");
            }
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method initializeBeanContextResources() with no parameters.<p>
     */
    public void testInitializeBeanContextResources() {
        try {
            
            // Instantiate services
            BeanContextServicesSupport sup = new BeanContextServicesSupport();
            sup.initializeBeanContextResources();
            
            //if (sup.proxy == null) {
                //fail("The resources are not initialized");
            //}
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /** UTILITY METHODS **/
    
    /**
     * Fake implementation of provider
     */
    private BeanContextServiceProvider getProvider() {
    
        return new BeanContextServiceProvider() {

            public java.util.Iterator getCurrentServiceSelectors(BeanContextServices bcs, 
                    Class serviceClass) {
                        
                return bcs.getCurrentServiceSelectors(serviceClass);
            }
            
            public Object getService(BeanContextServices bcs, Object requestor,
                    Class serviceClass, Object serviceSelector) {                            
                
                return null;
            }
            
            public void releaseService(BeanContextServices bcs, Object requestor, 
                    Object service) {
            }
        };
    }

    /** STANDARD ENDING **/

    /**
     * Start testing from the command line.<p>
     */
    public static Test suite() {
        return new TestSuite(BeanContextServicesSupportTest.class);
    }

    /**
     * Start testing from the command line.<p>
     *
     * @param args - Command line parameters.<p>
     */
    public static void main(String args[]) {
        junit.textui.TestRunner.run(suite());
    }
}