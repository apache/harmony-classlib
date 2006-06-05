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
/**
 * @author Hugo Beilis
 * @author Osvaldo Demo
 * @author Jorge Rafael
 * @version 1.0
 */

package ar.org.fitc.test.integration.crypto.util;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BlockFillerOutputStream extends FilterOutputStream {

    private int blockSize = 8;

    private byte[] buffer = new byte[blockSize];

    public BlockFillerOutputStream(OutputStream out) {
        super(out);
    }

    public BlockFillerOutputStream(OutputStream out,int bsize) {
        super(out);
        this.blockSize = bsize;
    }

    private byte[] fill(byte[] data,int offset,int length) throws IOException {
//        if (length > blockSize-4) {
//            throw new IOException("Length larger than blockSize...");
//        }
        for (int i=offset; i< blockSize; i++) {
            if (i < offset+4) {
                buffer[i] = Util.intToByteArray(length)[i-offset];
            } else if (i < length+4) {
                buffer[i] = data[i];
            } else {
                buffer[i] = 0;
            }
        }
        return buffer;
    }

    @Override
    public void write(byte[] data) throws IOException {
        write(data, 0, data.length);
    }

    @Override
    public void write(byte[] data, int offset, int length) throws IOException {
        super.write(fill(data,offset,length));
    }

    @Override
    public void write(int data) throws IOException {
        write(Util.intToByteArray(data),0,4);
    }
}
