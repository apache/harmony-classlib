package ar.org.fitc.test.crypto.spec;


import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import junit.framework.TestCase;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
public class TestOAEPParameterSpec extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestOAEPParameterSpec.class);
    }

    public TestOAEPParameterSpec(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'javax.crypto.spec.OAEPParameterSpec.OAEPParameterSpec(String, String, AlgorithmParameterSpec, PSource)'
     */
    public final void testOAEPParameterSpec001() {
       try{ String mdName="";
        String mgfName="";
        
        OAEPParameterSpec oaepp= new OAEPParameterSpec(mdName, mgfName, MGF1ParameterSpec.SHA1, PSource.PSpecified.DEFAULT);
        if (oaepp instanceof OAEPParameterSpec){
            assertTrue(true);
        }
        else{
            assertTrue(false);                        
        }
    } catch (Throwable e) {
        fail("Should not raise any Exception...");
    }
}  
    
    public final void testOAEPParameterSpec002() {
        try{ String mdName=null;
         String mgfName="mgf1SHA1";

         new OAEPParameterSpec(mdName, mgfName, MGF1ParameterSpec.SHA1, PSource.PSpecified.DEFAULT);
         fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
      } catch (Throwable e) {
          fail("Should not raise any Exception...");
      }
  }
    
    
    public final void testOAEPParameterSpec003() {
        try{ String mdName="hge";
         String mgfName="";
         
         new OAEPParameterSpec(mdName, mgfName, MGF1ParameterSpec.SHA1, null);
         fail("Should raise NullPointerException");
        } catch (NullPointerException e) {
            assertTrue(true);
      } catch (Throwable e) {
          fail("Should not raise any Exception...");
      }
  }

    
    public final void testOAEPParameterSpec004() {
        try{ String mdName="";
         String mgfName="";

         OAEPParameterSpec oaepp=new OAEPParameterSpec(mdName, mgfName, null, PSource.PSpecified.DEFAULT);
         if (oaepp instanceof OAEPParameterSpec){
             assertTrue(true);
         }
         else{
             assertTrue(false);                        
         }
     } catch (Throwable e) {
         fail("Should not raise any Exception...");
     }
 }  
    /*
     * Test method for 'javax.crypto.spec.OAEPParameterSpec.getDigestAlgorithm()'
     */
    public final void testGetDigestAlgorithm001() {
        try{ String mdName="jrwtyjq";
        String mgfName="";
new PSSParameterSpec(0);
        OAEPParameterSpec oaepp= new OAEPParameterSpec(mdName, mgfName, MGF1ParameterSpec.SHA1, PSource.PSpecified.DEFAULT);
        assertEquals(mdName,oaepp.getDigestAlgorithm());
    } catch (Throwable e) {
        fail("Should not raise any Exception...");
    }
}  
    

    
    public final void testGetDigestAlgorithm002() {
        try{ String mdName="";
        String mgfName="";
        OAEPParameterSpec oaepp= new OAEPParameterSpec(mdName, mgfName, null, PSource.PSpecified.DEFAULT);
        assertEquals(mdName,oaepp.getDigestAlgorithm());
    } catch (Throwable e) {
        fail("Should not raise any Exception...");
    }
}  
    /*
     * Test method for 'javax.crypto.spec.OAEPParameterSpec.getMGFAlgorithm()'
     */
    public final void testGetMGFAlgorithm001() {
        try{ String mdName="jrwtyjq";
        String mgfName="n8yuifg57";
        new PSSParameterSpec(0);
        OAEPParameterSpec oaepp= new OAEPParameterSpec(mdName, mgfName, MGF1ParameterSpec.SHA1, PSource.PSpecified.DEFAULT);
        assertEquals(mgfName,oaepp.getMGFAlgorithm());
    } catch (Throwable e) {
        fail("Should not raise any Exception...");
    }
    }

    
    public final void testGetMGFAlgorithm002() {
        try{ String mdName="jrwtyjq";
        String mgfName="";
       new PSSParameterSpec(0);
        OAEPParameterSpec oaepp= new OAEPParameterSpec(mdName, mgfName, MGF1ParameterSpec.SHA1, PSource.PSpecified.DEFAULT);
        assertEquals(mgfName,oaepp.getMGFAlgorithm());
    } catch (Throwable e) {
        fail("Should not raise any Exception...");
    }
    }
    /*
     * Test method for 'javax.crypto.spec.OAEPParameterSpec.getMGFParameters()'
     */
    public final void testGetMGFParameters001() {
        try{ String mdName="";
        String mgfName="";
        PSSParameterSpec as= new PSSParameterSpec(0);
        OAEPParameterSpec oaepp= new OAEPParameterSpec(mdName, mgfName, as, PSource.PSpecified.DEFAULT);
        assertEquals(as,oaepp.getMGFParameters());    
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
        }
    
    
    public final void testGetMGFParameters002() {
        try{ String mdName="jy5w";
        String mgfName="ter";
        OAEPParameterSpec oaepp= new OAEPParameterSpec(mdName, mgfName, MGF1ParameterSpec.SHA1, PSource.PSpecified.DEFAULT);
        assertEquals(MGF1ParameterSpec.SHA1,oaepp.getMGFParameters());    
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
        }

    /*
     * Test method for 'javax.crypto.spec.OAEPParameterSpec.getPSource()'
     */
    public final void testGetPSource001() {
    try{ String mdName="jy5w";
    String mgfName="ter";
    OAEPParameterSpec oaepp= new OAEPParameterSpec(mdName, mgfName, MGF1ParameterSpec.SHA1, PSource.PSpecified.DEFAULT);
    assertEquals(PSource.PSpecified.DEFAULT,oaepp.getPSource());
    } catch (Throwable e) {
        fail("Should not raise any Exception...");
    }
} 
    
    public final void testDefault001() {
        try{ assertEquals(OAEPParameterSpec.DEFAULT.getDigestAlgorithm(),"SHA-1");        
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }      
    public final void testDefault002() {        
        try{assertEquals(OAEPParameterSpec.DEFAULT.getMGFAlgorithm(),"MGF1");               
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }  
    public final void testDefault003() {  
        try{ assertEquals(OAEPParameterSpec.DEFAULT.getPSource(), PSource.PSpecified.DEFAULT);
        } catch (Throwable e) {
            fail("Should not raise any Exception...");
        }
    }        
}
