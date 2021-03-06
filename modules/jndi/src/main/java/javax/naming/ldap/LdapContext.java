/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package javax.naming.ldap;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;

/**
 * An <code>LdapContext</code> is used when LDAPv3 extended operations and/or
 * controls are required.
 * <p>
 * Extended operations are dealt with by the <code>extendedOperation</code>
 * method. All other methods relate to the use of controls. Controls are extra
 * information provided to or from an LDAP v3 server. Controls are either of the
 * type <code>Request</code> or <code>Response</code>. There is a special
 * type of request controls known as connection controls.
 * </p>
 * <p>
 * Connection controls are used by a service provider when connecting or
 * reconnecting to an LDAP server. These are associated with connections and are
 * inherited by derived <code>Context</code>. It should be noted however that
 * subsequent changes to a derived <code>Context</code>'s own connection
 * controls has no impact on the <code>Context</code> from which it inherited.
 * There are a number of environment properties which relate specifically to
 * LDAP service providers. The one which is important here is:
 * <code>java.naming.ldap.control.connect</code> which holds the array of
 * connection controls for a <code>Context</code>. Service providers rely on
 * this to pass on the connection controls to derived <code>Context</code>.
 * </p>
 * <p>
 * Request controls are used by a service provider when performing operations
 * other than connecting or reconnecting. These are referred to as Context
 * Request Controls as they relate to the specific <code>Context</code>
 * instance. It must be noted that, unlike connection controls, they are not
 * inherited. Connection and request controls being split like this allows
 * service provider implementations to pool connections.
 * </p>
 * <p>
 * Response controls are generated by an LDAPv3 server.
 * </p>
 * 
 * @see DirContext
 */
public interface LdapContext extends DirContext {

    /**
     * This is set to the environment property java.naming.factory.control.
     */
    static final String CONTROL_FACTORIES = "java.naming.factory.control"; //$NON-NLS-1$

    /**
     * Deals with extended operations of LDAPv3.
     * 
     * @param e
     *            an extended request
     * @return an extended response for the supplied request
     * @throws NamingException
     *             If an error is encountered.
     */
    ExtendedResponse extendedOperation(ExtendedRequest e)
            throws NamingException;

    /**
     * Creates a new instance of <code>LdapContext</code> using the supplied
     * controls.
     * 
     * @param ac
     *            an array of controls
     * @return a new <code>LdapContext</code> instance
     * @throws NamingException
     *             If an error is encountered.
     */
    LdapContext newInstance(Control[] ac) throws NamingException;

    /**
     * Reconnects using the supplied controls.
     * 
     * @param ac
     *            an array of controls
     * @throws NamingException
     *             If an error is encountered.
     */
    void reconnect(Control[] ac) throws NamingException;

    /**
     * Gets an array of connection controls.
     * 
     * @return an array of connection controls
     * @throws NamingException
     *             If an error is encountered.
     */
    Control[] getConnectControls() throws NamingException;

    /**
     * Sets the request controls.
     * 
     * @param ac
     *            an array of request controls
     * @throws NamingException
     *             If an error is encountered.
     */
    void setRequestControls(Control[] ac) throws NamingException;

    /**
     * Gets the request controls.
     * 
     * @return an array of request controls
     * @throws NamingException
     *             If an error is encountered.
     */
    Control[] getRequestControls() throws NamingException;

    /**
     * Gets the response controls.
     * 
     * @return an array of response controls
     * @throws NamingException
     *             If an error is encountered.
     */
    Control[] getResponseControls() throws NamingException;

}
