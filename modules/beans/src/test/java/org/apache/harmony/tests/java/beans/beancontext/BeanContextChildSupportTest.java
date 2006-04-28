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
 * @version $Revision: 1.2.4.2 $
 */
package org.apache.harmony.tests.java.beans.beancontext;

import java.beans.beancontext.BeanContextChildSupport;
import java.beans.beancontext.BeanContextServiceAvailableEvent;
import java.beans.beancontext.BeanContextServiceRevokedEvent;
import java.beans.beancontext.BeanContextSupport;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Test class for java.beans.beancontext.BeanContextChildSupport.<p>
 *
 * @author Sergey A. Krivenko
 * @version $Revision: 1.2.4.2 $
 */

public class BeanContextChildSupportTest extends TestCase {

    /** STANDARD BEGINNING **/

    /**
     * No arguments constructor to enable serialization.<p>
     */
    public BeanContextChildSupportTest() {
        super();
    }

    /**
     * Constructs this test case with the given name.<p>
     *
     * @param name - The name for this test case.<p>
     */
    public BeanContextChildSupportTest(String name) {
        super(name);
    }

    /** TEST CONSTRUCTORS **/

    /** * Test constructor with BeanContextChild parameter.<p>
     *
     * @see BeanContextChildSupport#BeanContextChildSupport(BeanContextChild)
     */
    public void testConstructorBeanContextChild() {
        try {
            BeanContextChildSupport sup = new BeanContextChildSupport(null);
        }
        catch (Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /** * Test constructor with no parameters.<p>
     *
     * @see BeanContextChildSupport#BeanContextChildSupport()
     */
    public void testConstructor() {
        try {
            BeanContextChildSupport sup = new BeanContextChildSupport();
        }
        catch (Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /** TEST METHODS **/

    /**
     * Test method getBeanContextChildPeer() with no parameters.<p>
     */
    public void testGetBeanContextChildPeer() {
        try {
            BeanContextChildSupport sup = new BeanContextChildSupport();
            
            if (!sup.getBeanContextChildPeer().equals(sup)) {
                fail("The objects should be equal");
            }
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method setBeanContext() with BeanContext parameter.<p>
     */
    public void testSetBeanContextBeanContext() {
        try {
            BeanContextChildSupport sup = new BeanContextChildSupport();
            sup.setBeanContext(new BeanContextSupport());
            
            if (sup.getBeanContext() == null) {
                fail("BeanContext should not be null");
            }
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method isDelegated() with no parameters.<p>
     */
    public void testIsDelegated() {
        try {
            BeanContextChildSupport sup = new BeanContextChildSupport();
            
            if (sup.isDelegated()) {
                fail("Child is not supposed to be delegated");
            }
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * @test java.beans.beancontext.BeanContextChildSupport#serviceAvailable(java.beans.beancontext.BeanContextServiceAvailableEvent)
     * @test java.beans.beancontext.BeanContextChildSupport#serviceRevoked(java.beans.beancontext.BeanContextServiceRevokedEvent)
     */
    public void testServiceAvailable() {
        //Regression for HARMONY-372
        (new java.beans.beancontext.BeanContextChildSupport()).serviceAvailable(null);
        (new java.beans.beancontext.BeanContextChildSupport()).serviceRevoked(null); 
    }

    /** UTILITY METHODS **/

    /** STANDARD ENDING **/

    /**
     * Start testing from the command line.<p>
     */
    public static Test suite() {
        return new TestSuite(BeanContextChildSupportTest.class);
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