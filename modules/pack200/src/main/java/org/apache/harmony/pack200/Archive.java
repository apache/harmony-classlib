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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

import org.objectweb.asm.ClassReader;


/**
 *
 */
public class Archive {

    private final JarInputStream inputStream;
    private final OutputStream outputStream;
    private JarFile jarFile;

    public Archive(JarInputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = new BufferedOutputStream(outputStream);
    }

    public Archive(JarFile jarFile, OutputStream outputStream) {
        this.outputStream = new BufferedOutputStream(outputStream);
        this.jarFile = jarFile;
        inputStream = null;
    }

    public void pack() throws Pack200Exception, IOException {
        List classes = new ArrayList();
        List files = new ArrayList();
        List classNames = new ArrayList();
        List classModtimes = new ArrayList();
        if(inputStream != null) {
            while(inputStream.available() > 0) {
                JarEntry jarEntry = inputStream.getNextJarEntry();
                if(jarEntry != null) {
                    addJarEntry(jarEntry, new BufferedInputStream(inputStream), classes, classNames, classModtimes, files);
                }
            }
        } else {
            Enumeration jarEntries = jarFile.entries();
            while(jarEntries.hasMoreElements()) {
                JarEntry jarEntry = (JarEntry) jarEntries.nextElement();
                addJarEntry(jarEntry, new BufferedInputStream(jarFile.getInputStream(jarEntry)), classes, classNames, classModtimes, files);
            }
        }
        new Segment().pack(classes, classNames, classModtimes, files, outputStream);  // TODO: Multiple segments
        outputStream.close();
    }

    private void addJarEntry(JarEntry jarEntry, InputStream stream, List javaClasses, List classNames, List classModtimes, List files) throws IOException, Pack200Exception {
        String name = jarEntry.getName();
        if(name.endsWith(".class")) {
            long size = jarEntry.getSize();
            if (size > Integer.MAX_VALUE) {
                throw new RuntimeException("Large Class!");
            }
            byte[] bytes = new byte[(int)size];
            int read = stream.read(bytes);
            if(read != size) {
                throw new RuntimeException("Error reading from stream");
            }
            ClassReader classParser = new Pack200ClassReader(bytes);
            javaClasses.add(classParser);
            classNames.add(name);
            classModtimes.add(new Long(jarEntry.getTime()));
        } else {
            // TODO: it's a file...
        }
    }

}
