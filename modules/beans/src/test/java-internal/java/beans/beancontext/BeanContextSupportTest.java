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
 * @version $Revision: 1.4.4.2 $
 */
package java.beans.beancontext;

import java.beans.beancontext.BeanContextSupport;
import java.beans.beancontext.BeanContextMembershipEvent;
import java.beans.beancontext.BeanContextMembershipListener;
import java.beans.PropertyVetoException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Test class for java.beans.beancontext.BeanContextSupport.<p>
 *
 * @author Sergey A. Krivenko
 * @version $Revision: 1.4.4.2 $
 */

public class BeanContextSupportTest extends TestCase {

    /** STANDARD BEGINNING **/

    /**
     * No arguments constructor to enable serialization.<p>
     */
    public BeanContextSupportTest() {
        super();
    }

    /**
     * Constructs this test case with the given name.<p>
     *
     * @param name - The name for this test case.<p>
     */
    public BeanContextSupportTest(String name) {
        super(name);
    }

    /** TEST CONSTRUCTORS **/

    /** * Test constructor with BeanContext, Locale, boolean, boolean parameters.<p>
     *
     * @see BeanContextSupport#BeanContextSupport(BeanContext, Locale, boolean, boolean)
     */
    public void testConstructorBeanContextLocalebooleanboolean() {
        try {
            BeanContextSupport sup = new BeanContextSupport(null, null, true, true);
        }
        catch (Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /** * Test constructor with BeanContext, Locale, boolean parameters.<p>
     *
     * @see BeanContextSupport#BeanContextSupport(BeanContext, Locale, boolean)
     */
    public void testConstructorBeanContextLocaleboolean() {
        try {
            BeanContextSupport sup = new BeanContextSupport(null, null, true);
        }
        catch (Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /** * Test constructor with BeanContext, Locale parameters.<p>
     *
     * @see BeanContextSupport#BeanContextSupport(BeanContext, Locale)
     */
    public void testConstructorBeanContextLocale() {
        try {
            BeanContextSupport sup = new BeanContextSupport(null, null);
        }
        catch (Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /** * Test constructor with BeanContext parameter.<p>
     *
     * @see BeanContextSupport#BeanContextSupport(BeanContext)
     */
    public void testConstructorBeanContext() {
        try {
            BeanContextSupport sup = new BeanContextSupport(null);
        }
        catch (Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /** * Test constructor with no parameters.<p>
     *
     * @see BeanContextSupport#BeanContextSupport()
     */
    public void testConstructor() {
        try {
            BeanContextSupport sup = new BeanContextSupport();
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
            BeanContextSupport sup = new BeanContextSupport();
            sup.createBCSChild(new BeanContextSupport(), new BeanContextSupport());
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method setLocale() with Locale parameter.<p>
     */
    public void testSetLocaleLocale() {
        try {
            BeanContextSupport sup = new BeanContextSupport();
            sup.setLocale(null);
            
            if (!sup.getLocale().equals(java.util.Locale.getDefault())) {
                fail("BeanContext should have default locale");
            }
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method bcsChildren() with no parameters.<p>
     */
    public void testBcsChildren() {
        try {
            BeanContextSupport sup = new BeanContextSupport();
            sup.add(new BeanContextChildSupport());
            
            for (java.util.Iterator it = sup.bcsChildren(); it.hasNext(); ) {
                Object next = it.next();
                
                if (!(next instanceof BeanContextSupport.BCSChild)) {
                    fail("Children must be instances of " +
                         "BeanContextSupport.BCSChild class " +
                         "but at least one of them: " + next.getClass());
                }
            }
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method retainAll() with Collection parameter.<p>
     */
    public void testRetainAllCollection() {
        try {
            
            /*// Create an instance and add one child
            BeanContextSupport sup = new BeanContextSupport();
            BeanContextChildSupport ch = new BeanContextChildSupport();
            sup.add(ch);
            
            // Create collection with an instance of the child that was added
            java.util.Collection col = new java.util.ArrayList();
            col.add(ch);            
            
            // Remove all children that are not in the collection
            // The collection must remain unchanged
            if (sup.retainAll(col)) {
                fail("False should be returned");
            }
            
            // Just one child must be present
            if (sup.size() != 1) {
                fail("The size of the collection must be 1");
            }
            
            // Add a new child in the collection and remove the old one
            col.clear();
            col.add(new Object());
            
            // Remove all children that are not in the collection
            // The collection must have 0 elements after that
            if (!sup.retainAll(col)) {
                fail("True should be returned");
            }
            
            // No children must be present
            if (sup.size() != 0) {
                fail("The size of the collection must be 0");
            }*/
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method removeAll() with Collection parameter.<p>
     */
    public void testRemoveAllCollection() {
        try {
            
            /*// Create an instance and add one child
            BeanContextSupport sup = new BeanContextSupport();
            BeanContextChildSupport ch = new BeanContextChildSupport();
            sup.add(ch);
            
            // Create collection with an instance of an arbitrary child
            java.util.Collection col = new java.util.ArrayList();
            col.add(new Object());    
            
            // Remove all children that are in the collection
            // The collection should not change after that
            if (sup.removeAll(col)) {
                fail("False should be returned");
            }
            
            // Add a child that is a member of the BeanContext
            col.add(ch); 
            
            // Remove all children that are in the collection
            // The collection should change after that
            if (!sup.removeAll(col)) {
                fail("True should be returned");
            }
            
            // No children must be present
            if (sup.size() != 0) {
                fail("The size of the collection must be 0 but is " + sup.size());
            }*/
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method containsAll() with Collection parameter.<p>
     */
    public void testContainsAllCollection() {
        try {
            
            /*// Create an instance and add two children
            BeanContextSupport sup = new BeanContextSupport();
            BeanContextChildSupport ch = new BeanContextChildSupport();
            Object obj = new Object();
            sup.add(ch);
            sup.add(obj);
            
            // Create collection with BCS children that just were added
            java.util.Collection col = new java.util.ArrayList();
            
            for (java.util.Iterator it = sup.bcsChildren(); it.hasNext(); ) {
                col.add(it.next());
            }
            
            // Two collections have the same elements
            if (!sup.containsAll(col)) {
                fail("True should be returned");
            }
            
            sup.remove(obj);
            
            // Now they are different
            if (sup.containsAll(col)) {
                fail("False should be returned");
            }*/
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method addAll() with Collection parameter.<p>
     */
    public void testAddAllCollection() {
        try {
            
            /*// Create an instance and add two children
            BeanContextSupport sup = new BeanContextSupport();
                     
            // Create collection with two elements
            java.util.Collection col = new java.util.ArrayList();
            col.add(new BeanContextChildSupport());
            col.add(new Object());
            
            // Place two children into the BeanContext
            if (!sup.addAll(col)) {
                fail("True should be returned");
            }
            
            // Two children must be present
            if (sup.size() != 2) {
                fail("The size of the collection must be 2 but is " + sup.size());
            }*/
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method remove() with Object, boolean parameters.<p>
     */
    public void testRemoveObjectboolean() {
        try {
            
            // Create an instance and add one child
            BeanContextSupport sup = new BeanContextSupport();
            BeanContextChildSupport ch = new BeanContextChildSupport();
            sup.add(ch);
            
            // Remove unexisting child
            if (sup.remove(new Object(), true)) {
                fail("False should be returned");
            }
            
            // Remove it
            if (!sup.remove(ch, true)) {
                fail("True should be returned");
            }
            
            // No children must be present
            if (sup.size() != 0) {
                fail("The size of the collection must be 0 but is " + sup.size());
            }
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method remove() with Object parameter.<p>
     */
    public void testRemoveObject() {
        try {
            
            // Create an instance and add one child
            BeanContextSupport sup = new BeanContextSupport();
            BeanContextChildSupport ch = new BeanContextChildSupport();
            sup.add(ch);
            
            // Remove unexisting child
            if (sup.remove(new Object())) {
                fail("False should be returned");
            }
            
            // Remove it
            if (!sup.remove(ch)) {
                fail("True should be returned");
            }
            
            // No children must be present
            if (sup.size() != 0) {
                fail("The size of the collection must be 0 but is " + sup.size());
            }
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method containsKey() with Object parameter.<p>
     */
    public void testContainsKeyObject() {
        try {
            
            // Create an instance and add a child
            BeanContextSupport sup = new BeanContextSupport();
            BeanContextChildSupport ch = new BeanContextChildSupport();
            sup.add(ch);
            
            // We should find the child now
            if (!sup.containsKey(ch)) {
                fail("True should be returned");
            }
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method contains() with Object parameter.<p>
     */
    public void testContainsObject() {
        try {
            
            // Create an instance and add a child
            BeanContextSupport sup = new BeanContextSupport();
            BeanContextChildSupport ch = new BeanContextChildSupport();
            sup.add(ch);
            
            BeanContextSupport.BCSChild bcs = 
                (BeanContextSupport.BCSChild) sup.bcsChildren().next();
            
            // We should find the child now
            if (!sup.contains(bcs)) {
                // fail("True should be returned");
            }
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method add() with Object parameter.<p>
     */
    public void testAddObject() {
        try {
            
            // Create an instance and add a child
            BeanContextSupport sup = new BeanContextSupport();
            sup.add(new Object());
            
            // Just one child must be present
            if (sup.size() != 1) {
                fail("The size of the collection must be 1 but is " + sup.size());
            }
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method toArray() with no parameters.<p>
     */
    public void testToArray() {
        try {
            
            // Create an instance and add two children
            BeanContextSupport sup = new BeanContextSupport();
            sup.add("obj1");
            sup.add("obj2");
            
            // Convert to array
            Object[] array = sup.toArray();
            
            // Check length
            if (array.length != 2) {
                fail("The size of the collection must be 2 but is " + array.length);
            }
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method copyChildren() with no parameters.<p>
     */
    public void testCopyChildren() {
        try {
            
            // Create an instance and add two children
            BeanContextSupport sup = new BeanContextSupport();
            sup.add("obj1");
            sup.add("obj2");
            
            // Convert to array
            Object[] array = sup.copyChildren();
            
            // Check length
            if (array.length != 2) {
                fail("The size of the collection must be 2 but is " + array.length);
            }
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method removeBeanContextMembershipListener() with BeanContextMembershipListener parameter.<p>
     */
    public void testRemoveBeanContextMembershipListenerBeanContextMembershipListener() {
        try {
            
            // Create BeanContext and BeanContextMembershipListener instances
            BeanContextSupport sup = new BeanContextSupport();
            BeanContextMembershipListener l = getBeanContextMembershipListener();
            sup.addBeanContextMembershipListener(l);
            sup.removeBeanContextMembershipListener(l);
            
            // Check if it's there
            if (sup.bcmListeners.contains(l)) {
                fail("Listener should not be present");
            }
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method addBeanContextMembershipListener() with BeanContextMembershipListener parameter.<p>
     */
    public void testAddBeanContextMembershipListenerBeanContextMembershipListener() {
        try {
            
            // Create BeanContext and BeanContextMembershipListener instances
            BeanContextSupport sup = new BeanContextSupport();
            BeanContextMembershipListener l = getBeanContextMembershipListener();
            sup.addBeanContextMembershipListener(l);
            
            // Check if it's there
            if (!sup.bcmListeners.contains(l)) {
                fail("Listener should be present");
            }
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method getBeanContextPeer() with no parameters.<p>
     */
    public void testGetBeanContextPeer() {
        try {
            
            // Create BeanContext instance
            BeanContextSupport sup = new BeanContextSupport();
            
            // The peer and this context should be equal
            if (!sup.getBeanContextPeer().equals(sup)) {
                fail("The peer and the BeanContext should be equal");
            }
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method vetoableChange() with PropertyChangeEvent parameter.<p>
     */
    public void testVetoableChangePropertyChangeEvent() {
        try {
            
            /** @todo: not implemented yet in the class **/
            // Create BeanContext instance
            BeanContextSupport sup = new BeanContextSupport();
            //sup.vetoableChange(null);
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method propertyChange() with PropertyChangeEvent parameter.<p>
     */
    public void testPropertyChangePropertyChangeEvent() {
        try {
            
            /** @todo: not implemented yet in the class **/
            // Create BeanContext instance
            BeanContextSupport sup = new BeanContextSupport();
            //sup.propertyChange(null);
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method isEmpty() with no parameters.<p>
     */
    public void testIsEmpty() {
        try {
            
            // Create BeanContext instance
            BeanContextSupport sup = new BeanContextSupport();
            
            if (!sup.isEmpty()) {
                fail("The collection of children should be empty");
            } 
            
            sup.add(new Object());
            
            if (sup.isEmpty()) {
                fail("The collection of children should not be empty");
            }
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method clear() with no parameters.<p>
     */
    public void testClear() {
        try {
            
            /*// Create BeanContext instance
            BeanContextSupport sup = new BeanContextSupport();
            
            // Add a child and then clear
            sup.add(new Object());
            sup.clear();
            
            if (!sup.isEmpty()) {
                fail("The collection of children should be empty");
            }*/
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method size() with no parameters.<p>
     */
    public void testSize() {
        try {
            
            // Create BeanContext instance
            BeanContextSupport sup = new BeanContextSupport();
            
            if (sup.size() != 0) {
                fail("The size of the collection should be equal to 0");
            }
            
            sup.add(new Object());
            
            if (sup.size() != 1) {
                fail("The size of the collection should be equal to 1");
            }
        }
        catch(Exception e) {
            fail("Unexpected exception: " + e + " caused by: " + e.getCause());
        }
    }

    /**
     * Test method getResourceAsStream() with String, BeanContextChild=null parameters.<p>
     */
    public void test_getResourceAsStreamLlava_lang_StringLjava_beans_beancontext_BeanContextChild() {
        BeanContextSupport obj = new BeanContextSupport();
        try {
            obj.getResourceAsStream(new String(), null);
            fail("NullPointerException expected");
        } catch (NullPointerException t) {
        }
    }

    /**
     * Test method getResourceAsStream() with String=null, BeanContextChild=null parameters.<p>
     */
    public void test_getResourceAsStreamLlava_lang_StringLjava_beans_beancontext_BeanContextChild2() {
        BeanContextSupport obj = new BeanContextSupport();
        try {
            obj.getResourceAsStream(null, null);
            fail("NullPointerException expected");
        } catch (IllegalArgumentException t) {
            fail("NullPointerException expected");
        } catch (NullPointerException t) {
        }
    }

    /**
     * Test method vetoableChange() with PropertyChangeEvent=null parameter.<p>
     */
    public void test_vetoableChangeLjava_beans_PropertyChangeEvent() {
        BeanContextSupport obj = new BeanContextSupport();
        try {
            obj.vetoableChange(null);
            fail("NullPointerException expected");
        } catch (PropertyVetoException p) {
            fail("NullPointerException expected");
        } catch (NullPointerException t) {
        }
    }

    /**
     * Test method getResource() with String!=null, BeanContextChild=null parameters.<p>
     */
    public void test_getResourceLjava_lang_StringLjava_beans_beancontext_BeanContextChild() {
        BeanContextSupport obj = new BeanContextSupport();
        try {
            obj.getResource("", null);
            fail("NullPointerException expected");
        } catch (NullPointerException t) {
        }
    }

    /**
     * Test method getResource() with String=null, BeanContextChild=null parameters.<p>
     */
    public void test_getResourceLjava_lang_StringLjava_beans_beancontext_BeanContextChild2() {
        BeanContextSupport obj = new BeanContextSupport();
        try {
            obj.getResource(null, null);
            fail("NullPointerException expected");
        } catch (IllegalArgumentException t) {
            fail("NullPointerException expected");
        } catch (NullPointerException t) {
        }
    }

    /** UTILITY METHODS **/
    
    /**
     * Create BeanContextMembershipListener instance
     */
    private BeanContextMembershipListener getBeanContextMembershipListener() {
        return new BeanContextMembershipListener() {
                    
            public void childrenAdded(BeanContextMembershipEvent bcme) {
                ;
            }
            
            public void childrenRemoved(BeanContextMembershipEvent bcme) {
                ;
            }
        };
    }

    /** STANDARD ENDING **/

    /**
     * Start testing from the command line.<p>
     */
    public static Test suite() {
        return new TestSuite(BeanContextSupportTest.class);
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