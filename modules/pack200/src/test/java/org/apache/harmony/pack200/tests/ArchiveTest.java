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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarOutputStream;

import junit.framework.TestCase;

import org.apache.harmony.pack200.Archive;

/**
 * Tests for org.apache.harmony.pack200.Archive, which is the main class for unpack200.
 */
public class ArchiveTest extends TestCase {

    InputStream in;
    JarOutputStream out;

    public void testJustResourcesGZip() throws Exception {
       in = Archive.class
                .getResourceAsStream("/org/apache/harmony/pack200/tests/JustResources.pack.gz");
        out = new JarOutputStream(new FileOutputStream(File.createTempFile("Just", "ResourcesGz.jar")));
        Archive archive = new Archive(in, out);
        archive.unpack();
    }

    // Test with an archive containing Harmony's SQL module, packed with -E1
    public void testWithSqlE1() throws Exception {
        in = Archive.class
                .getResourceAsStream("/org/apache/harmony/pack200/tests/sql-e1.pack.gz");
        out = new JarOutputStream(new FileOutputStream(File.createTempFile("sql-e1", ".jar")));
        Archive archive = new Archive(in, out);
        archive.unpack();
    }

    // Test with an archive containing Harmony's SQL module
    public void testWithSql() throws Exception {
        in = Archive.class
                .getResourceAsStream("/org/apache/harmony/pack200/tests/sql.pack.gz");
        out = new JarOutputStream(new FileOutputStream(File.createTempFile("sql", ".jar")));
        Archive archive = new Archive(in, out);
        archive.unpack();
    }

    // Test with an archive containing Harmony's Pack200 module, packed with -E1
    public void testWithPack200E1() throws Exception {
        in = Archive.class
                .getResourceAsStream("/org/apache/harmony/pack200/tests/pack200-e1.pack.gz");
        out = new JarOutputStream(new FileOutputStream(File.createTempFile("pack", "200-e1.jar")));
        Archive archive = new Archive(in, out);
        archive.unpack();
    }

    // Test with an archive containing Harmony's Pack200 module
    public void testWithPack200() throws Exception {
        in = Archive.class
                .getResourceAsStream("/org/apache/harmony/pack200/tests/pack200.pack.gz");
        out = new JarOutputStream(new FileOutputStream(File.createTempFile("pack", "200.jar")));
        Archive archive = new Archive(in, out);
        archive.unpack();
    }

    // Test with an archive containing Harmony's JNDI module
    public void testWithJNDIE1() throws Exception {
        in = Archive.class
                .getResourceAsStream("/org/apache/harmony/pack200/tests/jndi-e1.pack.gz");
        out = new JarOutputStream(new FileOutputStream(File.createTempFile("jndi", "-e1.jar")));
        Archive archive = new Archive(in, out);
        archive.unpack();
    }

    // Test with an archive containing Annotations
    public void testWithAnnotations() throws Exception {
        in = Archive.class
                .getResourceAsStream("/org/apache/harmony/pack200/tests/annotations.pack.gz");
        out = new JarOutputStream(new FileOutputStream(File.createTempFile("ann", "otations.jar")));
        Archive archive = new Archive(in, out);
        archive.unpack();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        try {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }





}
