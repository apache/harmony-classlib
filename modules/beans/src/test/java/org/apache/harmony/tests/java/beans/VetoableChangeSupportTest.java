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
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.2.6.3 $
 */
package org.apache.harmony.tests.java.beans;

import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeListenerProxy;
import java.beans.VetoableChangeSupport;
import java.beans.beancontext.BeanContextChildSupport;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * The test checks the class java.beans.VetoableChangeSupport
 * @author Maxim V. Berkultsev
 * @version $Revision$
 */

public class VetoableChangeSupportTest extends TestCase {
    
    /**
     * 
     */
    public VetoableChangeSupportTest() {
        super();
    }
    
    /**
     *
     */
    public VetoableChangeSupportTest(String name) {
        super(name);
    }

    /**
     * @tests java.beans.VetoableChangeSupport#VetoableChangeSupport(
     *        java.lang.Object)
     */
    public void testVetoableChangeSupport_null() {
        // Regression for HARMONY-228
        try {
            new VetoableChangeSupport(null);
            fail("Should throw NullPointerException.");
        } catch (NullPointerException e) {
        }
    }

    /**
     * @tests java.beans.VetoableChangeSupport#addVetoableChangeListener(java.lang.String,
     *        java.beans.VetoableChangeListener)
     */
    public void test_addPropertyChangeListenerNullNull() throws Exception {
        // Regression for HARMONY-441
        new VetoableChangeSupport("bean1")
                .addVetoableChangeListener(null, null);
    }

    
    public void test_readObject() throws Exception {
        // Regression for HARMONY-421
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(new BeanContextChildSupport());
        oos.flush();

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(
                baos.toByteArray()));
        ois.readObject();

        ois.close();
        oos.close();
    }


    /**
     * The test checks the method add() with no property specified
     */
    public void testAddVetoableChangeListener() {
        VetoableChangeSupport vcs = new VetoableChangeSupport("bean1");
        VetoableChangeListener vcl = new VetoableChangeListener() {
            public void vetoableChange(PropertyChangeEvent pce) {
            }
        };
        vcs.addVetoableChangeListener(vcl);
        VetoableChangeListener[] vcls = vcs.getVetoableChangeListeners();

        assertNotNull("Returned listeners is null.", vcls);
        assertEquals(1, vcls.length);
        assertEquals(vcl, vcls[0]);
    }
    
    /**
     * The test checks the method add() for property specified
     */
    public void testAddVetoableChangeListenerByPropertyName() {
        VetoableChangeSupport vcs = new VetoableChangeSupport("bean1");
        VetoableChangeListener vcl = new VetoableChangeListener() {
            public void vetoableChange(PropertyChangeEvent pce) {
            }
        };
        vcs.addVetoableChangeListener("property1", vcl);
        VetoableChangeListener[] vcls = vcs.getVetoableChangeListeners(
                "property1");

        assertNotNull("Returned listeners is null.", vcls);
        assertEquals(1, vcls.length);
        assertEquals(vcl, vcls[0]);
    }
    
    /**
     * The test checks the method add() for VetoableChangeListenerProxy
     */
    public void testAddVetoableChangeListenerProxy() {
        VetoableChangeSupport vcs = new VetoableChangeSupport("bean1");
        VetoableChangeListener vcl = new VetoableChangeListener() {
            public void vetoableChange(PropertyChangeEvent pce) {}
        };
        vcs.addVetoableChangeListener("property1", vcl);
        VetoableChangeListener[] vcls = vcs.getVetoableChangeListeners();

        assertNotNull("Returned listeners is null.", vcls);
        assertEquals(1, vcls.length);

        assertTrue("Listener is not of VetoableChangeListenerProxy type",
                vcls[0] instanceof VetoableChangeListenerProxy);
        assertEquals(vcl, ((VetoableChangeListenerProxy) vcls[0]).getListener());
        assertEquals("property1", ((VetoableChangeListenerProxy) vcls[0])
                .getPropertyName());
    }
    
    /**
     * 
     */
    public static Test suite() {
        return new TestSuite(VetoableChangeSupportTest.class);
    }
    
    /**
     * 
     */
    public static void main(String[] args) {
        TestRunner.run(suite());
    }
}
