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
package org.apache.harmony.beans.tests.support;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.2.6.3 $
 */

public class ParentBeanBeanInfo extends SimpleBeanInfo {
    
    public ParentBeanBeanInfo() {
        super();
    }
    
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            return new PropertyDescriptor[] {
                new PropertyDescriptor("parentText", ParentBean.class,
                        "getText", "setText")
            };
        } catch (IntrospectionException ie) {
            System.out.println("in ParentBeanBeanInfo.getPropertyDescriptors: "
                    + ie.getMessage());
            return null;
        }
        
    }
}
