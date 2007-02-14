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

package javax.naming.ldap;

import javax.naming.NamingException;

import org.apache.harmony.jndi.internal.nls.Messages;
import org.apache.harmony.jndi.internal.tls.config.ClassFinder;
import org.apache.harmony.jndi.internal.tls.config.ConfigFileNotFound;
import org.apache.harmony.jndi.internal.tls.config.MergePath;

/**
 * 
 * @ar.org.fitc.spec_ref
 * 
 * @version 0.0.1
 * 
 * @author Osvaldo C. Demo
 * 
 */
public class StartTlsRequest implements ExtendedRequest {

    private static final long serialVersionUID = 4441679576360753397L;

    /**
     * @ar.org.fitc.spec_ref
     */
    public static final String OID = "1.3.6.1.4.1.1466.20037";

    /**
     * @ar.org.fitc.spec_ref
     */
    public StartTlsRequest() {

    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public byte[] getEncodedValue() {
        return null;
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public ExtendedResponse createExtendedResponse(String id, byte[] berValue,
            int offset, int length) throws NamingException {
        if (!id.equals("1.3.6.1.4.1.1466.20037")) {
            throw new NamingException(
                    Messages.getString("ldap.07") +" "+ id);
        }
        MergePath p = new MergePath(new String[] {
                System.getProperty("user.dir"),
                System.getProperty("java.home"),
                System.getProperty("org.apache.harmony.boot.class.path") });
        ClassFinder cf = new ClassFinder(p.getPathNames());
        String[] classList;
        Class retClass;
        try {
            classList = cf.getClassList();
            for (int i = 0; i < classList.length; i++) {
                try {
                    retClass = Class.forName(classList[i], true, Thread
                            .currentThread().getContextClassLoader());
                    try {
                        return (StartTlsResponse) retClass.newInstance();
                    } catch (InstantiationException e) {
                    } catch (IllegalAccessException e) {
                    } catch (ClassCastException e) {
                    }
                } catch (ClassNotFoundException e) {
                }
            }
            throw new NamingException(Messages.getString("ldap.08")+" "+ classList);
        } catch (ConfigFileNotFound e) {
            throw new NamingException(Messages.getString("ldap.09"));
            // return new TlsResponseImpl(); TODO This should return a provider
            // default implementation
        }
    }

    /**
     * @ar.org.fitc.spec_ref
     */
    public String getID() {
        return OID;
    }
}
