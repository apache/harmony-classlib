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
 * @version $Revision: 1.1.2.1 $
 */
package org.apache.harmony.beans.editors;

import java.beans.PropertyEditorSupport;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.1.2.1 $
 */

public class FloatEditor extends PropertyEditorSupport {
    
    /**
     * 
     * @param source
     */
    public FloatEditor(Object source) {
        super(source);
    }

    /**
     */
    public FloatEditor() {
        super();
    }
    
    public String getAsText() {
        return getValueAsText();
    }
    
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            setValue(new Float(text));
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException(nfe.toString());
        }
    }
    
    public String getJavaInitializationString() {
        return getValueAsText();
    }
    
    public String[] getTags() {
        return null;
    }
    
    public void setValue(Object value) {
        if(value instanceof Float) {
            super.setValue(value);
        }
    }
    
    private String getValueAsText() {
        String result = null;
        Object value = getValue();
        if(value != null) {
            Float fValue = (Float) value;
            result = fValue.toString();
        }
        return result;
    }
}
