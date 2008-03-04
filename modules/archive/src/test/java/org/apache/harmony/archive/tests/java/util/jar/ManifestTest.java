/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
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
package org.apache.harmony.archive.tests.java.util.jar;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import junit.framework.TestCase;
import tests.support.resource.Support_Resources;

public class ManifestTest extends TestCase {

	private final String JAR_NAME = "hyts_patch.jar";

    private final String ATT_JAR_NAME = "hyts_att.jar";

    private final String MANIFEST_NAME = "manifest/hyts_MANIFEST.MF";

    private Manifest m;
    
    private Manifest m2;

    private File resources;
    
    @Override
    protected void setUp() {
        resources = Support_Resources.createTempFolder();
        try {
            Support_Resources.copyFile(resources, null, JAR_NAME);
            JarFile jarFile = new JarFile(new File(resources, JAR_NAME));
            m = jarFile.getManifest();
            jarFile.close();
            Support_Resources.copyFile(resources, null, ATT_JAR_NAME);
            jarFile = new JarFile(new File(resources, ATT_JAR_NAME));
            m2 = jarFile.getManifest();
            jarFile.close();
        } catch (Exception e) {
            fail("Exception during setup: " + e.toString());
        }
    }

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
     * @tests java.util.jar.Manifest#Manifest(java.util.jar.Manifest)
     */
    public void test_Constructor_Ljava_util_jar_Manifest() throws IOException {
        Manifest firstManifest = new Manifest(new URL(Support_Resources
                .getURL(MANIFEST_NAME)).openStream());
        Manifest secondManifest = new Manifest(firstManifest);
        assertEquals(firstManifest, secondManifest);
    }
	
	/**
	 * @tests java.util.jar.Manifest#Manifest(java.io.InputStream)
	 */
	public void test_ConstructorLjava_io_InputStream() throws IOException {
		// Test for method java.util.jar.Manifest(java.io.InputStream)
		/*
		 * ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 * m2.write(baos); InputSteam is = new ByteArrayInputStream
		 * (baos.toByteArray()); Manifest myManifest = new Manifest (is);
		 * assertTrue("Manifests should be equal", myManifest.equals(m2));
		 */

        Manifest manifest = new Manifest(new URL(Support_Resources
                .getURL(MANIFEST_NAME)).openStream());

		Attributes main = manifest.getMainAttributes();
		assertEquals("Bundle-Name not correct", "ClientSupport", main.getValue("Bundle-Name")
				);
		assertEquals("Bundle-Description not correct",
				
								"Provides SessionService, AuthenticationService. Extends RegistryService.", main
						.getValue("Bundle-Description")
						);
		assertEquals("Bundle-Activator not correct", 
				"com.ibm.ive.eccomm.client.support.ClientSupportActivator", main.getValue(
				"Bundle-Activator"));
		assertEquals("Import-Package not correct",
				
								"com.ibm.ive.eccomm.client.services.log,com.ibm.ive.eccomm.client.services.registry,com.ibm.ive.eccomm.service.registry; specification-version=1.0.0,com.ibm.ive.eccomm.service.session; specification-version=1.0.0,com.ibm.ive.eccomm.service.framework; specification-version=1.2.0,org.osgi.framework; specification-version=1.0.0,org.osgi.service.log; specification-version=1.0.0,com.ibm.ive.eccomm.flash; specification-version=1.2.0,com.ibm.ive.eccomm.client.xml,com.ibm.ive.eccomm.client.http.common,com.ibm.ive.eccomm.client.http.client", main
						.getValue("Import-Package")
						);
		assertEquals("Import-Service not correct",
				
								"org.osgi.service.log.LogReaderServiceorg.osgi.service.log.LogService,com.ibm.ive.eccomm.service.registry.RegistryService", main
						.getValue("Import-Service")
						);
		assertEquals("Export-Package not correct",
				
								"com.ibm.ive.eccomm.client.services.authentication; specification-version=1.0.0,com.ibm.ive.eccomm.service.authentication; specification-version=1.0.0,com.ibm.ive.eccomm.common; specification-version=1.0.0,com.ibm.ive.eccomm.client.services.registry.store; specification-version=1.0.0", main
						.getValue("Export-Package")
						);
		assertEquals("Export-Service not correct",
				
								"com.ibm.ive.eccomm.service.authentication.AuthenticationService,com.ibm.ive.eccomm.service.session.SessionService", main
						.getValue("Export-Service")
						);
		assertEquals("Bundle-Vendor not correct", "IBM", main.getValue("Bundle-Vendor")
				);
		assertEquals("Bundle-Version not correct", "1.2.0", main
				.getValue("Bundle-Version"));

        // Regression test for HARMONY-5424
        String manifestContent = "Manifest-Version: 1.0\nCreated-By: Apache\nPackage: \nBuild-Jdk: 1.4.1_01\n\n"
                + "Name: \nSpecification-Title: foo\nSpecification-Version: 1.0\nSpecification-Vendor: \n"
                + "Implementation-Title: \nImplementation-Version: 1.0\nImplementation-Vendor: \n\n";
        ByteArrayInputStream bis = new ByteArrayInputStream(manifestContent
                .getBytes());
        Manifest mf = new Manifest(bis);

        assertEquals("Wrong number of main attributes", 4, mf
                .getMainAttributes().size());

        Map<String, Attributes> entries = mf.getEntries();
        assertEquals("Wrong number of named entries", 1, entries.size());

        Attributes namedEntryAttributes = (Attributes) (entries.get(""));
        assertEquals("Wrong number of named entry attributes", 6,
                namedEntryAttributes.size());
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
     * @tests java.util.jar.Manifest#clone()
     */
    public void test_clone() {
        Manifest newManifest = (Manifest) m.clone();
        assertEquals(newManifest, m);
    }

    /**
     * @tests java.util.jar.Manifest#equals(java.lang.Object)
     */
    public void test_equalsLjava_lang_Object() throws IOException {
        Manifest firstManifest = new Manifest(new URL(Support_Resources
                .getURL(MANIFEST_NAME)).openStream());
        Manifest secondManifest = new Manifest(new URL(Support_Resources
                .getURL(MANIFEST_NAME)).openStream());

        assertTrue(firstManifest.equals(secondManifest));
    }

    /**
     * @tests java.util.jar.Manifest#hashCode()
     */
    public void test_hashCode() {
        Manifest newManifest = (Manifest) m.clone();
        assertEquals(newManifest.hashCode(), m.hashCode());
    }
	
	/**
	 * @tests java.util.jar.Manifest#getAttributes(java.lang.String)
	 */
	public void test_getAttributesLjava_lang_String() {
		// Test for method java.util.jar.Attributes
		// java.util.jar.Manifest.getAttributes(java.lang.String)
		assertNull("Should not exist",
				m2.getAttributes("Doesn't Exist"));
		assertEquals("Should exist", "OK", m2.getAttributes("HasAttributes.txt").get(
				new Attributes.Name("MyAttribute")));
	}

	/**
	 * @tests java.util.jar.Manifest#getEntries()
	 */
	public void test_getEntries() {
		// Test for method java.util.Map java.util.jar.Manifest.getEntries()
		Map<String, Attributes> myMap = m2.getEntries();
		assertNull("Shouldn't exist", myMap.get("Doesn't exist"));
		assertEquals("Should exist",
				"OK", myMap.get("HasAttributes.txt").get(
						new Attributes.Name("MyAttribute")));

	}

	/**
	 * @tests java.util.jar.Manifest#getMainAttributes()
	 */
	public void test_getMainAttributes() {
		// Test for method java.util.jar.Attributes
		// java.util.jar.Manifest.getMainAttributes()
		Attributes a = m.getMainAttributes();
		assertEquals("Manifest_Version should return 1.0", "1.0", a.get(
				Attributes.Name.MANIFEST_VERSION));
	}

    /**
     * @tests {@link java.util.jar.Manifest#read(java.io.InputStream)
     */
    public void test_readLjava_io_InputStream() {
        // Regression for HARMONY-89
        InputStream is = new InputStreamImpl();
        try {
            new Manifest().read(is);
            fail("Assert 0: Should have thrown IOException");
        } catch (IOException e) {
            // expected
        }
    }

    // helper class
    class InputStreamImpl extends InputStream {
        public InputStreamImpl() {
            super();
        }

        @Override
        public int read() {
            return 0;
        }
    }
}
