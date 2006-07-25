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

/**
 * @author Sergei A. Krivenko
 * @version $Revision: 1.2.4.3 $
 */

public class BeanContextServiceAvailableEvent extends BeanContextEvent {

    static final long serialVersionUID = -5333985775656400778L;

    /**
     * @serial
     */
    protected Class serviceClass;

    /**
     * @com.intel.drl.spec_ref
     */
    public BeanContextServiceAvailableEvent(BeanContextServices bcs, Class sc) {
        super(bcs);
        this.serviceClass = sc;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Iterator getCurrentServiceSelectors() {
        return ((BeanContextServices) super.source)
                .getCurrentServiceSelectors(serviceClass);
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
        return (BeanContextServices) super.source;
    }
}
