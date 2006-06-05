package ar.org.fitc.test.crypto.spec;

import java.security.InvalidKeyException;

import javax.crypto.spec.DESKeySpec;

import junit.framework.TestCase;

import org.bouncycastle.util.encoders.Hex;

import ar.org.fitc.test.util.Messages;

public class TestDESKeySpec extends TestCase implements Messages {

    private DESKeySpec dks = null;
    private byte[] input = null;
    private int offset = 0;

    private final static byte[] nonParityAdjustedKey = {(byte) 0xc7, (byte) 0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99,(byte)0xc7, (byte)0x85};
    private final static byte[] parityAdjustedKey = {(byte)0x6E,(byte)0x6E,(byte)0x6E,(byte)0x6E,(byte)0x6E,(byte)0x6E,(byte)0x6E,(byte)0x6E,(byte)0x6E,(byte)0x6E,(byte)0x6E};
    private final static byte[] parityAdjustedWeakKey = {(byte)0x01,(byte)0x01,(byte)0x01,(byte)0x01,(byte)0x01,(byte)0x01,(byte)0x01,(byte)0x01,(byte)0x01,(byte)0x01,(byte)0x01};

    private static String[] weak = {"0101 0101 0101 0101",
        "1F1F 1F1F 0E0E 0E0E",
        "E0E0 E0E0 F1F1 F1F1",
        "FEFE FEFE FEFE FEFE",
        "01FE 01FE 01FE 01FE",
        "FE01 FE01 FE01 FE01",
        "1FE0 1FE0 0EF1 0EF1",
        "E01F E01F F10E F10E",
        "01E0 01E0 01F1 01F1",
        "E001 E001 F101 F101",
        "1FFE 1FFE 0EFE 0EFE",
        "FE1F FE1F FE0E FE0E",
        "011F 011F 010E 010E",
        "1F01 1F01 0E01 0E01",
        "E0FE E0FE F1FE F1FE",
        "FEE0 FEE0 FEF1 FEF1"};

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestDESKeySpec.class);
    }

    public TestDESKeySpec(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'javax.crypto.spec.DESKeySpec.DESKeySpec(byte[])'
     */
    public final void testDESKeySpecByteArray001() {
        try {
            dks = new DESKeySpec(input);
            fail(msgNullPointer);
        } catch (NullPointerException e) {
        } catch (Throwable e) {
            fail(msgNullPointer);
        }
    }

    public final void testDESKeySpecByteArray002() {
        try {
            byte[] input = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff};
            dks = new DESKeySpec(input);
            fail(msgInvalidKey);
        } catch (InvalidKeyException e) {
        } catch (Throwable e) {
            fail(msgInvalidKey);
        }
    }

    public final void testDESKeySpecByteArray003() {
        try {
            dks = new DESKeySpec(nonParityAdjustedKey);
        } catch (Throwable e) {
            fail(msgNoException);
        }
    }

    /*
     * Test method for 'javax.crypto.spec.DESKeySpec.DESKeySpec(byte[], int)'
     */
    public final void testDESKeySpecByteArrayInt001() {
        try {
            dks = new DESKeySpec(input,offset);
            fail(msgNullPointer);
        } catch (NullPointerException e) {
        } catch (Throwable e) {
            fail(msgNullPointer);
        }
    }

    public final void testDESKeySpecByteArrayInt002() {
        try {
            byte[] input = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff};
            dks = new DESKeySpec(input,offset);
            fail(msgInvalidKey);
        } catch (InvalidKeyException e) {
        } catch (Throwable e) {
            fail(msgInvalidKey);
        }
    }

    public final void testDESKeySpecByteArrayInt003() {
        try {
            offset = 145;
            dks = new DESKeySpec(nonParityAdjustedKey,offset);
            fail(msgInvalidKey);
        } catch (InvalidKeyException e) {
        } catch (Throwable e) {
            fail(msgInvalidKey);
        }
    }

    public final void testDESKeySpecByteArrayInt004() {
        offset = 1;
        try {
            dks = new DESKeySpec(nonParityAdjustedKey,offset);
        } catch (Throwable e) {
            fail(msgNoException);
        }
    }

    /*
     * Test method for 'javax.crypto.spec.DESKeySpec.getKey()'
     */
    public final void testGetKey001() {
        offset = 2;
        try {
            dks = new DESKeySpec(nonParityAdjustedKey,offset);
            for (int i=0 ; i < dks.getKey().length ; i++)
                assertEquals("Not the same array element",nonParityAdjustedKey[i+offset],dks.getKey()[i]);
        } catch (Throwable e) {
            fail(msgNoException);
        }
    }

    public final void testGetKey002() {
        offset = 2;
        try {
            dks = new DESKeySpec(nonParityAdjustedKey,offset);
            assertTrue("Not a byte array",dks.getKey() instanceof byte[]);
        } catch (Throwable e) {
            fail(msgNoException);
        }
    }

    /*
     * Test method for 'javax.crypto.spec.DESKeySpec.isParityAdjusted(byte[], int)'
     */
    public final void testIsParityAdjusted001() {
        offset = 2;
        try {
            assertFalse("Sould not be Parity Adjusted",DESKeySpec.isParityAdjusted(nonParityAdjustedKey,offset));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public final void testIsParityAdjusted002() {
        try {
            assertTrue("Sould be Parity Adjusted",DESKeySpec.isParityAdjusted(parityAdjustedKey,offset));
        } catch (Throwable e) {
            fail(msgNoException+e);
        }

    }

    public final void testIsParityAdjusted003() {
        try {
            assertTrue("Sould be Parity Adjusted",DESKeySpec.isParityAdjusted(parityAdjustedWeakKey,offset));
        } catch (Throwable e) {
            fail(msgNoException+e);
        }

    }

    /*
     * Test method for 'javax.crypto.spec.DESKeySpec.isWeak(byte[], int)'
     */
    public final void testIsWeak001() {
        try {
            assertTrue("Must be a week key",DESKeySpec.isWeak(parityAdjustedWeakKey,offset));
        } catch (Throwable e) {
            fail(msgNoException+e);
        }
    }

    public final void testIsWeak002() {
        offset = 0;
        try {
         assertTrue("Must be a week key",DESKeySpec.isWeak(Hex.decode(weak[0]),offset));
        } catch (Throwable e) {
         fail(msgNoException+e);
        }
      }

      public final void testIsWeak003() {
        offset = 0;
        try {
         assertTrue("Must be a week key",DESKeySpec.isWeak(Hex.decode(weak[1]),offset));
        } catch (Throwable e) {
         fail(msgNoException+e);
        }
      }

      public final void testIsWeak004() {
        offset = 0;
        try {
         assertTrue("Must be a week key",DESKeySpec.isWeak(Hex.decode(weak[2]),offset));
        } catch (Throwable e) {
         fail(msgNoException+e);
        }
      }

      public final void testIsWeak005() {
        offset = 0;
        try {
         assertTrue("Must be a week key",DESKeySpec.isWeak(Hex.decode(weak[3]),offset));
        } catch (Throwable e) {
         fail(msgNoException+e);
        }
      }

      public final void testIsWeak006() {
        offset = 0;
        try {
         assertTrue("Must be a week key",DESKeySpec.isWeak(Hex.decode(weak[4]),offset));
        } catch (Throwable e) {
         fail(msgNoException+e);
        }
      }

      public final void testIsWeak007() {
        offset = 0;
        try {
         assertTrue("Must be a week key",DESKeySpec.isWeak(Hex.decode(weak[5]),offset));
        } catch (Throwable e) {
         fail(msgNoException+e);
        }
      }

      public final void testIsWeak008() {
        offset = 0;
        try {
         assertTrue("Must be a week key",DESKeySpec.isWeak(Hex.decode(weak[6]),offset));
        } catch (Throwable e) {
         fail(msgNoException+e);
        }
      }

      public final void testIsWeak009() {
        offset = 0;
        try {
         assertTrue("Must be a week key",DESKeySpec.isWeak(Hex.decode(weak[7]),offset));
        } catch (Throwable e) {
         fail(msgNoException+e);
        }
      }

      public final void testIsWeak010() {
        offset = 0;
        try {
         assertTrue("Must be a week key",DESKeySpec.isWeak(Hex.decode(weak[8]),offset));
        } catch (Throwable e) {
         fail(msgNoException+e);
        }
      }

      public final void testIsWeak011() {
        offset = 0;
        try {
         assertTrue("Must be a week key",DESKeySpec.isWeak(Hex.decode(weak[9]),offset));
        } catch (Throwable e) {
         fail(msgNoException+e);
        }
      }

      public final void testIsWeak012() {
        offset = 0;
        try {
         assertTrue("Must be a week key",DESKeySpec.isWeak(Hex.decode(weak[10]),offset));
        } catch (Throwable e) {
         fail(msgNoException+e);
        }
      }

      public final void testIsWeak013() {
        offset = 0;
        try {
         assertTrue("Must be a week key",DESKeySpec.isWeak(Hex.decode(weak[11]),offset));
        } catch (Throwable e) {
         fail(msgNoException+e);
        }
      }

      public final void testIsWeak014() {
        offset = 0;
        try {
         assertTrue("Must be a week key",DESKeySpec.isWeak(Hex.decode(weak[12]),offset));
        } catch (Throwable e) {
         fail(msgNoException+e);
        }
      }

      public final void testIsWeak015() {
        offset = 0;
        try {
         assertTrue("Must be a week key",DESKeySpec.isWeak(Hex.decode(weak[13]),offset));
        } catch (Throwable e) {
         fail(msgNoException+e);
        }
      }

      public final void testIsWeak016() {
        offset = 0;
        try {
         assertTrue("Must be a week key",DESKeySpec.isWeak(Hex.decode(weak[14]),offset));
        } catch (Throwable e) {
         fail(msgNoException+e);
        }
      }

      public final void testIsWeak017() {
        offset = 0;
        try {
         assertTrue("Must be a week key",DESKeySpec.isWeak(Hex.decode(weak[15]),offset));
        } catch (Throwable e) {
         fail(msgNoException+e);
        }
      }
}
