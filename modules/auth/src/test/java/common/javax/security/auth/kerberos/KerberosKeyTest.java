/*
 *  Copyright 2006 The Apache Software Foundation or its licensors, as applicable.
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

package javax.security.auth.kerberos;

import java.util.Arrays;

import junit.framework.TestCase;

/**
 * Tests KerberosKey class implementation.
 */
public class KerberosKeyTest extends TestCase {

    // principal object for testing
    private final KerberosPrincipal principal = new KerberosPrincipal(
            "name@aaa.com", 1);

    // byte array for testing
    private final byte[] keyBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04,
            0x05, 0x06, 0x07 };

    /**
     * @tests javax.security.auth.kerberos.KerberosKey#KerberosKey(
     *        javax.security.auth.kerberos.KerberosPrincipal, byte[], int, int)
     */
    public void test_Ctor1() {

        // OK to pass null value for principal parameter
        assertNull(new KerberosKey(null, keyBytes, 0, 0).getPrincipal());

        // NPE for null keyBytes parameter
        try {
            new KerberosKey(principal, null, 0, 0);
            fail("No expected NullPointerException");
        } catch (NullPointerException e) {
        }

        // construct with DES algorithm
        KerberosKey key = new KerberosKey(principal, keyBytes, 1, 123);
        assertEquals("DES algorithm", "DES", key.getAlgorithm());
        assertEquals("version number", 123, key.getVersionNumber());
        assertEquals("format", "RAW", key.getFormat());
        assertSame("principal", principal, key.getPrincipal());
        assertFalse("is destroyed", key.isDestroyed());

        // construct with NULL algorithm
        key = new KerberosKey(principal, keyBytes, 0, 0);
        assertEquals("NULL algorithm", "NULL", key.getAlgorithm());
        assertEquals("version number", 0, key.getVersionNumber());
    }

    /**
     * @tests javax.security.auth.kerberos.KerberosKey#KerberosKey(
     *        javax.security.auth.kerberos.KerberosPrincipal, char[],
     *        java.lang.String)
     */
    public void test_Ctor2() {

        // NPE for null value for principal parameter
        try {
            new KerberosKey(null, new char[10], "DES");
            fail("No expected NullPointerException");
        } catch (NullPointerException e) {
        }

        // NPE for null password value
        try {
            new KerberosKey(principal, null, "DES");
            fail("No expected NullPointerException");
        } catch (NullPointerException e) {
        }

        // IAE for unsupported algorithm
        try {
            new KerberosKey(principal, new char[10],
                    "there_is_no_such_algorithm");
            fail("No expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }

        // if algorithm parameter is null then DES is used
        KerberosKey key = new KerberosKey(principal, new char[10], null);

        assertEquals("algorithm", "DES", key.getAlgorithm());
        assertEquals("format", "RAW", key.getFormat());
        assertEquals("key type", 3, key.getKeyType());
        assertEquals("version number", 0, key.getVersionNumber());
        assertFalse("is destroyed", key.isDestroyed());
        assertSame("principal", principal, key.getPrincipal());
    }

    /**
     * @tests javax.security.auth.kerberos.KerberosKey#getEncoded()
     */
    public void test_getEncoded() {

        KerberosKey key = new KerberosKey(principal, keyBytes, 1, 123);

        byte[] keyBytes1 = key.getEncoded();
        assertTrue("encoded", Arrays.equals(keyBytes, keyBytes1));
        
        // bytes are copied each time we invoke the method
        assertNotSame("keyBytes immutability 1 ", keyBytes, keyBytes1);
        assertNotSame("keyBytes immutability 2 ", keyBytes1, key.getEncoded());
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(KerberosKeyTest.class);
    }
}
