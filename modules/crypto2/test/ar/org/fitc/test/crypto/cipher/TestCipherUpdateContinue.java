package ar.org.fitc.test.crypto.cipher;

import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.security.AlgorithmParameters;

import javax.crypto.Cipher;

import ar.org.fitc.test.util.K;

@SuppressWarnings("unused")
public class TestCipherUpdateContinue extends TestCipherUpdate {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestCipherUpdateContinue.class);
    }

    public TestCipherUpdateContinue(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'javax.crypto.Cipher.update(byte[], int, int, byte[], int)'
     */
    public final void testUpdateByteArrayIntIntByteArrayInt001() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt002() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt003() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt004() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt005() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt006() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt007() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt008() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt009() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt010() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt011() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt012() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt013() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt014() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt015() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt016() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt017() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt018() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt019() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt020() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt021() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt022() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt023() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt024() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt025() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt026() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt027() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt028() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt029() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt030() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt031() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt032() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt033() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt034() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt035() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt036() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt037() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt038() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt039() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt040() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt041() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt042() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt043() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt044() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt045() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt046() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt047() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt048() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt049() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt050() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt051() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt052() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt053() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt054() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt055() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt056() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt057() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt058() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt059() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt060() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt061() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt062() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt063() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt064() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt065() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt066() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt067() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt068() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt069() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt070() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt071() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt072() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt073() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt074() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt075() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt076() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt077() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt078() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt079() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt080() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt081() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt082() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt083() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt084() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt085() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt086() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt087() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt088() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt089() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt090() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt091() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt092() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt093() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt094() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt095() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt096() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt097() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt098() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt099() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt100() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt101() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt102() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt103() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt104() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt105() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt106() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt107() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt108() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt109() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt110() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt111() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt112() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt113() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt114() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt115() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt116() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt117() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt118() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt119() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt120() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt121() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt122() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt123() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt124() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt125() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt126() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt127() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt128() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt129() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt130() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt131() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt132() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt133() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt134() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt135() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt136() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt137() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt138() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt139() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt140() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt141() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt142() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt143() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt144() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt145() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt146() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt147() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt148() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt149() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt150() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt151() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt152() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt153() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt154() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt155() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt156() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt157() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt158() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt159() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt160() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt161() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt162() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt163() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt164() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt165() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt166() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt167() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt168() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt169() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt170() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt171() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt172() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt173() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt174() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt175() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt176() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt177() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt178() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt179() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt180() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt181() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt182() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt183() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt184() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt185() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt186() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt187() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt188() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt189() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt190() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt191() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt192() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt193() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt194() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt195() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt196() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt197() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt198() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt199() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt200() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt201() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt202() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt203() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt204() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt205() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt206() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt207() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt208() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt209() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt210() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt211() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt212() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt213() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt214() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt215() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt216() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt217() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt218() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt219() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt220() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt221() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt222() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt223() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt224() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt225() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt226() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt227() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt228() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt229() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt230() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt231() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt232() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt233() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt234() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt235() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt236() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt237() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt238() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt239() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt240() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt241() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt242() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt243() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt244() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt245() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt246() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt247() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt248() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt249() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt250() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt251() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt252() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt253() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt254() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt255() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt256() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt257() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt258() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt259() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt260() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt261() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt262() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt263() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt264() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt265() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt266() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt267() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt268() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt269() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt270() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt271() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt272() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt273() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt274() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt275() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt276() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt277() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt278() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt279() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt280() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt281() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt282() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt283() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt284() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt285() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt286() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt287() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt288() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt289() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt290() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt291() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt292() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt293() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt294() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt295() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt296() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt297() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt298() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt299() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt300() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt301() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt302() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt303() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt304() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt305() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt306() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt307() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt308() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt309() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt310() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt311() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt312() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt313() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt314() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt315() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt316() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt317() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt318() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt319() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt320() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt321() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt322() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt323() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt324() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt325() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt326() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt327() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt328() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt329() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt330() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt331() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt332() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt333() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt334() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt335() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt336() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt337() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt338() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt339() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt340() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt341() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt342() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt343() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt344() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt345() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt346() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt347() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt348() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt349() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt350() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt351() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt352() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt353() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt354() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt355() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt356() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt357() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt358() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt359() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt360() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt361() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt362() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, 0);
            assertTrue("Mustn't read more than asked for", read <= 5);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt363() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, 4);
            assertTrue("Mustn't read more than asked for", read <= 5);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt364() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt365() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, 0);
            assertTrue("Mustn't read more than asked for", read <= 5);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt366() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, 4);
            assertTrue("Mustn't read more than asked for", read <= 5);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt367() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt368() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, 0);
            assertTrue("Mustn't read more than asked for", read <= 5);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt369() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, 4);
            assertTrue("Mustn't read more than asked for", read <= 5);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt370() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt371() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt372() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt373() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt374() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt375() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt376() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt377() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt378() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt379() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt380() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt381() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt382() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt383() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt384() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt385() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt386() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt387() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt388() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt389() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt390() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt391() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt392() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt393() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt394() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt395() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt396() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt397() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt398() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt399() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt400() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt401() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt402() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt403() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt404() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt405() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt406() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt407() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt408() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt409() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt410() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt411() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt412() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt413() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt414() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt415() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt416() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt417() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt418() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt419() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt420() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt421() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt422() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt423() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt424() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt425() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt426() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt427() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt428() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt429() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt430() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt431() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt432() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt433() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt434() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt435() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt436() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt437() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt438() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt439() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt440() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt441() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt442() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt443() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt444() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt445() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt446() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt447() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt448() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt449() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt450() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt451() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt452() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt453() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt454() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt455() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt456() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt457() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt458() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt459() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt460() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt461() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt462() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt463() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt464() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt465() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt466() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt467() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt468() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt469() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt470() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt471() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt472() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt473() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt474() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt475() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt476() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt477() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt478() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt479() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt480() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt481() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt482() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt483() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt484() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt485() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt486() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt487() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt488() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt489() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt490() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt491() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt492() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt493() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt494() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt495() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt496() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt497() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt498() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt499() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt500() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt501() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt502() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt503() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt504() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt505() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt506() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt507() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt508() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt509() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt510() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt511() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt512() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt513() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, -1, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt514() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt515() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt516() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt517() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt518() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt519() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt520() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt521() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt522() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt523() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt524() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, 0);
            assertTrue("Mustn't read more than asked for", read <= 5);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt525() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, 4);
            assertTrue("Mustn't read more than asked for", read <= 5);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt526() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt527() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, 0);
            assertTrue("Mustn't read more than asked for", read <= 5);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt528() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, 4);
            assertTrue("Mustn't read more than asked for", read <= 5);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt529() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt530() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, 0);
            assertTrue("Mustn't read more than asked for", read <= 5);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt531() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 5, output, 4);
            assertTrue("Mustn't read more than asked for", read <= 5);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt532() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt533() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt534() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt535() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt536() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt537() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt538() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt539() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt540() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(input, 15, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt541() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt542() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt543() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt544() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt545() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt546() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt547() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt548() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt549() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt550() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt551() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt552() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt553() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt554() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt555() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt556() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt557() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt558() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt559() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt560() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt561() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt562() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt563() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt564() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt565() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt566() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt567() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, -1, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt568() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt569() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt570() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt571() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt572() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt573() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt574() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt575() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt576() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt577() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt578() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt579() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt580() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt581() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt582() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt583() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt584() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt585() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt586() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt587() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt588() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt589() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt590() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt591() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt592() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt593() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt594() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(null, 15, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt595() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt596() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt597() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt598() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt599() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt600() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt601() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt602() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt603() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt604() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt605() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt606() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt607() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt608() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt609() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt610() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt611() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt612() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt613() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt614() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt615() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt616() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt617() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt618() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt619() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt620() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt621() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], -1, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt622() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt623() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt624() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt625() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt626() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt627() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt628() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt629() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt630() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, -1, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt631() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt632() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt633() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt634() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt635() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt636() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt637() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt638() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt639() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 5, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt640() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt641() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt642() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt643() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt644() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt645() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt646() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, -1);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt647() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, 0);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt648() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -59, -52, -68, -73, 74, 123, 51, -49});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
            int read = cipher.update(new byte[0], 15, 8, output, 4);
            fail("must throw RuntimeException");
        } catch (RuntimeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt649() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt650() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt651() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt652() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt653() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt654() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt655() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt656() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt657() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt658() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt659() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt660() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt661() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt662() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt663() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt664() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt665() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt666() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt667() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt668() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt669() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt670() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt671() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt672() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt673() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt674() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt675() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, -1, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt676() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt677() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt678() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt679() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt680() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt681() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt682() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt683() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt684() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt685() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt686() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt687() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt688() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt689() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt690() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt691() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt692() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt693() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt694() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt695() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt696() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt697() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt698() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt699() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt700() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt701() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt702() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(input, 15, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt703() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt704() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt705() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt706() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt707() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt708() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt709() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt710() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt711() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt712() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt713() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt714() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt715() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt716() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt717() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt718() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt719() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt720() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt721() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt722() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt723() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt724() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt725() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt726() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt727() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt728() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt729() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, -1, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt730() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt731() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt732() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt733() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt734() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt735() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt736() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt737() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt738() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt739() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt740() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt741() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt742() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt743() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt744() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt745() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt746() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt747() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt748() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt749() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt750() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt751() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt752() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt753() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt754() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt755() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt756() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(null, 15, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt757() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt758() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt759() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt760() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt761() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt762() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt763() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt764() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt765() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt766() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt767() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt768() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt769() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt770() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt771() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt772() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt773() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt774() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt775() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt776() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt777() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt778() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt779() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt780() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt781() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt782() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt783() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], -1, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt784() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt785() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt786() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt787() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt788() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt789() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt790() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, -1, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt791() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, -1, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt792() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, -1, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt793() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt794() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt795() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt796() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt797() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt798() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt799() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, 5, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt800() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, 5, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt801() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, 5, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt802() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt803() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt804() {
        try {
            byte[] output = null;
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt805() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt806() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt807() {
        try {
            byte[] output = new byte[4];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt808() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, 8, output, -1);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt809() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, 8, output, 0);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteArrayIntIntByteArrayInt810() {
        try {
            byte[] output = new byte[20];
            cipher = Cipher.getInstance(transformation,provider);
            int read = cipher.update(new byte[0], 15, 8, output, 4);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testUpdateByteBufferByteBuffer001() {
        try {
        ByteBuffer output = null;
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(binput, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer002() {
        try {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(binput, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer003() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(binput, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer004() {
        try {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(binput, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer005() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(binput, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer006() {
        try {
        ByteBuffer output = null;
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(null, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer007() {
        try {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(null, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer008() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(null, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer009() {
        try {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(null, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer010() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(null, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer011() {
        try {
        ByteBuffer output = null;
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(ByteBuffer.allocate(0), output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer012() {
        try {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(ByteBuffer.allocate(0), output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer013() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(ByteBuffer.allocate(0), output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer014() {
        try {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(ByteBuffer.allocate(0), output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer015() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(ByteBuffer.allocate(0), output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer016() {
        try {
        ByteBuffer output = null;
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(binput, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer017() {
        try {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(binput, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer018() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(binput, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer019() {
        try {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(binput, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer020() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(binput, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer021() {
        try {
        ByteBuffer output = null;
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(null, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer022() {
        try {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(null, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer023() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(null, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer024() {
        try {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(null, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer025() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(null, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer026() {
        try {
        ByteBuffer output = null;
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(ByteBuffer.allocate(0), output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer027() {
        try {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(ByteBuffer.allocate(0), output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer028() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(ByteBuffer.allocate(0), output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer029() {
        try {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(ByteBuffer.allocate(0), output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer030() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(ByteBuffer.allocate(0), output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer031() {
        try {
        ByteBuffer output = null;
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(binput, output);
        fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer032() {
        try {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(binput, output);
        assertEquals("None have read",0, read);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer033() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(binput, output);
        assertEquals("None have read",0, read);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer034() {
        try {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(binput, output);
        fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer035() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(binput, output);
        fail("must throw ReadOnlyBufferException");
        } catch (ReadOnlyBufferException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer036() {
        try {
        ByteBuffer output = null;
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(null, output);
        fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer037() {
        try {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(null, output);
        fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer038() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(null, output);
        fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer039() {
        try {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(null, output);
        fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer040() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(null, output);
        fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer041() {
        try {
        ByteBuffer output = null;
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(ByteBuffer.allocate(0), output);
        fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer042() {
        try {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(ByteBuffer.allocate(0), output);
        assertEquals("None have read",0, read);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer043() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(ByteBuffer.allocate(0), output);
        assertEquals("None have read",0, read);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer044() {
        try {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(ByteBuffer.allocate(0), output);
        assertEquals("None have read",0, read);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer045() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(ByteBuffer.allocate(0), output);
        fail("must throw ReadOnlyBufferException");
        } catch (ReadOnlyBufferException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer046() {
        try {
        ByteBuffer output = null;
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(binput, output);
        fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer047() {
        try {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(binput, output);
        assertEquals("None have read",0, read);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer048() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(binput, output);
        assertEquals("None have read",0, read);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer049() {
        try {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(binput, output);
        fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer050() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(binput, output);
        fail("must throw ReadOnlyBufferException");
        } catch (ReadOnlyBufferException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer051() {
        try {
        ByteBuffer output = null;
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(null, output);
        fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer052() {
        try {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(null, output);
        fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer053() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(null, output);
        fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer054() {
        try {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(null, output);
        fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer055() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(null, output);
        fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer056() {
        try {
        ByteBuffer output = null;
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(ByteBuffer.allocate(0), output);
        fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer057() {
        try {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(ByteBuffer.allocate(0), output);
        assertEquals("None have read",0, read);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer058() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(ByteBuffer.allocate(0), output);
        assertEquals("None have read",0, read);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer059() {
        try {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(ByteBuffer.allocate(0), output);
        assertEquals("None have read",0, read);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer060() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance(transformation,provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -118, -36, -82, -34, -45, 115, 37, 51});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al);
        int read = cipher.update(ByteBuffer.allocate(0), output);
        fail("must throw ReadOnlyBufferException");
        } catch (ReadOnlyBufferException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer061() {
        try {
        ByteBuffer output = null;
        cipher = Cipher.getInstance(transformation,provider);
        int read = cipher.update(binput, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer062() {
        try {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance(transformation,provider);
        int read = cipher.update(binput, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer063() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance(transformation,provider);
        int read = cipher.update(binput, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer064() {
        try {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance(transformation,provider);
        int read = cipher.update(binput, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer065() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance(transformation,provider);
        int read = cipher.update(binput, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer066() {
        try {
        ByteBuffer output = null;
        cipher = Cipher.getInstance(transformation,provider);
        int read = cipher.update(null, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer067() {
        try {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance(transformation,provider);
        int read = cipher.update(null, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer068() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance(transformation,provider);
        int read = cipher.update(null, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer069() {
        try {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance(transformation,provider);
        int read = cipher.update(null, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer070() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance(transformation,provider);
        int read = cipher.update(null, output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer071() {
        try {
        ByteBuffer output = null;
        cipher = Cipher.getInstance(transformation,provider);
        int read = cipher.update(ByteBuffer.allocate(0), output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer072() {
        try {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance(transformation,provider);
        int read = cipher.update(ByteBuffer.allocate(0), output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer073() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance(transformation,provider);
        int read = cipher.update(ByteBuffer.allocate(0), output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer074() {
        try {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance(transformation,provider);
        int read = cipher.update(ByteBuffer.allocate(0), output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testUpdateByteBufferByteBuffer075() {
        try {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance(transformation,provider);
        int read = cipher.update(ByteBuffer.allocate(0), output);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }




}
