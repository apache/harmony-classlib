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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @com.intel.drl.spec_ref
 */
public abstract class PermissionCollection implements Serializable {

    /**
     * @com.intel.drl.spec_ref
     */
    private static final long serialVersionUID = -6727011328946861783L;

    /**
     * @com.intel.drl.spec_ref
     */
    private boolean readOnly; // = false;

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract void add(Permission permission);

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract Enumeration elements();

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract boolean implies(Permission permission);

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean isReadOnly() {
        return readOnly;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setReadOnly() {
        readOnly = true;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String toString() {
        List elist = new ArrayList(100);
        Enumeration elenum = elements();
        String superStr = super.toString();
        int totalLength = superStr.length() + 5;
        if (elenum != null) {
            while (elenum.hasMoreElements()) {
                String el = elenum.nextElement().toString();
                totalLength += el.length();
                elist.add(el);
            }
        }
        int esize = elist.size();
        totalLength += esize * 4;
        //FIXME StringBuffer --> StringBuilder
        StringBuffer result = new StringBuffer(totalLength).append(superStr)
            .append(" (");
        for (int i = 0; i < esize; i++) {
            result.append("\n  ").append(elist.get(i).toString());
        }
        return result.append("\n)").toString();
    }
}