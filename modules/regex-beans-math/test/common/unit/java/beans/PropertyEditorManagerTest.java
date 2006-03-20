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
 * @version $Revision: 1.4.6.3 $
 */
package java.beans;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import java.beans.PropertyEditorManager;
import java.beans.auxiliary.AnotherSampleProperty;
import java.beans.auxiliary.OtherEditor;
import java.beans.auxiliary.SampleProperty;
import java.beans.auxiliary.SamplePropertyEditor;
import java.beans.editors.AnotherSamplePropertyEditor;

/**
 * The test checks the class java.beans.PropertyEditorManager
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.4.6.3 $
 */

public class PropertyEditorManagerTest extends TestCase {
    
    /**
     * 
     */
    public PropertyEditorManagerTest() {
        super();
    }
    
    /**
     *
     */
    public PropertyEditorManagerTest(String name) {
        super(name);
    }
    
    /**
     * The test checks the method findEditor() for registered editors
     */
    public void testFindRegisteredEditor() {
        PropertyEditorManager.registerEditor(SampleProperty.class,
                OtherEditor.class);
        PropertyEditor pe = PropertyEditorManager.findEditor(
                SampleProperty.class);
        if(pe == null) {
            fail("No property editor found");
        } else {
            assertTrue(pe instanceof OtherEditor);
        }
        PropertyEditorManager.registerEditor(SampleProperty.class, null);
    }
    
    /**
     * The test checks the method findEditor() for editors found by name
     */
    public void testFindEditorByNameAddOn() {
        PropertyEditor pe = PropertyEditorManager.findEditor(
                SampleProperty.class);
        if(pe == null) {
            fail("No property editor found");
        } else {
            assertTrue(pe instanceof SamplePropertyEditor);
        }
    }
    
    /**
     * The test checks the method findEditor() for editors on search path
     */
    public void testFindEditorByDefaultLocation() {
        PropertyEditorManager.setEditorSearchPath(
                new String[] {"java.beans.editors"});
        PropertyEditor pe = PropertyEditorManager.findEditor(
                AnotherSampleProperty.class);
        if(pe == null) {
            fail("No property editor found");
        } else {
            assertTrue(pe instanceof AnotherSamplePropertyEditor);
        }
    }
    
    /**
     * 
     */
    public static Test suite() {
        return new TestSuite(PropertyEditorManagerTest.class);
    }
    
    /**
     * 
     */
    public static void main(String[] args) {
        TestRunner.run(suite());
    }
}
