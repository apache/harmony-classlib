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

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BlockFillerInputStream extends FilterInputStream {

    private byte[] buffer = null;

    private int blockSize = 8;

    private byte[] bytecount = new byte[4];

    public BlockFillerInputStream(InputStream in) {
        super(in);
    }

    public BlockFillerInputStream(InputStream in,int bsize) {
        super(in);
        this.blockSize = bsize;
    }

    @Override
    public int read(byte[] data) throws IOException {
        return read(data,0,data.length);
    }

    @Override
    public int read() throws IOException {
        byte[] oneint = new byte[4];
        if (read(oneint,0,4) != -1){
            return Util.byteArrayToInt(oneint);
        } else {
            return -1;
        }
    }

    @Override
    public int read(byte[] data,int offset,int length) throws IOException {
//        if (length > blockSize - 4 ) {
//            throw new IOException("Not enough space at input byte array");
//        }
        buffer = new byte[blockSize];
        int readed = 0;
        try {
            readed = super.read(buffer,0,blockSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (readed != -1) {
            //getting byte count
            for (int i=0; i< 4; i++) {
                bytecount[i] = buffer[i];
            }
            length = Util.byteArrayToInt(bytecount);
            //getting data
            for (int i=offset; i< length+4; i++) {
                data[i] = buffer[i+4];
            }
            return length;
        } else {
            return -1;
        }
    }
}
