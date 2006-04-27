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

import java.util.EventObject;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.4.6.3 $
 */

public class PropertyChangeEvent extends EventObject {
    
    private static final long serialVersionUID = 7042693688939648123L;
    
    String propertyName;
    Object oldValue;
    Object newValue;
    Object propagationId;
    
    /**
     * @com.intel.drl.spec_ref
     */
    public PropertyChangeEvent(Object source, String propertyName,
            Object oldValue, Object newValue) {
        super(source);
        
        this.propertyName = propertyName;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setPropagationId(Object propagationId) {
        this.propagationId = propagationId;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Object getPropagationId() {
        return propagationId;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Object getOldValue() {
        return oldValue;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Object getNewValue() {
        return newValue;
    }
}
