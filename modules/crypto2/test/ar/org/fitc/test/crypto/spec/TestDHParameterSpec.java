package ar.org.fitc.test.crypto.spec;

import java.math.BigInteger;

import javax.crypto.spec.DHParameterSpec;

import ar.org.fitc.test.util.Messages;

import junit.framework.TestCase;

public class TestDHParameterSpec extends TestCase implements Messages {

    private DHParameterSpec DHps = null;
    private BigInteger p = new BigInteger("12893918237918723");
    private BigInteger g = new BigInteger("98709498734598174");
    private int l = 0;
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestDHParameterSpec.class);
    }

    public TestDHParameterSpec(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'javax.crypto.spec.DHParameterSpec.DHParameterSpec(BigInteger, BigInteger)'
     */                

    public final void testDHParameterSpecBigIntegerBigInteger001() {
        DHps = new DHParameterSpec(p,g);
        assertTrue(msgNotInstance+"DHParameterSpec",DHps instanceof DHParameterSpec);
    }

    /*
     * Test method for 'javax.crypto.spec.DHParameterSpec.DHParameterSpec(BigInteger, BigInteger, int)'
     */
    public final void testDHParameterSpecBigIntegerBigIntegerInt001() {
        l = 1024;
        DHps = new DHParameterSpec(p,g,l);
        assertTrue(msgNotInstance+"DHParameterSpec",DHps instanceof DHParameterSpec);
    }

    /*
     * Test method for 'javax.crypto.spec.DHParameterSpec.getP()'
     */
    public final void testGetP001() {
        testDHParameterSpecBigIntegerBigInteger001();
        assertEquals(msgNotSame,p,DHps.getP());
    }
    
    public final void testGetP002() {
        p = new BigInteger("19087409817093284790128759018475093709283740952874309528734095287304958720391231231231231236545767970697804752");
        testDHParameterSpecBigIntegerBigInteger001();
        assertEquals(msgNotSame,p,DHps.getP());
    }
    
    public final void testGetP003() {
        p = new BigInteger("-19087409817093284790128759018475093709283740952874309528734095287304958720391231231231231236545767970697804752");
        testDHParameterSpecBigIntegerBigInteger001();
        assertEquals(msgNotSame,p,DHps.getP());
    }

    /*
     * Test method for 'javax.crypto.spec.DHParameterSpec.getG()'
     */
    public final void testGetG001() {
        testDHParameterSpecBigIntegerBigInteger001();
        assertEquals(msgNotSame,g,DHps.getG());
    }
    
    public final void testGetG002() {
        g = new BigInteger("9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
        testDHParameterSpecBigIntegerBigInteger001();
        assertEquals(msgNotSame,g,DHps.getG());
    }
    
    public final void testGetG003() {
        g = new BigInteger("-9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999");
        testDHParameterSpecBigIntegerBigInteger001();
        assertEquals(msgNotSame,g,DHps.getG());
    }

    /*
     * Test method for 'javax.crypto.spec.DHParameterSpec.getL()'
     */
    public final void testGetL001() {
        testDHParameterSpecBigIntegerBigIntegerInt001();
        assertEquals(msgNotSame,l,DHps.getL());
    }
    
    public final void testGetL002() {
        l = Integer.MAX_VALUE;
        testDHParameterSpecBigIntegerBigIntegerInt001();
        assertEquals(msgNotSame,l,DHps.getL());
    }

    public final void testGetL003() {
        l = Integer.MIN_VALUE;
        testDHParameterSpecBigIntegerBigIntegerInt001();
        assertEquals(msgNotSame,l,DHps.getL());
    }
    
    public final void testGetL004() {
        testDHParameterSpecBigIntegerBigInteger001();
        assertTrue("L parameter not 0",DHps.getL() == 0);
    }
}
