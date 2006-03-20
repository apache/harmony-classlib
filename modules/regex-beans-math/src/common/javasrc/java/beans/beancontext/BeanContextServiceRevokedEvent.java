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
 * @author Sergei A. Krivenko
 * @version $Revision: 1.3.4.3 $
 */
package java.beans.beancontext;

/**
 * @author Sergei A. Krivenko
 * @version $Revision: 1.3.4.3 $
 */

public class BeanContextServiceRevokedEvent extends BeanContextEvent {

    /**
     * @serial
     */
    protected Class serviceClass;
    
    /**
     * @serial
     */
    private boolean invalidateRefs;

    /**
     * @com.intel.drl.spec_ref
     */
    public BeanContextServiceRevokedEvent(BeanContextServices bcs, Class sc, 
            boolean invalidate) {
                
        super(bcs);
        this.serviceClass = sc;
        this.invalidateRefs = invalidate;
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public Class getServiceClass() {
        return this.serviceClass;
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public BeanContextServices getSourceAsBeanContextServices() {
        return (BeanContextServices) super.propagatedFrom;
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public boolean isCurrentServiceInvalidNow() {
        return this.invalidateRefs;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean isServiceClass(Class service) {
        return service.equals(this.serviceClass);
    }
}
