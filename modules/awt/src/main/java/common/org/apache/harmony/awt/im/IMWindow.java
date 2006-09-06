/*
 *  Copyright 2005 - 2006 The Apache Software Software Foundation or its licensors, as applicable.
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
 * @author Dmitry A. Durnev
 * @version $Revision$
 */
package org.apache.harmony.awt.im;

import java.awt.Frame;
import java.awt.im.InputContext;

public class IMWindow extends Frame {
    private final InputContext inputContext;
    
    IMWindow(String title, InputContext ic) {
        super(title);
        inputContext = ic;       
    }
    
    IMWindow() {
        this("Input Window", null);
    }
    
    public void addNotify() {
        super.addNotify();
        IMManager.makeIMWindow(this);
    }
    
    public InputContext getInputContext() {
        if (inputContext != null) {
            return inputContext;
        }
        return super.getInputContext();
    }
}
