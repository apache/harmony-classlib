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
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

import junit.framework.TestCase;

import org.apache.harmony.pack200.Segment;

/**
 * Tests for org.apache.harmony.pack200.Segment.
 */
public class SegmentTest extends TestCase {

    InputStream in;
    JarOutputStream out;

    public void testHelloWorld() throws Exception {
        in = Segment.class
                .getResourceAsStream("/org/apache/harmony/pack200/tests/HelloWorld.pack");
        Segment segment = Segment.parse(in);
        assertNotNull(segment);
        File file = File.createTempFile("hello", "world.jar");
        out = new JarOutputStream(new FileOutputStream(file));
        segment.writeJar(out);
        out.close();
        out = null;
        JarFile jarFile = new JarFile(file);
        JarEntry entry = jarFile.getJarEntry("org/apache/harmony/archive/tests/internal/pack200/HelloWorld.class");
        assertNotNull(entry);
        Process process = Runtime.getRuntime().exec("java -cp " + file.getName() + " org.apache.harmony.archive.tests.internal.pack200.HelloWorld", new String[] {}, file.getParentFile());
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = reader.readLine();
        assertEquals(line, "Hello world");
    }

    public void testJustResources() throws Exception {
        in = Segment.class
                .getResourceAsStream("/org/apache/harmony/pack200/tests/JustResources.pack");
        Segment segment = Segment.parse(in);
        assertNotNull(segment);
        out = new JarOutputStream(new FileOutputStream(File.createTempFile("just", "resources.jar")));
        segment.writeJar(out);
    }

    // Test with an archive containing Harmony's SQL module, packed with -E1
    public void testWithSqlE1() throws Exception {
        in = Segment.class
                .getResourceAsStream("/org/apache/harmony/pack200/tests/sql-e1.pack.gz");
        Segment segment = Segment.parse(in);
        assertNotNull(segment);
        out = new JarOutputStream(new FileOutputStream(File.createTempFile("sql", "-e1.jar")));
        segment.writeJar(out);
    }

    // Test with an archive containing Harmony's SQL module
    public void testWithSql() throws Exception {
        in = Segment.class
                .getResourceAsStream("/org/apache/harmony/pack200/tests/sql.pack.gz");
        Segment segment = Segment.parse(in);
        assertNotNull(segment);
        out = new JarOutputStream(new FileOutputStream(File.createTempFile("sql", ".jar")));
        segment.writeJar(out);
    }

    // Test with an archive containing Harmony's Pack200 module, packed with -E1
    public void testWithPack200E1() throws Exception {
        in = Segment.class
                .getResourceAsStream("/org/apache/harmony/pack200/tests/pack200-e1.pack.gz");
        Segment segment = Segment.parse(in);
        assertNotNull(segment);
        out = new JarOutputStream(new FileOutputStream(File.createTempFile("pack", "200-e1.jar")));
        segment.writeJar(out);
    }

    // Test with an archive containing Harmony's Pack200 module
    public void testWithPack200() throws Exception {
        in = Segment.class
                .getResourceAsStream("/org/apache/harmony/pack200/tests/pack200.pack.gz");
        Segment segment = Segment.parse(in);
        assertNotNull(segment);
        out = new JarOutputStream(new FileOutputStream(File.createTempFile("pack", "200.jar")));
        segment.writeJar(out);
    }

    // Test with an archive containing Harmony's JNDI module
    public void testWithJNDIE1() throws Exception {
        in = Segment.class
                .getResourceAsStream("/org/apache/harmony/pack200/tests/jndi-e1.pack.gz");
        Segment segment = Segment.parse(in);
        assertNotNull(segment);
        out = new JarOutputStream(new FileOutputStream(File.createTempFile("jndi", "-e1.jar")));
        segment.writeJar(out);
    }

    // Test with an archive containing Annotations
    public void testWithAnnotations() throws Exception {

        in = Segment.class
                .getResourceAsStream("/org/apache/harmony/pack200/tests/annotations.pack.gz");
        Segment segment = Segment.parse(in);
        assertNotNull(segment);
        out = new JarOutputStream(new FileOutputStream(File.createTempFile("ann", "otations.jar")));
        segment.writeJar(out);

    }

    public void testInterfaceOnly() throws Exception {
        in = Segment.class
                .getResourceAsStream("/org/apache/harmony/pack200/tests/InterfaceOnly.pack");
        Segment segment = Segment.parse(in);
        assertNotNull(segment);
        out = new JarOutputStream(new FileOutputStream(File.createTempFile("Interface", "Only.jar")));
        segment.writeJar(out);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        try {
            if (in != null) {
                in.close();
            }
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}