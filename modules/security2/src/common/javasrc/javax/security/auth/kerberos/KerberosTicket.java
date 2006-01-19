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

package javax.security.auth.kerberos;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.security.auth.DestroyFailedException;
import javax.security.auth.Destroyable;
import javax.security.auth.RefreshFailedException;
import javax.security.auth.Refreshable;

import org.apache.harmony.security.utils.Array;


/**
 * @com.intel.drl.spec_ref
 */
public class KerberosTicket implements Destroyable, Refreshable, Serializable {

    private static final long serialVersionUID = 7395334370157380539L;

    // The description of these falgs defines in the Kerberos Protocol Specification (RFC 1510).
    
    // reserved flag for future expansion
    private static final int RESERVED = 0;

    // FORWARDABLE flag 
    private static final int FORWARDABLE = 1;

    // FORWARDED flag
    private static final int FORWARDED = 2;

    // PROXIABLE flag
    private static final int PROXIABLE = 3;

    // PROXY flag
    private static final int PROXY = 4;

    // MAY_POSTDATE flag, while unuses
    private static final int MAY_POSTDATE = 5;

    // POSTDATED flag
    private static final int POSTDATED = 6;

    // INVALID flag, while unuses
    private static final int INVALID = 7;

    // RENEWABLE flag
    private static final int RENEWABLE = 8;

    // INITIAL flag
    private static final int INITIAL = 9;

    // PRE_AUTHENT flag, while unuses
    private static final int PRE_AUTHENT = 10;

    // HW_AUTHENT flag, while unuses
    private static final int HW_AUTHENT = 11;

    // UNUSED flag, 12 - 32 bit reserved
    private static final int UNUSED = 31; 

    // line feed 
    private static final String LF = "\n";
    
    //ASN.1 encoding of the ticket
    private byte[] asn1Encoding;

    //raw bytes for the session key
    private KeyImpl sessionKey;

    //ticket flags
    private boolean[] flags;

    //time of initial authentication for the client
    private Date authTime;

    //time after which the ticket will be valid
    private Date startTime;

    // time after which the ticket will be invalid
    private Date endTime;

    // expiration time for the ticket
    private Date renewTill;

    // client that owns this ticket
    private KerberosPrincipal client;

    //service that owns this ticket
    private KerberosPrincipal server;

    //addresses from where the ticket may be used by the client
    private InetAddress[] clientAddresses;

    // indicates the ticket state
    private transient boolean destroyed;
    

    /**
     * @com.intel.drl.spec_ref
     */
    public KerberosTicket(byte[] asn1Encoding, KerberosPrincipal client,
                          KerberosPrincipal server, byte[] keyBytes,
                          int keyType, boolean[] flags, Date authTime,
                          Date startTime, Date endTime, Date renewTill,
                          InetAddress[] clientAddresses) {

        if (asn1Encoding == null) {
            throw new IllegalArgumentException(
                "ASN.1 encoding of this ticket is null");
        }
        if (client == null) {
            throw new IllegalArgumentException("client is null");
        }

        if (server == null) {
            throw new IllegalArgumentException("server is null");
        }

        if (keyBytes == null) {
            throw new IllegalArgumentException("session key is null");
        }

        if (authTime == null) {
            throw new IllegalArgumentException("authentication time is null");
        }

        if (endTime == null) {
            throw new IllegalArgumentException("expiration time is null");
        }

        this.asn1Encoding = new byte[asn1Encoding.length];
        System.arraycopy(asn1Encoding, 0, this.asn1Encoding, 0,
                         this.asn1Encoding.length);

        this.client = client;
        this.server = server;
        this.sessionKey = new KeyImpl(keyBytes, keyType);

        if (flags == null) {
            this.flags = new boolean[UNUSED];
        } else if (flags.length > UNUSED) {
            this.flags = new boolean[flags.length];
            //Arrays.fill(flags, UNUSED, flags.length, false);
            System.arraycopy(flags, 0, this.flags, 0, this.flags.length);
        } else {
            this.flags = new boolean[flags.length];
            //System.arraycopy(flags, 0, this.flags, 0, this.flags.length);
            for (int i = 0; i < flags.length; i++) {
                this.flags[i] = flags[i];
            }
        }

        if (flags[RENEWABLE] && renewTill == null) {
            throw new IllegalArgumentException(
                "An absolute expiration time can not be null for renewable tickets");
        }

        this.renewTill = renewTill;

        if (startTime != null) {
            this.startTime = startTime;
        } else {
            this.startTime = authTime; 
        }
        
        if (startTime.getTime() > endTime.getTime()) {
            //TODO: make correct description of the exception  
            throw new IllegalArgumentException("Incorrect start or end time");
        }
        
        this.authTime = authTime;
        this.endTime = endTime;
        
        if (clientAddresses != null) {
            this.clientAddresses = new InetAddress[clientAddresses.length];
            System.arraycopy(clientAddresses, 0, this.clientAddresses, 0,
                             this.clientAddresses.length);
        }

    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final KerberosPrincipal getClient() {
        checkState();
        return client;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final KerberosPrincipal getServer() {
        checkState();
        return server;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final SecretKey getSessionKey() {
        checkState();
        return (SecretKey)sessionKey;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final int getSessionKeyType() {
        checkState();
        return sessionKey.getKeyType();
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final byte[] getEncoded() {
        checkState();
        byte[] tmp = new byte[this.asn1Encoding.length];
        System.arraycopy(this.asn1Encoding, 0, tmp, 0, tmp.length);
        return tmp;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final boolean isForwardable() {
        checkState();
        return flags[FORWARDABLE];
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final boolean isForwarded() {
        checkState();
        //TODO: was based on authentication involving a forwarde TGT ?
        return flags[FORWARDED];
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final boolean isProxiable() {
        checkState();
        return flags[PROXIABLE];
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final boolean isProxy() {
        checkState();
        return flags[PROXY];
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final boolean isPostdated() {
        checkState();
        return flags[POSTDATED];
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final boolean isRenewable() {
        checkState();
        return flags[RENEWABLE];
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final boolean isInitial() {
        checkState();
        return flags[INITIAL];
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final boolean[] getFlags() {
        checkState();
        boolean[] tmp = new boolean[flags.length];
        System.arraycopy(flags, 0, tmp, 0, tmp.length);
        return tmp;

    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final Date getAuthTime() {
        checkState();
        return authTime;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final Date getStartTime() {
        checkState();
        return startTime;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final Date getEndTime() {
        checkState();
        return endTime;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final Date getRenewTill() {
        checkState();
        return renewTill;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public final InetAddress[] getClientAddresses() {
        checkState();
        if (this.clientAddresses != null) {
            InetAddress[] tmp = new InetAddress[this.clientAddresses.length];
            System.arraycopy(clientAddresses, 0, tmp, 0, tmp.length);
            return tmp;
        }
        return null;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void destroy() throws DestroyFailedException {
        if (!destroyed) {
            Arrays.fill(this.asn1Encoding, (byte)0);
            this.client = null;
            this.server = null;
            this.sessionKey.destroy();
            this.flags = null;
            this.authTime = null;
            this.startTime = null;
            this.endTime = null;
            this.renewTill = null;
            this.clientAddresses = null;
            destroyed = true;
        } else {
            throw new DestroyFailedException("This ticket is already destroyed"); 
        }

    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean isDestroyed() {
        return destroyed;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public void refresh() throws RefreshFailedException {

        checkState();
        
        if (!flags[RENEWABLE]) {
            throw new RefreshFailedException("The ticket is not renewable");
        }

        if (System.currentTimeMillis() > this.renewTill.getTime()) {
            throw new RefreshFailedException(
                "The allowable renew time is passed for this ticket");
        }
        
        //TODO: need access to a KDC server          
        throw new UnsupportedOperationException(); 
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean isCurrent() {
        checkState();
        if (this.getStartTime().getTime() <= System.currentTimeMillis()
            && System.currentTimeMillis() <= this.getEndTime().getTime()) {
            return true;
        }
        return false;
    }

    /**
     * @com.intel.drl.spec_ref
     */
    public String toString() {
        checkState();
        StringBuffer sb = new StringBuffer();
        sb.append("Ticket = ").append(Array.toString(asn1Encoding,"(hex) ") + LF);
        sb.append("Client Principal = ").append(client.getName() + LF);
        sb.append("Server Principal = ").append(server.getName() + LF);
        //TODO: append session key
        sb.append("Session Key = ").append(sessionKey.toString() + LF);
        sb.append("Forwardable Ticket = ").append(flags[FORWARDABLE] + LF);
        sb.append("Forwarded Ticket = ").append(flags[FORWARDED] + LF);
        sb.append("Proxiable Ticket = ").append(flags[PROXIABLE] + LF);
        sb.append("Proxy Ticket = ").append(flags[PROXY] + LF);
        sb.append("Postdated Ticket = ").append(flags[POSTDATED] + LF);
        sb.append("Renewable Ticket = ").append(flags[RENEWABLE] + LF);
        sb.append("Initial Ticket = ").append(flags[INITIAL] + LF);
        sb.append("Auth Time = ").append(this.authTime.toString() + LF);
        sb.append("Start Time = ").append(this.startTime.toString() + LF);
        sb.append("End Time = ").append(this.endTime.toString() + LF);
        sb.append("Renew Till = ").append(this.renewTill.toString() + LF);
        sb.append("Client Addresses ");
        if (clientAddresses != null) {
            for (int i = 0; i < clientAddresses.length; i++) {
                if (clientAddresses[i] == null) {
                    throw new NullPointerException("InetAddress is null");
                }
                sb.append("clientAddresses[" + i + "] = ").append(clientAddresses[i].toString() + LF +"\t\t");
            }
        } else {
            sb.append("null");
        }
        
        return sb.toString();
    }

    /** 
     * if a key is destroyed then IllegalStateException must be thrown 
     */
    private void checkState() {
        if (destroyed) {
            throw new IllegalStateException("This ticket is destroyed");
        }
    }
}