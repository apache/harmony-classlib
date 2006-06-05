package ar.org.fitc.test.crypto;

import java.awt.color.ColorSpace;
import java.io.IOException;
import java.io.Serializable;
import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SealedObject;

import junit.framework.TestCase;
import ar.org.fitc.test.crypto.definitions.DefinitionCipherInitedNonBlock;
import ar.org.fitc.test.util.ControlSerializable;
import ar.org.fitc.test.util.Messages;
import ar.org.fitc.test.util.crypto.Util;

public class TestSealedObject extends TestCase implements Messages {
    private SealedObject so = null;

    private Cipher cipher = null;

    private Key key = null;

    private Serializable objToSeal = null;

    private String provider = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestSealedObject.class);
    }

    public TestSealedObject(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        provider = "BC";
        key = Util.getInstanceKey();
        // cipher = Util.getInstanceCipherInited("DES/CBC/PKCS5PADDING",key);
        cipher = Util.getInstanceCipherInitedNonBlock(key);
        objToSeal = Util.getInstanceSerializedObject();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'javax.crypto.SealedObject.SealedObject(Serializable,
     * Cipher)'
     */
    public final void testSealedObjectSerializableCipher001() {
        try {
            so = new SealedObject(objToSeal, cipher);
            assertTrue(msgNotInstance, so instanceof SealedObject);
        } catch (Throwable e) {
            e.printStackTrace();
            fail(msgNoException + e);
        }
    }

    public final void testSealedObjectSerializableCipher002() {
        try {
            cipher = Util.getInstanceCipherInited("DES/CBC/NoPadding");
            so = new SealedObject(objToSeal, cipher);
            fail(msgRaise + "IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
            fail(msgRaise + "IllegalBlockSizeException");
        }
    }

    public final void testSealedObjectSerializableCipher003() {
        try {
            so = new SealedObject(
                    Util.getInstanceSerializedObjectIOException(), cipher);
            fail(msgNoException + "IOException");
        } catch (IOException e) {
        } catch (Throwable e) {
            fail(msgNoException + "IOException");
        }
    }

    /*
     * Test method for 'javax.crypto.SealedObject.getAlgorithm()'
     */
    public final void testGetAlgorithm001() {
        testSealedObjectSerializableCipher001();
        assertEquals("Not valid",
                DefinitionCipherInitedNonBlock.transformation, so
                        .getAlgorithm());
    }

    public final void testGetAlgorithm002() {
        testSealedObjectSerializableCipher001();
        assertTrue(msgNotInstance + "String",
                so.getAlgorithm() instanceof String);
    }

    /*
     * Test methods for 'javax.crypto.SealedObject.getObject(Key)'
     */
    public final void testGetObjectKey001() {
        testSealedObjectSerializableCipher001();
        try {
            assertTrue(msgNotInstance + "ColorSpace",
                    so.getObject(key) instanceof ColorSpace);
            assertNotSame(msgNotSame, objToSeal, so.getObject(key));
        } catch (Throwable e) {
            fail(msgNoException);
        }
    }

    public final void testGetObjectKey002() {
        testSealedObjectSerializableCipher001();
        try {
            key = Util.getInstanceKey("Blowfish");
            assertTrue(msgNotInstance + "ColorSpace",
                    so.getObject(key) instanceof ColorSpace);
            fail(msgRaise + "InvalidKeyException");
        } catch (InvalidKeyException e) {
        } catch (Throwable e) {
            fail(msgRaise + "InvalidKeyException");
        }
    }

    public final void testGetObjectKey003() {
        testSealedObjectSerializableCipher001();
        try {
            key = Util.getInstanceKey("NA");
            assertTrue(msgNotInstance + "ColorSpace",
                    so.getObject(key) instanceof ColorSpace);
            fail(msgRaise + "NoSuchAlgorithm");
        } catch (NoSuchAlgorithmException e) {
        } catch (Throwable e) {
            fail(msgRaise + "NoSuchAlgorithm");
        }
    }

    public final void testGetObjectKey004() throws Exception {
        objToSeal = new ControlSerializable(1,2, ControlSerializable.readObjectIOException);
        so = new SealedObject(objToSeal, cipher);
        try {
            so.getObject(key);
            fail(msgRaise + "IOException");
        } catch (IOException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    public final void testGetObjectKey005() throws Exception {
        objToSeal = new ControlSerializable(1,2, ControlSerializable.readObjectClassNotFoundException);
        so = new SealedObject(objToSeal, cipher);
        try {
            so.getObject(key);
            fail(msgRaise + "ClassNotFoundException");
        } catch (ClassNotFoundException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /*
     * Test method for 'javax.crypto.SealedObject.getObject(Cipher)'
     */

    public final void testGetObjectCipher001() {
        testSealedObjectSerializableCipher001();
        try {
            cipher = null;
            assertEquals(msgNotSame, objToSeal, so.getObject(cipher));
            fail(msgRaise + "NullPointerException");
        } catch (NullPointerException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    public final void testGetObjectCipher002() throws Exception {
        objToSeal = new ControlSerializable(1,2, ControlSerializable.readObjectIOException);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        so = new SealedObject(objToSeal, cipher);
        cipher.init(Cipher.DECRYPT_MODE, key, cipher.getParameters());
        try {
            so.getObject(cipher);
            fail(msgRaise + "IOException");
        } catch (IOException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    public final void testGetObjectCipher003() throws Exception{
        objToSeal = new ControlSerializable(1,2, ControlSerializable.readObjectClassNotFoundException);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        so = new SealedObject(objToSeal, cipher);
        cipher.init(Cipher.DECRYPT_MODE, key, cipher.getParameters());
        try {
            so.getObject(cipher);
            fail(msgRaise + "ClassNotFoundException");
        } catch (ClassNotFoundException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    public final void testGetObjectCipher004() throws Exception {
        
//        objToSeal = new ControlSerializable(1,2, 5);//ControlSerializable.readObjectClassNotFoundException);
//        cipher = Util.getInstanceCipherInited("DES/CFB8/NoPadding");
//        cipher.init(Cipher.ENCRYPT_MODE, key);
//        so = new SealedObject(objToSeal, cipher);
//        AlgorithmParameters al = cipher.getParameters();
//        //cipher = Cipher.getInstance("RSA", "BC");
//        cipher = Util.getInstanceCipherInited("DES/CFB10/NoPadding");
//        cipher.init(Cipher.ENCRYPT_MODE, key, al);
//        try {
//            so.getObject(cipher);
//            fail(msgRaise + "IllegalBlockSizeException");
//        } catch (IllegalBlockSizeException e) {
//        } catch (Throwable e) {
//            fail("fail with: " + e);
//        }
    }

    public final void testGetObjectCipher005() throws Exception {

        
        cipher = Util.getInstanceCipherInited("DES/CFB/ISO10126Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        AlgorithmParameters al = cipher.getParameters();
        so = new SealedObject(objToSeal, cipher);
        cipher = Util.getInstanceCipherInited("DES/CFB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, al);
        try {
            
            so.getObject(cipher);
            fail(msgRaise + "BadPaddingException");
        } catch (BadPaddingException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    /*
     * Test method for 'javax.crypto.SealedObject.getObject(Key, String)'
     */
    public final void testGetObjectKeyString001() {
        testSealedObjectSerializableCipher001();
        try {
            assertTrue(msgNotInstance + "ColorSpace", so.getObject(key,
                    provider) instanceof ColorSpace);
            assertNotSame(msgNotSame, objToSeal, so.getObject(key, provider));
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }

    public final void testGetObjectKeyString002() {
        testSealedObjectSerializableCipher001();
        provider = "";
        try {

            assertTrue(msgNotInstance + "ColorSpace", so.getObject(key,
                    provider) instanceof ColorSpace);
            fail(msgRaise + "IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail(msgRaise + "IllegalArgumentException");
        }
    }

    public final void testGetObjectKeyString003() {
        testSealedObjectSerializableCipher001();
        provider = null;
        try {
            assertTrue(msgNotInstance + "ColorSpace", so.getObject(key,
                    provider) instanceof ColorSpace);
            fail(msgRaise + "IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail(msgRaise + "IllegalArgumentException");
        }
    }

    public final void testGetObjectKeyString004() throws Exception {
        objToSeal = new ControlSerializable(1,2, ControlSerializable.readObjectIOException);
        so = new SealedObject(objToSeal, cipher);
        try {
            so.getObject(key, provider);
            fail(msgRaise + "IOException");
        } catch (IOException e) {
        } catch (Throwable e) {
            fail(msgRaise + "IOException");
        }
    }

    public final void testGetObjectKeyString005() throws Exception {
        objToSeal = new ControlSerializable(1,2, ControlSerializable.readObjectClassNotFoundException);
        so = new SealedObject(objToSeal, cipher);
        try {
            so.getObject(key, provider);
            fail(msgRaise + "ClassNotFoundException");
        } catch (ClassNotFoundException e) {
        } catch (Throwable e) {
            fail(msgRaise + "ClassNotFoundException");
        }
    }

    public final void testGetObjectKeyString006() {
        testSealedObjectSerializableCipher001();
        try {
            key = Util.getInstanceKey("TripleAES");
            assertTrue(msgNotInstance + "ColorSpace", so.getObject(key,
                    provider) instanceof ColorSpace);
            fail(msgRaise + "NoSuchAlgorithmException");
        } catch (NoSuchAlgorithmException e) {
        } catch (Throwable e) {
            fail(msgRaise + "NoSuchAlgorithmException");
        }
    }

    public final void testGetObjectKeyString007() {
        testSealedObjectSerializableCipher001();
        provider = "Unknown";
        try {
            assertTrue(msgNotInstance + "ColorSpace", so.getObject(key,
                    provider) instanceof ColorSpace);
            fail(msgRaise + "NoSuchProviderException");
        } catch (NoSuchProviderException e) {
        } catch (Throwable e) {
            fail(msgRaise + "NoSuchProviderException");
        }
    }

    public final void testGetObjectKeyString008() {
        testSealedObjectSerializableCipher001();
        try {
            key = Util.getInstanceKey("Blowfish");
            assertTrue(msgNotInstance + "ColorSpace", so.getObject(key,
                    provider) instanceof ColorSpace);
            fail(msgRaise + "InvalidKeyException");
        } catch (InvalidKeyException e) {
        } catch (Throwable e) {
            fail(msgRaise + "InvalidKeyException" + e);
        }
    }
}
