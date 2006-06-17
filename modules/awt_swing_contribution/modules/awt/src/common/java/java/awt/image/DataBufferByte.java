/*
 *  Copyright 2005 - 2006 The Apache Software Software Foundation or its licensors, as applicable.
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
 * @author Igor V. Stolyarov
 * @version $Revision$
 */
package java.awt.image;

public final class DataBufferByte extends DataBuffer {

    byte data[][];

    public DataBufferByte(byte dataArrays[][], int size, int offsets[]) {
        super(TYPE_BYTE, size, dataArrays.length, offsets);
        data = (byte[][]) dataArrays.clone();
    }

    public DataBufferByte(byte dataArrays[][], int size) {
        super(TYPE_BYTE, size, dataArrays.length);
        data = (byte[][]) dataArrays.clone();
    }

    public DataBufferByte(byte dataArray[], int size, int offset) {
        super(TYPE_BYTE, size, 1, offset);
        data = new byte[1][];
        data[0] = dataArray;
    }

    public DataBufferByte(byte dataArray[], int size) {
        super(TYPE_BYTE, size);
        data = new byte[1][];
        data[0] = dataArray;
    }

    public DataBufferByte(int size, int numBanks) {
        super(TYPE_BYTE, size, numBanks);
        data = new byte[numBanks][];
        int i = 0;
        while (i < numBanks) {
            data[i++] = new byte[size];
        }
    }

    public DataBufferByte(int size) {
        super(TYPE_BYTE, size);
        data = new byte[1][];
        data[0] = new byte[size];
    }

    public void setElem(int bank, int i, int val) {
        data[bank][offsets[bank] + i] = (byte) val;
    }

    public void setElem(int i, int val) {
        data[0][offset + i] = (byte) val;
    }

    public int getElem(int bank, int i) {
        return (int) (data[bank][offsets[bank] + i]) & 0xff;
    }

    public byte[] getData(int bank) {
        return data[bank];
    }

    public int getElem(int i) {
        return (int) (data[0][offset + i]) & 0xff;
    }

    public byte[][] getBankData() {
        return (byte[][]) data.clone();
    }

    public byte[] getData() {
        return data[0];
    }

}

