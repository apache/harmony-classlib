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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

    private JarOutputStream outputStream;

    private boolean removePackFile;

    private int logLevel = LOG_LEVEL_STANDARD;

    private FileOutputStream logFile;

    private boolean overrideDeflateHint;

    private boolean deflateHint;

    public Archive(String inputFile, String outputFile)
            throws FileNotFoundException, IOException {
        inputStream = new FileInputStream(inputFile);
        outputStream = new JarOutputStream(new FileOutputStream(outputFile));
    }

    public Archive(InputStream inputStream, JarOutputStream outputStream)
            throws FileNotFoundException, IOException {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

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
            int[] jarMagic = { 0xCA, 0xFE, 0xBA, 0xBE }; // Magic word for a Jar file
            byte word[] = new byte[4];
            inputStream.read(word);
            boolean compressedWithE0 = true;
            for (int m = 0; m < jarMagic.length; m++) {
                if (word[m] != jarMagic[m]) {
                    compressedWithE0 = false;
                }
            }
            inputStream.reset();
            if(compressedWithE0) { // The original Jar was not packed, so just copy it across
                byte[] bytes = new byte[16384];
                int bytesRead = 0;
                while(bytesRead != -1) {
                    bytesRead = inputStream.read(bytes);                    
                    outputStream.write(bytes, 0, bytesRead);
                }
            } else {
                while (inputStream.available() > 0) {
                    Segment segment = new Segment();
                    segment.setLogLevel(logLevel);
                    segment.setLogStream(logFile != null ? (OutputStream) logFile
                            : (OutputStream) System.out);
                    if (overrideDeflateHint) {
                        segment.overrideDeflateHint(deflateHint);
                    }
                    segment.unpack(inputStream, outputStream);
                }
            }
        } catch (IOException e) {
            try {
                inputStream.close();
            } finally {
                outputStream.close();
            }
        }
        if (removePackFile) {

        }
    }

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
        ;
    }

    public void setLogFile(String logFileName) throws FileNotFoundException {
        this.logFile = new FileOutputStream(logFileName);
    }

    public void setDeflateHint(boolean deflateHint) {
        overrideDeflateHint = true;
        this.deflateHint = deflateHint;
    }

}