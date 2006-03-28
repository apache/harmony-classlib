/* Copyright 1998, 2006 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.harmony.luni.net;

import java.security.AccessController;

import com.ibm.oti.util.PriviAction;

public class NetUtil {
    
    /*
     * FIXME: enhance performance to cache the values
     */
    
    /**
     * Answer whether to use a SOCKS proxy.
     * 
     * @return boolean
     */
    public static boolean usingSocks() {
        String proxySet = (String) AccessController
                .doPrivileged(new PriviAction("socksProxySet"));
        if (proxySet != null) {
            return proxySet.toLowerCase().equals("true");
        }
        return AccessController.doPrivileged(new PriviAction("socksProxyHost")) != null;
    }

    /**
     * Answer whether to prefer IPV6 address
     * 
     * @return boolean
     */
    public static boolean preferIPv6Addresses() {
    	String result = (String) AccessController.doPrivileged(new PriviAction(
    			"java.net.preferIPv6Addresses"));
    	return "true".equals(result);
    }

    /**
     * Answer whether to prefer IPV4 stack
     * 
     * @return boolean
     */
    public static boolean preferIPv4Stack() {
    	String result = (String) AccessController.doPrivileged(new PriviAction(
    			"java.net.preferIPv4Stack"));
        return "true".equals(result);
    }

}
