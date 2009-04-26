/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.util.jar;

import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.harmony.archive.util.Util;

/**
 * {@code JarFile} is used to read jar entries and their associated data from
 * jar files.
 * 
 * @see JarInputStream
 * @see JarEntry
 */
public class JarFile extends ZipFile {

    /**
     * The MANIFEST file name.
     */
    public static final String MANIFEST_NAME = "META-INF/MANIFEST.MF"; //$NON-NLS-1$

    static final String META_DIR = "META-INF/"; //$NON-NLS-1$

    private Manifest manifest;

    private ZipEntry manifestEntry;

    JarVerifier verifier;

    static final class JarFileInputStream extends FilterInputStream {
        private long count;

        private ZipEntry zipEntry;

        private JarVerifier.VerifierEntry entry;

        JarFileInputStream(InputStream is, ZipEntry ze,
                JarVerifier.VerifierEntry e) {
            super(is);
            zipEntry = ze;
            count = zipEntry.getSize();
            entry = e;
        }

        @Override
        public int read() throws IOException {
            if (count > 0) {
                int r = super.read();
                if (r != -1) {
                    entry.write(r);
                    count--;
                } else {
                    count = 0;
                }
                if (count == 0) {
                    entry.verify();
                }
                return r;
            } else {
                return -1;
            }
        }

        @Override
        public int read(byte[] buf, int off, int nbytes) throws IOException {
            if (count > 0) {
                int r = super.read(buf, off, nbytes);
                if (r != -1) {
                    int size = r;
                    if (count < size) {
                        size = (int) count;
                    }
                    entry.write(buf, off, size);
                    count -= size;
                } else {
                    count = 0;
                }
                if (count == 0) {
                    entry.verify();
                }
                return r;
            } else {
                return -1;
            }
        }

        @Override
        public long skip(long nbytes) throws IOException {
            long cnt = 0, rem = 0;
            byte[] buf = new byte[4096];
            while (cnt < nbytes) {
                int x = read(buf, 0,
                        (rem = nbytes - cnt) > buf.length ? buf.length
                                : (int) rem);
                if (x == -1) {
                    return cnt;
                }
                cnt += x;
            }
            return cnt;
        }
    }

    /**
     * Create a new {@code JarFile} using the contents of the specified file.
     * 
     * @param file
     *            the JAR file as {@link File}.
     * @throws IOException
     *             If the file cannot be read.
     */
    public JarFile(File file) throws IOException {
        this(file, true);
    }

    /**
     * Create a new {@code JarFile} using the contents of the specified file.
     * 
     * @param file
     *            the JAR file as {@link File}.
     * @param verify
     *            if this JAR file is signed whether it must be verified.
     * @throws IOException
     *             If the file cannot be read.
     */
    public JarFile(File file, boolean verify) throws IOException {
        super(file);
        if (verify) {
            verifier = new JarVerifier(file.getPath());
        }
        readMetaEntries();
    }

    /**
     * Create a new {@code JarFile} using the contents of file.
     * 
     * @param file
     *            the JAR file as {@link File}.
     * @param verify
     *            if this JAR filed is signed whether it must be verified.
     * @param mode
     *            the mode to use, either {@link ZipFile#OPEN_READ OPEN_READ} or
     *            {@link ZipFile#OPEN_DELETE OPEN_DELETE}.
     * @throws IOException
     *             If the file cannot be read.
     */
    public JarFile(File file, boolean verify, int mode) throws IOException {
        super(file, mode);
        if (verify) {
            verifier = new JarVerifier(file.getPath());
        }
        readMetaEntries();
    }

    /**
     * Create a new {@code JarFile} from the contents of the file specified by
     * filename.
     * 
     * @param filename
     *            the file name referring to the JAR file.
     * @throws IOException
     *             if file name cannot be opened for reading.
     */
    public JarFile(String filename) throws IOException {
        this(filename, true);

    }

    /**
     * Create a new {@code JarFile} from the contents of the file specified by
     * {@code filename}.
     * 
     * @param filename
     *            the file name referring to the JAR file.
     * @param verify
     *            if this JAR filed is signed whether it must be verified.
     * @throws IOException
     *             If file cannot be opened or read.
     */
    public JarFile(String filename, boolean verify) throws IOException {
        super(filename);
        if (verify) {
            verifier = new JarVerifier(filename);
        }
        readMetaEntries();
    }

    /**
     * Return an enumeration containing the {@code JarEntrys} contained in this
     * {@code JarFile}.
     * 
     * @return the {@code Enumeration} containing the JAR entries.
     * @throws IllegalStateException
     *             if this {@code JarFile} is closed.
     */
    @Override
    public Enumeration<JarEntry> entries() {
        class JarFileEnumerator implements Enumeration<JarEntry> {
            Enumeration<? extends ZipEntry> ze;

            JarFile jf;

            JarFileEnumerator(Enumeration<? extends ZipEntry> zenum, JarFile jf) {
                ze = zenum;
                this.jf = jf;
            }

            public boolean hasMoreElements() {
                return ze.hasMoreElements();
            }

            public JarEntry nextElement() {
                JarEntry je = new JarEntry(ze.nextElement());
                je.parentJar = jf;
                return je;
            }
        }
        return new JarFileEnumerator(super.entries(), this);
    }

    /**
     * Return the {@code JarEntry} specified by its name or {@code null} if no
     * such entry exists.
     * 
     * @param name
     *            the name of the entry in the JAR file.
     * @return the JAR entry defined by the name.
     */
    public JarEntry getJarEntry(String name) {
        return (JarEntry) getEntry(name);
    }

    /**
     * Returns the {@code Manifest} object associated with this {@code JarFile}
     * or {@code null} if no MANIFEST entry exists.
     * 
     * @return the MANIFEST.
     * @throws IOException
     *             if an error occurs reading the MANIFEST file.
     * @throws IllegalStateException
     *             if the jar file is closed.
     * @see Manifest
     */
    public Manifest getManifest() throws IOException {
        if (manifest != null) {
            return manifest;
        }
        try {
            InputStream is = super.getInputStream(manifestEntry);
            if (verifier != null) {
                byte[] buf = new byte[is.available()];
                is.mark(buf.length);
                is.read(buf, 0, buf.length);
                is.reset();
                verifier.addMetaEntry(manifestEntry.getName(), buf);
            }
            try {
                manifest = new Manifest(is, verifier != null);
            } finally {
                is.close();
            }
            manifestEntry = null;
        } catch (NullPointerException e) {
            manifestEntry = null;
        }
        return manifest;
    }

    private void readMetaEntries() throws IOException {
        ZipEntry[] metaEntries = getMetaEntriesImpl(null);
        int dirLength = META_DIR.length();

        boolean signed = false;

        if (null != metaEntries) {
            for (ZipEntry entry : metaEntries) {
                String entryName = entry.getName();
                if (manifestEntry == null
                        && manifest == null
                        && Util.ASCIIIgnoreCaseRegionMatches(entryName,
                                dirLength, MANIFEST_NAME, dirLength,
                                MANIFEST_NAME.length() - dirLength)) {
                    manifestEntry = entry;
                    if (verifier == null) {
                        break;
                    }
                } else if (verifier != null
                        && entryName.length() > dirLength
                        && (Util.ASCIIIgnoreCaseRegionMatches(entryName,
                                entryName.length() - 3, ".SF", 0, 3) //$NON-NLS-1$
                                || Util.ASCIIIgnoreCaseRegionMatches(entryName,
                                        entryName.length() - 4, ".DSA", 0, 4) //$NON-NLS-1$
                        || Util.ASCIIIgnoreCaseRegionMatches(entryName,
                                entryName.length() - 4, ".RSA", 0, 4))) { //$NON-NLS-1$
                    signed = true;
                    InputStream is = super.getInputStream(entry);
                    byte[] buf = new byte[is.available()];
                    try {
                        is.read(buf, 0, buf.length);
                    } finally {
                        is.close();
                    }
                    verifier.addMetaEntry(entryName, buf);
                }
            }
        }
        if (!signed) {
            verifier = null;
        }
    }

    /**
     * Return an {@code InputStream} for reading the decompressed contents of
     * ZIP entry.
     * 
     * @param ze
     *            the ZIP entry to be read.
     * @return the input stream to read from.
     * @throws IOException
     *             if an error occurred while creating the input stream.
     */
    @Override
    public InputStream getInputStream(ZipEntry ze) throws IOException {
        if (manifestEntry != null) {
            getManifest();
        }
        if (verifier != null) {
            verifier.setManifest(getManifest());
            if (manifest != null) {
                verifier.mainAttributesEnd = manifest.getMainAttributesEnd();
            }
            if (verifier.readCertificates()) {
                verifier.removeMetaEntries();
                if (manifest != null) {
                    manifest.removeChunks();
                }
                if (!verifier.isSignedJar()) {
                    verifier = null;
                }
            }
        }
        InputStream in = super.getInputStream(ze);
        if (in == null) {
            return null;
        }
        if (verifier == null || ze.getSize() == -1) {
            return in;
        }
        JarVerifier.VerifierEntry entry = verifier.initEntry(ze.getName());
        if (entry == null) {
            return in;
        }
        return new JarFileInputStream(in, ze, entry);
    }

    /**
     * Return the {@code JarEntry} specified by name or {@code null} if no such
     * entry exists.
     * 
     * @param name
     *            the name of the entry in the JAR file.
     * @return the ZIP entry extracted.
     */
    @Override
    public ZipEntry getEntry(String name) {
        ZipEntry ze = super.getEntry(name);
        if (ze == null) {
            return ze;
        }
        JarEntry je = new JarEntry(ze);
        je.parentJar = this;
        return je;
    }

    private native ZipEntry[] getMetaEntriesImpl(byte[] buf);

}
