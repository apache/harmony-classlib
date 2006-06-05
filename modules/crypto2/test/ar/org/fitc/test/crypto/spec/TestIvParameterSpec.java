package ar.org.fitc.test.crypto.spec;

import javax.crypto.spec.IvParameterSpec;

import junit.framework.TestCase;
import ar.org.fitc.test.util.Messages;

public class TestIvParameterSpec extends TestCase implements Messages{

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestIvParameterSpec.class);
    }

    public TestIvParameterSpec(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'javax.crypto.spec.IvParameterSpec.IvParameterSpec(byte[])'
     */
    public final void testIvParameterSpecByteArray001() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(null));
            fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception...");
    }
    }

    public final void testIvParameterSpecByteArray002() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(new byte[0]));
    } catch (Throwable e) {
        fail("Should not raise any Exception...");
    }
    }


    public final void testIvParameterSpecByteArray003() {
        try{
        	byte []j= {010,101,101};
        	assertNotNull(msgNotNull, new IvParameterSpec(j));
    } catch (Throwable e) {
        fail("Should not raise any Exception...");
    }
    }
    /*
     * Test method for 'javax.crypto.spec.IvParameterSpec.IvParameterSpec(byte[], int, int)'
     */

    public final void testIvParameterSpecByteArrayIntInt001() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(null,0,0));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }

    public final void testIvParameterSpecByteArrayIntInt002() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(new byte[0],0,0));
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt003() {
        try{
        	byte []j= {010,101,101,011,111,101,100,001,000};
        	assertNotNull(msgNotNull, new IvParameterSpec(j,0,0));
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt004() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(null,-3,0));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }

    public final void testIvParameterSpecByteArrayIntInt005() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(new byte[0],-3,0));
            fail("Should raise ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt006() {
        try{
        	byte []j= {010,101,101,011,111,101,100,001,000};
        	assertNotNull(msgNotNull, new IvParameterSpec(j,-3,0));
            fail("Should raise ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }

    public final void testIvParameterSpecByteArrayIntInt007() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(null,3,0));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }

    public final void testIvParameterSpecByteArrayIntInt008() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(new byte[0],3,0));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..." +e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt009() {
        try{
        	byte []j= {010,101,101,011,111,101,100,001,000};
        	assertNotNull(msgNotNull, new IvParameterSpec(j,3,0));
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt010() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(null,10,0));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..." +e);
    }
    }

    public final void testIvParameterSpecByteArrayIntInt011() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(new byte[0],10,0));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..." +e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt012() {
        try{
        	byte []j= {010,101,101,011,111,101,100,001,000};
        	assertNotNull(msgNotNull, new IvParameterSpec(j,10,0));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception...");
    }
    }

    public final void testIvParameterSpecByteArrayIntInt013() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(null,0,-2));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }

    public final void testIvParameterSpecByteArrayIntInt014() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(new byte[0],0,-2));
            fail("Should raise ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt015() {
        try{
        	byte []j= {010,101,101,011,111,101,100,001,000};
        	assertNotNull(msgNotNull, new IvParameterSpec(j,0,-2));
            fail("Should raise ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt016() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(null,-3,-2));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }

    public final void testIvParameterSpecByteArrayIntInt017() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(new byte[0],-3,-2));
            fail("Should raise ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt018() {
        try{
        	byte []j= {010,101,101,011,111,101,100,001,000};
        	assertNotNull(msgNotNull, new IvParameterSpec(j,-3,-2));
            fail("Should raise ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }

    public final void testIvParameterSpecByteArrayIntInt019() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(null,3,-2));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }

    public final void testIvParameterSpecByteArrayIntInt020() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(new byte[0],3,-2));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..." +e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt021() {
        try{
        	byte []j= {010,101,101,011,111,101,100,001,000};
        	assertNotNull(msgNotNull, new IvParameterSpec(j,3,-2));
            fail("Should raise ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt022() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(null,10,-2));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..." +e);
    }
    }

    public final void testIvParameterSpecByteArrayIntInt023() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(new byte[0],10,-2));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..." +e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt024() {
        try{
        	byte []j= {010,101,101,011,111,101,100,001,000};
        	assertNotNull(msgNotNull, new IvParameterSpec(j,10,-2));
            fail("Should raise ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception...");
    }
    }

    public final void testIvParameterSpecByteArrayIntInt025() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(null,0,2));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }

    public final void testIvParameterSpecByteArrayIntInt026() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(new byte[0],0,2));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt027() {
        try{
        	byte []j= {010,101,101,011,111,101,100,001,000};
        	assertNotNull(msgNotNull, new IvParameterSpec(j,0,2));
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt028() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(null,-3,2));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }

    public final void testIvParameterSpecByteArrayIntInt029() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(new byte[0],-3,2));
            fail("Should raise ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt030() {
        try{
        	byte []j= {010,101,101,011,111,101,100,001,000};
        	assertNotNull(msgNotNull, new IvParameterSpec(j,-3,2));
            fail("Should raise ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }

    public final void testIvParameterSpecByteArrayIntInt031() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(null,3,2));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }

    public final void testIvParameterSpecByteArrayIntInt032() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(new byte[0],3,2));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..." +e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt033() {
        try{
        	byte []j= {010,101,101,011,111,101,100,001,000};
        	assertNotNull(msgNotNull, new IvParameterSpec(j,3,2));
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt034() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(null,10,2));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..." +e);
    }
    }

    public final void testIvParameterSpecByteArrayIntInt035() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(new byte[0],10,2));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..." +e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt036() {
        try{
        	byte []j= {010,101,101,011,111,101,100,001,000};
        	assertNotNull(msgNotNull, new IvParameterSpec(j,10,2));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception...");
    }
    }

    public final void testIvParameterSpecByteArrayIntInt037() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(null,0,11));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }

    public final void testIvParameterSpecByteArrayIntInt038() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(new byte[0],0,11));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt039() {
        try{
        	byte []j= {010,101,101,011,111,101,100,001,000};
        	assertNotNull(msgNotNull, new IvParameterSpec(j,0,11));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt040() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(null,-3,11));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }

    public final void testIvParameterSpecByteArrayIntInt041() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(new byte[0],-3,11));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt042() {
        try{
        	byte []j= {010,101,101,011,111,101,100,001,000};
        	assertNotNull(msgNotNull, new IvParameterSpec(j,-3,11));
            fail("Should raise ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }

    public final void testIvParameterSpecByteArrayIntInt043() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(null,3,11));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }

    public final void testIvParameterSpecByteArrayIntInt044() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(new byte[0],3,11));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..." +e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt045() {
        try{
        	byte []j= {010,101,101,011,111,101,100,001,000};
        	assertNotNull(msgNotNull, new IvParameterSpec(j,3,11));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt046() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(null,10,11));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..." +e);
    }
    }

    public final void testIvParameterSpecByteArrayIntInt047() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(new byte[0],10,11));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..." +e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt048() {
        try{
        	byte []j= {010,101,101,011,111,101,100,001,000};
        	assertNotNull(msgNotNull, new IvParameterSpec(j,10,11));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception...");
    }
    }

    public final void testIvParameterSpecByteArrayIntInt049() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(null,0,11));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }

    public final void testIvParameterSpecByteArrayIntInt050() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(new byte[0],0,11));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt051() {
        try{
        	byte []j= {010,101,101,011,111,101,100,001,000};
        	assertNotNull(msgNotNull, new IvParameterSpec(j,0,11));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt052() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(null,-3,11));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }

    public final void testIvParameterSpecByteArrayIntInt053() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(new byte[0],-3,11));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt054() {
        try{
        	byte []j= {010,101,101,011,111,101,100,001,000};
        	assertNotNull(msgNotNull, new IvParameterSpec(j,-3,11));
            fail("Should raise ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }

    public final void testIvParameterSpecByteArrayIntInt055() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(null,3,11));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }

    public final void testIvParameterSpecByteArrayIntInt056() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(new byte[0],3,11));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..." +e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt057() {
        try{
        	byte []j= {010,101,101,011,111,101,100,001,000};
        	assertNotNull(msgNotNull, new IvParameterSpec(j,3,11));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..."+e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt058() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(null,10,11));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..." +e);
    }
    }

    public final void testIvParameterSpecByteArrayIntInt059() {
        try{
        	assertNotNull(msgNotNull, new IvParameterSpec(new byte[0],10,11));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception..." +e);
    }
    }


    public final void testIvParameterSpecByteArrayIntInt060() {
        try{
        	byte []j= {010,101,101,011,111,101,100,001,000};
        	assertNotNull(msgNotNull, new IvParameterSpec(j,10,11));
            fail("Should raise IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception...");
    }
    }
    /*
     * Test method for 'javax.crypto.spec.IvParameterSpec.getIV()'
     */

    	 public final void testGetIV001() {
    	        try{
    	        	IvParameterSpec iv= new IvParameterSpec(new byte[0]);
    	        	assertEquals(iv.getIV().length,0);
    	    } catch (Throwable e) {
    	        fail("Should not raise any Exception...");
    	    }
    	    }


    	    public final void testGetIV002() {
    	        try{
    	        	byte []j= {010,101,101,101,101,010,101,101,101,101,101,101,101,010,101,101,101,101,101,101,101,010,101,101,101,101,101,101,101,010,101,101,101};
    	        	IvParameterSpec iv= new IvParameterSpec(j);
    	        	for(int i=0;i<j.length;i++){
        	        	assertEquals(iv.getIV()[i],j[i]);
        	        	}
    	    } catch (Throwable e) {
    	        fail("Should not raise any Exception...");
    	    }
    	    }


    	  public final void testGetIV003() {
    	        try{
    	        	byte[]j=new byte[0];
    	        	IvParameterSpec iv= new IvParameterSpec(j,0,0);
    	        	assertEquals(iv.getIV().length,0);
    	    } catch (Throwable e) {
    	        fail("Should not raise any Exception..."+e);
    	    }
    	    }

    	 public final void testGetIV004() {
    	        try{
    	        	byte []j= {010,101,101,011,111,101,100,001,000};
    	        	IvParameterSpec iv= new IvParameterSpec(j,0,0);
    	        	assertEquals(iv.getIV().length,0);
    	    } catch (Throwable e) {
    	        fail("Should not raise any Exception..."+e);
    	    }
    	    }

    	 public final void testGetIV005() {
    	        try{
    	        	byte []j= {010,101,101,011,111,101,100,001,000};
    	        	IvParameterSpec iv= new IvParameterSpec(j,3,0);
    	        	assertEquals(iv.getIV().length,0);
    	    } catch (Throwable e) {
    	        fail("Should not raise any Exception..."+e);
    	    }
    	    }

    	public final void testGetIV006() {
    	        try{
    	        	byte []j= {010,101,101,011,111,101,100,001,000};
    	        	IvParameterSpec iv= new IvParameterSpec(j,0,2);
    	        	for(int i=0;i<2;i++){
        	        	assertEquals(iv.getIV()[i],j[i]);
        	        	}
    	    } catch (Throwable e) {
    	        fail("Should not raise any Exception..."+e);
    	    }
    	    }


    	   public final void testGetIV007() {
    	        try{
    	        	byte []j= {010,101,101,011,111,101,100,001,000};
    	        	IvParameterSpec iv= new IvParameterSpec(j,3,2);
    	        	for(int i=0;i<2;i++){
    	        	assertEquals(iv.getIV()[i],j[i+3]);
    	        	}
    	    } catch (Throwable e) {
    	        fail("Should not raise any Exception..."+e);
    	    }
    	    }

    	   public final void testGetIV008() {
   	        try{
   	        	byte []j= {010,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000};
   	        	IvParameterSpec iv= new IvParameterSpec(j,9,25);
   	        	for(int i=0;i<15;i++){
   	        	assertEquals(iv.getIV()[i],j[i+9]);
   	        	}
   	    } catch (Throwable e) {
   	        fail("Should not raise any Exception..."+e);
   	    }
    	   }
 	   public final void testGetIV009() {
  	        try{
  	        	byte []j= {010,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000};
  	        	IvParameterSpec iv= new IvParameterSpec(j,9,25);
  	        	assertFalse("must be new arrays every call", iv.getIV() == iv.getIV());
  	    } catch (Throwable e) {
  	        fail("Should not raise any Exception..."+e);
  	    }
 	   }

  	   public final void testGetIV010() {
	        try{
	        	byte []j= {010,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000,101,101,011,111,101,100,001,000};
	        	IvParameterSpec iv= new IvParameterSpec(j,9,25);
	      	   assertNotSame("must not be same arrays", j, iv.getIV());
	    } catch (Throwable e) {
	        fail("Should not raise any Exception..."+e);
	    }
    	    }
}