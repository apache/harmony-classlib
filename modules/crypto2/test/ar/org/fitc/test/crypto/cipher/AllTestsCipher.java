package ar.org.fitc.test.crypto.cipher;

import ar.org.fitc.test.util.TestSuiteAcumulable;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTestsCipher {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(AllTestsCipher.suite());
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(
                "Test for ar.org.fitc.test.crypto.cipher");
        //$JUnit-BEGIN$
        suite.addTest(new TestSuiteAcumulable(TestCipherUpdate.class));
        suite.addTest(new TestSuiteAcumulable(TestCipherWrap.class));
        suite.addTest(new TestSuiteAcumulable(TestCipherStatic.class));
        suite.addTest(new TestSuiteAcumulable(TestCipherUnwrap.class));
        suite.addTest(new TestSuiteAcumulable(TestCipherUpdateContinue.class));
        suite.addTest(new TestSuiteAcumulable(TestCipherDoFinal.class));
        //$JUnit-END$
        return suite;
    }

}

