/* Copyright 2005 The Apache Software Foundation or its licensors, as applicable
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

package tests.api.java.security;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.PermissionCollection;
import java.security.SecurityPermission;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import tests.support.Support_Exec;
import tests.support.Support_GetLocal;

public class PermissionCollectionTest extends junit.framework.TestCase {

	// The below test is known to fail. Haven't got to the bottom of
	// it yet but here is what has been determined :-
	//
	// * the Support_PermissionCollection application that is forked off
	// near the end of this test needs to verify a signed jar (signedBKS.jar).
	// This means that com.ibm.oti.util.JarUtils.verifySignature() ends up
	// getting called. But at present that exists as just a lightweight/stub
	// implementation which simply returns NULL. That behaviour causes a
	// security exception inside java.util.jar.JarVerifier.
	//
	// * the above problem was fixed by rebuilding Harmony with the STUB
	// IMPLEMENTATION of com.ibm.oti.util.JarUtils.verifySignature() replaced
	// with one that delegates to
	// org.apache.harmony.security.utils.JarUtils.verifySignature().
	//
	// * unfortunately, a NPE is raised in line 103 of Harmony's JarUtils class.
	//
	// * the cause of that NPE has still not been determined. Could it be
	// related to Harmony's current stub implementation of BigInteger ?
	/**
	 * @tests java.security.PermissionCollection#implies(java.security.Permission)
	 */
	public void test_impliesLjava_security_Permission() {

		// Look for the tests classpath
		URL classURL = this.getClass().getProtectionDomain().getCodeSource()
				.getLocation();
		if (classURL == null) {
			fail("Could not get this class' location");
		}

		File policyFile = null;
		try {
			policyFile = Support_GetLocal.createTempFile(".policy");
		} catch (IOException e) {
			fail("Unexpected IOException while creating policy file : " + e);
		}

		// Create the policy file (and save the existing one if any)
		try {
			FileOutputStream fileOut = new FileOutputStream(policyFile);
			String linebreak = System.getProperty("line.separator");
			String towrite = "grant codeBase \""
					+ classURL.toExternalForm()
					+ "tests/resources/PermissionCollection/signedBKS.jar"
					+ "\" signedBy \"eleanor\" {"
					+ linebreak
					+ "permission java.io.FilePermission \"test1.txt\", \"write\";"
					+ linebreak
					+ "permission mypackage.MyPermission \"essai\", signedBy \"eleanor,dylan\";"
					+ linebreak
					+ "};"
					+ linebreak
					+ "grant codeBase \""
					+ classURL.toExternalForm()
					+ "tests/resources/PermissionCollection/signedBKS.jar"
					+ "\" signedBy \"eleanor\" {"
					+ linebreak
					+ "permission java.io.FilePermission \"test2.txt\", \"write\";"
					+ linebreak + "};" + linebreak + "grant codeBase \"";
			towrite += classURL.toExternalForm();
			towrite += "\" {" + linebreak
					+ "permission java.security.AllPermission;" + linebreak
					+ "};" + linebreak + "keystore \""
					+ classURL.toExternalForm()
					+ "tests/resources/PermissionCollection/keystore.bks"
					+ "\",\"BKS\";";
			fileOut.write(towrite.getBytes());
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			fail("Exception while creating policy file : " + e);
		}

		// Copy mypermissionBKS.jar to the user directory so that it can be put
		// in
		// the classpath
		File jarFile = null;
		try {
			URL jarURL = new URL(
					classURL.toExternalForm()
							+ "tests/resources/PermissionCollection/mypermissionBKS.jar");
			jarFile = Support_GetLocal.createTempFile(".jar");
			FileOutputStream fout = new FileOutputStream(jarFile);
			InputStream jis = jarURL.openStream();
			int c = jis.read();
			while (c != -1) {
				fout.write(c);
				c = jis.read();
			}
			fout.flush();
			fout.close();
			jis.close();
		} catch (MalformedURLException e) {
			fail("Unexpected MalformedURLException while creating jar file :"
					+ e);
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException while creating jar file :"
					+ e);
		} catch (IOException e) {
			fail("Unexpected IOException while creating jar file : " + e);
		}

		String classPath = new File(classURL.getFile()).getPath();

		// Execute Support_PermissionCollection in another VM
		String[] classPathArray = new String[2];
		classPathArray[0] = classPath;
		classPathArray[1] = jarFile.getPath();
		String[] args = new String[3];
		try {
			args[0] = "-Djava.security.policy=" + policyFile.toURL();
			args[1] = "tests.support.Support_PermissionCollection";
			args[2] = classURL.toExternalForm()
					+ "tests/resources/PermissionCollection/signedBKS.jar";
		} catch (MalformedURLException e) {
			fail("Unexpected MalformedURLException while policy file to url : "
					+ e);
		}

		try {
			String result = Support_Exec.execJava(args, classPathArray, true);
			// Delete the Jar file copied in the user directory
			if (!jarFile.delete()) {
				throw new IOException("Could not delete temporary jar file : "
						+ jarFile.getPath());
			}

			// Delete the temporary policy file
			if (!policyFile.delete()) {
				throw new IOException(
						"Could not delete temporary policy file : "
								+ policyFile.getPath());
			}

			StringTokenizer resultTokenizer = new StringTokenizer(result, ",");

			// Check the test result from the new VM process
			assertEquals("Permission should be granted", "true", resultTokenizer
					.nextToken());
			assertEquals("signed Permission should be granted", "true", resultTokenizer
					.nextToken());
			assertEquals("Permission should not be granted", "false", resultTokenizer
					.nextToken());
		} catch (IOException e) {
			fail("IOException during test : " + e);
		} catch (InterruptedException e) {
			fail("InterruptedException during test : " + e);
		} catch (NoSuchElementException e) {
			fail("NoSuchElementException during test : " + e);
		} catch (Exception e) {
			fail("Exception during test : " + e);
		}
	}

	/**
	 * @tests java.security.PermissionCollection#PermissionCollection()
	 */
	public void test_Constructor() {
		// test java.security.permissionCollection.PermissionCollection()
		try {
			SecurityPermission permi = new SecurityPermission(
					"testing permissionCollection-isReadOnly");
			PermissionCollection permCollect = permi.newPermissionCollection();
			assertNotNull(
					"creat permissionCollection constructor returned a null",
					permCollect);
		} catch (Exception e) {
			fail("creating of permissionCollection constructor failed : " + e);
		}
	}

	/**
	 * @tests java.security.PermissionCollection#isReadOnly()
	 */
	public void test_isReadOnly() {
		// test java.security.permissionCollection.isReadOnly()
		SecurityPermission permi = new SecurityPermission(
				"testing permissionCollection-isREadOnly");
		PermissionCollection permCollect = permi.newPermissionCollection();
		assertTrue("readOnly has not been set, but isReadOnly returned true",
				!permCollect.isReadOnly());
		permCollect.setReadOnly();
		assertTrue("readOnly is set, but isReadonly returned false",
				permCollect.isReadOnly());
	}

	/**
	 * @tests java.security.PermissionCollection#setReadOnly()
	 */
	public void test_setReadOnly() {
		// test java.security.permissionCollection.setReadOnly()
		SecurityPermission permi = new SecurityPermission(
				"testing permissionCollection-setReadOnly");
		PermissionCollection permCollect = permi.newPermissionCollection();
		assertTrue("readOnly has not been set, but isReadOnly returned true",
				!permCollect.isReadOnly());
		permCollect.setReadOnly();
		assertTrue("readOnly is set, but isReadonly returned false",
				permCollect.isReadOnly());
	}

	/**
	 * @tests java.security.PermissionCollection#toString()
	 */
	public void test_toString() {
		// test java.security.permissionCollection.toString()
		SecurityPermission permi = new SecurityPermission(
				"testing permissionCollection-isREadOnly");
		assertNotNull("toString should have returned a string of elements",
				permi.newPermissionCollection().toString());
	}
}