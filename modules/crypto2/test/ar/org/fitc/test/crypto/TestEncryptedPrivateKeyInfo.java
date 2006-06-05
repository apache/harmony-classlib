package ar.org.fitc.test.crypto;

import junit.framework.TestCase;
import javax.crypto.*;

import ar.org.fitc.test.crypto.definitions.DefinitionEncryptedPrivateKeyInfo;
import ar.org.fitc.test.util.Messages;
import ar.org.fitc.test.util.crypto.Util;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.io.IOException;

public class TestEncryptedPrivateKeyInfo extends TestCase implements Messages,
        DefinitionEncryptedPrivateKeyInfo {
    // private Key key = null;
    // private KeyGenerator kg = null;
    @SuppressWarnings("unused")
    private Cipher cipher = null;

    @SuppressWarnings("unused")
    private String algName = "";

    @SuppressWarnings("unused")
    private byte[] encryptedData = null;

    @SuppressWarnings("unused")
    private byte[] encoded = null;

    @SuppressWarnings("unused")
    private AlgorithmParameters algParams = null;

    @SuppressWarnings("unused")
    private Key decryptKey = null;

    @SuppressWarnings("unused")
    private Provider provider = null;

    @SuppressWarnings("unused")
    private String providerName = "";

    @SuppressWarnings("unused")
    private PKCS8EncodedKeySpec pkcs8eks = null;

    private EncryptedPrivateKeyInfo epki = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestEncryptedPrivateKeyInfo.class);
    }

    public TestEncryptedPrivateKeyInfo(String name) {
        super(name);

    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

  

    public final void testEncryptedPrivateKeyInfoByteArray002() {
        try {
            new EncryptedPrivateKeyInfo(null);
            fail(msgRaise + "NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {

            fail("Failed with:" + e);
        }
    }

    public final void testEncryptedPrivateKeyInfoByteArray003() {
        try {
            new EncryptedPrivateKeyInfo(data);
            fail(msgRaise + "IOException");
        } catch (IOException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for
     * 'javax.crypto.EncryptedPrivateKeyInfo.EncryptedPrivateKeyInfo(String,
     * byte[])'
     */
    public final void testEncryptedPrivateKeyInfoStringByteArray001() {
        try {
            epki = new EncryptedPrivateKeyInfo(algorithm, data);
            assertTrue(epki instanceof EncryptedPrivateKeyInfo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEncryptedPrivateKeyInfoStringByteArray002() {
        try {
            new EncryptedPrivateKeyInfo((String) null, data);
            fail(msgRaise + "NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {

            fail("Failed with:" + e);
        }
    }

    public final void testEncryptedPrivateKeyInfoStringByteArray003() {
        try {
            new EncryptedPrivateKeyInfo(algorithm, null);
            fail(msgRaise + "NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {

            fail("Failed with:" + e);
        }
    }

    public final void testEncryptedPrivateKeyInfoStringByteArray004() {
        try {
            new EncryptedPrivateKeyInfo(algorithm, new byte[0]);
            fail(msgRaise + "IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEncryptedPrivateKeyInfoStringByteArray005() {
        try {
            new EncryptedPrivateKeyInfo((String) null, null);
            fail(msgRaise + "NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {

            fail("Failed with:" + e);
        }
    }

    public final void testEncryptedPrivateKeyInfoStringByteArray006() {
        try {
            new EncryptedPrivateKeyInfo("SADFasdfasa", data);
            fail(msgRaise + "NoSuchAlgorithmException");
        } catch (NoSuchAlgorithmException e) {
            assertTrue(true);
        } catch (Throwable e) {

            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for
     * 'javax.crypto.EncryptedPrivateKeyInfo.EncryptedPrivateKeyInfo(AlgorithmParametres,
     * byte[])'
     */
    public final void testEncryptedPrivateKeyInfoAlgorithmParametersByteArray001() {
        try {
            AlgorithmParameters algo = Util.getAlgorithmParameters(
                    algorithmGen, providerString);
            assertNotNull("Null AlgorithmParameters", algo);
            epki = new EncryptedPrivateKeyInfo(algo, data);
            assertTrue(epki instanceof EncryptedPrivateKeyInfo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEncryptedPrivateKeyInfoAlgorithmParametersByteArray002() {
        try {
            AlgorithmParameters algo = Util.getAlgorithmParameters(
                    algorithmGen, providerString);
            assertNotNull("Null AlgorithmParameters", algo);
            new EncryptedPrivateKeyInfo(algo, null);
            fail(msgRaise + "NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {

            fail("Failed with:" + e);
        }
    }

    public final void testEncryptedPrivateKeyInfoAlgorithmParametersByteArray003() {
        try {
            new EncryptedPrivateKeyInfo((AlgorithmParameters) null, null);
            fail(msgRaise + "NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {

            fail("Failed with:" + e);
        }
    }

    public final void testEncryptedPrivateKeyInfoAlgorithmParametersByteArray004() {
        try {
            new EncryptedPrivateKeyInfo((AlgorithmParameters) null, data);
            fail(msgRaise + "NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEncryptedPrivateKeyInfoAlgorithmParametersByteArray005() {
        try {
            AlgorithmParameters algo = Util.getAlgorithmParameters(
                    algorithmGen, providerString);
            assertNotNull("Null AlgorithmParameters", algo);
            new EncryptedPrivateKeyInfo(algo, new byte[0]);
            fail(msgRaise + "IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {

            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'javax.crypto.EncryptedPrivateKeyInfo.getAlgName()'
     */
    public final void testGetAlgName001() {
        testEncryptedPrivateKeyInfoStringByteArray001();
        assertEquals(algorithm, epki.getAlgName());
    }

    public final void testGetAlgName002() {
        testEncryptedPrivateKeyInfoAlgorithmParametersByteArray001();
        assertEquals(algorithm, epki.getAlgName());

    }

  

    public final void testGetAlgParameters002() {
        testEncryptedPrivateKeyInfoAlgorithmParametersByteArray001();
        try {
            AlgorithmParameters alg = epki.getAlgParameters();
            assertNotNull("check not null AlgorithmParameters", alg);
            assertEquals("check names equals", algorithm, alg.getAlgorithm());
            assertNotNull("check not null Provider", alg.getProvider());
            assertEquals("check provider equals", providerString, alg
                    .getProvider().getName());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'javax.crypto.EncryptedPrivateKeyInfo.getEncryptedData()'
     */
    public final void testGetEncryptedData001() {
        testEncryptedPrivateKeyInfoStringByteArray001();
        try {
            int i = 0;
            for (byte b : epki.getEncryptedData()) {
                assertEquals("Checking if the same data ", b, data[i++]);
            }
            assertNotSame("Checking if it is a new array", data, epki
                    .getEncryptedData());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testGetEncryptedData002() {
        testEncryptedPrivateKeyInfoAlgorithmParametersByteArray001();
        try {
            int i = 0;
            for (byte b : epki.getEncryptedData()) {
                assertEquals("Checking if the same data", b, data[i++]);
            }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testGetEncryptedData003() {
        testEncryptedPrivateKeyInfoStringByteArray001();
        try {
            assertNotSame("Checking if it is a new array", data, epki
                    .getEncryptedData());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testGetEncryptedData004() {
        testEncryptedPrivateKeyInfoAlgorithmParametersByteArray001();
        try {
            assertNotSame("Checking if it is a new array", data, epki
                    .getEncryptedData());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testGetEncryptedData005() {
        testEncryptedPrivateKeyInfoStringByteArray001();
        try {
            assertNotSame("Checking if it is a new array", epki
                    .getEncryptedData(), epki.getEncryptedData());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testGetEncryptedData006() {
        testEncryptedPrivateKeyInfoAlgorithmParametersByteArray001();
        try {
            assertNotSame("Checking if it is a new array", epki
                    .getEncryptedData(), epki.getEncryptedData());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

   

    /*
     * Test method for 'javax.crypto.EncryptedPrivateKeyInfo.getKeySpec(Key)'
     */
    public final void testGetKeySpecKey() {
        // TODO Auto-generated method stub
        try {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'javax.crypto.EncryptedPrivateKeyInfo.getKeySpec(Key,
     * String)'
     */
    public final void testGetKeySpecKeyString() {
        // TODO Auto-generated method stub
        try {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'javax.crypto.EncryptedPrivateKeyInfo.getKeySpec(Key,
     * Provider)'
     */
    public final void testGetKeySpecKeyProvider() {
        // TODO Auto-generated method stub
        try {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'javax.crypto.EncryptedPrivateKeyInfo.getEncoded()'
     */
    public final void testGetEncoded001() {
        testEncryptedPrivateKeyInfoStringByteArray001();
        try {
            assertTrue(
                    "Checking data is well encoded",
                    new EncryptedPrivateKeyInfo(epki.getEncoded()) instanceof EncryptedPrivateKeyInfo);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    

}
