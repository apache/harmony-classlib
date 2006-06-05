package ar.org.fitc.test.crypto;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import ar.org.fitc.test.crypto.cipher.AllTestsCipher;
import ar.org.fitc.test.util.TestSuiteAcumulable;
import ar.org.fitc.test.whitebox.WhiteBoxAllTests;

public class AllTestsCrypto extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(AllTestsCrypto.suite());
    }

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for ar.org.fitc.test.crypto");
        suite.addTest(new TestSuiteAcumulable(TestCipherInputStream.class));
        suite.addTest(new TestSuiteAcumulable(TestCipherOutputStream.class));
        suite.addTest(new TestSuiteAcumulable(TestEncryptedPrivateKeyInfo.class));
        suite.addTest(new TestSuiteAcumulable(TestKeyAgreement.class));
        suite.addTest(new TestSuiteAcumulable(TestKeyGenerator.class));
        suite.addTest(new TestSuiteAcumulable(TestMac.class));
        suite.addTest(new TestSuiteAcumulable(TestMacStatic.class));
        suite.addTest(new TestSuiteAcumulable(TestSealedObject.class));
        suite.addTest(new TestSuiteAcumulable(TestSecretKeyFactory.class));
        suite.addTest(AllTestsCipher.suite());
        suite.addTest(WhiteBoxAllTests.suite());
        return suite;
    }

}
