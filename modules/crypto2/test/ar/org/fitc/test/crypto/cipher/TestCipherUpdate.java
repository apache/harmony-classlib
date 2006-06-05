package ar.org.fitc.test.crypto.cipher;

import java.security.AlgorithmParameters;

import javax.crypto.Cipher;
import javax.crypto.ShortBufferException;

import ar.org.fitc.test.util.K;

@SuppressWarnings("unused")
public class TestCipherUpdate extends TestCipher {

    public TestCipherUpdate(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'javax.crypto.Cipher.update(byte[])'
     */
    public final void testUpdateByteArray001() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(input);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArray002() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 109, -126, -97, 5, 56, -34, -19, 120});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(null);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArray003() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 109, -126, -97, 5, 56, -34, -19, 120});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(new byte[0]);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArray004() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 109, -126, -97, 5, 56, -34, -19, 120});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(input);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArray005() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 109, -126, -97, 5, 56, -34, -19, 120});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(null);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArray006() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 109, -126, -97, 5, 56, -34, -19, 120});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(new byte[0]);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    public final void testUpdateByteArray008() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 109, -126, -97, 5, 56, -34, -19, 120});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(null);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArray009() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 109, -126, -97, 5, 56, -34, -19, 120});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0]);
            assertNull("No input no output",output);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    public final void testUpdateByteArray011() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 109, -126, -97, 5, 56, -34, -19, 120});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(null);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArray012() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 109, -126, -97, 5, 56, -34, -19, 120});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0]);
            assertNull("No input no output",output);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArray013() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            cipher.update(input);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArray014() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            cipher.update(null);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArray015() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            cipher.update(new byte[0]);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /*
     * Test method for 'javax.crypto.Cipher.update(byte[], int, int)'
     */
    public final void testUpdateByteArrayIntInt001() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(input, -1, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt002() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(input, -1, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt003() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(input, -1, 5);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt004() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(input, -1, 8);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt005() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(input, 0, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt006() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(input, 0, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt007() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(input, 0, 5);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt008() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(input, 0, 8);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt009() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(input, 15, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt010() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(input, 15, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt011() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(input, 15, 5);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt012() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(input, 15, 8);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt013() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(null, -1, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt014() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(null, -1, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt015() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(null, -1, 5);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt016() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(null, -1, 8);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt017() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(null, 0, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt018() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(null, 0, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt019() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(null, 0, 5);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt020() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(null, 0, 8);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt021() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(null, 15, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt022() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(null, 15, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt023() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(null, 15, 5);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt024() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(null, 15, 8);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt025() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(new byte[0], -1, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt026() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(new byte[0], -1, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt027() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(new byte[0], -1, 5);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt028() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(new byte[0], -1, 8);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt029() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(new byte[0], 0, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt030() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            cipher.update(new byte[0], 0, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt031() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 0, 5);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt032() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 0, 8);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt033() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 15, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt034() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 15, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt035() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 15, 5);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt036() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 15, 8);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt037() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, -1, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt038() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, -1, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt039() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, -1, 5);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt040() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, -1, 8);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt041() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, 0, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt042() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, 0, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt043() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, 0, 5);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt044() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, 0, 8);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt045() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, 15, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt046() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, 15, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt047() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, 15, 5);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt048() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, 15, 8);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt049() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, -1, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt050() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, -1, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt051() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, -1, 5);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt052() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, -1, 8);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt053() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, 0, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt054() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, 0, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt055() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, 0, 5);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt056() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, 0, 8);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt057() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, 15, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt058() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, 15, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt059() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, 15, 5);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt060() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, 15, 8);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt061() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], -1, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt062() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], -1, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt063() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], -1, 5);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt064() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], -1, 8);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt065() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 0, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt066() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 0, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt067() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 0, 5);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt068() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 0, 8);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt069() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 15, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt070() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 15, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt071() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 15, 5);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt072() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 15, 8);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt073() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, -1, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt074() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, -1, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt075() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, -1, 5);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt076() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, -1, 8);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt077() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, 0, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt078() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, 0, 0);
            assertNull("No input no output",output);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt079() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, 0, 5);
            assertNull("No input no output",output);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    public final void testUpdateByteArrayIntInt081() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, 15, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt082() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, 15, 0);
            assertNull("No input no output",output);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt083() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, 15, 5);
            assertNull("No input no output",output);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt084() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, 15, 8);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt085() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, -1, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt086() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, -1, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt087() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, -1, 5);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt088() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, -1, 8);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt089() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, 0, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt090() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, 0, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt091() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, 0, 5);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt092() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, 0, 8);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt093() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, 15, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt094() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, 15, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt095() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, 15, 5);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt096() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, 15, 8);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt097() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], -1, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt098() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], -1, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt099() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], -1, 5);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt100() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], -1, 8);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt101() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 0, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt102() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 0, 0);
            assertNull("No input no output",output);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt103() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 0, 5);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt104() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 0, 8);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt105() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 15, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt106() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 15, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt107() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 15, 5);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt108() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 15, 8);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt109() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, -1, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt110() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, -1, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt111() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, -1, 5);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt112() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, -1, 8);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt113() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, 0, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt114() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, 0, 0);
            assertNull("No input no output",output);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt115() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, 0, 5);
            assertNull("No input no output",output);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt116() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, 0, 8);
            assertEquals("This is the result expected", -115, output[0]);
            assertEquals("This is the result expected", -66, output[1]);
            assertEquals("This is the result expected", -22, output[2]);
            assertEquals("This is the result expected", 19, output[3]);
            assertEquals("This is the result expected", -23, output[4]);
            assertEquals("This is the result expected", 75, output[5]);
            assertEquals("This is the result expected", 8, output[6]);
            assertEquals("This is the result expected", -17, output[7]);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt117() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, 15, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt118() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, 15, 0);
            assertNull("No input no output",output);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt119() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, 15, 5);
            assertNull("No input no output",output);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt120() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(input, 15, 8);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt121() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, -1, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt122() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, -1, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt123() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, -1, 5);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt124() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, -1, 8);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt125() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, 0, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt126() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, 0, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt127() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, 0, 5);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt128() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, 0, 8);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt129() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, 15, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt130() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, 15, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt131() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, 15, 5);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt132() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(null, 15, 8);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt133() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], -1, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt134() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], -1, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt135() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], -1, 5);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt136() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], -1, 8);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt137() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 0, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt138() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 0, 0);
            assertNull("No input no output",output);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt139() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 0, 5);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt140() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 0, 8);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt141() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 15, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt142() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 15, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt143() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 15, 5);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt144() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 125, 76, -75, 106, 69, 87, 109, -40});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            byte[] output = cipher.update(new byte[0], 15, 8);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt145() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(input, -1, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt146() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(input, -1, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt147() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(input, -1, 5);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt148() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(input, -1, 8);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt149() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(input, 0, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt150() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(input, 0, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt151() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(input, 0, 5);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt152() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(input, 0, 8);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt153() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(input, 15, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt154() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(input, 15, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt155() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(input, 15, 5);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt156() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(input, 15, 8);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt157() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(null, -1, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt158() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(null, -1, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt159() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(null, -1, 5);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt160() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(null, -1, 8);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt161() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(null, 0, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt162() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(null, 0, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt163() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(null, 0, 5);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt164() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(null, 0, 8);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt165() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(null, 15, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt166() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(null, 15, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt167() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(null, 15, 5);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt168() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(null, 15, 8);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt169() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(new byte[0], -1, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt170() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(new byte[0], -1, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt171() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(new byte[0], -1, 5);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt172() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(new byte[0], -1, 8);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt173() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(new byte[0], 0, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt174() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(new byte[0], 0, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt175() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(new byte[0], 0, 5);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt176() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(new byte[0], 0, 8);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt177() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(new byte[0], 15, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt178() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(new byte[0], 15, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt179() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(new byte[0], 15, 5);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntInt180() {
        try {
            cipher = Cipher.getInstance(transformation,provider);
            byte[] output = cipher.update(new byte[0], 15, 8);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }


    /*
     * Test method for 'javax.crypto.Cipher.update(byte[], int, int, byte[])'
     */
    public final void testUpdateByteArrayIntIntByteArray001() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray002() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray003() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray004() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray005() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray006() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray007() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray008() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray009() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray010() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray011() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray012() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray013() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray014() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray015() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray016() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray017() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray018() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray019() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray020() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray021() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray022() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray023() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray024() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray025() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray026() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray027() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray028() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray029() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray030() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray031() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray032() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray033() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray034() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray035() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray036() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray037() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray038() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray039() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray040() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray041() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray042() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray043() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray044() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray045() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray046() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray047() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray048() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray049() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray050() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray051() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray052() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray053() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray054() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray055() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray056() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray057() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray058() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray059() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray060() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray061() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray062() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray063() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray064() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray065() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray066() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray067() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray068() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray069() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray070() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray071() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray072() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray073() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray074() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray075() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray076() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray077() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray078() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray079() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray080() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray081() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray082() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray083() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray084() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray085() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray086() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray087() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray088() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray089() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray090() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray091() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray092() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray093() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray094() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray095() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray096() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray097() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray098() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray099() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray100() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray101() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray102() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray103() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray104() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray105() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray106() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray107() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray108() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray109() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray110() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray111() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray112() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray113() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray114() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray115() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray116() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray117() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray118() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray119() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray120() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray121() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray122() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray123() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray124() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray125() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray126() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray127() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray128() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray129() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray130() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray131() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray132() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray133() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray134() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray135() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray136() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray137() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray138() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray139() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray140() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray141() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray142() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray143() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray144() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray145() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray146() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray147() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray148() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray149() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray150() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray151() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray152() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray153() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray154() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray155() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray156() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray157() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray158() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray159() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray160() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray161() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray162() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray163() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray164() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray165() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray166() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray167() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray168() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray169() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray170() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray171() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray172() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray173() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray174() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray175() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray176() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray177() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray178() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray179() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray180() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray181() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray182() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray183() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray184() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray185() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray186() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray187() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray188() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray189() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray190() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray191() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray192() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray193() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray194() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray195() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray196() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray197() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray198() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray199() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray200() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray201() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray202() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray203() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray204() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray205() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray206() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray207() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray208() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray209() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray210() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray211() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray212() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray213() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray214() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray215() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray216() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray217() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray218() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray219() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray220() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray221() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray222() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray223() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray224() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray225() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray226() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray227() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray228() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray229() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray230() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray231() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray232() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 0, output);
            assertTrue("Mustn't read more than asked for", read <= 0);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray233() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 0, output);
            assertTrue("Mustn't read more than asked for", read <= 0);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray234() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 0, output);
            assertTrue("Mustn't read more than asked for", read <= 0);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray235() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 5, output);
            assertTrue("Mustn't read more than asked for", read <= 5);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray236() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 5, output);
            assertTrue("Mustn't read more than asked for", read <= 5);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray237() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 5, output);
            assertTrue("Mustn't read more than asked for", read <= 5);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray238() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray239() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 8, output);
            fail("must throw ShortBufferException");
        } catch (ShortBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray240() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 8, output);
            assertTrue("Mustn't read more than asked for", read <= 8);
            assertEquals("This is the result expected", 60, output[0]);
            assertEquals("This is the result expected", 120, output[1]);
            assertEquals("This is the result expected", 66, output[2]);
            assertEquals("This is the result expected", 13, output[3]);
            assertEquals("This is the result expected", 78, output[4]);
            assertEquals("This is the result expected", -62, output[5]);
            assertEquals("This is the result expected", 43, output[6]);
            assertEquals("This is the result expected", -46, output[7]);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray241() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray242() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray243() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray244() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 0, output);
            assertTrue("Mustn't read more than asked for", read <= 0);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray245() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 0, output);
            assertTrue("Mustn't read more than asked for", read <= 0);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray246() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 0, output);
            assertTrue("Mustn't read more than asked for", read <= 0);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray247() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output);
            assertTrue("Mustn't read more than asked for", read <= 5);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray248() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output);
            assertTrue("Mustn't read more than asked for", read <= 5);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray249() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output);
            assertTrue("Mustn't read more than asked for", read <= 5);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray250() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray251() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray252() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray253() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray254() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray255() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray256() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray257() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray258() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray259() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray260() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray261() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray262() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray263() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray264() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray265() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray266() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray267() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray268() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray269() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray270() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray271() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray272() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray273() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray274() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray275() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray276() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray277() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray278() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray279() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray280() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray281() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray282() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray283() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray284() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray285() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray286() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray287() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray288() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray289() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray290() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray291() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray292() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray293() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray294() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray295() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray296() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray297() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray298() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray299() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray300() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray301() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray302() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray303() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray304() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 0, output);
            assertTrue("Mustn't read more than asked for", read <= 0);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray305() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 0, output);
            assertTrue("Mustn't read more than asked for", read <= 0);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray306() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 0, output);
            assertTrue("Mustn't read more than asked for", read <= 0);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray307() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray308() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray309() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray310() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray311() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray312() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray313() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray314() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray315() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray316() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray317() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray318() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray319() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray320() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray321() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray322() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray323() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray324() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray325() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray326() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray327() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray328() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray329() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray330() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray331() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray332() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray333() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray334() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray335() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray336() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray337() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray338() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray339() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray340() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 0, output);
            assertTrue("Mustn't read more than asked for", read <= 0);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray341() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 0, output);
            assertTrue("Mustn't read more than asked for", read <= 0);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray342() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 0, output);
            assertTrue("Mustn't read more than asked for", read <= 0);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray343() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 5, output);
            assertTrue("Mustn't read more than asked for", read <= 5);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray344() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 5, output);
            assertTrue("Mustn't read more than asked for", read <= 5);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray345() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 5, output);
            assertTrue("Mustn't read more than asked for", read <= 5);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray346() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray347() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 0, 8, output);
            fail("must throw ShortBufferException");
        } catch (ShortBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    public final void testUpdateByteArrayIntIntByteArray349() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray350() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray351() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray352() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 0, output);
            assertTrue("Mustn't read more than asked for", read <= 0);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray353() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 0, output);
            assertTrue("Mustn't read more than asked for", read <= 0);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray354() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 0, output);
            assertTrue("Mustn't read more than asked for", read <= 0);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray355() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output);
            assertTrue("Mustn't read more than asked for", read <= 5);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray356() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output);
            assertTrue("Mustn't read more than asked for", read <= 5);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray357() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output);
            assertTrue("Mustn't read more than asked for", read <= 5);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray358() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray359() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray360() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray361() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray362() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray363() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray364() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray365() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray366() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray367() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray368() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray369() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray370() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray371() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray372() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray373() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray374() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray375() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray376() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray377() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray378() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray379() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray380() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray381() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray382() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray383() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray384() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 0, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray385() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray386() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray387() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray388() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray389() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray390() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray391() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray392() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray393() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray394() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray395() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray396() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray397() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray398() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray399() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray400() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray401() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray402() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray403() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray404() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray405() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray406() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray407() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray408() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray409() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray410() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray411() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray412() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 0, output);
            assertTrue("Mustn't read more than asked for", read <= 0);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray413() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 0, output);
            assertTrue("Mustn't read more than asked for", read <= 0);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray414() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 0, output);
            assertTrue("Mustn't read more than asked for", read <= 0);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray415() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray416() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray417() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray418() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray419() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray420() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 0, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray421() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray422() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray423() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray424() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray425() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray426() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 0, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray427() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray428() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray429() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray430() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray431() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray432() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, 36, 2, -35, -63, -100, -38, -113, 15});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray433() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray434() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray435() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray436() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray437() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray438() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray439() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray440() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray441() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray442() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray443() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray444() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray445() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 0, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray446() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 0, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray447() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 0, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray448() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 0, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray449() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 0, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray450() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 0, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray451() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 0, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray452() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 0, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray453() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 0, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray454() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 0, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray455() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 0, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray456() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 0, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray457() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray458() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray459() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray460() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray461() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray462() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray463() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray464() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray465() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray466() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray467() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray468() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray469() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray470() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray471() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray472() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray473() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray474() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray475() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray476() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray477() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray478() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray479() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray480() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray481() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 0, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray482() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 0, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray483() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 0, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray484() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 0, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray485() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 0, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray486() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 0, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray487() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 0, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray488() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 0, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray489() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 0, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray490() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 0, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray491() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 0, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray492() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 0, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray493() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray494() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray495() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray496() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray497() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray498() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray499() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray500() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray501() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray502() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray503() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray504() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray505() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray506() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray507() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray508() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray509() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray510() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray511() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray512() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray513() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray514() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray515() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray516() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray517() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 0, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray518() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 0, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray519() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 0, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray520() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 0, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray521() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 0, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray522() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 0, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray523() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 0, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray524() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 0, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray525() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 0, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray526() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 0, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray527() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 0, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray528() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 0, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray529() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray530() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray531() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, -1, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray532() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray533() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray534() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, 0, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray535() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray536() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray537() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, 5, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray538() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray539() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArray540() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, 8, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

}
