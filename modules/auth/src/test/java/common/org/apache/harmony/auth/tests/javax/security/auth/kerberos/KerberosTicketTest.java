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

package org.apache.harmony.auth.tests.javax.security.auth.kerberos;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.security.auth.kerberos.KerberosPrincipal;
import javax.security.auth.kerberos.KerberosTicket;

import junit.framework.TestCase;

public class KerberosTicketTest extends TestCase {

    // ticket's ASN.1 encoding  
    private static final byte[] ticket = { 0x01, 0x02, 0x03, 0x04 };

    // client's principal 
    private static final KerberosPrincipal pClient = new KerberosPrincipal(
            "client@apache.org");

    // server's principal 
    private static final KerberosPrincipal pServer = new KerberosPrincipal(
            "server@apache.org");

    // session key
    private static final byte[] sessionKey = { 0x01, 0x04, 0x03, 0x02 };

    private static final int KEY_TYPE = 1;

    private static final boolean[] flags = { true, false, true, false, true,
            false, true, false, true, false, true, false, };

    private static final Date authTime = new Date(0);

    private static final Date startTime = new Date(1);

    private static final Date endTime = new Date(2);

    private static final Date renewTill = new Date(3);

    private static final InetAddress[] addesses;

    static {
        try {
            addesses = new InetAddress[] { InetAddress.getLocalHost() };
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @tests javax.security.auth.kerberos.KerberosTicket#getAuthTime() 
     */
    public void test_getAuthTime() throws Exception {

        KerberosTicket krbTicket = new KerberosTicket(ticket, pClient, pServer,
                sessionKey, KEY_TYPE, flags, authTime, startTime, endTime,
                renewTill, addesses);

        assertEquals(0, krbTicket.getAuthTime().getTime());

        // initial value is copied
        assertNotSame(authTime, krbTicket.getAuthTime());

        // KerberosTicket instance is immutable 
        assertNotSame(krbTicket.getAuthTime(), krbTicket.getAuthTime());

        // auth time: null value is illegal for contructor
        try {
            new KerberosTicket(ticket, pClient, pServer, sessionKey, KEY_TYPE,
                    flags, null, startTime, endTime, renewTill, addesses);
            fail("No expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }

    /**
     * @tests javax.security.auth.kerberos.KerberosTicket#getClient() 
     */
    public void test_getClient() throws Exception {

        KerberosTicket krbTicket = new KerberosTicket(ticket, pClient, pServer,
                sessionKey, KEY_TYPE, flags, authTime, startTime, endTime,
                renewTill, addesses);

        assertSame(pClient, krbTicket.getClient());

        // client principal: null value is illegal for contructor
        try {
            new KerberosTicket(ticket, null, pServer, sessionKey, KEY_TYPE,
                    flags, authTime, startTime, endTime, renewTill, addesses);
            fail("No expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }

    /**
     * @tests javax.security.auth.kerberos.KerberosTicket#getClientAddresses() 
     */
    public void test_getClientAddresses() throws Exception {

        KerberosTicket krbTicket = new KerberosTicket(ticket, pClient, pServer,
                sessionKey, KEY_TYPE, flags, authTime, startTime, endTime,
                renewTill, addesses);

        assertTrue(Arrays.equals(addesses, krbTicket.getClientAddresses()));

        // initial value is copied
        assertNotSame(addesses, krbTicket.getClientAddresses());

        // KerberosTicket instance is immutable 
        assertNotSame(krbTicket.getClientAddresses(), krbTicket
                .getClientAddresses());

        // addesses: null value is OK for contructor
        krbTicket = new KerberosTicket(ticket, pClient, pServer, sessionKey,
                KEY_TYPE, flags, authTime, startTime, endTime, renewTill, null);
        assertNull(krbTicket.getClientAddresses());
    }

    /**
     * @tests javax.security.auth.kerberos.KerberosTicket#getEncoded() 
     */
    public void test_getEncoded() throws Exception {

        KerberosTicket krbTicket = new KerberosTicket(ticket, pClient, pServer,
                sessionKey, KEY_TYPE, flags, authTime, startTime, endTime,
                renewTill, addesses);

        assertTrue(Arrays.equals(ticket, krbTicket.getEncoded()));

        // initial byte array is copied
        assertNotSame(ticket, krbTicket.getEncoded());

        // KerberosTicket instance is immutable 
        assertNotSame(krbTicket.getEncoded(), krbTicket.getEncoded());

        // ticket: null value is illegal for contructor
        try {
            new KerberosTicket(null, pClient, pServer, sessionKey, KEY_TYPE,
                    flags, authTime, startTime, endTime, renewTill, addesses);
            fail("No expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }

    /**
     * @tests javax.security.auth.kerberos.KerberosTicket#getEndTime() 
     */
    public void test_getEndTime() throws Exception {

        KerberosTicket krbTicket = new KerberosTicket(ticket, pClient, pServer,
                sessionKey, KEY_TYPE, flags, authTime, startTime, endTime,
                renewTill, addesses);

        assertEquals(2, krbTicket.getEndTime().getTime());

        // initial value is copied
        assertNotSame(endTime, krbTicket.getEndTime());

        // KerberosTicket instance is immutable 
        assertNotSame(krbTicket.getEndTime(), krbTicket.getEndTime());

        // end time: null value is illegal for contructor
        try {
            new KerberosTicket(ticket, pClient, pServer, sessionKey, KEY_TYPE,
                    flags, authTime, startTime, null, renewTill, addesses);
            fail("No expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }

    /**
     * @tests javax.security.auth.kerberos.KerberosTicket#getServer() 
     */
    public void test_getServer() throws Exception {

        KerberosTicket krbTicket = new KerberosTicket(ticket, pClient, pServer,
                sessionKey, KEY_TYPE, flags, authTime, startTime, endTime,
                renewTill, addesses);

        assertSame(pServer, krbTicket.getServer());

        // server principal: null value is illegal for contructor
        try {
            new KerberosTicket(ticket, pClient, null, sessionKey, KEY_TYPE,
                    flags, authTime, startTime, endTime, renewTill, addesses);
            fail("No expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }

    /**
     * @tests javax.security.auth.kerberos.KerberosTicket#getSessionKey() 
     */
    public void test_getSessionKey() throws Exception {

        KerberosTicket krbTicket = new KerberosTicket(ticket, pClient, pServer,
                sessionKey, KEY_TYPE, flags, authTime, startTime, endTime,
                renewTill, addesses);

        assertSame(krbTicket.getSessionKey(), krbTicket.getSessionKey());

        // test returned SecretKey object
        SecretKey sKey = krbTicket.getSessionKey();
        byte[] keyBytes = sKey.getEncoded();

        assertTrue(Arrays.equals(sessionKey, keyBytes));
        // initial byte array is copied
        assertNotSame(sessionKey, sKey.getEncoded());
        // key instance is immutable 
        assertNotSame(sKey.getEncoded(), sKey.getEncoded());

        assertEquals("algorithm", "DES", sKey.getAlgorithm());
        assertEquals("format", "RAW", sKey.getFormat());

        // sessionKey: null value is illegal for contructor
        try {
            new KerberosTicket(ticket, pClient, pServer, null, KEY_TYPE, flags,
                    authTime, startTime, endTime, renewTill, addesses);
            fail("No expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }

    /**
     * @tests javax.security.auth.kerberos.KerberosTicket#getSessionKeyType()
     */
    public void test_getSessionKeyType() throws Exception {

        KerberosTicket krbTicket = new KerberosTicket(ticket, pClient, pServer,
                sessionKey, KEY_TYPE, flags, authTime, startTime, endTime,
                renewTill, addesses);

        assertEquals(KEY_TYPE, krbTicket.getSessionKeyType());
    }
}
