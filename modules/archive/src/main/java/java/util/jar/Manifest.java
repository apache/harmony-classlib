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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.harmony.archive.internal.nls.Messages;
import org.apache.harmony.luni.util.ThreadLocalCache;

/**
 * The Manifest class is used to obtain attribute information for a JarFile and
 * its entries.
 */
public class Manifest implements Cloneable {
    static final int LINE_LENGTH_LIMIT = 72;

    private static final byte[] LINE_SEPARATOR = new byte[] { '\r', '\n' };

    private static final byte[] VALUE_SEPARATOR = new byte[] { ':', ' ' };

    private static final Attributes.Name NAME_ATTRIBUTE = new Attributes.Name(
            "Name"); //$NON-NLS-1$

    private Attributes mainAttributes = new Attributes();

    private HashMap<String, Attributes> entries = new HashMap<String, Attributes>();

    static class Chunk {
        int start;
        int end;

        Chunk(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private HashMap<String, Chunk> chunks;

    /**
     * Manifest bytes are used for delayed entry parsing.
     */
    private InitManifest im;

    /**
     * The end of the main attributes section in the manifest is needed in
     * verification.
     */
    private int mainEnd;

    /**
     * Constructs a new Manifest instance.
     */
    public Manifest() {
        super();
    }

    /**
     * Constructs a new Manifest instance using the attributes obtained from is.
     * 
     * @param is
     *            InputStream to parse for attributes
     * 
     * @throws IOException
     *             if an IO error occurs while creating this Manifest
     * 
     */
    public Manifest(InputStream is) throws IOException {
        super();
        read(is);
    }

    /**
     * Constructs a new Manifest instance. The new instance will have the same
     * attributes as those found in the parameter Manifest.
     * 
     * @param man
     *            Manifest instance to obtain attributes from
     */
    @SuppressWarnings("unchecked")
    public Manifest(Manifest man) {
        mainAttributes = (Attributes) man.mainAttributes.clone();
        entries = (HashMap<String, Attributes>) ((HashMap<String, Attributes>) man
                .getEntries()).clone();
    }

    Manifest(InputStream is, boolean readChunks) throws IOException {
        if (readChunks) {
            chunks = new HashMap<String, Chunk>();
        }
        read(is);
    }

    /**
     * Resets the both the mainAttributes as well as the entry Attributes
     * associated with this Manifest.
     */
    public void clear() {
        im = null;
        entries.clear();
        mainAttributes.clear();
    }

    /**
     * Returns the Attributes associated with the parameter entry name
     * 
     * @param name
     *            The name of the entry to obtain Attributes for.
     * @return The Attributes for the entry or null if the entry does not exist.
     */
    public Attributes getAttributes(String name) {
        return getEntries().get(name);
    }

    /**
     * Returns a Map containing the Attributes for each entry in the Manifest.
     * 
     * @return A Map of entry attributes
     */
    public Map<String, Attributes> getEntries() {
        initEntries();
        return entries;
    }

    private void initEntries() {
        if (im == null) {
            return;
        }
        // try {
        // im.initEntries(entries, chunks);
        // } catch (IOException ioe) {
        // throw new RuntimeException(ioe);
        // }
        // im = null;
    }

    /**
     * Returns the main Attributes of the JarFile.
     * 
     * @return Main Attributes associated with the source JarFile
     */
    public Attributes getMainAttributes() {
        return mainAttributes;
    }

    /**
     * Creates a copy of this Manifest. The returned Manifest will equal the
     * Manifest from which it was cloned.
     * 
     * @return A copy of the receiver.
     */
    @Override
    public Object clone() {
        return new Manifest(this);
    }

    /**
     * Writes out the attribute information of the receiver to the specified
     * OutputStream
     * 
     * @param os
     *            The OutputStream to write to.
     * 
     * @throws IOException
     *             If an error occurs writing the Manifest
     */
    public void write(OutputStream os) throws IOException {
        write(this, os);
    }

    /**
     * Constructs a new Manifest instance obtaining Attribute information from
     * the parameter InputStream.
     * 
     * @param is
     *            The InputStream to read from
     * @throws IOException
     *             If an error occurs reading the Manifest.
     */
    public void read(InputStream is) throws IOException {
        byte[] buf;
        try {
            buf = org.apache.harmony.luni.util.ByteBuffer.wrap(is);
        } catch (OutOfMemoryError oome) {
            throw new IOException(Messages.getString("archive.2E")); //$NON-NLS-1$
        }
        im = new InitManifest(buf, mainAttributes,
                Attributes.Name.MANIFEST_VERSION);
        mainEnd = im.getPos();
        // FIXME
        im.initEntries(entries, chunks);
        im = null;
    }

    /**
     * Returns the hashCode for this instance.
     * 
     * @return This Manifest's hashCode
     */
    @Override
    public int hashCode() {
        return mainAttributes.hashCode() ^ getEntries().hashCode();
    }

    /**
     * Determines if the receiver is equal to the parameter Object. Two
     * Manifests are equal if they have identical main Attributes as well as
     * identical entry Attributes.
     * 
     * @param o
     *            The Object to compare against.
     * @return <code>true</code> if the manifests are equal,
     *         <code>false</code> otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        if (!mainAttributes.equals(((Manifest) o).mainAttributes)) {
            return false;
        }
        return getEntries().equals(((Manifest) o).getEntries());
    }

    Chunk getChunk(String name) {
        return chunks.get(name);
    }

    void removeChunks() {
        chunks = null;
    }

    int getMainAttributesEnd() {
        return mainEnd;
    }

    /**
     * Writes out the attribute information of the receiver to the specified
     * OutputStream
     * 
     * @param manifest
     *            the attribute information of the receiver
     * @param out
     *            The OutputStream to write to.
     * 
     * @throws IOException
     *             If an error occurs writing the Manifest
     */
    static void write(Manifest manifest, OutputStream out) throws IOException {
        CharsetEncoder encoder = ThreadLocalCache.utf8Encoder.get();
        ByteBuffer buffer = ThreadLocalCache.byteBuffer.get();

        String version = manifest.mainAttributes
                .getValue(Attributes.Name.MANIFEST_VERSION);
        if (version != null) {
            writeEntry(out, Attributes.Name.MANIFEST_VERSION, version, encoder,
                    buffer);
            Iterator<?> entries = manifest.mainAttributes.keySet().iterator();
            while (entries.hasNext()) {
                Attributes.Name name = (Attributes.Name) entries.next();
                if (!name.equals(Attributes.Name.MANIFEST_VERSION)) {
                    writeEntry(out, name, manifest.mainAttributes
                            .getValue(name), encoder, buffer);
                }
            }
        }
        out.write(LINE_SEPARATOR);
        Iterator<String> i = manifest.getEntries().keySet().iterator();
        while (i.hasNext()) {
            String key = i.next();
            writeEntry(out, NAME_ATTRIBUTE, key, encoder, buffer);
            Attributes attrib = manifest.entries.get(key);
            Iterator<?> entries = attrib.keySet().iterator();
            while (entries.hasNext()) {
                Attributes.Name name = (Attributes.Name) entries.next();
                writeEntry(out, name, attrib.getValue(name), encoder, buffer);
            }
            out.write(LINE_SEPARATOR);
        }
    }

    private static void writeEntry(OutputStream os, Attributes.Name name,
            String value, CharsetEncoder encoder, ByteBuffer bBuf)
            throws IOException {
        byte[] out = name.getBytes();
        if (out.length > LINE_LENGTH_LIMIT) {
            throw new IOException(Messages.getString(
                    "archive.33", name, Integer.valueOf(LINE_LENGTH_LIMIT))); //$NON-NLS-1$
        }

        os.write(out);
        os.write(VALUE_SEPARATOR);

        encoder.reset();
        bBuf.clear().limit(LINE_LENGTH_LIMIT - out.length - 2);

        CharBuffer cBuf = CharBuffer.wrap(value);
        CoderResult r;

        while (true) {
            r = encoder.encode(cBuf, bBuf, true);
            if (CoderResult.UNDERFLOW == r) {
                r = encoder.flush(bBuf);
            }
            os.write(bBuf.array(), bBuf.arrayOffset(), bBuf.position());
            os.write(LINE_SEPARATOR);
            if (CoderResult.UNDERFLOW == r) {
                break;
            }
            os.write(' ');
            bBuf.clear().limit(LINE_LENGTH_LIMIT - 1);
        }
    }
}
