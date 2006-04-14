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

package javax.security.auth.login;

import java.util.Collections;
import java.util.Map;

/** 
 * @com.intel.drl.spec_ref 
 */
public class AppConfigurationEntry {


    // the login module options
    private final Map<String, ?> options;

    // the control flag
    private final AppConfigurationEntry.LoginModuleControlFlag controlFlag;

    // the login module name 
    private final String loginModuleName;

    /** 
     * @com.intel.drl.spec_ref 
     */
    public AppConfigurationEntry(String loginModuleName,
            AppConfigurationEntry.LoginModuleControlFlag controlFlag,
            Map<String, ?> options) {

        if (loginModuleName == null || loginModuleName.length() == 0) {
            throw new IllegalArgumentException(
                    "name of the LoginModule is null or has a length of 0");
        }

        if (controlFlag == null) {
            throw new IllegalArgumentException("invalid control flag");
        }

        if (options == null) {
            throw new IllegalArgumentException("invalid options");
        }

        this.loginModuleName = loginModuleName;
        this.controlFlag = controlFlag;
        this.options = Collections.unmodifiableMap(options);
    }

    /** 
     * @com.intel.drl.spec_ref 
     */
    public String getLoginModuleName() {
        return loginModuleName;
    }

    /** 
     * @com.intel.drl.spec_ref 
     */
    public LoginModuleControlFlag getControlFlag() {
        return controlFlag;
    }

    /** 
     * @com.intel.drl.spec_ref 
     */
    public Map<java.lang.String, ?> getOptions() {
        return options;
    }

    /** 
     * @com.intel.drl.spec_ref 
     */
    public static class LoginModuleControlFlag {

        // the control flag
        private final String flag;

        /** 
         * @com.intel.drl.spec_ref 
         */
        public static final LoginModuleControlFlag REQUIRED = new LoginModuleControlFlag(
                "LoginModuleControlFlag: required");

        /** 
         * @com.intel.drl.spec_ref 
         */
        public static final LoginModuleControlFlag REQUISITE = new LoginModuleControlFlag(
                "LoginModuleControlFlag: requisite");

        /** 
         * @com.intel.drl.spec_ref 
         */
        public static final LoginModuleControlFlag OPTIONAL = new LoginModuleControlFlag(
                "LoginModuleControlFlag: optional");

        /** 
         * @com.intel.drl.spec_ref 
         */
        public static final LoginModuleControlFlag SUFFICIENT = new LoginModuleControlFlag(
                "LoginModuleControlFlag: sufficient");

        // Creates the LoginModuleControlFlag object with specified a flag
        private LoginModuleControlFlag(String flag) {
            this.flag = flag;
        }

        /** 
         * @com.intel.drl.spec_ref 
         */
        public String toString() {
            return flag;
        }
    }

}