/* 
 *  Licensed to the Apache Software Foundation (ASF) under one or more 
 *  contributor license agreements.  See the NOTICE file distributed with 
 *  this work for additional information regarding copyright ownership. 
 *  The ASF licenses this file to You under the Apache License, Version 2.0 
 *  (the "License"); you may not use this file except in compliance with 
 *  the License.  You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0 
 * 
 *  Unless required by applicable law or agreed to in writing, software 
 *  distributed under the License is distributed on an "AS IS" BASIS, 
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 *  See the License for the specific language governing permissions and 
 *  limitations under the License. 
 */ 
/**
 * @author Hugo Beilis
 * @author Leonardo Soler
 * @author Gabriel Miretti
 * @version 1.0
 */
package org.apache.harmony.jndi.tests.javax.naming.ldap.whitebox;

import java.util.Arrays;
import javax.naming.InvalidNameException;
import javax.naming.directory.BasicAttributes;
import javax.naming.ldap.Rdn;
import junit.framework.TestCase;

/**
 * <p>This class test is made to test all cases of package where the coverage was not 100%.</p>
 * <p>We are gonna find here a lot cases from diferent classes, notice here that the conventional structure
 * followed in the rest of the proyect is applied  here.</p>
 * 
 */
public class TestRdnWhiteBoxDevelopment extends TestCase {
    
    private Rdn rdn;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestRdnWhiteBoxDevelopment.class);
    }

    public TestRdnWhiteBoxDevelopment(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
       
    }

    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
     * <p>Here we are testing if the constructor can recive several multivalued types.</p>
     * <p>The expected result is a not null instance.</p>
     */
    public void testRdnString() {
        try {
            rdn = new Rdn("test=now+cn=mio+cn=mio2+ou=please+cn=mio3+ou=please2");
            assertNotNull(rdn);
        } catch (InvalidNameException e1) {
            fail("Failed with: "+e1);
        }
    }
    
    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
     * <p>Here we are testing if the constructor can recive several multivalued types.</p>
     * <p>The expected result is a not null instance.</p>
     */
    public void testRdnString001() {
        try {
            rdn = new Rdn("cn=mio+ou=please+cn=mio2+cn=mio3");
            assertNotNull(rdn);
        } catch (InvalidNameException e1) {
            fail("Failed with: "+e1);
        }
    }
    
    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
     * <p>Here we are testing if the constructor can recive several multivalued types.</p>
     * <p>The expected result is a not null instance.</p>
     */
    public void testRdnString002() {
        try {
            rdn = new Rdn("ou=please+test=now+cn=mio+cn=mio2+ou=please+cn=mio3+ou=please2+nueva=prueba");
            assertNotNull(rdn);
        } catch (InvalidNameException e1) {
            fail("Failed with: "+e1);
        }
    }
    
    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
     * <p>Here we are testing if the constructor can recive several multivalued types.</p>
     * <p>The expected result is a not null instance.</p>
     */
    public void testRdnString003() {
        try {
            rdn = new Rdn("au=please+d=d+b=now+cn=mio+cn=mio2+ou=please+cn=mio3+b=please2+na=prueba");
            assertNotNull(rdn);
        } catch (InvalidNameException e1) {
            fail("Failed with: "+e1);
        }
    }
    
    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
     * <p>Here we are testing if the constructor can recive the special character "#".</p>
     * <p>The expected result is a not null instance.</p>
     */
    public void testRdnString004() {
        try {
            rdn = new Rdn("au=#00420ced");
            assertNotNull(rdn);
        } catch (InvalidNameException e1) {
            fail("Failed with: "+e1);
        }
    }
    
    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
     * <p>Here we are testing if the constructor can recive the special character "#".</p>
     * <p>The expected result is a not null instance.</p>
     */
    public void testRdnString005() {
        try {
            rdn = new Rdn("au=\\#00420ced");
            assertNotNull(rdn);
        } catch (InvalidNameException e1) {
            fail("Failed with: "+e1);
        }
    }
    
    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
     * <p>Here we are testing if the constructor can recive the special character "#".</p>
     * <p>The expected result is a not null instance.</p>
     */
    public void testRdnString016() {
        
        String x="t=\\#0FA3TA";
        try {
            Rdn y=new Rdn(x);
            assertNotNull(y);
        } catch (InvalidNameException e) {
            fail("Failed with:"+e);
        }
        
    }
    
    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
     * <p>Here we are testing if the constructor can recive utf8.</p>
     * <p>The expected result is a not null instance.</p>
     */
    public void testRdnString017() {
        try {
            rdn = new Rdn("t=\\4CM\\4E+u=Minombre\\40+a=\\4C\\0d");
        } catch (InvalidNameException e) {
            e.printStackTrace();
            fail();
        }
    }
    
    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
     * <p>Here we are testing if the constructor can recive the special character "=".</p>
     * <p>The expected result is a not null instance.</p>
     */
    public void testRdnString018() {
        
        String x="v=a=a";
        try {
            rdn =new Rdn(x);
            assertNotNull(rdn);
        } catch (InvalidNameException e) {
            fail("Failed with:"+e);
        }
    }
    
    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
     * <p>Here we are testing if the constructor can recive the special character "#".</p>
     * <p>The expected result is a not null instance.</p>
     */
    public void testRdnString019() {
        
        String x="v=a=+a=+v=#0D8F";
        try {
            rdn =new Rdn(x);
            assertNotNull(rdn);
        } catch (InvalidNameException e) {
            fail("Failed with:"+e);
        }
    }
    
    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
     * <p>Here we are testing if the constructor can recive the special character "=".</p>
     * <p>The expected result is a not null instance.</p>
     */
    public void testParsingEqualCharacter() {
        String x="v=======";
        try {
            rdn =new Rdn(x);
            assertNotNull(rdn);
        } catch (InvalidNameException e) {
            fail("Failed with:"+e);
        }
    }
    
    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
     * <p>Here we are testing if the constructor can recive the special character "\".</p>
     * <p>The expected result is a not null instance.</p>
     */
    public void testQuotedTypeCHECK() {
        
        String x="v=+a=+\\\\a="; //+v=#0D8F";
        try {
            rdn =new Rdn(x);
            fail("Type should not contains quoted chars");
        } catch (InvalidNameException e) {
           
        }
    }
    
    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
     * <p>Here we are testing if the constructor can recive the special character "+".</p>
     * <p>The expected result is a not null instance.</p>
     */
    public void testIgnoreEndingPlusChar() {
        String x="a=k+";
        try {
            rdn =new Rdn(x);
            Rdn rdn2 = new Rdn("a=k");
            assertTrue(rdn.equals(rdn2));
            assertNotNull(rdn);
        } catch (InvalidNameException e) {
            fail("Failed with:"+e);
        }
    }
    
    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
     * <p>Here we are testing if the constructor can recive the special character "<".</p>
     * <p>The expected result is a not null instance.</p>
     */
    public void testParsingMinorCharacter() {
        String x="v=<";
        try {
            rdn =new Rdn(x);
            assertNotNull(rdn);
        } catch (InvalidNameException e) {
            fail("Failed with:"+e);
        }
    }
    
    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.Rdn(String)'</p>
     * <p>Here we are testing if the constructor can recive the special character "#".</p>
     * <p>The expected result is that two rdns must not be the same.</p>
     */
    public final void testEquals() {
        try {
            rdn = new Rdn("au=#00420ced");
            assertNotSame(rdn,rdn = new Rdn("au=\\#00420ced"));
        } catch (InvalidNameException e) {
            fail("Failed with: "+e);
        }
        
    }
    
    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.equals(Rdn)'</p>
     * <p>Here we are testing if the method can recive a Rdn.</p>
     * <p>The expected result is that two rdns must be the same.</p>
     */
    public void testRDNEqualsCompare() {
            String t="a=\\<a+a=a\\=a+t=test+t=test+v=fsdasd+v=LL";
            Rdn rdn;
            try {
                rdn = new Rdn("t=test+v=\\4C\\4C+t=test+a=a=a+v=fsdasd+a=<a");
                Rdn rdn1=new Rdn(rdn.toString());
                assertTrue(rdn.equals(rdn1));
            } catch (InvalidNameException e) {
                fail("Failed with:"+e);
            }
    }
    
    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.equals(Rdn)'</p>
     * <p>Here we are testing if the method can recive a Rdn.</p>
     * <p>The expected result is that two rdns must be the same.</p>
     */
    public void testRDNequals() {

    	byte[] aux=new byte[]{116,101,115,116};//this is equal to say "test" in ascii.
        Rdn x1 = null;
        Rdn x2 = null;
        Rdn x3 = null;
        try {
            x1 = new Rdn("t=test");
            x2=new Rdn(x1);
            x3=new Rdn("t",aux);
        }catch (InvalidNameException e) {
         	fail();
        }
        
        assertFalse("Should not be equals.",x2.equals(x3));
    }
    
    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.equals(Rdn)'</p>
     * <p>Here we are testing if the method can recive a Rdn.</p>
     * <p>The expected result is that two rdns must be the same.</p>
     */
	public void testRDNequals2() {
        Rdn x2 = null;
        Rdn x3 = null; 
        try {
            x2 = new Rdn("cn","#0001");
            x3 = new Rdn("cn",new byte[]{0,1});
        } catch (InvalidNameException e) {
            e.printStackTrace();
        }
        assertTrue("Should be equals.",x2.equals(x3));
    }
	
	/**
     * <p>Test method for 'javax.naming.ldap.Rdn.equals(Rdn)'</p>
     * <p>Here we are testing if the method can recive a Rdn.</p>
     * <p>The expected result is that two rdns must be the same.</p>
     */
	public void testRDNequals3() {
        Rdn x1 = null;
		Rdn x2 = null;
        Rdn x3 = null;
        Rdn x4 = null;
        Rdn x5 = null;
        MyBasicAttributes set = new MyBasicAttributes("cn",new byte[]{116,116,116});
        try {
        	
            x2 = new Rdn("cn=ttt");
            x3 = new Rdn("cn",new byte[]{116,116,116});
            x1 = new Rdn(x2);
            x4 = new Rdn(set);
        } catch (InvalidNameException e) {
        	fail("Failed with:"+e);
        }
        assertFalse("Should not be equals.",x3.equals(x2));
        assertFalse("Should not be equals.",x1.equals(x3));
        assertTrue("Should be equals.",x3.equals(x3));
        assertTrue("Should be equals.",x3.equals(x4));
        try {
        	x1 = new Rdn("cn",new byte[]{11,116,11});
        	x2 = new Rdn("cn",new byte[]{116,116,116});
            x3 = new Rdn("cn",new byte[]{116,116,116});
            x4 = new Rdn("cn=#30");
            x5 = new Rdn("cn", new byte[]{0});
        
        } catch (InvalidNameException e) {
        	fail("Failed with:"+e);
        }
        assertFalse(x1.equals(x3));
        assertTrue(x2.equals(x3));
        assertFalse(x5.equals(x4));
        
    }
	  
    
    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.equals(Rdn)'</p>
     * <p>Here we are testing if the method can recive a Rdn with a different array.</p>
     * <p>The expected result is that two rdns must not be the same.</p>
     */
    public final void testEqualsArrays() {
        try {
        	Rdn x=new Rdn("t",new byte[]{01,00,02});
        	Rdn y=new Rdn("t",new byte[]{00,03,01});
        	assertFalse(x.equals(y));
        } catch (InvalidNameException e) {
            fail("Failed with: "+e);
        }
        
    }
    
    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.escapedValue(Object)'</p>
     * <p>Here we are testing if this method can escape some values.</p>
     * <p>The expected result is that the method return the string escaped.</p>
     */
    public final void testEscapedValue001() {
        assertEquals("t�a\\, mar�a",Rdn.escapeValue("t�a, mar�a"));
    }

    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.escapedValue(Object)'</p>
     * <p>Here we are testing if this method can escape some values.</p>
     * <p>The expected result is that the method return the string escaped.</p>
     */
    public final void testEscapedValue002() {
        assertEquals("#00420ced",Rdn.escapeValue(new byte[]{0,66,12,-19}));
    }
    
    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.escapedValue(Object)'</p>
     * <p>Here we are testing if this method can escape some values.</p>
     * <p>The expected result is that the method return the string escaped.</p>
     */
    public final void testescapedValue003() {
        assertEquals("#fa08",Rdn.escapeValue(new byte[]{-6, 8}));
    }

    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.escapedValue(Object)'</p>
     * <p>Here we are testing if this method can escape some values.</p>
     * <p>The expected result is that the method return the string escaped.</p>
     */
    public final void testEscapedValue004() {
        assertEquals("t�a\\, mar�a \\#\\,sobrante\\>\\<",Rdn.escapeValue("t�a, mar�a #,sobrante><"));
    }
    
    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.escapedValue(Object)'</p>
     * <p>Here we are testing if this method can escape some values.</p>
     * <p>The expected result is that the method return the string escaped.</p>
     */
    public final void testEscapedValue005() {
        assertEquals("t�a\\, mar�a \\#\\,sobrante\\>\\<",Rdn.escapeValue("t�a, mar�a #,sobrante><"));
    }
    
    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.toString()'</p>
     * <p>Here we are testing if this method returns the correct string.</p>
     */
    public void testToString001() {
        try {
            String t="t=test+v=value+t=test2+v=value2";
            Rdn rdn=new Rdn(t);
            assertEquals("t=test+t=test2+v=value+v=value2",rdn.toString());
        } catch (Throwable e) {
            fail("Failed with:"+e);
        }
    }
    
    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.toString()'</p>
     * <p>Here we are testing if this method returns the correct string.</p>
     */
    public void testToString002() {
        try {
            String t="SN=Lu\\C4\\8Di\\C4\\87";
            Rdn rdn=new Rdn(t);
            assertEquals(8,rdn.toString().getBytes().length);
        } catch (Throwable e) {
            fail("Failed with:"+e);
        }
    }
    
    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.toString()'</p>
     * <p>Here we are testing if this method returns the correct string.</p>
     */    
    public void testToString003() {
        try {
            String t="v=#080100";
            Rdn rdn=new Rdn("v",new byte[]{8,01,0});
            assertEquals(t,rdn.toString());
        } catch (Throwable e) {
            fail("Failed with:"+e);
        }
        
    }
    
    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.toString()'</p>
     * <p>Here we are testing if this method returns the correct string.</p>
     */   
    public void testToString004() {
        try {
        	String x="a";
            Rdn rdn=new Rdn("v"," ");
            if("v=\\ "==rdn.toString())fail("Should not happens this.");
        } catch (Throwable e) {
            fail("Failed with:"+e);
        }
        
    }
     
    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.escapedValue(Object)'</p>
     * <p>Here we are testing if this method can escape some values.</p>
     * <p>The expected result is that the method return the string escaped.</p>
     */
    public void testUnescapedValue001() {
    	
        assertEquals(Arrays.toString(new byte[]{-6, 8}),Arrays.toString((byte[]) Rdn.unescapeValue("#fa08")));    
    }
    

    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.escapedValue(Object)'</p>
     * <p>Here we are testing if this method can escape some values.</p>
     * <p>The expected result is that the method return the string escaped.</p>
     */
    public void testUnescapedValue002() {
        try {
            Rdn.unescapeValue("##fa08");
            fail("An exception must be thrown.");
        } catch (IllegalArgumentException e) {
        	
        }
    }
    
    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.escapedValue(Object)'</p>
     * <p>Here we are testing if this method can escape some values.</p>
     * <p>The expected result is that the method return the string escaped.</p>
     */
    public void testUnescapedValue003() {
        assertEquals("t=LMN+u=Minombre@+a=L",Rdn.unescapeValue("t=\\4CM\\4E+u=Minombre\\40+a=\\4C"));
    }   
    
    /**
     * <p>Test method for 'javax.naming.ldap.Rdn.hashCode()'</p>
     * <p>Here we are testing if this method return the hash of an object distinct in the value of a string.</p>
     */
    public void testHashCode(){
    	try {
			Rdn x=new Rdn("t",new byte[]{01,00,02});
			assertNotNull(x);
			assertNotSame(0,x.hashCode());
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}
    }
    
    /**
	 * <p>Test method for 'javax.naming.ldap.Rdn.getValue()'</p>
	 * <p>Here we are testing if this method retrieves one of this Rdn's value. In this case should raise an exception
	 * because the rdn is empty.</p>
	 * <p>The expected result is an exception.</p>
	 */
	public void testGetValue001() {
		try {
			Rdn rdn=new Rdn("t"," ");
			System.out.println(rdn.getValue());
			
		} catch (Throwable e) {
			fail("Failed with:"+e);
		}
		
	}

	/**
	 * <p>Test method to test if the constructor take literally what is between '"'.</p>
	 *
	 */
	public void testRDNComillas() {
        Rdn x2 = null;
        Rdn x3 = null; 
        try {
            x2 = new Rdn("cn","\"mio\"");
            x3 = new Rdn("cn","\"mio\"");
        } catch (InvalidNameException e) {
            fail("Failed with:"+e);
        }
        assertTrue("Should be equals.",x2.equals(x3));
    }
	
	/**
	 * <p>Test method to test if the constructor take literally what is between '"'.</p>
	 *
	 */
	public void testRDNComillas2() {
        Rdn x2 = null;
        try {
            String temp="cn=mio\\,zapato\\,veloz\\;\\\" \\#";
        	x2 = new Rdn("cn=\"mio,zapato,veloz;\\\" \\#\"");
            assertEquals(0,temp.compareToIgnoreCase(x2.toString()));
        } catch (InvalidNameException e) {
            fail();
        }
    }

	/**
	 * <p>Test methdo to test if the constructor can recive the correct lenght to a byte array.</p>
	 *
	 */
	public void testRDNWrongHexValue() {
        try {
            Rdn x2 = new Rdn("cn=#A");
            fail();
        } catch (InvalidNameException e) {
        } catch (IllegalArgumentException e) {
        }
    
    }
	
	/**
	 * Here we are testing to send the escaped special characters and utf8. 
	 *
	 */
	public void testRdnEscapeValue002(){
		String fName="cn=\\;\\\"";
		try {
			Rdn x=new Rdn(fName);
			assertNotNull(x);
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}
		
		
		
	}
	
	/**
	 * Here we are testing to send the escaped special characters and utf8. 
	 *
	 */
	public void testRdnEscapeValue003(){
		String fName="cn=\\<\\>";
		try {
			Rdn x=new Rdn(fName);
			assertNotNull(x);
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}
		
		
		
	}

	/**
	 * Here we are testing to send the escaped special characters and utf8. 
	 *
	 */
	public void testRdnEscapeValue004(){
		String fName="cn=\\<\\>\\\\";
		try {
			Rdn x=new Rdn(fName);
			fail("Failed, an exception must be thrown. ");
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		} catch (Exception e) {
			
		}
		
		
		
	}
	
	/**
	 * Here we are testing to send the escaped special characters and utf8. 
	 *
	 */
	public void testRdnEscapeValue005(){
		String fName="cn=\\<\\>\\=";
		try {
			Rdn x=new Rdn(fName);
			assertNotNull(x);
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}
		
		
		
	}
	/**
	 * Here we are testing to send the escaped special characters and utf8. 
	 *
	 */
	public void testRdnEscapeValue006(){
		String fName="cn=\\<\\>\"\\+\"";
		try {
			Rdn x=new Rdn(fName);
			assertNotNull(x);
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}
		
		
		
	}

	/**
	 * Here we are testing to send the escaped special characters and utf8. 
	 *
	 */
	public void testRdnEscapeValue007(){
		String fName="cn=\\<\\>\\4C";
		try {
			Rdn x=new Rdn(fName);
			assertNotNull(x);
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}
		
		
		
	}
	
	/**
	 * Here we are testing to send the escaped special characters and utf8. 
	 *
	 */
	public void testRdnEscapeValue008(){
		String fName="cn=\\<\\>\\ ";
		try {
			Rdn x=new Rdn(fName);
			assertNotNull(x);
		} catch (InvalidNameException e) {
			fail("Failed with:"+e);
		}
		
		
		
	}
	/*
	 * Class to help us to do the white box.
	 */
	private class MyBasicAttributes extends BasicAttributes {
		
		private static final long serialVersionUID = 1L;
		private Object myo;
		MyBasicAttributes(String x,Object o){
			super(x,o);
			this.myo=o;
		}
		public Object getValue(){
			return myo;
		}
	}

}


