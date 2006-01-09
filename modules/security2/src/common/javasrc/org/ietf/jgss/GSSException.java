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
public class GSSException extends Exception {

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
    private static final String[] errorMessages = { "BAD BINDINGS", "BAD MECH",
            "BAD NAME", "BAD NAMETYPE", "BAD STATUS", "BAD MIC",
            "CONTEXT EXPIRED", "CREDENTIALS EXPIRED", "DEFECTIVE CREDENTIAL",
            "DEFECTIVE TOKEN", "FAILURE", "NO CONTEXT", "NO CRED", "BAD QOP",
            "UNAUTHORIZED", "UNAVAILABLE", "DUPLICATE ELEMENT", "NAME NOT MN",
            "DUPLICATE TOKEN", "OLD TOKEN", "UNSEQ TOKEN", "GAP TOKEN" };

    //major code
    private int majorCode = FAILURE;

    //minor code
    private int minorCode /*= 0*/;

    //minor string
    private String minorString;

    /**
     * @com.intel.drl.spec_ref
     */
    public GSSException(int majorCode) {
        if (majorCode > 0 && majorCode <= 22) {
            this.majorCode = majorCode;
        }
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public GSSException(int majorCode, int minorCode, String minorString) {
        this(majorCode);
        this.minorCode = minorCode;
        this.minorString = minorString;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int getMajor() {
        return majorCode;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getMajorString() {
        return errorMessages[majorCode - 1];
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public int getMinor() {
        return minorCode;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String getMinorString() {
        if (minorCode == 0) {
            return null;
        }
        return minorString;
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
        return tmp2 + " (" + tmp + ')';
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void setMinor(int minorCode, String minorString) {
        this.minorCode = minorCode;
        this.minorString = minorString;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String toString() {
        return "GSSException: " + getMessage();
    }
}