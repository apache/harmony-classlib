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

package org.apache.harmony.security.fortress;

import java.security.AccessController;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


/**
 * This class contains information about all registered providers and preferred
 * implementations for all "serviceName.algName".
 * 
 */

public class Services {

    // The HashMap that contains information about preferred implementations for
    // all serviceName.algName in the registered providers
    private static HashMap services = new HashMap(512);

    // Need refresh flag
    private static boolean needRefresh; // = false;

    /**
     * Refresh number
     */
    public static int refreshNumber = 1;

    // Registered providers
    private static ArrayList providers = new ArrayList(20);

    // Hash for quick provider access by name
    private static HashMap providersNames = new HashMap(20);

    static {
        AccessController.doPrivileged(new java.security.PrivilegedAction() {
            public Object run() {
                loadProviders();
                return null;
            }
        });
    }

    // Load staticaly registered providers and init Services Info
    private static void loadProviders() {
        String providerClassName = null;
        int i = 1;
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        Provider p;

        while ((providerClassName = Security.getProperty("security.provider."
                + i++)) != null) {
            try {
                p = (Provider) Class
                        .forName(providerClassName.trim(), true, cl)
                        .newInstance();
                providers.add(p);
                providersNames.put(p.getName(), p);
                initServiceInfo(p);
            } catch (Exception e) { // ignore
            }
        }
        Engine.door.renumProviders();
    }

    /**
     * Returns registered providers
     * 
     * @return
     */
    public static Provider[] getProviders() {
        return (Provider[]) providers.toArray(new Provider[providers.size()]);
    }

    /**
     * Returns registered providers as List
     * 
     * @return
     */
    public static java.util.List getProvidersList() {
        return new ArrayList(providers);
    }

    /**
     * Returns the provider with the specified name
     * 
     * @param name
     * @return
     */
    public static Provider getProvider(String name) {
        if (name == null) {
            return null;
        }
        return (Provider) providersNames.get(name);
    }

    /**
     * Inserts a provider at a specified position
     * 
     * @param provider
     * @param position
     * @return
     */
    public static int insertProviderAt(Provider provider, int position) {
        int size = providers.size();
        if ((position < 1) || (position > size)) {
            position = size + 1;
        }
        providers.add(position - 1, provider);
        providersNames.put(provider.getName(), provider);
        setNeedRefresh();
        return position;
    }

    /**
     * Removes the provider
     * 
     * @param providerNumber
     */
    public static void removeProvider(int providerNumber) {
        Provider p = (Provider) providers.remove(providerNumber - 1);
        providersNames.remove(p.getName());
        setNeedRefresh();
    }

    /**
     * 
     * Adds information about provider services into HashMap.
     * 
     * @param p
     */
    public static void initServiceInfo(Provider p) {
        Provider.Service serv;
        String key;
        String type;
        String alias;
        StringBuffer sb = new StringBuffer(128);

        for (Iterator it1 = p.getServices().iterator(); it1.hasNext();) {
            serv = (Provider.Service) it1.next();
            type = serv.getType();
            sb.delete(0, sb.length());
            key = sb.append(type).append(".").append(
                    serv.getAlgorithm().toUpperCase()).toString();
            if (!services.containsKey(key)) {
                services.put(key, serv);
            }
            for (Iterator it2 = Engine.door.getAliases(serv); it2.hasNext();) {
                alias = (String) it2.next();
                sb.delete(0, sb.length());
                key = sb.append(type).append(".").append(alias.toUpperCase())
                        .toString();
                if (!services.containsKey(key)) {
                    services.put(key, serv);
                }
            }
        }
    }

    /**
     * 
     * Updates services hashtable for all registerd providers
     *  
     */
    public static void updateServiceInfo() {
        services.clear();
        for (Iterator it = providers.iterator(); it.hasNext();) {
            initServiceInfo((Provider) it.next());
        }
        needRefresh = false;
    }

    /**
     * Returns true if sevices contain any provider information  
     * @return
     */
    public static boolean isEmpty() {
        return services.isEmpty();
    }
    
    /**
     * 
     * Returns service description.
     * Call refresh() befor.
     * 
     * @param key
     * @return
     */
    public static Provider.Service getService(String key) {
        return (Provider.Service) services.get(key);
    }

    /**
     * Prints Services content  
     */
    // FIXME remove debug function
    public static void printServices() {
        refresh();
        java.util.Set s = services.keySet();
        for (java.util.Iterator i = s.iterator(); i.hasNext();) {
            Object key = i.next();
            System.out.println(key + "=" + services.get(key));
        }
    }

    /**
     * Set flag needRefresh 
     *
     */
    public static void setNeedRefresh() {
        needRefresh = true;
    }

    /**
     * Refresh services info
     *
     */
    public static void refresh() {
        if (needRefresh) {
            refreshNumber++;
            updateServiceInfo();
        }
    }
}