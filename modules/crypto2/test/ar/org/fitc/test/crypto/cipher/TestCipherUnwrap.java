package ar.org.fitc.test.crypto.cipher;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;

import ar.org.fitc.test.util.K;

@SuppressWarnings("unused")
public class TestCipherUnwrap extends TestCipher {

    SecureRandom sr = new SecureRandom();

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestCipherUnwrap.class);
    }

    public TestCipherUnwrap(String name) throws NoSuchAlgorithmException {
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
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm "DES"
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap001() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm "RSA"
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap002() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm null
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap003() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm "" and
     * key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap004() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm "DES"
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap005() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm "RSA"
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap006() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm null
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap007() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm "" and
     * key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap008() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm "DES"
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap009() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm "RSA"
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap010() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm null
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap011() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm "" and
     * key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap012() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm "DES"
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap013() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm "RSA"
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap014() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm null
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap015() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm "" and
     * key type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap016() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave publica, key algorithm "DES"
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap017() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave publica, key algorithm "RSA"
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap018() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave publica, key algorithm null
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap019() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave publica, key algorithm "" and
     * key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap020() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave publica, key algorithm "DES"
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap021() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave publica, key algorithm "RSA"
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap022() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave publica, key algorithm null
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap023() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave publica, key algorithm "" and
     * key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap024() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave publica, key algorithm "DES"
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap025() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave publica, key algorithm "RSA"
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap026() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave publica, key algorithm null
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap027() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave publica, key algorithm "" and
     * key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap028() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave publica, key algorithm "DES"
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap029() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave publica, key algorithm "RSA"
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap030() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave publica, key algorithm null
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap031() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave publica, key algorithm "" and
     * key type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap032() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "DES"
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap033() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "RSA"
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap034() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm null
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap035() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap036() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "DES"
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap037() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "RSA"
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap038() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm null
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap039() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap040() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "DES"
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap041() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "RSA"
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap042() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm null
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap043() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap044() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "DES"
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap045() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "RSA"
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap046() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm null
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap047() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap048() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap049() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap050() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap051() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "" and key type
     * PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap052() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap053() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap054() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap055() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "" and key type
     * PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap056() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap057() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap058() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap059() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "" and key type
     * SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap060() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap061() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap062() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap063() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "" and key type
     * tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap064() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm "DES"
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap065() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm "RSA"
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap066() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm null
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap067() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm "" and
     * key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap068() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm "DES"
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap069() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm "RSA"
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap070() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm null
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap071() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm "" and
     * key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap072() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm "DES"
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap073() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm "RSA"
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap074() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm null
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap075() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm "" and
     * key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap076() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm "DES"
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap077() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm "RSA"
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap078() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm null
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap079() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave privada, key algorithm "" and
     * key type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap080() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "DES"
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap081() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "RSA"
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap082() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm null
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap083() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap084() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "DES"
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap085() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "RSA"
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap086() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm null
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap087() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap088() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "DES"
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap089() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "RSA"
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap090() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm null
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap091() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap092() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "DES"
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap093() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "RSA"
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap094() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm null
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap095() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap096() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap097() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap098() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap099() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "" and key type
     * PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap100() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap101() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap102() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap103() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "" and key type
     * PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap104() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap105() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap106() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap107() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "" and key type
     * SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap108() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap109() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap110() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap111() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "" and key type
     * tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap112() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "DES"
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap113() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "RSA"
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap114() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm null
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap115() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap116() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "DES"
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap117() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "RSA"
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap118() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm null
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap119() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap120() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "DES"
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap121() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "RSA"
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap122() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm null
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap123() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap124() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "DES"
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap125() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "RSA"
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap126() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm null
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap127() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap128() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap129() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap130() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap131() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "" and key type
     * PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap132() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap133() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap134() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap135() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "" and key type
     * PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap136() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap137() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap138() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap139() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "" and key type
     * SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap140() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap141() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap142() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap143() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "" and key type
     * tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap144() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "DES"
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap145() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "RSA"
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap146() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm null
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap147() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap148() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "DES"
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap149() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "RSA"
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap150() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm null
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap151() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap152() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "DES"
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap153() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "RSA"
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap154() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm null
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap155() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap156() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "DES"
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap157() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm "RSA"
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap158() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm null
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap159() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap160() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap161() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap162() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap163() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "" and key type
     * PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap164() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap165() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap166() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap167() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "" and key type
     * PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap168() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap169() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap170() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap171() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "" and key type
     * SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap172() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap173() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap174() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap175() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.WRAP_MODE, make a unwrap with nula, key algorithm "" and key type
     * tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap176() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave privada, key algorithm "RSA"
     * and key type PRIVATE_KEY. Expected result is a normal termination.
     */

    public final void testUnwrap177() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            assertEquals("Must return a equal key", K.getRSAPrivateKey(),
                    newkey);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave privada, key algorithm null
     * and key type PRIVATE_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap178() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave privada, key algorithm ""
     * and key type PRIVATE_KEY. Expected result is a normal termination.
     */

    public final void testUnwrap179() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            assertEquals("Must return a equal key", K.getRSAPrivateKey(),
                    newkey);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave privada, key algorithm null
     * and key type PUBLIC_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap180() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave privada, key algorithm "DES"
     * and key type SECRET_KEY. Expected result is a normal termination.
     */

    public final void testUnwrap181() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            assertFalse("Must return a no equal key, bad parametres used",
                    newkey.equals(K.getRSAPrivateKey()));
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave privada, key algorithm "RSA"
     * and key type SECRET_KEY. Expected result is a normal termination.
     */

    public final void testUnwrap182() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            assertFalse("Must return a no equal key, bad parametres used",
                    newkey.equals(K.getRSAPrivateKey()));
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave privada, key algorithm null
     * and key type SECRET_KEY. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap183() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave privada, key algorithm ""
     * and key type SECRET_KEY. Expected result is a normal termination.
     */

    public final void testUnwrap184() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            assertFalse("Must return a no equal key, bad parametres used",
                    newkey.equals(K.getRSAPrivateKey()));
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave privada, key algorithm "DES"
     * and key type tipo invalido. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap185() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave privada, key algorithm "RSA"
     * and key type tipo invalido. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap186() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave privada, key algorithm null
     * and key type tipo invalido. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap187() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave privada, key algorithm ""
     * and key type tipo invalido. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap188() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave publica, key algorithm null
     * and key type PRIVATE_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap189() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave publica, key algorithm "RSA"
     * and key type PUBLIC_KEY. Expected result is a normal termination.
     */

    public final void testUnwrap190() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            assertEquals("Must return a equal key", K.getRSAPublicKey(), newkey);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave publica, key algorithm null
     * and key type PUBLIC_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap191() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave publica, key algorithm "DES"
     * and key type SECRET_KEY. Expected result is a normal termination.
     */

    public final void testUnwrap192() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            assertFalse("Must return a no equal key, bad parametres used",
                    newkey.equals(K.getRSAPublicKey()));
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave publica, key algorithm "RSA"
     * and key type SECRET_KEY. Expected result is a normal termination.
     */

    public final void testUnwrap193() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            assertFalse("Must return a no equal key, bad parametres used",
                    newkey.equals(K.getRSAPublicKey()));
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave publica, key algorithm null
     * and key type SECRET_KEY. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap194() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave publica, key algorithm ""
     * and key type SECRET_KEY. Expected result is a normal termination.
     */

    public final void testUnwrap195() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            assertFalse("Must return a no equal key, bad parametres used",
                    newkey.equals(K.getRSAPublicKey()));
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave publica, key algorithm "DES"
     * and key type tipo invalido. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap196() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave publica, key algorithm "RSA"
     * and key type tipo invalido. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap197() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave publica, key algorithm null
     * and key type tipo invalido. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap198() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave publica, key algorithm ""
     * and key type tipo invalido. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap199() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type PRIVATE_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap200() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type PUBLIC_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap201() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type SECRET_KEY. Expected result is a normal termination.
     */

    public final void testUnwrap202() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            assertFalse("Must return a no equal key, bad parametres used",
                    newkey.equals(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6,
                            7, 8 })));
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type SECRET_KEY. Expected result is a normal termination.
     */

    public final void testUnwrap203() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            assertFalse("Must return a no equal key, bad parametres used",
                    newkey.equals(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6,
                            7, 8 })));
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type SECRET_KEY. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap204() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type SECRET_KEY. Expected result is a normal termination.
     */

    public final void testUnwrap205() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            assertFalse("Must return a no equal key, bad parametres used",
                    newkey.equals(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6,
                            7, 8 })));
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type tipo invalido. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap206() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type tipo invalido. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap207() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type tipo invalido. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap208() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type tipo invalido. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap209() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PRIVATE_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap210() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PRIVATE_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap211() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type PRIVATE_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap212() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "" and key
     * type PRIVATE_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap213() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PUBLIC_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap214() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PUBLIC_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap215() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type PUBLIC_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap216() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "" and key
     * type PUBLIC_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap217() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type SECRET_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap218() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type SECRET_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap219() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type SECRET_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap220() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "" and key
     * type SECRET_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap221() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type tipo invalido. Expected result is throw IllegalArgumentException.
     */

    public final void testUnwrap222() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type tipo invalido. Expected result is throw IllegalArgumentException.
     */

    public final void testUnwrap223() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type tipo invalido. Expected result is throw IllegalArgumentException.
     */

    public final void testUnwrap224() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "" and key
     * type tipo invalido. Expected result is throw IllegalArgumentException.
     */

    public final void testUnwrap225() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave privada, key algorithm "RSA"
     * and key type PRIVATE_KEY. Expected result is a normal termination.
     */

    public final void testUnwrap226() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            assertEquals("Must return a equal key", K.getRSAPrivateKey(),
                    newkey);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave privada, key algorithm null
     * and key type PRIVATE_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap227() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave privada, key algorithm ""
     * and key type PRIVATE_KEY. Expected result is a normal termination.
     */

    public final void testUnwrap228() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            assertEquals("Must return a equal key", K.getRSAPrivateKey(),
                    newkey);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave privada, key algorithm null
     * and key type PUBLIC_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap229() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave privada, key algorithm "DES"
     * and key type SECRET_KEY. Expected result is a normal termination.
     */

    public final void testUnwrap230() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            assertFalse("Must return a no equal key, bad parametres used",
                    newkey.equals(K.getRSAPrivateKey()));
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave privada, key algorithm "RSA"
     * and key type SECRET_KEY. Expected result is a normal termination.
     */

    public final void testUnwrap231() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            assertFalse("Must return a no equal key, bad parametres used",
                    newkey.equals(K.getRSAPrivateKey()));
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave privada, key algorithm null
     * and key type SECRET_KEY. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap232() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave privada, key algorithm ""
     * and key type SECRET_KEY. Expected result is a normal termination.
     */

    public final void testUnwrap233() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            assertFalse("Must return a no equal key, bad parametres used",
                    newkey.equals(K.getRSAPrivateKey()));
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave privada, key algorithm "DES"
     * and key type tipo invalido. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap234() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave privada, key algorithm "RSA"
     * and key type tipo invalido. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap235() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave privada, key algorithm null
     * and key type tipo invalido. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap236() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave privada, key algorithm ""
     * and key type tipo invalido. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap237() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type PRIVATE_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap238() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type PUBLIC_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap239() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type SECRET_KEY. Expected result is a normal termination.
     */

    public final void testUnwrap240() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            assertFalse("Must return a no equal key, bad parametres used",
                    newkey.equals(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6,
                            7, 8 })));
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type SECRET_KEY. Expected result is a normal termination.
     */

    public final void testUnwrap241() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            assertFalse("Must return a no equal key, bad parametres used",
                    newkey.equals(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6,
                            7, 8 })));
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type SECRET_KEY. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap242() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type SECRET_KEY. Expected result is a normal termination.
     */

    public final void testUnwrap243() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            assertFalse("Must return a no equal key, bad parametres used",
                    newkey.equals(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6,
                            7, 8 })));
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type tipo invalido. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap244() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type tipo invalido. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap245() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type tipo invalido. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap246() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type tipo invalido. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap247() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PRIVATE_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap248() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PRIVATE_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap249() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type PRIVATE_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap250() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "" and key
     * type PRIVATE_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap251() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PUBLIC_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap252() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PUBLIC_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap253() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type PUBLIC_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap254() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "" and key
     * type PUBLIC_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap255() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type SECRET_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap256() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type SECRET_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap257() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type SECRET_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap258() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "" and key
     * type SECRET_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap259() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type tipo invalido. Expected result is throw IllegalArgumentException.
     */

    public final void testUnwrap260() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type tipo invalido. Expected result is throw IllegalArgumentException.
     */

    public final void testUnwrap261() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type tipo invalido. Expected result is throw IllegalArgumentException.
     */

    public final void testUnwrap262() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "" and key
     * type tipo invalido. Expected result is throw IllegalArgumentException.
     */

    public final void testUnwrap263() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type PRIVATE_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap264() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type PUBLIC_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap265() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type SECRET_KEY. Expected result is a normal termination.
     */

    public final void testUnwrap266() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            assertFalse("Must return a no equal key, bad parametres used",
                    newkey.equals(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6,
                            7, 8 })));
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type SECRET_KEY. Expected result is a normal termination.
     */

    public final void testUnwrap267() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            assertFalse("Must return a no equal key, bad parametres used",
                    newkey.equals(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6,
                            7, 8 })));
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type SECRET_KEY. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap268() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type SECRET_KEY. Expected result is a normal termination.
     */

    public final void testUnwrap269() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            assertFalse("Must return a no equal key, bad parametres used",
                    newkey.equals(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6,
                            7, 8 })));
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type tipo invalido. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap270() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type tipo invalido. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap271() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type tipo invalido. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap272() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type tipo invalido. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap273() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PRIVATE_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap274() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PRIVATE_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap275() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type PRIVATE_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap276() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "" and key
     * type PRIVATE_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap277() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PUBLIC_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap278() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PUBLIC_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap279() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type PUBLIC_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap280() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "" and key
     * type PUBLIC_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap281() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type SECRET_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap282() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type SECRET_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap283() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type SECRET_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap284() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "" and key
     * type SECRET_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap285() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type tipo invalido. Expected result is throw IllegalArgumentException.
     */

    public final void testUnwrap286() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type tipo invalido. Expected result is throw IllegalArgumentException.
     */

    public final void testUnwrap287() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type tipo invalido. Expected result is throw IllegalArgumentException.
     */

    public final void testUnwrap288() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "" and key
     * type tipo invalido. Expected result is throw IllegalArgumentException.
     */

    public final void testUnwrap289() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type PRIVATE_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap290() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type PUBLIC_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap291() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type SECRET_KEY. Expected result is a normal termination.
     */

    public final void testUnwrap292() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            assertFalse("Must return a no equal key, bad parametres used",
                    newkey.equals(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6,
                            7, 8 })));
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type SECRET_KEY. Expected result is a normal termination.
     */

    public final void testUnwrap293() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            assertFalse("Must return a no equal key, bad parametres used",
                    newkey.equals(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6,
                            7, 8 })));
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type SECRET_KEY. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap294() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type SECRET_KEY. Expected result is a normal termination.
     */

    public final void testUnwrap295() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            assertFalse("Must return a no equal key, bad parametres used",
                    newkey.equals(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6,
                            7, 8 })));
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type tipo invalido. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap296() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type tipo invalido. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap297() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type tipo invalido. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap298() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type tipo invalido. Expected result is throw
     * IllegalArgumentException.
     */

    public final void testUnwrap299() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PRIVATE_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap300() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PRIVATE_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap301() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type PRIVATE_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap302() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "" and key
     * type PRIVATE_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap303() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PUBLIC_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap304() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PUBLIC_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap305() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type PUBLIC_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap306() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "" and key
     * type PUBLIC_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap307() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type SECRET_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap308() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type SECRET_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap309() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type SECRET_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap310() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "" and key
     * type SECRET_KEY. Expected result is throw RuntimeException.
     */

    public final void testUnwrap311() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type tipo invalido. Expected result is throw IllegalArgumentException.
     */

    public final void testUnwrap312() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type tipo invalido. Expected result is throw IllegalArgumentException.
     */

    public final void testUnwrap313() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm null and key
     * type tipo invalido. Expected result is throw IllegalArgumentException.
     */

    public final void testUnwrap314() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.UNWRAP_MODE, make a unwrap with nula, key algorithm "" and key
     * type tipo invalido. Expected result is throw IllegalArgumentException.
     */

    public final void testUnwrap315() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.UNWRAP_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "DES" and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap316() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "RSA" and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap317() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm null
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap318() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm ""
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap319() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "DES" and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap320() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "RSA" and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap321() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm null
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap322() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm ""
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap323() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "DES" and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap324() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "RSA" and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap325() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm null
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap326() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm ""
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap327() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "DES" and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap328() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "RSA" and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap329() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm null
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap330() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm ""
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap331() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave publica, key algorithm
     * "DES" and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap332() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave publica, key algorithm
     * "RSA" and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap333() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave publica, key algorithm null
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap334() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave publica, key algorithm ""
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap335() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave publica, key algorithm
     * "DES" and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap336() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave publica, key algorithm
     * "RSA" and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap337() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave publica, key algorithm null
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap338() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave publica, key algorithm ""
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap339() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave publica, key algorithm
     * "DES" and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap340() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave publica, key algorithm
     * "RSA" and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap341() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave publica, key algorithm null
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap342() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave publica, key algorithm ""
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap343() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave publica, key algorithm
     * "DES" and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap344() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave publica, key algorithm
     * "RSA" and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap345() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave publica, key algorithm null
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap346() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave publica, key algorithm ""
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap347() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap348() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap349() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap350() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap351() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap352() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap353() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap354() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap355() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap356() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap357() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap358() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap359() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap360() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap361() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap362() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap363() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap364() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap365() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap366() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap367() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap368() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap369() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap370() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap371() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap372() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap373() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap374() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap375() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap376() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap377() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap378() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap379() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "DES" and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap380() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "RSA" and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap381() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm null
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap382() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm ""
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap383() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "DES" and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap384() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "RSA" and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap385() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm null
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap386() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm ""
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap387() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "DES" and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap388() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "RSA" and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap389() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm null
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap390() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm ""
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap391() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "DES" and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap392() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "RSA" and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap393() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm null
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap394() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave privada, key algorithm ""
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap395() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap396() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap397() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap398() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap399() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap400() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap401() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap402() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap403() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap404() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap405() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap406() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap407() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap408() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap409() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap410() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap411() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap412() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap413() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap414() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap415() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap416() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap417() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap418() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap419() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap420() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap421() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap422() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap423() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap424() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap425() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap426() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap427() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap428() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap429() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap430() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap431() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap432() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap433() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap434() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap435() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap436() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap437() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap438() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap439() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap440() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap441() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap442() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap443() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap444() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap445() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap446() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap447() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap448() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap449() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap450() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap451() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap452() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap453() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap454() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap455() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap456() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap457() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap458() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap459() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap460() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap461() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap462() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap463() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap464() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap465() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap466() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap467() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap468() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap469() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap470() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap471() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap472() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap473() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap474() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap475() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap476() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap477() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap478() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap479() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap480() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap481() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap482() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap483() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap484() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap485() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap486() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap487() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap488() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap489() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap490() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.ENCRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap491() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.ENCRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "DES" and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap492() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "RSA" and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap493() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm null
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap494() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm ""
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap495() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "DES" and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap496() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "RSA" and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap497() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm null
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap498() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm ""
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap499() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "DES" and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap500() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "RSA" and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap501() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm null
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap502() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm ""
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap503() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "DES" and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap504() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "RSA" and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap505() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm null
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap506() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm ""
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap507() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave publica, key algorithm
     * "DES" and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap508() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave publica, key algorithm
     * "RSA" and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap509() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave publica, key algorithm null
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap510() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave publica, key algorithm ""
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap511() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave publica, key algorithm
     * "DES" and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap512() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave publica, key algorithm
     * "RSA" and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap513() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave publica, key algorithm null
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap514() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave publica, key algorithm ""
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap515() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave publica, key algorithm
     * "DES" and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap516() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave publica, key algorithm
     * "RSA" and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap517() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave publica, key algorithm null
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap518() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave publica, key algorithm ""
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap519() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave publica, key algorithm
     * "DES" and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap520() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave publica, key algorithm
     * "RSA" and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap521() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave publica, key algorithm null
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap522() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave publica, key algorithm ""
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap523() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPublicKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap524() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap525() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap526() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap527() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap528() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap529() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap530() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap531() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap532() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap533() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap534() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap535() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap536() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap537() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap538() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap539() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap540() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap541() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap542() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap543() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap544() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap545() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap546() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap547() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap548() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap549() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap550() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap551() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap552() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap553() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap554() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/ECB/ISO10126Padding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap555() throws Exception {
        cipher = Cipher.getInstance("DES/ECB/ISO10126Padding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "DES" and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap556() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "RSA" and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap557() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm null
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap558() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm ""
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap559() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "DES" and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap560() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "RSA" and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap561() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm null
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap562() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm ""
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap563() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "DES" and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap564() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "RSA" and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap565() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm null
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap566() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm ""
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap567() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "DES" and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap568() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm
     * "RSA" and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap569() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm null
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap570() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave privada, key algorithm ""
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap571() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher.wrap(K.getRSAPrivateKey());
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap572() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap573() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap574() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap575() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap576() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap577() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap578() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap579() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap580() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap581() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap582() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap583() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap584() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap585() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap586() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap587() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap588() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap589() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap590() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap591() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap592() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap593() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap594() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap595() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap596() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap597() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap598() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap599() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap600() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap601() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap602() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is DES/CBC/NoPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap603() throws Exception {
        cipher = Cipher.getInstance("DES/CBC/NoPadding", provider);
        cipher.init(Cipher.WRAP_MODE, new K("DES", new byte[] { 1, 2, 3, 4, 5,
                6, 7, 8 }));
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, new K("DES", new byte[] { 1, 2, 3, 4,
                5, 6, 7, 8 }), cipher.getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap604() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap605() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap606() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap607() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap608() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap609() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap610() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap611() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap612() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap613() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap614() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap615() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap616() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap617() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap618() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap619() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap620() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap621() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap622() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap623() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap624() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap625() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap626() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap627() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap628() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap629() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap630() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap631() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap632() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap633() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap634() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap635() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPrivateKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPublicKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap636() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap637() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type PRIVATE_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap638() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap639() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap640() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap641() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type PUBLIC_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap642() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap643() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap644() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap645() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type SECRET_KEY. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap646() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap647() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "DES" and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap648() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * "RSA" and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap649() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm
     * null and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap650() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with clave simetrica, key algorithm ""
     * and key type tipo invalido. Expected result is throw
     * IllegalStateException.
     */

    public final void testUnwrap651() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        output = cipher
                .wrap(new K("DES", new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 }));
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap652() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap653() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap654() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type PRIVATE_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap655() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PRIVATE_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap656() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap657() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap658() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type PUBLIC_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap659() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.PUBLIC_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap660() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap661() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap662() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type SECRET_KEY. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap663() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", Cipher.SECRET_KEY);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "DES" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap664() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "DES", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "RSA" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap665() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "RSA", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm null and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap666() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, null, 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * The cipher, whose transformation is RSA/NONE/OAEPPadding and mode is
     * Cipher.DECRYPT_MODE, make a unwrap with nula, key algorithm "" and key
     * type tipo invalido. Expected result is throw IllegalStateException.
     */

    public final void testUnwrap667() throws Exception {
        cipher = Cipher.getInstance("RSA/NONE/OAEPPadding", provider);
        cipher.init(Cipher.WRAP_MODE, K.getRSAPublicKey());
        byte[] output = null;
        cipher.init(Cipher.DECRYPT_MODE, K.getRSAPrivateKey(), cipher
                .getParameters());
        try {
            Key newkey = cipher.unwrap(output, "", 99);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

}
