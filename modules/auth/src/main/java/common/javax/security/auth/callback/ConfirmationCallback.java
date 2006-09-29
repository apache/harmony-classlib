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
public class ConfirmationCallback implements Callback, Serializable {
 
    /**
     * @com.intel.drl.spec_ref
     */
    private static final long serialVersionUID = -9095656433782481624L; 

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int YES = 0; // default options
    
    /**
     * @com.intel.drl.spec_ref
     */
    public static final int NO = 1;
    
    /**
     * @com.intel.drl.spec_ref
     */
    public static final int CANCEL = 2;
    
    /**
     * @com.intel.drl.spec_ref
     */
    public static final int OK = 3;
    
    /**
     * @com.intel.drl.spec_ref
     */
    public static final int YES_NO_OPTION = 0; // options type
    
    /**
     * @com.intel.drl.spec_ref
     */
    public static final int YES_NO_CANCEL_OPTION = 1;
    
    /**
     * @com.intel.drl.spec_ref
     */
    public static final int OK_CANCEL_OPTION = 2;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int UNSPECIFIED_OPTION = -1;
    
    /**
     * @com.intel.drl.spec_ref
     */
    public static final int INFORMATION = 0; // messages type
    
    /**
     * @com.intel.drl.spec_ref
     */
    public static final int WARNING = 1;
    
    /**
     * @com.intel.drl.spec_ref
     */
    public static final int ERROR = 2;
    
    /**
     * @com.intel.drl.spec_ref
     */
    private String prompt;
    
    /**
     * @com.intel.drl.spec_ref
     */
    private int messageType;

    /**
     * @com.intel.drl.spec_ref
     */
    private int optionType = UNSPECIFIED_OPTION;

    /**
     * @com.intel.drl.spec_ref
     */
    private int defaultOption;
    
    /**
     * @com.intel.drl.spec_ref
     */
    private String[] options;

    /**
     * @com.intel.drl.spec_ref
     */
    private int selection;
    
    /**
     * @com.intel.drl.spec_ref
     */
    public ConfirmationCallback(int messageType, int optionType,
            int defaultOption) {

        if (messageType > ERROR ||
                messageType < INFORMATION) {
            throw new IllegalArgumentException(Messages.getString("auth.16")); //$NON-NLS-1$
        }

        switch (optionType) {
        case YES_NO_OPTION:
            if (defaultOption != YES && defaultOption != NO) {
                throw new IllegalArgumentException(Messages.getString("auth.17")); //$NON-NLS-1$
            }
            break;
        case YES_NO_CANCEL_OPTION:
            if (defaultOption != YES && defaultOption != NO
                    && defaultOption != CANCEL) {
                throw new IllegalArgumentException(Messages.getString("auth.17")); //$NON-NLS-1$
            }
            break;
        case OK_CANCEL_OPTION:
            if (defaultOption != OK && defaultOption != CANCEL) {
                throw new IllegalArgumentException(Messages.getString("auth.17")); //$NON-NLS-1$
            }
            break;
        default:
            throw new IllegalArgumentException(Messages.getString("auth.18")); //$NON-NLS-1$
        }
        this.messageType = messageType;
        this.optionType = optionType;
        this.defaultOption = defaultOption;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public ConfirmationCallback(int messageType, String[] options,
            int defaultOption) {

        if (messageType > ERROR ||
                messageType < INFORMATION) {
            throw new IllegalArgumentException(Messages.getString("auth.16")); //$NON-NLS-1$
        }

        if (options == null || options.length == 0) {
            throw new IllegalArgumentException(Messages.getString("auth.1A")); //$NON-NLS-1$
        }
        for (int i = 0; i < options.length; i++) {
            if (options[i] == null || options[i].length() == 0) {
                throw new IllegalArgumentException(Messages.getString("auth.1A")); //$NON-NLS-1$
            }
        }
        if (0 > defaultOption  || defaultOption >= options.length) {
            throw new IllegalArgumentException(Messages.getString("auth.17")); //$NON-NLS-1$
        }
        //FIXME:System.arraycopy(options, 0 , new String[this.options.length], 0, this.options.length);
        this.options = options;
        this.defaultOption = defaultOption;
        this.messageType = messageType;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public ConfirmationCallback(String prompt, int messageType, int optionType,
            int defaultOption) {

        if (prompt == null || prompt.length() == 0) {
            throw new IllegalArgumentException(Messages.getString("auth.14")); //$NON-NLS-1$
        }
        
        if (messageType > ERROR ||
                messageType < INFORMATION) {
            throw new IllegalArgumentException(Messages.getString("auth.16")); //$NON-NLS-1$
        }

        switch (optionType) {
        case YES_NO_OPTION:
            if (defaultOption != YES && defaultOption != NO) {
                throw new IllegalArgumentException(Messages.getString("auth.17")); //$NON-NLS-1$
            }
            break;
        case YES_NO_CANCEL_OPTION:
            if (defaultOption != YES && defaultOption != NO
                    && defaultOption != CANCEL) {
                throw new IllegalArgumentException(Messages.getString("auth.17")); //$NON-NLS-1$
            }
            break;
        case OK_CANCEL_OPTION:
            if (defaultOption != OK && defaultOption != CANCEL) {
                throw new IllegalArgumentException(Messages.getString("auth.17")); //$NON-NLS-1$
            }
            break;
        default:
            throw new IllegalArgumentException(Messages.getString("auth.18")); //$NON-NLS-1$
        }
        this.prompt = prompt;
        this.messageType = messageType;
        this.optionType = optionType;
        this.defaultOption = defaultOption;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public ConfirmationCallback(String prompt, int messageType,
            String[] options, int defaultOption) {

        if (prompt == null || prompt.length() == 0) {
            throw new IllegalArgumentException(Messages.getString("auth.14")); //$NON-NLS-1$
        }

        if (messageType > ERROR ||
                messageType < INFORMATION) {
            throw new IllegalArgumentException(Messages.getString("auth.16")); //$NON-NLS-1$
        }

        if (options == null || options.length == 0) {
            throw new IllegalArgumentException(Messages.getString("auth.1A")); //$NON-NLS-1$
        }
        for (int i = 0; i < options.length; i++) {
            if (options[i] == null || options[i].length() == 0) {
                throw new IllegalArgumentException(Messages.getString("auth.1A")); //$NON-NLS-1$
            }
        }
        if (0 > defaultOption  || defaultOption >= options.length) {
            throw new IllegalArgumentException(Messages.getString("auth.17")); //$NON-NLS-1$
        }
        //FIXME:System.arraycopy(options, 0 , new String[this.options.length], 0, this.options.length);
        this.options = options;
        this.defaultOption = defaultOption;
        this.messageType = messageType;
        this.prompt = prompt;
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
    public int getMessageType() {
        return messageType;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int getDefaultOption() {
        return defaultOption;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String[] getOptions() {
         return options;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int getOptionType() {
        return optionType;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int getSelectedIndex() {
        return selection;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setSelectedIndex(int selection) {
        if (options != null) {
            if ( 0 <= selection && selection <= options.length) {
                this.selection = selection;
            } else {
                throw new ArrayIndexOutOfBoundsException(Messages.getString("auth.1B")); //$NON-NLS-1$
            }
        } else {
            switch (optionType) {
            case YES_NO_OPTION:
                if (selection != YES && selection != NO) {
                    throw new IllegalArgumentException(Messages.getString("auth.19")); //$NON-NLS-1$
                }
                break;
            case YES_NO_CANCEL_OPTION:
                if (selection != YES && selection != NO
                        && selection != CANCEL) {
                    throw new IllegalArgumentException(Messages.getString("auth.19")); //$NON-NLS-1$
                }
                break;
            case OK_CANCEL_OPTION:
                if (selection != OK && selection != CANCEL) {
                    throw new IllegalArgumentException(Messages.getString("auth.19")); //$NON-NLS-1$
                }
                break;
            }
            this.selection = selection;
        }
    }
    
}