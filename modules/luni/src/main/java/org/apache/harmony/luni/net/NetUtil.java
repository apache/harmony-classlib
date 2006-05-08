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

import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.security.AccessController;
import java.util.List;

import org.apache.harmony.luni.util.PriviAction;

public class NetUtil {
    
    /*
     * FIXME: enhance performance to cache the values
     */
    
    /**
	 * Answer whether to use a SOCKS proxy.
	 * 
	 * @param proxy
	 *            java.net.Proxy, may be <code>null</code> in which case the 
     *            return value is determined from the values of the 
     *            "socksProxySet" and "socksProxyHost" system properties.
	 * @return true if SOCKS proxy is set by <code>proxy</code> parameter or
     *         else "socksProxySet" system property is set and has the value
     *         of "true" or if "socksProxyHost" is set in system properties.
	 */
    public static boolean usingSocks(Proxy proxy) {
    	if(null != proxy && Proxy.NO_PROXY == proxy){
    		return false;
    	}
    	if(null != proxy && Proxy.Type.SOCKS == proxy.type()){
    		return true;
    	}
        String proxySet = (String) AccessController
                .doPrivileged(new PriviAction("socksProxySet")); //$NON-NLS-1$
        if (proxySet != null) {
            return proxySet.toLowerCase().equals("true"); //$NON-NLS-1$
        }
        return AccessController.doPrivileged(new PriviAction("socksProxyHost")) != null; //$NON-NLS-1$
    }

    /**
     * Answer whether to prefer IPV6 address
     * 
     * @return boolean
     */
    public static boolean preferIPv6Addresses() {
    	String result = (String) AccessController.doPrivileged(new PriviAction(
    			"java.net.preferIPv6Addresses")); //$NON-NLS-1$
    	return "true".equals(result); //$NON-NLS-1$
    }

    /**
     * Answer whether to prefer IPV4 stack
     * 
     * @return boolean
     */
    public static boolean preferIPv4Stack() {
    	String result = (String) AccessController.doPrivileged(new PriviAction(
    			"java.net.preferIPv4Stack")); //$NON-NLS-1$
        return "true".equals(result); //$NON-NLS-1$
    }

    /**
     * Gets proxy list according to the uri by system ProxySelector.
     * @param uri
     * @return a list of proxy for the uri. Returns null if no proxy 
     * 		   is available.
     */
    public static List<Proxy> getProxyList(URI uri){
		// use system default selector to get proxy list
		ProxySelector selector = ProxySelector.getDefault();
		if(null == selector){
			return null;
		}
		List proxyList = selector.select(uri);
    	return proxyList;
    }
}
