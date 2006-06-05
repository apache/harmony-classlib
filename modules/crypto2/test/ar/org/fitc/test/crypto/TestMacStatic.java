package ar.org.fitc.test.crypto;

import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.PSSParameterSpec;
import java.util.Arrays;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import junit.framework.TestCase;
import ar.org.fitc.test.util.K;
import ar.org.fitc.test.util.Messages;
import ar.org.fitc.test.util.crypto.Util;

/**
 * Junit TestCase container class for Mac test methods
 *
 * @author Osvaldo Demo
 * @version 1.0
 *
 */
public class TestMacStatic extends TestCase implements Messages {

    private KeyGenerator kg = null;

    private Mac mac;

    private static Key key = null;

    private SecretKey skey = null;

    private Provider provider = null;

    private String providerString = null;

    private PBEParameterSpec params = null;

    private String algorithm = null;

    private byte salt2[] = null;

    private byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,
            (byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };

    private byte inputArray[] = null;

    private byte input = 0;

    private ByteBuffer inputBuffer = null;

    private Mac mac2;

    public static void main(String[] args) {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Sanity Check not passed: BouncyCastle Provider NOT INSTALLED.\r"
                            + "Tests will not run");
        } else {
            junit.textui.TestRunner.run(TestMacStatic.class);
        }
    }

    public TestMacStatic(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        algorithm = "HmacMD5";
        providerString = "SunJCE";
        kg = KeyGenerator.getInstance(algorithm);
        kg.init(new SecureRandom());
        key = new K(algorithm, salt);
        skey = kg.generateKey();
        mac = Mac.getInstance(key.getAlgorithm());
        // mac2 = Mac.getInstance(key.getAlgorithm());
        provider = mac.getProvider();
        params = new PBEParameterSpec(salt, 5);
        input = (byte) 0xac;
        inputBuffer = ByteBuffer.allocate(8);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'javax.crypto.Mac.getInstance(String)'
     */

    public final void testGetInstanceString002() {
        try {
            mac = Mac.getInstance("ELGAMAL");
            assertNotNull("Should not be null", mac);
        } catch (NoSuchAlgorithmException e) {
            e.getCause();
        }
    }

    public final void testGetInstanceString003() {
        try {
            mac = Mac.getInstance(null);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testGetInstanceString004() {
        try {
            mac = Mac.getInstance("hhatja");
            fail("Should raise NoSuchAlgorithmException");
        } catch (NoSuchAlgorithmException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testGetInstanceString005() {
        try {
            mac = Mac.getInstance("DH");
            fail("Should raise NoSuchAlgorithmException");
        } catch (NoSuchAlgorithmException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'javax.crypto.Mac.getInstance(String, String)'
     */
    public final void testGetInstanceStringString001() {

        try {
            mac = Mac.getInstance("HMacMD5", providerString);
            assertNotNull("Should not be null", mac);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testGetInstanceStringString002() {

        try {
            mac = Mac.getInstance(null, providerString);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testGetInstanceStringString003() {

        try {
            mac = Mac.getInstance("RC2MAC", providerString);
            fail("Should raise NoSuchAlgorithmException");
        } catch (NoSuchAlgorithmException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testGetInstanceStringString004() {

        try {
            mac = Mac.getInstance(algorithm, "NotValidProvider");
            fail("Should raise NoSuchProviderException");
        } catch (NoSuchProviderException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testGetInstanceStringString005() {
        try {
            mac = Mac.getInstance(algorithm, "");
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testGetInstanceStringString006() {
        try {
            mac = Mac.getInstance("hrgeqj", "NotValidProvider");
            fail("Should raise NoSuchProviderException");
        } catch (NoSuchProviderException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'javax.crypto.Mac.getInstance(String, Provider)'
     */

    public final void testGetInstanceStringProvider002() {

        try {
            mac = Mac.getInstance(null, provider);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testGetInstanceStringProvider003() {

        try {
            mac = Mac.getInstance("nyarsk67", provider);
            fail("Should raise NoSuchAlgorithmException");
        } catch (NoSuchAlgorithmException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testGetInstanceStringProvider004() {
        try {
            provider = null;
            mac = Mac.getInstance(algorithm, provider);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testGetInstanceStringProvider005() {
        try {
            provider = null;
            mac = Mac.getInstance("nyarsk67", provider);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'javax.crypto.Mac.getAlgorithm()'
     */

    public final void testGetAlgorithm001() {
        assertNotNull("Should not be null", mac.getAlgorithm());
    }

    /*
     * Test method for 'javax.crypto.Mac.getProvider()'
     */
    public final void testGetProvider001() {
        assertNotNull("Should not be null", mac.getProvider());
    }

    /*
     * Test method for 'javax.crypto.Mac.getMacLength()'
     */
    public final void testGetMacLength001() {
        assertTrue("Length should not be negative", mac.getMacLength() >= 0);
    }

    /*
     * Test method for 'javax.crypto.Mac.init(Key)'
     */

    public final void testInitKey002() {
        try {
            mac.init(null);
            fail("Should not raise any Exception...");
        } catch (InvalidKeyException e) {
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }

    public final void testInitKey003() {
        try {
            mac = Mac.getInstance("HMACPBESHA1");
            kg = KeyGenerator.getInstance("HmacSHA512");
            key = kg.generateKey();
            mac.init(key);
            fail("Should raise InvalidKeyException");
        } catch (InvalidKeyException e) {
        } catch (Throwable e) {
            fail("Should raise InvalidKeyException");
        }
    }

    /*
     * Test method for 'javax.crypto.Mac.init(Key, AlgorithmParameterSpec)'
     */

    public final void testInitKeyAlgorithmParameterSpec001() {
        try {
            mac = Mac.getInstance("HmacPBESHA1");
            String pwd = "grehasionh bnestrhgoiarenhbtkalht";
            PBEKeySpec keySpec = new PBEKeySpec(pwd.toCharArray());
            SecretKeyFactory kf = SecretKeyFactory.getInstance("PBE");
            SecretKey key = kf.generateSecret(keySpec);
            byte[] salt = new byte[] { (byte) 0x7d, (byte) 0x60, (byte) 0x43,
                    (byte) 0x5f, (byte) 0x02, (byte) 0xe9, (byte) 0xe0,
                    (byte) 0xae };
            PBEParameterSpec pbe = new PBEParameterSpec(salt, 1000);
            mac.init(key, pbe);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }

    public final void testInitKeyAlgorithmParameterSpec002() {
        try {
            kg = KeyGenerator.getInstance("HMacSHA1");
            key = kg.generateKey();
            mac = Mac.getInstance("HMACPBESHA1");
            mac.init(key, params);
            fail("Should raise InvalidKeyException");
        } catch (InvalidKeyException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testInitKeyAlgorithmParameterSpec003() {
        try {
            mac = Mac.getInstance("PBEWITHHMACSHA");
            kg = KeyGenerator.getInstance("HmacSHA1");
            skey = kg.generateKey();
            byte[] salt = new byte[] { 0x7d, 0x60, 0x43, 0x5f, 0x02,
                    (byte) 0xe9, (byte) 0xe0, (byte) 0xae };
            mac.init(skey, new PBEParameterSpec(salt, 55));
            fail("Should raise InvalidKeyException");
        } catch (InvalidAlgorithmParameterException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testInitKeyAlgorithmParameterSpec004() {
        try {
            mac = Mac.getInstance("PBEWITHHMACSHA");
            kg = KeyGenerator.getInstance("HmacSHA1");
            skey = kg.generateKey();
            mac.init(skey, null);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testInitKeyAlgorithmParameterSpec005() {

        try {
            mac = Mac.getInstance("HmacPBESHA1");
            String pwd = "grehasionh bnestrhgoiarenhbtkalht";
            PBEKeySpec keySpec = new PBEKeySpec(pwd.toCharArray());
            SecretKeyFactory kf = SecretKeyFactory.getInstance("PBE");
            SecretKey key = kf.generateSecret(keySpec);
            byte[] salt = new byte[] { (byte) 0x7d, (byte) 0x60, (byte) 0x43,
                    (byte) 0x5f, (byte) 0x02, (byte) 0xe9, (byte) 0xe0,
                    (byte) 0xae };
            mac.init(key, new PBEParameterSpec(salt, 0));
            fail("Should raise InvalidKeyException");
        } catch (InvalidAlgorithmParameterException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testInitKeyAlgorithmParameterSpec006() {

        try {
            mac = Mac.getInstance("HmacPBESHA1");
            String pwd = "grehasionh bnestrhgoiarenhbtkalht";
            PBEKeySpec keySpec = new PBEKeySpec(pwd.toCharArray());
            SecretKeyFactory kf = SecretKeyFactory.getInstance("PBE");
            SecretKey key = kf.generateSecret(keySpec);
            salt2 = new byte[8];
            mac.init(key, new PBEParameterSpec(salt2, 1));
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }

    public final void testInitKeyAlgorithmParameterSpec007() {
        try {
            mac = Mac.getInstance("HmacPBESHA1");
            String pwd = "grehasionh bnestrhgoiarenhbtkalht";
            PBEKeySpec keySpec = new PBEKeySpec(pwd.toCharArray());
            SecretKeyFactory kf = SecretKeyFactory.getInstance("PBE");
            SecretKey key = kf.generateSecret(keySpec);
            byte[] salt = new byte[] { (byte) 0x7d, (byte) 0x60, (byte) 0x43,
                    (byte) 0x5f, (byte) 0x02, (byte) 0xe9, (byte) 0xe0,
                    (byte) 0xae };
            mac.init(key, new PBEParameterSpec(salt, -5));
            fail("Should raise InvalidKeyException");
        } catch (InvalidAlgorithmParameterException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testInitKeyAlgorithmParameterSpec008() {
        try {
            mac = Mac.getInstance("HmacPBESHA1");
            String pwd = "grehasionh bnestrhgoiarenhbtkalht";
            PBEKeySpec keySpec = new PBEKeySpec(pwd.toCharArray());
            SecretKeyFactory kf = SecretKeyFactory.getInstance("PBE");
            SecretKey key = kf.generateSecret(keySpec);
            int saltLen = 55;
            mac.init(key, new PSSParameterSpec(saltLen));
            fail("Should raise InvalidKeyException");
        } catch (InvalidAlgorithmParameterException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testInitKeyAlgorithmParameterSpec009() {
        try {
            mac = Mac.getInstance("HmacPBESHA1");
            SecretKey key = null;
            byte[] salt = new byte[] { (byte) 0x7d, (byte) 0x60, (byte) 0x43,
                    (byte) 0x5f, (byte) 0x02, (byte) 0xe9, (byte) 0xe0,
                    (byte) 0xae };
            PBEParameterSpec pbe = new PBEParameterSpec(salt, 1000);
            mac.init(key, pbe);
            fail("Should raise InvalidKeyException");
        } catch (InvalidKeyException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'javax.crypto.Mac.update(byte)'
     */


    public final void testUpdateByteIllegalStateException() {
        try {
            byte input = (byte) 0xc7;
            mac.update(input);
            fail("Should raise IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'javax.crypto.Mac.update(byte[])'
     */

    public final void testUpdateByteArrayIllegalStateException() {
        try {
            mac.update(inputArray);
            fail("Should raise IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for javax.crypto.Mac.update(byte)'
     */

    boolean logtestUpdateByte = false;

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: -82 <br>
     * Expected result: [-70, -103, 33, -54, 13, 101, -80, 112, 94, 107, 22,
     * -32, -16, -110, -71, -69]
     *
     */
    public final void testUpdateByte002() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte002\" was not excecuted.");
            return;
        }
        byte input = -82;
        String hash = "[-70, -103, 33, -54, 13, 101, -80, 112, 94, 107, 22, -32, -16, -110, -71, -69]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte002");
        }
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out
                    .println("-> expected result: [-70, -103, 33, -54, 13, 101, -80, 112, 94, 107, 22, -32, -16, -110, -71, -69]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByte) {
            System.out.println("---Test PASSED---");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: -112 <br>
     * Expected result: [-17, -31, -79, -74, 19, 23, 61, -54, 123, 60, -24, -1,
     * -81, -1, 112, -41]
     *
     */
    public final void testUpdateByte003() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte003\" was not excecuted.");
            return;
        }
        byte input = -112;
        String hash = "[-17, -31, -79, -74, 19, 23, 61, -54, 123, 60, -24, -1, -81, -1, 112, -41]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte003");
        }
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out
                    .println("-> expected result: [-17, -31, -79, -74, 19, 23, 61, -54, 123, 60, -24, -1, -81, -1, 112, -41]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByte) {
            System.out.println("---Test PASSED---");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: -48 <br>
     * Expected result: [-88, 124, 12, -27, 31, 110, -75, 40, 125, 6, -122,
     * -117, -85, 45, -55, -116]
     *
     */
    public final void testUpdateByte004() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte004\" was not excecuted.");
            return;
        }
        byte input = -48;
        String hash = "[-88, 124, 12, -27, 31, 110, -75, 40, 125, 6, -122, -117, -85, 45, -55, -116]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte004");
        }
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out
                    .println("-> expected result: [-88, 124, 12, -27, 31, 110, -75, 40, 125, 6, -122, -117, -85, 45, -55, -116]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByte) {
            System.out.println("---Test PASSED---");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: -17 <br>
     * Expected result: [112, 114, -23, -114, -90, -34, 101, -122, 85, 29, -61,
     * 56, -26, 59, 38, 26]
     *
     */
    public final void testUpdateByte005() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte005\" was not excecuted.");
            return;
        }
        byte input = -17;
        String hash = "[112, 114, -23, -114, -90, -34, 101, -122, 85, 29, -61, 56, -26, 59, 38, 26]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte005");
        }
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out
                    .println("-> expected result: [112, 114, -23, -114, -90, -34, 101, -122, 85, 29, -61, 56, -26, 59, 38, 26]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByte) {
            System.out.println("---Test PASSED---");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: -41 <br>
     * Expected result: [-2, 44, -76, -29, 31, 14, 1, 126, -23, 90, -27, 77, 51,
     * 87, 24, -89]
     *
     */
    public final void testUpdateByte006() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte006\" was not excecuted.");
            return;
        }
        byte input = -41;
        String hash = "[-2, 44, -76, -29, 31, 14, 1, 126, -23, 90, -27, 77, 51, 87, 24, -89]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte006");
        }
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out
                    .println("-> expected result: [-2, 44, -76, -29, 31, 14, 1, 126, -23, 90, -27, 77, 51, 87, 24, -89]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByte) {
            System.out.println("---Test PASSED---");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: 113 <br>
     * Expected result: [94, 12, 70, 72, 107, 5, -38, -118, 35, 87, 84, 40, 78,
     * 112, 116, -44]
     *
     */
    public final void testUpdateByte007() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte007\" was not excecuted.");
            return;
        }
        byte input = 113;
        String hash = "[94, 12, 70, 72, 107, 5, -38, -118, 35, 87, 84, 40, 78, 112, 116, -44]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte007");
        }
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out
                    .println("-> expected result: [94, 12, 70, 72, 107, 5, -38, -118, 35, 87, 84, 40, 78, 112, 116, -44]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByte) {
            System.out.println("---Test PASSED---");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: -113 <br>
     * Expected result: [-61, 71, -127, -21, 108, 125, -61, 29, 56, -109, -31,
     * -55, -47, -35, -117, 113]
     *
     */
    public final void testUpdateByte008() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte008\" was not excecuted.");
            return;
        }
        byte input = -113;
        String hash = "[-61, 71, -127, -21, 108, 125, -61, 29, 56, -109, -31, -55, -47, -35, -117, 113]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte008");
        }
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out
                    .println("-> expected result: [-61, 71, -127, -21, 108, 125, -61, 29, 56, -109, -31, -55, -47, -35, -117, 113]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByte) {
            System.out.println("---Test PASSED---");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: -65 <br>
     * Expected result: [-81, 75, -17, 90, 98, 30, 106, -6, -71, -3, -108, 111,
     * 113, 82, -80, 66]
     *
     */
    public final void testUpdateByte009() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte009\" was not excecuted.");
            return;
        }
        byte input = -65;
        String hash = "[-81, 75, -17, 90, 98, 30, 106, -6, -71, -3, -108, 111, 113, 82, -80, 66]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte009");
        }
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out
                    .println("-> expected result: [-81, 75, -17, 90, 98, 30, 106, -6, -71, -3, -108, 111, 113, 82, -80, 66]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByte) {
            System.out.println("---Test PASSED---");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: -9 <br>
     * Expected result: [39, -118, -70, 120, -24, 117, -89, -2, -58, -97, -9,
     * 100, -12, 54, -79, 109]
     *
     */
    public final void testUpdateByte010() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte010\" was not excecuted.");
            return;
        }
        byte input = -9;
        String hash = "[39, -118, -70, 120, -24, 117, -89, -2, -58, -97, -9, 100, -12, 54, -79, 109]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte010");
        }
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out
                    .println("-> expected result: [39, -118, -70, 120, -24, 117, -89, -2, -58, -97, -9, 100, -12, 54, -79, 109]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByte) {
            System.out.println("---Test PASSED---");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: 103 <br>
     * Expected result: [-10, -80, -97, 44, -14, -79, 118, 72, 70, -79, -38,
     * -84, 46, 24, 26, -34]
     *
     */
    public final void testUpdateByte011() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte011\" was not excecuted.");
            return;
        }
        byte input = 103;
        String hash = "[-10, -80, -97, 44, -14, -79, 118, 72, 70, -79, -38, -84, 46, 24, 26, -34]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte011");
        }
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out
                    .println("-> expected result: [-10, -80, -97, 44, -14, -79, 118, 72, 70, -79, -38, -84, 46, 24, 26, -34]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByte) {
            System.out.println("---Test PASSED---");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: -124 <br>
     * Expected result: [-106, -111, 41, 59, -37, 107, 4, 71, -39, -118, -94,
     * -45, -103, 32, -28, -68]
     *
     */
    public final void testUpdateByte012() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte012\" was not excecuted.");
            return;
        }
        byte input = -124;
        String hash = "[-106, -111, 41, 59, -37, 107, 4, 71, -39, -118, -94, -45, -103, 32, -28, -68]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte012");
        }
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out
                    .println("-> expected result: [-106, -111, 41, 59, -37, 107, 4, 71, -39, -118, -94, -45, -103, 32, -28, -68]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByte) {
            System.out.println("---Test PASSED---");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: -101 <br>
     * Expected result: [-55, 60, -108, -128, -76, 95, -111, 125, -75, 110, -20,
     * -30, -91, -83, 113, -108]
     *
     */
    public final void testUpdateByte013() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte013\" was not excecuted.");
            return;
        }
        byte input = -101;
        String hash = "[-55, 60, -108, -128, -76, 95, -111, 125, -75, 110, -20, -30, -91, -83, 113, -108]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte013");
        }
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out
                    .println("-> expected result: [-55, 60, -108, -128, -76, 95, -111, 125, -75, 110, -20, -30, -91, -83, 113, -108]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByte) {
            System.out.println("---Test PASSED---");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: 70 <br>
     * Expected result: [-35, -50, -26, 0, -103, 38, 40, 81, 45, -5, 46, -70,
     * 90, 90, 104, 19]
     *
     */
    public final void testUpdateByte014() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte014\" was not excecuted.");
            return;
        }
        byte input = 70;
        String hash = "[-35, -50, -26, 0, -103, 38, 40, 81, 45, -5, 46, -70, 90, 90, 104, 19]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte014");
        }
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out
                    .println("-> expected result: [-35, -50, -26, 0, -103, 38, 40, 81, 45, -5, 46, -70, 90, 90, 104, 19]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByte) {
            System.out.println("---Test PASSED---");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: 80 <br>
     * Expected result: [76, -81, -4, 25, 50, -20, 16, -15, 26, 122, 65, 43, 52,
     * 13, 4, -76]
     *
     */
    public final void testUpdateByte015() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte015\" was not excecuted.");
            return;
        }
        byte input = 80;
        String hash = "[76, -81, -4, 25, 50, -20, 16, -15, 26, 122, 65, 43, 52, 13, 4, -76]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte015");
        }
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out
                    .println("-> expected result: [76, -81, -4, 25, 50, -20, 16, -15, 26, 122, 65, 43, 52, 13, 4, -76]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByte) {
            System.out.println("---Test PASSED---");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: -12 <br>
     * Expected result: [0, 101, 21, -114, 26, 10, 110, 73, -31, -94, 74, 103,
     * 10, 99, -113, -1]
     *
     */
    public final void testUpdateByte016() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte016\" was not excecuted.");
            return;
        }
        byte input = -12;
        String hash = "[0, 101, 21, -114, 26, 10, 110, 73, -31, -94, 74, 103, 10, 99, -113, -1]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte016");
        }
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out
                    .println("-> expected result: [0, 101, 21, -114, 26, 10, 110, 73, -31, -94, 74, 103, 10, 99, -113, -1]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByte) {
            System.out.println("---Test PASSED---");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: -115 <br>
     * Expected result: [-119, 20, -71, 0, 75, 28, 52, 15, -126, 20, 44, 61,
     * -108, -104, -18, -107]
     *
     */
    public final void testUpdateByte017() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte017\" was not excecuted.");
            return;
        }
        byte input = -115;
        String hash = "[-119, 20, -71, 0, 75, 28, 52, 15, -126, 20, 44, 61, -108, -104, -18, -107]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte017");
        }
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out
                    .println("-> expected result: [-119, 20, -71, 0, 75, 28, 52, 15, -126, 20, 44, 61, -108, -104, -18, -107]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByte) {
            System.out.println("---Test PASSED---");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: -40 <br>
     * Expected result: [24, -103, 24, 92, 28, 113, 123, 59, -74, 24, -74, -49,
     * 42, 9, -91, 69]
     *
     */
    public final void testUpdateByte018() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte018\" was not excecuted.");
            return;
        }
        byte input = -40;
        String hash = "[24, -103, 24, 92, 28, 113, 123, 59, -74, 24, -74, -49, 42, 9, -91, 69]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte018");
        }
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out
                    .println("-> expected result: [24, -103, 24, 92, 28, 113, 123, 59, -74, 24, -74, -49, 42, 9, -91, 69]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByte) {
            System.out.println("---Test PASSED---");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: 71 <br>
     * Expected result: [108, -99, -43, -72, -111, 79, 107, 80, -19, 94, -108,
     * 102, 62, 113, 111, 37]
     *
     */
    public final void testUpdateByte019() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte019\" was not excecuted.");
            return;
        }
        byte input = 71;
        String hash = "[108, -99, -43, -72, -111, 79, 107, 80, -19, 94, -108, 102, 62, 113, 111, 37]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte019");
        }
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out
                    .println("-> expected result: [108, -99, -43, -72, -111, 79, 107, 80, -19, 94, -108, 102, 62, 113, 111, 37]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByte) {
            System.out.println("---Test PASSED---");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: -40 <br>
     * Expected result: [24, -103, 24, 92, 28, 113, 123, 59, -74, 24, -74, -49,
     * 42, 9, -91, 69]
     *
     */
    public final void testUpdateByte020() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte020\" was not excecuted.");
            return;
        }
        byte input = -40;
        String hash = "[24, -103, 24, 92, 28, 113, 123, 59, -74, 24, -74, -49, 42, 9, -91, 69]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte020");
        }
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out
                    .println("-> expected result: [24, -103, 24, 92, 28, 113, 123, 59, -74, 24, -74, -49, 42, 9, -91, 69]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByte) {
            System.out.println("---Test PASSED---");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: -37 <br>
     * Expected result: [1, -123, 103, 117, 71, 102, -17, -114, 125, -54, 78,
     * 105, 9, -18, 64, -45]
     *
     */
    public final void testUpdateByte021() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte021\" was not excecuted.");
            return;
        }
        byte input = -37;
        String hash = "[1, -123, 103, 117, 71, 102, -17, -114, 125, -54, 78, 105, 9, -18, 64, -45]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte021");
        }
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out
                    .println("-> expected result: [1, -123, 103, 117, 71, 102, -17, -114, 125, -54, 78, 105, 9, -18, 64, -45]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByte) {
            System.out.println("---Test PASSED---");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: 105 <br>
     * Expected result: [-36, -86, 45, -78, 50, 91, -98, -77, 100, -107, -100,
     * 126, 80, -1, -96, 81]
     *
     */
    public final void testUpdateByte022() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte022\" was not excecuted.");
            return;
        }
        byte input = 105;
        String hash = "[-36, -86, 45, -78, 50, 91, -98, -77, 100, -107, -100, 126, 80, -1, -96, 81]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte022");
        }
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out
                    .println("-> expected result: [-36, -86, 45, -78, 50, 91, -98, -77, 100, -107, -100, 126, 80, -1, -96, 81]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByte) {
            System.out.println("---Test PASSED---");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: 104 <br>
     * Expected result: [110, -66, -3, -103, 126, 74, -41, 32, -58, 52, -93, 15,
     * 88, -37, 58, 103]
     *
     */
    public final void testUpdateByte023() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte023\" was not excecuted.");
            return;
        }
        byte input = 104;
        String hash = "[110, -66, -3, -103, 126, 74, -41, 32, -58, 52, -93, 15, 88, -37, 58, 103]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte023");
        }
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out
                    .println("-> expected result: [110, -66, -3, -103, 126, 74, -41, 32, -58, 52, -93, 15, 88, -37, 58, 103]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByte) {
            System.out.println("---Test PASSED---");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: 40 <br>
     * Expected result: [-60, -81, -19, 42, -51, -64, 98, -46, 96, 12, -6, 76,
     * -38, -73, -110, 63]
     *
     */
    public final void testUpdateByte024() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte024\" was not excecuted.");
            return;
        }
        byte input = 40;
        String hash = "[-60, -81, -19, 42, -51, -64, 98, -46, 96, 12, -6, 76, -38, -73, -110, 63]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte024");
        }
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out
                    .println("-> expected result: [-60, -81, -19, 42, -51, -64, 98, -46, 96, 12, -6, 76, -38, -73, -110, 63]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByte) {
            System.out.println("---Test PASSED---");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: -104 <br>
     * Expected result: [61, -29, 10, 17, -60, 113, -46, 8, 36, -88, -116, -82,
     * 67, 71, 90, 118]
     *
     */
    public final void testUpdateByte025() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte025\" was not excecuted.");
            return;
        }
        byte input = -104;
        String hash = "[61, -29, 10, 17, -60, 113, -46, 8, 36, -88, -116, -82, 67, 71, 90, 118]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte025");
        }
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out
                    .println("-> expected result: [61, -29, 10, 17, -60, 113, -46, 8, 36, -88, -116, -82, 67, 71, 90, 118]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByte) {
            System.out.println("---Test PASSED---");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: -56 <br>
     * Expected result: [-102, 89, 19, -94, 101, 7, 80, -5, -23, -123, 22, 57,
     * 63, 17, 92, 25]
     *
     */
    public final void testUpdateByte026() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte026\" was not excecuted.");
            return;
        }
        byte input = -56;
        String hash = "[-102, 89, 19, -94, 101, 7, 80, -5, -23, -123, 22, 57, 63, 17, 92, 25]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte026");
        }
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out
                    .println("-> expected result: [-102, 89, 19, -94, 101, 7, 80, -5, -23, -123, 22, 57, 63, 17, 92, 25]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByte) {
            System.out.println("---Test PASSED---");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: -1 <br>
     * Expected result: [97, -94, -98, 59, -83, 32, 93, 6, 104, 40, 67, 113, 70,
     * 2, 75, 46]
     *
     */
    public final void testUpdateByte027() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte027\" was not excecuted.");
            return;
        }
        byte input = -1;
        String hash = "[97, -94, -98, 59, -83, 32, 93, 6, 104, 40, 67, 113, 70, 2, 75, 46]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte027");
        }
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out
                    .println("-> expected result: [97, -94, -98, 59, -83, 32, 93, 6, 104, 40, 67, 113, 70, 2, 75, 46]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByte) {
            System.out.println("---Test PASSED---");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: -96 <br>
     * Mac not initialized. Should throw IllegalStateException
     *
     */
    public final void testUpdateByte028() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte028\" was not excecuted.");
            return;
        }
        byte input = -96;
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte028");
        }
        try {

            for (int i = 0; i < 100; i++) {
                mac.update(input);
            }
            mac.update(input);
            fail("Should raise IllegalStateException");
        } catch (IllegalStateException e) {
            if (logtestUpdateByte) {
                System.out
                        .println("Raised java.lang.IllegalStateException: MAC not initialized");
                System.out.println("---Test PASSED---");
                return;
            }
        } catch (Throwable e) {
            fail("Should raise IllegalStateException");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: 69 <br>
     * Mac not initialized. Should throw IllegalStateException
     *
     */
    public final void testUpdateByte029() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte029\" was not excecuted.");
            return;
        }
        byte input = 69;
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte029");
        }
        try {

            for (int i = 0; i < 100; i++) {
                mac.update(input);
            }
            mac.update(input);
            fail("Should raise IllegalStateException");
        } catch (IllegalStateException e) {
            if (logtestUpdateByte) {
                System.out
                        .println("Raised java.lang.IllegalStateException: MAC not initialized");
                System.out.println("---Test PASSED---");
                return;
            }
        } catch (Throwable e) {
            fail("Should raise IllegalStateException");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: -92 <br>
     * Mac not initialized. Should throw IllegalStateException
     *
     */
    public final void testUpdateByte030() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte030\" was not excecuted.");
            return;
        }
        byte input = -92;
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte030");
        }
        try {

            for (int i = 0; i < 100; i++) {
                mac.update(input);
            }
            mac.update(input);
            fail("Should raise IllegalStateException");
        } catch (IllegalStateException e) {
            if (logtestUpdateByte) {
                System.out
                        .println("Raised java.lang.IllegalStateException: MAC not initialized");
                System.out.println("---Test PASSED---");
                return;
            }
        } catch (Throwable e) {
            fail("Should raise IllegalStateException");
        }
    }

    /**
     * {@ar.org.fitc.testmethod_ref javax.crypto.Mac.update(byte)} MAC algorithm
     * used: HmacMD5, Provider: BC <br>
     * Input: 102 <br>
     * Mac not initialized. Should throw IllegalStateException
     *
     */
    public final void testUpdateByte031() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByte031\" was not excecuted.");
            return;
        }
        byte input = 102;
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByte) {
            System.out.println("testUpdateByte031");
        }
        try {

            for (int i = 0; i < 100; i++) {
                mac.update(input);
            }
            mac.update(input);
            fail("Should raise IllegalStateException");
        } catch (IllegalStateException e) {
            if (logtestUpdateByte) {
                System.out
                        .println("Raised java.lang.IllegalStateException: MAC not initialized");
                System.out.println("---Test PASSED---");
                return;
            }
        } catch (Throwable e) {
            fail("Should raise IllegalStateException");
        }
    }

    /*
     * Test method for 'javax.crypto.Mac.update(byte[], int, int)'
     */

    boolean logtestUpdateByteArrayIntInt = false;

    public final void testUpdateByteArrayIntInt001()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BouncyCastle not installed. Test \"testUpdateByteArrayIntInt001\" was not excecuted.");
            return;
        }
        byte[] input = { 56, -96, -47, 37, 15, -44, -75, -35, -63, 45, -114,
                86, -49, 84, 56, -67, -10, -38, 91, 58, -37, -88, -119, -26,
                -97, 28, 102, 118, 32, 58 };
        int offset = 6;
        int length = 12;
        String hash = "[59, -79, 3, -76, 65, 120, -60, 123, 46, 53, -81, 28, -8, 22, -80, -14]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("testUpdateByteArrayIntInt001");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input, offset, length);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteArrayIntInt) {
            System.out
                    .println("-> expected result: [59, -79, 3, -76, 65, 120, -60, 123, 46, 53, -81, 28, -8, 22, -80, -14]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("---Test PASSED---");
        }
    }



    public final void testUpdateByteArrayIntInt003()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BouncyCastle not installed. Test \"testUpdateByteArrayIntInt003\" was not excecuted.");
            return;
        }
        byte[] input = { -100, 122, -72, 120, 125, -100, -45, -108, -50, -105,
                71, 24, 90, -106, -3, 66, -13, -80, -49 };
        int offset = 1;
        int length = 14;
        String hash = "[-111, -81, -91, -105, -20, 70, -54, 27, 43, -10, 86, -6, 109, 113, 14, -2]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("testUpdateByteArrayIntInt003");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input, offset, length);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteArrayIntInt) {
            System.out
                    .println("-> expected result: [-111, -81, -91, -105, -20, 70, -54, 27, 43, -10, 86, -6, 109, 113, 14, -2]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("---Test PASSED---");
        }
    }


    public final void testUpdateByteArrayIntInt005()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BouncyCastle not installed. Test \"testUpdateByteArrayIntInt005\" was not excecuted.");
            return;
        }
        byte[] input = { 24, -29, -74, 56, -75, 90, -128, -83, -13, -15, 31,
                69, 125, -45, -82, -30, 106, -68, -116, -8, 121, -98, 41, 50,
                53, 17, 14, -97, -89, 27, 114, -95, 3, -37, 121, 95, -50, 42,
                -57, 64, 63, 83, 104, -119, 92, -101, 53, 28 };
        int offset = 4;
        int length = 1;
        String hash = "[104, 118, 95, -66, 49, 21, 50, 86, -58, -35, -12, -37, -26, -101, -49, 58]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("testUpdateByteArrayIntInt005");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input, offset, length);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteArrayIntInt) {
            System.out
                    .println("-> expected result: [104, 118, 95, -66, 49, 21, 50, 86, -58, -35, -12, -37, -26, -101, -49, 58]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("---Test PASSED---");
        }
    }


    public final void testUpdateByteArrayIntInt007()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BouncyCastle not installed. Test \"testUpdateByteArrayIntInt007\" was not excecuted.");
            return;
        }
        byte[] input = { 117, -110, -125, -92, 54, 120, 8, -31, -90, 87, 33,
                -6, 58, 63, -71, -67, -40 };
        int offset = 1;
        int length = 4;
        String hash = "[93, 77, -72, 31, 10, -75, -83, 34, 126, 63, 64, -19, -107, 10, 28, -56]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("testUpdateByteArrayIntInt007");
        }
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input, offset, length);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteArrayIntInt) {
            System.out
                    .println("-> expected result: [93, 77, -72, 31, 10, -75, -83, 34, 126, 63, 64, -19, -107, 10, 28, -56]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteArrayIntInt008()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BouncyCastle not installed. Test \"testUpdateByteArrayIntInt008\" was not excecuted.");
            return;
        }
        byte[] input = { 24, -7, -49, 2, -70, 78, -37, -4, 1, -64, -98, 120,
                -24, -50, -48, -40, -94, -89, 1, 81, 44, 121, 6, 113, 51, -44,
                -92, -115, 14, 41, -102, 57, 3, 100, 109, -128, -92, -29, 85,
                -92, -109, 116 };
        int offset = 3;
        int length = 9;
        String hash = "[-121, -50, 18, -31, 88, -109, 71, -24, -102, 86, -93, 102, 12, 57, 8, 66]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("testUpdateByteArrayIntInt008");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input, offset, length);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteArrayIntInt) {
            System.out
                    .println("-> expected result: [-121, -50, 18, -31, 88, -109, 71, -24, -102, 86, -93, 102, 12, 57, 8, 66]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("---Test PASSED---");
        }
    }



    public final void testUpdateByteArrayIntInt010()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BouncyCastle not installed. Test \"testUpdateByteArrayIntInt010\" was not excecuted.");
            return;
        }
        byte[] input = { -56, -78, -5, 103, -30, -60, 121, 81, 74, 37, -52, 76,
                -75, 122, -69, -50, -2, -14, 27, -47, 3, -12, -38, 17, -100,
                -20, -83, -28, -63, -93, -22, -17, 70 };
        int offset = 1;
        int length = 20;
        String hash = "[58, -67, -75, -89, 65, -92, 93, -26, -28, -15, 59, -6, -67, -64, -10, 53]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("testUpdateByteArrayIntInt010");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input, offset, length);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteArrayIntInt) {
            System.out
                    .println("-> expected result: [58, -67, -75, -89, 65, -92, 93, -26, -28, -15, 59, -6, -67, -64, -10, 53]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("---Test PASSED---");
        }
    }



    public final void testUpdateByteArrayIntInt012()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BouncyCastle not installed. Test \"testUpdateByteArrayIntInt012\" was not excecuted.");
            return;
        }
        byte[] input = { -75, -84, 100, 114, 104, -116, -88, -36, -127, -28,
                -9, -29, -101, -123, -116, -98, -52, 97, 77, 119, -48, -38, -46 };
        int offset = 4;
        int length = 19;
        String hash = "[-115, -29, -103, -125, -48, 102, -123, -73, 66, 66, 55, -122, 61, 19, -121, 36]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("testUpdateByteArrayIntInt012");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input, offset, length);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteArrayIntInt) {
            System.out
                    .println("-> expected result: [-115, -29, -103, -125, -48, 102, -123, -73, 66, 66, 55, -122, 61, 19, -121, 36]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteArrayIntInt013()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BouncyCastle not installed. Test \"testUpdateByteArrayIntInt013\" was not excecuted.");
            return;
        }
        byte[] input = { -44, -76, 8, 36, 54, 44, 75, -13, 63, 91, 101, -105,
                -115, 126, 24, 125, 16, -127, 45, -44, 30, -102, -58, -114,
                -39, 22, -66, 84, 125, -49, 45, -81, -12, -63, -48, 80, -39,
                51, 12, 33, -97, 47, -110, 104, -71, -21 };
        int offset = 2;
        int length = 12;
        String hash = "[-11, 76, -123, -53, 24, -26, 125, -114, -12, -14, -76, -117, 77, 44, -98, 47]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("testUpdateByteArrayIntInt013");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input, offset, length);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteArrayIntInt) {
            System.out
                    .println("-> expected result: [-11, 76, -123, -53, 24, -26, 125, -114, -12, -14, -76, -117, 77, 44, -98, 47]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteArrayIntInt014()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BouncyCastle not installed. Test \"testUpdateByteArrayIntInt014\" was not excecuted.");
            return;
        }
        byte[] input = { 49, 89, 96, -90, -55, 85, -85, -36, 81, -9, -59, 49,
                18, 97, -75, -108, -64, 4, 75, 84, 87, 86, -51, 104, -80, -105,
                48, 44, -78, -80, -123, 33, -110, 73, -4, 117, -29, -87, 48,
                -23, -58, 114 };
        int offset = 3;
        int length = 21;
        String hash = "[-85, 78, -54, 64, -77, 34, -107, -29, 99, -12, 96, 62, -89, -83, -119, -92]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("testUpdateByteArrayIntInt014");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input, offset, length);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteArrayIntInt) {
            System.out
                    .println("-> expected result: [-85, 78, -54, 64, -77, 34, -107, -29, 99, -12, 96, 62, -89, -83, -119, -92]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteArrayIntInt015()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BouncyCastle not installed. Test \"testUpdateByteArrayIntInt015\" was not excecuted.");
            return;
        }
        byte[] input = { 88, -59, -114, -95, 10, 72, 101, 20, 36, 36, -1, -13,
                -106, -77, -31, -32 };
        int offset = 0;
        int length = 3;
        String hash = "[-62, 92, 78, 2, 97, 28, -42, -88, -64, -111, -35, 31, 60, -8, -119, 110]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("testUpdateByteArrayIntInt015");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input, offset, length);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteArrayIntInt) {
            System.out
                    .println("-> expected result: [-62, 92, 78, 2, 97, 28, -42, -88, -64, -111, -35, 31, 60, -8, -119, 110]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteArrayIntInt016()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BouncyCastle not installed. Test \"testUpdateByteArrayIntInt016\" was not excecuted.");
            return;
        }
        byte[] input = { 106, 2, -32, -94, 2, -119, 21, -26, 105, 97, 84, 45,
                126, -83, 81, 27, 69, -18, 110, -17, -67 };
        int offset = 1;
        int length = 2;
        String hash = "[-109, -49, -20, -116, -5, 52, -37, -31, 45, -63, 22, 68, -8, -10, 19, 92]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("testUpdateByteArrayIntInt016");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input, offset, length);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteArrayIntInt) {
            System.out
                    .println("-> expected result: [-109, -49, -20, -116, -5, 52, -37, -31, 45, -63, 22, 68, -8, -10, 19, 92]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteArrayIntInt017()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BouncyCastle not installed. Test \"testUpdateByteArrayIntInt017\" was not excecuted.");
            return;
        }
        byte[] input = { -5, -66, 106, -113, -28, 71, -36, -46, -55, 5, 88,
                -44, 19, -120, 67 };
        int offset = 0;
        int length = 15;
        String hash = "[-123, 40, -107, 89, -111, 65, 79, 29, -33, -74, -124, 77, 17, 102, -85, -113]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("testUpdateByteArrayIntInt017");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input, offset, length);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteArrayIntInt) {
            System.out
                    .println("-> expected result: [-123, 40, -107, 89, -111, 65, 79, 29, -33, -74, -124, 77, 17, 102, -85, -113]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteArrayIntInt018()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BouncyCastle not installed. Test \"testUpdateByteArrayIntInt018\" was not excecuted.");
            return;
        }
        byte[] input = { -71, -96, 77, 26, 117, -69, -53, 35, -85, -30, 9, -48,
                -51, 51, -107, 36, -35, -99, -77, -8, -67, -78, 124, 60, -75,
                80, -113, 107, 79, 35, 105, 16, 98, 97, -44, -12, 67, 86, 26,
                -114, -64, 102, -19, 103, -56, -77 };
        int offset = 9;
        int length = 26;
        String hash = "[-96, -45, 101, -97, -28, -14, -75, -106, -57, -97, -60, -42, -18, 84, -81, 66]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("testUpdateByteArrayIntInt018");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input, offset, length);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteArrayIntInt) {
            System.out
                    .println("-> expected result: [-96, -45, 101, -97, -28, -14, -75, -106, -57, -97, -60, -42, -18, 84, -81, 66]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteArrayIntInt019()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BouncyCastle not installed. Test \"testUpdateByteArrayIntInt019\" was not excecuted.");
            return;
        }
        byte[] input = { 96, 43, 35, 62, -117, -70, 22, 126, 61, 106, -34, 115,
                125, 68, 6, 119, 69, 98, -22, 69, -98, -81, -57, -106, 71 };
        int offset = 1;
        int length = 20;
        String hash = "[-107, 42, 72, 19, -82, -10, -57, 29, 68, 14, 108, -45, 67, 30, -83, 102]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("testUpdateByteArrayIntInt019");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input, offset, length);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteArrayIntInt) {
            System.out
                    .println("-> expected result: [-107, 42, 72, 19, -82, -10, -57, 29, 68, 14, 108, -45, 67, 30, -83, 102]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteArrayIntInt020()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BouncyCastle not installed. Test \"testUpdateByteArrayIntInt020\" was not excecuted.");
            return;
        }
        byte[] input = { 107, -112, 62, -125, -108, -68, 127, -110, -83, 10,
                -7, -31, 52, 76, 94, -61, -34, 0, -119, 123, -33, -3, 1, 78,
                -80, -86, -90, -54, -99, 45, 96, -47, -60, -69, -99, 12, 22,
                69, -78 };
        int offset = 6;
        int length = 19;
        String hash = "[-80, -61, 45, 80, -19, 20, -69, -92, 112, -103, 59, 13, -2, 87, 12, -97]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("testUpdateByteArrayIntInt020");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input, offset, length);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteArrayIntInt) {
            System.out
                    .println("-> expected result: [-80, -61, 45, 80, -19, 20, -69, -92, 112, -103, 59, 13, -2, 87, 12, -97]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("---Test PASSED---");
        }
    }


    public final void testUpdateByteArrayIntInt022()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BouncyCastle not installed. Test \"testUpdateByteArrayIntInt022\" was not excecuted.");
            return;
        }
        byte[] input = { -52, 48, -23, -97, 54, 107, -100, -23, -125, 108, 29,
                123, 80, 127, -66, 113, 67, 0, 62, -44, 104, 90, 60, 41, 61,
                31, 88, 32, 43, 7, 52, 70, 41, 79, -19, 27, 68, 65, 59, -90, 73 };
        int offset = 3;
        int length = 16;
        String hash = "[-78, -95, -17, 2, -70, 79, 39, -71, 113, 126, -3, 123, -100, 41, 101, -17]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("testUpdateByteArrayIntInt022");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input, offset, length);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteArrayIntInt) {
            System.out
                    .println("-> expected result: [-78, -95, -17, 2, -70, 79, 39, -71, 113, 126, -3, 123, -100, 41, 101, -17]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteArrayIntInt023()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BouncyCastle not installed. Test \"testUpdateByteArrayIntInt023\" was not excecuted.");
            return;
        }
        byte[] input = { 105, -80, 10, 61, -64, -54, -99, -6, -63, -47, 96,
                -20, 81, -9, 82, -113, -47, -49, -24 };
        int offset = 3;
        int length = 1;
        String hash = "[-16, 11, 14, 49, -124, -4, 32, 105, -82, -67, -33, -112, 100, 96, -91, 127]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("testUpdateByteArrayIntInt023");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input, offset, length);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteArrayIntInt) {
            System.out
                    .println("-> expected result: [-16, 11, 14, 49, -124, -4, 32, 105, -82, -67, -33, -112, 100, 96, -91, 127]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteArrayIntInt024()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BouncyCastle not installed. Test \"testUpdateByteArrayIntInt024\" was not excecuted.");
            return;
        }
        byte[] input = { -120, -39, 107, -3, 46, -73, 48, -83, -36, 29, 103,
                -54, -20, -4, 30, -79, 30, 84, 0, 86, 49, 94, -62, 94, 48, 50,
                53, 9, 48, -24, -126, -72, 10, 70, -126, -63, -53, 47, 66, 98,
                15, 20, -60, -115 };
        int offset = 0;
        int length = 1;
        String hash = "[-118, -21, -16, 35, 102, 123, 25, 26, -115, -108, 42, 34, -75, 42, -32, -76]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("testUpdateByteArrayIntInt024");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input, offset, length);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteArrayIntInt) {
            System.out
                    .println("-> expected result: [-118, -21, -16, 35, 102, 123, 25, 26, -115, -108, 42, 34, -75, 42, -32, -76]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteArrayIntInt025()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BouncyCastle not installed. Test \"testUpdateByteArrayIntInt025\" was not excecuted.");
            return;
        }
        byte[] input = { 126, 20, 43, 57, 2, 12, 45, -65, -107, -56, -103, -56,
                -106, 123, 4, -84, 12, 105, 62, 38, -93, 0, -68, 32, -25, -31,
                -36, 119, -25, -102, 34, 81, -38, -59, -21, 106, -76, 0, -72 };
        int offset = 8;
        int length = 10;
        String hash = "[53, -128, 60, -11, -36, -67, -37, 61, -93, -117, -119, 76, 17, 6, -75, 121]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("testUpdateByteArrayIntInt025");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input, offset, length);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteArrayIntInt) {
            System.out
                    .println("-> expected result: [53, -128, 60, -11, -36, -67, -37, 61, -93, -117, -119, 76, 17, 6, -75, 121]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteArrayIntInt026()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BouncyCastle not installed. Test \"testUpdateByteArrayIntInt026\" was not excecuted.");
            return;
        }
        byte[] input = { -123, 27, 33, -14, 101, 30, 13, -11, 2, 22, -28, -117,
                107, 97, -57, -111, 11, 78, -71, -44, -101, -45, 95, 65, 57,
                -12, 112, 26, -9, 74, 83, -34, -111, 103, 8, -30, 44, 96, 75,
                61, 114, -66, 114, 116 };
        int offset = 1;
        int length = 35;
        String hash = "[72, 73, 52, 94, 19, 63, -122, -114, 57, 103, -53, 27, -23, -6, 4, -121]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("testUpdateByteArrayIntInt026");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input, offset, length);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteArrayIntInt) {
            System.out
                    .println("-> expected result: [72, 73, 52, 94, 19, 63, -122, -114, 57, 103, -53, 27, -23, -6, 4, -121]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteArrayIntInt027()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BouncyCastle not installed. Test \"testUpdateByteArrayIntInt027\" was not excecuted.");
            return;
        }
        byte[] input = { -89, -67, -102, -21, 78, 12, -92, -20, 106, 78, 69,
                -3, 6, -106, 22, 104, 58, 29, 74, -115, 100, 85, -126, 26, 71,
                -123, 11, -95, -67 };
        int offset = 0;
        int length = 15;
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("testUpdateByteArrayIntInt027");
        }
        try {
            mac.update(input, offset, length);
            fail("Should raise IllegalStateException");
        } catch (IllegalStateException e) {
            if (logtestUpdateByteArrayIntInt) {
                System.out
                        .println("Raised java.lang.IllegalStateException: MAC not initialized");
                System.out.println("---Test PASSED---");
                return;
            }
        } catch (Throwable e) {
            fail("Should raise IllegalStateException");
        }
    }

    public final void testUpdateByteArrayIntInt028()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BouncyCastle not installed. Test \"testUpdateByteArrayIntInt028\" was not excecuted.");
            return;
        }
        byte[] input = { 50, -102, 8, -75, -43, 17, -66, 59, 65, -116, -50,
                -67, -36, 88, -94, 115, -117 };
        int offset = 3;
        int length = 9;
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("testUpdateByteArrayIntInt028");
        }
        try {
            mac.update(input, offset, length);
            fail("Should raise IllegalStateException");
        } catch (IllegalStateException e) {
            if (logtestUpdateByteArrayIntInt) {
                System.out
                        .println("Raised java.lang.IllegalStateException: MAC not initialized");
                System.out.println("---Test PASSED---");
                return;
            }
        } catch (Throwable e) {
            fail("Should raise IllegalStateException");
        }
    }

    public final void testUpdateByteArrayIntInt029()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BouncyCastle not installed. Test \"testUpdateByteArrayIntInt029\" was not excecuted.");
            return;
        }
        byte[] input = { 62, -93, 48, 78, -7, -1, -128, 43, 91, -13, 0, 46, 74,
                37, -53, 42, 115, 96, -53, -76, 123, 88, 55 };
        int offset = 1;
        int length = 22;
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("testUpdateByteArrayIntInt029");
        }
        try {
            mac.update(input, offset, length);
            fail("Should raise IllegalStateException");
        } catch (IllegalStateException e) {
            if (logtestUpdateByteArrayIntInt) {
                System.out
                        .println("Raised java.lang.IllegalStateException: MAC not initialized");
                System.out.println("---Test PASSED---");
                return;
            }
        } catch (Throwable e) {
            fail("Should raise IllegalStateException");
        }
    }

    public final void testUpdateByteArrayIntInt030()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BouncyCastle not installed. Test \"testUpdateByteArrayIntInt030\" was not excecuted.");
            return;
        }
        byte[] input = { 26, -37, 79, 36, -98, -83, 12, -48, -15, 42, -56, 108,
                -122, 101, 112, 52, 13, -53, -55, 62, -8, 6, -79, 22, -106,
                -125, 74, 51, -50, 91, -90, -62, 82, -52, -65, -47, -91, 68,
                -88, 1 };
        int offset = 5;
        int length = 36;
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteArrayIntInt) {
            System.out.println("testUpdateByteArrayIntInt030");
        }
        try {
            mac.update(input, offset, length);
            fail("Should raise IllegalStateException");
        } catch (IllegalStateException e) {
            if (logtestUpdateByteArrayIntInt) {
                System.out
                        .println("Raised java.lang.IllegalStateException: MAC not initialized");
                System.out.println("---Test PASSED---");
                return;
            }
        } catch (Throwable e) {
            fail("Should raise IllegalStateException");
        }
    }

    /*
     * Test method for 'javax.crypto.Mac.update(ByteBuffer)'
     */

    public final void testUpdateByteBuffer005()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer005\" was not excecuted.");
            return;
        }
        byte[] input = { 91, -20, -62, -55, 68, 64, -125, -69, 28, -26, 8, -83,
                34, 46, -16, 65, 76, -7, 92 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        String hash = "[41, -56, -9, -58, 92, -67, -1, -57, 59, -113, -11, -82, 79, 1, -25, 59]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer005");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(inputByteBuffer);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteBuffer) {
            System.out
                    .println("-> expected result: [41, -56, -9, -58, 92, -67, -1, -57, 59, -113, -11, -82, 79, 1, -25, 59]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteBuffer) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteBuffer006()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer006\" was not excecuted.");
            return;
        }
        byte[] input = { -116, -72, -25, 46, -47, 69, -104, -110, -20, -123,
                -92, -96, -94, -38, -65, 32, 61, -2, -6, -33, -99, 3, -72, 18,
                95, -79, 7, 66, -12, -23, -63, 99, -81, 20, 49, 24, -110, 45,
                -79, 3, 109, 115, 124, 82, 57, 92, 75, -47, -42 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        String hash = "[11, -44, -55, 18, -64, -18, -36, 3, -38, 62, -63, 110, -85, -114, 68, 82]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer006");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(inputByteBuffer);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteBuffer) {
            System.out
                    .println("-> expected result: [11, -44, -55, 18, -64, -18, -36, 3, -38, 62, -63, 110, -85, -114, 68, 82]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteBuffer) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteBuffer007()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer007\" was not excecuted.");
            return;
        }
        byte[] input = { -73, 15, 94, 92, 40, 78, -1, -3, 31, 87, -34, 55, 10,
                126, -40, -106, 93, -113, 44, -31, -103, -88, -30, 76, 23,
                -128, -114, -36, -39, -26, 120, -47, 108, -7, 117, -41, -38,
                -76, -56, -127, 12, 107, 43, -78, 118, -16, -54, 69 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        String hash = "[49, 25, -77, 40, 100, -74, -26, -44, 64, -90, 95, -36, 69, -104, 62, 107]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer007");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(inputByteBuffer);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteBuffer) {
            System.out
                    .println("-> expected result: [49, 25, -77, 40, 100, -74, -26, -44, 64, -90, 95, -36, 69, -104, 62, 107]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteBuffer) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteBuffer008()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer008\" was not excecuted.");
            return;
        }
        byte[] input = { 0, 63, -128, 25, 4, -38, -84, 58, 3, 121, 103, -18,
                24, -61, 34, 102, -72, 77, -118, -34, -109, 91, -103, -93, -38,
                -10, 75, 81, 74, 12, 26, -2, 106, -56, -96, -115, 26, -41, 9,
                -19, -109, 101, 63, 37, -37 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        String hash = "[114, -71, 38, 53, 33, 127, 114, -118, -61, -61, 43, -1, -71, 77, -4, 26]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer008");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(inputByteBuffer);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteBuffer) {
            System.out
                    .println("-> expected result: [114, -71, 38, 53, 33, 127, 114, -118, -61, -61, 43, -1, -71, 77, -4, 26]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteBuffer) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteBuffer009()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer009\" was not excecuted.");
            return;
        }
        byte[] input = { 13, 36, -79, -50, -37, -25, -42, 109, -13, 81, -42,
                -113, -60, 25, 56, 47, -14, -96, 92, 122, -116, 19, 51, -95,
                50, 58, 73, -83, -97, 121, -79, 26, 115, 27, -90, -40, -37,
                -96, -30 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        String hash = "[-5, 3, -126, -5, -23, -83, 60, -69, 31, 82, -55, 10, -77, -108, 59, -90]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer009");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(inputByteBuffer);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteBuffer) {
            System.out
                    .println("-> expected result: [-5, 3, -126, -5, -23, -83, 60, -69, 31, 82, -55, 10, -77, -108, 59, -90]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteBuffer) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteBuffer010()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer010\" was not excecuted.");
            return;
        }
        byte[] input = { 52, -5, 19, 5, -104, -26, -9, 97, -57, 24, 39, 77,
                -63, 11, -39, -25, 27, 43, 108, 64, -48, -54, -69, 47 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        String hash = "[-100, -51, -36, 15, 59, -21, 114, 82, 51, 92, -110, -128, 92, 116, 5, 52]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer010");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(inputByteBuffer);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteBuffer) {
            System.out
                    .println("-> expected result: [-100, -51, -36, 15, 59, -21, 114, 82, 51, 92, -110, -128, 92, 116, 5, 52]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteBuffer) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteBuffer011()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer011\" was not excecuted.");
            return;
        }
        byte[] input = { -49, 21, -16, -32, 70, -71, 108, -35, 31, -83, -30,
                46, 15, -1, 45, 29, 103, 86, 80, -88, -60, 30, 22 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        String hash = "[68, -33, 74, -25, 80, 31, 82, -53, -71, -79, 123, 10, -101, 93, -118, -12]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer011");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(inputByteBuffer);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteBuffer) {
            System.out
                    .println("-> expected result: [68, -33, 74, -25, 80, 31, 82, -53, -71, -79, 123, 10, -101, 93, -118, -12]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteBuffer) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteBuffer012()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer012\" was not excecuted.");
            return;
        }
        byte[] input = { 9, 118, -125, 110, -98, -121, -109, 2, 22, -53, 51,
                100, 123, 89, -72, 110, 48, 79, 46, 62, 17, -8, -115, -66, -95,
                110, -68, -108, 119, 20, 115, -14, -41, 95, 117, -6, 50, 32,
                110, -71, -78, -68, -118, 35 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        String hash = "[3, -3, 2, 111, 88, 121, -24, -104, 0, -21, 120, -37, -50, 6, 59, 90]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer012");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(inputByteBuffer);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteBuffer) {
            System.out
                    .println("-> expected result: [3, -3, 2, 111, 88, 121, -24, -104, 0, -21, 120, -37, -50, 6, 59, 90]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteBuffer) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteBuffer013()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer013\" was not excecuted.");
            return;
        }
        byte[] input = { 92, 121, -56, 53, -97, -75, -127, -75, -25, 50, -88,
                -94, -36, 70, -4, 127, -108, 118, -50, 23, 35, 14, -95, -74,
                99, -39, -17 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        String hash = "[-106, 82, 65, -72, -84, -4, -126, 107, -76, -99, -2, 6, 14, 103, 78, 121]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer013");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(inputByteBuffer);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteBuffer) {
            System.out
                    .println("-> expected result: [-106, 82, 65, -72, -84, -4, -126, 107, -76, -99, -2, 6, 14, 103, 78, 121]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteBuffer) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteBuffer014()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer014\" was not excecuted.");
            return;
        }
        byte[] input = { 31, 29, 112, -32, -40, -10, -42, -91, -29, -121, -98,
                117, -95, 10, 69, -5, 54 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        String hash = "[-67, -73, 10, 124, -106, 72, -14, -103, -60, -75, 71, 49, -119, 107, 99, -24]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer014");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(inputByteBuffer);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteBuffer) {
            System.out
                    .println("-> expected result: [-67, -73, 10, 124, -106, 72, -14, -103, -60, -75, 71, 49, -119, 107, 99, -24]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteBuffer) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteBuffer015()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer015\" was not excecuted.");
            return;
        }
        byte[] input = { -6, -54, 59, 102, -112, -64, -115, -27, 78, -41, -119,
                -115, 11, 117, -60, 1, -38, 91, -128, 40, 112, -119, -23, -72,
                84, 36, 120, 18, -34, 26, -84, -89, 7, -109, -84, 47, 14, -125,
                60 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        String hash = "[30, -39, -99, 121, -81, -15, 35, 38, 20, 79, -40, 5, 62, 31, -80, -17]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer015");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(inputByteBuffer);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteBuffer) {
            System.out
                    .println("-> expected result: [30, -39, -99, 121, -81, -15, 35, 38, 20, 79, -40, 5, 62, 31, -80, -17]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteBuffer) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteBuffer016()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer016\" was not excecuted.");
            return;
        }
        byte[] input = { -16, -120, -6, -52, -28, 26, -83, 46, 2, 104, 7, -73,
                124, -40, 53, 102, -24 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        String hash = "[-94, -64, -47, 14, -16, 97, 54, 3, -48, -75, -100, 23, -96, -69, -121, 8]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer016");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(inputByteBuffer);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteBuffer) {
            System.out
                    .println("-> expected result: [-94, -64, -47, 14, -16, 97, 54, 3, -48, -75, -100, 23, -96, -69, -121, 8]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteBuffer) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteBuffer017()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer017\" was not excecuted.");
            return;
        }
        byte[] input = { 48, 91, 78, -62, -58, -70, -11, 86, 12, -127, 24, 68,
                -20, 66, 12, 5, 95, -120, -104, -23, -27, -81, -71, -11, -72,
                48, 85, 22, 96, -6, -48, -1, 42, 84, -6, -79, 90, -56, 44, 10,
                -55, 39, -2, -35 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        String hash = "[70, -68, -39, 106, 58, -72, -58, -3, -91, 90, 89, 66, -99, 68, -24, -60]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer017");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(inputByteBuffer);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteBuffer) {
            System.out
                    .println("-> expected result: [70, -68, -39, 106, 58, -72, -58, -3, -91, 90, 89, 66, -99, 68, -24, -60]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteBuffer) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteBuffer018()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer018\" was not excecuted.");
            return;
        }
        byte[] input = { 57, -124, 25, -17, -47, -98, 53, -102, 24, 96, -98,
                97, -125, 112, 48 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        String hash = "[-25, -88, 16, -115, 116, 13, 56, -80, -8, -108, -127, 127, 1, 55, -119, 47]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer018");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(inputByteBuffer);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteBuffer) {
            System.out
                    .println("-> expected result: [-25, -88, 16, -115, 116, 13, 56, -80, -8, -108, -127, 127, 1, 55, -119, 47]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteBuffer) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteBuffer019()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer019\" was not excecuted.");
            return;
        }
        byte[] input = { -88, -96, -29, -41, 87, 1, -110, -61, -122, 41, -109,
                -107, -95, 81, 25, 76, 84, -87, -89, 2, 125, 26, -50, 114, 51,
                126, 62, 105, -71, 60, -98, 115, 54, 53, 53, 105, 113, -78, 43,
                90, 82, 54 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        String hash = "[6, -15, 116, -113, 86, -68, 77, -72, 27, -18, -34, 34, 89, 29, 84, -127]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer019");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(inputByteBuffer);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteBuffer) {
            System.out
                    .println("-> expected result: [6, -15, 116, -113, 86, -68, 77, -72, 27, -18, -34, 34, 89, 29, 84, -127]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteBuffer) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteBuffer020()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer020\" was not excecuted.");
            return;
        }
        byte[] input = { -87, 2, -22, -13, 22, 124, -127, 108, -33, 20, 117,
                76, 40, -10, -97, -55, 57, -14, 77, -64, 61, -7, 36, 0, -63,
                111, 65, -53, 49, 70, 105 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        String hash = "[29, -114, -17, 107, 96, -36, 55, 44, 57, 69, 64, -26, -49, -25, -20, 76]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer020");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(inputByteBuffer);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteBuffer) {
            System.out
                    .println("-> expected result: [29, -114, -17, 107, 96, -36, 55, 44, 57, 69, 64, -26, -49, -25, -20, 76]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteBuffer) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteBuffer021()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer021\" was not excecuted.");
            return;
        }
        byte[] input = { 68, 6, 46, 22, 99, 69, 89, 10, -51, -8, 42, -96, 98,
                -77, 113, 97, -41, -98, -69, 122, 58, 26, 99, -50, -95, 37,
                -70, -8, 6, -117, -32, -57, 105, 110, -110, -104, -125, 79,
                -116, 75, 101, -46, -29, -30, -57, -115 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        String hash = "[-69, -34, 66, -105, -102, 119, 2, 118, -116, 126, -77, 26, -45, -42, 10, -6]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer021");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(inputByteBuffer);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteBuffer) {
            System.out
                    .println("-> expected result: [-69, -34, 66, -105, -102, 119, 2, 118, -116, 126, -77, 26, -45, -42, 10, -6]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteBuffer) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteBuffer022()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer022\" was not excecuted.");
            return;
        }
        byte[] input = { -82, -58, -9, -58, 19, 99, 24, 56, 10, 28, -6, 54, 95,
                -127, -24, 60, 96, -27, -101 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        String hash = "[123, 68, -80, 123, -123, -44, -116, 9, -68, -116, -88, 92, 29, -85, -75, 43]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer022");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(inputByteBuffer);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteBuffer) {
            System.out
                    .println("-> expected result: [123, 68, -80, 123, -123, -44, -116, 9, -68, -116, -88, 92, 29, -85, -75, 43]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteBuffer) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteBuffer023()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer023\" was not excecuted.");
            return;
        }
        byte[] input = { -6, -47, -23, -107, -86, 32, 58, -58, 92, 90, 40, -9,
                114, 41, -69, -66, 88, 123, -116, 99, -82, 69, -79, 79, -2,
                -89, -102 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        String hash = "[104, -84, 107, -123, -79, 21, -78, 12, 57, -88, -50, 62, 7, -92, 49, 8]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer023");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(inputByteBuffer);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteBuffer) {
            System.out
                    .println("-> expected result: [104, -84, 107, -123, -79, 21, -78, 12, 57, -88, -50, 62, 7, -92, 49, 8]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteBuffer) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteBuffer024()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer024\" was not excecuted.");
            return;
        }
        byte[] input = { -21, -118, 103, -83, 105, -62, -13, 85, 21, 39, -94,
                13, -35, 19, -101, 43, 96, 36, 38, 37, -14, 56, 40, -116, 34,
                32, 117, -44 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        String hash = "[79, -79, 96, 66, 93, -124, 84, 91, 34, 90, 66, -32, 91, 36, 8, -91]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer024");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(inputByteBuffer);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteBuffer) {
            System.out
                    .println("-> expected result: [79, -79, 96, 66, 93, -124, 84, 91, 34, 90, 66, -32, 91, 36, 8, -91]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteBuffer) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteBuffer025()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer025\" was not excecuted.");
            return;
        }
        byte[] input = { -105, 96, 104, -103, -77, 65, -63, 53, 42, 1, -91, -9,
                103, -128, -37, 70, -75, -92, 15, 50, 120, 47, 19, 11, -81, -7,
                -19, 100, -44, 36, -64, 91, 126, -46, -20 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        String hash = "[-3, -118, -73, -33, 112, -87, 27, -61, -95, -76, 5, -38, -80, -57, -121, 27]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer025");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(inputByteBuffer);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteBuffer) {
            System.out
                    .println("-> expected result: [-3, -118, -73, -33, 112, -87, 27, -61, -95, -76, 5, -38, -80, -57, -121, 27]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteBuffer) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteBuffer026()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer026\" was not excecuted.");
            return;
        }
        byte[] input = { 1, -123, 117, -103, -59, -67, 31, 125, -37, -70, 76,
                84, -27, 25, 94, 0, 101, -2, -38, 43, -10, -19, 67, 113, 25,
                -43, 103, -38, -46, -14, 118, 101, 93, 21, 12, -81, 0, -119,
                41, 30, 34 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        String hash = "[-11, -67, -14, -122, 37, -109, -77, -56, -82, -1, 16, -115, 19, 45, 56, -6]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer026");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(inputByteBuffer);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteBuffer) {
            System.out
                    .println("-> expected result: [-11, -67, -14, -122, 37, -109, -77, -56, -82, -1, 16, -115, 19, 45, 56, -6]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteBuffer) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteBuffer027()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer027\" was not excecuted.");
            return;
        }
        byte[] input = { -51, -75, -121, -101, -30, -41, -51, -86, -19, 82, 16,
                -108, -13, -23, -25, -89, -13, 49, -55, -4, 67, -47, 55, 58, 47 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        String hash = "[-86, 72, -37, 97, -79, -42, 39, -33, -103, 46, 35, -117, -68, 91, -5, 116]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer027");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(inputByteBuffer);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteBuffer) {
            System.out
                    .println("-> expected result: [-86, 72, -37, 97, -79, -42, 39, -33, -103, 46, 35, -117, -68, 91, -5, 116]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteBuffer) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteBuffer028()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer028\" was not excecuted.");
            return;
        }
        byte[] input = { -2, 98, 69, 91, 77, 60, -80, 81, 83, -121, 74, -117,
                -111, 48, 0, 91, 21, 30, 126, -10, 106, -57, 20, 119, 19, 123,
                60, -90, 10, -73, 55, -113, -27, -2, -101, -48, -18, -77, -1,
                -38 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        String hash = "[37, 81, 20, 56, 18, 59, -51, 13, -26, 107, -123, -34, -22, -70, 20, 41]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer028");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(inputByteBuffer);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteBuffer) {
            System.out
                    .println("-> expected result: [37, 81, 20, 56, 18, 59, -51, 13, -26, 107, -123, -34, -22, -70, 20, 41]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteBuffer) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteBuffer029()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer029\" was not excecuted.");
            return;
        }
        byte[] input = { 41, 72, -43, 51, -80, 113, -102, -101, -92, 38, 113,
                49, -64, -15, 110, -49, -102, 54, 8, 22, 38, -82, 118, 101, 55,
                -59, 110, 108, 121, -112, -100, -64, 49, -16, -96, -76, -27,
                -25 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        String hash = "[-69, 7, -90, 81, -4, 43, 74, 15, 119, 56, -12, -90, -70, -120, 53, 58]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer029");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(inputByteBuffer);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteBuffer) {
            System.out
                    .println("-> expected result: [-69, 7, -90, 81, -4, 43, 74, 15, 119, 56, -12, -90, -70, -120, 53, 58]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteBuffer) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteBuffer030()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer030\" was not excecuted.");
            return;
        }
        byte[] input = { 92, -90, -32, 73, -100, -3, 101, 109, -58, -73, 104,
                20, 103, -109, 20, 6, 124, -92, -34, 12, -115, -107, 90, -122,
                -48, 94, 80, -90, -73, -29, 58, -51, 31, -51, 126, 43, -113,
                -100, 106 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        String hash = "[23, -110, -100, 77, 23, -42, 58, -27, 71, -87, -123, -21, 110, 58, -88, -91]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer030");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(inputByteBuffer);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByteBuffer) {
            System.out
                    .println("-> expected result: [23, -110, -100, 77, 23, -42, 58, -27, 71, -87, -123, -21, 110, 58, -88, -91]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestUpdateByteBuffer) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testUpdateByteBuffer031()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer031\" was not excecuted.");
            return;
        }
        byte[] input = { -28, 51, 46, -108, 16, 86, -29, 117, -124, -127, -55,
                37, 78, 58, 10, -127, -36, 122, 78, 69, -31, 12, -104, -50,
                -31, -17, 26, 101, 96, 113, -46, 48, -21, -121, 2 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer031");
        }
        try {
            mac.update(inputByteBuffer);
            fail("Should raise IllegalStateException");
        } catch (IllegalStateException e) {
            if (logtestUpdateByteBuffer) {
                System.out
                        .println("Raised java.lang.IllegalStateException: MAC not initialized");
                System.out.println("---Test PASSED---");
                return;
            }
        } catch (Throwable e) {
            fail("Should raise IllegalStateException");
        }
    }

    public final void testUpdateByteBuffer032()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer032\" was not excecuted.");
            return;
        }
        byte[] input = { -84, 42, -46, 19, 120, -49, -62, -27, -19, -12, -42,
                -12, 108, -126, -58, -109, 118, 77, -28, 24, 60, 81, -60, 16,
                10, 87, 61, 123, 115, -105, -73, -97, 35, -114, 93, 8, 124,
                -72, 42, 105, 59, 13, -111, -102, -78, 33, -27, -75, 62 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer032");
        }
        try {
            mac.update(inputByteBuffer);
            fail("Should raise IllegalStateException");
        } catch (IllegalStateException e) {
            if (logtestUpdateByteBuffer) {
                System.out
                        .println("Raised java.lang.IllegalStateException: MAC not initialized");
                System.out.println("---Test PASSED---");
                return;
            }
        } catch (Throwable e) {
            fail("Should raise IllegalStateException");
        }
    }

    public final void testUpdateByteBuffer033()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer033\" was not excecuted.");
            return;
        }
        byte[] input = { 97, 90, 19, 41, 111, 113, -101, -76, 72, 118, 28, 64,
                -7, 26, -92, 88, -124, 81, -88, 46, -113, 75, 47, 57, 1, -114,
                -36, 94, -63, 120, -96, -92, -110, -81, 116, 96 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer033");
        }
        try {
            mac.update(inputByteBuffer);
            fail("Should raise IllegalStateException");
        } catch (IllegalStateException e) {
            if (logtestUpdateByteBuffer) {
                System.out
                        .println("Raised java.lang.IllegalStateException: MAC not initialized");
                System.out.println("---Test PASSED---");
                return;
            }
        } catch (Throwable e) {
            fail("Should raise IllegalStateException");
        }
    }

    public final void testUpdateByteBuffer034()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testUpdateByteBuffer034\" was not excecuted.");
            return;
        }
        byte[] input = { -91, -29, 70, 5, 38, 9, 45, 5, 26, -23, -108, -42, -1,
                14, -47, -3, 99, -79 };
        ByteBuffer inputByteBuffer = ByteBuffer.wrap(input);
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestUpdateByteBuffer) {
            System.out.println("testUpdateByteBuffer034");
        }
        try {
            mac.update(inputByteBuffer);
            fail("Should raise IllegalStateException");
        } catch (IllegalStateException e) {
            if (logtestUpdateByteBuffer) {
                System.out
                        .println("Raised java.lang.IllegalStateException: MAC not initialized");
                System.out.println("---Test PASSED---");
                return;
            }
        } catch (Throwable e) {
            fail("Should raise IllegalStateException");
        }
    }

    public final void testUpdateByteBufferIllegalStateException() {
        try {
            // inputBuffer=inputBuffer.wrap(inputArray);
            inputBuffer.clear();
            inputArray = new byte[inputBuffer.capacity()];
            inputBuffer.put(inputArray, 0, inputArray.length);
            mac.update(inputBuffer);
            fail("Should raise IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'javax.crypto.Mac.doFinal()'
     */

    public final void testDoFinal001() {
        try {
            KeyGenerator kg = KeyGenerator.getInstance(algorithm);
            key = kg.generateKey();
            String input = "Move the package to the right position";

            mac = Mac.getInstance(algorithm, "BC");
            mac2 = Mac.getInstance(algorithm, "SunJCE");

            mac.init(key);
            mac2.init(key);

            mac.update(Util.toByteArray(input));
            mac2.update(Util.toByteArray(input));

            byte[] output1 = new byte[mac.getMacLength()];
            byte[] output2 = new byte[mac2.getMacLength()];

            output1 = mac.doFinal();
            output2 = mac2.doFinal();

//            System.out.println("Input: " + input);
//            System.out.println("Algorithm: " + mac.getAlgorithm()
//                    + " Provider: " + mac.getProvider());
//            System.out.println("MAC: " + java.util.Arrays.toString(output1));
//            System.out.println("Algorithm: " + mac2.getAlgorithm()
//                    + " Provider: " + mac2.getProvider());
//            System.out.println("MAC: " + java.util.Arrays.toString(output2));

            for (int i = 0; i < mac2.getMacLength(); i++) {
                assertEquals(output1[i], output2[i]);
            }

        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testDoFinal002() {
        try {
            byte[] output1 = new byte[16];
            byte[] output2 = new byte[16];
            mac = Mac.getInstance("HmacPBESHA1");
            String pwd = "grehasionh bnestrhgoiarenhbtkalht";
            PBEKeySpec keySpec = new PBEKeySpec(pwd.toCharArray());
            SecretKeyFactory kf = SecretKeyFactory.getInstance("PBE");
            SecretKey skey = kf.generateSecret(keySpec);
            byte[] salt = new byte[] { (byte) 0x7d, (byte) 0x60, (byte) 0x43,
                    (byte) 0x5f, (byte) 0x02, (byte) 0xe9, (byte) 0xe0,
                    (byte) 0xae };
            PBEParameterSpec pbe = new PBEParameterSpec(salt, 1000);
            mac.init(skey, pbe);
            output1 = mac.doFinal();
            input = (byte) 0xc7;
            mac.update(input);
            mac.doFinal();
            output2 = mac.doFinal();

            for (int i = 0; i < 16; i++) {
                assertEquals(output1[i], output2[i]);
            }

        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }


    public final void testDoFinalIllegalStateException() {
        try {
            mac.doFinal();
            fail("Should raise IllegalStateException");
        } catch (IllegalStateException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'javax.crypto.Mac.doFinal(byte[], int)'
     */



    /*
     * Test method for 'javax.crypto.Mac.doFinal(byte[], int) that throws an
     * IllegalStateException'
     */

    public final void testDoFinalByteArrayIntIllegalStateException() {

        try {
            mac2 = mac;
            byte[] output = new byte[30];
            int outOffset = 3;
            mac.doFinal();
            mac.doFinal(output, outOffset);
            fail("Should raise IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'javax.crypto.Mac.doFinal(byte[])'
     */



    public final void testDoFinalByteArrayIllegalStateException() {
        try {
            byte[] inputArray = { (byte) 0xc7, (byte) 0x85, (byte) 0x21,
                    (byte) 0x8c, (byte) 0x7e, (byte) 0xc8, (byte) 0xff,
                    (byte) 0x99 };
            mac.doFinal(inputArray);
            fail("Should raise IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'javax.crypto.Mac.clone()
     */

    public final void testClone004() {
        try {
            assertNotNull(mac.clone());
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }

    public final void testCloneCloneNotSupportedException() {
        try {
            algorithm = "HMACSHA384";
            kg = KeyGenerator.getInstance(algorithm);
            key = kg.generateKey();
            algorithm = "SKIPJACKMAC";
            mac = Mac.getInstance(key.getAlgorithm());
            mac.clone().getClass();
        } catch (CloneNotSupportedException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    boolean logtestUpdateByteBuffer = false;

    boolean logtestDoFinalByte = false;

    /*
     * TODO Falta realizar bien la inicializacion del mac y ver que el input no
     * sea null
     */

    public final void testDoFinalByte025() throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testDoFinalByte025\" was not excecuted.");
            return;
        }
        byte[] input = null;
        String hash = "[56, 125, 32, -105, 123, 17, 32, -30, 22, -85, -63, 10, -1, 111, 78, 32]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        mac.init(key);
        if (logtestDoFinalByte) {
            System.out.println("testDoFinalByte025");
        }
        byte[] result = mac.doFinal(input);
        if (logtestDoFinalByte) {
            System.out
                    .println("-> expected result: [56, 125, 32, -105, 123, 17, 32, -30, 22, -85, -63, 10, -1, 111, 78, 32]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestDoFinalByte) {
            System.out.println("---Test PASSED---");
        }
    }

    /*
     * Test method for 'javax.crypto.Mac.reset()'
     */



    /*
     * Test method for doFinal(Byte[] output,int outOffset)'
     */

    boolean logtestDoFinalByteArrayInt = false;

    public final void testDoFinalByteArrayInt006()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, ShortBufferException, IllegalStateException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testDoFinalByteArrayInt006\" was not excecuted.");
            return;
        }
        byte[] input = { 79, -23, 30, 91, -94, -119, 89, -12, 92, -67, 35, 73,
                -3, -107, -80, 112, -39, -6, -31 };
        int outOffset = 0;
        String hash = "[23, 123, 69, 13, -20, -66, 13, 90, -24, -53, -106, 19, 71, 3, -109, -76]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestDoFinalByteArrayInt) {
            System.out.println("testDoFinalByteArrayInt006");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = new byte[16];
        mac.doFinal(result, outOffset);
        if (logtestDoFinalByteArrayInt) {
            System.out
                    .println("-> expected result: [23, 123, 69, 13, -20, -66, 13, 90, -24, -53, -106, 19, 71, 3, -109, -76]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestDoFinalByteArrayInt) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testDoFinalByteArrayInt007()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, ShortBufferException, IllegalStateException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testDoFinalByteArrayInt007\" was not excecuted.");
            return;
        }
        byte[] input = { -119, 74, -1, 100, 87, -104, -81, 30, 55, -114, -101,
                116, -25, 9, 44, -82, 52, -1, 66, 120, 58, 73, 100, 17, -49,
                -12, 34, -120, 59, -32 };
        int outOffset = 15;
        String hash = "[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -80, -124, -121, 27, 55, 113, 72, 94, -95, 13, -24, -4, 89, 86, -36, -61, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestDoFinalByteArrayInt) {
            System.out.println("testDoFinalByteArrayInt007");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = new byte[48];
        mac.doFinal(result, outOffset);
        if (logtestDoFinalByteArrayInt) {
            System.out
                    .println("-> expected result: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -80, -124, -121, 27, 55, 113, 72, 94, -95, 13, -24, -4, 89, 86, -36, -61, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestDoFinalByteArrayInt) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testDoFinalByteArrayInt008()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, ShortBufferException, IllegalStateException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testDoFinalByteArrayInt008\" was not excecuted.");
            return;
        }
        byte[] input = { -117, -74, 57, -77, 98, 81, 5, 115, 100, 32, -126, 14,
                69, 119, -43, 39, 13, -51, 13, 49, -103, -119, 108, 8, 116,
                -128, -11, 47, 92, -53, -109, 91, 98, -17 };
        int outOffset = 33;
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestDoFinalByteArrayInt) {
            System.out.println("testDoFinalByteArrayInt008");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);

        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        try {

            byte[] result = new byte[48];
            mac.doFinal(result, outOffset);
            fail("Should raise ShortBufferException");
        } catch (ShortBufferException e) {
            if (logtestDoFinalByteArrayInt) {
                System.out
                        .println("Raised javax.crypto.ShortBufferException: Cannot store MAC in output buffer");
                System.out.println("---Test PASSED---");
                return;
            }
        } catch (Throwable e) {
            fail("Should raise ShortBufferException");
        }
    }

    public final void testDoFinalByteArrayInt009()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, ShortBufferException, IllegalStateException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testDoFinalByteArrayInt009\" was not excecuted.");
            return;
        }
        byte[] input = { 53, -79, -62, 44, -8, -27, 114, 114, 42, -89, 93, 105,
                -48, -122, 8, -9, 35, -101, 57, 80, 66, 72, -20, -110 };
        int outOffset = 40;
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestDoFinalByteArrayInt) {
            System.out.println("testDoFinalByteArrayInt009");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        try {

            byte[] result = new byte[48];
            mac.doFinal(result, outOffset);
            fail("Should raise ShortBufferException");
        } catch (ShortBufferException e) {
            if (logtestDoFinalByteArrayInt) {
                System.out
                        .println("Raised javax.crypto.ShortBufferException: Cannot store MAC in output buffer");
                System.out.println("---Test PASSED---");
                return;
            }
        } catch (Throwable e) {
            fail("Should raise ShortBufferException");
        }
    }

    public final void testDoFinalByteArrayInt010()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, ShortBufferException, IllegalStateException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testDoFinalByteArrayInt010\" was not excecuted.");
            return;
        }
        byte[] input = { -44, 124, 58, 122, 72, -1, 113, 23, -80, -4, -51, 24,
                -127, -81, -80, 83, -14, -102, 46, 80, 40, 87, 80, 48, -71,
                -56, 0, 6, -50, -93, 39, 11, 50, -127, -104, -84, -50, -88,
                -37, -80, 11, -125, -22, -62, -15 };
        int outOffset = 7;
        String hash = "[0, 0, 0, 0, 0, 0, 0, -106, -45, 54, 42, 69, 103, 60, -111, -83, 59, -98, -106, -51, 11, -69, -25, 0, 0, 0, 0, 0, 0, 0, 0, 0]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestDoFinalByteArrayInt) {
            System.out.println("testDoFinalByteArrayInt010");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = new byte[32];
        mac.doFinal(result, outOffset);
        if (logtestDoFinalByteArrayInt) {
            System.out
                    .println("-> expected result: [0, 0, 0, 0, 0, 0, 0, -106, -45, 54, 42, 69, 103, 60, -111, -83, 59, -98, -106, -51, 11, -69, -25, 0, 0, 0, 0, 0, 0, 0, 0, 0]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestDoFinalByteArrayInt) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testDoFinalByteArrayInt011()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, ShortBufferException, IllegalStateException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testDoFinalByteArrayInt011\" was not excecuted.");
            return;
        }
        byte[] input = { -67, -32, 20, 31, -27, -26, -47, 124, 42, -55, 100, 8,
                34, -43, -65, -93, 105, 5, -112, -29, 5, 125, -85, 29, -42, 67,
                86, -89, -118, -128, -110, 16, -15, -56, 57, 85, 14, -36, -111,
                -77, 91, -80, 73, 71, -48, -126 };
        int outOffset = 81;
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestDoFinalByteArrayInt) {
            System.out.println("testDoFinalByteArrayInt011");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        try {
            byte[] result = new byte[96];
            mac.doFinal(result, outOffset);
            fail("Should raise ShortBufferException");
        } catch (ShortBufferException e) {
            if (logtestDoFinalByteArrayInt) {
                System.out
                        .println("Raised javax.crypto.ShortBufferException: Cannot store MAC in output buffer");
                System.out.println("---Test PASSED---");
                return;
            }
        } catch (Throwable e) {
            fail("Should raise ShortBufferException");
        }
    }

    public final void testDoFinalByteArrayInt012()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, ShortBufferException, IllegalStateException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testDoFinalByteArrayInt012\" was not excecuted.");
            return;
        }
        byte[] input = { -114, 33, -58, 125, 44, 16, -32, 92, -49, 34, 28, -91,
                24, 0, -109, 22, 68, 8, -43, 19, -118, 40, -13, -13, -83, 58,
                -57, 125, 46, 0, -113, -28, 107, -53, 31, -83, -52, 62, -77 };
        int outOffset = 77;
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        mac.init(key);
        if (logtestDoFinalByteArrayInt) {
            System.out.println("testDoFinalByteArrayInt012");
        }
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        try {

            byte[] result = new byte[48];
            mac.doFinal(result, outOffset);
            fail("Should raise ShortBufferException");
        } catch (ShortBufferException e) {
            if (logtestDoFinalByteArrayInt) {
                System.out
                        .println("Raised javax.crypto.ShortBufferException: Cannot store MAC in output buffer");
                System.out.println("---Test PASSED---");
                return;
            }
        } catch (Throwable e) {
            fail("Should raise ShortBufferException");
        }
    }

    public final void testDoFinalByteArrayInt013()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, ShortBufferException, IllegalStateException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testDoFinalByteArrayInt013\" was not excecuted.");
            return;
        }
        byte[] input = { -65, 99, -41, -11, -19, 65, 36, -74, -39, 1, 70, 116,
                2, -126, -61, -108, -54, -53 };
        int outOffset = 77;
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        mac.init(key);
        if (logtestDoFinalByteArrayInt) {
            System.out.println("testDoFinalByteArrayInt013");
        }
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        try {

            byte[] result = new byte[48];
            mac.doFinal(result, outOffset);
            fail("Should raise ShortBufferException");
        } catch (ShortBufferException e) {
            if (logtestDoFinalByteArrayInt) {
                System.out
                        .println("Raised javax.crypto.ShortBufferException: Cannot store MAC in output buffer");
                System.out.println("---Test PASSED---");
                return;
            }
        } catch (Throwable e) {
            fail("Should raise ShortBufferException");
        }
    }

    public final void testDoFinalByteArrayInt014()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, ShortBufferException, IllegalStateException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testDoFinalByteArrayInt014\" was not excecuted.");
            return;
        }
        byte[] input = { 67, -114, 116, 80, -48, -1, -70, -64, -33, -2, -6,
                -104, -31, 29, -115, 55, -94, -24, -100, -41, 41, -127, -73,
                -51, 21, 4, -74, 83, 62, 124 };
        int outOffset = 14;
        String hash = "[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 37, 63, 40, -82, -47, 54, 30, 61, -15, 77, 63, -74, -83, -67, -35, 117, 0, 0]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestDoFinalByteArrayInt) {
            System.out.println("testDoFinalByteArrayInt014");
        }
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = new byte[32];
        mac.doFinal(result, outOffset);
        if (logtestDoFinalByteArrayInt) {
            System.out
                    .println("-> expected result: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 37, 63, 40, -82, -47, 54, 30, 61, -15, 77, 63, -74, -83, -67, -35, 117, 0, 0]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestDoFinalByteArrayInt) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testDoFinalByteArrayInt015()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, ShortBufferException, IllegalStateException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testDoFinalByteArrayInt015\" was not excecuted.");
            return;
        }
        byte[] input = { 67, 27, -63, -98, -53, -124, -74, -80, 2, -40, -10,
                -92, -29, -125, -57, 122, 22, 96, -45, -72, -67, 74, 48, -84,
                109, 23, 121, 12, 122, 115, -96, 24, 106, 36, 28 };
        int outOffset = 15;
        String hash = "[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 113, -21, -123, -41, 68, 115, 31, -48, -110, -11, -47, -108, 94, 111, -114, 57, 0]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestDoFinalByteArrayInt) {
            System.out.println("testDoFinalByteArrayInt015");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = new byte[32];
        mac.doFinal(result, outOffset);
        if (logtestDoFinalByteArrayInt) {
            System.out
                    .println("-> expected result: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 113, -21, -123, -41, 68, 115, 31, -48, -110, -11, -47, -108, 94, 111, -114, 57, 0]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestDoFinalByteArrayInt) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testDoFinalByteArrayInt016()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, ShortBufferException, IllegalStateException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testDoFinalByteArrayInt016\" was not excecuted.");
            return;
        }
        byte[] input = { -60, -91, 30, -65, 61, 92, -59, 119, 52, -46, -81, 93,
                10, -89, 61, 94, -60, -6, 75, 73, -22, -56, -98, 31, 40, -70,
                10, -8, -67, -37, -69 };
        int outOffset = 4;
        String hash = "[0, 0, 0, 0, -87, 18, 112, 126, 9, 47, 25, -45, -84, -34, -13, -1, 65, -1, 124, -5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestDoFinalByteArrayInt) {
            System.out.println("testDoFinalByteArrayInt016");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = new byte[32];
        mac.doFinal(result, outOffset);
        if (logtestDoFinalByteArrayInt) {
            System.out
                    .println("-> expected result: [0, 0, 0, 0, -87, 18, 112, 126, 9, 47, 25, -45, -84, -34, -13, -1, 65, -1, 124, -5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestDoFinalByteArrayInt) {
            System.out.println("---Test PASSED---");
        }
    }


    public final void testDoFinalByteArrayInt020()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, ShortBufferException, IllegalStateException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testDoFinalByteArrayInt020\" was not excecuted.");
            return;
        }
        byte[] input = { 107, 105, -127, 33, -3, 119, -69, -84, 2, 115, -97,
                92, -58, 2, 103, -59, -74, 43, 62, 104, -91, -50, 80, -65, 114,
                -26, 91, -27, -30, -93, 45, -66, -45, 116, 47, 58, 37, -62, 40,
                -128, -22, 26, -92, -84, 8, 118, 21, -2, 60 };
        int outOffset = 33;
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        mac.init(key);
        if (logtestDoFinalByteArrayInt) {
            System.out.println("testDoFinalByteArrayInt020");
        }
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        try {

            byte[] result = new byte[48];
            mac.doFinal(result, outOffset);
            fail("Should raise ShortBufferException");
        } catch (ShortBufferException e) {
            if (logtestDoFinalByteArrayInt) {
                System.out
                        .println("Raised javax.crypto.ShortBufferException: Cannot store MAC in output buffer");
                System.out.println("---Test PASSED---");
                return;
            }
        } catch (Throwable e) {
            fail("Should raise ShortBufferException");
        }
    }

    public final void testDoFinalByteArrayInt021()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, ShortBufferException, IllegalStateException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testDoFinalByteArrayInt021\" was not excecuted.");
            return;
        }
        byte[] input = { -23, -9, 115, -97, -18, -74, 13, 49, 19, 29, -73, -40,
                80, -111, 127, -107, -9, 102, 24, -5, -76, 124, 43, -8, 116,
                112, 50, -6, 92, 65, 120, -11, 110, -72, 102, -10, -111, -125,
                121 };
        int outOffset = 33;
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        mac.init(key);
        if (logtestDoFinalByteArrayInt) {
            System.out.println("testDoFinalByteArrayInt021");
        }
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        try {

            byte[] result = new byte[48];
            mac.doFinal(result, outOffset);
            fail("Should raise ShortBufferException");
        } catch (ShortBufferException e) {
            if (logtestDoFinalByteArrayInt) {
                System.out
                        .println("Raised javax.crypto.ShortBufferException: Cannot store MAC in output buffer");
                System.out.println("---Test PASSED---");
                return;
            }
        } catch (Throwable e) {
            fail("Should raise ShortBufferException");
        }
    }

    public final void testDoFinalByteArrayInt022()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, ShortBufferException, IllegalStateException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testDoFinalByteArrayInt022\" was not excecuted.");
            return;
        }
        byte[] input = { -21, 38, -60, -79, 60, -119, 53, 104, -84, 120, -41,
                21, -51, 60, -31, 70, 87, 51, 33, -9, -122, -70, 118, 85, 96,
                79, -40, -17, 121, -97, 117, -7, -41, 55, -17, -37, 5, -69,
                105, -34, 83, 54, -79, 25, -19, -69, -128, -61, 82 };
        int outOffset = 33;
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        mac.init(key);
        if (logtestDoFinalByteArrayInt) {
            System.out.println("testDoFinalByteArrayInt022");
        }
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }

        try {

            byte[] result = new byte[48];
            mac.doFinal(result, outOffset);
            fail("Should raise ShortBufferException");
        } catch (ShortBufferException e) {
            if (logtestDoFinalByteArrayInt) {
                System.out
                        .println("Raised javax.crypto.ShortBufferException: Cannot store MAC in output buffer");
                System.out.println("---Test PASSED---");
                return;
            }
        } catch (Throwable e) {
            fail("Should raise ShortBufferException");
        }
    }

    public final void testDoFinalByteArrayInt023()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, ShortBufferException, IllegalStateException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testDoFinalByteArrayInt023\" was not excecuted.");
            return;
        }
        byte[] input = { 118, 17, 107, -111, -48, 108, -112, 103, 105, -66,
                120, -24, -8, -2, -90, -91, 5, 11, -18, -54, 37, 90, -16, 13,
                47, 94, 31, 23, -84, -13, -80, 114, 120, -87, -54, -28, 15,
                -47, 81, -33, -62, 43, -85, 10, -100, -46, -4, 67 };
        int outOffset = 10;
        String hash = "[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 25, 89, -65, 44, 102, -13, 17, 107, -60, 20, 18, 72, 89, 3, 120, 50, 0, 0, 0, 0, 0, 0]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestDoFinalByteArrayInt) {
            System.out.println("testDoFinalByteArrayInt023");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = new byte[32];
        mac.doFinal(result, outOffset);
        if (logtestDoFinalByteArrayInt) {
            System.out
                    .println("-> expected result: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 25, 89, -65, 44, 102, -13, 17, 107, -60, 20, 18, 72, 89, 3, 120, 50, 0, 0, 0, 0, 0, 0]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestDoFinalByteArrayInt) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testDoFinalByteArrayInt024()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, ShortBufferException, IllegalStateException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testDoFinalByteArrayInt024\" was not excecuted.");
            return;
        }
        byte[] input = { -103, 79, -26, -82, -81, 16, 7, 90, 125, 112, -38, 1,
                -106, -34, 30, 119, -37, -33, -64, 72, 92, -120, -81, 26, -12,
                -106, 33 };
        int outOffset = 2;
        String hash = "[0, 0, 75, 124, -66, 126, 53, -25, -123, -102, -93, -100, -110, 34, 110, 46, -53, -108, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestDoFinalByteArrayInt) {
            System.out.println("testDoFinalByteArrayInt024");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = new byte[32];
        mac.doFinal(result, outOffset);
        if (logtestDoFinalByteArrayInt) {
            System.out
                    .println("-> expected result: [0, 0, 75, 124, -66, 126, 53, -25, -123, -102, -93, -100, -110, 34, 110, 46, -53, -108, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestDoFinalByteArrayInt) {
            System.out.println("---Test PASSED---");
        }
    }



    public final void testDoFinalByteArrayInt026()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, ShortBufferException, IllegalStateException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testDoFinalByteArrayInt026\" was not excecuted.");
            return;
        }
        byte[] input = { -105, 36, -74, -35, -27, 38, -43, 114, 4, -74, -43,
                13, 21, -2, -63, -84, 111, -7, -44, -75, -13, -5, -54, 47, -55,
                19, 44, 75, 13, 53, -44, 63, -62, 120, -80, -120, -83, -35,
                -18, 10, -79 };
        int outOffset = 40;
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        mac.init(key);
        if (logtestDoFinalByteArrayInt) {
            System.out.println("testDoFinalByteArrayInt026");
        }
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }

        try {

            byte[] result = new byte[48];
            mac.doFinal(result, outOffset);
            fail("Should raise ShortBufferException");
        } catch (ShortBufferException e) {
            if (logtestDoFinalByteArrayInt) {
                System.out
                        .println("Raised javax.crypto.ShortBufferException: Cannot store MAC in output buffer");
                System.out.println("---Test PASSED---");
                return;
            }
        } catch (Throwable e) {
            fail("Should raise ShortBufferException");
        }
    }

    public final void testDoFinalByteArrayInt027()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, ShortBufferException, IllegalStateException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testDoFinalByteArrayInt027\" was not excecuted.");
            return;
        }
        byte[] input = { 40, 8, -127, 49, 50, -15, 49, 70, 50, 127, 87, -29,
                -58, 52, -1, 7, 33, -28, 6, -53, -47, -64, -50, -40, 45, -31,
                0, 42, 105, -5, 78, -70, 99, 32, -127, -36, -45, -28, 118, -39 };
        int outOffset = 4;
        String hash = "[0, 0, 0, 0, 97, -22, 61, 123, -57, -92, -81, -64, 69, 102, -125, -128, 67, -82, 26, 47, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestDoFinalByteArrayInt) {
            System.out.println("testDoFinalByteArrayInt027");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = new byte[32];
        mac.doFinal(result, outOffset);
        if (logtestDoFinalByteArrayInt) {
            System.out
                    .println("-> expected result: [0, 0, 0, 0, 97, -22, 61, 123, -57, -92, -81, -64, 69, 102, -125, -128, 67, -82, 26, 47, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestDoFinalByteArrayInt) {
            System.out.println("---Test PASSED---");
        }
    }



    public final void testDoFinalByteArrayInt029()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, ShortBufferException, IllegalStateException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testDoFinalByteArrayInt029\" was not excecuted.");
            return;
        }
        byte[] input = { 66, -83, -69, 52, 59, -37, -90, -80, 25, -84, 74, 121,
                86, 113, -36, -73, 52, 15, 87 };
        int outOffset = 8;
        String hash = "[0, 0, 0, 0, 0, 0, 0, 0, 94, 4, 56, 78, 106, -18, 23, -104, -87, 47, -104, -63, 118, 70, 124, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestDoFinalByteArrayInt) {
            System.out.println("testDoFinalByteArrayInt029");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = new byte[48];
        mac.doFinal(result, outOffset);
        if (logtestDoFinalByteArrayInt) {
            System.out
                    .println("-> expected result: [0, 0, 0, 0, 0, 0, 0, 0, 94, 4, 56, 78, 106, -18, 23, -104, -87, 47, -104, -63, 118, 70, 124, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestDoFinalByteArrayInt) {
            System.out.println("---Test PASSED---");
        }
    }

    public final void testDoFinalByteArrayInt030()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, ShortBufferException, IllegalStateException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testDoFinalByteArrayInt030\" was not excecuted.");
            return;
        }
        byte[] input = { -35, -86, -51, -71, 96, 19, -4, -51, -59, 102, -109,
                -98, -70, -9, -23, 108, 43, -76, -110, -24, 55, 25, -28, 78,
                -50, 51, -62, 86, -84, 26, 8, 6, -116, 110, -12, 77, -47, 32,
                48, 96, 39, -116 };
        int outOffset = 12;
        String hash = "[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 77, -16, 113, -31, -39, -35, -83, -121, -65, 35, 41, 12, -60, -90, 106, -64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]";
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestDoFinalByteArrayInt) {
            System.out.println("testDoFinalByteArrayInt030");
        }

        key = new K("HmacMD5", salt);
        mac.init(key);
        for (int i = 0; i < 100; i++) {
            mac.update(input);
        }
        byte[] result = new byte[48];
        mac.doFinal(result, outOffset);
        if (logtestDoFinalByteArrayInt) {
            System.out
                    .println("-> expected result: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 77, -16, 113, -31, -39, -35, -83, -121, -65, 35, 41, 12, -60, -90, 106, -64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]");
            System.out.println("-> actual result: " + Arrays.toString(result));
        }
        assertEquals(msgNotSame, hash, Arrays.toString(result));
        if (logtestDoFinalByteArrayInt) {
            System.out.println("---Test PASSED---");
        }
    }



    public final void testDoFinalByteArrayInt032()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, ShortBufferException, IllegalStateException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testDoFinalByteArrayInt032\" was not excecuted.");
            return;
        }
        byte[] input = { -111, -91, -97, 106, 108, 10, -11, 50, 58, -124, 5,
                -23, 127, -39, 104, -5, -94, 119, 55, -97, -6, 45, 102, -96,
                77, -25, -35, 68, 38, -94, 82, 115, 25, -60, -96, -19, 44, 6,
                35, -78, 37, -66, 84, 25, -49, -38, -50, -32, -25 };
        int outOffset = 12;
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestDoFinalByteArrayInt) {
            System.out.println("testDoFinalByteArrayInt032");
        }
        try {

            for (int i = 0; i < 100; i++) {
                mac.update(input);
            }
            byte[] result = new byte[48];
            mac.doFinal(result, outOffset);
            fail("Should raise IllegalStateException");
        } catch (IllegalStateException e) {
            if (logtestDoFinalByteArrayInt) {
                System.out
                        .println("Raised java.lang.IllegalStateException: MAC not initialized");
                System.out.println("---Test PASSED---");
                return;
            }
        } catch (Throwable e) {
            fail("Should raise IllegalStateException");
        }
    }

    public final void testDoFinalByteArrayInt033()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, ShortBufferException, IllegalStateException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testDoFinalByteArrayInt033\" was not excecuted.");
            return;
        }
        byte[] input = { 124, -89, 6, 15, 57, -84, 74, -73, 88, 73, 11, 29, 44,
                24, -13, 1, 96, 26, 81, 2, -3, 127, -69, -20, 118, -81, 118,
                -87, 5, 30, 69, -89, -42, -14, -99, -57, 46, -119 };
        int outOffset = 12;
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestDoFinalByteArrayInt) {
            System.out.println("testDoFinalByteArrayInt033");
        }
        try {

            for (int i = 0; i < 100; i++) {
                mac.update(input);
            }
            byte[] result = new byte[48];
            mac.doFinal(result, outOffset);
            fail("Should raise IllegalStateException");
        } catch (IllegalStateException e) {
            if (logtestDoFinalByteArrayInt) {
                System.out
                        .println("Raised java.lang.IllegalStateException: MAC not initialized");
                System.out.println("---Test PASSED---");
                return;
            }
        } catch (Throwable e) {
            fail("Should raise IllegalStateException");
        }
    }

    public final void testDoFinalByteArrayInt034()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, ShortBufferException, IllegalStateException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testDoFinalByteArrayInt034\" was not excecuted.");
            return;
        }
        byte[] input = { 92, -128, 49, 62, -107, -28, 70, -54, 14, -18, -91,
                -93, -127, -83, -27 };
        int outOffset = 12;
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestDoFinalByteArrayInt) {
            System.out.println("testDoFinalByteArrayInt034");
        }
        try {

            for (int i = 0; i < 100; i++) {
                mac.update(input);
            }
            byte[] result = new byte[48];
            mac.doFinal(result, outOffset);
            fail("Should raise IllegalStateException");
        } catch (IllegalStateException e) {
            if (logtestDoFinalByteArrayInt) {
                System.out
                        .println("Raised java.lang.IllegalStateException: MAC not initialized");
                System.out.println("---Test PASSED---");
                return;
            }
        } catch (Throwable e) {
            fail("Should raise IllegalStateException");
        }
    }

    public final void testDoFinalByteArrayInt035()
            throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidKeyException, ShortBufferException, IllegalStateException {
        if (Security.getProvider("BC") == null) {
            System.err
                    .println("Warning: provider BC not installed. Test \"testDoFinalByteArrayInt035\" was not excecuted.");
            return;
        }
        byte[] input = { -34, 15, -10, 23, 49, 82, 118, -26, 41, -77, -52, 102,
                -7, 117, 97, -33, 119, -32 };
        int outOffset = 12;
        Mac mac = Mac.getInstance("HmacMD5", "BC");
        if (logtestDoFinalByteArrayInt) {
            System.out.println("testDoFinalByteArrayInt035");
        }
        try {

            for (int i = 0; i < 100; i++) {
                mac.update(input);
            }
            byte[] result = new byte[96];
            mac.doFinal(result, outOffset);
            fail("Should raise IllegalStateException");
        } catch (IllegalStateException e) {
            if (logtestDoFinalByteArrayInt) {
                System.out
                        .println("Raised java.lang.IllegalStateException: MAC not initialized");
                System.out.println("---Test PASSED---");
                return;
            }
        } catch (Throwable e) {
            fail("Should raise IllegalStateException");
        }
    }



}
