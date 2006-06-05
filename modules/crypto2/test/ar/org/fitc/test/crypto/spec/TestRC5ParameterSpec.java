package ar.org.fitc.test.crypto.spec;

import javax.crypto.spec.RC5ParameterSpec;

import junit.framework.TestCase;

public class TestRC5ParameterSpec extends TestCase {

    private RC5ParameterSpec rc5ps = null;

    private int version = 12, rounds  = 15, wordSize= 23;
    private int ivLen = (wordSize / 4) +4;
    private byte[] iv = new byte[ivLen];
    private byte[] iv50 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49};

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestRC5ParameterSpec.class);
    }

    public TestRC5ParameterSpec(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void RC5ParameterSpecIntIntInt() {
        rc5ps = new RC5ParameterSpec(version,rounds,wordSize);
        assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
    }

    public void RC5ParameterSpecIntIntIntByteArray() {
        rc5ps = new RC5ParameterSpec(version,rounds,wordSize, iv);
        assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
    }

    public void RC5ParameterSpecIntIntIntByteArrayInt() {
        rc5ps = new RC5ParameterSpec(version,rounds,wordSize, iv, 4);
        assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
    }


    /*
     * Test method for 'javax.crypto.spec.RC5ParameterSpec.RC5ParameterSpec(int, int, int)'
     */
    public final void testRC5ParameterSpecIntIntInt001() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,0,0);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt002() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,0,245);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt003() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,0,-1);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt004() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,0,wordSize);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }


    public final void testRC5ParameterSpecIntIntInt006() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,213412,245);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt007() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,213412,-1);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt008() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,213412,wordSize);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt009() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,rounds,0);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt010() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,rounds,245);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt011() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,rounds,-1);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt012() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,rounds,wordSize);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt013() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-23412,0);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt014() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-23412,245);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt015() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-23412,-1);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt016() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-23412,wordSize);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt017() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-1,0);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt018() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-1,245);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt019() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-1,-1);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt020() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-1,wordSize);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt021() {
        try {
            rc5ps = new RC5ParameterSpec(-23,0,0);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt022() {
        try {
            rc5ps = new RC5ParameterSpec(-23,0,245);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt023() {
        try {
            rc5ps = new RC5ParameterSpec(-23,0,-1);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt024() {
        try {
            rc5ps = new RC5ParameterSpec(-23,0,wordSize);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt025() {
        try {
            rc5ps = new RC5ParameterSpec(-23,213412,0);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt026() {
        try {
            rc5ps = new RC5ParameterSpec(-23,213412,245);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt027() {
        try {
            rc5ps = new RC5ParameterSpec(-23,213412,-1);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt028() {
        try {
            rc5ps = new RC5ParameterSpec(-23,213412,wordSize);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt029() {
        try {
            rc5ps = new RC5ParameterSpec(-23,rounds,0);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt030() {
        try {
            rc5ps = new RC5ParameterSpec(-23,rounds,245);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt031() {
        try {
            rc5ps = new RC5ParameterSpec(-23,rounds,-1);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt032() {
        try {
            rc5ps = new RC5ParameterSpec(-23,rounds,wordSize);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt033() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-23412,0);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt034() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-23412,245);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt035() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-23412,-1);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt036() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-23412,wordSize);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt037() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-1,0);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt038() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-1,245);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt039() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-1,-1);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt040() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-1,wordSize);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt041() {
        try {
            rc5ps = new RC5ParameterSpec(0,0,0);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt042() {
        try {
            rc5ps = new RC5ParameterSpec(0,0,245);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt043() {
        try {
            rc5ps = new RC5ParameterSpec(0,0,-1);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt044() {
        try {
            rc5ps = new RC5ParameterSpec(0,0,wordSize);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt045() {
        try {
            rc5ps = new RC5ParameterSpec(0,213412,0);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt046() {
        try {
            rc5ps = new RC5ParameterSpec(0,213412,245);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt047() {
        try {
            rc5ps = new RC5ParameterSpec(0,213412,-1);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt048() {
        try {
            rc5ps = new RC5ParameterSpec(0,213412,wordSize);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt049() {
        try {
            rc5ps = new RC5ParameterSpec(0,rounds,0);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt050() {
        try {
            rc5ps = new RC5ParameterSpec(0,rounds,245);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt051() {
        try {
            rc5ps = new RC5ParameterSpec(0,rounds,-1);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt052() {
        try {
            rc5ps = new RC5ParameterSpec(0,rounds,wordSize);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt053() {
        try {
            rc5ps = new RC5ParameterSpec(0,-23412,0);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt054() {
        try {
            rc5ps = new RC5ParameterSpec(0,-23412,245);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt055() {
        try {
            rc5ps = new RC5ParameterSpec(0,-23412,-1);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt056() {
        try {
            rc5ps = new RC5ParameterSpec(0,-23412,wordSize);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt057() {
        try {
            rc5ps = new RC5ParameterSpec(0,-1,0);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt058() {
        try {
            rc5ps = new RC5ParameterSpec(0,-1,245);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt059() {
        try {
            rc5ps = new RC5ParameterSpec(0,-1,-1);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt060() {
        try {
            rc5ps = new RC5ParameterSpec(0,-1,wordSize);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt061() {
        try {
            rc5ps = new RC5ParameterSpec(version,0,0);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt062() {
        try {
            rc5ps = new RC5ParameterSpec(version,0,245);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt063() {
        try {
            rc5ps = new RC5ParameterSpec(version,0,-1);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt064() {
        try {
            rc5ps = new RC5ParameterSpec(version,0,wordSize);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt065() {
        try {
            rc5ps = new RC5ParameterSpec(version,213412,0);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt066() {
        try {
            rc5ps = new RC5ParameterSpec(version,213412,245);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt067() {
        try {
            rc5ps = new RC5ParameterSpec(version,213412,-1);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt068() {
        try {
            rc5ps = new RC5ParameterSpec(version,213412,wordSize);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt069() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,0);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt070() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,245);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt071() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,-1);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt072() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt073() {
        try {
            rc5ps = new RC5ParameterSpec(version,-23412,0);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt074() {
        try {
            rc5ps = new RC5ParameterSpec(version,-23412,245);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt075() {
        try {
            rc5ps = new RC5ParameterSpec(version,-23412,-1);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt076() {
        try {
            rc5ps = new RC5ParameterSpec(version,-23412,wordSize);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt077() {
        try {
            rc5ps = new RC5ParameterSpec(version,-1,0);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt078() {
        try {
            rc5ps = new RC5ParameterSpec(version,-1,245);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt079() {
        try {
            rc5ps = new RC5ParameterSpec(version,-1,-1);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt080() {
        try {
            rc5ps = new RC5ParameterSpec(version,-1,wordSize);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt081() {
        try {
            rc5ps = new RC5ParameterSpec(-1,0,0);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt082() {
        try {
            rc5ps = new RC5ParameterSpec(-1,0,245);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt083() {
        try {
            rc5ps = new RC5ParameterSpec(-1,0,-1);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt084() {
        try {
            rc5ps = new RC5ParameterSpec(-1,0,wordSize);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt085() {
        try {
            rc5ps = new RC5ParameterSpec(-1,213412,0);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt086() {
        try {
            rc5ps = new RC5ParameterSpec(-1,213412,245);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt087() {
        try {
            rc5ps = new RC5ParameterSpec(-1,213412,-1);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt088() {
        try {
            rc5ps = new RC5ParameterSpec(-1,213412,wordSize);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt089() {
        try {
            rc5ps = new RC5ParameterSpec(-1,rounds,0);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt090() {
        try {
            rc5ps = new RC5ParameterSpec(-1,rounds,245);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt091() {
        try {
            rc5ps = new RC5ParameterSpec(-1,rounds,-1);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt092() {
        try {
            rc5ps = new RC5ParameterSpec(-1,rounds,wordSize);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt093() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-23412,0);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt094() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-23412,245);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt095() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-23412,-1);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt096() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-23412,wordSize);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt097() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-1,0);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt098() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-1,245);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt099() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-1,-1);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntInt100() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-1,wordSize);
            assertTrue("Can't instance RC5ParameterSpec", rc5ps instanceof RC5ParameterSpec);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }



    /*
     * Test method for 'javax.crypto.spec.RC5ParameterSpec.RC5ParameterSpec(int, int, int, byte[])'
     */

    public final void testRC5ParameterSpecIntIntIntByteArray001() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,0,245, new byte[2*(245 / 8)-1]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray002() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,0,245, iv);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray003() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,0,245, new byte[0]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray004() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,0,245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray005() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,213412,245, new byte[2*(245 / 8)-1]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray006() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,213412,245, iv);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray007() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,213412,245, new byte[0]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray008() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,213412,245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray009() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,rounds,245, new byte[2*(245 / 8)-1]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray010() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,rounds,245, iv);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray011() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,rounds,245, new byte[0]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray012() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,rounds,245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray013() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-23412,245, new byte[2*(245 / 8)-1]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray014() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-23412,245, iv);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray015() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-23412,245, new byte[0]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray016() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-23412,245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray017() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-1,245, new byte[2*(245 / 8)-1]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray018() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-1,245, iv);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray019() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-1,245, new byte[0]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray020() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-1,245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray021() {
        try {
            rc5ps = new RC5ParameterSpec(-23,0,245, new byte[2*(245 / 8)-1]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray022() {
        try {
            rc5ps = new RC5ParameterSpec(-23,0,245, iv);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray023() {
        try {
            rc5ps = new RC5ParameterSpec(-23,0,245, new byte[0]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray024() {
        try {
            rc5ps = new RC5ParameterSpec(-23,0,245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray025() {
        try {
            rc5ps = new RC5ParameterSpec(-23,213412,245, new byte[2*(245 / 8)-1]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray026() {
        try {
            rc5ps = new RC5ParameterSpec(-23,213412,245, iv);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray027() {
        try {
            rc5ps = new RC5ParameterSpec(-23,213412,245, new byte[0]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray028() {
        try {
            rc5ps = new RC5ParameterSpec(-23,213412,245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray029() {
        try {
            rc5ps = new RC5ParameterSpec(-23,rounds,245, new byte[2*(245 / 8)-1]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray030() {
        try {
            rc5ps = new RC5ParameterSpec(-23,rounds,245, iv);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray031() {
        try {
            rc5ps = new RC5ParameterSpec(-23,rounds,245, new byte[0]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray032() {
        try {
            rc5ps = new RC5ParameterSpec(-23,rounds,245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray033() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-23412,245, new byte[2*(245 / 8)-1]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray034() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-23412,245, iv);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray035() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-23412,245, new byte[0]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray036() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-23412,245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray037() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-1,245, new byte[2*(245 / 8)-1]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray038() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-1,245, iv);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray039() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-1,245, new byte[0]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray040() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-1,245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray041() {
        try {
            rc5ps = new RC5ParameterSpec(0,0,245, new byte[2*(245 / 8)-1]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray042() {
        try {
            rc5ps = new RC5ParameterSpec(0,0,245, iv);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray043() {
        try {
            rc5ps = new RC5ParameterSpec(0,0,245, new byte[0]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray044() {
        try {
            rc5ps = new RC5ParameterSpec(0,0,245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray045() {
        try {
            rc5ps = new RC5ParameterSpec(0,213412,245, new byte[2*(245 / 8)-1]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray046() {
        try {
            rc5ps = new RC5ParameterSpec(0,213412,245, iv);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray047() {
        try {
            rc5ps = new RC5ParameterSpec(0,213412,245, new byte[0]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray048() {
        try {
            rc5ps = new RC5ParameterSpec(0,213412,245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray049() {
        try {
            rc5ps = new RC5ParameterSpec(0,rounds,245, new byte[2*(245 / 8)-1]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray050() {
        try {
            rc5ps = new RC5ParameterSpec(0,rounds,245, iv);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray051() {
        try {
            rc5ps = new RC5ParameterSpec(0,rounds,245, new byte[0]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray052() {
        try {
            rc5ps = new RC5ParameterSpec(0,rounds,245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray053() {
        try {
            rc5ps = new RC5ParameterSpec(0,-23412,245, new byte[2*(245 / 8)-1]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray054() {
        try {
            rc5ps = new RC5ParameterSpec(0,-23412,245, iv);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray055() {
        try {
            rc5ps = new RC5ParameterSpec(0,-23412,245, new byte[0]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray056() {
        try {
            rc5ps = new RC5ParameterSpec(0,-23412,245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray057() {
        try {
            rc5ps = new RC5ParameterSpec(0,-1,245, new byte[2*(245 / 8)-1]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray058() {
        try {
            rc5ps = new RC5ParameterSpec(0,-1,245, iv);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray059() {
        try {
            rc5ps = new RC5ParameterSpec(0,-1,245, new byte[0]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray060() {
        try {
            rc5ps = new RC5ParameterSpec(0,-1,245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray061() {
        try {
            rc5ps = new RC5ParameterSpec(version,0,245, new byte[2*(245 / 8)-1]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray062() {
        try {
            rc5ps = new RC5ParameterSpec(version,0,245, iv);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray063() {
        try {
            rc5ps = new RC5ParameterSpec(version,0,245, new byte[0]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray064() {
        try {
            rc5ps = new RC5ParameterSpec(version,0,245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray065() {
        try {
            rc5ps = new RC5ParameterSpec(version,213412,245, new byte[2*(245 / 8)-1]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray066() {
        try {
            rc5ps = new RC5ParameterSpec(version,213412,245, iv);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray067() {
        try {
            rc5ps = new RC5ParameterSpec(version,213412,245, new byte[0]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray068() {
        try {
            rc5ps = new RC5ParameterSpec(version,213412,245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray069() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,245, new byte[2*(245 / 8)-1]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray070() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,245, iv);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray071() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,245, new byte[0]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray072() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray073() {
        try {
            rc5ps = new RC5ParameterSpec(version,-23412,245, new byte[2*(245 / 8)-1]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray074() {
        try {
            rc5ps = new RC5ParameterSpec(version,-23412,245, iv);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray075() {
        try {
            rc5ps = new RC5ParameterSpec(version,-23412,245, new byte[0]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray076() {
        try {
            rc5ps = new RC5ParameterSpec(version,-23412,245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray077() {
        try {
            rc5ps = new RC5ParameterSpec(version,-1,245, new byte[2*(245 / 8)-1]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray078() {
        try {
            rc5ps = new RC5ParameterSpec(version,-1,245, iv);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray079() {
        try {
            rc5ps = new RC5ParameterSpec(version,-1,245, new byte[0]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray080() {
        try {
            rc5ps = new RC5ParameterSpec(version,-1,245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray081() {
        try {
            rc5ps = new RC5ParameterSpec(-1,0,245, new byte[2*(245 / 8)-1]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray082() {
        try {
            rc5ps = new RC5ParameterSpec(-1,0,245, iv);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray083() {
        try {
            rc5ps = new RC5ParameterSpec(-1,0,245, new byte[0]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray084() {
        try {
            rc5ps = new RC5ParameterSpec(-1,0,245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray085() {
        try {
            rc5ps = new RC5ParameterSpec(-1,213412,245, new byte[2*(245 / 8)-1]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray086() {
        try {
            rc5ps = new RC5ParameterSpec(-1,213412,245, iv);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray087() {
        try {
            rc5ps = new RC5ParameterSpec(-1,213412,245, new byte[0]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray088() {
        try {
            rc5ps = new RC5ParameterSpec(-1,213412,245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray089() {
        try {
            rc5ps = new RC5ParameterSpec(-1,rounds,245, new byte[2*(245 / 8)-1]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray090() {
        try {
            rc5ps = new RC5ParameterSpec(-1,rounds,245, iv);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray091() {
        try {
            rc5ps = new RC5ParameterSpec(-1,rounds,245, new byte[0]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray092() {
        try {
            rc5ps = new RC5ParameterSpec(-1,rounds,245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray093() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-23412,245, new byte[2*(245 / 8)-1]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray094() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-23412,245, iv);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray095() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-23412,245, new byte[0]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray096() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-23412,245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray097() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-1,245, new byte[2*(245 / 8)-1]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray098() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-1,245, iv);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray099() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-1,245, new byte[0]);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArray100() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-1,245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }





    /*
     * Test method for 'javax.crypto.spec.RC5ParameterSpec.RC5ParameterSpec(int, int, int, byte[], int)'
     */
    public final void testRC5ParameterSpecIntIntIntByteArrayInt001() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,0,245, new byte[2*(245 / 8)-1], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt002() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,0,245, new byte[2*(245 / 8)-1], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt003() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,0,245, new byte[2*(245 / 8)-1], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt004() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,0,245, iv, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt005() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,0,245, iv, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt006() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,0,245, iv, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt007() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,0,245, new byte[0], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt008() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,0,245, new byte[0], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt009() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,0,245, new byte[0], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt010() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,0,245, null, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt011() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,0,245, null, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt012() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,0,245, null, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt013() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,213412,245, new byte[2*(245 / 8)-1], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt014() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,213412,245, new byte[2*(245 / 8)-1], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt015() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,213412,245, new byte[2*(245 / 8)-1], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt016() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,213412,245, iv, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt017() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,213412,245, iv, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt018() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,213412,245, iv, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt019() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,213412,245, new byte[0], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt020() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,213412,245, new byte[0], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt021() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,213412,245, new byte[0], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt022() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,213412,245, null, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt023() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,213412,245, null, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt024() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,213412,245, null, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt025() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,rounds,245, new byte[2*(245 / 8)-1], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt026() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,rounds,245, new byte[2*(245 / 8)-1], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt027() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,rounds,245, new byte[2*(245 / 8)-1], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt028() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,rounds,245, iv, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt029() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,rounds,245, iv, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt030() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,rounds,245, iv, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt031() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,rounds,245, new byte[0], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt032() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,rounds,245, new byte[0], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt033() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,rounds,245, new byte[0], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt034() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,rounds,245, null, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt035() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,rounds,245, null, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt036() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,rounds,245, null, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt037() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-23412,245, new byte[2*(245 / 8)-1], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt038() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-23412,245, new byte[2*(245 / 8)-1], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt039() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-23412,245, new byte[2*(245 / 8)-1], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt040() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-23412,245, iv, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt041() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-23412,245, iv, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt042() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-23412,245, iv, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt043() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-23412,245, new byte[0], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt044() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-23412,245, new byte[0], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt045() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-23412,245, new byte[0], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt046() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-23412,245, null, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt047() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-23412,245, null, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt048() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-23412,245, null, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt049() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-1,245, new byte[2*(245 / 8)-1], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt050() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-1,245, new byte[2*(245 / 8)-1], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt051() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-1,245, new byte[2*(245 / 8)-1], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt052() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-1,245, iv, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt053() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-1,245, iv, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt054() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-1,245, iv, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt055() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-1,245, new byte[0], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt056() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-1,245, new byte[0], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt057() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-1,245, new byte[0], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt058() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-1,245, null, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt059() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-1,245, null, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt060() {
        try {
            rc5ps = new RC5ParameterSpec(120231231,-1,245, null, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt061() {
        try {
            rc5ps = new RC5ParameterSpec(-23,0,245, new byte[2*(245 / 8)-1], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt062() {
        try {
            rc5ps = new RC5ParameterSpec(-23,0,245, new byte[2*(245 / 8)-1], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt063() {
        try {
            rc5ps = new RC5ParameterSpec(-23,0,245, new byte[2*(245 / 8)-1], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt064() {
        try {
            rc5ps = new RC5ParameterSpec(-23,0,245, iv, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt065() {
        try {
            rc5ps = new RC5ParameterSpec(-23,0,245, iv, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt066() {
        try {
            rc5ps = new RC5ParameterSpec(-23,0,245, iv, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt067() {
        try {
            rc5ps = new RC5ParameterSpec(-23,0,245, new byte[0], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt068() {
        try {
            rc5ps = new RC5ParameterSpec(-23,0,245, new byte[0], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt069() {
        try {
            rc5ps = new RC5ParameterSpec(-23,0,245, new byte[0], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt070() {
        try {
            rc5ps = new RC5ParameterSpec(-23,0,245, null, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt071() {
        try {
            rc5ps = new RC5ParameterSpec(-23,0,245, null, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt072() {
        try {
            rc5ps = new RC5ParameterSpec(-23,0,245, null, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt073() {
        try {
            rc5ps = new RC5ParameterSpec(-23,213412,245, new byte[2*(245 / 8)-1], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt074() {
        try {
            rc5ps = new RC5ParameterSpec(-23,213412,245, new byte[2*(245 / 8)-1], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt075() {
        try {
            rc5ps = new RC5ParameterSpec(-23,213412,245, new byte[2*(245 / 8)-1], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt076() {
        try {
            rc5ps = new RC5ParameterSpec(-23,213412,245, iv, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt077() {
        try {
            rc5ps = new RC5ParameterSpec(-23,213412,245, iv, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt078() {
        try {
            rc5ps = new RC5ParameterSpec(-23,213412,245, iv, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt079() {
        try {
            rc5ps = new RC5ParameterSpec(-23,213412,245, new byte[0], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt080() {
        try {
            rc5ps = new RC5ParameterSpec(-23,213412,245, new byte[0], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt081() {
        try {
            rc5ps = new RC5ParameterSpec(-23,213412,245, new byte[0], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt082() {
        try {
            rc5ps = new RC5ParameterSpec(-23,213412,245, null, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt083() {
        try {
            rc5ps = new RC5ParameterSpec(-23,213412,245, null, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt084() {
        try {
            rc5ps = new RC5ParameterSpec(-23,213412,245, null, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt085() {
        try {
            rc5ps = new RC5ParameterSpec(-23,rounds,245, new byte[2*(245 / 8)-1], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt086() {
        try {
            rc5ps = new RC5ParameterSpec(-23,rounds,245, new byte[2*(245 / 8)-1], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt087() {
        try {
            rc5ps = new RC5ParameterSpec(-23,rounds,245, new byte[2*(245 / 8)-1], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt088() {
        try {
            rc5ps = new RC5ParameterSpec(-23,rounds,245, iv, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt089() {
        try {
            rc5ps = new RC5ParameterSpec(-23,rounds,245, iv, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt090() {
        try {
            rc5ps = new RC5ParameterSpec(-23,rounds,245, iv, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt091() {
        try {
            rc5ps = new RC5ParameterSpec(-23,rounds,245, new byte[0], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt092() {
        try {
            rc5ps = new RC5ParameterSpec(-23,rounds,245, new byte[0], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt093() {
        try {
            rc5ps = new RC5ParameterSpec(-23,rounds,245, new byte[0], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt094() {
        try {
            rc5ps = new RC5ParameterSpec(-23,rounds,245, null, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt095() {
        try {
            rc5ps = new RC5ParameterSpec(-23,rounds,245, null, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt096() {
        try {
            rc5ps = new RC5ParameterSpec(-23,rounds,245, null, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt097() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-23412,245, new byte[2*(245 / 8)-1], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt098() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-23412,245, new byte[2*(245 / 8)-1], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt099() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-23412,245, new byte[2*(245 / 8)-1], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt100() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-23412,245, iv, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt101() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-23412,245, iv, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt102() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-23412,245, iv, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt103() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-23412,245, new byte[0], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt104() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-23412,245, new byte[0], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt105() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-23412,245, new byte[0], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt106() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-23412,245, null, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt107() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-23412,245, null, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt108() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-23412,245, null, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt109() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-1,245, new byte[2*(245 / 8)-1], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt110() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-1,245, new byte[2*(245 / 8)-1], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt111() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-1,245, new byte[2*(245 / 8)-1], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt112() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-1,245, iv, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt113() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-1,245, iv, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt114() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-1,245, iv, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt115() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-1,245, new byte[0], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt116() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-1,245, new byte[0], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt117() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-1,245, new byte[0], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt118() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-1,245, null, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt119() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-1,245, null, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt120() {
        try {
            rc5ps = new RC5ParameterSpec(-23,-1,245, null, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt121() {
        try {
            rc5ps = new RC5ParameterSpec(0,0,245, new byte[2*(245 / 8)-1], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt122() {
        try {
            rc5ps = new RC5ParameterSpec(0,0,245, new byte[2*(245 / 8)-1], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt123() {
        try {
            rc5ps = new RC5ParameterSpec(0,0,245, new byte[2*(245 / 8)-1], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt124() {
        try {
            rc5ps = new RC5ParameterSpec(0,0,245, iv, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt125() {
        try {
            rc5ps = new RC5ParameterSpec(0,0,245, iv, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt126() {
        try {
            rc5ps = new RC5ParameterSpec(0,0,245, iv, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt127() {
        try {
            rc5ps = new RC5ParameterSpec(0,0,245, new byte[0], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt128() {
        try {
            rc5ps = new RC5ParameterSpec(0,0,245, new byte[0], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt129() {
        try {
            rc5ps = new RC5ParameterSpec(0,0,245, new byte[0], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt130() {
        try {
            rc5ps = new RC5ParameterSpec(0,0,245, null, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt131() {
        try {
            rc5ps = new RC5ParameterSpec(0,0,245, null, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt132() {
        try {
            rc5ps = new RC5ParameterSpec(0,0,245, null, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt133() {
        try {
            rc5ps = new RC5ParameterSpec(0,213412,245, new byte[2*(245 / 8)-1], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt134() {
        try {
            rc5ps = new RC5ParameterSpec(0,213412,245, new byte[2*(245 / 8)-1], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt135() {
        try {
            rc5ps = new RC5ParameterSpec(0,213412,245, new byte[2*(245 / 8)-1], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt136() {
        try {
            rc5ps = new RC5ParameterSpec(0,213412,245, iv, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt137() {
        try {
            rc5ps = new RC5ParameterSpec(0,213412,245, iv, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt138() {
        try {
            rc5ps = new RC5ParameterSpec(0,213412,245, iv, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt139() {
        try {
            rc5ps = new RC5ParameterSpec(0,213412,245, new byte[0], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt140() {
        try {
            rc5ps = new RC5ParameterSpec(0,213412,245, new byte[0], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt141() {
        try {
            rc5ps = new RC5ParameterSpec(0,213412,245, new byte[0], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt142() {
        try {
            rc5ps = new RC5ParameterSpec(0,213412,245, null, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt143() {
        try {
            rc5ps = new RC5ParameterSpec(0,213412,245, null, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt144() {
        try {
            rc5ps = new RC5ParameterSpec(0,213412,245, null, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt145() {
        try {
            rc5ps = new RC5ParameterSpec(0,rounds,245, new byte[2*(245 / 8)-1], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt146() {
        try {
            rc5ps = new RC5ParameterSpec(0,rounds,245, new byte[2*(245 / 8)-1], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt147() {
        try {
            rc5ps = new RC5ParameterSpec(0,rounds,245, new byte[2*(245 / 8)-1], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt148() {
        try {
            rc5ps = new RC5ParameterSpec(0,rounds,245, iv, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt149() {
        try {
            rc5ps = new RC5ParameterSpec(0,rounds,245, iv, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt150() {
        try {
            rc5ps = new RC5ParameterSpec(0,rounds,245, iv, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt151() {
        try {
            rc5ps = new RC5ParameterSpec(0,rounds,245, new byte[0], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt152() {
        try {
            rc5ps = new RC5ParameterSpec(0,rounds,245, new byte[0], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt153() {
        try {
            rc5ps = new RC5ParameterSpec(0,rounds,245, new byte[0], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt154() {
        try {
            rc5ps = new RC5ParameterSpec(0,rounds,245, null, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt155() {
        try {
            rc5ps = new RC5ParameterSpec(0,rounds,245, null, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt156() {
        try {
            rc5ps = new RC5ParameterSpec(0,rounds,245, null, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt157() {
        try {
            rc5ps = new RC5ParameterSpec(0,-23412,245, new byte[2*(245 / 8)-1], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt158() {
        try {
            rc5ps = new RC5ParameterSpec(0,-23412,245, new byte[2*(245 / 8)-1], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt159() {
        try {
            rc5ps = new RC5ParameterSpec(0,-23412,245, new byte[2*(245 / 8)-1], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt160() {
        try {
            rc5ps = new RC5ParameterSpec(0,-23412,245, iv, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt161() {
        try {
            rc5ps = new RC5ParameterSpec(0,-23412,245, iv, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt162() {
        try {
            rc5ps = new RC5ParameterSpec(0,-23412,245, iv, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt163() {
        try {
            rc5ps = new RC5ParameterSpec(0,-23412,245, new byte[0], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt164() {
        try {
            rc5ps = new RC5ParameterSpec(0,-23412,245, new byte[0], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt165() {
        try {
            rc5ps = new RC5ParameterSpec(0,-23412,245, new byte[0], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt166() {
        try {
            rc5ps = new RC5ParameterSpec(0,-23412,245, null, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt167() {
        try {
            rc5ps = new RC5ParameterSpec(0,-23412,245, null, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt168() {
        try {
            rc5ps = new RC5ParameterSpec(0,-23412,245, null, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt169() {
        try {
            rc5ps = new RC5ParameterSpec(0,-1,245, new byte[2*(245 / 8)-1], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt170() {
        try {
            rc5ps = new RC5ParameterSpec(0,-1,245, new byte[2*(245 / 8)-1], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt171() {
        try {
            rc5ps = new RC5ParameterSpec(0,-1,245, new byte[2*(245 / 8)-1], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt172() {
        try {
            rc5ps = new RC5ParameterSpec(0,-1,245, iv, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt173() {
        try {
            rc5ps = new RC5ParameterSpec(0,-1,245, iv, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt174() {
        try {
            rc5ps = new RC5ParameterSpec(0,-1,245, iv, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt175() {
        try {
            rc5ps = new RC5ParameterSpec(0,-1,245, new byte[0], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt176() {
        try {
            rc5ps = new RC5ParameterSpec(0,-1,245, new byte[0], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt177() {
        try {
            rc5ps = new RC5ParameterSpec(0,-1,245, new byte[0], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt178() {
        try {
            rc5ps = new RC5ParameterSpec(0,-1,245, null, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt179() {
        try {
            rc5ps = new RC5ParameterSpec(0,-1,245, null, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt180() {
        try {
            rc5ps = new RC5ParameterSpec(0,-1,245, null, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt181() {
        try {
            rc5ps = new RC5ParameterSpec(version,0,245, new byte[2*(245 / 8)-1], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt182() {
        try {
            rc5ps = new RC5ParameterSpec(version,0,245, new byte[2*(245 / 8)-1], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt183() {
        try {
            rc5ps = new RC5ParameterSpec(version,0,245, new byte[2*(245 / 8)-1], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt184() {
        try {
            rc5ps = new RC5ParameterSpec(version,0,245, iv, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt185() {
        try {
            rc5ps = new RC5ParameterSpec(version,0,245, iv, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt186() {
        try {
            rc5ps = new RC5ParameterSpec(version,0,245, iv, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt187() {
        try {
            rc5ps = new RC5ParameterSpec(version,0,245, new byte[0], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt188() {
        try {
            rc5ps = new RC5ParameterSpec(version,0,245, new byte[0], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt189() {
        try {
            rc5ps = new RC5ParameterSpec(version,0,245, new byte[0], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt190() {
        try {
            rc5ps = new RC5ParameterSpec(version,0,245, null, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt191() {
        try {
            rc5ps = new RC5ParameterSpec(version,0,245, null, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt192() {
        try {
            rc5ps = new RC5ParameterSpec(version,0,245, null, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt193() {
        try {
            rc5ps = new RC5ParameterSpec(version,213412,245, new byte[2*(245 / 8)-1], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt194() {
        try {
            rc5ps = new RC5ParameterSpec(version,213412,245, new byte[2*(245 / 8)-1], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt195() {
        try {
            rc5ps = new RC5ParameterSpec(version,213412,245, new byte[2*(245 / 8)-1], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt196() {
        try {
            rc5ps = new RC5ParameterSpec(version,213412,245, iv, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt197() {
        try {
            rc5ps = new RC5ParameterSpec(version,213412,245, iv, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt198() {
        try {
            rc5ps = new RC5ParameterSpec(version,213412,245, iv, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt199() {
        try {
            rc5ps = new RC5ParameterSpec(version,213412,245, new byte[0], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt200() {
        try {
            rc5ps = new RC5ParameterSpec(version,213412,245, new byte[0], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt201() {
        try {
            rc5ps = new RC5ParameterSpec(version,213412,245, new byte[0], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt202() {
        try {
            rc5ps = new RC5ParameterSpec(version,213412,245, null, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt203() {
        try {
            rc5ps = new RC5ParameterSpec(version,213412,245, null, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt204() {
        try {
            rc5ps = new RC5ParameterSpec(version,213412,245, null, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt205() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,245, new byte[2*(245 / 8)-1], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt206() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,245, new byte[2*(245 / 8)-1], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt207() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,245, new byte[2*(245 / 8)-1], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt208() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,245, iv, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt209() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,245, iv, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt210() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,245, iv, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt211() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,245, new byte[0], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt212() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,245, new byte[0], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt213() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,245, new byte[0], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt214() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,245, null, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt215() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,245, null, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt216() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,245, null, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt217() {
        try {
            rc5ps = new RC5ParameterSpec(version,-23412,245, new byte[2*(245 / 8)-1], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt218() {
        try {
            rc5ps = new RC5ParameterSpec(version,-23412,245, new byte[2*(245 / 8)-1], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt219() {
        try {
            rc5ps = new RC5ParameterSpec(version,-23412,245, new byte[2*(245 / 8)-1], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt220() {
        try {
            rc5ps = new RC5ParameterSpec(version,-23412,245, iv, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt221() {
        try {
            rc5ps = new RC5ParameterSpec(version,-23412,245, iv, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt222() {
        try {
            rc5ps = new RC5ParameterSpec(version,-23412,245, iv, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt223() {
        try {
            rc5ps = new RC5ParameterSpec(version,-23412,245, new byte[0], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt224() {
        try {
            rc5ps = new RC5ParameterSpec(version,-23412,245, new byte[0], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt225() {
        try {
            rc5ps = new RC5ParameterSpec(version,-23412,245, new byte[0], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt226() {
        try {
            rc5ps = new RC5ParameterSpec(version,-23412,245, null, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt227() {
        try {
            rc5ps = new RC5ParameterSpec(version,-23412,245, null, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt228() {
        try {
            rc5ps = new RC5ParameterSpec(version,-23412,245, null, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt229() {
        try {
            rc5ps = new RC5ParameterSpec(version,-1,245, new byte[2*(245 / 8)-1], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt230() {
        try {
            rc5ps = new RC5ParameterSpec(version,-1,245, new byte[2*(245 / 8)-1], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt231() {
        try {
            rc5ps = new RC5ParameterSpec(version,-1,245, new byte[2*(245 / 8)-1], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt232() {
        try {
            rc5ps = new RC5ParameterSpec(version,-1,245, iv, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt233() {
        try {
            rc5ps = new RC5ParameterSpec(version,-1,245, iv, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt234() {
        try {
            rc5ps = new RC5ParameterSpec(version,-1,245, iv, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt235() {
        try {
            rc5ps = new RC5ParameterSpec(version,-1,245, new byte[0], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt236() {
        try {
            rc5ps = new RC5ParameterSpec(version,-1,245, new byte[0], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt237() {
        try {
            rc5ps = new RC5ParameterSpec(version,-1,245, new byte[0], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt238() {
        try {
            rc5ps = new RC5ParameterSpec(version,-1,245, null, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt239() {
        try {
            rc5ps = new RC5ParameterSpec(version,-1,245, null, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt240() {
        try {
            rc5ps = new RC5ParameterSpec(version,-1,245, null, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt241() {
        try {
            rc5ps = new RC5ParameterSpec(-1,0,245, new byte[2*(245 / 8)-1], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt242() {
        try {
            rc5ps = new RC5ParameterSpec(-1,0,245, new byte[2*(245 / 8)-1], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt243() {
        try {
            rc5ps = new RC5ParameterSpec(-1,0,245, new byte[2*(245 / 8)-1], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt244() {
        try {
            rc5ps = new RC5ParameterSpec(-1,0,245, iv, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt245() {
        try {
            rc5ps = new RC5ParameterSpec(-1,0,245, iv, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt246() {
        try {
            rc5ps = new RC5ParameterSpec(-1,0,245, iv, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt247() {
        try {
            rc5ps = new RC5ParameterSpec(-1,0,245, new byte[0], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt248() {
        try {
            rc5ps = new RC5ParameterSpec(-1,0,245, new byte[0], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt249() {
        try {
            rc5ps = new RC5ParameterSpec(-1,0,245, new byte[0], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt250() {
        try {
            rc5ps = new RC5ParameterSpec(-1,0,245, null, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt251() {
        try {
            rc5ps = new RC5ParameterSpec(-1,0,245, null, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt252() {
        try {
            rc5ps = new RC5ParameterSpec(-1,0,245, null, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt253() {
        try {
            rc5ps = new RC5ParameterSpec(-1,213412,245, new byte[2*(245 / 8)-1], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt254() {
        try {
            rc5ps = new RC5ParameterSpec(-1,213412,245, new byte[2*(245 / 8)-1], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt255() {
        try {
            rc5ps = new RC5ParameterSpec(-1,213412,245, new byte[2*(245 / 8)-1], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt256() {
        try {
            rc5ps = new RC5ParameterSpec(-1,213412,245, iv, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt257() {
        try {
            rc5ps = new RC5ParameterSpec(-1,213412,245, iv, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt258() {
        try {
            rc5ps = new RC5ParameterSpec(-1,213412,245, iv, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt259() {
        try {
            rc5ps = new RC5ParameterSpec(-1,213412,245, new byte[0], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt260() {
        try {
            rc5ps = new RC5ParameterSpec(-1,213412,245, new byte[0], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt261() {
        try {
            rc5ps = new RC5ParameterSpec(-1,213412,245, new byte[0], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt262() {
        try {
            rc5ps = new RC5ParameterSpec(-1,213412,245, null, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt263() {
        try {
            rc5ps = new RC5ParameterSpec(-1,213412,245, null, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt264() {
        try {
            rc5ps = new RC5ParameterSpec(-1,213412,245, null, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt265() {
        try {
            rc5ps = new RC5ParameterSpec(-1,rounds,245, new byte[2*(245 / 8)-1], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt266() {
        try {
            rc5ps = new RC5ParameterSpec(-1,rounds,245, new byte[2*(245 / 8)-1], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt267() {
        try {
            rc5ps = new RC5ParameterSpec(-1,rounds,245, new byte[2*(245 / 8)-1], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt268() {
        try {
            rc5ps = new RC5ParameterSpec(-1,rounds,245, iv, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt269() {
        try {
            rc5ps = new RC5ParameterSpec(-1,rounds,245, iv, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt270() {
        try {
            rc5ps = new RC5ParameterSpec(-1,rounds,245, iv, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt271() {
        try {
            rc5ps = new RC5ParameterSpec(-1,rounds,245, new byte[0], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt272() {
        try {
            rc5ps = new RC5ParameterSpec(-1,rounds,245, new byte[0], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt273() {
        try {
            rc5ps = new RC5ParameterSpec(-1,rounds,245, new byte[0], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt274() {
        try {
            rc5ps = new RC5ParameterSpec(-1,rounds,245, null, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt275() {
        try {
            rc5ps = new RC5ParameterSpec(-1,rounds,245, null, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt276() {
        try {
            rc5ps = new RC5ParameterSpec(-1,rounds,245, null, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt277() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-23412,245, new byte[2*(245 / 8)-1], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt278() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-23412,245, new byte[2*(245 / 8)-1], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt279() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-23412,245, new byte[2*(245 / 8)-1], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt280() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-23412,245, iv, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt281() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-23412,245, iv, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt282() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-23412,245, iv, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt283() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-23412,245, new byte[0], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt284() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-23412,245, new byte[0], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt285() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-23412,245, new byte[0], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt286() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-23412,245, null, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt287() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-23412,245, null, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt288() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-23412,245, null, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt289() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-1,245, new byte[2*(245 / 8)-1], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt290() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-1,245, new byte[2*(245 / 8)-1], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt291() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-1,245, new byte[2*(245 / 8)-1], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt292() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-1,245, iv, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt293() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-1,245, iv, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt294() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-1,245, iv, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt295() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-1,245, new byte[0], 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt296() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-1,245, new byte[0], 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt297() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-1,245, new byte[0], -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt298() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-1,245, null, 4);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt299() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-1,245, null, 0);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testRC5ParameterSpecIntIntIntByteArrayInt300() {
        try {
            rc5ps = new RC5ParameterSpec(-1,-1,245, null, -1);
            fail("must raise exception");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);

        }
    }


    /*
     * Test method for 'javax.crypto.spec.RC5ParameterSpec.getVersion()'
     */
    public final void testGetVersion001() {
        RC5ParameterSpecIntIntInt();
        assertEquals("Diferent version", version, rc5ps.getVersion());
    }
    public final void testGetVersion002() {
        RC5ParameterSpecIntIntIntByteArray();
        assertEquals("Diferent version", version, rc5ps.getVersion());
    }
    public final void testGetVersion003() {
        RC5ParameterSpecIntIntIntByteArrayInt();
        assertEquals("Diferent version", version, rc5ps.getVersion());
    }

    /*
     * Test method for 'javax.crypto.spec.RC5ParameterSpec.getRounds()'
     */
    public final void testGetRounds001() {
        RC5ParameterSpecIntIntInt();
        assertEquals("Diferent rounds", rounds, rc5ps.getRounds());
    }
    public final void testGetRounds002() {
        RC5ParameterSpecIntIntIntByteArray();
        assertEquals("Diferent rounds", rounds, rc5ps.getRounds());
    }
    public final void testGetRounds003() {
        RC5ParameterSpecIntIntIntByteArrayInt();
        assertEquals("Diferent rounds", rounds, rc5ps.getRounds());
    }

    /*
     * Test method for 'javax.crypto.spec.RC5ParameterSpec.getWordSize()'
     */
    public final void testGetWordSize001() {
        RC5ParameterSpecIntIntInt();
        assertEquals("Diferent wordSize", wordSize, rc5ps.getWordSize());
    }
    public final void testGetWordSize002() {
        RC5ParameterSpecIntIntIntByteArray();
        assertEquals("Diferent wordSize", wordSize, rc5ps.getWordSize());
    }
    public final void testGetWordSize003() {
        RC5ParameterSpecIntIntIntByteArrayInt();
        assertEquals("Diferent wordSize", wordSize, rc5ps.getWordSize());
    }

    /*
     * Test method for 'javax.crypto.spec.RC5ParameterSpec.getIV()'
     */
    public final void testGetIV001() {
        try {
            RC5ParameterSpecIntIntInt();
            assertNull("IV not uses", rc5ps.getIV());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    public final void testGetIV002() {
        try {
            RC5ParameterSpecIntIntIntByteArray();
            for (int i=0; i < ivLen-5; i++) {
                assertEquals("Diferent IV", iv[i], rc5ps.getIV()[i]);
            }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    public final void testGetIV003() {
        try {
            RC5ParameterSpecIntIntIntByteArrayInt();
            for (int i=0; i < ivLen-5; i++) {
                assertEquals("Diferent IV", iv[i+4], rc5ps.getIV()[i]);
            }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }




    /*
     * Test method for 'javax.crypto.spec.RC5ParameterSpec.equals(Object)'
     */

    public final void testEquals001() {
        try {

            RC5ParameterSpecIntIntInt();
            assertFalse("Two diferrent parameters inited SecretKeySpec have be equals", rc5ps.equals(null));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    public final void testEquals002() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize-1  );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals003() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize-1 , iv50 );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals004() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize-1 , iv50 , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals005() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize-1 , iv50 , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals006() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize-1 , iv );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals007() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize-1 , iv , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals008() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize-1 , iv , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals009() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize  );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals010() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize , iv50 );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals011() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize , iv50 , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals012() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize , iv50 , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals013() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize , iv );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertTrue("Two idem parameters inited  are not equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals014() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize , iv , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertTrue("Two idem parameters inited  are not equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals015() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize , iv , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertTrue("Two idem parameters inited  are not equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals016() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds-1,wordSize-1  );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals017() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds-1,wordSize-1 , iv50 );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals018() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds-1,wordSize-1 , iv50 , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals019() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds-1,wordSize-1 , iv50 , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals020() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds-1,wordSize-1 , iv );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals021() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds-1,wordSize-1 , iv , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals022() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds-1,wordSize-1 , iv , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals023() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds-1,wordSize  );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals024() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds-1,wordSize , iv50 );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals025() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds-1,wordSize , iv50 , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals026() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds-1,wordSize , iv50 , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals027() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds-1,wordSize , iv );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals028() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds-1,wordSize , iv , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals029() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds-1,wordSize , iv , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals030() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds,wordSize-1  );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals031() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds,wordSize-1 , iv50 );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals032() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds,wordSize-1 , iv50 , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals033() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds,wordSize-1 , iv50 , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals034() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds,wordSize-1 , iv );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals035() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds,wordSize-1 , iv , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals036() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds,wordSize-1 , iv , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals037() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds,wordSize  );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals038() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds,wordSize , iv50 );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals039() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds,wordSize , iv50 , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals040() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds,wordSize , iv50 , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals041() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds,wordSize , iv );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals042() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds,wordSize , iv , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals043() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds,wordSize , iv , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals044() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds-1,wordSize-1  );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals045() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds-1,wordSize-1 , iv50 );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals046() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds-1,wordSize-1 , iv50 , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals047() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds-1,wordSize-1 , iv50 , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals048() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds-1,wordSize-1 , iv );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals049() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds-1,wordSize-1 , iv , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals050() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds-1,wordSize-1 , iv , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals051() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds-1,wordSize  );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals052() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds-1,wordSize , iv50 );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals053() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds-1,wordSize , iv50 , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals054() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds-1,wordSize , iv50 , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals055() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds-1,wordSize , iv );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals056() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds-1,wordSize , iv , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals057() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds-1,wordSize , iv , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            assertFalse("Two diferent parameters inited are equals", rc5ps2.equals(rc5ps));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }


    /*
     * Test method for 'javax.crypto.spec.RC5ParameterSpec.hashCode()'
     */


    public final void testHashCode001() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize-1  );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode002() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize-1 , new byte[50] );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode003() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize-1 , new byte[50] , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode004() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize-1 , new byte[50] , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode005() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize-1 , iv );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode006() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize-1 , iv , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode007() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize-1 , iv , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode008() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize  );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode009() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize , new byte[50] );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode010() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize , new byte[50] , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode011() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize , new byte[50] , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode012() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize , iv );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode013() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize , iv , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode014() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds,wordSize , iv , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode015() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds-1,wordSize-1  );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode016() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds-1,wordSize-1 , new byte[50] );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode017() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds-1,wordSize-1 , new byte[50] , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode018() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds-1,wordSize-1 , new byte[50] , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode019() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds-1,wordSize-1 , iv );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode020() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds-1,wordSize-1 , iv , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode021() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds-1,wordSize-1 , iv , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode022() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds-1,wordSize  );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode023() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds-1,wordSize , new byte[50] );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode024() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds-1,wordSize , new byte[50] , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode025() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds-1,wordSize , new byte[50] , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode026() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds-1,wordSize , iv );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode027() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds-1,wordSize , iv , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode028() {
        try {
            rc5ps = new RC5ParameterSpec(version,rounds-1,wordSize , iv , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode029() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds,wordSize-1  );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode030() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds,wordSize-1 , new byte[50] );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode031() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds,wordSize-1 , new byte[50] , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode032() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds,wordSize-1 , new byte[50] , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode033() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds,wordSize-1 , iv );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode034() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds,wordSize-1 , iv , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode035() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds,wordSize-1 , iv , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode036() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds,wordSize  );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode037() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds,wordSize , new byte[50] );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode038() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds,wordSize , new byte[50] , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode039() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds,wordSize , new byte[50] , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode040() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds,wordSize , iv );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode041() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds,wordSize , iv , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode042() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds,wordSize , iv , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode043() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds-1,wordSize-1  );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode044() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds-1,wordSize-1 , new byte[50] );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode045() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds-1,wordSize-1 , new byte[50] , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode046() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds-1,wordSize-1 , new byte[50] , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode047() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds-1,wordSize-1 , iv );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode048() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds-1,wordSize-1 , iv , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode049() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds-1,wordSize-1 , iv , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode050() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds-1,wordSize  );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode051() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds-1,wordSize , new byte[50] );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode052() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds-1,wordSize , new byte[50] , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode053() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds-1,wordSize , new byte[50] , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode054() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds-1,wordSize , iv );

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode055() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds-1,wordSize , iv , 0);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode056() {
        try {
            rc5ps = new RC5ParameterSpec(version+1,rounds-1,wordSize , iv , 4);

            RC5ParameterSpec rc5ps2 = new RC5ParameterSpec(version, rounds, wordSize, iv);
            if (rc5ps2.equals(rc5ps)) {
    assertEquals("Two idem parameters inited  have distint hashCode", rc5ps2.hashCode() , rc5ps.hashCode());
    }
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }


}
