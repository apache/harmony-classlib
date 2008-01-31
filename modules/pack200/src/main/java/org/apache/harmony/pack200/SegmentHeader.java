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

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class SegmentHeader {

    private int archiveMajor;

    private int archiveMinor;

    private long archiveModtime;

    private long archiveSize;

    private int attributeDefinitionCount;

    private InputStream bandHeadersInputStream;

    private int bandHeadersSize;

    private int classCount;

    private int cpClassCount;

    private int cpDescriptorCount;

    private int cpDoubleCount;

    private int cpFieldCount;

    private int cpFloatCount;

    private int cpIMethodCount;

    private int cpIntCount;

    private int cpLongCount;

    private int cpMethodCount;

    private int cpSignatureCount;

    private int cpStringCount;

    private int cpUTF8Count;

    private int defaultClassMajorVersion;

    private int defaultClassMinorVersion;

    private int innerClassCount;

    private int numberOfFiles;

    private int segmentsRemaining;

    private SegmentOptions options;


    /**
     * The magic header for a Pack200 Segment is 0xCAFED00D. I wonder where they
     * get their inspiration from ...
     */
    private static final int[] magic = { 0xCA, 0xFE, 0xD0, 0x0D };

    public void unpack(InputStream in) throws IOException,
            Pack200Exception, Error, Pack200Exception {
        long word[] = decodeScalar("archive_magic_word", in, Codec.BYTE1,
                magic.length);
        for (int m = 0; m < magic.length; m++)
            if (word[m] != magic[m])
                throw new Error("Bad header");
        setArchiveMinorVersion((int) decodeScalar("archive_minver", in,
                Codec.UNSIGNED5));
        setArchiveMajorVersion((int) decodeScalar("archive_majver", in,
                Codec.UNSIGNED5));
        options = new SegmentOptions((int) decodeScalar("archive_options", in,
                Codec.UNSIGNED5));
        parseArchiveFileCounts(in);
        parseArchiveSpecialCounts(in);
        parseCpCounts(in);
        parseClassCounts(in);

        if (getBandHeadersSize() > 0) {
            byte[] bandHeaders = new byte[getBandHeadersSize()];
            readFully(in, bandHeaders);
            setBandHeadersData(bandHeaders);
        }
    }

    /**
     * Sets the minor version of this archive
     *
     * @param version
     *            the minor version of the archive
     * @throws Pack200Exception
     *             if the minor version is not 7
     */
    private void setArchiveMinorVersion(int version) throws Pack200Exception {
        if (version != 7)
            throw new Pack200Exception("Invalid segment minor version");
        archiveMinor = version;
    }

    /**
     * Sets the major version of this archive.
     *
     * @param version
     *            the minor version of the archive
     * @throws Pack200Exception
     *             if the major version is not 150
     */
    private void setArchiveMajorVersion(int version) throws Pack200Exception {
        if (version != 150)
            throw new Pack200Exception("Invalid segment major version: " + version);
        archiveMajor = version;
    }

    public long getArchiveModtime() {
        return archiveModtime;
    }

    public int getArchiveMajor() {
        return archiveMajor;
    }

    public int getArchiveMinor() {
        return archiveMinor;
    }

    public int getAttributeDefinitionCount() {
        return attributeDefinitionCount;
    }

    public int getClassCount() {
        return classCount;
    }

    public int getCpClassCount() {
        return cpClassCount;
    }

    public int getCpDescriptorCount() {
        return cpDescriptorCount;
    }

    public int getCpDoubleCount() {
        return cpDoubleCount;
    }

    public int getCpFieldCount() {
        return cpFieldCount;
    }

    public int getCpFloatCount() {
        return cpFloatCount;
    }

    public int getCpIMethodCount() {
        return cpIMethodCount;
    }

    public int getCpIntCount() {
        return cpIntCount;
    }

    public int getCpLongCount() {
        return cpLongCount;
    }

    public int getCpMethodCount() {
        return cpMethodCount;
    }

    public int getCpSignatureCount() {
        return cpSignatureCount;
    }

    public int getCpStringCount() {
        return cpStringCount;
    }

    public int getCpUTF8Count() {
        return cpUTF8Count;
    }

    public int getDefaultClassMajorVersion() {
        return defaultClassMajorVersion;
    }

    public int getDefaultClassMinorVersion() {
        return defaultClassMinorVersion;
    }

    public int getInnerClassCount() {
        return innerClassCount;
    }

    public void setNumberOfFiles(int numberOfFiles) {
        this.numberOfFiles = numberOfFiles;
    }

    public long getArchiveSize() {
        return archiveSize;
    }

    /**
     * Obtain the band headers data as an input stream. If no band headers are
     * present, this will return an empty input stream to prevent any further
     * reads taking place.
     *
     * Note that as a stream, data consumed from this input stream can't be
     * re-used. Data is only read from this stream if the encoding is such that
     * additional information needs to be decoded from the stream itself.
     *
     * @return the band headers input stream
     */
    public InputStream getBandHeadersInputStream() {
        if (bandHeadersInputStream == null) {
            bandHeadersInputStream = new ByteArrayInputStream(new byte[0]);
        }
        return bandHeadersInputStream;

    }

    public int getNumberOfFiles() {
        return numberOfFiles;
    }

    public int getSegmentsRemaining() {
        return segmentsRemaining;
    }

    public SegmentOptions getOptions() {
        return options;
    }

    private void parseArchiveFileCounts(InputStream in) throws IOException,
            Pack200Exception {
        if (options.hasArchiveFileCounts()) {
            setArchiveSize(decodeScalar("archive_size_hi", in, Codec.UNSIGNED5) << 32
                    | decodeScalar("archive_size_lo", in, Codec.UNSIGNED5));
            setSegmentsRemaining(decodeScalar("archive_next_count", in,
                    Codec.UNSIGNED5));
            setArchiveModtime(decodeScalar("archive_modtime", in,
                    Codec.UNSIGNED5));
            numberOfFiles = (int)decodeScalar("file_count", in, Codec.UNSIGNED5);
        }
    }

    private void parseArchiveSpecialCounts(InputStream in) throws IOException,
            Pack200Exception {
        if (getOptions().hasSpecialFormats()) {
            bandHeadersSize = (int)decodeScalar("band_headers_size", in,
                    Codec.UNSIGNED5);
            setAttributeDefinitionCount(decodeScalar("attr_definition_count",
                    in, Codec.UNSIGNED5));
        }
    }

    private void parseClassCounts(InputStream in) throws IOException,
            Pack200Exception {
        innerClassCount = (int)decodeScalar("ic_count", in, Codec.UNSIGNED5);
        defaultClassMinorVersion = (int)decodeScalar("default_class_minver", in,
                Codec.UNSIGNED5);
        defaultClassMajorVersion = (int)decodeScalar("default_class_majver", in,
                Codec.UNSIGNED5);
        classCount = (int)decodeScalar("class_count", in, Codec.UNSIGNED5);
    }

    private void parseCpCounts(InputStream in) throws IOException,
            Pack200Exception {
        cpUTF8Count = (int)decodeScalar("cp_Utf8_count", in, Codec.UNSIGNED5);
        if (getOptions().hasCPNumberCounts()) {
            cpIntCount = (int)decodeScalar("cp_Int_count", in, Codec.UNSIGNED5);
            cpFloatCount = (int)decodeScalar("cp_Float_count", in, Codec.UNSIGNED5);
            cpLongCount = (int)decodeScalar("cp_Long_count", in, Codec.UNSIGNED5);
            cpDoubleCount = (int)decodeScalar("cp_Double_count", in,
                    Codec.UNSIGNED5);
        }
        cpStringCount = (int)decodeScalar("cp_String_count", in, Codec.UNSIGNED5);
        cpClassCount = (int)decodeScalar("cp_Class_count", in, Codec.UNSIGNED5);
        cpSignatureCount = (int)decodeScalar("cp_Signature_count", in,
                Codec.UNSIGNED5);
        cpDescriptorCount = (int)decodeScalar("cp_Descr_count", in, Codec.UNSIGNED5);
        cpFieldCount = (int)decodeScalar("cp_Field_count", in, Codec.UNSIGNED5);
        cpMethodCount = (int)decodeScalar("cp_Method_count", in, Codec.UNSIGNED5);
        cpIMethodCount = (int)decodeScalar("cp_Imethod_count", in, Codec.UNSIGNED5);
    }

    /**
     * Decode a number of scalars from the band file. A scalar is like a band,
     * but does not perform any band code switching.
     *
     * @param name
     *            the name of the scalar (primarily for logging/debugging
     *            purposes)
     * @param in
     *            the input stream to read from
     * @param codec
     *            the codec for this scalar
     * @return an array of decoded <code>long[]</code> values
     * @throws IOException
     *             if there is a problem reading from the underlying input
     *             stream
     * @throws Pack200Exception
     *             if there is a problem decoding the value or that the value is
     *             invalid
     */
    private long[] decodeScalar(String name, InputStream in, BHSDCodec codec,
            int n) throws IOException, Pack200Exception {
        // TODO Remove debugging code
        debug("Parsed #" + name + " (" + n + ")");
        return codec.decode(n, in);
    }

    /**
     * Decode a scalar from the band file. A scalar is like a band, but does not
     * perform any band code switching.
     *
     * @param name
     *            the name of the scalar (primarily for logging/debugging
     *            purposes)
     * @param in
     *            the input stream to read from
     * @param codec
     *            the codec for this scalar
     * @return the decoded value
     * @throws IOException
     *             if there is a problem reading from the underlying input
     *             stream
     * @throws Pack200Exception
     *             if there is a problem decoding the value or that the value is
     *             invalid
     */
    private long decodeScalar(String name, InputStream in, BHSDCodec codec)
            throws IOException, Pack200Exception {
        long ret =  codec.decode(in);
        debug("Parsed #" + name + " as " + ret);
        return ret;
    }

    public void setArchiveModtime(long archiveModtime) {
        this.archiveModtime = archiveModtime;
    }

    public void setArchiveSize(long archiveSize) {
        this.archiveSize = archiveSize;
    }

    private void setAttributeDefinitionCount(long valuie) {
        this.attributeDefinitionCount = (int) valuie;
    }

    private void setBandHeadersData(byte[] bandHeaders) {
        this.bandHeadersInputStream = new ByteArrayInputStream(bandHeaders);
    }

    public void setSegmentsRemaining(long value) {
        segmentsRemaining = (int) value;
    }

    /**
     * Completely reads in a byte array, akin to the implementation in
     * {@link java.lang.DataInputStream}. TODO Refactor out into a separate
     * InputStream handling class
     *
     * @param in
     *            the input stream to read from
     * @param data
     *            the byte array to read into
     * @throws IOException
     *             if a problem occurs during reading from the underlying stream
     * @throws Pack200Exception
     *             if a problem occurs with an unexpected value or unsupported
     *             codec
     */
    private static void readFully(InputStream in, byte[] data)
            throws IOException, Pack200Exception {
        int total = in.read(data);
        if (total == -1)
            throw new EOFException("Failed to read any data from input stream");
        while (total < data.length) {
            int delta = in.read(data, total, data.length - total);
            if (delta == -1)
                throw new EOFException(
                        "Failed to read some data from input stream");
            total += delta;
        }
    }

    public int getBandHeadersSize() {
        return bandHeadersSize;
    }

    protected void debug(String message) {
      if (System.getProperty("debug.pack200") != null) {
          System.err.println(message);
      }
  }


}
