package ar.org.fitc.test.crypto;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;

import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import junit.framework.TestCase;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Hex;

import ar.org.fitc.test.crypto.definitions.DefinitionSecretKeyFactory;
import ar.org.fitc.test.util.Messages;
import ar.org.fitc.test.util.crypto.Util;

public class TestSecretKeyFactory extends TestCase implements Messages, DefinitionSecretKeyFactory {
	
    private Provider provider = null;
    private SecretKey key=null;
    private SecretKeyFactory skf=null;
    private KeySpec keySpec = null;
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestSecretKeyFactory.class);
    }

    public TestSecretKeyFactory(String name) {
        super(name);
        provider = Util.getInstanceProvider(providerString); 
    }

    protected void setUp() throws Exception {
        super.setUp();    
        skf = SecretKeyFactory.getInstance(algorithm); 

    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /*
     * Test method for 'javax.crypto.SecretKeyFactory.getInstance(String)'
     */
    public final void testGetInstanceString001() {
    	try{  
    		skf = SecretKeyFactory.getInstance(null);
    		fail(msgNullPointer);
    	} catch (NullPointerException e) {
    		assertTrue(true);
    	} catch (Throwable e) {
    		fail("Failed with:" + e);
    	}
    }
    public final void testGetInstanceString002() {
    	try{  
    		skf = SecretKeyFactory.getInstance("hhatja");
    		fail(msgNoSuchAlgorithm);
    	} catch (NoSuchAlgorithmException e) {
    		assertTrue(true);
    	} catch (Throwable e) {
    		fail("Failed with:" + e);
    	}
    }
    public final void testGetInstanceString003() {
        
        try{
            skf = SecretKeyFactory.getInstance(algorithm);
            assertTrue(skf instanceof SecretKeyFactory);
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    /*
     * Test method for 'javax.crypto.SecretKeyFactory.getInstance(String, String)'
     */
    
    public final void testGetInstanceStringString001() {
        
    	try{  
    		skf = SecretKeyFactory.getInstance(null, providerString);
    		fail(msgNullPointer);
    	} catch (NullPointerException e) {
    		assertTrue(true);
    	} catch (Throwable e) {
    		fail("Failed with:" + e);
    	}
    }

    public final void testGetInstanceStringString002() {
        
    	try {
    		skf = SecretKeyFactory.getInstance(algorithm,providerString);
    		assertTrue(skf instanceof SecretKeyFactory);
     	} catch (Throwable e) {
     		fail("Failed with:" + e);
     	}	
    }


    public final void testGetInstanceStringString003() {
        
    	try{  
    		skf = SecretKeyFactory.getInstance(algorithm,(String) null);
    		fail(msgRaise + "IllegalArgumentException");
    	} catch (IllegalArgumentException e) {
    		assertTrue(true);
    	} catch (Throwable e) {
    		fail("Failed with:" + e);
    	}
    }
       
    public final void testGetInstanceStringString004() {
    	try{  
    		skf = SecretKeyFactory.getInstance("hhatja",providerString);
    		fail(msgNoSuchAlgorithm);
    	} catch (NoSuchAlgorithmException e) {
    		assertTrue(true);
    	} catch (Throwable e) {
    		fail("Failed with:" + e);
    	}
    }
    /*
     * Test method for 'javax.crypto.SecretKeyFactory.getInstance(String, String)'
     */
    public final void testGetInstanceStringString005() {
        
    	try{  
    		skf = SecretKeyFactory.getInstance(algorithm,"asdfasd");
    		fail(msgNoSuchProvider);
    	} catch (NoSuchProviderException e) {
        	assertTrue(true);
    	} catch (Throwable e) {
    		fail("Failed with:" + e);
    	}
    }
    
    public final void testGetInstanceStringString006() {
        try{  
            skf = SecretKeyFactory.getInstance(null,(String) null);
            fail(msgRaise + "IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        } catch (NullPointerException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }

    public final void testGetInstanceStringString007() {
    try{  
        skf = SecretKeyFactory.getInstance(null,"asdfasd");
        fail(msgNoSuchProvider);
    } catch (NoSuchProviderException e) {
    } catch (NullPointerException e) {
    } catch (Throwable e) {
        fail("Failed with:" + e);
    }
    }

    /*
     * Test method for 'javax.crypto.SecretKeyFactory.getInstance(String, Provider)'
     */
    public final void testGetInstanceStringProvider001() {
    	try{  
    		skf = SecretKeyFactory.getInstance(algorithm,provider);
    		assertTrue(skf instanceof SecretKeyFactory);
    	} catch (Throwable e) {
    		fail("Failed with:" + e);
    	}
    }

    public final void testGetInstanceStringProvider002() {

    	try{  
    		skf = SecretKeyFactory.getInstance("asdas",provider);
    		fail(msgNoSuchAlgorithm);
    	} catch (NoSuchAlgorithmException e) {
    		assertTrue(true);
    	} catch (Throwable e) {
    		fail("Failed with:" + e);
    	}
    }

    
    public final void testGetInstanceStringProvider003() {
        
    	try{  
    		skf = SecretKeyFactory.getInstance(algorithm,(Provider) null);
    		fail(msgIllegalArgument);
    	} catch (IllegalArgumentException e) {
    		assertTrue(true);
    	} catch (Throwable e) {
    		fail("Failed with:" + e);
    	}
    }
    
    public final void testGetInstanceStringProvider004() {
        
    	try{  
    		skf = SecretKeyFactory.getInstance(null,provider);
    		fail(msgNullPointer);
    	} catch (NullPointerException e) {
    		assertTrue(true);
    	} catch (Throwable e) {
    		fail("Failed with:" + e);
    	}
    }
    public final void testGetInstanceStringProvider005() {
        
        try{  
            skf = SecretKeyFactory.getInstance(null,(Provider) null);
            fail(msgIllegalArgument);
        } catch (IllegalArgumentException e) {
        } catch (NullPointerException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    public final void testGetInstanceStringProvider006() {
// TODO este test debe traer un provider mal certificado... hay que hacerlo de nuevo y para todas las clasess.. 
//        try{ 
//            skf = SecretKeyFactory.getInstance("DES",Util.getInstanceProvider("ITC no certificate"));
//            fail(msgIllegalArgument);
//        } catch (RuntimeException e) {
//            assertTrue(true);
//        } catch (Throwable e) {
//            fail("Failed with:" + e);
//        }
    }
    
    /*
     * Test method for 'javax.crypto.SecretKeyFactory.generateSecret(KeySpec)'
     */
    public final void testGenerateSecret001() {
    	try{
    		keySpec = Util.getInstanceKeySpec(algorithm);
    		key = skf.generateSecret(keySpec);
    		assertTrue(key instanceof SecretKey);
    	} catch (Throwable e) {
    		fail("Failed with:" + e);
    	}
    }

    /*
     * Test method for 'javax.crypto.SecretKeyFactory.generateSecret(KeySpec)'
     */
    public final void testGenerateSecret002() {
    	try{
    		
            skf.generateSecret(null);
    		fail(msgRaise + " InvalidKeySpecException");
    	} catch (InvalidKeySpecException e) {
    	} catch (Throwable e) {
    		fail("Failed with:" + e);
    	}
    }

    public final void testGenerateSecret003() {
        try{
            keySpec = new SecretKeySpec(Hex.decode("Prueba de encripta"), "nononohay");
            key = skf.generateSecret(keySpec);
            fail(msgRaise + " InvalidKeySpecException");
        } catch (InvalidKeySpecException e) {
        } catch (Throwable e) {
            fail("Failed with:" + e);
        }
    }
    
    
    /*
     * Test method for 'javax.crypto.SecretKeyFactory.getKeySpec(SecretKey, Class)'
     */
    public final void testGetKeySpec001() {
    	testGenerateSecret001();
    	try{
    		KeySpec ky = skf.getKeySpec(key, keySpec.getClass());
    		assertTrue(! ky.equals(keySpec));
    		assertTrue(keySpec.getClass().isInstance(ky));
    	} catch (Throwable e) {
    		fail("Failed with:" + e);
    	}

    }


    /*
     * Test method for 'javax.crypto.SecretKeyFactory.getKeySpec(SecretKey, Class)'
     */
    public final void testGetKeySpec002() {
    	testGenerateSecret001();
    	try{
    		skf.getKeySpec(key, Object.class);
    		assertTrue(false);
    	} catch (InvalidKeySpecException e) {
    	} catch (Throwable e) {
    		fail("Failed with:" + e);
    	}

    }

    /*
     * Test method for 'javax.crypto.SecretKeyFactory.getKeySpec(SecretKey, Class)'
     */
    public final void testGetKeySpec003() {
    	testGenerateSecret001();
    	try{
    		skf.getKeySpec(null, keySpec.getClass());
    		assertTrue(false);
    	} catch (InvalidKeySpecException e) {
    	} catch (Throwable e) {
    		fail("Failed with:" + e);
    	}

    }
    /*
     * Test method for 'javax.crypto.SecretKeyFactory.getProvider()'
     */
    public final void testGetProvider() {
    	assertEquals(skf.getProvider(), provider);

    }

    /*
     * Test method for 'javax.crypto.SecretKeyFactory.getAlgorithm()'
     */
    public final void testGetAlgorithm() {
    	assertEquals(skf.getAlgorithm(), algorithm);

    }

    /*
     * Test method for 'javax.crypto.SecretKeyFactory.translateKey(SecretKey)'
     */
    public final void testTranslateKey001() {
    	testGenerateSecret001();
    	try {
			assertEquals(skf.translateKey(key), skf.translateKey(key));
			
		} catch (Throwable e) {
    		fail("Failed with:" + e);
    	}

    }

    /*
     * Test method for 'javax.crypto.SecretKeyFactory.translateKey(SecretKey)'
     */
    public final void testTranslateKey002() {
    	testGenerateSecret001();
    	try {
            
			skf.translateKey(null);
			fail(msgRaise + "InvalidKeyException");
		} catch (InvalidKeyException e) {
		} catch (Throwable e) {
    		fail("Failed with:" + e);
    	}

    }

}

