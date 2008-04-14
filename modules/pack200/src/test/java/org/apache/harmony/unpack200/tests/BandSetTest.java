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
package org.apache.harmony.unpack200.tests;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.TestCase;

import org.apache.harmony.unpack200.BHSDCodec;
import org.apache.harmony.unpack200.BandSet;
import org.apache.harmony.unpack200.Codec;
import org.apache.harmony.unpack200.Pack200Exception;
import org.apache.harmony.unpack200.Segment;
import org.apache.harmony.unpack200.SegmentHeader;

public class BandSetTest extends TestCase {

    public class MockSegment extends Segment {

        public SegmentHeader getSegmentHeader() {
            return new SegmentHeader(this);
        }
    }

    private final BandSet bandSet = new BandSet(new MockSegment()) {

        public void unpack(InputStream inputStream) throws IOException,
                Pack200Exception {
        }

    };

    public void testDecodeBandInt() throws IOException, Pack200Exception {
        BHSDCodec codec = Codec.BYTE1;
        byte[] bytes = new byte[] { (byte) 3, (byte) 56, (byte) 122, (byte) 78 };
        InputStream in = new ByteArrayInputStream(bytes);
        int[] ints = bandSet.decodeBandInt("Test Band", in, codec, 4);
        for (int i = 0; i < ints.length; i++) {
            assertEquals("Wrong value in position " + i, ints[i], bytes[i]);
        }
    }

    public void testDecodeBandLong() throws IOException, Pack200Exception {
        BHSDCodec codec = Codec.BYTE1;
        byte[] bytes = new byte[] { (byte) 3, (byte) 56, (byte) 122, (byte) 78,
                (byte) 0, (byte) 255 };
        InputStream in = new ByteArrayInputStream(bytes);
        long[] longs = bandSet.decodeBandLong("Test Band", in, codec, 6);
        for (int i = 0; i < longs.length; i++) {
            assertEquals("Wrong value in position " + i, (byte) longs[i],
                    bytes[i]);
        }
        // TODO: Should test this with other Codecs.
    }

    public void testDecodeBandLong2() throws IOException, Pack200Exception {

        BHSDCodec codec = Codec.DELTA5;
        byte[] bytes = new byte[] { 3, 1, 2, 3, 4, 5 }; // 3 is decoded to -2 by
        // DELTA5, which
        // signifies a switch to
        // BYTE1
        InputStream in = new ByteArrayInputStream(bytes);
        long[] longs = bandSet.decodeBandLong("Test Band", in, codec, 5);
        for (int i = 0; i < longs.length; i++) {
            assertEquals("Wrong value in position " + i, longs[i], bytes[i + 1]);
        }
    }

    public void testParseFlags1() {

    }

    public void testParseFlags2() {

    }

    public void testParseFlags3() {

    }

    public void testParseReferences1() {

    }

    public void testParseReferences2() {

    }

}
