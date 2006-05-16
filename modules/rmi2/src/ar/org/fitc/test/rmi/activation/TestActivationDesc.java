/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
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
 * @author Osvaldo Demo
 * @author Jorge Rafael
 * @version 1.0
 */
package ar.org.fitc.test.rmi.activation;

import ar.org.fitc.test.util.Messages;
import junit.framework.TestCase;

import java.rmi.MarshalledObject;
import java.rmi.activation.*;


public class TestActivationDesc extends TestCase implements Messages{

	public TestActivationDesc(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}



	/*
	 * Test method for 'java.rmi.activation.ActivationDesc.ActivationDesc(String, String, MarshalledObject)'
	 */
	  public void testActivationDescStringStringMarshalledObject001() {
	      try{
	assertNotNull(msgNotNull, new ActivationDesc(null , null ,  new MarshalledObject(new Double(23.4)) ));
	            fail("Shoud raise ActivationException");
	        } catch (ActivationException e) {
	} catch (Throwable e) {
	    fail(msgNoException+e);
	}
	}
	   public void testActivationDescStringStringMarshalledObject002() {
	      try{
	assertNotNull(msgNotNull, new ActivationDesc(null , null ,  new MarshalledObject(null) ));
	            fail("Shoud raise ActivationException");
	        } catch (ActivationException e) {
	} catch (Throwable e) {
	    fail(msgNoException+e);
	}
	}
	   public void testActivationDescStringStringMarshalledObject003() {
	      try{
	assertNotNull(msgNotNull, new ActivationDesc(null ,  "" ,  new MarshalledObject(new Double(23.4)) ));
	            fail("Shoud raise ActivationException");
	        } catch (ActivationException e) {
	} catch (Throwable e) {
	    fail(msgNoException+e);
	}
	}
	   public void testActivationDescStringStringMarshalledObject004() {
	      try{
	assertNotNull(msgNotNull, new ActivationDesc(null ,  "" ,  new MarshalledObject(null) ));
	            fail("Shoud raise ActivationException");
	        } catch (ActivationException e) {
	} catch (Throwable e) {
	    fail(msgNoException+e);
	}
	}
	   public void testActivationDescStringStringMarshalledObject005() {
	      try{
	assertNotNull(msgNotNull, new ActivationDesc(null ,  "myLocation" ,  new MarshalledObject(new Double(23.4)) ));
	            fail("Shoud raise ActivationException");
	        } catch (ActivationException e) {
	} catch (Throwable e) {
	    fail(msgNoException+e);
	}
	}
	   public void testActivationDescStringStringMarshalledObject006() {
	      try{
	assertNotNull(msgNotNull, new ActivationDesc(null ,  "myLocation" ,  new MarshalledObject(null) ));
	            fail("Shoud raise ActivationException");
	        } catch (ActivationException e) {
	} catch (Throwable e) {
	    fail(msgNoException+e);
	}
	}
	   public void testActivationDescStringStringMarshalledObject007() {
	      try{
	assertNotNull(msgNotNull, new ActivationDesc("" , null ,  new MarshalledObject(new Double(23.4)) ));
	            fail("Shoud raise ActivationException");
	        } catch (ActivationException e) {
	} catch (Throwable e) {
	    fail(msgNoException+e);
	}
	}
	   public void testActivationDescStringStringMarshalledObject008() {
	      try{
	assertNotNull(msgNotNull, new ActivationDesc("" , null ,  new MarshalledObject(null) ));
	            fail("Shoud raise ActivationException");
	        } catch (ActivationException e) {
	} catch (Throwable e) {
	    fail(msgNoException+e);
	}
	}
	   public void testActivationDescStringStringMarshalledObject009() {
	      try{
	assertNotNull(msgNotNull, new ActivationDesc("" ,  "" ,  new MarshalledObject(new Double(23.4)) ));
	            fail("Shoud raise ActivationException");
	        } catch (ActivationException e) {
	} catch (Throwable e) {
	    fail(msgNoException+e);
	}
	}
	   public void testActivationDescStringStringMarshalledObject010() {
	      try{
	assertNotNull(msgNotNull, new ActivationDesc("" ,  "" ,  new MarshalledObject(null) ));
	            fail("Shoud raise ActivationException");
	        } catch (ActivationException e) {
	} catch (Throwable e) {
	    fail(msgNoException+e);
	}
	}
	   public void testActivationDescStringStringMarshalledObject011() {
	      try{
	assertNotNull(msgNotNull, new ActivationDesc("" ,  "myLocation" ,  new MarshalledObject(new Double(23.4)) ));
	            fail("Shoud raise ActivationException");
	        } catch (ActivationException e) {
	} catch (Throwable e) {
	    fail(msgNoException+e);
	}
	}
	   public void testActivationDescStringStringMarshalledObject012() {
	      try{
	assertNotNull(msgNotNull, new ActivationDesc("" ,  "myLocation" ,  new MarshalledObject(null) ));
	            fail("Shoud raise ActivationException");
	        } catch (ActivationException e) {
	} catch (Throwable e) {
	    fail(msgNoException+e);
	}
	}
	   public void testActivationDescStringStringMarshalledObject013() {
	      try{
	assertNotNull(msgNotNull, new ActivationDesc("myClass" , null ,  new MarshalledObject(new Double(23.4)) ));
	            fail("Shoud raise ActivationException");
	        } catch (ActivationException e) {
	} catch (Throwable e) {
	    fail(msgNoException+e);
	}
	}
	   public void testActivationDescStringStringMarshalledObject014() {
	      try{
	assertNotNull(msgNotNull, new ActivationDesc("myClass" , null ,  new MarshalledObject(null) ));
	            fail("Shoud raise ActivationException");
	        } catch (ActivationException e) {
	} catch (Throwable e) {
	    fail(msgNoException+e);
	}
	}
	   public void testActivationDescStringStringMarshalledObject015() {
	      try{
	assertNotNull(msgNotNull, new ActivationDesc("myClass" ,  "" ,  new MarshalledObject(new Double(23.4)) ));
	            fail("Shoud raise ActivationException");
	        } catch (ActivationException e) {
	} catch (Throwable e) {
	    fail(msgNoException+e);
	}
	}
	   public void testActivationDescStringStringMarshalledObject016() {
	      try{
	assertNotNull(msgNotNull, new ActivationDesc("myClass" ,  "" ,  new MarshalledObject(null) ));
	            fail("Shoud raise ActivationException");
	        } catch (ActivationException e) {
	} catch (Throwable e) {
	    fail(msgNoException+e);
	}
	}
	   public void testActivationDescStringStringMarshalledObject017() {
	      try{
	assertNotNull(msgNotNull, new ActivationDesc("myClass" ,  "myLocation" ,  new MarshalledObject(new Double(23.4)) ));
	            fail("Shoud raise ActivationException");
	        } catch (ActivationException e) {
	} catch (Throwable e) {
	    fail(msgNoException+e);
	}
	}
	   public void testActivationDescStringStringMarshalledObject018() {
	      try{
	assertNotNull(msgNotNull, new ActivationDesc("myClass" ,  "myLocation" ,  new MarshalledObject(null) ));
	            fail("Shoud raise ActivationException");
	        } catch (ActivationException e) {
	} catch (Throwable e) {
	    fail(msgNoException+e);
	}
	}
	   
	   public void testActivationDescStringStringMarshalledObject019() {
		      try{
		    	  ActivationGroupID id= new ActivationGroupID(null);
		    	  MarshalledObject mo= new MarshalledObject(null);
		    	  //System.setSecurityManager(new SecurityManager());
		    	  ActivationGroupDesc desc= new ActivationGroupDesc(null, null, mo, null, null);
		    	  ActivationGroup.createGroup(id, desc, 0);		    	  
		    	  /*ActivationGroupID id= new ActivationGroupID(null);
		    	  ActivationGroupDesc desc= ActivationSystem;*/
		    	 // long incarnation=18641;		    	  
		    	  //ActivationGroup.createGroup( id, desc, incarnation) ;
	//	assertNotNull(msgNotNull, new ActivationDesc("myClass" ,  "myLocation" ,  new MarshalledObject(null) ));		            
		       
		} catch (Throwable e) {
		    fail(msgNoException+e);
		}
		}

	/*
	 * Test method for 'java.rmi.activation.ActivationDesc.ActivationDesc(String, String, MarshalledObject, boolean)'
	 */
	public void testActivationDescStringStringMarshalledObjectBoolean() {
try{
	
	} catch (Throwable e) {
	    fail(msgNoException+e);
	}
	}

	/*
	 * Test method for 'java.rmi.activation.ActivationDesc.ActivationDesc(ActivationGroupID, String, String, MarshalledObject)'
	 */
	public void testActivationDescActivationGroupIDStringStringMarshalledObject() {
		try{
			
		} catch (Throwable e) {
		    fail(msgNoException+e);
		}
	}

	/*
	 * Test method for 'java.rmi.activation.ActivationDesc.ActivationDesc(ActivationGroupID, String, String, MarshalledObject, boolean)'
	 */
	public void testActivationDescActivationGroupIDStringStringMarshalledObjectBoolean() {

	}

	/*
	 * Test method for 'java.rmi.activation.ActivationDesc.getGroupID()'
	 */
	public void testGetGroupID() {

	}

	/*
	 * Test method for 'java.rmi.activation.ActivationDesc.getClassName()'
	 */
	public void testGetClassName() {

	}

	/*
	 * Test method for 'java.rmi.activation.ActivationDesc.getLocation()'
	 */
	public void testGetLocation() {

	}

	/*
	 * Test method for 'java.rmi.activation.ActivationDesc.getData()'
	 */
	public void testGetData() {

	}

	/*
	 * Test method for 'java.rmi.activation.ActivationDesc.getRestartMode()'
	 */
	public void testGetRestartMode() {

	}

	
	/*
	 * Test method for 'java.rmi.activation.ActivationDesc.hashCode()'
	 */
	public void testHashCode() {

	}

	/*
	 * Test method for 'java.rmi.activation.ActivationDesc.equals(Object)'
	 */
	public void testEquals() {

	}


}
