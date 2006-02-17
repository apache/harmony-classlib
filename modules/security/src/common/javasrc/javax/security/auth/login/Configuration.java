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

import java.security.AccessController;
import javax.security.auth.AuthPermission;

import org.apache.harmony.security.fortress.PolicyUtils;

/**
 * @com.intel.drl.spec_ref
 * 
 */
public abstract class Configuration {
     
    // the current configuration 
    private static Configuration configuration;
    
    // creates a AuthPermission object with a specify property
    private static final AuthPermission GET_LOGIN_CONFIGURATION = new AuthPermission(
            "getLoginConfiguration"); 

    // creates a AuthPermission object with a specify property
    private static final AuthPermission SET_LOGIN_CONFIGURATION = new AuthPermission(
            "setLoginConfiguration");
    
    // Key to security properties, defining default configuration provider.
    private static final String LOGIN_CONFIGURATION_PROVIDER = "login.configuration.provider";
    
    /**
     * @com.intel.drl.spec_ref
     */
    protected Configuration() {
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static Configuration getConfiguration() {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(GET_LOGIN_CONFIGURATION);
        }
        return getAccessibleConfiguration();
    }
    
    // Reads name of default configuration provider from security.properties,
    // loads the class and instantiates the provider.<br> 
    // In case of any exception, wraps it with SecurityException and throws
    // further.
    private static final Configuration getDefaultProvider() {
         return (Configuration) AccessController
                .doPrivileged(new PolicyUtils.ProviderLoader(
                        LOGIN_CONFIGURATION_PROVIDER, Configuration.class));
    }
    
    /**
     * Shortcut accessor for friendly classes, to skip security checks.
     * If active configuration was set to <code>null</code>, tries to load a default 
     * provider, so this method never returns <code>null</code>. <br>
     * This method is synchronized with setConfiguration()
     */     
    static Configuration getAccessibleConfiguration() {
        Configuration current = configuration;
        if (current == null) {
            synchronized (Configuration.class) {
                if (configuration == null) {
                    configuration = getDefaultProvider();
                }
                return configuration;
            }
        }
        return current;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public static void setConfiguration(Configuration configuration) {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(SET_LOGIN_CONFIGURATION);
        }
        Configuration.configuration = configuration;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract AppConfigurationEntry[] getAppConfigurationEntry(
            String applicationName);

    /**
     * @com.intel.drl.spec_ref
     */
    public abstract void refresh();

}
