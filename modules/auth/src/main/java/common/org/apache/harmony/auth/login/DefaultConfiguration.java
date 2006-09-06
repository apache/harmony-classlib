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

package org.apache.harmony.auth.login;

import java.io.File;
import java.net.URL;
import java.security.AccessController;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;

import javax.security.auth.AuthPermission;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;

import org.apache.harmony.security.fortress.PolicyUtils;


/**
 * Default Configuration implementation based on login configuration files.
 * This implementation recognizes text files, consisting of clauses with the
 * following syntax:
 * 
 * <pre>
 * Application {
 *          LoginModuleClass Flag [Options ...];
 *          LoginModuleClass Flag [Options ...];
 * };
 * </pre>
 */

public class DefaultConfiguration extends Configuration {

    
    // system property for dynamically added login configuration file location.
    private static final String JAVA_SECURITY_LOGIN_CONFIG = "java.security.auth.login.config"; //$NON-NLS-1$

    // location of login configuration file
    private static final String LOGIN_CONFIG_URL_PREFIX = "login.config.url."; //$NON-NLS-1$
    
    // default a config file from user's home directory
    private static final String JAVA_LOGIN_CONF_FILE = "file:" + System.getProperty("user.home") + //$NON-NLS-1$ //$NON-NLS-2$
                                                        + File.separatorChar + ".java.login.config"; //$NON-NLS-1$

    // creates a AuthPermission object 
    private static final AuthPermission REFRESH_LOGIN_CONFIGURATION = new AuthPermission("refreshLoginConfiguration"); //$NON-NLS-1$

    // set of application entry
    private Map configutations = Collections.synchronizedMap(new HashMap());

     /**
      * Default a constructor
      */
    public DefaultConfiguration() {
        try {
            init();
        } catch (Exception e) {
            // TODO log warning
        }
    }
    
    /**
     * @com.intel.drl.spec_ref 
     */
    public AppConfigurationEntry[] getAppConfigurationEntry(
            String applicationName) {

        LinkedList list = (LinkedList) configutations.get(applicationName);

        if (list == null || list.size() == 0) {
            return null;
        }
        return (AppConfigurationEntry[]) list.toArray(new AppConfigurationEntry[list.size()]);
    }

    /**
     * @com.intel.drl.spec_ref 
     */
    public synchronized void refresh() {

        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(REFRESH_LOGIN_CONFIGURATION);
        }
        configutations.clear();
        try {
            init();
        } catch (Exception e) {
            //TODO: log warning
        }
    }
    
     /**
      * Initialize a login configuration file
      */ 
    private void init() {

        HashMap fresh = new HashMap();
        Properties system = new Properties((Properties)AccessController
                                            .doPrivileged(new PolicyUtils.SystemKit()));
        system.setProperty("/", File.separator); //$NON-NLS-1$
        URL[] policyLocations = PolicyUtils.getPolicyURLs(system,
                JAVA_SECURITY_LOGIN_CONFIG, LOGIN_CONFIG_URL_PREFIX);

        for (int i = 0; i < policyLocations.length; i++) {
            try {
                fresh.putAll(DefaultConfigurationParser.configParser(
                        policyLocations[i], system, fresh));
            } catch (Exception e) {
                //TODO: log warning
                //new SecurityException("Unable to load a login configuration file");
            }
        }
        // if location is not define then get a config file from user's directory 
         if (policyLocations.length == 0) {
            try {
                fresh.putAll(DefaultConfigurationParser.configParser(new URL(JAVA_LOGIN_CONF_FILE),
                        system, fresh));
            } catch (Exception e) {
                //TODO: log warning
                //throw new SecurityException ("Unable to load a login configuration file");
            }
        }
         configutations = fresh;
    }
    
}
