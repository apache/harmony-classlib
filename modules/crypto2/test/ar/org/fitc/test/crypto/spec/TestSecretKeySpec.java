package ar.org.fitc.test.crypto.spec;

import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Hex;

import junit.framework.TestCase;

public class TestSecretKeySpec extends TestCase {

    public String key = "Estoy es una prueba sege as";
    public String algorithm = "DES";
    public SecretKeySpec sks = null;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestSecretKeySpec.class);
    }

    public TestSecretKeySpec(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }


    /*
     * Test method for 'javax.crypto.spec.SecretKeySpec.SecretKeySpec(byte[], String)'
     */
    public final void testSecretKeySpecByteArrayString001() {
        sks = new SecretKeySpec(Hex.decode(key), algorithm);
        assertTrue("Can't instance SecretKeySpec", sks instanceof SecretKeySpec);
    }
    public final void testSecretKeySpecByteArrayString002() {
        try {
            sks = new SecretKeySpec(null, algorithm);
            fail("raise exception IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    public final void testSecretKeySpecByteArrayString003() {
        try {
            sks = new SecretKeySpec(null, null);
            fail("raise exception IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    public final void testSecretKeySpecByteArrayString004() {
        try {
            sks = new SecretKeySpec(Hex.decode(key), null);
            fail("raise exception IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    public final void testSecretKeySpecByteArrayString005() {
        try {
            sks = new SecretKeySpec(new byte[0], null);
            fail("raise exception IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    public final void testSecretKeySpecByteArrayString006() {
        try {
            sks = new SecretKeySpec(new byte[0], algorithm);
            fail("raise exception IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    /*
     * Test method for 'javax.crypto.spec.SecretKeySpec.SecretKeySpec(byte[], int, int, String)'
     */
    public final void testSecretKeySpecByteArrayIntIntString001() {
        sks = new SecretKeySpec(Hex.decode(key), 2, 3, algorithm);
        assertTrue("Can't instance SecretKeySpec", sks instanceof SecretKeySpec);
    }
    public final void testSecretKeySpecByteArrayIntIntString002() {
        try {
            sks = new SecretKeySpec(Hex.decode(key), 0, 3, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString004() {
        try {
            sks = new SecretKeySpec(Hex.decode(key), 0, 0, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString005() {
        try {
            sks = new SecretKeySpec(Hex.decode(key), 0, -1, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString006() {
        try {
            sks = new SecretKeySpec(Hex.decode(key), 0, -1, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString007() {
        try {
            sks = new SecretKeySpec(Hex.decode(key), 0, 245, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString008() {
        try {
            sks = new SecretKeySpec(Hex.decode(key), 0, 245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString009() {
        try {
            sks = new SecretKeySpec(Hex.decode(key), -1, 3, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString010() {
        try {
            sks = new SecretKeySpec(Hex.decode(key), -1, 3, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString011() {
        try {
            sks = new SecretKeySpec(Hex.decode(key), -1, 0, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString012() {
        try {
            sks = new SecretKeySpec(Hex.decode(key), -1, 0, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString013() {
        try {
            sks = new SecretKeySpec(Hex.decode(key), -1, -1, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString014() {
        try {
            sks = new SecretKeySpec(Hex.decode(key), -1, -1, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString015() {
        try {
            sks = new SecretKeySpec(Hex.decode(key), -1, 245, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString016() {
        try {
            sks = new SecretKeySpec(Hex.decode(key), -1, 245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString017() {
        try {
            sks = new SecretKeySpec(Hex.decode(key), 234, 3, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString018() {
        try {
            sks = new SecretKeySpec(Hex.decode(key), 234, 3, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString019() {
        try {
            sks = new SecretKeySpec(Hex.decode(key), 234, 0, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString020() {
        try {
            sks = new SecretKeySpec(Hex.decode(key), 234, 0, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString021() {
        try {
            sks = new SecretKeySpec(Hex.decode(key), 234, -1, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString022() {
        try {
            sks = new SecretKeySpec(Hex.decode(key), 234, -1, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString023() {
        try {
            sks = new SecretKeySpec(Hex.decode(key), 234, 245, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString024() {
        try {
            sks = new SecretKeySpec(Hex.decode(key), 234, 245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString025() {
        try {
            sks = new SecretKeySpec(null, 0, 3, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString026() {
        try {
            sks = new SecretKeySpec(null, 0, 3, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString027() {
        try {
            sks = new SecretKeySpec(null, 0, 0, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString028() {
        try {
            sks = new SecretKeySpec(null, 0, 0, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString029() {
        try {
            sks = new SecretKeySpec(null, 0, -1, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString030() {
        try {
            sks = new SecretKeySpec(null, 0, -1, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString031() {
        try {
            sks = new SecretKeySpec(null, 0, 245, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString032() {
        try {
            sks = new SecretKeySpec(null, 0, 245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString033() {
        try {
            sks = new SecretKeySpec(null, -1, 3, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString034() {
        try {
            sks = new SecretKeySpec(null, -1, 3, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString035() {
        try {
            sks = new SecretKeySpec(null, -1, 0, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString036() {
        try {
            sks = new SecretKeySpec(null, -1, 0, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString037() {
        try {
            sks = new SecretKeySpec(null, -1, -1, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString038() {
        try {
            sks = new SecretKeySpec(null, -1, -1, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString039() {
        try {
            sks = new SecretKeySpec(null, -1, 245, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString040() {
        try {
            sks = new SecretKeySpec(null, -1, 245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString041() {
        try {
            sks = new SecretKeySpec(null, 234, 3, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString042() {
        try {
            sks = new SecretKeySpec(null, 234, 3, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString043() {
        try {
            sks = new SecretKeySpec(null, 234, 0, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString044() {
        try {
            sks = new SecretKeySpec(null, 234, 0, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString045() {
        try {
            sks = new SecretKeySpec(null, 234, -1, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString046() {
        try {
            sks = new SecretKeySpec(null, 234, -1, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString047() {
        try {
            sks = new SecretKeySpec(null, 234, 245, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString048() {
        try {
            sks = new SecretKeySpec(null, 234, 245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString049() {
        try {
            sks = new SecretKeySpec(new byte[0], 0, 3, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString050() {
        try {
            sks = new SecretKeySpec(new byte[0], 0, 3, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString051() {
        try {
            sks = new SecretKeySpec(new byte[0], 0, 0, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString052() {
        try {
            sks = new SecretKeySpec(new byte[0], 0, 0, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString053() {
        try {
            sks = new SecretKeySpec(new byte[0], 0, -1, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString054() {
        try {
            sks = new SecretKeySpec(new byte[0], 0, -1, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString055() {
        try {
            sks = new SecretKeySpec(new byte[0], 0, 245, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString056() {
        try {
            sks = new SecretKeySpec(new byte[0], 0, 245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString057() {
        try {
            sks = new SecretKeySpec(new byte[0], -1, 3, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString058() {
        try {
            sks = new SecretKeySpec(new byte[0], -1, 3, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString059() {
        try {
            sks = new SecretKeySpec(new byte[0], -1, 0, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString060() {
        try {
            sks = new SecretKeySpec(new byte[0], -1, 0, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString061() {
        try {
            sks = new SecretKeySpec(new byte[0], -1, -1, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString062() {
        try {
            sks = new SecretKeySpec(new byte[0], -1, -1, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString063() {
        try {
            sks = new SecretKeySpec(new byte[0], -1, 245, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString064() {
        try {
            sks = new SecretKeySpec(new byte[0], -1, 245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString065() {
        try {
            sks = new SecretKeySpec(new byte[0], 234, 3, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString066() {
        try {
            sks = new SecretKeySpec(new byte[0], 234, 3, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString067() {
        try {
            sks = new SecretKeySpec(new byte[0], 234, 0, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString068() {
        try {
            sks = new SecretKeySpec(new byte[0], 234, 0, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString069() {
        try {
            sks = new SecretKeySpec(new byte[0], 234, -1, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString070() {
        try {
            sks = new SecretKeySpec(new byte[0], 234, -1, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString071() {
        try {
            sks = new SecretKeySpec(new byte[0], 234, 245, algorithm);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testSecretKeySpecByteArrayIntIntString072() {
        try {
            sks = new SecretKeySpec(new byte[0], 234, 245, null);
            fail("must raise exception");
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'javax.crypto.spec.SecretKeySpec.equals(Object)'
     */
    public final void testEquals001() {
        try {
            testSecretKeySpecByteArrayString001();
            SecretKeySpec sks2 = sks;
            testSecretKeySpecByteArrayString001();
            assertTrue("Two idem parameters inited SecretKeySpec have fail equals", sks.equals(sks2));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testEquals002() {
        try {

            testSecretKeySpecByteArrayIntIntString001();
            SecretKeySpec sks2 = sks;
            testSecretKeySpecByteArrayIntIntString001();
            assertTrue("Two idem parameters inited SecretKeySpec have fail equals", sks.equals(sks2));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    public final void testEquals003() {
        try {

            testSecretKeySpecByteArrayIntIntString001();
            SecretKeySpec sks2 = sks;
            testSecretKeySpecByteArrayString001();
            assertFalse("Two diferrent parameters inited SecretKeySpec have be equals", sks.equals(sks2));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    public final void testEquals004() {
        try {

            testSecretKeySpecByteArrayIntIntString001();

            assertFalse("Two diferrent parameters inited SecretKeySpec have be equals", sks.equals(null));
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    /*
     * Test method for 'javax.crypto.spec.SecretKeySpec.getAlgorithm()'
     */
    public final void testGetAlgorithm001() {
        try {

            testSecretKeySpecByteArrayString001();
            assertEquals("Two distint algorithm", algorithm, sks.getAlgorithm());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    public final void testGetAlgorithm002() {
        try {
            testSecretKeySpecByteArrayIntIntString001();
            assertEquals("Two distint algorithm", algorithm, sks.getAlgorithm());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }



    /*
     * Test method for 'javax.crypto.spec.SecretKeySpec.getEncoded()'
     */
    public final void testGetEncoded001() {
        try {
            testSecretKeySpecByteArrayString001();
            byte[] encode = sks.getEncoded();
            byte[] initEncode = Hex.decode(key);
            for (int i=0; i < initEncode.length; i++)
                assertEquals("Distint key", initEncode[i], encode[i]);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    public final void testGetEncoded002() {
        try {
            testSecretKeySpecByteArrayIntIntString001();
            byte[] encode = sks.getEncoded();
            byte[] initEncode = Hex.decode(key);
            for (int i=2; i < 5; i++)
                assertEquals("Distint key", initEncode[i], encode[i-2]);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    public final void testGetEncoded003() {
        try {
            testSecretKeySpecByteArrayIntIntString001();
            assertEquals("Equal lenght", 3, sks.getEncoded().length);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testGetEncoded004() {
        try {
            testSecretKeySpecByteArrayString001();
            assertNotSame("Distint array", sks.getEncoded(), sks.getEncoded());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    /*
     * Test method for 'javax.crypto.spec.SecretKeySpec.getFormat()'
     */
    public final void testGetFormat001() {
        try {
            testSecretKeySpecByteArrayString001();
            assertEquals("Format RAW", "RAW", sks.getFormat());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }



    /*
     * Test method for 'javax.crypto.spec.SecretKeySpec.hashCode()'
     */
    public final void testHashCode001() {
        try {
            testSecretKeySpecByteArrayString001();
            SecretKeySpec sks2 = sks;
            testSecretKeySpecByteArrayString001();
            assertEquals("Two idem parameters inited SecretKeySpec have distint hashCode", sks.hashCode(), sks2.hashCode());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testHashCode002() {
        try {

            testSecretKeySpecByteArrayIntIntString001();
            SecretKeySpec sks2 = sks;
            testSecretKeySpecByteArrayIntIntString001();
            assertEquals("Two idem parameters inited SecretKeySpec have distint hashCode", sks.hashCode(), sks2.hashCode());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    public final void testHashCode003() {
        try {

            testSecretKeySpecByteArrayIntIntString001();
            SecretKeySpec sks2 = sks;
            testSecretKeySpecByteArrayString001();
            assertFalse("Two diferrent parameters inited SecretKeySpec have same hashCode", sks.hashCode() == sks2.hashCode());
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }



}
