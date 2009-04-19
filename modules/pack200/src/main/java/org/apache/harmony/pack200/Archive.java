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
import java.util.jar.JarOutputStream;
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
    private boolean stripDebug;
    private int effort = 5;

    public Archive(JarInputStream inputStream, OutputStream outputStream,
            boolean gzip) throws IOException {
        this.inputStream = inputStream;
        if (gzip) {
            outputStream = new GZIPOutputStream(outputStream);
        }
        this.outputStream = new BufferedOutputStream(outputStream);
    }

    public Archive(JarFile jarFile, OutputStream outputStream,
            boolean gzip) throws IOException {

        if (gzip) {
            outputStream = new GZIPOutputStream(outputStream);
        }
        this.outputStream = new BufferedOutputStream(outputStream);
        this.jarFile = jarFile;
        inputStream = null;
    }

    public void setSegmentLimit(int limit) {
        segmentLimit = limit;
    }

    public void setEffort(int effort) {
        this.effort = effort;
    }

    public void stripDebugAttributes() {
        stripDebug = true;
    }

    public void pack() throws Pack200Exception, IOException {
        List classes = new ArrayList();
        List files = new ArrayList();

        if(effort == 0) {
            doZeroEffortPack();
        } else {

            if (inputStream != null) {
                Manifest manifest = jarFile != null ? jarFile.getManifest()
                        : inputStream.getManifest();
                if (manifest != null) {
                    System.out.println("manifest exists");
                    System.out.println(manifest.toString());
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    manifest.write(baos);
                    files.add(new File("META-INF", new byte[0], 0));
                    files.add(new File("META-INF/MANIFEST.MF", baos.toByteArray(), 0));
                }
                JarEntry jarEntry = inputStream.getNextJarEntry();
                while (jarEntry != null) {
                    if(jarEntry.getName().startsWith("META-INF")) {
                        System.out.println(jarEntry.getName());
                    }
                    boolean added = addJarEntry(jarEntry,
                            new BufferedInputStream(inputStream), classes,
                            files);
                    if (!added) { // not added because segment has reached
                        // maximum size
                        if(classes.size() > 0 || files.size() > 0) {
                            new Segment().pack(classes, files, outputStream, stripDebug, effort);
                            classes = new ArrayList();
                            files = new ArrayList();
                            currentSegmentSize = 0;
                            addJarEntry(jarEntry, new BufferedInputStream(inputStream), classes, files);
                            currentSegmentSize = 0; // ignore the size of the first entry for compatibility with the RI
                        }
                    } else if (segmentLimit == 0 && estimateSize(jarEntry) > 0) {
                        // create a new segment for each class unless size = 0
                        new Segment().pack(classes, files, outputStream, stripDebug, effort);
                        classes = new ArrayList();
                        files = new ArrayList();
                    }
                    jarEntry = inputStream.getNextJarEntry();
                }
            } else {
                Enumeration jarEntries = jarFile.entries();
                while (jarEntries.hasMoreElements()) {
                    JarEntry jarEntry = (JarEntry) jarEntries.nextElement();
                    boolean added = addJarEntry(jarEntry, new BufferedInputStream(
                            jarFile.getInputStream(jarEntry)), classes, files);
                    if (!added) { // not added because segment has reached maximum
                        // size
                        new Segment().pack(classes, files, outputStream, stripDebug, effort);
                        classes = new ArrayList();
                        files = new ArrayList();
                        currentSegmentSize = 0;
                        addJarEntry(jarEntry, new BufferedInputStream(jarFile
                                .getInputStream(jarEntry)), classes, files);
                        currentSegmentSize = 0; // ignore the size of the first entry for compatibility with the RI
                    } else if (segmentLimit == 0 && estimateSize(jarEntry) > 0) {
                        // create a new segment for each class unless size = 0
                        new Segment().pack(classes, files, outputStream, stripDebug, effort);
                        classes = new ArrayList();
                        files = new ArrayList();
                    }
                }
            }
            if(classes.size() > 0 || files.size() > 0) {
                new Segment().pack(classes, files, outputStream, stripDebug, effort);
            }
            outputStream.close();
        }
    }

    private void doZeroEffortPack() throws IOException, Pack200Exception {
        JarOutputStream jarOutputStream = new JarOutputStream(outputStream);
        if(inputStream != null) {
            JarInputStream jarInputStream;
            if(!(inputStream instanceof JarInputStream)) {
                jarInputStream = new JarInputStream(inputStream);
            } else {
                jarInputStream = inputStream;
            }
            Manifest manifest = jarInputStream.getManifest();
            if (manifest != null) {
                jarOutputStream.putNextEntry(new JarEntry("META-INF/"));
                jarOutputStream.closeEntry();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                manifest.write(baos);
                jarOutputStream.putNextEntry(new JarEntry("META-INF/MANIFEST.MF"));
                jarOutputStream.write(baos.toByteArray());
                jarOutputStream.closeEntry();
            }
            BufferedInputStream buff = new BufferedInputStream(jarInputStream);
            JarEntry jarEntry;
            while ((jarEntry = jarInputStream.getNextJarEntry()) != null) {
                jarOutputStream.putNextEntry(jarEntry);
                byte[] bytes = new byte[(int) jarEntry.getSize()];
                int bytesRead = buff.read(bytes);
                if(bytesRead != jarEntry.getSize()) {
                    throw new Pack200Exception("Error reading from input jar file");
                }
                jarOutputStream.write(bytes, 0, bytesRead);
                jarOutputStream.closeEntry();
            }
            jarOutputStream.close();
        } else {
            Enumeration jarEntries = jarFile.entries();
            while (jarEntries.hasMoreElements()) {
                JarEntry jarEntry = (JarEntry) jarEntries.nextElement();
                InputStream inStream = new BufferedInputStream(
                        jarFile.getInputStream(jarEntry));
                jarOutputStream.putNextEntry(jarEntry);
                byte[] bytes = new byte[16384];
                int bytesRead = inStream.read(bytes);
                while (bytesRead != -1) {
                    jarOutputStream.write(bytes, 0, bytesRead);
                    bytesRead = inStream.read(bytes);
                }
                jarOutputStream.closeEntry();
            }
            jarOutputStream.close();
        }
    }

    private boolean addJarEntry(JarEntry jarEntry, InputStream stream,
            List javaClasses, List files) throws IOException, Pack200Exception {
        String name = jarEntry.getName();
        long size = jarEntry.getSize();
        if (size > Integer.MAX_VALUE) {
            throw new RuntimeException("Large Class!"); // TODO: Should probably allow this
        } else if (size < 0) {
            size = 0;
//            throw new RuntimeException("Error: size for " + name + " is " + size);
        }
        if(segmentLimit != -1 && segmentLimit != 0) {
            // -1 is a special case where only one segment is created and
            // 0 is a special case where one segment is created for each file except for files in "META-INF"

            long packedSize = estimateSize(jarEntry);
            if (packedSize + currentSegmentSize > segmentLimit && currentSegmentSize > 0) {
                return false; // don't add this JarEntry to the current segment
            } else {
                currentSegmentSize += packedSize; // do add this JarEntry
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

    private long estimateSize(JarEntry jarEntry) {
        // The heuristic used here is for compatibility with the RI and should not be changed
        String name = jarEntry.getName();
        if(name.startsWith("META-INF") || name.startsWith("/META-INF")) {
            return 0;
        } else {
            long fileSize = jarEntry.getSize();
            if(fileSize < 0) {
                fileSize = 0;
            }
            return name.length() + fileSize + 5;
        }
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

        public String toString() {
            return name;
        }
    }

}
