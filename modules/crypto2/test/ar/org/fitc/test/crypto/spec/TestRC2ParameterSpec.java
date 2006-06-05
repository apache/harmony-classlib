package ar.org.fitc.test.crypto.spec;

import javax.crypto.spec.RC2ParameterSpec;

import junit.framework.TestCase;

public class TestRC2ParameterSpec extends TestCase {
    
    private int effectiveKeyBits = 7;
    private int ivLen = 12;
    private byte[] iv = new byte[ivLen];
    private byte[] iv50 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49};
    
    private RC2ParameterSpec rc2ps = null;
    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestRC2ParameterSpec.class);
    }
    
    public TestRC2ParameterSpec(String name) {
        super(name);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    protected void tearDown() throws Exception {
        super.tearDown();
        rc2ps = null;
    }
    
    
    /*
     * Test method for 'javax.crypto.spec.RC2ParameterSpec.RC2ParameterSpec(int)'
     */
    public final void testRC2ParameterSpecInt001() {
        try {
            assertTrue("must instance, but it doesn't", new RC2ParameterSpec(0) instanceof RC2ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
        
    }
    public final void testRC2ParameterSpecInt002() {
        try {
            assertTrue("must instance, but it doesn't", new RC2ParameterSpec(7) instanceof RC2ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
        
    }
    public final void testRC2ParameterSpecInt003() {
        try {
            assertTrue("must instance, but it doesn't", new RC2ParameterSpec(-11) instanceof RC2ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    public final void testRC2ParameterSpecInt004() {
        try {
            assertTrue("must instance, but it doesn't", new RC2ParameterSpec(2347) instanceof RC2ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
        
    }
    
    /*
     * Test method for 'javax.crypto.spec.RC2ParameterSpec.RC2ParameterSpec(int, byte[])'
     */
    
    public final void testRC2ParameterSpecIntByteArray001() {
        try {
            rc2ps = new RC2ParameterSpec(0, new byte[0]);
            fail("IV array is not what need");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testRC2ParameterSpecIntByteArray002() {
        try {
            rc2ps = new RC2ParameterSpec(0, new byte[12]);
            assertTrue("must instance, but it doesn't", rc2ps instanceof RC2ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testRC2ParameterSpecIntByteArray003() {
        try {
            rc2ps = new RC2ParameterSpec(0, new byte[7]);
            fail("IV array is not what need");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testRC2ParameterSpecIntByteArray004() {
        try {
            rc2ps = new RC2ParameterSpec(0, null);
            fail("IV array is not what need");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testRC2ParameterSpecIntByteArray005() {
        try {
            rc2ps = new RC2ParameterSpec(7, new byte[0]);
            fail("IV array is not what need");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testRC2ParameterSpecIntByteArray006() {
        try {
            rc2ps = new RC2ParameterSpec(7, new byte[12]);
            assertTrue("must instance, but it doesn't", rc2ps instanceof RC2ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testRC2ParameterSpecIntByteArray007() {
        try {
            rc2ps = new RC2ParameterSpec(7, new byte[7]);
            fail("IV array is not what need");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testRC2ParameterSpecIntByteArray008() {
        try {
            rc2ps = new RC2ParameterSpec(7, null);
            fail("IV array is not what need");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testRC2ParameterSpecIntByteArray009() {
        try {
            rc2ps = new RC2ParameterSpec(-11, new byte[0]);
            fail("IV array is not what need");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testRC2ParameterSpecIntByteArray010() {
        try {
            rc2ps = new RC2ParameterSpec(-11, new byte[12]);
            assertTrue("must instance, but it doesn't", rc2ps instanceof RC2ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testRC2ParameterSpecIntByteArray011() {
        try {
            rc2ps = new RC2ParameterSpec(-11, new byte[7]);
            fail("IV array is not what need");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testRC2ParameterSpecIntByteArray012() {
        try {
            rc2ps = new RC2ParameterSpec(-11, null);
            fail("IV array is not what need");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    /*
     * Test method for 'javax.crypto.spec.RC2ParameterSpec.RC2ParameterSpec(int, byte[], int)'
     */
    
    public final void testRC2ParameterSpecIntByteArrayInt001() {
        try {
            rc2ps = new RC2ParameterSpec(0,new byte[] {1,2,3,4,5,6,7,8,9,0,23,23},4);
            assertTrue("Can't instance RC2ParameterSpec", rc2ps instanceof RC2ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC2ParameterSpecIntByteArrayInt002() {
        try {
            rc2ps = new RC2ParameterSpec(0,new byte[] {1,2,3,4,5,6,7,8,9,0,23,23},0);
            assertTrue("Can't instance RC2ParameterSpec", rc2ps instanceof RC2ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC2ParameterSpecIntByteArrayInt003() {
        try {
            rc2ps = new RC2ParameterSpec(0,new byte[] {1,2,3,4,5,6,7,8,9,0,23,23},-1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
    
        }
    }

    public final void testRC2ParameterSpecIntByteArrayInt004() {
        try {
            rc2ps = new RC2ParameterSpec(0,new byte[0],4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC2ParameterSpecIntByteArrayInt005() {
        try {
            rc2ps = new RC2ParameterSpec(0,new byte[0],0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC2ParameterSpecIntByteArrayInt006() {
        try {
            rc2ps = new RC2ParameterSpec(0,new byte[0],-1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
    
        }
    }

    public final void testRC2ParameterSpecIntByteArrayInt007() {
        try {
            rc2ps = new RC2ParameterSpec(0,null,4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC2ParameterSpecIntByteArrayInt008() {
        try {
            rc2ps = new RC2ParameterSpec(0,null,0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC2ParameterSpecIntByteArrayInt009() {
        try {
            rc2ps = new RC2ParameterSpec(0,null,-1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
    
        }
    }

    public final void testRC2ParameterSpecIntByteArrayInt010() {
        try {
            rc2ps = new RC2ParameterSpec(7,new byte[] {1,2,3,4,5,6,7,8,9,0,23,23},4);
            assertTrue("Can't instance RC2ParameterSpec", rc2ps instanceof RC2ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC2ParameterSpecIntByteArrayInt011() {
        try {
            rc2ps = new RC2ParameterSpec(7,new byte[] {1,2,3,4,5,6,7,8,9,0,23,23},0);
            assertTrue("Can't instance RC2ParameterSpec", rc2ps instanceof RC2ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC2ParameterSpecIntByteArrayInt012() {
        try {
            rc2ps = new RC2ParameterSpec(7,new byte[] {1,2,3,4,5,6,7,8,9,0,23,23},-1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
    
        }
    }

    public final void testRC2ParameterSpecIntByteArrayInt013() {
        try {
            rc2ps = new RC2ParameterSpec(7,new byte[0],4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC2ParameterSpecIntByteArrayInt014() {
        try {
            rc2ps = new RC2ParameterSpec(7,new byte[0],0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC2ParameterSpecIntByteArrayInt015() {
        try {
            rc2ps = new RC2ParameterSpec(7,new byte[0],-1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
    
        }
    }

    public final void testRC2ParameterSpecIntByteArrayInt016() {
        try {
            rc2ps = new RC2ParameterSpec(7,null,4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC2ParameterSpecIntByteArrayInt017() {
        try {
            rc2ps = new RC2ParameterSpec(7,null,0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC2ParameterSpecIntByteArrayInt018() {
        try {
            rc2ps = new RC2ParameterSpec(7,null,-1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
    
        }
    }

    public final void testRC2ParameterSpecIntByteArrayInt019() {
        try {
            rc2ps = new RC2ParameterSpec(-11,new byte[] {1,2,3,4,5,6,7,8,9,0,23,23},4);
            assertTrue("Can't instance RC2ParameterSpec", rc2ps instanceof RC2ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC2ParameterSpecIntByteArrayInt020() {
        try {
            rc2ps = new RC2ParameterSpec(-11,new byte[] {1,2,3,4,5,6,7,8,9,0,23,23},0);
            assertTrue("Can't instance RC2ParameterSpec", rc2ps instanceof RC2ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC2ParameterSpecIntByteArrayInt021() {
        try {
            rc2ps = new RC2ParameterSpec(-11,new byte[] {1,2,3,4,5,6,7,8,9,0,23,23},-1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
    
        }
    }

    public final void testRC2ParameterSpecIntByteArrayInt022() {
        try {
            rc2ps = new RC2ParameterSpec(-11,new byte[0],4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC2ParameterSpecIntByteArrayInt023() {
        try {
            rc2ps = new RC2ParameterSpec(-11,new byte[0],0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC2ParameterSpecIntByteArrayInt024() {
        try {
            rc2ps = new RC2ParameterSpec(-11,new byte[0],-1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
    
        }
    }

    public final void testRC2ParameterSpecIntByteArrayInt025() {
        try {
            rc2ps = new RC2ParameterSpec(-11,null,4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC2ParameterSpecIntByteArrayInt026() {
        try {
            rc2ps = new RC2ParameterSpec(-11,null,0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC2ParameterSpecIntByteArrayInt027() {
        try {
            rc2ps = new RC2ParameterSpec(-11,null,-1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
    
        }
    }


    
    /*
     * Test method for 'javax.crypto.spec.RC2ParameterSpec.getEffectiveKeyBits()'
     */
    public final void testGetEffectiveKeyBits001() {
        try {
            int i=3;
            assertEquals("EffectiveKeyBits must be equals", i, new RC2ParameterSpec(i).getEffectiveKeyBits());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    public final void testGetEffectiveKeyBits002() {
        try {
            int i=-7;
            assertEquals("EffectiveKeyBits must be equals", i, new RC2ParameterSpec(i).getEffectiveKeyBits());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    public final void testGetEffectiveKeyBits003() {
        try {
            int i=-8;
            assertEquals("EffectiveKeyBits must be equals", i, new RC2ParameterSpec(i, iv).getEffectiveKeyBits());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    public final void testGetEffectiveKeyBits004() {
        try {
            int i=11;
            assertEquals("EffectiveKeyBits must be equals", i, new RC2ParameterSpec(i, iv, 4).getEffectiveKeyBits());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    /*
     * Test method for 'javax.crypto.spec.RC2ParameterSpec.getIV()'
     */
    public final void testGetIV001() {
        try {
            assertEquals("IV must be equals", null, new RC2ParameterSpec(7).getIV());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    public final void testGetIV002() {
        try {
            byte[] giv = new RC2ParameterSpec(7, iv50).getIV();
            assertEquals("IV must have length 8", 8, giv.length);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    public final void testGetIV003() {
        try {
            byte[] giv = new RC2ParameterSpec(7, iv).getIV();
            for (int i=0; i < 8; i++) {
                assertEquals("IV must be equals", iv[i], giv[i]);
            }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    public final void testGetIV004() {
        try {
            byte[] giv = new RC2ParameterSpec(7, iv50, 7).getIV();
            for (int i=0; i < 8; i++) {
                assertEquals("IV must be equals", iv50[i+7], giv[i]);
            }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    /*
     * Test method for 'javax.crypto.spec.RC2ParameterSpec.hashCode()'
     */
    
    public final void testHashCode001() {
        try {
            rc2ps = new RC2ParameterSpec(effectiveKeyBits, iv, 4);
            
            RC2ParameterSpec rc2ps2 = new RC2ParameterSpec(effectiveKeyBits, iv);
            assertEquals("must be equals hashCode", rc2ps.hashCode(), rc2ps2.hashCode());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testHashCode002() {
        try {
            rc2ps = new RC2ParameterSpec(effectiveKeyBits, iv, 0);
            
            RC2ParameterSpec rc2ps2 = new RC2ParameterSpec(effectiveKeyBits, iv);
            assertEquals("must be equals hashCode", rc2ps.hashCode(), rc2ps2.hashCode());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testHashCode003() {
        try {
            rc2ps = new RC2ParameterSpec(effectiveKeyBits, iv50, 4);
            
            RC2ParameterSpec rc2ps2 = new RC2ParameterSpec(effectiveKeyBits, iv);
            assertNotSame("must not be equals hashCode", rc2ps.hashCode(), rc2ps2.hashCode());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testHashCode004() {
        try {
            rc2ps = new RC2ParameterSpec(effectiveKeyBits, iv50, 0);
            
            RC2ParameterSpec rc2ps2 = new RC2ParameterSpec(effectiveKeyBits, iv);
            assertNotSame("must not be equals hashCode", rc2ps.hashCode(), rc2ps2.hashCode());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testHashCode005() {
        try {
            rc2ps = new RC2ParameterSpec(effectiveKeyBits+1, iv, 4);
            
            RC2ParameterSpec rc2ps2 = new RC2ParameterSpec(effectiveKeyBits, iv);
            assertNotSame("must not be equals hashCode", rc2ps.hashCode(), rc2ps2.hashCode());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testHashCode006() {
        try {
            rc2ps = new RC2ParameterSpec(effectiveKeyBits+1, iv, 0);
            
            RC2ParameterSpec rc2ps2 = new RC2ParameterSpec(effectiveKeyBits, iv);
            assertNotSame("must not be equals hashCode", rc2ps.hashCode(), rc2ps2.hashCode());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testHashCode007() {
        try {
            rc2ps = new RC2ParameterSpec(effectiveKeyBits+1, iv50, 4);
            
            RC2ParameterSpec rc2ps2 = new RC2ParameterSpec(effectiveKeyBits, iv);
            assertNotSame("must not be equals hashCode", rc2ps.hashCode(), rc2ps2.hashCode());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testHashCode008() {
        try {
            rc2ps = new RC2ParameterSpec(effectiveKeyBits+1, iv50, 0);
            
            RC2ParameterSpec rc2ps2 = new RC2ParameterSpec(effectiveKeyBits, iv);
            assertNotSame("must not be equals hashCode", rc2ps.hashCode(), rc2ps2.hashCode());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    /*
     * Test method for 'javax.crypto.spec.RC2ParameterSpec.equals(Object)'
     */
    
    public final void testEquals001() {
        try {
            rc2ps = new RC2ParameterSpec(effectiveKeyBits, iv, 4);
            
            RC2ParameterSpec rc2ps2 = new RC2ParameterSpec(effectiveKeyBits, iv);
            assertTrue("must be equals", rc2ps.equals(rc2ps2));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testEquals002() {
        try {
            rc2ps = new RC2ParameterSpec(effectiveKeyBits, iv, 0);
            
            RC2ParameterSpec rc2ps2 = new RC2ParameterSpec(effectiveKeyBits, iv);
            assertTrue("must be equals", rc2ps.equals(rc2ps2));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testEquals003() {
        try {
            rc2ps = new RC2ParameterSpec(effectiveKeyBits, iv50, 4);
            
            RC2ParameterSpec rc2ps2 = new RC2ParameterSpec(effectiveKeyBits, iv);
            assertFalse("must not be equals", rc2ps.equals(rc2ps2));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testEquals004() {
        try {
            rc2ps = new RC2ParameterSpec(effectiveKeyBits, iv50, 0);
            
            RC2ParameterSpec rc2ps2 = new RC2ParameterSpec(effectiveKeyBits, iv);
            assertFalse("must not be equals", rc2ps.equals(rc2ps2));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testEquals005() {
        try {
            rc2ps = new RC2ParameterSpec(effectiveKeyBits+1, iv, 4);
            
            RC2ParameterSpec rc2ps2 = new RC2ParameterSpec(effectiveKeyBits, iv);
            assertFalse("must not be equals", rc2ps.equals(rc2ps2));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testEquals006() {
        try {
            rc2ps = new RC2ParameterSpec(effectiveKeyBits+1, iv, 0);
            
            RC2ParameterSpec rc2ps2 = new RC2ParameterSpec(effectiveKeyBits, iv);
            assertFalse("must not be equals", rc2ps.equals(rc2ps2));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testEquals007() {
        try {
            rc2ps = new RC2ParameterSpec(effectiveKeyBits+1, iv50, 4);
            
            RC2ParameterSpec rc2ps2 = new RC2ParameterSpec(effectiveKeyBits, iv);
            assertFalse("must not be equals", rc2ps.equals(rc2ps2));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testEquals008() {
        try {
            rc2ps = new RC2ParameterSpec(effectiveKeyBits+1, iv50, 0);
            
            RC2ParameterSpec rc2ps2 = new RC2ParameterSpec(effectiveKeyBits, iv);
            assertFalse("must not be equals", rc2ps.equals(rc2ps2));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    public final void testEquals009() {
        try {
            rc2ps = new RC2ParameterSpec(effectiveKeyBits+1, iv50, 4);
            
            assertFalse("must not be equals", rc2ps.equals(null));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
}
