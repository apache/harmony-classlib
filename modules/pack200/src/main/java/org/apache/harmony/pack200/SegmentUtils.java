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

// TODO Write doc
public final class SegmentUtils {
    
	public static int countArgs(String descriptor) {
		int bra = descriptor.indexOf("(");
		int ket = descriptor.indexOf(")");
		if (bra == -1 || ket == -1 || ket < bra)
			throw new IllegalArgumentException("No arguments");

		boolean inType = false;
		int count = 0;
		for (int i = bra + 1; i < ket; i++) {
			char charAt = descriptor.charAt(i);
			if (inType && charAt == ';') {
				inType = false;
			} else if (!inType && charAt == 'L') {
				inType = true;
				count++;
			} else if (charAt == '[' || inType) {
				// NOP
			} else {
				count++;
			}
		}
		return count;
	}

	public static int countMatches(long[] flags, IMatcher matcher) {
		int count = 0;
		for (int i = 0; i < flags.length; i++) {
			if (matcher.matches(flags[i]))
				count++;
		}
		return count;
	}
    
    public static int countBit16(int[] flags) {
        int count = 0;
        for (int i = 0; i < flags.length; i++) {
            if ((flags[i] & (1 << 16)) != 0)
                count++;
        }
        return count;
    }
    
    public static int countBit16(long[] flags) {
        int count = 0;
        for (int i = 0; i < flags.length; i++) {
            if ((flags[i] & (1 << 16)) != 0)
                count++;
        }
        return count;
    }
    
    public static int countBit16(long[][] flags) {
        int count = 0;
        for (int i = 0; i < flags.length; i++) {
            for (int j = 0; j < flags[i].length; j++) {
                if ((flags[i][j] & (1 << 16)) != 0)
                    count++;
            }            
        }
        return count;
    }

	public static int countMatches(long[][] flags, IMatcher matcher) {
		int count = 0;
		for (int i = 0; i < flags.length; i++) {
			count += countMatches(flags[i], matcher);
		}
		return count;
	}

	private SegmentUtils() {
		// Intended to be a helper class
	}
}
