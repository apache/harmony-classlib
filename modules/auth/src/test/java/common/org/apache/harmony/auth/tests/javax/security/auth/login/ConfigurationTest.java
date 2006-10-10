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

package org.apache.harmony.auth.tests.javax.security.auth.login;

import java.security.Permission;
import java.security.Security;

import javax.security.auth.AuthPermission;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;

import junit.framework.TestCase;

import org.apache.harmony.auth.tests.support.TestUtils;

import tests.support.resource.Support_Resources;

/**
 * Tests Configuration class
 */
public class ConfigurationTest extends TestCase {

    // system property to specify another login configuration file 
    private static final String AUTH_LOGIN_CONFIG = "java.security.auth.login.config";

    // security property to specify default configuration implementation 
    private static final String LOGIN_CONFIG_PROVIDER = "login.configuration.provider";

	/**
	 * Easy the SecurityManager class
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

	/**
	 * Ease the configuration class
	 */
	public static class ConfTestProvider extends Configuration {

		public AppConfigurationEntry[] getAppConfigurationEntry(
				String applicationName) {
			return null;
		}

		public void refresh() {
		}
	}

    // installed security manager 
    SecurityManager oldSM;

    // default implementation of Configuration class
    Configuration defaultConfig;

    // value of java.security.auth.login.config system property
    private String oldAuthConfig;

    @Override
    protected void setUp() {

        // point to some existing file to be read 
        String testConfig = Support_Resources
                .getAbsoluteResourcePath("auth.conf");
        oldAuthConfig = System.setProperty(AUTH_LOGIN_CONFIG, "=" + testConfig);

        defaultConfig = Configuration.getConfiguration();

        oldSM = System.getSecurityManager();
    }

    @Override
    protected void tearDown() {

        TestUtils.setSystemProperty(AUTH_LOGIN_CONFIG, oldAuthConfig);

        System.setSecurityManager(oldSM);
        Configuration.setConfiguration(defaultConfig);
    }

	/**
	 * Tests that setConfiguration() is properly secured via SecurityManager.
	 */
	public void testSetConfiguration() {
		MySecurityManager checker = new MySecurityManager(new AuthPermission(
				"setLoginConfiguration"), true);
		System.setSecurityManager(checker);
		Configuration custom = new ConfTestProvider();
		Configuration.setConfiguration(custom);
		assertTrue(checker.checkAsserted);
		assertSame(custom, Configuration.getConfiguration());

		checker.reset();
		checker.enableAccess = false;
		try {
			Configuration.setConfiguration(new ConfTestProvider());
			fail("No expected SecurityException");
		} catch (SecurityException ex) {
		}
	}

	/**
	 * Tests that getConfiguration() is properly secured via SecurityManager.
	 */
	public void testGetConfiguration() {
		Configuration.setConfiguration(new ConfTestProvider());
		MySecurityManager checker = new MySecurityManager(new AuthPermission(
				"getLoginConfiguration"), true);
		System.setSecurityManager(checker);
		Configuration.getConfiguration();
		assertTrue(checker.checkAsserted);
		checker.reset();
		checker.enableAccess = false;
		try {
			Configuration.getConfiguration();
			fail("No expected SecurityException");
		} catch (SecurityException ex) {
		}
	}

	/**
	 * Tests loading of a default provider, both valid and invalid class
	 * references.
	 */
    public void test_loadDefaultProvider() {

        String oldProvider = Security.getProperty(LOGIN_CONFIG_PROVIDER);
        try {
            // test: loading custom test provider
            Security.setProperty(LOGIN_CONFIG_PROVIDER, ConfTestProvider.class
                    .getName());
            Configuration.setConfiguration(null); // reset config
            assertEquals(ConfTestProvider.class, Configuration
                    .getConfiguration().getClass());

            // test: loading absent class as test provider
            Security.setProperty(LOGIN_CONFIG_PROVIDER, "ThereIsNoSuchClass");
            Configuration.setConfiguration(null); // reset config
            try {
                Configuration.getConfiguration();
                fail("No SecurityException on failed provider");
            } catch (SecurityException ok) {
                assertTrue(ok.getCause() instanceof ClassNotFoundException);
            }

            // test: loading wrong class as test provider
            // a name of this unit test is used as config provider
            Security.setProperty(LOGIN_CONFIG_PROVIDER, this.getClass()
                    .getName());
            Configuration.setConfiguration(null);// reset config
            try {
                Configuration.getConfiguration();
                fail("No expected ClassCastException");
            } catch (ClassCastException ok) {
            }

        } finally {
            //TODO reset security property if oldProvider==null
            Security.setProperty(LOGIN_CONFIG_PROVIDER,
                    (oldProvider == null) ? "" : oldProvider);
        }
    }
    
    public static void main(String[] args) {
        junit.textui.TestRunner.run(ConfigurationTest.class);
    }
}
