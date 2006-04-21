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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.apache.harmony.tests.java.beans.auxiliary.SerializablePropertyChangeListener;
import org.apache.harmony.tests.java.beans.auxiliary.NonSerializablePropertyChangeListener;

/**
 * The test checks the class java.beans.PropertyChangeSupport
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.2.6.3 $
 */

public class PropertyChangeSupportTest extends TestCase {
    
    /**
     * 
     */
    public PropertyChangeSupportTest() {
        super();
    }
    
    /**
     *
     */
    public PropertyChangeSupportTest(String name) {
        super(name);
    }

    /**
     * @tests java.beans.PropertyChangeSupport#PropertyChangeSupport(
     *        java.lang.Object)
     */
    public void testConstructor_Null() {
        try {
            // Regression for HARMONY-227
            new PropertyChangeSupport(null);
            fail("Should throw NullPointerException!");
        } catch (NullPointerException ex) {
            // expected
        }
    }

    /**
     * The test checks the serialization for listeners supporting serialization
     */
    public void testSerializableListener() {
        writePropertyChangeListeners(new PropertyChangeListener[] { 
            new SerializablePropertyChangeListener()
        });
        PropertyChangeListener[] pcls = readPropertyChangeListeners();
    }
    
    /**
     * The test checks the serialization for listeners not supporting
     * serialization
     */
    public void testNonSerializableListener() {
        writePropertyChangeListeners(new PropertyChangeListener[] { 
            new NonSerializablePropertyChangeListener()
        });
        PropertyChangeListener[] pcls = readPropertyChangeListeners();
    }

    /**
     * 
     */
    public static Test suite() {
        return new TestSuite(PropertyChangeSupportTest.class);
    }
    
    /**
     * 
     */
    public static void main(String[] args) {
        TestRunner.run(suite());
    }
    
    private void writePropertyChangeListeners(PropertyChangeListener[] array) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("x.ser"));
            PropertyChangeSupport pcs = new PropertyChangeSupport("bean");
            if(array != null && array.length > 0) {
                for(int i = 0; i < array.length; ++i) {
                    pcs.addPropertyChangeListener(array[i]);
                }
            }
            oos.writeObject(pcs);
            oos.flush();
        } catch (Exception e) {
            System.out.println(e.getClass() + ": " + e.getMessage());
            fail("Exception is thrown in testNonSerializableListener");
        } finally {
            if(oos != null) {
                try {
                    oos.close();
                } catch (IOException ioe) {
                    fail("Exception while closing ObjectOutputStream");
                }
            }
        }
    }
    
    private PropertyChangeListener[] readPropertyChangeListeners() {
        ObjectInputStream ois = null;
        PropertyChangeSupport pcs = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("x.ser"));
            pcs = (PropertyChangeSupport) ois.readObject();
        } catch (Exception e) {
            System.out.println(e.getClass() + ": " + e.getMessage());
            fail("Exception is thrown in testNonSerializableListener while "
                    + "reading");
        } finally {
            if(ois != null) {
                try {
                    ois.close();
                } catch (IOException ioe) {
                    fail("Exception while closing ObjectInputStream");
                }
            }
        }
        
        return pcs.getPropertyChangeListeners();
        
    }
}
