package ar.org.fitc.test.crypto.cipher;

import java.lang.reflect.Method;
import java.security.InvalidKeyException;
import java.security.cert.Certificate;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import ar.org.fitc.test.util.IteratorCertificate;

public class TestCipherAndCertificateAll extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestCipherAndCertificateAll.suite());
    }

    public static Test suite () {
        TestSuite test = new TestSuite();
        IteratorCertificate cg = new IteratorCertificate();
        while(cg.hasNext()) {
            Class c = TestCipherAndCertificate.class;
            Method[] ms = c.getMethods();
            Certificate cert = cg.next();
            for (int i=0; i < ms.length; i++) {
                String name = ms[i].getName();
                if (name.startsWith("test")) {
                    if (name.endsWith("1") || name.endsWith("6")) { 
                        if (cg.isCriticalkeyusage() && !cg.isKeyEnciphermentOn()) {
                                //|| ((cg.uses & KeyUsage.cRLSign) == KeyUsage.cRLSign))) {
                            test.addTest(new TestCipherAndCertificate(name, cert, cg.keyUses2String(), InvalidKeyException.class));
                            continue;
                        } 
                    } else if(name.endsWith("4") || name.endsWith("9")) {
                        if (cg.isCriticalkeyusage() && !cg.isDataEnciphermentOn()) {
                            //|| ((cg.uses & KeyUsage.cRLSign) == KeyUsage.cRLSign))) {
                        test.addTest(new TestCipherAndCertificate(name, cert, cg.keyUses2String(), InvalidKeyException.class));
                        continue;
                        }    
                    }
                        
                    test.addTest(new TestCipherAndCertificate(name, cert, cg.keyUses2String()));
                    
                    
                }
            }
        }
            
        
        return test;
    }
    
    
    
}
