package ar.org.fitc.test.crypto;

import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import junit.framework.TestCase;
import ar.org.fitc.test.util.K;
import ar.org.fitc.test.util.Messages;

/**
 * Junit TestCase container class for Mac test methods
 *
 * @author Osvaldo Demo
 * @version 1.0
 */
public class TestMac extends TestCase implements Messages {

    @SuppressWarnings("unused")
    private byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,
            (byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };

    private KeyGenerator kg = null;

    private Mac mac = null;

    private Key key = null;

    private Provider provider = null;

    private String providerString = "SunJCE";

    private String algorithm = null;

    private byte inputArray[] = null;

    private byte input = (byte) 0xac;

    private int offset = 2;

    private int len = 3;

    private ByteBuffer inputBuffer = ByteBuffer.allocate(8);

    private int macLength;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestMac.class);
    }

    public TestMac(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        try {
//            algorithm = "HmacMD5";
            algorithm = "DES";
            kg = KeyGenerator.getInstance(algorithm);
            kg.init(new SecureRandom());
            key = kg.generateKey();
            mac = Mac.getInstance(key.getAlgorithm());
            provider = mac.getProvider();
            macLength = mac.getMacLength();
        } catch (java.security.NoSuchAlgorithmException e) {
            fail("Failed with:" + e);
        }
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'javax.crypto.Mac.getInstance(String)'
     */

    public final void testGetInstanceString001() {
        try {
            mac = Mac.getInstance(algorithm);
            assertNotNull("Should not be null", mac);
        } catch (NoSuchAlgorithmException e) {
            e.getCause();
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

    /*
     * Test method for 'javax.crypto.Mac.getInstance(String, Provider)'
     */
    public final void testGetInstanceStringProvider001() {
        try {
            mac = Mac.getInstance(algorithm, provider);
            assertNotNull("Should not be null", mac);
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

    public final void testGetAlgorithm002() {
        assertEquals(algorithm, mac.getAlgorithm());
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

    public final void testGetMacLength002() {
        assertEquals("Length for " + mac.getAlgorithm() + " should be "
                + macLength, macLength, mac.getMacLength());
    }

    /*
     * Test method for 'javax.crypto.Mac.init(Key)'
     */
    public final void testInitKey001() {
        try {
            mac.init(key);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }

    /*
     * Test method for 'javax.crypto.Mac.update(byte)'
     */

    /*
     * Test method for 'javax.crypto.Mac.update(byte[])'
     */
    public final void testUpdateByteArray001() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {103, -100, 83, -124, 46, -40, -107, 48, 26, -16, 19, 57, 26, -119, 110, -31, -44, 118, 47, -73, -37, -105, 89, -46, -88, 114, -94, -55, 47, -102, 110, 91, -46, -64, -82, 127, 18, 9, 56, -78, -13, 34};
        String hash = "[124, 51, 18, -86, -69, 4, 34, -27, -16, 95, 90, -13, 24, 96, 92, -66]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray001"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (log) {
            System.out.println("-> expected result: [124, 51, 18, -86, -69, 4, 34, -27, -16, 95, 90, -13, 24, 96, 92, -66]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByteArray002() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {17, 38, 98, -91, 16, 51, 35, -87, 102, -99, 112, 42, 0, -113, 27};
        String hash = "[97, -86, -31, 9, 6, 59, 8, 25, 45, -72, 82, 69, 12, 5, -74, -67]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray002"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (log) {
            System.out.println("-> expected result: [97, -86, -31, 9, 6, 59, 8, 25, 45, -72, 82, 69, 12, 5, -74, -67]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByteArray003() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {-47, 22, 52, 46, 120, -10, -1, 84, -51, 50, 102, -11, -68, 14, 25, -125, -53, -50, -90, -86, 52, -69, -50, -53, 108, 36, -59, 56, -92, -76, 7, 80, -85, 76, -67, 20};
        String hash = "[0, -5, -99, -31, 7, -107, -65, 25, -104, 16, 108, 19, 35, -115, -37, 45]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray003"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (log) {
            System.out.println("-> expected result: [0, -5, -99, -31, 7, -107, -65, 25, -104, 16, 108, 19, 35, -115, -37, 45]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByteArray004() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {-42, -7, 65, -49, -56, 62, 49, 74, -9, 65, -45, 59, 44, -8, -93, 23, 100, -84, 79, 95, 5, -12, -48, -42, 87, 20, 26, 102, -124, -83, 32, -26};
        String hash = "[25, 41, -90, 35, 92, -128, 13, 30, -60, 59, -76, -70, 31, -107, -101, -68]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray004"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (log) {
            System.out.println("-> expected result: [25, 41, -90, 35, 92, -128, 13, 30, -60, 59, -76, -70, 31, -107, -101, -68]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByteArray005() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {35, -27, 118, -95, -69, -128, -16, 4, -10, 105, 125, -77, 67, 69, 76};
        String hash = "[-38, -74, -102, -66, 55, 121, -46, -89, -69, 86, -87, 40, -3, -57, -25, -119]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray005"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (log) {
            System.out.println("-> expected result: [-38, -74, -102, -66, 55, 121, -46, -89, -69, 86, -87, 40, -3, -57, -25, -119]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByteArray006() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {-26, 91, -5, -79, 113, 49, 75, -46, -13, -122, 68, -99, -59, -97, 36, 43, -112, 36, 9, -60, -23, 46, 106, 4, -37, -13, 80, -33, 81, 103, 76};
        String hash = "[74, -36, 75, -29, -28, -10, 50, -114, -69, 55, 96, 61, 15, -126, -94, -44]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray006"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (log) {
            System.out.println("-> expected result: [74, -36, 75, -29, -28, -10, 50, -114, -69, 55, 96, 61, 15, -126, -94, -44]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByteArray007() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {122, -96, 21, 59, -11, 40, -3, 100, -98, 123, 42, 110, -45, -25, 8, 14, -14, -110, 47, 80, 6, 7, 112, 42, 111, -85};
        String hash = "[65, 17, 98, -8, 19, 125, 119, 54, -13, -50, 71, -88, -109, 28, 104, 20]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray007"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (log) {
            System.out.println("-> expected result: [65, 17, 98, -8, 19, 125, 119, 54, -13, -50, 71, -88, -109, 28, 104, 20]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByteArray008() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {40, -84, -40, -40, 79, 95, -74, -69, 85, -26, -4, 33, 59, 14, -87, 96, -37, -92, 71, 46, 124, -31, -12, 57, -98, 13, 87, -64, 51, -32, -26, -77, 52, -71, 119, -105, 12, -19, -67};
        String hash = "[95, 7, 112, 1, -30, -86, -34, -30, 76, 27, 31, 85, -4, 66, -107, 126]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray008"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (log) {
            System.out.println("-> expected result: [95, 7, 112, 1, -30, -86, -34, -30, 76, 27, 31, 85, -4, 66, -107, 126]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByteArray009() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {-102, 8, -108, 85, -64, 44, 59, -124, 41, 73, 68, -62, -66, 93, -4, -91, -90, -125, 54, 114, -45, 33, -34, -115, 74, 18, -118, -8, -29, 125, 56, -69, 8, 101, -38, 16};
        String hash = "[-102, -29, -82, -23, 101, 113, 41, -44, 2, -30, 90, 63, -38, -15, 123, 120]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray009"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (log) {
            System.out.println("-> expected result: [-102, -29, -82, -23, 101, 113, 41, -44, 2, -30, 90, 63, -38, -15, 123, 120]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByteArray010() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {7, -64, -58, -116, -113, 9, 125, 47, -112, 72, -23, -95, -124, 13, -117, 96, 58, -60, 2};
        String hash = "[108, -29, 58, 21, -37, -109, -125, 6, 112, -25, -128, -72, 78, -103, 44, -3]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray010"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (log) {
            System.out.println("-> expected result: [108, -29, 58, 21, -37, -109, -125, 6, 112, -25, -128, -72, 78, -103, 44, -3]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByteArray011() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {38, 80, 83, -90, 78, 49, 71, -55, 123, 102, 84, 13, 73, 92, 93};
        String hash = "[-97, 32, 80, 121, -96, 47, 31, 42, 20, 15, 21, 98, 47, -45, -5, 97]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray011"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (log) {
            System.out.println("-> expected result: [-97, 32, 80, 121, -96, 47, 31, 42, 20, 15, 21, 98, 47, -45, -5, 97]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByteArray012() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {-69, 34, -19, 18, -39, -95, 127, -24, 12, 91, -59, -70, -31, 10, -19, 57, 17, -34, 71, -111, -82, -114, -48, 50, -87, 95, -99, -31, -24, 56, 64, 78, -119, 110, -21, -81, 11, 118, 38, 28, -121, -70, -87, 118, 78, -7, 32, 69, -69};
        String hash = "[-115, -44, -104, 66, 46, 120, 16, 33, -113, -72, 41, 5, -125, 32, 64, 30]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray012"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (log) {
            System.out.println("-> expected result: [-115, -44, -104, 66, 46, 120, 16, 33, -113, -72, 41, 5, -125, 32, 64, 30]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByteArray013() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {91, -112, 86, -70, -86, 3, -38, -90, 17, -52, 32, 42, 33, 116, 101, 89, 100, -81, -14, -95, 112};
        String hash = "[122, 18, -85, -39, -101, 46, 97, -118, 46, -6, -60, 33, -3, -107, -20, 3]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray013"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (log) {
            System.out.println("-> expected result: [122, 18, -85, -39, -101, 46, 97, -118, 46, -6, -60, 33, -3, -107, -20, 3]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByteArray014() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {-101, 42, -41, 74, 75, 117, 122, 100, 53, 121, -34, -82, 22, 121, -105, -16, 114, 57, -15, 112, -96, -83, -93, 32, -21, 81, 83, -77, 24, 92, -121, -90};
        String hash = "[-32, 93, -2, 28, 126, 86, -30, 14, 72, 25, 9, -69, 1, -83, -92, 65]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray014"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (log) {
            System.out.println("-> expected result: [-32, 93, -2, 28, 126, 86, -30, 14, 72, 25, 9, -69, 1, -83, -92, 65]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByteArray015() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {-111, -101, -76, -117, 21, 63, 113, 84, -50, 11, -40, -76, 15, -85, 92, 123, -37, 72, -92, 5, 4, -49};
        String hash = "[39, -80, -58, -88, 36, 32, 21, 103, 81, -7, 52, -28, 7, -43, -40, 127]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray015"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (log) {
            System.out.println("-> expected result: [39, -80, -58, -88, 36, 32, 21, 103, 81, -7, 52, -28, 7, -43, -40, 127]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByteArray016() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {-97, 122, 7, 47, -55, -119, 56, 70, -44, -119, -67, -25, 64, 106, -81, -47, -118, -24, 99, -47, 19, -103, 125, 95, 14, -6, -35, -126, -117, -77, -43, -90, -21, -86};
        String hash = "[-97, 11, -58, 19, 79, 28, 5, 111, 87, -64, -66, -85, 80, -55, 84, 5]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray016"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (log) {
            System.out.println("-> expected result: [-97, 11, -58, 19, 79, 28, 5, 111, 87, -64, -66, -85, 80, -55, 84, 5]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByteArray017() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {-123, 89, 41, 57, 103, 25, 93, -112, -10, -19, -111, -29, -82, 10, 65, 81, 112, -105, -84, -124, 9, 3, -45, 98, -83, -1, 36, -5, 43, 93, -105, -55, 96, -92, 29, -78};
        String hash = "[-40, 23, -29, -125, -39, -126, -99, 26, -124, 47, -20, -92, -96, -72, 6, -98]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray017"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (log) {
            System.out.println("-> expected result: [-40, 23, -29, -125, -39, -126, -99, 26, -124, 47, -20, -92, -96, -72, 6, -98]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByteArray018() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {-114, 87, 64, 57, 120, 1, -33, 98, 28, 108, 11, 26, -56, 52, -53, -30, -36, -35, -27, -33, -8, -99, -38, 117, 35, 84, -56, 76, -41, 36, 5, 93, -73, 66, 105, -3, -123, -105, -17, 43, -66};
        String hash = "[-7, -37, -56, 111, -38, 82, 7, -100, -122, 111, -126, 73, -106, 80, 124, 29]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray018"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (log) {
            System.out.println("-> expected result: [-7, -37, -56, 111, -38, 82, 7, -100, -122, 111, -126, 73, -106, 80, 124, 29]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByteArray019() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {-82, 122, 56, -109, -49, -83, -15, 107, -128, -77, 74, 104, -42, 41, -62, 7, -81, -110, 112, 26, -41, 122, 91, 51, -33, 108, 4, -24, 61, 30, -127, -65, -91, 125, -93, -2, -84, 106};
        String hash = "[117, -118, -89, -36, -67, -96, 91, -76, 109, 30, 22, 73, -22, 93, 60, -46]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray019"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (log) {
            System.out.println("-> expected result: [117, -118, -89, -36, -67, -96, 91, -76, 109, 30, 22, 73, -22, 93, 60, -46]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByteArray020() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {-109, 73, -83, -74, 69, -42, 89, 27, -39, -26, 82, -120, 117, 87, -86, 17, -116, -58, -72, 7, -53, -85, 90, -94, 10, 65, -116, 92, 27, -120, -101, 33, 86, -126, -124, -28, 24, 59, -50, -98, 51, -29, -36, 79};
        String hash = "[11, -12, -86, 3, -37, 64, 73, -4, 1, -111, 16, -90, 43, -110, -31, -82]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray020"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (log) {
            System.out.println("-> expected result: [11, -12, -86, 3, -37, 64, 73, -4, 1, -111, 16, -90, 43, -110, -31, -82]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByteArray021() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {-56, 60, 13, 70, -38, -126, -41, 43, -25, 122, 102, -37, -95, 6, -26};
        String hash = "[-36, -127, -82, 21, -25, 104, -55, 79, -38, -54, -64, 38, -77, 125, 48, -50]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray021"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (log) {
            System.out.println("-> expected result: [-36, -127, -82, 21, -25, 104, -55, 79, -38, -54, -64, 38, -77, 125, 48, -50]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByteArray022() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {53, -64, 41, 4, 10, -79, -107, -43, 26, -98, -49, 49, 51, 78, -40, -22, 71, -42, 44, 116, 9, -119, 73, 17, -54, 68, -68, 9, -50, -5, -30, -74, 29, 32, 90, -26, 13, 51, 22, -79, -77, 0, -4, 90, -77};
        String hash = "[5, 52, -91, -86, 114, -32, -65, 104, 59, 43, -79, -68, -68, 79, 123, -32]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray022"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (log) {
            System.out.println("-> expected result: [5, 52, -91, -86, 114, -32, -65, 104, 59, 43, -79, -68, -68, 79, 123, -32]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByteArray023() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {25, -27, -43, 95, 65, 9, 57, -110, 80, -8, -23, -95, 17, -58, 26, 36, 45, -54, 107, -55, -108, 30, -12, -97, -12, -114, 41, 89, 59};
        String hash = "[112, 120, -119, 5, 87, -122, -78, -118, 8, 55, 109, 70, -2, 48, -93, -119]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray023"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (log) {
            System.out.println("-> expected result: [112, 120, -119, 5, 87, -122, -78, -118, 8, 55, 109, 70, -2, 48, -93, -119]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByteArray024() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {20, -85, 48, -61, -96, 27, -97, -28, 98, -110, -45, 87, 117, -54, 114, -69, -58, 34, 73, -73, 112, -49, -45, -44, -108, 100, -10, -85, 124, -110, -88, 94, -100, -77, -23};
        String hash = "[59, -48, 91, 112, 121, 36, -120, -69, -107, 122, 60, -41, -104, -79, 39, 97]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray024"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (log) {
            System.out.println("-> expected result: [59, -48, 91, 112, 121, 36, -120, -69, -107, 122, 60, -41, -104, -79, 39, 97]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByteArray025() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {-120, 16, -24, 14, -43, 23, -64, 111, 75, -68, -23, 88, -37, -15, -6, 99, 78, -45, 30, -105};
        String hash = "[34, 0, 121, -50, -128, -34, 68, -98, 99, 55, -29, 63, 24, 99, 82, 44]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray025"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (log) {
            System.out.println("-> expected result: [34, 0, 121, -50, -128, -34, 68, -98, 99, 55, -29, 63, 24, 99, 82, 44]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByteArray026() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {91, 71, -112, -63, -86, -35, 63, 35, -49, -128, 122, -21, -87, 21, 71, -43, -9, 98, 92, 83, -20, 20, 108, 81, -86, 53, 26, -31, -62, -72, 7, 99, -113, -12, 64, -106, -120};
        String hash = "[78, -98, 8, -41, 112, 33, 27, 74, 40, 122, -64, -77, 3, -50, 41, -90]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray026"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (log) {
            System.out.println("-> expected result: [78, -98, 8, -41, 112, 33, 27, 74, 40, 122, -64, -77, 3, -50, 41, -90]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (log) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByteArray027() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {-124, 85, -34, -91, 65, -86, -104, -55, -57, 95, 51, -14, 98, 20, 55, 5, 0, 105, 119, 58, -19, -30, -39, -123, -71, -110, 70, -100, -94, -30, 13, 66, 53, 51, 102, -60, -123, 115, -11};
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray027"); }
        try {
            mac.update(input);
            fail("Sould raise IllegalStateException");
        } catch (IllegalStateException e) {
            if (log) {
                System.out.println("Raised java.lang.IllegalStateException: MAC not initialized");
                System.out.println("---Test PASSED---");
            }
        } catch (Throwable e) {
            fail("Sould raise IllegalStateException");
        }
    }
    public final void testUpdateByteArray028() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {74, 66, 42, 113, 121, -114, 23, 93, 101, 82, -46, -3, 80, 85, -26, -71, 95, -95, 77, 98, 53, -58, 102, 11, 70, -46, -15, -125, -100, 75, -92, 18, 80, 104, -5, -75, 32, 83, 15, -85, -111, 9, -5, -53};
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray028"); }
        try {
            mac.update(input);
            fail("Sould raise IllegalStateException");
        } catch (IllegalStateException e) {
            if (log) {
                System.out.println("Raised java.lang.IllegalStateException: MAC not initialized");
                System.out.println("---Test PASSED---");
            }
        } catch (Throwable e) {
            fail("Sould raise IllegalStateException");
        }
    }
    public final void testUpdateByteArray029() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {49, -51, -56, 42, 27, -125, 39, -5, -39, -100, -117, 18, 93, -90, -114, 37, 81, -8, 86, -88, 110, 51, -60, 103, -34, 103, 2, -77, 117, -73, 45, 3, 66, 69, -116, 120, 93, -97, -7, -90, -97, 110, -24, 16, 81, 35, -108, 71, -52};
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray029"); }
        try {
            mac.update(input);
            fail("Sould raise IllegalStateException");
        } catch (IllegalStateException e) {
            if (log) {
                System.out.println("Raised java.lang.IllegalStateException: MAC not initialized");
                System.out.println("---Test PASSED---");
            }
        } catch (Throwable e) {
            fail("Sould raise IllegalStateException");
        }
    }
    public final void testUpdateByteArray030() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        byte[] input = {-40, 53, 107, -70, 98, 63, 42, 20, -34, 8, -53, 69, 11, 62, 89, -33, -74, -79, -126, 65, -54, 8, 67, 88, -10, -53, 3, -102, -113, 44, 111, 126, -81, -15};
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (log) { System.out.println("testUpdateByteArray030"); }
        try {
            mac.update(input);
            fail("Sould raise IllegalStateException");
        } catch (IllegalStateException e) {
            if (log) {
                System.out.println("Raised java.lang.IllegalStateException: MAC not initialized");
                System.out.println("---Test PASSED---");
            }
        } catch (Throwable e) {
            fail("Sould raise IllegalStateException");
        }
    }

   /*
    * Test method for 'javax.crypto.Mac.update(byte[], int, int)'
    */

    public final void testUpdateByteArrayIntInt001() {
        try {
            mac.init(key);
            mac.update(inputArray, offset, len);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testUpdateByteArrayIntInt002() {
        try {
            offset = 0;
            len = 3;
            mac.init(key);
            mac.update(inputArray, offset, len);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testUpdateByteArrayIntInt003() {
        try {
            offset = 0;
            len = 0;
            mac.init(key);
            mac.update(inputArray, offset, len);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testUpdateByteArrayIntInt004() {
        try {
            offset = 3;
            len = 0;
            mac.init(key);
            mac.update(inputArray, offset, len);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testUpdateByteArrayIntInt005() {
        try {
            offset = 3;
            len = 1000;
            mac.init(key);
            mac.update(inputArray, offset, len);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testUpdateByteArrayIntInt006() {
        try {
            offset = 1000;
            len = -3;
            mac.init(key);
            mac.update(inputArray, offset, len);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testUpdateByteArrayIntInt007() {
        try {
            offset = 1000;
            len = -3;
            mac.init(key);
            inputArray = null;
            mac.update(inputArray, offset, len);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'javax.crypto.Mac.update(ByteBuffer)'
     */

    public final void testUpdateByteBuffer001() {
        try {
            mac.init(key);
            inputBuffer.clear();
            inputArray = new byte[inputBuffer.capacity()];
            inputBuffer.put(inputArray, 0, inputArray.length);
            mac.update(inputBuffer);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testUpdateByteBuffer002() {
        try {
            mac.init(key);
            inputBuffer.clear();
            inputArray = new byte[inputBuffer.capacity()];
            inputBuffer.put(inputArray, 0, inputArray.length);
            int i = inputBuffer.capacity();
            mac.update(inputBuffer);
            assertEquals(inputBuffer.capacity(), i);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testUpdateByteBuffer003() {
        try {
            mac.init(key);
            inputBuffer.clear();
            inputArray = new byte[inputBuffer.capacity()];
            inputBuffer.put(inputArray, 0, inputArray.length);
            inputBuffer.rewind();
            int i = inputBuffer.capacity();
            mac.update(inputBuffer);
            assertTrue(inputBuffer.position() == i);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testUpdateByteBuffer004() {
        try {
            mac.init(key);
            inputBuffer.clear();
            inputArray = new byte[0];
            inputBuffer.put(inputArray, 0, inputArray.length);
            mac.update(inputBuffer);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'javax.crypto.Mac.doFinal()'
     */

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

    public final void testDoFinal003() {

        try {
            mac.init(key);
            input = (byte) 0xc7;
            mac.update(input);
            assertNotNull(mac.doFinal());
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }

    /*
     * Test method for 'javax.crypto.Mac.doFinal(byte[], int)'
     */


    public final void testDoFinalByteArrayInt002() {
        try {
            byte[] output1 = new byte[20];
            byte[] output2 = new byte[20];
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
            output1 = mac.doFinal();
            input = (byte) 0xc7;
            mac.update(input);
            int outOffset = 0;
            mac.doFinal();
            mac.doFinal(output2, outOffset);
            for (int i = 0; i < 20; i++) {
                assertEquals(output1[i], output2[i]);
            }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }



    /*
     * Test method for 'javax.crypto.Mac.doFinal(byte[], int) that throws an
     * IllegalStateException'
     */

    /*
     * Test method for 'javax.crypto.Mac.doFinal(byte[])'
     */


    public final void testDoFinalByteArray002() {
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
            byte[] inputArray = { (byte) 0xc7, (byte) 0x85, (byte) 0x21,
                    (byte) 0x8c, (byte) 0x7e, (byte) 0xc8, (byte) 0xff,
                    (byte) 0x99 };
            mac.update(inputArray);
            output1 = mac.doFinal();
            output2 = mac.doFinal(inputArray);
            for (int i = 0; i < 16; i++) {
                assertEquals(output1[i], output2[i]);
            }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }

    }

    public final void testDoFinalByteArray003() {
        try {
            mac.init(key);
            byte[] inputArray = { (byte) 0xc7, (byte) 0x85, (byte) 0x21,
                    (byte) 0x8c, (byte) 0x7e, (byte) 0xc8, (byte) 0xff,
                    (byte) 0x99 };
            assertNotNull(mac.doFinal(inputArray));
        } catch (Throwable e) {
            fail("Should not raise any Exception..." + e);
        }
    }



    /*
     * Test method for 'javax.crypto.Mac.clone()
     */

    /*
     public final void testClone002() {
     try {
     Cloned = mac.clone();
     assertEquals(mac,Cloned);
     //assertTrue(mac.equals(Cloned));
      } catch (Throwable e) {
      fail("Failed with:" + e);
      }
      }
      */

    boolean logtestUpdateByte = false;
    boolean log = false;

    public final void testUpdateByte001() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte001\" was not excecuted.");
            return;
        }
        byte input = -63;
        String hash = "[-57, 53, -27, 15, 92, 62, 117, 70, -69, -23, -48, 37, 99, 62, 61, -9]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte001"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out.println("-> expected result: [-57, 53, -27, 15, 92, 62, 117, 70, -69, -23, -48, 37, 99, 62, 61, -9]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (logtestUpdateByte) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByte002() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte002\" was not excecuted.");
            return;
        }
        byte input = -113;
        String hash = "[-61, 71, -127, -21, 108, 125, -61, 29, 56, -109, -31, -55, -47, -35, -117, 113]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte002"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out.println("-> expected result: [-61, 71, -127, -21, 108, 125, -61, 29, 56, -109, -31, -55, -47, -35, -117, 113]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (logtestUpdateByte) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByte003() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte003\" was not excecuted.");
            return;
        }
        byte input = 53;
        String hash = "[31, 72, -71, -88, 11, 79, 31, -81, -106, 19, -40, 63, 101, -80, -126, 78]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte003"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out.println("-> expected result: [31, 72, -71, -88, 11, 79, 31, -81, -106, 19, -40, 63, 101, -80, -126, 78]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (logtestUpdateByte) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByte004() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte004\" was not excecuted.");
            return;
        }
        byte input = 5;
        String hash = "[111, -118, 16, -108, -76, 7, -44, 28, 36, -28, -126, -49, -40, 62, -55, 67]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte004"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out.println("-> expected result: [111, -118, 16, -108, -76, 7, -44, 28, 36, -28, -126, -49, -40, 62, -55, 67]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (logtestUpdateByte) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByte005() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte005\" was not excecuted.");
            return;
        }
        byte input = -21;
        String hash = "[60, -11, 38, -106, -29, -107, 10, 30, -71, -107, -35, 37, 92, 49, 65, -97]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte005"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out.println("-> expected result: [60, -11, 38, -106, -29, -107, 10, 30, -71, -107, -35, 37, 92, 49, 65, -97]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (logtestUpdateByte) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByte006() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte006\" was not excecuted.");
            return;
        }
        byte input = 126;
        String hash = "[-66, -48, -99, -21, 81, 58, 87, -76, 105, -93, -33, 78, -45, 60, 6, 123]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte006"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out.println("-> expected result: [-66, -48, -99, -21, 81, 58, 87, -76, 105, -93, -33, 78, -45, 60, 6, 123]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (logtestUpdateByte) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByte007() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte007\" was not excecuted.");
            return;
        }
        byte input = 69;
        String hash = "[-19, -25, 89, 48, -46, 114, -5, -24, 42, -14, 22, -113, 74, 10, -71, 123]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte007"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out.println("-> expected result: [-19, -25, 89, 48, -46, 114, -5, -24, 42, -14, 22, -113, 74, 10, -71, 123]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (logtestUpdateByte) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByte008() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte008\" was not excecuted.");
            return;
        }
        byte input = 92;
        String hash = "[101, 100, -10, -18, -43, -58, 99, -75, 40, 11, 97, -31, -21, -95, -127, -12]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte008"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out.println("-> expected result: [101, 100, -10, -18, -43, -58, 99, -75, 40, 11, 97, -31, -21, -95, -127, -12]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (logtestUpdateByte) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByte009() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte009\" was not excecuted.");
            return;
        }
        byte input = 7;
        String hash = "[103, -18, 7, -77, 116, -124, 31, -15, -80, 29, 61, -19, 95, 91, 78, -120]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte009"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out.println("-> expected result: [103, -18, 7, -77, 116, -124, 31, -15, -80, 29, 61, -19, 95, 91, 78, -120]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (logtestUpdateByte) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByte010() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte010\" was not excecuted.");
            return;
        }
        byte input = -48;
        String hash = "[-88, 124, 12, -27, 31, 110, -75, 40, 125, 6, -122, -117, -85, 45, -55, -116]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte010"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out.println("-> expected result: [-88, 124, 12, -27, 31, 110, -75, 40, 125, 6, -122, -117, -85, 45, -55, -116]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (logtestUpdateByte) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByte011() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte011\" was not excecuted.");
            return;
        }
        byte input = -82;
        String hash = "[-70, -103, 33, -54, 13, 101, -80, 112, 94, 107, 22, -32, -16, -110, -71, -69]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte011"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out.println("-> expected result: [-70, -103, 33, -54, 13, 101, -80, 112, 94, 107, 22, -32, -16, -110, -71, -69]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (logtestUpdateByte) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByte012() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte012\" was not excecuted.");
            return;
        }
        byte input = -41;
        String hash = "[-2, 44, -76, -29, 31, 14, 1, 126, -23, 90, -27, 77, 51, 87, 24, -89]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte012"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out.println("-> expected result: [-2, 44, -76, -29, 31, 14, 1, 126, -23, 90, -27, 77, 51, 87, 24, -89]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (logtestUpdateByte) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByte013() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte013\" was not excecuted.");
            return;
        }
        byte input = 97;
        String hash = "[39, 36, -53, 120, -110, 63, -58, 80, -49, 16, -77, 47, -7, -12, 57, -19]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte013"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out.println("-> expected result: [39, 36, -53, 120, -110, 63, -58, 80, -49, 16, -77, 47, -7, -12, 57, -19]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (logtestUpdateByte) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByte014() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte014\" was not excecuted.");
            return;
        }
        byte input = -21;
        String hash = "[60, -11, 38, -106, -29, -107, 10, 30, -71, -107, -35, 37, 92, 49, 65, -97]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte014"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out.println("-> expected result: [60, -11, 38, -106, -29, -107, 10, 30, -71, -107, -35, 37, 92, 49, 65, -97]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (logtestUpdateByte) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByte015() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte015\" was not excecuted.");
            return;
        }
        byte input = -73;
        String hash = "[76, -125, -85, 35, -78, 115, -12, -48, -107, 96, -103, 5, 112, 77, 6, 60]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte015"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out.println("-> expected result: [76, -125, -85, 35, -78, 115, -12, -48, -107, 96, -103, 5, 112, 77, 6, 60]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (logtestUpdateByte) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByte016() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte016\" was not excecuted.");
            return;
        }
        byte input = -36;
        String hash = "[33, -17, 124, -66, -57, 21, -7, 52, 93, -66, 10, -108, -96, -41, -77, 72]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte016"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out.println("-> expected result: [33, -17, 124, -66, -57, 21, -7, 52, 93, -66, 10, -108, -96, -41, -77, 72]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (logtestUpdateByte) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByte017() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte017\" was not excecuted.");
            return;
        }
        byte input = -105;
        String hash = "[-97, 56, 19, 126, 37, -75, 36, -6, -17, 92, -23, 54, 108, -67, 26, -2]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte017"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out.println("-> expected result: [-97, 56, 19, 126, 37, -75, 36, -6, -17, 92, -23, 54, 108, -67, 26, -2]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (logtestUpdateByte) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByte018() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte018\" was not excecuted.");
            return;
        }
        byte input = 117;
        String hash = "[66, -62, -3, -37, 54, 94, 50, -54, 52, -120, -76, -68, 127, -82, 87, -67]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte018"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out.println("-> expected result: [66, -62, -3, -37, 54, 94, 50, -54, 52, -120, -76, -68, 127, -82, 87, -67]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (logtestUpdateByte) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByte019() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte019\" was not excecuted.");
            return;
        }
        byte input = 63;
        String hash = "[-36, -10, 2, 81, 16, 107, -40, -15, 96, -62, -125, 2, 58, -12, 82, -1]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte019"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out.println("-> expected result: [-36, -10, 2, 81, 16, 107, -40, -15, 96, -62, -125, 2, 58, -12, 82, -1]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (logtestUpdateByte) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByte020() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte020\" was not excecuted.");
            return;
        }
        byte input = 70;
        String hash = "[-35, -50, -26, 0, -103, 38, 40, 81, 45, -5, 46, -70, 90, 90, 104, 19]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte020"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out.println("-> expected result: [-35, -50, -26, 0, -103, 38, 40, 81, 45, -5, 46, -70, 90, 90, 104, 19]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (logtestUpdateByte) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByte021() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte021\" was not excecuted.");
            return;
        }
        byte input = -64;
        String hash = "[79, -34, -48, 13, -80, 70, -91, -75, -22, -115, -84, 12, 99, 30, -39, 118]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte021"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out.println("-> expected result: [79, -34, -48, 13, -80, 70, -91, -75, -22, -115, -84, 12, 99, 30, -39, 118]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (logtestUpdateByte) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByte022() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte022\" was not excecuted.");
            return;
        }
        byte input = 4;
        String hash = "[-46, 35, -74, 41, 40, -28, 25, -111, -57, -116, 43, 16, -78, -95, -66, -40]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte022"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out.println("-> expected result: [-46, 35, -74, 41, 40, -28, 25, -111, -57, -116, 43, 16, -78, -95, -66, -40]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (logtestUpdateByte) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByte023() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte023\" was not excecuted.");
            return;
        }
        byte input = -102;
        String hash = "[-20, -10, 25, -52, 12, 25, -2, -49, 24, -83, -17, -97, -30, -12, 85, 70]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte023"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out.println("-> expected result: [-20, -10, 25, -52, 12, 25, -2, -49, 24, -83, -17, -97, -30, -12, 85, 70]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (logtestUpdateByte) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByte024() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte024\" was not excecuted.");
            return;
        }
        byte input = -112;
        String hash = "[-17, -31, -79, -74, 19, 23, 61, -54, 123, 60, -24, -1, -81, -1, 112, -41]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte024"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out.println("-> expected result: [-17, -31, -79, -74, 19, 23, 61, -54, 123, 60, -24, -1, -81, -1, 112, -41]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (logtestUpdateByte) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByte025() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte025\" was not excecuted.");
            return;
        }
        byte input = 114;
        String hash = "[-110, 120, 87, 117, -26, -128, 8, -127, -120, -94, -107, -119, -30, 43, -5, 37]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte025"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out.println("-> expected result: [-110, 120, 87, 117, -26, -128, 8, -127, -120, -94, -107, -119, -30, 43, -5, 37]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (logtestUpdateByte) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByte026() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte026\" was not excecuted.");
            return;
        }
        byte input = 58;
        String hash = "[52, 71, 98, 1, 79, -56, -8, 11, -63, -92, 36, -46, -81, -114, -21, -111]";
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte026"); }
        byte[] salt = { (byte) 0xc7, (byte) 0x85, (byte) 0x21, (byte) 0x8c,(byte) 0x7e, (byte) 0xc8, (byte) 0xff, (byte) 0x99 };
        key = new K("HmacMD5",salt);
        mac.init(key);
        for (int i=0 ; i<100 ; i++) {
            mac.update(input);
        }
        byte[] result = mac.doFinal();
        if (logtestUpdateByte) {
            System.out.println("-> expected result: [52, 71, 98, 1, 79, -56, -8, 11, -63, -92, 36, -46, -81, -114, -21, -111]");
            System.out.println("-> actual result: "+ Arrays.toString(result));
        }
        assertEquals(msgNotSame,hash,Arrays.toString(result));
        if (logtestUpdateByte) { System.out.println("---Test PASSED---"); }
    }
    public final void testUpdateByte027() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte027\" was not excecuted.");
            return;
        }
        byte input = -38;
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte027"); }
        try {
            mac.update(input);
            fail("Sould raise IllegalStateException");
        } catch (IllegalStateException e) {
            if (logtestUpdateByte) {
                System.out.println("Raised java.lang.IllegalStateException: MAC not initialized");
                System.out.println("---Test PASSED---");
            }
        } catch (Throwable e) {
            fail("Sould raise IllegalStateException");
        }
    }
    public final void testUpdateByte028() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte028\" was not excecuted.");
            return;
        }
        byte input = -104;
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte028"); }
        try {
            mac.update(input);
            fail("Sould raise IllegalStateException");
        } catch (IllegalStateException e) {
            if (logtestUpdateByte) {
                System.out.println("Raised java.lang.IllegalStateException: MAC not initialized");
                System.out.println("---Test PASSED---");
            }
        } catch (Throwable e) {
            fail("Sould raise IllegalStateException");
        }
    }
    public final void testUpdateByte029() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte029\" was not excecuted.");
            return;
        }
        byte input = 82;
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte029"); }
        try {
            mac.update(input);
            fail("Sould raise IllegalStateException");
        } catch (IllegalStateException e) {
            if (logtestUpdateByte) {
                System.out.println("Raised java.lang.IllegalStateException: MAC not initialized");
                System.out.println("---Test PASSED---");
            }
        } catch (Throwable e) {
            fail("Sould raise IllegalStateException");
        }
    }
    public final void testUpdateByte030() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        if (Security.getProvider("BC") == null) {
            System.err.println("Warning: provider BouncyCastle not installed. Test \"testUpdateByte030\" was not excecuted.");
            return;
        }
        byte input = 18;
        Mac mac = Mac.getInstance("HmacMD5","BC");
        if (logtestUpdateByte) { System.out.println("testUpdateByte030"); }
        try {
            mac.update(input);
            fail("Sould raise IllegalStateException");
        } catch (IllegalStateException e) {
            if (logtestUpdateByte) {
                System.out.println("Raised java.lang.IllegalStateException: MAC not initialized");
                System.out.println("---Test PASSED---");
            }
            return;
        } catch (Throwable e) {
            fail("Sould raise IllegalStateException");
        }
        fail("Sould raise IllegalStateException");
    }


}