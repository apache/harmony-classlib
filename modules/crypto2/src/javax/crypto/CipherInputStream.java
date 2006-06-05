/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package javax.crypto;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author Diego Raul Mercado 
 * @version 1.2
 * @ar.org.fitc.spec_ref 
 */
public class CipherInputStream extends FilterInputStream {
    private Cipher cipher;

	/** The buffer's size is <code>blockSize * 2</code> */
    private byte[] buffer = null;

    /** Indicates where to read */
    private int offset = 0;

    /** Indicates available's bytes to read */
    private int length = 0;

    private int blockSize;

	private final byte[] getBytes(int len) throws IOException {
        int size = len + blockSize - (len % blockSize);
        byte[] blocks = new byte[size];
        byte[] result = null;
        int read = in.read(blocks, 0, size);

        if (read > 0) {
            result = cipher.update(blocks, 0, read);
        } else if (read == 0) {
            result = new byte[0];
        } else if (cipher != null) { //read == -1
            try {
                result = cipher.doFinal();
            } catch (IllegalBlockSizeException e) {
            } catch (BadPaddingException e) {
            }
            cipher = null;
        }
        return result;
    }

    /** @ar.org.fitc.spec_ref */
    protected CipherInputStream(InputStream is) {
        this(is, new NullCipher());
    }

    /** @ar.org.fitc.spec_ref */
    public CipherInputStream(InputStream is, Cipher c) {
        super(is);
        this.cipher = c;
        blockSize = cipher.getBlockSize() == 0 ? 1 : cipher.getBlockSize();
        buffer = new byte[blockSize * 2];
    }

    /** @ar.org.fitc.spec_ref */
    public int available() throws IOException {
        return length;
    }

    /** @ar.org.fitc.spec_ref */
    public void close() throws IOException {
        in.close();
        buffer = null;
        offset = 0;
        length = 0;
        cipher = null;
    }

    /** @ar.org.fitc.spec_ref */
    public boolean markSupported() {
        return false;
    }

    /** @ar.org.fitc.spec_ref */
    public int read() throws IOException {
        int b = 0;
        if (length > 0) {
            b = (int) buffer[offset++] & 0xFF;
            length--;
        } else {
            offset = 0;
            // We assert that the block get blocked. Otherwise, we can't read
            do {
                buffer = getBytes(blockSize);
            } while (buffer != null && buffer.length == 0);

            if (buffer == null) {
                b = -1;
            } else if (buffer.length > 0) {
                b = (int) buffer[offset++] & 0xFF;
                length = buffer.length - 1;
            }
        }
        return b;
    }

    /** 
     * @ar.org.fitc.spec_ref     
     * <code>NullPointerException</code> is thrown if <code>b<code> is null  
     */
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    /**
     * @ar.org.fitc.spec_ref 
     * <code>NullPointerException</code> is thrown if <code>b</code> is null 
     * <br>
     * <br>
     * <code>ArrayIndexOutOfBoundsException</code> is thrown if <code>off</code> 
     * is negative or <code>off</code> + <code>len</code> > 
     * <code>b.length</code> 
     */
    public int read(byte[] b, int off, int len) throws IOException {
        int read = 0;
        int min = 0;

        if (len > 0) {
            min = Math.min(length, len);
            if (b != null && buffer != null) {
                System.arraycopy(buffer, offset, b, off, min);
            }
            off += min;
            len -= min;
            read = min;
            offset += min;
            length -= min;

            if (len > 0) {
                buffer = getBytes(len);

                if (buffer == null) {
                    read = read == 0 ? -1 : read;
                } else if (buffer.length > 0) {
                    min = Math.min(buffer.length, len);
                    if (b != null) {
                        System.arraycopy(buffer, 0, b, off, min);
                    }
                    read += min;
                    offset = min;
                    length = buffer.length - offset;
                }
            }
        }
        return read;
    }

    /** @ar.org.fitc.spec_ref */
    public long skip(long n) throws IOException {
        int min = 0;

        if (n > 0) {
            min = (int) Math.min(length, n);
            length -= min;
            offset += min;
        }

        return min;
    }
}
