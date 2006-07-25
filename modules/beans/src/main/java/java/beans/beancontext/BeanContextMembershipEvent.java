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
 * @version $Revision: 1.4.4.3 $
 */
package java.beans.beancontext;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

/**
 * @author Sergei A. Krivenko
 * @version $Revision: 1.4.4.3 $
 */

public class BeanContextMembershipEvent extends BeanContextEvent {

    static final long serialVersionUID = 3499346510334590959L;

    /**
     * 
     */
    protected Collection children;

    /**
     * @com.intel.drl.spec_ref
     */
    public BeanContextMembershipEvent(BeanContext bc, Collection changes) {
        super(bc);

        if (changes == null) {
            throw new NullPointerException("Changes are null");
        }

        this.children = changes;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public BeanContextMembershipEvent(BeanContext bc, Object[] changes) {
        super(bc);

        if (changes == null) {
            throw new NullPointerException("Changes are null");
        }

        // Initialize collection
        this.children = new Vector<Object>();

        // Add all elements to it
        for (int i = 0; i < changes.length; i++) {
            this.children.add(changes[i]);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean contains(Object child) {
        return this.children.contains(child);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Iterator iterator() {
        return this.children.iterator();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int size() {
        return this.children.size();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Object[] toArray() {
        return this.children.toArray();
    }
}
