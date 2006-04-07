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
* @author Maxim V. Makarov
* @version $Revision$
*/

package javax.security.auth.callback;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @com.intel.drl.spec_ref
 *
 */
public class PasswordCallback implements Callback, Serializable {

    /**
     * @com.intel.drl.spec_ref
     */
    private static final long serialVersionUID = 2267422647454909926L;
    
    /**
     * @com.intel.drl.spec_ref
     */
    private String prompt;

    /**
     * @com.intel.drl.spec_ref
     */    
    boolean echoOn;
    
    /**
     * @com.intel.drl.spec_ref
     */
    private char[] inputPassword;
    
    // sets the prompt
    private void setPrompt(String prompt) throws IllegalArgumentException {
        if (prompt == null || prompt.length() == 0) {
            throw new IllegalArgumentException("Invalid prompt");
        }
        this.prompt = prompt;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public PasswordCallback(String prompt, boolean echoOn) {
        setPrompt(prompt);
        this.echoOn = echoOn;
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
    public boolean isEchoOn() {
        return echoOn;
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public void setPassword(char[] password) {
        if (password == null) {
            this.inputPassword = password;        
        } else {
            inputPassword = new char[password.length];
            System.arraycopy(password , 0, inputPassword, 0, inputPassword.length);   
        }
    }
    
    /**
     * @com.intel.drl.spec_ref
     */
    public char[] getPassword() {
        if (inputPassword != null){
            char[] tmp = new char[inputPassword.length];
            System.arraycopy(inputPassword, 0, tmp, 0, tmp.length);
            return tmp;
        }
        return null;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void clearPassword() {
        if (inputPassword != null) {
            Arrays.fill(inputPassword, '\u0000');
        }
    }
}
