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
package org.apache.harmony.pack200;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;


/**
 *
 */
public class Archive {

    private final JarInputStream inputStream;
    private final OutputStream outputStream;
    private JarFile jarFile;

    public Archive(JarInputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public Archive(JarFile jarFile, OutputStream outputStream) {
        this.outputStream = outputStream;
        this.jarFile = jarFile;
        inputStream = null;
    }

    public void pack() throws Pack200Exception, IOException {
        List classes = new ArrayList();
        List files = new ArrayList();
        if(inputStream != null) {
            while(inputStream.available() > 0) {
                JarEntry jarEntry = inputStream.getNextJarEntry();
                if(jarEntry != null) {
                    addJarEntry(jarEntry, inputStream, classes, files);
                }
            }
        } else {
            Enumeration jarEntries = jarFile.entries();
            while(jarEntries.hasMoreElements()) {
                JarEntry jarEntry = (JarEntry) jarEntries.nextElement();
                addJarEntry(jarEntry, jarFile.getInputStream(jarEntry), classes, files);
            }
        }
        new Segment().pack(classes, files, outputStream);  // TODO: Multiple segments
    }

    private void addJarEntry(JarEntry jarEntry, InputStream stream, List javaClasses, List files) throws IOException, Pack200Exception {
        String name = jarEntry.getName();
        if(name.endsWith(".class")) {
            ClassParser classParser = new ClassParser(stream, name);
            JavaClass javaClass = classParser.parse();
            javaClasses.add(javaClass);
        } else {
            // TODO: it's a file...
        }
    }

}
