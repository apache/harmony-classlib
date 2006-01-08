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
* @author Maxim V. Makarov
* @version $Revision$
*/

package javax.security.auth.login;

import java.security.Permission;
import java.security.Security;

import javax.security.auth.AuthPermission;
import com.openintel.drl.security.test.PerformanceTest;

/**
 * Tests Configuration class
 */

public class ConfigurationTest extends PerformanceTest {

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

	/**
	 * Ease the configuration class
	 * 
	 */
	public static class ConfTestProvider extends Configuration {

		public AppConfigurationEntry[] getAppConfigurationEntry(
				String applicationName) {
			return null;
		}

		public void refresh() {
		}
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(ConfigurationTest.class);
	}

	SecurityManager old = System.getSecurityManager();

	Configuration oldConfiguration = Configuration.getConfiguration();

	public void tearDown() {
		System.setSecurityManager(old);
		Configuration.setConfiguration(oldConfiguration);
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
	public void testLoadDefaultProvider() {
		String Configuration_PROVIDER = "login.configuration.provider";
		String oldProvider = Security.getProperty(Configuration_PROVIDER);
		try {
			Security.setProperty(Configuration_PROVIDER, ConfTestProvider.class.getName());
			Configuration.setConfiguration(null);
			Configuration p = Configuration.getConfiguration();
			assertNotNull(p);
			assertEquals(ConfTestProvider.class.getName(), p.getClass().getName());

			Security.setProperty(Configuration_PROVIDER, "a.b.c.D");
			Configuration.setConfiguration(null);
            try {
                p = Configuration.getConfiguration();
                fail("No SecurityException on failed provider");
            } catch (Exception ok) {
                assertTrue(ok.getCause() instanceof ClassNotFoundException);
            }

		} finally {
			Security.setProperty(Configuration_PROVIDER,
					(oldProvider == null) ? "" : oldProvider);
		}
	}
}