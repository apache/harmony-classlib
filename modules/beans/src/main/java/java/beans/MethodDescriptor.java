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
 * @version $Revision: 1.5.4.3 $
 */
package java.beans;

import java.lang.reflect.Method;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.5.4.3 $
 */

public class MethodDescriptor extends FeatureDescriptor {
    
    private Method method = null;
    private ParameterDescriptor[] parameterDescriptors = {};

    /**
     * @com.intel.drl.spec_ref
     */
    public MethodDescriptor(Method method,
            ParameterDescriptor[] parameterDescriptors) {
        super();
        
        this.method = method;
        this.parameterDescriptors = parameterDescriptors;
        
        if(method != null) {
            setName(method.getName());
            setDisplayName(method.getName());
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public MethodDescriptor(Method method) {
        super();
        
        this.method = method;
        if(method != null) {
            setName(method.getName());
            setDisplayName(method.getName());
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Method getMethod() {
        return method;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public ParameterDescriptor[] getParameterDescriptors() {
        return parameterDescriptors;
    }
}
