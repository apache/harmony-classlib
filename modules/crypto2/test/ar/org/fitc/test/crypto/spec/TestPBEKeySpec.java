package ar.org.fitc.test.crypto.spec;

import javax.crypto.spec.PBEKeySpec;

import junit.framework.TestCase;

public class TestPBEKeySpec extends TestCase {
    String pwd=null;
    char[] password = null;
    PBEKeySpec keySpec=null;
    byte[] salt = null;                     
    int iterationCount=0; 
    int keyLength=0;
    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestPBEKeySpec.class);
    }
    
    public TestPBEKeySpec(String name) {
        super(name);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    protected void tearDown() throws Exception {
        super.tearDown();
        System.gc();
    }
    
    /*
     * Test method for 'javax.crypto.spec.PBEKeySpec.PBEKeySpec(char[])'
     */
    public final void testPBEKeySpecCharArray001() {
        try{
            char []password = {0xc7};
            keySpec = new PBEKeySpec(password);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    public final void testPBEKeySpecCharArray002() {
        try{
            password=new char[999999];       
            keySpec = new PBEKeySpec(password);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }           
    
    public final void testPBEKeySpecCharArray003() {
        try{
            password=new char[0];
            keySpec = new PBEKeySpec(password);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testPBEKeySpecCharArray004() {
        try{            
            keySpec = new PBEKeySpec(password);
            if(keySpec.getPassword().length==0){
                assertTrue(true);
            }
            else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArray005() {
        try{
            char []password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            keySpec = new PBEKeySpec(password);		
            if (keySpec.getPassword()!=password){
                assertTrue(true);
            }
            else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArray006() {
        try{
            pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
            keySpec = new PBEKeySpec(pwd.toCharArray());
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testPBEKeySpecCharArray007() {
        try{
            char []password = {0xc7};
            keySpec = new PBEKeySpec(password);     
            if (keySpec.getPassword()!=password){
                assertTrue(true);
            }
            else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArray008() {
        try{
            char []password = new char[0];
            keySpec = new PBEKeySpec(password);     
            if (keySpec.getPassword()!=password){
                assertTrue(true);
            }
            else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArray009() {
        try{
            pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
            keySpec = new PBEKeySpec(password);     
            if (keySpec.getPassword()!=pwd.toCharArray()){
                assertTrue(true);
            }
            else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    
    
    
    
    /*
     * Test method for 'javax.crypto.spec.PBEKeySpec.PBEKeySpec(char[], byte[], int, int)'
     */
    
    public final void testPBEKeySpecCharArrayByteArrayInt001() {
        try{
            char []password = {0xc7};
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayInt002() {
        try{
            password=new char[999999];       
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 250;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);
        } catch (Throwable e) {
            fail("Should not raise any Exception..." + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt003() {
        try{
            password=new char[0];
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt004() {
        try{
            
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);
            if(keySpec.getPassword().length==0){
                assertTrue(true);
            }
            else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt005() {
        try{
            char []password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);
            if (keySpec.getPassword()!=password){
                assertTrue(true);
            }
            else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt006() {
        try{
            pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;            
            keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt007() {
        try{
            char []password = {0xc7};
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);    
            if (keySpec.getPassword()!=password){
                assertTrue(true);
            }
            else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt008() {
        try{
            char []password = new char[0];
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;            
            keySpec = new PBEKeySpec(password,salt, iterationCount); 
            if (keySpec.getPassword()!=password){
                assertTrue(true);
            }
            else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt009() {
        try{
            pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;            
            keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount);    
            if (keySpec.getPassword()!=pwd.toCharArray()){
                assertTrue(true);
            }
            else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt010() {
        try{
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;
            keySpec = new PBEKeySpec(password,salt, iterationCount);		
            if (keySpec.getSalt()!=salt){
                assertTrue(true);
            }       
            else {assertTrue(false);}		
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    

    
    public final void testPBEKeySpecCharArrayByteArrayInt011() {
        try{
            char []password = {0xc7};
            byte[] salt = null;                     
            iterationCount= 240;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayInt012() {
        try{
            password=new char[999999];       
            
            iterationCount= 240;            
            keySpec = new PBEKeySpec(password,null, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt013() {
        try{
            password=new char[0];
            
            iterationCount= 240;            
            keySpec = new PBEKeySpec(password,null, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt014() {
        try{
            
            
            iterationCount= 240;            
            keySpec = new PBEKeySpec(password,null, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt015() {
        try{
            pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
            
            iterationCount= 240;            
            keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt016() {
        try{
            char []password = {0xc7};
            byte[] salt = new byte[0];                     
            iterationCount= 240;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayInt017() {
        try{
            password=new char[999999];       
            
            iterationCount= 240;            
            keySpec = new PBEKeySpec(password,new byte[0], iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt018() {
        try{
            password=new char[0];
            
            iterationCount= 240;            
            keySpec = new PBEKeySpec(password,new byte[0], iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt019() {
        try{
            
            
            iterationCount= 240;            
            keySpec = new PBEKeySpec(password,new byte[0], iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt020() {
        try{
            pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
            
            iterationCount= 240;            
            keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt021() {
        try{
            char []password = {0xc7};
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 99999999;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayInt022() {
        try{
            password=new char[99999];       
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 99999999;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);
        } catch (Throwable e) {
            fail("Should not raise any Exception..." + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt023() {
        try{
            password=new char[0];
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 99999999;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt024() {
        try{
            
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 99999999;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);
            if(keySpec.getPassword().length==0){
                assertTrue(true);
            }
            else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt025() {
        try{
            char []password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 99999999;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);
            if (keySpec.getPassword()!=password){
                assertTrue(true);
            }
            else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt026() {
        try{
            pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 99999999;            
            keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt027() {
        try{
            char []password = {0xc7};
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 99999999;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);    
            if (keySpec.getPassword()!=password){
                assertTrue(true);
            }
            else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt028() {
        try{
            char []password = new char[0];
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 99999999;            
            keySpec = new PBEKeySpec(password,salt, iterationCount); 
            if (keySpec.getPassword()!=password){
                assertTrue(true);
            }
            else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt029() {
        try{
            pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 99999999;            
            keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount);    
            if (keySpec.getPassword()!=pwd.toCharArray()){
                assertTrue(true);
            }
            else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt030() {
        try{
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 99999999;
            keySpec = new PBEKeySpec(password,salt, iterationCount);        
            if (keySpec.getSalt()!=salt){
                assertTrue(true);
            }       
            else {assertTrue(false);}       
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt031() {
        try{
            char []password = {0xc7};
            byte[] salt = null;                     
            iterationCount= 99999999;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayInt032() {
        try{
            password=new char[99999];       
            
            iterationCount= 99999999;            
            keySpec = new PBEKeySpec(password,null, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt033() {
        try{
            password=new char[0];
            
            iterationCount= 99999999;            
            keySpec = new PBEKeySpec(password,null, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt034() {
        try{
            
            
            iterationCount= 99999999;            
            keySpec = new PBEKeySpec(password,null, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt035() {
        try{
            pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
            
            iterationCount= 99999999;            
            keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt036() {
        try{
            char []password = {0xc7};
            byte[] salt = new byte[0];                     
            iterationCount= 99999999;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayInt037() {
        try{
            password=new char[99999];       
            
            iterationCount= 99999999;            
            keySpec = new PBEKeySpec(password,new byte[0], iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt038() {
        try{
            password=new char[0];
            
            iterationCount= 99999999;            
            keySpec = new PBEKeySpec(password,new byte[0], iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt039() {
        try{
            
            
            iterationCount= 99999999;            
            keySpec = new PBEKeySpec(password,new byte[0], iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt040() {
        try{
            pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
            
            iterationCount= 99999999;            
            keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt041() {
        try{
            char []password = {0xc7};
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 1;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayInt042() {
        try{
            password=new char[99999];       
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 1;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);
        } catch (Throwable e) {
            fail("Should not raise any Exception..." + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt043() {
        try{
            password=new char[0];
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 1;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt044() {
        try{
            
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 1;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);
            if(keySpec.getPassword().length==0){
                assertTrue(true);
            }
            else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt045() {
        try{
            char []password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 1;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);
            if (keySpec.getPassword()!=password){
                assertTrue(true);
            }
            else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt046() {
        try{
            pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 1;            
            keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt047() {
        try{
            char []password = {0xc7};
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 1;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);    
            if (keySpec.getPassword()!=password){
                assertTrue(true);
            }
            else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt048() {
        try{
            char []password = new char[0];
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 1;            
            keySpec = new PBEKeySpec(password,salt, iterationCount); 
            if (keySpec.getPassword()!=password){
                assertTrue(true);
            }
            else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt049() {
        try{
            pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 1;            
            keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount);    
            if (keySpec.getPassword()!=pwd.toCharArray()){
                assertTrue(true);
            }
            else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt050() {
        try{
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 1;
            keySpec = new PBEKeySpec(password,salt, iterationCount);        
            if (keySpec.getSalt()!=salt){
                assertTrue(true);
            }       
            else {assertTrue(false);}       
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt051() {
        try{
            char []password = {0xc7};
            byte[] salt = null;                     
            iterationCount= 1;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayInt052() {
        try{
            password=new char[99999];       
            
            iterationCount= 1;            
            keySpec = new PBEKeySpec(password,null, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt053() {
        try{
            password=new char[0];
            
            iterationCount= 1;            
            keySpec = new PBEKeySpec(password,null, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt054() {
        try{
            
            
            iterationCount= 1;            
            keySpec = new PBEKeySpec(password,null, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt055() {
        try{
            pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
            
            iterationCount= 1;            
            keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt056() {
        try{
            char []password = {0xc7};
            byte[] salt = new byte[0];                     
            iterationCount= 1;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayInt057() {
        try{
            password=new char[99999];       
            
            iterationCount= 1;            
            keySpec = new PBEKeySpec(password,new byte[0], iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt058() {
        try{
            password=new char[0];
            
            iterationCount= 1;            
            keySpec = new PBEKeySpec(password,new byte[0], iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt059() {
        try{
            
            
            iterationCount= 1;            
            keySpec = new PBEKeySpec(password,new byte[0], iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt060() {
        try{
            pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
            
            iterationCount= 1;            
            keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt061() {
        try{
            char []password = {0xc7};
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 0;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayInt062() {
        try{
            password=new char[99999];       
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 0;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt063() {
        try{
            password=new char[0];
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 0;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt064() {
        try{
            pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 0;            
            keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt065() {
        try{
            char []password = {0xc7};
            byte[] salt = null;                     
            iterationCount= 0;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayInt066() {
        try{
            password=new char[99999];       
            
            iterationCount= 0;            
            keySpec = new PBEKeySpec(password,null, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt067() {
        try{
            password=new char[0];
            
            iterationCount= 0;            
            keySpec = new PBEKeySpec(password,null, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt068() {
        try{
            
            
            iterationCount= 0;            
            keySpec = new PBEKeySpec(password,null, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt069() {
        try{
            pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
            
            iterationCount= 0;            
            keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt070() {
        try{
            char []password = {0xc7};
            byte[] salt = new byte[0];                     
            iterationCount= 0;            
            keySpec = new PBEKeySpec(password,salt, iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayInt071() {
        try{
            password=new char[99999];       
            
            iterationCount= 0;            
            keySpec = new PBEKeySpec(password,new byte[0], iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt072() {
        try{
            password=new char[0];
            
            iterationCount= 0;            
            keySpec = new PBEKeySpec(password,new byte[0], iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt073() {
        try{
            
            
            iterationCount= 0;            
            keySpec = new PBEKeySpec(password,new byte[0], iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt074() {
        try{
            pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
            
            iterationCount= 0;            
            keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt075() {
        try{
            char []password = {0xc7};
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= -1;              
            keySpec = new PBEKeySpec(password,salt, iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayInt076() {
        try{
            password=new char[99999];       
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= -1;              
            keySpec = new PBEKeySpec(password,salt, iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt077() {
        try{
            password=new char[0];
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= -1;              
            keySpec = new PBEKeySpec(password,salt, iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt078() {
        try{
            pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= -1;              
            keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt079() {
        try{
            char []password = {0xc7};
            byte[] salt = null;                     
            iterationCount= -1;              
            keySpec = new PBEKeySpec(password,salt, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayInt080() {
        try{
            password=new char[99999];       
            
            iterationCount= -1;              
            keySpec = new PBEKeySpec(password,null, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt081() {
        try{
            password=new char[0];
            
            iterationCount= -1;              
            keySpec = new PBEKeySpec(password,null, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt082() {
        try{
            
            
            iterationCount= -1;              
            keySpec = new PBEKeySpec(password,null, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt083() {
        try{
            pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
            
            iterationCount= -1;              
            keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt084() {
        try{
            char []password = {0xc7};
            byte[] salt = new byte[0];                     
            iterationCount= -1;              
            keySpec = new PBEKeySpec(password,salt, iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayInt085() {
        try{
            password=new char[99999];       
            
            iterationCount= -1;              
            keySpec = new PBEKeySpec(password,new byte[0], iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt086() {
        try{
            password=new char[0];
            
            iterationCount= -1;              
            keySpec = new PBEKeySpec(password,new byte[0], iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt087() {
        try{
            
            
            iterationCount= -1;              
            keySpec = new PBEKeySpec(password,new byte[0], iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt088() {
        try{
            pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
            
            iterationCount= -1;              
            keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt089() {
        try{
            char []password = {0xc7};
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= -999999999;                
            keySpec = new PBEKeySpec(password,salt, iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayInt090() {
        try{
            password=new char[99999];       
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= -999999999;                
            keySpec = new PBEKeySpec(password,salt, iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt091() {
        try{
            password=new char[0];
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= -999999999;                
            keySpec = new PBEKeySpec(password,salt, iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt092() {
        try{
            pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
            byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= -999999999;                
            keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt093() {
        try{
            char []password = {0xc7};
            byte[] salt = null;                     
            iterationCount= -999999999;                
            keySpec = new PBEKeySpec(password,salt, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayInt094() {
        try{
            password=new char[99999];       
            
            iterationCount= -999999999;                
            keySpec = new PBEKeySpec(password,null, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt095() {
        try{
            password=new char[0];
            
            iterationCount= -999999999;                
            keySpec = new PBEKeySpec(password,null, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt096() {
        try{
            
            
            iterationCount= -999999999;                
            keySpec = new PBEKeySpec(password,null, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt097() {
        try{
            pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
            
            iterationCount= -999999999;                
            keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount);
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt098() {
        try{
            char []password = {0xc7};
            byte[] salt = new byte[0];                     
            iterationCount= -999999999;                
            keySpec = new PBEKeySpec(password,salt, iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayInt099() {
        try{
            password=new char[99999];       
            
            iterationCount= -999999999;                
            keySpec = new PBEKeySpec(password,new byte[0], iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt100() {
        try{
            password=new char[0];
            
            iterationCount= -999999999;                
            keySpec = new PBEKeySpec(password,new byte[0], iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt101() {
        try{
            
            
            iterationCount= -999999999;                
            keySpec = new PBEKeySpec(password,new byte[0], iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayInt102() {
        try{
            pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
            
            iterationCount= -999999999;                
            keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount);
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt001() {
        try{   keyLength=64;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;    
        keyLength=64;
        keySpec = new PBEKeySpec(password,salt, iterationCount,keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt002() {
        try{   keyLength=64;
        password=new char[99999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 250;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception..." + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt003() {
        try{   keyLength=64;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt004() {
        try{   keyLength=64;
        
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        if(keySpec.getPassword().length==0){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt005() {
        try{   keyLength=64;
        char []password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        if (keySpec.getPassword()!=password){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt006() {
        try{   keyLength=64;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt007() {
        try{   keyLength=64;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);    
        if (keySpec.getPassword()!=password){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt008() {
        try{   keyLength=64;
        char []password = new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength); 
        if (keySpec.getPassword()!=password){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt009() {
        try{   keyLength=64;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);    
        if (keySpec.getPassword()!=pwd.toCharArray()){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt010() {
        try{   keyLength=64;
        char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);        
        if (keySpec.getSalt()!=salt){
            assertTrue(true);
        }       
        else {assertTrue(false);}       
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt011() {
        try{   keyLength=64;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt012() {
        try{   keyLength=64;
        password=new char[99999];       
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt013() {
        try{   keyLength=64;
        password=new char[0];
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt014() {
        try{   keyLength=64;
        
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt015() {
        try{   keyLength=64;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt016() {
        try{   keyLength=64;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt017() {
        try{   keyLength=64;
        password=new char[99999];       
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt018() {
        try{   keyLength=64;
        password=new char[0];
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt019() {
        try{   keyLength=64;
        
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt020() {
        try{   keyLength=64;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt021() {
        try{   keyLength=64;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt022() {
        try{   keyLength=64;
        password=new char[99999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception..." + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt023() {
        try{   keyLength=64;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt024() {
        try{   keyLength=64;
        
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        if(keySpec.getPassword().length==0){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt025() {
        try{   keyLength=64;
        char []password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        if (keySpec.getPassword()!=password){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt026() {
        try{   keyLength=64;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt027() {
        try{   keyLength=64;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);    
        if (keySpec.getPassword()!=password){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt028() {
        try{   keyLength=64;
        char []password = new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength); 
        if (keySpec.getPassword()!=password){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt029() {
        try{   keyLength=64;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);    
        if (keySpec.getPassword()!=pwd.toCharArray()){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt030() {
        try{   keyLength=64;
        char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);        
        if (keySpec.getSalt()!=salt){
            assertTrue(true);
        }       
        else {assertTrue(false);}       
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt031() {
        try{   keyLength=64;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt032() {
        try{   keyLength=64;
        password=new char[99999];       
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt033() {
        try{   keyLength=64;
        password=new char[0];
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt034() {
        try{   keyLength=64;
        
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt035() {
        try{   keyLength=64;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt036() {
        try{   keyLength=64;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt037() {
        try{   keyLength=64;
        password=new char[99999];       
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt038() {
        try{   keyLength=64;
        password=new char[0];
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt039() {
        try{   keyLength=64;
        
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt040() {
        try{   keyLength=64;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt041() {
        try{   keyLength=64;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt042() {
        try{   keyLength=64;
        password=new char[99999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception..." + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt043() {
        try{   keyLength=64;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt044() {
        try{   keyLength=64;
        
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        if(keySpec.getPassword().length==0){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt045() {
        try{   keyLength=64;
        char []password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        if (keySpec.getPassword()!=password){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt046() {
        try{   keyLength=64;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt047() {
        try{   
        keyLength=64;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);    
        if (keySpec.getPassword()!=password){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt048() {
        try{   
        keyLength=64;
        char []password = new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength); 
        if (keySpec.getPassword()!=password){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt049() {
        try{   keyLength=64;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);    
        if (keySpec.getPassword()!=pwd.toCharArray()){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt050() {
        try{   keyLength=64;
        char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);        
        if (keySpec.getSalt()!=salt){
            assertTrue(true);
        }       
        else {assertTrue(false);}       
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt051() {
        try{   keyLength=64;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt052() {
        try{   keyLength=64;
        password=new char[99999];       
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt053() {
        try{   keyLength=64;
        password=new char[0];
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt054() {
        try{   keyLength=64;
        
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt055() {
        try{   keyLength=64;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt056() {
        try{   keyLength=64;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt057() {
        try{   keyLength=64;
        password=new char[99999];       
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt058() {
        try{   keyLength=64;
        password=new char[0];
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt059() {
        try{   keyLength=64;
        
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt060() {
        try{   keyLength=64;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt061() {
        try{   keyLength=64;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt062() {
        try{   keyLength=64;
        password=new char[99999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt063() {
        try{   keyLength=64;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt064() {
        try{   keyLength=64;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt065() {
        try{   keyLength=64;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt066() {
        try{   keyLength=64;
        password=new char[99999];       
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt067() {
        try{   keyLength=64;
        password=new char[0];
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt068() {
        try{   keyLength=64;
        
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt069() {
        try{   keyLength=64;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt070() {
        try{   keyLength=64;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt071() {
        try{   keyLength=64;
        password=new char[99999];       
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt072() {
        try{   keyLength=64;
        password=new char[0];
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt073() {
        try{   keyLength=64;
        
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt074() {
        try{   keyLength=64;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt075() {
        try{   keyLength=64;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt076() {
        try{   keyLength=64;
        password=new char[99999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt077() {
        try{   keyLength=64;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt078() {
        try{   keyLength=64;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt079() {
        try{   keyLength=64;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt080() {
        try{   keyLength=64;
        password=new char[99999];       
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt081() {
        try{   keyLength=64;
        password=new char[0];
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt082() {
        try{   keyLength=64;
        
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt083() {
        try{   keyLength=64;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt084() {
        try{   keyLength=64;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt085() {
        try{   keyLength=64;
        password=new char[99999];       
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt086() {
        try{   keyLength=64;
        password=new char[0];
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt087() {
        try{   keyLength=64;
        
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt088() {
        try{   keyLength=64;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt089() {
        try{   keyLength=64;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt090() {
        try{   keyLength=64;
        password=new char[99999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt091() {
        try{   keyLength=64;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt092() {
        try{   keyLength=64;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt093() {
        try{   keyLength=64;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt094() {
        try{   keyLength=64;
        password=new char[99999];       
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt095() {
        try{   keyLength=64;
        password=new char[0];
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt096() {
        try{   keyLength=64;
        
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt097() {
        try{   keyLength=64;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt098() {
        try{   keyLength=64;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt099() {
        try{   keyLength=64;
        password=new char[99999];       
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt100() {
        try{   keyLength=64;
        password=new char[0];
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt101() {
        try{   keyLength=64;
        
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt102() {
        try{   keyLength=64;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt103() {
        try{   keyLength=99999999;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;    
        keyLength=99999999;
        keySpec = new PBEKeySpec(password,salt, iterationCount,keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt104() {
        try{   keyLength=99999999;
        password=new char[99999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 250;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception..." + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt105() {
        try{   keyLength=99999999;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt106() {
        try{   keyLength=99999999;
        
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        if(keySpec.getPassword().length==0){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt107() {
        try{   keyLength=99999999;
        char []password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        if (keySpec.getPassword()!=password){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt108() {
        try{   keyLength=99999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt109() {
        try{   keyLength=99999999;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);    
        if (keySpec.getPassword()!=password){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt110() {
        try{   keyLength=99999999;
        char []password = new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength); 
        if (keySpec.getPassword()!=password){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt111() {
        try{   keyLength=99999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);    
        if (keySpec.getPassword()!=pwd.toCharArray()){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt112() {
        try{   keyLength=99999999;
        char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);        
        if (keySpec.getSalt()!=salt){
            assertTrue(true);
        }       
        else {assertTrue(false);}       
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt113() {
        try{   keyLength=99999999;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt114() {
        try{   keyLength=99999999;
        password=new char[99999];       
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt115() {
        try{   keyLength=99999999;
        password=new char[0];
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt116() {
        try{   keyLength=99999999;
        
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt117() {
        try{   keyLength=99999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt118() {
        try{   keyLength=99999999;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt119() {
        try{   keyLength=99999999;
        password=new char[99999];       
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt120() {
        try{   keyLength=99999999;
        password=new char[0];
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt121() {
        try{   keyLength=99999999;
        
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt122() {
        try{   keyLength=99999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt123() {
        try{   keyLength=99999999;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt124() {
        try{   keyLength=99999999;
        password=new char[99999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception..." + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt125() {
        try{   keyLength=99999999;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt126() {
        try{   keyLength=99999999;
        
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        if(keySpec.getPassword().length==0){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt127() {
        try{   keyLength=99999999;
        char []password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        if (keySpec.getPassword()!=password){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt128() {
        try{   keyLength=99999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt129() {
        try{   keyLength=99999999;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);    
        if (keySpec.getPassword()!=password){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt130() {
        try{   keyLength=99999999;
        char []password = new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength); 
        if (keySpec.getPassword()!=password){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt131() {
        try{   keyLength=99999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);    
        if (keySpec.getPassword()!=pwd.toCharArray()){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt132() {
        try{   keyLength=99999999;
        char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);        
        if (keySpec.getSalt()!=salt){
            assertTrue(true);
        }       
        else {assertTrue(false);}       
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt133() {
        try{   keyLength=99999999;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt134() {
        try{   keyLength=99999999;
        password=new char[9999];       
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt135() {
        try{   keyLength=99999999;
        password=new char[0];
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt136() {
        try{   keyLength=99999999;
        
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt137() {
        try{   keyLength=99999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt138() {
        try{   keyLength=99999999;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt139() {
        try{   keyLength=99999999;
        password=new char[9999];       
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt140() {
        try{   keyLength=99999999;
        password=new char[0];
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt141() {
        try{   keyLength=99999999;
        
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt142() {
        try{   keyLength=99999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt143() {
        try{   keyLength=99999999;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt144() {
        try{   keyLength=99999999;
        password=new char[9999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception..." + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt145() {
        try{   keyLength=99999999;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt146() {
        try{   keyLength=99999999;
        
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        if(keySpec.getPassword().length==0){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt147() {
        try{   keyLength=99999999;
        char []password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        if (keySpec.getPassword()!=password){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt148() {
        try{   keyLength=99999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt149() {
        try{   
        keyLength=99999999;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);    
        if (keySpec.getPassword()!=password){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt150() {
        try{   
        keyLength=99999999;
        char []password = new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength); 
        if (keySpec.getPassword()!=password){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt151() {
        try{   keyLength=99999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);    
        if (keySpec.getPassword()!=pwd.toCharArray()){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt152() {
        try{   keyLength=99999999;
        char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);        
        if (keySpec.getSalt()!=salt){
            assertTrue(true);
        }       
        else {assertTrue(false);}       
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt1513() {
        try{   keyLength=99999999;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt154() {
        try{   keyLength=99999999;
        password=new char[9999];       
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt155() {
        try{   keyLength=99999999;
        password=new char[0];
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt156() {
        try{   keyLength=99999999;
        
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt157() {
        try{   keyLength=99999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt158() {
        try{   keyLength=99999999;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt159() {
        try{   keyLength=99999999;
        password=new char[9999];       
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt160() {
        try{   keyLength=99999999;
        password=new char[0];
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt161() {
        try{   keyLength=99999999;
        
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt162() {
        try{   keyLength=99999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt163() {
        try{   keyLength=99999999;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt164() {
        try{   keyLength=99999999;
        password=new char[9999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt165() {
        try{   keyLength=99999999;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt166() {
        try{   keyLength=99999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt167() {
        try{   keyLength=99999999;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt168() {
        try{   keyLength=99999999;
        password=new char[9999];       
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt169() {
        try{   keyLength=99999999;
        password=new char[0];
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt170() {
        try{   keyLength=99999999;
        
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt171() {
        try{   keyLength=99999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt172() {
        try{   keyLength=99999999;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt173() {
        try{   keyLength=99999999;
        password=new char[9999];       
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt174() {
        try{   keyLength=99999999;
        password=new char[0];
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt175() {
        try{   keyLength=99999999;
        
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt176() {
        try{   keyLength=99999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt177() {
        try{   keyLength=99999999;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt178() {
        try{   keyLength=99999999;
        password=new char[9999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt179() {
        try{   keyLength=99999999;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt180() {
        try{   keyLength=99999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt181() {
        try{   keyLength=99999999;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt182() {
        try{   keyLength=99999999;
        password=new char[9999];       
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt183() {
        try{   keyLength=99999999;
        password=new char[0];
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt184() {
        try{   keyLength=99999999;
        
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt18() {
        try{   keyLength=99999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt186() {
        try{   keyLength=99999999;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt187() {
        try{   keyLength=99999999;
        password=new char[9999];       
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt188() {
        try{   keyLength=99999999;
        password=new char[0];
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt189() {
        try{   keyLength=99999999;
        
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt190() {
        try{   keyLength=99999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt191() {
        try{   keyLength=99999999;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt192() {
        try{   keyLength=99999999;
        password=new char[9999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt193() {
        try{   keyLength=99999999;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt194() {
        try{   keyLength=99999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt195() {
        try{   keyLength=99999999;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt196() {
        try{   keyLength=99999999;
        password=new char[9999];       
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt197() {
        try{   keyLength=99999999;
        password=new char[0];
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt198() {
        try{   keyLength=99999999;
        
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt199() {
        try{   keyLength=99999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt200() {
        try{   keyLength=99999999;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt201() {
        try{   keyLength=99999999;
        password=new char[9999];       
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt202() {
        try{   keyLength=99999999;
        password=new char[0];
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt203() {
        try{   keyLength=99999999;
        
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt204() {
        try{   keyLength=99999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }   
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt205() {
        try{   keyLength=1;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;    
        keyLength=1;
        keySpec = new PBEKeySpec(password,salt, iterationCount,keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt206() {
        try{   keyLength=1;
        password=new char[99999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 250;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception..." + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt207() {
        try{   keyLength=1;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt208() {
        try{   keyLength=1;
        
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        if(keySpec.getPassword().length==0){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt209() {
        try{   keyLength=1;
        char []password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        if (keySpec.getPassword()!=password){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt210() {
        try{   keyLength=1;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt211() {
        try{   keyLength=1;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);    
        if (keySpec.getPassword()!=password){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt212() {
        try{   keyLength=1;
        char []password = new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength); 
        if (keySpec.getPassword()!=password){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt213() {
        try{   keyLength=1;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);    
        if (keySpec.getPassword()!=pwd.toCharArray()){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt214() {
        try{   keyLength=1;
        char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);        
        if (keySpec.getSalt()!=salt){
            assertTrue(true);
        }       
        else {assertTrue(false);}       
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt215() {
        try{   keyLength=1;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt216() {
        try{   keyLength=1;
        password=new char[99999];       
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt217() {
        try{   keyLength=1;
        password=new char[0];
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt218() {
        try{   keyLength=1;
        
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt219() {
        try{   keyLength=1;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt220() {
        try{   keyLength=1;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt221() {
        try{   keyLength=1;
        password=new char[99999];       
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt222() {
        try{   keyLength=1;
        password=new char[0];
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt223() {
        try{   keyLength=1;
        
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt224() {
        try{   keyLength=1;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt225() {
        try{   keyLength=1;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt226() {
        try{   keyLength=1;
        password=new char[99999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception..." + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt227() {
        try{   keyLength=1;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt228() {
        try{   keyLength=1;
        
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        if(keySpec.getPassword().length==0){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt229() {
        try{   keyLength=1;
        char []password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        if (keySpec.getPassword()!=password){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt230() {
        try{   keyLength=1;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt231() {
        try{   keyLength=1;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);    
        if (keySpec.getPassword()!=password){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt232() {
        try{   keyLength=1;
        char []password = new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength); 
        if (keySpec.getPassword()!=password){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt233() {
        try{   keyLength=1;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);    
        if (keySpec.getPassword()!=pwd.toCharArray()){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt234() {
        try{   keyLength=1;
        char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);        
        if (keySpec.getSalt()!=salt){
            assertTrue(true);
        }       
        else {assertTrue(false);}       
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt235() {
        try{   keyLength=1;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt236() {
        try{   keyLength=1;
        password=new char[99999];       
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt237() {
        try{   keyLength=1;
        password=new char[0];
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt238() {
        try{   keyLength=1;
        
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt239() {
        try{   keyLength=1;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt240() {
        try{   keyLength=1;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt241() {
        try{   keyLength=1;
        password=new char[99999];       
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt242() {
        try{   keyLength=1;
        password=new char[0];
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt243() {
        try{   keyLength=1;
        
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt244() {
        try{   keyLength=1;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt245() {
        try{   keyLength=1;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt246() {
        try{   keyLength=1;
        password=new char[99999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception..." + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt247() {
        try{   keyLength=1;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt248() {
        try{   keyLength=1;
        
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        if(keySpec.getPassword().length==0){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt249() {
        try{   keyLength=1;
        char []password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        if (keySpec.getPassword()!=password){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt250() {
        try{   keyLength=1;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt251() {
        try{   
        keyLength=1;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);    
        if (keySpec.getPassword()!=password){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt252() {
        try{   
        keyLength=1;
        char []password = new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength); 
        if (keySpec.getPassword()!=password){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt253() {
        try{   keyLength=1;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);    
        if (keySpec.getPassword()!=pwd.toCharArray()){
            assertTrue(true);
        }
        else {assertTrue(false);}
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt254() {
        try{   keyLength=1;
        char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);        
        if (keySpec.getSalt()!=salt){
            assertTrue(true);
        }       
        else {assertTrue(false);}       
        } 
        catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt255() {
        try{   keyLength=1;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt256() {
        try{   keyLength=1;
        password=new char[99999];       
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt257() {
        try{   keyLength=1;
        password=new char[0];
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt258() {
        try{   keyLength=1;
        
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt259() {
        try{   keyLength=1;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt260() {
        try{   keyLength=1;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt261() {
        try{   keyLength=1;
        password=new char[99999];       
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt262() {
        try{   keyLength=1;
        password=new char[0];
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt263() {
        try{   keyLength=1;
        
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt264() {
        try{   keyLength=1;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt265() {
        try{   keyLength=1;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt266() {
        try{   keyLength=1;
        password=new char[99999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt267() {
        try{   keyLength=1;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt268() {
        try{   keyLength=1;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt269() {
        try{   keyLength=1;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt270() {
        try{   keyLength=1;
        password=new char[99999];       
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt271() {
        try{   keyLength=1;
        password=new char[0];
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt272() {
        try{   keyLength=1;
        
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt273() {
        try{   keyLength=1;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt274() {
        try{   keyLength=1;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt275() {
        try{   keyLength=1;
        password=new char[99999];       
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt276() {
        try{   keyLength=1;
        password=new char[0];
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt277() {
        try{   keyLength=1;
        
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt278() {
        try{   keyLength=1;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt279() {
        try{   keyLength=1;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt280() {
        try{   keyLength=1;
        password=new char[99999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt281() {
        try{   keyLength=1;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt282() {
        try{   keyLength=1;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt283() {
        try{   keyLength=1;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt284() {
        try{   keyLength=1;
        password=new char[99999];       
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt285() {
        try{   keyLength=1;
        password=new char[0];
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt286() {
        try{   keyLength=1;
        
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt287() {
        try{   keyLength=1;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt288() {
        try{   keyLength=1;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt289() {
        try{   keyLength=1;
        password=new char[99999];       
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt290() {
        try{   keyLength=1;
        password=new char[0];
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt291() {
        try{   keyLength=1;
        
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt292() {
        try{   keyLength=1;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt293() {
        try{   keyLength=1;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt294() {
        try{   keyLength=1;
        password=new char[99999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt295() {
        try{   keyLength=1;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt296() {
        try{   keyLength=1;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt297() {
        try{   keyLength=1;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt298() {
        try{   keyLength=1;
        password=new char[99999];       
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt299() {
        try{   keyLength=1;
        password=new char[0];
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt300() {
        try{   keyLength=1;
        
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt301() {
        try{   keyLength=1;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt302() {
        try{   keyLength=1;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt303() {
        try{   keyLength=1;
        password=new char[99999];       
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt304() {
        try{   keyLength=1;
        password=new char[0];
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt305() {
        try{   keyLength=1;
        
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt306() {
        try{   keyLength=1;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt307() {
        try{   keyLength=0;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;    
     
        keySpec = new PBEKeySpec(password,salt, iterationCount,keyLength);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt308() {
        try{   keyLength=0;
        password=new char[99999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 250;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt309() {
        try{   keyLength=0;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt310() {
        try{   keyLength=0;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt311() {
        try{   keyLength=0;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;    
        keyLength=0;
        keySpec = new PBEKeySpec(password,salt, iterationCount,keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt312() {
        try{   keyLength=0;
        password=new char[99999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 250;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt313() {
        try{   keyLength=0;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt314() {
        try{   keyLength=0;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt315() {
        try{   keyLength=0;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt316() {
        try{   keyLength=0;
        password=new char[99999];       
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt317() {
        try{   keyLength=0;
        password=new char[0];
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt318() {
        try{   keyLength=0;
        
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt319() {
        try{   keyLength=0;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt320() {
        try{   keyLength=0;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt321() {
        try{   keyLength=0;
        password=new char[99999];       
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt322() {
        try{   keyLength=0;
        password=new char[0];
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt323() {
        try{   keyLength=0;
        
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt324() {
        try{   keyLength=0;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt325() {
        try{   keyLength=0;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt326() {
        try{   keyLength=0;
        password=new char[99999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt327() {
        try{   keyLength=0;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt328() {
        try{   keyLength=0;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
        
      
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt329() {
        try{   keyLength=0;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt330() {
        try{   keyLength=0;
        password=new char[99999];       
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt331() {
        try{   keyLength=0;
        password=new char[0];
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt332() {
        try{   keyLength=0;
        
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt333() {
        try{   keyLength=0;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt334() {
        try{   keyLength=0;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt335() {
        try{   keyLength=0;
        password=new char[99999];       
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt336() {
        try{   keyLength=0;
        password=new char[0];
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt337() {
        try{   keyLength=0;
        
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt338() {
        try{   keyLength=0;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt339() {
        try{   keyLength=0;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt340() {
        try{   keyLength=0;
        password=new char[99999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt341() {
        try{   keyLength=0;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt342() {
        try{   keyLength=0;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt343() {
        try{   keyLength=0;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt344() {
        try{   keyLength=0;
        password=new char[99999];       
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt345() {
        try{   keyLength=0;
        password=new char[0];
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt346() {
        try{   keyLength=0;
        
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt347() {
        try{   keyLength=0;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt348() {
        try{   keyLength=0;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt349() {
        try{   keyLength=0;
        password=new char[99999];       
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt350() {
        try{   keyLength=0;
        password=new char[0];
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt351() {
        try{   keyLength=0;
        
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt352() {
        try{   keyLength=0;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt353() {
        try{   keyLength=0;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt354() {
        try{   keyLength=0;
        password=new char[99999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt355() {
        try{   keyLength=0;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt356() {
        try{   keyLength=0;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt357() {
        try{   keyLength=0;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt358() {
        try{   keyLength=0;
        password=new char[99999];       
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt359() {
        try{   keyLength=0;
        password=new char[0];
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt360() {
        try{   keyLength=0;
        
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt361() {
        try{   keyLength=0;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt362() {
        try{   keyLength=0;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt363() {
        try{   keyLength=0;
        password=new char[99999];       
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt364() {
        try{   keyLength=0;
        password=new char[0];
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt365() {
        try{   keyLength=0;
        
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt366() {
        try{   keyLength=0;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt367() {
        try{   keyLength=0;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt368() {
        try{   keyLength=0;
        password=new char[99999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt369() {
        try{   keyLength=0;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt370() {
        try{   keyLength=0;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt371() {
        try{   keyLength=0;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt372() {
        try{   keyLength=0;
        password=new char[99999];       
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt373() {
        try{   keyLength=0;
        password=new char[0];
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt374() {
        try{   keyLength=0;
        
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt375() {
        try{   keyLength=0;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt376() {
        try{   keyLength=0;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt377() {
        try{   keyLength=0;
        password=new char[99999];       
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt378() {
        try{   keyLength=0;
        password=new char[0];
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt379() {
        try{   keyLength=0;
        
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt380() {
        try{   keyLength=0;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt381() {
        try{   keyLength=0;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt382() {
        try{   keyLength=0;
        password=new char[99999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt383() {
        try{   keyLength=0;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt384() {
        try{   keyLength=0;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt385() {
        try{   keyLength=0;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt386() {
        try{   keyLength=0;
        password=new char[99999];       
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt387() {
        try{   keyLength=0;
        password=new char[0];
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt388() {
        try{   keyLength=0;
        
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt389() {
        try{   keyLength=0;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt390() {
        try{   keyLength=0;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt391() {
        try{   keyLength=0;
        password=new char[99999];       
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt392() {
        try{   keyLength=0;
        password=new char[0];
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt393() {
        try{   keyLength=0;
        
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt394() {
        try{   keyLength=0;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt395() {
        try{   keyLength=-999999999;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;    
     
        keySpec = new PBEKeySpec(password,salt, iterationCount,keyLength);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt396() {
        try{   keyLength=-999999999;
        password=new char[99999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 250;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt397() {
        try{   keyLength=-999999999;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt398() {
        try{   keyLength=-999999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt399() {
        try{   keyLength=-999999999;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;    
        keyLength=-999999999;
        keySpec = new PBEKeySpec(password,salt, iterationCount,keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt400() {
        try{   keyLength=-999999999;
        password=new char[99999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 250;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt401() {
        try{   keyLength=-999999999;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt402() {
        try{   keyLength=-999999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt403() {
        try{   keyLength=-999999999;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt404() {
        try{   keyLength=-999999999;
        password=new char[99999];       
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt405() {
        try{   keyLength=-999999999;
        password=new char[0];
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt406() {
        try{   keyLength=-999999999;
        
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt407() {
        try{   keyLength=-999999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt408() {
        try{   keyLength=-999999999;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt409() {
        try{   keyLength=-999999999;
        password=new char[99999];       
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt410() {
        try{   keyLength=-999999999;
        password=new char[0];
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt411() {
        try{   keyLength=-999999999;
        
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt412() {
        try{   keyLength=-999999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 240;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt413() {
        try{   keyLength=-999999999;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt414() {
        try{   keyLength=-999999999;
        password=new char[99999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt415() {
        try{   keyLength=-999999999;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt416() {
        try{   keyLength=-999999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
        
      
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt417() {
        try{   keyLength=-999999999;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt418() {
        try{   keyLength=-999999999;
        password=new char[99999];       
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt419() {
        try{   keyLength=-999999999;
        password=new char[0];
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt420() {
        try{   keyLength=-999999999;
        
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt421() {
        try{   keyLength=-999999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt422() {
        try{   keyLength=-999999999;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt423() {
        try{   keyLength=-999999999;
        password=new char[99999];       
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt424() {
        try{   keyLength=-999999999;
        password=new char[0];
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt425() {
        try{   keyLength=-999999999;
        
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt426() {
        try{   keyLength=-999999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 99999999;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt427() {
        try{   keyLength=-999999999;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt428() {
        try{   keyLength=-999999999;
        password=new char[99999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt429() {
        try{   keyLength=-999999999;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt430() {
        try{   keyLength=-999999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt431() {
        try{   keyLength=-999999999;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt432() {
        try{   keyLength=-999999999;
        password=new char[99999];       
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt433() {
        try{   keyLength=-999999999;
        password=new char[0];
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt434() {
        try{   keyLength=-999999999;
        
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt435() {
        try{   keyLength=-999999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt436() {
        try{   keyLength=-999999999;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt437() {
        try{   keyLength=-999999999;
        password=new char[99999];       
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt438() {
        try{   keyLength=-999999999;
        password=new char[0];
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt439() {
        try{   keyLength=-999999999;
        
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt440() {
        try{   keyLength=-999999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 1;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt441() {
        try{   keyLength=-999999999;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt442() {
        try{   keyLength=-999999999;
        password=new char[99999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt443() {
        try{   keyLength=-999999999;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt444() {
        try{   keyLength=-999999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt445() {
        try{   keyLength=-999999999;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt446() {
        try{   keyLength=-999999999;
        password=new char[99999];       
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt447() {
        try{   keyLength=-999999999;
        password=new char[0];
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt448() {
        try{   keyLength=-999999999;
        
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt449() {
        try{   keyLength=-999999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt450() {
        try{   keyLength=-999999999;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt451() {
        try{   keyLength=-999999999;
        password=new char[99999];       
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt452() {
        try{   keyLength=-999999999;
        password=new char[0];
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt453() {
        try{   keyLength=-999999999;
        
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt454() {
        try{   keyLength=-999999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= 0;            
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt455() {
        try{   keyLength=-999999999;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt456() {
        try{   keyLength=-999999999;
        password=new char[99999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt457() {
        try{   keyLength=-999999999;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt458() {
        try{   keyLength=-999999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt459() {
        try{   keyLength=-999999999;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt460() {
        try{   keyLength=-999999999;
        password=new char[99999];       
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt461() {
        try{   keyLength=-999999999;
        password=new char[0];
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt462() {
        try{   keyLength=-999999999;
        
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt463() {
        try{   keyLength=-999999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt464() {
        try{   keyLength=-999999999;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt465() {
        try{   keyLength=-999999999;
        password=new char[99999];       
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt466() {
        try{   keyLength=-999999999;
        password=new char[0];
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt467() {
        try{   keyLength=-999999999;
        
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt468() {
        try{   keyLength=-999999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= -1;              
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt469() {
        try{   keyLength=-999999999;
        char []password = {0xc7};
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt470() {
        try{   keyLength=-999999999;
        password=new char[99999];       
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt471() {
        try{   keyLength=-999999999;
        password=new char[0];
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt472() {
        try{   keyLength=-999999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        byte[] salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(pwd.toCharArray(),salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt473() {
        try{   keyLength=-999999999;
        char []password = {0xc7};
        byte[] salt = null;                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt474() {
        try{   keyLength=-999999999;
        password=new char[99999];       
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt475() {
        try{   keyLength=-999999999;
        password=new char[0];
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt476() {
        try{   keyLength=-999999999;
        
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt477() {
        try{   keyLength=-999999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(pwd.toCharArray(),null, iterationCount, keyLength);
        fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt478() {
        try{   keyLength=-999999999;
        char []password = {0xc7};
        byte[] salt = new byte[0];                     
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,salt, iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt479() {
        try{   keyLength=-999999999;
        password=new char[99999];       
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt480() {
        try{   keyLength=-999999999;
        password=new char[0];
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt481() {
        try{   keyLength=-999999999;
        
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(password,new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testPBEKeySpecCharArrayByteArrayIntInt482() {
        try{   keyLength=-999999999;
        pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
        "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
        "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
        "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
        "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
        
        iterationCount= -999999999;                
        keySpec = new PBEKeySpec(pwd.toCharArray(),new byte[0], iterationCount, keyLength);
        fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }    

    
    

    
    /*
     * Test method for 'javax.crypto.spec.PBEKeySpec.getPassword()'
     */

    
    
    public final void testGetPassword001() {
        try{   
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            PBEKeySpec keySpec = new PBEKeySpec(password);
            for(int i=0; i<password.length; i++) {
                assertEquals(password[i] , keySpec.getPassword()[i]);
            }  
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    public final void testGetPassword002() {
        try{   
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;
            
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount);
            for(int i=0; i<password.length; i++) {
                assertEquals(password[i] , keySpec.getPassword()[i]);
            }
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testGetPassword003() {
        try{   
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;       
            keyLength=128;
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
            for(int i=0; i<password.length; i++) {
                assertEquals(password[i] , keySpec.getPassword()[i]);
            }
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    

    public final void testGetPassword004() {
        try{   
            password=new char[0];
            PBEKeySpec keySpec = new PBEKeySpec(password);
            for(int i=0; i<password.length; i++) {
                assertEquals(password[i] , keySpec.getPassword()[i]);
            }
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    public final void testGetPassword005() {
        try{   
            password=new char[0];
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;
            
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount);
            for(int i=0; i<password.length; i++) {
                assertEquals(password[i] , keySpec.getPassword()[i]);
            }
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testGetPassword006() {
        try{   
            password=new char[0];
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;       
            keyLength=128;
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
            for(int i=0; i<password.length; i++) {
                assertEquals(password[i] , keySpec.getPassword()[i]);
            }
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    
    public final void testGetPassword007() {
        try{   
            pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
            password=pwd.toCharArray();
            PBEKeySpec keySpec = new PBEKeySpec(pwd.toCharArray());
            
            for(int i=0; i<password.length; i++) {
                assertEquals(password[i] , keySpec.getPassword()[i]);
            }
        } catch (Throwable e) {
            fail("Should not raise any Exception..."+e);
        }
    }
    public final void testGetPassword008() {
        try{   
            pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
   
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;
            password=pwd.toCharArray();
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount);
            for(int i=0; i<password.length; i++) {
                assertEquals(password[i], keySpec.getPassword()[i]);
            }
        } catch (Throwable e) {
            fail("Should not raise any Exception..." + e);
        }
    }
    
    
    public final void testGetPassword009() {
        try{   
            pwd="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhyteeeeeeeeeeeeeeeeeeeekykkkkkkkkkkkkkkkkk52" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn" +
            "65555555555555555555555555555555555555555555555555555555522222222222222222222222222222rrrrrngnnnnnnnnnnnnnnnnrqwarrtmyktwyjk" +
            "j65w56jjjjjjjjjjjjjjjjjjjjjjy6666666666666666666666666666666666666666666666666666666666666662qqqqqqqqqqqqaaaaaaaaaaaartthjjtrq" +
            "jh654qqjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj666666666666666666666666666666666666666666666666666666666666666" +
            "42wwwwwwwwwwwwwssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssjhhnnnnnnnnnnnn";
            password=pwd.toCharArray();
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;       
            keyLength=128;
            PBEKeySpec keySpec = new PBEKeySpec(pwd.toCharArray(), salt, iterationCount, keyLength);
            for(int i=0; i<password.length; i++) {
                assertEquals(password[i], keySpec.getPassword()[i]);
            }
        } catch (Throwable e) {
            fail("Should not raise any Exception..."+e);
        }
    }
    
    
    
    
    
    public final void testGetPassword010() {
        try{   

            keySpec = new PBEKeySpec(password);
             if (keySpec.getPassword().length==0)
             {
                 assertTrue(true);
             }
             else
             {
                 assertTrue(false);   
             }
           
        } catch (Throwable e) {
            fail("Should not raise any Exception..." + e);
        }
    }
    public final void testGetPassword011() {
        try{   

            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;
            
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount);
            if (keySpec.getPassword().length==0)
            {
                assertTrue(true);
            }
            else
            {
                assertTrue(false);   
            }
        } catch (Throwable e) {
            fail("Should not raise any Exception..." + e);
        }
    }
    
    public final void testGetPassword012() {
        try{   
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;       
            keyLength=128;
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
        
                if (keySpec.getPassword().length==0)
                {
                    assertTrue(true);
                }
                else
                {
                    assertTrue(false);   
                }
         
        } catch (Throwable e) {
            fail("Should not raise any Exception..." + e);
        }
    
    }
    


    public final void testGetPassword013() {
        try{   
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            PBEKeySpec keySpec = new PBEKeySpec(password);
            keySpec.clearPassword();
            keySpec.getPassword();
            fail("Should raise NoSuchAlgorithmException");
        } catch (IllegalStateException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    public final void testGetPassword014() {
        try{   
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};
            iterationCount= 240;  
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount);
            keySpec.clearPassword();
            keySpec.getPassword();
            fail("Should raise NoSuchAlgorithmException");
        } catch (IllegalStateException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    public final void testGetPassword015() {
        try{   
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;       
            keyLength=128;
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
            keySpec.clearPassword();
            keySpec.getPassword();
            fail("Should raise NoSuchAlgorithmException");
        } catch (IllegalStateException e) {
            assertTrue(true);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    
    public final void testGetPassword016() {
        try{   
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            PBEKeySpec keySpec = new PBEKeySpec(password);
            if (keySpec.getPassword() instanceof char[]){
                assertTrue(true);
            }
            else{
                assertTrue(false);
            }
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    public final void testGetPassword017() {
        try{   
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;
            
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount);
            if (keySpec.getPassword() instanceof char[]){
                assertTrue(true);
            }
            else{
                assertTrue(false);
            }
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testGetPassword018() {
        try{   
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;       
            keyLength=128;
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
            if (keySpec.getPassword() instanceof char[]){
                assertTrue(true);
            }
            else{
                assertTrue(false);
            }
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    
    public final void testGetPassword019() {
        try{   
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            PBEKeySpec keySpec = new PBEKeySpec(password);
            assertNotSame("must not be same arrays",password,keySpec.getPassword());
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    public final void testGetPassword020() {
        try{   
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;
            
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount);
            assertNotSame("must not be same arrays",password,keySpec.getPassword());
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testGetPassword021() {
        try{   
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;       
            keyLength=128;
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
            
                assertNotSame("must not be same arrays",password,keySpec.getPassword());
            
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    
    
    public final void testGetPassword022() {
        try{   
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            PBEKeySpec keySpec = new PBEKeySpec(password);
            assertFalse("must be new arrays every call", keySpec.getPassword() == keySpec.getPassword());
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    public final void testGetPassword023() {
        try{   
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;
            
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount);
            assertFalse("must be new arrays every call", keySpec.getPassword() == keySpec.getPassword());
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testGetPassword024() {
        try{   
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;       
            keyLength=128;
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
            
                
                assertFalse("must be new arrays every call", keySpec.getPassword() == keySpec.getPassword());
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    
    
    public final void testGetPassword025() {
        try{   
            char[] password = new char[0];
            PBEKeySpec keySpec = new PBEKeySpec(password);
            assertNotSame("must not be same arrays",password,keySpec.getPassword());
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    public final void testGetPassword026() {
        try{   
            char[] password = new char[0];
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;
            
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount);
            assertNotSame("must not be same arrays",password,keySpec.getPassword());
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testGetPassword027() {
        try{   
            char[] password = new char[0];
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;       
            keyLength=128;
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
            
                assertNotSame("must not be same arrays",password,keySpec.getPassword());
            
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    
    
    public final void testGetPassword028() {
        try{   
            char[] password = new char[0];
            PBEKeySpec keySpec = new PBEKeySpec(password);
            assertFalse("must be new arrays every call", keySpec.getPassword() == keySpec.getPassword());
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    public final void testGetPassword029() {
        try{   
            char[] password = new char[0];
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;
            
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount);
            assertFalse("must be new arrays every call", keySpec.getPassword() == keySpec.getPassword());
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    public final void testGetPassword030() {
        try{   
            char[] password = new char[0];
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;       
            keyLength=128;
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
            
                
                assertFalse("must be new arrays every call", keySpec.getPassword() == keySpec.getPassword());
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    public final void testGetPassword031() {
        try{   
            char[] password = new char[0];
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;       
            keyLength=128;
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
            
                
                assertFalse("must be new arrays every call", keySpec.getPassword() == keySpec.getPassword());
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    

    
    
    
    
    /*
     * Test method for 'javax.crypto.spec.PBEKeySpec.getSalt()'
     */

    
    
    
    
    
    
    
    
    public final void testGetSalt001() {
        try{   
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            PBEKeySpec keySpec = new PBEKeySpec(password);
            assertNull(keySpec.getSalt());
              
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }

    public final void testGetSalt002() {
        try{   
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount);
            for(int i=0; i<salt.length; i++) {
                assertEquals(keySpec.getSalt()[i], salt[i]);
            }     
            
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    public final void testGetSalt003() {
        try{   
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;       
            keyLength=128;
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount,keyLength);
            for(int i=0; i<password.length; i++) {
                assertEquals(password[i] , keySpec.getPassword()[i]);
            }
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    public final void testGetSalt004() {
        try{   
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            byte[]salt = new byte[1];                     
            iterationCount= 240;
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount);
            if(keySpec.getSalt() instanceof byte[]){
                assertTrue(true);
                }
            else{assertTrue(false);
                }  
            
        } catch (Throwable e) {
            fail("Should not raise any Exception..." + e);
        }
    }
    
    public final void testGetSalt005() {
        try{   
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;       
            keyLength=128;
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount,keyLength);
            if(keySpec.getSalt() instanceof byte[]){
                assertTrue(true);
                }
            else{assertTrue(false);
                }
        } catch (Throwable e) {
            fail("Should not raise any Exception..." + e);
        }
    }
    
    
    public final void testGetSalt006() {
        try{   
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount);
            assertNotSame("must not be same arrays",     salt, keySpec.getSalt());            
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    public final void testGetSalt007() {
        try{   
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;       
            keyLength=128;
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount,keyLength);
            assertNotSame("must not be same arrays",     salt, keySpec.getSalt());  
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    

    
    public final void testGetSalt008() {
        try{   
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount);
            assertFalse("must be new arrays every call", keySpec.getSalt() == keySpec.getSalt());  
            
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    public final void testGetSalt009() {
        try{   
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;       
            keyLength=128;
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount,keyLength);
            assertFalse("must be new arrays every call", keySpec.getSalt() == keySpec.getSalt());
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    
    

    
    /*
     * Test method for 'javax.crypto.spec.PBEKeySpec.getIterationCount()'
     */
    public final void testGetIterationCount001() {
        try{   
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            PBEKeySpec keySpec = new PBEKeySpec(password);
            assertEquals(0,keySpec.getIterationCount());              
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }

    public final void testGetIterationCount002() {
        try{   
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount);
            assertEquals(iterationCount, keySpec.getIterationCount());
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    public final void testGetIterationCount003() {
        try{   
            char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
            byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
            iterationCount= 240;       
            keyLength=128;
            PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount,keyLength);
            assertEquals(iterationCount, keySpec.getIterationCount());
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }
    
    /*
     * Test method for 'javax.crypto.spec.PBEKeySpec.getKeyLength()'
     */    
        public final void testGetKeyLength001() {
            try{   
                char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
                PBEKeySpec keySpec = new PBEKeySpec(password);
                assertEquals(0 , keySpec.getKeyLength());              
            } catch (Throwable e) {
                fail("Should not raise any Exception..." + e);
            }
        }

        public final void testGetKeyLength002() {
            try{   
                char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
                byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
                iterationCount= 240;
                PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount);
                assertEquals(0, keySpec.getKeyLength());
            } catch (Throwable e) {
                fail("Should not raise any Exception..." + e);
            }
        }
        
        public final void testGetKeyLength003() {
            try{   
                char[] password = {0xc7, 0x85, 0x21, 0x8c,0x7e, 0xc8, 0xff, 0x99};
                byte[]salt = {(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};                     
                iterationCount= 240;       
                keyLength=128;
                PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount,keyLength);
                assertEquals(keyLength, keySpec.getKeyLength());
            } catch (Throwable e) {
                fail("Should not raise any Exception..." + e);
            }
        }
    
}
