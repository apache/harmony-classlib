/* Copyright 2005 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tests.api.java.beans;

import java.awt.Image;
import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;

import junit.framework.TestCase;

/**
 * Test the signature of the interface BeanInfo.
 */
public class BeanInfoTest extends TestCase {

    public void testSignature() {
        DummyBeanInfo o = new DummyBeanInfo();
        assertTrue(o instanceof BeanInfo);
    }
    
    static class DummyBeanInfo implements BeanInfo {
        
        /* (non-Javadoc)
         * @see java.beans.BeanInfo#getAdditionalBeanInfo()
         */
        public BeanInfo[] getAdditionalBeanInfo() {
            return null;
        }

        /* (non-Javadoc)
         * @see java.beans.BeanInfo#getBeanDescriptor()
         */
        public BeanDescriptor getBeanDescriptor() {
            return null;
        }

        /* (non-Javadoc)
         * @see java.beans.BeanInfo#getDefaultEventIndex()
         */
        public int getDefaultEventIndex() {
            return 0;
        }

        /* (non-Javadoc)
         * @see java.beans.BeanInfo#getDefaultPropertyIndex()
         */
        public int getDefaultPropertyIndex() {
            return 0;
        }

        /* (non-Javadoc)
         * @see java.beans.BeanInfo#getEventSetDescriptors()
         */
        public EventSetDescriptor[] getEventSetDescriptors() {
            return null;
        }

        /* (non-Javadoc)
         * @see java.beans.BeanInfo#getIcon(int)
         */
        public Image getIcon(int iconKind) {
            return null;
        }

        /* (non-Javadoc)
         * @see java.beans.BeanInfo#getMethodDescriptors()
         */
        public MethodDescriptor[] getMethodDescriptors() {
            return null;
        }

        /* (non-Javadoc)
         * @see java.beans.BeanInfo#getPropertyDescriptors()
         */
        public PropertyDescriptor[] getPropertyDescriptors() {
            return null;
        }
    }
}
