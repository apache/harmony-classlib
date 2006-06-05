package ar.org.fitc.test.crypto.spec;


import javax.crypto.spec.PBEParameterSpec;

import junit.framework.TestCase;

public class TestPBEParameterSpec extends TestCase {
    PBEParameterSpec keySpec=null;
    int iterationCount= 1;
    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestPBEParameterSpec.class);
    }

    public TestPBEParameterSpec(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'javax.crypto.spec.PBEParameterSpec.PBEParameterSpec(byte[], int)'
     */
    public final void testPBEParameterSpec001() {
        try{
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;            
            keySpec = new PBEParameterSpec(salt, iterationCount);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    public final void testPBEParameterSpec002() {
        try{
            byte[] salt = null;                     
            iterationCount= 240;            
            keySpec = new PBEParameterSpec(salt, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testPBEParameterSpec003() {
        try{
            byte[] salt = new byte[0];                     
            iterationCount= 1;            
            keySpec = new PBEParameterSpec(salt, iterationCount);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testPBEParameterSpec004() {
        try{
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 0;            
            keySpec = new PBEParameterSpec(salt, iterationCount);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    public final void testPBEParameterSpec005() {
        try{
            byte[] salt = null;                     
            iterationCount= 0;            
            keySpec = new PBEParameterSpec(salt, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testPBEParameterSpec006() {
        try{

            byte[] salt = new byte[0];                     
            iterationCount= 0;            
            keySpec = new PBEParameterSpec(salt, iterationCount);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    public final void testPBEParameterSpec007() {
        try{
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 999999999;            
            keySpec = new PBEParameterSpec(salt, iterationCount);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    public final void testPBEParameterSpec008() {
        try{
            byte[] salt = null;                     
            iterationCount= 999999999;            
            keySpec = new PBEParameterSpec(salt, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testPBEParameterSpec009() {
        try{

            byte[] salt = new byte[0];                     
            iterationCount= 999999999;            
            keySpec = new PBEParameterSpec(salt, iterationCount);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testPBEParameterSpec010() {
        try{
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= -999999999;            
            keySpec = new PBEParameterSpec(salt, iterationCount);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    public final void testPBEParameterSpec011() {
        try{
            byte[] salt = null;                     
            iterationCount= -999999999;            
            keySpec = new PBEParameterSpec(salt, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testPBEParameterSpec012() {
        try{

            byte[] salt = new byte[0];                     
            iterationCount= -999999999;            
            keySpec = new PBEParameterSpec(salt, iterationCount);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    /*
     * Test method for 'javax.crypto.spec.PBEParameterSpec.getSalt()'
     */
    public final void testGetSalt001() {
     try{
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEParameterSpec(salt, iterationCount);
        if (keySpec.getSalt() instanceof byte[]){
            assertTrue(true);
        }
        else{
            assertTrue(false);
        }
    } catch (Throwable e) {
        fail("Should not raise any Exception...");
    }
    }
    
    public final void testGetSalt002() {
        try{   
            
            byte[]salt = new byte[0];                     
            iterationCount= 240;
            keySpec = new PBEParameterSpec(salt, iterationCount);
            if(keySpec.getSalt().length==0){
                assertTrue(true);
                }
            else{assertTrue(false);
                }
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }

    public final void testGetSalt003() {
        try{   
           
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;
            keySpec = new PBEParameterSpec(salt, iterationCount);
            for(int i=0; i<salt.length; i++) {
                assertEquals(keySpec.getSalt()[i], salt[i]);
            }                 
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    

    public final void testGetSalt004() {
        try{
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;
            keySpec = new PBEParameterSpec(salt, iterationCount);
          assertNotSame("must not be same arrays", salt, keySpec.getSalt());
      } catch (Throwable e) {
          fail("Should not raise any Exception...");
      }
  }
    public final void testGetSalt005() {
        try{
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;
            keySpec = new PBEParameterSpec(salt, iterationCount);
          assertFalse("must be new arrays every call", keySpec.getSalt() == keySpec.getSalt());                            
      } catch (Throwable e) {
          fail("Should not raise any Exception..."+ e);
      }
  } 
    
    public final void testGetSalt006() {
        try{
            byte[]salt = new byte[0];                        
            iterationCount= 240;
            keySpec = new PBEParameterSpec(salt, iterationCount);
          assertNotSame("must not be same arrays", salt, keySpec.getSalt());
      } catch (Throwable e) {
          fail("Should not raise any Exception...");
      }
  }
    public final void testGetSalt007() {
        try{
            byte[]salt = new byte[0];                     
            iterationCount= 240;
            keySpec = new PBEParameterSpec(salt, iterationCount);
          assertFalse("must be new arrays every call", keySpec.getSalt() == keySpec.getSalt());                            
      } catch (Throwable e) {
          fail("Should not raise any Exception..."+ e);
      }
  } 
    
    
    /*
     * Test method for 'javax.crypto.spec.PBEParameterSpec.getIterationCount()'
     */
    public final void testGetIterationCount001() {
        try{   
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 999999999;
            keySpec = new PBEParameterSpec(salt, iterationCount);
            assertEquals(iterationCount, keySpec.getIterationCount());
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    public final void testGetIterationCount002() {
        try{   
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 0;
            keySpec = new PBEParameterSpec(salt, iterationCount);
            assertEquals(iterationCount, keySpec.getIterationCount());
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    public final void testGetIterationCount003() {
        try{   
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= -999999999;
            keySpec = new PBEParameterSpec(salt, iterationCount);
            assertEquals(iterationCount, keySpec.getIterationCount());
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
}
