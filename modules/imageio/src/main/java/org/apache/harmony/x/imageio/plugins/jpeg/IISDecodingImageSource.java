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
 * @author Rustem Rafikov
 * @version $Revision: 1.2 $
 */
package org.apache.harmony.x.imageio.plugins.jpeg;

import javax.imageio.stream.ImageInputStream;

import org.apache.harmony.awt.gl.image.DecodingImageSource;

import java.io.InputStream;
import java.io.IOException;

/**
 * This allows usage of the java2d jpegdecoder with ImageInputStream in
 * the JPEGImageReader. Temporary, only to make JPEGImageReader#read(..)
 * working.
 *
 */
public class IISDecodingImageSource extends DecodingImageSource {

    private InputStream is;

    public IISDecodingImageSource(ImageInputStream iis) {
        is = new IISToInputStreamWrapper(iis);
    }

    protected boolean checkConnection() {
        return true;
    }

    protected InputStream getInputStream() {
        return is;
    }

    static class IISToInputStreamWrapper extends InputStream {

        private ImageInputStream input;

        public IISToInputStreamWrapper(ImageInputStream input) {
            this.input=input;
        }

        public int read() throws IOException {
            return input.read();
        }

        public int read(byte[] b) throws IOException {
            return input.read(b);
        }

        public int read(byte[] b, int off, int len) throws IOException {
            return input.read(b, off, len);
        }

        public long skip(long n) throws IOException {
            return input.skipBytes(n);
        }

        public boolean markSupported() {
            return true;
        }

        public void mark(int readlimit) {
            input.mark();
        }

        public void reset() throws IOException {
            input.reset();
        }

        public void close() throws IOException {
            input.close();
        }
    }
}
