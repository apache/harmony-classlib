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
import java.util.List;


public abstract class BandSet {

    public abstract void pack(OutputStream out) throws IOException, Pack200Exception;

    public byte[] encodeScalar(int[] band, BHSDCodec codec) throws Pack200Exception {
        return codec.encode(band);
    }

    public byte[] encodeScalar(int value, BHSDCodec codec) throws Pack200Exception {
        return codec.encode(value);
    }

    public byte[] encodeBandInt(String name, int[] ints, Codec defaultCodec) throws Pack200Exception {
        // TODO non-default codecs
        return defaultCodec.encode(ints);
    }

    public boolean isPredictableSourceFileName(String className, String sourceFileName) {
        if (className.indexOf('.') != -1) {
            className = className.substring(className.lastIndexOf('.') + 1);
        }
        if (className.indexOf('$') != -1) {
            className = className.substring(0, className.indexOf('$'));
        }
        className += ".java";
        return className.equals(sourceFileName);
    }

    protected byte[] encodeFlags(String name, long[] flags, BHSDCodec loCodec, BHSDCodec hiCodec,
            boolean haveHiFlags) throws Pack200Exception {
        if(!haveHiFlags) {
            int[] loBits = new int[flags.length];
            for (int i = 0; i < flags.length; i++) {
                loBits[i] = (int) flags[i];
            }
            return encodeBandInt(name, loBits, loCodec);
        } else {

            int[] hiBits = new int[flags.length];
            int[] loBits = new int[flags.length];
            for (int i = 0; i < flags.length; i++) {
                long l = flags[i];
                hiBits[i] = (int) (l >> 32);
                loBits[i] = (int) l;
            }
            byte[] hi = encodeBandInt(name, hiBits, hiCodec);
            byte[] lo = encodeBandInt(name, loBits, loCodec);
            byte[] total = new byte[hi.length + lo.length];
            System.arraycopy(hi, 0, total, 0, hi.length);
            System.arraycopy(lo, 0, total, hi.length + 1, lo.length);
            return total;
        }
    }

    protected int[] listToArray(List integerList) {
        int[] array = new int[integerList.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = ((Integer)integerList.get(i)).intValue();
        }
        return array;
    }

    protected long[] longListToArray(List longList) {
        long[] array = new long[longList.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = ((Long)longList.get(i)).longValue();
        }
        return array;
    }

    protected int[] cpEntryListToArray(List list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = ((ConstantPoolEntry)list.get(i)).getIndex();
        }
        return array;
    }

    protected int[] cpEntryOrNullListToArray(List theList) {
        int[] array = new int[theList.size()];
        for (int j = 0; j < array.length; j++) {
            ConstantPoolEntry cpEntry = (ConstantPoolEntry) theList.get(j);
            array[j] = cpEntry == null ? 0 : cpEntry.getIndex() + 1;
        }
        return array;
    }

    protected byte[] encodeFlags(String name, long[][] flags, BHSDCodec loCodec, BHSDCodec hiCodec,
            boolean haveHiFlags) throws Pack200Exception {
        return encodeFlags(name, flatten(flags), loCodec, hiCodec, haveHiFlags);
   }

    private long[] flatten(long[][] flags) {
        int totalSize = 0;
        for (int i = 0; i < flags.length; i++) {
            totalSize += flags[i].length;
        }
        long[] flatArray = new long[totalSize];
        int index = 0;
        for (int i = 0; i < flags.length; i++) {
            for (int j = 0; j < flags[i].length; j++) {
                flatArray[index] = flags[i][j];
                index++;
            }
        }
        return flatArray;
    }

}
