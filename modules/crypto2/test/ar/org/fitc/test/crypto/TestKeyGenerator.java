package ar.org.fitc.test.crypto;

import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;

import junit.framework.TestCase;
import ar.org.fitc.test.util.Messages;

/**
 * Junit TestCase container class for KeyGenerator test methods
 *
 * @author Osvaldo Demo
 * @version 1.0
 *
 */

public class TestKeyGenerator extends TestCase implements Messages {

    private KeyGenerator kg;

    private String algorithm;

    private String providerString;

    private Provider provider = null;

    private AlgorithmParameterGenerator apg = null;

    private AlgorithmParameters ap = null;

    private int keysize;

    IvParameterSpec ivSpec;

    /**
     * This method makes posible to run the tests contained in this class from
     * command line
     */
    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestKeyGenerator.class);
    }

    public TestKeyGenerator(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        algorithm = "DES";
        kg = KeyGenerator.getInstance(algorithm);
        provider = kg.getProvider();
        providerString = provider.getName();
        apg = AlgorithmParameterGenerator.getInstance(algorithm);
        apg.init(128, new SecureRandom());
        ap = apg.generateParameters();
        keysize = 56;
        ivSpec = new IvParameterSpec(new byte[8]);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     *
     * {@ar.org.fitc.testmethod_ref javax.crypto.KeyGenerator.getAlgorithm()}
     *
     * This method tests that the returning value of <b>getAlgorithm()</b>
     * should not be Null.
     *
     * @ar.org.fitc.internal this method calls assertNotNull to check for a not null value.
     * @ar.org.fitc.todo check the posibility of making new tests
     *
     */
    public final void testGetAlgorithm001() {
        assertNotNull("Should not be null", kg.getAlgorithm());
    }

    /**
     * This method tests that the returning value of getAlgorithm() should
     * return the <i>same algorithm</i> used on initialization.
     *
     * @ar.org.fitc.internal uses assertEquals.
     * @ar.org.fitc.testmethod_ref javax.crypto.KeyGenerator.getAlgorithm()
     */
    public final void testGetAlgorithm002() {
        assertEquals("Transformation String incorrect...", algorithm, kg
                .getAlgorithm());
    }

    /**
     * This method tests that the method getInstance(String algorithm) throws
     * <i>NullPointerException</i> when "null" is passed as parameter.
     *
     * @ar.org.fitc.internal uses a try catch block.
     */
    public final void testGetInstanceString001() {
        try {
            kg = KeyGenerator.getInstance(null);
            fail(msgNullPointer);
        } catch (NullPointerException e) {
        } catch (Throwable e) {
            fail(msgNullPointer);
        }
    }

    /**
     * This method tests that the method getInstance(String algorithm) throws
     * <i>NoSuchAlgorithmException</i> when "adf" is passed as parameter.
     *
     * @ar.org.fitc.internal uses a try catch block.
     */
    public final void testGetInstanceString002() {
        try {
            kg = KeyGenerator.getInstance("adf");
            fail(msgNoSuchAlgorithm);
        } catch (NoSuchAlgorithmException e) {
        } catch (Throwable e) {
            fail(msgNoSuchAlgorithm);
        }
    }

    /**
     * This method tests that the returning value of getInstance(String
     * algorithm) should not be null when a valid algorithm is passed as
     * parameter.
     *
     * @ar.org.fitc.internal uses a try catch block.
     */
    public final void testGetInstanceString003() {
        try {
            kg = KeyGenerator.getInstance(algorithm);
            assertNotNull("Should not be null", kg);
        } catch (Throwable e) {
            fail(msgNoException);
        }
    }

    /**
     * This method tests that the returning value of <b>getInstance(String
     * algorithm,String Provider)</b> should not be null when a valid algorithm
     * and provider are passed as parameters.
     *
     * @ar.org.fitc.internal uses a try catch block.
     */
    public final void testGetInstanceStringString001() {
        try {
            kg = KeyGenerator.getInstance(algorithm, providerString);
            assertTrue(msgNotInstance + "KeyGenerator",
                    kg instanceof KeyGenerator);
        } catch (Throwable e) {
            fail(msgNoException);
        }
    }

    /**
     * This method tests the method <b>getInstance(String algorithm,String
     * Provider)</b> should throw <i>NoSuchProviderException</i> when a valid
     * algorithm and "WrongProvider" are passed as parameters.
     *
     * @ar.org.fitc.internal uses a try catch block.
     */
    public final void testGetInstanceStringString002() {
        providerString = "WrongProvider";
        try {
            kg = KeyGenerator.getInstance(algorithm, providerString);
            fail(msgNoSuchProvider);
        } catch (NoSuchProviderException e) {
        } catch (Throwable e) {
            fail(msgNoSuchProvider);
        }
    }

    /**
     * This method tests that the returning value of <b>getInstance(String
     * algorithm,String Provider)</b> should throw <i>IllegalArgumentException</i>
     * when a valid algorithm and null string are passed as parameters.
     *
     * @ar.org.fitc.internal uses a try catch block.
     */
    public final void testGetInstanceStringString003() {
        providerString = null;
        try {
            kg = KeyGenerator.getInstance(algorithm, providerString);
            fail(msgIllegalArgument);
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail(msgIllegalArgument);
        }
    }

    /**
     * This method tests that the returning value of <b>getInstance(String
     * algorithm,String Provider)</b> should throw <i>NullPointerException</i>
     * when a null string algorithm and valid provider are passed as parameters.
     *
     * @ar.org.fitc.internal uses a try catch block.
     */
    public final void testGetInstanceStringString004() {
        algorithm = null;
        try {
            kg = KeyGenerator.getInstance(algorithm, providerString);
            fail(msgNullPointer);
        } catch (NullPointerException e) {
        } catch (Throwable e) {
            fail(msgNullPointer);
        }
    }

    /**
     * This method tests that the returning value of <b>getInstance(String
     * algorithm,String Provider)</b> should throw <i>NullPointerException</i>
     * when a valid algorithm and provider are passed as parameters.
     *
     * @ar.org.fitc.internal uses a try catch block.
     */
    public final void testGetInstanceStringProvider001() {
        try {
            kg = KeyGenerator.getInstance(algorithm, provider);
            assertNotNull("Should not be null", kg);
        } catch (Throwable e) {
            fail(msgNoException);
        }
    }

    /**
     * This method tests that the returning value of <b>getInstance(String
     * algorithm,String Provider)</b> should throw <i>NoSuchAlgorithmException</i>
     * when "NONE" algorithm and a valid provider are passed as parameters.
     *
     * @ar.org.fitc.internal uses a try catch block.
     */
    public final void testGetInstanceStringProvider002() {
        algorithm = "NONE";
        try {
            kg = KeyGenerator.getInstance(algorithm, provider);
            fail(msgNoSuchAlgorithm);
        } catch (NoSuchAlgorithmException e) {
        } catch (Throwable e) {
            fail(msgNoSuchAlgorithm);
        }
    }

    /**
     * This method tests that the returning value of <b>getInstance(String
     * algorithm,String Provider)</b> should throw <i>NoSuchAlgorithmException</i>
     * when a valid algorithm and a null provider are passed as parameters.
     *
     * @ar.org.fitc.internal uses a try catch block.
     */
    public final void testGetInstanceStringProvider003() {
        provider = null;
        try {
            kg = KeyGenerator.getInstance(algorithm, provider);
            fail(msgIllegalArgument);
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail(msgIllegalArgument);
        }
    }

    /**
     * This method tests that the returning value of <b>getProvider()</b>
     * should not return null when a valid algorithm and a null provider are
     * passed as parameters.
     *
     * @ar.org.fitc.internal uses a try catch block.
     */
    public final void testGetProvider001() {
        assertNotNull("Should not be null", kg.getProvider());
    }

    /**
     * This method tests that the method <b>init(SecureRandom random)</b>
     * should not raise an exception when passed a new SecureRandom() as
     * parameter
     *
     * @ar.org.fitc.internal uses a try catch block.
     */
    public final void testInitSecureRandom001() {
        try {
            kg.init(new SecureRandom());
        } catch (Throwable e) {
            fail(msgNoException);
        }
    }

    /**
     * This method tests that the method <b>init(AlgorithmParameterSpec params)</b>
     * should raise <i>InvalidParameterException</i> when passed null instead
     * of an AlgorithmParameterSpec.
     *
     * @ar.org.fitc.internal uses a try catch block.
     */
    public final void testInitAlgorithmParameterSpec001() {
        try {
            kg.init(ap.getParameterSpec(null));
            fail(msgRaise + "InvalidParameterException");
        } catch (InvalidParameterSpecException e) {
        } catch (Throwable e) {
            fail(msgRaise + "InvalidParameterException");
        }
    }

    /**
     * This method tests that the method <b>init(AlgorithmParameterSpec params)</b>
     * should raise <i>InvalidAlgorithmParameterException</i> when passed a
     * parameter spec inited on null instead of an AlgorithmParameterSpec.
     *
     * @ar.org.fitc.internal uses a try catch block.
     * @ar.org.fitc.internal checks for BouncyCastle provider because its
     *                       required by the test
     */
    public final void testInitAlgorithmParameterSpec002() {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte030\" was not excecuted.");
            return;
        }
        try {
            kg = KeyGenerator.getInstance("Blowfish", "BC");
            apg = AlgorithmParameterGenerator.getInstance("RC2", "BC");
            apg.init(128, new SecureRandom());
            ap = apg.generateParameters();
            kg.init(ap.getParameterSpec(RC2ParameterSpec.class));
            fail(msgRaise + "InvalidAlgorithmParameterException");
        } catch (InvalidAlgorithmParameterException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /**
     * This method tests that the method <b>init(AlgorithmParameterSpec
     * params,SecureRandom random)</b> should raise
     * <i>InvalidAlgorithmParameterException</i> when passed null instead of an
     * AlgorithmParameterSpec.
     *
     * @ar.org.fitc.internal uses a try catch block.
     */
    public final void testInitAlgorithmParameterSpecSecureRandom001() {
        try {
            kg = KeyGenerator.getInstance("DES", "BC");
            kg.init(ivSpec, new SecureRandom());
            fail(msgRaise + "InvalidAlgorithmParameterException");
        } catch (InvalidAlgorithmParameterException e) {
        } catch (Throwable e) {
            fail(msgRaise + "InvalidAlgorithmParameterException");
        }
    }

    /**
     * This method tests that the method <b>init(int keysize)</b> should raise
     * <i>InvalidParameterException</i> when passed Integer.MAX_VALUE as key
     * size, but only when Strong Limited Jurisdiction policy file is installed.
     *
     * If Unlimited Strength jurisdiction policy file is installed, this test
     * should fail.
     *
     * @ar.org.fitc.internal uses a try catch block.
     */
    public final void testInitInt001() {
        try {
            keysize = Integer.MAX_VALUE;
            kg.init(keysize);
            fail(msgRaise + "InvalidParameterException");
        } catch (InvalidParameterException e) {
            return;
        } catch (Throwable e) {
            fail(msgRaise + "InvalidParameterException");
        }
    }

    /**
     * This method tests that the method <b>init(int keysize)</b> should not
     * raise an exception when passed a valid integer as key size.
     *
     * @ar.org.fitc.internal uses a try catch block.
     */
    public final void testInitInt002() {
        try {
            kg.init(keysize);
        } catch (Throwable e) {
            fail(msgNoException);
        }
    }

    /**
     * This method tests that the method <b>init(int keysize,SecureRandom
     * random)</b> should not raise an exception when passed a valid integer as
     * key size.
     *
     * @ar.org.fitc.internal uses a try catch block.
     */
    public final void testInitIntSecureRandom001() {
        try {
            kg.init(keysize, new SecureRandom());
        } catch (Throwable e) {
            fail(msgNoException);
        }
    }

    /**
     * This method tests that the method <b>generateKey()</b> should not return
     * null when the KeyGenerator has been initialized correctly.
     *
     * @ar.org.fitc.internal uses assertNotNull.
     */
    public final void testGenerateKey001() {
        assertNotNull("Should not be null", kg.generateKey());
    }
}
