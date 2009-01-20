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

package java.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.Permission;
import java.security.PermissionCollection;

import org.apache.harmony.luni.util.Inet6Util;
import org.apache.harmony.luni.util.Msg;

/**
 * SocketPermissions represent permission to access resources via sockets. The
 * name of the permission should be either the (possibly wildcarded (eg.
 * *.company.com)) DNS style name of the of the host for which access is being
 * requested, or its IP address in standard nn.nn.nn.nn ("dot") notation. The
 * action list can be made up of any of the following:
 * <dl>
 * <dt>connect</dt>
 * <dd>requests permission to connect to the host</dd>
 * <dt>listen</dt>
 * <dd>requests permission to listen for connections from the host</dd>
 * <dt>accept</dt>
 * <dd>requests permission to accept connections from the host</dd>
 * <dt>resolve</dt>
 * <dd>requests permission to resolve the host name</dd>
 * </dl>
 * Note that "resolve" is implied when any (or none) of the others are present.
 * <p>
 * Access to a particular port can be requested by appending a colon and a
 * single digit to the name (eg. "*.company.com:7000"). A range of port numbers
 * can also be specified, by appending a pattern of the form <low>-<high> where
 * <low> and <high> are valid port numbers. If either <low> or <high> is omitted
 * it is equivalent to entering the lowest or highest possible value
 * respectively. For example:
 * 
 * <pre>
 * SocketPermission(&quot;www.company.com:7000-&quot;, &quot;connect&quot;, &quot;accept&quot;)
 * </pre>
 * 
 * represents permission to connect to and accept connections from
 * www.company.com on ports in the range 7000 to 65535.
 */
public final class SocketPermission extends Permission implements Serializable {

    private static final long serialVersionUID = -7204263841984476862L;

    // Bit masks for each of the possible actions
    static final int SP_CONNECT = 1;

    static final int SP_LISTEN = 2;

    static final int SP_ACCEPT = 4;

    static final int SP_RESOLVE = 8;

    // list of actions permitted for socket permission in order, indexed by mask
    // value
    @SuppressWarnings("nls")
    private static final String[] actionNames = { "", "connect", "listen", "",
            "accept", "", "", "", "resolve" };

    // If a wildcard is present store the information
    private transient boolean isPartialWild;

    private transient boolean isWild;

    // The highest port number
    private static final int HIGHEST_PORT = 65535;

    // The lowest port number
    private static final int LOWEST_PORT = 0;

    transient String hostName; // Host name as returned by InetAddress

    transient String ipString; // IP address as returned by InetAddress

    transient boolean resolved; // IP address has been resolved

    // the port range;
    transient int portMin = LOWEST_PORT;

    transient int portMax = HIGHEST_PORT;

    private String actions; // List of all actions allowed by this permission

    transient int actionsMask = SP_RESOLVE;

    /**
     * Constructs an instance of this class. The host name can be a DNS name, an
     * individual hostname, an ip address or the empty string which implies
     * localhost. The port or port range is optional.
     * <p>
     * The action list is a comma-seperated list which can consist of "connect",
     * "listen", "accept", and "resolve". They are case-insensitive and can be
     * put together in any order. "resolve" is always implied.
     * 
     * @param host
     *            java.lang.String the host name
     * @param action
     *            java.lang.String the action string
     */
    public SocketPermission(String host, String action) {
        super(host.equals("") ? "localhost" : host); //$NON-NLS-1$ //$NON-NLS-2$
        hostName = getHostString(host);
        if (action == null) {
            throw new NullPointerException();
        }
        if (action.equals("")) { //$NON-NLS-1$
            throw new IllegalArgumentException();
        }

        setActions(action);
        actions = toCanonicalActionString(action);
        // Use host since we are only checking for port presence
        parsePort(host, hostName);
    }

    /**
     * Compares the argument to the receiver, and answers true if they represent
     * the equal objects using a class specific comparison.
     * <p>
     * 
     * @param o
     *            the object to compare with this object
     * @return <code>true</code> if the object is the same as this object
     *         <code>false</code> if it is different from this object
     * @see #hashCode
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        SocketPermission sp = (SocketPermission) o;
        if (!hostName.equals(sp.hostName)) {
            if (getIPString() == null || !ipString.equals(sp.getIPString())) {
                return false;
            }
        }
        if (this.actionsMask != SP_RESOLVE) {
            if (this.portMin != sp.portMin) {
                return false;
            }
            if (this.portMax != sp.portMax) {
                return false;
            }
        }
        return this.actionsMask == sp.actionsMask;
    }

    /**
     * Answers an integer hash code for the receiver. Any two objects which
     * answer <code>true</code> when passed to <code>.equals</code> must
     * answer the same value for this method.
     * 
     * @return int the receiver's hash.
     * 
     * @see #equals
     */
    @Override
    public int hashCode() {
        return hostName.hashCode() ^ actionsMask ^ portMin ^ portMax;
    }

    /**
     * Answers the canonical action list of this SocketPermission in the order:
     * connect, listen, accept, resolve.
     * 
     * @return java.lang.String the canonical action list
     */
    @Override
    public String getActions() {
        return actions;
    }

    /**
     * Stores the actions for this permission as a bit field
     * 
     * @param actions
     *            java.lang.String the action list
     */
    private void setActions(String actions) throws IllegalArgumentException {
        if (actions.equals("")) { //$NON-NLS-1$
            return;
        }
        boolean parsing = true;
        String action;
        StringBuffer sb = new StringBuffer();
        int pos = 0, length = actions.length();
        while (parsing) {
            char c;
            sb.setLength(0);
            while (pos < length && (c = actions.charAt(pos++)) != ',') {
                sb.append(c);
            }
            if (pos == length) {
                parsing = false;
            }
            action = sb.toString().trim().toLowerCase();
            if (action.equals(actionNames[SP_CONNECT])) {
                actionsMask |= SP_CONNECT;
            } else if (action.equals(actionNames[SP_LISTEN])) {
                actionsMask |= SP_LISTEN;
            } else if (action.equals(actionNames[SP_ACCEPT])) {
                actionsMask |= SP_ACCEPT;
            } else if (action.equals(actionNames[SP_RESOLVE])) {
                // do nothing
            } else {
                throw new IllegalArgumentException(Msg.getString("K0048", //$NON-NLS-1$
                        action));
            }
        }
    }

    /**
     * Check the permission to see if the actions requested by the argument
     * permission are permissable. All argument permission actions, host and
     * port must be implied by this permission in order to return true. This
     * permission may imply additional actions etc. not present in the argument
     * permission.
     * 
     * @return boolean true if this permission implies <code>p</code>, and
     *         false otherwise
     * @param p
     *            java.security.Permission the other socket permission
     */
    @Override
    public boolean implies(Permission p) {
        SocketPermission sp;
        try {
            sp = (SocketPermission) p;
        } catch (ClassCastException e) {
            return false;
        }

        // tests if the action list of p is the subset of the one of the
        // receiver
        if (sp == null || (actionsMask & sp.actionsMask) != sp.actionsMask) {
            return false;
        }

        // only check the port range if the action string of the current object
        // is not "resolve"
        if (!p.getActions().equals("resolve")) { //$NON-NLS-1$
            if ((sp.portMin < this.portMin) || (sp.portMax > this.portMax)) {
                return false;
            }
        }

        // Verify the host is valid
        return checkHost(sp);
    }

    /**
     * Answers a PermissionCollection for storing SocketPermission objects.
     * 
     * @return java.security.PermissionCollection a permission collection
     */
    @Override
    public PermissionCollection newPermissionCollection() {
        return new SocketPermissionCollection();
    }
    
    /**
     * Parse the port, including the minPort, maxPort
     * @param hostPort the host[:port] one
     * @param host the host name we just get
     * @throws IllegalArgumentException If the port is not a positive number or minPort
     *                                  is not less than or equal maxPort
     */
    private void parsePort(String hostPort, String host) throws IllegalArgumentException {
       String port = hostPort.substring(host.length());
       String emptyString = ""; //$NON-NLS-1$

       if (emptyString.equals(port)) {
           // Not specified
           portMin = 80;
           portMax = 80;
           return;
       }
       
       if (":*".equals(port)) {
           // The port range should be 0-65535
           portMin = 0;
           portMax = 65535;
           return;
       }
       
       // Omit ':'
       port = port.substring(1);
       int negIdx = port.indexOf('-');
       String strPortMin = emptyString;
       String strPortMax = emptyString;
       if (-1 == negIdx) {
           // No neg mark, only one number
           strPortMin = port;
           strPortMax = port;
       } else {
           strPortMin = port.substring(0, negIdx);
           strPortMax = port.substring(negIdx + 1);
           if (emptyString.equals(strPortMin)) {
               strPortMin = "0";
           }
           if (emptyString.equals(strPortMax)) {
               strPortMax = "65535";
           }
       }
       try {
           portMin = Integer.valueOf(strPortMin).intValue();
           portMax = Integer.valueOf(strPortMax).intValue();
           
           if (portMin > portMax) {
               throw new IllegalArgumentException(Msg.getString("K0049") + " " + port); //$NON-NLS-1$
           }
       } catch (NumberFormatException e) {
           throw new IllegalArgumentException(Msg.getString("K004a") + " " + port); //$NON-NLS-1$
       }
    }

    /**
     * Creates a canonical action list.
     * 
     * @param action
     *            java.lang.String
     * 
     * @return java.lang.String
     */
    private String toCanonicalActionString(String action) {
        if (action == null || action.equals("") || actionsMask == SP_RESOLVE) { //$NON-NLS-1$
            return actionNames[SP_RESOLVE]; // If none specified return the
        }
        // implied action resolve
        StringBuffer sb = new StringBuffer();
        if ((actionsMask & SP_CONNECT) == SP_CONNECT) {
            sb.append(',');
            sb.append(actionNames[SP_CONNECT]);
        }
        if ((actionsMask & SP_LISTEN) == SP_LISTEN) {
            sb.append(',');
            sb.append(actionNames[SP_LISTEN]);
        }
        if ((actionsMask & SP_ACCEPT) == SP_ACCEPT) {
            sb.append(',');
            sb.append(actionNames[SP_ACCEPT]);
        }
        sb.append(',');
        sb.append(actionNames[SP_RESOLVE]);// Resolve is always implied
        // Don't copy the first ','.
        return actions = sb.substring(1, sb.length());
    }

    private String getIPString() {
        if (!resolved) {
            try {
                ipString = InetAddress.getHostNameInternal(hostName);
            } catch (UnknownHostException e) {
                // ignore
            }
            resolved = true;
        }
        return ipString;
    }

    /**
     * Get the host part from the host[:port] one.
     * The host should be
     *      host = (hostname | IPv4address | IPv6reference | IPv6 in full uncompressed form)
     * The wildcard "*" may be included once in a DNS name host specification. If it is included, 
     * it must be in the leftmost position
     * 
     * @param host
     * @return
     * @throws IllegalArgumentException   if the host is invalid.
     */
    private String getHostString(String host) throws IllegalArgumentException {
        host = host.trim();
        int idx = -1;
        idx = host.indexOf(':');
        isPartialWild = (host.length() > 0 && host.charAt(0) == '*');
        if (isPartialWild) {
            resolved = true;
            isWild = (host.length() == 1);
            if (isWild) {
                return host;
            }
            if (idx > -1) {
                host = host.substring(0, idx);
            }
            return host.toLowerCase();
        }

        int lastIdx = host.lastIndexOf(':');
        
        if (idx == lastIdx) {
            if (-1 != idx) {
                // only one colon, should be port
                host = host.substring(0, idx);
            }
            return host.toLowerCase();
        }
            // maybe ipv6
        boolean isFirstBracket = (host.charAt(0) == '[');
        if (!isFirstBracket) {
            // No bracket, should be in full form
            int colonNum = 0;
            for (int i = 0; i < host.length(); ++i) {
                if (host.charAt(i) == ':') {
                    colonNum++;
                }
            }
            // Get rid of the colon before port
            if (8 == colonNum) {
                host = host.substring(0, lastIdx);
            }
            if (Inet6Util.isIP6AddressInFullForm(host)) {
                return host.toLowerCase();
            }
            throw new IllegalArgumentException(Msg.getString("K004a") + " "
                    + host);
        }
        // forward bracket found
        int bbracketIdx = host.indexOf(']');
        if (-1 == bbracketIdx) {
            // no back bracket found, wrong
            throw new IllegalArgumentException(Msg.getString("K004a") + " "
                    + host);
        }
        host = host.substring(0, bbracketIdx + 1);
        if (Inet6Util.isValidIP6Address(host)) {
            return host.toLowerCase();
        }
        throw new IllegalArgumentException(Msg.getString("K004a") + " " + host);
    }

    /*
     * Determines whether or not this permission could refer to the same host as
     * sp
     */
    boolean checkHost(SocketPermission sp) {
        if (isPartialWild) {
            if (isWild) {
                return true; // Match on any host
            }
            int length = hostName.length() - 1;
            return sp.hostName.regionMatches(sp.hostName.length() - length,
                    hostName, 1, length);
        }
        // The ipString may not be the same, some hosts resolve to
        // multiple ips
        return (getIPString() != null && ipString.equals(sp.getIPString()))
                || hostName.equals(sp.hostName);
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
    }

    private void readObject(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {
        stream.defaultReadObject();
        // Initialize locals
        isPartialWild = false;
        isWild = false;
        portMin = LOWEST_PORT;
        portMax = HIGHEST_PORT;
        actionsMask = SP_RESOLVE;
        hostName = getHostString(getName());
        parsePort(getName(), hostName);
        setActions(actions);
    }
}
