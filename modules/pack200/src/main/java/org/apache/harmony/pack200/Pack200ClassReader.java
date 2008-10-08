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
import java.io.InputStream;

import org.objectweb.asm.ClassReader;

/**
 * Wrapper for ClassReader than enables pack200 to obtain extra class file
 * information
 */
public class Pack200ClassReader extends ClassReader {

    private boolean lastConstantHadWideIndex;

    /**
     * @param b
     */
    public Pack200ClassReader(byte[] b) {
        super(b);
    }

    /**
     * @param is
     * @throws IOException
     */
    public Pack200ClassReader(InputStream is) throws IOException {
        super(is);
    }

    /**
     * @param name
     * @throws IOException
     */
    public Pack200ClassReader(String name) throws IOException {
        super(name);
    }

    public Object readConst(int item, char[] buf) {
        lastConstantHadWideIndex = item > Byte.MAX_VALUE;
        return super.readConst(item, buf);
    }

    /**
     * @param b
     * @param off
     * @param len
     */
    public Pack200ClassReader(byte[] b, int off, int len) {
        super(b, off, len);
    }

    public boolean lastConstantHadWideIndex() {
        return lastConstantHadWideIndex;
    }

}
