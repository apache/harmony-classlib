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
//NOTE: Do not use generics in this code; it needs to run on JVMs < 1.5
//NOTE: Do not extract strings as messages; this code is still a work-in-progress
//NOTE: Also, don't get rid of 'else' statements for the hell of it ...
import java.io.IOException;
import java.io.InputStream;

/**
 * A codec allows a sequence of bytes to be decoded into integer values (or vice
 * versa). It uses a variable-length encoding and a modified sign representation
 * such that small numbers are represented as a single byte, whilst larger
 * numbers take more bytes to encode. The number may be signed or unsigned; if
 * it is unsigned, it can be weighted towards positive numbers or equally
 * distributed using a one's complement. The codec also supports delta coding,
 * where a sequence of numbers is represented as a series of first-order
 * differences. So a delta encoding of the integers [1..10] would be represented
 * as a sequence of 10x1s. This allows the absolute value of a coded integer to
 * fall outside of the 'small number' range, whilst still being encoded as a
 * single byte.
 * 
 * A codec is configured with four parameters:
 * <dl>
 * <dt>B</dt>
 * <dd>The maximum number of bytes that each value is encoded as. B must be a
 * value between [1..5]. For a pass-through coding (where each byte is encoded
 * as itself, aka {@link #BYTE1}, B is 1 (each byte takes a maximum of 1 byte).</dd>
 * <dt>H</dt>
 * <dd>The radix of the integer. Values are defined as a sequence of values,
 * where value <code>n</code> is multiplied by <code>H^<sup>n</sup></code>.
 * So the number 1234 may be represented as the sequence 4 3 2 1 with a radix
 * (H) of 10. Note that other permutations are also possible; 43 2 1 will also
 * encode 1234. The co-parameter L is defined as 256-H. This is important
 * because only the last value in a sequence may be &lt; L; all prior values
 * must be &gt; L.</dd>
 * <dt>S</dt>
 * <dd>Whether the codec represents signed values (or not). This may have 3
 * values; 0 (unsigned), 1 (signed, ones complement) or 2 (signed, but not sure
 * what the difference is) TODO Update documentation when I know what the
 * difference is</dd>
 * <dt>D</dt>
 * <dd>Whether the codec represents a delta encoding. This may be 0 (no delta)
 * or 1 (delta encoding). A delta encoding of 1 indicates that values are
 * cumulative; a sequence of <code>1 1 1 1 1</code> will represent the
 * sequence <code>1 2 3 4 5</code>. For this reason, the codec supports two
 * variants of decode; one {@link #decode(InputStream, long) with} and one
 * {@link #decode(InputStream) without} a <code>last</code> parameter. If the
 * codec is a non-delta encoding, then the value is ignored if passed. If the
 * codec is a delta encoding, it is a run-time error to call the value without
 * the extra parameter, and the previous value should be returned. (It was
 * designed this way to support multi-threaded access without requiring a new
 * instance of the Codec to be cloned for each use.)
 * <dt>
 * </dl>
 * 
 * Codecs are notated as (B,H,S,D) and either D or S,D may be omitted if zero.
 * Thus {@link #BYTE1} is denoted (1,256,0,0) or (1,256). The
 * {@link #toString()} method prints out the condensed form of the encoding.
 * Often, the last character in the name ({@link #BYTE1}, {@link #UNSIGNED5})
 * gives a clue as to the B value. Those that start with U ({@link #UDELTA5},
 * {@link #UNSIGNED5}) are unsigned; otherwise, in most cases, they are signed.
 * The presence of the word Delta ({@link #DELTA5}, {@link #UDELTA5})
 * indicates a delta encoding is used.
 * 
 * This codec is really quite cool for storing compressed information, and could
 * be used entirely separately from the Pack200 implementation for efficient
 * transfer of integer data if required.
 * 
 * Note that all information is byte-oriented; for decoding float/double
 * information, the bit values are converted (not cast) into a long type. Note
 * that long values are used throughout even though most may be cast to ints;
 * this is primarily to avoid having to worry about signed values, even if it
 * would be more efficient to do so.
 * 
 * There are a number of standard codecs ({@link #UDELTA5}, {@link #UNSIGNED5},
 * {@link #BYTE1}, {@link #CHAR3}) that are used in the implementation of many
 * bands; but there are a variety of other ones, and indeed the specification
 * assumes that other combinations of values can result in more specific and
 * efficient formats. There are also a sequence of canonical encodings defined
 * by the Pack200 specification, which allow a codec to be referred to by
 * canonical number. TODO Add links to canonical numbers when this has been
 * done.
 * 
 * @author Alex Blewitt
 * @version $Revision: $
 */
public abstract class Codec {
	/**
	 * BCI5 = (5,4): Used for storing branching information in bytecode.
	 */
	public static final BHSDCodec BCI5 = new BHSDCodec(5, 4);

	/**
	 * BRANCH5 = (5,4,2): Used for storing branching information in bytecode.
	 */
	public static final BHSDCodec BRANCH5 = new BHSDCodec(5, 4, 2);

	/**
	 * BYTE1 = (1,256): Used for storing plain bytes.
	 */
	public static final BHSDCodec BYTE1 = new BHSDCodec(1, 256);

	/**
	 * CHAR3 = (3,128): Used for storing text (UTF-8) strings. NB This isn't
	 * quite the same as UTF-8, but has similar properties; ASCII characters
	 * &lt; 127 are stored in a single byte.
	 */
	public static final BHSDCodec CHAR3 = new BHSDCodec(3, 128);

	/**
	 * DELTA5 = (5,64,1,1): Used for the majority of numerical codings where
	 * there is a correlated sequence of signed values.
	 */
	public static final BHSDCodec DELTA5 = new BHSDCodec(5, 64, 1, 1);

	/**
	 * DELTA5 = (5,64,2,1): Used for the majority of numerical codings where
	 * there is a correlated sequence of signed values, but where most of them
	 * are expected to be non-negative.
	 */
	public static final BHSDCodec MDELTA5 = new BHSDCodec(5, 64, 2, 1);

	/**
	 * SIGNED5 = (5,64,1): Used for small signed values.
	 */
	public static final BHSDCodec SIGNED5 = new BHSDCodec(5, 64, 1);

	/**
	 * UDELTA5 = (5,64,0,1): Used for the majority of numerical codings where
	 * there is a correlated sequence of unsigned values.
	 */
	public static final BHSDCodec UDELTA5 = new BHSDCodec(5, 64, 0, 1);

	/**
	 * USIGNED5 = (5,64): Used for small unsigned values.
	 */
	public static final BHSDCodec UNSIGNED5 = new BHSDCodec(5, 64);

	/**
	 * Decode a sequence of bytes from the given input stream, returning the
	 * value as a long. Note that this method can only be applied for non-delta
	 * encodings.
	 * 
	 * @param in
	 *            the input stream to read from
	 * @return the value as a long
	 * @throws IOException
	 *             if there is a problem reading from the underlying input
	 *             stream
	 * @throws Pack200Exception
	 *             if the encoding is a delta encoding
	 */
	public abstract long decode(InputStream in) throws IOException,
			Pack200Exception;

	/**
	 * Decode a sequence of bytes from the given input stream, returning the
	 * value as a long. If this encoding is a delta encoding (d=1) then the
	 * previous value must be passed in as a parameter. If it is a non-delta
	 * encoding, then it does not matter what value is passed in, so it makes
	 * sense for the value to be passed in by default using code similar to:
	 * 
	 * <pre>
	 * long last = 0;
	 * while (condition) {
	 * 	last = codec.decode(in, last);
	 * 	// do something with last
	 * }
	 * </pre>
	 * 
	 * @param in
	 *            the input stream to read from
	 * @param last
	 *            the previous value read, which must be supplied if the codec
	 *            is a delta encoding
	 * @return the value as a long
	 * @throws IOException
	 *             if there is a problem reading from the underlying input
	 *             stream
	 * @throws Pack200Exception
	 *             if there is a problem decoding the value or that the value is
	 *             invalid
	 */
	public abstract long decode(InputStream in, long last) throws IOException,
			Pack200Exception;

	/**
	 * Decodes a sequence of <code>n</code> values from <code>in</code>.
	 * This should probably be used in most cases, since some codecs
	 * (such as @{link PopCodec}) only work when the number of values
	 * to be read is known.
	 * 
	 * @param n
	 *            the number of values to decode
	 * @param in
	 *            the input stream to read from
	 * @return an array of <code>long</code> values corresponding to values
	 *         decoded
	 * @throws IOException
	 *             if there is a problem reading from the underlying input
	 *             stream
	 * @throws Pack200Exception
	 *             if there is a problem decoding the value or that the value is
	 *             invalid
	 */
	public long[] decode(int n, InputStream in) throws IOException,
			Pack200Exception {
		long result[] = new long[n];
		long last = 0;
		for(int i=0;i<n;i++) {
			result[i] = last = decode(in,last);
		}
		return result;
	}
    
    /**
     * Decodes a sequence of <code>n</code> values from <code>in</code>.
     * 
     * @param n
     *            the number of values to decode
     * @param in
     *            the input stream to read from
     * @param firstValue
     *            the first value in the band if it has already been read
     * @return an array of <code>long</code> values corresponding to values
     *         decoded, with firstValue as the first value in the array.
     * @throws IOException
     *             if there is a problem reading from the underlying input
     *             stream
     * @throws Pack200Exception
     *             if there is a problem decoding the value or that the value is
     *             invalid
     */
    public long[] decode(int n, InputStream in, long firstValue) throws IOException,
            Pack200Exception {
        long result[] = new long[n + 1];
        long last = firstValue;
        for(int i=1;i<n+1;i++) {
            result[i] = last = decode(in,last);
        }
        return result;
    }
}
