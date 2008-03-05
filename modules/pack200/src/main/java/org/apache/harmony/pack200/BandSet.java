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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.apache.harmony.pack200.bytecode.CPClass;
import org.apache.harmony.pack200.bytecode.CPDouble;
import org.apache.harmony.pack200.bytecode.CPFieldRef;
import org.apache.harmony.pack200.bytecode.CPFloat;
import org.apache.harmony.pack200.bytecode.CPInteger;
import org.apache.harmony.pack200.bytecode.CPInterfaceMethodRef;
import org.apache.harmony.pack200.bytecode.CPLong;
import org.apache.harmony.pack200.bytecode.CPMethodRef;
import org.apache.harmony.pack200.bytecode.CPNameAndType;
import org.apache.harmony.pack200.bytecode.CPString;
import org.apache.harmony.pack200.bytecode.CPUTF8;
import org.apache.harmony.pack200.bytecode.ClassConstantPool;

public abstract class BandSet {

    public abstract void unpack(InputStream inputStream) throws IOException, Pack200Exception;

    protected Segment segment;

    protected SegmentHeader header;

    public BandSet(Segment segment) {
        this.segment = segment;
        this.header = segment.getSegmentHeader();
    }

    /**
     * Decode a band and return an array of <code>int</code> values
     *
     * @param name
     *            the name of the band (primarily for logging/debugging
     *            purposes)
     * @param in
     *            the InputStream to decode from
     * @param defaultCodec
     *            the default codec for this band
     * @param count
     *            the number of elements to read
     * @return an array of decoded <code>int</code> values
     * @throws IOException
     *             if there is a problem reading from the underlying input
     *             stream
     * @throws Pack200Exception
     *             if there is a problem decoding the value or that the value is
     *             invalid
     */
    public int[] decodeBandInt(String name, InputStream in,
            BHSDCodec codec, int count) throws IOException,
            Pack200Exception {
        return decodeBandInt(name, in, codec, count, true);
    }

    /**
     * Decode a band and return an array of <code>int</code> values
     *
     * @param name
     *            the name of the band (primarily for logging/debugging
     *            purposes)
     * @param in
     *            the InputStream to decode from
     * @param defaultCodec
     *            the default codec for this band
     * @param count
     *            the number of elements to read
     * @return an array of decoded <code>int</code> values
     * @throws IOException
     *             if there is a problem reading from the underlying input
     *             stream
     * @throws Pack200Exception
     *             if there is a problem decoding the value or that the value is
     *             invalid
     */
    public int[] decodeBandInt(String name, InputStream in,
            BHSDCodec codec, int count, boolean negativesAllowed) throws IOException,
            Pack200Exception {
        int[] band;
        Codec codecUsed = codec;
        if (codec.getB() == 1 || count == 0) {
            return codec.decodeInts(count, in);
        }
        int[] getFirst = codec.decodeInts(1, in);
        if (getFirst.length == 0) {
            return getFirst;
        }
        int first = getFirst[0];
        if (codec.isSigned() && first >= -256 && first <= -1) {
            // Non-default codec should be used
            codecUsed = CodecEncoding.getCodec((-1 - first),
                    header.getBandHeadersInputStream(), codec);
            band = codecUsed.decodeInts(count, in);
        } else if (!codec.isSigned() && first >= codec.getL()
                && first <= codec.getL() + 255) {
            // Non-default codec should be used
            codecUsed = CodecEncoding.getCodec(first
                    - codec.getL(), header.getBandHeadersInputStream(), codec);
            band = codecUsed.decodeInts(count, in);
        } else {
            // First element should not be discarded
            band = codec.decodeInts(count - 1, in, first);
        }
        if(!negativesAllowed && codec != codecUsed) {
            if(codecUsed instanceof BHSDCodec && ((BHSDCodec)codecUsed).isSigned()) {
                for (int i = 0; i < band.length; i++) {
                    while(band[i] < 0) {
                        band[i] += ((BHSDCodec)codecUsed).cardinality();
                    }
                }
            }
        }
        if(codecUsed instanceof BHSDCodec) {
            long cardinality = ((BHSDCodec)codecUsed).cardinality();
            for (int i = 0; i < band.length; i++) {
                while(band[i] > cardinality) {
                    band[i] -= cardinality;
                }
            }
        }
        return band;
    }

    /**
     * Decode a band and return an array of <code>int[]</code> values
     *
     * @param name
     *            the name of the band (primarily for logging/debugging
     *            purposes)
     * @param in
     *            the InputStream to decode from
     * @param defaultCodec
     *            the default codec for this band
     * @param counts
     *            the numbers of elements to read for each int array within the
     *            array to be returned
     * @return an array of decoded <code>int[]</code> values
     * @throws IOException
     *             if there is a problem reading from the underlying input
     *             stream
     * @throws Pack200Exception
     *             if there is a problem decoding the value or that the value is
     *             invalid
     */
    public int[][] decodeBandInt(String name, InputStream in,
            BHSDCodec defaultCodec, int[] counts)
            throws IOException, Pack200Exception {
        return decodeBandInt(name, in, defaultCodec, counts, true);
    }

    // TODO: Use this version for all bands that shouldn't have negatives.
    /**
     * Decode a band and return an array of <code>int[]</code> values
     *
     * @param name
     *            the name of the band (primarily for logging/debugging
     *            purposes)
     * @param in
     *            the InputStream to decode from
     * @param defaultCodec
     *            the default codec for this band
     * @param counts
     *            the numbers of elements to read for each int array within the
     *            array to be returned
     * @return an array of decoded <code>int[]</code> values
     * @throws IOException
     *             if there is a problem reading from the underlying input
     *             stream
     * @throws Pack200Exception
     *             if there is a problem decoding the value or that the value is
     *             invalid
     */
    public int[][] decodeBandInt(String name, InputStream in,
            BHSDCodec defaultCodec, int[] counts, boolean negativesAllowed)
            throws IOException, Pack200Exception {
        int[][] result = new int[counts.length][];
        int totalCount = 0;
        for (int i = 0; i < counts.length; i++) {
            totalCount += counts[i];
        }
        int[] twoDResult = decodeBandInt(name, in, defaultCodec, totalCount, negativesAllowed);
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            result[i] = new int[counts[i]];
            for(int j = 0; j < result[i].length; j++) {
                result[i][j] = twoDResult[index];
                index++;
            }
        }
        return result;
    }

    /**
     * Decode a band and return an array of <code>long</code> values
     *
     * @param name
     *            the name of the band (primarily for logging/debugging
     *            purposes)
     * @param in
     *            the InputStream to decode from
     * @param codec
     *            the default codec for this band
     * @param count
     *            the number of elements to read
     * @return an array of decoded <code>long</code> values
     * @throws IOException
     *             if there is a problem reading from the underlying input
     *             stream
     * @throws Pack200Exception
     *             if there is a problem decoding the value or that the value is
     *             invalid
     */
    public long[] decodeBandLong(String name, InputStream in, BHSDCodec codec,
            int count) throws IOException, Pack200Exception {
        if (codec.getB() == 1 || count == 0) {
            return codec.decode(count, in);
        }
        long[] getFirst = codec.decode(1, in);
        if (getFirst.length == 0) {
            return getFirst;
        }
        long first = getFirst[0];
        if (codec.isSigned() && first >= -256 && first <= -1) {
            // Non-default codec should be used
            Codec nonDefaultCodec = CodecEncoding.getCodec((int) (-1 - first),
                    header.getBandHeadersInputStream(), codec);
            return nonDefaultCodec.decode(count, in);
        } else if (!codec.isSigned() && first >= codec.getL()
                && first <= codec.getL() + 255) {
            // Non-default codec should be used
            Codec nonDefaultCodec = CodecEncoding.getCodec((int) first
                    - codec.getL(), header.getBandHeadersInputStream(), codec);
            return nonDefaultCodec.decode(count, in);
        } else {
            // First element should not be discarded
            return codec.decode(count - 1, in, first);
        }
    }

    public byte[] encodeBandLong(long[] data, BHSDCodec codec) throws IOException, Pack200Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (int i = 0; i < data.length; i++) {
            baos.write(codec.encode(data[i], i == 0 ? 0 : data[i-1]));
        }
        return baos.toByteArray();
    }

    public long[] parseFlags(String name, InputStream in, int count,
            BHSDCodec codec, boolean hasHi) throws IOException,
            Pack200Exception {
        return parseFlags(name, in, new int[] { count },
                (hasHi ? codec : null), codec)[0];
    }

    public long[][] parseFlags(String name, InputStream in, int counts[],
            BHSDCodec codec, boolean hasHi) throws IOException,
            Pack200Exception {
        return parseFlags(name, in, counts, (hasHi ? codec : null), codec);
    }

    public long[] parseFlags(String name, InputStream in, int count,
            BHSDCodec hiCodec, BHSDCodec loCodec) throws IOException,
            Pack200Exception {
        return parseFlags(name, in, new int[] { count }, hiCodec, loCodec)[0];
    }

    public long[][] parseFlags(String name, InputStream in, int counts[],
            BHSDCodec hiCodec, BHSDCodec loCodec) throws IOException,
            Pack200Exception {
        int count = counts.length;
        if (count == 0) {
            return new long[][] { {} };
        }
        int sum = 0;
        long[][] result = new long[count][];
        for (int i = 0; i < count; i++) {
            result[i] = new long[counts[i]];
            sum += counts[i];
        }
        long[] hi = null;
        int[] lo;
        if(hiCodec != null) {
            hi = decodeBandLong(name, in, hiCodec, sum);
            lo = decodeBandInt(name, in, loCodec, sum);
        } else {
            lo = decodeBandInt(name, in, loCodec, sum);
        }

        int index = 0;
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                if(hi != null) {
                    result[i][j] = (hi[index] << 32) | lo[index];
                } else {
                    result[i][j] = lo[index];
                }
                index++;
            }
        }

        // TODO Remove debugging code
        debug("Parsed *" + name + " (" + result.length + ")");
        return result;
    }

    /**
     * Helper method to parse <i>count</i> references from <code>in</code>,
     * using <code>codec</code> to decode the values as indexes into
     * <code>reference</code> (which is populated prior to this call). An
     * exception is thrown if a decoded index falls outside the range
     * [0..reference.length-1].
     *
     * @param name
     *            the band name
     * @param in
     *            the input stream to read from
     * @param codec
     *            the codec to use for decoding
     * @param count
     *            the number of references to decode
     * @param reference
     *            the array of values to use for the indexes; often
     *            {@link #cpUTF8}
     *
     * @throws IOException
     *             if a problem occurs during reading from the underlying stream
     * @throws Pack200Exception
     *             if a problem occurs with an unexpected value or unsupported
     *             codec
     */
    public String[] parseReferences(String name, InputStream in,
            BHSDCodec codec, int count, String[] reference) throws IOException,
            Pack200Exception {
        return parseReferences(name, in, codec, new int[] { count },
                reference)[0];
    }

    /**
     * Helper method to parse <i>count</i> references from <code>in</code>,
     * using <code>codec</code> to decode the values as indexes into
     * <code>reference</code> (which is populated prior to this call). An
     * exception is thrown if a decoded index falls outside the range
     * [0..reference.length-1]. Unlike the other parseReferences, this
     * post-processes the result into an array of results.
     *
     * @param name
     *            TODO
     * @param in
     *            the input stream to read from
     * @param codec
     *            the codec to use for decoding
     * @param count
     *            the number of references to decode
     * @param reference
     *            the array of values to use for the indexes; often
     *            {@link #cpUTF8}
     *
     * @throws IOException
     *             if a problem occurs during reading from the underlying stream
     * @throws Pack200Exception
     *             if a problem occurs with an unexpected value or unsupported
     *             codec
     */
    public String[][] parseReferences(String name, InputStream in,
            BHSDCodec codec, int counts[], String[] reference)
            throws IOException, Pack200Exception {
        int count = counts.length;
        if (count == 0) {
            return new String[][] { {} };
        }
        String[][] result = new String[count][];
        int sum = 0;
        for (int i = 0; i < count; i++) {
            result[i] = new String[counts[i]];
            sum += counts[i];
        }
        // TODO Merge the decode and parsing of a multiple structure into one
        String[] result1 = new String[sum];
        int[] indices = decodeBandInt(name, in, codec, sum, reference.length - 1);
        for (int i1 = 0; i1 < sum; i1++) {
            int index = indices[i1];
            if (index < 0 || index >= reference.length)
                throw new Pack200Exception(
                        "Something has gone wrong during parsing references, index = " + index + ", array size = " + reference.length);
            result1[i1] = reference[index];
        }
        String[] refs = result1;
        // TODO Merge the decode and parsing of a multiple structure into one
        int pos = 0;
        for (int i = 0; i < count; i++) {
            int num = counts[i];
            result[i] = new String[num];
            System.arraycopy(refs, pos, result[i], 0, num);
            pos += num;
        }
        return result;
    }

    private int[] decodeBandInt(String name, InputStream in, BHSDCodec codec, int count, int maxValue) throws IOException, Pack200Exception {
        int[] band;
        Codec codecUsed = codec;
        if (codec.getB() == 1 || count == 0) {
            band = codec.decodeInts(count, in);
        } else {
            int[] getFirst = codec.decodeInts(1, in);
            if (getFirst.length == 0) {
                return new int[0];
            }
            int first = getFirst[0];
            if (codec.isSigned() && first >= -256 && first <= -1) {
                // Non-default codec should be used
                codecUsed = CodecEncoding.getCodec((-1 - first),
                        header.getBandHeadersInputStream(), codec);
                band = codecUsed.decodeInts(count, in);
            } else if (!codec.isSigned() && first >= codec.getL()
                    && first <= codec.getL() + 255) {
                // Non-default codec should be used
                codecUsed = CodecEncoding.getCodec(first
                        - codec.getL(), header.getBandHeadersInputStream(), codec);
                band = codecUsed.decodeInts(count, in);
            } else {
                // First element should not be discarded
                band = codec.decodeInts(count - 1, in, first);
            }
        }


        /*
         * Note - this is not in the spec, but seems to be used as an
         * optimization by the RI for bands where the minimum and maximum values
         * are known (ie reference bands). It will not hurt any implementation
         * that is following the spec because all the values decoded will be
         * inside the range anyway.
         */
        if (codecUsed instanceof BHSDCodec) {
            for (int i = 0; i < band.length; i++) {
                while (band[i] < 0) {
                    band[i] += ((BHSDCodec) codecUsed).cardinality();
                }
                while (band[i] > maxValue) {
                    band[i] -= ((BHSDCodec) codecUsed).cardinality();
                }
            }
        } else if (codecUsed instanceof PopulationCodec) {
            PopulationCodec popCodec = (PopulationCodec)codecUsed;
            long[] favoured = (long[]) popCodec.getFavoured().clone();
            Arrays.sort(favoured);
            for (int i = 0; i < band.length; i++) {
                if(band[i] < 0 || band[i] > maxValue) {
                    boolean favouredValue = Arrays.binarySearch(favoured, band[i]) > -1;
                    Codec theCodec = favouredValue ? popCodec.getFavouredCodec(): popCodec.getUnvafouredCodec();
                    if(theCodec instanceof BHSDCodec) {
                        while (band[i] < 0) {
                            band[i] +=  ((BHSDCodec) theCodec).cardinality();
                        }
                        while (band[i] > maxValue) {
                            band[i] -= ((BHSDCodec) theCodec).cardinality();
                        }
                    }
                }
            }
        }
        return band;
    }

    /**
     * This is a local debugging message to aid the developer in writing this
     * class. It will be removed before going into production. If the property
     * 'debug.pack200' is set, this will generate messages to stderr; otherwise,
     * it will be silent.
     *
     * @param message
     * @deprecated this should be removed from production code
     */
    protected void debug(String message) {
        segment.debug(message);
    }

    public CPInteger[] parseCPIntReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        int[] reference = segment.getCpBands().getCpInt();
        int[] indices = decodeBandInt(name, in, codec, count, reference.length - 1);
        CPInteger[] result = new CPInteger[indices.length];
        for (int i1 = 0; i1 < count; i1++) {
            int index = indices[i1];
            if (index < 0 || index >= reference.length)
                throw new Pack200Exception(
                        "Something has gone wrong during parsing references, index = " + index + ", array size = " + reference.length);
            result[i1] = segment.getCpBands().cpIntegerValue(new Integer(reference[index]));
        }
        return result;
    }

    public CPDouble[] parseCPDoubleReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        double[] reference = segment.getCpBands().getCpDouble();
        int[] indices = decodeBandInt(name, in, codec, count, reference.length - 1);
        CPDouble[] result = new CPDouble[indices.length];
        for (int i1 = 0; i1 < count; i1++) {
            int index = indices[i1];
            if (index < 0 || index >= reference.length)
                throw new Pack200Exception(
                        "Something has gone wrong during parsing references, index = " + index + ", array size = " + reference.length);
            result[i1] = segment.getCpBands().cpDoubleValue(new Double(reference[index]));
        }
        return result;
    }

    public CPFloat[] parseCPFloatReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        float[] reference = segment.getCpBands().getCpFloat();
        int[] indices = decodeBandInt(name, in, codec, count, reference.length - 1);
        CPFloat[] result = new CPFloat[indices.length];
        for (int i1 = 0; i1 < count; i1++) {
            int index = indices[i1];
            if (index < 0 || index >= reference.length)
                throw new Pack200Exception(
                        "Something has gone wrong during parsing references, index = " + index + ", array size = " + reference.length);
            result[i1] = segment.getCpBands().cpFloatValue(new Float(reference[index]));
        }
        return result;
    }

    public CPLong[] parseCPLongReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        long[] reference = segment.getCpBands().getCpLong();
        int[] indices = decodeBandInt(name, in, codec, count, reference.length - 1);
        CPLong[] result = new CPLong[indices.length];
        for (int i1 = 0; i1 < count; i1++) {
            int index = indices[i1];
            if (index < 0 || index >= reference.length)
                throw new Pack200Exception(
                        "Something has gone wrong during parsing references, index = " + index + ", array size = " + reference.length);
            result[i1] = segment.getCpBands().cpLongValue(new Long(reference[index]));
        }
        return result;
    }

    public CPUTF8[] parseCPUTF8References(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        String[] reference = segment.getCpBands().getCpUTF8();
        int[] indices = decodeBandInt(name, in, codec, count, reference.length - 1);
        CPUTF8[] result = new CPUTF8[indices.length];
        for (int i1 = 0; i1 < count; i1++) {
            int index = indices[i1];
            if (index < 0 || index >= reference.length)
                throw new Pack200Exception(
                        "Something has gone wrong during parsing references, index = " + index + ", array size = " + reference.length);
            result[i1] = segment.getCpBands().cpUTF8Value(reference[index], ClassConstantPool.DOMAIN_NORMALASCIIZ);
        }
        return result;
    }


    public CPUTF8[][] parseCPUTF8References(String name, InputStream in, BHSDCodec codec, int[] counts) throws IOException, Pack200Exception {
        String[] reference = segment.getCpBands().getCpUTF8();
        CPUTF8[][] result = new CPUTF8[counts.length][];
        int sum = 0;
        for (int i = 0; i < counts.length; i++) {
            result[i] = new CPUTF8[counts[i]];
            sum += counts[i];
        }
        CPUTF8[] result1 = new CPUTF8[sum];
        int[] indices = decodeBandInt(name, in, codec, sum, reference.length - 1);
        for (int i1 = 0; i1 < sum; i1++) {
            int index = indices[i1];
            if (index < 0 || index >= reference.length)
                throw new Pack200Exception(
                        "Something has gone wrong during parsing references, index = " + index + ", array size = " + reference.length);
            result1[i1] = segment.getCpBands().cpUTF8Value(reference[index], ClassConstantPool.DOMAIN_NORMALASCIIZ);
        }
        CPUTF8[] refs = result1;
        int pos = 0;
        for (int i = 0; i < counts.length; i++) {
            int num = counts[i];
            result[i] = new CPUTF8[num];
            System.arraycopy(refs, pos, result[i], 0, num);
            pos += num;
        }
        return result;
    }

    public CPString[] parseCPStringReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        String[] reference = segment.getCpBands().getCpString();
        int[] indices = decodeBandInt(name, in, codec, count, reference.length - 1);
        CPString[] result = new CPString[indices.length];
        for (int i1 = 0; i1 < count; i1++) {
            int index = indices[i1];
            if (index < 0 || index >= reference.length)
                throw new Pack200Exception(
                        "Something has gone wrong during parsing references, index = " + index + ", array size = " + reference.length);
            result[i1] = segment.getCpBands().cpStringValue(reference[index]);
        }
        return result;
    }

    public CPInterfaceMethodRef[] parseCPInterfaceMethodRefReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        CpBands cpBands = segment.getCpBands();
        String[] reference = cpBands.getCpIMethodClass();
        String[] descriptors = cpBands.getCpIMethodDescriptor();
        int[] indices = decodeBandInt(name, in, codec, count, reference.length - 1);
        CPInterfaceMethodRef[] result = new CPInterfaceMethodRef[indices.length];
        for (int i1 = 0; i1 < count; i1++) {
            int index = indices[i1];
            if (index < 0 || index >= reference.length)
                throw new Pack200Exception(
                        "Something has gone wrong during parsing references, index = "
                                + index + ", array size = " + reference.length);
            result[i1] = new CPInterfaceMethodRef(cpBands
                    .cpClassValue(reference[index]), cpBands
                    .cpNameAndTypeValue(descriptors[index]));
        }
        return result;
    }

    public CPMethodRef[] parseCPMethodRefReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        CpBands cpBands = segment.getCpBands();
        String[] reference = cpBands.getCpMethodClass();
        String[] descriptors = cpBands.getCpMethodDescriptor();
        int[] indices = decodeBandInt(name, in, codec, count, reference.length - 1);
        CPMethodRef[] result = new CPMethodRef[indices.length];
        for (int i1 = 0; i1 < count; i1++) {
            int index = indices[i1];
            if (index < 0 || index >= reference.length)
                throw new Pack200Exception(
                        "Something has gone wrong during parsing references, index = "
                                + index + ", array size = " + reference.length);
            result[i1] = new CPMethodRef(cpBands.cpClassValue(reference[index]),
                    cpBands.cpNameAndTypeValue(descriptors[index]));
        }
        return result;
    }

    public CPFieldRef[] parseCPFieldRefReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        CpBands cpBands = segment.getCpBands();
        String[] reference = cpBands.getCpFieldClass();
        String[] descriptors = cpBands.getCpFieldDescriptor();
        int[] indices = decodeBandInt(name, in, codec, count, reference.length - 1);
        CPFieldRef[] result = new CPFieldRef[indices.length];
        for (int i1 = 0; i1 < count; i1++) {
            int index = indices[i1];
            if (index < 0 || index >= reference.length)
                throw new Pack200Exception(
                        "Something has gone wrong during parsing references, index = "
                                + index + ", array size = " + reference.length);
            result[i1] = new CPFieldRef(cpBands.cpClassValue(reference[index]),
                    cpBands.cpNameAndTypeValue(descriptors[index]));
        }
        return result;
    }

    public CPNameAndType[] parseCPDescriptorReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        CpBands cpBands = segment.getCpBands();
        String[] reference = cpBands.getCpDescriptor();
        int[] indices = decodeBandInt(name, in, codec, count, reference.length - 1);
        CPNameAndType[] result = new CPNameAndType[indices.length];
        for (int i1 = 0; i1 < count; i1++) {
            int index = indices[i1];
            if (index < 0 || index >= reference.length)
                throw new Pack200Exception(
                        "Something has gone wrong during parsing references, index = " + index + ", array size = " + reference.length);
            result[i1] = cpBands.cpNameAndTypeValue(reference[index]);
        }
        return result;
    }

    public CPUTF8[] parseCPSignatureReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        String[] reference = segment.getCpBands().getCpSignature();
        int[] indices = decodeBandInt(name, in, codec, count, reference.length - 1);
        CPUTF8[] result = new CPUTF8[indices.length];
        for (int i1 = 0; i1 < count; i1++) {
            int index = indices[i1];
            if (index < 0 || index >= reference.length)
                throw new Pack200Exception(
                        "Something has gone wrong during parsing references, index = " + index + ", array size = " + reference.length);
            result[i1] = segment.getCpBands().cpUTF8Value(reference[index], ClassConstantPool.DOMAIN_SIGNATUREASCIIZ);
        }
        return result;
    }

    public CPClass[] parseCPClassReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        String[] reference = segment.getCpBands().getCpClass();
        int[] indices = decodeBandInt(name, in, codec, count, reference.length - 1);
        CPClass[] result = new CPClass[indices.length];
        for (int i1 = 0; i1 < count; i1++) {
            int index = indices[i1];
            if (index < 0 || index >= reference.length)
                throw new Pack200Exception(
                        "Something has gone wrong during parsing references, index = " + index + ", array size = " + reference.length);
            result[i1] = segment.getCpBands().cpClassValue(reference[index]);
        }
        return result;
    }

}
