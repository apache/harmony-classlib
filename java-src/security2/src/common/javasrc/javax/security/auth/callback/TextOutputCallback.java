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
public class TextOutputCallback implements Callback, Serializable {

    /**
     * @com.intel.drl.spec_ref
     */
    private static final long serialVersionUID = 1689502495511663102L; 

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int INFORMATION = 0;

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
    private String message;

    /**
     * @com.intel.drl.spec_ref
     */
    private int messageType;

    /**
     * @com.intel.drl.spec_ref
     */
    public TextOutputCallback(int messageType, String message) {
        if (messageType > ERROR || messageType < INFORMATION) {
            throw new IllegalArgumentException("Invalid message type");
        }
        if (message == null || message.length() == 0) {
            throw new IllegalArgumentException("Invalid message");
        }
        this.messageType = messageType;
        this.message = message;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getMessage() {
        return message;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int getMessageType() {
        return messageType;
    }
}