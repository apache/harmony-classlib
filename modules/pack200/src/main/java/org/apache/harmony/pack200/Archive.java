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
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.zip.GZIPOutputStream;

/**
 * Archive is the main entry point to pack200 and represents a packed archive.
 * An archive is constructed with either a JarInputStream and an output stream
 * or a JarFile as input and an OutputStream. Options can be set, then
 * <code>pack()</code> is called, to pack the Jar file into a pack200 archive.
 */
public class Archive {

    private final JarInputStream inputStream;
    private final OutputStream outputStream;
    private JarFile jarFile;
    private long currentSegmentSize;
    private final PackingOptions options;

    /**
     * Creates an Archive with streams for the input and output.
     *
     * @param inputStream
     * @param outputStream
     * @param options - packing options (if null then defaults are used)
     * @throws IOException
     */
    public Archive(JarInputStream inputStream, OutputStream outputStream,
            PackingOptions options) throws IOException {
        this.inputStream = inputStream;
        if(options == null) { // use all defaults
            options = new PackingOptions();
        }
        this.options = options;
        if (options.isGzip()) {
            outputStream = new GZIPOutputStream(outputStream);
        }
        this.outputStream = new BufferedOutputStream(outputStream);
        PackingUtils.config(options);
    }

    /**
     * Creates an Archive with the given input file and a stream for the output
     *
     * @param jarFile - the input file
     * @param outputStream
     * @param options - packing options (if null then defaults are used)
     * @throws IOException
     */
    public Archive(JarFile jarFile, OutputStream outputStream,
            PackingOptions options) throws IOException {
        if(options == null) { // use all defaults
            options = new PackingOptions();
        }
        this.options = options;
        if (options.isGzip()) {
            outputStream = new GZIPOutputStream(outputStream);
        }
        this.outputStream = new BufferedOutputStream(outputStream);
        this.jarFile = jarFile;
        inputStream = null;
        PackingUtils.config(options);
    }

    /**
     * Pack the archive
     * @throws Pack200Exception
     * @throws IOException
     */
    public void pack() throws Pack200Exception, IOException {
        int effort = options.getEffort();
        if(effort == 0) {
            doZeroEffortPack();
        } else {
            List classes = new ArrayList();
            List files = new ArrayList();
            long segmentLimit = options.getSegmentLimit();
            List segmentUnitList = new ArrayList();
            if (inputStream != null) {
                Manifest manifest = jarFile != null ? jarFile.getManifest()
                        : inputStream.getManifest();
                if (manifest != null) {
                    PackingUtils.log("Pack META-INF/MANIFEST.MF");
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    manifest.write(baos);
                    files.add(new PackingFile("META-INF/MANIFEST.MF", baos.toByteArray(), 0));
                }
                JarEntry jarEntry = inputStream.getNextJarEntry();
                while (jarEntry != null) {
                    if(jarEntry.getName().startsWith("META-INF")) {
                        PackingUtils.log("Pack " + jarEntry.getName());
                    }
                    boolean added = addJarEntry(jarEntry,
                            new BufferedInputStream(inputStream), classes,
                            files);
                    if (!added) { // not added because segment has reached
                        // maximum size
                        if(classes.size() > 0 || files.size() > 0) {
                            segmentUnitList.add(new SegmentUnit(classes, files));
                            classes = new ArrayList();
                            files = new ArrayList();
                            currentSegmentSize = 0;
                            addJarEntry(jarEntry, new BufferedInputStream(inputStream), classes, files);
                            currentSegmentSize = 0; // ignore the size of the first entry for compatibility with the RI
                        }
                    } else if (segmentLimit == 0 && estimateSize(jarEntry) > 0) {
                        // create a new segment for each class unless size = 0
                        segmentUnitList.add(new SegmentUnit(classes, files));
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
                        segmentUnitList.add(new SegmentUnit(classes, files));
                        classes = new ArrayList();
                        files = new ArrayList();
                        currentSegmentSize = 0;
                        addJarEntry(jarEntry, new BufferedInputStream(jarFile
                                .getInputStream(jarEntry)), classes, files);
                        currentSegmentSize = 0; // ignore the size of the first entry for compatibility with the RI
                    } else if (segmentLimit == 0 && estimateSize(jarEntry) > 0) {
                        // create a new segment for each class unless size = 0
                        segmentUnitList.add(new SegmentUnit(classes, files));
                        classes = new ArrayList();
                        files = new ArrayList();
                    }
                }
            }
            if(classes.size() > 0 || files.size() > 0) {
                segmentUnitList.add(new SegmentUnit(classes, files));
            }

            int size = segmentUnitList.size();
            int classFileAmount = 0;
            int fileAmount = 0;
            int totalByteAmount = 0;
            int totalPackedByteAmount = 0;
            SegmentUnit segmentUnit = null;
            for (int index = 0; index < size; index++) {
                segmentUnit = (SegmentUnit) segmentUnitList.get(index);
                classFileAmount += segmentUnit.classList.size();
                fileAmount += segmentUnit.fileList.size();
                new Segment().pack(segmentUnit, outputStream, options);
                totalByteAmount += segmentUnit.getByteAmount();
                totalPackedByteAmount += segmentUnit.getPackedByteAmount();
            }
            PackingUtils.log("Total: Packed " + fileAmount + " files including "
                    + classFileAmount + " classes of " + totalByteAmount
                    + " input bytes into " + totalPackedByteAmount + " bytes");

            outputStream.close();
        }
    }

    private void doZeroEffortPack() throws IOException, Pack200Exception {
        PackingUtils.log("Start to perform a zero-effort packing");
        JarOutputStream jarOutputStream = new JarOutputStream(outputStream);
        if(inputStream != null) {
            Manifest manifest = inputStream.getManifest();
            if (manifest != null) {
                jarOutputStream.putNextEntry(new JarEntry("META-INF/"));
                jarOutputStream.closeEntry();
                PackingUtils.log("Packed \"META-INF\" folder");
                
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                manifest.write(baos);
                jarOutputStream.putNextEntry(new JarEntry("META-INF/MANIFEST.MF"));
                jarOutputStream.write(baos.toByteArray());
                jarOutputStream.closeEntry();
                PackingUtils.log("Packed META-INF/MANIFEST.MF");
            }
            BufferedInputStream buff = new BufferedInputStream(inputStream);
            JarEntry jarEntry;
            while ((jarEntry = inputStream.getNextJarEntry()) != null) {
                jarOutputStream.putNextEntry(jarEntry);
                byte[] bytes = new byte[(int) jarEntry.getSize()];
                int bytesRead = buff.read(bytes);
                if(bytesRead != jarEntry.getSize()) {
                    throw new Pack200Exception("Error reading from input jar file");
                }
                jarOutputStream.write(bytes, 0, bytesRead);
                jarOutputStream.closeEntry();
                PackingUtils.log("Packed " + jarEntry.getName());
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
                PackingUtils.log("Packed " + jarEntry.getName());
            }
            jarOutputStream.close();
        }
    }

    private boolean addJarEntry(JarEntry jarEntry, InputStream stream,
            List javaClasses, List files) throws IOException, Pack200Exception {
        long segmentLimit = options.getSegmentLimit();
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
        if (name.endsWith(".class") && !options.isPassFile(name)) {
            Pack200ClassReader classParser = new Pack200ClassReader(bytes);
            classParser.setFileName(name);
            javaClasses.add(classParser);
            bytes = new byte[0];
        }
        files.add(new PackingFile(name, bytes, jarEntry.getTime()));
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

    static class SegmentUnit {

        private List classList;

        private List fileList;

        private int byteAmount = 0;

        private int packedByteAmount = 0;

        public SegmentUnit(List classes, List files) {
            classList = classes;
            fileList = files;

            // Calculate the amount of bytes in classes and files before packing
            Pack200ClassReader classReader;
            for (Iterator iterator = classList.iterator(); iterator.hasNext();) {
                classReader = (Pack200ClassReader) iterator.next();
                byteAmount += classReader.b.length;
            }

            PackingFile file;
            for (Iterator iterator = fileList.iterator(); iterator.hasNext();) {
                file = (PackingFile) iterator.next();
                byteAmount += file.contents.length;
            }
        }

        public List getClassList() {
            return classList;
        }

        public int classListSize() {
            return classList.size();
        }

        public int fileListSize() {
            return fileList.size();
        }

        public List getFileList() {
            return fileList;
        }

        public int getByteAmount() {
            return byteAmount;
        }

        public int getPackedByteAmount() {
            return packedByteAmount;
        }

        public void addPackedByteAmount(int amount) {
            packedByteAmount += amount;
        }
    }

    static class PackingFile {

        private final String name;
        private byte[] contents;
        private final long modtime;

        public PackingFile(String name, byte[] contents, long modtime) {
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

        public void setContents(byte[] contents) {
            this.contents = contents;
        }
    }

}
