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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.zip.GZIPInputStream;

/**
 * The Archive class is the main entry point to unpack200. An archive is
 * constructed with either two file names, a pack file and an output file name
 * or two input streams corresponding to the input and the output streams. Then
 * <code>unpack()</code> is called, to unpack the pack200 archive.
 */
public class Archive {

    private static final int LOG_LEVEL_VERBOSE = 2;

    private static final int LOG_LEVEL_STANDARD = 1;

    private static final int LOG_LEVEL_QUIET = 0;

    private InputStream inputStream;

    private final JarOutputStream outputStream;

    private boolean removePackFile;

    private int logLevel = LOG_LEVEL_STANDARD;

    private FileOutputStream logFile;

    private boolean overrideDeflateHint;

    private boolean deflateHint;

    private String inputFileName;

    /**
     * Creates an Archive with the given input and output file names.
     * @param inputFile
     * @param outputFile
     * @throws FileNotFoundException if the input file does not exist
     * @throws IOException
     */
    public Archive(String inputFile, String outputFile)
            throws FileNotFoundException, IOException {
        this.inputFileName = inputFile;
        inputStream = new FileInputStream(inputFile);
        outputStream = new JarOutputStream(new FileOutputStream(outputFile));
    }

    /**
     * Creates an Archive with streams for the input and output files.
     * Note: If you use this method then calling {@link #setRemovePackFile(boolean)}
     * will have no effect.
     * @param inputStream
     * @param outputStream
     * @throws IOException
     */
    public Archive(InputStream inputStream, JarOutputStream outputStream)
            throws IOException {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    /**
     * Unpacks the Archive from the input file to the output file
     * @throws Pack200Exception
     * @throws IOException
     */
    public void unpack() throws Pack200Exception, IOException {
        outputStream.setComment("PACK200");
        try {
            if (!inputStream.markSupported()) {
                inputStream = new BufferedInputStream(inputStream);
                if (!inputStream.markSupported())
                    throw new IllegalStateException();
            }
            inputStream.mark(2);
            if (((inputStream.read() & 0xFF) | (inputStream.read() & 0xFF) << 8) == GZIPInputStream.GZIP_MAGIC) {
                inputStream.reset();
                inputStream = new BufferedInputStream(new GZIPInputStream(
                        inputStream));
            } else {
                inputStream.reset();
            }
            inputStream.mark(4);
            int[] magic = { 0xCA, 0xFE, 0xD0, 0x0D }; // Magic word for pack200
            int word[] = new int[4];
            for (int i = 0; i < word.length; i++) {
                word[i] = inputStream.read();
            }
            boolean compressedWithE0 = false;
            for (int m = 0; m < magic.length; m++) {
                if (word[m] != magic[m]) {
                    compressedWithE0 = true;
                }
            }
            inputStream.reset();
            if(compressedWithE0) { // The original Jar was not packed, so just copy it across
                JarInputStream jarInputStream = new JarInputStream(inputStream);
                JarEntry jarEntry;
                while((jarEntry = jarInputStream.getNextJarEntry()) != null) {
                    outputStream.putNextEntry(jarEntry);
                    byte[] bytes = new byte[16384];
                    int bytesRead = jarInputStream.read(bytes);
                    while(bytesRead != -1) {
                        outputStream.write(bytes, 0, bytesRead);
                        bytesRead = jarInputStream.read(bytes);
                    }
                    outputStream.closeEntry();
                }
            } else {
                while (available(inputStream)) {
                    Segment segment = new Segment();
                    segment.setLogLevel(logLevel);
                    segment.setLogStream(logFile != null ? (OutputStream) logFile
                            : (OutputStream) System.out);
                    if (overrideDeflateHint) {
                        segment.overrideDeflateHint(deflateHint);
                    }
                    segment.unpack(inputStream, outputStream);
                    outputStream.flush();
                }
            }
        } finally {
            try {
                inputStream.close();
            } catch (Exception e2) {}
            try {
                outputStream.close();
            } catch (Exception e2) {}
        }
        if (removePackFile) {
            File file = new File(inputFileName);
            boolean deleted = file.delete();
            if(!deleted) {
            	throw new Pack200Exception("Failed to delete the input file.");
            }
        }
    }

    private boolean available(InputStream inputStream) throws IOException {
        inputStream.mark(1);
        int check = inputStream.read();
        inputStream.reset();
        return check != -1;
    }

    /**
     * If removePackFile is set to true, the input file is deleted after
     * unpacking
     *
     * @param removePackFile
     */
    public void setRemovePackFile(boolean removePackFile) {
        this.removePackFile = removePackFile;
    }

    public void setVerbose(boolean verbose) {
        if (verbose) {
            logLevel = LOG_LEVEL_VERBOSE;
        } else if (logLevel == LOG_LEVEL_VERBOSE) {
            logLevel = LOG_LEVEL_STANDARD;
        }
    }

    public void setQuiet(boolean quiet) {
        if (quiet) {
            logLevel = LOG_LEVEL_QUIET;
        } else if (logLevel == LOG_LEVEL_QUIET) {
            logLevel = LOG_LEVEL_QUIET;
        }
    }

    public void setLogFile(String logFileName) throws FileNotFoundException {
        this.logFile = new FileOutputStream(logFileName);
    }

    public void setDeflateHint(boolean deflateHint) {
        overrideDeflateHint = true;
        this.deflateHint = deflateHint;
    }

}