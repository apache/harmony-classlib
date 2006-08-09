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
 * @version $Revision: 1.10.6.3 $
 */
package java.beans;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.10.6.3 $
 */

public abstract class PersistenceDelegate {

    /**
     * @com.intel.drl.spec_ref
     */
    public PersistenceDelegate() {
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    protected void initialize(
            Class<?> type, Object oldInstance, Object newInstance, Encoder out) {
        if ((out != null) && (type != null)) {
            PersistenceDelegate pd = out.getPersistenceDelegate(
                    type.getSuperclass());

            if (pd != null) {
                pd.initialize(type, oldInstance, newInstance, out);
            }
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    protected abstract Expression instantiate(Object oldInstance, Encoder out);

    /**
     * @com.intel.drl.spec_ref
     */
    protected boolean mutatesTo(Object oldInstance, Object newInstance) {
        boolean bothInstancesAreNull = (oldInstance == null)
                && (newInstance == null);
        
        if (bothInstancesAreNull) {
            return false;
        } else {
            return (oldInstance != null) && (newInstance != null) ? 
                oldInstance.getClass() == newInstance.getClass() : false;
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void writeObject(Object oldInstance, Encoder out) {
        
        // nulls are covered by NullPersistenceDelegate
        assert oldInstance != null;

        Object newInstance = out.get(oldInstance);
       
        if (mutatesTo(oldInstance, newInstance)) {
           initialize(oldInstance.getClass(), oldInstance, newInstance, out);
        } else {
           if (newInstance != null) {
               out.remove(newInstance);
           }
           
           out.writeExpression(instantiate(oldInstance, out));
           newInstance = out.get(oldInstance);
           initialize(oldInstance.getClass(), oldInstance, newInstance, out);
        }
    }
}
