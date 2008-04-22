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
package org.apache.harmony.unpack200.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

import junit.framework.TestCase;

import org.apache.harmony.unpack200.Segment;

/**
 * Tests for org.apache.harmony.unpack200.Segment.
 */
public class SegmentTest extends TestCase {

    InputStream in;
    JarOutputStream out;

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

    public void testJustResources() throws Exception {
        in = Segment.class
                .getResourceAsStream("/org/apache/harmony/pack200/tests/JustResources.pack");
        out = new JarOutputStream(new FileOutputStream(File.createTempFile(
                "just", "resources.jar")));
        Segment segment = new Segment();
        segment.unpack(in, out);
    }

    public void testInterfaceOnly() throws Exception {
        in = Segment.class
                .getResourceAsStream("/org/apache/harmony/pack200/tests/InterfaceOnly.pack");
        out = new JarOutputStream(new FileOutputStream(File.createTempFile(
                "Interface", "Only.jar")));
        Segment segment = new Segment();
        segment.unpack(in, out);
    }

    public void testHelloWorld() throws Exception {
        in = Segment.class
                .getResourceAsStream("/org/apache/harmony/pack200/tests/HelloWorld.pack");
        File file = File.createTempFile("hello", "world.jar");
        out = new JarOutputStream(new FileOutputStream(file));
        Segment segment = new Segment();
        segment.unpack(in, out);
        out.close();
        out = null;
        JarFile jarFile = new JarFile(file);
        JarEntry entry = jarFile
                .getJarEntry("org/apache/harmony/archive/tests/internal/pack200/HelloWorld.class");
        assertNotNull(entry);
        Process process = Runtime
                .getRuntime()
                .exec(
                        "java -cp "
                                + file.getName()
                                + " org.apache.harmony.archive.tests.internal.pack200.HelloWorld",
                        new String[] {}, file.getParentFile());
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                process.getInputStream()));
        String line = reader.readLine();
        assertEquals(line, "Hello world");
        reader.close();

        Process process2 = Runtime
                .getRuntime()
                .exec(
                        "javap -c -verbose -classpath "
                                + file.getName()
                                + " org.apache.harmony.archive.tests.internal.pack200.HelloWorld",
                        new String[] {}, file.getParentFile());
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(process2
                .getInputStream()));
        InputStream javapCompareFile = Segment.class
                .getResourceAsStream("/org/apache/harmony/pack200/tests/HelloWorldJavap.out");
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(
                javapCompareFile));
        String line1 = reader1.readLine();
        String line2 = reader2.readLine();
        int i = 1;
        while (line1 != null || line2 != null) {
            assertEquals(line2, line1);
            line1 = reader1.readLine();
            line2 = reader2.readLine();
            i++;
        }
        reader1.close();
        reader2.close();
    }

}