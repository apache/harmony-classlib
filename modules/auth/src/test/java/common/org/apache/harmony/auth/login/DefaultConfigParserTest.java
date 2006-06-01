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

package org.apache.harmony.auth.login;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;

import javax.security.auth.login.AppConfigurationEntry;

import junit.framework.TestCase;
import org.apache.harmony.auth.login.DefaultConfigurationParser;
import org.apache.harmony.auth.login.DefaultConfigurationParser.InvalidFormatException;


/**
 * Tests parser implementation for default configuration.
 */

public class DefaultConfigParserTest extends TestCase {

    static String outputPath = System.getProperty("RESOURCE_DIR", "test/common/unit");
    
    static String fconf = outputPath + File.separator + "org" + 
        File.separator + "apache" + File.separator + "harmony" + 
        File.separator + "auth" + File.separator + "login" +
        File.separator + "auth.conf";
    
	Properties p;

	URL url;
	
	LinkedList entriesList;

	public static void main(String[] args) {
		junit.textui.TestRunner.run(DefaultConfigParserTest.class);
	}

	/**
	 * Constructor for DefaultConfigParserTest.
	 * 
	 * @param name
	 */
	public DefaultConfigParserTest(String name) {
		super(name);
	}

	public void setUp() throws Exception {
		p = System.getProperties();
		File f = new File(fconf);
		try {
			url = f.toURL();
		} catch (MalformedURLException e) {
		}
	}

	/**
	 *  test on existance of a application name  
	 * @throws Exception
	 */
	public final void testConfigParser_01() throws Exception {
			Map config = null;
			config = DefaultConfigurationParser.configParser(url, p, new HashMap());
			entriesList = (LinkedList) config.get("Login1");
			assertNotNull(entriesList);
			entriesList = (LinkedList) config.get("other");
			assertNotNull(entriesList);
			entriesList = (LinkedList) config.get("Login2");
			assertNotNull(entriesList);
			entriesList = (LinkedList) config.get("Login3");
			assertNotNull(entriesList);
			entriesList = (LinkedList) config.get("Login4");
			assertNotNull(entriesList);
			entriesList = (LinkedList) config.get("Login5");
			assertNotNull(entriesList);
			entriesList = (LinkedList) config.get("Login6");
			assertNotNull(entriesList);
			entriesList = (LinkedList) config.get("Login7");
			assertNotNull(entriesList);
			entriesList = (LinkedList) config.get("Login8");
			assertNull(entriesList);
	}

	/**
	 * test on correct initialization of a config file 
	 * @throws Exception
	 */
	public final void testConfigParser_02() throws Exception {

			Map config = DefaultConfigurationParser.configParser(url, p, new HashMap());
			entriesList = (LinkedList) config.get("Login1");

			if (entriesList == null || entriesList.size() == 0) {
			    fail("Unable to read configuration");
			}

			Iterator i = entriesList.iterator();
			while (i.hasNext()) {
				AppConfigurationEntry e = (AppConfigurationEntry) i.next();
				assertEquals("com.intel.security.auth.module.LoginModule1", e
						.getLoginModuleName());
				assertEquals("LoginModuleControlFlag: required", e
						.getControlFlag().toString());
				assertEquals(
						AppConfigurationEntry.LoginModuleControlFlag.REQUIRED,
						e.getControlFlag());
				Map m = new HashMap();
				m.put("debug1", "true");
				m.put("test1", "false");
				assertEquals(m, e.getOptions());
			}
	}

	/**
	 * test on invalid initialization of a config file
	 * @throws Exception
	 */
	public final void testNegConfig() throws Exception {
		String[] str = {
				
				"Login ERR {\n com.intel.security.auth.module.LoginModule required debug=\"true\" test=false;\n};",
				"{\n com.intel.security.auth.module.LoginModule required debug=\"true\" test=false;\n};",
				"Login \n com.intel.security.auth.module.LoginModule required debug=\"true\" test=false;\n};",
				"Login {\n com.intel.security.auth.module.LoginModule required debug=\"true\" test=false;\n}",
				"Login {\n com.intel.security.auth.module.LoginModule required debug=\"true\" test=false;\n;",
				"Login {\n com.intel.security.auth.module.LoginModule required debug=\"true\" test=false\n};",
				"Login {\n com.intel.security.auth.module.LoginModule required debug=\"true\" test=;\n};",
				"Login {\n com.intel.security.auth.module.LoginModule required debug=\"true\" =false;\n};",
				"Login {\n com.intel.security.auth.module.LoginModule required debug=\"true test=false;\n};",
				"Login {\n com.intel.security.auth.module.LoginModule required debug=true\" test=false;\n};",
				"Login {\n com.intel.security.auth.module.LoginModule required debug\n};",
				"Login {\n com.intel.security.auth.module.LoginModule INVALID \n};",
				"Login {\n required \n};",
				"Login {\n com.intel.security.auth.module.LoginModule required\n}",
				"Login {\n com.intel.security.auth.module.LoginModule1 required debug=\"true\";\n};\n"
				+ "Login {\n com.intel.security.auth.module.LoginModule2 required debug=\"true\" test=false;\n};\n",
				};

		for (int i = 0; i < str.length; i++) {
			File file = File.createTempFile("auth_neg_", ".conf");

			byte[] b = str[i].getBytes();
			OutputStream os = new FileOutputStream(file);

			for (int j = 0; j < b.length; j++) {
				os.write(b[j]);
			}

			os.flush();
			os.close();
			try {
				DefaultConfigurationParser.configParser(file.toURL(), p, new HashMap());
				fail("no expected InvalidFormatException" + i);
			} catch (NullPointerException e) {
				System.out.println(file.getCanonicalPath());
			} catch (DefaultConfigurationParser.InvalidFormatException e) {
			} finally {		
				file.delete();
			}
		}
	}
	
	public void testException() {
		InvalidFormatException ife = new InvalidFormatException("message");
		assertEquals("message", ife.getMessage());
		try {
            throw ife;
        }catch (Exception e){
            assertTrue(ife.equals(e));
        }
        DefaultConfigurationParser.InvalidFormatException ife1 = new DefaultConfigurationParser.InvalidFormatException("message");
		assertEquals("message", ife1.getMessage());
	}
	
}
