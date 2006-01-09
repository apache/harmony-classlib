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
* @author Alexey V. Varlamov
* @version $Revision$
*/

package java.security;

import java.io.IOException;
import java.io.Serializable;

/**
 * @com.intel.drl.spec_ref
 * 
 */
public class GuardedObject implements Serializable {

    /**
     * @com.intel.drl.spec_ref
     */
    private static final long serialVersionUID = -5240450096227834308L;

    /**
     * @com.intel.drl.spec_ref
     */
    private final Object object;

    /**
     * @com.intel.drl.spec_ref
     */
    private final Guard guard;

    /** 
     * @com.intel.drl.spec_ref 
     */
    public GuardedObject(Object object, Guard guard) {
        this.object = object;
        this.guard = guard;
    }

    /** 
     * @com.intel.drl.spec_ref 
     */
    public Object getObject() throws SecurityException {
        if (guard != null) {
            guard.checkGuard(object);
        }
        return object;
    }

    /** 
     * Checks guard (if there is one) before performing a default serialization. 
     */
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        if (guard != null) {
            guard.checkGuard(object);
        }
        out.defaultWriteObject();
    }
}