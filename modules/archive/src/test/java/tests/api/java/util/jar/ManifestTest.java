/* Copyright 1998, 2005 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tests.api.java.util.jar;



import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import tests.support.resource.Support_Resources;

public class ManifestTest extends junit.framework.TestCase {

	final String jarName = "hyts_patch.jar";

	final String attJarName = "hyts_att.jar";

	Manifest m, m2;

	File resources;

	/**
	 * @tests java.util.jar.Manifest#Manifest()
	 */
	public void test_Constructor() {
		// Test for method java.util.jar.Manifest()
		Manifest emptyManifest = new Manifest();
		assertTrue("Should have no entries", emptyManifest.getEntries()
				.isEmpty());
		assertTrue("Should have no main attributes", emptyManifest
				.getMainAttributes().isEmpty());
	}

	/**
	 * @tests java.util.jar.Manifest#Manifest(java.io.InputStream)
	 */
	public void test_ConstructorLjava_io_InputStream() {
		// Test for method java.util.jar.Manifest(java.io.InputStream)
		/*
		 * ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 * m2.write(baos); InputSteam is = new ByteArrayInputStream
		 * (baos.toByteArray()); Manifest myManifest = new Manifest (is);
		 * assertTrue("Manifests should be equal", myManifest.equals(m2));
		 */

		Manifest manifest = null;
		try {
			manifest = new Manifest(new URL(Support_Resources
					.getURL("manifest/hyts_MANIFEST.MF")).openStream());
		} catch (MalformedURLException e) {
			fail("Malformed URL");
		} catch (IOException e) {
			fail("IOException");
		}
		Attributes main = manifest.getMainAttributes();
		assertTrue("Bundle-Name not correct", main.getValue("Bundle-Name")
				.equals("ClientSupport"));
		assertTrue(
				"Bundle-Description not correct",
				main
						.getValue("Bundle-Description")
						.equals(
								"Provides SessionService, AuthenticationService. Extends RegistryService."));
		assertTrue("Bundle-Activator not correct", main.getValue(
				"Bundle-Activator").equals(
				"com.ibm.ive.eccomm.client.support.ClientSupportActivator"));
		assertTrue(
				"Import-Package not correct",
				main
						.getValue("Import-Package")
						.equals(
								"com.ibm.ive.eccomm.client.services.log,com.ibm.ive.eccomm.client.services.registry,com.ibm.ive.eccomm.service.registry; specification-version=1.0.0,com.ibm.ive.eccomm.service.session; specification-version=1.0.0,com.ibm.ive.eccomm.service.framework; specification-version=1.2.0,org.osgi.framework; specification-version=1.0.0,org.osgi.service.log; specification-version=1.0.0,com.ibm.ive.eccomm.flash; specification-version=1.2.0,com.ibm.ive.eccomm.client.xml,com.ibm.ive.eccomm.client.http.common,com.ibm.ive.eccomm.client.http.client"));
		assertTrue(
				"Import-Service not correct",
				main
						.getValue("Import-Service")
						.equals(
								"org.osgi.service.log.LogReaderServiceorg.osgi.service.log.LogService,com.ibm.ive.eccomm.service.registry.RegistryService"));
		assertTrue(
				"Export-Package not correct",
				main
						.getValue("Export-Package")
						.equals(
								"com.ibm.ive.eccomm.client.services.authentication; specification-version=1.0.0,com.ibm.ive.eccomm.service.authentication; specification-version=1.0.0,com.ibm.ive.eccomm.common; specification-version=1.0.0,com.ibm.ive.eccomm.client.services.registry.store; specification-version=1.0.0"));
		assertTrue(
				"Export-Service not correct",
				main
						.getValue("Export-Service")
						.equals(
								"com.ibm.ive.eccomm.service.authentication.AuthenticationService,com.ibm.ive.eccomm.service.session.SessionService"));
		assertTrue("Bundle-Vendor not correct", main.getValue("Bundle-Vendor")
				.equals("IBM"));
		assertTrue("Bundle-Version not correct", main
				.getValue("Bundle-Version").equals("1.2.0"));
	}

	/**
	 * @tests java.util.jar.Manifest#clear()
	 */
	public void test_clear() {
		// Test for method void java.util.jar.Manifest.clear()
		m2.clear();
		assertTrue("Should have no entries", m2.getEntries().isEmpty());
		assertTrue("Should have no main attributes", m2.getMainAttributes()
				.isEmpty());
	}

	/**
	 * @tests java.util.jar.Manifest#getAttributes(java.lang.String)
	 */
	public void test_getAttributesLjava_lang_String() {
		// Test for method java.util.jar.Attributes
		// java.util.jar.Manifest.getAttributes(java.lang.String)
		assertTrue("Should not exist",
				m2.getAttributes("Doesn't Exist") == null);
		assertTrue("Should exist", m2.getAttributes("HasAttributes.txt").get(
				new Attributes.Name("MyAttribute")).equals("OK"));
	}

	/**
	 * @tests java.util.jar.Manifest#getEntries()
	 */
	public void test_getEntries() {
		// Test for method java.util.Map java.util.jar.Manifest.getEntries()
		Map myMap = m2.getEntries();
		assertTrue("Shouldn't exist", myMap.get("Doesn't exist") == null);
		assertTrue("Should exist",
				((Attributes) myMap.get("HasAttributes.txt")).get(
						new Attributes.Name("MyAttribute")).equals("OK"));

	}

	/**
	 * @tests java.util.jar.Manifest#getMainAttributes()
	 */
	public void test_getMainAttributes() {
		// Test for method java.util.jar.Attributes
		// java.util.jar.Manifest.getMainAttributes()
		Attributes a = m.getMainAttributes();
		assertTrue("Manifest_Version should return 1.0", a.get(
				Attributes.Name.MANIFEST_VERSION).equals("1.0"));
	}

	/**
	 * Sets up the fixture, for example, open a network connection. This method
	 * is called before a test is executed.
	 */
	protected void setUp() {
		resources = Support_Resources.createTempFolder();
		try {
			Support_Resources.copyFile(resources, null, jarName);
			JarFile jarFile = new JarFile(new File(resources, jarName));
			m = jarFile.getManifest();
			jarFile.close();
			Support_Resources.copyFile(resources, null, attJarName);
			jarFile = new JarFile(new File(resources, attJarName));
			m2 = jarFile.getManifest();
			jarFile.close();
		} catch (Exception e) {
			fail("Exception during setup: " + e.toString());
		}
	}

	/**
	 * Tears down the fixture, for example, close a network connection. This
	 * method is called after a test is executed.
	 */
	protected void tearDown() {
	}

}
