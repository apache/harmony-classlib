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
* @author Vera Y. Petrashkova
* @version $Revision$
*/

package javax.security.sasl;

import java.security.Provider;
import java.security.Security;
import javax.security.auth.callback.CallbackHandler;

import org.apache.harmony.auth.internal.nls.Messages;

import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 
 * @com.intel.drl.spec_ref
 * 
 *  
 */
public class Sasl {
    // SaslClientFactory service name
    private static final String CLIENTFACTORYSRV = "SaslClientFactory"; //$NON-NLS-1$

    // SaslServerFactory service name
    private static final String SERVERFACTORYSRV = "SaslServerFactory"; //$NON-NLS-1$

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static final String POLICY_NOPLAINTEXT = "javax.security.sasl.policy.noplaintext"; //$NON-NLS-1$

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static final String POLICY_NOACTIVE = "javax.security.sasl.policy.noactive"; //$NON-NLS-1$

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static final String POLICY_NODICTIONARY = "javax.security.sasl.policy.nodictionary"; //$NON-NLS-1$

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static final String POLICY_NOANONYMOUS = "javax.security.sasl.policy.noanonymous"; //$NON-NLS-1$

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static final String POLICY_FORWARD_SECRECY = "javax.security.sasl.policy.forward"; //$NON-NLS-1$

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static final String POLICY_PASS_CREDENTIALS = "javax.security.sasl.policy.credentials"; //$NON-NLS-1$

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static final String MAX_BUFFER = "javax.security.sasl.maxbuffer"; //$NON-NLS-1$

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static final String RAW_SEND_SIZE = "javax.security.sasl.rawsendsize"; //$NON-NLS-1$

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static final String REUSE = "javax.security.sasl.reuse"; //$NON-NLS-1$

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static final String QOP = "javax.security.sasl.qop"; //$NON-NLS-1$

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static final String STRENGTH = "javax.security.sasl.strength"; //$NON-NLS-1$

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static final String SERVER_AUTH = "javax.security.sasl.server.authentication"; //$NON-NLS-1$

    // Default public constructor is overridden
    private Sasl() {
    }

    // Forms new instance of factory
    private static Object newInstance(String factoryName, Provider prv)
            throws SaslException {
        String msg = Messages.getString("auth.31"); //$NON-NLS-1$
        Object factory;
        ClassLoader cl = prv.getClass().getClassLoader();
        if (cl == null) {
            cl = ClassLoader.getSystemClassLoader();
        }
        try {
            factory = (Class.forName(factoryName, true, cl)).newInstance();
            return factory;
        } catch (IllegalAccessException e) {
            throw new SaslException(msg + factoryName, e);
        } catch (ClassNotFoundException e) {
            throw new SaslException(msg + factoryName, e);
        } catch (InstantiationException e) {
            throw new SaslException(msg + factoryName, e);
        }
    }

    // This method forms the list of SaslClient/SaslServer factories
    // which are implemented in used providers
    private static Collection findFactories(String service) {
        HashSet fact = new HashSet();
        Provider[] pp = Security.getProviders();
        if ((pp == null) || (pp.length == 0)) {
            return fact;
        }
        HashSet props = new HashSet();
        Enumeration keys;
        String prName;
        String prop;
        for (int i = 0; i < pp.length; i++) {
            prName = pp[i].getName();
            keys = pp[i].keys();
            while (keys.hasMoreElements()) {
                String s = (String) keys.nextElement();
                if (s.startsWith(service)) {
                    prop = pp[i].getProperty(s);
                    try {
                        if (props.add(prName.concat(prop))) {
                            fact.add(newInstance(prop, pp[i]));
                        }
                    } catch (SaslException e) {
                        // ignore this factory
                        e.printStackTrace();
                    }
                }
            }
        }
        return fact;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static Enumeration<SaslClientFactory> getSaslClientFactories() {
        //        Vector res = findFactories(CLIENTFACTORYSRV);
        Collection res = findFactories(CLIENTFACTORYSRV);
        return Collections.enumeration(res);

    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static Enumeration<SaslServerFactory> getSaslServerFactories() {
        //        Vector res = findFactories(SERVERFACTORYSRV);
        Collection res = findFactories(SERVERFACTORYSRV);
        return Collections.enumeration(res);
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static SaslServer createSaslServer(String mechanism,
            String protocol, String serverName, Map<String,?> prop, CallbackHandler cbh)
            throws SaslException {
        if (mechanism == null) {
            throw new NullPointerException(Messages.getString("auth.32")); //$NON-NLS-1$
        }
        HashSet res = (HashSet) findFactories(SERVERFACTORYSRV);
        if (res.size() == 0) {
            return null;
        }
        SaslServer saslS = null;
        SaslServerFactory fact = null;
        boolean is = false;
        String[] mech;
        Iterator iter = res.iterator();
        while (iter.hasNext()) {
            fact = (SaslServerFactory) iter.next();
            mech = fact.getMechanismNames(null);
            is = false;
            if (mech != null) {
                for (int j = 0; j < mech.length; j++) {
                    if (mech[j].equals(mechanism)) {
                        is = true;
                        break;
                    }
                }
            }
            if (is) {
                saslS = fact.createSaslServer(mechanism, protocol, serverName,
                        prop, cbh);
                if (saslS != null) {
                    return saslS;
                }
            }
        }
        return null;
    }

    /**
     * @com.intel.drl.spec_ref
     *  
     */
    public static SaslClient createSaslClient(String[] mechanisms,
            String authanticationID, String protocol, String serverName,
            Map<String,?> prop, CallbackHandler cbh) throws SaslException {
        if (mechanisms == null) {
            throw new NullPointerException(Messages.getString("auth.33")); //$NON-NLS-1$
        }
        HashSet res = (HashSet) findFactories(CLIENTFACTORYSRV);
        if (res.size() == 0) {
            return null;
        }
        SaslClientFactory fact;
        SaslClient saslC = null;
        String[] mech;
        boolean is = false;
        Iterator iter = res.iterator();
        while (iter.hasNext()) {
            fact = (SaslClientFactory) iter.next();
            mech = fact.getMechanismNames(null);
            is = false;
            if (mech != null) {
                for (int j = 0; j < mech.length; j++) {
                    for (int n = 0; n < mechanisms.length; n++) {
                        if (mech[j].equals(mechanisms[n])) {
                            is = true;
                            break;
                        }
                    }
                }
            }
            if (is) {
                saslC = fact.createSaslClient(mechanisms, authanticationID,
                        protocol, serverName, prop, cbh);
                if (saslC != null) {
                    return saslC;
                }
            }
        }
        return null;
    }
}