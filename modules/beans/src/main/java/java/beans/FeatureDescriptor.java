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

import java.util.HashMap;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.4.6.3 $
 */

public class FeatureDescriptor {
    
    private HashMap<String, Object> values = new HashMap<String, Object>();
    boolean preferred, hidden, expert;
    String shortDescription = null;
    String name = null;
    String displayName = null;

    /**
     * @com.intel.drl.spec_ref
     */
    public FeatureDescriptor() {
        this.preferred = false;
        this.hidden = false;
        this.expert = false;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setValue(String attributeName, Object value) {
        if ((attributeName != null) && (value != null)) {
            values.put(attributeName, value);
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Object getValue(String attributeName) {
        Object result = null;
        if (attributeName != null) {
            result = values.get(attributeName);
        }
        return result;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public Enumeration attributeNames() {
        String attributeNamesStr = "";
        Iterator i = values.keySet().iterator();
        while(i.hasNext()) {
            String attributeName = (String) i.next();
            if(attributeNamesStr.equals("")) {
                attributeNamesStr += attributeName;
            } else {
                attributeNamesStr += ' ' + attributeName;
            }
        }
        return new StringTokenizer(attributeNamesStr);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setShortDescription(String text) {
        this.shortDescription = text;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getName() {
        return name;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setPreferred(boolean preferred) {
        this.preferred = preferred;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setExpert(boolean expert) {
        this.expert = expert;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean isPreferred() {
        return preferred;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean isExpert() {
        return expert;
    }
}
