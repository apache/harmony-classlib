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
import java.util.Properties;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.activation.ActivationDesc;
import java.rmi.activation.ActivationException;
import java.rmi.activation.ActivationGroup;
import java.rmi.activation.ActivationGroupDesc;
import java.rmi.activation.ActivationGroupID;
import java.rmi.activation.ActivationID;
import java.rmi.activation.ActivationInstantiator;
import java.rmi.activation.ActivationMonitor;
import java.rmi.activation.ActivationSystem;
import java.rmi.activation.UnknownGroupException;
import java.rmi.activation.UnknownObjectException;
import java.security.Permission;

class MyActivationSystem implements ActivationSystem{
	
	
	public ActivationID registerObject(ActivationDesc desc) throws ActivationException, UnknownGroupException, RemoteException {
		return null;  
		
	}



	public ActivationGroupID registerGroup(ActivationGroupDesc desc) throws ActivationException, RemoteException {
		

		
		
		return registerGroup(desc);
		
		
	}

	public ActivationMonitor activeGroup(ActivationGroupID arg0, ActivationInstantiator arg1, long arg2) throws UnknownGroupException, ActivationException, RemoteException {

		return null;
	}

	public void unregisterGroup(ActivationGroupID arg0) throws ActivationException, UnknownGroupException, RemoteException {

		
	}

	public void shutdown() throws RemoteException {

		
	}

	public ActivationDesc setActivationDesc(ActivationID arg0, ActivationDesc arg1) throws ActivationException, UnknownObjectException, UnknownGroupException, RemoteException {

		return null;
	}

	public ActivationGroupDesc setActivationGroupDesc(ActivationGroupID arg0, ActivationGroupDesc arg1) throws ActivationException, UnknownGroupException, RemoteException {

		return null;
	}

	public ActivationDesc getActivationDesc(ActivationID arg0) throws ActivationException, UnknownObjectException, RemoteException {

		return null;
	}

	public ActivationGroupDesc getActivationGroupDesc(ActivationGroupID arg0) throws ActivationException, UnknownGroupException, RemoteException {

		return null;
	}



	public void unregisterObject(ActivationID arg0) throws ActivationException, UnknownObjectException, RemoteException {

		
	}
}



public class TestActivationGroup extends TestCase implements Messages{


	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestActivationGroup.class);
	}

	public TestActivationGroup(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();

      //  ac = new myactivator();
      //  at = new ActivationID(ac);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * Test method for 'java.rmi.activation.ActivationGroup.inactiveObject(ActivationID)'
	 */
	public final void testInactiveObject() {

	}

	/*
	 * Test method for 'java.rmi.activation.ActivationGroup.activeObject(ActivationID, Remote)'
	 */
	public final void testActiveObjectActivationIDRemote() {

	}

	/*
	 * Test method for 'java.rmi.activation.ActivationGroup.createGroup(ActivationGroupID, ActivationGroupDesc, long)'
	 */

	public final void testCreateGroup001() {
		try{
			
		ActivationGroup.createGroup(null, null, 0);
        fail("Shoud raise ActivationException");
        } catch (ActivationException e) {
	} catch (Throwable e) {
	    fail(msgNoException+e);
	}
	}
	
	public final void testCreateGroup002() {
		try{
			 System.setSecurityManager(new RMISecurityManager());
		      Properties policyFileLocation = new Properties(); 
		      policyFileLocation.put("java.security.policy", 
		         "c:/policy.txt");
		      ActivationGroupDesc.CommandEnvironment ace = null; 
		      ActivationGroupDesc exampleGroup = new 
		      ActivationGroupDesc(policyFileLocation, ace);
			Properties p=new Properties();
			p.setProperty("g43tmn","grew");					
			ActivationGroupID agi=null;			
		ActivationGroup.createGroup(agi, exampleGroup, 0);
        fail("Shoud raise SecurityException");
        } catch (SecurityException e) {
	} catch (Throwable e) {
	    fail(msgNoException+e);
	}
	}
	/*
	public final void testCreateGroup003() {
		SecurityManager smOld = System.getSecurityManager();
		try{
			
			
			
	        
	        
	        	System.setSecurityManager(new RMISecurityManager());
	            SecurityManager sm = new SecurityManager() {
	                boolean allow = false;
	                public void checkPermission(Permission perm) {
	                    if (!allow) {
	                        allow= true;
	                        throw new SecurityException("No, No, No, you can't do that.");
	                    }
	                }
	            };
	      
	        System.setSecurityManager(sm);
	        
			Properties props = new Properties(); 
			props.put("java.security.policy", "c:/policy.txt"); 

					      ActivationGroupDesc.CommandEnvironment ace = null;
					      
		      ActivationGroupDesc exampleGroup = new
		      ActivationGroupDesc(props, ace);
			ActivationGroupID agi=null;		
			System.setProperty ("java.security.policy", "c:/Documents and Settings/Intel/java.policy");
			ActivationGroupID id  
			   = ActivationGroup.getSystem().registerGroup(exampleGroup); 
	//	ActivationGroup.createGroup(agi, exampleGroup, 0);
	} catch (Throwable e) {
	    fail(msgNoException+e);
    } finally {
        System.setSecurityManager(smOld);
    }   
	}
*/
/*

	public final void testCreateGroup001() {
	try{
		ActivationGroupID id= new ActivationGroupID(null);
        ActivationGroupDesc desc= new ActivationGroupDesc(null, null);
        long incarnation=0;
		ActivationGroup.createGroup(id, desc, incarnation);
        fail("Should raise ActivationException");
    } catch (ActivationException e) {
    } catch (Throwable e) {
        fail("Failed with:" + e);
    }    
}

*//*
	public final void testCreateGroup004() {
		try{	


//	Server server;
//	ActivationDesc desc;
//	String codebase = "http://zaphod/codebase/";
//
//	MarshalledObject data = new MarshalledObject("some data");
//	ActivationDesc desc3 = new ActivationDesc( "examples.ServerImpl", codebase, data);
//	server = (Server)Activatable.register(desc3);
		} catch (Throwable e) {
		    fail(msgNoException+e);
		}
		}*/

/*
	
	public final void testCreateGroup002() {
		try{
			ActivationGroupID id= new ActivationGroupID(null);
			String cmdpath="greahtrjukeiul";
			String[] argv={"hbteqh642ukju6e j 3864","u65o0986p''p´80","r4 3t vu76i8"," m0m9´09 543 2 "};
	        ActivationGroupDesc desc= new ActivationGroupDesc(new Properties() , new ActivationGroupDesc.CommandEnvironment(cmdpath, argv) );
	        long incarnation=1555;
			ActivationGroup.createGroup(id, desc, incarnation);
	        fail("Should raise ActivationException");
	    } catch (ActivationException e) {
	    } catch (Throwable e) {
	        fail("Failed with:" + e);
	    }    
	}
*/
	/*
	 * Test method for 'java.rmi.activation.ActivationGroup.currentGroupID()'
	 */
	public final void testCurrentGroupID() {
		try{

	} catch (Throwable e) {
	    fail(msgNoException+e);
	}
	}

	/*
	 * Test method for 'java.rmi.activation.ActivationGroup.setSystem(ActivationSystem)'
	 */
	public final void testSetSystem() {

	}

	/*
	 * Test method for 'java.rmi.activation.ActivationGroup.getSystem()'
	 */
	public final void testGetSystem001() {
        SecurityManager smOld = System.getSecurityManager();
        try{
            SecurityManager sm = new SecurityManager() {
                boolean allow = false;
                public void checkPermission(Permission perm) {
                    if (!allow) {
                        allow= true;
                      //  throw new SecurityException("No, No, No, you can't do that.");
                    }
                }
            };
            System.setSecurityManager(sm);		
			ActivationGroup.getSystem();
		//} catch (ActivationException e) {				
		} catch (Throwable e) {
		    fail(msgNoException+e);
		
    } finally {
        System.setSecurityManager(smOld);
    }        
}		
}
