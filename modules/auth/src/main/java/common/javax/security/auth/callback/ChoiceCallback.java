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

import org.apache.harmony.auth.internal.nls.Messages;

/**
 * @com.intel.drl.spec_ref
 *
 */
public class ChoiceCallback implements Callback, Serializable {
    
    /** 
     * @com.intel.drl.spec_ref 
     */
    private static final long serialVersionUID = -3975664071579892167L; 

    /**
     * @com.intel.drl.spec_ref
     */
    private int defaultChoice;

    /**
     * @com.intel.drl.spec_ref
     */
    private String prompt;

    /**
     * @com.intel.drl.spec_ref
     */
    private boolean multipleSelectionsAllowed;

    /**
     * @com.intel.drl.spec_ref
     */
    private String[] choices;

    /**
     * @com.intel.drl.spec_ref
     */
    private int[] selections;

    // sets the choices.
    private void setChoices(String[] choices) {
        if (choices == null || choices.length == 0){
            throw new IllegalArgumentException(Messages.getString("auth.1C")); //$NON-NLS-1$
        }
        for (int i = 0; i < choices.length; i++) {
            if (choices[i] == null || choices[i].length() == 0) {
                throw new IllegalArgumentException(Messages.getString("auth.1C")); //$NON-NLS-1$
            }
        }
        //FIXME: System.arraycopy(choices, 0 , new String[choices.length], 0, choices.length);
        this.choices = choices;

    }

    // sets the prompt.
    private void setPrompt(String prompt) {
        if (prompt == null || prompt.length() == 0) {
            throw new IllegalArgumentException(Messages.getString("auth.14")); //$NON-NLS-1$
        }
        this.prompt = prompt;
    }
    
    // sets the defaultChoice.
    private void setDefaultChoice(int defaultChoice) {
        if (0 > defaultChoice || defaultChoice >= choices.length) {
            throw new IllegalArgumentException(Messages.getString("auth.1D")); //$NON-NLS-1$
        }
        this.defaultChoice = defaultChoice;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public ChoiceCallback(String prompt, String[] choices, int defaultChoice,
            boolean multipleSelectionsAllowed) {
        setPrompt(prompt);
        setChoices(choices);
        setDefaultChoice(defaultChoice);
        this.multipleSelectionsAllowed = multipleSelectionsAllowed;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean allowMultipleSelections() {
        return multipleSelectionsAllowed;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String[] getChoices() {
        return choices;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int getDefaultChoice() {
        return defaultChoice;
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
    public int[] getSelectedIndexes() {
        return selections;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setSelectedIndex(int selection) {
        this.selections = new int[1];
        this.selections[0] = selection;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setSelectedIndexes(int[] selections) {
        if (!multipleSelectionsAllowed) {
            throw new UnsupportedOperationException();
        }
        this.selections = selections;
        //FIXME: 
        // this.selections = new int[selections.length]
        //System.arraycopy(selections, 0, this.selections, 0, this.selections.length);
    }

}
