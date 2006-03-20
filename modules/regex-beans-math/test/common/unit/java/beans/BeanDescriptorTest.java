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
 * @version $Revision: 1.1.6.3 $
 */
package java.beans;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * The test checks the class java.beans.BeanDescriptor
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.1.6.3 $
 */

public class BeanDescriptorTest extends TestCase {
    
    /**
     * 
     */
    public BeanDescriptorTest() {
        super();
    }
    
    /**
     *
     */
    public BeanDescriptorTest(String name) {
        super(name);
    }
    
    /**
     * The test checks the method testNullaryConstructor()
     */
    public void testNullaryConstructor() {
        BeanDescriptor bd= new BeanDescriptor(String.class);
        assertEquals(bd.getName(), "String");
    }
    
    /**
     * 
     */
    public static Test suite() {
        return new TestSuite(BeanDescriptorTest.class);
    }
    
    /**
     * 
     */
    public static void main(String[] args) {
        TestRunner.run(suite());
    }
}
