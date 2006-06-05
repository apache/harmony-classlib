package ar.org.fitc.test.whitebox;

import junit.framework.Test;
import junit.framework.TestSuite;

public class WhiteBoxAllTests {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(WhiteBoxAllTests.suite());
    }

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for ar.org.fitc.test.whitebox");
        //$JUnit-BEGIN$
        suite.addTestSuite(TestMac.class);
        suite.addTestSuite(TestCipherInputStream.class);
        suite.addTestSuite(TestCipher.class);
        suite.addTestSuite(TestNullCipher.class);
        suite.addTestSuite(TestEncryptedPrivateKeyInfo.class);
        suite.addTestSuite(TestSealedObject.class);
        //$JUnit-END$
        return suite;
    }

}
