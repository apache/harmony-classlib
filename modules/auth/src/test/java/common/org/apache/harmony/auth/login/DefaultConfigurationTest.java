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
* @author Maxim V. Makarov
* @version $Revision$
*/

package org.apache.harmony.auth.login;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Permission;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.AuthPermission;
import javax.security.auth.login.AppConfigurationEntry;

import junit.framework.TestCase;

import org.apache.harmony.auth.tests.support.TestUtils;
import org.apache.harmony.auth.login.DefaultConfiguration;


/**
 * Tests default configuration implementation.  
 */

public class DefaultConfigurationTest extends TestCase {

    private static final String LOGIN_CONFIG = "java.security.auth.login.config";
    
    static String outputPath = System.getProperty("RESOURCE_DIR", "test/common/unit");
    
    static String otherConfFile = outputPath + File.separator + "org" + 
        File.separator + "apache" + File.separator + "harmony" + 
        File.separator + "auth" + File.separator + "login" +
        File.separator + "auth.conf";

	private static File defaultConfFile;

	static AppConfigurationEntry[] ents = null;

	SecurityManager old = System.getSecurityManager();

	String oldp1 = null;
	String oldp2 = null;

	public void setUp() throws Exception {
		createConfFile();
		
		oldp1 = Security.getProperty("login.config.url.1");
		oldp2 = Security.getProperty("login.config.url.2");
	}

	public void tearDown() throws Exception {
		System.setSecurityManager(old);

		TestUtils.setSystemProperty("login.config.url.1", oldp1);
		TestUtils.setSystemProperty("login.config.url.2", oldp2);

		defaultConfFile.delete();
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(DefaultConfigurationTest.class);
	}
	
	/**
	 * loading a config file specified on the security property  
	 * using login.config.url.1
	 * XXX: load a default config file
	 */
	public static void testLoadConfigFile() throws Exception {
			DefaultConfiguration dc = null;
			dc = new DefaultConfiguration();
			assertNotNull(dc);
			ents = dc.getAppConfigurationEntry("Login1");
			assertNull(ents);
			ents = dc.getAppConfigurationEntry(null);
			assertNull(ents);
			Security.setProperty("login.config.url.1", "file:"
					+ defaultConfFile.getCanonicalPath());
			Security.setProperty("login.config.url.2", "file:"
					+ new File(otherConfFile).getCanonicalPath());

			dc = new DefaultConfiguration();
			ents = dc.getAppConfigurationEntry("LoginNew");
			assertNotNull(ents);
			assertEquals("com.intel.security.auth.module.LoginModule1", ents[0].getLoginModuleName());
			Map m = new HashMap();
			m.put("debug", "true");
			m.put("test", "false");
			assertEquals(m, ents[0].getOptions());
			assertEquals("LoginModuleControlFlag: optional", ents[0].getControlFlag().toString());

			ents = dc.getAppConfigurationEntry("Login1");
			assertNotNull(ents);
			for (int i = 0; i < ents.length; i++) {
				assertEquals("com.intel.security.auth.module.LoginModule1",
						ents[i].getLoginModuleName());
				m.clear();
				m.put("debug1", "true");
				m.put("test1", "false");
				assertEquals(m, ents[i].getOptions());
				assertEquals("LoginModuleControlFlag: required", ents[i]
						.getControlFlag().toString());
			}
			
			
			
	}
	/**
	 * loading a config file specified on the system property
	 * using -Djava.security.auth.login.config    
	 */
	public void testLoadConfigFile_1() throws IOException {
		
	    String oldp = System.getProperty(LOGIN_CONFIG);	
		try {
		System.setProperty(LOGIN_CONFIG, 
				new File(otherConfFile).getCanonicalPath());
		DefaultConfiguration dc = new DefaultConfiguration();
		assertNotNull(dc);
		
		ents = dc.getAppConfigurationEntry("Login2");
		assertNotNull(ents);
		ents = dc.getAppConfigurationEntry("other");
		assertNotNull(ents);
		ents = dc.getAppConfigurationEntry("Login1");
		assertNotNull(ents);
		Map m = new HashMap();
		for (int i = 0; i < ents.length; i++) {
			assertEquals("com.intel.security.auth.module.LoginModule1",
					ents[i].getLoginModuleName());
			m.clear();
			m.put("debug1", "true");
			m.put("test1", "false");
			assertEquals(m, ents[i].getOptions());
			assertEquals("LoginModuleControlFlag: required", ents[i]
					.getControlFlag().toString());
		}
		
		ents = dc.getAppConfigurationEntry("Login7");
		assertNotNull(ents);

		
		assertEquals("com.intel.security.auth.module.LoginModule1", ents[0].getLoginModuleName());
		assertEquals("com.intel.security.auth.module.LoginModule2", ents[1].getLoginModuleName());
		assertEquals("com.intel.security.auth.module.LoginModule3", ents[2].getLoginModuleName());
		assertEquals("com.intel.security.auth.module.LoginModule4",  ents[3].getLoginModuleName());
		
		assertEquals("LoginModuleControlFlag: required", ents[0].getControlFlag().toString());
		assertEquals("LoginModuleControlFlag: optional", ents[1].getControlFlag().toString());
		assertEquals("LoginModuleControlFlag: sufficient", ents[2].getControlFlag().toString());
		assertEquals("LoginModuleControlFlag: requisite", ents[3].getControlFlag().toString());
		
		m.clear();
		m.put("AAAA", "true");
		m.put("BBB", "false");
		assertEquals(m, ents[0].getOptions());
		m.clear();
		m.put("debug2", "true");
		assertEquals(m, ents[1].getOptions());
		m.clear();
		m.put("debug2", "false");
		assertEquals(m, ents[2].getOptions());
		m.clear();
		m.put("ticketCache", System.getProperty("user.home")+ File.separator+"tickets");
		m.put("useTicketCache", "true");
		assertEquals(m, ents[3].getOptions());

		} finally {
		    TestUtils.setSystemProperty(LOGIN_CONFIG, oldp);
		}
	}
	/**
	 * test of the refresh method
	 */
	public void testRefresh() throws IOException {
	    
	    String oldp = System.getProperty(LOGIN_CONFIG);
		try {
			System.setProperty(LOGIN_CONFIG, 
					new File(otherConfFile).getCanonicalPath());

		DefaultConfiguration dc = new DefaultConfiguration();
		MySecurityManager checker = new MySecurityManager(new AuthPermission(
				"refreshLoginConfiguration"), true);
		System.setSecurityManager(checker);
		dc.refresh();
		assertTrue(checker.checkAsserted);
		checker.reset();
		checker.enableAccess = false;
		try {
			dc.refresh();
			fail("No expected SecurityException");
		} catch (SecurityException ex) {
		}
		} finally {
		    TestUtils.setSystemProperty(LOGIN_CONFIG, oldp);
		}

	}

	private static void createConfFile() throws SecurityException, IOException {
		
		defaultConfFile = File.createTempFile(".java.login.config", null);

		String newConfFile = "LoginNew {\n com.intel.security.auth.module.LoginModule1 optional"
			+ " debug=\"true\" test=false;\n};";

		byte[] b = newConfFile.getBytes();

		OutputStream os = new FileOutputStream(defaultConfFile);
		for (int j = 0; j < b.length; j++) {
			os.write(b[j]);
		}
		os.flush();
		os.close();
	}

	/**
	 * Easy the SecurityManager class
	 * 
	 */

	class MySecurityManager extends SecurityManager {

		public boolean enableAccess;

		public Permission checkTarget;

		public boolean checkAsserted;

		public MySecurityManager(Permission target, boolean enable) {
			checkAsserted = false;
			checkTarget = target;
			enableAccess = enable;
		}

		public void checkPermission(Permission p) {
			if (p instanceof AuthPermission && checkTarget.equals(p)) {
				checkAsserted = true;
				if (!enableAccess) {
					throw new SecurityException();
				}
			}
		}

		public MySecurityManager reset() {
			checkAsserted = false;
			return this;
		}
	}
	
}
