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

import java.io.File;
import java.security.Security;
import java.util.Properties;

import javax.security.auth.AuthPermission;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;

import junit.framework.TestCase;

import org.apache.harmony.auth.tests.support.SecurityChecker;
import org.apache.harmony.auth.tests.support.TestUtils;

import tests.support.Support_Exec;
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
        SecurityChecker checker = new SecurityChecker(new AuthPermission(
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
        SecurityChecker checker = new SecurityChecker(new AuthPermission(
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
            Configuration.setConfiguration(null); // reset default config
            assertEquals(ConfTestProvider.class, Configuration
                    .getConfiguration().getClass());

            // test: loading absent class as test provider
            Security.setProperty(LOGIN_CONFIG_PROVIDER, "ThereIsNoSuchClass");
            Configuration.setConfiguration(null); // reset default config
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
            Configuration.setConfiguration(null);// reset default config
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

    /**
     * Tests reading config files by default provider
     */
    public void test_defaultProvider() throws Exception {

        // test: there are no config files to be read
        // Regression for HARMONY-1715

        // no login.config.url.N security properties should be set
        String javaSecurityFile = TestUtils
                .createJavaPropertiesFile(new Properties());

        // tmp user home to avoid presence of ${user.home}/.java.login.config
        String tmpUserHome = System.getProperty("java.io.tmpdir")
                + File.separatorChar + "tmpUserHomeForConfigurationTest";
        File dir = new File(tmpUserHome);
        if (!dir.exists()) {
            dir.mkdirs();
            dir.deleteOnExit();
        }
        String javaLoginConfig = tmpUserHome + File.separatorChar
                + ".java.login.config";
        assertFalse("There should be no login config file: " + javaLoginConfig,
                new File(javaLoginConfig).exists());

        String[] arg = new String[] { "-Duser.home=" + tmpUserHome,
                "-Djava.security.properties=" + javaSecurityFile,
                NoConfigFileToBeRead.class.getName() };

        Support_Exec.execJava(arg, null, true);
    }

    public static class NoConfigFileToBeRead {

        // the test is based on assumption that security properties 
        // login.config.url.N are unset and there is no file
        // ${user.home}/.java.login.config
        public static void main(String[] args) {

            //reset path to alternative configuration file
            TestUtils.setSystemProperty(AUTH_LOGIN_CONFIG, null);

            Configuration.setConfiguration(null); // reset default config
            try {
                Configuration.getConfiguration();
                TestCase.fail("No expected SecurityException");
            } catch (SecurityException e) {
            }
        }
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(ConfigurationTest.class);
    }
}
