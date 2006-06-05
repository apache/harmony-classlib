package ar.org.fitc.test.crypto.spec;

import java.math.BigInteger;

import javax.crypto.spec.DHPublicKeySpec;

import ar.org.fitc.test.util.Messages;
import junit.framework.TestCase;

public class TestDHPublicKeySpec extends TestCase implements Messages {

    private DHPublicKeySpec DHPKs = null;
    private BigInteger p = new BigInteger("12893918237918723");
    private BigInteger g = new BigInteger("98709498734598174");
    private BigInteger y = new BigInteger("9999999999999999999999999999999999999999999999");
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestDHPublicKeySpec.class);
    }

    public TestDHPublicKeySpec(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'javax.crypto.spec.DHPublicKeySpec.DHPublicKeySpec(BigInteger, BigInteger, BigInteger)'
     */
    public final void testDHPublicKeySpec001() {
        DHPKs = new DHPublicKeySpec(y,p,g);
        assertTrue(msgNotInstance+"DHPublicKeySpec",DHPKs instanceof DHPublicKeySpec);
    }

    /*
     * Test method for 'javax.crypto.spec.DHPublicKeySpec.getY()'
     */
    public final void testGetY001() {
        testDHPublicKeySpec001();
        assertEquals(msgNotSame,y,DHPKs.getY());
    }

    public final void testGetY002() {
        y = new BigInteger("99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
        testDHPublicKeySpec001();
        assertEquals(msgNotSame,y,DHPKs.getY());
    }
    
    public final void testGetY003() {
        y = new BigInteger("-99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
        testDHPublicKeySpec001();
        assertEquals(msgNotSame,y,DHPKs.getY());
    }

    /*
     * Test method for 'javax.crypto.spec.DHPublicKeySpec.getP()'
     */
    public final void testGetP001() {
        testDHPublicKeySpec001();
        assertEquals(msgNotSame,p,DHPKs.getP());
    }
    
    public final void testGetP002() {
        p = new BigInteger("99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
        testDHPublicKeySpec001();
        assertEquals(msgNotSame,p,DHPKs.getP());
    }
    
    public final void testGetP003() {
        p = new BigInteger("-99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
        testDHPublicKeySpec001();
        assertEquals(msgNotSame,p,DHPKs.getP());
    }

    /*
     * Test method for 'javax.crypto.spec.DHPublicKeySpec.getG()'
     */
    public final void testGetG001() {
        testDHPublicKeySpec001();
        assertEquals(msgNotSame,g,DHPKs.getG());
    }
    
    public final void testGetG002() {
        g = new BigInteger("99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
        testDHPublicKeySpec001();
        assertEquals(msgNotSame,g,DHPKs.getG());
    }

    public final void testGetG003() {
        g = new BigInteger("-99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
        testDHPublicKeySpec001();
        assertEquals(msgNotSame,g,DHPKs.getG());
    }
}
