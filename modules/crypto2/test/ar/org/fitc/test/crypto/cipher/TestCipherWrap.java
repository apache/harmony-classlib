package ar.org.fitc.test.crypto.cipher;

import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

import ar.org.fitc.test.util.K;


@SuppressWarnings("unused") 
public class TestCipherWrap extends TestCipher {

    SecureRandom sr = new SecureRandom();

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestCipherWrap.class);
    }

    public TestCipherWrap(String name) throws NoSuchAlgorithmException {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();

    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'javax.crypto.Cipher.wrap(Key)'
     */

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a wrap with clave privada. Expected result is a
     * normal termination.
     */

    public final void testWrap001() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPrivateKey());
            byte[] result = { -73, -39, 11, 89, 14, 48, -106, -126, 45, -99,
                    60, 114, 20, 81, 83, 75, 97, -86, -27, -107, -116, 10, 8,
                    -52, -23, 3, 44, 104, -50, -108, 107, -8, -86, -82, -65,
                    11, 91, -86, -15, -61, -10, -64, 30, -54, 97, 68, 49, 86,
                    -107, -4, -24, -9, 7, -46, 104, -82, -128, -32, -83, -23,
                    -84, -4, -88, 29, -16, -106, 53, 92, 40, -49, 115, 35, 68,
                    115, 8, 53, -7, 98, 80, -85, 43, -59, 76, 99, -122, 96,
                    114, 121, -43, 81, 32, 18, 88, -112, -27, -73, -21, 50, 64,
                    123, 21, -38, -24, 48, -68, -86, 79, 127, 123, 22, 78, 2,
                    9, -52, 57, -22, -52, -48, 44, -39, -96, -10, -54, -44,
                    -11, -38, 9, -82, 75, 38, -93, 16, 44, 93, -32, 70, -10,
                    51, -69, -39, -69, 59, -88, -90, -113, -9, -34, -106, 49,
                    -104, -15, 55, -91, -115, -116, 27, -23, 36, -79, -68, -52,
                    71, 124, 69, -15, -125, 16, -48, 120, -13, -80, 28, 116,
                    -8, -119, -61, -59, 3, 73, -49, 65, -64, -101, 101, -106,
                    73, -116, -115, -81, -102, 84, 32, -10, 35, 111, -75, -79,
                    -47, 41, 101, 93, -1, 58, -119, -58, 103, 125, -28, -88,
                    93, -120, -123, -128, -119, 25, 117, -12, 116, 74, 82, 112,
                    -3, 41, -22, 71, 89, 99, 2, -35, 23, -115, 53, -101, -56,
                    71, -126, -39, -29, 110, 76, 83, 97, -52, 93, 43, 67, -96,
                    8, 81, -116, 91, -66, -44, 14, -28, -91, -97, -86, 98, 114,
                    -20, 20, -10, 117, 91, 68, -17, -56, -109, -20, 14, -86,
                    79, 116, 14, -52, 5, 113, 0, 14, -48, -10, -42, 5, -50,
                    -73, -125, 28, -105, -81, -37, 81, -18, 24, 49, 16, -28,
                    46, -45, -35, 105, 21, 72, -62, 120, 56, -36, 39, 25, -66,
                    107, -73, -43, -30, 89, 46, -29, 79, -87, -44, -17, -107,
                    90, 67, 115, -73, 94, 66, 39, -94, -6, -74, 15, -9, -74,
                    -74, 11, 7, -67, 120, -117, -50, 9, -111 };
            for (int index = 0; index < result.length; index++) {
                assertEquals("This is the result expected in index " + index,
                        output[index], result[index]);
            }
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a wrap with clave publica. Expected result is a
     * normal termination.
     */

    public final void testWrap002() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPublicKey());
            byte[] result = { 66, -47, -18, -69, -124, 56, 9, 120, -99, -27,
                    -98, 38, -104, -127, 50, 64, 0, -90, -100, 0, -82, -40,
                    -122, -106, -117, 125, 59, -7, 39, -71, 16, -67, -120, -79,
                    -60, 40, 77, -15, 7, -76, 61, 37, -51, -40, -19, 66, 107,
                    97, 39, 40, -71, 6, 95, 96, 10, 103, 49, -87, -8, -33, 120,
                    107, -18, 4, 103, 98, -81, -56, -114, -120, -5, 33, -56,
                    -47, -41, -72, 14, -27, 33, 6, 82, -31, 81, 95, -100, -102,
                    108, -48 };
            for (int index = 0; index < result.length; index++) {
                assertEquals("This is the result expected in index " + index,
                        output[index], result[index]);
            }
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a wrap with clave simetrica. Expected result is a
     * normal termination.
     */

    public final void testWrap003() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        try {
            output = cipher.wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7,
                    8 }));
            byte[] result = { 119, -89, -42, -68, -11, 121, 98, -71 };
            for (int index = 0; index < result.length; index++) {
                assertEquals("This is the result expected in index " + index,
                        output[index], result[index]);
            }
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a wrap with nula. Expected result is throw
     * RuntimeException.
     */

    public final void testWrap004() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        try {
            output = cipher.wrap(null);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a wrap with clave privada. Expected result is
     * throw IllegalStateException.
     */

    public final void testWrap005() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }));
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPrivateKey());
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a wrap with clave publica. Expected result is
     * throw IllegalStateException.
     */

    public final void testWrap006() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }));
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPublicKey());
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a wrap with clave simetrica. Expected result is
     * throw IllegalStateException.
     */

    public final void testWrap007() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }));
        byte[] output = null;
        try {
            output = cipher.wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7,
                    8 }));
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a wrap with nula. Expected result is throw
     * IllegalStateException.
     */

    public final void testWrap008() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }));
        byte[] output = null;
        try {
            output = cipher.wrap(null);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a wrap with clave privada. Expected result is
     * throw IllegalStateException.
     */

    public final void testWrap009() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }));
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPrivateKey());
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a wrap with clave publica. Expected result is
     * throw IllegalStateException.
     */

    public final void testWrap010() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }));
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPublicKey());
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a wrap with clave simetrica. Expected result is
     * throw IllegalStateException.
     */

    public final void testWrap011() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }));
        byte[] output = null;
        try {
            output = cipher.wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7,
                    8 }));
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a wrap with nula. Expected result is throw
     * IllegalStateException.
     */

    public final void testWrap012() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }));
        byte[] output = null;
        try {
            output = cipher.wrap(null);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a wrap with clave privada. Expected result is
     * throw IllegalStateException.
     */

    public final void testWrap013() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }));
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPrivateKey());
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a wrap with clave publica. Expected result is
     * throw IllegalStateException.
     */

    public final void testWrap014() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }));
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPublicKey());
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a wrap with clave simetrica. Expected result is
     * throw IllegalStateException.
     */

    public final void testWrap015() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }));
        byte[] output = null;
        try {
            output = cipher.wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7,
                    8 }));
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a wrap with nula. Expected result is throw
     * IllegalStateException.
     */

    public final void testWrap016() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }));
        byte[] output = null;
        try {
            output = cipher.wrap(null);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a wrap with clave privada. Expected result is a
     * normal termination.
     */

    public final void testWrap017() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] { 4, 8, -112, -97, 93, 13, -40, -1, 72, -36 });
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), al);
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPrivateKey());
            byte[] result = { 103, -16, -12, 87, -122, -114, 6, 84, 109, 26,
                    -128, 114, 118, 38, -126, -118, -87, 24, 122, 123, -37,
                    -12, -27, 119, 101, 39, -109, -109, 60, -60, 54, 6, -97,
                    -61, -63, 80, -83, 48, -43, -12, 27, -63, -120, -58, -67,
                    66, -54, 120, 113, 45, -56, -18, -63, -123, -70, -37, 87,
                    43, -122, -94, -95, -127, -9, -97, 67, -60, -91, -77, 18,
                    27, -102, 112, -10, -43, 124, -78, 36, -78, -78, -43, -109,
                    58, -88, -43, -2, 29, -121, -56, -124, 126, -102, -16, -72,
                    -127, -123, 79, 55, -123, 68, 55, -19, 13, 117, -4, 98, 36,
                    -64, 13, 104, -116, 78, 68, 122, -124, 86, -100, -1, 114,
                    -105, 29, 102, -113, -92, -90, -107, -47, -85, -34, -96,
                    -108, 90, -50, -114, -110, -80, 27, -52, -99, 102, -22,
                    -95, 119, 121, -48, 105, -88, -113, 30, -2, -66, 11, -118,
                    -15, 50, -61, 87, -6, 74, 65, 82, 113, -15, 74, -18, 120,
                    -8, 54, 121, -54, -124, 95, 32, -126, -34, -64, 39, 118,
                    -88, -24, 83, 16, 71, -43, -15, -47, -47, 90, 126, 16, 47,
                    15, 21, -8, -8, -37, -82, -93, 69, 70, 54, 85, -112, -78,
                    -113, 49, 120, 0, -19, 36, -50, -39, -93, -89, 22, 109, 51,
                    -19, 69, -100, 84, 106, 82, 85, 83, -75, 83, 56, 3, -75,
                    62, -18, -54, -10, -64, 74, -8, -7, 110, -64, -79, 44, -23,
                    -43, -48, -118, 92, -71, 77, -66, -66, 118, -45, 2, -86,
                    114, 16, -38, -24, -53, 81, -34, 34, -55, 5, -27, -6, -97,
                    -91, -67, -76, -2, 93, 55, 45, -46, 91, -4, 100, -43, -6,
                    -78, 18, -82, -116, 32, -4, 122, 39, -47, 66, 43, -80, -67,
                    -29, 68, -37, 104, -63, -27, -67, -29, 73, -121, 118, -67,
                    -107, 30, -121, -12, -119, -3, 61, 91, 106, -119, -7, -98,
                    -126, -61, -108, -55, 52, -58, -41, 111, 27, -106, -62, 9,
                    80, 126, -27, 10, -101, -53, 56, -91, -100, -5, -23, -127,
                    96, -82, 102 };
            for (int index = 0; index < output.length; index++) {
                assertEquals("This is the result expected", output[index],
                        result[index]);
            }
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a wrap with clave publica. Expected result arroje
     * IllegalBlockSizeException.
     */

    public final void testWrap018() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] { 4, 8, 126, 21, -95, 39, -101, -11, -47, -50 });
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), al);
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPublicKey());
            fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a wrap with clave simetrica. Expected result is a
     * normal termination.
     */

    public final void testWrap019() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] { 4, 8, 114, -62, -61, 95, -25, 70, -48, 85 });
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), al);
        byte[] output = null;
        try {
            output = cipher.wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7,
                    8 }));
            byte[] result = { 110, -118, 125, 43, 117, -42, -69, 11 };
            for (int index = 0; index < output.length; index++) {
                assertEquals("This is the result expected", output[index],
                        result[index]);
            }
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a wrap with nula. Expected result is throw
     * RuntimeException.
     */

    public final void testWrap020() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] { 4, 8, 81, 89, 46, 23, 94, -1, -55, 28 });
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), al);
        byte[] output = null;
        try {
            output = cipher.wrap(null);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a wrap with clave privada. Expected result is
     * throw IllegalStateException.
     */

    public final void testWrap021() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] { 4, 8, 101, 65, -62, 67, 59, -13, 84, -44 });
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), al);
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPrivateKey());
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a wrap with clave publica. Expected result is
     * throw IllegalStateException.
     */

    public final void testWrap022() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] { 4, 8, -62, 54, -87, -103, 57, 49, 61, 65 });
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), al);
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPublicKey());
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a wrap with clave simetrica. Expected result is
     * throw IllegalStateException.
     */

    public final void testWrap023() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] { 4, 8, 42, 10, -50, -63, -83, 2, -57, 45 });
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), al);
        byte[] output = null;
        try {
            output = cipher.wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7,
                    8 }));
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a wrap with nula. Expected result is throw
     * IllegalStateException.
     */

    public final void testWrap024() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] { 4, 8, -22, -66, -29, 124, 117, 48, 68, -122 });
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), al);
        byte[] output = null;
        try {
            output = cipher.wrap(null);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a wrap with clave privada. Expected result is
     * throw IllegalStateException.
     */

    public final void testWrap025() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] { 4, 8, 43, -116, -110, -33, -100, 11, 12, 126 });
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), al);
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPrivateKey());
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a wrap with clave publica. Expected result is
     * throw IllegalStateException.
     */

    public final void testWrap026() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] { 4, 8, 36, -26, 47, 101, 83, 114, 13, -107 });
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), al);
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPublicKey());
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a wrap with clave simetrica. Expected result is
     * throw IllegalStateException.
     */

    public final void testWrap027() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] { 4, 8, 80, -121, -64, 124, 22, -122, 32, 29 });
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), al);
        byte[] output = null;
        try {
            output = cipher.wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7,
                    8 }));
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a wrap with nula. Expected result is throw
     * IllegalStateException.
     */

    public final void testWrap028() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] { 4, 8, 34, -87, -64, 53, -21, 50, -3, -2 });
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), al);
        byte[] output = null;
        try {
            output = cipher.wrap(null);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a wrap with clave privada. Expected result is
     * throw IllegalStateException.
     */

    public final void testWrap029() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] { 4, 8, 53, -96, 81, 29, -113, 101, -56, 26 });
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), al);
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPrivateKey());
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a wrap with clave publica. Expected result is
     * throw IllegalStateException.
     */

    public final void testWrap030() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] { 4, 8, -68, -58, 13, 39, 100, -18, -61, -37 });
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), al);
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPublicKey());
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a wrap with clave simetrica. Expected result is
     * throw IllegalStateException.
     */

    public final void testWrap031() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] { 4, 8, 111, -82, 40, 33, 87, 21, -107, -125 });
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), al);
        byte[] output = null;
        try {
            output = cipher.wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7,
                    8 }));
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a wrap with nula. Expected result is throw
     * IllegalStateException.
     */

    public final void testWrap032() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] { 4, 8, -38, -93, 4, 28, -125, 112, -105, -97 });
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), al);
        byte[] output = null;
        try {
            output = cipher.wrap(null);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.WRAP_MODE,
     * make a wrap with clave privada. Expected result is throw
     * RuntimeException.
     */

    public final void testWrap033() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPrivateKey());
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.WRAP_MODE,
     * make a wrap with clave publica. Expected result is throw
     * RuntimeException.
     */

    public final void testWrap034() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPublicKey());
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.WRAP_MODE,
     * make a wrap with clave simetrica. Expected result is a normal
     * termination.
     */

    public final void testWrap035() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        try {
            output = cipher.wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7,
                    8 }));
            byte[] result = { 21, 90, -42, -68, -94, 59, 116, 26, -101, -35,
                    124, -43, -8, -2, 116, 65, -119, 7, 97, 31, -106, 115, -60,
                    117, 39, 127, -4, 51, -21, -50, -54, -125, -20, -17, 92,
                    -16, 30, -96, -51, -6, 117, -122, -50, 76, 58, 117, -13,
                    83, -33, 82, 21, 109, 98, 24, -75, 38, 94, -91, 16, -50,
                    -103, -61, 42, 107 };
            for (int index = 0; index < output.length; index++) {
                assertEquals("This is the result expected", output[index],
                        result[index]);
            }
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.WRAP_MODE,
     * make a wrap with nula. Expected result is throw RuntimeException.
     */

    public final void testWrap036() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        try {
            output = cipher.wrap(null);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.WRAP_MODE,
     * make a wrap with clave privada. Expected result is throw
     * RuntimeException.
     */

    public final void testWrap037() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPrivateKey());
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.WRAP_MODE,
     * make a wrap with clave publica. Expected result is throw
     * RuntimeException.
     */

    public final void testWrap038() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPublicKey());
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.WRAP_MODE,
     * make a wrap with clave simetrica. Expected result is a normal
     * termination.
     */

    public final void testWrap039() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        try {
            output = cipher.wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7,
                    8 }));
            byte[] result = { 53, 125, -19, 112, -63, -121, 16, -72, 45, -28,
                    38, 3, 88, -13, 62, -126, -40, 50, -82, -122, -7, 29, -106,
                    7, 61, 33, 86, 16, -24, 48, 102, -3, -51, -54, 33, 113,
                    -123, -52, 22, -14, 31, -127, 18, -100, 78, -91, 106, -67,
                    -116, 39, 14, 104, 5, 101, -3, 88, -36, 13, 9, 81, 60, 0,
                    -25, 109 };
            for (int index = 0; index < output.length; index++) {
                assertEquals("This is the result expected", output[index],
                        result[index]);
            }
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.WRAP_MODE,
     * make a wrap with nula. Expected result is throw RuntimeException.
     */

    public final void testWrap040() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        try {
            output = cipher.wrap(null);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.UNWRAP_MODE,
     * make a wrap with clave privada. Expected result is throw
     * IllegalStateException.
     */

    public final void testWrap041() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPrivateKey());
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.UNWRAP_MODE,
     * make a wrap with clave publica. Expected result is throw
     * IllegalStateException.
     */

    public final void testWrap042() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPublicKey());
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.UNWRAP_MODE,
     * make a wrap with clave simetrica. Expected result is throw
     * IllegalStateException.
     */

    public final void testWrap043() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        try {
            output = cipher.wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7,
                    8 }));
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.UNWRAP_MODE,
     * make a wrap with nula. Expected result is throw IllegalStateException.
     */

    public final void testWrap044() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        try {
            output = cipher.wrap(null);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.UNWRAP_MODE,
     * make a wrap with clave privada. Expected result is throw
     * IllegalStateException.
     */

    public final void testWrap045() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPrivateKey());
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.UNWRAP_MODE,
     * make a wrap with clave publica. Expected result is throw
     * IllegalStateException.
     */

    public final void testWrap046() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPublicKey());
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.UNWRAP_MODE,
     * make a wrap with clave simetrica. Expected result is throw
     * IllegalStateException.
     */

    public final void testWrap047() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        try {
            output = cipher.wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7,
                    8 }));
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.UNWRAP_MODE,
     * make a wrap with nula. Expected result is throw IllegalStateException.
     */

    public final void testWrap048() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        try {
            output = cipher.wrap(null);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.ENCRYPT_MODE,
     * make a wrap with clave privada. Expected result is throw
     * IllegalStateException.
     */

    public final void testWrap049() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPrivateKey());
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.ENCRYPT_MODE,
     * make a wrap with clave publica. Expected result is throw
     * IllegalStateException.
     */

    public final void testWrap050() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPublicKey());
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.ENCRYPT_MODE,
     * make a wrap with clave simetrica. Expected result is throw
     * IllegalStateException.
     */

    public final void testWrap051() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        try {
            output = cipher.wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7,
                    8 }));
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.ENCRYPT_MODE,
     * make a wrap with nula. Expected result is throw IllegalStateException.
     */

    public final void testWrap052() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        try {
            output = cipher.wrap(null);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.ENCRYPT_MODE,
     * make a wrap with clave privada. Expected result is throw
     * IllegalStateException.
     */

    public final void testWrap053() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey());
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPrivateKey());
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.ENCRYPT_MODE,
     * make a wrap with clave publica. Expected result is throw
     * IllegalStateException.
     */

    public final void testWrap054() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey());
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPublicKey());
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.ENCRYPT_MODE,
     * make a wrap with clave simetrica. Expected result is throw
     * IllegalStateException.
     */

    public final void testWrap055() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey());
        byte[] output = null;
        try {
            output = cipher.wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7,
                    8 }));
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.ENCRYPT_MODE,
     * make a wrap with nula. Expected result is throw IllegalStateException.
     */

    public final void testWrap056() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey());
        byte[] output = null;
        try {
            output = cipher.wrap(null);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.DECRYPT_MODE,
     * make a wrap with clave privada. Expected result is throw
     * IllegalStateException.
     */

    public final void testWrap057() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPrivateKey());
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.DECRYPT_MODE,
     * make a wrap with clave publica. Expected result is throw
     * IllegalStateException.
     */

    public final void testWrap058() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPublicKey());
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.DECRYPT_MODE,
     * make a wrap with clave simetrica. Expected result is throw
     * IllegalStateException.
     */

    public final void testWrap059() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        try {
            output = cipher.wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7,
                    8 }));
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.DECRYPT_MODE,
     * make a wrap with nula. Expected result is throw IllegalStateException.
     */

    public final void testWrap060() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        try {
            output = cipher.wrap(null);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.DECRYPT_MODE,
     * make a wrap with clave privada. Expected result is throw
     * IllegalStateException.
     */

    public final void testWrap061() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey());
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPrivateKey());
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.DECRYPT_MODE,
     * make a wrap with clave publica. Expected result is throw
     * IllegalStateException.
     */

    public final void testWrap062() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey());
        byte[] output = null;
        try {
            output = cipher.wrap(K.getRSAPublicKey());
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.DECRYPT_MODE,
     * make a wrap with clave simetrica. Expected result is throw
     * IllegalStateException.
     */

    public final void testWrap063() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey());
        byte[] output = null;
        try {
            output = cipher.wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7,
                    8 }));
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA and mode is Cipher.DECRYPT_MODE,
     * make a wrap with nula. Expected result is throw IllegalStateException.
     */

    public final void testWrap064() throws Exception {
        cipher = Cipher.getInstance("RSA", provider);
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey());
        byte[] output = null;
        try {
            output = cipher.wrap(null);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

}
