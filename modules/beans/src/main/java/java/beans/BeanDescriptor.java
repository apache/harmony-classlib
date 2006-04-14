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

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.4.6.3 $
 */

public class BeanDescriptor extends FeatureDescriptor {
	
    private Class beanClass;
    private Class customizerClass;

    /**
     * @com.intel.drl.spec_ref
     */
    public BeanDescriptor(Class beanClass, Class customizerClass) {
        super();

        if (beanClass == null) {
            throw new NullPointerException();
        }
        setName(getShortClassName(beanClass));
        this.beanClass = beanClass;
        this.customizerClass = customizerClass;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BeanDescriptor(Class beanClass) {
        super();

        if (beanClass == null) {
            throw new NullPointerException();
        }
        setName(getShortClassName(beanClass));
        this.beanClass = beanClass;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Class getCustomizerClass() {
        return customizerClass;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Class getBeanClass() {
        return beanClass;
    }
    
    private String getShortClassName(Class beanClass) {
        String result = null;
        
        if(beanClass != null) {
            String beanClassName = beanClass.getName();
            int idx = beanClassName.lastIndexOf(".");
            result = (idx == -1) ? beanClassName : beanClassName.substring(idx
                    + 1);
        }
        
        return result;
    }

}
