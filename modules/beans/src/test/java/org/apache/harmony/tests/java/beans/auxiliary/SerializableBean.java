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
 * @version $Revision: 1.3.6.3 $
 */
package org.apache.harmony.tests.java.beans.auxiliary;

import java.io.Serializable;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.3.6.3 $
 */

public class SerializableBean implements Serializable {
    
    private int value;
    private String text = null;
    private Integer iValue = null;
    private int[] intArray;
    private String[] strArray;
    
    public SerializableBean() {
    }
    
    public SerializableBean(String text) {
        this.text = text;
    }
    
    public String getText() {
        return this.text;
    }
    
    public Integer getIValue() {
        return iValue;
    }
    
    public void setIValue(Integer iValue) {
        this.iValue = iValue;
    }
    
    public int getValue() {
        return value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public int[] getIntArray() {
        return intArray;
    }
    
    public void setIntArray(int[] intArray) {
        this.intArray = intArray;
    }
    
    public String[] getStrArray() {
        return strArray;
    }
    
    public void setStrArray(String[] strArray) {
        this.strArray = strArray;
    }
}
