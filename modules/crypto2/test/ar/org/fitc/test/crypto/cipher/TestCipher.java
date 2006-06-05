package ar.org.fitc.test.crypto.cipher;

import java.nio.ByteBuffer;
import java.security.Key;
import java.security.Provider;
import java.security.Security;

import javax.crypto.Cipher;

import junit.framework.TestCase;
import ar.org.fitc.test.util.Messages;

public class TestCipher extends TestCase implements Messages {
    
      
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
        junit.textui.TestRunner.run(TestCipher.class);
    }
    
    public TestCipher(String arg0) {
        super(arg0);
        
    }
    
    public TestCipher() {
        super();
    }
       
    protected void setUp() throws Exception {
        super.setUp();
//        System.out.println(transformation);
        //algorithm = "DES";
        //mode = "CBC";
        //padding = "NoPadding";
        //providerStrKeying = "BC";
        //providerString = "BC";

        /*
         cipher = Cipher.getInstance(transformation);
         key = Util.getInstanceKey(algorithm);
         
         */
        transformation = algorithm + "/" + mode + "/" + padding;
        provider = Security.getProvider(providerString);
        //key = Util.getInstanceKey(algorithm);

    }
    
    protected void tearDown() throws Exception {
        super.tearDown();
        System.gc();
    }
}
