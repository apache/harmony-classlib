package ar.org.fitc.test.crypto.cipher;

import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;

import junit.framework.TestCase;

@SuppressWarnings("unused")
public class TestCipherDoFinal extends TestCase {
    
    public class K 
    implements Key, PublicKey
    {
        static final long serialVersionUID = 1L;
        private final byte[] keyBytes;
        private final String alg;
        
        public K(String alg, byte[] keyBytes) 
        {
            this.alg      = alg;
            this.keyBytes = keyBytes;
        }
        
        public String getAlgorithm() { return alg;  }
        public String getFormat()    { return "RAW"; }
        public byte[] getEncoded()   { return (byte[])keyBytes.clone(); }
    }
    
    protected Cipher cipher = null;
    protected Provider provider = null;
    
    public static String providerString = "BC";
    public static String algorithm = "DES";
    public static String mode = "CBC";
    public static String padding = "NoPadding";
    protected String transformation = algorithm + "/" + mode + "/" + padding;
    public static Key key = null;
    public byte[] input = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
    public ByteBuffer binput = ByteBuffer.allocate(20).put(input);
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestCipherDoFinal.class);
    }
    
    public TestCipherDoFinal(String name) {
        super(name);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        transformation = algorithm + "/" + mode + "/" + padding;
        provider = Security.getProvider(providerString);
    }
    
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    /*
     * Test method for 'javax.crypto.Cipher.doFinal()'
     */
    public final void testdoFinal001() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        output = cipher.doFinal();
        assertNotNull(output);
        assertTrue(output.length >0);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinal002() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, 16, 16, -34, 126, 122, -10, 105, -121});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        output = cipher.doFinal();
        fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinal003() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, 16, 16, -34, 126, 122, -10, 105, -121});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        output = cipher.doFinal();
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinal004() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, 16, 16, -34, 126, 122, -10, 105, -121});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        output = cipher.doFinal();
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinal005() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        try {
        output = cipher.doFinal();
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinal006() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, 16, 16, -34, 126, 122, -10, 105, -121});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        output = cipher.doFinal();
        assertNotNull(output);
        assertTrue(output.length >0);
        
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinal007() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, 16, 16, -34, 126, 122, -10, 105, -121});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        output = cipher.doFinal();
        fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinal008() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, 16, 16, -34, 126, 122, -10, 105, -121});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        output = cipher.doFinal();
        fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinal009() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, 16, 16, -34, 126, 122, -10, 105, -121});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        output = cipher.doFinal();
        fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinal010() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, 16, 16, -34, 126, 122, -10, 105, -121});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        output = cipher.doFinal();
        assertNotNull(output);
        assertTrue(output.length >0);
        
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinal011() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, 16, 16, -34, 126, 122, -10, 105, -121});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        output = cipher.doFinal();
        fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinal012() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, 16, 16, -34, 126, 122, -10, 105, -121});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        output = cipher.doFinal();
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinal013() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, 16, 16, -34, 126, 122, -10, 105, -121});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        output = cipher.doFinal();
        assertNotNull(output);
        assertTrue(output.length ==0);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }

   
    /*
     * Test method for 'javax.crypto.Cipher.doFinal(byte[], int)'
     */
    public final void testdoFinalByteArrayInt001() throws Exception {
        byte[] output = new byte[20];
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        int read = cipher.doFinal(output, 0);
       
        assertEquals("This is the result expected", 66, output[0]);
        assertEquals("This is the result expected", -125, output[1]);
        assertEquals("This is the result expected", 52, output[2]);
        assertEquals("This is the result expected", -78, output[3]);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt002() throws Exception {
        byte[] output = new byte[20];
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        int read = cipher.doFinal(output, 0);
        fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt003() throws Exception {
        byte[] output = new byte[20];
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        int read = cipher.doFinal(output, 0);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt004() throws Exception {
        byte[] output = new byte[20];
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        int read = cipher.doFinal(output, 0);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt005() throws Exception {
        byte[] output = new byte[20];
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        try {
        int read = cipher.doFinal(output, 0);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt006() throws Exception {
        byte[] output = new byte[20];
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        int read = cipher.doFinal(output, 0);
        assertEquals("This is the result expected", 66, output[0]);
        assertEquals("This is the result expected", -125, output[1]);
        assertEquals("This is the result expected", 52, output[2]);
        assertEquals("This is the result expected", -78, output[3]);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt007() throws Exception {
        byte[] output = new byte[20];
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        int read = cipher.doFinal(output, -1);
        fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt008() throws Exception {
        byte[] output = new byte[20];
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        int read = cipher.doFinal(output, 4);
        assertEquals("This is the result expected", 66, output[0+4]);
        assertEquals("This is the result expected", -125, output[1+4]);
        assertEquals("This is the result expected", 52, output[2+4]);
        assertEquals("This is the result expected", -78, output[3+4]);
        
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt009() throws Exception {
        byte[] output = new byte[20];
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        int read = cipher.doFinal(output, 0);
        assertEquals("This is the result expected", 66, output[0]);
        assertEquals("This is the result expected", -125, output[1]);
        assertEquals("This is the result expected", 52, output[2]);
        assertEquals("This is the result expected", -78, output[3]);
        
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt010() throws Exception {
        byte[] output = new byte[4];
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        int read = cipher.doFinal(output, 0);
        fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt011() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        int read = cipher.doFinal(output, 0);
        fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt012() throws Exception {
        byte[] output = input;
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        int read = cipher.doFinal(output, 0);
        
        assertEquals("This is the result expected", 66, output[0]);
        assertEquals("This is the result expected", -125, output[1]);
        assertEquals("This is the result expected", 52, output[2]);
        assertEquals("This is the result expected", -78, output[3]);
        
        assertEquals("This is the result expected", -28, output[7]);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt013() throws Exception {
        byte[] output = new byte[20];
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        int read = cipher.doFinal(output, 0);
        fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt014() throws Exception {
        byte[] output = new byte[4];
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        int read = cipher.doFinal(output, 0);
        fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt015() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        int read = cipher.doFinal(output, 0);
        fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt016() throws Exception {
        byte[] output = input;
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        int read = cipher.doFinal(output, 0);
        fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt017() throws Exception {
        byte[] output = new byte[20];
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        int read = cipher.doFinal(output, 0);
        fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt018() throws Exception {
        byte[] output = new byte[4];
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        int read = cipher.doFinal(output, 0);
        fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt019() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        int read = cipher.doFinal(output, 0);
        fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt020() throws Exception {
        byte[] output = input;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        int read = cipher.doFinal(output, 0);
        fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt021() throws Exception {
        byte[] output = new byte[20];
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        int read = cipher.doFinal(output, 0);
        fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt022() throws Exception {
        byte[] output = new byte[4];
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        int read = cipher.doFinal(output, 0);
        fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt023() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        int read = cipher.doFinal(output, 0);
        fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt024() throws Exception {
        byte[] output = input;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        int read = cipher.doFinal(output, 0);
        fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt025() throws Exception {
        byte[] output = new byte[20];
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        int read = cipher.doFinal(output, 0);
        System.out.println();
        assertEquals("This is the result expected", -2, output[7]);

        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt026() throws Exception {
        byte[] output = new byte[4];
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        int read = cipher.doFinal(output, 0);
        fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt027() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        int read = cipher.doFinal(output, 0);
        fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt028() throws Exception {
        byte[] output = input;
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        int read = cipher.doFinal(output, 0);
        assertEquals("This is the result expected", -2, output[7]);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt029() throws Exception {
        byte[] output = new byte[20];
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        int read = cipher.doFinal(output, 0);
        fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt030() throws Exception {
        byte[] output = new byte[4];
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        int read = cipher.doFinal(output, 0);
        fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt031() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        int read = cipher.doFinal(output, 0);
        fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt032() throws Exception {
        byte[] output = input;
        cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        int read = cipher.doFinal(output, 0);
        fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt033() throws Exception {
        byte[] output = new byte[20];
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        int read = cipher.doFinal(output, 0);
        assertEquals("None have read",0, read);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt034() throws Exception {
        byte[] output = new byte[4];
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        int read = cipher.doFinal(output, 0);
        assertEquals("None have read",0, read);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt035() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        int read = cipher.doFinal(output, 0);
        fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt036() throws Exception {
        byte[] output = input;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        int read = cipher.doFinal(output, 0);
        assertEquals("None have read",0, read);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt037() throws Exception {
        byte[] output = new byte[20];
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        int read = cipher.doFinal(output, 0);
        assertEquals("None have read",0, read);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt038() throws Exception {
        byte[] output = new byte[4];
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        int read = cipher.doFinal(output, 0);
        assertEquals("None have read",0, read);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt039() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        int read = cipher.doFinal(output, 0);
        fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArrayInt040() throws Exception {
        byte[] output = input;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -13, -102, 77, -71, 2, -1, -19, 125});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        int read = cipher.doFinal(output, 0);
        assertEquals("None have read",0, read);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
    /*
     * Test method for 'javax.crypto.Cipher.doFinal(byte[])'
     */
    public final void testdoFinalByteArray001() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -104, -6, 81, -2, 28, -66, -15, -67});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        output = cipher.doFinal(input);
        assertEquals("This is the result expected", -116, output[0]);
        assertEquals("This is the result expected", 76, output[1]);
        assertEquals("This is the result expected", 3, output[2]);
        assertEquals("This is the result expected", 25, output[3]);
        assertEquals("This is the result expected", -40, output[4]);
        assertEquals("This is the result expected", 55, output[5]);
        assertEquals("This is the result expected", 111, output[6]);
        assertEquals("This is the result expected", -20, output[7]);
        assertEquals("This is the result expected", -95, output[8]);
        assertEquals("This is the result expected", 121, output[9]);
        assertEquals("This is the result expected", -111, output[10]);
        assertEquals("This is the result expected", 45, output[11]);
        assertEquals("This is the result expected", 91, output[12]);
        assertEquals("This is the result expected", 38, output[13]);
        assertEquals("This is the result expected", -30, output[14]);
        assertEquals("This is the result expected", -125, output[15]);
        assertEquals("This is the result expected", -55, output[16]);
        assertEquals("This is the result expected", 80, output[17]);
        assertEquals("This is the result expected", -121, output[18]);
        assertEquals("This is the result expected", 21, output[19]);
        assertEquals("This is the result expected", -91, output[20]);
        assertEquals("This is the result expected", 46, output[21]);
        assertEquals("This is the result expected", -112, output[22]);
        assertEquals("This is the result expected", -22, output[23]);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArray002() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -104, -6, 81, -2, 28, -66, -15, -67});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        output = cipher.doFinal(input);
        assertEquals("This is the result expected", -94, output[0]);
        assertEquals("This is the result expected", 92, output[1]);
        assertEquals("This is the result expected", 92, output[2]);
        assertEquals("This is the result expected", 94, output[3]);
        assertEquals("This is the result expected", -49, output[4]);
        assertEquals("This is the result expected", -74, output[5]);
        assertEquals("This is the result expected", 65, output[6]);
        assertEquals("This is the result expected", 54, output[7]);
        assertEquals("This is the result expected", -89, output[8]);
        assertEquals("This is the result expected", -116, output[9]);
        assertEquals("This is the result expected", -95, output[10]);
        assertEquals("This is the result expected", -45, output[11]);
        assertEquals("This is the result expected", 81, output[12]);
        assertEquals("This is the result expected", 34, output[13]);
        assertEquals("This is the result expected", -92, output[14]);
        assertEquals("This is the result expected", 83, output[15]);
        assertEquals("This is the result expected", 74, output[16]);
        assertEquals("This is the result expected", -59, output[17]);
        assertEquals("This is the result expected", -98, output[18]);
        assertEquals("This is the result expected", -52, output[19]);
        assertEquals("This is the result expected", -34, output[20]);
        assertEquals("This is the result expected", -12, output[21]);
        assertEquals("This is the result expected", -66, output[22]);
        assertEquals("This is the result expected", 94, output[23]);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArray003() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -104, -6, 81, -2, 28, -66, -15, -67});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        output = cipher.doFinal(input);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArray004() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -104, -6, 81, -2, 28, -66, -15, -67});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        output = cipher.doFinal(input);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArray005() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
        output = cipher.doFinal(input);
        fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArray006() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -104, -6, 81, -2, 28, -66, -15, -67});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        output = cipher.doFinal(input);
        assertEquals("This is the result expected", -116, output[0]);
        assertEquals("This is the result expected", 76, output[1]);
        assertEquals("This is the result expected", 3, output[2]);
        assertEquals("This is the result expected", 25, output[3]);
        assertEquals("This is the result expected", -40, output[4]);
        assertEquals("This is the result expected", 55, output[5]);
        assertEquals("This is the result expected", 111, output[6]);
        assertEquals("This is the result expected", -20, output[7]);
        assertEquals("This is the result expected", -95, output[8]);
        assertEquals("This is the result expected", 121, output[9]);
        assertEquals("This is the result expected", -111, output[10]);
        assertEquals("This is the result expected", 45, output[11]);
        assertEquals("This is the result expected", 91, output[12]);
        assertEquals("This is the result expected", 38, output[13]);
        assertEquals("This is the result expected", -30, output[14]);
        assertEquals("This is the result expected", -125, output[15]);
        assertEquals("This is the result expected", -55, output[16]);
        assertEquals("This is the result expected", 80, output[17]);
        assertEquals("This is the result expected", -121, output[18]);
        assertEquals("This is the result expected", 21, output[19]);
        assertEquals("This is the result expected", -91, output[20]);
        assertEquals("This is the result expected", 46, output[21]);
        assertEquals("This is the result expected", -112, output[22]);
        assertEquals("This is the result expected", -22, output[23]);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArray007() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -104, -6, 81, -2, 28, -66, -15, -67});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        output = cipher.doFinal(null);
        fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArray008() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -104, -6, 81, -2, 28, -66, -15, -67});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        output = cipher.doFinal(new byte[0]);
        fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArray009() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -104, -6, 81, -2, 28, -66, -15, -67});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        output = cipher.doFinal(input);
        assertEquals("This is the result expected", -116, output[0]);
        assertEquals("This is the result expected", 76, output[1]);
        assertEquals("This is the result expected", 3, output[2]);
        assertEquals("This is the result expected", 25, output[3]);
        assertEquals("This is the result expected", -40, output[4]);
        assertEquals("This is the result expected", 55, output[5]);
        assertEquals("This is the result expected", 111, output[6]);
        assertEquals("This is the result expected", -20, output[7]);
        assertEquals("This is the result expected", -95, output[8]);
        assertEquals("This is the result expected", 121, output[9]);
        assertEquals("This is the result expected", -111, output[10]);
        assertEquals("This is the result expected", 45, output[11]);
        assertEquals("This is the result expected", 91, output[12]);
        assertEquals("This is the result expected", 38, output[13]);
        assertEquals("This is the result expected", -30, output[14]);
        assertEquals("This is the result expected", -125, output[15]);
        assertEquals("This is the result expected", -55, output[16]);
        assertEquals("This is the result expected", 80, output[17]);
        assertEquals("This is the result expected", -121, output[18]);
        assertEquals("This is the result expected", 21, output[19]);
        assertEquals("This is the result expected", -91, output[20]);
        assertEquals("This is the result expected", 46, output[21]);
        assertEquals("This is the result expected", -112, output[22]);
        assertEquals("This is the result expected", -22, output[23]);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArray010() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -104, -6, 81, -2, 28, -66, -15, -67});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        output = cipher.doFinal(input);
        assertEquals("This is the result expected", -94, output[0]);
        assertEquals("This is the result expected", 92, output[1]);
        assertEquals("This is the result expected", 92, output[2]);
        assertEquals("This is the result expected", 94, output[3]);
        assertEquals("This is the result expected", -49, output[4]);
        assertEquals("This is the result expected", -74, output[5]);
        assertEquals("This is the result expected", 65, output[6]);
        assertEquals("This is the result expected", 54, output[7]);
        assertEquals("This is the result expected", -89, output[8]);
        assertEquals("This is the result expected", -116, output[9]);
        assertEquals("This is the result expected", -95, output[10]);
        assertEquals("This is the result expected", -45, output[11]);
        assertEquals("This is the result expected", 81, output[12]);
        assertEquals("This is the result expected", 34, output[13]);
        assertEquals("This is the result expected", -92, output[14]);
        assertEquals("This is the result expected", 83, output[15]);
        assertEquals("This is the result expected", 74, output[16]);
        assertEquals("This is the result expected", -59, output[17]);
        assertEquals("This is the result expected", -98, output[18]);
        assertEquals("This is the result expected", -52, output[19]);
        assertEquals("This is the result expected", -34, output[20]);
        assertEquals("This is the result expected", -12, output[21]);
        assertEquals("This is the result expected", -66, output[22]);
        assertEquals("This is the result expected", 94, output[23]);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArray011() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -104, -6, 81, -2, 28, -66, -15, -67});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        output = cipher.doFinal(input);
        assertEquals("This is the result expected", -116, output[0]);
        assertEquals("This is the result expected", 76, output[1]);
        assertEquals("This is the result expected", 3, output[2]);
        assertEquals("This is the result expected", 25, output[3]);
        assertEquals("This is the result expected", -40, output[4]);
        assertEquals("This is the result expected", 55, output[5]);
        assertEquals("This is the result expected", 111, output[6]);
        assertEquals("This is the result expected", -20, output[7]);
        assertEquals("This is the result expected", -95, output[8]);
        assertEquals("This is the result expected", 121, output[9]);
        assertEquals("This is the result expected", -111, output[10]);
        assertEquals("This is the result expected", 45, output[11]);
        assertEquals("This is the result expected", 91, output[12]);
        assertEquals("This is the result expected", 38, output[13]);
        assertEquals("This is the result expected", -30, output[14]);
        assertEquals("This is the result expected", -125, output[15]);
        assertEquals("This is the result expected", -55, output[16]);
        assertEquals("This is the result expected", 80, output[17]);
        assertEquals("This is the result expected", -121, output[18]);
        assertEquals("This is the result expected", 21, output[19]);
        assertEquals("This is the result expected", -91, output[20]);
        assertEquals("This is the result expected", 46, output[21]);
        assertEquals("This is the result expected", -112, output[22]);
        assertEquals("This is the result expected", -22, output[23]);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArray012() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -104, -6, 81, -2, 28, -66, -15, -67});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
        output = cipher.doFinal(input);
        assertEquals("This is the result expected", -94, output[0]);
        assertEquals("This is the result expected", 92, output[1]);
        assertEquals("This is the result expected", 92, output[2]);
        assertEquals("This is the result expected", 94, output[3]);
        assertEquals("This is the result expected", -49, output[4]);
        assertEquals("This is the result expected", -74, output[5]);
        assertEquals("This is the result expected", 65, output[6]);
        assertEquals("This is the result expected", 54, output[7]);
        assertEquals("This is the result expected", -89, output[8]);
        assertEquals("This is the result expected", -116, output[9]);
        assertEquals("This is the result expected", -95, output[10]);
        assertEquals("This is the result expected", -45, output[11]);
        assertEquals("This is the result expected", 81, output[12]);
        assertEquals("This is the result expected", 34, output[13]);
        assertEquals("This is the result expected", -92, output[14]);
        assertEquals("This is the result expected", 83, output[15]);
        assertEquals("This is the result expected", 74, output[16]);
        assertEquals("This is the result expected", -59, output[17]);
        assertEquals("This is the result expected", -98, output[18]);
        assertEquals("This is the result expected", -52, output[19]);
        assertEquals("This is the result expected", -34, output[20]);
        assertEquals("This is the result expected", -12, output[21]);
        assertEquals("This is the result expected", -66, output[22]);
        assertEquals("This is the result expected", 94, output[23]);
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArray013() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -104, -6, 81, -2, 28, -66, -15, -67});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        output = cipher.doFinal(input);
        fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArray014() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -104, -6, 81, -2, 28, -66, -15, -67});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        output = cipher.doFinal(input);
        fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArray015() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -104, -6, 81, -2, 28, -66, -15, -67});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        output = cipher.doFinal(input);
        fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }
        public final void testdoFinalByteArray016() throws Exception {
        byte[] output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -104, -6, 81, -2, 28, -66, -15, -67});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
        output = cipher.doFinal(input);
        fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
         fail("fail with: " + e);
        }
        }

    /*
     * Test method for 'javax.crypto.Cipher.doFinal(byte[], int, int)'
     */
        public final void testdoFinalByteArrayIntInt001() throws Exception {
            byte[] output = null;
            cipher = Cipher.getInstance("DES",provider);
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8})); 
            cipher.update(input);
            try {
            output = cipher.doFinal(input, 3, 14);
            assertEquals("This is the result expected", -26, output[0]);
            assertEquals("This is the result expected", -26, output[1]);
            assertEquals("This is the result expected", 19, output[2]);
            assertEquals("This is the result expected", 48, output[3]);
            assertEquals("This is the result expected", -58, output[4]);
            assertEquals("This is the result expected", 97, output[5]);
            assertEquals("This is the result expected", 8, output[6]);
            assertEquals("This is the result expected", -2, output[7]);
            assertEquals("This is the result expected", -97, output[8]);
            assertEquals("This is the result expected", 47, output[9]);
            assertEquals("This is the result expected", 63, output[10]);
            assertEquals("This is the result expected", -28, output[11]);
            assertEquals("This is the result expected", 16, output[12]);
            assertEquals("This is the result expected", -111, output[13]);
            assertEquals("This is the result expected", -2, output[14]);
            assertEquals("This is the result expected", -55, output[15]);
            assertEquals("This is the result expected", 91, output[16]);
            assertEquals("This is the result expected", 30, output[17]);
            assertEquals("This is the result expected", 4, output[18]);
            assertEquals("This is the result expected", -22, output[19]);
            assertEquals("This is the result expected", 88, output[20]);
            assertEquals("This is the result expected", -111, output[21]);
            assertEquals("This is the result expected", -2, output[22]);
            assertEquals("This is the result expected", 72, output[23]);
            } catch (Throwable e) {
             fail("fail with: " + e);
            }
            }
            public final void testdoFinalByteArrayIntInt002() throws Exception {
            byte[] output = null;
            cipher = Cipher.getInstance("DES",provider);
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8})); 
            cipher.update(input);
            try {
            output = cipher.doFinal(input, 3, 14);
            fail("must throw IllegalBlockSizeException");
            } catch (IllegalBlockSizeException e) {
            } catch (Throwable e) {
             fail("fail with: " + e);
            }
            }
            public final void testdoFinalByteArrayIntInt003() throws Exception {
            byte[] output = null;
            cipher = Cipher.getInstance("DES",provider);
            cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8})); 
            try {
            output = cipher.doFinal(input, 3, 14);
            fail("must throw IllegalStateException");
            } catch (IllegalStateException e) {
            } catch (Throwable e) {
             fail("fail with: " + e);
            }
            }
            public final void testdoFinalByteArrayIntInt004() throws Exception {
            byte[] output = null;
            cipher = Cipher.getInstance("DES",provider);
            cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8})); 
            try {
            output = cipher.doFinal(input, 3, 14);
            fail("must throw IllegalStateException");
            } catch (IllegalStateException e) {
            } catch (Throwable e) {
             fail("fail with: " + e);
            }
            }
            public final void testdoFinalByteArrayIntInt005() throws Exception {
            byte[] output = null;
            cipher = Cipher.getInstance("DES",provider);
            try {
            output = cipher.doFinal(input, 3, 14);
            fail("must throw IllegalStateException");
            } catch (IllegalStateException e) {
            } catch (Throwable e) {
             fail("fail with: " + e);
            }
            }
            public final void testdoFinalByteArrayIntInt006() throws Exception {
            byte[] output = null;
            cipher = Cipher.getInstance("DES",provider);
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8})); 
            cipher.update(input);
            try {
            output = cipher.doFinal(input, 3, 14);
            assertEquals("This is the result expected", -26, output[0]);
            assertEquals("This is the result expected", -26, output[1]);
            assertEquals("This is the result expected", 19, output[2]);
            assertEquals("This is the result expected", 48, output[3]);
            assertEquals("This is the result expected", -58, output[4]);
            assertEquals("This is the result expected", 97, output[5]);
            assertEquals("This is the result expected", 8, output[6]);
            assertEquals("This is the result expected", -2, output[7]);
            assertEquals("This is the result expected", -97, output[8]);
            assertEquals("This is the result expected", 47, output[9]);
            assertEquals("This is the result expected", 63, output[10]);
            assertEquals("This is the result expected", -28, output[11]);
            assertEquals("This is the result expected", 16, output[12]);
            assertEquals("This is the result expected", -111, output[13]);
            assertEquals("This is the result expected", -2, output[14]);
            assertEquals("This is the result expected", -55, output[15]);
            assertEquals("This is the result expected", 91, output[16]);
            assertEquals("This is the result expected", 30, output[17]);
            assertEquals("This is the result expected", 4, output[18]);
            assertEquals("This is the result expected", -22, output[19]);
            assertEquals("This is the result expected", 88, output[20]);
            assertEquals("This is the result expected", -111, output[21]);
            assertEquals("This is the result expected", -2, output[22]);
            assertEquals("This is the result expected", 72, output[23]);
            } catch (Throwable e) {
             fail("fail with: " + e);
            }
            }
            public final void testdoFinalByteArrayIntInt007() throws Exception {
            byte[] output = null;
            cipher = Cipher.getInstance("DES",provider);
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8})); 
            cipher.update(input);
            try {
            output = cipher.doFinal(input, -1, 14);
            fail("must throw IllegalArgumentException");
            } catch (IllegalArgumentException e) {
            } catch (Throwable e) {
             fail("fail with: " + e);
            }
            }
            public final void testdoFinalByteArrayIntInt008() throws Exception {
            byte[] output = null;
            cipher = Cipher.getInstance("DES",provider);
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8})); 
            cipher.update(input);
            try {
            output = cipher.doFinal(input, 3, 14);
            assertEquals("This is the result expected", -26, output[0]);
            assertEquals("This is the result expected", -26, output[1]);
            assertEquals("This is the result expected", 19, output[2]);
            assertEquals("This is the result expected", 48, output[3]);
            assertEquals("This is the result expected", -58, output[4]);
            assertEquals("This is the result expected", 97, output[5]);
            assertEquals("This is the result expected", 8, output[6]);
            assertEquals("This is the result expected", -2, output[7]);
            assertEquals("This is the result expected", -97, output[8]);
            assertEquals("This is the result expected", 47, output[9]);
            assertEquals("This is the result expected", 63, output[10]);
            assertEquals("This is the result expected", -28, output[11]);
            assertEquals("This is the result expected", 16, output[12]);
            assertEquals("This is the result expected", -111, output[13]);
            assertEquals("This is the result expected", -2, output[14]);
            assertEquals("This is the result expected", -55, output[15]);
            assertEquals("This is the result expected", 91, output[16]);
            assertEquals("This is the result expected", 30, output[17]);
            assertEquals("This is the result expected", 4, output[18]);
            assertEquals("This is the result expected", -22, output[19]);
            assertEquals("This is the result expected", 88, output[20]);
            assertEquals("This is the result expected", -111, output[21]);
            assertEquals("This is the result expected", -2, output[22]);
            assertEquals("This is the result expected", 72, output[23]);
            } catch (Throwable e) {
             fail("fail with: " + e);
            }
            }
            public final void testdoFinalByteArrayIntInt009() throws Exception {
            byte[] output = null;
            cipher = Cipher.getInstance("DES",provider);
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8})); 
            cipher.update(input);
            try {
            output = cipher.doFinal(input, 3, -1);
            fail("must throw IllegalArgumentException");
            } catch (IllegalArgumentException e) {
            } catch (Throwable e) {
             fail("fail with: " + e);
            }
            }
            public final void testdoFinalByteArrayIntInt010() throws Exception {
            byte[] output = null;
            cipher = Cipher.getInstance("DES",provider);
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8})); 
            cipher.update(input);
            try {
            output = cipher.doFinal(input, 3, 0);
            assertEquals("This is the result expected", -73, output[0]);
            assertEquals("This is the result expected", 78, output[1]);
            assertEquals("This is the result expected", -11, output[2]);
            assertEquals("This is the result expected", -108, output[3]);
            assertEquals("This is the result expected", 97, output[4]);
            assertEquals("This is the result expected", 95, output[5]);
            assertEquals("This is the result expected", 92, output[6]);
            assertEquals("This is the result expected", 8, output[7]);
            } catch (Throwable e) {
             fail("fail with: " + e);
            }
            }
            public final void testdoFinalByteArrayIntInt011() throws Exception {
            byte[] output = null;
            cipher = Cipher.getInstance("DES",provider);
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8})); 
            cipher.update(input);
            try {
            output = cipher.doFinal(input, 3, 14);
            assertEquals("This is the result expected", -26, output[0]);
            assertEquals("This is the result expected", -26, output[1]);
            assertEquals("This is the result expected", 19, output[2]);
            assertEquals("This is the result expected", 48, output[3]);
            assertEquals("This is the result expected", -58, output[4]);
            assertEquals("This is the result expected", 97, output[5]);
            assertEquals("This is the result expected", 8, output[6]);
            assertEquals("This is the result expected", -2, output[7]);
            assertEquals("This is the result expected", -97, output[8]);
            assertEquals("This is the result expected", 47, output[9]);
            assertEquals("This is the result expected", 63, output[10]);
            assertEquals("This is the result expected", -28, output[11]);
            assertEquals("This is the result expected", 16, output[12]);
            assertEquals("This is the result expected", -111, output[13]);
            assertEquals("This is the result expected", -2, output[14]);
            assertEquals("This is the result expected", -55, output[15]);
            assertEquals("This is the result expected", 91, output[16]);
            assertEquals("This is the result expected", 30, output[17]);
            assertEquals("This is the result expected", 4, output[18]);
            assertEquals("This is the result expected", -22, output[19]);
            assertEquals("This is the result expected", 88, output[20]);
            assertEquals("This is the result expected", -111, output[21]);
            assertEquals("This is the result expected", -2, output[22]);
            assertEquals("This is the result expected", 72, output[23]);
            } catch (Throwable e) {
             fail("fail with: " + e);
            }
            }
            public final void testdoFinalByteArrayIntInt012() throws Exception {
            byte[] output = null;
            cipher = Cipher.getInstance("DES",provider);
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8})); 
            cipher.update(input);
            try {
            output = cipher.doFinal(null, 3, 14);
            fail("must throw IllegalArgumentException");
            } catch (IllegalArgumentException e) {
            } catch (Throwable e) {
             fail("fail with: " + e);
            }
            }
            public final void testdoFinalByteArrayIntInt013() throws Exception {
            byte[] output = null;
            cipher = Cipher.getInstance("DES",provider);
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8})); 
            cipher.update(input);
            try {
            output = cipher.doFinal(new byte[0], 3, 14);
            fail("must throw IllegalArgumentException");
            } catch (IllegalArgumentException e) {
            } catch (Throwable e) {
             fail("fail with: " + e);
            }
            }
            public final void testdoFinalByteArrayIntInt014() throws Exception {
            byte[] output = null;
            cipher = Cipher.getInstance("DES",provider);
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8})); 
            cipher.update(input);
            try {
            output = cipher.doFinal(input, 3, 14);
            assertEquals("This is the result expected", -26, output[0]);
            assertEquals("This is the result expected", -26, output[1]);
            assertEquals("This is the result expected", 19, output[2]);
            assertEquals("This is the result expected", 48, output[3]);
            assertEquals("This is the result expected", -58, output[4]);
            assertEquals("This is the result expected", 97, output[5]);
            assertEquals("This is the result expected", 8, output[6]);
            assertEquals("This is the result expected", -2, output[7]);
            assertEquals("This is the result expected", -97, output[8]);
            assertEquals("This is the result expected", 47, output[9]);
            assertEquals("This is the result expected", 63, output[10]);
            assertEquals("This is the result expected", -28, output[11]);
            assertEquals("This is the result expected", 16, output[12]);
            assertEquals("This is the result expected", -111, output[13]);
            assertEquals("This is the result expected", -2, output[14]);
            assertEquals("This is the result expected", -55, output[15]);
            assertEquals("This is the result expected", 91, output[16]);
            assertEquals("This is the result expected", 30, output[17]);
            assertEquals("This is the result expected", 4, output[18]);
            assertEquals("This is the result expected", -22, output[19]);
            assertEquals("This is the result expected", 88, output[20]);
            assertEquals("This is the result expected", -111, output[21]);
            assertEquals("This is the result expected", -2, output[22]);
            assertEquals("This is the result expected", 72, output[23]);
            } catch (Throwable e) {
             fail("fail with: " + e);
            }
            }
            public final void testdoFinalByteArrayIntInt015() throws Exception {
            byte[] output = null;
            cipher = Cipher.getInstance("DES",provider);
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8})); 
            cipher.update(input);
            try {
            output = cipher.doFinal(input, 3, 14);
            fail("must throw IllegalBlockSizeException");
            } catch (IllegalBlockSizeException e) {
            } catch (Throwable e) {
             fail("fail with: " + e);
            }
            }
            public final void testdoFinalByteArrayIntInt016() throws Exception {
            byte[] output = null;
            cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
            AlgorithmParameters al = null;
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
            cipher.update(input);
            try {
            output = cipher.doFinal(input, 3, 14);
            fail("must throw IllegalBlockSizeException");
            } catch (IllegalBlockSizeException e) {
            } catch (Throwable e) {
             fail("fail with: " + e);
            }
            }
            public final void testdoFinalByteArrayIntInt017() throws Exception {
            byte[] output = null;
            cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -78, -91, 124, 36, 121, 78, -93, -114});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
            cipher.update(input);
            try {
            output = cipher.doFinal(input, 3, 14);
            fail("must throw IllegalBlockSizeException");
            } catch (IllegalBlockSizeException e) {
            } catch (Throwable e) {
             fail("fail with: " + e);
            }
            }
            public final void testdoFinalByteArrayIntInt018() throws Exception {
            byte[] output = null;
            cipher = Cipher.getInstance("DES",provider);
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8})); 
            try {
            output = cipher.doFinal(input, 3, 14);
            assertEquals("This is the result expected", 2, output[0]);
            assertEquals("This is the result expected", -67, output[1]);
            assertEquals("This is the result expected", 5, output[2]);
            assertEquals("This is the result expected", 5, output[3]);
            assertEquals("This is the result expected", -63, output[4]);
            assertEquals("This is the result expected", 8, output[5]);
            assertEquals("This is the result expected", -33, output[6]);
            assertEquals("This is the result expected", 93, output[7]);
            assertEquals("This is the result expected", 3, output[8]);
            assertEquals("This is the result expected", 95, output[9]);
            assertEquals("This is the result expected", 10, output[10]);
            assertEquals("This is the result expected", 28, output[11]);
            assertEquals("This is the result expected", -58, output[12]);
            assertEquals("This is the result expected", 31, output[13]);
            assertEquals("This is the result expected", -38, output[14]);
            assertEquals("This is the result expected", -120, output[15]);
            } catch (Throwable e) {
             fail("fail with: " + e);
            }
            }
            public final void testdoFinalByteArrayIntInt019() throws Exception {
            byte[] output = null;
            cipher = Cipher.getInstance("DES",provider);
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8})); 
            try {
            output = cipher.doFinal(input, 3, 14);
            fail("must throw IllegalBlockSizeException");
            } catch (IllegalBlockSizeException e) {
            } catch (Throwable e) {
             fail("fail with: " + e);
            }
            }
            public final void testdoFinalByteArrayIntInt020() throws Exception {
            byte[] output = null;
            cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -78, -91, 124, 36, 121, 78, -93, -114});
            cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
            try {
            output = cipher.doFinal(input, 3, 14);
            fail("must throw IllegalBlockSizeException");
            } catch (IllegalBlockSizeException e) {
            } catch (Throwable e) {
             fail("fail with: " + e);
            }
            }
            public final void testdoFinalByteArrayIntInt021() throws Exception {
            byte[] output = null;
            cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
            AlgorithmParameters al = null;
            al = AlgorithmParameters.getInstance(algorithm);
            al.init(new byte[] {4, 8, -78, -91, 124, 36, 121, 78, -93, -114});
            cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
            try {
            output = cipher.doFinal(input, 3, 14);
            fail("must throw IllegalBlockSizeException");
            } catch (IllegalBlockSizeException e) {
            } catch (Throwable e) {
             fail("fail with: " + e);
            }
            }
    
    /*
     * Test method for 'javax.crypto.Cipher.doFinal(byte[], int, int, byte[])'
     */
            public final void testdoFinalByteArrayIntIntByteArray001() throws Exception {
                byte[] output = new byte[20];
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                AlgorithmParameters al = null;
                cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                cipher.update(input);
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw IllegalBlockSizeException");
                } catch (IllegalBlockSizeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray002() throws Exception {
                byte[] output = new byte[20];
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                cipher.update(input);
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw IllegalBlockSizeException");
                } catch (IllegalBlockSizeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray003() throws Exception {
                byte[] output = new byte[20];
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw IllegalStateException");
                } catch (IllegalStateException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray004() throws Exception {
                byte[] output = new byte[20];
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw IllegalStateException");
                } catch (IllegalStateException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray005() throws Exception {
                byte[] output = new byte[20];
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw IllegalStateException");
                } catch (IllegalStateException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray006() throws Exception {
                byte[] output = new byte[20];
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                cipher.update(input);
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw IllegalBlockSizeException");
                } catch (IllegalBlockSizeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray007() throws Exception {
                byte[] output = new byte[20];
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                cipher.update(input);
                try {
                int read = cipher.doFinal(input, -1, 14, output);
                fail("must throw IllegalArgumentException");
                } catch (IllegalArgumentException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray008() throws Exception {
                byte[] output = new byte[20];
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                cipher.update(input);
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw IllegalBlockSizeException");
                } catch (IllegalBlockSizeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray009() throws Exception {
                byte[] output = new byte[20];
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                cipher.update(input);
                try {
                int read = cipher.doFinal(input, 3, -1, output);
                fail("must throw IllegalArgumentException");
                } catch (IllegalArgumentException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray010() throws Exception {
                byte[] output = new byte[20];
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                cipher.update(input);
                try {
                int read = cipher.doFinal(input, 3, 0, output);
                assertEquals("This is the result expected", -127, output[0]);
                assertEquals("This is the result expected", -57, output[1]);
                assertEquals("This is the result expected", -29, output[2]);
                assertEquals("This is the result expected", -61, output[3]);
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray011() throws Exception {
                byte[] output = new byte[20];
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                cipher.update(input);
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw IllegalBlockSizeException");
                } catch (IllegalBlockSizeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray012() throws Exception {
                byte[] output = new byte[20];
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                cipher.update(input);
                try {
                int read = cipher.doFinal(null, 3, 14, output);
                fail("must throw IllegalArgumentException");
                } catch (IllegalArgumentException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray013() throws Exception {
                byte[] output = new byte[20];
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                cipher.update(input);
                try {
                int read = cipher.doFinal(new byte[0], 3, 14, output);
                fail("must throw IllegalArgumentException");
                } catch (IllegalArgumentException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray014() throws Exception {
                byte[] output = new byte[20];
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                cipher.update(input);
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw IllegalBlockSizeException");
                } catch (IllegalBlockSizeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray015() throws Exception {
                byte[] output = new byte[4];
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                cipher.update(input);
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray016() throws Exception {
                byte[] output = null;
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                cipher.update(input);
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray017() throws Exception {
                byte[] output = input;
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                cipher.update(input);
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw IllegalBlockSizeException");
                } catch (IllegalBlockSizeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray018() throws Exception {
                byte[] output = new byte[20];
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                cipher.update(input);
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw IllegalBlockSizeException");
                } catch (IllegalBlockSizeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray019() throws Exception {
                byte[] output = new byte[4];
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                cipher.update(input);
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray020() throws Exception {
                byte[] output = null;
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                cipher.update(input);
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray021() throws Exception {
                byte[] output = input;
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                cipher.update(input);
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw IllegalBlockSizeException");
                } catch (IllegalBlockSizeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray022() throws Exception {
                byte[] output = new byte[20];
                cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                cipher.update(input);
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw IllegalBlockSizeException");
                } catch (IllegalBlockSizeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray023() throws Exception {
                byte[] output = new byte[4];
                cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                cipher.update(input);
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray024() throws Exception {
                byte[] output = null;
                cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                cipher.update(input);
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray025() throws Exception {
                byte[] output = input;
                cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                cipher.update(input);
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw IllegalBlockSizeException");
                } catch (IllegalBlockSizeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray026() throws Exception {
                byte[] output = new byte[20];
                cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                cipher.update(input);
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw IllegalBlockSizeException");
                } catch (IllegalBlockSizeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray027() throws Exception {
                byte[] output = new byte[4];
                cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                cipher.update(input);
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray028() throws Exception {
                byte[] output = null;
                cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                cipher.update(input);
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray029() throws Exception {
                byte[] output = input;
                cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                cipher.update(input);
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw IllegalBlockSizeException");
                } catch (IllegalBlockSizeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray030() throws Exception {
                byte[] output = new byte[20];
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                assertEquals("This is the result expected", -12, output[0]);
                assertEquals("This is the result expected", 30, output[1]);
                assertEquals("This is the result expected", -13, output[2]);
                assertEquals("This is the result expected", 90, output[3]);
                assertEquals("This is the result expected", -14, output[4]);
                assertEquals("This is the result expected", 115, output[5]);
                assertEquals("This is the result expected", -93, output[6]);
                assertEquals("This is the result expected", 125, output[7]);
                assertEquals("This is the result expected", -87, output[8]);
                assertEquals("This is the result expected", 107, output[9]);
                assertEquals("This is the result expected", -74, output[10]);
                assertEquals("This is the result expected", 52, output[11]);
                assertEquals("This is the result expected", -39, output[12]);
                assertEquals("This is the result expected", -32, output[13]);
                
                assertEquals("This is the result expected", 121, output[15]);
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray031() throws Exception {
                byte[] output = new byte[4];
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray032() throws Exception {
                byte[] output = null;
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray033() throws Exception {
                byte[] output = input;
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                assertEquals("This is the result expected", -12, output[0]);
                assertEquals("This is the result expected", 30, output[1]);
                assertEquals("This is the result expected", -13, output[2]);
                assertEquals("This is the result expected", 90, output[3]);
                assertEquals("This is the result expected", -14, output[4]);
                assertEquals("This is the result expected", 115, output[5]);
                assertEquals("This is the result expected", -93, output[6]);
                assertEquals("This is the result expected", 125, output[7]);
                assertEquals("This is the result expected", -87, output[8]);
                assertEquals("This is the result expected", 107, output[9]);
                assertEquals("This is the result expected", -74, output[10]);
                assertEquals("This is the result expected", 52, output[11]);
                assertEquals("This is the result expected", -39, output[12]);
                assertEquals("This is the result expected", -32, output[13]);
                
                assertEquals("This is the result expected", 121, output[15]);
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray034() throws Exception {
                byte[] output = new byte[20];
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw IllegalBlockSizeException");
                } catch (IllegalBlockSizeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray035() throws Exception {
                byte[] output = new byte[4];
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray036() throws Exception {
                byte[] output = null;
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray037() throws Exception {
                byte[] output = input;
                cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw IllegalBlockSizeException");
                } catch (IllegalBlockSizeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray038() throws Exception {
                byte[] output = new byte[20];
                cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw IllegalBlockSizeException");
                } catch (IllegalBlockSizeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray039() throws Exception {
                byte[] output = new byte[4];
                cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray040() throws Exception {
                byte[] output = null;
                cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray041() throws Exception {
                byte[] output = input;
                cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw IllegalBlockSizeException");
                } catch (IllegalBlockSizeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray042() throws Exception {
                byte[] output = new byte[20];
                cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw IllegalBlockSizeException");
                } catch (IllegalBlockSizeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray043() throws Exception {
                byte[] output = new byte[4];
                cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray044() throws Exception {
                byte[] output = null;
                cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw RuntimeException");
                } catch (RuntimeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }
                public final void testdoFinalByteArrayIntIntByteArray045() throws Exception {
                byte[] output = input;
                cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                AlgorithmParameters al = null;
                al = AlgorithmParameters.getInstance(algorithm);
                al.init(new byte[] {4, 8, -38, -43, -108, -102, 45, 6, -75, 126});
                cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                try {
                int read = cipher.doFinal(input, 3, 14, output);
                fail("must throw IllegalBlockSizeException");
                } catch (IllegalBlockSizeException e) {
                } catch (Throwable e) {
                 fail("fail with: " + e);
                }
                }



    /*
     * Test method for 'javax.crypto.Cipher.doFinal(byte[], int, int, byte[], int)'
     */
                public final void testdoFinalByteArrayIntIntByteArrayInt001() throws Exception {
                    byte[] output = new byte[20];
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw IllegalBlockSizeException");
                    } catch (IllegalBlockSizeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt002() throws Exception {
                    byte[] output = new byte[20];
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw IllegalBlockSizeException");
                    } catch (IllegalBlockSizeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt003() throws Exception {
                    byte[] output = new byte[20];
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw IllegalStateException");
                    } catch (IllegalStateException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt004() throws Exception {
                    byte[] output = new byte[20];
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw IllegalStateException");
                    } catch (IllegalStateException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt005() throws Exception {
                    byte[] output = new byte[20];
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw IllegalStateException");
                    } catch (IllegalStateException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt006() throws Exception {
                    byte[] output = new byte[20];
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw IllegalBlockSizeException");
                    } catch (IllegalBlockSizeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt007() throws Exception {
                    byte[] output = new byte[20];
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, -1);
                    fail("must throw IllegalArgumentException");
                    } catch (IllegalArgumentException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt008() throws Exception {
                    byte[] output = new byte[20];
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 4);
                    fail("must throw IllegalBlockSizeException");
                    } catch (IllegalBlockSizeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt009() throws Exception {
                    byte[] output = new byte[20];
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw IllegalBlockSizeException");
                    } catch (IllegalBlockSizeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt010() throws Exception {
                    byte[] output = new byte[20];
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(input, -1, 14, output, 0);
                    fail("must throw IllegalArgumentException");
                    } catch (IllegalArgumentException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt011() throws Exception {
                    byte[] output = new byte[20];
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw IllegalBlockSizeException");
                    } catch (IllegalBlockSizeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt012() throws Exception {
                    byte[] output = new byte[20];
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(input, 3, -1, output, 0);
                    fail("must throw IllegalArgumentException");
                    } catch (IllegalArgumentException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt013() throws Exception {
                    byte[] output = new byte[20];
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(input, 3, 0, output, 0);

                    assertEquals("This is the result expected", 31, output[0]);
                    assertEquals("This is the result expected", -39, output[1]);
                    assertEquals("This is the result expected", -118, output[2]);
                    assertEquals("This is the result expected", -110, output[3]);
                    assertEquals("This is the result expected", 42, output[7]);
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt014() throws Exception {
                    byte[] output = new byte[20];
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw IllegalBlockSizeException");
                    } catch (IllegalBlockSizeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt015() throws Exception {
                    byte[] output = new byte[20];
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(null, 3, 14, output, 0);
                    fail("must throw IllegalArgumentException");
                    } catch (IllegalArgumentException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt016() throws Exception {
                    byte[] output = new byte[20];
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(new byte[0], 3, 14, output, 0);
                    fail("must throw IllegalArgumentException");
                    } catch (IllegalArgumentException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt017() throws Exception {
                    byte[] output = new byte[20];
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw IllegalBlockSizeException");
                    } catch (IllegalBlockSizeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt018() throws Exception {
                    byte[] output = new byte[4];
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw RuntimeException");
                    } catch (RuntimeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt019() throws Exception {
                    byte[] output = null;
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw RuntimeException");
                    } catch (RuntimeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt020() throws Exception {
                    byte[] output = input;
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw IllegalBlockSizeException");
                    } catch (IllegalBlockSizeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt021() throws Exception {
                    byte[] output = new byte[20];
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw IllegalBlockSizeException");
                    } catch (IllegalBlockSizeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt022() throws Exception {
                    byte[] output = new byte[4];
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw RuntimeException");
                    } catch (RuntimeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt023() throws Exception {
                    byte[] output = null;
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw RuntimeException");
                    } catch (RuntimeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt024() throws Exception {
                    byte[] output = input;
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw IllegalBlockSizeException");
                    } catch (IllegalBlockSizeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt025() throws Exception {
                    byte[] output = new byte[20];
                    cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw IllegalBlockSizeException");
                    } catch (IllegalBlockSizeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt026() throws Exception {
                    byte[] output = new byte[4];
                    cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw RuntimeException");
                    } catch (RuntimeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt027() throws Exception {
                    byte[] output = null;
                    cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw RuntimeException");
                    } catch (RuntimeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt028() throws Exception {
                    byte[] output = input;
                    cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw IllegalBlockSizeException");
                    } catch (IllegalBlockSizeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt029() throws Exception {
                    byte[] output = new byte[20];
                    cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw IllegalBlockSizeException");
                    } catch (IllegalBlockSizeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt030() throws Exception {
                    byte[] output = new byte[4];
                    cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw RuntimeException");
                    } catch (RuntimeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt031() throws Exception {
                    byte[] output = null;
                    cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw RuntimeException");
                    } catch (RuntimeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt032() throws Exception {
                    byte[] output = input;
                    cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    cipher.update(input);
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw IllegalBlockSizeException");
                    } catch (IllegalBlockSizeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt033() throws Exception {
                    byte[] output = new byte[20];
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                   
                    assertEquals("This is the result expected", 69, output[0]);
                    assertEquals("This is the result expected", 32, output[1]);
                    assertEquals("This is the result expected", 58, output[2]);
                    assertEquals("This is the result expected", -6, output[3]);
                    assertEquals("This is the result expected", 69, output[4]);
                    assertEquals("This is the result expected", 100, output[5]);
                    assertEquals("This is the result expected", -9, output[6]);
                    assertEquals("This is the result expected", -9, output[7]);
                    assertEquals("This is the result expected", 19, output[8]);
                    assertEquals("This is the result expected", 2, output[9]);
                    assertEquals("This is the result expected", 38, output[10]);
                    assertEquals("This is the result expected", 11, output[11]);
                    assertEquals("This is the result expected", -103, output[12]);
                    assertEquals("This is the result expected", 106, output[13]);
                    
                    assertEquals("This is the result expected", 90, output[15]);
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt034() throws Exception {
                    byte[] output = new byte[4];
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw RuntimeException");
                    } catch (RuntimeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt035() throws Exception {
                    byte[] output = null;
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw RuntimeException");
                    } catch (RuntimeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt036() throws Exception {
                    byte[] output = input;
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    
                    assertEquals("This is the result expected", 69, output[0]);
                    assertEquals("This is the result expected", 32, output[1]);
                    assertEquals("This is the result expected", 58, output[2]);
                    assertEquals("This is the result expected", -6, output[3]);
                    assertEquals("This is the result expected", 69, output[4]);
                    assertEquals("This is the result expected", 100, output[5]);
                    assertEquals("This is the result expected", -9, output[6]);
                    assertEquals("This is the result expected", -9, output[7]);
                    assertEquals("This is the result expected", 19, output[8]);
                    assertEquals("This is the result expected", 2, output[9]);
                    assertEquals("This is the result expected", 38, output[10]);
                    assertEquals("This is the result expected", 11, output[11]);
                    assertEquals("This is the result expected", -103, output[12]);
                    assertEquals("This is the result expected", 106, output[13]);
                    
                    assertEquals("This is the result expected", 90, output[15]); 
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt037() throws Exception {
                    byte[] output = new byte[20];
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw IllegalBlockSizeException");
                    } catch (IllegalBlockSizeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt038() throws Exception {
                    byte[] output = new byte[4];
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw RuntimeException");
                    } catch (RuntimeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt039() throws Exception {
                    byte[] output = null;
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw RuntimeException");
                    } catch (RuntimeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt040() throws Exception {
                    byte[] output = input;
                    cipher = Cipher.getInstance("DES/CFB/ISO10126Padding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw IllegalBlockSizeException");
                    } catch (IllegalBlockSizeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt041() throws Exception {
                    byte[] output = new byte[20];
                    cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw IllegalBlockSizeException");
                    } catch (IllegalBlockSizeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt042() throws Exception {
                    byte[] output = new byte[4];
                    cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw RuntimeException");
                    } catch (RuntimeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt043() throws Exception {
                    byte[] output = null;
                    cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw RuntimeException");
                    } catch (RuntimeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt044() throws Exception {
                    byte[] output = input;
                    cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw IllegalBlockSizeException");
                    } catch (IllegalBlockSizeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt045() throws Exception {
                    byte[] output = new byte[20];
                    cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw IllegalBlockSizeException");
                    } catch (IllegalBlockSizeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt046() throws Exception {
                    byte[] output = new byte[4];
                    cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw RuntimeException");
                    } catch (RuntimeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt047() throws Exception {
                    byte[] output = null;
                    cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw RuntimeException");
                    } catch (RuntimeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }
                    public final void testdoFinalByteArrayIntIntByteArrayInt048() throws Exception {
                    byte[] output = input;
                    cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
                    AlgorithmParameters al = null;
                    al = AlgorithmParameters.getInstance(algorithm);
                    al.init(new byte[] {4, 8, -62, -112, -54, 108, 42, 61, 57, 21});
                    cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
                    try {
                    int read = cipher.doFinal(input, 3, 14, output, 0);
                    fail("must throw IllegalBlockSizeException");
                    } catch (IllegalBlockSizeException e) {
                    } catch (Throwable e) {
                     fail("fail with: " + e);
                    }
                    }


    
    
    /*
     * Test method for 'javax.crypto.Cipher.doFinal(ByteBuffer, ByteBuffer)'
     */
    public final void testdoFinalByteBufferByteBuffer001() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer002() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer003() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer004() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer005() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer006() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer007() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer008() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer009() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer010() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer011() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer012() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer013() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer014() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer015() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer016() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer017() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer018() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer019() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer020() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer021() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer022() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer023() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer024() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer025() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer026() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer027() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer028() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer029() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer030() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer031() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer032() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw ShortBufferException");
        } catch (ShortBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer033() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer034() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer035() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw ReadOnlyBufferException");
        } catch (ReadOnlyBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer036() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer037() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer038() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer039() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer040() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer041() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer042() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw ShortBufferException");
        } catch (ShortBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer043() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer044() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw ShortBufferException");
        } catch (ShortBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer045() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw ReadOnlyBufferException");
        } catch (ReadOnlyBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer046() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer047() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw ShortBufferException");
        } catch (ShortBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer048() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer049() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer050() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw ReadOnlyBufferException");
        } catch (ReadOnlyBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer051() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer052() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer053() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer054() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer055() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer056() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer057() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw ShortBufferException");
        } catch (ShortBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer058() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer059() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw ShortBufferException");
        } catch (ShortBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer060() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw ReadOnlyBufferException");
        } catch (ReadOnlyBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer061() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer062() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer063() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer064() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer065() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer066() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer067() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer068() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer069() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer070() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer071() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer072() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer073() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer074() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer075() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer076() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer077() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer078() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer079() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer080() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer081() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer082() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer083() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer084() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer085() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer086() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer087() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer088() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer089() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer090() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer091() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer092() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer093() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer094() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer095() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer096() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer097() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer098() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer099() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer100() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer101() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer102() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer103() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer104() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer105() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer106() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer107() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw ShortBufferException");
        } catch (ShortBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer108() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer109() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer110() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw ReadOnlyBufferException");
        } catch (ReadOnlyBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer111() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer112() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer113() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer114() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer115() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer116() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer117() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw ShortBufferException");
        } catch (ShortBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer118() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer119() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw ShortBufferException");
        } catch (ShortBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer120() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw ReadOnlyBufferException");
        } catch (ReadOnlyBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer121() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer122() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw ShortBufferException");
        } catch (ShortBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer123() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer124() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer125() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw ReadOnlyBufferException");
        } catch (ReadOnlyBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer126() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer127() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer128() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer129() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer130() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer131() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer132() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw ShortBufferException");
        } catch (ShortBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer133() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalBlockSizeException");
        } catch (IllegalBlockSizeException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer134() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw ShortBufferException");
        } catch (ShortBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer135() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        cipher.update(input);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw ReadOnlyBufferException");
        } catch (ReadOnlyBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer136() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer137() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer138() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer139() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer140() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer141() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer142() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer143() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer144() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer145() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer146() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer147() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer148() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer149() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer150() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer151() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer152() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer153() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer154() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer155() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer156() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer157() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer158() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer159() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer160() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer161() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer162() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer163() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer164() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer165() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer166() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer167() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer168() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer169() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer170() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer171() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer172() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer173() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer174() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer175() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer176() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer177() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer178() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer179() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer180() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer181() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer182() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer183() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer184() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer185() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw ReadOnlyBufferException");
        } catch (ReadOnlyBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer186() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer187() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer188() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer189() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer190() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer191() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer192() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer193() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer194() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer195() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw ReadOnlyBufferException");
        } catch (ReadOnlyBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer196() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer197() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer198() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer199() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer200() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw ReadOnlyBufferException");
        } catch (ReadOnlyBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer201() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer202() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer203() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer204() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer205() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer206() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer207() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer208() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer209() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer210() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw ReadOnlyBufferException");
        } catch (ReadOnlyBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer211() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer212() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer213() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer214() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer215() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer216() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer217() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer218() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer219() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer220() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer221() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer222() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer223() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer224() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer225() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer226() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer227() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer228() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer229() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer230() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer231() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer232() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer233() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer234() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer235() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer236() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer237() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer238() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer239() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer240() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.WRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer241() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer242() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer243() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer244() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer245() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer246() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer247() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer248() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer249() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer250() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer251() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer252() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer253() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer254() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer255() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.UNWRAP_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer256() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer257() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer258() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer259() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer260() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw ReadOnlyBufferException");
        } catch (ReadOnlyBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer261() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer262() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer263() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer264() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer265() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer266() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer267() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer268() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer269() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer270() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.DECRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw ReadOnlyBufferException");
        } catch (ReadOnlyBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer271() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer272() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer273() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer274() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer275() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw ReadOnlyBufferException");
        } catch (ReadOnlyBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer276() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer277() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer278() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer279() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer280() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer281() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer282() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer283() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer284() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            assertEquals("None have read",0, read);
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer285() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        AlgorithmParameters al = null;
        al = AlgorithmParameters.getInstance(algorithm);
        al.init(new byte[] {4, 8, -28, 34, -76, 14, -117, -102, -62, -12});
        cipher.init(Cipher.ENCRYPT_MODE, new K(algorithm, new byte[] {1,2,3,4,5,6,7,8}), al); 
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw ReadOnlyBufferException");
        } catch (ReadOnlyBufferException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer286() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer287() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer288() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer289() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer290() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(binput, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer291() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer292() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer293() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer294() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer295() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(null, output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer296() throws Exception {
        ByteBuffer output = null;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer297() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(4);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer298() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20);
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer299() throws Exception {
        ByteBuffer output = binput;
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    public final void testdoFinalByteBufferByteBuffer300() throws Exception {
        ByteBuffer output = ByteBuffer.allocate(20).asReadOnlyBuffer();
        cipher = Cipher.getInstance("DES/CBC/NoPadding",provider);
        try {
            int read = cipher.doFinal(ByteBuffer.allocate(0), output);
            fail("must throw IllegalStateException");
        } catch (IllegalStateException e) {
        } catch (Throwable e) {
            fail("fail with: " + e);
        }
    }
    
    
    
}
