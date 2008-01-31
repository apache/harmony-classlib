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

package java.io;

import org.apache.harmony.luni.util.Msg;
import org.apache.harmony.luni.util.Util;

/**
 * DataInputStream is a filter class which can read typed data from a Stream.
 * Typically, this stream has been written by a DataOutputStream. Types that can
 * be read include byte, 16-bit short, 32-bit int, 32-bit float, 64-bit long,
 * 64-bit double, byte strings, and UTF Strings.
 * 
 * @see DataOutputStream
 */
public class DataInputStream extends FilterInputStream implements DataInput {
    /**
     * Constructs a new DataInputStream on the InputStream <code>in</code>.
     * All reads can now be filtered through this stream. Note that data read by
     * this Stream is not in a human readable format and was most likely created
     * by a DataOutputStream.
     * 
     * @param in
     *            the target InputStream to filter reads on.
     * 
     * @see DataOutputStream
     * @see RandomAccessFile
     */

    byte[] buff;

    public DataInputStream(InputStream in) {
        super(in);
        buff = new byte[8];
    }

    /**
     * Reads bytes from the source stream into the byte array
     * <code>buffer</code>. The number of bytes actually read is returned.
     * 
     * @param buffer
     *            the buffer to read bytes into
     * @return the number of bytes actually read or -1 if end of stream.
     * 
     * @throws IOException
     *             If a problem occurs reading from this DataInputStream.
     * 
     * @see DataOutput#write(byte[])
     * @see DataOutput#write(byte[], int, int)
     */
    @Override
    public final int read(byte[] buffer) throws IOException {
        return in.read(buffer, 0, buffer.length);
    }

    /**
     * Read at most <code>length</code> bytes from this DataInputStream and
     * stores them in byte array <code>buffer</code> starting at
     * <code>offset</code>. Answer the number of bytes actually read or -1 if
     * no bytes were read and end of stream was encountered.
     * 
     * @param buffer
     *            the byte array in which to store the read bytes.
     * @param offset
     *            the offset in <code>buffer</code> to store the read bytes.
     * @param length
     *            the maximum number of bytes to store in <code>buffer</code>.
     * @return the number of bytes actually read or -1 if end of stream.
     * 
     * @throws IOException
     *             If a problem occurs reading from this DataInputStream.
     * 
     * @see DataOutput#write(byte[])
     * @see DataOutput#write(byte[], int, int)
     */
    @Override
    public final int read(byte[] buffer, int offset, int length)
            throws IOException {
        return in.read(buffer, offset, length);
    }

    /**
     * Reads a boolean from this stream.
     * 
     * @return the next boolean value from the source stream.
     * 
     * @throws IOException
     *             If a problem occurs reading from this DataInputStream.
     * 
     * @see DataOutput#writeBoolean(boolean)
     */
    public final boolean readBoolean() throws IOException {
        int temp = in.read();
        if (temp < 0) {
            throw new EOFException();
        }
        return temp != 0;
    }

    /**
     * Reads an 8-bit byte value from this stream.
     * 
     * @return the next byte value from the source stream.
     * 
     * @throws IOException
     *             If a problem occurs reading from this DataInputStream.
     * 
     * @see DataOutput#writeByte(int)
     */
    public final byte readByte() throws IOException {
        int temp = in.read();
        if (temp < 0) {
            throw new EOFException();
        }
        return (byte) temp;
    }

    /**
     * Reads a 16-bit character value from this stream.
     * 
     * @return the next <code>char</code> value from the source stream.
     * 
     * @throws IOException
     *             If a problem occurs reading from this DataInputStream.
     * 
     * @see DataOutput#writeChar(int)
     */
    private int readToBuff(int count) throws IOException {
        int offset = 0;

        while(offset < count) {
            int bytesRead = in.read(buff, offset, count - offset);
            if(bytesRead == -1) return bytesRead;
            offset += bytesRead;
        } 
        return offset;
    }

    public final char readChar() throws IOException {
        if (readToBuff(2) < 0){
            throw new EOFException();
        }
        return (char) (((buff[0] & 0xff) << 8) | (buff[1] & 0xff));

    }

    /**
     * Reads a 64-bit <code>double</code> value from this stream.
     * 
     * @return the next <code>double</code> value from the source stream.
     * 
     * @throws IOException
     *             If a problem occurs reading from this DataInputStream.
     * 
     * @see DataOutput#writeDouble(double)
     */
    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }

    /**
     * Reads a 32-bit <code>float</code> value from this stream.
     * 
     * @return the next <code>float</code> value from the source stream.
     * 
     * @throws IOException
     *             If a problem occurs reading from this DataInputStream.
     * 
     * @see DataOutput#writeFloat(float)
     */
    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }

    /**
     * Reads bytes from this stream into the byte array <code>buffer</code>.
     * This method will block until <code>buffer.length</code> number of bytes
     * have been read.
     * 
     * @param buffer
     *            to read bytes into
     * 
     * @throws IOException
     *             If a problem occurs reading from this DataInputStream.
     * 
     * @see DataOutput#write(byte[])
     * @see DataOutput#write(byte[], int, int)
     */
    public final void readFully(byte[] buffer) throws IOException {
        readFully(buffer, 0, buffer.length);
    }

    /**
     * Reads bytes from this stream and stores them in the byte array
     * <code>buffer</code> starting at the position <code>offset</code>.
     * This method blocks until <code>count</code> bytes have been read.
     * 
     * @param buffer
     *            the byte array into which the data is read
     * @param offset
     *            the offset the operation start at
     * @param length
     *            the maximum number of bytes to read
     * 
     * @throws IOException
     *             if a problem occurs while reading from this stream
     * @throws EOFException
     *             if reaches the end of the stream before enough bytes have
     *             been read
     * @see java.io.DataInput#readFully(byte[], int, int)
     */
    public final void readFully(byte[] buffer, int offset, int length)
            throws IOException {
        if (length < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (length == 0) {
            return;
        }
        if (in == null) {
            throw new NullPointerException(Msg.getString("KA00b")); //$NON-NLS-1$
        }
        if (buffer == null) {
            throw new NullPointerException(Msg.getString("K0047")); //$NON-NLS-1$
        }
        if (offset < 0 || offset > buffer.length - length) {
            throw new IndexOutOfBoundsException();
        }
        while (length > 0) {
            int result = in.read(buffer, offset, length);
            if (result < 0) {
                throw new EOFException();
            }
            offset += result;
            length -= result;
        }
    }

    /**
     * Reads a 32-bit integer value from this stream.
     * 
     * @return the next <code>int</code> value from the source stream.
     * 
     * @throws IOException
     *             If a problem occurs reading from this DataInputStream.
     * 
     * @see DataOutput#writeInt(int)
     */
    public final int readInt() throws IOException {
        if (readToBuff(4) < 0){
            throw new EOFException();
        }
        return ((buff[0] & 0xff) << 24) | ((buff[1] & 0xff) << 16) |
            ((buff[2] & 0xff) << 8) | (buff[3] & 0xff);
    }

    /**
     * Answers a <code>String</code> representing the next line of text
     * available in this BufferedReader. A line is represented by 0 or more
     * characters followed by <code>'\n'</code>, <code>'\r'</code>,
     * <code>"\n\r"</code> or end of stream. The <code>String</code> does
     * not include the newline sequence.
     * 
     * @return the contents of the line or null if no characters were read
     *         before end of stream.
     * 
     * @throws IOException
     *             If the DataInputStream is already closed or some other IO
     *             error occurs.
     * 
     * @deprecated Use BufferedReader
     */
    @Deprecated
    public final String readLine() throws IOException {
        StringBuffer line = new StringBuffer(80); // Typical line length
        boolean foundTerminator = false;
        while (true) {
            int nextByte = in.read();
            switch (nextByte) {
                case -1:
                    if (line.length() == 0 && !foundTerminator) {
                        return null;
                    }
                    return line.toString();
                case (byte) '\r':
                    if (foundTerminator) {
                        ((PushbackInputStream) in).unread(nextByte);
                        return line.toString();
                    }
                    foundTerminator = true;
                    /* Have to be able to peek ahead one byte */
                    if (!(in.getClass() == PushbackInputStream.class)) {
                        in = new PushbackInputStream(in);
                    }
                    break;
                case (byte) '\n':
                    return line.toString();
                default:
                    if (foundTerminator) {
                        ((PushbackInputStream) in).unread(nextByte);
                        return line.toString();
                    }
                    line.append((char) nextByte);
            }
        }
    }

    /**
     * Reads a 64-bit <code>long</code> value from this stream.
     * 
     * @return the next <code>long</code> value from the source stream.
     * 
     * @throws IOException
     *             If a problem occurs reading from this DataInputStream.
     * 
     * @see DataOutput#writeLong(long)
     */
    public final long readLong() throws IOException {
        if (readToBuff(8) < 0){
            throw new EOFException();
        }
        int i1 = ((buff[0] & 0xff) << 24) | ((buff[1] & 0xff) << 16) |
            ((buff[2] & 0xff) << 8) | (buff[3] & 0xff);
        int i2 = ((buff[4] & 0xff) << 24) | ((buff[5] & 0xff) << 16) |
            ((buff[6] & 0xff) << 8) | (buff[7] & 0xff);

        return ((i1 & 0xffffffffL) << 32) | (i2 & 0xffffffffL);
    }

    /**
     * Reads a 16-bit <code>short</code> value from this stream.
     * 
     * @return the next <code>short</code> value from the source stream.
     * 
     * @throws IOException
     *             If a problem occurs reading from this DataInputStream.
     * 
     * @see DataOutput#writeShort(int)
     */
    public final short readShort() throws IOException {
        if (readToBuff(2) < 0){
            throw new EOFException();
        }
        return (short) (((buff[0] & 0xff) << 8) | (buff[1] & 0xff));
    }

    /**
     * Reads an unsigned 8-bit <code>byte</code> value from this stream and
     * returns it as an int.
     * 
     * @return the next unsigned byte value from the source stream.
     * 
     * @throws IOException
     *             If a problem occurs reading from this DataInputStream.
     * 
     * @see DataOutput#writeByte(int)
     */
    public final int readUnsignedByte() throws IOException {
        int temp = in.read();
        if (temp < 0) {
            throw new EOFException();
        }
        return temp;
    }

    /**
     * Reads a 16-bit unsigned <code>short</code> value from this stream and
     * returns it as an int.
     * 
     * @return the next unsigned <code>short</code> value from the source
     *         stream.
     * 
     * @throws IOException
     *             If a problem occurs reading from this DataInputStream.
     * 
     * @see DataOutput#writeShort(int)
     */
    public final int readUnsignedShort() throws IOException {
        if (readToBuff(2) < 0){
            throw new EOFException();
        }
        return (char) (((buff[0] & 0xff) << 8) | (buff[1] & 0xff));
    }

    /**
     * Reads a UTF format String from this Stream.
     * 
     * @return the next UTF String from the source stream.
     * 
     * @throws IOException
     *             If a problem occurs reading from this DataInputStream.
     * 
     * @see DataOutput#writeUTF(java.lang.String)
     */
    public final String readUTF() throws IOException {
        return decodeUTF(readUnsignedShort());
    }


    String decodeUTF(int utfSize) throws IOException {
        return decodeUTF(utfSize, this);
    }

    private static String decodeUTF(int utfSize, DataInput in) throws IOException {
        byte[] buf = new byte[utfSize];
        char[] out = new char[utfSize];
        in.readFully(buf, 0, utfSize);

        return Util.convertUTF8WithBuf(buf, out, 0, utfSize);
    }

    /**
     * Reads a UTF format String from the DataInput Stream <code>in</code>.
     * 
     * @param in
     *            the input stream to read from
     * @return the next UTF String from the source stream.
     * 
     * @throws IOException
     *             If a problem occurs reading from this DataInputStream.
     * 
     * @see DataOutput#writeUTF(java.lang.String)
     */
    public static final String readUTF(DataInput in) throws IOException {
        return decodeUTF(in.readUnsignedShort(), in);
    }

    /**
     * Skips <code>count</code> number of bytes in this stream. Subsequent
     * <code>read()</code>'s will not return these bytes unless
     * <code>reset()</code> is used.
     * 
     * @param count
     *            the number of bytes to skip.
     * @return the number of bytes actually skipped.
     * 
     * @throws IOException
     *             If the stream is already closed or another IOException
     *             occurs.
     */
    public final int skipBytes(int count) throws IOException {
        int skipped = 0;
        long skip;
        while (skipped < count && (skip = in.skip(count - skipped)) != 0) {
            skipped += skip;
        }
        if (skipped < 0) {
            throw new EOFException();
        }
        return skipped;
    }
}
