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

package org.apache.harmony.xnet.provider.jsse;

import junit.framework.TestCase;

/**
 * Tests for <code>CipherSuite</code> constructor and methods
 * 
 */
public class CipherSuiteTest extends TestCase {

    public void testEquals() {
        CipherSuite c1 = new CipherSuite("CipherSuite1", false, 0, "", "",
                new byte[] { 10, 10 });
        CipherSuite c2 = new CipherSuite("CipherSuite2", false, 0, "", "",
                new byte[] { 10, 10 });
        CipherSuite c3 = new CipherSuite("CipherSuite3", false, 0, "", "",
                new byte[] { 10, 11 });
        CipherSuite c4 = new CipherSuite("CipherSuite4", false, 0, "", "",
                new byte[] { 11, 10 });
        if (!c1.equals(c2) || c1.equals(c3) || c4.equals(c1) || c4.equals(c3)) {
            fail("testEquals failed");
        }
    }

    public void testToString() {
        CipherSuite c1 = new CipherSuite("CipherSuite1", false, 0, "", "",
                new byte[] { 10, 10 });
        assertEquals("testToString failed", "CipherSuite1: 10 10",
                c1.toString());
    }

    public void testGetByName() {
        CipherSuite[] suites = CipherSuite.getSupported();
        for (int i = 0; i < suites.length; i++) {
            assertEquals("incorrect cipher suite returned", suites[i],
                    CipherSuite.getByName(suites[i].getName()));
        }
        assertNull("non null cipher suite returned for name: SomeName",
                CipherSuite.getByName("SomeName"));
    }

    /*
     * Class under test for CipherSuite getByCode(byte, byte)
     */
    public void testGetByCodebytebyte() {
        CipherSuite[] suites = CipherSuite.getSupported();
        for (int i = 0; i < suites.length; i++) {
            byte[] code = suites[i].toBytes();
            assertEquals("incorrect cipher suite returned", suites[i],
                    CipherSuite.getByCode(code[0], code[1]));
        }
        assertEquals("incorrect cipher suite returned for code: 10 10",
                new CipherSuite("UNKNOUN_10_10", false, 0, "", "", new byte[] {
                        10, 10 }), CipherSuite.getByCode((byte) 10, (byte) 10));
    }

    /*
     * Class under test for CipherSuite getByCode(byte, byte, byte)
     */
    public void testGetByCodebytebytebyte() {
        CipherSuite[] suites = CipherSuite.getSupported();
        for (int i = 0; i < suites.length; i++) {
            byte[] code = suites[i].toBytes();
            assertEquals("incorrect cipher suite returned", suites[i],
                    CipherSuite.getByCode((byte) 0, (byte) 0, code[1]));
        }
        assertEquals("incorrect cipher suite returned for code: 10 10 10",
                new CipherSuite("UNKNOUN_10_10_10", false, 0, "", "", 
                        new byte[] { 10, 10, 10 }),
                CipherSuite.getByCode((byte) 10, (byte) 10, (byte) 10));
    }

    public void testIsAnonymous() {
        CipherSuite c1 = new CipherSuite("CipherSuite1", false,
                CipherSuite.KeyExchange_DH_anon, "", "", new byte[] { 10, 10 });
        CipherSuite c2 = new CipherSuite("CipherSuite2", false,
                CipherSuite.KeyExchange_DH_anon_EXPORT, "", "", new byte[] { 9,
                        10 });
        CipherSuite c3 = new CipherSuite("CipherSuite3", false,
                CipherSuite.KeyExchange_DH_DSS, "", "", new byte[] { 10, 11 });
        CipherSuite c4 = new CipherSuite("CipherSuite4", false,
                CipherSuite.KeyExchange_DH_RSA, "", "", new byte[] { 11, 10 });
        assertTrue(c1.isAnonymous());
        assertTrue(c1.isAnonymous());
        assertFalse(c3.isAnonymous());
        assertFalse(c4.isAnonymous());
    }

    public void testGetSupported() {
        CipherSuite[] suites = CipherSuite.getSupported();
        for (int i = 0; i < suites.length; i++) {
            assertTrue(suites[i].supported);
        }
    }

    public void testGetSupportedCipherSuiteNames() {
        CipherSuite[] suites = CipherSuite.getSupported();
        String[] names = CipherSuite.getSupportedCipherSuiteNames();
        for (int i = 0; i < suites.length; i++) {
            assertEquals(suites[i].getName(), names[i]);
        }
    }

    public void testGetBulkEncryptionAlgorithm() {       
        assertEquals(CipherSuite.TLS_NULL_WITH_NULL_NULL
                .getBulkEncryptionAlgorithm(), null);
        assertEquals(CipherSuite.TLS_RSA_WITH_NULL_MD5
                .getBulkEncryptionAlgorithm(), null);
        assertEquals(CipherSuite.TLS_RSA_WITH_NULL_SHA
                .getBulkEncryptionAlgorithm(), null);
        assertEquals(CipherSuite.TLS_RSA_EXPORT_WITH_RC4_40_MD5
                .getBulkEncryptionAlgorithm(), "RC4");
        assertEquals(CipherSuite.TLS_RSA_WITH_RC4_128_MD5
                .getBulkEncryptionAlgorithm(), "RC4");
        assertEquals(CipherSuite.TLS_RSA_WITH_RC4_128_SHA
                .getBulkEncryptionAlgorithm(), "RC4");
        assertEquals(CipherSuite.TLS_RSA_EXPORT_WITH_RC2_CBC_40_MD5
                .getBulkEncryptionAlgorithm(), "RC2/CBC/NoPadding");
        assertEquals(CipherSuite.TLS_RSA_WITH_IDEA_CBC_SHA
                .getBulkEncryptionAlgorithm(), "IDEA/CBC/NoPadding");
        assertEquals(CipherSuite.TLS_RSA_EXPORT_WITH_DES40_CBC_SHA
                .getBulkEncryptionAlgorithm(), "DES/CBC/NoPadding");
        assertEquals(CipherSuite.TLS_RSA_WITH_DES_CBC_SHA
                .getBulkEncryptionAlgorithm(), "DES/CBC/NoPadding");
        assertEquals(CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA
                .getBulkEncryptionAlgorithm(), "DESede/CBC/NoPadding");
        assertEquals(CipherSuite.TLS_DH_DSS_EXPORT_WITH_DES40_CBC_SHA
                .getBulkEncryptionAlgorithm(), "DES/CBC/NoPadding");
        assertEquals(CipherSuite.TLS_DH_DSS_WITH_DES_CBC_SHA
                .getBulkEncryptionAlgorithm(), "DES/CBC/NoPadding");
        assertEquals(CipherSuite.TLS_DH_DSS_WITH_3DES_EDE_CBC_SHA
                .getBulkEncryptionAlgorithm(), "DESede/CBC/NoPadding");
        assertEquals(CipherSuite.TLS_DH_RSA_EXPORT_WITH_DES40_CBC_SHA
                .getBulkEncryptionAlgorithm(), "DES/CBC/NoPadding");
        assertEquals(CipherSuite.TLS_DH_RSA_WITH_DES_CBC_SHA
                .getBulkEncryptionAlgorithm(), "DES/CBC/NoPadding");
        assertEquals(CipherSuite.TLS_DH_RSA_WITH_3DES_EDE_CBC_SHA
                .getBulkEncryptionAlgorithm(), "DESede/CBC/NoPadding");
        assertEquals(CipherSuite.TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA
                .getBulkEncryptionAlgorithm(), "DES/CBC/NoPadding");
        assertEquals(CipherSuite.TLS_DHE_DSS_WITH_DES_CBC_SHA
                .getBulkEncryptionAlgorithm(), "DES/CBC/NoPadding");
        assertEquals(CipherSuite.TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA
                .getBulkEncryptionAlgorithm(), "DESede/CBC/NoPadding");
        assertEquals(CipherSuite.TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA
                .getBulkEncryptionAlgorithm(), "DES/CBC/NoPadding");
        assertEquals(CipherSuite.TLS_DHE_RSA_WITH_DES_CBC_SHA
                .getBulkEncryptionAlgorithm(), "DES/CBC/NoPadding");
        assertEquals(CipherSuite.TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA
                .getBulkEncryptionAlgorithm(), "DESede/CBC/NoPadding");
        assertEquals(CipherSuite.TLS_DH_anon_EXPORT_WITH_RC4_40_MD5
                .getBulkEncryptionAlgorithm(), "RC4");
        assertEquals(CipherSuite.TLS_DH_anon_WITH_RC4_128_MD5
                .getBulkEncryptionAlgorithm(), "RC4");
        assertEquals(CipherSuite.TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA
                .getBulkEncryptionAlgorithm(), "DES/CBC/NoPadding");
        assertEquals(CipherSuite.TLS_DH_anon_WITH_DES_CBC_SHA
                .getBulkEncryptionAlgorithm(), "DES/CBC/NoPadding");
        assertEquals(CipherSuite.TLS_DH_anon_WITH_3DES_EDE_CBC_SHA
                .getBulkEncryptionAlgorithm(), "DESede/CBC/NoPadding");
    }

    public void testGetBlockSize() {
        assertEquals(CipherSuite.TLS_NULL_WITH_NULL_NULL
                .getBlockSize(), 0);
        assertEquals(CipherSuite.TLS_RSA_WITH_NULL_MD5
                .getBlockSize(), 0);
        assertEquals(CipherSuite.TLS_RSA_WITH_NULL_SHA
                .getBlockSize(), 0);
        assertEquals(CipherSuite.TLS_RSA_EXPORT_WITH_RC4_40_MD5
                .getBlockSize(), 0);
        assertEquals(CipherSuite.TLS_RSA_WITH_RC4_128_MD5
                .getBlockSize(), 0);
        assertEquals(CipherSuite.TLS_RSA_WITH_RC4_128_SHA
                .getBlockSize(), 0);
        assertEquals(CipherSuite.TLS_RSA_EXPORT_WITH_RC2_CBC_40_MD5
                .getBlockSize(), 8);
        assertEquals(CipherSuite.TLS_RSA_WITH_IDEA_CBC_SHA
                .getBlockSize(), 8);
        assertEquals(CipherSuite.TLS_RSA_EXPORT_WITH_DES40_CBC_SHA
                .getBlockSize(), 8);
        assertEquals(CipherSuite.TLS_RSA_WITH_DES_CBC_SHA
                .getBlockSize(), 8);
        assertEquals(CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA
                .getBlockSize(), 8);
        assertEquals(CipherSuite.TLS_DH_DSS_EXPORT_WITH_DES40_CBC_SHA
                .getBlockSize(), 8);
        assertEquals(CipherSuite.TLS_DH_DSS_WITH_DES_CBC_SHA
                .getBlockSize(), 8);
        assertEquals(CipherSuite.TLS_DH_DSS_WITH_3DES_EDE_CBC_SHA
                .getBlockSize(), 8);
        assertEquals(CipherSuite.TLS_DH_RSA_EXPORT_WITH_DES40_CBC_SHA
                .getBlockSize(), 8);
        assertEquals(CipherSuite.TLS_DH_RSA_WITH_DES_CBC_SHA
                .getBlockSize(), 8);
        assertEquals(CipherSuite.TLS_DH_RSA_WITH_3DES_EDE_CBC_SHA
                .getBlockSize(), 8);
        assertEquals(CipherSuite.TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA
                .getBlockSize(), 8);
        assertEquals(CipherSuite.TLS_DHE_DSS_WITH_DES_CBC_SHA
                .getBlockSize(), 8);
        assertEquals(CipherSuite.TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA
                .getBlockSize(), 8);
        assertEquals(CipherSuite.TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA
                .getBlockSize(), 8);
        assertEquals(CipherSuite.TLS_DHE_RSA_WITH_DES_CBC_SHA
                .getBlockSize(), 8);
        assertEquals(CipherSuite.TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA
                .getBlockSize(), 8);
        assertEquals(CipherSuite.TLS_DH_anon_EXPORT_WITH_RC4_40_MD5
                .getBlockSize(), 0);
        assertEquals(CipherSuite.TLS_DH_anon_WITH_RC4_128_MD5
                .getBlockSize(), 0);
        assertEquals(CipherSuite.TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA
                .getBlockSize(), 8);
        assertEquals(CipherSuite.TLS_DH_anon_WITH_DES_CBC_SHA
                .getBlockSize(), 8);
        assertEquals(CipherSuite.TLS_DH_anon_WITH_3DES_EDE_CBC_SHA
                .getBlockSize(), 8);
    }

    public void testGetHmacName() {
        assertEquals(CipherSuite.TLS_NULL_WITH_NULL_NULL
                .getHmacName(), null);
        assertEquals(CipherSuite.TLS_RSA_WITH_NULL_MD5
                .getHmacName(), "HmacMD5");
        assertEquals(CipherSuite.TLS_RSA_WITH_NULL_SHA
                .getHmacName(), "HmacSHA1");
        assertEquals(CipherSuite.TLS_RSA_EXPORT_WITH_RC4_40_MD5
                .getHmacName(), "HmacMD5");
        assertEquals(CipherSuite.TLS_RSA_WITH_RC4_128_MD5
                .getHmacName(), "HmacMD5");
        assertEquals(CipherSuite.TLS_RSA_WITH_RC4_128_SHA
                .getHmacName(), "HmacSHA1");
        assertEquals(CipherSuite.TLS_RSA_EXPORT_WITH_RC2_CBC_40_MD5
                .getHmacName(), "HmacMD5");
        assertEquals(CipherSuite.TLS_RSA_WITH_IDEA_CBC_SHA
                .getHmacName(), "HmacSHA1");
        assertEquals(CipherSuite.TLS_RSA_EXPORT_WITH_DES40_CBC_SHA
                .getHmacName(), "HmacSHA1");
        assertEquals(CipherSuite.TLS_RSA_WITH_DES_CBC_SHA
                .getHmacName(), "HmacSHA1");
        assertEquals(CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA
                .getHmacName(), "HmacSHA1");
        assertEquals(CipherSuite.TLS_DH_DSS_EXPORT_WITH_DES40_CBC_SHA
                .getHmacName(), "HmacSHA1");
        assertEquals(CipherSuite.TLS_DH_DSS_WITH_DES_CBC_SHA
                .getHmacName(), "HmacSHA1");
        assertEquals(CipherSuite.TLS_DH_DSS_WITH_3DES_EDE_CBC_SHA
                .getHmacName(), "HmacSHA1");
        assertEquals(CipherSuite.TLS_DH_RSA_EXPORT_WITH_DES40_CBC_SHA
                .getHmacName(), "HmacSHA1");
        assertEquals(CipherSuite.TLS_DH_RSA_WITH_DES_CBC_SHA
                .getHmacName(), "HmacSHA1");
        assertEquals(CipherSuite.TLS_DH_RSA_WITH_3DES_EDE_CBC_SHA
                .getHmacName(), "HmacSHA1");
        assertEquals(CipherSuite.TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA
                .getHmacName(), "HmacSHA1");
        assertEquals(CipherSuite.TLS_DHE_DSS_WITH_DES_CBC_SHA
                .getHmacName(), "HmacSHA1");
        assertEquals(CipherSuite.TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA
                .getHmacName(), "HmacSHA1");
        assertEquals(CipherSuite.TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA
                .getHmacName(), "HmacSHA1");
        assertEquals(CipherSuite.TLS_DHE_RSA_WITH_DES_CBC_SHA
                .getHmacName(), "HmacSHA1");
        assertEquals(CipherSuite.TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA
                .getHmacName(), "HmacSHA1");
        assertEquals(CipherSuite.TLS_DH_anon_EXPORT_WITH_RC4_40_MD5
                .getHmacName(), "HmacMD5");
        assertEquals(CipherSuite.TLS_DH_anon_WITH_RC4_128_MD5
                .getHmacName(), "HmacMD5");
        assertEquals(CipherSuite.TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA
                .getHmacName(), "HmacSHA1");
        assertEquals(CipherSuite.TLS_DH_anon_WITH_DES_CBC_SHA
                .getHmacName(), "HmacSHA1");
        assertEquals(CipherSuite.TLS_DH_anon_WITH_3DES_EDE_CBC_SHA
                .getHmacName(), "HmacSHA1");
    }

    public void testGetHashName() {
        assertEquals(CipherSuite.TLS_NULL_WITH_NULL_NULL
                .getHashName(), null);
        assertEquals(CipherSuite.TLS_RSA_WITH_NULL_MD5
                .getHashName(), "MD5");
        assertEquals(CipherSuite.TLS_RSA_WITH_NULL_SHA
                .getHashName(), "SHA-1");
        assertEquals(CipherSuite.TLS_RSA_EXPORT_WITH_RC4_40_MD5
                .getHashName(), "MD5");
        assertEquals(CipherSuite.TLS_RSA_WITH_RC4_128_MD5
                .getHashName(), "MD5");
        assertEquals(CipherSuite.TLS_RSA_WITH_RC4_128_SHA
                .getHashName(), "SHA-1");
        assertEquals(CipherSuite.TLS_RSA_EXPORT_WITH_RC2_CBC_40_MD5
                .getHashName(), "MD5");
        assertEquals(CipherSuite.TLS_RSA_WITH_IDEA_CBC_SHA
                .getHashName(), "SHA-1");
        assertEquals(CipherSuite.TLS_RSA_EXPORT_WITH_DES40_CBC_SHA
                .getHashName(), "SHA-1");
        assertEquals(CipherSuite.TLS_RSA_WITH_DES_CBC_SHA
                .getHashName(), "SHA-1");
        assertEquals(CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA
                .getHashName(), "SHA-1");
        assertEquals(CipherSuite.TLS_DH_DSS_EXPORT_WITH_DES40_CBC_SHA
                .getHashName(), "SHA-1");
        assertEquals(CipherSuite.TLS_DH_DSS_WITH_DES_CBC_SHA
                .getHashName(), "SHA-1");
        assertEquals(CipherSuite.TLS_DH_DSS_WITH_3DES_EDE_CBC_SHA
                .getHashName(), "SHA-1");
        assertEquals(CipherSuite.TLS_DH_RSA_EXPORT_WITH_DES40_CBC_SHA
                .getHashName(), "SHA-1");
        assertEquals(CipherSuite.TLS_DH_RSA_WITH_DES_CBC_SHA
                .getHashName(), "SHA-1");
        assertEquals(CipherSuite.TLS_DH_RSA_WITH_3DES_EDE_CBC_SHA
                .getHashName(), "SHA-1");
        assertEquals(CipherSuite.TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA
                .getHashName(), "SHA-1");
        assertEquals(CipherSuite.TLS_DHE_DSS_WITH_DES_CBC_SHA
                .getHashName(), "SHA-1");
        assertEquals(CipherSuite.TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA
                .getHashName(), "SHA-1");
        assertEquals(CipherSuite.TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA
                .getHashName(), "SHA-1");
        assertEquals(CipherSuite.TLS_DHE_RSA_WITH_DES_CBC_SHA
                .getHashName(), "SHA-1");
        assertEquals(CipherSuite.TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA
                .getHashName(), "SHA-1");
        assertEquals(CipherSuite.TLS_DH_anon_EXPORT_WITH_RC4_40_MD5
                .getHashName(), "MD5");
        assertEquals(CipherSuite.TLS_DH_anon_WITH_RC4_128_MD5
                .getHashName(), "MD5");
        assertEquals(CipherSuite.TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA
                .getHashName(), "SHA-1");
        assertEquals(CipherSuite.TLS_DH_anon_WITH_DES_CBC_SHA
                .getHashName(), "SHA-1");
        assertEquals(CipherSuite.TLS_DH_anon_WITH_3DES_EDE_CBC_SHA
                .getHashName(), "SHA-1");
    }

    public void testGetMACLength() {
        assertEquals(CipherSuite.TLS_NULL_WITH_NULL_NULL
                .getMACLength(), 0);
        assertEquals(CipherSuite.TLS_RSA_WITH_NULL_MD5
                .getMACLength(), 16);
        assertEquals(CipherSuite.TLS_RSA_WITH_NULL_SHA
                .getMACLength(), 20);
        assertEquals(CipherSuite.TLS_RSA_EXPORT_WITH_RC4_40_MD5
                .getMACLength(), 16);
        assertEquals(CipherSuite.TLS_RSA_WITH_RC4_128_MD5
                .getMACLength(), 16);
        assertEquals(CipherSuite.TLS_RSA_WITH_RC4_128_SHA
                .getMACLength(), 20);
        assertEquals(CipherSuite.TLS_RSA_EXPORT_WITH_RC2_CBC_40_MD5
                .getMACLength(), 16);
        assertEquals(CipherSuite.TLS_RSA_WITH_IDEA_CBC_SHA
                .getMACLength(), 20);
        assertEquals(CipherSuite.TLS_RSA_EXPORT_WITH_DES40_CBC_SHA
                .getMACLength(), 20);
        assertEquals(CipherSuite.TLS_RSA_WITH_DES_CBC_SHA
                .getMACLength(), 20);
        assertEquals(CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA
                .getMACLength(), 20);
        assertEquals(CipherSuite.TLS_DH_DSS_EXPORT_WITH_DES40_CBC_SHA
                .getMACLength(), 20);
        assertEquals(CipherSuite.TLS_DH_DSS_WITH_DES_CBC_SHA
                .getMACLength(), 20);
        assertEquals(CipherSuite.TLS_DH_DSS_WITH_3DES_EDE_CBC_SHA
                .getMACLength(), 20);
        assertEquals(CipherSuite.TLS_DH_RSA_EXPORT_WITH_DES40_CBC_SHA
                .getMACLength(), 20);
        assertEquals(CipherSuite.TLS_DH_RSA_WITH_DES_CBC_SHA
                .getMACLength(), 20);
        assertEquals(CipherSuite.TLS_DH_RSA_WITH_3DES_EDE_CBC_SHA
                .getMACLength(), 20);
        assertEquals(CipherSuite.TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA
                .getMACLength(), 20);
        assertEquals(CipherSuite.TLS_DHE_DSS_WITH_DES_CBC_SHA
                .getMACLength(), 20);
        assertEquals(CipherSuite.TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA
                .getMACLength(), 20);
        assertEquals(CipherSuite.TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA
                .getMACLength(), 20);
        assertEquals(CipherSuite.TLS_DHE_RSA_WITH_DES_CBC_SHA
                .getMACLength(), 20);
        assertEquals(CipherSuite.TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA
                .getMACLength(), 20);
        assertEquals(CipherSuite.TLS_DH_anon_EXPORT_WITH_RC4_40_MD5
                .getMACLength(), 16);
        assertEquals(CipherSuite.TLS_DH_anon_WITH_RC4_128_MD5
                .getMACLength(), 16);
        assertEquals(CipherSuite.TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA
                .getMACLength(), 20);
        assertEquals(CipherSuite.TLS_DH_anon_WITH_DES_CBC_SHA
                .getMACLength(), 20);
        assertEquals(CipherSuite.TLS_DH_anon_WITH_3DES_EDE_CBC_SHA
                .getMACLength(), 20);
    }

    public void testIsExportable() {
        assertEquals(CipherSuite.TLS_NULL_WITH_NULL_NULL
                .isExportable(), true);
        assertEquals(CipherSuite.TLS_RSA_WITH_NULL_MD5
                .isExportable(), true);
        assertEquals(CipherSuite.TLS_RSA_WITH_NULL_SHA
                .isExportable(), true);
        assertEquals(CipherSuite.TLS_RSA_EXPORT_WITH_RC4_40_MD5
                .isExportable(), true);
        assertEquals(CipherSuite.TLS_RSA_WITH_RC4_128_MD5
                .isExportable(), false);
        assertEquals(CipherSuite.TLS_RSA_WITH_RC4_128_SHA
                .isExportable(), false);
        assertEquals(CipherSuite.TLS_RSA_EXPORT_WITH_RC2_CBC_40_MD5
                .isExportable(), true);
        assertEquals(CipherSuite.TLS_RSA_WITH_IDEA_CBC_SHA
                .isExportable(), false);
        assertEquals(CipherSuite.TLS_RSA_EXPORT_WITH_DES40_CBC_SHA
                .isExportable(), true);
        assertEquals(CipherSuite.TLS_RSA_WITH_DES_CBC_SHA
                .isExportable(), false);
        assertEquals(CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA
                .isExportable(), false);
        assertEquals(CipherSuite.TLS_DH_DSS_EXPORT_WITH_DES40_CBC_SHA
                .isExportable(), true);
        assertEquals(CipherSuite.TLS_DH_DSS_WITH_DES_CBC_SHA
                .isExportable(), false);
        assertEquals(CipherSuite.TLS_DH_DSS_WITH_3DES_EDE_CBC_SHA
                .isExportable(), false);
        assertEquals(CipherSuite.TLS_DH_RSA_EXPORT_WITH_DES40_CBC_SHA
                .isExportable(), true);
        assertEquals(CipherSuite.TLS_DH_RSA_WITH_DES_CBC_SHA
                .isExportable(), false);
        assertEquals(CipherSuite.TLS_DH_RSA_WITH_3DES_EDE_CBC_SHA
                .isExportable(), false);
        assertEquals(CipherSuite.TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA
                .isExportable(), true);
        assertEquals(CipherSuite.TLS_DHE_DSS_WITH_DES_CBC_SHA
                .isExportable(), false);
        assertEquals(CipherSuite.TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA
                .isExportable(), false);
        assertEquals(CipherSuite.TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA
                .isExportable(), true);
        assertEquals(CipherSuite.TLS_DHE_RSA_WITH_DES_CBC_SHA
                .isExportable(), false);
        assertEquals(CipherSuite.TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA
                .isExportable(), false);
        assertEquals(CipherSuite.TLS_DH_anon_EXPORT_WITH_RC4_40_MD5
                .isExportable(), true);
        assertEquals(CipherSuite.TLS_DH_anon_WITH_RC4_128_MD5
                .isExportable(), false);
        assertEquals(CipherSuite.TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA
                .isExportable(), true);
        assertEquals(CipherSuite.TLS_DH_anon_WITH_DES_CBC_SHA
                .isExportable(), false);
        assertEquals(CipherSuite.TLS_DH_anon_WITH_3DES_EDE_CBC_SHA
                .isExportable(), false);
    }

}
