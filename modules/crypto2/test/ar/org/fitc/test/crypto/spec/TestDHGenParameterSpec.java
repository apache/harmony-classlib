package ar.org.fitc.test.crypto.spec;

import javax.crypto.spec.DHGenParameterSpec;

import ar.org.fitc.test.util.Messages;

import junit.framework.TestCase;

public class TestDHGenParameterSpec extends TestCase implements Messages {

    private DHGenParameterSpec dhgps = null;
    private int primeSize = 0;
    private int exponentSize = 0;
    
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestDHGenParameterSpec.class);
    }

    public TestDHGenParameterSpec(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'javax.crypto.spec.DHGenParameterSpec.DHGenParameterSpec(int, int)'
     */
    public final void testDHGenParameterSpec001() {
        dhgps = new DHGenParameterSpec(primeSize,exponentSize);
        assertTrue(msgNotInstance+"DHGENParameterSpec",dhgps instanceof DHGenParameterSpec);
    }

    /*
     * Test method for 'javax.crypto.spec.DHGenParameterSpec.getPrimeSize()'
     */
    public final void testGetPrimeSize001() {
        primeSize = 1024;
        testDHGenParameterSpec001();
        assertEquals("Not the same number",primeSize,dhgps.getPrimeSize());
    }
    
    public final void testGetPrimeSize002() {
        primeSize = Integer.MAX_VALUE;
        testDHGenParameterSpec001();
        assertEquals("Not the same number",primeSize,dhgps.getPrimeSize());
    }
    
    public final void testGetPrimeSize003() {
        primeSize = Integer.MIN_VALUE;
        testDHGenParameterSpec001();
        assertEquals("Not the same number",primeSize,dhgps.getPrimeSize());
    }
    
    /*
     * Test method for 'javax.crypto.spec.DHGenParameterSpec.getExponentSize()'
     */
    public final void testGetExponentSize001() {
        exponentSize = 1024;
        testDHGenParameterSpec001();
        assertEquals("Not the same number",exponentSize,dhgps.getExponentSize());
    }
    
    public final void testGetExponentSize002() {
        exponentSize = Integer.MIN_VALUE;
        testDHGenParameterSpec001();
        assertEquals("Not the same number",exponentSize,dhgps.getExponentSize());
    }
    
    public final void testGetExponentSize003() {
        exponentSize = Integer.MAX_VALUE;
        testDHGenParameterSpec001();
        assertEquals("Not the same number",exponentSize,dhgps.getExponentSize());
    }

}
