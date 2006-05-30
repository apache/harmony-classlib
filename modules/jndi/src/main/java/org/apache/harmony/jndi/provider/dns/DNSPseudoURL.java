/*
 * Copyright 2005-2006 The Apache Software Foundation or its licensors, as applicable
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
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author Alexei Y. Zakharov
 * @version $Revision: 1.1.2.4 $
 */

package org.apache.harmony.jndi.provider.dns;

import java.util.StringTokenizer;

/**
 * Represents a DNS pseudo URL.
 * @author Alexei Zakharov
 * @version $Revision: 1.1.2.4 $
 */
public class DNSPseudoURL {

    private String host = "localhost";
    private int port = ProviderConstants.DEFAULT_DNS_PORT;
    private String domain = ".";
    private boolean hostIpWasGiven = false;

    /**
     * Parses given argument and constructs new <code>DNSPseudoURL</code>
     * object. The format of the argument is:<br>
     * <code>dns:[//host[:port]][/domain]</code><br>
     * If no host information was given then <code>localhost</code> will be
     * used. If no port was given then the standard DNS server port
     * <code>53</code> will be used. If no domain was given then the root domain
     * will be used. All domain here are treated as absolute domains.
     * @param DNS string representation of DNS URL 
     * @throws IllegalArgumentException if the argument is not in acceptable
     * format
     * @throws NullPointerException if the argument is null 
     */
    public DNSPseudoURL(String strForm) throws IllegalArgumentException
    
    {
        StringTokenizer st = null;
        StringTokenizer st2 = null;
        String token;

        if (strForm == null) {
            throw new NullPointerException("strForm is null");
        }
        st = new StringTokenizer(strForm, "/", true);
        if (!st.hasMoreTokens()) {
            throw new IllegalArgumentException("Empty URL");
        }
        // scheme
        token = st.nextToken();
        if (!token.equals("dns:")) {
            throw new IllegalArgumentException("Specified scheme is not dns");
        }
        // host
        if (st.hasMoreTokens()) {
            token = st.nextToken();
            if (!token.equals("/") || !st.hasMoreTokens()) { 
                throw new IllegalArgumentException("Bad URL syntax");
            }
            token = st.nextToken();
            if (token.equals("/")) {
                // host[:port] was given
                if (!st.hasMoreElements()) {
                    throw new IllegalArgumentException("Bad URL syntax");
                }
                token = st.nextToken();
                st2 = new StringTokenizer(token, ":");
                host = st2.nextToken();
                try {
                    ProviderMgr.parseIpStr(host);
                    hostIpWasGiven = true;
                } catch (IllegalArgumentException e) {
                    hostIpWasGiven = false;
                }
                // port
                if (st2.hasMoreTokens()) {
                    port = Integer.parseInt(st2.nextToken());
                }
                // domain
                if (st.hasMoreTokens()) {
                    token = st.nextToken();
                    if (!token.equals("/") || !st.hasMoreTokens()) {
                        throw new IllegalArgumentException("Bad URL syntax");
                    }
                    domain = ProviderMgr.normalizeName(st.nextToken());
                }
            } else {
                // domain
                domain = ProviderMgr.normalizeName(token);
            }
            // extra
            if (st.hasMoreTokens()) {
                throw new IllegalArgumentException(
                        "Extra characters encountered at the end of the URL");
            }
        }
    }

    /**
     * @return Returns the domain.
     */
    public String getDomain() {
        return domain;
    }
    /**
     * @return Returns the host.
     */
    public String getHost() {
        return host;
    }
    /**
     * @return Returns the hostIpWasGiven.
     */
    public boolean isHostIpGiven() {
        return hostIpWasGiven;
    }
    /**
     * @return Returns the port.
     */
    public int getPort() {
        return port;
    }
}
