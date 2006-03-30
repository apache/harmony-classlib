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
package org.apache.harmony.tests.java.beans.infos;

import java.beans.MethodDescriptor;
import java.beans.SimpleBeanInfo;
import org.apache.harmony.tests.java.beans.auxiliary.SampleBean;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.2.6.3 $
 */

public class SampleBeanBeanInfo extends SimpleBeanInfo {
    
    public MethodDescriptor[] getMethodDescriptors() {
        try {
            return new MethodDescriptor[] {
                new MethodDescriptor(SampleBean.class.getDeclaredMethod("getX",
                        null))
            };
        } catch (Exception e) {
            return null;
        }
    }
}
