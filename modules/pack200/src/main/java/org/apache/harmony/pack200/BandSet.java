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
import java.io.OutputStream;


public abstract class BandSet {

    public abstract void pack(OutputStream out) throws IOException, Pack200Exception;

    public byte[] encodeScalar(int[] band, BHSDCodec codec) throws Pack200Exception {
        return codec.encode(band);
    }

    public byte[] encodeScalar(int value, BHSDCodec codec) throws Pack200Exception {
        return codec.encode(value);
    }

    public byte[] encodeBandInt(int[] ints, Codec defaultCodec) throws Pack200Exception {
        // TODO non-default codecs
        return defaultCodec.encode(ints);
    }

    public boolean isPredictableSourceFileName(String className, String sourceFileName) {
        if (className.indexOf(".") != -1) {
            className = className.substring(className.lastIndexOf(".") + 1);
        }
        if (className.indexOf("$") != -1) {
            className = className.substring(0, className.indexOf("$"));
        }
        className += ".java";
        return className.equals(sourceFileName);
    }

    protected byte[] encodeFlags(long[] flags, BHSDCodec loCodec, BHSDCodec hiCodec,
            boolean haveHiFlags) throws Pack200Exception {
        if(!haveHiFlags) {
            int[] loBits = new int[flags.length];
            for (int i = 0; i < flags.length; i++) {
                loBits[i] = (int) flags[i];
            }
            return encodeBandInt(loBits, loCodec);
        } else {

            int[] hiBits = new int[flags.length];
            int[] loBits = new int[flags.length];
            for (int i = 0; i < flags.length; i++) {
                long l = flags[i];
                hiBits[i] = (int) (l >> 32);
                loBits[i] = (int) l;
            }
            byte[] hi = encodeBandInt(hiBits, hiCodec);
            byte[] lo = encodeBandInt(loBits, loCodec);
            byte[] total = new byte[hi.length + lo.length];
            System.arraycopy(hi, 0, total, 0, hi.length);
            System.arraycopy(lo, 0, total, hi.length + 1, lo.length);
            return total;
        }
    }

}
