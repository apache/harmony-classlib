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
package java.beans;

import java.awt.Image;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.2.6.3 $
 */

public interface BeanInfo {

    public static final int ICON_COLOR_16x16 = 1;

    public static final int ICON_COLOR_32x32 = 2;

    public static final int ICON_MONO_16x16 = 3;

    public static final int ICON_MONO_32x32 = 4;

    /**
     * @com.intel.drl.spec_ref
     */
    public PropertyDescriptor[] getPropertyDescriptors();

    /**
     * @com.intel.drl.spec_ref
     */
    public MethodDescriptor[] getMethodDescriptors();

    /**
     * @com.intel.drl.spec_ref
     */
    public EventSetDescriptor[] getEventSetDescriptors();

    /**
     * @com.intel.drl.spec_ref
     */
    public BeanInfo[] getAdditionalBeanInfo();

    /**
     * @com.intel.drl.spec_ref
     */
    public BeanDescriptor getBeanDescriptor();

    /**
     * @com.intel.drl.spec_ref
     */
    public Image getIcon(int iconKind);

    /**
     * @com.intel.drl.spec_ref
     */
    public int getDefaultPropertyIndex();

    /**
     * @com.intel.drl.spec_ref
     */
    public int getDefaultEventIndex();
}
