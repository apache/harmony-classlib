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

package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.*;
import org.bouncycastle.crypto.params.KeyParameter;

public class IDEAEngine implements BlockCipher
{

    private static String message =
        "IDEA not supported see "+
          "http://harmony.apache.org/documentation/idea_algorithm.html";

    public IDEAEngine()
    {
    }

    public void init(boolean forEncryption, CipherParameters params)
    {
        throw new IllegalArgumentException(message);
    }

    public String getAlgorithmName()
    {
        return "IDEA";
    }

    public int getBlockSize()
    {
        return 8;
    }

    public int processBlock(byte[] in, int inOff, byte[] out, int outOff)
    {
        throw new IllegalStateException(message);
    }

    public void reset()
    {
    }

    protected static final int BLOCK_SIZE = 8;
}
