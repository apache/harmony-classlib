package ar.org.fitc.test.main;

import java.security.NoSuchAlgorithmException;

import junit.extensions.RepeatedTest;
import junit.framework.Test;
import junit.framework.TestSuite;
import ar.org.fitc.test.crypto.TestCipherInputStream;
import ar.org.fitc.test.crypto.TestCipherOutputStream;
import ar.org.fitc.test.crypto.TestEncryptedPrivateKeyInfo;
import ar.org.fitc.test.crypto.TestKeyAgreement;
import ar.org.fitc.test.crypto.TestKeyGenerator;
import ar.org.fitc.test.crypto.TestMac;
import ar.org.fitc.test.crypto.TestMacStatic;
import ar.org.fitc.test.crypto.TestSealedObject;
import ar.org.fitc.test.crypto.TestSecretKeyFactory;
import ar.org.fitc.test.crypto.cipher.TestCipherAndCertificate;
import ar.org.fitc.test.crypto.cipher.TestCipherDinamic;
import ar.org.fitc.test.crypto.cipher.TestCipherDoFinal;
import ar.org.fitc.test.crypto.cipher.TestCipherStatic;
import ar.org.fitc.test.crypto.cipher.TestCipherUnwrap;
import ar.org.fitc.test.crypto.cipher.TestCipherUpdate;
import ar.org.fitc.test.crypto.cipher.TestCipherUpdateContinue;
import ar.org.fitc.test.crypto.cipher.TestCipherWrap;
import ar.org.fitc.test.crypto.spec.TestDESKeySpec;
import ar.org.fitc.test.crypto.spec.TestDESedeKeySpec;
import ar.org.fitc.test.crypto.spec.TestDHGenParameterSpec;
import ar.org.fitc.test.crypto.spec.TestDHParameterSpec;
import ar.org.fitc.test.crypto.spec.TestDHPrivateKeySpec;
import ar.org.fitc.test.crypto.spec.TestDHPublicKeySpec;
import ar.org.fitc.test.crypto.spec.TestIvParameterSpec;
import ar.org.fitc.test.crypto.spec.TestOAEPParameterSpec;
import ar.org.fitc.test.crypto.spec.TestPBEKeySpec;
import ar.org.fitc.test.crypto.spec.TestPBEParameterSpec;
import ar.org.fitc.test.crypto.spec.TestPSourcePSpecified;
import ar.org.fitc.test.crypto.spec.TestRC2ParameterSpec;
import ar.org.fitc.test.crypto.spec.TestRC5ParameterSpec;
import ar.org.fitc.test.crypto.spec.TestSecretKeySpec;
import ar.org.fitc.test.integration.IntegrationTestRunner;

public class NewPerformaceTests {

	public static void main(String[] args) throws NoSuchAlgorithmException {
        junit.textui.TestRunner.run(NewPerformaceTests.suite());
    }
	
	public static Test suite() throws NoSuchAlgorithmException {
        int i = 150;
        TestSuite suite = new TestSuite("Performance Crypto");
                
        suite.addTest(new RepeatedTest(new TestEncryptedPrivateKeyInfo("testEncryptedPrivateKeyInfoStringByteArray001")  , i));
        suite.addTest(new RepeatedTest(new TestEncryptedPrivateKeyInfo("testEncryptedPrivateKeyInfoStringByteArray002")  , i));
        suite.addTest(new RepeatedTest(new TestEncryptedPrivateKeyInfo("testEncryptedPrivateKeyInfoByteArray002")  , i));
        suite.addTest(new RepeatedTest(new TestEncryptedPrivateKeyInfo("testEncryptedPrivateKeyInfoByteArray003")  , i));
        suite.addTest(new RepeatedTest(new TestEncryptedPrivateKeyInfo("testEncryptedPrivateKeyInfoAlgorithmParametersByteArray001")  , i));
        suite.addTest(new RepeatedTest(new TestEncryptedPrivateKeyInfo("testEncryptedPrivateKeyInfoAlgorithmParametersByteArray002")  , i));
        suite.addTest(new RepeatedTest(new TestEncryptedPrivateKeyInfo("testGetAlgName001")  , i));
        suite.addTest(new RepeatedTest(new TestEncryptedPrivateKeyInfo("testGetAlgParameters002")  , i));
        suite.addTest(new RepeatedTest(new TestEncryptedPrivateKeyInfo("testGetEncryptedData001")  , i));
        suite.addTest(new RepeatedTest(new TestEncryptedPrivateKeyInfo("testGetEncryptedData006")  , i));
        suite.addTest(new RepeatedTest(new TestEncryptedPrivateKeyInfo("testGetEncoded001")  , i));               
        
        suite.addTest(new RepeatedTest(new TestKeyAgreement("testGetAlgorithm001")  , i));        
        suite.addTest(new RepeatedTest(new TestKeyAgreement("testGetInstanceString001")  , i));
        suite.addTest(new RepeatedTest(new TestKeyAgreement("testGetInstanceString003")  , i));
        suite.addTest(new RepeatedTest(new TestKeyAgreement("testGetInstanceStringString001")  , i));
        suite.addTest(new RepeatedTest(new TestKeyAgreement("testGetInstanceStringString002")  , i));
        suite.addTest(new RepeatedTest(new TestKeyAgreement("testGetInstanceStringProvider001")  , i));
        suite.addTest(new RepeatedTest(new TestKeyAgreement("testGetInstanceStringProvider002")  , i));
        suite.addTest(new RepeatedTest(new TestKeyAgreement("testGetProvider")  , i));
        suite.addTest(new RepeatedTest(new TestKeyAgreement("testInitKey001")  , i));
        suite.addTest(new RepeatedTest(new TestKeyAgreement("testInitKey002")  , i));
        suite.addTest(new RepeatedTest(new TestKeyAgreement("testInitKeySecureRandom001")  , i));
        suite.addTest(new RepeatedTest(new TestKeyAgreement("testInitKeySecureRandom002")  , i));
        suite.addTest(new RepeatedTest(new TestKeyAgreement("testInitKeyAlgorithmParameterSpec001")  , i));
        suite.addTest(new RepeatedTest(new TestKeyAgreement("testInitKeyAlgorithmParameterSpec006")  , i));
        suite.addTest(new RepeatedTest(new TestKeyAgreement("testInitKeyAlgorithmParameterSpecSecureRandom001")  , i));
        suite.addTest(new RepeatedTest(new TestKeyAgreement("testInitKeyAlgorithmParameterSpecSecureRandom002")  , i));
        suite.addTest(new RepeatedTest(new TestKeyAgreement("testDoPhase007")  , i));
        suite.addTest(new RepeatedTest(new TestKeyAgreement("testDoPhase010")  , i));
        suite.addTest(new RepeatedTest(new TestKeyAgreement("testGenerateSecret005")  , i));
        suite.addTest(new RepeatedTest(new TestKeyAgreement("testGenerateSecret002")  , i));
        suite.addTest(new RepeatedTest(new TestKeyAgreement("testGenerateSecretByteArrayInt001")  , i));
        suite.addTest(new RepeatedTest(new TestKeyAgreement("testGenerateSecretByteArrayInt002")  , i));
        suite.addTest(new RepeatedTest(new TestKeyAgreement("testGenerateSecretString001")  , i));
        suite.addTest(new RepeatedTest(new TestKeyAgreement("testGenerateSecretString002")  , i));
        
        suite.addTest(new RepeatedTest(new TestKeyGenerator("testGetAlgorithm001")  , i));
        suite.addTest(new RepeatedTest(new TestKeyGenerator("testGetInstanceString003")  , i));
        suite.addTest(new RepeatedTest(new TestKeyGenerator("testGetInstanceString001")  , i));
        suite.addTest(new RepeatedTest(new TestKeyGenerator("testGetInstanceStringString001")  , i));
        suite.addTest(new RepeatedTest(new TestKeyGenerator("testGetInstanceStringString002")  , i));
        suite.addTest(new RepeatedTest(new TestKeyGenerator("testGetInstanceStringProvider001")  , i));
        suite.addTest(new RepeatedTest(new TestKeyGenerator("testGetInstanceStringProvider002")  , i));
        suite.addTest(new RepeatedTest(new TestKeyGenerator("testGetProvider001")  , i));
        suite.addTest(new RepeatedTest(new TestKeyGenerator("testInitSecureRandom001")  , i));
        suite.addTest(new RepeatedTest(new TestKeyGenerator("testInitAlgorithmParameterSpec001")  , i));
        suite.addTest(new RepeatedTest(new TestKeyGenerator("testInitAlgorithmParameterSpec002")  , i));
        suite.addTest(new RepeatedTest(new TestKeyGenerator("testInitAlgorithmParameterSpecSecureRandom001")  , i));
        suite.addTest(new RepeatedTest(new TestKeyGenerator("testInitInt001")  , i));
        suite.addTest(new RepeatedTest(new TestKeyGenerator("testInitInt002")  , i));
        suite.addTest(new RepeatedTest(new TestKeyGenerator("testInitIntSecureRandom001")  , i));
        suite.addTest(new RepeatedTest(new TestKeyGenerator("testGenerateKey001")  , i));
        
        suite.addTest(new RepeatedTest(new TestMac("testGetInstanceString001")  , i));
        suite.addTest(new RepeatedTest(new TestMacStatic("testGetInstanceString005")  , i));
        suite.addTest(new RepeatedTest(new TestMac("testGetInstanceStringString001")  , i));
        suite.addTest(new RepeatedTest(new TestMacStatic("testGetInstanceStringString004")  , i));
        suite.addTest(new RepeatedTest(new TestMac("testGetInstanceStringProvider001")  , i));
        suite.addTest(new RepeatedTest(new TestMacStatic("testGetInstanceStringProvider003")  , i));        
        suite.addTest(new RepeatedTest(new TestMacStatic("testGetAlgorithm001")  , i));
        suite.addTest(new RepeatedTest(new TestMacStatic("testGetMacLength001")  , i));
        suite.addTest(new RepeatedTest(new TestMacStatic("testGetProvider001")  , i));        
        suite.addTest(new RepeatedTest(new TestMac("testInitKey001")  , i));
        suite.addTest(new RepeatedTest(new TestMacStatic("testInitKey003")  , i));        
        suite.addTest(new RepeatedTest(new TestMacStatic("testInitKeyAlgorithmParameterSpec001")  , i));
        suite.addTest(new RepeatedTest(new TestMacStatic("testInitKeyAlgorithmParameterSpec005")  , i));
                
        suite.addTest(new RepeatedTest(new TestMacStatic("testUpdateByte002")  , i));
        suite.addTest(new RepeatedTest(new TestMacStatic("testUpdateByte029")  , i));
        suite.addTest(new RepeatedTest(new TestMac("testUpdateByteArray001")  , i));
        suite.addTest(new RepeatedTest(new TestMac("testUpdateByteArray030")  , i));
        suite.addTest(new RepeatedTest(new TestMac("testUpdateByteArrayIntInt006")  , i));
        suite.addTest(new RepeatedTest(new TestMacStatic("testUpdateByteArrayIntInt030")  , i));
        suite.addTest(new RepeatedTest(new TestMac("testUpdateByteBuffer002")  , i));
        suite.addTest(new RepeatedTest(new TestMacStatic("testUpdateByteBuffer016")  , i));
        suite.addTest(new RepeatedTest(new TestMacStatic("testUpdateByteBuffer034")  , i));
        suite.addTest(new RepeatedTest(new TestMacStatic("testClone004")  , i));
        suite.addTest(new RepeatedTest(new TestMacStatic("testCloneCloneNotSupportedException")  , i));
        
        suite.addTest(new RepeatedTest(new TestMacStatic("testDoFinal002")  , i));
        suite.addTest(new RepeatedTest(new TestMacStatic("testDoFinalIllegalStateException")  , i));
        suite.addTest(new RepeatedTest(new TestMac("testDoFinalByteArray002")  , i));
        suite.addTest(new RepeatedTest(new TestMacStatic("testDoFinalByteArrayIllegalStateException")  , i));
        suite.addTest(new RepeatedTest(new TestMacStatic("testDoFinalByteArrayInt006")  , i));
        suite.addTest(new RepeatedTest(new TestMacStatic("testDoFinalByteArrayInt012")  , i));
        suite.addTest(new RepeatedTest(new TestMacStatic("testClone004")  , i));
        suite.addTest(new RepeatedTest(new TestMacStatic("testCloneCloneNotSupportedException")  , i));
        
        
        suite.addTest(new RepeatedTest(new TestSealedObject("testSealedObjectSerializableCipher001")  , i));
        suite.addTest(new RepeatedTest(new TestSealedObject("testSealedObjectSerializableCipher002")  , i));
        suite.addTest(new RepeatedTest(new TestSealedObject("testGetAlgorithm001")  , i));
        suite.addTest(new RepeatedTest(new TestSealedObject("testGetObjectKey001")  , i));
        suite.addTest(new RepeatedTest(new TestSealedObject("testGetObjectKey002")  , i));
        suite.addTest(new RepeatedTest(new TestSealedObject("testGetObjectCipher001")  , i));
        suite.addTest(new RepeatedTest(new TestSealedObject("testGetObjectKeyString001")  , i));
        suite.addTest(new RepeatedTest(new TestSealedObject("testGetObjectKeyString003")  , i));
        
        suite.addTest(new RepeatedTest(new TestSecretKeyFactory("testGetInstanceString001")  , i));
        suite.addTest(new RepeatedTest(new TestSecretKeyFactory("testGetInstanceString003")  , i));
        suite.addTest(new RepeatedTest(new TestSecretKeyFactory("testGetInstanceStringString002")  , i));
        suite.addTest(new RepeatedTest(new TestSecretKeyFactory("testGetInstanceStringString003")  , i));
        suite.addTest(new RepeatedTest(new TestSecretKeyFactory("testGetInstanceStringProvider001")  , i));
        suite.addTest(new RepeatedTest(new TestSecretKeyFactory("testGetInstanceStringProvider005")  , i));
        suite.addTest(new RepeatedTest(new TestSecretKeyFactory("testGenerateSecret001")  , i));
        suite.addTest(new RepeatedTest(new TestSecretKeyFactory("testGenerateSecret002")  , i));
   
        suite.addTest(new RepeatedTest(new TestSecretKeyFactory("testGetKeySpec001")  , i));
        suite.addTest(new RepeatedTest(new TestSecretKeyFactory("testGetKeySpec003")  , i));
        suite.addTest(new RepeatedTest(new TestSecretKeyFactory("testGetProvider")  , i));
        suite.addTest(new RepeatedTest(new TestSecretKeyFactory("testGetAlgorithm")  , i));
        suite.addTest(new RepeatedTest(new TestSecretKeyFactory("testTranslateKey001")  , i));
        suite.addTest(new RepeatedTest(new TestSecretKeyFactory("testTranslateKey002")  , i));

       
        suite.addTest(new RepeatedTest(new TestCipherInputStream("testCipherInputStreamConstructor004")  , i));
        suite.addTest(new RepeatedTest(new TestCipherInputStream("testCipherInputStreamConstructor003")  , i));
        suite.addTest(new RepeatedTest(new TestCipherInputStream("testAvailable002")  , i));
        suite.addTest(new RepeatedTest(new TestCipherInputStream("testClose001")  , i));
        suite.addTest(new RepeatedTest(new TestCipherInputStream("testClose002")  , i));        
        suite.addTest(new RepeatedTest(new TestCipherInputStream("testMarkSupported001")  , i));
        suite.addTest(new RepeatedTest(new TestCipherInputStream("testMarkSupported002")  , i));
        suite.addTest(new RepeatedTest(new TestCipherInputStream("testRead001")  , i));        
        suite.addTest(new RepeatedTest(new TestCipherInputStream("testRead002")  , i));
        suite.addTest(new RepeatedTest(new TestCipherInputStream("testRead003")  , i));
        suite.addTest(new RepeatedTest(new TestCipherInputStream("testRead005")  , i));
        suite.addTest(new RepeatedTest(new TestCipherInputStream("testRead007")  , i));
        suite.addTest(new RepeatedTest(new TestCipherInputStream("testSkip001")  , i));
        suite.addTest(new RepeatedTest(new TestCipherInputStream("testSkip003")  , i));
        
        suite.addTest(new RepeatedTest(new TestCipherOutputStream("testCipherOutputStream005")  , i));
        suite.addTest(new RepeatedTest(new TestCipherOutputStream("testCipherOutputStream003")  , i));
        suite.addTest(new RepeatedTest(new TestCipherOutputStream("testClose001")  , i));
        suite.addTest(new RepeatedTest(new TestCipherOutputStream("testClose002")  , i));
        suite.addTest(new RepeatedTest(new TestCipherOutputStream("testFlush001")  , i));
        suite.addTest(new RepeatedTest(new TestCipherOutputStream("testFlush002")  , i));
        suite.addTest(new RepeatedTest(new TestCipherOutputStream("testWriteInt001")  , i));
        suite.addTest(new RepeatedTest(new TestCipherOutputStream("testWriteInt002")  , i));        
        suite.addTest(new RepeatedTest(new TestCipherOutputStream("testWriteByteArrayIntInt027")  , i));
        suite.addTest(new RepeatedTest(new TestCipherOutputStream("testWriteByteArrayIntInt002")  , i));
        suite.addTest(new RepeatedTest(new TestCipherOutputStream("testWriteByteArray002")  , i));
        suite.addTest(new RepeatedTest(new TestCipherOutputStream("testWriteByteArray003")  , i));
        
        suite.addTest(new RepeatedTest(new TestCipherDinamic("testGetInstanceString004")  , i));
        suite.addTest(new RepeatedTest(new TestCipherStatic("testGetInstanceString002")  , i));
        suite.addTest(new RepeatedTest(new TestCipherStatic("testGetInstanceStringString003")  , i));
        suite.addTest(new RepeatedTest(new TestCipherDinamic("testGetInstanceStringString001")  , i));
        suite.addTest(new RepeatedTest(new TestCipherStatic("testGetInstanceStringProvider004")  , i));
        suite.addTest(new RepeatedTest(new TestCipherDinamic("testGetInstanceStringProvider001")  , i));
        suite.addTest(new RepeatedTest(new TestCipherDinamic("testGetProvider001")  , i));
        suite.addTest(new RepeatedTest(new TestCipherDinamic("testGetAlgorithm001")  , i));
        suite.addTest(new RepeatedTest(new TestCipherStatic("testGetBlockSize001")  , i));
        suite.addTest(new RepeatedTest(new TestCipherStatic("testGetBlockSize002")  , i));
        suite.addTest(new RepeatedTest(new TestCipherDinamic("testGetOutputSize007")  , i));
        suite.addTest(new RepeatedTest(new TestCipherStatic("testGetOutputSize001")  , i));
        
        suite.addTest(new RepeatedTest(new TestCipherStatic("testGetIV001")  , i));
        suite.addTest(new RepeatedTest(new TestCipherStatic("testGetIV004")  , i));
        
        suite.addTest(new RepeatedTest(new TestCipherStatic("testGetParameters004")  , i));
        suite.addTest(new RepeatedTest(new TestCipherStatic("testGetParameters005")  , i));
        suite.addTest(new RepeatedTest(new TestCipherDinamic("testGetExemptionMechanism001")  , i));
        
        suite.addTest(new RepeatedTest(new TestCipherStatic("testInitIntKeyAlgorithmParametersSpec004")  , i));
        suite.addTest(new RepeatedTest(new TestCipherStatic("testInitIntKeyAlgorithmParametersSpec001")  , i));
        suite.addTest(new RepeatedTest(new TestCipherStatic("testInitIntKeyAlgorithmParametersSpecSecureRandom007")  , i));
        suite.addTest(new RepeatedTest(new TestCipherStatic("testInitIntKeyAlgorithmParametersSpecSecureRandom002")  , i));
        suite.addTest(new RepeatedTest(new TestCipherStatic("testInitIntKeyAlgorithmParametersSecureRandom007")  , i));
        suite.addTest(new RepeatedTest(new TestCipherStatic("testInitIntKeyAlgorithmParametersSecureRandom002")  , i));
        suite.addTest(new RepeatedTest(new TestCipherStatic("testInitIntKeyAlgorithmParameters005")  , i));
        suite.addTest(new RepeatedTest(new TestCipherStatic("testInitIntKeyAlgorithmParameters003")  , i));
        
        suite.addTest(new RepeatedTest(new TestCipherStatic("testUpdateByteArray010")  , i));
        suite.addTest(new RepeatedTest(new TestCipherUpdate("testUpdateByteArray002")  , i));
        
        suite.addTest(new RepeatedTest(new TestCipherStatic("testUpdateByteArrayIntInt080")  , i));
        suite.addTest(new RepeatedTest(new TestCipherUpdate("testUpdateByteArrayIntInt074")  , i));
        suite.addTest(new RepeatedTest(new TestCipherStatic("testUpdateByteArrayIntIntByteArray348")  , i));
        suite.addTest(new RepeatedTest(new TestCipherUpdate("testUpdateByteArrayIntIntByteArray039")  , i));
        suite.addTest(new RepeatedTest(new TestCipherUpdateContinue("testUpdateByteArrayIntIntByteArrayInt026")  , i));
        suite.addTest(new RepeatedTest(new TestCipherUpdateContinue("testUpdateByteArrayIntIntByteArrayInt362")  , i));        
        
        
        suite.addTest(new RepeatedTest(new TestCipherUpdateContinue("testUpdateByteBufferByteBuffer001")  , i));
        suite.addTest(new RepeatedTest(new TestCipherUpdateContinue("testUpdateByteBufferByteBuffer032")  , i));
        suite.addTest(new RepeatedTest(new TestCipherDoFinal("testdoFinal001")  , i));
        suite.addTest(new RepeatedTest(new TestCipherDoFinal("testdoFinal011")  , i));
        suite.addTest(new RepeatedTest(new TestCipherDoFinal("testdoFinalByteArrayInt001")  , i));
        suite.addTest(new RepeatedTest(new TestCipherDoFinal("testdoFinalByteArrayInt005")  , i));
        suite.addTest(new RepeatedTest(new TestCipherDoFinal("testdoFinalByteArray006")  , i));
        suite.addTest(new RepeatedTest(new TestCipherDoFinal("testdoFinalByteArray005")  , i));
        suite.addTest(new RepeatedTest(new TestCipherDoFinal("testdoFinalByteArrayIntInt001")  , i));
        suite.addTest(new RepeatedTest(new TestCipherDoFinal("testdoFinalByteArrayIntInt007")  , i));
        suite.addTest(new RepeatedTest(new TestCipherDoFinal("testdoFinalByteArrayIntIntByteArray010")  , i));
        suite.addTest(new RepeatedTest(new TestCipherDoFinal("testdoFinalByteArrayIntIntByteArray011")  , i));     
        
        suite.addTest(new RepeatedTest(new TestCipherDoFinal("testdoFinalByteArrayIntIntByteArrayInt013")  , i));
        suite.addTest(new RepeatedTest(new TestCipherDoFinal("testdoFinalByteArrayIntIntByteArrayInt017")  , i));
        suite.addTest(new RepeatedTest(new TestCipherDoFinal("testdoFinalByteBufferByteBuffer210")  , i));
        suite.addTest(new RepeatedTest(new TestCipherDoFinal("testdoFinalByteBufferByteBuffer209")  , i));   
        
                
        suite.addTest(new RepeatedTest(new TestCipherStatic("testGetMaxAllowedKeyLength001")  , i));
        suite.addTest(new RepeatedTest(new TestCipherStatic("testGetMaxAllowedKeyLength003")  , i));
        

        suite.addTest(new RepeatedTest(new TestCipherWrap("testWrap001")  , i));
        suite.addTest(new RepeatedTest(new TestCipherWrap("testWrap007")  , i));

        suite.addTest(new RepeatedTest(new TestCipherUnwrap("testUnwrap001")  , i));
        suite.addTest(new RepeatedTest(new TestCipherUnwrap("testUnwrap177")  , i));
        
        suite.addTest(new RepeatedTest(new TestCipherAndCertificate("testInitIntKeyCertificate001")  , i));
        suite.addTest(new RepeatedTest(new TestCipherAndCertificate("testInitIntKeyCertificateSecureRandom001")  , i));
        suite.addTest(new RepeatedTest(new TestCipherStatic("testInitIntKeyAlgorithmParametersSpec006")  , i));
        
        suite.addTest(new RepeatedTest(new TestDESedeKeySpec("testDESedeKeySpecByteArray003"), i));
        suite.addTest(new RepeatedTest(new TestDESedeKeySpec("testDESedeKeySpecByteArray002") , i));
        suite.addTest(new RepeatedTest(new TestDESedeKeySpec("testDESedeKeySpecByteArrayInt001")  , i));
        suite.addTest(new RepeatedTest(new TestDESedeKeySpec("testDESedeKeySpecByteArrayInt004")  , i));
        suite.addTest(new RepeatedTest(new TestDESedeKeySpec("testGetKey001") , i));
        suite.addTest(new RepeatedTest(new TestDESedeKeySpec("testIsParityAdjusted001")  , i));
   //Missing Exception    suite.addTest(new RepeatedTest(new DESedeKeySpec("testIsParityAdjusted001")  , i));
        
        
        suite.addTest(new RepeatedTest(new TestDESKeySpec("testDESKeySpecByteArray001")  , i));
        suite.addTest(new RepeatedTest(new TestDESKeySpec("testDESKeySpecByteArray003")  , i));
        suite.addTest(new RepeatedTest(new TestDESKeySpec("testDESKeySpecByteArrayInt002")  , i));
        suite.addTest(new RepeatedTest(new TestDESKeySpec("testDESKeySpecByteArrayInt004")  , i));
        suite.addTest(new RepeatedTest( new TestDESKeySpec("testGetKey001")  , i));
        suite.addTest(new RepeatedTest(new TestDESKeySpec("testIsParityAdjusted001")  , i));
        suite.addTest(new RepeatedTest(new TestDESKeySpec("testIsWeak001")  , i));
        
        suite.addTest(new RepeatedTest(new TestDHGenParameterSpec("testDHGenParameterSpec001")  , i));
        suite.addTest(new RepeatedTest(new TestDHGenParameterSpec("testGetPrimeSize001")  , i));
        suite.addTest(new RepeatedTest(new TestDHGenParameterSpec("testGetExponentSize001")  , i));
        suite.addTest(new RepeatedTest(new TestDHParameterSpec("testDHParameterSpecBigIntegerBigInteger001")  , i));
        suite.addTest(new RepeatedTest(new TestDHParameterSpec("testDHParameterSpecBigIntegerBigIntegerInt001")  , i));
        suite.addTest(new RepeatedTest(new TestDHParameterSpec("testGetP002")  , i));
        suite.addTest(new RepeatedTest(new TestDHParameterSpec("testGetG002")  , i));
        suite.addTest(new RepeatedTest(new TestDHParameterSpec("testGetL003")  , i));
        
        suite.addTest(new RepeatedTest(new TestDHPrivateKeySpec("testDHPrivateKeySpec001")  , i));
        suite.addTest(new RepeatedTest(new TestDHPrivateKeySpec("testGetX002")  , i));
        suite.addTest(new RepeatedTest(new TestDHPrivateKeySpec("testGetP002")  , i));
        suite.addTest(new RepeatedTest(new TestDHPrivateKeySpec("testGetG001")  , i));
        
        suite.addTest(new RepeatedTest(new TestDHPublicKeySpec("testDHPublicKeySpec001")  , i));
        suite.addTest(new RepeatedTest(new TestDHPublicKeySpec("testGetY003")  , i));
        suite.addTest(new RepeatedTest(new TestDHPublicKeySpec("testGetP001")  , i));
        suite.addTest(new RepeatedTest(new TestDHPublicKeySpec("testGetG003")  , i));
        
        suite.addTest(new RepeatedTest(new TestIvParameterSpec("testIvParameterSpecByteArray001")  , i));
        suite.addTest(new RepeatedTest(new TestIvParameterSpec("testIvParameterSpecByteArray003")  , i));
        suite.addTest(new RepeatedTest(new TestIvParameterSpec("testIvParameterSpecByteArrayIntInt009")  , i));
        suite.addTest(new RepeatedTest(new TestIvParameterSpec("testIvParameterSpecByteArrayIntInt015")  , i));        
        suite.addTest(new RepeatedTest(new TestIvParameterSpec("testGetIV008")  , i));

        
        suite.addTest(new RepeatedTest(new TestOAEPParameterSpec("testOAEPParameterSpec003")  , i));
        suite.addTest(new RepeatedTest(new TestOAEPParameterSpec("testOAEPParameterSpec004")  , i));
        suite.addTest(new RepeatedTest(new TestOAEPParameterSpec("testGetDigestAlgorithm002")  , i));
        suite.addTest(new RepeatedTest(new TestOAEPParameterSpec("testGetMGFAlgorithm001")  , i));
        suite.addTest(new RepeatedTest(new TestOAEPParameterSpec("testGetMGFParameters002")  , i));
        suite.addTest(new RepeatedTest(new TestOAEPParameterSpec("testGetPSource001")  , i));
        
        suite.addTest(new RepeatedTest(new TestOAEPParameterSpec("testOAEPParameterSpec003")  , i));
        suite.addTest(new RepeatedTest(new TestOAEPParameterSpec("testOAEPParameterSpec004")  , i));
        suite.addTest(new RepeatedTest(new TestOAEPParameterSpec("testGetDigestAlgorithm002")  , i));
        suite.addTest(new RepeatedTest(new TestOAEPParameterSpec("testGetMGFAlgorithm001")  , i));
        suite.addTest(new RepeatedTest(new TestOAEPParameterSpec("testGetMGFParameters002")  , i));
        suite.addTest(new RepeatedTest(new TestOAEPParameterSpec("testGetPSource001")  , i));
        
        suite.addTest(new RepeatedTest(new TestPBEKeySpec("testPBEKeySpecCharArray006")  , i));
        suite.addTest(new RepeatedTest(new TestPBEKeySpec("testPBEKeySpecCharArrayByteArrayInt007")  , i));
        suite.addTest(new RepeatedTest(new TestPBEKeySpec("testPBEKeySpecCharArrayByteArrayInt012")  , i));
        suite.addTest(new RepeatedTest(new TestPBEKeySpec("testPBEKeySpecCharArrayByteArrayIntInt026")  , i));
        suite.addTest(new RepeatedTest(new TestPBEKeySpec("testPBEKeySpecCharArrayByteArrayIntInt033")  , i));
        suite.addTest(new RepeatedTest(new TestPBEKeySpec("testGetPassword008")  , i));        
        suite.addTest(new RepeatedTest(new TestPBEKeySpec("testGetPassword013")  , i));
        suite.addTest(new RepeatedTest(new TestPBEKeySpec("testGetSalt003")  , i));
        suite.addTest(new RepeatedTest(new TestPBEKeySpec("testGetIterationCount003")  , i));
        suite.addTest(new RepeatedTest(new TestPBEKeySpec("testGetKeyLength003")  , i));
        

        suite.addTest(new RepeatedTest(new TestPBEParameterSpec("testPBEParameterSpec007")  , i));
        suite.addTest(new RepeatedTest(new TestPBEParameterSpec("testPBEParameterSpec011")  , i));
        suite.addTest(new RepeatedTest(new TestPBEParameterSpec("testGetSalt001")  , i));
        suite.addTest(new RepeatedTest(new TestPBEParameterSpec("testGetIterationCount003")  , i));
        suite.addTest(new RepeatedTest(new TestPSourcePSpecified("testPSourcePSpecified001")  , i));
        suite.addTest(new RepeatedTest(new TestPSourcePSpecified("testPSourcePSpecified002")  , i));
        suite.addTest(new RepeatedTest(new TestPSourcePSpecified("testGetValue003")  , i));
     
        suite.addTest(new RepeatedTest(new TestRC2ParameterSpec("testRC2ParameterSpecInt004")  , i));
        suite.addTest(new RepeatedTest(new TestRC2ParameterSpec("testRC2ParameterSpecIntByteArray001")  , i));
        suite.addTest(new RepeatedTest(new TestRC2ParameterSpec("testRC2ParameterSpecIntByteArray002")  , i));
        suite.addTest(new RepeatedTest(new TestRC2ParameterSpec("testRC2ParameterSpecIntByteArrayInt002")  , i));
        suite.addTest(new RepeatedTest(new TestRC2ParameterSpec("testRC2ParameterSpecIntByteArrayInt004")  , i));
        suite.addTest(new RepeatedTest(new TestRC2ParameterSpec("testGetEffectiveKeyBits004")  , i));
        suite.addTest(new RepeatedTest(new TestRC2ParameterSpec("testGetIV002")  , i));
        suite.addTest(new RepeatedTest(new TestRC2ParameterSpec("testHashCode003")  , i));
        suite.addTest(new RepeatedTest(new TestRC2ParameterSpec("testEquals003")  , i));
        
        suite.addTest(new RepeatedTest(new TestRC5ParameterSpec("testRC5ParameterSpecIntIntInt019")  , i));        
        suite.addTest(new RepeatedTest(new TestRC5ParameterSpec("testRC5ParameterSpecIntIntIntByteArray001")  , i)); 
        //missing without exception
        suite.addTest(new RepeatedTest(new TestRC5ParameterSpec("testRC5ParameterSpecIntIntIntByteArrayInt298")  , i));
        //missing without exception
        suite.addTest(new RepeatedTest(new TestRC5ParameterSpec("testGetVersion002")  , i));
        suite.addTest(new RepeatedTest(new TestRC5ParameterSpec("testGetRounds003")  , i));
        suite.addTest(new RepeatedTest(new TestRC5ParameterSpec("testGetWordSize001")  , i));
        suite.addTest(new RepeatedTest(new TestRC5ParameterSpec("testGetIV001")  , i));
        suite.addTest(new RepeatedTest(new TestRC5ParameterSpec("testEquals003")  , i));
        suite.addTest(new RepeatedTest(new TestRC5ParameterSpec("testHashCode018")  , i));
        
        suite.addTest(new RepeatedTest(new TestSecretKeySpec("testSecretKeySpecByteArrayString001")  , i));
        suite.addTest(new RepeatedTest(new TestSecretKeySpec("testSecretKeySpecByteArrayString002")  , i));
        suite.addTest(new RepeatedTest(new TestSecretKeySpec("testSecretKeySpecByteArrayIntIntString001")  , i));
        suite.addTest(new RepeatedTest(new TestSecretKeySpec("testSecretKeySpecByteArrayIntIntString002")  , i));
        suite.addTest(new RepeatedTest(new TestSecretKeySpec("testEquals001")  , i));
        suite.addTest(new RepeatedTest(new TestSecretKeySpec("testGetAlgorithm002")  , i));
        suite.addTest(new RepeatedTest(new TestSecretKeySpec("testGetEncoded004")  , i));
        suite.addTest(new RepeatedTest(new TestSecretKeySpec("testGetFormat001")  , i));
        suite.addTest(new RepeatedTest(new TestSecretKeySpec("testHashCode002")  , i));

        
        suite.addTest(new IntegrationTestRunner("testChat"));
        suite.addTest(new IntegrationTestRunner("testDHKeyAgreement"));
        suite.addTest(new IntegrationTestRunner("testBlockAndNeverContinue"));        
               
        return suite;
	}

}

