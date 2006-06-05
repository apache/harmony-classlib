package ar.org.fitc.test.crypto.cipher;

import java.security.InvalidParameterException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.Certificate;

import javax.crypto.Cipher;

import junit.framework.TestCase;
import ar.org.fitc.test.util.IteratorCertificate;


public class TestCipherAndCertificate extends TestCase {
    
    protected Cipher cipher = null;
    protected Provider provider = null;
    protected String providerString = "BC";
    protected String algorithm = "DES";
    public String mode = "CBC";
    public String padding = "NoPadding";
    protected String transformation = algorithm + "/" + mode + "/" + padding;
    Certificate cert =null;
    String msgError="";
    protected Class myThrowable;
    
    private void mustThrowException() {
        if (myThrowable != null) {
            fail("Bit String uses: " + msgError  + "Must throw " + myThrowable.getName());
        }
    }
    
    
    public TestCipherAndCertificate(String arg0, Certificate c, String msg, java.lang.Class exception) {
        super(arg0);
        cert = c;
        provider = Security.getProvider(providerString);
        msgError = msg;
        myThrowable = exception;
        
    }
    
    public TestCipherAndCertificate(String arg0, Certificate c, String i) {
        this(arg0, c, i, null);
    }
    public TestCipherAndCertificate(String arg0) {
        this(arg0, new IteratorCertificate().next(), "", null);      
    }
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestCipherAndCertificate.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        assertNotNull(cert);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     *  Test method for 'javax.crypto.Cipher.init(int, Certificate)'
     */
    
    @SuppressWarnings("unchecked")
    public final void testInitIntKeyCertificate001() {
        try {
            transformation = "RSA";
            cipher = Cipher.getInstance(transformation,provider);
            cipher.init(Cipher.WRAP_MODE, cert);
            mustThrowException();
        } catch (Throwable e) {
            if (myThrowable == null || !myThrowable.isAssignableFrom(e.getClass())) {
                fail("Bit String uses: " + msgError  + " fail with: " + e);
            }
        }
    }
    
    
    @SuppressWarnings("unchecked")
    public final void testInitIntKeyCertificate002() {
        try {
            transformation = "RSA";
            cipher = Cipher.getInstance(transformation,provider);
            cipher.init(Cipher.UNWRAP_MODE, cert);
            mustThrowException();
        } catch (Throwable e) {
            if (myThrowable == null || !myThrowable.isAssignableFrom(e.getClass())) {
                fail("Bit String uses: " + msgError  + " fail with: " + e);
            }
        }
    }
    @SuppressWarnings("unchecked")
    public final void testInitIntKeyCertificate003() {
        try {
            transformation = "RSA";
            cipher = Cipher.getInstance(transformation,provider);
            cipher.init(Cipher.DECRYPT_MODE, cert);
            mustThrowException();
        } catch (Throwable e) {
            if (myThrowable == null || !myThrowable.isAssignableFrom(e.getClass())) {
                fail("Bit String uses: " + msgError  + " fail with: " + e);
            }
        }
    }
    @SuppressWarnings("unchecked")
    public final void testInitIntKeyCertificate004() {
        try {
            transformation = "RSA";
            cipher = Cipher.getInstance(transformation,provider);
            cipher.init(Cipher.ENCRYPT_MODE, cert);
            mustThrowException();
        } catch (Throwable e) {
            if (myThrowable == null || !myThrowable.isAssignableFrom(e.getClass())) {
                fail("Bit String uses: " + msgError  + " fail with: " + e);
            }
        }
    }
    @SuppressWarnings("unchecked")
    public final void testInitIntKeyCertificate005() {
        try {
            transformation = "RSA";
            cipher = Cipher.getInstance(transformation,provider);
            cipher.init(15, cert); 
            fail("Must throw InvalidParameterException");
        } catch (InvalidParameterException e) {
        } catch (Throwable e) {
            if (myThrowable == null || !myThrowable.isAssignableFrom(e.getClass())) {
                fail("Bit String uses: " + msgError  + " fail with: " + e);
            }
        }
    }
        
    /*
     * Test method for 'javax.crypto.Cipher.init(int, Certificate, SecureRandom)'
     */
    
    @SuppressWarnings("unchecked")
    public final void testInitIntKeyCertificateSecureRandom001() {
        try {
            transformation = "RSA";
            cipher = Cipher.getInstance(transformation,provider);
            cipher.init(Cipher.WRAP_MODE, cert, null);
            mustThrowException();
        } catch (Throwable e) {
            if (myThrowable == null || !myThrowable.isAssignableFrom(e.getClass())) {
                fail("Bit String uses: " + msgError  + " fail with: " + e);
            }
        }
    }
    
    
    @SuppressWarnings("unchecked")
    public final void testInitIntKeyCertificateSecureRandom002() {
        try {
            transformation = "RSA";
            cipher = Cipher.getInstance(transformation,provider);
            cipher.init(Cipher.UNWRAP_MODE, cert, null);
            mustThrowException();
        } catch (Throwable e) {
            if (myThrowable == null || !myThrowable.isAssignableFrom(e.getClass())) {
                fail("Bit String uses: " + msgError  + " fail with: " + e);
            }
        }
    }
    @SuppressWarnings("unchecked")
    public final void testInitIntKeyCertificateSecureRandom003() {
        try {
            transformation = "RSA";
            cipher = Cipher.getInstance(transformation,provider);
            cipher.init(Cipher.DECRYPT_MODE, cert, null);
            mustThrowException();
        } catch (Throwable e) {
            if (myThrowable == null || !myThrowable.isAssignableFrom(e.getClass())) {
                fail("Bit String uses: " + msgError  + " fail with: " + e);
            }
        }
    }
    @SuppressWarnings("unchecked")
    public final void testInitIntKeyCertificateSecureRandom004() {
        try {
            transformation = "RSA";
            cipher = Cipher.getInstance(transformation,provider);
            cipher.init(Cipher.ENCRYPT_MODE, cert, null); 
            mustThrowException();
        } catch (Throwable e) {
            if (myThrowable == null || !myThrowable.isAssignableFrom(e.getClass())) {
                fail("Bit String uses: " + msgError  + " fail with: " + e);
            }
        }
    }
    @SuppressWarnings("unchecked")
    public final void testInitIntKeyCertificateSecureRandom005() {
        try {
            transformation = "RSA";
            cipher = Cipher.getInstance(transformation,provider);
            cipher.init(15, cert, null);
            fail("Must throw InvalidParameterException");
            mustThrowException();
        } catch (InvalidParameterException e) {
        } catch (Throwable e) {
            if (myThrowable == null || !myThrowable.isAssignableFrom(e.getClass())) {
                fail("Bit String uses: " + msgError  + " fail with: " + e);
            }
        }
    }
    @SuppressWarnings("unchecked")
    public final void testInitIntKeyCertificateSecureRandom006() {
        try {
            transformation = "RSA";
            cipher = Cipher.getInstance(transformation,provider);
            cipher.init(Cipher.WRAP_MODE, cert, new SecureRandom());
            mustThrowException();
        } catch (Throwable e) {
            if (myThrowable == null || !myThrowable.isAssignableFrom(e.getClass())) {
                fail("Bit String uses: " + msgError  + " fail with: " + e);
            }
        }
    }
    
    
    @SuppressWarnings("unchecked")
    public final void testInitIntKeyCertificateSecureRandom007() {
        try {
            transformation = "RSA";
            cipher = Cipher.getInstance(transformation,provider);
            cipher.init(Cipher.UNWRAP_MODE, cert, new SecureRandom());
            mustThrowException();
        } catch (Throwable e) {
            if (myThrowable == null || !myThrowable.isAssignableFrom(e.getClass())) {
                fail("Bit String uses: " + msgError  + " fail with: " + e);
            }
        }
    }
    @SuppressWarnings("unchecked")
    public final void testInitIntKeyCertificateSecureRandom008() {
        try {
            transformation = "RSA";
            cipher = Cipher.getInstance(transformation,provider);
            cipher.init(Cipher.DECRYPT_MODE, cert, new SecureRandom());
            mustThrowException();
        } catch (Throwable e) {
            if (myThrowable == null || !myThrowable.isAssignableFrom(e.getClass())) {
                fail("Bit String uses: " + msgError  + " fail with: " + e);
            }
        }
    }
    @SuppressWarnings("unchecked")
    public final void testInitIntKeyCertificateSecureRandom009() {
        try {
            transformation = "RSA";
            cipher = Cipher.getInstance(transformation,provider);
            cipher.init(Cipher.ENCRYPT_MODE, cert, new SecureRandom());
            mustThrowException();
        } catch (Throwable e) {
            if (myThrowable == null || !myThrowable.isAssignableFrom(e.getClass())) {
                fail("Bit String uses: " + msgError  + " fail with: " + e);
            }
        }
    }
    @SuppressWarnings("unchecked")
    public final void testInitIntKeyCertificateSecureRandom010() {
        try {
            transformation = "RSA";
            cipher = Cipher.getInstance(transformation,provider);
            cipher.init(15, cert, new SecureRandom());
            fail("Must throw InvalidParameterException");
        } catch (InvalidParameterException e) {
        } catch (Throwable e) {
            if (myThrowable == null || !myThrowable.isAssignableFrom(e.getClass())) {
                fail("Bit String uses: " + msgError  + " fail with: " + e);
            }
        }
    }
}
