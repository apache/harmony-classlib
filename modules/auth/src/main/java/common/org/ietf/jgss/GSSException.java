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
* @author Alexander V. Esin
* @version $Revision$
*/
package org.ietf.jgss;

/**
 * @com.intel.drl.spec_ref
 */
public class GSSException extends Exception {

    private static final long serialVersionUID = -2706218945227726672L;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int BAD_BINDINGS = 1;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int BAD_MECH = 2;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int BAD_MIC = 6;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int BAD_NAME = 3;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int BAD_NAMETYPE = 4;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int BAD_QOP = 14;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int BAD_STATUS = 5;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int CONTEXT_EXPIRED = 7;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int CREDENTIALS_EXPIRED = 8;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int DEFECTIVE_CREDENTIAL = 9;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int DEFECTIVE_TOKEN = 10;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int DUPLICATE_ELEMENT = 17;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int DUPLICATE_TOKEN = 19;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int FAILURE = 11;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int GAP_TOKEN = 22;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int NAME_NOT_MN = 18;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int NO_CONTEXT = 12;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int NO_CRED = 13;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int OLD_TOKEN = 20;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int UNAUTHORIZED = 15;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int UNAVAILABLE = 16;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int UNSEQ_TOKEN = 21;

    //error messages
    private static final String[] errorMessages = { "BAD BINDINGS", "BAD MECH", //$NON-NLS-1$ //$NON-NLS-2$
            "BAD NAME", "BAD NAMETYPE", "BAD STATUS", "BAD MIC", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
            "CONTEXT EXPIRED", "CREDENTIALS EXPIRED", "DEFECTIVE CREDENTIAL", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "DEFECTIVE TOKEN", "FAILURE", "NO CONTEXT", "NO CRED", "BAD QOP", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
            "UNAUTHORIZED", "UNAVAILABLE", "DUPLICATE ELEMENT", "NAME NOT MN", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
            "DUPLICATE TOKEN", "OLD TOKEN", "UNSEQ TOKEN", "GAP TOKEN" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

    //major code
    private int major = FAILURE;

    //minor code
    private int minor;

    //minor string
    private String minorMessage;

    //major string
    private String majorString;

    /**
     * @com.intel.drl.spec_ref
     */
    public GSSException(int majorCode) {
        if (majorCode > 0 && majorCode <= 22) {
            this.major = majorCode;
        }
        this.majorString = errorMessages[major - 1];
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public GSSException(int majorCode, int minorCode, String minorString) {
        this(majorCode);
        this.minor = minorCode;
        this.minorMessage = minorString;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int getMajor() {
        return major;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getMajorString() {
        return majorString;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int getMinor() {
        return minor;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getMinorString() {
        if (minor == 0) {
            return null;
        }
        return minorMessage;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getMessage() {
        String tmp = getMinorString();
        String tmp2 = getMajorString();
        if (tmp == null) {
            return tmp2;
        }
        return tmp2 + " (" + tmp + ')'; //$NON-NLS-1$
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setMinor(int minorCode, String minorString) {
        this.minor = minorCode;
        this.minorMessage = minorString;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String toString() {
        return "GSSException: " + getMessage(); //$NON-NLS-1$
    }
}
