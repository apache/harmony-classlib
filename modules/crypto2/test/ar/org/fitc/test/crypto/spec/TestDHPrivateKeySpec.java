package ar.org.fitc.test.crypto.spec;

import java.math.BigInteger;
import javax.crypto.spec.DHPrivateKeySpec;
import ar.org.fitc.test.util.Messages;
import junit.framework.TestCase;

public class TestDHPrivateKeySpec extends TestCase implements Messages{

    private DHPrivateKeySpec DHPKs = null;
    private BigInteger p = new BigInteger("12893918237918723");
    private BigInteger g = new BigInteger("98709498734598174");
    private BigInteger x = new BigInteger("9999999999999999999999999999999999999999999999");
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestDHPrivateKeySpec.class);
    }

    public TestDHPrivateKeySpec(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'javax.crypto.spec.DHPrivateKeySpec.DHPrivateKeySpec(BigInteger, BigInteger, BigInteger)'
     */
    public final void testDHPrivateKeySpec001() {
        DHPKs = new DHPrivateKeySpec(x,p,g);
        assertTrue(msgNotInstance+"DHPrivateKeySpec",DHPKs instanceof DHPrivateKeySpec);
    }

    /*
     * Test method for 'javax.crypto.spec.DHPrivateKeySpec.getX()'
     */
    public final void testGetX001() {
        testDHPrivateKeySpec001();
        assertEquals(msgNotSame,x,DHPKs.getX());
    }

    public final void testGetX002() {
        x = new BigInteger("99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
        testDHPrivateKeySpec001();
        assertEquals(msgNotSame,x,DHPKs.getX());
    }
    
    public final void testGetX003() {
        x = new BigInteger("-99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
        testDHPrivateKeySpec001();
        assertEquals(msgNotSame,x,DHPKs.getX());
    }
    
    /*
     * Test method for 'javax.crypto.spec.DHPrivateKeySpec.getP()'
     */
    public final void testGetP001() {
        testDHPrivateKeySpec001();
        assertEquals(msgNotSame,p,DHPKs.getP());
    }
    
    public final void testGetP002() {
        p = new BigInteger("99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
        testDHPrivateKeySpec001();
        assertEquals(msgNotSame,p,DHPKs.getP());
    }
    
    public final void testGetP003() {
        p = new BigInteger("-99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
        testDHPrivateKeySpec001();
        assertEquals(msgNotSame,p,DHPKs.getP());
    }

    /*
     * Test method for 'javax.crypto.spec.DHPrivateKeySpec.getG()'
     */
    public final void testGetG001() {
        testDHPrivateKeySpec001();
        assertEquals(msgNotSame,g,DHPKs.getG());
    }
    
    public final void testGetG002() {
        g = new BigInteger("99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
        testDHPrivateKeySpec001();
        assertEquals(msgNotSame,g,DHPKs.getG());
    }

    public final void testGetG003() {
        g = new BigInteger("-99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
        testDHPrivateKeySpec001();
        assertEquals(msgNotSame,g,DHPKs.getG());
    }

}
