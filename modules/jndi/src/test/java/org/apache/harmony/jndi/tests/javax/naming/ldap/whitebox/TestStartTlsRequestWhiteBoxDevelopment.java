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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

import javax.naming.NamingException;
import javax.naming.ldap.StartTlsRequest;
import javax.naming.ldap.StartTlsResponse;

import junit.framework.TestCase;

/**
 * <p>This class test is made to test all cases of package where the coverage was not 100%.</p>
 * <p>We are gonna find here a lot cases from diferent classes, notice here that the conventional structure
 * followed in the rest of the proyect is applied  here.</p>
 * 
 * For this tests to run correctly, should not exist any META-INF/services/javax.naming.ldap.StartTlsRespose
 * file within any directory and/or jar file in the following path: user.dir, bootclasspath and java.home 
 * 
 */
public class TestStartTlsRequestWhiteBoxDevelopment extends TestCase {
	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestStartTlsRequestWhiteBoxDevelopment.class);
	}

	public TestStartTlsRequestWhiteBoxDevelopment(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testFile001(){
		File path = new File(System.getProperty("user.dir")+File.separator+"META-INF"+File.separator+"services"+File.separator+"javax.naming.ldap.StartTlsResponse");
		path.getParentFile().mkdirs();		
		PrintStream out=null;
		try {
			out= new PrintStream(new FileOutputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
		out.println("org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockStartTlsResponse");		
		StartTlsRequest str = new StartTlsRequest();
		String ID="1.3.6.1.4.1.1466.20037";
		int t1=210,t2=650;
		byte[] t0=ID.getBytes();
		try {
			StartTlsResponse x=(StartTlsResponse) str.createExtendedResponse(ID,t0,t1,t2);
			path.delete();
			assertEquals("org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockStartTlsResponse",x.getClass().getCanonicalName() );
		} catch (Throwable e){
			path.delete();
			fail("failed with: " + e);
		}
	}
	
	public void testFile002(){
		File path = new File(System.getProperty("user.dir")+File.separator+"META-INF"+File.separator+"services"+File.separator+"javax.naming.ldap.StartTlsResponse");		
		path.getParentFile().mkdirs();		
		PrintStream out=null;
		try {
			out= new PrintStream(new FileOutputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
		out.println("org.fitc.algo.que.no\n"+
				    "org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockStartTlsResponse");
		StartTlsRequest str = new StartTlsRequest();
		String ID="1.3.6.1.4.1.1466.20037";
		int t1=210,t2=650;
		byte[] t0=ID.getBytes();
		try {
			StartTlsResponse x=(StartTlsResponse) str.createExtendedResponse(ID,t0,t1,t2);
			path.delete();
			assertEquals("org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockStartTlsResponse",x.getClass().getCanonicalName());
		} catch (Throwable e){
			path.delete();
			fail("Failed with: "+ e);
		}		
	}
	public void testFile003(){
		File path = new File(System.getProperty("user.dir")+File.separator+"META-INF"+File.separator+"services"+File.separator+"javax.naming.ldap.StartTlsResponse");		
		path.getParentFile().mkdirs();		
		PrintStream out=null;
		try {
			out= new PrintStream(new FileOutputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
		out.println("org.fitc.algo.que.no\n");
				    
		StartTlsRequest str = new StartTlsRequest();
		String ID="1.3.6.1.4.1.1466.20037";
		int t1=210,t2=650;
		byte[] t0=ID.getBytes();
		try {
			StartTlsResponse x=(StartTlsResponse) str.createExtendedResponse(ID,t0,t1,t2);
			path.delete();
			fail("NamignException expected");
		}catch (NamingException e){
			path.delete();			
		}catch (Throwable e){
			path.delete();		
			e.printStackTrace();
			fail("Failed with: "+ e);
		}
	}
	public void testFile004(){
		StartTlsRequest str = new StartTlsRequest();
		String ID="1.3.6.1.4.1.1466.20037";
		int t1=210,t2=650;
		byte[] t0=ID.getBytes();
		try {
			StartTlsResponse x=(StartTlsResponse) str.createExtendedResponse(ID,t0,t1,t2);			
			fail("NamignException expected");
		}catch (NamingException e){	
		}catch (Throwable e){					
			e.printStackTrace();
			fail("Failed with: "+ e);
		}
	}
	
	public void testJar001(){
		File path = new File(System.getProperty("user.dir")+"/miarchivo.jar");
		FileOutputStream os=null;
		JarOutputStream jos=null;
		JarEntry je=new JarEntry("META-INF"+File.separator+"services"+File.separator+"javax.naming.ldap.StartTlsResponse");		
		try {
			os=new FileOutputStream(path);
			jos=new JarOutputStream(os);
			jos.putNextEntry(je);
			jos.write("org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockStartTlsResponse".getBytes());
			jos.flush();
			jos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StartTlsRequest str = new StartTlsRequest();
		String ID="1.3.6.1.4.1.1466.20037";
		int t1=210,t2=650;
		byte[] t0=ID.getBytes();
		try {
			StartTlsResponse x=(StartTlsResponse) str.createExtendedResponse(ID,t0,t1,t2);
			path.delete();
			assertEquals("org.apache.harmony.jndi.tests.javax.naming.spi.mock.ldap.MockStartTlsResponse",x.getClass().getCanonicalName() );
		} catch (Throwable e){
			path.delete();
			fail("failed with: " + e);
		}		
		path.delete();
	}
	
	
	public void testJar002(){
		File path = new File(System.getProperty("user.dir")+"/miarchivo.jar");
		FileOutputStream os=null;
		JarOutputStream jos=null;
		JarEntry je=new JarEntry("META-INF"+File.separator+"services"+File.separator+"javax.naming.ldap.StartTlsResponse");		
		try {
			os=new FileOutputStream(path);
			jos=new JarOutputStream(os);
			jos.putNextEntry(je);
			jos.write("org.fitc.algo.que.no\n".getBytes());
			jos.flush();
			jos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StartTlsRequest str = new StartTlsRequest();
		String ID="1.3.6.1.4.1.1466.20037";
		int t1=210,t2=650;
		byte[] t0=ID.getBytes();
		try {
			StartTlsResponse x=(StartTlsResponse) str.createExtendedResponse(ID,t0,t1,t2);
			path.delete();
			fail("NamignException expected");
		}catch (NamingException e){
			path.delete();			
		}catch (Throwable e){
			path.delete();		
			e.printStackTrace();
			fail("Failed with: "+ e);
		}
	}
}