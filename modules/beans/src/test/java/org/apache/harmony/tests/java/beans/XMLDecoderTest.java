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
 * @version $Revision: 1.3.6.3 $
 */
package org.apache.harmony.tests.java.beans;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import java.beans.Introspector;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

/**
 * The test checks the class java.beans.XMLDecoder
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.3.6.3 $
 */

public class XMLDecoderTest extends TestCase {
    
    /**
     * 
     */
    public XMLDecoderTest() {
        super();
    }
    
    /**
     *
     */
    public XMLDecoderTest(String name) {
        super(name);
    }

    /**
     * The test checks the code generation for XML from Test1.xml
     */
    public void testDecodeLinkedList() {
        decode("xml/Test1.xml");
    }

    /**
     * The test checks the code generation for XML from Test2.xml
     */
    public void testDecodePrimitiveArrayByLength() {
        decode("xml/Test2.xml");
    }
    
    /**
     * The test checks the code generation for XML from Test3.xml
     */
    public void testDecodePrimitiveArrayByElements() {
        decode("xml/Test3.xml");
    }
    
    /**
     * The test checks the code generation for XML from Test4.xml
     */
    public void testDecodeObjectArrayByLength() {
        decode("xml/Test4.xml");
    }
    
    /**
     * The test checks the code generation for XML from Test5.xml
     */
    public void testDecodeObjectArrayByElements() {
        decode("xml/Test5.xml");
    }
    
    /**
     * The test checks the code generation for XML from Test6.xml
     */
    public void testDecodeReference() {
        decode("xml/Test6.xml");
    }
    
    /**
     * The test checks the code generation for XML from Test7.xml
     */
    public void testDecodeStringArray() {
        decode("xml/Test7.xml");
    }

    /*
     * The test checks the code generation for XML from MainTest.xml
     * 
    public void testMain() {
        decode("xml/MainTest.xml");
    }
    */

    /**
     * 
     */
    public static Test suite() {
        return new TestSuite(XMLDecoderTest.class);
    }
    
    /**
     * 
     */
    public static void main(String[] args) {
        TestRunner.run(suite());
    }
    
    /**
     * 
     */
    private void decode(String resourceName) {
        XMLDecoder d = null;
        try {
            Introspector.setBeanInfoSearchPath(new String[] {});
            d = new XMLDecoder(new BufferedInputStream(
                                                       ClassLoader.getSystemClassLoader().getResourceAsStream(resourceName)));
            while(true) {
                Object obj = d.readObject();
            }
        } catch (ArrayIndexOutOfBoundsException aibe) {
            assertTrue(true);
        } catch (Exception e) {
            System.out.println(e.getClass() + " :" + e.getMessage());
            fail("Exception " + e.getClass() + " is thrown");
        } finally {
            if(d != null) {
                d.close();
            }
        }
    }
}
