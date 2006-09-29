/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
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
* @author Maxim V. Makarov
* @version $Revision$
*/

package javax.security.auth.callback;

import java.io.Serializable;

import org.apache.harmony.auth.internal.nls.Messages;

/**
 * @com.intel.drl.spec_ref
 *
 */
public class NameCallback implements Callback, Serializable {

    /**
     * @com.intel.drl.spec_ref
     */
    private static final long serialVersionUID = 3770938795909392253L; 
    
    /**
     * @com.intel.drl.spec_ref
     */
    private String prompt;
    
    /**
     * @com.intel.drl.spec_ref
     */
    private String defaultName;
    
    /**
     * @com.intel.drl.spec_ref
     */ 
    private String inputName;
    
    // sets the prompt
    private void setPrompt(String prompt) {
        if (prompt == null || prompt.length() == 0) {
            throw new IllegalArgumentException(Messages.getString("auth.14")); //$NON-NLS-1$
        }
        this.prompt = prompt;
    }

    // sets the default name
    private void setDefaultName(String defaultName) {
        if (defaultName == null || defaultName.length() == 0) {
            throw new IllegalArgumentException(Messages.getString("auth.1E")); //$NON-NLS-1$
        }
        this.defaultName = defaultName;
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public NameCallback(String prompt) {
        setPrompt(prompt);
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public NameCallback(String prompt, String defaultName) {
        setPrompt(prompt);
        setDefaultName(defaultName);
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public String getPrompt() {
        return prompt;
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public String getDefaultName() {
        return defaultName;
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public void setName(String name){
        this.inputName = name;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getName() {
        return inputName;
    }
}
