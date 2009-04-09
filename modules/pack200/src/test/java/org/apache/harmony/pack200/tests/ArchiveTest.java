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
package org.apache.harmony.pack200.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

import junit.framework.TestCase;

import org.apache.harmony.pack200.Archive;
import org.apache.harmony.pack200.Pack200Exception;
import org.apache.harmony.unpack200.Segment;

public class ArchiveTest extends TestCase {

    JarFile in;
    OutputStream out;
    File file;

    public void testHelloWorld() throws IOException, Pack200Exception, URISyntaxException {
        in = new JarFile(new File(Archive.class.getResource(
                "/org/apache/harmony/pack200/tests/hw.jar").toURI()));
        file = File.createTempFile("helloworld", ".pack.gz");
        out = new FileOutputStream(file);
        new Archive(in, out, true).pack();
        in.close();
        out.close();

        // now unpack
        InputStream in2 = new FileInputStream(file);
        File file2 = File.createTempFile("helloworld", ".jar");
        JarOutputStream out2 = new JarOutputStream(new FileOutputStream(file2));
        org.apache.harmony.unpack200.Archive archive = new org.apache.harmony.unpack200.Archive(
                in2, out2);
        archive.unpack();
        out2.close();
        in2.close();

        JarFile jarFile = new JarFile(file2);
        file2.deleteOnExit();
        JarEntry entry = jarFile
                .getJarEntry("org/apache/harmony/archive/tests/internal/pack200/HelloWorld.class");
        assertNotNull(entry);
        InputStream ours = jarFile.getInputStream(entry);

        JarFile jarFile2 = new JarFile(new File(Segment.class.getResource(
                "/org/apache/harmony/pack200/tests/hw.jar").toURI()));
        JarEntry entry2 = jarFile2
                .getJarEntry("org/apache/harmony/archive/tests/internal/pack200/HelloWorld.class");
        assertNotNull(entry2);

        InputStream expected = jarFile2.getInputStream(entry2);

        BufferedReader reader1 = new BufferedReader(new InputStreamReader(ours));
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(
                expected));
        String line1 = reader1.readLine();
        String line2 = reader2.readLine();
        int i = 1;
        while (line1 != null || line2 != null) {
            assertEquals("Unpacked class files differ", line2, line1);
            line1 = reader1.readLine();
            line2 = reader2.readLine();
            i++;
        }
        reader1.close();
        reader2.close();
    }

    public void testSQL() throws IOException, Pack200Exception, URISyntaxException {
        in = new JarFile(new File(Archive.class.getResource(
                "/org/apache/harmony/pack200/tests/sqlUnpacked.jar").toURI()));
        file = File.createTempFile("sql", ".pack");
        out = new FileOutputStream(file);
        new Archive(in, out, false).pack();
        in.close();
        out.close();

        // now unpack
        InputStream in2 = new FileInputStream(file);
        File file2 = File.createTempFile("sqlout", ".jar");
        JarOutputStream out2 = new JarOutputStream(new FileOutputStream(file2));
        org.apache.harmony.unpack200.Archive archive = new org.apache.harmony.unpack200.Archive(in2, out2);
        archive.unpack();
        JarFile jarFile = new JarFile(file2);
        file2.deleteOnExit();

        File compareFile = new File(Archive.class.getResource(
                "/org/apache/harmony/pack200/tests/sqlUnpacked.jar").toURI());
        JarFile jarFile2 = new JarFile(compareFile);

        assertEquals(jarFile2.size(), jarFile.size());

        compareFiles(jarFile, jarFile2);
    }

    public void testLargeClass() throws IOException, Pack200Exception, URISyntaxException {
        in = new JarFile(new File(Archive.class.getResource(
                "/org/apache/harmony/pack200/tests/largeClassUnpacked.jar")
                .toURI()));
        file = File.createTempFile("largeClass", ".pack");
        out = new FileOutputStream(file);
        new Archive(in, out, false).pack();
        in.close();
        out.close();

        // now unpack
        InputStream in2 = new FileInputStream(file);
        File file2 = File.createTempFile("largeClassOut", ".jar");
        JarOutputStream out2 = new JarOutputStream(new FileOutputStream(file2));
        org.apache.harmony.unpack200.Archive archive = new org.apache.harmony.unpack200.Archive(in2, out2);
        archive.unpack();
        JarFile jarFile = new JarFile(file2);
        file2.deleteOnExit();

        File compareFile = new File(Archive.class.getResource(
                "/org/apache/harmony/pack200/tests/largeClassUnpacked.jar").toURI());
        JarFile jarFile2 = new JarFile(compareFile);

        assertEquals(jarFile2.size(), jarFile.size());

        compareFiles(jarFile, jarFile2);
    }

    public void testJNDI() throws IOException, Pack200Exception, URISyntaxException {
        in = new JarFile(new File(Archive.class.getResource(
                "/org/apache/harmony/pack200/tests/jndi.jar").toURI()));
        file = File.createTempFile("jndi", ".pack");
        out = new FileOutputStream(file);
        new Archive(in, out, false).pack();
        in.close();
        out.close();

        // now unpack
        InputStream in2 = new FileInputStream(file);
        File file2 = File.createTempFile("jndiout", ".jar");
        JarOutputStream out2 = new JarOutputStream(new FileOutputStream(file2));
        org.apache.harmony.unpack200.Archive archive = new org.apache.harmony.unpack200.Archive(in2, out2);
        archive.unpack();
        JarFile jarFile = new JarFile(file2);
        file2.deleteOnExit();
        JarFile jarFile2 = new JarFile(new File(Archive.class.getResource(
                "/org/apache/harmony/pack200/tests/jndiUnpacked.jar").toURI()));

        compareFiles(jarFile, jarFile2);
    }

    public void testSegmentLimits() throws IOException, Pack200Exception,
            URISyntaxException {
        in = new JarFile(new File(Archive.class.getResource(
                "/org/apache/harmony/pack200/tests/hw.jar").toURI()));
        file = File.createTempFile("helloworld", ".pack.gz");
        out = new FileOutputStream(file);
        Archive archive = new Archive(in, out, true);
        archive.setSegmentLimit(0);
        archive.pack();
        in.close();
        out.close();

        in = new JarFile(new File(Archive.class.getResource(
                "/org/apache/harmony/pack200/tests/hw.jar").toURI()));
        file = File.createTempFile("helloworld", ".pack.gz");
        out = new FileOutputStream(file);
        archive = new Archive(in, out, true);
        archive.setSegmentLimit(-1);
        archive.pack();
        in.close();
        out.close();

        in = new JarFile(new File(Archive.class.getResource(
                "/org/apache/harmony/pack200/tests/hw.jar").toURI()));
        file = File.createTempFile("helloworld", ".pack.gz");
        out = new FileOutputStream(file);
        archive = new Archive(in, out, true);
        archive.setSegmentLimit(5000);
        archive.pack();
        in.close();
        out.close();
    }

    public void testStripDebug() throws IOException, Pack200Exception, URISyntaxException {
        in = new JarFile(new File(Archive.class
                .getResource("/org/apache/harmony/pack200/tests/sqlUnpacked.jar").toURI()));
        file = File.createTempFile("sql", ".pack");
        out = new FileOutputStream(file);
        Archive archive = new Archive(in, out, false);
        archive.stripDebugAttributes();
        archive.pack();
        in.close();
        out.close();

        // now unpack
        InputStream in2 = new FileInputStream(file);
        File file2 = File.createTempFile("sqloutNoDebug", ".jar");
        JarOutputStream out2 = new JarOutputStream(new FileOutputStream(file2));
        org.apache.harmony.unpack200.Archive u2archive = new org.apache.harmony.unpack200.Archive(in2, out2);
        u2archive.unpack();

        File compareFile = new File(Archive.class.getResource(
                "/org/apache/harmony/pack200/tests/sqlUnpackedNoDebug.jar").toURI());
        JarFile jarFile = new JarFile(file2);
        assertTrue(file2.length() < 250000);
        file2.deleteOnExit();

        JarFile jarFile2 = new JarFile(compareFile);

        compareFiles(jarFile, jarFile2);
    }

    public void testAnnotations() throws IOException, Pack200Exception,
            URISyntaxException {
        in = new JarFile(new File(Archive.class.getResource(
                "/org/apache/harmony/pack200/tests/annotationsUnpacked.jar")
                .toURI()));
        file = File.createTempFile("annotations", ".pack");
        out = new FileOutputStream(file);
        new Archive(in, out, false).pack();
        in.close();
        out.close();

        // now unpack
        InputStream in2 = new FileInputStream(file);
        File file2 = File.createTempFile("annotationsout", ".jar");
        JarOutputStream out2 = new JarOutputStream(new FileOutputStream(file2));
        org.apache.harmony.unpack200.Archive archive = new org.apache.harmony.unpack200.Archive(
                in2, out2);
        archive.unpack();
        JarFile jarFile = new JarFile(file2);
        file2.deleteOnExit();
        JarFile jarFile2 = new JarFile(new File(Archive.class.getResource(
                "/org/apache/harmony/pack200/tests/annotationsUnpacked.jar").toURI()));

        compareFiles(jarFile, jarFile2);
    }

    public void testE0() throws Pack200Exception, IOException, URISyntaxException {
        File f1 = new File(Archive.class.getResource(
                "/org/apache/harmony/pack200/tests/jndi.jar").toURI());
        in = new JarFile(f1);
        file = File.createTempFile("jndiE0", ".pack");
        out = new FileOutputStream(file);
        Archive archive = new Archive(in, out, false);
        archive.setEffort(0);
        archive.pack();
        in.close();
        out.close();
        compareFiles(new JarFile(f1), new JarFile(file));

    }

//    public void testE0again() throws IOException, Pack200Exception, URISyntaxException {
//        JarInputStream inputStream = new JarInputStream(Archive.class.getResourceAsStream("/org/apache/harmony/pack200/tests/jndi.jar"));
//        file = File.createTempFile("jndiE0", ".pack");
//        out = new FileOutputStream(file);
//        Archive archive = new Archive(inputStream, out, false);
//        archive.setEffort(0);
//        archive.pack();
//        inputStream.close();
//        out.close();
//        in = new JarFile(new File(Archive.class.getResource(
//                "/org/apache/harmony/pack200/tests/jndi.jar").toURI()));
//        compareFiles(in, new JarFile(file));
//    }

    public void testMultipleJars() throws URISyntaxException, IOException, Pack200Exception {
    	File folder = new File(Archive.class
    			.getResource("/org/apache/harmony/pack200/tests/jars").toURI());
    	String[] children = folder.list();
    	for (int i = 0; i < children.length; i++) {
			if(children[i].endsWith(".jar") && !children[i].endsWith("Unpacked.jar")) {
				File inputFile = new File(folder, children[i]);
				in = new JarFile(inputFile);
				file = File.createTempFile("temp", ".pack.gz");
		        out = new FileOutputStream(file);
//		        System.out.println("packing " + children[i]);
		        new Archive(in, out, true).pack();
		        in.close();
		        out.close();

		        // unpack and compare

			}
		}
    }

    private void compareFiles(JarFile jarFile, JarFile jarFile2)
            throws IOException {
        Enumeration entries = jarFile.entries();
        while (entries.hasMoreElements()) {

            JarEntry entry = (JarEntry) entries.nextElement();
            assertNotNull(entry);

            String name = entry.getName();
            JarEntry entry2 = jarFile2.getJarEntry(name);
            assertNotNull("Missing Entry: " + name, entry2);
            if (!name.equals("META-INF/MANIFEST.MF")) { // Manifests aren't
                                                        // necessarily
                                                        // byte-for-byte
                                                        // identical

                InputStream ours = jarFile.getInputStream(entry);
                InputStream expected = jarFile2.getInputStream(entry2);

                BufferedReader reader1 = new BufferedReader(
                        new InputStreamReader(ours));
                BufferedReader reader2 = new BufferedReader(
                        new InputStreamReader(expected));
                String line1 = reader1.readLine();
                String line2 = reader2.readLine();
                int i = 1;
                while (line1 != null || line2 != null) {
                    assertEquals("Unpacked files differ for " + name, line2,
                            line1);
                    line1 = reader1.readLine();
                    line2 = reader2.readLine();
                    i++;
                }
                reader1.close();
                reader2.close();
            }
        }
        jarFile.close();
        jarFile2.close();
    }

}
