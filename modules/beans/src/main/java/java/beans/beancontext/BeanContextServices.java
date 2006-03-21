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
 * @version $Revision: 1.2.4.3 $
 */
package java.beans.beancontext;

import java.util.Iterator;
import java.util.TooManyListenersException;

/**
 * @author Sergei A. Krivenko
 * @version $Revision: 1.2.4.3 $
 */

public interface BeanContextServices 
        extends BeanContext, BeanContextServicesListener {
    
    /**
     * @com.intel.drl.spec_ref
     */
    public void addBeanContextServicesListener(
            BeanContextServicesListener bcsl);
    
    /**
     * @com.intel.drl.spec_ref
     */
    public boolean addService(Class serviceClass, 
        BeanContextServiceProvider serviceProvider);
        
    /**
     * @com.intel.drl.spec_ref
     */
    public Iterator getCurrentServiceClasses();
    
    /**
     * @com.intel.drl.spec_ref
     */
    public Iterator getCurrentServiceSelectors(Class serviceClass);

    /**
     * @com.intel.drl.spec_ref
     */
    public Object getService(BeanContextChild child, Object requestor, 
        Class serviceClass, Object serviceSelector, 
        BeanContextServiceRevokedListener bcsrl)
            throws TooManyListenersException;

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean hasService(Class serviceClass);
        
    /**
     * @com.intel.drl.spec_ref
     */
    public void releaseService(BeanContextChild child, Object requestor, 
        Object service);

    /**
     * @com.intel.drl.spec_ref
     */
    public void removeBeanContextServicesListener(
            BeanContextServicesListener bcsl);

    /**
     * @com.intel.drl.spec_ref
     */
    public void revokeService(Class serviceClass, 
        BeanContextServiceProvider serviceProvider,
        boolean revokeCurrentServicesNow);
}
