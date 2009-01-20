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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;
import java.util.zip.GZIPOutputStream;

import org.objectweb.asm.ClassReader;

/**
 *
 */
public class Archive {

    private final JarInputStream inputStream;
    private final OutputStream outputStream;
    private JarFile jarFile;
    private long segmentLimit = 1000000;
    private long currentSegmentSize;

    public Archive(JarInputStream inputStream, OutputStream outputStream,
            boolean gzip) throws IOException {
        this.inputStream = inputStream;
        if (gzip) {
            outputStream = new GZIPOutputStream(outputStream);
        }
        this.outputStream = new BufferedOutputStream(outputStream);
    }

    public Archive(JarFile jarFile, OutputStream outputStream) {
        this.outputStream = new BufferedOutputStream(outputStream);
        this.jarFile = jarFile;
        inputStream = null;
    }

    public void setSegmentLimit(int limit) {
        segmentLimit = limit;
    }

    public void pack() throws Pack200Exception, IOException {
        List classes = new ArrayList();
        List files = new ArrayList();
        Manifest manifest = jarFile != null ? jarFile.getManifest()
                : inputStream.getManifest();
        if (manifest != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            manifest.write(baos);
            files.add(new File("META-INF/MANIFEST.MF", baos.toByteArray(), 0));
        }
        if (inputStream != null) {
            while (inputStream.available() > 0) {
                JarEntry jarEntry = inputStream.getNextJarEntry();
                if (jarEntry != null) {
                    boolean added = addJarEntry(jarEntry,
                            new BufferedInputStream(inputStream), classes,
                            files);
                    if (!added) { // not added because segment has reached
                        // maximum size
                        new Segment().pack(classes, files, outputStream);
                        classes = new ArrayList();
                        files = new ArrayList();
                        currentSegmentSize = 0;
                        if (!addJarEntry(jarEntry, new BufferedInputStream(
                                inputStream), classes, files)) {
                            throw new Pack200Exception(
                                    "Segment limit is too small for the files you are trying to pack");
                        }
                    } else if (segmentLimit == 0) {
                        // create a new segment for each class
                        new Segment().pack(classes, files, outputStream);
                        classes = new ArrayList();
                        files = new ArrayList();
                    }
                }
            }
        } else {
            Enumeration jarEntries = jarFile.entries();
            while (jarEntries.hasMoreElements()) {
                JarEntry jarEntry = (JarEntry) jarEntries.nextElement();
                boolean added = addJarEntry(jarEntry, new BufferedInputStream(
                        jarFile.getInputStream(jarEntry)), classes, files);
                if (!added) { // not added because segment has reached maximum
                    // size
                    new Segment().pack(classes, files, outputStream);
                    classes = new ArrayList();
                    files = new ArrayList();
                    currentSegmentSize = 0;
                    if (!addJarEntry(jarEntry, new BufferedInputStream(jarFile
                            .getInputStream(jarEntry)), classes, files)) {
                        throw new Pack200Exception("Segment limit is too small");
                    }
                } else if (segmentLimit == 0) {
                    // create a new segment for each class
                    new Segment().pack(classes, files, outputStream);
                    classes = new ArrayList();
                    files = new ArrayList();
                }
            }
        }
        if(classes.size() > 0 || files.size() > 0) {
            new Segment().pack(classes, files, outputStream);
        }
        outputStream.close();
    }

    private boolean addJarEntry(JarEntry jarEntry, InputStream stream,
            List javaClasses, List files) throws IOException, Pack200Exception {
        String name = jarEntry.getName();
        long size = jarEntry.getSize();
        if (size > Integer.MAX_VALUE) {
            throw new RuntimeException("Large Class!");
        }
        if(segmentLimit != -1 && segmentLimit != 0) {
            // -1 is a special case where only one segment is created and
            // 0 is a special case where one segment is created for each file
            int packedSize = name.endsWith(".class") ? estimatePackedSize(size)
                    : (int) size;
            if (packedSize + currentSegmentSize > segmentLimit) {
                return false;
            } else {
                currentSegmentSize += packedSize;
            }
        }
        byte[] bytes = new byte[(int) size];
        int read = stream.read(bytes);
        if (read != size) {
            throw new RuntimeException("Error reading from stream");
        }
        if (name.endsWith(".class")) {
            ClassReader classParser = new Pack200ClassReader(bytes);
            javaClasses.add(classParser);
            bytes = new byte[0];
        }
        files.add(new File(name, bytes, jarEntry.getTime()));
        return true;
    }

    private int estimatePackedSize(long size) {
        return (int) size; // TODO: try to match the RI as closely as possible
    }

    static class File {

        private final String name;
        private final byte[] contents;
        private final long modtime;

        public File(String name, byte[] contents, long modtime) {
            this.name = name;
            this.contents = contents;
            this.modtime = modtime;
        }

        public byte[] getContents() {
            return contents;
        }

        public String getName() {
            return name;
        }

        public long getModtime() {
            return modtime;
        }
    }

}
