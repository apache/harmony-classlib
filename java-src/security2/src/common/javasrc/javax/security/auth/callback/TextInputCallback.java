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

/**
 * @com.intel.drl.spec_ref
 *
 */
public class TextInputCallback implements Callback, Serializable {

    /**
     * @com.intel.drl.spec_ref
     */
    private static final long serialVersionUID = -8064222478852811804L; 
    
    /**
     * @com.intel.drl.spec_ref
     */
    private String defaultText;

    /**
     * @com.intel.drl.spec_ref
     */
    private String prompt;

    /**
     * @com.intel.drl.spec_ref
     */
    private String inputText;

    // sets the prompt
    private void setPrompt(String prompt) {
        if (prompt == null || prompt.length() == 0) {
            throw new IllegalArgumentException("Invalid prompt");
        }
        this.prompt = prompt;
    }

    // sets the default text
    private void setDefaultText(String defaultText) {
        if (defaultText == null || defaultText.length() == 0) {
            throw new IllegalArgumentException("Invalid default text");
        }
        this.defaultText = defaultText;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public TextInputCallback(String prompt) {
        setPrompt(prompt);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public TextInputCallback(String prompt, String defaultText) {
        setPrompt(prompt);
        setDefaultText(defaultText);
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getDefaultText() {
        return defaultText;
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
    public String getText() {
        return inputText;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setText(String text) {
        this.inputText = text;
    }

}