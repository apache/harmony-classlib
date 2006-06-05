package ar.org.fitc.test.crypto.spec;

import java.security.InvalidKeyException;

import javax.crypto.spec.DESedeKeySpec;

import junit.framework.TestCase;

import org.bouncycastle.util.encoders.Hex;

import ar.org.fitc.test.util.Messages;

public class TestDESedeKeySpec extends TestCase implements Messages {

    private final static String NONPARITY_ADJUSTED_KEYS = "C785218C7EC8FF99C785218C7EC8FF99C785218C7EC8FF99C785";

    private final static String PARITY_ADJUSTED_1 = "6E6E6E6E6E6E6E6E6E6E6E6E6E6E6E6E6E6E6E6E6E6E6E6E";

    private final static String PARITY_ADJUSTED_2 = "010101010101010101010101010101010101010101010101";

    @SuppressWarnings("unused")
    private DESedeKeySpec deks = null;

    private byte[] input = null;

    private int offset = 0;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestDESedeKeySpec.class);
    }

    public TestDESedeKeySpec(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        offset = 150;
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'javax.crypto.spec.DESedeKeySpec.DESedeKeySpec(byte[])'
     */
    public final void testDESedeKeySpecByteArray001() {
        try {
            deks = new DESedeKeySpec(input);
            fail(msgNullPointer);
        } catch (NullPointerException e) {
        } catch (Throwable e) {
            fail(msgNullPointer);
        }
    }

    public final void testDESedeKeySpecByteArray002() {
        try {
            byte[] input = { (byte) 0xc7, (byte) 0x85, (byte) 0x21,
                    (byte) 0x8c, (byte) 0x7e, (byte) 0xc8, (byte) 0xff };
            deks = new DESedeKeySpec(input);
            fail(msgInvalidKey);
        } catch (InvalidKeyException e) {
        } catch (Throwable e) {
            fail(msgInvalidKey);
        }
    }

    public final void testDESedeKeySpecByteArray003() {
        try {
            deks = new DESedeKeySpec(Hex.decode(NONPARITY_ADJUSTED_KEYS));
            assertTrue(msgNotInstance, deks instanceof DESedeKeySpec);
        } catch (Throwable e) {
            fail(msgNoException);
        }
    }

    /*
     * Test method for 'javax.crypto.spec.DESedeKeySpec.DESedeKeySpec(byte[],
     * int)'
     */
    public final void testDESedeKeySpecByteArrayInt001() {
        try {
            deks = new DESedeKeySpec(input, offset);
            fail(msgNullPointer);
        } catch (NullPointerException e) {
        } catch (Throwable e) {
            fail(msgNullPointer);
        }
    }

    public final void testDESedeKeySpecByteArrayInt002() {
        try {
            byte[] input = { (byte) 0xc7, (byte) 0x85, (byte) 0x21,
                    (byte) 0x8c, (byte) 0x7e, (byte) 0xc8, (byte) 0xff };
            deks = new DESedeKeySpec(input, offset);
            fail(msgInvalidKey);
        } catch (InvalidKeyException e) {
        } catch (Throwable e) {
            fail(msgInvalidKey);
        }
    }

    public final void testDESedeKeySpecByteArrayInt003() {
        try {
            offset = 145;
            deks = new DESedeKeySpec(Hex.decode(NONPARITY_ADJUSTED_KEYS), offset);
            fail(msgInvalidKey);
        } catch (InvalidKeyException e) {
        } catch (Throwable e) {
            fail(msgInvalidKey);
        }
    }

    public final void testDESedeKeySpecByteArrayInt004() {
        offset = 1;
        try {
            deks = new DESedeKeySpec(Hex.decode(NONPARITY_ADJUSTED_KEYS), offset);
            assertTrue(msgNotInstance, deks instanceof DESedeKeySpec);
        } catch (Throwable e) {
            fail(msgNoException);
        }
    }

    /*
     * Test method for 'javax.crypto.spec.DESedeKeySpec.getKey()'
     */
    public final void testGetKey001() {
        offset = 2;
        try {
            deks = new DESedeKeySpec(Hex.decode(NONPARITY_ADJUSTED_KEYS), offset);
            for (int i = 0; i < deks.getKey().length; i++) {
                assertEquals("Not the same array element", Hex
                        .decode(NONPARITY_ADJUSTED_KEYS)[i + offset], deks
                        .getKey()[i]);
            }
        } catch (Throwable e) {
            fail(msgNoException);
        }
    }

    public final void testGetKey002() {
        offset = 2;
        try {
            deks = new DESedeKeySpec(Hex.decode(NONPARITY_ADJUSTED_KEYS), offset);
            assertTrue("Not a byte array", deks.getKey() instanceof byte[]);
        } catch (Throwable e) {
            fail(msgNoException);
        }
    }

    /*
     * Test method for 'javax.crypto.spec.DESedeKeySpec.isParityAdjusted(byte[],
     * int)'
     */
    public final void testIsParityAdjusted001() {
        offset = 2;
        try {
            assertFalse("Sould not be Parity Adjusted", DESedeKeySpec
                    .isParityAdjusted(Hex.decode(NONPARITY_ADJUSTED_KEYS), offset));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public final void testIsParityAdjusted002() {
        offset = 0;
        try {
            assertTrue("Sould be Parity Adjusted", DESedeKeySpec
                    .isParityAdjusted(Hex.decode(PARITY_ADJUSTED_1), offset));
        } catch (Throwable e) {
            fail(msgNoException + e);
        }

    }

    public final void testIsParityAdjusted003() {
        offset = 0;
        try {
            assertTrue("Sould be Parity Adjusted", DESedeKeySpec
                    .isParityAdjusted(Hex.decode(PARITY_ADJUSTED_2), offset));
        } catch (Throwable e) {
            fail(msgNoException + e);
        }

    }

}
