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

import java.io.IOException;
import java.io.InputStream;

public class PopulationCodec extends Codec {
	private Codec favouredCodec;
	private Codec tokenCodec;
	private Codec unvafouredCodec;
	private int l;
    private long[] favoured;
	
	public PopulationCodec(Codec favouredCodec, Codec tableCodec, Codec unvafouredCodec) {
		this.favouredCodec = favouredCodec;
		this.tokenCodec = tableCodec;
		this.unvafouredCodec = unvafouredCodec;
	}

	public PopulationCodec(Codec favouredCodec, int l, Codec unvafouredCodec) {
		if (l >= 256 || l <=0)
			throw new IllegalArgumentException("L must be between 1..255");
		this.favouredCodec = favouredCodec;
		this.l = l;
		this.unvafouredCodec = unvafouredCodec;
	}

	
	public long decode(InputStream in) throws IOException, Pack200Exception {
		throw new Pack200Exception("Population encoding does not work unless the number of elements are known");
	}

	
	public long decode(InputStream in, long last) throws IOException,
			Pack200Exception {
		throw new Pack200Exception("Population encoding does not work unless the number of elements are known");
	}

	
	public long[] decode(int n, InputStream in) throws IOException, Pack200Exception {
		favoured = new long[n]; // there must be <= n  values, but probably a lot less
		long result[];
		// read table of favorites first
		long smallest = Long.MAX_VALUE;
		long last = 0;
		long value = 0; // TODO Are these sensible starting points?
		int k = -1;
		while( true ) {
			last = value;
			value = favouredCodec.decode(in,last);
			if ( value == smallest || value == last)
				break;
			favoured[++k] = value;
			if (Math.abs(smallest) > Math.abs(value)) {
				smallest = value;
			} else if (Math.abs(smallest) == Math.abs(value) ) {
				// ensure that -X and +X -> +X
				smallest = Math.abs(smallest);
			}
		}
		// if tokenCodec needs to be derived from the T, L and K values
		if (tokenCodec == null) {
			if (k < 256) {
				tokenCodec = Codec.BYTE1;
			} else {
				// if k >= 256, b >= 2
				int b = 1;
				while(++b < 5 && tokenCodec == null) {
					BHSDCodec codec = new BHSDCodec(b,256-l,0);
					if (codec.encodes(k))
						tokenCodec = codec;
				}
				if (tokenCodec == null)
					throw new Pack200Exception("Cannot calculate token codec from " + k + " and " + l);
			}
		}
		// read favorites
		result = tokenCodec.decode(n, in);		
        // read unfavorites
        last = 0;
        for(int i = 0; i < n; i++) {
            int index = (int) result[i];
            if (index == 0) {
                result[i] = last = unvafouredCodec.decode(in, last);
            } else {
                result[i] = favoured[index-1];
            }
        }
		return result;
    }

    public long[] getFavoured() {
        return favoured;
    }

    public Codec getFavouredCodec() {
        return favouredCodec;
    }

    public Codec getUnvafouredCodec() {
        return unvafouredCodec;
    }
}
