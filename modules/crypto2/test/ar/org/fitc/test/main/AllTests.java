package ar.org.fitc.test.main;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import ar.org.fitc.test.crypto.AllTestsCrypto;
import ar.org.fitc.test.crypto.spec.TestSuiteCryptoSpec;

public class AllTests extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(AllTests.suite());
    }

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for ar.org.fitc.test.crypto.spec");
        //$JUnit-BEGIN$
        suite.addTest(TestSuiteCryptoSpec.suite());
        suite.addTest(AllTestsCrypto.suite());
        return suite;
    }

}
