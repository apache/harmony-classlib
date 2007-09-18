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
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/**
 * TODO Comment -- quite a lot can be nicked from Codec, since this was created
 * from it
 * 
 * @author Alex Blewitt
 * 
 */
public final class BHSDCodec extends Codec {

	/**
	 * The maximum number of bytes in each coding word
	 */
	private int b;

	/**
	 * Whether delta encoding is used (0=false,1=true)
	 */
	private int d;

	/**
	 * The radix of the encoding
	 */
	private int h;

	/**
	 * The co-parameter of h; h-256
	 */
	private int l;

	/**
	 * Represents signed numbers or not (0=unsigned,1/2=signed)
	 */
	private int s;
    
    private long cardinality;

	/**
	 * Constructs an unsigned, non-delta Codec with the given B and H values.
	 * 
	 * @param b
	 *            the maximum number of bytes that a value can be encoded as
	 *            [1..5]
	 * @param h
	 *            the radix of the encoding [1..256]
	 */
	public BHSDCodec(int b, int h) {
		this(b, h, 0);
	}

	/**
	 * Constructs a non-delta Codec with the given B, H and S values.
	 * 
	 * @param b
	 *            the maximum number of bytes that a value can be encoded as
	 *            [1..5]
	 * @param h
	 *            the radix of the encoding [1..256]
	 * @param s
	 *            whether the encoding represents signed numbers (s=0 is
	 *            unsigned; s=1 is signed with 1s complement; s=2 is signed with ?)
	 */
	public BHSDCodec(int b, int h, int s) {
		this(b, h, s, 0);
	}

	/**
	 * Constructs a Codec with the given B, H, S and D values.
	 * 
	 * @param b
	 *            the maximum number of bytes that a value can be encoded as
	 *            [1..5]
	 * @param h
	 *            the radix of the encoding [1..256]
	 * @param s
	 *            whether the encoding represents signed numbers (s=0 is
	 *            unsigned; s=1 is signed with 1s complement; s=2 is signed with ?)
	 * @param d
	 *            whether this is a delta encoding (d=0 is non-delta; d=1 is
	 *            delta)
	 */
	public BHSDCodec(int b, int h, int s, int d) {
		if (b < 1 || b > 5)
			throw new IllegalArgumentException("1<=b<=5");
		if (h < 1 || h > 256)
			throw new IllegalArgumentException("1<=h<=256");
		if (s < 0 || s > 2)
			throw new IllegalArgumentException("0<=s<=2");
		if (d < 0 || d > 1)
			throw new IllegalArgumentException("0<=d<=1");
		if (b == 1 && h != 256)
			throw new IllegalArgumentException("b=1 -> h=256");
		if (h == 256 && b == 5)
			throw new IllegalArgumentException("h=256 -> b!=5");
		this.b = b;
		this.h = h;
		this.s = s;
		this.d = d;
		this.l = 256 - h;
        if(h == 1) {
            cardinality = b * 255 + 1;
        } else {
            cardinality = (long) ((long)(l * (1-Math.pow(h, b))/(1-h)) + Math.pow(h,b));
        }
	}

	/**
	 * Returns the cardinality of this codec; that is, the number of distinct
	 * values that it can contain.
	 * 
	 * @return the cardinality of this codec
	 */
	public long cardinality() {
		return cardinality;
	}

	
	public long decode(InputStream in) throws IOException, Pack200Exception {
		if (d != 0)
			throw new Pack200Exception(
					"Delta encoding used without passing in last value; this is a coding error");
		return decode(in, 0);
	}

	
	public long decode(InputStream in, long last) throws IOException,
			Pack200Exception {
		int n = 0;
		long z = 0;
		long x;
		do {
			x = in.read();
			if (x == -1)
				throw new EOFException("End of stream reached whilst decoding");
			z += x * Math.pow(h, n); 
            n++;
		} while (n < b & isHigh(x));
        long u = z;
        long twoPowS = (long)Math.pow(2, s);
        double twoPowSMinusOne = twoPowS-1;
        if(isSigned()) {
            if(u % twoPowS < twoPowSMinusOne) {
                if(cardinality < Math.pow(2, 32)) {
                    z = (long) (u - (Math.floor(u/ twoPowS)));                    
                } else {
                    z = cast32((long) (u - (Math.floor(u/ twoPowS))));
                }                
            } else {
                z = (long) (-Math.floor(u/ twoPowS) - 1);
            }
        }
		if (isDelta())
			z += last;
		return z;
	}

	private long cast32(long u) {
        u = (long) ((long) ((u + Math.pow(2, 31)) % Math.pow(2, 32)) - Math.pow(2, 31));
        return u;
    }

    private boolean isHigh(long x) {
        return x>=l;
    }

    /**
	 * True if this encoding can code the given value
	 * 
	 * @param value
	 *            the value to check
	 * @return <code>true</code> if the encoding can encode this value
	 */
	public boolean encodes(long value) {
		return (value >= smallest() && value <= largest());
	}

	/**
	 * Returns true if this codec is a delta codec
	 * @return true if this codec is a delta codec
	 */
	public boolean isDelta() {
		return d != 0;
	}

	/**
	 * Returns true if this codec is a signed codec
	 * @return true if this codec is a signed codec
	 */
	public boolean isSigned() {
		return s != 0;
	}

	/**
	 * Returns the largest value that this codec can represent.
	 * 
	 * @return the largest value that this codec can represent.
	 */
	public long largest() {
		long result;
		if (isDelta()) {
			result = Long.MAX_VALUE;
		} else {
			// TODO This can probably be optimized into a better mathematical statement
			if (s == 0) {
				result = cardinality() - 1;
			} else if (s == 1) {
				result = cardinality() / 2 - 1;
			} else if (s == 2) {
				result = (3L * cardinality()) / 4 - 1;
			} else {
				throw new Error("Unknown s value");
			}
		}
		return Math.min((s == 0 ? ((long) Integer.MAX_VALUE) << 1
				: Integer.MAX_VALUE) - 1, result);
	}
	/**
	 * Returns the smallest value that this codec can represent.
	 * 
	 * @return the smallest value that this codec can represent.
	 */
	public long smallest() {
		long result;
		if (isDelta()) {
			result = Integer.MIN_VALUE;
		} else {
			if (isSigned()) {
				result = -cardinality() / (1 << s);
			} else {
				result = 0;
			}
		}
		return Math.max(Integer.MIN_VALUE, result);
	}
	/**
	 * Returns the codec in the form (1,256) or (1,64,1,1). Note that trailing
	 * zero fields are not shown.
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer(11);
		buffer.append('(');
		buffer.append(b);
		buffer.append(',');
		buffer.append(h);
		if (s != 0 || d != 0) {
			buffer.append(',');
			buffer.append(s);
		}
		if (d != 0) {
			buffer.append(',');
			buffer.append(d);
		}
		buffer.append(')');
		return buffer.toString();
	}

	/**
	 * @return the b
	 */
	public int getB() {
		return b;
	}

	/**
	 * @return the h
	 */
	public int getH() {
		return h;
	}

	/**
	 * @return the l
	 */
	public int getL() {
		return l;
	}
}
