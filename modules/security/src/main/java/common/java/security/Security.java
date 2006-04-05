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
* @author Boris V. Kuznetsov
* @version $Revision$
*/

package java.security;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.AlgorithmParameters;
import java.security.KeyFactory;
import java.util.Enumeration;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.harmony.security.fortress.Engine;
import org.apache.harmony.security.fortress.PolicyUtils;
import org.apache.harmony.security.fortress.SecurityAccess;
import org.apache.harmony.security.fortress.Services;


/**
 * @com.intel.drl.spec_ref
 * 
 */

public final class Security {

    // Security properties
    private static Properties secprops = new Properties();

    // static inicialization
    // - load security properties files
    // - load staticaly registered providers
    // - if no provider description file found then load
    // DRL default providers
    static {
        AccessController.doPrivileged(new java.security.PrivilegedAction() {
            public Object run() {
                boolean loaded = false;
                File f = new File(System.getProperty("java.home")
                        + File.separator + "lib" + File.separator
                        + "security" + File.separator + "java.security");
                if (f.exists()) {
                    try {
                        FileInputStream fis = new FileInputStream(f);
                        InputStream is = new BufferedInputStream(fis);
                        secprops.load(is);
                        loaded = true;
                        is.close();
                    } catch (IOException e) {
//                        System.err.println("Could not load Security properties file: "
//                                        + e);
                    }
                }

                if ("true".equalsIgnoreCase(secprops.getProperty("security.allowCustomePropertiesFile", "true"))) {
                    String securityFile = System.getProperty("java.security.properties");
                    if (securityFile != null) {
                        if (securityFile.startsWith("=")) { // overwrite
                            secprops = new Properties();
                            loaded = false;
                            securityFile = securityFile.substring(1);
                        }
                        try {
                            securityFile = PolicyUtils.expand(securityFile, System.getProperties());
                        } catch (PolicyUtils.ExpansionFailedException e) {
//                            System.err.println("Could not load custom Security properties file "
//                                    + securityFile +": " + e);
                        }
                        f = new File(securityFile);
                        InputStream is;
                        try {
                            if (f.exists()) {
                                FileInputStream fis = new FileInputStream(f);
                                is = new BufferedInputStream(fis);
                            } else {
                                URL url = new URL(securityFile);
                                is = new BufferedInputStream(url.openStream());
                            }
                            secprops.load(is);
                            loaded = true;
                            is.close();
                        } catch (IOException e) {
 //                           System.err.println("Could not load custom Security properties file "
 //                                   + securityFile +": " + e);
                        }
                    }
                }
                if (!loaded) {
                    registerDRLProviders();
                }
                Engine.door = new SecurityDoor();
                return null;
            }
        });
    }

    /**
     * This class can't be instantiated.
     */
    private Security() {
    }

    // Register default DRL providers
    private static void registerDRLProviders() {
        //     TODO register DRL default providers
//        System.err.println("Security properties file not loaded \n"
//                + "No providers registered.");
    }

    /**
	 * @com.intel.drl.spec_ref
	 * 
	 * @deprecated Use {@link KeyFactory} and {@link AlgorithmParameters} to get
	 *             algorithm property information.
	 */
    public static String getAlgorithmProperty(String algName, String propName) {
        if (algName == null || propName == null) {
            return null;
        }
        String prop = propName + "." + algName;
        Provider[] providers = getProviders();
        for (int i = 0; i < providers.length; i++) {
            for (Enumeration e = providers[i].propertyNames(); e
                    .hasMoreElements();) {
                String pname = (String) e.nextElement();
                if (prop.equalsIgnoreCase(pname)) {
                    return providers[i].getProperty(pname);
                }
            }
        }
        return null;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static synchronized int insertProviderAt(Provider provider,
            int position) {
        // check security access; check that provider is not already
        // installed, else return -1; if (position <1) or (position > max
        // position) position = max position + 1; insert provider, shifte up
        // one position for next providers; Note: The position is 1-based
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkSecurityAccess("insertProvider." + provider.getName());
        }
        if (getProvider(provider.getName()) != null) {
            return -1;
        }
        int result = Services.insertProviderAt(provider, position);
        renumProviders();
        return result;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static int addProvider(Provider provider) {
        return insertProviderAt(provider, 0);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static synchronized void removeProvider(String name) {
        // It is not clear from spec.:
    	// 1. if name is null, should we checkSecurityAccess or not? 
    	//    throw SecurityExeption or not?
        // 2. as 1 but provider is not installed
        // 3. behavior if name is empty string?

        Provider p;
        if ((name == null) || (name.length() == 0)) {
            return;
        }
        p = getProvider(name);
        if (p == null) {
            return;
        }
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkSecurityAccess("removeProvider." + name);
        }
        Services.removeProvider(p.getProviderNumber());
        renumProviders();
        p.setProviderNumber(-1);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static synchronized Provider[] getProviders() {
        return Services.getProviders();
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static synchronized Provider getProvider(String name) {
        return Services.getProvider(name);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static Provider[] getProviders(String filter) {
        if (filter == null) {
            throw new NullPointerException("The filter is null");
        }
        if (filter.length() == 0) {
            throw new InvalidParameterException(
                    "The fillter is not in the required format");
        }
        HashMap hm = new HashMap();
        int i = filter.indexOf(":");
        if ((i == filter.length() - 1) || (i == 0)) {
            throw new InvalidParameterException(
                    "The fillter is not in the required format");
        }
        if (i < 1) {
            hm.put(filter, "");
        } else {
            hm.put(filter.substring(0, i), filter.substring(i + 1));
        }
        return getProviders(hm);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static synchronized Provider[] getProviders(Map filter) {
        if (filter == null) {
            throw new NullPointerException("The filter is null");
        }
        if (filter.isEmpty()) {
            return null;
        }
        java.util.List result = Services.getProvidersList();
        Set keys = filter.entrySet();
        Map.Entry entry;
        for (Iterator it = keys.iterator(); it.hasNext();) {
            entry = (Map.Entry) it.next();
            String key = (String) entry.getKey();
            String val = (String) entry.getValue();
            String attribute = null;
            int i = key.indexOf(" ");
            int j = key.indexOf(".");
            if (j == -1) {
                throw new InvalidParameterException(
                        "The fillter is not in the required format");
            }
            if (i == -1) { // <crypto_service>.<algorithm_or_type>
                if (val.length() != 0) {
                    throw new InvalidParameterException(
                            "The fillter is not in the required format");
                }
            } else { // <crypto_service>.<algorithm_or_type> <attribute_name>
                if (val.length() == 0) {
                    throw new InvalidParameterException(
                            "The fillter is not in the required format");
                }
                attribute = key.substring(i + 1);
                key = key.substring(0, i);
            }
            String serv = key.substring(0, j);
            String alg = key.substring(j + 1);
            Provider p;
            for (int k = 0; k < result.size(); k++) {
                try {
                    p = (Provider) result.get(k);
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
                if (!p.implementsAlg(serv, alg, attribute, val)) {
                    result.remove(p);
                    k--;
                }
            }
        }
        if (result.size() > 0) {
            return (Provider[]) result.toArray(new Provider[result.size()]);
        } else {
            return null;
        }
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static String getProperty(String key) {
        if (key == null) {
            throw new NullPointerException("The key is null");
        }
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkSecurityAccess("getProperty." + key);
        }
        return secprops.getProperty(key);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static void setProperty(String key, String datnum) {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkSecurityAccess("setProperty." + key);
        }
        secprops.put(key, datnum);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static Set getAlgorithms(String serviceName) {
        //         FIXME for 1.5 classes
        //        new HashSet() -> emptySet()
        Set result = new HashSet();
        Provider[] p = getProviders();
        for (int i = 0; i < p.length; i++) {
            for (Iterator it = p[i].getServices().iterator(); it.hasNext();) {
                Provider.Service s = (Provider.Service) it.next();
                if (s.getType().equalsIgnoreCase(serviceName)) {
                    result.add(s.getAlgorithm());
                }
            }
        }
        return result;
    }

    /**
     * 
     * Update sequence numbers of all providers
     *  
     */
    private static void renumProviders() {
        Provider[] p = Services.getProviders();
        for (int i = 0; i < p.length; i++) {
            p[i].setProviderNumber(i + 1);
        }
    }

    private static class SecurityDoor implements SecurityAccess {
        // Access to Security.renumProviders()
        public void renumProviders() {
            Security.renumProviders();
        }

        //  Access to Security.getAliases()
        public Iterator getAliases(Provider.Service s) {
            return s.getAliases();
        }
        
        // Access to Provider.getService()
        public Provider.Service getService(Provider p, String type) {
            return p.getService(type);
        }
    }
}
