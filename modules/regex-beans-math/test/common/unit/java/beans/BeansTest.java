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
 * @version $Revision: 1.3.6.4 $
 */
package java.beans;

import java.beans.auxiliary.SampleBean;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * The test checks the class java.beans.Beans
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.3.6.4 $
 */

public class BeansTest extends TestCase {
    
    /**
     * 
     */
    public BeansTest() {
        super();
    }
    
    /**
     *
     */
    public BeansTest(String name) {
        super(name);
    }
    
    /**
     * The test checks the method instantiate()
     * using specific classloader for class loading
     */
    public void testLoadBySpecificClassLoader() {
        String beanName = "java.beans.auxiliary.SampleBean";
        
        try {
            ClassLoader cls = createSpecificClassLoader();
            Object bean = Beans.instantiate(cls, beanName);
            
            assertNotNull(bean);
            assertEquals(bean.getClass(), SampleBean.class);
            
            SampleBean sampleBean = (SampleBean) bean;
            checkValues(sampleBean);
        } catch (ClassNotFoundException cnfe) {
            fail("Class with name " + beanName + " is not found");
        } catch (IOException ioe) {
            fail("IOException is thrown while loading " + beanName + " class");
        }
    }
    
    /**
     * The test checks the method instantiate()
     * using default classloader for class loading
     */
    public void testLoadByDefaultClassLoader() {
        String beanName = "java.beans.auxiliary.SampleBean";
        
        try {
            Object bean = Beans.instantiate(null, beanName);
            
            assertNotNull(bean);
            assertEquals(bean.getClass(), SampleBean.class);
            
            SampleBean sampleBean = (SampleBean) bean;
            checkValues(sampleBean);
        } catch (ClassNotFoundException cnfe) {
            fail("Class with name " + beanName + " is not found");
        } catch (IOException ioe) {
            fail("IOException is thrown while loading " + beanName + " class");
        }
    }

    /**
     * 
     */
    public static Test suite() {
        return new TestSuite(BeansTest.class);
    }
    
    /**
     * 
     */
    public static void main(String[] args) {
        TestRunner.run(suite());
    }
    
    private ClassLoader createSpecificClassLoader() {
        return new ClassLoader() {
            public Class loadClass(String name) throws ClassNotFoundException {
                Class result = super.loadClass(name);
                return result;
            }
        };        
    }
    
    private void checkValues(SampleBean sampleBean) {
        assertEquals(null, sampleBean.getText());
    }
}