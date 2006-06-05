package ar.org.fitc.test.crypto.spec;

import junit.framework.TestCase;
import javax.crypto.spec.PSource.PSpecified;

public class TestPSourcePSpecified extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestPSourcePSpecified.class);
    }

    public TestPSourcePSpecified(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'javax.crypto.spec.PSource.PSpecified()'
     */

    public final void testPSourcePSpecified001() {
        try{
          byte[] p={(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};
          assertTrue("Must be a instanceof Pspecified", new PSpecified(p) instanceof PSpecified);
      } catch (Throwable e) {
          fail("Should not raise any Exception...");
      }
  }
    public final void testPSourcePSpecified002() {
      try{
        byte[] p=null;
        new PSpecified(p);
        fail("Should raise NullPointerException");
      } catch (NullPointerException e) {
          assertTrue(true);
    } catch (Throwable e) {
        fail("Should not raise any Exception...");
    }
}

    public final void testPSourcePSpecified003() {
        try{
          byte[] p=new byte[0];
          new PSpecified(p);
      } catch (Throwable e) {
          fail("Should not raise any Exception...");
      }
  }

    /*
     * Test method for 'javax.crypto.spec.PSource.PSpecified().getValue()'
     */
    public final void testGetValue001() {
        try{
          byte[] p={(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};
          PSpecified ps= new PSpecified(p);
          if(ps.getValue() instanceof byte[]){
              assertTrue(true);
              }
          else{assertTrue(false);
              }
      } catch (Throwable e) {
          fail("Should not raise any Exception...");
      }
  }


    public final void testGetValue002() {
        try{
          byte[] p={(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};

          PSpecified ps= new PSpecified(p);
          byte[] gvalue = ps.getValue();
          for (int i=0; i < p.length; i++) {
              assertEquals("must be equals arrays", p[i], gvalue[i]);
          }


      } catch (Throwable e) {
          fail("Should not raise any Exception...");
      }
  }

    public final void testGetValue003() {
        try{
          byte[] p={(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};

          PSpecified ps= new PSpecified(p);
          byte[] gvalue = ps.getValue();
          assertNotSame("must not be same arrays", p, gvalue);
      } catch (Throwable e) {
          fail("Should not raise any Exception...");
      }
  }
    public final void testGetValue004() {
        try{
            byte[] p={(byte)0xc7, (byte)0x85, (byte)0x21, (byte)0x8c,(byte)0x7e, (byte)0xc8, (byte)0xff, (byte)0x99};
          PSpecified ps= new PSpecified(p);
          assertFalse("must be new arrays every call", ps.getValue() == ps.getValue());
      } catch (Throwable e) {
          fail("Should not raise any Exception..."+ e);
      }
  }

    public final void testGetValue005() {
        try{
            byte[] p=new byte[0];
          PSpecified ps= new PSpecified(p);
          if(ps.getValue() instanceof byte[]){
              assertTrue(true);
              }
          else{assertTrue(false);
              }
      } catch (Throwable e) {
          fail("Should not raise any Exception...");
      }
  }


    public final void testGetValue006() {
        try{
            byte[] p=new byte[0];

          PSpecified ps= new PSpecified(p);
          byte[] gvalue = ps.getValue();
          for (int i=0; i < p.length; i++) {
              assertEquals("must be equals arrays", p[i], gvalue[i]);
          }


      } catch (Throwable e) {
          fail("Should not raise any Exception...");
      }
  }

    public final void testGetValue007() {
        try{
            byte[] p=new byte[0];

          PSpecified ps= new PSpecified(p);
          byte[] gvalue = ps.getValue();
          assertNotSame("must not be same arrays", p, gvalue);
      } catch (Throwable e) {
          fail("Should not raise any Exception...");
      }
  }



    public final void testDefault() {
        try{
            assertEquals(PSpecified.DEFAULT.getValue().length, 0);
      } catch (Throwable e) {
          fail("Should not raise any Exception..."+ e);
      }
  }


}
