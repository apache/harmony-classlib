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
 * @author Hugo Beilis
 * @author Osvaldo Demo
 * @author Jorge Rafael
 * @version 1.0
 */

package ar.org.fitc.test.whitebox;

import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.EncryptedPrivateKeyInfo;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import junit.framework.TestCase;
import ar.org.fitc.test.util.crypto.Util;

public class TestEncryptedPrivateKeyInfo extends TestCase {
    private static PBEKeySpec pbekey = new PBEKeySpec("password".toCharArray());

    private static Key key;

    private static Cipher pbe;

    private static byte[] encrypted;

    private static EncryptedPrivateKeyInfo epki;

    private static byte[] privateKey;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestEncryptedPrivateKeyInfo.class);
    }

    static {
        try {
            key = SecretKeyFactory.getInstance("pbewithmd5anddes")
                    .generateSecret(pbekey);

            privateKey = new byte[] { 48, -126, 2, 118, 2, 1, 0, 48, 13, 6, 9,
                    42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0, 4, -126, 2, 96,
                    48, -126, 2, 92, 2, 1, 0, 2, -127, -127, 0, -83, 42, -36,
                    97, 78, 109, 14, 50, 97, -87, -16, -32, 43, -109, -90, -2,
                    56, -58, -110, 25, -56, -104, -122, -34, -115, 51, 120,
                    -25, 12, -110, 46, 106, 95, 96, 122, -107, -54, 5, 118,
                    -112, -83, 57, -97, -55, -64, -35, -84, 32, -66, -116, 10,
                    4, 84, 69, 64, 33, -97, -37, 76, 74, 79, -29, -40, -93, 9,
                    -85, 78, -85, -88, -74, -72, 56, 67, -124, 39, 21, -41,
                    -111, -53, 93, 77, 97, 63, 50, -14, -102, -79, 45, -5, 73,
                    64, 20, -52, -54, -25, 35, -15, 11, -24, -49, 24, -86, -6,
                    -119, -44, 59, 6, 97, 2, 60, -115, -77, 42, -46, 112, -21,
                    39, 59, 32, 124, -105, -119, 6, -33, -122, 15, -21, 81, 2,
                    3, 1, 0, 1, 2, -127, -128, 30, -36, -92, -86, 50, -17, -4,
                    -73, -67, -16, 43, -99, -56, 38, 104, 124, -90, 97, -67,
                    104, 26, -8, 20, 17, 85, -40, -10, -109, 55, 10, 86, 123,
                    94, -60, 51, 20, -65, 12, 122, 53, 14, -86, 86, 111, -2,
                    -113, -125, 61, -46, -73, -30, -111, 34, -13, -76, -102,
                    73, -125, 43, -11, -110, -121, 104, -12, 90, 5, 67, 91, 26,
                    -43, 94, -125, 67, 72, 41, 80, 94, 88, -32, 25, -16, 40,
                    31, 2, -81, 76, -101, 121, -35, -88, 123, -95, 117, -1,
                    -100, 109, -37, -118, 21, -81, 12, 68, 58, -1, 57, -6,
                    -101, 29, 84, 43, 27, 65, -48, -93, -76, -15, 123, 63, 3,
                    61, 17, 77, 31, -32, -44, -108, 116, -127, 2, 65, 0, -18,
                    64, 11, 85, -70, 89, 1, -94, -125, -20, 67, 70, -17, -110,
                    97, -85, 51, -118, 108, -67, -49, -38, 59, 82, 17, -71,
                    -74, -109, -2, 86, 61, -50, 4, 67, -26, 14, -51, -31, -68,
                    17, -7, 93, 102, -92, -88, -75, 78, 112, 66, 112, -62, -93,
                    24, 8, -110, 38, -95, 35, -7, 76, 112, -61, -123, 19, 2,
                    65, 0, -70, 17, -117, -111, -106, 85, 113, 79, -110, 116,
                    -117, -103, 111, 40, -45, -28, 62, 67, -25, 67, 6, 92, -95,
                    51, -69, -21, -112, 76, -124, -55, -45, 31, 100, -28, 99,
                    -80, -47, -70, 91, 38, 9, 74, -109, 73, -79, 3, -64, -69,
                    72, 89, 36, 89, 61, 32, 90, -123, 52, -105, -32, 97, -119,
                    -11, -18, -117, 2, 65, 0, -57, 122, -88, 2, -25, 59, -114,
                    -70, -37, 105, -2, 10, 110, -100, 73, 90, 102, -107, 7,
                    -25, 5, 24, -37, -126, 35, -123, 50, -15, 70, -118, 62,
                    -59, 55, -99, 2, 10, 115, 39, 5, -88, -90, -7, 36, -17,
                    -56, -79, -59, 51, 8, -18, 16, -119, 45, -98, 63, -128, 72,
                    123, 46, 72, 106, 65, -54, 49, 2, 64, 122, -22, 46, 53,
                    -67, -122, -79, -12, -85, -67, -37, -98, 63, 37, 76, 92,
                    97, -103, 127, -62, -14, 7, -20, -31, 125, 9, 78, -24,
                    -115, 107, -71, -48, -22, 88, 73, 86, -68, 37, 12, 35, 99,
                    77, -27, 96, 111, -44, 23, -44, 96, 37, -86, -14, 92, -125,
                    -21, -24, 68, -26, -13, 109, -37, -78, 31, -93, 2, 64, 124,
                    11, -102, -99, 16, -58, -36, 34, -27, 127, -96, -53, 51,
                    30, -17, 125, 121, 66, -27, 109, 112, 46, -46, 21, 104,
                    -126, 72, -59, -28, 44, -42, 33, 105, -112, -77, 118, -4,
                    48, 89, 22, -74, -116, 13, 104, -104, -78, 42, 94, -69,
                    -84, -118, 19, -28, -83, 77, -60, -6, 16, -96, -81, 95,
                    -69, 122, 69 };

            pbe = Cipher.getInstance("PBEwithmd5anddes");
            pbe.init(Cipher.ENCRYPT_MODE, key);

            // OBSERVAR que encriptamos la clave privada codificada en ASN.1
            encrypted = pbe.doFinal(privateKey);

            epki = new EncryptedPrivateKeyInfo(pbe.getParameters(), encrypted);
        } catch (Exception e) {
            throw new AssertionError();
        }
    }

    /*
     * Test method for
     * 'javax.crypto.EncryptedPrivateKeyInfo.EncryptedPrivateKeyInfo(byte[])'
     */
    public final void testEncryptedPrivateKeyInfoByteArray() {
        EncryptedPrivateKeyInfo epki;

        // TEST_01
        byte[] encoded = new byte[] { 48, 25, 48, 13, 6, 9, 42, -122, 72, -122,
                -9, 13, 1, 1, 1, 5, 0, 4, 8, 0, 0, 0, 0, 0, 0, 0, 0 };
        try {
            epki = new EncryptedPrivateKeyInfo(encoded);
            assertTrue(epki.getAlgName().equals("RSA"));
            assertTrue(Arrays.equals(epki.getEncryptedData(), new byte[] { 0,
                    0, 0, 0, 0, 0, 0, 0 }));
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }

        // TEST_02 - INVALID TAG (1ER OCTETO)
        encoded = new byte[] { 42, 25, 48, 13, 6, 9, 42, -122, 72, -122, -9,
                13, 1, 1, 1, 5, 0, 4, 8, 0, 0, 0, 0, 0, 0, 0, 0 };
        try {
            epki = new EncryptedPrivateKeyInfo(encoded);
            fail(); // XXX FALLA SUN
        } catch (IOException e) {
        }

        // TEST_03 - INVALID LENGTH (2DO OCTETO)
        encoded = new byte[] { 48, 26, 48, 13, 6, 9, 42, -122, 72, -122, -9,
                13, 1, 1, 1, 5, 0, 4, 8, 0, 0, 0, 0, 0, 0, 0, 0 };
        try {
            epki = new EncryptedPrivateKeyInfo(encoded);
            fail();
        } catch (IOException e) {
        }

        // TEST_04 - INVALID LENGTH (2DO OCTETO)
        encoded = new byte[] { 48, 24, 48, 13, 6, 9, 42, -122, 72, -122, -9,
                13, 1, 1, 1, 5, 0, 4, 8, 0, 0, 0, 0, 0, 0, 0, 0 };
        try {
            epki = new EncryptedPrivateKeyInfo(encoded);
            fail();
        } catch (IOException e) {
        }

        // TEST_05 - INVALID TAG (3ER OCTETO)
        encoded = new byte[] { 48, 25, 45, 13, 6, 9, 42, -122, 72, -122, -9,
                13, 1, 1, 1, 5, 0, 4, 8, 0, 0, 0, 0, 0, 0, 0, 0 };
        try {
            epki = new EncryptedPrivateKeyInfo(encoded);
            fail();
        } catch (IOException e) {
        }

        // TEST_06 - INVALID LENGTH (4TO OCTETO)
        encoded = new byte[] { 48, 25, 48, 14, 6, 9, 42, -122, 72, -122, -9,
                13, 1, 1, 1, 5, 0, 4, 8, 0, 0, 0, 0, 0, 0, 0, 0 };
        try {
            epki = new EncryptedPrivateKeyInfo(encoded);
            fail();
        } catch (IOException e) {
        }

        // TEST_07 - INVALID LENGTH (4TO OCTETO)
        encoded = new byte[] { 48, 25, 48, 12, 6, 9, 42, -122, 72, -122, -9,
                13, 1, 1, 1, 5, 0, 4, 8, 0, 0, 0, 0, 0, 0, 0, 0 };
        try {
            epki = new EncryptedPrivateKeyInfo(encoded);
            fail();
        } catch (IOException e) {
        }

        // TEST_08 - INVALID TAG (5TO OCTETO)
        encoded = new byte[] { 48, 25, 48, 13, 4, 9, 42, -122, 72, -122, -9,
                13, 1, 1, 1, 5, 0, 4, 8, 0, 0, 0, 0, 0, 0, 0, 0 };
        try {
            epki = new EncryptedPrivateKeyInfo(encoded);
            fail();
        } catch (IOException e) {
        }

        // TEST_09 - INVALID LENGTH (6TO OCTETO)
        encoded = new byte[] { 48, 25, 48, 13, 6, 10, 42, -122, 72, -122, -9,
                13, 1, 1, 1, 5, 0, 4, 8, 0, 0, 0, 0, 0, 0, 0, 0 };
        try {
            epki = new EncryptedPrivateKeyInfo(encoded);
            fail();
        } catch (IOException e) {
        }

        // TEST_10 - INVALID LENGTH (6TO OCTETO)
        encoded = new byte[] { 48, 25, 48, 13, 6, 8, 42, -122, 72, -122, -9,
                13, 1, 1, 1, 5, 0, 4, 8, 0, 0, 0, 0, 0, 0, 0, 0 };
        try {
            epki = new EncryptedPrivateKeyInfo(encoded);
            fail();
        } catch (IOException e) {
        }

        // TEST_11 - INVALID TAG (18VO OCTETO)
        encoded = new byte[] { 48, 25, 48, 13, 6, 9, 42, -122, 72, -122, -9,
                13, 1, 1, 1, 5, 0, 3, 8, 0, 0, 0, 0, 0, 0, 0, 0 };
        try {
            epki = new EncryptedPrivateKeyInfo(encoded);
            fail();
        } catch (IOException e) {
        }

        // TEST_12 - INVALID LENGTH (19NO OCTETO)
        encoded = new byte[] { 48, 25, 48, 13, 6, 9, 42, -122, 72, -122, -9,
                13, 1, 1, 1, 5, 0, 4, 9, 0, 0, 0, 0, 0, 0, 0, 0 };
        try {
            epki = new EncryptedPrivateKeyInfo(encoded);
            fail();
        } catch (IOException e) {
        }

        // TEST_13 - INVALID LENGTH (20MO OCTETO)
        encoded = new byte[] { 48, 25, 48, 13, 6, 9, 42, -122, 72, -122, -9,
                13, 1, 1, 1, 5, 0, 4, 7, 0, 0, 0, 0, 0, 0, 0, 0 };
        try {
            epki = new EncryptedPrivateKeyInfo(encoded);
            fail();
        } catch (IOException e) {
        }

        // TEST_14 - INVALID SIZE
        encoded = new byte[] { 48, 25, 48, 13, 6, 9, 42, -122, 72, -122, -9,
                13, 1, 1, 1, 5, 0, 4, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        try {
            epki = new EncryptedPrivateKeyInfo(encoded);
            fail();
        } catch (IOException e) {
        }

        // TEST_15 - INVALID SIZE
        encoded = new byte[] { 48, 25, 48, 13, 6, 9, 42, -122, 72, -122, -9,
                13, 1, 1, 1, 5, 0, 4, 8, 0, 0, 0, 0, 0, 0, 0 };
        try {
            epki = new EncryptedPrivateKeyInfo(encoded);
            fail();
        } catch (IOException e) {
        }

        // TEST_16 - INVALID SIZE
        encoded = new byte[] { 48, 24, 48, 13, 6, 9, 42, -122, 72, -122, -9,
                13, 1, 1, 1, 5, 0, 4, 8, 0, 0, 0, 0, 0, 0, 0 };
        try {
            epki = new EncryptedPrivateKeyInfo(encoded);
            fail();
        } catch (IOException e) {
        }

        // TEST_17 - NEGATIVE VALUES
        encoded = new byte[] { 48, 25, 48, 13, 6, 9, 42, -122, 72, -122, -9,
                13, 1, 1, -1, 5, 0, 4, 8, 0, 0, 0, 0, 0, 0, 0, 0 };
        try {
            epki = new EncryptedPrivateKeyInfo(encoded);
            System.out.println(epki.getAlgName());
            fail();
        } catch (IOException e) {
        }

        // TEST_18 - NEGATIVE VALUES
        encoded = new byte[] { 48, 25, 48, 13, 6, 9, 42, -122, 72, -122, -9,
                13, -1, -1, -1, 5, 0, 4, 8, 0, 0, 0, 0, 0, 0, 0, 0 };
        try {
            epki = new EncryptedPrivateKeyInfo(encoded);
            fail();
        } catch (IOException e) {
        }

        // TEST_19 - NEGATIVE VALUES
        encoded = new byte[] { 48, -25, 48, 13, 6, 9, 42, -122, 72, -122, -9,
                13, 1, 1, 1, 5, 0, 4, 8, 0, 0, 0, 0, 0, 0, 0, 0 };
        try {
            epki = new EncryptedPrivateKeyInfo(encoded);
            fail();
        } catch (IOException e) {
        }

        // TEST_20 - NEGATIVE VALUES
        encoded = new byte[] { 48, 25, 48, 13, 6, 9, 42, -122, 72, -122, -9,
                13, 1, 1, 1, 5, 0, 4, -8, 0, 0, 0, 0, 0, 0, 0, 0 };
        try {
            epki = new EncryptedPrivateKeyInfo(encoded);
            fail();
        } catch (IOException e) {
        }

        // TEST_21 - CERO VALUE
        encoded = new byte[] { 48, 25, 48, 13, 6, 9, 42, -122, 72, -122, -9,
                13, 1, 1, 1, 5, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        try {
            epki = new EncryptedPrivateKeyInfo(encoded);
            fail();
        } catch (IOException e) {
        }

        // TEST_21 - INTEGER MAX_VALUE
        encoded = new byte[] { 48, 27, 48, 15, 6, 9, 42, -21, -12, -122, -72,
                -122, -9, -13, -1, -1, -1, 5, 0, 4, 8, 0, 0, 0, 0, 0, 0, 0, 0 };
        try {
            epki = new EncryptedPrivateKeyInfo(encoded);
            fail();
        } catch (IOException e) {
        }

        // TEST_22 - INTEGER MAX_VALUE EXCEEDED
        encoded = new byte[] { 48, 16, 48, 8, 6, 6, 81, (byte) 0x88, -128,
                -128, -128, 0, 5, 0, 4, 2, 4, 0 };
        try {
            epki = new EncryptedPrivateKeyInfo(encoded);
            fail();
        } catch (IOException e) {
        }

    }

    /*
     * Test method for
     * 'javax.crypto.EncryptedPrivateKeyInfo.EncryptedPrivateKeyInfo(String,
     * byte[])'
     */
    public final void testEncryptedPrivateKeyInfoStringByteArray() {
        /*
         * TEST VALIDOS DE HASHMAPS + CASE INSENSITIVE
         */
        EncryptedPrivateKeyInfo epki;
        try {
            epki = new EncryptedPrivateKeyInfo("RSA", new byte[] { 0, 0, 0, 0,
                    0 });
            assertEquals(epki.getAlgName(), "RSA");
            epki = new EncryptedPrivateKeyInfo("rsa", new byte[] { 0, 0, 0, 0,
                    0 });
            assertEquals(epki.getAlgName(), "RSA");
            epki = new EncryptedPrivateKeyInfo("rSa", new byte[] { 0, 0, 0, 0,
                    0 });
            assertEquals(epki.getAlgName(), "RSA");
            epki = new EncryptedPrivateKeyInfo("1.2.840.113549.1.1.1",
                    new byte[] { 0, 0, 0, 0, 0 });
            assertEquals(epki.getAlgName(), "RSA");
            epki = new EncryptedPrivateKeyInfo("1.3.14.3.2.29", new byte[] { 0,
                    0, 0, 0, 0 });
            assertEquals(epki.getAlgName(), "SHA1withRSA");
            epki = new EncryptedPrivateKeyInfo("SHA-1/DSA", new byte[] { 0, 0,
                    0, 0, 0 });
            assertEquals(epki.getAlgName(), "SHA1withDSA");
            epki = new EncryptedPrivateKeyInfo("SHA1/RSA", new byte[] { 0, 0,
                    0, 0, 0 });
            assertEquals(epki.getAlgName(), "SHA1withRSA");
            epki = new EncryptedPrivateKeyInfo("1.2.3.4.5.6.7.8.9.0",
                    new byte[] { 0, 0, 0, 0, 0 });
            assertEquals("1.2.3.4.5.6.7.8.9.0", epki.getAlgName()); // expected
                                                                    // | actual
            epki = new EncryptedPrivateKeyInfo("1.2.0.4", new byte[] { 0, 0, 0,
                    0, 0 });
            assertEquals("1.2.0.4", epki.getAlgName()); // expected | actual
        } catch (NoSuchAlgorithmException e) {
            fail();
        }
        /*
         * TEST INVALIDOS
         */
        try {
            epki = new EncryptedPrivateKeyInfo("1.2.", new byte[] { 0, 0, 0, 0,
                    0 });
            fail(); // XXX SUN no trata correctamente esta excepción
        } catch (NoSuchAlgorithmException e) {
        }

        try {
            epki = new EncryptedPrivateKeyInfo("3.3.4.5.6.7.8.9.0", new byte[] {
                    0, 0, 0, 0, 0 });
            fail();
        } catch (NoSuchAlgorithmException e) {
        }

        try {
            epki = new EncryptedPrivateKeyInfo("1.3.4.-5.6.7.8.9.0",
                    new byte[] { 0, 0, 0, 0, 0 });
            fail();
        } catch (NoSuchAlgorithmException e) {
        }

        try {
            epki = new EncryptedPrivateKeyInfo("1.3.4.5.6.7.8.-9.0",
                    new byte[] { 0, 0, 0, 0, 0 });
            fail();
        } catch (NoSuchAlgorithmException e) {
        }

        try {
            epki = new EncryptedPrivateKeyInfo("-1.3.4.5.6.7.8.9.0",
                    new byte[] { 0, 0, 0, 0, 0 });
            fail();
        } catch (NoSuchAlgorithmException e) {
        }

        try {
            epki = new EncryptedPrivateKeyInfo("2.-3.4.5.6.7.8.9.0",
                    new byte[] { 0, 0, 0, 0, 0 });
            fail();
        } catch (NoSuchAlgorithmException e) {
        }
        try {
            epki = new EncryptedPrivateKeyInfo("2.40.4.5.6.7.8.9.0",
                    new byte[] { 0, 0, 0, 0, 0 });
            fail();
        } catch (NoSuchAlgorithmException e) {
        }

    }

    /*
     * Test method for
     * 'javax.crypto.EncryptedPrivateKeyInfo.EncryptedPrivateKeyInfo(AlgorithmParameters,
     * byte[])'
     */
    public final void testEncryptedPrivateKeyInfoAlgorithmParametersByteArray001() throws NoSuchAlgorithmException {
        EncryptedPrivateKeyInfo epki2 = new EncryptedPrivateKeyInfo(pbe.getParameters(),encrypted);
        assertTrue(Util.areEqual(encrypted,epki2.getEncryptedData()));
    }
    
    public final void testEncryptedPrivateKeyInfoAlgorithmParametersByteArray002() throws NoSuchAlgorithmException {
        try {
            @SuppressWarnings("unused") EncryptedPrivateKeyInfo epki2 = new EncryptedPrivateKeyInfo((AlgorithmParameters)null,encrypted);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
        } catch (Throwable e) {
            fail("Should raise NullPointerException");
        }
    }
    
    public final void testEncryptedPrivateKeyInfoAlgorithmParametersByteArray003() throws NoSuchAlgorithmException {
        byte[] testbyte = new byte[] {}; //empty encryptedData
        try {
            @SuppressWarnings("unused") EncryptedPrivateKeyInfo epki2 = new EncryptedPrivateKeyInfo(pbe.getParameters(),testbyte);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Should raise IllegalArgumentException");
        }
    }

    public final void testEncryptedPrivateKeyInfoAlgorithmParametersByteArray004() throws NoSuchAlgorithmException, NoSuchPaddingException {
        AlgorithmParameters algorithmParams = AlgorithmParameters.getInstance("Blowfish");
        try {
            @SuppressWarnings("unused") EncryptedPrivateKeyInfo epki2 = new EncryptedPrivateKeyInfo(algorithmParams,encrypted);
            fail("Should raise NoSuchAlgorithmException");
        } catch (NoSuchAlgorithmException e) {
        } catch (Throwable e) {
            fail("Should raise NoSuchAlgorithmException but raised: "+e);
        }
    }

    /*
     * Test method for 'javax.crypto.EncryptedPrivateKeyInfo.getAlgName()'
     */
    public final void testGetAlgName() {
        EncryptedPrivateKeyInfo epki;
        try {
            epki = new EncryptedPrivateKeyInfo("OID.1.2.840.113549.1.1.1",
                    new byte[] { 0, 0, 0, 0 });
            assertEquals("RSA", epki.getAlgName());

            epki = new EncryptedPrivateKeyInfo("OID.1.2.2.2.2.2", new byte[] {
                    0, 0, 0, 0 });
            assertEquals("1.2.2.2.2.2", epki.getAlgName());

        } catch (NoSuchAlgorithmException e) {
            fail();
        }

    }

    /*
     * Test method for 'javax.crypto.EncryptedPrivateKeyInfo.getAlgParameters()'
     */
    public final void testGetAlgParameters() throws IOException {
        assertTrue(Util.areEqual(epki.getAlgParameters().getEncoded(),pbe.getParameters().getEncoded()));
    }

    /*
     * Test method for 'javax.crypto.EncryptedPrivateKeyInfo.getEncryptedData()'
     */
    public final void testGetEncryptedData() {
        assertTrue(Util.areEqual(encrypted, epki.getEncryptedData()));
    }

    /*
     * Test method for 'javax.crypto.EncryptedPrivateKeyInfo.getKeySpec(Cipher)'
     */
    public final void testGetKeySpecCipher001()
            throws NoSuchAlgorithmException, NoSuchPaddingException {
        Cipher c = Cipher.getInstance("PBEWithMD5AndDES");
        try {
            epki.getKeySpec(c);
            fail("Should raise InvalidKeySpecException");
        } catch (InvalidKeySpecException e) {
        } catch (Throwable e) {
            fail("Should raise InvalidKeySpecException");
        }

    }

    public final void testGetKeySpecCipher002()
            throws NoSuchAlgorithmException, NoSuchPaddingException {
        Cipher c = null;
        try {
            epki.getKeySpec(c);
            fail("Should raise NullpointerException");
        } catch (NullPointerException e) {
        } catch (Throwable e) {
            fail("Should raise NullpointerException");
        }

    }

    public final void testGetKeySpecCipher003()
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeySpecException, InvalidKeyException,
            InvalidAlgorithmParameterException {
        Cipher c = Cipher.getInstance("PBEWithMD5AndDES");
        c.init(Cipher.DECRYPT_MODE, key, pbe.getParameters());
        assertTrue(Util.areEqual(epki.getKeySpec(c).getEncoded(),privateKey));
    }

    /*
     * Test method for 'javax.crypto.EncryptedPrivateKeyInfo.getKeySpec(Key)'
     */
    public final void testGetKeySpecKey() {
        // recupera la clave codificada en ASN.1 con la clave simétrica key
        PKCS8EncodedKeySpec peks = null;
        try {
            peks = epki.getKeySpec(key);
        } catch (InvalidKeyException e) {
            fail();
        } catch (NoSuchAlgorithmException e) {
            fail();
        }
        // verificación
        assertTrue(Arrays.equals(peks.getEncoded(), privateKey));

        // NULL POINTER

        try {
            peks = epki.getKeySpec((Key) null);
        } catch (NullPointerException e) {
        } catch (InvalidKeyException e) {
            fail();
        } catch (NoSuchAlgorithmException e) {
            fail();
        }

        // INVALID KEY

        try {
            Key key1 = SecretKeyFactory.getInstance("pbewithmd5anddes")
                    .generateSecret(pbekey);
            peks = epki.getKeySpec(key1);
        } catch (InvalidKeySpecException e) {
            fail();
        } catch (NoSuchAlgorithmException e) {
            fail();
        } catch (InvalidKeyException e) {
        }
    }

    /*
     * Test method for 'javax.crypto.EncryptedPrivateKeyInfo.getKeySpec(Key,
     * String)'
     */
    public final void testGetKeySpecKeyString() {
        // recupera la clave codificada en ASN.1 con la clave simétrica key
        PKCS8EncodedKeySpec peks = null;
        try {
            peks = epki.getKeySpec(key, "SunJCE");
        } catch (InvalidKeyException e) {
            fail();
        } catch (NoSuchAlgorithmException e) {
            fail();
        } catch (NoSuchProviderException e) {
            fail();
        }
        // verificación
        assertTrue(Arrays.equals(peks.getEncoded(), privateKey));

        // NULL POINTER

        try {
            peks = epki.getKeySpec((Key) null, (String) null);
        } catch (NullPointerException e) {
        } catch (InvalidKeyException e) {
            fail();
        } catch (NoSuchAlgorithmException e) {
            fail();
        } catch (NoSuchProviderException e) {
            fail();
        }

        try {
            peks = epki.getKeySpec((Key) null, "SunJCE");
        } catch (NullPointerException e) {
        } catch (InvalidKeyException e) {
            fail();
        } catch (NoSuchAlgorithmException e) {
            fail();
        } catch (NoSuchProviderException e) {
            fail();
        }

        try {
            peks = epki.getKeySpec(key, (String) null);
        } catch (NullPointerException e) {
        } catch (InvalidKeyException e) {
            fail();
        } catch (NoSuchAlgorithmException e) {
            fail();
        } catch (NoSuchProviderException e) {
            fail();
        }

    }

    /*
     * Test method for 'javax.crypto.EncryptedPrivateKeyInfo.getKeySpec(Key,
     * Provider)'
     */
    public final void testGetKeySpecKeyProvider001() {
        // recupera la clave codificada en ASN.1 con la clave simétrica key
        PKCS8EncodedKeySpec peks = null;
        try {
            peks = epki.getKeySpec(key, Security.getProvider("SunJCE"));
        } catch (InvalidKeyException e) {
            fail();
        } catch (NoSuchAlgorithmException e) {
            fail();
        }
        // verificación
        assertTrue(Arrays.equals(peks.getEncoded(), privateKey));
    }
    
    public final void testGetKeySpecKeyProvider002() {
        try {
            @SuppressWarnings("unused") PKCS8EncodedKeySpec peks = epki.getKeySpec(key, (Provider)null);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
        } catch (Throwable e) {
            fail("Should raise NullPointerException");
        }
    }

    /*
     * Test method for 'javax.crypto.EncryptedPrivateKeyInfo.getEncoded()'
     */
    public final void testGetEncoded() throws IOException {
        byte[] encoded = new byte[] { 48, -126, 2, -96, 48, 26, 6, 9, 42, -122,
                72, -122, -9, 13, 1, 5, 3, 48, 13, 4, 8, -45, 50, 48, -113, 60,
                -125, 73, -83, 2, 1, 10, 4, -126, 2, -128, -23, 67, -58, -67,
                -5, -29, -53, -122, 17, -120, 57, -63, -95, -63, -85, -5, -80,
                102, 77, 3, -79, -125, 107, 103, 119, -79, 79, 61, 34, 18, -90,
                124, -103, -53, 36, -112, -102, -50, 70, 116, 73, 52, 48, -52,
                42, -1, 16, 36, -53, -8, -51, -60, -77, 99, -27, 40, -121, -59,
                122, -98, 59, -20, 89, 11, -84, -1, -117, -98, -75, -42, -20,
                4, 55, -82, 76, 110, -20, 6, -29, -30, 65, -8, -126, -115, -61,
                -80, 107, -90, -35, 50, -25, -77, 122, -52, 15, 115, 99, -55,
                44, 112, -96, -100, 38, 12, -18, 62, -34, -64, 52, 38, 96, 23,
                44, -107, 73, 3, 88, -107, -22, 32, 114, 25, 104, 37, -111,
                -61, 90, -29, 107, -100, -57, -101, -60, -76, -86, -67, 25,
                127, 24, 30, -95, -13, -66, -84, 16, -84, -88, 20, -8, 34, -76,
                85, -20, -95, -78, -42, 110, -83, 117, -113, -83, -83, -29,
                -50, 19, -55, 27, -96, 121, -115, 81, -31, -17, -90, 9, 36, 50,
                -95, -73, 84, -119, -126, -57, -7, -92, -54, -57, 118, 12, -84,
                42, 79, 46, 108, -94, 21, 10, -97, -90, 110, -37, 84, 1, 57,
                75, 5, -109, -125, -63, 18, -64, -51, -112, 45, -89, 2, 7, 73,
                -12, 14, 85, 0, 91, -38, 61, 52, 78, 77, 119, -21, 100, -79,
                61, 40, 84, 58, -99, 55, 98, -77, -43, -73, -54, 37, 70, 57,
                38, -108, -28, 116, -56, 10, 32, -124, 123, -24, 26, 6, -30,
                46, 58, 54, 16, -57, 118, 38, 2, -18, -113, 90, 4, -74, 12, 22,
                13, -27, 114, 102, 6, -102, -22, -2, 25, 21, 81, 122, 82, -116,
                -84, 124, 30, -83, -63, 35, -27, -87, -124, 52, -47, -111, 41,
                -42, -17, -117, 99, 118, -120, -12, 1, 94, 34, -55, -82, 67,
                16, 95, -112, -110, -14, 38, -73, -117, -11, 102, -12, 7, 118,
                23, 25, -35, -27, 65, -106, 2, 39, -66, -19, -81, -88, -10,
                107, -93, 109, -6, -20, -75, -101, 39, 73, -72, -14, 2, -69,
                -104, 103, 127, 20, -85, 112, 58, -103, -43, -52, 19, 9, 126,
                71, 41, -67, 34, -35, -44, -125, -88, -16, -123, -20, -115, 46,
                -49, -111, -127, -62, -94, -6, 21, -111, 112, -2, -53, 17, 17,
                -85, 107, -63, -119, -95, 23, 101, 40, -14, 16, 125, -89, 115,
                -43, 95, 90, -33, -106, -8, 2, -21, -16, 39, -23, 115, -27,
                -39, 9, 124, -72, -12, -95, -106, -122, -23, 54, 69, 12, -98,
                -37, -14, -14, -12, 116, -104, 66, -128, -98, -118, -2, -59,
                -13, -66, -9, 111, 33, -78, -77, -99, -15, -41, -58, 77, 72,
                -128, -121, 13, -19, 53, 82, 103, 35, 25, 6, -88, -76, 63, -44,
                87, -53, 114, 9, -23, 5, -31, -22, -115, 45, 63, 10, -49, 35,
                -125, 118, 41, 76, 66, -84, 40, -39, -40, -124, 88, -102, 80,
                -93, 24, 22, 26, -48, -93, 81, 102, 107, 77, 5, 47, -25, -94,
                124, -50, -95, 96, -59, 101, 74, -107, -109, 50, -13, 25, 7,
                33, -31, -104, -113, 75, 43, -65, 77, 99, 48, 108, 64, -108,
                95, -99, -9, -26, 68, 24, 63, -54, -33, -31, 125, 49, 60, 86,
                58, -46, -22, 11, -38, 69, -96, 17, -23, 31, -66, 32, -15,
                -118, -85, 5, 44, -25, 79, -84, -22, -33, -94, 49, 105, -110,
                13, 26, -16, -95, 0, -93, 16, -78, 79, -128, -125, -12, -106,
                77, -42, 112, -60, -52, -27, 111, 126, 41, -104, -19, 30, 103,
                22, 95, 57, -19, -66, 112, -45, -124, 79, -60, -83, -95, 83,
                70, -119, -41, 38, 99, -15, 90, 59, -126, -50, -66, -23, 78,
                35, 35, 89, 61, -33, -93, 63, -94, -122, -23, 50 };

        EncryptedPrivateKeyInfo ep2 = new EncryptedPrivateKeyInfo(encoded);
        assertEquals("PBEWithMD5AndDES",ep2.getAlgName());
        assertTrue(Util.areEqual(encoded, ep2.getEncoded()));
        
    }

}
