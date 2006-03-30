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

import org.apache.harmony.tests.java.beans.EventHandlerTest;

/**
 * @author Maxim V. Berkultsev
 * @version $Revision: 1.3.6.3 $
 */

public class InvocationObject {
    
    private EventHandlerTest logger;
    private Object object;
    private String text;
    private int intValue = -1;
    
    public InvocationObject(EventHandlerTest logger) {
        this.logger = logger;
    }
    
    public void doSomething() {
        if(logger != null) {
            logger.logMethodCall(this, "doSomething", new Object[] {} );
        }
    }
    
    public void doSomething(int intValue) {
        this.intValue = intValue;
        
        if(logger != null) {
            logger.logMethodCall(this, "doSomething",
                    new Object[] { new Integer(intValue) } );
        }
    }
    
    public void setSomeObject(Object object) {
        this.object = object;
        
        if(logger != null) {
            logger.logMethodCall(this, "setSomeObject",
                    new Object[] { object } );
        }
    }
    
    public void setSomeText(String text) {
        this.text = text;
        
        if(logger != null) {
            logger.logMethodCall(this, "setSomeText", new Object[] { text } );
        }
    }
    
    public String getSomeText() {
        return text;
    }
    
    public int getIntValue() {
        return intValue;
    }
    
    public void setIntValue(int intValue) {
        this.intValue = intValue;
        
        if(logger != null) {
            logger.logMethodCall(this, "setIntValue",
                    new Object[] { new Integer(intValue) } );
        }
    }
    
    public void setSomeValue(int i) {
        if(logger != null) {
            logger.logMethodCall(this, "setSomeValue",
                    new Object[] { new Integer(intValue) } );
        }
    }
}
