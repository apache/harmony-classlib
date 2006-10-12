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
/**
 * @author Rustem V. Rafikov
 * @version $Revision: 1.3 $
 */
package javax.imageio.stream;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteOrder;

public abstract class ImageInputStreamImpl implements ImageInputStream {

    protected ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;

    protected long streamPos = 0;
    protected long flushedPos = 0;
    protected int bitOffset = 0;

    private boolean closed = false;

    private final PositionStack posStack = new PositionStack();

    public ImageInputStreamImpl() {}

    protected final void checkClosed() throws IOException {
        if (closed) {
            throw new IOException("stream is closed");
        }
    }

    public void setByteOrder(ByteOrder byteOrder) {
        this.byteOrder = byteOrder;
    }

    public ByteOrder getByteOrder() {
        return byteOrder;
    }

    public abstract int read() throws IOException;

    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    public abstract int read(byte[] b, int off, int len) throws IOException;

    public void readBytes(IIOByteBuffer buf, int len) throws IOException {
        if (buf == null) {
            throw new NullPointerException("buffer is NULL");
        }

        byte[] b = new byte[len];
        len = read(b, 0, b.length);

        buf.setData(b);
        buf.setOffset(0);
        buf.setLength(len);
    }

    public boolean readBoolean() throws IOException {
        int b = read();
        if (b < 0) {
            throw new EOFException("EOF reached");
        }
        return b != 0;
    }

    public byte readByte() throws IOException {
        int b = read();
        if (b < 0) {
            throw new EOFException("EOF reached");
        }
        return (byte) b;
    }

    public int readUnsignedByte() throws IOException {
        int b = read();
        if (b < 0) {
            throw new EOFException("EOF reached");
        }
        return b;
    }

    public short readShort() throws IOException {
        int b1 = read();
        int b2 = read();

        if (b1 < 0 || b2 < 0) {
            throw new EOFException("EOF reached");
        }

        return byteOrder == ByteOrder.BIG_ENDIAN ?
                (short) ((b1 << 8) | (b2 & 0xff)) :
                (short) ((b2 << 8) | (b1 & 0xff));
    }

    public int readUnsignedShort() throws IOException {
        //-- TODO implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public char readChar() throws IOException {
        //-- TODO implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public int readInt() throws IOException {
        //-- TODO implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public long readUnsignedInt() throws IOException {
        //-- TODO implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public long readLong() throws IOException {
        //-- TODO implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public float readFloat() throws IOException {
        //-- TODO implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public double readDouble() throws IOException {
        //-- TODO implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public String readLine() throws IOException {
        //-- TODO implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public String readUTF() throws IOException {
        //-- TODO implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void readFully(byte[] b, int off, int len) throws IOException {
        //-- TODO implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void readFully(byte[] b) throws IOException {
        readFully(b, 0, b.length);
    }

    public void readFully(short[] s, int off, int len) throws IOException {
        //-- TODO implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void readFully(char[] c, int off, int len) throws IOException {
        //-- TODO implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void readFully(int[] i, int off, int len) throws IOException {
        //-- TODO implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void readFully(long[] l, int off, int len) throws IOException {
        //-- TODO implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void readFully(float[] f, int off, int len) throws IOException {
        //-- TODO implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void readFully(double[] d, int off, int len) throws IOException {
        //-- TODO implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public long getStreamPosition() throws IOException {
        checkClosed();
        return streamPos;
    }

    public int getBitOffset() throws IOException {
        checkClosed();
        return bitOffset;
    }

    public void setBitOffset(int bitOffset) throws IOException {
        checkClosed();
        this.bitOffset = bitOffset;
    }

    public int readBit() throws IOException {
        //-- TODO implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public long readBits(int numBits) throws IOException {
        //-- TODO implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public long length() {
        return -1L;
    }

    public int skipBytes(int n) throws IOException {
        //-- TODO implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public long skipBytes(long n) throws IOException {
        //-- TODO implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void seek(long pos) throws IOException {
        checkClosed();
        if (pos < getFlushedPosition()) {
            throw new IllegalArgumentException("trying to seek before flushed pos");
        }
        bitOffset = 0;
        streamPos = pos;
    }

    public void mark() {
        try {
            posStack.push(getStreamPosition());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Stream marking error");
        }
    }

    public void reset() throws IOException {
        //-- TODO bit pos
        if (!posStack.isEmpty()) {
            long p = posStack.pop();
            if (p < flushedPos) {
                throw new IOException("marked position lies in the flushed portion of the stream");
            }
            seek(p);
        }
    }

    public void flushBefore(long pos) throws IOException {
        if (pos > getStreamPosition()) {
            throw new IndexOutOfBoundsException("Trying to flush outside of current position");
        }
        if (pos < flushedPos) {
            throw new IndexOutOfBoundsException("Trying to flush within already flushed portion");
        }
        flushedPos = pos;
        //-- TODO implement
    }

    public void flush() throws IOException {
        flushBefore(getStreamPosition());
    }

    public long getFlushedPosition() {
        return flushedPos;
    }

    public boolean isCached() {
        return false; //def
    }

    public boolean isCachedMemory() {
        return false; //def
    }

    public boolean isCachedFile() {
        return false; //def
    }

    public void close() throws IOException {
        checkClosed();
        closed = true;

    }

    @Override
    protected void finalize() throws Throwable {
        if (!closed) {
            try {
                close();
            } finally {
                super.finalize();
            }
        }
    }

    private static class PositionStack {
        private static final int SIZE = 10;

        private long[] values = new long[SIZE];
        private int pos = 0;


        void push(long v) {
            if (pos >= values.length) {
                ensure(pos+1);
            }
            values[pos++] = v;
        }

        long pop() {
            return values[--pos];
        }

        boolean isEmpty() {
            return pos == 0;
        }

        private void ensure(int size) {
            long[] arr = new long[Math.max(2 * values.length, size)];
            System.arraycopy(values, 0, arr, 0, values.length);
            values = arr;
        }
    }
}
