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
* @author Alexander V. Esin
* @version $Revision$
*/
package org.ietf.jgss;

/**
 * @com.intel.drl.spec_ref
 */
public class MessageProp {

    //privacy state
    private boolean privState;

    //quality-of-protection
    private int qop /*= 0*/;

    //duplicate token
    private boolean duplicate;

    //old token
    private boolean old;

    //unseq
    private boolean unseq;

    //gap
    private boolean gap;

    //minor status
    private int minorStatus /*= 0*/;

    //minor status string
    private String minorString;

    /**
     * @com.intel.drl.spec_ref
     */
    public MessageProp(boolean privState) {
        this.privState = privState;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public MessageProp(int qop, boolean privState) {
        this(privState);
        this.qop = qop;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int getQOP() {
        return qop;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean getPrivacy() {
        return privState;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean isDuplicateToken() {
        return duplicate;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean isOldToken() {
        return old;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean isUnseqToken() {
        return unseq;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean isGapToken() {
        return gap;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int getMinorStatus() {
        return minorStatus;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getMinorString() {
        return minorString;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setQOP(int qop) {
        this.qop = qop;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setPrivacy(boolean privState) {
        this.privState = privState;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setSupplementaryStates(boolean duplicate, boolean old,
            boolean unseq, boolean gap, int minorStatus, String minorString) {
        this.duplicate = duplicate;
        this.old = old;
        this.unseq = unseq;
        this.gap = gap;
        this.minorStatus = minorStatus;
        this.minorString = minorString;
    }
}