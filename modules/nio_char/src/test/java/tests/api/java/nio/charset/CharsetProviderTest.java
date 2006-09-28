/* Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tests.api.java.nio.charset;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import tests.api.java.nio.charset.CharsetTest.MockCharset;
import tests.api.java.nio.charset.CharsetTest.MockSecurityManager;
import junit.framework.TestCase;

/**
 * Test charset providers managed by Charset.
 */
public class CharsetProviderTest extends TestCase {

	// need to be modified, e.g., read from system property
	static String PROP_CONFIG_FILE1 = "clear.tests.cp1";

	static String PROP_CONFIG_FILE2 = "clear.tests.cp2";

	static String CONFIG_FILE1 = null;

	static String CONFIG_FILE2 = null;

	static MockCharset charset1 = new MockCharset("mockCharset00",
			new String[] { "mockCharset01", "mockCharset02" });

	static MockCharset charset2 = new MockCharset("mockCharset10",
			new String[] { "mockCharset11", "mockCharset12" });

	/**
	 * @param arg0
	 */
	public CharsetProviderTest(String arg0) {
		super(arg0);
		if (null == System.getProperty(PROP_CONFIG_FILE1)
				|| null == System.getProperty(PROP_CONFIG_FILE2)) {
			throw new RuntimeException(
					"Please set system property clear.tests.cp1 and clear.tests.cp2, i.e. two classpaths in order to run this test suite.");
		}
		String sep = System.getProperty("file.separator");

		CONFIG_FILE1 = System.getProperty(PROP_CONFIG_FILE1);
		if (!CONFIG_FILE1.endsWith(sep)) {
			CONFIG_FILE1 += sep;
		}
		CONFIG_FILE1 += "META-INF" + sep + "services" + sep
				+ "java.nio.charset.spi.CharsetProvider";
		CONFIG_FILE2 = System.getProperty(PROP_CONFIG_FILE2);
		if (!CONFIG_FILE2.endsWith(sep)) {
			CONFIG_FILE2 += sep;
		}
		CONFIG_FILE2 += "META-INF" + sep + "services" + sep
				+ "java.nio.charset.spi.CharsetProvider";
	}

	/*
	 * Write the string to the config file.
	 */
	private void setupFile(String path, String content) throws Exception {
		String sep = System.getProperty("file.separator");
		int sepIndex = path.lastIndexOf(sep);
		File f = new File(path.substring(0, sepIndex));
		f.mkdirs();

		FileOutputStream fos = new FileOutputStream(path);
		OutputStreamWriter writer = new OutputStreamWriter(fos);// , "UTF-8");
		try {
			writer.write(content);
		} finally {
			writer.close();
		}
	}

	/*
	 * Write the string to the config file.
	 */
	private void cleanupFile(String path) throws Exception {
		File f = new File(path);
		f.delete();
	}

	/*
	 * Test the method isSupported(String) with charset supported by some
	 * providers (multiple).
	 */
	public void testIsSupported_NormalProvider() throws Exception {
		try {
			assertFalse(Charset.isSupported("mockCharset00"));
			assertFalse(Charset.isSupported("mockCharset01"));
			assertFalse(Charset.isSupported("mockCharset02"));

			assertFalse(Charset.isSupported("mockCharset10"));
			assertFalse(Charset.isSupported("mockCharset11"));
			assertFalse(Charset.isSupported("mockCharset12"));

			StringBuffer sb = new StringBuffer();
			sb.append("#comment\r");
			sb.append("\n");
			sb.append("\r\n");
			sb
					.append(" \ttests.api.java.nio.charset.CharsetTest$MockCharsetProvider \t\n\r");
			sb
					.append(" \ttests.api.java.nio.charset.CharsetTest$MockCharsetProvider \t");
			setupFile(CONFIG_FILE1, sb.toString());

			sb = new StringBuffer();
			sb.append(" #comment\r");
			sb.append("\n");
			sb.append("\r\n");
			sb
					.append(" \ttests.api.java.nio.charset.CharsetTest$MockCharsetProvider2 \t\n\r");
			setupFile(CONFIG_FILE2, sb.toString());

			assertTrue(Charset.isSupported("mockCharset00"));
			assertTrue(Charset.isSupported("mockCharset01"));
			assertTrue(Charset.isSupported("mockCharset02"));
			assertTrue(Charset.isSupported("MOCKCharset00"));
			assertTrue(Charset.isSupported("MOCKCharset01"));
			assertTrue(Charset.isSupported("MOCKCharset02"));

			assertTrue(Charset.isSupported("mockCharset10"));
			// ignore case problem in mock, intended
			assertFalse(Charset.isSupported("mockCharset11"));
			assertTrue(Charset.isSupported("mockCharset12"));
			assertTrue(Charset.isSupported("MOCKCharset10"));
			// intended case problem in mock
			assertFalse(Charset.isSupported("MOCKCharset11"));
			assertTrue(Charset.isSupported("MOCKCharset12"));

			// cleanupFile(CONFIG_FILE1);
			// assertFalse(Charset.isSupported("mockCharset00"));
			// assertFalse(Charset.isSupported("mockCharset01"));
			// assertTrue(Charset.isSupported("mockCharset02"));
		} finally {
			cleanupFile(CONFIG_FILE1);
			cleanupFile(CONFIG_FILE2);
		}
	}

	/*
	 * Test the method isSupported(String) when the configuration file contains
	 * a non-existing class name.
	 */
	public void testIsSupported_NonExistingClass() throws Exception {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("impossible\r");
			setupFile(CONFIG_FILE1, sb.toString());

			Charset.isSupported("impossible");
			fail("Should throw Error!");
		} catch (Error e) {
			// expected
		} finally {
			cleanupFile(CONFIG_FILE1);
		}
	}

	/*
	 * Test the method isSupported(String) when the configuration file contains
	 * a non-CharsetProvider class name.
	 */
	public void testIsSupported_NotCharsetProviderClass() throws Exception {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("java.lang.String\r");
			setupFile(CONFIG_FILE1, sb.toString());

			Charset.isSupported("impossible");
			fail("Should throw ClassCastException!");
		} catch (ClassCastException e) {
			// expected
		} finally {
			cleanupFile(CONFIG_FILE1);
		}
	}

	/*
	 * Test the method isSupported(String) with insufficient prililege to use
	 * charset provider.
	 */
	public void testIsSupported_InsufficientPrililege() throws Exception {
		SecurityManager oldMan = System.getSecurityManager();
		System.setSecurityManager(new MockSecurityManager());
		try {
			Charset.isSupported("UTF-8");

			try {
				StringBuffer sb = new StringBuffer();
				sb
						.append("tests.api.java.nio.charset.CharsetTest$MockCharsetProvider2\r");
				setupFile(CONFIG_FILE1, sb.toString());

				Charset.isSupported("gb180300000");
				// fail("Should throw SecurityException!");
			} catch (SecurityException e) {
				// expected
			} finally {
				cleanupFile(CONFIG_FILE1);
			}
		} finally {
			System.setSecurityManager(oldMan);
		}
	}

	/*
	 * Test the method forName(String) with charset supported by some providers
	 * (multiple).
	 */
	public void testForName_NormalProvider() throws Exception {
		try {
			try {
				Charset.forName("mockCharset00");
				fail("Should throw UnsupportedCharsetException!");
			} catch (UnsupportedCharsetException e) {
				// expected
			}
			try {
				Charset.forName("mockCharset01");
				fail("Should throw UnsupportedCharsetException!");
			} catch (UnsupportedCharsetException e) {
				// expected
			}
			try {
				Charset.forName("mockCharset02");
				fail("Should throw UnsupportedCharsetException!");
			} catch (UnsupportedCharsetException e) {
				// expected
			}
			try {
				Charset.forName("mockCharset10");
				fail("Should throw UnsupportedCharsetException!");
			} catch (UnsupportedCharsetException e) {
				// expected
			}
			try {
				Charset.forName("mockCharset11");
				fail("Should throw UnsupportedCharsetException!");
			} catch (UnsupportedCharsetException e) {
				// expected
			}

			// try {
			// Charset.forName("mockCharset12");
			// fail("Should throw UnsupportedCharsetException!");
			// } catch (UnsupportedCharsetException e) {
			// // expected
			// }

			StringBuffer sb = new StringBuffer();
			sb.append("#comment\r");
			sb.append("\n");
			sb.append("\r\n");
			sb
					.append(" \ttests.api.java.nio.charset.CharsetTest$MockCharsetProvider \t\n\r");
			sb
					.append(" \ttests.api.java.nio.charset.CharsetTest$MockCharsetProvider \t");
			setupFile(CONFIG_FILE1, sb.toString());

			sb = new StringBuffer();
			sb.append("#comment\r");
			sb.append("\n");
			sb.append("\r\n");
			sb
					.append(" \ttests.api.java.nio.charset.CharsetTest$MockCharsetProvider2 \t\n\r");
			setupFile(CONFIG_FILE2, sb.toString());

			assertTrue(Charset.forName("mockCharset00") instanceof MockCharset);
			assertTrue(Charset.forName("mockCharset01") instanceof MockCharset);
			assertTrue(Charset.forName("mockCharset02") instanceof MockCharset);

			assertTrue(Charset.forName("mockCharset10") == charset2);
			// intended case problem in mock
			try {
				Charset.forName("mockCharset11");
				fail("Should throw UnsupportedCharsetException!");
			} catch (UnsupportedCharsetException e) {
				// expected
			}
			assertTrue(Charset.forName("mockCharset12") == charset2);

			cleanupFile(CONFIG_FILE1);
			try {
				Charset.forName("mockCharset00");
				fail("Should throw UnsupportedCharsetException!");
			} catch (UnsupportedCharsetException e) {
				// expected
			}
			try {
				Charset.forName("mockCharset01");
				fail("Should throw UnsupportedCharsetException!");
			} catch (UnsupportedCharsetException e) {
				// expected
			}
			try {
				Charset.forName("mockCharset02");
				fail("Should throw UnsupportedCharsetException!");
			} catch (UnsupportedCharsetException e) {
				// expected
			}
		} finally {
			cleanupFile(CONFIG_FILE1);
			cleanupFile(CONFIG_FILE2);
		}
	}

	/*
	 * Test the method forName(String) when the charset provider supports a
	 * built-in charset.
	 */
	public void testForName_DuplicateWithBuiltInCharset() throws Exception {
		try {
			StringBuffer sb = new StringBuffer();
			sb
					.append("tests.api.java.nio.charset.CharsetTest$MockCharsetProvider3\r");
			setupFile(CONFIG_FILE1, sb.toString());

			assertFalse(Charset.forName("us-ascii") instanceof MockCharset);
			assertFalse(Charset.availableCharsets().get("us-ascii") instanceof MockCharset);
		} finally {
			cleanupFile(CONFIG_FILE1);
		}
	}

	/*
	 * Test the method forName(String) when the configuration file contains a
	 * non-existing class name.
	 */
	public void testForName_NonExistingClass() throws Exception {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("impossible\r");
			setupFile(CONFIG_FILE1, sb.toString());

			Charset.forName("impossible");
			fail("Should throw Error!");
		} catch (Error e) {
			// expected
		} finally {
			cleanupFile(CONFIG_FILE1);
		}
	}

	/*
	 * Test the method forName(String) when the configuration file contains a
	 * non-CharsetProvider class name.
	 */
	public void testForName_NotCharsetProviderClass() throws Exception {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("java.lang.String\r");
			setupFile(CONFIG_FILE1, sb.toString());

			Charset.forName("impossible");
			fail("Should throw ClassCastException!");
		} catch (ClassCastException e) {
			// expected
		} finally {
			cleanupFile(CONFIG_FILE1);
		}
	}

	/*
	 * Test the method availableCharsets() with charset supported by some
	 * providers (multiple).
	 */
	public void testAvailableCharsets_NormalProvider() throws Exception {
		try {
			assertFalse(Charset.availableCharsets()
					.containsKey("mockCharset00"));
			assertFalse(Charset.availableCharsets()
					.containsKey("mockCharset01"));
			assertFalse(Charset.availableCharsets()
					.containsKey("mockCharset02"));

			assertFalse(Charset.availableCharsets()
					.containsKey("mockCharset10"));
			assertFalse(Charset.availableCharsets()
					.containsKey("mockCharset11"));
			assertFalse(Charset.availableCharsets()
					.containsKey("mockCharset12"));

			StringBuffer sb = new StringBuffer();
			sb.append("#comment\r");
			sb.append("\n");
			sb.append("\r\n");
			sb
					.append(" \ttests.api.java.nio.charset.CharsetTest$MockCharsetProvider \t\n\r");
			sb
					.append(" \ttests.api.java.nio.charset.CharsetTest$MockCharsetProvider \t");
			setupFile(CONFIG_FILE1, sb.toString());

			sb = new StringBuffer();
			sb.append("#comment\r");
			sb.append("\n");
			sb.append("\r\n");
			sb
					.append(" \ttests.api.java.nio.charset.CharsetTest$MockCharsetProvider2 \t\n\r");
			setupFile(CONFIG_FILE2, sb.toString());

			assertTrue(Charset.availableCharsets().containsKey("mockCharset00"));
			assertTrue(Charset.availableCharsets().containsKey("MOCKCharset00"));
			assertTrue(Charset.availableCharsets().get("mockCharset00") instanceof MockCharset);
			assertTrue(Charset.availableCharsets().get("MOCKCharset00") instanceof MockCharset);
			assertFalse(Charset.availableCharsets()
					.containsKey("mockCharset01"));
			assertFalse(Charset.availableCharsets()
					.containsKey("mockCharset02"));

			assertTrue(Charset.availableCharsets().get("mockCharset10") == charset2);
			assertTrue(Charset.availableCharsets().get("MOCKCharset10") == charset2);
			assertFalse(Charset.availableCharsets()
					.containsKey("mockCharset11"));
			assertFalse(Charset.availableCharsets()
					.containsKey("mockCharset12"));

			// delete one config file
			cleanupFile(CONFIG_FILE1);
			assertFalse(Charset.availableCharsets()
					.containsKey("mockCharset00"));
			assertFalse(Charset.availableCharsets()
					.containsKey("mockCharset01"));
			assertFalse(Charset.availableCharsets()
					.containsKey("mockCharset02"));
			assertTrue(Charset.availableCharsets().containsKey("mockCharset10"));
			assertTrue(Charset.availableCharsets().containsKey("MOCKCharset10"));
			assertTrue(Charset.availableCharsets().get("mockCharset10") == charset2);
			assertFalse(Charset.availableCharsets()
					.containsKey("mockCharset11"));
			assertFalse(Charset.availableCharsets()
					.containsKey("mockCharset12"));
		} finally {
			cleanupFile(CONFIG_FILE1);
			cleanupFile(CONFIG_FILE2);
		}
	}

	/*
	 * Test the method availableCharsets(String) when the configuration file
	 * contains a non-existing class name.
	 */
	public void testAvailableCharsets_NonExistingClass() throws Exception {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("impossible\r");
			setupFile(CONFIG_FILE1, sb.toString());

			Charset.availableCharsets();
			fail("Should throw Error!");
		} catch (Error e) {
			// expected
		} finally {
			cleanupFile(CONFIG_FILE1);
		}
	}

	/*
	 * Test the method availableCharsets(String) when the configuration file
	 * contains a non-CharsetProvider class name.
	 */
	public void testAvailableCharsets_NotCharsetProviderClass()
			throws Exception {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("java.lang.String\r");
			setupFile(CONFIG_FILE1, sb.toString());

			Charset.availableCharsets();
			fail("Should throw ClassCastException!");
		} catch (ClassCastException e) {
			// expected
		} finally {
			cleanupFile(CONFIG_FILE1);
		}
	}

	/*
	 * Test the method availableCharsets(String) when the configuration file
	 * contains an illegal string.
	 */
	public void testAvailableCharsets_IllegalString() throws Exception {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("java String\r");
			setupFile(CONFIG_FILE1, sb.toString());

			Charset.availableCharsets();
			fail("Should throw Error!");
		} catch (Error e) {
			// expected
		} finally {
			cleanupFile(CONFIG_FILE1);
		}
	}

}
