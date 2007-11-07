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
import java.io.OutputStream;

public abstract class BandSet {
    
    public abstract void unpack(InputStream inputStream) throws IOException, Pack200Exception;
    
    public abstract void pack(OutputStream outputStream);
    
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
            BHSDCodec defaultCodec, int count) throws IOException,
            Pack200Exception {
        // TODO Might be able to improve this directly.
        int[] result = new int[count];
        long[] longResult = decodeBandLong(name, in, defaultCodec, count);
        for (int i = 0; i < count; i++) {
            result[i] = (int) longResult[i];
        }
        return result;
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
    public int[][] decodeBandInt(String name, InputStream in, BHSDCodec defaultCodec, int[] counts) throws IOException, Pack200Exception {
        int[][] result = new int[counts.length][];
        int totalCount = 0;
        for (int i = 0; i < counts.length; i++) {
            totalCount += counts[i];
        }
        int[] twoDResult = decodeBandInt(name, in, defaultCodec, totalCount);
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
        long[] band;
        Codec codecUsed = codec;
        if (codec.getB() == 1 || count == 0) {
            band = codec.decode(count, in);           
        } else {
            long[] getFirst = codec.decode(1, in);
            if (getFirst.length == 0) {
                return new int[0];
            }
            long first = getFirst[0];
            if (codec.isSigned() && first >= -256 && first <= -1) {
                // Non-default codec should be used
                codecUsed = CodecEncoding.getCodec((int) (-1 - first),
                        header.getBandHeadersInputStream(), codec);
                band = codecUsed.decode(count, in);          
            } else if (!codec.isSigned() && first >= codec.getL()
                    && first <= codec.getL() + 255) {
                // Non-default codec should be used
                codecUsed = CodecEncoding.getCodec((int) first
                        - codec.getL(), header.getBandHeadersInputStream(), codec);
                band = codecUsed.decode(count, in);
            } else {
                // First element should not be discarded
                band = codec.decode(count - 1, in, first);
            }
        }

        int[] returnBand = new int[band.length];
        for (int i = 0; i < returnBand.length; i++) {
            returnBand[i] = (int)band[i];
        }
        
        /*
         * Note - this is not in the spec, but seems to be used as an
         * optimization by the RI for bands where the minimum and maximum values
         * are known (ie reference bands). It will not hurt any encoding that is
         * following the spec because all the values decoded will be inside the
         * range anyway.
         */
        if (codecUsed instanceof BHSDCodec) {
            for (int i = 0; i < returnBand.length; i++) {
                while (returnBand[i] < 0) {
                    returnBand[i] += ((BHSDCodec) codecUsed).cardinality();
                }
                while (returnBand[i] > maxValue) {
                    returnBand[i] -= ((BHSDCodec) codecUsed).cardinality();
                }
            }
        }
        
        return returnBand;
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


}
